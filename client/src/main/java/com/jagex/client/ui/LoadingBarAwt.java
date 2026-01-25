package com.jagex.client.ui;

import com.jagex.client.Client;
import com.jagex.game.runetek4.client.GameShell;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

public class LoadingBarAwt {
    @OriginalMember(owner = "client!tg", name = "a", descriptor = "Ljava/awt/FontMetrics;")
    public static FontMetrics fontMetrics;

    @OriginalMember(owner = "client!sa", name = "R", descriptor = "Ljava/awt/runetek4.Font;")
    public static Font font;

    @OriginalMember(owner = "client!lj", name = "y", descriptor = "Ljava/awt/Image;")
    public static Image loadingBar;

    @OriginalMember(owner = "client!bc", name = "a", descriptor = "(Ljava/awt/Color;ZZLclient!na;I)V")
    public static void render(@OriginalArg(0) Color color, @OriginalArg(2) boolean redraw, @OriginalArg(3) JString text, @OriginalArg(4) int loadingBarPercentage) {
        try {
            @Pc(6) Graphics graphics = GameShell.canvas.getGraphics();
            if (font == null) {
                font = new Font("Helvetica", 1, 13);
                fontMetrics = GameShell.canvas.getFontMetrics(font);
            }
            if (redraw) {
                graphics.setColor(Color.black);
                graphics.fillRect(0, 0, GameShell.canvasWidth, GameShell.canvasHeight);
            }
            if (color == null) {
                color = new Color(140, 17, 17);
            }
            try {
                if (loadingBar == null) {
                    loadingBar = GameShell.canvas.createImage(304, 34);
                }
                @Pc(56) Graphics loadingBarGraphics = loadingBar.getGraphics();
                loadingBarGraphics.setColor(color);
                loadingBarGraphics.drawRect(0, 0, 303, 33);
                loadingBarGraphics.fillRect(2, 2, loadingBarPercentage * 3, 30);
                loadingBarGraphics.setColor(Color.black);
                loadingBarGraphics.drawRect(1, 1, 301, 31);
                loadingBarGraphics.fillRect(loadingBarPercentage * 3 + 2, 2, 300 - loadingBarPercentage * 3, 30);
                loadingBarGraphics.setFont(font);
                loadingBarGraphics.setColor(Color.white);
                text.drawString(22, (304 - text.stringWidth(fontMetrics)) / 2, loadingBarGraphics);
                graphics.drawImage(loadingBar, GameShell.canvasWidth / 2 - 152, GameShell.canvasHeight / 2 - 18, null);
            } catch (@Pc(134) Exception exception) {
                @Pc(140) int x = GameShell.canvasWidth / 2 - 152;
                @Pc(146) int y = GameShell.canvasHeight / 2 - 18;
                graphics.setColor(color);
                graphics.drawRect(x, y, 303, 33);
                graphics.fillRect(x + 2, y + 2, loadingBarPercentage * 3, 30);
                graphics.setColor(Color.black);
                graphics.drawRect(x + 1, y - -1, 301, 31);
                graphics.fillRect(loadingBarPercentage * 3 + x + 2, y + 2, 300 - loadingBarPercentage * 3, 30);
                graphics.setFont(font);
                graphics.setColor(Color.white);
                text.drawString(y + 22, x + (-text.stringWidth(fontMetrics) + 304) / 2, graphics);
            }
            if (Client.mainLoadPrimaryText != null) {
                graphics.setFont(font);
                graphics.setColor(Color.white);
                Client.mainLoadPrimaryText.drawString(GameShell.canvasHeight / 2 - 26, GameShell.canvasWidth / 2 - Client.mainLoadPrimaryText.stringWidth(fontMetrics) / 2, graphics);
            }
        } catch (@Pc(252) Exception exception) {
            GameShell.canvas.repaint();
        }
    }

    @OriginalMember(owner = "client!n", name = "a", descriptor = "(B)V")
    public static void disposeLoadingText() {
        fontMetrics = null;
        font = null;
        loadingBar = null;
    }
}
