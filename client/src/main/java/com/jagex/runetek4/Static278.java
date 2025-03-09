package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static278 {

	@OriginalMember(owner = "runetek4.client!wj", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_1101 = Static28.parse(" <col=ffff00>");

	@OriginalMember(owner = "runetek4.client!wj", name = "e", descriptor = "Lclient!na;")
	public static JString mainLoadPrimaryText = null;

	@OriginalMember(owner = "runetek4.client!wj", name = "f", descriptor = "Lclient!na;")
	public static final JString aClass100_1103 = Static28.parse("ul");

	@OriginalMember(owner = "runetek4.client!wj", name = "l", descriptor = "I")
	public static int anInt5867 = 0;

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(I)V")
	public static void method4645() {
		while (true) {
			if (Static57.in.bitsAvailable(Static223.packetSize) >= 27) {
				@Pc(14) int local14 = Static57.in.gBit(15);
				if (local14 != 32767) {
					@Pc(19) boolean local19 = false;
					if (Static175.npcs[local14] == null) {
						local19 = true;
						Static175.npcs[local14] = new NPCEntity();
					}
					@Pc(37) NPCEntity local37 = Static175.npcs[local14];
					Static33.npcIds[Static272.npcCount++] = local14;
					local37.cycle = Static83.loopCycle;
					if (local37.type != null && local37.type.hasBackgroundSound()) {
						Static91.method1877(local37);
					}
					@Pc(66) int local66 = Static57.in.gBit(1);
					@Pc(73) int local73 = Static56.anIntArray141[Static57.in.gBit(3)];
					if (local19) {
						local37.dstYaw = local37.anInt3381 = local73;
					}
					@Pc(86) int local86 = Static57.in.gBit(1);
					if (local86 == 1) {
						Static44.entityUpdateIds[Static116.entityUpdateCount++] = local14;
					}
					@Pc(105) int local105 = Static57.in.gBit(5);
					local37.method2698(Static214.get(Static57.in.gBit(14)));
					if (local105 > 15) {
						local105 -= 32;
					}
					@Pc(124) int local124 = Static57.in.gBit(5);
					if (local124 > 15) {
						local124 -= 32;
					}
					local37.setSize(local37.type.size);
					local37.anInt3365 = local37.type.bas;
					local37.anInt3376 = local37.type.turnspeed;
					if (local37.anInt3376 == 0) {
						local37.anInt3381 = 0;
					}
					local37.teleport(local37.size(), Static173.localPlayer.pathTileX[0] + local124, local105 + Static173.localPlayer.pathTileZ[0], local66 == 1);
					if (local37.type.hasBackgroundSound()) {
						AreaSoundManager.add(local37.pathTileZ[0], null, 0, local37, local37.pathTileX[0], Static55.currentLevel, null);
					}
					continue;
				}
			}
			Static57.in.accessBytes();
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

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(Z)V")
	public static void method4648(@OriginalArg(0) boolean arg0) {
		if (arg0) {
			Static130.levelTiles = Static276.aClass3_Sub5ArrayArrayArray3;
			Static83.levelHeightMap = Static80.anIntArrayArrayArray19;
			Static182.aClass3_Sub14ArrayArray2 = Static195.aClass3_Sub14ArrayArray3;
		} else {
			Static130.levelTiles = Static197.aClass3_Sub5ArrayArrayArray2;
			Static83.levelHeightMap = Static107.anIntArrayArrayArray10;
			Static182.aClass3_Sub14ArrayArray2 = Static36.aClass3_Sub14ArrayArray1;
		}
		Static126.anInt3114 = Static130.levelTiles.length;
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "b", descriptor = "(I)V")
	public static void method4649() {
		Static125.aClass99_18.method3104();
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIB)V")
	public static void method4650(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (Static12.anInt391 != 0 && arg1 != -1) {
			Static122.method2410(Static214.aClass153_106, arg1, Static12.anInt391);
			Static144.aBoolean173 = true;
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
			Static12.aByteArrayArrayArray2[arg7][arg5][arg4] = 0;
		}
		while (true) {
			local32 = arg3.g1();
			if (local32 == 0) {
				if (arg2) {
					Static83.levelHeightMap[0][arg5][arg4] = Static107.anIntArrayArrayArray10[0][arg5][arg4];
				} else if (arg7 == 0) {
					Static83.levelHeightMap[0][arg5][arg4] = -Static65.method1498(arg4 + arg1 + 556238, arg0 + arg5 + 932731) * 8;
				} else {
					Static83.levelHeightMap[arg7][arg5][arg4] = Static83.levelHeightMap[arg7 - 1][arg5][arg4] - 240;
				}
				break;
			}
			if (local32 == 1) {
				@Pc(111) int local111 = arg3.g1();
				if (arg2) {
					Static83.levelHeightMap[0][arg5][arg4] = Static107.anIntArrayArrayArray10[0][arg5][arg4] + local111 * 8;
				} else {
					if (local111 == 1) {
						local111 = 0;
					}
					if (arg7 == 0) {
						Static83.levelHeightMap[0][arg5][arg4] = -local111 * 8;
					} else {
						Static83.levelHeightMap[arg7][arg5][arg4] = Static83.levelHeightMap[arg7 - 1][arg5][arg4] - local111 * 8;
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
				Static12.aByteArrayArrayArray2[arg7][arg5][arg4] = (byte) (local32 - 49);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!wj", name = "b", descriptor = "(B)V")
	public static void processLogout() {
		if (Static124.socket != null) {
			Static124.socket.closeGracefully();
			Static124.socket = null;
		}
		Static217.method3768();
		Static65.method1500();
		@Pc(19) int local19;
		for (local19 = 0; local19 < 4; local19++) {
			Static148.levelCollisionMap[local19].reset();
		}
		Static116.method2325(false);
		System.gc();
		Static29.method801();
		Static144.aBoolean173 = false;
		BZip2State.anInt4363 = -1;
		AreaSoundManager.clear(true);
		Static230.aBoolean250 = false;
		Static142.originZ = 0;
		Static80.anInt4701 = 0;
		Static52.anInt1695 = 0;
		Static225.originX = 0;
		for (local19 = 0; local19 < Static143.aClass102Array1.length; local19++) {
			Static143.aClass102Array1[local19] = null;
		}
		Static267.playerCount = 0;
		Static272.npcCount = 0;
		for (local19 = 0; local19 < 2048; local19++) {
			Static159.players[local19] = null;
			Static115.playerAppearanceBuffer[local19] = null;
		}
		for (local19 = 0; local19 < 32768; local19++) {
			Static175.npcs[local19] = null;
		}
		for (local19 = 0; local19 < 4; local19++) {
			for (@Pc(115) int local115 = 0; local115 < 104; local115++) {
				for (@Pc(122) int local122 = 0; local122 < 104; local122++) {
					Static159.levelObjStacks[local19][local115][local122] = null;
				}
			}
		}
		Static35.method902();
		Static189.anInt4443 = 0;
		Static8.method121();
		Static73.method1596(true);
	}
}
