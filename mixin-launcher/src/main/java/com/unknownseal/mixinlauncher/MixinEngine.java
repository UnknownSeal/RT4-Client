package com.unknownseal.mixinlauncher;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.MemberSubstitution;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;
import org.objectweb.asm.Type;

import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;

public class MixinEngine {

    private static final List<Runnable> mixins = new ArrayList<>();
    public static final Map<String, List<Method>> methodRegistry = new HashMap<>();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Mixin {
        Class<?> value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Inject {
        String method();
        Class<?>[] args() default {};
        At at();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface This {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface At {
        String value();
        String target() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface Slice {
        At from();
        At to();
    }

    public static void scanAndRegisterAnnotatedMixins(String basePackage) {
        try (ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .acceptPackages(basePackage)
                .scan()) {

            scanResult.getClassesWithAnnotation(Mixin.class.getName())
                    .loadClasses()
                    .forEach(cls -> {
                        MixinLogger.LOGGER.info("Found annotated mixin: " + cls.getName());
                        registerMixinClass(cls);
                    });
        } catch (Exception e) {
            MixinLogger.LOGGER.log(Level.SEVERE, "Failed to scan and register mixins", e);
        }
    }

    private static void registerMixinClass(Class<?> mixinClass) {
        Mixin mixinAnnotation = mixinClass.getAnnotation(Mixin.class);
        Class<?> targetClass = mixinAnnotation.value();

        for (Method method : mixinClass.getDeclaredMethods()) {
            if (!method.isAnnotationPresent(Inject.class)) continue;
            Inject inject = method.getAnnotation(Inject.class);

            String methodName = inject.method();
            Class<?>[] args = inject.args();
            String descriptor = "";
            try {
                Method targetMethod = Arrays.stream(targetClass.getDeclaredMethods())
                        .filter(m -> m.getName().equals(methodName))
                        .filter(m -> Arrays.equals(m.getParameterTypes(), args))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Method not found: " + methodName));
                descriptor = Type.getMethodDescriptor(targetMethod);
            } catch (Exception e) {
                MixinLogger.LOGGER.log(Level.WARNING, "Failed to resolve descriptor for method: " + methodName, e);
                continue;
            }

            String at = inject.at().value();
            String invokeTarget = inject.at().target();

            final String finalDescriptor = descriptor;

            mixins.add(() -> {
                try {
                    MixinLogger.LOGGER.info("Injecting into " + targetClass.getName() + "." + methodName + " at " + at);

                    ElementMatcher.Junction<MethodDescription> matcher = methodName.equals("<init>") ?
                            ElementMatchers.isConstructor() :
                            ElementMatchers.named(methodName);

                    matcher = matcher.and(ElementMatchers.hasDescriptor(finalDescriptor));

                    String key = targetClass.getName() + "." + methodName + "@" + at;
                    method.setAccessible(true);
                    methodRegistry.computeIfAbsent(key, k -> new ArrayList<>()).add(method);

                    DynamicType.Builder<?> builder = new ByteBuddy().redefine(targetClass);

                    switch (at.toUpperCase()) {
                        case "TAIL":
                            builder = builder.visit(Advice.to(TailDispatcher.class).on(matcher));
                            break;
                        case "HEAD":
                            builder = builder.visit(Advice.to(HeadDispatcher.class).on(matcher));
                            break;
                        case "INVOKE":
                            if (!invokeTarget.isEmpty() && invokeTarget.contains("#")) {
                                String[] parts = invokeTarget.split("#");
                                String methodSimple = parts[1];

                                builder = builder.visit(MemberSubstitution
                                        .relaxed()
                                        .method(ElementMatchers.named(methodSimple))
                                        .replaceWith(method)
                                        .on(matcher));
                            } else {
                                builder = builder.visit(Advice.to(InvokeDispatcher.class).on(matcher));
                            }
                            break;
                    }

                    builder.make()
                            .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

                    MixinLogger.LOGGER.info("Successfully injected into: " + targetClass.getName());
                } catch (Exception e) {
                    MixinLogger.LOGGER.log(Level.SEVERE, "Injection failed for: " + targetClass.getName(), e);
                }
            });
        }
    }

    public static class HeadDispatcher {
        @Advice.OnMethodEnter
        public static void onEnter(
                @Advice.Origin Method origin,
                @Advice.AllArguments Object[] args,
                @Advice.This(optional = true) Object self
        ) throws Exception {
            dispatch(origin, args, "HEAD", self);
        }
    }

    public static class TailDispatcher {
        @Advice.OnMethodExit
        public static void onExit(
                @Advice.Origin Method origin,
                @Advice.AllArguments Object[] args,
                @Advice.This(optional = true) Object self
        ) throws Exception {
            dispatch(origin, args, "TAIL", self);
        }
    }

    public static class InvokeDispatcher {
        @Advice.OnMethodEnter
        public static void onInvoke(
                @Advice.Origin Method origin,
                @Advice.AllArguments Object[] args,
                @Advice.This(optional = true) Object self
        ) throws Exception {
            dispatch(origin, args, "INVOKE", self);
        }
    }

    public static void dispatch(Method origin, Object[] args, String at, Object self) throws Exception {
        String key = origin.getDeclaringClass().getName() + "." + origin.getName() + "@" + at;
        List<Method> hooks = methodRegistry.get(key);
        if (hooks != null) {
            for (Method hook : hooks) {
                Annotation[][] paramAnnotations = hook.getParameterAnnotations();
                Class<?>[] paramTypes = hook.getParameterTypes();
                List<Object> invokeArgs = new ArrayList<>();
                int argIndex = 0;
                for (int i = 0; i < paramTypes.length; i++) {
                    boolean isThis = Arrays.stream(paramAnnotations[i]).anyMatch(a -> a.annotationType() == This.class);
                    if (isThis) {
                        invokeArgs.add(self);
                    } else {
                        if (argIndex < args.length) {
                            invokeArgs.add(args[argIndex++]);
                        } else {
                            invokeArgs.add(null);
                        }
                    }
                }
                hook.invoke(null, invokeArgs.toArray());
            }
        }
    }

    public static void applyAll() {
        for (Runnable mixin : mixins) {
            mixin.run();
        }
    }
}
