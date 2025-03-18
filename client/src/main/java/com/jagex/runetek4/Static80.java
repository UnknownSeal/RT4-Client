package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static80 {

	@OriginalMember(owner = "client!gf", name = "M", descriptor = "I")
	public static int anInt4698;

	@OriginalMember(owner = "client!gf", name = "O", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray19;

	@OriginalMember(owner = "client!gf", name = "R", descriptor = "I")
	public static int anInt4701;

	@OriginalMember(owner = "client!gf", name = "T", descriptor = "I")
	public static int anInt4702;

	@OriginalMember(owner = "client!gf", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_886 = JString.parse(")3runescape)3com)4l=");

	@OriginalMember(owner = "client!gf", name = "S", descriptor = "[I")
	public static final int[] anIntArray419 = new int[] { 0, 2, 2, 2, 1, 1, 2, 2, 1, 3, 1, 1 };

	@OriginalMember(owner = "client!gf", name = "a", descriptor = "(BII)V")
	public static void method3616(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		Static142.anInt3482 = arg0 - Static158.anInt3846;
		@Pc(24) int local24 = Static142.anInt3482 - (int) ((float) Static24.component.width / Static83.aFloat3);
		@Pc(33) int local33 = Static142.anInt3482 + (int) ((float) Static24.component.width / Static83.aFloat3);
		if (local24 < 0) {
			Static142.anInt3482 = (int) ((float) Static24.component.width / Static83.aFloat3);
		}
		Static217.anInt4901 = IdkTypeList.anInt4296 + Static2.anInt13 - arg1 - 1;
		@Pc(61) int local61 = (int) ((float) Static24.component.height / Static83.aFloat3) + Static217.anInt4901;
		@Pc(70) int local70 = Static217.anInt4901 - (int) ((float) Static24.component.height / Static83.aFloat3);
		if (local33 > Static48.anInt1449) {
			Static142.anInt3482 = Static48.anInt1449 - (int) ((float) Static24.component.width / Static83.aFloat3);
		}
		if (local70 < 0) {
			Static217.anInt4901 = (int) ((float) Static24.component.height / Static83.aFloat3);
		}
		if (IdkTypeList.anInt4296 < local61) {
			Static217.anInt4901 = IdkTypeList.anInt4296 - (int) ((float) Static24.component.height / Static83.aFloat3);
		}
	}

}
