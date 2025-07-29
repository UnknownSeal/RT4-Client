package com.jagex.runetek4.client;

import com.jagex.runetek4.*;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.data.cache.CacheArchive;
import com.jagex.runetek4.data.cache.media.component.Widget;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.core.io.PacketBit;
import com.jagex.runetek4.entity.entity.NpcList;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.game.inventory.Inv;
import com.jagex.runetek4.game.world.WorldLoader;
import com.jagex.runetek4.graphics.gl.GlRaster;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.core.DisplayMode;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.input.MouseCapturer;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.network.BufferedSocket;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.graphics.raster.SoftwareRaster;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.sprite.Sprite;
import com.jagex.runetek4.ui.widget.WidgetList;
import com.jagex.runetek4.ui.widget.MiniMap;
import com.jagex.runetek4.ui.widget.MiniMenu;
import com.jagex.runetek4.util.debug.Cheat;
import com.jagex.runetek4.game.world.WorldList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;

public class LoginManager {

    // Jagex's RSA key:
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

    @OriginalMember(owner = "runetek4.client!wj", name = "f", descriptor = "Lclient!na;")
    public static final JString ul = JString.parse("ul");

    @OriginalMember(owner = "runetek4.client!oe", name = "o", descriptor = "Lclient!na;")
    public static final JString n = JString.parse("n");

    @OriginalMember(owner = "runetek4.client!nb", name = "a", descriptor = "Lclient!na;")
    public static final JString um = JString.parse("um");

    @OriginalMember(owner = "client!bg", name = "g", descriptor = "Lclient!i;")
    public static final PacketBit packet = new PacketBit(5000);

    @OriginalMember(owner = "runetek4.client!qi", name = "z", descriptor = "Lclient!qf;")
    public static Sprite loginSprite1;

    @OriginalMember(owner = "runetek4.client!d", name = "ib", descriptor = "Lclient!qf;")
    public static Sprite loginSprite2;

    @OriginalMember(owner = "runetek4.client!hd", name = "e", descriptor = "Lclient!qf;")
    public static Sprite loginSprite3;

    @OriginalMember(owner = "runetek4.client!nb", name = "i", descriptor = "Lclient!qf;")
    public static Sprite loginSprite4;

    @OriginalMember(owner = "runetek4.client!oi", name = "h", descriptor = "Lclient!qf;")
    public static Sprite loginSprite5;

    @OriginalMember(owner = "runetek4.client!qf", name = "M", descriptor = "I")
    public static int idleNetCycles = 0;

    @OriginalMember(owner = "runetek4.client!gk", name = "h", descriptor = "I")
    public static int mapFlagZ = 0;

    @OriginalMember(owner = "runetek4.client!ja", name = "n", descriptor = "I")
    public static int mapFlagX = 0;

    @OriginalMember(owner = "runetek4.client!ol", name = "V", descriptor = "I")
    public static int step = 0;

    @OriginalMember(owner = "runetek4.client!vk", name = "b", descriptor = "I")
    public static int reply = -2;

    @OriginalMember(owner = "runetek4.client!af", name = "c", descriptor = "I")
    public static int anInt39 = -1;

    @OriginalMember(owner = "runetek4.client!rl", name = "X", descriptor = "I")
    public static int autoStep = 0;

    @OriginalMember(owner = "runetek4.client!pl", name = "i", descriptor = "I")
    public static int connectionRetryCount = 0;

    @OriginalMember(owner = "client!bj", name = "Y", descriptor = "I")
    public static int connectionTimeoutCounter = 0;

    @OriginalMember(owner = "runetek4.client!qi", name = "A", descriptor = "I")
    public static int disallowResult = -1;

    @OriginalMember(owner = "runetek4.client!pg", name = "S", descriptor = "I")
    public static int staffModLevel = 0;

    @OriginalMember(owner = "runetek4.client!sd", name = "X", descriptor = "Z")
    public static boolean aBoolean247 = false;

