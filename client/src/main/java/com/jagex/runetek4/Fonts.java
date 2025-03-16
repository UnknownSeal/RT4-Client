package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

public class Fonts {
    @OriginalMember(owner = "runetek4.client!wl", name = "q", descriptor = "Lclient!rk;")
    public static Font b12Full;
    @OriginalMember(owner = "runetek4.client!vj", name = "j", descriptor = "Lclient!dd;")
    public static SoftwareFont p11FullSoftware;

    @OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(Lclient!ve;ILclient!ve;)V")
    public static void load(@OriginalArg(0) Js5 arg0, @OriginalArg(2) Js5 arg1) {
        Static114.aClass3_Sub2_Sub9_42 = Static127.getFont(Static166.anInt4049, arg1, arg0);
        if (GlRenderer.enabled) {
            p11FullSoftware = Static122.method2412(Static166.anInt4049, arg0, arg1);
        } else {
            p11FullSoftware = (SoftwareFont) Static114.aClass3_Sub2_Sub9_42;
        }
        Static215.aClass3_Sub2_Sub9_32 = Static127.getFont(Static130.anInt3161, arg1, arg0);
        b12Full = Static127.getFont(Static73.anInt2077, arg1, arg0);
    }

    @OriginalMember(owner = "runetek4.client!j", name = "a", descriptor = "(BZLclient!na;)V")
    public static void drawTextOnScreen(@OriginalArg(1) boolean arg0, @OriginalArg(2) JString arg1) {
        @Pc(24) int local24 = Static215.aClass3_Sub2_Sub9_32.method2856(arg1, 250);
        @Pc(31) int local31 = Static215.aClass3_Sub2_Sub9_32.method2860(arg1, 250) * 13;
        if (GlRenderer.enabled) {
            Static46.method1186(6, 6, local24 + 4 + 4, local31 + 8, 0);
            Static46.method1179(6, 6, local24 + 4 + 4, local31 + 4 + 4, 16777215);
        } else {
            Rasterizer.drawFilledRectangle(6, 6, local24 + 4 + 4, local31 + 8, 0);
            Rasterizer.drawUnfilledRectangle(6, 6, local24 + 8, 4 + 4 + local31, 16777215);
        }
        Static215.aClass3_Sub2_Sub9_32.method2852(arg1, 10, 10, local24, local31, 16777215, -1, 1, 1, 0);
        Static133.method4012(6, local24 + 8, 6, local31 + 4 + 4);
        if (!arg0) {
            Static121.method2407(10, 10, local31, local24);
        } else if (GlRenderer.enabled) {
            GlRenderer.swapBuffers();
        } else {
            try {
                @Pc(159) Graphics local159 = GameShell.canvas.getGraphics();
                SoftwareRaster.frameBuffer.draw(local159);
            } catch (@Pc(167) Exception local167) {
                GameShell.canvas.repaint();
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ld", name = "a", descriptor = "(B)I")
    public static int getTotal() {
        return 6;
    }
}
