package com.jagex.runetek4.game.world;

import com.jagex.runetek4.scene.Camera;
import com.jagex.runetek4.entity.player.PlayerList;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.auth.LoginManager;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.npc.NpcTypeList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.entity.npc.Npc;
import com.jagex.runetek4.entity.npc.NpcList;
import com.jagex.runetek4.entity.PathingEntity;
import com.jagex.runetek4.entity.player.Player;
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

import static com.jagex.runetek4.network.ClientProt.DETECT_MODIFIED_CLIENT;

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
    public static void loadDynamicLocsToScene(@OriginalArg(0) boolean underWater) {
        @Pc(19) byte planeCount;
        @Pc(21) byte[][] mapDataBuffers;
        if (GlRenderer.enabled && underWater) {
            mapDataBuffers = underWaterLocationsMapFilesBuffer;
            planeCount = 1;
        } else {
            planeCount = 4;
            mapDataBuffers = locationMapFilesBuffer;
        }
        for (@Pc(29) int currentPlane = 0; currentPlane < planeCount; currentPlane++) {
            Client.audioLoop();
            for (@Pc(36) int regionX = 0; regionX < 13; regionX++) {
                for (@Pc(43) int regionZ = 0; regionZ < 13; regionZ++) {
                    @Pc(56) int packedLocationData = Protocol.dynamicRegionData[currentPlane][regionX][regionZ];
                    if (packedLocationData != -1) {
                        @Pc(67) int extractedPlane = packedLocationData >> 24 & 0x3;
                        if (!underWater || extractedPlane == 0) {
                            @Pc(77) int rotation = packedLocationData >> 1 & 0x3;
                            @Pc(83) int worldX = packedLocationData >> 14 & 0x3FF;
                            @Pc(89) int worldZ = packedLocationData >> 3 & 0x7FF;
                            @Pc(99) int regionId = worldZ / 8 + (worldX / 8 << 8);
                            for (@Pc(101) int regionIndex = 0; regionIndex < regionBitPacked.length; regionIndex++) {
                                if (regionBitPacked[regionIndex] == regionId && mapDataBuffers[regionIndex] != null) {
                                    parseLocationData(PathFinder.collisionMaps, currentPlane, mapDataBuffers[regionIndex], extractedPlane, rotation, regionX * 8, regionZ * 8, underWater, (worldX & 0x7) * 8, (worldZ & 0x7) * 8);
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
    public static void parseLocationData(@OriginalArg(0) CollisionMap[] collisionMaps, @OriginalArg(1) int plane, @OriginalArg(2) byte[] locationData, @OriginalArg(3) int targetPlane, @OriginalArg(4) int baseRotation, @OriginalArg(5) int baseX, @OriginalArg(6) int baseZ, @OriginalArg(7) boolean underWater, @OriginalArg(8) int regionOffsetX, @OriginalArg(9) int regionOffsetZ) {
        @Pc(7) int locationId = -1;
        @Pc(12) Packet packet = new Packet(locationData);
        while (true) {
            @Pc(20) int idDelta = packet.gVarSmart();
            if (idDelta == 0) {
                return;
            }
            locationId += idDelta;
            @Pc(31) int positionData = 0;
            while (true) {
                @Pc(35) int positionDelta = packet.gSmart1or2();
                if (positionDelta == 0) {
                    break;
                }
                positionData += positionDelta - 1;
                @Pc(50) int localX = positionData & 0x3F;
                @Pc(56) int localZ = positionData >> 6 & 0x3F;
                @Pc(60) int locationPlane = positionData >> 12;
                @Pc(64) int shapeAndRotation = packet.g1();
                @Pc(68) int shape = shapeAndRotation >> 2;
                @Pc(72) int rotation = shapeAndRotation & 0x3;
                if (targetPlane == locationPlane && localZ >= regionOffsetX && localZ < regionOffsetX + 8 && regionOffsetZ <= localX && regionOffsetZ + 8 > localX) {
                    @Pc(103) LocType locType = LocTypeList.get(locationId);
                    @Pc(120) int worldX = transformLocalX(localX & 0x7, baseRotation, rotation, locType.length, locType.width, localZ & 0x7) + baseX;
                    @Pc(137) int worldZ = transformLocalZ(locType.width, baseRotation, locType.length, localZ & 0x7, rotation, localX & 0x7) + baseZ;
                    if (worldX > 0 && worldZ > 0 && worldX < 103 && worldZ < 103) {
                        @Pc(154) CollisionMap collisionMap = null;
                        if (!underWater) {
                            @Pc(159) int collisionPlane = plane;
                            if ((SceneGraph.renderFlags[1][worldX][worldZ] & 0x2) == 2) {
                                collisionPlane = plane - 1;
                            }
                            if (collisionPlane >= 0) {
                                collisionMap = collisionMaps[collisionPlane];
                            }
                        }
                        SceneGraph.addLoc(plane, !underWater, plane, underWater, collisionMap, locationId, shape, worldX, worldZ, rotation + baseRotation & 0x3);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!eb", name = "a", descriptor = "(IIIIIII)I")
    public static int transformLocalX(@OriginalArg(0) int localX, @OriginalArg(1) int baseRotation, @OriginalArg(2) int objectRotation, @OriginalArg(3) int objectLength, @OriginalArg(4) int objectWidth, @OriginalArg(6) int localZ) {
        if ((objectRotation & 0x1) == 1) {
            @Pc(10) int temp = objectWidth;
            objectWidth = objectLength;
            objectLength = temp;
        }
        @Pc(18) int totalRotation = baseRotation & 0x3;
        if (totalRotation == 0) {
            return localZ;
        } else if (totalRotation == 1) {
            return localX;
        } else if (totalRotation == 2) {
            return 7 + 1 - localZ - objectWidth;
        } else {
            return 7 + 1 - localX - objectLength;
        }
    }

    @OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(IIBIIII)I")
    public static int transformLocalZ(@OriginalArg(0) int objectWidth, @OriginalArg(1) int baseRotation, @OriginalArg(3) int objectLength, @OriginalArg(4) int localZ, @OriginalArg(5) int objectRotation, @OriginalArg(6) int localX) {
        if ((objectRotation & 0x1) == 1) {
            @Pc(9) int temp = objectWidth;
            objectWidth = objectLength;
            objectLength = temp;
        }
        @Pc(29) int local29 = baseRotation & 0x3;
        if (local29 == 0) {
            return localX;
        } else if (local29 == 1) {
            return 7 + 1 - localZ - objectWidth;
        } else if (local29 == 2) {
            return 1 + 7 - objectLength - localX;
        } else {
            return localZ;
        }
    }

    @OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(ZB)V")
    public static void loadDynamicTerrain(@OriginalArg(0) boolean underWater) {
        @Pc(11) byte planeCount;
        @Pc(13) byte[][] mapDataBuffers;
        if (GlRenderer.enabled && underWater) {
            planeCount = 1;
            mapDataBuffers = underWaterMapFilesBuffer;
        } else {
            mapDataBuffers = mapFilesBuffer;
            planeCount = 4;
        }
        for (@Pc(21) int plane = 0; plane < planeCount; plane++) {
            Client.audioLoop();
            for (@Pc(32) int regionX = 0; regionX < 13; regionX++) {
                for (@Pc(39) int regionZ = 0; regionZ < 13; regionZ++) {
                    @Pc(52) int packedTerrainData = Protocol.dynamicRegionData[plane][regionX][regionZ];
                    @Pc(54) boolean terrainLoaded = false;
                    if (packedTerrainData != -1) {
                        @Pc(65) int extractedPlane = packedTerrainData >> 24 & 0x3;
                        if (!underWater || extractedPlane == 0) {
                            @Pc(76) int worldZ = packedTerrainData >> 3 & 0x7FF;
                            @Pc(82) int rotation = packedTerrainData >> 1 & 0x3;
                            @Pc(88) int worldX = packedTerrainData >> 14 & 0x3FF;
                            @Pc(98) int regionId = (worldX / 8 << 8) + worldZ / 8;
                            for (@Pc(100) int regionIndex = 0; regionIndex < regionBitPacked.length; regionIndex++) {
                                if (regionBitPacked[regionIndex] == regionId && mapDataBuffers[regionIndex] != null) {
                                    SceneGraph.parseTerrainData(rotation, regionX * 8, plane, PathFinder.collisionMaps, regionZ * 8, mapDataBuffers[regionIndex], extractedPlane, (worldZ & 0x7) * 8, (worldX & 0x7) * 8, underWater);
                                    terrainLoaded = true;
                                    break;
                                }
                            }
                        }
                    }
                    if (!terrainLoaded) {
                        SceneGraph.initializeEmptyTerrain(plane, regionZ * 8, regionX * 8, 8, 8);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!gn", name = "a", descriptor = "(ZI)V")
    public static void loadTerrainData(@OriginalArg(0) boolean underWater) {
        @Pc(7) byte planeCount;
        @Pc(9) byte[][] mapDataBuffers;
        if (GlRenderer.enabled && underWater) {
            planeCount = 1;
            mapDataBuffers = underWaterMapFilesBuffer;
        } else {
            planeCount = 4;
            mapDataBuffers = mapFilesBuffer;
        }
        @Pc(18) int regionCount = mapDataBuffers.length;
        @Pc(20) int regionIndex;
        @Pc(38) int worldX;
        @Pc(49) int worldZ;
        @Pc(53) byte[] terrainData;
        for (regionIndex = 0; regionIndex < regionCount; regionIndex++) {
            worldX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.sceneBaseTileX;
            worldZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.sceneBaseTileZ;
            terrainData = mapDataBuffers[regionIndex];
            if (terrainData != null) {
                Client.audioLoop();
                SceneGraph.loadMapRegion(PathFinder.collisionMaps, underWater, SceneGraph.centralZoneX * 8 - 48, worldZ, worldX, (SceneGraph.centralZoneZ - 6) * 8, terrainData);
            }
        }
        for (regionIndex = 0; regionIndex < regionCount; regionIndex++) {
            worldX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.sceneBaseTileX;
            worldZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.sceneBaseTileZ;
            terrainData = mapDataBuffers[regionIndex];
            if (terrainData == null && SceneGraph.centralZoneZ < 800) {
                Client.audioLoop();
                for (@Pc(130) int plane = 0; plane < planeCount; plane++) {
                    SceneGraph.initializeEmptyTerrain(plane, worldZ, worldX, 64, 64);
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(IIIIZIZ)V")
    public static void initializeMapRegion(@OriginalArg(0) int plane, @OriginalArg(1) int zoneZ, @OriginalArg(2) int zoneX, @OriginalArg(3) int playerLocalX, @OriginalArg(4) boolean isWorldTransition, @OriginalArg(5) int playerLocalZ) {
        if (SceneGraph.centralZoneX == zoneX && zoneZ == SceneGraph.centralZoneZ && (SceneGraph.centralPlane == plane || SceneGraph.allLevelsAreVisible())) {
            return;
        }
        SceneGraph.centralZoneX = zoneX;
        SceneGraph.centralZoneZ = zoneZ;
        SceneGraph.centralPlane = plane;
        if (SceneGraph.allLevelsAreVisible()) {
            SceneGraph.centralPlane = 0;
        }
        if (isWorldTransition) {
            Client.processGameStatus(28);
        } else {
            Client.processGameStatus(25);
        }
        Fonts.drawTextOnScreen(true, LocalizedText.LOADING);
        @Pc(53) int oldOriginZ = Camera.sceneBaseTileZ;
        @Pc(55) int oldOriginX = Camera.sceneBaseTileX;
        Camera.sceneBaseTileZ = zoneZ * 8 - 48;
        Camera.sceneBaseTileX = (zoneX - 6) * 8;
        map = MapList.getContainingSource(SceneGraph.centralZoneX * 8, SceneGraph.centralZoneZ * 8);
        @Pc(81) int deltaZ = Camera.sceneBaseTileZ - oldOriginZ;
        @Pc(86) int deltaX = Camera.sceneBaseTileX - oldOriginX;
        mapElementList = null;
        @Pc(96) int npcIndex;
        @Pc(103) Npc npc;
        @Pc(109) int queueIndex;
        if (isWorldTransition) {
            NpcList.npcCount = 0;
            for (npcIndex = 0; npcIndex < 32768; npcIndex++) {
                npc = NpcList.npcs[npcIndex];
                if (npc != null) {
                    npc.xFine -= deltaX * 128;
                    npc.zFine -= deltaZ * 128;
                    if (npc.xFine >= 0 && npc.xFine <= 13184 && npc.zFine >= 0 && npc.zFine <= 13184) {
                        for (queueIndex = 0; queueIndex < 10; queueIndex++) {
                            npc.movementQueueX[queueIndex] -= deltaX;
                            npc.movementQueueZ[queueIndex] -= deltaZ;
                        }
                        NpcList.npcIds[NpcList.npcCount++] = npcIndex;
                    } else {
                        NpcList.npcs[npcIndex].setNpcType(null);
                        NpcList.npcs[npcIndex] = null;
                    }
                }
            }
        } else {
            for (npcIndex = 0; npcIndex < 32768; npcIndex++) {
                npc = NpcList.npcs[npcIndex];
                if (npc != null) {
                    for (queueIndex = 0; queueIndex < 10; queueIndex++) {
                        npc.movementQueueX[queueIndex] -= deltaX;
                        npc.movementQueueZ[queueIndex] -= deltaZ;
                    }
                    npc.xFine -= deltaX * 128;
                    npc.zFine -= deltaZ * 128;
                }
            }
        }
        for (npcIndex = 0; npcIndex < 2048; npcIndex++) {
            @Pc(265) Player player = PlayerList.players[npcIndex];
            if (player != null) {
                for (queueIndex = 0; queueIndex < 10; queueIndex++) {
                    player.movementQueueX[queueIndex] -= deltaX;
                    player.movementQueueZ[queueIndex] -= deltaZ;
                }
                player.xFine -= deltaX * 128;
                player.zFine -= deltaZ * 128;
            }
        }
        Player.currentLevel = plane;
        PlayerList.self.teleport(playerLocalZ, false, playerLocalX);
        @Pc(322) byte endTileX = 104;
        @Pc(324) byte startTileX = 0;
        @Pc(326) byte startTileZ = 0;
        @Pc(328) byte dirZ = 1;
        @Pc(330) byte endTileZ = 104;
        @Pc(332) byte dirX = 1;
        if (deltaZ < 0) {
            dirZ = -1;
            endTileZ = -1;
            startTileZ = 103;
        }
        if (deltaX < 0) {
            dirX = -1;
            startTileX = 103;
            endTileX = -1;
        }
        for (@Pc(358) int tileX = startTileX; tileX != endTileX; tileX += dirX) {
            for (@Pc(367) int tileZ = startTileZ; tileZ != endTileZ; tileZ += dirZ) {
                @Pc(378) int sourceX = deltaX + tileX;
                @Pc(382) int sourceZ = tileZ + deltaZ;
                for (@Pc(384) int level = 0; level < 4; level++) {
                    if (sourceX >= 0 && sourceZ >= 0 && sourceX < 104 && sourceZ < 104) {
                        SceneGraph.objStacks[level][tileX][tileZ] = SceneGraph.objStacks[level][sourceX][sourceZ];
                    } else {
                        SceneGraph.objStacks[level][tileX][tileZ] = null;
                    }
                }
            }
        }
        for (@Pc(451) ChangeLocRequest locRequest = (ChangeLocRequest) ChangeLocRequest.queue.head(); locRequest != null; locRequest = (ChangeLocRequest) ChangeLocRequest.queue.next()) {
            locRequest.z -= deltaZ;
            locRequest.x -= deltaX;
            if (locRequest.x < 0 || locRequest.z < 0 || locRequest.x >= 104 || locRequest.z >= 104) {
                locRequest.unlink();
            }
        }
        if (isWorldTransition) {
            Camera.renderX -= deltaX * 128;
            Camera.renderZ -= deltaZ * 128;
            Camera.targetTileX -= deltaZ;
            Camera.lookAtTileZ -= deltaX;
            Camera.lookAtTileX -= deltaZ;
            Camera.targetTileZ -= deltaX;
        } else {
            Camera.cameraType = 1;
        }
        SoundPlayer.size = 0;
        if (LoginManager.flagSceneTileX != 0) {
            LoginManager.flagSceneTileZ -= deltaZ;
            LoginManager.flagSceneTileX -= deltaX;
        }
        if (GlRenderer.enabled && isWorldTransition && (Math.abs(deltaX) > 104 || Math.abs(deltaZ) > 104)) {
            FogManager.setInstantFade();
        }
        LightingManager.anInt2875 = -1;
        SceneGraph.spotanims.clear();
        SceneGraph.projectiles.clear();
    }

    @OriginalMember(owner = "client!ca", name = "a", descriptor = "(ZI)V")
    public static void loadLocsToScene(@OriginalArg(0) boolean underWater) {
        @Pc(13) int regionCount = mapFilesBuffer.length;
        @Pc(19) byte[][] locationDataBuffers;
        if (GlRenderer.enabled && underWater) {
            locationDataBuffers = underWaterLocationsMapFilesBuffer;
        } else {
            locationDataBuffers = locationMapFilesBuffer;
        }
        for (@Pc(25) int regionIndex = 0; regionIndex < regionCount; regionIndex++) {
            @Pc(32) byte[] locationData = locationDataBuffers[regionIndex];
            if (locationData != null) {
                @Pc(45) int worldX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.sceneBaseTileX;
                @Pc(56) int worldZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.sceneBaseTileZ;
                Client.audioLoop();
                SceneGraph.readLocs(worldX, underWater, locationData, worldZ, PathFinder.collisionMaps);
            }
        }
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(BII[B)Z")
    public static boolean loadLocs(@OriginalArg(1) int baseX, @OriginalArg(2) int baseZ, @OriginalArg(3) byte[] locationData) {
        @Pc(15) boolean allLocsReady = true;
        @Pc(17) int locationId = -1;
        @Pc(22) Packet packet = new Packet(locationData);
        label70: while (true) {
            @Pc(26) int idDelta = packet.gVarSmart();
            if (idDelta == 0) {
                return allLocsReady;
            }
            @Pc(33) int positionData = 0;
            locationId += idDelta;
            @Pc(39) boolean skipLocationData = false;
            while (true) {
                @Pc(78) int shape;
                @Pc(95) LocType locType;
                do {
                    @Pc(72) int worldZ;
                    @Pc(68) int worldX;
                    do {
                        do {
                            do {
                                do {
                                    @Pc(45) int positionDelta;
                                    while (skipLocationData) {
                                        positionDelta = packet.gSmart1or2();
                                        if (positionDelta == 0) {
                                            continue label70;
                                        }
                                        packet.g1();
                                    }
                                    positionDelta = packet.gSmart1or2();
                                    if (positionDelta == 0) {
                                        continue label70;
                                    }
                                    positionData += positionDelta - 1;
                                    @Pc(58) int localX = positionData & 0x3F;
                                    @Pc(64) int localZ = positionData >> 6 & 0x3F;
                                    worldX = baseZ + localX;
                                    worldZ = baseX + localZ;
                                    shape = packet.g1() >> 2;
                                } while (worldZ <= 0);
                            } while (worldX <= 0);
                        } while (worldZ >= 103);
                    } while (worldX >= 103);
                    locType = LocTypeList.get(locationId);
                } while (shape == 22 && !Preferences.showGroundDecorations && locType.active == 0 && locType.blockwalk != 1 && !locType.forcedecor);
                skipLocationData = true;
                if (!locType.isReady()) {
                    allLocsReady = false;
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
                chunkZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.sceneBaseTileZ;
                chunkX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.sceneBaseTileX;
                if (SceneGraph.dynamicMapRegion) {
                    chunkZ = 10;
                    chunkX = 10;
                }
                fileExists &= loadLocs(chunkX, chunkZ, locationData);
            }
            if (GlRenderer.enabled) {
                locationData = underWaterLocationsMapFilesBuffer[regionIndex];
                if (locationData != null) {
                    chunkX = (regionBitPacked[regionIndex] >> 8) * 64 - Camera.sceneBaseTileX;
                    chunkZ = (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.sceneBaseTileZ;
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
        SceneGraph.buildTiles(PathFinder.collisionMaps, false);
        if (GlRenderer.enabled) {
            LightingManager.method2395();
        }
        ClientProt.ping(true);
        index = SceneGraph.firstVisibleLevel;
        if (index > Player.currentLevel) {
            index = Player.currentLevel;
        }
        if (index < Player.currentLevel - 1) {
            // TODO why is this here?
        }
        if (SceneGraph.allLevelsAreVisible()) {
            SceneGraph.ensureTilesExist(0);
        } else {
            SceneGraph.ensureTilesExist(SceneGraph.firstVisibleLevel);
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
            SceneGraph.buildTiles(PathFinder.collisionMaps, true);
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
                Protocol.sortObjStacks(chunkX, chunkZ);
            }
        }
        ClientScriptRunner.method2218();
        Client.audioLoop();
        ChangeLocRequest.refreshRequests();
        Client.unload();
        aBoolean252 = false;
        if (GameShell.frame != null && Protocol.gameServerSocket != null && Client.gameState == 25) {
            Protocol.outboundBuffer.pIsaac1(DETECT_MODIFIED_CLIENT);
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
                Protocol.outboundBuffer.pIsaac1(ClientProt.MAP_BUILD_COMPLETE);
            }
        }
        WorldMap.method2720();
        Client.audioLoop();
        GameShell.resetTimer();
    }

    @OriginalMember(owner = "runetek4.client!mh", name = "h", descriptor = "(B)V")
    public static void decodeNpcFiles() {
        @Pc(10) int regionCount = npcSpawnsFilesBuffer.length;
        for (@Pc(16) int regionIndex = 0; regionIndex < regionCount; regionIndex++) {
            if (npcSpawnsFilesBuffer[regionIndex] != null) {
                @Pc(25) int lookupIndex = -1;
                for (@Pc(27) int searchIndex = 0; searchIndex < anInt3811; searchIndex++) {
                    if (regionIndexLookup[searchIndex] == regionBitPacked[regionIndex]) {
                        lookupIndex = searchIndex;
                        break;
                    }
                }
                if (lookupIndex == -1) {
                    regionIndexLookup[anInt3811] = regionBitPacked[regionIndex];
                    lookupIndex = anInt3811++;
                }
                @Pc(67) int npcIndex = 0;
                @Pc(74) Packet packet = new Packet(npcSpawnsFilesBuffer[regionIndex]);
                while (packet.offset < npcSpawnsFilesBuffer[regionIndex].length && npcIndex < 511) {
                    @Pc(97) int globalNpcId = npcIndex++ << 6 | lookupIndex;
                    @Pc(103) int packedSpawnData = packet.g2();
                    @Pc(107) int plane = packedSpawnData >> 14;
                    @Pc(113) int localX = packedSpawnData >> 7 & 0x3F;
                    @Pc(125) int worldX = localX + (regionBitPacked[regionIndex] >> 8) * 64 - Camera.sceneBaseTileX;
                    @Pc(129) int localZ = packedSpawnData & 0x3F;
                    @Pc(142) int worldZ = localZ + (regionBitPacked[regionIndex] & 0xFF) * 64 - Camera.sceneBaseTileZ;
                    @Pc(148) NpcType npcType = NpcTypeList.get(packet.g2());
                    if (NpcList.npcs[globalNpcId] == null && (npcType.walkflags & 0x1) > 0 && plane == SceneGraph.centralPlane && worldX >= 0 && npcType.size + worldX < 104 && worldZ >= 0 && worldZ + npcType.size < 104) {
                        NpcList.npcs[globalNpcId] = new Npc();
                        @Pc(198) Npc npc = NpcList.npcs[globalNpcId];
                        NpcList.npcIds[NpcList.npcCount++] = globalNpcId;
                        npc.lastSeenLoop = Client.loop;
                        npc.setNpcType(npcType);
                        npc.setSize(npc.type.size);
                        npc.dstYaw = npc.orientation = PathingEntity.ANGLES[npc.type.respawndir];
                        npc.anInt3376 = npc.type.turnspeed;
                        if (npc.anInt3376 == 0) {
                            npc.orientation = 0;
                        }
                        npc.anInt3365 = npc.type.nas;
                        npc.teleport(npc.getSize(), worldX, worldZ, true);
                    }
                }
            }
        }
    }
}
