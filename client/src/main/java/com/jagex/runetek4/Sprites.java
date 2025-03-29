package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Sprites {
    @OriginalMember(owner = "client!bg", name = "N", descriptor = "Lclient!na;")
    public static final JString MAPFUNCTION = JString.parse("mapfunction");
    @OriginalMember(owner = "client!bh", name = "s", descriptor = "Lclient!na;")
    public static final JString P11_FULL = JString.parse("p11_full");
    @OriginalMember(owner = "client!bh", name = "v", descriptor = "Lclient!na;")
    public static final JString FLOORSHADOWS = JString.parse("floorshadows");
    @OriginalMember(owner = "client!cb", name = "eb", descriptor = "Lclient!na;")
    public static final JString NAME_ICONS = JString.parse("name_icons");
    @OriginalMember(owner = "client!ee", name = "e", descriptor = "Lclient!na;")
    public static final JString P12_FULL = JString.parse("p12_full");
    @OriginalMember(owner = "client!fm", name = "eb", descriptor = "Lclient!na;")
    public static final JString B12_FULL = JString.parse("b12_full");
    @OriginalMember(owner = "client!gm", name = "db", descriptor = "Lclient!na;")
    public static final JString CROSS = JString.parse("cross");
    @OriginalMember(owner = "runetek4.client!mi", name = "T", descriptor = "[Lclient!ok;")
    public static IndexedSprite[] nameIcons;
    @OriginalMember(owner = "runetek4.client!l", name = "j", descriptor = "[Lclient!qf;")
    public static Sprite[] mapdots;
    @OriginalMember(owner = "runetek4.client!wh", name = "l", descriptor = "[Lclient!qf;")
    public static Sprite[] aClass3_Sub2_Sub1Array11;
    @OriginalMember(owner = "client!ci", name = "t", descriptor = "I")
    public static int anInt1016;
    @OriginalMember(owner = "client!runetek4.client", name = "W", descriptor = "I")
    public static int anInt1165;
    @OriginalMember(owner = "runetek4.client!tj", name = "d", descriptor = "[Lclient!ek;")
    public static SoftwareIndexedSprite[] floorShadows;

    @OriginalMember(owner = "runetek4.client!ja", name = "a", descriptor = "(Z)V")
    public static void clear() {
        Fonts.p12Full = null;
        Static213.aClass3_Sub2_Sub1Array8 = null;
        Static240.crossSprites = null;
        nameIcons = null;
        Fonts.p11FullSoftware = null;
        Static173.aClass3_Sub2_Sub1_Sub1Array9 = null;
        Static67.aClass3_Sub2_Sub1Array4 = null;
        Static149.aClass3_Sub2_Sub1Array7 = null;
        Static138.aClass3_Sub2_Sub1Array5 = null;
        Fonts.b12Full = null;
        Static106.aClass3_Sub2_Sub1_7 = null;
        mapdots = null;
        Fonts.p11Full = null;
        Static84.aClass3_Sub2_Sub1_4 = null;
        Static116.aClass3_Sub2_Sub1Array3 = null;
        Static277.aClass3_Sub2_Sub1Array12 = null;
        aClass3_Sub2_Sub1Array11 = null;
        Static219.aClass3_Sub2_Sub1Array9 = null;
        Static241.aClass36Array16 = null;
    }

    @OriginalMember(owner = "client!cg", name = "a", descriptor = "(ILclient!ve;)V")
    public static void load(@OriginalArg(1) Js5 arg0) {
        Static173.aClass3_Sub2_Sub1_Sub1Array9 = SpriteLoader.loadSoftwareSprites(Static280.anInt5900, arg0);
        Static213.aClass3_Sub2_Sub1Array8 = SpriteLoader.loadAlphaSprites(Static131.anInt3261, arg0);
        Static116.aClass3_Sub2_Sub1Array3 = SpriteLoader.loadAlphaSprites(anInt1165, arg0);
        Static219.aClass3_Sub2_Sub1Array9 = SpriteLoader.loadAlphaSprites(Static214.anInt5579, arg0);
        Static138.aClass3_Sub2_Sub1Array5 = SpriteLoader.loadAlphaSprites(Static34.anInt1049, arg0);
        aClass3_Sub2_Sub1Array11 = SpriteLoader.loadAlphaSprites(Class6.anInt4741, arg0);
        Static149.aClass3_Sub2_Sub1Array7 = SpriteLoader.loadAlphaSprites(Static149.anInt3551, arg0);
        Static84.aClass3_Sub2_Sub1_4 = SpriteLoader.loadSprites(Static19.anInt647, arg0);
        Static240.crossSprites = SpriteLoader.method2580(anInt1016, arg0);
        mapdots = SpriteLoader.method2580(Static78.anInt2147, arg0);
        Static241.aClass36Array16 = SpriteLoader.loadIndexedSprites(arg0, Static124.anInt3083);
        nameIcons = SpriteLoader.loadIndexedSprites(arg0, FloorUnderlayTypeList.anInt5057);
        Fonts.p11Full.setNameIcons(nameIcons, null);
        Fonts.p12Full.setNameIcons(nameIcons, null);
        Fonts.b12Full.setNameIcons(nameIcons, null);
        if (GlRenderer.enabled) {
            floorShadows = SpriteLoader.loadSoftwareIndexedSprites(Static84.anInt2257, arg0);
            for (@Pc(101) int local101 = 0; local101 < floorShadows.length; local101++) {
                floorShadows[local101].trim();
            }
        }
        @Pc(124) SoftwareSprite local124 = SpriteLoader.loadSoftwareSprite(0, arg0, Static163.anInt3962);
        local124.trim();
        if (GlRenderer.enabled) {
            Static106.aClass3_Sub2_Sub1_7 = new GlSprite(local124);
        } else {
            Static106.aClass3_Sub2_Sub1_7 = local124;
        }
        @Pc(143) SoftwareSprite[] local143 = SpriteLoader.loadSoftwareSprites(Static128.anInt3143, arg0);
        @Pc(145) int local145;
        for (local145 = 0; local145 < local143.length; local145++) {
            local143[local145].trim();
        }
        if (GlRenderer.enabled) {
            Static277.aClass3_Sub2_Sub1Array12 = new Sprite[local143.length];
            for (local145 = 0; local145 < local143.length; local145++) {
                Static277.aClass3_Sub2_Sub1Array12[local145] = new GlSprite(local143[local145]);
            }
        } else {
            Static277.aClass3_Sub2_Sub1Array12 = local143;
        }
        @Pc(196) int local196 = (int) ((double) 21 * Math.random()) - 10;
        local145 = (int) (Math.random() * 21.0D) - 10;
        @Pc(210) int local210 = (int) (Math.random() * 21.0D) - 10;
        @Pc(217) int local217 = (int) (Math.random() * 41.0D) - 20;
        @Pc(219) int local219;
        for (local219 = 0; local219 < Static173.aClass3_Sub2_Sub1_Sub1Array9.length; local219++) {
            Static173.aClass3_Sub2_Sub1_Sub1Array9[local219].method315(local145 + local217, local217 + local196, local217 + local210);
        }
        if (GlRenderer.enabled) {
            Static67.aClass3_Sub2_Sub1Array4 = new Sprite[Static173.aClass3_Sub2_Sub1_Sub1Array9.length];
            for (local219 = 0; local219 < Static173.aClass3_Sub2_Sub1_Sub1Array9.length; local219++) {
                Static67.aClass3_Sub2_Sub1Array4[local219] = new GlSprite(Static173.aClass3_Sub2_Sub1_Sub1Array9[local219]);
            }
        } else {
            Static67.aClass3_Sub2_Sub1Array4 = Static173.aClass3_Sub2_Sub1_Sub1Array9;
        }
    }

    @OriginalMember(owner = "runetek4.client!g", name = "a", descriptor = "(ILclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        Static166.p11FullId = arg0.getGroupId(P11_FULL);
        Static130.p12FullId = arg0.getGroupId(P12_FULL);
        Static73.b12FullId = arg0.getGroupId(B12_FULL);
        Static280.anInt5900 = arg0.getGroupId(MAPFUNCTION);
        Static131.anInt3261 = arg0.getGroupId(Static219.HITMARKS);
        anInt1165 = arg0.getGroupId(Static260.HITBAR_DEFAULT);
        Static214.anInt5579 = arg0.getGroupId(Static123.HEADICONS_PK);
        Static34.anInt1049 = arg0.getGroupId(Static228.HEADICONS_PRAYER);
        Class6.anInt4741 = arg0.getGroupId(Static98.HINT_HEADICONS);
        Static149.anInt3551 = arg0.getGroupId(Static189.HINT_MAPMARKERS);
        Static19.anInt647 = arg0.getGroupId(Static259.MAPFLAGS);
        anInt1016 = arg0.getGroupId(CROSS);
        Static78.anInt2147 = arg0.getGroupId(Static189.MAPDOTS);
        Static124.anInt3083 = arg0.getGroupId(Static250.SCROLLBAR);
        FloorUnderlayTypeList.anInt5057 = arg0.getGroupId(NAME_ICONS);
        Static84.anInt2257 = arg0.getGroupId(FLOORSHADOWS);
        Static163.anInt3962 = arg0.getGroupId(Static280.COMPASS);
        Static128.anInt3143 = arg0.getGroupId(Static5.HINT_MAPEDGE);
    }

    @OriginalMember(owner = "client!cd", name = "a", descriptor = "(Lclient!ve;B)I")
    public static int getReady(@OriginalArg(0) Js5 arg0) {
        @Pc(5) int local5 = 0;
        if (arg0.isFileReady(Static280.anInt5900)) {
            local5++;
        }
        if (arg0.isFileReady(Static131.anInt3261)) {
            local5++;
        }
        if (arg0.isFileReady(anInt1165)) {
            local5++;
        }
        if (arg0.isFileReady(Static214.anInt5579)) {
            local5++;
        }
        if (arg0.isFileReady(Static34.anInt1049)) {
            local5++;
        }
        if (arg0.isFileReady(Class6.anInt4741)) {
            local5++;
        }
        if (arg0.isFileReady(Static149.anInt3551)) {
            local5++;
        }
        if (arg0.isFileReady(Static19.anInt647)) {
            local5++;
        }
        if (arg0.isFileReady(anInt1016)) {
            local5++;
        }
        if (arg0.isFileReady(Static78.anInt2147)) {
            local5++;
        }
        if (arg0.isFileReady(Static124.anInt3083)) {
            local5++;
        }
        if (arg0.isFileReady(FloorUnderlayTypeList.anInt5057)) {
            local5++;
        }
        if (arg0.isFileReady(Static84.anInt2257)) {
            local5++;
        }
        if (arg0.isFileReady(Static163.anInt3962)) {
            local5++;
        }
        if (arg0.isFileReady(Static128.anInt3143)) {
            local5++;
        }
        return local5;
    }

    @OriginalMember(owner = "runetek4.client!f", name = "h", descriptor = "(I)I")
    public static int total() {
        return 15;
    }
}
