package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.flotype.FloorOverlayTypeList;
import com.jagex.runetek4.game.config.meltype.MapElementList;
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
    @OriginalMember(owner = "runetek4.client!gf", name = "N", descriptor = "Z")
    public static boolean roofsVisible = true;
    @OriginalMember(owner = "runetek4.client!uf", name = "b", descriptor = "Z")
    public static boolean showGroundDecorations = true;
    @OriginalMember(owner = "runetek4.client!ec", name = "n", descriptor = "Z")
    public static boolean highDetailTextures = true;
    @OriginalMember(owner = "runetek4.client!il", name = "I", descriptor = "I")
    public static int brightness = 3;
    @OriginalMember(owner = "client!fk", name = "g", descriptor = "Z")
    public static boolean fogEnabled = true;
    @OriginalMember(owner = "runetek4.client!od", name = "c", descriptor = "Z")
    public static boolean highDetailLighting = true;
    @OriginalMember(owner = "client!fb", name = "m", descriptor = "Z")
    public static boolean cursorsEnabled = true;
    @OriginalMember(owner = "client!bb", name = "n", descriptor = "I")
    public static int musicVolume = 255;
    @OriginalMember(owner = "runetek4.client!rm", name = "g", descriptor = "Z")
    public static boolean highWaterDetail = true;
    @OriginalMember(owner = "runetek4.client!ga", name = "e", descriptor = "I")
    private static int particles = 2;

    @OriginalMember(owner = "runetek4.client!pa", name = "d", descriptor = "(I)V")
    public static void method3413() {
        if (Static269.aClass3_Sub2_Sub4_2 == null) {
            return;
        }
        if (WorldMap.loadPercentage < 10) {
            if (!MapList.archive.isGroupReady(Static269.aClass3_Sub2_Sub4_2.group)) {
                WorldMap.loadPercentage = client.js5Archive23.method4478(Static269.aClass3_Sub2_Sub4_2.group) / 10;
                return;
            }
            client.method84();
            WorldMap.loadPercentage = 10;
        }
        if (WorldMap.loadPercentage == 10) {
            WorldMap.originX = Static269.aClass3_Sub2_Sub4_2.displayMinX >> 6 << 6;
            WorldMap.originZ = Static269.aClass3_Sub2_Sub4_2.displayMaxX >> 6 << 6;
            WorldMap.length = (Static269.aClass3_Sub2_Sub4_2.displayMinZ >> 6 << 6) + 64 - WorldMap.originZ;
            Static48.anInt1449 = (Static269.aClass3_Sub2_Sub4_2.displayMaxZ >> 6 << 6) + 64 - WorldMap.originX;
            if (Static269.aClass3_Sub2_Sub4_2.defaultZoom == 37) {
                Static83.aFloat3 = 3.0F;
                Static138.aFloat14 = 3.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.defaultZoom == 50) {
                Static83.aFloat3 = 4.0F;
                Static138.aFloat14 = 4.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.defaultZoom == 75) {
                Static83.aFloat3 = 6.0F;
                Static138.aFloat14 = 6.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.defaultZoom == 100) {
                Static83.aFloat3 = 8.0F;
                Static138.aFloat14 = 8.0F;
            } else if (Static269.aClass3_Sub2_Sub4_2.defaultZoom == 200) {
                Static83.aFloat3 = 16.0F;
                Static138.aFloat14 = 16.0F;
            } else {
                Static83.aFloat3 = 8.0F;
                Static138.aFloat14 = 8.0F;
            }
            @Pc(144) int local144 = (PlayerList.self.xFine >> 7) + Camera.originX - WorldMap.originX;
            @Pc(153) int local153 = local144 + (int) (Math.random() * 10.0D) - 5;
            @Pc(168) int local168 = WorldMap.originZ + WorldMap.length - Camera.originZ - (PlayerList.self.zFine >> 7) - 1;
            @Pc(177) int local177 = local168 + (int) (Math.random() * 10.0D) - 5;
            if (local153 >= 0 && Static48.anInt1449 > local153 && local177 >= 0 && local177 < WorldMap.length) {
                WorldMap.anInt435 = local153;
                WorldMap.anInt919 = local177;
            } else {
                WorldMap.anInt919 = WorldMap.originZ + WorldMap.length - Static269.aClass3_Sub2_Sub4_2.originZ * 64 - 1;
                WorldMap.anInt435 = Static269.aClass3_Sub2_Sub4_2.originX * 64 - WorldMap.originX;
            }
            Static38.method965();
            Static145.anIntArray330 = new int[FloorOverlayTypeList.capacity + 1];
            @Pc(235) int local235 = WorldMap.length >> 6;
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
            Static33.method868(new Packet(MapList.archive.fetchFile(Static166.aClass100_779, Static269.aClass3_Sub2_Sub4_2.group)));
            WorldMap.loadPercentage = 30;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 30) {
            WorldMap.method3998(new Packet(MapList.archive.fetchFile(Static4.aClass100_7, Static269.aClass3_Sub2_Sub4_2.group)));
            WorldMap.loadPercentage = 40;
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 40) {
            PreciseSleep.method3980(new Packet(MapList.archive.fetchFile(Static73.OVERLAY2, Static269.aClass3_Sub2_Sub4_2.group)));
            WorldMap.loadPercentage = 50;
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 50) {
            Static166.method3166(new Packet(MapList.archive.fetchFile(Static42.aClass100_331, Static269.aClass3_Sub2_Sub4_2.group)));
            WorldMap.loadPercentage = 60;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 60) {
            if (MapList.archive.isGroupNameValid(JString.concatenate(new JString[]{Static269.aClass3_Sub2_Sub4_2.group, Static265.aClass100_1086}))) {
                if (!MapList.archive.isGroupReady(JString.concatenate(new JString[]{Static269.aClass3_Sub2_Sub4_2.group, Static265.aClass100_1086}))) {
                    return;
                }
                Static203.aMapElementList_1 = MapElementList.create(JString.concatenate(new JString[]{Static269.aClass3_Sub2_Sub4_2.group, Static265.aClass100_1086}), MapList.archive);
            } else {
                Static203.aMapElementList_1 = new MapElementList(0);
            }
            WorldMap.loadPercentage = 70;
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 70) {
            Static273.aClass41_7 = new WorldMapFont(11, true, GameShell.canvas);
            WorldMap.loadPercentage = 73;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 73) {
            Static152.aClass41_3 = new WorldMapFont(12, true, GameShell.canvas);
            WorldMap.loadPercentage = 76;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 76) {
            Static169.aClass41_5 = new WorldMapFont(14, true, GameShell.canvas);
            WorldMap.loadPercentage = 79;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 79) {
            Static130.aClass41_1 = new WorldMapFont(17, true, GameShell.canvas);
            WorldMap.loadPercentage = 82;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 82) {
            Static203.aClass41_8 = new WorldMapFont(19, true, GameShell.canvas);
            WorldMap.loadPercentage = 85;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 85) {
            Static130.aClass41_2 = new WorldMapFont(22, true, GameShell.canvas);
            WorldMap.loadPercentage = 88;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (WorldMap.loadPercentage == 88) {
            Static270.aClass41_9 = new WorldMapFont(26, true, GameShell.canvas);
            WorldMap.loadPercentage = 91;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else {
            Static160.aClass41_4 = new WorldMapFont(30, true, GameShell.canvas);
            WorldMap.loadPercentage = 100;
            ClientProt.ping(true);
            GameShell.resetTimer();
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
                @Pc(39) Packet local39 = encode();
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
        brightness = 3;
        setAllLevelsVisible(true);
        roofsVisible = true;
        stereo = true;
        highWaterDetail = true;
        windowMode = 0;
        fullScreenHeight = 0;
        showGroundDecorations = true;
        Static11.aBoolean15 = true;
        Static15.lowMemory = true;
        ambientSoundsVolume = 127;
        fogEnabled = true;
        Static209.aBoolean240 = true;
        fullScreenWidth = 0;
        Static139.anInt3451 = 2;
        Static159.aBoolean189 = true;
        highDetailLighting = true;
        musicVolume = 255;
        highDetailTextures = true;
        antiAliasingMode = 0;
        @Pc(48) FileOnDisk local48 = null;
        Static125.anInt3104 = 127;
        if (GameShell.maxMemory >= 96) {
            setParticles(2);
        } else {
            setParticles(0);
        }
        Static164.anInt3988 = 0;
        buildArea = 0;
        aBoolean63 = false;
        cursorsEnabled = true;
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

    @OriginalMember(owner = "runetek4.client!ec", name = "a", descriptor = "(IZ)V")
    public static void setAllLevelsVisible(@OriginalArg(1) boolean arg0) {
        Static162.aBoolean190 = arg0;
        SceneGraph.aBoolean130 = !SceneGraph.allLevelsvisible();
    }

    @OriginalMember(owner = "client!dl", name = "a", descriptor = "(B)Lclient!wa;")
    public static Packet encode() {
        @Pc(4) Packet local4 = new Packet(34);
        local4.p1(11);
        local4.p1(brightness);
        local4.p1(Static162.aBoolean190 ? 1 : 0);
        local4.p1(roofsVisible ? 1 : 0);
        local4.p1(showGroundDecorations ? 1 : 0);
        local4.p1(highDetailTextures ? 1 : 0);
        local4.p1(Static15.lowMemory ? 1 : 0);
        local4.p1(Static11.aBoolean15 ? 1 : 0);
        local4.p1(Static159.aBoolean189 ? 1 : 0);
        local4.p1(Static209.aBoolean240 ? 1 : 0);
        local4.p1(Static139.anInt3451);
        local4.p1(highDetailLighting ? 1 : 0);
        local4.p1(highWaterDetail ? 1 : 0);
        local4.p1(fogEnabled ? 1 : 0);
        local4.p1(windowMode);
        local4.p1(stereo ? 1 : 0);
        local4.p1(Static125.anInt3104);
        local4.p1(musicVolume);
        local4.p1(ambientSoundsVolume);
        local4.p2(fullScreenWidth);
        local4.p2(fullScreenHeight);
        local4.p1(getParticleSetting());
        local4.p4(Static164.anInt3988);
        local4.p1(favoriteWorlds);
        local4.p1(safeMode ? 1 : 0);
        local4.p1(aBoolean63 ? 1 : 0);
        local4.p1(buildArea);
        local4.p1(Static127.aBoolean159 ? 1 : 0);
        local4.p1(cursorsEnabled ? 1 : 0);
        return local4;
    }
}
