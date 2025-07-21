package com.unknownseal.mixinlauncher.hooks;

import com.unknownseal.mixinlauncher.mixin.MixinEngine.At;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Shadow;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Inject;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Mixin;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;
import com.unknownseal.mixinlauncher.events.GameShellStarted;

import java.awt.*;

@Mixin(value = com.jagex.runetek4.client.GameShell.class)
public class GameShellMixin
{
    @Inject(
            method = "startApplication",
            args = { int.class, String.class },
            at = @At("TAIL")
    )
    public static void afterStartApplication(int cacheId, String cacheSubDir) {
        PluginManagerHolder.get().getEventBus().post(new GameShellStarted());
    }
}
