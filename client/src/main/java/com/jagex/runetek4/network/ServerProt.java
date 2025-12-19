package com.jagex.runetek4.network;

public final class ServerProt {

    // Location zone packets
    public static final int ZONE_LOC_DEL = 195;
    public static final int ZONE_LOC_ADD_CHANGE = 179;
    public static final int ZONE_LOC_MERGE = 20;
    public static final int ZONE_LOC_ATTACH = 202;

    // Ground item zone packets
    public static final int ZONE_OBJ_ADD = 33;
    public static final int ZONE_OBJ_ADD_PRIVATE = 135;
    public static final int ZONE_OBJ_COUNT = 14;
    public static final int ZONE_OBJ_DEL = 240;

    // Animation zone packets
    public static final int ZONE_MAP_PROJANIM = 16;
    public static final int ZONE_MAP_PROJANIM_SPECIFIC = 104;
    public static final int ZONE_MAP_PROJANIM_SMALL = 121;
    public static final int ZONE_MAP_ANIM = 17;

    // Audio zone packets
    public static final int ZONE_SOUND_AREA = 97;

    // Interface packets
    public static final int IF_OPENTOP = 145;
    public static final int IF_OPENSUB = 155;
    public static final int IF_CLOSE_SUB = 149;
    public static final int IF_SETEVENTS = 9;
    public static final int IF_SETOBJECT = 165;

    // Inventory packets
    public static final int INV_TRANSMIT = 22;
    public static final int INV_TRANSMIT_FULL = 105;
    public static final int INV_DELETE = 191;
    public static final int INV_RESET_COMPONENT = 144;

    // Varp/Varc packets
    public static final int VARP_SMALL = 60;
    public static final int VARP_LARGE = 226;
    public static final int VARBIT = 37;
    public static final int VARBIT_LARGE = 84;
    public static final int VARC_SMALL = 69;
    public static final int VARC_LEGACY = 65;
    public static final int RESET_ANIMS = 89;
    public static final int SYNC_VARP_LARGE = 128;

    // Player update packets
    public static final int UPDATE_STAT = 38;
    public static final int UPDATE_RUNENERGY = 234;
    public static final int UPDATE_RUNWEIGHT = 159;
    public static final int PLAYER_INFO = 225;
    public static final int PLAYER_TELEPORT = 13;

    // Camera packets
    public static final int CAM_LOOKAT = 125;
    public static final int CAM_TARGET = 154;
    public static final int CAM_ORBIT = 187;
    public static final int CAM_SHAKE = 27;
    public static final int CAM_RESET = 24;

    // Map/Region packets
    public static final int MAP_UPDATE = 230;
    public static final int MAP_REBUILD = 214;
    public static final int MAP_REBUILD_PARTIAL = 162;
    public static final int MAP_CLEAR_ZONE = 112;
    public static final int MAP_COORDS = 26;

    // NPC packets
    public static final int NPC_INFO = 32;
    public static final int NPC_ANIM = 102;
    public static final int SPOTANIM_ENTITY = 56;

    // Chat and messaging packets
    public static final int MESSAGE_PRIVATE = 0;
    public static final int MESSAGE_PRIVATE_RECIPIENT = 54;
    public static final int MESSAGE_QUICKCHAT_FRIEND = 81;
    public static final int MESSAGE_QUICKCHAT_PRIVATE = 247;
    public static final int MESSAGE_GAME = 70;
    public static final int MESSAGE_QUICKCHAT_CLANCHAT = 141;
    public static final int FRIENDLIST_LOADED = 62;
    public static final int CLANCHAT_MEMBER_UPDATE = 196;

    // System and miscellaneous packets
    public static final int CLIENTSCRIPT_RUN = 115;
    public static final int MUSIC_PLAY = 4;
    public static final int SYSTEM_UPDATE = 85;
    public static final int LOGOUT = 86;
    public static final int REFLECTION_CHECK = 114;
    public static final int RANDOM_VERIFY = 169;
    public static final int URL_OPEN = 42;
    public static final int MINIMAP_STATE = 192;
    public static final int WALK_TEXT = 160;
    public static final int LAST_LOGIN_INFO = 164;
    public static final int SCENE_RESET = 153;
    public static final int COOKIE_STORE = 142; // Removed

}
