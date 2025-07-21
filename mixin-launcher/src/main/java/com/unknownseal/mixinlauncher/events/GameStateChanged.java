package com.unknownseal.mixinlauncher.events;

public class GameStateChanged
{
    private final int gameState;

    public GameStateChanged(int gameState) {
        this.gameState = gameState;
    }

    public int getGameState() {
        return gameState;
    }
}
