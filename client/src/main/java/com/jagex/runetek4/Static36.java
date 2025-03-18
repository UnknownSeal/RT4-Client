package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.input.Keyboard;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static36 {

	@OriginalMember(owner = "client!runetek4.client", name = "W", descriptor = "I")
	public static int anInt1165;

	@OriginalMember(owner = "client!runetek4.client", name = "kb", descriptor = "[[Lclient!hg;")
	public static Class3_Sub14[][] aClass3_Sub14ArrayArray1;

	@OriginalMember(owner = "client!runetek4.client", name = "lb", descriptor = "[I")
	public static int[] anIntArray84;

	@OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "(Lclient!be;)Lclient!be;")
	public static Component method938(@OriginalArg(0) Component arg0) {
		@Pc(4) int local4 = InterfaceList.getServerActiveProperties(arg0).method505();
		if (local4 == 0) {
			return null;
		}
		for (@Pc(10) int local10 = 0; local10 < local4; local10++) {
			arg0 = InterfaceList.getComponent(arg0.overlayer);
			if (arg0 == null) {
				return null;
			}
		}
		return arg0;
	}

	@OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "([Lclient!be;IIIIIII)V")
	public static void method946(@OriginalArg(0) Component[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		for (@Pc(1) int local1 = 0; local1 < arg0.length; local1++) {
			@Pc(9) Component local9 = arg0[local1];
			if (local9 != null && local9.overlayer == arg1 && (!local9.if3 || local9.type == 0 || local9.aBoolean25 || InterfaceList.getServerActiveProperties(local9).anInt546 != 0 || local9 == ClientScriptRunner.aClass13_1 || local9.contentType == 1338) && (!local9.if3 || !InterfaceList.method947(local9))) {
				@Pc(50) int local50 = local9.x + arg6;
				@Pc(55) int local55 = local9.y + arg7;
				@Pc(61) int local61;
				@Pc(63) int local63;
				@Pc(65) int local65;
				@Pc(67) int local67;
				if (local9.type == 2) {
					local61 = arg2;
					local63 = arg3;
					local65 = arg4;
					local67 = arg5;
				} else {
					@Pc(73) int local73 = local50 + local9.width;
					@Pc(78) int local78 = local55 + local9.height;
					if (local9.type == 9) {
						local73++;
						local78++;
					}
					local61 = local50 > arg2 ? local50 : arg2;
					local63 = local55 > arg3 ? local55 : arg3;
					local65 = local73 < arg4 ? local73 : arg4;
					local67 = local78 < arg5 ? local78 : arg5;
				}
				if (local9 == ClientScriptRunner.aClass13_14) {
					Static44.aBoolean83 = true;
					Static124.anInt3075 = local50;
					Static20.anInt660 = local55;
				}
				if (!local9.if3 || local61 < local65 && local63 < local67) {
					if (local9.type == 0) {
						if (!local9.if3 && InterfaceList.method947(local9) && InterfaceList.aClass13_22 != local9) {
							continue;
						}
						if (local9.noClickThrough && Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
							for (@Pc(164) HookRequest local164 = (HookRequest) Static185.aClass69_101.head(); local164 != null; local164 = (HookRequest) Static185.aClass69_101.next()) {
								if (local164.aBoolean158) {
									local164.unlink();
									local164.source.aBoolean19 = false;
								}
							}
							if (ClientScriptRunner.anInt4851 == 0) {
								ClientScriptRunner.aClass13_14 = null;
								ClientScriptRunner.aClass13_1 = null;
							}
							Static137.anInt3337 = 0;
						}
					}
					if (local9.if3) {
						@Pc(207) boolean local207;
						if (Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
							local207 = true;
						} else {
							local207 = false;
						}
						@Pc(212) boolean local212 = false;
						if (Static22.activeInterfaceType == 1 && local207) {
							local212 = true;
						}
						@Pc(221) boolean local221 = false;
						if (Mouse.clickButton == 1 && aClass6.mouseClickX >= local61 && Static60.mouseClickY >= local63 && aClass6.mouseClickX < local65 && Static60.mouseClickY < local67) {
							local221 = true;
						}
						@Pc(243) int local243;
						@Pc(322) int local322;
						if (local9.aByteArray8 != null) {
							for (local243 = 0; local243 < local9.aByteArray8.length; local243++) {
								if (Keyboard.pressedKeys[local9.aByteArray8[local243]]) {
									if (local9.anIntArray49 == null || client.loop >= local9.anIntArray49[local243]) {
										@Pc(279) byte local279 = local9.aByteArray7[local243];
										if (local279 == 0 || ((local279 & 0x2) == 0 || Keyboard.pressedKeys[86]) && ((local279 & 0x1) == 0 || Keyboard.pressedKeys[82]) && ((local279 & 0x4) == 0 || Keyboard.pressedKeys[81])) {
											ClientProt.method4512(JString.EMPTY, -1, local243 + 1, local9.id);
											local322 = local9.anIntArray46[local243];
											if (local9.anIntArray49 == null) {
												local9.anIntArray49 = new int[local9.aByteArray8.length];
											}
											if (local322 == 0) {
												local9.anIntArray49[local243] = Integer.MAX_VALUE;
											} else {
												local9.anIntArray49[local243] = client.loop + local322;
											}
										}
									}
								} else if (local9.anIntArray49 != null) {
									local9.anIntArray49[local243] = 0;
								}
							}
						}
						if (local221) {
							Static40.method1015(Static60.mouseClickY - local55, aClass6.mouseClickX - local50, local9);
						}
						if (ClientScriptRunner.aClass13_14 != null && ClientScriptRunner.aClass13_14 != local9 && local207 && InterfaceList.getServerActiveProperties(local9).method509()) {
							Static56.aClass13_12 = local9;
						}
						if (local9 == ClientScriptRunner.aClass13_1) {
							InterfaceList.aBoolean174 = true;
							ClientScriptRunner.anInt2225 = local50;
							InterfaceList.anInt5103 = local55;
						}
						if (local9.aBoolean25 || local9.contentType != 0) {
							@Pc(399) HookRequest local399;
							if (local207 && MouseWheel.wheelRotation != 0 && local9.onScroll != null) {
								local399 = new HookRequest();
								local399.aBoolean158 = true;
								local399.source = local9;
								local399.anInt3097 = MouseWheel.wheelRotation;
								local399.arguments = local9.onScroll;
								Static185.aClass69_101.addTail(local399);
							}
							if (ClientScriptRunner.aClass13_14 != null || Static118.component != null || ClientScriptRunner.aBoolean108 || local9.contentType != 1400 && Static137.anInt3337 > 0) {
								local221 = false;
								local212 = false;
								local207 = false;
							}
							@Pc(508) int local508;
							if (local9.contentType != 0) {
								if (local9.contentType == 1337) {
									InterfaceList.aClass13_26 = local9;
									InterfaceList.redraw(local9);
									continue;
								}
								if (local9.contentType == 1338) {
									if (local221) {
										Static1.anInt5 = aClass6.mouseClickX - local50;
										Static107.anInt2878 = Static60.mouseClickY - local55;
									}
									continue;
								}
								if (local9.contentType == 1400) {
									Static24.component = local9;
									if (local221) {
										if (Keyboard.pressedKeys[82] && LoginManager.staffModLevel > 0) {
											local243 = (int) ((double) (aClass6.mouseClickX - local50 - local9.width / 2) * 2.0D / (double) Static83.aFloat3);
											local508 = (int) ((double) (Static60.mouseClickY - local55 - local9.height / 2) * 2.0D / (double) Static83.aFloat3);
											local322 = Static13.anInt435 + local243;
											@Pc(516) int local516 = Static28.anInt919 + local508;
											@Pc(520) int local520 = local322 + Static158.anInt3846;
											@Pc(528) int local528 = IdkTypeList.anInt4296 + Static2.anInt13 - local516 - 1;
											Cheat.teleport(local520, local528, 0);
											ClientProt.closeWidget();
											continue;
										}
										Static137.anInt3337 = 1;
										ClientScriptRunner.anInt5388 = Mouse.lastMouseX;
										ClientScriptRunner.anInt4035 = Mouse.lastMouseY;
										continue;
									}
									if (local212 && Static137.anInt3337 > 0) {
										if (Static137.anInt3337 == 1 && (ClientScriptRunner.anInt5388 != Mouse.lastMouseX || ClientScriptRunner.anInt4035 != Mouse.lastMouseY)) {
											Static197.anInt4620 = Static13.anInt435;
											Static71.anInt1885 = Static28.anInt919;
											Static137.anInt3337 = 2;
										}
										if (Static137.anInt3337 == 2) {
											Static98.method1964(Static197.anInt4620 + (int) ((double) (ClientScriptRunner.anInt5388 - Mouse.lastMouseX) * 2.0D / (double) Static138.aFloat14));
											Static277.method4641(Static71.anInt1885 + (int) ((double) (ClientScriptRunner.anInt4035 - Mouse.lastMouseY) * 2.0D / (double) Static138.aFloat14));
										}
										continue;
									}
									Static137.anInt3337 = 0;
									continue;
								}
								if (local9.contentType == 1401) {
									if (local212) {
										Static119.method2387(local9.width, Mouse.lastMouseY - local55, Mouse.lastMouseX - local50, local9.height);
									}
									continue;
								}
								if (local9.contentType == 1402) {
									if (!GlRenderer.enabled) {
										InterfaceList.redraw(local9);
									}
									continue;
								}
							}
							if (!local9.aBoolean24 && local221) {
								local9.aBoolean24 = true;
								if (local9.onClickRepeat != null) {
									local399 = new HookRequest();
									local399.aBoolean158 = true;
									local399.source = local9;
									local399.anInt3102 = aClass6.mouseClickX - local50;
									local399.anInt3097 = Static60.mouseClickY - local55;
									local399.arguments = local9.onClickRepeat;
									Static185.aClass69_101.addTail(local399);
								}
							}
							if (local9.aBoolean24 && local212 && local9.onDrag != null) {
								local399 = new HookRequest();
								local399.aBoolean158 = true;
								local399.source = local9;
								local399.anInt3102 = Mouse.lastMouseX - local50;
								local399.anInt3097 = Mouse.lastMouseY - local55;
								local399.arguments = local9.onDrag;
								Static185.aClass69_101.addTail(local399);
							}
							if (local9.aBoolean24 && !local212) {
								local9.aBoolean24 = false;
								if (local9.onRelease != null) {
									local399 = new HookRequest();
									local399.aBoolean158 = true;
									local399.source = local9;
									local399.anInt3102 = Mouse.lastMouseX - local50;
									local399.anInt3097 = Mouse.lastMouseY - local55;
									local399.arguments = local9.onRelease;
									InterfaceList.lowPriorityRequests.addTail(local399);
								}
							}
							if (local212 && local9.onHold != null) {
								local399 = new HookRequest();
								local399.aBoolean158 = true;
								local399.source = local9;
								local399.anInt3102 = Mouse.lastMouseX - local50;
								local399.anInt3097 = Mouse.lastMouseY - local55;
								local399.arguments = local9.onHold;
								Static185.aClass69_101.addTail(local399);
							}
							if (!local9.aBoolean19 && local207) {
								local9.aBoolean19 = true;
								if (local9.onMouseOver != null) {
									local399 = new HookRequest();
									local399.aBoolean158 = true;
									local399.source = local9;
									local399.anInt3102 = Mouse.lastMouseX - local50;
									local399.anInt3097 = Mouse.lastMouseY - local55;
									local399.arguments = local9.onMouseOver;
									Static185.aClass69_101.addTail(local399);
								}
							}
							if (local9.aBoolean19 && local207 && local9.onMouseRepeat != null) {
								local399 = new HookRequest();
								local399.aBoolean158 = true;
								local399.source = local9;
								local399.anInt3102 = Mouse.lastMouseX - local50;
								local399.anInt3097 = Mouse.lastMouseY - local55;
								local399.arguments = local9.onMouseRepeat;
								Static185.aClass69_101.addTail(local399);
							}
							if (local9.aBoolean19 && !local207) {
								local9.aBoolean19 = false;
								if (local9.onMouseLeave != null) {
									local399 = new HookRequest();
									local399.aBoolean158 = true;
									local399.source = local9;
									local399.anInt3102 = Mouse.lastMouseX - local50;
									local399.anInt3097 = Mouse.lastMouseY - local55;
									local399.arguments = local9.onMouseLeave;
									InterfaceList.lowPriorityRequests.addTail(local399);
								}
							}
							if (local9.onTimer != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onTimer;
								InterfaceList.highPriorityRequests.addTail(local399);
							}
							@Pc(966) HookRequest local966;
							if (local9.onVarcTransmit != null && Static4.updatedVarcsWriterIndex > local9.updatedVarcsReaderIndex) {
								if (local9.varcTriggers == null || Static4.updatedVarcsWriterIndex - local9.updatedVarcsReaderIndex > 32) {
									local399 = new HookRequest();
									local399.source = local9;
									local399.arguments = local9.onVarcTransmit;
									Static185.aClass69_101.addTail(local399);
								} else {
									label563: for (local243 = local9.updatedVarcsReaderIndex; local243 < Static4.updatedVarcsWriterIndex; local243++) {
										local508 = Static138.updatedVarcs[local243 & 0x1F];
										for (local322 = 0; local322 < local9.varcTriggers.length; local322++) {
											if (local9.varcTriggers[local322] == local508) {
												local966 = new HookRequest();
												local966.source = local9;
												local966.arguments = local9.onVarcTransmit;
												Static185.aClass69_101.addTail(local966);
												break label563;
											}
										}
									}
								}
								local9.updatedVarcsReaderIndex = Static4.updatedVarcsWriterIndex;
							}
							if (local9.onVarcstrTransmit != null && Static72.updatedVarcstrsWriterIndex > local9.updatedVarcstrsReaderIndex) {
								if (local9.varcstrTriggers == null || Static72.updatedVarcstrsWriterIndex - local9.updatedVarcstrsReaderIndex > 32) {
									local399 = new HookRequest();
									local399.source = local9;
									local399.arguments = local9.onVarcstrTransmit;
									Static185.aClass69_101.addTail(local399);
								} else {
									label539: for (local243 = local9.updatedVarcstrsReaderIndex; local243 < Static72.updatedVarcstrsWriterIndex; local243++) {
										local508 = ClientScriptRunner.updatedVarcstrs[local243 & 0x1F];
										for (local322 = 0; local322 < local9.varcstrTriggers.length; local322++) {
											if (local9.varcstrTriggers[local322] == local508) {
												local966 = new HookRequest();
												local966.source = local9;
												local966.arguments = local9.onVarcstrTransmit;
												Static185.aClass69_101.addTail(local966);
												break label539;
											}
										}
									}
								}
								local9.updatedVarcstrsReaderIndex = Static72.updatedVarcstrsWriterIndex;
							}
							if (local9.onVarpTransmit != null && Static70.updatedVarpsWriterIndex > local9.updatedVarpsReaderIndex) {
								if (local9.varpTriggers == null || Static70.updatedVarpsWriterIndex - local9.updatedVarpsReaderIndex > 32) {
									local399 = new HookRequest();
									local399.source = local9;
									local399.arguments = local9.onVarpTransmit;
									Static185.aClass69_101.addTail(local399);
								} else {
									label515: for (local243 = local9.updatedVarpsReaderIndex; local243 < Static70.updatedVarpsWriterIndex; local243++) {
										local508 = Static83.updatedVarps[local243 & 0x1F];
										for (local322 = 0; local322 < local9.varpTriggers.length; local322++) {
											if (local9.varpTriggers[local322] == local508) {
												local966 = new HookRequest();
												local966.source = local9;
												local966.arguments = local9.onVarpTransmit;
												Static185.aClass69_101.addTail(local966);
												break label515;
											}
										}
									}
								}
								local9.updatedVarpsReaderIndex = Static70.updatedVarpsWriterIndex;
							}
							if (local9.onInvTransmit != null && Static111.anInt2901 > local9.updatedInventoriesReaderIndex) {
								if (local9.inventoryTriggers == null || Static111.anInt2901 - local9.updatedInventoriesReaderIndex > 32) {
									local399 = new HookRequest();
									local399.source = local9;
									local399.arguments = local9.onInvTransmit;
									Static185.aClass69_101.addTail(local399);
								} else {
									label491: for (local243 = local9.updatedInventoriesReaderIndex; local243 < Static111.anInt2901; local243++) {
										local508 = Static27.anIntArray70[local243 & 0x1F];
										for (local322 = 0; local322 < local9.inventoryTriggers.length; local322++) {
											if (local9.inventoryTriggers[local322] == local508) {
												local966 = new HookRequest();
												local966.source = local9;
												local966.arguments = local9.onInvTransmit;
												Static185.aClass69_101.addTail(local966);
												break label491;
											}
										}
									}
								}
								local9.updatedInventoriesReaderIndex = Static111.anInt2901;
							}
							if (local9.onStatTransmit != null && Static89.anInt2385 > local9.updatedStatsReaderIndex) {
								if (local9.statTriggers == null || Static89.anInt2385 - local9.updatedStatsReaderIndex > 32) {
									local399 = new HookRequest();
									local399.source = local9;
									local399.arguments = local9.onStatTransmit;
									Static185.aClass69_101.addTail(local399);
								} else {
									label467: for (local243 = local9.updatedStatsReaderIndex; local243 < Static89.anInt2385; local243++) {
										local508 = Static249.anIntArray478[local243 & 0x1F];
										for (local322 = 0; local322 < local9.statTriggers.length; local322++) {
											if (local9.statTriggers[local322] == local508) {
												local966 = new HookRequest();
												local966.source = local9;
												local966.arguments = local9.onStatTransmit;
												Static185.aClass69_101.addTail(local966);
												break label467;
											}
										}
									}
								}
								local9.updatedStatsReaderIndex = Static89.anInt2385;
							}
							if (Chat.transmitAt > local9.lastTransmitTimer && local9.onMsg != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onMsg;
								Static185.aClass69_101.addTail(local399);
							}
							if (FriendList.transmitAt > local9.lastTransmitTimer && local9.onFriendTransmit != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onFriendTransmit;
								Static185.aClass69_101.addTail(local399);
							}
							if (ClanChat.transmitAt > local9.lastTransmitTimer && local9.onClanTransmit != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onClanTransmit;
								Static185.aClass69_101.addTail(local399);
							}
							if (Static207.anInt4778 > local9.lastTransmitTimer && local9.onStockTransmit != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onStockTransmit;
								Static185.aClass69_101.addTail(local399);
							}
							if (Static209.miscTransmitAt > local9.lastTransmitTimer && local9.onMiscTransmit != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onMiscTransmit;
								Static185.aClass69_101.addTail(local399);
							}
							local9.lastTransmitTimer = InterfaceList.transmitTimer;
							if (local9.onKey != null) {
								for (local243 = 0; local243 < InterfaceList.keyQueueSize; local243++) {
									@Pc(1430) HookRequest local1430 = new HookRequest();
									local1430.source = local9;
									local1430.anInt3100 = InterfaceList.keyCodes[local243];
									local1430.anInt3099 = InterfaceList.keyChars[local243];
									local1430.arguments = local9.onKey;
									Static185.aClass69_101.addTail(local1430);
								}
							}
							if (Camera.aBoolean16 && local9.onMinimapUnlock != null) {
								local399 = new HookRequest();
								local399.source = local9;
								local399.arguments = local9.onMinimapUnlock;
								Static185.aClass69_101.addTail(local399);
							}
						}
					}
					if (!local9.if3 && ClientScriptRunner.aClass13_14 == null && Static118.component == null && !ClientScriptRunner.aBoolean108) {
						if ((local9.anInt470 >= 0 || local9.overColor != 0) && Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
							if (local9.anInt470 >= 0) {
								InterfaceList.aClass13_22 = arg0[local9.anInt470];
							} else {
								InterfaceList.aClass13_22 = local9;
							}
						}
						if (local9.type == 8 && Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
							Protocol.aClass13_11 = local9;
						}
						if (local9.scrollMaxV > local9.height) {
							Static236.method4049(Mouse.lastMouseY, local9.height, local9, Mouse.lastMouseX, local50 + local9.width, local55, local9.scrollMaxV);
						}
					}
					if (local9.type == 0) {
						method946(arg0, local9.id, local61, local63, local65, local67, local50 - local9.scrollX, local55 - local9.scrollY);
						if (local9.createdComponents != null) {
							method946(local9.createdComponents, local9.id, local61, local63, local65, local67, local50 - local9.scrollX, local55 - local9.scrollY);
						}
						@Pc(1595) ComponentPointer local1595 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) local9.id);
						if (local1595 != null) {
							InterfaceList.method1320(local50, local63, local55, local65, local1595.interfaceId, local61, local67);
						}
					}
				}
			}
		}
	}

}
