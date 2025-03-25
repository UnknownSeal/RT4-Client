package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.SpotAnimEntity;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;

public class Protocol {
    @OriginalMember(owner = "client!ag", name = "P", descriptor = "Lclient!i;")
    public static final PacketBit outboundBuffer = new PacketBit(5000);
    @OriginalMember(owner = "runetek4.client!eg", name = "e", descriptor = "Lclient!i;")
    public static final PacketBit inboundBuffer = new PacketBit(65536);
    @OriginalMember(owner = "runetek4.client!ef", name = "f", descriptor = "Lclient!na;")
    public static final JString DUELSTAKE = JString.parse(":duelstake:");
    @OriginalMember(owner = "client!en", name = "h", descriptor = "Lclient!na;")
    public static final JString CHALREQ = JString.parse(":chalreq:");
    @OriginalMember(owner = "client!fb", name = "i", descriptor = "Lclient!na;")
    public static final JString MAX_AGE = JString.parse("; Max)2Age=");
    @OriginalMember(owner = "runetek4.client!qi", name = "t", descriptor = "I")
    public static int anInt4762 = 0;
    @OriginalMember(owner = "runetek4.client!fe", name = "R", descriptor = "Z")
    public static boolean prevFocus = true;
    @OriginalMember(owner = "runetek4.client!dm", name = "q", descriptor = "I")
    public static int opcode4 = 0;
    @OriginalMember(owner = "runetek4.client!af", name = "k", descriptor = "I")
    public static int opcode3 = 0;
    @OriginalMember(owner = "runetek4.client!na", name = "l", descriptor = "I")
    public static int opcode = 0;
    @OriginalMember(owner = "runetek4.client!sj", name = "t", descriptor = "I")
    public static int opcode2 = 0;
    @OriginalMember(owner = "runetek4.client!pm", name = "ab", descriptor = "Z")
    public static boolean aBoolean228 = true;
    @OriginalMember(owner = "runetek4.client!pe", name = "a", descriptor = "I")
	public static int verifyId = 0;
    @OriginalMember(owner = "runetek4.client!od", name = "i", descriptor = "I")
    public static int sceneDelta = 0;
    @OriginalMember(owner = "runetek4.client!cj", name = "n", descriptor = "Lsignlink!im;")
    public static PrivilegedRequest openUrlRequest;
    @OriginalMember(owner = "runetek4.client!na", name = "W", descriptor = "Z")
    public static boolean newTab;
    @OriginalMember(owner = "client!ck", name = "eb", descriptor = "Z")
    public static boolean verifyIdChanged = false;
    @OriginalMember(owner = "runetek4.client!kd", name = "ob", descriptor = "I")
    public static int anInt3251 = 0;
    @OriginalMember(owner = "runetek4.client!jk", name = "B", descriptor = "Lclient!ma;")
    public static BufferedSocket gameServerSocket;
    @OriginalMember(owner = "runetek4.client!dg", name = "h", descriptor = "Lclient!be;")
    public static Component aClass13_11;
    @OriginalMember(owner = "runetek4.client!kf", name = "l", descriptor = "I")
    public static int anInt5235 = 0;
    @OriginalMember(owner = "runetek4.client!t", name = "l", descriptor = "Lclient!ma;")
    public static BufferedSocket aClass95_4;
    @OriginalMember(owner = "runetek4.client!sc", name = "o", descriptor = "I")
    public static int packetSize = 0;
    @OriginalMember(owner = "runetek4.client!pb", name = "ab", descriptor = "I")
	public static int anInt4422 = 0;

