package com.unknownseal.mixinlauncher.mixins;

import com.jagex.runetek4.scene.SceneGraph;
import com.unknownseal.mixinlauncher.annotations.At;
import com.unknownseal.mixinlauncher.annotations.Inject;
import com.unknownseal.mixinlauncher.annotations.Mixin;
import com.unknownseal.mixinlauncher.events.DrawScene;
import com.unknownseal.mixinlauncher.plugin.PluginManagerHolder;

import java.awt.event.MouseWheelEvent;

@Mixin(SceneGraph.class)
public class SceneGraphMixin {

    @Inject(method = "drawScene", args = { int.class, boolean.class, int.class, int.class, int.class }, at = @At("TAIL"))
    public static void drawScene(int height, boolean skipEntityUpdates, int x, int width, int y) {
        PluginManagerHolder.get().getEventBus().post(new DrawScene());
    }

}

