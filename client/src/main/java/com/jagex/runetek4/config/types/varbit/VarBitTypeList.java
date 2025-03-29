package com.jagex.runetek4.config.types.varbit;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class VarBitTypeList {
    @OriginalMember(owner = "runetek4.client!jl", name = "G", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);
    @OriginalMember(owner = "runetek4.client!nj", name = "c", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(Lclient!ve;I)V")
    public static void init(@OriginalArg(0) Js5 arg0) {
        archive = arg0;
    }

    @OriginalMember(owner = "runetek4.client!jl", name = "a", descriptor = "(IB)Lclient!kk;")
    public static VarbitType get(@OriginalArg(0) int id) {
        @Pc(10) VarbitType varbit = (VarbitType) types.get((long) id);
        if (varbit != null) {
            return varbit;
        }
        @Pc(31) byte[] cacheData = archive.getfile(getGroupId(id), getFileId(id));
        varbit = new VarbitType();
        if (cacheData != null) {
            varbit.decode(new Packet(cacheData));
        }
        types.put(varbit, (long) id);
        return varbit;
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "d", descriptor = "(BI)V")
    public static void clean() {
        types.clean(5);
    }

    @OriginalMember(owner = "client!gd", name = "a", descriptor = "(I)V")
    public static void removeSoft() {
        types.removeSoft();
    }

    @OriginalMember(owner = "client!ec", name = "b", descriptor = "(I)V")
    public static void clear() {
        types.clean();
    }

    @OriginalMember(owner = "runetek4.client!wf", name = "a", descriptor = "(II)I")
    public static int getFileId(@OriginalArg(0) int arg0) {
        return arg0 & 0x3FF;
    }

    @OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(II)I")
    public static int getGroupId(@OriginalArg(0) int arg0) {
        return arg0 >>> 10;
    }
}
