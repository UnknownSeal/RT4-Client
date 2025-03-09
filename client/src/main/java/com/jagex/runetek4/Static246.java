package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.entity.SpotAnimEntity;
import com.jagex.runetek4.game.config.invtype.InvType;
import com.jagex.runetek4.config.NPCType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static246 {

	@OriginalMember(owner = "runetek4.client!u", name = "i", descriptor = "I")
	public static int anInt5393;

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "I")
	public static int anInt5388 = 0;

	@OriginalMember(owner = "runetek4.client!u", name = "g", descriptor = "Lclient!na;")
	public static final JagString aClass100_1029 = Static28.parse("cookiehost");

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(BLclient!ve;Lclient!ve;)V")
	public static void method4237(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		Static153.aClass153_57 = arg0;
		Static243.aClass153_97 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Z)V")
	public static void pushSpotanims() {
		for (@Pc(9) SpotAnimEntity entity = (SpotAnimEntity) Static99.spotanims.head(); entity != null; entity = (SpotAnimEntity) Static99.spotanims.next()) {
			@Pc(15) SpotAnim spotAnim = entity.aClass8_Sub2_1;
			if (spotAnim.level != Static55.currentLevel || spotAnim.seqComplete) {
				entity.unlink();
			} else if (spotAnim.startCycle <= Static83.loopCycle) {
				spotAnim.update(Static178.sceneDelta);
				if (spotAnim.seqComplete) {
					entity.unlink();
				} else {
					Static43.addTemporary(spotAnim.level, spotAnim.x, spotAnim.z, spotAnim.anInt599, 60, spotAnim, 0, -1L, false);
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Lclient!me;IIII)V")
	public static void method4240(@OriginalArg(0) NPCType npc, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (PreciseSleep.anInt5204 >= 400) {
			return;
		}
		if (npc.multinpc != null) {
			npc = npc.getMultiNPC();
		}
		if (npc == null || !npc.active) {
			return;
		}
		@Pc(35) JagString tooltip = npc.name;
		if (npc.vislevel != 0) {
			@Pc(47) JagString string = Static266.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
			tooltip = Static34.method882(new JagString[] { tooltip, Static123.getCombatLevelColorTag(npc.vislevel, Static173.localPlayer.combatLevel), Static123.aClass100_593, string, Static123.method2423(npc.vislevel), Static72.aClass100_448 });
		}
		if (Static260.anInt5014 == 1) {
			Static98.method1966(Static169.anInt4075, (long) arg2, Static34.method882(new JagString[] { Static34.aClass100_203, Static201.aClass100_407, tooltip }), arg1, (short) 26, LocalizedText.USE, arg3);
		} else if (Static241.aBoolean302) {
			@Pc(378) Class3_Sub2_Sub12 local378 = Static121.anInt3039 == -1 ? null : Static110.method2277(Static121.anInt3039);
			if ((Static274.anInt4999 & 0x2) != 0 && (local378 == null || npc.getParam(Static121.anInt3039, local378.anInt2667) != local378.anInt2667)) {
				Static98.method1966(anInt5393, (long) arg2, Static34.method882(new JagString[] { Static78.aClass100_466, Static201.aClass100_407, tooltip }), arg1, (short) 45, Static102.aClass100_545, arg3);
			}
		} else {
			@Pc(129) JagString[] spellSelected = npc.ops;
			if (Static208.aBoolean237) {
				spellSelected = Static279.method4664(spellSelected);
			}
			@Pc(140) int op;
			if (spellSelected != null) {
				for (op = 4; op >= 0; op--) {
					if (spellSelected[op] != null && (Static266.game != 0 || !spellSelected[op].equalsIgnoreCase(LocalizedText.ATTACK))) {
						@Pc(161) byte local161 = 0;
						if (op == 0) {
							local161 = 17;
						}
						if (op == 1) {
							local161 = 16;
						}
						@Pc(176) int local176 = -1;
						if (op == 2) {
							local161 = 4;
						}
						if (op == 3) {
							local161 = 19;
						}
						if (npc.anInt3750 == op) {
							local176 = npc.anInt3719;
						}
						if (op == npc.anInt3743) {
							local176 = npc.anInt3735;
						}
						if (op == 4) {
							local161 = 2;
						}
						Static98.method1966(local176, (long) arg2, Static34.method882(new JagString[] { Static226.YELLOW2, tooltip }), arg1, local161, spellSelected[op], arg3);
					}
				}
			}
			if (Static266.game == 0 && spellSelected != null) {
				for (op = 4; op >= 0; op--) {
					if (spellSelected[op] != null && spellSelected[op].equalsIgnoreCase(LocalizedText.ATTACK)) {
						@Pc(271) short action = 0;
						if (npc.vislevel > Static173.localPlayer.combatLevel) {
							action = 2000;
						}
						@Pc(281) short menuOption = 0;
						if (op == 0) {
							menuOption = 17;
						}
						if (op == 1) {
							menuOption = 16;
						}
						if (op == 2) {
							menuOption = 4;
						}
						if (op == 3) {
							menuOption = 19;
						}
						if (op == 4) {
							menuOption = 2;
						}
						if (menuOption != 0) {
							menuOption += action;
						}
						Static98.method1966(npc.cursorattack, (long) arg2, Static34.method882(new JagString[] { Static226.YELLOW2, tooltip }), arg1, menuOption, spellSelected[op], arg3);
					}
				}
			}
			Static98.method1966(Static225.anInt5073, (long) arg2, Static34.method882(new JagString[] { Static226.YELLOW2, tooltip }), arg1, (short) 1007, LocalizedText.EXAMINE, arg3);
		}
	}

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(II)Lclient!md;")
	public static InvType get(@OriginalArg(0) int arg0) {
		@Pc(16) InvType invType = (InvType) Static89.aClass54_8.get((long) arg0);
		if (invType != null) {
			return invType;
		}
		@Pc(27) byte[] bytes = Static9.aClass153_2.getfile(5, arg0);
		invType = new InvType();
		if (bytes != null) {
			invType.decode(new Packet(bytes));
		}
		Static89.aClass54_8.put(invType, (long) arg0);
		return invType;
	}
}
