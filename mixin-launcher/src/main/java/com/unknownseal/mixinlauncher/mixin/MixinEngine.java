package com.unknownseal.mixinlauncher.mixin;

import com.unknownseal.mixinlauncher.annotations.*;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import com.unknownseal.mixinlauncher.mixin.handlers.FieldHookHandler;
import com.unknownseal.mixinlauncher.mixin.handlers.InjectHandler;
import com.unknownseal.mixinlauncher.mixin.handlers.ModifyArgHandler;
import com.unknownseal.mixinlauncher.mixin.handlers.RedirectHandler;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class MixinEngine {

    private static final List<String> earlyFailures = new ArrayList<>();
    private static final List<Runnable> mixins = new ArrayList<>();
    public static final Map<String, List<HookInfo>> methodRegistry = new HashMap<>();

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
                InjectHandler.handle(mixinClass, targetClass, method, mixins, methodRegistry, earlyFailures);
                hasHooks = true;
            } else if (method.isAnnotationPresent(ModifyArg.class)) {
                ModifyArgHandler.handle(mixinClass, targetClass, method, mixins, methodRegistry, earlyFailures);
                hasHooks = true;
            } else if (method.isAnnotationPresent(Redirect.class)) {
                RedirectHandler.handle(mixinClass, targetClass, method, mixins, methodRegistry, earlyFailures);
                hasHooks = true;
            } else if (method.isAnnotationPresent(FieldHook.class)) {
                FieldHookHandler.handle(mixinClass, targetClass, method, mixins, methodRegistry, earlyFailures);
                hasHooks = true;
            }
        }

        if (!hasHooks) {
            LauncherLogger.warn("Warning: mixin " + mixinClass.getSimpleName() + " does not define any hooks");
        }
    }

    public static void dispatch(Method origin, Object[] args, String at, Object self) throws Exception {
        Dispatchers.dispatch(origin, args, at, self, methodRegistry);
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
                LauncherLogger.error("Runtime injection failed: " + e);
            }
        }

        return new Object[]{applied, failures};
    }
}
