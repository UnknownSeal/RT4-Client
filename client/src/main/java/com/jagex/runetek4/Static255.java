package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static255 {

	@OriginalMember(owner = "runetek4.client!ul", name = "I", descriptor = "Lclient!gn;")
	public static final LruHashTable cache = new LruHashTable(4);
}
