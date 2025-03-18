package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Flames {
    @OriginalMember(owner = "client!bb", name = "m", descriptor = "Lclient!na;")
    public static final JString RUNES = JString.parse("runes");
    @OriginalMember(owner = "client!km", name = "Yc", descriptor = "I")
    public static int runesID;
    @OriginalMember(owner = "client!uf", name = "e", descriptor = "I")
    public static int flameCycle = 0;
    @OriginalMember(owner = "client!nd", name = "y", descriptor = "Lclient!mm;")
    public static SoftwareSprite imageFlames;
    @OriginalMember(owner = "client!hh", name = "r", descriptor = "[I")
    public static int[] flameGradient;
    @OriginalMember(owner = "client!jj", name = "e", descriptor = "I")
    public static int flameOffset = 0;
    @OriginalMember(owner = "client!t", name = "G", descriptor = "[Lclient!ek;")
    public static SoftwareIndexedSprite[] runes;

    @OriginalMember(owner = "client!sk", name = "a", descriptor = "(Lclient!ve;I)Z")
    public static boolean isReady(@OriginalArg(0) Js5 arg0) {
        return arg0.method4506(runesID);
    }

    @OriginalMember(owner = "client!wa", name = "a", descriptor = "(Z)V")
    public static void update() {
        flameCycle++;
    }

    @OriginalMember(owner = "client!gg", name = "a", descriptor = "(ILclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        runesID = arg0.getGroupId(RUNES);
    }

    @OriginalMember(owner = "client!s", name = "b", descriptor = "(III)V")
    public static void render(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        if (flameCycle > 0) {
            method4528(flameCycle);
            flameCycle = 0;
        }
        @Pc(20) int local20 = 0;
        @Pc(24) int local24 = Rasterizer.destinationWidth * arg1;
        @Pc(26) int local26 = 0;
        for (@Pc(28) int local28 = 1; local28 < 255; local28++) {
            @Pc(43) int local43 = (256 - local28) * Static35.anIntArray83[local28] / 256;
            if (local43 < 0) {
                local43 = 0;
            }
            local20 += local43;
            @Pc(55) int local55;
            for (local55 = local43; local55 < 128; local55++) {
                @Pc(65) int local65 = Rasterizer.destinationPixels[local24++ + arg0];
                @Pc(70) int local70 = Static216.anIntArray188[local20++];
                if (local70 == 0) {
                    imageFlames.pixels[local26++] = local65;
                } else {
                    @Pc(76) int local76 = local70 + 18;
                    if (local76 > 255) {
                        local76 = 255;
                    }
                    @Pc(89) int local89 = 256 - local70 - 18;
                    if (local89 > 255) {
                        local89 = 255;
                    }
                    local70 = flameGradient[local70];
                    imageFlames.pixels[local26++] = (local89 * (local65 & 0xFF00FF) + (local70 & 0xFF00FF) * local76 & 0xFF00FF00) + ((local70 & 0xFF00) * local76 + ((local65 & 0xFF00) * local89) & 0xFF0000) >> 8;
                }
            }
            for (local55 = 0; local55 < local43; local55++) {
                imageFlames.pixels[local26++] = Rasterizer.destinationPixels[arg0 + local24++];
            }
            local24 += Rasterizer.destinationWidth - 128;
        }
        if (GlRenderer.enabled) {
            GlRaster.render(imageFlames.pixels, arg0, arg1, imageFlames.width, imageFlames.height);
        } else {
            imageFlames.render(arg0, arg1);
        }
    }

    @OriginalMember(owner = "client!vl", name = "a", descriptor = "(II)V")
    public static void method4528(@OriginalArg(0) int arg0) {
        if (arg0 > 256) {
            arg0 = 256;
        }
        if (arg0 > 10) {
            arg0 = 10;
        }
        flameOffset += arg0 * 128;
        @Pc(40) int local40;
        if (Static270.anIntArray562.length < flameOffset) {
            flameOffset -= Static270.anIntArray562.length;
            local40 = (int) (Math.random() * 12.0D);
            setRune(runes[local40]);
        }
        local40 = 0;
        @Pc(54) int local54 = (256 - arg0) * 128;
        @Pc(58) int local58 = arg0 * 128;
        @Pc(60) int local60;
        @Pc(89) int local89;
        for (local60 = 0; local60 < local54; local60++) {
            local89 = Static216.anIntArray188[local40 + local58] - arg0 * Static270.anIntArray562[Static270.anIntArray562.length - 1 & flameOffset + local40] / 6;
            if (local89 < 0) {
                local89 = 0;
            }
            Static216.anIntArray188[local40++] = local89;
        }
        @Pc(117) int local117;
        @Pc(125) int local125;
        for (local60 = 256 - arg0; local60 < 256; local60++) {
            local89 = local60 * 128;
            for (local117 = 0; local117 < 128; local117++) {
                local125 = (int) (Math.random() * 100.0D);
                if (local125 < 50 && local117 > 10 && local117 < 118) {
                    Static216.anIntArray188[local117 + local89] = 255;
                } else {
                    Static216.anIntArray188[local117 + local89] = 0;
                }
            }
        }
        for (local60 = 0; local60 < 256 - arg0; local60++) {
            Static35.anIntArray83[local60] = Static35.anIntArray83[local60 + arg0];
        }
        for (local60 = 256 - arg0; local60 < 256; local60++) {
            Static35.anIntArray83[local60] = (int) (Math.sin((double) Static1.anInt6 / 14.0D) * 16.0D + Math.sin((double) Static1.anInt6 / 15.0D) * 14.0D + Math.sin((double) Static1.anInt6 / 16.0D) * 12.0D);
            Static1.anInt6++;
        }
        Static226.anInt5084 += arg0;
        local60 = (arg0 + (client.loop & 0x1)) / 2;
        if (local60 <= 0) {
            return;
        }
        for (local89 = 0; local89 < Static226.anInt5084; local89++) {
            local117 = (int) (Math.random() * 124.0D) + 2;
            local125 = (int) (Math.random() * 128.0D) + 128;
            Static216.anIntArray188[local117 + (local125 << 7)] = 192;
        }
        Static226.anInt5084 = 0;
        @Pc(290) int local290;
        for (local89 = 0; local89 < 256; local89++) {
            local125 = local89 * 128;
            local117 = 0;
            for (local290 = -local60; local290 < 128; local290++) {
                if (local60 + local290 < 128) {
                    local117 += Static216.anIntArray188[local125 + local290 + local60];
                }
                if (local290 - local60 - 1 >= 0) {
                    local117 -= Static216.anIntArray188[local290 + local125 - local60 - 1];
                }
                if (local290 >= 0) {
                    Static103.anIntArray254[local290 + local125] = local117 / (local60 * 2 + 1);
                }
            }
        }
        for (local89 = 0; local89 < 128; local89++) {
            local117 = 0;
            for (local125 = -local60; local125 < 256; local125++) {
                local290 = local125 * 128;
                if (local125 + local60 < 256) {
                    local117 += Static103.anIntArray254[local60 * 128 + local89 + local290];
                }
                if (local125 - local60 - 1 >= 0) {
                    local117 -= Static103.anIntArray254[local89 + local290 - (local60 + 1) * 128];
                }
                if (local125 >= 0) {
                    Static216.anIntArray188[local290 + local89] = local117 / (local60 * 2 + 1);
                }
            }
        }
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(BLclient!ek;)V")
    public static void setRune(@OriginalArg(1) SoftwareIndexedSprite arg0) {
        @Pc(5) int local5;
        for (local5 = 0; local5 < Static270.anIntArray562.length; local5++) {
            Static270.anIntArray562[local5] = 0;
        }
        @Pc(36) int local36;
        for (local5 = 0; local5 < 5000; local5++) {
            local36 = (int) ((double) 256 * Math.random() * 128.0D);
            Static270.anIntArray562[local36] = (int) (Math.random() * 284.0D);
        }
        @Pc(66) int local66;
        @Pc(76) int local76;
        for (local5 = 0; local5 < 20; local5++) {
            for (local36 = 1; local36 < 255; local36++) {
                for (local66 = 1; local66 < 127; local66++) {
                    local76 = local66 + (local36 << 7);
                    Static263.anIntArray516[local76] = (Static270.anIntArray562[local76 + 128] + Static270.anIntArray562[local76 - 1] + Static270.anIntArray562[local76 + 1] + Static270.anIntArray562[local76 + -128]) / 4;
                }
            }
            @Pc(113) int[] local113 = Static270.anIntArray562;
            Static270.anIntArray562 = Static263.anIntArray516;
            Static263.anIntArray516 = local113;
        }
        if (arg0 == null) {
            return;
        }
        local5 = 0;
        for (local36 = 0; local36 < arg0.anInt4278; local36++) {
            for (local66 = 0; local66 < arg0.anInt4270; local66++) {
                if (arg0.aByteArray18[local5++] != 0) {
                    local76 = arg0.anInt4280 + local66 + 16;
                    @Pc(162) int local162 = arg0.anInt4273 + local36 + 16;
                    @Pc(169) int local169 = local76 + (local162 << 7);
                    Static270.anIntArray562[local169] = 0;
                }
            }
        }
    }
}
