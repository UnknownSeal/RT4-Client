package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static189 {

	@OriginalMember(owner = "runetek4.client!pe", name = "n", descriptor = "Lclient!na;")
	public static final JString HINT_MAPMARKERS = JString.parse("hint_mapmarkers");

	@OriginalMember(owner = "runetek4.client!pe", name = "z", descriptor = "Lclient!na;")
	public static final JString MAPDOTS = JString.parse("mapdots");

	@OriginalMember(owner = "runetek4.client!pe", name = "a", descriptor = "(BZ)V")
	public static void method3438(@OriginalArg(1) boolean arg0) {
		if (arg0 != Static30.aBoolean61) {
			Static30.aBoolean61 = arg0;
			Static90.clear();
		}
	}
}
