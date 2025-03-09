package com.jagex.runetek4;

import com.jagex.runetek4.game.scene.entities.NPCEntity;
import com.jagex.runetek4.game.world.entity.PlayerEntity;
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
	public static void pushProjectiles() {
		for (@Pc(16) ProjectileEntity proj = (ProjectileEntity) Static217.projectiles.head(); proj != null; proj = (ProjectileEntity) Static217.projectiles.next()) {
			@Pc(21) ProjectileAnimation projAnim = proj.aClass8_Sub6_1;
			if (Static55.currentLevel != projAnim.level || projAnim.lastCycle < Static83.loopCycle) {
				proj.unlink();
			} else if (Static83.loopCycle >= projAnim.startCycle) {
				if (projAnim.target > 0) {
					@Pc(54) NPCEntity npc = Static175.npcs[projAnim.target - 1];
					if (npc != null && npc.x >= 0 && npc.x < 13312 && npc.z >= 0 && npc.z < 13312) {
						projAnim.updateVelocity(npc.z, Static83.loopCycle, Static207.getHeightmapY(projAnim.level, npc.x, npc.z) - projAnim.anInt4805, npc.x);
					}
				}
				if (projAnim.target < 0) {
					@Pc(102) int index = -projAnim.target - 1;
					@Pc(107) PlayerEntity player;
					if (Static16.localPid == index) {
						player = Static173.localPlayer;
					} else {
						player = Static159.players[index];
					}
					if (player != null && player.x >= 0 && player.x < 13312 && player.z >= 0 && player.z < 13312) {
						projAnim.updateVelocity(player.z, Static83.loopCycle, Static207.getHeightmapY(projAnim.level, player.x, player.z) - projAnim.anInt4805, player.x);
					}
				}
				projAnim.update(Static178.sceneDelta);
				Static43.addTemporary(Static55.currentLevel, (int) projAnim.x, (int) projAnim.y, (int) projAnim.z, 60, projAnim, projAnim.yaw, -1L, false);
			}
		}
	}
}
