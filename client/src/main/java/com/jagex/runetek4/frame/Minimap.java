package com.jagex.runetek4.frame;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Minimap {
    @OriginalMember(owner = "runetek4.client!wc", name = "h", descriptor = "[[I")
    public static final int[][] anIntArrayArray46 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };
    @OriginalMember(owner = "runetek4.client!ke", name = "T", descriptor = "[[I")
    public static final int[][] anIntArrayArray24 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };

    @OriginalMember(owner = "runetek4.client!em", name = "a", descriptor = "(Lclient!be;Lclient!qf;IIIBI)V")
    public static void drawOnMinimap(@OriginalArg(0) Component arg0, @OriginalArg(1) Sprite sprite, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
        if (sprite == null) {
            return;
        }
        @Pc(21) int local21 = arg3 * arg3 + arg2 * arg2;
        @Pc(27) int local27 = Static59.minimapAnticheatAngle + Static57.orbitCameraYaw & 0x7FF;
        @Pc(39) int local39 = Math.max(arg0.anInt445 / 2, arg0.anInt459 / 2) + 10;
        if (local39 * local39 < local21) {
            return;
        }
        @Pc(50) int local50 = MathUtils.anIntArray223[local27];
        @Pc(58) int local58 = local50 * 256 / (Static273.minimapZoom + 256);
        @Pc(62) int local62 = MathUtils.anIntArray225[local27];
        @Pc(70) int local70 = local62 * 256 / (Static273.minimapZoom + 256);
        @Pc(81) int local81 = local58 * arg2 + arg3 * local70 >> 16;
        @Pc(92) int local92 = local70 * arg2 - arg3 * local58 >> 16;
        if (GlRenderer.enabled) {
            ((GlSprite) sprite).method1425(arg0.anInt445 / 2 + arg5 + local81 - sprite.anInt1860 / 2, arg0.anInt459 / 2 + arg4 - (local92 + sprite.anInt1866 / 2), (GlSprite) arg0.method489(false));
        } else {
            ((ImageRGB) sprite).drawImage(arg0.anInt445 / 2 + arg5 + local81 - sprite.anInt1860 / 2, -(sprite.anInt1866 / 2) + arg0.anInt459 / 2 + arg4 + -local92, arg0.anIntArray37, arg0.anIntArray45);
        }
    }
}
