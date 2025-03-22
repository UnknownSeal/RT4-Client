package com.jagex.runetek4.util;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!hf")
public final class MathUtils {

	@OriginalMember(owner = "runetek4.client!hf", name = "g", descriptor = "[I")
	public static final int[] sin = new int[2048];

	@OriginalMember(owner = "runetek4.client!hf", name = "h", descriptor = "[I")
	public static final int[] reciprical16 = new int[2048];

	@OriginalMember(owner = "runetek4.client!hf", name = "f", descriptor = "[I")
	private static final int[] reciprical15 = new int[512];

	@OriginalMember(owner = "runetek4.client!hf", name = "r", descriptor = "[I")
	public static final int[] cos = new int[2048];

	static {
		@Pc(33) int local33;
		for (local33 = 1; local33 < 512; local33++) {
			reciprical15[local33] = 32768 / local33;
		}
		for (local33 = 1; local33 < 2048; local33++) {
			reciprical16[local33] = 65536 / local33;
		}
		for (local33 = 0; local33 < 2048; local33++) {
			sin[local33] = (int) (Math.sin((double) local33 * 0.0030679615D) * 65536.0D);
			cos[local33] = (int) (Math.cos((double) local33 * 0.0030679615D) * 65536.0D);
		}
	}
}
