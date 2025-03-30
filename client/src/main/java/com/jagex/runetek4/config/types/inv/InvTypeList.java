package com.jagex.runetek4.config.types.inv;

import com.jagex.runetek4.LruHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class InvTypeList {
    @OriginalMember(owner = "runetek4.client!ha", name = "p", descriptor = "Lclient!gn;")
    public static final LruHashTable types = new LruHashTable(64);
    @OriginalMember(owner = "client!al", name = "q", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(II)Lclient!md;")
    public static InvType get(@OriginalArg(0) int id) {
        @Pc(16) InvType invType = (InvType) types.get((long) id);
        if (invType != null) {
            return invType;
        }
        @Pc(27) byte[] bytes = archive.getfile(5, id);
        invType = new InvType();
        if (bytes != null) {
            invType.decode(new Packet(bytes));
        }
        types.put(invType, (long) id);
        return invType;
    }

    @OriginalMember(owner = "client!je", name = "a", descriptor = "(ILclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        archive = arg0;
    }
}
