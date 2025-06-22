package com.unknownseal.mixinlauncher;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.instrument.Instrumentation;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class MixinLoader {

    public static void applyAllMixins() throws Exception {
        MixinLogger.LOGGER.info("Installing ByteBuddy agent...");
        Instrumentation instrumentation = ByteBuddyAgent.install();

        // Scan and register mixins first!
        MixinLogger.LOGGER.info("Scanning and registering mixins...");
        try (ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .acceptPackages("com.unknownseal.mixinlauncher.mixins")
                .scan()) {

            MixinEngine.scanAndRegisterAnnotatedMixins("com.unknownseal.mixinlauncher.mixins");

        } catch (Exception scanException) {
            MixinLogger.LOGGER.log(Level.SEVERE, "Failed to scan and register mixins", scanException);
        }

        // Then load classes (optional if just scanning is enough)
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages("com.unknownseal.mixinlauncher.mixins")
                .scan()) {

            scanResult.getAllClasses()
                    .loadClasses()
                    .forEach(cls -> {
                        MixinLogger.LOGGER.info("Forcing load of: " + cls.getName());
                        try {
                            Class.forName(cls.getName(), true, cls.getClassLoader());
                        } catch (Exception e) {
                            MixinLogger.LOGGER.log(Level.SEVERE, "Failed to load class: " + cls.getName(), e);
                        }
                    });

        } catch (Exception scanException) {
            MixinLogger.LOGGER.log(Level.SEVERE, "Error scanning for mixins", scanException);
        }

        MixinLogger.LOGGER.info("Applying manual mixins...");
        MixinEngine.applyAll();

        MixinLogger.LOGGER.info("Checking loaded classes...");
        for (Class<?> loaded : instrumentation.getAllLoadedClasses()) {
            if (loaded.getName().startsWith("com.jagex.runetek4.client")) {
                MixinLogger.LOGGER.info("Target class already loaded: " + loaded.getName());
            }
        }
    }

    public static void main(String[] args) throws Exception {
        applyAllMixins();

        MixinLogger.LOGGER.info("Attempting to launch client.main()...");
        try {
            Class<?> clientClass = Class.forName("com.jagex.runetek4.client.client");
            Method mainMethod = clientClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            MixinLogger.LOGGER.log(Level.SEVERE, "Failed to launch client main method", e);
        }
    }
}
