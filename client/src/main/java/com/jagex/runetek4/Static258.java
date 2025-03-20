package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static258 {

	@OriginalMember(owner = "runetek4.client!va", name = "G", descriptor = "[Z")
	public static boolean[] aBooleanArray130;

	@OriginalMember(owner = "runetek4.client!va", name = "b", descriptor = "(II)V")
	public static void method4415() {
		Static179.aClass99_25.clean(5);
		Static169.modelCacheStatic.clean(5);
		Static93.aClass99_14.clean(5);
		Static262.aClass99_36.clean(5);
	}

	@OriginalMember(owner = "runetek4.client!va", name = "c", descriptor = "(BI)V")
	public static void method4444(@OriginalArg(1) int arg0) {
		CacheArchive.anInt172 = arg0;
		ClientScriptRunner.anInt2428 = 20;
		WorldMap.anInt1864 = 3;
	}
}
