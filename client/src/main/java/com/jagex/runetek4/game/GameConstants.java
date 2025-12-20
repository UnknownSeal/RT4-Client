package com.jagex.runetek4.game;

public class GameConstants {

    //Social
    public static final int MAX_FRIENDS = 200;
    public static final int MAX_CLAN_MEMBERS = 100;
    public static final int MAX_RECENT_MESSAGES = 100;
    public static final int IGNORE_LIST_ENTRY_SIZE = 8;

    // Player
    public static final int MAX_PLAYER_OPTIONS = 8;
    public static final int MAX_SKILL_LEVEL_INDEX = 98;

    // UI
    public static final int MAX_COMPONENT_REDRAW_SLOTS = 100;
    public static final int CIRCULAR_BUFFER_MASK = 0x1F;
    public static final int ZOOM_PERCENTAGE = 100;

    // Inventory
    public static final int INVENTORY_ID_MASK = 0x7FFF;
    public static final int INVENTORY_COMPONENT_OFFSET = -70000;
    public static final int INVENTORY_PARAM_OFFSET = 32768;  // 0x8000
    public static final int EXTENDED_COUNT_MARKER = 255;

    // Timing
    public static final int SYSTEM_UPDATE_TICK_MULTIPLIER = 30;

    // Map Markers
    public static final int MAP_MARKER_PARAM_SHIFT = 6;
    public static final int MAP_MARKER_TYPE_MASK = 0x3F;
    public static final int MAP_MARKER_NPC = 1;
    public static final int MAP_MARKER_PLAYER = 10;
    public static final int MAP_MARKER_COORD_MIN = 2;
    public static final int MAP_MARKER_COORD_MAX = 6;

}
