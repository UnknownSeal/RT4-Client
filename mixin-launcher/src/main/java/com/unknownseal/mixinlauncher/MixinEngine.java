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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.logging.Level;

public class MixinEngine {

    private static final List<Runnable> mixins = new ArrayList<>();
    public static final Map<String, List<Method>> methodRegistry = new HashMap<>();

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Mixin {
        Class<?>[] value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Inject {
        String method();
        Class<?>[] args() default {};
        At at();
        Slice slice() default @Slice(from = @At("HEAD"), to = @At("TAIL"));
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ModifyArg {
        String method();
        Class<?>[] args() default {};
        int index();
        At at();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface Redirect {
        String method();
        Class<?>[] args() default {};
        At at();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.PARAMETER)
    public @interface This {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    public @interface Shadow {}

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface At {
        String value();
        String target() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
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
                        Mixin annotation = cls.getAnnotation(Mixin.class);
                        for (Class<?> target : annotation.value()) {
                            registerMixinClass(cls, target);
                        }
                    });
        } catch (Exception e) {
            MixinLogger.LOGGER.log(Level.SEVERE, "Failed to scan and register mixins", e);
        }
    }

    private static void registerMixinClass(Class<?> mixinClass, Class<?> targetClass) {
        for (Field field : mixinClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Shadow.class)) {
                try {
                    Field targetField = targetClass.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    field.setAccessible(true);
                    if (Modifier.isStatic(targetField.getModifiers())) {
                        field.set(null, targetField.get(null));
                    } else if (Field.class.isAssignableFrom(field.getType())) {
                        field.set(null, targetField);
                    } else {
                        MixinLogger.LOGGER.warning("Cannot shadow instance field unless declared as 'Field': " + field.getName());
                    }
                    MixinLogger.LOGGER.info("Linked shadow field: " + field.getName());
                } catch (Exception e) {
                    MixinLogger.LOGGER.warning("Failed to link shadow field: " + field.getName() + ", reason: " + e);
                }
            }
        }

