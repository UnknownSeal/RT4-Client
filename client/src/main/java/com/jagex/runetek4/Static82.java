package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static82 {

	@OriginalMember(owner = "client!gi", name = "a", descriptor = "(IIIIB)V")
	public static void method1760(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (arg1 - arg0 >= Static172.anInt4164 && FloorUnderlayTypeList.anInt5063 >= arg0 + arg1 && arg3 - arg0 >= Static267.anInt5773 && Static106.anInt2869 >= arg0 + arg3) {
			Static49.method1206(arg1, arg0, arg2, arg3);
		} else {
			Static89.method1843(arg2, arg3, arg0, arg1);
		}
	}

}
