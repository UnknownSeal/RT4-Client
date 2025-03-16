package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LoadingBar {
    @OriginalMember(owner = "runetek4.client!oj", name = "a", descriptor = "(BZLclient!rk;)V")
    public static void render(@OriginalArg(1) boolean arg0, @OriginalArg(2) Font arg1) {
        @Pc(9) int local9;
        if (GlRenderer.enabled || arg0) {
            local9 = GameShell.canvasHeigth;
            @Pc(15) int local15 = local9 * 956 / 503;
            Static78.aClass3_Sub2_Sub1_3.method1419((GameShell.canvasWidth - local15) / 2, 0, local15, local9);
            Static243.aClass36_1.drawImage(GameShell.canvasWidth / 2 - Static243.aClass36_1.anInt4270 / 2, 18);
        }
        arg1.method2875(LocalizedText.GAME0_LOADING, GameShell.canvasWidth / 2, GameShell.canvasHeigth / 2 - 26, 16777215, -1);
        local9 = GameShell.canvasHeigth / 2 - 18;
        if (GlRenderer.enabled) {
            Static46.method1179(GameShell.canvasWidth / 2 - 152, local9, 304, 34, 9179409);
            Static46.method1179(GameShell.canvasWidth / 2 - 151, local9 - -1, 302, 32, 0);
            Static46.method1186(GameShell.canvasWidth / 2 - 150, local9 + 2, Static199.mainLoadPercentage * 3, 30, 9179409);
            Static46.method1186(GameShell.canvasWidth / 2 + Static199.mainLoadPercentage * 3 - 150, local9 + 2, 300 - Static199.mainLoadPercentage * 3, 30, 0);
        } else {
            Rasterizer.drawUnfilledRectangle(GameShell.canvasWidth / 2 - 152, local9, 304, 34, 9179409);
            Rasterizer.drawUnfilledRectangle(GameShell.canvasWidth / 2 - 151, local9 + 1, 302, 32, 0);
            Rasterizer.drawFilledRectangle(GameShell.canvasWidth / 2 - 150, local9 + 2, Static199.mainLoadPercentage * 3, 30, 9179409);
            Rasterizer.drawFilledRectangle(Static199.mainLoadPercentage * 3 + GameShell.canvasWidth / 2 - 150, local9 + 2, 300 - Static199.mainLoadPercentage * 3, 30, 0);
        }
        arg1.method2875(Static126.mainLoadSecondaryText, GameShell.canvasWidth / 2, GameShell.canvasHeigth / 2 + 4, 16777215, -1);
    }
}
