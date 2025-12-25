package com.jagex.runetek4.network;

import com.jagex.runetek4.*;
import com.jagex.runetek4.client.auth.LoginManager;
import com.jagex.runetek4.entity.PathingEntity;
import com.jagex.runetek4.entity.PathingEntityAnimation;
import com.jagex.runetek4.entity.npc.Npc;
import com.jagex.runetek4.entity.npc.NpcList;
import com.jagex.runetek4.entity.player.Player;
import com.jagex.runetek4.entity.player.PlayerList;
import com.jagex.runetek4.entity.player.PlayerSkillXpTable;
import com.jagex.runetek4.game.logic.CollisionConstants;
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
import com.jagex.runetek4.clientscript.ClientServerStateSync;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.ui.component.Component;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.config.types.spotanim.SpotAnimTypeList;
import com.jagex.runetek4.core.datastruct.LinkList;
import com.jagex.runetek4.core.exceptions.TracingException;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.config.types.bas.BasType;
import com.jagex.runetek4.config.types.quickchat.QuickChatPhraseType;
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
import com.jagex.runetek4.entity.loc.ClientObj;
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
import java.util.Objects;

import static com.jagex.runetek4.game.GameConstants.*;
import static com.jagex.runetek4.game.logic.CollisionConstants.*;
import static com.jagex.runetek4.game.world.CoordinateConstants.*;
import static com.jagex.runetek4.network.ClientProt.TRANSMITVAR_VERIFYID;
import static com.jagex.runetek4.network.ProtocolConstants.*;
import static com.jagex.runetek4.network.ProtocolConstants.DYNAMIC_REGION_BITS;
import static com.jagex.runetek4.network.ServerProt.*;

public class Protocol {

    @OriginalMember(owner = "client!ag", name = "P", descriptor = "Lclient!i;")
    public static final PacketBit outboundBuffer = new PacketBit(OUTBOUND_BUFFER_SIZE);

    @OriginalMember(owner = "runetek4.client!eg", name = "e", descriptor = "Lclient!i;")
    public static final PacketBit inboundBuffer = new PacketBit(INBOUND_BUFFER_SIZE);

    @OriginalMember(owner = "runetek4.client!ef", name = "f", descriptor = "Lclient!na;")
    public static final JString DUELSTAKE = JString.parse(":duelstake:");

    @OriginalMember(owner = "client!en", name = "h", descriptor = "Lclient!na;")
    public static final JString CHALREQ = JString.parse(":chalreq:");

    @OriginalMember(owner = "runetek4.client!wb", name = "f", descriptor = "Lclient!wa;")
    public static final Packet CHAT_PACKET = new Packet(new byte[CHAT_PACKET_SIZE]);

    @OriginalMember(owner = "client!eb", name = "p", descriptor = "[I")
    public static final int[] removedIds = new int[MAX_REMOVED_IDS];

    @OriginalMember(owner = "client!dh", name = "d", descriptor = "[I")
    public static final int[] extendedIds = new int[MAX_EXTENDED_IDS];

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
    public static final int[][][] dynamicRegionData = new int[CollisionConstants.LEVELS][ProtocolConstants.BUILD_AREA_SIZE][ProtocolConstants.BUILD_AREA_SIZE];

    @OriginalMember(owner = "client!fc", name = "f", descriptor = "Lclient!na;")
    public static final JString IMG0 = JString.parse("<img=0>");

    @OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
    public static final int[] cameraModifierCycle = new int[CAMERA_MODIFIER_COUNT];

    @OriginalMember(owner = "runetek4.client!pg", name = "db", descriptor = "Lclient!na;")
    public static final JString ASSISTREQ = JString.parse(":assistreq:");

    @OriginalMember(owner = "runetek4.client!rj", name = "ab", descriptor = "Lclient!na;")
    public static final JString CLANREQ = JString.parse(":clanreq:");

    @OriginalMember(owner = "runetek4.client!na", name = "cb", descriptor = "Lclient!na;")
    public static final JString ALLYREQ = JString.parse(":allyreq:");

    @OriginalMember(owner = "client!dh", name = "i", descriptor = "Lclient!na;")
    public static final JString IMG1 = JString.parse("<img=1>");

    @OriginalMember(owner = "runetek4.client!qi", name = "t", descriptor = "I")
    public static int mouseIdleFrameCount = 0;

    @OriginalMember(owner = "runetek4.client!fe", name = "R", descriptor = "Z")
    public static boolean prevFocus = true;

    @OriginalMember(owner = "runetek4.client!dm", name = "q", descriptor = "I")
    public static int thirdLastOpcode = 0;

    @OriginalMember(owner = "runetek4.client!af", name = "k", descriptor = "I")
    public static int secondLastOpcode = 0;

    @OriginalMember(owner = "runetek4.client!na", name = "l", descriptor = "I")
    public static int currentOpcode = 0;

    @OriginalMember(owner = "runetek4.client!sj", name = "t", descriptor = "I")
    public static int previousOpcode = 0;

    @OriginalMember(owner = "runetek4.client!pm", name = "ab", descriptor = "Z")
    public static boolean shouldSendCameraPosition = true;

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
    public static int noTimeoutCycle = 0;

    @OriginalMember(owner = "runetek4.client!jk", name = "B", descriptor = "Lclient!ma;")
    public static BufferedSocket gameServerSocket;

    @OriginalMember(owner = "runetek4.client!dg", name = "h", descriptor = "Lclient!be;")
    public static Component dragHoverComponent;

    @OriginalMember(owner = "runetek4.client!kf", name = "l", descriptor = "I")
    public static int dragHoverAnimationProgress = 0;

    @OriginalMember(owner = "runetek4.client!t", name = "l", descriptor = "Lclient!ma;")
    public static BufferedSocket previousSocket;

    @OriginalMember(owner = "runetek4.client!sc", name = "o", descriptor = "I")
    public static int packetSize = 0;

