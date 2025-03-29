package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static155 {

	@OriginalMember(owner = "runetek4.client!me", name = "a", descriptor = "(IB)V")
	public static void method2940(@OriginalArg(0) int arg0) {
		WorldMap.anInt4901 = -1;
		if (arg0 == 37) {
			WorldMap.targetZoom = 3.0F;
		} else if (arg0 == 50) {
			WorldMap.targetZoom = 4.0F;
		} else if (arg0 == 75) {
			WorldMap.targetZoom = 6.0F;
		} else if (arg0 == 100) {
			WorldMap.targetZoom = 8.0F;
		} else if (arg0 == 200) {
			WorldMap.targetZoom = 16.0F;
		}
		WorldMap.anInt4901 = -1;
	}

}