    @OriginalMember(owner = "runetek4.client!qf", name = "X", descriptor = "Lclient!be;")
    public static Widget hoveredWidget = null;

    @OriginalMember(owner = "runetek4.client!qg", name = "W", descriptor = "Z")
    public static boolean playerMember = false;

    @OriginalMember(owner = "runetek4.client!mf", name = "X", descriptor = "I")
    public static int loginScreenId;

    @OriginalMember(owner = "runetek4.client!sk", name = "ib", descriptor = "I")
    public static int hopTime = 0;

    @OriginalMember(owner = "runetek4.client!hd", name = "a", descriptor = "I")
    public static int loops = 0;

    @OriginalMember(owner = "runetek4.client!wh", name = "s", descriptor = "I")
    public static int errors = 0;

    @OriginalMember(owner = "runetek4.client!pi", name = "P", descriptor = "J")
    public static long serverKey = 0L;

    @OriginalMember(owner = "runetek4.client!ud", name = "O", descriptor = "I")
    public static int playerModLevel = 0;

    @OriginalMember(owner = "runetek4.client!jk", name = "G", descriptor = "Z")
    public static boolean playerUnderage = false;

    @OriginalMember(owner = "runetek4.client!ql", name = "c", descriptor = "Z")
    public static boolean parentalChatConsent = false;

    @OriginalMember(owner = "runetek4.client!aa", name = "l", descriptor = "Z")
    public static boolean membersWorld = false;

    @OriginalMember(owner = "client!gm", name = "bb", descriptor = "Z")
    public static boolean worldQuickChat = false;

    @OriginalMember(owner = "client!c", name = "hb", descriptor = "Z")
    public static boolean parentalAdvertConsent = false;

    @OriginalMember(owner = "runetek4.client!da", name = "ab", descriptor = "I")
    public static int anInt1275;

    @OriginalMember(owner = "client!ee", name = "c", descriptor = "I")
    public static int anInt1736;

    @OriginalMember(owner = "client!gl", name = "d", descriptor = "I")
    public static int anInt2261;

    @OriginalMember(owner = "runetek4.client!ii", name = "y", descriptor = "I")
    public static int anInt2910;

    @OriginalMember(owner = "runetek4.client!kk", name = "i", descriptor = "I")
    public static int anInt3324;

    @OriginalMember(owner = "runetek4.client!nf", name = "e", descriptor = "I")
    public static int anInt4073;

    @OriginalMember(owner = "runetek4.client!pk", name = "Y", descriptor = "I")
    public static int anInt4581;

    @OriginalMember(owner = "runetek4.client!sm", name = "b", descriptor = "I")
    public static int anInt5208;

    @OriginalMember(owner = "runetek4.client!ug", name = "p", descriptor = "I")
    public static int anInt5457;

    @OriginalMember(owner = "runetek4.client!uj", name = "E", descriptor = "I")
    public static int anInt5556;

    @OriginalMember(owner = "runetek4.client!vf", name = "c", descriptor = "I")
    public static int anInt5752;

    @OriginalMember(owner = "runetek4.client!j", name = "g", descriptor = "(I)V")
    public static void method4637() {
        loginSprite4 = null;
        loginSprite2 = null;
        loginSprite3 = null;
        loginSprite5 = null;
        loginSprite1 = null;
    }

