package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.invtype.InvType;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static246 {

	@OriginalMember(owner = "runetek4.client!u", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_1029 = JString.parse("cookiehost");

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(BLclient!ve;Lclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		Static153.aClass153_57 = arg0;
		Static243.aClass153_97 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Lclient!me;IIII)V")
	public static void method4240(@OriginalArg(0) NpcType npc, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (MiniMenu.menuActionRow >= 400) {
			return;
		}
		if (npc.multiNpcs != null) {
			npc = npc.getMultiNPC();
		}
		if (npc == null || !npc.active) {
			return;
		}
		@Pc(35) JString tooltip = npc.name;
		if (npc.vislevel != 0) {
			@Pc(47) JString string = client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
			tooltip = JString.concatenate(new JString[] { tooltip, Static123.getCombatLevelColorTag(npc.vislevel, PlayerList.self.combatLevel), Static123.aClass100_593, string, JString.parseInt(npc.vislevel), Static72.aClass100_448 });
		}
		if (MiniMenu.anInt5014 == 1) {
			MiniMenu.addActionRow(Static169.anInt4075, (long) arg2, JString.concatenate(new JString[] { Static34.aClass100_203, Static201.aClass100_407, tooltip }), arg1, (short) 26, LocalizedText.USE, arg3);
		} else if (MiniMenu.aBoolean302) {
			@Pc(378) Class3_Sub2_Sub12 local378 = Static121.anInt3039 == -1 ? null : Static110.method2277(Static121.anInt3039);
			if ((Static274.anInt4999 & 0x2) != 0 && (local378 == null || npc.getParam(Static121.anInt3039, local378.anInt2667) != local378.anInt2667)) {
				MiniMenu.addActionRow(MiniMenu.anInt5393, (long) arg2, JString.concatenate(new JString[] { Static78.aClass100_466, Static201.aClass100_407, tooltip }), arg1, (short) 45, MiniMenu.aClass100_545, arg3);
			}
		} else {
			@Pc(129) JString[] spellSelected = npc.ops;
			if (Static208.aBoolean237) {
				spellSelected = Static279.method4664(spellSelected);
			}
			@Pc(140) int op;
			if (spellSelected != null) {
				for (op = 4; op >= 0; op--) {
					if (spellSelected[op] != null && (client.game != 0 || !spellSelected[op].equalsIgnoreCase(LocalizedText.ATTACK))) {
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
						MiniMenu.addActionRow(local176, (long) arg2, JString.concatenate(new JString[] { Static226.YELLOW2, tooltip }), arg1, local161, spellSelected[op], arg3);
					}
				}
			}
			if (client.game == 0 && spellSelected != null) {
				for (op = 4; op >= 0; op--) {
					if (spellSelected[op] != null && spellSelected[op].equalsIgnoreCase(LocalizedText.ATTACK)) {
						@Pc(271) short action = 0;
						if (npc.vislevel > PlayerList.self.combatLevel) {
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
						MiniMenu.addActionRow(npc.cursorattack, (long) arg2, JString.concatenate(new JString[] { Static226.YELLOW2, tooltip }), arg1, menuOption, spellSelected[op], arg3);
					}
				}
			}
			MiniMenu.addActionRow(Static225.anInt5073, (long) arg2, JString.concatenate(new JString[] { Static226.YELLOW2, tooltip }), arg1, (short) 1007, LocalizedText.EXAMINE, arg3);
		}
	}

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(II)Lclient!md;")
	public static InvType get(@OriginalArg(0) int arg0) {
		@Pc(16) InvType invType = (InvType) Static89.aClass54_8.get((long) arg0);
		if (invType != null) {
			return invType;
		}
		@Pc(27) byte[] bytes = CacheArchive.aClass153_2.getfile(5, arg0);
		invType = new InvType();
		if (bytes != null) {
			invType.decode(new Packet(bytes));
		}
		Static89.aClass54_8.put(invType, (long) arg0);
		return invType;
	}
}
