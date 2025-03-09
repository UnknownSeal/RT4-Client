package com.jagex.runetek4;

import com.jagex.runetek4.game.scene.entities.NPCEntity;
import com.jagex.runetek4.game.world.entity.PlayerEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static173 {

    @OriginalMember(owner = "runetek4.client!nk", name = "E", descriptor = "I")
	public static int anInt4183;

	@OriginalMember(owner = "runetek4.client!nk", name = "L", descriptor = "[Lclient!mm;")
	public static SoftwareSprite[] aClass3_Sub2_Sub1_Sub1Array9;

	@OriginalMember(owner = "runetek4.client!nk", name = "O", descriptor = "Lclient!e;")
	public static PlayerEntity localPlayer;

	@OriginalMember(owner = "runetek4.client!nk", name = "n", descriptor = "[Lclient!sl;")
	public static final SynthSound[] aClass138Array1 = new SynthSound[50];

	@OriginalMember(owner = "runetek4.client!nk", name = "c", descriptor = "(IZ)V")
	public static void pushNpcs(@OriginalArg(1) boolean arg0) {
		@Pc(7) int i;
		@Pc(16) NPCEntity npc;
		@Pc(107) int npcSize;
		@Pc(113) int x;
		@Pc(133) int z;
		@Pc(149) int local149;
		@Pc(158) int local158;
		@Pc(171) int local171;
		for (i = 0; i < Static272.npcCount; i++) {
			npc = Static175.npcs[Static33.npcIds[i]];
			if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.method2933()) {
				@Pc(42) int npcSize2 = npc.size();
				@Pc(97) int local97;
				if (npcSize2 == 1) {
					if ((npc.x & 0x7F) == 64 && (npc.z & 0x7F) == 64) {
						local97 = npc.x >> 7;
						npcSize = npc.z >> 7;
						if (local97 >= 0 && local97 < 104 && npcSize >= 0 && npcSize < 104) {
							local171 = Static31.anIntArrayArray6[local97][npcSize]++;
						}
					}
				} else if (((npcSize2 & 0x1) != 0 || (npc.x & 0x7F) == 0 && (npc.z & 0x7F) == 0) && ((npcSize2 & 0x1) != 1 || (npc.x & 0x7F) == 64 && (npc.z & 0x7F) == 64)) {
					local97 = npc.x - npcSize2 * 64 >> 7;
					npcSize = npc.z - npcSize2 * 64 >> 7;
					x = npc.size() + local97;
					if (local97 < 0) {
						local97 = 0;
					}
					if (x > 104) {
						x = 104;
					}
					z = npcSize + npc.size();
					if (npcSize < 0) {
						npcSize = 0;
					}
					if (z > 104) {
						z = 104;
					}
					for (local149 = local97; local149 < x; local149++) {
						for (local158 = npcSize; local158 < z; local158++) {
							local171 = Static31.anIntArrayArray6[local149][local158]++;
						}
					}
				}
			}
		}
		label200: for (i = 0; i < Static272.npcCount; i++) {
			npc = Static175.npcs[Static33.npcIds[i]];
			@Pc(262) long bitset = (long) Static33.npcIds[i] << 32 | 0x20000000L;
			if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.method2933()) {
				npcSize = npc.size();
				if (npcSize == 1) {
					if ((npc.x & 0x7F) == 64 && (npc.z & 0x7F) == 64) {
						x = npc.x >> 7;
						z = npc.z >> 7;
						if (x < 0 || x >= 104 || z < 0 || z >= 104) {
							continue;
						}
						if (Static31.anIntArrayArray6[x][z] > 1) {
							local171 = Static31.anIntArrayArray6[x][z]--;
							continue;
						}
					}
				} else if ((npcSize & 0x1) == 0 && (npc.x & 0x7F) == 0 && (npc.z & 0x7F) == 0 || (npcSize & 0x1) == 1 && (npc.x & 0x7F) == 64 && (npc.z & 0x7F) == 64) {
					x = npc.x - npcSize * 64 >> 7;
					z = npc.z - npcSize * 64 >> 7;
					local158 = z + npcSize;
					if (z < 0) {
						z = 0;
					}
					@Pc(368) boolean local368 = true;
					local149 = x + npcSize;
					if (local158 > 104) {
						local158 = 104;
					}
					if (x < 0) {
						x = 0;
					}
					if (local149 > 104) {
						local149 = 104;
					}
					@Pc(396) int local396;
					@Pc(401) int local401;
					for (local396 = x; local396 < local149; local396++) {
						for (local401 = z; local401 < local158; local401++) {
							if (Static31.anIntArrayArray6[local396][local401] <= 1) {
								local368 = false;
								break;
							}
						}
					}
					if (local368) {
						local396 = x;
						while (true) {
							if (local396 >= local149) {
								continue label200;
							}
							for (local401 = z; local401 < local158; local401++) {
								local171 = Static31.anIntArrayArray6[local396][local401]--;
							}
							local396++;
						}
					}
				}
				if (!npc.type.active) {
					bitset |= Long.MIN_VALUE;
				}
				npc.y = Static207.getHeightmapY(Static55.currentLevel, npc.x, npc.z);
				Static43.addTemporary(Static55.currentLevel, npc.x, npc.z, npc.y, npcSize * 64 + 60 - 64, npc, npc.anInt3381, bitset, npc.aBoolean171);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!nk", name = "a", descriptor = "(Z[J[I)V")
	public static void method3243(@OriginalArg(1) long[] arg0, @OriginalArg(2) int[] arg1) {
		Static83.method436(arg0, 0, arg0.length - 1, arg1);
	}

	@OriginalMember(owner = "runetek4.client!nk", name = "a", descriptor = "(IIIIIB)V")
	public static void method3246(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg4 > Static106.anInt2869 || arg2 < Static267.anInt5773) {
			return;
		}
		@Pc(24) boolean local24;
		if (Static172.anInt4164 > arg1) {
			local24 = false;
			arg1 = Static172.anInt4164;
		} else if (Static224.anInt5063 >= arg1) {
			local24 = true;
		} else {
			local24 = false;
			arg1 = Static224.anInt5063;
		}
		@Pc(43) boolean local43;
		if (arg3 < Static172.anInt4164) {
			arg3 = Static172.anInt4164;
			local43 = false;
		} else if (arg3 > Static224.anInt5063) {
			arg3 = Static224.anInt5063;
			local43 = false;
		} else {
			local43 = true;
		}
		if (Static267.anInt5773 > arg4) {
			arg4 = Static267.anInt5773;
		} else {
			Static131.method2576(Static71.anIntArrayArray10[arg4++], arg1, arg3, arg0);
		}
		if (arg2 <= Static106.anInt2869) {
			Static131.method2576(Static71.anIntArrayArray10[arg2--], arg1, arg3, arg0);
		} else {
			arg2 = Static106.anInt2869;
		}
		@Pc(98) int local98;
		if (local24 && local43) {
			for (local98 = arg4; local98 <= arg2; local98++) {
				@Pc(105) int[] local105 = Static71.anIntArrayArray10[local98];
				local105[arg1] = local105[arg3] = arg0;
			}
		} else if (local24) {
			for (local98 = arg4; local98 <= arg2; local98++) {
				Static71.anIntArrayArray10[local98][arg1] = arg0;
			}
		} else if (local43) {
			for (local98 = arg4; local98 <= arg2; local98++) {
				Static71.anIntArrayArray10[local98][arg3] = arg0;
			}
		}
	}
}
