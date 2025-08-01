package com.unknownseal.mixinlauncher.mixins;

import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.events.MouseClicked;
import com.jagex.runetek4.input.Mouse;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

import java.awt.event.MouseEvent;

@Mixin(Mouse.class)
public class MouseMixin {

    @Inject(method = "mousePressed", args = { MouseEvent.class }, at = @At("HEAD"))
    public static void onMousePressed(MouseEvent event) {
        try {
            MouseClicked mouseClickedEvent = new MouseClicked(event);

            if (PluginManagerHolder.get() != null) {
                PluginManagerHolder.get().getEventBus().post(mouseClickedEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}