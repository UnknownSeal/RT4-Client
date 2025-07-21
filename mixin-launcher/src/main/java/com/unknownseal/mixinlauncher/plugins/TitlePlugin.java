package com.unknownseal.mixinlauncher.plugins;

import com.unknownseal.mixinlauncher.events.GameStateChanged;
import com.unknownseal.mixinlauncher.eventbus.EventBus;
import com.unknownseal.mixinlauncher.eventbus.Subscribe;
import com.unknownseal.mixinlauncher.plugin.Plugin;
import com.unknownseal.mixinlauncher.api.ClientContext;

public class TitlePlugin implements Plugin
{
    private ClientContext client;

    @Override
    public String getName() {
        return "Title Plugin";
    }

    @Override
    public void onStart(EventBus bus, ClientContext ctx) {
        this.client = ctx;
        bus.register(this);
    }

    @Override
    public void onStop() {
        if (client != null && client.getFrame() != null) {
            client.getFrame().setTitle("RuneScape 530");
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {
        if (client.getFrame() == null) {
            return;
        }

        if (event.getGameState() == 30) {
            String name = client.getLocalPlayerName();
            if (name != null) {
                client.getFrame().setTitle("RuneScape 530 - " + name);
            } else {
                client.getFrame().setTitle("RuneScape 530 - (Unknown)");
            }
        }
        else if (event.getGameState() == 10) {
            client.getFrame().setTitle("RuneScape 530 - Login");
        }
        else {
            client.getFrame().setTitle("RuneScape 530");
        }
    }
}
