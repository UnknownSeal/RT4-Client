package com.jagex.runetek4;

import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static101 {

	@OriginalMember(owner = "runetek4.client!hm", name = "gb", descriptor = "J")
	public static long aLong98;

	@OriginalMember(owner = "runetek4.client!hm", name = "R", descriptor = "Lclient!na;")
	public static final JString aClass100_537 = JString.parse("<img=1>");

	@OriginalMember(owner = "runetek4.client!hm", name = "T", descriptor = "Lclient!na;")
	public static final JString aClass100_538 = JString.parse(" ");

	@OriginalMember(owner = "runetek4.client!hm", name = "fb", descriptor = "[Lclient!ba;")
	public static GWCWorld[] aClass10_Sub1Array1 = new GWCWorld[0];

	@OriginalMember(owner = "runetek4.client!hm", name = "a", descriptor = "(IIIII)V")
	public static void method2054(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) int local8;
		if (arg0 <= arg2) {
			for (local8 = arg0; local8 < arg2; local8++) {
				ObjTypeList.anIntArrayArray10[local8][arg1] = arg3;
			}
		} else {
			for (local8 = arg2; local8 < arg0; local8++) {
				ObjTypeList.anIntArrayArray10[local8][arg1] = arg3;
			}
		}
	}
}
