package com.unknownseal.mixinlauncher.hooks;

import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.mixin.MixinEngine;
import com.unknownseal.mixinlauncher.events.GameTick;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

@Mixin(com.jagex.runetek4.client.Game.class)
public class GameTickMixin {

    private static long tickCounter = 0;

    @Inject(
            method = "updateGame",
            at = @At("TAIL")
    )
    public static void afterTick() {
        tickCounter++;
        PluginManagerHolder.get().getEventBus().post(new GameTick(tickCounter));
    }
}
