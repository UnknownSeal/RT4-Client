package com.jagex.runetek4;

import com.jagex.runetek4.game.config.bastype.BasTypeList;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.NodeCache;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static79 {

	@OriginalMember(owner = "runetek4.client!ge", name = "q", descriptor = "I")
	public static int anInt2161;

	@OriginalMember(owner = "runetek4.client!ge", name = "i", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_11 = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!ge", name = "k", descriptor = "[I")
	public static int[] anIntArray205 = new int[2];

	@OriginalMember(owner = "runetek4.client!ge", name = "m", descriptor = "I")
	public static int chatEffectsDisabled = 0;

	@OriginalMember(owner = "runetek4.client!ge", name = "a", descriptor = "(IIIZIZZ)V")
	public static void method1697(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5) {
		if (arg2 <= arg4) {
			return;
		}
		@Pc(13) int local13 = (arg2 + arg4) / 2;
		@Pc(15) int local15 = arg4;
		@Pc(19) GWCWorld local19 = Static101.aClass10_Sub1Array1[local13];
		Static101.aClass10_Sub1Array1[local13] = Static101.aClass10_Sub1Array1[arg2];
		Static101.aClass10_Sub1Array1[arg2] = local19;
		for (@Pc(31) int local31 = arg4; local31 < arg2; local31++) {
			if (Static164.method3115(local19, Static101.aClass10_Sub1Array1[local31], arg0, arg1, arg3, arg5) <= 0) {
				@Pc(53) GWCWorld local53 = Static101.aClass10_Sub1Array1[local31];
				Static101.aClass10_Sub1Array1[local31] = Static101.aClass10_Sub1Array1[local15];
				Static101.aClass10_Sub1Array1[local15++] = local53;
			}
		}
		Static101.aClass10_Sub1Array1[arg2] = Static101.aClass10_Sub1Array1[local15];
		Static101.aClass10_Sub1Array1[local15] = local19;
		method1697(arg0, arg1, local15 - 1, arg3, arg4, arg5);
		method1697(arg0, arg1, arg2, arg3, local15 + 1, arg5);
	}

	@OriginalMember(owner = "runetek4.client!ge", name = "a", descriptor = "(BLclient!ve;)V")
	public static void method1703(@OriginalArg(1) Js5 arg0) {
		BasTypeList.archive = arg0;
	}
}
