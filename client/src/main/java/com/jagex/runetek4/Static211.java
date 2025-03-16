package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static211 {

	@OriginalMember(owner = "runetek4.client!rc", name = "R", descriptor = "Z")
	private static boolean aBoolean74;

	@OriginalMember(owner = "runetek4.client!rc", name = "p", descriptor = "I")
	public static int anInt1142 = 0;

	@OriginalMember(owner = "runetek4.client!rc", name = "C", descriptor = "Z")
	public static boolean aBoolean72 = false;

	@OriginalMember(owner = "runetek4.client!rc", name = "G", descriptor = "Lclient!na;")
	public static final JString aClass100_230 = JString.parse("");

	@OriginalMember(owner = "runetek4.client!rc", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_231 = JString.parse(")3)3)3");

	@OriginalMember(owner = "runetek4.client!rc", name = "M", descriptor = "Z")
	public static boolean aBoolean73 = false;

	@OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Lclient!na;Z)Lclient!na;")
	public static JString method923(@OriginalArg(0) JString arg0) {
		@Pc(12) int local12 = Static171.method3218(arg0);
		return local12 == -1 ? Static93.aClass100_517 : Static203.aMapElementTypeList_1.aClass100Array153[local12].method3140(Static101.aClass100_538, Static197.aClass100_872);
	}

	@OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Z)V")
	public static void method924() {
		Static244.aClass99_32.clear();
	}

}
