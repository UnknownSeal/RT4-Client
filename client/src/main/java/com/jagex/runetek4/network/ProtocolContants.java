package com.jagex.runetek4.network;

public class ProtocolContants {

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

}
