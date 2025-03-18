package com.jagex.runetek4;

import com.jagex.runetek4.core.utils.MillisTimer;
import com.jagex.runetek4.core.utils.Timer;
import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static70 {

	@OriginalMember(owner = "client!fi", name = "m", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray7;

	@OriginalMember(owner = "client!fi", name = "l", descriptor = "I")
	public static int anInt2014 = 0;

	@OriginalMember(owner = "client!fi", name = "n", descriptor = "I")
	public static int updatedVarpsWriterIndex = 0;

	@OriginalMember(owner = "client!fi", name = "a", descriptor = "(B)Lclient!s;")
	public static Timer method1547() {
		try {
			return (Timer) Class.forName("com.jagex.runetek4.core.utils.NanoTimer").getDeclaredConstructor().newInstance();
		} catch (@Pc(15) Throwable local15) {
			return new MillisTimer();
		}
	}

	@OriginalMember(owner = "client!fi", name = "a", descriptor = "(III)V")
	public static void method1549(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		for (@Pc(11) int local11 = 0; local11 < Static98.anInt2510; local11++) {
			@Pc(18) FloorOverlayType local18 = Static256.method4395(local11);
			if (local18 != null) {
				@Pc(24) int local24 = local18.material;
				if (local24 >= 0 && !Rasterizer.textureProvider.method3236(local24)) {
					local24 = -1;
				}
				@Pc(53) int local53;
				@Pc(66) int local66;
				@Pc(72) int local72;
				@Pc(95) int local95;
				if (local18.averagecolour >= 0) {
					local66 = local18.averagecolour;
					local72 = (local66 & 0x7F) + arg0;
					if (local72 < 0) {
						local72 = 0;
					} else if (local72 > 127) {
						local72 = 127;
					}
					local95 = (local66 & 0x380) + (arg1 + local66 & 0xFC00) + local72;
					local53 = Pix3D.anIntArray220[ColorUtils.multiplyLightnessGrayscale(local95, 96)];
				} else if (local24 >= 0) {
					local53 = Pix3D.anIntArray220[ColorUtils.multiplyLightnessGrayscale(Rasterizer.textureProvider.method3234(local24), 96)];
				} else if (local18.rgb == -1) {
					local53 = -1;
				} else {
					local66 = local18.rgb;
					local72 = arg0 + (local66 & 0x7F);
					if (local72 < 0) {
						local72 = 0;
					} else if (local72 > 127) {
						local72 = 127;
					}
					local95 = local72 + (local66 & 0x380) + (local66 + arg1 & 0xFC00);
					local53 = Pix3D.anIntArray220[ColorUtils.multiplyLightnessGrayscale(local95, 96)];
				}
				Static145.anIntArray330[local11 + 1] = local53;
			}
		}
	}
}
