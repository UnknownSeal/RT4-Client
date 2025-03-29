package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static80 {

	@OriginalMember(owner = "client!gf", name = "a", descriptor = "(BII)V")
	public static void method3616(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		WorldMap.anInt3482 = arg0 - WorldMap.originX;
		@Pc(24) int local24 = WorldMap.anInt3482 - (int) ((float) WorldMap.component.width / WorldMap.zoom);
		@Pc(33) int local33 = WorldMap.anInt3482 + (int) ((float) WorldMap.component.width / WorldMap.zoom);
		if (local24 < 0) {
			WorldMap.anInt3482 = (int) ((float) WorldMap.component.width / WorldMap.zoom);
		}
		WorldMap.anInt4901 = WorldMap.length + WorldMap.originZ - arg1 - 1;
		@Pc(61) int local61 = (int) ((float) WorldMap.component.height / WorldMap.zoom) + WorldMap.anInt4901;
		@Pc(70) int local70 = WorldMap.anInt4901 - (int) ((float) WorldMap.component.height / WorldMap.zoom);
		if (local33 > WorldMap.width) {
			WorldMap.anInt3482 = WorldMap.width - (int) ((float) WorldMap.component.width / WorldMap.zoom);
		}
		if (local70 < 0) {
			WorldMap.anInt4901 = (int) ((float) WorldMap.component.height / WorldMap.zoom);
		}
		if (WorldMap.length < local61) {
			WorldMap.anInt4901 = WorldMap.length - (int) ((float) WorldMap.component.height / WorldMap.zoom);
		}
	}

}