    @OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "(Z)V")
    public static void readPlayerInfo() {
        @Pc(6) int local6 = inboundBuffer.gBit(8);

        if (PlayerList.playerCount > local6) {
            for (int inxed = local6; inxed < PlayerList.playerCount; inxed++) {
                Static52.entityRemovalIds[Static240.entityRemovalCount++] = PlayerList.playerIds[inxed];
            }
        }
        if (local6 > PlayerList.playerCount) {
            throw new RuntimeException("gppov1");
        }

        PlayerList.playerCount = 0;

        for (int index = 0; index < local6; index++) {
            @Pc(75) int local75 = PlayerList.playerIds[index];
            @Pc(79) Player local79 = PlayerList.players[local75];
            @Pc(84) int local84 = inboundBuffer.gBit(1);
            if (local84 == 0) {
                PlayerList.playerIds[PlayerList.playerCount++] = local75;
                local79.cycle = client.loop;
            } else {
                @Pc(107) int local107 = inboundBuffer.gBit(2);
                if (local107 == 0) {
                    PlayerList.playerIds[PlayerList.playerCount++] = local75;
                    local79.cycle = client.loop;
                    Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                } else {
                    @Pc(153) int local153;
                    @Pc(163) int local163;
                    if (local107 == 1) {
                        PlayerList.playerIds[PlayerList.playerCount++] = local75;
                        local79.cycle = client.loop;
                        local153 = inboundBuffer.gBit(3);
                        local79.method2684(1, local153);
                        local163 = inboundBuffer.gBit(1);
                        if (local163 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                        }
                    } else if (local107 == 2) {
                        PlayerList.playerIds[PlayerList.playerCount++] = local75;
                        local79.cycle = client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            local153 = inboundBuffer.gBit(3);
                            local79.method2684(2, local153);
                            local163 = inboundBuffer.gBit(3);
                            local79.method2684(2, local163);
                        } else {
                            local153 = inboundBuffer.gBit(3);
                            local79.method2684(0, local153);
                        }
                        local153 = inboundBuffer.gBit(1);
                        if (local153 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
                        }
                    } else if (local107 == 3) {
                        Static52.entityRemovalIds[Static240.entityRemovalCount++] = local75;
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!an", name = "h", descriptor = "(I)Z")
    public static boolean readPacket() {
        try {
            return readPacketInternal();
        } catch (@Pc(14) IOException ioException) {
            Game.tryReconnect();
            return true;
        } catch (@Pc(19) Exception exception) {
            @Pc(61) String message = "T2 - " + opcode + "," + opcode3 + "," + opcode4 + " - " + packetSize + "," + (Camera.originX + PlayerList.self.movementQueueX[0]) + "," + (PlayerList.self.movementQueueZ[0] + Camera.originZ) + " - ";
            for (@Pc(63) int local63 = 0; local63 < packetSize && local63 < 50; local63++) {
                message = message + inboundBuffer.data[local63] + ",";
            }
            TracingException.report(message, exception);
            Game.processLogout();
            return true;
        }
    }

    @OriginalMember(owner = "client!ac", name = "a", descriptor = "(B)Z")
    public static boolean readPacketInternal() throws IOException {
        if (gameServerSocket == null) {
            return false;
        }
        @Pc(14) int available = gameServerSocket.available();
        if (available == 0) {
            return false;
        }
        if (opcode == -1) {
            available--;
            gameServerSocket.read(0, 1, inboundBuffer.data);
            inboundBuffer.offset = 0;
            opcode = inboundBuffer.gIssac1();
            packetSize = Static234.anIntArray456[opcode];
        }
        if (packetSize == -1) {
            if (available <= 0) {
                return false;
            }
            gameServerSocket.read(0, 1, inboundBuffer.data);
            available--;
            packetSize = inboundBuffer.data[0] & 0xFF;
        }
        if (packetSize == -2) {
            if (available <= 1) {
                return false;
            }
            available -= 2;
            gameServerSocket.read(0, 2, inboundBuffer.data);
            inboundBuffer.offset = 0;
            packetSize = inboundBuffer.g2();
        }
        if (packetSize > available) {
            return false;
        }
        inboundBuffer.offset = 0;
        gameServerSocket.read(0, packetSize, inboundBuffer.data);
        opcode4 = opcode3;
        opcode3 = opcode2;
        opcode2 = opcode;
        LoginManager.idleNetCycles = 0;
        @Pc(133) int ii;
        if (opcode == 60) {
            ii = inboundBuffer.g2sub();
            @Pc(137) byte local137 = inboundBuffer.g1neg();
            ObjTypeList.method2575(local137, ii);
            opcode = -1;
            return true;
        }
        @Pc(171) int slot;
        @Pc(156) JString message2;
        if (opcode == 115) {
            int verifyID = inboundBuffer.g2();
            message2 = inboundBuffer.gjstr();
            @Pc(163) Object[] local163 = new Object[message2.length() + 1];
            for (slot = message2.length() - 1; slot >= 0; slot--) {
                if (message2.charAt(slot) == 115) {
                    local163[slot + 1] = inboundBuffer.gjstr();
                } else {
                    local163[slot + 1] = Integer.valueOf(inboundBuffer.g4());
                }
            }
            local163[0] = Integer.valueOf(inboundBuffer.g4());
            if (setVerifyID(verifyID)) {
                @Pc(226) HookRequest local226 = new HookRequest();
                local226.arguments = local163;
                ClientScriptRunner.run(local226);
            }
            opcode = -1;
            return true;
        }
        @Pc(275) long username;
        @Pc(262) boolean ignored;
        @Pc(277) int i;
        @Pc(506) JString local506;
        if (opcode == 70) {
            @Pc(245) JString message = inboundBuffer.gjstr();
            if (message.endsWith(Static196.TRADEREQ)) {
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = message2.encode37();
                ignored = false;
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.overrideChat == 0) {
                    Chat.addMessage(message2, 4, LocalizedText.TRADEREQ);
                }
            } else if (message.endsWith(CHALREQ)) {
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = message2.encode37();
                ignored = false;
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.overrideChat == 0) {
                    local506 = message.substring(message.length() - 9, message.indexOf(JString.COLON_SIGN) + 1);
                    Chat.addMessage(message2, 8, local506);
                }
            } else if (message.endsWith(Static191.ASSISTREQ)) {
                ignored = false;
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = message2.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (username == IgnoreList.encodedIgnores[i]) {
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
            } else if (message.endsWith(DUELSTAKE)) {
                ignored = false;
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = message2.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (username == IgnoreList.encodedIgnores[i]) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.overrideChat == 0) {
                    Chat.addMessage(message2, 14, JString.EMPTY);
                }
            } else if (message.endsWith(Static112.DUELFRIEND)) {
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                ignored = false;
                username = message2.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.overrideChat == 0) {
                    Chat.addMessage(message2, 15, JString.EMPTY);
                }
            } else if (message.endsWith(Static217.CLANREQ)) {
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = message2.encode37();
                ignored = false;
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (username == IgnoreList.encodedIgnores[i]) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.overrideChat == 0) {
                    Chat.addMessage(message2, 16, JString.EMPTY);
                }
            } else if (message.endsWith(Static164.ALLYREQ)) {
                message2 = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                ignored = false;
                username = message2.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.overrideChat == 0) {
                    local506 = message.substring(message.length() - 9, message.indexOf(JString.COLON_SIGN) + 1);
                    Chat.addMessage(message2, 21, local506);
                }
            } else {
                Chat.addMessage(JString.EMPTY, 0, message);
            }
            opcode = -1;
            return true;
        }
        @Pc(786) int xp;
        @Pc(790) JString local790;
        if (opcode == 123) {
            ii = inboundBuffer.g2le();
            int verifyID = inboundBuffer.g2sub();
            local790 = inboundBuffer.gjstr();
            if (setVerifyID(verifyID)) {
                DelayedStateChange.method3498(local790, ii);
            }
            opcode = -1;
            return true;
        } else if (opcode == 230) {
            Static180.anInt4264 = inboundBuffer.g1add();
            Static115.anInt2940 = inboundBuffer.g1_alt3();
            while (packetSize > inboundBuffer.offset) {
                opcode = inboundBuffer.g1();
                Static75.method1634();
            }
            opcode = -1;
            return true;
        } else if (opcode == 153) {
            opcode = -1;
            LoginManager.mapFlagX = 0;
            return true;
        } else {
            @Pc(864) int world;
            if (opcode == 220) {
                ii = inboundBuffer.p4rme();
                xp = inboundBuffer.g2le();
                int verifyID = inboundBuffer.g2();
                if (setVerifyID(verifyID)) {
                    DelayedStateChange.method3938(xp, ii);
                }
                opcode = -1;
                return true;
            }
            @Pc(884) long username2;
            @Pc(908) int local908;
            @Pc(916) int local916;
            @Pc(899) long local899;
            @Pc(904) long local904;
            if (opcode == 81) {
                username2 = inboundBuffer.g8();
                inboundBuffer.g1s();
                username = inboundBuffer.g8();
                local899 = inboundBuffer.g2();
                local904 = inboundBuffer.g3();
                local908 = inboundBuffer.g1();
                @Pc(910) boolean local910 = false;
                local916 = inboundBuffer.g2();
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
                        for (local924 = 0; local924 < IgnoreList.ignoreCount; local924++) {
                            if (IgnoreList.encodedIgnores[local924] == username2) {
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
                    @Pc(999) JString local999 = QuickChatPhraseTypeList.list(local916).method770(inboundBuffer);
                    if (local908 == 2 || local908 == 3) {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).toTitleCase() }));
                    } else if (local908 == 1) {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).toTitleCase() }));
                    } else {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), Base37.decode37(username2).toTitleCase());
                    }
                }
                opcode = -1;
                return true;
            }
            @Pc(1146) int count;
            @Pc(1160) int local1160;
            @Pc(1245) boolean local1245;
            if (opcode == 55) {
                ClanChat.transmitAt = InterfaceList.transmitTimer;
                username2 = inboundBuffer.g8();
                if (username2 == 0L) {
                    ClanChat.owner = null;
                    opcode = -1;
                    ClanChat.name = null;
                    ClanChat.members = null;
                    ClanChat.size = 0;
                    return true;
                }
                username = inboundBuffer.g8();
                ClanChat.name = Base37.decode37(username);
                ClanChat.owner = Base37.decode37(username2);
                ClanChat.minKick = inboundBuffer.g1s();
                count = inboundBuffer.g1();
                if (count == 255) {
                    opcode = -1;
                    return true;
                }
                ClanChat.size = count;
                @Pc(1158) ClanMember[] local1158 = new ClanMember[100];
                for (local1160 = 0; local1160 < ClanChat.size; local1160++) {
                    local1158[local1160] = new ClanMember();
                    local1158[local1160].nodeId = inboundBuffer.g8();
                    local1158[local1160].username = Base37.decode37(local1158[local1160].nodeId);
                    local1158[local1160].world = inboundBuffer.g2();
                    local1158[local1160].rank = inboundBuffer.g1s();
                    local1158[local1160].worldName = inboundBuffer.gjstr();
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
                opcode = -1;
                return true;
            } else if (opcode == 164) {
                ii = inboundBuffer.g4rme();
                Player.lastLogAddress = GameShell.signLink.getReverseDns(ii);
                opcode = -1;
                return true;
            } else if (opcode == 225) {
                // PLAYER_INFO
                Static64.getPlayer();
                opcode = -1;
                return true;
            } else if (opcode == 48) {
                int verifyID = inboundBuffer.g2();
                message2 = inboundBuffer.gjstr();
                world = inboundBuffer.g2leadd();
                if (setVerifyID(verifyID)) {
                    DelayedStateChange.method3498(message2, world);
                }
                opcode = -1;
                return true;
            } else if (opcode == 232) {
                Chat.publicFilter = inboundBuffer.g1();
                Chat.privateFilter = inboundBuffer.g1();
                Chat.tradeFilter = inboundBuffer.g1();
                opcode = -1;
                return true;
            } else {
                @Pc(1409) JString local1409;
                if (opcode == 44) {
                    ii = inboundBuffer.g2leadd();
                    if (ii == 65535) {
                        ii = -1;
                    }
                    xp = inboundBuffer.g1();
                    world = inboundBuffer.g1();
                    local1409 = inboundBuffer.gjstr();
                    if (world >= 1 && world <= 8) {
                        if (local1409.equalsIgnoreCase(MiniMenu.NULL)) {
                            local1409 = null;
                        }
                        Player.options[world - 1] = local1409;
                        Player.cursors[world - 1] = ii;
                        Player.secondaryOptions[world - 1] = xp == 0;
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 226) {
                    ii = inboundBuffer.g4();
                    xp = inboundBuffer.g2sub();
                    ObjTypeList.method2575(ii, xp);
                    opcode = -1;
                    return true;
                } else if (opcode == 21) {
                    ii = inboundBuffer.p1neg();
                    int verifyID = inboundBuffer.g2();
                    world = inboundBuffer.g4me();
                    if (setVerifyID(verifyID)) {
                        DelayedStateChange.method2905(world, ii);
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 145) {
                    // IF_OPENTOP
                    int parent = inboundBuffer.g2leadd();
                    int reset = inboundBuffer.g1add();
                    int verifyID = inboundBuffer.g2leadd();
                    if (setVerifyID(verifyID)) {
                        if (reset == 2) {
                            Static5.method34();
                        }
                        InterfaceList.topLevelInterface = parent;
                        InterfaceList.method1753(parent);
                        InterfaceList.method3712(false);
                        InterfaceList.method1626(InterfaceList.topLevelInterface);
                        for (slot = 0; slot < 100; slot++) {
                            InterfaceList.aBooleanArray100[slot] = true;
                        }
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 69) {
                    int verifyID = inboundBuffer.g2leadd();
                    xp = inboundBuffer.g4();
                    world = inboundBuffer.g2sub();
                    if (setVerifyID(verifyID)) {
                        DelayedStateChange.updateVarC(world, xp);
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 141) {
                    username2 = inboundBuffer.g8();
                    world = inboundBuffer.g2();
                    local1409 = QuickChatPhraseTypeList.list(world).method770(inboundBuffer);
                    Chat.add(world, 19, local1409, null, Base37.decode37(username2).toTitleCase());
                    opcode = -1;
                    return true;
                } else if (opcode == 169) {
                    Static271.method4598(inboundBuffer);
                    opcode = -1;
                    return true;
                } else if (opcode == 89) {
                    VarpDomain.resetVarBits();
                    InterfaceList.redrawActiveInterfaces();
                    Static70.updatedVarpsWriterIndex += 32;
                    opcode = -1;
                    return true;
                } else if (opcode == 125) {
                    int verifyID = inboundBuffer.g2();
                    xp = inboundBuffer.g1();
                    world = inboundBuffer.g1();
                    slot = inboundBuffer.g2();
                    count = inboundBuffer.g1();
                    i = inboundBuffer.g1();
                    if (setVerifyID(verifyID)) {
                        Camera.method3849(slot, world, count, xp, i);
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 36) {
                    ii = inboundBuffer.p4rme();
                    xp = inboundBuffer.g2les();
                    int verifyID = inboundBuffer.g2sub();
                    if (setVerifyID(verifyID)) {
                        DelayedStateChange.method3893(ii, xp);
                    }
                    opcode = -1;
                    return true;
                } else {
                    @Pc(1814) ServerActiveProperties local1814;
                    @Pc(1804) ServerActiveProperties local1804;
                    if (opcode == 9) {
                        ii = inboundBuffer.g2leadd();
                        xp = inboundBuffer.g4me();
                        int verifyID = inboundBuffer.g2sub();
                        slot = inboundBuffer.g2le();
                        if (slot == 65535) {
                            slot = -1;
                        }
                        count = inboundBuffer.g2sub();
                        if (count == 65535) {
                            count = -1;
                        }
                        if (setVerifyID(verifyID)) {
                            for (i = count; i <= slot; i++) {
                                local904 = (long) i + ((long) xp << 32);
                                local1804 = (ServerActiveProperties) InterfaceList.properties.getNode(local904);
                                if (local1804 != null) {
                                    local1814 = new ServerActiveProperties(local1804.anInt546, ii);
                                    local1804.unlink();
                                } else if (i == -1) {
                                    local1814 = new ServerActiveProperties(InterfaceList.getComponent(xp).properties.anInt546, ii);
                                } else {
                                    local1814 = new ServerActiveProperties(0, ii);
                                }
                                InterfaceList.properties.put(local1814, local904);
                            }
                        }
                        opcode = -1;
                        return true;
                    }
                    @Pc(1986) int j;
                    if (opcode == 56) {
                        ii = inboundBuffer.g2();
                        xp = inboundBuffer.g2le();
                        world = inboundBuffer.g4rme();
                        slot = inboundBuffer.g2leadd();
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
                                    if (slot != -1 && local1894.spotanimFrame != -1 && SeqTypeList.getAnimationSequence(Static34.method877(slot).animationId).priority < SeqTypeList.getAnimationSequence(Static34.method877(local1894.spotanimFrame).animationId).priority) {
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
                                                local1994 = SeqTypeList.getAnimationSequence(j);
                                                if (local1994 != null && local1994.anIntArray473 != null) {
                                                    SoundPlayer.playSeqSound(local1894.zFine, local1994, local1894.xFine, false, 0);
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
                                    if (slot != -1 && local2033.spotanimFrame != -1 && SeqTypeList.getAnimationSequence(Static34.method877(slot).animationId).priority < SeqTypeList.getAnimationSequence(Static34.method877(local2033.spotanimFrame).animationId).priority) {
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
                                                local1994 = SeqTypeList.getAnimationSequence(j);
                                                if (local1994 != null && local1994.anIntArray473 != null) {
                                                    SoundPlayer.playSeqSound(local2033.zFine, local1994, local2033.xFine, local2033 == PlayerList.self, 0);
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
                        opcode = -1;
                        return true;
                    } else if (opcode == 207) {
                        ii = inboundBuffer.p4rme();
                        int verifyID = inboundBuffer.g2sub();
                        world = inboundBuffer.g2();
                        slot = inboundBuffer.g2sub();
                        if (setVerifyID(verifyID)) {
                            DelayedStateChange.setComponentModelRotationSpeedServer(slot + (world << 16), ii);
                        }
                        opcode = -1;
                        return true;
                    } else if (opcode == 38) {
                        // UPDATE_STAT
                        InterfaceList.redrawActiveInterfaces();
                        ii = inboundBuffer.g1add();
                        xp = inboundBuffer.g4rme();
                        world = inboundBuffer.g1();
                        PlayerSkillXpTable.skillExperience[world] = xp;
                        PlayerSkillXpTable.skillLevel[world] = ii;
                        PlayerSkillXpTable.skillBaseLevel[world] = 1;
                        for (slot = 0; slot < 98; slot++) {
                            if (PlayerSkillXpTable.levelExperience[slot] <= xp) {
                                PlayerSkillXpTable.skillBaseLevel[world] = slot + 2;
                            }
                        }
                        PlayerSkillXpTable.updatedStats[PlayerSkillXpTable.updatedStatsWriterIndex++ & 0x1F] = world;
                        opcode = -1;
                        return true;
                    } else if (opcode == 104 || opcode == 121 || opcode == 97 || opcode == 14 || opcode == 202 || opcode == 135 || opcode == 17 || opcode == 16 || opcode == 240 || opcode == 33 || opcode == 20 || opcode == 195 || opcode == 179) {
                        Static75.method1634();
                        opcode = -1;
                        return true;
                    } else if (opcode == 149) {
                        int verifyID = inboundBuffer.g2();
                        xp = inboundBuffer.g4();
                        if (setVerifyID(verifyID)) {
                            @Pc(2441) ComponentPointer local2441 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) xp);
                            if (local2441 != null) {
                                InterfaceList.closeInterface(true, local2441);
                            }
                            if (ClientScriptRunner.aClass13_10 != null) {
                                InterfaceList.redraw(ClientScriptRunner.aClass13_10);
                                ClientScriptRunner.aClass13_10 = null;
                            }
                        }
                        opcode = -1;
                        return true;
                    } else if (opcode == 187) {
                        ii = inboundBuffer.g2le();
                        int verifyID = inboundBuffer.g2();
                        world = inboundBuffer.g2();
                        if (setVerifyID(verifyID)) {
                            Camera.orbitCameraYaw = ii;
                            Camera.orbitCameraPitch = world;
                            if (Camera.cameraType == 2) {
                                Camera.cameraPitch = Camera.orbitCameraPitch;
                                Camera.cameraYaw = Camera.orbitCameraYaw;
                            }
                            SceneCamera.clampCameraAngle();
                        }
                        opcode = -1;
                        return true;
                    } else if (opcode == 132) {
                        ii = inboundBuffer.g2();
                        int verifyID = inboundBuffer.g2sub();
                        world = inboundBuffer.g2leadd();
                        slot = inboundBuffer.g2leadd();
                        count = inboundBuffer.g4();
                        if (setVerifyID(verifyID)) {
                            DelayedStateChange.updateView(world, count, slot, ii);
                        }
                        opcode = -1;
                        return true;
                    } else if (opcode == 112) {
                        Static115.anInt2940 = inboundBuffer.g1();
                        Static180.anInt4264 = inboundBuffer.p1neg();
                        for (ii = Static115.anInt2940; ii < Static115.anInt2940 + 8; ii++) {
                            for (xp = Static180.anInt4264; xp < Static180.anInt4264 + 8; xp++) {
                                if (SceneGraph.objStacks[Player.plane][ii][xp] != null) {
                                    SceneGraph.objStacks[Player.plane][ii][xp] = null;
                                    spawnGroundObject(xp, ii);
                                }
                            }
                        }
                        for (@Pc(2604) ChangeLocRequest local2604 = (ChangeLocRequest) ChangeLocRequest.queue.head(); local2604 != null; local2604 = (ChangeLocRequest) ChangeLocRequest.queue.next()) {
                            if (local2604.x >= Static115.anInt2940 && Static115.anInt2940 + 8 > local2604.x && local2604.z >= Static180.anInt4264 && local2604.z < Static180.anInt4264 + 8 && local2604.level == Player.plane) {
                                local2604.resetLoops = 0;
                            }
                        }
                        opcode = -1;
                        return true;
                    } else if (opcode == 144) {
                        ii = inboundBuffer.p4rme();
                        @Pc(2666) Component local2666 = InterfaceList.getComponent(ii);
                        for (world = 0; world < local2666.invSlotObjId.length; world++) {
                            local2666.invSlotObjId[world] = -1;
                            local2666.invSlotObjId[world] = 0;
                        }
                        InterfaceList.redraw(local2666);
                        opcode = -1;
                        return true;
                    } else if (opcode == 130) {
                        ii = inboundBuffer.g4me();
                        int verifyID = inboundBuffer.g2leadd();
                        world = inboundBuffer.g2sub();
                        if (world == 65535) {
                            world = -1;
                        }
                        if (setVerifyID(verifyID)) {
                            DelayedStateChange.updateComponentModel(-1, 1, ii, world);
                        }
                        opcode = -1;
                        return true;
                    } else if (opcode == 192) {
                        MiniMap.state = inboundBuffer.g1();
                        opcode = -1;
                        return true;
                    } else if (opcode == 13) {
                        ii = inboundBuffer.g1_alt3();
                        xp = inboundBuffer.g1add();
                        world = inboundBuffer.g1();
                        Player.plane = xp >> 1;
                        PlayerList.self.teleport(ii, (xp & 0x1) == 1, world);
                        opcode = -1;
                        return true;
                    } else {
                        @Pc(3002) int local3002;
                        @Pc(3038) JString local3038;
                        @Pc(3020) JString local3020;
                        if (opcode == 62) {
                            username2 = inboundBuffer.g8();
                            world = inboundBuffer.g2();
                            slot = inboundBuffer.g1();
                            ignored = true;
                            if (username2 < 0L) {
                                username2 &= Long.MAX_VALUE;
                                ignored = false;
                            }
                            local506 = JString.EMPTY;
                            if (world > 0) {
                                local506 = inboundBuffer.gjstr();
                            }
                            @Pc(2834) JString displayName = Base37.decode37(username2).toTitleCase();
                            for (j = 0; j < FriendList.friendCount; j++) {
                                if (username2 == FriendList.encodedUsernames[j]) {
                                    if (world != FriendList.friendWorlds[j]) {
                                        FriendList.friendWorlds[j] = world;
                                        if (world > 0) {
                                            Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGIN}));
                                        }
                                        if (world == 0) {
                                            Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGOUT}));
                                        }
                                    }
                                    FriendList.worldNames[j] = local506;
                                    FriendList.ranks[j] = slot;
                                    displayName = null;
                                    FriendList.friendGame[j] = ignored;
                                    break;
                                }
                            }
                            if (displayName != null && FriendList.friendCount < 200) {
                                FriendList.encodedUsernames[FriendList.friendCount] = username2;
                                FriendList.friendUsernames[FriendList.friendCount] = displayName;
                                FriendList.friendWorlds[FriendList.friendCount] = world;
                                FriendList.worldNames[FriendList.friendCount] = local506;
                                FriendList.ranks[FriendList.friendCount] = slot;
                                FriendList.friendGame[FriendList.friendCount] = ignored;
                                FriendList.friendCount++;
                            }
                            FriendList.transmitAt = InterfaceList.transmitTimer;
                            local908 = FriendList.friendCount;
                            while (local908 > 0) {
                                local908--;
                                @Pc(2961) boolean local2961 = true;
                                for (local916 = 0; local916 < local908; local916++) {
                                    if (FriendList.friendWorlds[local916] != Static125.worldId && Static125.worldId == FriendList.friendWorlds[local916 + 1] || FriendList.friendWorlds[local916] == 0 && FriendList.friendWorlds[local916 + 1] != 0) {
                                        local2961 = false;
                                        local3002 = FriendList.friendWorlds[local916];
                                        FriendList.friendWorlds[local916] = FriendList.friendWorlds[local916 + 1];
                                        FriendList.friendWorlds[local916 + 1] = local3002;
                                        local3020 = FriendList.worldNames[local916];
                                        FriendList.worldNames[local916] = FriendList.worldNames[local916 + 1];
                                        FriendList.worldNames[local916 + 1] = local3020;
                                        local3038 = FriendList.friendUsernames[local916];
                                        FriendList.friendUsernames[local916] = FriendList.friendUsernames[local916 + 1];
                                        FriendList.friendUsernames[local916 + 1] = local3038;
                                        @Pc(3056) long local3056 = FriendList.encodedUsernames[local916];
                                        FriendList.encodedUsernames[local916] = FriendList.encodedUsernames[local916 + 1];
                                        FriendList.encodedUsernames[local916 + 1] = local3056;
                                        @Pc(3074) int local3074 = FriendList.ranks[local916];
                                        FriendList.ranks[local916] = FriendList.ranks[local916 + 1];
                                        FriendList.ranks[local916 + 1] = local3074;
                                        @Pc(3092) boolean local3092 = FriendList.friendGame[local916];
                                        FriendList.friendGame[local916] = FriendList.friendGame[local916 + 1];
                                        FriendList.friendGame[local916 + 1] = local3092;
                                    }
                                }
                                if (local2961) {
                                    break;
                                }
                            }
                            opcode = -1;
                            return true;
                        } else if (opcode == 160) {
                            if (packetSize == 0) {
                                MiniMenu.walkText = LocalizedText.WALKHERE;
                            } else {
                                MiniMenu.walkText = inboundBuffer.gjstr();
                            }
                            opcode = -1;
                            return true;
                        } else if (opcode == 128) {
                            for (ii = 0; ii < VarPlayerDefinition.activeVarps.length; ii++) {
                                if (VarPlayerDefinition.varPlayerCache[ii] != VarPlayerDefinition.activeVarps[ii]) {
                                    VarPlayerDefinition.activeVarps[ii] = VarPlayerDefinition.varPlayerCache[ii];
                                    VarpDomain.refreshMagicVarp(ii);
                                    Static83.updatedVarps[Static70.updatedVarpsWriterIndex++ & 0x1F] = ii;
                                }
                            }
                            opcode = -1;
                            return true;
                        } else if (opcode == 154) {
                            int verifyID = inboundBuffer.g2();
                            xp = inboundBuffer.g1();
                            world = inboundBuffer.g1();
                            slot = inboundBuffer.g2();
                            count = inboundBuffer.g1();
                            i = inboundBuffer.g1();
                            if (setVerifyID(verifyID)) {
                                Camera.method2722(true, count, slot, i, world, xp);
                            }
                            opcode = -1;
                            return true;
                        } else if (opcode == 247) {
                            username2 = inboundBuffer.g8();
                            username = inboundBuffer.g2();
                            local899 = inboundBuffer.g3();
                            local1160 = inboundBuffer.g1();
                            j = inboundBuffer.g2();
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
                                    for (local3272 = 0; local3272 < IgnoreList.ignoreCount; local3272++) {
                                        if (username2 == IgnoreList.encodedIgnores[local3272]) {
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
                                local3020 = QuickChatPhraseTypeList.list(j).method770(inboundBuffer);
                                if (local1160 == 2) {
                                    Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).toTitleCase() }));
                                } else if (local1160 == 1) {
                                    Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).toTitleCase() }));
                                } else {
                                    Chat.add(j, 18, local3020, null, Base37.decode37(username2).toTitleCase());
                                }
                            }
                            opcode = -1;
                            return true;
                        } else {
                            @Pc(3456) ComponentPointer local3456;
                            if (opcode == 176) {
                                ii = inboundBuffer.g4rme();
                                int verifyID = inboundBuffer.g2sub();
                                world = inboundBuffer.g4rme();
                                if (setVerifyID(verifyID)) {
                                    @Pc(3449) ComponentPointer local3449 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) ii);
                                    local3456 = (ComponentPointer) InterfaceList.openInterfaces.getNode((long) world);
                                    if (local3456 != null) {
                                        InterfaceList.closeInterface(local3449 == null || local3456.interfaceId != local3449.interfaceId, local3456);
                                    }
                                    if (local3449 != null) {
                                        local3449.unlink();
                                        InterfaceList.openInterfaces.put(local3449, (long) world);
                                    }
                                    @Pc(3490) Component local3490 = InterfaceList.getComponent(ii);
                                    if (local3490 != null) {
                                        InterfaceList.redraw(local3490);
                                    }
                                    local3490 = InterfaceList.getComponent(world);
                                    if (local3490 != null) {
                                        InterfaceList.redraw(local3490);
                                        InterfaceList.method531(local3490, true);
                                    }
                                    if (InterfaceList.topLevelInterface != -1) {
                                        InterfaceList.runScripts(1, InterfaceList.topLevelInterface);
                                    }
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 27) {
                                int verifyID = inboundBuffer.g2();
                                xp = inboundBuffer.g1();
                                world = inboundBuffer.g1();
                                slot = inboundBuffer.g1();
                                count = inboundBuffer.g1();
                                i = inboundBuffer.g2();
                                if (setVerifyID(verifyID)) {
                                    Camera.cameraModifierEnabled[xp] = true;
                                    Camera.cameraModifierJitter[xp] = world;
                                    Camera.cameraAmplitude[xp] = slot;
                                    Camera.cameraFrequency[xp] = count;
                                    Static31.cameraModifierCycle[xp] = i;
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 2) {
                                ii = inboundBuffer.g4rme();
                                int verifyID = inboundBuffer.g2sub();
                                world = inboundBuffer.g2leadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.setColor(world, ii);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 85) {
                                Player.systemUpdateTimer = inboundBuffer.g2() * 30;
                                opcode = -1;
                                InterfaceList.miscTransmitAt = InterfaceList.transmitTimer;
                                return true;
                            } else if (opcode == 114) {
                                Class6.method3654(GameShell.signLink, inboundBuffer, packetSize);
                                opcode = -1;
                                return true;
                            } else if (opcode == 65) {
                                int verifyID = inboundBuffer.g2le();
                                xp = inboundBuffer.p1neg();
                                world = inboundBuffer.g2leadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.updateVarC(world, xp);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 234) {
                                // UPDATE_RUNENERGY
                                InterfaceList.redrawActiveInterfaces();
                                Player.energy = inboundBuffer.g1();
                                InterfaceList.miscTransmitAt = InterfaceList.transmitTimer;
                                opcode = -1;
                                return true;
                            } else if (opcode == 209) {
                                if (InterfaceList.topLevelInterface != -1) {
                                    InterfaceList.runScripts(0, InterfaceList.topLevelInterface);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 191) {
                                ii = inboundBuffer.g2le();
                                Inv.delete(ii);
                                Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & 0x1F] = ii & 0x7FFF;
                                opcode = -1;
                                return true;
                            } else if (opcode == 102) {
                                ii = inboundBuffer.g2le();
                                xp = inboundBuffer.g1_alt3();
                                world = inboundBuffer.g2();
                                @Pc(3766) Npc local3766 = NpcList.npcs[ii];
                                if (local3766 != null) {
                                    Static223.method3855(xp, world, local3766);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 159) {
                                // UPDATE_RUNWEIGHT
                                InterfaceList.redrawActiveInterfaces();
                                Player.weightCarried = inboundBuffer.g2s();
                                InterfaceList.miscTransmitAt = InterfaceList.transmitTimer;
                                opcode = -1;
                                return true;
                            } else if (opcode == 71) {
                                username2 = inboundBuffer.g8();
                                local790 = Font.escape(Static65.method1497(inboundBuffer).encodeMessage());
                                Chat.addMessage(Base37.decode37(username2).toTitleCase(), 6, local790);
                                opcode = -1;
                                return true;
                            } else if (opcode == 42) {
                                if (GameShell.fullScreenFrame != null) {
                                    DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
                                }
                                @Pc(3848) byte[] local3848 = new byte[packetSize];
                                inboundBuffer.method2237(local3848, packetSize);
                                message2 = JString.decodeString(local3848, packetSize, 0);
                                if (GameShell.frame == null && (SignLink.anInt5928 == 3 || !SignLink.osName.startsWith("win") || client.haveIe6)) {
                                    Static169.openUrl(message2, true);
                                } else {
                                    ClientScriptRunner.url = message2;
                                    newTab = true;
                                    openUrlRequest = GameShell.signLink.openUrl(new String(message2.method3148(), "ISO-8859-1"));
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 111) {
                                int verifyID = inboundBuffer.g2sub();
                                xp = inboundBuffer.p4rme();
                                world = inboundBuffer.g2leadd();
                                slot = inboundBuffer.g2le();
                                count = inboundBuffer.g2leadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.updateComponentModel(world, 7, xp, slot << 16 | count);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 37) {
                                ii = inboundBuffer.g1add();
                                xp = inboundBuffer.g2le();
                                Static272.method3995(ii, xp);
                                opcode = -1;
                                return true;
                            } else if (opcode == 155) {
                                // IF_OPENSUB
                                int flags = inboundBuffer.g1();
                                int windowID = inboundBuffer.p4rme();
                                int verifyID = inboundBuffer.g2sub();
                                int interfaceID = inboundBuffer.g2();
                                if (setVerifyID(verifyID)) {
                                    ComponentPointer componentPointer = (ComponentPointer) InterfaceList.openInterfaces.getNode(windowID);
                                    if (componentPointer != null) {
                                        InterfaceList.closeInterface(interfaceID != componentPointer.interfaceId, componentPointer);
                                    }
                                    InterfaceList.openSubInterface(interfaceID, windowID, flags);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 131) {
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
                                opcode = -1;
                                return true;
                            } else if (opcode == 217) {
                                ii = inboundBuffer.g1();
                                @Pc(4084) MapMarker local4084 = new MapMarker();
                                xp = ii >> 6;
                                local4084.type = ii & 0x3F;
                                local4084.anInt4048 = inboundBuffer.g1();
                                if (local4084.anInt4048 >= 0 && local4084.anInt4048 < Sprites.aClass3_Sub2_Sub1Array11.length) {
                                    if (local4084.type == 1 || local4084.type == 10) {
                                        local4084.actorTargetId = inboundBuffer.g2();
                                        inboundBuffer.offset += 3;
                                    } else if (local4084.type >= 2 && local4084.type <= 6) {
                                        if (local4084.type == 2) {
                                            local4084.anInt4045 = 64;
                                            local4084.anInt4047 = 64;
                                        }
                                        if (local4084.type == 3) {
                                            local4084.anInt4045 = 0;
                                            local4084.anInt4047 = 64;
                                        }
                                        if (local4084.type == 4) {
                                            local4084.anInt4045 = 128;
                                            local4084.anInt4047 = 64;
                                        }
                                        if (local4084.type == 5) {
                                            local4084.anInt4045 = 64;
                                            local4084.anInt4047 = 0;
                                        }
                                        if (local4084.type == 6) {
                                            local4084.anInt4045 = 64;
                                            local4084.anInt4047 = 128;
                                        }
                                        local4084.type = 2;
                                        local4084.targetX = inboundBuffer.g2();
                                        local4084.anInt4046 = inboundBuffer.g2();
                                        local4084.anInt4050 = inboundBuffer.g1();
                                    }
                                    local4084.playerModelId = inboundBuffer.g2();
                                    if (local4084.playerModelId == 65535) {
                                        local4084.playerModelId = -1;
                                    }
                                    MiniMap.hintMapMarkers[xp] = local4084;
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 126) {
                                // UPDATE_IGNORELIST
                                IgnoreList.ignoreCount = packetSize / 8;
                                for (ii = 0; ii < IgnoreList.ignoreCount; ii++) {
                                    IgnoreList.encodedIgnores[ii] = inboundBuffer.g8();
                                    IgnoreList.ignoreNames[ii] = Base37.decode37(IgnoreList.encodedIgnores[ii]);
                                }
                                FriendList.transmitAt = InterfaceList.transmitTimer;
                                opcode = -1;
                                return true;
                            } else if (opcode == 32) {
                                method1800();
                                opcode = -1;
                                return true;
                            } else if (opcode == 119) {
                                int verifyID = inboundBuffer.g2sub();
                                xp = inboundBuffer.g4me();
                                world = inboundBuffer.g2s();
                                slot = inboundBuffer.g2sadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.method4666(world, xp, slot);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 235) {
                                ii = inboundBuffer.g1_alt3();
                                xp = ii >> 2;
                                world = ii & 0x3;
                                slot = Loc.LAYERS[xp];
                                count = inboundBuffer.g2();
                                i = inboundBuffer.g4();
                                if (count == 65535) {
                                    count = -1;
                                }
                                local908 = i & 0x3FFF;
                                j = i >> 14 & 0x3FFF;
                                j -= Camera.originX;
                                local908 -= Camera.originZ;
                                local1160 = i >> 28 & 0x3;
                                Static92.method1881(local1160, world, xp, local908, slot, j, count);
                                opcode = -1;
                                return true;
                            } else if (opcode == 0) {
                                username2 = inboundBuffer.g8();
                                username = inboundBuffer.g2();
                                local899 = inboundBuffer.g3();
                                local1160 = inboundBuffer.g1();
                                @Pc(4425) boolean local4425 = false;
                                @Pc(4431) long local4431 = local899 + (username << 32);
                                local3002 = 0;
                                label1450: while (true) {
                                    if (local3002 >= 100) {
                                        if (local1160 <= 1) {
                                            if (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat) {
                                                local4425 = true;
                                            } else {
                                                for (local3002 = 0; local3002 < IgnoreList.ignoreCount; local3002++) {
                                                    if (username2 == IgnoreList.encodedIgnores[local3002]) {
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
                                    @Pc(4518) JString local4518 = Font.escape(Static65.method1497(inboundBuffer).encodeMessage());
                                    if (local1160 == 2 || local1160 == 3) {
                                        Chat.addMessage(JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).toTitleCase() }), 7, local4518);
                                    } else if (local1160 == 1) {
                                        Chat.addMessage(JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).toTitleCase() }), 7, local4518);
                                    } else {
                                        Chat.addMessage(Base37.decode37(username2).toTitleCase(), 3, local4518);
                                    }
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 54) {
                                username2 = inboundBuffer.g8();
                                inboundBuffer.g1s();
                                username = inboundBuffer.g8();
                                local899 = inboundBuffer.g2();
                                local904 = inboundBuffer.g3();
                                @Pc(4626) long local4626 = (local899 << 32) + local904;
                                local908 = inboundBuffer.g1();
                                @Pc(4632) boolean local4632 = false;
                                @Pc(4634) int local4634 = 0;
                                label1575: while (true) {
                                    if (local4634 >= 100) {
                                        if (local908 <= 1) {
                                            if (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat) {
                                                local4632 = true;
                                            } else {
                                                for (local4634 = 0; local4634 < IgnoreList.ignoreCount; local4634++) {
                                                    if (IgnoreList.encodedIgnores[local4634] == username2) {
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
                                    local3038 = Font.escape(Static65.method1497(inboundBuffer).encodeMessage());
                                    if (local908 == 2 || local908 == 3) {
                                        Chat.method1598(local3038, JString.concatenate(new JString[] { Static44.aClass100_336, Base37.decode37(username2).toTitleCase() }), Base37.decode37(username).toTitleCase());
                                    } else if (local908 == 1) {
                                        Chat.method1598(local3038, JString.concatenate(new JString[] { Static65.aClass100_435, Base37.decode37(username2).toTitleCase() }), Base37.decode37(username).toTitleCase());
                                    } else {
                                        Chat.method1598(local3038, Base37.decode37(username2).toTitleCase(), Base37.decode37(username).toTitleCase());
                                    }
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 214) {
                                Static75.method1629(true);
                                opcode = -1;
                                return true;
                            } else if (opcode == 172) {
                                ii = inboundBuffer.g2();
                                xp = inboundBuffer.g1();
                                if (ii == 65535) {
                                    ii = -1;
                                }
                                world = inboundBuffer.g2();
                                Static26.method744(xp, ii, world);
                                opcode = -1;
                                return true;
                            } else if (opcode == 66) {
                                int verifyID = inboundBuffer.g2leadd();
                                xp = inboundBuffer.g4rme();
                                if (setVerifyID(verifyID)) {
                                    world = 0;
                                    if (PlayerList.self.appearance != null) {
                                        world = PlayerList.self.appearance.getHeadModelId();
                                    }
                                    DelayedStateChange.updateComponentModel(-1, 3, xp, world);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 171) {
                                ii = inboundBuffer.p4rme();
                                message2 = inboundBuffer.gjstr();
                                int verifyID = inboundBuffer.g2sub();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.method3617(message2, ii);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 84) {
                                ii = inboundBuffer.g4me();
                                xp = inboundBuffer.g2leadd();
                                Static272.method3995(ii, xp);
                                opcode = -1;
                                return true;
                            } else {
                                @Pc(4956) Component local4956;
                                if (opcode == 22) {
                                    ii = inboundBuffer.g4();
                                    xp = inboundBuffer.g2();
                                    if (ii < -70000) {
                                        xp += 32768;
                                    }
                                    if (ii < 0) {
                                        local4956 = null;
                                    } else {
                                        local4956 = InterfaceList.getComponent(ii);
                                    }
                                    while (inboundBuffer.offset < packetSize) {
                                        slot = inboundBuffer.gSmart1or2();
                                        count = inboundBuffer.g2();
                                        i = 0;
                                        if (count != 0) {
                                            i = inboundBuffer.g1();
                                            if (i == 255) {
                                                i = inboundBuffer.g4();
                                            }
                                        }
                                        if (local4956 != null && slot >= 0 && local4956.invSlotObjId.length > slot) {
                                            local4956.invSlotObjId[slot] = count;
                                            local4956.invSlotObjCount[slot] = i;
                                        }
                                        Inv.update(count - 1, slot, i, xp);
                                    }
                                    if (local4956 != null) {
                                        InterfaceList.redraw(local4956);
                                    }
                                    InterfaceList.redrawActiveInterfaces();
                                    Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & 0x1F] = xp & 0x7FFF;
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 24) {
                                    int verifyID = inboundBuffer.g2();
                                    if (setVerifyID(verifyID)) {
                                        Camera.resetCameraEffects();
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 86) {
                                    Game.processLogout();
                                    opcode = -1;
                                    return false;
                                } else if (opcode == 116) {
                                    ii = inboundBuffer.g1();
                                    if (inboundBuffer.g1() == 0) {
                                        StockMarketManager.offers[ii] = new StockMarketOffer();
                                    } else {
                                        inboundBuffer.offset--;
                                        StockMarketManager.offers[ii] = new StockMarketOffer(inboundBuffer);
                                    }
                                    opcode = -1;
                                    Static207.anInt4778 = InterfaceList.transmitTimer;
                                    return true;
                                } else if (opcode == 73) {
                                    ii = inboundBuffer.g2sub();
                                    xp = inboundBuffer.g4me();
                                    if (ii == 65535) {
                                        ii = -1;
                                    }
                                    int verifyID = inboundBuffer.g2le();
                                    if (setVerifyID(verifyID)) {
                                        DelayedStateChange.updateComponentModel(-1, 2, xp, ii);
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 162) {
                                    Static75.method1629(false);
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 165) {
                                    int verifyID = inboundBuffer.g2le();
                                    xp = inboundBuffer.g2le();
                                    if (xp == 65535) {
                                        xp = -1;
                                    }
                                    world = inboundBuffer.g4();
                                    slot = inboundBuffer.g2sub();
                                    count = inboundBuffer.g4rme();
                                    if (slot == 65535) {
                                        slot = -1;
                                    }
                                    if (setVerifyID(verifyID)) {
                                        for (i = slot; i <= xp; i++) {
                                            local904 = ((long) world << 32) + ((long) i);
                                            local1804 = (ServerActiveProperties) InterfaceList.properties.getNode(local904);
                                            if (local1804 != null) {
                                                local1814 = new ServerActiveProperties(count, local1804.targetParam);
                                                local1804.unlink();
                                            } else if (i == -1) {
                                                local1814 = new ServerActiveProperties(count, InterfaceList.getComponent(world).properties.targetParam);
                                            } else {
                                                local1814 = new ServerActiveProperties(count, -1);
                                            }
                                            InterfaceList.properties.put(local1814, local904);
                                        }
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 197) {
                                    FriendList.state = inboundBuffer.g1();
                                    FriendList.transmitAt = InterfaceList.transmitTimer;
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 196) {
                                    username2 = inboundBuffer.g8();
                                    world = inboundBuffer.g2();
                                    @Pc(5325) byte local5325 = inboundBuffer.g1s();
                                    ignored = false;
                                    if ((Long.MIN_VALUE & username2) != 0L) {
                                        ignored = true;
                                    }
                                    if (ignored) {
                                        if (ClanChat.size == 0) {
                                            opcode = -1;
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
                                        local506 = inboundBuffer.gjstr();
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
                                                opcode = -1;
                                                return true;
                                            }
                                            if (local908 < 0) {
                                                break;
                                            }
                                        }
                                        if (ClanChat.members.length <= ClanChat.size) {
                                            opcode = -1;
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
                                    opcode = -1;
                                    ClanChat.transmitAt = InterfaceList.transmitTimer;
                                    return true;
                                } else if (opcode == 50) {
                                    ii = inboundBuffer.g4();
                                    xp = inboundBuffer.p4rme();
                                    world = inboundBuffer.g2leadd();
                                    if (world == 65535) {
                                        world = -1;
                                    }
                                    int verifyID = inboundBuffer.g2le();
                                    if (setVerifyID(verifyID)) {
                                        @Pc(5603) Component com = InterfaceList.getComponent(xp);
                                        @Pc(5615) ObjType obj;
                                        if (com.if3) {
                                            DelayedStateChange.method3707(xp, ii, world);
                                            obj = ObjTypeList.get(world);
                                            DelayedStateChange.updateView(obj.zoom2d, xp, obj.yAngle2D, obj.xAngle2D);
                                            DelayedStateChange.method2745(xp, obj.zAngle2D, obj.yOffset2D, obj.xOffset2D);
                                        } else if (world == -1) {
                                            com.modelType = 0;
                                            opcode = -1;
                                            return true;
                                        } else {
                                            obj = ObjTypeList.get(world);
                                            com.modelXAngle = obj.xAngle2D;
                                            com.modelZoom = obj.zoom2d * 100 / ii;
                                            com.modelType = 4;
                                            com.modelId = world;
                                            com.modelYAngle = obj.yAngle2D;
                                            InterfaceList.redraw(com);
                                        }
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 105) {
                                    ii = inboundBuffer.g4();
                                    xp = inboundBuffer.g2();
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
                                    slot = inboundBuffer.g2();
                                    for (count = 0; count < slot; count++) {
                                        i = inboundBuffer.g1_alt3();
                                        if (i == 255) {
                                            i = inboundBuffer.g4();
                                        }
                                        local1160 = inboundBuffer.g2();
                                        if (local4956 != null && count < local4956.invSlotObjId.length) {
                                            local4956.invSlotObjId[count] = local1160;
                                            local4956.invSlotObjCount[count] = i;
                                        }
                                        Inv.update(local1160 - 1, count, i, xp);
                                    }
                                    if (local4956 != null) {
                                        InterfaceList.redraw(local4956);
                                    }
                                    InterfaceList.redrawActiveInterfaces();
                                    Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & 0x1F] = xp & 0x7FFF;
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 142) {
                                    Static230.method3954(inboundBuffer.gjstr());
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 26) {
                                    Static115.anInt2940 = inboundBuffer.p1neg();
                                    Static180.anInt4264 = inboundBuffer.g1();
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 4) {
                                    ii = inboundBuffer.g2leadd();
                                    if (ii == 65535) {
                                        ii = -1;
                                    }
                                    MusicPlayer.playSong(ii);
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 208) {
                                    ii = inboundBuffer.g3le();
                                    xp = inboundBuffer.g2le();
                                    if (xp == 65535) {
                                        xp = -1;
                                    }
                                    MusicPlayer.playJingle(ii, xp);
                                    opcode = -1;
                                    return true;
                                } else {
                                    TracingException.report("T1 - " + opcode + "," + opcode3 + "," + opcode4 + " - " + packetSize, null);
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

    @OriginalMember(owner = "client!uc", name = "a", descriptor = "(IB)Z")
    public static boolean setVerifyID(@OriginalArg(0) int verifyID) {
        verifyId = verifyID + 1 & 0xFFFF;
        verifyIdChanged = true;
        return true;
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(B)V")
    public static void method1202() {
        inboundBuffer.accessBits();
        @Pc(13) int local13 = inboundBuffer.gBit(8);
        @Pc(22) int local22;
        if (NpcList.npcCount > local13) {
            for (local22 = local13; local22 < NpcList.npcCount; local22++) {
                Static52.entityRemovalIds[Static240.entityRemovalCount++] = NpcList.npcIds[local22];
            }
        }
        if (NpcList.npcCount < local13) {
            throw new RuntimeException("gnpov1");
        }
        NpcList.npcCount = 0;
        for (local22 = 0; local22 < local13; local22++) {
            @Pc(61) int local61 = NpcList.npcIds[local22];
            @Pc(65) Npc local65 = NpcList.npcs[local61];
            @Pc(70) int local70 = inboundBuffer.gBit(1);
            if (local70 == 0) {
                NpcList.npcIds[NpcList.npcCount++] = local61;
                local65.cycle = client.loop;
            } else {
                @Pc(92) int local92 = inboundBuffer.gBit(2);
                if (local92 == 0) {
                    NpcList.npcIds[NpcList.npcCount++] = local61;
                    local65.cycle = client.loop;
                    Static44.entityUpdateIds[Static116.entityUpdateCount++] = local61;
                } else {
                    @Pc(139) int local139;
                    @Pc(149) int local149;
                    if (local92 == 1) {
                        NpcList.npcIds[NpcList.npcCount++] = local61;
                        local65.cycle = client.loop;
                        local139 = inboundBuffer.gBit(3);
                        local65.method2684(1, local139);
                        local149 = inboundBuffer.gBit(1);
                        if (local149 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local61;
                        }
                    } else if (local92 == 2) {
                        NpcList.npcIds[NpcList.npcCount++] = local61;
                        local65.cycle = client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            local139 = inboundBuffer.gBit(3);
                            local65.method2684(2, local139);
                            local149 = inboundBuffer.gBit(3);
                            local65.method2684(2, local149);
                        } else {
                            local139 = inboundBuffer.gBit(3);
                            local65.method2684(0, local139);
                        }
                        local139 = inboundBuffer.gBit(1);
                        if (local139 == 1) {
                            Static44.entityUpdateIds[Static116.entityUpdateCount++] = local61;
                        }
                    } else if (local92 == 3) {
                        Static52.entityRemovalIds[Static240.entityRemovalCount++] = local61;
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!gm", name = "h", descriptor = "(I)V")
    public static void method1800() {
        Static116.entityUpdateCount = 0;
        Static240.entityRemovalCount = 0;
        method1202();
        Static278.method4645();
        Static234.method4014();
        @Pc(19) int i;
        for (i = 0; i < Static240.entityRemovalCount; i++) {
            @Pc(30) int local30 = Static52.entityRemovalIds[i];
            if (NpcList.npcs[local30].cycle != client.loop) {
                if (NpcList.npcs[local30].type.hasAreaSound()) {
                    AreaSoundManager.remove(NpcList.npcs[local30]);
                }
                NpcList.npcs[local30].setNpcType(null);
                NpcList.npcs[local30] = null;
            }
        }
        if (packetSize != inboundBuffer.offset) {
            throw new RuntimeException("gnp1 pos:" + inboundBuffer.offset + " psize:" + packetSize);
        }
        for (i = 0; i < NpcList.npcCount; i++) {
            if (NpcList.npcs[NpcList.npcIds[i]] == null) {
                throw new RuntimeException("gnp2 pos:" + i + " size:" + NpcList.npcCount);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(IBI)V")
    public static void spawnGroundObject(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        @Pc(9) LinkedList local9 = SceneGraph.objStacks[Player.plane][arg1][arg0];
        if (local9 == null) {
            Static187.method3420(Player.plane, arg1, arg0);
            return;
        }
        @Pc(28) int local28 = -99999999;
        @Pc(30) ObjStackNode local30 = null;
        @Pc(35) ObjStackNode local35;
        for (local35 = (ObjStackNode) local9.head(); local35 != null; local35 = (ObjStackNode) local9.next()) {
            @Pc(44) ObjType local44 = ObjTypeList.get(local35.value.type);
            @Pc(47) int local47 = local44.cost;
            if (local44.stackable == 1) {
                local47 *= local35.value.amount + 1;
            }
            if (local28 < local47) {
                local28 = local47;
                local30 = local35;
            }
        }
        if (local30 == null) {
            Static187.method3420(Player.plane, arg1, arg0);
            return;
        }
        local9.addHead(local30);
        @Pc(89) ObjStack local89 = null;
        @Pc(91) ObjStack local91 = null;
        for (local35 = (ObjStackNode) local9.head(); local35 != null; local35 = (ObjStackNode) local9.next()) {
            @Pc(103) ObjStack local103 = local35.value;
            if (local103.type != local30.value.type) {
                if (local89 == null) {
                    local89 = local103;
                }
                if (local103.type != local89.type && local91 == null) {
                    local91 = local103;
                }
            }
        }
        @Pc(152) long local152 = (long) ((arg0 << 7) + arg1 + 1610612736);
        Static69.method1543(Player.plane, arg1, arg0, SceneGraph.getTileHeight(Player.plane, arg1 * 128 + 64, arg0 * 128 + 64), local30.value, local152, local89, local91);
    }
}
