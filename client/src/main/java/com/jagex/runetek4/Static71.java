package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimType;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static71 {

	@OriginalMember(owner = "client!fk", name = "a", descriptor = "(B)V")
	public static void removeSoft() {
		Static279.aClass99_38.removeSoft();
		SpotAnimType.modelCache.removeSoft();
	}

}
