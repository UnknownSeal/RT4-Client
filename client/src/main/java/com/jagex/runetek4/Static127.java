package com.jagex.runetek4;

import java.io.UnsupportedEncodingException;

import com.jagex.runetek4.dash3d.entity.LocAddEntity;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.config.Component;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static127 {

	@OriginalMember(owner = "runetek4.client!k", name = "j", descriptor = "I")
	public static int anInt3126;

	@OriginalMember(owner = "runetek4.client!k", name = "l", descriptor = "[I")
	public static int[] anIntArray292;

	@OriginalMember(owner = "runetek4.client!k", name = "c", descriptor = "Z")
	public static boolean aBoolean159 = false;

	@OriginalMember(owner = "runetek4.client!k", name = "i", descriptor = "I")
	public static int anInt3125 = 0;

	@OriginalMember(owner = "runetek4.client!k", name = "m", descriptor = "Z")
	public static boolean aBoolean160 = false;

	@OriginalMember(owner = "runetek4.client!k", name = "t", descriptor = "I")
	public static int anInt3132 = 0;

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(IIBLclient!ve;Lclient!ve;)Lclient!rk;")
	public static Font method2462(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1, @OriginalArg(4) Js5 arg2) {
		return Static234.method4016(arg1, 0, arg0) ? Static29.method799(arg2.getfile(arg0, 0)) : null;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(IIIIZIZ)V")
	public static void method2463(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) boolean arg4, @OriginalArg(5) int arg5) {
		if (Static80.anInt4701 == arg2 && arg1 == Static52.anInt1695 && (Static41.anInt1316 == arg0 || Static138.allLevelsvisible())) {
			return;
		}
		Static80.anInt4701 = arg2;
		Static52.anInt1695 = arg1;
		Static41.anInt1316 = arg0;
		if (Static138.allLevelsvisible()) {
			Static41.anInt1316 = 0;
		}
		if (arg4) {
			Game.processGameStatus(28);
		} else {
			Game.processGameStatus(25);
		}
		Static114.method4636(true, LocalizedText.LOADING);
		@Pc(53) int local53 = Static142.originZ;
		@Pc(55) int local55 = Static225.originX;
		Static142.originZ = arg1 * 8 - 48;
		Static225.originX = (arg2 - 6) * 8;
		Static158.aClass3_Sub2_Sub4_3 = Static29.method803(Static80.anInt4701 * 8, Static52.anInt1695 * 8);
		@Pc(81) int dz = Static142.originZ - local53;
		@Pc(86) int dx = Static225.originX - local55;
		Static235.aMapElementTypeList_2 = null;
		@Pc(96) int i;
		@Pc(103) NPCEntity local103;
		@Pc(109) int j;
		if (arg4) {
			Static272.npcCount = 0;
			for (i = 0; i < 32768; i++) {
				local103 = Static175.npcs[i];
				if (local103 != null) {
					local103.x -= dx * 128;
					local103.z -= dz * 128;
					if (local103.x >= 0 && local103.x <= 13184 && local103.z >= 0 && local103.z <= 13184) {
						for (j = 0; j < 10; j++) {
							local103.pathTileX[j] -= dx;
							local103.pathTileZ[j] -= dz;
						}
						Static33.npcIds[Static272.npcCount++] = i;
					} else {
						Static175.npcs[i].method2698(null);
						Static175.npcs[i] = null;
					}
				}
			}
		} else {
			for (i = 0; i < 32768; i++) {
				local103 = Static175.npcs[i];
				if (local103 != null) {
					for (j = 0; j < 10; j++) {
						local103.pathTileX[j] -= dx;
						local103.pathTileZ[j] -= dz;
					}
					local103.x -= dx * 128;
					local103.z -= dz * 128;
				}
			}
		}
		for (i = 0; i < 2048; i++) {
			@Pc(265) PlayerEntity player = Static159.players[i];
			if (player != null) {
				for (j = 0; j < 10; j++) {
					player.pathTileX[j] -= dx;
					player.pathTileZ[j] -= dz;
				}
				player.x -= dx * 128;
				player.z -= dz * 128;
			}
		}
		Static55.currentLevel = arg0;
		Static173.localPlayer.teleport(arg5, false, arg3);
		@Pc(322) byte endTileX = 104;
		@Pc(324) byte startTileX = 0;
		@Pc(326) byte startTileZ = 0;
		@Pc(328) byte dirZ = 1;
		@Pc(330) byte endTileZ = 104;
		@Pc(332) byte dirX = 1;
		if (dz < 0) {
			dirZ = -1;
			endTileZ = -1;
			startTileZ = 103;
		}
		if (dx < 0) {
			dirX = -1;
			startTileX = 103;
			endTileX = -1;
		}
		for (@Pc(358) int x = startTileX; x != endTileX; x += dirX) {
			for (@Pc(367) int z = startTileZ; z != endTileZ; z += dirZ) {
				@Pc(378) int lastX = dx + x;
				@Pc(382) int lastZ = z + dz;
				for (@Pc(384) int level = 0; level < 4; level++) {
					if (lastX >= 0 && lastZ >= 0 && lastX < 104 && lastZ < 104) {
						Static159.levelObjStacks[level][x][z] = Static159.levelObjStacks[level][lastX][lastZ];
					} else {
						Static159.levelObjStacks[level][x][z] = null;
					}
				}
			}
		}
		for (@Pc(451) LocAddEntity loc = (LocAddEntity) Static26.spawnedLocations.head(); loc != null; loc = (LocAddEntity) Static26.spawnedLocations.next()) {
			loc.z -= dz;
			loc.x -= dx;
			if (loc.x < 0 || loc.z < 0 || loc.x >= 104 || loc.z >= 104) {
				loc.unlink();
			}
		}
		if (arg4) {
			Static138.cameraX -= dx * 128;
			Static134.cameraZ -= dz * 128;
			Static248.anInt4232 -= dz;
			Static251.anInt5449 -= dx;
			Static265.anInt5765 -= dz;
			Static245.anInt5375 -= dx;
		} else {
			Static227.anInt5096 = 1;
		}
		Static189.anInt4451 = 0;
		if (Static115.anInt2939 != 0) {
			Static84.anInt2255 -= dz;
			Static115.anInt2939 -= dx;
		}
		if (GlRenderer.enabled && arg4 && (Math.abs(dx) > 104 || Math.abs(dz) > 104)) {
			Static86.method1799();
		}
		Static107.anInt2875 = -1;
		Static99.spotanims.clear();
		Static217.projectiles.clear();
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(B)Lclient!da;")
	public static DelayedStateChange poll() {
		@Pc(10) DelayedStateChange local10 = (DelayedStateChange) Static215.aClass16_9.head();
		if (local10 != null) {
			local10.unlink();
			local10.clear();
			return local10;
		}
		do {
			local10 = (DelayedStateChange) Static140.aClass16_7.head();
			if (local10 == null) {
				return null;
			}
			if (local10.method1009() > MonotonicTime.get()) {
				return null;
			}
			local10.unlink();
			local10.clear();
		} while ((Long.MIN_VALUE & local10.secondaryNodeId) == 0L);
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!be;Lclient!na;I)Lclient!na;")
	public static JString method2465(@OriginalArg(0) Component arg0, @OriginalArg(1) JString arg1) {
		if (arg1.indexOf(Static49.aClass100_352) == -1) {
			return arg1;
		}
		while (true) {
			@Pc(14) int local14 = arg1.indexOf(Static23.aClass100_133);
			if (local14 == -1) {
				while (true) {
					local14 = arg1.indexOf(Static276.aClass100_1097);
					if (local14 == -1) {
						while (true) {
							local14 = arg1.indexOf(Static160.aClass100_761);
							if (local14 == -1) {
								while (true) {
									local14 = arg1.indexOf(Static96.aClass100_520);
									if (local14 == -1) {
										while (true) {
											local14 = arg1.indexOf(Static235.aClass100_1002);
											if (local14 == -1) {
												while (true) {
													local14 = arg1.indexOf(Static122.aClass100_591);
													if (local14 == -1) {
														return arg1;
													}
													@Pc(246) JString local246 = Static186.aClass100_827;
													if (Static232.aClass212_5 != null) {
														local246 = Static181.method3341(Static232.aClass212_5.intArg2);
														try {
															if (Static232.aClass212_5.result != null) {
																@Pc(265) byte[] local265 = ((String) Static232.aClass212_5.result).getBytes("ISO-8859-1");
																local246 = Static10.decodeString(local265, local265.length, 0);
															}
														} catch (@Pc(274) UnsupportedEncodingException local274) {
														}
													}
													arg1 = Static34.method882(new JString[] { arg1.substring(local14, 0), local246, arg1.substring(local14 + 4) });
												}
											}
											arg1 = Static34.method882(new JString[] { arg1.substring(local14, 0), Static262.method4510(ClientScript.executeClientscript(4, arg0)), arg1.substring(local14 + 2) });
										}
									}
									arg1 = Static34.method882(new JString[] { arg1.substring(local14, 0), Static262.method4510(ClientScript.executeClientscript(3, arg0)), arg1.substring(local14 + 2) });
								}
							}
							arg1 = Static34.method882(new JString[] { arg1.substring(local14, 0), Static262.method4510(ClientScript.executeClientscript(2, arg0)), arg1.substring(local14 + 2) });
						}
					}
					arg1 = Static34.method882(new JString[] { arg1.substring(local14, 0), Static262.method4510(ClientScript.executeClientscript(1, arg0)), arg1.substring(local14 + 2) });
				}
			}
			arg1 = Static34.method882(new JString[] { arg1.substring(local14, 0), Static262.method4510(ClientScript.executeClientscript(0, arg0)), arg1.substring(local14 + 2) });
		}
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!na;Z)V")
	public static void method2470(@OriginalArg(0) JString chatTyped) {
		if (Static191.staffModLevel >= 2) {
			@Pc(18) int level;
			@Pc(38) int x;
			@Pc(29) Runtime local29;
			if (chatTyped.equalsIgnoreCase(Static81.GC)) {
				Static119.method2380();
				for (level = 0; level < 10; level++) {
					System.gc();
				}
				local29 = Runtime.getRuntime();
				x = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
				Static103.addMessage(null, 0, Static34.method882(new JString[] { Static202.aClass100_892, Static123.method2423(x), Static17.aClass100_101 }));
			}
			@Pc(117) int z;
			if (chatTyped.equalsIgnoreCase(Static154.MM)) {
				Static119.method2380();
				for (level = 0; level < 10; level++) {
					System.gc();
				}
				local29 = Runtime.getRuntime();
				x = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
				Static103.addMessage(null, 0, Static34.method882(new JString[] { Static203.aClass100_893, Static123.method2423(x), Static17.aClass100_101 }));
				Static16.method501();
				Static119.method2380();
				for (z = 0; z < 10; z++) {
					System.gc();
				}
				x = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
				Static103.addMessage(null, 0, Static34.method882(new JString[] { Static270.aClass100_1093, Static123.method2423(x), Static17.aClass100_101 }));
			}
			if (chatTyped.equalsIgnoreCase(Static240.PCACHESIZE)) {
				Static103.addMessage(null, 0, Static34.method882(new JString[] { Static44.aClass100_335, Static123.method2423(Static198.method1029()) }));
			}
			if (GlRenderer.enabled && chatTyped.equalsIgnoreCase(Static201.CARDMEM)) {
				System.out.println("oncard_geometry:" + Static63.oncard_geometry);
				System.out.println("oncard_2d:" + Static63.oncard_2d);
				System.out.println("oncard_texture:" + Static63.oncard_texture);
			}
			if (chatTyped.equalsIgnoreCase(Static257.CLIENTDROP)) {
				Game.tryReconnect();
			}
			if (chatTyped.equalsIgnoreCase(Static279.CLIENTJS5DROP)) {
				client.js5NetQueue.clientDrop();
			}
			if (chatTyped.equalsIgnoreCase(Static185.SERVERJS5DROP)) {
				client.js5NetQueue.serverDrop();
			}
			if (chatTyped.equalsIgnoreCase(Static165.BREAKCON)) {
				GameShell.signLink.method5110();
				Static124.gameServerSocket.method2833();
				client.js5NetQueue.method2323();
			}
			if (chatTyped.equalsIgnoreCase(Static114.REPLACECANVAS)) {
				Static35.canvasReplaceRecommended = true;
			}
			if (chatTyped.equalsIgnoreCase(Static148.REBUILD)) {
				Game.processGameStatus(25);
			}
			if (chatTyped.equalsIgnoreCase(Static107.FPSON)) {
				Static43.displayFps = true;
			}
			if (chatTyped.equalsIgnoreCase(Static61.FPSOFF)) {
				Static43.displayFps = false;
			}
			if (chatTyped.equalsIgnoreCase(Static96.WM0)) {
				Static241.method4540(false, 0, -1, -1);
			}
			if (chatTyped.equalsIgnoreCase(Static181.WM1)) {
				Static241.method4540(false, 1, -1, -1);
			}
			if (chatTyped.equalsIgnoreCase(Static207.WM2)) {
				Static241.method4540(false, 2, -1, -1);
			}
			if (chatTyped.equalsIgnoreCase(Static99.WM3)) {
				Static241.method4540(false, 3, 1024, 768);
			}
			if (chatTyped.equalsIgnoreCase(Static69.NOCLIP)) {
				for (level = 0; level < 4; level++) {
					for (x = 1; x < 103; x++) {
						for (z = 1; z < 103; z++) {
							Static148.levelCollisionMap[level].flags[x][z] = 0;
						}
					}
				}
			}
			if (chatTyped.startsWith(Static241.SETPARTICLES)) {
				Static76.method1645(chatTyped.substring(15).method3132());
				Static203.method3663(GameShell.signLink);
				Static18.sentToServer = false;
			}
			if (chatTyped.startsWith(Static170.FPS) && client.modeWhere != 0) {
				Static115.method2312(chatTyped.substring(6).method3132());
			}
			if (chatTyped.equalsIgnoreCase(Static272.ERRORTEST)) {
				throw new RuntimeException();
			}
			if (chatTyped.startsWith(Static211.RECT_DEBUG)) {
				Static199.anInt4672 = chatTyped.substring(12).trim().method3132();
				Static103.addMessage(null, 0, Static34.method882(new JString[] { Static276.aClass100_1096, Static123.method2423(Static199.anInt4672) }));
			}
			if (chatTyped.equalsIgnoreCase(Static181.QA_OP_TEST)) {
				Static121.aBoolean154 = true;
			}
			if (chatTyped.equalsIgnoreCase(Static124.TWEEN)) {
				if (Static204.tween) {
					Static204.tween = false;
					Static103.addMessage(null, 0, Static274.FORCED_TWEENING_DISABLED);
				} else {
					Static204.tween = true;
					Static103.addMessage(null, 0, Static50.FORCED_TWEENING_ENABLED);
				}
			}
			if (chatTyped.equalsIgnoreCase(Static114.SHIFTCLICK)) {
				if (Static172.shiftClick) {
					Static154.SHIFTCLICK_DISABLED.printToConsole();
					Static172.shiftClick = false;
				} else {
					Static43.SHIFTCLICK_ENABLED.printToConsole();
					Static172.shiftClick = true;
				}
			}
		}
		Static6.outboundBuffer.pIsaac1(44);
		Static6.outboundBuffer.p1(chatTyped.length() - 1);
		Static6.outboundBuffer.pjstr(chatTyped.substring(2));
	}
}
