package com.unknownseal.mixinlauncher.mixins;


import com.jagex.runetek4.client.Game;
import com.jagex.runetek4.network.Protocol;
import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.events.ClientTick;
import com.unknownseal.mixinlauncher.events.GameTick;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

@Mixin({Protocol.class, Game.class})
public class GameTickMixin {

    @Inject(method = "updateGame", at = @At("HEAD"))
    public static void onUpdateGame() {
        PluginManagerHolder.get().getEventBus().post(new ClientTick());
    }

    @Inject(method = "readPlayerInfoPacket", at = @At("HEAD"))
    public static void onReadPlayerInfoPacket() {
        PluginManagerHolder.get().getEventBus().post(new GameTick());
    }
}
