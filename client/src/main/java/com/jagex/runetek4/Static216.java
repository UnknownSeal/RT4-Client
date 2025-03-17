package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static216 {

	@OriginalMember(owner = "runetek4.client!ri", name = "c", descriptor = "Lclient!ve;")
	public static Js5 aClass153_31;

	@OriginalMember(owner = "runetek4.client!ri", name = "d", descriptor = "[I")
	public static int[] anIntArray188;

	@OriginalMember(owner = "runetek4.client!ri", name = "b", descriptor = "[I")
	public static final int[] anIntArray187 = new int[14];

	@OriginalMember(owner = "runetek4.client!ri", name = "a", descriptor = "(II)I")
	public static int method1640(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg1 = arg1 * (arg0 & 0x7F) >> 7;
		if (arg1 < 2) {
			arg1 = 2;
		} else if (arg1 > 126) {
			arg1 = 126;
		}
		return (arg0 & 0xFF80) + arg1;
	}
}
