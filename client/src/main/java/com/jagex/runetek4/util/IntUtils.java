package com.jagex.runetek4.util;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class IntUtils {
    @OriginalMember(owner = "client!nb", name = "a", descriptor = "(BI)I")
    public static int bitceil(@OriginalArg(1) int arg0) {
        @Pc(0) int local0 = arg0 - 1;
        @Pc(6) int local6 = local0 | local0 >>> 1;
        @Pc(22) int local22 = local6 | local6 >>> 2;
        @Pc(28) int local28 = local22 | local22 >>> 4;
        @Pc(34) int local34 = local28 | local28 >>> 8;
        @Pc(40) int local40 = local34 | local34 >>> 16;
        return local40 + 1;
    }

    @OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(BI)Z")
    public static boolean isPowerOfTwo(@OriginalArg(1) int arg0) {
        return arg0 == (-arg0 & arg0);
    }
}
