package com.unknownseal.mixinlauncher.api;

import com.unknownseal.mixinlauncher.hooks.CameraMixin;
import com.unknownseal.mixinlauncher.utils.StringUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelListener;

import static com.jagex.runetek4.client.GameShell.canvas;
import static com.jagex.runetek4.client.GameShell.frame;
import static com.jagex.runetek4.client.client.gameState;

public class ClientContextImpl implements ClientContext{

    @Override
    public Frame getFrame() {
        return frame;
    }

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public int getGameState() {
        return gameState;
    }
    @Override
    public String getLocalPlayerName() {
        com.jagex.runetek4.Player player = com.jagex.runetek4.PlayerList.self;
        if (player != null && player.username != null) {
            return StringUtils.fromJstring(player.username);
        }
        return null;
    }


    @Override
    public int getCameraZoom() {
        return CameraMixin.getZoom();
    }

    @Override
    public void addMouseListener(MouseAdapter listener) {
        canvas.addMouseListener(listener);
    }

    @Override
    public void addMouseWheelListener(MouseWheelListener listener) {
        canvas.addMouseWheelListener(listener);
    }

    @Override
    public void addKeyListener(KeyAdapter listener) {
        canvas.addKeyListener(listener);
    }


    @Override
    public void setCameraZoom(int zoom) {
        CameraMixin.setZoom(zoom);
    }

}
