package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static4 {

	@OriginalMember(owner = "runetek4.client!ac", name = "e", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray1;

	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;

	@OriginalMember(owner = "runetek4.client!ac", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_7 = JString.parse("overlay");

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(IIII)I")
	public static int getRenderLevel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		if ((SceneGraph.renderFlags[arg2][arg1][arg0] & 0x8) == 0) {
			return arg2 <= 0 || (SceneGraph.renderFlags[1][arg1][arg0] & 0x2) == 0 ? arg2 : arg2 - 1;
		} else {
			return 0;
		}
	}

}