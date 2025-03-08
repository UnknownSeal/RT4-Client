package com.jagex.runetek4;

import java.io.IOException;

import com.jagex.runetek4.game.client.ClientInvCache;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.game.config.bastype.BASType;
import com.jagex.runetek4.game.config.iftype.Component;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.game.config.objtype.ObjType;
import com.jagex.runetek4.game.config.seqtype.SeqType;
import com.jagex.runetek4.game.scene.entities.NPCEntity;
import com.jagex.runetek4.game.world.entity.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static4 {

	@OriginalMember(owner = "runetek4.client!ac", name = "e", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray1;

	@OriginalMember(owner = "runetek4.client!ac", name = "c", descriptor = "I")
	public static int js5ConnectState = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "i", descriptor = "Lclient!ih;")
	public static final LinkedList aClass69_2 = new LinkedList();

	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;

	@OriginalMember(owner = "runetek4.client!ac", name = "l", descriptor = "Lclient!ck;")
	public static final BASType aClass20_1 = new BASType();

	@OriginalMember(owner = "runetek4.client!ac", name = "m", descriptor = "Lclient!na;")
	public static final JagString aClass100_7 = Static28.parse("overlay");

	@OriginalMember(owner = "runetek4.client!ac", name = "n", descriptor = "I")
	public static int anInt36 = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "o", descriptor = "I")
	public static int updatedVarcsWriterIndex = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "p", descriptor = "Lclient!be;")
	public static Component aClass13_1 = null;

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(IIII)I")
	public static int getRenderLevel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		if ((Static12.aByteArrayArrayArray2[arg2][arg1][arg0] & 0x8) == 0) {
			return arg2 <= 0 || (Static12.aByteArrayArrayArray2[1][arg1][arg0] & 0x2) == 0 ? arg2 : arg2 - 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "([J[Ljava/lang/Object;I)V")
	public static void method23(@OriginalArg(0) long[] arg0, @OriginalArg(1) Object[] arg1) {
		Static228.method3909(arg0.length - 1, arg0, 0, arg1);
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(BI)V")
	public static void method24(@OriginalArg(1) int arg0) {
		@Pc(16) DelayedStateChange local16 = Static238.method4143(1, arg0);
		local16.method1007();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(Lclient!na;I)I")
	public static int method25(@OriginalArg(0) JagString arg0) {
		if (arg0 == null) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static9.anInt178; local20++) {
			if (arg0.equalsIgnoreCase(Static122.aClass100Array92[local20])) {
				return local20;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(B)Z")
	public static boolean method26() throws IOException {
		if (Static124.socket == null) {
			return false;
		}
		@Pc(14) int available = Static124.socket.available();
		if (available == 0) {
			return false;
		}
		if (Static164.packetType == -1) {
			available--;
			Static124.socket.read(0, 1, Static57.in.data);
			Static57.in.pos = 0;
			Static164.packetType = Static57.in.gIssac1();
			Static223.packetSize = Static234.anIntArray456[Static164.packetType];
		}
		if (Static223.packetSize == -1) {
			if (available <= 0) {
				return false;
			}
			Static124.socket.read(0, 1, Static57.in.data);
			available--;
			Static223.packetSize = Static57.in.data[0] & 0xFF;
		}
		if (Static223.packetSize == -2) {
			if (available <= 1) {
				return false;
			}
			available -= 2;
			Static124.socket.read(0, 2, Static57.in.data);
			Static57.in.pos = 0;
			Static223.packetSize = Static57.in.g2();
		}
		if (Static223.packetSize > available) {
			return false;
		}
		Static57.in.pos = 0;
		Static124.socket.read(0, Static223.packetSize, Static57.in.data);
		Static49.anInt1462 = Static5.anInt45;
		Static5.anInt45 = Static230.anInt5152;
		Static230.anInt5152 = Static164.packetType;
		Static201.idleNetCycles = 0;
		@Pc(133) int level;
		if (Static164.packetType == 60) {
			level = Static57.in.g2sub();
			@Pc(137) byte local137 = Static57.in.g1neg();
			Static170.method2575(local137, level);
			Static164.packetType = -1;
			return true;
		}
		@Pc(171) int slot;
		@Pc(156) JagString message2;
		if (Static164.packetType == 115) {
			level = Static57.in.g2();
			message2 = Static57.in.gjstr();
			@Pc(163) Object[] local163 = new Object[message2.length() + 1];
			for (slot = message2.length() - 1; slot >= 0; slot--) {
				if (message2.method3149(slot) == 115) {
					local163[slot + 1] = Static57.in.gjstr();
				} else {
					local163[slot + 1] = Integer.valueOf(Static57.in.g4());
				}
			}
			local163[0] = Integer.valueOf(Static57.in.g4());
			if (Static248.method3288(level)) {
				@Pc(226) HookRequest local226 = new HookRequest();
				local226.anObjectArray31 = local163;
				Static82.method1767(local226);
			}
			Static164.packetType = -1;
			return true;
		}
		@Pc(275) long username;
		@Pc(262) boolean ignored;
		@Pc(277) int i;
		@Pc(506) JagString local506;
		if (Static164.packetType == 70) {
			@Pc(245) JagString message = Static57.in.gjstr();
			if (message.endsWith(Static196.TRADEREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				ignored = false;
				for (i = 0; i < Static35.size; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Static103.addMessage(message2, 4, LocalizedText.TRADEREQ);
				}
			} else if (message.endsWith(Static61.CHALREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				ignored = false;
				for (i = 0; i < Static35.size; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					local506 = message.substring(message.length() - 9, message.indexOf(Static264.aClass100_875) + 1);
					Static103.addMessage(message2, 8, local506);
				}
			} else if (message.endsWith(Static191.ASSISTREQ)) {
				ignored = false;
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				for (i = 0; i < Static35.size; i++) {
					if (username == Static190.ignoreName37[i]) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Static103.addMessage(message2, 10, Static186.aClass100_827);
				}
			} else if (message.endsWith(Static141.CLAN)) {
				message2 = message.substring(message.indexOf(Static141.CLAN), 0);
				Static103.addMessage(Static186.aClass100_827, 11, message2);
			} else if (message.endsWith(Static138.TRADE)) {
				message2 = message.substring(message.indexOf(Static138.TRADE), 0);
				if (Player.overrideChat == 0) {
					Static103.addMessage(Static186.aClass100_827, 12, message2);
				}
			} else if (message.endsWith(Static244.ASSIST)) {
				message2 = message.substring(message.indexOf(Static244.ASSIST), 0);
				if (Player.overrideChat == 0) {
					Static103.addMessage(Static186.aClass100_827, 13, message2);
				}
			} else if (message.endsWith(Static56.DUELSTAKE)) {
				ignored = false;
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				for (i = 0; i < Static35.size; i++) {
					if (username == Static190.ignoreName37[i]) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Static103.addMessage(message2, 14, Static186.aClass100_827);
				}
			} else if (message.endsWith(Static112.DUELFRIEND)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				ignored = false;
				username = message2.encode37();
				for (i = 0; i < Static35.size; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Static103.addMessage(message2, 15, Static186.aClass100_827);
				}
			} else if (message.endsWith(Static217.CLANREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				ignored = false;
				for (i = 0; i < Static35.size; i++) {
					if (username == Static190.ignoreName37[i]) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Static103.addMessage(message2, 16, Static186.aClass100_827);
				}
			} else if (message.endsWith(Static164.ALLYREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				ignored = false;
				username = message2.encode37();
				for (i = 0; i < Static35.size; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					local506 = message.substring(message.length() - 9, message.indexOf(Static264.aClass100_875) + 1);
					Static103.addMessage(message2, 21, local506);
				}
			} else {
				Static103.addMessage(Static186.aClass100_827, 0, message);
			}
			Static164.packetType = -1;
			return true;
		}
		@Pc(786) int xp;
		@Pc(790) JagString local790;
		if (Static164.packetType == 123) {
			level = Static57.in.g2le();
			xp = Static57.in.g2sub();
			local790 = Static57.in.gjstr();
			if (Static248.method3288(xp)) {
				Static193.method3498(local790, level);
			}
			Static164.packetType = -1;
			return true;
		} else if (Static164.packetType == 230) {
			Static180.anInt4264 = Static57.in.g1add();
			Static115.anInt2940 = Static57.in.g1_alt3();
			while (Static223.packetSize > Static57.in.pos) {
				Static164.packetType = Static57.in.g1();
				Static75.method1634();
			}
			Static164.packetType = -1;
			return true;
		} else if (Static164.packetType == 153) {
			Static164.packetType = -1;
			Static115.anInt2939 = 0;
			return true;
		} else {
			@Pc(864) int stat;
			if (Static164.packetType == 220) {
				level = Static57.in.p4rme();
				xp = Static57.in.g2le();
				stat = Static57.in.g2();
				if (Static248.method3288(stat)) {
					Static229.method3938(xp, level);
				}
				Static164.packetType = -1;
				return true;
			}
			@Pc(884) long local884;
			@Pc(908) int local908;
			@Pc(916) int local916;
			@Pc(899) long local899;
			@Pc(904) long local904;
			if (Static164.packetType == 81) {
				local884 = Static57.in.g8();
				Static57.in.g1s();
				username = Static57.in.g8();
				local899 = Static57.in.g2();
				local904 = Static57.in.g3();
				local908 = Static57.in.g1();
				@Pc(910) boolean local910 = false;
				local916 = Static57.in.g2();
				@Pc(922) long local922 = (local899 << 32) + local904;
				@Pc(924) int local924 = 0;
				label1320: while (true) {
					if (local924 < 100) {
						if (local922 != Static233.aLongArray9[local924]) {
							local924++;
							continue;
						}
						local910 = true;
						break;
					}
					if (local908 <= 1) {
						for (local924 = 0; local924 < Static35.size; local924++) {
							if (Static190.ignoreName37[local924] == local884) {
								local910 = true;
								break label1320;
							}
						}
					}
					break;
				}
				if (!local910 && Player.overrideChat == 0) {
					Static233.aLongArray9[Static251.anInt5447] = local922;
					Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
					@Pc(999) JagString local999 = Static230.list(local916).method770(Static57.in);
					if (local908 == 2 || local908 == 3) {
						Static154.add(local916, 20, local999, Static79.decode37(username).method3125(), Static34.method882(new JagString[] { Static44.aClass100_336, Static79.decode37(local884).method3125() }));
					} else if (local908 == 1) {
						Static154.add(local916, 20, local999, Static79.decode37(username).method3125(), Static34.method882(new JagString[] { Static65.aClass100_435, Static79.decode37(local884).method3125() }));
					} else {
						Static154.add(local916, 20, local999, Static79.decode37(username).method3125(), Static79.decode37(local884).method3125());
					}
				}
				Static164.packetType = -1;
				return true;
			}
			@Pc(1146) int count;
			@Pc(1160) int local1160;
			@Pc(1245) boolean local1245;
			if (Static164.packetType == 55) {
				Static278.anInt5867 = Static119.transmitTimer;
				local884 = Static57.in.g8();
				if (local884 == 0L) {
					Static270.aClass100_1094 = null;
					Static164.packetType = -1;
					Static15.aClass100_87 = null;
					Static199.aClass3_Sub22Array1 = null;
					Static214.anInt5577 = 0;
					return true;
				}
				username = Static57.in.g8();
				Static15.aClass100_87 = Static79.decode37(username);
				Static270.aClass100_1094 = Static79.decode37(local884);
				Static50.aByte6 = Static57.in.g1s();
				count = Static57.in.g1();
				if (count == 255) {
					Static164.packetType = -1;
					return true;
				}
				Static214.anInt5577 = count;
				@Pc(1158) Class3_Sub22[] local1158 = new Class3_Sub22[100];
				for (local1160 = 0; local1160 < Static214.anInt5577; local1160++) {
					local1158[local1160] = new Class3_Sub22();
					local1158[local1160].nodeId = Static57.in.g8();
					local1158[local1160].aClass100_636 = Static79.decode37(local1158[local1160].nodeId);
					local1158[local1160].anInt3340 = Static57.in.g2();
					local1158[local1160].aByte9 = Static57.in.g1s();
					local1158[local1160].aClass100_635 = Static57.in.gjstr();
					if (Static101.aLong98 == local1158[local1160].nodeId) {
						Static160.aByte14 = local1158[local1160].aByte9;
					}
				}
				local908 = Static214.anInt5577;
				while (local908 > 0) {
					local1245 = true;
					local908--;
					for (local916 = 0; local916 < local908; local916++) {
						if (local1158[local916].aClass100_636.method3139(local1158[local916 + 1].aClass100_636) > 0) {
							local1245 = false;
							@Pc(1279) Class3_Sub22 local1279 = local1158[local916];
							local1158[local916] = local1158[local916 + 1];
							local1158[local916 + 1] = local1279;
						}
					}
					if (local1245) {
						break;
					}
				}
				Static199.aClass3_Sub22Array1 = local1158;
				Static164.packetType = -1;
				return true;
			} else if (Static164.packetType == 164) {
				level = Static57.in.g4rme();
				Static232.aClass212_5 = GameShell.signLink.method5128(level);
				Static164.packetType = -1;
				return true;
			} else if (Static164.packetType == 225) {
				Static64.method1495();
				Static164.packetType = -1;
				return true;
			} else if (Static164.packetType == 48) {
				level = Static57.in.g2();
				message2 = Static57.in.gjstr();
				stat = Static57.in.g2leadd();
				if (Static248.method3288(level)) {
					Static193.method3498(message2, stat);
				}
				Static164.packetType = -1;
				return true;
			} else if (Static164.packetType == 232) {
				Static59.anInt1812 = Static57.in.g1();
				Static49.anInt1459 = Static57.in.g1();
				Static84.anInt2256 = Static57.in.g1();
				Static164.packetType = -1;
				return true;
			} else {
				@Pc(1409) JagString local1409;
				if (Static164.packetType == 44) {
					level = Static57.in.g2leadd();
					if (level == 65535) {
						level = -1;
					}
					xp = Static57.in.g1();
					stat = Static57.in.g1();
					local1409 = Static57.in.gjstr();
					if (stat >= 1 && stat <= 8) {
						if (local1409.equalsIgnoreCase(Static92.aClass100_510)) {
							local1409 = null;
						}
						Static160.aClass100Array121[stat - 1] = local1409;
						Static191.anIntArray388[stat - 1] = level;
						Static1.aBooleanArray1[stat - 1] = xp == 0;
					}
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 226) {
					level = Static57.in.g4();
					xp = Static57.in.g2sub();
					Static170.method2575(level, xp);
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 21) {
					level = Static57.in.p1neg();
					xp = Static57.in.g2();
					stat = Static57.in.g4me();
					if (Static248.method3288(xp)) {
						Static153.method2905(stat, level);
					}
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 145) {
					level = Static57.in.g2leadd();
					xp = Static57.in.g1add();
					stat = Static57.in.g2leadd();
					if (Static248.method3288(stat)) {
						if (xp == 2) {
							Static5.method34();
						}
						Static154.topLevelInterace = level;
						Static81.method1753(level);
						Static210.method3712(false);
						Static74.method1626(Static154.topLevelInterace);
						for (slot = 0; slot < 100; slot++) {
							Static186.aBooleanArray100[slot] = true;
						}
					}
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 69) {
					level = Static57.in.g2leadd();
					xp = Static57.in.g4();
					stat = Static57.in.g2sub();
					if (Static248.method3288(level)) {
						Static132.method2606(stat, xp);
					}
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 141) {
					local884 = Static57.in.g8();
					stat = Static57.in.g2();
					local1409 = Static230.list(stat).method770(Static57.in);
					Static154.add(stat, 19, local1409, null, Static79.decode37(local884).method3125());
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 169) {
					Static271.method4598(Static57.in);
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 89) {
					Static8.method121();
					Static103.method2245();
					Static70.updatedVarpsWriterIndex += 32;
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 125) {
					level = Static57.in.g2();
					xp = Static57.in.g1();
					stat = Static57.in.g1();
					slot = Static57.in.g2();
					count = Static57.in.g1();
					i = Static57.in.g1();
					if (Static248.method3288(level)) {
						Static260.method3849(slot, stat, count, xp, i);
					}
					Static164.packetType = -1;
					return true;
				} else if (Static164.packetType == 36) {
					level = Static57.in.p4rme();
					xp = Static57.in.g2les();
					stat = Static57.in.g2sub();
					if (Static248.method3288(stat)) {
						Static225.method3893(level, xp);
					}
					Static164.packetType = -1;
					return true;
				} else {
					@Pc(1814) ServerActiveProperties local1814;
					@Pc(1804) ServerActiveProperties local1804;
					if (Static164.packetType == 9) {
						level = Static57.in.g2leadd();
						xp = Static57.in.g4me();
						stat = Static57.in.g2sub();
						slot = Static57.in.g2le();
						if (slot == 65535) {
							slot = -1;
						}
						count = Static57.in.g2sub();
						if (count == 65535) {
							count = -1;
						}
						if (Static248.method3288(stat)) {
							for (i = count; i <= slot; i++) {
								local904 = (long) i + ((long) xp << 32);
								local1804 = (ServerActiveProperties) Static210.aClass133_21.getNode(local904);
								if (local1804 != null) {
									local1814 = new ServerActiveProperties(local1804.anInt546, level);
									local1804.remove();
								} else if (i == -1) {
									local1814 = new ServerActiveProperties(Static5.getComponent(xp).aClass3_Sub4_1.anInt546, level);
								} else {
									local1814 = new ServerActiveProperties(0, level);
								}
								Static210.aClass133_21.pushNode(local1814, local904);
							}
						}
						Static164.packetType = -1;
						return true;
					}
					@Pc(1986) int local1986;
					if (Static164.packetType == 56) {
						level = Static57.in.g2();
						xp = Static57.in.g2le();
						stat = Static57.in.g4rme();
						slot = Static57.in.g2leadd();
						if (stat >> 30 == 0) {
							@Pc(1994) SeqType local1994;
							if (stat >> 29 != 0) {
								count = stat & 0xFFFF;
								@Pc(1894) NPCEntity local1894 = Static175.npcs[count];
								if (local1894 != null) {
									if (slot == 65535) {
										slot = -1;
									}
									local1245 = true;
									if (slot != -1 && local1894.anInt3432 != -1 && Static36.method941(Static34.method877(slot).anInt1754).priority < Static36.method941(Static34.method877(local1894.anInt3432).anInt1754).priority) {
										local1245 = false;
									}
									if (local1245) {
										local1894.anInt3361 = 0;
										local1894.anInt3432 = slot;
										local1894.spotanimLastCycle = Static83.loopCycle + level;
										local1894.spotanimId = 0;
										if (local1894.spotanimLastCycle > Static83.loopCycle) {
											local1894.spotanimId = -1;
										}
										local1894.spotanimOffset = xp;
										local1894.anInt3418 = 1;
										if (local1894.anInt3432 != -1 && Static83.loopCycle == local1894.spotanimLastCycle) {
											local1986 = Static34.method877(local1894.anInt3432).anInt1754;
											if (local1986 != -1) {
												local1994 = Static36.method941(local1986);
												if (local1994 != null && local1994.anIntArray473 != null) {
													Static152.method2836(local1894.z, local1994, local1894.x, false, 0);
												}
											}
										}
									}
								}
							} else if (stat >> 28 != 0) {
								count = stat & 0xFFFF;
								@Pc(2033) Player local2033;
								if (Static16.anInt549 == count) {
									local2033 = Static173.localPlayer;
								} else {
									local2033 = Static159.players[count];
								}
								if (local2033 != null) {
									if (slot == 65535) {
										slot = -1;
									}
									local1245 = true;
									if (slot != -1 && local2033.anInt3432 != -1 && Static36.method941(Static34.method877(slot).anInt1754).priority < Static36.method941(Static34.method877(local2033.anInt3432).anInt1754).priority) {
										local1245 = false;
									}
									if (local1245) {
										local2033.spotanimLastCycle = level + Static83.loopCycle;
										local2033.spotanimOffset = xp;
										local2033.anInt3432 = slot;
										if (local2033.anInt3432 == 65535) {
											local2033.anInt3432 = -1;
										}
										local2033.anInt3418 = 1;
										local2033.anInt3361 = 0;
										local2033.spotanimId = 0;
										if (local2033.spotanimLastCycle > Static83.loopCycle) {
											local2033.spotanimId = -1;
										}
										if (local2033.anInt3432 != -1 && local2033.spotanimLastCycle == Static83.loopCycle) {
											local1986 = Static34.method877(local2033.anInt3432).anInt1754;
											if (local1986 != -1) {
												local1994 = Static36.method941(local1986);
												if (local1994 != null && local1994.anIntArray473 != null) {
													Static152.method2836(local2033.z, local1994, local2033.x, local2033 == Static173.localPlayer, 0);
												}
											}
										}
									}
								}
							}
						} else {
							count = stat >> 28 & 0x3;
							i = (stat >> 14 & 0x3FFF) - Static225.originX;
							local1160 = (stat & 0x3FFF) - Static142.originZ;
							if (i >= 0 && local1160 >= 0 && i < 104 && local1160 < 104) {
								local1160 = local1160 * 128 + 64;
								i = i * 128 + 64;
								@Pc(2241) SpotAnim local2241 = new SpotAnim(slot, count, i, local1160, Static207.method3685(count, i, local1160) - xp, level, Static83.loopCycle);
								Static99.aClass69_64.addTail(new SpotAnimNode(local2241));
							}
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 207) {
						level = Static57.in.p4rme();
						xp = Static57.in.g2sub();
						stat = Static57.in.g2();
						slot = Static57.in.g2sub();
						if (Static248.method3288(xp)) {
							Static190.method3444(slot + (stat << 16), level);
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 38) {
						// UPDATE_STAT
						Static103.method2245();
						level = Static57.in.g1add();
						xp = Static57.in.g4rme();
						stat = Static57.in.g1();
						Static227.anIntArray446[stat] = xp;
						Static99.anIntArray240[stat] = level;
						Static141.skillBaseLevel[stat] = 1;
						for (slot = 0; slot < 98; slot++) {
							if (ObjType.levelExperience[slot] <= xp) {
								Static141.skillBaseLevel[stat] = slot + 2;
							}
						}
						Static249.anIntArray478[Static89.anInt2385++ & 0x1F] = stat;
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 104 || Static164.packetType == 121 || Static164.packetType == 97 || Static164.packetType == 14 || Static164.packetType == 202 || Static164.packetType == 135 || Static164.packetType == 17 || Static164.packetType == 16 || Static164.packetType == 240 || Static164.packetType == 33 || Static164.packetType == 20 || Static164.packetType == 195 || Static164.packetType == 179) {
						Static75.method1634();
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 149) {
						level = Static57.in.g2();
						xp = Static57.in.g4();
						if (Static248.method3288(level)) {
							@Pc(2441) Class3_Sub31 local2441 = (Class3_Sub31) Static119.aClass133_9.getNode((long) xp);
							if (local2441 != null) {
								Static132.method2605(true, local2441);
							}
							if (Static39.aClass13_10 != null) {
								Static43.method1143(Static39.aClass13_10);
								Static39.aClass13_10 = null;
							}
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 187) {
						level = Static57.in.g2le();
						xp = Static57.in.g2();
						stat = Static57.in.g2();
						if (Static248.method3288(xp)) {
							Static57.orbitCameraYaw = level;
							Static72.anInt2031 = stat;
							if (Static227.anInt5096 == 2) {
								Static240.anInt5333 = Static72.anInt2031;
								Static184.anInt4358 = Static57.orbitCameraYaw;
							}
							Static87.method1812();
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 132) {
						level = Static57.in.g2();
						xp = Static57.in.g2sub();
						stat = Static57.in.g2leadd();
						slot = Static57.in.g2leadd();
						count = Static57.in.g4();
						if (Static248.method3288(xp)) {
							Static261.method4505(stat, count, slot, level);
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 112) {
						Static115.anInt2940 = Static57.in.g1();
						Static180.anInt4264 = Static57.in.p1neg();
						for (level = Static115.anInt2940; level < Static115.anInt2940 + 8; level++) {
							for (xp = Static180.anInt4264; xp < Static180.anInt4264 + 8; xp++) {
								if (Static159.levelObjStacks[Static55.level][level][xp] != null) {
									Static159.levelObjStacks[Static55.level][level][xp] = null;
									Static220.method3797(xp, level);
								}
							}
						}
						for (@Pc(2604) Class3_Sub7 local2604 = (Class3_Sub7) Static26.spawnedLocations.head(); local2604 != null; local2604 = (Class3_Sub7) Static26.spawnedLocations.next()) {
							if (local2604.anInt928 >= Static115.anInt2940 && Static115.anInt2940 + 8 > local2604.anInt928 && local2604.anInt916 >= Static180.anInt4264 && local2604.anInt916 < Static180.anInt4264 + 8 && local2604.anInt918 == Static55.level) {
								local2604.anInt924 = 0;
							}
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 144) {
						level = Static57.in.p4rme();
						@Pc(2666) Component local2666 = Static5.getComponent(level);
						for (stat = 0; stat < local2666.objTypes.length; stat++) {
							local2666.objTypes[stat] = -1;
							local2666.objTypes[stat] = 0;
						}
						Static43.method1143(local2666);
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 130) {
						level = Static57.in.g4me();
						xp = Static57.in.g2leadd();
						stat = Static57.in.g2sub();
						if (stat == 65535) {
							stat = -1;
						}
						if (Static248.method3288(xp)) {
							Static132.method2607(-1, 1, level, stat);
						}
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 192) {
						Static270.anInt5795 = Static57.in.g1();
						Static164.packetType = -1;
						return true;
					} else if (Static164.packetType == 13) {
						level = Static57.in.g1_alt3();
						xp = Static57.in.g1add();
						stat = Static57.in.g1();
						Static55.level = xp >> 1;
						Static173.localPlayer.method1265(level, (xp & 0x1) == 1, stat);
						Static164.packetType = -1;
						return true;
					} else {
						@Pc(3002) int local3002;
						@Pc(3038) JagString local3038;
						@Pc(3020) JagString local3020;
						if (Static164.packetType == 62) {
							local884 = Static57.in.g8();
							stat = Static57.in.g2();
							slot = Static57.in.g1();
							ignored = true;
							if (local884 < 0L) {
								local884 &= Long.MAX_VALUE;
								ignored = false;
							}
							local506 = Static186.aClass100_827;
							if (stat > 0) {
								local506 = Static57.in.gjstr();
							}
							@Pc(2834) JagString local2834 = Static79.decode37(local884).method3125();
							for (local1986 = 0; local1986 < Static9.anInt178; local1986++) {
								if (local884 == Static92.aLongArray3[local1986]) {
									if (stat != Static104.anIntArray255[local1986]) {
										Static104.anIntArray255[local1986] = stat;
										if (stat > 0) {
											Static103.addMessage(Static186.aClass100_827, 5, Static34.method882(new JagString[] { local2834, LocalizedText.FRIENDLOGIN}));
										}
										if (stat == 0) {
											Static103.addMessage(Static186.aClass100_827, 5, Static34.method882(new JagString[] { local2834, LocalizedText.FRIENDLOGOUT}));
										}
									}
									Static214.aClass100Array170[local1986] = local506;
									Static106.anIntArray258[local1986] = slot;
									local2834 = null;
									Static3.aBooleanArray135[local1986] = ignored;
									break;
								}
							}
							if (local2834 != null && Static9.anInt178 < 200) {
								Static92.aLongArray3[Static9.anInt178] = local884;
								Static122.aClass100Array92[Static9.anInt178] = local2834;
								Static104.anIntArray255[Static9.anInt178] = stat;
								Static214.aClass100Array170[Static9.anInt178] = local506;
								Static106.anIntArray258[Static9.anInt178] = slot;
								Static3.aBooleanArray135[Static9.anInt178] = ignored;
								Static9.anInt178++;
							}
							Static185.anInt4369 = Static119.transmitTimer;
							local908 = Static9.anInt178;
							while (local908 > 0) {
								local908--;
								@Pc(2961) boolean local2961 = true;
								for (local916 = 0; local916 < local908; local916++) {
									if (Static104.anIntArray255[local916] != Static125.worldId && Static125.worldId == Static104.anIntArray255[local916 + 1] || Static104.anIntArray255[local916] == 0 && Static104.anIntArray255[local916 + 1] != 0) {
										local2961 = false;
										local3002 = Static104.anIntArray255[local916];
										Static104.anIntArray255[local916] = Static104.anIntArray255[local916 + 1];
										Static104.anIntArray255[local916 + 1] = local3002;
										local3020 = Static214.aClass100Array170[local916];
										Static214.aClass100Array170[local916] = Static214.aClass100Array170[local916 + 1];
										Static214.aClass100Array170[local916 + 1] = local3020;
										local3038 = Static122.aClass100Array92[local916];
										Static122.aClass100Array92[local916] = Static122.aClass100Array92[local916 + 1];
										Static122.aClass100Array92[local916 + 1] = local3038;
										@Pc(3056) long local3056 = Static92.aLongArray3[local916];
										Static92.aLongArray3[local916] = Static92.aLongArray3[local916 + 1];
										Static92.aLongArray3[local916 + 1] = local3056;
										@Pc(3074) int local3074 = Static106.anIntArray258[local916];
										Static106.anIntArray258[local916] = Static106.anIntArray258[local916 + 1];
										Static106.anIntArray258[local916 + 1] = local3074;
										@Pc(3092) boolean local3092 = Static3.aBooleanArray135[local916];
										Static3.aBooleanArray135[local916] = Static3.aBooleanArray135[local916 + 1];
										Static3.aBooleanArray135[local916 + 1] = local3092;
									}
								}
								if (local2961) {
									break;
								}
							}
							Static164.packetType = -1;
							return true;
						} else if (Static164.packetType == 160) {
							if (Static223.packetSize == 0) {
								Static195.aClass100_859 = LocalizedText.WALKHERE;
							} else {
								Static195.aClass100_859 = Static57.in.gjstr();
							}
							Static164.packetType = -1;
							return true;
						} else if (Static164.packetType == 128) {
							for (level = 0; level < Static7.anIntArray75.length; level++) {
								if (Static106.anIntArray257[level] != Static7.anIntArray75[level]) {
									Static7.anIntArray75[level] = Static106.anIntArray257[level];
									Static85.method1775(level);
									Static83.updatedVarps[Static70.updatedVarpsWriterIndex++ & 0x1F] = level;
								}
							}
							Static164.packetType = -1;
							return true;
						} else if (Static164.packetType == 154) {
							level = Static57.in.g2();
							xp = Static57.in.g1();
							stat = Static57.in.g1();
							slot = Static57.in.g2();
							count = Static57.in.g1();
							i = Static57.in.g1();
							if (Static248.method3288(level)) {
								Static141.method2722(true, count, slot, i, stat, xp);
							}
							Static164.packetType = -1;
							return true;
						} else if (Static164.packetType == 247) {
							local884 = Static57.in.g8();
							username = Static57.in.g2();
							local899 = Static57.in.g3();
							local1160 = Static57.in.g1();
							local1986 = Static57.in.g2();
							@Pc(3263) boolean local3263 = false;
							@Pc(3270) long local3270 = (username << 32) + local899;
							@Pc(3272) int local3272 = 0;
							label1402: while (true) {
								if (local3272 < 100) {
									if (local3270 != Static233.aLongArray9[local3272]) {
										local3272++;
										continue;
									}
									local3263 = true;
									break;
								}
								if (local1160 <= 1) {
									for (local3272 = 0; local3272 < Static35.size; local3272++) {
										if (local884 == Static190.ignoreName37[local3272]) {
											local3263 = true;
											break label1402;
										}
									}
								}
								break;
							}
							if (!local3263 && Player.overrideChat == 0) {
								Static233.aLongArray9[Static251.anInt5447] = local3270;
								Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
								local3020 = Static230.list(local1986).method770(Static57.in);
								if (local1160 == 2) {
									Static154.add(local1986, 18, local3020, null, Static34.method882(new JagString[] { Static44.aClass100_336, Static79.decode37(local884).method3125() }));
								} else if (local1160 == 1) {
									Static154.add(local1986, 18, local3020, null, Static34.method882(new JagString[] { Static65.aClass100_435, Static79.decode37(local884).method3125() }));
								} else {
									Static154.add(local1986, 18, local3020, null, Static79.decode37(local884).method3125());
								}
							}
							Static164.packetType = -1;
							return true;
						} else {
							@Pc(3456) Class3_Sub31 local3456;
							if (Static164.packetType == 176) {
								level = Static57.in.g4rme();
								xp = Static57.in.g2sub();
								stat = Static57.in.g4rme();
								if (Static248.method3288(xp)) {
									@Pc(3449) Class3_Sub31 local3449 = (Class3_Sub31) Static119.aClass133_9.getNode((long) level);
									local3456 = (Class3_Sub31) Static119.aClass133_9.getNode((long) stat);
									if (local3456 != null) {
										Static132.method2605(local3449 == null || local3456.anInt5878 != local3449.anInt5878, local3456);
									}
									if (local3449 != null) {
										local3449.remove();
										Static119.aClass133_9.pushNode(local3449, (long) stat);
									}
									@Pc(3490) Component local3490 = Static5.getComponent(level);
									if (local3490 != null) {
										Static43.method1143(local3490);
									}
									local3490 = Static5.getComponent(stat);
									if (local3490 != null) {
										Static43.method1143(local3490);
										Static17.method531(local3490, true);
									}
									if (Static154.topLevelInterace != -1) {
										Static54.method1304(1, Static154.topLevelInterace);
									}
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 27) {
								level = Static57.in.g2();
								xp = Static57.in.g1();
								stat = Static57.in.g1();
								slot = Static57.in.g1();
								count = Static57.in.g1();
								i = Static57.in.g2();
								if (Static248.method3288(level)) {
									Static176.aBooleanArray95[xp] = true;
									Static222.anIntArray437[xp] = stat;
									Static276.anIntArray564[xp] = slot;
									Static202.anIntArray424[xp] = count;
									Static31.anIntArray76[xp] = i;
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 2) {
								level = Static57.in.g4rme();
								xp = Static57.in.g2sub();
								stat = Static57.in.g2leadd();
								if (Static248.method3288(xp)) {
									Static136.method2649(stat, level);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 85) {
								Static60.systemUpdateTimer = Static57.in.g2() * 30;
								Static164.packetType = -1;
								Static209.miscTransmitAt = Static119.transmitTimer;
								return true;
							} else if (Static164.packetType == 114) {
								Static202.method3654(GameShell.signLink, Static57.in, Static223.packetSize);
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 65) {
								level = Static57.in.g2le();
								xp = Static57.in.p1neg();
								stat = Static57.in.g2leadd();
								if (Static248.method3288(level)) {
									Static132.method2606(stat, xp);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 234) {
								Static103.method2245();
								Static12.anInt400 = Static57.in.g1();
								Static209.miscTransmitAt = Static119.transmitTimer;
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 209) {
								if (Static154.topLevelInterace != -1) {
									Static54.method1304(0, Static154.topLevelInterace);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 191) {
								level = Static57.in.g2le();
								Static13.method472(level);
								Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = level & 0x7FFF;
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 102) {
								level = Static57.in.g2le();
								xp = Static57.in.g1_alt3();
								stat = Static57.in.g2();
								@Pc(3766) NPCEntity local3766 = Static175.npcs[level];
								if (local3766 != null) {
									Static223.method3855(xp, stat, local3766);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 159) {
								Static103.method2245();
								Static251.anInt5456 = Static57.in.g2s();
								Static209.miscTransmitAt = Static119.transmitTimer;
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 71) {
								local884 = Static57.in.g8();
								local790 = Static218.method2862(Static65.method1497(Static57.in).method3116());
								Static103.addMessage(Static79.decode37(local884).method3125(), 6, local790);
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 42) {
								if (Static69.aFrame2 != null) {
									Static241.method4540(false, Static214.anInt5581, -1, -1);
								}
								@Pc(3848) byte[] local3848 = new byte[Static223.packetSize];
								Static57.in.method2237(local3848, Static223.packetSize);
								message2 = Static10.decodeString(local3848, Static223.packetSize, 0);
								if (Static39.frame == null && (SignLink.anInt5928 == 3 || !SignLink.osName.startsWith("win") || Static178.aBoolean203)) {
									Static169.openUrl(message2, true);
								} else {
									Static175.url = message2;
									Static164.newTab = true;
									Static33.openUrlRequest = GameShell.signLink.method5131(new String(message2.method3148(), "ISO-8859-1"));
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 111) {
								level = Static57.in.g2sub();
								xp = Static57.in.p4rme();
								stat = Static57.in.g2leadd();
								slot = Static57.in.g2le();
								count = Static57.in.g2leadd();
								if (Static248.method3288(level)) {
									Static132.method2607(stat, 7, xp, slot << 16 | count);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 37) {
								level = Static57.in.g1add();
								xp = Static57.in.g2le();
								Static272.method3995(level, xp);
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 155) {
								level = Static57.in.g1();
								xp = Static57.in.p4rme();
								stat = Static57.in.g2sub();
								slot = Static57.in.g2();
								if (Static248.method3288(stat)) {
									local3456 = (Class3_Sub31) Static119.aClass133_9.getNode((long) xp);
									if (local3456 != null) {
										Static132.method2605(local3456.anInt5878 != slot, local3456);
									}
									Static44.method1148(slot, xp, level);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 131) {
								for (level = 0; level < Static159.players.length; level++) {
									if (Static159.players[level] != null) {
										Static159.players[level].anInt3369 = -1;
									}
								}
								for (level = 0; level < Static175.npcs.length; level++) {
									if (Static175.npcs[level] != null) {
										Static175.npcs[level].anInt3369 = -1;
									}
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 217) {
								level = Static57.in.g1();
								@Pc(4084) Class102 local4084 = new Class102();
								xp = level >> 6;
								local4084.anInt4058 = level & 0x3F;
								local4084.anInt4048 = Static57.in.g1();
								if (local4084.anInt4048 >= 0 && local4084.anInt4048 < Static276.aClass3_Sub2_Sub1Array11.length) {
									if (local4084.anInt4058 == 1 || local4084.anInt4058 == 10) {
										local4084.anInt4057 = Static57.in.g2();
										Static57.in.pos += 3;
									} else if (local4084.anInt4058 >= 2 && local4084.anInt4058 <= 6) {
										if (local4084.anInt4058 == 2) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 64;
										}
										if (local4084.anInt4058 == 3) {
											local4084.anInt4045 = 0;
											local4084.anInt4047 = 64;
										}
										if (local4084.anInt4058 == 4) {
											local4084.anInt4045 = 128;
											local4084.anInt4047 = 64;
										}
										if (local4084.anInt4058 == 5) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 0;
										}
										if (local4084.anInt4058 == 6) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 128;
										}
										local4084.anInt4058 = 2;
										local4084.anInt4053 = Static57.in.g2();
										local4084.anInt4046 = Static57.in.g2();
										local4084.anInt4050 = Static57.in.g1();
									}
									local4084.anInt4052 = Static57.in.g2();
									if (local4084.anInt4052 == 65535) {
										local4084.anInt4052 = -1;
									}
									Static143.aClass102Array1[xp] = local4084;
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 126) {
								Static35.size = Static223.packetSize / 8;
								for (level = 0; level < Static35.size; level++) {
									Static190.ignoreName37[level] = Static57.in.g8();
									Static193.aClass100Array134[level] = Static79.decode37(Static190.ignoreName37[level]);
								}
								Static185.anInt4369 = Static119.transmitTimer;
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 32) {
								Static86.method1800();
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 119) {
								level = Static57.in.g2sub();
								xp = Static57.in.g4me();
								stat = Static57.in.g2s();
								slot = Static57.in.g2sadd();
								if (Static248.method3288(level)) {
									Static280.method4666(stat, xp, slot);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 235) {
								level = Static57.in.g1_alt3();
								xp = level >> 2;
								stat = level & 0x3;
								slot = client.locShapeToLayer[xp];
								count = Static57.in.g2();
								i = Static57.in.g4();
								if (count == 65535) {
									count = -1;
								}
								local908 = i & 0x3FFF;
								local1986 = i >> 14 & 0x3FFF;
								local1986 -= Static225.originX;
								local908 -= Static142.originZ;
								local1160 = i >> 28 & 0x3;
								Static92.method1881(local1160, stat, xp, local908, slot, local1986, count);
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 0) {
								local884 = Static57.in.g8();
								username = Static57.in.g2();
								local899 = Static57.in.g3();
								local1160 = Static57.in.g1();
								@Pc(4425) boolean local4425 = false;
								@Pc(4431) long local4431 = local899 + (username << 32);
								local3002 = 0;
								label1450: while (true) {
									if (local3002 >= 100) {
										if (local1160 <= 1) {
											if (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129) {
												local4425 = true;
											} else {
												for (local3002 = 0; local3002 < Static35.size; local3002++) {
													if (local884 == Static190.ignoreName37[local3002]) {
														local4425 = true;
														break label1450;
													}
												}
											}
										}
										break;
									}
									if (local4431 == Static233.aLongArray9[local3002]) {
										local4425 = true;
										break;
									}
									local3002++;
								}
								if (!local4425 && Player.overrideChat == 0) {
									Static233.aLongArray9[Static251.anInt5447] = local4431;
									Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
									@Pc(4518) JagString local4518 = Static218.method2862(Static65.method1497(Static57.in).method3116());
									if (local1160 == 2 || local1160 == 3) {
										Static103.addMessage(Static34.method882(new JagString[] { Static44.aClass100_336, Static79.decode37(local884).method3125() }), 7, local4518);
									} else if (local1160 == 1) {
										Static103.addMessage(Static34.method882(new JagString[] { Static65.aClass100_435, Static79.decode37(local884).method3125() }), 7, local4518);
									} else {
										Static103.addMessage(Static79.decode37(local884).method3125(), 3, local4518);
									}
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 54) {
								local884 = Static57.in.g8();
								Static57.in.g1s();
								username = Static57.in.g8();
								local899 = Static57.in.g2();
								local904 = Static57.in.g3();
								@Pc(4626) long local4626 = (local899 << 32) + local904;
								local908 = Static57.in.g1();
								@Pc(4632) boolean local4632 = false;
								@Pc(4634) int local4634 = 0;
								label1575: while (true) {
									if (local4634 >= 100) {
										if (local908 <= 1) {
											if (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129) {
												local4632 = true;
											} else {
												for (local4634 = 0; local4634 < Static35.size; local4634++) {
													if (Static190.ignoreName37[local4634] == local884) {
														local4632 = true;
														break label1575;
													}
												}
											}
										}
										break;
									}
									if (Static233.aLongArray9[local4634] == local4626) {
										local4632 = true;
										break;
									}
									local4634++;
								}
								if (!local4632 && Player.overrideChat == 0) {
									Static233.aLongArray9[Static251.anInt5447] = local4626;
									Static251.anInt5447 = (Static251.anInt5447 + 1) % 100;
									local3038 = Static218.method2862(Static65.method1497(Static57.in).method3116());
									if (local908 == 2 || local908 == 3) {
										Static73.method1598(local3038, Static34.method882(new JagString[] { Static44.aClass100_336, Static79.decode37(local884).method3125() }), Static79.decode37(username).method3125());
									} else if (local908 == 1) {
										Static73.method1598(local3038, Static34.method882(new JagString[] { Static65.aClass100_435, Static79.decode37(local884).method3125() }), Static79.decode37(username).method3125());
									} else {
										Static73.method1598(local3038, Static79.decode37(local884).method3125(), Static79.decode37(username).method3125());
									}
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 214) {
								Static75.method1629(true);
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 172) {
								level = Static57.in.g2();
								xp = Static57.in.g1();
								if (level == 65535) {
									level = -1;
								}
								stat = Static57.in.g2();
								Static26.method744(xp, level, stat);
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 66) {
								level = Static57.in.g2leadd();
								xp = Static57.in.g4rme();
								if (Static248.method3288(level)) {
									stat = 0;
									if (Static173.localPlayer.model != null) {
										stat = Static173.localPlayer.model.method1952();
									}
									Static132.method2607(-1, 3, xp, stat);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 171) {
								level = Static57.in.p4rme();
								message2 = Static57.in.gjstr();
								stat = Static57.in.g2sub();
								if (Static248.method3288(stat)) {
									Static80.method3617(message2, level);
								}
								Static164.packetType = -1;
								return true;
							} else if (Static164.packetType == 84) {
								level = Static57.in.g4me();
								xp = Static57.in.g2leadd();
								Static272.method3995(level, xp);
								Static164.packetType = -1;
								return true;
							} else {
								@Pc(4956) Component local4956;
								if (Static164.packetType == 22) {
									level = Static57.in.g4();
									xp = Static57.in.g2();
									if (level < -70000) {
										xp += 32768;
									}
									if (level < 0) {
										local4956 = null;
									} else {
										local4956 = Static5.getComponent(level);
									}
									while (Static57.in.pos < Static223.packetSize) {
										slot = Static57.in.gSmart1or2();
										count = Static57.in.g2();
										i = 0;
										if (count != 0) {
											i = Static57.in.g1();
											if (i == 255) {
												i = Static57.in.g4();
											}
										}
										if (local4956 != null && slot >= 0 && local4956.objTypes.length > slot) {
											local4956.objTypes[slot] = count;
											local4956.objCounts[slot] = i;
										}
										ClientInvCache.update(count - 1, slot, i, xp);
									}
									if (local4956 != null) {
										Static43.method1143(local4956);
									}
									Static103.method2245();
									Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = xp & 0x7FFF;
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 24) {
									level = Static57.in.g2();
									if (Static248.method3288(level)) {
										Static35.method902();
									}
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 86) {
									Static278.processLogout();
									Static164.packetType = -1;
									return false;
								} else if (Static164.packetType == 116) {
									level = Static57.in.g1();
									if (Static57.in.g1() == 0) {
										Static229.aClass136Array1[level] = new StockMarketOffer();
									} else {
										Static57.in.pos--;
										Static229.aClass136Array1[level] = new StockMarketOffer(Static57.in);
									}
									Static164.packetType = -1;
									Static207.anInt4778 = Static119.transmitTimer;
									return true;
								} else if (Static164.packetType == 73) {
									level = Static57.in.g2sub();
									xp = Static57.in.g4me();
									if (level == 65535) {
										level = -1;
									}
									stat = Static57.in.g2le();
									if (Static248.method3288(stat)) {
										Static132.method2607(-1, 2, xp, level);
									}
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 162) {
									Static75.method1629(false);
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 165) {
									level = Static57.in.g2le();
									xp = Static57.in.g2le();
									if (xp == 65535) {
										xp = -1;
									}
									stat = Static57.in.g4();
									slot = Static57.in.g2sub();
									count = Static57.in.g4rme();
									if (slot == 65535) {
										slot = -1;
									}
									if (Static248.method3288(level)) {
										for (i = slot; i <= xp; i++) {
											local904 = ((long) stat << 32) + ((long) i);
											local1804 = (ServerActiveProperties) Static210.aClass133_21.getNode(local904);
											if (local1804 != null) {
												local1814 = new ServerActiveProperties(count, local1804.anInt540);
												local1804.remove();
											} else if (i == -1) {
												local1814 = new ServerActiveProperties(count, Static5.getComponent(stat).aClass3_Sub4_1.anInt540);
											} else {
												local1814 = new ServerActiveProperties(count, -1);
											}
											Static210.aClass133_21.pushNode(local1814, local904);
										}
									}
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 197) {
									Static166.anInt4054 = Static57.in.g1();
									Static185.anInt4369 = Static119.transmitTimer;
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 196) {
									local884 = Static57.in.g8();
									stat = Static57.in.g2();
									@Pc(5325) byte local5325 = Static57.in.g1s();
									ignored = false;
									if ((Long.MIN_VALUE & local884) != 0L) {
										ignored = true;
									}
									if (ignored) {
										if (Static214.anInt5577 == 0) {
											Static164.packetType = -1;
											return true;
										}
										local884 &= Long.MAX_VALUE;
										for (i = 0; Static214.anInt5577 > i && (local884 != Static199.aClass3_Sub22Array1[i].nodeId || stat != Static199.aClass3_Sub22Array1[i].anInt3340); i++) {
										}
										if (i < Static214.anInt5577) {
											while (Static214.anInt5577 - 1 > i) {
												Static199.aClass3_Sub22Array1[i] = Static199.aClass3_Sub22Array1[i + 1];
												i++;
											}
											Static214.anInt5577--;
											Static199.aClass3_Sub22Array1[Static214.anInt5577] = null;
										}
									} else {
										local506 = Static57.in.gjstr();
										@Pc(5347) Class3_Sub22 local5347 = new Class3_Sub22();
										local5347.nodeId = local884;
										local5347.aClass100_636 = Static79.decode37(local5347.nodeId);
										local5347.aByte9 = local5325;
										local5347.aClass100_635 = local506;
										local5347.anInt3340 = stat;
										for (local1986 = Static214.anInt5577 - 1; local1986 >= 0; local1986--) {
											local908 = Static199.aClass3_Sub22Array1[local1986].aClass100_636.method3139(local5347.aClass100_636);
											if (local908 == 0) {
												Static199.aClass3_Sub22Array1[local1986].anInt3340 = stat;
												Static199.aClass3_Sub22Array1[local1986].aByte9 = local5325;
												Static199.aClass3_Sub22Array1[local1986].aClass100_635 = local506;
												if (local884 == Static101.aLong98) {
													Static160.aByte14 = local5325;
												}
												Static278.anInt5867 = Static119.transmitTimer;
												Static164.packetType = -1;
												return true;
											}
											if (local908 < 0) {
												break;
											}
										}
										if (Static199.aClass3_Sub22Array1.length <= Static214.anInt5577) {
											Static164.packetType = -1;
											return true;
										}
										for (local908 = Static214.anInt5577 - 1; local908 > local1986; local908--) {
											Static199.aClass3_Sub22Array1[local908 + 1] = Static199.aClass3_Sub22Array1[local908];
										}
										if (Static214.anInt5577 == 0) {
											Static199.aClass3_Sub22Array1 = new Class3_Sub22[100];
										}
										Static199.aClass3_Sub22Array1[local1986 + 1] = local5347;
										if (Static101.aLong98 == local884) {
											Static160.aByte14 = local5325;
										}
										Static214.anInt5577++;
									}
									Static164.packetType = -1;
									Static278.anInt5867 = Static119.transmitTimer;
									return true;
								} else if (Static164.packetType == 50) {
									level = Static57.in.g4();
									xp = Static57.in.p4rme();
									stat = Static57.in.g2leadd();
									if (stat == 65535) {
										stat = -1;
									}
									slot = Static57.in.g2le();
									if (Static248.method3288(slot)) {
										@Pc(5603) Component local5603 = Static5.getComponent(xp);
										@Pc(5615) ObjType local5615;
										if (local5603.aBoolean32) {
											Static209.method3707(xp, level, stat);
											local5615 = Static71.get(stat);
											Static261.method4505(local5615.zoom2d, xp, local5615.yan2d, local5615.xan2d);
											Static145.method2745(xp, local5615.zan2d, local5615.yof2d, local5615.xof2d);
										} else if (stat == -1) {
											local5603.modelType = 0;
											Static164.packetType = -1;
											return true;
										} else {
											local5615 = Static71.get(stat);
											local5603.modelXAngle = local5615.xan2d;
											local5603.modelZoom = local5615.zoom2d * 100 / level;
											local5603.modelType = 4;
											local5603.modelId = stat;
											local5603.modelYAngle = local5615.yan2d;
											Static43.method1143(local5603);
										}
									}
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 105) {
									level = Static57.in.g4();
									xp = Static57.in.g2();
									if (level < -70000) {
										xp += 32768;
									}
									if (level >= 0) {
										local4956 = Static5.getComponent(level);
									} else {
										local4956 = null;
									}
									if (local4956 != null) {
										for (slot = 0; slot < local4956.objTypes.length; slot++) {
											local4956.objTypes[slot] = 0;
											local4956.objCounts[slot] = 0;
										}
									}
									Static14.method475(xp);
									slot = Static57.in.g2();
									for (count = 0; count < slot; count++) {
										i = Static57.in.g1_alt3();
										if (i == 255) {
											i = Static57.in.g4();
										}
										local1160 = Static57.in.g2();
										if (local4956 != null && count < local4956.objTypes.length) {
											local4956.objTypes[count] = local1160;
											local4956.objCounts[count] = i;
										}
										ClientInvCache.update(local1160 - 1, count, i, xp);
									}
									if (local4956 != null) {
										Static43.method1143(local4956);
									}
									Static103.method2245();
									Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = xp & 0x7FFF;
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 142) {
									Static230.method3954(Static57.in.gjstr());
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 26) {
									Static115.anInt2940 = Static57.in.p1neg();
									Static180.anInt4264 = Static57.in.g1();
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 4) {
									level = Static57.in.g2leadd();
									if (level == 65535) {
										level = -1;
									}
									Static148.method2765(level);
									Static164.packetType = -1;
									return true;
								} else if (Static164.packetType == 208) {
									level = Static57.in.g3le();
									xp = Static57.in.g2le();
									if (xp == 65535) {
										xp = -1;
									}
									Static278.method4650(level, xp);
									Static164.packetType = -1;
									return true;
								} else {
									Static89.report("T1 - " + Static164.packetType + "," + Static5.anInt45 + "," + Static49.anInt1462 + " - " + Static223.packetSize, null);
									Static278.processLogout();
									return true;
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "b", descriptor = "(I)V")
	public static void method28() {
		Static43.method1143(Static105.aClass13_14);
		Static213.anInt4851++;
		if (Static44.aBoolean83 && Static146.aBoolean174) {
			@Pc(30) int local30 = Static215.anInt4873;
			local30 -= Static246.anInt5388;
			if (Static81.anInt2225 > local30) {
				local30 = Static81.anInt2225;
			}
			@Pc(41) int local41 = Static223.anInt5032;
			if (Static81.anInt2225 + aClass13_1.anInt445 < local30 - -Static105.aClass13_14.anInt445) {
				local30 = Static81.anInt2225 + aClass13_1.anInt445 - Static105.aClass13_14.anInt445;
			}
			local41 -= Static165.anInt4035;
			if (local41 < Static228.anInt5103) {
				local41 = Static228.anInt5103;
			}
			if (Static228.anInt5103 + aClass13_1.anInt459 < local41 - -Static105.aClass13_14.anInt459) {
				local41 = Static228.anInt5103 + aClass13_1.anInt459 - Static105.aClass13_14.anInt459;
			}
			@Pc(109) int local109 = local41 - Static20.anInt660;
			@Pc(114) int local114 = local30 - Static124.anInt3075;
			@Pc(122) int local122 = local30 + aClass13_1.anInt489 - Static81.anInt2225;
			@Pc(130) int local130 = aClass13_1.scrollY + local41 - Static228.anInt5103;
			@Pc(133) int local133 = Static105.aClass13_14.anInt472;
			if (Static213.anInt4851 > Static105.aClass13_14.anInt447 && (local133 < local114 || -local133 > local114 || local109 > local133 || local109 < -local133)) {
				Static138.aBoolean172 = true;
			}
			@Pc(176) HookRequest local176;
			if (Static105.aClass13_14.anObjectArray26 != null && Static138.aBoolean172) {
				local176 = new HookRequest();
				local176.source = Static105.aClass13_14;
				local176.anObjectArray31 = Static105.aClass13_14.anObjectArray26;
				local176.anInt3102 = local122;
				local176.anInt3097 = local130;
				Static82.method1767(local176);
			}
			if (Static22.anInt723 == 0) {
				if (Static138.aBoolean172) {
					if (Static105.aClass13_14.anObjectArray16 != null) {
						local176 = new HookRequest();
						local176.anInt3097 = local130;
						local176.aClass13_16 = Static56.aClass13_12;
						local176.anInt3102 = local122;
						local176.anObjectArray31 = Static105.aClass13_14.anObjectArray16;
						local176.source = Static105.aClass13_14;
						Static82.method1767(local176);
					}
					if (Static56.aClass13_12 != null && Static36.method938(Static105.aClass13_14) != null) {
						Static6.outboundBuffer.pIsaac1(79);
						Static6.outboundBuffer.p4_alt3(Static105.aClass13_14.anInt507);
						Static6.outboundBuffer.p2_alt1(Static56.aClass13_12.componentId);
						Static6.outboundBuffer.p4(Static56.aClass13_12.anInt507);
						Static6.outboundBuffer.p2_alt1(Static105.aClass13_14.componentId);
					}
				} else if ((Static116.anInt2952 == 1 || Static277.method4640(PreciseSleep.anInt5204 - 1)) && PreciseSleep.anInt5204 > 2) {
					Static226.method3901();
				} else if (PreciseSleep.anInt5204 > 0) {
					Static59.method1372();
				}
				Static105.aClass13_14 = null;
			}
		} else if (Static213.anInt4851 > 1) {
			Static105.aClass13_14 = null;
		}
	}
}
