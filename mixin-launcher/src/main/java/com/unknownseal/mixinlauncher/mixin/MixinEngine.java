package com.unknownseal.mixinlauncher.mixin;

import com.unknownseal.mixinlauncher.utils.LauncherLogger;
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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.logging.Level;

public class MixinEngine {

    private static final List<String> earlyFailures = new ArrayList<>();
    private static final List<Runnable> mixins = new ArrayList<>();
    public static final Map<String, List<HookInfo>> methodRegistry = new HashMap<>();

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
    public @interface Shadow {
        Class<?> target();
    }

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
                        LauncherLogger.info("Found annotated mixin: " + cls.getName());
                        Mixin annotation = cls.getAnnotation(Mixin.class);
                        for (Class<?> target : annotation.value()) {
                            registerMixinClass(cls, target);
                        }
                    });
        } catch (Exception e) {
            LauncherLogger.error("Failed to scan and register mixins: " + e);
        }
    }

    private static void registerMixinClass(Class<?> mixinClass, Class<?> targetClass) {
        boolean hasHooks = false;

        for (Field field : mixinClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Shadow.class)) {
                Shadow shadow = field.getAnnotation(Shadow.class);
                Class<?> shadowTarget = shadow.target();

                try {
                    Field targetField = shadowTarget.getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    field.setAccessible(true);
                    LauncherLogger.info("Linked shadow field: " + field.getName() + " from " + shadowTarget.getName());
                } catch (Exception e) {
                    LauncherLogger.warn("Failed to link shadow field: " + field.getName()
                            + " in mixin " + mixinClass.getSimpleName() + " → " + e.getMessage());
                }
            }
        }

        for (Method method : mixinClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inject.class)) {
                handleInjectMethod(mixinClass, targetClass, method);
                hasHooks = true;
            } else if (method.isAnnotationPresent(ModifyArg.class)) {
                handleModifyArgMethod(mixinClass, targetClass, method);
                hasHooks = true;
            } else if (method.isAnnotationPresent(Redirect.class)) {
                handleRedirectMethod(mixinClass, targetClass, method);
                hasHooks = true;
            }
        }

        if (!hasHooks) {
            LauncherLogger.warn("Warning: mixin " + mixinClass.getSimpleName() + " does not define any hooks");
        }
    }

    private static void handleInjectMethod(Class<?> mixinClass, Class<?> targetClass, Method method) {
        Inject inject = method.getAnnotation(Inject.class);
        String methodName = inject.method();
        Class<?>[] args = inject.args();
        String descriptor;

        try {
            Method targetMethod = null;

            Method[] declaredMethods = targetClass.getDeclaredMethods();
            List<Method> overloads = new ArrayList<>();

            for (Method m : declaredMethods) {
                if (m.getName().equals(methodName)) {
                    overloads.add(m);
                    if (Arrays.equals(m.getParameterTypes(), args)) {
                        targetMethod = m;
                        break;
                    }
                }
            }

            if (targetMethod == null) {
                if (!overloads.isEmpty()) {
                    StringBuilder overloadSignatures = new StringBuilder();
                    for (Method m : overloads) {
                        overloadSignatures.append("\n    ").append(Arrays.toString(m.getParameterTypes()));
                    }
                    throw new RuntimeException("Method found with name but no matching arguments. Possible signatures:" +
                            overloadSignatures.toString());
                } else {
                    throw new RuntimeException("No method with name: " + methodName);
                }
            }

            descriptor = Type.getMethodDescriptor(targetMethod);

        } catch (Exception e) {
            String msg = "Failed to resolve descriptor for method: " + methodName + " in mixin " + mixinClass.getSimpleName() + " → " + e.getMessage() + " (hint: check @Inject argument types)";
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }


        String at = inject.at().value();
        String invokeTarget = inject.at().target();
        final String finalDescriptor = descriptor;

        mixins.add(() -> {
            try {
                LauncherLogger.info("Injecting into " + targetClass.getName() + "." + methodName + " at " + at);
                ElementMatcher.Junction<MethodDescription> matcher = methodName.equals("<init>")
                        ? ElementMatchers.isConstructor()
                        : ElementMatchers.named(methodName);
                matcher = matcher.and(ElementMatchers.hasDescriptor(finalDescriptor));

                String key = targetClass.getName() + "." + methodName + "@" + at;
                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new ArrayList<>()).add(new HookInfo(method, mixinClass.getSimpleName()));

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
                    default:
                        String msg = "Unsupported @At value: " + at + " in mixin " + mixinClass.getSimpleName() + ", method " + methodName;
                        LauncherLogger.warn(msg);
                        earlyFailures.add(msg);

                        return;
                }

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

                LauncherLogger.info("Successfully injected into: " + targetClass.getName());
            } catch (Exception e) {
                LauncherLogger.error("Injection failed for: " + targetClass.getName() + " in mixin " + mixinClass.getSimpleName() + " → " + e.getMessage());
            }
        });
    }

    private static void handleModifyArgMethod(Class<?> mixinClass, Class<?> targetClass, Method method) {
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
            String msg = "Failed to resolve descriptor for @ModifyArg method: " + methodName + " in mixin " + mixinClass.getSimpleName() + " → " + e.getMessage();
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        mixins.add(() -> {
            try {
                LauncherLogger.info("Modifying argument for: " + targetClass.getName() + "." + methodName);
                ElementMatcher.Junction<MethodDescription> matcher = ElementMatchers.named(methodName)
                        .and(ElementMatchers.hasDescriptor(descriptor));

                String key = targetClass.getName() + "." + methodName + "@MODIFY_ARG";
                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new ArrayList<>()).add(new HookInfo(method, mixinClass.getSimpleName()));

                DynamicType.Builder<?> builder = new ByteBuddy().redefine(targetClass)
                        .visit(Advice.to(ModifyArgDispatcher.class).on(matcher));

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

                LauncherLogger.info("Successfully injected ModifyArg into: " + targetClass.getName());
            } catch (Exception e) {
                LauncherLogger.error("ModifyArg injection failed for: " + targetClass.getName() + " in mixin " + mixinClass.getSimpleName() + " → " + e.getMessage());
            }
        });
    }

    private static void handleRedirectMethod(Class<?> mixinClass, Class<?> targetClass, Method method) {
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
            String msg = "Failed to resolve descriptor for @Redirect method: " + methodName + " → " + e.getMessage();
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        String key = targetClass.getName() + "." + methodName + "@REDIRECT";
        String target = redirect.at().target();
        if (target.isEmpty() || !target.contains("#")) {
            String msg = "Invalid @Redirect target: " + target + " in mixin " + mixinClass.getSimpleName() + ", method " + methodName + " → expected format: Class#method";
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        String[] parts = target.split("#");
        String methodSimple = parts[1];

        mixins.add(() -> {
            try {
                LauncherLogger.info("Redirecting call to: " + target);

                ElementMatcher.Junction<MethodDescription> matcher = ElementMatchers.named(methodName)
                        .and(ElementMatchers.hasDescriptor(descriptor));

                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new ArrayList<>()).add(new HookInfo(method, mixinClass.getSimpleName()));

                DynamicType.Builder<?> builder = new ByteBuddy()
                        .redefine(targetClass)
                        .visit(MemberSubstitution
                                .relaxed()
                                .method(ElementMatchers.named(methodSimple))
                                .replaceWith(method)
                                .on(matcher));

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

                LauncherLogger.info("Successfully applied @Redirect to: " + targetClass.getName());
            } catch (Exception e) {
                LauncherLogger.error("Redirect injection failed for: " + targetClass.getName() + " → " + e);
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
            List<HookInfo> hooks = methodRegistry.get(key);
            if (hooks != null) {
                for (HookInfo hookInfo : hooks) {
                    Method hook = hookInfo.method;
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
        List<HookInfo> hooks = methodRegistry.get(key);
        if (hooks != null) {
            for (HookInfo hookInfo : hooks) {
                Method hook = hookInfo.method;
                String mixinName = hookInfo.mixinClassName;

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

                try {
                    hook.invoke(null, invokeArgs.toArray());
                } catch (Exception e) {
                    Throwable cause = e instanceof InvocationTargetException
                            ? ((InvocationTargetException) e).getCause()
                            : e;

                    String errorDetail = cause.getClass().getSimpleName();
                    if (cause.getMessage() != null) {
                        errorDetail += ": " + cause.getMessage();
                    }

                    LauncherLogger.error("Error invoking hook in mixin " + mixinName + " for " + origin.getDeclaringClass().getSimpleName() + "." + origin.getName() + "@" + at + " → " + errorDetail);
                }

            }
        }
    }

    private static class HookInfo {
        final Method method;
        final String mixinClassName;

        HookInfo(Method method, String mixinClassName) {
            this.method = method;
            this.mixinClassName = mixinClassName;
        }
    }

    public static Object[] applyAll() {
        int applied = 0;
        List<String> failures = new ArrayList<>(earlyFailures);
        earlyFailures.clear();

        for (Runnable mixin : mixins) {
            try {
                mixin.run();
                applied++;
            } catch (Exception e) {
                failures.add("Runtime injection failed: " + e.getMessage());
            }
        }

        return new Object[] { applied, failures };
    }
}
