package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class WorldMap {
    @OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(IZ)V")
    public static void clear(@OriginalArg(1) boolean arg0) {
        Static90.aByteArrayArrayArray8 = null;
        Static83.anIntArrayArrayArray3 = null;
        Static24.component = null;
        Static34.aByteArrayArrayArray3 = null;
        Static145.anIntArray330 = null;
        Static125.aByteArrayArrayArray10 = null;
        if (arg0 && Static269.aClass3_Sub2_Sub4_2 != null) {
            Static153.aClass100_724 = Static269.aClass3_Sub2_Sub4_2.aClass100_138;
        } else {
            Static153.aClass100_724 = null;
        }
        Static70.aByteArrayArrayArray7 = null;
        Static229.aByteArrayArrayArray12 = null;
        Static58.anIntArrayArrayArray5 = null;
        Static248.anIntArrayArrayArray17 = null;
        Static41.anInt1309 = 0;
        Static269.aClass3_Sub2_Sub4_2 = null;
        Static145.aClass69_84.clear();
        Static203.aMapElementTypeList_1 = null;
        Static217.anInt4901 = -1;
        Static130.aClass41_2 = null;
        Static160.aClass41_4 = null;
        Static152.aClass41_3 = null;
        Static270.aClass41_9 = null;
        Static273.aClass41_7 = null;
        Static169.aClass41_5 = null;
        Static130.aClass41_1 = null;
        Static203.aClass41_8 = null;
        Static70.aClass3_Sub2_Sub1_2 = null;
        Static142.anInt3482 = -1;
        Static153.aClass3_Sub2_Sub1_Sub1_2 = null;
    }
}
