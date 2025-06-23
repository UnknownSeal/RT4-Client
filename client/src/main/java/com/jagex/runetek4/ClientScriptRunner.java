package com.jagex.runetek4;

import java.io.*;
import java.util.Calendar;
import java.util.Date;

import com.jagex.runetek4.audio.midi.MidiPlayer;
import com.jagex.runetek4.cache.cs.ClientScript;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.client.client;
import com.jagex.runetek4.config.types.enums.EnumTypeList;
import com.jagex.runetek4.config.types.idk.IDKTypeList;
import com.jagex.runetek4.config.types.inv.InvTypeList;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.param.ParamType;
import com.jagex.runetek4.config.types.param.ParamTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatCatType;
import com.jagex.runetek4.config.types.quickchat.QuickChatCatTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.struct.StructTypeList;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.types.enums.EnumType;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseType;
import com.jagex.runetek4.node.SecondaryLinkedList;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.Tile;
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

	@OriginalMember(owner = "runetek4.client!ab", name = "j", descriptor = "[Lclient!na;")
	public static final JString[] scriptStringValues = new JString[1000];
	@OriginalMember(owner = "runetek4.client!uj", name = "t", descriptor = "[I")
	public static final int[] scriptIntValues = new int[1000];
	@OriginalMember(owner = "client!fl", name = "Q", descriptor = "Lclient!na;")
	public static final JString EMPTY_STRING = JString.parse("");
	@OriginalMember(owner = "client!bb", name = "A", descriptor = "Lclient!na;")
	public static final JString aClass100_74 = JString.parse("::");
	@OriginalMember(owner = "client!be", name = "ib", descriptor = "Lclient!na;")
	public static final JString EVENT_OPBASE = JString.parse("event_opbase");
	@OriginalMember(owner = "client!da", name = "O", descriptor = "Lclient!na;")
	public static final JString aClass100_253 = JString.parse("(U0a )2 via: ");
	@OriginalMember(owner = "client!fl", name = "H", descriptor = "Lclient!na;")
	public static final JString aClass100_446 = JString.parse("<img=0>");
	@OriginalMember(owner = "runetek4.client!nd", name = "b", descriptor = "Lclient!na;")
	public static final JString aClass100_780 = JString.parse("Clientscript error in: ");
	@OriginalMember(owner = "runetek4.client!hm", name = "R", descriptor = "Lclient!na;")
	public static final JString aClass100_537 = JString.parse("<img=1>");
	@OriginalMember(owner = "runetek4.client!hn", name = "K", descriptor = "Ljava/util/Calendar;")
	public static final Calendar aCalendar2 = Calendar.getInstance();
	@OriginalMember(owner = "runetek4.client!kk", name = "m", descriptor = "Lclient!na;")
	public static final JString CS_ERROR = JString.parse("Clientscript error )2 check log for details");
	@OriginalMember(owner = "client!fe", name = "nc", descriptor = "[Lclient!hj;")
	public static final GoSubFrame[] callStack = new GoSubFrame[50];
	@OriginalMember(owner = "client!ee", name = "j", descriptor = "[I")
	public static final int[] anIntArray140 = new int[5];
	@OriginalMember(owner = "runetek4.client!oe", name = "i", descriptor = "[[I")
	public static final int[][] anIntArrayArray33 = new int[5][5000];
	@OriginalMember(owner = "runetek4.client!rl", name = "eb", descriptor = "Lclient!na;")
	public static final JString aClass100_928 = JString.parse("(U0a )2 in: ");
	@OriginalMember(owner = "client!fe", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_639 = JString.parse(" ");
	@OriginalMember(owner = "client!dc", name = "M", descriptor = "Lclient!na;")
	public static final JString aClass100_268 = JString.parse(")4");
	@OriginalMember(owner = "runetek4.client!he", name = "gb", descriptor = "Lclient!na;")
	public static final JString aClass100_518 = JString.parse("www");
	@OriginalMember(owner = "client!e", name = "Tc", descriptor = "Lclient!na;")
	public static final JString aClass100_365 = JString.parse("www)2wtqa");
	@OriginalMember(owner = "runetek4.client!lk", name = "J", descriptor = "Lclient!na;")
	public static final JString aClass100_687 = JString.parse(")4p=");
	@OriginalMember(owner = "client!en", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_424 = JString.parse("http:)4)4");
	@OriginalMember(owner = "client!gf", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_886 = JString.parse(")3runescape)3com)4l=");
	@OriginalMember(owner = "runetek4.client!v", name = "a", descriptor = "Lclient!na;")
	public static final JString aClass100_98 = JString.parse(")4a=");
	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] anIntArrayArray6 = new int[104][104];
	@OriginalMember(owner = "runetek4.client!n", name = "e", descriptor = "Lclient!na;")
	public static final JString aClass100_767 = JString.parse(")2");
	@OriginalMember(owner = "client!je", name = "U", descriptor = "Lclient!na;")
	public static final JString SHOWINGVIDEOAD = JString.parse("showingVideoAd");
	@OriginalMember(owner = "runetek4.client!ob", name = "p", descriptor = "Lclient!na;")
	public static final JString aClass100_802 = JString.parse("(U0a )2 non)2existant gosub script)2num: ");
	@OriginalMember(owner = "runetek4.client!af", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_10 = JString.parse("<br>");

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
	@OriginalMember(owner = "client!fc", name = "a", descriptor = "I")
	public static int anInt1951 = -1;
	@OriginalMember(owner = "runetek4.client!jh", name = "n", descriptor = "Lclient!bd;")
	public static QuickChatPhrase activePhrase;
	@OriginalMember(owner = "runetek4.client!wf", name = "j", descriptor = "Lclient!be;")
	public static Component secondaryActiveComponent;
	@OriginalMember(owner = "runetek4.client!sg", name = "i", descriptor = "Lclient!be;")
	public static Component primaryActiveComponent;
	@OriginalMember(owner = "runetek4.client!og", name = "g", descriptor = "[Lclient!na;")
	public static JString[] stringLocals;
	@OriginalMember(owner = "runetek4.client!rh", name = "a", descriptor = "[I")
	public static int[] intLocals;
	@OriginalMember(owner = "runetek4.client!km", name = "ad", descriptor = "I")
	public static int fp = 0;
	@OriginalMember(owner = "runetek4.client!od", name = "g", descriptor = "S")
	public static short aShort25 = 256;
	@OriginalMember(owner = "client!an", name = "db", descriptor = "S")
	public static short aShort9 = 205;
	@OriginalMember(owner = "runetek4.client!mc", name = "tb", descriptor = "S")
	public static short aShort22 = 1;
	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;
	@OriginalMember(owner = "runetek4.client!nc", name = "n", descriptor = "I")
	public static int anInt4055 = 0;
	@OriginalMember(owner = "runetek4.client!tm", name = "i", descriptor = "I")
	public static int anInt5377 = 0;
	@OriginalMember(owner = "client!bn", name = "eb", descriptor = "I")
	public static int anInt773 = 0;
	@OriginalMember(owner = "client!ah", name = "n", descriptor = "I")
	public static int anInt983 = 0;
	@OriginalMember(owner = "runetek4.client!sc", name = "p", descriptor = "I")
	public static int anInt5029 = 0;
	@OriginalMember(owner = "runetek4.client!kd", name = "yb", descriptor = "S")
	public static short aShort21 = 32767;
	@OriginalMember(owner = "client!ee", name = "f", descriptor = "S")
	public static short aShort12 = 1;
	@OriginalMember(owner = "runetek4.client!kk", name = "j", descriptor = "I")
	public static int anInt3325 = 0;
	@OriginalMember(owner = "runetek4.client!vk", name = "f", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray15;
	@OriginalMember(owner = "runetek4.client!vg", name = "b", descriptor = "S")
	public static short aShort30 = 256;
	@OriginalMember(owner = "runetek4.client!lj", name = "z", descriptor = "[I")
	public static int[] anIntArray338 = new int[2];
	@OriginalMember(owner = "runetek4.client!vl", name = "i", descriptor = "[I")
	public static int[] anIntArray518 = new int[2];
	@OriginalMember(owner = "runetek4.client!tk", name = "K", descriptor = "[I")
	public static int[] anIntArray476 = new int[2];
	@OriginalMember(owner = "client!e", name = "xc", descriptor = "[I")
	public static int[] anIntArray134 = new int[2];
	@OriginalMember(owner = "client!ge", name = "k", descriptor = "[I")
	public static int[] anIntArray205 = new int[2];
	@OriginalMember(owner = "client!bf", name = "B", descriptor = "I")
	public static int anInt548 = -1;
	@OriginalMember(owner = "runetek4.client!pb", name = "rb", descriptor = "S")
	public static short aShort27 = 320;

	@OriginalMember(owner = "runetek4.client!t", name = "b", descriptor = "(I)V")
	public static void clear() {
		IDKTypeList.types.clean();
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
				} else if ((VarpDomain.oneMouseButton == 1 || MiniMenu.menuHasAddFriend(MiniMenu.menuActionRow - 1)) && MiniMenu.menuActionRow > 2) {
					determineMenuSize();
				} else if (MiniMenu.menuActionRow > 0) {
					MiniMenu.processMenuActions();
				}
				aClass13_14 = null;
			}
		} else if (anInt4851 > 1) {
			aClass13_14 = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "(ILclient!jl;)V")
	public static void run(@OriginalArg(1) HookRequest request) {
		run(200000, request);
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
					value = PlayerSkillXpTable.boostedLevels[script[pc++]];
				}
				if (opcode == 17) {
					nextOperator = 3;
				}
				if (opcode == 2) { // load_skill_base_level {skill}
					value = PlayerSkillXpTable.baseLevels[script[pc++]];
				}
				if (opcode == 3) { // load_skill_exp {skill}
					value = PlayerSkillXpTable.experience[script[pc++]];
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
					if (pc3 != -1 && (!ObjTypeList.get(pc3).members || LoginManager.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (pc3 + 1 == com.invSlotObjId[j]) {
								value += com.invSlotObjCount[j];
							}
						}
					}
				}
				if (opcode == 5) {  // load_var {id}
					value = VarpDomain.activeVarps[script[pc++]];
				}
				if (opcode == 6) {  // load_next_level_xp {skill}
					value = PlayerSkillXpTable.xpLevelLookup[PlayerSkillXpTable.baseLevels[script[pc++]] - 1];
				}
				if (opcode == 7) {
					value = VarpDomain.activeVarps[script[pc++]] * 100 / 46875;
				}
				if (opcode == 8) { // load_combat_level
					value = PlayerList.self.combatLevel;
				}
				if (opcode == 9) { // load_total_level
					for (pc2 = 0; pc2 < 25; pc2++) {
						if (PlayerSkillXpTable.ENABLED_SKILLS[pc2]) {
							value += PlayerSkillXpTable.baseLevels[pc2];
						}
					}
				}
				if (opcode == 10) { // load_inv_contains {interface id} {obj id}
					pc2 = script[pc++] << 16;
					pc2 += script[pc++];
					com = InterfaceList.getComponent(pc2);
					pc3 = script[pc++];
					if (pc3 != -1 && (!ObjTypeList.get(pc3).members || LoginManager.membersWorld)) {
						for (j = 0; j < com.invSlotObjId.length; j++) {
							if (com.invSlotObjId[j] == pc3 + 1) {
								value = 999999999;
								break;
							}
						}
					}
				}
				if (opcode == 11) { // load_energy
					value = Player.runEnergy;
				}
				if (opcode == 12) { // load_weight
					value = Player.weightCarried;
				}
				if (opcode == 13) { // load_bool {varp} {bit: 0..31}
					pc2 = VarpDomain.activeVarps[script[pc++]];
					@Pc(353) int leastSignificantBit = script[pc++];
					value = (0x1 << leastSignificantBit & pc2) == 0 ? 0 : 1;
				}
				if (opcode == 14) {
					pc2 = script[pc++];
					value = VarpDomain.getVarbitValue(pc2);
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
					if (Cheat.qaOpTest && (InterfaceList.getServerActiveProperties(component).events != 0 || component.type == 0) && alpha > 127) {
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
								if (MiniMenu.aBoolean302 && (MiniMenu.anInt4999 & 0x40) != 0) {
									@Pc(583) Component local583 = InterfaceList.getCreatedComponent(MiniMenu.anInt2512, MiniMenu.anInt506);
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
								@Pc(968) JString local968 = JString.concatenate(new JString[] { Cheat.DEBUG_CACHE, StringUtils.formatNumber(0, true, 2, (long) local571), aClass100_672, JString.parseInt(local563), aClass100_80});
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
							@Pc(1186) SubInterface local1186 = (SubInterface) InterfaceList.openInterfaces.get((long) component.id);
							if (local1186 != null) {
								if (local1186.anInt5879 == 0 && !aBoolean108 && anInt3751 >= local166 && local164 <= anInt1892 && local302 > anInt3751 && anInt1892 < local291 && !Cheat.qaOpTest) {
									MiniMenu.ops[0] = LocalizedText.CANCEL;
									MiniMenu.menuActionRow = 1;
									MiniMenu.cursors[0] = MiniMenu.anInt1092;
									MiniMenu.actions[0] = 1005;
									MiniMenu.opBases[0] = JString.EMPTY;
								}
								method86(local1186.interfaceId, local166, local302, local123, rectangle, local291, local164, local114);
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
																top = SoftwareRaster.clipTop;
																bottom = SoftwareRaster.clipBottom;
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
													@Pc(2630) SeqType local2630 = local276 == -1 ? null : SeqTypeList.get(local276);
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
													@Pc(2760) SeqType local2760 = local276 == -1 ? null : SeqTypeList.get(local276);
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
												@Pc(2689) SeqType local2689 = SeqTypeList.get(local276);
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
																local3159 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local2611.name, JString.aClass100_978 });
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
														SoftwareRaster.drawDiagonalLine(local123, local276, local468, memory, component.color);
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
			if (PlayerAppearance.DEFAULT.gender) {
				arg0.spriteId = anInt3851;
			} else {
				arg0.spriteId = anInt3502;
			}
		} else if (local16 == 325) {
			if (anInt3851 == -1) {
				anInt3502 = arg0.activeSpriteId;
				anInt3851 = arg0.spriteId;
			}
			if (PlayerAppearance.DEFAULT.gender) {
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
		anInt3325++;
		method3711();
		if (!arg1) {
			pushPlayers(true);
			pushNpcs(true);
			pushPlayers(false);
		}
		pushNpcs(false);
		if (!arg1) {
			updateSceneProjectiles();
		}
		updateSpotAnims();
		if (GlRenderer.enabled) {
			method2314(arg3, arg4, arg0, arg2, true);
			arg2 = anInt983;
			arg4 = anInt773;
			arg3 = anInt4055;
			arg0 = anInt5377;
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
			Camera.orbitCamera(Camera.cameraX, arg0, SceneGraph.getTileHeight(Player.plane, PlayerList.self.xFine, PlayerList.self.zFine) - 50, 6000 + (pitch * 3), local57, Camera.cameraZ, pitch);
		}
		local57 = Camera.renderY;
		pitch = Camera.renderX;
		@Pc(121) int cameraZ = Camera.renderZ;
		@Pc(123) int cameraPitch = Camera.cameraPitch;
		@Pc(125) int cameraYaw = Camera.cameraYaw;
		@Pc(127) int type;
		@Pc(171) int jitter;
		for (type = 0; type < 5; type++) {
			if (Camera.cameraModifierEnabled[type]) {
				jitter = (int) ((double) -Camera.cameraModifierJitter[type] + (double) (Camera.cameraModifierJitter[type] * 2 + 1) * Math.random() + Math.sin((double) Protocol.cameraModifierCycle[type] * ((double) Camera.cameraFrequency[type] / 100.0D)) * (double) Camera.cameraAmplitude[type]);
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
					Camera.renderY += jitter;
				}
				if (type == 0) {
					Camera.renderX += jitter;
				}
			}
		}
		method4302();
		if (GlRenderer.enabled) {
			GlRaster.setClip(arg2, arg4, arg2 + arg3, arg4 - -arg0);
			@Pc(248) float local248 = (float) Camera.cameraPitch * 0.17578125F;
			@Pc(253) float local253 = (float) Camera.cameraYaw * 0.17578125F;
			if (Camera.cameraType == 3) {
				local248 = Camera.aFloat15 * 360.0F / 6.2831855F;
				local253 = Camera.aFloat10 * 360.0F / 6.2831855F;
			}
			GlRenderer.method4171(arg2, arg4, arg3, arg0, arg3 / 2 + arg2, arg4 - -(arg0 / 2), local248, local253, anInt5029, anInt5029);
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
		@Pc(387) byte local387 = method4047() == 2 ? (byte) anInt3325 : 1;
		if (GlRenderer.enabled) {
			GlRenderer.restoreLighting();
			GlRenderer.setDepthTestEnabled(true);
			GlRenderer.setFogEnabled(true);
			if (client.gameState == 10) {
				jitter = FogManager.method2235(Protocol.sceneDelta, Camera.renderZ >> 10, Preferences.brightness, Camera.renderX >> 10);
			} else {
				jitter = FogManager.method2235(Protocol.sceneDelta, PlayerList.self.movementQueueZ[0] >> 3, Preferences.brightness, PlayerList.self.movementQueueX[0] >> 3);
			}
			LightingManager.method2394(client.loop, !Preferences.flickeringEffectsOn);
			GlRenderer.clearColorAndDepthBuffers(jitter);
			MaterialManager.method2731(Camera.cameraPitch, Camera.renderZ, Camera.renderY, Camera.renderX, Camera.cameraYaw);
			GlRenderer.anInt5323 = client.loop;
			SceneGraph.method2954(Camera.renderX, Camera.renderY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, aByteArrayArrayArray15, anIntArray205, anIntArray338, anIntArray518, anIntArray134, anIntArray476, Player.plane + 1, local387, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
			aBoolean299 = true;
			LightingManager.method2390();
			MaterialManager.method2731(0, 0, 0, 0, 0);
			client.audioLoop();
			method3858();
			drawOverheads(arg4, arg3, arg2, anInt5029, arg0, anInt5029);
			MiniMap.method4000(arg3, arg2, arg0, anInt5029, anInt5029, arg4);
		} else {
			SoftwareRaster.fillRect(arg2, arg4, arg3, arg0, 0);
			SceneGraph.method2954(Camera.renderX, Camera.renderY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, aByteArrayArrayArray15, anIntArray205, anIntArray338, anIntArray518, anIntArray134, anIntArray476, Player.plane + 1, local387, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
			client.audioLoop();
			method3858();
			drawOverheads(arg4, arg3, arg2, 256, arg0, 256);
			MiniMap.method4000(arg3, arg2, arg0, 256, 256, arg4);
		}
		((Js5TextureProvider) Rasterizer.textureProvider).method3239(Protocol.sceneDelta);
		Player.method2310(arg3, arg4, arg0, arg2);
		Camera.cameraPitch = cameraPitch;
		Camera.renderZ = cameraZ;
		Camera.renderY = local57;
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
			MiniMenu.addEntries(arg4, arg3, arg0, arg2, anInt1892, anInt3751);
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
				SoftwareRaster.method2504(arg0, arg1, arg2.anIntArray37, arg2.anIntArray45);
			}
		} else if (GlRenderer.enabled) {
			((GlSprite) Sprites.compass).renderRotatedTransparent(arg0, arg1, arg2.width, arg2.height, Sprites.compass.width / 2, Sprites.compass.height / 2, Camera.orbitCameraYaw, 256, (GlSprite) arg2.method489(false));
		} else {
			((SoftwareSprite) Sprites.compass).renderRotated(arg0, arg1, arg2.width, arg2.height, Sprites.compass.width / 2, Sprites.compass.height / 2, Camera.orbitCameraYaw, arg2.anIntArray37, arg2.anIntArray45);
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
			WorldMap.method4364(arg2, 0, WorldMap.width, 0, 0, WorldMap.length, arg1, 0);
			if (GlRenderer.enabled) {
				WorldMap.aClass3_Sub2_Sub1_2 = new GlSprite(local63);
			} else {
				WorldMap.aClass3_Sub2_Sub1_2 = local63;
			}
			if (GlRenderer.enabled) {
				SoftwareRaster.pixels = null;
			} else {
				SoftwareRaster.frameBuffer.makeTarget();
			}
		}
		WorldMap.aClass3_Sub2_Sub1_2.drawPixels(arg0, arg3);
		@Pc(147) int local147 = arg1 * anInt2884 / WorldMap.length + arg3;
		@Pc(153) int local153 = WorldMap.anInt1176 * arg1 / WorldMap.length;
		@Pc(161) int local161 = arg0 + arg2 * anInt2882 / WorldMap.width;
		@Pc(167) int local167 = arg2 * WorldMap.anInt2387 / WorldMap.width;
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
		for (@Pc(238) MapFunction local238 = (MapFunction) WorldMap.mapFunctions.head(); local238 != null; local238 = (MapFunction) WorldMap.mapFunctions.next()) {
			if (local238.id == WorldMap.anInt172) {
				@Pc(258) int local258 = arg3 + local238.z * arg1 / WorldMap.length;
				@Pc(267) int local267 = arg2 * local238.x / WorldMap.width + arg0;
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
		Sprites.scrollbars[0].renderTransparent(arg2, arg3);
		Sprites.scrollbars[1].renderTransparent(arg2, arg4 + arg3 - 16);
		@Pc(35) int local35 = arg4 * (arg4 - 32) / arg1;
		if (local35 < 8) {
			local35 = 8;
		}
		@Pc(54) int local54 = arg0 * (arg4 - local35 - 32) / (arg1 - arg4);
		if (!GlRenderer.enabled) {
			SoftwareRaster.fillRect(arg2, arg3 + 16, 16, arg4 - 32, anInt4306);
			SoftwareRaster.fillRect(arg2, local54 + arg3 + 16, 16, local35, anInt1704);
			SoftwareRaster.drawVerticalLine(arg2, local54 + arg3 + 16, local35, anInt4938);
			SoftwareRaster.drawVerticalLine(arg2 + 1, local54 + 16 + arg3, local35, anInt4938);
			SoftwareRaster.drawHorizontalLine(arg2, arg3 + local54 + 16, 16, anInt4938);
			SoftwareRaster.drawHorizontalLine(arg2, arg3 + local54 + 17, 16, anInt4938);
			SoftwareRaster.drawVerticalLine(arg2 + 15, local54 + 16 + arg3, local35, anInt671);
			SoftwareRaster.drawVerticalLine(arg2 + 14, arg3 - -17 - -local54, local35 - 1, anInt671);
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
			local9 = JString.concatenate(new JString[] { local9.substring(local21, 0), JString.aClass100_760, local9.substring(local21) });
		}
		if (local9.length() > 9) {
			return JString.concatenate(new JString[] { JString.aClass100_1043, local9.substring(local9.length() - 8, 0), LocalizedText.MILLION_SHORT, MiniMenu.OPEN_PARENTHESIS, local9, JString.aClass100_583 });
		} else if (local9.length() > 6) {
			return JString.concatenate(new JString[] { JString.aClass100_589, local9.substring(local9.length() - 4, 0), LocalizedText.THOUSAND_SHORT, MiniMenu.OPEN_PARENTHESIS, local9, JString.aClass100_583 });
		} else {
			return JString.concatenate(new JString[] { JString.aClass100_1101, local9, JString.aClass100_978 });
		}
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(BILclient!jl;)V")
	public static void run(@OriginalArg(1) int maxCycles, @OriginalArg(2) HookRequest request) {
		@Pc(4) Object[] listeners = request.arguments;
		@Pc(10) int sid = (Integer) listeners[0];
		@Pc(14) ClientScript clientScript = ClientScriptList.get(sid);
		if (clientScript == null) {
			return;
		}
		fp = 0;
		@Pc(26) int ssp = 0;
		@Pc(28) int isp = 0;
		@Pc(30) int pc = -1;
		@Pc(33) int[] intOperands = clientScript.intOperands;
		@Pc(36) int[] opcodes = clientScript.opcodes;
		@Pc(44) byte op = -1;
		@Pc(58) int cycles;
		try {
			intLocals = new int[clientScript.localIntCount];
			@Pc(50) int localIntIndex = 0;
			stringLocals = new JString[clientScript.localStringCount];
			@Pc(56) int localStringIndex = 0;
			@Pc(77) int id;
			@Pc(194) JString value;
			for (cycles = 1; cycles < listeners.length; cycles++) {
				if (listeners[cycles] instanceof Integer) {
					id = (Integer) listeners[cycles];
					if (id == -2147483647) { // 0
						id = request.mouseX;
					}
					if (id == -2147483646) { // 1
						id = request.mouseY;
					}
					if (id == -2147483645) { // 2
						id = request.source == null ? -1 : request.source.id;
					}
					if (id == -2147483644) { // 3
						id = request.op;
					}
					if (id == -2147483643) {
						id = request.source == null ? -1 : request.source.createdComponentId;
					}
					if (id == -2147483642) {
						id = request.target == null ? -1 : request.target.id;
					}
					if (id == -2147483641) {
						id = request.target == null ? -1 : request.target.createdComponentId;
					}
					if (id == -2147483640) {
						id = request.keyCode;
					}
					if (id == -2147483639) {
						id = request.keyChar;
					}
					intLocals[localIntIndex++] = id;
				} else if (listeners[cycles] instanceof JString) {
					value = (JString) listeners[cycles];
					if (value.strEquals(EVENT_OPBASE)) {
						value = request.opBase;
					}
					stringLocals[localStringIndex++] = value;
				}
			}
			cycles = 0;
			nextOp: while (true) {
				cycles++;
				if (maxCycles < cycles) {
					throw new RuntimeException("slow");
				}
				pc++;
				@Pc(226) int opcode = opcodes[pc];
				@Pc(803) int interfaceType;
				@Pc(652) int i;
				@Pc(809) int componentId;
				@Pc(609) JString chatTyped;
				if (opcode < 100) {
					// core language ops (not commands)

					if (opcode == 0) {
						// push_constant_int
						scriptIntValues[isp++] = intOperands[pc];
						continue;
					}
					if (opcode == 1) {
						// push_varp
						id = intOperands[pc];
						scriptIntValues[isp++] = VarpDomain.activeVarps[id];
						continue;
					}
					if (opcode == 2) {
						// pop_varp
						id = intOperands[pc];
						isp--;
						VarpDomain.setVarpClient(id, scriptIntValues[isp]);
						continue;
					}
					if (opcode == 3) {
						// push_constant_string
						scriptStringValues[ssp++] = clientScript.stringOperands[pc];
						continue;
					}
					if (opcode == 6) {
						// branch
						pc += intOperands[pc];
						continue;
					}
					if (opcode == 7) {
						// branch_not
						isp -= 2;
						if (scriptIntValues[isp] != scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 8) {
						// branch_equal
						isp -= 2;
						if (scriptIntValues[isp + 1] == scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 9) {
						// branch_equals
						isp -= 2;
						if (scriptIntValues[isp] < scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 10) {
						// branch_greater_than
						isp -= 2;
						if (scriptIntValues[isp + 1] < scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 21) {
						// return
						if (fp == 0) {
							return;
						}
						@Pc(423) GoSubFrame frame = callStack[--fp];
						clientScript = frame.script;
						intLocals = frame.localInts;
						opcodes = clientScript.opcodes;
						pc = frame.pc;
						stringLocals = frame.stringLocals;
						intOperands = clientScript.intOperands;
						continue;
					}
					if (opcode == 25) {
						// push_varbit
						id = intOperands[pc];
						scriptIntValues[isp++] = VarpDomain.getVarbitValue(id);
						continue;
					}
					if (opcode == 27) {
						// pop_varbit
						id = intOperands[pc];
						isp--;
						VarpDomain.setVarbitClient(id, scriptIntValues[isp]);
						continue;
					}
					if (opcode == 31) {
						// branch_less_than_or_equals
						isp -= 2;
						if (scriptIntValues[isp + 1] >= scriptIntValues[isp]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 32) {
						// branch_greater_than_or_equals
						isp -= 2;
						if (scriptIntValues[isp] >= scriptIntValues[isp + 1]) {
							pc += intOperands[pc];
						}
						continue;
					}
					if (opcode == 33) {
						// push_int_local
						scriptIntValues[isp++] = intLocals[intOperands[pc]];
						continue;
					}
					@Pc(555) int local555;
					if (opcode == 34) {
						// pop_int_local
						local555 = intOperands[pc];
						isp--;
						intLocals[local555] = scriptIntValues[isp];
						continue;
					}
					if (opcode == 35) {
						// push_string_local
						scriptStringValues[ssp++] = stringLocals[intOperands[pc]];
						continue;
					}
					if (opcode == 36) {
						// pop_string_local
						local555 = intOperands[pc];
						ssp--;
						stringLocals[local555] = scriptStringValues[ssp];
						continue;
					}
					if (opcode == 37) {
						// join_string
						id = intOperands[pc];
						ssp -= id;
						chatTyped = JString.join(ssp, id, scriptStringValues);
						scriptStringValues[ssp++] = chatTyped;
						continue;
					}
					if (opcode == 38) {
						// pop_int_discard
						isp--;
						continue;
					}
					if (opcode == 39) {
						// pop_string_discard
						ssp--;
						continue;
					}
					if (opcode == 40) {
						// gosub_with_params
						id = intOperands[pc];
						@Pc(642) ClientScript invokeScript = ClientScriptList.get(id);
						@Pc(646) int[] invokeScriptIntLocals = new int[invokeScript.localIntCount];
						@Pc(650) JString[] invokeScriptStringLocals = new JString[invokeScript.localStringCount];
						for (i = 0; i < invokeScript.intArgs; i++) {
							invokeScriptIntLocals[i] = scriptIntValues[i + isp - invokeScript.intArgs];
						}
						for (i = 0; i < invokeScript.stringArgs; i++) {
							invokeScriptStringLocals[i] = scriptStringValues[i + ssp - invokeScript.stringArgs];
						}
						isp -= invokeScript.intArgs;
						ssp -= invokeScript.stringArgs;
						@Pc(705) GoSubFrame frame = new GoSubFrame();
						frame.stringLocals = stringLocals;
						frame.localInts = intLocals;
						frame.pc = pc;
						frame.script = clientScript;
						if (fp >= callStack.length) {
							throw new RuntimeException();
						}
						clientScript = invokeScript;
						pc = -1;
						callStack[fp++] = frame;
						intLocals = invokeScriptIntLocals;
						intOperands = invokeScript.intOperands;
						opcodes = invokeScript.opcodes;
						stringLocals = invokeScriptStringLocals;
						continue;
					}
					if (opcode == 42) {
						// push_varc_int
						scriptIntValues[isp++] = VarcDomain.varcs[intOperands[pc]];
						continue;
					}
					if (opcode == 43) {
						// pop_varc_int
						id = intOperands[pc];
						isp--;
						VarcDomain.varcs[id] = scriptIntValues[isp];
						DelayedStateChange.setVarcClient(id);
						continue;
					}
					if (opcode == 44) {
						id = intOperands[pc] >> 16;
						isp--;
						interfaceType = scriptIntValues[isp];
						componentId = intOperands[pc] & 0xFFFF;
						if (interfaceType >= 0 && interfaceType <= 5000) {
							anIntArray140[id] = interfaceType;
							@Pc(828) byte defaultValue = -1;
							if (componentId == 105) {
								defaultValue = 0;
							}
							i = 0;
							while (true) {
								if (interfaceType <= i) {
									continue nextOp;
								}
								anIntArrayArray33[id][i] = defaultValue;
								i++;
							}
						}
						throw new RuntimeException();
					}
					if (opcode == 45) {
						id = intOperands[pc];
						isp--;
						componentId = scriptIntValues[isp];
						if (componentId >= 0 && componentId < anIntArray140[id]) {
							scriptIntValues[isp++] = anIntArrayArray33[id][componentId];
							continue;
						}
						throw new RuntimeException();
					}
					if (opcode == 46) {
						id = intOperands[pc];
						isp -= 2;
						componentId = scriptIntValues[isp];
						if (componentId >= 0 && componentId < anIntArray140[id]) {
							anIntArrayArray33[id][componentId] = scriptIntValues[isp + 1];
							continue;
						}
						throw new RuntimeException();
					}
					if (opcode == 47) {
						value = VarcDomain.varcstrs[intOperands[pc]];
						if (value == null) {
							value = VarpDomain.NULL;
						}
						scriptStringValues[ssp++] = value;
						continue;
					}
					if (opcode == 48) {
						id = intOperands[pc];
						ssp--;
						VarcDomain.varcstrs[id] = scriptStringValues[ssp];
						DelayedStateChange.setVarcstrClient(id);
						continue;
					}
					if (opcode == 51) {
						@Pc(992) HashTable table = clientScript.switchTables[intOperands[pc]];
						isp--;
						@Pc(1002) IntWrapper node = (IntWrapper) table.get((long) scriptIntValues[isp]);
						if (node != null) {
							pc += node.value;
						}
						continue;
					}
				}
				@Pc(1020) boolean secondary;
				if (intOperands[pc] == 1) {
					secondary = true;
				} else {
					secondary = false;
				}
				@Pc(1182) Component createdComponent;
				@Pc(1052) int j;
				@Pc(1063) Component component;
				@Pc(1087) int childId;
				@Pc(1256) Component local1256;
				if (opcode < 300) {
					if (opcode == 100) {
						// cc_create
						isp -= 3;
						componentId = scriptIntValues[isp];
						interfaceType = scriptIntValues[isp + 1];
						j = scriptIntValues[isp + 2];
						if (interfaceType != 0) {
							component = InterfaceList.getComponent(componentId);
							if (component.createdComponents == null) {
								component.createdComponents = new Component[j + 1];
							}
							if (j >= component.createdComponents.length) {
								@Pc(1085) Component[] createdComponents = new Component[j + 1];
								for (childId = 0; childId < component.createdComponents.length; childId++) {
									createdComponents[childId] = component.createdComponents[childId];
								}
								component.createdComponents = createdComponents;
							}
							if (j > 0 && component.createdComponents[j - 1] == null) {
								throw new RuntimeException("Gap at:" + (j - 1));
							}
							@Pc(1137) Component local1137 = new Component();
							local1137.if3 = true;
							local1137.createdComponentId = j;
							local1137.overlayer = local1137.id = component.id;
							local1137.type = interfaceType;
							component.createdComponents[j] = local1137;
							if (secondary) {
								secondaryActiveComponent = local1137;
							} else {
								primaryActiveComponent = local1137;
							}
							InterfaceList.redraw(component);
							continue;
						}
						throw new RuntimeException();
					}
					@Pc(1204) Component component2;
					if (opcode == 101) {
						// cc_delete
						createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
						if (createdComponent.createdComponentId == -1) {
							if (!secondary) {
								throw new RuntimeException("Tried to cc_delete static active-component!");
							}
							throw new RuntimeException("Tried to .cc_delete static .active-component!");
						}
						component2 = InterfaceList.getComponent(createdComponent.id);
						component2.createdComponents[createdComponent.createdComponentId] = null;
						InterfaceList.redraw(component2);
						continue;
					}
					if (opcode == 102) {
						// cc_deleteall
						isp--;
						createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
						createdComponent.createdComponents = null;
						InterfaceList.redraw(createdComponent);
						continue;
					}
					if (opcode == 200) {
						isp -= 2;
						componentId = scriptIntValues[isp];
						interfaceType = scriptIntValues[isp + 1];
						local1256 = InterfaceList.getCreatedComponent(componentId, interfaceType);
						if (local1256 != null && interfaceType != -1) {
							scriptIntValues[isp++] = 1;
							if (secondary) {
								secondaryActiveComponent = local1256;
							} else {
								primaryActiveComponent = local1256;
							}
							continue;
						}
						scriptIntValues[isp++] = 0;
						continue;
					}
					if (opcode == 201) {
						isp--;
						componentId = scriptIntValues[isp];
						component2 = InterfaceList.getComponent(componentId);
						if (component2 == null) {
							scriptIntValues[isp++] = 0;
						} else {
							scriptIntValues[isp++] = 1;
							if (secondary) {
								secondaryActiveComponent = component2;
							} else {
								primaryActiveComponent = component2;
							}
						}
						continue;
					}
				} else {
					@Pc(12388) boolean local12388;
					if (opcode < 500) {
						if (opcode == 403) {
							isp -= 2;
							interfaceType = scriptIntValues[isp + 1];
							componentId = scriptIntValues[isp];
							for (j = 0; j < PlayerAppearance.MALE_FEATURES.length; j++) {
								if (componentId == PlayerAppearance.MALE_FEATURES[j]) {
									PlayerList.self.appearance.setIdentikit(j, interfaceType);
									continue nextOp;
								}
							}
							j = 0;
							while (true) {
								if (j >= PlayerAppearance.FEMALE_FEATURES.length) {
									continue nextOp;
								}
								if (componentId == PlayerAppearance.FEMALE_FEATURES[j]) {
									PlayerList.self.appearance.setIdentikit(j, interfaceType);
									continue nextOp;
								}
								j++;
							}
						}
						if (opcode == 404) {
							isp -= 2;
							componentId = scriptIntValues[isp];
							interfaceType = scriptIntValues[isp + 1];
							PlayerList.self.appearance.setColor(componentId, interfaceType);
							continue;
						}
						if (opcode == 410) {
							isp--;
							local12388 = scriptIntValues[isp] != 0;
							PlayerList.self.appearance.setGender(local12388);
							continue;
						}
					} else {
						@Pc(1552) boolean local1552;
						if ((opcode < 1000 || opcode >= 1100) && (opcode < 2000 || opcode >= 2100)) {
							@Pc(2522) JString chatTypedLowercase;
							if (opcode >= 1100 && opcode < 1200 || !(opcode < 2100 || opcode >= 2200)) {
								if (opcode < 2000) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								} else {
									opcode -= 1000;
									isp--;
									createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
								}
								if (opcode == 1100) {
									// setscrollpos
									isp -= 2;
									createdComponent.scrollX = scriptIntValues[isp];
									if (createdComponent.scrollX > createdComponent.scrollMaxH - createdComponent.width) {
										createdComponent.scrollX = createdComponent.scrollMaxH - createdComponent.width;
									}
									if (createdComponent.scrollX < 0) {
										createdComponent.scrollX = 0;
									}
									createdComponent.scrollY = scriptIntValues[isp + 1];
									if (createdComponent.scrollY > createdComponent.scrollMaxV - createdComponent.height) {
										createdComponent.scrollY = createdComponent.scrollMaxV - createdComponent.height;
									}
									if (createdComponent.scrollY < 0) {
										createdComponent.scrollY = 0;
									}
									InterfaceList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentScrollPositionClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1101) {
									// setcolor
									isp--;
									createdComponent.color = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentColorClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1102) {
									// setfill
									isp--;
									createdComponent.filled = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1103) {
									// settrans
									isp--;
									createdComponent.alpha = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1104) {
									// setlinewid
									isp--;
									createdComponent.lineWidth = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1105) {
									// setgraphic
									isp--;
									createdComponent.spriteId = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1106) {
									isp--;
									createdComponent.angle2d = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1107) {
									// settiling
									isp--;
									createdComponent.spriteTiling = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1108) {
									// setmodel
									createdComponent.modelType = 1;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1109) {
									// setmodelangle
									isp -= 6;
									createdComponent.modelXOffset = scriptIntValues[isp];
									createdComponent.modelZOffset = scriptIntValues[isp + 1];
									createdComponent.modelXAngle = scriptIntValues[isp + 2];
									createdComponent.modelYAngle = scriptIntValues[isp + 3];
									createdComponent.modelYOffset = scriptIntValues[isp + 4];
									createdComponent.modelZoom = scriptIntValues[isp + 5];
									InterfaceList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAngleClient(createdComponent.id);
										DelayedStateChange.setComponentModelOffsetClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1110) {
									// setmodelanim
									isp--;
									interfaceType = scriptIntValues[isp];
									if (createdComponent.modelSeqId != interfaceType) {
										createdComponent.modelSeqId = interfaceType;
										createdComponent.anInt510 = 0;
										createdComponent.anInt500 = 0;
										createdComponent.anInt496 = 1;
										InterfaceList.redraw(createdComponent);
									}
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAnimClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1111) {
									// setmodelorthog
									isp--;
									createdComponent.modelOrtho = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1112) {
									// settext
									ssp--;
									chatTypedLowercase = scriptStringValues[ssp];
									if (!chatTypedLowercase.strEquals(createdComponent.text)) {
										createdComponent.text = chatTypedLowercase;
										InterfaceList.redraw(createdComponent);
									}
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentTextClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1113) {
									// settextfont
									isp--;
									createdComponent.fontId = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1114) {
									// settextalign
									isp -= 3;
									createdComponent.halign = scriptIntValues[isp];
									createdComponent.valign = scriptIntValues[isp + 1];
									createdComponent.vpadding = scriptIntValues[isp + 2];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1115) {
									// settextshadow
									isp--;
									createdComponent.shadowed = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1116) {
									isp--;
									createdComponent.outlineThickness = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1117) {
									isp--;
									createdComponent.shadowColor = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1118) {
									isp--;
									createdComponent.vFlip = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1119) {
									isp--;
									createdComponent.hFlip = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1120) {
									isp -= 2;
									createdComponent.scrollMaxH = scriptIntValues[isp];
									createdComponent.scrollMaxV = scriptIntValues[isp + 1];
									InterfaceList.redraw(createdComponent);
									if (createdComponent.type == 0) {
										InterfaceList.method531(createdComponent, false);
									}
									continue;
								}
								if (opcode == 1121) {
									isp -= 2;
									createdComponent.aShort11 = (short) scriptIntValues[isp];
									createdComponent.aShort10 = (short) scriptIntValues[isp + 1];
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1122) {
									isp--;
									createdComponent.hasAlpha = scriptIntValues[isp] == 1;
									InterfaceList.redraw(createdComponent);
									continue;
								}
								if (opcode == 1123) {
									isp--;
									createdComponent.modelZoom = scriptIntValues[isp];
									InterfaceList.redraw(createdComponent);
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelAngleClient(createdComponent.id);
									}
									continue;
								}
							} else if (opcode >= 1200 && opcode < 1300 || !(opcode < 2200 || opcode >= 2300)) {
								if (opcode < 2000) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								} else {
									isp--;
									createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
									opcode -= 1000;
								}
								InterfaceList.redraw(createdComponent);
								if (opcode == 1200 || opcode == 1205) {
									isp -= 2;
									j = scriptIntValues[isp + 1];
									interfaceType = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentObjClient(createdComponent.id);
										DelayedStateChange.setComponentModelAngleClient(createdComponent.id);
										DelayedStateChange.setComponentModelOffsetClient(createdComponent.id);
									}
									if (interfaceType == -1) {
										createdComponent.modelId = -1;
										createdComponent.modelType = 1;
										createdComponent.objId = -1;
									} else {
										createdComponent.objId = interfaceType;
										createdComponent.objCount = j;
										@Pc(13416) ObjType local13416 = ObjTypeList.get(interfaceType);
										createdComponent.modelYOffset = local13416.zAngle2D;
										createdComponent.modelXOffset = local13416.xof2d;
										createdComponent.modelXAngle = local13416.xan2d;
										createdComponent.modelZOffset = local13416.yof2d;
										createdComponent.modelYAngle = local13416.yan2d;
										createdComponent.modelZoom = local13416.zoom2d;
										if (createdComponent.anInt451 > 0) {
											createdComponent.modelZoom = createdComponent.modelZoom * 32 / createdComponent.anInt451;
										} else if (createdComponent.baseWidth > 0) {
											createdComponent.modelZoom = createdComponent.modelZoom * 32 / createdComponent.baseWidth;
										}
										if (opcode == 1205) {
											createdComponent.objDrawText = false;
										} else {
											createdComponent.objDrawText = true;
										}
									}
									continue;
								}
								if (opcode == 1201) {
									// setnpchead
									createdComponent.modelType = 2;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1202) {
									// setplayerhead_self
									createdComponent.modelType = 3;
									createdComponent.modelId = PlayerList.self.appearance.getHeadModelId();
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1203) {
									// setnpcmodel
									createdComponent.modelType = 6;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
								if (opcode == 1204) {
									createdComponent.modelType = 5;
									isp--;
									createdComponent.modelId = scriptIntValues[isp];
									if (createdComponent.createdComponentId == -1) {
										DelayedStateChange.setComponentModelClient(createdComponent.id);
									}
									continue;
								}
							} else if (opcode >= 1300 && opcode < 1400 || opcode >= 2300 && opcode < 2400) {
								if (opcode >= 2000) {
									// if_
									isp--;
									createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
									opcode -= 1000;
								} else {
									// cc_
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
								}
								if (opcode == 1300) {
									isp--;
									interfaceType = scriptIntValues[isp] - 1;
									if (interfaceType >= 0 && interfaceType <= 9) {
										ssp--;
										createdComponent.method480(scriptStringValues[ssp], interfaceType);
										continue;
									}
									ssp--;
									continue;
								}
								if (opcode == 1301) {
									isp -= 2;
									j = scriptIntValues[isp + 1];
									interfaceType = scriptIntValues[isp];
									createdComponent.aClass13_5 = InterfaceList.getCreatedComponent(interfaceType, j);
									continue;
								}
								if (opcode == 1302) {
									isp--;
									createdComponent.dragRenderBehavior = scriptIntValues[isp] == 1;
									continue;
								}
								if (opcode == 1303) {
									isp--;
									createdComponent.dragDeadzone = scriptIntValues[isp];
									continue;
								}
								if (opcode == 1304) {
									isp--;
									createdComponent.dragDeadtime = scriptIntValues[isp];
									continue;
								}
								if (opcode == 1305) {
									ssp--;
									createdComponent.optionBase = scriptStringValues[ssp];
									continue;
								}
								if (opcode == 1306) {
									ssp--;
									createdComponent.optionCircumfix = scriptStringValues[ssp];
									continue;
								}
								if (opcode == 1307) {
									createdComponent.ops = null;
									continue;
								}
								if (opcode == 1308) {
									isp--;
									createdComponent.anInt484 = scriptIntValues[isp];
									isp--;
									createdComponent.anInt499 = scriptIntValues[isp];
									continue;
								}
								if (opcode == 1309) {
									isp--;
									interfaceType = scriptIntValues[isp];
									isp--;
									j = scriptIntValues[isp];
									if (j >= 1 && j <= 10) {
										createdComponent.method477(j - 1, interfaceType);
									}
									continue;
								}
							} else {
								@Pc(4859) int start;
								if (opcode >= 1400 && opcode < 1500 || opcode >= 2400 && opcode < 2500) {
									if (opcode < 2000) {
										// if_
										createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									} else {
										// cc_
										opcode -= 1000;
										isp--;
										createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
									}
									@Pc(12937) int[] local12937 = null;
									ssp--;
									chatTypedLowercase = scriptStringValues[ssp];
									if (chatTypedLowercase.length() > 0 && chatTypedLowercase.charAt(chatTypedLowercase.length() - 1) == 89) {
										isp--;
										i = scriptIntValues[isp];
										if (i > 0) {
											local12937 = new int[i];
											while (i-- > 0) {
												isp--;
												local12937[i] = scriptIntValues[isp];
											}
										}
										chatTypedLowercase = chatTypedLowercase.substring(chatTypedLowercase.length() - 1, 0);
									}
									@Pc(13000) Object[] arguments = new Object[chatTypedLowercase.length() + 1];
									for (start = arguments.length - 1; start >= 1; start--) {
										if (chatTypedLowercase.charAt(start - 1) == 115) {
											ssp--;
											arguments[start] = scriptStringValues[ssp];
										} else {
											isp--;
											arguments[start] = Integer.valueOf(scriptIntValues[isp]);
										}
									}
									isp--;
									start = scriptIntValues[isp];
									if (start == -1) {
										arguments = null;
									} else {
										arguments[0] = Integer.valueOf(start);
									}
									createdComponent.aBoolean25 = true;
									if (opcode == 1400) {
										createdComponent.onClickRepeat = arguments;
									} else if (opcode == 1401) {
										createdComponent.onHold = arguments;
									} else if (opcode == 1402) {
										createdComponent.onRelease = arguments;
									} else if (opcode == 1403) {
										createdComponent.onMouseOver = arguments;
									} else if (opcode == 1404) {
										createdComponent.onMouseLeave = arguments;
									} else if (opcode == 1405) {
										createdComponent.onDragStart = arguments;
									} else if (opcode == 1406) {
										createdComponent.onUseWith = arguments;
									} else if (opcode == 1407) {
										createdComponent.varpTriggers = local12937;
										createdComponent.onVarpTransmit = arguments;
									} else if (opcode == 1408) {
										createdComponent.onTimer = arguments;
									} else if (opcode == 1409) {
										createdComponent.onOptionClick = arguments;
									} else if (opcode == 1410) {
										createdComponent.onDragRelease = arguments;
									} else if (opcode == 1411) {
										createdComponent.onDrag = arguments;
									} else if (opcode == 1412) {
										createdComponent.onMouseRepeat = arguments;
									} else if (opcode == 1414) {
										createdComponent.inventoryTriggers = local12937;
										createdComponent.onInvTransmit = arguments;
									} else if (opcode == 1415) {
										createdComponent.statTriggers = local12937;
										createdComponent.onStatTransmit = arguments;
									} else if (opcode == 1416) {
										createdComponent.onUse = arguments;
									} else if (opcode == 1417) {
										createdComponent.onScroll = arguments;
									} else if (opcode == 1418) {
										createdComponent.onMsg = arguments;
									} else if (opcode == 1419) {
										createdComponent.onKey = arguments;
									} else if (opcode == 1420) {
										createdComponent.onFriendTransmit = arguments;
									} else if (opcode == 1421) {
										createdComponent.onClanTransmit = arguments;
									} else if (opcode == 1422) {
										createdComponent.onMiscTransmit = arguments;
									} else if (opcode == 1423) {
										createdComponent.onDialogAbort = arguments;
									} else if (opcode == 1424) {
										createdComponent.onWidgetsOpenClose = arguments;
									} else if (opcode == 1425) {
										createdComponent.onStockTransmit = arguments;
									} else if (opcode == 1426) {
										createdComponent.onMinimapUnlock = arguments;
									} else if (opcode == 1427) {
										createdComponent.onResize = arguments;
									} else if (opcode == 1428) {
										createdComponent.onVarcTransmit = arguments;
										createdComponent.varcTriggers = local12937;
									} else if (opcode == 1429) {
										createdComponent.varcstrTriggers = local12937;
										createdComponent.onVarcstrTransmit = arguments;
									}
									continue;
								}
								if (opcode < 1600) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1500) {
										// cc_getx
										scriptIntValues[isp++] = createdComponent.x;
										continue;
									}
									if (opcode == 1501) {
										// cc_gety
										scriptIntValues[isp++] = createdComponent.y;
										continue;
									}
									if (opcode == 1502) {
										// cc_getwidth
										scriptIntValues[isp++] = createdComponent.width;
										continue;
									}
									if (opcode == 1503) {
										// cc_getheight
										scriptIntValues[isp++] = createdComponent.height;
										continue;
									}
									if (opcode == 1504) {
										// cc_gethide
										scriptIntValues[isp++] = createdComponent.hidden ? 1 : 0;
										continue;
									}
									if (opcode == 1505) {
										// set_getlayer
										scriptIntValues[isp++] = createdComponent.overlayer;
										continue;
									}
								} else if (opcode < 1700) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1600) {
										// cc_getscrollx
										scriptIntValues[isp++] = createdComponent.scrollX;
										continue;
									}
									if (opcode == 1601) {
										// cc_getscrolly
										scriptIntValues[isp++] = createdComponent.scrollY;
										continue;
									}
									if (opcode == 1602) {
										scriptStringValues[ssp++] = createdComponent.text;
										continue;
									}
									if (opcode == 1603) {
										scriptIntValues[isp++] = createdComponent.scrollMaxH;
										continue;
									}
									if (opcode == 1604) {
										scriptIntValues[isp++] = createdComponent.scrollMaxV;
										continue;
									}
									if (opcode == 1605) {
										scriptIntValues[isp++] = createdComponent.modelZoom;
										continue;
									}
									if (opcode == 1606) {
										scriptIntValues[isp++] = createdComponent.modelXAngle;
										continue;
									}
									if (opcode == 1607) {
										scriptIntValues[isp++] = createdComponent.modelYOffset;
										continue;
									}
									if (opcode == 1608) {
										scriptIntValues[isp++] = createdComponent.modelYAngle;
										continue;
									}
									if (opcode == 1609) {
										scriptIntValues[isp++] = createdComponent.alpha;
										continue;
									}
									if (opcode == 1610) {
										scriptIntValues[isp++] = createdComponent.modelXOffset;
										continue;
									}
									if (opcode == 1611) {
										scriptIntValues[isp++] = createdComponent.modelZOffset;
										continue;
									}
									if (opcode == 1612) {
										scriptIntValues[isp++] = createdComponent.spriteId;
										continue;
									}
								} else if (opcode < 1800) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1700) {
										scriptIntValues[isp++] = createdComponent.objId;
										continue;
									}
									if (opcode == 1701) {
										if (createdComponent.objId == -1) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = createdComponent.objCount;
										}
										continue;
									}
									if (opcode == 1702) {
										scriptIntValues[isp++] = createdComponent.createdComponentId;
										continue;
									}
								} else if (opcode < 1900) {
									createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
									if (opcode == 1800) {
										scriptIntValues[isp++] = InterfaceList.getServerActiveProperties(createdComponent).getTargetMask();
										continue;
									}
									if (opcode == 1801) {
										isp--;
										interfaceType = scriptIntValues[isp];
										interfaceType--;
										if (createdComponent.ops != null && interfaceType < createdComponent.ops.length && createdComponent.ops[interfaceType] != null) {
											scriptStringValues[ssp++] = createdComponent.ops[interfaceType];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 1802) {
										if (createdComponent.optionBase == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = createdComponent.optionBase;
										}
										continue;
									}
								} else if (opcode < 2600) {
									isp--;
									createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
									if (opcode == 2500) {
										// if_getx
										scriptIntValues[isp++] = createdComponent.x;
										continue;
									}
									if (opcode == 2501) {
										// if_gety
										scriptIntValues[isp++] = createdComponent.y;
										continue;
									}
									if (opcode == 2502) {
										// if_getwidth
										scriptIntValues[isp++] = createdComponent.width;
										continue;
									}
									if (opcode == 2503) {
										// if_getheight
										scriptIntValues[isp++] = createdComponent.height;
										continue;
									}
									if (opcode == 2504) {
										// if_gethide
										scriptIntValues[isp++] = createdComponent.hidden ? 1 : 0;
										continue;
									}
									if (opcode == 2505) {
										// if_getlayer
										scriptIntValues[isp++] = createdComponent.overlayer;
										continue;
									}
								} else if (opcode < 2700) {
									isp--;
									createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
									if (opcode == 2600) {
										// if_getscrollx
										scriptIntValues[isp++] = createdComponent.scrollX;
										continue;
									}
									if (opcode == 2601) {
										// if_getscrolly
										scriptIntValues[isp++] = createdComponent.scrollY;
										continue;
									}
									if (opcode == 2602) {
										scriptStringValues[ssp++] = createdComponent.text;
										continue;
									}
									if (opcode == 2603) {
										scriptIntValues[isp++] = createdComponent.scrollMaxH;
										continue;
									}
									if (opcode == 2604) {
										scriptIntValues[isp++] = createdComponent.scrollMaxV;
										continue;
									}
									if (opcode == 2605) {
										scriptIntValues[isp++] = createdComponent.modelZoom;
										continue;
									}
									if (opcode == 2606) {
										scriptIntValues[isp++] = createdComponent.modelXAngle;
										continue;
									}
									if (opcode == 2607) {
										scriptIntValues[isp++] = createdComponent.modelYOffset;
										continue;
									}
									if (opcode == 2608) {
										scriptIntValues[isp++] = createdComponent.modelYAngle;
										continue;
									}
									if (opcode == 2609) {
										scriptIntValues[isp++] = createdComponent.alpha;
										continue;
									}
									if (opcode == 2610) {
										scriptIntValues[isp++] = createdComponent.modelXOffset;
										continue;
									}
									if (opcode == 2611) {
										scriptIntValues[isp++] = createdComponent.modelZOffset;
										continue;
									}
									if (opcode == 2612) {
										scriptIntValues[isp++] = createdComponent.spriteId;
										continue;
									}
								} else if (opcode < 2800) {
									if (opcode == 2700) {
										isp--;
										createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
										scriptIntValues[isp++] = createdComponent.objId;
										continue;
									}
									if (opcode == 2701) {
										isp--;
										createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
										if (createdComponent.objId == -1) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = createdComponent.objCount;
										}
										continue;
									}
									if (opcode == 2702) {
										isp--;
										componentId = scriptIntValues[isp];
										@Pc(12566) SubInterface subInterface = (SubInterface) InterfaceList.openInterfaces.get((long) componentId);
										if (subInterface == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = 1;
										}
										continue;
									}
									if (opcode == 2703) {
										isp--;
										createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
										if (createdComponent.createdComponents == null) {
											scriptIntValues[isp++] = 0;
											continue;
										}
										interfaceType = createdComponent.createdComponents.length;
										for (j = 0; j < createdComponent.createdComponents.length; j++) {
											if (createdComponent.createdComponents[j] == null) {
												interfaceType = j;
												break;
											}
										}
										scriptIntValues[isp++] = interfaceType;
										continue;
									}
									if (opcode == 2704 || opcode == 2705) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										@Pc(12663) SubInterface subInterface = (SubInterface) InterfaceList.openInterfaces.get((long) componentId);
										if (subInterface != null && subInterface.interfaceId == interfaceType) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
								} else if (opcode < 2900) {
									isp--;
									createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
									if (opcode == 2800) {
										scriptIntValues[isp++] = InterfaceList.getServerActiveProperties(createdComponent).getTargetMask();
										continue;
									}
									if (opcode == 2801) {
										isp--;
										interfaceType = scriptIntValues[isp];
										interfaceType--;
										if (createdComponent.ops != null && createdComponent.ops.length > interfaceType && createdComponent.ops[interfaceType] != null) {
											scriptStringValues[ssp++] = createdComponent.ops[interfaceType];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 2802) {
										if (createdComponent.optionBase == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = createdComponent.optionBase;
										}
										continue;
									}
								} else if (opcode < 3200) {
									if (opcode == 3100) {
										// mes
										ssp--;
										chatTyped = scriptStringValues[ssp];
										Chat.addMessage(EMPTY_STRING, 0, chatTyped);
										continue;
									}
									if (opcode == 3101) {
										// anim
										isp -= 2;
										Player.animate(scriptIntValues[isp + 1], scriptIntValues[isp], PlayerList.self);
										continue;
									}
									if (opcode == 3103) {
										InterfaceList.closeModal();
										continue;
									}
									if (opcode == 3104) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										interfaceType = 0;
										if (chatTyped.isInt()) {
											interfaceType = chatTyped.parseInt();
										}
										Protocol.outboundBuffer.pIsaac1(23);
										Protocol.outboundBuffer.p4(interfaceType);
										continue;
									}
									if (opcode == 3105) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										Protocol.outboundBuffer.pIsaac1(244);
										Protocol.outboundBuffer.p8(chatTyped.encode37());
										continue;
									}
									if (opcode == 3106) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										Protocol.outboundBuffer.pIsaac1(65);
										Protocol.outboundBuffer.p1(chatTyped.length() + 1);
										Protocol.outboundBuffer.pjstr(chatTyped);
										continue;
									}
									if (opcode == 3107) {
										isp--;
										componentId = scriptIntValues[isp];
										ssp--;
										chatTypedLowercase = scriptStringValues[ssp];
										ClientProt.clickPlayerOption(componentId, chatTypedLowercase);
										continue;
									}
									if (opcode == 3108) {
										isp -= 3;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										j = scriptIntValues[isp + 2];
										component = InterfaceList.getComponent(j);
										method1015(interfaceType, componentId, component);
										continue;
									}
									if (opcode == 3109) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										local1256 = secondary ? secondaryActiveComponent : primaryActiveComponent;
										interfaceType = scriptIntValues[isp + 1];
										method1015(interfaceType, componentId, local1256);
										continue;
									}
									if (opcode == 3110) {
										isp--;
										componentId = scriptIntValues[isp];
										Protocol.outboundBuffer.pIsaac1(111);
										Protocol.outboundBuffer.p2(componentId);
										continue;
									}
								} else if (opcode < 3300) {
									if (opcode == 3200) {
										// sound_synth
										isp -= 3;
										SoundPlayer.play(scriptIntValues[isp + 1], scriptIntValues[isp], scriptIntValues[isp + 2]);
										continue;
									}
									if (opcode == 3201) {
										// sound_song
										isp--;
										MusicPlayer.playSong(scriptIntValues[isp]);
										continue;
									}
									if (opcode == 3202) {
										// sound_jingle
										isp -= 2;
										MusicPlayer.playJingle(scriptIntValues[isp + 1], scriptIntValues[isp]);
										continue;
									}
								} else if (opcode < 3400) {
									if (opcode == 3300) {
										// clientclock
										scriptIntValues[isp++] = client.loop;
										continue;
									}
									if (opcode == 3301) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemType(componentId, interfaceType);
										continue;
									}
									if (opcode == 3302) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getItemCount(componentId, interfaceType);
										continue;
									}
									if (opcode == 3303) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getSlotTotal(componentId, interfaceType);
										continue;
									}
									if (opcode == 3304) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = InvTypeList.get(componentId).size;
										continue;
									}
									if (opcode == 3305) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.boostedLevels[componentId];
										continue;
									}
									if (opcode == 3306) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.baseLevels[componentId];
										continue;
									}
									if (opcode == 3307) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = PlayerSkillXpTable.experience[componentId];
										continue;
									}
									if (opcode == 3308) {
										componentId = Player.plane;
										interfaceType = Camera.originX + (PlayerList.self.xFine >> 7);
										j = (PlayerList.self.zFine >> 7) + Camera.originZ;
										scriptIntValues[isp++] = (componentId << 28) - (-(interfaceType << 14) - j);
										continue;
									}
									if (opcode == 3309) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId >> 14 & 0x3FFF;
										continue;
									}
									if (opcode == 3310) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId >> 28;
										continue;
									}
									if (opcode == 3311) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId & 0x3FFF;
										continue;
									}
									if (opcode == 3312) {
										scriptIntValues[isp++] = LoginManager.membersWorld ? 1 : 0;
										continue;
									}
									if (opcode == 3313) {
										isp -= 2;
										componentId = scriptIntValues[isp] + 32768;
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemType(componentId, interfaceType);
										continue;
									}
									if (opcode == 3314) {
										isp -= 2;
										componentId = scriptIntValues[isp] + 32768;
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getItemCount(componentId, interfaceType);
										continue;
									}
									if (opcode == 3315) {
										isp -= 2;
										componentId = scriptIntValues[isp] + 32768;
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getSlotTotal(componentId, interfaceType);
										continue;
									}
									if (opcode == 3316) {
										if (LoginManager.staffModLevel < 2) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = LoginManager.staffModLevel;
										}
										continue;
									}
									if (opcode == 3317) {
										scriptIntValues[isp++] = Player.systemUpdateTimer;
										continue;
									}
									if (opcode == 3318) {
										scriptIntValues[isp++] = Player.worldId;
										continue;
									}
									if (opcode == 3321) {
										scriptIntValues[isp++] = Player.runEnergy;
										continue;
									}
									if (opcode == 3322) {
										scriptIntValues[isp++] = Player.weightCarried;
										continue;
									}
									if (opcode == 3323) {
										if (LoginManager.playerModLevel >= 5 && LoginManager.playerModLevel <= 9) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3324) {
										if (LoginManager.playerModLevel >= 5 && LoginManager.playerModLevel <= 9) {
											scriptIntValues[isp++] = LoginManager.playerModLevel;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3325) {
										scriptIntValues[isp++] = LoginManager.playerMember ? 1 : 0;
										continue;
									}
									if (opcode == 3326) {
										scriptIntValues[isp++] = PlayerList.self.combatLevel;
										continue;
									}
									if (opcode == 3327) {
										scriptIntValues[isp++] = PlayerList.self.appearance.gender ? 1 : 0;
										continue;
									}
									if (opcode == 3328) {
										scriptIntValues[isp++] = LoginManager.playerUnderage && !LoginManager.parentalChatConsent ? 1 : 0;
										continue;
									}
									if (opcode == 3329) {
										scriptIntValues[isp++] = LoginManager.worldQuickChat ? 1 : 0;
										continue;
									}
									if (opcode == 3330) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getFreeSpace(componentId);
										continue;
									}
									if (opcode == 3331) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = Inv.getTotalParam(false, componentId, interfaceType);
										continue;
									}
									if (opcode == 3332) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = Inv.getTotalParam(true, componentId, interfaceType);
										continue;
									}
									if (opcode == 3333) {
										scriptIntValues[isp++] = LoginManager.anInt39;
										continue;
									}
									if (opcode == 3335) {
										scriptIntValues[isp++] = client.language;
										continue;
									}
									if (opcode == 3336) {
										isp -= 4;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										componentId += interfaceType << 14;
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										componentId += j << 28;
										componentId += i;
										scriptIntValues[isp++] = componentId;
										continue;
									}
									if (opcode == 3337) {
										scriptIntValues[isp++] = client.affiliate;
										continue;
									}
								} else if (opcode < 3500) {
									@Pc(3422) EnumType type;
									if (opcode == 3400) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										type = EnumTypeList.get(componentId);
										if (type.valueType == 115) {
										}
										scriptStringValues[ssp++] = type.getValueString(interfaceType);
										continue;
									}
									if (opcode == 3408) {
										isp -= 4;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										@Pc(3469) EnumType local3469 = EnumTypeList.get(j);
										if (local3469.keyType == componentId && local3469.valueType == interfaceType) {
											if (interfaceType == 115) {
												scriptStringValues[ssp++] = local3469.getValueString(i);
											} else {
												scriptIntValues[isp++] = local3469.getValueInt(i);
											}
											continue;
										}
										throw new RuntimeException("C3408-1");
									}
									if (opcode == 3409) {
										isp -= 3;
										interfaceType = scriptIntValues[isp + 1];
										j = scriptIntValues[isp + 2];
										componentId = scriptIntValues[isp];
										if (interfaceType == -1) {
											throw new RuntimeException("C3409-2");
										}
										@Pc(3549) EnumType local3549 = EnumTypeList.get(interfaceType);
										if (local3549.valueType != componentId) {
											throw new RuntimeException("C3409-1");
										}
										scriptIntValues[isp++] = local3549.containsValue(j) ? 1 : 0;
										continue;
									}
									if (opcode == 3410) {
										isp--;
										componentId = scriptIntValues[isp];
										ssp--;
										chatTypedLowercase = scriptStringValues[ssp];
										if (componentId == -1) {
											throw new RuntimeException("C3410-2");
										}
										type = EnumTypeList.get(componentId);
										if (type.valueType != 115) {
											throw new RuntimeException("C3410-1");
										}
										scriptIntValues[isp++] = type.method3086(chatTypedLowercase) ? 1 : 0;
										continue;
									}
									if (opcode == 3411) {
										isp--;
										componentId = scriptIntValues[isp];
										@Pc(3645) EnumType local3645 = EnumTypeList.get(componentId);
										scriptIntValues[isp++] = local3645.table.length();
										continue;
									}
								} else if (opcode < 3700) {
									if (opcode == 3600) {
										if (FriendList.state == 0) {
											scriptIntValues[isp++] = -2;
										} else if (FriendList.state == 1) {
											scriptIntValues[isp++] = -1;
										} else {
											scriptIntValues[isp++] = FriendList.friendCount;
										}
										continue;
									}
									if (opcode == 3601) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && componentId < FriendList.friendCount) {
											scriptStringValues[ssp++] = FriendList.friendUsernames[componentId];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3602) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > componentId) {
											scriptIntValues[isp++] = FriendList.friendWorlds[componentId];
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3603) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > componentId) {
											scriptIntValues[isp++] = FriendList.ranks[componentId];
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3604) {
										isp--;
										interfaceType = scriptIntValues[isp];
										ssp--;
										chatTyped = scriptStringValues[ssp];
										FriendList.setRank(chatTyped, interfaceType);
										continue;
									}
									if (opcode == 3605) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										FriendList.addFriend(chatTyped.encode37());
										continue;
									}
									if (opcode == 3606) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										FriendList.removeFriend(chatTyped.encode37());
										continue;
									}
									if (opcode == 3607) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										IgnoreList.addIgnore(chatTyped.encode37());
										continue;
									}
									if (opcode == 3608) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										IgnoreList.remove(chatTyped.encode37());
										continue;
									}
									if (opcode == 3609) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped.startsWith(aClass100_446) || chatTyped.startsWith(aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										scriptIntValues[isp++] = FriendList.contains(chatTyped) ? 1 : 0;
										continue;
									}
									if (opcode == 3610) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && FriendList.friendCount > componentId) {
											scriptStringValues[ssp++] = FriendList.worldNames[componentId];
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3611) {
										if (ClanChat.name == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = ClanChat.name.toTitleCase();
										}
										continue;
									}
									if (opcode == 3612) {
										if (ClanChat.name == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = ClanChat.size;
										}
										continue;
									}
									if (opcode == 3613) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > componentId) {
											scriptStringValues[ssp++] = ClanChat.members[componentId].username.toTitleCase();
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3614) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && componentId < ClanChat.size) {
											scriptIntValues[isp++] = ClanChat.members[componentId].world;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3615) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > componentId) {
											scriptIntValues[isp++] = ClanChat.members[componentId].rank;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3616) {
										scriptIntValues[isp++] = ClanChat.minKick;
										continue;
									}
									if (opcode == 3617) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										ClanChat.kick(chatTyped);
										continue;
									}
									if (opcode == 3618) {
										scriptIntValues[isp++] = ClanChat.rank;
										continue;
									}
									if (opcode == 3619) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										ClanChat.join(chatTyped.encode37());
										continue;
									}
									if (opcode == 3620) {
										ClanChat.leave();
										continue;
									}
									if (opcode == 3621) {
										if (FriendList.state == 0) {
											scriptIntValues[isp++] = -1;
										} else {
											scriptIntValues[isp++] = IgnoreList.ignoreCount;
										}
										continue;
									}
									if (opcode == 3622) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state != 0 && IgnoreList.ignoreCount > componentId) {
											scriptStringValues[ssp++] = Base37.decode37(IgnoreList.encodedIgnores[componentId]).toTitleCase();
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3623) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped.startsWith(aClass100_446) || chatTyped.startsWith(aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										scriptIntValues[isp++] = IgnoreList.contains(chatTyped) ? 1 : 0;
										continue;
									}
									if (opcode == 3624) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.members != null && ClanChat.size > componentId && ClanChat.members[componentId].username.equalsIgnoreCase(PlayerList.self.username)) {
											scriptIntValues[isp++] = 1;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3625) {
										if (ClanChat.owner == null) {
											scriptStringValues[ssp++] = EMPTY_STRING;
										} else {
											scriptStringValues[ssp++] = ClanChat.owner.toTitleCase();
										}
										continue;
									}
									if (opcode == 3626) {
										isp--;
										componentId = scriptIntValues[isp];
										if (ClanChat.name != null && ClanChat.size > componentId) {
											scriptStringValues[ssp++] = ClanChat.members[componentId].worldName;
											continue;
										}
										scriptStringValues[ssp++] = EMPTY_STRING;
										continue;
									}
									if (opcode == 3627) {
										isp--;
										componentId = scriptIntValues[isp];
										if (FriendList.state == 2 && componentId >= 0 && componentId < FriendList.friendCount) {
											scriptIntValues[isp++] = FriendList.friendGame[componentId] ? 1 : 0;
											continue;
										}
										scriptIntValues[isp++] = 0;
										continue;
									}
									if (opcode == 3628) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped.startsWith(aClass100_446) || chatTyped.startsWith(aClass100_537)) {
											chatTyped = chatTyped.substring(7);
										}
										scriptIntValues[isp++] = FriendList.indexOf(chatTyped);
										continue;
									}
									if (opcode == 3629) {
										scriptIntValues[isp++] = client.country;
										continue;
									}
								} else if (opcode < 4000) {
									if (opcode == 3903) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].getType();
										continue;
									}
									if (opcode == 3904) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].item;
										continue;
									}
									if (opcode == 3905) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].price;
										continue;
									}
									if (opcode == 3906) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].count;
										continue;
									}
									if (opcode == 3907) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].completedCount;
										continue;
									}
									if (opcode == 3908) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = StockMarketManager.offers[componentId].completedGold;
										continue;
									}
									if (opcode == 3910) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 0 ? 1 : 0;
										continue;
									}
									if (opcode == 3911) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 2 ? 1 : 0;
										continue;
									}
									if (opcode == 3912) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 5 ? 1 : 0;
										continue;
									}
									if (opcode == 3913) {
										isp--;
										componentId = scriptIntValues[isp];
										interfaceType = StockMarketManager.offers[componentId].getStatus();
										scriptIntValues[isp++] = interfaceType == 1 ? 1 : 0;
										continue;
									}
								} else if (opcode < 4100) {
									if (opcode == 4000) {
										// add
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = interfaceType + componentId;
										continue;
									}
									if (opcode == 4001) {
										// sub
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId - interfaceType;
										continue;
									}
									if (opcode == 4002) {
										// multiply
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = interfaceType * componentId;
										continue;
									}
									if (opcode == 4003) {
										// divide
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId / interfaceType;
										continue;
									}
									if (opcode == 4004) {
										// random
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = (int) ((double) componentId * Math.random());
										continue;
									}
									if (opcode == 4005) {
										// randominc
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = (int) (Math.random() * (double) (componentId + 1));
										continue;
									}
									if (opcode == 4006) {
										// interpolate
										isp -= 5;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										i = scriptIntValues[isp + 3];
										j = scriptIntValues[isp + 2];
										start = scriptIntValues[isp + 4];
										scriptIntValues[isp++] = (interfaceType - componentId) * (start + -j) / (i - j) + componentId;
										continue;
									}
									@Pc(4899) long local4899;
									@Pc(4892) long local4892;
									if (opcode == 4007) {
										// addpercent
										isp -= 2;
										local4892 = scriptIntValues[isp];
										local4899 = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = (int) (local4892 * local4899 / 100L + local4892);
										continue;
									}
									if (opcode == 4008) {
										// setbit
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId | 0x1 << interfaceType;
										continue;
									}
									if (opcode == 4009) {
										// clearbit
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = -(0x1 << interfaceType) - 1 & componentId;
										continue;
									}
									if (opcode == 4010) {
										// testbit
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = (componentId & 0x1 << interfaceType) == 0 ? 0 : 1;
										continue;
									}
									if (opcode == 4011) {
										// modulo
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = componentId % interfaceType;
										continue;
									}
									if (opcode == 4012) {
										// pow
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										if (componentId == 0) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = (int) Math.pow((double) componentId, (double) interfaceType);
										}
										continue;
									}
									if (opcode == 4013) {
										// invpow
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										if (componentId == 0) {
											scriptIntValues[isp++] = 0;
										} else if (interfaceType == 0) {
											scriptIntValues[isp++] = Integer.MAX_VALUE;
										} else {
											scriptIntValues[isp++] = (int) Math.pow((double) componentId, 1.0D / (double) interfaceType);
										}
										continue;
									}
									if (opcode == 4014) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = interfaceType & componentId;
										continue;
									}
									if (opcode == 4015) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId | interfaceType;
										continue;
									}
									if (opcode == 4016) {
										isp -= 2;
										componentId = scriptIntValues[isp];
										interfaceType = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = componentId < interfaceType ? componentId : interfaceType;
										continue;
									}
									if (opcode == 4017) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = interfaceType >= componentId ? interfaceType : componentId;
										continue;
									}
									if (opcode == 4018) {
										isp -= 3;
										local4892 = scriptIntValues[isp];
										local4899 = scriptIntValues[isp + 1];
										@Pc(5251) long local5251 = (long) scriptIntValues[isp + 2];
										scriptIntValues[isp++] = (int) (local4892 * local5251 / local4899);
										continue;
									}
								} else if (opcode >= 4200) {
									@Pc(5294) ParamType local5294;
									if (opcode < 4300) {
										if (opcode == 4200) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptStringValues[ssp++] = ObjTypeList.get(componentId).name;
											continue;
										}
										@Pc(11269) ObjType local11269;
										if (opcode == 4201) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local11269 = ObjTypeList.get(componentId);
											if (interfaceType >= 1 && interfaceType <= 5 && local11269.op[interfaceType - 1] != null) {
												scriptStringValues[ssp++] = local11269.op[interfaceType - 1];
												continue;
											}
											scriptStringValues[ssp++] = EMPTY_STRING;
											continue;
										}
										if (opcode == 4202) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local11269 = ObjTypeList.get(componentId);
											if (interfaceType >= 1 && interfaceType <= 5 && local11269.iop[interfaceType - 1] != null) {
												scriptStringValues[ssp++] = local11269.iop[interfaceType - 1];
												continue;
											}
											scriptStringValues[ssp++] = EMPTY_STRING;
											continue;
										}
										if (opcode == 4203) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(componentId).cost;
											continue;
										}
										if (opcode == 4204) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(componentId).stackable == 1 ? 1 : 0;
											continue;
										}
										@Pc(11417) ObjType local11417;
										if (opcode == 4205) {
											isp--;
											componentId = scriptIntValues[isp];
											local11417 = ObjTypeList.get(componentId);
											if (local11417.certTemplate == -1 && local11417.certLink >= 0) {
												scriptIntValues[isp++] = local11417.certLink;
												continue;
											}
											scriptIntValues[isp++] = componentId;
											continue;
										}
										if (opcode == 4206) {
											isp--;
											componentId = scriptIntValues[isp];
											local11417 = ObjTypeList.get(componentId);
											if (local11417.certTemplate >= 0 && local11417.certLink >= 0) {
												scriptIntValues[isp++] = local11417.certLink;
												continue;
											}
											scriptIntValues[isp++] = componentId;
											continue;
										}
										if (opcode == 4207) {
											isp--;
											componentId = scriptIntValues[isp];
											scriptIntValues[isp++] = ObjTypeList.get(componentId).members ? 1 : 0;
											continue;
										}
										if (opcode == 4208) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local5294 = ParamTypeList.get(interfaceType);
											if (local5294.isString()) {
												scriptStringValues[ssp++] = ObjTypeList.get(componentId).getParam(local5294.defaultString, interfaceType);
											} else {
												scriptIntValues[isp++] = ObjTypeList.get(componentId).getParam(local5294.defaultInt, interfaceType);
											}
											continue;
										}
										if (opcode == 4210) {
											ssp--;
											chatTyped = scriptStringValues[ssp];
											isp--;
											interfaceType = scriptIntValues[isp];
											Find.search(interfaceType == 1, chatTyped);
											scriptIntValues[isp++] = Find.index;
											continue;
										}
										if (opcode == 4211) {
											if (Find.results != null && Find.size < Find.index) {
												scriptIntValues[isp++] = Find.results[Find.size++] & 0xFFFF;
												continue;
											}
											scriptIntValues[isp++] = -1;
											continue;
										}
										if (opcode == 4212) {
											Find.size = 0;
											continue;
										}
									} else if (opcode < 4400) {
										if (opcode == 4300) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local5294 = ParamTypeList.get(interfaceType);
											if (local5294.isString()) {
												scriptStringValues[ssp++] = NpcTypeList.get(componentId).getParam(interfaceType, local5294.defaultString);
											} else {
												scriptIntValues[isp++] = NpcTypeList.get(componentId).getParam(interfaceType, local5294.defaultInt);
											}
											continue;
										}
									} else if (opcode >= 4500) {
										if (opcode >= 4600) {
											if (opcode < 5100) {
												if (opcode == 5000) {
													scriptIntValues[isp++] = Chat.publicFilter;
													continue;
												}
												if (opcode == 5001) {
													isp -= 3;
													Chat.publicFilter = scriptIntValues[isp];
													Chat.privateFilter = scriptIntValues[isp + 1];
													Chat.tradeFilter = scriptIntValues[isp + 2];
													Protocol.outboundBuffer.pIsaac1(157);
													Protocol.outboundBuffer.p1(Chat.publicFilter);
													Protocol.outboundBuffer.p1(Chat.privateFilter);
													Protocol.outboundBuffer.p1(Chat.tradeFilter);
													continue;
												}
												if (opcode == 5002) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													isp -= 2;
													interfaceType = scriptIntValues[isp];
													j = scriptIntValues[isp + 1];
													Protocol.outboundBuffer.pIsaac1(99);
													Protocol.outboundBuffer.p8(chatTyped.encode37());
													Protocol.outboundBuffer.p1(interfaceType - 1);
													Protocol.outboundBuffer.p1(j);
													continue;
												}
												if (opcode == 5003) {
													chatTypedLowercase = null;
													isp--;
													componentId = scriptIntValues[isp];
													if (componentId < 100) {
														chatTypedLowercase = Chat.messages[componentId];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == 5004) {
													isp--;
													componentId = scriptIntValues[isp];
													interfaceType = -1;
													if (componentId < 100 && Chat.messages[componentId] != null) {
														interfaceType = Chat.types[componentId];
													}
													scriptIntValues[isp++] = interfaceType;
													continue;
												}
												if (opcode == 5005) {
													scriptIntValues[isp++] = Chat.privateFilter;
													continue;
												}
												if (opcode == 5008) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													if (!chatTyped.startsWith(aClass100_74)) {
														if (LoginManager.staffModLevel == 0 && (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat)) {
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
														WordPack.writeString(Protocol.outboundBuffer, chatTyped);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - start);
														continue;
													}
													Cheat.execute(chatTyped);
													continue;
												}
												if (opcode == 5009) {
													ssp -= 2;
													chatTypedLowercase = scriptStringValues[ssp + 1];
													chatTyped = scriptStringValues[ssp];
													if (LoginManager.staffModLevel != 0 || (!LoginManager.playerUnderage || LoginManager.parentalChatConsent) && !LoginManager.worldQuickChat) {
														Protocol.outboundBuffer.pIsaac1(201);
														Protocol.outboundBuffer.p1(0);
														j = Protocol.outboundBuffer.offset;
														Protocol.outboundBuffer.p8(chatTyped.encode37());
														WordPack.writeString(Protocol.outboundBuffer, chatTypedLowercase);
														Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - j);
													}
													continue;
												}
												if (opcode == 5010) {
													isp--;
													componentId = scriptIntValues[isp];
													chatTypedLowercase = null;
													if (componentId < 100) {
														chatTypedLowercase = Chat.names[componentId];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == 5011) {
													isp--;
													componentId = scriptIntValues[isp];
													chatTypedLowercase = null;
													if (componentId < 100) {
														chatTypedLowercase = Chat.clans[componentId];
													}
													if (chatTypedLowercase == null) {
														chatTypedLowercase = EMPTY_STRING;
													}
													scriptStringValues[ssp++] = chatTypedLowercase;
													continue;
												}
												if (opcode == 5012) {
													isp--;
													componentId = scriptIntValues[isp];
													interfaceType = -1;
													if (componentId < 100) {
														interfaceType = Chat.phraseIds[componentId];
													}
													scriptIntValues[isp++] = interfaceType;
													continue;
												}
												if (opcode == 5015) {
													if (PlayerList.self == null || PlayerList.self.username == null) {
														chatTyped = Player.usernameInput;
													} else {
														chatTyped = PlayerList.self.getUsername();
													}
													scriptStringValues[ssp++] = chatTyped;
													continue;
												}
												if (opcode == 5016) {
													scriptIntValues[isp++] = Chat.tradeFilter;
													continue;
												}
												if (opcode == 5017) {
													scriptIntValues[isp++] = Chat.size;
													continue;
												}
												if (opcode == 5050) {
													isp--;
													componentId = scriptIntValues[isp];
													scriptStringValues[ssp++] = QuickChatCatTypeList.get(componentId).description;
													continue;
												}
												@Pc(6378) QuickChatCatType local6378;
												if (opcode == 5051) {
													isp--;
													componentId = scriptIntValues[isp];
													local6378 = QuickChatCatTypeList.get(componentId);
													if (local6378.subcategories == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = local6378.subcategories.length;
													}
													continue;
												}
												if (opcode == 5052) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													@Pc(6416) QuickChatCatType local6416 = QuickChatCatTypeList.get(componentId);
													i = local6416.subcategories[interfaceType];
													scriptIntValues[isp++] = i;
													continue;
												}
												if (opcode == 5053) {
													isp--;
													componentId = scriptIntValues[isp];
													local6378 = QuickChatCatTypeList.get(componentId);
													if (local6378.phrases == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = local6378.phrases.length;
													}
													continue;
												}
												if (opcode == 5054) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).phrases[interfaceType];
													continue;
												}
												if (opcode == 5055) {
													isp--;
													componentId = scriptIntValues[isp];
													scriptStringValues[ssp++] = QuickChatPhraseTypeList.get(componentId).getText();
													continue;
												}
												if (opcode == 5056) {
													isp--;
													componentId = scriptIntValues[isp];
													@Pc(6527) QuickChatPhraseType local6527 = QuickChatPhraseTypeList.get(componentId);
													if (local6527.autoResponses == null) {
														scriptIntValues[isp++] = 0;
													} else {
														scriptIntValues[isp++] = local6527.autoResponses.length;
													}
													continue;
												}
												if (opcode == 5057) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatPhraseTypeList.get(componentId).autoResponses[interfaceType];
													continue;
												}
												if (opcode == 5058) {
													activePhrase = new QuickChatPhrase();
													isp--;
													activePhrase.id = scriptIntValues[isp];
													activePhrase.type = QuickChatPhraseTypeList.get(activePhrase.id);
													activePhrase.values = new int[activePhrase.type.getDynamicCommandCount()];
													continue;
												}
												if (opcode == 5059) {
													Protocol.outboundBuffer.pIsaac1(167);
													Protocol.outboundBuffer.p1(0);
													componentId = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(0);
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - componentId);
													continue;
												}
												if (opcode == 5060) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													Protocol.outboundBuffer.pIsaac1(178);
													Protocol.outboundBuffer.p1(0);
													interfaceType = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p8(chatTyped.encode37());
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - interfaceType);
													continue;
												}
												if (opcode == 5061) {
													Protocol.outboundBuffer.pIsaac1(167);
													Protocol.outboundBuffer.p1(0);
													componentId = Protocol.outboundBuffer.offset;
													Protocol.outboundBuffer.p1(1);
													Protocol.outboundBuffer.p2(activePhrase.id);
													activePhrase.type.encodeMessage(Protocol.outboundBuffer, activePhrase.values);
													Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - componentId);
													continue;
												}
												if (opcode == 5062) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).subcategoryShortcuts[interfaceType];
													continue;
												}
												if (opcode == 5063) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).phraseShortcuts[interfaceType];
													continue;
												}
												if (opcode == 5064) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													if (interfaceType == -1) {
														scriptIntValues[isp++] = -1;
													} else {
														scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).method469(interfaceType);
													}
													continue;
												}
												if (opcode == 5065) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													if (interfaceType == -1) {
														scriptIntValues[isp++] = -1;
													} else {
														scriptIntValues[isp++] = QuickChatCatTypeList.get(componentId).method466(interfaceType);
													}
													continue;
												}
												if (opcode == 5066) {
													isp--;
													componentId = scriptIntValues[isp];
													scriptIntValues[isp++] = QuickChatPhraseTypeList.get(componentId).getDynamicCommandCount();
													continue;
												}
												if (opcode == 5067) {
													isp -= 2;
													interfaceType = scriptIntValues[isp + 1];
													componentId = scriptIntValues[isp];
													j = QuickChatPhraseTypeList.get(componentId).getDynamicCommand(interfaceType);
													scriptIntValues[isp++] = j;
													continue;
												}
												if (opcode == 5068) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													activePhrase.values[componentId] = interfaceType;
													continue;
												}
												if (opcode == 5069) {
													isp -= 2;
													componentId = scriptIntValues[isp];
													interfaceType = scriptIntValues[isp + 1];
													activePhrase.values[componentId] = interfaceType;
													continue;
												}
												if (opcode == 5070) {
													isp -= 3;
													componentId = scriptIntValues[isp];
													j = scriptIntValues[isp + 2];
													interfaceType = scriptIntValues[isp + 1];
													@Pc(6996) QuickChatPhraseType local6996 = QuickChatPhraseTypeList.get(componentId);
													if (local6996.getDynamicCommand(interfaceType) != 0) {
														throw new RuntimeException("bad command");
													}
													scriptIntValues[isp++] = local6996.getDynamicCommandParam(j, interfaceType);
													continue;
												}
												if (opcode == 5071) {
													ssp--;
													chatTyped = scriptStringValues[ssp];
													isp--;
													local1552 = scriptIntValues[isp] == 1;
													Find.findQuickChatPhrases(local1552, chatTyped);
													scriptIntValues[isp++] = Find.index;
													continue;
												}
												if (opcode == 5072) {
													if (Find.results != null && Find.size < Find.index) {
														scriptIntValues[isp++] = Find.results[Find.size++] & 0xFFFF;
														continue;
													}
													scriptIntValues[isp++] = -1;
													continue;
												}
												if (opcode == 5073) {
													Find.size = 0;
													continue;
												}
											} else if (opcode < 5200) {
												if (opcode == 5100) {
													if (Keyboard.pressedKeys[86]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
												if (opcode == 5101) {
													if (Keyboard.pressedKeys[82]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
												if (opcode == 5102) {
													if (Keyboard.pressedKeys[81]) {
														scriptIntValues[isp++] = 1;
													} else {
														scriptIntValues[isp++] = 0;
													}
													continue;
												}
											} else {
												@Pc(7566) boolean local7566;
												if (opcode < 5300) {
													if (opcode == 5200) {
														isp--;
														WorldMap.setTargetZoom(scriptIntValues[isp]);
														continue;
													}
													if (opcode == 5201) {
														scriptIntValues[isp++] = WorldMap.getTargetZoom();
														continue;
													}
													if (opcode == 5202) {
														isp--;
														WorldMap.method4444(scriptIntValues[isp]);
														continue;
													}
													if (opcode == 5203) {
														ssp--;
														WorldMap.method4656(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == 5204) {
														scriptStringValues[ssp - 1] = WorldMap.method923(scriptStringValues[ssp - 1]);
														continue;
													}
													if (opcode == 5205) {
														ssp--;
														WorldMap.method1853(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == 5206) {
														isp--;
														componentId = scriptIntValues[isp];
														@Pc(7264) Map local7264 = MapList.getContainingSource(componentId >> 14 & 0x3FFF, componentId & 0x3FFF);
														if (local7264 == null) {
															scriptStringValues[ssp++] = EMPTY_STRING;
														} else {
															scriptStringValues[ssp++] = local7264.group;
														}
														continue;
													}
													@Pc(7293) Map local7293;
													if (opcode == 5207) {
														ssp--;
														local7293 = MapList.get(scriptStringValues[ssp]);
														if (local7293 != null && local7293.name != null) {
															scriptStringValues[ssp++] = local7293.name;
															continue;
														}
														scriptStringValues[ssp++] = EMPTY_STRING;
														continue;
													}
													if (opcode == 5208) {
														scriptIntValues[isp++] = WorldMap.anInt2387;
														scriptIntValues[isp++] = WorldMap.anInt1176;
														continue;
													}
													if (opcode == 5209) {
														scriptIntValues[isp++] = WorldMap.originX + WorldMap.anInt435;
														scriptIntValues[isp++] = WorldMap.originZ + WorldMap.length - WorldMap.anInt919 - 1;
														continue;
													}
													if (opcode == 5210) {
														local7293 = WorldMap.getCurrentMap();
														if (local7293 == null) {
															scriptIntValues[isp++] = 0;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local7293.originX * 64;
															scriptIntValues[isp++] = local7293.originZ * 64;
														}
														continue;
													}
													if (opcode == 5211) {
														local7293 = WorldMap.getCurrentMap();
														if (local7293 == null) {
															scriptIntValues[isp++] = 0;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local7293.displayMaxZ - local7293.displayMinX;
															scriptIntValues[isp++] = local7293.displayMinZ - local7293.displayMaxX;
														}
														continue;
													}
													if (opcode == 5212) {
														componentId = WorldMap.method2352();
														j = 0;
														if (componentId == -1) {
															chatTypedLowercase = EMPTY_STRING;
														} else {
															chatTypedLowercase = WorldMap.labels.text[componentId];
															j = WorldMap.labels.method3894(componentId);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(aClass100_639, aClass100_10);
														scriptStringValues[ssp++] = chatTypedLowercase;
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == 5213) {
														j = 0;
														componentId = WorldMap.method2385();
														if (componentId == -1) {
															chatTypedLowercase = EMPTY_STRING;
														} else {
															chatTypedLowercase = WorldMap.labels.text[componentId];
															j = WorldMap.labels.method3894(componentId);
														}
														chatTypedLowercase = chatTypedLowercase.method3140(aClass100_639, aClass100_10);
														scriptStringValues[ssp++] = chatTypedLowercase;
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == 5214) {
														isp--;
														componentId = scriptIntValues[isp];
														WorldMap.method3616(componentId >> 14 & 0x3FFF, componentId & 0x3FFF);
														continue;
													}
													if (opcode == 5215) {
														isp--;
														componentId = scriptIntValues[isp];
														ssp--;
														chatTypedLowercase = scriptStringValues[ssp];
														local7566 = false;
														@Pc(7577) SecondaryLinkedList local7577 = method3333(componentId >> 14 & 0x3FFF, componentId & 0x3FFF);
														for (@Pc(7582) Map local7582 = (Map) local7577.head(); local7582 != null; local7582 = (Map) local7577.next()) {
															if (local7582.group.equalsIgnoreCase(chatTypedLowercase)) {
																local7566 = true;
																break;
															}
														}
														if (local7566) {
															scriptIntValues[isp++] = 1;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == 5216) {
														isp--;
														componentId = scriptIntValues[isp];
														MapList.method4332(componentId);
														continue;
													}
													if (opcode == 5217) {
														isp--;
														componentId = scriptIntValues[isp];
														if (MapList.method1855(componentId)) {
															scriptIntValues[isp++] = 1;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == 5218) {
														local7293 = WorldMap.getCurrentMap();
														if (local7293 == null) {
															scriptIntValues[isp++] = -1;
														} else {
															scriptIntValues[isp++] = local7293.defaultZoom;
														}
														continue;
													}
													if (opcode == 5219) {
														ssp--;
														WorldMap.method1149(scriptStringValues[ssp]);
														continue;
													}
													if (opcode == 5220) {
														scriptIntValues[isp++] = WorldMap.loadPercentage == 100 ? 1 : 0;
														continue;
													}
												} else if (opcode < 5400) {
													if (opcode == 5300) {
														isp -= 2;
														interfaceType = scriptIntValues[isp + 1];
														componentId = scriptIntValues[isp];
														DisplayMode.setWindowMode(false, 3, componentId, interfaceType);
														scriptIntValues[isp++] = GameShell.fullScreenFrame == null ? 0 : 1;
														continue;
													}
													if (opcode == 5301) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														continue;
													}
													if (opcode == 5302) {
														@Pc(7780) DisplayMode[] local7780 = DisplayMode.getModes();
														scriptIntValues[isp++] = local7780.length;
														continue;
													}
													if (opcode == 5303) {
														isp--;
														componentId = scriptIntValues[isp];
														@Pc(7800) DisplayMode[] local7800 = DisplayMode.getModes();
														scriptIntValues[isp++] = local7800[componentId].width;
														scriptIntValues[isp++] = local7800[componentId].height;
														continue;
													}
													if (opcode == 5305) {
														interfaceType = Preferences.fullScreenHeight;
														componentId = Preferences.fullScreenWidth;
														j = -1;
														@Pc(7833) DisplayMode[] local7833 = DisplayMode.getModes();
														for (start = 0; start < local7833.length; start++) {
															@Pc(7843) DisplayMode local7843 = local7833[start];
															if (componentId == local7843.width && local7843.height == interfaceType) {
																j = start;
																break;
															}
														}
														scriptIntValues[isp++] = j;
														continue;
													}
													if (opcode == 5306) {
														scriptIntValues[isp++] = DisplayMode.getWindowMode();
														continue;
													}
													if (opcode == 5307) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														DisplayMode.setWindowMode(false, componentId, -1, -1);
														continue;
													}
													if (opcode == 5308) {
														scriptIntValues[isp++] = Preferences.favoriteWorlds;
														continue;
													}
													if (opcode == 5309) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.favoriteWorlds = componentId;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (opcode < 5500) {
													if (opcode == 5400) {
														ssp -= 2;
														chatTyped = scriptStringValues[ssp];
														chatTypedLowercase = scriptStringValues[ssp + 1];
														isp--;
														j = scriptIntValues[isp];
														Protocol.outboundBuffer.pIsaac1(117);
														Protocol.outboundBuffer.p1(Packet.gjstrlen(chatTyped) + Packet.gjstrlen(chatTypedLowercase) + 1);
														Protocol.outboundBuffer.pjstr(chatTyped);
														Protocol.outboundBuffer.pjstr(chatTypedLowercase);
														Protocol.outboundBuffer.p1(j);
														continue;
													}
													if (opcode == 5401) {
														isp -= 2;
														client.aShortArray88[scriptIntValues[isp]] = (short) ColorUtils.rgbToHsl(scriptIntValues[isp + 1]);
														ObjTypeList.clearModels();
														ObjTypeList.clearSprites();
														NpcTypeList.method4649();
														NpcTypeList.method443();
														method1807();
														continue;
													}
													if (opcode == 5405) {
														isp -= 2;
														componentId = scriptIntValues[isp];
														interfaceType = scriptIntValues[isp + 1];
														if (componentId >= 0 && componentId < 2) {
															Camera.anIntArrayArrayArray9[componentId] = new int[interfaceType << 1][4];
														}
														continue;
													}
													if (opcode == 5406) {
														isp -= 7;
														componentId = scriptIntValues[isp];
														interfaceType = scriptIntValues[isp + 1] << 1;
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														start = scriptIntValues[isp + 4];
														@Pc(8108) int local8108 = scriptIntValues[isp + 6];
														childId = scriptIntValues[isp + 5];
														if (componentId >= 0 && componentId < 2 && Camera.anIntArrayArrayArray9[componentId] != null && interfaceType >= 0 && Camera.anIntArrayArrayArray9[componentId].length > interfaceType) {
															Camera.anIntArrayArrayArray9[componentId][interfaceType] = new int[] { (j >> 14 & 0x3FFF) * 128, i, (j & 0x3FFF) * 128, local8108 };
															Camera.anIntArrayArrayArray9[componentId][interfaceType + 1] = new int[] { (start >> 14 & 0x3FFF) * 128, childId, (start & 0x3FFF) * 128 };
														}
														continue;
													}
													if (opcode == 5407) {
														isp--;
														componentId = Camera.anIntArrayArrayArray9[scriptIntValues[isp]].length >> 1;
														scriptIntValues[isp++] = componentId;
														continue;
													}
													if (opcode == 5411) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														if (GameShell.frame == null) {
															openUrl(method479(), false);
														} else {
															System.exit(0);
														}
														continue;
													}
													if (opcode == 5419) {
														chatTyped = EMPTY_STRING;
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
														scriptStringValues[ssp++] = chatTyped;
														continue;
													}
													if (opcode == 5420) {
														scriptIntValues[isp++] = SignLink.anInt5928 == 3 ? 1 : 0;
														continue;
													}
													if (opcode == 5421) {
														if (GameShell.fullScreenFrame != null) {
															DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
														}
														isp--;
														local1552 = scriptIntValues[isp] == 1;
														ssp--;
														chatTyped = scriptStringValues[ssp];
														@Pc(8356) JString local8356 = JString.concatenate(new JString[] { method479(), chatTyped });
														if (GameShell.frame != null || local1552 && SignLink.anInt5928 != 3 && SignLink.osName.startsWith("win") && !client.haveIe6) {
															Protocol.newTab = local1552;
															url = local8356;
															Protocol.openUrlRequest = GameShell.signLink.openUrl(new String(local8356.method3148(), "ISO-8859-1"));
															continue;
														}
														openUrl(local8356, local1552);
														continue;
													}
													if (opcode == 5422) {
														isp--;
														j = scriptIntValues[isp];
														ssp -= 2;
														chatTypedLowercase = scriptStringValues[ssp + 1];
														chatTyped = scriptStringValues[ssp];
														if (chatTyped.length() > 0) {
															if (PlayerList.playerNames == null) {
																PlayerList.playerNames = new JString[PlayerList.anIntArray309[client.game]];
															}
															PlayerList.playerNames[j] = chatTyped;
														}
														if (chatTypedLowercase.length() > 0) {
															if (PlayerList.playerNames2 == null) {
																PlayerList.playerNames2 = new JString[PlayerList.anIntArray309[client.game]];
															}
															PlayerList.playerNames2[j] = chatTypedLowercase;
														}
														continue;
													}
													if (opcode == 5423) {
														ssp--;
														scriptStringValues[ssp].printToConsole();
														continue;
													}
													if (opcode == 5424) {
														isp -= 11;
														LoginManager.anInt1275 = scriptIntValues[isp];
														LoginManager.anInt2910 = scriptIntValues[isp + 1];
														LoginManager.anInt5457 = scriptIntValues[isp + 2];
														LoginManager.anInt5208 = scriptIntValues[isp + 3];
														LoginManager.anInt1736 = scriptIntValues[isp + 4];
														LoginManager.anInt4073 = scriptIntValues[isp + 5];
														LoginManager.anInt2261 = scriptIntValues[isp + 6];
														LoginManager.anInt3324 = scriptIntValues[isp + 7];
														LoginManager.anInt5556 = scriptIntValues[isp + 8];
														LoginManager.anInt4581 = scriptIntValues[isp + 9];
														LoginManager.anInt5752 = scriptIntValues[isp + 10];
														client.js5Archive8.isFileReady(LoginManager.anInt1736);
														client.js5Archive8.isFileReady(LoginManager.anInt4073);
														client.js5Archive8.isFileReady(LoginManager.anInt2261);
														client.js5Archive8.isFileReady(LoginManager.anInt3324);
														client.js5Archive8.isFileReady(LoginManager.anInt5556);
														InterfaceList.aBoolean298 = true;
														continue;
													}
													if (opcode == 5425) {
														LoginManager.method4637();
														InterfaceList.aBoolean298 = false;
														continue;
													}
													if (opcode == 5426) {
														isp--;
														anInt5794 = scriptIntValues[isp];
														continue;
													}
													if (opcode == 5427) {
														isp -= 2;
														MiniMap.anInt4075 = scriptIntValues[isp];
														MiniMap.anInt5073 = scriptIntValues[isp + 1];
														continue;
													}
												} else if (opcode < 5600) {
													if (opcode == 5500) {
														isp -= 4;
														componentId = scriptIntValues[isp];
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														interfaceType = scriptIntValues[isp + 1];
														Camera.method2722(false, j, interfaceType, i, (componentId & 0x3FFF) - Camera.originZ, (componentId >> 14 & 0x3FFF) - Camera.originX);
														continue;
													}
													if (opcode == 5501) {
														isp -= 4;
														interfaceType = scriptIntValues[isp + 1];
														componentId = scriptIntValues[isp];
														i = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 2];
														Camera.method3849(interfaceType, (componentId & 0x3FFF) - Camera.originZ, j, (componentId >> 14 & 0x3FFF) - Camera.originX, i);
														continue;
													}
													if (opcode == 5502) {
														isp -= 6;
														componentId = scriptIntValues[isp];
														if (componentId >= 2) {
															throw new RuntimeException();
														}
														Camera.anInt3718 = componentId;
														interfaceType = scriptIntValues[isp + 1];
														if (Camera.anIntArrayArrayArray9[Camera.anInt3718].length >> 1 <= interfaceType + 1) {
															throw new RuntimeException();
														}
														Camera.anInt3125 = interfaceType;
														Camera.anInt5224 = 0;
														Camera.anInt5101 = scriptIntValues[isp + 2];
														Camera.anInt5843 = scriptIntValues[isp + 3];
														j = scriptIntValues[isp + 4];
														if (j >= 2) {
															throw new RuntimeException();
														}
														Camera.anInt1694 = j;
														i = scriptIntValues[isp + 5];
														if (Camera.anIntArrayArrayArray9[Camera.anInt1694].length >> 1 <= i + 1) {
															throw new RuntimeException();
														}
														Camera.anInt2119 = i;
														Camera.cameraType = 3;
														continue;
													}
													if (opcode == 5503) {
														Camera.resetCameraEffects();
														continue;
													}
													if (opcode == 5504) {
														isp -= 2;
														Camera.orbitCameraPitch = scriptIntValues[isp];
														Camera.orbitCameraYaw = scriptIntValues[isp + 1];
														if (Camera.cameraType == 2) {
															Camera.cameraYaw = Camera.orbitCameraYaw;
															Camera.cameraPitch = Camera.orbitCameraPitch;
														}
														SceneCamera.clampCameraAngle();
														continue;
													}
													if (opcode == 5505) {
														scriptIntValues[isp++] = Camera.orbitCameraPitch;
														continue;
													}
													if (opcode == 5506) {
														scriptIntValues[isp++] = Camera.orbitCameraYaw;
														continue;
													}
												} else if (opcode < 5700) {
													if (opcode == 5600) {
														ssp -= 2;
														chatTyped = scriptStringValues[ssp];
														chatTypedLowercase = scriptStringValues[ssp + 1];
														isp--;
														j = scriptIntValues[isp];
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															LoginManager.login(chatTyped, chatTypedLowercase, j);
														}
														continue;
													}
													if (opcode == 5601) {
														LoginManager.method3395();
														continue;
													}
													if (opcode == 5602) {
														if (LoginManager.step == 0) {
															LoginManager.reply = -2;
														}
														continue;
													}
													if (opcode == 5603) {
														isp -= 4;
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.checkInfo(scriptIntValues[isp + 2], scriptIntValues[isp + 3], scriptIntValues[isp], scriptIntValues[isp + 1]);
														}
														continue;
													}
													if (opcode == 5604) {
														ssp--;
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.checkName(scriptStringValues[ssp].encode37());
														}
														continue;
													}
													if (opcode == 5605) {
														isp -= 4;
														ssp -= 2;
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0 && WorldList.step == 0) {
															CreateManager.createAccount(scriptIntValues[isp], scriptIntValues[isp + 3], scriptIntValues[isp + 1], scriptStringValues[ssp + 1], scriptStringValues[ssp].encode37(), scriptIntValues[isp + 2]);
														}
														continue;
													}
													if (opcode == 5606) {
														if (CreateManager.step == 0) {
															CreateManager.reply = -2;
														}
														continue;
													}
													if (opcode == 5607) {
														scriptIntValues[isp++] = LoginManager.reply;
														continue;
													}
													if (opcode == 5608) {
														scriptIntValues[isp++] = LoginManager.hopTime;
														continue;
													}
													if (opcode == 5609) {
														scriptIntValues[isp++] = CreateManager.reply;
														continue;
													}
													if (opcode == 5610) {
														for (componentId = 0; componentId < 5; componentId++) {
															scriptStringValues[ssp++] = CreateManager.suggestedNames.length > componentId ? CreateManager.suggestedNames[componentId].toTitleCase() : EMPTY_STRING;
														}
														CreateManager.suggestedNames = null;
														continue;
													}
													if (opcode == 5611) {
														scriptIntValues[isp++] = LoginManager.disallowResult;
														continue;
													}
												} else if (opcode < 6100) {
													if (opcode == 6001) {
														isp--;
														int brightness = scriptIntValues[isp];
														if (brightness < 1) {
															brightness = 1;
														}
														if (brightness > 4) {
															brightness = 4;
														}
														Preferences.brightness = brightness;
														if (!GlRenderer.enabled || !Preferences.highDetailLighting) {
															if (Preferences.brightness == 1) {
																Rasterizer.setBrightness(0.9F);
															}
															if (Preferences.brightness == 2) {
																Rasterizer.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Rasterizer.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Rasterizer.setBrightness(0.6F);
															}
														}
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
															if (!Preferences.highDetailLighting) {
																method2742();
															}
														}
														ObjTypeList.clearSprites();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6002) {
														isp--;
														Preferences.setLowmem(scriptIntValues[isp] == 1);
														LocTypeList.clear();
														method2742();
														method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6003) {
														isp--;
														Preferences.roofsVisible = scriptIntValues[isp] == 1;
														method2218();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6005) {
														isp--;
														Preferences.showGroundDecorations = scriptIntValues[isp] == 1;
														method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6006) {
														isp--;
														Preferences.highDetailTextures = scriptIntValues[isp] == 1;
														((Js5TextureProvider) Rasterizer.textureProvider).method3245(!Preferences.highDetailTextures);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6007) {
														isp--;
														Preferences.manyIdleAnimations = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6008) {
														isp--;
														Preferences.flickeringEffectsOn = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6009) {
														isp--;
														Preferences.manyGroundTextures = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6010) {
														isp--;
														Preferences.characterShadowsOn = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6011) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.sceneryShadowsType = componentId;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6012) {
														if (GlRenderer.enabled) {
															MaterialManager.setMaterial(0, 0);
														}
														isp--;
														Preferences.highDetailLighting = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled && Preferences.highDetailLighting) {
															Rasterizer.setBrightness(0.7F);
														} else {
															if (Preferences.brightness == 1) {
																Rasterizer.setBrightness(0.9F);
															}
															if (Preferences.brightness == 2) {
																Rasterizer.setBrightness(0.8F);
															}
															if (Preferences.brightness == 3) {
																Rasterizer.setBrightness(0.7F);
															}
															if (Preferences.brightness == 4) {
																Rasterizer.setBrightness(0.6F);
															}
														}
														method2742();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6014) {
														isp--;
														Preferences.highWaterDetail = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled) {
															method2742();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6015) {
														isp--;
														Preferences.fogEnabled = scriptIntValues[isp] == 1;
														if (GlRenderer.enabled) {
															FogManager.setInstantFade();
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6016) {
														isp--;
														componentId = scriptIntValues[isp];
														if (GlRenderer.enabled) {
															GameShell.canvasReplaceRecommended = true;
														}
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.antiAliasingMode = componentId;
														continue;
													}
													if (opcode == 6017) {
														isp--;
														Preferences.stereo = scriptIntValues[isp] == 1;
														client.method930();
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6018) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 127) {
															componentId = 127;
														}
														Preferences.soundEffectVolume = componentId;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6019) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 255) {
															componentId = 255;
														}
														if (componentId != Preferences.musicVolume) {
															if (Preferences.musicVolume == 0 && MusicPlayer.groupId != -1) {
																MidiPlayer.playImmediate(client.js5Archive6, MusicPlayer.groupId, componentId);
																MidiPlayer.jingle = false;
															} else if (componentId == 0) {
																MidiPlayer.method4548();
																MidiPlayer.jingle = false;
															} else {
																MidiPlayer.method3956(componentId);
															}
															Preferences.musicVolume = componentId;
														}
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6020) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 127) {
															componentId = 127;
														}
														Preferences.ambientSoundsVolume = componentId;
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														continue;
													}
													if (opcode == 6021) {
														isp--;
														neverRemoveRoofs = scriptIntValues[isp] == 1;
														method2218();
														continue;
													}
													if (opcode == 6023) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															componentId = 0;
														}
														if (componentId > 2) {
															componentId = 2;
														}
														local1552 = false;
														if (GameShell.maxMemory < 96) {
															local1552 = true;
															componentId = 0;
														}
														Preferences.setParticles(componentId);
														Preferences.write(GameShell.signLink);
														Preferences.sentToServer = false;
														scriptIntValues[isp++] = local1552 ? 0 : 1;
														continue;
													}
													if (opcode == 6024) {
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0 || componentId > 2) {
															componentId = 0;
														}
														Preferences.windowMode = componentId;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == 6028) {
														isp--;
														Preferences.cursorsEnabled = scriptIntValues[isp] != 0;
														Preferences.write(GameShell.signLink);
														continue;
													}
												} else if (opcode < 6200) {
													if (opcode == 6101) {
														scriptIntValues[isp++] = Preferences.brightness;
														continue;
													}
													if (opcode == 6102) {
														scriptIntValues[isp++] = SceneGraph.allLevelsAreVisible() ? 1 : 0;
														continue;
													}
													if (opcode == 6103) {
														scriptIntValues[isp++] = Preferences.roofsVisible ? 1 : 0;
														continue;
													}
													if (opcode == 6105) {
														scriptIntValues[isp++] = Preferences.showGroundDecorations ? 1 : 0;
														continue;
													}
													if (opcode == 6106) {
														scriptIntValues[isp++] = Preferences.highDetailTextures ? 1 : 0;
														continue;
													}
													if (opcode == 6107) {
														scriptIntValues[isp++] = Preferences.manyIdleAnimations ? 1 : 0;
														continue;
													}
													if (opcode == 6108) {
														scriptIntValues[isp++] = Preferences.flickeringEffectsOn ? 1 : 0;
														continue;
													}
													if (opcode == 6109) {
														scriptIntValues[isp++] = Preferences.manyGroundTextures ? 1 : 0;
														continue;
													}
													if (opcode == 6110) {
														scriptIntValues[isp++] = Preferences.characterShadowsOn ? 1 : 0;
														continue;
													}
													if (opcode == 6111) {
														scriptIntValues[isp++] = Preferences.sceneryShadowsType;
														continue;
													}
													if (opcode == 6112) {
														scriptIntValues[isp++] = Preferences.highDetailLighting ? 1 : 0;
														continue;
													}
													if (opcode == 6114) {
														scriptIntValues[isp++] = Preferences.highWaterDetail ? 1 : 0;
														continue;
													}
													if (opcode == 6115) {
														scriptIntValues[isp++] = Preferences.fogEnabled ? 1 : 0;
														continue;
													}
													if (opcode == 6116) {
														scriptIntValues[isp++] = Preferences.antiAliasingMode;
														continue;
													}
													if (opcode == 6117) {
														scriptIntValues[isp++] = Preferences.stereo ? 1 : 0;
														continue;
													}
													if (opcode == 6118) {
														scriptIntValues[isp++] = Preferences.soundEffectVolume;
														continue;
													}
													if (opcode == 6119) {
														scriptIntValues[isp++] = Preferences.musicVolume;
														continue;
													}
													if (opcode == 6120) {
														scriptIntValues[isp++] = Preferences.ambientSoundsVolume;
														continue;
													}
													if (opcode == 6121) {
														if (GlRenderer.enabled) {
															scriptIntValues[isp++] = GlRenderer.arbMultisampleSupported ? 1 : 0;
														} else {
															scriptIntValues[isp++] = 0;
														}
														continue;
													}
													if (opcode == 6123) {
														scriptIntValues[isp++] = Preferences.getParticleSetting();
														continue;
													}
													if (opcode == 6124) {
														scriptIntValues[isp++] = Preferences.windowMode;
														continue;
													}
													if (opcode == 6128) {
														scriptIntValues[isp++] = Preferences.cursorsEnabled ? 1 : 0;
														continue;
													}
												} else if (opcode < 6300) {
													if (opcode == 6200) {
														isp -= 2;
														aShort25 = (short) scriptIntValues[isp];
														if (aShort25 <= 0) {
															aShort25 = 256;
														}
														aShort9 = (short) scriptIntValues[isp + 1];
														if (aShort9 <= 0) {
															aShort9 = 205;
														}
														continue;
													}
													if (opcode == 6201) {
														isp -= 2;
														aShort30 = (short) scriptIntValues[isp];
														if (aShort30 <= 0) {
															aShort30 = 256;
														}
														aShort27 = (short) scriptIntValues[isp + 1];
														if (aShort27 <= 0) {
															aShort27 = 320;
														}
														continue;
													}
													if (opcode == 6202) {
														isp -= 4;
														aShort22 = (short) scriptIntValues[isp];
														if (aShort22 <= 0) {
															aShort22 = 1;
														}
														aShort1 = (short) scriptIntValues[isp + 1];
														if (aShort1 <= 0) {
															aShort1 = 32767;
														} else if (aShort22 > aShort1) {
															aShort1 = aShort22;
														}
														aShort12 = (short) scriptIntValues[isp + 2];
														if (aShort12 <= 0) {
															aShort12 = 1;
														}
														aShort21 = (short) scriptIntValues[isp + 3];
														if (aShort21 <= 0) {
															aShort21 = 32767;
														} else if (aShort21 < aShort12) {
															aShort21 = aShort12;
														}
														continue;
													}
													if (opcode == 6203) {
														method2314(InterfaceList.aClass13_26.width, 0, InterfaceList.aClass13_26.height, 0, false);
														scriptIntValues[isp++] = anInt4055;
														scriptIntValues[isp++] = anInt5377;
														continue;
													}
													if (opcode == 6204) {
														scriptIntValues[isp++] = aShort30;
														scriptIntValues[isp++] = aShort27;
														continue;
													}
													if (opcode == 6205) {
														scriptIntValues[isp++] = aShort25;
														scriptIntValues[isp++] = aShort9;
														continue;
													}
												} else if (opcode < 6400) {
													if (opcode == 6300) {
														scriptIntValues[isp++] = (int) (MonotonicTime.currentTimeMillis() / 60000L);
														continue;
													}
													if (opcode == 6301) {
														scriptIntValues[isp++] = (int) (MonotonicTime.currentTimeMillis() / 86400000L) - 11745;
														continue;
													}
													if (opcode == 6302) {
														isp -= 3;
														j = scriptIntValues[isp + 2];
														interfaceType = scriptIntValues[isp + 1];
														componentId = scriptIntValues[isp];
														aCalendar2.clear();
														aCalendar2.set(11, 12);
														aCalendar2.set(j, interfaceType, componentId);
														scriptIntValues[isp++] = (int) (aCalendar2.getTime().getTime() / 86400000L) - 11745;
														continue;
													}
													if (opcode == 6303) {
														aCalendar2.clear();
														aCalendar2.setTime(new Date(MonotonicTime.currentTimeMillis()));
														scriptIntValues[isp++] = aCalendar2.get(1);
														continue;
													}
													if (opcode == 6304) {
														local1552 = true;
														isp--;
														componentId = scriptIntValues[isp];
														if (componentId < 0) {
															local1552 = (componentId + 1) % 4 == 0;
														} else if (componentId < 1582) {
															local1552 = componentId % 4 == 0;
														} else if (componentId % 4 != 0) {
															local1552 = false;
														} else if (componentId % 100 != 0) {
															local1552 = true;
														} else if (componentId % 400 != 0) {
															local1552 = false;
														}
														scriptIntValues[isp++] = local1552 ? 1 : 0;
														continue;
													}
												} else if (opcode < 6500) {
													if (opcode == 6405) {
														scriptIntValues[isp++] = client.showVideoAd() ? 1 : 0;
														continue;
													}
													if (opcode == 6406) {
														scriptIntValues[isp++] = isShowingVideoAd() ? 1 : 0;
														continue;
													}
												} else if (opcode < 6600) {
													if (opcode == 6500) {
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															scriptIntValues[isp++] = WorldList.fetch() == -1 ? 0 : 1;
															continue;
														}
														scriptIntValues[isp++] = 1;
														continue;
													}
													@Pc(10247) WorldInfo local10247;
													@Pc(10191) World local10191;
													if (opcode == 6501) {
														local10191 = WorldList.getFirstWorld();
														if (local10191 == null) {
															scriptIntValues[isp++] = -1;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local10191.id;
															scriptIntValues[isp++] = local10191.flags;
															scriptStringValues[ssp++] = local10191.activity;
															local10247 = local10191.getWorldInfo();
															scriptIntValues[isp++] = local10247.flag;
															scriptStringValues[ssp++] = local10247.name;
															scriptIntValues[isp++] = local10191.players;
														}
														continue;
													}
													if (opcode == 6502) {
														local10191 = WorldList.getNextWorld();
														if (local10191 == null) {
															scriptIntValues[isp++] = -1;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local10191.id;
															scriptIntValues[isp++] = local10191.flags;
															scriptStringValues[ssp++] = local10191.activity;
															local10247 = local10191.getWorldInfo();
															scriptIntValues[isp++] = local10247.flag;
															scriptStringValues[ssp++] = local10247.name;
															scriptIntValues[isp++] = local10191.players;
														}
														continue;
													}
													if (opcode == 6503) {
														isp--;
														componentId = scriptIntValues[isp];
														if (client.gameState == 10 && LoginManager.autoStep == 0 && LoginManager.step == 0 && CreateManager.step == 0) {
															scriptIntValues[isp++] = WorldList.hopWorld(componentId) ? 1 : 0;
															continue;
														}
														scriptIntValues[isp++] = 0;
														continue;
													}
													if (opcode == 6504) {
														isp--;
														Preferences.lastWorldId = scriptIntValues[isp];
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == 6505) {
														scriptIntValues[isp++] = Preferences.lastWorldId;
														continue;
													}
													if (opcode == 6506) {
														isp--;
														componentId = scriptIntValues[isp];
														@Pc(10440) World local10440 = getWorld(componentId);
														if (local10440 == null) {
															scriptIntValues[isp++] = -1;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
															scriptStringValues[ssp++] = EMPTY_STRING;
															scriptIntValues[isp++] = 0;
														} else {
															scriptIntValues[isp++] = local10440.flags;
															scriptStringValues[ssp++] = local10440.activity;
															@Pc(10458) WorldInfo local10458 = local10440.getWorldInfo();
															scriptIntValues[isp++] = local10458.flag;
															scriptStringValues[ssp++] = local10458.name;
															scriptIntValues[isp++] = local10440.players;
														}
														continue;
													}
													if (opcode == 6507) {
														isp -= 4;
														j = scriptIntValues[isp + 2];
														componentId = scriptIntValues[isp];
														local7566 = scriptIntValues[isp + 3] == 1;
														local1552 = scriptIntValues[isp + 1] == 1;
														WorldList.sortWorldList(j, local1552, componentId, local7566);
														continue;
													}
												} else if (opcode < 6700) {
													if (opcode == 6600) {
														isp--;
														Preferences.aBoolean63 = scriptIntValues[isp] == 1;
														Preferences.write(GameShell.signLink);
														continue;
													}
													if (opcode == 6601) {
														scriptIntValues[isp++] = Preferences.aBoolean63 ? 1 : 0;
														continue;
													}
												}
											}
										} else if (opcode == 4500) {
											isp -= 2;
											componentId = scriptIntValues[isp];
											interfaceType = scriptIntValues[isp + 1];
											local5294 = ParamTypeList.get(interfaceType);
											if (local5294.isString()) {
												scriptStringValues[ssp++] = StructTypeList.get(componentId).getParam(local5294.defaultString, interfaceType);
											} else {
												scriptIntValues[isp++] = StructTypeList.get(componentId).method2798(interfaceType, local5294.defaultInt);
											}
											continue;
										}
									} else if (opcode == 4400) {
										isp -= 2;
										interfaceType = scriptIntValues[isp + 1];
										componentId = scriptIntValues[isp];
										local5294 = ParamTypeList.get(interfaceType);
										if (local5294.isString()) {
											scriptStringValues[ssp++] = LocTypeList.get(componentId).getParam(local5294.defaultString, interfaceType);
										} else {
											scriptIntValues[isp++] = LocTypeList.get(componentId).getParam(local5294.defaultInt, interfaceType);
										}
										continue;
									}
								} else {
									if (opcode == 4100) {
										// append_num
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp--;
										interfaceType = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { chatTyped, JString.parseInt(interfaceType) });
										continue;
									}
									if (opcode == 4101) {
										// append
										ssp -= 2;
										chatTypedLowercase = scriptStringValues[ssp + 1];
										chatTyped = scriptStringValues[ssp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { chatTyped, chatTypedLowercase });
										continue;
									}
									if (opcode == 4102) {
										// append_signnum
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp--;
										interfaceType = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { chatTyped, JString.parseIntTrue(interfaceType) });
										continue;
									}
									if (opcode == 4103) {
										// lowercase
										ssp--;
										chatTyped = scriptStringValues[ssp];
										scriptStringValues[ssp++] = chatTyped.toLowerCase();
										continue;
									}
									if (opcode == 4104) {
										// fromdate
										isp--;
										componentId = scriptIntValues[isp];
										@Pc(11770) long local11770 = (long) componentId * 86400000L + 1014768000000L;
										aCalendar2.setTime(new Date(local11770));
										i = aCalendar2.get(5);
										start = aCalendar2.get(2);
										childId = aCalendar2.get(1);
										scriptStringValues[ssp++] = JString.concatenate(new JString[] { JString.parseInt(i), aClass100_767, DateUtil.aClass100Array40[start], aClass100_767, JString.parseInt(childId) });
										continue;
									}
									if (opcode == 4105) {
										// text_gender
										ssp -= 2;
										chatTypedLowercase = scriptStringValues[ssp + 1];
										chatTyped = scriptStringValues[ssp];
										if (PlayerList.self.appearance != null && PlayerList.self.appearance.gender) {
											scriptStringValues[ssp++] = chatTypedLowercase;
											continue;
										}
										scriptStringValues[ssp++] = chatTyped;
										continue;
									}
									if (opcode == 4106) {
										// tostring
										isp--;
										componentId = scriptIntValues[isp];
										scriptStringValues[ssp++] = JString.parseInt(componentId);
										continue;
									}
									if (opcode == 4107) {
										// compare
										ssp -= 2;
										scriptIntValues[isp++] = scriptStringValues[ssp].compare(scriptStringValues[ssp + 1]);
										continue;
									}
									if (opcode == 4108) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp -= 2;
										j = scriptIntValues[isp + 1];
										interfaceType = scriptIntValues[isp];
										scriptIntValues[isp++] = FontMetricsList.get(j).getParagraphLineCount(chatTyped, interfaceType);
										continue;
									}
									if (opcode == 4109) {
										isp -= 2;
										ssp--;
										chatTyped = scriptStringValues[ssp];
										j = scriptIntValues[isp + 1];
										interfaceType = scriptIntValues[isp];
										scriptIntValues[isp++] = FontMetricsList.get(j).getMaxLineWidth(chatTyped, interfaceType);
										continue;
									}
									if (opcode == 4110) {
										ssp -= 2;
										chatTyped = scriptStringValues[ssp];
										chatTypedLowercase = scriptStringValues[ssp + 1];
										isp--;
										if (scriptIntValues[isp] == 1) {
											scriptStringValues[ssp++] = chatTyped;
										} else {
											scriptStringValues[ssp++] = chatTypedLowercase;
										}
										continue;
									}
									if (opcode == 4111) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										scriptStringValues[ssp++] = Font.escape(chatTyped);
										continue;
									}
									if (opcode == 4112) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										isp--;
										interfaceType = scriptIntValues[isp];
										if (interfaceType == -1) {
											throw new RuntimeException("null char");
										}
										scriptStringValues[ssp++] = chatTyped.concatChar(interfaceType);
										continue;
									}
									if (opcode == 4113) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isValidChar(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4114) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.method433(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4115) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isLetter(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4116) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.isDigit(componentId) ? 1 : 0;
										continue;
									}
									if (opcode == 4117) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										if (chatTyped == null) {
											scriptIntValues[isp++] = 0;
										} else {
											scriptIntValues[isp++] = chatTyped.length();
										}
										continue;
									}
									if (opcode == 4118) {
										isp -= 2;
										ssp--;
										chatTyped = scriptStringValues[ssp];
										interfaceType = scriptIntValues[isp];
										j = scriptIntValues[isp + 1];
										scriptStringValues[ssp++] = chatTyped.substring(j, interfaceType);
										continue;
									}
									if (opcode == 4119) {
										ssp--;
										chatTyped = scriptStringValues[ssp];
										chatTypedLowercase = JString.allocate(chatTyped.length());
										@Pc(12220) boolean local12220 = false;
										for (i = 0; i < chatTyped.length(); i++) {
											start = chatTyped.charAt(i);
											if (start == 60) {
												local12220 = true;
											} else if (start == 62) {
												local12220 = false;
											} else if (!local12220) {
												chatTypedLowercase.append(start);
											}
										}
										chatTypedLowercase.method3156();
										scriptStringValues[ssp++] = chatTypedLowercase;
										continue;
									}
									if (opcode == 4120) {
										isp -= 2;
										ssp--;
										chatTyped = scriptStringValues[ssp];
										interfaceType = scriptIntValues[isp];
										j = scriptIntValues[isp + 1];
										scriptIntValues[isp++] = chatTyped.indexOf(interfaceType, j);
										continue;
									}
									if (opcode == 4121) {
										ssp -= 2;
										chatTyped = scriptStringValues[ssp];
										chatTypedLowercase = scriptStringValues[ssp + 1];
										isp--;
										j = scriptIntValues[isp];
										scriptIntValues[isp++] = chatTyped.indexOf(chatTypedLowercase, j);
										continue;
									}
									if (opcode == 4122) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.toLowerCase(componentId);
										continue;
									}
									if (opcode == 4123) {
										isp--;
										componentId = scriptIntValues[isp];
										scriptIntValues[isp++] = CharUtils.toUpperCase(componentId);
										continue;
									}
									if (opcode == 4124) {
										isp--;
										local12388 = scriptIntValues[isp] != 0;
										isp--;
										interfaceType = scriptIntValues[isp];
										scriptStringValues[ssp++] = StringUtils.formatNumber(client.language, local12388, 0, (long) interfaceType);
										continue;
									}
								}
							}
						} else {
							if (opcode < 2000) {
								createdComponent = secondary ? secondaryActiveComponent : primaryActiveComponent;
							} else {
								isp--;
								createdComponent = InterfaceList.getComponent(scriptIntValues[isp]);
								opcode -= 1000;
							}
							if (opcode == 1000) {
								// setposition
								isp -= 4;
								createdComponent.baseX = scriptIntValues[isp];
								createdComponent.baseY = scriptIntValues[isp + 1];
								j = scriptIntValues[isp + 3];
								if (j < 0) {
									j = 0;
								} else if (j > 5) {
									j = 5;
								}
								interfaceType = scriptIntValues[isp + 2];
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 5) {
									interfaceType = 5;
								}
								createdComponent.xMode = (byte) j;
								createdComponent.yMode = (byte) interfaceType;
								InterfaceList.redraw(createdComponent);
								InterfaceList.update(createdComponent);
								if (createdComponent.createdComponentId == -1) {
									DelayedStateChange.setComponentPositionClient(createdComponent.id);
								}
								continue;
							}
							if (opcode == 1001) {
								// setsize
								isp -= 4;
								createdComponent.baseWidth = scriptIntValues[isp];
								createdComponent.baseHeight = scriptIntValues[isp + 1];
								createdComponent.anInt451 = 0;
								createdComponent.anInt526 = 0;
								interfaceType = scriptIntValues[isp + 2];
								j = scriptIntValues[isp + 3];
								if (j < 0) {
									j = 0;
								} else if (j > 4) {
									j = 4;
								}
								createdComponent.dynamicHeightValue = (byte) j;
								if (interfaceType < 0) {
									interfaceType = 0;
								} else if (interfaceType > 4) {
									interfaceType = 4;
								}
								createdComponent.dynamicWidthValue = (byte) interfaceType;
								InterfaceList.redraw(createdComponent);
								InterfaceList.update(createdComponent);
								if (createdComponent.type == 0) {
									InterfaceList.method531(createdComponent, false);
								}
								continue;
							}
							if (opcode == 1003) {
								// sethide
								isp--;
								local1552 = scriptIntValues[isp] == 1;
								if (local1552 != createdComponent.hidden) {
									createdComponent.hidden = local1552;
									InterfaceList.redraw(createdComponent);
								}
								if (createdComponent.createdComponentId == -1) {
									DelayedStateChange.setComponentHiddenClient(createdComponent.id);
								}
								continue;
							}
							if (opcode == 1004) {
								// setaspect
								isp -= 2;
								createdComponent.aspectWidth = scriptIntValues[isp];
								createdComponent.aspectHeight = scriptIntValues[isp + 1];
								InterfaceList.redraw(createdComponent);
								InterfaceList.update(createdComponent);
								if (createdComponent.type == 0) {
									InterfaceList.method531(createdComponent, false);
								}
								continue;
							}
							if (opcode == 1005) {
								isp--;
								createdComponent.noClickThrough = scriptIntValues[isp] == 1;
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
					Chat.addMessage(EMPTY_STRING, 0, CS_ERROR);
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + op, local14378);
			} else {
				@Pc(14385) JString local14385 = JString.allocate(30);
				local14385.method3113(aClass100_928).method3113(clientScript.name);
				for (cycles = fp - 1; cycles >= 0; cycles--) {
					local14385.method3113(aClass100_253).method3113(callStack[cycles].script.name);
				}
				if (op == 40) {
					cycles = intOperands[pc];
					local14385.method3113(aClass100_802).method3113(JString.parseInt(cycles));
				}
				if (client.modeWhere != 0) {
					Chat.addMessage(EMPTY_STRING, 0, JString.concatenate(new JString[] { aClass100_780, clientScript.name}));
				}
				TracingException.report("CS2 - scr:" + clientScript.nodeId + " op:" + op + new String(local14385.method3148()), local14378);
			}
		}
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(Z)V")
	public static void method1807() {
		for (@Pc(11) int local11 = 0; local11 < 100; local11++) {
			InterfaceList.aBooleanArray100[local11] = true;
		}
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "o", descriptor = "(I)V")
	public static void method2218() {
		@Pc(8) int local8 = method4047();
		if (local8 == 0) {
			aByteArrayArrayArray15 = null;
			method3993(0);
		} else if (local8 == 1) {
			method960((byte) 0);
			method3993(512);
			method2608();
		} else {
			method960((byte) (anInt3325 - 4 & 0xFF));
			method3993(2);
		}
	}

	@OriginalMember(owner = "runetek4.client!q", name = "a", descriptor = "(IIIIIIBI)V")
	public static void method1026(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6) {
		if (arg5 < 128 || arg2 < 128 || arg5 > 13056 || arg2 > 13056) {
			anInt548 = -1;
			anInt1951 = -1;
			return;
		}
		@Pc(38) int local38 = SceneGraph.getTileHeight(Player.plane, arg5, arg2) - arg3;
		@Pc(42) int local42 = arg2 - Camera.renderZ;
		@Pc(46) int local46 = local38 - Camera.renderY;
		@Pc(50) int local50 = arg5 - Camera.renderX;
		@Pc(54) int local54 = MathUtils.sin[Camera.cameraPitch];
		@Pc(58) int local58 = MathUtils.cos[Camera.cameraPitch];
		@Pc(62) int local62 = MathUtils.sin[Camera.cameraYaw];
		@Pc(66) int local66 = MathUtils.cos[Camera.cameraYaw];
		@Pc(76) int local76 = local50 * local66 + local62 * local42 >> 16;
		@Pc(87) int local87 = local42 * local66 - local62 * local50 >> 16;
		@Pc(89) int local89 = local76;
		@Pc(99) int local99 = local58 * local46 - local87 * local54 >> 16;
		@Pc(113) int local113 = local87 * local58 + local46 * local54 >> 16;
		if (local113 < 50) {
			anInt548 = -1;
			anInt1951 = -1;
		} else if (GlRenderer.enabled) {
			@Pc(150) int local150 = arg1 * 512 >> 8;
			anInt1951 = local150 * local89 / local113 + arg0;
			@Pc(164) int local164 = arg6 * 512 >> 8;
			anInt548 = local164 * local99 / local113 + arg4;
		} else {
			anInt1951 = (local89 << 9) / local113 + arg0;
			anInt548 = (local99 << 9) / local113 + arg4;
		}
	}

	@OriginalMember(owner = "runetek4.client!ja", name = "a", descriptor = "(IIIIIZ)V")
	public static void method2314(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4) {
		if (arg0 < 1) {
			arg0 = 1;
		}
		if (arg2 < 1) {
			arg2 = 1;
		}
		if (GlRenderer.enabled) {
			@Pc(25) int local25 = arg2 - 334;
			if (local25 < 0) {
				local25 = 0;
			} else if (local25 > 100) {
				local25 = 100;
			}
			@Pc(51) int local51 = local25 * (aShort9 - aShort25) / 100 + aShort25;
			if (aShort22 > local51) {
				local51 = aShort22;
			} else if (aShort1 < local51) {
				local51 = aShort1;
			}
			@Pc(73) int local73 = local51 * arg2 * 512 / (arg0 * 334);
			@Pc(115) int local115;
			@Pc(122) int local122;
			@Pc(86) short local86;
			if (local73 < aShort12) {
				local86 = aShort12;
				local51 = arg0 * 334 * local86 / (arg2 * 512);
				if (aShort1 < local51) {
					local51 = aShort1;
					local115 = arg2 * 512 * local51 / (local86 * 334);
					local122 = (arg0 - local115) / 2;
					if (arg4) {
						GlRaster.method1177();
						GlRaster.fillRect(arg3, arg1, local122, arg2, 0);
						GlRaster.fillRect(arg0 + arg3 - local122, arg1, local122, arg2, 0);
					}
					arg3 += local122;
					arg0 -= local122 * 2;
				}
			} else if (aShort21 < local73) {
				local86 = aShort21;
				local51 = local86 * arg0 * 334 / (arg2 * 512);
				if (aShort22 > local51) {
					local51 = aShort22;
					local115 = local86 * arg0 * 334 / (local51 * 512);
					local122 = (arg2 - local115) / 2;
					if (arg4) {
						GlRaster.method1177();
						GlRaster.fillRect(arg3, arg1, arg0, local122, 0);
						GlRaster.fillRect(arg3, arg1 + arg2 - local122, arg0, local122, 0);
					}
					arg2 -= local122 * 2;
					arg1 += local122;
				}
			}
			anInt5029 = local51 * arg2 / 334;
		}
		anInt4055 = (short) arg0;
		anInt5377 = (short) arg2;
		anInt773 = arg1;
		anInt983 = arg3;
	}

	@OriginalMember(owner = "runetek4.client!lc", name = "a", descriptor = "(IIIIIII)V")
	public static void drawOverheads(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		OverHeadChat.sizes = 0;
		@Pc(5) int local5;
		@Pc(642) int local642;
		@Pc(74) int local74;
		@Pc(265) int local265;
		@Pc(310) int local310;
		@Pc(359) int local359;
		@Pc(639) int local639;
		for (local5 = -1; local5 < PlayerList.playerCount + NpcList.npcCount; local5++) {
			@Pc(17) PathingEntity local17;
			if (local5 == -1) {
				local17 = PlayerList.self;
			} else if (PlayerList.playerCount > local5) {
				local17 = PlayerList.players[PlayerList.playerIds[local5]];
			} else {
				local17 = NpcList.npcs[NpcList.npcIds[local5 - PlayerList.playerCount]];
			}
			if (local17 != null && local17.isVisible()) {
				@Pc(58) NpcType local58;
				if (local17 instanceof Npc) {
					local58 = ((Npc) local17).type;
					if (local58.multinpc != null) {
						local58 = local58.getMultiNPC();
					}
					if (local58 == null) {
						continue;
					}
				}
				@Pc(161) int local161;
				if (local5 >= PlayerList.playerCount) {
					local58 = ((Npc) local17).type;
					if (local58.multinpc != null) {
						local58 = local58.getMultiNPC();
					}
					if (local58.headIcon >= 0 && Sprites.headiconPrayers.length > local58.headIcon) {
						if (local58.overlayheight == -1) {
							local265 = local17.height() + 15;
						} else {
							local265 = local58.overlayheight + 15;
						}
						setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local265, arg1 >> 1);
						if (anInt1951 > -1) {
							Sprites.headiconPrayers[local58.headIcon].render(arg2 + anInt1951 - 12, arg0 + -30 - -anInt548);
						}
					}
					@Pc(308) MapMarker[] local308 = MiniMap.hintMapMarkers;
					for (local310 = 0; local310 < local308.length; local310++) {
						@Pc(322) MapMarker local322 = local308[local310];
						if (local322 != null && local322.type == 1 && local322.actorTargetId == NpcList.npcIds[local5 - PlayerList.playerCount] && client.loop % 20 < 10) {
							if (local58.overlayheight == -1) {
								local359 = local17.height() + 15;
							} else {
								local359 = local58.overlayheight + 15;
							}
							setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local359, arg1 >> 1);
							if (anInt1951 > -1) {
								Sprites.headhints[local322.anInt4048].render(arg2 + anInt1951 - 12, anInt548 + -28 + arg0);
							}
						}
					}
				} else {
					local74 = 30;
					@Pc(77) Player local77 = (Player) local17;
					if (local77.anInt1669 != -1 || local77.anInt1649 != -1) {
						setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local17.height() + 15, arg1 >> 1);
						if (anInt1951 > -1) {
							if (local77.anInt1669 != -1) {
								Sprites.headiconPks[local77.anInt1669].render(anInt1951 + arg2 - 12, arg0 + -30 + anInt548);
								local74 += 25;
							}
							if (local77.anInt1649 != -1) {
								Sprites.headiconPrayers[local77.anInt1649].render(arg2 + anInt1951 - 12, arg0 - (-anInt548 + local74));
								local74 += 25;
							}
						}
					}
					if (local5 >= 0) {
						@Pc(159) MapMarker[] local159 = MiniMap.hintMapMarkers;
						for (local161 = 0; local161 < local159.length; local161++) {
							@Pc(173) MapMarker local173 = local159[local161];
							if (local173 != null && local173.type == 10 && PlayerList.playerIds[local5] == local173.actorTargetId) {
								setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local17.height() + 15, arg1 >> 1);
								if (anInt1951 > -1) {
									Sprites.headhints[local173.anInt4048].render(arg2 + anInt1951 - 12, arg0 + (anInt548 - local74));
								}
							}
						}
					}
				}
				if (local17.chatMessage != null && (local5 >= PlayerList.playerCount || Chat.publicFilter == 0 || Chat.publicFilter == 3 || Chat.publicFilter == 1 && FriendList.contains(((Player) local17).username))) {
					setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local17.height(), arg1 >> 1);
					if (anInt1951 > -1 && OverHeadChat.sizes < OverHeadChat.capacity) {
						OverHeadChat.anIntArray389[OverHeadChat.sizes] = Fonts.b12Full.getStringWidth(local17.chatMessage) / 2;
						OverHeadChat.anIntArray387[OverHeadChat.sizes] = Fonts.b12Full.characterDefaultHeight;
						OverHeadChat.anIntArray385[OverHeadChat.sizes] = anInt1951;
						OverHeadChat.anIntArray392[OverHeadChat.sizes] = anInt548;
						OverHeadChat.colors[OverHeadChat.sizes] = local17.chatColor;
						OverHeadChat.effects[OverHeadChat.sizes] = local17.chatEffect;
						OverHeadChat.loops[OverHeadChat.sizes] = local17.chatLoops;
						OverHeadChat.messages[OverHeadChat.sizes] = local17.chatMessage;
						OverHeadChat.sizes++;
					}
				}
				if (local17.hitpointsBarVisibleUntil > client.loop) {
					@Pc(508) Sprite local508 = Sprites.hitbars[0];
					@Pc(512) Sprite local512 = Sprites.hitbars[1];
					if (local17 instanceof Npc) {
						@Pc(518) Npc local518 = (Npc) local17;
						@Pc(528) Sprite[] local528 = (Sprite[]) HitBarList.hitBars.get((long) local518.type.hitBarId);
						if (local528 == null) {
							local528 = SpriteLoader.loadAlphaSprites(local518.type.hitBarId, client.js5Archive8);
							if (local528 != null) {
								HitBarList.hitBars.put(local528, (long) local518.type.hitBarId);
							}
						}
						if (local528 != null && local528.length == 2) {
							local512 = local528[1];
							local508 = local528[0];
						}
						@Pc(571) NpcType local571 = local518.type;
						if (local571.overlayheight == -1) {
							local310 = local17.height();
						} else {
							local310 = local571.overlayheight;
						}
					} else {
						local310 = local17.height();
					}
					setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local508.height + local310 + 10, arg1 >> 1);
					if (anInt1951 > -1) {
						local161 = anInt1951 + arg2 - (local508.width >> 1);
						local359 = anInt548 + arg0 - 3;
						local508.render(local161, local359);
						local639 = local508.width * local17.hitpointsBar / 255;
						local642 = local508.height;
						if (GlRenderer.enabled) {
							GlRaster.method1183(local161, local359, local161 + local639, local359 + local642);
						} else {
							SoftwareRaster.method2498(local161, local359, local161 + local639, local642 + local359);
						}
						local512.render(local161, local359);
						if (GlRenderer.enabled) {
							GlRaster.setClip(arg2, arg0, arg1 + arg2, arg0 - -arg4);
						} else {
							SoftwareRaster.setClip(arg2, arg0, arg1 + arg2, arg4 + arg0);
						}
					}
				}
				for (local74 = 0; local74 < 4; local74++) {
					if (local17.hitVisibleUntil[local74] > client.loop) {
						if (local17 instanceof Npc) {
							@Pc(725) Npc local725 = (Npc) local17;
							@Pc(728) NpcType local728 = local725.type;
							if (local728.overlayheight == -1) {
								local265 = local17.height() / 2;
							} else {
								local265 = local728.overlayheight / 2;
							}
						} else {
							local265 = local17.height() / 2;
						}
						setOverheadScreenCoordinateOffsets(arg4 >> 1, arg3, local17, arg5, local265, arg1 >> 1);
						if (anInt1951 > -1) {
							if (local74 == 1) {
								anInt548 -= 20;
							}
							if (local74 == 2) {
								anInt548 -= 10;
								anInt1951 -= 15;
							}
							if (local74 == 3) {
								anInt548 -= 10;
								anInt1951 += 15;
							}
							Sprites.hitmarks[local17.hitTypes[local74]].render(arg2 + anInt1951 - 12, arg0 + anInt548 - 12);
							Fonts.p11Full.renderCenter(JString.parseInt(local17.hitDamages[local74]), anInt1951 + arg2 - 1, anInt548 + 3 + arg0, 16777215, 0);
						}
					}
				}
			}
		}
		for (local5 = 0; local5 < OverHeadChat.sizes; local5++) {
			local74 = OverHeadChat.anIntArray392[local5];
			@Pc(859) int local859 = OverHeadChat.anIntArray385[local5];
			local310 = OverHeadChat.anIntArray387[local5];
			local265 = OverHeadChat.anIntArray389[local5];
			@Pc(869) boolean local869 = true;
			while (local869) {
				local869 = false;
				for (local359 = 0; local359 < local5; local359++) {
					if (OverHeadChat.anIntArray392[local359] - OverHeadChat.anIntArray387[local359] < local74 + 2 && local74 - local310 < OverHeadChat.anIntArray392[local359] - -2 && local859 - local265 < OverHeadChat.anIntArray385[local359] + OverHeadChat.anIntArray389[local359] && OverHeadChat.anIntArray385[local359] - OverHeadChat.anIntArray389[local359] < local265 + local859 && OverHeadChat.anIntArray392[local359] - OverHeadChat.anIntArray387[local359] < local74) {
						local74 = OverHeadChat.anIntArray392[local359] - OverHeadChat.anIntArray387[local359];
						local869 = true;
					}
				}
			}
			anInt1951 = OverHeadChat.anIntArray385[local5];
			anInt548 = OverHeadChat.anIntArray392[local5] = local74;
			@Pc(962) JString local962 = OverHeadChat.messages[local5];
			if (VarpDomain.chatEffectsDisabled == 0) {
				local639 = 16776960;
				if (OverHeadChat.colors[local5] < 6) {
					local639 = OverHeadChat.CHAT_COLORS[OverHeadChat.colors[local5]];
				}
				if (OverHeadChat.colors[local5] == 6) {
					local639 = anInt3325 % 20 >= 10 ? 16776960 : 16711680;
				}
				if (OverHeadChat.colors[local5] == 7) {
					local639 = anInt3325 % 20 < 10 ? 255 : 65535;
				}
				if (OverHeadChat.colors[local5] == 8) {
					local639 = anInt3325 % 20 >= 10 ? 8454016 : 45056;
				}
				if (OverHeadChat.colors[local5] == 9) {
					local642 = 150 - OverHeadChat.loops[local5];
					if (local642 < 50) {
						local639 = local642 * 1280 + 16711680;
					} else if (local642 < 100) {
						local639 = 16776960 + 16384000 - local642 * 327680;
					} else if (local642 < 150) {
						local639 = local642 * 5 + 65280 - 500;
					}
				}
				if (OverHeadChat.colors[local5] == 10) {
					local642 = 150 - OverHeadChat.loops[local5];
					if (local642 < 50) {
						local639 = local642 * 5 + 16711680;
					} else if (local642 < 100) {
						local639 = 16711935 - (local642 - 50) * 327680;
					} else if (local642 < 150) {
						local639 = local642 * 327680 + 255 + 500 - local642 * 5 - 32768000;
					}
				}
				if (OverHeadChat.colors[local5] == 11) {
					local642 = 150 - OverHeadChat.loops[local5];
					if (local642 < 50) {
						local639 = 16777215 - local642 * 327685;
					} else if (local642 < 100) {
						local639 = local642 * 327685 + 65280 - 16384250;
					} else if (local642 < 150) {
						local639 = 16777215 + 32768000 - local642 * 327680;
					}
				}
				if (OverHeadChat.effects[local5] == 0) {
					Fonts.b12Full.renderCenter(local962, anInt1951 + arg2, arg0 + anInt548, local639, 0);
				}
				if (OverHeadChat.effects[local5] == 1) {
					Fonts.b12Full.renderWave(local962, arg2 + anInt1951, anInt548 + arg0, local639, anInt3325);
				}
				if (OverHeadChat.effects[local5] == 2) {
					Fonts.b12Full.renderWave2(local962, arg2 + anInt1951, arg0 - -anInt548, local639, anInt3325);
				}
				if (OverHeadChat.effects[local5] == 3) {
					Fonts.b12Full.renderShake(local962, arg2 + anInt1951, anInt548 + arg0, local639, anInt3325, 150 - OverHeadChat.loops[local5]);
				}
				if (OverHeadChat.effects[local5] == 4) {
					local642 = (150 - OverHeadChat.loops[local5]) * (Fonts.b12Full.getStringWidth(local962) + 100) / 150;
					if (GlRenderer.enabled) {
						GlRaster.method1183(anInt1951 + arg2 - 50, arg0, anInt1951 + arg2 + 50, arg4 + arg0);
					} else {
						SoftwareRaster.method2498(arg2 + anInt1951 - 50, arg0, anInt1951 + arg2 + 50, arg4 + arg0);
					}
					Fonts.b12Full.renderLeft(local962, arg2 + anInt1951 + 50 - local642, arg0 + anInt548, local639, 0);
					if (GlRenderer.enabled) {
						GlRaster.setClip(arg2, arg0, arg1 + arg2, arg4 + arg0);
					} else {
						SoftwareRaster.setClip(arg2, arg0, arg2 + arg1, arg0 + arg4);
					}
				}
				if (OverHeadChat.effects[local5] == 5) {
					@Pc(1372) int local1372 = 0;
					local642 = 150 - OverHeadChat.loops[local5];
					if (GlRenderer.enabled) {
						GlRaster.method1183(arg2, anInt548 + arg0 - Fonts.b12Full.characterDefaultHeight - 1, arg1 + arg2, arg0 + anInt548 + 5);
					} else {
						SoftwareRaster.method2498(arg2, anInt548 + arg0 - Fonts.b12Full.characterDefaultHeight - 1, arg2 + arg1, anInt548 + arg0 + 5);
					}
					if (local642 < 25) {
						local1372 = local642 - 25;
					} else if (local642 > 125) {
						local1372 = local642 - 125;
					}
					Fonts.b12Full.renderCenter(local962, anInt1951 + arg2, local1372 + arg0 + anInt548, local639, 0);
					if (GlRenderer.enabled) {
						GlRaster.setClip(arg2, arg0, arg2 + arg1, arg0 + arg4);
					} else {
						SoftwareRaster.setClip(arg2, arg0, arg2 + arg1, arg0 + arg4);
					}
				}
			} else {
				Fonts.b12Full.renderCenter(local962, arg2 + anInt1951, arg0 + anInt548, 16776960, 0);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(I)V")
	public static void method3711() {
		for (@Pc(7) int local7 = 0; local7 < 104; local7++) {
			for (@Pc(14) int local14 = 0; local14 < 104; local14++) {
				anIntArrayArray6[local7][local14] = 0;
			}
		}
	}

	@OriginalMember(owner = "client!cn", name = "b", descriptor = "(ZI)V")
	public static void pushPlayers(@OriginalArg(0) boolean arg0) {
		@Pc(3) int local3 = PlayerList.playerCount;
		if (LoginManager.mapFlagX == PlayerList.self.xFine >> 7 && PlayerList.self.zFine >> 7 == LoginManager.mapFlagZ) {
			LoginManager.mapFlagX = 0;
		}
		if (arg0) {
			local3 = 1;
		}
		@Pc(28) int i;
		@Pc(39) Player player;
		@Pc(82) int stz;
		@Pc(182) int local182;
		@Pc(200) int local200;
		@Pc(214) int local214;
		@Pc(223) int local223;
		@Pc(106) int local106;
		for (i = 0; i < local3; i++) {
			if (arg0) {
				player = PlayerList.self;
			} else {
				player = PlayerList.players[PlayerList.playerIds[i]];
			}
			if (player != null && player.isVisible()) {
				@Pc(55) int local55 = player.getSize();
				@Pc(77) int stx;
				if (local55 == 1) {
					if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
						stx = player.xFine >> 7;
						stz = player.zFine >> 7;
						if (stx >= 0 && stx < 104 && stz >= 0 && stz < 104) {
							local106 = anIntArrayArray6[stx][stz]++;
						}
					}
				} else if (((local55 & 0x1) != 0 || (player.xFine & 0x7F) == 0 && (player.zFine & 0x7F) == 0) && ((local55 & 0x1) != 1 || (player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64)) {
					stx = player.xFine - local55 * 64 >> 7;
					stz = player.zFine - local55 * 64 >> 7;
					local182 = player.getSize() + stx;
					if (local182 > 104) {
						local182 = 104;
					}
					if (stx < 0) {
						stx = 0;
					}
					local200 = stz + player.getSize();
					if (stz < 0) {
						stz = 0;
					}
					if (local200 > 104) {
						local200 = 104;
					}
					for (local214 = stx; local214 < local182; local214++) {
						for (local223 = stz; local223 < local200; local223++) {
							local106 = anIntArrayArray6[local214][local223]++;
						}
					}
				}
			}
		}
		label220: for (i = 0; i < local3; i++) {
			@Pc(272) long id;
			if (arg0) {
				player = PlayerList.self;
				id = 8791798054912L;
			} else {
				player = PlayerList.players[PlayerList.playerIds[i]];
				id = (long) PlayerList.playerIds[i] << 32;
			}
			if (player != null && player.isVisible()) {
				player.lowMemory = false;
				if ((Preferences.manyIdleAnimations && PlayerList.playerCount > 200 || PlayerList.playerCount > 50) && !arg0 && player.movementSeqId == player.getBasType().readyanim) {
					player.lowMemory = true;
				}
				stz = player.getSize();
				if (stz == 1) {
					if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
						local182 = player.xFine >> 7;
						local200 = player.zFine >> 7;
						if (local182 < 0 || local182 >= 104 || local200 < 0 || local200 >= 104) {
							continue;
						}
						if (anIntArrayArray6[local182][local200] > 1) {
							local106 = anIntArrayArray6[local182][local200]--;
							continue;
						}
					}
				} else if ((stz & 0x1) == 0 && (player.xFine & 0x7F) == 0 && (player.zFine & 0x7F) == 0 || (stz & 0x1) == 1 && (player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 0) {
					local182 = player.xFine - stz * 64 >> 7;
					local214 = stz + local182;
					local200 = player.zFine - stz * 64 >> 7;
					if (local214 > 104) {
						local214 = 104;
					}
					if (local182 < 0) {
						local182 = 0;
					}
					local223 = stz + local200;
					if (local200 < 0) {
						local200 = 0;
					}
					@Pc(468) boolean local468 = true;
					if (local223 > 104) {
						local223 = 104;
					}
					@Pc(476) int local476;
					@Pc(485) int local485;
					for (local476 = local182; local476 < local214; local476++) {
						for (local485 = local200; local485 < local223; local485++) {
							if (anIntArrayArray6[local476][local485] <= 1) {
								local468 = false;
								break;
							}
						}
					}
					if (local468) {
						local476 = local182;
						while (true) {
							if (local476 >= local214) {
								continue label220;
							}
							for (local485 = local200; local485 < local223; local485++) {
								local106 = anIntArrayArray6[local476][local485]--;
							}
							local476++;
						}
					}
				}
				if (player.attachment == null || client.loop < player.attachmentSetAt || player.attachmentResetAt <= client.loop) {
					player.anInt3424 = SceneGraph.getTileHeight(Player.plane, player.xFine, player.zFine);
					SceneGraph.addTemporary(Player.plane, player.xFine, player.zFine, player.anInt3424, (stz - 1) * 64 + 60, player, player.anInt3381, id, player.seqStretches);
				} else {
					player.lowMemory = false;
					player.anInt3424 = SceneGraph.getTileHeight(Player.plane, player.xFine, player.zFine);
					addTemporary(Player.plane, player.xFine, player.zFine, player.anInt3424, player, player.anInt3381, id, player.atachmentX0, player.attachmentZ0, player.attachmentX1, player.attachmentZ1);
				}
			}
		}
	}

	@OriginalMember(owner = "client!nk", name = "c", descriptor = "(IZ)V")
	public static void pushNpcs(@OriginalArg(1) boolean arg0) {
		@Pc(7) int i;
		@Pc(16) Npc npc;
		@Pc(107) int npcSize;
		@Pc(113) int x;
		@Pc(133) int z;
		@Pc(149) int local149;
		@Pc(158) int local158;
		@Pc(171) int local171;
		for (i = 0; i < NpcList.npcCount; i++) {
			npc = NpcList.npcs[NpcList.npcIds[i]];
			if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.isMultiNpcValid()) {
				@Pc(42) int npcSize2 = npc.getSize();
				@Pc(97) int local97;
				if (npcSize2 == 1) {
					if ((npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
						local97 = npc.xFine >> 7;
						npcSize = npc.zFine >> 7;
						if (local97 >= 0 && local97 < 104 && npcSize >= 0 && npcSize < 104) {
							local171 = anIntArrayArray6[local97][npcSize]++;
						}
					}
				} else if (((npcSize2 & 0x1) != 0 || (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0) && ((npcSize2 & 0x1) != 1 || (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64)) {
					local97 = npc.xFine - npcSize2 * 64 >> 7;
					npcSize = npc.zFine - npcSize2 * 64 >> 7;
					x = npc.getSize() + local97;
					if (local97 < 0) {
						local97 = 0;
					}
					if (x > 104) {
						x = 104;
					}
					z = npcSize + npc.getSize();
					if (npcSize < 0) {
						npcSize = 0;
					}
					if (z > 104) {
						z = 104;
					}
					for (local149 = local97; local149 < x; local149++) {
						for (local158 = npcSize; local158 < z; local158++) {
							local171 = anIntArrayArray6[local149][local158]++;
						}
					}
				}
			}
		}
		label200: for (i = 0; i < NpcList.npcCount; i++) {
			npc = NpcList.npcs[NpcList.npcIds[i]];
			@Pc(262) long bitset = (long) NpcList.npcIds[i] << 32 | 0x20000000L;
			if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.isMultiNpcValid()) {
				npcSize = npc.getSize();
				if (npcSize == 1) {
					if ((npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
						x = npc.xFine >> 7;
						z = npc.zFine >> 7;
						if (x < 0 || x >= 104 || z < 0 || z >= 104) {
							continue;
						}
						if (anIntArrayArray6[x][z] > 1) {
							local171 = anIntArrayArray6[x][z]--;
							continue;
						}
					}
				} else if ((npcSize & 0x1) == 0 && (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0 || (npcSize & 0x1) == 1 && (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
					x = npc.xFine - npcSize * 64 >> 7;
					z = npc.zFine - npcSize * 64 >> 7;
					local158 = z + npcSize;
					if (z < 0) {
						z = 0;
					}
					@Pc(368) boolean local368 = true;
					local149 = x + npcSize;
					if (local158 > 104) {
						local158 = 104;
					}
					if (x < 0) {
						x = 0;
					}
					if (local149 > 104) {
						local149 = 104;
					}
					@Pc(396) int local396;
					@Pc(401) int local401;
					for (local396 = x; local396 < local149; local396++) {
						for (local401 = z; local401 < local158; local401++) {
							if (anIntArrayArray6[local396][local401] <= 1) {
								local368 = false;
								break;
							}
						}
					}
					if (local368) {
						local396 = x;
						while (true) {
							if (local396 >= local149) {
								continue label200;
							}
							for (local401 = z; local401 < local158; local401++) {
								local171 = anIntArrayArray6[local396][local401]--;
							}
							local396++;
						}
					}
				}
				if (!npc.type.active) {
					bitset |= Long.MIN_VALUE;
				}
				npc.anInt3424 = SceneGraph.getTileHeight(Player.plane, npc.xFine, npc.zFine);
				SceneGraph.addTemporary(Player.plane, npc.xFine, npc.zFine, npc.anInt3424, npcSize * 64 + 60 - 64, npc, npc.anInt3381, bitset, npc.seqStretches);
			}
		}
	}

	@OriginalMember(owner = "client!pk", name = "i", descriptor = "(I)V")
	public static void updateSceneProjectiles() {
		for (@Pc(16) ProjAnimNode proj = (ProjAnimNode) SceneGraph.projectiles.head(); proj != null; proj = (ProjAnimNode) SceneGraph.projectiles.next()) {
			@Pc(21) ProjectileAnimation projAnim = proj.value;
			if (Player.plane != projAnim.currentPlane || projAnim.lastCycle < client.loop) {
				proj.unlink();
			} else if (client.loop >= projAnim.firstCycle) {
				if (projAnim.target > 0) {
					@Pc(54) Npc npc = NpcList.npcs[projAnim.target - 1];
					if (npc != null && npc.xFine >= 0 && npc.xFine < 13312 && npc.zFine >= 0 && npc.zFine < 13312) {
						projAnim.setTarget(npc.zFine, client.loop, SceneGraph.getTileHeight(projAnim.currentPlane, npc.xFine, npc.zFine) - projAnim.baseZ, npc.xFine);
					}
				}
				if (projAnim.target < 0) {
					@Pc(102) int index = -projAnim.target - 1;
					@Pc(107) Player player;
					if (PlayerList.selfId == index) {
						player = PlayerList.self;
					} else {
						player = PlayerList.players[index];
					}
					if (player != null && player.xFine >= 0 && player.xFine < 13312 && player.zFine >= 0 && player.zFine < 13312) {
						projAnim.setTarget(player.zFine, client.loop, SceneGraph.getTileHeight(projAnim.currentPlane, player.xFine, player.zFine) - projAnim.baseZ, player.xFine);
					}
				}
				projAnim.update(Protocol.sceneDelta);
				SceneGraph.addTemporary(Player.plane, (int) projAnim.x, (int) projAnim.y, (int) projAnim.z, 60, projAnim, projAnim.yaw, -1L, false);
			}
		}
	}

	@OriginalMember(owner = "client!u", name = "a", descriptor = "(Z)V")
	public static void updateSpotAnims() {
		for (@Pc(9) SpotAnimEntity entity = (SpotAnimEntity) SceneGraph.spotanims.head(); entity != null; entity = (SpotAnimEntity) SceneGraph.spotanims.next()) {
			@Pc(15) SpotAnim spotAnim = entity.aClass8_Sub2_1;
			if (spotAnim.level != Player.plane || spotAnim.seqComplete) {
				entity.unlink();
			} else if (spotAnim.startCycle <= client.loop) {
				spotAnim.update(Protocol.sceneDelta);
				if (spotAnim.seqComplete) {
					entity.unlink();
				} else {
					SceneGraph.addTemporary(spotAnim.level, spotAnim.x, spotAnim.z, spotAnim.anInt599, 60, spotAnim, 0, -1L, false);
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!tc", name = "a", descriptor = "(B)I")
	public static int method4047() {
		if (neverRemoveRoofs) {
			return 0;
		} else if (SceneGraph.allLevelsAreVisible()) {
			return Preferences.roofsVisible ? 2 : 1;
		} else {
			return 1;
		}
	}

	@OriginalMember(owner = "runetek4.client!ok", name = "a", descriptor = "(IIB)Lclient!ce;")
	public static SecondaryLinkedList method3333(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(9) SecondaryLinkedList local9 = new SecondaryLinkedList();
		for (@Pc(14) Map local14 = (Map) MapList.areas.head(); local14 != null; local14 = (Map) MapList.areas.next()) {
			if (local14.valid && local14.containsSource(arg1, arg0)) {
				local9.addTail(local14);
			}
		}
		return local9;
	}

	@OriginalMember(owner = "client!cn", name = "a", descriptor = "(BB)V")
	public static void method960(@OriginalArg(0) byte arg0) {
		if (aByteArrayArrayArray15 == null) {
			aByteArrayArrayArray15 = new byte[4][104][104];
		}
		for (@Pc(20) int local20 = 0; local20 < 4; local20++) {
			for (@Pc(25) int local25 = 0; local25 < 104; local25++) {
				for (@Pc(32) int local32 = 0; local32 < 104; local32++) {
					aByteArrayArrayArray15[local20][local25][local32] = arg0;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!sm", name = "a", descriptor = "(II)V")
	public static void method3993(@OriginalArg(0) int arg0) {
		anIntArray338 = new int[arg0];
		anIntArray518 = new int[arg0];
		anIntArray476 = new int[arg0];
		anIntArray134 = new int[arg0];
		anIntArray205 = new int[arg0];
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "f", descriptor = "(B)V")
	public static void method2608() {
		@Pc(7) int local7 = 0;
		for (@Pc(23) int local23 = 0; local23 < 104; local23++) {
			for (@Pc(30) int local30 = 0; local30 < 104; local30++) {
				if (method4348(true, local23, local30, SceneGraph.tiles, local7)) {
					local7++;
				}
				if (local7 >= 512) {
					return;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(BZII[[[Lclient!bj;I)Z")
	public static boolean method4348(@OriginalArg(1) boolean arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Tile[][][] arg3, @OriginalArg(5) int arg4) {
		@Pc(14) byte local14 = arg0 ? 1 : (byte) (anInt3325 & 0xFF);
		if (local14 == aByteArrayArrayArray15[Player.plane][arg1][arg2]) {
			return false;
		} else if ((SceneGraph.renderFlags[Player.plane][arg1][arg2] & 0x4) == 0) {
			return false;
		} else {
			@Pc(47) int local47 = 0;
			@Pc(49) byte local49 = 0;
			PathFinder.queueX[0] = arg1;
			@Pc(69) int local69 = local49 + 1;
			PathFinder.queueZ[0] = arg2;
			aByteArrayArrayArray15[Player.plane][arg1][arg2] = local14;
			while (local47 != local69) {
				@Pc(94) int local94 = PathFinder.queueX[local47] >> 16 & 0xFF;
				@Pc(102) int local102 = PathFinder.queueX[local47] >> 24 & 0xFF;
				@Pc(108) int local108 = PathFinder.queueX[local47] & 0xFFFF;
				@Pc(116) int local116 = PathFinder.queueZ[local47] >> 16 & 0xFF;
				@Pc(122) int local122 = PathFinder.queueZ[local47] & 0xFFFF;
				local47 = local47 + 1 & 0xFFF;
				@Pc(130) boolean local130 = false;
				@Pc(132) boolean local132 = false;
				if ((SceneGraph.renderFlags[Player.plane][local108][local122] & 0x4) == 0) {
					local130 = true;
				}
				@Pc(150) int local150;
				@Pc(191) int local191;
				label238: for (local150 = Player.plane + 1; local150 <= 3; local150++) {
					if ((SceneGraph.renderFlags[local150][local108][local122] & 0x8) == 0) {
						@Pc(227) int local227;
						@Pc(358) int local358;
						if (local130 && arg3[local150][local108][local122] != null) {
							if (arg3[local150][local108][local122].wall != null) {
								local191 = SceneGraph.method2251(local94);
								if (arg3[local150][local108][local122].wall.typeA == local191 || arg3[local150][local108][local122].wall.typeB == local191) {
									continue;
								}
								if (local102 != 0) {
									local227 = SceneGraph.method2251(local102);
									if (local227 == arg3[local150][local108][local122].wall.typeA || arg3[local150][local108][local122].wall.typeB == local227) {
										continue;
									}
								}
								if (local116 != 0) {
									local227 = SceneGraph.method2251(local116);
									if (local227 == arg3[local150][local108][local122].wall.typeA || local227 == arg3[local150][local108][local122].wall.typeB) {
										continue;
									}
								}
							}
							if (arg3[local150][local108][local122].scenery != null) {
								for (local191 = 0; local191 < arg3[local150][local108][local122].sceneryLen; local191++) {
									local227 = (int) (arg3[local150][local108][local122].scenery[local191].key >> 14 & 0x3FL);
									if (local227 == 21) {
										local227 = 19;
									}
									@Pc(352) int local352 = (int) (arg3[local150][local108][local122].scenery[local191].key >> 20 & 0x3L);
									local358 = local227 | local352 << 6;
									if (local358 == local94 || local102 != 0 && local358 == local102 || local116 != 0 && local116 == local358) {
										continue label238;
									}
								}
							}
						}
						local132 = true;
						@Pc(395) Tile local395 = arg3[local150][local108][local122];
						if (local395 != null && local395.sceneryLen > 0) {
							for (local227 = 0; local227 < local395.sceneryLen; local227++) {
								@Pc(418) Scenery local418 = local395.scenery[local227];
								if (local418.xMax != local418.xMin || local418.zMax != local418.zMin) {
									for (local358 = local418.xMin; local358 <= local418.xMax; local358++) {
										for (@Pc(450) int local450 = local418.zMin; local450 <= local418.zMax; local450++) {
											aByteArrayArrayArray15[local150][local358][local450] = local14;
										}
									}
								}
							}
						}
						aByteArrayArrayArray15[local150][local108][local122] = local14;
					}
				}
				if (local132) {
					if (SceneGraph.tileHeights[Player.plane + 1][local108][local122] > anIntArray205[arg4]) {
						anIntArray205[arg4] = SceneGraph.tileHeights[Player.plane + 1][local108][local122];
					}
					local150 = local108 << 7;
					if (local150 < anIntArray338[arg4]) {
						anIntArray338[arg4] = local150;
					} else if (anIntArray518[arg4] < local150) {
						anIntArray518[arg4] = local150;
					}
					local191 = local122 << 7;
					if (anIntArray476[arg4] > local191) {
						anIntArray476[arg4] = local191;
					} else if (anIntArray134[arg4] < local191) {
						anIntArray134[arg4] = local191;
					}
				}
				if (!local130) {
					if (local108 >= 1 && aByteArrayArrayArray15[Player.plane][local108 - 1][local122] != local14) {
						PathFinder.queueX[local69] = local108 - 1 | 0x120000 | 0xD3000000;
						PathFinder.queueZ[local69] = local122 | 0x130000;
						local69 = local69 + 1 & 0xFFF;
						aByteArrayArrayArray15[Player.plane][local108 - 1][local122] = local14;
					}
					local122++;
					if (local122 < 104) {
						if (local108 - 1 >= 0 && local14 != aByteArrayArrayArray15[Player.plane][local108 - 1][local122] && (SceneGraph.renderFlags[Player.plane][local108][local122] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][local108 - 1][local122 - 1] & 0x4) == 0) {
							PathFinder.queueX[local69] = 0x52000000 | 0x120000 | local108 - 1;
							PathFinder.queueZ[local69] = local122 | 0x130000;
							aByteArrayArrayArray15[Player.plane][local108 - 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
						if (local14 != aByteArrayArrayArray15[Player.plane][local108][local122]) {
							PathFinder.queueX[local69] = local108 | 0x13000000 | 0x520000;
							PathFinder.queueZ[local69] = local122 | 0x530000;
							local69 = local69 + 1 & 0xFFF;
							aByteArrayArrayArray15[Player.plane][local108][local122] = local14;
						}
						if (local108 + 1 < 104 && aByteArrayArrayArray15[Player.plane][local108 + 1][local122] != local14 && (SceneGraph.renderFlags[Player.plane][local108][local122] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][local108 + 1][local122 - 1] & 0x4) == 0) {
							PathFinder.queueX[local69] = 0x92000000 | 0x520000 | local108 + 1;
							PathFinder.queueZ[local69] = local122 | 0x530000;
							aByteArrayArrayArray15[Player.plane][local108 + 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
					}
					local122--;
					if (local108 + 1 < 104 && local14 != aByteArrayArrayArray15[Player.plane][local108 + 1][local122]) {
						PathFinder.queueX[local69] = local108 + 1 | 0x920000 | 0x53000000;
						PathFinder.queueZ[local69] = local122 | 0x930000;
						aByteArrayArrayArray15[Player.plane][local108 + 1][local122] = local14;
						local69 = local69 + 1 & 0xFFF;
					}
					local122--;
					if (local122 >= 0) {
						if (local108 - 1 >= 0 && aByteArrayArrayArray15[Player.plane][local108 - 1][local122] != local14 && (SceneGraph.renderFlags[Player.plane][local108][local122] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][local108 - 1][local122 + 1] & 0x4) == 0) {
							PathFinder.queueX[local69] = local108 - 1 | 0xD20000 | 0x12000000;
							PathFinder.queueZ[local69] = local122 | 0xD30000;
							aByteArrayArrayArray15[Player.plane][local108 - 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
						if (local14 != aByteArrayArrayArray15[Player.plane][local108][local122]) {
							PathFinder.queueX[local69] = local108 | 0xD20000 | 0x93000000;
							PathFinder.queueZ[local69] = local122 | 0xD30000;
							local69 = local69 + 1 & 0xFFF;
							aByteArrayArrayArray15[Player.plane][local108][local122] = local14;
						}
						if (local108 + 1 < 104 && aByteArrayArrayArray15[Player.plane][local108 + 1][local122] != local14 && (SceneGraph.renderFlags[Player.plane][local108][local122] & 0x4) == 0 && (SceneGraph.renderFlags[Player.plane][local108 + 1][local122 + 1] & 0x4) == 0) {
							PathFinder.queueX[local69] = local108 + 1 | 0xD2000000 | 0x920000;
							PathFinder.queueZ[local69] = local122 | 0x930000;
							aByteArrayArrayArray15[Player.plane][local108 + 1][local122] = local14;
							local69 = local69 + 1 & 0xFFF;
						}
					}
				}
			}
			if (anIntArray205[arg4] != -1000000) {
				anIntArray205[arg4] += 10;
				anIntArray338[arg4] -= 50;
				anIntArray518[arg4] += 50;
				anIntArray134[arg4] += 50;
				anIntArray476[arg4] -= 50;
			}
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!nf", name = "a", descriptor = "(Lclient!na;BZ)V")
	public static void openUrl(@OriginalArg(0) JString arg0, @OriginalArg(2) boolean arg1) {
		if (!arg1) {
			try {
				GameShell.instance.getAppletContext().showDocument(arg0.method3127(GameShell.instance.getCodeBase()), "_top");
			} catch (@Pc(22) Exception local22) {
			}
			return;
		}
		if (GlRenderer.enabled && GameShell.openWindowJavaScript) {
			try {
				BrowserControl.call(GameShell.signLink.applet, "openjs", new Object[] { arg0.method3127(GameShell.instance.getCodeBase()).toString() });
				return;
			} catch (@Pc(48) Throwable local48) {
			}
		}
		try {
			GameShell.instance.getAppletContext().showDocument(arg0.method3127(GameShell.instance.getCodeBase()), "_blank");
		} catch (@Pc(59) Exception local59) {
		}
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(BIILclient!fe;III)V")
	public static void setOverheadScreenCoordinateOffsets(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) PathingEntity arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		method1026(arg5, arg1, arg2.zFine, arg4, arg0, arg2.xFine, arg3);
	}

	@OriginalMember(owner = "client!ed", name = "b", descriptor = "(II)Lclient!ba;")
	public static World getWorld(@OriginalArg(1) int arg0) {
		return WorldList.loaded && arg0 >= WorldList.minId && arg0 <= WorldList.maxId ? WorldList.worlds[arg0 - WorldList.minId] : null;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "()V")
	public static void method3858() {
		for (@Pc(1) int local1 = 0; local1 < SceneGraph.sceneryLen; local1++) {
			@Pc(8) Scenery local8 = SceneGraph.scenery[local1];
			SceneGraph.removeScenery(local8);
			SceneGraph.scenery[local1] = null;
		}
		SceneGraph.sceneryLen = 0;
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(Z)Lclient!na;")
	public static JString method479() {
		@Pc(8) JString local8 = aClass100_518;
		@Pc(10) JString local10 = JString.EMPTY;
		if (client.modeWhere != 0) {
			local8 = aClass100_365;
		}
		if (client.settings != null) {
			local10 = JString.concatenate(new JString[] {aClass100_687, client.settings});
		}
		return JString.concatenate(new JString[] {aClass100_424, local8, aClass100_886, JString.parseInt(client.language), aClass100_98, JString.parseInt(client.affiliate), local10, aClass100_268});
	}

	@OriginalMember(owner = "runetek4.client!ol", name = "a", descriptor = "(IIIILclient!th;IJIIII)Z")
	public static boolean addTemporary(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) Entity arg4, @OriginalArg(5) int arg5, @OriginalArg(6) long arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10) {
		return arg4 == null ? true : SceneGraph.addLoc(arg0, arg7, arg8, arg9 + 1 - arg7, arg10 - arg8 + 1, arg1, arg2, arg3, arg4, arg5, true, arg6);
	}

	@OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "(I)Z")
	public static boolean isShowingVideoAd() {
		if (client.objectTag) {
			try {
				return !((Boolean) SHOWINGVIDEOAD.browserControlCall(GameShell.signLink.applet));
			} catch (@Pc(21) Throwable local21) {
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!uh", name = "f", descriptor = "(I)V")
	public static void method4302() {
		if (method4047() != 2) {
			return;
		}
		@Pc(27) byte local27 = (byte) (anInt3325 - 4 & 0xFF);
		@Pc(31) int local31 = anInt3325 % 104;
		@Pc(33) int local33;
		@Pc(40) int local40;
		for (local33 = 0; local33 < 4; local33++) {
			for (local40 = 0; local40 < 104; local40++) {
				aByteArrayArrayArray15[local33][local31][local40] = local27;
			}
		}
		if (Player.plane == 3) {
			return;
		}
		for (local33 = 0; local33 < 2; local33++) {
			anIntArray205[local33] = -1000000;
			anIntArray338[local33] = 1000000;
			anIntArray518[local33] = 0;
			anIntArray476[local33] = 1000000;
			anIntArray134[local33] = 0;
		}
		if (Camera.cameraType != 1) {
			local33 = SceneGraph.getTileHeight(Player.plane, Camera.renderX, Camera.renderZ);
			if (local33 - Camera.renderY < 800 && (SceneGraph.renderFlags[Player.plane][Camera.renderX >> 7][Camera.renderZ >> 7] & 0x4) != 0) {
				method4348(false, Camera.renderX >> 7, Camera.renderZ >> 7, SceneGraph.tiles, 1);
			}
			return;
		}
		if ((SceneGraph.renderFlags[Player.plane][PlayerList.self.xFine >> 7][PlayerList.self.zFine >> 7] & 0x4) != 0) {
			method4348(false, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7, SceneGraph.tiles, 0);
		}
		if (Camera.cameraPitch >= 310) {
			return;
		}
		@Pc(135) int local135 = PlayerList.self.zFine >> 7;
		local40 = Camera.renderZ >> 7;
		@Pc(146) int local146;
		if (local40 < local135) {
			local146 = local135 - local40;
		} else {
			local146 = local40 - local135;
		}
		local33 = Camera.renderX >> 7;
		@Pc(162) int local162 = PlayerList.self.xFine >> 7;
		@Pc(174) int local174;
		if (local162 > local33) {
			local174 = local162 - local33;
		} else {
			local174 = local33 - local162;
		}
		@Pc(192) int local192;
		@Pc(186) int local186;
		if (local174 <= local146) {
			local186 = 32768;
			local192 = local174 * 65536 / local146;
			while (local40 != local135) {
				if (local40 < local135) {
					local40++;
				} else if (local40 > local135) {
					local40--;
				}
				if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
					method4348(false, local33, local40, SceneGraph.tiles, 1);
					break;
				}
				local186 += local192;
				if (local186 >= 65536) {
					if (local162 > local33) {
						local33++;
					} else if (local162 < local33) {
						local33--;
					}
					local186 -= 65536;
					if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
						method4348(false, local33, local40, SceneGraph.tiles, 1);
						break;
					}
				}
			}
			return;
		}
		local186 = 32768;
		local192 = local146 * 65536 / local174;
		while (local162 != local33) {
			if (local162 > local33) {
				local33++;
			} else if (local33 > local162) {
				local33--;
			}
			if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
				method4348(false, local33, local40, SceneGraph.tiles, 1);
				break;
			}
			local186 += local192;
			if (local186 >= 65536) {
				if (local40 < local135) {
					local40++;
				} else if (local135 < local40) {
					local40--;
				}
				local186 -= 65536;
				if ((SceneGraph.renderFlags[Player.plane][local33][local40] & 0x4) != 0) {
					method4348(false, local33, local40, SceneGraph.tiles, 1);
					break;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(I)V")
	public static void method2742() {
		if (client.gameState == 10 && GlRenderer.enabled) {
			client.processGameStatus(28);
		}
		if (client.gameState == 30) {
			client.processGameStatus(25);
		}
	}

	@OriginalMember(owner = "runetek4.client!sf", name = "b", descriptor = "(B)V")
	public static void determineMenuSize() {
		@Pc(16) int local16 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
		@Pc(18) int local18;
		@Pc(27) int local27;
		for (local18 = 0; local18 < MiniMenu.menuActionRow; local18++) {
			local27 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(local18));
			if (local27 > local16) {
				local16 = local27;
			}
		}
		local18 = MiniMenu.menuActionRow * 15 + 21;
		@Pc(43) int local43 = anInt1892;
		local16 += 8;
		local27 = anInt3751 - local16 / 2;
		if (local43 + local18 > GameShell.canvasHeigth) {
			local43 = GameShell.canvasHeigth - local18;
		}
		if (GameShell.canvasWidth < local27 + local16) {
			local27 = GameShell.canvasWidth - local16;
		}
		if (local27 < 0) {
			local27 = 0;
		}
		if (local43 < 0) {
			local43 = 0;
		}
		if (MiniMenu.anInt3953 == 1) {
			if (anInt3751 == Mouse.anInt5850 && Mouse.anInt5895 == anInt1892) {
				InterfaceList.anInt436 = MiniMenu.menuActionRow * 15 + (InterfaceList.aBoolean298 ? 26 : 22);
				MiniMenu.anInt3953 = 0;
				InterfaceList.anInt5138 = local43;
				InterfaceList.anInt4271 = local27;
				aBoolean108 = true;
				InterfaceList.anInt761 = local16;
			}
		} else if (anInt3751 == Mouse.mouseClickX && anInt1892 == Mouse.mouseClickY) {
			InterfaceList.anInt4271 = local27;
			MiniMenu.anInt3953 = 0;
			InterfaceList.anInt761 = local16;
			InterfaceList.anInt5138 = local43;
			InterfaceList.anInt436 = (InterfaceList.aBoolean298 ? 26 : 22) + MiniMenu.menuActionRow * 15;
			aBoolean108 = true;
		} else {
			Mouse.anInt5895 = Mouse.mouseClickY;
			Mouse.anInt5850 = Mouse.mouseClickX;
			MiniMenu.anInt3953 = 1;
		}
	}

	@OriginalMember(owner = "client!ag", name = "a", descriptor = "(IIIIIIIII)V")
	public static void method86(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
		if (InterfaceList.load(arg0)) {
			renderComponent(arg1, arg7, arg3, InterfaceList.cachedComponents[arg0], arg2, -1, arg6, arg5, arg4);
		} else if (arg4 == -1) {
			for (@Pc(27) int local27 = 0; local27 < 100; local27++) {
				InterfaceList.aBooleanArray100[local27] = true;
			}
		} else {
			InterfaceList.aBooleanArray100[arg4] = true;
		}
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIILclient!be;)V")
	public static void method1015(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) Component arg2) {
		if (aClass13_14 != null || aBoolean108 || (arg2 == null || method1836(arg2) == null)) {
			return;
		}
		aClass13_14 = arg2;
		aClass13_1 = method1836(arg2);
		anInt5388 = arg1;
		aBoolean172 = false;
		anInt4851 = 0;
		anInt4035 = arg0;
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(ILclient!be;)Lclient!be;")
	public static Component method1836(@OriginalArg(1) Component arg0) {
		@Pc(12) Component local12 = InterfaceList.method938(arg0);
		if (local12 == null) {
			local12 = arg0.aClass13_5;
		}
		return local12;
	}
}
