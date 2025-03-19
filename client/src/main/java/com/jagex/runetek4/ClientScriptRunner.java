package com.jagex.runetek4;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.cs.ClientScript;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.def.VarbitDefinition;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.game.config.enumtype.EnumType;
import com.jagex.runetek4.game.config.quickchatphrasetype.QuickChatPhraseType;
import com.jagex.runetek4.game.shared.framework.gwc.GWCLocation;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.game.world.entity.PlayerAppearance;
import com.jagex.runetek4.graphics.RawModel;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.node.NodeQueue;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.util.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class ClientScriptRunner {

	@OriginalMember(owner = "client!bm", name = "p", descriptor = "Lclient!na;")
	public static final JString aClass100_133 = JString.parse("(U1");
	@OriginalMember(owner = "runetek4.client!wh", name = "u", descriptor = "Lclient!na;")
	public static final JString aClass100_1097 = JString.parse("(U2");
	@OriginalMember(owner = "runetek4.client!mj", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_761 = JString.parse("(U3");
	@OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "Lclient!na;")
	public static final JString aClass100_520 = JString.parse("(U4");
	@OriginalMember(owner = "runetek4.client!tb", name = "P", descriptor = "Lclient!na;")
	public static final JString aClass100_1002 = JString.parse("(U5");
	@OriginalMember(owner = "runetek4.client!jh", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_591 = JString.parse("(Udns");
	@OriginalMember(owner = "runetek4.client!lh", name = "z", descriptor = "Lclient!na;")
	public static final JString aClass100_672 = JString.parse("(U (X");
	@OriginalMember(owner = "client!bd", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_80 = JString.parse("(U(Y");
	@OriginalMember(owner = "runetek4.client!oj", name = "p", descriptor = "I")
	public static final int anInt4306 = 2301979;
	@OriginalMember(owner = "runetek4.client!ec", name = "l", descriptor = "I")
	public static final int anInt1704 = 5063219;
	@OriginalMember(owner = "runetek4.client!rl", name = "Z", descriptor = "I")
	public static final int anInt4938 = 7759444;
	@OriginalMember(owner = "client!bj", name = "V", descriptor = "I")
	public static final int anInt671 = 3353893;
	@OriginalMember(owner = "runetek4.client!pg", name = "V", descriptor = "I")
	public static final int anInt4504 = 50;
	@OriginalMember(owner = "runetek4.client!t", name = "l", descriptor = "Lclient!ma;")
	public static BufferedSocket aClass95_4;

	@OriginalMember(owner = "runetek4.client!t", name = "y", descriptor = "I")
	public static int anInt5223 = 0;

	@OriginalMember(owner = "runetek4.client!t", name = "C", descriptor = "Lclient!na;")
	public static final JString aClass100_994 = JString.parse(")3");

	@OriginalMember(owner = "runetek4.client!t", name = "E", descriptor = "[I")
	public static final int[] updatedVarcstrs = new int[32];

	@OriginalMember(owner = "runetek4.client!em", name = "z", descriptor = "Z")
	public static boolean aBoolean108 = false;
	@OriginalMember(owner = "runetek4.client!d", name = "R", descriptor = "Lclient!be;")
	public static Component aClass13_10 = null;
	@OriginalMember(owner = "client!bi", name = "jb", descriptor = "Z")
	public static boolean aBoolean43 = true;
	@OriginalMember(owner = "runetek4.client!k", name = "m", descriptor = "Z")
	public static boolean neverRemoveRoofs = false;
	@OriginalMember(owner = "runetek4.client!ib", name = "e", descriptor = "Lclient!be;")
	public static Component aClass13_14 = null;
	@OriginalMember(owner = "runetek4.client!nm", name = "W", descriptor = "Lclient!na;")
	public static JString url;
	@OriginalMember(owner = "runetek4.client!th", name = "m", descriptor = "[Lclient!be;")
	public static Component[] aClass13Array13;
	@OriginalMember(owner = "runetek4.client!k", name = "j", descriptor = "I")
	public static int anInt3126;
	@OriginalMember(owner = "runetek4.client!gf", name = "K", descriptor = "I")
	public static int anInt4696;
	@OriginalMember(owner = "runetek4.client!ac", name = "p", descriptor = "Lclient!be;")
	public static Component aClass13_1 = null;
	@OriginalMember(owner = "runetek4.client!km", name = "pc", descriptor = "Z")
	public static boolean aBoolean172 = false;
	@OriginalMember(owner = "runetek4.client!gg", name = "db", descriptor = "I")
	public static int anInt2225 = -1;
	@OriginalMember(owner = "runetek4.client!nb", name = "d", descriptor = "I")
	public static int anInt4035 = 0;
	@OriginalMember(owner = "runetek4.client!kd", name = "Bb", descriptor = "I")
	public static int anInt3260 = -1;
	@OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "I")
	public static int anInt2503 = -1;
	@OriginalMember(owner = "runetek4.client!ld", name = "c", descriptor = "I")
	public static int anInt3484 = -1;
	@OriginalMember(owner = "runetek4.client!lf", name = "k", descriptor = "I")
	public static int anInt3502 = -1;
	@OriginalMember(owner = "runetek4.client!mh", name = "X", descriptor = "I")
	public static int anInt3851 = -1;
	@OriginalMember(owner = "runetek4.client!ig", name = "b", descriptor = "I")
	public static int anInt2882;
	@OriginalMember(owner = "runetek4.client!ig", name = "f", descriptor = "I")
	public static int anInt2884;
	@OriginalMember(owner = "runetek4.client!hc", name = "P", descriptor = "I")
	public static int anInt2428;
	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "I")
	public static int anInt5388 = 0;
	@OriginalMember(owner = "runetek4.client!re", name = "y", descriptor = "I")
	public static int anInt4851;
	@OriginalMember(owner = "runetek4.client!me", name = "nb", descriptor = "I")
	public static int anInt3751;
	@OriginalMember(owner = "runetek4.client!em", name = "w", descriptor = "I")
	public static int anInt1892;
	@OriginalMember(owner = "runetek4.client!vg", name = "c", descriptor = "Z")
	public static boolean aBoolean299 = false;
	@OriginalMember(owner = "runetek4.client!wb", name = "c", descriptor = "I")
	public static int anInt5794 = -1;

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(I)V")
	public static void clear() {
		IdkTypeList.types.clean();
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(IIIZIII)V")
	public static void method4000(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(3) int local3 = 0;
		@Pc(5) Class102[] local5 = MiniMap.hintMapMarkers;
		while (local5.length > local3) {
			@Pc(17) Class102 local17 = local5[local3];
			if (local17 != null && local17.headIconDrawType == 2) {
				Static198.method1026(arg0 >> 1, arg4, (local17.anInt4046 - Camera.originZ << 7) + local17.anInt4047, local17.anInt4050 * 2, arg2 >> 1, local17.anInt4045 + (local17.anInt4053 - Camera.originX << 7), arg3);
				if (Static65.anInt1951 > -1 && client.loop % 20 < 10) {
					Static276.aClass3_Sub2_Sub1Array11[local17.anInt4048].render(arg1 + Static65.anInt1951 - 12, arg5 + -28 - -Static16.anInt548);
				}
			}
			local3++;
		}
	}

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(B)V")
	public static void removeSoft() {
		Static93.aClass99_13.removeSoft();
		Static125.aClass99_18.removeSoft();
		Static262.aClass99_35.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(ZB)V")
	public static void method4002(@OriginalArg(0) boolean arg0) {
		@Pc(19) byte local19;
		@Pc(21) byte[][] local21;
		if (GlRenderer.enabled && arg0) {
			local21 = Static19.aByteArrayArray4;
			local19 = 1;
		} else {
			local19 = 4;
			local21 = Static156.aByteArrayArray11;
		}
		for (@Pc(29) int local29 = 0; local29 < local19; local29++) {
			client.audioLoop();
			for (@Pc(36) int local36 = 0; local36 < 13; local36++) {
				for (@Pc(43) int local43 = 0; local43 < 13; local43++) {
					@Pc(56) int local56 = Static187.anIntArrayArrayArray18[local29][local36][local43];
					if (local56 != -1) {
						@Pc(67) int local67 = local56 >> 24 & 0x3;
						if (!arg0 || local67 == 0) {
							@Pc(77) int local77 = local56 >> 1 & 0x3;
							@Pc(83) int local83 = local56 >> 14 & 0x3FF;
							@Pc(89) int local89 = local56 >> 3 & 0x7FF;
							@Pc(99) int local99 = local89 / 8 + (local83 / 8 << 8);
							for (@Pc(101) int local101 = 0; local101 < Static238.anIntArray470.length; local101++) {
								if (Static238.anIntArray470[local101] == local99 && local21[local101] != null) {
									Static217.method3771(PathFinder.collisionMaps, local29, local21[local101], local67, local77, local36 * 8, local43 * 8, arg0, (local83 & 0x7) * 8, (local89 & 0x7) * 8);
									break;
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(BJII)Z")
	public static boolean method4003(@OriginalArg(1) long arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(12) int local12 = (int) arg0 >> 14 & 0x1F;
		@Pc(24) int local24 = (int) arg0 >> 20 & 0x3;
		@Pc(31) int local31 = (int) (arg0 >>> 32) & Integer.MAX_VALUE;
		if (local12 == 10 || local12 == 11 || local12 == 22) {
			@Pc(46) LocType local46 = LocTypeList.get(local31);
			@Pc(62) int local62;
			@Pc(59) int local59;
			if (local24 == 0 || local24 == 2) {
				local59 = local46.length;
				local62 = local46.width;
			} else {
				local59 = local46.width;
				local62 = local46.length;
			}
			@Pc(73) int local73 = local46.forceapproach;
			if (local24 != 0) {
				local73 = (local73 << local24 & 0xF) + (local73 >> 4 - local24);
			}
			PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, local59, true, local73, arg2, local62, 0, 2, arg1, PlayerList.self.movementQueueX[0]);
		} else {
			PathFinder.tryMove(PlayerList.self.movementQueueZ[0], local24, 0, true, 0, arg2, 0, local12 + 1, 2, arg1, PlayerList.self.movementQueueX[0]);
		}
		Cross.y = Mouse.mouseClickY;
		Cross.crossCycle = 0;
		Cross.crossMode = 2;
		Cross.x = Mouse.mouseClickX;
		return true;
	}

	@OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(Lclient!i;II)V")
	public static void createClientScriptCheckPacket(@OriginalArg(0) PacketBit buffer) {
		while (true) {
			@Pc(18) ReflectionCheck clientScriptRunner = (ReflectionCheck) Static204.aClass69_113.head();
			if (clientScriptRunner == null) {
				return;
			}
			@Pc(23) boolean bool = false;
			@Pc(25) int i;
			for (i = 0; i < clientScriptRunner.scriptCount; i++) {
				if (clientScriptRunner.valueNodes[i] != null) {
					if (clientScriptRunner.valueNodes[i].status == 2) {
						clientScriptRunner.errorCodes[i] = -5;
					}
					if (clientScriptRunner.valueNodes[i].status == 0) {
						bool = true;
					}
				}
				if (clientScriptRunner.functionNodes[i] != null) {
					if (clientScriptRunner.functionNodes[i].status == 2) {
						clientScriptRunner.errorCodes[i] = -6;
					}
					if (clientScriptRunner.functionNodes[i].status == 0) {
						bool = true;
					}
				}
			}
			if (bool) {
				return;
			}
			buffer.pIsaac1(163);
			buffer.p1(0);
			i = buffer.offset;
			buffer.p4(clientScriptRunner.scriptId);
			for (@Pc(121) int j = 0; j < clientScriptRunner.scriptCount; j++) {
				if (clientScriptRunner.errorCodes[j] == 0) {
					try {
						@Pc(151) int opcode = clientScriptRunner.anIntArray139[j];
						@Pc(168) Field field;
						@Pc(195) int fieldValue;
						if (opcode == 0) {
							field = (Field) clientScriptRunner.valueNodes[j].result;
							fieldValue = field.getInt(null);
							buffer.p1(0);
							buffer.p4(fieldValue);
						} else if (opcode == 1) {
							field = (Field) clientScriptRunner.valueNodes[j].result;
							field.setInt(null, clientScriptRunner.anIntArray137[j]);
							buffer.p1(0);
						} else if (opcode == 2) {
							field = (Field) clientScriptRunner.valueNodes[j].result;
							fieldValue = field.getModifiers();
							buffer.p1(0);
							buffer.p4(fieldValue);
						}
						@Pc(234) Method method;
						if (opcode == 3) {
							method = (Method) clientScriptRunner.functionNodes[j].result;
							@Pc(239) byte[][] argumentValueData = clientScriptRunner.argumentValues[j];
							@Pc(243) Object[] objects = new Object[argumentValueData.length];
							for (@Pc(245) int valueIndex = 0; valueIndex < argumentValueData.length; valueIndex++) {
								@Pc(259) ObjectInputStream objectinputstream = new ObjectInputStream(new ByteArrayInputStream(argumentValueData[valueIndex]));
								objects[valueIndex] = objectinputstream.readObject();
							}
							@Pc(272) Object object = method.invoke(null, objects);
							if (object == null) {
								buffer.p1(0);
							} else if (object instanceof Number) {
								buffer.p1(1);
								buffer.p8(((Number) object).longValue());
							} else if (object instanceof JString) {
								buffer.p1(2);
								buffer.pjstr((JString) object);
							} else {
								buffer.p1(4);
							}
						} else if (opcode == 4) {
							method = (Method) clientScriptRunner.functionNodes[j].result;
							fieldValue = method.getModifiers();
							buffer.p1(0);
							buffer.p4(fieldValue);
						}
					} catch (@Pc(338) ClassNotFoundException classnotfoundexception) {
						buffer.p1(-10);
					} catch (@Pc(344) InvalidClassException invalidclassexception) {
						buffer.p1(-11);
					} catch (@Pc(350) StreamCorruptedException streamcorruptedexception) {
						buffer.p1(-12);
					} catch (@Pc(356) OptionalDataException optionaldataexception) {
						buffer.p1(-13);
					} catch (@Pc(362) IllegalAccessException illegalaccessexception) {
						buffer.p1(-14);
					} catch (@Pc(368) IllegalArgumentException illegalargumentexception) {
						buffer.p1(-15);
					} catch (@Pc(374) InvocationTargetException invocationtargetexception) {
						buffer.p1(-16);
					} catch (@Pc(380) SecurityException securityexception) {
						buffer.p1(-17);
					} catch (@Pc(386) IOException ioexception) {
						buffer.p1(-18);
					} catch (@Pc(392) NullPointerException nullpointerexception) {
						buffer.p1(-19);
					} catch (@Pc(398) Exception exception) {
						buffer.p1(-20);
					} catch (@Pc(404) Throwable throwable) {
						buffer.p1(-21);
					}
				} else {
					buffer.p1(clientScriptRunner.errorCodes[j]);
				}
			}
			buffer.pCrc32(i);
			buffer.p1len(buffer.offset - i);
			clientScriptRunner.unlink();
		}
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "b", descriptor = "(I)V")
	public static void method28() {
		InterfaceList.redraw(aClass13_14);
		anInt4851++;
		if (InterfaceList.aBoolean83 && InterfaceList.aBoolean174) {
			@Pc(30) int local30 = Mouse.lastMouseX;
			local30 -= anInt5388;
			if (anInt2225 > local30) {
				local30 = anInt2225;
			}
			@Pc(41) int local41 = Mouse.lastMouseY;
			if (anInt2225 + aClass13_1.width < local30 - -aClass13_14.width) {
				local30 = anInt2225 + aClass13_1.width - aClass13_14.width;
			}
			local41 -= anInt4035;
			if (local41 < InterfaceList.anInt5103) {
				local41 = InterfaceList.anInt5103;
			}
			if (InterfaceList.anInt5103 + aClass13_1.height < local41 - -aClass13_14.height) {
				local41 = InterfaceList.anInt5103 + aClass13_1.height - aClass13_14.height;
			}
			@Pc(109) int local109 = local41 - InterfaceList.anInt660;
			@Pc(114) int local114 = local30 - InterfaceList.anInt3075;
			@Pc(122) int local122 = local30 + aClass13_1.scrollX - anInt2225;
			@Pc(130) int local130 = aClass13_1.scrollY + local41 - InterfaceList.anInt5103;
			@Pc(133) int local133 = aClass13_14.dragDeadzone;
			if (anInt4851 > aClass13_14.dragDeadtime && (local133 < local114 || -local133 > local114 || local109 > local133 || local109 < -local133)) {
				aBoolean172 = true;
			}
			@Pc(176) HookRequest local176;
			if (aClass13_14.onDragStart != null && aBoolean172) {
				local176 = new HookRequest();
				local176.source = aClass13_14;
				local176.arguments = aClass13_14.onDragStart;
				local176.mouseX = local122;
				local176.mouseY = local130;
				run(local176);
			}
			if (Mouse.pressedButton == 0) {
				if (aBoolean172) {
					if (aClass13_14.onDragRelease != null) {
						local176 = new HookRequest();
						local176.mouseY = local130;
						local176.target = InterfaceList.aClass13_12;
						local176.mouseX = local122;
						local176.arguments = aClass13_14.onDragRelease;
						local176.source = aClass13_14;
						run(local176);
					}
					if (InterfaceList.aClass13_12 != null && InterfaceList.method938(aClass13_14) != null) {
						Protocol.outboundBuffer.pIsaac1(79);
						Protocol.outboundBuffer.p4_alt3(aClass13_14.id);
						Protocol.outboundBuffer.p2_alt1(InterfaceList.aClass13_12.createdComponentId);
						Protocol.outboundBuffer.p4(InterfaceList.aClass13_12.id);
						Protocol.outboundBuffer.p2_alt1(aClass13_14.createdComponentId);
					}
				} else if ((Static116.oneMouseButton == 1 || Static277.menuHasAddFriend(MiniMenu.menuActionRow - 1)) && MiniMenu.menuActionRow > 2) {
					Static226.determineMenuSize();
				} else if (MiniMenu.menuActionRow > 0) {
					Static59.processMenuActions();
				}
				aClass13_14 = null;
			}
		} else if (anInt4851 > 1) {
			aClass13_14 = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "(ILclient!jl;)V")
	public static void run(@OriginalArg(1) HookRequest arg0) {
		runClientScripts(200000, arg0);
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!be;Lclient!na;I)Lclient!na;")
	public static JString interpolate(@OriginalArg(0) Component arg0, @OriginalArg(1) JString string) {
		if (string.indexOf(JString.PERCENT_SIGN) == -1) {
			return string;
		}
		while (true) {
			@Pc(14) int local14 = string.indexOf(aClass100_133);
			if (local14 == -1) {
				while (true) {
					local14 = string.indexOf(aClass100_1097);
					if (local14 == -1) {
						while (true) {
							local14 = string.indexOf(aClass100_761);
							if (local14 == -1) {
								while (true) {
									local14 = string.indexOf(aClass100_520);
									if (local14 == -1) {
										while (true) {
											local14 = string.indexOf(aClass100_1002);
											if (local14 == -1) {
												while (true) {
													local14 = string.indexOf(aClass100_591);
													if (local14 == -1) {
														return string;
													}
													@Pc(246) JString local246 = JString.EMPTY;
													if (Player.lastLogAddress != null) {
														local246 = JString.formatIp(Player.lastLogAddress.intArg2);
														try {
															if (Player.lastLogAddress.result != null) {
																@Pc(265) byte[] local265 = ((String) Player.lastLogAddress.result).getBytes("ISO-8859-1");
																local246 = JString.decodeString(local265, local265.length, 0);
															}
														} catch (@Pc(274) UnsupportedEncodingException local274) {
														}
													}
													string = JString.concatenate(new JString[] { string.substring(local14, 0), local246, string.substring(local14 + 4) });
												}
											}
											string = JString.concatenate(new JString[] { string.substring(local14, 0), StringUtils.toString(executeClientscript(4, arg0)), string.substring(local14 + 2) });
										}
									}
									string = JString.concatenate(new JString[] { string.substring(local14, 0), StringUtils.toString(executeClientscript(3, arg0)), string.substring(local14 + 2) });
								}
							}
							string = JString.concatenate(new JString[] { string.substring(local14, 0), StringUtils.toString(executeClientscript(2, arg0)), string.substring(local14 + 2) });
						}
					}
					string = JString.concatenate(new JString[] { string.substring(local14, 0), StringUtils.toString(executeClientscript(1, arg0)), string.substring(local14 + 2) });
				}
			}
			string = JString.concatenate(new JString[] { string.substring(local14, 0), StringUtils.toString(executeClientscript(0, arg0)), string.substring(local14 + 2) });
		}
	}

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BILclient!be;)I")
	public static int executeClientscript(@OriginalArg(1) int scriptIndex, @OriginalArg(2) Component component) {
		if (component.scripts == null || scriptIndex >= component.scripts.length) {
			return -2;
		}
		try {
			@Pc(33) int[] script = component.scripts[scriptIndex];
			@Pc(35) byte accumulatorMode = 0;
			@Pc(37) int accumulator = 0;
			@Pc(39) int pc = 0;
			while (true) {
				@Pc(41) int value = 0;
				@Pc(46) int opcode = script[pc++];
				@Pc(48) byte nextOperator = 0;
				if (opcode == 0) {
					return accumulator;
				}
				if (opcode == 15) {
					nextOperator = 1;
				}
				if (opcode == 16) {
					nextOperator = 2;
				}
				if (opcode == 1) { // load_skill_level {skill}
					value = PlayerSkillXpTable.skillLevel[script[pc++]];
				}
				if (opcode == 17) {
					nextOperator = 3;
				}
				if (opcode == 2) { // load_skill_base_level {skill}
					value = PlayerSkillXpTable.skillBaseLevel[script[pc++]];
				}
				if (opcode == 3) { // load_skill_exp {skill}
					value = PlayerSkillXpTable.skillExperience[script[pc++]];
				}
				@Pc(124) int pc2;
				@Pc(135) Component com;
				@Pc(140) int pc3;
				@Pc(152) int j;
				if (opcode == 4) { // load_inv_count {interface id} {obj id}
					pc2 = script[pc++] << 16;
					@Pc(131) int componentId = pc2 + script[pc++];
					com = InterfaceList.getComponent(componentId);
					pc3 = script[pc++];
					if (pc3 != -1 && (!ObjTypeList.get(pc3).members || Static2.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (pc3 + 1 == com.invSlotObjId[j]) {
								value += com.invSlotObjCount[j];
							}
						}
					}
				}
				if (opcode == 5) {  // load_var {id}
					value = VarPlayerDefinition.varPlayers[script[pc++]];
				}
				if (opcode == 6) {  // load_next_level_xp {skill}
					value = PlayerSkillXpTable.levelExperience[PlayerSkillXpTable.skillBaseLevel[script[pc++]] - 1];
				}
				if (opcode == 7) {
					value = VarPlayerDefinition.varPlayers[script[pc++]] * 100 / 46875;
				}
				if (opcode == 8) { // load_combat_level
					value = PlayerList.self.combatLevel;
				}
				if (opcode == 9) { // load_total_level
					for (pc2 = 0; pc2 < 25; pc2++) {
						if (PlayerSkillXpTable.ENABLED_SKILLS[pc2]) {
							value += PlayerSkillXpTable.skillBaseLevel[pc2];
						}
					}
				}
				if (opcode == 10) { // load_inv_contains {interface id} {obj id}
					pc2 = script[pc++] << 16;
					pc2 += script[pc++];
					com = InterfaceList.getComponent(pc2);
					pc3 = script[pc++];
					if (pc3 != -1 && (!ObjTypeList.get(pc3).members || Static2.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (com.invSlotObjId[j] == pc3 + 1) {
								value = 999999999;
								break;
							}
						}
					}
				}
				if (opcode == 11) { // load_energy
					value = Player.energy;
				}
				if (opcode == 12) { // load_weight
					value = Player.weightCarried;
				}
				if (opcode == 13) { // load_bool {varp} {bit: 0..31}
					pc2 = VarPlayerDefinition.varPlayers[script[pc++]];
					@Pc(353) int leastSignificantBit = script[pc++];
					value = (0x1 << leastSignificantBit & pc2) == 0 ? 0 : 1;
				}
				if (opcode == 14) {
					pc2 = script[pc++];
					value = VarbitDefinition.getVarbitValue(pc2);
				}
				if (opcode == 18) {
					value = (PlayerList.self.xFine >> 7) + Camera.originX;
				}
				if (opcode == 19) {
					value = (PlayerList.self.zFine >> 7) + Camera.originZ;
				}
				if (opcode == 20) {
					value = script[pc++];
				}
				if (nextOperator == 0) {
					if (accumulatorMode == 0) {
						accumulator += value;
					}
					if (accumulatorMode == 1) {
						accumulator -= value;
					}
					if (accumulatorMode == 2 && value != 0) {
						accumulator /= value;
					}
					if (accumulatorMode == 3) {
						accumulator *= value;
					}
					accumulatorMode = 0;
				} else {
					accumulatorMode = nextOperator;
				}
			}
		} catch (@Pc(464) Exception local464) {
			return -1;
		}
	}

	@OriginalMember(owner = "runetek4.client!md", name = "a", descriptor = "(Lclient!be;I)Z")
	public static boolean isTrue(@OriginalArg(0) Component component) {
		if (component.cs1ComparisonOpcodes == null) {
			return false;
		}
		for (@Pc(14) int i = 0; i < component.cs1ComparisonOpcodes.length; i++) {
			@Pc(34) int value = executeClientscript(i, component);
			@Pc(39) int operand = component.scriptOperand[i];
			if (component.cs1ComparisonOpcodes[i] == 2) {
				if (operand <= value) {
					return false;
				}
			} else if (component.cs1ComparisonOpcodes[i] == 3) {
				if (value <= operand) {
					return false;
				}
			} else if (component.cs1ComparisonOpcodes[i] == 4) {
				if (value == operand) {
					return false;
				}
			} else if (operand != value) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(III[Lclient!be;IIIIBI)V")
	public static void renderComponent(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) Component[] components, @OriginalArg(4) int arg4, @OriginalArg(5) int layer, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(9) int parentRectangle) {
		if (GlRenderer.enabled) {
			GlRaster.setClip(arg0, arg6, arg4, arg7);
		} else {
			SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
			Rasterizer.prepare();
		}
		for (@Pc(18) int i = 0; i < components.length; i++) {
			@Pc(30) Component component = components[i];
			if (component != null && (component.overlayer == layer || layer == -1412584499 && component == aClass13_14)) {
				@Pc(57) int rectangle;
				if (parentRectangle == -1) {
					InterfaceList.rectangleX[InterfaceList.rectangles] = arg2 + component.x;
					InterfaceList.rectangleY[InterfaceList.rectangles] = component.y + arg1;
					InterfaceList.rectangleWidth[InterfaceList.rectangles] = component.width;
					InterfaceList.rectangleHeight[InterfaceList.rectangles] = component.height;
					rectangle = InterfaceList.rectangles++;
				} else {
					rectangle = parentRectangle;
				}
				component.rectangleLoop = client.loop;
				component.rectangle = rectangle;
				if (!component.if3 || !InterfaceList.method947(component)) {
					if (component.contentType > 0) {
						method13(component);
					}
					@Pc(114) int local114 = arg1 + component.y;
					@Pc(117) int alpha = component.alpha;
					@Pc(123) int local123 = component.x + arg2;
					if (Cheat.qaOpTest && (InterfaceList.getServerActiveProperties(component).anInt546 != 0 || component.type == 0) && alpha > 127) {
						alpha = 127;
					}
					@Pc(166) int local166;
					@Pc(164) int local164;
					if (component == aClass13_14) {
						if (layer != -1412584499 && !component.dragRenderBehavior) {
							anInt4696 = arg2;
							anInt3126 = arg1;
							aClass13Array13 = components;
							continue;
						}
						if (aBoolean172 && InterfaceList.aBoolean174) {
							local164 = Mouse.lastMouseY;
							local166 = Mouse.lastMouseX;
							local164 -= anInt4035;
							if (local164 < InterfaceList.anInt5103) {
								local164 = InterfaceList.anInt5103;
							}
							if (local164 + component.height > aClass13_1.height + InterfaceList.anInt5103) {
								local164 = aClass13_1.height + InterfaceList.anInt5103 - component.height;
							}
							local114 = local164;
							local166 -= anInt5388;
							if (anInt2225 > local166) {
								local166 = anInt2225;
							}
							if (aClass13_1.width + anInt2225 < component.width + local166) {
								local166 = aClass13_1.width + anInt2225 - component.width;
							}
							local123 = local166;
						}
						if (!component.dragRenderBehavior) {
							alpha = 128;
						}
					}
					@Pc(302) int local302;
					@Pc(291) int local291;
					@Pc(270) int local270;
					@Pc(276) int local276;
					if (component.type == 2) {
						local291 = arg7;
						local302 = arg4;
						local164 = arg6;
						local166 = arg0;
					} else {
						local164 = local114 > arg6 ? local114 : arg6;
						local166 = arg0 < local123 ? local123 : arg0;
						local270 = component.width + local123;
						local276 = local114 + component.height;
						if (component.type == 9) {
							local276++;
							local270++;
						}
						local291 = arg7 <= local276 ? arg7 : local276;
						local302 = local270 >= arg4 ? arg4 : local270;
					}
					if (!component.if3 || local302 > local166 && local164 < local291) {
						@Pc(468) int local468;
						@Pc(503) int memory;
						@Pc(514) int color;
						@Pc(518) int cardMemory;
						@Pc(556) int dragY;
						@Pc(563) int local563;
						@Pc(571) int local571;
						@Pc(545) int objId;
						if (component.contentType != 0) {
							if (component.contentType == 1337 || component.contentType == 1403 && GlRenderer.enabled) {
								InterfaceList.aClass13_26 = component;
								InterfaceList.anInt5574 = local114;
								anInt2503 = local123;
								drawScene(component.height, component.contentType == 1403, local123, component.width, local114);
								if (GlRenderer.enabled) {
									GlRaster.setClip(arg0, arg6, arg4, arg7);
								} else {
									SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
								}
								continue;
							}
							if (component.contentType == 1338) {
								if (!component.method478()) {
									continue;
								}
								MiniMap.render(rectangle, local114, local123, component);
								if (GlRenderer.enabled) {
									GlRaster.setClip(arg0, arg6, arg4, arg7);
								} else {
									SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
								}
								if (MiniMap.state != 0 && MiniMap.state != 3 || aBoolean108 || local166 > anInt3751 || anInt1892 < local164 || anInt3751 >= local302 || local291 <= anInt1892) {
									continue;
								}
								local270 = anInt3751 - local123;
								local276 = anInt1892 - local114;
								local468 = component.anIntArray37[local276];
								if (local270 < local468 || local270 > local468 + component.anIntArray45[local276]) {
									continue;
								}
								local276 -= component.height / 2;
								memory = Camera.orbitCameraYaw + MiniMap.minimapAnticheatAngle & 0x7FF;
								local270 -= component.width / 2;
								color = MathUtils.sin[memory];
								cardMemory = MathUtils.cos[memory];
								color = (MiniMap.minimapZoom + 256) * color >> 8;
								cardMemory = (MiniMap.minimapZoom + 256) * cardMemory >> 8;
								objId = cardMemory * local276 - color * local270 >> 11;
								dragY = local276 * color + local270 * cardMemory >> 11;
								local563 = PlayerList.self.xFine + dragY >> 7;
								local571 = PlayerList.self.zFine - objId >> 7;
								if (MiniMenu.aBoolean302 && (Static274.anInt4999 & 0x40) != 0) {
									@Pc(583) Component local583 = InterfaceList.method1418(MiniMenu.anInt2512, MiniMenu.anInt506);
									if (local583 == null) {
										MiniMenu.method1294();
									} else {
										MiniMenu.addActionRow(MiniMenu.anInt5393, 1L, MiniMenu.aClass100_961, local563, (short) 11, MiniMenu.aClass100_545, local571);
									}
									continue;
								}
								if (client.game == 1) {
									MiniMenu.addActionRow(-1, 1L, JString.EMPTY, local563, (short) 36, LocalizedText.FACEHERE, local571);
								}
								MiniMenu.addActionRow(-1, 1L, JString.EMPTY, local563, (short) 60, MiniMenu.walkText, local571);
								continue;
							}
							if (component.contentType == 1339) {
								if (component.method478()) {
									method3047(local123, local114, component, rectangle);
									if (GlRenderer.enabled) {
										GlRaster.setClip(arg0, arg6, arg4, arg7);
									} else {
										SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
									}
								}
								continue;
							}
							if (component.contentType == 1400) {
								WorldMap.method2225(local123, local114, component.height, component.width);
								InterfaceList.aBooleanArray100[rectangle] = true;
								InterfaceList.rectangleRedraw[rectangle] = true;
								if (GlRenderer.enabled) {
									GlRaster.setClip(arg0, arg6, arg4, arg7);
								} else {
									SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
								}
								continue;
							}
							if (component.contentType == 1401) {
								method4(local123, component.height, component.width, local114);
								InterfaceList.aBooleanArray100[rectangle] = true;
								InterfaceList.rectangleRedraw[rectangle] = true;
								if (GlRenderer.enabled) {
									GlRaster.setClip(arg0, arg6, arg4, arg7);
								} else {
									SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
								}
								continue;
							}
							if (component.contentType == 1402) {
								if (!GlRenderer.enabled) {
									Flames.render(local123, local114);
									InterfaceList.aBooleanArray100[rectangle] = true;
									InterfaceList.rectangleRedraw[rectangle] = true;
								}
								continue;
							}
							if (component.contentType == 1405) {
								if (!Cheat.displayFps) {
									continue;
								}
								local270 = component.width + local123;
								local276 = local114 + 15;
								Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_FPS2, JString.parseInt(GameShell.fps) }), local270, local276, 16776960, -1);
								local276 += 15;
								@Pc(795) Runtime runtime = Runtime.getRuntime();
								memory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
								color = 16776960;
								if (memory > 65536) {
									color = 16711680;
								}
								Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_MEM, JString.parseInt(memory), Cheat.DEBUG_MEM_UNIT}), local270, local276, color, -1);
								local276 += 15;
								if (GlRenderer.enabled) {
									color = 16776960;
									cardMemory = (GlCleaner.oncard_texture + GlCleaner.oncard_geometry + GlCleaner.oncard_2d) / 1024;
									if (cardMemory > 65536) {
										color = 16711680;
									}
									Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_CARD, JString.parseInt(cardMemory), Cheat.DEBUG_MEM_UNIT}), local270, local276, color, -1);
									local276 += 15;
								}
								cardMemory = 0;
								objId = 0;
								dragY = 0;
								for (local563 = 0; local563 < 28; local563++) {
									cardMemory += client.js5Providers[local563].getIndexSize();
									dragY += client.js5Providers[local563].getVerifiedGroups();
									objId += client.js5Providers[local563].getTotalVerifiedGroups();
								}
								local571 = dragY * 10000 / cardMemory;
								local563 = objId * 100 / cardMemory;
								@Pc(968) JString local968 = JString.concatenate(new JString[] { Cheat.DEBUG_CACHE, Static182.method3360(0, true, 2, (long) local571), aClass100_672, JString.parseInt(local563), aClass100_80});
								Fonts.p11Full.renderRight(local968, local270, local276, 16776960, -1);
								local276 += 12;
								InterfaceList.aBooleanArray100[rectangle] = true;
								InterfaceList.rectangleRedraw[rectangle] = true;
								continue;
							}
							if (component.contentType == 1406) {
								anInt3484 = local114;
								LoginManager.aClass13_13 = component;
								anInt3260 = local123;
								continue;
							}
						}
						if (!aBoolean108) {
							if (component.type == 0 && component.noClickThrough && anInt3751 >= local166 && anInt1892 >= local164 && anInt3751 < local302 && local291 > anInt1892 && !Cheat.qaOpTest) {
								MiniMenu.menuActionRow = 1;
								MiniMenu.cursors[0] = MiniMenu.anInt1092;
								MiniMenu.ops[0] = LocalizedText.CANCEL;
								MiniMenu.opBases[0] = JString.EMPTY;
								MiniMenu.actions[0] = 1005;
							}
							if (local166 <= anInt3751 && local164 <= anInt1892 && local302 > anInt3751 && local291 > anInt1892) {
								MiniMenu.addComponentEntries(anInt1892 - local114, -local123 + anInt3751, component);
							}
						}
						if (component.type == 0) {
							if (!component.if3 && InterfaceList.method947(component) && InterfaceList.aClass13_22 != component) {
								continue;
							}
							if (!component.if3) {
								if (component.scrollMaxV - component.height < component.scrollY) {
									component.scrollY = component.scrollMaxV - component.height;
								}
								if (component.scrollY < 0) {
									component.scrollY = 0;
								}
							}
							renderComponent(local166, local114 - component.scrollY, -component.scrollX + local123, components, local302, component.id, local164, local291, rectangle);
							if (component.createdComponents != null) {
								renderComponent(local166, local114 - component.scrollY, -component.scrollX + local123, component.createdComponents, local302, component.id, local164, local291, rectangle);
							}
							@Pc(1186) ComponentPointer local1186 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) component.id);
							if (local1186 != null) {
								if (local1186.anInt5879 == 0 && !aBoolean108 && anInt3751 >= local166 && local164 <= anInt1892 && local302 > anInt3751 && anInt1892 < local291 && !Cheat.qaOpTest) {
									MiniMenu.ops[0] = LocalizedText.CANCEL;
									MiniMenu.menuActionRow = 1;
									MiniMenu.cursors[0] = MiniMenu.anInt1092;
									MiniMenu.actions[0] = 1005;
									MiniMenu.opBases[0] = JString.EMPTY;
								}
								Static6.method86(local1186.interfaceId, local166, local302, local123, rectangle, local291, local164, local114);
							}
							if (GlRenderer.enabled) {
								GlRaster.setClip(arg0, arg6, arg4, arg7);
							} else {
								SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
								Rasterizer.prepare();
							}
						}
						if (InterfaceList.aBooleanArray116[rectangle] || Cheat.rectDebug > 1) {
							if (component.type == 0 && !component.if3 && component.scrollMaxV > component.height) {
								method1624(component.scrollY, component.scrollMaxV, component.width + local123, local114, component.height);
							}
							if (component.type != 1) {
								if (component.type == 2) {
									local270 = 0;
									for (local276 = 0; local276 < component.baseHeight; local276++) {
										for (local468 = 0; local468 < component.baseWidth; local468++) {
											color = local114 + local276 * (component.invMarginY + 32);
											memory = (component.invMarginX + 32) * local468 + local123;
											if (local270 < 20) {
												color += component.invOffsetY[local270];
												memory += component.invOffsetX[local270];
											}
											if (component.invSlotObjId[local270] > 0) {
												objId = component.invSlotObjId[local270] - 1;
												if (arg0 < memory + 32 && memory < arg4 && arg6 < color + 32 && color < arg7 || component == InterfaceList.clickedInventoryComponent && InterfaceList.selectedInventorySlot == local270) {
													@Pc(1476) Sprite sprite;
													if (MiniMenu.anInt5014 == 1 && MiniMenu.anInt4370 == local270 && component.id == MiniMap.anInt5062) {
														sprite = Inv.getObjectSprite(2, objId, component.objDrawText, component.invSlotObjCount[local270], 0);
													} else {
														sprite = Inv.getObjectSprite(1, objId, component.objDrawText, component.invSlotObjCount[local270], 3153952);
													}
													if (Rasterizer.textureHasTransparency) {
														InterfaceList.aBooleanArray100[rectangle] = true;
													}
													if (sprite == null) {
														InterfaceList.redraw(component);
													} else if (InterfaceList.clickedInventoryComponent == component && local270 == InterfaceList.selectedInventorySlot) {
														cardMemory = Mouse.lastMouseX - InterfaceList.clickedInventoryComponentX;
														dragY = Mouse.lastMouseY - InterfaceList.clickedInventoryComponentY;
														if (dragY < 5 && dragY > -5) {
															dragY = 0;
														}
														if (cardMemory < 5 && cardMemory > -5) {
															cardMemory = 0;
														}
														if (InterfaceList.lastItemDragTime < 5) {
															cardMemory = 0;
															dragY = 0;
														}
														// draw dragged icon (at half opacity)
														sprite.renderAlpha(memory + cardMemory, color - -dragY, 128);
														if (layer != -1) {
															@Pc(1571) Component local1571 = components[layer & 0xFFFF];
															@Pc(1577) int top;
															@Pc(1575) int bottom;
															if (GlRenderer.enabled) {
																bottom = GlRaster.clipBottom;
																top = GlRaster.clipTop;
															} else {
																top = Rasterizer.viewportTop;
																bottom = Rasterizer.viewportBottom;
															}
															@Pc(1611) int local1611;
															if (top > dragY + color && local1571.scrollY > 0) {
																local1611 = Protocol.sceneDelta * (top - dragY - color) / 3;
																if (local1611 > Protocol.sceneDelta * 10) {
																	local1611 = Protocol.sceneDelta * 10;
																}
																if (local1611 > local1571.scrollY) {
																	local1611 = local1571.scrollY;
																}
																local1571.scrollY -= local1611;
																InterfaceList.clickedInventoryComponentY += local1611;
																InterfaceList.redraw(local1571);
															}
															if (bottom < dragY + color + 32 && local1571.scrollY < local1571.scrollMaxV - local1571.height) {
																local1611 = (color + dragY + 32 - bottom) * Protocol.sceneDelta / 3;
																if (local1611 > Protocol.sceneDelta * 10) {
																	local1611 = Protocol.sceneDelta * 10;
																}
																if (local1571.scrollMaxV - local1571.scrollY - local1571.height < local1611) {
																	local1611 = local1571.scrollMaxV - local1571.height - local1571.scrollY;
																}
																local1571.scrollY += local1611;
																InterfaceList.clickedInventoryComponentY -= local1611;
																InterfaceList.redraw(local1571);
															}
														}
													} else if (component == MiniMenu.pressedInventoryComponent && local270 == MiniMenu.anInt5444) {
														sprite.renderAlpha(memory, color, 128);
													} else {
														sprite.render(memory, color);
													}
												}
											} else if (component.invSprite != null && local270 < 20) {
												@Pc(1381) Sprite local1381 = component.method482(local270);
												if (local1381 != null) {
													local1381.render(memory, color);
												} else if (Component.aBoolean72) {
													InterfaceList.redraw(component);
												}
											}
											local270++;
										}
									}
								} else if (component.type == 3) {
									if (isTrue(component)) {
										local270 = component.activeColor;
										if (InterfaceList.aClass13_22 == component && component.anInt475 != 0) {
											local270 = component.anInt475;
										}
									} else {
										local270 = component.color;
										if (component == InterfaceList.aClass13_22 && component.overColor != 0) {
											local270 = component.overColor;
										}
									}
									if (alpha == 0) {
										if (component.filled) {
											if (GlRenderer.enabled) {
												GlRaster.fillRect(local123, local114, component.width, component.height, local270);
											} else {
												SoftwareRaster.fillRect(local123, local114, component.width, component.height, local270);
											}
										} else if (GlRenderer.enabled) {
											GlRaster.drawRect(local123, local114, component.width, component.height, local270);
										} else {
											SoftwareRaster.drawRect(local123, local114, component.width, component.height, local270);
										}
									} else if (component.filled) {
										if (GlRenderer.enabled) {
											GlRaster.fillRectAlpha(local123, local114, component.width, component.height, local270, 256 - (alpha & 0xFF));
										} else {
											SoftwareRaster.fillRectAlpha(local123, local114, component.width, component.height, local270, 256 - (alpha & 0xFF));
										}
									} else if (GlRenderer.enabled) {
										GlRaster.drawRectAlpha(local123, local114, component.width, component.height, local270, 256 - (alpha & 0xFF));
									} else {
										SoftwareRaster.drawRectAlpha(local123, local114, component.width, component.height, local270, 256 - (alpha & 0xFF));
									}
								} else {
									@Pc(1921) Font local1921;
									if (component.type == 4) {
										local1921 = component.getFont(Sprites.nameIcons);
										if (local1921 != null) {
											@Pc(1934) JString local1934 = component.text;
											if (isTrue(component)) {
												local276 = component.activeColor;
												if (InterfaceList.aClass13_22 == component && component.anInt475 != 0) {
													local276 = component.anInt475;
												}
												if (component.activeText.length() > 0) {
													local1934 = component.activeText;
												}
											} else {
												local276 = component.color;
												if (InterfaceList.aClass13_22 == component && component.overColor != 0) {
													local276 = component.overColor;
												}
											}
											if (component.if3 && component.objId != -1) {
												@Pc(1989) ObjType local1989 = ObjTypeList.get(component.objId);
												local1934 = local1989.name;
												if (local1934 == null) {
													local1934 = MiniMenu.NULL;
												}
												if ((local1989.stackable == 1 || component.objCount != 1) && component.objCount != -1) {
													local1934 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local1934, JString.aClass100_375, method1548(component.objCount) });
												}
											}
											if (aClass13_10 == component) {
												local276 = component.color;
												local1934 = LocalizedText.PLEASEWAIT;
											}
											if (!component.if3) {
												local1934 = interpolate(component, local1934);
											}
											local1921.drawInterfaceText(local1934, local123, local114, component.width, component.height, local276, component.shadowed ? 0 : -1, component.halign, component.valign, component.vpadding);
										} else if (Component.aBoolean72) {
											InterfaceList.redraw(component);
										}
									} else if (component.type == 5) {
										@Pc(2094) Sprite sprite;
										if (component.if3) {
											if (component.objId == -1) {
												sprite = component.method489(false);
											} else {
												sprite = Inv.getObjectSprite(component.outlineThickness, component.objId, component.objDrawText, component.objCount, component.shadowColor);
											}
											if (sprite != null) {
												local276 = sprite.innerWidth;
												local468 = sprite.innerHeight;
												if (component.spriteTiling) {
													memory = (local276 + component.width - 1) / local276;
													color = (component.height + local468 - 1) / local468;
													if (GlRenderer.enabled) {
														GlRaster.method1183(local123, local114, component.width + local123, component.height + local114);
														@Pc(2274) boolean local2274 = IntUtils.isPowerOfTwo(sprite.width);
														@Pc(2279) boolean local2279 = IntUtils.isPowerOfTwo(sprite.height);
														@Pc(2282) GlSprite local2282 = (GlSprite) sprite;
														if (local2274 && local2279) {
															if (alpha == 0) {
																local2282.method1429(local123, local114, memory, color);
															} else {
																local2282.method1426(local123, local114, 256 - (alpha & 0xFF), memory, color);
															}
														} else if (local2274) {
															for (local563 = 0; local563 < color; local563++) {
																if (alpha == 0) {
																	local2282.method1429(local123, local563 * local468 + local114, memory, 1);
																} else {
																	local2282.method1426(local123, local114 + local563 * local468, -(alpha & 0xFF) + 256, memory, 1);
																}
															}
														} else if (local2279) {
															for (local563 = 0; local563 < memory; local563++) {
																if (alpha == 0) {
																	local2282.method1429(local276 * local563 + local123, local114, 1, color);
																} else {
																	local2282.method1426(local276 * local563 + local123, local114, 256 - (alpha & 0xFF), 1, color);
																}
															}
														} else {
															for (local563 = 0; local563 < memory; local563++) {
																for (local571 = 0; local571 < color; local571++) {
																	if (alpha == 0) {
																		sprite.render(local123 + local276 * local563, local468 * local571 + local114);
																	} else {
																		sprite.renderAlpha(local563 * local276 + local123, local468 * local571 + local114, 256 - (alpha & 0xFF));
																	}
																}
															}
														}
														GlRaster.setClip(arg0, arg6, arg4, arg7);
													} else {
														SoftwareRaster.method2498(local123, local114, local123 + component.width, local114 - -component.height);
														for (cardMemory = 0; cardMemory < memory; cardMemory++) {
															for (dragY = 0; dragY < color; dragY++) {
																if (component.angle2d != 0) {
																	sprite.renderAngled(local114 + local468 * dragY + local468 / 2, component.angle2d, 4096, cardMemory * local276 + local123 + local276 / 2);
																} else if (alpha == 0) {
																	sprite.render(cardMemory * local276 + local123, local468 * dragY + local114);
																} else {
																	sprite.renderAlpha(cardMemory * local276 + local123, local114 + dragY * local468, 256 - (alpha & 0xFF));
																}
															}
														}
														SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
													}
												} else {
													memory = component.width * 4096 / local276;
													if (component.angle2d != 0) {
														sprite.renderAngled(local114 + component.height / 2, component.angle2d, memory, local123 + component.width / 2);
													} else if (alpha != 0) {
														sprite.renderAlpha(local123, local114, component.width, component.height, 256 - (alpha & 0xFF));
													} else if (local276 == component.width && local468 == component.height) {
														sprite.render(local123, local114);
													} else {
														// render icons in a container i.e bank icons
														sprite.renderResized(local123, local114, component.width, component.height);
													}
												}
											} else if (Component.aBoolean72) {
												InterfaceList.redraw(component);
											}
										} else {
											sprite = component.method489(isTrue(component));
											if (sprite != null) {
												sprite.render(local123, local114);
											} else if (Component.aBoolean72) {
												InterfaceList.redraw(component);
											}
										}
									} else {
										@Pc(2611) ObjType local2611;
										if (component.type == 6) {
											@Pc(2587) boolean local2587 = isTrue(component);
											@Pc(2589) Model local2589 = null;
											if (local2587) {
												local276 = component.activeModelSeqId;
											} else {
												local276 = component.modelSeqId;
											}
											memory = 0;
											if (component.objId != -1) {
												local2611 = ObjTypeList.get(component.objId);
												if (local2611 != null) {
													local2611 = local2611.getMeshAddress(component.objCount);
													@Pc(2630) SeqType local2630 = local276 == -1 ? null : SeqTypeList.getAnimationSequence(local276);
													local2589 = local2611.getModel(component.anInt496, component.anInt500, local2630, 1, component.anInt510);
													if (local2589 == null) {
														InterfaceList.redraw(component);
													} else {
														memory = -local2589.getMinY() / 2;
													}
												}
											} else if (component.modelType == 5) {
												if (component.modelId == -1) {
													local2589 = PlayerAppearance.DEFAULT.method1954(null, -1, null, null, 0, -1, 0, -1, -1);
												} else {
													color = component.modelId & 0x7FF;
													if (color == PlayerList.selfId) {
														color = 2047;
													}
													@Pc(2751) Player local2751 = PlayerList.players[color];
													@Pc(2760) SeqType local2760 = local276 == -1 ? null : SeqTypeList.getAnimationSequence(local276);
													if (local2751 != null && (int) local2751.username.encode37() << 11 == (component.modelId & 0xFFFFF800)) {
														local2589 = local2751.appearance.method1954(null, -1, null, local2760, 0, -1, 0, component.anInt510, 0);
													}
												}
											} else if (local276 == -1) {
												local2589 = component.method488(-1, null, -1, 0, local2587, PlayerList.self.appearance);
												if (local2589 == null && Component.aBoolean72) {
													InterfaceList.redraw(component);
												}
											} else {
												@Pc(2689) SeqType local2689 = SeqTypeList.getAnimationSequence(local276);
												local2589 = component.method488(component.anInt496, local2689, component.anInt510, component.anInt500, local2587, PlayerList.self.appearance);
												if (local2589 == null && Component.aBoolean72) {
													InterfaceList.redraw(component);
												}
											}
											if (local2589 != null) {
												if (component.anInt451 > 0) {
													color = (component.width << 8) / component.anInt451;
												} else {
													color = 256;
												}
												if (component.anInt526 <= 0) {
													cardMemory = 256;
												} else {
													cardMemory = (component.height << 8) / component.anInt526;
												}
												dragY = local123 + component.width / 2 + (color * component.anInt495 >> 8);
												objId = component.height / 2 + local114 + (cardMemory * component.anInt481 >> 8);
												if (GlRenderer.enabled) {
													if (component.modelOrtho) {
														GlRenderer.method4182(dragY, objId, component.modelZoom, component.aShort11, color, cardMemory);
													} else {
														GlRenderer.method4148(dragY, objId, color, cardMemory);
														GlRenderer.method4152((float) component.aShort10, (float) component.aShort11 * 1.5F);
													}
													GlRenderer.restoreLighting();
													GlRenderer.setDepthTestEnabled(true);
													GlRenderer.setFogEnabled(false);
													FogManager.init(Preferences.brightness);
													if (aBoolean299) {
														GlRaster.method1177();
														GlRenderer.clearDepthBuffer();
														GlRaster.setClip(arg0, arg6, arg4, arg7);
														aBoolean299 = false;
													}
													if (component.aBoolean34) {
														GlRenderer.disableDepthMask();
													}
													local563 = MathUtils.sin[component.modelXAngle] * component.modelZoom >> 16;
													local571 = component.modelZoom * MathUtils.cos[component.modelXAngle] >> 16;
													if (component.if3) {
														local2589.setCamera(component.modelYAngle, component.modelYOffset, component.modelXAngle, component.modelXOffset, component.modelZOffset + local563 + memory, component.modelZOffset + local571, -1L);
													} else {
														local2589.setCamera(component.modelYAngle, 0, component.modelXAngle, 0, local563, local571, -1L);
													}
													if (component.aBoolean34) {
														GlRenderer.enableDepthMask();
													}
												} else {
													Rasterizer.setBounds(dragY, objId);
													local563 = MathUtils.sin[component.modelXAngle] * component.modelZoom >> 16;
													local571 = component.modelZoom * MathUtils.cos[component.modelXAngle] >> 16;
													if (!component.if3) {
														local2589.setCamera(component.modelYAngle, 0, component.modelXAngle, 0, local563, local571, -1L);
													} else if (component.modelOrtho) {
														((SoftwareModel) local2589).method4591(component.modelYAngle, component.modelYOffset, component.modelXAngle, component.modelXOffset, component.modelZOffset + memory + local563, local571 + component.modelZOffset, component.modelZoom);
													} else {
														local2589.setCamera(component.modelYAngle, component.modelYOffset, component.modelXAngle, component.modelXOffset, component.modelZOffset + local563 + memory, local571 + component.modelZOffset, -1L);
													}
													Rasterizer.prepareOffsets();
												}
											}
										} else {
											if (component.type == 7) {
												local1921 = component.getFont(Sprites.nameIcons);
												if (local1921 == null) {
													if (Component.aBoolean72) {
														InterfaceList.redraw(component);
													}
													continue;
												}
												local276 = 0;
												for (local468 = 0; local468 < component.baseHeight; local468++) {
													for (memory = 0; memory < component.baseWidth; memory++) {
														if (component.invSlotObjId[local276] > 0) {
															local2611 = ObjTypeList.get(component.invSlotObjId[local276] - 1);
															@Pc(3159) JString local3159;
															if (local2611.stackable != 1 && component.invSlotObjCount[local276] == 1) {
																local3159 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local2611.name, Static230.aClass100_978 });
															} else {
																local3159 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local2611.name, JString.aClass100_375, method1548(component.invSlotObjCount[local276]) });
															}
															dragY = local123 + memory * (component.invMarginX + 115);
															objId = (component.invMarginY + 12) * local468 + local114;
															if (component.halign == 0) {
																local1921.renderLeft(local3159, dragY, objId, component.color, component.shadowed ? 0 : -1);
															} else if (component.halign == 1) {
																local1921.renderCenter(local3159, dragY + 57, objId, component.color, component.shadowed ? 0 : -1);
															} else {
																local1921.renderRight(local3159, dragY + 115 - 1, objId, component.color, component.shadowed ? 0 : -1);
															}
														}
														local276++;
													}
												}
											}
											if (component.type == 8 && Protocol.aClass13_11 == component && Protocol.anInt5235 == anInt4504) {
												local276 = 0;
												local270 = 0;
												@Pc(3297) JString local3297 = component.text;
												@Pc(3299) Font local3299 = Fonts.p12Full;
												local3297 = interpolate(component, local3297);
												@Pc(3325) JString local3325;
												while (local3297.length() > 0) {
													cardMemory = local3297.indexOf(JString.aClass100_556);
													if (cardMemory == -1) {
														local3325 = local3297;
														local3297 = JString.EMPTY;
													} else {
														local3325 = local3297.substring(cardMemory, 0);
														local3297 = local3297.substring(cardMemory + 4);
													}
													dragY = local3299.getStringWidth(local3325);
													local276 += local3299.characterDefaultHeight + 1;
													if (local270 < dragY) {
														local270 = dragY;
													}
												}
												dragY = local114 + component.height + 5;
												local270 += 6;
												local276 += 7;
												if (dragY + local276 > arg7) {
													dragY = arg7 - local276;
												}
												cardMemory = local123 + component.width - local270 - 5;
												if (cardMemory < local123 + 5) {
													cardMemory = local123 + 5;
												}
												if (local270 + cardMemory > arg4) {
													cardMemory = arg4 - local270;
												}
												if (GlRenderer.enabled) {
													GlRaster.fillRect(cardMemory, dragY, local270, local276, 16777120);
													GlRaster.drawRect(cardMemory, dragY, local270, local276, 0);
												} else {
													SoftwareRaster.fillRect(cardMemory, dragY, local270, local276, 16777120);
													SoftwareRaster.drawRect(cardMemory, dragY, local270, local276, 0);
												}
												local3297 = component.text;
												objId = dragY + local3299.characterDefaultHeight + 2;
												local3297 = interpolate(component, local3297);
												while (local3297.length() > 0) {
													local563 = local3297.indexOf(JString.aClass100_556);
													if (local563 == -1) {
														local3325 = local3297;
														local3297 = JString.EMPTY;
													} else {
														local3325 = local3297.substring(local563, 0);
														local3297 = local3297.substring(local563 + 4);
													}
													local3299.renderLeft(local3325, cardMemory + 3, objId, 0, -1);
													objId += local3299.characterDefaultHeight + 1;
												}
											}
											if (component.type == 9) {
												if (component.aBoolean20) {
													local468 = local123 + component.width;
													local276 = local114 + component.height;
													memory = local114;
												} else {
													local276 = local114;
													memory = local114 + component.height;
													local468 = local123 + component.width;
												}
												if (component.lineWidth == 1) {
													if (GlRenderer.enabled) {
														GlRaster.drawDiagonalLine(local123, local276, local468, memory, component.color);
													} else {
														Rasterizer.drawDiagonalLine(local123, local276, local468, memory, component.color);
													}
												} else if (GlRenderer.enabled) {
													GlRaster.method1181(local123, local276, local468, memory, component.color, component.lineWidth);
												} else {
													SoftwareRaster.method2494(local123, local276, local468, memory, component.color, component.lineWidth);
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

	@OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(BLclient!be;)V")
	public static void method13(@OriginalArg(1) Component arg0) {
		@Pc(16) int local16 = arg0.contentType;
		if (local16 == 324) {
			if (anInt3851 == -1) {
				anInt3851 = arg0.spriteId;
				anInt3502 = arg0.activeSpriteId;
			}
			if (PlayerAppearance.DEFAULT.aBoolean141) {
				arg0.spriteId = anInt3851;
			} else {
				arg0.spriteId = anInt3502;
			}
		} else if (local16 == 325) {
			if (anInt3851 == -1) {
				anInt3502 = arg0.activeSpriteId;
				anInt3851 = arg0.spriteId;
			}
			if (PlayerAppearance.DEFAULT.aBoolean141) {
				arg0.spriteId = anInt3502;
			} else {
				arg0.spriteId = anInt3851;
			}
		} else if (local16 == 327) {
			arg0.modelXAngle = 150;
			arg0.modelYAngle = (int) (Math.sin((double) client.loop / 40.0D) * 256.0D) & 0x7FF;
			arg0.modelType = 5;
			arg0.modelId = -1;
		} else if (local16 == 328) {
			if (PlayerList.self.username == null) {
				arg0.modelId = 0;
			} else {
				arg0.modelXAngle = 150;
				arg0.modelYAngle = (int) (Math.sin((double) client.loop / 40.0D) * 256.0D) & 0x7FF;
				arg0.modelType = 5;
				arg0.modelId = ((int) PlayerList.self.username.encode37() << 11) + 2047;
				arg0.anInt496 = PlayerList.self.anInt3388;
				arg0.anInt500 = 0;
				arg0.modelSeqId = PlayerList.self.movementSeqId;
				arg0.anInt510 = PlayerList.self.anInt3407;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IIZIII)V")
	public static void drawScene(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		Static136.anInt3325++;
		Static210.method3711();
		if (!arg1) {
			Game.pushPlayers(true);
			Game.pushNpcs(true);
			Game.pushPlayers(false);
		}
		Game.pushNpcs(false);
		if (!arg1) {
			Game.pushProjectiles();
		}
		Game.pushSpotanims();
		if (GlRenderer.enabled) {
			Static115.method2314(arg3, arg4, arg0, arg2, true);
			arg2 = aClass6.anInt983;
			arg4 = Static24.anInt773;
			arg3 = Static166.anInt4055;
			arg0 = Static245.anInt5377;
		}
		@Pc(59) int pitch;
		@Pc(57) int local57;
		if (Camera.cameraType == 1) {
			local57 = Camera.cameraAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
			pitch = Camera.orbitCameraPitch;
			if (pitch < Camera.cameraPitchClamp / 256) {
				pitch = Camera.cameraPitchClamp / 256;
			}
			if (Camera.cameraModifierEnabled[4] && Camera.cameraAmplitude[4] + 128 > pitch) {
				pitch = Camera.cameraAmplitude[4] + 128;
			}
			Camera.orbitCamera(Camera.cameraX, arg0, SceneGraph.getTileHeight(Player.plane, PlayerList.self.xFine, PlayerList.self.zFine) - 50, 600 - -(pitch * 3), local57, Camera.cameraZ, pitch);
		}
		local57 = Camera.cameraY;
		pitch = Camera.renderX;
		@Pc(121) int cameraZ = Camera.renderZ;
		@Pc(123) int cameraPitch = Camera.cameraPitch;
		@Pc(125) int cameraYaw = Camera.cameraYaw;
		@Pc(127) int type;
		@Pc(171) int jitter;
		for (type = 0; type < 5; type++) {
			if (Camera.cameraModifierEnabled[type]) {
				jitter = (int) ((double) -Camera.cameraModifierJitter[type] + (double) (Camera.cameraModifierJitter[type] * 2 + 1) * Math.random() + Math.sin((double) Static31.cameraModifierCycle[type] * ((double) Camera.cameraFrequency[type] / 100.0D)) * (double) Camera.cameraAmplitude[type]);
				if (type == 3) {
					Camera.cameraYaw = jitter + Camera.cameraYaw & 0x7FF;
				}
				if (type == 4) {
					Camera.cameraPitch += jitter;
					if (Camera.cameraPitch < 128) {
						Camera.cameraPitch = 128;
					}
					if (Camera.cameraPitch > 383) {
						Camera.cameraPitch = 383;
					}
				}
				if (type == 2) {
					Camera.renderZ += jitter;
				}
				if (type == 1) {
					Camera.cameraY += jitter;
				}
				if (type == 0) {
					Camera.renderX += jitter;
				}
			}
		}
		FloTypeList.method4302();
		if (GlRenderer.enabled) {
			GlRaster.setClip(arg2, arg4, arg2 + arg3, arg4 - -arg0);
			@Pc(248) float local248 = (float) Camera.cameraPitch * 0.17578125F;
			@Pc(253) float local253 = (float) Camera.cameraYaw * 0.17578125F;
			if (Camera.cameraType == 3) {
				local248 = Camera.aFloat15 * 360.0F / 6.2831855F;
				local253 = Camera.aFloat10 * 360.0F / 6.2831855F;
			}
			GlRenderer.method4171(arg2, arg4, arg3, arg0, arg3 / 2 + arg2, arg4 - -(arg0 / 2), local248, local253, Static223.anInt5029, Static223.anInt5029);
		} else {
			SoftwareRaster.setClip(arg2, arg4, arg3 + arg2, arg0 + arg4);
			Rasterizer.prepare();
		}
		if (aBoolean108 || anInt3751 < arg2 || anInt3751 >= arg3 + arg2 || arg4 > anInt1892 || arg0 + arg4 <= anInt1892) {
			RawModel.allowInput = false;
			MiniMenu.anInt7 = 0;
		} else {
			RawModel.allowInput = true;
			MiniMenu.anInt7 = 0;
			jitter = Rasterizer.screenUpperX;
			@Pc(344) int local344 = Rasterizer.screenLowerY;
			type = Rasterizer.screenLowerX;
			GlModel.anInt3582 = type + (jitter - type) * (-arg2 + anInt3751) / arg3;
			@Pc(361) int local361 = Rasterizer.screenUpperY;
			RawModel.anInt1053 = (local361 - local344) * (anInt1892 - arg4) / arg0 + local344;
		}
		client.audioLoop();
		@Pc(387) byte local387 = Static236.method4047() == 2 ? (byte) Static136.anInt3325 : 1;
		if (GlRenderer.enabled) {
			GlRenderer.restoreLighting();
			GlRenderer.setDepthTestEnabled(true);
			GlRenderer.setFogEnabled(true);
			if (client.gameState == 10) {
				jitter = FogManager.method2235(Protocol.sceneDelta, Camera.renderZ >> 10, Preferences.brightness, Camera.renderX >> 10);
			} else {
				jitter = FogManager.method2235(Protocol.sceneDelta, PlayerList.self.movementQueueZ[0] >> 3, Preferences.brightness, PlayerList.self.movementQueueX[0] >> 3);
			}
			Static120.method2394(client.loop, !Static11.aBoolean15);
			GlRenderer.clearColorAndDepthBuffers(jitter);
			Static143.method2731(Camera.cameraPitch, Camera.renderZ, Camera.cameraY, Camera.renderX, Camera.cameraYaw);
			GlRenderer.anInt5323 = client.loop;
			Static156.method2954(Camera.renderX, Camera.cameraY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, Player.plane + 1, local387, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
			aBoolean299 = true;
			Static120.method2390();
			Static143.method2731(0, 0, 0, 0, 0);
			client.audioLoop();
			Static223.method3858();
			Static142.method2726(arg4, arg3, arg2, Static223.anInt5029, arg0, Static223.anInt5029);
			method4000(arg3, arg2, arg0, Static223.anInt5029, Static223.anInt5029, arg4);
		} else {
			SoftwareRaster.fillRect(arg2, arg4, arg3, arg0, 0);
			Static156.method2954(Camera.renderX, Camera.cameraY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, Player.plane + 1, local387, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
			client.audioLoop();
			Static223.method3858();
			Static142.method2726(arg4, arg3, arg2, 256, arg0, 256);
			method4000(arg3, arg2, arg0, 256, 256, arg4);
		}
		((Js5GlTextureProvider) Rasterizer.textureProvider).method3239(Protocol.sceneDelta);
		Static115.method2310(arg3, arg4, arg0, arg2);
		Camera.cameraPitch = cameraPitch;
		Camera.renderZ = cameraZ;
		Camera.cameraY = local57;
		Camera.renderX = pitch;
		Camera.cameraYaw = cameraYaw;
		if (aBoolean43 && client.js5NetQueue.getUrgentRequestCount() == 0) {
			aBoolean43 = false;
		}
		if (aBoolean43) {
			if (GlRenderer.enabled) {
				GlRaster.fillRect(arg2, arg4, arg3, arg0, 0);
			} else {
				SoftwareRaster.fillRect(arg2, arg4, arg3, arg0, 0);
			}
			Fonts.drawTextOnScreen(false, LocalizedText.LOADING);
		}
		if (!arg1 && !aBoolean43 && !aBoolean108 && arg2 <= anInt3751 && arg3 + arg2 > anInt3751 && arg4 <= anInt1892 && arg0 + arg4 > anInt1892) {
			Static176.method3304(arg4, arg3, arg0, arg2, anInt1892, anInt3751);
		}
	}

	@OriginalMember(owner = "runetek4.client!mj", name = "a", descriptor = "(IILclient!be;IB)V")
	public static void method3047(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Component arg2, @OriginalArg(3) int arg3) {
		if (GlRenderer.enabled) {
			GlRaster.setClip(arg0, arg1, arg2.width + arg0, arg2.height + arg1);
		}
		if (MiniMap.state >= 3) {
			if (GlRenderer.enabled) {
				@Pc(44) Sprite local44 = arg2.method489(false);
				if (local44 != null) {
					local44.render(arg0, arg1);
				}
			} else {
				Rasterizer.method2504(arg0, arg1, arg2.anIntArray37, arg2.anIntArray45);
			}
		} else if (GlRenderer.enabled) {
			((GlSprite) Static106.aClass3_Sub2_Sub1_7).renderRotatedTransparent(arg0, arg1, arg2.width, arg2.height, Static106.aClass3_Sub2_Sub1_7.width / 2, Static106.aClass3_Sub2_Sub1_7.height / 2, Camera.orbitCameraYaw, 256, (GlSprite) arg2.method489(false));
		} else {
			((SoftwareSprite) Static106.aClass3_Sub2_Sub1_7).method313(arg0, arg1, arg2.width, arg2.height, Static106.aClass3_Sub2_Sub1_7.width / 2, Static106.aClass3_Sub2_Sub1_7.height / 2, Camera.orbitCameraYaw, arg2.anIntArray37, arg2.anIntArray45);
		}
		InterfaceList.rectangleRedraw[arg3] = true;
	}

	@OriginalMember(owner = "client!a", name = "a", descriptor = "(IIIII)V")
	public static void method4(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		if (GlRenderer.enabled) {
			GlRaster.setClip(arg0, arg3, arg2 + arg0, arg1 + arg3);
			GlRaster.fillRect(arg0, arg3, arg2, arg1, 0);
		} else {
			SoftwareRaster.setClip(arg0, arg3, arg2 + arg0, arg3 + arg1);
			SoftwareRaster.fillRect(arg0, arg3, arg2, arg1, 0);
		}

		if (WorldMap.loadPercentage < 100) {
			return;
		}

		if (WorldMap.aClass3_Sub2_Sub1_2 == null || arg2 != WorldMap.aClass3_Sub2_Sub1_2.width || WorldMap.aClass3_Sub2_Sub1_2.height != arg1) {
			@Pc(63) SoftwareSprite local63 = new SoftwareSprite(arg2, arg1);
			SoftwareRaster.setSize(local63.pixels, arg2, arg1);
			Static214.method4364(arg2, 0, Static48.anInt1449, 0, 0, WorldMap.length, arg1, 0);
			if (GlRenderer.enabled) {
				WorldMap.aClass3_Sub2_Sub1_2 = new GlSprite(local63);
			} else {
				WorldMap.aClass3_Sub2_Sub1_2 = local63;
			}
			if (GlRenderer.enabled) {
				SoftwareRaster.destinationPixels = null;
			} else {
				SoftwareRaster.frameBuffer.makeTarget();
			}
		}
		WorldMap.aClass3_Sub2_Sub1_2.drawPixels(arg0, arg3);
		@Pc(147) int local147 = arg1 * anInt2884 / WorldMap.length + arg3;
		@Pc(153) int local153 = Static37.anInt1176 * arg1 / WorldMap.length;
		@Pc(161) int local161 = arg0 + arg2 * anInt2882 / Static48.anInt1449;
		@Pc(167) int local167 = arg2 * Static89.anInt2387 / Static48.anInt1449;
		@Pc(169) int local169 = 16711680;
		if (client.game == 1) {
			local169 = 16777215;
		}
		if (GlRenderer.enabled) {
			GlRaster.fillRectAlpha(local161, local147, local167, local153, local169, 128);
			GlRaster.drawRect(local161, local147, local167, local153, local169);
		} else {
			SoftwareRaster.fillRectAlpha(local161, local147, local167, local153, local169, 128);
			SoftwareRaster.drawRect(local161, local147, local167, local153, local169);
		}
		if (WorldMap.anInt1864 <= 0) {
			return;
		}
		@Pc(225) int local225;
		if (anInt2428 > 10) {
			local225 = (20 - anInt2428) * 25;
		} else {
			local225 = anInt2428 * 25;
		}
		for (@Pc(238) Class3_Sub26 local238 = (Class3_Sub26) Static145.aClass69_84.head(); local238 != null; local238 = (Class3_Sub26) Static145.aClass69_84.next()) {
			if (local238.anInt4308 == CacheArchive.anInt172) {
				@Pc(258) int local258 = arg3 + local238.anInt4314 * arg1 / WorldMap.length;
				@Pc(267) int local267 = arg2 * local238.anInt4307 / Static48.anInt1449 + arg0;
				if (GlRenderer.enabled) {
					GlRaster.fillRectAlpha(local267 - 2, local258 - 2, 4, 4, 16776960, local225);
				} else {
					SoftwareRaster.fillRectAlpha(local267 - 2, local258 - 2, 4, 4, 16776960, local225);
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!fn", name = "a", descriptor = "(BIIIII)V")
	public static void method1624(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		Static241.aClass36Array16[0].drawImage(arg2, arg3);
		Static241.aClass36Array16[1].drawImage(arg2, arg4 + arg3 - 16);
		@Pc(35) int local35 = arg4 * (arg4 - 32) / arg1;
		if (local35 < 8) {
			local35 = 8;
		}
		@Pc(54) int local54 = arg0 * (arg4 - local35 - 32) / (arg1 - arg4);
		if (!GlRenderer.enabled) {
			SoftwareRaster.fillRect(arg2, arg3 + 16, 16, arg4 - 32, anInt4306);
			SoftwareRaster.fillRect(arg2, local54 + arg3 + 16, 16, local35, anInt1704);
			Rasterizer.drawVerticalLine(arg2, local54 + arg3 + 16, local35, anInt4938);
			Rasterizer.drawVerticalLine(arg2 + 1, local54 + 16 + arg3, local35, anInt4938);
			SoftwareRaster.drawHorizontalLine(arg2, arg3 + local54 + 16, 16, anInt4938);
			SoftwareRaster.drawHorizontalLine(arg2, arg3 + local54 + 17, 16, anInt4938);
			Rasterizer.drawVerticalLine(arg2 + 15, local54 + 16 + arg3, local35, anInt671);
			Rasterizer.drawVerticalLine(arg2 + 14, arg3 - -17 - -local54, local35 - 1, anInt671);
			SoftwareRaster.drawHorizontalLine(arg2, local35 + arg3 + local54 + 15, 16, anInt671);
			SoftwareRaster.drawHorizontalLine(arg2 + 1, local35 + arg3 - (-local54 + -14), 15, anInt671);
			return;
		}
		GlRaster.fillRect(arg2, arg3 + 16, 16, arg4 - 32, anInt4306);
		GlRaster.fillRect(arg2, arg3 + local54 + 16, 16, local35, anInt1704);
		GlRaster.method1176(arg2, local54 + arg3 + 16, local35, anInt4938);
		GlRaster.method1176(arg2 + 1, local54 + 16 + arg3, local35, anInt4938);
		GlRaster.method1174(arg2, local54 + arg3 + 16, 16, anInt4938);
		GlRaster.method1174(arg2, local54 + arg3 + 17, 16, anInt4938);
		GlRaster.method1176(arg2 + 15, arg3 + (16 - -local54), local35, anInt671);
		GlRaster.method1176(arg2 + 14, arg3 - -local54 + 17, local35 - 1, anInt671);
		GlRaster.method1174(arg2, local35 + arg3 + local54 + 15, 16, anInt671);
		GlRaster.method1174(arg2 + 1, arg3 + 14 - -local54 + local35, 15, anInt671);
	}

	@OriginalMember(owner = "runetek4.client!fi", name = "a", descriptor = "(BI)Lclient!na;")
	public static JString method1548(@OriginalArg(1) int arg0) {
		@Pc(9) JString local9 = JString.parseInt(arg0);
		for (@Pc(21) int local21 = local9.length() - 3; local21 > 0; local21 -= 3) {
			local9 = JString.concatenate(new JString[] { local9.substring(local21, 0), Static159.aClass100_760, local9.substring(local21) });
		}
		if (local9.length() > 9) {
			return JString.concatenate(new JString[] { Static250.aClass100_1043, local9.substring(local9.length() - 8, 0), LocalizedText.MILLION_SHORT, Static123.aClass100_593, local9, Static116.aClass100_583 });
		} else if (local9.length() > 6) {
			return JString.concatenate(new JString[] { Static119.aClass100_589, local9.substring(local9.length() - 4, 0), LocalizedText.THOUSAND_SHORT, Static123.aClass100_593, local9, Static116.aClass100_583 });
		} else {
			return JString.concatenate(new JString[] { Static278.aClass100_1101, local9, Static230.aClass100_978 });
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BILclient!jl;)V")
	public static void runClientScripts(@OriginalArg(1) int arg0, @OriginalArg(2) HookRequest arg1) {
		@Pc(4) Object[] listeners = arg1.arguments;
		@Pc(10) int local10 = (Integer) listeners[0];
		@Pc(14) ClientScript clientScript = ClientScriptList.decodeClientScript(local10);
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
						j = arg1.mouseX;
					}
					if (j == -2147483646) { // 1
						j = arg1.mouseY;
					}
					if (j == -2147483645) { // 2
						j = arg1.source == null ? -1 : arg1.source.id;
					}
					if (j == -2147483644) { // 3
						j = arg1.op;
					}
					if (j == -2147483643) {
						j = arg1.source == null ? -1 : arg1.source.createdComponentId;
					}
					if (j == -2147483642) {
						j = arg1.target == null ? -1 : arg1.target.id;
					}
					if (j == -2147483641) {
						j = arg1.target == null ? -1 : arg1.target.createdComponentId;
					}
					if (j == -2147483640) {
						j = arg1.keyCode;
					}
					if (j == -2147483639) {
						j = arg1.keyChar;
					}
					Static215.localInts[localIntIndex++] = j;
				} else if (listeners[listenersIndex] instanceof JString) {
					string = (JString) listeners[listenersIndex];
					if (string.strEquals(Static15.aClass100_83)) {
						string = arg1.opBase;
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
						@Pc(423) GoSubFrame goSubFrame = Static67.GO_SUB_FRAMES[--Static138.invokedScriptIndex];
						clientScript = goSubFrame.script;
						Static215.localInts = goSubFrame.localInts;
						scriptOpcodes = clientScript.opcodes;
						scriptIndex = goSubFrame.pc;
						Static180.localStrings = goSubFrame.stringLocals;
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
						@Pc(642) ClientScript invokeScript = ClientScriptList.decodeClientScript(j);
						@Pc(646) int[] local646 = new int[invokeScript.localIntCount];
						@Pc(650) JString[] local650 = new JString[invokeScript.localStringCount];
						for (local652 = 0; local652 < invokeScript.intArgs; local652++) {
							local646[local652] = Static254.scriptIntValues[local652 + intValueIndex - invokeScript.intArgs];
						}
						for (local652 = 0; local652 < invokeScript.stringArgs; local652++) {
							local650[local652] = Static3.scriptStringValues[local652 + local26 - invokeScript.stringArgs];
						}
						intValueIndex -= invokeScript.intArgs;
						local26 -= invokeScript.stringArgs;
						@Pc(705) GoSubFrame goSubFrame = new GoSubFrame();
						goSubFrame.stringLocals = Static180.localStrings;
						goSubFrame.localInts = Static215.localInts;
						goSubFrame.pc = scriptIndex;
						goSubFrame.script = clientScript;
						if (Static138.invokedScriptIndex >= Static67.GO_SUB_FRAMES.length) {
							throw new RuntimeException();
						}
						clientScript = invokeScript;
						scriptIndex = -1;
						Static67.GO_SUB_FRAMES[Static138.invokedScriptIndex++] = goSubFrame;
						Static215.localInts = local646;
						local33 = invokeScript.intOperands;
						scriptOpcodes = invokeScript.opcodes;
						Static180.localStrings = local650;
						continue;
					}
					if (scriptOpcode == 42) {
						// push_varc_int
						Static254.scriptIntValues[intValueIndex++] = VarcDomain.varcs[local33[scriptIndex]];
						continue;
					}
					if (scriptOpcode == 43) {
						// pop_varc_int
						j = local33[scriptIndex];
						intValueIndex--;
						VarcDomain.varcs[j] = Static254.scriptIntValues[intValueIndex];
						DelayedStateChange.method24(j);
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
						DelayedStateChange.method1840(j);
						continue;
					}
					if (scriptOpcode == 51) {
						@Pc(992) HashTable local992 = clientScript.switchTables[local33[scriptIndex]];
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
							component = InterfaceList.getComponent(interfaceData);
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
							local1137.if3 = true;
							local1137.createdComponentId = childCount;
							local1137.overlayer = local1137.id = component.id;
							local1137.type = interfaceType;
							component.createdComponents[childCount] = local1137;
							if (local1020) {
								Static274.aClass13_24 = local1137;
							} else {
								Static227.aClass13_25 = local1137;
							}
							InterfaceList.redraw(component);
							continue;
						}
						throw new RuntimeException();
					}
					@Pc(1204) Component local1204;
					if (scriptOpcode == 101) {
						// cc_delete
						local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
						if (local1182.createdComponentId == -1) {
							if (!local1020) {
								throw new RuntimeException("Tried to cc_delete static active-component!");
							}
							throw new RuntimeException("Tried to .cc_delete static .active-component!");
						}
						local1204 = InterfaceList.getComponent(local1182.id);
						local1204.createdComponents[local1182.createdComponentId] = null;
						InterfaceList.redraw(local1204);
						continue;
					}
					if (scriptOpcode == 102) {
						// cc_deleteall
						intValueIndex--;
						local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
						local1182.createdComponents = null;
						InterfaceList.redraw(local1182);
						continue;
					}
					if (scriptOpcode == 200) {
						intValueIndex -= 2;
						interfaceData = Static254.scriptIntValues[intValueIndex];
						interfaceType = Static254.scriptIntValues[intValueIndex + 1];
						local1256 = InterfaceList.method1418(interfaceData, interfaceType);
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
						local1204 = InterfaceList.getComponent(interfaceData);
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
									PlayerList.self.appearance.method1953(childCount, interfaceType);
									continue label4266;
								}
							}
							childCount = 0;
							while (true) {
								if (childCount >= Static153.anIntArray351.length) {
									continue label4266;
								}
								if (interfaceData == Static153.anIntArray351[childCount]) {
									PlayerList.self.appearance.method1953(childCount, interfaceType);
									continue label4266;
								}
								childCount++;
							}
						}
						if (scriptOpcode == 404) {
							intValueIndex -= 2;
							interfaceData = Static254.scriptIntValues[intValueIndex];
							interfaceType = Static254.scriptIntValues[intValueIndex + 1];
							PlayerList.self.appearance.method1951(interfaceData, interfaceType);
							continue;
						}
						if (scriptOpcode == 410) {
							intValueIndex--;
							local12388 = Static254.scriptIntValues[intValueIndex] != 0;
							PlayerList.self.appearance.method1948(local12388);
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
									local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
								}
								if (scriptOpcode == 1100) {
									// setscrollpos
									intValueIndex -= 2;
									local1182.scrollX = Static254.scriptIntValues[intValueIndex];
									if (local1182.scrollX > local1182.scrollMaxH - local1182.width) {
										local1182.scrollX = local1182.scrollMaxH - local1182.width;
									}
									if (local1182.scrollX < 0) {
										local1182.scrollX = 0;
									}
									local1182.scrollY = Static254.scriptIntValues[intValueIndex + 1];
									if (local1182.scrollY > local1182.scrollMaxV - local1182.height) {
										local1182.scrollY = local1182.scrollMaxV - local1182.height;
									}
									if (local1182.scrollY < 0) {
										local1182.scrollY = 0;
									}
									InterfaceList.redraw(local1182);
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method2353(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1101) {
									// setcolor
									intValueIndex--;
									local1182.color = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method4224(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1102) {
									// setfill
									intValueIndex--;
									local1182.filled = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1103) {
									// settrans
									intValueIndex--;
									local1182.alpha = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1104) {
									// setlinewid
									intValueIndex--;
									local1182.lineWidth = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1105) {
									// setgraphic
									intValueIndex--;
									local1182.spriteId = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1106) {
									intValueIndex--;
									local1182.angle2d = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1107) {
									// settiling
									intValueIndex--;
									local1182.spriteTiling = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1108) {
									// setmodel
									local1182.modelType = 1;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method4600(local1182.id);
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
									InterfaceList.redraw(local1182);
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAngleClient(local1182.id);
										DelayedStateChange.setComponentModelOffsetClient(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1110) {
									// setmodelanim
									intValueIndex--;
									interfaceType = Static254.scriptIntValues[intValueIndex];
									if (local1182.modelSeqId != interfaceType) {
										local1182.modelSeqId = interfaceType;
										local1182.anInt510 = 0;
										local1182.anInt500 = 0;
										local1182.anInt496 = 1;
										InterfaceList.redraw(local1182);
									}
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method3345(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1111) {
									// setmodelorthog
									intValueIndex--;
									local1182.modelOrtho = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1112) {
									// settext
									local26--;
									chatTypedLowercase = Static3.scriptStringValues[local26];
									if (!chatTypedLowercase.strEquals(local1182.text)) {
										local1182.text = chatTypedLowercase;
										InterfaceList.redraw(local1182);
									}
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method3096(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1113) {
									// settextfont
									intValueIndex--;
									local1182.fontId = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1114) {
									// settextalign
									intValueIndex -= 3;
									local1182.halign = Static254.scriptIntValues[intValueIndex];
									local1182.valign = Static254.scriptIntValues[intValueIndex + 1];
									local1182.vpadding = Static254.scriptIntValues[intValueIndex + 2];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1115) {
									// settextshadow
									intValueIndex--;
									local1182.shadowed = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1116) {
									intValueIndex--;
									local1182.outlineThickness = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1117) {
									intValueIndex--;
									local1182.shadowColor = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1118) {
									intValueIndex--;
									local1182.vFlip = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1119) {
									intValueIndex--;
									local1182.hFlip = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1120) {
									intValueIndex -= 2;
									local1182.scrollMaxH = Static254.scriptIntValues[intValueIndex];
									local1182.scrollMaxV = Static254.scriptIntValues[intValueIndex + 1];
									InterfaceList.redraw(local1182);
									if (local1182.type == 0) {
										InterfaceList.method531(local1182, false);
									}
									continue;
								}
								if (scriptOpcode == 1121) {
									intValueIndex -= 2;
									local1182.aShort11 = (short) Static254.scriptIntValues[intValueIndex];
									local1182.aShort10 = (short) Static254.scriptIntValues[intValueIndex + 1];
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1122) {
									intValueIndex--;
									local1182.hasAlpha = Static254.scriptIntValues[intValueIndex] == 1;
									InterfaceList.redraw(local1182);
									continue;
								}
								if (scriptOpcode == 1123) {
									intValueIndex--;
									local1182.modelZoom = Static254.scriptIntValues[intValueIndex];
									InterfaceList.redraw(local1182);
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAngleClient(local1182.id);
									}
									continue;
								}
							} else if (scriptOpcode >= 1200 && scriptOpcode < 1300 || !(scriptOpcode < 2200 || scriptOpcode >= 2300)) {
								if (scriptOpcode < 2000) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
								} else {
									intValueIndex--;
									local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
									scriptOpcode -= 1000;
								}
								InterfaceList.redraw(local1182);
								if (scriptOpcode == 1200 || scriptOpcode == 1205) {
									intValueIndex -= 2;
									childCount = Static254.scriptIntValues[intValueIndex + 1];
									interfaceType = Static254.scriptIntValues[intValueIndex];
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.setComponentObjClient(local1182.id);
										DelayedStateChange.setComponentModelAngleClient(local1182.id);
										DelayedStateChange.setComponentModelOffsetClient(local1182.id);
									}
									if (interfaceType == -1) {
										local1182.modelId = -1;
										local1182.modelType = 1;
										local1182.objId = -1;
									} else {
										local1182.objId = interfaceType;
										local1182.objCount = childCount;
										@Pc(13416) ObjType local13416 = ObjTypeList.get(interfaceType);
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
											local1182.objDrawText = false;
										} else {
											local1182.objDrawText = true;
										}
									}
									continue;
								}
								if (scriptOpcode == 1201) {
									// setnpchead
									local1182.modelType = 2;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method4600(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1202) {
									// setplayerhead_self
									local1182.modelType = 3;
									local1182.modelId = PlayerList.self.appearance.getHeadModelId();
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method4600(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1203) {
									// setnpcmodel
									local1182.modelType = 6;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method4600(local1182.id);
									}
									continue;
								}
								if (scriptOpcode == 1204) {
									local1182.modelType = 5;
									intValueIndex--;
									local1182.modelId = Static254.scriptIntValues[intValueIndex];
									if (local1182.createdComponentId == -1) {
										DelayedStateChange.method4600(local1182.id);
									}
									continue;
								}
							} else if (scriptOpcode >= 1300 && scriptOpcode < 1400 || scriptOpcode >= 2300 && scriptOpcode < 2400) {
								if (scriptOpcode >= 2000) {
									// if_
									intValueIndex--;
									local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
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
									local1182.aClass13_5 = InterfaceList.method1418(interfaceType, childCount);
									continue;
								}
								if (scriptOpcode == 1302) {
									intValueIndex--;
									local1182.dragRenderBehavior = Static254.scriptIntValues[intValueIndex] == 1;
									continue;
								}
								if (scriptOpcode == 1303) {
									intValueIndex--;
									local1182.dragDeadzone = Static254.scriptIntValues[intValueIndex];
									continue;
								}
								if (scriptOpcode == 1304) {
									intValueIndex--;
									local1182.dragDeadtime = Static254.scriptIntValues[intValueIndex];
									continue;
								}
								if (scriptOpcode == 1305) {
									local26--;
									local1182.optionBase = Static3.scriptStringValues[local26];
									continue;
								}
								if (scriptOpcode == 1306) {
									local26--;
									local1182.optionCircumfix = Static3.scriptStringValues[local26];
									continue;
								}
								if (scriptOpcode == 1307) {
									local1182.ops = null;
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
										local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
									}
									@Pc(12937) int[] local12937 = null;
									local26--;
									chatTypedLowercase = Static3.scriptStringValues[local26];
									if (chatTypedLowercase.length() > 0 && chatTypedLowercase.charAt(chatTypedLowercase.length() - 1) == 89) {
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
										if (chatTypedLowercase.charAt(start - 1) == 115) {
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
										local1182.onClickRepeat = local13000;
									} else if (scriptOpcode == 1401) {
										local1182.onHold = local13000;
									} else if (scriptOpcode == 1402) {
										local1182.onRelease = local13000;
									} else if (scriptOpcode == 1403) {
										local1182.onMouseOver = local13000;
									} else if (scriptOpcode == 1404) {
										local1182.onMouseLeave = local13000;
									} else if (scriptOpcode == 1405) {
										local1182.onDragStart = local13000;
									} else if (scriptOpcode == 1406) {
										local1182.onUseWith = local13000;
									} else if (scriptOpcode == 1407) {
										local1182.varpTriggers = local12937;
										local1182.onVarpTransmit = local13000;
									} else if (scriptOpcode == 1408) {
										local1182.onTimer = local13000;
									} else if (scriptOpcode == 1409) {
										local1182.onOptionClick = local13000;
									} else if (scriptOpcode == 1410) {
										local1182.onDragRelease = local13000;
									} else if (scriptOpcode == 1411) {
										local1182.onDrag = local13000;
									} else if (scriptOpcode == 1412) {
										local1182.onMouseRepeat = local13000;
									} else if (scriptOpcode == 1414) {
										local1182.inventoryTriggers = local12937;
										local1182.onInvTransmit = local13000;
									} else if (scriptOpcode == 1415) {
										local1182.statTriggers = local12937;
										local1182.onStatTransmit = local13000;
									} else if (scriptOpcode == 1416) {
										local1182.onUse = local13000;
									} else if (scriptOpcode == 1417) {
										local1182.onScroll = local13000;
									} else if (scriptOpcode == 1418) {
										local1182.onMsg = local13000;
									} else if (scriptOpcode == 1419) {
										local1182.onKey = local13000;
									} else if (scriptOpcode == 1420) {
										local1182.onFriendTransmit = local13000;
									} else if (scriptOpcode == 1421) {
										local1182.onClanTransmit = local13000;
									} else if (scriptOpcode == 1422) {
										local1182.onMiscTransmit = local13000;
									} else if (scriptOpcode == 1423) {
										local1182.onDialogAbort = local13000;
									} else if (scriptOpcode == 1424) {
										local1182.onWidgetsOpenClose = local13000;
									} else if (scriptOpcode == 1425) {
										local1182.onStockTransmit = local13000;
									} else if (scriptOpcode == 1426) {
										local1182.onMinimapUnlock = local13000;
									} else if (scriptOpcode == 1427) {
										local1182.onResize = local13000;
									} else if (scriptOpcode == 1428) {
										local1182.onVarcTransmit = local13000;
										local1182.varcTriggers = local12937;
									} else if (scriptOpcode == 1429) {
										local1182.varcstrTriggers = local12937;
										local1182.onVarcstrTransmit = local13000;
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
										Static254.scriptIntValues[intValueIndex++] = local1182.width;
										continue;
									}
									if (scriptOpcode == 1503) {
										// cc_getheight
										Static254.scriptIntValues[intValueIndex++] = local1182.height;
										continue;
									}
									if (scriptOpcode == 1504) {
										// cc_gethide
										Static254.scriptIntValues[intValueIndex++] = local1182.hidden ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 1505) {
										// set_getlayer
										Static254.scriptIntValues[intValueIndex++] = local1182.overlayer;
										continue;
									}
								} else if (scriptOpcode < 1700) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									if (scriptOpcode == 1600) {
										// cc_getscrollx
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollX;
										continue;
									}
									if (scriptOpcode == 1601) {
										// cc_getscrolly
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollY;
										continue;
									}
									if (scriptOpcode == 1602) {
										Static3.scriptStringValues[local26++] = local1182.text;
										continue;
									}
									if (scriptOpcode == 1603) {
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollMaxH;
										continue;
									}
									if (scriptOpcode == 1604) {
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollMaxV;
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
										Static254.scriptIntValues[intValueIndex++] = local1182.alpha;
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
										Static254.scriptIntValues[intValueIndex++] = local1182.spriteId;
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
										Static254.scriptIntValues[intValueIndex++] = local1182.createdComponentId;
										continue;
									}
								} else if (scriptOpcode < 1900) {
									local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
									if (scriptOpcode == 1800) {
										Static254.scriptIntValues[intValueIndex++] = InterfaceList.getServerActiveProperties(local1182).method512();
										continue;
									}
									if (scriptOpcode == 1801) {
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										interfaceType--;
										if (local1182.ops != null && interfaceType < local1182.ops.length && local1182.ops[interfaceType] != null) {
											Static3.scriptStringValues[local26++] = local1182.ops[interfaceType];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 1802) {
										if (local1182.optionBase == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = local1182.optionBase;
										}
										continue;
									}
								} else if (scriptOpcode < 2600) {
									intValueIndex--;
									local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
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
										Static254.scriptIntValues[intValueIndex++] = local1182.width;
										continue;
									}
									if (scriptOpcode == 2503) {
										// if_getheight
										Static254.scriptIntValues[intValueIndex++] = local1182.height;
										continue;
									}
									if (scriptOpcode == 2504) {
										// if_gethide
										Static254.scriptIntValues[intValueIndex++] = local1182.hidden ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 2505) {
										// if_getlayer
										Static254.scriptIntValues[intValueIndex++] = local1182.overlayer;
										continue;
									}
								} else if (scriptOpcode < 2700) {
									intValueIndex--;
									local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
									if (scriptOpcode == 2600) {
										// if_getscrollx
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollX;
										continue;
									}
									if (scriptOpcode == 2601) {
										// if_getscrolly
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollY;
										continue;
									}
									if (scriptOpcode == 2602) {
										Static3.scriptStringValues[local26++] = local1182.text;
										continue;
									}
									if (scriptOpcode == 2603) {
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollMaxH;
										continue;
									}
									if (scriptOpcode == 2604) {
										Static254.scriptIntValues[intValueIndex++] = local1182.scrollMaxV;
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
										Static254.scriptIntValues[intValueIndex++] = local1182.alpha;
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
										Static254.scriptIntValues[intValueIndex++] = local1182.spriteId;
										continue;
									}
								} else if (scriptOpcode < 2800) {
									if (scriptOpcode == 2700) {
										intValueIndex--;
										local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
										Static254.scriptIntValues[intValueIndex++] = local1182.objId;
										continue;
									}
									if (scriptOpcode == 2701) {
										intValueIndex--;
										local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
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
										@Pc(12566) ComponentPointer local12566 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) interfaceData);
										if (local12566 == null) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = 1;
										}
										continue;
									}
									if (scriptOpcode == 2703) {
										intValueIndex--;
										local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
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
										@Pc(12663) ComponentPointer local12663 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) interfaceData);
										if (local12663 != null && local12663.interfaceId == interfaceType) {
											Static254.scriptIntValues[intValueIndex++] = 1;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
								} else if (scriptOpcode < 2900) {
									intValueIndex--;
									local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
									if (scriptOpcode == 2800) {
										Static254.scriptIntValues[intValueIndex++] = InterfaceList.getServerActiveProperties(local1182).method512();
										continue;
									}
									if (scriptOpcode == 2801) {
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										interfaceType--;
										if (local1182.ops != null && local1182.ops.length > interfaceType && local1182.ops[interfaceType] != null) {
											Static3.scriptStringValues[local26++] = local1182.ops[interfaceType];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 2802) {
										if (local1182.optionBase == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = local1182.optionBase;
										}
										continue;
									}
								} else if (scriptOpcode < 3200) {
									if (scriptOpcode == 3100) {
										// mes
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Chat.addMessage(Static72.aClass100_447, 0, chatTyped);
										continue;
									}
									if (scriptOpcode == 3101) {
										// anim
										intValueIndex -= 2;
										Static186.method3415(Static254.scriptIntValues[intValueIndex + 1], Static254.scriptIntValues[intValueIndex], PlayerList.self);
										continue;
									}
									if (scriptOpcode == 3103) {
										ClientProt.closeWidget();
										continue;
									}
									if (scriptOpcode == 3104) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										interfaceType = 0;
										if (chatTyped.method3123()) {
											interfaceType = chatTyped.parseInt();
										}
										Protocol.outboundBuffer.pIsaac1(23);
										Protocol.outboundBuffer.p4(interfaceType);
										continue;
									}
									if (scriptOpcode == 3105) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Protocol.outboundBuffer.pIsaac1(244);
										Protocol.outboundBuffer.p8(chatTyped.encode37());
										continue;
									}
									if (scriptOpcode == 3106) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										Protocol.outboundBuffer.pIsaac1(65);
										Protocol.outboundBuffer.p1(chatTyped.length() + 1);
										Protocol.outboundBuffer.pjstr(chatTyped);
										continue;
									}
									if (scriptOpcode == 3107) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										local26--;
										chatTypedLowercase = Static3.scriptStringValues[local26];
										ClientProt.clickPlayerOption(interfaceData, chatTypedLowercase);
										continue;
									}
									if (scriptOpcode == 3108) {
										intValueIndex -= 3;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										childCount = Static254.scriptIntValues[intValueIndex + 2];
										component = InterfaceList.getComponent(childCount);
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
										Protocol.outboundBuffer.pIsaac1(111);
										Protocol.outboundBuffer.p2(interfaceData);
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
										Static254.scriptIntValues[intValueIndex++] = client.loop;
										continue;
									}
									if (scriptOpcode == 3301) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Inv.getItemType(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3302) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Inv.getItemCount(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3303) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Inv.getSlotTotal(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3304) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = InvTypeList.get(interfaceData).size;
										continue;
									}
									if (scriptOpcode == 3305) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = PlayerSkillXpTable.skillLevel[interfaceData];
										continue;
									}
									if (scriptOpcode == 3306) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = PlayerSkillXpTable.skillBaseLevel[interfaceData];
										continue;
									}
									if (scriptOpcode == 3307) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = PlayerSkillXpTable.skillExperience[interfaceData];
										continue;
									}
									if (scriptOpcode == 3308) {
										interfaceData = Player.plane;
										interfaceType = Camera.originX + (PlayerList.self.xFine >> 7);
										childCount = (PlayerList.self.zFine >> 7) + Camera.originZ;
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
										Static254.scriptIntValues[intValueIndex++] = Inv.getItemType(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3314) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex] + 32768;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Inv.getItemCount(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3315) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex] + 32768;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Inv.getSlotTotal(interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3316) {
										if (LoginManager.staffModLevel < 2) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = LoginManager.staffModLevel;
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
										Static254.scriptIntValues[intValueIndex++] = Player.energy;
										continue;
									}
									if (scriptOpcode == 3322) {
										Static254.scriptIntValues[intValueIndex++] = Player.weightCarried;
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
										Static254.scriptIntValues[intValueIndex++] = LoginManager.playerMember ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3326) {
										Static254.scriptIntValues[intValueIndex++] = PlayerList.self.combatLevel;
										continue;
									}
									if (scriptOpcode == 3327) {
										Static254.scriptIntValues[intValueIndex++] = PlayerList.self.appearance.aBoolean141 ? 1 : 0;
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
										Static254.scriptIntValues[intValueIndex++] = Inv.getFreeSpace(interfaceData);
										continue;
									}
									if (scriptOpcode == 3331) {
										intValueIndex -= 2;
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = Static178.getTotalParam(false, interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3332) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										Static254.scriptIntValues[intValueIndex++] = Static178.getTotalParam(true, interfaceData, interfaceType);
										continue;
									}
									if (scriptOpcode == 3333) {
										Static254.scriptIntValues[intValueIndex++] = LoginManager.anInt39;
										continue;
									}
									if (scriptOpcode == 3335) {
										Static254.scriptIntValues[intValueIndex++] = client.language;
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
										Static254.scriptIntValues[intValueIndex++] = client.affiliate;
										continue;
									}
								} else if (scriptOpcode < 3500) {
									@Pc(3422) EnumType local3422;
									if (scriptOpcode == 3400) {
										intValueIndex -= 2;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = Static254.scriptIntValues[intValueIndex + 1];
										local3422 = EnumTypeList.get(interfaceData);
										if (local3422.valueType == 115) {
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
										@Pc(3469) EnumType local3469 = EnumTypeList.get(childCount);
										if (local3469.keyType == interfaceData && local3469.valueType == interfaceType) {
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
										@Pc(3549) EnumType local3549 = EnumTypeList.get(interfaceType);
										if (local3549.valueType != interfaceData) {
											throw new RuntimeException("C3409-1");
										}
										Static254.scriptIntValues[intValueIndex++] = local3549.containsValue(childCount) ? 1 : 0;
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
										local3422 = EnumTypeList.get(interfaceData);
										if (local3422.valueType != 115) {
											throw new RuntimeException("C3410-1");
										}
										Static254.scriptIntValues[intValueIndex++] = local3422.method3086(chatTypedLowercase) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3411) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										@Pc(3645) EnumType local3645 = EnumTypeList.get(interfaceData);
										Static254.scriptIntValues[intValueIndex++] = local3645.table.length();
										continue;
									}
								} else if (scriptOpcode < 3700) {
									if (scriptOpcode == 3600) {
										if (FriendList.state == 0) {
											Static254.scriptIntValues[intValueIndex++] = -2;
										} else if (FriendList.state == 1) {
											Static254.scriptIntValues[intValueIndex++] = -1;
										} else {
											Static254.scriptIntValues[intValueIndex++] = FriendList.friendCount;
										}
										continue;
									}
									if (scriptOpcode == 3601) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (FriendList.state == 2 && interfaceData < FriendList.friendCount) {
											Static3.scriptStringValues[local26++] = FriendList.friendUsernames[interfaceData];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3602) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (FriendList.state == 2 && FriendList.friendCount > interfaceData) {
											Static254.scriptIntValues[intValueIndex++] = FriendList.friendWorlds[interfaceData];
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3603) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (FriendList.state == 2 && FriendList.friendCount > interfaceData) {
											Static254.scriptIntValues[intValueIndex++] = FriendList.ranks[interfaceData];
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
										FriendList.setRank(chatTyped, interfaceType);
										continue;
									}
									if (scriptOpcode == 3605) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										FriendList.addFriend(chatTyped.encode37());
										continue;
									}
									if (scriptOpcode == 3606) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										FriendList.removeFriend(chatTyped.encode37());
										continue;
									}
									if (scriptOpcode == 3607) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										IgnoreList.addIgnore(chatTyped.encode37());
										continue;
									}
									if (scriptOpcode == 3608) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										IgnoreList.remove(chatTyped.encode37());
										continue;
									}
									if (scriptOpcode == 3609) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										if (chatTyped.startsWith(Static72.aClass100_446) || chatTyped.startsWith(Static101.aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										Static254.scriptIntValues[intValueIndex++] = FriendList.contains(chatTyped) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3610) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (FriendList.state == 2 && FriendList.friendCount > interfaceData) {
											Static3.scriptStringValues[local26++] = FriendList.worldNames[interfaceData];
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3611) {
										if (ClanChat.name == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = ClanChat.name.toTitleCase();
										}
										continue;
									}
									if (scriptOpcode == 3612) {
										if (ClanChat.name == null) {
											Static254.scriptIntValues[intValueIndex++] = 0;
										} else {
											Static254.scriptIntValues[intValueIndex++] = ClanChat.size;
										}
										continue;
									}
									if (scriptOpcode == 3613) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (ClanChat.name != null && ClanChat.size > interfaceData) {
											Static3.scriptStringValues[local26++] = ClanChat.members[interfaceData].username.toTitleCase();
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3614) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (ClanChat.name != null && interfaceData < ClanChat.size) {
											Static254.scriptIntValues[intValueIndex++] = ClanChat.members[interfaceData].world;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3615) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (ClanChat.name != null && ClanChat.size > interfaceData) {
											Static254.scriptIntValues[intValueIndex++] = ClanChat.members[interfaceData].rank;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3616) {
										Static254.scriptIntValues[intValueIndex++] = ClanChat.minKick;
										continue;
									}
									if (scriptOpcode == 3617) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										ClanChat.kick(chatTyped);
										continue;
									}
									if (scriptOpcode == 3618) {
										Static254.scriptIntValues[intValueIndex++] = ClanChat.rank;
										continue;
									}
									if (scriptOpcode == 3619) {
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										ClanChat.join(chatTyped.encode37());
										continue;
									}
									if (scriptOpcode == 3620) {
										ClanChat.leave();
										continue;
									}
									if (scriptOpcode == 3621) {
										if (FriendList.state == 0) {
											Static254.scriptIntValues[intValueIndex++] = -1;
										} else {
											Static254.scriptIntValues[intValueIndex++] = IgnoreList.ignoreCount;
										}
										continue;
									}
									if (scriptOpcode == 3622) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (FriendList.state != 0 && IgnoreList.ignoreCount > interfaceData) {
											Static3.scriptStringValues[local26++] = Base37.decode37(IgnoreList.encodedIgnores[interfaceData]).toTitleCase();
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
										Static254.scriptIntValues[intValueIndex++] = IgnoreList.contains(chatTyped) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3624) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (ClanChat.members != null && ClanChat.size > interfaceData && ClanChat.members[interfaceData].username.equalsIgnoreCase(PlayerList.self.username)) {
											Static254.scriptIntValues[intValueIndex++] = 1;
											continue;
										}
										Static254.scriptIntValues[intValueIndex++] = 0;
										continue;
									}
									if (scriptOpcode == 3625) {
										if (ClanChat.owner == null) {
											Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										} else {
											Static3.scriptStringValues[local26++] = ClanChat.owner.toTitleCase();
										}
										continue;
									}
									if (scriptOpcode == 3626) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (ClanChat.name != null && ClanChat.size > interfaceData) {
											Static3.scriptStringValues[local26++] = ClanChat.members[interfaceData].worldName;
											continue;
										}
										Static3.scriptStringValues[local26++] = Static72.aClass100_447;
										continue;
									}
									if (scriptOpcode == 3627) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										if (FriendList.state == 2 && interfaceData >= 0 && interfaceData < FriendList.friendCount) {
											Static254.scriptIntValues[intValueIndex++] = FriendList.friendGame[interfaceData] ? 1 : 0;
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
										Static254.scriptIntValues[intValueIndex++] = FriendList.indexOf(chatTyped);
										continue;
									}
									if (scriptOpcode == 3629) {
										Static254.scriptIntValues[intValueIndex++] = client.country;
										continue;
									}
								} else if (scriptOpcode < 4000) {
									if (scriptOpcode == 3903) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = StockMarketManager.offers[interfaceData].method3905();
										continue;
									}
									if (scriptOpcode == 3904) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = StockMarketManager.offers[interfaceData].anInt5094;
										continue;
									}
									if (scriptOpcode == 3905) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = StockMarketManager.offers[interfaceData].anInt5099;
										continue;
									}
									if (scriptOpcode == 3906) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = StockMarketManager.offers[interfaceData].anInt5090;
										continue;
									}
									if (scriptOpcode == 3907) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = StockMarketManager.offers[interfaceData].anInt5089;
										continue;
									}
									if (scriptOpcode == 3908) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = StockMarketManager.offers[interfaceData].anInt5092;
										continue;
									}
									if (scriptOpcode == 3910) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = StockMarketManager.offers[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 0 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3911) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = StockMarketManager.offers[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 2 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3912) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = StockMarketManager.offers[interfaceData].method3904();
										Static254.scriptIntValues[intValueIndex++] = interfaceType == 5 ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 3913) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										interfaceType = StockMarketManager.offers[interfaceData].method3904();
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
											Static3.scriptStringValues[local26++] = ObjTypeList.get(interfaceData).name;
											continue;
										}
										@Pc(11269) ObjType local11269;
										if (scriptOpcode == 4201) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local11269 = ObjTypeList.get(interfaceData);
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
											local11269 = ObjTypeList.get(interfaceData);
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
											Static254.scriptIntValues[intValueIndex++] = ObjTypeList.get(interfaceData).cost;
											continue;
										}
										if (scriptOpcode == 4204) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											Static254.scriptIntValues[intValueIndex++] = ObjTypeList.get(interfaceData).stackable == 1 ? 1 : 0;
											continue;
										}
										@Pc(11417) ObjType local11417;
										if (scriptOpcode == 4205) {
											intValueIndex--;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											local11417 = ObjTypeList.get(interfaceData);
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
											local11417 = ObjTypeList.get(interfaceData);
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
											Static254.scriptIntValues[intValueIndex++] = ObjTypeList.get(interfaceData).members ? 1 : 0;
											continue;
										}
										if (scriptOpcode == 4208) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local5294 = Static110.method2277(interfaceType);
											if (local5294.method2078()) {
												Static3.scriptStringValues[local26++] = ObjTypeList.get(interfaceData).getParam(local5294.aClass100_544, interfaceType);
											} else {
												Static254.scriptIntValues[intValueIndex++] = ObjTypeList.get(interfaceData).getParam(local5294.anInt2667, interfaceType);
											}
											continue;
										}
										if (scriptOpcode == 4210) {
											local26--;
											chatTyped = Static3.scriptStringValues[local26];
											intValueIndex--;
											interfaceType = Static254.scriptIntValues[intValueIndex];
											Find.search(interfaceType == 1, chatTyped);
											Static254.scriptIntValues[intValueIndex++] = Find.index;
											continue;
										}
										if (scriptOpcode == 4211) {
											if (Find.results != null && Find.size < Find.index) {
												Static254.scriptIntValues[intValueIndex++] = Find.results[Find.size++] & 0xFFFF;
												continue;
											}
											Static254.scriptIntValues[intValueIndex++] = -1;
											continue;
										}
										if (scriptOpcode == 4212) {
											Find.size = 0;
											continue;
										}
									} else if (scriptOpcode < 4400) {
										if (scriptOpcode == 4300) {
											intValueIndex -= 2;
											interfaceData = Static254.scriptIntValues[intValueIndex];
											interfaceType = Static254.scriptIntValues[intValueIndex + 1];
											local5294 = Static110.method2277(interfaceType);
											if (local5294.method2078()) {
												Static3.scriptStringValues[local26++] = NpcType.getDefinition(interfaceData).getParam(interfaceType, local5294.aClass100_544);
											} else {
												Static254.scriptIntValues[intValueIndex++] = NpcType.getDefinition(interfaceData).getParam(interfaceType, local5294.anInt2667);
											}
											continue;
										}
									} else if (scriptOpcode >= 4500) {
										if (scriptOpcode >= 4600) {
											if (scriptOpcode < 5100) {
												if (scriptOpcode == 5000) {
													Static254.scriptIntValues[intValueIndex++] = Chat.publicFilter;
													continue;
												}
												if (scriptOpcode == 5001) {
													intValueIndex -= 3;
													Chat.publicFilter = Static254.scriptIntValues[intValueIndex];
													Chat.privateFilter = Static254.scriptIntValues[intValueIndex + 1];
													Chat.tradeFilter = Static254.scriptIntValues[intValueIndex + 2];
													Protocol.outboundBuffer.pIsaac1(157);
													Protocol.outboundBuffer.p1(Chat.publicFilter);
													Protocol.outboundBuffer.p1(Chat.privateFilter);
													Protocol.outboundBuffer.p1(Chat.tradeFilter);
													continue;
												}
												if (scriptOpcode == 5002) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex];
													childCount = Static254.scriptIntValues[intValueIndex + 1];
													Protocol.outboundBuffer.pIsaac1(99);
													Protocol.outboundBuffer.p8(chatTyped.encode37());
													Protocol.outboundBuffer.p1(interfaceType - 1);
													Protocol.outboundBuffer.p1(childCount);
													continue;
												}
												if (scriptOpcode == 5003) {
													chatTypedLowercase = null;
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													if (interfaceData < 100) {
														chatTypedLowercase = Chat.messages[interfaceData];
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
													if (interfaceData < 100 && Chat.messages[interfaceData] != null) {
														interfaceType = Chat.types[interfaceData];
													}
													Static254.scriptIntValues[intValueIndex++] = interfaceType;
													continue;
												}
												if (scriptOpcode == 5005) {
													Static254.scriptIntValues[intValueIndex++] = Chat.privateFilter;
													continue;
												}
												if (scriptOpcode == 5008) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													if (!chatTyped.startsWith(Static12.aClass100_74)) {
														if (LoginManager.staffModLevel == 0 && (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129)) {
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
														} else if (client.language != 0) {
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
														} else if (client.language != 0) {
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
														Protocol.outboundBuffer.pIsaac1(237);
														Protocol.outboundBuffer.p1(0);
														start = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p1(color);
														Protocol.outboundBuffer.p1(effect);
														Static146.method2748(Protocol.outboundBuffer, chatTyped);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - start);
														continue;
													}
													Cheat.execute(chatTyped);
													continue;
												}
												if (scriptOpcode == 5009) {
													local26 -= 2;
													chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
													chatTyped = Static3.scriptStringValues[local26];
													if (LoginManager.staffModLevel != 0 || (!Static124.aBoolean157 || Static207.parentalChatConsent) && !Static86.aBoolean129) {
														Protocol.outboundBuffer.pIsaac1(201);
														Protocol.outboundBuffer.p1(0);
														childCount = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p8(chatTyped.encode37());
														Static146.method2748(Protocol.outboundBuffer, chatTypedLowercase);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - childCount);
													}
													continue;
												}
												if (scriptOpcode == 5010) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													chatTypedLowercase = null;
													if (interfaceData < 100) {
														chatTypedLowercase = Chat.names[interfaceData];
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
														chatTypedLowercase = Chat.clans[interfaceData];
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
														interfaceType = Chat.phraseIds[interfaceData];
													}
													Static254.scriptIntValues[intValueIndex++] = interfaceType;
													continue;
												}
												if (scriptOpcode == 5015) {
													if (PlayerList.self == null || PlayerList.self.username == null) {
														chatTyped = LoginManager.username;
													} else {
														chatTyped = PlayerList.self.getUsername();
													}
													Static3.scriptStringValues[local26++] = chatTyped;
													continue;
												}
												if (scriptOpcode == 5016) {
													Static254.scriptIntValues[intValueIndex++] = Chat.tradeFilter;
													continue;
												}
												if (scriptOpcode == 5017) {
													Static254.scriptIntValues[intValueIndex++] = Chat.size;
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
													Static3.scriptStringValues[local26++] = QuickChatPhraseTypeList.list(interfaceData).getText();
													continue;
												}
												if (scriptOpcode == 5056) {
													intValueIndex--;
													interfaceData = Static254.scriptIntValues[intValueIndex];
													@Pc(6527) QuickChatPhraseType local6527 = QuickChatPhraseTypeList.list(interfaceData);
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
													Static254.scriptIntValues[intValueIndex++] = QuickChatPhraseTypeList.list(interfaceData).autoResponses[interfaceType];
													continue;
												}
												if (scriptOpcode == 5058) {
													Static122.aQuickChatPhrase_1 = new QuickChatPhrase();
													intValueIndex--;
													Static122.aQuickChatPhrase_1.anInt439 = Static254.scriptIntValues[intValueIndex];
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1 = QuickChatPhraseTypeList.list(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.anIntArray33 = new int[Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.method767()];
													continue;
												}
												if (scriptOpcode == 5059) {
													Protocol.outboundBuffer.pIsaac1(167);
													Protocol.outboundBuffer.p1(0);
													interfaceData = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(0);
													Protocol.outboundBuffer.p2(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.putDynamics(Protocol.outboundBuffer, Static122.aQuickChatPhrase_1.anIntArray33);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - interfaceData);
													continue;
												}
												if (scriptOpcode == 5060) {
													local26--;
													chatTyped = Static3.scriptStringValues[local26];
													Protocol.outboundBuffer.pIsaac1(178);
													Protocol.outboundBuffer.p1(0);
													interfaceType = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p8(chatTyped.encode37());
													Protocol.outboundBuffer.p2(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.putDynamics(Protocol.outboundBuffer, Static122.aQuickChatPhrase_1.anIntArray33);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - interfaceType);
													continue;
												}
												if (scriptOpcode == 5061) {
													Protocol.outboundBuffer.pIsaac1(167);
													Protocol.outboundBuffer.p1(0);
													interfaceData = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(1);
													Protocol.outboundBuffer.p2(Static122.aQuickChatPhrase_1.anInt439);
													Static122.aQuickChatPhrase_1.aQuickChatPhraseType_1.putDynamics(Protocol.outboundBuffer, Static122.aQuickChatPhrase_1.anIntArray33);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - interfaceData);
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
													Static254.scriptIntValues[intValueIndex++] = QuickChatPhraseTypeList.list(interfaceData).method767();
													continue;
												}
												if (scriptOpcode == 5067) {
													intValueIndex -= 2;
													interfaceType = Static254.scriptIntValues[intValueIndex + 1];
													interfaceData = Static254.scriptIntValues[intValueIndex];
													childCount = QuickChatPhraseTypeList.list(interfaceData).method765(interfaceType);
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
													@Pc(6996) QuickChatPhraseType local6996 = QuickChatPhraseTypeList.list(interfaceData);
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
													Find.findQuickChatPhrases(local1552, chatTyped);
													Static254.scriptIntValues[intValueIndex++] = Find.index;
													continue;
												}
												if (scriptOpcode == 5072) {
													if (Find.results != null && Find.size < Find.index) {
														Static254.scriptIntValues[intValueIndex++] = Find.results[Find.size++] & 0xFFFF;
														continue;
													}
													Static254.scriptIntValues[intValueIndex++] = -1;
													continue;
												}
												if (scriptOpcode == 5073) {
													Find.size = 0;
													continue;
												}
											} else if (scriptOpcode < 5200) {
												if (scriptOpcode == 5100) {
													if (Keyboard.pressedKeys[86]) {
														Static254.scriptIntValues[intValueIndex++] = 1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = 0;
													}
													continue;
												}
												if (scriptOpcode == 5101) {
													if (Keyboard.pressedKeys[82]) {
														Static254.scriptIntValues[intValueIndex++] = 1;
													} else {
														Static254.scriptIntValues[intValueIndex++] = 0;
													}
													continue;
												}
												if (scriptOpcode == 5102) {
													if (Keyboard.pressedKeys[81]) {
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
														Static254.scriptIntValues[intValueIndex++] = WorldMap.originX + WorldMap.anInt435;
														Static254.scriptIntValues[intValueIndex++] = WorldMap.originZ + WorldMap.length - WorldMap.anInt919 - 1;
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
														@Pc(7577) NodeQueue local7577 = Static183.method3333(interfaceData >> 14 & 0x3FFF, interfaceData & 0x3FFF);
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
														Static254.scriptIntValues[intValueIndex++] = WorldMap.loadPercentage == 100 ? 1 : 0;
														continue;
													}
												} else if (scriptOpcode < 5400) {
													if (scriptOpcode == 5300) {
														intValueIndex -= 2;
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														interfaceData = Static254.scriptIntValues[intValueIndex];
														DisplayMode.setWindowMode(false, 3, interfaceData, interfaceType);
														Static254.scriptIntValues[intValueIndex++] = GameShell.fullScreenFrame == null ? 0 : 1;
														continue;
													}
													if (scriptOpcode == 5301) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														continue;
													}
													if (scriptOpcode == 5302) {
														@Pc(7780) DisplayMode[] local7780 = DisplayMode.getModes();
														Static254.scriptIntValues[intValueIndex++] = local7780.length;
														continue;
													}
													if (scriptOpcode == 5303) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														@Pc(7800) DisplayMode[] local7800 = DisplayMode.getModes();
														Static254.scriptIntValues[intValueIndex++] = local7800[interfaceData].width;
														Static254.scriptIntValues[intValueIndex++] = local7800[interfaceData].height;
														continue;
													}
													if (scriptOpcode == 5305) {
														interfaceType = Preferences.fullScreenHeight;
														interfaceData = Preferences.fullScreenWidth;
														childCount = -1;
														@Pc(7833) DisplayMode[] local7833 = DisplayMode.getModes();
														for (start = 0; start < local7833.length; start++) {
															@Pc(7843) DisplayMode local7843 = local7833[start];
															if (interfaceData == local7843.width && local7843.height == interfaceType) {
																childCount = start;
																break;
															}
														}
														Static254.scriptIntValues[intValueIndex++] = childCount;
														continue;
													}
													if (scriptOpcode == 5306) {
														Static254.scriptIntValues[intValueIndex++] = DisplayMode.getWindowMode();
														continue;
													}
													if (scriptOpcode == 5307) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														DisplayMode.setWindowMode(false, interfaceData, -1, -1);
														continue;
													}
													if (scriptOpcode == 5308) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.favoriteWorlds;
														continue;
													}
													if (scriptOpcode == 5309) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Preferences.favoriteWorlds = interfaceData;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (scriptOpcode < 5500) {
													if (scriptOpcode == 5400) {
														local26 -= 2;
														chatTyped = Static3.scriptStringValues[local26];
														chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
														intValueIndex--;
														childCount = Static254.scriptIntValues[intValueIndex];
														Protocol.outboundBuffer.pIsaac1(117);
														Protocol.outboundBuffer.p1(Static229.method3937(chatTyped) + Static229.method3937(chatTypedLowercase) + 1);
														Protocol.outboundBuffer.pjstr(chatTyped);
														Protocol.outboundBuffer.pjstr(chatTypedLowercase);
														Protocol.outboundBuffer.p1(childCount);
														continue;
													}
													if (scriptOpcode == 5401) {
														intValueIndex -= 2;
														client.aShortArray88[Static254.scriptIntValues[intValueIndex]] = (short) ColorUtils.rgbToHsl(Static254.scriptIntValues[intValueIndex + 1]);
														Static211.method924();
														Static269.method2172();
														Static278.method4649();
														Static11.method443();
														method1807();
														continue;
													}
													if (scriptOpcode == 5405) {
														intValueIndex -= 2;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														if (interfaceData >= 0 && interfaceData < 2) {
															Camera.anIntArrayArrayArray9[interfaceData] = new int[interfaceType << 1][4];
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
														if (interfaceData >= 0 && interfaceData < 2 && Camera.anIntArrayArrayArray9[interfaceData] != null && interfaceType >= 0 && Camera.anIntArrayArrayArray9[interfaceData].length > interfaceType) {
															Camera.anIntArrayArrayArray9[interfaceData][interfaceType] = new int[] { (childCount >> 14 & 0x3FFF) * 128, local652, (childCount & 0x3FFF) * 128, local8108 };
															Camera.anIntArrayArrayArray9[interfaceData][interfaceType + 1] = new int[] { (start >> 14 & 0x3FFF) * 128, childId, (start & 0x3FFF) * 128 };
														}
														continue;
													}
													if (scriptOpcode == 5407) {
														intValueIndex--;
														interfaceData = Camera.anIntArrayArrayArray9[Static254.scriptIntValues[intValueIndex]].length >> 1;
														Static254.scriptIntValues[intValueIndex++] = interfaceData;
														continue;
													}
													if (scriptOpcode == 5411) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														if (GameShell.frame == null) {
															Static169.openUrl(Static15.method479(), false);
														} else {
															System.exit(0);
														}
														continue;
													}
													if (scriptOpcode == 5419) {
														chatTyped = Static72.aClass100_447;
														if (Player.lastLogAddress != null) {
															chatTyped = JString.formatIp(Player.lastLogAddress.intArg2);
															try {
																if (Player.lastLogAddress.result != null) {
																	@Pc(8281) byte[] local8281 = ((String) Player.lastLogAddress.result).getBytes("ISO-8859-1");
																	chatTyped = JString.decodeString(local8281, local8281.length, 0);
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
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														intValueIndex--;
														local1552 = Static254.scriptIntValues[intValueIndex] == 1;
														local26--;
														chatTyped = Static3.scriptStringValues[local26];
														@Pc(8356) JString local8356 = JString.concatenate(new JString[] { Static15.method479(), chatTyped });
														if (GameShell.frame != null || local1552 && SignLink.anInt5928 != 3 && SignLink.osName.startsWith("win") && !client.haveIe6) {
															Protocol.newTab = local1552;
															url = local8356;
															Protocol.openUrlRequest = GameShell.signLink.openUrl(new String(local8356.method3148(), "ISO-8859-1"));
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
																Static103.aClass100Array88 = new JString[Static132.anIntArray309[client.game]];
															}
															Static103.aClass100Array88[childCount] = chatTyped;
														}
														if (chatTypedLowercase.length() > 0) {
															if (Static263.aClass100Array174 == null) {
																Static263.aClass100Array174 = new JString[Static132.anIntArray309[client.game]];
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
														client.js5Archive8.isFileReady(Static55.anInt1736);
														client.js5Archive8.isFileReady(Static169.anInt4073);
														client.js5Archive8.isFileReady(Static85.anInt2261);
														client.js5Archive8.isFileReady(Static136.anInt3324);
														client.js5Archive8.isFileReady(Static254.anInt5556);
														InterfaceList.aBoolean298 = true;
														continue;
													}
													if (scriptOpcode == 5425) {
														LoginManager.method4637();
														InterfaceList.aBoolean298 = false;
														continue;
													}
													if (scriptOpcode == 5426) {
														intValueIndex--;
														anInt5794 = Static254.scriptIntValues[intValueIndex];
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
														Camera.method2722(false, childCount, interfaceType, local652, (interfaceData & 0x3FFF) - Camera.originZ, (interfaceData >> 14 & 0x3FFF) - Camera.originX);
														continue;
													}
													if (scriptOpcode == 5501) {
														intValueIndex -= 4;
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														interfaceData = Static254.scriptIntValues[intValueIndex];
														local652 = Static254.scriptIntValues[intValueIndex + 3];
														childCount = Static254.scriptIntValues[intValueIndex + 2];
														Camera.method3849(interfaceType, (interfaceData & 0x3FFF) - Camera.originZ, childCount, (interfaceData >> 14 & 0x3FFF) - Camera.originX, local652);
														continue;
													}
													if (scriptOpcode == 5502) {
														intValueIndex -= 6;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData >= 2) {
															throw new RuntimeException();
														}
														Camera.anInt3718 = interfaceData;
														interfaceType = Static254.scriptIntValues[intValueIndex + 1];
														if (Camera.anIntArrayArrayArray9[Camera.anInt3718].length >> 1 <= interfaceType + 1) {
															throw new RuntimeException();
														}
														Camera.anInt3125 = interfaceType;
														Camera.anInt5224 = 0;
														Camera.anInt5101 = Static254.scriptIntValues[intValueIndex + 2];
														Camera.anInt5843 = Static254.scriptIntValues[intValueIndex + 3];
														childCount = Static254.scriptIntValues[intValueIndex + 4];
														if (childCount >= 2) {
															throw new RuntimeException();
														}
														Camera.anInt1694 = childCount;
														local652 = Static254.scriptIntValues[intValueIndex + 5];
														if (Camera.anIntArrayArrayArray9[Camera.anInt1694].length >> 1 <= local652 + 1) {
															throw new RuntimeException();
														}
														Camera.anInt2119 = local652;
														Camera.cameraType = 3;
														continue;
													}
													if (scriptOpcode == 5503) {
														Camera.resetCameraEffects();
														continue;
													}
													if (scriptOpcode == 5504) {
														intValueIndex -= 2;
														Camera.orbitCameraPitch = Static254.scriptIntValues[intValueIndex];
														Camera.orbitCameraYaw = Static254.scriptIntValues[intValueIndex + 1];
														if (Camera.cameraType == 2) {
															Camera.cameraYaw = Camera.orbitCameraYaw;
															Camera.cameraPitch = Camera.orbitCameraPitch;
														}
														SceneCamera.clampCameraAngle();
														continue;
													}
													if (scriptOpcode == 5505) {
														Static254.scriptIntValues[intValueIndex++] = Camera.orbitCameraPitch;
														continue;
													}
													if (scriptOpcode == 5506) {
														Static254.scriptIntValues[intValueIndex++] = Camera.orbitCameraYaw;
														continue;
													}
												} else if (scriptOpcode < 5700) {
													if (scriptOpcode == 5600) {
														local26 -= 2;
														chatTyped = Static3.scriptStringValues[local26];
														chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
														intValueIndex--;
														childCount = Static254.scriptIntValues[intValueIndex];
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && Static82.anInt2231 == 0) {
															LoginManager.login(chatTyped, chatTypedLowercase, childCount);
														}
														continue;
													}
													if (scriptOpcode == 5601) {
														LoginManager.method3395();
														continue;
													}
													if (scriptOpcode == 5602) {
														if (LoginManager.step == 0) {
															LoginManager.reply = -2;
														}
														continue;
													}
													if (scriptOpcode == 5603) {
														intValueIndex -= 4;
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && Static82.anInt2231 == 0) {
															CreateManager.checkInfo(Static254.scriptIntValues[intValueIndex + 2], Static254.scriptIntValues[intValueIndex + 3], Static254.scriptIntValues[intValueIndex], Static254.scriptIntValues[intValueIndex + 1]);
														}
														continue;
													}
													if (scriptOpcode == 5604) {
														local26--;
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && Static82.anInt2231 == 0) {
															CreateManager.checkName(Static3.scriptStringValues[local26].encode37());
														}
														continue;
													}
													if (scriptOpcode == 5605) {
														intValueIndex -= 4;
														local26 -= 2;
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && Static82.anInt2231 == 0) {
															CreateManager.createAccount(Static254.scriptIntValues[intValueIndex], Static254.scriptIntValues[intValueIndex + 3], Static254.scriptIntValues[intValueIndex + 1], Static3.scriptStringValues[local26 + 1], Static3.scriptStringValues[local26].encode37(), Static254.scriptIntValues[intValueIndex + 2]);
														}
														continue;
													}
													if (scriptOpcode == 5606) {
														if (CreateManager.step == 0) {
															CreateManager.reply = -2;
														}
														continue;
													}
													if (scriptOpcode == 5607) {
														Static254.scriptIntValues[intValueIndex++] = LoginManager.reply;
														continue;
													}
													if (scriptOpcode == 5608) {
														Static254.scriptIntValues[intValueIndex++] = PreciseSleep.anInt5202;
														continue;
													}
													if (scriptOpcode == 5609) {
														Static254.scriptIntValues[intValueIndex++] = CreateManager.reply;
														continue;
													}
													if (scriptOpcode == 5610) {
														for (interfaceData = 0; interfaceData < 5; interfaceData++) {
															Static3.scriptStringValues[local26++] = CreateManager.suggestedNames.length > interfaceData ? CreateManager.suggestedNames[interfaceData].toTitleCase() : Static72.aClass100_447;
														}
														CreateManager.suggestedNames = null;
														continue;
													}
													if (scriptOpcode == 5611) {
														Static254.scriptIntValues[intValueIndex++] = LoginManager.disallowResult;
														continue;
													}
												} else if (scriptOpcode < 6100) {
													if (scriptOpcode == 6001) {
														intValueIndex--;
														int brightness = Static254.scriptIntValues[intValueIndex];
														if (brightness < 1) {
															brightness = 1;
														}
														if (brightness > 4) {
															brightness = 4;
														}
														Preferences.brightness = brightness;
														if (!GlRenderer.enabled || !Preferences.highDetailLighting) {
															if (Preferences.brightness == 1) {
																Pix3D.setBrightness(0.9F);
															}
															if (Preferences.brightness == 2) {
																Pix3D.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Pix3D.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Pix3D.setBrightness(0.6F);
															}
														}
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
															if (!Preferences.highDetailLighting) {
																Static145.method2742();
															}
														}
														Static269.method2172();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6002) {
														intValueIndex--;
														Preferences.setAllLevelsVisible(Static254.scriptIntValues[intValueIndex] == 1);
														LocTypeList.clear();
														Static145.method2742();
														Static269.method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6003) {
														intValueIndex--;
														Preferences.roofsVisible = Static254.scriptIntValues[intValueIndex] == 1;
														Static269.method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6005) {
														intValueIndex--;
														Preferences.groundDecoration = Static254.scriptIntValues[intValueIndex] == 1;
														Static145.method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6006) {
														intValueIndex--;
														Preferences.highDetailTextures = Static254.scriptIntValues[intValueIndex] == 1;
														((Js5GlTextureProvider) Rasterizer.textureProvider).method3245(!Preferences.highDetailTextures);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6007) {
														intValueIndex--;
														Static15.lowMemory = Static254.scriptIntValues[intValueIndex] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6008) {
														intValueIndex--;
														Static11.aBoolean15 = Static254.scriptIntValues[intValueIndex] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6009) {
														intValueIndex--;
														Static159.aBoolean189 = Static254.scriptIntValues[intValueIndex] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6010) {
														intValueIndex--;
														Static209.aBoolean240 = Static254.scriptIntValues[intValueIndex] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6011) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Static139.anInt3451 = interfaceData;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6012) {
														if (GlRenderer.enabled) {
															MaterialManager.setMaterial(0, 0);
														}
														intValueIndex--;
														Preferences.highDetailLighting = Static254.scriptIntValues[intValueIndex] == 1;
														if (GlRenderer.enabled && Preferences.highDetailLighting) {
															Pix3D.setBrightness(0.7F);
														} else {
															if (Preferences.brightness == 1) {
																Pix3D.setBrightness(0.9F);
															}
															if (Preferences.brightness == 2) {
																Pix3D.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Pix3D.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Pix3D.setBrightness(0.6F);
															}
														}
														Static145.method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6014) {
														intValueIndex--;
														Static220.aBoolean244 = Static254.scriptIntValues[intValueIndex] == 1;
														if (GlRenderer.enabled) {
															Static145.method2742();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6015) {
														intValueIndex--;
														Preferences.fogEnabled = Static254.scriptIntValues[intValueIndex] == 1;
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6016) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (GlRenderer.enabled) {
															GameShell.canvasReplaceRecommended = true;
														}
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Preferences.antiAliasingMode = interfaceData;
														continue;
													}
													if (scriptOpcode == 6017) {
														intValueIndex--;
														Preferences.stereo = Static254.scriptIntValues[intValueIndex] == 1;
														client.method930();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
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
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
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
																Static122.method2410(client.js5Archive6, BZip2State.anInt4363, interfaceData);
																Static144.jingle = false;
															} else if (interfaceData == 0) {
																Static241.method4548();
																Static144.jingle = false;
															} else {
																Static230.method3956(interfaceData);
															}
															Static12.anInt391 = interfaceData;
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
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
														Preferences.ambientSoundsVolume = interfaceData;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (scriptOpcode == 6021) {
														intValueIndex--;
														neverRemoveRoofs = Static254.scriptIntValues[intValueIndex] == 1;
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
														if (GameShell.maxMemory < 96) {
															local1552 = true;
															interfaceData = 0;
														}
														Preferences.setParticles(interfaceData);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														Static254.scriptIntValues[intValueIndex++] = local1552 ? 0 : 1;
														continue;
													}
													if (scriptOpcode == 6024) {
														intValueIndex--;
														interfaceData = Static254.scriptIntValues[intValueIndex];
														if (interfaceData < 0 || interfaceData > 2) {
															interfaceData = 0;
														}
														Preferences.windowMode = interfaceData;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (scriptOpcode == 6028) {
														intValueIndex--;
														Preferences.cursorsEnabled = Static254.scriptIntValues[intValueIndex] != 0;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (scriptOpcode < 6200) {
													if (scriptOpcode == 6101) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.brightness;
														continue;
													}
													if (scriptOpcode == 6102) {
														Static254.scriptIntValues[intValueIndex++] = Static138.allLevelsvisible() ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6103) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.roofsVisible ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6105) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.groundDecoration ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6106) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.highDetailTextures ? 1 : 0;
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
														Static254.scriptIntValues[intValueIndex++] = Preferences.highDetailLighting ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6114) {
														Static254.scriptIntValues[intValueIndex++] = Static220.aBoolean244 ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6115) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.fogEnabled ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6116) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.antiAliasingMode;
														continue;
													}
													if (scriptOpcode == 6117) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.stereo ? 1 : 0;
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
														Static254.scriptIntValues[intValueIndex++] = Preferences.ambientSoundsVolume;
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
														Static254.scriptIntValues[intValueIndex++] = Preferences.getParticleSetting();
														continue;
													}
													if (scriptOpcode == 6124) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.windowMode;
														continue;
													}
													if (scriptOpcode == 6128) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.cursorsEnabled ? 1 : 0;
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
														Static115.method2314(InterfaceList.aClass13_26.width, 0, InterfaceList.aClass13_26.height, 0, false);
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
														Static254.scriptIntValues[intValueIndex++] = (int) (MonotonicTime.currentTimeMillis() / 60000L);
														continue;
													}
													if (scriptOpcode == 6301) {
														Static254.scriptIntValues[intValueIndex++] = (int) (MonotonicTime.currentTimeMillis() / 86400000L) - 11745;
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
														Static102.aCalendar2.setTime(new Date(MonotonicTime.currentTimeMillis()));
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
														Static254.scriptIntValues[intValueIndex++] = client.showVideoAd() ? 1 : 0;
														continue;
													}
													if (scriptOpcode == 6406) {
														Static254.scriptIntValues[intValueIndex++] = Static267.method4527() ? 1 : 0;
														continue;
													}
												} else if (scriptOpcode < 6600) {
													if (scriptOpcode == 6500) {
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															Static254.scriptIntValues[intValueIndex++] = WorldList.fetch() == -1 ? 0 : 1;
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
														local10191 = Static88.method1821();
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
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															Static254.scriptIntValues[intValueIndex++] = Static176.method3303(interfaceData) ? 1 : 0;
															continue;
														}
														Static254.scriptIntValues[intValueIndex++] = 0;
														continue;
													}
													if (scriptOpcode == 6504) {
														intValueIndex--;
														Static164.anInt3988 = Static254.scriptIntValues[intValueIndex];
														Preferences.write(GameShell.signLink);
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
														Preferences.aBoolean63 = Static254.scriptIntValues[intValueIndex] == 1;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (scriptOpcode == 6601) {
														Static254.scriptIntValues[intValueIndex++] = Preferences.aBoolean63 ? 1 : 0;
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
											Static3.scriptStringValues[local26++] = LocTypeList.get(interfaceData).getParam(local5294.aClass100_544, interfaceType);
										} else {
											Static254.scriptIntValues[intValueIndex++] = LocTypeList.get(interfaceData).getParam(local5294.anInt2667, interfaceType);
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
										Static3.scriptStringValues[local26++] = JString.concatenate(new JString[] { chatTyped, JString.parseInt(interfaceType) });
										continue;
									}
									if (scriptOpcode == 4101) {
										// append
										local26 -= 2;
										chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
										chatTyped = Static3.scriptStringValues[local26];
										Static3.scriptStringValues[local26++] = JString.concatenate(new JString[] { chatTyped, chatTypedLowercase });
										continue;
									}
									if (scriptOpcode == 4102) {
										// append_signnum
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static3.scriptStringValues[local26++] = JString.concatenate(new JString[] { chatTyped, Static110.method2285(interfaceType) });
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
										Static3.scriptStringValues[local26++] = JString.concatenate(new JString[] { JString.parseInt(local652), Static163.aClass100_767, DateUtil.aClass100Array40[start], Static163.aClass100_767, JString.parseInt(childId) });
										continue;
									}
									if (scriptOpcode == 4105) {
										// text_gender
										local26 -= 2;
										chatTypedLowercase = Static3.scriptStringValues[local26 + 1];
										chatTyped = Static3.scriptStringValues[local26];
										if (PlayerList.self.appearance != null && PlayerList.self.appearance.aBoolean141) {
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
										Static3.scriptStringValues[local26++] = JString.parseInt(interfaceData);
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
										Static254.scriptIntValues[intValueIndex++] = FontMetricsList.get(childCount).getParagraphLineCount(chatTyped, interfaceType);
										continue;
									}
									if (scriptOpcode == 4109) {
										intValueIndex -= 2;
										local26--;
										chatTyped = Static3.scriptStringValues[local26];
										childCount = Static254.scriptIntValues[intValueIndex + 1];
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = FontMetricsList.get(childCount).getMaxLineWidth(chatTyped, interfaceType);
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
										Static3.scriptStringValues[local26++] = Font.escape(chatTyped);
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
										Static254.scriptIntValues[intValueIndex++] = CharUtils.isValidChar(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4114) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = CharUtils.method433(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4115) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = CharUtils.isLetter(interfaceData) ? 1 : 0;
										continue;
									}
									if (scriptOpcode == 4116) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = CharUtils.isDigit(interfaceData) ? 1 : 0;
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
											start = chatTyped.charAt(local652);
											if (start == 60) {
												local12220 = true;
											} else if (start == 62) {
												local12220 = false;
											} else if (!local12220) {
												chatTypedLowercase.append(start);
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
										Static254.scriptIntValues[intValueIndex++] = CharUtils.toLowerCase(interfaceData);
										continue;
									}
									if (scriptOpcode == 4123) {
										intValueIndex--;
										interfaceData = Static254.scriptIntValues[intValueIndex];
										Static254.scriptIntValues[intValueIndex++] = CharUtils.toUpperCase(interfaceData);
										continue;
									}
									if (scriptOpcode == 4124) {
										intValueIndex--;
										local12388 = Static254.scriptIntValues[intValueIndex] != 0;
										intValueIndex--;
										interfaceType = Static254.scriptIntValues[intValueIndex];
										Static3.scriptStringValues[local26++] = Static182.method3360(client.language, local12388, 0, (long) interfaceType);
										continue;
									}
								}
							}
						} else {
							if (scriptOpcode < 2000) {
								local1182 = local1020 ? Static274.aClass13_24 : Static227.aClass13_25;
							} else {
								intValueIndex--;
								local1182 = InterfaceList.getComponent(Static254.scriptIntValues[intValueIndex]);
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
								InterfaceList.redraw(local1182);
								InterfaceList.update(local1182);
								if (local1182.createdComponentId == -1) {
									DelayedStateChange.method4675(local1182.id);
								}
								continue;
							}
							if (scriptOpcode == 1001) {
								// setsize
								intValueIndex -= 4;
								local1182.baseWidth = Static254.scriptIntValues[intValueIndex];
								local1182.baseHeight = Static254.scriptIntValues[intValueIndex + 1];
								local1182.anInt451 = 0;
								local1182.anInt526 = 0;
								interfaceType = Static254.scriptIntValues[intValueIndex + 2];
								childCount = Static254.scriptIntValues[intValueIndex + 3];
								if (childCount < 0) {
									childCount = 0;
								} else if (childCount > 4) {
									childCount = 4;
								}
								local1182.dynamicHeightValue = (byte) childCount;
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 4) {
									interfaceType = 4;
								}
								local1182.dynamicWidthValue = (byte) interfaceType;
								InterfaceList.redraw(local1182);
								InterfaceList.update(local1182);
								if (local1182.type == 0) {
									InterfaceList.method531(local1182, false);
								}
								continue;
							}
							if (scriptOpcode == 1003) {
								// sethide
								intValueIndex--;
								local1552 = Static254.scriptIntValues[intValueIndex] == 1;
								if (local1552 != local1182.hidden) {
									local1182.hidden = local1552;
									InterfaceList.redraw(local1182);
								}
								if (local1182.createdComponentId == -1) {
									DelayedStateChange.method1906(local1182.id);
								}
								continue;
							}
							if (scriptOpcode == 1004) {
								// setaspect
								intValueIndex -= 2;
								local1182.aspectWidth = Static254.scriptIntValues[intValueIndex];
								local1182.aspectHeight = Static254.scriptIntValues[intValueIndex + 1];
								InterfaceList.redraw(local1182);
								InterfaceList.update(local1182);
								if (local1182.type == 0) {
									InterfaceList.method531(local1182, false);
								}
								continue;
							}
							if (scriptOpcode == 1005) {
								intValueIndex--;
								local1182.noClickThrough = Static254.scriptIntValues[intValueIndex] == 1;
								continue;
							}
						}
					}
				}
				throw new IllegalStateException();
			}
		} catch (@Pc(14378) Exception local14378) {
			if (clientScript.name == null) {
				if (client.modeWhere != 0) {
					Chat.addMessage(Static72.aClass100_447, 0, Static136.aClass100_633);
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + local44, local14378);
			} else {
				@Pc(14385) JString local14385 = Static87.method1804(30);
				local14385.method3113(Static219.aClass100_928).method3113(clientScript.name);
				for (listenersIndex = Static138.invokedScriptIndex - 1; listenersIndex >= 0; listenersIndex--) {
					local14385.method3113(Static40.aClass100_253).method3113(Static67.GO_SUB_FRAMES[listenersIndex].script.name);
				}
				if (local44 == 40) {
					listenersIndex = local33[scriptIndex];
					local14385.method3113(Static176.aClass100_802).method3113(JString.parseInt(listenersIndex));
				}
				if (client.modeWhere != 0) {
					Chat.addMessage(Static72.aClass100_447, 0, JString.concatenate(new JString[] { Static167.aClass100_780, clientScript.name}));
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + local44 + new String(local14385.method3148()), local14378);
			}
		}
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(Z)V")
	public static void method1807() {
		for (@Pc(11) int local11 = 0; local11 < 100; local11++) {
			InterfaceList.aBooleanArray100[local11] = true;
		}
	}
}
