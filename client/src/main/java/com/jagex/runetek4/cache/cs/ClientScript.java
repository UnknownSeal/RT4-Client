package com.jagex.runetek4.cache.cs;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.VarbitDefinition;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.CachedNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!qc")
public final class ClientScript extends CachedNode {

	@OriginalMember(owner = "runetek4.client!qc", name = "I", descriptor = "I")
	public int intArgs;

	@OriginalMember(owner = "runetek4.client!qc", name = "L", descriptor = "I")
	public int localIntCount;

	@OriginalMember(owner = "runetek4.client!qc", name = "N", descriptor = "I")
	public int stringArgs;

	@OriginalMember(owner = "runetek4.client!qc", name = "O", descriptor = "[I")
	public int[] opcodes;

	@OriginalMember(owner = "runetek4.client!qc", name = "Q", descriptor = "[Lclient!sc;")
	public HashTable[] switchTables;

	@OriginalMember(owner = "runetek4.client!qc", name = "R", descriptor = "Lclient!na;")
	public JString name;

	@OriginalMember(owner = "runetek4.client!qc", name = "S", descriptor = "I")
	public int localStringCount;

	@OriginalMember(owner = "runetek4.client!qc", name = "T", descriptor = "[Lclient!na;")
	public JString[] stringOperands;

	@OriginalMember(owner = "runetek4.client!qc", name = "W", descriptor = "[I")
	public int[] intOperands;

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BILclient!be;)I")
	public static int executeClientscript(@OriginalArg(1) int scriptIndex, @OriginalArg(2) Component component) {
		if (component.scripts == null || scriptIndex >= component.scripts.length) {
			return -2;
		}
		try {
			@Pc(33) int[] script = component.scripts[scriptIndex];
			@Pc(35) byte local35 = 0;
			@Pc(37) int register = 0;
			@Pc(39) int pc = 0;
			while (true) {
				@Pc(41) int register1 = 0;
				@Pc(46) int opcode = script[pc++];
				@Pc(48) byte nextOperator = 0;
				if (opcode == 0) {
					return register;
				}
				if (opcode == 15) {
					nextOperator = 1;
				}
				if (opcode == 16) {
					nextOperator = 2;
				}
				if (opcode == 1) { // load_skill_level {skill}
					register1 = PlayerSkillXpTable.skillLevel[script[pc++]];
				}
				if (opcode == 17) {
					nextOperator = 3;
				}
				if (opcode == 2) { // load_skill_base_level {skill}
					register1 = PlayerSkillXpTable.skillBaseLevel[script[pc++]];
				}
				if (opcode == 3) { // load_skill_exp {skill}
					register1 = PlayerSkillXpTable.skillExperience[script[pc++]];
				}
				@Pc(124) int i;
				@Pc(135) Component com;
				@Pc(140) int local140;
				@Pc(152) int j;
				if (opcode == 4) { // load_inv_count {interface id} {obj id}
					i = script[pc++] << 16;
					@Pc(131) int local131 = i + script[pc++];
					com = InterfaceList.getComponent(local131);
					local140 = script[pc++];
					if (local140 != -1 && (!Static71.get(local140).members || Static2.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (local140 + 1 == com.invSlotObjId[j]) {
								register1 += com.invSlotObjCount[j];
							}
						}
					}
				}
				if (opcode == 5) {  // load_var {id}
					register1 = VarPlayerDefinition.varPlayers[script[pc++]];
				}
				if (opcode == 6) {  // load_next_level_xp {skill}
					register1 = PlayerSkillXpTable.levelExperience[PlayerSkillXpTable.skillBaseLevel[script[pc++]] - 1];
				}
				if (opcode == 7) {
					register1 = VarPlayerDefinition.varPlayers[script[pc++]] * 100 / 46875;
				}
				if (opcode == 8) { // load_combat_level
					register1 = PlayerList.self.combatLevel;
				}
				if (opcode == 9) { // load_total_level
					for (i = 0; i < 25; i++) {
						if (Static182.aBooleanArray97[i]) {
							register1 += PlayerSkillXpTable.skillBaseLevel[i];
						}
					}
				}
				if (opcode == 10) { // load_inv_contains {interface id} {obj id}
					i = script[pc++] << 16;
					i += script[pc++];
					com = InterfaceList.getComponent(i);
					local140 = script[pc++];
					if (local140 != -1 && (!Static71.get(local140).members || Static2.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (com.invSlotObjId[j] == local140 + 1) {
								register1 = 999999999;
								break;
							}
						}
					}
				}
				if (opcode == 11) { // load_energy
					register1 = ClientScriptRunner.energy;
				}
				if (opcode == 12) { // load_weight
					register1 = Static251.weightCarried;
				}
				if (opcode == 13) { // load_bool {varp} {bit: 0..31}
					i = VarPlayerDefinition.varPlayers[script[pc++]];
					@Pc(353) int leastSignificantBit = script[pc++];
					register1 = (0x1 << leastSignificantBit & i) == 0 ? 0 : 1;
				}
				if (opcode == 14) {
					i = script[pc++];
					register1 = VarbitDefinition.getVarbitValue(i);
				}
				if (opcode == 18) {
					register1 = (PlayerList.self.xFine >> 7) + Camera.originX;
				}
				if (opcode == 19) {
					register1 = (PlayerList.self.zFine >> 7) + Camera.originZ;
				}
				if (opcode == 20) {
					register1 = script[pc++];
				}
				if (nextOperator == 0) {
					if (local35 == 0) {
						register += register1;
					}
					if (local35 == 1) {
						register -= register1;
					}
					if (local35 == 2 && register1 != 0) {
						register /= register1;
					}
					if (local35 == 3) {
						register *= register1;
					}
					local35 = 0;
				} else {
					local35 = nextOperator;
				}
			}
		} catch (@Pc(464) Exception local464) {
			return -1;
		}
	}
}
