package com.jagex.runetek4;

import com.jagex.runetek4.game.scene.entities.NPCEntity;
import com.jagex.runetek4.game.world.entity.Player;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static195 {

	@OriginalMember(owner = "runetek4.client!pk", name = "R", descriptor = "[[Lclient!hg;")
	public static Class3_Sub14[][] aClass3_Sub14ArrayArray3;

	@OriginalMember(owner = "runetek4.client!pk", name = "Y", descriptor = "I")
	public static int anInt4581;

	@OriginalMember(owner = "runetek4.client!pk", name = "Z", descriptor = "Lclient!ve;")
	public static Js5 aClass153_80;

	@OriginalMember(owner = "runetek4.client!pk", name = "bb", descriptor = "Lclient!na;")
	public static JagString aClass100_859;

	@OriginalMember(owner = "runetek4.client!pk", name = "V", descriptor = "[S")
	public static final short[] aShortArray64 = new short[] { -10304, 9104, -1, -1, -1 };

	@OriginalMember(owner = "runetek4.client!pk", name = "f", descriptor = "(B)I")
	public static int getIdleLoops() {
		return Static229.anInt5140;
	}

	@OriginalMember(owner = "runetek4.client!pk", name = "i", descriptor = "(I)V")
	public static void method3532() {
		for (@Pc(16) ProjAnimNode local16 = (ProjAnimNode) Static217.aClass69_116.head(); local16 != null; local16 = (ProjAnimNode) Static217.aClass69_116.next()) {
			@Pc(21) ProjectileAnimation local21 = local16.aClass8_Sub6_1;
			if (Static55.level != local21.anInt4810 || local21.lastCycle < Static83.loopCycle) {
				local16.remove();
			} else if (Static83.loopCycle >= local21.anInt4822) {
				if (local21.anInt4819 > 0) {
					@Pc(54) NPCEntity local54 = Static175.npcs[local21.anInt4819 - 1];
					if (local54 != null && local54.x >= 0 && local54.x < 13312 && local54.z >= 0 && local54.z < 13312) {
						local21.setTarget(local54.z, Static83.loopCycle, Static207.method3685(local21.anInt4810, local54.x, local54.z) - local21.anInt4805, local54.x);
					}
				}
				if (local21.anInt4819 < 0) {
					@Pc(102) int local102 = -local21.anInt4819 - 1;
					@Pc(107) Player local107;
					if (Static16.anInt549 == local102) {
						local107 = Static173.localPlayer;
					} else {
						local107 = Static159.players[local102];
					}
					if (local107 != null && local107.x >= 0 && local107.x < 13312 && local107.z >= 0 && local107.z < 13312) {
						local21.setTarget(local107.z, Static83.loopCycle, Static207.method3685(local21.anInt4810, local107.x, local107.z) - local21.anInt4805, local107.x);
					}
				}
				local21.method3704(Static178.anInt4247);
				Static43.method1141(Static55.level, (int) local21.x, (int) local21.y, (int) local21.z, 60, local21, local21.yaw, -1L, false);
			}
		}
	}
}
