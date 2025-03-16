package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static90 {

	@OriginalMember(owner = "runetek4.client!hb", name = "v", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray8;

	@OriginalMember(owner = "runetek4.client!hb", name = "t", descriptor = "[I")
	public static final int[] DIRECTION_ALLOW_WALL_CORNER_TYPE = new int[] { 160, 192, 80, 96, 0, 144, 80, 48, 160 };

	@OriginalMember(owner = "runetek4.client!hb", name = "b", descriptor = "(Lclient!na;I)V")
	public static void method1853(@OriginalArg(0) JString arg0) {
		WorldMap.clear(false);
		Static133.method4011(arg0);
	}

	@OriginalMember(owner = "runetek4.client!hb", name = "c", descriptor = "(I)V")
	public static void clear() {
		Static179.aClass99_25.clear();
		Static169.modelCacheStatic.clear();
		Static93.aClass99_14.clear();
		Static262.aClass99_36.clear();
	}

	@OriginalMember(owner = "runetek4.client!hb", name = "b", descriptor = "(II)Z")
	public static boolean method1855(@OriginalArg(0) int arg0) {
		return arg0 >= 0 && Static258.aBooleanArray130.length > arg0 ? Static258.aBooleanArray130[arg0] : false;
	}

	@OriginalMember(owner = "runetek4.client!hb", name = "a", descriptor = "(Z)V")
	public static void method1857() {
		Static45.aClass99_6.clear();
	}
}
