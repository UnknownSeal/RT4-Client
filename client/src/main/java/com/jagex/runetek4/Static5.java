package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static5 {

	@OriginalMember(owner = "runetek4.client!af", name = "g", descriptor = "Lclient!na;")
	public static final JString HINT_MAPEDGE = JString.parse("hint_mapedge");

	@OriginalMember(owner = "runetek4.client!af", name = "b", descriptor = "(B)V")
	public static void method34() {
		WorldMap.clear(false);
		System.gc();
		client.processGameStatus(25);
	}

}
