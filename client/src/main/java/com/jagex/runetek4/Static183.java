package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static183 {

	@OriginalMember(owner = "runetek4.client!ok", name = "a", descriptor = "(IIIIIII)V")
	public static void method3334(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(5) int local5 = arg4 + arg1;
		@Pc(14) int local14 = arg3 - arg4;
		@Pc(29) int local29 = arg4 + arg5;
		@Pc(31) int local31;
		for (local31 = arg1; local31 < local5; local31++) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local31], arg5, arg0, arg2);
		}
		for (local31 = arg3; local31 > local14; local31--) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local31], arg5, arg0, arg2);
		}
		@Pc(70) int local70 = arg0 - arg4;
		for (local31 = local5; local31 <= local14; local31++) {
			@Pc(83) int[] local83 = ObjTypeList.anIntArrayArray10[local31];
			ArrayUtils.fillRange(local83, arg5, local29, arg2);
			ArrayUtils.fillRange(local83, local70, arg0, arg2);
		}
	}
}
