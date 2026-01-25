package com.jagex.network;

public final class ServerProt {

    // ========================================
    // ZONE UPDATE PROTOCOL
    // ========================================

    // Zone update containers
    public static final int UPDATE_ZONE_FULL_FOLLOWS = 112; // NXT naming
    public static final int UPDATE_ZONE_PARTIAL_FOLLOWS = 26;
    public static final int UPDATE_ZONE_PARTIAL_ENCLOSED = 230;

    // Location (Loc) zone updates
    public static final int LOC_ADD = 179; // NXT naming
    public static final int LOC_DEL = 195; // NXT naming
    public static final int LOC_ADD_CHANGE = 202; // NXT naming
    public static final int LOC_ANIM = 20; // NXT naming
    public static final int LOC_ANIM_SPECIFIC = 235; // NXT naming

    // Object (Obj) zone updates - ground items
    public static final int OBJ_ADD = 135; // NXT naming
    public static final int OBJ_DEL = 240; // NXT naming
    public static final int OBJ_COUNT = 14; // NXT naming
    public static final int OBJ_REVEAL = 33; // NXT naming

    // Projectile zone updates
    public static final int MAP_PROJANIM = 104; // NXT naming
    public static final int MAP_PROJANIM_2 = 16; // NXT naming
    public static final int MAP_PROJANIM_SMALL = 121;

    // Spot animation zone updates
    public static final int SPOTANIM_SPECIFIC = 17; // NXT naming
    public static final int SPOTANIM_ENTITY = 56;

    // Sound zone updates
    public static final int SOUND_AREA = 97; // NXT naming

    // ========================================
    // MAP AND REGION
    // ========================================

    public static final int MAP_REBUILD_REGION = 214; // Dynamic regions
    public static final int MAP_REBUILD_NORMAL = 162;
    public static final int HINT_ARROW = 217; // NXT naming (?)
    public static final int CLEAR_MINIMAP_FLAG = 153;
    public static final int SET_MINIMAP_STATE = 192;

    // ========================================
    // PLAYER UPDATE
    // ========================================

    public static final int PLAYER_INFO = 225; // NXT naming
    public static final int PLAYER_TELEPORT = 13;
    public static final int UPDATE_STAT = 38; // NXT naming
    public static final int UPDATE_RUNENERGY = 234; // NXT naming
    public static final int UPDATE_RUNWEIGHT = 159; // NXT naming (?)
    public static final int SET_PLAYER_OPTION = 44;

    // ========================================
    // NPC UPDATE
    // ========================================

    public static final int NPC_INFO = 32; // NXT naming
    public static final int NPC_ANIM_SPECIFIC = 102; // NXT naming

    // ========================================
    // ANIMATION AND EFFECTS
    // ========================================

    public static final int RESET_ANIMS = 131; // NXT naming

    // ========================================
    // INTERFACE STRUCTURE
    // ========================================

    public static final int IF_OPENSUB = 145; // NXT naming (?)
    public static final int IF_OPENTOP = 155; // NXT naming (?)
    public static final int IF_CLOSESUB = 149; // NXT naming (?)
    public static final int IF_MOVESUB = 176;
    public static final int IF_SETEVENTS = 9;
    public static final int IF_RUNSCRIPT = 209;

    // ========================================
    // INTERFACE CONTENT
    // ========================================

    public static final int IF_SETTEXT = 123;
    public static final int IF_SETTEXT_ALT = 48;
    public static final int IF_SETTEXT1 = 171;
    public static final int IF_SETCOLOUR = 2; // NXT naming
    public static final int IF_SETHIDE = 21; // NXT naming (?)
    public static final int IF_SETANIM = 36; // NXT naming (?)
    public static final int IF_SETPOSITION = 119; // NXT naming
    public static final int IF_SETANGLE = 132; // NXT naming
    public static final int IF_SETSCROLLPOS = 220; // NXT naming
    public static final int SET_INTERFACE_SETTINGS = 165;

    // ========================================
    // INTERFACE MODELS
    // ========================================

