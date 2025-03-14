package com.jagex.runetek4;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.cs.ClientScript;
import com.jagex.runetek4.cache.def.ActorDefinition;
import com.jagex.runetek4.cache.def.VarbitDefinition;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.game.client.ClientInvCache;
import com.jagex.runetek4.game.config.enumtype.EnumType;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.cache.def.ItemDefinition;
import com.jagex.runetek4.game.config.quickchatphrasetype.QuickChatPhraseType;
import com.jagex.runetek4.game.shared.framework.gwc.GWCLocation;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static88 {

	@OriginalMember(owner = "runetek4.client!h", name = "a", descriptor = "(Lclient!ve;Lclient!ve;Z)V")
	public static void method1817(@OriginalArg(0) com.jagex.runetek4.js5.CacheArchive arg0, @OriginalArg(1) com.jagex.runetek4.js5.CacheArchive arg1) {
		Static86.aClass153_37 = arg0;
		Static58.aClass153_28 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!h", name = "a", descriptor = "(BILclient!jl;)V")
	public static void runClientScripts(@OriginalArg(1) int arg0, @OriginalArg(2) HookRequest arg1) {
		@Pc(4) Object[] listeners = arg1.anObjectArray31;
		@Pc(10) int local10 = (Integer) listeners[0];
		@Pc(14) ClientScript clientScript = ClientScript.decodeClientScript(local10);
		if (clientScript == null) {
			return;
		}
		Static138.invokedScriptIndex = 0;
		@Pc(26) int local26 = 0;
		@Pc(28) int intValueIndex = 0;
		@Pc(30) int scriptIndex = -1;
		@Pc(33) int[] local33 = clientScript.intOperands;
		@Pc(36) int[] scriptOpcodes = clientScript.opcodes;
		@Pc(44) byte local44 = -1;
		@Pc(58) int listenersIndex;
		try {
			Static215.localInts = new int[clientScript.localIntCount];
			@Pc(50) int localIntIndex = 0;
			Static180.localStrings = new JString[clientScript.localStringCount];
			@Pc(56) int localStringIndex = 0;
			@Pc(77) int j;
			@Pc(194) JString string;
			for (listenersIndex = 1; listenersIndex < listeners.length; listenersIndex++) {
				if (listeners[listenersIndex] instanceof Integer) {
					j = (Integer) listeners[listenersIndex];
					if (j == -2147483647) { // 0
						j = arg1.anInt3102;
					}
					if (j == -2147483646) { // 1
						j = arg1.anInt3097;
					}
					if (j == -2147483645) { // 2
						j = arg1.source == null ? -1 : arg1.source.anInt507;
					}
					if (j == -2147483644) { // 3
						j = arg1.anInt3101;
					}
					if (j == -2147483643) {
						j = arg1.source == null ? -1 : arg1.source.componentId;
					}
					if (j == -2147483642) {
						j = arg1.aClass13_16 == null ? -1 : arg1.aClass13_16.anInt507;
					}
					if (j == -2147483641) {
						j = arg1.aClass13_16 == null ? -1 : arg1.aClass13_16.componentId;
					}
					if (j == -2147483640) {
						j = arg1.anInt3100;
					}
					if (j == -2147483639) {
						j = arg1.anInt3099;
					}
					Static215.localInts[localIntIndex++] = j;
				} else if (listeners[listenersIndex] instanceof JString) {
					string = (JString) listeners[listenersIndex];
					if (string.method3108(Static15.aClass100_83)) {
						string = arg1.aClass100_598;
					}
					Static180.localStrings[localStringIndex++] = string;
				}
			}
			listenersIndex = 0;
			label4266: while (true) {
				listenersIndex++;
				if (arg0 < listenersIndex) {
					throw new RuntimeException("slow");
				}
				scriptIndex++;
				@Pc(226) int scriptOpcode = scriptOpcodes[scriptIndex];
				@Pc(803) int interfaceType;
				@Pc(652) int local652;
				@Pc(809) int interfaceData;
				@Pc(609) JString chatTyped;
				if (scriptOpcode < 100) {
					// core language ops (not commands)

					if (scriptOpcode == 0) {
						// push_constant_int
						Static254.scriptIntValues[intValueIndex++] = local33[scriptIndex];
						continue;
					}
					if (scriptOpcode == 1) {
						// push_varp
						j = local33[scriptIndex];
						Static254.scriptIntValues[intValueIndex++] = VarPlayerDefinition.varPlayers[j];
						continue;
					}
					if (scriptOpcode == 2) {
						// pop_varp
						j = local33[scriptIndex];
						intValueIndex--;
						Static148.method2766(j, Static254.scriptIntValues[intValueIndex]);
						continue;
					}
					if (scriptOpcode == 3) {
						// push_constant_string
						Static3.scriptStringValues[local26++] = clientScript.stringOperands[scriptIndex];
						continue;
					}
					if (scriptOpcode == 6) {
						// branch
						scriptIndex += local33[scriptIndex];
						continue;
					}
					if (scriptOpcode == 7) {
						// branch_not
						intValueIndex -= 2;
						if (Static254.scriptIntValues[intValueIndex] != Static254.scriptIntValues[intValueIndex + 1]) {
							scriptIndex += local33[scriptIndex];
						}
						continue;
					}
					if (scriptOpcode == 8) {
						// branch_equal
						intValueIndex -= 2;
						if (Static254.scriptIntValues[intValueIndex + 1] == Static254.scriptIntValues[intValueIndex]) {
							scriptIndex += local33[scriptIndex];
						}
						continue;
					}
					if (scriptOpcode == 9) {
						// branch_equals
						intValueIndex -= 2;
						if (Static254.scriptIntValues[intValueIndex] < Static254.scriptIntValues[intValueIndex + 1]) {
							scriptIndex += local33[scriptIndex];
						}
						continue;
					}
					if (scriptOpcode == 10) {
						// branch_greater_than
						intValueIndex -= 2;
						if (Static254.scriptIntValues[intValueIndex + 1] < Static254.scriptIntValues[intValueIndex]) {
							scriptIndex += local33[scriptIndex];
						}
						continue;
					}
					if (scriptOpcode == 21) {
						// return
						if (Static138.invokedScriptIndex == 0) {
							return;
						}
						@Pc(423) InvokedScript invokedScript = Static67.invokedScripts[--Static138.invokedScriptIndex];
						clientScript = invokedScript.script;
						Static215.localInts = invokedScript.localInts;
						scriptOpcodes = clientScript.opcodes;
						scriptIndex = invokedScript.anInt2515;
						Static180.localStrings = invokedScript.aClass100Array79;
						local33 = clientScript.intOperands;
						continue;
					}
					if (scriptOpcode == 25) {
						// push_varbit
						j = local33[scriptIndex];
						Static254.scriptIntValues[intValueIndex++] = VarbitDefinition.getVarbitValue(j);
						continue;
					}
					if (scriptOpcode == 27) {
						// pop_varbit
						j = local33[scriptIndex];
						intValueIndex--;
						Class6.method3655(j, Static254.scriptIntValues[intValueIndex]);
						continue;
					}
					if (scriptOpcode == 31) {
						// branch_less_than_or_equals
						intValueIndex -= 2;
						if (Static254.scriptIntValues[intValueIndex + 1] >= Static254.scriptIntValues[intValueIndex]) {
							scriptIndex += local33[scriptIndex];
						}
						continue;
					}
					if (scriptOpcode == 32) {
						// branch_greater_than_or_equals
						intValueIndex -= 2;
						if (Static254.scriptIntValues[intValueIndex] >= Static254.scriptIntValues[intValueIndex + 1]) {
							scriptIndex += local33[scriptIndex];
						}
						continue;
					}
					if (scriptOpcode == 33) {
						// push_int_local
						Static254.scriptIntValues[intValueIndex++] = Static215.localInts[local33[scriptIndex]];
						continue;
					}
					@Pc(555) int local555;
					if (scriptOpcode == 34) {
						// pop_int_local
						local555 = local33[scriptIndex];
						intValueIndex--;
						Static215.localInts[local555] = Static254.scriptIntValues[intValueIndex];
						continue;
					}
					if (scriptOpcode == 35) {
						// push_string_local
						Static3.scriptStringValues[local26++] = Static180.localStrings[local33[scriptIndex]];
						continue;
					}
					if (scriptOpcode == 36) {
						// pop_string_local
						local555 = local33[scriptIndex];
						local26--;
						Static180.localStrings[local555] = Static3.scriptStringValues[local26];
						continue;
					}
					if (scriptOpcode == 37) {
						// join_string
						j = local33[scriptIndex];
						local26 -= j;
						chatTyped = Static118.method2355(local26, j, Static3.scriptStringValues);
						Static3.scriptStringValues[local26++] = chatTyped;
						continue;
					}
					if (scriptOpcode == 38) {
						// pop_int_discard
						intValueIndex--;
						continue;
					}
					if (scriptOpcode == 39) {
						// pop_string_discard
						local26--;
						continue;
					}
					if (scriptOpcode == 40) {
						// gosub_with_params
						j = local33[scriptIndex];
						@Pc(642) ClientScript invokeScript = ClientScript.decodeClientScript(j);
						@Pc(646) int[] local646 = new int[invokeScript.localIntCount];
						@Pc(650) JString[] local650 = new JString[invokeScript.localStringCount];
						for (local652 = 0; local652 < invokeScript.anInt4665; local652++) {
							local646[local652] = Static254.scriptIntValues[local652 + intValueIndex - invokeScript.anInt4665];
						}
						for (local652 = 0; local652 < invokeScript.anInt4669; local652++) {
							local650[local652] = Static3.scriptStringValues[local652 + local26 - invokeScript.anInt4669];
						}
						intValueIndex -= invokeScript.anInt4665;
						local26 -= invokeScript.anInt4669;
						@Pc(705) InvokedScript invokedScript = new InvokedScript();
						invokedScript.aClass100Array79 = Static180.localStrings;
						invokedScript.localInts = Static215.localInts;
						invokedScript.anInt2515 = scriptIndex;
						invokedScript.script = clientScript;
						if (Static138.invokedScriptIndex >= Static67.invokedScripts.length) {
							throw new RuntimeException();
						}
						clientScript = invokeScript;
						scriptIndex = -1;
						Static67.invokedScripts[Static138.invokedScriptIndex++] = invokedScript;
						Static215.localInts = local646;
						local33 = invokeScript.intOperands;
						scriptOpcodes = invokeScript.opcodes;
						Static180.localStrings = local650;
						continue;
					}
					if (scriptOpcode == 42) {
						// push_varc_int
						Static254.scriptIntValues[intValueIndex++] = VarPlayerDefinition.varcs[local33[scriptIndex]];
						continue;
					}
					if (scriptOpcode == 43) {
						// pop_varc_int
						j = local33[scriptIndex];
						intValueIndex--;
						VarPlayerDefinition.varcs[j] = Static254.scriptIntValues[intValueIndex];
						Static4.method24(j);
						continue;
					}
					if (scriptOpcode == 44) {
						j = local33[scriptIndex] >> 16;
						intValueIndex--;
						interfaceType = Static254.scriptIntValues[intValueIndex];
						interfaceData = local33[scriptIndex] & 0xFFFF;
						if (interfaceType >= 0 && interfaceType <= 5000) {
							Static55.anIntArray140[j] = interfaceType;
							@Pc(828) byte local828 = -1;
							if (interfaceData == 105) {
								local828 = 0;
							}
							local652 = 0;
							while (true) {
								if (interfaceType <= local652) {
									continue label4266;
								}
								Static179.anIntArrayArray33[j][local652] = local828;
								local652++;
							}
						}
						throw new RuntimeException();
					}
					if (scriptOpcode == 45) {
						j = local33[scriptIndex];
						intValueIndex--;
						interfaceData = Static254.scriptIntValues[intValueIndex];
						if (interfaceData >= 0 && interfaceData < Static55.anIntArray140[j]) {
							Static254.scriptIntValues[intValueIndex++] = Static179.anIntArrayArray33[j][interfaceData];
							continue;
						}
						throw new RuntimeException();
					}
					if (scriptOpcode == 46) {
						j = local33[scriptIndex];
						intValueIndex -= 2;
						interfaceData = Static254.scriptIntValues[intValueIndex];
						if (interfaceData >= 0 && interfaceData < Static55.anIntArray140[j]) {
							Static179.anIntArrayArray33[j][interfaceData] = Static254.scriptIntValues[intValueIndex + 1];
							continue;
						}
						throw new RuntimeException();
					}
					if (scriptOpcode == 47) {
						string = Static226.varcstrs[local33[scriptIndex]];
						if (string == null) {
							string = Static254.aClass100_1061;
						}
						Static3.scriptStringValues[local26++] = string;
						continue;
					}
					if (scriptOpcode == 48) {
						j = local33[scriptIndex];
						local26--;
						Static226.varcstrs[j] = Static3.scriptStringValues[local26];
						Static89.method1840(j);
						continue;
					}
					if (scriptOpcode == 51) {
						@Pc(992) IterableMap local992 = clientScript.aClass133Array1[local33[scriptIndex]];
						intValueIndex--;
						@Pc(1002) IntWrapper local1002 = (IntWrapper) local992.getNode((long) Static254.scriptIntValues[intValueIndex]);
						if (local1002 != null) {
							scriptIndex += local1002.value;
						}
						continue;
					}
				}
				@Pc(1020) boolean local1020;
				if (local33[scriptIndex] == 1) {
					local1020 = true;
				} else {
					local1020 = false;
				}
				@Pc(1182) Component local1182;
				@Pc(1052) int childCount;
				@Pc(1063) Component component;
				@Pc(1087) int childId;
				@Pc(1256) Component local1256;
				if (scriptOpcode < 300) {
					if (scriptOpcode == 100) {
						// cc_create
						intValueIndex -= 3;
						interfaceData = Static254.scriptIntValues[intValueIndex];
						interfaceType = Static254.scriptIntValues[intValueIndex + 1];
						childCount = Static254.scriptIntValues[intValueIndex + 2];
						if (interfaceType != 0) {
							component = Component.getComponent(interfaceData);
							if (component.createdComponents == null) {
								component.createdComponents = new Component[childCount + 1];
							}
							if (childCount >= component.createdComponents.length) {
								@Pc(1085) Component[] childInterfaces = new Component[childCount + 1];
								for (childId = 0; childId < component.createdComponents.length; childId++) {
									childInterfaces[childId] = component.createdComponents[childId];
								}
								component.createdComponents = childInterfaces;
							}
							if (childCount > 0 && component.createdComponents[childCount - 1] == null) {
								throw new RuntimeException("Gap at:" + (childCount - 1));
							}
							@Pc(1137) Component local1137 = new Component();
							local1137.aBoolean32 = true;
							local1137.componentId = childCount;
							local1137.layer = local1137.anInt507 = component.anInt507;
							local1137.INVENTORY = interfaceType;
							component.createdComponents[childCount] = local1137;
							if (local1020) {
								Static274.aClass13_24 = local1137;
							} else {
								Static227.aClass13_25 = local1137;
							}
							Static43.method1143(component);
							continue;
						}
						throw new RuntimeException();
					}
					@Pc(1204) Component local1204;
					if (scriptOpcode == 101) {
						// cc_delete
						local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
						if (local1182.componentId == -1) {
							if (!local1020) {
								throw new RuntimeException("Tried to cc_delete static active-component!");
							}
							throw new RuntimeException("Tried to .cc_delete static .active-component!");
						}
						local1204 = Component.getComponent(local1182.anInt507);
						local1204.createdComponents[local1182.componentId] = null;
						Static43.method1143(local1204);
						continue;
					}
					if (scriptOpcode == 102) {
						// cc_deleteall
						intValueIndex--;
						local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
						local1182.createdComponents = null;
						Static43.method1143(local1182);
						continue;
					}
					if (scriptOpcode == 200) {
						intValueIndex -= 2;
						interfaceData = Static254.scriptIntValues[intValueIndex];
						interfaceType = Static254.scriptIntValues[intValueIndex + 1];
						local1256 = Static201.method1418(interfaceData, interfaceType);
						if (local1256 != null && interfaceType != -1) {
							Static254.scriptIntValues[intValueIndex++] = 1;
							if (local1020) {
								Static274.aClass13_24 = local1256;
							} else {
								Static227.aClass13_25 = local1256;
							}
							continue;
						}
						Static254.scriptIntValues[intValueIndex++] = 0;
						continue;
					}
					if (scriptOpcode == 201) {
						intValueIndex--;
						interfaceData = Static254.scriptIntValues[intValueIndex];
						local1204 = Component.getComponent(interfaceData);
						if (local1204 == null) {
							Static254.scriptIntValues[intValueIndex++] = 0;
						} else {
							Static254.scriptIntValues[intValueIndex++] = 1;
							if (local1020) {
								Static274.aClass13_24 = local1204;
							} else {
								Static227.aClass13_25 = local1204;
							}
						}
						continue;
					}
				} else {
					@Pc(12388) boolean local12388;
					if (scriptOpcode < 500) {
						if (scriptOpcode == 403) {
							intValueIndex -= 2;
							interfaceType = Static254.scriptIntValues[intValueIndex + 1];
							interfaceData = Static254.scriptIntValues[intValueIndex];
							for (childCount = 0; childCount < Static204.anIntArray425.length; childCount++) {
								if (interfaceData == Static204.anIntArray425[childCount]) {
									Static173.localPlayer.model.method1953(childCount, interfaceType);
									continue label4266;
								}
							}
							childCount = 0;
							while (true) {
								if (childCount >= Static153.anIntArray351.length) {
									continue label4266;
								}
								if (interfaceData == Static153.anIntArray351[childCount]) {
									Static173.localPlayer.model.method1953(childCount, interfaceType);
									continue label4266;
								}
								childCount++;
							}
						}
						if (scriptOpcode == 404) {
							intValueIndex -= 2;
							interfaceData = Static254.scriptIntValues[intValueIndex];
							interfaceType = Static254.scriptIntValues[intValueIndex + 1];
							Static173.localPlayer.model.method1951(interfaceData, interfaceType);
							continue;
						}
						if (scriptOpcode == 410) {
							intValueIndex--;
							local12388 = Static254.scriptIntValues[intValueIndex] != 0;
							Static173.localPlayer.model.method1948(local12388);
							continue;
						}
					} else {
						@Pc(1552) boolean local1552;
						if ((scriptOpcode < 1000 || scriptOpcode >= 1100) && (scriptOpcode < 2000 || scriptOpcode >= 2100)) {
							@Pc(2522) JString chatTypedLowercase;
							if (scriptOpcode >= 1100 && scriptOpcode < 1200 || !(scriptOpcode < 2100 || scriptOpcode >= 2200)) {
								if (scriptOpcode < 2000) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
								} else {
									scriptOpcode -= 1000;
									intValueIndex--;
									local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
								}
								if (scriptOpcode == 1100) {
									// setscrollpos
									intValueIndex -= 2;
									local1182.anInt489 = Static254.scriptIntValues[intValueIndex];
									if (local1182.anInt489 > local1182.anInt486 - local1182.anInt445) {
										local1182.anInt489 = local1182.anInt486 - local1182.anInt445;
									}
									if (local1182.anInt489 < 0) {
										local1182.anInt489 = 0;
									}
									local1182.scrollY = Static254.scriptIntValues[intValueIndex + 1];
									if (local1182.scrollY > local1182.anInt491 - local1182.anInt459) {
										local1182.scrollY = local1182.anInt491 - local1182.anInt459;
									}
									if (local1182.scrollY < 0) {
										local1182.scrollY = 0;
									}
									Static43.method1143(local1182);
									if (local1182.componentId == -1) {
										Static118.method2353(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1101) {
									// setcolor
									intValueIndex--;
									local1182.anInt474 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									if (local1182.componentId == -1) {
										Static245.method4224(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1102) {
									// setfill
									intValueIndex--;
									local1182.aBoolean30 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1103) {
									// settrans
									intValueIndex--;
									local1182.anInt476 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1104) {
									// setlinewid
									intValueIndex--;
									local1182.anInt490 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1105) {
									// setgraphic
									intValueIndex--;
									local1182.anInt477 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1106) {
									intValueIndex--;
									local1182.anInt521 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1107) {
									// settiling
									intValueIndex--;
									local1182.aBoolean23 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1108) {
									// setmodel
									local1182.modelType = 1;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									if (local1182.componentId == -1) {
										Static271.method4600(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1109) {
									// setmodelangle
									intValueIndex -= 6;
									local1182.modelXOffset = Static254.scriptIntValues[intValueIndex];
									local1182.modelZOffset = Static254.scriptIntValues[intValueIndex + 1];
									local1182.modelXAngle = Static254.scriptIntValues[intValueIndex + 2];
									local1182.modelYAngle = Static254.scriptIntValues[intValueIndex + 3];
									local1182.modelYOffset = Static254.scriptIntValues[intValueIndex + 4];
									local1182.modelZoom = Static254.scriptIntValues[intValueIndex + 5];
									Static43.method1143(local1182);
									if (local1182.componentId == -1) {
										Static153.method2910(local1182.anInt507);
										Static180.method3328(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1110) {
									// setmodelanim
									intValueIndex--;
									interfaceType = Static254.scriptIntValues[intValueIndex];
									if (local1182.anInt522 != interfaceType) {
										local1182.anInt522 = interfaceType;
										local1182.anInt510 = 0;
										local1182.anInt500 = 0;
										local1182.anInt496 = 1;
										Static43.method1143(local1182);
									}
									if (local1182.componentId == -1) {
										Static181.method3345(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1111) {
									// setmodelorthog
									intValueIndex--;
									local1182.aBoolean22 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1112) {
									// settext
									local26--;
									chatTypedLowercase = Static3.scriptStringValues[local26];
									if (!chatTypedLowercase.method3108(local1182.aClass100_84)) {
										local1182.aClass100_84 = chatTypedLowercase;
										Static43.method1143(local1182);
									}
									if (local1182.componentId == -1) {
										Static163.method3096(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1113) {
									// settextfont
									intValueIndex--;
									local1182.fontId = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1114) {
									// settextalign
									intValueIndex -= 3;
									local1182.anInt460 = Static254.scriptIntValues[intValueIndex];
									local1182.anInt478 = Static254.scriptIntValues[intValueIndex + 1];
									local1182.anInt467 = Static254.scriptIntValues[intValueIndex + 2];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1115) {
									// settextshadow
									intValueIndex--;
									local1182.aBoolean28 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1116) {
									intValueIndex--;
									local1182.anInt514 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1117) {
									intValueIndex--;
									local1182.anInt513 = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1118) {
									intValueIndex--;
									local1182.aBoolean21 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1119) {
									intValueIndex--;
									local1182.aBoolean26 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1120) {
									intValueIndex -= 2;
									local1182.anInt486 = Static254.scriptIntValues[intValueIndex];
									local1182.anInt491 = Static254.scriptIntValues[intValueIndex + 1];
									Static43.method1143(local1182);
									if (local1182.INVENTORY == 0) {
										Static17.method531(local1182, false);
									}
									continue;
								}
								if (scriptOpcode == 1121) {
									intValueIndex -= 2;
									local1182.aShort11 = (short) Static254.scriptIntValues[intValueIndex];
									local1182.aShort10 = (short) Static254.scriptIntValues[intValueIndex + 1];
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1122) {
									intValueIndex--;
									local1182.aBoolean18 = Static254.scriptIntValues[intValueIndex] == 1;
									Static43.method1143(local1182);
									continue;
								}
								if (scriptOpcode == 1123) {
									intValueIndex--;
									local1182.modelZoom = Static254.scriptIntValues[intValueIndex];
									Static43.method1143(local1182);
									if (local1182.componentId == -1) {
										Static153.method2910(local1182.anInt507);
									}
									continue;
								}
							} else if (scriptOpcode >= 1200 && scriptOpcode < 1300 || !(scriptOpcode < 2200 || scriptOpcode >= 2300)) {
								if (scriptOpcode < 2000) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
								} else {
									intValueIndex--;
									local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
									scriptOpcode -= 1000;
								}
								Static43.method1143(local1182);
								if (scriptOpcode == 1200 || scriptOpcode == 1205) {
									intValueIndex -= 2;
									childCount = Static254.scriptIntValues[intValueIndex + 1];
									interfaceType = Static254.scriptIntValues[intValueIndex];
									if (local1182.componentId == -1) {
										Static251.method4279(local1182.anInt507);
										Static153.method2910(local1182.anInt507);
										Static180.method3328(local1182.anInt507);
									}
									if (interfaceType == -1) {
										local1182.modelId = -1;
										local1182.modelType = 1;
										local1182.objId = -1;
									} else {
										local1182.objId = interfaceType;
										local1182.objCount = childCount;
										@Pc(13416) ItemDefinition local13416 = Static71.get(interfaceType);
										local1182.modelYOffset = local13416.zan2d;
										local1182.modelXOffset = local13416.xof2d;
										local1182.modelXAngle = local13416.xan2d;
										local1182.modelZOffset = local13416.yof2d;
										local1182.modelYAngle = local13416.yan2d;
										local1182.modelZoom = local13416.zoom2d;
										if (local1182.anInt451 > 0) {
											local1182.modelZoom = local1182.modelZoom * 32 / local1182.anInt451;
										} else if (local1182.baseWidth > 0) {
											local1182.modelZoom = local1182.modelZoom * 32 / local1182.baseWidth;
										}
										if (scriptOpcode == 1205) {
											local1182.aBoolean31 = false;
										} else {
											local1182.aBoolean31 = true;
										}
									}
									continue;
								}
								if (scriptOpcode == 1201) {
									// setnpchead
									local1182.modelType = 2;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									if (local1182.componentId == -1) {
										Static271.method4600(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1202) {
									// setplayerhead_self
									local1182.modelType = 3;
									local1182.modelId = Static173.localPlayer.model.getHeadModelId();
									if (local1182.componentId == -1) {
										Static271.method4600(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1203) {
									// setnpcmodel
									local1182.modelType = 6;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									if (local1182.componentId == -1) {
										Static271.method4600(local1182.anInt507);
									}
									continue;
								}
								if (scriptOpcode == 1204) {
									local1182.modelType = 5;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									if (local1182.componentId == -1) {
										Static271.method4600(local1182.anInt507);
									}
									continue;
								}
							} else if (scriptOpcode >= 1300 && scriptOpcode < 1400 || scriptOpcode >= 2300 && scriptOpcode < 2400) {
								if (scriptOpcode >= 2000) {
									// if_
									intValueIndex--;
									local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
									scriptOpcode -= 1000;
								} else {
									// cc_
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
								}
								if (scriptOpcode == 1300) {
									intValueIndex--;
									interfaceType = Static254.scriptIntValues[intValueIndex] - 1;
									if (interfaceType >= 0 && interfaceType <= 9) {
										local26--;
										local1182.method480(Static3.scriptStringValues[local26], interfaceType);
										continue;
									}
									local26--;
									continue;
								}
								if (scriptOpcode == 1301) {
									intValueIndex -= 2;
									childCount = Static254.scriptIntValues[intValueIndex + 1];
									interfaceType = Static254.scriptIntValues[intValueIndex];
									local1182.aClass13_5 = Static201.method1418(interfaceType, childCount);
									continue;
								}
								if (scriptOpcode == 1302) {
									intValueIndex--;
									local1182.aBoolean27 = Static254.scriptIntValues[intValueIndex] == 1;
									continue;
								}
								if (scriptOpcode == 1303) {
									intValueIndex--;
									local1182.anInt472 = Static254.scriptIntValues[intValueIndex];
									continue;
								}
								if (scriptOpcode == 1304) {
									intValueIndex--;
									local1182.anInt447 = Static254.scriptIntValues[intValueIndex];
									continue;
								}
								if (scriptOpcode == 1305) {
									local26--;
									local1182.aClass100_88 = Static3.scriptStringValues[local26];
									continue;
								}
								if (scriptOpcode == 1306) {
									local26--;
									local1182.aClass100_86 = Static3.scriptStringValues[local26];
									continue;
								}
								if (scriptOpcode == 1307) {
									local1182.aClass100Array18 = null;
									continue;
								}
								if (scriptOpcode == 1308) {
									intValueIndex--;
									local1182.anInt484 = Static254.scriptIntValues[intValueIndex];
									intValueIndex--;
									local1182.anInt499 = Static254.scriptIntValues[intValueIndex];
									continue;
								}
								if (scriptOpcode == 1309) {
									intValueIndex--;
									interfaceType = Static254.scriptIntValues[intValueIndex];
									intValueIndex--;
									childCount = Static254.scriptIntValues[intValueIndex];
									if (childCount >= 1 && childCount <= 10) {
										local1182.method477(childCount - 1, interfaceType);
									}
									continue;
								}
							} else {
								@Pc(4859) int start;
								if (scriptOpcode >= 1400 && scriptOpcode < 1500 || scriptOpcode >= 2400 && scriptOpcode < 2500) {
									if (scriptOpcode < 2000) {
										// if_
										local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									} else {
										// cc_
										scriptOpcode -= 1000;
										intValueIndex--;
										local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
									}
									@Pc(12937) int[] local12937 = null;
									local26--;
									chatTypedLowercase = Static3.scriptStringValues[local26];
									if (chatTypedLowercase.length() > 0 && chatTypedLowercase.method3149(chatTypedLowercase.length() - 1) == 89) {
										intValueIndex--;
										local652 = Static254.scriptIntValues[intValueIndex];
										if (local652 > 0) {
											local12937 = new int[local652];
											while (local652-- > 0) {
												intValueIndex--;
												local12937[local652] = Static254.scriptIntValues[intValueIndex];
											}
										}
										chatTypedLowercase = chatTypedLowercase.substring(chatTypedLowercase.length() - 1, 0);
									}
									@Pc(13000) Object[] local13000 = new Object[chatTypedLowercase.length() + 1];
									for (start = local13000.length - 1; start >= 1; start--) {
										if (chatTypedLowercase.method3149(start - 1) == 115) {
											local26--;
											local13000[start] = Static3.scriptStringValues[local26];
										} else {
											intValueIndex--;
											local13000[start] = Integer.valueOf(Static254.scriptIntValues[intValueIndex]);
										}
									}
									intValueIndex--;
									start = Static254.scriptIntValues[intValueIndex];
									if (start == -1) {
										local13000 = null;
									} else {
										local13000[0] = Integer.valueOf(start);
									}
									local1182.aBoolean25 = true;
									if (scriptOpcode == 1400) {
										local1182.anObjectArray5 = local13000;
									} else if (scriptOpcode == 1401) {
										local1182.anObjectArray9 = local13000;
									} else if (scriptOpcode == 1402) {
										local1182.anObjectArray18 = local13000;
									} else if (scriptOpcode == 1403) {
										local1182.anObjectArray19 = local13000;
									} else if (scriptOpcode == 1404) {
										local1182.anObjectArray24 = local13000;
									} else if (scriptOpcode == 1405) {
										local1182.anObjectArray26 = local13000;
									} else if (scriptOpcode == 1406) {
										local1182.anObjectArray27 = local13000;
									} else if (scriptOpcode == 1407) {
										local1182.anIntArray44 = local12937;
										local1182.anObjectArray25 = local13000;
									} else if (scriptOpcode == 1408) {
										local1182.anObjectArray22 = local13000;
									} else if (scriptOpcode == 1409) {
										local1182.anObjectArray29 = local13000;
									} else if (scriptOpcode == 1410) {
										local1182.anObjectArray16 = local13000;
									} else if (scriptOpcode == 1411) {
										local1182.anObjectArray6 = local13000;
									} else if (scriptOpcode == 1412) {
										local1182.anObjectArray23 = local13000;
									} else if (scriptOpcode == 1414) {
										local1182.anIntArray34 = local12937;
										local1182.anObjectArray7 = local13000;
									} else if (scriptOpcode == 1415) {
										local1182.anIntArray42 = local12937;
										local1182.anObjectArray2 = local13000;
									} else if (scriptOpcode == 1416) {
										local1182.anObjectArray11 = local13000;
									} else if (scriptOpcode == 1417) {
										local1182.anObjectArray10 = local13000;
									} else if (scriptOpcode == 1418) {
										local1182.anObjectArray20 = local13000;
									} else if (scriptOpcode == 1419) {
										local1182.anObjectArray14 = local13000;
									} else if (scriptOpcode == 1420) {
										local1182.anObjectArray1 = local13000;
									} else if (scriptOpcode == 1421) {
										local1182.anObjectArray28 = local13000;
									} else if (scriptOpcode == 1422) {
										local1182.anObjectArray30 = local13000;
									} else if (scriptOpcode == 1423) {
										local1182.anObjectArray12 = local13000;
									} else if (scriptOpcode == 1424) {
										local1182.anObjectArray8 = local13000;
									} else if (scriptOpcode == 1425) {
										local1182.anObjectArray21 = local13000;
									} else if (scriptOpcode == 1426) {
										local1182.anObjectArray13 = local13000;
									} else if (scriptOpcode == 1427) {
										local1182.anObjectArray17 = local13000;
									} else if (scriptOpcode == 1428) {
										local1182.anObjectArray4 = local13000;
										local1182.anIntArray38 = local12937;
									} else if (scriptOpcode == 1429) {
										local1182.anIntArray35 = local12937;
										local1182.anObjectArray15 = local13000;
									}
									continue;
								}
								if (scriptOpcode < 1600) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									if (scriptOpcode == 1500) {
										// cc_getx
										Static254.scriptIntValues[intValueIndex++] = local1182.x;
										continue;
									}
									if (scriptOpcode == 1501) {
										// cc_gety
										Static254.scriptIntValues[intValueIndex++] = local1182.y;
										continue;
									}
									if (scriptOpcode == 1502) {
										// cc_getwidth
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt445;
										continue;
									}
									if (scriptOpcode == 1503) {
										// cc_getheight
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt459;
										continue;
									}
									if (scriptOpcode == 1504) {
										// cc_gethide
										Static254.scriptIntValues[intValueIndex++] = local1182.hidden ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 1505) {
										// set_getlayer
										Static254.scriptIntValues[intValueIndex++] = local1182.layer;
										continue;
									}
								} else if (scriptOpcode < 1700) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									if (scriptOpcode == 1600) {
										// cc_getscrollx
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt489;
										continue;
									}
									if (scriptOpcode == 1601) {
										// cc_getscrolly
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollY;
										continue;
									}
									if (scriptOpcode == 1602) {
										Static3.scriptStringValues[local26++] = local1182.aClass100_84;
										continue;
									}
									if (scriptOpcode == 1603) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt486;
										continue;
									}
									if (scriptOpcode == 1604) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt491;
										continue;
									}
									if (scriptOpcode == 1605) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelZoom;
										continue;
									}
									if (scriptOpcode == 1606) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelXAngle;
										continue;
									}
									if (scriptOpcode == 1607) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelYOffset;
										continue;
									}
									if (scriptOpcode == 1608) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelYAngle;
										continue;
									}
									if (scriptOpcode == 1609) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt476;
										continue;
									}
									if (scriptOpcode == 1610) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelXOffset;
										continue;
									}
									if (scriptOpcode == 1611) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelZOffset;
										continue;
									}
									if (scriptOpcode == 1612) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt477;
										continue;
									}
								} else if (scriptOpcode < 1800) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									if (scriptOpcode == 1700) {
										Static254.scriptIntValues[intValueIndex++] = local1182.objId;
										continue;
									}
									if (scriptOpcode == 1701) {
										if (local1182.objId == -1) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = local1182.objCount;
										}
										continue;
									}
									if (scriptOpcode == 1702) {
										Static254.scriptIntValues[intValueIndex++] = local1182.componentId;
										continue;
									}
								} else if (scriptOpcode < 1900) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									if (scriptOpcode == 1800) {
										Static254.scriptIntValues[intValueIndex++] = Static36.method940(local1182).method512();
										continue;
									}
									if (scriptOpcode == 1801) {
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										interfaceType--;
										if (local1182.aClass100Array18 != null && interfaceType < local1182.aClass100Array18.length && local1182.aClass100Array18[interfaceType] != null) {
											Static3.scriptStringValues[local26++] = local1182.aClass100Array18[interfaceType];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 1802) {
										if (local1182.aClass100_88 == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = local1182.aClass100_88;
										}
										continue;
									}
								} else if (scriptOpcode < 2600) {
									intValueIndex--;
									local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
									if (scriptOpcode == 2500) {
										// if_getx
										Static254.scriptIntValues[intValueIndex++] = local1182.x;
										continue;
									}
									if (scriptOpcode == 2501) {
										// if_gety
										Static254.scriptIntValues[intValueIndex++] = local1182.y;
										continue;
									}
									if (scriptOpcode == 2502) {
										// if_getwidth
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt445;
										continue;
									}
									if (scriptOpcode == 2503) {
										// if_getheight
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt459;
										continue;
									}
									if (scriptOpcode == 2504) {
										// if_gethide
										Static254.scriptIntValues[intValueIndex++] = local1182.hidden ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 2505) {
										// if_getlayer
										Static254.scriptIntValues[intValueIndex++] = local1182.layer;
										continue;
									}
								} else if (scriptOpcode < 2700) {
									intValueIndex--;
									local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
									if (scriptOpcode == 2600) {
										// if_getscrollx
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt489;
										continue;
									}
									if (scriptOpcode == 2601) {
										// if_getscrolly
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollY;
										continue;
									}
									if (scriptOpcode == 2602) {
										Static3.scriptStringValues[local26++] = local1182.aClass100_84;
										continue;
									}
									if (scriptOpcode == 2603) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt486;
										continue;
									}
									if (scriptOpcode == 2604) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt491;
										continue;
									}
									if (scriptOpcode == 2605) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelZoom;
										continue;
									}
									if (scriptOpcode == 2606) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelXAngle;
										continue;
									}
									if (scriptOpcode == 2607) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelYOffset;
										continue;
									}
									if (scriptOpcode == 2608) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelYAngle;
										continue;
									}
									if (scriptOpcode == 2609) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt476;
										continue;
									}
									if (scriptOpcode == 2610) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelXOffset;
										continue;
									}
									if (scriptOpcode == 2611) {
										Static254.scriptIntValues[intValueIndex++] = local1182.modelZOffset;
										continue;
									}
									if (scriptOpcode == 2612) {
										Static254.scriptIntValues[intValueIndex++] = local1182.anInt477;
										continue;
									}
								} else if (scriptOpcode < 2800) {
									if (scriptOpcode == 2700) {
										intValueIndex--;
										local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
										Static254.scriptIntValues[intValueIndex++] = local1182.objId;
										continue;
									}
									if (scriptOpcode == 2701) {
										intValueIndex--;
										local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
										if (local1182.objId == -1) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = local1182.objCount;
										}
										continue;
									}
									if (scriptOpcode == 2702) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										@Pc(12566) Class3_Sub31 local12566 = (Class3_Sub31) Static119.aClass133_9.getNode((long) interfaceData);
										if (local12566 == null) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = 1;
										}
										continue;
									}
									if (scriptOpcode == 2703) {
										intValueIndex--;
										local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
										if (local1182.createdComponents == null) {
											Static254.scriptIntValues[intValueIndex++] = 0;
											continue;
										}
										interfaceType = local1182.createdComponents.length;
										for (childCount = 0; childCount < local1182.createdComponents.length; childCount++) {
											if (local1182.createdComponents[childCount] == null) {
												interfaceType = childCount;
												break;
											}
										}
										Static254.scriptIntValues[intValueIndex++] = interfaceType;
										continue;
									}
									if (scriptOpcode == 2704 || scriptOpcode == 2705) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										@Pc(12663) Class3_Sub31 local12663 = (Class3_Sub31) Static119.aClass133_9.getNode((long) interfaceData);
										if (local12663 != null && local12663.anInt5878 == interfaceType) {
											Static254.scriptIntValues[intValueIndex++] = 1;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
								} else if (scriptOpcode < 2900) {
									intValueIndex--;
									local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
									if (scriptOpcode == 2800) {
										Static254.scriptIntValues[intValueIndex++] = Static36.method940(local1182).method512();
										continue;
									}
									if (scriptOpcode == 2801) {
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										interfaceType--;
										if (local1182.aClass100Array18 != null && local1182.aClass100Array18.length > interfaceType && local1182.aClass100Array18[interfaceType] != null) {
											Static3.scriptStringValues[local26++] = local1182.aClass100Array18[interfaceType];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 2802) {
										if (local1182.aClass100_88 == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = local1182.aClass100_88;
										}
										continue;
									}
								} else if (scriptOpcode < 3200) {
									if (scriptOpcode == 3100) {
										// mes
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static103.addMessage(Static72.aClass100_447, 0, chatTyped);
										continue;
									}
									if (scriptOpcode == 3101) {
										// anim
										intValueIndex -= 2;
										Static186.method3415(Static254.scriptIntValues[intValueIndex + 1], Static254.scriptIntValues[intValueIndex], Static173.localPlayer);
										continue;
									}
									if (scriptOpcode == 3103) {
										Static153.method2909();
										continue;
									}
									if (scriptOpcode == 3104) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										interfaceType = 0;
										if (chatTyped.method3123()) {
											interfaceType = chatTyped.method3132();
										}
										Static6.outboundBuffer.pIsaac1(23);
										Static6.outboundBuffer.p4(interfaceType);
										continue;
									}
									if (scriptOpcode == 3105) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static6.outboundBuffer.pIsaac1(244);
										Static6.outboundBuffer.p8(chatTyped.toBase37());
										continue;
									}
									if (scriptOpcode == 3106) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static6.outboundBuffer.pIsaac1(65);
										Static6.outboundBuffer.p1(chatTyped.length() + 1);
										Static6.outboundBuffer.pjstr(chatTyped);
										continue;
									}
									if (scriptOpcode == 3107) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										local26--;
										chatTypedLowercase = Static3.scriptStringValues[local26];
										Static276.method4613(interfaceData, chatTypedLowercase);
										continue;
									}
									if (scriptOpcode == 3108) {
										intValueIndex -= 3;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										childCount = Static254.scriptIntValues[intValueIndex + 2];
										component = Component.getComponent(childCount);
										Static40.method1015(interfaceType, interfaceData, component);
										continue;
									}
									if (scriptOpcode == 3109) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										local1256 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static40.method1015(interfaceType, interfaceData, local1256);
										continue;
									}
									if (scriptOpcode == 3110) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static6.outboundBuffer.pIsaac1(111);
										Static6.outboundBuffer.p2(interfaceData);
										continue;
									}
								} else if (scriptOpcode < 3300) {
									if (scriptOpcode == 3200) {
										// sound_synth
										intValueIndex -= 3;
										Static26.method744(Static254.scriptIntValues[intValueIndex + 1], Static254.scriptIntValues[intValueIndex], Static254.scriptIntValues[intValueIndex + 2]);
										continue;
									}
									if (scriptOpcode == 3201) {
										// sound_song
										intValueIndex--;
										Static148.method2765(Static254.scriptIntValues[intValueIndex]);
										continue;
									}
									if (scriptOpcode == 3202) {
										// sound_jingle
										intValueIndex -= 2;
										Static278.method4650(Static254.scriptIntValues[intValueIndex + 1], Static254.scriptIntValues[intValueIndex]);
										continue;
									}
								} else if (scriptOpcode < 3400) {
									if (scriptOpcode == 3300) {
										// clientclock
										Static254.scriptIntValues[intValueIndex++] = Static83.loopCycle;
										continue;
									}
									if (scriptOpcode == 3301) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Static15.method484(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3302) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static23.method647(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3303) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = ClientInvCache.total(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3304) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static246.get(interfaceData).size;
										continue;
									}
									if (scriptOpcode == 3305) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static99.skillLevel[interfaceData];
										continue;
									}
									if (scriptOpcode == 3306) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static141.skillBaseLevel[interfaceData];
										continue;
									}
									if (scriptOpcode == 3307) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static227.skillExperience[interfaceData];
										continue;
									}
									if (scriptOpcode == 3308) {
										interfaceData = Static55.currentLevel;
										interfaceType = Static225.originX + (Static173.localPlayer.x >> 7);
										childCount = (Static173.localPlayer.z >> 7) + Static142.originZ;
										Static254.scriptIntValues[intValueIndex++] = (interfaceData << 28) - (-(interfaceType << 14) - childCount);
										continue;
									}
									if (scriptOpcode == 3309) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = interfaceData >> 14 & 0x3FFF;
										continue;
									}
									if (scriptOpcode == 3310) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = interfaceData >> 28;
										continue;
									}
									if (scriptOpcode == 3311) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = interfaceData & 0x3FFF;
										continue;
									}
									if (scriptOpcode == 3312) {
										Static254.scriptIntValues[intValueIndex++] = Static2.membersWorld ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3313) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex] + 32768;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Static15.method484(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3314) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex] + 32768;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Static23.method647(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3315) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex] + 32768;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = ClientInvCache.total(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3316) {
										if (Static191.staffModLevel < 2) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = Static191.staffModLevel;
										}
										continue;
									}
									if (scriptOpcode == 3317) {
										Static254.scriptIntValues[intValueIndex++] = Static60.systemUpdateTimer;
										continue;
									}
									if (scriptOpcode == 3318) {
										Static254.scriptIntValues[intValueIndex++] = Static125.worldId;
										continue;
									}
									if (scriptOpcode == 3321) {
										Static254.scriptIntValues[intValueIndex++] = ClientScriptRunner.energy;
										continue;
									}
									if (scriptOpcode == 3322) {
										Static254.scriptIntValues[intValueIndex++] = Static251.weightCarried;
										continue;
									}
									if (scriptOpcode == 3323) {
										if (Static249.anInt5431 >= 5 && Static249.anInt5431 <= 9) {
											Static254.scriptIntValues[intValueIndex++] = 1;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3324) {
										if (Static249.anInt5431 >= 5 && Static249.anInt5431 <= 9) {
											Static254.scriptIntValues[intValueIndex++] = Static249.anInt5431;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3325) {
										Static254.scriptIntValues[intValueIndex++] = Class6.members ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3326) {
										Static254.scriptIntValues[intValueIndex++] = Static173.localPlayer.combatLevel;
										continue;
									}
									if (scriptOpcode == 3327) {
										Static254.scriptIntValues[intValueIndex++] = Static173.localPlayer.model.aBoolean141 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3328) {
										Static254.scriptIntValues[intValueIndex++] = Static124.aBoolean157 && !Static207.parentalChatConsent ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3329) {
										Static254.scriptIntValues[intValueIndex++] = Static86.aBoolean129 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3330) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = ClientInvCache.method446(interfaceData);
										continue;
									}
									if (scriptOpcode == 3331) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static178.method3319(false, interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3332) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Static178.method3319(true, interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3333) {
										Static254.scriptIntValues[intValueIndex++] = Static5.anInt39;
										continue;
									}
									if (scriptOpcode == 3335) {
										Static254.scriptIntValues[intValueIndex++] = Static141.anInt3470;
										continue;
									}
									if (scriptOpcode == 3336) {
										intValueIndex -= 4;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceData += interfaceType << 14;
										local652 = Static254.scriptIntValues[intValueIndex + 3];
										childCount = Static254.scriptIntValues[intValueIndex + 2];
										interfaceData += childCount << 28;
										interfaceData += local652;
										Static254.scriptIntValues[intValueIndex++] = interfaceData;
										continue;
									}
									if (scriptOpcode == 3337) {
										Static254.scriptIntValues[intValueIndex++] = Static204.anInt4760;
										continue;
									}
								} else if (scriptOpcode < 3500) {
									@Pc(3422) EnumType local3422;
									if (scriptOpcode == 3400) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										local3422 = Static253.get(interfaceData);
										if (local3422.outputtype == 115) {
										}
										Static3.scriptStringValues[local26++] = local3422.getValueString(interfaceType);
										continue;
									}
									if (scriptOpcode == 3408) {
										intValueIndex -= 4;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										local652 = Static254.scriptIntValues[intValueIndex + 3];
										childCount = Static254.scriptIntValues[intValueIndex + 2];
										@Pc(3469) EnumType local3469 = Static253.get(childCount);
										if (local3469.inputtype == interfaceData && local3469.outputtype == interfaceType) {
											if (interfaceType == 115) {
												Static3.scriptStringValues[local26++] = local3469.getValueString(local652);
											} else {
												Static254.scriptIntValues[intValueIndex++] = local3469.getValueInt(local652);
											}
											continue;
										}
										throw new RuntimeException("C3408-1");
									}
									if (scriptOpcode == 3409) {
										intValueIndex -= 3;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										childCount = Static254.scriptIntValues[intValueIndex + 2];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (interfaceType == -1) {
											throw new RuntimeException("C3409-2");
										}
										@Pc(3549) EnumType local3549 = Static253.get(interfaceType);
										if (local3549.outputtype != interfaceData) {
											throw new RuntimeException("C3409-1");
										}
										Static254.scriptIntValues[intValueIndex++] = local3549.method3090(childCount) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3410) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										local26--;
										chatTypedLowercase = Static3.scriptStringValues[local26];
										if (interfaceData == -1) {
											throw new RuntimeException("C3410-2");
										}
										local3422 = Static253.get(interfaceData);
										if (local3422.outputtype != 115) {
											throw new RuntimeException("C3410-1");
										}
										Static254.scriptIntValues[intValueIndex++] = local3422.method3086(chatTypedLowercase) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3411) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										@Pc(3645) EnumType local3645 = Static253.get(interfaceData);
										Static254.scriptIntValues[intValueIndex++] = local3645.values.length();
										continue;
									}
								} else if (scriptOpcode < 3700) {
									if (scriptOpcode == 3600) {
										if (Static166.anInt4054 == 0) {
											Static254.scriptIntValues[intValueIndex++] = -2;
										} else if (Static166.anInt4054 == 1) {
											Static254.scriptIntValues[intValueIndex++] = -1;
										} else {
											Static254.scriptIntValues[intValueIndex++] = CacheArchive.friendCount;
										}
										continue;
									}
									if (scriptOpcode == 3601) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static166.anInt4054 == 2 && interfaceData < CacheArchive.friendCount) {
											Static3.scriptStringValues[local26++] = Static122.friendName[interfaceData];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3602) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static166.anInt4054 == 2 && CacheArchive.friendCount > interfaceData) {
											Static254.scriptIntValues[intValueIndex++] = Static104.friendWorld[interfaceData];
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3603) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static166.anInt4054 == 2 && CacheArchive.friendCount > interfaceData) {
											Static254.scriptIntValues[intValueIndex++] = Static106.anIntArray258[interfaceData];
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3604) {
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static171.method3221(chatTyped, interfaceType);
										continue;
									}
									if (scriptOpcode == 3605) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static64.addFriend(chatTyped.toBase37());
										continue;
									}
									if (scriptOpcode == 3606) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static193.removeFriend(chatTyped.toBase37());
										continue;
									}
									if (scriptOpcode == 3607) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static140.addIgnore(chatTyped.toBase37());
										continue;
									}
									if (scriptOpcode == 3608) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static69.method1542(chatTyped.toBase37());
										continue;
									}
									if (scriptOpcode == 3609) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										if (chatTyped.startsWith(Static72.aClass100_446) || chatTyped.startsWith(Static101.aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										Static254.scriptIntValues[intValueIndex++] = Static98.method1965(chatTyped) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3610) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static166.anInt4054 == 2 && CacheArchive.friendCount > interfaceData) {
											Static3.scriptStringValues[local26++] = Static214.aClass100Array170[interfaceData];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3611) {
										if (Static15.aClass100_87 == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = Static15.aClass100_87.method3125();
										}
										continue;
									}
									if (scriptOpcode == 3612) {
										if (Static15.aClass100_87 == null) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = Static214.anInt5577;
										}
										continue;
									}
									if (scriptOpcode == 3613) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static15.aClass100_87 != null && Static214.anInt5577 > interfaceData) {
											Static3.scriptStringValues[local26++] = Static199.aClass3_Sub22Array1[interfaceData].aClass100_636.method3125();
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3614) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static15.aClass100_87 != null && interfaceData < Static214.anInt5577) {
											Static254.scriptIntValues[intValueIndex++] = Static199.aClass3_Sub22Array1[interfaceData].anInt3340;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3615) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static15.aClass100_87 != null && Static214.anInt5577 > interfaceData) {
											Static254.scriptIntValues[intValueIndex++] = Static199.aClass3_Sub22Array1[interfaceData].aByte9;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3616) {
										Static254.scriptIntValues[intValueIndex++] = Static50.aByte6;
										continue;
									}
									if (scriptOpcode == 3617) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static178.method3318(chatTyped);
										continue;
									}
									if (scriptOpcode == 3618) {
										Static254.scriptIntValues[intValueIndex++] = Static160.aByte14;
										continue;
									}
									if (scriptOpcode == 3619) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static156.method2956(chatTyped.toBase37());
										continue;
									}
									if (scriptOpcode == 3620) {
										Static134.method2623();
										continue;
									}
									if (scriptOpcode == 3621) {
										if (Static166.anInt4054 == 0) {
											Static254.scriptIntValues[intValueIndex++] = -1;
										} else {
											Static254.scriptIntValues[intValueIndex++] = Static35.ignoreCount;
										}
										continue;
									}
									if (scriptOpcode == 3622) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static166.anInt4054 != 0 && Static35.ignoreCount > interfaceData) {
											Static3.scriptStringValues[local26++] = Static79.decode37(Static190.ignoreName37[interfaceData]).method3125();
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3623) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										if (chatTyped.startsWith(Static72.aClass100_446) || chatTyped.startsWith(Static101.aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										Static254.scriptIntValues[intValueIndex++] = Static238.method4144(chatTyped) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3624) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static199.aClass3_Sub22Array1 != null && Static214.anInt5577 > interfaceData && Static199.aClass3_Sub22Array1[interfaceData].aClass100_636.equalsIgnoreCase(Static173.localPlayer.name)) {
											Static254.scriptIntValues[intValueIndex++] = 1;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3625) {
										if (Static270.aClass100_1094 == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = Static270.aClass100_1094.method3125();
										}
										continue;
									}
									if (scriptOpcode == 3626) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static15.aClass100_87 != null && Static214.anInt5577 > interfaceData) {
											Static3.scriptStringValues[local26++] = Static199.aClass3_Sub22Array1[interfaceData].aClass100_635;
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3627) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (Static166.anInt4054 == 2 && interfaceData >= 0 && interfaceData < CacheArchive.friendCount) {
											Static254.scriptIntValues[intValueIndex++] = Static3.aBooleanArray135[interfaceData] ? 1 : 0;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3628) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										if (chatTyped.startsWith(Static72.aClass100_446) || chatTyped.startsWith(Static101.aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										Static254.scriptIntValues[intValueIndex++] = Static4.method25(chatTyped);
										continue;
									}
									if (scriptOpcode == 3629) {
										Static254.scriptIntValues[intValueIndex++] = Static279.anInt5880;
										continue;
									}
								} else if (scriptOpcode < 4000) {
									if (scriptOpcode == 3903) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static229.aClass136Array1[interfaceData].method3905();
										continue;
									}
									if (scriptOpcode == 3904) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static229.aClass136Array1[interfaceData].anInt5094;
										continue;
									}
									if (scriptOpcode == 3905) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static229.aClass136Array1[interfaceData].anInt5099;
										continue;
									}
									if (scriptOpcode == 3906) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static229.aClass136Array1[interfaceData].anInt5090;
										continue;
									}
									if (scriptOpcode == 3907) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static229.aClass136Array1[interfaceData].anInt5089;
										continue;
									}
									if (scriptOpcode == 3908) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static229.aClass136Array1[interfaceData].anInt5092;
										continue;
									}
									if (scriptOpcode == 3910) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static229.aClass136Array1[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 0 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3911) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static229.aClass136Array1[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 2 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3912) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static229.aClass136Array1[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 5 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3913) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static229.aClass136Array1[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 1 ? 1 : 0;
										continue;
									}
								} else if (scriptOpcode < 4100) {
									if (scriptOpcode == 4000) {
										// add
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceType + interfaceData;
										continue;
									}
									if (scriptOpcode == 4001) {
										// sub
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceData - interfaceType;
										continue;
									}
									if (scriptOpcode == 4002) {
										// multiply
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceType * interfaceData;
										continue;
									}
									if (scriptOpcode == 4003) {
										// divide
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceData / interfaceType;
										continue;
									}
									if (scriptOpcode == 4004) {
										// random
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = (int) ((double) interfaceData * Math.random());
										continue;
									}
									if (scriptOpcode == 4005) {
										// randominc
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = (int) (Math.random() * (double) (interfaceData + 1));
										continue;
									}
									if (scriptOpcode == 4006) {
										// interpolate
										intValueIndex -= 5;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										local652 = Static254.scriptIntValues[intValueIndex + 3];
										childCount = Static254.scriptIntValues[intValueIndex + 2];
										start = Static254.scriptIntValues[intValueIndex + 4];
										Static254.scriptIntValues[intValueIndex++] = (interfaceType - interfaceData) * (start + -childCount) / (local652 - childCount) + interfaceData;
										continue;
									}
									@Pc(4899) long local4899;
									@Pc(4892) long local4892;
									if (scriptOpcode == 4007) {
										// addpercent
										intValueIndex -= 2;
										local4892 = Static254.scriptIntValues[intValueIndex];
										local4899 = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = (int) (local4892 * local4899 / 100L + local4892);
										continue;
									}
									if (scriptOpcode == 4008) {
										// setbit
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceData | 0x1 << interfaceType;
										continue;
									}
									if (scriptOpcode == 4009) {
										// clearbit
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = -(0x1 << interfaceType) - 1 & interfaceData;
										continue;
									}
									if (scriptOpcode == 4010) {
										// testbit
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = (interfaceData & 0x1 << interfaceType) == 0 ? 0 : 1;
										continue;
									}
									if (scriptOpcode == 4011) {
										// modulo
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = interfaceData % interfaceType;
										continue;
									}
									if (scriptOpcode == 4012) {
										// pow
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (interfaceData == 0) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = (int) Math.pow((double) interfaceData, (double) interfaceType);
										}
										continue;
									}
									if (scriptOpcode == 4013) {
										// invpow
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (interfaceData == 0) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else if (interfaceType == 0) {
											Static254.scriptIntValues[intValueIndex++] = Integer.MAX_VALUE;
										} else {
											Static254.scriptIntValues[intValueIndex++] = (int) Math.pow((double) interfaceData, 1.0D / (double) interfaceType);
										}
										continue;
									}
									if (scriptOpcode == 4014) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = interfaceType & interfaceData;
										continue;
									}
									if (scriptOpcode == 4015) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceData | interfaceType;
										continue;
									}
									if (scriptOpcode == 4016) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = interfaceData < interfaceType ? interfaceData : interfaceType;
										continue;
									}
									if (scriptOpcode == 4017) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = interfaceType >= interfaceData ? interfaceType : interfaceData;
										continue;
									}
									if (scriptOpcode == 4018) {
										intValueIndex -= 3;
										local4892 = Static254.scriptIntValues[intValueIndex];
										local4899 = Static254.scriptIntValues[intValueIndex + 1];
										@Pc(5251) long local5251 = (long) Static254.scriptIntValues[intValueIndex + 2];
										Static254.scriptIntValues[intValueIndex++] = (int) (local4892 * local5251 / local4899);
										continue;
									}
								} else if (scriptOpcode >= 4200) {
									@Pc(5294) Class3_Sub2_Sub12 local5294;
									if (scriptOpcode < 4300) {
										if (scriptOpcode == 4200) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											Static3.scriptStringValues[local26++] = Static71.get(interfaceData).name;
											continue;
										}
										@Pc(11269) ItemDefinition local11269;
										if (scriptOpcode == 4201) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local11269 = Static71.get(interfaceData);
											if (interfaceType >= 1 && interfaceType <= 5 && local11269.groundOptions[interfaceType - 1] != null) {
												Static3.scriptStringValues[local26++] = local11269.groundOptions[interfaceType - 1];
												continue;
											}
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
											continue;
										}
										if (scriptOpcode == 4202) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local11269 = Static71.get(interfaceData);
											if (interfaceType >= 1 && interfaceType <= 5 && local11269.interfaceOptions[interfaceType - 1] != null) {
												Static3.scriptStringValues[local26++] = local11269.interfaceOptions[interfaceType - 1];
												continue;
											}
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
											continue;
										}
										if (scriptOpcode == 4203) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											Static254.scriptIntValues[intValueIndex++] = Static71.get(interfaceData).cost;
											continue;
										}
										if (scriptOpcode == 4204) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											Static254.scriptIntValues[intValueIndex++] = Static71.get(interfaceData).stackable == 1 ? 1 : 0;
											continue;
										}
										@Pc(11417) ItemDefinition local11417;
										if (scriptOpcode == 4205) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											local11417 = Static71.get(interfaceData);
											if (local11417.certtemplate == -1 && local11417.certlink >= 0) {
												Static254.scriptIntValues[intValueIndex++] = local11417.certlink;
												continue;
											}
											Static254.scriptIntValues[intValueIndex++] = interfaceData;
											continue;
										}
										if (scriptOpcode == 4206) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											local11417 = Static71.get(interfaceData);
											if (local11417.certtemplate >= 0 && local11417.certlink >= 0) {
												Static254.scriptIntValues[intValueIndex++] = local11417.certlink;
												continue;
											}
											Static254.scriptIntValues[intValueIndex++] = interfaceData;
											continue;
										}
										if (scriptOpcode == 4207) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											Static254.scriptIntValues[intValueIndex++] = Static71.get(interfaceData).members ? 1 : 0;
											continue;
										}
										if (scriptOpcode == 4208) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local5294 = Static110.method2277(interfaceType);
											if (local5294.method2078()) {
												Static3.scriptStringValues[local26++] = Static71.get(interfaceData).getParam(local5294.aClass100_544, interfaceType);
											} else {
												Static254.scriptIntValues[intValueIndex++] = Static71.get(interfaceData).getParam(local5294.anInt2667, interfaceType);
											}
											continue;
										}
										if (scriptOpcode == 4210) {
											local26--;
											chatTyped = Static3.scriptStringValues[local26];
											intValueIndex--;
											interfaceType = Static254.scriptIntValues[intValueIndex];
											Static155.method2941(interfaceType == 1, chatTyped);
											Static254.scriptIntValues[intValueIndex++] = Static111.anInt2905;
											continue;
										}
										if (scriptOpcode == 4211) {
											if (Static169.aShortArray52 != null && Static67.anInt3356 < Static111.anInt2905) {
												Static254.scriptIntValues[intValueIndex++] = Static169.aShortArray52[Static67.anInt3356++] & 0xFFFF;
												continue;
											}
											Static254.scriptIntValues[intValueIndex++] = -1;
											continue;
										}
										if (scriptOpcode == 4212) {
											Static67.anInt3356 = 0;
											continue;
										}
									} else if (scriptOpcode < 4400) {
										if (scriptOpcode == 4300) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local5294 = Static110.method2277(interfaceType);
											if (local5294.method2078()) {
												Static3.scriptStringValues[local26++] = ActorDefinition.getDefinition(interfaceData).getParam(interfaceType, local5294.aClass100_544);
											} else {
												Static254.scriptIntValues[intValueIndex++] = ActorDefinition.getDefinition(interfaceData).getParam(interfaceType, local5294.anInt2667);
											}
											continue;
										}
									} else if (scriptOpcode >= 4500) {
										if (scriptOpcode >= 4600) {
											if (scriptOpcode < 5100) {
												if (scriptOpcode == 5000) {
													Static254.scriptIntValues[intValueIndex++] = Static59.anInt1812;
													continue;
												}
												if (scriptOpcode == 5001) {
													intValueIndex -= 3;
													Static59.anInt1812 = Static254.scriptIntValues[intValueIndex];
													Static49.anInt1459 = Static254.scriptIntValues[intValueIndex + 1];
													Static84.anInt2256 = Static254.scriptIntValues[intValueIndex + 2];
													Static6.outboundBuffer.pIsaac1(157);
													Static6.outboundBuffer.p1(Static59.anInt1812);
													Static6.outboundBuffer.p1(Static49.anInt1459);
													Static6.outboundBuffer.p1(Static84.anInt2256);
													continue;
												}
												if (scriptOpcode == 5002) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex];
													childCount = Static254.scriptIntValues[intValueIndex + 1];
													Static6.outboundBuffer.pIsaac1(99);
													Static6.outboundBuffer.p8(chatTyped.toBase37());
													Static6.outboundBuffer.p1(interfaceType - 1);
													Static6.outboundBuffer.p1(childCount);
													continue;
												}
												if (scriptOpcode == 5003) {
													chatTypedLowercase = null;
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													if (interfaceData < 100) {
														chatTypedLowercase = Static230.messageText[interfaceData];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = Static72.aClass100_447;
													}
													Static3.scriptStringValues[local26++] = chatTypedLowercase;
													continue;
												}
												if (scriptOpcode == 5004) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													interfaceType = -1;
													if (interfaceData < 100 && Static230.messageText[interfaceData] != null) {
														interfaceType = Static26.anIntArray67[interfaceData];
													}
													Static254.scriptIntValues[intValueIndex++] = interfaceType;
													continue;
												}
												if (scriptOpcode == 5005) {
													Static254.scriptIntValues[intValueIndex++] = Static49.anInt1459;
													continue;
												}
												if (scriptOpcode == 5008) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													if (!chatTyped.startsWith(Static12.aClass100_74)) {
														if (Static191.staffModLevel == 0 && (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129)) {
															continue;
														}
														chatTypedLowercase = chatTyped.toLowerCase();
														@Pc(5555) byte color = 0;
														if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL0)) {
															color = 0;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL0.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL1)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL1.length());
															color = 1;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL2)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL2.length());
															color = 2;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL3)) {
															color = 3;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL3.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL4)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL4.length());
															color = 4;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL5)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL5.length());
															color = 5;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL6)) {
															color = 6;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL6.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL7)) {
															color = 7;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL7.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL8)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL8.length());
															color = 8;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL9)) {
															color = 9;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL9.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL10)) {
															color = 10;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL10.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATCOL11)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATCOL11.length());
															color = 11;
														} else if (Static141.anInt3470 != 0) {
															if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL0)) {
																color = 0;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL0.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL1)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL1.length());
																color = 1;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL2)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL2.length());
																color = 2;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL3)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL3.length());
																color = 3;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL4)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL4.length());
																color = 4;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL5)) {
																color = 5;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL5.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL6)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL6.length());
																color = 6;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL7)) {
																color = 7;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL7.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL8)) {
																color = 8;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL8.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL9)) {
																color = 9;
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL9.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL10)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL10.length());
																color = 10;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATCOL11)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATCOL11.length());
																color = 11;
															}
														}
														@Pc(5943) byte effect = 0;
														chatTypedLowercase = chatTyped.toLowerCase();
														if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT1)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT1.length());
															effect = 1;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT2)) {
															effect = 2;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT2.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT3)) {
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT3.length());
															effect = 3;
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECT4)) {
															effect = 4;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECT4.length());
														} else if (chatTypedLowercase.startsWith(LocalizedText.STABLE_CHATEFFECTC5)) {
															effect = 5;
															chatTyped = chatTyped.substring(LocalizedText.STABLE_CHATEFFECTC5.length());
														} else if (Static141.anInt3470 != 0) {
															if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT1)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT1.length());
																effect = 1;
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT2)) {
																effect = 2;
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT2.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT3)) {
																effect = 3;
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT3.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT4)) {
																effect = 4;
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT4.length());
															} else if (chatTypedLowercase.startsWith(LocalizedText.CHATEFFECT5)) {
																chatTyped = chatTyped.substring(LocalizedText.CHATEFFECT5.length());
																effect = 5;
															}
														}
														Static6.outboundBuffer.pIsaac1(237);
														Static6.outboundBuffer.p1(0);
														start = Static6.outboundBuffer.position;
														Static6.outboundBuffer.p1(color);
														Static6.outboundBuffer.p1(effect);
														Static146.method2748(Static6.outboundBuffer, chatTyped);
														Static6.outboundBuffer.p1len(Static6.outboundBuffer.position - start);
														continue;
													}
													Static127.method2470(chatTyped);
													continue;
												}
												if (scriptOpcode == 5009) {
													local26 -= 2;
													chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
													chatTyped = Static3.scriptStringValues[local26];
													if (Static191.staffModLevel != 0 || (!Static124.aBoolean157 || Static207.parentalChatConsent) && !Static86.aBoolean129) {
														Static6.outboundBuffer.pIsaac1(201);
														Static6.outboundBuffer.p1(0);
														childCount = Static6.outboundBuffer.position;
														Static6.outboundBuffer.p8(chatTyped.toBase37());
														Static146.method2748(Static6.outboundBuffer, chatTypedLowercase);
														Static6.outboundBuffer.p1len(Static6.outboundBuffer.position - childCount);
													}
													continue;
												}
												if (scriptOpcode == 5010) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													chatTypedLowercase = null;
													if (interfaceData < 100) {
														chatTypedLowercase = Static153.aClass100Array112[interfaceData];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = Static72.aClass100_447;
													}
													Static3.scriptStringValues[local26++] = chatTypedLowercase;
													continue;
												}
												if (scriptOpcode == 5011) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													chatTypedLowercase = null;
													if (interfaceData < 100) {
														chatTypedLowercase = Static64.aClass100Array62[interfaceData];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = Static72.aClass100_447;
													}
													Static3.scriptStringValues[local26++] = chatTypedLowercase;
													continue;
												}
												if (scriptOpcode == 5012) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													interfaceType = -1;
													if (interfaceData < 100) {
														interfaceType = Static241.anIntArray521[interfaceData];
													}
													Static254.scriptIntValues[intValueIndex++] = interfaceType;
													continue;
												}
												if (scriptOpcode == 5015) {
													if (Static173.localPlayer == null || Static173.localPlayer.name == null) {
														chatTyped = Static186.username;
													} else {
														chatTyped = Static173.localPlayer.getName();
													}
													Static3.scriptStringValues[local26++] = chatTyped;
													continue;
												}
												if (scriptOpcode == 5016) {
													Static254.scriptIntValues[intValueIndex++] = Static84.anInt2256;
													continue;
												}
												if (scriptOpcode == 5017) {
													Static254.scriptIntValues[intValueIndex++] = Static62.anInt1941;
													continue;
												}
												if (scriptOpcode == 5050) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static3.scriptStringValues[local26++] = Static235.method4045(interfaceData).aClass100_79;
													continue;
												}
												@Pc(6378) Class3_Sub2_Sub2 local6378;
												if (scriptOpcode == 5051) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													local6378 = Static235.method4045(interfaceData);
													if (local6378.anIntArray30 == null) {
														Static254.scriptIntValues[intValueIndex++] = 0;
													} else {
														Static254.scriptIntValues[intValueIndex++] = local6378.anIntArray30.length;
													}
													continue;
												}
												if (scriptOpcode == 5052) {
													intValueIndex -= 2;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													@Pc(6416) Class3_Sub2_Sub2 local6416 = Static235.method4045(interfaceData);
													local652 = local6416.anIntArray30[interfaceType];
													Static254.scriptIntValues[intValueIndex++] = local652;
													continue;
												}
												if (scriptOpcode == 5053) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													local6378 = Static235.method4045(interfaceData);
													if (local6378.anIntArray32 == null) {
														Static254.scriptIntValues[intValueIndex++] = 0;
													} else {
														Static254.scriptIntValues[intValueIndex++] = local6378.anIntArray32.length;
													}
													continue;
												}
												if (scriptOpcode == 5054) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static254.scriptIntValues[intValueIndex++] = Static235.method4045(interfaceData).anIntArray32[interfaceType];
													continue;
												}
												if (scriptOpcode == 5055) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static3.scriptStringValues[local26++] = Static230.list(interfaceData).method769();
													continue;
												}
												if (scriptOpcode == 5056) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													@Pc(6527) QuickChatPhraseType local6527 = Static230.list(interfaceData);
													if (local6527.autoResponses == null) {
														Static254.scriptIntValues[intValueIndex++] = 0;
													} else {
														Static254.scriptIntValues[intValueIndex++] = local6527.autoResponses.length;
													}
													continue;
												}
												if (scriptOpcode == 5057) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static254.scriptIntValues[intValueIndex++] = Static230.list(interfaceData).autoResponses[interfaceType];
													continue;
												}
												if (scriptOpcode == 5058) {
													Static122.aQuickChatPhrase_1 = new QuickChatPhrase();
													intValueIndex--;
													Static122.aQuickChatPhrase_1.anInt439 = Static254.scriptIntValues[intValueIndex];
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1 = Static230.list(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.anIntArray33 = new int[Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.method767()];
													continue;
												}
												if (scriptOpcode == 5059) {
													Static6.outboundBuffer.pIsaac1(167);
													Static6.outboundBuffer.p1(0);
													interfaceData = Static6.outboundBuffer.position;
													Static6.outboundBuffer.p1(0);
													Static6.outboundBuffer.p2(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.putDynamics(Static6.outboundBuffer, Static122.aQuickChatPhrase_1.anIntArray33);
													Static6.outboundBuffer.p1len(Static6.outboundBuffer.position - interfaceData);
													continue;
												}
												if (scriptOpcode == 5060) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													Static6.outboundBuffer.pIsaac1(178);
													Static6.outboundBuffer.p1(0);
													interfaceType = Static6.outboundBuffer.position;
													Static6.outboundBuffer.p8(chatTyped.toBase37());
													Static6.outboundBuffer.p2(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.putDynamics(Static6.outboundBuffer, Static122.aQuickChatPhrase_1.anIntArray33);
													Static6.outboundBuffer.p1len(Static6.outboundBuffer.position - interfaceType);
													continue;
												}
												if (scriptOpcode == 5061) {
													Static6.outboundBuffer.pIsaac1(167);
													Static6.outboundBuffer.p1(0);
													interfaceData = Static6.outboundBuffer.position;
													Static6.outboundBuffer.p1(1);
													Static6.outboundBuffer.p2(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.putDynamics(Static6.outboundBuffer, Static122.aQuickChatPhrase_1.anIntArray33);
													Static6.outboundBuffer.p1len(Static6.outboundBuffer.position - interfaceData);
													continue;
												}
												if (scriptOpcode == 5062) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static254.scriptIntValues[intValueIndex++] = Static235.method4045(interfaceData).anIntArray31[interfaceType];
													continue;
												}
												if (scriptOpcode == 5063) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static254.scriptIntValues[intValueIndex++] = Static235.method4045(interfaceData).anIntArray29[interfaceType];
													continue;
												}
												if (scriptOpcode == 5064) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													if (interfaceType == -1) {
														Static254.scriptIntValues[intValueIndex++] = -1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = Static235.method4045(interfaceData).method469(interfaceType);
													}
													continue;
												}
												if (scriptOpcode == 5065) {
													intValueIndex -= 2;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													if (interfaceType == -1) {
														Static254.scriptIntValues[intValueIndex++] = -1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = Static235.method4045(interfaceData).method466(interfaceType);
													}
													continue;
												}
												if (scriptOpcode == 5066) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													Static254.scriptIntValues[intValueIndex++] = Static230.list(interfaceData).method767();
													continue;
												}
												if (scriptOpcode == 5067) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													childCount = Static230.list(interfaceData).method765(interfaceType);
													Static254.scriptIntValues[intValueIndex++] = childCount;
													continue;
												}
												if (scriptOpcode == 5068) {
													intValueIndex -= 2;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													Static122.aQuickChatPhrase_1.anIntArray33[interfaceData] = interfaceType;
													continue;
												}
												if (scriptOpcode == 5069) {
													intValueIndex -= 2;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													Static122.aQuickChatPhrase_1.anIntArray33[interfaceData] = interfaceType;
													continue;
												}
												if (scriptOpcode == 5070) {
													intValueIndex -= 3;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													childCount = Static254.scriptIntValues[intValueIndex + 2];
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													@Pc(6996) QuickChatPhraseType local6996 = Static230.list(interfaceData);
													if (local6996.method765(interfaceType) != 0) {
														throw new RuntimeException("bad command");
													}
													Static254.scriptIntValues[intValueIndex++] = local6996.method764(childCount, interfaceType);
													continue;
												}
												if (scriptOpcode == 5071) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													intValueIndex--;
													local1552 = Static254.scriptIntValues[intValueIndex] == 1;
													Static24.method668(local1552, chatTyped);
													Static254.scriptIntValues[intValueIndex++] = Static111.anInt2905;
													continue;
												}
												if (scriptOpcode == 5072) {
													if (Static169.aShortArray52 != null && Static67.anInt3356 < Static111.anInt2905) {
														Static254.scriptIntValues[intValueIndex++] = Static169.aShortArray52[Static67.anInt3356++] & 0xFFFF;
														continue;
													}
													Static254.scriptIntValues[intValueIndex++] = -1;
													continue;
												}
												if (scriptOpcode == 5073) {
													Static67.anInt3356 = 0;
													continue;
												}
											} else if (scriptOpcode < 5200) {
												if (scriptOpcode == 5100) {
													if (Static187.pressedKeys[86]) {
														Static254.scriptIntValues[intValueIndex++] = 1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = 0;
													}
													continue;
												}
												if (scriptOpcode == 5101) {
													if (Static187.pressedKeys[82]) {
														Static254.scriptIntValues[intValueIndex++] = 1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = 0;
													}
													continue;
												}
												if (scriptOpcode == 5102) {
													if (Static187.pressedKeys[81]) {
														Static254.scriptIntValues[intValueIndex++] = 1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = 0;
													}
													continue;
												}
											} else {
												@Pc(7566) boolean local7566;
												if (scriptOpcode < 5300) {
													if (scriptOpcode == 5200) {
														intValueIndex--;
														Static155.method2940(Static254.scriptIntValues[intValueIndex]);
														continue;
													}
													if (scriptOpcode == 5201) {
														Static254.scriptIntValues[intValueIndex++] = Static91.getZoom();
														continue;
													}
													if (scriptOpcode == 5202) {
														intValueIndex--;
														Static258.method4444(Static254.scriptIntValues[intValueIndex]);
														continue;
													}
													if (scriptOpcode == 5203) {
														local26--;
														Static3.method4656(Static3.scriptStringValues[local26]);
														continue;
													}
													if (scriptOpcode == 5204) {
														Static3.scriptStringValues[local26 - 1] = Static211.method923(Static3.scriptStringValues[local26 - 1]);
														continue;
													}
													if (scriptOpcode == 5205) {
														local26--;
														Static90.method1853(Static3.scriptStringValues[local26]);
														continue;
													}
													if (scriptOpcode == 5206) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														@Pc(7264) Map local7264 = Static29.method803(interfaceData >> 14 & 0x3FFF, interfaceData & 0x3FFF);
														if (local7264 == null) {
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
														} else {
															Static3.scriptStringValues[local26++] = local7264.aClass100_138;
														}
														continue;
													}
													@Pc(7293) Map local7293;
													if (scriptOpcode == 5207) {
														local26--;
														local7293 = Static124.method2434(Static3.scriptStringValues[local26]);
														if (local7293 != null && local7293.aClass100_137 != null) {
															Static3.scriptStringValues[local26++] = local7293.aClass100_137;
															continue;
														}
														Static3.scriptStringValues[local26++] = Static72.aClass100_447;
														continue;
													}
													if (scriptOpcode == 5208) {
														Static254.scriptIntValues[intValueIndex++] = Static89.anInt2387;
														Static254.scriptIntValues[intValueIndex++] = Static37.anInt1176;
														continue;
													}
													if (scriptOpcode == 5209) {
														Static254.scriptIntValues[intValueIndex++] = Static158.anInt3846 + Static13.anInt435;
														Static254.scriptIntValues[intValueIndex++] = Static2.anInt13 + Static181.anInt4296 - Static28.anInt919 - 1;
														continue;
													}
													if (scriptOpcode == 5210) {
														local7293 = Static214.method4361();
														if (local7293 == null) {
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static254.scriptIntValues[intValueIndex++] = 0;
														} else {
															Static254.scriptIntValues[intValueIndex++] = local7293.anInt769 * 64;
															Static254.scriptIntValues[intValueIndex++] = local7293.anInt764 * 64;
														}
														continue;
													}
													if (scriptOpcode == 5211) {
														local7293 = Static214.method4361();
														if (local7293 == null) {
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static254.scriptIntValues[intValueIndex++] = 0;
														} else {
															Static254.scriptIntValues[intValueIndex++] = local7293.anInt770 - local7293.anInt763;
															Static254.scriptIntValues[intValueIndex++] = local7293.anInt758 - local7293.anInt771;
														}
														continue;
													}
													if (scriptOpcode == 5212) {
														interfaceData = Static118.method2352();
														childCount = 0;
														if (interfaceData == -1) {
															chatTypedLowercase = Static72.aClass100_447;
														} else {
															chatTypedLowercase = Static203.aMapElementTypeList_1.aClass100Array153[interfaceData];
															childCount = Static203.aMapElementTypeList_1.method3894(interfaceData);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(Static67.aClass100_639, Static5.aClass100_10);
														Static3.scriptStringValues[local26++] = chatTypedLowercase;
														Static254.scriptIntValues[intValueIndex++] = childCount;
														continue;
													}
													if (scriptOpcode == 5213) {
														childCount = 0;
														interfaceData = Static119.method2385();
														if (interfaceData == -1) {
															chatTypedLowercase = Static72.aClass100_447;
														} else {
															chatTypedLowercase = Static203.aMapElementTypeList_1.aClass100Array153[interfaceData];
															childCount = Static203.aMapElementTypeList_1.method3894(interfaceData);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(Static67.aClass100_639, Static5.aClass100_10);
														Static3.scriptStringValues[local26++] = chatTypedLowercase;
														Static254.scriptIntValues[intValueIndex++] = childCount;
														continue;
													}
													if (scriptOpcode == 5214) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														Static80.method3616(interfaceData >> 14 & 0x3FFF, interfaceData & 0x3FFF);
														continue;
													}
													if (scriptOpcode == 5215) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														local26--;
														chatTypedLowercase = Static3.scriptStringValues[local26];
														local7566 = false;
														@Pc(7577) SecondaryLinkedList local7577 = Static183.method3333(interfaceData >> 14 & 0x3FFF, interfaceData & 0x3FFF);
														for (@Pc(7582) Map local7582 = (Map) local7577.head(); local7582 != null; local7582 = (Map) local7577.prev()) {
															if (local7582.aClass100_138.equalsIgnoreCase(chatTypedLowercase)) {
																local7566 = true;
																break;
															}
														}
														if (local7566) {
															Static254.scriptIntValues[intValueIndex++] = 1;
														} else {
															Static254.scriptIntValues[intValueIndex++] = 0;
														}
														continue;
													}
													if (scriptOpcode == 5216) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														Static253.method4332(interfaceData);
														continue;
													}
													if (scriptOpcode == 5217) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (Static90.method1855(interfaceData)) {
															Static254.scriptIntValues[intValueIndex++] = 1;
														} else {
															Static254.scriptIntValues[intValueIndex++] = 0;
														}
														continue;
													}
													if (scriptOpcode == 5218) {
														local7293 = Static214.method4361();
														if (local7293 == null) {
															Static254.scriptIntValues[intValueIndex++] = -1;
														} else {
															Static254.scriptIntValues[intValueIndex++] = local7293.anInt772;
														}
														continue;
													}
													if (scriptOpcode == 5219) {
														local26--;
														Static44.method1149(Static3.scriptStringValues[local26]);
														continue;
													}
													if (scriptOpcode == 5220) {
														Static254.scriptIntValues[intValueIndex++] = Static41.anInt1309 == 100 ? 1 : 0;
														continue;
													}
												} else if (scriptOpcode < 5400) {
													if (scriptOpcode == 5300) {
														intValueIndex -= 2;
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														interfaceData = Static254.scriptIntValues[intValueIndex];
														Static241.method4540(false, 3, interfaceData, interfaceType);
														Static254.scriptIntValues[intValueIndex++] = Static69.aFrame2 == null ? 0 : 1;
														continue;
													}
													if (scriptOpcode == 5301) {
														if (Static69.aFrame2 != null) {
															Static241.method4540(false, Static214.anInt5581, -1, -1);
														}
														continue;
													}
													if (scriptOpcode == 5302) {
														@Pc(7780) Class114[] local7780 = Static3.method4660();
														Static254.scriptIntValues[intValueIndex++] = local7780.length;
														continue;
													}
													if (scriptOpcode == 5303) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														@Pc(7800) Class114[] local7800 = Static3.method4660();
														Static254.scriptIntValues[intValueIndex++] = local7800[interfaceData].anInt4248;
														Static254.scriptIntValues[intValueIndex++] = local7800[interfaceData].anInt4250;
														continue;
													}
													if (scriptOpcode == 5305) {
														interfaceType = Static22.anInt729;
														interfaceData = Static114.anInt5831;
														childCount = -1;
														@Pc(7833) Class114[] local7833 = Static3.method4660();
														for (start = 0; start < local7833.length; start++) {
															@Pc(7843) Class114 local7843 = local7833[start];
															if (interfaceData == local7843.anInt4248 && local7843.anInt4250 == interfaceType) {
																childCount = start;
																break;
															}
														}
														Static254.scriptIntValues[intValueIndex++] = childCount;
														continue;
													}
													if (scriptOpcode == 5306) {
														Static254.scriptIntValues[intValueIndex++] = Static144.method2736();
														continue;
													}
													if (scriptOpcode == 5307) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Static241.method4540(false, interfaceData, -1, -1);
														continue;
													}
													if (scriptOpcode == 5308) {
														Static254.scriptIntValues[intValueIndex++] = Static214.anInt5581;
														continue;
													}
													if (scriptOpcode == 5309) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Static214.anInt5581 = interfaceData;
														Static203.method3663(GameShell.signLink);
														continue;
													}
												} else if (scriptOpcode < 5500) {
													if (scriptOpcode == 5400) {
														local26 -= 2;
														chatTyped = Static3.scriptStringValues[local26];
														chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
														intValueIndex--;
														childCount = Static254.scriptIntValues[intValueIndex];
														Static6.outboundBuffer.pIsaac1(117);
														Static6.outboundBuffer.p1(Static229.method3937(chatTyped) + Static229.method3937(chatTypedLowercase) + 1);
														Static6.outboundBuffer.pjstr(chatTyped);
														Static6.outboundBuffer.pjstr(chatTypedLowercase);
														Static6.outboundBuffer.p1(childCount);
														continue;
													}
													if (scriptOpcode == 5401) {
														intValueIndex -= 2;
														Static259.aShortArray88[Static254.scriptIntValues[intValueIndex]] = (short) Static105.hslToRgb(Static254.scriptIntValues[intValueIndex + 1]);
														Static211.method924();
														Static269.method2172();
														Static278.method4649();
														Static11.method443();
														Static87.method1807();
														continue;
													}
													if (scriptOpcode == 5405) {
														intValueIndex -= 2;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														if (interfaceData >= 0 && interfaceData < 2) {
															Static107.anIntArrayArrayArray9[interfaceData] = new int[interfaceType << 1][4];
														}
														continue;
													}
													if (scriptOpcode == 5406) {
														intValueIndex -= 7;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														interfaceType = Static254.scriptIntValues[intValueIndex + 1] << 1;
														local652 = Static254.scriptIntValues[intValueIndex + 3];
														childCount = Static254.scriptIntValues[intValueIndex + 2];
														start = Static254.scriptIntValues[intValueIndex + 4];
														@Pc(8108) int local8108 = Static254.scriptIntValues[intValueIndex + 6];
														childId = Static254.scriptIntValues[intValueIndex + 5];
														if (interfaceData >= 0 && interfaceData < 2 && Static107.anIntArrayArrayArray9[interfaceData] != null && interfaceType >= 0 && Static107.anIntArrayArrayArray9[interfaceData].length > interfaceType) {
															Static107.anIntArrayArrayArray9[interfaceData][interfaceType] = new int[] { (childCount >> 14 & 0x3FFF) * 128, local652, (childCount & 0x3FFF) * 128, local8108 };
															Static107.anIntArrayArrayArray9[interfaceData][interfaceType + 1] = new int[] { (start >> 14 & 0x3FFF) * 128, childId, (start & 0x3FFF) * 128 };
														}
														continue;
													}
													if (scriptOpcode == 5407) {
														intValueIndex--;
														interfaceData = Static107.anIntArrayArrayArray9[Static254.scriptIntValues[intValueIndex]].length >> 1;
														Static254.scriptIntValues[intValueIndex++] = interfaceData;
														continue;
													}
													if (scriptOpcode == 5411) {
														if (Static69.aFrame2 != null) {
															Static241.method4540(false, Static214.anInt5581, -1, -1);
														}
														if (Static39.frame == null) {
															Static169.openUrl(Static15.method479(), false);
														} else {
															System.exit(0);
														}
														continue;
													}
													if (scriptOpcode == 5419) {
														chatTyped = Static72.aClass100_447;
														if (Static232.aClass212_5 != null) {
															chatTyped = Static181.method3341(Static232.aClass212_5.intArg2);
															try {
																if (Static232.aClass212_5.result != null) {
																	@Pc(8281) byte[] local8281 = ((String) Static232.aClass212_5.result).getBytes("ISO-8859-1");
																	chatTyped = Static10.decodeString(local8281, local8281.length, 0);
																}
															} catch (@Pc(8290) UnsupportedEncodingException local8290) {
															}
														}
														Static3.scriptStringValues[local26++] = chatTyped;
														continue;
													}
													if (scriptOpcode == 5420) {
														Static254.scriptIntValues[intValueIndex++] = SignLink.anInt5928 == 3 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 5421) {
														if (Static69.aFrame2 != null) {
															Static241.method4540(false, Static214.anInt5581, -1, -1);
														}
														intValueIndex--;
														local1552 = Static254.scriptIntValues[intValueIndex] == 1;
														local26--;
														chatTyped = Static3.scriptStringValues[local26];
														@Pc(8356) JString local8356 = Static34.method882(new JString[] { Static15.method479(), chatTyped });
														if (Static39.frame != null || local1552 && SignLink.anInt5928 != 3 && SignLink.osName.startsWith("win") && !Static178.aBoolean203) {
															Static164.newTab = local1552;
															Static175.url = local8356;
															Static33.openUrlRequest = GameShell.signLink.method5131(new String(local8356.method3148(), "ISO-8859-1"));
															continue;
														}
														Static169.openUrl(local8356, local1552);
														continue;
													}
													if (scriptOpcode == 5422) {
														intValueIndex--;
														childCount = Static254.scriptIntValues[intValueIndex];
														local26 -= 2;
														chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
														chatTyped = Static3.scriptStringValues[local26];
														if (chatTyped.length() > 0) {
															if (Static103.aClass100Array88 == null) {
																Static103.aClass100Array88 = new JString[Static132.anIntArray309[Static266.game]];
															}
															Static103.aClass100Array88[childCount] = chatTyped;
														}
														if (chatTypedLowercase.length() > 0) {
															if (Static263.aClass100Array174 == null) {
																Static263.aClass100Array174 = new JString[Static132.anIntArray309[Static266.game]];
															}
															Static263.aClass100Array174[childCount] = chatTypedLowercase;
														}
														continue;
													}
													if (scriptOpcode == 5423) {
														local26--;
														Static3.scriptStringValues[local26].printToConsole();
														continue;
													}
													if (scriptOpcode == 5424) {
														intValueIndex -= 11;
														Static40.anInt1275 = Static254.scriptIntValues[intValueIndex];
														Static111.anInt2910 = Static254.scriptIntValues[intValueIndex + 1];
														Static251.anInt5457 = Static254.scriptIntValues[intValueIndex + 2];
														Static232.anInt5208 = Static254.scriptIntValues[intValueIndex + 3];
														Static55.anInt1736 = Static254.scriptIntValues[intValueIndex + 4];
														Static169.anInt4073 = Static254.scriptIntValues[intValueIndex + 5];
														Static85.anInt2261 = Static254.scriptIntValues[intValueIndex + 6];
														Static136.anInt3324 = Static254.scriptIntValues[intValueIndex + 7];
														Static254.anInt5556 = Static254.scriptIntValues[intValueIndex + 8];
														Static195.anInt4581 = Static254.scriptIntValues[intValueIndex + 9];
														Static262.anInt5752 = Static254.scriptIntValues[intValueIndex + 10];
														Static209.aClass153_86.method4506(Static55.anInt1736);
														Static209.aClass153_86.method4506(Static169.anInt4073);
														Static209.aClass153_86.method4506(Static85.anInt2261);
														Static209.aClass153_86.method4506(Static136.anInt3324);
														Static209.aClass153_86.method4506(Static254.anInt5556);
														Static261.aBoolean298 = true;
														continue;
													}
													if (scriptOpcode == 5425) {
														Static114.method4637();
														Static261.aBoolean298 = false;
														continue;
													}
													if (scriptOpcode == 5426) {
														intValueIndex--;
														Static270.anInt5794 = Static254.scriptIntValues[intValueIndex];
														continue;
													}
													if (scriptOpcode == 5427) {
														intValueIndex -= 2;
														Static169.anInt4075 = Static254.scriptIntValues[intValueIndex];
														Static225.anInt5073 = Static254.scriptIntValues[intValueIndex + 1];
														continue;
													}
												} else if (scriptOpcode < 5600) {
													if (scriptOpcode == 5500) {
														intValueIndex -= 4;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														local652 = Static254.scriptIntValues[intValueIndex + 3];
														childCount = Static254.scriptIntValues[intValueIndex + 2];
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														Static141.method2722(false, childCount, interfaceType, local652, (interfaceData & 0x3FFF) - Static142.originZ, (interfaceData >> 14 & 0x3FFF) - Static225.originX);
														continue;
													}
													if (scriptOpcode == 5501) {
														intValueIndex -= 4;
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														interfaceData = Static254.scriptIntValues[intValueIndex];
														local652 = Static254.scriptIntValues[intValueIndex + 3];
														childCount = Static254.scriptIntValues[intValueIndex + 2];
														Static260.method3849(interfaceType, (interfaceData & 0x3FFF) - Static142.originZ, childCount, (interfaceData >> 14 & 0x3FFF) - Static225.originX, local652);
														continue;
													}
													if (scriptOpcode == 5502) {
														intValueIndex -= 6;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData >= 2) {
															throw new RuntimeException();
														}
														Static155.anInt3718 = interfaceData;
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														if (Static107.anIntArrayArrayArray9[Static155.anInt3718].length >> 1 <= interfaceType + 1) {
															throw new RuntimeException();
														}
														Static127.anInt3125 = interfaceType;
														ClientScriptRunner.anInt5224 = 0;
														Static228.anInt5101 = Static254.scriptIntValues[intValueIndex + 2];
														Static114.anInt5843 = Static254.scriptIntValues[intValueIndex + 3];
														childCount = Static254.scriptIntValues[intValueIndex + 4];
														if (childCount >= 2) {
															throw new RuntimeException();
														}
														Static52.anInt1694 = childCount;
														local652 = Static254.scriptIntValues[intValueIndex + 5];
														if (Static107.anIntArrayArrayArray9[Static52.anInt1694].length >> 1 <= local652 + 1) {
															throw new RuntimeException();
														}
														Static75.anInt2119 = local652;
														Static227.anInt5096 = 3;
														continue;
													}
													if (scriptOpcode == 5503) {
														Static35.method902();
														continue;
													}
													if (scriptOpcode == 5504) {
														intValueIndex -= 2;
														Static72.orbitCameraPitch = Static254.scriptIntValues[intValueIndex];
														Static57.orbitCameraYaw = Static254.scriptIntValues[intValueIndex + 1];
														if (Static227.anInt5096 == 2) {
															Static184.cameraYaw = Static57.orbitCameraYaw;
															Static240.cameraPitch = Static72.orbitCameraPitch;
														}
														Static87.method1812();
														continue;
													}
													if (scriptOpcode == 5505) {
														Static254.scriptIntValues[intValueIndex++] = Static72.orbitCameraPitch;
														continue;
													}
													if (scriptOpcode == 5506) {
														Static254.scriptIntValues[intValueIndex++] = Static57.orbitCameraYaw;
														continue;
													}
												} else if (scriptOpcode < 5700) {
													if (scriptOpcode == 5600) {
														local26 -= 2;
														chatTyped = Static3.scriptStringValues[local26];
														chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
														intValueIndex--;
														childCount = Static254.scriptIntValues[intValueIndex];
														if (Static244.gamestate == 10 && Static219.anInt4937 == 0 && Static184.anInt4348 == 0 && Static179.step == 0 && Static82.anInt2231 == 0) {
															Static225.method3896(chatTyped, chatTypedLowercase, childCount);
														}
														continue;
													}
													if (scriptOpcode == 5601) {
														Static185.method3395();
														continue;
													}
													if (scriptOpcode == 5602) {
														if (Static184.anInt4348 == 0) {
															Static266.anInt5336 = -2;
														}
														continue;
													}
													if (scriptOpcode == 5603) {
														intValueIndex -= 4;
														if (Static244.gamestate == 10 && Static219.anInt4937 == 0 && Static184.anInt4348 == 0 && Static179.step == 0 && Static82.anInt2231 == 0) {
															Static125.method2448(Static254.scriptIntValues[intValueIndex + 2], Static254.scriptIntValues[intValueIndex + 3], Static254.scriptIntValues[intValueIndex], Static254.scriptIntValues[intValueIndex + 1]);
														}
														continue;
													}
													if (scriptOpcode == 5604) {
														local26--;
														if (Static244.gamestate == 10 && Static219.anInt4937 == 0 && Static184.anInt4348 == 0 && Static179.step == 0 && Static82.anInt2231 == 0) {
															Static78.method1691(Static3.scriptStringValues[local26].toBase37());
														}
														continue;
													}
													if (scriptOpcode == 5605) {
														intValueIndex -= 4;
														local26 -= 2;
														if (Static244.gamestate == 10 && Static219.anInt4937 == 0 && Static184.anInt4348 == 0 && Static179.step == 0 && Static82.anInt2231 == 0) {
															Static40.method1016(Static254.scriptIntValues[intValueIndex], Static254.scriptIntValues[intValueIndex + 3], Static254.scriptIntValues[intValueIndex + 1], Static3.scriptStringValues[local26 + 1], Static3.scriptStringValues[local26].toBase37(), Static254.scriptIntValues[intValueIndex + 2]);
														}
														continue;
													}
													if (scriptOpcode == 5606) {
														if (Static179.step == 0) {
															Static223.reply = -2;
														}
														continue;
													}
													if (scriptOpcode == 5607) {
														Static254.scriptIntValues[intValueIndex++] = Static266.anInt5336;
														continue;
													}
													if (scriptOpcode == 5608) {
														Static254.scriptIntValues[intValueIndex++] = PreciseSleep.anInt5202;
														continue;
													}
													if (scriptOpcode == 5609) {
														Static254.scriptIntValues[intValueIndex++] = Static223.reply;
														continue;
													}
													if (scriptOpcode == 5610) {
														for (interfaceData = 0; interfaceData < 5; interfaceData++) {
															Static3.scriptStringValues[local26++] = Static229.aClass100Array156.length > interfaceData ? Static229.aClass100Array156[interfaceData].method3125() : Static72.aClass100_447;
														}
														Static229.aClass100Array156 = null;
														continue;
													}
													if (scriptOpcode == 5611) {
														Static254.scriptIntValues[intValueIndex++] = Static204.anInt4765;
														continue;
													}
												} else if (scriptOpcode < 6100) {
													if (scriptOpcode == 6001) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 1) {
															interfaceData = 1;
														}
														if (interfaceData > 4) {
															interfaceData = 4;
														}
														Static113.anInt4609 = interfaceData;
														if (!GlRenderer.enabled || !Static178.highDetailLighting) {
															if (Static113.anInt4609 == 1) {
																Pix3D.method1911(0.9F);
															}
															if (Static113.anInt4609 == 2) {
																Pix3D.method1911(0.8F);
															}
															if (Static113.anInt4609 == 3) {
																Pix3D.method1911(0.7F);
															}
															if (Static113.anInt4609 == 4) {
																Pix3D.method1911(0.6F);
															}
														}
														if (GlRenderer.enabled) {
															Static86.method1799();
															if (!Static178.highDetailLighting) {
																Static145.method2742();
															}
														}
														Static269.method2172();
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6002) {
														intValueIndex--;
														Static53.method1293(Static254.scriptIntValues[intValueIndex] == 1);
														Static90.method1854();
														Static145.method2742();
														Static269.method2218();
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6003) {
														intValueIndex--;
														Static80.aBoolean231 = Static254.scriptIntValues[intValueIndex] == 1;
														Static269.method2218();
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6005) {
														intValueIndex--;
														Static250.aBoolean283 = Static254.scriptIntValues[intValueIndex] == 1;
														Static145.method2742();
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6006) {
														intValueIndex--;
														Static53.aBoolean99 = Static254.scriptIntValues[intValueIndex] == 1;
														((Js5GlTextureProvider) Pix3D.anInterface1_2).method3245(!Static53.aBoolean99);
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6007) {
														intValueIndex--;
														Static15.lowMemory = Static254.scriptIntValues[intValueIndex] == 1;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6008) {
														intValueIndex--;
														Static11.aBoolean15 = Static254.scriptIntValues[intValueIndex] == 1;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6009) {
														intValueIndex--;
														Static159.aBoolean189 = Static254.scriptIntValues[intValueIndex] == 1;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6010) {
														intValueIndex--;
														Static209.aBoolean240 = Static254.scriptIntValues[intValueIndex] == 1;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6011) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Static139.anInt3451 = interfaceData;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6012) {
														if (GlRenderer.enabled) {
															Static27.setMaterial(0, 0);
														}
														intValueIndex--;
														Static178.highDetailLighting = Static254.scriptIntValues[intValueIndex] == 1;
														if (GlRenderer.enabled && Static178.highDetailLighting) {
															Pix3D.method1911(0.7F);
														} else {
															if (Static113.anInt4609 == 1) {
																Pix3D.method1911(0.9F);
															}
															if (Static113.anInt4609 == 2) {
																Pix3D.method1911(0.8F);
															}
															if (Static113.anInt4609 == 3) {
																Pix3D.method1911(0.7F);
															}
															if (Static113.anInt4609 == 4) {
																Pix3D.method1911(0.6F);
															}
														}
														Static145.method2742();
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6014) {
														intValueIndex--;
														Static220.aBoolean244 = Static254.scriptIntValues[intValueIndex] == 1;
														if (GlRenderer.enabled) {
															Static145.method2742();
														}
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6015) {
														intValueIndex--;
														Static71.aBoolean107 = Static254.scriptIntValues[intValueIndex] == 1;
														if (GlRenderer.enabled) {
															Static86.method1799();
														}
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6016) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (GlRenderer.enabled) {
															Static35.canvasReplaceRecommended = true;
														}
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Static186.antialiasingMode = interfaceData;
														continue;
													}
													if (scriptOpcode == 6017) {
														intValueIndex--;
														Static99.aBoolean143 = Static254.scriptIntValues[intValueIndex] == 1;
														Static211.method930();
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6018) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0) {
															interfaceData = 0;
														}
														if (interfaceData > 127) {
															interfaceData = 127;
														}
														Static125.anInt3104 = interfaceData;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6019) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0) {
															interfaceData = 0;
														}
														if (interfaceData > 255) {
															interfaceData = 255;
														}
														if (interfaceData != Static12.anInt391) {
															if (Static12.anInt391 == 0 && BZip2State.anInt4363 != -1) {
																Static122.method2410(Static130.aClass153_47, BZip2State.anInt4363, interfaceData);
																Static144.aBoolean173 = false;
															} else if (interfaceData == 0) {
																Static241.method4548();
																Static144.aBoolean173 = false;
															} else {
																Static230.method3956(interfaceData);
															}
															Static12.anInt391 = interfaceData;
														}
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6020) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0) {
															interfaceData = 0;
														}
														if (interfaceData > 127) {
															interfaceData = 127;
														}
														Static30.anInt978 = interfaceData;
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6021) {
														intValueIndex--;
														Static127.aBoolean160 = Static254.scriptIntValues[intValueIndex] == 1;
														Static269.method2218();
														continue;
													}
													if (scriptOpcode == 6023) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0) {
															interfaceData = 0;
														}
														if (interfaceData > 2) {
															interfaceData = 2;
														}
														local1552 = false;
														if (Static238.anInt5316 < 96) {
															local1552 = true;
															interfaceData = 0;
														}
														Static76.method1645(interfaceData);
														Static203.method3663(GameShell.signLink);
														Static18.sentToServer = false;
														Static254.scriptIntValues[intValueIndex++] = local1552 ? 0 : 1;
														continue;
													}
													if (scriptOpcode == 6024) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Static102.anInt2679 = interfaceData;
														Static203.method3663(GameShell.signLink);
														continue;
													}
													if (scriptOpcode == 6028) {
														intValueIndex--;
														Static64.aBoolean111 = Static254.scriptIntValues[intValueIndex] != 0;
														Static203.method3663(GameShell.signLink);
														continue;
													}
												} else if (scriptOpcode < 6200) {
													if (scriptOpcode == 6101) {
														Static254.scriptIntValues[intValueIndex++] = Static113.anInt4609;
														continue;
													}
													if (scriptOpcode == 6102) {
														Static254.scriptIntValues[intValueIndex++] = Static138.allLevelsvisible() ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6103) {
														Static254.scriptIntValues[intValueIndex++] = Static80.aBoolean231 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6105) {
														Static254.scriptIntValues[intValueIndex++] = Static250.aBoolean283 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6106) {
														Static254.scriptIntValues[intValueIndex++] = Static53.aBoolean99 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6107) {
														Static254.scriptIntValues[intValueIndex++] = Static15.lowMemory ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6108) {
														Static254.scriptIntValues[intValueIndex++] = Static11.aBoolean15 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6109) {
														Static254.scriptIntValues[intValueIndex++] = Static159.aBoolean189 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6110) {
														Static254.scriptIntValues[intValueIndex++] = Static209.aBoolean240 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6111) {
														Static254.scriptIntValues[intValueIndex++] = Static139.anInt3451;
														continue;
													}
													if (scriptOpcode == 6112) {
														Static254.scriptIntValues[intValueIndex++] = Static178.highDetailLighting ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6114) {
														Static254.scriptIntValues[intValueIndex++] = Static220.aBoolean244 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6115) {
														Static254.scriptIntValues[intValueIndex++] = Static71.aBoolean107 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6116) {
														Static254.scriptIntValues[intValueIndex++] = Static186.antialiasingMode;
														continue;
													}
													if (scriptOpcode == 6117) {
														Static254.scriptIntValues[intValueIndex++] = Static99.aBoolean143 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6118) {
														Static254.scriptIntValues[intValueIndex++] = Static125.anInt3104;
														continue;
													}
													if (scriptOpcode == 6119) {
														Static254.scriptIntValues[intValueIndex++] = Static12.anInt391;
														continue;
													}
													if (scriptOpcode == 6120) {
														Static254.scriptIntValues[intValueIndex++] = Static30.anInt978;
														continue;
													}
													if (scriptOpcode == 6121) {
														if (GlRenderer.enabled) {
															Static254.scriptIntValues[intValueIndex++] = GlRenderer.arbMultisampleSupported ? 1 : 0;
														} else {
															Static254.scriptIntValues[intValueIndex++] = 0;
														}
														continue;
													}
													if (scriptOpcode == 6123) {
														Static254.scriptIntValues[intValueIndex++] = Static76.method1644();
														continue;
													}
													if (scriptOpcode == 6124) {
														Static254.scriptIntValues[intValueIndex++] = Static102.anInt2679;
														continue;
													}
													if (scriptOpcode == 6128) {
														Static254.scriptIntValues[intValueIndex++] = Static64.aBoolean111 ? 1 : 0;
														continue;
													}
												} else if (scriptOpcode < 6300) {
													if (scriptOpcode == 6200) {
														intValueIndex -= 2;
														Static178.aShort25 = (short) Static254.scriptIntValues[intValueIndex];
														if (Static178.aShort25 <= 0) {
															Static178.aShort25 = 256;
														}
														Static10.aShort9 = (short) Static254.scriptIntValues[intValueIndex + 1];
														if (Static10.aShort9 <= 0) {
															Static10.aShort9 = 205;
														}
														continue;
													}
													if (scriptOpcode == 6201) {
														intValueIndex -= 2;
														Static263.aShort30 = (short) Static254.scriptIntValues[intValueIndex];
														if (Static263.aShort30 <= 0) {
															Static263.aShort30 = 256;
														}
														Static187.aShort27 = (short) Static254.scriptIntValues[intValueIndex + 1];
														if (Static187.aShort27 <= 0) {
															Static187.aShort27 = 320;
														}
														continue;
													}
													if (scriptOpcode == 6202) {
														intValueIndex -= 4;
														Static153.aShort22 = (short) Static254.scriptIntValues[intValueIndex];
														if (Static153.aShort22 <= 0) {
															Static153.aShort22 = 1;
														}
														Static4.aShort1 = (short) Static254.scriptIntValues[intValueIndex + 1];
														if (Static4.aShort1 <= 0) {
															Static4.aShort1 = 32767;
														} else if (Static153.aShort22 > Static4.aShort1) {
															Static4.aShort1 = Static153.aShort22;
														}
														Static55.aShort12 = (short) Static254.scriptIntValues[intValueIndex + 2];
														if (Static55.aShort12 <= 0) {
															Static55.aShort12 = 1;
														}
														Static131.aShort21 = (short) Static254.scriptIntValues[intValueIndex + 3];
														if (Static131.aShort21 <= 0) {
															Static131.aShort21 = 32767;
														} else if (Static131.aShort21 < Static55.aShort12) {
															Static131.aShort21 = Static55.aShort12;
														}
														continue;
													}
													if (scriptOpcode == 6203) {
														Static115.method2314(Static280.aClass13_26.anInt445, 0, Static280.aClass13_26.anInt459, 0, false);
														Static254.scriptIntValues[intValueIndex++] = Static166.anInt4055;
														Static254.scriptIntValues[intValueIndex++] = Static245.anInt5377;
														continue;
													}
													if (scriptOpcode == 6204) {
														Static254.scriptIntValues[intValueIndex++] = Static263.aShort30;
														Static254.scriptIntValues[intValueIndex++] = Static187.aShort27;
														continue;
													}
													if (scriptOpcode == 6205) {
														Static254.scriptIntValues[intValueIndex++] = Static178.aShort25;
														Static254.scriptIntValues[intValueIndex++] = Static10.aShort9;
														continue;
													}
												} else if (scriptOpcode < 6400) {
													if (scriptOpcode == 6300) {
														Static254.scriptIntValues[intValueIndex++] = (int) (MonotonicTime.get() / 60000L);
														continue;
													}
													if (scriptOpcode == 6301) {
														Static254.scriptIntValues[intValueIndex++] = (int) (MonotonicTime.get() / 86400000L) - 11745;
														continue;
													}
													if (scriptOpcode == 6302) {
														intValueIndex -= 3;
														childCount = Static254.scriptIntValues[intValueIndex + 2];
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														interfaceData = Static254.scriptIntValues[intValueIndex];
														Static102.aCalendar2.clear();
														Static102.aCalendar2.set(11, 12);
														Static102.aCalendar2.set(childCount, interfaceType, interfaceData);
														Static254.scriptIntValues[intValueIndex++] = (int) (Static102.aCalendar2.getTime().getTime() / 86400000L) - 11745;
														continue;
													}
													if (scriptOpcode == 6303) {
														Static102.aCalendar2.clear();
														Static102.aCalendar2.setTime(new Date(MonotonicTime.get()));
														Static254.scriptIntValues[intValueIndex++] = Static102.aCalendar2.get(1);
														continue;
													}
													if (scriptOpcode == 6304) {
														local1552 = true;
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0) {
															local1552 = (interfaceData + 1) % 4 == 0;
														} else if (interfaceData < 1582) {
															local1552 = interfaceData % 4 == 0;
														} else if (interfaceData % 4 != 0) {
															local1552 = false;
														} else if (interfaceData % 100 != 0) {
															local1552 = true;
														} else if (interfaceData % 400 != 0) {
															local1552 = false;
														}
														Static254.scriptIntValues[intValueIndex++] = local1552 ? 1 : 0;
														continue;
													}
												} else if (scriptOpcode < 6500) {
													if (scriptOpcode == 6405) {
														Static254.scriptIntValues[intValueIndex++] = Static87.method1802() ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6406) {
														Static254.scriptIntValues[intValueIndex++] = Static267.method4527() ? 1 : 0;
														continue;
													}
												} else if (scriptOpcode < 6600) {
													if (scriptOpcode == 6500) {
														if (Static244.gamestate == 10 && Static219.anInt4937 == 0 && Static184.anInt4348 == 0 && Static179.step == 0) {
															Static254.scriptIntValues[intValueIndex++] = Static207.method3684() == -1 ? 0 : 1;
															continue;
														}
														Static254.scriptIntValues[intValueIndex++] = 1;
														continue;
													}
													@Pc(10247) GWCLocation local10247;
													@Pc(10191) GWCWorld local10191;
													if (scriptOpcode == 6501) {
														local10191 = Static18.method556();
														if (local10191 == null) {
															Static254.scriptIntValues[intValueIndex++] = -1;
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
															Static254.scriptIntValues[intValueIndex++] = 0;
														} else {
															Static254.scriptIntValues[intValueIndex++] = local10191.id;
															Static254.scriptIntValues[intValueIndex++] = local10191.flags;
															Static3.scriptStringValues[local26++] = local10191.activity;
															local10247 = local10191.getGWCLocation();
															Static254.scriptIntValues[intValueIndex++] = local10247.flag;
															Static3.scriptStringValues[local26++] = local10247.name;
															Static254.scriptIntValues[intValueIndex++] = local10191.players;
														}
														continue;
													}
													if (scriptOpcode == 6502) {
														local10191 = method1821();
														if (local10191 == null) {
															Static254.scriptIntValues[intValueIndex++] = -1;
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
															Static254.scriptIntValues[intValueIndex++] = 0;
														} else {
															Static254.scriptIntValues[intValueIndex++] = local10191.id;
															Static254.scriptIntValues[intValueIndex++] = local10191.flags;
															Static3.scriptStringValues[local26++] = local10191.activity;
															local10247 = local10191.getGWCLocation();
															Static254.scriptIntValues[intValueIndex++] = local10247.flag;
															Static3.scriptStringValues[local26++] = local10247.name;
															Static254.scriptIntValues[intValueIndex++] = local10191.players;
														}
														continue;
													}
													if (scriptOpcode == 6503) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (Static244.gamestate == 10 && Static219.anInt4937 == 0 && Static184.anInt4348 == 0 && Static179.step == 0) {
															Static254.scriptIntValues[intValueIndex++] = Static176.method3303(interfaceData) ? 1 : 0;
															continue;
														}
														Static254.scriptIntValues[intValueIndex++] = 0;
														continue;
													}
													if (scriptOpcode == 6504) {
														intValueIndex--;
														Static164.anInt3988 = Static254.scriptIntValues[intValueIndex];
														Static203.method3663(GameShell.signLink);
														continue;
													}
													if (scriptOpcode == 6505) {
														Static254.scriptIntValues[intValueIndex++] = Static164.anInt3988;
														continue;
													}
													if (scriptOpcode == 6506) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														@Pc(10440) GWCWorld local10440 = Static54.method1310(interfaceData);
														if (local10440 == null) {
															Static254.scriptIntValues[intValueIndex++] = -1;
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
															Static254.scriptIntValues[intValueIndex++] = 0;
															Static3.scriptStringValues[local26++] = Static72.aClass100_447;
															Static254.scriptIntValues[intValueIndex++] = 0;
														} else {
															Static254.scriptIntValues[intValueIndex++] = local10440.flags;
															Static3.scriptStringValues[local26++] = local10440.activity;
															@Pc(10458) GWCLocation local10458 = local10440.getGWCLocation();
															Static254.scriptIntValues[intValueIndex++] = local10458.flag;
															Static3.scriptStringValues[local26++] = local10458.name;
															Static254.scriptIntValues[intValueIndex++] = local10440.players;
														}
														continue;
													}
													if (scriptOpcode == 6507) {
														intValueIndex -= 4;
														childCount = Static254.scriptIntValues[intValueIndex + 2];
														interfaceData = Static254.scriptIntValues[intValueIndex];
														local7566 = Static254.scriptIntValues[intValueIndex + 3] == 1;
														local1552 = Static254.scriptIntValues[intValueIndex + 1] == 1;
														Static228.method3908(childCount, local1552, interfaceData, local7566);
														continue;
													}
												} else if (scriptOpcode < 6700) {
													if (scriptOpcode == 6600) {
														intValueIndex--;
														Static33.aBoolean63 = Static254.scriptIntValues[intValueIndex] == 1;
														Static203.method3663(GameShell.signLink);
														continue;
													}
													if (scriptOpcode == 6601) {
														Static254.scriptIntValues[intValueIndex++] = Static33.aBoolean63 ? 1 : 0;
														continue;
													}
												}
											}
										} else if (scriptOpcode == 4500) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local5294 = Static110.method2277(interfaceType);
											if (local5294.method2078()) {
												Static3.scriptStringValues[local26++] = Static123.method2417(interfaceData).method2802(local5294.aClass100_544, interfaceType);
											} else {
												Static254.scriptIntValues[intValueIndex++] = Static123.method2417(interfaceData).method2798(interfaceType, local5294.anInt2667);
											}
											continue;
										}
									} else if (scriptOpcode == 4400) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										local5294 = Static110.method2277(interfaceType);
										if (local5294.method2078()) {
											Static3.scriptStringValues[local26++] = Static271.get(interfaceData).getParam(local5294.aClass100_544, interfaceType);
										} else {
											Static254.scriptIntValues[intValueIndex++] = Static271.get(interfaceData).getParam(local5294.anInt2667, interfaceType);
										}
										continue;
									}
								} else {
									if (scriptOpcode == 4100) {
										// append_num
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static3.scriptStringValues[local26++] = Static34.method882(new JString[] { chatTyped, Static123.method2423(interfaceType) });
										continue;
									}
									if (scriptOpcode == 4101) {
										// append
										local26 -= 2;
										chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
										chatTyped = Static3.scriptStringValues[local26];
										Static3.scriptStringValues[local26++] = Static34.method882(new JString[] { chatTyped, chatTypedLowercase });
										continue;
									}
									if (scriptOpcode == 4102) {
										// append_signnum
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static3.scriptStringValues[local26++] = Static34.method882(new JString[] { chatTyped, Static110.method2285(interfaceType) });
										continue;
									}
									if (scriptOpcode == 4103) {
										// lowercase
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static3.scriptStringValues[local26++] = chatTyped.toLowerCase();
										continue;
									}
									if (scriptOpcode == 4104) {
										// fromdate
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										@Pc(11770) long local11770 = (long) interfaceData * 86400000L + 1014768000000L;
										Static102.aCalendar2.setTime(new Date(local11770));
										local652 = Static102.aCalendar2.get(5);
										start = Static102.aCalendar2.get(2);
										childId = Static102.aCalendar2.get(1);
										Static3.scriptStringValues[local26++] = Static34.method882(new JString[] { Static123.method2423(local652), Static163.aClass100_767, Static34.aClass100Array40[start], Static163.aClass100_767, Static123.method2423(childId) });
										continue;
									}
									if (scriptOpcode == 4105) {
										// text_gender
										local26 -= 2;
										chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
										chatTyped = Static3.scriptStringValues[local26];
										if (Static173.localPlayer.model != null && Static173.localPlayer.model.aBoolean141) {
											Static3.scriptStringValues[local26++] = chatTypedLowercase;
											continue;
										}
										Static3.scriptStringValues[local26++] = chatTyped;
										continue;
									}
									if (scriptOpcode == 4106) {
										// tostring
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static3.scriptStringValues[local26++] = Static123.method2423(interfaceData);
										continue;
									}
									if (scriptOpcode == 4107) {
										// compare
										local26 -= 2;
										Static254.scriptIntValues[intValueIndex++] = Static3.scriptStringValues[local26].method3126(Static3.scriptStringValues[local26 + 1]);
										continue;
									}
									if (scriptOpcode == 4108) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										intValueIndex -= 2;
										childCount = Static254.scriptIntValues[intValueIndex + 1];
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static148.method2768(childCount).method2860(chatTyped, interfaceType);
										continue;
									}
									if (scriptOpcode == 4109) {
										intValueIndex -= 2;
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										childCount = Static254.scriptIntValues[intValueIndex + 1];
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static148.method2768(childCount).method2856(chatTyped, interfaceType);
										continue;
									}
									if (scriptOpcode == 4110) {
										local26 -= 2;
										chatTyped = Static3.scriptStringValues[local26];
										chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
										intValueIndex--;
										if (Static254.scriptIntValues[intValueIndex] == 1) {
											Static3.scriptStringValues[local26++] = chatTyped;
										} else {
											Static3.scriptStringValues[local26++] = chatTypedLowercase;
										}
										continue;
									}
									if (scriptOpcode == 4111) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Static3.scriptStringValues[local26++] = Static218.method2862(chatTyped);
										continue;
									}
									if (scriptOpcode == 4112) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										if (interfaceType == -1) {
											throw new RuntimeException("null char");
										}
										Static3.scriptStringValues[local26++] = chatTyped.method3128(interfaceType);
										continue;
									}
									if (scriptOpcode == 4113) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static273.method3213(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4114) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static83.method433(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4115) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static258.method4428(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4116) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static24.method671(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4117) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										if (chatTyped == null) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = chatTyped.length();
										}
										continue;
									}
									if (scriptOpcode == 4118) {
										intValueIndex -= 2;
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										interfaceType = Static254.scriptIntValues[intValueIndex];
										childCount = Static254.scriptIntValues[intValueIndex + 1];
										Static3.scriptStringValues[local26++] = chatTyped.substring(childCount, interfaceType);
										continue;
									}
									if (scriptOpcode == 4119) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										chatTypedLowercase = Static87.method1804(chatTyped.length());
										@Pc(12220) boolean local12220 = false;
										for (local652 = 0; local652 < chatTyped.length(); local652++) {
											start = chatTyped.method3149(local652);
											if (start == 60) {
												local12220 = true;
											} else if (start == 62) {
												local12220 = false;
											} else if (!local12220) {
												chatTypedLowercase.method3152(start);
											}
										}
										chatTypedLowercase.method3156();
										Static3.scriptStringValues[local26++] = chatTypedLowercase;
										continue;
									}
									if (scriptOpcode == 4120) {
										intValueIndex -= 2;
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										interfaceType = Static254.scriptIntValues[intValueIndex];
										childCount = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = chatTyped.method3135(interfaceType, childCount);
										continue;
									}
									if (scriptOpcode == 4121) {
										local26 -= 2;
										chatTyped = Static3.scriptStringValues[local26];
										chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
										intValueIndex--;
										childCount = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = chatTyped.method3146(chatTypedLowercase, childCount);
										continue;
									}
									if (scriptOpcode == 4122) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = PreciseSleep.method3984(interfaceData);
										continue;
									}
									if (scriptOpcode == 4123) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static143.method2733(interfaceData);
										continue;
									}
									if (scriptOpcode == 4124) {
										intValueIndex--;
										local12388 = Static254.scriptIntValues[intValueIndex] != 0;
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static3.scriptStringValues[local26++] = Static182.method3360(Static141.anInt3470, local12388, 0, (long) interfaceType);
										continue;
									}
								}
							}
						} else {
							if (scriptOpcode < 2000) {
								local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
							} else {
								intValueIndex--;
								local1182 = Component.getComponent(Static254.scriptIntValues[intValueIndex]);
								scriptOpcode -= 1000;
							}
							if (scriptOpcode == 1000) {
								// setposition
								intValueIndex -= 4;
								local1182.baseX = Static254.scriptIntValues[intValueIndex];
								local1182.baseY = Static254.scriptIntValues[intValueIndex + 1];
								childCount = Static254.scriptIntValues[intValueIndex + 3];
								if (childCount < 0) {
									childCount = 0;
								} else if (childCount > 5) {
									childCount = 5;
								}
								interfaceType = Static254.scriptIntValues[intValueIndex + 2];
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 5) {
									interfaceType = 5;
								}
								local1182.xMode = (byte) childCount;
								local1182.yMode = (byte) interfaceType;
								Static43.method1143(local1182);
								Static74.method1625(local1182);
								if (local1182.componentId == -1) {
									Static280.method4675(local1182.anInt507);
								}
								continue;
							}
							if (scriptOpcode == 1001) {
								// setsize
								intValueIndex -= 4;
								local1182.baseWidth = Static254.scriptIntValues[intValueIndex];
								local1182.anInt488 = Static254.scriptIntValues[intValueIndex + 1];
								local1182.anInt451 = 0;
								local1182.anInt526 = 0;
								interfaceType = Static254.scriptIntValues[intValueIndex + 2];
								childCount = Static254.scriptIntValues[intValueIndex + 3];
								if (childCount < 0) {
									childCount = 0;
								} else if (childCount > 4) {
									childCount = 4;
								}
								local1182.aByte3 = (byte) childCount;
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 4) {
									interfaceType = 4;
								}
								local1182.aByte5 = (byte) interfaceType;
								Static43.method1143(local1182);
								Static74.method1625(local1182);
								if (local1182.INVENTORY == 0) {
									Static17.method531(local1182, false);
								}
								continue;
							}
							if (scriptOpcode == 1003) {
								// sethide
								intValueIndex--;
								local1552 = Static254.scriptIntValues[intValueIndex] == 1;
								if (local1552 != local1182.hidden) {
									local1182.hidden = local1552;
									Static43.method1143(local1182);
								}
								if (local1182.componentId == -1) {
									Static93.method1906(local1182.anInt507);
								}
								continue;
							}
							if (scriptOpcode == 1004) {
								// setaspect
								intValueIndex -= 2;
								local1182.anInt473 = Static254.scriptIntValues[intValueIndex];
								local1182.anInt442 = Static254.scriptIntValues[intValueIndex + 1];
								Static43.method1143(local1182);
								Static74.method1625(local1182);
								if (local1182.INVENTORY == 0) {
									Static17.method531(local1182, false);
								}
								continue;
							}
							if (scriptOpcode == 1005) {
								intValueIndex--;
								local1182.aBoolean29 = Static254.scriptIntValues[intValueIndex] == 1;
								continue;
							}
						}
					}
				}
				throw new IllegalStateException();
			}
		} catch (@Pc(14378) Exception local14378) {
			if (clientScript.aClass100_880 == null) {
				if (client.modeWhere != 0) {
					Static103.addMessage(Static72.aClass100_447, 0, Static136.aClass100_633);
				}
				Static89.report("CS2 - scr:" + clientScript.nodeId + " op:" + local44, local14378);
			} else {
				@Pc(14385) JString local14385 = Static87.method1804(30);
				local14385.method3113(Static219.aClass100_928).method3113(clientScript.aClass100_880);
				for (listenersIndex = Static138.invokedScriptIndex - 1; listenersIndex >= 0; listenersIndex--) {
					local14385.method3113(Static40.aClass100_253).method3113(Static67.invokedScripts[listenersIndex].script.aClass100_880);
				}
				if (local44 == 40) {
					listenersIndex = local33[scriptIndex];
					local14385.method3113(Static176.aClass100_802).method3113(Static123.method2423(listenersIndex));
				}
				if (client.modeWhere != 0) {
					Static103.addMessage(Static72.aClass100_447, 0, Static34.method882(new JString[] { Static167.aClass100_780, clientScript.aClass100_880 }));
				}
				Static89.report("CS2 - scr:" + clientScript.nodeId + " op:" + local44 + new String(local14385.method3148()), local14378);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!h", name = "a", descriptor = "(I)Lclient!ba;")
	public static GWCWorld method1821() {
		return Static101.aClass10_Sub1Array1.length > Static51.anInt1682 ? Static101.aClass10_Sub1Array1[Static51.anInt1682++] : null;
	}

	@OriginalMember(owner = "runetek4.client!h", name = "a", descriptor = "(Ljava/awt/runetek4.Component;Z)V")
	public static void start(@OriginalArg(0) java.awt.Component arg0) {
		arg0.addMouseListener(Static93.aClass150_1);
		arg0.addMouseMotionListener(Static93.aClass150_1);
		arg0.addFocusListener(Static93.aClass150_1);
	}
}