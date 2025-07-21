package com.unknownseal.mixinlauncher.plugins;

import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.events.GameShellStarted;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.plugin.Plugin;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class FpsPlugin implements Plugin
{
    @Override
    public String getName() {
        return "FPS Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        bus.register(this);
    }

    @Subscribe
    public void onGameShellStarted(GameShellStarted event) {
        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = ge.getScreenDevices();

            int maxRefreshRate = 0;

            for (GraphicsDevice device : devices) {
                DisplayMode[] modes = device.getDisplayModes();
                for (DisplayMode mode : modes) {
                    if (mode.getRefreshRate() > maxRefreshRate) {
                       // maxRefreshRate = mode.getRefreshRate();
                    }
                }
            }

            int refreshRate = maxRefreshRate > 0 ? maxRefreshRate : 60;
            LauncherLogger.info("Detected max refresh rate across all monitors: " + refreshRate);

            Class<?> gameShell = Class.forName("com.jagex.runetek4.client.GameShell");
            gameShell.getMethod("setFpsTarget", int.class).invoke(null, refreshRate);

            LauncherLogger.info("Set FPS target to: " + refreshRate);

        } catch (Throwable t) {
            LauncherLogger.error("Failed to configure FPS: " + t);
        }
    }
}
