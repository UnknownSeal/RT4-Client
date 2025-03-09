package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static16 {

	@OriginalMember(owner = "client!bf", name = "s", descriptor = "Lclient!ve;")
	public static Js5 aClass153_9;

	@OriginalMember(owner = "client!bf", name = "B", descriptor = "I")
	public static int anInt548 = -1;

	@OriginalMember(owner = "client!bf", name = "C", descriptor = "[I")
	public static final int[] anIntArray51 = new int[] { 2, 2, 4, 2, 1, 8, 4, 1, 4, 4, 2, 1, 1, 1, 4, 1 };

	@OriginalMember(owner = "client!bf", name = "E", descriptor = "I")
	public static int localPid = -1;

	@OriginalMember(owner = "client!bf", name = "G", descriptor = "I")
	public static int anInt551 = 0;

	@OriginalMember(owner = "client!bf", name = "I", descriptor = "[I")
	public static final int[] CHAT_COLORS = new int[] { 16776960, 16711680, 65280, 65535, 16711935, 16777215 };

	@OriginalMember(owner = "client!bf", name = "c", descriptor = "(I)V")
	public static void method501() {
		if (!GlRenderer.enabled || PreciseSleep.aBoolean252) {
			return;
		}
		@Pc(14) Ground[][][] local14 = Static130.levelTiles;
		for (@Pc(22) int local22 = 0; local22 < local14.length; local22++) {
			@Pc(30) Ground[][] local30 = local14[local22];
			for (@Pc(32) int local32 = 0; local32 < local30.length; local32++) {
				for (@Pc(42) int local42 = 0; local42 < local30[local32].length; local42++) {
					@Pc(54) Ground local54 = local30[local32][local42];
					if (local54 != null) {
						@Pc(71) GlModel local71;
						if (local54.groundDecor != null && local54.groundDecor.entity instanceof GlModel) {
							local71 = (GlModel) local54.groundDecor.entity;
							if ((local54.groundDecor.key & Long.MIN_VALUE) == 0L) {
								local71.method4111(false, true, true, false, true, true);
							} else {
								local71.method4111(true, true, true, true, true, true);
							}
						}
						if (local54.decor != null) {
							if (local54.decor.model instanceof GlModel) {
								local71 = (GlModel) local54.decor.model;
								if ((local54.decor.aLong52 & Long.MIN_VALUE) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
							if (local54.decor.aClass8_2 instanceof GlModel) {
								local71 = (GlModel) local54.decor.aClass8_2;
								if ((Long.MIN_VALUE & local54.decor.aLong52) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
						}
						if (local54.wall != null) {
							if (local54.wall.modelA instanceof GlModel) {
								local71 = (GlModel) local54.wall.modelA;
								if ((local54.wall.aLong107 & Long.MIN_VALUE) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
							if (local54.wall.modelB instanceof GlModel) {
								local71 = (GlModel) local54.wall.modelB;
								if ((Long.MIN_VALUE & local54.wall.aLong107) == 0L) {
									local71.method4111(false, true, true, false, true, true);
								} else {
									local71.method4111(true, true, true, true, true, true);
								}
							}
						}
						for (@Pc(270) int local270 = 0; local270 < local54.anInt662; local270++) {
							if (local54.aClass31Array1[local270].aClass8_4 instanceof GlModel) {
								@Pc(293) GlModel local293 = (GlModel) local54.aClass31Array1[local270].aClass8_4;
								if ((Long.MIN_VALUE & local54.aClass31Array1[local270].aLong56) == 0L) {
									local293.method4111(false, true, true, false, true, true);
								} else {
									local293.method4111(true, true, true, true, true, true);
								}
							}
						}
					}
				}
			}
		}
		PreciseSleep.aBoolean252 = true;
	}
}