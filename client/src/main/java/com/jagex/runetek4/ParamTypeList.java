package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ParamTypeList {
    @OriginalMember(owner = "runetek4.client!wd", name = "a", descriptor = "Lclient!gn;")
    public static final LruHashTable types = new LruHashTable(64);
    @OriginalMember(owner = "client!d", name = "hb", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "(BLclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        archive = arg0;
    }

    @OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(II)Lclient!hn;")
    public static ParamType get(@OriginalArg(1) int arg0) {
        @Pc(6) ParamType local6 = (ParamType) types.get((long) arg0);
        if (local6 != null) {
            return local6;
        }
        @Pc(30) byte[] local30 = archive.getfile(11, arg0);
        local6 = new ParamType();
        if (local30 != null) {
            local6.decode(new Packet(local30));
        }
        types.put(local6, (long) arg0);
        return local6;
    }
}
