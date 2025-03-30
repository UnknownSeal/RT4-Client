package com.jagex.runetek4.config.types.struct;

import com.jagex.runetek4.LruHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class StructTypeList {
    @OriginalMember(owner = "runetek4.client!sk", name = "bb", descriptor = "Lclient!gn;")
    public static final LruHashTable types = new LruHashTable(64);
    @OriginalMember(owner = "client!bm", name = "e", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "client!eh", name = "a", descriptor = "(Lclient!ve;I)V")
    public static void init(@OriginalArg(0) Js5 arg0) {
        archive = arg0;
    }

    @OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "(BI)Lclient!lk;")
    public static StructType get(@OriginalArg(1) int arg0) {
        @Pc(10) StructType local10 = (StructType) types.get((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(26) byte[] local26 = archive.getfile(26, arg0);
        local10 = new StructType();
        if (local26 != null) {
            local10.decode(new Packet(local26));
        }
        types.put(local10, (long) arg0);
        return local10;
    }
}
