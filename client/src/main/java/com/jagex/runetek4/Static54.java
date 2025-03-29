package com.jagex.runetek4;

import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static54 {

	@OriginalMember(owner = "client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static172.anInt4164 && arg3 <= FloorUnderlayTypeList.anInt5063) {
			@Pc(22) int local22 = IntUtils.clamp(Static106.anInt2869, arg1, Static267.anInt5773);
			@Pc(28) int local28 = IntUtils.clamp(Static106.anInt2869, arg0, Static267.anInt5773);
			Static101.method2054(local22, arg3, local28, arg2);
		}
	}

	@OriginalMember(owner = "client!ed", name = "c", descriptor = "(I)V")
	public static void clear() {
		FloorUnderlayTypeList.types.clear();
	}

}
