package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.entity.LocType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LocTypeList {
    @OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(II)Lclient!pb;")
    public static LocType get(@OriginalArg(1) int id) {
        @Pc(15) LocType locType = (LocType) Static179.aClass99_25.get((long) id);
        if (locType != null) {
            return locType;
        }
        @Pc(30) byte[] bytes = Static146.aClass153_54.getfile(Static253.method4328(id), Static33.method872(id));
        locType = new LocType();
        locType.anInt4426 = id;
        if (bytes != null) {
            locType.decode(new Packet(bytes));
        }
        locType.postDecode();
        if (!Static30.aBoolean61 && locType.members) {
            locType.op = null;
        }
        if (locType.breakroutefinding) {
            locType.blockwalk = 0;
            locType.blockrange = false;
        }
        Static179.aClass99_25.put(locType, (long) id);
        return locType;
    }

    @OriginalMember(owner = "runetek4.client!oe", name = "b", descriptor = "(I)V")
    public static void removeSoft() {
        Static179.aClass99_25.removeSoft();
        Static169.modelCacheStatic.removeSoft();
        Static93.aClass99_14.removeSoft();
        Static262.aClass99_36.removeSoft();
    }

    @OriginalMember(owner = "runetek4.client!hb", name = "c", descriptor = "(I)V")
    public static void clear() {
        Static179.aClass99_25.clear();
        Static169.modelCacheStatic.clear();
        Static93.aClass99_14.clear();
        Static262.aClass99_36.clear();
    }
}
