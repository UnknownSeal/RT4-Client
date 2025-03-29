package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarpType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarpTypeList {
    @OriginalMember(owner = "runetek4.client!sm", name = "c", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);
    @OriginalMember(owner = "runetek4.client!gg", name = "ab", descriptor = "Lclient!ve;")
    public static Js5 archive;
    @OriginalMember(owner = "runetek4.client!nb", name = "p", descriptor = "I")
    public static int varpTypeSize;

    @OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(Lclient!ve;B)V")
    public static void init(@OriginalArg(0) Js5 js5) {
        archive = js5;
        varpTypeSize = archive.getGroupCapacity(16);
    }

    @OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(II)Lclient!eh;")
    public static VarpType get(@OriginalArg(1) int varPlayerIndex) {
        @Pc(10) VarpType definition = (VarpType) types.get((long) varPlayerIndex);
        if (definition != null) {
            return definition;
        }
        @Pc(20) byte[] cacheData = archive.getfile(16, varPlayerIndex);
        definition = new VarpType();
        if (cacheData != null) {
            definition.decode(new Packet(cacheData));
        }
        types.put(definition, (long) varPlayerIndex);
        return definition;
    }

    @OriginalMember(owner = "runetek4.client!ud", name = "d", descriptor = "(I)V")
    public static void clear() {
        types.clear();
    }

    @OriginalMember(owner = "runetek4.client!ab", name = "b", descriptor = "(B)V")
    public static void removeSoft() {
        types.removeSoft();
    }

    @OriginalMember(owner = "client!bn", name = "c", descriptor = "(II)V")
    public static void clean() {
        types.clear(5);
    }
}
