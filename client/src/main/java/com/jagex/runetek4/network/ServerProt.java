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
}
