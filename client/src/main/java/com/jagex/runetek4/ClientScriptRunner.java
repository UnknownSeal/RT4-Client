package com.jagex.runetek4;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.def.VarbitDefinition;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.game.world.entity.PlayerAppearance;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.util.IntUtils;
import com.jagex.runetek4.util.MathUtils;
import com.jagex.runetek4.util.StringUtils;
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

	@OriginalMember(owner = "runetek4.client!t", name = "m", descriptor = "Z")
	public static volatile boolean focus_in = true;

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

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(I)V")
	public static void clear() {
		Static67.aClass99_20.clean();
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
					Static276.aClass3_Sub2_Sub1Array11[local17.anInt4048].drawSprite(arg1 + Static65.anInt1951 - 12, arg5 + -28 - -Static16.anInt548);
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
		Cross.y = Static60.mouseClickY;
		Cross.crossCycle = 0;
		Cross.crossMode = 2;
		Cross.x = aClass6.mouseClickX;
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
		if (Static44.aBoolean83 && InterfaceList.aBoolean174) {
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
			@Pc(109) int local109 = local41 - Static20.anInt660;
			@Pc(114) int local114 = local30 - Static124.anInt3075;
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
				local176.anInt3102 = local122;
				local176.anInt3097 = local130;
				run(local176);
			}
			if (Static22.activeInterfaceType == 0) {
				if (aBoolean172) {
					if (aClass13_14.onDragRelease != null) {
						local176 = new HookRequest();
						local176.anInt3097 = local130;
						local176.aClass13_16 = Static56.aClass13_12;
						local176.anInt3102 = local122;
						local176.arguments = aClass13_14.onDragRelease;
						local176.source = aClass13_14;
						run(local176);
					}
					if (Static56.aClass13_12 != null && Static36.method938(aClass13_14) != null) {
						Protocol.outboundBuffer.pIsaac1(79);
						Protocol.outboundBuffer.p4_alt3(aClass13_14.id);
						Protocol.outboundBuffer.p2_alt1(Static56.aClass13_12.createdComponentId);
						Protocol.outboundBuffer.p4(Static56.aClass13_12.id);
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
		Static88.runClientScripts(200000, arg0);
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
			Rasterizer.setSize();
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
								Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_FPS2, JString.parseInt(Static243.fps) }), local270, local276, 16776960, -1);
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
									cardMemory = (Static63.oncard_texture + Static63.oncard_geometry + Static63.oncard_2d) / 1024;
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
								MiniMenu.cursors[0] = Static35.anInt1092;
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
									MiniMenu.cursors[0] = Static35.anInt1092;
									MiniMenu.actions[0] = 1005;
									MiniMenu.opBases[0] = JString.EMPTY;
								}
								Static6.method86(local1186.interfaceId, local166, local302, local123, rectangle, local291, local164, local114);
							}
							if (GlRenderer.enabled) {
								GlRaster.setClip(arg0, arg6, arg4, arg7);
							} else {
								SoftwareRaster.setClip(arg0, arg6, arg4, arg7);
								Rasterizer.setSize();
							}
						}
						if (Static223.aBooleanArray116[rectangle] || Cheat.rectDebug > 1) {
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
												if (arg0 < memory + 32 && memory < arg4 && arg6 < color + 32 && color < arg7 || component == Static118.component && Static4.selectedInventorySlot == local270) {
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
													} else if (Static118.component == component && local270 == Static4.selectedInventorySlot) {
														cardMemory = Mouse.lastMouseX - Static149.anInt3554;
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
														sprite.drawSprite(memory, color);
													}
												}
											} else if (component.invSprite != null && local270 < 20) {
												@Pc(1381) Sprite local1381 = component.method482(local270);
												if (local1381 != null) {
													local1381.drawSprite(memory, color);
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
																		sprite.drawSprite(local123 + local276 * local563, local468 * local571 + local114);
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
																	sprite.drawSprite(cardMemory * local276 + local123, local468 * dragY + local114);
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
														sprite.method1422(local123, local114, component.width, component.height, 256 - (alpha & 0xFF));
													} else if (local276 == component.width && local468 == component.height) {
														sprite.drawSprite(local123, local114);
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
												sprite.drawSprite(local123, local114);
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
			Rasterizer.setSize();
		}
		if (aBoolean108 || anInt3751 < arg2 || anInt3751 >= arg3 + arg2 || arg4 > anInt1892 || arg0 + arg4 <= anInt1892) {
			Static39.aBoolean77 = false;
			Static2.anInt7 = 0;
		} else {
			Static39.aBoolean77 = true;
			Static2.anInt7 = 0;
			jitter = Static247.anInt5405;
			@Pc(344) int local344 = Static1.anInt4;
			type = Static240.anInt5334;
			Static150.anInt3582 = type + (jitter - type) * (-arg2 + anInt3751) / arg3;
			@Pc(361) int local361 = Static148.anInt3535;
			Static34.anInt1053 = (local361 - local344) * (anInt1892 - arg4) / arg0 + local344;
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
					local44.drawSprite(arg0, arg1);
				}
			} else {
				Rasterizer.method2504(arg0, arg1, arg2.anIntArray37, arg2.anIntArray45);
			}
		} else if (GlRenderer.enabled) {
			((GlSprite) Static106.aClass3_Sub2_Sub1_7).method1427(arg0, arg1, arg2.width, arg2.height, Static106.aClass3_Sub2_Sub1_7.width / 2, Static106.aClass3_Sub2_Sub1_7.height / 2, Camera.orbitCameraYaw, 256, (GlSprite) arg2.method489(false));
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
			Static214.method4364(arg2, 0, Static48.anInt1449, 0, 0, IdkTypeList.anInt4296, arg1, 0);
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
		WorldMap.aClass3_Sub2_Sub1_2.render(arg0, arg3);
		@Pc(147) int local147 = arg1 * anInt2884 / IdkTypeList.anInt4296 + arg3;
		@Pc(153) int local153 = Static37.anInt1176 * arg1 / IdkTypeList.anInt4296;
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
				@Pc(258) int local258 = arg3 + local238.anInt4314 * arg1 / IdkTypeList.anInt4296;
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
}
