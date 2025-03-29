package com.jagex.runetek4;

import com.jagex.runetek4.game.world.entity.PlayerAppearance;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static230 {

	@OriginalMember(owner = "runetek4.client!sj", name = "c", descriptor = "(I)V")
	public static void clear() {
		PlayerAppearance.bodyModels.clear();
		PlayerAppearance.headModels.clear();
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(IIBIII)V")
	public static void method3950(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 >= Static172.anInt4164 && arg3 <= FloorUnderlayTypeList.anInt5063 && Static267.anInt5773 <= arg4 && Static106.anInt2869 >= arg2) {
			Static176.method3308(arg2, arg3, arg4, arg0, arg1);
		} else {
			Static163.method3105(arg1, arg3, arg4, arg0, arg2);
		}
	}

}
