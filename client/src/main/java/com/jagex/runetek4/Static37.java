package com.jagex.runetek4;

import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static37 {

	@OriginalMember(owner = "runetek4.client!cm", name = "a", descriptor = "Lclient!m;")
	public static GlTextureProvider anInterface1_1;

	@OriginalMember(owner = "runetek4.client!cm", name = "b", descriptor = "Ljava/lang/Thread;")
	public static Thread aThread1;

	@OriginalMember(owner = "runetek4.client!cm", name = "c", descriptor = "I")
	public static int anInt1176;

	@OriginalMember(owner = "runetek4.client!cm", name = "a", descriptor = "(ILclient!fe;)V")
	public static void method949(@OriginalArg(1) PathingEntity e) {
		if (e.anInt3376 == 0) {
			return;
		}
		@Pc(13) BasType local13 = e.getBasType();
		@Pc(43) int dstX;
		@Pc(36) int dstZ;
		if (e.targetId != -1 && e.targetId < 32768) {
			@Pc(26) Npc npc = NpcList.npcs[e.targetId];
			if (npc != null) {
				dstZ = e.zFine - npc.zFine;
				dstX = e.xFine - npc.xFine;
				if (dstX != 0 || dstZ != 0) {
					e.dstYaw = (int) (Math.atan2((double) dstX, (double) dstZ) * 325.949D) & 0x7FF;
				}
			}
		}
		@Pc(94) int local94;
		@Pc(70) int index;
		if (e.targetId >= 32768) {
			index = e.targetId - 32768;
			if (index == PlayerList.selfId) {
				index = 2047;
			}
			@Pc(83) Player player = PlayerList.players[index];
			if (player != null) {
				local94 = e.zFine - player.zFine;
				dstZ = e.xFine - player.xFine;
				if (dstZ != 0 || local94 != 0) {
					e.dstYaw = (int) (Math.atan2((double) dstZ, (double) local94) * 325.949D) & 0x7FF;
				}
			}
		}
		if ((e.anInt3382 != 0 || e.anInt3363 != 0) && (e.movementQueueSize == 0 || e.anInt3417 > 0)) {
			index = e.xFine - (e.anInt3382 - Camera.originX - Camera.originX) * 64;
			dstX = e.zFine - (e.anInt3363 - Camera.originZ - Camera.originZ) * 64;
			if (index != 0 || dstX != 0) {
				e.dstYaw = (int) (Math.atan2((double) index, (double) dstX) * 325.949D) & 0x7FF;
			}
			e.anInt3363 = 0;
			e.anInt3382 = 0;
		}
		index = e.dstYaw - e.anInt3381 & 0x7FF;
		if (index == 0) {
			e.anInt3385 = 0;
			e.anInt3414 = 0;
		} else if (local13.yawAcceleration == 0) {
			e.anInt3385++;
			@Pc(226) boolean local226;
			if (index > 1024) {
				e.anInt3381 -= e.anInt3376;
				local226 = true;
				if (index < e.anInt3376 || index > 2048 - e.anInt3376) {
					e.anInt3381 = e.dstYaw;
					local226 = false;
				}
				if (local13.idleAnimationId == e.movementSeqId && (e.anInt3385 > 25 || local226)) {
					if (local13.standingCCWTurn == -1) {
						e.movementSeqId = local13.walkAnimation;
					} else {
						e.movementSeqId = local13.standingCCWTurn;
					}
				}
			} else {
				local226 = true;
				e.anInt3381 += e.anInt3376;
				if (e.anInt3376 > index || index > 2048 - e.anInt3376) {
					local226 = false;
					e.anInt3381 = e.dstYaw;
				}
				if (local13.idleAnimationId == e.movementSeqId && (e.anInt3385 > 25 || local226)) {
					if (local13.standingCWTurn == -1) {
						e.movementSeqId = local13.walkAnimation;
					} else {
						e.movementSeqId = local13.standingCWTurn;
					}
				}
			}
			e.anInt3381 &= 0x7FF;
		} else {
			if (local13.idleAnimationId == e.movementSeqId && e.anInt3385 > 25) {
				if (local13.standingCWTurn == -1) {
					e.movementSeqId = local13.walkAnimation;
				} else {
					e.movementSeqId = local13.standingCWTurn;
				}
			}
			dstX = e.dstYaw << 5;
			if (dstX != e.anInt3402) {
				e.anInt3387 = 0;
				e.anInt3402 = dstX;
				dstZ = dstX - e.anInt3377 & 0xFFFF;
				local94 = e.anInt3414 * e.anInt3414 / (local13.yawAcceleration * 2);
				@Pc(471) int local471;
				if (e.anInt3414 > 0 && dstZ >= local94 && dstZ - local94 < 32768) {
					e.anInt3397 = dstZ / 2;
					e.aBoolean167 = true;
					local471 = local13.yawMaxSpeed * local13.yawMaxSpeed / (local13.yawAcceleration * 2);
					if (local471 > 32767) {
						local471 = 32767;
					}
					if (local471 < e.anInt3397) {
						e.anInt3397 = dstZ - local471;
					}
				} else if (e.anInt3414 < 0 && local94 <= 65536 - dstZ && 65536 - dstZ - local94 < 32768) {
					e.anInt3397 = (65536 - dstZ) / 2;
					e.aBoolean167 = true;
					local471 = local13.yawMaxSpeed * local13.yawMaxSpeed / (local13.yawAcceleration * 2);
					if (local471 > 32767) {
						local471 = 32767;
					}
					if (local471 < e.anInt3397) {
						e.anInt3397 = 65536 - dstZ - local471;
					}
				} else {
					e.aBoolean167 = false;
				}
			}
			if (e.anInt3414 == 0) {
				dstZ = e.anInt3402 - e.anInt3377 & 0xFFFF;
				if (dstZ < local13.yawAcceleration) {
					e.anInt3377 = e.anInt3402;
				} else {
					e.anInt3387 = 0;
					local94 = local13.yawMaxSpeed * local13.yawMaxSpeed / (local13.yawAcceleration * 2);
					e.aBoolean167 = true;
					if (local94 > 32767) {
						local94 = 32767;
					}
					if (dstZ >= 32768) {
						e.anInt3414 = -local13.yawAcceleration;
						e.anInt3397 = (65536 - dstZ) / 2;
						if (local94 < e.anInt3397) {
							e.anInt3397 = 65536 - dstZ - local94;
						}
					} else {
						e.anInt3414 = local13.yawAcceleration;
						e.anInt3397 = dstZ / 2;
						if (local94 < e.anInt3397) {
							e.anInt3397 = dstZ - local94;
						}
					}
				}
			} else if (e.anInt3414 <= 0) {
				if (e.anInt3387 >= e.anInt3397) {
					e.aBoolean167 = false;
				}
				if (!e.aBoolean167) {
					e.anInt3414 += local13.yawAcceleration;
					if (e.anInt3414 > 0) {
						e.anInt3414 = 0;
					}
				} else if (-local13.yawMaxSpeed < e.anInt3414) {
					e.anInt3414 -= local13.yawAcceleration;
				}
			} else {
				if (e.anInt3397 <= e.anInt3387) {
					e.aBoolean167 = false;
				}
				if (!e.aBoolean167) {
					e.anInt3414 -= local13.yawAcceleration;
					if (e.anInt3414 < 0) {
						e.anInt3414 = 0;
					}
				} else if (e.anInt3414 < local13.yawMaxSpeed) {
					e.anInt3414 += local13.yawAcceleration;
				}
			}
			e.anInt3377 += e.anInt3414;
			e.anInt3377 &= 0xFFFF;
			if (e.anInt3414 <= 0) {
				e.anInt3387 -= e.anInt3414;
			} else {
				e.anInt3387 += e.anInt3414;
			}
			e.anInt3381 = e.anInt3377 >> 5;
		}
	}
}
