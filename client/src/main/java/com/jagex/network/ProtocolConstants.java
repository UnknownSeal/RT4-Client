package com.jagex.network;

public class ProtocolConstants {

    // Packet buffers
    public static final int OUTBOUND_BUFFER_SIZE = 5000;
    public static final int INBOUND_BUFFER_SIZE = 65536;
    public static final int CHAT_PACKET_SIZE = 5000;
    public static final int MAX_REMOVED_IDS = 1000;
    public static final int MAX_EXTENDED_IDS = 2048;

    // Player info update flags
    public static final int PLAYER_UPDATE_FLAG_CHAT = 0x80;
    public static final int PLAYER_UPDATE_FLAG_HIT_PRIMARY = 0x1;
    public static final int PLAYER_UPDATE_FLAG_ANIM = 0x8;
    public static final int PLAYER_UPDATE_FLAG_APPEARANCE = 0x4;
    public static final int PLAYER_UPDATE_FLAG_FACE_ENTITY = 0x2;
    public static final int PLAYER_UPDATE_FLAG_FORCE_MOVE = 0x400;
    public static final int PLAYER_UPDATE_FLAG_OVERHEAD_CHAT = 0x20;
    public static final int PLAYER_UPDATE_FLAG_HIT_SECONDARY = 0x200;
    public static final int PLAYER_UPDATE_FLAG_SPOTANIM = 0x800;
    public static final int PLAYER_UPDATE_FLAG_SPOTANIM_EXTENDED = 0x100;
    public static final int PLAYER_UPDATE_FLAG_FACE_COORD = 0x40;

    // Player info bits
    public static final int PLAYER_INFO_BITS_REQUIRED = 11;
    public static final int PLAYER_INDEX_BITS = 11;
    public static final int PLAYER_COORD_BITS = 7;
    public static final int PLAYER_LEVEL_BITS = 2;
    public static final int PLAYER_ANGLE_BITS = 3;
    public static final int PLAYER_DELTA_BITS = 5;
    public static final int PLAYER_DELTA_THRESHOLD = 15;
    public static final int PLAYER_DELTA_OFFSET = 32;

    // NPC info bits
    public static final int NPC_INFO_BITS_REQUIRED = 27;
    public static final int NPC_INDEX_BITS = 15;
    public static final int NPC_TYPE_BITS = 14;
    public static final int NPC_ANGLE_BITS = 3;
    public static final int NPC_DELTA_BITS = 5;
    public static final int NPC_DELTA_THRESHOLD = 15;
    public static final int NPC_DELTA_OFFSET = 32;
    public static final int NPC_END_MARKER = 32767;

    // NPC update flags
    public static final int NPC_UPDATE_FLAG_HIT_PRIMARY = 0x40;
    public static final int NPC_UPDATE_FLAG_HIT_SECONDARY = 0x2;
    public static final int NPC_UPDATE_FLAG_ANIM = 0x10;
    public static final int NPC_UPDATE_FLAG_FACE_ENTITY = 0x4;
    public static final int NPC_UPDATE_FLAG_SPOTANIM = 0x80;
    public static final int NPC_UPDATE_FLAG_TRANSFORM = 0x1;
    public static final int NPC_UPDATE_FLAG_OVERHEAD_CHAT = 0x20;
    public static final int NPC_UPDATE_FLAG_LAYERED_ANIM = 0x100;
    public static final int NPC_UPDATE_FLAG_FACE_COORD = 0x200;
    public static final int NPC_UPDATE_FLAG_EXTENDED = 0x8;
    public static final int PLAYER_UPDATE_FLAG_EXTENDED = 0x10;

    // Entity type flags
    public static final int ENTITY_TYPE_SHIFT_CHECK = 30;
    public static final int ENTITY_TYPE_NPC_SHIFT = 29;
    public static final int ENTITY_TYPE_PLAYER_SHIFT = 28;

    // Random data
    public static final int RANDOM_DATA_SIZE = 24;

    // Bit shift
    public static final int BIT_SHIFT_1 = 0x1;
    public static final int MESSAGE_ID_HIGH_SHIFT = 32;
    public static final int COMPONENT_UPPER_WORD_SHIFT = 16;

    // Network bits
    public static final int NPC_COUNT_BITS = 8;
    public static final int DYNAMIC_REGION_BITS = 26;

    // XTEA
    public static final int XTEA_KEY_SIZE = 4;
    public static final int XTEA_ENTRY_SIZE_BYTES = 16;

    // Invalid ID markers
    public static final int INVALID_ID_U16 = 65535;

    // Bit masks
    public static final int U16_MASK = 0xFFFF;

    // Camera
    public static final int CAMERA_MODIFIER_COUNT = 5;

    // Build area
    public static final int BUILD_AREA_SIZE = 13;

    // Location shape extraction
    public static final int LOC_SHAPE_SHIFT = 2;
}
