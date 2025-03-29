package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.dash3d.entity.ProjAnimNode;
import com.jagex.runetek4.dash3d.entity.SpotAnimEntity;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.game.config.quickchatphrasetype.QuickChatPhraseType;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.util.MathUtils;
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
    @OriginalMember(owner = "runetek4.client!wb", name = "f", descriptor = "Lclient!wa;")
    public static final Packet CHAT_PACKET = new Packet(new byte[5000]);
    @OriginalMember(owner = "client!eb", name = "p", descriptor = "[I")
    public static final int[] removedIds = new int[1000];
    @OriginalMember(owner = "client!dh", name = "d", descriptor = "[I")
    public static final int[] extendedIds = new int[2048];
    @OriginalMember(owner = "runetek4.client!ta", name = "z", descriptor = "[I")
    public static final int[] PACKET_LENGTHS = new int[] { -1, 0, 8, 0, 2, 0, 0, 0, 0, 12, 0, 1, 0, 3, 7, 0, 15, 6, 0, 0, 4, 7, -2, -1, 2, 0, 2, 8, 0, 0, 0, 0, -2, 5, 0, 0, 8, 3, 6, 0, 0, 0, -1, 0, -1, 0, 0, 6, -2, 0, 12, 0, 0, 0, -1, -2, 10, 0, 0, 0, 3, 0, -1, 0, 0, 5, 6, 0, 0, 8, -1, -1, 0, 8, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 6, 2, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 5, 0, 0, -2, 0, 0, 0, 0, 0, 12, 2, 0, -2, -2, 20, 0, 0, 10, 0, 15, 0, -1, 0, 8, -2, 0, 0, 0, 8, 0, 12, 0, 0, 7, 0, 0, 0, 0, 0, -1, -1, 0, 4, 5, 0, 0, 0, 6, 0, 0, 0, 0, 8, 9, 0, 0, 0, 2, -1, 0, -2, 0, 4, 14, 0, 0, 0, 24, 0, -2, 5, 0, 0, 0, 10, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 2, 1, 0, 0, 2, -1, 1, 0, 0, 0, 0, 14, 0, 0, 0, 0, 10, 5, 0, 0, 0, 0, 0, -2, 0, 0, 9, 0, 0, 8, 0, 0, 0, 0, -2, 6, 0, 0, 0, -2, 0, 3, 0, 1, 7, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 3, 0, 0 };
    @OriginalMember(owner = "runetek4.client!tl", name = "f", descriptor = "Lclient!na;")
    public static final JString ASSIST = JString.parse(":assist:");
    @OriginalMember(owner = "runetek4.client!pl", name = "f", descriptor = "Lclient!na;")
    public static final JString TRADEREQ = JString.parse(":tradereq:");
    @OriginalMember(owner = "runetek4.client!lb", name = "s", descriptor = "Lclient!na;")
    public static final JString CLAN = JString.parse(":clan:");
    @OriginalMember(owner = "runetek4.client!ij", name = "a", descriptor = "Lclient!na;")
    public static final JString DUELFRIEND = JString.parse(":duelfriend:");
    @OriginalMember(owner = "runetek4.client!km", name = "Sc", descriptor = "Lclient!na;")
    public static final JString TRADE = JString.parse(":trade:");
    @OriginalMember(owner = "runetek4.client!pb", name = "x", descriptor = "[[[I")
    public static final int[][][] anIntArrayArrayArray18 = new int[4][13][13];
    @OriginalMember(owner = "client!fc", name = "f", descriptor = "Lclient!na;")
    public static final JString IMG0 = JString.parse("<img=0>");
    @OriginalMember(owner = "runetek4.client!wd", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_989 = JString.parse("cookieprefix");
    @OriginalMember(owner = "runetek4.client!u", name = "g", descriptor = "Lclient!na;")
    public static final JString aClass100_1029 = JString.parse("cookiehost");
    @OriginalMember(owner = "runetek4.client!lc", name = "m", descriptor = "Lclient!na;")
    public static final JString aClass100_667 = JString.parse("settings=");
    @OriginalMember(owner = "runetek4.client!wh", name = "n", descriptor = "Lclient!na;")
    public static final JString aClass100_1095 = JString.parse("; version=1; path=)4; domain=");
    @OriginalMember(owner = "runetek4.client!tm", name = "d", descriptor = "Lclient!na;")
    public static final JString aClass100_1018 = JString.parse("; Expires=Thu)1 01)2Jan)21970 00:00:00 GMT; Max)2Age=0");
    @OriginalMember(owner = "runetek4.client!vg", name = "h", descriptor = "Lclient!na;")
    public static final JString aClass100_1082 = JString.parse("; Expires=");
    @OriginalMember(owner = "runetek4.client!s", name = "g", descriptor = "Lclient!na;")
    public static final JString aClass100_821 = JString.parse("document)3cookie=(R");
    @OriginalMember(owner = "runetek4.client!sc", name = "f", descriptor = "Lclient!na;")
    public static final JString aClass100_946 = JString.parse("(R");
    @OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
    public static final int[] cameraModifierCycle = new int[5];
    @OriginalMember(owner = "runetek4.client!pg", name = "db", descriptor = "Lclient!na;")
    public static final JString ASSISTREQ = JString.parse(":assistreq:");
    @OriginalMember(owner = "runetek4.client!rj", name = "ab", descriptor = "Lclient!na;")
    public static final JString CLANREQ = JString.parse(":clanreq:");
    @OriginalMember(owner = "runetek4.client!na", name = "cb", descriptor = "Lclient!na;")
    public static final JString ALLYREQ = JString.parse(":allyreq:");
    @OriginalMember(owner = "client!dh", name = "i", descriptor = "Lclient!na;")
    public static final JString IMG1 = JString.parse("<img=1>");
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
    @OriginalMember(owner = "client!fl", name = "C", descriptor = "Lsignlink!im;")
    public static PrivilegedRequest socketRequest;
    @OriginalMember(owner = "runetek4.client!jb", name = "m", descriptor = "I")
    public static int extendedCount = 0;
    @OriginalMember(owner = "runetek4.client!tg", name = "h", descriptor = "I")
    public static int removedCount = 0;
    @OriginalMember(owner = "client!bj", name = "r", descriptor = "I")
    public static int cameraOffsetZModifier = 2;
    @OriginalMember(owner = "runetek4.client!ld", name = "i", descriptor = "I")
    public static int cameraOffsetCycle = 0;
    @OriginalMember(owner = "client!vl", name = "k", descriptor = "I")
    public static int idleTimeout = 0;
    @OriginalMember(owner = "client!bf", name = "G", descriptor = "I")
    public static int anInt551 = 0;
    @OriginalMember(owner = "runetek4.client!rm", name = "c", descriptor = "I")
    public static int cameraOffsetYawModifier = 1;

    @OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "(Z)V")
    public static void readPlayerInfo() {
        @Pc(6) int local6 = inboundBuffer.gBit(8);

        if (PlayerList.playerCount > local6) {
            for (int inxed = local6; inxed < PlayerList.playerCount; inxed++) {
                removedIds[removedCount++] = PlayerList.playerIds[inxed];
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
                local79.lastSeenLoop = client.loop;
            } else {
                @Pc(107) int local107 = inboundBuffer.gBit(2);
                if (local107 == 0) {
                    PlayerList.playerIds[PlayerList.playerCount++] = local75;
                    local79.lastSeenLoop = client.loop;
                    extendedIds[extendedCount++] = local75;
                } else {
                    @Pc(153) int local153;
                    @Pc(163) int local163;
                    if (local107 == 1) {
                        PlayerList.playerIds[PlayerList.playerCount++] = local75;
                        local79.lastSeenLoop = client.loop;
                        local153 = inboundBuffer.gBit(3);
                        local79.move(1, local153);
                        local163 = inboundBuffer.gBit(1);
                        if (local163 == 1) {
                            extendedIds[extendedCount++] = local75;
                        }
                    } else if (local107 == 2) {
                        PlayerList.playerIds[PlayerList.playerCount++] = local75;
                        local79.lastSeenLoop = client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            local153 = inboundBuffer.gBit(3);
                            local79.move(2, local153);
                            local163 = inboundBuffer.gBit(3);
                            local79.move(2, local163);
                        } else {
                            local153 = inboundBuffer.gBit(3);
                            local79.move(0, local153);
                        }
                        local153 = inboundBuffer.gBit(1);
                        if (local153 == 1) {
                            extendedIds[extendedCount++] = local75;
                        }
                    } else if (local107 == 3) {
                        removedIds[removedCount++] = local75;
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
            packetSize = PACKET_LENGTHS[opcode];
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
            VarpDomain.set(local137, ii);
            opcode = -1;
            return true;
        }
        @Pc(171) int slot;
        @Pc(156) JString argTypes;
        if (opcode == 115) {
            int tracknum = inboundBuffer.g2();
            argTypes = inboundBuffer.gjstr();
            @Pc(163) Object[] scriptArgs = new Object[argTypes.length() + 1];
            for (slot = argTypes.length() - 1; slot >= 0; slot--) {
                if (argTypes.charAt(slot) == 115) {
                    scriptArgs[slot + 1] = inboundBuffer.gjstr();
                } else {
                    scriptArgs[slot + 1] = Integer.valueOf(inboundBuffer.g4());
                }
            }
            scriptArgs[0] = Integer.valueOf(inboundBuffer.g4());
            if (setVerifyID(tracknum)) {
                @Pc(226) HookRequest request = new HookRequest();
                request.arguments = scriptArgs;
                ClientScriptRunner.run(request);
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
            if (message.endsWith(TRADEREQ)) {
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = argTypes.encode37();
                ignored = false;
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    Chat.addMessage(argTypes, 4, LocalizedText.TRADEREQ);
                }
            } else if (message.endsWith(CHALREQ)) {
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = argTypes.encode37();
                ignored = false;
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    local506 = message.substring(message.length() - 9, message.indexOf(JString.COLON_SIGN) + 1);
                    Chat.addMessage(argTypes, 8, local506);
                }
            } else if (message.endsWith(ASSISTREQ)) {
                ignored = false;
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = argTypes.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (username == IgnoreList.encodedIgnores[i]) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    Chat.addMessage(argTypes, 10, JString.EMPTY);
                }
            } else if (message.endsWith(CLAN)) {
                argTypes = message.substring(message.indexOf(CLAN), 0);
                Chat.addMessage(JString.EMPTY, 11, argTypes);
            } else if (message.endsWith(TRADE)) {
                argTypes = message.substring(message.indexOf(TRADE), 0);
                if (Player.inTutorialIsland == 0) {
                    Chat.addMessage(JString.EMPTY, 12, argTypes);
                }
            } else if (message.endsWith(ASSIST)) {
                argTypes = message.substring(message.indexOf(ASSIST), 0);
                if (Player.inTutorialIsland == 0) {
                    Chat.addMessage(JString.EMPTY, 13, argTypes);
                }
            } else if (message.endsWith(DUELSTAKE)) {
                ignored = false;
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = argTypes.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (username == IgnoreList.encodedIgnores[i]) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    Chat.addMessage(argTypes, 14, JString.EMPTY);
                }
            } else if (message.endsWith(DUELFRIEND)) {
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                ignored = false;
                username = argTypes.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    Chat.addMessage(argTypes, 15, JString.EMPTY);
                }
            } else if (message.endsWith(CLANREQ)) {
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                username = argTypes.encode37();
                ignored = false;
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (username == IgnoreList.encodedIgnores[i]) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    Chat.addMessage(argTypes, 16, JString.EMPTY);
                }
            } else if (message.endsWith(ALLYREQ)) {
                argTypes = message.substring(message.indexOf(JString.COLON_SIGN), 0);
                ignored = false;
                username = argTypes.encode37();
                for (i = 0; i < IgnoreList.ignoreCount; i++) {
                    if (IgnoreList.encodedIgnores[i] == username) {
                        ignored = true;
                        break;
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    local506 = message.substring(message.length() - 9, message.indexOf(JString.COLON_SIGN) + 1);
                    Chat.addMessage(argTypes, 21, local506);
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
            SceneGraph.currentChunkZ = inboundBuffer.g1add();
            SceneGraph.currentChunkX = inboundBuffer.g1_alt3();
            while (packetSize > inboundBuffer.offset) {
                opcode = inboundBuffer.g1();
                readZonePacket();
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
                if (!local910 && Player.inTutorialIsland == 0) {
                    Chat.recentMessages[Chat.messageCounter] = local922;
                    Chat.messageCounter = (Chat.messageCounter + 1) % 100;
                    @Pc(999) JString local999 = QuickChatPhraseTypeList.get(local916).decodeMessage(inboundBuffer);
                    if (local908 == 2 || local908 == 3) {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), JString.concatenate(new JString[] { IMG1, Base37.decode37(username2).toTitleCase() }));
                    } else if (local908 == 1) {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), JString.concatenate(new JString[] { IMG0, Base37.decode37(username2).toTitleCase() }));
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
                    if (Player.name37 == local1158[local1160].nodeId) {
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
                readPlayerInfoPacket();
                opcode = -1;
                return true;
            } else if (opcode == 48) {
                int verifyID = inboundBuffer.g2();
                argTypes = inboundBuffer.gjstr();
                world = inboundBuffer.g2leadd();
                if (setVerifyID(verifyID)) {
                    DelayedStateChange.method3498(argTypes, world);
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
                    VarpDomain.set(ii, xp);
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
                    local1409 = QuickChatPhraseTypeList.get(world).decodeMessage(inboundBuffer);
                    Chat.add(world, 19, local1409, null, Base37.decode37(username2).toTitleCase());
                    opcode = -1;
                    return true;
                } else if (opcode == 169) {
                    writeRandom(inboundBuffer);
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
                                    local1814 = new ServerActiveProperties(local1804.events, ii);
                                    local1804.unlink();
                                } else if (i == -1) {
                                    local1814 = new ServerActiveProperties(InterfaceList.getComponent(xp).properties.events, ii);
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
                                    if (slot != -1 && local1894.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(slot).animationId).priority < SeqTypeList.get(SpotAnimTypeList.get(local1894.spotAnimId).animationId).priority) {
                                        local1245 = false;
                                    }
                                    if (local1245) {
                                        local1894.anInt3361 = 0;
                                        local1894.spotAnimId = slot;
                                        local1894.spotAnimStart = client.loop + ii;
                                        local1894.spotanimId = 0;
                                        if (local1894.spotAnimStart > client.loop) {
                                            local1894.spotanimId = -1;
                                        }
                                        local1894.spotAnimY = xp;
                                        local1894.anInt3418 = 1;
                                        if (local1894.spotAnimId != -1 && client.loop == local1894.spotAnimStart) {
                                            j = SpotAnimTypeList.get(local1894.spotAnimId).animationId;
                                            if (j != -1) {
                                                local1994 = SeqTypeList.get(j);
                                                if (local1994 != null && local1994.frames != null) {
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
                                    if (slot != -1 && local2033.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(slot).animationId).priority < SeqTypeList.get(SpotAnimTypeList.get(local2033.spotAnimId).animationId).priority) {
                                        local1245 = false;
                                    }
                                    if (local1245) {
                                        local2033.spotAnimStart = ii + client.loop;
                                        local2033.spotAnimY = xp;
                                        local2033.spotAnimId = slot;
                                        if (local2033.spotAnimId == 65535) {
                                            local2033.spotAnimId = -1;
                                        }
                                        local2033.anInt3418 = 1;
                                        local2033.anInt3361 = 0;
                                        local2033.spotanimId = 0;
                                        if (local2033.spotAnimStart > client.loop) {
                                            local2033.spotanimId = -1;
                                        }
                                        if (local2033.spotAnimId != -1 && local2033.spotAnimStart == client.loop) {
                                            j = SpotAnimTypeList.get(local2033.spotAnimId).animationId;
                                            if (j != -1) {
                                                local1994 = SeqTypeList.get(j);
                                                if (local1994 != null && local1994.frames != null) {
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
                        PlayerSkillXpTable.experience[world] = xp;
                        PlayerSkillXpTable.boostedLevels[world] = ii;
                        PlayerSkillXpTable.baseLevels[world] = 1;
                        for (slot = 0; slot < 98; slot++) {
                            if (PlayerSkillXpTable.xpLevelLookup[slot] <= xp) {
                                PlayerSkillXpTable.baseLevels[world] = slot + 2;
                            }
                        }
                        PlayerSkillXpTable.updatedStats[PlayerSkillXpTable.updatedStatsWriterIndex++ & 0x1F] = world;
                        opcode = -1;
                        return true;
                    } else if (opcode == 104 || opcode == 121 || opcode == 97 || opcode == 14 || opcode == 202 || opcode == 135 || opcode == 17 || opcode == 16 || opcode == 240 || opcode == 33 || opcode == 20 || opcode == 195 || opcode == 179) {
                        readZonePacket();
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
                        SceneGraph.currentChunkX = inboundBuffer.g1();
                        SceneGraph.currentChunkZ = inboundBuffer.p1neg();
                        for (ii = SceneGraph.currentChunkX; ii < SceneGraph.currentChunkX + 8; ii++) {
                            for (xp = SceneGraph.currentChunkZ; xp < SceneGraph.currentChunkZ + 8; xp++) {
                                if (SceneGraph.objStacks[Player.plane][ii][xp] != null) {
                                    SceneGraph.objStacks[Player.plane][ii][xp] = null;
                                    spawnGroundObject(xp, ii);
                                }
                            }
                        }
                        for (@Pc(2604) ChangeLocRequest local2604 = (ChangeLocRequest) ChangeLocRequest.queue.head(); local2604 != null; local2604 = (ChangeLocRequest) ChangeLocRequest.queue.next()) {
                            if (local2604.x >= SceneGraph.currentChunkX && SceneGraph.currentChunkX + 8 > local2604.x && local2604.z >= SceneGraph.currentChunkZ && local2604.z < SceneGraph.currentChunkZ + 8 && local2604.level == Player.plane) {
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
                                    if (FriendList.friendWorlds[local916] != Player.worldId && Player.worldId == FriendList.friendWorlds[local916 + 1] || FriendList.friendWorlds[local916] == 0 && FriendList.friendWorlds[local916 + 1] != 0) {
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
                            if (!local3263 && Player.inTutorialIsland == 0) {
                                Chat.recentMessages[Chat.messageCounter] = local3270;
                                Chat.messageCounter = (Chat.messageCounter + 1) % 100;
                                local3020 = QuickChatPhraseTypeList.get(j).decodeMessage(inboundBuffer);
                                if (local1160 == 2) {
                                    Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { IMG1, Base37.decode37(username2).toTitleCase() }));
                                } else if (local1160 == 1) {
                                    Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { IMG0, Base37.decode37(username2).toTitleCase() }));
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
                                    cameraModifierCycle[xp] = i;
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
                                ReflectionCheck.push(GameShell.signLink, inboundBuffer, packetSize);
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
                                Player.runEnergy = inboundBuffer.g1();
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
                                    animateNpc(xp, world, local3766);
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
                                local790 = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                Chat.addMessage(Base37.decode37(username2).toTitleCase(), 6, local790);
                                opcode = -1;
                                return true;
                            } else if (opcode == 42) {
                                if (GameShell.fullScreenFrame != null) {
                                    DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
                                }
                                @Pc(3848) byte[] local3848 = new byte[packetSize];
                                inboundBuffer.method2237(local3848, packetSize);
                                argTypes = JString.decodeString(local3848, packetSize, 0);
                                if (GameShell.frame == null && (SignLink.anInt5928 == 3 || !SignLink.osName.startsWith("win") || client.haveIe6)) {
                                    ClientScriptRunner.openUrl(argTypes, true);
                                } else {
                                    ClientScriptRunner.url = argTypes;
                                    newTab = true;
                                    openUrlRequest = GameShell.signLink.openUrl(new String(argTypes.method3148(), "ISO-8859-1"));
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
                                    openSubInterface(interfaceID, windowID, flags);
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
                                readNpcPacket();
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
                                SceneGraph.method1881(local1160, world, xp, local908, slot, j, count);
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
                                if (!local4425 && Player.inTutorialIsland == 0) {
                                    Chat.recentMessages[Chat.messageCounter] = local4431;
                                    Chat.messageCounter = (Chat.messageCounter + 1) % 100;
                                    @Pc(4518) JString local4518 = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                    if (local1160 == 2 || local1160 == 3) {
                                        Chat.addMessage(JString.concatenate(new JString[] { IMG1, Base37.decode37(username2).toTitleCase() }), 7, local4518);
                                    } else if (local1160 == 1) {
                                        Chat.addMessage(JString.concatenate(new JString[] { IMG0, Base37.decode37(username2).toTitleCase() }), 7, local4518);
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
                                if (!local4632 && Player.inTutorialIsland == 0) {
                                    Chat.recentMessages[Chat.messageCounter] = local4626;
                                    Chat.messageCounter = (Chat.messageCounter + 1) % 100;
                                    local3038 = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                    if (local908 == 2 || local908 == 3) {
                                        Chat.method1598(local3038, JString.concatenate(new JString[] { IMG1, Base37.decode37(username2).toTitleCase() }), Base37.decode37(username).toTitleCase());
                                    } else if (local908 == 1) {
                                        Chat.method1598(local3038, JString.concatenate(new JString[] { IMG0, Base37.decode37(username2).toTitleCase() }), Base37.decode37(username).toTitleCase());
                                    } else {
                                        Chat.method1598(local3038, Base37.decode37(username2).toTitleCase(), Base37.decode37(username).toTitleCase());
                                    }
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 214) {
                                readRebuildPacket(true);
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
                                argTypes = inboundBuffer.gjstr();
                                int verifyID = inboundBuffer.g2sub();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.method3617(argTypes, ii);
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
                                    readRebuildPacket(false);
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
                                                if (username2 == Player.name37) {
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
                                        if (Player.name37 == username2) {
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
                                    method3954(inboundBuffer.gjstr());
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 26) {
                                    SceneGraph.currentChunkX = inboundBuffer.p1neg();
                                    SceneGraph.currentChunkZ = inboundBuffer.g1();
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
    public static void clearAreaNPCs() {
        inboundBuffer.accessBits();
        @Pc(13) int npcsInArea = inboundBuffer.gBit(8);
        @Pc(22) int i;
        if (NpcList.npcCount > npcsInArea) {
            for (i = npcsInArea; i < NpcList.npcCount; i++) {
                removedIds[removedCount++] = NpcList.npcIds[i];
            }
        }
        if (NpcList.npcCount < npcsInArea) {
            throw new RuntimeException("gnpov1");
        }
        NpcList.npcCount = 0;
        for (i = 0; i < npcsInArea; i++) {
            @Pc(61) int id = NpcList.npcIds[i];
            @Pc(65) Npc npc = NpcList.npcs[id];
            @Pc(70) int local70 = inboundBuffer.gBit(1);
            if (local70 == 0) {
                NpcList.npcIds[NpcList.npcCount++] = id;
                npc.lastSeenLoop = client.loop;
            } else {
                @Pc(92) int local92 = inboundBuffer.gBit(2);
                if (local92 == 0) {
                    NpcList.npcIds[NpcList.npcCount++] = id;
                    npc.lastSeenLoop = client.loop;
                    extendedIds[extendedCount++] = id;
                } else {
                    @Pc(139) int local139;
                    @Pc(149) int local149;
                    if (local92 == 1) {
                        NpcList.npcIds[NpcList.npcCount++] = id;
                        npc.lastSeenLoop = client.loop;
                        local139 = inboundBuffer.gBit(3);
                        npc.move(1, local139);
                        local149 = inboundBuffer.gBit(1);
                        if (local149 == 1) {
                            extendedIds[extendedCount++] = id;
                        }
                    } else if (local92 == 2) {
                        NpcList.npcIds[NpcList.npcCount++] = id;
                        npc.lastSeenLoop = client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            local139 = inboundBuffer.gBit(3);
                            npc.move(2, local139);
                            local149 = inboundBuffer.gBit(3);
                            npc.move(2, local149);
                        } else {
                            local139 = inboundBuffer.gBit(3);
                            npc.move(0, local139);
                        }
                        local139 = inboundBuffer.gBit(1);
                        if (local139 == 1) {
                            extendedIds[extendedCount++] = id;
                        }
                    } else if (local92 == 3) {
                        removedIds[removedCount++] = id;
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!gm", name = "h", descriptor = "(I)V")
    public static void readNpcPacket() {
        extendedCount = 0;
        removedCount = 0;
        clearAreaNPCs();
        loadAreaNPCs();
        npcCombat();
        @Pc(19) int i;
        for (i = 0; i < removedCount; i++) {
            @Pc(30) int removedId = removedIds[i];
            if (NpcList.npcs[removedId].lastSeenLoop != client.loop) {
                if (NpcList.npcs[removedId].type.hasAreaSound()) {
                    AreaSoundManager.remove(NpcList.npcs[removedId]);
                }
                NpcList.npcs[removedId].setNpcType(null);
                NpcList.npcs[removedId] = null;
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
            SceneGraph.removeObjStack(Player.plane, arg1, arg0);
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
            SceneGraph.removeObjStack(Player.plane, arg1, arg0);
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
        SceneGraph.setObjStack(Player.plane, arg1, arg0, SceneGraph.getTileHeight(Player.plane, arg1 * 128 + 64, arg0 * 128 + 64), local30.value, local152, local89, local91);
    }

    @OriginalMember(owner = "client!g", name = "b", descriptor = "(B)V")
    public static void readZonePacket() {
        @Pc(15) int local15;
        @Pc(23) int local23;
        @Pc(19) int local19;
        @Pc(27) int local27;
        @Pc(31) int local31;
        @Pc(39) int local39;
        @Pc(45) int local45;
        if (opcode == 195) {
            local15 = inboundBuffer.p1neg();
            local19 = local15 & 0x3;
            local23 = local15 >> 2;
            local27 = Loc.LAYERS[local23];
            local31 = inboundBuffer.g1();
            local39 = (local31 >> 4 & 0x7) + SceneGraph.currentChunkX;
            local45 = (local31 & 0x7) + SceneGraph.currentChunkZ;
            if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
                ChangeLocRequest.push(Player.plane, local45, local19, local39, -1, -1, local27, local23, 0);
            }
        } else if (opcode == 33) {
            local15 = inboundBuffer.g2le();
            local23 = inboundBuffer.g1();
            local27 = (local23 & 0x7) + SceneGraph.currentChunkZ;
            local19 = (local23 >> 4 & 0x7) + SceneGraph.currentChunkX;
            local31 = inboundBuffer.g2sub();
            if (local19 >= 0 && local27 >= 0 && local19 < 104 && local27 < 104) {
                @Pc(122) ObjStack local122 = new ObjStack();
                local122.amount = local31;
                local122.type = local15;
                if (SceneGraph.objStacks[Player.plane][local19][local27] == null) {
                    SceneGraph.objStacks[Player.plane][local19][local27] = new LinkedList();
                }
                SceneGraph.objStacks[Player.plane][local19][local27].addTail(new ObjStackNode(local122));
                spawnGroundObject(local27, local19);
            }
        } else {
            @Pc(218) int local218;
            @Pc(228) int local228;
            @Pc(232) int local232;
            @Pc(247) int local247;
            @Pc(224) int local224;
            @Pc(236) int local236;
            @Pc(317) ProjectileAnimation local317;
            if (opcode == 121) {
                local15 = inboundBuffer.g1();
                local23 = SceneGraph.currentChunkX * 2 + (local15 >> 4 & 0xF);
                local19 = (local15 & 0xF) + SceneGraph.currentChunkZ * 2;
                local27 = local23 + inboundBuffer.g1s();
                local31 = inboundBuffer.g1s() + local19;
                local39 = inboundBuffer.g2s();
                local45 = inboundBuffer.g2();
                local218 = inboundBuffer.g1() * 4;
                local224 = inboundBuffer.g1() * 4;
                local228 = inboundBuffer.g2();
                local232 = inboundBuffer.g2();
                local236 = inboundBuffer.g1();
                if (local236 == 255) {
                    local236 = -1;
                }
                local247 = inboundBuffer.g1();
                if (local23 >= 0 && local19 >= 0 && local23 < 208 && local19 < 208 && local27 >= 0 && local31 >= 0 && local27 < 208 && local31 < 208 && local45 != 65535) {
                    local31 *= 64;
                    local27 = local27 * 64;
                    local19 = local19 * 64;
                    local23 = local23 * 64;
                    local317 = new ProjectileAnimation(local45, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local218, client.loop + local228, local232 + client.loop, local236, local247, local39, local224);
                    local317.setTarget(local31, client.loop + local228, -local224 + SceneGraph.getTileHeight(Player.plane, local27, local31), local27);
                    SceneGraph.projectiles.addTail(new ProjAnimNode(local317));
                }
            } else if (opcode == 17) {
                local15 = inboundBuffer.g1();
                local23 = SceneGraph.currentChunkX + (local15 >> 4 & 0x7);
                local19 = SceneGraph.currentChunkZ + (local15 & 0x7);
                local27 = inboundBuffer.g2();
                local31 = inboundBuffer.g1();
                local39 = inboundBuffer.g2();
                if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
                    local23 = local23 * 128 + 64;
                    local19 = local19 * 128 + 64;
                    @Pc(427) SpotAnim local427 = new SpotAnim(local27, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local31, local39, client.loop);
                    SceneGraph.spotanims.addTail(new SpotAnimEntity(local427));
                }
            } else if (opcode == 179) {
                local15 = inboundBuffer.g1add();
                local23 = local15 >> 2;
                local19 = local15 & 0x3;
                local27 = Loc.LAYERS[local23];
                local31 = inboundBuffer.g1();
                local39 = SceneGraph.currentChunkX + (local31 >> 4 & 0x7);
                local45 = (local31 & 0x7) + SceneGraph.currentChunkZ;
                local218 = inboundBuffer.g2sub();
                if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
                    ChangeLocRequest.push(Player.plane, local45, local19, local39, -1, local218, local27, local23, 0);
                }
            } else if (opcode == 20) {
                local15 = inboundBuffer.g1_alt3();
                local23 = (local15 >> 4 & 0x7) + SceneGraph.currentChunkX;
                local19 = SceneGraph.currentChunkZ + (local15 & 0x7);
                local27 = inboundBuffer.g1_alt3();
                local31 = local27 >> 2;
                local39 = local27 & 0x3;
                local45 = Loc.LAYERS[local31];
                local218 = inboundBuffer.g2le();
                if (local218 == 65535) {
                    local218 = -1;
                }
                SceneGraph.method1881(Player.plane, local39, local31, local19, local45, local23, local218);
            } else {
                @Pc(633) int local633;
                if (opcode == 202) {
                    local15 = inboundBuffer.g1();
                    local23 = local15 >> 2;
                    local19 = local15 & 0x3;
                    local27 = inboundBuffer.g1();
                    local31 = (local27 >> 4 & 0x7) + SceneGraph.currentChunkX;
                    local39 = (local27 & 0x7) + SceneGraph.currentChunkZ;
                    @Pc(605) byte local605 = inboundBuffer.p1_alt3();
                    @Pc(609) byte local609 = inboundBuffer.p1_alt3();
                    @Pc(613) byte local613 = inboundBuffer.g1sub();
                    local228 = inboundBuffer.g2sub();
                    local232 = inboundBuffer.g2le();
                    @Pc(625) byte local625 = inboundBuffer.g1s();
                    local247 = inboundBuffer.g2();
                    local633 = inboundBuffer.g2lesadd();
                    if (!GlRenderer.enabled) {
                        AttachLocRequest.push(local625, local247, local633, local232, local39, local613, local19, local605, local31, local23, local609, local228);
                    }
                }
                if (opcode == 14) {
                    local15 = inboundBuffer.g1();
                    local19 = SceneGraph.currentChunkZ + (local15 & 0x7);
                    local23 = (local15 >> 4 & 0x7) + SceneGraph.currentChunkX;
                    local27 = inboundBuffer.g2();
                    local31 = inboundBuffer.g2();
                    local39 = inboundBuffer.g2();
                    if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
                        @Pc(710) LinkedList local710 = SceneGraph.objStacks[Player.plane][local23][local19];
                        if (local710 != null) {
                            for (@Pc(718) ObjStackNode local718 = (ObjStackNode) local710.head(); local718 != null; local718 = (ObjStackNode) local710.next()) {
                                @Pc(723) ObjStack local723 = local718.value;
                                if ((local27 & 0x7FFF) == local723.type && local31 == local723.amount) {
                                    local723.amount = local39;
                                    break;
                                }
                            }
                            spawnGroundObject(local19, local23);
                        }
                    }
                } else if (opcode == 135) {
                    local15 = inboundBuffer.g2leadd();
                    local23 = inboundBuffer.p1neg();
                    local27 = SceneGraph.currentChunkZ + (local23 & 0x7);
                    local19 = (local23 >> 4 & 0x7) + SceneGraph.currentChunkX;
                    local31 = inboundBuffer.g2le();
                    local39 = inboundBuffer.g2le();
                    if (local19 >= 0 && local27 >= 0 && local19 < 104 && local27 < 104 && PlayerList.selfId != local15) {
                        @Pc(812) ObjStack local812 = new ObjStack();
                        local812.amount = local31;
                        local812.type = local39;
                        if (SceneGraph.objStacks[Player.plane][local19][local27] == null) {
                            SceneGraph.objStacks[Player.plane][local19][local27] = new LinkedList();
                        }
                        SceneGraph.objStacks[Player.plane][local19][local27].addTail(new ObjStackNode(local812));
                        spawnGroundObject(local27, local19);
                    }
                } else if (opcode == 16) {
                    local15 = inboundBuffer.g1();
                    local23 = SceneGraph.currentChunkX + (local15 >> 4 & 0x7);
                    local19 = (local15 & 0x7) + SceneGraph.currentChunkZ;
                    local27 = local23 + inboundBuffer.g1s();
                    local31 = inboundBuffer.g1s() + local19;
                    local39 = inboundBuffer.g2s();
                    local45 = inboundBuffer.g2();
                    local218 = inboundBuffer.g1() * 4;
                    local224 = inboundBuffer.g1() * 4;
                    local228 = inboundBuffer.g2();
                    local232 = inboundBuffer.g2();
                    local236 = inboundBuffer.g1();
                    local247 = inboundBuffer.g1();
                    if (local236 == 255) {
                        local236 = -1;
                    }
                    if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104 && local27 >= 0 && local31 >= 0 && local27 < 104 && local31 < 104 && local45 != 65535) {
                        local31 = local31 * 128 + 64;
                        local19 = local19 * 128 + 64;
                        local23 = local23 * 128 + 64;
                        local27 = local27 * 128 + 64;
                        local317 = new ProjectileAnimation(local45, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local218, local228 + client.loop, local232 + client.loop, local236, local247, local39, local224);
                        local317.setTarget(local31, client.loop + local228, SceneGraph.getTileHeight(Player.plane, local27, local31) - local224, local27);
                        SceneGraph.projectiles.addTail(new ProjAnimNode(local317));
                    }
                } else if (opcode == 104) {
                    local15 = inboundBuffer.g1();
                    local19 = SceneGraph.currentChunkZ * 2 + (local15 & 0xF);
                    local23 = SceneGraph.currentChunkX * 2 + (local15 >> 4 & 0xF);
                    local27 = inboundBuffer.g1s() + local23;
                    local31 = inboundBuffer.g1s() + local19;
                    local39 = inboundBuffer.g2s();
                    local45 = inboundBuffer.g2s();
                    local218 = inboundBuffer.g2();
                    local224 = inboundBuffer.g1s();
                    local228 = inboundBuffer.g1() * 4;
                    local232 = inboundBuffer.g2();
                    local236 = inboundBuffer.g2();
                    local247 = inboundBuffer.g1();
                    local633 = inboundBuffer.g1();
                    if (local247 == 255) {
                        local247 = -1;
                    }
                    if (local23 >= 0 && local19 >= 0 && local23 < 208 && local19 < 208 && local27 >= 0 && local31 >= 0 && local27 < 208 && local31 < 208 && local218 != 65535) {
                        local27 = local27 * 64;
                        local23 *= 64;
                        local31 *= 64;
                        local19 *= 64;
                        if (local39 != 0) {
                            @Pc(1194) int local1194;
                            @Pc(1198) PathingEntity local1198;
                            @Pc(1184) int local1184;
                            @Pc(1188) int local1188;
                            if (local39 >= 0) {
                                local1184 = local39 - 1;
                                local1188 = local1184 & 0x7FF;
                                local1194 = local1184 >> 11 & 0xF;
                                local1198 = NpcList.npcs[local1188];
                            } else {
                                local1184 = -local39 - 1;
                                local1194 = local1184 >> 11 & 0xF;
                                local1188 = local1184 & 0x7FF;
                                if (PlayerList.selfId == local1188) {
                                    local1198 = PlayerList.self;
                                } else {
                                    local1198 = PlayerList.players[local1188];
                                }
                            }
                            if (local1198 != null) {
                                @Pc(1232) BasType local1232 = local1198.getBasType();
                                if (local1232.modelRotateTranslate != null && local1232.modelRotateTranslate[local1194] != null) {
                                    local1188 = local1232.modelRotateTranslate[local1194][0];
                                    local224 -= local1232.modelRotateTranslate[local1194][1];
                                    @Pc(1264) int local1264 = local1232.modelRotateTranslate[local1194][2];
                                    @Pc(1269) int local1269 = MathUtils.sin[local1198.anInt3381];
                                    @Pc(1274) int local1274 = MathUtils.cos[local1198.anInt3381];
                                    @Pc(1284) int local1284 = local1188 * local1274 + local1264 * local1269 >> 16;
                                    @Pc(1295) int local1295 = local1274 * local1264 - local1188 * local1269 >> 16;
                                    local19 += local1295;
                                    local23 += local1284;
                                }
                            }
                        }
                        @Pc(1331) ProjectileAnimation local1331 = new ProjectileAnimation(local218, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local224, local232 + client.loop, local236 + client.loop, local247, local633, local45, local228);
                        local1331.setTarget(local31, local232 + client.loop, -local228 + SceneGraph.getTileHeight(Player.plane, local27, local31), local27);
                        SceneGraph.projectiles.addTail(new ProjAnimNode(local1331));
                    }
                } else if (opcode == 97) {
                    local15 = inboundBuffer.g1();
                    local23 = SceneGraph.currentChunkX + (local15 >> 4 & 0x7);
                    local19 = SceneGraph.currentChunkZ + (local15 & 0x7);
                    local27 = inboundBuffer.g2();
                    if (local27 == 65535) {
                        local27 = -1;
                    }
                    local31 = inboundBuffer.g1();
                    local39 = local31 >> 4 & 0xF;
                    local218 = inboundBuffer.g1();
                    local45 = local31 & 0x7;
                    if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
                        local224 = local39 + 1;
                        if (PlayerList.self.movementQueueX[0] >= local23 - local224 && local224 + local23 >= PlayerList.self.movementQueueX[0] && PlayerList.self.movementQueueZ[0] >= local19 - local224 && PlayerList.self.movementQueueZ[0] <= local224 + local19 && Preferences.ambientSoundsVolume != 0 && local45 > 0 && SoundPlayer.size < 50 && local27 != -1) {
                            SoundPlayer.ids[SoundPlayer.size] = local27;
                            SoundPlayer.loops[SoundPlayer.size] = local45;
                            SoundPlayer.delays[SoundPlayer.size] = local218;
                            SoundPlayer.sounds[SoundPlayer.size] = null;
                            SoundPlayer.positions[SoundPlayer.size] = local39 + (local23 << 16) + (local19 << 8);
                            SoundPlayer.size++;
                        }
                    }
                } else if (opcode == 240) {
                    local15 = inboundBuffer.g1_alt3();
                    local19 = SceneGraph.currentChunkZ + (local15 & 0x7);
                    local23 = (local15 >> 4 & 0x7) + SceneGraph.currentChunkX;
                    local27 = inboundBuffer.g2();
                    if (local23 >= 0 && local19 >= 0 && local23 < 104 && local19 < 104) {
                        @Pc(1565) LinkedList local1565 = SceneGraph.objStacks[Player.plane][local23][local19];
                        if (local1565 != null) {
                            for (@Pc(1572) ObjStackNode local1572 = (ObjStackNode) local1565.head(); local1572 != null; local1572 = (ObjStackNode) local1565.next()) {
                                if (local1572.value.type == (local27 & 0x7FFF)) {
                                    local1572.unlink();
                                    break;
                                }
                            }
                            if (local1565.head() == null) {
                                SceneGraph.objStacks[Player.plane][local23][local19] = null;
                            }
                            spawnGroundObject(local19, local23);
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!g", name = "a", descriptor = "(IZ)V")
    public static void readRebuildPacket(@OriginalArg(1) boolean arg0) {
        SceneGraph.dynamicMapRegion = arg0;
        @Pc(13) int local13;
        @Pc(20) int local20;
        @Pc(26) int local26;
        @Pc(31) int local31;
        @Pc(60) int local60;
        @Pc(64) int local64;
        @Pc(138) int local138;
        @Pc(151) int local151;
        @Pc(169) int local169;
        if (!SceneGraph.dynamicMapRegion) {
            local13 = inboundBuffer.g2sub();
            local20 = (packetSize - inboundBuffer.offset) / 16;
            LoginManager.regionsXteaKeys = new int[local20][4];
            for (local26 = 0; local26 < local20; local26++) {
                for (local31 = 0; local31 < 4; local31++) {
                    LoginManager.regionsXteaKeys[local26][local31] = inboundBuffer.p4rme();
                }
            }
            local26 = inboundBuffer.g1_alt3();
            local31 = inboundBuffer.g2();
            local60 = inboundBuffer.g2sub();
            local64 = inboundBuffer.g2sub();
            LoginManager.regionBitPacked = new int[local20];
            LoginManager.mapFilesBuffer = new byte[local20][];
            LoginManager.npcSpawnsFilesBuffer = null;
            LoginManager.underWaterMapFileIds = new int[local20];
            LoginManager.locationMapFilesBuffer = new byte[local20][];
            LoginManager.underWaterLocationsMapFilesBuffer = new byte[local20][];
            LoginManager.npcSpawnsFileIds = null;
            LoginManager.mapFileIds = new int[local20];
            LoginManager.underWaterMapFilesBuffer = new byte[local20][];
            LoginManager.locationsMapFileIds = new int[local20];
            LoginManager.underWaterLocationsMapFileIds = new int[local20];
            local20 = 0;
            @Pc(100) boolean local100 = false;
            if ((local31 / 8 == 48 || local31 / 8 == 49) && local60 / 8 == 48) {
                local100 = true;
            }
            if (local31 / 8 == 48 && local60 / 8 == 148) {
                local100 = true;
            }
            for (local138 = (local31 - 6) / 8; local138 <= (local31 + 6) / 8; local138++) {
                for (local151 = (local60 - 6) / 8; local151 <= (local60 + 6) / 8; local151++) {
                    local169 = (local138 << 8) + local151;
                    if (local100 && (local151 == 49 || local151 == 149 || local151 == 147 || local138 == 50 || local138 == 49 && local151 == 47)) {
                        LoginManager.regionBitPacked[local20] = local169;
                        LoginManager.mapFileIds[local20] = -1;
                        LoginManager.locationsMapFileIds[local20] = -1;
                        LoginManager.underWaterMapFileIds[local20] = -1;
                        LoginManager.underWaterLocationsMapFileIds[local20] = -1;
                    } else {
                        LoginManager.regionBitPacked[local20] = local169;
                        LoginManager.mapFileIds[local20] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_558, JString.parseInt(local138), LoginManager.UNDERSCORE, JString.parseInt(local151) }));
                        LoginManager.locationsMapFileIds[local20] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_1090, JString.parseInt(local138), LoginManager.UNDERSCORE, JString.parseInt(local151) }));
                        LoginManager.underWaterMapFileIds[local20] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_772, JString.parseInt(local138), LoginManager.UNDERSCORE, JString.parseInt(local151) }));
                        LoginManager.underWaterLocationsMapFileIds[local20] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_1103, JString.parseInt(local138), LoginManager.UNDERSCORE, JString.parseInt(local151) }));
                    }
                    local20++;
                }
            }
            LoginManager.method2463(local26, local60, local31, local64, false, local13);
            return;
        }
        local13 = inboundBuffer.g2leadd();
        local20 = inboundBuffer.g2leadd();
        local26 = inboundBuffer.g1_alt3();
        local31 = inboundBuffer.g2leadd();
        inboundBuffer.accessBits();
        @Pc(391) int local391;
        for (local60 = 0; local60 < 4; local60++) {
            for (local64 = 0; local64 < 13; local64++) {
                for (local391 = 0; local391 < 13; local391++) {
                    local138 = inboundBuffer.gBit(1);
                    if (local138 == 1) {
                        anIntArrayArrayArray18[local60][local64][local391] = inboundBuffer.gBit(26);
                    } else {
                        anIntArrayArrayArray18[local60][local64][local391] = -1;
                    }
                }
            }
        }
        inboundBuffer.accessBytes();
        local60 = (packetSize - inboundBuffer.offset) / 16;
        LoginManager.regionsXteaKeys = new int[local60][4];
        for (local64 = 0; local64 < local60; local64++) {
            for (local391 = 0; local391 < 4; local391++) {
                LoginManager.regionsXteaKeys[local64][local391] = inboundBuffer.p4rme();
            }
        }
        local64 = inboundBuffer.g2();
        LoginManager.underWaterLocationsMapFileIds = new int[local60];
        LoginManager.locationsMapFileIds = new int[local60];
        LoginManager.mapFileIds = new int[local60];
        LoginManager.underWaterLocationsMapFilesBuffer = new byte[local60][];
        LoginManager.npcSpawnsFileIds = null;
        LoginManager.underWaterMapFileIds = new int[local60];
        LoginManager.locationMapFilesBuffer = new byte[local60][];
        LoginManager.mapFilesBuffer = new byte[local60][];
        LoginManager.regionBitPacked = new int[local60];
        LoginManager.npcSpawnsFilesBuffer = null;
        LoginManager.underWaterMapFilesBuffer = new byte[local60][];
        local60 = 0;
        for (local391 = 0; local391 < 4; local391++) {
            for (local138 = 0; local138 < 13; local138++) {
                for (local151 = 0; local151 < 13; local151++) {
                    local169 = anIntArrayArrayArray18[local391][local138][local151];
                    if (local169 != -1) {
                        @Pc(555) int local555 = local169 >> 14 & 0x3FF;
                        @Pc(561) int local561 = local169 >> 3 & 0x7FF;
                        @Pc(571) int local571 = local561 / 8 + (local555 / 8 << 8);
                        @Pc(573) int local573;
                        for (local573 = 0; local573 < local60; local573++) {
                            if (local571 == LoginManager.regionBitPacked[local573]) {
                                local571 = -1;
                                break;
                            }
                        }
                        if (local571 != -1) {
                            LoginManager.regionBitPacked[local60] = local571;
                            @Pc(609) int local609 = local571 & 0xFF;
                            local573 = local571 >> 8 & 0xFF;
                            LoginManager.mapFileIds[local60] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_558, JString.parseInt(local573), LoginManager.UNDERSCORE, JString.parseInt(local609) }));
                            LoginManager.locationsMapFileIds[local60] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_1090, JString.parseInt(local573), LoginManager.UNDERSCORE, JString.parseInt(local609) }));
                            LoginManager.underWaterMapFileIds[local60] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_772, JString.parseInt(local573), LoginManager.UNDERSCORE, JString.parseInt(local609) }));
                            LoginManager.underWaterLocationsMapFileIds[local60] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.aClass100_1103, JString.parseInt(local573), LoginManager.UNDERSCORE, JString.parseInt(local609) }));
                            local60++;
                        }
                    }
                }
            }
        }
        LoginManager.method2463(local26, local64, local20, local31, false, local13);
    }

    @OriginalMember(owner = "client!gk", name = "a", descriptor = "(IIBLclient!e;)V")
    public static void readExtendedPlayerInfo(@OriginalArg(0) int flags, @OriginalArg(1) int arg1, @OriginalArg(3) Player player) {
        @Pc(13) int chatFlags;
        @Pc(17) int staffModLevel;
        @Pc(24) int local24;
        if ((flags & 0x80) != 0) {

            chatFlags = inboundBuffer.g2le();
            staffModLevel = inboundBuffer.g1();
            @Pc(21) int len = inboundBuffer.g1();
            local24 = inboundBuffer.offset;

            @Pc(35) boolean quickChat = (chatFlags & 0x8000) != 0;

            if (player.username != null && player.appearance != null) {
                @Pc(48) long encodedUsername = player.username.encode37();
                @Pc(50) boolean ignored = false;
                if (staffModLevel <= 1) {
                    if (!quickChat && (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat)) {
                        ignored = true;
                    } else {
                        for (@Pc(69) int i = 0; i < IgnoreList.ignoreCount; i++) {
                            if (IgnoreList.encodedIgnores[i] == encodedUsername) {
                                ignored = true;
                                break;
                            }
                        }
                    }
                }
                if (!ignored && Player.inTutorialIsland == 0) {
                    CHAT_PACKET.offset = 0;
                    inboundBuffer.gBytesRev(CHAT_PACKET.data, len);
                    CHAT_PACKET.offset = 0;

                    @Pc(106) int phraseId = -1;

                    @Pc(127) JString message;
                    if (quickChat) {
                        @Pc(112) QuickChatPhrase quickChatPhrase = QuickChatPhraseType.method3568(CHAT_PACKET);
                        chatFlags &= 0x7FFF;
                        phraseId = quickChatPhrase.id;
                        message = quickChatPhrase.type.decodeMessage(CHAT_PACKET);
                    } else {
                        message = Font.escape(formatChatMessage(CHAT_PACKET).encodeMessage());
                    }
                    player.chatMessage = message.trim();
                    player.chatEffect = chatFlags & 0xFF;
                    player.chatLoops = 150;
                    player.chatColor = chatFlags >> 8;
                    if (staffModLevel == 2) {
                        Chat.add(phraseId, quickChat ? 17 : 1, message, null, JString.concatenate(new JString[] {IMG1, player.getUsername() }));
                    } else if (staffModLevel == 1) {
                        Chat.add(phraseId, quickChat ? 17 : 1, message, null, JString.concatenate(new JString[] {IMG0, player.getUsername() }));
                    } else {
                        Chat.add(phraseId, quickChat ? 17 : 2, message, null, player.getUsername());
                    }
                }
            }
            inboundBuffer.offset = local24 + len;
        }
        if ((flags & 0x1) != 0) {
            chatFlags = inboundBuffer.gSmart1or2();
            staffModLevel = inboundBuffer.g1add();
            player.addHit(staffModLevel, client.loop, chatFlags);
            player.hitpointsBarVisibleUntil = client.loop + 300;
            player.hitpointsBar = inboundBuffer.g1_alt3();
        }
        if ((flags & 0x8) != 0) {
            chatFlags = inboundBuffer.g2();
            if (chatFlags == 65535) {
                chatFlags = -1;
            }
            staffModLevel = inboundBuffer.g1();
            Player.animate(staffModLevel, chatFlags, player);
        }
        if ((flags & 0x4) != 0) {
            chatFlags = inboundBuffer.g1add();
            @Pc(309) byte[] local309 = new byte[chatFlags];
            @Pc(314) Packet local314 = new Packet(local309);
            inboundBuffer.gdata(chatFlags, local309);
            PlayerList.appearanceCache[arg1] = local314;
            player.decodeAppearance(local314);
        }
        if ((flags & 0x2) != 0) {
            player.faceEntity = inboundBuffer.g2sub();
            if (player.faceEntity == 65535) {
                player.faceEntity = -1;
            }
        }
        if ((flags & 0x400) != 0) {
            player.anInt3380 = inboundBuffer.p1neg();
            player.anInt3428 = inboundBuffer.g1();
            player.anInt3416 = inboundBuffer.g1add();
            player.anInt3392 = inboundBuffer.g1();
            player.anInt3395 = inboundBuffer.g2le() + client.loop;
            player.anInt3386 = inboundBuffer.g2le() + client.loop;
            player.anInt3431 = inboundBuffer.p1neg();
            player.movementQueueSize = 1;
            player.anInt3405 = 0;
        }
        if ((flags & 0x20) != 0) {
            player.chatMessage = inboundBuffer.gjstr();
            if (player.chatMessage.charAt(0) == 126) {
                player.chatMessage = player.chatMessage.substring(1);
                Chat.addMessage(player.getUsername(), 2, player.chatMessage);
            } else if (player == PlayerList.self) {
                Chat.addMessage(player.getUsername(), 2, player.chatMessage);
            }
            player.chatEffect = 0;
            player.chatColor = 0;
            player.chatLoops = 150;
        }
        if ((flags & 0x200) != 0) {
            chatFlags = inboundBuffer.gSmart1or2();
            staffModLevel = inboundBuffer.g1_alt3();
            player.addHit(staffModLevel, client.loop, chatFlags);
        }
        if ((flags & 0x800) != 0) {
            chatFlags = inboundBuffer.p1neg();
            @Pc(502) int[] local502 = new int[chatFlags];
            @Pc(505) int[] local505 = new int[chatFlags];
            @Pc(508) int[] local508 = new int[chatFlags];
            for (@Pc(510) int local510 = 0; local510 < chatFlags; local510++) {
                @Pc(521) int local521 = inboundBuffer.g2le();
                if (local521 == 65535) {
                    local521 = -1;
                }
                local502[local510] = local521;
                local505[local510] = inboundBuffer.g1add();
                local508[local510] = inboundBuffer.g2();
            }
            Player.method865(local505, local502, player, local508);
        }
        if ((flags & 0x100) != 0) {
            chatFlags = inboundBuffer.g2le();
            if (chatFlags == 65535) {
                chatFlags = -1;
            }
            staffModLevel = inboundBuffer.p4rme();
            @Pc(573) boolean local573 = true;
            if (chatFlags != -1 && player.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(chatFlags).animationId).priority < SeqTypeList.get(SpotAnimTypeList.get(player.spotAnimId).animationId).priority) {
                local573 = false;
            }
            if (local573) {
                player.spotAnimStart = (staffModLevel & 0xFFFF) + client.loop;
                player.anInt3361 = 0;
                player.spotanimId = 0;
                player.spotAnimId = chatFlags;
                if (player.spotAnimStart > client.loop) {
                    player.spotanimId = -1;
                }
                player.spotAnimY = staffModLevel >> 16;
                player.anInt3418 = 1;
                if (player.spotAnimId != -1 && client.loop == player.spotAnimStart) {
                    local24 = SpotAnimTypeList.get(player.spotAnimId).animationId;
                    if (local24 != -1) {
                        @Pc(663) SeqType local663 = SeqTypeList.get(local24);
                        if (local663 != null && local663.frames != null) {
                            SoundPlayer.playSeqSound(player.zFine, local663, player.xFine, player == PlayerList.self, 0);
                        }
                    }
                }
            }
        }
        if ((flags & 0x40) != 0) {
            player.faceX = inboundBuffer.g2();
            player.faceY = inboundBuffer.g2leadd();
        }
    }

    @OriginalMember(owner = "client!fb", name = "b", descriptor = "(B)V")
    public static void readPlayerInfoPacket() {
        extendedCount = 0;
        removedCount = 0;
        readSelfPlayerInfo();
        readPlayerInfo();
        readNewPlayerInfo();
        readExtendedPlayerInfo();
        @Pc(23) int i;
        for (i = 0; i < removedCount; i++) {
            @Pc(30) int index = removedIds[i];
            if (client.loop != PlayerList.players[index].lastSeenLoop) {
                if (PlayerList.players[index].soundRadius > 0) {
                    AreaSoundManager.remove(PlayerList.players[index]);
                }
                PlayerList.players[index] = null;
            }
        }
        if (packetSize != inboundBuffer.offset) {
            throw new RuntimeException("gpp1 pos:" + inboundBuffer.offset + " psize:" + packetSize);
        }
        for (i = 0; i < PlayerList.playerCount; i++) {
            if (PlayerList.players[PlayerList.playerIds[i]] == null) {
                throw new RuntimeException("gpp2 pos:" + i + " size:" + PlayerList.playerCount);
            }
        }
    }

    @OriginalMember(owner = "client!bg", name = "a", descriptor = "(B)V")
    public static void readSelfPlayerInfo() {
        inboundBuffer.accessBits();
        @Pc(11) int local11 = inboundBuffer.gBit(1);
        if (local11 == 0) {
            return;
        }
        @Pc(23) int local23 = inboundBuffer.gBit(2);
        if (local23 == 0) {
            extendedIds[extendedCount++] = 2047;
            return;
        }
        @Pc(54) int local54;
        @Pc(64) int local64;
        if (local23 == 1) {
            local54 = inboundBuffer.gBit(3);
            PlayerList.self.move(1, local54);
            local64 = inboundBuffer.gBit(1);
            if (local64 == 1) {
                extendedIds[extendedCount++] = 2047;
            }
        } else if (local23 == 2) {
            if (inboundBuffer.gBit(1) == 1) {
                local54 = inboundBuffer.gBit(3);
                PlayerList.self.move(2, local54);
                local64 = inboundBuffer.gBit(3);
                PlayerList.self.move(2, local64);
            } else {
                local54 = inboundBuffer.gBit(3);
                PlayerList.self.move(0, local54);
            }
            local54 = inboundBuffer.gBit(1);
            if (local54 == 1) {
                extendedIds[extendedCount++] = 2047;
            }
        } else if (local23 == 3) {
            local54 = inboundBuffer.gBit(7);
            local64 = inboundBuffer.gBit(1);
            Player.plane = inboundBuffer.gBit(2);
            @Pc(163) int local163 = inboundBuffer.gBit(1);
            if (local163 == 1) {
                extendedIds[extendedCount++] = 2047;
            }
            @Pc(181) int local181 = inboundBuffer.gBit(7);
            PlayerList.self.teleport(local181, local64 == 1, local54);
        }
    }

    @OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(I)V")
    public static void readNewPlayerInfo() {
        while (true) {
            if (inboundBuffer.bitsAvailable(packetSize) >= 11) {
                @Pc(20) int index = inboundBuffer.gBit(11);
                if (index != 2047) {
                    @Pc(27) boolean local27 = false;
                    if (PlayerList.players[index] == null) {
                        PlayerList.players[index] = new Player();
                        local27 = true;
                        if (PlayerList.appearanceCache[index] != null) {
                            PlayerList.players[index].decodeAppearance(PlayerList.appearanceCache[index]);
                        }
                    }
                    PlayerList.playerIds[PlayerList.playerCount++] = index;
                    @Pc(65) Player player = PlayerList.players[index];
                    player.lastSeenLoop = client.loop;
                    @Pc(73) int local73 = inboundBuffer.gBit(1);
                    if (local73 == 1) {
                        extendedIds[extendedCount++] = index;
                    }
                    @Pc(92) int dx = inboundBuffer.gBit(5);
                    @Pc(99) int local99 = PathingEntity.ANGLES[inboundBuffer.gBit(3)];
                    if (dx > 15) {
                        dx -= 32;
                    }
                    if (local27) {
                        player.dstYaw = player.anInt3381 = local99;
                    }
                    @Pc(116) int jump = inboundBuffer.gBit(1);
                    @Pc(121) int dz = inboundBuffer.gBit(5);
                    if (dz > 15) {
                        dz -= 32;
                    }
                    player.teleport(dx + PlayerList.self.movementQueueX[0], jump == 1, PlayerList.self.movementQueueZ[0] + dz);
                    continue;
                }
            }
            inboundBuffer.accessBytes();
            return;
        }
    }

    @OriginalMember(owner = "runetek4.client!tm", name = "a", descriptor = "(I)V")
    public static void readExtendedPlayerInfo() {
        for (@Pc(7) int i = 0; i < extendedCount; i++) {
            @Pc(31) int id = extendedIds[i];
            @Pc(35) Player player = PlayerList.players[id];
            @Pc(39) int flags = inboundBuffer.g1();
            if ((flags & 0x10) != 0) {
                flags += inboundBuffer.g1() << 8;
            }
            readExtendedPlayerInfo(flags, id, player);
        }
    }

    @OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(I)V")
    public static void npcCombat() {
        for (@Pc(3) int i = 0; i < extendedCount; i++) {
            @Pc(10) int extendedId = extendedIds[i];
            @Pc(14) Npc npc = NpcList.npcs[extendedId];
            @Pc(18) int local18 = inboundBuffer.g1();
            if ((local18 & 0x8) != 0) {
                local18 += inboundBuffer.g1() << 8;
            }
            @Pc(43) int local43;
            @Pc(47) int info;
            if ((local18 & 0x40) != 0) {
                local43 = inboundBuffer.g1(); // Hit value
                info = inboundBuffer.p1neg(); // Color
                npc.addHit(info, client.loop, local43);
                npc.hitpointsBarVisibleUntil = client.loop + 300;
                npc.hitpointsBar = inboundBuffer.g1_alt3();
            }
            if ((local18 & 0x2) != 0) {
                local43 = inboundBuffer.p1neg(); // Hit value
                info = inboundBuffer.g1_alt3(); // Color
                npc.addHit(info, client.loop, local43);
            }
            if ((local18 & 0x10) != 0) {
                local43 = inboundBuffer.g2(); // Animation ID
                info = inboundBuffer.g1(); // Sequence
                if (local43 == 65535) {
                    local43 = -1;
                }
                animateNpc(info, local43, npc);
            }
            if ((local18 & 0x4) != 0) {
                npc.faceEntity = inboundBuffer.g2sub();
                if (npc.faceEntity == 65535) {
                    npc.faceEntity = -1;
                }
            }
            if ((local18 & 0x80) != 0) {
                local43 = inboundBuffer.g2sub();
                if (local43 == 65535) {
                    local43 = -1;
                }
                info = inboundBuffer.g4me();
                @Pc(147) boolean local147 = true;
                if (local43 != -1 && npc.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(local43).animationId).priority < SeqTypeList.get(SpotAnimTypeList.get(npc.spotAnimId).animationId).priority) {
                    local147 = false;
                }
                if (local147) {
                    npc.spotAnimId = local43;
                    npc.spotAnimStart = (info & 0xFFFF) + client.loop;
                    npc.anInt3361 = 0;
                    npc.spotanimId = 0;
                    npc.spotAnimY = info >> 16;
                    npc.anInt3418 = 1;
                    if (npc.spotAnimStart > client.loop) {
                        npc.spotanimId = -1;
                    }
                    if (npc.spotAnimId != -1 && npc.spotAnimStart == client.loop) {
                        @Pc(227) int seqId = SpotAnimTypeList.get(npc.spotAnimId).animationId;
                        if (seqId != -1) {
                            @Pc(236) SeqType local236 = SeqTypeList.get(seqId);
                            if (local236 != null && local236.frames != null) {
                                SoundPlayer.playSeqSound(npc.zFine, local236, npc.xFine, false, 0);
                            }
                        }
                    }
                }
            }
            if ((local18 & 0x1) != 0) {
                if (npc.type.hasAreaSound()) {
                    AreaSoundManager.remove(npc);
                }
                npc.setNpcType(NpcTypeList.get(inboundBuffer.g2le()));
                npc.setSize(npc.type.size);
                npc.anInt3365 = npc.type.bastypeid;
                if (npc.type.hasAreaSound()) {
                    AreaSoundManager.add(npc.movementQueueZ[0], null, 0, npc, npc.movementQueueX[0], Player.plane, null);
                }
            }
            if ((local18 & 0x20) != 0) {
                npc.chatMessage = inboundBuffer.gjstr();
                npc.chatLoops = 100;
            }
            if ((local18 & 0x100) != 0) {
                local43 = inboundBuffer.p1neg();
                @Pc(331) int[] local331 = new int[local43];
                @Pc(334) int[] local334 = new int[local43];
                @Pc(337) int[] local337 = new int[local43];
                for (@Pc(339) int local339 = 0; local339 < local43; local339++) {
                    @Pc(350) int local350 = inboundBuffer.g2le();
                    if (local350 == 65535) {
                        local350 = -1;
                    }
                    local331[local339] = local350;
                    local334[local339] = inboundBuffer.g1_alt3();
                    local337[local339] = inboundBuffer.g2();
                }
                method3037(local337, npc, local334, local331);
            }
            if ((local18 & 0x200) != 0) {
                npc.faceX = inboundBuffer.g2sub();
                npc.faceY = inboundBuffer.g2();
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(I)V")
    public static void loadAreaNPCs() {
        while (true) {
            if (inboundBuffer.bitsAvailable(packetSize) >= 27) {
                @Pc(14) int npcIndex = inboundBuffer.gBit(15);
                if (npcIndex != 32767) {
                    @Pc(19) boolean local19 = false;
                    if (NpcList.npcs[npcIndex] == null) {
                        local19 = true;
                        NpcList.npcs[npcIndex] = new Npc();
                    }
                    @Pc(37) Npc npc = NpcList.npcs[npcIndex];
                    NpcList.npcIds[NpcList.npcCount++] = npcIndex;
                    npc.lastSeenLoop = client.loop;
                    if (npc.type != null && npc.type.hasAreaSound()) {
                        AreaSoundManager.remove(npc);
                    }
                    @Pc(66) int local66 = inboundBuffer.gBit(1);
                    @Pc(73) int angle = PathingEntity.ANGLES[inboundBuffer.gBit(3)];
                    if (local19) {
                        npc.dstYaw = npc.anInt3381 = angle;
                    }
                    @Pc(86) int local86 = inboundBuffer.gBit(1);
                    if (local86 == 1) {
                        extendedIds[extendedCount++] = npcIndex;
                    }
                    @Pc(105) int local105 = inboundBuffer.gBit(5);
                    npc.setNpcType(NpcTypeList.get(inboundBuffer.gBit(14)));
                    if (local105 > 15) {
                        local105 -= 32;
                    }
                    @Pc(124) int local124 = inboundBuffer.gBit(5);
                    if (local124 > 15) {
                        local124 -= 32;
                    }
                    npc.setSize(npc.type.size);
                    npc.anInt3365 = npc.type.bastypeid;
                    npc.anInt3376 = npc.type.rotationSpeed;
                    if (npc.anInt3376 == 0) {
                        npc.anInt3381 = 0;
                    }
                    npc.teleport(npc.getSize(), PlayerList.self.movementQueueX[0] + local124, local105 + PlayerList.self.movementQueueZ[0], local66 == 1);
                    if (npc.type.hasAreaSound()) {
                        AreaSoundManager.add(npc.movementQueueZ[0], null, 0, npc, npc.movementQueueX[0], Player.plane, null);
                    }
                    continue;
                }
            }
            inboundBuffer.accessBytes();
            return;
        }
    }

    @OriginalMember(owner = "client!fk", name = "c", descriptor = "(I)V")
    public static void transmitVerifyId() {
        outboundBuffer.pIsaac1(177);
        outboundBuffer.p2(verifyId);
    }

    @OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!wa;I)V")
    public static void writeRandom(@OriginalArg(0) Packet arg0) {
        if (client.uid != null) {
            try {
                client.uid.seek(0L);
                client.uid.write(arg0.data, arg0.offset, 24);
            } catch (@Pc(16) Exception local16) {
            }
        }
        arg0.offset += 24;
    }

    @OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!wa;I)Lclient!na;")
    public static JString formatChatMessage(@OriginalArg(0) Packet arg0) {
        return method4350(arg0);
    }

    @OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(Lclient!wa;II)Lclient!na;")
    public static JString method4350(@OriginalArg(0) Packet arg0) {
        try {
            @Pc(7) int local7 = arg0.gSmart1or2();
            if (local7 > 32767) {
                local7 = 32767;
            }
            @Pc(15) byte[] local15 = new byte[local7];
            arg0.offset += Static62.aClass44_1.decode(0, local7, local15, arg0.data, arg0.offset);
            return JString.decodeString(local15, local7, 0);
        } catch (@Pc(47) Exception local47) {
            return Static267.CABBAGE;
        }
    }

    @OriginalMember(owner = "runetek4.client!mi", name = "a", descriptor = "([IBLclient!km;[I[I)V")
    public static void method3037(@OriginalArg(0) int[] arg0, @OriginalArg(2) Npc arg1, @OriginalArg(3) int[] arg2, @OriginalArg(4) int[] arg3) {
        for (@Pc(3) int local3 = 0; local3 < arg3.length; local3++) {
            @Pc(15) int local15 = arg3[local3];
            @Pc(19) int local19 = arg0[local3];
            @Pc(23) int local23 = arg2[local3];
            for (@Pc(25) int local25 = 0; local19 != 0 && arg1.aPathingEntityClass147Array3.length > local25; local25++) {
                if ((local19 & 0x1) != 0) {
                    if (local15 == -1) {
                        arg1.aPathingEntityClass147Array3[local25] = null;
                    } else {
                        @Pc(60) SeqType local60 = SeqTypeList.get(local15);
                        @Pc(65) PathingEntity_Class147 local65 = arg1.aPathingEntityClass147Array3[local25];
                        @Pc(68) int local68 = local60.exactmove;
                        if (local65 != null) {
                            if (local15 == local65.anInt5396) {
                                if (local68 == 0) {
                                    local65 = arg1.aPathingEntityClass147Array3[local25] = null;
                                } else if (local68 == 1) {
                                    local65.anInt5399 = 0;
                                    local65.anInt5400 = 0;
                                    local65.anInt5398 = 1;
                                    local65.anInt5404 = 0;
                                    local65.anInt5408 = local23;
                                    SoundPlayer.playSeqSound(arg1.zFine, local60, arg1.xFine, false, 0);
                                } else if (local68 == 2) {
                                    local65.anInt5400 = 0;
                                }
                            } else if (local60.priority >= SeqTypeList.get(local65.anInt5396).priority) {
                                local65 = arg1.aPathingEntityClass147Array3[local25] = null;
                            }
                        }
                        if (local65 == null) {
                            local65 = arg1.aPathingEntityClass147Array3[local25] = new PathingEntity_Class147();
                            local65.anInt5398 = 1;
                            local65.anInt5404 = 0;
                            local65.anInt5408 = local23;
                            local65.anInt5396 = local15;
                            local65.anInt5400 = 0;
                            local65.anInt5399 = 0;
                            SoundPlayer.playSeqSound(arg1.zFine, local60, arg1.xFine, false, 0);
                        }
                    }
                }
                local19 >>>= 0x1;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(IIILclient!km;)V")
    public static void animateNpc(@OriginalArg(0) int arg0, @OriginalArg(1) int animationId, @OriginalArg(3) Npc npc) {
        if (npc.primarySeqId == animationId && animationId != -1) {
            @Pc(10) SeqType seqType = SeqTypeList.get(animationId);
            @Pc(13) int local13 = seqType.exactmove;
            if (local13 == 1) {
                npc.anInt3373 = 1;
                npc.anInt3425 = 0;
                npc.anInt3360 = 0;
                npc.anInt3371 = 0;
                npc.anInt3420 = arg0;
                SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, npc.anInt3425);
            }
            if (local13 == 2) {
                npc.anInt3371 = 0;
            }
        } else if (animationId == -1 || npc.primarySeqId == -1 || SeqTypeList.get(animationId).priority >= SeqTypeList.get(npc.primarySeqId).priority) {
            npc.anInt3360 = 0;
            npc.primarySeqId = animationId;
            npc.anInt3373 = 1;
            npc.anInt3371 = 0;
            npc.anInt3420 = arg0;
            npc.anInt3405 = npc.movementQueueSize;
            npc.anInt3425 = 0;
            if (npc.primarySeqId != -1) {
                SoundPlayer.playSeqSound(npc.zFine, SeqTypeList.get(npc.primarySeqId), npc.xFine, false, npc.anInt3425);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(ILclient!na;)V")
    public static void method3954(@OriginalArg(1) JString arg0) {
        client.settings = arg0;
        if (GameShell.signLink.applet == null) {
            return;
        }
        try {
            @Pc(17) JString local17 = aClass100_989.fromParameters(GameShell.signLink.applet);
            @Pc(23) JString local23 = aClass100_1029.fromParameters(GameShell.signLink.applet);
            @Pc(48) JString local48 = JString.concatenate(new JString[] { local17, aClass100_667, arg0, aClass100_1095, local23 });
            if (arg0.length() == 0) {
                local48 = JString.concatenate(new JString[] { local48, aClass100_1018});
            } else {
                local48 = JString.concatenate(new JString[] { local48, aClass100_1082, DateUtil.getDateString(MonotonicTime.currentTimeMillis() + 94608000000L), MAX_AGE, JString.method2929(94608000L) });
            }
            JString.concatenate(new JString[] {aClass100_821, local48, aClass100_946}).method3134(GameShell.signLink.applet);
        } catch (@Pc(124) Throwable local124) {
        }
    }

    @OriginalMember(owner = "client!dh", name = "a", descriptor = "(IIII)Lclient!wk;")
    public static ComponentPointer openSubInterface(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
        @Pc(9) ComponentPointer local9 = new ComponentPointer();
        local9.anInt5879 = arg2;
        local9.interfaceId = arg0;
        InterfaceList.openInterfaces.put(local9, arg1);
        InterfaceList.method1753(arg0);
        @Pc(28) Component local28 = InterfaceList.getComponent(arg1);
        if (local28 != null) {
            InterfaceList.redraw(local28);
        }
        if (ClientScriptRunner.aClass13_10 != null) {
            InterfaceList.redraw(ClientScriptRunner.aClass13_10);
            ClientScriptRunner.aClass13_10 = null;
        }
        @Pc(45) int local45 = MiniMenu.menuActionRow;
        @Pc(53) int local53;
        for (local53 = 0; local53 < local45; local53++) {
            if (InterfaceList.method5(MiniMenu.actions[local53])) {
                MiniMenu.remove(local53);
            }
        }
        if (MiniMenu.menuActionRow == 1) {
            ClientScriptRunner.aBoolean108 = false;
            InterfaceList.redrawScreen(InterfaceList.anInt4271, InterfaceList.anInt761, InterfaceList.anInt5138, InterfaceList.anInt436);
        } else {
            InterfaceList.redrawScreen(InterfaceList.anInt4271, InterfaceList.anInt761, InterfaceList.anInt5138, InterfaceList.anInt436);
            local53 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(95) int local95 = 0; local95 < MiniMenu.menuActionRow; local95++) {
                @Pc(104) int local104 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(local95));
                if (local104 > local53) {
                    local53 = local104;
                }
            }
            InterfaceList.anInt761 = local53 + 8;
            InterfaceList.anInt436 = MiniMenu.menuActionRow * 15 + (InterfaceList.aBoolean298 ? 26 : 22);
        }
        if (local28 != null) {
            InterfaceList.method531(local28, false);
        }
        InterfaceList.method1626(arg0);
        if (InterfaceList.topLevelInterface != -1) {
            InterfaceList.runScripts(1, InterfaceList.topLevelInterface);
        }
        return local9;
    }

    @OriginalMember(owner = "client!ah", name = "b", descriptor = "(I)V")
    public static void method843() {
        if (InterfaceList.clickedInventoryComponent != null || ClientScriptRunner.aClass13_14 != null) {
            return;
        }
        @Pc(20) int local20 = Mouse.clickButton;
        @Pc(93) int local93;
        @Pc(99) int local99;
        if (!ClientScriptRunner.aBoolean108) {
            if (local20 == 1 && MiniMenu.menuActionRow > 0) {
                @Pc(37) short local37 = MiniMenu.actions[MiniMenu.menuActionRow - 1];
                if (local37 == 25 || local37 == 23 || local37 == 48 || local37 == 7 || local37 == 13 || local37 == 47 || local37 == 5 || local37 == 43 || local37 == 35 || local37 == 58 || local37 == 22 || local37 == 1006) {
                    local93 = MiniMenu.intArgs1[MiniMenu.menuActionRow - 1];
                    local99 = MiniMenu.intArgs2[MiniMenu.menuActionRow - 1];
                    @Pc(103) Component local103 = InterfaceList.getComponent(local99);
                    @Pc(106) ServerActiveProperties local106 = InterfaceList.getServerActiveProperties(local103);
                    if (local106.isObjSwapEnabled() || local106.isObjReplaceEnabled()) {
                        InterfaceList.lastItemDragTime = 0;
                        InterfaceList.draggingClickedInventoryObject = false;
                        if (InterfaceList.clickedInventoryComponent != null) {
                            InterfaceList.redraw(InterfaceList.clickedInventoryComponent);
                        }
                        InterfaceList.clickedInventoryComponent = InterfaceList.getComponent(local99);
                        InterfaceList.clickedInventoryComponentX = Mouse.mouseClickX;
                        InterfaceList.clickedInventoryComponentY = Mouse.mouseClickY;
                        InterfaceList.selectedInventorySlot = local93;
                        InterfaceList.redraw(InterfaceList.clickedInventoryComponent);
                        return;
                    }
                }
            }
            if (local20 == 1 && (Static116.oneMouseButton == 1 && MiniMenu.menuActionRow > 2 || MiniMenu.menuHasAddFriend(MiniMenu.menuActionRow - 1))) {
                local20 = 2;
            }
            if (local20 == 2 && MiniMenu.menuActionRow > 0 || MiniMenu.anInt3953 == 1) {
                ClientScriptRunner.determineMenuSize();
            }
            if (local20 == 1 && MiniMenu.menuActionRow > 0 || MiniMenu.anInt3953 == 2) {
                MiniMenu.processMenuActions();
            }
            return;
        }
        @Pc(204) int local204;
        if (local20 != 1) {
            local93 = Mouse.lastMouseY;
            local204 = Mouse.lastMouseX;
            if (local204 < InterfaceList.anInt4271 - 10 || local204 > InterfaceList.anInt761 + InterfaceList.anInt4271 + 10 || InterfaceList.anInt5138 - 10 > local93 || local93 > InterfaceList.anInt436 + InterfaceList.anInt5138 + 10) {
                ClientScriptRunner.aBoolean108 = false;
                InterfaceList.redrawScreen(InterfaceList.anInt4271, InterfaceList.anInt761, InterfaceList.anInt5138, InterfaceList.anInt436);
            }
        }
        if (local20 != 1) {
            return;
        }
        local204 = InterfaceList.anInt4271;
        local93 = InterfaceList.anInt5138;
        local99 = InterfaceList.anInt761;
        @Pc(265) int local265 = Mouse.mouseClickX;
        @Pc(267) int local267 = Mouse.mouseClickY;
        @Pc(269) int local269 = -1;
        for (@Pc(271) int local271 = 0; local271 < MiniMenu.menuActionRow; local271++) {
            @Pc(289) int local289;
            if (InterfaceList.aBoolean298) {
                local289 = (MiniMenu.menuActionRow - local271 - 1) * 15 + local93 + 35;
            } else {
                local289 = (MiniMenu.menuActionRow - local271 - 1) * 15 + local93 + 31;
            }
            if (local265 > local204 && local204 + local99 > local265 && local289 - 13 < local267 && local289 + 3 > local267) {
                local269 = local271;
            }
        }
        if (local269 != -1) {
            MiniMenu.doAction(local269);
        }
        ClientScriptRunner.aBoolean108 = false;
        InterfaceList.redrawScreen(InterfaceList.anInt4271, InterfaceList.anInt761, InterfaceList.anInt5138, InterfaceList.anInt436);
    }
}
