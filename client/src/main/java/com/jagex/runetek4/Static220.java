package com.jagex.runetek4;

import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.Wall;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static220 {

	@OriginalMember(owner = "runetek4.client!rm", name = "c", descriptor = "I")
	public static int cameraOffsetYawModifier = 1;

	@OriginalMember(owner = "runetek4.client!rm", name = "i", descriptor = "Lclient!na;")
	public static final JString aClass100_930 = JString.parse("(Z");

	@OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(JB)V")
	public static void sleep0(@OriginalArg(0) long arg0) {
		try {
			Thread.sleep(arg0);
		} catch (@Pc(11) InterruptedException local11) {
		}
	}

	@OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(III)V")
	public static void method3801() {
		for (@Pc(1) int local1 = 0; local1 < Static126.anInt3114; local1++) {
			for (@Pc(6) int local6 = 0; local6 < Static152.anInt3594; local6++) {
				for (@Pc(11) int local11 = 0; local11 < Static99.anInt2550; local11++) {
					@Pc(22) Tile local22 = SceneGraph.tiles[local1][local6][local11];
					if (local22 != null) {
						@Pc(27) Wall local27 = local22.wall;
						if (local27 != null && local27.primary.method4543()) {
							Static69.method1544(local27.primary, local1, local6, local11, 1, 1);
							if (local27.modelB != null && local27.modelB.method4543()) {
								Static69.method1544(local27.modelB, local1, local6, local11, 1, 1);
								local27.primary.method4544(local27.modelB, 0, 0, 0, false);
								local27.modelB = local27.modelB.createModel();
							}
							local27.primary = local27.primary.createModel();
						}
						for (@Pc(83) int local83 = 0; local83 < local22.entityCount; local83++) {
							@Pc(92) Scenery local92 = local22.sceneries[local83];
							if (local92 != null && local92.entity.method4543()) {
								Static69.method1544(local92.entity, local1, local6, local11, local92.anInt1713 + 1 - local92.anInt1701, local92.anInt1698 - local92.anInt1696 + 1);
								local92.entity = local92.entity.createModel();
							}
						}
						@Pc(131) GroundDecor local131 = local22.groundDecor;
						if (local131 != null && local131.entity.method4543()) {
							Static264.method3574(local131.entity, local1, local6, local11);
							local131.entity = local131.entity.createModel();
						}
					}
				}
			}
		}
	}
}
