package com.unknownseal.mixinlauncher.api;

import com.unknownseal.mixinlauncher.api.coords.LocalPoint;
import java.util.Objects;

public class MarkedTile {
    private final LocalPoint location;
    private final TileType type;
    private final int customColor;
    private final boolean useCustomColor;


    public MarkedTile(int tileX, int tileZ, TileType type, int customColor) {
        this.location = new LocalPoint(tileX * 128 + 64, tileZ * 128 + 64);
        this.type = type;
        this.customColor = customColor;
        this.useCustomColor = true;
    }

    public LocalPoint getLocation() {
        return location;
    }

    public int getColor() {
        return customColor;
    }

    public int getTileX() {
        return location.getSceneX();
    }

    public int getTileZ() {
        return location.getSceneY();
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MarkedTile that = (MarkedTile) obj;
        return Objects.equals(location, that.location) && type == that.type;
    }
}