        for (Method method : mixinClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                handleInjectMethod(mixinClass, targetClass, method);
            } else if (method.isAnnotationPresent(ModifyArg.class)) {
                handleModifyArgMethod(targetClass, method);
            } else if (method.isAnnotationPresent(Redirect.class)) {
                handleRedirectMethod(targetClass, method);
            }
        }
    }

    private static void handleInjectMethod(Class<?> mixinClass, Class<?> targetClass, Method method) {
        Inject inject = method.getAnnotation(Inject.class);
        String methodName = inject.method();
        Class<?>[] args = inject.args();
        String descriptor;

        try {
            Method targetMethod = Arrays.stream(targetClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals(methodName))
                    .filter(m -> Arrays.equals(m.getParameterTypes(), args))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Method not found: " + methodName));
            descriptor = Type.getMethodDescriptor(targetMethod);
        } catch (Exception e) {
            MixinLogger.LOGGER.log(Level.WARNING, "Failed to resolve descriptor for method: " + methodName, e);
            return;
        }

        String at = inject.at().value();
        String invokeTarget = inject.at().target();
        final String finalDescriptor = descriptor;

        mixins.add(() -> {
            try {
                MixinLogger.LOGGER.info("Injecting into " + targetClass.getName() + "." + methodName + " at " + at);
                ElementMatcher.Junction<MethodDescription> matcher = methodName.equals("<init>")
                        ? ElementMatchers.isConstructor()
                        : ElementMatchers.named(methodName);
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

    private static void handleModifyArgMethod(Class<?> targetClass, Method method) {
        ModifyArg modifyArg = method.getAnnotation(ModifyArg.class);
        String methodName = modifyArg.method();
        Class<?>[] args = modifyArg.args();
        String descriptor;

        try {
            Method targetMethod = Arrays.stream(targetClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals(methodName))
                    .filter(m -> Arrays.equals(m.getParameterTypes(), args))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Method not found: " + methodName));
            descriptor = Type.getMethodDescriptor(targetMethod);
        } catch (Exception e) {
            MixinLogger.LOGGER.log(Level.WARNING, "Failed to resolve descriptor for method: " + methodName, e);
            return;
        }

        mixins.add(() -> {
            try {
                MixinLogger.LOGGER.info("Modifying argument for: " + targetClass.getName() + "." + methodName);
                ElementMatcher.Junction<MethodDescription> matcher = ElementMatchers.named(methodName)
                        .and(ElementMatchers.hasDescriptor(descriptor));

                String key = targetClass.getName() + "." + methodName + "@MODIFY_ARG";
                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new ArrayList<>()).add(method);

                DynamicType.Builder<?> builder = new ByteBuddy().redefine(targetClass)
                        .visit(Advice.to(ModifyArgDispatcher.class).on(matcher));

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

                MixinLogger.LOGGER.info("Successfully injected ModifyArg into: " + targetClass.getName());
            } catch (Exception e) {
                MixinLogger.LOGGER.log(Level.SEVERE, "ModifyArg injection failed for: " + targetClass.getName(), e);
            }
        });
    }

    private static void handleRedirectMethod(Class<?> targetClass, Method method) {
        Redirect redirect = method.getAnnotation(Redirect.class);
        String methodName = redirect.method();
        Class<?>[] args = redirect.args();
        String descriptor;

        try {
            Method targetMethod = Arrays.stream(targetClass.getDeclaredMethods())
                    .filter(m -> m.getName().equals(methodName))
                    .filter(m -> Arrays.equals(m.getParameterTypes(), args))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Method not found: " + methodName));
            descriptor = Type.getMethodDescriptor(targetMethod);
        } catch (Exception e) {
            MixinLogger.LOGGER.log(Level.WARNING, "Failed to resolve descriptor for method: " + methodName, e);
            return;
        }

        String key = targetClass.getName() + "." + methodName + "@REDIRECT";
        String target = redirect.at().target();
        if (target.isEmpty() || !target.contains("#")) {
            MixinLogger.LOGGER.warning("Invalid @Redirect target: " + target);
            return;
        }

        String[] parts = target.split("#");
        String methodSimple = parts[1];

        mixins.add(() -> {
            try {
                MixinLogger.LOGGER.info("Redirecting call to: " + target);

                ElementMatcher.Junction<MethodDescription> matcher = ElementMatchers.named(methodName)
                        .and(ElementMatchers.hasDescriptor(descriptor));

                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new ArrayList<>()).add(method);

                DynamicType.Builder<?> builder = new ByteBuddy()
                        .redefine(targetClass)
                        .visit(MemberSubstitution
                                .relaxed()
                                .method(ElementMatchers.named(methodSimple))
                                .replaceWith(method)
                                .on(matcher));

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

                MixinLogger.LOGGER.info("Successfully applied @Redirect to: " + targetClass.getName());
            } catch (Exception e) {
                MixinLogger.LOGGER.log(Level.SEVERE, "Redirect injection failed for: " + targetClass.getName(), e);
            }
        });
    }

    public static class HeadDispatcher {
        @Advice.OnMethodEnter
        public static void onEnter(@Advice.Origin Method origin, @Advice.AllArguments Object[] args, @Advice.This(optional = true) Object self) throws Exception {
            dispatch(origin, args, "HEAD", self);
        }
    }

    public static class TailDispatcher {
        @Advice.OnMethodExit
        public static void onExit(@Advice.Origin Method origin, @Advice.AllArguments Object[] args, @Advice.This(optional = true) Object self) throws Exception {
            dispatch(origin, args, "TAIL", self);
        }
    }

    public static class InvokeDispatcher {
        @Advice.OnMethodEnter
        public static void onInvoke(@Advice.Origin Method origin, @Advice.AllArguments Object[] args, @Advice.This(optional = true) Object self) throws Exception {
            dispatch(origin, args, "INVOKE", self);
        }
    }

    public static class ModifyArgDispatcher {
        @Advice.OnMethodEnter
        public static void modifyArgs(@Advice.Origin Method origin, @Advice.AllArguments(readOnly = false) Object[] args) throws Exception {
            String key = origin.getDeclaringClass().getName() + "." + origin.getName() + "@MODIFY_ARG";
            List<Method> hooks = methodRegistry.get(key);
            if (hooks != null) {
                for (Method hook : hooks) {
                    ModifyArg annotation = hook.getAnnotation(ModifyArg.class);
                    int index = annotation.index();
                    Object original = args[index];
                    Object replacement = hook.invoke(null, original);
                    args[index] = replacement;
                }
            }
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
