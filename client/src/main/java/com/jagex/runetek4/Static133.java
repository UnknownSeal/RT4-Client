package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static133 {

	@OriginalMember(owner = "runetek4.client!kf", name = "c", descriptor = "J")
	public static long clickTime = 0L;

	@OriginalMember(owner = "runetek4.client!kf", name = "d", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] aClass3_Sub2_Sub7Array7 = new AnimFrameset[14];

	@OriginalMember(owner = "runetek4.client!kf", name = "a", descriptor = "(II)I")
	public static int method4010(@OriginalArg(0) int arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "runetek4.client!kf", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method4011(@OriginalArg(0) JString arg0) {
		for (@Pc(15) Map local15 = (Map) MapList.aClass69_120.head(); local15 != null; local15 = (Map) MapList.aClass69_120.next()) {
			if (local15.group.strEquals(arg0)) {
				Static269.aClass3_Sub2_Sub4_2 = local15;
				return;
			}
		}
	}

}
