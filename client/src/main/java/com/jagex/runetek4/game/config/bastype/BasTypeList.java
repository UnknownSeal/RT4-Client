package com.jagex.runetek4.game.config.bastype;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class BasTypeList {
    @OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "Lclient!n;")
    public static final NodeCache types = new NodeCache(64);
    @OriginalMember(owner = "runetek4.client!nd", name = "p", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!hb", name = "a", descriptor = "(ZI)Lclient!ck;")
    public static BasType get(@OriginalArg(1) int arg0) {
        @Pc(10) BasType local10 = (BasType) types.get((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(21) byte[] local21 = archive.getfile(32, arg0);
        local10 = new BasType();
        if (local21 != null) {
            local10.decode(new Packet(local21));
        }
        local10.postDecode();
        types.put(local10, (long) arg0);
        return local10;
    }

    @OriginalMember(owner = "runetek4.client!jk", name = "e", descriptor = "(B)V")
    public static void clear() {
        types.method3104();
    }

    @OriginalMember(owner = "client!bi", name = "c", descriptor = "(II)V")
    public static void clean() {
        types.clear(5);
    }

    @OriginalMember(owner = "runetek4.client!di", name = "d", descriptor = "(I)V")
    public static void removeSoft() {
        types.removeSoft();
    }
}
