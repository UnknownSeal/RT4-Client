package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static80 {

	@OriginalMember(owner = "runetek4.client!gf", name = "K", descriptor = "I")
	public static int anInt4696;

	@OriginalMember(owner = "runetek4.client!gf", name = "M", descriptor = "I")
	public static int anInt4698;

	@OriginalMember(owner = "runetek4.client!gf", name = "O", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray19;

	@OriginalMember(owner = "runetek4.client!gf", name = "R", descriptor = "I")
	public static int anInt4701;

	@OriginalMember(owner = "runetek4.client!gf", name = "T", descriptor = "I")
	public static int anInt4702;

	@OriginalMember(owner = "runetek4.client!gf", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_886 = JString.parse(")3runescape)3com)4l=");

	@OriginalMember(owner = "runetek4.client!gf", name = "N", descriptor = "Z")
	public static boolean aBoolean231 = true;

	@OriginalMember(owner = "runetek4.client!gf", name = "S", descriptor = "[I")
	public static final int[] anIntArray419 = new int[] { 0, 2, 2, 2, 1, 1, 2, 2, 1, 3, 1, 1 };

	@OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(Lclient!ve;IIB)Lclient!mm;")
	public static ImageRGB method3613(@OriginalArg(0) Js5 arg0, @OriginalArg(2) int arg1) {
		return Static234.method4016(arg0, 0, arg1) ? Static102.method2071() : null;
	}

	@OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(BII)V")
	public static void method3616(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		Static142.anInt3482 = arg0 - Static158.anInt3846;
		@Pc(24) int local24 = Static142.anInt3482 - (int) ((float) Static24.component.anInt445 / Static83.aFloat3);
		@Pc(33) int local33 = Static142.anInt3482 + (int) ((float) Static24.component.anInt445 / Static83.aFloat3);
		if (local24 < 0) {
			Static142.anInt3482 = (int) ((float) Static24.component.anInt445 / Static83.aFloat3);
		}
		Static217.anInt4901 = IdkTypeList.anInt4296 + Static2.anInt13 - arg1 - 1;
		@Pc(61) int local61 = (int) ((float) Static24.component.anInt459 / Static83.aFloat3) + Static217.anInt4901;
		@Pc(70) int local70 = Static217.anInt4901 - (int) ((float) Static24.component.anInt459 / Static83.aFloat3);
		if (local33 > Static48.anInt1449) {
			Static142.anInt3482 = Static48.anInt1449 - (int) ((float) Static24.component.anInt445 / Static83.aFloat3);
		}
		if (local70 < 0) {
			Static217.anInt4901 = (int) ((float) Static24.component.anInt459 / Static83.aFloat3);
		}
		if (IdkTypeList.anInt4296 < local61) {
			Static217.anInt4901 = IdkTypeList.anInt4296 - (int) ((float) Static24.component.anInt459 / Static83.aFloat3);
		}
	}

	@OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(Lclient!na;II)V")
	public static void method3617(@OriginalArg(0) JString arg0, @OriginalArg(2) int arg1) {
		@Pc(6) DelayedStateChange local6 = Static238.method4143(3, arg1);
		local6.method1017();
		local6.stringArg = arg0;
	}
}
