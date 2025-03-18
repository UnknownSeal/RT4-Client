package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Equipment {
    @OriginalMember(owner = "client!ta", name = "p", descriptor = "[I")
    public static int[] objIds;

    @OriginalMember(owner = "client!eh", name = "a", descriptor = "(I)V")
    public static void init() {
        @Pc(8) int[] ids = new int[ObjTypeList.capacity];
        @Pc(10) int local10 = 0;
        for (@Pc(12) int local12 = 0; local12 < ObjTypeList.capacity; local12++) {
            @Pc(19) ObjType def = ObjTypeList.get(local12);
            if (def.manwear >= 0 || def.womanwear >= 0) {
                ids[local10++] = local12;
            }
        }
        objIds = new int[local10];
        for (int i = 0; i < local10; i++) {
            objIds[i] = ids[i];
        }
    }
}
