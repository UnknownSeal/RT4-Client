package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static3 {

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method4656(@OriginalArg(0) JString arg0) {
		@Pc(9) int local9 = WorldMap.method3218(arg0);
		if (local9 != -1) {
			Static80.method3616(WorldMap.labels.aShortArray73[local9], WorldMap.labels.aShortArray72[local9]);
		}
	}

}
