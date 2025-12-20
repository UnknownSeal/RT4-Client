package com.jagex.runetek4.game.world;

public class CoordinateConstants {

    // Coordinate bit packing
    public static final int PLANE_SHIFT = 28;
    public static final int PLANE_MASK = 0x3;
    public static final int COORD_X_SHIFT = 14;
    public static final int COORD_MASK = 0x3FFF;
    public static final int ENTITY_ID_MASK = 0xFFFF;

    // Tile coordinates
    public static final int TILE_SIZE = 128;
    public static final int TILE_CENTER_OFFSET = 64;

    // Teleport flags
    public static final int LEVEL_SHIFT = 1;
    public static final int TELEPORT_FLAG_MASK = 0x1;

}
