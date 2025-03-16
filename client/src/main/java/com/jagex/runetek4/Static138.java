package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static138 {

	@OriginalMember(owner = "runetek4.client!km", name = "sc", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array5;

	@OriginalMember(owner = "runetek4.client!km", name = "uc", descriptor = "F")
	public static float aFloat14;

	@OriginalMember(owner = "runetek4.client!km", name = "Yc", descriptor = "I")
	public static int anInt3443;

	@OriginalMember(owner = "runetek4.client!km", name = "pc", descriptor = "Z")
	public static boolean aBoolean172 = false;

	@OriginalMember(owner = "runetek4.client!km", name = "tc", descriptor = "Lclient!na;")
	private static final JString DEC = JString.parse("Dec");

	@OriginalMember(owner = "runetek4.client!km", name = "vc", descriptor = "Lclient!na;")
	private static final JString JUL = JString.parse("Jul");

	@OriginalMember(owner = "runetek4.client!km", name = "xc", descriptor = "Lclient!na;")
	private static final JString MAY = JString.parse("May");

	@OriginalMember(owner = "runetek4.client!km", name = "yc", descriptor = "Lclient!na;")
	private static final JString NOV = JString.parse("Nov");

	@OriginalMember(owner = "runetek4.client!km", name = "zc", descriptor = "Lclient!na;")
	private static final JString MAR = JString.parse("Mar");

	@OriginalMember(owner = "runetek4.client!km", name = "Gc", descriptor = "Lclient!na;")
	private static final JString JAN = JString.parse("Jan");

	@OriginalMember(owner = "runetek4.client!km", name = "Hc", descriptor = "Lclient!na;")
	private static final JString FEB = JString.parse("Feb");

	@OriginalMember(owner = "runetek4.client!km", name = "Tc", descriptor = "Lclient!na;")
	private static final JString APR = JString.parse("Apr");

	@OriginalMember(owner = "runetek4.client!km", name = "Wc", descriptor = "Lclient!na;")
	private static final JString JUN = JString.parse("Jun");

	@OriginalMember(owner = "runetek4.client!km", name = "Qc", descriptor = "Lclient!na;")
	private static final JString AUG = JString.parse("Aug");

	@OriginalMember(owner = "runetek4.client!km", name = "cd", descriptor = "Lclient!na;")
	private static final JString SEP = JString.parse("Sep");

	@OriginalMember(owner = "runetek4.client!km", name = "dd", descriptor = "Lclient!na;")
	private static final JString OCT = JString.parse("Oct");

	@OriginalMember(owner = "runetek4.client!km", name = "Ac", descriptor = "[Lclient!na;")
	public static final JString[] MONTHS = new JString[] {JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC};

	@OriginalMember(owner = "runetek4.client!km", name = "Bc", descriptor = "[I")
	public static final int[] updatedVarcs = new int[32];

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