    @OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(I)V")
    public static void method1841() {
        if (!ClientScriptRunner.menuVisible) {
            if (MiniMenu.menuState != 0) {
                ClientScriptRunner.scriptMouseX = Mouse.lastClickX;
                ClientScriptRunner.scriptMouseY = Mouse.lastClickY;
            } else if (Mouse.clickButton == 0) {
                ClientScriptRunner.scriptMouseX = Mouse.lastMouseX;
                ClientScriptRunner.scriptMouseY = Mouse.lastMouseY;
            } else {
                ClientScriptRunner.scriptMouseX = Mouse.mouseClickX;
                ClientScriptRunner.scriptMouseY = Mouse.mouseClickY;
            }
            MiniMenu.menuActionRow = 1;
            MiniMenu.ops[0] = LocalizedText.CANCEL;
            MiniMenu.opBases[0] = JString.EMPTY;
            MiniMenu.actions[0] = 1005;
            MiniMenu.cursors[0] = MiniMenu.defaultCursor;
        }
        if (WidgetList.topLevelInterface != -1) {
            WidgetList.method1949(WidgetList.topLevelInterface);
        }
        @Pc(60) int widgetIndex;
        for (widgetIndex = 0; widgetIndex < WidgetList.rectangles; widgetIndex++) {
            if (WidgetList.widgetNeedsRedraw[widgetIndex]) {
                WidgetList.rectangleRedraw[widgetIndex] = true;
            }
            WidgetList.widgetRedrawPrevious[widgetIndex] = WidgetList.widgetNeedsRedraw[widgetIndex];
            WidgetList.widgetNeedsRedraw[widgetIndex] = false;
        }
        hoveredWidget = null;
        ClientScriptRunner.anInt2503 = -1;
        WidgetList.anInt5574 = -1;
        WidgetList.mouseOverInventoryInterface = null;
        if (GlRenderer.enabled) {
            ClientScriptRunner.aBoolean299 = true;
        }
        WidgetList.anInt4311 = Client.loop;
        if (WidgetList.topLevelInterface != -1) {
            WidgetList.rectangles = 0;
            CacheArchive.method182();
        }
        if (GlRenderer.enabled) {
            GlRaster.method1177();
        } else {
            SoftwareRaster.resetBounds();
        }
        MiniMenu.sort();
        if (ClientScriptRunner.menuVisible) {
            if (WidgetList.hasScrollbar) {
                MiniMenu.drawB();
            } else {
                MiniMenu.drawA();
            }
        } else if (hoveredWidget != null) {
            MiniMenu.method1207(hoveredWidget, ClientScriptRunner.anInt3484, ClientScriptRunner.anInt3260);
        } else if (ClientScriptRunner.anInt2503 != -1) {
            MiniMenu.method1207(null, WidgetList.anInt5574, ClientScriptRunner.anInt2503);
        }
        widgetIndex = ClientScriptRunner.menuVisible ? -1 : MiniMenu.getShiftClickOption();
        if (widgetIndex == -1) {
            widgetIndex = ClientScriptRunner.anInt5794;
        }
        WidgetList.method1750(widgetIndex);
        if (MiniMenu.anInt3096 == 1) {
            MiniMenu.anInt3096 = 2;
        }
        if (Protocol.anInt4422 == 1) {
            Protocol.anInt4422 = 2;
        }
        if (Cheat.rectDebug == 3) {
            for (@Pc(189) int rectIndex = 0; rectIndex < WidgetList.rectangles; rectIndex++) {
                if (WidgetList.widgetRedrawPrevious[rectIndex]) {
                    if (GlRenderer.enabled) {
                        GlRaster.fillRectAlpha(WidgetList.rectangleX[rectIndex], WidgetList.rectangleY[rectIndex], WidgetList.rectangleWidth[rectIndex], WidgetList.rectangleHeight[rectIndex], 16711935, 128);
                    } else {
                        SoftwareRaster.fillRectAlpha(WidgetList.rectangleX[rectIndex], WidgetList.rectangleY[rectIndex], WidgetList.rectangleWidth[rectIndex], WidgetList.rectangleHeight[rectIndex], 16711935, 128);
                    }
                } else if (WidgetList.rectangleRedraw[rectIndex]) {
                    if (GlRenderer.enabled) {
                        GlRaster.fillRectAlpha(WidgetList.rectangleX[rectIndex], WidgetList.rectangleY[rectIndex], WidgetList.rectangleWidth[rectIndex], WidgetList.rectangleHeight[rectIndex], 16711680, 128);
                    } else {
                        SoftwareRaster.fillRectAlpha(WidgetList.rectangleX[rectIndex], WidgetList.rectangleY[rectIndex], WidgetList.rectangleWidth[rectIndex], WidgetList.rectangleHeight[rectIndex], 16711680, 128);
                    }
                }
            }
        }
        AreaSoundManager.redraw(Protocol.sceneDelta, PlayerList.self.xFine, PlayerList.self.zFine, Player.plane);
        Protocol.sceneDelta = 0;
    }

