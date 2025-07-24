package com.unknownseal.mixinlauncher.mixin.handlers;

import com.unknownseal.mixinlauncher.annotations.Redirect;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import com.unknownseal.mixinlauncher.mixin.HookInfo;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.MemberSubstitution;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.matcher.ElementMatcher;

import org.objectweb.asm.Type;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class RedirectHandler {

    /**
     * Handles injecting methods annotated with @Redirect into the target class.
     * Adds the injection runnable and updates method registry.
     *
     * @param mixinClass the class containing the @Redirect method
     * @param targetClass the target class to inject into
     * @param method the @Redirect method
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
        Redirect redirect = method.getAnnotation(Redirect.class);
        String methodName = redirect.method();
        Class<?>[] args = redirect.args();
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
            String msg = "Failed to resolve descriptor for @Redirect method: " + methodName + " → " + e.getMessage();
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        String key = targetClass.getName() + "." + methodName + "@REDIRECT";
        String target = redirect.at().target();

        if (target.isEmpty() || !target.contains("#")) {
            String msg = "Invalid @Redirect target: " + target + " in mixin " + mixinClass.getSimpleName() + ", method " + methodName +
                    " → expected format: Class#method";
            LauncherLogger.warn(msg);
            earlyFailures.add(msg);
            return;
        }

        String[] parts = target.split("#");
        String methodSimple = parts[1];

        mixins.add(() -> {
            try {
                ElementMatcher.Junction<MethodDescription> matcher = ElementMatchers.named(methodName)
                        .and(ElementMatchers.hasDescriptor(descriptor));

                method.setAccessible(true);
                methodRegistry.computeIfAbsent(key, k -> new java.util.ArrayList<>()).add(new HookInfo(method, mixinClass.getSimpleName()));

                DynamicType.Builder<?> builder = new ByteBuddy()
                        .redefine(targetClass)
                        .visit(MemberSubstitution
                                .relaxed()
                                .method(ElementMatchers.named(methodSimple))
                                .replaceWith(method)
                                .on(matcher));

                builder.make()
                        .load(targetClass.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
            } catch (Exception e) {
                LauncherLogger.error("Redirect injection failed for: " + targetClass.getName() + " → " + e);
            }
        });
    }
}
