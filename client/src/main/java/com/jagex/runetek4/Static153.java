package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static153 {

	@OriginalMember(owner = "runetek4.client!mc", name = "Q", descriptor = "Lclient!na;")
	public static JString aClass100_724;

	@OriginalMember(owner = "runetek4.client!mc", name = "S", descriptor = "Lclient!mm;")
	public static ImageRGB aClass3_Sub2_Sub1_Sub1_2;

	@OriginalMember(owner = "runetek4.client!mc", name = "Z", descriptor = "Lclient!ve;")
	public static Js5 aClass153_57;

	@OriginalMember(owner = "runetek4.client!mc", name = "ab", descriptor = "[I")
	public static final int[] anIntArray351 = new int[] { 7, 8, 9, 10, 11, 12, 13, 15 };

	@OriginalMember(owner = "runetek4.client!mc", name = "fb", descriptor = "Lclient!na;")
	private static final JString aClass100_725 = JString.parse("pt");

	@OriginalMember(owner = "runetek4.client!mc", name = "mb", descriptor = "Lclient!na;")
	private static final JString aClass100_726 = JString.parse("en");

	@OriginalMember(owner = "runetek4.client!mc", name = "vb", descriptor = "Lclient!na;")
	private static final JString aClass100_728 = JString.parse("de");

	@OriginalMember(owner = "runetek4.client!mc", name = "pb", descriptor = "Lclient!na;")
	private static final JString aClass100_727 = JString.parse("fr");

	@OriginalMember(owner = "runetek4.client!mc", name = "ob", descriptor = "[Lclient!na;")
	public static final JString[] aClass100Array113 = new JString[] { aClass100_726, aClass100_728, aClass100_727, aClass100_725 };

	@OriginalMember(owner = "runetek4.client!mc", name = "tb", descriptor = "S")
	public static short aShort22 = 1;

	@OriginalMember(owner = "runetek4.client!mc", name = "c", descriptor = "(III)V")
	public static void method2905(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(14) DelayedStateChange local14 = Static238.method4143(7, arg0);
		local14.method1017();
		local14.intArg1 = arg1;
	}

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

	@OriginalMember(owner = "runetek4.client!mc", name = "a", descriptor = "(BI)V")
	public static void method2910(@OriginalArg(1) int arg0) {
		@Pc(4) DelayedStateChange local4 = Static238.method4143(8, arg0);
		local4.method1007();
	}
}
