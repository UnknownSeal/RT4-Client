package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static249 {

	@OriginalMember(owner = "runetek4.client!ud", name = "O", descriptor = "I")
	public static int anInt5431 = 0;

	@OriginalMember(owner = "runetek4.client!ud", name = "P", descriptor = "Lclient!gn;")
	public static final LruHashTable recentUse = new LruHashTable(64);

	@OriginalMember(owner = "runetek4.client!ud", name = "Q", descriptor = "Lclient!na;")
	public static final JString aClass100_1039 = JString.parse(" x ");

	@OriginalMember(owner = "runetek4.client!ud", name = "T", descriptor = "[I")
	public static final int[] anIntArray478 = new int[32];

	@OriginalMember(owner = "runetek4.client!ud", name = "a", descriptor = "(ILclient!be;)Z")
	public static boolean method4265(@OriginalArg(1) Component arg0) {
		if (arg0.contentType == 205) {
			Game.idleTimeout = 250;
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "runetek4.client!ud", name = "d", descriptor = "(I)V")
	public static void clear() {
		VarPlayerDefinition.varPlayerDefinitionCache.clear();
	}
}
