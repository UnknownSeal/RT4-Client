package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static179 {

	@OriginalMember(owner = "runetek4.client!oe", name = "a", descriptor = "I")
	public static int mouseRecorderPrevY = 0;

	@OriginalMember(owner = "runetek4.client!oe", name = "b", descriptor = "I")
	public static int bankInsertMode = 0;

	@OriginalMember(owner = "runetek4.client!oe", name = "i", descriptor = "[[I")
	public static final int[][] anIntArrayArray33 = new int[5][5000];

	@OriginalMember(owner = "runetek4.client!oe", name = "a", descriptor = "(IZ)I")
	public static int method3322(@OriginalArg(0) int arg0) {
		return arg0 & 0x7F;
	}

}
