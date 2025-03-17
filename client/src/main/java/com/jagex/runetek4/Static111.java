package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static111 {

	@OriginalMember(owner = "runetek4.client!ii", name = "i", descriptor = "I")
	public static int anInt2903;

	@OriginalMember(owner = "runetek4.client!ii", name = "l", descriptor = "I")
	public static int anInt2905;

	@OriginalMember(owner = "runetek4.client!ii", name = "y", descriptor = "I")
	public static int anInt2910;

	@OriginalMember(owner = "runetek4.client!ii", name = "c", descriptor = "I")
	public static int anInt2901 = 0;

	@OriginalMember(owner = "runetek4.client!ii", name = "e", descriptor = "Lclient!na;")
	public static final JString aClass100_570 = JString.parse(")2");

	@OriginalMember(owner = "runetek4.client!ii", name = "a", descriptor = "(Lclient!be;III)V")
	public static void method2291(@OriginalArg(0) Component arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (arg0.xMode == 0) {
			arg0.y = arg0.baseY;
		} else if (arg0.xMode == 1) {
			arg0.y = (arg1 - arg0.anInt459) / 2 + arg0.baseY;
		} else if (arg0.xMode == 2) {
			arg0.y = arg1 - arg0.anInt459 - arg0.baseY;
		} else if (arg0.xMode == 3) {
			arg0.y = arg0.baseY * arg1 >> 14;
		} else if (arg0.xMode == 4) {
			arg0.y = (arg1 * arg0.baseY >> 14) + (arg1 - arg0.anInt459) / 2;
		} else {
			arg0.y = arg1 - (arg1 * arg0.baseY >> 14) - arg0.anInt459;
		}
		if (arg0.yMode == 0) {
			arg0.x = arg0.baseX;
		} else if (arg0.yMode == 1) {
			arg0.x = arg0.baseX + (arg2 - arg0.anInt445) / 2;
		} else if (arg0.yMode == 2) {
			arg0.x = arg2 - arg0.baseX - arg0.anInt445;
		} else if (arg0.yMode == 3) {
			arg0.x = arg0.baseX * arg2 >> 14;
		} else if (arg0.yMode == 4) {
			arg0.x = (arg0.baseX * arg2 >> 14) + (arg2 - arg0.anInt445) / 2;
		} else {
			arg0.x = arg2 - (arg2 * arg0.baseX >> 14) - arg0.anInt445;
		}
		if (!Cheat.qaOpTest || InterfaceList.getServerActiveProperties(arg0).anInt546 == 0 && arg0.INVENTORY != 0) {
			return;
		}
		if (arg0.y < 0) {
			arg0.y = 0;
		} else if (arg0.anInt459 + arg0.y > arg1) {
			arg0.y = arg1 - arg0.anInt459;
		}
		if (arg0.x < 0) {
			arg0.x = 0;
		} else if (arg2 < arg0.x + arg0.anInt445) {
			arg0.x = arg2 - arg0.anInt445;
		}
	}

}
