package com.jagex.runetek4.config.types.light;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.data.js5.Js5;
import com.jagex.runetek4.core.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LightTypeList {
    @OriginalMember(owner = "runetek4.client!rm", name = "d", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);
    @OriginalMember(owner = "client!gl", name = "a", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!id", name = "a", descriptor = "(Lclient!ve;B)V")
    public static void init(@OriginalArg(0) Js5 arg0) {
        archive = arg0;
    }

    @OriginalMember(owner = "runetek4.client!la", name = "a", descriptor = "(II)Lclient!ic;")
    public static LightType get(@OriginalArg(1) int arg0) {
        @Pc(10) LightType local10 = (LightType) types.get((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(26) byte[] local26 = archive.getfile(31, arg0);
        local10 = new LightType();
        if (local26 != null) {
            local10.decode(new Packet(local26));
        }
        types.put(local10, (long) arg0);
        return local10;
    }

    @OriginalMember(owner = "client!c", name = "c", descriptor = "(II)V")
    public static void clean() {
        types.clean(5);
    }

    @OriginalMember(owner = "client!gd", name = "b", descriptor = "(I)V")
    public static void clear() {
        types.clean();
    }

    @OriginalMember(owner = "runetek4.client!hd", name = "a", descriptor = "(I)V")
    public static void removeSoft() {
        types.removeSoft();
    }
}