    @OriginalMember(owner = "runetek4.client!pb", name = "ab", descriptor = "I")
	public static int walkRequestState = 0;

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
    public static int cameraPositionUpdateCooldown = 0;

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
                    @Pc(153) int direction;
                    @Pc(163) int hasExtendedInfo;
                    if (updateType == 1) {
                        PlayerList.playerIds[PlayerList.playerCount++] = playerId;
                        player.lastSeenLoop = Client.loop;
                        direction = inboundBuffer.gBit(3);
                        player.move(1, direction);
                        hasExtendedInfo = inboundBuffer.gBit(1);
                        if (hasExtendedInfo == 1) {
                            extendedIds[extendedCount++] = playerId;
                        }
                    } else if (updateType == 2) {
                        PlayerList.playerIds[PlayerList.playerCount++] = playerId;
                        player.lastSeenLoop = Client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            direction = inboundBuffer.gBit(3);
                            player.move(2, direction);
                            hasExtendedInfo = inboundBuffer.gBit(3);
                            player.move(2, hasExtendedInfo);
                        } else {
                            direction = inboundBuffer.gBit(3);
                            player.move(0, direction);
                        }
                        direction = inboundBuffer.gBit(1);
                        if (direction == 1) {
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
            @Pc(61) String message = "T2 - " + currentOpcode + "," + secondLastOpcode + "," + thirdLastOpcode + " - " + packetSize + "," + (Camera.sceneBaseTileX + PlayerList.self.movementQueueX[0]) + "," + (PlayerList.self.movementQueueZ[0] + Camera.sceneBaseTileZ) + " - ";
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
        if (currentOpcode == -1) {
            available--;
            gameServerSocket.read(0, 1, inboundBuffer.data);
            inboundBuffer.offset = 0;
            currentOpcode = inboundBuffer.gIssac1();
            packetSize = PACKET_LENGTHS[currentOpcode];
        }
        if (packetSize == -1) {
            if (available <= 0) {
                return false;
            }
            gameServerSocket.read(0, 1, inboundBuffer.data);
            available--;
            packetSize = inboundBuffer.data[0] & BYTE_MASK;
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
        thirdLastOpcode = secondLastOpcode;
        secondLastOpcode = previousOpcode;
        previousOpcode = currentOpcode;
        LoginManager.idleNetCycles = 0;
        @Pc(133) int ii;
        if (currentOpcode == VARP_SMALL) {
            // Update a player variable with a small value (< 128)
            ii = inboundBuffer.g2_alt2();
            @Pc(137) byte varpId = inboundBuffer.g1neg();
            VarpDomain.setVarpServer(varpId, ii);
            currentOpcode = -1;
            return true;
        }
        @Pc(171) int slot;
        @Pc(156) JString argTypes;
        if (currentOpcode == CLIENTSCRIPT_RUN) {
            // Execute a ClientScript with arguments passed from the server
            // Server sends Script ID, argument types and values
            int scriptId = inboundBuffer.g2();
            argTypes = inboundBuffer.gjstr();
            @Pc(163) Object[] scriptArgs = new Object[argTypes.length() + 1];
            for (slot = argTypes.length() - 1; slot >= 0; slot--) {
                if (argTypes.charAt(slot) == 115) { // 115 = string argument
                    scriptArgs[slot + 1] = inboundBuffer.gjstr();
                } else {
                    scriptArgs[slot + 1] = Integer.valueOf(inboundBuffer.g4());
                }
            }
            scriptArgs[0] = Integer.valueOf(inboundBuffer.g4());
            if (setVerifyID(scriptId)) {
                @Pc(226) ComponentEvent request = new ComponentEvent();
                request.arguments = scriptArgs;
                ClientScriptRunner.run(request);
            }
            currentOpcode = -1;
            return true;
        }
        @Pc(275) long username;
        @Pc(262) boolean ignored;
        @Pc(277) int i;
        @Pc(506) JString worldName;
        if (currentOpcode == MESSAGE_GAME) {
            // Game messages with special suffixes
            // Ex. :tradereq:
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
            currentOpcode = -1;
            return true;
        }
        @Pc(786) int param1;
        @Pc(790) JString messageText;
        if (currentOpcode == IF_SETTEXT) {
            // Set text on a interface component
            // Takes: component, verify_id, text
            ii = inboundBuffer.g2_al1();
            int verifyID = inboundBuffer.g2_alt2();
            messageText = inboundBuffer.gjstr();
            if (setVerifyID(verifyID)) {
                ClientServerStateSync.setComponentTextServer(messageText, ii);
            }
            currentOpcode = -1;
            return true;
        } else if (currentOpcode == MAP_UPDATE) {
            SceneGraph.currentChunkZ = inboundBuffer.g1_alt1();
            SceneGraph.currentChunkX = inboundBuffer.g1_alt3();
            while (packetSize > inboundBuffer.offset) {
                currentOpcode = inboundBuffer.g1();
                readZonePacket();
            }
            currentOpcode = -1;
            return true;
        } else if (currentOpcode == SCENE_RESET) {
            currentOpcode = -1;
            LoginManager.flagSceneTileX = 0;
            return true;
        } else {
            @Pc(864) int i2;
            if (currentOpcode == IF_SETCOMPONENT_PARAMS) {
                ii = inboundBuffer.p4rme();
                param1 = inboundBuffer.g2_al1();
                int verifyID = inboundBuffer.g2();
                if (setVerifyID(verifyID)) {
                    ClientServerStateSync.setComponentScrollPositionServer(param1, ii);
                }
                currentOpcode = -1;
                return true;
            }
            @Pc(884) long senderName;
            @Pc(908) int chatType;
            @Pc(916) int phraseId;
            @Pc(899) long messageId1;
            @Pc(904) long messageId2;
            if (currentOpcode == MESSAGE_QUICKCHAT_FRIEND) {
                // Quickchat message from friend
                senderName = inboundBuffer.g8();
                inboundBuffer.g1s();
                username = inboundBuffer.g8();
                messageId1 = inboundBuffer.g2();
                messageId2 = inboundBuffer.g3();
                chatType = inboundBuffer.g1();
                @Pc(910) boolean isDuplicate = false;
                phraseId = inboundBuffer.g2();
                @Pc(922) long combinedMessageId = (messageId1 << MESSAGE_ID_HIGH_SHIFT) + messageId2; // Combine parts into 64-bit unique message ID
                @Pc(924) int messageIndex = 0;
                label1320: while (true) {
                    if (messageIndex < MAX_RECENT_MESSAGES) {
                        if (combinedMessageId != Chat.recentMessages[messageIndex]) {
                            messageIndex++;
                            continue;
                        }
                        isDuplicate = true;
                        break;
                    }
                    if (chatType <= 1) {
                        for (messageIndex = 0; messageIndex < IgnoreList.ignoreCount; messageIndex++) {
                            if (IgnoreList.encodedIgnores[messageIndex] == senderName) {
                                isDuplicate = true;
                                break label1320;
                            }
                        }
                    }
                    break;
                }
                if (!isDuplicate && Player.inTutorialIsland == 0) {
                    Chat.recentMessages[Chat.messageCounter] = combinedMessageId;
                    Chat.messageCounter = (Chat.messageCounter + 1) % MAX_RECENT_MESSAGES;
                    @Pc(999) JString decodedMessage = QuickChatPhraseTypeList.get(phraseId).decodeMessage(inboundBuffer);
                    if (chatType == 2 || chatType == 3) {
                        Chat.add(phraseId, 20, decodedMessage, Base37.fromBase37(username).toTitleCase(), JString.concatenate(new JString[] { IMG1, Base37.fromBase37(senderName).toTitleCase() }));
                    } else if (chatType == 1) {
                        Chat.add(phraseId, 20, decodedMessage, Base37.fromBase37(username).toTitleCase(), JString.concatenate(new JString[] { IMG0, Base37.fromBase37(senderName).toTitleCase() }));
                    } else {
                        Chat.add(phraseId, 20, decodedMessage, Objects.requireNonNull(Base37.fromBase37(username)).toTitleCase(), Base37.fromBase37(senderName).toTitleCase());
                    }
                }
                currentOpcode = -1;
                return true;
            }
            @Pc(1146) int count;
            @Pc(1160) int chatFlags;
            @Pc(1245) boolean isSorted;
            if (currentOpcode == CLANCHAT_CHANNEL) {
                // Complete clan chat channel data
                ClanChat.transmitAt = ComponentList.transmitTimer;
                senderName = inboundBuffer.g8();
                if (senderName == 0L) {
                    ClanChat.owner = null;
                    currentOpcode = -1;
                    ClanChat.name = null;
                    ClanChat.members = null;
                    ClanChat.size = 0;
                    return true;
                }
                username = inboundBuffer.g8();
                ClanChat.name = Base37.fromBase37(username);
                ClanChat.owner = Base37.fromBase37(senderName);
                ClanChat.minKick = inboundBuffer.g1s();
                count = inboundBuffer.g1();
                if (count == EXTENDED_COUNT_MARKER) {
                    // Extended member list follows in separate packet
                    currentOpcode = -1;
                    return true;
                }
                ClanChat.size = count;
                @Pc(1158) ClanMember[] clanMembers = new ClanMember[MAX_CLAN_MEMBERS];
                for (chatFlags = 0; chatFlags < ClanChat.size; chatFlags++) {
                    clanMembers[chatFlags] = new ClanMember();
                    clanMembers[chatFlags].nodeId = inboundBuffer.g8();
                    clanMembers[chatFlags].username = Base37.fromBase37(clanMembers[chatFlags].nodeId);
                    clanMembers[chatFlags].world = inboundBuffer.g2();
                    clanMembers[chatFlags].rank = inboundBuffer.g1s();
                    clanMembers[chatFlags].worldName = inboundBuffer.gjstr();
                    if (Player.name37 == clanMembers[chatFlags].nodeId) {
                        ClanChat.rank = clanMembers[chatFlags].rank;
                    }
                }
                chatType = ClanChat.size;
                // Alphabetically sort clan members by name
                while (chatType > 0) {
                    isSorted = true;
                    chatType--;
                    for (phraseId = 0; phraseId < chatType; phraseId++) {
                        if (clanMembers[phraseId].username.method3139(clanMembers[phraseId + 1].username) > 0) {
                            isSorted = false;
                            @Pc(1279) ClanMember clanMember = clanMembers[phraseId];
                            clanMembers[phraseId] = clanMembers[phraseId + 1];
                            clanMembers[phraseId + 1] = clanMember;
                        }
                    }
                    if (isSorted) {
                        break;
                    }
                }
                ClanChat.members = clanMembers;
                currentOpcode = -1;
                return true;
            } else if (currentOpcode == LAST_LOGIN_INFO) {
                // LAST_LOGIN_INFO
                // Last IP Adress
                ii = inboundBuffer.g4rme();
                Player.lastLogAddress = GameShell.signLink.getReverseDns(ii);
                currentOpcode = -1;
                return true;
            } else if (currentOpcode == PLAYER_INFO) {
                // PLAYER_INFO
                // Player entity updates
                readPlayerInfoPacket();
                currentOpcode = -1;
                return true;
            } else if (currentOpcode == IF_SETTEXT_ALT) {
                // Alternative format for setting text on component
                int verifyID = inboundBuffer.g2();
                argTypes = inboundBuffer.gjstr();
                i2 = inboundBuffer.g2_alt3(); // Player option slot
                if (setVerifyID(verifyID)) {
                    ClientServerStateSync.setComponentTextServer(argTypes, i2);
                }
                currentOpcode = -1;
                return true;
            } else if (currentOpcode == CHAT_FILTER_SETTINGS) {
                // Update chat filter settings
                Chat.publicFilter = inboundBuffer.g1();
                Chat.privateFilter = inboundBuffer.g1();
                Chat.tradeFilter = inboundBuffer.g1();
                currentOpcode = -1;
                return true;
            } else {
                @Pc(1409) JString message_text;
                if (currentOpcode == SET_PLAYER_OPTION) {
                    // Sets option text, cursor type, right-click options on players
                    ii = inboundBuffer.g2_alt3();
                    if (ii == INVALID_ID_U16) {
                        ii = -1;
                    }
                    param1 = inboundBuffer.g1();
                    i2 = inboundBuffer.g1();
                    message_text = inboundBuffer.gjstr();
                    if (i2 >= 1 && i2 <= MAX_PLAYER_OPTIONS) {
                        if (message_text.equalsIgnoreCase(MiniMenu.NULL)) {
                            message_text = null;
                        }
                        Player.options[i2 - 1] = message_text;
                        Player.cursors[i2 - 1] = ii;
                        Player.secondaryOptions[i2 - 1] = param1 == 0;
                    }
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == VARP_LARGE) {
                    // Update a player variable with large ID (> 255)
                    ii = inboundBuffer.g4();
                    param1 = inboundBuffer.g2_alt2();
                    VarpDomain.setVarpServer(ii, param1);
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == IF_SETCOMPONENT_PARAMS2) {
                    ii = inboundBuffer.g1_alt2();
                    int verifyID = inboundBuffer.g2();
                    i2 = inboundBuffer.g4me();
                    if (setVerifyID(verifyID)) {
                        ClientServerStateSync.setComponentHiddenServer(i2, ii);
                    }
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == IF_OPENTOP) {
                    // IF_OPENTOP
                    // Open a modal
                    // Replaces current main interface
                    // Optionally reset world map
                    int parent = inboundBuffer.g2_alt3();
                    int reset = inboundBuffer.g1_alt1();
                    int verifyID = inboundBuffer.g2_alt3();
                    if (setVerifyID(verifyID)) {
                        if (reset == 2) {
                            WorldMap.reset();
                        }
                        ComponentList.topLevelInterface = parent;
                        ComponentList.resetComponentAnimations(parent);
                        ComponentList.updateInterfaceLayout(false);
                        ComponentList.runInterfaceInitScripts(ComponentList.topLevelInterface);
                        for (slot = 0; slot < MAX_COMPONENT_REDRAW_SLOTS; slot++) {
                            ComponentList.componentNeedsRedraw[slot] = true;
                        }
                    }
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == VARC_SMALL) {
                    // Update client variable
                    int verifyID = inboundBuffer.g2_alt3();
                    param1 = inboundBuffer.g4();
                    i2 = inboundBuffer.g2_alt2(); // VarC ID
                    if (setVerifyID(verifyID)) {
                        ClientServerStateSync.updateVarC(i2, param1);
                    }
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == MESSAGE_QUICKCHAT_CLANCHAT) {
                    // Quickchat message in clan chat
                    senderName = inboundBuffer.g8();
                    i2 = inboundBuffer.g2(); // Quickchat phrase ID
                    message_text = QuickChatPhraseTypeList.get(i2).decodeMessage(inboundBuffer);
                    Chat.add(i2, 19, message_text, null, Base37.fromBase37(senderName).toTitleCase());
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == RANDOM_VERIFY) {
                    // Anti botting verification
                    writeRandom(inboundBuffer);
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == RESET_ANIMS) {
                    // Reset varbits and trigger interface redraw
                    VarpDomain.resetVarBits();
                    ComponentList.redrawActiveInterfaces();
                    VarpDomain.updatedVarpsWriterIndex += 32;
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == CAM_LOOKAT) {
                    // Set camera to look at a specific coordinate
                    int verifyID = inboundBuffer.g2();
                    param1 = inboundBuffer.g1();
                    i2 = inboundBuffer.g1();
                    slot = inboundBuffer.g2();
                    count = inboundBuffer.g1();
                    i = inboundBuffer.g1();
                    if (setVerifyID(verifyID)) {
                        Camera.setCameraLookAtTarget(slot, i2, count, param1, i);
                    }
                    currentOpcode = -1;
                    return true;
                } else if (currentOpcode == IF_SETCOMPONENT_PARAMS3) {
                    ii = inboundBuffer.p4rme();
                    param1 = inboundBuffer.g2les();
                    int verifyID = inboundBuffer.g2_alt2();
                    if (setVerifyID(verifyID)) {
                        ClientServerStateSync.setComponentModelAnimServer(ii, param1);
                    }
                    currentOpcode = -1;
                    return true;
                } else {
                    @Pc(1814) ServerActiveProperties activeProperties1;
                    @Pc(1804) ServerActiveProperties activeProperties2;
                    if (currentOpcode == IF_SETEVENTS) {
                        // Set which events a component can recieve
                        // ex. mouse clicks, dragging, key presses
                        ii = inboundBuffer.g2_alt3();
                        param1 = inboundBuffer.g4me();
                        int verifyID = inboundBuffer.g2_alt2();
                        slot = inboundBuffer.g2_al1();
                        if (slot == INVALID_ID_U16) {
                            slot = -1;
                        }
                        count = inboundBuffer.g2_alt2();
                        if (count == INVALID_ID_U16) {
                            count = -1;
                        }
                        if (setVerifyID(verifyID)) {
                            for (i = count; i <= slot; i++) {
                                messageId2 = (long) i + ((long) param1 << MESSAGE_ID_HIGH_SHIFT); // Combine component ID + slot into a unique key
                                activeProperties2 = (ServerActiveProperties) ComponentList.properties.get(messageId2);
                                if (activeProperties2 != null) {
                                    activeProperties1 = new ServerActiveProperties(activeProperties2.events, ii);
                                    activeProperties2.unlink();
                                } else if (i == -1) {
                                    activeProperties1 = new ServerActiveProperties(ComponentList.getComponent(param1).properties.events, ii);
                                } else {
                                    activeProperties1 = new ServerActiveProperties(0, ii);
                                }
                                ComponentList.properties.put(activeProperties1, messageId2);
                            }
                        }
                        currentOpcode = -1;
                        return true;
                    }
                    @Pc(1986) int j;
                    if (currentOpcode == SPOTANIM_ENTITY) {
                        // Play a spot animation on entity or coordinate
                        ii = inboundBuffer.g2();
                        param1 = inboundBuffer.g2_al1();
                        i2 = inboundBuffer.g4rme();
                        slot = inboundBuffer.g2_alt3();
                        // Check entity type from packedworld coordinate
                        // ENTITY_TYPE_SHIFT_CHECK = 0: Entity
                        // ENTITY_TYPE_SHIFT_CHECK = 1: World coordinate
                        if (i2 >> ENTITY_TYPE_SHIFT_CHECK == 0) {
                            @Pc(1994) SeqType seqType;
                            // ENTITY_TYPE_NPC_SHIFT = NPC
                            if (i2 >> ENTITY_TYPE_NPC_SHIFT != 0) {
                                count = i2 & ENTITY_ID_MASK;
                                @Pc(1894) Npc npc = NpcList.npcs[count];
                                if (npc != null) {
                                    if (slot == INVALID_ID_U16) {
                                        slot = -1;
                                    }
                                    isSorted = true;
                                    // Check animation priority
                                    if (slot != -1 && npc.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(slot).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(npc.spotAnimId).seqId).priority) {
                                        isSorted = false;
                                    }
                                    if (isSorted) {
                                        npc.anInt3361 = 0;
                                        npc.spotAnimId = slot;
                                        npc.spotAnimStart = Client.loop + ii;
                                        npc.spotanimId = 0;
                                        if (npc.spotAnimStart > Client.loop) {
                                            npc.spotanimId = -1;
                                        }
                                        npc.spotAnimY = param1;
                                        npc.anInt3418 = 1;
                                        if (npc.spotAnimId != -1 && Client.loop == npc.spotAnimStart) {
                                            j = SpotAnimTypeList.get(npc.spotAnimId).seqId;
                                            if (j != -1) {
                                                seqType = SeqTypeList.get(j);
                                                if (seqType != null && seqType.frames != null) {
                                                    SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, 0);
                                                }
                                            }
                                        }
                                    }
                                }
                                // ENTITY_TYPE_PLAYER_SHIFT = Player
                            } else if (i2 >> ENTITY_TYPE_PLAYER_SHIFT != 0) {
                                count = i2 & ENTITY_ID_MASK;
                                @Pc(2033) Player player;
                                if (PlayerList.localPid == count) {
                                    player = PlayerList.self;
                                } else {
                                    player = PlayerList.players[count];
                                }
                                if (player != null) {
                                    if (slot == INVALID_ID_U16) {
                                        slot = -1;
                                    }
                                    isSorted = true;
                                    if (slot != -1 && player.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(slot).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(player.spotAnimId).seqId).priority) {
                                        isSorted = false;
                                    }
                                    if (isSorted) {
                                        player.spotAnimStart = ii + Client.loop;
                                        player.spotAnimY = param1;
                                        player.spotAnimId = slot;
                                        if (player.spotAnimId == INVALID_ID_U16) {
                                            player.spotAnimId = -1;
                                        }
                                        player.anInt3418 = 1;
                                        player.anInt3361 = 0;
                                        player.spotanimId = 0;
                                        if (player.spotAnimStart > Client.loop) {
                                            player.spotanimId = -1;
                                        }
                                        if (player.spotAnimId != -1 && player.spotAnimStart == Client.loop) {
                                            j = SpotAnimTypeList.get(player.spotAnimId).seqId;
                                            if (j != -1) {
                                                seqType = SeqTypeList.get(j);
                                                if (seqType != null && seqType.frames != null) {
                                                    SoundPlayer.playSeqSound(player.zFine, seqType, player.xFine, player == PlayerList.self, 0);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            // Extract coordinate from packed value
                            count = i2 >> PLANE_SHIFT & PLANE_MASK;
                            i = (i2 >> COORD_X_SHIFT & COORD_MASK) - Camera.sceneBaseTileX; // X coordinate
                            chatFlags = (i2 & COORD_MASK) - Camera.sceneBaseTileZ; // Z coordinate
                            if (i >= 0 && chatFlags >= 0 && i < SIZE && chatFlags < SIZE) {
                                chatFlags = chatFlags * TILE_SIZE + TILE_CENTER_OFFSET;
                                i = i * TILE_SIZE + TILE_CENTER_OFFSET;
                                @Pc(2241) SpotAnim spotAnim = new SpotAnim(slot, count, i, chatFlags, SceneGraph.getTileHeight(count, i, chatFlags) - param1, ii, Client.loop);
                                SceneGraph.spotanims.push(new SpotAnimEntity(spotAnim));
                            }
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == IF_SETMODELROTATION) {
                        // Set 3D model rotation speed on component
                        ii = inboundBuffer.p4rme();
                        int verifyID = inboundBuffer.g2_alt2();
                        i2 = inboundBuffer.g2();
                        slot = inboundBuffer.g2_alt2();
                        if (setVerifyID(verifyID)) {
                            ClientServerStateSync.setComponentModelRotationSpeedServer(slot + (i2 << COMPONENT_UPPER_WORD_SHIFT), ii);
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == UPDATE_STAT) {
                        // UPDATE_STAT
                        // Update players skill level and xp
                        // Sends boosted level
                        ComponentList.redrawActiveInterfaces();
                        ii = inboundBuffer.g1_alt1();
                        param1 = inboundBuffer.g4rme();
                        i2 = inboundBuffer.g1(); // Skill
                        PlayerSkillXpTable.experience[i2] = param1;
                        PlayerSkillXpTable.boostedLevels[i2] = ii;
                        PlayerSkillXpTable.baseLevels[i2] = 1;
                        // Calculate base level from xp
                        for (slot = 0; slot < MAX_SKILL_LEVEL_INDEX; slot++) {
                            if (PlayerSkillXpTable.xpLevelLookup[slot] <= param1) {
                                PlayerSkillXpTable.baseLevels[i2] = slot + 2;
                            }
                        }
                        PlayerSkillXpTable.updatedStats[PlayerSkillXpTable.updatedStatsWriterIndex++ & CIRCULAR_BUFFER_MASK] = i2;
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == ZONE_MAP_PROJANIM_SPECIFIC || currentOpcode == ZONE_MAP_PROJANIM_SMALL || currentOpcode == ZONE_SOUND_AREA || currentOpcode == ZONE_OBJ_COUNT || currentOpcode == ZONE_LOC_ATTACH || currentOpcode == ZONE_OBJ_ADD_PRIVATE || currentOpcode == ZONE_MAP_ANIM || currentOpcode == ZONE_MAP_PROJANIM || currentOpcode == ZONE_OBJ_DEL || currentOpcode == ZONE_OBJ_ADD || currentOpcode == ZONE_LOC_MERGE || currentOpcode == ZONE_LOC_DEL || currentOpcode == ZONE_LOC_ADD_CHANGE) {
                        // Zone update packet
                        readZonePacket();
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == IF_CLOSE_SUB) {
                        // Close sub-interface
                        int verifyID = inboundBuffer.g2();
                        param1 = inboundBuffer.g4();
                        if (setVerifyID(verifyID)) {
                            @Pc(2441) SubInterface subInterface = (SubInterface) ComponentList.openInterfaces.get((long) param1);
                            if (subInterface != null) {
                                ComponentList.closeInterface(true, subInterface);
                            }
                            if (ClientScriptRunner.modalBackgroundComponent != null) {
                                ComponentList.redraw(ClientScriptRunner.modalBackgroundComponent);
                                ClientScriptRunner.modalBackgroundComponent = null;
                            }
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == CAM_ORBIT) {
                        // Set orbital camera angles
                        // Controls yaw and pitch
                        ii = inboundBuffer.g2_al1();
                        int verifyID = inboundBuffer.g2();
                        i2 = inboundBuffer.g2(); // Camera pitch
                        if (setVerifyID(verifyID)) {
                            Camera.orbitCameraYaw = ii;
                            Camera.orbitCameraPitch = i2;
                            if (Camera.cameraType == 2) { // Camera type 2 = orbital camera
                                Camera.cameraPitch = Camera.orbitCameraPitch;
                                Camera.cameraYaw = Camera.orbitCameraYaw;
                            }
                            SceneCamera.clampCameraAngle();
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == UPDATE_VIEW) {
                        // Update interface component view settings
                        ii = inboundBuffer.g2();
                        int verifyID = inboundBuffer.g2_alt2();
                        i2 = inboundBuffer.g2_alt3();
                        slot = inboundBuffer.g2_alt3();
                        count = inboundBuffer.g4();
                        if (setVerifyID(verifyID)) {
                            ClientServerStateSync.updateView(i2, count, slot, ii);
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == MAP_CLEAR_ZONE) {
                        // Clear all ground items in a 8x8 area
                        SceneGraph.currentChunkX = inboundBuffer.g1();
                        SceneGraph.currentChunkZ = inboundBuffer.g1_alt2();
                        for (ii = SceneGraph.currentChunkX; ii < SceneGraph.currentChunkX + ZONE_SIZE; ii++) {
                            for (param1 = SceneGraph.currentChunkZ; param1 < SceneGraph.currentChunkZ + ZONE_SIZE; param1++) {
                                if (SceneGraph.objStacks[Player.currentLevel][ii][param1] != null) {
                                    SceneGraph.objStacks[Player.currentLevel][ii][param1] = null;
                                    sortObjStacks(ii, param1);
                                }
                            }
                        }
                        for (@Pc(2604) ChangeLocRequest locRequest = (ChangeLocRequest) ChangeLocRequest.queue.head(); locRequest != null; locRequest = (ChangeLocRequest) ChangeLocRequest.queue.next()) {
                            if (locRequest.x >= SceneGraph.currentChunkX && SceneGraph.currentChunkX + ZONE_SIZE > locRequest.x && locRequest.z >= SceneGraph.currentChunkZ && locRequest.z < SceneGraph.currentChunkZ + ZONE_SIZE && locRequest.level == Player.currentLevel) {
                                locRequest.resetLoops = 0;
                            }
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == INV_RESET_COMPONENT) {
                        // Clear all item in interface inventory component
                        ii = inboundBuffer.p4rme();
                        @Pc(2666) Component component = ComponentList.getComponent(ii);
                        for (i2 = 0; i2 < component.invSlotObjId.length; i2++) {
                            component.invSlotObjId[i2] = -1;
                            component.invSlotObjId[i2] = 0;
                        }
                        ComponentList.redraw(component);
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == IF_SETMODEL) {
                        // Set 3D model on interface component
                        ii = inboundBuffer.g4me();
                        int verifyID = inboundBuffer.g2_alt3();
                        i2 = inboundBuffer.g2_alt2();
                        if (i2 == INVALID_ID_U16) {
                            i2 = -1;
                        }
                        if (setVerifyID(verifyID)) {
                            ClientServerStateSync.updateComponentModel(-1, 1, ii, i2);
                        }
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == MINIMAP_STATE) {
                        // Set minimap visibility state
                        MiniMap.state = inboundBuffer.g1();
                        currentOpcode = -1;
                        return true;
                    } else if (currentOpcode == PLAYER_TELEPORT) {
                        // Teleport player to coordinate
                        ii = inboundBuffer.g1_alt3();
                        param1 = inboundBuffer.g1_alt1();
                        i2 = inboundBuffer.g1();
                        Player.currentLevel = param1 >> LEVEL_SHIFT;
                        PlayerList.self.teleport(ii, (param1 & TELEPORT_FLAG_MASK) == 1, i2);
                        currentOpcode = -1;
                        return true;
                    } else {
                        @Pc(3002) int friendIndex;
                        @Pc(3038) JString message_Text;
                        @Pc(3020) JString quickChatText;
                        if (currentOpcode == FRIENDLIST_LOADED) {
                            // Update or add friend to friends list
                            // Sends online status, world, rank, username
                            // Sorted by online on current world
                            senderName = inboundBuffer.g8();
                            i2 = inboundBuffer.g2(); // World
                            slot = inboundBuffer.g1();
                            ignored = true;
                            if (senderName < 0L) {
                                senderName &= Long.MAX_VALUE;
                                ignored = false;
                            }
                            worldName = JString.EMPTY;
                            if (i2 > 0) {
                                worldName = inboundBuffer.gjstr();
                            }
                            @Pc(2834) JString displayName = Base37.fromBase37(senderName).toTitleCase();
                            for (j = 0; j < FriendList.friendCount; j++) {
                                if (senderName == FriendList.encodedUsernames[j]) {
                                    if (i2 != FriendList.friendWorlds[j]) {
                                        FriendList.friendWorlds[j] = i2;
                                        if (i2 > 0) {
                                            Chat.addMessage(JString.EMPTY, 5, JString.concatenate(new JString[] { displayName, LocalizedText.FRIENDLOGIN}));
                                        }
                                        if (i2 == 0) {
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
                            if (displayName != null && FriendList.friendCount < MAX_FRIENDS) {
                                FriendList.encodedUsernames[FriendList.friendCount] = senderName;
                                FriendList.friendUsernames[FriendList.friendCount] = displayName;
                                FriendList.friendWorlds[FriendList.friendCount] = i2;
                                FriendList.worldNames[FriendList.friendCount] = worldName;
                                FriendList.ranks[FriendList.friendCount] = slot;
                                FriendList.friendGame[FriendList.friendCount] = ignored;
                                FriendList.friendCount++;
                            }
                            FriendList.transmitAt = ComponentList.transmitTimer;
                            chatType = FriendList.friendCount;
                            while (chatType > 0) {
                                chatType--;
                                @Pc(2961) boolean hasSwaps = true;
                                for (phraseId = 0; phraseId < chatType; phraseId++) {
                                    if (FriendList.friendWorlds[phraseId] != Player.worldId && Player.worldId == FriendList.friendWorlds[phraseId + 1] || FriendList.friendWorlds[phraseId] == 0 && FriendList.friendWorlds[phraseId + 1] != 0) {
                                        hasSwaps = false;
                                        friendIndex = FriendList.friendWorlds[phraseId];
                                        FriendList.friendWorlds[phraseId] = FriendList.friendWorlds[phraseId + 1];
                                        FriendList.friendWorlds[phraseId + 1] = friendIndex;
                                        quickChatText = FriendList.worldNames[phraseId];
                                        FriendList.worldNames[phraseId] = FriendList.worldNames[phraseId + 1];
                                        FriendList.worldNames[phraseId + 1] = quickChatText;
                                        message_Text = FriendList.friendUsernames[phraseId];
                                        FriendList.friendUsernames[phraseId] = FriendList.friendUsernames[phraseId + 1];
                                        FriendList.friendUsernames[phraseId + 1] = message_Text;
                                        @Pc(3056) long encodedUsername = FriendList.encodedUsernames[phraseId];
                                        FriendList.encodedUsernames[phraseId] = FriendList.encodedUsernames[phraseId + 1];
                                        FriendList.encodedUsernames[phraseId + 1] = encodedUsername;
                                        @Pc(3074) int friendRank = FriendList.ranks[phraseId];
                                        FriendList.ranks[phraseId] = FriendList.ranks[phraseId + 1];
                                        FriendList.ranks[phraseId + 1] = friendRank;
                                        @Pc(3092) boolean isInGame = FriendList.friendGame[phraseId];
                                        FriendList.friendGame[phraseId] = FriendList.friendGame[phraseId + 1];
                                        FriendList.friendGame[phraseId + 1] = isInGame;
                                    }
                                }
                                if (hasSwaps) {
                                    break;
                                }
                            }
                            currentOpcode = -1;
                            return true;
                        } else if (currentOpcode == WALK_TEXT) {
                            // Set custom "Walk here" text
                            // Empty = use default
                            if (packetSize == 0) {
                                MiniMenu.walkText = LocalizedText.WALKHERE;
                            } else {
                                MiniMenu.walkText = inboundBuffer.gjstr();
                            }
                            currentOpcode = -1;
                            return true;
                        } else if (currentOpcode == SYNC_VARP_LARGE) {
                            // Synchronize all player variables from server to client
                            // Used after login or major state change
                            for (ii = 0; ii < VarpDomain.activeVarps.length; ii++) {
                                if (VarpDomain.serverVarps[ii] != VarpDomain.activeVarps[ii]) {
                                    VarpDomain.activeVarps[ii] = VarpDomain.serverVarps[ii];
                                    VarpDomain.refreshMagicVarp(ii);
                                    VarpDomain.updatedVarps[VarpDomain.updatedVarpsWriterIndex++ & CIRCULAR_BUFFER_MASK] = ii;
                                }
                            }
                            currentOpcode = -1;
                            return true;
                        } else if (currentOpcode == CAM_TARGET) {
                            // Set camera target position with smoothing
                            int verifyID = inboundBuffer.g2();
                            param1 = inboundBuffer.g1();
                            i2 = inboundBuffer.g1();
                            slot = inboundBuffer.g2();
                            count = inboundBuffer.g1();
                            i = inboundBuffer.g1();
                            if (setVerifyID(verifyID)) {
                                Camera.setCameraTargetPosition(true, count, slot, i, i2, param1);
                            }
                            currentOpcode = -1;
                            return true;
                        } else if (currentOpcode == MESSAGE_QUICKCHAT_PRIVATE) {
                            // Private quickchat message
                            senderName = inboundBuffer.g8();
                            username = inboundBuffer.g2();
                            messageId1 = inboundBuffer.g3();
                            chatFlags = inboundBuffer.g1();
                            j = inboundBuffer.g2();
                            @Pc(3263) boolean isIgnored = false;
                            @Pc(3270) long combinedMessageId = (username << MESSAGE_ID_HIGH_SHIFT) + messageId1;
                            @Pc(3272) int messageIndex = 0;
                            label1402: while (true) {
                                if (messageIndex < MAX_RECENT_MESSAGES) {
                                    if (combinedMessageId != Chat.recentMessages[messageIndex]) {
                                        messageIndex++;
                                        continue;
                                    }
                                    isIgnored = true;
                                    break;
                                }
                                if (chatFlags <= 1) {
                                    for (messageIndex = 0; messageIndex < IgnoreList.ignoreCount; messageIndex++) {
                                        if (senderName == IgnoreList.encodedIgnores[messageIndex]) {
                                            isIgnored = true;
                                            break label1402;
                                        }
                                    }
                                }
                                break;
                            }
                            if (!isIgnored && Player.inTutorialIsland == 0) {
                                Chat.recentMessages[Chat.messageCounter] = combinedMessageId;
                                Chat.messageCounter = (Chat.messageCounter + 1) % MAX_RECENT_MESSAGES;
                                quickChatText = QuickChatPhraseTypeList.get(j).decodeMessage(inboundBuffer);
                                if (chatFlags == 2) {
                                    Chat.add(j, 18, quickChatText, null, JString.concatenate(new JString[] { IMG1, Base37.fromBase37(senderName).toTitleCase() }));
                                } else if (chatFlags == 1) {
                                    Chat.add(j, 18, quickChatText, null, JString.concatenate(new JString[] { IMG0, Base37.fromBase37(senderName).toTitleCase() }));
                                } else {
                                    Chat.add(j, 18, quickChatText, null, Base37.fromBase37(senderName).toTitleCase());
                                }
                            }
                            currentOpcode = -1;
                            return true;
                        } else {
                            @Pc(3456) SubInterface oldSubInterface;
                            if (currentOpcode == IF_MOVESUB) {
                                // Move a sub-interface from one component slot to another
                                ii = inboundBuffer.g4rme();
                                int verifyID = inboundBuffer.g2_alt2();
                                i2 = inboundBuffer.g4rme();
                                if (setVerifyID(verifyID)) {
                                    @Pc(3449) SubInterface newSubInterface = (SubInterface) ComponentList.openInterfaces.get((long) ii);
                                    oldSubInterface = (SubInterface) ComponentList.openInterfaces.get((long) i2);
                                    if (oldSubInterface != null) {
                                        ComponentList.closeInterface(newSubInterface == null || oldSubInterface.interfaceId != newSubInterface.interfaceId, oldSubInterface);
                                    }
                                    if (newSubInterface != null) {
                                        newSubInterface.unlink();
                                        ComponentList.openInterfaces.put(newSubInterface, (long) i2);
                                    }
                                    @Pc(3490) Component component = ComponentList.getComponent(ii);
                                    if (component != null) {
                                        ComponentList.redraw(component);
                                    }
                                    component = ComponentList.getComponent(i2);
                                    if (component != null) {
                                        ComponentList.redraw(component);
                                        ComponentList.updateContainerLayout(component, true);
                                    }
                                    if (ComponentList.topLevelInterface != -1) {
                                        ComponentList.runScripts(1, ComponentList.topLevelInterface);
                                    }
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == CAM_SHAKE) {
                                // Triggers camera shake effect
                                int verifyID = inboundBuffer.g2();
                                param1 = inboundBuffer.g1();
                                i2 = inboundBuffer.g1();
                                slot = inboundBuffer.g1();
                                count = inboundBuffer.g1();
                                i = inboundBuffer.g2();
                                if (setVerifyID(verifyID)) {
                                    Camera.cameraModifierEnabled[param1] = true;
                                    Camera.cameraModifierJitter[param1] = i2;
                                    Camera.cameraAmplitude[param1] = slot;
                                    Camera.cameraFrequency[param1] = count;
                                    cameraModifierCycle[param1] = i;
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_SETCOLOR) {
                                // Set component color in RGB
                                ii = inboundBuffer.g4rme();
                                int verifyID = inboundBuffer.g2_alt2();
                                i2 = inboundBuffer.g2_alt3();
                                if (setVerifyID(verifyID)) {
                                    ClientServerStateSync.setColor(i2, ii);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == SYSTEM_UPDATE) {
                                // Starts server update countdown
                                Player.systemUpdateTimer = inboundBuffer.g2() * SYSTEM_UPDATE_TICK_MULTIPLIER;
                                currentOpcode = -1;
                                ComponentList.miscTransmitAt = ComponentList.transmitTimer;
                                return true;
                            } else if (currentOpcode == REFLECTION_CHECK) {
                                // Anti botting reflection check
                                // Server asks client to inspect it's own code via reflection
                                ReflectionCheck.push(GameShell.signLink, inboundBuffer, packetSize);
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == VARC_LEGACY) {
                                // Legacy format for client variable updates
                                int verifyID = inboundBuffer.g2_al1();
                                param1 = inboundBuffer.g1_alt2();
                                i2 = inboundBuffer.g2_alt3();
                                if (setVerifyID(verifyID)) {
                                    ClientServerStateSync.updateVarC(i2, param1);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == UPDATE_RUNENERGY) {
                                // UPDATE_RUNENERGY
                                // update player run energy percentage
                                ComponentList.redrawActiveInterfaces();
                                Player.runEnergy = inboundBuffer.g1();
                                ComponentList.miscTransmitAt = ComponentList.transmitTimer;
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_RUNSCRIPT) {
                                // Execute ClientScript on specific component
                                if (ComponentList.topLevelInterface != -1) {
                                    ComponentList.runScripts(0, ComponentList.topLevelInterface);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == INV_DELETE) {
                                // remove item from inventory
                                ii = inboundBuffer.g2_al1();
                                Inv.delete(ii);
                                Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & CIRCULAR_BUFFER_MASK] = ii & INVENTORY_ID_MASK;
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == NPC_ANIM) {
                                // Play animation on a specific NPC
                                ii = inboundBuffer.g2_al1();
                                param1 = inboundBuffer.g1_alt3();
                                i2 = inboundBuffer.g2();
                                @Pc(3766) Npc npc = NpcList.npcs[ii];
                                if (npc != null) {
                                    animateNpc(param1, i2, npc);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == UPDATE_RUNWEIGHT) {
                                // UPDATE_RUNWEIGHT
                                // Update player's weight
                                ComponentList.redrawActiveInterfaces();
                                Player.weightCarried = inboundBuffer.g2s();
                                ComponentList.miscTransmitAt = ComponentList.transmitTimer;
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == MESSAGE_BROADCAST) {
                                // Server wide broadcast message
                                senderName = inboundBuffer.g8();
                                messageText = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                Chat.addMessage(Base37.fromBase37(senderName).toTitleCase(), 6, messageText);
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == URL_OPEN) {
                                // Open URL in browser
                                if (GameShell.fullScreenFrame != null) {
                                    DisplayMode.setWindowMode(false, Preferences.favoriteWorlds, -1, -1);
                                }
                                @Pc(3848) byte[] mapData = new byte[packetSize];
                                inboundBuffer.method2237(mapData, packetSize);
                                argTypes = JString.decodeString(mapData, packetSize, 0);
                                if (GameShell.frame == null && (SignLink.anInt5928 == 3 || !SignLink.osName.startsWith("win") || Client.haveIe6)) {
                                    ClientScriptRunner.openUrl(argTypes, true);
                                } else {
                                    ClientScriptRunner.url = argTypes;
                                    newTab = true;
                                    openUrlRequest = GameShell.signLink.openUrl(new String(argTypes.method3148(), "ISO-8859-1"));
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_SETMODEL_TYPE7) {
                                int verifyID = inboundBuffer.g2_alt2();
                                param1 = inboundBuffer.p4rme();
                                i2 = inboundBuffer.g2_alt3();
                                slot = inboundBuffer.g2_al1();
                                count = inboundBuffer.g2_alt3();
                                if (setVerifyID(verifyID)) {
                                    ClientServerStateSync.updateComponentModel(i2, 7, param1, slot << COMPONENT_UPPER_WORD_SHIFT | count);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == VARBIT) {
                                // Update varbit (bit-packed variables within a player variable)
                                ii = inboundBuffer.g1_alt1();
                                param1 = inboundBuffer.g2_al1();
                                VarpDomain.setVarbitServer(ii, param1);
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_OPENSUB) {
                                // IF_OPENSUB
                                // Open sub-interface in component slot
                                int flags = inboundBuffer.g1();
                                int windowID = inboundBuffer.p4rme();
                                int verifyID = inboundBuffer.g2_alt2();
                                int interfaceID = inboundBuffer.g2();
                                if (setVerifyID(verifyID)) {
                                    SubInterface SubInterface = (SubInterface) ComponentList.openInterfaces.get(windowID);
                                    if (SubInterface != null) {
                                        ComponentList.closeInterface(interfaceID != SubInterface.interfaceId, SubInterface);
                                    }
                                    openSubInterface(interfaceID, windowID, flags);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == RESET_PLAYER_NPC_ANIMS) {
                                // RESET_ANIMS
                                // Reset all entity animations
                                // Clears animations for all players and NPCs
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
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == HINT_ARROW) {
                                // Dsiplay hint arrow poiting on location/entity
                                // Can target entities, players and coordinates
                                ii = inboundBuffer.g1();
                                @Pc(4084) MapMarker marker = new MapMarker();
                                param1 = ii >> MAP_MARKER_PARAM_SHIFT;
                                marker.type = ii & MAP_MARKER_TYPE_MASK;
                                marker.anInt4048 = inboundBuffer.g1();
                                if (marker.anInt4048 >= 0 && marker.anInt4048 < Sprites.headhints.length) {
                                    if (marker.type == MAP_MARKER_NPC || marker.type == MAP_MARKER_PLAYER) {
                                        marker.actorTargetId = inboundBuffer.g2();
                                        inboundBuffer.offset += 3;
                                    } else if (marker.type >= MAP_MARKER_COORD_MIN && marker.type <= MAP_MARKER_COORD_MAX) {
                                        if (marker.type == 2) {
                                            marker.anInt4045 = TILE_CENTER_OFFSET;
                                            marker.anInt4047 = TILE_CENTER_OFFSET;
                                        }
                                        if (marker.type == 3) {
                                            marker.anInt4045 = 0;
                                            marker.anInt4047 = TILE_CENTER_OFFSET;
                                        }
                                        if (marker.type == 4) {
                                            marker.anInt4045 = TILE_SIZE;
                                            marker.anInt4047 = TILE_CENTER_OFFSET;
                                        }
                                        if (marker.type == 5) {
                                            marker.anInt4045 = TILE_CENTER_OFFSET;
                                            marker.anInt4047 = 0;
                                        }
                                        if (marker.type == 6) {
                                            marker.anInt4045 = TILE_CENTER_OFFSET;
                                            marker.anInt4047 = TILE_SIZE;
                                        }
                                        marker.type = 2;
                                        marker.targetX = inboundBuffer.g2();
                                        marker.anInt4046 = inboundBuffer.g2();
                                        marker.anInt4050 = inboundBuffer.g1();
                                    }
                                    marker.playerModelId = inboundBuffer.g2();
                                    if (marker.playerModelId == INVALID_ID_U16) {
                                        marker.playerModelId = -1;
                                    }
                                    MiniMap.hintMapMarkers[param1] = marker;
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == UPDATE_IGNORELIST) {
                                // UPDATE_IGNORELIST
                                // Add or update ignored players
                                IgnoreList.ignoreCount = packetSize / IGNORE_LIST_ENTRY_SIZE;
                                for (ii = 0; ii < IgnoreList.ignoreCount; ii++) {
                                    IgnoreList.encodedIgnores[ii] = inboundBuffer.g8();
                                    IgnoreList.ignoreName37[ii] = Base37.fromBase37(IgnoreList.encodedIgnores[ii]);
                                }
                                FriendList.transmitAt = ComponentList.transmitTimer;
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == NPC_INFO) {
                                // NPC entity updates
                                readNpcPacket();
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_SETCOMPONENT_PARAMS4) {
                                int verifyID = inboundBuffer.g2_alt2();
                                param1 = inboundBuffer.g4me();
                                i2 = inboundBuffer.g2s();
                                slot = inboundBuffer.g2sadd();
                                if (setVerifyID(verifyID)) {
                                    ClientServerStateSync.setComponentPositionServer(i2, param1, slot);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == ZONE_LOC_ATTACH_SIMPLE) {
                                // Attach location to tile
                                ii = inboundBuffer.g1_alt3();
                                param1 = ii >> LOC_PARAM_SHIFT;
                                i2 = ii & ROTATION_MASK;
                                slot = Loc.LAYERS[param1];
                                count = inboundBuffer.g2();
                                i = inboundBuffer.g4();
                                if (count == INVALID_ID_U16) {
                                    count = -1;
                                }
                                chatType = i & COORD_MASK;
                                j = i >> COORD_X_SHIFT & COORD_MASK;
                                j -= Camera.sceneBaseTileX;
                                chatType -= Camera.sceneBaseTileZ;
                                chatFlags = i >> PLANE_SHIFT & PLANE_MASK;
                                SceneGraph.attachLocToTile(chatFlags, i2, param1, chatType, slot, j, count);
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == MESSAGE_PRIVATE) {
                                // Private chat from friend
                                senderName = inboundBuffer.g8();
                                username = inboundBuffer.g2();
                                messageId1 = inboundBuffer.g3();
                                chatFlags = inboundBuffer.g1();
                                @Pc(4425) boolean isIgnored = false;
                                @Pc(4431) long combinedMessageId = messageId1 + (username << MESSAGE_ID_HIGH_SHIFT);
                                friendIndex = 0;
                                label1450: while (true) {
                                    if (friendIndex >= MAX_RECENT_MESSAGES) {
                                        if (chatFlags <= 1) {
                                            if (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat) {
                                                isIgnored = true;
                                            } else {
                                                for (friendIndex = 0; friendIndex < IgnoreList.ignoreCount; friendIndex++) {
                                                    if (senderName == IgnoreList.encodedIgnores[friendIndex]) {
                                                        isIgnored = true;
                                                        break label1450;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    if (combinedMessageId == Chat.recentMessages[friendIndex]) {
                                        isIgnored = true;
                                        break;
                                    }
                                    friendIndex++;
                                }
                                if (!isIgnored && Player.inTutorialIsland == 0) {
                                    Chat.recentMessages[Chat.messageCounter] = combinedMessageId;
                                    Chat.messageCounter = (Chat.messageCounter + 1) % MAX_RECENT_MESSAGES;
                                    @Pc(4518) JString formattedMessage = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                    if (chatFlags == 2 || chatFlags == 3) {
                                        Chat.addMessage(JString.concatenate(new JString[] { IMG1, Base37.fromBase37(senderName).toTitleCase() }), 7, formattedMessage);
                                    } else if (chatFlags == 1) {
                                        Chat.addMessage(JString.concatenate(new JString[] { IMG0, Base37.fromBase37(senderName).toTitleCase() }), 7, formattedMessage);
                                    } else {
                                        Chat.addMessage(Base37.fromBase37(senderName).toTitleCase(), 3, formattedMessage);
                                    }
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == MESSAGE_PRIVATE_RECIPIENT) {
                                // Echo of sent private message
                                senderName = inboundBuffer.g8();
                                inboundBuffer.g1s();
                                username = inboundBuffer.g8();
                                messageId1 = inboundBuffer.g2();
                                messageId2 = inboundBuffer.g3();
                                @Pc(4626) long combinedMessageId = (messageId1 << MESSAGE_ID_HIGH_SHIFT) + messageId2;
                                chatType = inboundBuffer.g1();
                                @Pc(4632) boolean isIgnored = false;
                                @Pc(4634) int messageIndex = 0;
                                label1575: while (true) {
                                    if (messageIndex >= MAX_RECENT_MESSAGES) {
                                        if (chatType <= 1) {
                                            if (LoginManager.playerUnderage && !LoginManager.parentalChatConsent || LoginManager.worldQuickChat) {
                                                isIgnored = true;
                                            } else {
                                                for (messageIndex = 0; messageIndex < IgnoreList.ignoreCount; messageIndex++) {
                                                    if (IgnoreList.encodedIgnores[messageIndex] == senderName) {
                                                        isIgnored = true;
                                                        break label1575;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    if (Chat.recentMessages[messageIndex] == combinedMessageId) {
                                        isIgnored = true;
                                        break;
                                    }
                                    messageIndex++;
                                }
                                if (!isIgnored && Player.inTutorialIsland == 0) {
                                    Chat.recentMessages[Chat.messageCounter] = combinedMessageId;
                                    Chat.messageCounter = (Chat.messageCounter + 1) % MAX_RECENT_MESSAGES;
                                    message_Text = Font.escape(formatChatMessage(inboundBuffer).encodeMessage());
                                    if (chatType == 2 || chatType == 3) {
                                        Chat.method1598(message_Text, JString.concatenate(new JString[] { IMG1, Base37.fromBase37(senderName).toTitleCase() }), Base37.fromBase37(username).toTitleCase());
                                    } else if (chatType == 1) {
                                        Chat.method1598(message_Text, JString.concatenate(new JString[] { IMG0, Base37.fromBase37(senderName).toTitleCase() }), Base37.fromBase37(username).toTitleCase());
                                    } else {
                                        Chat.method1598(message_Text, Base37.fromBase37(senderName).toTitleCase(), Base37.fromBase37(username).toTitleCase());
                                    }
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == MAP_REBUILD) {
                                // Full dynamic map region rebuild
                                // Used for dynamic regions
                                readRebuildPacket(true);
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == SOUND_EFFECT) {
                                // Play sound effect at location
                                ii = inboundBuffer.g2();
                                param1 = inboundBuffer.g1();
                                if (ii == INVALID_ID_U16) {
                                    ii = -1;
                                }
                                i2 = inboundBuffer.g2();
                                SoundPlayer.play(param1, ii, i2);
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_SETMODEL_HEAD) {
                                // Set NPC head model on component
                                int verifyID = inboundBuffer.g2_alt3();
                                param1 = inboundBuffer.g4rme();
                                if (setVerifyID(verifyID)) {
                                    i2 = 0;
                                    if (PlayerList.self.appearance != null) {
                                        i2 = PlayerList.self.appearance.getHeadModelId();
                                    }
                                    ClientServerStateSync.updateComponentModel(-1, 3, param1, i2);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == IF_SETHIDE) {
                                // Show or hide a interface component
                                ii = inboundBuffer.p4rme();
                                argTypes = inboundBuffer.gjstr();
                                int verifyID = inboundBuffer.g2_alt2();
                                if (setVerifyID(verifyID)) {
                                    ClientServerStateSync.setComponentTextServer2(argTypes, ii);
                                }
                                currentOpcode = -1;
                                return true;
                            } else if (currentOpcode == VARBIT_LARGE) {
                                // Update VarBit with large ID (> 65535)
                                ii = inboundBuffer.g4me();
                                param1 = inboundBuffer.g2_alt3();
                                VarpDomain.setVarbitServer(ii, param1);
                                currentOpcode = -1;
                                return true;
                            } else {
                                @Pc(4956) Component component;
                                if (currentOpcode == INV_TRANSMIT) {
                                    // Sen partial inventory update
                                    // Updates specific changed slots
                                    ii = inboundBuffer.g4();
                                    param1 = inboundBuffer.g2();
                                    if (ii < INVENTORY_COMPONENT_OFFSET) {
                                        param1 += INVENTORY_PARAM_OFFSET;
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
                                            if (i == EXTENDED_COUNT_MARKER) {
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
                                    Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & CIRCULAR_BUFFER_MASK] = param1 & INVENTORY_ID_MASK;
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == CAM_RESET) {
                                    // Reset camera to default player-following mode
                                    // Clear scripted movement
                                    int verifyID = inboundBuffer.g2();
                                    if (setVerifyID(verifyID)) {
                                        Camera.resetCameraEffects();
                                    }
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == LOGOUT) {
                                    // Server initiated logout
                                    Game.processLogout();
                                    currentOpcode = -1;
                                    return false;
                                } else if (currentOpcode == GE_OFFER_UPDATE) {
                                    // GE offer status changed
                                    ii = inboundBuffer.g1();
                                    if (inboundBuffer.g1() == 0) {
                                        StockMarketManager.offers[ii] = new StockMarketOffer();
                                    } else {
                                        inboundBuffer.offset--;
                                        StockMarketManager.offers[ii] = new StockMarketOffer(inboundBuffer);
                                    }
                                    currentOpcode = -1;
                                    StockMarketManager.transmitAt = ComponentList.transmitTimer;
                                    return true;
                                } else if (currentOpcode == IF_SETMODEL_TYPE2) {
                                    ii = inboundBuffer.g2_alt2();
                                    param1 = inboundBuffer.g4me();
                                    if (ii == INVALID_ID_U16) {
                                        ii = -1;
                                    }
                                    int verifyID = inboundBuffer.g2_al1();
                                    if (setVerifyID(verifyID)) {
                                        ClientServerStateSync.updateComponentModel(-1, 2, param1, ii);
                                    }
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == MAP_REBUILD_PARTIAL) {
                                    // Static map region load
                                    readRebuildPacket(false);
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == IF_SETOBJECT) {
                                    // Dsiplay object/item on a component
                                    int verifyID = inboundBuffer.g2_al1();
                                    param1 = inboundBuffer.g2_al1();
                                    if (param1 == INVALID_ID_U16) {
                                        param1 = -1;
                                    }
                                    i2 = inboundBuffer.g4();
                                    slot = inboundBuffer.g2_alt2();
                                    count = inboundBuffer.g4rme();
                                    if (slot == INVALID_ID_U16) {
                                        slot = -1;
                                    }
                                    if (setVerifyID(verifyID)) {
                                        for (i = slot; i <= param1; i++) {
                                            messageId2 = ((long) i2 << MESSAGE_ID_HIGH_SHIFT) + ((long) i);
                                            activeProperties2 = (ServerActiveProperties) ComponentList.properties.get(messageId2);
                                            if (activeProperties2 != null) {
                                                activeProperties1 = new ServerActiveProperties(count, activeProperties2.targetParam);
                                                activeProperties2.unlink();
                                            } else if (i == -1) {
                                                activeProperties1 = new ServerActiveProperties(count, ComponentList.getComponent(i2).properties.targetParam);
                                            } else {
                                                activeProperties1 = new ServerActiveProperties(count, -1);
                                            }
                                            ComponentList.properties.put(activeProperties1, messageId2);
                                        }
                                    }
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == FRIENDLIST_STATE) {
                                    // Initialize friends list loading state
                                    FriendList.state = inboundBuffer.g1();
                                    FriendList.transmitAt = ComponentList.transmitTimer;
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == CLANCHAT_MEMBER_UPDATE) {
                                    // Single clan chat member update
                                    senderName = inboundBuffer.g8();
                                    i2 = inboundBuffer.g2();
                                    @Pc(5325) byte memberRank = inboundBuffer.g1s();
                                    ignored = false;
                                    if ((Long.MIN_VALUE & senderName) != 0L) {
                                        ignored = true;
                                    }
                                    if (ignored) {
                                        if (ClanChat.size == 0) {
                                            currentOpcode = -1;
                                            return true;
                                        }
                                        senderName &= Long.MAX_VALUE;
                                        for (i = 0; ClanChat.size > i && (senderName != ClanChat.members[i].nodeId || i2 != ClanChat.members[i].world); i++) {
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
                                        @Pc(5347) ClanMember clanMember = new ClanMember();
                                        clanMember.nodeId = senderName;
                                        clanMember.username = Base37.fromBase37(clanMember.nodeId);
                                        clanMember.rank = memberRank;
                                        clanMember.worldName = worldName;
                                        clanMember.world = i2;
                                        for (j = ClanChat.size - 1; j >= 0; j--) {
                                            chatType = ClanChat.members[j].username.method3139(clanMember.username);
                                            if (chatType == 0) {
                                                ClanChat.members[j].world = i2;
                                                ClanChat.members[j].rank = memberRank;
                                                ClanChat.members[j].worldName = worldName;
                                                if (senderName == Player.name37) {
                                                    ClanChat.rank = memberRank;
                                                }
                                                ClanChat.transmitAt = ComponentList.transmitTimer;
                                                currentOpcode = -1;
                                                return true;
                                            }
                                            if (chatType < 0) {
                                                break;
                                            }
                                        }
                                        if (ClanChat.members.length <= ClanChat.size) {
                                            currentOpcode = -1;
                                            return true;
                                        }
                                        for (chatType = ClanChat.size - 1; chatType > j; chatType--) {
                                            ClanChat.members[chatType + 1] = ClanChat.members[chatType];
                                        }
                                        if (ClanChat.size == 0) {
                                            ClanChat.members = new ClanMember[MAX_CLAN_MEMBERS];
                                        }
                                        ClanChat.members[j + 1] = clanMember;
                                        if (Player.name37 == senderName) {
                                            ClanChat.rank = memberRank;
                                        }
                                        ClanChat.size++;
                                    }
                                    currentOpcode = -1;
                                    ClanChat.transmitAt = ComponentList.transmitTimer;
                                    return true;
                                } else if (currentOpcode == IF_SETMODEL_OBJ) {
                                    // Set object/item model on component
                                    ii = inboundBuffer.g4();
                                    param1 = inboundBuffer.p4rme();
                                    i2 = inboundBuffer.g2_alt3();
                                    if (i2 == INVALID_ID_U16) {
                                        i2 = -1;
                                    }
                                    int verifyID = inboundBuffer.g2_al1();
                                    if (setVerifyID(verifyID)) {
                                        @Pc(5603) Component com = ComponentList.getComponent(param1);
                                        @Pc(5615) ObjType obj;
                                        if (com.if3) {
                                            ClientServerStateSync.setComponentObjectServer(param1, ii, i2);
                                            obj = ObjTypeList.get(i2);
                                            ClientServerStateSync.updateView(obj.zoom2d, param1, obj.yan2d, obj.xan2d);
                                            ClientServerStateSync.setComponentModelOffsetServer(param1, obj.zAngle2D, obj.yof2d, obj.xof2d);
                                        } else if (i2 == -1) {
                                            com.modelType = 0;
                                            currentOpcode = -1;
                                            return true;
                                        } else {
                                            obj = ObjTypeList.get(i2);
                                            com.modelXAngle = obj.xan2d;
                                            com.modelZoom = obj.zoom2d * ZOOM_PERCENTAGE / ii;
                                            com.modelType = 4;
                                            com.modelId = i2;
                                            com.modelYAngle = obj.yan2d;
                                            ComponentList.redraw(com);
                                        }
                                    }
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == INV_TRANSMIT_FULL) {
                                    // Send complete inventory content
                                    ii = inboundBuffer.g4();
                                    param1 = inboundBuffer.g2();
                                    if (ii < INVENTORY_COMPONENT_OFFSET) {
                                        param1 += INVENTORY_PARAM_OFFSET;
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
                                        if (i == EXTENDED_COUNT_MARKER) {
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
                                    Inv.updatedInventories[Inv.updatedInventoriesWriterIndex++ & CIRCULAR_BUFFER_MASK] = param1 & INVENTORY_ID_MASK;
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == COOKIE_STORE) {
                                    // Called removed method3954, stored username to cookie
                                    //method3954(inboundBuffer.gjstr());
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == MAP_COORDS) {
                                    // Update base map coordinates
                                    SceneGraph.currentChunkX = inboundBuffer.g1_alt2();
                                    SceneGraph.currentChunkZ = inboundBuffer.g1();
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == MUSIC_PLAY) {
                                    // Play music
                                    // Starts looping by track ID
                                    ii = inboundBuffer.g2_alt3();
                                    if (ii == INVALID_ID_U16) {
                                        ii = -1;
                                    }
                                    MusicPlayer.playSong(ii);
                                    currentOpcode = -1;
                                    return true;
                                } else if (currentOpcode == MUSIC_JINGLE) {
                                    // Play short jingle (non-looping)
                                    ii = inboundBuffer.g3le();
                                    param1 = inboundBuffer.g2_al1();
                                    if (param1 == INVALID_ID_U16) {
                                        param1 = -1;
                                    }
                                    MusicPlayer.playJingle(ii, param1);
                                    currentOpcode = -1;
                                    return true;
                                } else {
                                    TracingException.report("T1 - " + currentOpcode + "," + secondLastOpcode + "," + thirdLastOpcode + " - " + packetSize, null);
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
        verifyId = verifyID + 1 & U16_MASK;
        verifyIdChanged = true;
        return true;
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(B)V")
    public static void clearAreaNPCs() {
        inboundBuffer.accessBits();
        @Pc(13) int npcsInArea = inboundBuffer.gBit(NPC_COUNT_BITS);
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
            @Pc(70) int hasUpdate = inboundBuffer.gBit(1);
            if (hasUpdate == 0) {
                NpcList.npcIds[NpcList.npcCount++] = id;
                npc.lastSeenLoop = Client.loop;
            } else {
                @Pc(92) int updateType = inboundBuffer.gBit(2);
                if (updateType == 0) {
                    NpcList.npcIds[NpcList.npcCount++] = id;
                    npc.lastSeenLoop = Client.loop;
                    extendedIds[extendedCount++] = id;
                } else {
                    @Pc(139) int direction;
                    @Pc(149) int hasExtendedInfo;
                    if (updateType == 1) {
                        NpcList.npcIds[NpcList.npcCount++] = id;
                        npc.lastSeenLoop = Client.loop;
                        direction = inboundBuffer.gBit(3);
                        npc.move(1, direction);
                        hasExtendedInfo = inboundBuffer.gBit(1);
                        if (hasExtendedInfo == 1) {
                            extendedIds[extendedCount++] = id;
                        }
                    } else if (updateType == 2) {
                        NpcList.npcIds[NpcList.npcCount++] = id;
                        npc.lastSeenLoop = Client.loop;
                        if (inboundBuffer.gBit(1) == 1) {
                            direction = inboundBuffer.gBit(3);
                            npc.move(2, direction);
                            hasExtendedInfo = inboundBuffer.gBit(3);
                            npc.move(2, hasExtendedInfo);
                        } else {
                            direction = inboundBuffer.gBit(3);
                            npc.move(0, direction);
                        }
                        direction = inboundBuffer.gBit(1);
                        if (direction == 1) {
                            extendedIds[extendedCount++] = id;
                        }
                    } else if (updateType == 3) {
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

    @OriginalMember(owner = "client!rm", name = "a", descriptor = "(IBI)V")
    public static void sortObjStacks(@OriginalArg(2) int x, @OriginalArg(0) int z) {
        @Pc(9) LinkList objStacks = SceneGraph.objStacks[Player.currentLevel][x][z];

        if (objStacks == null) {
            SceneGraph.removeGroundObjects(Player.currentLevel, x, z);
            return;
        }
        @Pc(28) int topCost = MIN_COST_VALUE;
        @Pc(30) ClientObj topObj = null;

        @Pc(35) ClientObj objIterator;
        for (objIterator = (ClientObj) objStacks.head(); objIterator != null; objIterator = (ClientObj) objStacks.next()) {
            @Pc(44) ObjType objType = ObjTypeList.get(objIterator.value.id);
            @Pc(47) int cost = objType.cost;
            if (objType.stackable == STACKABLE_FLAG) {
                cost *= objIterator.value.count + 1;
            }
            if (topCost < cost) {
                topCost = cost;
                topObj = objIterator;
            }
        }
        if (topObj == null) {
            SceneGraph.removeGroundObjects(Player.currentLevel, x, z);
            return;
        }
        objStacks.addHead(topObj);
        @Pc(89) ObjStack secondStack = null;
        @Pc(91) ObjStack thirdStack = null;
        for (objIterator = (ClientObj) objStacks.head(); objIterator != null; objIterator = (ClientObj) objStacks.next()) {
            @Pc(103) ObjStack objStack = objIterator.value;
            if (objStack.id != topObj.value.id) {
                if (secondStack == null) {
                    secondStack = objStack;
                }
                if (objStack.id != secondStack.id && thirdStack == null) {
                    thirdStack = objStack;
                }
            }
        }
        @Pc(152) long coordHash = (long) ((z << COORD_HASH_SHIFT) + x + COORD_HASH_OFFSET);
        SceneGraph.setObjStack(Player.currentLevel, x, z, SceneGraph.getTileHeight(Player.currentLevel, x * TILE_SIZE + TILE_CENTER_OFFSET, z * TILE_SIZE + TILE_CENTER_OFFSET), topObj.value, coordHash, secondStack, thirdStack);
    }

    @OriginalMember(owner = "client!g", name = "b", descriptor = "(B)V")
    public static void readZonePacket() {
        @Pc(15) int packedData;
        @Pc(23) int local23;
        @Pc(19) int local19;
        @Pc(27) int local27;
        @Pc(31) int local31;
        @Pc(39) int local39;
        @Pc(45) int local45;
        if (currentOpcode == ZONE_LOC_DEL) {
            // ZONE_LOC_DEL
            // Delete a location from the zone
            // Used for ex. removing doors

            packedData = inboundBuffer.g1_alt2(); // Shape + rotation packed
            local19 = packedData & ROTATION_MASK; // Rotation
            local23 = packedData >> LOC_PARAM_SHIFT; // Shape type (wall, ground etc)
            local27 = Loc.LAYERS[local23]; // Get layer for this shape

            local31 = inboundBuffer.g1(); // Zone coordinates
            local39 = (local31 >> ZONE_COORD_SHIFT & ZONE_COORD_MASK) + SceneGraph.currentChunkX; // Absolute X
            local45 = (local31 & ZONE_COORD_MASK) + SceneGraph.currentChunkZ; // Absolute Z

            if (local39 >= 0 && local45 >= 0 && local39 < 104 && local45 < 104) {
                // Delete the location ath this position
                ChangeLocRequest.push(Player.currentLevel, local45, local19, local39, -1, -1, local27, local23, 0);
            }
        } else if (currentOpcode == ZONE_OBJ_ADD) {
            // ZONE_OBJ_ADD
            // Add ground objects visible for all players
            packedData = inboundBuffer.g2_al1(); // Object type ID
            local23 = inboundBuffer.g1(); // Zone coordinates (packed)

            local27 = (local23 & ZONE_COORD_MASK) + SceneGraph.currentChunkZ; // Absolute Z
            local19 = (local23 >> ZONE_COORD_SHIFT & ZONE_COORD_MASK) + SceneGraph.currentChunkX; // Absolute X

            local31 = inboundBuffer.g2_alt2(); // Item count

            if (local19 >= 0 && local27 >= 0 && local19 < SIZE && local27 < SIZE) {
                @Pc(122) ObjStack objStack = new ObjStack();
                objStack.count = local31; // Stack size
                objStack.id = packedData; // Item ID

                // Create obj stack if not existing
                if (SceneGraph.objStacks[Player.currentLevel][local19][local27] == null) {
                    SceneGraph.objStacks[Player.currentLevel][local19][local27] = new LinkList();
                }

                SceneGraph.objStacks[Player.currentLevel][local19][local27].push(new ClientObj(objStack));
                sortObjStacks(local19, local27); // Render the object
            }
        } else {
            @Pc(218) int startHeight;
            @Pc(228) int startCycle;
            @Pc(232) int endCycle;
            @Pc(247) int arc;
            @Pc(224) int endHeight;
            @Pc(236) int Angle;
            @Pc(317) ProjectileAnimation projectile;
            if (currentOpcode == ZONE_MAP_PROJANIM_SMALL) {
                // ZONE_MAP_PROJANIM_SMALL
                // Simple projectile
                packedData = inboundBuffer.g1(); // Starting coordinate
                local23 = SceneGraph.currentChunkX * 2 + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK_4BIT); // projectile start X
                local19 = (packedData & ZONE_COORD_MASK_4BIT) + SceneGraph.currentChunkZ * 2; // projectile Start Z
                local27 = local23 + inboundBuffer.g1s(); // Target X offset
                local31 = inboundBuffer.g1s() + local19; // Target Z offset
                local39 = inboundBuffer.g2s(); // Source entity
                local45 = inboundBuffer.g2(); // Projectile spotanim ID
                startHeight = inboundBuffer.g1() * HEIGHT_SCALE_FACTOR; // Start height
                endHeight = inboundBuffer.g1() * HEIGHT_SCALE_FACTOR; // End height
                startCycle = inboundBuffer.g2(); // Start cycle
                endCycle = inboundBuffer.g2(); // End cycle
                Angle = inboundBuffer.g1(); // Angle

                if (Angle == NO_ANGLE_SPECIFIED) {
                    Angle = -1;
                }

                arc = inboundBuffer.g1(); // Arc

                if (local23 >= 0 && local19 >= 0 && local23 < BUILD_AREA_HALF_TILES && local19 < BUILD_AREA_HALF_TILES && local27 >= 0 && local31 >= 0 && local27 < BUILD_AREA_HALF_TILES && local31 < BUILD_AREA_HALF_TILES && local45 != INVALID_ID_U16) {
                    local31 *= HALF_TILE_SIZE;

                    // Convert to world coordinates
                    local27 = local27 * HALF_TILE_SIZE;
                    local19 = local19 * HALF_TILE_SIZE;
                    local23 = local23 * HALF_TILE_SIZE;

                    projectile = new ProjectileAnimation(local45, Player.currentLevel, local23, local19, SceneGraph.getTileHeight(Player.currentLevel, local23, local19) - startHeight, Client.loop + startCycle, endCycle + Client.loop, Angle, arc, local39, endHeight);
                    projectile.setTarget(local31, Client.loop + startCycle, -endHeight + SceneGraph.getTileHeight(Player.currentLevel, local27, local31), local27);
                    SceneGraph.projectiles.push(new ProjAnimNode(projectile));
                }
            } else if (currentOpcode == ZONE_MAP_ANIM) {
                // ZONE_MAP_ANIM
                // Spot animation
                packedData = inboundBuffer.g1(); // Zone coordinate
                local23 = SceneGraph.currentChunkX + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Spot anim X
                local19 = SceneGraph.currentChunkZ + (packedData & ZONE_COORD_MASK); // Spot anim Z
                local27 = inboundBuffer.g2(); // Spotanim type ID
                local31 = inboundBuffer.g1(); // Height offset
                local39 = inboundBuffer.g2(); // Duration/cycle

                if (local23 >= 0 && local19 >= 0 && local23 < SIZE && local19 < SIZE) {
                    // Convert to world coodinates
                    local23 = local23 * TILE_SIZE + TILE_CENTER_OFFSET;
                    local19 = local19 * TILE_SIZE + TILE_CENTER_OFFSET;

                    // Create spot anim at position
                    @Pc(427) SpotAnim spotAnim = new SpotAnim(
                            local27, // Spotanim ID
                            Player.currentLevel, // Height level
                            local23, local19, // Position
                            SceneGraph.getTileHeight(Player.currentLevel, local23, local19) - local31, // Height
                            local39, // Duration
                            Client.loop // Start cycle
                    );
                    SceneGraph.spotanims.push(new SpotAnimEntity(spotAnim));
                }
            } else if (currentOpcode == ZONE_LOC_ADD_CHANGE) {
                // ZONE_LOC_ADD_CHANGE
                // Add or change location with animation
                packedData = inboundBuffer.g1_alt1(); // Shape + rotation
                local23 = packedData >> LOC_PARAM_SHIFT; // Shape type
                local19 = packedData & ROTATION_MASK; // Rotation
                local27 = Loc.LAYERS[local23]; // Layer for rendering order
                local31 = inboundBuffer.g1(); // Zone coordinates
                local39 = SceneGraph.currentChunkX + (local31 >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Zone X
                local45 = (local31 & ZONE_COORD_MASK) + SceneGraph.currentChunkZ; // Zone Z
                startHeight = inboundBuffer.g2_alt2(); // Location Type ID
                if (local39 >= 0 && local45 >= 0 && local39 < SIZE && local45 < SIZE) {
                    ChangeLocRequest.push(Player.currentLevel, local45, local19, local39, -1, startHeight, local27, local23, 0);
                }
            } else if (currentOpcode == ZONE_LOC_MERGE) {
                // ZONE_LOC_MERGE
                // Attach location directly to tile
                packedData = inboundBuffer.g1_alt3(); // Zone coordinates
                int x = SceneGraph.currentChunkX + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Zone X
                int z = SceneGraph.currentChunkZ + (packedData & ZONE_COORD_MASK); // Zone Z
                int info = inboundBuffer.g1_alt3(); // Shape + rotation
                int shape = info >> LOC_SHAPE_SHIFT; // Shape type
                int angle = info & ROTATION_MASK; // Angle
                local45 = Loc.LAYERS[shape]; // Rendering layer
                startHeight = inboundBuffer.g2_al1(); // Location type ID

                if (startHeight == INVALID_ID_U16) {
                    startHeight = -1; // -1 = remove
                }

                // Attach directly to scene graph tile
                SceneGraph.attachLocToTile(Player.currentLevel, angle, shape, z, local45, x, startHeight);
            } else {
                @Pc(633) int transformValue3;
                if (currentOpcode == ZONE_LOC_ATTACH) {
                    // ZONE_LOC_ATTACH
                    // Attach/merge location with complex OpenGL tranformations
                    // Used for ex. agility
                    // Contains transformation matrices for smooth OpenGl animation
                    // Skiped if in Software renderer
                    packedData = inboundBuffer.g1(); // Shape + rotation packed
                    local23 = packedData >> LOC_SHAPE_SHIFT; // Location shape
                    local19 = packedData & ROTATION_MASK; // Rotation (0-3, 0/90/180/270 degrees)
                    local27 = inboundBuffer.g1(); // Zone coordnates packed
                    local31 = SceneGraph.currentChunkX + (local27 >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Extract zone X
                    local39 = SceneGraph.currentChunkZ + (local27 & ZONE_COORD_MASK); // Extract Zone Z

                    // Read transformation parameters OpenGL specific
                    @Pc(605) byte transformParam1 = inboundBuffer.g1b_alt3();
                    @Pc(609) byte transformParam2 = inboundBuffer.g1b_alt3();
                    @Pc(613) byte transformParam3 = inboundBuffer.g1sub();
                    startCycle = inboundBuffer.g2_alt2(); // Tranform value 1
                    endCycle = inboundBuffer.g2_al1(); // Transform value 2
                    @Pc(625) byte transformParam4 = inboundBuffer.g1s();
                    arc = inboundBuffer.g2(); // Location type ID
                    transformValue3 = inboundBuffer.g2lesadd(); // Transform value 3

                    if (!GlRenderer.enabled) {
                        // OpenGL-only packet, push to attach queue for processing
                        AttachLocRequest.push(
                                transformParam4,
                                arc,
                                transformValue3,
                                endCycle,
                                local39,
                                transformParam3,
                                local19,
                                transformParam1,
                                local31,
                                local23,
                                transformParam2,
                                startCycle);
                    }
                }
                if (currentOpcode == ZONE_OBJ_COUNT) {
                    // ZONE_OBJ_COUNT
                    // Update existing ground object stack count
                    packedData = inboundBuffer.g1(); // Zone coordinate
                    int z = SceneGraph.currentChunkZ + (packedData & ZONE_COORD_MASK); // Zone Z
                    int x = SceneGraph.currentChunkX + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Zone X

                    int id = inboundBuffer.g2(); // Object type ID
                    int oldCount = inboundBuffer.g2(); // Old count
                    int newCount = inboundBuffer.g2(); // New count

                    if (x >= 0 && z >= 0 && x < CollisionConstants.SIZE && z < CollisionConstants.SIZE) {
                        @Pc(710) LinkList list = SceneGraph.objStacks[Player.currentLevel][x][z];

                        if (list != null) {
                            // Find the matching object stack
                            for (@Pc(718) ClientObj obj = (ClientObj) list.head(); obj != null; obj = (ClientObj) list.next()) {
                                @Pc(723) ObjStack objStack = obj.value;

                                // Match by type and old count
                                if ((id & INVENTORY_ID_MASK) == objStack.id && oldCount == objStack.count) {
                                    objStack.count = newCount; // Update to new count
                                    break;
                                }
                            }
                            sortObjStacks(x, z);
                        }
                    }
                } else if (currentOpcode == ZONE_OBJ_ADD_PRIVATE) {
                    // ZONE_OBJ_ADD_PRIVATE
                    // Add "private" ground objects
                    // Only visible to specific player
                    // Private items create a separate stack from public items
                    int receiver = inboundBuffer.g2_alt3(); // Player ID
                    local23 = inboundBuffer.g1_alt2(); // Zone coordinates

                    int z = SceneGraph.currentChunkZ + (local23 & ZONE_COORD_MASK); // Zone Z
                    int x = SceneGraph.currentChunkX + (local23 >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Zone X

                    int count = inboundBuffer.g2_al1(); // Item count
                    int id = inboundBuffer.g2_al1(); // Object type ID

                    if (x >= 0 && z >= 0 && x < CollisionConstants.SIZE && z < CollisionConstants.SIZE && receiver != PlayerList.localPid) {
                        @Pc(812) ObjStack objStack = new ObjStack();
                        objStack.count = count; // Stack size
                        objStack.id = id; // Item ID

                        // Create obj stack if not existing
                        if (SceneGraph.objStacks[Player.currentLevel][x][z] == null) {
                            SceneGraph.objStacks[Player.currentLevel][x][z] = new LinkList();
                        }

                        SceneGraph.objStacks[Player.currentLevel][x][z].push(new ClientObj(objStack));
                        sortObjStacks(x, z); // Render the object
                    }
                } else if (currentOpcode == ZONE_MAP_PROJANIM) {
                    // Zone_MAP_PROJANIM
                    // Projectile animation
                    packedData = inboundBuffer.g1(); // Starting zone coordinate
                    local23 = SceneGraph.currentChunkX + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Start zone X
                    local19 = (packedData & ZONE_COORD_MASK) + SceneGraph.currentChunkZ; // Start zone Z
                    local27 = local23 + inboundBuffer.g1s(); // Target tile X (offset)
                    local31 = inboundBuffer.g1s() + local19; // Target tile Z (offset)
                    local39 = inboundBuffer.g2s(); // Source entity
                    local45 = inboundBuffer.g2(); // Projectile spotanim ID
                    startHeight = inboundBuffer.g1() * HEIGHT_SCALE_FACTOR; // Start height offset
                    endHeight = inboundBuffer.g1() * HEIGHT_SCALE_FACTOR; // End height offset
                    startCycle = inboundBuffer.g2(); // Start cycle (delay before showing)
                    endCycle = inboundBuffer.g2(); // End cycle
                    Angle = inboundBuffer.g1(); // Angle/slope
                    arc = inboundBuffer.g1(); // Arc height

                    if (Angle == NO_ANGLE_SPECIFIED) {
                        Angle = -1; // No angle specified
                    }

                    // Validate coordinates are in bounds
                    if (local23 >= 0 && local19 >= 0 && local23 < SIZE && local19 < SIZE && local27 >= 0 && local31 >= 0 && local27 < SIZE && local31 < SIZE && local45 != INVALID_ID_U16) {
                        //Convert tile coords to world coordinates
                        local31 = local31 * TILE_SIZE + TILE_CENTER_OFFSET;
                        local19 = local19 * TILE_SIZE + TILE_CENTER_OFFSET;
                        local23 = local23 * TILE_SIZE + TILE_CENTER_OFFSET;
                        local27 = local27 * TILE_SIZE + TILE_CENTER_OFFSET;

                        // Create projectile animation
                        projectile = new ProjectileAnimation(
                                local45, // Spotanim ID
                                Player.currentLevel, // Height level
                                local23, local19, // Start position
                                SceneGraph.getTileHeight(Player.currentLevel, local23, local19) - startHeight, // Start height
                                startCycle + Client.loop, // Start cycle
                                endCycle + Client.loop, // End cycle
                                Angle, // Angle
                                arc, //Arc
                                local39, // Source entity
                                endHeight // End height offset
                        );

                        //Set target position
                        projectile.setTarget(local31, Client.loop + startCycle, SceneGraph.getTileHeight(Player.currentLevel, local27, local31) - endHeight, local27);
                        SceneGraph.projectiles.push(new ProjAnimNode(projectile));
                    }
                } else if (currentOpcode == ZONE_MAP_PROJANIM_SPECIFIC) {
                    // ZONE_MAP_PROJANIM_SPECIFIC
                    // Entity-specific projectile with source tracking
                    // Used for ex. projectiles fired from specific entities
                    packedData = inboundBuffer.g1(); // Starting coordinate
                    local19 = SceneGraph.currentChunkZ * 2 + (packedData & ZONE_COORD_MASK_4BIT); // Start Z (doubled)
                    local23 = SceneGraph.currentChunkX * 2 + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK_4BIT); // Start X (doubled)
                    local27 = inboundBuffer.g1s() + local23; // Target X
                    local31 = inboundBuffer.g1s() + local19; // Target Z
                    local39 = inboundBuffer.g2s(); // Source entity ID (packed)
                    local45 = inboundBuffer.g2s(); // Target entity ID (packed)
                    startHeight = inboundBuffer.g2(); // Projectile spotanim ID
                    endHeight = inboundBuffer.g1s(); // Vertical offset
                    startCycle = inboundBuffer.g1() * HEIGHT_SCALE_FACTOR; // Height offset at target
                    endCycle = inboundBuffer.g2(); // Start cycle
                    Angle = inboundBuffer.g2(); // End cycle
                    arc = inboundBuffer.g1(); // Angle
                    transformValue3 = inboundBuffer.g1(); // Arc height

                    if (arc == NO_ANGLE_SPECIFIED) {
                        arc = -1;
                    }

                    if (local23 >= 0 && local19 >= 0 && local23 < BUILD_AREA_HALF_TILES && local19 < BUILD_AREA_HALF_TILES && local27 >= 0 && local31 >= 0 && local27 < BUILD_AREA_HALF_TILES && local31 < BUILD_AREA_HALF_TILES && startHeight != INVALID_ID_U16) {
                        // Convert 4x4 coords to world coords
                        local27 = local27 * HALF_TILE_SIZE;
                        local23 *= HALF_TILE_SIZE;
                        local31 *= HALF_TILE_SIZE;
                        local19 *= HALF_TILE_SIZE;

                        // If source entity specified, apply model attachment offset
                        if (local39 != 0) {
                            @Pc(1194) int bodySlot;
                            @Pc(1198) PathingEntity sourceEntity;
                            @Pc(1184) int packedEntityId;
                            @Pc(1188) int entityIndex;
                            if (local39 >= 0) {
                                // Positive = NPC
                                packedEntityId = local39 - 1;
                                entityIndex = packedEntityId & ENTITY_INDEX_MASK; // Bottom 11 bits = NPC Index
                                bodySlot = packedEntityId >> ENTITY_SLOT_SHIFT & ENTITY_SLOT_MASK; // Top 4 bits = body part/slot
                                sourceEntity = NpcList.npcs[entityIndex];
                            } else {
                                // Negative = Player
                                packedEntityId = -local39 - 1;
                                bodySlot = packedEntityId >> ENTITY_SLOT_SHIFT & ENTITY_SLOT_MASK;
                                entityIndex = packedEntityId & ENTITY_INDEX_MASK;
                                if (PlayerList.localPid == entityIndex) {
                                    sourceEntity = PlayerList.self;
                                } else {
                                    sourceEntity = PlayerList.players[entityIndex];
                                }
                            }

                            // Apply model transformation for projectile source point
                            if (sourceEntity != null) {
                                @Pc(1232) BasType basType = sourceEntity.getBasType();

                                // Check if this body part has transformation data
                                if (basType.modelRotateTranslate != null && basType.modelRotateTranslate[bodySlot] != null) {
                                    entityIndex = basType.modelRotateTranslate[bodySlot][0]; // X offset
                                    endHeight -= basType.modelRotateTranslate[bodySlot][1]; // Y offset
                                    @Pc(1264) int zOffset = basType.modelRotateTranslate[bodySlot][2]; // Z offset

                                    // Rotate offset based on entity orientation
                                    @Pc(1269) int sinValue = MathUtils.sin[sourceEntity.orientation];
                                    @Pc(1274) int cosValue = MathUtils.cos[sourceEntity.orientation];

                                    // Apply rotation matrix
                                    @Pc(1284) int rotatedXOffset = entityIndex * cosValue + zOffset * sinValue >> FIXED_POINT_SHIFT;
                                    @Pc(1295) int rotatedZOffset = cosValue * zOffset - entityIndex * sinValue >> FIXED_POINT_SHIFT;

                                    local19 += rotatedZOffset;
                                    local23 += rotatedXOffset;
                                }
                            }
                        }

                        // Create projectile wiht adjusted source position
                        @Pc(1331) ProjectileAnimation proj = new ProjectileAnimation(startHeight, Player.currentLevel, local23, local19, SceneGraph.getTileHeight(Player.currentLevel, local23, local19) - endHeight, endCycle + Client.loop, Angle + Client.loop, arc, transformValue3, local45, startCycle);
                        proj.setTarget(local31, endCycle + Client.loop, -startCycle + SceneGraph.getTileHeight(Player.currentLevel, local27, local31), local27);
                        SceneGraph.projectiles.push(new ProjAnimNode(proj));
                    }
                } else if (currentOpcode == ZONE_SOUND_AREA) {
                    // ZONE_SOUND_AREA
                    // Area sound effect
                    packedData = inboundBuffer.g1(); // Zone coordinate
                    local23 = SceneGraph.currentChunkX + (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK); // Zone X
                    local19 = SceneGraph.currentChunkZ + (packedData & ZONE_COORD_MASK); // Zone Z
                    local27 = inboundBuffer.g2(); // Sound effect ID

                    if (local27 == INVALID_ID_U16) {
                        local27 = -1; // No sound
                    }

                    local31 = inboundBuffer.g1(); // Sound parameters packed
                    local39 = local31 >> SOUND_RADIUS_SHIFT & SOUND_RADIUS_MASK; // Sound radius (tile distance)
                    startHeight = inboundBuffer.g1(); // Delay before playing
                    local45 = local31 & SOUND_LOOP_MASK; // Loop count
                    if (local23 >= 0 && local19 >= 0 && local23 < SIZE && local19 < SIZE) {
                        endHeight = local39 + 1; // Radius

                        //Only play if player is within range
                        if (PlayerList.self.movementQueueX[0] >= local23 - endHeight
                                && endHeight + local23 >= PlayerList.self.movementQueueX[0]
                                && PlayerList.self.movementQueueZ[0] >= local19 - endHeight
                                && PlayerList.self.movementQueueZ[0] <= endHeight + local19
                                && Preferences.ambientSoundsVolume != 0 // Volume enabled
                                && local45 > 0 // Has loop count
                                && SoundPlayer.size < MAX_SOUND_QUEUE_SIZE // Sound queue not full
                                && local27 != -1) { // Valid sound ID

                            // Add sound to player queue
                            SoundPlayer.ids[SoundPlayer.size] = local27;
                            SoundPlayer.loops[SoundPlayer.size] = local45; // Loop count
                            SoundPlayer.delays[SoundPlayer.size] = startHeight; // Delay
                            SoundPlayer.sounds[SoundPlayer.size] = null;
                            SoundPlayer.positions[SoundPlayer.size] = local39 + (local23 << POSITION_X_SHIFT) + (local19 << POSITION_Y_SHIFT); // Pack position + radius
                            SoundPlayer.size++;
                        }
                    }
                } else if (currentOpcode == ZONE_OBJ_DEL) {
                    // ZONE_OBJ_DEL
                    // Delete ground objects from zone
                    packedData = inboundBuffer.g1_alt3(); // Zone coordinates
                    local19 = SceneGraph.currentChunkZ + (packedData & ZONE_COORD_MASK); // Zone Z
                    local23 = (packedData >> ZONE_COORD_SHIFT & ZONE_COORD_MASK) + SceneGraph.currentChunkX; // Zone X
                    local27 = inboundBuffer.g2(); // Object type ID

                    if (local23 >= 0 && local19 >= 0 && local23 < CollisionConstants.SIZE && local19 < CollisionConstants.SIZE) {
                        @Pc(1565) LinkList objStackList = SceneGraph.objStacks[Player.currentLevel][local23][local19];

                        if (objStackList != null) {
                            // Find and remove the matching object
                            for (@Pc(1572) ClientObj objIterator = (ClientObj) objStackList.head(); objIterator != null; objIterator = (ClientObj) objStackList.next()) {
                                if (objIterator.value.id == (local27 & INVENTORY_ID_MASK)) {
                                    objIterator.unlink(); // Remove from list
                                    break;
                                }
                            }

                            // Clean up empty list
                            if (objStackList.head() == null) {
                                SceneGraph.objStacks[Player.currentLevel][local23][local19] = null;
                            }
                            sortObjStacks(local23, local19);
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
        @Pc(26) int baseChunkX;
        @Pc(31) int baseChunkZ;
        @Pc(60) int playerZ;
        @Pc(64) int chunkSizeX;
        @Pc(138) int chunkX;
        @Pc(151) int chunkZ;
        @Pc(169) int regionId;
        if (!SceneGraph.dynamicMapRegion) {
            playerPlane = inboundBuffer.g2_alt2();
            regionCount = (packetSize - inboundBuffer.offset) /  XTEA_ENTRY_SIZE_BYTES;
            WorldLoader.regionsXteaKeys = new int[regionCount][XTEA_KEY_SIZE];
            for (baseChunkX = 0; baseChunkX < regionCount; baseChunkX++) {
                for (baseChunkZ = 0; baseChunkZ < XTEA_KEY_SIZE; baseChunkZ++) {
                    WorldLoader.regionsXteaKeys[baseChunkX][baseChunkZ] = inboundBuffer.p4rme();
                }
            }
            baseChunkX = inboundBuffer.g1_alt3();
            baseChunkZ = inboundBuffer.g2();
            playerZ = inboundBuffer.g2_alt2();
            chunkSizeX = inboundBuffer.g2_alt2();
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
            if ((baseChunkZ / MAP_SQUARE_SIZE == TUTORIAL_ISLAND_X1 || baseChunkZ / MAP_SQUARE_SIZE == TUTORIAL_ISLAND_X2) && playerZ / MAP_SQUARE_SIZE == TUTORIAL_ISLAND_Z2) {
                isTutorialIsland = true;
            }
            if (baseChunkZ / MAP_SQUARE_SIZE == TUTORIAL_ISLAND_X1 && playerZ / MAP_SQUARE_SIZE == TUTORIAL_ISLAND_Z5) {
                isTutorialIsland = true;
            }
            for (chunkX = (baseChunkZ - MAP_LOAD_RADIUS) / MAP_SQUARE_SIZE; chunkX <= (baseChunkZ + MAP_LOAD_RADIUS) / MAP_SQUARE_SIZE; chunkX++) {
                for (chunkZ = (playerZ - MAP_LOAD_RADIUS) / MAP_SQUARE_SIZE; chunkZ <= (playerZ + MAP_LOAD_RADIUS) / MAP_SQUARE_SIZE; chunkZ++) {
                    regionId = (chunkX << REGION_ID_SHIFT) + chunkZ;
                    if (isTutorialIsland && (chunkZ == TUTORIAL_ISLAND_Z3 || chunkZ == TUTORIAL_ISLAND_Z6 || chunkZ == TUTORIAL_ISLAND_Z4 || chunkX == TUTORIAL_ISLAND_X3 || chunkX == TUTORIAL_ISLAND_X2 && chunkZ == TUTORIAL_ISLAND_Z1)) {
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
            WorldLoader.initializeMapRegion(baseChunkX, playerZ, baseChunkZ, chunkSizeX, false, playerPlane);
            return;
        }
        playerPlane = inboundBuffer.g2_alt3();
        regionCount = inboundBuffer.g2_alt3();
        baseChunkX = inboundBuffer.g1_alt3();
        baseChunkZ = inboundBuffer.g2_alt3();
        inboundBuffer.accessBits();
        @Pc(391) int plane;
        for (playerZ = 0; playerZ < CollisionConstants.LEVELS; playerZ++) {
            for (chunkSizeX = 0; chunkSizeX < BUILD_AREA_SIZE; chunkSizeX++) {
                for (plane = 0; plane < BUILD_AREA_SIZE; plane++) {
                    chunkX = inboundBuffer.gBit(1);
                    if (chunkX == 1) {
                        dynamicRegionData[playerZ][chunkSizeX][plane] = inboundBuffer.gBit(DYNAMIC_REGION_BITS);
                    } else {
                        dynamicRegionData[playerZ][chunkSizeX][plane] = -1;
                    }
                }
            }
        }
        inboundBuffer.accessBytes();
        playerZ = (packetSize - inboundBuffer.offset) / XTEA_ENTRY_SIZE_BYTES;
        WorldLoader.regionsXteaKeys = new int[playerZ][XTEA_KEY_SIZE];
        for (chunkSizeX = 0; chunkSizeX < playerZ; chunkSizeX++) {
            for (plane = 0; plane < XTEA_KEY_SIZE; plane++) {
                WorldLoader.regionsXteaKeys[chunkSizeX][plane] = inboundBuffer.p4rme();
            }
        }
        chunkSizeX = inboundBuffer.g2();
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
        for (plane = 0; plane < CollisionConstants.LEVELS; plane++) {
            for (chunkX = 0; chunkX < BUILD_AREA_SIZE; chunkX++) {
                for (chunkZ = 0; chunkZ < BUILD_AREA_SIZE; chunkZ++) {
                    regionId = dynamicRegionData[plane][chunkX][chunkZ];
                    if (regionId != -1) {
                        @Pc(555) int regionX = regionId >>  REGION_X_SHIFT & REGION_X_MASK;
                        @Pc(561) int regionZ = regionId >> REGION_Z_SHIFT & REGION_Z_MASK;
                        @Pc(571) int packedRegionId = regionZ / MAP_SQUARE_SIZE + (regionX / MAP_SQUARE_SIZE << REGION_ID_SHIFT);
                        @Pc(573) int regionIndex;
                        for (regionIndex = 0; regionIndex < playerZ; regionIndex++) {
                            if (packedRegionId == WorldLoader.regionBitPacked[regionIndex]) {
                                packedRegionId = -1;
                                break;
                            }
                        }
                        if (packedRegionId != -1) {
                            WorldLoader.regionBitPacked[playerZ] = packedRegionId;
                            @Pc(609) int regionZUnpacked = packedRegionId & BYTE_MASK;
                            regionIndex = packedRegionId >> REGION_ID_SHIFT & BYTE_MASK;
                            WorldLoader.mapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { WorldLoader.m, JString.parseInt(regionIndex), WorldLoader.UNDERSCORE, JString.parseInt(regionZUnpacked) }));
                            WorldLoader.locationsMapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { WorldLoader.l, JString.parseInt(regionIndex), WorldLoader.UNDERSCORE, JString.parseInt(regionZUnpacked) }));
                            WorldLoader.underWaterMapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.um, JString.parseInt(regionIndex), WorldLoader.UNDERSCORE, JString.parseInt(regionZUnpacked) }));
                            WorldLoader.underWaterLocationsMapFileIds[playerZ] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] { LoginManager.ul, JString.parseInt(regionIndex), WorldLoader.UNDERSCORE, JString.parseInt(regionZUnpacked) }));
                            playerZ++;
                        }
                    }
                }
            }
        }
        WorldLoader.initializeMapRegion(baseChunkX, chunkSizeX, regionCount, baseChunkZ, false, playerPlane);
    }

    @OriginalMember(owner = "client!gk", name = "a", descriptor = "(IIBLclient!e;)V")
    public static void readExtendedPlayerInfo(@OriginalArg(0) int flags, @OriginalArg(1) int playerId, @OriginalArg(3) Player player) {
        @Pc(13) int chatFlags;
        @Pc(17) int staffModLevel;
        @Pc(24) int regionCount;
        if ((flags & PLAYER_UPDATE_FLAG_CHAT) != 0) {

            chatFlags = inboundBuffer.g2_al1();
            staffModLevel = inboundBuffer.g1();
            @Pc(21) int len = inboundBuffer.g1();
            regionCount = inboundBuffer.offset;

            @Pc(35) boolean quickChat = (chatFlags & QUICKCHAT_FLAG) != 0;

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
                        chatFlags &= QUICKCHAT_FLAG_MASK;
                        phraseId = quickChatPhrase.id;
                        message = quickChatPhrase.type.decodeMessage(CHAT_PACKET);
                    } else {
                        message = Font.escape(formatChatMessage(CHAT_PACKET).encodeMessage());
                    }
                    player.chatMessage = message.trim();
                    player.chatEffect = chatFlags & CHAT_EFFECT_MASK;
                    player.chatLoops = CHAT_DURATION_LOOPS;
                    player.chatColor = chatFlags >> CHAT_COLOR_SHIFT;
                    if (staffModLevel == STAFF_MOD_LEVEL_JMOD) {
                        Chat.add(phraseId, quickChat ? CHAT_TYPE_QUICKCHAT : CHAT_TYPE_PUBLIC, message, null, JString.concatenate(new JString[] {IMG1, player.getUsername() }));
                    } else if (staffModLevel == STAFF_MOD_LEVEL_PMOD) {
                        Chat.add(phraseId, quickChat ? CHAT_TYPE_QUICKCHAT : CHAT_TYPE_PUBLIC, message, null, JString.concatenate(new JString[] {IMG0, player.getUsername() }));
                    } else {
                        Chat.add(phraseId, quickChat ? CHAT_TYPE_QUICKCHAT : CHAT_TYPE_NORMAL, message, null, player.getUsername());
                    }
                }
            }
            inboundBuffer.offset = regionCount + len;
        }
        if ((flags & PLAYER_UPDATE_FLAG_HIT_PRIMARY) != 0) {
            chatFlags = inboundBuffer.gSmart1or2();
            staffModLevel = inboundBuffer.g1_alt1();
            player.hit(staffModLevel, Client.loop, chatFlags);
            player.hitpointsBarVisibleUntil = Client.loop + HITPOINTS_BAR_DURATION;
            player.hitpointsBar = inboundBuffer.g1_alt3();
        }
        if ((flags & PLAYER_UPDATE_FLAG_ANIM) != 0) {
            chatFlags = inboundBuffer.g2();
            if (chatFlags == INVALID_ID_U16) {
                chatFlags = -1;
            }
            staffModLevel = inboundBuffer.g1();
            Player.animate(staffModLevel, chatFlags, player);
        }
        if ((flags & PLAYER_UPDATE_FLAG_APPEARANCE) != 0) {
            chatFlags = inboundBuffer.g1_alt1();
            @Pc(309) byte[] appearanceData = new byte[chatFlags];
            @Pc(314) Packet appearanceBuffer = new Packet(appearanceData);
            inboundBuffer.gdata(chatFlags, appearanceData);
            PlayerList.appearanceCache[playerId] = appearanceBuffer;
            player.decodeAppearance(appearanceBuffer);
        }
        if ((flags & PLAYER_UPDATE_FLAG_FACE_ENTITY) != 0) {
            player.faceEntity = inboundBuffer.g2_alt2();
            if (player.faceEntity == INVALID_ID_U16) {
                player.faceEntity = -1;
            }
        }
        if ((flags & PLAYER_UPDATE_FLAG_FORCE_MOVE) != 0) {
            player.anInt3380 = inboundBuffer.g1_alt2();
            player.anInt3428 = inboundBuffer.g1();
            player.anInt3416 = inboundBuffer.g1_alt1();
            player.anInt3392 = inboundBuffer.g1();
            player.anInt3395 = inboundBuffer.g2_al1() + Client.loop;
            player.anInt3386 = inboundBuffer.g2_al1() + Client.loop;
            player.anInt3431 = inboundBuffer.g1_alt2();
            player.movementQueueSize = 1;
            player.movementQueueSnapshot = 0;
        }
        if ((flags & PLAYER_UPDATE_FLAG_OVERHEAD_CHAT) != 0) {
            player.chatMessage = inboundBuffer.gjstr();
            if (player.chatMessage.charAt(0) == TILDE_CHAR) {
                player.chatMessage = player.chatMessage.substring(1);
                Chat.addMessage(player.getUsername(), 2, player.chatMessage);
            } else if (player == PlayerList.self) {
                Chat.addMessage(player.getUsername(), 2, player.chatMessage);
            }
            player.chatEffect = 0;
            player.chatColor = 0;
            player.chatLoops = CHAT_DURATION_LOOPS;
        }
        if ((flags & PLAYER_UPDATE_FLAG_HIT_SECONDARY) != 0) {
            chatFlags = inboundBuffer.gSmart1or2();
            staffModLevel = inboundBuffer.g1_alt3();
            player.hit(staffModLevel, Client.loop, chatFlags);
        }
        if ((flags & PLAYER_UPDATE_FLAG_SPOTANIM) != 0) {
            chatFlags = inboundBuffer.g1_alt2();
            @Pc(502) int[] spotAnimIds = new int[chatFlags];
            @Pc(505) int[] spotAnimSlots = new int[chatFlags];
            @Pc(508) int[] spotAnimDelays = new int[chatFlags];
            for (@Pc(510) int animIndex = 0; animIndex < chatFlags; animIndex++) {
                @Pc(521) int spotAnimId = inboundBuffer.g2_al1();
                if (spotAnimId == INVALID_ID_U16) {
                    spotAnimId = -1;
                }
                spotAnimIds[animIndex] = spotAnimId;
                spotAnimSlots[animIndex] = inboundBuffer.g1_alt1();
                spotAnimDelays[animIndex] = inboundBuffer.g2();
            }
            Player.updateLayeredAnimations(spotAnimSlots, spotAnimIds, player, spotAnimDelays);
        }
        if ((flags & PLAYER_UPDATE_FLAG_SPOTANIM_EXTENDED) != 0) {
            chatFlags = inboundBuffer.g2_al1();
            if (chatFlags == INVALID_ID_U16) {
                chatFlags = -1;
            }
            staffModLevel = inboundBuffer.p4rme();
            @Pc(573) boolean shouldReplace = true;
            if (chatFlags != -1 && player.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(chatFlags).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(player.spotAnimId).seqId).priority) {
                shouldReplace = false;
            }
            if (shouldReplace) {
                player.spotAnimStart = (staffModLevel & U16_MASK) + Client.loop;
                player.anInt3361 = 0;
                player.spotanimId = 0;
                player.spotAnimId = chatFlags;
                if (player.spotAnimStart > Client.loop) {
                    player.spotanimId = -1;
                }
                player.spotAnimY = staffModLevel >> UPPER_WORD_SHIFT;
                player.anInt3418 = 1;
                if (player.spotAnimId != -1 && Client.loop == player.spotAnimStart) {
                    regionCount = SpotAnimTypeList.get(player.spotAnimId).seqId;
                    if (regionCount != -1) {
                        @Pc(663) SeqType seqType = SeqTypeList.get(regionCount);
                        if (seqType != null && seqType.frames != null) {
                            SoundPlayer.playSeqSound(player.zFine, seqType, player.xFine, player == PlayerList.self, 0);
                        }
                    }
                }
            }
        }
        if ((flags & PLAYER_UPDATE_FLAG_FACE_COORD) != 0) {
            player.faceX = inboundBuffer.g2();
            player.faceY = inboundBuffer.g2_alt3();
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
        @Pc(11) int hasUpdate = inboundBuffer.gBit(1);
        if (hasUpdate == 0) {
            return;
        }
        @Pc(23) int updateType = inboundBuffer.gBit(2);
        if (updateType == 0) {
            extendedIds[extendedCount++] = LOCAL_PLAYER_INDEX;
            return;
        }
        @Pc(54) int direction;
        @Pc(64) int hasExtendedInfo;
        if (updateType == 1) {
            direction = inboundBuffer.gBit(3);
            PlayerList.self.move(1, direction);
            hasExtendedInfo = inboundBuffer.gBit(1);
            if (hasExtendedInfo == 1) {
                extendedIds[extendedCount++] = LOCAL_PLAYER_INDEX;
            }
        } else if (updateType == 2) {
            if (inboundBuffer.gBit(1) == 1) {
                direction = inboundBuffer.gBit(3);
                PlayerList.self.move(2, direction);
                hasExtendedInfo = inboundBuffer.gBit(3);
                PlayerList.self.move(2, hasExtendedInfo);
            } else {
                direction = inboundBuffer.gBit(3);
                PlayerList.self.move(0, direction);
            }
            direction = inboundBuffer.gBit(1);
            if (direction == 1) {
                extendedIds[extendedCount++] = LOCAL_PLAYER_INDEX;
            }
        } else if (updateType == 3) {
            direction = inboundBuffer.gBit(PLAYER_COORD_BITS);
            hasExtendedInfo = inboundBuffer.gBit(1);
            Player.currentLevel = inboundBuffer.gBit(PLAYER_LEVEL_BITS);
            @Pc(163) int needsExtendedUpdate = inboundBuffer.gBit(1);
            if (needsExtendedUpdate == 1) {
                extendedIds[extendedCount++] = LOCAL_PLAYER_INDEX;
            }
            @Pc(181) int coordZ = inboundBuffer.gBit(PLAYER_COORD_BITS);
            PlayerList.self.teleport(coordZ, hasExtendedInfo == 1, direction);
        }
    }

    @OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(I)V")
    public static void readNewPlayerInfo() {
        while (true) {
            if (inboundBuffer.bitsAvailable(packetSize) >= PLAYER_INFO_BITS_REQUIRED) {
                @Pc(20) int index = inboundBuffer.gBit(PLAYER_INDEX_BITS);
                if (index != LOCAL_PLAYER_INDEX) {
                    @Pc(27) boolean isNewPlayer = false;
                    if (PlayerList.players[index] == null) {
                        PlayerList.players[index] = new Player();
                        isNewPlayer = true;
                        if (PlayerList.appearanceCache[index] != null) {
                            PlayerList.players[index].decodeAppearance(PlayerList.appearanceCache[index]);
                        }
                    }
                    PlayerList.playerIds[PlayerList.playerCount++] = index;
                    @Pc(65) Player player = PlayerList.players[index];
                    player.lastSeenLoop = Client.loop;
                    @Pc(73) int hasExtendedInfo = inboundBuffer.gBit(1);
                    if (hasExtendedInfo == 1) {
                        extendedIds[extendedCount++] = index;
                    }
                    @Pc(92) int dx = inboundBuffer.gBit(PLAYER_DELTA_BITS);
                    @Pc(99) int orientation = PathingEntity.ANGLES[inboundBuffer.gBit(PLAYER_ANGLE_BITS)];
                    if (dx > PLAYER_DELTA_THRESHOLD) {
                        dx -= PLAYER_DELTA_OFFSET;
                    }
                    if (isNewPlayer) {
                        player.dstYaw = player.orientation = orientation;
                    }
                    @Pc(116) int jump = inboundBuffer.gBit(1);
                    @Pc(121) int dz = inboundBuffer.gBit(PLAYER_DELTA_BITS);
                    if (dz > PLAYER_DELTA_THRESHOLD) {
                        dz -= PLAYER_DELTA_OFFSET;
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
            if ((flags & PLAYER_UPDATE_FLAG_EXTENDED) != 0) {
                flags += inboundBuffer.g1() << REGION_ID_SHIFT;
            }
            readExtendedPlayerInfo(flags, id, player);
        }
    }

    @OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(I)V")
    public static void npcCombat() {
        for (@Pc(3) int i = 0; i < extendedCount; i++) {
            @Pc(10) int extendedId = extendedIds[i];
            @Pc(14) Npc npc = NpcList.npcs[extendedId];
            @Pc(18) int updateFlags = inboundBuffer.g1();
            if ((updateFlags & NPC_UPDATE_FLAG_EXTENDED) != 0) {
                updateFlags += inboundBuffer.g1() << REGION_ID_SHIFT;
            }
            @Pc(43) int value;
            @Pc(47) int info;
            if ((updateFlags & NPC_UPDATE_FLAG_HIT_PRIMARY) != 0) {
                value = inboundBuffer.g1(); // Hit value
                info = inboundBuffer.g1_alt2(); // Color
                npc.hit(info, Client.loop, value);
                npc.hitpointsBarVisibleUntil = Client.loop + HITPOINTS_BAR_DURATION;
                npc.hitpointsBar = inboundBuffer.g1_alt3();
            }
            if ((updateFlags & NPC_UPDATE_FLAG_HIT_SECONDARY) != 0) {
                value = inboundBuffer.g1_alt2(); // Hit value
                info = inboundBuffer.g1_alt3(); // Color
                npc.hit(info, Client.loop, value);
            }
            if ((updateFlags & NPC_UPDATE_FLAG_ANIM) != 0) {
                value = inboundBuffer.g2(); // Animation ID
                info = inboundBuffer.g1(); // Sequence
                if (value == INVALID_ID_U16) {
                    value = -1;
                }
                animateNpc(info, value, npc);
            }
            if ((updateFlags & NPC_UPDATE_FLAG_FACE_ENTITY) != 0) {
                npc.faceEntity = inboundBuffer.g2_alt2();
                if (npc.faceEntity == INVALID_ID_U16) {
                    npc.faceEntity = -1;
                }
            }
            if ((updateFlags & NPC_UPDATE_FLAG_SPOTANIM) != 0) {
                value = inboundBuffer.g2_alt2();
                if (value == INVALID_ID_U16) {
                    value = -1;
                }
                info = inboundBuffer.g4me();
                @Pc(147) boolean shouldReplace = true;
                if (value != -1 && npc.spotAnimId != -1 && SeqTypeList.get(SpotAnimTypeList.get(value).seqId).priority < SeqTypeList.get(SpotAnimTypeList.get(npc.spotAnimId).seqId).priority) {
                    shouldReplace = false;
                }
                if (shouldReplace) {
                    npc.spotAnimId = value;
                    npc.spotAnimStart = (info & U16_MASK) + Client.loop;
                    npc.anInt3361 = 0;
                    npc.spotanimId = 0;
                    npc.spotAnimY = info >> UPPER_WORD_SHIFT;
                    npc.anInt3418 = 1;
                    if (npc.spotAnimStart > Client.loop) {
                        npc.spotanimId = -1;
                    }
                    if (npc.spotAnimId != -1 && npc.spotAnimStart == Client.loop) {
                        @Pc(227) int seqId = SpotAnimTypeList.get(npc.spotAnimId).seqId;
                        if (seqId != -1) {
                            @Pc(236) SeqType seqType = SeqTypeList.get(seqId);
                            if (seqType != null && seqType.frames != null) {
                                SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, 0);
                            }
                        }
                    }
                }
            }
            if ((updateFlags & NPC_UPDATE_FLAG_TRANSFORM) != 0) {
                if (npc.type.hasAreaSound()) {
                    AreaSoundManager.remove(npc);
                }
                npc.setNpcType(NpcTypeList.get(inboundBuffer.g2_al1()));
                npc.setSize(npc.type.size);
                npc.anInt3365 = npc.type.nas;
                if (npc.type.hasAreaSound()) {
                    AreaSoundManager.add(npc.movementQueueZ[0], null, 0, npc, npc.movementQueueX[0], Player.currentLevel, null);
                }
            }
            if ((updateFlags & NPC_UPDATE_FLAG_OVERHEAD_CHAT) != 0) {
                npc.chatMessage = inboundBuffer.gjstr();
                npc.chatLoops = NPC_CHAT_DURATION_LOOPS;
            }
            if ((updateFlags & NPC_UPDATE_FLAG_LAYERED_ANIM) != 0) {
                value = inboundBuffer.g1_alt2();
                @Pc(331) int[] animIds = new int[value];
                @Pc(334) int[] animSlots = new int[value];
                @Pc(337) int[] animDelays = new int[value];
                for (@Pc(339) int animIndex = 0; animIndex < value; animIndex++) {
                    @Pc(350) int animId = inboundBuffer.g2_al1();
                    if (animId == INVALID_ID_U16) {
                        animId = -1;
                    }
                    animIds[animIndex] = animId;
                    animSlots[animIndex] = inboundBuffer.g1_alt3();
                    animDelays[animIndex] = inboundBuffer.g2();
                }
                updateNpcLayeredAnimations(animDelays, npc, animSlots, animIds);
            }
            if ((updateFlags & NPC_UPDATE_FLAG_FACE_COORD) != 0) {
                npc.faceX = inboundBuffer.g2_alt2();
                npc.faceY = inboundBuffer.g2();
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(I)V")
    public static void loadAreaNPCs() {
        while (true) {
            if (inboundBuffer.bitsAvailable(packetSize) >= NPC_INFO_BITS_REQUIRED) {
                @Pc(14) int npcIndex = inboundBuffer.gBit(NPC_INDEX_BITS);
                if (npcIndex != NPC_END_MARKER) {
                    @Pc(19) boolean isNewNpc = false;
                    if (NpcList.npcs[npcIndex] == null) {
                        isNewNpc = true;
                        NpcList.npcs[npcIndex] = new Npc();
                    }
                    @Pc(37) Npc npc = NpcList.npcs[npcIndex];
                    NpcList.npcIds[NpcList.npcCount++] = npcIndex;
                    npc.lastSeenLoop = Client.loop;
                    if (npc.type != null && npc.type.hasAreaSound()) {
                        AreaSoundManager.remove(npc);
                    }
                    @Pc(66) int jump = inboundBuffer.gBit(1);
                    @Pc(73) int angle = PathingEntity.ANGLES[inboundBuffer.gBit(NPC_ANGLE_BITS)];
                    if (isNewNpc) {
                        npc.dstYaw = npc.orientation = angle;
                    }
                    @Pc(86) int hasExtendedInfo = inboundBuffer.gBit(1);
                    if (hasExtendedInfo == 1) {
                        extendedIds[extendedCount++] = npcIndex;
                    }
                    @Pc(105) int deltaZ = inboundBuffer.gBit(5);
                    npc.setNpcType(NpcTypeList.get(inboundBuffer.gBit(NPC_TYPE_BITS)));
                    if (deltaZ > NPC_DELTA_THRESHOLD) {
                        deltaZ -= NPC_DELTA_OFFSET;
                    }
                    @Pc(124) int deltaX = inboundBuffer.gBit(NPC_DELTA_BITS);
                    if (deltaX > NPC_DELTA_THRESHOLD) {
                        deltaX -= NPC_DELTA_OFFSET;
                    }
                    npc.setSize(npc.type.size);
                    npc.anInt3365 = npc.type.nas;
                    npc.anInt3376 = npc.type.turnspeed;
                    if (npc.anInt3376 == 0) {
                        npc.orientation = 0;
                    }
                    npc.teleport(npc.getSize(), PlayerList.self.movementQueueX[0] + deltaX, deltaZ + PlayerList.self.movementQueueZ[0], jump == 1);
                    if (npc.type.hasAreaSound()) {
                        AreaSoundManager.add(npc.movementQueueZ[0], null, 0, npc, npc.movementQueueX[0], Player.currentLevel, null);
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
        outboundBuffer.pIsaac1(TRANSMITVAR_VERIFYID);
        outboundBuffer.p2(verifyId);
    }

    @OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(Lclient!wa;I)V")
    public static void writeRandom(@OriginalArg(0) Packet buffer) {
        if (Client.uid != null) {
            try {
                Client.uid.seek(0L);
                Client.uid.write(buffer.data, buffer.offset, RANDOM_DATA_SIZE);
            } catch (@Pc(16) Exception ignored) {
            }
        }
        buffer.offset += RANDOM_DATA_SIZE;
    }

    @OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!wa;I)Lclient!na;")
    public static JString formatChatMessage(@OriginalArg(0) Packet buffer) {
        return WordPack.readStringInternal(buffer);
    }

    @OriginalMember(owner = "runetek4.client!mi", name = "a", descriptor = "([IBLclient!km;[I[I)V")
    public static void updateNpcLayeredAnimations(@OriginalArg(0) int[] slotMasks, @OriginalArg(2) Npc npc, @OriginalArg(3) int[] delays, @OriginalArg(4) int[] animIds) {
        for (@Pc(3) int animIndex = 0; animIndex < animIds.length; animIndex++) {
            @Pc(15) int animId = animIds[animIndex];
            @Pc(19) int slotMask = slotMasks[animIndex];
            @Pc(23) int delay = delays[animIndex];
            for (@Pc(25) int slotIndex = 0; slotMask != 0 && npc.layeredAnimations.length > slotIndex; slotIndex++) {
                if ((slotMask & BIT_SHIFT_1) != 0) {
                    if (animId == -1) {
                        npc.layeredAnimations[slotIndex] = null;
                    } else {
                        @Pc(60) SeqType seqType = SeqTypeList.get(animId);
                        @Pc(65) PathingEntityAnimation animation = npc.layeredAnimations[slotIndex];
                        @Pc(68) int exactMove = seqType.exactmove;
                        if (animation != null) {
                            if (animId == animation.sequenceId) {
                                if (exactMove == EXACTMOVE_RESTART) {
                                    animation = npc.layeredAnimations[slotIndex] = null;
                                } else if (exactMove == EXACTMOVE_RESET) {
                                    animation.frameIndex = 0;
                                    animation.frameTime = 0;
                                    animation.direction = 1;
                                    animation.loopCount = 0;
                                    animation.delay = delay;
                                    SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, 0);
                                } else if (exactMove == EXACTMOVE_CONTINUE) {
                                    animation.frameTime = 0;
                                }
                            } else if (seqType.priority >= SeqTypeList.get(animation.sequenceId).priority) {
                                animation = npc.layeredAnimations[slotIndex] = null;
                            }
                        }
                        if (animation == null) {
                            animation = npc.layeredAnimations[slotIndex] = new PathingEntityAnimation();
                            animation.direction = 1;
                            animation.loopCount = 0;
                            animation.delay = delay;
                            animation.sequenceId = animId;
                            animation.frameTime = 0;
                            animation.frameIndex = 0;
                            SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, 0);
                        }
                    }
                }
                slotMask >>>= BIT_SHIFT_1;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(IIILclient!km;)V")
    public static void animateNpc(@OriginalArg(0) int delay, @OriginalArg(1) int animationId, @OriginalArg(3) Npc npc) {
        if (npc.primarySeqId == animationId && animationId != -1) {
            @Pc(10) SeqType seqType = SeqTypeList.get(animationId);
            @Pc(13) int exactMove = seqType.exactmove;
            if (exactMove == EXACTMOVE_RESET) {
                npc.animationDirection = 1;
                npc.animationFrameDelay = 0;
                npc.animationFrame = 0;
                npc.animationLoopCounter = 0;
                npc.animationDelay = delay;
                SoundPlayer.playSeqSound(npc.zFine, seqType, npc.xFine, false, npc.animationFrameDelay);
            }
            if (exactMove == EXACTMOVE_CONTINUE) {
                npc.animationLoopCounter = 0;
            }
        } else if (animationId == -1 || npc.primarySeqId == -1 || SeqTypeList.get(animationId).priority >= SeqTypeList.get(npc.primarySeqId).priority) {
            npc.animationFrame = 0;
            npc.primarySeqId = animationId;
            npc.animationDirection = 1;
            npc.animationLoopCounter = 0;
            npc.animationDelay = delay;
            npc.movementQueueSnapshot = npc.movementQueueSize;
            npc.animationFrameDelay = 0;
            if (npc.primarySeqId != -1) {
                SoundPlayer.playSeqSound(npc.zFine, SeqTypeList.get(npc.primarySeqId), npc.xFine, false, npc.animationFrameDelay);
            }
        }
    }

    @OriginalMember(owner = "client!dh", name = "a", descriptor = "(IIII)Lclient!wk;")
    public static SubInterface openSubInterface(@OriginalArg(1) int interfaceId, @OriginalArg(2) int windowId, @OriginalArg(3) int modalType) {
        @Pc(9) SubInterface subInterface = new SubInterface();
        subInterface.modalType = modalType;
        subInterface.interfaceId = interfaceId;
        ComponentList.openInterfaces.put(subInterface, windowId);
        ComponentList.resetComponentAnimations(interfaceId);
        @Pc(28) Component component = ComponentList.getComponent(windowId);
        if (component != null) {
            ComponentList.redraw(component);
        }
        if (ClientScriptRunner.modalBackgroundComponent != null) {
            ComponentList.redraw(ClientScriptRunner.modalBackgroundComponent);
            ClientScriptRunner.modalBackgroundComponent = null;
        }
        @Pc(45) int initialMenuSize = MiniMenu.menuActionRow;
        @Pc(53) int menuIndex;
        for (menuIndex = 0; menuIndex < initialMenuSize; menuIndex++) {
            if (ComponentList.shouldRemoveMenuAction(MiniMenu.actions[menuIndex])) {
                MiniMenu.removeActionRow(menuIndex);
            }
        }
        if (MiniMenu.menuActionRow == MOUSE_BUTTON_LEFT) {
            ClientScriptRunner.menuVisible = false;
            ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
        } else {
            ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
            menuIndex = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(95) int menu_index = 0; menu_index < MiniMenu.menuActionRow; menu_index++) {
                @Pc(104) int optionWidth = Fonts.b12Full.getStringWidth(MiniMenu.getOp(menu_index));
                if (optionWidth > menuIndex) {
                    menuIndex = optionWidth;
                }
            }
            ComponentList.menuWidth = menuIndex + MENU_WIDTH_PADDING;
            ComponentList.menuHeight = MiniMenu.menuActionRow * MENU_OPTION_ROW_HEIGHT + (ComponentList.hasScrollbar ? MENU_HEIGHT_WITH_SCROLLBAR : MENU_HEIGHT_NO_SCROLLBAR);
        }
        if (component != null) {
            ComponentList.updateContainerLayout(component, false);
        }
        ComponentList.runInterfaceInitScripts(interfaceId);
        if (ComponentList.topLevelInterface != -1) {
            ComponentList.runScripts(1, ComponentList.topLevelInterface);
        }
        return subInterface;
    }

    @OriginalMember(owner = "client!ah", name = "b", descriptor = "(I)V")
    public static void handleMenuClick() {
        if (ComponentList.clickedInventoryComponent != null || ClientScriptRunner.dragComponent != null) {
            return;
        }
        @Pc(20) int clickButton = Mouse.clickButton;
        @Pc(93) int componentSlot;
        @Pc(99) int componentId;
        if (!ClientScriptRunner.menuVisible) {
            if (clickButton == 1 && MiniMenu.menuActionRow > 0) {
                @Pc(37) short actionType = MiniMenu.actions[MiniMenu.menuActionRow - 1];
                if (actionType == MENU_ACTION_EXAMINE_ITEM || actionType == MENU_ACTION_ITEM_OPT_1 || actionType == MENU_ACTION_ITEM_OPT_2 || actionType == MENU_ACTION_ITEM_OPT_3 || actionType == MENU_ACTION_ITEM_OPT_4 || actionType == MENU_ACTION_ITEM_OPT_5 || actionType == MENU_ACTION_USE_ITEM || actionType == MENU_ACTION_43 || actionType == MENU_ACTION_35 || actionType == MENU_ACTION_58 || actionType == MENU_ACTION_CANCEL || actionType == MENU_ACTION_CONTINUE) {
                    componentSlot = MiniMenu.intArgs1[MiniMenu.menuActionRow - 1];
                    componentId = MiniMenu.intArgs2[MiniMenu.menuActionRow - 1];
                    @Pc(103) Component component = ComponentList.getComponent(componentId);
                    @Pc(106) ServerActiveProperties activeProperties = ComponentList.getServerActiveProperties(component);
                    if (activeProperties.isObjSwapEnabled() || activeProperties.isObjReplaceEnabled()) {
                        ComponentList.lastItemDragTime = 0;
                        ComponentList.draggingClickedInventoryObject = false;
                        if (ComponentList.clickedInventoryComponent != null) {
                            ComponentList.redraw(ComponentList.clickedInventoryComponent);
                        }
                        ComponentList.clickedInventoryComponent = ComponentList.getComponent(componentId);
                        ComponentList.clickedInventoryComponentX = Mouse.mouseClickX;
                        ComponentList.clickedInventoryComponentY = Mouse.mouseClickY;
                        ComponentList.selectedInventorySlot = componentSlot;
                        ComponentList.redraw(ComponentList.clickedInventoryComponent);
                        return;
                    }
                }
            }
            if (clickButton == MOUSE_BUTTON_LEFT && (VarpDomain.oneMouseButton == 1 && MiniMenu.menuActionRow > 2 || MiniMenu.isComponentAction(MiniMenu.menuActionRow - 1))) {
                clickButton = MOUSE_BUTTON_RIGHT;
            }
            if (clickButton == MOUSE_BUTTON_RIGHT && MiniMenu.menuActionRow > 0 || MiniMenu.menuState == MENU_STATE_OPEN) {
                ClientScriptRunner.determineMenuSize();
            }
            if (clickButton == MOUSE_BUTTON_LEFT && MiniMenu.menuActionRow > 0 || MiniMenu.menuState == MENU_STATE_EXECUTE) {
                MiniMenu.processMenuActions();
            }
            return;
        }
        @Pc(204) int menuX;
        if (clickButton != MOUSE_BUTTON_LEFT) {
            componentSlot = Mouse.lastMouseY;
            menuX = Mouse.lastMouseX;
            if (menuX < ComponentList.menuX - MENU_BOUNDS_PADDING || menuX > ComponentList.menuWidth + ComponentList.menuX + MENU_BOUNDS_PADDING || ComponentList.menuY - MENU_BOUNDS_PADDING > componentSlot || componentSlot > ComponentList.menuHeight + ComponentList.menuY + MENU_BOUNDS_PADDING) {
                ClientScriptRunner.menuVisible = false;
                ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
            }
        }
        if (clickButton != MOUSE_BUTTON_LEFT) {
            return;
        }
        menuX = ComponentList.menuX;
        componentSlot = ComponentList.menuY;
        componentId = ComponentList.menuWidth;
        @Pc(265) int mouseClickX = Mouse.mouseClickX;
        @Pc(267) int mouseClickY = Mouse.mouseClickY;
        @Pc(269) int selectedRow = -1;
        for (@Pc(271) int rowIndex = 0; rowIndex < MiniMenu.menuActionRow; rowIndex++) {
            @Pc(289) int rowYPosition;
            if (ComponentList.hasScrollbar) {
                rowYPosition = (MiniMenu.menuActionRow - rowIndex - 1) * MENU_OPTION_ROW_HEIGHT + componentSlot + MENU_Y_OFFSET_SCROLLBAR;
            } else {
                rowYPosition = (MiniMenu.menuActionRow - rowIndex - 1) * MENU_OPTION_ROW_HEIGHT + componentSlot + MENU_Y_OFFSET_NO_SCROLLBAR;
            }
            if (mouseClickX > menuX && menuX + componentId > mouseClickX && rowYPosition - MENU_HIT_AREA_TOP < mouseClickY && rowYPosition + MENU_HIT_AREA_BOTTOM > mouseClickY) {
                selectedRow = rowIndex;
            }
        }
        if (selectedRow != -1) {
            MiniMenu.doAction(selectedRow);
        }
        ClientScriptRunner.menuVisible = false;
        ComponentList.redrawScreen(ComponentList.menuX, ComponentList.menuWidth, ComponentList.menuY, ComponentList.menuHeight);
    }
}
