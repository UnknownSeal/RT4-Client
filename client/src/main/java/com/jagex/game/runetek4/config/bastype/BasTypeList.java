package com.jagex.game.runetek4.config.bastype;

import com.jagex.core.io.Packet;
import com.jagex.js5.Js5;
import com.jagex.core.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class BasTypeList {
    @OriginalMember(owner = "client!vf", name = "a", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);

    @OriginalMember(owner = "client!nd", name = "p", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!hb", name = "a", descriptor = "(ZI)Lclient!ck;")
    public static BasType get(@OriginalArg(1) int arg0) {
        @Pc(10) BasType local10 = (BasType) types.get(arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(21) byte[] local21 = archive.getfile(32, arg0);
        local10 = new BasType();
        if (local21 != null) {
            local10.decode(new Packet(local21));
        }
        local10.postDecode();
        types.put(local10, arg0);
        return local10;
    }

    @OriginalMember(owner = "runetek4.client!jk", name = "e", descriptor = "(B)V")
    public static void clear() {
        types.clean();
    }

    @OriginalMember(owner = "client!bi", name = "c", descriptor = "(II)V")
    public static void clean() {
        types.clean(5);
    }

    @OriginalMember(owner = "client!di", name = "d", descriptor = "(I)V")
    public static void removeSoft() {
        types.removeSoft();
    }

    @OriginalMember(owner = "client!ge", name = "a", descriptor = "(BLclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0) {
        archive = arg0;
    }
}
