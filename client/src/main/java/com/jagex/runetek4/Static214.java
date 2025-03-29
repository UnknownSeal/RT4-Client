package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static214 {

	@OriginalMember(owner = "runetek4.client!rg", name = "d", descriptor = "(B)Lclient!bn;")
	public static Map method4361() {
		return WorldMap.currentMap;
	}

	@OriginalMember(owner = "runetek4.client!rg", name = "a", descriptor = "(IIIIIIIII)V")
	public static void method4364(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		@Pc(7) int local7 = arg2 - arg7;
		@Pc(16) int local16 = (arg0 - arg4 << 16) / local7;
		@Pc(21) int local21 = arg5 - arg3;
		@Pc(30) int local30 = (arg6 - arg1 << 16) / local21;
		Static144.method2735(arg1, arg5, arg3, arg2, arg4, arg7, local30, local16);
	}
}
