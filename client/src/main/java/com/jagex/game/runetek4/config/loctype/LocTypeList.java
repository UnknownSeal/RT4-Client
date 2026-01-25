package com.jagex.game.runetek4.config.loctype;

import com.jagex.core.io.Packet;
import com.jagex.js5.Js5;
import com.jagex.core.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LocTypeList {
    @OriginalMember(owner = "client!nf", name = "f", descriptor = "Lclient!n;")
    public static final SoftLruHashTable modelCacheStatic = new SoftLruHashTable(500);

    @OriginalMember(owner = "client!oe", name = "j", descriptor = "Lclient!n;")
    public static final SoftLruHashTable types = new SoftLruHashTable(64);

    @OriginalMember(owner = "client!he", name = "fb", descriptor = "Lclient!n;")
    public static final SoftLruHashTable aClass99_14 = new SoftLruHashTable(30);

    @OriginalMember(owner = "client!vf", name = "l", descriptor = "Lclient!n;")
    public static final SoftLruHashTable aClass99_36 = new SoftLruHashTable(50);

    @OriginalMember(owner = "client!jg", name = "j", descriptor = "Lclient!ve;")
    public static Js5 modelsArchive;

    @OriginalMember(owner = "client!cg", name = "c", descriptor = "Z")
    public static boolean allowMembers;

    @OriginalMember(owner = "client!lg", name = "g", descriptor = "Lclient!ve;")
    public static Js5 locsArchive;

    @OriginalMember(owner = "client!wc", name = "a", descriptor = "(II)Lclient!pb;")
    public static LocType get(@OriginalArg(1) int id) {
        @Pc(15) LocType locType = (LocType) types.get(id);
        if (locType != null) {
            return locType;
        }
        @Pc(30) byte[] bytes = locsArchive.getfile(getGroupId(id), getFileId(id));
        locType = new LocType();
        locType.id = id;
        if (bytes != null) {
            locType.decode(new Packet(bytes));
        }
        locType.postDecode();
        if (!allowMembers && locType.members) {
            locType.op = null;
        }
        if (locType.breakroutefinding) {
            locType.blockwalk = 0;
            locType.blockrange = false;
        }
        types.put(locType, id);
        return locType;
    }

    @OriginalMember(owner = "client!oe", name = "b", descriptor = "(I)V")
    public static void removeSoft() {
        types.removeSoft();
        modelCacheStatic.removeSoft();
        aClass99_14.removeSoft();
        aClass99_36.removeSoft();
    }

    @OriginalMember(owner = "client!hb", name = "c", descriptor = "(I)V")
    public static void clear() {
        types.clean();
        modelCacheStatic.clean();
        aClass99_14.clean();
        aClass99_36.clean();
    }

    @OriginalMember(owner = "client!oi", name = "a", descriptor = "(ILclient!ve;Lclient!ve;Z)V")
    public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
        allowMembers = true;
        modelsArchive = arg1;
        locsArchive = arg0;
    }

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(IB)I")
    public static int getFileId(@OriginalArg(0) int id) {
        return id & 0xFF;
    }

    @OriginalMember(owner = "client!ui", name = "c", descriptor = "(II)I")
    public static int getGroupId(@OriginalArg(0) int id) {
        return id >>> 8;
    }

    @OriginalMember(owner = "client!va", name = "b", descriptor = "(II)V")
    public static void clean() {
        types.clean(5);
        modelCacheStatic.clean(5);
        aClass99_14.clean(5);
        aClass99_36.clean(5);
    }

    @OriginalMember(owner = "client!pe", name = "a", descriptor = "(BZ)V")
    public static void setAllowMembers(@OriginalArg(1) boolean arg0) {
        if (arg0 != allowMembers) {
            allowMembers = arg0;
            clear();
        }
    }
}
