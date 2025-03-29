package com.jagex.runetek4;

import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class TitleScreen {
    @OriginalMember(owner = "client!cb", name = "cb", descriptor = "Lclient!na;")
    public static final JString TITLEBG = JString.parse("titlebg");
    @OriginalMember(owner = "runetek4.client!nb", name = "m", descriptor = "Lclient!na;")
    public static final JString LOGO = JString.parse("logo");
    @OriginalMember(owner = "client!bh", name = "M", descriptor = "Z")
    public static boolean loaded;
    @OriginalMember(owner = "client!gd", name = "n", descriptor = "Lclient!qf;")
    public static Sprite titleBg;
    @OriginalMember(owner = "runetek4.client!tk", name = "o", descriptor = "Lclient!ok;")
    public static IndexedSprite logo;
    @OriginalMember(owner = "runetek4.client!vf", name = "m", descriptor = "I")
    public static int bgId = -1;
    @OriginalMember(owner = "runetek4.client!kk", name = "g", descriptor = "I")
    public static int logoId = -1;

    @OriginalMember(owner = "client!je", name = "f", descriptor = "(B)V")
    public static void clear() {
        if (loaded) {
            logo = null;
            loaded = false;
            titleBg = null;
        }
    }

    @OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(Lclient!ve;B)V")
    public static void load(@OriginalArg(0) Js5 archive) {
        if (loaded) {
            return;
        }
        if (GlRenderer.enabled) {
            GlRaster.clear();
        } else {
            SoftwareRaster.clear();
        }
        titleBg = SpriteLoader.loadSpriteAutoDetect(archive, bgId);
        @Pc(20) int height = GameShell.canvasHeigth;
        @Pc(26) int width = height * 956 / 503;
        titleBg.renderResized((GameShell.canvasWidth - width) / 2, 0, width, height);
        logo = SpriteLoader.loadIndexedSpriteAutoDetect(logoId, archive);
        logo.renderTransparent(GameShell.canvasWidth / 2 - logo.width / 2, 18);
        loaded = true;
    }

    @OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "(ILclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 archive) {
        bgId = archive.getGroupId(TITLEBG);
        logoId = archive.getGroupId(LOGO);
    }

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(ILclient!ve;)I")
    public static int getReady(@OriginalArg(1) Js5 archive) {
        @Pc(1) int ready = 0;
        if (archive.isFileReady(bgId)) {
            ready++;
        }
        if (archive.isFileReady(logoId)) {
            ready++;
        }
        return ready;
    }

    @OriginalMember(owner = "runetek4.client!ia", name = "a", descriptor = "(I)I")
    public static int getTotal() {
        return 2;
    }
}
