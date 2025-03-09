package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static85 {

	@OriginalMember(owner = "runetek4.client!gl", name = "a", descriptor = "Lclient!ve;")
	public static Js5 aClass153_36;

	@OriginalMember(owner = "runetek4.client!gl", name = "d", descriptor = "I")
	public static int anInt2261;

	@OriginalMember(owner = "runetek4.client!gl", name = "e", descriptor = "I")
	public static int anInt2262;

	@OriginalMember(owner = "runetek4.client!gl", name = "f", descriptor = "I")
	public static int anInt2263;

	@OriginalMember(owner = "runetek4.client!gl", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void method1774(@OriginalArg(0) Js5 arg0) {
		Static84.aClass153_35 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!gl", name = "a", descriptor = "(II)V")
	public static void method1775(@OriginalArg(1) int arg0) {
		Static103.method2245();
		AreaSoundManager.method2386();
		@Pc(17) int local17 = VarpDefinition.getDefinition(arg0).clientCode;
		if (local17 == 0) {
			return;
		}
		@Pc(25) int local25 = VarpDefinition.varps[arg0];
		if (local17 == 6) {
			Static79.anInt2157 = local25;
		}
		if (local17 == 5) {
			Static116.anInt2952 = local25;
		}
		if (local17 == 9) {
			Static179.anInt4254 = local25;
		}
	}
}
