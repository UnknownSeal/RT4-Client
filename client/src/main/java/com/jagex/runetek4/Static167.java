package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static167 {

	@OriginalMember(owner = "runetek4.client!nd", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_783 = JString.parse(")4p=");

	@OriginalMember(owner = "runetek4.client!nd", name = "a", descriptor = "(ZLclient!qb;)V")
	public static void method3170(@OriginalArg(1) PcmStream arg0) {
		if (arg0.sound != null) {
			arg0.sound.anInt3313 = 0;
		}
		arg0.active = false;
		for (@Pc(14) PcmStream local14 = arg0.firstSubStream(); local14 != null; local14 = arg0.nextSubStream()) {
			method3170(local14);
		}
	}

}
