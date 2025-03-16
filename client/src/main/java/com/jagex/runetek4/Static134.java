package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static134 {

	@OriginalMember(owner = "runetek4.client!kh", name = "a", descriptor = "(B)Lclient!ek;")
	public static SoftwareIndexedSprite method2619() {
		@Pc(25) SoftwareIndexedSprite local25 = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], aClass6.aByteArrayArray5[0], Static259.anIntArray513);
		Static75.method1631();
		return local25;
	}

	@OriginalMember(owner = "runetek4.client!kh", name = "a", descriptor = "(II)V")
	public static void method2621() {
		Static45.aClass99_6.clear(5);
	}

	@OriginalMember(owner = "runetek4.client!kh", name = "a", descriptor = "(IIIBII)V")
	public static void method2622(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(8) int local8 = arg2 + 1;
		ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[arg2], arg4, arg1, arg0);
		@Pc(17) int local17 = arg3 - 1;
		ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[arg3], arg4, arg1, arg0);
		for (@Pc(29) int local29 = local8; local29 <= local17; local29++) {
			@Pc(40) int[] local40 = ObjTypeList.anIntArrayArray10[local29];
			local40[arg4] = local40[arg1] = arg0;
		}
	}

}
