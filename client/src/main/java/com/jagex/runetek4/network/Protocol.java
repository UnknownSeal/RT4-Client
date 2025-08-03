package com.jagex.runetek4.network;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.world.WorldLoader;
import com.jagex.runetek4.graphics.animation.ProjAnimNode;
import com.jagex.runetek4.graphics.animation.ProjectileAnimation;
import com.jagex.runetek4.graphics.animation.SpotAnim;
import com.jagex.runetek4.graphics.animation.SpotAnimEntity;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.audio.streaming.MusicPlayer;
import com.jagex.runetek4.scene.Camera;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.ui.chat.ClanChat;
import com.jagex.runetek4.ui.chat.QuickChatPhrase;
import com.jagex.runetek4.client.*;
import com.jagex.runetek4.clientscript.DelayedStateChange;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.spotanim.SpotAnimTypeList;
import com.jagex.runetek4.core.datastruct.LinkedList;
import com.jagex.runetek4.core.exceptions.TracingException;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.config.types.bas.BasType;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseType;
import com.jagex.runetek4.entity.entity.*;
import com.jagex.runetek4.game.inventory.Inv;
import com.jagex.runetek4.game.locs.AttachLocRequest;
import com.jagex.runetek4.game.locs.ChangeLocRequest;
import com.jagex.runetek4.game.state.VarpDomain;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.core.DisplayMode;
import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.ui.component.*;
import com.jagex.runetek4.ui.social.ClanMember;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.util.string.WordPack;
import com.jagex.runetek4.game.map.MapMarker;
import com.jagex.runetek4.network.security.ReflectionCheck;
import com.jagex.runetek4.entity.loc.Loc;
import com.jagex.runetek4.entity.loc.ObjStack;
import com.jagex.runetek4.entity.loc.ObjStackNode;
import com.jagex.runetek4.scene.SceneCamera;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.social.FriendList;
import com.jagex.runetek4.ui.social.IgnoreList;
import com.jagex.runetek4.ui.sprite.Sprites;
import com.jagex.runetek4.game.stockmarket.StockMarketManager;
import com.jagex.runetek4.game.stockmarket.StockMarketOffer;
import com.jagex.runetek4.ui.events.ComponentEvent;
import com.jagex.runetek4.util.data.Base37;
import com.jagex.runetek4.util.math.MathUtils;
import com.jagex.runetek4.util.system.SignLink;
import com.jagex.runetek4.game.world.WorldMap;
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
    public static final int[][][] dynamicRegionData = new int[4][13][13];

    @OriginalMember(owner = "client!fc", name = "f", descriptor = "Lclient!na;")
    public static final JString IMG0 = JString.parse("<img=0>");

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
        @Pc(6) int newPlayerCount = inboundBuffer.gBit(8);

        if (PlayerList.playerCount > newPlayerCount) {
            for (int index = newPlayerCount; index < PlayerList.playerCount; index++) {
                removedIds[removedCount++] = PlayerList.playerIds[index];
            }
        }
        if (newPlayerCount > PlayerList.playerCount) {
            throw new RuntimeException("gppov1");
        }

        PlayerList.playerCount = 0;

        for (int index = 0; index < newPlayerCount; index++) {
            @Pc(75) int playerId = PlayerList.playerIds[index];
            @Pc(79) Player player = PlayerList.players[playerId];
            @Pc(84) int hasUpdate = inboundBuffer.gBit(1);
            if (hasUpdate == 0) {
                PlayerList.playerIds[PlayerList.playerCount++] = playerId;
                player.lastSeenLoop = Client.loop;
            } else {
                @Pc(107) int updateType = inboundBuffer.gBit(2);
                if (updateType == 0) {
                    PlayerList.playerIds[PlayerList.playerCount++] = playerId;
                    player.lastSeenLoop = Client.loop;
                    extendedIds[extendedCount++] = playerId;
                } else {
                    @Pc(153) int local153;
                    @Pc(163) int local163;
                    if (updateType == 1) {
                        PlayerList.playerIds[PlayerList.playerCount++] = playerId;
                        player.lastSeenLoop = Client.loop;
                        local153 = inboundBuffer.gBit(3);
                        player.move(1, local153);
                        local163 = inboundBuffer.gBit(1);
                        if (local163 == 1) {
                            extendedIds[extendedCount++] = playerId;
                        }
                    } else if (updateType == 2) {
                        PlayerList.playerIds[PlayerList.playerCount++] = playerId;
                        player.lastSeenLoop = Client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            local153 = inboundBuffer.gBit(3);
                            player.move(2, local153);
                            local163 = inboundBuffer.gBit(3);
                            player.move(2, local163);
                        } else {
                            local153 = inboundBuffer.gBit(3);
                            player.move(0, local153);
                        }
                        local153 = inboundBuffer.gBit(1);
                        if (local153 == 1) {
                            extendedIds[extendedCount++] = playerId;
                        }
                    } else if (updateType == 3) {
                        removedIds[removedCount++] = playerId;
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
            for (@Pc(63) int byteIndex = 0; byteIndex < packetSize && byteIndex < 50; byteIndex++) {
                message = message + inboundBuffer.data[byteIndex] + ",";
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
            VarpDomain.setVarpServer(local137, ii);
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
                @Pc(226) ComponentEvent request = new ComponentEvent();
                request.arguments = scriptArgs;
                ClientScriptRunner.run(request);
            }
            opcode = -1;
            return true;
        }
        @Pc(275) long username;
        @Pc(262) boolean ignored;
        @Pc(277) int i;
        @Pc(506) JString worldName;
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
                    worldName = message.substring(message.length() - 9, message.indexOf(JString.COLON_SIGN) + 1);
                    Chat.addMessage(argTypes, 8, worldName);
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
                    worldName = message.substring(message.length() - 9, message.indexOf(JString.COLON_SIGN) + 1);
                    Chat.addMessage(argTypes, 21, worldName);
                }
            } else {
                Chat.addMessage(JString.EMPTY, 0, message);
            }
            opcode = -1;
            return true;
        }
        @Pc(786) int param1;
        @Pc(790) JString messageText;
        if (opcode == 123) {
            ii = inboundBuffer.g2le();
            int verifyID = inboundBuffer.g2sub();
            messageText = inboundBuffer.gjstr();
            if (setVerifyID(verifyID)) {
                DelayedStateChange.method3498(messageText, ii);
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
                param1 = inboundBuffer.g2le();
                int verifyID = inboundBuffer.g2();
                if (setVerifyID(verifyID)) {
                    DelayedStateChange.method3938(param1, ii);
                }
                opcode = -1;
                return true;
            }
            @Pc(884) long senderName;
            @Pc(908) int chatType;
            @Pc(916) int local916;
            @Pc(899) long messageId1;
            @Pc(904) long messageId2;
            if (opcode == 81) {
                senderName = inboundBuffer.g8();
                inboundBuffer.g1s();
                username = inboundBuffer.g8();
                messageId1 = inboundBuffer.g2();
                messageId2 = inboundBuffer.g3();
                chatType = inboundBuffer.g1();
                @Pc(910) boolean local910 = false;
                local916 = inboundBuffer.g2();
                @Pc(922) long local922 = (messageId1 << 32) + messageId2;
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
                    if (chatType <= 1) {
                        for (local924 = 0; local924 < IgnoreList.ignoreCount; local924++) {
                            if (IgnoreList.encodedIgnores[local924] == senderName) {
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
                    if (chatType == 2 || chatType == 3) {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), JString.concatenate(new JString[] { IMG1, Base37.decode37(senderName).toTitleCase() }));
                    } else if (chatType == 1) {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), JString.concatenate(new JString[] { IMG0, Base37.decode37(senderName).toTitleCase() }));
                    } else {
                        Chat.add(local916, 20, local999, Base37.decode37(username).toTitleCase(), Base37.decode37(senderName).toTitleCase());
                    }
                }
                opcode = -1;
                return true;
            }
            @Pc(1146) int count;
            @Pc(1160) int chatFlags;
            @Pc(1245) boolean local1245;
            if (opcode == 55) {
                ClanChat.transmitAt = ComponentList.transmitTimer;
                senderName = inboundBuffer.g8();
                if (senderName == 0L) {
                    ClanChat.owner = null;
                    opcode = -1;
                    ClanChat.name = null;
                    ClanChat.members = null;
                    ClanChat.size = 0;
                    return true;
                }
                username = inboundBuffer.g8();
                ClanChat.name = Base37.decode37(username);
                ClanChat.owner = Base37.decode37(senderName);
                ClanChat.minKick = inboundBuffer.g1s();
                count = inboundBuffer.g1();
                if (count == 255) {
                    opcode = -1;
                    return true;
                }
                ClanChat.size = count;
                @Pc(1158) ClanMember[] local1158 = new ClanMember[100];
                for (chatFlags = 0; chatFlags < ClanChat.size; chatFlags++) {
                    local1158[chatFlags] = new ClanMember();
                    local1158[chatFlags].nodeId = inboundBuffer.g8();
                    local1158[chatFlags].username = Base37.decode37(local1158[chatFlags].nodeId);
                    local1158[chatFlags].world = inboundBuffer.g2();
                    local1158[chatFlags].rank = inboundBuffer.g1s();
                    local1158[chatFlags].worldName = inboundBuffer.gjstr();
                    if (Player.name37 == local1158[chatFlags].nodeId) {
                        ClanChat.rank = local1158[chatFlags].rank;
                    }
                }
                chatType = ClanChat.size;
                while (chatType > 0) {
                    local1245 = true;
                    chatType--;
                    for (local916 = 0; local916 < chatType; local916++) {
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
                // LAST_LOGIN_INFO
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
                    param1 = inboundBuffer.g1();
                    world = inboundBuffer.g1();
                    local1409 = inboundBuffer.gjstr();
                    if (world >= 1 && world <= 8) {
                        if (local1409.equalsIgnoreCase(MiniMenu.NULL)) {
                            local1409 = null;
                        }
                        Player.options[world - 1] = local1409;
                        Player.cursors[world - 1] = ii;
                        Player.secondaryOptions[world - 1] = param1 == 0;
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 226) {
                    ii = inboundBuffer.g4();
                    param1 = inboundBuffer.g2sub();
                    VarpDomain.setVarpServer(ii, param1);
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
                            WorldMap.reset();
                        }
                        ComponentList.topLevelInterface = parent;
                        ComponentList.resetComponentAnimations(parent);
                        ComponentList.updateInterfaceLayout(false);
                        ComponentList.runInterfaceInitScripts(ComponentList.topLevelInterface);
                        for (slot = 0; slot < 100; slot++) {
                            ComponentList.componentNeedsRedraw[slot] = true;
                        }
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 69) {
                    int verifyID = inboundBuffer.g2leadd();
                    param1 = inboundBuffer.g4();
                    world = inboundBuffer.g2sub();
                    if (setVerifyID(verifyID)) {
                        DelayedStateChange.updateVarC(world, param1);
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 141) {
                    senderName = inboundBuffer.g8();
                    world = inboundBuffer.g2();
                    local1409 = QuickChatPhraseTypeList.get(world).decodeMessage(inboundBuffer);
                    Chat.add(world, 19, local1409, null, Base37.decode37(senderName).toTitleCase());
                    opcode = -1;
                    return true;
                } else if (opcode == 169) {
                    writeRandom(inboundBuffer);
                    opcode = -1;
                    return true;
                } else if (opcode == 89) {
                    VarpDomain.resetVarBits();
                    ComponentList.redrawActiveInterfaces();
                    VarpDomain.updatedVarpsWriterIndex += 32;
                    opcode = -1;
                    return true;
                } else if (opcode == 125) {
                    int verifyID = inboundBuffer.g2();
                    param1 = inboundBuffer.g1();
                    world = inboundBuffer.g1();
                    slot = inboundBuffer.g2();
                    count = inboundBuffer.g1();
                    i = inboundBuffer.g1();
                    if (setVerifyID(verifyID)) {
                        Camera.setCameraLookAtTarget(slot, world, count, param1, i);
                    }
                    opcode = -1;
                    return true;
                } else if (opcode == 36) {
                    ii = inboundBuffer.p4rme();
                    param1 = inboundBuffer.g2les();
                    int verifyID = inboundBuffer.g2sub();
                    if (setVerifyID(verifyID)) {
                        DelayedStateChange.method3893(ii, param1);
                    }
                    opcode = -1;
                    return true;
                } else {
                    @Pc(1814) ServerActiveProperties local1814;
                    @Pc(1804) ServerActiveProperties local1804;
                    if (opcode == 9) {
                        ii = inboundBuffer.g2leadd();
                        param1 = inboundBuffer.g4me();
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
                                messageId2 = (long) i + ((long) param1 << 32);
                                local1804 = (ServerActiveProperties) ComponentList.properties.get(messageId2);
                                if (local1804 != null) {
                                    local1814 = new ServerActiveProperties(local1804.events, ii);
                                    local1804.unlink();
                                } else if (i == -1) {
                                    local1814 = new ServerActiveProperties(ComponentList.getComponent(param1).properties.events, ii);
                                } else {
                                    local1814 = new ServerActiveProperties(0, ii);
                                }
                                ComponentList.properties.put(local1814, messageId2);
                            }
                        }
                        opcode = -1;
                        return true;
                    }
                    @Pc(1986) int j;
                    if (opcode == 56) {
                        ii = inboundBuffer.g2();
                        param1 = inboundBuffer.g2le();
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
                                    if (slot != -1 && local1894.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(slot).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(local1894.spotAnimId).seqId).priority) {
                                        local1245 = false;
                                    }
                                    if (local1245) {
                                        local1894.anInt3361 = 0;
                                        local1894.spotAnimId = slot;
                                        local1894.spotAnimStart = Client.loop + ii;
                                        local1894.spotanimId = 0;
                                        if (local1894.spotAnimStart > Client.loop) {
                                            local1894.spotanimId = -1;
                                        }
                                        local1894.spotAnimY = param1;
                                        local1894.anInt3418 = 1;
                                        if (local1894.spotAnimId != -1 && Client.loop == local1894.spotAnimStart) {
                                            j = SpotAnimTypeList.get(local1894.spotAnimId).seqId;
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
                                    if (slot != -1 && local2033.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(slot).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(local2033.spotAnimId).seqId).priority) {
                                        local1245 = false;
                                    }
                                    if (local1245) {
                                        local2033.spotAnimStart = ii + Client.loop;
                                        local2033.spotAnimY = param1;
                                        local2033.spotAnimId = slot;
                                        if (local2033.spotAnimId == 65535) {
                                            local2033.spotAnimId = -1;
                                        }
                                        local2033.anInt3418 = 1;
                                        local2033.anInt3361 = 0;
                                        local2033.spotanimId = 0;
                                        if (local2033.spotAnimStart > Client.loop) {
                                            local2033.spotanimId = -1;
                                        }
                                        if (local2033.spotAnimId != -1 && local2033.spotAnimStart == Client.loop) {
                                            j = SpotAnimTypeList.get(local2033.spotAnimId).seqId;
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
                            chatFlags = (world & 0x3FFF) - Camera.originZ;
                            if (i >= 0 && chatFlags >= 0 && i < 104 && chatFlags < 104) {
                                chatFlags = chatFlags * 128 + 64;
                                i = i * 128 + 64;
                                @Pc(2241) SpotAnim local2241 = new SpotAnim(slot, count, i, chatFlags, SceneGraph.getTileHeight(count, i, chatFlags) - param1, ii, Client.loop);
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
                        ComponentList.redrawActiveInterfaces();
                        ii = inboundBuffer.g1add();
                        param1 = inboundBuffer.g4rme();
                        world = inboundBuffer.g1();
                        PlayerSkillXpTable.experience[world] = param1;
                        PlayerSkillXpTable.boostedLevels[world] = ii;
                        PlayerSkillXpTable.baseLevels[world] = 1;
                        for (slot = 0; slot < 98; slot++) {
                            if (PlayerSkillXpTable.xpLevelLookup[slot] <= param1) {
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
                        param1 = inboundBuffer.g4();
                        if (setVerifyID(verifyID)) {
                            @Pc(2441) SubInterface local2441 = (SubInterface) ComponentList.openInterfaces.get((long) param1);
                            if (local2441 != null) {
                                ComponentList.closeInterface(true, local2441);
                            }
                            if (ClientScriptRunner.modalBackgroundComponent != null) {
                                ComponentList.redraw(ClientScriptRunner.modalBackgroundComponent);
                                ClientScriptRunner.modalBackgroundComponent = null;
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
                            for (param1 = SceneGraph.currentChunkZ; param1 < SceneGraph.currentChunkZ + 8; param1++) {
                                if (SceneGraph.objStacks[Player.plane][ii][param1] != null) {
                                    SceneGraph.objStacks[Player.plane][ii][param1] = null;
                                    spawnGroundObject(param1, ii);
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
                        @Pc(2666) Component local2666 = ComponentList.getComponent(ii);
                        for (world = 0; world < local2666.invSlotObjId.length; world++) {
                            local2666.invSlotObjId[world] = -1;
                            local2666.invSlotObjId[world] = 0;
                        }
                        ComponentList.redraw(local2666);
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
                        param1 = inboundBuffer.g1add();
                        world = inboundBuffer.g1();
                        Player.plane = param1 >> 1;
                        PlayerList.self.teleport(ii, (param1 & 0x1) == 1, world);
                        opcode = -1;
                        return true;
                    } else {
                        @Pc(3002) int local3002;
                        @Pc(3038) JString local3038;
                        @Pc(3020) JString local3020;
                        if (opcode == 62) {
                            senderName = inboundBuffer.g8();
                            world = inboundBuffer.g2();
                            slot = inboundBuffer.g1();
                            ignored = true;
                            if (senderName < 0L) {
                                senderName &= Long.MAX_VALUE;
                                ignored = false;
                            }
                            worldName = JString.EMPTY;
                            if (world > 0) {
                                worldName = inboundBuffer.gjstr();
                            }
                            @Pc(2834) JString displayName = Base37.decode37(senderName).toTitleCase();
                            for (j = 0; j < FriendList.friendCount; j++) {
                                if (senderName == FriendList.encodedUsernames[j]) {
                                    if (world != FriendList.friendWorlds[j]) {
                                        FriendList.friendWorlds[j] = world;
                                        if (world > 0) {
                                            Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGIN}));
                                        }
                                        if (world == 0) {
                                            Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGOUT}));
                                        }
                                    }
                                    FriendList.worldNames[j] = worldName;
                                    FriendList.ranks[j] = slot;
                                    displayName = null;
                                    FriendList.friendGame[j] = ignored;
                                    break;
                                }
                            }
                            if (displayName != null && FriendList.friendCount < 200) {
                                FriendList.encodedUsernames[FriendList.friendCount] = senderName;
                                FriendList.friendUsernames[FriendList.friendCount] = displayName;
                                FriendList.friendWorlds[FriendList.friendCount] = world;
                                FriendList.worldNames[FriendList.friendCount] = worldName;
                                FriendList.ranks[FriendList.friendCount] = slot;
                                FriendList.friendGame[FriendList.friendCount] = ignored;
                                FriendList.friendCount++;
                            }
                            FriendList.transmitAt = ComponentList.transmitTimer;
                            chatType = FriendList.friendCount;
                            while (chatType > 0) {
                                chatType--;
                                @Pc(2961) boolean local2961 = true;
                                for (local916 = 0; local916 < chatType; local916++) {
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
                            for (ii = 0; ii < VarpDomain.activeVarps.length; ii++) {
                                if (VarpDomain.serverVarps[ii] != VarpDomain.activeVarps[ii]) {
                                    VarpDomain.activeVarps[ii] = VarpDomain.serverVarps[ii];
                                    VarpDomain.refreshMagicVarp(ii);
                                    VarpDomain.updatedVarps[VarpDomain.updatedVarpsWriterIndex++ & 0x1F] = ii;
                                }
                            }
                            opcode = -1;
                            return true;
                        } else if (opcode == 154) {
                            int verifyID = inboundBuffer.g2();
                            param1 = inboundBuffer.g1();
                            world = inboundBuffer.g1();
                            slot = inboundBuffer.g2();
                            count = inboundBuffer.g1();
                            i = inboundBuffer.g1();
                            if (setVerifyID(verifyID)) {
                                Camera.setCameraTargetPosition(true, count, slot, i, world, param1);
                            }
                            opcode = -1;
                            return true;
                        } else if (opcode == 247) {
                            senderName = inboundBuffer.g8();
                            username = inboundBuffer.g2();
                            messageId1 = inboundBuffer.g3();
                            chatFlags = inboundBuffer.g1();
                            j = inboundBuffer.g2();
                            @Pc(3263) boolean local3263 = false;
                            @Pc(3270) long local3270 = (username << 32) + messageId1;
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
                                if (chatFlags <= 1) {
                                    for (local3272 = 0; local3272 < IgnoreList.ignoreCount; local3272++) {
                                        if (senderName == IgnoreList.encodedIgnores[local3272]) {
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
                                if (chatFlags == 2) {
                                    Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { IMG1, Base37.decode37(senderName).toTitleCase() }));
                                } else if (chatFlags == 1) {
                                    Chat.add(j, 18, local3020, null, JString.concatenate(new JString[] { IMG0, Base37.decode37(senderName).toTitleCase() }));
                                } else {
                                    Chat.add(j, 18, local3020, null, Base37.decode37(senderName).toTitleCase());
                                }
                            }
                            opcode = -1;
                            return true;
                        } else {
                            @Pc(3456) SubInterface oldSubInterface;
                            if (opcode == 176) {
                                ii = inboundBuffer.g4rme();
                                int verifyID = inboundBuffer.g2sub();
                                world = inboundBuffer.g4rme();
                                if (setVerifyID(verifyID)) {
                                    @Pc(3449) SubInterface newSubInterface = (SubInterface) ComponentList.openInterfaces.get((long) ii);
                                    oldSubInterface = (SubInterface) ComponentList.openInterfaces.get((long) world);
                                    if (oldSubInterface != null) {
                                        ComponentList.closeInterface(newSubInterface == null || oldSubInterface.interfaceId != newSubInterface.interfaceId, oldSubInterface);
                                    }
                                    if (newSubInterface != null) {
                                        newSubInterface.unlink();
                                        ComponentList.openInterfaces.put(newSubInterface, (long) world);
                                    }
                                    @Pc(3490) Component local3490 = ComponentList.getComponent(ii);
                                    if (local3490 != null) {
                                        ComponentList.redraw(local3490);
                                    }
                                    local3490 = ComponentList.getComponent(world);
                                    if (local3490 != null) {
                                        ComponentList.redraw(local3490);
                                        ComponentList.updateContainerLayout(local3490, true);
                                    }
                                    if (ComponentList.topLevelInterface != -1) {
                                        ComponentList.runScripts(1, ComponentList.topLevelInterface);
                                    }
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 27) {
                                int verifyID = inboundBuffer.g2();
                                param1 = inboundBuffer.g1();
                                world = inboundBuffer.g1();
                                slot = inboundBuffer.g1();
                                count = inboundBuffer.g1();
                                i = inboundBuffer.g2();
                                if (setVerifyID(verifyID)) {
                                    Camera.cameraModifierEnabled[param1] = true;
                                    Camera.cameraModifierJitter[param1] = world;
                                    Camera.cameraAmplitude[param1] = slot;
                                    Camera.cameraFrequency[param1] = count;
                                    cameraModifierCycle[param1] = i;
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
                                ComponentList.miscTransmitAt = ComponentList.transmitTimer;
                                return true;
                            } else if (opcode == 114) {
                                ReflectionCheck.push(GameShell.signLink, inboundBuffer, packetSize);
                                opcode = -1;
                                return true;
                            } else if (opcode == 65) {
                                int verifyID = inboundBuffer.g2le();
                                param1 = inboundBuffer.p1neg();
                                world = inboundBuffer.g2leadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.updateVarC(world, param1);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 234) {
                                // UPDATE_RUNENERGY
                                ComponentList.redrawActiveInterfaces();
                                Player.runEnergy = inboundBuffer.g1();
                                ComponentList.miscTransmitAt = ComponentList.transmitTimer;
                                opcode = -1;
                                return true;
                            } else if (opcode == 209) {
                                if (ComponentList.topLevelInterface != -1) {
                                    ComponentList.runScripts(0, ComponentList.topLevelInterface);
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
                                param1 = inboundBuffer.g1_alt3();
                                world = inboundBuffer.g2();
                                @Pc(3766) Npc local3766 = NpcList.npcs[ii];
                                if (local3766 != null) {
                                    animateNpc(param1, world, local3766);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 159) {
                                // UPDATE_RUNWEIGHT
                                ComponentList.redrawActiveInterfaces();
                                Player.weightCarried = inboundBuffer.g2s();
                                ComponentList.miscTransmitAt = ComponentList.transmitTimer;
                                opcode = -1;
                                return true;
                            } else if (opcode == 71) {
                                senderName = inboundBuffer.g8();
                                messageText = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                Chat.addMessage(Base37.decode37(senderName).toTitleCase(), 6, messageText);
                                opcode = -1;
                                return true;
                            } else if (opcode == 42) {
                                if (GameShell.fullScreenFrame != null) {
                                    DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
                                }
                                @Pc(3848) byte[] local3848 = new byte[packetSize];
                                inboundBuffer.method2237(local3848, packetSize);
                                argTypes = JString.decodeString(local3848, packetSize, 0);
                                if (GameShell.frame == null && (SignLink.anInt5928 == 3 || !SignLink.osName.startsWith("win") || Client.haveIe6)) {
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
                                param1 = inboundBuffer.p4rme();
                                world = inboundBuffer.g2leadd();
                                slot = inboundBuffer.g2le();
                                count = inboundBuffer.g2leadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.updateComponentModel(world, 7, param1, slot << 16 | count);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 37) {
                                ii = inboundBuffer.g1add();
                                param1 = inboundBuffer.g2le();
                                VarpDomain.setVarbitServer(ii, param1);
                                opcode = -1;
                                return true;
                            } else if (opcode == 155) {
                                // IF_OPENSUB
                                int flags = inboundBuffer.g1();
                                int windowID = inboundBuffer.p4rme();
                                int verifyID = inboundBuffer.g2sub();
                                int interfaceID = inboundBuffer.g2();
                                if (setVerifyID(verifyID)) {
                                    SubInterface SubInterface = (SubInterface) ComponentList.openInterfaces.get(windowID);
                                    if (SubInterface != null) {
                                        ComponentList.closeInterface(interfaceID != SubInterface.interfaceId, SubInterface);
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
                                param1 = ii >> 6;
                                local4084.type = ii & 0x3F;
                                local4084.anInt4048 = inboundBuffer.g1();
                                if (local4084.anInt4048 >= 0 && local4084.anInt4048 < Sprites.headhints.length) {
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
                                    MiniMap.hintMapMarkers[param1] = local4084;
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
                                FriendList.transmitAt = ComponentList.transmitTimer;
                                opcode = -1;
                                return true;
                            } else if (opcode == 32) {
                                readNpcPacket();
                                opcode = -1;
                                return true;
                            } else if (opcode == 119) {
                                int verifyID = inboundBuffer.g2sub();
                                param1 = inboundBuffer.g4me();
                                world = inboundBuffer.g2s();
                                slot = inboundBuffer.g2sadd();
                                if (setVerifyID(verifyID)) {
                                    DelayedStateChange.method4666(world, param1, slot);
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 235) {
                                ii = inboundBuffer.g1_alt3();
                                param1 = ii >> 2;
                                world = ii & 0x3;
                                slot = Loc.LAYERS[param1];
                                count = inboundBuffer.g2();
                                i = inboundBuffer.g4();
                                if (count == 65535) {
                                    count = -1;
                                }
                                chatType = i & 0x3FFF;
                                j = i >> 14 & 0x3FFF;
                                j -= Camera.originX;
                                chatType -= Camera.originZ;
                                chatFlags = i >> 28 & 0x3;
                                SceneGraph.attachLocToTile(chatFlags, world, param1, chatType, slot, j, count);
                                opcode = -1;
                                return true;
                            } else if (opcode == 0) {
                                senderName = inboundBuffer.g8();
                                username = inboundBuffer.g2();
                                messageId1 = inboundBuffer.g3();
                                chatFlags = inboundBuffer.g1();
                                @Pc(4425) boolean local4425 = false;
                                @Pc(4431) long local4431 = messageId1 + (username << 32);
                                local3002 = 0;
                                label1450: while (true) {
                                    if (local3002 >= 100) {
                                        if (chatFlags <= 1) {
                                            if (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat) {
                                                local4425 = true;
                                            } else {
                                                for (local3002 = 0; local3002 < IgnoreList.ignoreCount; local3002++) {
                                                    if (senderName == IgnoreList.encodedIgnores[local3002]) {
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
                                    if (chatFlags == 2 || chatFlags == 3) {
                                        Chat.addMessage(JString.concatenate(new JString[] { IMG1, Base37.decode37(senderName).toTitleCase() }), 7, local4518);
                                    } else if (chatFlags == 1) {
                                        Chat.addMessage(JString.concatenate(new JString[] { IMG0, Base37.decode37(senderName).toTitleCase() }), 7, local4518);
                                    } else {
                                        Chat.addMessage(Base37.decode37(senderName).toTitleCase(), 3, local4518);
                                    }
                                }
                                opcode = -1;
                                return true;
                            } else if (opcode == 54) {
                                senderName = inboundBuffer.g8();
                                inboundBuffer.g1s();
                                username = inboundBuffer.g8();
                                messageId1 = inboundBuffer.g2();
                                messageId2 = inboundBuffer.g3();
                                @Pc(4626) long local4626 = (messageId1 << 32) + messageId2;
                                chatType = inboundBuffer.g1();
                                @Pc(4632) boolean local4632 = false;
                                @Pc(4634) int local4634 = 0;
                                label1575: while (true) {
                                    if (local4634 >= 100) {
                                        if (chatType <= 1) {
                                            if (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat) {
                                                local4632 = true;
                                            } else {
                                                for (local4634 = 0; local4634 < IgnoreList.ignoreCount; local4634++) {
                                                    if (IgnoreList.encodedIgnores[local4634] == senderName) {
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
                                    if (chatType == 2 || chatType == 3) {
                                        Chat.method1598(local3038, JString.concatenate(new JString[] { IMG1, Base37.decode37(senderName).toTitleCase() }), Base37.decode37(username).toTitleCase());
                                    } else if (chatType == 1) {
                                        Chat.method1598(local3038, JString.concatenate(new JString[] { IMG0, Base37.decode37(senderName).toTitleCase() }), Base37.decode37(username).toTitleCase());
                                    } else {
                                        Chat.method1598(local3038, Base37.decode37(senderName).toTitleCase(), Base37.decode37(username).toTitleCase());
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
                                param1 = inboundBuffer.g1();
                                if (ii == 65535) {
                                    ii = -1;
                                }
                                world = inboundBuffer.g2();
                                SoundPlayer.play(param1, ii, world);
                                opcode = -1;
                                return true;
                            } else if (opcode == 66) {
                                int verifyID = inboundBuffer.g2leadd();
                                param1 = inboundBuffer.g4rme();
                                if (setVerifyID(verifyID)) {
                                    world = 0;
                                    if (PlayerList.self.appearance != null) {
                                        world = PlayerList.self.appearance.getHeadModelId();
                                    }
                                    DelayedStateChange.updateComponentModel(-1, 3, param1, world);
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
                                param1 = inboundBuffer.g2leadd();
                                VarpDomain.setVarbitServer(ii, param1);
                                opcode = -1;
                                return true;
                            } else {
                                @Pc(4956) Component component;
                                if (opcode == 22) {
                                    ii = inboundBuffer.g4();
                                    param1 = inboundBuffer.g2();
                                    if (ii < -70000) {
                                        param1 += 32768;
                                    }
                                    if (ii < 0) {
                                        component = null;
                                    } else {
                                        component = ComponentList.getComponent(ii);
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
                                        if (component != null && slot >= 0 && component.invSlotObjId.length > slot) {
                                            component.invSlotObjId[slot] = count;
                                            component.invSlotObjCount[slot] = i;
                                        }
                                        Inv.update(count - 1, slot, i, param1);
                                    }
                                    if (component != null) {
                                        ComponentList.redraw(component);
                                    }
                                    ComponentList.redrawActiveInterfaces();
                                    Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & 0x1F] = param1 & 0x7FFF;
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
                                    StockMarketManager.transmitAt = ComponentList.transmitTimer;
                                    return true;
                                } else if (opcode == 73) {
                                    ii = inboundBuffer.g2sub();
                                    param1 = inboundBuffer.g4me();
                                    if (ii == 65535) {
                                        ii = -1;
                                    }
                                    int verifyID = inboundBuffer.g2le();
                                    if (setVerifyID(verifyID)) {
                                        DelayedStateChange.updateComponentModel(-1, 2, param1, ii);
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 162) {
                                    readRebuildPacket(false);
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 165) {
                                    int verifyID = inboundBuffer.g2le();
                                    param1 = inboundBuffer.g2le();
                                    if (param1 == 65535) {
                                        param1 = -1;
                                    }
                                    world = inboundBuffer.g4();
                                    slot = inboundBuffer.g2sub();
                                    count = inboundBuffer.g4rme();
                                    if (slot == 65535) {
                                        slot = -1;
                                    }
                                    if (setVerifyID(verifyID)) {
                                        for (i = slot; i <= param1; i++) {
                                            messageId2 = ((long) world << 32) + ((long) i);
                                            local1804 = (ServerActiveProperties) ComponentList.properties.get(messageId2);
                                            if (local1804 != null) {
                                                local1814 = new ServerActiveProperties(count, local1804.targetParam);
                                                local1804.unlink();
                                            } else if (i == -1) {
                                                local1814 = new ServerActiveProperties(count, ComponentList.getComponent(world).properties.targetParam);
                                            } else {
                                                local1814 = new ServerActiveProperties(count, -1);
                                            }
                                            ComponentList.properties.put(local1814, messageId2);
                                        }
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 197) {
                                    FriendList.state = inboundBuffer.g1();
                                    FriendList.transmitAt = ComponentList.transmitTimer;
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 196) {
                                    senderName = inboundBuffer.g8();
                                    world = inboundBuffer.g2();
                                    @Pc(5325) byte local5325 = inboundBuffer.g1s();
                                    ignored = false;
                                    if ((Long.MIN_VALUE & senderName) != 0L) {
                                        ignored = true;
                                    }
                                    if (ignored) {
                                        if (ClanChat.size == 0) {
                                            opcode = -1;
                                            return true;
                                        }
                                        senderName &= Long.MAX_VALUE;
                                        for (i = 0; ClanChat.size > i && (senderName != ClanChat.members[i].nodeId || world != ClanChat.members[i].world); i++) {
                                            // TODO why is this here?
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
                                        worldName = inboundBuffer.gjstr();
                                        @Pc(5347) ClanMember local5347 = new ClanMember();
                                        local5347.nodeId = senderName;
                                        local5347.username = Base37.decode37(local5347.nodeId);
                                        local5347.rank = local5325;
                                        local5347.worldName = worldName;
                                        local5347.world = world;
                                        for (j = ClanChat.size - 1; j >= 0; j--) {
                                            chatType = ClanChat.members[j].username.method3139(local5347.username);
                                            if (chatType == 0) {
                                                ClanChat.members[j].world = world;
                                                ClanChat.members[j].rank = local5325;
                                                ClanChat.members[j].worldName = worldName;
                                                if (senderName == Player.name37) {
                                                    ClanChat.rank = local5325;
                                                }
                                                ClanChat.transmitAt = ComponentList.transmitTimer;
                                                opcode = -1;
                                                return true;
                                            }
                                            if (chatType < 0) {
                                                break;
                                            }
                                        }
                                        if (ClanChat.members.length <= ClanChat.size) {
                                            opcode = -1;
                                            return true;
                                        }
                                        for (chatType = ClanChat.size - 1; chatType > j; chatType--) {
                                            ClanChat.members[chatType + 1] = ClanChat.members[chatType];
                                        }
                                        if (ClanChat.size == 0) {
                                            ClanChat.members = new ClanMember[100];
                                        }
                                        ClanChat.members[j + 1] = local5347;
                                        if (Player.name37 == senderName) {
                                            ClanChat.rank = local5325;
                                        }
                                        ClanChat.size++;
                                    }
                                    opcode = -1;
                                    ClanChat.transmitAt = ComponentList.transmitTimer;
                                    return true;
                                } else if (opcode == 50) {
                                    ii = inboundBuffer.g4();
                                    param1 = inboundBuffer.p4rme();
                                    world = inboundBuffer.g2leadd();
                                    if (world == 65535) {
                                        world = -1;
                                    }
                                    int verifyID = inboundBuffer.g2le();
                                    if (setVerifyID(verifyID)) {
                                        @Pc(5603) Component com = ComponentList.getComponent(param1);
                                        @Pc(5615) ObjType obj;
                                        if (com.if3) {
                                            DelayedStateChange.method3707(param1, ii, world);
                                            obj = ObjTypeList.get(world);
                                            DelayedStateChange.updateView(obj.zoom2d, param1, obj.yan2d, obj.xan2d);
                                            DelayedStateChange.method2745(param1, obj.zAngle2D, obj.yof2d, obj.xof2d);
                                        } else if (world == -1) {
                                            com.modelType = 0;
                                            opcode = -1;
                                            return true;
                                        } else {
                                            obj = ObjTypeList.get(world);
                                            com.modelXAngle = obj.xan2d;
                                            com.modelZoom = obj.zoom2d * 100 / ii;
                                            com.modelType = 4;
                                            com.modelId = world;
                                            com.modelYAngle = obj.yan2d;
                                            ComponentList.redraw(com);
                                        }
                                    }
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 105) {
                                    ii = inboundBuffer.g4();
                                    param1 = inboundBuffer.g2();
                                    if (ii < -70000) {
                                        param1 += 32768;
                                    }
                                    if (ii >= 0) {
                                        component = ComponentList.getComponent(ii);
                                    } else {
                                        component = null;
                                    }
                                    if (component != null) {
                                        for (slot = 0; slot < component.invSlotObjId.length; slot++) {
                                            component.invSlotObjId[slot] = 0;
                                            component.invSlotObjCount[slot] = 0;
                                        }
                                    }
                                    Inv.clearInventory(param1);
                                    slot = inboundBuffer.g2();
                                    for (count = 0; count < slot; count++) {
                                        i = inboundBuffer.g1_alt3();
                                        if (i == 255) {
                                            i = inboundBuffer.g4();
                                        }
                                        chatFlags = inboundBuffer.g2();
                                        if (component != null && count < component.invSlotObjId.length) {
                                            component.invSlotObjId[count] = chatFlags;
                                            component.invSlotObjCount[count] = i;
                                        }
                                        Inv.update(chatFlags - 1, count, i, param1);
                                    }
                                    if (component != null) {
                                        ComponentList.redraw(component);
                                    }
                                    ComponentList.redrawActiveInterfaces();
                                    Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & 0x1F] = param1 & 0x7FFF;
                                    opcode = -1;
                                    return true;
                                } else if (opcode == 142) {
                                    // Called removed method3954, stored username to cookie
                                    //method3954(inboundBuffer.gjstr());
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
                                    param1 = inboundBuffer.g2le();
                                    if (param1 == 65535) {
                                        param1 = -1;
                                    }
                                    MusicPlayer.playJingle(ii, param1);
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
                npc.lastSeenLoop = Client.loop;
            } else {
                @Pc(92) int local92 = inboundBuffer.gBit(2);
                if (local92 == 0) {
                    NpcList.npcIds[NpcList.npcCount++] = id;
                    npc.lastSeenLoop = Client.loop;
                    extendedIds[extendedCount++] = id;
                } else {
                    @Pc(139) int local139;
                    @Pc(149) int local149;
                    if (local92 == 1) {
                        NpcList.npcIds[NpcList.npcCount++] = id;
                        npc.lastSeenLoop = Client.loop;
                        local139 = inboundBuffer.gBit(3);
                        npc.move(1, local139);
                        local149 = inboundBuffer.gBit(1);
                        if (local149 == 1) {
                            extendedIds[extendedCount++] = id;
                        }
                    } else if (local92 == 2) {
                        NpcList.npcIds[NpcList.npcCount++] = id;
                        npc.lastSeenLoop = Client.loop;
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
            if (NpcList.npcs[removedId].lastSeenLoop != Client.loop) {
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
                    local317 = new ProjectileAnimation(local45, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local218, Client.loop + local228, local232 + Client.loop, local236, local247, local39, local224);
                    local317.setTarget(local31, Client.loop + local228, -local224 + SceneGraph.getTileHeight(Player.plane, local27, local31), local27);
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
                    @Pc(427) SpotAnim local427 = new SpotAnim(local27, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local31, local39, Client.loop);
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
                SceneGraph.attachLocToTile(Player.plane, local39, local31, local19, local45, local23, local218);
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
                        local317 = new ProjectileAnimation(local45, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local218, local228 + Client.loop, local232 + Client.loop, local236, local247, local39, local224);
                        local317.setTarget(local31, Client.loop + local228, SceneGraph.getTileHeight(Player.plane, local27, local31) - local224, local27);
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
                                    @Pc(1269) int local1269 = MathUtils.sin[local1198.orientation];
                                    @Pc(1274) int local1274 = MathUtils.cos[local1198.orientation];
                                    @Pc(1284) int local1284 = local1188 * local1274 + local1264 * local1269 >> 16;
                                    @Pc(1295) int local1295 = local1274 * local1264 - local1188 * local1269 >> 16;
                                    local19 += local1295;
                                    local23 += local1284;
                                }
                            }
                        }
                        @Pc(1331) ProjectileAnimation local1331 = new ProjectileAnimation(local218, Player.plane, local23, local19, SceneGraph.getTileHeight(Player.plane, local23, local19) - local224, local232 + Client.loop, local236 + Client.loop, local247, local633, local45, local228);
                        local1331.setTarget(local31, local232 + Client.loop, -local228 + SceneGraph.getTileHeight(Player.plane, local27, local31), local27);
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
    public static void readRebuildPacket(@OriginalArg(1) boolean isDynamic) {
        SceneGraph.dynamicMapRegion = isDynamic;
        @Pc(13) int playerPlane;
        @Pc(20) int regionCount;
        @Pc(26) int local26;
        @Pc(31) int local31;
        @Pc(60) int playerZ;
        @Pc(64) int local64;
        @Pc(138) int chunkX;
        @Pc(151) int chunkZ;
        @Pc(169) int regionId;
        if (!SceneGraph.dynamicMapRegion) {
            playerPlane = inboundBuffer.g2sub();
            regionCount = (packetSize - inboundBuffer.offset) / 16;
            WorldLoader.regionsXteaKeys = new int[regionCount][4];
            for (local26 = 0; local26 < regionCount; local26++) {
                for (local31 = 0; local31 < 4; local31++) {
                    WorldLoader.regionsXteaKeys[local26][local31] = inboundBuffer.p4rme();
                }
            }
            local26 = inboundBuffer.g1_alt3();
            local31 = inboundBuffer.g2();
            playerZ = inboundBuffer.g2sub();
            local64 = inboundBuffer.g2sub();
            WorldLoader.regionBitPacked = new int[regionCount];
            WorldLoader.mapFilesBuffer = new byte[regionCount][];
            WorldLoader.npcSpawnsFilesBuffer = null;
            WorldLoader.underWaterMapFileIds = new int[regionCount];
            WorldLoader.locationMapFilesBuffer = new byte[regionCount][];
            WorldLoader.underWaterLocationsMapFilesBuffer = new byte[regionCount][];
            WorldLoader.npcSpawnsFileIds = null;
            WorldLoader.mapFileIds = new int[regionCount];
            WorldLoader.underWaterMapFilesBuffer = new byte[regionCount][];
            WorldLoader.locationsMapFileIds = new int[regionCount];
            WorldLoader.underWaterLocationsMapFileIds = new int[regionCount];
            regionCount = 0;
            @Pc(100) boolean isTutorialIsland = false;
            if ((local31 / 8 == 48 || local31 / 8 == 49) && playerZ / 8 == 48) {
                isTutorialIsland = true;
            }
            if (local31 / 8 == 48 && playerZ / 8 == 148) {
                isTutorialIsland = true;
            }
            for (chunkX = (local31 - 6) / 8; chunkX <= (local31 + 6) / 8; chunkX++) {
                for (chunkZ = (playerZ - 6) / 8; chunkZ <= (playerZ + 6) / 8; chunkZ++) {
                    regionId = (chunkX << 8) + chunkZ;
                    if (isTutorialIsland && (chunkZ == 49 || chunkZ == 149 || chunkZ == 147 || chunkX == 50 || chunkX == 49 && chunkZ == 47)) {
                        WorldLoader.regionBitPacked[regionCount] = regionId;
                        WorldLoader.mapFileIds[regionCount] = -1;
                        WorldLoader.locationsMapFileIds[regionCount] = -1;
                        WorldLoader.underWaterMapFileIds[regionCount] = -1;
                        WorldLoader.underWaterLocationsMapFileIds[regionCount] = -1;
                    } else {
                        WorldLoader.regionBitPacked[regionCount] = regionId;
                        WorldLoader.mapFileIds[regionCount] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { WorldLoader.m, JString.parseInt(chunkX), WorldLoader.UNDERSCORE, JString.parseInt(chunkZ) }));
                        WorldLoader.locationsMapFileIds[regionCount] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { WorldLoader.l, JString.parseInt(chunkX), WorldLoader.UNDERSCORE, JString.parseInt(chunkZ) }));
                        WorldLoader.underWaterMapFileIds[regionCount] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.um, JString.parseInt(chunkX), WorldLoader.UNDERSCORE, JString.parseInt(chunkZ) }));
                        WorldLoader.underWaterLocationsMapFileIds[regionCount] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.ul, JString.parseInt(chunkX), WorldLoader.UNDERSCORE, JString.parseInt(chunkZ) }));
                    }
                    regionCount++;
                }
            }
            WorldLoader.initializeMapRegion(local26, playerZ, local31, local64, false, playerPlane);
            return;
        }
        playerPlane = inboundBuffer.g2leadd();
        regionCount = inboundBuffer.g2leadd();
        local26 = inboundBuffer.g1_alt3();
        local31 = inboundBuffer.g2leadd();
        inboundBuffer.accessBits();
        @Pc(391) int local391;
        for (playerZ = 0; playerZ < 4; playerZ++) {
            for (local64 = 0; local64 < 13; local64++) {
                for (local391 = 0; local391 < 13; local391++) {
                    chunkX = inboundBuffer.gBit(1);
                    if (chunkX == 1) {
                        dynamicRegionData[playerZ][local64][local391] = inboundBuffer.gBit(26);
                    } else {
                        dynamicRegionData[playerZ][local64][local391] = -1;
                    }
                }
            }
        }
        inboundBuffer.accessBytes();
        playerZ = (packetSize - inboundBuffer.offset) / 16;
        WorldLoader.regionsXteaKeys = new int[playerZ][4];
        for (local64 = 0; local64 < playerZ; local64++) {
            for (local391 = 0; local391 < 4; local391++) {
                WorldLoader.regionsXteaKeys[local64][local391] = inboundBuffer.p4rme();
            }
        }
        local64 = inboundBuffer.g2();
        WorldLoader.underWaterLocationsMapFileIds = new int[playerZ];
        WorldLoader.locationsMapFileIds = new int[playerZ];
        WorldLoader.mapFileIds = new int[playerZ];
        WorldLoader.underWaterLocationsMapFilesBuffer = new byte[playerZ][];
        WorldLoader.npcSpawnsFileIds = null;
        WorldLoader.underWaterMapFileIds = new int[playerZ];
        WorldLoader.locationMapFilesBuffer = new byte[playerZ][];
        WorldLoader.mapFilesBuffer = new byte[playerZ][];
        WorldLoader.regionBitPacked = new int[playerZ];
        WorldLoader.npcSpawnsFilesBuffer = null;
        WorldLoader.underWaterMapFilesBuffer = new byte[playerZ][];
        playerZ = 0;
        for (local391 = 0; local391 < 4; local391++) {
            for (chunkX = 0; chunkX < 13; chunkX++) {
                for (chunkZ = 0; chunkZ < 13; chunkZ++) {
                    regionId = dynamicRegionData[local391][chunkX][chunkZ];
                    if (regionId != -1) {
                        @Pc(555) int local555 = regionId >> 14 & 0x3FF;
                        @Pc(561) int local561 = regionId >> 3 & 0x7FF;
                        @Pc(571) int local571 = local561 / 8 + (local555 / 8 << 8);
                        @Pc(573) int local573;
                        for (local573 = 0; local573 < playerZ; local573++) {
                            if (local571 == WorldLoader.regionBitPacked[local573]) {
                                local571 = -1;
                                break;
                            }
                        }
                        if (local571 != -1) {
                            WorldLoader.regionBitPacked[playerZ] = local571;
                            @Pc(609) int local609 = local571 & 0xFF;
                            local573 = local571 >> 8 & 0xFF;
                            WorldLoader.mapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { WorldLoader.m, JString.parseInt(local573), WorldLoader.UNDERSCORE, JString.parseInt(local609) }));
                            WorldLoader.locationsMapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { WorldLoader.l, JString.parseInt(local573), WorldLoader.UNDERSCORE, JString.parseInt(local609) }));
                            WorldLoader.underWaterMapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.um, JString.parseInt(local573), WorldLoader.UNDERSCORE, JString.parseInt(local609) }));
                            WorldLoader.underWaterLocationsMapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.ul, JString.parseInt(local573), WorldLoader.UNDERSCORE, JString.parseInt(local609) }));
                            playerZ++;
                        }
                    }
                }
            }
        }
        WorldLoader.initializeMapRegion(local26, local64, regionCount, local31, false, playerPlane);
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
            player.addHit(staffModLevel, Client.loop, chatFlags);
            player.hitpointsBarVisibleUntil = Client.loop + 300;
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
            player.anInt3395 = inboundBuffer.g2le() + Client.loop;
            player.anInt3386 = inboundBuffer.g2le() + Client.loop;
            player.anInt3431 = inboundBuffer.p1neg();
            player.movementQueueSize = 1;
            player.movementQueueSnapshot = 0;
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
            player.addHit(staffModLevel, Client.loop, chatFlags);
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
            Player.updateLayeredAnimations(local505, local502, player, local508);
        }
        if ((flags & 0x100) != 0) {
            chatFlags = inboundBuffer.g2le();
            if (chatFlags == 65535) {
                chatFlags = -1;
            }
            staffModLevel = inboundBuffer.p4rme();
            @Pc(573) boolean local573 = true;
            if (chatFlags != -1 && player.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(chatFlags).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(player.spotAnimId).seqId).priority) {
                local573 = false;
            }
            if (local573) {
                player.spotAnimStart = (staffModLevel & 0xFFFF) + Client.loop;
                player.anInt3361 = 0;
                player.spotanimId = 0;
                player.spotAnimId = chatFlags;
                if (player.spotAnimStart > Client.loop) {
                    player.spotanimId = -1;
                }
                player.spotAnimY = staffModLevel >> 16;
                player.anInt3418 = 1;
                if (player.spotAnimId != -1 && Client.loop == player.spotAnimStart) {
                    local24 = SpotAnimTypeList.get(player.spotAnimId).seqId;
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
            if (Client.loop != PlayerList.players[index].lastSeenLoop) {
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
                    player.lastSeenLoop = Client.loop;
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
                        player.dstYaw = player.orientation = local99;
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
                npc.addHit(info, Client.loop, local43);
                npc.hitpointsBarVisibleUntil = Client.loop + 300;
                npc.hitpointsBar = inboundBuffer.g1_alt3();
            }
            if ((local18 & 0x2) != 0) {
                local43 = inboundBuffer.p1neg(); // Hit value
                info = inboundBuffer.g1_alt3(); // Color
                npc.addHit(info, Client.loop, local43);
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
                if (local43 != -1 && npc.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(local43).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(npc.spotAnimId).seqId).priority) {
                    local147 = false;
                }
                if (local147) {
                    npc.spotAnimId = local43;
                    npc.spotAnimStart = (info & 0xFFFF) + Client.loop;
                    npc.anInt3361 = 0;
                    npc.spotanimId = 0;
                    npc.spotAnimY = info >> 16;
                    npc.anInt3418 = 1;
                    if (npc.spotAnimStart > Client.loop) {
                        npc.spotanimId = -1;
                    }
                    if (npc.spotAnimId != -1 && npc.spotAnimStart == Client.loop) {
                        @Pc(227) int seqId = SpotAnimTypeList.get(npc.spotAnimId).seqId;
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
                npc.anInt3365 = npc.type.nas;
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
                    npc.lastSeenLoop = Client.loop;
                    if (npc.type != null && npc.type.hasAreaSound()) {
                        AreaSoundManager.remove(npc);
                    }
                    @Pc(66) int local66 = inboundBuffer.gBit(1);
                    @Pc(73) int angle = PathingEntity.ANGLES[inboundBuffer.gBit(3)];
                    if (local19) {
                        npc.dstYaw = npc.orientation = angle;
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
                    npc.anInt3365 = npc.type.nas;
                    npc.anInt3376 = npc.type.turnspeed;
                    if (npc.anInt3376 == 0) {
                        npc.orientation = 0;
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
        if (Client.uid != null) {
            try {
                Client.uid.seek(0L);
                Client.uid.write(arg0.data, arg0.offset, 24);
            } catch (@Pc(16) Exception ignored) {
            }
        }
        arg0.offset += 24;
    }

    @OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!wa;I)Lclient!na;")
    public static JString formatChatMessage(@OriginalArg(0) Packet arg0) {
        return WordPack.readStringInternal(arg0);
    }

    @OriginalMember(owner = "runetek4.client!mi", name = "a", descriptor = "([IBLclient!km;[I[I)V")
    public static void method3037(@OriginalArg(0) int[] arg0, @OriginalArg(2) Npc arg1, @OriginalArg(3) int[] arg2, @OriginalArg(4) int[] arg3) {
        for (@Pc(3) int local3 = 0; local3 < arg3.length; local3++) {
            @Pc(15) int local15 = arg3[local3];
            @Pc(19) int local19 = arg0[local3];
            @Pc(23) int local23 = arg2[local3];
            for (@Pc(25) int local25 = 0; local19 != 0 && arg1.layeredAnimations.length > local25; local25++) {
                if ((local19 & 0x1) != 0) {
                    if (local15 == -1) {
                        arg1.layeredAnimations[local25] = null;
                    } else {
                        @Pc(60) SeqType local60 = SeqTypeList.get(local15);
                        @Pc(65) PathingEntityAnimation local65 = arg1.layeredAnimations[local25];
                        @Pc(68) int local68 = local60.exactmove;
                        if (local65 != null) {
                            if (local15 == local65.sequenceId) {
                                if (local68 == 0) {
                                    local65 = arg1.layeredAnimations[local25] = null;
                                } else if (local68 == 1) {
                                    local65.frameIndex = 0;
                                    local65.frameTime = 0;
                                    local65.direction = 1;
                                    local65.loopCount = 0;
                                    local65.delay = local23;
                                    SoundPlayer.playSeqSound(arg1.zFine, local60, arg1.xFine, false, 0);
                                } else if (local68 == 2) {
                                    local65.frameTime = 0;
                                }
                            } else if (local60.priority >= SeqTypeList.get(local65.sequenceId).priority) {
                                local65 = arg1.layeredAnimations[local25] = null;
                            }
                        }
                        if (local65 == null) {
                            local65 = arg1.layeredAnimations[local25] = new PathingEntityAnimation();
                            local65.direction = 1;
                            local65.loopCount = 0;
                            local65.delay = local23;
                            local65.sequenceId = local15;
                            local65.frameTime = 0;
                            local65.frameIndex = 0;
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
                npc.animationDirection = 1;
                npc.animationFrameDelay = 0;
                npc.animationFrame = 0;
                npc.animationLoopCounter = 0;
                npc.animationDelay = arg0;
                SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, npc.animationFrameDelay);
            }
            if (local13 == 2) {
                npc.animationLoopCounter = 0;
            }
        } else if (animationId == -1 || npc.primarySeqId == -1 || SeqTypeList.get(animationId).priority >= SeqTypeList.get(npc.primarySeqId).priority) {
            npc.animationFrame = 0;
            npc.primarySeqId = animationId;
            npc.animationDirection = 1;
            npc.animationLoopCounter = 0;
            npc.animationDelay = arg0;
            npc.movementQueueSnapshot = npc.movementQueueSize;
            npc.animationFrameDelay = 0;
            if (npc.primarySeqId != -1) {
                SoundPlayer.playSeqSound(npc.zFine, SeqTypeList.get(npc.primarySeqId), npc.xFine, false, npc.animationFrameDelay);
            }
        }
    }

    @OriginalMember(owner = "client!dh", name = "a", descriptor = "(IIII)Lclient!wk;")
    public static SubInterface openSubInterface(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
        @Pc(9) SubInterface local9 = new SubInterface();
        local9.modalType = arg2;
        local9.interfaceId = arg0;
        ComponentList.openInterfaces.put(local9, arg1);
        ComponentList.resetComponentAnimations(arg0);
        @Pc(28) Component local28 = ComponentList.getComponent(arg1);
        if (local28 != null) {
            ComponentList.redraw(local28);
        }
        if (ClientScriptRunner.modalBackgroundComponent != null) {
            ComponentList.redraw(ClientScriptRunner.modalBackgroundComponent);
            ClientScriptRunner.modalBackgroundComponent = null;
        }
        @Pc(45) int local45 = MiniMenu.menuActionRow;
        @Pc(53) int local53;
        for (local53 = 0; local53 < local45; local53++) {
            if (ComponentList.shouldRemoveMenuAction(MiniMenu.actions[local53])) {
                MiniMenu.removeActionRow(local53);
            }
        }
        if (MiniMenu.menuActionRow == 1) {
            ClientScriptRunner.menuVisible = false;
            ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
        } else {
            ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
            local53 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(95) int local95 = 0; local95 < MiniMenu.menuActionRow; local95++) {
                @Pc(104) int local104 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(local95));
                if (local104 > local53) {
                    local53 = local104;
                }
            }
            ComponentList.menuWidth = local53 + 8;
            ComponentList.menuHeight = MiniMenu.menuActionRow * 15 + (ComponentList.hasScrollbar ? 26 : 22);
        }
        if (local28 != null) {
            ComponentList.updateContainerLayout(local28, false);
        }
        ComponentList.runInterfaceInitScripts(arg0);
        if (ComponentList.topLevelInterface != -1) {
            ComponentList.runScripts(1, ComponentList.topLevelInterface);
        }
        return local9;
    }

    @OriginalMember(owner = "client!ah", name = "b", descriptor = "(I)V")
    public static void method843() {
        if (ComponentList.clickedInventoryComponent != null || ClientScriptRunner.dragComponent != null) {
            return;
        }
        @Pc(20) int local20 = Mouse.clickButton;
        @Pc(93) int local93;
        @Pc(99) int local99;
        if (!ClientScriptRunner.menuVisible) {
            if (local20 == 1 && MiniMenu.menuActionRow > 0) {
                @Pc(37) short local37 = MiniMenu.actions[MiniMenu.menuActionRow - 1];
                if (local37 == 25 || local37 == 23 || local37 == 48 || local37 == 7 || local37 == 13 || local37 == 47 || local37 == 5 || local37 == 43 || local37 == 35 || local37 == 58 || local37 == 22 || local37 == 1006) {
                    local93 = MiniMenu.intArgs1[MiniMenu.menuActionRow - 1];
                    local99 = MiniMenu.intArgs2[MiniMenu.menuActionRow - 1];
                    @Pc(103) Component local103 = ComponentList.getComponent(local99);
                    @Pc(106) ServerActiveProperties local106 = ComponentList.getServerActiveProperties(local103);
                    if (local106.isObjSwapEnabled() || local106.isObjReplaceEnabled()) {
                        ComponentList.lastItemDragTime = 0;
                        ComponentList.draggingClickedInventoryObject = false;
                        if (ComponentList.clickedInventoryComponent != null) {
                            ComponentList.redraw(ComponentList.clickedInventoryComponent);
                        }
                        ComponentList.clickedInventoryComponent = ComponentList.getComponent(local99);
                        ComponentList.clickedInventoryComponentX = Mouse.mouseClickX;
                        ComponentList.clickedInventoryComponentY = Mouse.mouseClickY;
                        ComponentList.selectedInventorySlot = local93;
                        ComponentList.redraw(ComponentList.clickedInventoryComponent);
                        return;
                    }
                }
            }
            if (local20 == 1 && (VarpDomain.oneMouseButton == 1 && MiniMenu.menuActionRow > 2 || MiniMenu.isComponentAction(MiniMenu.menuActionRow - 1))) {
                local20 = 2;
            }
            if (local20 == 2 && MiniMenu.menuActionRow > 0 || MiniMenu.menuState == 1) {
                ClientScriptRunner.determineMenuSize();
            }
            if (local20 == 1 && MiniMenu.menuActionRow > 0 || MiniMenu.menuState == 2) {
                MiniMenu.processMenuActions();
            }
            return;
        }
        @Pc(204) int local204;
        if (local20 != 1) {
            local93 = Mouse.lastMouseY;
            local204 = Mouse.lastMouseX;
            if (local204 < ComponentList.menuX - 10 || local204 > ComponentList.menuWidth + ComponentList.menuX + 10 || ComponentList.menuY - 10 > local93 || local93 > ComponentList.menuHeight + ComponentList.menuY + 10) {
                ClientScriptRunner.menuVisible = false;
                ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
            }
        }
        if (local20 != 1) {
            return;
        }
        local204 = ComponentList.menuX;
        local93 = ComponentList.menuY;
        local99 = ComponentList.menuWidth;
        @Pc(265) int local265 = Mouse.mouseClickX;
        @Pc(267) int local267 = Mouse.mouseClickY;
        @Pc(269) int local269 = -1;
        for (@Pc(271) int local271 = 0; local271 < MiniMenu.menuActionRow; local271++) {
            @Pc(289) int local289;
            if (ComponentList.hasScrollbar) {
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
        ClientScriptRunner.menuVisible = false;
        ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
    }
}
