package com.unknownseal.mixinlauncher.plugins;

import com.jagex.runetek4.Keyboard;
import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.events.MouseWheelMoved;
import com.unknownseal.mixinlauncher.plugin.Plugin;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;

public class ZoomPlugin implements Plugin {

    public static final int MIN_ZOOM = 200;
    public static final int MAX_ZOOM = 4000;
    public static final int DEFAULT_ZOOM = 600;

    private static volatile int zoom = DEFAULT_ZOOM;

    private ClientContext clientContext;

    @Override
    public String getName() {
        return "Zoom Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        this.clientContext = ctx;
        bus.register(this);;
    }

    @Subscribe
    public void onMouseWheelMoved(MouseWheelMoved event) {
        if (!isShiftPressed()) {
            return;
        }

        int rotation = event.getEvent().getWheelRotation();

        int currentZoom = clientContext.getCameraZoom();
        int newZoom = currentZoom + (rotation > 0 ? 50 : -50);
        newZoom = clamp(newZoom, MIN_ZOOM, MAX_ZOOM);

        if (newZoom != currentZoom) {
            clientContext.setCameraZoom(newZoom);
            LauncherLogger.info("Zoom changed to: " + newZoom);
        }
    }

    @Override
    public void onStop() {
    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
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
}
