package com.jagex.runetek4.config.types.npc;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class NpcTypeList {

    @OriginalMember(owner = "runetek4.client!jl", name = "x", descriptor = "Lclient!n;")
    public static final SoftLruHashTable models = new SoftLruHashTable(50);

    @OriginalMember(owner = "runetek4.client!vf", name = "k", descriptor = "Lclient!n;")
    public static final SoftLruHashTable headModels = new SoftLruHashTable(5);

    @OriginalMember(owner = "runetek4.client!he", name = "V", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);

    @OriginalMember(owner = "client!gm", name = "ib", descriptor = "Lclient!ve;")
	public static Js5 modelsArchive;

    @OriginalMember(owner = "client!eh", name = "f", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "runetek4.client!rg", name = "b", descriptor = "(II)Lclient!me;")
    public static NpcType get(@OriginalArg(0) int id) {
        @Pc(10) NpcType npcType = (NpcType) types.get((long) id);
        if (npcType != null) {
            return npcType;
        }
        @Pc(26) byte[] bytes = archive.getfile(getGroupId(id), getFileId(id));
        npcType = new NpcType();
        npcType.id = id;
        if (bytes != null) {
            npcType.decode(new Packet(bytes));
        }
        npcType.postDecode();
        types.put(npcType, (long) id);
        return npcType;
    }

    @OriginalMember(owner = "client!h", name = "a", descriptor = "(Lclient!ve;Lclient!ve;Z)V")
    public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
        modelsArchive = arg0;
        archive = arg1;
    }

    @OriginalMember(owner = "client!em", name = "a", descriptor = "(II)I")
    public static int getGroupId(@OriginalArg(0) int arg0) {
        return arg0 >>> 7;
    }

    @OriginalMember(owner = "runetek4.client!oe", name = "a", descriptor = "(IZ)I")
    public static int getFileId(@OriginalArg(0) int arg0) {
        return arg0 & 0x7F;
    }

    @OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(B)V")
    public static void removeSoft() {
        types.removeSoft();
        models.removeSoft();
        headModels.removeSoft();
    }

    @OriginalMember(owner = "runetek4.client!qi", name = "e", descriptor = "(B)V")
    public static void clear() {
        types.clean();
        models.clean();
        headModels.clean();
    }

    @OriginalMember(owner = "runetek4.client!ra", name = "c", descriptor = "(BI)V")
    public static void method3706() {
        types.clean(5);
        models.clean(5);
        headModels.clean(5);
    }

    @OriginalMember(owner = "runetek4.client!wj", name = "b", descriptor = "(I)V")
    public static void method4649() {
        models.clean();
    }

    @OriginalMember(owner = "client!ba", name = "e", descriptor = "(I)V")
    public static void method443() {
        headModels.clean();
    }
}
