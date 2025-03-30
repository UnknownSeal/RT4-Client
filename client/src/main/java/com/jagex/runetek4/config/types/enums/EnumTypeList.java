package com.jagex.runetek4.config.types.enums;

import com.jagex.runetek4.LruHashTable;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class EnumTypeList {
    @OriginalMember(owner = "runetek4.client!lj", name = "p", descriptor = "Lclient!gn;")
    public static final LruHashTable types = new LruHashTable(128);
    @OriginalMember(owner = "client!gk", name = "e", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "client!gl", name = "a", descriptor = "(Lclient!ve;I)V")
    public static void init(@OriginalArg(0) Js5 arg0) {
        archive = arg0;
    }

    @OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IZ)Lclient!ml;")
    public static EnumType get(@OriginalArg(0) int id) {
        @Pc(10) EnumType enumType = (EnumType) types.get((long) id);
        if (enumType != null) {
            return enumType;
        }
        @Pc(24) byte[] bytes = archive.getfile(getGroupId(id), getFileId(id));
        enumType = new EnumType();
        if (bytes != null) {
            enumType.decode(new Packet(bytes));
        }
        types.put(enumType, (long) id);
        return enumType;
    }

    @OriginalMember(owner = "runetek4.client!i", name = "e", descriptor = "(BI)I")
    public static int getFileId(@OriginalArg(1) int arg0) {
        return arg0 & 0xFF;
    }

    @OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(BI)I")
    public static int getGroupId(@OriginalArg(1) int arg0) {
        return arg0 >>> 8;
    }
}
