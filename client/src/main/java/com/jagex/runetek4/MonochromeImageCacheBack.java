package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!a")
public final class MonochromeImageCacheBack {

	@OriginalMember(owner = "runetek4.client!a", name = "b", descriptor = "[I")
	public static final int[] anIntArray1 = new int[4096];

	static {
		for (@Pc(4) int local4 = 0; local4 < 4096; local4++) {
			anIntArray1[local4] = Static273.fade(local4);
		}
	}
}
