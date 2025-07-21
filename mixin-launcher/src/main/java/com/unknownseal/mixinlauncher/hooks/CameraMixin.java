package com.unknownseal.mixinlauncher.hooks;

import com.jagex.runetek4.Camera;
import com.jagex.runetek4.ClientScriptRunner;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.At;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Redirect;
import com.unknownseal.mixinlauncher.mixin.MixinEngine.Mixin;


@Mixin(ClientScriptRunner.class)
public class CameraMixin {

    private static int zoom = 600;

    public static int getZoom() {
        return zoom;
    }

    public static void setZoom(int value) {
        zoom = value;
    }

    @Redirect(
            method = "drawScene",
            args = { int.class, boolean.class, int.class, int.class, int.class },
            at = @At(value = "INVOKE", target = "Camera#orbitCamera")
    )
    public static void redirectOrbitCamera(int cameraX, int arg0, int height, int originalDistance, int yaw, int cameraZ, int pitch) {
        Camera.orbitCamera(cameraX, arg0, height, zoom, yaw, cameraZ, pitch);
    }
}
