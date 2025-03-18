package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.game.config.meltype.MapElementTypeList;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class LoginManager {
    @OriginalMember(owner = "client!bh", name = "C", descriptor = "Lclient!na;")
    public static final JString COMPLETE_PERCENT = JString.parse("<br>(X100(U(Y");
    @OriginalMember(owner = "runetek4.client!e", name = "Dc", descriptor = "Lclient!na;")
    public static final JString aClass100_363 = JString.parse("_labels");
    @OriginalMember(owner = "client!ef", name = "h", descriptor = "Lclient!na;")
    public static final JString UNZAP = JString.parse("unzap");
    @OriginalMember(owner = "runetek4.client!qi", name = "z", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_10;
    @OriginalMember(owner = "runetek4.client!d", name = "ib", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_1;
    @OriginalMember(owner = "runetek4.client!hd", name = "e", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_6;
    @OriginalMember(owner = "runetek4.client!nb", name = "i", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_8;
    @OriginalMember(owner = "runetek4.client!oi", name = "h", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_9;
    @OriginalMember(owner = "runetek4.client!qf", name = "M", descriptor = "I")
    public static int idleNetCycles = 0;
    @OriginalMember(owner = "runetek4.client!gk", name = "h", descriptor = "I")
    public static int mapFlagZ = 0;
    @OriginalMember(owner = "runetek4.client!ja", name = "n", descriptor = "I")
    public static int mapFlagX = 0;
    @OriginalMember(owner = "runetek4.client!nm", name = "U", descriptor = "I")
    public static int mapFilesMissingCount = 0;
    @OriginalMember(owner = "runetek4.client!wc", name = "g", descriptor = "I")
    public static int anInt5804 = 0;
    @OriginalMember(owner = "runetek4.client!ol", name = "V", descriptor = "I")
    public static int step = 0;
    @OriginalMember(owner = "runetek4.client!vk", name = "b", descriptor = "I")
    public static int reply = -2;
    @OriginalMember(owner = "runetek4.client!pa", name = "P", descriptor = "Lclient!na;")
    public static JString password = JString.EMPTY;
    @OriginalMember(owner = "runetek4.client!af", name = "c", descriptor = "I")
    public static int anInt39 = -1;
    @OriginalMember(owner = "runetek4.client!pa", name = "S", descriptor = "Lclient!na;")
	public static JString username = JString.EMPTY;
    @OriginalMember(owner = "runetek4.client!rl", name = "X", descriptor = "I")
    public static int autoStep = 0;
    @OriginalMember(owner = "runetek4.client!pl", name = "i", descriptor = "I")
    public static int anInt4587 = 0;
    @OriginalMember(owner = "client!bj", name = "Y", descriptor = "I")
    public static int anInt673 = 0;
    @OriginalMember(owner = "runetek4.client!qi", name = "A", descriptor = "I")
    public static int disallowResult = -1;
    @OriginalMember(owner = "runetek4.client!pg", name = "S", descriptor = "I")
    public static int staffModLevel = 0;
    @OriginalMember(owner = "runetek4.client!sd", name = "X", descriptor = "Z")
    public static boolean aBoolean247 = false;
    @OriginalMember(owner = "runetek4.client!qf", name = "X", descriptor = "Lclient!be;")
    public static Component aClass13_13 = null;

    @OriginalMember(owner = "runetek4.client!j", name = "g", descriptor = "(I)V")
    public static void method4637() {
        aClass3_Sub2_Sub1_8 = null;
        aClass3_Sub2_Sub1_1 = null;
        aClass3_Sub2_Sub1_6 = null;
        aClass3_Sub2_Sub1_9 = null;
        aClass3_Sub2_Sub1_10 = null;
    }

    @OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(I)V")
    public static void method1841() {
        if (!ClientScriptRunner.aBoolean108) {
            if (Static162.anInt3953 != 0) {
                ClientScriptRunner.anInt3751 = Static277.anInt5850;
                ClientScriptRunner.anInt1892 = Static280.anInt5895;
            } else if (Mouse.clickButton == 0) {
                ClientScriptRunner.anInt3751 = Mouse.lastMouseX;
                ClientScriptRunner.anInt1892 = Mouse.lastMouseY;
            } else {
                ClientScriptRunner.anInt3751 = aClass6.mouseClickX;
                ClientScriptRunner.anInt1892 = Static60.mouseClickY;
            }
            MiniMenu.menuActionRow = 1;
            MiniMenu.ops[0] = LocalizedText.CANCEL;
            MiniMenu.opBases[0] = JString.EMPTY;
            MiniMenu.actions[0] = 1005;
            MiniMenu.cursors[0] = Static35.anInt1092;
        }
        if (InterfaceList.topLevelInterace != -1) {
            Static96.method1949(InterfaceList.topLevelInterace);
        }
        @Pc(60) int local60;
        for (local60 = 0; local60 < InterfaceList.rectangles; local60++) {
            if (InterfaceList.aBooleanArray100[local60]) {
                InterfaceList.rectangleRedraw[local60] = true;
            }
            Static223.aBooleanArray116[local60] = InterfaceList.aBooleanArray100[local60];
            InterfaceList.aBooleanArray100[local60] = false;
        }
        aClass13_13 = null;
        ClientScriptRunner.anInt2503 = -1;
        InterfaceList.anInt5574 = -1;
        Static169.aClass13_18 = null;
        if (GlRenderer.enabled) {
            ClientScriptRunner.aBoolean299 = true;
        }
        Static182.anInt4311 = client.loop;
        if (InterfaceList.topLevelInterace != -1) {
            InterfaceList.rectangles = 0;
            CacheArchive.method182();
        }
        if (GlRenderer.enabled) {
            GlRaster.method1177();
        } else {
            Rasterizer.resetBounds();
        }
        Static280.method4673();
        if (ClientScriptRunner.aBoolean108) {
            if (InterfaceList.aBoolean298) {
                Static112.method2297();
            } else {
                Static145.method2744();
            }
        } else if (aClass13_13 != null) {
            Static49.method1207(aClass13_13, ClientScriptRunner.anInt3484, ClientScriptRunner.anInt3260);
        } else if (ClientScriptRunner.anInt2503 != -1) {
            Static49.method1207(null, InterfaceList.anInt5574, ClientScriptRunner.anInt2503);
        }
        local60 = ClientScriptRunner.aBoolean108 ? -1 : Static235.method4044();
        if (local60 == -1) {
            local60 = Static270.anInt5794;
        }
        InterfaceList.method1750(local60);
        if (Static125.anInt3096 == 1) {
            Static125.anInt3096 = 2;
        }
        if (Static187.anInt4422 == 1) {
            Static187.anInt4422 = 2;
        }
        if (Cheat.rectDebug == 3) {
            for (@Pc(189) int local189 = 0; local189 < InterfaceList.rectangles; local189++) {
                if (Static223.aBooleanArray116[local189]) {
                    if (GlRenderer.enabled) {
                        GlRaster.fillRectAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711935, 128);
                    } else {
                        SoftwareRaster.fillRectAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711935, 128);
                    }
                } else if (InterfaceList.rectangleRedraw[local189]) {
                    if (GlRenderer.enabled) {
                        GlRaster.fillRectAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711680, 128);
                    } else {
                        SoftwareRaster.fillRectAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711680, 128);
                    }
                }
            }
        }
        AreaSoundManager.redraw(Protocol.sceneDelta, PlayerList.self.xFine, PlayerList.self.zFine, Player.plane);
        Protocol.sceneDelta = 0;
    }

    @OriginalMember(owner = "client!ca", name = "h", descriptor = "(I)V")
    public static void setupLoadingScreenRegion() {
        @Pc(10) int local10 = (Camera.renderX >> 10) + (Camera.originX >> 3);
        @Pc(23) int local23 = (Camera.renderZ >> 10) + (Camera.originZ >> 3);
        Static156.aByteArrayArray11 = new byte[18][];
        Static35.anIntArray82 = new int[18];
        Static191.aByteArrayArray15 = new byte[18][];
        Static36.anIntArray84 = new int[18];
        Static72.anIntArrayArray14 = new int[18][4];
        Static186.aByteArrayArray14 = new byte[18][];
        Static238.anIntArray470 = new int[18];
        Static273.aByteArrayArray13 = new byte[18][];
        Static175.anIntArray371 = new int[18];
        Static99.anIntArray239 = new int[18];
        Static172.anIntArray366 = new int[18];
        Static19.aByteArrayArray4 = new byte[18][];
        @Pc(74) int local74 = 0;
        @Pc(80) int local80;
        for (local80 = (local10 - 6) / 8; local80 <= (local10 + 6) / 8; local80++) {
            for (@Pc(97) int local97 = (local23 - 6) / 8; local97 <= (local23 + 6) / 8; local97++) {
                @Pc(115) int local115 = (local80 << 8) + local97;
                Static238.anIntArray470[local74] = local115;
                Static36.anIntArray84[local74] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { Static103.aClass100_558, JString.parseInt(local80), Static86.aClass100_488, JString.parseInt(local97) }));
                Static172.anIntArray366[local74] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { Static270.aClass100_1090, JString.parseInt(local80), Static86.aClass100_488, JString.parseInt(local97) }));
                Static175.anIntArray371[local74] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { Static179.aClass100_807, JString.parseInt(local80), Static86.aClass100_488, JString.parseInt(local97) }));
                Static99.anIntArray239[local74] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { Static165.aClass100_772, JString.parseInt(local80), Static86.aClass100_488, JString.parseInt(local97) }));
                Static35.anIntArray82[local74] = client.js5Archive5.getGroupId(JString.concatenate(new JString[] { Static278.aClass100_1103, JString.parseInt(local80), Static86.aClass100_488, JString.parseInt(local97) }));
                if (Static175.anIntArray371[local74] == -1) {
                    Static36.anIntArray84[local74] = -1;
                    Static172.anIntArray366[local74] = -1;
                    Static99.anIntArray239[local74] = -1;
                    Static35.anIntArray82[local74] = -1;
                }
                local74++;
            }
        }
        for (local80 = local74; local80 < Static175.anIntArray371.length; local80++) {
            Static175.anIntArray371[local80] = -1;
            Static36.anIntArray84[local80] = -1;
            Static172.anIntArray366[local80] = -1;
            Static99.anIntArray239[local80] = -1;
            Static35.anIntArray82[local80] = -1;
        }
        Static127.method2463(0, local23, local10, 8, true, 8);
    }

    @OriginalMember(owner = "client!se", name = "a", descriptor = "(Lclient!na;Lclient!na;IB)V")
    public static void login(@OriginalArg(0) JString username, @OriginalArg(1) JString password, @OriginalArg(2) int arg2) {
        LoginManager.password = password;
        anInt39 = arg2;
        LoginManager.username = username;
        if (LoginManager.username.strEquals(JString.EMPTY) || LoginManager.password.strEquals(JString.EMPTY)) {
            reply = 3;
        } else if (Static125.worldId == -1) {
            anInt673 = 0;
            anInt4587 = 0;
            reply = -3;
            autoStep = 1;
            @Pc(43) Packet login_packet = new Packet(128);
            login_packet.p1(10);
            login_packet.p2((int) (Math.random() * 99999.0D));
            login_packet.p2(530);
            login_packet.p8(LoginManager.username.encode37());
            login_packet.p4((int) (Math.random() * 9.9999999E7D));
            login_packet.pjstr(LoginManager.password);
            login_packet.p4((int) (Math.random() * 9.9999999E7D));
            login_packet.rsaenc(Static86.RSA_EXPONENT, Static86.RSA_MODULUS);
            Protocol.outboundBuffer.offset = 0;
            Protocol.outboundBuffer.p1(210);
            Protocol.outboundBuffer.p1(login_packet.offset);
            Protocol.outboundBuffer.pdata(login_packet.data, login_packet.offset);
        } else {
            method1208();
        }
    }

    @OriginalMember(owner = "client!p", name = "a", descriptor = "(I)V")
    public static void method3395() {
        if (step == 5) {
            step = 6;
        }
    }

    @OriginalMember(owner = "client!dm", name = "d", descriptor = "(I)V")
    public static void method1208() {
        aBoolean247 = false;
        PreciseSleep.anInt5202 = 0;
        reply = -3;
        Static92.anInt2430 = 0;
        step = 1;
        Static276.anInt5816 = 0;
        disallowResult = -1;
    }

    @OriginalMember(owner = "runetek4.client!gd", name = "c", descriptor = "(I)V")
    public static void rebuildMap() {
        ClientProt.ping(false);
        mapFilesMissingCount = 0;
        @Pc(12) boolean local12 = true;
        @Pc(14) int local14;
        for (local14 = 0; local14 < Static273.aByteArrayArray13.length; local14++) {
            if (Static36.anIntArray84[local14] != -1 && Static273.aByteArrayArray13[local14] == null) {
                Static273.aByteArrayArray13[local14] = client.js5Archive5.getfile(Static36.anIntArray84[local14], 0);
                if (Static273.aByteArrayArray13[local14] == null) {
                    mapFilesMissingCount++;
                    local12 = false;
                }
            }
            if (Static172.anIntArray366[local14] != -1 && Static156.aByteArrayArray11[local14] == null) {
                Static156.aByteArrayArray11[local14] = client.js5Archive5.getfile(Static172.anIntArray366[local14], 0, Static72.anIntArrayArray14[local14]);
                if (Static156.aByteArrayArray11[local14] == null) {
                    local12 = false;
                    mapFilesMissingCount++;
                }
            }
            if (GlRenderer.enabled) {
                if (Static99.anIntArray239[local14] != -1 && Static186.aByteArrayArray14[local14] == null) {
                    Static186.aByteArrayArray14[local14] = client.js5Archive5.getfile(Static99.anIntArray239[local14], 0);
                    if (Static186.aByteArrayArray14[local14] == null) {
                        local12 = false;
                        mapFilesMissingCount++;
                    }
                }
                if (Static35.anIntArray82[local14] != -1 && Static19.aByteArrayArray4[local14] == null) {
                    Static19.aByteArrayArray4[local14] = client.js5Archive5.getfile(Static35.anIntArray82[local14], 0);
                    if (Static19.aByteArrayArray4[local14] == null) {
                        mapFilesMissingCount++;
                        local12 = false;
                    }
                }
            }
            if (Static175.anIntArray371 != null && Static191.aByteArrayArray15[local14] == null && Static175.anIntArray371[local14] != -1) {
                Static191.aByteArrayArray15[local14] = client.js5Archive5.getfile(Static175.anIntArray371[local14], 0, Static72.anIntArrayArray14[local14]);
                if (Static191.aByteArrayArray15[local14] == null) {
                    mapFilesMissingCount++;
                    local12 = false;
                }
            }
        }
        if (Static235.aMapElementTypeList_2 == null) {
            if (Static158.aClass3_Sub2_Sub4_3 == null || !client.js5Archive23.method4497(JString.concatenate(new JString[] { Static158.aClass3_Sub2_Sub4_3.aClass100_138, aClass100_363 }))) {
                Static235.aMapElementTypeList_2 = new MapElementTypeList(0);
            } else if (client.js5Archive23.method4489(JString.concatenate(new JString[] { Static158.aClass3_Sub2_Sub4_3.aClass100_138, aClass100_363 }))) {
                Static235.aMapElementTypeList_2 = MapElementTypeList.create(JString.concatenate(new JString[] { Static158.aClass3_Sub2_Sub4_3.aClass100_138, aClass100_363 }), client.js5Archive23);
            } else {
                local12 = false;
                mapFilesMissingCount++;
            }
        }
        if (!local12) {
            ClientScriptRunner.anInt5223 = 1;
            return;
        }
        anInt5804 = 0;
        local12 = true;
        @Pc(320) int local320;
        @Pc(309) int local309;
        for (local14 = 0; local14 < Static273.aByteArrayArray13.length; local14++) {
            @Pc(294) byte[] local294 = Static156.aByteArrayArray11[local14];
            if (local294 != null) {
                local309 = (Static238.anIntArray470[local14] & 0xFF) * 64 - Camera.originZ;
                local320 = (Static238.anIntArray470[local14] >> 8) * 64 - Camera.originX;
                if (Static230.aBoolean250) {
                    local309 = 10;
                    local320 = 10;
                }
                local12 &= Static49.method1201(local320, local309, local294);
            }
            if (GlRenderer.enabled) {
                local294 = Static19.aByteArrayArray4[local14];
                if (local294 != null) {
                    local320 = (Static238.anIntArray470[local14] >> 8) * 64 - Camera.originX;
                    local309 = (Static238.anIntArray470[local14] & 0xFF) * 64 - Camera.originZ;
                    if (Static230.aBoolean250) {
                        local309 = 10;
                        local320 = 10;
                    }
                    local12 &= Static49.method1201(local320, local309, local294);
                }
            }
        }
        if (!local12) {
            ClientScriptRunner.anInt5223 = 2;
            return;
        }
        if (ClientScriptRunner.anInt5223 != 0) {
            Fonts.drawTextOnScreen(true, JString.concatenate(new JString[] { LocalizedText.LOADING, COMPLETE_PERCENT}));
        }
        client.audioLoop();
        client.unload();
        @Pc(420) boolean local420 = false;
        @Pc(427) int local427;
        if (GlRenderer.enabled && Static220.aBoolean244) {
            for (local427 = 0; local427 < Static273.aByteArrayArray13.length; local427++) {
                if (Static19.aByteArrayArray4[local427] != null || Static186.aByteArrayArray14[local427] != null) {
                    local420 = true;
                    break;
                }
            }
        }
        Static28.method792(GlRenderer.enabled ? 28 : 25, local420);
        for (local427 = 0; local427 < 4; local427++) {
            PathFinder.collisionMaps[local427].reset();
        }
        for (local427 = 0; local427 < 4; local427++) {
            for (local320 = 0; local320 < 104; local320++) {
                for (local309 = 0; local309 < 104; local309++) {
                    SceneGraph.renderFlags[local427][local320][local309] = 0;
                }
            }
        }
        AreaSoundManager.clear(false);
        if (GlRenderer.enabled) {
            Static242.aClass36_Sub1_4.method1392();
            for (local427 = 0; local427 < 13; local427++) {
                for (local320 = 0; local320 < 13; local320++) {
                    Static242.aOpenGLRendererArrayArray1[local427][local320].aBoolean313 = true;
                }
            }
        }
        if (GlRenderer.enabled) {
            Static120.method2404();
        }
        if (GlRenderer.enabled) {
            FogManager.setDefaultChunksAtmosphere();
        }
        client.audioLoop();
        System.gc();
        ClientProt.ping(true);
        Static196.method3535(false);
        if (!Static230.aBoolean250) {
            Static87.method1805(false);
            ClientProt.ping(true);
            if (GlRenderer.enabled) {
                local427 = PlayerList.self.movementQueueX[0] >> 3;
                local320 = PlayerList.self.movementQueueZ[0] >> 3;
                FogManager.setLightPosition(local320, local427);
            }
            Static26.method743(false);
            if (Static191.aByteArrayArray15 != null) {
                Static158.method3013();
            }
        }
        if (Static230.aBoolean250) {
            Static89.method1835(false);
            ClientProt.ping(true);
            if (GlRenderer.enabled) {
                local427 = PlayerList.self.movementQueueX[0] >> 3;
                local320 = PlayerList.self.movementQueueZ[0] >> 3;
                FogManager.setLightPosition(local320, local427);
            }
            ClientScriptRunner.method4002(false);
        }
        client.unload();
        ClientProt.ping(true);
        Static45.method1169(PathFinder.collisionMaps, false);
        if (GlRenderer.enabled) {
            Static120.method2395();
        }
        ClientProt.ping(true);
        local427 = Static146.firstvisibleLevel;
        if (local427 > Player.plane) {
            local427 = Player.plane;
        }
        if (local427 < Player.plane - 1) {
        }
        if (Static138.allLevelsvisible()) {
            Static146.method2750(0);
        } else {
            Static146.method2750(Static146.firstvisibleLevel);
        }
        Static105.method2255();
        if (GlRenderer.enabled && local420) {
            Static278.method4648(true);
            Static196.method3535(true);
            if (!Static230.aBoolean250) {
                Static87.method1805(true);
                ClientProt.ping(true);
                Static26.method743(true);
            }
            if (Static230.aBoolean250) {
                Static89.method1835(true);
                ClientProt.ping(true);
                ClientScriptRunner.method4002(true);
            }
            client.unload();
            ClientProt.ping(true);
            Static45.method1169(PathFinder.collisionMaps, true);
            ClientProt.ping(true);
            Static105.method2255();
            Static278.method4648(false);
        }
        if (GlRenderer.enabled) {
            for (local320 = 0; local320 < 13; local320++) {
                for (local309 = 0; local309 < 13; local309++) {
                    Static242.aOpenGLRendererArrayArray1[local320][local309].method4676(SceneGraph.tileHeights[0], local320 * 8, local309 * 8);
                }
            }
        }
        for (local320 = 0; local320 < 104; local320++) {
            for (local309 = 0; local309 < 104; local309++) {
                Static220.method3797(local309, local320);
            }
        }
        Static269.method2218();
        client.audioLoop();
        ChangeLocRequest.method3796();
        client.unload();
        PreciseSleep.aBoolean252 = false;
        if (GameShell.frame != null && Protocol.gameServerSocket != null && client.gameState == 25) {
            Protocol.outboundBuffer.pIsaac1(20);
            Protocol.outboundBuffer.p4(1057001181);
        }
        if (!Static230.aBoolean250) {
            @Pc(815) int local815 = (Static52.anInt1695 + 6) / 8;
            @Pc(821) int local821 = (Static52.anInt1695 - 6) / 8;
            local320 = (Static80.anInt4701 - 6) / 8;
            local309 = (Static80.anInt4701 + 6) / 8;
            for (@Pc(837) int local837 = local320 - 1; local837 <= local309 + 1; local837++) {
                for (@Pc(850) int local850 = local821 - 1; local850 <= local815 + 1; local850++) {
                    if (local837 < local320 || local837 > local309 || local850 < local821 || local850 > local815) {
                        client.js5Archive5.method4486(JString.concatenate(new JString[] { Static103.aClass100_558, JString.parseInt(local837), Static86.aClass100_488, JString.parseInt(local850) }));
                        client.js5Archive5.method4486(JString.concatenate(new JString[] { Static270.aClass100_1090, JString.parseInt(local837), Static86.aClass100_488, JString.parseInt(local850) }));
                    }
                }
            }
        }
        if (client.gameState == 28) {
            client.processGameStatus(10);
        } else {
            client.processGameStatus(30);
            if (Protocol.gameServerSocket != null) {
                Protocol.outboundBuffer.pIsaac1(110);
            }
        }
        Static141.method2720();
        client.audioLoop();
        GameShell.resetTimer();
    }

    @OriginalMember(owner = "client!ch", name = "b", descriptor = "(B)V")
    public static void loopAuto() {
        if (autoStep == 0) {
            return;
        }
        try {
            if (++anInt673 > 1500) {
                if (Protocol.gameServerSocket != null) {
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                }
                if (anInt4587 >= 1) {
                    reply = -5;
                    autoStep = 0;
                    return;
                }
                anInt673 = 0;
                anInt4587++;
                autoStep = 1;
                if (client.worldListPort == client.worldListDefaultPort) {
                    client.worldListPort = client.worldListAlternatePort;
                } else {
                    client.worldListPort = client.worldListDefaultPort;
                }
            }
            if (autoStep == 1) {
                Static72.aClass212_3 = GameShell.signLink.openSocket(client.worldListHostname, client.worldListPort);
                autoStep = 2;
            }
            @Pc(126) int local126;
            if (autoStep == 2) {
                if (Static72.aClass212_3.status == 2) {
                    throw new IOException();
                }
                if (Static72.aClass212_3.status != 1) {
                    return;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
                Static72.aClass212_3 = null;
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                local126 = Protocol.gameServerSocket.read();
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
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
                Static176.method3303(local126);
                if (Static125.worldId == -1) {
                    autoStep = 0;
                    reply = 6;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                autoStep = 0;
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
                method1208();
            }
        } catch (@Pc(210) IOException local210) {
            if (Protocol.gameServerSocket != null) {
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
            }
            if (anInt4587 < 1) {
                if (client.worldListPort == client.worldListDefaultPort) {
                    client.worldListPort = client.worldListAlternatePort;
                } else {
                    client.worldListPort = client.worldListDefaultPort;
                }
                autoStep = 1;
                anInt673 = 0;
                anInt4587++;
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
            if (++Static92.anInt2430 > 2000) {
                if (Protocol.gameServerSocket != null) {
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                }
                if (Static276.anInt5816 >= 1) {
                    reply = -5;
                    step = 0;
                    return;
                }
                Static92.anInt2430 = 0;
                if (client.port == client.defaultPort) {
                    client.port = client.alternatePort;
                } else {
                    client.port = client.defaultPort;
                }
                step = 1;
                Static276.anInt5816++;
            }
            if (step == 1) {
                Static72.aClass212_3 = GameShell.signLink.openSocket(client.hostname, client.port);
                step = 2;
            }
            if (step == 2) {
                if (Static72.aClass212_3.status == 2) {
                    throw new IOException();
                }
                if (Static72.aClass212_3.status != 1) {
                    return;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
                Static72.aClass212_3 = null;
                @Pc(106) long local106 = Static101.aLong98 = username.encode37();
                Protocol.outboundBuffer.offset = 0;
                Protocol.outboundBuffer.p1(14);
                @Pc(120) int local120 = (int) (local106 >> 16 & 0x1FL);
                Protocol.outboundBuffer.p1(local120);
                Protocol.gameServerSocket.write(2, Protocol.outboundBuffer.data);
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                @Pc(150) int local150 = Protocol.gameServerSocket.read();
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
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
                Static193.aLong147 = Protocol.inboundBuffer.g8();
                @Pc(210) int[] seed = new int[4];
                Protocol.outboundBuffer.offset = 0;
                seed[2] = (int) (Static193.aLong147 >> 32);
                seed[3] = (int) Static193.aLong147;
                seed[1] = (int) (Math.random() * 9.9999999E7D);
                seed[0] = (int) (Math.random() * 9.9999999E7D);
                Protocol.outboundBuffer.p1(10);
                Protocol.outboundBuffer.p4(seed[0]);
                Protocol.outboundBuffer.p4(seed[1]);
                Protocol.outboundBuffer.p4(seed[2]);
                Protocol.outboundBuffer.p4(seed[3]);
                Protocol.outboundBuffer.p8(username.encode37());
                Protocol.outboundBuffer.pjstr(password);
                Protocol.outboundBuffer.rsaenc(Static86.RSA_EXPONENT, Static86.RSA_MODULUS);
                Static17.aClass3_Sub15_Sub1_2.offset = 0;
                if (client.gameState == 40) {
                    Static17.aClass3_Sub15_Sub1_2.p1(18);
                } else {
                    Static17.aClass3_Sub15_Sub1_2.p1(16);
                }

                Static17.aClass3_Sub15_Sub1_2.p2(Protocol.outboundBuffer.offset + Static229.method3937(client.settings) + 159);
                Static17.aClass3_Sub15_Sub1_2.p4(530);
                Static17.aClass3_Sub15_Sub1_2.p1(anInt39);
                Static17.aClass3_Sub15_Sub1_2.p1(client.advertSuppressed ? 1 : 0);
                Static17.aClass3_Sub15_Sub1_2.p1(1);
                Static17.aClass3_Sub15_Sub1_2.p1(DisplayMode.getWindowMode());
                Static17.aClass3_Sub15_Sub1_2.p2(GameShell.canvasWidth);
                Static17.aClass3_Sub15_Sub1_2.p2(GameShell.canvasHeigth);
                Static17.aClass3_Sub15_Sub1_2.p1(Preferences.antiAliasingMode);
                client.writeUid(Static17.aClass3_Sub15_Sub1_2);
                Static17.aClass3_Sub15_Sub1_2.pjstr(client.settings);
                Static17.aClass3_Sub15_Sub1_2.p4(client.affiliate);
                Static17.aClass3_Sub15_Sub1_2.p4(Static145.method2746());
                Preferences.sentToServer = true;
                Static17.aClass3_Sub15_Sub1_2.p2(Protocol.verifyId);
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive0.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive1.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive2.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive3.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive4.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive5.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive6.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive7.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive8.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive9.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive10.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive11.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive12.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive13.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive14.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive15.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive16.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive17.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive18.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive19.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive20.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive21.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive22.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive23.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive24.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive25.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive26.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.p4(client.js5Archive27.getChecksum());
                Static17.aClass3_Sub15_Sub1_2.pdata(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
                Protocol.gameServerSocket.write(Static17.aClass3_Sub15_Sub1_2.offset, Static17.aClass3_Sub15_Sub1_2.data);
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
                } else if (local623 == 23 && Static276.anInt5816 < 1) {
                    step = 1;
                    Static276.anInt5816++;
                    Static92.anInt2430 = 0;
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
                    PreciseSleep.anInt5202 = (Protocol.gameServerSocket.read() + 3) * 60;
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
                Static249.anInt5431 = Protocol.inboundBuffer.g1();
                Static124.aBoolean157 = Protocol.inboundBuffer.g1() == 1;
                Static207.parentalChatConsent = Protocol.inboundBuffer.g1() == 1;
                Static25.aBoolean57 = Protocol.inboundBuffer.g1() == 1;
                Static86.aBoolean129 = Protocol.inboundBuffer.g1() == 1;
                Static245.enabled = Protocol.inboundBuffer.g1() == 1;
                PlayerList.selfId = Protocol.inboundBuffer.g2();
                Class6.members = Protocol.inboundBuffer.g1() == 1;
                Static2.membersWorld = Protocol.inboundBuffer.g1() == 1;
                Static189.method3438(Static2.membersWorld);
                CacheArchive.method186(Static2.membersWorld);
                if (!client.advertSuppressed) {
                    if (Static124.aBoolean157 && !Static25.aBoolean57 || Class6.members) {
                        try {
                            Static167.aClass100_781.browserControlCall(GameShell.signLink.applet);
                        } catch (@Pc(910) Throwable local910) {
                        }
                    } else {
                        try {
                            UNZAP.browserControlCall(GameShell.signLink.applet);
                        } catch (@Pc(920) Throwable local920) {
                        }
                    }
                }
                Protocol.opcode = Protocol.inboundBuffer.gIssac1();
                Static223.packetSize = Protocol.inboundBuffer.g2();
                step = 9;
            }
            if (step == 9) {
                if (Protocol.gameServerSocket.available() < Static223.packetSize) {
                    return;
                }
                Protocol.inboundBuffer.offset = 0;
                Protocol.gameServerSocket.read(0, Static223.packetSize, Protocol.inboundBuffer.data);
                reply = 2;
                step = 0;
                client.method4221();
                Static80.anInt4701 = -1;
                Static75.method1629(false);
                Protocol.opcode = -1;
                return;
            }
        } catch (@Pc(977) IOException local977) {
            if (Protocol.gameServerSocket != null) {
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
            }
            if (Static276.anInt5816 >= 1) {
                step = 0;
                reply = -4;
            } else {
                step = 1;
                Static92.anInt2430 = 0;
                Static276.anInt5816++;
                if (client.defaultPort == client.port) {
                    client.port = client.alternatePort;
                } else {
                    client.port = client.defaultPort;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(Z)V")
    public static void reconnect() {
        Protocol.outboundBuffer.offset = 0;
        Protocol.opcode3 = -1;
        ClientScriptRunner.aBoolean108 = false;
        Static223.packetSize = 0;
        mapFlagX = 0;
        MiniMenu.menuActionRow = 0;
        Protocol.opcode2 = -1;
        MiniMap.state = 0;
        Static60.systemUpdateTimer = 0;
        Protocol.opcode4 = -1;
        Protocol.inboundBuffer.offset = 0;
        idleNetCycles = 0;
        Protocol.opcode = -1;
        @Pc(35) int local35;
        for (local35 = 0; local35 < PlayerList.players.length; local35++) {
            if (PlayerList.players[local35] != null) {
                PlayerList.players[local35].targetId = -1;
            }
        }
        for (local35 = 0; local35 < NpcList.npcs.length; local35++) {
            if (NpcList.npcs[local35] != null) {
                NpcList.npcs[local35].targetId = -1;
            }
        }
        Inv.clear();
        Camera.cameraType = 1;
        client.processGameStatus(30);
        for (local35 = 0; local35 < 100; local35++) {
            InterfaceList.aBooleanArray100[local35] = true;
        }
        ClientProt.sendWindowDetails();
    }
}
