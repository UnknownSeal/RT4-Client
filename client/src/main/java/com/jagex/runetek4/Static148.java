package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static148 {

	@OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(III)V")
	public static void method2766(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		VarpDomain.activeVarps[arg0] = arg1;
		@Pc(21) LongNode local21 = (LongNode) VarpDomain.aClass133_20.get((long) arg0);
		if (local21 == null) {
			local21 = new LongNode(MonotonicTime.currentTimeMillis() + 500L);
			VarpDomain.aClass133_20.put(local21, (long) arg0);
		} else {
			local21.value = MonotonicTime.currentTimeMillis() + 500L;
		}
	}

}
