package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static211 {

	@OriginalMember(owner = "runetek4.client!rc", name = "R", descriptor = "Z")
	private static boolean aBoolean74;

	@OriginalMember(owner = "runetek4.client!rc", name = "M", descriptor = "Z")
	public static boolean aBoolean73 = false;

	@OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Lclient!na;Z)Lclient!na;")
	public static JString method923(@OriginalArg(0) JString arg0) {
		@Pc(12) int local12 = WorldMap.method3218(arg0);
		return local12 == -1 ? WorldMap.aClass100_517 : WorldMap.labels.aClass100Array153[local12].method3140(WorldMap.aClass100_538, WorldMap.aClass100_872);
	}

}
