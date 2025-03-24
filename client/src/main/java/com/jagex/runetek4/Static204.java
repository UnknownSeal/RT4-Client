package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static204 {

	@OriginalMember(owner = "runetek4.client!qi", name = "u", descriptor = "Lclient!ih;")
	public static LinkedList aClass69_113 = new LinkedList();

	@OriginalMember(owner = "runetek4.client!qi", name = "x", descriptor = "[I")
	public static final int[] anIntArray425 = new int[] { 0, 1, 2, 3, 4, 5, 6, 14 };

	@OriginalMember(owner = "runetek4.client!qi", name = "e", descriptor = "(B)V")
	public static void clear() {
		Static93.aClass99_13.clean();
		Static125.aClass99_18.clean();
		Static262.aClass99_35.clean();
	}

	@OriginalMember(owner = "runetek4.client!qi", name = "a", descriptor = "(IIBI)I")
	public static int method3675(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		@Pc(3) int local3 = arg0 & 0x3;
		if (local3 == 0) {
			return arg1;
		} else if (local3 == 1) {
			return arg2;
		} else if (local3 == 2) {
			return 1023 - arg1;
		} else {
			return 1023 - arg2;
		}
	}
}
