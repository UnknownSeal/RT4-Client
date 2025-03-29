package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static6 {

	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(IIIIIIIII)V")
	public static void method86(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		if (InterfaceList.load(arg0)) {
			ClientScriptRunner.renderComponent(arg1, arg7, arg3, InterfaceList.cachedComponents[arg0], arg2, -1, arg6, arg5, arg4);
		} else if (arg4 == -1) {
			for (@Pc(27) int local27 = 0; local27 < 100; local27++) {
				InterfaceList.aBooleanArray100[local27] = true;
			}
		} else {
			InterfaceList.aBooleanArray100[arg4] = true;
		}
	}
}
