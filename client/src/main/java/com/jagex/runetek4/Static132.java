package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static132 {

	@OriginalMember(owner = "runetek4.client!ke", name = "R", descriptor = "Lclient!ve;")
	public static Js5 aClass153_48;

	@OriginalMember(owner = "runetek4.client!ke", name = "f", descriptor = "(B)V")
	public static void method2608() {
		@Pc(7) int local7 = 0;
		for (@Pc(23) int local23 = 0; local23 < 104; local23++) {
			for (@Pc(30) int local30 = 0; local30 < 104; local30++) {
				if (Static254.method4348(true, local23, local30, SceneGraph.tiles, local7)) {
					local7++;
				}
				if (local7 >= 512) {
					return;
				}
			}
		}
	}

}
