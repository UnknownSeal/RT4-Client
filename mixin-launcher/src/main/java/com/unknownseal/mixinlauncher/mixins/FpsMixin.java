package com.unknownseal.mixinlauncher.mixins;

import com.unknownseal.mixinlauncher.MixinEngine.At;
import com.unknownseal.mixinlauncher.MixinEngine.Inject;
import com.unknownseal.mixinlauncher.MixinEngine.Mixin;
import com.unknownseal.mixinlauncher.MixinLogger;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

@Mixin(value = com.jagex.runetek4.client.GameShell.class)
public class FpsMixin {

    @Inject(
            method = "startApplication",
            args = { int.class, String.class },
            at = @At("TAIL")
    )
    public static void afterStartApplication(int cacheId, String cacheSubDir) {
        MixinLogger.LOGGER.info(">>> Tail of startApplication invoked");

        try {
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice[] devices = ge.getScreenDevices();

            int maxRefreshRate = 0;

            for (GraphicsDevice device : devices) {
                DisplayMode[] modes = device.getDisplayModes();
                for (DisplayMode mode : modes) {
                    if (mode.getRefreshRate() > maxRefreshRate) {
                        maxRefreshRate = mode.getRefreshRate();
                    }
                }
            }

            int refreshRate = maxRefreshRate > 0 ? maxRefreshRate : 60;
            MixinLogger.LOGGER.info("Detected max refresh rate across all monitors: " + refreshRate);

            Class<?> gameShell = Class.forName("com.jagex.runetek4.client.GameShell");
            gameShell.getMethod("setFpsTarget", int.class).invoke(null, refreshRate);

            MixinLogger.LOGGER.info("Set FPS to: " + refreshRate);

        } catch (Throwable t) {
            MixinLogger.LOGGER.severe("Failed to configure FPS: " + t);
        }
    }
}
