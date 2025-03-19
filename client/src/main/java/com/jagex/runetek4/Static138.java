package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static138 {

	@OriginalMember(owner = "runetek4.client!km", name = "sc", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array5;

	@OriginalMember(owner = "runetek4.client!km", name = "uc", descriptor = "F")
	public static float aFloat14;

	@OriginalMember(owner = "runetek4.client!km", name = "Rc", descriptor = "[I")
	public static final int[] FRONT_WALL_TYPES = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };

	@OriginalMember(owner = "runetek4.client!km", name = "Sc", descriptor = "Lclient!na;")
	public static final JString TRADE = JString.parse(":trade:");

	@OriginalMember(owner = "runetek4.client!km", name = "ad", descriptor = "I")
	public static int invokedScriptIndex = 0;

	@OriginalMember(owner = "runetek4.client!km", name = "b", descriptor = "(III)I")
	public static int method2695(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(14) int local14 = arg1 * 57 + arg0;
		@Pc(20) int local20 = local14 ^ local14 << 13;
		@Pc(34) int local34 = Integer.MAX_VALUE & (local20 * local20 * 15731 + 789221) * local20 + 1376312589;
		return local34 >> 19 & 0xFF;
	}

	@OriginalMember(owner = "runetek4.client!km", name = "f", descriptor = "(I)Z")
	public static boolean allLevelsvisible() {
		return GlRenderer.enabled ? true : Static162.aBoolean190;
	}

}
