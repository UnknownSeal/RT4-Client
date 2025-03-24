package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SpriteLoader {
    @OriginalMember(owner = "runetek4.client!wf", name = "b", descriptor = "[I")
    public static int[] xOffsets;
    @OriginalMember(owner = "runetek4.client!wa", name = "z", descriptor = "[I")
    public static int[] yOffsets;
    @OriginalMember(owner = "runetek4.client!uj", name = "x", descriptor = "[I")
    public static int[] innerWidths;
    @OriginalMember(owner = "client!ca", name = "Y", descriptor = "[I")
    public static int[] innerHeights;
    @OriginalMember(owner = "client!ah", name = "l", descriptor = "[[B")
    public static byte[][] pixels;

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIBLclient!ve;)Lclient!qf;")
    public static Sprite loadSprites(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1) {
        return decode(arg1, 0, arg0) ? Static82.method1764() : null;
    }

    @OriginalMember(owner = "runetek4.client!gd", name = "a", descriptor = "(ILclient!ve;II)Lclient!mm;")
    public static SoftwareSprite loadSoftwareSprite(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1, @OriginalArg(3) int arg2) {
        return decode(arg1, arg0, arg2) ? Static196.method3537() : null;
    }

    @OriginalMember(owner = "runetek4.client!ml", name = "a", descriptor = "(BILclient!ve;)[Lclient!ek;")
    public static SoftwareIndexedSprite[] loadSoftwareIndexedSpritesAutoDetect(@OriginalArg(1) int arg0, @OriginalArg(2) Js5 arg1) {
        return Static254.method4346(arg1, arg0) ? Static121.method2406() : null;
    }

    @OriginalMember(owner = "client!g", name = "a", descriptor = "(B)V")
    public static void clear() {
        innerWidths = null;
        yOffsets = null;
        innerHeights = null;
        pixels = null;
        xOffsets = null;
        Static259.anIntArray513 = null;
    }

    @OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(Lclient!ve;III)Z")
    public static boolean decode(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(9) byte[] local9 = arg0.getfile(arg2, arg1);
        if (local9 == null) {
            return false;
        } else {
            Static84.method1770(local9);
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IZILclient!ve;)Lclient!ek;")
    public static SoftwareIndexedSprite loadSoftwareIndexedSprite(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
        return decode(arg1, 0, arg0) ? Static134.method2619() : null;
    }
}
