package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.LocMergeEntity;
import com.jagex.runetek4.cache.def.ActorDefinition;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static54 {

	@OriginalMember(owner = "runetek4.client!ed", name = "D", descriptor = "Lclient!na;")
	public static final JString DETAILS = Static28.parse("details");

	@OriginalMember(owner = "runetek4.client!ed", name = "H", descriptor = "Lclient!na;")
	public static final JString aClass100_375 = Static28.parse("<)4col> x");

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(III)V")
	public static void method1304(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		if (Component.load(arg1)) {
			Static2.method7(Component.cachedComponents[arg1], arg0);
		}
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(IBIILclient!be;)V")
	public static void method1305(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Component arg3) {
		Static107.method2261();
		if (GlRenderer.enabled) {
			Static46.method1187(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		} else {
			Rasterizer.setBounds(arg2, arg1, arg2 + arg3.anInt445, arg1 + arg3.anInt459);
		}
		if (Static270.anInt5795 != 2 && Static270.anInt5795 != 5 && Static89.aClass3_Sub2_Sub1_5 != null) {
			@Pc(48) int angle = Static59.minimapAnticheatAngle + Static57.orbitCameraYaw & 0x7FF;
			@Pc(57) int anchorX = Static173.localPlayer.x / 32 + 48;
			@Pc(67) int anchorY = 464 - Static173.localPlayer.z / 32;
			if (GlRenderer.enabled) {
				((GlSprite) Static89.aClass3_Sub2_Sub1_5).method1427(arg2, arg1, arg3.anInt445, arg3.anInt459, anchorX, anchorY, angle, Static273.minimapZoom + 256, (GlSprite) arg3.method489(false));
			} else {
				((ImageRGB) Static89.aClass3_Sub2_Sub1_5).method310(arg2, arg1, arg3.anInt445, arg3.anInt459, anchorX, anchorY, angle, Static273.minimapZoom + 256, arg3.anIntArray37, arg3.anIntArray45);
			}
			@Pc(146) int local146;
			@Pc(181) int local181;
			@Pc(150) int local150;
			@Pc(154) int local154;
			@Pc(231) int local231;
			@Pc(200) int local200;
			@Pc(239) int local239;
			@Pc(271) int local271;
			if (Static235.aMapElementTypeList_2 != null) {
				for (@Pc(117) int local117 = 0; local117 < Static235.aMapElementTypeList_2.anInt5074; local117++) {
					if (Static235.aMapElementTypeList_2.method3892(local117)) {
						local146 = (Static235.aMapElementTypeList_2.aShortArray73[local117] - Static225.originX) * 4 + 2 - Static173.localPlayer.x / 32;
						local150 = MathUtils.anIntArray223[angle];
						local154 = MathUtils.anIntArray225[angle];
						@Pc(156) Font local156 = Static114.aClass3_Sub2_Sub9_42;
						@Pc(164) int local164 = local150 * 256 / (Static273.minimapZoom + 256);
						local181 = (Static235.aMapElementTypeList_2.aShortArray72[local117] - Static142.originZ) * 4 + 2 - Static173.localPlayer.z / 32;
						@Pc(189) int local189 = local154 * 256 / (Static273.minimapZoom + 256);
						local200 = local181 * local189 - local146 * local164 >> 16;
						if (Static235.aMapElementTypeList_2.method3894(local117) == 1) {
							local156 = Static215.aClass3_Sub2_Sub9_32;
						}
						if (Static235.aMapElementTypeList_2.method3894(local117) == 2) {
							local156 = Static280.aClass3_Sub2_Sub9_43;
						}
						local231 = local164 * local181 + local189 * local146 >> 16;
						local239 = local156.method2856(Static235.aMapElementTypeList_2.aClass100Array153[local117], 100);
						@Pc(245) int local245 = local231 - local239 / 2;
						if (local245 >= -arg3.anInt445 && local245 <= arg3.anInt445 && local200 >= -arg3.anInt459 && local200 <= arg3.anInt459) {
							local271 = 16777215;
							if (Static235.aMapElementTypeList_2.anIntArray444[local117] != -1) {
								local271 = Static235.aMapElementTypeList_2.anIntArray444[local117];
							}
							if (GlRenderer.enabled) {
								Static46.method1188((GlSprite) arg3.method489(false));
							} else {
								Rasterizer.method2486(arg3.anIntArray37, arg3.anIntArray45);
							}
							local156.method2869(Static235.aMapElementTypeList_2.aClass100Array153[local117], arg2 + local245 + arg3.anInt445 / 2, arg1 + arg3.anInt459 / 2 + -local200, local239, 50, local271, 0, 1, 0, 0);
							if (GlRenderer.enabled) {
								Static46.method1173();
							} else {
								Rasterizer.method2482();
							}
						}
					}
				}
			}
			for (local146 = 0; local146 < Static251.anInt5454; local146++) {
				local181 = Static145.anIntArray331[local146] * 4 + 2 - Static173.localPlayer.x / 32;
				local150 = Static93.anIntArray219[local146] * 4 + 2 - Static173.localPlayer.z / 32;
				@Pc(382) LocMergeEntity local382 = Static271.get(Static199.anIntArray417[local146]);
				if (local382.multiloc != null) {
					local382 = local382.getVisible();
					if (local382 == null || local382.mapfunction == -1) {
						continue;
					}
				}
				Static60.method1446(arg3, Static67.aClass3_Sub2_Sub1Array4[local382.mapfunction], local150, local181, arg1, arg2);
			}
			for (local146 = 0; local146 < 104; local146++) {
				for (local181 = 0; local181 < 104; local181++) {
					@Pc(439) LinkList local439 = Static159.levelObjStacks[Static55.currentLevel][local146][local181];
					if (local439 != null) {
						local154 = local146 * 4 + 2 - Static173.localPlayer.x / 32;
						local231 = local181 * 4 + 2 - Static173.localPlayer.z / 32;
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[0], local231, local154, arg1, arg2);
					}
				}
			}
			for (local146 = 0; local146 < Static272.npcCount; local146++) {
				@Pc(498) NPCEntity local498 = Static175.npcs[Static33.npcIds[local146]];
				if (local498 != null && local498.isVisible()) {
					@Pc(507) ActorDefinition local507 = local498.type;
					if (local507 != null && local507.multinpc != null) {
						local507 = local507.getMultiNPC();
					}
					if (local507 != null && local507.minimap && local507.active) {
						local154 = local498.x / 32 - Static173.localPlayer.x / 32;
						local231 = local498.z / 32 - Static173.localPlayer.z / 32;
						if (local507.anInt3739 == -1) {
							Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[1], local231, local154, arg1, arg2);
						} else {
							Static60.method1446(arg3, Static67.aClass3_Sub2_Sub1Array4[local507.anInt3739], local231, local154, arg1, arg2);
						}
					}
				}
			}
			for (local146 = 0; local146 < Static267.playerCount; local146++) {
				@Pc(591) PlayerEntity local591 = Static159.players[Static105.playerIds[local146]];
				if (local591 != null && local591.isVisible()) {
					local154 = local591.z / 32 - Static173.localPlayer.z / 32;
					local150 = local591.x / 32 - Static173.localPlayer.x / 32;
					@Pc(624) long local624 = local591.name.toBase37();
					@Pc(626) boolean local626 = false;
					for (local239 = 0; local239 < CacheArchive.friendCount; local239++) {
						if (local624 == Static92.friendName37[local239] && Static104.friendWorld[local239] != 0) {
							local626 = true;
							break;
						}
					}
					@Pc(660) boolean local660 = false;
					for (local271 = 0; local271 < Static214.anInt5577; local271++) {
						if (local624 == Static199.aClass3_Sub22Array1[local271].nodeId) {
							local660 = true;
							break;
						}
					}
					@Pc(682) boolean local682 = false;
					if (Static173.localPlayer.anInt1650 != 0 && local591.anInt1650 != 0 && local591.anInt1650 == Static173.localPlayer.anInt1650) {
						local682 = true;
					}
					if (local626) {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[3], local154, local150, arg1, arg2);
					} else if (local660) {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[5], local154, local150, arg1, arg2);
					} else if (local682) {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[4], local154, local150, arg1, arg2);
					} else {
						Static60.method1446(arg3, Static139.aClass3_Sub2_Sub1Array6[2], local154, local150, arg1, arg2);
					}
				}
			}
			@Pc(756) Class102[] local756 = Static143.aClass102Array1;
			for (local181 = 0; local181 < local756.length; local181++) {
				@Pc(770) Class102 local770 = local756[local181];
				if (local770 != null && local770.anInt4058 != 0 && Static83.loopCycle % 20 < 10) {
					if (local770.anInt4058 == 1 && local770.anInt4057 >= 0 && local770.anInt4057 < Static175.npcs.length) {
						@Pc(804) NPCEntity local804 = Static175.npcs[local770.anInt4057];
						if (local804 != null) {
							local231 = local804.x / 32 - Static173.localPlayer.x / 32;
							local200 = local804.z / 32 - Static173.localPlayer.z / 32;
							Static97.method1960(local770.anInt4048, arg1, arg2, local231, local200, arg3);
						}
					}
					if (local770.anInt4058 == 2) {
						local154 = (local770.anInt4053 - Static225.originX) * 4 + 2 - Static173.localPlayer.x / 32;
						local231 = (-Static142.originZ + local770.anInt4046) * 4 + 2 - Static173.localPlayer.z / 32;
						Static97.method1960(local770.anInt4048, arg1, arg2, local154, local231, arg3);
					}
					if (local770.anInt4058 == 10 && local770.anInt4057 >= 0 && Static159.players.length > local770.anInt4057) {
						@Pc(905) PlayerEntity local905 = Static159.players[local770.anInt4057];
						if (local905 != null) {
							local200 = local905.z / 32 - Static173.localPlayer.z / 32;
							local231 = local905.x / 32 - Static173.localPlayer.x / 32;
							Static97.method1960(local770.anInt4048, arg1, arg2, local231, local200, arg3);
						}
					}
				}
			}
			if (Static115.anInt2939 != 0) {
				local146 = Static115.anInt2939 * 4 + 2 - Static173.localPlayer.x / 32;
				local181 = Static84.anInt2255 * 4 + 2 - Static173.localPlayer.z / 32;
				Static60.method1446(arg3, Static84.aClass3_Sub2_Sub1_4, local181, local146, arg1, arg2);
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
		Static31.aBooleanArray29[arg0] = true;
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(ZIIII)V")
	public static void method1306(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg3 >= Static172.anInt4164 && arg3 <= Static224.anInt5063) {
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
	public static void method1308() {
		Static83.aClass99_3.method3104();
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
		return Static61.aBoolean109 && arg0 >= Static19.anInt636 && arg0 <= Static171.anInt4157 ? Static196.aClass10_Sub1Array2[arg0 - Static19.anInt636] : null;
	}

	@OriginalMember(owner = "runetek4.client!ed", name = "d", descriptor = "(B)V")
	public static void loop() {
		for (@Pc(5) int local5 = 0; local5 < Static189.anInt4451; local5++) {
			@Pc(12) int local12 = Static164.anIntArray362[local5]--;
			if (Static164.anIntArray362[local5] >= -10) {
				@Pc(79) SynthSound local79 = Static173.aClass138Array1[local5];
				if (local79 == null) {
					local79 = Static292.method3988(Static248.aClass153_75, Static200.anIntArray421[local5], 0);
					if (local79 == null) {
						continue;
					}
					Static164.anIntArray362[local5] += local79.method3990();
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
						@Pc(151) int local151 = local133 * 128 + 64 - Static173.localPlayer.z;
						if (local151 < 0) {
							local151 = -local151;
						}
						@Pc(167) int local167 = local141 * 128 + 64 - Static173.localPlayer.x;
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
						local209 = Static30.anInt978 * (local125 - local180) / local125;
					}
					if (local209 > 0) {
						@Pc(223) PcmSound local223 = local79.method3989().method2648(Static56.aClass156_1);
						@Pc(228) SoundPcmStream local228 = Static284.method404(local223, local209);
						local228.method396(Static276.anIntArray563[local5] - 1);
						Static204.soundStream.method1343(local228);
					}
					Static164.anIntArray362[local5] = -100;
				}
			} else {
				Static189.anInt4451--;
				for (@Pc(28) int local28 = local5; local28 < Static189.anInt4451; local28++) {
					Static200.anIntArray421[local28] = Static200.anIntArray421[local28 + 1];
					Static173.aClass138Array1[local28] = Static173.aClass138Array1[local28 + 1];
					Static276.anIntArray563[local28] = Static276.anIntArray563[local28 + 1];
					Static164.anIntArray362[local28] = Static164.anIntArray362[local28 + 1];
					Static26.anIntArray68[local28] = Static26.anIntArray68[local28 + 1];
				}
				local5--;
			}
		}
		if (Static144.aBoolean173 && !Static136.method2655()) {
			if (Static12.anInt391 != 0 && BZip2State.anInt4363 != -1) {
				Static122.method2410(Static130.aClass153_47, BZip2State.anInt4363, Static12.anInt391);
			}
			Static144.aBoolean173 = false;
		} else if (Static12.anInt391 != 0 && BZip2State.anInt4363 != -1 && !Static136.method2655()) {
			Static6.outboundBuffer.pIsaac1(137);
			Static6.outboundBuffer.p4(BZip2State.anInt4363);
			BZip2State.anInt4363 = -1;
		}
	}
}
