package com.unknownseal.mixinlauncher.api;

import java.awt.*;

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
     * Returns the current game state.
     */
    int getGameState();

    /**
     * Returns the local player’s name, or null if not logged in.
     */
    String getLocalPlayerName();

    /**
     * Get the current camera zoom distance.
     */
    int getCameraZoom();

    /**
     * Set the desired camera zoom distance.
     */
    void setCameraZoom(int zoom);

}
