package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static190 {

	@OriginalMember(owner = "runetek4.client!pf", name = "h", descriptor = "[J")
	public static final long[] ignoreName37 = new long[100];

	@OriginalMember(owner = "runetek4.client!pf", name = "r", descriptor = "[I")
	public static final int[] anIntArray382 = new int[500];

	@OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(IIZIII)Lclient!qf;")
	public static Sprite method3443(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		@Pc(27) int local27 = (arg2 ? 65536 : 0) + arg1 + (arg0 << 17) + (arg4 << 19);
		@Pc(37) long local37 = (long) local27 * 3849834839L + (long) arg3 * 3147483667L;
		@Pc(43) Sprite local43 = (Sprite) Static118.aClass99_16.get(local37);
		if (local43 != null) {
			return local43;
		}
		Pix3D.aBoolean134 = false;
		local43 = Static164.method3150(arg4, false, arg1, arg2, arg0, arg3, false);
		if (local43 != null && !Pix3D.aBoolean134) {
			Static118.aClass99_16.put(local43, local37);
		}
		return local43;
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(III)V")
	public static void method3444(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(14) DelayedStateChange local14 = Static238.method4143(13, arg1);
		local14.method1017();
		local14.intArg1 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "c", descriptor = "(II)V")
	public static void method3447() {
		Static27.aClass99_4.clear(5);
		Static244.aClass99_32.clear(5);
		Static118.aClass99_16.clear(5);
	}
}
