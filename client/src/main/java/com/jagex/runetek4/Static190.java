package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static190 {

	@OriginalMember(owner = "runetek4.client!pf", name = "h", descriptor = "[J")
	public static final long[] ignoreName37 = new long[100];

	@OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(III)V")
	public static void method3444(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(14) DelayedStateChange local14 = Static238.method4143(13, arg1);
		local14.method1017();
		local14.intArg1 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "c", descriptor = "(II)V")
	public static void method3447() {
		Static27.aClass99_4.clean(5);
		Static244.aClass99_32.clean(5);
		Static118.aClass99_16.clean(5);
	}
}
