package com.jagex.runetek4;

import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Equipment {
    @OriginalMember(owner = "client!ta", name = "p", descriptor = "[I")
    public static int[] objIds;

    @OriginalMember(owner = "client!eh", name = "a", descriptor = "(I)V")
    public static void init() {
        @Pc(8) int[] ids = new int[ObjTypeList.capacity];
        @Pc(10) int j = 0;
        for (@Pc(12) int i = 0; i < ObjTypeList.capacity; i++) {
            @Pc(19) ObjType def = ObjTypeList.get(i);
            if (def.manwear >= 0 || def.womanwear >= 0) {
                ids[j++] = i;
            }
        }
        objIds = new int[j];
        for (int i = 0; i < j; i++) {
            objIds[i] = ids[i];
        }
    }
}
