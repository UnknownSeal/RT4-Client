package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.meltype.MapElementTypeList;
import com.jagex.runetek4.util.SignLink;
import com.jagex.runetek4.util.ThreadUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;

public class Preferences {
    @OriginalMember(owner = "client!bl", name = "W", descriptor = "I")
    public static int fullScreenHeight = 0;
    @OriginalMember(owner = "runetek4.client!j", name = "v", descriptor = "I")
    public static int fullScreenWidth = 0;
    @OriginalMember(owner = "runetek4.client!rg", name = "F", descriptor = "I")
    public static int favoriteWorlds = 0;
    @OriginalMember(owner = "client!cg", name = "f", descriptor = "I")
    public static int ambientSoundsVolume = 127;
    @OriginalMember(owner = "runetek4.client!cj", name = "h", descriptor = "Z")
    public static boolean aBoolean63;
    @OriginalMember(owner = "client!bh", name = "z", descriptor = "Z")
    public static boolean sentToServer = true;
    @OriginalMember(owner = "runetek4.client!pa", name = "N", descriptor = "I")
    public static int antiAliasingMode = 0;
    @OriginalMember(owner = "runetek4.client!hk", name = "eb", descriptor = "Z")
    public static boolean stereo = true;
    @OriginalMember(owner = "runetek4.client!na", name = "h", descriptor = "Z")
    public static boolean safeMode = false;
    @OriginalMember(owner = "runetek4.client!hn", name = "X", descriptor = "I")
    public static int windowMode = 0;
    @OriginalMember(owner = "runetek4.client!lb", name = "A", descriptor = "I")
    public static int buildArea = 0;
    @OriginalMember(owner = "runetek4.client!ga", name = "e", descriptor = "I")
    private static int particles = 2;

