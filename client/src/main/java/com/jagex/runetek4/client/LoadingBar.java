package com.jagex.runetek4.client;

import com.jagex.runetek4.graphics.gl.GlRaster;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.graphics.raster.SoftwareRaster;
import com.jagex.runetek4.data.cache.media.Font;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LoadingBar {
    @OriginalMember(owner = "runetek4.client!oj", name = "a", descriptor = "(BZLclient!rk;)V")
    public static void render(@OriginalArg(1) boolean renderBackground, @OriginalArg(2) Font font) {
        @Pc(9) int canvasHeight;
        if (GlRenderer.enabled || renderBackground) {
            canvasHeight = GameShell.canvasHeigth;
            @Pc(15) int backgroundWidth = canvasHeight * 956 / 503;
            TitleScreen.titleBg.renderResized((GameShell.canvasWidth - backgroundWidth) / 2, 0, backgroundWidth, canvasHeight);
            TitleScreen.logo.renderTransparent(GameShell.canvasWidth / 2 - TitleScreen.logo.width / 2, 18);
        }
        font.renderCenter(LocalizedText.GAME0_LOADING, GameShell.canvasWidth / 2, GameShell.canvasHeigth / 2 - 26, 16777215, -1);
        canvasHeight = GameShell.canvasHeigth / 2 - 18;
        if (GlRenderer.enabled) {
            GlRaster.drawRect(GameShell.canvasWidth / 2 - 152, canvasHeight, 304, 34, 9179409);
            GlRaster.drawRect(GameShell.canvasWidth / 2 - 151, canvasHeight - -1, 302, 32, 0);
            GlRaster.fillRect(GameShell.canvasWidth / 2 - 150, canvasHeight + 2, Client.mainLoadPercentage * 3, 30, 9179409);
            GlRaster.fillRect(GameShell.canvasWidth / 2 + Client.mainLoadPercentage * 3 - 150, canvasHeight + 2, 300 - Client.mainLoadPercentage * 3, 30, 0);
        } else {
            SoftwareRaster.drawRect(GameShell.canvasWidth / 2 - 152, canvasHeight, 304, 34, 9179409);
            SoftwareRaster.drawRect(GameShell.canvasWidth / 2 - 151, canvasHeight + 1, 302, 32, 0);
            SoftwareRaster.fillRect(GameShell.canvasWidth / 2 - 150, canvasHeight + 2, Client.mainLoadPercentage * 3, 30, 9179409);
            SoftwareRaster.fillRect(Client.mainLoadPercentage * 3 + GameShell.canvasWidth / 2 - 150, canvasHeight + 2, 300 - Client.mainLoadPercentage * 3, 30, 0);
        }
        font.renderCenter(Client.mainLoadSecondaryText, GameShell.canvasWidth / 2, GameShell.canvasHeigth / 2 + 4, 16777215, -1);
    }
}
