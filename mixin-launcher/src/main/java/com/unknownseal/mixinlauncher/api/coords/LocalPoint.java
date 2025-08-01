package com.unknownseal.mixinlauncher.api.coords;

/**
 * Represents a point in local coordinates within the game world.
 */
public class LocalPoint {

    private final int x;
    private final int y;

    public LocalPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSceneX() {
        return x >>> 7;
    }

    public int getSceneY() {
        return y >>> 7;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        LocalPoint that = (LocalPoint) obj;
        return x == that.x && y == that.y;
    }
}