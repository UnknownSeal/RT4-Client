package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static74 {

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(ZIIIIIII)V")
	public static void method1623(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (arg3 == arg6) {
			Static152.method2826(arg0, arg2, arg5, arg6, arg1, arg4);
		} else if (arg1 - arg6 >= Static172.anInt4164 && arg6 + arg1 <= FloorUnderlayTypeList.anInt5063 && Static267.anInt5773 <= arg2 - arg3 && Static106.anInt2869 >= arg2 + arg3) {
			Static270.method4594(arg5, arg1, arg2, arg4, arg6, arg3, arg0);
		} else {
			PreciseSleep.method3981(arg6, arg5, arg4, arg0, arg2, arg1, arg3);
		}
	}

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(ILclient!be;)V")
	public static void method1625(@OriginalArg(1) Component arg0) {
		@Pc(7) Component local7 = Static280.method4668(arg0);
		@Pc(19) int local19;
		@Pc(17) int local17;
		if (local7 == null) {
			local17 = GameShell.canvasHeigth;
			local19 = GameShell.canvasWidth;
		} else {
			local17 = local7.height;
			local19 = local7.width;
		}
		Static150.method2801(local17, local19, arg0, false);
		Static111.method2291(arg0, local17, local19);
	}

	@OriginalMember(owner = "client!fn", name = "c", descriptor = "(II)V")
	public static void method1626(@OriginalArg(0) int arg0) {
		if (arg0 == -1 || !Component.load(arg0)) {
			return;
		}
		@Pc(31) Component[] local31 = Component.cachedComponents[arg0];
		for (@Pc(33) int local33 = 0; local33 < local31.length; local33++) {
			@Pc(41) Component local41 = local31[local33];
			if (local41.anObjectArray3 != null) {
				@Pc(50) HookRequest local50 = new HookRequest();
				local50.arguments = local41.anObjectArray3;
				local50.source = local41;
				Static88.runClientScripts(2000000, local50);
			}
		}
	}

}
