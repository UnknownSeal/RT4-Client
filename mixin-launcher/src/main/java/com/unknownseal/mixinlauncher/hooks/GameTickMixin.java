package com.unknownseal.mixinlauncher.hooks;

import com.unknownseal.mixinlauncher.mixin.MixinEngine;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Mixin;
import com.unknownseal.mixinlauncher.events.GameTick;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

@Mixin(com.jagex.runetek4.client.Game.class)
public class GameTickMixin {

    private static long tickCounter = 0;

    @MixinEngine.Inject(
            method = "updateGame",
            at = @MixinEngine.At("TAIL")
    )
    public static void afterTick() {
        tickCounter++;
        PluginManagerHolder.get().getEventBus().post(new GameTick(tickCounter));
    }
}
