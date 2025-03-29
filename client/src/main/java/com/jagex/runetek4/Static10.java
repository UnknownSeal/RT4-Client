package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static10 {

	@OriginalMember(owner = "client!an", name = "a", descriptor = "(IIIIIII)V")
	public static void method352(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(11) int local11 = IntUtils.clamp(Static106.anInt2869, arg5, Static267.anInt5773);
		@Pc(17) int local17 = IntUtils.clamp(Static106.anInt2869, arg0, Static267.anInt5773);
		@Pc(23) int local23 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3, Static172.anInt4164);
		@Pc(29) int local29 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg2, Static172.anInt4164);
		@Pc(42) int local42 = IntUtils.clamp(Static106.anInt2869, arg5 + arg1, Static267.anInt5773);
		@Pc(51) int local51 = IntUtils.clamp(Static106.anInt2869, arg0 - arg1, Static267.anInt5773);
		@Pc(53) int local53;
		for (local53 = local11; local53 < local42; local53++) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local53], local23, local29, arg4);
		}
		for (local53 = local17; local53 > local51; local53--) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local53], local23, local29, arg4);
		}
		@Pc(95) int local95 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg1 + arg3, Static172.anInt4164);
		@Pc(104) int local104 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg2 - arg1, Static172.anInt4164);
		for (local53 = local42; local53 <= local51; local53++) {
			@Pc(117) int[] local117 = ObjTypeList.anIntArrayArray10[local53];
			ArrayUtils.fillRange(local117, local23, local95, arg4);
			ArrayUtils.fillRange(local117, local104, local29, arg4);
		}
	}
}