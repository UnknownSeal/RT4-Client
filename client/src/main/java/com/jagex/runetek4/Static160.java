package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static160 {

	@OriginalMember(owner = "runetek4.client!mj", name = "n", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_4;

	@OriginalMember(owner = "runetek4.client!mj", name = "C", descriptor = "[S")
	public static short[] aShortArray41;

	@OriginalMember(owner = "runetek4.client!mj", name = "i", descriptor = "I")
	public static int anInt3902 = 0;

	@OriginalMember(owner = "runetek4.client!mj", name = "a", descriptor = "(IIIII)Z")
	public static boolean method3049(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		@Pc(9) int local9 = arg3 * PreciseSleep.anInt5205 + arg0 * ObjType.anInt2222 >> 16;
		@Pc(19) int local19 = arg3 * ObjType.anInt2222 - arg0 * PreciseSleep.anInt5205 >> 16;
		@Pc(29) int local29 = arg1 * Static109.anInt2886 + local19 * Static121.anInt3038 >> 16;
		@Pc(39) int local39 = arg1 * Static121.anInt3038 - local19 * Static109.anInt2886 >> 16;
		if (local29 < 1) {
			local29 = 1;
		}
		@Pc(50) int local50 = (local9 << 9) / local29;
		@Pc(56) int local56 = (local39 << 9) / local29;
		@Pc(66) int local66 = arg2 * Static109.anInt2886 + local19 * Static121.anInt3038 >> 16;
		@Pc(76) int local76 = arg2 * Static121.anInt3038 - local19 * Static109.anInt2886 >> 16;
		if (local66 < 1) {
			local66 = 1;
		}
		@Pc(87) int local87 = (local9 << 9) / local66;
		@Pc(93) int local93 = (local76 << 9) / local66;
		if (local29 < 50 && local66 < 50) {
			return false;
		} else if (local29 > arg4 && local66 > arg4) {
			return false;
		} else if (local50 < Rasterizer.screenLowerX && local87 < Rasterizer.screenLowerX) {
			return false;
		} else if (local50 > Rasterizer.screenUpperX && local87 > Rasterizer.screenUpperX) {
			return false;
		} else if (local56 < Rasterizer.screenLowerY && local93 < Rasterizer.screenLowerY) {
			return false;
		} else {
			return local56 <= Rasterizer.screenUpperY || local93 <= Rasterizer.screenUpperY;
		}
	}
}
