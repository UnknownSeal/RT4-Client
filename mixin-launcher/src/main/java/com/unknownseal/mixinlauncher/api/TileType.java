package com.unknownseal.mixinlauncher.api;

import com.unknownseal.mixinlauncher.api.rendering.TileUtils;

public enum TileType {
    PLAYER_TILE("Player Tile", TileUtils.COLOR_RED),

    HOVERED_TILE("Hovered Tile", TileUtils.COLOR_GREEN),

    MANUALLY_MARKED("Manually Marked", TileUtils.COLOR_BLUE);
    
    private final String displayName;
    private final int defaultColor;
    
    TileType(String displayName, int defaultColor) {
        this.displayName = displayName;
        this.defaultColor = defaultColor;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDefaultColor() {
        return defaultColor;
    }
}