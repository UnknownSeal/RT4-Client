package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static180 {

	@OriginalMember(owner = "runetek4.client!og", name = "b", descriptor = "I")
	public static int anInt4264;

	@OriginalMember(owner = "runetek4.client!og", name = "g", descriptor = "[Lclient!na;")
	public static JString[] localStrings;

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(Lclient!ve;Lclient!ve;B)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
		Static250.aClass153_101 = arg1;
		@Pc(12) int local12 = (int) (Math.random() * 21.0D) - 10;
		Static98.aClass153_42 = arg0;
		@Pc(21) int local21 = (int) (Math.random() * 21.0D) - 10;
		Static98.aClass153_42.getGroupCapacity(34);
		@Pc(33) int local33 = (int) (Math.random() * 21.0D) - 10;
		@Pc(40) int local40 = (int) (Math.random() * 41.0D) - 20;
		Static266.anInt5344 = local40 + local21;
		Static85.anInt2262 = local12 + local40;
		Static173.anInt4183 = local40 + local33;
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(BIILclient!fe;III)V")
	public static void method3326(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) PathingEntity arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		Static198.method1026(arg5, arg1, arg2.zFine, arg4, arg0, arg2.xFine, arg3);
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		Static172.gameDefinitionsJs5 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(III)I")
	public static int method3330(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (arg0 > 22050) {
			arg1 = arg0;
			arg0 = 22050;
		}
		while (arg0 != 0) {
			@Pc(21) int local21 = arg1 % arg0;
			arg1 = arg0;
			arg0 = local21;
		}
		return arg1;
	}
}
