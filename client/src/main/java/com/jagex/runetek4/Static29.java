package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static29 {

	@OriginalMember(owner = "client!ce", name = "d", descriptor = "Lclient!ve;")
	public static Js5 aClass153_19;

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(II)V")
	public static void method801() {
		Static253.anInt5527 = 0;
		Static226.anInt5085 = -1;
		Static14.anInt441 = 1;
		Static57.anInt1757 = 2;
		Static72.aBoolean116 = false;
		Static172.aClass153_70 = null;
		Static277.anInt5853 = -1;
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(IBI)Lclient!bn;")
	public static Map method803(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		for (@Pc(10) Map local10 = (Map) Static228.aClass69_120.head(); local10 != null; local10 = (Map) Static228.aClass69_120.next()) {
			if (local10.aBoolean50 && local10.method664(arg1, arg0)) {
				return local10;
			}
		}
		return null;
	}
}
