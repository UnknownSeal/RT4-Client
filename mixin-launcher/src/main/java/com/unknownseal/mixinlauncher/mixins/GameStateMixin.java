package com.unknownseal.mixinlauncher.mixins;

import com.jagex.runetek4.client.Client;
import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;
import com.unknownseal.mixinlauncher.events.GameStateChanged;

@Mixin(Client.class)
public class GameStateMixin
{
    private static int lastGameState = -1;

    @Inject(method = "mainloop", at = @At("TAIL"))
    public static void onUpdateGame() {
        int currentState = Client.gameState;

        if (currentState != lastGameState) {
            PluginManagerHolder.get()
                    .getEventBus()
                    .post(new GameStateChanged(currentState));
            lastGameState = currentState;
        }
    }
}
