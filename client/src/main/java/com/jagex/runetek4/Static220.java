package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.node.NodeCache;
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

	@OriginalMember(owner = "runetek4.client!rm", name = "d", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_28 = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!rm", name = "g", descriptor = "Z")
	public static boolean aBoolean244 = true;

	@OriginalMember(owner = "runetek4.client!rm", name = "i", descriptor = "Lclient!na;")
	public static final JString aClass100_930 = JString.parse("(Z");

	@OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(IBI)V")
	public static void method3797(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(9) LinkedList local9 = SceneGraph.objStacks[Player.plane][arg1][arg0];
		if (local9 == null) {
			Static187.method3420(Player.plane, arg1, arg0);
			return;
		}
		@Pc(28) int local28 = -99999999;
		@Pc(30) ObjStackNode local30 = null;
		@Pc(35) ObjStackNode local35;
		for (local35 = (ObjStackNode) local9.head(); local35 != null; local35 = (ObjStackNode) local9.next()) {
			@Pc(44) ObjType local44 = ObjTypeList.get(local35.aClass8_Sub7_1.anInt5555);
			@Pc(47) int local47 = local44.cost;
			if (local44.stackable == 1) {
				local47 *= local35.aClass8_Sub7_1.anInt5550 + 1;
			}
			if (local28 < local47) {
				local28 = local47;
				local30 = local35;
			}
		}
		if (local30 == null) {
			Static187.method3420(Player.plane, arg1, arg0);
			return;
		}
		local9.addHead(local30);
		@Pc(89) ObjStack local89 = null;
		@Pc(91) ObjStack local91 = null;
		for (local35 = (ObjStackNode) local9.head(); local35 != null; local35 = (ObjStackNode) local9.next()) {
			@Pc(103) ObjStack local103 = local35.aClass8_Sub7_1;
			if (local103.anInt5555 != local30.aClass8_Sub7_1.anInt5555) {
				if (local89 == null) {
					local89 = local103;
				}
				if (local103.anInt5555 != local89.anInt5555 && local91 == null) {
					local91 = local103;
				}
			}
		}
		@Pc(152) long local152 = (long) ((arg0 << 7) + arg1 + 1610612736);
		Static69.method1543(Player.plane, arg1, arg0, SceneGraph.getTileHeight(Player.plane, arg1 * 128 + 64, arg0 * 128 + 64), local30.aClass8_Sub7_1, local152, local89, local91);
	}

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
					@Pc(22) Tile local22 = Static130.levelTiles[local1][local6][local11];
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
