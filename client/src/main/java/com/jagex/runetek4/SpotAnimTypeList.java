package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SpotAnimTypeList {
    @OriginalMember(owner = "runetek4.client!wk", name = "t", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);
    @OriginalMember(owner = "runetek4.client!ef", name = "b", descriptor = "Lclient!n;")
    public static final SoftLruHashTable models = new SoftLruHashTable(30);
    @OriginalMember(owner = "runetek4.client!he", name = "cb", descriptor = "Lclient!ve;")
    public static Js5 modelsArchive;
    @OriginalMember(owner = "runetek4.client!ke", name = "R", descriptor = "Lclient!ve;")
    public static Js5 archive;

    @OriginalMember(owner = "client!ck", name = "a", descriptor = "(BI)Lclient!eg;")
    public static SpotAnimType get(@OriginalArg(1) int id) {
        @Pc(10) SpotAnimType spotAnim = (SpotAnimType) types.get((long) id);
        if (spotAnim != null) {
            return spotAnim;
        }
        @Pc(26) byte[] src = archive.getfile(method3681(id), method4010(id));
        spotAnim = new SpotAnimType();
        spotAnim.id = id;
        if (src != null) {
            spotAnim.decode(new Packet(src));
        }
        types.put(spotAnim, (long) id);
        return spotAnim;
    }

    @OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(Lclient!ve;Lclient!ve;I)V")
    public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
        modelsArchive = arg0;
        archive = arg1;
    }

    @OriginalMember(owner = "runetek4.client!qk", name = "a", descriptor = "(ZI)I")
    public static int method3681(@OriginalArg(1) int arg0) {
        return arg0 >>> 8;
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "a", descriptor = "(II)I")
    public static int method4010(@OriginalArg(0) int arg0) {
        return arg0 & 0xFF;
    }

    @OriginalMember(owner = "runetek4.client!kl", name = "c", descriptor = "(II)V")
    public static void clean() {
        types.clean(5);
        models.clean(5);
    }

    @OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(Z)V")
    public static void clear() {
        types.clean();
        models.clean();
    }

    @OriginalMember(owner = "client!fk", name = "a", descriptor = "(B)V")
    public static void removeSoft() {
        types.removeSoft();
        models.removeSoft();
    }
}
