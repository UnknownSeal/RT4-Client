package com.unknownseal.mixinlauncher;

import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.mixin.MixinLoader;
import com.unknownseal.mixinlauncher.plugin.PluginManager;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;

import java.lang.reflect.Method;

public class Launcher
{
    public static void main(String[] args) throws Exception
    {
        long startTime = System.currentTimeMillis();

        LauncherLogger.info("Forcing load of GameShell for shadows…");
        Class.forName("com.jagex.runetek4.client.GameShell");


        LauncherLogger.info("Applying all hooks…");
        MixinLoader.applyAllHooks();

        EventBus eventBus = new EventBus();

        PluginManager pm = new PluginManager(eventBus);
        PluginManagerHolder.set(pm);
        pm.loadPlugins("com.unknownseal.mixinlauncher.plugins");

        long elapsed = System.currentTimeMillis() - startTime;
        LauncherLogger.info("All hooks applied and plugins loaded in " + elapsed + " ms");

        LauncherLogger.info("Launching client main()…");
        try {
            Class<?> clientClass = Class.forName("com.jagex.runetek4.client.Client");
            Method mainMethod = clientClass.getMethod("main", String[].class);
            mainMethod.invoke(null, (Object) args);
        } catch (Exception e) {
            LauncherLogger.error("Failed to launch client main method: " + e);
        }
    }
}
