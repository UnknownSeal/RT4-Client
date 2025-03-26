package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static180 {

	@OriginalMember(owner = "runetek4.client!og", name = "b", descriptor = "I")
	public static int anInt4264;

	@OriginalMember(owner = "runetek4.client!og", name = "g", descriptor = "[Lclient!na;")
	public static JString[] localStrings;

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(BIILclient!fe;III)V")
	public static void method3326(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) PathingEntity arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		ClientScriptRunner.method1026(arg5, arg1, arg2.zFine, arg4, arg0, arg2.xFine, arg3);
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		Static172.gameDefinitionsJs5 = arg0;
	}

}
