package com.unknownseal.mixinlauncher.mixins;

import com.unknownseal.mixinlauncher.MixinEngine.Inject;
import com.unknownseal.mixinlauncher.MixinEngine.At;
import com.unknownseal.mixinlauncher.MixinEngine.Mixin;
import com.unknownseal.mixinlauncher.MixinLogger;

@Mixin(com.jagex.runetek4.client.GameShell.class)
public class TitleMixin {

    @Inject(method = "startApplication", args = { int.class, String.class }, at = @At("TAIL"))
    private static void afterStart() {
        try {
            com.jagex.runetek4.client.GameShell.frame.setTitle("Runescape 530 - Mixin Edition");
            MixinLogger.LOGGER.info("Replaced window title");
        } catch (Exception e) {
            MixinLogger.LOGGER.severe("Error replacing window title: " + e);
        }
    }
}
