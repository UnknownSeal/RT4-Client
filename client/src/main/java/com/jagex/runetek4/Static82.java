package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static82 {

	@OriginalMember(owner = "client!gi", name = "c", descriptor = "I")
	public static int anInt2231 = 0;

	@OriginalMember(owner = "client!gi", name = "a", descriptor = "(IIIIB)V")
	public static void method1760(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (arg1 - arg0 >= Static172.anInt4164 && FloorUnderlayTypeList.anInt5063 >= arg0 + arg1 && arg3 - arg0 >= Static267.anInt5773 && Static106.anInt2869 >= arg0 + arg3) {
			Static49.method1206(arg1, arg0, arg2, arg3);
		} else {
			Static89.method1843(arg2, arg3, arg0, arg1);
		}
	}

	@OriginalMember(owner = "client!gi", name = "b", descriptor = "(I)Lclient!qf;")
	public static Sprite method1764() {
		@Pc(9) byte[] local9 = SpriteLoader.pixels[0];
		@Pc(17) int local17 = SpriteLoader.innerWidths[0] * SpriteLoader.innerHeights[0];
		@Pc(20) int[] local20 = new int[local17];
		for (@Pc(28) int local28 = 0; local28 < local17; local28++) {
			local20[local28] = Static259.anIntArray513[local9[local28] & 0xFF];
		}
		@Pc(69) Sprite local69;
		if (GlRenderer.enabled) {
			local69 = new GlSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[0], SpriteLoader.yOffsets[0], SpriteLoader.innerWidths[0], SpriteLoader.innerHeights[0], local20);
		} else {
			local69 = new SoftwareSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[0], SpriteLoader.yOffsets[0], SpriteLoader.innerWidths[0], SpriteLoader.innerHeights[0], local20);
		}
		SpriteLoader.clear();
		return local69;
	}

}
