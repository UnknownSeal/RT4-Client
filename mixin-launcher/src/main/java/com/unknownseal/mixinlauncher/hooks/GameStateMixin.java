package com.unknownseal.mixinlauncher.hooks;

import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;
import com.unknownseal.mixinlauncher.events.GameStateChanged;

@Mixin(com.jagex.runetek4.client.client.class)
public class GameStateMixin
{
    private static int lastGameState = -1;

    @Inject(method = "mainloop", at = @At("TAIL"))
    public static void onUpdateGame() {
        int currentState = com.jagex.runetek4.client.client.gameState;

        if (currentState != lastGameState) {
            PluginManagerHolder.get()
                    .getEventBus()
                    .post(new GameStateChanged(currentState));
            lastGameState = currentState;
        }
    }
}
