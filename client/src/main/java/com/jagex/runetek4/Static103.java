package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.frame.Minimap;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static103 {

	@OriginalMember(owner = "runetek4.client!i", name = "Ub", descriptor = "[Lclient!na;")
	public static JString[] aClass100Array88;

	@OriginalMember(owner = "runetek4.client!i", name = "ec", descriptor = "[I")
	public static int[] anIntArray254;

    @OriginalMember(owner = "runetek4.client!i", name = "ic", descriptor = "Lclient!na;")
	public static final JString aClass100_558 = JString.parse("m");

	@OriginalMember(owner = "runetek4.client!i", name = "p", descriptor = "(II)V")
	public static void method2232(@OriginalArg(1) int arg0) {
		if (arg0 < 0) {
			return;
		}
		@Pc(15) int local15 = Static196.anIntArray408[arg0];
		@Pc(19) int local19 = Static56.anIntArray142[arg0];
		@Pc(23) int local23 = Static39.aShortArray6[arg0];
		if (local23 >= 2000) {
			local23 -= 2000;
		}
		@Pc(31) long local31 = Static159.aLongArray5[arg0];
		@Pc(36) int a = (int) Static159.aLongArray5[arg0];
		@Pc(43) Player local43;
		if (local23 == 31) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static122.x = aClass6.mouseClickX;
				Static25.y = Static60.mouseClickY;
				Protocol.outboundBuffer.pIsaac1(71);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 46) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Protocol.outboundBuffer.pIsaac1(247);
			Protocol.outboundBuffer.p2_alt1(Camera.originZ + local19);
			Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
			Protocol.outboundBuffer.p2(Integer.MAX_VALUE & (int) (local31 >>> 32));
		}
		if (local23 == 40) {
			Protocol.outboundBuffer.pIsaac1(27);
			Protocol.outboundBuffer.p2(Static185.anInt4370);
			Protocol.outboundBuffer.p4_alt1(local19);
			Protocol.outboundBuffer.p2_alt1(local15);
			Protocol.outboundBuffer.p4_alt1(FluTypeList.anInt5062);
			Protocol.outboundBuffer.p2_alt3(Static274.anInt4997);
			Protocol.outboundBuffer.p2_alt3(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		@Pc(192) Npc npc;
		if (local23 == 19) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static122.x = aClass6.mouseClickX;
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Protocol.outboundBuffer.pIsaac1(30);
				Protocol.outboundBuffer.p2(a);
			}
		}
		if (local23 == 17) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Protocol.outboundBuffer.pIsaac1(78);
				Protocol.outboundBuffer.p2_alt1(a);
			}
		}
		if (local23 == 44) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static122.x = aClass6.mouseClickX;
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static17.crossCycle = 0;
				Protocol.outboundBuffer.pIsaac1(133);
				Protocol.outboundBuffer.p2_alt1(a);
			}
		}
		if (local23 == 58) {
			Protocol.outboundBuffer.pIsaac1(135);
			Protocol.outboundBuffer.p2_alt2(a);
			Protocol.outboundBuffer.p2_alt2(local15);
			Protocol.outboundBuffer.p4_alt3(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 42) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Protocol.outboundBuffer.pIsaac1(254);
			Protocol.outboundBuffer.p2_alt1(local15 + Camera.originX);
			Protocol.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
			Protocol.outboundBuffer.p2(local19 + Camera.originZ);
		}
		if (local23 == 28) {
			ClientProt.closeWidget();
		}
		if (local23 == 45) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static122.x = aClass6.mouseClickX;
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Protocol.outboundBuffer.pIsaac1(239);
				Protocol.outboundBuffer.p4_alt1(Static98.anInt2512);
				Protocol.outboundBuffer.p2_alt2(Static15.anInt506);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		@Pc(560) boolean local560;
		if (local23 == 18) {
			if (client.game == 1) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			} else {
				local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				if (!local560) {
					PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				}
			}
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Protocol.outboundBuffer.pIsaac1(66);
			Protocol.outboundBuffer.p2_alt1(Camera.originX + local15);
			Protocol.outboundBuffer.p2(a);
			Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
		}
		if (local23 == 1001) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Protocol.outboundBuffer.pIsaac1(170);
			Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (local31 >>> 32));
			Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
			Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
		}
		if (local23 == 1002) {
			Static70.crossMode = 2;
			Static122.x = aClass6.mouseClickX;
			Static25.y = Static60.mouseClickY;
			Static17.crossCycle = 0;
			Protocol.outboundBuffer.pIsaac1(92);
			Protocol.outboundBuffer.p2_alt3(a);
		}
		@Pc(693) Component com;
		if (local23 == 1006) {
			com = InterfaceList.getComponent(local19);
			if (com == null || com.invSlotObjCount[local15] < 100000) {
				Protocol.outboundBuffer.pIsaac1(92);
				Protocol.outboundBuffer.p2_alt3(a);
			} else {
				Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { JString.parseInt(com.invSlotObjCount[local15]), Static249.aClass100_1039, Static71.get(a).name}));
			}
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 60) {
			if (a == 0) {
				Static113.method3556(Player.plane, local15, local19);
			} else if (a == 1) {
				if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
					Cheat.teleport(Camera.originX + local15, Camera.originZ + local19, Player.plane);
				} else if (PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, local15, 0, 0, 1, local19, PlayerList.self.movementQueueX[0])) {
					Protocol.outboundBuffer.p1(Static1.anInt5);
					Protocol.outboundBuffer.p1(Static107.anInt2878);
					Protocol.outboundBuffer.p2(Camera.orbitCameraYaw);
					Protocol.outboundBuffer.p1(57);
					Protocol.outboundBuffer.p1(Minimap.minimapAnticheatAngle);
					Protocol.outboundBuffer.p1(Minimap.minimapZoom);
					Protocol.outboundBuffer.p1(89);
					Protocol.outboundBuffer.p2(PlayerList.self.xFine);
					Protocol.outboundBuffer.p2(PlayerList.self.zFine);
					Protocol.outboundBuffer.p1(BZip2State.tryMoveNearest);
					Protocol.outboundBuffer.p1(63);
				}
			}
		}
		if (local23 == 1007) {
			Static17.crossCycle = 0;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static122.x = aClass6.mouseClickX;
			npc = NpcList.npcs[a];
			if (npc != null) {
				@Pc(884) NpcType local884 = npc.type;
				if (local884.multiNpcs != null) {
					local884 = local884.getMultiNPC();
				}
				if (local884 != null) {
					Protocol.outboundBuffer.pIsaac1(72);
					Protocol.outboundBuffer.p2(local884.id);
				}
			}
		}
		if (local23 == 47) {
			Protocol.outboundBuffer.pIsaac1(156);
			Protocol.outboundBuffer.p2_alt3(local15);
			Protocol.outboundBuffer.p2_alt2(a);
			Protocol.outboundBuffer.p4_alt1(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 3) {
			Protocol.outboundBuffer.pIsaac1(253);
			Protocol.outboundBuffer.p4_alt1(Static98.anInt2512);
			Protocol.outboundBuffer.p2_alt3(local15);
			Protocol.outboundBuffer.p4_alt1(local19);
			Protocol.outboundBuffer.p2_alt2(a);
			Protocol.outboundBuffer.p2_alt1(Static15.anInt506);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 10) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Protocol.outboundBuffer.pIsaac1(4);
				Protocol.outboundBuffer.p2_alt1(a);
			}
		}
		if (local23 == 41 && ClientScriptRunner.aClass13_10 == null) {
			Static2.method10(local15, local19);
			ClientScriptRunner.aClass13_10 = Static201.method1418(local19, local15);
			InterfaceList.redraw(ClientScriptRunner.aClass13_10);
		}
		if (local23 == 49) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Protocol.outboundBuffer.pIsaac1(84);
			Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (local31 >>> 32));
			Protocol.outboundBuffer.p2_alt3(Camera.originZ + local19);
			Protocol.outboundBuffer.p2_alt1(local15 + Camera.originX);
		}
		if (local23 == 23) {
			Protocol.outboundBuffer.pIsaac1(206);
			Protocol.outboundBuffer.p2_alt2(a);
			Protocol.outboundBuffer.p2_alt1(local15);
			Protocol.outboundBuffer.p4_alt1(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 14 && ClientScriptRunner.method4003(local31, local19, local15)) {
			Protocol.outboundBuffer.pIsaac1(134);
			Protocol.outboundBuffer.p2_alt2(Camera.originX + local15);
			Protocol.outboundBuffer.p2(Static274.anInt4997);
			Protocol.outboundBuffer.p2_alt1(local19 + Camera.originZ);
			Protocol.outboundBuffer.p2(Static185.anInt4370);
			Protocol.outboundBuffer.p4_alt3(FluTypeList.anInt5062);
			Protocol.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 37) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Protocol.outboundBuffer.pIsaac1(114);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 9 || local23 == 1003) {
			ClientProt.method4512(ClientScriptRunner.aClass100Array160[arg0], local15, a, local19);
		}
		if (local23 == 5) {
			Protocol.outboundBuffer.pIsaac1(55);
			Protocol.outboundBuffer.p2_alt1(a);
			Protocol.outboundBuffer.p2_alt2(local15);
			Protocol.outboundBuffer.p4rme(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 21) {
			if (client.game == 1) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			} else {
				local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				if (!local560) {
					PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				}
			}
			Static70.crossMode = 2;
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static25.y = Static60.mouseClickY;
			Protocol.outboundBuffer.pIsaac1(228);
			Protocol.outboundBuffer.p2(a);
			Protocol.outboundBuffer.p2_alt1(Camera.originX + local15);
			Protocol.outboundBuffer.p2_alt3(Camera.originZ + local19);
		}
		if (local23 == 4) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Protocol.outboundBuffer.pIsaac1(148);
				Protocol.outboundBuffer.p2_alt2(a);
			}
		}
		if (local23 == 32) {
			com = Static201.method1418(local19, local15);
			if (com != null) {
				Static53.method1294();
				@Pc(1493) ServerActiveProperties local1493 = InterfaceList.getServerActiveProperties(com);
				Static247.method4246(local19, local15, local1493.method512(), local1493.anInt540, com.anInt499, com.anInt484);
				MiniMenu.anInt5014 = 0;
				Static102.aClass100_545 = Static97.method1963(com);
				if (Static102.aClass100_545 == null) {
					Static102.aClass100_545 = Static250.aClass100_1042;
				}
				if (com.if3) {
					Static78.aClass100_466 = JString.concatenate(new JString[] { com.optionBase, Static204.aClass100_896 });
				} else {
					Static78.aClass100_466 = JString.concatenate(new JString[] { Static42.GREEN, com.optionSuffix, Static204.aClass100_896 });
				}
			}
			return;
		}
		if (local23 == 29) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static25.y = Static60.mouseClickY;
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Protocol.outboundBuffer.pIsaac1(180);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 35) {
			Protocol.outboundBuffer.pIsaac1(161);
			Protocol.outboundBuffer.p4_alt1(local19);
			Protocol.outboundBuffer.p2_alt3(a);
			Protocol.outboundBuffer.p2_alt3(local15);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 15) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static25.y = Static60.mouseClickY;
				Protocol.outboundBuffer.pIsaac1(195);
				Protocol.outboundBuffer.p2_alt2(Static15.anInt506);
				Protocol.outboundBuffer.p4_alt1(Static98.anInt2512);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 34) {
			if (client.game == 1) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			} else {
				local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				if (!local560) {
					PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				}
			}
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static17.crossCycle = 0;
			Protocol.outboundBuffer.pIsaac1(109);
			Protocol.outboundBuffer.p2_alt1(local19 + Camera.originZ);
			Protocol.outboundBuffer.p2(local15 + Camera.originX);
			Protocol.outboundBuffer.p2_alt3(a);
		}
		if (local23 == 25) {
			Protocol.outboundBuffer.pIsaac1(81);
			Protocol.outboundBuffer.p2_alt2(local15);
			Protocol.outboundBuffer.p2(a);
			Protocol.outboundBuffer.p4rme(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 2) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Protocol.outboundBuffer.pIsaac1(218);
				Protocol.outboundBuffer.p2_alt1(a);
			}
		}
		@Pc(1955) int varp;
		if (local23 == 51) {
			Protocol.outboundBuffer.pIsaac1(10);
			Protocol.outboundBuffer.p4(local19);
			com = InterfaceList.getComponent(local19);
			if (com.scripts != null && com.scripts[0][0] == 5) {
				varp = com.scripts[0][1];
				if (VarPlayerDefinition.varPlayers[varp] != com.scriptOperand[0]) {
					VarPlayerDefinition.varPlayers[varp] = com.scriptOperand[0];
					Static85.handleVarps(varp);
				}
			}
		}
		if (local23 == 26) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Protocol.outboundBuffer.pIsaac1(115);
				Protocol.outboundBuffer.p4_alt3(FluTypeList.anInt5062);
				Protocol.outboundBuffer.p2_alt1(Static185.anInt4370);
				Protocol.outboundBuffer.p2_alt1(a);
				Protocol.outboundBuffer.p2_alt3(Static274.anInt4997);
			}
		}
		if (local23 == 59) {
			Protocol.outboundBuffer.pIsaac1(10);
			Protocol.outboundBuffer.p4(local19);
			com = InterfaceList.getComponent(local19);
			if (com.scripts != null && com.scripts[0][0] == 5) {
				varp = com.scripts[0][1];
				VarPlayerDefinition.varPlayers[varp] = 1 - VarPlayerDefinition.varPlayers[varp];
				Static85.handleVarps(varp);
			}
		}
		if (local23 == 33) {
			local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			if (!local560) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			}
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static25.y = Static60.mouseClickY;
			Static70.crossMode = 2;
			Protocol.outboundBuffer.pIsaac1(101);
			Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
			Protocol.outboundBuffer.p2_alt1(Static185.anInt4370);
			Protocol.outboundBuffer.p2_alt1(Static274.anInt4997);
			Protocol.outboundBuffer.p2_alt1(a);
			Protocol.outboundBuffer.p2_alt3(Camera.originZ + local19);
			Protocol.outboundBuffer.p4_alt3(FluTypeList.anInt5062);
		}
		if (local23 == 1004) {
			Static17.crossCycle = 0;
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Protocol.outboundBuffer.pIsaac1(94);
			Protocol.outboundBuffer.p2_alt3(a);
		}
		if (local23 == 11) {
			if (a == 0) {
				Static125.anInt3096 = 1;
				Static113.method3556(Player.plane, local15, local19);
			} else if (a == 1) {
				Protocol.outboundBuffer.pIsaac1(131);
				Protocol.outboundBuffer.p4_alt3(Static98.anInt2512);
				Protocol.outboundBuffer.p2_alt2(Camera.originX + local15);
				Protocol.outboundBuffer.p2_alt3(Static15.anInt506);
				Protocol.outboundBuffer.p2_alt2(local19 + Camera.originZ);
			}
		}
		if (local23 == 8) {
			com = InterfaceList.getComponent(local19);
			@Pc(2287) boolean local2287 = true;
			if (com.contentType > 0) {
				local2287 = MiniMenu.method4265(com);
			}
			if (local2287) {
				Protocol.outboundBuffer.pIsaac1(10);
				Protocol.outboundBuffer.p4(local19);
			}
		}
		if (local23 == 1) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Protocol.outboundBuffer.pIsaac1(248);
				Protocol.outboundBuffer.p2_alt3(a);
				Protocol.outboundBuffer.p2(Static274.anInt4997);
				Protocol.outboundBuffer.p2(Static185.anInt4370);
				Protocol.outboundBuffer.p4_alt3(FluTypeList.anInt5062);
			}
		}
		if (local23 == 7) {
			Protocol.outboundBuffer.pIsaac1(85);
			Protocol.outboundBuffer.p4rme(local19);
			Protocol.outboundBuffer.p2(local15);
			Protocol.outboundBuffer.p2_alt2(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 24) {
			if (client.game == 1) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			} else {
				local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				if (!local560) {
					PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				}
			}
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Protocol.outboundBuffer.pIsaac1(48);
			Protocol.outboundBuffer.p2_alt2(local15 + Camera.originX);
			Protocol.outboundBuffer.p2_alt3(a);
			Protocol.outboundBuffer.p2_alt1(Camera.originZ + local19);
		}
		if (local23 == 38 && ClientScriptRunner.method4003(local31, local19, local15)) {
			Protocol.outboundBuffer.pIsaac1(233);
			Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
			Protocol.outboundBuffer.p2_alt2(Camera.originX + local15);
			Protocol.outboundBuffer.p2_alt3(Static15.anInt506);
			Protocol.outboundBuffer.p4rme(Static98.anInt2512);
			Protocol.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 13) {
			Protocol.outboundBuffer.pIsaac1(6);
			Protocol.outboundBuffer.p4(local19);
			Protocol.outboundBuffer.p2_alt2(local15);
			Protocol.outboundBuffer.p2_alt1(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 57) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Protocol.outboundBuffer.pIsaac1(175);
				Protocol.outboundBuffer.p2_alt2(a);
			}
		}
		if (local23 == 22) {
			Static53.method1294();
			com = InterfaceList.getComponent(local19);
			FluTypeList.anInt5062 = local19;
			Static185.anInt4370 = local15;
			MiniMenu.anInt5014 = 1;
			Static274.anInt4997 = a;
			InterfaceList.redraw(com);
			Static34.aClass100_203 = JString.concatenate(new JString[] { Static8.aClass100_32, Static71.get(a).name, Static204.aClass100_896 });
			if (Static34.aClass100_203 == null) {
				Static34.aClass100_203 = Static92.aClass100_510;
			}
			return;
		}
		if (local23 == 50) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Protocol.outboundBuffer.pIsaac1(194);
			Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
			Protocol.outboundBuffer.p2_alt1(Camera.originX + local15);
			Protocol.outboundBuffer.p2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 48) {
			Protocol.outboundBuffer.pIsaac1(154);
			Protocol.outboundBuffer.p2_alt1(local15);
			Protocol.outboundBuffer.p4rme(local19);
			Protocol.outboundBuffer.p2_alt3(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 30) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static17.crossCycle = 0;
				Static122.x = aClass6.mouseClickX;
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Protocol.outboundBuffer.pIsaac1(68);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 43) {
			Protocol.outboundBuffer.pIsaac1(153);
			Protocol.outboundBuffer.p4_alt1(local19);
			Protocol.outboundBuffer.p2_alt1(local15);
			Protocol.outboundBuffer.p2_alt1(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = InterfaceList.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 39) {
			local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			if (!local560) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			}
			Static25.y = Static60.mouseClickY;
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static17.crossCycle = 0;
			Protocol.outboundBuffer.pIsaac1(73);
			Protocol.outboundBuffer.p4rme(Static98.anInt2512);
			Protocol.outboundBuffer.p2(Camera.originZ + local19);
			Protocol.outboundBuffer.p2_alt3(a);
			Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
			Protocol.outboundBuffer.p2_alt1(Static15.anInt506);
		}
		if (local23 == 12) {
			Protocol.outboundBuffer.pIsaac1(82);
			Protocol.outboundBuffer.p2(Static15.anInt506);
			Protocol.outboundBuffer.p4rme(local19);
			Protocol.outboundBuffer.p4(Static98.anInt2512);
			Protocol.outboundBuffer.p2_alt3(local15);
		}
		if (local23 == 36) {
			if (a == 0) {
				Static187.anInt4422 = 1;
				Static113.method3556(Player.plane, local15, local19);
			} else if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
				Cheat.teleport(local15 + Camera.originX, Camera.originZ - -local19, Player.plane);
			} else {
				Protocol.outboundBuffer.pIsaac1(179);
				Protocol.outboundBuffer.p2(local19 + Camera.originZ);
				Protocol.outboundBuffer.p2(local15 + Camera.originX);
			}
		}
		if (local23 == 6) {
			local43 = PlayerList.players[a];
			if (local43 != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local43.movementQueueX[0], 1, 0, 2, local43.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static25.y = Static60.mouseClickY;
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Protocol.outboundBuffer.pIsaac1(106);
				Protocol.outboundBuffer.p2(a);
			}
		}
		if (local23 == 20) {
			if (client.game == 1) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
			} else {
				local560 = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				if (!local560) {
					PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
				}
			}
			Static25.y = Static60.mouseClickY;
			Static17.crossCycle = 0;
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Protocol.outboundBuffer.pIsaac1(33);
			Protocol.outboundBuffer.p2(a);
			Protocol.outboundBuffer.p2(Camera.originX + local15);
			Protocol.outboundBuffer.p2_alt1(Camera.originZ + local19);
		}
		if (local23 == 16) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Protocol.outboundBuffer.pIsaac1(3);
				Protocol.outboundBuffer.p2_alt3(a);
			}
		}
		if (MiniMenu.anInt5014 != 0) {
			MiniMenu.anInt5014 = 0;
			InterfaceList.redraw(InterfaceList.getComponent(FluTypeList.anInt5062));
		}
		if (MiniMenu.aBoolean302) {
			Static53.method1294();
		}
		if (Static257.aClass13_7 != null && Static72.anInt2043 == 0) {
			InterfaceList.redraw(Static257.aClass13_7);
		}
	}

	@OriginalMember(owner = "runetek4.client!i", name = "b", descriptor = "(IIIII)I")
	public static int method2235(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (Static222.aBoolean246) {
			arg0 = 1000000;
			Static222.aBoolean246 = false;
		}
		@Pc(15) Environment local15 = Static192.aClass92ArrayArray1[arg3][arg1];
		@Pc(25) float local25 = ((float) arg2 * 0.1F + 0.7F) * local15.aFloat16;
		@Pc(28) float local28 = local15.aFloat18;
		@Pc(31) int local31 = local15.anInt3526;
		@Pc(34) int local34 = local15.anInt3529;
		@Pc(37) int local37 = local15.anInt3525;
		if (!Static71.aBoolean107) {
			local34 = 0;
		}
		@Pc(44) float local44 = local15.aFloat17;
		if (local31 != Static109.anInt2883 || Static126.aFloat13 != local25 || Static8.aFloat1 != local28 || local44 != Static15.aFloat4 || Static166.anInt4044 != local37 || Static226.anInt5080 != local34) {
			Static126.aFloat13 = local25;
			Static253.aFloat37 = Static253.aFloat36;
			Static59.aFloat6 = Static61.aFloat7;
			Static109.anInt2883 = local31;
			Static264.anInt4623 = Static154.anInt3709;
			Static171.anInt4153 = Static79.anInt2161;
			Static15.aFloat4 = local44;
			Static3.anInt5868 = 0;
			Static131.anInt3255 = Static261.anInt5731;
			Static226.anInt5080 = local34;
			Static8.aFloat1 = local28;
			Static166.anInt4044 = local37;
			Static185.aFloat23 = Static23.aFloat5;
		}
		if (Static3.anInt5868 < 65536) {
			Static3.anInt5868 += arg0 * 250;
			if (Static3.anInt5868 >= 65536) {
				Static3.anInt5868 = 65536;
			}
			@Pc(114) float local114 = (float) Static3.anInt5868 / 65536.0F;
			@Pc(118) int local118 = Static3.anInt5868 >> 8;
			@Pc(125) int local125 = 65536 - Static3.anInt5868 >> 8;
			Static154.anInt3709 = (local118 * (Static166.anInt4044 & 0xFF00FF) + (Static264.anInt4623 & 0xFF00FF) * local125 & 0xFF00FF00) + (local125 * (Static264.anInt4623 & 0xFF00) + (Static166.anInt4044 & 0xFF00) * local118 & 0xFF0000) >> 8;
			@Pc(162) float local162 = (float) (65536 - Static3.anInt5868) / 65536.0F;
			Static61.aFloat7 = local162 * Static59.aFloat6 + local114 * Static126.aFloat13;
			Static253.aFloat36 = Static253.aFloat37 * local162 + local114 * Static8.aFloat1;
			Static23.aFloat5 = local114 * Static15.aFloat4 + local162 * Static185.aFloat23;
			Static261.anInt5731 = ((Static109.anInt2883 & 0xFF00) * local118 + local125 * (Static131.anInt3255 & 0xFF00) & 0xFF0000) + ((Static131.anInt3255 & 0xFF00FF) * local125 + ((Static109.anInt2883 & 0xFF00FF) * local118) & 0xFF00FF00) >> 8;
			Static79.anInt2161 = local118 * Static226.anInt5080 + local125 * Static171.anInt4153 >> 8;
		}
		Static161.method3060(Static261.anInt5731, Static61.aFloat7, Static253.aFloat36, Static23.aFloat5);
		Static161.method3062(Static154.anInt3709, Static79.anInt2161);
		Static161.method3063((float) Static85.anInt2263, (float) Static159.anInt3893, (float) Static148.anInt3534);
		Static161.method3058();
		return Static154.anInt3709;
	}

	@OriginalMember(owner = "runetek4.client!i", name = "e", descriptor = "(BI)I")
	public static int method2236(@OriginalArg(1) int arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "runetek4.client!i", name = "r", descriptor = "(I)V")
	public static void removeSoft() {
		Static27.aClass99_4.removeSoft();
		Static244.aClass99_32.removeSoft();
		Static118.aClass99_16.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!i", name = "i", descriptor = "(Z)V")
	public static void method2245() {
		for (@Pc(6) ComponentPointer local6 = (ComponentPointer) InterfaceList.openInterfaces.head(); local6 != null; local6 = (ComponentPointer) InterfaceList.openInterfaces.prev()) {
			@Pc(14) int local14 = local6.anInt5878;
			if (Component.load(local14)) {
				@Pc(21) boolean local21 = true;
				@Pc(25) Component[] local25 = Component.cachedComponents[local14];
				@Pc(27) int local27;
				for (local27 = 0; local27 < local25.length; local27++) {
					if (local25[local27] != null) {
						local21 = local25[local27].if3;
						break;
					}
				}
				if (!local21) {
					local27 = (int) local6.nodeId;
					@Pc(60) Component local60 = InterfaceList.getComponent(local27);
					if (local60 != null) {
						InterfaceList.redraw(local60);
					}
				}
			}
		}
	}
}
