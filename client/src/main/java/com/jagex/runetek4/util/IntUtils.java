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

    @OriginalMember(owner = "runetek4.client!ra", name = "b", descriptor = "(III)I")
    public static int pow(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
        @Pc(15) int local15 = 1;
        while (arg0 > 1) {
            if ((arg0 & 0x1) != 0) {
                local15 *= arg1;
            }
            arg1 *= arg1;
            arg0 >>= 0x1;
        }
        if (arg0 == 1) {
            return local15 * arg1;
        } else {
            return local15;
        }
    }

    @OriginalMember(owner = "runetek4.client!qi", name = "b", descriptor = "(II)I")
    public static int bitCount(@OriginalArg(0) int arg0) {
        @Pc(5) int local5 = 0;
        if (arg0 < 0 || arg0 >= 65536) {
            local5 += 16;
            arg0 >>>= 0x10;
        }
        if (arg0 >= 256) {
            local5 += 8;
            arg0 >>>= 0x8;
        }
        if (arg0 >= 16) {
            local5 += 4;
            arg0 >>>= 0x4;
        }
        if (arg0 >= 4) {
            arg0 >>>= 0x2;
            local5 += 2;
        }
        if (arg0 >= 1) {
            arg0 >>>= 0x1;
            local5++;
        }
        return arg0 + local5;
    }

    @OriginalMember(owner = "client!gd", name = "a", descriptor = "(IIBI)I")
    public static int clamp(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
        return arg2 > arg1 ? arg2 : arg1 > arg0 ? arg0 : arg1;
    }

    @OriginalMember(owner = "client!bn", name = "a", descriptor = "(III)I")
	public static int bitReverse(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(8) int local8 = 0;
		while (arg0 > 0) {
			local8 = local8 << 1 | arg1 & 0x1;
			arg1 >>>= 0x1;
			arg0--;
		}
		return local8;
	}

    @OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "(II)I")
    public static int bitCountFast(@OriginalArg(0) int arg0) {
        @Pc(9) int local9 = (arg0 >>> 1 & 0xD5555555) + (arg0 & 0x55555555);
        @Pc(19) int local19 = (local9 >>> 2 & 0x33333333) + (local9 & 0x33333333);
        @Pc(31) int local31 = (local19 >>> 4) + local19 & 0xF0F0F0F;
        @Pc(37) int local37 = local31 + (local31 >>> 8);
        @Pc(43) int local43 = local37 + (local37 >>> 16);
        return local43 & 0xFF;
    }
}
