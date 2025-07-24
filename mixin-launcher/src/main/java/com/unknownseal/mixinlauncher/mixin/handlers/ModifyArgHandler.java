package com.unknownseal.mixinlauncher.mixin.handlers;

import com.unknownseal.mixinlauncher.annotations.ModifyArg;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import com.unknownseal.mixinlauncher.mixin.HookInfo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.ElementMatcher;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ModifyArgHandler {

    /**
     * Handles injecting methods annotated with @ModifyArg into the target class.
     * Adds the injection runnable and updates method registry.
     *
     * @param mixinClass the class containing the @ModifyArg method
     * @param targetClass the target class to inject into
     * @param method the @ModifyArg method
     * @param mixins list to add injection runnables
     * @param methodRegistry shared hook registry map
     * @param earlyFailures list to track early failures
     */
    public static void handle(
            Class<?> mixinClass,
            Class<?> targetClass,
            Method method,
            List<Runnable> mixins,
            Map<String, List<HookInfo>> methodRegistry,
            List<String> earlyFailures
    ) {
        ModifyArg modifyArg = method.getAnnotation(ModifyArg.class);
        String methodName = modifyArg.method();
        Class<?>[] args = modifyArg.args();
        String descriptor;

        try {
            Method targetMethod = null;
            for (Method m : targetClass.getDeclaredMethods()) {
                if (m.getName().equals(methodName) && java.util.Arrays.equals(m.getParameterTypes(), args)) {
                    targetMethod = m;
                    break;
                }
            }
            if (targetMethod == null) {
                throw new RuntimeException("Method not found: " + methodName);
            }
            descriptor = Type.getMethodDescriptor(targetMethod);
        } catch (Exception e) {
            String msg = "Failed to resolve descriptor for @ModifyArg method: " + methodName + " in mixin " + mixinClass.getSimpleName() +
                    " → " + e.getMessage();
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        mixins.add(() -> {
            try {
                ElementMatcher.Junction<MethodDescription> matcher = ElementMatchers.named(methodName)
                        .and(ElementMatchers.hasDescriptor(descriptor));

                String key = targetClass.getName() + "." + methodName + "@MODIFY_ARG";
                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(new HookInfo(method, mixinClass.getSimpleName()));

                DynamicType.Builder<?> builder = new ByteBuddy().redefine(targetClass)
                        .visit(Advice.to(com.unknownseal.mixinlauncher.mixin.Dispatchers.ModifyArgDispatcher.class).on(matcher));

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
            } catch (Exception e) {
                LauncherLogger.error("ModifyArg injection failed for: " + targetClass.getName() + " in mixin " + mixinClass.getSimpleName() +
                        " → " + e.getMessage());
            }
        });
    }
}
