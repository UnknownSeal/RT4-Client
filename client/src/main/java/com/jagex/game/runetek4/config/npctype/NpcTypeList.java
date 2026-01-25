package com.jagex.game.runetek4.config.npctype;

import com.jagex.core.io.Packet;
import com.jagex.js5.Js5;
import com.jagex.core.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class NpcTypeList {

    @OriginalMember(owner = "client!jl", name = "x", descriptor = "Lclient!n;")
    public static final SoftLruHashTable models = new SoftLruHashTable(50);

    @OriginalMember(owner = "client!vf", name = "k", descriptor = "Lclient!n;")
    public static final SoftLruHashTable headModels = new SoftLruHashTable(5);

    @OriginalMember(owner = "client!he", name = "V", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);

    @OriginalMember(owner = "client!gm", name = "ib", descriptor = "Lclient!ve;")
	public static Js5 modelsArchive;

    @OriginalMember(owner = "client!eh", name = "f", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "client!rg", name = "b", descriptor = "(II)Lclient!me;")
    public static NpcType get(@OriginalArg(0) int id) {
        @Pc(10) NpcType npcType = (NpcType) types.get(id);
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
        types.put(npcType, id);
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

    @OriginalMember(owner = "client!oe", name = "a", descriptor = "(IZ)I")
    public static int getFileId(@OriginalArg(0) int arg0) {
        return arg0 & 0x7F;
    }

    @OriginalMember(owner = "client!t", name = "b", descriptor = "(B)V")
    public static void removeSoft() {
        types.removeSoft();
        models.removeSoft();
        headModels.removeSoft();
    }

    @OriginalMember(owner = "client!qi", name = "e", descriptor = "(B)V")
    public static void clear() {
        types.clean();
        models.clean();
        headModels.clean();
    }

    @OriginalMember(owner = "client!ra", name = "c", descriptor = "(BI)V")
    public static void method3706() {
        types.clean(5);
        models.clean(5);
        headModels.clean(5);
    }

    @OriginalMember(owner = "client!wj", name = "b", descriptor = "(I)V")
    public static void method4649() {
        models.clean();
    }

    @OriginalMember(owner = "client!ba", name = "e", descriptor = "(I)V")
    public static void method443() {
        headModels.clean();
    }
}