    public static final int IF_SETMODEL = 130; // NXT naming
    public static final int IF_SETPLAYERHEAD = 66; // NXT naming (?) - double check if this is correct or if it's IF_SETPLAYERMODEL_SELF etc
    public static final int IF_SETOBJECT = 50; // NXT naming
    public static final int IF_SETNPCHEAD = 73; // NXT naming
    public static final int IF_SETMODELROTATION = 207;
    public static final int GENERATE_CHAT_HEAD_FROM_BODY = 111;

    // ========================================
    // INVENTORY
    // ========================================

    public static final int UPDATE_INV_FULL = 105; // NXT naming
    public static final int UPDATE_INV_PARTIAL = 22; // NXT naming
    public static final int INV_DELETE = 191;
    public static final int INV_CLEAR = 144;

    // ========================================
    // VARIABLES (VARP/VARBIT/VARC)
    // ========================================

    public static final int VARP_SMALL = 60; // NXT naming
    public static final int VARP_LARGE = 226; // NXT naming
    public static final int VARBIT_SMALL = 37; // NXT naming
    public static final int VARBIT_LARGE = 84; // NXT naming
    public static final int CLIENT_SETVARC_SMALL = 65; // NXT naming
    public static final int CLIENT_SETVARC_LARGE = 69; // NXT naming
    public static final int FORCE_VARP_REFRESH = 128;
    public static final int RESET_CLIENT_VARCACHE = 89; // NXT naming (?)

    // ========================================
    // CAMERA
    // ========================================

    public static final int CAM_LOOKAT = 125; // NXT naming
    public static final int CAM_FORCEANGLE = 187; // NXT naming
    public static final int CAM_SHAKE = 27; // NXT naming
    public static final int CAM_RESET = 24; // NXT naming
    public static final int CAMERA_DETACH = 154;

    // ========================================
    // SOCIAL AND MESSAGING
    // ========================================

    // Chat messages
    public static final int MESSAGE_GAME = 70; // NXT naming
    public static final int MESSAGE_PRIVATE = 0; // NXT naming
    public static final int MESSAGE_PRIVATE_ECHO = 71; // NXT naming
    public static final int MESSAGE_CLANCHANNEL = 54; // NXT naming
    public static final int MESSAGE_QUICKCHAT_PRIVATE = 247; // NXT naming
    public static final int MESSAGE_QUICKCHAT_PRIVATE_ECHO = 141; // NXT naming
    public static final int MESSAGE_QUICKCHAT_CLAN = 81;

    // Friend/Ignore lists
    public static final int UPDATE_FRIENDLIST = 62; // NXT naming (?)
    public static final int FRIENDLIST_LOADED = 197; // NXT naming (?)
    public static final int UPDATE_IGNORELIST = 126; // NXT naming
    public static final int CHAT_FILTER_SETTINGS = 232; // NXT naming

    // Clan chat
    public static final int CLANCHAT_JOIN = 55;
    public static final int CLANCHAT_MEMBER_UPDATE = 196;

    // ========================================
    // AUDIO
    // ========================================

    public static final int MIDI_SONG = 4; // NXT naming
    public static final int MIDI_JINGLE = 208; // NXT naming
    public static final int SYNTH_SOUND = 172; // NXT naming

    // ========================================
    // TRADING AND GRAND EXCHANGE
    // ========================================

    public static final int GE_OFFER_UPDATE = 116;

    // ========================================
    // SYSTEM AND MISCELLANEOUS
    // ========================================

    public static final int CLIENTSCRIPT_RUN = 115;
    public static final int UPDATE_REBOOT_TIMER = 85; // NXT naming
    public static final int LOGOUT = 86; // NXT namin
    public static final int REFLECTION_CHECK = 114;
    public static final int UPDATE_UID192 = 169; // NXT naming (?)
    public static final int URL_OPEN = 42; // NXT naming
    public static final int WALK_TEXT = 160;
    public static final int LAST_LOGIN_INFO = 164; // NXT naming (?)
    public static final int COOKIE_STORE = 142; // Removed

}