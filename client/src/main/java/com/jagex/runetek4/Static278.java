package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static278 {

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(I)V")
	public static void method4645() {
		while (true) {
			if (Protocol.inboundBuffer.bitsAvailable(Protocol.packetSize) >= 27) {
				@Pc(14) int local14 = Protocol.inboundBuffer.gBit(15);
				if (local14 != 32767) {
					@Pc(19) boolean local19 = false;
					if (NpcList.npcs[local14] == null) {
						local19 = true;
						NpcList.npcs[local14] = new Npc();
					}
					@Pc(37) Npc local37 = NpcList.npcs[local14];
					NpcList.npcIds[NpcList.npcCount++] = local14;
					local37.cycle = client.loop;
					if (local37.type != null && local37.type.hasBackgroundSound()) {
						AreaSoundManager.remove(local37);
					}
					@Pc(66) int local66 = Protocol.inboundBuffer.gBit(1);
					@Pc(73) int local73 = PathingEntity.ANGLES[Protocol.inboundBuffer.gBit(3)];
					if (local19) {
						local37.dstYaw = local37.anInt3381 = local73;
					}
					@Pc(86) int local86 = Protocol.inboundBuffer.gBit(1);
					if (local86 == 1) {
						Static44.entityUpdateIds[Static116.entityUpdateCount++] = local14;
					}
					@Pc(105) int local105 = Protocol.inboundBuffer.gBit(5);
					local37.setNpcType(NpcType.getDefinition(Protocol.inboundBuffer.gBit(14)));
					if (local105 > 15) {
						local105 -= 32;
					}
					@Pc(124) int local124 = Protocol.inboundBuffer.gBit(5);
					if (local124 > 15) {
						local124 -= 32;
					}
					local37.setSize(local37.type.size);
					local37.anInt3365 = local37.type.bas;
					local37.anInt3376 = local37.type.turnspeed;
					if (local37.anInt3376 == 0) {
						local37.anInt3381 = 0;
					}
					local37.teleport(local37.getSize(), PlayerList.self.movementQueueX[0] + local124, local105 + PlayerList.self.movementQueueZ[0], local66 == 1);
					if (local37.type.hasBackgroundSound()) {
						AreaSoundManager.add(local37.movementQueueZ[0], null, 0, local37, local37.movementQueueX[0], Player.plane, null);
					}
					continue;
				}
			}
			Protocol.inboundBuffer.accessBytes();
			return;
		}
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIIIIII)V")
	public static void method4647(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) Class120 local3 = new Class120();
		local3.anInt4452 = arg1 / 128;
		local3.anInt4446 = arg2 / 128;
		local3.anInt4461 = arg3 / 128;
		local3.anInt4464 = arg4 / 128;
		local3.anInt4453 = arg0;
		local3.anInt4460 = arg1;
		local3.anInt4445 = arg2;
		local3.anInt4458 = arg3;
		local3.anInt4449 = arg4;
		local3.anInt4444 = arg5;
		local3.anInt4447 = arg6;
		Static91.aClass120Array1[Static28.anInt917++] = local3;
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "b", descriptor = "(I)V")
	public static void method4649() {
		Static125.aClass99_18.clean();
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIB)V")
	public static void method4650(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (Preferences.musicVolume != 0 && arg1 != -1) {
			MidiPlayer.playImmediate(client.js5Archive11, arg1, Preferences.musicVolume);
			Static144.jingle = true;
		}
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIZLclient!wa;IIBII)V")
	public static void method4651(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) Packet arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		@Pc(32) int local32;
		if (arg5 < 0 || arg5 >= 104 || arg4 < 0 || arg4 >= 104) {
			while (true) {
				local32 = arg3.g1();
				if (local32 == 0) {
					break;
				}
				if (local32 == 1) {
					arg3.g1();
					break;
				}
				if (local32 <= 49) {
					arg3.g1();
				}
			}
			return;
		}
		if (!arg2) {
			SceneGraph.renderFlags[arg7][arg5][arg4] = 0;
		}
		while (true) {
			local32 = arg3.g1();
			if (local32 == 0) {
				if (arg2) {
					SceneGraph.tileHeights[0][arg5][arg4] = SceneGraph.surfaceTileHeights[0][arg5][arg4];
				} else if (arg7 == 0) {
					SceneGraph.tileHeights[0][arg5][arg4] = -Static65.method1498(arg4 + arg1 + 556238, arg0 + arg5 + 932731) * 8;
				} else {
					SceneGraph.tileHeights[arg7][arg5][arg4] = SceneGraph.tileHeights[arg7 - 1][arg5][arg4] - 240;
				}
				break;
			}
			if (local32 == 1) {
				@Pc(111) int local111 = arg3.g1();
				if (arg2) {
					SceneGraph.tileHeights[0][arg5][arg4] = SceneGraph.surfaceTileHeights[0][arg5][arg4] + local111 * 8;
				} else {
					if (local111 == 1) {
						local111 = 0;
					}
					if (arg7 == 0) {
						SceneGraph.tileHeights[0][arg5][arg4] = -local111 * 8;
					} else {
						SceneGraph.tileHeights[arg7][arg5][arg4] = SceneGraph.tileHeights[arg7 - 1][arg5][arg4] - local111 * 8;
					}
				}
				break;
			}
			if (local32 <= 49) {
				Static240.aByteArrayArrayArray14[arg7][arg5][arg4] = arg3.g1s();
				Static163.aByteArrayArrayArray11[arg7][arg5][arg4] = (byte) ((local32 - 2) / 4);
				Static4.aByteArrayArrayArray1[arg7][arg5][arg4] = (byte) (local32 + arg6 - 2 & 0x3);
			} else if (local32 > 81) {
				Static253.levelTileUnderlayIds[arg7][arg5][arg4] = (byte) (local32 - 81);
			} else if (!arg2) {
				SceneGraph.renderFlags[arg7][arg5][arg4] = (byte) (local32 - 49);
			}
		}
	}

}
