package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.dash3d.entity.Npc;
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
	public static final JString aClass100_558 = Static28.parse("m");

	@OriginalMember(owner = "runetek4.client!i", name = "a", descriptor = "(Lclient!na;ILclient!na;I)V")
	public static void addMessage(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JString arg2) {
		Static154.add(-1, arg1, arg2, null, arg0);
	}

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
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static122.x = aClass6.mouseClickX;
				Static25.y = Static60.mouseClickY;
				Static6.outboundBuffer.pIsaac1(71);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 46) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Static6.outboundBuffer.pIsaac1(247);
			Static6.outboundBuffer.p2_alt1(Static142.originZ + local19);
			Static6.outboundBuffer.p2_alt3(local15 + Static225.originX);
			Static6.outboundBuffer.p2(Integer.MAX_VALUE & (int) (local31 >>> 32));
		}
		if (local23 == 40) {
			Static6.outboundBuffer.pIsaac1(27);
			Static6.outboundBuffer.p2(Static185.anInt4370);
			Static6.outboundBuffer.p4_alt1(local19);
			Static6.outboundBuffer.p2_alt1(local15);
			Static6.outboundBuffer.p4_alt1(Static224.anInt5062);
			Static6.outboundBuffer.p2_alt3(Static274.anInt4997);
			Static6.outboundBuffer.p2_alt3(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		@Pc(192) Npc npc;
		if (local23 == 19) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static122.x = aClass6.mouseClickX;
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static6.outboundBuffer.pIsaac1(30);
				Static6.outboundBuffer.p2(a);
			}
		}
		if (local23 == 17) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static6.outboundBuffer.pIsaac1(78);
				Static6.outboundBuffer.p2_alt1(a);
			}
		}
		if (local23 == 44) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static122.x = aClass6.mouseClickX;
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static17.crossCycle = 0;
				Static6.outboundBuffer.pIsaac1(133);
				Static6.outboundBuffer.p2_alt1(a);
			}
		}
		if (local23 == 58) {
			Static6.outboundBuffer.pIsaac1(135);
			Static6.outboundBuffer.p2_alt2(a);
			Static6.outboundBuffer.p2_alt2(local15);
			Static6.outboundBuffer.p4_alt3(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 42) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Static6.outboundBuffer.pIsaac1(254);
			Static6.outboundBuffer.p2_alt1(local15 + Static225.originX);
			Static6.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
			Static6.outboundBuffer.p2(local19 + Static142.originZ);
		}
		if (local23 == 28) {
			Static153.method2909();
		}
		if (local23 == 45) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static122.x = aClass6.mouseClickX;
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static6.outboundBuffer.pIsaac1(239);
				Static6.outboundBuffer.p4_alt1(Static98.anInt2512);
				Static6.outboundBuffer.p2_alt2(Static15.anInt506);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		@Pc(560) boolean local560;
		if (local23 == 18) {
			if (Static266.game == 1) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			} else {
				local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
				if (!local560) {
					Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
				}
			}
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static6.outboundBuffer.pIsaac1(66);
			Static6.outboundBuffer.p2_alt1(Static225.originX + local15);
			Static6.outboundBuffer.p2(a);
			Static6.outboundBuffer.p2_alt3(local19 + Static142.originZ);
		}
		if (local23 == 1001) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Static6.outboundBuffer.pIsaac1(170);
			Static6.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (local31 >>> 32));
			Static6.outboundBuffer.p2_alt3(local15 + Static225.originX);
			Static6.outboundBuffer.p2_alt3(local19 + Static142.originZ);
		}
		if (local23 == 1002) {
			Static70.crossMode = 2;
			Static122.x = aClass6.mouseClickX;
			Static25.y = Static60.mouseClickY;
			Static17.crossCycle = 0;
			Static6.outboundBuffer.pIsaac1(92);
			Static6.outboundBuffer.p2_alt3(a);
		}
		@Pc(693) Component com;
		if (local23 == 1006) {
			com = Component.getComponent(local19);
			if (com == null || com.invSlotObjCount[local15] < 100000) {
				Static6.outboundBuffer.pIsaac1(92);
				Static6.outboundBuffer.p2_alt3(a);
			} else {
				addMessage(Static186.aClass100_827, 0, Static34.method882(new JString[] { Static123.method2423(com.invSlotObjCount[local15]), Static249.aClass100_1039, Static71.get(a).name}));
			}
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 60) {
			if (a == 0) {
				Static113.method3556(Player.plane, local15, local19);
			} else if (a == 1) {
				if (Static191.staffModLevel > 0 && Static187.pressedKeys[82] && Static187.pressedKeys[81]) {
					Static61.teleport(Static225.originX + local15, Static142.originZ + local19, Player.plane);
				} else if (Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, true, 0, local15, 0, 0, 1, local19, PlayerList.self.pathTileX[0])) {
					Static6.outboundBuffer.p1(Static1.anInt5);
					Static6.outboundBuffer.p1(Static107.anInt2878);
					Static6.outboundBuffer.p2(Static57.orbitCameraYaw);
					Static6.outboundBuffer.p1(57);
					Static6.outboundBuffer.p1(Static59.minimapAnticheatAngle);
					Static6.outboundBuffer.p1(Static273.minimapZoom);
					Static6.outboundBuffer.p1(89);
					Static6.outboundBuffer.p2(PlayerList.self.xFine);
					Static6.outboundBuffer.p2(PlayerList.self.zFine);
					Static6.outboundBuffer.p1(BZip2State.tryMoveNearest);
					Static6.outboundBuffer.p1(63);
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
					Static6.outboundBuffer.pIsaac1(72);
					Static6.outboundBuffer.p2(local884.id);
				}
			}
		}
		if (local23 == 47) {
			Static6.outboundBuffer.pIsaac1(156);
			Static6.outboundBuffer.p2_alt3(local15);
			Static6.outboundBuffer.p2_alt2(a);
			Static6.outboundBuffer.p4_alt1(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 3) {
			Static6.outboundBuffer.pIsaac1(253);
			Static6.outboundBuffer.p4_alt1(Static98.anInt2512);
			Static6.outboundBuffer.p2_alt3(local15);
			Static6.outboundBuffer.p4_alt1(local19);
			Static6.outboundBuffer.p2_alt2(a);
			Static6.outboundBuffer.p2_alt1(Static15.anInt506);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 10) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static6.outboundBuffer.pIsaac1(4);
				Static6.outboundBuffer.p2_alt1(a);
			}
		}
		if (local23 == 41 && Static39.aClass13_10 == null) {
			Static2.method10(local15, local19);
			Static39.aClass13_10 = Static201.method1418(local19, local15);
			Static43.method1143(Static39.aClass13_10);
		}
		if (local23 == 49) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Static6.outboundBuffer.pIsaac1(84);
			Static6.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (local31 >>> 32));
			Static6.outboundBuffer.p2_alt3(Static142.originZ + local19);
			Static6.outboundBuffer.p2_alt1(local15 + Static225.originX);
		}
		if (local23 == 23) {
			Static6.outboundBuffer.pIsaac1(206);
			Static6.outboundBuffer.p2_alt2(a);
			Static6.outboundBuffer.p2_alt1(local15);
			Static6.outboundBuffer.p4_alt1(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 14 && ClientScriptRunner.method4003(local31, local19, local15)) {
			Static6.outboundBuffer.pIsaac1(134);
			Static6.outboundBuffer.p2_alt2(Static225.originX + local15);
			Static6.outboundBuffer.p2(Static274.anInt4997);
			Static6.outboundBuffer.p2_alt1(local19 + Static142.originZ);
			Static6.outboundBuffer.p2(Static185.anInt4370);
			Static6.outboundBuffer.p4_alt3(Static224.anInt5062);
			Static6.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 37) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static6.outboundBuffer.pIsaac1(114);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 9 || local23 == 1003) {
			Static263.method4512(ClientScriptRunner.aClass100Array160[arg0], local15, a, local19);
		}
		if (local23 == 5) {
			Static6.outboundBuffer.pIsaac1(55);
			Static6.outboundBuffer.p2_alt1(a);
			Static6.outboundBuffer.p2_alt2(local15);
			Static6.outboundBuffer.p4rme(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 21) {
			if (Static266.game == 1) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			} else {
				local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
				if (!local560) {
					Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
				}
			}
			Static70.crossMode = 2;
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static25.y = Static60.mouseClickY;
			Static6.outboundBuffer.pIsaac1(228);
			Static6.outboundBuffer.p2(a);
			Static6.outboundBuffer.p2_alt1(Static225.originX + local15);
			Static6.outboundBuffer.p2_alt3(Static142.originZ + local19);
		}
		if (local23 == 4) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static6.outboundBuffer.pIsaac1(148);
				Static6.outboundBuffer.p2_alt2(a);
			}
		}
		if (local23 == 32) {
			com = Static201.method1418(local19, local15);
			if (com != null) {
				Static53.method1294();
				@Pc(1493) ServerActiveProperties local1493 = Static36.method940(com);
				Static247.method4246(local19, local15, local1493.method512(), local1493.anInt540, com.anInt499, com.anInt484);
				Static260.anInt5014 = 0;
				Static102.aClass100_545 = Static97.method1963(com);
				if (Static102.aClass100_545 == null) {
					Static102.aClass100_545 = Static250.aClass100_1042;
				}
				if (com.aBoolean32) {
					Static78.aClass100_466 = Static34.method882(new JString[] { com.aClass100_88, Static204.aClass100_896 });
				} else {
					Static78.aClass100_466 = Static34.method882(new JString[] { Static42.GREEN, com.aClass100_85, Static204.aClass100_896 });
				}
			}
			return;
		}
		if (local23 == 29) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static25.y = Static60.mouseClickY;
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static6.outboundBuffer.pIsaac1(180);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 35) {
			Static6.outboundBuffer.pIsaac1(161);
			Static6.outboundBuffer.p4_alt1(local19);
			Static6.outboundBuffer.p2_alt3(a);
			Static6.outboundBuffer.p2_alt3(local15);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 15) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static25.y = Static60.mouseClickY;
				Static6.outboundBuffer.pIsaac1(195);
				Static6.outboundBuffer.p2_alt2(Static15.anInt506);
				Static6.outboundBuffer.p4_alt1(Static98.anInt2512);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 34) {
			if (Static266.game == 1) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			} else {
				local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
				if (!local560) {
					Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
				}
			}
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static17.crossCycle = 0;
			Static6.outboundBuffer.pIsaac1(109);
			Static6.outboundBuffer.p2_alt1(local19 + Static142.originZ);
			Static6.outboundBuffer.p2(local15 + Static225.originX);
			Static6.outboundBuffer.p2_alt3(a);
		}
		if (local23 == 25) {
			Static6.outboundBuffer.pIsaac1(81);
			Static6.outboundBuffer.p2_alt2(local15);
			Static6.outboundBuffer.p2(a);
			Static6.outboundBuffer.p4rme(local19);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 2) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static6.outboundBuffer.pIsaac1(218);
				Static6.outboundBuffer.p2_alt1(a);
			}
		}
		@Pc(1955) int varp;
		if (local23 == 51) {
			Static6.outboundBuffer.pIsaac1(10);
			Static6.outboundBuffer.p4(local19);
			com = Component.getComponent(local19);
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
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static70.crossMode = 2;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static6.outboundBuffer.pIsaac1(115);
				Static6.outboundBuffer.p4_alt3(Static224.anInt5062);
				Static6.outboundBuffer.p2_alt1(Static185.anInt4370);
				Static6.outboundBuffer.p2_alt1(a);
				Static6.outboundBuffer.p2_alt3(Static274.anInt4997);
			}
		}
		if (local23 == 59) {
			Static6.outboundBuffer.pIsaac1(10);
			Static6.outboundBuffer.p4(local19);
			com = Component.getComponent(local19);
			if (com.scripts != null && com.scripts[0][0] == 5) {
				varp = com.scripts[0][1];
				VarPlayerDefinition.varPlayers[varp] = 1 - VarPlayerDefinition.varPlayers[varp];
				Static85.handleVarps(varp);
			}
		}
		if (local23 == 33) {
			local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
			if (!local560) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			}
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static25.y = Static60.mouseClickY;
			Static70.crossMode = 2;
			Static6.outboundBuffer.pIsaac1(101);
			Static6.outboundBuffer.p2_alt3(local15 + Static225.originX);
			Static6.outboundBuffer.p2_alt1(Static185.anInt4370);
			Static6.outboundBuffer.p2_alt1(Static274.anInt4997);
			Static6.outboundBuffer.p2_alt1(a);
			Static6.outboundBuffer.p2_alt3(Static142.originZ + local19);
			Static6.outboundBuffer.p4_alt3(Static224.anInt5062);
		}
		if (local23 == 1004) {
			Static17.crossCycle = 0;
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static6.outboundBuffer.pIsaac1(94);
			Static6.outboundBuffer.p2_alt3(a);
		}
		if (local23 == 11) {
			if (a == 0) {
				Static125.anInt3096 = 1;
				Static113.method3556(Player.plane, local15, local19);
			} else if (a == 1) {
				Static6.outboundBuffer.pIsaac1(131);
				Static6.outboundBuffer.p4_alt3(Static98.anInt2512);
				Static6.outboundBuffer.p2_alt2(Static225.originX + local15);
				Static6.outboundBuffer.p2_alt3(Static15.anInt506);
				Static6.outboundBuffer.p2_alt2(local19 + Static142.originZ);
			}
		}
		if (local23 == 8) {
			com = Component.getComponent(local19);
			@Pc(2287) boolean local2287 = true;
			if (com.contentType > 0) {
				local2287 = Static249.method4265(com);
			}
			if (local2287) {
				Static6.outboundBuffer.pIsaac1(10);
				Static6.outboundBuffer.p4(local19);
			}
		}
		if (local23 == 1) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static6.outboundBuffer.pIsaac1(248);
				Static6.outboundBuffer.p2_alt3(a);
				Static6.outboundBuffer.p2(Static274.anInt4997);
				Static6.outboundBuffer.p2(Static185.anInt4370);
				Static6.outboundBuffer.p4_alt3(Static224.anInt5062);
			}
		}
		if (local23 == 7) {
			Static6.outboundBuffer.pIsaac1(85);
			Static6.outboundBuffer.p4rme(local19);
			Static6.outboundBuffer.p2(local15);
			Static6.outboundBuffer.p2_alt2(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 24) {
			if (Static266.game == 1) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			} else {
				local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
				if (!local560) {
					Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
				}
			}
			Static70.crossMode = 2;
			Static25.y = Static60.mouseClickY;
			Static122.x = aClass6.mouseClickX;
			Static17.crossCycle = 0;
			Static6.outboundBuffer.pIsaac1(48);
			Static6.outboundBuffer.p2_alt2(local15 + Static225.originX);
			Static6.outboundBuffer.p2_alt3(a);
			Static6.outboundBuffer.p2_alt1(Static142.originZ + local19);
		}
		if (local23 == 38 && ClientScriptRunner.method4003(local31, local19, local15)) {
			Static6.outboundBuffer.pIsaac1(233);
			Static6.outboundBuffer.p2_alt3(local19 + Static142.originZ);
			Static6.outboundBuffer.p2_alt2(Static225.originX + local15);
			Static6.outboundBuffer.p2_alt3(Static15.anInt506);
			Static6.outboundBuffer.p4rme(Static98.anInt2512);
			Static6.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 13) {
			Static6.outboundBuffer.pIsaac1(6);
			Static6.outboundBuffer.p4(local19);
			Static6.outboundBuffer.p2_alt2(local15);
			Static6.outboundBuffer.p2_alt1(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 57) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static70.crossMode = 2;
				Static25.y = Static60.mouseClickY;
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static6.outboundBuffer.pIsaac1(175);
				Static6.outboundBuffer.p2_alt2(a);
			}
		}
		if (local23 == 22) {
			Static53.method1294();
			com = Component.getComponent(local19);
			Static224.anInt5062 = local19;
			Static185.anInt4370 = local15;
			Static260.anInt5014 = 1;
			Static274.anInt4997 = a;
			Static43.method1143(com);
			Static34.aClass100_203 = Static34.method882(new JString[] { Static8.aClass100_32, Static71.get(a).name, Static204.aClass100_896 });
			if (Static34.aClass100_203 == null) {
				Static34.aClass100_203 = Static92.aClass100_510;
			}
			return;
		}
		if (local23 == 50) {
			ClientScriptRunner.method4003(local31, local19, local15);
			Static6.outboundBuffer.pIsaac1(194);
			Static6.outboundBuffer.p2_alt3(local19 + Static142.originZ);
			Static6.outboundBuffer.p2_alt1(Static225.originX + local15);
			Static6.outboundBuffer.p2((int) (local31 >>> 32) & Integer.MAX_VALUE);
		}
		if (local23 == 48) {
			Static6.outboundBuffer.pIsaac1(154);
			Static6.outboundBuffer.p2_alt1(local15);
			Static6.outboundBuffer.p4rme(local19);
			Static6.outboundBuffer.p2_alt3(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 30) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static17.crossCycle = 0;
				Static122.x = aClass6.mouseClickX;
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Static6.outboundBuffer.pIsaac1(68);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		if (local23 == 43) {
			Static6.outboundBuffer.pIsaac1(153);
			Static6.outboundBuffer.p4_alt1(local19);
			Static6.outboundBuffer.p2_alt1(local15);
			Static6.outboundBuffer.p2_alt1(a);
			Static72.anInt2043 = 0;
			Static257.aClass13_7 = Component.getComponent(local19);
			Static250.anInt5444 = local15;
		}
		if (local23 == 39) {
			local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
			if (!local560) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			}
			Static25.y = Static60.mouseClickY;
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static17.crossCycle = 0;
			Static6.outboundBuffer.pIsaac1(73);
			Static6.outboundBuffer.p4rme(Static98.anInt2512);
			Static6.outboundBuffer.p2(Static142.originZ + local19);
			Static6.outboundBuffer.p2_alt3(a);
			Static6.outboundBuffer.p2_alt3(local15 + Static225.originX);
			Static6.outboundBuffer.p2_alt1(Static15.anInt506);
		}
		if (local23 == 12) {
			Static6.outboundBuffer.pIsaac1(82);
			Static6.outboundBuffer.p2(Static15.anInt506);
			Static6.outboundBuffer.p4rme(local19);
			Static6.outboundBuffer.p4(Static98.anInt2512);
			Static6.outboundBuffer.p2_alt3(local15);
		}
		if (local23 == 36) {
			if (a == 0) {
				Static187.anInt4422 = 1;
				Static113.method3556(Player.plane, local15, local19);
			} else if (Static191.staffModLevel > 0 && Static187.pressedKeys[82] && Static187.pressedKeys[81]) {
				Static61.teleport(local15 + Static225.originX, Static142.originZ - -local19, Player.plane);
			} else {
				Static6.outboundBuffer.pIsaac1(179);
				Static6.outboundBuffer.p2(local19 + Static142.originZ);
				Static6.outboundBuffer.p2(local15 + Static225.originX);
			}
		}
		if (local23 == 6) {
			local43 = Static159.players[a];
			if (local43 != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local43.pathTileX[0], 1, 0, 2, local43.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static25.y = Static60.mouseClickY;
				Static17.crossCycle = 0;
				Static70.crossMode = 2;
				Static122.x = aClass6.mouseClickX;
				Static6.outboundBuffer.pIsaac1(106);
				Static6.outboundBuffer.p2(a);
			}
		}
		if (local23 == 20) {
			if (Static266.game == 1) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
			} else {
				local560 = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.pathTileX[0]);
				if (!local560) {
					Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.pathTileX[0]);
				}
			}
			Static25.y = Static60.mouseClickY;
			Static17.crossCycle = 0;
			Static122.x = aClass6.mouseClickX;
			Static70.crossMode = 2;
			Static6.outboundBuffer.pIsaac1(33);
			Static6.outboundBuffer.p2(a);
			Static6.outboundBuffer.p2(Static225.originX + local15);
			Static6.outboundBuffer.p2_alt1(Static142.originZ + local19);
		}
		if (local23 == 16) {
			npc = NpcList.npcs[a];
			if (npc != null) {
				Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 1, false, 0, npc.pathTileX[0], 1, 0, 2, npc.pathTileZ[0], PlayerList.self.pathTileX[0]);
				Static122.x = aClass6.mouseClickX;
				Static17.crossCycle = 0;
				Static25.y = Static60.mouseClickY;
				Static70.crossMode = 2;
				Static6.outboundBuffer.pIsaac1(3);
				Static6.outboundBuffer.p2_alt3(a);
			}
		}
		if (Static260.anInt5014 != 0) {
			Static260.anInt5014 = 0;
			Static43.method1143(Component.getComponent(Static224.anInt5062));
		}
		if (Static241.aBoolean302) {
			Static53.method1294();
		}
		if (Static257.aClass13_7 != null && Static72.anInt2043 == 0) {
			Static43.method1143(Static257.aClass13_7);
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
	public static void method2239() {
		Static27.aClass99_4.method3103();
		Static244.aClass99_32.method3103();
		Static118.aClass99_16.method3103();
	}

	@OriginalMember(owner = "runetek4.client!i", name = "i", descriptor = "(Z)V")
	public static void method2245() {
		for (@Pc(6) Class3_Sub31 local6 = (Class3_Sub31) Static119.aClass133_9.head(); local6 != null; local6 = (Class3_Sub31) Static119.aClass133_9.prev()) {
			@Pc(14) int local14 = local6.anInt5878;
			if (Component.load(local14)) {
				@Pc(21) boolean local21 = true;
				@Pc(25) Component[] local25 = Component.cachedComponents[local14];
				@Pc(27) int local27;
				for (local27 = 0; local27 < local25.length; local27++) {
					if (local25[local27] != null) {
						local21 = local25[local27].aBoolean32;
						break;
					}
				}
				if (!local21) {
					local27 = (int) local6.nodeId;
					@Pc(60) Component local60 = Component.getComponent(local27);
					if (local60 != null) {
						Static43.method1143(local60);
					}
				}
			}
		}
	}
}
