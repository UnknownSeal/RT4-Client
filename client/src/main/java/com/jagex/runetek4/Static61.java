package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static61 {

	@OriginalMember(owner = "runetek4.client!en", name = "z", descriptor = "F")
	public static float aFloat7;

	@OriginalMember(owner = "runetek4.client!en", name = "e", descriptor = "Lclient!na;")
	public static final JagString FPSOFF = Static28.parse("::fpsoff");

	@OriginalMember(owner = "runetek4.client!en", name = "h", descriptor = "Lclient!na;")
	public static final JagString CHALREQ = Static28.parse(":chalreq:");

	@OriginalMember(owner = "runetek4.client!en", name = "t", descriptor = "[I")
	public static final int[] anIntArray148 = new int[14];

	@OriginalMember(owner = "runetek4.client!en", name = "x", descriptor = "Lclient!na;")
	public static final JagString aClass100_424 = Static28.parse("http:)4)4");

	@OriginalMember(owner = "runetek4.client!en", name = "A", descriptor = "Z")
	public static boolean aBoolean109 = false;

	@OriginalMember(owner = "runetek4.client!en", name = "a", descriptor = "(IIIB)V")
	public static void teleport(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(66) JagString local66 = Static34.method882(new JagString[] { Static96.aClass100_521, Static123.method2423(arg2), Static159.aClass100_760, Static123.method2423(arg0 >> 6), Static159.aClass100_760, Static123.method2423(arg1 >> 6), Static159.aClass100_760, Static123.method2423(arg0 & 0x3F), Static159.aClass100_760, Static123.method2423(arg1 & 0x3F) });
		local66.printToConsole();
		Static127.method2470(local66);
	}
}
