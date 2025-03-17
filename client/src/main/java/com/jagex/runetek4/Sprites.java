package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Sprites {
    @OriginalMember(owner = "runetek4.client!mi", name = "T", descriptor = "[Lclient!ok;")
    public static IndexedSprite[] nameIcons;

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
        Static139.aClass3_Sub2_Sub1Array6 = null;
        Fonts.p11Full = null;
        Static84.aClass3_Sub2_Sub1_4 = null;
        Static116.aClass3_Sub2_Sub1Array3 = null;
        Static277.aClass3_Sub2_Sub1Array12 = null;
        Static276.aClass3_Sub2_Sub1Array11 = null;
        Static219.aClass3_Sub2_Sub1Array9 = null;
        Static241.aClass36Array16 = null;
    }

    @OriginalMember(owner = "client!cg", name = "a", descriptor = "(ZILclient!ve;I)[Lclient!ek;")
    private static SoftwareIndexedSprite[] method837(@OriginalArg(1) int arg0, @OriginalArg(2) Js5 arg1) {
        return Static234.method4016(arg1, 0, arg0) ? Static121.method2406() : null;
    }

    @OriginalMember(owner = "client!cg", name = "a", descriptor = "(ILclient!ve;)V")
    public static void load(@OriginalArg(1) Js5 arg0) {
        Static173.aClass3_Sub2_Sub1_Sub1Array9 = Static265.method4523(Static280.anInt5900, arg0);
        Static213.aClass3_Sub2_Sub1Array8 = Static209.method3708(Static131.anInt3261, arg0);
        Static116.aClass3_Sub2_Sub1Array3 = Static209.method3708(Static36.anInt1165, arg0);
        Static219.aClass3_Sub2_Sub1Array9 = Static209.method3708(Static214.anInt5579, arg0);
        Static138.aClass3_Sub2_Sub1Array5 = Static209.method3708(Static34.anInt1049, arg0);
        Static276.aClass3_Sub2_Sub1Array11 = Static209.method3708(Class6.anInt4741, arg0);
        Static149.aClass3_Sub2_Sub1Array7 = Static209.method3708(Static149.anInt3551, arg0);
        Static84.aClass3_Sub2_Sub1_4 = SpriteLoader.loadSprites(Static19.anInt647, arg0);
        Static240.crossSprites = Static131.method2580(Static32.anInt1016, arg0);
        Static139.aClass3_Sub2_Sub1Array6 = Static131.method2580(Static78.anInt2147, arg0);
        Static241.aClass36Array16 = Static146.method2749(arg0, Static124.anInt3083);
        nameIcons = Static146.method2749(arg0, FluTypeList.anInt5057);
        Fonts.p11Full.setNameIcons(nameIcons, null);
        Fonts.p12Full.setNameIcons(nameIcons, null);
        Fonts.b12Full.setNameIcons(nameIcons, null);
        if (GlRenderer.enabled) {
            Static242.aClass36_Sub1Array2 = method837(Static84.anInt2257, arg0);
            for (@Pc(101) int local101 = 0; local101 < Static242.aClass36_Sub1Array2.length; local101++) {
                Static242.aClass36_Sub1Array2[local101].method1396();
            }
        }
        @Pc(124) SoftwareSprite local124 = SpriteLoader.loadSoftwareSprite(0, arg0, Static163.anInt3962);
        local124.method311();
        if (GlRenderer.enabled) {
            Static106.aClass3_Sub2_Sub1_7 = new GlSprite(local124);
        } else {
            Static106.aClass3_Sub2_Sub1_7 = local124;
        }
        @Pc(143) SoftwareSprite[] local143 = Static265.method4523(Static128.anInt3143, arg0);
        @Pc(145) int local145;
        for (local145 = 0; local145 < local143.length; local145++) {
            local143[local145].method311();
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
        Static166.anInt4049 = arg0.getGroupId(Static18.P11_FULL);
        Static130.anInt3161 = arg0.getGroupId(Static55.P12_FULL);
        Static73.anInt2077 = arg0.getGroupId(Static73.B12_FULL);
        Static280.anInt5900 = arg0.getGroupId(Static17.MAPFUNCTION);
        Static131.anInt3261 = arg0.getGroupId(Static219.HITMARKS);
        Static36.anInt1165 = arg0.getGroupId(Static260.HITBAR_DEFAULT);
        Static214.anInt5579 = arg0.getGroupId(Static123.HEADICONS_PK);
        Static34.anInt1049 = arg0.getGroupId(Static228.HEADICONS_PRAYER);
        Class6.anInt4741 = arg0.getGroupId(Static98.HINT_HEADICONS);
        Static149.anInt3551 = arg0.getGroupId(Static189.HINT_MAPMARKERS);
        Static19.anInt647 = arg0.getGroupId(Static259.MAPFLAGS);
        Static32.anInt1016 = arg0.getGroupId(Static86.CROSS);
        Static78.anInt2147 = arg0.getGroupId(Static189.MAPDOTS);
        Static124.anInt3083 = arg0.getGroupId(Static250.SCROLLBAR);
        FluTypeList.anInt5057 = arg0.getGroupId(Static27.NAME_ICONS);
        Static84.anInt2257 = arg0.getGroupId(Static18.FLOORSHADOWS);
        Static163.anInt3962 = arg0.getGroupId(Static280.COMPASS);
        Static128.anInt3143 = arg0.getGroupId(Static5.HINT_MAPEDGE);
    }

    @OriginalMember(owner = "client!cd", name = "a", descriptor = "(Lclient!ve;B)I")
    public static int getReady(@OriginalArg(0) Js5 arg0) {
        @Pc(5) int local5 = 0;
        if (arg0.method4506(Static280.anInt5900)) {
            local5++;
        }
        if (arg0.method4506(Static131.anInt3261)) {
            local5++;
        }
        if (arg0.method4506(Static36.anInt1165)) {
            local5++;
        }
        if (arg0.method4506(Static214.anInt5579)) {
            local5++;
        }
        if (arg0.method4506(Static34.anInt1049)) {
            local5++;
        }
        if (arg0.method4506(Class6.anInt4741)) {
            local5++;
        }
        if (arg0.method4506(Static149.anInt3551)) {
            local5++;
        }
        if (arg0.method4506(Static19.anInt647)) {
            local5++;
        }
        if (arg0.method4506(Static32.anInt1016)) {
            local5++;
        }
        if (arg0.method4506(Static78.anInt2147)) {
            local5++;
        }
        if (arg0.method4506(Static124.anInt3083)) {
            local5++;
        }
        if (arg0.method4506(FluTypeList.anInt5057)) {
            local5++;
        }
        if (arg0.method4506(Static84.anInt2257)) {
            local5++;
        }
        if (arg0.method4506(Static163.anInt3962)) {
            local5++;
        }
        if (arg0.method4506(Static128.anInt3143)) {
            local5++;
        }
        return local5;
    }

    @OriginalMember(owner = "runetek4.client!f", name = "h", descriptor = "(I)I")
    public static int total() {
        return 15;
    }
}
