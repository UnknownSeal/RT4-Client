package com.jagex.game.collision;

public final class CollisionFlag {

    public static final int WALL_NORTH_WEST = 0x1;

    public static final int WALL_NORTH = 0x2;

    public static final int WALL_NORTH_EAST = 0x4;

    public static final int WALL_EAST = 0x8;

    public static final int WALL_SOUTH_EAST = 0x10;

    public static final int WALL_SOUTH = 0x20;

    public static final int WALL_SOUTH_WEST = 0x40;

    public static final int WALL_WEST = 0x80;

    public static final int LOCATION = 0x100;

    public static final int WALL_NORTH_WEST_BLOCKRANGE = 0x200;

    public static final int WALL_NORTH_BLOCKRANGE = 0x400;

    public static final int WALL_NORTH_EAST_BLOCKRANGE = 0x800;

    public static final int WALL_EAST_BLOCKRANGE = 0x1000;

    public static final int WALL_SOUTH_EAST_BLOCKRANGE = 0x2000;

    public static final int WALL_SOUTH_BLOCKRANGE = 0x4000;

    public static final int WALL_SOUTH_WEST_BLOCKRANGE = 0x8000;

    public static final int WALL_WEST_BLOCKRANGE = 0x10000;

    public static final int LOCATION_BLOCKRANGE = 0x20100;

    public static final int GROUND_DECOR = 0x40000;

    public static final int UNKNOWN = 0x1080000;

    public static final int BLOCKWALK = 0x200000;

    public static final int WALL_NORTH_AND_WEST = WALL_NORTH | WALL_WEST;

    public static final int WALL_NORTH_AND_EAST = WALL_NORTH | WALL_EAST;

    public static final int WALL_SOUTH_AND_EAST = WALL_SOUTH | WALL_EAST;

    public static final int WALL_SOUTH_AND_WEST = WALL_SOUTH | WALL_WEST;

    public static final int WALL_NORTH_AND_WEST_BLOCKRANGE = WALL_NORTH_BLOCKRANGE | WALL_WEST_BLOCKRANGE;

    public static final int WALL_NORTH_AND_EAST_BLOCKRANGE = WALL_NORTH_BLOCKRANGE | WALL_EAST_BLOCKRANGE;

    public static final int WALL_SOUTH_AND_EAST_BLOCKRANGE = WALL_SOUTH_BLOCKRANGE | WALL_EAST_BLOCKRANGE;

    public static final int WALL_SOUTH_AND_WEST_BLOCKRANGE = WALL_SOUTH_BLOCKRANGE | WALL_WEST_BLOCKRANGE;

    public static final int WALL = BLOCKWALK | UNKNOWN | GROUND_DECOR | LOCATION;





}
