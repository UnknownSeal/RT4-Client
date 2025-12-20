package com.jagex.runetek4.game.world;

public class CoordinateConstants {

    // Coordinate bit packing
    public static final int PLANE_SHIFT = 28;
    public static final int PLANE_MASK = 0x3;
    public static final int COORD_X_SHIFT = 14;
    public static final int COORD_MASK = 0x3FFF;
    public static final int ENTITY_ID_MASK = 0xFFFF;

    // Coordinate hashing
    public static final int COORD_HASH_SHIFT = 7;
    public static final long COORD_HASH_OFFSET = 1610612736L;

    // Tile coordinates
    public static final int TILE_SIZE = 128;
    public static final int HALF_TILE_SIZE = 64;
    public static final int TILE_CENTER_OFFSET = 64;
    public static final int BUILD_AREA_HALF_TILES = 208;

    // Teleport flags
    public static final int LEVEL_SHIFT = 1;
    public static final int TELEPORT_FLAG_MASK = 0x1;

    // Rotation/Orientation
    public static final int ROTATION_MASK = 0x3;

    // Location packing
    public static final int LOC_PARAM_SHIFT = 2;

    // Zone coordinate packing
    public static final int ZONE_COORD_SHIFT = 4;
    public static final int ZONE_COORD_MASK = 0x7;
    public static final int ZONE_COORD_MASK_4BIT = 0xF;

    // Height/coordinate scales
    public static final int HEIGHT_SCALE_FACTOR = 4;
    public static final int FIXED_POINT_SHIFT = 16;


}
