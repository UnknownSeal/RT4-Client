package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.meltype.MapElementTypeList;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Preferences {
    @OriginalMember(owner = "client!bl", name = "W", descriptor = "I")
    public static int fullScreenHeight = 0;
    @OriginalMember(owner = "runetek4.client!j", name = "v", descriptor = "I")
    public static int fullScreenWidth = 0;
    @OriginalMember(owner = "runetek4.client!rg", name = "F", descriptor = "I")
    public static int favoriteWorlds = 0;
    @OriginalMember(owner = "client!cg", name = "f", descriptor = "I")
    public static int ambientSoundsVolume = 127;

    @OriginalMember(owner = "runetek4.client!pa", name = "d", descriptor = "(I)V")
    public static void method3413() {
        if (Static269.aClass3_Sub2_Sub4_2 == null) {
            return;
        }
        if (Static41.anInt1309 < 10) {
            if (!Static119.aClass153_44.method4489(Static269.aClass3_Sub2_Sub4_2.aClass100_138)) {
                Static41.anInt1309 = Static227.aClass153_94.method4478(Static269.aClass3_Sub2_Sub4_2.aClass100_138) / 10;
                return;
            }
            Static6.method84();
            Static41.anInt1309 = 10;
        }
        if (Static41.anInt1309 == 10) {
            Static158.anInt3846 = Static269.aClass3_Sub2_Sub4_2.anInt763 >> 6 << 6;
            Static2.anInt13 = Static269.aClass3_Sub2_Sub4_2.anInt771 >> 6 << 6;
            Static181.anInt4296 = (Static269.aClass3_Sub2_Sub4_2.anInt758 >> 6 << 6) + 64 - Static2.anInt13;
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
            @Pc(144) int local144 = (Static173.localPlayer.xFine >> 7) + Static225.originX - Static158.anInt3846;
            @Pc(153) int local153 = local144 + (int) (Math.random() * 10.0D) - 5;
            @Pc(168) int local168 = Static2.anInt13 + Static181.anInt4296 - Static142.originZ - (Static173.localPlayer.zFine >> 7) - 1;
            @Pc(177) int local177 = local168 + (int) (Math.random() * 10.0D) - 5;
            if (local153 >= 0 && Static48.anInt1449 > local153 && local177 >= 0 && local177 < Static181.anInt4296) {
                Static13.anInt435 = local153;
                Static28.anInt919 = local177;
            } else {
                Static28.anInt919 = Static2.anInt13 + Static181.anInt4296 - Static269.aClass3_Sub2_Sub4_2.anInt764 * 64 - 1;
                Static13.anInt435 = Static269.aClass3_Sub2_Sub4_2.anInt769 * 64 - Static158.anInt3846;
            }
            Static38.method965();
            Static145.anIntArray330 = new int[Static98.anInt2510 + 1];
            @Pc(235) int local235 = Static181.anInt4296 >> 6;
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
            Static41.anInt1309 = 20;
        } else if (Static41.anInt1309 == 20) {
            Static33.method868(new Packet(Static119.aClass153_44.method4485(Static166.aClass100_779, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            Static41.anInt1309 = 30;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 30) {
            Static47.method3998(new Packet(Static119.aClass153_44.method4485(Static4.aClass100_7, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            Static41.anInt1309 = 40;
            Static234.method4020();
        } else if (Static41.anInt1309 == 40) {
            PreciseSleep.method3980(new Packet(Static119.aClass153_44.method4485(Static73.OVERLAY2, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            Static41.anInt1309 = 50;
            Static234.method4020();
        } else if (Static41.anInt1309 == 50) {
            Static166.method3166(new Packet(Static119.aClass153_44.method4485(Static42.aClass100_331, Static269.aClass3_Sub2_Sub4_2.aClass100_138)));
            Static41.anInt1309 = 60;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 60) {
            if (Static119.aClass153_44.method4497(Static34.method882(new JString[]{Static269.aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}))) {
                if (!Static119.aClass153_44.method4489(Static34.method882(new JString[]{Static269.aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}))) {
                    return;
                }
                Static203.aMapElementTypeList_1 = MapElementTypeList.create(Static34.method882(new JString[]{Static269.aClass3_Sub2_Sub4_2.aClass100_138, Static265.aClass100_1086}), Static119.aClass153_44);
            } else {
                Static203.aMapElementTypeList_1 = new MapElementTypeList(0);
            }
            Static41.anInt1309 = 70;
            Static234.method4020();
        } else if (Static41.anInt1309 == 70) {
            Static273.aClass41_7 = new WorldMapFont(11, true, GameShell.canvas);
            Static41.anInt1309 = 73;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 73) {
            Static152.aClass41_3 = new WorldMapFont(12, true, GameShell.canvas);
            Static41.anInt1309 = 76;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 76) {
            Static169.aClass41_5 = new WorldMapFont(14, true, GameShell.canvas);
            Static41.anInt1309 = 79;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 79) {
            Static130.aClass41_1 = new WorldMapFont(17, true, GameShell.canvas);
            Static41.anInt1309 = 82;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 82) {
            Static203.aClass41_8 = new WorldMapFont(19, true, GameShell.canvas);
            Static41.anInt1309 = 85;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 85) {
            Static130.aClass41_2 = new WorldMapFont(22, true, GameShell.canvas);
            Static41.anInt1309 = 88;
            aClass6.method842(true);
            Static234.method4020();
        } else if (Static41.anInt1309 == 88) {
            Static270.aClass41_9 = new WorldMapFont(26, true, GameShell.canvas);
            Static41.anInt1309 = 91;
            aClass6.method842(true);
            Static234.method4020();
        } else {
            Static160.aClass41_4 = new WorldMapFont(30, true, GameShell.canvas);
            Static41.anInt1309 = 100;
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
                PreciseSleep.sleep(1L);
            }
            if (local16.status == 1) {
                local11 = (FileOnDisk) local16.result;
                @Pc(39) Packet local39 = Static48.method1196();
                local11.method5134(local39.data, local39.position, 0);
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
}
