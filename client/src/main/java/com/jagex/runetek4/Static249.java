package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static249 {

	@OriginalMember(owner = "runetek4.client!ud", name = "d", descriptor = "(I)V")
	public static void clear() {
		VarPlayerDefinition.varPlayerDefinitionCache.clear();
	}
}
