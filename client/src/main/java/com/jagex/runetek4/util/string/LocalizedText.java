package com.jagex.runetek4.util.string;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class LocalizedText {
    //region English Translations
    @OriginalMember(owner = "runetek4.client!ui", name = "ob", descriptor = "Lclient!na;")
    private static final JString ENG_ATTACK = JString.parse("Attack");

    @OriginalMember(owner = "runetek4.client!vf", name = "d", descriptor = "Lclient!na;")
    private static final JString ENG_ATTEMPT_TO_REESTABLISH = JString.parse("Please wait )2 attempting to reestablish)3");

    @OriginalMember(owner = "runetek4.client!wb", name = "i", descriptor = "Lclient!na;")
    private static final JString ENG_CANCEL = JString.parse("Cancel");

    @OriginalMember(owner = "runetek4.client!bk", name = "I", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL0 = JString.parse("yellow:");

    @OriginalMember(owner = "runetek4.client!lb", name = "x", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL1 = JString.parse("red:");

    @OriginalMember(owner = "runetek4.client!h", name = "Bb", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL2 = JString.parse("green:");

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "eb", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL3 = JString.parse("cyan:");

    @OriginalMember(owner = "runetek4.client!hd", name = "h", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL4 = JString.parse("purple:");

    @OriginalMember(owner = "runetek4.client!bf", name = "y", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL5 = JString.parse("white:");

    @OriginalMember(owner = "runetek4.client!mg", name = "U", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL6 = JString.parse("flash1:");

    @OriginalMember(owner = "runetek4.client!tm", name = "n", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL7 = JString.parse("flash2:");

    @OriginalMember(owner = "runetek4.client!km", name = "Cc", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL8 = JString.parse("flash3:");

    @OriginalMember(owner = "runetek4.client!aa", name = "r", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL9 = JString.parse("glow1:");

    @OriginalMember(owner = "runetek4.client!vf", name = "j", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL10 = JString.parse("glow2:");

    @OriginalMember(owner = "runetek4.client!ah", name = "m", descriptor = "Lclient!na;")
    private static final JString ENG_CHATCOL11 = JString.parse("glow3:");

    @OriginalMember(owner = "runetek4.client!dc", name = "E", descriptor = "Lclient!na;")
    private static final JString ENG_CHATEFFECT1 = JString.parse("wave:");

    @OriginalMember(owner = "runetek4.client!pg", name = "ib", descriptor = "Lclient!na;")
    private static final JString ENG_CHATEFFECT2 = JString.parse("wave2:");

    @OriginalMember(owner = "runetek4.client!rm", name = "h", descriptor = "Lclient!na;")
    private static final JString ENG_CHATEFFECT3 = JString.parse("shake:");

    @OriginalMember(owner = "runetek4.client!ef", name = "m", descriptor = "Lclient!na;")
    private static final JString ENG_CHATEFFECT4 = JString.parse("scroll:");

    @OriginalMember(owner = "runetek4.client!eg", name = "B", descriptor = "Lclient!na;")
    private static final JString ENG_CHATEFFECT5 = JString.parse("slide:");

    @OriginalMember(owner = "runetek4.client!bm", name = "d", descriptor = "Lclient!na;")
    private static final JString ENG_CHECKING_FOR_UPDATES = JString.parse("Checking for updates )2 ");

    @OriginalMember(owner = "runetek4.client!ta", name = "q", descriptor = "Lclient!na;")
    private static final JString ENG_CHOOSE_OPTION = JString.parse("Choose Option");

    @OriginalMember(owner = "runetek4.client!d", name = "fb", descriptor = "Lclient!na;")
    private static final JString ENG_CLOSE = JString.parse("Close");

    @OriginalMember(owner = "runetek4.client!sm", name = "f", descriptor = "Lclient!na;")
    private static final JString ENG_CONLOST = JString.parse("Connection lost)3");

    @OriginalMember(owner = "runetek4.client!ig", name = "c", descriptor = "Lclient!na;")
    private static final JString ENG_CONTINUE = JString.parse("Continue");

    @OriginalMember(owner = "runetek4.client!dc", name = "R", descriptor = "Lclient!na;")
    private static final JString ENG_DROP = JString.parse("Drop");

    @OriginalMember(owner = "runetek4.client!li", name = "u", descriptor = "Lclient!na;")
    private static final JString ENG_EXAMINE = JString.parse("Examine");

    @OriginalMember(owner = "runetek4.client!sd", name = "M", descriptor = "Lclient!na;")
    private static final JString ENG_FACEHERE = JString.parse("Face here");

    @OriginalMember(owner = "runetek4.client!ii", name = "g", descriptor = "Lclient!na;")
    private static final JString ENG_FRIENDCANTADDSELF = JString.parse("You can(Wt add yourself to your own friend list)3");

    @OriginalMember(owner = "runetek4.client!lk", name = "S", descriptor = "Lclient!na;")
    private static final JString ENG_FRIENDLISTDUPE = JString.parse(" is already on your friend list)3");

    @OriginalMember(owner = "runetek4.client!t", name = "B", descriptor = "Lclient!na;")
    private static final JString ENG_FRIENDLISTFULL = JString.parse("Your friend list is full)3 Max of 100 for free users)1 and 200 for members)3");

    @OriginalMember(owner = "runetek4.client!c", name = "W", descriptor = "Lclient!na;")
    private static final JString ENG_FRIENDLOGIN = JString.parse(" has logged in)3");

    @OriginalMember(owner = "runetek4.client!hd", name = "o", descriptor = "Lclient!na;")
    private static final JString ENG_FRIENDLOGOUT = JString.parse(" has logged out)3");

    @OriginalMember(owner = "runetek4.client!ec", name = "r", descriptor = "Lclient!na;")
    private static final JString ENG_GAME0_LOADING = JString.parse("RuneScape is loading )2 please wait)3)3)3");

    @OriginalMember(owner = "runetek4.client!an", name = "Z", descriptor = "Lclient!na;")
    private static final JString ENG_HIDDEN = JString.parse("Hidden");

    @OriginalMember(owner = "runetek4.client!nb", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_IGNORECANTADDSELF = JString.parse("You can(Wt add yourself to your own ignore list)3");

    @OriginalMember(owner = "runetek4.client!ol", name = "X", descriptor = "Lclient!na;")
    private static final JString ENG_IGNORELISTDUPE = JString.parse(" is already on your ignore list)3");

    @OriginalMember(owner = "runetek4.client!u", name = "l", descriptor = "Lclient!na;")
    private static final JString ENG_IGNORELISTFULL = JString.parse("Your ignore list is full)3 Max of 100 users)3");

    @OriginalMember(owner = "runetek4.client!ck", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_LENT_ITEM_RETURN = JString.parse("Discard");

    @OriginalMember(owner = "runetek4.client!ef", name = "s", descriptor = "Lclient!na;")
    private static final JString ENG_LEVEL = JString.parse("level: ");

    @OriginalMember(owner = "runetek4.client!nh", name = "bb", descriptor = "Lclient!na;")
    private static final JString ENG_LOADING = JString.parse("Loading )2 please wait)3");

    @OriginalMember(owner = "runetek4.client!dm", name = "B", descriptor = "Lclient!na;")
    private static final JString ENG_LOADINGDOTDOTDOT = JString.parse("Loading)3)3)3");

    @OriginalMember(owner = "runetek4.client!kd", name = "kb", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD0 = JString.parse("Allocating memory");

    @OriginalMember(owner = "runetek4.client!na", name = "ob", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD0B = JString.parse("Allocated memory");

    @OriginalMember(owner = "runetek4.client!il", name = "J", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD10B = JString.parse("Created gameworld");

    @OriginalMember(owner = "runetek4.client!vj", name = "h", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD30 = JString.parse("Connecting to update server");

    @OriginalMember(owner = "runetek4.client!od", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD30B = JString.parse("Connected to update server");

    @OriginalMember(owner = "runetek4.client!ba", name = "s", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD40B = JString.parse("Loaded update list");

    @OriginalMember(owner = "runetek4.client!se", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD45B = JString.parse("Prepared sound engine");

    @OriginalMember(owner = "runetek4.client!lk", name = "P", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD50 = JString.parse("Loading fonts )2 ");

    @OriginalMember(owner = "runetek4.client!tl", name = "e", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD50B = JString.parse("Loaded fonts");

    @OriginalMember(owner = "runetek4.client!dm", name = "v", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD60 = JString.parse("Loading title screen )2 ");

    @OriginalMember(owner = "runetek4.client!cb", name = "gb", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD60B = JString.parse("Loaded title screen");

    @OriginalMember(owner = "runetek4.client!dl", name = "j", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD65B = JString.parse("Opened title screen");

    @OriginalMember(owner = "runetek4.client!bk", name = "K", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD70 = JString.parse("Loading config )2 ");

    @OriginalMember(owner = "runetek4.client!fn", name = "P", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD70B = JString.parse("Loaded config");

    @OriginalMember(owner = "runetek4.client!vc", name = "X", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD80 = JString.parse("Loading sprites )2 ");

    @OriginalMember(owner = "runetek4.client!sc", name = "w", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD80B = JString.parse("Loaded sprites");

    @OriginalMember(owner = "runetek4.client!ub", name = "n", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD90 = JString.parse("Loading textures )2 ");

    @OriginalMember(owner = "runetek4.client!gm", name = "fb", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD90B = JString.parse("Loaded textures");

    @OriginalMember(owner = "runetek4.client!ni", name = "p", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD110B = JString.parse("Loaded input handler");

    @OriginalMember(owner = "runetek4.client!li", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD120 = JString.parse("Loading wordpack )2 ");

    @OriginalMember(owner = "runetek4.client!pl", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD120B = JString.parse("Loaded wordpack");

    @OriginalMember(owner = "runetek4.client!bg", name = "O", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD130 = JString.parse("Loading interfaces )2 ");

    @OriginalMember(owner = "runetek4.client!wa", name = "D", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD130B = JString.parse("Loaded interfaces");

    @OriginalMember(owner = "runetek4.client!r", name = "c", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD135 = JString.parse("Loading world list data");

    @OriginalMember(owner = "runetek4.client!ck", name = "o", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD135B = JString.parse("Loaded world list data");

    @OriginalMember(owner = "runetek4.client!cn", name = "J", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD140 = JString.parse("Starting 3d Library");

    @OriginalMember(owner = "runetek4.client!rg", name = "u", descriptor = "Lclient!na;")
    private static final JString ENG_MAINLOAD150B = JString.parse("Started 3d Library");

    @OriginalMember(owner = "runetek4.client!qc", name = "Z", descriptor = "Lclient!na;")
    private static final JString ENG_MEMBERS_OBJECT = JString.parse("Members object");

    @OriginalMember(owner = "runetek4.client!fh", name = "W", descriptor = "Lclient!na;")
    private static final JString ENG_MILLION_SUFFIX = JString.parse("M");

    @OriginalMember(owner = "runetek4.client!qk", name = "l", descriptor = "Lclient!na;")
    private static final JString ENG_MINISEPARATOR = JString.parse(" ");

    @OriginalMember(owner = "runetek4.client!ui", name = "ab", descriptor = "Lclient!na;")
    private static final JString ENG_MOREOPTIONS = JString.parse(" more options");

    @OriginalMember(owner = "runetek4.client!ph", name = "c", descriptor = "Lclient!na;")
    private static final JString ENG_OK = JString.parse("Ok");

    @OriginalMember(owner = "runetek4.client!bj", name = "z", descriptor = "Lclient!na;")
    private static final JString ENG_PLEASEWAIT = JString.parse("Please wait)3)3)3");

    @OriginalMember(owner = "runetek4.client!mf", name = "T", descriptor = "Lclient!na;")
    private static final JString ENG_RATING = JString.parse("rating: ");

    @OriginalMember(owner = "runetek4.client!pm", name = "gb", descriptor = "Lclient!na;")
    private static final JString ENG_REMOVEFRIEND = JString.parse(" from your friend list first)3");

    @OriginalMember(owner = "runetek4.client!rc", name = "v", descriptor = "Lclient!na;")
    private static final JString ENG_REMOVEIGNORE = JString.parse(" from your ignore list first)3");

    @OriginalMember(owner = "runetek4.client!fk", name = "b", descriptor = "Lclient!na;")
    private static final JString ENG_REMOVESOCIAL = JString.parse("Please remove ");

    @OriginalMember(owner = "runetek4.client!se", name = "o", descriptor = "Lclient!na;")
    private static final JString ENG_SELECT = JString.parse("Select");

    @OriginalMember(owner = "runetek4.client!ef", name = "k", descriptor = "Lclient!na;")
    private static final JString ENG_SKILL = JString.parse("skill: ");

    @OriginalMember(owner = "runetek4.client!si", name = "hb", descriptor = "Lclient!na;")
    private static final JString ENG_TAKE = JString.parse("Take");

    @OriginalMember(owner = "runetek4.client!cl", name = "N", descriptor = "Lclient!na;")
    private static final JString ENG_THOUSAND_SUFFIX = JString.parse("K");

    @OriginalMember(owner = "runetek4.client!ea", name = "q", descriptor = "Lclient!na;")
    private static final JString ENG_TRADEREQ = JString.parse("wishes to trade with you)3");

    @OriginalMember(owner = "runetek4.client!gi", name = "f", descriptor = "Lclient!na;")
    private static final JString ENG_UNABLETOFIND = JString.parse("Unable to find ");

    @OriginalMember(owner = "runetek4.client!sa", name = "U", descriptor = "Lclient!na;")
    private static final JString ENG_USE = JString.parse("Use");

    @OriginalMember(owner = "runetek4.client!ib", name = "m", descriptor = "Lclient!na;")
    private static final JString ENG_WALKHERE = JString.parse("Walk here");
    //endregion

    //region Stable Translations (chat effects/colors)
    @OriginalMember(owner = "runetek4.client!bk", name = "R", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL0 = ENG_CHATCOL0;

    @OriginalMember(owner = "runetek4.client!lb", name = "D", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL1 = ENG_CHATCOL1;

    @OriginalMember(owner = "runetek4.client!h", name = "wb", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL2 = ENG_CHATCOL2;

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "cb", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL3 = ENG_CHATCOL3;

    @OriginalMember(owner = "runetek4.client!hd", name = "p", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL4 = ENG_CHATCOL4;

    @OriginalMember(owner = "runetek4.client!bf", name = "N", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL5 = ENG_CHATCOL5;

    @OriginalMember(owner = "runetek4.client!mg", name = "T", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL6 = ENG_CHATCOL6;

    @OriginalMember(owner = "runetek4.client!tm", name = "k", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL7 = ENG_CHATCOL7;

    @OriginalMember(owner = "runetek4.client!km", name = "wc", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL8 = ENG_CHATCOL8;

    @OriginalMember(owner = "runetek4.client!aa", name = "d", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL9 = ENG_CHATCOL9;

    @OriginalMember(owner = "runetek4.client!vf", name = "h", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL10 = ENG_CHATCOL10;

    @OriginalMember(owner = "runetek4.client!ah", name = "r", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATCOL11 = ENG_CHATCOL11;

    @OriginalMember(owner = "runetek4.client!dc", name = "S", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATEFFECT1 = ENG_CHATEFFECT1;

    @OriginalMember(owner = "runetek4.client!pg", name = "X", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATEFFECT2 = ENG_CHATEFFECT2;

    @OriginalMember(owner = "runetek4.client!rm", name = "k", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATEFFECT3 = ENG_CHATEFFECT3;

    @OriginalMember(owner = "runetek4.client!ef", name = "t", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATEFFECT4 = ENG_CHATEFFECT4;

    @OriginalMember(owner = "runetek4.client!eg", name = "u", descriptor = "Lclient!na;")
    public static final JString STABLE_CHATEFFECTC5 = ENG_CHATEFFECT5;
    //endregion

    // TODO: unsorted
    //region Active
    @OriginalMember(owner = "runetek4.client!vj", name = "b", descriptor = "Lclient!na;")
    public static JString MAINLOAD30 = ENG_MAINLOAD30;

    @OriginalMember(owner = "runetek4.client!lb", name = "q", descriptor = "Lclient!na;")
    public static JString CHATCOL1 = ENG_CHATCOL1;

    @OriginalMember(owner = "runetek4.client!sd", name = "O", descriptor = "Lclient!na;")
    public static JString FACEHERE = ENG_FACEHERE;

    @OriginalMember(owner = "runetek4.client!vc", name = "Q", descriptor = "Lclient!na;")
    public static JString MAINLOAD80 = ENG_MAINLOAD80;

    @OriginalMember(owner = "runetek4.client!mf", name = "U", descriptor = "Lclient!na;")
    public static JString RATING = ENG_RATING;

    @OriginalMember(owner = "runetek4.client!qc", name = "Y", descriptor = "Lclient!na;")
    public static JString MEMBERS_OBJECT = ENG_MEMBERS_OBJECT;

    @OriginalMember(owner = "runetek4.client!si", name = "db", descriptor = "Lclient!na;")
    public static JString TAKE = ENG_TAKE;

    @OriginalMember(owner = "runetek4.client!ni", name = "j", descriptor = "Lclient!na;")
    public static JString MAINLOAD110B = ENG_MAINLOAD110B;

    @OriginalMember(owner = "runetek4.client!ib", name = "j", descriptor = "Lclient!na;")
    public static JString WALKHERE = ENG_WALKHERE;

    @OriginalMember(owner = "runetek4.client!qk", name = "i", descriptor = "Lclient!na;")
    public static JString MINISEPARATOR = ENG_MINISEPARATOR;

    @OriginalMember(owner = "runetek4.client!gi", name = "b", descriptor = "Lclient!na;")
    public static JString UNABLETOFIND = ENG_UNABLETOFIND;

    @OriginalMember(owner = "runetek4.client!nb", name = "c", descriptor = "Lclient!na;")
    public static JString IGNORECANTADDSELF = ENG_IGNORECANTADDSELF;

    @OriginalMember(owner = "runetek4.client!ph", name = "d", descriptor = "Lclient!na;")
    public static JString OK = ENG_OK;

    @OriginalMember(owner = "runetek4.client!rg", name = "E", descriptor = "Lclient!na;")
    public static JString MAINLOAD150B = ENG_MAINLOAD150B;

    @OriginalMember(owner = "runetek4.client!rc", name = "s", descriptor = "Lclient!na;")
    public static JString REMOVEIGNORE = ENG_REMOVEIGNORE;

    @OriginalMember(owner = "runetek4.client!tm", name = "p", descriptor = "Lclient!na;")
    public static JString CHATCOL7 = ENG_CHATCOL7;

    @OriginalMember(owner = "runetek4.client!ta", name = "w", descriptor = "Lclient!na;")
    public static JString CHOOSE_OPTION = ENG_CHOOSE_OPTION;

    @OriginalMember(owner = "runetek4.client!vf", name = "i", descriptor = "Lclient!na;")
    public static JString CHATCOL10 = ENG_CHATCOL10;

    @OriginalMember(owner = "runetek4.client!vf", name = "e", descriptor = "Lclient!na;")
    public static JString ATTEMPT_TO_REESTABLISH = ENG_ATTEMPT_TO_REESTABLISH;

    @OriginalMember(owner = "runetek4.client!ig", name = "g", descriptor = "Lclient!na;")
    public static JString CONTINUE = ENG_CONTINUE;

    @OriginalMember(owner = "runetek4.client!sc", name = "r", descriptor = "Lclient!na;")
    public static JString MAINLOAD80B = ENG_MAINLOAD80B;

    @OriginalMember(owner = "runetek4.client!r", name = "a", descriptor = "Lclient!na;")
    public static JString MAINLOAD135 = ENG_MAINLOAD135;

    @OriginalMember(owner = "runetek4.client!sa", name = "X", descriptor = "Lclient!na;")
    public static JString USE = ENG_USE;

    @OriginalMember(owner = "runetek4.client!ub", name = "e", descriptor = "Lclient!na;")
    public static JString MAINLOAD90 = ENG_MAINLOAD90;

    @OriginalMember(owner = "runetek4.client!hd", name = "f", descriptor = "Lclient!na;")
    public static JString CHATCOL4 = ENG_CHATCOL4;

    @OriginalMember(owner = "runetek4.client!hd", name = "c", descriptor = "Lclient!na;")
    public static JString FRIENDLOGOUT = ENG_FRIENDLOGOUT;

    @OriginalMember(owner = "runetek4.client!nh", name = "W", descriptor = "Lclient!na;")
    public static JString LOADING = ENG_LOADING;

    @OriginalMember(owner = "runetek4.client!h", name = "zb", descriptor = "Lclient!na;")
    public static JString CHATCOL2 = ENG_CHATCOL2;

    @OriginalMember(owner = "runetek4.client!wa", name = "eb", descriptor = "Lclient!na;")
    public static JString MAINLOAD130B = ENG_MAINLOAD130B;

    @OriginalMember(owner = "runetek4.client!u", name = "f", descriptor = "Lclient!na;")
    public static JString IGNORELISTFULL = ENG_IGNORELISTFULL;

    @OriginalMember(owner = "runetek4.client!sm", name = "d", descriptor = "Lclient!na;")
    public static JString CONLOST = ENG_CONLOST;

    @OriginalMember(owner = "runetek4.client!il", name = "M", descriptor = "Lclient!na;")
    public static JString MAINLOAD10B = ENG_MAINLOAD10B;

    @OriginalMember(owner = "runetek4.client!wb", name = "h", descriptor = "Lclient!na;")
    public static JString CANCEL = ENG_CANCEL;

    @OriginalMember(owner = "runetek4.client!t", name = "D", descriptor = "Lclient!na;")
    public static JString FRIENDLISTFULL = ENG_FRIENDLISTFULL;

    @OriginalMember(owner = "runetek4.client!km", name = "Dc", descriptor = "Lclient!na;")
    public static JString CHATCOL8 = ENG_CHATCOL8;

    @OriginalMember(owner = "runetek4.client!mg", name = "W", descriptor = "Lclient!na;")
    public static JString CHATCOL6 = ENG_CHATCOL6;

    @OriginalMember(owner = "runetek4.client!kd", name = "Ab", descriptor = "Lclient!na;")
    public static JString MAINLOAD0 = ENG_MAINLOAD0;

    @OriginalMember(owner = "runetek4.client!rm", name = "j", descriptor = "Lclient!na;")
    public static JString CHATEFFECT3 = ENG_CHATEFFECT3;

    @OriginalMember(owner = "runetek4.client!pm", name = "fb", descriptor = "Lclient!na;")
    public static JString REMOVEFRIEND = ENG_REMOVEFRIEND;

    @OriginalMember(owner = "runetek4.client!tl", name = "h", descriptor = "Lclient!na;")
    public static JString MAINLOAD50B = ENG_MAINLOAD50B;

    @OriginalMember(owner = "runetek4.client!dm", name = "d", descriptor = "Lclient!na;")
    public static JString MAINLOAD60 = ENG_MAINLOAD60;

    @OriginalMember(owner = "runetek4.client!se", name = "v", descriptor = "Lclient!na;")
    public static JString MAINLOAD45B = ENG_MAINLOAD45B;

    @OriginalMember(owner = "runetek4.client!se", name = "k", descriptor = "Lclient!na;")
    public static JString SELECT = ENG_SELECT;

    @OriginalMember(owner = "runetek4.client!li", name = "i", descriptor = "Lclient!na;")
    public static JString MAINLOAD120 = ENG_MAINLOAD120;

    @OriginalMember(owner = "runetek4.client!li", name = "f", descriptor = "Lclient!na;")
    public static JString EXAMINE = ENG_EXAMINE;

    @OriginalMember(owner = "runetek4.client!gm", name = "cb", descriptor = "Lclient!na;")
    public static JString MAINLOAD90B = ENG_MAINLOAD90B;

    @OriginalMember(owner = "runetek4.client!ii", name = "q", descriptor = "Lclient!na;")
    public static JString FRIENDCANTADDSELF = ENG_FRIENDCANTADDSELF;

    @OriginalMember(owner = "runetek4.client!pl", name = "g", descriptor = "Lclient!na;")
    public static JString MAINLOAD120B = ENG_MAINLOAD120B;

    @OriginalMember(owner = "runetek4.client!ol", name = "db", descriptor = "Lclient!na;")
    public static JString IGNORELISTDUPE = ENG_IGNORELISTDUPE;

    @OriginalMember(owner = "runetek4.client!lk", name = "X", descriptor = "Lclient!na;")
    public static JString MAINLOAD50 = ENG_MAINLOAD50;

    @OriginalMember(owner = "runetek4.client!lk", name = "ab", descriptor = "Lclient!na;")
    public static JString FRIENDLISTDUPE = ENG_FRIENDLISTDUPE;

    @OriginalMember(owner = "runetek4.client!pg", name = "hb", descriptor = "Lclient!na;")
    public static JString CHATEFFECT2 = ENG_CHATEFFECT2;

    @OriginalMember(owner = "runetek4.client!od", name = "h", descriptor = "Lclient!na;")
    public static JString MAINLOAD30B = ENG_MAINLOAD30B;

    @OriginalMember(owner = "runetek4.client!na", name = "D", descriptor = "Lclient!na;")
    public static JString MAINLOAD0B = ENG_MAINLOAD0B;

    @OriginalMember(owner = "runetek4.client!fk", name = "h", descriptor = "Lclient!na;")
    public static JString REMOVESOCIAL1 = ENG_REMOVESOCIAL;

    @OriginalMember(owner = "runetek4.client!ui", name = "Y", descriptor = "Lclient!na;")
    public static JString ATTACK = ENG_ATTACK;

    @OriginalMember(owner = "runetek4.client!ui", name = "V", descriptor = "Lclient!na;")
    public static JString MOREOPTIONS = ENG_MOREOPTIONS;

    @OriginalMember(owner = "runetek4.client!fn", name = "R", descriptor = "Lclient!na;")
    public static JString MAINLOAD70B = ENG_MAINLOAD70B;

    @OriginalMember(owner = "runetek4.client!fk", name = "o", descriptor = "Lclient!na;")
    public static JString REMOVESOCIAL2 = ENG_REMOVESOCIAL;

    @OriginalMember(owner = "runetek4.client!fh", name = "bb", descriptor = "Lclient!na;")
    public static JString MILLION_SHORT = ENG_MILLION_SUFFIX;

    @OriginalMember(owner = "runetek4.client!fh", name = "T", descriptor = "Lclient!na;")
    public static JString MILLION = ENG_MILLION_SUFFIX;

    @OriginalMember(owner = "runetek4.client!eg", name = "z", descriptor = "Lclient!na;")
    public static JString CHATEFFECT5 = ENG_CHATEFFECT5;

    @OriginalMember(owner = "runetek4.client!ef", name = "q", descriptor = "Lclient!na;")
    public static JString LEVEL = ENG_LEVEL;

    @OriginalMember(owner = "runetek4.client!ef", name = "n", descriptor = "Lclient!na;")
    public static JString SKILL = ENG_SKILL;

    @OriginalMember(owner = "runetek4.client!ef", name = "o", descriptor = "Lclient!na;")
    public static JString CHATEFFECT4 = ENG_CHATEFFECT4;

    @OriginalMember(owner = "runetek4.client!ec", name = "k", descriptor = "Lclient!na;")
    public static JString GAME0_LOADING = ENG_GAME0_LOADING;

    @OriginalMember(owner = "runetek4.client!ea", name = "p", descriptor = "Lclient!na;")
    public static JString TRADEREQ = ENG_TRADEREQ;

    @OriginalMember(owner = "runetek4.client!dm", name = "e", descriptor = "Lclient!na;")
    public static JString LOADINGDOTDOTDOT = ENG_LOADINGDOTDOTDOT;

    @OriginalMember(owner = "runetek4.client!dl", name = "k", descriptor = "Lclient!na;")
    public static JString MAINLOAD65B = ENG_MAINLOAD65B;

    @OriginalMember(owner = "runetek4.client!dc", name = "hb", descriptor = "Lclient!na;")
    public static JString CHATEFFECT1 = ENG_CHATEFFECT1;

    @OriginalMember(owner = "runetek4.client!dc", name = "Y", descriptor = "Lclient!na;")
    public static JString DROP = ENG_DROP;

    @OriginalMember(owner = "runetek4.client!d", name = "gb", descriptor = "Lclient!na;")
    public static JString CLOSE = ENG_CLOSE;

    @OriginalMember(owner = "runetek4.client!cn", name = "q", descriptor = "Lclient!na;")
    public static JString MAINLOAD140 = ENG_MAINLOAD140;

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "X", descriptor = "Lclient!na;")
    public static JString CHATCOL3 = ENG_CHATCOL3;

    @OriginalMember(owner = "runetek4.client!cl", name = "T", descriptor = "Lclient!na;")
    public static JString THOUSAND_SHORT = ENG_THOUSAND_SUFFIX;

    @OriginalMember(owner = "runetek4.client!cl", name = "Q", descriptor = "Lclient!na;")
    public static JString THOUSAND = ENG_THOUSAND_SUFFIX;

    @OriginalMember(owner = "runetek4.client!ck", name = "y", descriptor = "Lclient!na;")
    public static JString MAINLOAD135B = ENG_MAINLOAD135B;

    @OriginalMember(owner = "runetek4.client!ck", name = "i", descriptor = "Lclient!na;")
    public static JString LENT_ITEM_RETURN = ENG_LENT_ITEM_RETURN;

    @OriginalMember(owner = "runetek4.client!cb", name = "db", descriptor = "Lclient!na;")
    public static JString MAINLOAD60B = ENG_MAINLOAD60B;

    @OriginalMember(owner = "runetek4.client!c", name = "Z", descriptor = "Lclient!na;")
    public static JString FRIENDLOGIN = ENG_FRIENDLOGIN;

    @OriginalMember(owner = "runetek4.client!bm", name = "l", descriptor = "Lclient!na;")
    public static JString CHECKING_FOR_UPDATES = ENG_CHECKING_FOR_UPDATES;

    @OriginalMember(owner = "runetek4.client!bk", name = "L", descriptor = "Lclient!na;")
    public static JString CHATCOL0 = ENG_CHATCOL0;

    @OriginalMember(owner = "runetek4.client!bk", name = "N", descriptor = "Lclient!na;")
    public static JString MAINLOAD70 = ENG_MAINLOAD70;

    @OriginalMember(owner = "runetek4.client!bj", name = "q", descriptor = "Lclient!na;")
    public static JString PLEASEWAIT = ENG_PLEASEWAIT;

    @OriginalMember(owner = "runetek4.client!bg", name = "P", descriptor = "Lclient!na;")
    public static JString MAINLOAD130 = ENG_MAINLOAD130;

    @OriginalMember(owner = "runetek4.client!bf", name = "D", descriptor = "Lclient!na;")
    public static JString CHATCOL5 = ENG_CHATCOL5;

    @OriginalMember(owner = "runetek4.client!ba", name = "z", descriptor = "Lclient!na;")
    public static JString MAINLOAD40B = ENG_MAINLOAD40B;

    @OriginalMember(owner = "runetek4.client!an", name = "cb", descriptor = "Lclient!na;")
    public static JString HIDDEN = ENG_HIDDEN;

    @OriginalMember(owner = "runetek4.client!ah", name = "q", descriptor = "Lclient!na;")
    public static JString CHATCOL11 = ENG_CHATCOL11;

    @OriginalMember(owner = "runetek4.client!aa", name = "g", descriptor = "Lclient!na;")
    public static JString CHATCOL9 = ENG_CHATCOL9;
    //endregion

    // TODO: unsorted
    //region German Translations
    @OriginalMember(owner = "runetek4.client!vk", name = "l", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD135 = JString.parse("Lade Liste der Welten");

    @OriginalMember(owner = "runetek4.client!eg", name = "A", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD80B = JString.parse("Sprites geladen)3");

    @OriginalMember(owner = "runetek4.client!eh", name = "h", descriptor = "Lclient!na;")
    public static final JString GER_TRADEREQ = JString.parse("m-Ochte mit Ihnen handeln)3");

    @OriginalMember(owner = "runetek4.client!eh", name = "i", descriptor = "Lclient!na;")
    public static final JString GER_REMOVEFRIEND = JString.parse(" zuerst von Ihrer Freunde)2Liste(Q");

    @OriginalMember(owner = "runetek4.client!ed", name = "r", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD120B = JString.parse("Wordpack geladen)3");

    @OriginalMember(owner = "runetek4.client!dk", name = "i", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL6 = JString.parse("blinken1:");

    @OriginalMember(owner = "runetek4.client!dm", name = "h", descriptor = "Lclient!na;")
    public static final JString GER_REMOVESOCIAL = JString.parse("Bitte entfernen Sie ");

    @OriginalMember(owner = "runetek4.client!ck", name = "c", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD70 = JString.parse("Lade Konfiguration )2 ");

    @OriginalMember(owner = "runetek4.client!cm", name = "h", descriptor = "Lclient!na;")
    public static final JString GER_CONLOST = JString.parse("Verbindung abgebrochen)3");

    @OriginalMember(owner = "runetek4.client!cn", name = "s", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL4 = JString.parse("lila:");

    @OriginalMember(owner = "runetek4.client!cn", name = "C", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL1 = JString.parse("rot:");

    @OriginalMember(owner = "runetek4.client!cd", name = "y", descriptor = "Lclient!na;")
    public static final JString GER_CHECKING_FOR_UPDATES = JString.parse("Suche nach Updates )2 ");

    @OriginalMember(owner = "runetek4.client!cd", name = "D", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD120 = JString.parse("Lade Wordpack )2 ");

    @OriginalMember(owner = "runetek4.client!ce", name = "m", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD90 = JString.parse("Lade Texturen )2 ");

    @OriginalMember(owner = "runetek4.client!cg", name = "d", descriptor = "Lclient!na;")
    public static final JString GER_LENT_ITEM_RETURN = JString.parse("Ablegen");

    @OriginalMember(owner = "runetek4.client!bj", name = "p", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD70B = JString.parse("Konfig geladen)3");

    @OriginalMember(owner = "runetek4.client!bj", name = "U", descriptor = "Lclient!na;")
    public static final JString GER_CHATEFFECT2 = JString.parse("welle2:");

    @OriginalMember(owner = "runetek4.client!bk", name = "P", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD0 = JString.parse("Speicher wird zugewiesen)3");

    @OriginalMember(owner = "runetek4.client!bd", name = "d", descriptor = "Lclient!na;")
    public static final JString GER_SELECT = JString.parse("Ausw-=hlen");

    @OriginalMember(owner = "runetek4.client!bb", name = "N", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL0 = JString.parse("gelb:");

    @OriginalMember(owner = "runetek4.client!an", name = "eb", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD50 = JString.parse("Lade Schrifts-=tze )2 ");

    @OriginalMember(owner = "runetek4.client!ba", name = "E", descriptor = "Lclient!na;")
    public static final JString GER_LEVEL = JString.parse("Stufe: ");

    @OriginalMember(owner = "runetek4.client!af", name = "f", descriptor = "Lclient!na;")
    public static final JString GER_MEMBERS_OBJECT = JString.parse("Gegenstand f-Ur Mitglieder");

    @OriginalMember(owner = "runetek4.client!be", name = "Zc", descriptor = "Lclient!na;")
    public static final JString GER_TAKE = JString.parse("Nehmen");

    @OriginalMember(owner = "runetek4.client!se", name = "s", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD110B = JString.parse("Eingabeprozedur geladen)3");

    @OriginalMember(owner = "runetek4.client!gm", name = "eb", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD80 = JString.parse("Lade Sprites )2 ");

    @OriginalMember(owner = "runetek4.client!qj", name = "h", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD90B = JString.parse("Texturen geladen)3");

    @OriginalMember(owner = "runetek4.client!rb", name = "e", descriptor = "Lclient!na;")
    public static final JString GER_HIDDEN = JString.parse("Versteckt");

    @OriginalMember(owner = "runetek4.client!tk", name = "x", descriptor = "Lclient!na;")
    public static final JString GER_FRIENDLISTDUPE = JString.parse(" steht bereits auf Ihrer Freunde)2Liste(Q");

    @OriginalMember(owner = "runetek4.client!nk", name = "o", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD65B = JString.parse("Titelbild ge-Offnet)3");

    @OriginalMember(owner = "runetek4.client!nm", name = "R", descriptor = "Lclient!na;")
    public static final JString GER_CLOSE = JString.parse("Schlie-8en");

    @OriginalMember(owner = "runetek4.client!s", name = "j", descriptor = "Lclient!na;")
    public static final JString GER_FRIENDCANTADDSELF = JString.parse("Sie k-Onnen sich selbst nicht auf Ihre Freunde)2Liste setzen(Q");

    @OriginalMember(owner = "runetek4.client!oe", name = "k", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL11 = JString.parse("leuchten3:");

    @OriginalMember(owner = "runetek4.client!pg", name = "W", descriptor = "Lclient!na;")
    public static final JString GER_FRIENDLOGIN = JString.parse(" loggt sich ein)3");

    @OriginalMember(owner = "runetek4.client!pg", name = "Y", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL9 = JString.parse("leuchten1:");

    @OriginalMember(owner = "runetek4.client!g", name = "b", descriptor = "Lclient!na;")
    public static final JString GER_FRIENDLISTFULL = JString.parse("Ihre Freunde)2Liste ist voll(Q Maximale Eintr-=ge: Mitglieder 200)4freie Spieler 100");

    @OriginalMember(owner = "runetek4.client!ui", name = "hb", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD50B = JString.parse("Schrifts-=tze geladen)3");

    @OriginalMember(owner = "runetek4.client!na", name = "p", descriptor = "Lclient!na;")
    public static final JString GER_ATTEMPT_TO_REESTABLISH = JString.parse("Bitte warten Sie )2 es wird versucht)1 die Verbindung wiederherzustellen)3");

    @OriginalMember(owner = "runetek4.client!fc", name = "d", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD130 = JString.parse("Lade Benutzeroberfl-=che )2 ");

    @OriginalMember(owner = "runetek4.client!em", name = "A", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL8 = JString.parse("blinken3:");

    @OriginalMember(owner = "runetek4.client!q", name = "b", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD30B = JString.parse("Verbindung zum Update)2Server hergestellt)3");

    @OriginalMember(owner = "runetek4.client!gi", name = "l", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL3 = JString.parse("blaugr-Un:");

    @OriginalMember(owner = "runetek4.client!jm", name = "w", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD150B = JString.parse("3D)2Softwarebibliothek gestartet)3");

    @OriginalMember(owner = "runetek4.client!ig", name = "e", descriptor = "Lclient!na;")
    public static final JString GER_LOADING = JString.parse("Ladevorgang )2 bitte warten Sie)3");

    @OriginalMember(owner = "runetek4.client!ta", name = "m", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD0B = JString.parse("Zugewiesener Speicher)3");

    @OriginalMember(owner = "runetek4.client!sc", name = "k", descriptor = "Lclient!na;")
    public static final JString GER_FACEHERE = JString.parse("Hierhin drehen");

    @OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL7 = JString.parse("blinken2:");

    @OriginalMember(owner = "runetek4.client!h", name = "yb", descriptor = "Lclient!na;")
    public static final JString GER_REMOVEIGNORE = JString.parse(" zuerst von Ihrer Ignorieren)2Liste(Q");

    @OriginalMember(owner = "runetek4.client!nh", name = "S", descriptor = "Lclient!na;")
    public static final JString GER_LOADINGDOTDOTDOT = JString.parse("Lade)3)3)3");

    @OriginalMember(owner = "runetek4.client!nh", name = "V", descriptor = "Lclient!na;")
    public static final JString GER_CHATEFFECT3 = JString.parse("sch-Utteln:");

    @OriginalMember(owner = "runetek4.client!pi", name = "bb", descriptor = "Lclient!na;")
    public static final JString GER_IGNORECANTADDSELF = JString.parse("Sie k-Onnen sich selbst nicht selbst auf Ihre Ignorieren)2Liste setzen(Q");

    @OriginalMember(owner = "runetek4.client!ug", name = "f", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL5 = JString.parse("weiss:");

    @OriginalMember(owner = "runetek4.client!nc", name = "d", descriptor = "Lclient!na;")
    public static final JString GER_RATING = JString.parse("Kampfstufe: ");

    @OriginalMember(owner = "runetek4.client!pk", name = "eb", descriptor = "Lclient!na;")
    public static final JString GER_ATTACK = JString.parse("Angreifen");

    @OriginalMember(owner = "runetek4.client!pf", name = "l", descriptor = "Lclient!na;")
    public static final JString GER_MOREOPTIONS = JString.parse(" weitere Optionen");

    @OriginalMember(owner = "runetek4.client!la", name = "g", descriptor = "Lclient!na;")
    public static final JString GER_IGNORELISTDUPE = JString.parse(" steht bereits auf Ihrer Ignorieren)2Liste(Q");

    @OriginalMember(owner = "runetek4.client!jg", name = "k", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD60B = JString.parse("Titelbild geladen)3");

    @OriginalMember(owner = "runetek4.client!lg", name = "j", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL2 = JString.parse("gr-Un:");

    @OriginalMember(owner = "runetek4.client!nd", name = "u", descriptor = "Lclient!na;")
    public static final JString GER_CANCEL = JString.parse("Abbrechen");

    @OriginalMember(owner = "runetek4.client!uh", name = "fb", descriptor = "Lclient!na;")
    public static final JString GER_GAME0_LOADING = JString.parse("RuneScape wird geladen )2 bitte warten)3)3)3");

    @OriginalMember(owner = "runetek4.client!hb", name = "p", descriptor = "Lclient!na;")
    public static final JString GER_MILLION_SUFFIX = JString.parse("M");

    @OriginalMember(owner = "runetek4.client!hb", name = "x", descriptor = "Lclient!na;")
    public static final JString GER_PLEASEWAIT = JString.parse("Bitte warten Sie)3)3)3");

    @OriginalMember(owner = "runetek4.client!lj", name = "q", descriptor = "Lclient!na;")
    public static final JString GER_DROP = JString.parse("Fallen lassen");

    @OriginalMember(owner = "runetek4.client!uh", name = "O", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD130B = JString.parse("Benutzeroberfl-=che geladen)3");

    @OriginalMember(owner = "runetek4.client!pe", name = "q", descriptor = "Lclient!na;")
    public static final JString GER_MINISEPARATOR = JString.parse(": ");

    @OriginalMember(owner = "runetek4.client!pe", name = "d", descriptor = "Lclient!na;")
    public static final JString GER_CHATEFFECT1 = JString.parse("welle:");

    @OriginalMember(owner = "runetek4.client!si", name = "ib", descriptor = "Lclient!na;")
    public static final JString GER_CONTINUE = JString.parse("Weiter");

    @OriginalMember(owner = "runetek4.client!ic", name = "i", descriptor = "Lclient!na;")
    public static final JString GER_IGNORELISTFULL = JString.parse("Ihre Ignorieren)2Liste ist voll)1 Sie k-Onnen nur 100 Spieler darauf eintragen)3");

    @OriginalMember(owner = "runetek4.client!mf", name = "S", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD140 = JString.parse("Starte 3D)2Softwarebibliothek)3");

    @OriginalMember(owner = "runetek4.client!lh", name = "x", descriptor = "Lclient!na;")
    public static final JString GER_CHATEFFECT4 = JString.parse("scrollen:");

    @OriginalMember(owner = "runetek4.client!sk", name = "cb", descriptor = "Lclient!na;")
    public static final JString GER_EXAMINE = JString.parse("Untersuchen");

    @OriginalMember(owner = "runetek4.client!sk", name = "eb", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD45B = JString.parse("Musik)2Engine vorbereitet)3");

    @OriginalMember(owner = "runetek4.client!sd", name = "P", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD135B = JString.parse("Liste der Welten geladen");

    @OriginalMember(owner = "runetek4.client!vh", name = "i", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD30 = JString.parse("Verbindung mit Update)2Server)3)3)3");

    @OriginalMember(owner = "runetek4.client!re", name = "r", descriptor = "Lclient!na;")
    public static final JString GER_WALKHERE = JString.parse("Hierhin gehen");

    @OriginalMember(owner = "runetek4.client!qf", name = "U", descriptor = "Lclient!na;")
    public static final JString GER_CHOOSE_OPTION = JString.parse("W-=hlen Sie eine Option");

    @OriginalMember(owner = "runetek4.client!hj", name = "i", descriptor = "Lclient!na;")
    public static final JString GER_USE = JString.parse("Benutzen");

    @OriginalMember(owner = "runetek4.client!ql", name = "k", descriptor = "Lclient!na;")
    public static final JString GER_CHATEFFECT5 = JString.parse("gleiten:");

    @OriginalMember(owner = "runetek4.client!ok", name = "g", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD60 = JString.parse("Lade Titelbild )2 ");

    @OriginalMember(owner = "runetek4.client!gk", name = "a", descriptor = "Lclient!na;")
    public static final JString GER_CHATCOL10 = JString.parse("leuchten2:");

    @OriginalMember(owner = "runetek4.client!wd", name = "c", descriptor = "Lclient!na;")
    public static final JString GER_UNABLETOFIND = JString.parse("Spieler kann nicht gefunden werden: ");

    @OriginalMember(owner = "runetek4.client!og", name = "h", descriptor = "Lclient!na;")
    public static final JString GER_FRIENDLOGOUT = JString.parse(" loggt sich aus)3");

    @OriginalMember(owner = "runetek4.client!ml", name = "S", descriptor = "Lclient!na;")
    public static final JString GER_THOUSAND_SUFFIX = JString.parse("T");

    @OriginalMember(owner = "runetek4.client!va", name = "v", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD10B = JString.parse("Spielwelt erstellt)3");

    @OriginalMember(owner = "runetek4.client!mf", name = "q", descriptor = "Lclient!na;")
    public static final JString GER_OK = JString.parse("Okay");

    @OriginalMember(owner = "runetek4.client!ob", name = "c", descriptor = "Lclient!na;")
    public static final JString GER_SKILL = JString.parse("Fertigkeit: ");

    @OriginalMember(owner = "runetek4.client!wf", name = "h", descriptor = "Lclient!na;")
    public static final JString GER_MAINLOAD40B = JString.parse("Update)2Liste geladen)3");

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(Z)V")
    public static void switchToGerman() {
        CHATEFFECT1 = GER_CHATEFFECT1;
        MOREOPTIONS = GER_MOREOPTIONS;
        REMOVESOCIAL1 = GER_REMOVESOCIAL;
        MAINLOAD70B = GER_MAINLOAD70B;
        MAINLOAD50B = GER_MAINLOAD50B;
        REMOVEFRIEND = GER_REMOVEFRIEND;
        TRADEREQ = GER_TRADEREQ;
        CHATCOL8 = GER_CHATCOL8;
        FRIENDLISTFULL = GER_FRIENDLISTFULL;
        IGNORECANTADDSELF = GER_IGNORECANTADDSELF;
        CHECKING_FOR_UPDATES = GER_CHECKING_FOR_UPDATES;
        CHATCOL5 = GER_CHATCOL5;
        DROP = GER_DROP;
        FRIENDLOGIN = GER_FRIENDLOGIN;
        RATING = GER_RATING;
        THOUSAND_SHORT = GER_THOUSAND_SUFFIX;
        MAINLOAD135 = GER_MAINLOAD135;
        MINISEPARATOR = GER_MINISEPARATOR;
        CHATCOL2 = GER_CHATCOL2;
        CHATCOL9 = GER_CHATCOL9;
        MAINLOAD110B = GER_MAINLOAD110B;
        CHATCOL1 = GER_CHATCOL1;
        OK = GER_OK;
        GAME0_LOADING = GER_GAME0_LOADING;
        CHATCOL4 = GER_CHATCOL4;
        MAINLOAD10B = GER_MAINLOAD10B;
        REMOVESOCIAL2 = GER_REMOVESOCIAL;
        MAINLOAD50 = GER_MAINLOAD50;
        CANCEL = GER_CANCEL;
        CHATEFFECT3 = GER_CHATEFFECT3;
        IGNORELISTFULL = GER_IGNORELISTFULL;
        CONTINUE = GER_CONTINUE;
        LOADING = GER_LOADING;
        ATTACK = GER_ATTACK;
        MAINLOAD80B = GER_MAINLOAD80B;
        MAINLOAD90 = GER_MAINLOAD90;
        CHATCOL3 = GER_CHATCOL3;
        EXAMINE = GER_EXAMINE;
        CONLOST = GER_CONLOST;
        SKILL = GER_SKILL;
        MEMBERS_OBJECT = GER_MEMBERS_OBJECT;
        MAINLOAD135B = GER_MAINLOAD135B;
        REMOVEIGNORE = GER_REMOVEIGNORE;
        PLEASEWAIT = GER_PLEASEWAIT;
        FRIENDLOGOUT = GER_FRIENDLOGOUT;
        MAINLOAD140 = GER_MAINLOAD140;
        THOUSAND = GER_THOUSAND_SUFFIX;
        SELECT = GER_SELECT;
        CHATCOL10 = GER_CHATCOL10;
        TAKE = GER_TAKE;
        UNABLETOFIND = GER_UNABLETOFIND;
        MAINLOAD60 = GER_MAINLOAD60;
        MAINLOAD30 = GER_MAINLOAD30;
        WALKHERE = GER_WALKHERE;
        FACEHERE = GER_FACEHERE;
        MAINLOAD80 = GER_MAINLOAD80;
        MILLION_SHORT = GER_MILLION_SUFFIX;
        CHOOSE_OPTION = GER_CHOOSE_OPTION;
        MAINLOAD150B = GER_MAINLOAD150B;
        USE = GER_USE;
        MAINLOAD0 = GER_MAINLOAD0;
        ATTEMPT_TO_REESTABLISH = GER_ATTEMPT_TO_REESTABLISH;
        MAINLOAD130B = GER_MAINLOAD130B;
        CHATEFFECT5 = GER_CHATEFFECT5;
        MAINLOAD40B = GER_MAINLOAD40B;
        CHATCOL0 = GER_CHATCOL0;
        CHATCOL7 = GER_CHATCOL7;
        CHATCOL6 = GER_CHATCOL6;
        LOADINGDOTDOTDOT = GER_LOADINGDOTDOTDOT;
        MILLION = GER_MILLION_SUFFIX;
        MAINLOAD70 = GER_MAINLOAD70;
        MAINLOAD0B = GER_MAINLOAD0B;
        MAINLOAD60B = GER_MAINLOAD60B;
        CHATCOL11 = GER_CHATCOL11;
        FRIENDCANTADDSELF = GER_FRIENDCANTADDSELF;
        MAINLOAD120B = GER_MAINLOAD120B;
        IGNORELISTDUPE = GER_IGNORELISTDUPE;
        MAINLOAD30B = GER_MAINLOAD30B;
        LENT_ITEM_RETURN = GER_LENT_ITEM_RETURN;
        HIDDEN = GER_HIDDEN;
        CHATEFFECT2 = GER_CHATEFFECT2;
        MAINLOAD130 = GER_MAINLOAD130;
        FRIENDLISTDUPE = GER_FRIENDLISTDUPE;
        MAINLOAD65B = GER_MAINLOAD65B;
        CLOSE = GER_CLOSE;
        CHATEFFECT4 = GER_CHATEFFECT4;
        MAINLOAD90B = GER_MAINLOAD90B;
        MAINLOAD120 = GER_MAINLOAD120;
        MAINLOAD45B = GER_MAINLOAD45B;
        LEVEL = GER_LEVEL;
    }
    //endregion

    // TODO: unsorted
    //region French Translations
    @OriginalMember(owner = "runetek4.client!kc", name = "r", descriptor = "Lclient!na;")
    public static final JString FR_THOUSAND_SUFFIX = JString.parse("K");

    @OriginalMember(owner = "runetek4.client!qj", name = "g", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL1 = JString.parse("rouge:");

    @OriginalMember(owner = "runetek4.client!oe", name = "m", descriptor = "Lclient!na;")
    public static final JString FR_GAME0_LOADING = JString.parse("Chargement de RuneScape en cours )2 veuillez patienter)3)3)3");

    @OriginalMember(owner = "runetek4.client!ii", name = "j", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD130B = JString.parse("Interfaces charg-Bes");

    @OriginalMember(owner = "runetek4.client!pl", name = "d", descriptor = "Lclient!na;")
    public static final JString FR_RATING = JString.parse("classement ");

    @OriginalMember(owner = "runetek4.client!ol", name = "S", descriptor = "Lclient!na;")
    public static final JString FR_CONLOST = JString.parse("Connexion perdue)3");

    @OriginalMember(owner = "runetek4.client!ui", name = "nb", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL0 = JString.parse("jaune:");

    @OriginalMember(owner = "runetek4.client!fm", name = "T", descriptor = "Lclient!na;")
    public static final JString FR_MINISEPARATOR = JString.parse(" ");

    @OriginalMember(owner = "runetek4.client!fk", name = "c", descriptor = "Lclient!na;")
    public static final JString FR_CHECKING_FOR_UPDATES = JString.parse("V-Brification des mises -9 jour )2 ");

    @OriginalMember(owner = "runetek4.client!fk", name = "p", descriptor = "Lclient!na;")
    public static final JString FR_CHATEFFECT2 = JString.parse("ondulation2:");

    @OriginalMember(owner = "runetek4.client!fh", name = "V", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL2 = JString.parse("vert:");

    @OriginalMember(owner = "runetek4.client!fc", name = "g", descriptor = "Lclient!na;")
    public static final JString FR_USE = JString.parse("Utiliser");

    @OriginalMember(owner = "runetek4.client!ab", name = "m", descriptor = "Lclient!na;")
    public static final JString FR_MEMBERS_OBJECT = JString.parse("Objet d(Wabonn-Bs");

    @OriginalMember(owner = "runetek4.client!ah", name = "u", descriptor = "Lclient!na;")
    public static final JString FR_REMOVESOCIAL = JString.parse("Veuillez commencer par supprimer ");

    @OriginalMember(owner = "runetek4.client!bf", name = "A", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL10 = JString.parse("brillant2:");

    @OriginalMember(owner = "runetek4.client!bg", name = "n", descriptor = "Lclient!na;")
    public static final JString FR_FRIENDLISTFULL = JString.parse("Votre liste d(Wamis est pleine (X100 noms maximum pour la version gratuite et 200 pour les abonn-Bs(Y)3");

    @OriginalMember(owner = "runetek4.client!bm", name = "h", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD60B = JString.parse(",Mcran)2titre charg-B");

    @OriginalMember(owner = "runetek4.client!ca", name = "bb", descriptor = "Lclient!na;")
    public static final JString FR_EXAMINE = JString.parse("Examiner");

    @OriginalMember(owner = "runetek4.client!cd", name = "v", descriptor = "Lclient!na;")
    public static final JString FR_WALKHERE = JString.parse("Atteindre");

    @OriginalMember(owner = "runetek4.client!cj", name = "b", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL5 = JString.parse("blanc:");

    @OriginalMember(owner = "runetek4.client!cl", name = "P", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL11 = JString.parse("brillant3:");

    @OriginalMember(owner = "runetek4.client!cl", name = "R", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD65B = JString.parse(",Mcran)2titre ouvert");

    @OriginalMember(owner = "runetek4.client!cm", name = "e", descriptor = "Lclient!na;")
    public static final JString FR_FRIENDCANTADDSELF = JString.parse("Vous ne pouvez pas ajouter votre nom -9 votre liste d(Wamis)3");

    @OriginalMember(owner = "runetek4.client!cm", name = "i", descriptor = "Lclient!na;")
    public static final JString FR_SKILL = JString.parse("comp-Btence ");

    @OriginalMember(owner = "runetek4.client!cn", name = "x", descriptor = "Lclient!na;")
    public static final JString FR_IGNORECANTADDSELF = JString.parse("Vous ne pouvez pas ajouter votre nom -9 votre liste noire)3");

    @OriginalMember(owner = "runetek4.client!e", name = "vc", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD110B = JString.parse("Gestionnaire de saisie charg-B");

    @OriginalMember(owner = "runetek4.client!ef", name = "l", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD130 = JString.parse("Chargement des interfaces )2 ");

    @OriginalMember(owner = "runetek4.client!eg", name = "y", descriptor = "Lclient!na;")
    public static final JString FR_LENT_ITEM_RETURN = JString.parse("Jeter");

    @OriginalMember(owner = "runetek4.client!ej", name = "cb", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL7 = JString.parse("clignotant2:");

    @OriginalMember(owner = "runetek4.client!fb", name = "k", descriptor = "Lclient!na;")
    public static final JString FR_MILLION_SUFFIX = JString.parse("M");

    @OriginalMember(owner = "runetek4.client!rc", name = "D", descriptor = "Lclient!na;")
    public static final JString FR_FRIENDLOGOUT = JString.parse(" s(West d-Bconnect-B)3");

    @OriginalMember(owner = "runetek4.client!tm", name = "o", descriptor = "Lclient!na;")
    public static final JString FR_CANCEL = JString.parse("Annuler");

    @OriginalMember(owner = "runetek4.client!wa", name = "tb", descriptor = "Lclient!na;")
    public static final JString FR_CHATEFFECT4 = JString.parse("d-Broulement:");

    @OriginalMember(owner = "runetek4.client!wa", name = "H", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD50B = JString.parse("Polices charg-Bes");

    @OriginalMember(owner = "runetek4.client!il", name = "Q", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD90B = JString.parse("Textures charg-Bes");

    @OriginalMember(owner = "runetek4.client!il", name = "R", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD135B = JString.parse("Liste des serveurs charg-Be");

    @OriginalMember(owner = "runetek4.client!u", name = "d", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD150B = JString.parse("Librairie 3D d-Bmarr-Be");

    @OriginalMember(owner = "runetek4.client!kd", name = "tb", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD30B = JString.parse("Connect-B au serveur de mise -9 jour");

    @OriginalMember(owner = "runetek4.client!mg", name = "Y", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD0B = JString.parse("M-Bmoire attribu-Be");

    @OriginalMember(owner = "runetek4.client!km", name = "Ic", descriptor = "Lclient!na;")
    public static final JString FR_CHATEFFECT1 = JString.parse("ondulation:");

    @OriginalMember(owner = "runetek4.client!tl", name = "g", descriptor = "Lclient!na;")
    public static final JString FR_CHATEFFECT3 = JString.parse("tremblement:");

    @OriginalMember(owner = "runetek4.client!gm", name = "hb", descriptor = "Lclient!na;")
    public static final JString FR_FACEHERE = JString.parse("Regarder dans cette direction");

    @OriginalMember(owner = "runetek4.client!li", name = "e", descriptor = "Lclient!na;")
    public static final JString FR_PLEASEWAIT = JString.parse("Veuillez patienter)3)3)3");

    @OriginalMember(owner = "runetek4.client!gi", name = "o", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD135 = JString.parse("Chargement de la liste des serveurs");

    @OriginalMember(owner = "runetek4.client!jm", name = "t", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD120B = JString.parse("Module texte charg-B");

    @OriginalMember(owner = "runetek4.client!pb", name = "J", descriptor = "Lclient!na;")
    public static final JString FR_CHOOSE_OPTION = JString.parse("Choisir une option");

    @OriginalMember(owner = "runetek4.client!pb", name = "K", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD90 = JString.parse("Chargement des textures )2 ");

    @OriginalMember(owner = "runetek4.client!pb", name = "cb", descriptor = "Lclient!na;")
    public static final JString FR_LOADING = JString.parse("Chargement en cours)3 Veuillez patienter)3");

    @OriginalMember(owner = "runetek4.client!gi", name = "k", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL8 = JString.parse("clignotant3:");

    @OriginalMember(owner = "runetek4.client!q", name = "f", descriptor = "Lclient!na;")
    public static final JString FR_HIDDEN = JString.parse("Cach-B");

    @OriginalMember(owner = "runetek4.client!q", name = "g", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD50 = JString.parse("Chargement des polices )2 ");

    @OriginalMember(owner = "runetek4.client!gl", name = "c", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD10B = JString.parse("Monde de jeu cr-B-B");

    @OriginalMember(owner = "runetek4.client!me", name = "T", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL4 = JString.parse("violet:");

    @OriginalMember(owner = "runetek4.client!me", name = "kb", descriptor = "Lclient!na;")
    public static final JString FR_IGNORELISTFULL = JString.parse("Votre liste noire est pleine (X100 noms maximum(Y)3");

    @OriginalMember(owner = "runetek4.client!ud", name = "I", descriptor = "Lclient!na;")
    public static final JString FR_LOADINGDOTDOTDOT = JString.parse("Chargement en cours)3)3)3");

    @OriginalMember(owner = "runetek4.client!ud", name = "L", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL6 = JString.parse("clignotant1:");

    @OriginalMember(owner = "runetek4.client!ja", name = "o", descriptor = "Lclient!na;")
    public static final JString FR_TRADEREQ = JString.parse("voudrait faire un -Bchange avec vous)3");

    @OriginalMember(owner = "runetek4.client!ja", name = "p", descriptor = "Lclient!na;")
    public static final JString FR_FRIENDSLISTDUPE = JString.parse(" est d-Bj-9 dans votre liste d(Wamis)3");

    @OriginalMember(owner = "runetek4.client!ja", name = "b", descriptor = "Lclient!na;")
    public static final JString FR_SELECT = JString.parse("S-Blectionner");

    @OriginalMember(owner = "runetek4.client!ja", name = "d", descriptor = "Lclient!na;")
    public static final JString FR_LEVEL = JString.parse("niveau ");

    @OriginalMember(owner = "runetek4.client!md", name = "X", descriptor = "Lclient!na;")
    public static final JString FR_UNABLETOFIND = JString.parse("Impossible de trouver ");

    @OriginalMember(owner = "runetek4.client!md", name = "J", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD140 = JString.parse("D-Bmarrage de la librairie 3D");

    @OriginalMember(owner = "runetek4.client!md", name = "O", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD70B = JString.parse("Fichiers config charg-Bs");

    @OriginalMember(owner = "runetek4.client!md", name = "P", descriptor = "Lclient!na;")
    public static final JString FR_CLOSE = JString.parse("Fermer");

    @OriginalMember(owner = "runetek4.client!vj", name = "l", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD70 = JString.parse("Chargement des fichiers config )2 ");

    @OriginalMember(owner = "runetek4.client!sd", name = "J", descriptor = "Lclient!na;")
    public static final JString FR_ATTEMPT_TO_REESTABLISH = JString.parse("Veuillez patienter )2 tentative de r-Btablissement)3");

    @OriginalMember(owner = "runetek4.client!k", name = "n", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL9 = JString.parse("brillant1:");

    @OriginalMember(owner = "runetek4.client!k", name = "u", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD80 = JString.parse("Chargement des sprites )2 ");

    @OriginalMember(owner = "runetek4.client!ij", name = "n", descriptor = "Lclient!na;")
    public static final JString FR_REMOVEIGNORE = JString.parse(" de votre liste noire)3");

    @OriginalMember(owner = "runetek4.client!ic", name = "d", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD40B = JString.parse("Liste des mises -9 jour charg-Be");

    @OriginalMember(owner = "runetek4.client!ic", name = "m", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD80B = JString.parse("Sprites charg-Bs");

    @OriginalMember(owner = "runetek4.client!mf", name = "K", descriptor = "Lclient!na;")
    public static final JString FR_DROP = JString.parse("Poser");

    @OriginalMember(owner = "runetek4.client!rh", name = "q", descriptor = "Lclient!na;")
    public static final JString FR_OK = JString.parse("OK");

    @OriginalMember(owner = "runetek4.client!pk", name = "Q", descriptor = "Lclient!na;")
    public static final JString FR_CHATEFFECT5 = JString.parse("glissement:");

    @OriginalMember(owner = "runetek4.client!kh", name = "a", descriptor = "Lclient!na;")
    public static final JString FR_ATTACK = JString.parse("Attaquer");

    @OriginalMember(owner = "runetek4.client!tc", name = "b", descriptor = "Lclient!na;")
    public static final JString FR_TAKE = JString.parse("Prendre");

    @OriginalMember(owner = "runetek4.client!tc", name = "c", descriptor = "Lclient!na;")
    public static final JString FR_REMOVEFRIEND = JString.parse(" de votre liste d(Wamis)3");

    @OriginalMember(owner = "runetek4.client!hm", name = "lb", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD45B = JString.parse("Moteur son pr-Bpar-B");

    @OriginalMember(owner = "runetek4.client!kk", name = "a", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD0 = JString.parse("M-Bmoire en cours d(Wattribution");

    @OriginalMember(owner = "runetek4.client!wj", name = "k", descriptor = "Lclient!na;")
    public static final JString FR_CONTINUE = JString.parse("Continuer");

    @OriginalMember(owner = "runetek4.client!oi", name = "k", descriptor = "Lclient!na;")
    public static final JString FR_CHATCOL3 = JString.parse("cyan:");

    @OriginalMember(owner = "runetek4.client!sj", name = "x", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD30 = JString.parse("Connexion au serveur de mise -9 jour en cours");

    @OriginalMember(owner = "runetek4.client!la", name = "k", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOLAD120 = JString.parse("Chargement du module texte )2 ");

    @OriginalMember(owner = "runetek4.client!hi", name = "h", descriptor = "Lclient!na;")
    public static final JString FR_MAINLOAD60 = JString.parse("Chargement de l(W-Bcran)2titre )2 ");

    @OriginalMember(owner = "runetek4.client!gg", name = "jb", descriptor = "Lclient!na;")
    public static final JString FR_IGNORELISTDUPE = JString.parse(" est d-Bj-9 dans votre liste noire)3");

    @OriginalMember(owner = "runetek4.client!uf", name = "d", descriptor = "Lclient!na;")
    public static final JString FR_MOREOPTIONS = JString.parse(" autres options");

    @OriginalMember(owner = "runetek4.client!gg", name = "Q", descriptor = "Lclient!na;")
    public static final JString FR_FRIENDLOGIN = JString.parse(" s(West connect-B)3");

    @OriginalMember(owner = "runetek4.client!bb", name = "a", descriptor = "(B)V")
    public static void switchToFrench() {
        MAINLOAD70B = FR_MAINLOAD70B;
        MAINLOAD70 = FR_MAINLOAD70;
        CHATCOL4 = FR_CHATCOL4;
        CONTINUE = FR_CONTINUE;
        SKILL = FR_SKILL;
        EXAMINE = FR_EXAMINE;
        MAINLOAD120 = FR_MAINLOLAD120;
        CHATCOL10 = FR_CHATCOL10;
        MILLION_SHORT = FR_MILLION_SUFFIX;
        CHATCOL7 = FR_CHATCOL7;
        CHATEFFECT2 = FR_CHATEFFECT2;
        ATTACK = FR_ATTACK;
        FRIENDLISTFULL = FR_FRIENDLISTFULL;
        IGNORELISTDUPE = FR_IGNORELISTDUPE;
        MAINLOAD135B = FR_MAINLOAD135B;
        MAINLOAD110B = FR_MAINLOAD110B;
        CHATCOL1 = FR_CHATCOL1;
        CHATEFFECT1 = FR_CHATEFFECT1;
        MAINLOAD30 = FR_MAINLOAD30;
        FRIENDLISTDUPE = FR_FRIENDSLISTDUPE;
        CHATEFFECT3 = FR_CHATEFFECT3;
        MAINLOAD60 = FR_MAINLOAD60;
        MOREOPTIONS = FR_MOREOPTIONS;
        MAINLOAD65B = FR_MAINLOAD65B;
        MAINLOAD150B = FR_MAINLOAD150B;
        PLEASEWAIT = FR_PLEASEWAIT;
        FACEHERE = FR_FACEHERE;
        MILLION = FR_MILLION_SUFFIX;
        FRIENDLOGIN = FR_FRIENDLOGIN;
        CHATCOL3 = FR_CHATCOL3;
        MAINLOAD50 = FR_MAINLOAD50;
        CHATCOL8 = FR_CHATCOL8;
        OK = FR_OK;
        IGNORECANTADDSELF = FR_IGNORECANTADDSELF;
        MAINLOAD90B = FR_MAINLOAD90B;
        CHATCOL11 = FR_CHATCOL11;
        MAINLOAD140 = FR_MAINLOAD140;
        CHECKING_FOR_UPDATES = FR_CHECKING_FOR_UPDATES;
        ATTEMPT_TO_REESTABLISH = FR_ATTEMPT_TO_REESTABLISH;
        CHATEFFECT5 = FR_CHATEFFECT5;
        MAINLOAD0 = FR_MAINLOAD0;
        MAINLOAD45B = FR_MAINLOAD45B;
        MAINLOAD0B = FR_MAINLOAD0B;
        DROP = FR_DROP;
        MINISEPARATOR = FR_MINISEPARATOR;
        WALKHERE = FR_WALKHERE;
        UNABLETOFIND = FR_UNABLETOFIND;
        MAINLOAD60B = FR_MAINLOAD60B;
        REMOVEFRIEND = FR_REMOVEFRIEND;
        MAINLOAD80 = FR_MAINLOAD80;
        MEMBERS_OBJECT = FR_MEMBERS_OBJECT;
        MAINLOAD40B = FR_MAINLOAD40B;
        MAINLOAD50B = FR_MAINLOAD50B;
        TAKE = FR_TAKE;
        RATING = FR_RATING;
        LENT_ITEM_RETURN = FR_LENT_ITEM_RETURN;
        CLOSE = FR_CLOSE;
        TRADEREQ = FR_TRADEREQ;
        CHATCOL0 = FR_CHATCOL0;
        REMOVEIGNORE = FR_REMOVEIGNORE;
        CHATCOL9 = FR_CHATCOL9;
        CHOOSE_OPTION = FR_CHOOSE_OPTION;
        LOADINGDOTDOTDOT = FR_LOADINGDOTDOTDOT;
        CHATCOL5 = FR_CHATCOL5;
        MAINLOAD80B = FR_MAINLOAD80B;
        MAINLOAD130 = FR_MAINLOAD130;
        FRIENDCANTADDSELF = FR_FRIENDCANTADDSELF;
        REMOVESOCIAL1 = FR_REMOVESOCIAL;
        CANCEL = FR_CANCEL;
        MAINLOAD120B = FR_MAINLOAD120B;
        MAINLOAD10B = FR_MAINLOAD10B;
        THOUSAND = FR_THOUSAND_SUFFIX;
        LEVEL = FR_LEVEL;
        THOUSAND_SHORT = FR_THOUSAND_SUFFIX;
        SELECT = FR_SELECT;
        CONLOST = FR_CONLOST;
        IGNORELISTFULL = FR_IGNORELISTFULL;
        MAINLOAD130B = FR_MAINLOAD130B;
        GAME0_LOADING = FR_GAME0_LOADING;
        CHATCOL6 = FR_CHATCOL6;
        REMOVESOCIAL2 = FR_REMOVESOCIAL;
        CHATCOL2 = FR_CHATCOL2;
        HIDDEN = FR_HIDDEN;
        LOADING = FR_LOADING;
        MAINLOAD30B = FR_MAINLOAD30B;
        FRIENDLOGOUT = FR_FRIENDLOGOUT;
        MAINLOAD90 = FR_MAINLOAD90;
        USE = FR_USE;
        CHATEFFECT4 = FR_CHATEFFECT4;
        MAINLOAD135 = FR_MAINLOAD135;
    }

    @OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(II)V")
    public static void setLanguage(@OriginalArg(1) int arg0) {
        if (arg0 == 0) {
            return;
        }
        if (arg0 == 1) {
            switchToGerman();
        } else if (arg0 == 2) {
            switchToFrench();
        } else {
            throw new RuntimeException();
        }
    }
    //endregion
}
