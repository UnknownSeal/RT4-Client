package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.frame.Minimap;
import com.jagex.runetek4.game.world.entity.PlayerAppearance;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static87 {

	@OriginalMember(owner = "runetek4.client!gn", name = "t", descriptor = "Lclient!ve;")
	public static Js5 aClass153_38;

	@OriginalMember(owner = "runetek4.client!gn", name = "d", descriptor = "Z")
	public static boolean aBoolean130 = false;

	@OriginalMember(owner = "runetek4.client!gn", name = "v", descriptor = "Lclient!na;")
	public static final JString aClass100_494 = JString.parse("null");

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(BI)Lclient!na;")
	public static JString method1804(@OriginalArg(1) int capacity) {
		@Pc(13) JString local13 = new JString();
		local13.anInt4030 = 0;
		local13.aByteArray52 = new byte[capacity];
		return local13;
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(ZI)V")
	public static void method1805(@OriginalArg(0) boolean arg0) {
		@Pc(7) byte local7;
		@Pc(9) byte[][] local9;
		if (GlRenderer.enabled && arg0) {
			local7 = 1;
			local9 = Static186.aByteArrayArray14;
		} else {
			local7 = 4;
			local9 = Static273.aByteArrayArray13;
		}
		@Pc(18) int local18 = local9.length;
		@Pc(20) int local20;
		@Pc(38) int local38;
		@Pc(49) int local49;
		@Pc(53) byte[] local53;
		for (local20 = 0; local20 < local18; local20++) {
			local38 = (Static238.anIntArray470[local20] >> 8) * 64 - Camera.originX;
			local49 = (Static238.anIntArray470[local20] & 0xFF) * 64 - Camera.originZ;
			local53 = local9[local20];
			if (local53 != null) {
				client.audioLoop();
				Static269.method2203(PathFinder.collisionMaps, arg0, Static80.anInt4701 * 8 - 48, local49, local38, (Static52.anInt1695 - 6) * 8, local53);
			}
		}
		for (local20 = 0; local20 < local18; local20++) {
			local38 = (Static238.anIntArray470[local20] >> 8) * 64 - Camera.originX;
			local49 = (Static238.anIntArray470[local20] & 0xFF) * 64 - Camera.originZ;
			local53 = local9[local20];
			if (local53 == null && Static52.anInt1695 < 800) {
				client.audioLoop();
				for (@Pc(130) int local130 = 0; local130 < local7; local130++) {
					Static23.method645(local130, local49, local38, 64, 64);
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "b", descriptor = "(Z)V")
	public static void method1807() {
		for (@Pc(11) int local11 = 0; local11 < 100; local11++) {
			InterfaceList.aBooleanArray100[local11] = true;
		}
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(III[Lclient!be;IIIIBI)V")
	public static void drawGame(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) Component[] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(9) int arg8) {
		if (GlRenderer.enabled) {
			Static46.method1187(arg0, arg6, arg4, arg7);
		} else {
			Rasterizer.setBounds(arg0, arg6, arg4, arg7);
			Pix3D.method1908();
		}
		for (@Pc(18) int local18 = 0; local18 < arg3.length; local18++) {
			@Pc(30) Component local30 = arg3[local18];
			if (local30 != null && (local30.overlayer == arg5 || arg5 == -1412584499 && local30 == ClientScriptRunner.aClass13_14)) {
				@Pc(57) int local57;
				if (arg8 == -1) {
					InterfaceList.rectangleX[InterfaceList.rectangles] = arg2 + local30.x;
					InterfaceList.rectangleY[InterfaceList.rectangles] = local30.y + arg1;
					InterfaceList.rectangleWidth[InterfaceList.rectangles] = local30.width;
					InterfaceList.rectangleHeight[InterfaceList.rectangles] = local30.height;
					local57 = InterfaceList.rectangles++;
				} else {
					local57 = arg8;
				}
				local30.rectangleLoop = client.loop;
				local30.rectangle = local57;
				if (!local30.if3 || !Static36.method947(local30)) {
					if (local30.contentType > 0) {
						Static2.method13(local30);
					}
					@Pc(114) int local114 = arg1 + local30.y;
					@Pc(117) int local117 = local30.alpha;
					@Pc(123) int local123 = local30.x + arg2;
					if (Cheat.qaOpTest && (InterfaceList.getServerActiveProperties(local30).anInt546 != 0 || local30.type == 0) && local117 > 127) {
						local117 = 127;
					}
					@Pc(166) int local166;
					@Pc(164) int local164;
					if (local30 == ClientScriptRunner.aClass13_14) {
						if (arg5 != -1412584499 && !local30.dragRenderBehavior) {
							Static80.anInt4696 = arg2;
							Static127.anInt3126 = arg1;
							Static241.aClass13Array13 = arg3;
							continue;
						}
						if (Static138.aBoolean172 && Static146.aBoolean174) {
							local164 = Static223.anInt5032;
							local166 = Static215.anInt4873;
							local164 -= Static165.anInt4035;
							if (local164 < Static228.anInt5103) {
								local164 = Static228.anInt5103;
							}
							if (local164 + local30.height > Static4.aClass13_1.height + Static228.anInt5103) {
								local164 = Static4.aClass13_1.height + Static228.anInt5103 - local30.height;
							}
							local114 = local164;
							local166 -= Static246.anInt5388;
							if (com.jagex.runetek4.cache.def.ItemDefinition.anInt2225 > local166) {
								local166 = com.jagex.runetek4.cache.def.ItemDefinition.anInt2225;
							}
							if (Static4.aClass13_1.width + com.jagex.runetek4.cache.def.ItemDefinition.anInt2225 < local30.width + local166) {
								local166 = Static4.aClass13_1.width + com.jagex.runetek4.cache.def.ItemDefinition.anInt2225 - local30.width;
							}
							local123 = local166;
						}
						if (!local30.dragRenderBehavior) {
							local117 = 128;
						}
					}
					@Pc(302) int local302;
					@Pc(291) int local291;
					@Pc(270) int local270;
					@Pc(276) int local276;
					if (local30.type == 2) {
						local291 = arg7;
						local302 = arg4;
						local164 = arg6;
						local166 = arg0;
					} else {
						local164 = local114 > arg6 ? local114 : arg6;
						local166 = arg0 < local123 ? local123 : arg0;
						local270 = local30.width + local123;
						local276 = local114 + local30.height;
						if (local30.type == 9) {
							local276++;
							local270++;
						}
						local291 = arg7 <= local276 ? arg7 : local276;
						local302 = local270 >= arg4 ? arg4 : local270;
					}
					if (!local30.if3 || local302 > local166 && local164 < local291) {
						@Pc(468) int local468;
						@Pc(503) int local503;
						@Pc(514) int local514;
						@Pc(518) int local518;
						@Pc(556) int local556;
						@Pc(563) int local563;
						@Pc(571) int local571;
						@Pc(545) int local545;
						if (local30.contentType != 0) {
							if (local30.contentType == 1337 || local30.contentType == 1403 && GlRenderer.enabled) {
								Static280.aClass13_26 = local30;
								Static214.anInt5574 = local114;
								Static97.anInt2503 = local123;
								Static253.drawScene(local30.height, local30.contentType == 1403, local123, local30.width, local114);
								if (GlRenderer.enabled) {
									Static46.method1187(arg0, arg6, arg4, arg7);
								} else {
									Rasterizer.setBounds(arg0, arg6, arg4, arg7);
								}
								continue;
							}
							if (local30.contentType == 1338) {
								if (!local30.method478()) {
									continue;
								}
								Static54.method1305(local57, local114, local123, local30);
								if (GlRenderer.enabled) {
									Static46.method1187(arg0, arg6, arg4, arg7);
								} else {
									Rasterizer.setBounds(arg0, arg6, arg4, arg7);
								}
								if (Minimap.state != 0 && Minimap.state != 3 || ClientScriptRunner.aBoolean108 || local166 > Static155.anInt3751 || Static60.anInt1892 < local164 || Static155.anInt3751 >= local302 || local291 <= Static60.anInt1892) {
									continue;
								}
								local270 = Static155.anInt3751 - local123;
								local276 = Static60.anInt1892 - local114;
								local468 = local30.anIntArray37[local276];
								if (local270 < local468 || local270 > local468 + local30.anIntArray45[local276]) {
									continue;
								}
								local276 -= local30.height / 2;
								local503 = Camera.orbitCameraYaw + Minimap.minimapAnticheatAngle & 0x7FF;
								local270 -= local30.width / 2;
								local514 = MathUtils.sin[local503];
								local518 = MathUtils.cos[local503];
								local514 = (Minimap.minimapZoom + 256) * local514 >> 8;
								local518 = (Minimap.minimapZoom + 256) * local518 >> 8;
								local545 = local518 * local276 - local514 * local270 >> 11;
								local556 = local276 * local514 + local270 * local518 >> 11;
								local563 = PlayerList.self.xFine + local556 >> 7;
								local571 = PlayerList.self.zFine - local545 >> 7;
								if (MiniMenu.aBoolean302 && (Static274.anInt4999 & 0x40) != 0) {
									@Pc(583) Component local583 = Static201.method1418(Static98.anInt2512, Static15.anInt506);
									if (local583 == null) {
										Static53.method1294();
									} else {
										Static98.addActionRow(Static246.anInt5393, 1L, Static225.aClass100_961, local563, (short) 11, Static102.aClass100_545, local571);
									}
									continue;
								}
								if (client.game == 1) {
									Static98.addActionRow(-1, 1L, JString.EMPTY, local563, (short) 36, LocalizedText.FACEHERE, local571);
								}
								Static98.addActionRow(-1, 1L, JString.EMPTY, local563, (short) 60, MiniMenu.walkText, local571);
								continue;
							}
							if (local30.contentType == 1339) {
								if (local30.method478()) {
									Static160.method3047(local123, local114, local30, local57);
									if (GlRenderer.enabled) {
										Static46.method1187(arg0, arg6, arg4, arg7);
									} else {
										Rasterizer.setBounds(arg0, arg6, arg4, arg7);
									}
								}
								continue;
							}
							if (local30.contentType == 1400) {
								Static269.method2225(local123, local114, local30.height, local30.width);
								InterfaceList.aBooleanArray100[local57] = true;
								InterfaceList.rectangleRedraw[local57] = true;
								if (GlRenderer.enabled) {
									Static46.method1187(arg0, arg6, arg4, arg7);
								} else {
									Rasterizer.setBounds(arg0, arg6, arg4, arg7);
								}
								continue;
							}
							if (local30.contentType == 1401) {
								WorldMap.method4(local123, local30.height, local30.width, local114);
								InterfaceList.aBooleanArray100[local57] = true;
								InterfaceList.rectangleRedraw[local57] = true;
								if (GlRenderer.enabled) {
									Static46.method1187(arg0, arg6, arg4, arg7);
								} else {
									Rasterizer.setBounds(arg0, arg6, arg4, arg7);
								}
								continue;
							}
							if (local30.contentType == 1402) {
								if (!GlRenderer.enabled) {
									BZip2State.method3392(local123, local114);
									InterfaceList.aBooleanArray100[local57] = true;
									InterfaceList.rectangleRedraw[local57] = true;
								}
								continue;
							}
							if (local30.contentType == 1405) {
								if (!Cheat.displayFps) {
									continue;
								}
								local270 = local30.width + local123;
								local276 = local114 + 15;
								Static215.aClass3_Sub2_Sub9_32.method2864(JString.concatenate(new JString[] { Cheat.DEBUG_FPS2, JString.parseInt(Static243.fps) }), local270, local276, 16776960, -1);
								local276 += 15;
								@Pc(795) Runtime local795 = Runtime.getRuntime();
								local503 = (int) ((local795.totalMemory() - local795.freeMemory()) / 1024L);
								local514 = 16776960;
								if (local503 > 65536) {
									local514 = 16711680;
								}
								Static215.aClass3_Sub2_Sub9_32.method2864(JString.concatenate(new JString[] { Cheat.DEBUG_MEM, JString.parseInt(local503), Cheat.DEBUG_MEM_UNIT}), local270, local276, local514, -1);
								local276 += 15;
								if (GlRenderer.enabled) {
									local514 = 16776960;
									local518 = (Static63.oncard_texture + Static63.oncard_geometry + Static63.oncard_2d) / 1024;
									if (local518 > 65536) {
										local514 = 16711680;
									}
									Static215.aClass3_Sub2_Sub9_32.method2864(JString.concatenate(new JString[] { Cheat.DEBUG_CARD, JString.parseInt(local518), Cheat.DEBUG_MEM_UNIT}), local270, local276, local514, -1);
									local276 += 15;
								}
								local518 = 0;
								local545 = 0;
								local556 = 0;
								for (local563 = 0; local563 < 28; local563++) {
									local518 += client.js5Providers[local563].method535();
									local556 += client.js5Providers[local563].method529();
									local545 += client.js5Providers[local563].method533();
								}
								local571 = local556 * 10000 / local518;
								local563 = local545 * 100 / local518;
								@Pc(968) JString local968 = JString.concatenate(new JString[] { Cheat.DEBUG_CACHE, Static182.method3360(0, true, 2, (long) local571), Static147.aClass100_672, JString.parseInt(local563), Static14.aClass100_80 });
								Static114.aClass3_Sub2_Sub9_42.method2864(local968, local270, local276, 16776960, -1);
								local276 += 12;
								InterfaceList.aBooleanArray100[local57] = true;
								InterfaceList.rectangleRedraw[local57] = true;
								continue;
							}
							if (local30.contentType == 1406) {
								Static143.anInt3484 = local114;
								Static201.aClass13_13 = local30;
								Static131.anInt3260 = local123;
								continue;
							}
						}
						if (!ClientScriptRunner.aBoolean108) {
							if (local30.type == 0 && local30.noClickThrough && Static155.anInt3751 >= local166 && Static60.anInt1892 >= local164 && Static155.anInt3751 < local302 && local291 > Static60.anInt1892 && !Cheat.qaOpTest) {
								MiniMenu.menuActionRow = 1;
								Static190.anIntArray382[0] = Static35.anInt1092;
								Static254.aClass100Array168[0] = LocalizedText.CANCEL;
								ClientScriptRunner.aClass100Array160[0] = JString.EMPTY;
								Static39.aShortArray6[0] = 1005;
							}
							if (local166 <= Static155.anInt3751 && local164 <= Static60.anInt1892 && local302 > Static155.anInt3751 && local291 > Static60.anInt1892) {
								Static258.method4418(Static60.anInt1892 - local114, -local123 + Static155.anInt3751, local30);
							}
						}
						if (local30.type == 0) {
							if (!local30.if3 && Static36.method947(local30) && Static180.aClass13_22 != local30) {
								continue;
							}
							if (!local30.if3) {
								if (local30.scrollMaxV - local30.height < local30.scrollY) {
									local30.scrollY = local30.scrollMaxV - local30.height;
								}
								if (local30.scrollY < 0) {
									local30.scrollY = 0;
								}
							}
							drawGame(local166, local114 - local30.scrollY, -local30.scrollX + local123, arg3, local302, local30.id, local164, local291, local57);
							if (local30.createdComponents != null) {
								drawGame(local166, local114 - local30.scrollY, -local30.scrollX + local123, local30.createdComponents, local302, local30.id, local164, local291, local57);
							}
							@Pc(1186) ComponentPointer local1186 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) local30.id);
							if (local1186 != null) {
								if (local1186.anInt5879 == 0 && !ClientScriptRunner.aBoolean108 && Static155.anInt3751 >= local166 && local164 <= Static60.anInt1892 && local302 > Static155.anInt3751 && Static60.anInt1892 < local291 && !Cheat.qaOpTest) {
									Static254.aClass100Array168[0] = LocalizedText.CANCEL;
									MiniMenu.menuActionRow = 1;
									Static190.anIntArray382[0] = Static35.anInt1092;
									Static39.aShortArray6[0] = 1005;
									ClientScriptRunner.aClass100Array160[0] = JString.EMPTY;
								}
								Static6.method86(local1186.anInt5878, local166, local302, local123, local57, local291, local164, local114);
							}
							if (GlRenderer.enabled) {
								Static46.method1187(arg0, arg6, arg4, arg7);
							} else {
								Rasterizer.setBounds(arg0, arg6, arg4, arg7);
								Pix3D.method1908();
							}
						}
						if (Static223.aBooleanArray116[local57] || Cheat.rectDebug > 1) {
							if (local30.type == 0 && !local30.if3 && local30.scrollMaxV > local30.height) {
								Static74.method1624(local30.scrollY, local30.scrollMaxV, local30.width + local123, local114, local30.height);
							}
							if (local30.type != 1) {
								if (local30.type == 2) {
									local270 = 0;
									for (local276 = 0; local276 < local30.baseHeight; local276++) {
										for (local468 = 0; local468 < local30.baseWidth; local468++) {
											local514 = local114 + local276 * (local30.invMarginY + 32);
											local503 = (local30.invMarginX + 32) * local468 + local123;
											if (local270 < 20) {
												local514 += local30.invOffsetY[local270];
												local503 += local30.invOffsetX[local270];
											}
											if (local30.invSlotObjId[local270] > 0) {
												local545 = local30.invSlotObjId[local270] - 1;
												if (arg0 < local503 + 32 && local503 < arg4 && arg6 < local514 + 32 && local514 < arg7 || local30 == Static118.component && Static4.selectedInventorySlot == local270) {
													@Pc(1476) Sprite local1476;
													if (MiniMenu.anInt5014 == 1 && Static185.anInt4370 == local270 && local30.id == FluTypeList.anInt5062) {
														local1476 = Static190.method3443(2, local545, local30.objDrawText, local30.invSlotObjCount[local270], 0);
													} else {
														local1476 = Static190.method3443(1, local545, local30.objDrawText, local30.invSlotObjCount[local270], 3153952);
													}
													if (Pix3D.aBoolean134) {
														InterfaceList.aBooleanArray100[local57] = true;
													}
													if (local1476 == null) {
														InterfaceList.redraw(local30);
													} else if (Static118.component == local30 && local270 == Static4.selectedInventorySlot) {
														local518 = Static215.anInt4873 - Static149.anInt3554;
														local556 = Static223.anInt5032 - Static206.anInt4773;
														if (local556 < 5 && local556 > -5) {
															local556 = 0;
														}
														if (local518 < 5 && local518 > -5) {
															local518 = 0;
														}
														if (Static78.lastItemDragTime < 5) {
															local518 = 0;
															local556 = 0;
														}
														local1476.method1417(local503 + local518, local514 - -local556, 128);
														if (arg5 != -1) {
															@Pc(1571) Component local1571 = arg3[arg5 & 0xFFFF];
															@Pc(1577) int local1577;
															@Pc(1575) int local1575;
															if (GlRenderer.enabled) {
																local1575 = Static46.anInt1441;
																local1577 = Static46.anInt1438;
															} else {
																local1577 = Rasterizer.viewportTop;
																local1575 = Rasterizer.viewportBottom;
															}
															@Pc(1611) int local1611;
															if (local1577 > local556 + local514 && local1571.scrollY > 0) {
																local1611 = Protocol.sceneDelta * (local1577 - local556 - local514) / 3;
																if (local1611 > Protocol.sceneDelta * 10) {
																	local1611 = Protocol.sceneDelta * 10;
																}
																if (local1611 > local1571.scrollY) {
																	local1611 = local1571.scrollY;
																}
																local1571.scrollY -= local1611;
																Static206.anInt4773 += local1611;
																InterfaceList.redraw(local1571);
															}
															if (local1575 < local556 + local514 + 32 && local1571.scrollY < local1571.scrollMaxV - local1571.height) {
																local1611 = (local514 + local556 + 32 - local1575) * Protocol.sceneDelta / 3;
																if (local1611 > Protocol.sceneDelta * 10) {
																	local1611 = Protocol.sceneDelta * 10;
																}
																if (local1571.scrollMaxV - local1571.scrollY - local1571.height < local1611) {
																	local1611 = local1571.scrollMaxV - local1571.height - local1571.scrollY;
																}
																local1571.scrollY += local1611;
																Static206.anInt4773 -= local1611;
																InterfaceList.redraw(local1571);
															}
														}
													} else if (local30 == Static257.aClass13_7 && local270 == Static250.anInt5444) {
														local1476.method1417(local503, local514, 128);
													} else {
														local1476.drawSprite(local503, local514);
													}
												}
											} else if (local30.invSprite != null && local270 < 20) {
												@Pc(1381) Sprite local1381 = local30.method482(local270);
												if (local1381 != null) {
													local1381.drawSprite(local503, local514);
												} else if (Component.aBoolean72) {
													InterfaceList.redraw(local30);
												}
											}
											local270++;
										}
									}
								} else if (local30.type == 3) {
									if (Static154.method2926(local30)) {
										local270 = local30.activeColor;
										if (Static180.aClass13_22 == local30 && local30.anInt475 != 0) {
											local270 = local30.anInt475;
										}
									} else {
										local270 = local30.color;
										if (local30 == Static180.aClass13_22 && local30.overColor != 0) {
											local270 = local30.overColor;
										}
									}
									if (local117 == 0) {
										if (local30.filled) {
											if (GlRenderer.enabled) {
												Static46.method1186(local123, local114, local30.width, local30.height, local270);
											} else {
												Rasterizer.drawFilledRectangle(local123, local114, local30.width, local30.height, local270);
											}
										} else if (GlRenderer.enabled) {
											Static46.method1179(local123, local114, local30.width, local30.height, local270);
										} else {
											Rasterizer.drawUnfilledRectangle(local123, local114, local30.width, local30.height, local270);
										}
									} else if (local30.filled) {
										if (GlRenderer.enabled) {
											Static46.drawFilledRectangleAlpha(local123, local114, local30.width, local30.height, local270, 256 - (local117 & 0xFF));
										} else {
											Rasterizer.drawFilledRectangleAlpha(local123, local114, local30.width, local30.height, local270, 256 - (local117 & 0xFF));
										}
									} else if (GlRenderer.enabled) {
										Static46.method1180(local123, local114, local30.width, local30.height, local270, 256 - (local117 & 0xFF));
									} else {
										Rasterizer.drawUnfilledRectangleAlpha(local123, local114, local30.width, local30.height, local270, 256 - (local117 & 0xFF));
									}
								} else {
									@Pc(1921) Font local1921;
									if (local30.type == 4) {
										local1921 = local30.getFont(Static159.aClass36Array12);
										if (local1921 != null) {
											@Pc(1934) JString local1934 = local30.text;
											if (Static154.method2926(local30)) {
												local276 = local30.activeColor;
												if (Static180.aClass13_22 == local30 && local30.anInt475 != 0) {
													local276 = local30.anInt475;
												}
												if (local30.activeText.length() > 0) {
													local1934 = local30.activeText;
												}
											} else {
												local276 = local30.color;
												if (Static180.aClass13_22 == local30 && local30.overColor != 0) {
													local276 = local30.overColor;
												}
											}
											if (local30.if3 && local30.objId != -1) {
												@Pc(1989) com.jagex.runetek4.cache.def.ItemDefinition local1989 = Static71.get(local30.objId);
												local1934 = local1989.name;
												if (local1934 == null) {
													local1934 = Static92.aClass100_510;
												}
												if ((local1989.stackable == 1 || local30.objCount != 1) && local30.objCount != -1) {
													local1934 = JString.concatenate(new JString[] { Static8.aClass100_32, local1934, Static54.aClass100_375, Static70.method1548(local30.objCount) });
												}
											}
											if (ClientScriptRunner.aClass13_10 == local30) {
												local276 = local30.color;
												local1934 = LocalizedText.PLEASEWAIT;
											}
											if (!local30.if3) {
												local1934 = Static127.method2465(local30, local1934);
											}
											local1921.method2852(local1934, local123, local114, local30.width, local30.height, local276, local30.shadowed ? 0 : -1, local30.halign, local30.valign, local30.vpadding);
										} else if (Component.aBoolean72) {
											InterfaceList.redraw(local30);
										}
									} else if (local30.type == 5) {
										@Pc(2094) Sprite local2094;
										if (local30.if3) {
											if (local30.objId == -1) {
												local2094 = local30.method489(false);
											} else {
												local2094 = Static190.method3443(local30.outlineThickness, local30.objId, local30.objDrawText, local30.objCount, local30.shadowColor);
											}
											if (local2094 != null) {
												local276 = local2094.innerWidth;
												local468 = local2094.innerHeight;
												if (local30.spriteTiling) {
													local503 = (local276 + local30.width - 1) / local276;
													local514 = (local30.height + local468 - 1) / local468;
													if (GlRenderer.enabled) {
														Static46.method1183(local123, local114, local30.width + local123, local30.height + local114);
														@Pc(2274) boolean local2274 = Static209.method3702(local2094.width);
														@Pc(2279) boolean local2279 = Static209.method3702(local2094.height);
														@Pc(2282) GlSprite local2282 = (GlSprite) local2094;
														if (local2274 && local2279) {
															if (local117 == 0) {
																local2282.method1429(local123, local114, local503, local514);
															} else {
																local2282.method1426(local123, local114, 256 - (local117 & 0xFF), local503, local514);
															}
														} else if (local2274) {
															for (local563 = 0; local563 < local514; local563++) {
																if (local117 == 0) {
																	local2282.method1429(local123, local563 * local468 + local114, local503, 1);
																} else {
																	local2282.method1426(local123, local114 + local563 * local468, -(local117 & 0xFF) + 256, local503, 1);
																}
															}
														} else if (local2279) {
															for (local563 = 0; local563 < local503; local563++) {
																if (local117 == 0) {
																	local2282.method1429(local276 * local563 + local123, local114, 1, local514);
																} else {
																	local2282.method1426(local276 * local563 + local123, local114, 256 - (local117 & 0xFF), 1, local514);
																}
															}
														} else {
															for (local563 = 0; local563 < local503; local563++) {
																for (local571 = 0; local571 < local514; local571++) {
																	if (local117 == 0) {
																		local2094.drawSprite(local123 + local276 * local563, local468 * local571 + local114);
																	} else {
																		local2094.method1417(local563 * local276 + local123, local468 * local571 + local114, 256 - (local117 & 0xFF));
																	}
																}
															}
														}
														Static46.method1187(arg0, arg6, arg4, arg7);
													} else {
														Rasterizer.method2498(local123, local114, local123 + local30.width, local114 - -local30.height);
														for (local518 = 0; local518 < local503; local518++) {
															for (local556 = 0; local556 < local514; local556++) {
																if (local30.angle2d != 0) {
																	local2094.method1420(local114 + local468 * local556 + local468 / 2, local30.angle2d, 4096, local518 * local276 + local123 + local276 / 2);
																} else if (local117 == 0) {
																	local2094.drawSprite(local518 * local276 + local123, local468 * local556 + local114);
																} else {
																	local2094.method1417(local518 * local276 + local123, local114 + local556 * local468, 256 - (local117 & 0xFF));
																}
															}
														}
														Rasterizer.setBounds(arg0, arg6, arg4, arg7);
													}
												} else {
													local503 = local30.width * 4096 / local276;
													if (local30.angle2d != 0) {
														local2094.method1420(local114 + local30.height / 2, local30.angle2d, local503, local123 + local30.width / 2);
													} else if (local117 != 0) {
														local2094.method1422(local123, local114, local30.width, local30.height, 256 - (local117 & 0xFF));
													} else if (local276 == local30.width && local468 == local30.height) {
														local2094.drawSprite(local123, local114);
													} else {
														local2094.method1419(local123, local114, local30.width, local30.height);
													}
												}
											} else if (Component.aBoolean72) {
												InterfaceList.redraw(local30);
											}
										} else {
											local2094 = local30.method489(Static154.method2926(local30));
											if (local2094 != null) {
												local2094.drawSprite(local123, local114);
											} else if (Component.aBoolean72) {
												InterfaceList.redraw(local30);
											}
										}
									} else {
										@Pc(2611) com.jagex.runetek4.cache.def.ItemDefinition local2611;
										if (local30.type == 6) {
											@Pc(2587) boolean local2587 = Static154.method2926(local30);
											@Pc(2589) Model local2589 = null;
											if (local2587) {
												local276 = local30.activeModelSeqId;
											} else {
												local276 = local30.modelSeqId;
											}
											local503 = 0;
											if (local30.objId != -1) {
												local2611 = Static71.get(local30.objId);
												if (local2611 != null) {
													local2611 = local2611.getMeshAddress(local30.objCount);
													@Pc(2630) SeqType local2630 = local276 == -1 ? null : SeqType.getAnimationSequence(local276);
													local2589 = local2611.method1824(local30.anInt496, local30.anInt500, local2630, 1, local30.anInt510);
													if (local2589 == null) {
														InterfaceList.redraw(local30);
													} else {
														local503 = -local2589.getMinY() / 2;
													}
												}
											} else if (local30.modelType == 5) {
												if (local30.modelId == -1) {
													local2589 = PlayerAppearance.DEFAULT.method1954(null, -1, null, null, 0, -1, 0, -1, -1);
												} else {
													local514 = local30.modelId & 0x7FF;
													if (local514 == PlayerList.selfId) {
														local514 = 2047;
													}
													@Pc(2751) Player local2751 = PlayerList.players[local514];
													@Pc(2760) SeqType local2760 = local276 == -1 ? null : SeqType.getAnimationSequence(local276);
													if (local2751 != null && (int) local2751.username.encode37() << 11 == (local30.modelId & 0xFFFFF800)) {
														local2589 = local2751.model.method1954(null, -1, null, local2760, 0, -1, 0, local30.anInt510, 0);
													}
												}
											} else if (local276 == -1) {
												local2589 = local30.method488(-1, null, -1, 0, local2587, PlayerList.self.model);
												if (local2589 == null && Component.aBoolean72) {
													InterfaceList.redraw(local30);
												}
											} else {
												@Pc(2689) SeqType local2689 = SeqType.getAnimationSequence(local276);
												local2589 = local30.method488(local30.anInt496, local2689, local30.anInt510, local30.anInt500, local2587, PlayerList.self.model);
												if (local2589 == null && Component.aBoolean72) {
													InterfaceList.redraw(local30);
												}
											}
											if (local2589 != null) {
												if (local30.anInt451 > 0) {
													local514 = (local30.width << 8) / local30.anInt451;
												} else {
													local514 = 256;
												}
												if (local30.anInt526 <= 0) {
													local518 = 256;
												} else {
													local518 = (local30.height << 8) / local30.anInt526;
												}
												local556 = local123 + local30.width / 2 + (local514 * local30.anInt495 >> 8);
												local545 = local30.height / 2 + local114 + (local518 * local30.anInt481 >> 8);
												if (GlRenderer.enabled) {
													if (local30.modelOrtho) {
														GlRenderer.method4182(local556, local545, local30.modelZoom, local30.aShort11, local514, local518);
													} else {
														GlRenderer.method4148(local556, local545, local514, local518);
														GlRenderer.method4152((float) local30.aShort10, (float) local30.aShort11 * 1.5F);
													}
													GlRenderer.method4173();
													GlRenderer.setDepthTestEnabled(true);
													GlRenderer.setFogEnabled(false);
													Static229.method3935(Static113.brightness);
													if (Static263.aBoolean299) {
														Static46.method1177();
														GlRenderer.clearDepthBuffer();
														Static46.method1187(arg0, arg6, arg4, arg7);
														Static263.aBoolean299 = false;
													}
													if (local30.aBoolean34) {
														GlRenderer.disableDepthMask();
													}
													local563 = MathUtils.sin[local30.modelXAngle] * local30.modelZoom >> 16;
													local571 = local30.modelZoom * MathUtils.cos[local30.modelXAngle] >> 16;
													if (local30.if3) {
														local2589.drawModel(local30.modelYAngle, local30.modelYOffset, local30.modelXAngle, local30.modelXOffset, local30.modelZOffset + local563 + local503, local30.modelZOffset + local571, -1L);
													} else {
														local2589.drawModel(local30.modelYAngle, 0, local30.modelXAngle, 0, local563, local571, -1L);
													}
													if (local30.aBoolean34) {
														GlRenderer.enableDepthMask();
													}
												} else {
													Pix3D.method1919(local556, local545);
													local563 = MathUtils.sin[local30.modelXAngle] * local30.modelZoom >> 16;
													local571 = local30.modelZoom * MathUtils.cos[local30.modelXAngle] >> 16;
													if (!local30.if3) {
														local2589.drawModel(local30.modelYAngle, 0, local30.modelXAngle, 0, local563, local571, -1L);
													} else if (local30.modelOrtho) {
														((SoftwareModel) local2589).method4591(local30.modelYAngle, local30.modelYOffset, local30.modelXAngle, local30.modelXOffset, local30.modelZOffset + local503 + local563, local571 + local30.modelZOffset, local30.modelZoom);
													} else {
														local2589.drawModel(local30.modelYAngle, local30.modelYOffset, local30.modelXAngle, local30.modelXOffset, local30.modelZOffset + local563 + local503, local571 + local30.modelZOffset, -1L);
													}
													Pix3D.method1915();
												}
											}
										} else {
											if (local30.type == 7) {
												local1921 = local30.getFont(Static159.aClass36Array12);
												if (local1921 == null) {
													if (Component.aBoolean72) {
														InterfaceList.redraw(local30);
													}
													continue;
												}
												local276 = 0;
												for (local468 = 0; local468 < local30.baseHeight; local468++) {
													for (local503 = 0; local503 < local30.baseWidth; local503++) {
														if (local30.invSlotObjId[local276] > 0) {
															local2611 = Static71.get(local30.invSlotObjId[local276] - 1);
															@Pc(3159) JString local3159;
															if (local2611.stackable != 1 && local30.invSlotObjCount[local276] == 1) {
																local3159 = JString.concatenate(new JString[] { Static8.aClass100_32, local2611.name, Static230.aClass100_978 });
															} else {
																local3159 = JString.concatenate(new JString[] { Static8.aClass100_32, local2611.name, Static54.aClass100_375, Static70.method1548(local30.invSlotObjCount[local276]) });
															}
															local556 = local123 + local503 * (local30.invMarginX + 115);
															local545 = (local30.invMarginY + 12) * local468 + local114;
															if (local30.halign == 0) {
																local1921.drawString(local3159, local556, local545, local30.color, local30.shadowed ? 0 : -1);
															} else if (local30.halign == 1) {
																local1921.method2875(local3159, local556 + 57, local545, local30.color, local30.shadowed ? 0 : -1);
															} else {
																local1921.method2864(local3159, local556 + 115 - 1, local545, local30.color, local30.shadowed ? 0 : -1);
															}
														}
														local276++;
													}
												}
											}
											if (local30.type == 8 && Static43.aClass13_11 == local30 && Static133.anInt5235 == Static191.anInt4504) {
												local276 = 0;
												local270 = 0;
												@Pc(3297) JString local3297 = local30.text;
												@Pc(3299) Font local3299 = Static215.aClass3_Sub2_Sub9_32;
												local3297 = Static127.method2465(local30, local3297);
												@Pc(3325) JString local3325;
												while (local3297.length() > 0) {
													local518 = local3297.indexOf(Static269.aClass100_556);
													if (local518 == -1) {
														local3325 = local3297;
														local3297 = JString.EMPTY;
													} else {
														local3325 = local3297.substring(local518, 0);
														local3297 = local3297.substring(local518 + 4);
													}
													local556 = local3299.method2858(local3325);
													local276 += local3299.characterDefaultHeight + 1;
													if (local270 < local556) {
														local270 = local556;
													}
												}
												local556 = local114 + local30.height + 5;
												local270 += 6;
												local276 += 7;
												if (local556 + local276 > arg7) {
													local556 = arg7 - local276;
												}
												local518 = local123 + local30.width - local270 - 5;
												if (local518 < local123 + 5) {
													local518 = local123 + 5;
												}
												if (local270 + local518 > arg4) {
													local518 = arg4 - local270;
												}
												if (GlRenderer.enabled) {
													Static46.method1186(local518, local556, local270, local276, 16777120);
													Static46.method1179(local518, local556, local270, local276, 0);
												} else {
													Rasterizer.drawFilledRectangle(local518, local556, local270, local276, 16777120);
													Rasterizer.drawUnfilledRectangle(local518, local556, local270, local276, 0);
												}
												local3297 = local30.text;
												local545 = local556 + local3299.characterDefaultHeight + 2;
												local3297 = Static127.method2465(local30, local3297);
												while (local3297.length() > 0) {
													local563 = local3297.indexOf(Static269.aClass100_556);
													if (local563 == -1) {
														local3325 = local3297;
														local3297 = JString.EMPTY;
													} else {
														local3325 = local3297.substring(local563, 0);
														local3297 = local3297.substring(local563 + 4);
													}
													local3299.drawString(local3325, local518 + 3, local545, 0, -1);
													local545 += local3299.characterDefaultHeight + 1;
												}
											}
											if (local30.type == 9) {
												if (local30.aBoolean20) {
													local468 = local123 + local30.width;
													local276 = local114 + local30.height;
													local503 = local114;
												} else {
													local276 = local114;
													local503 = local114 + local30.height;
													local468 = local123 + local30.width;
												}
												if (local30.lineWidth == 1) {
													if (GlRenderer.enabled) {
														Static46.method1185(local123, local276, local468, local503, local30.color);
													} else {
														Rasterizer.drawDiagonalLine(local123, local276, local468, local503, local30.color);
													}
												} else if (GlRenderer.enabled) {
													Static46.method1181(local123, local276, local468, local503, local30.color, local30.lineWidth);
												} else {
													Rasterizer.method2494(local123, local276, local468, local503, local30.color, local30.lineWidth);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

}
