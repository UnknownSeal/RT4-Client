package com.unknownseal.mixinlauncher.plugins;

import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.events.GameShellStarted;
import com.unknownseal.mixinlauncher.plugin.Plugin;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ZoomPlugin implements Plugin {

    private static final int MIN_ZOOM = 200;
    private static final int MAX_ZOOM = 4000;
    private static final int ZOOM_STEP = 50;

    private volatile boolean shiftPressed = false;
    private ClientContext clientContext;

    @Override
    public String getName() {
        return "Zoom Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        this.clientContext = ctx;
        bus.register(this);
    }

    @Subscribe
    public void onGameShellStarted(GameShellStarted event) {
        clientContext.addMouseWheelListener(e -> {
            if (!shiftPressed) return;

            int currentZoom = clientContext.getCameraZoom();
            int newZoom = clamp(currentZoom + (e.getWheelRotation() > 0 ? ZOOM_STEP : -ZOOM_STEP), MIN_ZOOM, MAX_ZOOM);

            if (newZoom != currentZoom) {
                clientContext.setCameraZoom(newZoom);
            }
        });

        clientContext.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftPressed = true;
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SHIFT) shiftPressed = false;
            }
        });
    }

    @Override
    public void onStop() {

    }

    private int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }
}