    @OriginalMember(owner = "runetek4.client!pa", name = "d", descriptor = "(I)V")
    public static void method3413() {
        if (Static269.aClass3_Sub2_Sub4_2 == null) {
            return;
        }
        if (WorldMap.loadPercentage < 10) {
            if (!Static119.aClass153_44.method4489(Static269.aClass3_Sub2_Sub4_2.aClass100_138)) {
                WorldMap.loadPercentage = client.js5Archive23.method4478(Static269.aClass3_Sub2_Sub4_2.aClass100_138) / 10;
                return;
            }
            client.method84();
            WorldMap.loadPercentage = 10;
        }
        if (WorldMap.loadPercentage == 10) {
            Static158.anInt3846 = Static269.aClass3_Sub2_Sub4_2.anInt763 >> 6 << 6;
            Static2.anInt13 = Static269.aClass3_Sub2_Sub4_2.anInt771 >> 6 << 6;
            IdkTypeList.anInt4296 = (Static269.aClass3_Sub2_Sub4_2.anInt758 >> 6 << 6) + 64 - Static2.anInt13;
            Static48.anInt1449 = (Static269.aClass3_Sub2_Sub4_2.anInt770 >> 6 << 6) + 64 - Static158.anInt3846;
            if (Static269.aClass3_Sub2_Sub4_2.anInt772 == 37) {
                Static83.aFloat3 = 3.0F;
                Static138.aFloat14 = 3.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.anInt772 == 50) {
                Static83.aFloat3 = 4.0F;
                Static138.aFloat14 = 4.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.anInt772 == 75) {
                Static83.aFloat3 = 6.0F;
                Static138.aFloat14 = 6.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.anInt772 == 100) {
                Static83.aFloat3 = 8.0F;
                Static138.aFloat14 = 8.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.anInt772 == 200) {
                Static83.aFloat3 = 16.0F;
                Static138.aFloat14 = 16.0F;
            } else {
                Static83.aFloat3 = 8.0F;
                Static138.aFloat14 = 8.0F;
            }
            @Pc(144) int local144 = (PlayerList.self.xFine >> 7) + Camera.originX - Static158.anInt3846;
            @Pc(153) int local153 = local144 + (int) (Math.random() * 10.0D) - 5;
            @Pc(168) int local168 = Static2.anInt13 + IdkTypeList.anInt4296 - Camera.originZ - (PlayerList.self.zFine >> 7) - 1;
            @Pc(177) int local177 = local168 + (int) (Math.random() * 10.0D) - 5;
            if (local153 >= 0 && Static48.anInt1449 > local153 && local177 >= 0 && local177 < IdkTypeList.anInt4296) {
                Static13.anInt435 = local153;
                Static28.anInt919 = local177;
            } else {
                Static28.anInt919 = Static2.anInt13 + IdkTypeList.anInt4296 - Static269.aClass3_Sub2_Sub4_2.anInt764 * 64 - 1;
                Static13.anInt435 = Static269.aClass3_Sub2_Sub4_2.anInt769 * 64 - Static158.anInt3846;
            }
            Static38.method965();
            Static145.anIntArray330 = new int[Static98.anInt2510 + 1];
            @Pc(235) int local235 = IdkTypeList.anInt4296 >> 6;
            @Pc(239) int local239 = Static48.anInt1449 >> 6;
            Static90.aByteArrayArrayArray8 = new byte[local239][local235][];
            @Pc(249) int local249 = Static86.anInt2293 >> 2 << 10;
            Static70.aByteArrayArrayArray7 = new byte[local239][local235][];
            Static83.anIntArrayArrayArray3 = new int[local239][local235][];
            Static34.aByteArrayArrayArray3 = new byte[local239][local235][];
            Static248.anIntArrayArrayArray17 = new int[local239][local235][];
            Static229.aByteArrayArrayArray12 = new byte[local239][local235][];
            @Pc(273) int local273 = Static183.anInt4272 >> 1;
            Static125.aByteArrayArrayArray10 = new byte[local239][local235][];
            Static58.anIntArrayArrayArray5 = new int[local239][local235][];
            Static70.method1549(local273, local249);
            WorldMap.loadPercentage = 20;
        } else if (WorldMap.loadPercentage == 20) {
            Static33.method868(new Packet(Static119.aClass153_44.fetchFile(Static166.aClass100_779, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            WorldMap.loadPercentage = 30;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 30) {
            Static47.method3998(new Packet(Static119.aClass153_44.fetchFile(Static4.aClass100_7, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            WorldMap.loadPercentage = 40;
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 40) {
            PreciseSleep.method3980(new Packet(Static119.aClass153_44.fetchFile(Static73.OVERLAY2, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            WorldMap.loadPercentage = 50;
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 50) {
            Static166.method3166(new Packet(Static119.aClass153_44.fetchFile(Static42.aClass100_331, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            WorldMap.loadPercentage = 60;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 60) {
            if (Static119.aClass153_44.method4497(JString.concatenate(new JString[]{Static269.aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}))) {
                if (!Static119.aClass153_44.method4489(JString.concatenate(new JString[]{Static269.aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}))) {
                    return;
                }
                Static203.aMapElementTypeList_1 = MapElementTypeList.create(JString.concatenate(new JString[]{Static269.aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}), Static119.aClass153_44);
            } else {
                Static203.aMapElementTypeList_1 = new MapElementTypeList(0);
            }
            WorldMap.loadPercentage = 70;
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 70) {
            Static273.aClass41_7 = new WorldMapFont(11, true, GameShell.canvas);
            WorldMap.loadPercentage = 73;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 73) {
            Static152.aClass41_3 = new WorldMapFont(12, true, GameShell.canvas);
            WorldMap.loadPercentage = 76;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 76) {
            Static169.aClass41_5 = new WorldMapFont(14, true, GameShell.canvas);
            WorldMap.loadPercentage = 79;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 79) {
            Static130.aClass41_1 = new WorldMapFont(17, true, GameShell.canvas);
            WorldMap.loadPercentage = 82;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 82) {
            Static203.aClass41_8 = new WorldMapFont(19, true, GameShell.canvas);
            WorldMap.loadPercentage = 85;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 85) {
            Static130.aClass41_2 = new WorldMapFont(22, true, GameShell.canvas);
            WorldMap.loadPercentage = 88;
            aClass6.method842(true);
            Static234.method4020();
        } else if (WorldMap.loadPercentage == 88) {
            Static270.aClass41_9 = new WorldMapFont(26, true, GameShell.canvas);
            WorldMap.loadPercentage = 91;
            aClass6.method842(true);
            Static234.method4020();
        } else {
            Static160.aClass41_4 = new WorldMapFont(30, true, GameShell.canvas);
            WorldMap.loadPercentage = 100;
            aClass6.method842(true);
            Static234.method4020();
            System.gc();
        }
    }

    @OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "(Lsignlink!ll;B)V")
    public static void write(@OriginalArg(0) SignLink arg0) {
        @Pc(11) FileOnDisk local11 = null;
        try {
            @Pc(16) PrivilegedRequest local16 = arg0.openPreferences("runescape");
            while (local16.status == 0) {
                ThreadUtils.sleep(1L);
            }
            if (local16.status == 1) {
                local11 = (FileOnDisk) local16.result;
                @Pc(39) Packet local39 = Static48.method1196();
                local11.write(local39.data, local39.offset, 0);
            }
        } catch (@Pc(49) Exception local49) {
        }
        try {
            if (local11 != null) {
                local11.close();
            }
        } catch (@Pc(56) Exception local56) {
        }
    }

    @OriginalMember(owner = "runetek4.client!ga", name = "c", descriptor = "()I")
    public static int getParticleSetting() {
        return particles;
    }

    @OriginalMember(owner = "runetek4.client!ga", name = "b", descriptor = "(I)V")
    public static void setParticles(@OriginalArg(0) int arg0) {
        particles = arg0;
    }

    @OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(Lsignlink!ll;I)V")
    public static void read(@OriginalArg(0) SignLink arg0) {
        Static113.brightness = 3;
        Static53.method1293(true);
        Static80.aBoolean231 = true;
        stereo = true;
        Static220.aBoolean244 = true;
        windowMode = 0;
        fullScreenHeight = 0;
        Static250.aBoolean283 = true;
        Static11.aBoolean15 = true;
        Static15.lowMemory = true;
        ambientSoundsVolume = 127;
        Static71.aBoolean107 = true;
        Static209.aBoolean240 = true;
        fullScreenWidth = 0;
        Static139.anInt3451 = 2;
        Static159.aBoolean189 = true;
        Static178.highDetailLighting = true;
        Static12.anInt391 = 255;
        Static53.aBoolean99 = true;
        antiAliasingMode = 0;
        @Pc(48) FileOnDisk local48 = null;
        Static125.anInt3104 = 127;
        if (Static238.anInt5316 >= 96) {
            setParticles(2);
        } else {
            setParticles(0);
        }
        Static164.anInt3988 = 0;
        buildArea = 0;
        aBoolean63 = false;
        Static64.aBoolean111 = true;
        safeMode = false;
        Static127.aBoolean159 = false;
        favoriteWorlds = 0;
        try {
            @Pc(78) PrivilegedRequest local78 = arg0.openPreferences("runescape");
            while (local78.status == 0) {
                ThreadUtils.sleep(1L);
            }
            if (local78.status == 1) {
                local48 = (FileOnDisk) local78.result;
                @Pc(106) byte[] local106 = new byte[(int) local48.length()];
                @Pc(128) int local128;
                for (@Pc(108) int local108 = 0; local108 < local106.length; local108 += local128) {
                    local128 = local48.read(local108, local106.length - local108, local106);
                    if (local128 == -1) {
                        throw new IOException("EOF");
                    }
                }
                Static136.method2654(new Packet(local106));
            }
        } catch (@Pc(151) Exception local151) {
        }
        try {
            if (local48 != null) {
                local48.close();
            }
        } catch (@Pc(158) Exception local158) {
        }
    }
}
