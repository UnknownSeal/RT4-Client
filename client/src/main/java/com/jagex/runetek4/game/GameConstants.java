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
    public static final int LOCAL_PLAYER_INDEX = 2047;

    // Chat
    public static final int QUICKCHAT_FLAG = 0x8000;
    public static final int QUICKCHAT_FLAG_MASK = 0x7FFF;
    public static final int CHAT_EFFECT_MASK = 0xFF;
    public static final int CHAT_COLOR_SHIFT = 8;
    public static final int CHAT_DURATION_LOOPS = 150;
    public static final int CHAT_TYPE_PUBLIC = 1;
    public static final int CHAT_TYPE_NORMAL = 2;
    public static final int CHAT_TYPE_QUICKCHAT = 17;

    // Staff levels
    public static final int STAFF_MOD_LEVEL_PMOD = 1;
    public static final int STAFF_MOD_LEVEL_JMOD = 2;

    // Combat
    public static final int HITPOINTS_BAR_DURATION = 300;

    // UI
    public static final int MAX_COMPONENT_REDRAW_SLOTS = 100;
    public static final int CIRCULAR_BUFFER_MASK = 0x1F;

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

    // Object stacking
    public static final int MIN_COST_VALUE = -99999999;
    public static final int STACKABLE_FLAG = 1;

    // Entity packing
    public static final int ENTITY_INDEX_MASK = 0x7FF;
    public static final int ENTITY_SLOT_SHIFT = 11;
    public static final int ENTITY_SLOT_MASK = 0xF;

    // Sound system
    public static final int SOUND_RADIUS_SHIFT = 4;
    public static final int SOUND_RADIUS_MASK = 0xF;
    public static final int SOUND_LOOP_MASK = 0x7;
    public static final int MAX_SOUND_QUEUE_SIZE = 50;

    // Special markers/flags
    public static final int NO_ANGLE_SPECIFIED = 255;

    // Display
    public static final int ZOOM_PERCENTAGE = 100;

    // Character contants
    public static final int TILDE_CHAR = 126;  // ~

    // Tutorial Island coordinates
    public static final int TUTORIAL_ISLAND_X1 = 48;
    public static final int TUTORIAL_ISLAND_X2 = 49;
    public static final int TUTORIAL_ISLAND_X3 = 50;
    public static final int TUTORIAL_ISLAND_Z1 = 47;
    public static final int TUTORIAL_ISLAND_Z2 = 48;
    public static final int TUTORIAL_ISLAND_Z3 = 49;
    public static final int TUTORIAL_ISLAND_Z4 = 147;
    public static final int TUTORIAL_ISLAND_Z5 = 148;
    public static final int TUTORIAL_ISLAND_Z6 = 149;
}
