package com.jagex.runetek4.config.types.quickchat;

import com.jagex.runetek4.core.datastruct.LruHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.data.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class QuickChatCatTypeList {
    @OriginalMember(owner = "client!fm", name = "db", descriptor = "Lclient!gn;")
    public static final LruHashTable types = new LruHashTable(64);
    @OriginalMember(owner = "client!gn", name = "t", descriptor = "Lclient!ve;")
    public static Js5 archive1;
    @OriginalMember(owner = "client!ce", name = "d", descriptor = "Lclient!ve;")
    public static Js5 archive2;

    @OriginalMember(owner = "runetek4.client!ja", name = "a", descriptor = "(Lclient!ve;Lclient!ve;I)V")
    public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
        archive1 = arg1;
        archive2 = arg0;
    }

    @OriginalMember(owner = "runetek4.client!tb", name = "b", descriptor = "(IB)Lclient!bc;")
    public static QuickChatCatType get(@OriginalArg(0) int arg0) {
        @Pc(10) QuickChatCatType quickChatCat = (QuickChatCatType) types.get((long) arg0);
        if (quickChatCat != null) {
            return quickChatCat;
        }
        @Pc(24) byte[] local24;
        if (arg0 < 32768) {
            local24 = archive1.getfile(0, arg0);
        } else {
            local24 = archive2.getfile(0, arg0 & 0x7FFF);
        }
        quickChatCat = new QuickChatCatType();
        if (local24 != null) {
            quickChatCat.method470(new Packet(local24));
        }
        if (arg0 >= 32768) {
            quickChatCat.method465();
        }
        types.put(quickChatCat, (long) arg0);
        return quickChatCat;
    }
}
