package com.jagex.runetek4.graphics.font;

import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.core.node.SoftLruHashTable;
import com.jagex.runetek4.ui.sprite.Sprites;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class FontMetricsList {
    @OriginalMember(owner = "runetek4.client!l", name = "f", descriptor = "Lclient!n;")
    public static final SoftLruHashTable fontMetrics = new SoftLruHashTable(4);

    @OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(II)Lclient!dd;")
    public static SoftwareFont get(@OriginalArg(1) int arg0) {
        @Pc(16) SoftwareFont local16 = (SoftwareFont) fontMetrics.get((long) arg0);
        if (local16 != null) {
            return local16;
        }
        @Pc(26) byte[] local26 = Client.js5Archive13.getfile(arg0, 0);
        local16 = new SoftwareFont(local26);
        local16.setNameIcons(Sprites.nameIcons, null);
        fontMetrics.put(local16, (long) arg0);
        return local16;
    }
}
