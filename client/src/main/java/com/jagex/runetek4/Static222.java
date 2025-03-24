package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static222 {

	@OriginalMember(owner = "runetek4.client!sa", name = "a", descriptor = "(IIIBI)V")
	public static void method3826(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 <= arg2) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[arg1], arg3, arg2, arg0);
		} else {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[arg1], arg2, arg3, arg0);
		}
	}
}
