package com.unknownseal.mixinlauncher.mixin;

import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import net.bytebuddy.agent.ByteBuddyAgent;

import java.lang.instrument.Instrumentation;
import java.util.List;
import java.util.logging.Level;

public class MixinLoader
{
    private static final String HOOKS_PACKAGE = "com.unknownseal.mixinlauncher.hooks";

    public static void applyAllHooks() throws Exception
    {
        LauncherLogger.info("Installing ByteBuddy agent…");
        Instrumentation instrumentation = ByteBuddyAgent.install();

        LauncherLogger.info("Scanning and registering hooks…");
        try (ScanResult scanResult = new ClassGraph()
                .enableClassInfo()
                .enableAnnotationInfo()
                .acceptPackages(HOOKS_PACKAGE)
                .scan())
        {
            MixinEngine.scanAndRegisterAnnotatedMixins(HOOKS_PACKAGE);
        }
        catch (Exception scanException)
        {
            LauncherLogger.error("Failed to scan and register hooks: " + scanException);
        }

        LauncherLogger.info("Forcing hook classes to load…");
        try (ScanResult scanResult = new ClassGraph()
                .enableAllInfo()
                .acceptPackages(HOOKS_PACKAGE)
                .scan())
        {
            scanResult.getAllClasses()
                    .loadClasses()
                    .forEach(cls -> {
                        LauncherLogger.info("Forcing load of hook: " + cls.getName());
                        try {
                            Class.forName(cls.getName(), true, cls.getClassLoader());
                        } catch (Exception e) {
                            LauncherLogger.error("Failed to load hook class: " + cls.getName() + " → " + e);
                        }
                    });
        }
        catch (Exception scanException)
        {
            LauncherLogger.error("Error scanning for hooks: " + scanException);
        }

        LauncherLogger.info("Applying all registered hooks...");
        Object[] result = MixinEngine.applyAll();
        int applied = (int) result[0];
        List<String> failures = (List<String>) result[1];

        LauncherLogger.info("Hooks applied successfully: " + applied);

        if (!failures.isEmpty()) {
            LauncherLogger.warn("Hooks failed: " + failures.size());
            for (String failure : failures) {
                LauncherLogger.warn("  - " + failure);
            }
        } else {
            LauncherLogger.info("No hook failures.");
        }

        LauncherLogger.info("Checking loaded target classes…");
        for (Class<?> loaded : instrumentation.getAllLoadedClasses())
        {
            if (loaded.getName().startsWith("com.jagex.runetek4.client"))
            {
                LauncherLogger.info("Target class already loaded: " + loaded.getName());
            }
        }
    }
}