    @OriginalMember(owner = "client!ca", name = "h", descriptor = "(I)V")
    public static void setupLoadingScreenRegion() {
        @Pc(10) int centerRegionX = (Camera.renderX >> 10) + (Camera.originX >> 3);
        @Pc(23) int centerRegionZ = (Camera.renderZ >> 10) + (Camera.originZ >> 3);
        WorldLoader.locationMapFilesBuffer = new byte[18][];
        WorldLoader.underWaterLocationsMapFileIds = new int[18];
        WorldLoader.npcSpawnsFilesBuffer = new byte[18][];
        WorldLoader.mapFileIds = new int[18];
        WorldLoader.regionsXteaKeys = new int[18][4];
        WorldLoader.underWaterMapFilesBuffer = new byte[18][];
        WorldLoader.regionBitPacked = new int[18];
        WorldLoader.mapFilesBuffer = new byte[18][];
        WorldLoader.npcSpawnsFileIds = new int[18];
        WorldLoader.underWaterMapFileIds = new int[18];
        WorldLoader.locationsMapFileIds = new int[18];
        WorldLoader.underWaterLocationsMapFilesBuffer = new byte[18][];
        @Pc(74) int regionIndex = 0;
        @Pc(80) int regionX;
        for (regionX = (centerRegionX - 6) / 8; regionX <= (centerRegionX + 6) / 8; regionX++) {
            for (@Pc(97) int regionZ = (centerRegionZ - 6) / 8; regionZ <= (centerRegionZ + 6) / 8; regionZ++) {
                @Pc(115) int regionCoordinate = (regionX << 8) + regionZ;
                WorldLoader.regionBitPacked[regionIndex] = regionCoordinate;
                WorldLoader.mapFileIds[regionIndex] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] {WorldLoader.m, JString.parseInt(regionX), WorldLoader.UNDERSCORE, JString.parseInt(regionZ) }));
                WorldLoader.locationsMapFileIds[regionIndex] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] {WorldLoader.l, JString.parseInt(regionX), WorldLoader.UNDERSCORE, JString.parseInt(regionZ) }));
                WorldLoader.npcSpawnsFileIds[regionIndex] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] {n, JString.parseInt(regionX), WorldLoader.UNDERSCORE, JString.parseInt(regionZ) }));
                WorldLoader.underWaterMapFileIds[regionIndex] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] {um, JString.parseInt(regionX), WorldLoader.UNDERSCORE, JString.parseInt(regionZ) }));
                WorldLoader.underWaterLocationsMapFileIds[regionIndex] = Client.js5Archive5.getGroupId(JString.concatenate(new JString[] {ul, JString.parseInt(regionX), WorldLoader.UNDERSCORE, JString.parseInt(regionZ) }));
                if (WorldLoader.npcSpawnsFileIds[regionIndex] == -1) {
                    WorldLoader.mapFileIds[regionIndex] = -1;
                    WorldLoader.locationsMapFileIds[regionIndex] = -1;
                    WorldLoader.underWaterMapFileIds[regionIndex] = -1;
                    WorldLoader.underWaterLocationsMapFileIds[regionIndex] = -1;
                }
                regionIndex++;
            }
        }
        for (regionX = regionIndex; regionX < WorldLoader.npcSpawnsFileIds.length; regionX++) {
            WorldLoader.npcSpawnsFileIds[regionX] = -1;
            WorldLoader.mapFileIds[regionX] = -1;
            WorldLoader.locationsMapFileIds[regionX] = -1;
            WorldLoader.underWaterMapFileIds[regionX] = -1;
            WorldLoader.underWaterLocationsMapFileIds[regionX] = -1;
        }
        WorldLoader.initializeMapRegion(0, centerRegionZ, centerRegionX, 8, true, 8);
    }

    @OriginalMember(owner = "client!se", name = "a", descriptor = "(Lclient!na;Lclient!na;IB)V")
    public static void login(@OriginalArg(0) JString username, @OriginalArg(1) JString password, @OriginalArg(2) int arg2) {
        Player.password = password;
        anInt39 = arg2;
        Player.usernameInput = username;
        if (Player.usernameInput.strEquals(JString.EMPTY) || Player.password.strEquals(JString.EMPTY)) {
            reply = 3;
        } else if (Player.worldId == -1) {
            connectionTimeoutCounter = 0;
            connectionRetryCount = 0;
            reply = -3;
            autoStep = 1;
            @Pc(43) Packet login_packet = new Packet(128);
            login_packet.p1(10);
            login_packet.p2((int) (Math.random() * 99999.0D));
            login_packet.p2(530);
            login_packet.p8(Player.usernameInput.encode37());
            login_packet.p4((int) (Math.random() * 9.9999999E7D));
            login_packet.pjstr(Player.password);
            login_packet.p4((int) (Math.random() * 9.9999999E7D));
            login_packet.rsaenc(RSA_EXPONENT, RSA_MODULUS);
            Protocol.outboundBuffer.offset = 0;
            Protocol.outboundBuffer.p1(210);
            Protocol.outboundBuffer.p1(login_packet.offset);
            Protocol.outboundBuffer.pdata(login_packet.data, login_packet.offset);
        } else {
            clear();
        }
    }

    @OriginalMember(owner = "client!p", name = "a", descriptor = "(I)V")
    public static void advanceLoginStep() {
        if (step == 5) {
            step = 6;
        }
    }

    @OriginalMember(owner = "client!dm", name = "d", descriptor = "(I)V")
    public static void clear() {
        aBoolean247 = false;
        hopTime = 0;
        reply = -3;
        loops = 0;
        step = 1;
        errors = 0;
        disallowResult = -1;
    }

    @OriginalMember(owner = "client!ch", name = "b", descriptor = "(B)V")
    public static void loopAuto() {
        if (autoStep == 0) {
            return;
        }
        try {
            if (++connectionTimeoutCounter > 1500) {
                if (Protocol.gameServerSocket != null) {
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                }
                if (connectionRetryCount >= 1) {
                    reply = -5;
                    autoStep = 0;
                    return;
                }
                connectionTimeoutCounter = 0;
                connectionRetryCount++;
                autoStep = 1;
                if (Client.worldListPort == Client.worldListDefaultPort) {
                    Client.worldListPort = Client.worldListAlternatePort;
                } else {
                    Client.worldListPort = Client.worldListDefaultPort;
                }
            }
            if (autoStep == 1) {
                Protocol.socketRequest = GameShell.signLink.openSocket(Client.worldListHostname, Client.worldListPort);
                autoStep = 2;
            }
            @Pc(126) int local126;
            if (autoStep == 2) {
                if (Protocol.socketRequest.status == 2) {
                    throw new IOException();
                }
                if (Protocol.socketRequest.status != 1) {
                    return;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.socketRequest = null;
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                if (Client.musicChannel != null) {
                    Client.musicChannel.method3571();
                }
                if (Client.soundChannel != null) {
                    Client.soundChannel.method3571();
                }
                local126 = Protocol.gameServerSocket.read();
                if (Client.musicChannel != null) {
                    Client.musicChannel.method3571();
                }
                if (Client.soundChannel != null) {
                    Client.soundChannel.method3571();
                }
                if (local126 != 101) {
                    reply = local126;
                    autoStep = 0;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                autoStep = 3;
            }
            if (autoStep == 3) {
                if (Protocol.gameServerSocket.available() < 2) {
                    return;
                }
                local126 = Protocol.gameServerSocket.read() << 8 | Protocol.gameServerSocket.read();
                WorldList.hopWorld(local126);
                if (Player.worldId == -1) {
                    autoStep = 0;
                    reply = 6;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                autoStep = 0;
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
                clear();
            }
        } catch (@Pc(210) IOException local210) {
            if (Protocol.gameServerSocket != null) {
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
            }
            if (connectionRetryCount < 1) {
                if (Client.worldListPort == Client.worldListDefaultPort) {
                    Client.worldListPort = Client.worldListAlternatePort;
                } else {
                    Client.worldListPort = Client.worldListDefaultPort;
                }
                autoStep = 1;
                connectionTimeoutCounter = 0;
                connectionRetryCount++;
            } else {
                reply = -4;
                autoStep = 0;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ri", name = "a", descriptor = "(B)V")
    public static void loop() {
        if (step == 0 || step == 5) {
            return;
        }
        try {
            if (++loops > 2000) {
                if (Protocol.gameServerSocket != null) {
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                }
                if (errors >= 1) {
                    reply = -5;
                    step = 0;
                    return;
                }
                loops = 0;
                if (Client.port == Client.defaultPort) {
                    Client.port = Client.alternatePort;
                } else {
                    Client.port = Client.defaultPort;
                }
                step = 1;
                errors++;
            }
            if (step == 1) {
                Protocol.socketRequest = GameShell.signLink.openSocket(Client.hostname, Client.port);
                step = 2;
            }
            if (step == 2) {
                if (Protocol.socketRequest.status == 2) {
                    throw new IOException();
                }
                if (Protocol.socketRequest.status != 1) {
                    return;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.socketRequest = null;
                @Pc(106) long local106 = Player.name37 = Player.usernameInput.encode37();
                Protocol.outboundBuffer.offset = 0;
                Protocol.outboundBuffer.p1(14);
                @Pc(120) int local120 = (int) (local106 >> 16 & 0x1FL);
                Protocol.outboundBuffer.p1(local120);
                Protocol.gameServerSocket.write(2, Protocol.outboundBuffer.data);
                if (Client.musicChannel != null) {
                    Client.musicChannel.method3571();
                }
                if (Client.soundChannel != null) {
                    Client.soundChannel.method3571();
                }
                @Pc(150) int local150 = Protocol.gameServerSocket.read();
                if (Client.musicChannel != null) {
                    Client.musicChannel.method3571();
                }
                if (Client.soundChannel != null) {
                    Client.soundChannel.method3571();
                }
                if (local150 != 0) {
                    reply = local150;
                    step = 0;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                step = 3;
            }
            if (step == 3) {
                if (Protocol.gameServerSocket.available() < 8) {
                    return;
                }
                Protocol.gameServerSocket.read(0, 8, Protocol.inboundBuffer.data);
                Protocol.inboundBuffer.offset = 0;
                serverKey = Protocol.inboundBuffer.g8();
                @Pc(210) int[] seed = new int[4];
                Protocol.outboundBuffer.offset = 0;
                seed[2] = (int) (serverKey >> 32);
                seed[3] = (int) serverKey;
                seed[1] = (int) (Math.random() * 9.9999999E7D);
                seed[0] = (int) (Math.random() * 9.9999999E7D);
                Protocol.outboundBuffer.p1(10);
                Protocol.outboundBuffer.p4(seed[0]);
                Protocol.outboundBuffer.p4(seed[1]);
                Protocol.outboundBuffer.p4(seed[2]);
                Protocol.outboundBuffer.p4(seed[3]);
                Protocol.outboundBuffer.p8(Player.usernameInput.encode37());
                Protocol.outboundBuffer.pjstr(Player.password);
                Protocol.outboundBuffer.rsaenc(RSA_EXPONENT, RSA_MODULUS);
                packet.offset = 0;
                if (Client.gameState == 40) {
                    packet.p1(18);
                } else {
                    packet.p1(16);
                }

                packet.p2(Protocol.outboundBuffer.offset + Packet.gjstrlen(Client.settings) + 159);
                packet.p4(530);
                packet.p1(anInt39);
                packet.p1(Client.advertSuppressed ? 1 : 0);
                packet.p1(1);
                packet.p1(DisplayMode.getWindowMode());
                packet.p2(GameShell.canvasWidth);
                packet.p2(GameShell.canvasHeigth);
                packet.p1(Preferences.antiAliasingMode);
                Client.writeUid(packet);
                packet.pjstr(Client.settings);
                packet.p4(Client.affiliate);
                packet.p4(Preferences.toInt());
                Preferences.sentToServer = true;
                packet.p2(Protocol.verifyId);
                packet.p4(Client.js5Archive0.getChecksum());
                packet.p4(Client.js5Archive1.getChecksum());
                packet.p4(Client.js5Archive2.getChecksum());
                packet.p4(Client.js5Archive3.getChecksum());
                packet.p4(Client.js5Archive4.getChecksum());
                packet.p4(Client.js5Archive5.getChecksum());
                packet.p4(Client.js5Archive6.getChecksum());
                packet.p4(Client.js5Archive7.getChecksum());
                packet.p4(Client.js5Archive8.getChecksum());
                packet.p4(Client.js5Archive9.getChecksum());
                packet.p4(Client.js5Archive10.getChecksum());
                packet.p4(Client.js5Archive11.getChecksum());
                packet.p4(Client.js5Archive12.getChecksum());
                packet.p4(Client.js5Archive13.getChecksum());
                packet.p4(Client.js5Archive14.getChecksum());
                packet.p4(Client.js5Archive15.getChecksum());
                packet.p4(Client.js5Archive16.getChecksum());
                packet.p4(Client.js5Archive17.getChecksum());
                packet.p4(Client.js5Archive18.getChecksum());
                packet.p4(Client.js5Archive19.getChecksum());
                packet.p4(Client.js5Archive20.getChecksum());
                packet.p4(Client.js5Archive21.getChecksum());
                packet.p4(Client.js5Archive22.getChecksum());
                packet.p4(Client.js5Archive23.getChecksum());
                packet.p4(Client.js5Archive24.getChecksum());
                packet.p4(Client.js5Archive25.getChecksum());
                packet.p4(Client.js5Archive26.getChecksum());
                packet.p4(Client.js5Archive27.getChecksum());
                packet.pdata(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
                Protocol.gameServerSocket.write(packet.offset, packet.data);
                Protocol.outboundBuffer.Isaac(seed);
                for (@Pc(583) int i = 0; i < 4; i++) {
                    seed[i] += 50;
                }
                Protocol.inboundBuffer.Isaac(seed);
                step = 4;
            }
            if (step == 4) {
                if (Protocol.gameServerSocket.available() < 1) {
                    return;
                }
                @Pc(623) int local623 = Protocol.gameServerSocket.read();
                if (local623 == 21) {
                    step = 7;
                } else if (local623 == 29) {
                    step = 10;
                } else if (local623 == 1) {
                    step = 5;
                    reply = local623;
                    return;
                } else if (local623 == 2) {
                    step = 8;
                } else if (local623 == 15) {
                    step = 0;
                    reply = local623;
                    return;
                } else if (local623 == 23 && errors < 1) {
                    step = 1;
                    errors++;
                    loops = 0;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                } else {
                    reply = local623;
                    step = 0;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
            }
            if (step == 6) {
                Protocol.outboundBuffer.offset = 0;
                Protocol.outboundBuffer.pIsaac1(17);
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                step = 4;
                return;
            }
            if (step == 7) {
                if (Protocol.gameServerSocket.available() >= 1) {
                    hopTime = (Protocol.gameServerSocket.read() + 3) * 60;
                    step = 0;
                    reply = 21;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                return;
            }
            if (step == 10) {
                if (Protocol.gameServerSocket.available() >= 1) {
                    disallowResult = Protocol.gameServerSocket.read();
                    step = 0;
                    reply = 29;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                return;
            }
            if (step == 8) {
                if (Protocol.gameServerSocket.available() < 14) {
                    return;
                }
                Protocol.gameServerSocket.read(0, 14, Protocol.inboundBuffer.data);
                Protocol.inboundBuffer.offset = 0;
                staffModLevel = Protocol.inboundBuffer.g1();
                playerModLevel = Protocol.inboundBuffer.g1();
                playerUnderage = Protocol.inboundBuffer.g1() == 1;
                parentalChatConsent = Protocol.inboundBuffer.g1() == 1;
                parentalAdvertConsent = Protocol.inboundBuffer.g1() == 1;
                worldQuickChat = Protocol.inboundBuffer.g1() == 1;
                MouseCapturer.enabled = Protocol.inboundBuffer.g1() == 1;
                PlayerList.selfId = Protocol.inboundBuffer.g2();
                playerMember = Protocol.inboundBuffer.g1() == 1;
                membersWorld = Protocol.inboundBuffer.g1() == 1;
                LocTypeList.setAllowMembers(membersWorld);
                ObjTypeList.setAllowMembers(membersWorld);
                Protocol.opcode = Protocol.inboundBuffer.gIssac1();
                Protocol.packetSize = Protocol.inboundBuffer.g2();
                step = 9;
            }
            if (step == 9) {
                if (Protocol.gameServerSocket.available() < Protocol.packetSize) {
                    return;
                }
                Protocol.inboundBuffer.offset = 0;
                Protocol.gameServerSocket.read(0, Protocol.packetSize, Protocol.inboundBuffer.data);
                reply = 2;
                step = 0;
                Client.method4221();
                SceneGraph.centralZoneX = -1;
                Protocol.readRebuildPacket(false);
                Protocol.opcode = -1;
                return;
            }
        } catch (@Pc(977) IOException local977) {
            if (Protocol.gameServerSocket != null) {
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
            }
            if (errors >= 1) {
                step = 0;
                reply = -4;
            } else {
                step = 1;
                loops = 0;
                errors++;
                if (Client.defaultPort == Client.port) {
                    Client.port = Client.alternatePort;
                } else {
                    Client.port = Client.defaultPort;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(Z)V")
    public static void reconnect() {
        Protocol.outboundBuffer.offset = 0;
        Protocol.opcode3 = -1;
        ClientScriptRunner.menuVisible = false;
        Protocol.packetSize = 0;
        mapFlagX = 0;
        MiniMenu.menuActionRow = 0;
        Protocol.opcode2 = -1;
        MiniMap.state = 0;
        Player.systemUpdateTimer = 0;
        Protocol.opcode4 = -1;
        Protocol.inboundBuffer.offset = 0;
        idleNetCycles = 0;
        Protocol.opcode = -1;
        @Pc(35) int i;
        for (i = 0; i < PlayerList.players.length; i++) {
            if (PlayerList.players[i] != null) {
                PlayerList.players[i].faceEntity = -1;
            }
        }
        for (i = 0; i < NpcList.npcs.length; i++) {
            if (NpcList.npcs[i] != null) {
                NpcList.npcs[i].faceEntity = -1;
            }
        }
        Inv.clear();
        Camera.cameraType = 1;
        Client.processGameStatus(30);
        for (i = 0; i < 100; i++) {
            WidgetList.widgetNeedsRedraw[i] = true;
        }
        ClientProt.sendWindowDetails();
    }

}
