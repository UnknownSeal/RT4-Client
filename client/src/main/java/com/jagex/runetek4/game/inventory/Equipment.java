package com.jagex.runetek4.game.inventory;

import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Equipment {
    @OriginalMember(owner = "client!ta", name = "p", descriptor = "[I")
    public static int[] equipmentObjectIds;

    @OriginalMember(owner = "client!eh", name = "a", descriptor = "(I)V")
    public static void init() {
        @Pc(8) int[] tempEquipmentIds = new int[ObjTypeList.capacity];
        @Pc(10) int equipmentCount = 0;
        for (@Pc(12) int objectIndex = 0; objectIndex < ObjTypeList.capacity; objectIndex++) {
            @Pc(19) ObjType objType = ObjTypeList.get(objectIndex);
            if (objType.manwear >= 0 || objType.womanwear >= 0) {
                tempEquipmentIds[equipmentCount++] = objectIndex;
            }
        }
        equipmentObjectIds = new int[equipmentCount];
        for (int copyIndex = 0; copyIndex < equipmentCount; copyIndex++) {
            equipmentObjectIds[copyIndex] = tempEquipmentIds[copyIndex];
        }
    }
}
