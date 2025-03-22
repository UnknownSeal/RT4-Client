package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static141 {

	@OriginalMember(owner = "runetek4.client!lb", name = "s", descriptor = "Lclient!na;")
	public static final JString CLAN = JString.parse(":clan:");

	@OriginalMember(owner = "runetek4.client!lb", name = "u", descriptor = "I")
	public static int anInt3469 = 0;

	@OriginalMember(owner = "runetek4.client!lb", name = "a", descriptor = "(Lclient!ve;Lclient!ve;ILclient!ve;)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1, @OriginalArg(3) Js5 arg2) {
		Static243.aClass153_98 = arg1;
		Static5.aClass153_1 = arg0;
		Static225.aClass153_92 = arg2;
	}
}
