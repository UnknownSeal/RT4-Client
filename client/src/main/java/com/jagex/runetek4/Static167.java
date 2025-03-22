package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static167 {

	@OriginalMember(owner = "runetek4.client!nd", name = "s", descriptor = "I")
	public static int eyeTileX;

	@OriginalMember(owner = "runetek4.client!nd", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_780 = JString.parse("Clientscript error in: ");

	@OriginalMember(owner = "runetek4.client!nd", name = "q", descriptor = "[[I")
	public static final int[][] anIntArrayArray31 = new int[104][104];

	@OriginalMember(owner = "runetek4.client!nd", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_783 = JString.parse(")4p=");

	@OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "(ZLclient!qb;)V")
	public static void method3170(@OriginalArg(1) PcmStream arg0) {
		if (arg0.aClass3_Sub16_5 != null) {
			arg0.aClass3_Sub16_5.anInt3313 = 0;
		}
		arg0.aBoolean292 = false;
		for (@Pc(14) PcmStream local14 = arg0.method4406(); local14 != null; local14 = arg0.method4409()) {
			method3170(local14);
		}
	}

}
