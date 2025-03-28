package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class BZip2State {

	@OriginalMember(owner = "runetek4.client!s", name = "a", descriptor = "(II)I")
	public static int method3389(@OriginalArg(0) int arg0) {
		return arg0 >>> 7;
	}

}
