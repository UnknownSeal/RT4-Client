package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.quickchatphrasetype.QuickChatPhraseType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class QuickChatPhraseTypeList {
    @OriginalMember(owner = "client!ck", name = "C", descriptor = "I")
    public static int anInt1047 = 0;
    @OriginalMember(owner = "runetek4.client!le", name = "e", descriptor = "I")
    public static int anInt3490 = 0;

    @OriginalMember(owner = "runetek4.client!sj", name = "b", descriptor = "(II)Lclient!cb;")
    public static QuickChatPhraseType list(@OriginalArg(1) int id) {
        @Pc(10) QuickChatPhraseType cached = (QuickChatPhraseType) Static249.recentUse.get((long) id);
        if (cached != null) {
            return cached;
        }

        @Pc(27) byte[] bytes;
        if (id >= 32768) {
            bytes = Static262.configClientLarge.getfile(1, id & 0x7FFF);
        } else {
            bytes = Static238.configClientSmall.getfile(1, id);
        }

        QuickChatPhraseType quickChatPhraseType = new QuickChatPhraseType();
        if (bytes != null) {
            quickChatPhraseType.decode(new Packet(bytes));
        }
        if (id >= 32768) {
            quickChatPhraseType.postDecode();
        }
        Static249.recentUse.put(quickChatPhraseType, (long) id);
        return quickChatPhraseType;
    }
}
