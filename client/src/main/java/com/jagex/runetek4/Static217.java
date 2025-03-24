package com.jagex.runetek4;

import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static217 {

	@OriginalMember(owner = "runetek4.client!rj", name = "R", descriptor = "I")
	public static int eyeZ;

	@OriginalMember(owner = "runetek4.client!rj", name = "P", descriptor = "I")
	public static int anInt4901 = -1;

	@OriginalMember(owner = "runetek4.client!rj", name = "ab", descriptor = "Lclient!na;")
	public static final JString CLANREQ = JString.parse(":clanreq:");

	@OriginalMember(owner = "runetek4.client!rj", name = "a", descriptor = "(IIILclient!e;I)V")
	public static void method3767(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Player arg2, @OriginalArg(4) int arg3) {
		if (PlayerList.self == arg2 || MiniMenu.menuActionRow >= 400) {
			return;
		}
		@Pc(158) JString local158;
		if (arg2.anInt1671 == 0) {
			@Pc(22) boolean local22 = true;
			if (PlayerList.self.anInt1667 != -1 && arg2.anInt1667 != -1) {
				@Pc(43) int local43 = arg2.combatLevel < PlayerList.self.combatLevel ? PlayerList.self.combatLevel : arg2.combatLevel;
				@Pc(58) int local58 = PlayerList.self.anInt1667 < arg2.anInt1667 ? PlayerList.self.anInt1667 : arg2.anInt1667;
				@Pc(69) int local69 = local43 * 10 / 100 + local58 + 5;
				@Pc(76) int local76 = PlayerList.self.combatLevel - arg2.combatLevel;
				if (local76 < 0) {
					local76 = -local76;
				}
				if (local69 < local76) {
					local22 = false;
				}
			}
			@Pc(95) JString local95 = client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
			if (arg2.combatLevel < arg2.anInt1656) {
				local158 = JString.concatenate(new JString[] { arg2.getUsername(), local22 ? Static123.getCombatLevelColorTag(arg2.combatLevel, PlayerList.self.combatLevel) : Static204.aClass100_896, Static123.aClass100_593, local95, JString.parseInt(arg2.combatLevel), Static78.aClass100_465, JString.parseInt(arg2.anInt1656 - arg2.combatLevel), Static72.aClass100_448 });
			} else {
				local158 = JString.concatenate(new JString[] { arg2.getUsername(), local22 ? Static123.getCombatLevelColorTag(arg2.combatLevel, PlayerList.self.combatLevel) : Static204.aClass100_896, Static123.aClass100_593, local95, JString.parseInt(arg2.combatLevel), Static72.aClass100_448 });
			}
		} else {
			local158 = JString.concatenate(new JString[] { arg2.getUsername(), Static123.aClass100_593, LocalizedText.SKILL, JString.parseInt(arg2.anInt1671), Static72.aClass100_448 });
		}
		@Pc(275) int local275;
		if (MiniMenu.anInt5014 == 1) {
			MiniMenu.addActionRow(MiniMap.anInt4075, (long) arg0, JString.concatenate(new JString[] { Static34.aClass100_203, Static105.aClass100_561, local158 }), arg3, (short) 1, LocalizedText.USE, arg1);
		} else if (!MiniMenu.aBoolean302) {
			for (local275 = 7; local275 >= 0; local275--) {
				if (Player.options[local275] != null) {
					@Pc(291) short local291 = 0;
					if (client.game == 0 && Player.options[local275].equalsIgnoreCase(LocalizedText.ATTACK)) {
						if (arg2.combatLevel > PlayerList.self.combatLevel) {
							local291 = 2000;
						}
						if (PlayerList.self.teamId != 0 && arg2.teamId != 0) {
							if (PlayerList.self.teamId == arg2.teamId) {
								local291 = 2000;
							} else {
								local291 = 0;
							}
						}
					} else if (Player.secondaryOptions[local275]) {
						local291 = 2000;
					}
					@Pc(353) short local353 = Static5.aShortArray2[local275];
					@Pc(358) short local358 = (short) (local353 + local291);
					MiniMenu.addActionRow(Player.cursors[local275], (long) arg0, JString.concatenate(new JString[] { Static204.aClass100_896, local158 }), arg3, local358, Player.options[local275], arg1);
				}
			}
		} else if ((Static274.anInt4999 & 0x8) != 0) {
			MiniMenu.addActionRow(MiniMenu.anInt5393, (long) arg0, JString.concatenate(new JString[] { Static78.aClass100_466, Static105.aClass100_561, local158 }), arg3, (short) 15, MiniMenu.aClass100_545, arg1);
		}
		for (local275 = 0; local275 < MiniMenu.menuActionRow; local275++) {
			if (MiniMenu.actions[local275] == 60) {
				MiniMenu.opBases[local275] = JString.concatenate(new JString[] { Static204.aClass100_896, local158 });
				break;
			}
		}
	}

}
