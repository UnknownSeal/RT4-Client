package com.jagex.runetek4;

import com.jagex.runetek4.audio.SynthSound;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.frame.Minimap;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static54 {

	@OriginalMember(owner = "runetek4.client!ed", name = "D", descriptor = "Lclient!na;")
	public static final JString DETAILS = JString.parse("details");

	@OriginalMember(owner = "runetek4.client!ed", name = "H", descriptor = "Lclient!na;")
	public static final JString aClass100_375 = JString.parse("<)4col> x");

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(III)V")
	public static void method1304(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (Component.load(arg1)) {
			Static2.method7(Component.cachedComponents[arg1], arg0);
		}
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(IBIILclient!be;)V")
	public static void method1305(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Component arg3) {
		client.audioLoop();
		if (GlRenderer.enabled) {
			Static46.method1187(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		} else {
			Rasterizer.setBounds(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		}
		if (Minimap.state != 2 && Minimap.state != 5 && Minimap.sprite != null) {
			@Pc(48) int angle = Minimap.minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
			@Pc(57) int anchorX = PlayerList.self.xFine / 32 + 48;
			@Pc(67) int anchorY = 464 - PlayerList.self.zFine / 32;
			if (GlRenderer.enabled) {
				((GlSprite) Minimap.sprite).method1427(arg2, arg1, arg3.anInt445, arg3.anInt459, anchorX, anchorY, angle, Minimap.minimapZoom + 256, (GlSprite) arg3.method489(false));
			} else {
				((ImageRGB) Minimap.sprite).method310(arg2, arg1, arg3.anInt445, arg3.anInt459, anchorX, anchorY, angle, Minimap.minimapZoom + 256, arg3.anIntArray37, arg3.anIntArray45);
			}
			@Pc(146) int flagX;
			@Pc(181) int flagZ;
			@Pc(150) int local150;
			@Pc(154) int local154;
			@Pc(231) int npcX;
			@Pc(200) int npcZ;
			@Pc(239) int local239;
			@Pc(271) int local271;
			if (Static235.aMapElementTypeList_2 != null) {
				for (@Pc(117) int local117 = 0; local117 < Static235.aMapElementTypeList_2.anInt5074; local117++) {
					if (Static235.aMapElementTypeList_2.method3892(local117)) {
						flagX = (Static235.aMapElementTypeList_2.aShortArray73[local117] - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
						local150 = MathUtils.sin[angle];
						local154 = MathUtils.cos[angle];
						@Pc(156) Font local156 = Static114.aClass3_Sub2_Sub9_42;
						@Pc(164) int local164 = local150 * 256 / (Minimap.minimapZoom + 256);
						flagZ = (Static235.aMapElementTypeList_2.aShortArray72[local117] - Camera.originZ) * 4 + 2 - PlayerList.self.zFine / 32;
						@Pc(189) int local189 = local154 * 256 / (Minimap.minimapZoom + 256);
						npcZ = flagZ * local189 - flagX * local164 >> 16;
						if (Static235.aMapElementTypeList_2.method3894(local117) == 1) {
							local156 = Static215.aClass3_Sub2_Sub9_32;
						}
						if (Static235.aMapElementTypeList_2.method3894(local117) == 2) {
							local156 = Fonts.b12Full;
						}
						npcX = local164 * flagZ + local189 * flagX >> 16;
						local239 = local156.method2856(Static235.aMapElementTypeList_2.aClass100Array153[local117], 100);
						@Pc(245) int local245 = npcX - local239 / 2;
						if (local245 >= -arg3.anInt445 && local245 <= arg3.anInt445 && npcZ >= -arg3.anInt459 && npcZ <= arg3.anInt459) {
							local271 = 16777215;
							if (Static235.aMapElementTypeList_2.anIntArray444[local117] != -1) {
								local271 = Static235.aMapElementTypeList_2.anIntArray444[local117];
							}
							if (GlRenderer.enabled) {
								Static46.method1188((GlSprite) arg3.method489(false));
							} else {
								Rasterizer.method2486(arg3.anIntArray37, arg3.anIntArray45);
							}
							local156.method2869(Static235.aMapElementTypeList_2.aClass100Array153[local117], arg2 + local245 + arg3.anInt445 / 2, arg1 + arg3.anInt459 / 2 + -npcZ, local239, 50, local271, 0, 1, 0, 0);
							if (GlRenderer.enabled) {
								Static46.method1173();
							} else {
								Rasterizer.method2482();
							}
						}
					}
				}
			}
			for (flagX = 0; flagX < Static251.anInt5454; flagX++) {
				flagZ = Static145.anIntArray331[flagX] * 4 + 2 - PlayerList.self.xFine / 32;
				local150 = Static93.anIntArray219[flagX] * 4 + 2 - PlayerList.self.zFine / 32;
				@Pc(382) LocType local382 = LocTypeList.get(Static199.anIntArray417[flagX]);
				if (local382.multiloc != null) {
					local382 = local382.getMultiLoc();
					if (local382 == null || local382.mapfunction == -1) {
						continue;
					}
				}
				Minimap.drawOnMinimap(arg3, Static67.aClass3_Sub2_Sub1Array4[local382.mapfunction], local150, flagZ, arg1, arg2);
			}
			for (flagX = 0; flagX < 104; flagX++) {
				for (flagZ = 0; flagZ < 104; flagZ++) {
					@Pc(439) LinkedList local439 = SceneGraph.objStacks[Player.plane][flagX][flagZ];
					if (local439 != null) {
						local154 = flagX * 4 + 2 - PlayerList.self.xFine / 32;
						npcX = flagZ * 4 + 2 - PlayerList.self.zFine / 32;
						Minimap.drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[0], npcX, local154, arg1, arg2);
					}
				}
			}
			for (flagX = 0; flagX < NpcList.npcCount; flagX++) {
				@Pc(498) Npc local498 = NpcList.npcs[Static33.npcIds[flagX]];
				if (local498 != null && local498.isVisible()) {
					@Pc(507) NpcType local507 = local498.type;
					if (local507 != null && local507.multiNpcs != null) {
						local507 = local507.getMultiNPC();
					}
					if (local507 != null && local507.minimap && local507.active) {
						local154 = local498.xFine / 32 - PlayerList.self.xFine / 32;
						npcX = local498.zFine / 32 - PlayerList.self.zFine / 32;
						if (local507.anInt3739 == -1) {
							Minimap.drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[1], npcX, local154, arg1, arg2);
						} else {
							Minimap.drawOnMinimap(arg3, Static67.aClass3_Sub2_Sub1Array4[local507.anInt3739], npcX, local154, arg1, arg2);
						}
					}
				}
			}
			for (flagX = 0; flagX < PlayerList.playerCount; flagX++) {
				@Pc(591) Player local591 = PlayerList.players[PlayerList.playerIds[flagX]];
				if (local591 != null && local591.isVisible()) {
					local154 = local591.zFine / 32 - PlayerList.self.zFine / 32;
					local150 = local591.xFine / 32 - PlayerList.self.xFine / 32;
					@Pc(624) long name = local591.username.encode37();
					@Pc(626) boolean isFriend = false;
					for (local239 = 0; local239 < FriendList.friendCount; local239++) {
						if (name == Static92.friendName37[local239] && Static104.friendWorld[local239] != 0) {
							isFriend = true;
							break;
						}
					}
					@Pc(660) boolean local660 = false;
					for (local271 = 0; local271 < ClanChat.size; local271++) {
						if (name == ClanChat.members[local271].nodeId) {
							local660 = true;
							break;
						}
					}
					@Pc(682) boolean isTeammate = false;
					if (PlayerList.self.teamId != 0 && local591.teamId != 0 && local591.teamId == PlayerList.self.teamId) {
						isTeammate = true;
					}
					if (isFriend) {
						Minimap.drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[3], local154, local150, arg1, arg2);
					} else if (local660) {
						Minimap.drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[5], local154, local150, arg1, arg2);
					} else if (isTeammate) {
						Minimap.drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[4], local154, local150, arg1, arg2);
					} else {
						Minimap.drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[2], local154, local150, arg1, arg2);
					}
				}
			}
			@Pc(756) Class102[] local756 = Minimap.hintMapMarkers;
			for (flagZ = 0; flagZ < local756.length; flagZ++) {
				@Pc(770) Class102 local770 = local756[flagZ];
				if (local770 != null && local770.headIconDrawType != 0 && client.loop % 20 < 10) {
					if (local770.headIconDrawType == 1 && local770.hintIconNpcTarget >= 0 && local770.hintIconNpcTarget < NpcList.npcs.length) {
						@Pc(804) Npc npc = NpcList.npcs[local770.hintIconNpcTarget];
						if (npc != null) {
							npcX = npc.xFine / 32 - PlayerList.self.xFine / 32;
							npcZ = npc.zFine / 32 - PlayerList.self.zFine / 32;
							Static97.drawMinimapMark(local770.anInt4048, arg1, arg2, npcX, npcZ, arg3);
						}
					}
					if (local770.headIconDrawType == 2) {
						local154 = (local770.anInt4053 - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
						npcX = (-Camera.originZ + local770.anInt4046) * 4 + 2 - PlayerList.self.zFine / 32;
						Static97.drawMinimapMark(local770.anInt4048, arg1, arg2, local154, npcX, arg3);
					}
					if (local770.headIconDrawType == 10 && local770.hintIconNpcTarget >= 0 && PlayerList.players.length > local770.hintIconNpcTarget) {
						@Pc(905) Player player = PlayerList.players[local770.hintIconNpcTarget];
						if (player != null) {
							npcZ = player.zFine / 32 - PlayerList.self.zFine / 32;
							npcX = player.xFine / 32 - PlayerList.self.xFine / 32;
							Static97.drawMinimapMark(local770.anInt4048, arg1, arg2, npcX, npcZ, arg3);
						}
					}
				}
			}
			if (LoginManager.mapFlagX != 0) {
				flagX = LoginManager.mapFlagX * 4 + 2 - PlayerList.self.xFine / 32;
				flagZ = LoginManager.mapFlagZ * 4 + 2 - PlayerList.self.zFine / 32;
				Minimap.drawOnMinimap(arg3, Static84.aClass3_Sub2_Sub1_4, flagZ, flagX, arg1, arg2);
			}
			if (GlRenderer.enabled) {
				Static46.method1186(arg2 + arg3.anInt445 / 2 - 1, arg1 + -1 - -(arg3.anInt459 / 2), 3, 3, 16777215);
			} else {
				Rasterizer.drawFilledRectangle(arg3.anInt445 / 2 + arg2 - 1, arg3.anInt459 / 2 + -1 + arg1, 3, 3, 16777215);
			}
		} else if (GlRenderer.enabled) {
			@Pc(1041) Sprite local1041 = arg3.method489(false);
			if (local1041 != null) {
				local1041.drawSprite(arg2, arg1);
			}
		} else {
			Rasterizer.method2504(arg2, arg1, arg3.anIntArray37, arg3.anIntArray45);
		}
		InterfaceList.rectangleRedraw[arg0] = true;
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static172.anInt4164 && arg3 <= FluTypeList.anInt5063) {
			@Pc(22) int local22 = Static78.method1690(Static106.anInt2869, arg1, Static267.anInt5773);
			@Pc(28) int local28 = Static78.method1690(Static106.anInt2869, arg0, Static267.anInt5773);
			Static101.method2054(local22, arg3, local28, arg2);
		}
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "([SI[Lclient!na;II)V")
	public static void method1307(@OriginalArg(0) short[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JString[] arg2, @OriginalArg(4) int arg3) {
		if (arg1 <= arg3) {
			return;
		}
		@Pc(14) int local14 = arg3;
		@Pc(21) int local21 = (arg3 + arg1) / 2;
		@Pc(25) JString local25 = arg2[local21];
		arg2[local21] = arg2[arg1];
		arg2[arg1] = local25;
		@Pc(39) short local39 = arg0[local21];
		arg0[local21] = arg0[arg1];
		arg0[arg1] = local39;
		for (@Pc(51) int local51 = arg3; local51 < arg1; local51++) {
			if (local25 == null || arg2[local51] != null && arg2[local51].method3139(local25) < (local51 & 0x1)) {
				@Pc(80) JString local80 = arg2[local51];
				arg2[local51] = arg2[local14];
				arg2[local14] = local80;
				@Pc(94) short local94 = arg0[local51];
				arg0[local51] = arg0[local14];
				arg0[local14++] = local94;
			}
		}
		arg2[arg1] = arg2[local14];
		arg2[local14] = local25;
		arg0[arg1] = arg0[local14];
		arg0[local14] = local39;
		method1307(arg0, local14 - 1, arg2, arg3);
		method1307(arg0, arg1, arg2, local14 + 1);
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "c", descriptor = "(I)V")
	public static void clear() {
		Static83.aClass99_3.clear();
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(IIII)I")
	public static int method1309(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		if (arg0 > 243) {
			arg1 >>= 0x4;
		} else if (arg0 > 217) {
			arg1 >>= 0x3;
		} else if (arg0 > 192) {
			arg1 >>= 0x2;
		} else if (arg0 > 179) {
			arg1 >>= 0x1;
		}
		return (arg0 >> 1) + (arg1 >> 5 << 7) + (arg2 >> 2 << 10);
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "b", descriptor = "(II)Lclient!ba;")
	public static GWCWorld method1310(@OriginalArg(1) int arg0) {
		return WorldList.loaded && arg0 >= Static19.anInt636 && arg0 <= Static171.anInt4157 ? Static196.aClass10_Sub1Array2[arg0 - Static19.anInt636] : null;
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "d", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(5) int local5 = 0; local5 < SoundPlayer.size; local5++) {
			@Pc(12) int local12 = Static164.anIntArray362[local5]--;
			if (Static164.anIntArray362[local5] >= -10) {
				@Pc(79) SynthSound local79 = Static173.aClass138Array1[local5];
				if (local79 == null) {
					local79 = SynthSound.create(client.js5Archive4, Static200.anIntArray421[local5], 0);
					if (local79 == null) {
						continue;
					}
					Static164.anIntArray362[local5] += local79.delay();
					Static173.aClass138Array1[local5] = local79;
				}
				if (Static164.anIntArray362[local5] < 0) {
					@Pc(209) int local209;
					if (Static26.anIntArray68[local5] == 0) {
						local209 = Static125.anInt3104;
					} else {
						@Pc(125) int local125 = (Static26.anIntArray68[local5] & 0xFF) * 128;
						@Pc(133) int local133 = Static26.anIntArray68[local5] >> 8 & 0xFF;
						@Pc(141) int local141 = Static26.anIntArray68[local5] >> 16 & 0xFF;
						@Pc(151) int local151 = local133 * 128 + 64 - PlayerList.self.zFine;
						if (local151 < 0) {
							local151 = -local151;
						}
						@Pc(167) int local167 = local141 * 128 + 64 - PlayerList.self.xFine;
						if (local167 < 0) {
							local167 = -local167;
						}
						@Pc(180) int local180 = local167 + local151 - 128;
						if (local125 < local180) {
							Static164.anIntArray362[local5] = -100;
							continue;
						}
						if (local180 < 0) {
							local180 = 0;
						}
						local209 = Preferences.ambientSoundsVolume * (local125 - local180) / local125;
					}
					if (local209 > 0) {
						@Pc(223) PcmSound local223 = local79.toPcmSound().resample(client.pcmResampler);
						@Pc(228) SoundPcmStream local228 = Static284.method404(local223, local209);
						local228.setLoops(Static276.anIntArray563[local5] - 1);
						client.soundStream.addSubStream(local228);
					}
					Static164.anIntArray362[local5] = -100;
				}
			} else {
				SoundPlayer.size--;
				for (@Pc(28) int local28 = local5; local28 < SoundPlayer.size; local28++) {
					Static200.anIntArray421[local28] = Static200.anIntArray421[local28 + 1];
					Static173.aClass138Array1[local28] = Static173.aClass138Array1[local28 + 1];
					Static276.anIntArray563[local28] = Static276.anIntArray563[local28 + 1];
					Static164.anIntArray362[local28] = Static164.anIntArray362[local28 + 1];
					Static26.anIntArray68[local28] = Static26.anIntArray68[local28 + 1];
				}
				local5--;
			}
		}
		if (Static144.jingle && !Static136.method2655()) {
			if (Static12.anInt391 != 0 && BZip2State.anInt4363 != -1) {
				Static122.method2410(client.js5Archive6, BZip2State.anInt4363, Static12.anInt391);
			}
			Static144.jingle = false;
		} else if (Static12.anInt391 != 0 && BZip2State.anInt4363 != -1 && !Static136.method2655()) {
			Protocol.outboundBuffer.pIsaac1(137);
			Protocol.outboundBuffer.p4(BZip2State.anInt4363);
			BZip2State.anInt4363 = -1;
		}
	}
}
