package com.unknownseal.mixinlauncher.mixins;


import com.jagex.runetek4.input.JavaMouseWheel;
import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.annotations.This;
import com.unknownseal.mixinlauncher.events.MouseWheelMoved;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

import java.awt.event.MouseWheelEvent;

@Mixin(JavaMouseWheel.class)
public class JavaMouseWheelMixin {

    @Inject(method = "mouseWheelMoved", args = { MouseWheelEvent.class }, at = @At("TAIL"))
    public static synchronized void onMouseWheelMoved(MouseWheelEvent event, @This JavaMouseWheel self) {
        PluginManagerHolder.get().getEventBus().post(new MouseWheelMoved(event));
    }
}
