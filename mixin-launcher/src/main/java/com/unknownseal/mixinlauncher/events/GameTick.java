package com.unknownseal.mixinlauncher.events;

public class GameTick {

    private final long tick;

    public GameTick(long tick) {
        this.tick = tick;
    }

    public long getTick() {
        return tick;
    }

}
