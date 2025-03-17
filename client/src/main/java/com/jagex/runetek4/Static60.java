package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static60 {

	@OriginalMember(owner = "runetek4.client!em", name = "t", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray6;

	@OriginalMember(owner = "runetek4.client!em", name = "w", descriptor = "I")
	public static int anInt1892;

	@OriginalMember(owner = "runetek4.client!em", name = "u", descriptor = "Lclient!na;")
	public static final JString aClass100_420 = JString.parse(")1o");

	@OriginalMember(owner = "runetek4.client!em", name = "y", descriptor = "I")
	public static int mouseClickY = 0;

	@OriginalMember(owner = "runetek4.client!em", name = "B", descriptor = "I")
	public static int systemUpdateTimer = 0;

	@OriginalMember(owner = "runetek4.client!em", name = "a", descriptor = "(II)I")
	public static int method1447(@OriginalArg(0) int arg0) {
		return arg0 >>> 7;
	}
}
