package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static209 {

	@OriginalMember(owner = "runetek4.client!ra", name = "s", descriptor = "I")
	public static int port;

	@OriginalMember(owner = "runetek4.client!ra", name = "K", descriptor = "Lclient!ve;")
	public static Js5 aClass153_86;

	@OriginalMember(owner = "runetek4.client!ra", name = "J", descriptor = "I")
	public static int miscTransmitAt = 0;

	@OriginalMember(owner = "runetek4.client!ra", name = "R", descriptor = "Z")
	public static boolean aBoolean240 = true;

	@OriginalMember(owner = "runetek4.client!ra", name = "jb", descriptor = "J")
	public static volatile long aLong161 = 0L;

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(BI)Z")
	public static boolean method3702(@OriginalArg(1) int arg0) {
		return arg0 == (-arg0 & arg0);
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "c", descriptor = "(BI)V")
	public static void method3706() {
		Static93.aClass99_13.clear(5);
		Static125.aClass99_18.clear(5);
		Static262.aClass99_35.clear(5);
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(BIII)V")
	public static void method3707(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(9, arg0);
		local12.method1017();
		local12.intArg1 = arg2;
		local12.intArg3 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(IBILclient!ve;)[Lclient!qf;")
	public static Sprite[] method3708(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return Static234.method4016(arg1, 0, arg0) ? Static213.method3730() : null;
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "b", descriptor = "(III)I")
	public static int method3709(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(15) int local15 = 1;
		while (arg0 > 1) {
			if ((arg0 & 0x1) != 0) {
				local15 *= arg1;
			}
			arg1 *= arg1;
			arg0 >>= 0x1;
		}
		if (arg0 == 1) {
			return local15 * arg1;
		} else {
			return local15;
		}
	}
}
