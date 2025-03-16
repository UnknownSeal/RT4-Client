package com.jagex.runetek4;

import com.jagex.runetek4.game.config.bastype.BASType;
import com.jagex.runetek4.dash3d.entity.NPCRenderable;
import com.jagex.runetek4.dash3d.entity.Actor;
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

	@OriginalMember(owner = "runetek4.client!cm", name = "f", descriptor = "Lsignlink!im;")
	public static PrivilegedRequest js5SocketRequest;

	@OriginalMember(owner = "runetek4.client!cm", name = "a", descriptor = "(ILclient!fe;)V")
	public static void method949(@OriginalArg(1) Actor e) {
		if (e.anInt3376 == 0) {
			return;
		}
		@Pc(13) BASType local13 = e.method2681();
		@Pc(43) int dstX;
		@Pc(36) int dstZ;
		if (e.targetId != -1 && e.targetId < 32768) {
			@Pc(26) NPCRenderable npc = Static175.npcs[e.targetId];
			if (npc != null) {
				dstZ = e.z - npc.z;
				dstX = e.x - npc.x;
				if (dstX != 0 || dstZ != 0) {
					e.dstYaw = (int) (Math.atan2((double) dstX, (double) dstZ) * 325.949D) & 0x7FF;
				}
			}
		}
		@Pc(94) int local94;
		@Pc(70) int index;
		if (e.targetId >= 32768) {
			index = e.targetId - 32768;
			if (index == Static16.localPid) {
				index = 2047;
			}
			@Pc(83) Player player = Static159.players[index];
			if (player != null) {
				local94 = e.z - player.z;
				dstZ = e.x - player.x;
				if (dstZ != 0 || local94 != 0) {
					e.dstYaw = (int) (Math.atan2((double) dstZ, (double) local94) * 325.949D) & 0x7FF;
				}
			}
		}
		if ((e.anInt3382 != 0 || e.anInt3363 != 0) && (e.pathLength == 0 || e.anInt3417 > 0)) {
			index = e.x - (e.anInt3382 - Static225.originX - Static225.originX) * 64;
			dstX = e.z - (e.anInt3363 - Static142.originZ - Static142.originZ) * 64;
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
		} else if (local13.anInt1038 == 0) {
			e.anInt3385++;
			@Pc(226) boolean local226;
			if (index > 1024) {
				e.anInt3381 -= e.anInt3376;
				local226 = true;
				if (index < e.anInt3376 || index > 2048 - e.anInt3376) {
					e.anInt3381 = e.dstYaw;
					local226 = false;
				}
				if (local13.anInt1037 == e.secondarySeqId && (e.anInt3385 > 25 || local226)) {
					if (local13.anInt1036 == -1) {
						e.secondarySeqId = local13.anInt1051;
					} else {
						e.secondarySeqId = local13.anInt1036;
					}
				}
			} else {
				local226 = true;
				e.anInt3381 += e.anInt3376;
				if (e.anInt3376 > index || index > 2048 - e.anInt3376) {
					local226 = false;
					e.anInt3381 = e.dstYaw;
				}
				if (local13.anInt1037 == e.secondarySeqId && (e.anInt3385 > 25 || local226)) {
					if (local13.anInt1067 == -1) {
						e.secondarySeqId = local13.anInt1051;
					} else {
						e.secondarySeqId = local13.anInt1067;
					}
				}
			}
			e.anInt3381 &= 0x7FF;
		} else {
			if (local13.anInt1037 == e.secondarySeqId && e.anInt3385 > 25) {
				if (local13.anInt1067 == -1) {
					e.secondarySeqId = local13.anInt1051;
				} else {
					e.secondarySeqId = local13.anInt1067;
				}
			}
			dstX = e.dstYaw << 5;
			if (dstX != e.anInt3402) {
				e.anInt3387 = 0;
				e.anInt3402 = dstX;
				dstZ = dstX - e.anInt3377 & 0xFFFF;
				local94 = e.anInt3414 * e.anInt3414 / (local13.anInt1038 * 2);
				@Pc(471) int local471;
				if (e.anInt3414 > 0 && dstZ >= local94 && dstZ - local94 < 32768) {
					e.anInt3397 = dstZ / 2;
					e.aBoolean167 = true;
					local471 = local13.anInt1031 * local13.anInt1031 / (local13.anInt1038 * 2);
					if (local471 > 32767) {
						local471 = 32767;
					}
					if (local471 < e.anInt3397) {
						e.anInt3397 = dstZ - local471;
					}
				} else if (e.anInt3414 < 0 && local94 <= 65536 - dstZ && 65536 - dstZ - local94 < 32768) {
					e.anInt3397 = (65536 - dstZ) / 2;
					e.aBoolean167 = true;
					local471 = local13.anInt1031 * local13.anInt1031 / (local13.anInt1038 * 2);
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
				if (dstZ < local13.anInt1038) {
					e.anInt3377 = e.anInt3402;
				} else {
					e.anInt3387 = 0;
					local94 = local13.anInt1031 * local13.anInt1031 / (local13.anInt1038 * 2);
					e.aBoolean167 = true;
					if (local94 > 32767) {
						local94 = 32767;
					}
					if (dstZ >= 32768) {
						e.anInt3414 = -local13.anInt1038;
						e.anInt3397 = (65536 - dstZ) / 2;
						if (local94 < e.anInt3397) {
							e.anInt3397 = 65536 - dstZ - local94;
						}
					} else {
						e.anInt3414 = local13.anInt1038;
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
					e.anInt3414 += local13.anInt1038;
					if (e.anInt3414 > 0) {
						e.anInt3414 = 0;
					}
				} else if (-local13.anInt1031 < e.anInt3414) {
					e.anInt3414 -= local13.anInt1038;
				}
			} else {
				if (e.anInt3397 <= e.anInt3387) {
					e.aBoolean167 = false;
				}
				if (!e.aBoolean167) {
					e.anInt3414 -= local13.anInt1038;
					if (e.anInt3414 < 0) {
						e.anInt3414 = 0;
					}
				} else if (e.anInt3414 < local13.anInt1031) {
					e.anInt3414 += local13.anInt1038;
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
