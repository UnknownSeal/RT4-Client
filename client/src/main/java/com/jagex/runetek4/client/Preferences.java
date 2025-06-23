package com.jagex.runetek4.client;

import com.jagex.runetek4.FileOnDisk;
import com.jagex.runetek4.PrivilegedRequest;
import com.jagex.runetek4.SceneGraph;
import com.jagex.runetek4.core.io.Packet;
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

    @OriginalMember(owner = "runetek4.client!ra", name = "R", descriptor = "Z")
    public static boolean characterShadowsOn = true;

    @OriginalMember(owner = "client!ba", name = "x", descriptor = "Z")
    public static boolean flickeringEffectsOn = true;

    @OriginalMember(owner = "client!be", name = "Kb", descriptor = "Z")
    public static boolean manyIdleAnimations = true;

    @OriginalMember(owner = "runetek4.client!l", name = "k", descriptor = "I")
    public static int sceneryShadowsType = 2;

    @OriginalMember(owner = "runetek4.client!mi", name = "ab", descriptor = "Z")
    public static boolean manyGroundTextures = true;

    @OriginalMember(owner = "runetek4.client!jl", name = "J", descriptor = "I")
    public static int soundEffectVolume = 127;

    @OriginalMember(owner = "runetek4.client!na", name = "o", descriptor = "I")
    public static int lastWorldId = 0;

    @OriginalMember(owner = "runetek4.client!ml", name = "ab", descriptor = "Z")
    public static boolean lowmem = true;

    @OriginalMember(owner = "runetek4.client!k", name = "c", descriptor = "Z")
    public static boolean hdr = false;

    @OriginalMember(owner = "runetek4.client!ga", name = "e", descriptor = "I")
    private static int particles = 2;

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
        setLowmem(true);
        roofsVisible = true;
        stereo = true;
        highWaterDetail = true;
        windowMode = 0;
        fullScreenHeight = 0;
        showGroundDecorations = true;
        flickeringEffectsOn = true;
        manyIdleAnimations = true;
        ambientSoundsVolume = 127;
        fogEnabled = true;
        characterShadowsOn = true;
        fullScreenWidth = 0;
        sceneryShadowsType = 2;
        manyGroundTextures = true;
        highDetailLighting = true;
        musicVolume = 255;
        highDetailTextures = true;
        antiAliasingMode = 0;
        @Pc(48) FileOnDisk local48 = null;
        soundEffectVolume = 127;
        if (GameShell.maxMemory >= 96) {
            setParticles(2);
        } else {
            setParticles(0);
        }
        lastWorldId = 0;
        buildArea = 0;
        aBoolean63 = false;
        cursorsEnabled = true;
        safeMode = false;
        hdr = false;
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
                decode(new Packet(local106));
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
    public static void setLowmem(@OriginalArg(1) boolean arg0) {
        lowmem = arg0;
        SceneGraph.aBoolean130 = !SceneGraph.allLevelsAreVisible();
    }

    @OriginalMember(owner = "client!dl", name = "a", descriptor = "(B)Lclient!wa;")
    public static Packet encode() {
        @Pc(4) Packet local4 = new Packet(34);
        local4.p1(11);
        local4.p1(brightness);
        local4.p1(lowmem ? 1 : 0);
        local4.p1(roofsVisible ? 1 : 0);
        local4.p1(showGroundDecorations ? 1 : 0);
        local4.p1(highDetailTextures ? 1 : 0);
        local4.p1(manyIdleAnimations ? 1 : 0);
        local4.p1(flickeringEffectsOn ? 1 : 0);
        local4.p1(manyGroundTextures ? 1 : 0);
        local4.p1(characterShadowsOn ? 1 : 0);
        local4.p1(sceneryShadowsType);
        local4.p1(highDetailLighting ? 1 : 0);
        local4.p1(highWaterDetail ? 1 : 0);
        local4.p1(fogEnabled ? 1 : 0);
        local4.p1(windowMode);
        local4.p1(stereo ? 1 : 0);
        local4.p1(soundEffectVolume);
        local4.p1(musicVolume);
        local4.p1(ambientSoundsVolume);
        local4.p2(fullScreenWidth);
        local4.p2(fullScreenHeight);
        local4.p1(getParticleSetting());
        local4.p4(lastWorldId);
        local4.p1(favoriteWorlds);
        local4.p1(safeMode ? 1 : 0);
        local4.p1(aBoolean63 ? 1 : 0);
        local4.p1(buildArea);
        local4.p1(hdr ? 1 : 0);
        local4.p1(cursorsEnabled ? 1 : 0);
        return local4;
    }

    @OriginalMember(owner = "runetek4.client!kk", name = "b", descriptor = "(Lclient!wa;I)V")
    public static void decode(@OriginalArg(0) Packet packet) {
        if (packet.data.length - packet.offset < 1) {
            return;
        }
        @Pc(21) int version = packet.g1();
        if (version < 0 || version > 11) {
            return;
        }
        @Pc(34) byte len;
        if (version == 11) {
            len = 33;
        } else if (version == 10) {
            len = 32;
        } else if (version == 9) {
            len = 31;
        } else if (version == 8) {
            len = 30;
        } else if (version == 7) {
            len = 29;
        } else if (version == 6) {
            len = 28;
        } else if (version == 5) {
            len = 28;
        } else if (version == 4) {
            len = 24;
        } else if (version == 3) {
            len = 23;
        } else if (version == 2) {
            len = 22;
        } else if (version == 1) {
            len = 23;
        } else {
            len = 19;
        }
        if (packet.data.length - packet.offset < len) {
            return;
        }
        brightness = packet.g1();
        if (brightness < 1) {
            brightness = 1;
        } else if (brightness > 4) {
            brightness = 4;
        }
        setLowmem(packet.g1() == 1);
        roofsVisible = packet.g1() == 1;
        showGroundDecorations = packet.g1() == 1;
        highDetailTextures = packet.g1() == 1;
        manyIdleAnimations = packet.g1() == 1;
        flickeringEffectsOn = packet.g1() == 1;
        manyGroundTextures = packet.g1() == 1;
        characterShadowsOn = packet.g1() == 1;
        sceneryShadowsType = packet.g1();
        if (sceneryShadowsType > 2) {
            sceneryShadowsType = 2;
        }
        if (version < 2) {
            highDetailLighting = packet.g1() == 1;
            packet.g1();
        } else {
            highDetailLighting = packet.g1() == 1;
        }
        highWaterDetail = packet.g1() == 1;
        fogEnabled = packet.g1() == 1;
        windowMode = packet.g1();
        if (windowMode > 2) {
            windowMode = 2;
        }
        antiAliasingMode = windowMode;
        stereo = packet.g1() == 1;
        soundEffectVolume = packet.g1();
        if (soundEffectVolume > 127) {
            soundEffectVolume = 127;
        }
        musicVolume = packet.g1();
        ambientSoundsVolume = packet.g1();
        if (ambientSoundsVolume > 127) {
            ambientSoundsVolume = 127;
        }
        if (version >= 1) {
            fullScreenWidth = packet.g2();
            fullScreenHeight = packet.g2();
        }
        if (version >= 3 && version < 6) {
            packet.g1();
        }
        if (version >= 4) {
            @Pc(386) int particles = packet.g1();
            if (GameShell.maxMemory < 96) {
                particles = 0;
            }
            setParticles(particles);
        }
        if (version >= 5) {
            lastWorldId = packet.g4();
        }
        if (version >= 6) {
            favoriteWorlds = packet.g1();
        }
        if (version >= 7) {
            safeMode = packet.g1() == 1;
        }
        if (version >= 8) {
            aBoolean63 = packet.g1() == 1;
        }
        if (version >= 9) {
            buildArea = packet.g1();
        }
        if (version >= 10) {
            hdr = packet.g1() != 0;
        }
        if (version >= 11) {
            cursorsEnabled = packet.g1() != 0;
        }
    }

    @OriginalMember(owner = "runetek4.client!lf", name = "c", descriptor = "(I)I")
    public static int toInt() {
        return ((stereo ? 1 : 0) << 19) + (((fogEnabled ? 1 : 0) << 16) + ((highWaterDetail ? 1 : 0) << 15) + ((highDetailLighting ? 1 : 0) << 13) + ((characterShadowsOn ? 1 : 0) << 10) + ((manyGroundTextures ? 1 : 0) << 9) + ((manyIdleAnimations ? 1 : 0) << 7) + ((highDetailTextures ? 1 : 0) << 6) + ((showGroundDecorations ? 1 : 0) << 5) + (((lowmem ? 1 : 0) << 3) + (brightness & 0x7) - (-((roofsVisible ? 1 : 0) << 4) + -((flickeringEffectsOn ? 1 : 0) << 8)) - (-((sceneryShadowsType & 0x3) << 11) + -((soundEffectVolume == 0 ? 0 : 1) << 20) - (((musicVolume == 0 ? 0 : 1) << 21) + ((ambientSoundsVolume == 0 ? 0 : 1) << 22)))) + (getParticleSetting() << 23));
    }
}
