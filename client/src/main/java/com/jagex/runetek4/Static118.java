package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static118 {

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(B)I")
	public static int method2352() {
		Static232.anInt5212 = 0;
		return Static119.method2385();
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II)I")
	public static int method2356(@OriginalArg(1) int arg0) {
		return arg0 & 0x7F;
	}
}
