package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static39 {

	@OriginalMember(owner = "runetek4.client!d", name = "hb", descriptor = "Lclient!ve;")
	public static Js5 aClass153_23;

	@OriginalMember(owner = "runetek4.client!d", name = "db", descriptor = "Z")
	public static boolean aBoolean77 = false;

	@OriginalMember(owner = "runetek4.client!d", name = "eb", descriptor = "[S")
	public static final short[] aShortArray6 = new short[500];

	@OriginalMember(owner = "runetek4.client!d", name = "c", descriptor = "(III)I")
	public static int method990(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(11) int local11 = arg0 >> 31 & arg1 - 1;
		return ((arg0 >>> 31) + arg0) % arg1 + local11;
	}
}
