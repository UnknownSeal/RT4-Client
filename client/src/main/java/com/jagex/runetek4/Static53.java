package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static53 {

	@OriginalMember(owner = "client!ec", name = "d", descriptor = "Lclient!gn;")
	public static final LruHashTable aClass54_5 = new LruHashTable(16);

	@OriginalMember(owner = "client!ec", name = "b", descriptor = "(I)V")
	public static void clear() {
		Static125.varbitDefinitionCache.clean();
	}
}
