package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LoginManager {
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
                Static155.anInt3751 = Static277.anInt5850;
                Static60.anInt1892 = Static280.anInt5895;
            } else if (Mouse.clickButton == 0) {
                Static155.anInt3751 = Static215.anInt4873;
                Static60.anInt1892 = Static223.anInt5032;
            } else {
                Static155.anInt3751 = aClass6.mouseClickX;
                Static60.anInt1892 = Static60.mouseClickY;
            }
            MiniMenu.menuActionRow = 1;
            Static254.aClass100Array168[0] = LocalizedText.CANCEL;
            ClientScriptRunner.aClass100Array160[0] = JString.EMPTY;
            Static39.aShortArray6[0] = 1005;
            Static190.anIntArray382[0] = Static35.anInt1092;
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
        Static201.aClass13_13 = null;
        Static97.anInt2503 = -1;
        Static214.anInt5574 = -1;
        Static169.aClass13_18 = null;
        if (GlRenderer.enabled) {
            Static263.aBoolean299 = true;
        }
        Static182.anInt4311 = client.loop;
        if (InterfaceList.topLevelInterace != -1) {
            InterfaceList.rectangles = 0;
            CacheArchive.method182();
        }
        if (GlRenderer.enabled) {
            Static46.method1177();
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
        } else if (Static201.aClass13_13 != null) {
            Static49.method1207(Static201.aClass13_13, Static143.anInt3484, Static131.anInt3260);
        } else if (Static97.anInt2503 != -1) {
            Static49.method1207(null, Static214.anInt5574, Static97.anInt2503);
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
                        Static46.drawFilledRectangleAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711935, 128);
                    } else {
                        Rasterizer.drawFilledRectangleAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711935, 128);
                    }
                } else if (InterfaceList.rectangleRedraw[local189]) {
                    if (GlRenderer.enabled) {
                        Static46.drawFilledRectangleAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711680, 128);
                    } else {
                        Rasterizer.drawFilledRectangleAlpha(InterfaceList.rectangleX[local189], InterfaceList.rectangleY[local189], InterfaceList.rectangleWidth[local189], InterfaceList.rectangleHeight[local189], 16711680, 128);
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
        if (LoginManager.username.method3108(JString.EMPTY) || LoginManager.password.method3108(JString.EMPTY)) {
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
        FluTypeList.aBoolean247 = false;
        PreciseSleep.anInt5202 = 0;
        reply = -3;
        Static92.anInt2430 = 0;
        step = 1;
        Static276.anInt5816 = 0;
        disallowResult = -1;
    }
}
