package com.unknownseal.mixinlauncher.hooks;

import com.jagex.runetek4.JavaMouseWheel;
import com.unknownseal.mixinlauncher.events.MouseWheelMoved;
import com.unknownseal.mixinlauncher.mixin.MixinEngine;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Mixin;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Inject;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.At;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.This;
import com.unknownseal.mixinlauncher.oldmixins.ZoomMixin;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

import java.awt.event.MouseWheelEvent;

@Mixin(com.jagex.runetek4.JavaMouseWheel.class)
public class JavaMouseWheelMixin {

    @Inject(method = "mouseWheelMoved", args = { MouseWheelEvent.class }, at = @At("TAIL"))
    public static synchronized void onMouseWheelMoved(MouseWheelEvent event, @This JavaMouseWheel self) {
        PluginManagerHolder.get().getEventBus().post(new MouseWheelMoved(event));
    }
}
