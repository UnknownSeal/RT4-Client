package com.jagex.runetek4;

import java.io.IOException;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.dash3d.entity.SpotAnimEntity;
import com.jagex.runetek4.frame.Minimap;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static4 {

	@OriginalMember(owner = "runetek4.client!ac", name = "e", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray1;

	@OriginalMember(owner = "runetek4.client!ac", name = "k", descriptor = "S")
	public static short aShort1 = 32767;

	@OriginalMember(owner = "runetek4.client!ac", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_7 = JString.parse("overlay");

	@OriginalMember(owner = "runetek4.client!ac", name = "n", descriptor = "I")
	public static int selectedInventorySlot = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "o", descriptor = "I")
	public static int updatedVarcsWriterIndex = 0;

	@OriginalMember(owner = "runetek4.client!ac", name = "p", descriptor = "Lclient!be;")
	public static Component aClass13_1 = null;

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(IIII)I")
	public static int getRenderLevel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		if ((SceneGraph.renderFlags[arg2][arg1][arg0] & 0x8) == 0) {
			return arg2 <= 0 || (SceneGraph.renderFlags[1][arg1][arg0] & 0x2) == 0 ? arg2 : arg2 - 1;
		} else {
			return 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(BI)V")
	public static void method24(@OriginalArg(1) int arg0) {
		@Pc(16) DelayedStateChange local16 = Static238.method4143(1, arg0);
		local16.method1007();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(Lclient!na;I)I")
	public static int method25(@OriginalArg(0) JString arg0) {
		if (arg0 == null) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < FriendList.friendCount; local20++) {
			if (arg0.equalsIgnoreCase(Static122.friendName[local20])) {
				return local20;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(B)Z")
	public static boolean method26() throws IOException {
		if (Static124.gameServerSocket == null) {
			return false;
		}
		@Pc(14) int available = Static124.gameServerSocket.available();
		if (available == 0) {
			return false;
		}
		if (Protocol.opcode == -1) {
			available--;
			Static124.gameServerSocket.read(0, 1, Protocol.inboundBuffer.data);
			Protocol.inboundBuffer.offset = 0;
			Protocol.opcode = Protocol.inboundBuffer.gIssac1();
			Static223.packetSize = Static234.anIntArray456[Protocol.opcode];
		}
		if (Static223.packetSize == -1) {
			if (available <= 0) {
				return false;
			}
			Static124.gameServerSocket.read(0, 1, Protocol.inboundBuffer.data);
			available--;
			Static223.packetSize = Protocol.inboundBuffer.data[0] & 0xFF;
		}
		if (Static223.packetSize == -2) {
			if (available <= 1) {
				return false;
			}
			available -= 2;
			Static124.gameServerSocket.read(0, 2, Protocol.inboundBuffer.data);
			Protocol.inboundBuffer.offset = 0;
			Static223.packetSize = Protocol.inboundBuffer.g2();
		}
		if (Static223.packetSize > available) {
			return false;
		}
		Protocol.inboundBuffer.offset = 0;
		Static124.gameServerSocket.read(0, Static223.packetSize, Protocol.inboundBuffer.data);
		Protocol.opcode4 = Protocol.opcode3;
		Protocol.opcode3 = Protocol.opcode2;
		Protocol.opcode2 = Protocol.opcode;
		LoginManager.idleNetCycles = 0;
		@Pc(133) int ii;
		if (Protocol.opcode == 60) {
			ii = Protocol.inboundBuffer.g2sub();
			@Pc(137) byte local137 = Protocol.inboundBuffer.g1neg();
			ObjTypeList.method2575(local137, ii);
			Protocol.opcode = -1;
			return true;
		}
		@Pc(171) int slot;
		@Pc(156) JString message2;
		if (Protocol.opcode == 115) {
			ii = Protocol.inboundBuffer.g2();
			message2 = Protocol.inboundBuffer.gjstr();
			@Pc(163) Object[] local163 = new Object[message2.length() + 1];
			for (slot = message2.length() - 1; slot >= 0; slot--) {
				if (message2.charAt(slot) == 115) {
					local163[slot + 1] = Protocol.inboundBuffer.gjstr();
				} else {
					local163[slot + 1] = Integer.valueOf(Protocol.inboundBuffer.g4());
				}
			}
			local163[0] = Integer.valueOf(Protocol.inboundBuffer.g4());
			if (Static248.method3288(ii)) {
				@Pc(226) HookRequest local226 = new HookRequest();
				local226.anObjectArray31 = local163;
				ClientScriptRunner.run(local226);
			}
			Protocol.opcode = -1;
			return true;
		}
		@Pc(275) long username;
		@Pc(262) boolean ignored;
		@Pc(277) int i;
		@Pc(506) JString local506;
		if (Protocol.opcode == 70) {
			@Pc(245) JString message = Protocol.inboundBuffer.gjstr();
			if (message.endsWith(Static196.TRADEREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				ignored = false;
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Chat.addMessage(message2, 4, LocalizedText.TRADEREQ);
				}
			} else if (message.endsWith(Static61.CHALREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				ignored = false;
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					local506 = message.substring(message.length() - 9, message.indexOf(Static264.aClass100_875) + 1);
					Chat.addMessage(message2, 8, local506);
				}
			} else if (message.endsWith(Static191.ASSISTREQ)) {
				ignored = false;
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (username == Static190.ignoreName37[i]) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Chat.addMessage(message2, 10, JString.EMPTY);
				}
			} else if (message.endsWith(Static141.CLAN)) {
				message2 = message.substring(message.indexOf(Static141.CLAN), 0);
				Chat.addMessage(JString.EMPTY, 11, message2);
			} else if (message.endsWith(Static138.TRADE)) {
				message2 = message.substring(message.indexOf(Static138.TRADE), 0);
				if (Player.overrideChat == 0) {
					Chat.addMessage(JString.EMPTY, 12, message2);
				}
			} else if (message.endsWith(Static244.ASSIST)) {
				message2 = message.substring(message.indexOf(Static244.ASSIST), 0);
				if (Player.overrideChat == 0) {
					Chat.addMessage(JString.EMPTY, 13, message2);
				}
			} else if (message.endsWith(Static56.DUELSTAKE)) {
				ignored = false;
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (username == Static190.ignoreName37[i]) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Chat.addMessage(message2, 14, JString.EMPTY);
				}
			} else if (message.endsWith(Static112.DUELFRIEND)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				ignored = false;
				username = message2.encode37();
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Chat.addMessage(message2, 15, JString.EMPTY);
				}
			} else if (message.endsWith(Static217.CLANREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				username = message2.encode37();
				ignored = false;
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (username == Static190.ignoreName37[i]) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					Chat.addMessage(message2, 16, JString.EMPTY);
				}
			} else if (message.endsWith(Static164.ALLYREQ)) {
				message2 = message.substring(message.indexOf(Static264.aClass100_875), 0);
				ignored = false;
				username = message2.encode37();
				for (i = 0; i < Static35.ignoreCount; i++) {
					if (Static190.ignoreName37[i] == username) {
						ignored = true;
						break;
					}
				}
				if (!ignored && Player.overrideChat == 0) {
					local506 = message.substring(message.length() - 9, message.indexOf(Static264.aClass100_875) + 1);
					Chat.addMessage(message2, 21, local506);
				}
			} else {
				Chat.addMessage(JString.EMPTY, 0, message);
			}
			Protocol.opcode = -1;
			return true;
		}
		@Pc(786) int xp;
		@Pc(790) JString local790;
		if (Protocol.opcode == 123) {
			ii = Protocol.inboundBuffer.g2le();
			xp = Protocol.inboundBuffer.g2sub();
			local790 = Protocol.inboundBuffer.gjstr();
			if (Static248.method3288(xp)) {
				Static193.method3498(local790, ii);
			}
			Protocol.opcode = -1;
			return true;
		} else if (Protocol.opcode == 230) {
			Static180.anInt4264 = Protocol.inboundBuffer.g1add();
			Static115.anInt2940 = Protocol.inboundBuffer.g1_alt3();
			while (Static223.packetSize > Protocol.inboundBuffer.offset) {
				Protocol.opcode = Protocol.inboundBuffer.g1();
				Static75.method1634();
			}
			Protocol.opcode = -1;
			return true;
		} else if (Protocol.opcode == 153) {
			Protocol.opcode = -1;
			LoginManager.mapFlagX = 0;
			return true;
		} else {
			@Pc(864) int world;
			if (Protocol.opcode == 220) {
				ii = Protocol.inboundBuffer.p4rme();
				xp = Protocol.inboundBuffer.g2le();
				world = Protocol.inboundBuffer.g2();
				if (Static248.method3288(world)) {
					Static229.method3938(xp, ii);
				}
				Protocol.opcode = -1;
				return true;
			}
			@Pc(884) long username2;
			@Pc(908) int local908;
			@Pc(916) int local916;
			@Pc(899) long local899;
			@Pc(904) long local904;
			if (Protocol.opcode == 81) {
				username2 = Protocol.inboundBuffer.g8();
				Protocol.inboundBuffer.g1s();
				username = Protocol.inboundBuffer.g8();
				local899 = Protocol.inboundBuffer.g2();
				local904 = Protocol.inboundBuffer.g3();
				local908 = Protocol.inboundBuffer.g1();
				@Pc(910) boolean local910 = false;
				local916 = Protocol.inboundBuffer.g2();
				@Pc(922) long local922 = (local899 << 32) + local904;
				@Pc(924) int local924 = 0;
				label1320: while (true) {
					if (local924 < 100) {
						if (local922 != Chat.recentMessages[local924]) {
							local924++;
							continue;
						}
						local910 = true;
						break;
					}
					if (local908 <= 1) {
						for (local924 = 0; local924 < Static35.ignoreCount; local924++) {
							if (Static190.ignoreName37[local924] == username2) {
								local910 = true;
								break label1320;
							}
						}
					}
					break;
				}
				if (!local910 && Player.overrideChat == 0) {
					Chat.recentMessages[Chat.messageCounter] = local922;
					Chat.messageCounter = (Chat.messageCounter + 1) % 100;
					@Pc(999) JString local999 = Static230.list(local916).method770(Protocol.inboundBuffer);
					if (local908 == 2 || local908 == 3) {
						Chat.add(local916, 20, local999, Base37.decode37(username).method3125(), JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).method3125() }));
					} else if (local908 == 1) {
						Chat.add(local916, 20, local999, Base37.decode37(username).method3125(), JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).method3125() }));
					} else {
						Chat.add(local916, 20, local999, Base37.decode37(username).method3125(), Base37.decode37(username2).method3125());
					}
				}
				Protocol.opcode = -1;
				return true;
			}
			@Pc(1146) int count;
			@Pc(1160) int local1160;
			@Pc(1245) boolean local1245;
			if (Protocol.opcode == 55) {
				ClanChat.transmitAt = InterfaceList.transmitTimer;
				username2 = Protocol.inboundBuffer.g8();
				if (username2 == 0L) {
					ClanChat.owner = null;
					Protocol.opcode = -1;
					ClanChat.name = null;
					ClanChat.members = null;
					ClanChat.size = 0;
					return true;
				}
				username = Protocol.inboundBuffer.g8();
				ClanChat.name = Base37.decode37(username);
				ClanChat.owner = Base37.decode37(username2);
				ClanChat.minKick = Protocol.inboundBuffer.g1s();
				count = Protocol.inboundBuffer.g1();
				if (count == 255) {
					Protocol.opcode = -1;
					return true;
				}
				ClanChat.size = count;
				@Pc(1158) ClanMember[] local1158 = new ClanMember[100];
				for (local1160 = 0; local1160 < ClanChat.size; local1160++) {
					local1158[local1160] = new ClanMember();
					local1158[local1160].nodeId = Protocol.inboundBuffer.g8();
					local1158[local1160].username = Base37.decode37(local1158[local1160].nodeId);
					local1158[local1160].world = Protocol.inboundBuffer.g2();
					local1158[local1160].rank = Protocol.inboundBuffer.g1s();
					local1158[local1160].worldName = Protocol.inboundBuffer.gjstr();
					if (Static101.aLong98 == local1158[local1160].nodeId) {
						ClanChat.rank = local1158[local1160].rank;
					}
				}
				local908 = ClanChat.size;
				while (local908 > 0) {
					local1245 = true;
					local908--;
					for (local916 = 0; local916 < local908; local916++) {
						if (local1158[local916].username.method3139(local1158[local916 + 1].username) > 0) {
							local1245 = false;
							@Pc(1279) ClanMember local1279 = local1158[local916];
							local1158[local916] = local1158[local916 + 1];
							local1158[local916 + 1] = local1279;
						}
					}
					if (local1245) {
						break;
					}
				}
				ClanChat.members = local1158;
				Protocol.opcode = -1;
				return true;
			} else if (Protocol.opcode == 164) {
				ii = Protocol.inboundBuffer.g4rme();
				Static232.aClass212_5 = GameShell.signLink.getReverseDns(ii);
				Protocol.opcode = -1;
				return true;
			} else if (Protocol.opcode == 225) {
				// PLAYER_INFO
				Static64.getPlayer();
				Protocol.opcode = -1;
				return true;
			} else if (Protocol.opcode == 48) {
				ii = Protocol.inboundBuffer.g2();
				message2 = Protocol.inboundBuffer.gjstr();
				world = Protocol.inboundBuffer.g2leadd();
				if (Static248.method3288(ii)) {
					Static193.method3498(message2, world);
				}
				Protocol.opcode = -1;
				return true;
			} else if (Protocol.opcode == 232) {
				Chat.publicFilter = Protocol.inboundBuffer.g1();
				Chat.privateFilter = Protocol.inboundBuffer.g1();
				Chat.tradeFilter = Protocol.inboundBuffer.g1();
				Protocol.opcode = -1;
				return true;
			} else {
				@Pc(1409) JString local1409;
				if (Protocol.opcode == 44) {
					ii = Protocol.inboundBuffer.g2leadd();
					if (ii == 65535) {
						ii = -1;
					}
					xp = Protocol.inboundBuffer.g1();
					world = Protocol.inboundBuffer.g1();
					local1409 = Protocol.inboundBuffer.gjstr();
					if (world >= 1 && world <= 8) {
						if (local1409.equalsIgnoreCase(Static92.aClass100_510)) {
							local1409 = null;
						}
						Player.options[world - 1] = local1409;
						Player.cursors[world - 1] = ii;
						Player.secondaryOptions[world - 1] = xp == 0;
					}
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 226) {
					ii = Protocol.inboundBuffer.g4();
					xp = Protocol.inboundBuffer.g2sub();
					ObjTypeList.method2575(ii, xp);
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 21) {
					ii = Protocol.inboundBuffer.p1neg();
					xp = Protocol.inboundBuffer.g2();
					world = Protocol.inboundBuffer.g4me();
					if (Static248.method3288(xp)) {
						Static153.method2905(world, ii);
					}
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 145) {
					ii = Protocol.inboundBuffer.g2leadd();
					xp = Protocol.inboundBuffer.g1add();
					world = Protocol.inboundBuffer.g2leadd();
					if (Static248.method3288(world)) {
						if (xp == 2) {
							Static5.method34();
						}
						InterfaceList.topLevelInterace = ii;
						com.jagex.runetek4.cache.def.ItemDefinition.method1753(ii);
						Static210.method3712(false);
						Static74.method1626(InterfaceList.topLevelInterace);
						for (slot = 0; slot < 100; slot++) {
							InterfaceList.aBooleanArray100[slot] = true;
						}
					}
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 69) {
					ii = Protocol.inboundBuffer.g2leadd();
					xp = Protocol.inboundBuffer.g4();
					world = Protocol.inboundBuffer.g2sub();
					if (Static248.method3288(ii)) {
						Static132.method2606(world, xp);
					}
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 141) {
					username2 = Protocol.inboundBuffer.g8();
					world = Protocol.inboundBuffer.g2();
					local1409 = Static230.list(world).method770(Protocol.inboundBuffer);
					Chat.add(world, 19, local1409, null, Base37.decode37(username2).method3125());
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 169) {
					Static271.method4598(Protocol.inboundBuffer);
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 89) {
					VarpDomain.resetVarBits();
					Static103.method2245();
					Static70.updatedVarpsWriterIndex += 32;
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 125) {
					ii = Protocol.inboundBuffer.g2();
					xp = Protocol.inboundBuffer.g1();
					world = Protocol.inboundBuffer.g1();
					slot = Protocol.inboundBuffer.g2();
					count = Protocol.inboundBuffer.g1();
					i = Protocol.inboundBuffer.g1();
					if (Static248.method3288(ii)) {
						Camera.method3849(slot, world, count, xp, i);
					}
					Protocol.opcode = -1;
					return true;
				} else if (Protocol.opcode == 36) {
					ii = Protocol.inboundBuffer.p4rme();
					xp = Protocol.inboundBuffer.g2les();
					world = Protocol.inboundBuffer.g2sub();
					if (Static248.method3288(world)) {
						Static225.method3893(ii, xp);
					}
					Protocol.opcode = -1;
					return true;
				} else {
					@Pc(1814) ServerActiveProperties local1814;
					@Pc(1804) ServerActiveProperties local1804;
					if (Protocol.opcode == 9) {
						ii = Protocol.inboundBuffer.g2leadd();
						xp = Protocol.inboundBuffer.g4me();
						world = Protocol.inboundBuffer.g2sub();
						slot = Protocol.inboundBuffer.g2le();
						if (slot == 65535) {
							slot = -1;
						}
						count = Protocol.inboundBuffer.g2sub();
						if (count == 65535) {
							count = -1;
						}
						if (Static248.method3288(world)) {
							for (i = count; i <= slot; i++) {
								local904 = (long) i + ((long) xp << 32);
								local1804 = (ServerActiveProperties) Static210.aClass133_21.getNode(local904);
								if (local1804 != null) {
									local1814 = new ServerActiveProperties(local1804.anInt546, ii);
									local1804.unlink();
								} else if (i == -1) {
									local1814 = new ServerActiveProperties(InterfaceList.getComponent(xp).aClass3_Sub4_1.anInt546, ii);
								} else {
									local1814 = new ServerActiveProperties(0, ii);
								}
								Static210.aClass133_21.put(local1814, local904);
							}
						}
						Protocol.opcode = -1;
						return true;
					}
					@Pc(1986) int j;
					if (Protocol.opcode == 56) {
						ii = Protocol.inboundBuffer.g2();
						xp = Protocol.inboundBuffer.g2le();
						world = Protocol.inboundBuffer.g4rme();
						slot = Protocol.inboundBuffer.g2leadd();
						if (world >> 30 == 0) {
							@Pc(1994) SeqType local1994;
							if (world >> 29 != 0) {
								count = world & 0xFFFF;
								@Pc(1894) Npc local1894 = NpcList.npcs[count];
								if (local1894 != null) {
									if (slot == 65535) {
										slot = -1;
									}
									local1245 = true;
									if (slot != -1 && local1894.spotanimFrame != -1 && SeqType.getAnimationSequence(Static34.method877(slot).animationId).priority < SeqType.getAnimationSequence(Static34.method877(local1894.spotanimFrame).animationId).priority) {
										local1245 = false;
									}
									if (local1245) {
										local1894.anInt3361 = 0;
										local1894.spotanimFrame = slot;
										local1894.spotanimLastCycle = client.loop + ii;
										local1894.spotanimId = 0;
										if (local1894.spotanimLastCycle > client.loop) {
											local1894.spotanimId = -1;
										}
										local1894.spotanimOffset = xp;
										local1894.anInt3418 = 1;
										if (local1894.spotanimFrame != -1 && client.loop == local1894.spotanimLastCycle) {
											j = Static34.method877(local1894.spotanimFrame).animationId;
											if (j != -1) {
												local1994 = SeqType.getAnimationSequence(j);
												if (local1994 != null && local1994.anIntArray473 != null) {
													Static152.method2836(local1894.zFine, local1994, local1894.xFine, false, 0);
												}
											}
										}
									}
								}
							} else if (world >> 28 != 0) {
								count = world & 0xFFFF;
								@Pc(2033) Player local2033;
								if (PlayerList.selfId == count) {
									local2033 = PlayerList.self;
								} else {
									local2033 = PlayerList.players[count];
								}
								if (local2033 != null) {
									if (slot == 65535) {
										slot = -1;
									}
									local1245 = true;
									if (slot != -1 && local2033.spotanimFrame != -1 && SeqType.getAnimationSequence(Static34.method877(slot).animationId).priority < SeqType.getAnimationSequence(Static34.method877(local2033.spotanimFrame).animationId).priority) {
										local1245 = false;
									}
									if (local1245) {
										local2033.spotanimLastCycle = ii + client.loop;
										local2033.spotanimOffset = xp;
										local2033.spotanimFrame = slot;
										if (local2033.spotanimFrame == 65535) {
											local2033.spotanimFrame = -1;
										}
										local2033.anInt3418 = 1;
										local2033.anInt3361 = 0;
										local2033.spotanimId = 0;
										if (local2033.spotanimLastCycle > client.loop) {
											local2033.spotanimId = -1;
										}
										if (local2033.spotanimFrame != -1 && local2033.spotanimLastCycle == client.loop) {
											j = Static34.method877(local2033.spotanimFrame).animationId;
											if (j != -1) {
												local1994 = SeqType.getAnimationSequence(j);
												if (local1994 != null && local1994.anIntArray473 != null) {
													Static152.method2836(local2033.zFine, local1994, local2033.xFine, local2033 == PlayerList.self, 0);
												}
											}
										}
									}
								}
							}
						} else {
							count = world >> 28 & 0x3;
							i = (world >> 14 & 0x3FFF) - Camera.originX;
							local1160 = (world & 0x3FFF) - Camera.originZ;
							if (i >= 0 && local1160 >= 0 && i < 104 && local1160 < 104) {
								local1160 = local1160 * 128 + 64;
								i = i * 128 + 64;
								@Pc(2241) SpotAnim local2241 = new SpotAnim(slot, count, i, local1160, SceneGraph.getTileHeight(count, i, local1160) - xp, ii, client.loop);
								SceneGraph.spotanims.addTail(new SpotAnimEntity(local2241));
							}
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 207) {
						ii = Protocol.inboundBuffer.p4rme();
						xp = Protocol.inboundBuffer.g2sub();
						world = Protocol.inboundBuffer.g2();
						slot = Protocol.inboundBuffer.g2sub();
						if (Static248.method3288(xp)) {
							Static190.method3444(slot + (world << 16), ii);
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 38) {
						// UPDATE_STAT
						Static103.method2245();
						ii = Protocol.inboundBuffer.g1add();
						xp = Protocol.inboundBuffer.g4rme();
						world = Protocol.inboundBuffer.g1();
						PlayerSkillXpTable.skillExperience[world] = xp;
						PlayerSkillXpTable.skillLevel[world] = ii;
						PlayerSkillXpTable.skillBaseLevel[world] = 1;
						for (slot = 0; slot < 98; slot++) {
							if (com.jagex.runetek4.cache.def.ItemDefinition.levelExperience[slot] <= xp) {
								PlayerSkillXpTable.skillBaseLevel[world] = slot + 2;
							}
						}
						Static249.anIntArray478[Static89.anInt2385++ & 0x1F] = world;
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 104 || Protocol.opcode == 121 || Protocol.opcode == 97 || Protocol.opcode == 14 || Protocol.opcode == 202 || Protocol.opcode == 135 || Protocol.opcode == 17 || Protocol.opcode == 16 || Protocol.opcode == 240 || Protocol.opcode == 33 || Protocol.opcode == 20 || Protocol.opcode == 195 || Protocol.opcode == 179) {
						Static75.method1634();
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 149) {
						ii = Protocol.inboundBuffer.g2();
						xp = Protocol.inboundBuffer.g4();
						if (Static248.method3288(ii)) {
							@Pc(2441) ComponentPointer local2441 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) xp);
							if (local2441 != null) {
								InterfaceList.closeInterface(true, local2441);
							}
							if (ClientScriptRunner.aClass13_10 != null) {
								Static43.method1143(ClientScriptRunner.aClass13_10);
								ClientScriptRunner.aClass13_10 = null;
							}
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 187) {
						ii = Protocol.inboundBuffer.g2le();
						xp = Protocol.inboundBuffer.g2();
						world = Protocol.inboundBuffer.g2();
						if (Static248.method3288(xp)) {
							Camera.orbitCameraYaw = ii;
							Camera.orbitCameraPitch = world;
							if (Camera.cameraType == 2) {
								Camera.cameraPitch = Camera.orbitCameraPitch;
								Camera.cameraYaw = Camera.orbitCameraYaw;
							}
							SceneCamera.clampCameraAngle();
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 132) {
						ii = Protocol.inboundBuffer.g2();
						xp = Protocol.inboundBuffer.g2sub();
						world = Protocol.inboundBuffer.g2leadd();
						slot = Protocol.inboundBuffer.g2leadd();
						count = Protocol.inboundBuffer.g4();
						if (Static248.method3288(xp)) {
							Static261.method4505(world, count, slot, ii);
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 112) {
						Static115.anInt2940 = Protocol.inboundBuffer.g1();
						Static180.anInt4264 = Protocol.inboundBuffer.p1neg();
						for (ii = Static115.anInt2940; ii < Static115.anInt2940 + 8; ii++) {
							for (xp = Static180.anInt4264; xp < Static180.anInt4264 + 8; xp++) {
								if (SceneGraph.objStacks[Player.plane][ii][xp] != null) {
									SceneGraph.objStacks[Player.plane][ii][xp] = null;
									Static220.method3797(xp, ii);
								}
							}
						}
						for (@Pc(2604) ChangeLocRequest local2604 = (ChangeLocRequest) ChangeLocRequest.queue.head(); local2604 != null; local2604 = (ChangeLocRequest) ChangeLocRequest.queue.next()) {
							if (local2604.x >= Static115.anInt2940 && Static115.anInt2940 + 8 > local2604.x && local2604.z >= Static180.anInt4264 && local2604.z < Static180.anInt4264 + 8 && local2604.level == Player.plane) {
								local2604.resetLoops = 0;
							}
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 144) {
						ii = Protocol.inboundBuffer.p4rme();
						@Pc(2666) Component local2666 = InterfaceList.getComponent(ii);
						for (world = 0; world < local2666.invSlotObjId.length; world++) {
							local2666.invSlotObjId[world] = -1;
							local2666.invSlotObjId[world] = 0;
						}
						Static43.method1143(local2666);
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 130) {
						ii = Protocol.inboundBuffer.g4me();
						xp = Protocol.inboundBuffer.g2leadd();
						world = Protocol.inboundBuffer.g2sub();
						if (world == 65535) {
							world = -1;
						}
						if (Static248.method3288(xp)) {
							Static132.method2607(-1, 1, ii, world);
						}
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 192) {
						Minimap.state = Protocol.inboundBuffer.g1();
						Protocol.opcode = -1;
						return true;
					} else if (Protocol.opcode == 13) {
						ii = Protocol.inboundBuffer.g1_alt3();
						xp = Protocol.inboundBuffer.g1add();
						world = Protocol.inboundBuffer.g1();
						Player.plane = xp >> 1;
						PlayerList.self.teleport(ii, (xp & 0x1) == 1, world);
						Protocol.opcode = -1;
						return true;
					} else {
						@Pc(3002) int local3002;
						@Pc(3038) JString local3038;
						@Pc(3020) JString local3020;
						if (Protocol.opcode == 62) {
							username2 = Protocol.inboundBuffer.g8();
							world = Protocol.inboundBuffer.g2();
							slot = Protocol.inboundBuffer.g1();
							ignored = true;
							if (username2 < 0L) {
								username2 &= Long.MAX_VALUE;
								ignored = false;
							}
							local506 = JString.EMPTY;
							if (world > 0) {
								local506 = Protocol.inboundBuffer.gjstr();
							}
							@Pc(2834) JString displayName = Base37.decode37(username2).method3125();
							for (j = 0; j < FriendList.friendCount; j++) {
								if (username2 == Static92.friendName37[j]) {
									if (world != Static104.friendWorld[j]) {
										Static104.friendWorld[j] = world;
										if (world > 0) {
											Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGIN}));
										}
										if (world == 0) {
											Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGOUT}));
										}
									}
									Static214.aClass100Array170[j] = local506;
									Static106.anIntArray258[j] = slot;
									displayName = null;
									Static3.aBooleanArray135[j] = ignored;
									break;
								}
							}
							if (displayName != null && FriendList.friendCount < 200) {
								Static92.friendName37[FriendList.friendCount] = username2;
								Static122.friendName[FriendList.friendCount] = displayName;
								Static104.friendWorld[FriendList.friendCount] = world;
								Static214.aClass100Array170[FriendList.friendCount] = local506;
								Static106.anIntArray258[FriendList.friendCount] = slot;
								Static3.aBooleanArray135[FriendList.friendCount] = ignored;
								FriendList.friendCount++;
							}
							Static185.anInt4369 = InterfaceList.transmitTimer;
							local908 = FriendList.friendCount;
							while (local908 > 0) {
								local908--;
								@Pc(2961) boolean local2961 = true;
								for (local916 = 0; local916 < local908; local916++) {
									if (Static104.friendWorld[local916] != Static125.worldId && Static125.worldId == Static104.friendWorld[local916 + 1] || Static104.friendWorld[local916] == 0 && Static104.friendWorld[local916 + 1] != 0) {
										local2961 = false;
										local3002 = Static104.friendWorld[local916];
										Static104.friendWorld[local916] = Static104.friendWorld[local916 + 1];
										Static104.friendWorld[local916 + 1] = local3002;
										local3020 = Static214.aClass100Array170[local916];
										Static214.aClass100Array170[local916] = Static214.aClass100Array170[local916 + 1];
										Static214.aClass100Array170[local916 + 1] = local3020;
										local3038 = Static122.friendName[local916];
										Static122.friendName[local916] = Static122.friendName[local916 + 1];
										Static122.friendName[local916 + 1] = local3038;
										@Pc(3056) long local3056 = Static92.friendName37[local916];
										Static92.friendName37[local916] = Static92.friendName37[local916 + 1];
										Static92.friendName37[local916 + 1] = local3056;
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
							Protocol.opcode = -1;
							return true;
						} else if (Protocol.opcode == 160) {
							if (Static223.packetSize == 0) {
								MiniMenu.walkText = LocalizedText.WALKHERE;
							} else {
								MiniMenu.walkText = Protocol.inboundBuffer.gjstr();
							}
							Protocol.opcode = -1;
							return true;
						} else if (Protocol.opcode == 128) {
							for (ii = 0; ii < VarPlayerDefinition.varPlayers.length; ii++) {
								if (VarPlayerDefinition.varPlayerCache[ii] != VarPlayerDefinition.varPlayers[ii]) {
									VarPlayerDefinition.varPlayers[ii] = VarPlayerDefinition.varPlayerCache[ii];
									Static85.handleVarps(ii);
									Static83.updatedVarps[Static70.updatedVarpsWriterIndex++ & 0x1F] = ii;
								}
							}
							Protocol.opcode = -1;
							return true;
						} else if (Protocol.opcode == 154) {
							ii = Protocol.inboundBuffer.g2();
							xp = Protocol.inboundBuffer.g1();
							world = Protocol.inboundBuffer.g1();
							slot = Protocol.inboundBuffer.g2();
							count = Protocol.inboundBuffer.g1();
							i = Protocol.inboundBuffer.g1();
							if (Static248.method3288(ii)) {
								Camera.method2722(true, count, slot, i, world, xp);
							}
							Protocol.opcode = -1;
							return true;
						} else if (Protocol.opcode == 247) {
							username2 = Protocol.inboundBuffer.g8();
							username = Protocol.inboundBuffer.g2();
							local899 = Protocol.inboundBuffer.g3();
							local1160 = Protocol.inboundBuffer.g1();
							j = Protocol.inboundBuffer.g2();
							@Pc(3263) boolean local3263 = false;
							@Pc(3270) long local3270 = (username << 32) + local899;
							@Pc(3272) int local3272 = 0;
							label1402: while (true) {
								if (local3272 < 100) {
									if (local3270 != Chat.recentMessages[local3272]) {
										local3272++;
										continue;
									}
									local3263 = true;
									break;
								}
								if (local1160 <= 1) {
									for (local3272 = 0; local3272 < Static35.ignoreCount; local3272++) {
										if (username2 == Static190.ignoreName37[local3272]) {
											local3263 = true;
											break label1402;
										}
									}
								}
								break;
							}
							if (!local3263 && Player.overrideChat == 0) {
								Chat.recentMessages[Chat.messageCounter] = local3270;
								Chat.messageCounter = (Chat.messageCounter + 1) % 100;
								local3020 = Static230.list(j).method770(Protocol.inboundBuffer);
								if (local1160 == 2) {
									Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).method3125() }));
								} else if (local1160 == 1) {
									Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).method3125() }));
								} else {
									Chat.add(j, 18, local3020, null, Base37.decode37(username2).method3125());
								}
							}
							Protocol.opcode = -1;
							return true;
						} else {
							@Pc(3456) ComponentPointer local3456;
							if (Protocol.opcode == 176) {
								ii = Protocol.inboundBuffer.g4rme();
								xp = Protocol.inboundBuffer.g2sub();
								world = Protocol.inboundBuffer.g4rme();
								if (Static248.method3288(xp)) {
									@Pc(3449) ComponentPointer local3449 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) ii);
									local3456 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) world);
									if (local3456 != null) {
										InterfaceList.closeInterface(local3449 == null || local3456.anInt5878 != local3449.anInt5878, local3456);
									}
									if (local3449 != null) {
										local3449.unlink();
										InterfaceList.openInterfaces.put(local3449, (long) world);
									}
									@Pc(3490) Component local3490 = InterfaceList.getComponent(ii);
									if (local3490 != null) {
										Static43.method1143(local3490);
									}
									local3490 = InterfaceList.getComponent(world);
									if (local3490 != null) {
										Static43.method1143(local3490);
										Static17.method531(local3490, true);
									}
									if (InterfaceList.topLevelInterace != -1) {
										Static54.method1304(1, InterfaceList.topLevelInterace);
									}
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 27) {
								ii = Protocol.inboundBuffer.g2();
								xp = Protocol.inboundBuffer.g1();
								world = Protocol.inboundBuffer.g1();
								slot = Protocol.inboundBuffer.g1();
								count = Protocol.inboundBuffer.g1();
								i = Protocol.inboundBuffer.g2();
								if (Static248.method3288(ii)) {
									Camera.cameraModifierEnabled[xp] = true;
									Camera.cameraModifierJitter[xp] = world;
									Camera.cameraAmplitude[xp] = slot;
									Camera.cameraFrequency[xp] = count;
									Static31.cameraModifierCycle[xp] = i;
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 2) {
								ii = Protocol.inboundBuffer.g4rme();
								xp = Protocol.inboundBuffer.g2sub();
								world = Protocol.inboundBuffer.g2leadd();
								if (Static248.method3288(xp)) {
									Static136.method2649(world, ii);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 85) {
								Static60.systemUpdateTimer = Protocol.inboundBuffer.g2() * 30;
								Protocol.opcode = -1;
								Static209.miscTransmitAt = InterfaceList.transmitTimer;
								return true;
							} else if (Protocol.opcode == 114) {
								Class6.method3654(GameShell.signLink, Protocol.inboundBuffer, Static223.packetSize);
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 65) {
								ii = Protocol.inboundBuffer.g2le();
								xp = Protocol.inboundBuffer.p1neg();
								world = Protocol.inboundBuffer.g2leadd();
								if (Static248.method3288(ii)) {
									Static132.method2606(world, xp);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 234) {
								// UPDATE_RUNENERGY
								Static103.method2245();
								ClientScriptRunner.energy = Protocol.inboundBuffer.g1();
								Static209.miscTransmitAt = InterfaceList.transmitTimer;
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 209) {
								if (InterfaceList.topLevelInterace != -1) {
									Static54.method1304(0, InterfaceList.topLevelInterace);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 191) {
								ii = Protocol.inboundBuffer.g2le();
								Static13.method472(ii);
								Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = ii & 0x7FFF;
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 102) {
								ii = Protocol.inboundBuffer.g2le();
								xp = Protocol.inboundBuffer.g1_alt3();
								world = Protocol.inboundBuffer.g2();
								@Pc(3766) Npc local3766 = NpcList.npcs[ii];
								if (local3766 != null) {
									Static223.method3855(xp, world, local3766);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 159) {
								// UPDATE_RUNWEIGHT
								Static103.method2245();
								Static251.weightCarried = Protocol.inboundBuffer.g2s();
								Static209.miscTransmitAt = InterfaceList.transmitTimer;
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 71) {
								username2 = Protocol.inboundBuffer.g8();
								local790 = Static218.method2862(Static65.method1497(Protocol.inboundBuffer).method3116());
								Chat.addMessage(Base37.decode37(username2).method3125(), 6, local790);
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 42) {
								if (GameShell.fullScreenFrame != null) {
									DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
								}
								@Pc(3848) byte[] local3848 = new byte[Static223.packetSize];
								Protocol.inboundBuffer.method2237(local3848, Static223.packetSize);
								message2 = JString.decodeString(local3848, Static223.packetSize, 0);
								if (GameShell.frame == null && (SignLink.anInt5928 == 3 || !SignLink.osName.startsWith("win") || client.haveIe6)) {
									Static169.openUrl(message2, true);
								} else {
									ClientScriptRunner.url = message2;
									Protocol.newTab = true;
									Protocol.openUrlRequest = GameShell.signLink.openUrl(new String(message2.method3148(), "ISO-8859-1"));
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 111) {
								ii = Protocol.inboundBuffer.g2sub();
								xp = Protocol.inboundBuffer.p4rme();
								world = Protocol.inboundBuffer.g2leadd();
								slot = Protocol.inboundBuffer.g2le();
								count = Protocol.inboundBuffer.g2leadd();
								if (Static248.method3288(ii)) {
									Static132.method2607(world, 7, xp, slot << 16 | count);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 37) {
								ii = Protocol.inboundBuffer.g1add();
								xp = Protocol.inboundBuffer.g2le();
								Static272.method3995(ii, xp);
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 155) {
								ii = Protocol.inboundBuffer.g1();
								xp = Protocol.inboundBuffer.p4rme();
								world = Protocol.inboundBuffer.g2sub();
								slot = Protocol.inboundBuffer.g2();
								if (Static248.method3288(world)) {
									local3456 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) xp);
									if (local3456 != null) {
										InterfaceList.closeInterface(local3456.anInt5878 != slot, local3456);
									}
									Static44.method1148(slot, xp, ii);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 131) {
								// RESET_ANIMS
								for (ii = 0; ii < PlayerList.players.length; ii++) {
									if (PlayerList.players[ii] != null) {
										PlayerList.players[ii].primarySeqId = -1;
									}
								}
								for (ii = 0; ii < NpcList.npcs.length; ii++) {
									if (NpcList.npcs[ii] != null) {
										NpcList.npcs[ii].primarySeqId = -1;
									}
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 217) {
								ii = Protocol.inboundBuffer.g1();
								@Pc(4084) Class102 local4084 = new Class102();
								xp = ii >> 6;
								local4084.headIconDrawType = ii & 0x3F;
								local4084.anInt4048 = Protocol.inboundBuffer.g1();
								if (local4084.anInt4048 >= 0 && local4084.anInt4048 < Static276.aClass3_Sub2_Sub1Array11.length) {
									if (local4084.headIconDrawType == 1 || local4084.headIconDrawType == 10) {
										local4084.hintIconNpcTarget = Protocol.inboundBuffer.g2();
										Protocol.inboundBuffer.offset += 3;
									} else if (local4084.headIconDrawType >= 2 && local4084.headIconDrawType <= 6) {
										if (local4084.headIconDrawType == 2) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 64;
										}
										if (local4084.headIconDrawType == 3) {
											local4084.anInt4045 = 0;
											local4084.anInt4047 = 64;
										}
										if (local4084.headIconDrawType == 4) {
											local4084.anInt4045 = 128;
											local4084.anInt4047 = 64;
										}
										if (local4084.headIconDrawType == 5) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 0;
										}
										if (local4084.headIconDrawType == 6) {
											local4084.anInt4045 = 64;
											local4084.anInt4047 = 128;
										}
										local4084.headIconDrawType = 2;
										local4084.anInt4053 = Protocol.inboundBuffer.g2();
										local4084.anInt4046 = Protocol.inboundBuffer.g2();
										local4084.anInt4050 = Protocol.inboundBuffer.g1();
									}
									local4084.anInt4052 = Protocol.inboundBuffer.g2();
									if (local4084.anInt4052 == 65535) {
										local4084.anInt4052 = -1;
									}
									Minimap.hintMapMarkers[xp] = local4084;
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 126) {
								// UPDATE_IGNORELIST
								Static35.ignoreCount = Static223.packetSize / 8;
								for (ii = 0; ii < Static35.ignoreCount; ii++) {
									Static190.ignoreName37[ii] = Protocol.inboundBuffer.g8();
									Static193.ignoreName[ii] = Base37.decode37(Static190.ignoreName37[ii]);
								}
								Static185.anInt4369 = InterfaceList.transmitTimer;
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 32) {
								Static86.method1800();
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 119) {
								ii = Protocol.inboundBuffer.g2sub();
								xp = Protocol.inboundBuffer.g4me();
								world = Protocol.inboundBuffer.g2s();
								slot = Protocol.inboundBuffer.g2sadd();
								if (Static248.method3288(ii)) {
									Static280.method4666(world, xp, slot);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 235) {
								ii = Protocol.inboundBuffer.g1_alt3();
								xp = ii >> 2;
								world = ii & 0x3;
								slot = Loc.LAYERS[xp];
								count = Protocol.inboundBuffer.g2();
								i = Protocol.inboundBuffer.g4();
								if (count == 65535) {
									count = -1;
								}
								local908 = i & 0x3FFF;
								j = i >> 14 & 0x3FFF;
								j -= Camera.originX;
								local908 -= Camera.originZ;
								local1160 = i >> 28 & 0x3;
								Static92.method1881(local1160, world, xp, local908, slot, j, count);
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 0) {
								username2 = Protocol.inboundBuffer.g8();
								username = Protocol.inboundBuffer.g2();
								local899 = Protocol.inboundBuffer.g3();
								local1160 = Protocol.inboundBuffer.g1();
								@Pc(4425) boolean local4425 = false;
								@Pc(4431) long local4431 = local899 + (username << 32);
								local3002 = 0;
								label1450: while (true) {
									if (local3002 >= 100) {
										if (local1160 <= 1) {
											if (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129) {
												local4425 = true;
											} else {
												for (local3002 = 0; local3002 < Static35.ignoreCount; local3002++) {
													if (username2 == Static190.ignoreName37[local3002]) {
														local4425 = true;
														break label1450;
													}
												}
											}
										}
										break;
									}
									if (local4431 == Chat.recentMessages[local3002]) {
										local4425 = true;
										break;
									}
									local3002++;
								}
								if (!local4425 && Player.overrideChat == 0) {
									Chat.recentMessages[Chat.messageCounter] = local4431;
									Chat.messageCounter = (Chat.messageCounter + 1) % 100;
									@Pc(4518) JString local4518 = Static218.method2862(Static65.method1497(Protocol.inboundBuffer).method3116());
									if (local1160 == 2 || local1160 == 3) {
										Chat.addMessage(JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).method3125() }), 7, local4518);
									} else if (local1160 == 1) {
										Chat.addMessage(JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).method3125() }), 7, local4518);
									} else {
										Chat.addMessage(Base37.decode37(username2).method3125(), 3, local4518);
									}
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 54) {
								username2 = Protocol.inboundBuffer.g8();
								Protocol.inboundBuffer.g1s();
								username = Protocol.inboundBuffer.g8();
								local899 = Protocol.inboundBuffer.g2();
								local904 = Protocol.inboundBuffer.g3();
								@Pc(4626) long local4626 = (local899 << 32) + local904;
								local908 = Protocol.inboundBuffer.g1();
								@Pc(4632) boolean local4632 = false;
								@Pc(4634) int local4634 = 0;
								label1575: while (true) {
									if (local4634 >= 100) {
										if (local908 <= 1) {
											if (Static124.aBoolean157 && !Static207.parentalChatConsent || Static86.aBoolean129) {
												local4632 = true;
											} else {
												for (local4634 = 0; local4634 < Static35.ignoreCount; local4634++) {
													if (Static190.ignoreName37[local4634] == username2) {
														local4632 = true;
														break label1575;
													}
												}
											}
										}
										break;
									}
									if (Chat.recentMessages[local4634] == local4626) {
										local4632 = true;
										break;
									}
									local4634++;
								}
								if (!local4632 && Player.overrideChat == 0) {
									Chat.recentMessages[Chat.messageCounter] = local4626;
									Chat.messageCounter = (Chat.messageCounter + 1) % 100;
									local3038 = Static218.method2862(Static65.method1497(Protocol.inboundBuffer).method3116());
									if (local908 == 2 || local908 == 3) {
										Chat.method1598(local3038, JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).method3125() }), Base37.decode37(username).method3125());
									} else if (local908 == 1) {
										Chat.method1598(local3038, JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).method3125() }), Base37.decode37(username).method3125());
									} else {
										Chat.method1598(local3038, Base37.decode37(username2).method3125(), Base37.decode37(username).method3125());
									}
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 214) {
								Static75.method1629(true);
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 172) {
								ii = Protocol.inboundBuffer.g2();
								xp = Protocol.inboundBuffer.g1();
								if (ii == 65535) {
									ii = -1;
								}
								world = Protocol.inboundBuffer.g2();
								Static26.method744(xp, ii, world);
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 66) {
								ii = Protocol.inboundBuffer.g2leadd();
								xp = Protocol.inboundBuffer.g4rme();
								if (Static248.method3288(ii)) {
									world = 0;
									if (PlayerList.self.model != null) {
										world = PlayerList.self.model.getHeadModelId();
									}
									Static132.method2607(-1, 3, xp, world);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 171) {
								ii = Protocol.inboundBuffer.p4rme();
								message2 = Protocol.inboundBuffer.gjstr();
								world = Protocol.inboundBuffer.g2sub();
								if (Static248.method3288(world)) {
									Static80.method3617(message2, ii);
								}
								Protocol.opcode = -1;
								return true;
							} else if (Protocol.opcode == 84) {
								ii = Protocol.inboundBuffer.g4me();
								xp = Protocol.inboundBuffer.g2leadd();
								Static272.method3995(ii, xp);
								Protocol.opcode = -1;
								return true;
							} else {
								@Pc(4956) Component local4956;
								if (Protocol.opcode == 22) {
									ii = Protocol.inboundBuffer.g4();
									xp = Protocol.inboundBuffer.g2();
									if (ii < -70000) {
										xp += 32768;
									}
									if (ii < 0) {
										local4956 = null;
									} else {
										local4956 = InterfaceList.getComponent(ii);
									}
									while (Protocol.inboundBuffer.offset < Static223.packetSize) {
										slot = Protocol.inboundBuffer.gSmart1or2();
										count = Protocol.inboundBuffer.g2();
										i = 0;
										if (count != 0) {
											i = Protocol.inboundBuffer.g1();
											if (i == 255) {
												i = Protocol.inboundBuffer.g4();
											}
										}
										if (local4956 != null && slot >= 0 && local4956.invSlotObjId.length > slot) {
											local4956.invSlotObjId[slot] = count;
											local4956.invSlotObjCount[slot] = i;
										}
										Inv.update(count - 1, slot, i, xp);
									}
									if (local4956 != null) {
										Static43.method1143(local4956);
									}
									Static103.method2245();
									Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = xp & 0x7FFF;
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 24) {
									ii = Protocol.inboundBuffer.g2();
									if (Static248.method3288(ii)) {
										Camera.resetCameraEffects();
									}
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 86) {
									Game.processLogout();
									Protocol.opcode = -1;
									return false;
								} else if (Protocol.opcode == 116) {
									ii = Protocol.inboundBuffer.g1();
									if (Protocol.inboundBuffer.g1() == 0) {
										StockMarketManager.offers[ii] = new StockMarketOffer();
									} else {
										Protocol.inboundBuffer.offset--;
										StockMarketManager.offers[ii] = new StockMarketOffer(Protocol.inboundBuffer);
									}
									Protocol.opcode = -1;
									Static207.anInt4778 = InterfaceList.transmitTimer;
									return true;
								} else if (Protocol.opcode == 73) {
									ii = Protocol.inboundBuffer.g2sub();
									xp = Protocol.inboundBuffer.g4me();
									if (ii == 65535) {
										ii = -1;
									}
									world = Protocol.inboundBuffer.g2le();
									if (Static248.method3288(world)) {
										Static132.method2607(-1, 2, xp, ii);
									}
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 162) {
									Static75.method1629(false);
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 165) {
									ii = Protocol.inboundBuffer.g2le();
									xp = Protocol.inboundBuffer.g2le();
									if (xp == 65535) {
										xp = -1;
									}
									world = Protocol.inboundBuffer.g4();
									slot = Protocol.inboundBuffer.g2sub();
									count = Protocol.inboundBuffer.g4rme();
									if (slot == 65535) {
										slot = -1;
									}
									if (Static248.method3288(ii)) {
										for (i = slot; i <= xp; i++) {
											local904 = ((long) world << 32) + ((long) i);
											local1804 = (ServerActiveProperties) Static210.aClass133_21.getNode(local904);
											if (local1804 != null) {
												local1814 = new ServerActiveProperties(count, local1804.anInt540);
												local1804.unlink();
											} else if (i == -1) {
												local1814 = new ServerActiveProperties(count, InterfaceList.getComponent(world).aClass3_Sub4_1.anInt540);
											} else {
												local1814 = new ServerActiveProperties(count, -1);
											}
											Static210.aClass133_21.put(local1814, local904);
										}
									}
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 197) {
									FriendList.state = Protocol.inboundBuffer.g1();
									Static185.anInt4369 = InterfaceList.transmitTimer;
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 196) {
									username2 = Protocol.inboundBuffer.g8();
									world = Protocol.inboundBuffer.g2();
									@Pc(5325) byte local5325 = Protocol.inboundBuffer.g1s();
									ignored = false;
									if ((Long.MIN_VALUE & username2) != 0L) {
										ignored = true;
									}
									if (ignored) {
										if (ClanChat.size == 0) {
											Protocol.opcode = -1;
											return true;
										}
										username2 &= Long.MAX_VALUE;
										for (i = 0; ClanChat.size > i && (username2 != ClanChat.members[i].nodeId || world != ClanChat.members[i].world); i++) {
										}
										if (i < ClanChat.size) {
											while (ClanChat.size - 1 > i) {
												ClanChat.members[i] = ClanChat.members[i + 1];
												i++;
											}
											ClanChat.size--;
											ClanChat.members[ClanChat.size] = null;
										}
									} else {
										local506 = Protocol.inboundBuffer.gjstr();
										@Pc(5347) ClanMember local5347 = new ClanMember();
										local5347.nodeId = username2;
										local5347.username = Base37.decode37(local5347.nodeId);
										local5347.rank = local5325;
										local5347.worldName = local506;
										local5347.world = world;
										for (j = ClanChat.size - 1; j >= 0; j--) {
											local908 = ClanChat.members[j].username.method3139(local5347.username);
											if (local908 == 0) {
												ClanChat.members[j].world = world;
												ClanChat.members[j].rank = local5325;
												ClanChat.members[j].worldName = local506;
												if (username2 == Static101.aLong98) {
													ClanChat.rank = local5325;
												}
												ClanChat.transmitAt = InterfaceList.transmitTimer;
												Protocol.opcode = -1;
												return true;
											}
											if (local908 < 0) {
												break;
											}
										}
										if (ClanChat.members.length <= ClanChat.size) {
											Protocol.opcode = -1;
											return true;
										}
										for (local908 = ClanChat.size - 1; local908 > j; local908--) {
											ClanChat.members[local908 + 1] = ClanChat.members[local908];
										}
										if (ClanChat.size == 0) {
											ClanChat.members = new ClanMember[100];
										}
										ClanChat.members[j + 1] = local5347;
										if (Static101.aLong98 == username2) {
											ClanChat.rank = local5325;
										}
										ClanChat.size++;
									}
									Protocol.opcode = -1;
									ClanChat.transmitAt = InterfaceList.transmitTimer;
									return true;
								} else if (Protocol.opcode == 50) {
									ii = Protocol.inboundBuffer.g4();
									xp = Protocol.inboundBuffer.p4rme();
									world = Protocol.inboundBuffer.g2leadd();
									if (world == 65535) {
										world = -1;
									}
									slot = Protocol.inboundBuffer.g2le();
									if (Static248.method3288(slot)) {
										@Pc(5603) Component com = InterfaceList.getComponent(xp);
										@Pc(5615) com.jagex.runetek4.cache.def.ItemDefinition obj;
										if (com.aBoolean32) {
											Static209.method3707(xp, ii, world);
											obj = Static71.get(world);
											Static261.method4505(obj.zoom2d, xp, obj.yan2d, obj.xan2d);
											Static145.method2745(xp, obj.zan2d, obj.yof2d, obj.xof2d);
										} else if (world == -1) {
											com.modelType = 0;
											Protocol.opcode = -1;
											return true;
										} else {
											obj = Static71.get(world);
											com.modelXAngle = obj.xan2d;
											com.modelZoom = obj.zoom2d * 100 / ii;
											com.modelType = 4;
											com.modelId = world;
											com.modelYAngle = obj.yan2d;
											Static43.method1143(com);
										}
									}
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 105) {
									ii = Protocol.inboundBuffer.g4();
									xp = Protocol.inboundBuffer.g2();
									if (ii < -70000) {
										xp += 32768;
									}
									if (ii >= 0) {
										local4956 = InterfaceList.getComponent(ii);
									} else {
										local4956 = null;
									}
									if (local4956 != null) {
										for (slot = 0; slot < local4956.invSlotObjId.length; slot++) {
											local4956.invSlotObjId[slot] = 0;
											local4956.invSlotObjCount[slot] = 0;
										}
									}
									Static14.method475(xp);
									slot = Protocol.inboundBuffer.g2();
									for (count = 0; count < slot; count++) {
										i = Protocol.inboundBuffer.g1_alt3();
										if (i == 255) {
											i = Protocol.inboundBuffer.g4();
										}
										local1160 = Protocol.inboundBuffer.g2();
										if (local4956 != null && count < local4956.invSlotObjId.length) {
											local4956.invSlotObjId[count] = local1160;
											local4956.invSlotObjCount[count] = i;
										}
										Inv.update(local1160 - 1, count, i, xp);
									}
									if (local4956 != null) {
										Static43.method1143(local4956);
									}
									Static103.method2245();
									Static27.anIntArray70[Static111.anInt2901++ & 0x1F] = xp & 0x7FFF;
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 142) {
									Static230.method3954(Protocol.inboundBuffer.gjstr());
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 26) {
									Static115.anInt2940 = Protocol.inboundBuffer.p1neg();
									Static180.anInt4264 = Protocol.inboundBuffer.g1();
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 4) {
									ii = Protocol.inboundBuffer.g2leadd();
									if (ii == 65535) {
										ii = -1;
									}
									Static148.method2765(ii);
									Protocol.opcode = -1;
									return true;
								} else if (Protocol.opcode == 208) {
									ii = Protocol.inboundBuffer.g3le();
									xp = Protocol.inboundBuffer.g2le();
									if (xp == 65535) {
										xp = -1;
									}
									Static278.method4650(ii, xp);
									Protocol.opcode = -1;
									return true;
								} else {
									TracingException.report("T1 - " + Protocol.opcode + "," + Protocol.opcode3 + "," + Protocol.opcode4 + " - " + Static223.packetSize, null);
									Game.processLogout();
									return true;
								}
							}
						}
					}
				}
			}
		}
	}

}
