package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static153 {

	@OriginalMember(owner = "runetek4.client!mc", name = "Q", descriptor = "Lclient!na;")
	public static JString aClass100_724;

	@OriginalMember(owner = "runetek4.client!mc", name = "S", descriptor = "Lclient!mm;")
	public static SoftwareSprite aClass3_Sub2_Sub1_Sub1_2;

	@OriginalMember(owner = "runetek4.client!mc", name = "ab", descriptor = "[I")
	public static final int[] anIntArray351 = new int[] { 7, 8, 9, 10, 11, 12, 13, 15 };

	@OriginalMember(owner = "runetek4.client!mc", name = "tb", descriptor = "S")
	public static short aShort22 = 1;

	@OriginalMember(owner = "runetek4.client!mc", name = "a", descriptor = "(IIIIIIII)V")
	public static void method2907(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		@Pc(9) int local9 = arg4 + arg1;
		@Pc(13) int local13 = arg4 + arg6;
		@Pc(15) int local15;
		for (local15 = arg1; local15 < local9; local15++) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local15], arg6, arg5, arg0);
		}
		@Pc(34) int local34 = arg2 - arg4;
		@Pc(39) int local39 = arg5 - arg4;
		for (local15 = arg2; local15 > local34; local15--) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local15], arg6, arg5, arg0);
		}
		for (local15 = local9; local15 <= local34; local15++) {
			@Pc(72) int[] local72 = ObjTypeList.anIntArrayArray10[local15];
			ArrayUtils.fillRange(local72, arg6, local13, arg0);
			ArrayUtils.fillRange(local72, local13, local39, arg3);
			ArrayUtils.fillRange(local72, local39, arg5, arg0);
		}
	}

}
