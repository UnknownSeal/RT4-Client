package com.unknownseal.mixinlauncher.mixin.handlers;

import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import com.unknownseal.mixinlauncher.mixin.HookInfo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.asm.MemberSubstitution;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatcher;
import net.bytebuddy.matcher.ElementMatchers;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class InjectHandler {

    /**
     * Handles injecting the method annotated with @Inject into the target class.
     * Registers the injection task in the mixins runnable list and updates methodRegistry.
     *
     * @param mixinClass the class containing the @Inject method
     * @param targetClass the target class to inject into
     * @param method the @Inject annotated method to inject
     * @param mixins list to add injection runnables
     * @param methodRegistry shared hook registry map
     * @param earlyFailures list to track early failures for diagnostics
     */
    public static void handle(
            Class<?> mixinClass,
            Class<?> targetClass,
            Method method,
            List<Runnable> mixins,
            Map<String, List<HookInfo>> methodRegistry,
            List<String> earlyFailures
    ) {
        Inject inject = method.getAnnotation(Inject.class);
        String methodName = inject.method();
        Class<?>[] args = inject.args();
        String descriptor;

        try {
            Method targetMethod = null;
            List<Method> overloads = new ArrayList<>();

            for (Method m : targetClass.getDeclaredMethods()) {
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
                    throw new RuntimeException("Method found with name but no matching arguments. Possible signatures:" + overloadSignatures.toString());
                } else {
                    throw new RuntimeException("No method with name: " + methodName);
                }
            }

            descriptor = Type.getMethodDescriptor(targetMethod);
        } catch (Exception e) {
            String msg = "Failed to resolve descriptor for method: " + methodName + " in mixin " + mixinClass.getSimpleName() +
                    " → " + e.getMessage() + " (hint: check @Inject argument types)";
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        String at = inject.at().value();
        String invokeTarget = inject.at().target();
        final String finalDescriptor = descriptor;

        mixins.add(() -> {
            try {
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
                        builder = builder.visit(Advice.to(com.unknownseal.mixinlauncher.mixin.Dispatchers.TailDispatcher.class).on(matcher));
                        break;
                    case "HEAD":
                        builder = builder.visit(Advice.to(com.unknownseal.mixinlauncher.mixin.Dispatchers.HeadDispatcher.class).on(matcher));
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
                            builder = builder.visit(Advice.to(com.unknownseal.mixinlauncher.mixin.Dispatchers.InvokeDispatcher.class).on(matcher));
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
            } catch (Exception e) {
                LauncherLogger.error("Injection failed for: " + targetClass.getName() + " in mixin " + mixinClass.getSimpleName() + " → " + e.getMessage());
            }
        });
    }
}
