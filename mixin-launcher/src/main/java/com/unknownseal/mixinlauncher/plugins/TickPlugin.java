package com.unknownseal.mixinlauncher.plugins;

import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.events.GameTick;
import com.unknownseal.mixinlauncher.utils.LauncherLogger;
import com.unknownseal.mixinlauncher.api.ClientContext;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.plugin.Plugin;

public class TickPlugin implements Plugin {

    @Override
    public String getName() {
        return "Tick Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        LauncherLogger.info(getName() + " started.");
        bus.register(this);
    }

    @Override
    public void onStop() {
        LauncherLogger.info(getName() + " stopped.");
    }

    private long lastTickTime = -1;

    @Subscribe
    public void onGameTick(GameTick event) {
        long now = System.currentTimeMillis();

        if (lastTickTime != -1)
        {
            long delta = now - lastTickTime;
            LauncherLogger.info("Time between Game ticks: %d ms" , delta);
        }

        lastTickTime = now;
    }
}
