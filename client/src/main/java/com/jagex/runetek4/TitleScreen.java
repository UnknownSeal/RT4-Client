package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class TitleScreen {
    @OriginalMember(owner = "client!cb", name = "cb", descriptor = "Lclient!na;")
    public static final JString TITLEBG = JString.parse("titlebg");

    @OriginalMember(owner = "client!je", name = "f", descriptor = "(B)V")
    public static void clear() {
        if (Static18.aBoolean40) {
            Static243.aClass36_1 = null;
            Static18.aBoolean40 = false;
            Static78.aClass3_Sub2_Sub1_3 = null;
        }
    }

    @OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(Lclient!ve;B)V")
    public static void load(@OriginalArg(0) Js5 arg0) {
        if (Static18.aBoolean40) {
            return;
        }
        if (GlRenderer.enabled) {
            GlRaster.clear();
        } else {
            Rasterizer.clear();
        }
        Static78.aClass3_Sub2_Sub1_3 = Static130.method2514(arg0, Static262.bgId);
        @Pc(20) int local20 = GameShell.canvasHeigth;
        @Pc(26) int local26 = local20 * 956 / 503;
        Static78.aClass3_Sub2_Sub1_3.renderResized((GameShell.canvasWidth - local26) / 2, 0, local26, local20);
        Static243.aClass36_1 = Static40.method1010(Static136.logoId, arg0);
        Static243.aClass36_1.drawImage(GameShell.canvasWidth / 2 - Static243.aClass36_1.anInt4270 / 2, 18);
        Static18.aBoolean40 = true;
    }

    @OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "(ILclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 archive) {
        Static262.bgId = archive.getGroupId(TITLEBG);
        Static136.logoId = archive.getGroupId(Static165.LOGO);
    }

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(ILclient!ve;)I")
    public static int getReady(@OriginalArg(1) Js5 arg0) {
        @Pc(1) int local1 = 0;
        if (arg0.method4506(Static262.bgId)) {
            local1++;
        }
        if (arg0.method4506(Static136.logoId)) {
            local1++;
        }
        return local1;
    }

    @OriginalMember(owner = "runetek4.client!ia", name = "a", descriptor = "(I)I")
    public static int getTotal() {
        return 2;
    }
}
