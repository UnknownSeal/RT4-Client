package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Flames {
    @OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(Lclient!ve;I)Z")
    public static boolean isReady(@OriginalArg(0) Js5 arg0) {
        return arg0.method4506(Static138.anInt3443);
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(Z)V")
    public static void update() {
        Static250.anInt5434++;
    }

    @OriginalMember(owner = "runetek4.client!gg", name = "a", descriptor = "(ILclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        Static138.anInt3443 = arg0.getGroupId(Static12.aClass100_73);
    }

    @OriginalMember(owner = "runetek4.client!s", name = "b", descriptor = "(III)V")
    public static void render(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        if (Static250.anInt5434 > 0) {
            Static267.method4528(Static250.anInt5434);
            Static250.anInt5434 = 0;
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
                    Static167.aClass3_Sub2_Sub1_Sub1_3.pixels[local26++] = local65;
                } else {
                    @Pc(76) int local76 = local70 + 18;
                    if (local76 > 255) {
                        local76 = 255;
                    }
                    @Pc(89) int local89 = 256 - local70 - 18;
                    if (local89 > 255) {
                        local89 = 255;
                    }
                    local70 = Static96.anIntArray235[local70];
                    Static167.aClass3_Sub2_Sub1_Sub1_3.pixels[local26++] = (local89 * (local65 & 0xFF00FF) + (local70 & 0xFF00FF) * local76 & 0xFF00FF00) + ((local70 & 0xFF00) * local76 + ((local65 & 0xFF00) * local89) & 0xFF0000) >> 8;
                }
            }
            for (local55 = 0; local55 < local43; local55++) {
                Static167.aClass3_Sub2_Sub1_Sub1_3.pixels[local26++] = Rasterizer.destinationPixels[arg0 + local24++];
            }
            local24 += Rasterizer.destinationWidth - 128;
        }
        if (GlRenderer.enabled) {
            Static46.method1178(Static167.aClass3_Sub2_Sub1_Sub1_3.pixels, arg0, arg1, Static167.aClass3_Sub2_Sub1_Sub1_3.width, Static167.aClass3_Sub2_Sub1_Sub1_3.height);
        } else {
            Static167.aClass3_Sub2_Sub1_Sub1_3.render(arg0, arg1);
        }
    }
}
