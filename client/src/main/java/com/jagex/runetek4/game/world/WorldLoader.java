package com.jagex.runetek4.game.world;

import com.jagex.runetek4.Camera;
import com.jagex.runetek4.PlayerList;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.LoginManager;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.entity.entity.Npc;
import com.jagex.runetek4.entity.entity.NpcList;
import com.jagex.runetek4.entity.entity.PathingEntity;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.game.locs.ChangeLocRequest;
import com.jagex.runetek4.game.logic.CollisionMap;
import com.jagex.runetek4.game.logic.PathFinder;
import com.jagex.runetek4.game.map.Map;
import com.jagex.runetek4.game.map.MapElementList;
import com.jagex.runetek4.game.map.MapList;
import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.lighting.FogManager;
import com.jagex.runetek4.graphics.lighting.LightingManager;
import com.jagex.runetek4.graphics.lighting.ShadowManager;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WorldLoader {
    @OriginalMember(owner = "client!bh", name = "C", descriptor = "Lclient!na;")
    public static final JString COMPLETE_PERCENT = JString.parse("<br>(X100(U(Y");
    @OriginalMember(owner = "runetek4.client!e", name = "Dc", descriptor = "Lclient!na;")
    public static final JString labels = JString.parse("_labels");
    @OriginalMember(owner = "runetek4.client!i", name = "ic", descriptor = "Lclient!na;")
    public static final JString m = JString.parse("m");
    @OriginalMember(owner = "runetek4.client!wb", name = "e", descriptor = "Lclient!na;")
    public static final JString l = JString.parse("l");
    @OriginalMember(owner = "client!gm", name = "W", descriptor = "Lclient!na;")
    public static final JString UNDERSCORE = JString.parse("_");
    @OriginalMember(owner = "runetek4.client!rj", name = "Z", descriptor = "[I")
    public static final int[] regionIndexLookup = new int[64];
    @OriginalMember(owner = "runetek4.client!mf", name = "O", descriptor = "[[B")
    public static byte[][] locationMapFilesBuffer;
    @OriginalMember(owner = "runetek4.client!te", name = "H", descriptor = "[I")
    public static int[] regionBitPacked;
    @OriginalMember(owner = "client!bi", name = "Y", descriptor = "[[B")
    public static byte[][] underWaterLocationsMapFilesBuffer;
    @OriginalMember(owner = "runetek4.client!nm", name = "U", descriptor = "I")
    public static int mapFilesMissingCount = 0;
    @OriginalMember(owner = "runetek4.client!wc", name = "g", descriptor = "I")
    public static int locLoadFailures = 0;
    @OriginalMember(owner = "runetek4.client!t", name = "y", descriptor = "I")
    public static int loadingScreenState = 0;
    @OriginalMember(owner = "runetek4.client!we", name = "H", descriptor = "[[B")
    public static byte[][] mapFilesBuffer;
    @OriginalMember(owner = "runetek4.client!cl", name = "V", descriptor = "[I")
    public static int[] underWaterLocationsMapFileIds;
    @OriginalMember(owner = "runetek4.client!pg", name = "jb", descriptor = "[[B")
    public static byte[][] npcSpawnsFilesBuffer;
    @OriginalMember(owner = "client!runetek4.client", name = "lb", descriptor = "[I")
    public static int[] mapFileIds;
    @OriginalMember(owner = "runetek4.client!pa", name = "L", descriptor = "[[B")
    public static byte[][] underWaterMapFilesBuffer;
    @OriginalMember(owner = "client!fl", name = "D", descriptor = "[[I")
    public static int[][] regionsXteaKeys;
    @OriginalMember(owner = "runetek4.client!nm", name = "P", descriptor = "[I")
    public static int[] npcSpawnsFileIds;
    @OriginalMember(owner = "runetek4.client!hk", name = "bb", descriptor = "[I")
    public static int[] underWaterMapFileIds;
    @OriginalMember(owner = "runetek4.client!nj", name = "j", descriptor = "[I")
    public static int[] locationsMapFileIds;
    @OriginalMember(owner = "runetek4.client!mh", name = "hb", descriptor = "Lclient!bn;")
    public static Map map;
    @OriginalMember(owner = "runetek4.client!tb", name = "X", descriptor = "Lclient!se;")
    public static MapElementList mapElementList;
    @OriginalMember(owner = "runetek4.client!sk", name = "lb", descriptor = "Z")
    public static boolean aBoolean252 = false;
    @OriginalMember(owner = "runetek4.client!mg", name = "Q", descriptor = "I")
    public static int anInt3811 = 0;

    @OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(ZB)V")
    public static void loadDynamicLocsToScene(@OriginalArg(0) boolean arg0) {
        @Pc(19) byte local19;
        @Pc(21) byte[][] local21;
        if (GlRenderer.enabled && arg0) {
            local21 = underWaterLocationsMapFilesBuffer;
            local19 = 1;
        } else {
            local19 = 4;
            local21 = locationMapFilesBuffer;
        }
        for (@Pc(29) int local29 = 0; local29 < local19; local29++) {
            Client.audioLoop();
            for (@Pc(36) int local36 = 0; local36 < 13; local36++) {
                for (@Pc(43) int local43 = 0; local43 < 13; local43++) {
                    @Pc(56) int local56 = Protocol.anIntArrayArrayArray18[local29][local36][local43];
                    if (local56 != -1) {
                        @Pc(67) int local67 = local56 >> 24 & 0x3;
                        if (!arg0 || local67 == 0) {
                            @Pc(77) int local77 = local56 >> 1 & 0x3;
                            @Pc(83) int local83 = local56 >> 14 & 0x3FF;
                            @Pc(89) int local89 = local56 >> 3 & 0x7FF;
                            @Pc(99) int local99 = local89 / 8 + (local83 / 8 << 8);
                            for (@Pc(101) int local101 = 0; local101 < regionBitPacked.length; local101++) {
                                if (regionBitPacked[local101] == local99 && local21[local101] != null) {
                                    method3771(PathFinder.collisionMaps, local29, local21[local101], local67, local77, local36 * 8, local43 * 8, arg0, (local83 & 0x7) * 8, (local89 & 0x7) * 8);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!rj", name = "a", descriptor = "([Lclient!mj;I[BIIIIZIIB)V")
    public static void method3771(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) byte[] arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) boolean arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9) {
        @Pc(7) int local7 = -1;
        @Pc(12) Packet local12 = new Packet(arg2);
        while (true) {
            @Pc(20) int local20 = local12.gVarSmart();
            if (local20 == 0) {
                return;
            }
            local7 += local20;
            @Pc(31) int local31 = 0;
            while (true) {
                @Pc(35) int local35 = local12.gSmart1or2();
                if (local35 == 0) {
                    break;
                }
                local31 += local35 - 1;
                @Pc(50) int local50 = local31 & 0x3F;
                @Pc(56) int local56 = local31 >> 6 & 0x3F;
                @Pc(60) int local60 = local31 >> 12;
                @Pc(64) int local64 = local12.g1();
                @Pc(68) int local68 = local64 >> 2;
                @Pc(72) int local72 = local64 & 0x3;
                if (arg3 == local60 && local56 >= arg8 && local56 < arg8 + 8 && arg9 <= local50 && arg9 + 8 > local50) {
                    @Pc(103) LocType local103 = LocTypeList.get(local7);
                    @Pc(120) int local120 = method1286(local50 & 0x7, arg4, local72, local103.length, local103.width, local56 & 0x7) + arg5;
                    @Pc(137) int local137 = method4541(local103.width, arg4, local103.length, local56 & 0x7, local72, local50 & 0x7) + arg6;
                    if (local120 > 0 && local137 > 0 && local120 < 103 && local137 < 103) {
                        @Pc(154) CollisionMap local154 = null;
                        if (!arg7) {
                            @Pc(159) int local159 = arg1;
                            if ((SceneGraph.renderFlags[1][local120][local137] & 0x2) == 2) {
                                local159 = arg1 - 1;
                            }
                            if (local159 >= 0) {
                                local154 = arg0[local159];
                            }
                        }
                        SceneGraph.addLoc(arg1, !arg7, arg1, arg7, local154, local7, local68, local120, local137, local72 + arg4 & 0x3);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIII)I")
    public static int method1286(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
        if ((arg2 & 0x1) == 1) {
            @Pc(10) int local10 = arg4;
            arg4 = arg3;
            arg3 = local10;
        }
        @Pc(18) int local18 = arg1 & 0x3;
        if (local18 == 0) {
            return arg5;
        } else if (local18 == 1) {
            return arg0;
        } else if (local18 == 2) {
            return 7 + 1 - arg5 - arg4;
        } else {
            return 7 + 1 - arg0 - arg3;
        }
    }

    @OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(IIBIIII)I")
    public static int method4541(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
        if ((arg4 & 0x1) == 1) {
            @Pc(9) int local9 = arg0;
            arg0 = arg2;
            arg2 = local9;
        }
        @Pc(29) int local29 = arg1 & 0x3;
        if (local29 == 0) {
            return arg5;
        } else if (local29 == 1) {
            return 7 + 1 - arg3 - arg0;
        } else if (local29 == 2) {
            return 1 + 7 - arg2 - arg5;
        } else {
            return arg3;
        }
    }

    @OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(ZB)V")
    public static void loadDynamicTerrain(@OriginalArg(0) boolean arg0) {
        @Pc(11) byte local11;
        @Pc(13) byte[][] local13;
        if (GlRenderer.enabled && arg0) {
            local11 = 1;
            local13 = underWaterMapFilesBuffer;
        } else {
            local13 = mapFilesBuffer;
            local11 = 4;
        }
        for (@Pc(21) int local21 = 0; local21 < local11; local21++) {
            Client.audioLoop();
            for (@Pc(32) int local32 = 0; local32 < 13; local32++) {
                for (@Pc(39) int local39 = 0; local39 < 13; local39++) {
                    @Pc(52) int local52 = Protocol.anIntArrayArrayArray18[local21][local32][local39];
                    @Pc(54) boolean local54 = false;
                    if (local52 != -1) {
                        @Pc(65) int local65 = local52 >> 24 & 0x3;
                        if (!arg0 || local65 == 0) {
                            @Pc(76) int local76 = local52 >> 3 & 0x7FF;
                            @Pc(82) int local82 = local52 >> 1 & 0x3;
                            @Pc(88) int local88 = local52 >> 14 & 0x3FF;
                            @Pc(98) int local98 = (local88 / 8 << 8) + local76 / 8;
                            for (@Pc(100) int local100 = 0; local100 < regionBitPacked.length; local100++) {
                                if (regionBitPacked[local100] == local98 && local13[local100] != null) {
                                    SceneGraph.method4228(local82, local32 * 8, local21, PathFinder.collisionMaps, local39 * 8, local13[local100], local65, (local76 & 0x7) * 8, (local88 & 0x7) * 8, arg0);
                                    local54 = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!local54) {
                        SceneGraph.method645(local21, local39 * 8, local32 * 8, 8, 8);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!gn", name = "a", descriptor = "(ZI)V")
    public static void loadTerrainData(@OriginalArg(0) boolean arg0) {
        @Pc(7) byte local7;
        @Pc(9) byte[][] local9;
        if (GlRenderer.enabled && arg0) {
            local7 = 1;
            local9 = underWaterMapFilesBuffer;
        } else {
            local7 = 4;
            local9 = mapFilesBuffer;
        }
        @Pc(18) int local18 = local9.length;
        @Pc(20) int local20;
        @Pc(38) int local38;
        @Pc(49) int local49;
        @Pc(53) byte[] local53;
        for (local20 = 0; local20 < local18; local20++) {
            local38 = (regionBitPacked[local20] >> 8) * 64 - Camera.originX;
            local49 = (regionBitPacked[local20] & 0xFF) * 64 - Camera.originZ;
            local53 = local9[local20];
            if (local53 != null) {
                Client.audioLoop();
                SceneGraph.method2203(PathFinder.collisionMaps, arg0, SceneGraph.centralZoneX * 8 - 48, local49, local38, (SceneGraph.centralZoneZ - 6) * 8, local53);
            }
        }
        for (local20 = 0; local20 < local18; local20++) {
            local38 = (regionBitPacked[local20] >> 8) * 64 - Camera.originX;
            local49 = (regionBitPacked[local20] & 0xFF) * 64 - Camera.originZ;
            local53 = local9[local20];
            if (local53 == null && SceneGraph.centralZoneZ < 800) {
                Client.audioLoop();
                for (@Pc(130) int local130 = 0; local130 < local7; local130++) {
                    SceneGraph.method645(local130, local49, local38, 64, 64);
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(IIIIZIZ)V")
    public static void initializeMapRegion(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) boolean arg4, @OriginalArg(5) int arg5) {
        if (SceneGraph.centralZoneX == arg2 && arg1 == SceneGraph.centralZoneZ && (SceneGraph.centralPlane == arg0 || SceneGraph.allLevelsAreVisible())) {
            return;
        }
        SceneGraph.centralZoneX = arg2;
        SceneGraph.centralZoneZ = arg1;
        SceneGraph.centralPlane = arg0;
        if (SceneGraph.allLevelsAreVisible()) {
            SceneGraph.centralPlane = 0;
        }
        if (arg4) {
            Client.processGameStatus(28);
        } else {
            Client.processGameStatus(25);
        }
        Fonts.drawTextOnScreen(true, LocalizedText.LOADING);
        @Pc(53) int local53 = Camera.originZ;
        @Pc(55) int local55 = Camera.originX;
        Camera.originZ = arg1 * 8 - 48;
        Camera.originX = (arg2 - 6) * 8;
        map = MapList.getContainingSource(SceneGraph.centralZoneX * 8, SceneGraph.centralZoneZ * 8);
        @Pc(81) int dz = Camera.originZ - local53;
        @Pc(86) int dx = Camera.originX - local55;
        mapElementList = null;
        @Pc(96) int i;
        @Pc(103) Npc local103;
        @Pc(109) int j;
        if (arg4) {
            NpcList.npcCount = 0;
            for (i = 0; i < 32768; i++) {
                local103 = NpcList.npcs[i];
                if (local103 != null) {
                    local103.xFine -= dx * 128;
                    local103.zFine -= dz * 128;
                    if (local103.xFine >= 0 && local103.xFine <= 13184 && local103.zFine >= 0 && local103.zFine <= 13184) {
                        for (j = 0; j < 10; j++) {
                            local103.movementQueueX[j] -= dx;
                            local103.movementQueueZ[j] -= dz;
                        }
                        NpcList.npcIds[NpcList.npcCount++] = i;
                    } else {
                        NpcList.npcs[i].setNpcType(null);
                        NpcList.npcs[i] = null;
                    }
                }
            }
        } else {
            for (i = 0; i < 32768; i++) {
                local103 = NpcList.npcs[i];
                if (local103 != null) {
                    for (j = 0; j < 10; j++) {
                        local103.movementQueueX[j] -= dx;
                        local103.movementQueueZ[j] -= dz;
                    }
                    local103.xFine -= dx * 128;
                    local103.zFine -= dz * 128;
                }
            }
        }
        for (i = 0; i < 2048; i++) {
            @Pc(265) Player player = PlayerList.players[i];
            if (player != null) {
                for (j = 0; j < 10; j++) {
                    player.movementQueueX[j] -= dx;
                    player.movementQueueZ[j] -= dz;
                }
                player.xFine -= dx * 128;
                player.zFine -= dz * 128;
            }
        }
        Player.plane = arg0;
        PlayerList.self.teleport(arg5, false, arg3);
        @Pc(322) byte endTileX = 104;
        @Pc(324) byte startTileX = 0;
        @Pc(326) byte startTileZ = 0;
        @Pc(328) byte dirZ = 1;
        @Pc(330) byte endTileZ = 104;
        @Pc(332) byte dirX = 1;
        if (dz < 0) {
            dirZ = -1;
            endTileZ = -1;
            startTileZ = 103;
        }
        if (dx < 0) {
            dirX = -1;
            startTileX = 103;
            endTileX = -1;
        }
        for (@Pc(358) int x = startTileX; x != endTileX; x += dirX) {
            for (@Pc(367) int z = startTileZ; z != endTileZ; z += dirZ) {
                @Pc(378) int lastX = dx + x;
                @Pc(382) int lastZ = z + dz;
                for (@Pc(384) int level = 0; level < 4; level++) {
                    if (lastX >= 0 && lastZ >= 0 && lastX < 104 && lastZ < 104) {
                        SceneGraph.objStacks[level][x][z] = SceneGraph.objStacks[level][lastX][lastZ];
                    } else {
                        SceneGraph.objStacks[level][x][z] = null;
                    }
                }
            }
        }
        for (@Pc(451) ChangeLocRequest loc = (ChangeLocRequest) ChangeLocRequest.queue.head(); loc != null; loc = (ChangeLocRequest) ChangeLocRequest.queue.next()) {
            loc.z -= dz;
            loc.x -= dx;
            if (loc.x < 0 || loc.z < 0 || loc.x >= 104 || loc.z >= 104) {
                loc.unlink();
            }
        }
        if (arg4) {
            Camera.renderX -= dx * 128;
            Camera.renderZ -= dz * 128;
            Camera.anInt4232 -= dz;
            Camera.anInt5449 -= dx;
            Camera.anInt5765 -= dz;
            Camera.anInt5375 -= dx;
        } else {
            Camera.cameraType = 1;
        }
        SoundPlayer.size = 0;
        if (LoginManager.mapFlagX != 0) {
            LoginManager.mapFlagZ -= dz;
            LoginManager.mapFlagX -= dx;
        }
        if (GlRenderer.enabled && arg4 && (Math.abs(dx) > 104 || Math.abs(dz) > 104)) {
            FogManager.setInstantFade();
        }
        LightingManager.anInt2875 = -1;
        SceneGraph.spotanims.clear();
        SceneGraph.projectiles.clear();
    }

    @OriginalMember(owner = "client!ca", name = "a", descriptor = "(ZI)V")
    public static void loadLocsToScene(@OriginalArg(0) boolean highmem) {
        @Pc(13) int local13 = mapFilesBuffer.length;
        @Pc(19) byte[][] local19;
        if (GlRenderer.enabled && highmem) {
            local19 = underWaterLocationsMapFilesBuffer;
        } else {
            local19 = locationMapFilesBuffer;
        }
        for (@Pc(25) int local25 = 0; local25 < local13; local25++) {
            @Pc(32) byte[] local32 = local19[local25];
            if (local32 != null) {
                @Pc(45) int local45 = (regionBitPacked[local25] >> 8) * 64 - Camera.originX;
                @Pc(56) int local56 = (regionBitPacked[local25] & 0xFF) * 64 - Camera.originZ;
                Client.audioLoop();
                SceneGraph.readLocs(local45, highmem, local32, local56, PathFinder.collisionMaps);
            }
        }
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(BII[B)Z")
    public static boolean loadLocs(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) byte[] arg2) {
        @Pc(15) boolean local15 = true;
        @Pc(17) int local17 = -1;
        @Pc(22) Packet local22 = new Packet(arg2);
        label70: while (true) {
            @Pc(26) int local26 = local22.gVarSmart();
            if (local26 == 0) {
                return local15;
            }
            @Pc(33) int local33 = 0;
            local17 += local26;
            @Pc(39) boolean local39 = false;
            while (true) {
                @Pc(78) int local78;
                @Pc(95) LocType local95;
                do {
                    @Pc(72) int local72;
                    @Pc(68) int local68;
                    do {
                        do {
                            do {
                                do {
                                    @Pc(45) int local45;
                                    while (local39) {
                                        local45 = local22.gSmart1or2();
                                        if (local45 == 0) {
                                            continue label70;
                                        }
                                        local22.g1();
                                    }
                                    local45 = local22.gSmart1or2();
                                    if (local45 == 0) {
                                        continue label70;
                                    }
                                    local33 += local45 - 1;
                                    @Pc(58) int local58 = local33 & 0x3F;
                                    @Pc(64) int local64 = local33 >> 6 & 0x3F;
                                    local68 = arg1 + local58;
                                    local72 = arg0 + local64;
                                    local78 = local22.g1() >> 2;
                                } while (local72 <= 0);
                            } while (local68 <= 0);
                        } while (local72 >= 103);
                    } while (local68 >= 103);
                    local95 = LocTypeList.get(local17);
                } while (local78 == 22 && !Preferences.showGroundDecorations && local95.active == 0 && local95.blockwalk != 1 && !local95.forcedecor);
                local39 = true;
                if (!local95.isReady()) {
                    local15 = false;
                    locLoadFailures++;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!gd", name = "c", descriptor = "(I)V")
    public static void rebuildMap() {
        ClientProt.ping(false);
        mapFilesMissingCount = 0;
        @Pc(12) boolean fileExists = true;
        @Pc(14) int regionIndex;
        for (regionIndex = 0; regionIndex < mapFilesBuffer.length; regionIndex++) {
            if (mapFileIds[regionIndex] != -1 && mapFilesBuffer[regionIndex] == null) {
                mapFilesBuffer[regionIndex] = Client.js5Archive5.getfile(mapFileIds[regionIndex], 0);
                if (mapFilesBuffer[regionIndex] == null) {
                    mapFilesMissingCount++;
                    fileExists = false;
                }
            }
            if (locationsMapFileIds[regionIndex] != -1 && locationMapFilesBuffer[regionIndex] == null) {
                locationMapFilesBuffer[regionIndex] = Client.js5Archive5.getfile(locationsMapFileIds[regionIndex], 0, regionsXteaKeys[regionIndex]);
                if (locationMapFilesBuffer[regionIndex] == null) {
                    fileExists = false;
                    mapFilesMissingCount++;
                }
            }
            if (GlRenderer.enabled) {
                if (underWaterMapFileIds[regionIndex] != -1 && underWaterMapFilesBuffer[regionIndex] == null) {
                    underWaterMapFilesBuffer[regionIndex] = Client.js5Archive5.getfile(underWaterMapFileIds[regionIndex], 0);
                    if (underWaterMapFilesBuffer[regionIndex] == null) {
                        fileExists = false;
                        mapFilesMissingCount++;
                    }
                }
                if (underWaterLocationsMapFileIds[regionIndex] != -1 && underWaterLocationsMapFilesBuffer[regionIndex] == null) {
                    underWaterLocationsMapFilesBuffer[regionIndex] = Client.js5Archive5.getfile(underWaterLocationsMapFileIds[regionIndex], 0);
                    if (underWaterLocationsMapFilesBuffer[regionIndex] == null) {
                        mapFilesMissingCount++;
                        fileExists = false;
                    }
                }
            }
            if (npcSpawnsFileIds != null && npcSpawnsFilesBuffer[regionIndex] == null && npcSpawnsFileIds[regionIndex] != -1) {
                npcSpawnsFilesBuffer[regionIndex] = Client.js5Archive5.getfile(npcSpawnsFileIds[regionIndex], 0, regionsXteaKeys[regionIndex]);
                if (npcSpawnsFilesBuffer[regionIndex] == null) {
                    mapFilesMissingCount++;
                    fileExists = false;
                }
            }
        }
        if (mapElementList == null) {
            if (map == null || !Client.js5Archive23.isGroupNameValid(JString.concatenate(new JString[] { map.group, labels}))) {
                mapElementList = new MapElementList(0);
            } else if (Client.js5Archive23.isGroupReady(JString.concatenate(new JString[] { map.group, labels}))) {
                mapElementList = MapElementList.create(JString.concatenate(new JString[] { map.group, labels}), Client.js5Archive23);
            } else {
                fileExists = false;
                mapFilesMissingCount++;
            }
        }
        if (!fileExists) {
            loadingScreenState = 1;
            return;
        }
        locLoadFailures = 0;
        fileExists = true;
        @Pc(320) int chunkX;
        @Pc(309) int chunkZ;
        for (regionIndex = 0; regionIndex < mapFilesBuffer.length; regionIndex++) {
            @Pc(294) byte[] locationData = locationMapFilesBuffer[regionIndex];
            if (locationData != null) {
                chunkZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.originZ;
                chunkX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.originX;
                if (SceneGraph.dynamicMapRegion) {
                    chunkZ = 10;
                    chunkX = 10;
                }
                fileExists &= loadLocs(chunkX, chunkZ, locationData);
            }
            if (GlRenderer.enabled) {
                locationData = underWaterLocationsMapFilesBuffer[regionIndex];
                if (locationData != null) {
                    chunkX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.originX;
                    chunkZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.originZ;
                    if (SceneGraph.dynamicMapRegion) {
                        chunkZ = 10;
                        chunkX = 10;
                    }
                    fileExists &= loadLocs(chunkX, chunkZ, locationData);
                }
            }
        }
        if (!fileExists) {
            loadingScreenState = 2;
            return;
        }
        if (loadingScreenState != 0) {
            Fonts.drawTextOnScreen(true, JString.concatenate(new JString[] { LocalizedText.LOADING, COMPLETE_PERCENT}));
        }
        Client.audioLoop();
        Client.unload();
        @Pc(420) boolean hasUnderWaterMap = false;
        @Pc(427) int index;
        if (GlRenderer.enabled && Preferences.highWaterDetail) {
            for (index = 0; index < mapFilesBuffer.length; index++) {
                if (underWaterLocationsMapFilesBuffer[index] != null || underWaterMapFilesBuffer[index] != null) {
                    hasUnderWaterMap = true;
                    break;
                }
            }
        }
        SceneGraph.init(GlRenderer.enabled ? 28 : 25, hasUnderWaterMap);
        for (index = 0; index < 4; index++) {
            PathFinder.collisionMaps[index].reset();
        }
        for (index = 0; index < 4; index++) {
            for (chunkX = 0; chunkX < 104; chunkX++) {
                for (chunkZ = 0; chunkZ < 104; chunkZ++) {
                    SceneGraph.renderFlags[index][chunkX][chunkZ] = 0;
                }
            }
        }
        AreaSoundManager.clear(false);
        if (GlRenderer.enabled) {
            ShadowManager.shadowMapImage.clear();
            for (index = 0; index < 13; index++) {
                for (chunkX = 0; chunkX < 13; chunkX++) {
                    ShadowManager.shadows[index][chunkX].outputToSprite = true;
                }
            }
        }
        if (GlRenderer.enabled) {
            LightingManager.method2404();
        }
        if (GlRenderer.enabled) {
            FogManager.setDefaultChunksAtmosphere();
        }
        Client.audioLoop();
        System.gc();
        ClientProt.ping(true);
        SceneGraph.load(false);
        if (!SceneGraph.dynamicMapRegion) {
            loadTerrainData(false);
            ClientProt.ping(true);
            if (GlRenderer.enabled) {
                index = PlayerList.self.movementQueueX[0] >> 3;
                chunkX = PlayerList.self.movementQueueZ[0] >> 3;
                FogManager.setLightPosition(chunkX, index);
            }
            loadLocsToScene(false);
            if (npcSpawnsFilesBuffer != null) {
                decodeNpcFiles();
            }
        }
        if (SceneGraph.dynamicMapRegion) {
            loadDynamicTerrain(false);
            ClientProt.ping(true);
            if (GlRenderer.enabled) {
                index = PlayerList.self.movementQueueX[0] >> 3;
                chunkX = PlayerList.self.movementQueueZ[0] >> 3;
                FogManager.setLightPosition(chunkX, index);
            }
            loadDynamicLocsToScene(false);
        }
        Client.unload();
        ClientProt.ping(true);
        SceneGraph.method1169(PathFinder.collisionMaps, false);
        if (GlRenderer.enabled) {
            LightingManager.method2395();
        }
        ClientProt.ping(true);
        index = SceneGraph.firstVisibleLevel;
        if (index > Player.plane) {
            index = Player.plane;
        }
        if (index < Player.plane - 1) {
            // TODO why is this here?
        }
        if (SceneGraph.allLevelsAreVisible()) {
            SceneGraph.method2750(0);
        } else {
            SceneGraph.method2750(SceneGraph.firstVisibleLevel);
        }
        SceneGraph.unload();
        if (GlRenderer.enabled && hasUnderWaterMap) {
            SceneGraph.setUnderwater(true);
            SceneGraph.load(true);
            if (!SceneGraph.dynamicMapRegion) {
                loadTerrainData(true);
                ClientProt.ping(true);
                loadLocsToScene(true);
            }
            if (SceneGraph.dynamicMapRegion) {
                loadDynamicTerrain(true);
                ClientProt.ping(true);
                loadDynamicLocsToScene(true);
            }
            Client.unload();
            ClientProt.ping(true);
            SceneGraph.method1169(PathFinder.collisionMaps, true);
            ClientProt.ping(true);
            SceneGraph.unload();
            SceneGraph.setUnderwater(false);
        }
        if (GlRenderer.enabled) {
            for (chunkX = 0; chunkX < 13; chunkX++) {
                for (chunkZ = 0; chunkZ < 13; chunkZ++) {
                    ShadowManager.shadows[chunkX][chunkZ].method4676(SceneGraph.tileHeights[0], chunkX * 8, chunkZ * 8);
                }
            }
        }
        for (chunkX = 0; chunkX < 104; chunkX++) {
            for (chunkZ = 0; chunkZ < 104; chunkZ++) {
                Protocol.spawnGroundObject(chunkZ, chunkX);
            }
        }
        ClientScriptRunner.method2218();
        Client.audioLoop();
        ChangeLocRequest.method3796();
        Client.unload();
        aBoolean252 = false;
        if (GameShell.frame != null && Protocol.gameServerSocket != null && Client.gameState == 25) {
            Protocol.outboundBuffer.pIsaac1(20);
            Protocol.outboundBuffer.p4(1057001181);
        }
        if (!SceneGraph.dynamicMapRegion) {
            @Pc(815) int maxZ = (SceneGraph.centralZoneZ + 6) / 8;
            @Pc(821) int minZ = (SceneGraph.centralZoneZ - 6) / 8;
            chunkX = (SceneGraph.centralZoneX - 6) / 8;
            chunkZ = (SceneGraph.centralZoneX + 6) / 8;
            for (@Pc(837) int currentX = chunkX - 1; currentX <= chunkZ + 1; currentX++) {
                for (@Pc(850) int currentZ = minZ - 1; currentZ <= maxZ + 1; currentZ++) {
                    if (currentX < chunkX || currentX > chunkZ || currentZ < minZ || currentZ > maxZ) {
                        Client.js5Archive5.prefetchGroup(JString.concatenate(new JString[] {m, JString.parseInt(currentX), UNDERSCORE, JString.parseInt(currentZ) }));
                        Client.js5Archive5.prefetchGroup(JString.concatenate(new JString[] {l, JString.parseInt(currentX), UNDERSCORE, JString.parseInt(currentZ) }));
                    }
                }
            }
        }
        if (Client.gameState == 28) {
            Client.processGameStatus(10);
        } else {
            Client.processGameStatus(30);
            if (Protocol.gameServerSocket != null) {
                Protocol.outboundBuffer.pIsaac1(110);
            }
        }
        WorldMap.method2720();
        Client.audioLoop();
        GameShell.resetTimer();
    }

    @OriginalMember(owner = "runetek4.client!mh", name = "h", descriptor = "(B)V")
    public static void decodeNpcFiles() {
        @Pc(10) int local10 = npcSpawnsFilesBuffer.length;
        for (@Pc(16) int local16 = 0; local16 < local10; local16++) {
            if (npcSpawnsFilesBuffer[local16] != null) {
                @Pc(25) int local25 = -1;
                for (@Pc(27) int local27 = 0; local27 < anInt3811; local27++) {
                    if (regionIndexLookup[local27] == regionBitPacked[local16]) {
                        local25 = local27;
                        break;
                    }
                }
                if (local25 == -1) {
                    regionIndexLookup[anInt3811] = regionBitPacked[local16];
                    local25 = anInt3811++;
                }
                @Pc(67) int local67 = 0;
                @Pc(74) Packet local74 = new Packet(npcSpawnsFilesBuffer[local16]);
                while (local74.offset < npcSpawnsFilesBuffer[local16].length && local67 < 511) {
                    @Pc(97) int local97 = local67++ << 6 | local25;
                    @Pc(103) int local103 = local74.g2();
                    @Pc(107) int local107 = local103 >> 14;
                    @Pc(113) int local113 = local103 >> 7 & 0x3F;
                    @Pc(125) int local125 = local113 + (regionBitPacked[local16] >> 8) * 64 - Camera.originX;
                    @Pc(129) int local129 = local103 & 0x3F;
                    @Pc(142) int local142 = local129 + (regionBitPacked[local16] & 0xFF) * 64 - Camera.originZ;
                    @Pc(148) NpcType local148 = NpcTypeList.get(local74.g2());
                    if (NpcList.npcs[local97] == null && (local148.walkflags & 0x1) > 0 && local107 == SceneGraph.centralPlane && local125 >= 0 && local148.size + local125 < 104 && local142 >= 0 && local142 + local148.size < 104) {
                        NpcList.npcs[local97] = new Npc();
                        @Pc(198) Npc local198 = NpcList.npcs[local97];
                        NpcList.npcIds[NpcList.npcCount++] = local97;
                        local198.lastSeenLoop = Client.loop;
                        local198.setNpcType(local148);
                        local198.setSize(local198.type.size);
                        local198.dstYaw = local198.anInt3381 = PathingEntity.ANGLES[local198.type.respawndir];
                        local198.anInt3376 = local198.type.turnspeed;
                        if (local198.anInt3376 == 0) {
                            local198.anInt3381 = 0;
                        }
                        local198.anInt3365 = local198.type.nas;
                        local198.teleport(local198.getSize(), local125, local142, true);
                    }
                }
            }
        }
    }
}
