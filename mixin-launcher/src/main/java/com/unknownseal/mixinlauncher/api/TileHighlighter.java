package com.unknownseal.mixinlauncher.api;

import com.unknownseal.mixinlauncher.api.rendering.TileRenderer;
import com.unknownseal.mixinlauncher.api.rendering.TileUtils;
import com.jagex.runetek4.entity.entity.Player;

/**
 * Simple interface for plugins to highlight tiles
 */
public class TileHighlighter {

    public static void highlightTile(int plane, int tileX, int tileZ, int color) {
        TileRenderer.highlightTile(plane, tileX, tileZ, color);
    }

}