package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static218 {

	@OriginalMember(owner = "runetek4.client!rk", name = "M", descriptor = "Lclient!na;")
	public static final JString startShadow = Static28.parse("shad=");

	@OriginalMember(owner = "runetek4.client!rk", name = "O", descriptor = "Lclient!na;")
	public static final JString startTrans = Static28.parse("trans=");

	@OriginalMember(owner = "runetek4.client!rk", name = "P", descriptor = "Lclient!na;")
	public static final JString startUnderline = Static28.parse("u=");

	@OriginalMember(owner = "runetek4.client!rk", name = "Q", descriptor = "Lclient!na;")
	public static final JString startStrikethrough = Static28.parse("str=");

	@OriginalMember(owner = "runetek4.client!rk", name = "T", descriptor = "Lclient!na;")
	public static final JString endColor = Static28.parse(")4col");

	@OriginalMember(owner = "runetek4.client!rk", name = "X", descriptor = "Lclient!na;")
	public static final JString endShadow = Static28.parse(")4shad");

	@OriginalMember(owner = "runetek4.client!rk", name = "Y", descriptor = "Lclient!na;")
	public static final JString startColor = Static28.parse("col=");

	@OriginalMember(owner = "runetek4.client!rk", name = "Z", descriptor = "Lclient!na;")
	private static final JString aClass100_706 = Static28.parse("<gt>");

	@OriginalMember(owner = "runetek4.client!rk", name = "bb", descriptor = "Lclient!na;")
	public static final JString startDefaultUnderline = Static28.parse("u");

	@OriginalMember(owner = "runetek4.client!rk", name = "eb", descriptor = "Lclient!na;")
	public static final JString endTrans = Static28.parse(")4trans");

	@OriginalMember(owner = "runetek4.client!rk", name = "hb", descriptor = "Lclient!na;")
	private static final JString aClass100_711 = Static28.parse("<lt>");

	@OriginalMember(owner = "runetek4.client!rk", name = "ib", descriptor = "Lclient!na;")
	public static final JString endUnderline = Static28.parse(")4u");

	@OriginalMember(owner = "runetek4.client!rk", name = "kb", descriptor = "Lclient!na;")
	public static final JString lineBreak = Static28.parse("br");

	@OriginalMember(owner = "runetek4.client!rk", name = "lb", descriptor = "Lclient!na;")
	public static final JString startDefaultShadow = Static28.parse("shad");

	@OriginalMember(owner = "runetek4.client!rk", name = "pb", descriptor = "Lclient!na;")
	public static final JString endStrikeThrough = Static28.parse(")4str");

	@OriginalMember(owner = "runetek4.client!rk", name = "rb", descriptor = "Lclient!na;")
	public static final JString startDefaultStrikeThrough = Static28.parse("str");

	@OriginalMember(owner = "runetek4.client!rk", name = "sb", descriptor = "Lclient!na;")
	public static final JString aClass100_720 = Static87.method1804(100);

	@OriginalMember(owner = "runetek4.client!rk", name = "tb", descriptor = "I")
	public static int strikethroughColor = -1;

	@OriginalMember(owner = "runetek4.client!rk", name = "ub", descriptor = "I")
	public static int underlineColor = -1;

	@OriginalMember(owner = "runetek4.client!rk", name = "vb", descriptor = "I")
	public static int anInt3629 = 0;

	@OriginalMember(owner = "runetek4.client!rk", name = "wb", descriptor = "I")
	public static int defaultTextColor = 0;

	@OriginalMember(owner = "runetek4.client!rk", name = "yb", descriptor = "I")
	public static int defaultOpacity = 256;

	@OriginalMember(owner = "runetek4.client!rk", name = "zb", descriptor = "I")
	public static int defaultShadowColor = -1;

	@OriginalMember(owner = "runetek4.client!rk", name = "Ab", descriptor = "[Lclient!na;")
	public static final JString[] aClass100Array110 = new JString[100];

	@OriginalMember(owner = "runetek4.client!rk", name = "Bb", descriptor = "I")
	public static int shadowColor = -1;

	@OriginalMember(owner = "runetek4.client!rk", name = "Db", descriptor = "I")
	public static int anInt3636 = 0;

	@OriginalMember(owner = "runetek4.client!rk", name = "c", descriptor = "(Lclient!na;)Lclient!na;")
	public static JString method2862(@OriginalArg(0) JString arg0) {
		@Pc(3) int local3 = arg0.length();
		@Pc(5) int local5 = 0;
		@Pc(15) int local15;
		for (@Pc(7) int local7 = 0; local7 < local3; local7++) {
			local15 = arg0.charAt(local7);
			if (local15 == 60 || local15 == 62) {
				local5 += 3;
			}
		}
		@Pc(30) JString local30 = Static87.method1804(local3 + local5);
		for (local15 = 0; local15 < local3; local15++) {
			@Pc(40) int local40 = arg0.charAt(local15);
			if (local40 == 60) {
				local30.method3113(aClass100_711);
			} else if (local40 == 62) {
				local30.method3113(aClass100_706);
			} else {
				local30.method3152(local40);
			}
		}
		return local30;
	}

	@OriginalMember(owner = "runetek4.client!rk", name = "a", descriptor = "([[B[[B[I[I[III)I")
	public static int method2870(@OriginalArg(0) byte[][] arg0, @OriginalArg(1) byte[][] arg1, @OriginalArg(2) int[] arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) int local3 = arg2[arg5];
		@Pc(9) int local9 = local3 + arg4[arg5];
		@Pc(13) int local13 = arg2[arg6];
		@Pc(19) int local19 = local13 + arg4[arg6];
		@Pc(21) int local21 = local3;
		if (local13 > local3) {
			local21 = local13;
		}
		@Pc(28) int local28 = local9;
		if (local19 < local9) {
			local28 = local19;
		}
		@Pc(37) int local37 = arg3[arg5];
		if (arg3[arg6] < local37) {
			local37 = arg3[arg6];
		}
		@Pc(50) byte[] local50 = arg1[arg5];
		@Pc(54) byte[] local54 = arg0[arg6];
		@Pc(58) int local58 = local21 - local3;
		@Pc(62) int local62 = local21 - local13;
		for (@Pc(64) int local64 = local21; local64 < local28; local64++) {
			@Pc(77) int local77 = local50[local58++] + local54[local62++];
			if (local77 < local37) {
				local37 = local77;
			}
		}
		return -local37;
	}
}
