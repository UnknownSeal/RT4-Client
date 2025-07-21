package com.unknownseal.mixinlauncher.plugins;

import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.events.GameShellStarted;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.plugin.Plugin;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;

import java.awt.Frame;

public class WindowSizePlugin implements Plugin
{
    private ClientContext client;

    @Override
    public String getName() {
        return "Window Size Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        this.client = ctx;
        bus.register(this);
    }

    @Override
    public void onStop() {

    }

    @Subscribe
    public void onGameShellStarted(GameShellStarted event) {
        Frame frame = client.getFrame();
        if (frame == null) {
            return;
        }

        try {
            frame.setSize(1200, 900);
        } catch (Exception e) {
            LauncherLogger.error("Error setting window size: " + e);
        }
    }
}
