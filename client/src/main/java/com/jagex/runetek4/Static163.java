package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static163 {

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "I")
	public static int anInt3962;

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(IIIIII)V")
	public static void method3105(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(11) int local11 = IntUtils.clamp(Static106.anInt2869, arg2, Static267.anInt5773);
		@Pc(17) int local17 = IntUtils.clamp(Static106.anInt2869, arg4, Static267.anInt5773);
		@Pc(23) int local23 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3, Static172.anInt4164);
		@Pc(35) int local35 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg1, Static172.anInt4164);
		for (@Pc(37) int local37 = local11; local37 <= local17; local37++) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local37], local23, local35, arg0);
		}
	}
}
