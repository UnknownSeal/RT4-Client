package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static254 {

	@OriginalMember(owner = "runetek4.client!uj", name = "E", descriptor = "I")
	public static int anInt5556;

	@OriginalMember(owner = "runetek4.client!uj", name = "s", descriptor = "Lclient!na;")
	public static final JString aClass100_1061 = JString.parse("null");

	@OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(BLclient!ve;I)Z")
	public static boolean method4346(@OriginalArg(1) Js5 arg0, @OriginalArg(2) int arg1) {
		@Pc(13) byte[] local13 = arg0.method4500(arg1);
		if (local13 == null) {
			return false;
		} else {
			Static84.method1770(local13);
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(II)I")
	public static int method4349(@OriginalArg(0) int arg0) {
		return arg0 >>> 10;
	}

}
