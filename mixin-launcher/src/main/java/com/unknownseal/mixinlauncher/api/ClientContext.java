package com.unknownseal.mixinlauncher.api;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelListener;

/**
 * Represents the client state and window.
 * Used by plugins to interact with the game.
 */
public interface ClientContext {

    /**
     * Returns the main game window frame.
     */
    Frame getFrame();

    /**
     * Returns the main game canvas where the game renders.
     */
    Canvas getCanvas();

    /**
     * Returns the current game state.
     */
    int getGameState();

    /**
     * Returns the local player’s name, or null if not logged in.
     */
    String getLocalPlayerName();

    /**
     * Add a mouse listener to the client canvas.
     */
    void addMouseListener(MouseAdapter listener);

    /**
     * Add a mouse wheel listener to the client canvas.
     */
    void addMouseWheelListener(MouseWheelListener listener);

    /**
     * Add a keyboard listener to the client canvas.
     */
    void addKeyListener(KeyAdapter listener);

    /**
     * Get the current camera zoom distance.
     */
    int getCameraZoom();

    /**
     * Set the desired camera zoom distance.
     */
    void setCameraZoom(int zoom);

}
