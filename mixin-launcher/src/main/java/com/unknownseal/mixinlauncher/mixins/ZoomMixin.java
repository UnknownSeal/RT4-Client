package com.unknownseal.mixinlauncher.mixins;

import com.jagex.runetek4.Camera;
import com.jagex.runetek4.ClientScriptRunner;
import com.jagex.runetek4.JavaMouseWheel;
import com.jagex.runetek4.Keyboard;
import com.unknownseal.mixinlauncher.MixinEngine.*;

import java.awt.event.MouseWheelEvent;
import java.lang.reflect.Field;

@Mixin({ Camera.class, ClientScriptRunner.class, JavaMouseWheel.class })
public class ZoomMixin {

    public static int MIN_ZOOM = 200;
    public static int MAX_ZOOM = 4000;
    public static int DEFAULT_ZOOM = 600;

    public static final class ZoomState {
        public static int zoom = DEFAULT_ZOOM;
    }

    @Redirect(
            method = "drawScene",
            args = { int.class, boolean.class, int.class, int.class, int.class },
            at = @At(value = "INVOKE", target = "Camera#orbitCamera")
    )
    public static void redirectOrbitCamera(int cameraX, int arg0, int height, int modifiedDistance, int yaw, int cameraZ, int pitch) {
        Camera.orbitCamera(cameraX, arg0, height, ZoomState.zoom, yaw, cameraZ, pitch);
    }

    @Shadow
    private static Field currentRotation;

    @Inject(
            method = "mouseWheelMoved",
            args = { MouseWheelEvent.class },
            at = @At("TAIL")
    )
    public static synchronized void onMouseWheelMoved(MouseWheelEvent event, @This JavaMouseWheel self) {
        try {
            int previous = (int) currentRotation.get(self);
            int updated = previous + event.getWheelRotation();
            currentRotation.set(self, updated);
            int diff = updated - previous;

            if (isShiftPressed()) {
                ZoomState.zoom = clamp(MIN_ZOOM, MAX_ZOOM, ZoomState.zoom + (diff >= 0 ? 50 : -50));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isShiftPressed() {
        for (int keyCode : Keyboard.eventQueue) {
            if (keyCode == 81) {
                return true;
            }
            if (keyCode == ~(81 & 0xFFFFFF7F)) {
                return false;
            }
        }
        return false;
    }

    private static int clamp(int min, int max, int val) {
        return Math.max(min, Math.min(max, val));
    }

}
