package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static179 {

	@OriginalMember(owner = "runetek4.client!oe", name = "a", descriptor = "I")
	public static int mouseRecorderPrevY = 0;

	@OriginalMember(owner = "runetek4.client!oe", name = "b", descriptor = "I")
	public static int anInt4254 = 0;

	@OriginalMember(owner = "runetek4.client!oe", name = "i", descriptor = "[[I")
	public static final int[][] anIntArrayArray33 = new int[5][5000];

	@OriginalMember(owner = "runetek4.client!oe", name = "j", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_25 = new SoftLruHashTable(64);

	@OriginalMember(owner = "runetek4.client!oe", name = "l", descriptor = "I")
	public static int step = 0;

	@OriginalMember(owner = "runetek4.client!oe", name = "n", descriptor = "I")
	public static int minimapZoomModifier = 1;

	@OriginalMember(owner = "runetek4.client!oe", name = "o", descriptor = "Lclient!na;")
	public static final JString aClass100_807 = Static28.parse("n");

	@OriginalMember(owner = "runetek4.client!oe", name = "a", descriptor = "(IZ)I")
	public static int method3322(@OriginalArg(0) int arg0) {
		return arg0 & 0x7F;
	}

	@OriginalMember(owner = "runetek4.client!oe", name = "b", descriptor = "(I)V")
	public static void method3323() {
		aClass99_25.method3103();
		Static169.modelCacheStatic.method3103();
		Static93.aClass99_14.method3103();
		Static262.aClass99_36.method3103();
	}
}
