package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.Node;
import com.jagex.runetek4.config.Component;
import com.jagex.runetek4.config.ObjType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static273 {

	@OriginalMember(owner = "runetek4.client!we", name = "v", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_7;

	@OriginalMember(owner = "runetek4.client!we", name = "H", descriptor = "[[B")
	public static byte[][] aByteArrayArray13;

	@OriginalMember(owner = "runetek4.client!we", name = "w", descriptor = "I")
	public static int minimapZoom = 0;

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BI)I")
	public static int fade(@OriginalArg(1) int t) {
		@Pc(13) int depth = t * (t * t >> 12) >> 12;
		@Pc(26) int x = t * 6 - 61440;
		@Pc(34) int y = (t * x >> 12) + 40960;
		return depth * y >> 12;
	}

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BILclient!be;)I")
	public static int method3212(@OriginalArg(1) int arg0, @OriginalArg(2) Component arg1) {
		if (arg1.scripts == null || arg0 >= arg1.scripts.length) {
			return -2;
		}
		try {
			@Pc(33) int[] script = arg1.scripts[arg0];
			@Pc(35) byte local35 = 0;
			@Pc(37) int local37 = 0;
			@Pc(39) int pc = 0;
			while (true) {
				@Pc(41) int register = 0;
				@Pc(46) int opcode = script[pc++];
				@Pc(48) byte local48 = 0;
				if (opcode == 0) {
					return local37;
				}
				if (opcode == 15) {
					local48 = 1;
				}
				if (opcode == 16) {
					local48 = 2;
				}
				if (opcode == 1) { // load_skill_level {skill}
					register = Static99.skillLevel[script[pc++]];
				}
				if (opcode == 17) {
					local48 = 3;
				}
				if (opcode == 2) { // load_skill_base_level {skill}
					register = Static141.skillBaseLevel[script[pc++]];
				}
				if (opcode == 3) { // load_skill_exp {skill}
					register = Static227.skillExperience[script[pc++]];
				}
				@Pc(124) int i;
				@Pc(135) Component com;
				@Pc(140) int local140;
				@Pc(152) int j;
				if (opcode == 4) { // load_inv_count {interface id} {obj id}
					i = script[pc++] << 16;
					@Pc(131) int local131 = i + script[pc++];
					com = Static5.getComponent(local131);
					local140 = script[pc++];
					if (local140 != -1 && (!Static71.get(local140).members || Static2.aBoolean1)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (local140 + 1 == com.invSlotObjId[j]) {
								register += com.invSlotObjCount[j];
							}
						}
					}
				}
				if (opcode == 5) {  // load_var {id}
					register = Static7.varps[script[pc++]];
				}
				if (opcode == 6) {  // load_next_level_xp {skill}
					register = ObjType.levelExperience[Static141.skillBaseLevel[script[pc++]] - 1];
				}
				if (opcode == 7) {
					register = Static7.varps[script[pc++]] * 100 / 46875;
				}
				if (opcode == 8) { // load_combat_level
					register = Static173.localPlayer.combatLevel;
				}
				if (opcode == 9) { // load_total_level
					for (i = 0; i < 25; i++) {
						if (Static182.aBooleanArray97[i]) {
							register += Static141.skillBaseLevel[i];
						}
					}
				}
				if (opcode == 10) { // load_inv_contains {interface id} {obj id}
					i = script[pc++] << 16;
					i += script[pc++];
					com = Static5.getComponent(i);
					local140 = script[pc++];
					if (local140 != -1 && (!Static71.get(local140).members || Static2.aBoolean1)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (com.invSlotObjId[j] == local140 + 1) {
								register = 999999999;
								break;
							}
						}
					}
				}
				if (opcode == 11) { // load_energy
					register = Static12.energy;
				}
				if (opcode == 12) { // load_weight
					register = Static251.weightCarried;
				}
				if (opcode == 13) { // load_bool {varp} {bit: 0..31}
					i = Static7.varps[script[pc++]];
					@Pc(353) int lsb = script[pc++];
					register = (0x1 << lsb & i) == 0 ? 0 : 1;
				}
				if (opcode == 14) {
					i = script[pc++];
					register = Static155.method2945(i);
				}
				if (opcode == 18) {
					register = (Static173.localPlayer.x >> 7) + Static225.originX;
				}
				if (opcode == 19) {
					register = (Static173.localPlayer.z >> 7) + Static142.originZ;
				}
				if (opcode == 20) {
					register = script[pc++];
				}
				if (local48 == 0) {
					if (local35 == 0) {
						local37 += register;
					}
					if (local35 == 1) {
						local37 -= register;
					}
					if (local35 == 2 && register != 0) {
						local37 /= register;
					}
					if (local35 == 3) {
						local37 *= register;
					}
					local35 = 0;
				} else {
					local35 = local48;
				}
			}
		} catch (@Pc(464) Exception local464) {
			return -1;
		}
	}

	@OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(II)Z")
	public static boolean method3213(@OriginalArg(1) int arg0) {
		if (arg0 >= 32 && arg0 <= 126) {
			return true;
		} else if (arg0 >= 160 && arg0 <= 255) {
			return true;
		} else {
			return arg0 == 128 || arg0 == 140 || arg0 == 151 || arg0 == 156 || arg0 == 159;
		}
	}

	@OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(BI)V")
	public static void method3214(@OriginalArg(1) int arg0) {
		for (@Pc(11) Node local11 = Static210.aClass133_21.peekFront(); local11 != null; local11 = Static210.aClass133_21.prev()) {
			if ((local11.nodeId >> 48 & 0xFFFFL) == (long) arg0) {
				local11.unlink();
			}
		}
	}
}
