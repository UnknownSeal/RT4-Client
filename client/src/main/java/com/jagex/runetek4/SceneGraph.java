package com.jagex.runetek4;

import com.jagex.runetek4.config.FluType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.CollisionMap;
import com.jagex.runetek4.dash3d.entity.LocEntity;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.dash3d.entity.ObjStackEntity;
import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import com.jagex.runetek4.game.config.flotype.FloorOverlayTypeList;
import com.jagex.runetek4.game.config.lighttype.LightType;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.scene.Scene;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SceneGraph {
    @OriginalMember(owner = "client!bb", name = "g", descriptor = "[[[B")
    public static final byte[][][] renderFlags = new byte[4][104][104];
    @OriginalMember(owner = "runetek4.client!rj", name = "U", descriptor = "Lclient!ih;")
    public static final LinkedList projectiles = new LinkedList();
    @OriginalMember(owner = "runetek4.client!hk", name = "W", descriptor = "Lclient!ih;")
    public static final LinkedList spotanims = new LinkedList();
    @OriginalMember(owner = "runetek4.client!mi", name = "Y", descriptor = "[[[Lclient!ih;")
    public static final LinkedList[][][] objStacks = new LinkedList[4][104][104];
    @OriginalMember(owner = "runetek4.client!gj", name = "m", descriptor = "[[[I")
    public static int[][][] tileHeights;
    @OriginalMember(owner = "runetek4.client!sm", name = "e", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray13;
    @OriginalMember(owner = "runetek4.client!id", name = "i", descriptor = "[[[I")
    public static int[][][] surfaceTileHeights;
    @OriginalMember(owner = "client!gn", name = "d", descriptor = "Z")
    public static boolean aBoolean130 = false;
    @OriginalMember(owner = "runetek4.client!sj", name = "u", descriptor = "Z")
    public static boolean dynamicMapRegion = false;
    @OriginalMember(owner = "client!gf", name = "R", descriptor = "I")
    public static int centralZoneX;
    @OriginalMember(owner = "client!eb", name = "u", descriptor = "I")
    public static int centralZoneZ;
    @OriginalMember(owner = "client!dc", name = "ab", descriptor = "I")
    public static int centralPlane = 0;
    @OriginalMember(owner = "runetek4.client!lg", name = "k", descriptor = "I")
    public static int firstvisibleLevel = 99;
    @OriginalMember(owner = "runetek4.client!kc", name = "o", descriptor = "[[[Lclient!bj;")
    public static Tile[][][] tiles;
    @OriginalMember(owner = "client!runetek4.client", name = "kb", descriptor = "[[Lclient!hg;")
    public static GlTile[][] aGlTileArrayArray1;
    @OriginalMember(owner = "runetek4.client!ja", name = "q", descriptor = "I")
    public static int currentChunkX;
    @OriginalMember(owner = "runetek4.client!og", name = "b", descriptor = "I")
    public static int currentChunkZ;

    @OriginalMember(owner = "client!fc", name = "a", descriptor = "()V")
    public static void clear() {
        @Pc(3) int local3;
        @Pc(9) int local9;
        @Pc(14) int local14;
        if (Static197.aClass3_Sub5ArrayArrayArray2 != null) {
            for (local3 = 0; local3 < Static197.aClass3_Sub5ArrayArrayArray2.length; local3++) {
                for (local9 = 0; local9 < Static152.anInt3594; local9++) {
                    for (local14 = 0; local14 < Static99.anInt2550; local14++) {
                        Static197.aClass3_Sub5ArrayArrayArray2[local3][local9][local14] = null;
                    }
                }
            }
        }
        aGlTileArrayArray1 = null;
        if (Static276.aClass3_Sub5ArrayArrayArray3 != null) {
            for (local3 = 0; local3 < Static276.aClass3_Sub5ArrayArrayArray3.length; local3++) {
                for (local9 = 0; local9 < Static152.anInt3594; local9++) {
                    for (local14 = 0; local14 < Static99.anInt2550; local14++) {
                        Static276.aClass3_Sub5ArrayArrayArray3[local3][local9][local14] = null;
                    }
                }
            }
        }
        Static195.aClass3_Sub14ArrayArray3 = null;
        Static28.anInt917 = 0;
        if (Static91.aClass120Array1 != null) {
            for (local3 = 0; local3 < Static28.anInt917; local3++) {
                Static91.aClass120Array1[local3] = null;
            }
        }
        if (Static243.aClass31Array3 != null) {
            for (local3 = 0; local3 < Static22.anInt726; local3++) {
                Static243.aClass31Array3[local3] = null;
            }
            Static22.anInt726 = 0;
        }
        if (Static25.aClass31Array2 != null) {
            for (local3 = 0; local3 < Static25.aClass31Array2.length; local3++) {
                Static25.aClass31Array2[local3] = null;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(III)Lclient!jh;")
    public static Wall getWall(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        return local7 == null ? null : local7.wall;
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "b", descriptor = "(III)Lclient!ec;")
    public static Scenery getScenery(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        if (local7 == null) {
            return null;
        }
        for (@Pc(13) int local13 = 0; local13 < local7.entityCount; local13++) {
            @Pc(22) Scenery local22 = local7.sceneries[local13];
            if ((local22.hash >> 29 & 0x3L) == 2L && local22.anInt1701 == arg1 && local22.anInt1696 == arg2) {
                return local22;
            }
        }
        return null;
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(III)Lclient!bm;")
    public static GroundDecor getGroundDecor(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        return local7 == null || local7.groundDecor == null ? null : local7.groundDecor;
    }

    @OriginalMember(owner = "runetek4.client!ql", name = "a", descriptor = "(IIII)I")
    public static int getTileHeight(@OriginalArg(0) int arg0, @OriginalArg(2) int sceneX, @OriginalArg(3) int sceneZ) {
        if (tileHeights == null) {
            return 0;
        }
        @Pc(12) int tileX = sceneX >> 7;
        @Pc(16) int tileZ = sceneZ >> 7;
        if (tileX < 0 || tileZ < 0 || tileX > 103 || tileZ > 103) {
            return 0;
        }
        @Pc(36) int tileLocalX = sceneX & 0x7F;
        @Pc(40) int tileLocalZ = sceneZ & 0x7F;
        @Pc(42) int realLevel = arg0;
        if (arg0 < 3 && (renderFlags[1][tileX][tileZ] & 0x2) == 2) {
            realLevel = arg0 + 1;
        }
        @Pc(91) int y11 = tileLocalX * tileHeights[realLevel][tileX + 1][tileZ + 1] + tileHeights[realLevel][tileX][tileZ + 1] * (128 - tileLocalX) >> 7;
        @Pc(118) int y00 = tileLocalX * tileHeights[realLevel][tileX + 1][tileZ] + (128 - tileLocalX) * tileHeights[realLevel][tileX][tileZ] >> 7;
        return tileLocalZ * y11 + (128 - tileLocalZ) * y00 >> 7;
    }

    @OriginalMember(owner = "runetek4.client!ge", name = "a", descriptor = "(IIIIIIII)V")
    public static void method1698(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
        if (arg1 < 1 || arg4 < 1 || arg1 > 102 || arg4 > 102) {
            return;
        }
        @Pc(39) int local39;
        if (!allLevelsvisible() && (renderFlags[0][arg1][arg4] & 0x2) == 0) {
            local39 = arg2;
            if ((renderFlags[arg2][arg1][arg4] & 0x8) != 0) {
                local39 = 0;
            }
            if (local39 != centralPlane) {
                return;
            }
        }
        local39 = arg2;
        if (arg2 < 3 && (renderFlags[1][arg1][arg4] & 0x2) == 2) {
            local39 = arg2 + 1;
        }
        Static43.method1144(arg4, arg1, arg2, arg6, local39, PathFinder.collisionMaps[arg2]);
        if (arg0 >= 0) {
            @Pc(92) boolean local92 = Preferences.showGroundDecorations;
            Preferences.showGroundDecorations = true;
            addLoc(local39, false, arg2, false, PathFinder.collisionMaps[arg2], arg0, arg5, arg1, arg4, arg3);
            Preferences.showGroundDecorations = local92;
        }
    }

    @OriginalMember(owner = "runetek4.client!vj", name = "a", descriptor = "(III)J")
    public static long getWallKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        return local7 == null || local7.wall == null ? 0L : local7.wall.aLong107;
    }

    @OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "(III)J")
    public static long getWallDecorKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        return local7 == null || local7.wallDecor == null ? 0L : local7.wallDecor.key;
    }

    @OriginalMember(owner = "runetek4.client!cl", name = "a", descriptor = "(III)J")
    public static long getSceneryKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        if (local7 == null) {
            return 0L;
        }
        for (@Pc(13) int local13 = 0; local13 < local7.entityCount; local13++) {
            @Pc(22) Scenery local22 = local7.sceneries[local13];
            if ((local22.hash >> 29 & 0x3L) == 2L && local22.anInt1701 == arg1 && local22.anInt1696 == arg2) {
                return local22.hash;
            }
        }
        return 0L;
    }

    @OriginalMember(owner = "client!bj", name = "a", descriptor = "(III)J")
    public static long getGroundDecorKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        return local7 == null || local7.groundDecor == null ? 0L : local7.groundDecor.key;
    }

    @OriginalMember(owner = "client!sd", name = "c", descriptor = "(II)V")
    public static void method3884(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
        @Pc(7) Tile local7 = tiles[0][arg0][arg1];
        for (@Pc(9) int local9 = 0; local9 < 3; local9++) {
            @Pc(30) Tile local30 = tiles[local9][arg0][arg1] = tiles[local9 + 1][arg0][arg1];
            if (local30 != null) {
                local30.anInt672--;
                for (@Pc(40) int local40 = 0; local40 < local30.entityCount; local40++) {
                    @Pc(49) Scenery local49 = local30.sceneries[local40];
                    if ((local49.hash >> 29 & 0x3L) == 2L && local49.anInt1701 == arg0 && local49.anInt1696 == arg1) {
                        local49.anInt1709--;
                    }
                }
            }
        }
        if (tiles[0][arg0][arg1] == null) {
            tiles[0][arg0][arg1] = new Tile(0, arg0, arg1);
        }
        tiles[0][arg0][arg1].aClass3_Sub5_1 = local7;
        tiles[3][arg0][arg1] = null;
    }

    @OriginalMember(owner = "runetek4.client!jk", name = "a", descriptor = "(IZ[BII[Lclient!mj;)V")
    public static void readLocs(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) byte[] arg2, @OriginalArg(3) int arg3, @OriginalArg(5) CollisionMap[] arg4) {
        @Pc(10) Packet local10 = new Packet(arg2);
        @Pc(12) int local12 = -1;
        while (true) {
            @Pc(16) int local16 = local10.gVarSmart();
            if (local16 == 0) {
                return;
            }
            local12 += local16;
            @Pc(27) int local27 = 0;
            while (true) {
                @Pc(31) int local31 = local10.gSmart1or2();
                if (local31 == 0) {
                    break;
                }
                local27 += local31 - 1;
                @Pc(46) int local46 = local27 & 0x3F;
                @Pc(50) int local50 = local27 >> 12;
                @Pc(56) int local56 = local27 >> 6 & 0x3F;
                @Pc(60) int local60 = local10.g1();
                @Pc(64) int local64 = local60 >> 2;
                @Pc(68) int local68 = local60 & 0x3;
                @Pc(72) int local72 = arg0 + local56;
                @Pc(76) int local76 = local46 + arg3;
                if (local72 > 0 && local76 > 0 && local72 < 103 && local76 < 103) {
                    @Pc(90) CollisionMap local90 = null;
                    if (!arg1) {
                        @Pc(95) int local95 = local50;
                        if ((renderFlags[1][local72][local76] & 0x2) == 2) {
                            local95 = local50 - 1;
                        }
                        if (local95 >= 0) {
                            local90 = arg4[local95];
                        }
                    }
                    addLoc(local50, !arg1, local50, arg1, local90, local12, local64, local72, local76, local68);
                }
            }
        }
    }

    @OriginalMember(owner = "client!cd", name = "a", descriptor = "(IIIIZ)V")
    public static void init(@OriginalArg(3) int arg0, @OriginalArg(4) boolean arg1) {
        Static152.anInt3594 = 104;
        Static99.anInt2550 = 104;
        Static277.anInt5855 = arg0;
        Static197.aClass3_Sub5ArrayArrayArray2 = new Tile[4][Static152.anInt3594][Static99.anInt2550];
        surfaceTileHeights = new int[4][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
        if (GlRenderer.enabled) {
            aGlTileArrayArray1 = new GlTile[4][];
        }
        if (arg1) {
            Static276.aClass3_Sub5ArrayArrayArray3 = new Tile[1][Static152.anInt3594][Static99.anInt2550];
            Static62.anIntArrayArray11 = new int[Static152.anInt3594][Static99.anInt2550];
            Static80.anIntArrayArrayArray19 = new int[1][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
            if (GlRenderer.enabled) {
                Static195.aClass3_Sub14ArrayArray3 = new GlTile[1][];
            }
        } else {
            Static276.aClass3_Sub5ArrayArrayArray3 = null;
            Static62.anIntArrayArray11 = null;
            Static80.anIntArrayArrayArray19 = null;
            Static195.aClass3_Sub14ArrayArray3 = null;
        }
        setUnderwater(false);
        Static91.aClass120Array1 = new Class120[500];
        Static28.anInt917 = 0;
        Static247.aClass120Array2 = new Class120[500];
        Static215.anInt4870 = 0;
        Static140.anIntArrayArrayArray12 = new int[4][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
        Static243.aClass31Array3 = new Scenery[5000];
        Static22.anInt726 = 0;
        Static25.aClass31Array2 = new Scenery[100];
        Static48.aBooleanArrayArray1 = new boolean[Static277.anInt5855 + Static277.anInt5855 + 1][Static277.anInt5855 + Static277.anInt5855 + 1];
        Static89.aBooleanArrayArray3 = new boolean[Static277.anInt5855 + Static277.anInt5855 + 2][Static277.anInt5855 + Static277.anInt5855 + 2];
        aByteArrayArrayArray13 = new byte[4][Static152.anInt3594][Static99.anInt2550];
    }

    @OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "(ZI)V")
	public static void load(@OriginalArg(0) boolean arg0) {
		BZip2State.anIntArray376 = new int[104];
		Static139.anIntArray325 = new int[104];
		firstvisibleLevel = 99;
		Static251.blendChroma = new int[104];
		@Pc(14) byte local14;
		if (arg0) {
			local14 = 1;
		} else {
			local14 = 4;
		}
		Static163.aByteArrayArrayArray11 = new byte[local14][104][104];
		Static128.blendMagnitude = new int[104];
		Static60.anIntArrayArrayArray6 = new int[local14][105][105];
		Static118.levelShademap = new byte[local14][105][105];
		Static240.aByteArrayArrayArray14 = new byte[local14][104][104];
		Static279.anIntArray568 = new int[104];
		Static4.aByteArrayArrayArray1 = new byte[local14][104][104];
		Static253.levelTileUnderlayIds = new byte[local14][104][104];
	}

    @OriginalMember(owner = "client!di", name = "a", descriptor = "([Lclient!mj;ZI)V")
    public static void method1169(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) boolean arg1) {
        @Pc(10) int local10;
        @Pc(15) int local15;
        if (!arg1) {
            for (local10 = 0; local10 < 4; local10++) {
                for (local15 = 0; local15 < 104; local15++) {
                    for (@Pc(22) int local22 = 0; local22 < 104; local22++) {
                        if ((renderFlags[local10][local15][local22] & 0x1) == 1) {
                            @Pc(43) int local43 = local10;
                            if ((renderFlags[1][local15][local22] & 0x2) == 2) {
                                local43 = local10 - 1;
                            }
                            if (local43 >= 0) {
                                arg0[local43].flagTile(local22, local15);
                            }
                        }
                    }
                }
            }
            Static183.anInt4272 += (int) (Math.random() * 5.0D) - 2;
            if (Static183.anInt4272 < -16) {
                Static183.anInt4272 = -16;
            }
            if (Static183.anInt4272 > 16) {
                Static183.anInt4272 = 16;
            }
            Static86.anInt2293 += (int) (Math.random() * 5.0D) - 2;
            if (Static86.anInt2293 < -8) {
                Static86.anInt2293 = -8;
            }
            if (Static86.anInt2293 > 8) {
                Static86.anInt2293 = 8;
            }
        }
        @Pc(128) byte local128;
        if (arg1) {
            local128 = 1;
        } else {
            local128 = 4;
        }
        local10 = Static86.anInt2293 >> 2 << 10;
        @Pc(142) int[][] local142 = new int[104][104];
        @Pc(146) int[][] levelLightMap = new int[104][104];
        local15 = Static183.anInt4272 >> 1;
        @Pc(152) int level;
        @Pc(168) int z0;
        @Pc(173) int x0;
        @Pc(178) int z;
        @Pc(194) int underlayId;
        @Pc(200) int local200;
        @Pc(202) int dx;
        @Pc(209) int dz;
        @Pc(349) int len;
        @Pc(234) int normalX;
        @Pc(254) int normalY;
        @Pc(267) int normalZ;
        for (level = 0; level < local128; level++) {
            @Pc(159) byte[][] local159 = Static118.levelShademap[level];
            @Pc(273) int local273;
            @Pc(326) int local326;
            @Pc(332) int local332;
            @Pc(322) int local322;
            if (!GlRenderer.enabled) {
                z0 = (int) Math.sqrt(5100.0D);
                x0 = z0 * 768 >> 8;
                for (z = 1; z < 103; z++) {
                    for (underlayId = 1; underlayId < 103; underlayId++) {
                        dz = tileHeights[level][underlayId][z + 1] - tileHeights[level][underlayId][z - 1];
                        dx = tileHeights[level][underlayId + 1][z] - tileHeights[level][underlayId - 1][z];
                        len = (int) Math.sqrt((double) (dx * dx + dz * dz + 65536));
                        normalZ = (dz << 8) / len;
                        normalY = -65536 / len;
                        normalX = (dx << 8) / len;
                        local273 = (local159[underlayId][z] >> 1) + (local159[underlayId][z - 1] >> 2) + (local159[underlayId - -1][z] >> 3) + (local159[underlayId - 1][z] >> 2) + (local159[underlayId][z + 1] >> 3);
                        local200 = (normalZ * -50 + normalX * -50 + normalY * -10) / x0 + 74;
                        levelLightMap[underlayId][z] = local200 - local273;
                    }
                }
            } else if (Preferences.highDetailLighting) {
                for (z0 = 1; z0 < 103; z0++) {
                    for (x0 = 1; x0 < 103; x0++) {
                        underlayId = (local159[x0 + 1][z0] >> 3) + (local159[x0 - 1][z0] >> 2) + (local159[x0][z0 + -1] >> 2) + (local159[x0][z0 + 1] >> 3) + (local159[x0][z0] >> 1);
                        levelLightMap[x0][z0] = 74 - underlayId;
                    }
                }
            } else {
                z0 = (int) FogManager.light0Position[0];
                x0 = (int) FogManager.light0Position[1];
                z = (int) FogManager.light0Position[2];
                underlayId = (int) Math.sqrt((double) (x0 * x0 + z0 * z0 + z * z));
                local200 = underlayId * 1024 >> 8;
                for (dx = 1; dx < 103; dx++) {
                    for (dz = 1; dz < 103; dz++) {
                        normalX = tileHeights[level][dz + 1][dx] - tileHeights[level][dz - 1][dx];
                        normalY = tileHeights[level][dz][dx + 1] - tileHeights[level][dz][dx - 1];
                        normalZ = (int) Math.sqrt((double) (normalX * normalX + normalY * normalY + 65536));
                        local273 = (normalX << 8) / normalZ;
                        local322 = (local159[dz][dx + 1] >> 3) + (local159[dz][dx - 1] >> 2) + (local159[dz - 1][dx] >> 2) + (local159[dz + 1][dx] >> 3) + (local159[dz][dx] >> 1);
                        local326 = -65536 / normalZ;
                        local332 = (normalY << 8) / normalZ;
                        len = (z * local332 + z0 * local273 + local326 * x0) / local200 + 96;
                        levelLightMap[dz][dx] = len - (int) ((float) local322 * 1.7F);
                    }
                }
            }
            for (z0 = 0; z0 < 104; z0++) {
                BZip2State.anIntArray376[z0] = 0;
                Static139.anIntArray325[z0] = 0;
                Static279.anIntArray568[z0] = 0;
                Static251.blendChroma[z0] = 0;
                Static128.blendMagnitude[z0] = 0;
            }
            for (z0 = -5; z0 < 104; z0++) {
                for (x0 = 0; x0 < 104; x0++) {
                    z = z0 + 5;
                    @Pc(729) int debugMag;
                    if (z < 104) {
                        underlayId = Static253.levelTileUnderlayIds[level][z][x0] & 0xFF;
                        if (underlayId > 0) {
                            @Pc(693) FluType flu = FloorUnderlayTypeList.get(underlayId - 1);
                            BZip2State.anIntArray376[x0] += flu.weightedHue;
                            Static139.anIntArray325[x0] += flu.saturation;
                            Static279.anIntArray568[x0] += flu.lightness;
                            Static251.blendChroma[x0] += flu.chroma;
                            debugMag = Static128.blendMagnitude[x0]++;
                        }
                    }
                    underlayId = z0 - 5;
                    if (underlayId >= 0) {
                        local200 = Static253.levelTileUnderlayIds[level][underlayId][x0] & 0xFF;
                        if (local200 > 0) {
                            @Pc(758) FluType local758 = FloorUnderlayTypeList.get(local200 - 1);
                            BZip2State.anIntArray376[x0] -= local758.weightedHue;
                            Static139.anIntArray325[x0] -= local758.saturation;
                            Static279.anIntArray568[x0] -= local758.lightness;
                            Static251.blendChroma[x0] -= local758.chroma;
                            debugMag = Static128.blendMagnitude[x0]--;
                        }
                    }
                }
                if (z0 >= 0) {
                    x0 = 0;
                    underlayId = 0;
                    z = 0;
                    local200 = 0;
                    dx = 0;
                    for (dz = -5; dz < 104; dz++) {
                        len = dz + 5;
                        if (len < 104) {
                            z += Static139.anIntArray325[len];
                            dx += Static128.blendMagnitude[len];
                            x0 += BZip2State.anIntArray376[len];
                            local200 += Static251.blendChroma[len];
                            underlayId += Static279.anIntArray568[len];
                        }
                        normalX = dz - 5;
                        if (normalX >= 0) {
                            z -= Static139.anIntArray325[normalX];
                            local200 -= Static251.blendChroma[normalX];
                            x0 -= BZip2State.anIntArray376[normalX];
                            dx -= Static128.blendMagnitude[normalX];
                            underlayId -= Static279.anIntArray568[normalX];
                        }
                        if (dz >= 0 && dx > 0) {
                            local142[z0][dz] = ColorUtils.method1309(underlayId / dx, z / dx, x0 * 256 / local200);
                        }
                    }
                }
            }
            for (z0 = 1; z0 < 103; z0++) {
                label771: for (x0 = 1; x0 < 103; x0++) {
                    if (arg1 || allLevelsvisible() || (renderFlags[0][z0][x0] & 0x2) != 0 || (renderFlags[level][z0][x0] & 0x10) == 0 && Static4.getRenderLevel(x0, z0, level) == centralPlane) {
                        if (firstvisibleLevel > level) {
                            firstvisibleLevel = level;
                        }
                        z = Static253.levelTileUnderlayIds[level][z0][x0] & 0xFF;
                        underlayId = Static240.aByteArrayArrayArray14[level][z0][x0] & 0xFF;
                        if (z > 0 || underlayId > 0) {
                            dx = tileHeights[level][z0 + 1][x0];
                            local200 = tileHeights[level][z0][x0];
                            len = tileHeights[level][z0][x0 + 1];
                            dz = tileHeights[level][z0 + 1][x0 + 1];
                            if (level > 0) {
                                @Pc(1067) boolean local1067 = true;
                                if (z == 0 && Static163.aByteArrayArrayArray11[level][z0][x0] != 0) {
                                    local1067 = false;
                                }
                                if (underlayId > 0 && !FloorOverlayTypeList.method4395(underlayId - 1).occlude) {
                                    local1067 = false;
                                }
                                if (local1067 && local200 == dx && local200 == dz && len == local200) {
                                    Static60.anIntArrayArrayArray6[level][z0][x0] |= 0x4;
                                }
                            }
                            if (z <= 0) {
                                normalX = -1;
                                normalY = 0;
                            } else {
                                normalX = local142[z0][x0];
                                normalZ = (normalX & 0x7F) + local15;
                                if (normalZ < 0) {
                                    normalZ = 0;
                                } else if (normalZ > 127) {
                                    normalZ = 127;
                                }
                                local273 = (normalX & 0x380) + (normalX + local10 & 0xFC00) + normalZ;
                                normalY = Rasterizer.palette[ColorUtils.multiplyLightnessSafe(96, local273)];
                            }
                            normalZ = levelLightMap[z0][x0];
                            local332 = levelLightMap[z0][x0 + 1];
                            local273 = levelLightMap[z0 + 1][x0];
                            local326 = levelLightMap[z0 + 1][x0 + 1];
                            if (underlayId == 0) {
                                Static176.method3305(level, z0, x0, 0, 0, -1, local200, dx, dz, len, ColorUtils.multiplyLightnessSafe(normalZ, normalX), ColorUtils.multiplyLightnessSafe(local273, normalX), ColorUtils.multiplyLightnessSafe(local326, normalX), ColorUtils.multiplyLightnessSafe(local332, normalX), 0, 0, 0, 0, normalY, 0);
                                if (GlRenderer.enabled && level > 0 && normalX != -1 && FloorUnderlayTypeList.get(z - 1).blockShadow) {
                                    Static242.method4197(0, 0, true, false, z0, x0, local200 - tileHeights[0][z0][x0], -tileHeights[0][z0 + 1][x0] + dx, dz - tileHeights[0][z0 + 1][x0 + 1], len - tileHeights[0][z0][x0 + 1]);
                                }
                                if (GlRenderer.enabled && !arg1 && Static62.anIntArrayArray11 != null && level == 0) {
                                    for (local322 = z0 - 1; local322 <= z0 + 1; local322++) {
                                        for (@Pc(1794) int local1794 = x0 - 1; local1794 <= x0 + 1; local1794++) {
                                            if ((local322 != z0 || x0 != local1794) && local322 >= 0 && local322 < 104 && local1794 >= 0 && local1794 < 104) {
                                                @Pc(1834) int local1834 = Static240.aByteArrayArrayArray14[level][local322][local1794] & 0xFF;
                                                if (local1834 != 0) {
                                                    @Pc(1842) FloorOverlayType local1842 = FloorOverlayTypeList.method4395(local1834 - 1);
                                                    if (local1842.material != -1 && Rasterizer.textureProvider.getMaterialType(local1842.material) == 4) {
                                                        Static62.anIntArrayArray11[z0][x0] = local1842.waterColor + (local1842.waterOpaity << 24);
                                                        continue label771;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                local322 = Static163.aByteArrayArrayArray11[level][z0][x0] + 1;
                                @Pc(1242) byte local1242 = Static4.aByteArrayArrayArray1[level][z0][x0];
                                @Pc(1248) FloorOverlayType local1248 = FloorOverlayTypeList.method4395(underlayId - 1);
                                @Pc(1301) int local1301;
                                @Pc(1353) int local1353;
                                @Pc(1288) int local1288;
                                if (GlRenderer.enabled && !arg1 && Static62.anIntArrayArray11 != null && level == 0) {
                                    if (local1248.material != -1 && Rasterizer.textureProvider.getMaterialType(local1248.material) == 4) {
                                        Static62.anIntArrayArray11[z0][x0] = (local1248.waterOpaity << 24) + local1248.waterColor;
                                    } else {
                                        label737: for (local1288 = z0 - 1; local1288 <= z0 + 1; local1288++) {
                                            for (local1301 = x0 - 1; local1301 <= x0 + 1; local1301++) {
                                                if ((z0 != local1288 || local1301 != x0) && local1288 >= 0 && local1288 < 104 && local1301 >= 0 && local1301 < 104) {
                                                    local1353 = Static240.aByteArrayArrayArray14[level][local1288][local1301] & 0xFF;
                                                    if (local1353 != 0) {
                                                        @Pc(1366) FloorOverlayType local1366 = FloorOverlayTypeList.method4395(local1353 - 1);
                                                        if (local1366.material != -1 && Rasterizer.textureProvider.getMaterialType(local1366.material) == 4) {
                                                            Static62.anIntArrayArray11[z0][x0] = local1366.waterColor + (local1366.waterOpaity << 24);
                                                            break label737;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                local1288 = local1248.material;
                                if (local1288 >= 0 && !Rasterizer.textureProvider.method3236(local1288)) {
                                    local1288 = -1;
                                }
                                @Pc(1458) int local1458;
                                @Pc(1429) int local1429;
                                if (local1288 >= 0) {
                                    local1301 = -1;
                                    local1353 = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(Rasterizer.textureProvider.getAverageColor(local1288), 96)];
                                } else if (local1248.baseColor == -1) {
                                    local1301 = -2;
                                    local1353 = 0;
                                } else {
                                    local1301 = local1248.baseColor;
                                    local1429 = local15 + (local1301 & 0x7F);
                                    if (local1429 < 0) {
                                        local1429 = 0;
                                    } else if (local1429 > 127) {
                                        local1429 = 127;
                                    }
                                    local1458 = (local1301 & 0x380) + ((local1301 + local10 & 0xFC00) + local1429);
                                    local1353 = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(local1458, 96)];
                                }
                                if (local1248.secondaryColor >= 0) {
                                    local1429 = local1248.secondaryColor;
                                    local1458 = local15 + (local1429 & 0x7F);
                                    if (local1458 < 0) {
                                        local1458 = 0;
                                    } else if (local1458 > 127) {
                                        local1458 = 127;
                                    }
                                    @Pc(1529) int local1529 = (local1429 & 0x380) + ((local1429 + local10 & 0xFC00) + local1458);
                                    local1353 = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(local1529, 96)];
                                }
                                Static176.method3305(level, z0, x0, local322, local1242, local1288, local200, dx, dz, len, ColorUtils.multiplyLightnessSafe(normalZ, normalX), ColorUtils.multiplyLightnessSafe(local273, normalX), ColorUtils.multiplyLightnessSafe(local326, normalX), ColorUtils.multiplyLightnessSafe(local332, normalX), ColorUtils.multiplyLightnessGrayscale(local1301, normalZ), ColorUtils.multiplyLightnessGrayscale(local1301, local273), ColorUtils.multiplyLightnessGrayscale(local1301, local326), ColorUtils.multiplyLightnessGrayscale(local1301, local332), normalY, local1353);
                                if (GlRenderer.enabled && level > 0) {
                                    Static242.method4197(local322, local1242, local1301 == -2 || !local1248.hardShadow, normalX == -1 || !FloorUnderlayTypeList.get(z - 1).blockShadow, z0, x0, local200 - tileHeights[0][z0][x0], dx - tileHeights[0][z0 + 1][x0], dz - tileHeights[0][z0 + 1][x0 + 1], -tileHeights[0][z0][x0 + 1] + len);
                                }
                            }
                        }
                    }
                }
            }
            if (GlRenderer.enabled) {
                @Pc(1888) float[][] local1888 = new float[105][105];
                @Pc(1892) int[][] local1892 = tileHeights[level];
                @Pc(1896) float[][] local1896 = new float[105][105];
                @Pc(1900) float[][] local1900 = new float[105][105];
                local200 = 1;
                while (true) {
                    if (local200 > 103) {
                        @Pc(2025) GlTile[] local2025;
                        if (arg1) {
                            local2025 = Static193.method3501(renderFlags, Static163.aByteArrayArrayArray11[level], Static253.levelTileUnderlayIds[level], levelLightMap, local1896, Static62.anIntArrayArray11, Static240.aByteArrayArrayArray14[level], Static4.aByteArrayArrayArray1[level], local1888, level, local1900, local142, tileHeights[level], surfaceTileHeights[0]);
                            Static110.method2280(level, local2025);
                            break;
                        }
                        local2025 = Static193.method3501(renderFlags, Static163.aByteArrayArrayArray11[level], Static253.levelTileUnderlayIds[level], levelLightMap, local1896, null, Static240.aByteArrayArrayArray14[level], Static4.aByteArrayArrayArray1[level], local1888, level, local1900, local142, tileHeights[level], null);
                        @Pc(2049) GlTile[] local2049 = Static1.method2(local1896, local1888, tileHeights[level], level, local1900, Static4.aByteArrayArrayArray1[level], levelLightMap, Static163.aByteArrayArrayArray11[level], Static253.levelTileUnderlayIds[level], Static240.aByteArrayArrayArray14[level], renderFlags);
                        @Pc(2057) GlTile[] local2057 = new GlTile[local2025.length + local2049.length];
                        for (len = 0; len < local2025.length; len++) {
                            local2057[len] = local2025[len];
                        }
                        for (len = 0; len < local2049.length; len++) {
                            local2057[local2025.length + len] = local2049[len];
                        }
                        Static110.method2280(level, local2057);
                        BZip2State.method3393(local1900, Static253.levelTileUnderlayIds[level], Static4.aByteArrayArrayArray1[level], LightingManager.lights, level, LightingManager.lightCount, local1896, Static163.aByteArrayArrayArray11[level], Static240.aByteArrayArrayArray14[level], tileHeights[level], local1888);
                        break;
                    }
                    for (dx = 1; dx <= 103; dx++) {
                        len = local1892[dx][local200 + 1] - local1892[dx][local200 - 1];
                        dz = local1892[dx + 1][local200] - local1892[dx - 1][local200];
                        @Pc(1962) float local1962 = (float) Math.sqrt((double) (dz * dz + len * len + 65536));
                        local1888[dx][local200] = (float) dz / local1962;
                        local1896[dx][local200] = -256.0F / local1962;
                        local1900[dx][local200] = (float) len / local1962;
                    }
                    local200++;
                }
            }
            Static253.levelTileUnderlayIds[level] = null;
            Static240.aByteArrayArrayArray14[level] = null;
            Static163.aByteArrayArrayArray11[level] = null;
            Static4.aByteArrayArrayArray1[level] = null;
            Static118.levelShademap[level] = null;
        }
        Static220.method3801();
        if (arg1) {
            return;
        }
        @Pc(2204) int local2204;
        for (level = 0; level < 104; level++) {
            for (local2204 = 0; local2204 < 104; local2204++) {
                if ((renderFlags[1][level][local2204] & 0x2) == 2) {
                    method3884(level, local2204);
                }
            }
        }
        for (level = 0; level < 4; level++) {
            for (local2204 = 0; local2204 <= 104; local2204++) {
                for (z0 = 0; z0 <= 104; z0++) {
                    if ((Static60.anIntArrayArrayArray6[level][z0][local2204] & 0x1) != 0) {
                        local200 = level;
                        for (x0 = local2204; x0 > 0 && (Static60.anIntArrayArrayArray6[level][z0][x0 - 1] & 0x1) != 0; x0--) {
                        }
                        underlayId = level;
                        for (z = local2204; z < 104 && (Static60.anIntArrayArrayArray6[level][z0][z + 1] & 0x1) != 0; z++) {
                        }
                        label454: while (underlayId > 0) {
                            for (dx = x0; dx <= z; dx++) {
                                if ((Static60.anIntArrayArrayArray6[underlayId - 1][z0][dx] & 0x1) == 0) {
                                    break label454;
                                }
                            }
                            underlayId--;
                        }
                        label443: while (local200 < 3) {
                            for (dx = x0; dx <= z; dx++) {
                                if ((Static60.anIntArrayArrayArray6[local200 + 1][z0][dx] & 0x1) == 0) {
                                    break label443;
                                }
                            }
                            local200++;
                        }
                        dx = (local200 + 1 - underlayId) * (-x0 + (z - -1));
                        if (dx >= 8) {
                            len = tileHeights[local200][z0][x0] - 240;
                            normalX = tileHeights[underlayId][z0][x0];
                            Static278.method4647(1, z0 * 128, z0 * 128, x0 * 128, z * 128 + 128, len, normalX);
                            for (normalY = underlayId; normalY <= local200; normalY++) {
                                for (normalZ = x0; normalZ <= z; normalZ++) {
                                    Static60.anIntArrayArrayArray6[normalY][z0][normalZ] &= 0xFFFFFFFE;
                                }
                            }
                        }
                    }
                    if ((Static60.anIntArrayArrayArray6[level][z0][local2204] & 0x2) != 0) {
                        for (x0 = z0; x0 > 0 && (Static60.anIntArrayArrayArray6[level][x0 - 1][local2204] & 0x2) != 0; x0--) {
                        }
                        local200 = level;
                        underlayId = level;
                        for (z = z0; z < 104 && (Static60.anIntArrayArrayArray6[level][z + 1][local2204] & 0x2) != 0; z++) {
                        }
                        label508: while (underlayId > 0) {
                            for (dx = x0; dx <= z; dx++) {
                                if ((Static60.anIntArrayArrayArray6[underlayId - 1][dx][local2204] & 0x2) == 0) {
                                    break label508;
                                }
                            }
                            underlayId--;
                        }
                        label497: while (local200 < 3) {
                            for (dx = x0; dx <= z; dx++) {
                                if ((Static60.anIntArrayArrayArray6[local200 + 1][dx][local2204] & 0x2) == 0) {
                                    break label497;
                                }
                            }
                            local200++;
                        }
                        dx = (z + 1 - x0) * (-underlayId + local200 - -1);
                        if (dx >= 8) {
                            len = tileHeights[local200][x0][local2204] - 240;
                            normalX = tileHeights[underlayId][x0][local2204];
                            Static278.method4647(2, x0 * 128, z * 128 + 128, local2204 * 128, local2204 * 128, len, normalX);
                            for (normalY = underlayId; normalY <= local200; normalY++) {
                                for (normalZ = x0; normalZ <= z; normalZ++) {
                                    Static60.anIntArrayArrayArray6[normalY][normalZ][local2204] &= 0xFFFFFFFD;
                                }
                            }
                        }
                    }
                    if ((Static60.anIntArrayArrayArray6[level][z0][local2204] & 0x4) != 0) {
                        x0 = z0;
                        z = z0;
                        for (underlayId = local2204; underlayId > 0 && (Static60.anIntArrayArrayArray6[level][z0][underlayId - 1] & 0x4) != 0; underlayId--) {
                        }
                        for (local200 = local2204; local200 < 104 && (Static60.anIntArrayArrayArray6[level][z0][local200 + 1] & 0x4) != 0; local200++) {
                        }
                        label562: while (x0 > 0) {
                            for (dx = underlayId; dx <= local200; dx++) {
                                if ((Static60.anIntArrayArrayArray6[level][x0 - 1][dx] & 0x4) == 0) {
                                    break label562;
                                }
                            }
                            x0--;
                        }
                        label551: while (z < 104) {
                            for (dx = underlayId; dx <= local200; dx++) {
                                if ((Static60.anIntArrayArrayArray6[level][z + 1][dx] & 0x4) == 0) {
                                    break label551;
                                }
                            }
                            z++;
                        }
                        if ((z + 1 - x0) * (local200 - (underlayId - 1)) >= 4) {
                            dx = tileHeights[level][x0][underlayId];
                            Static278.method4647(4, x0 * 128, z * 128 + 128, underlayId * 128, local200 * 128 + 128, dx, dx);
                            for (dz = x0; dz <= z; dz++) {
                                for (len = underlayId; len <= local200; len++) {
                                    Static60.anIntArrayArrayArray6[level][dz][len] &= 0xFFFFFFFB;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!km", name = "f", descriptor = "(I)Z")
    public static boolean allLevelsvisible() {
        return GlRenderer.enabled ? true : Preferences.allLevelsVisible;
    }

    @OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(I)V")
    public static void method2750(@OriginalArg(0) int arg0) {
        Static235.anInt5276 = arg0;
        for (@Pc(3) int local3 = 0; local3 < Static152.anInt3594; local3++) {
            for (@Pc(8) int local8 = 0; local8 < Static99.anInt2550; local8++) {
                if (tiles[arg0][local3][local8] == null) {
                    tiles[arg0][local3][local8] = new Tile(arg0, local3, local8);
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ib", name = "b", descriptor = "(I)V")
    public static void unload() {
        Static251.blendChroma = null;
        Static60.anIntArrayArrayArray6 = null;
        Static128.blendMagnitude = null;
        Static163.aByteArrayArrayArray11 = null;
        Static4.aByteArrayArrayArray1 = null;
        Static118.levelShademap = null;
        Static240.aByteArrayArrayArray14 = null;
        Static253.levelTileUnderlayIds = null;
        Static139.anIntArray325 = null;
        BZip2State.anIntArray376 = null;
        Static279.anIntArray568 = null;
    }

    @OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(Z)V")
    public static void setUnderwater(@OriginalArg(0) boolean arg0) {
        if (arg0) {
            tiles = Static276.aClass3_Sub5ArrayArrayArray3;
            tileHeights = Static80.anIntArrayArrayArray19;
            Static182.aGlTileArrayArray2 = Static195.aClass3_Sub14ArrayArray3;
        } else {
            tiles = Static197.aClass3_Sub5ArrayArrayArray2;
            tileHeights = surfaceTileHeights;
            Static182.aGlTileArrayArray2 = aGlTileArrayArray1;
        }
        Static126.anInt3114 = tiles.length;
    }

    @OriginalMember(owner = "runetek4.client!p", name = "a", descriptor = "(IZIZLclient!mj;IIIBII)V")
    public static void addLoc(@OriginalArg(0) int arg0, @OriginalArg(1) boolean lowmem, @OriginalArg(2) int level, @OriginalArg(3) boolean arg3, @OriginalArg(4) CollisionMap collsion, @OriginalArg(5) int arg5, @OriginalArg(6) int shape, @OriginalArg(7) int arg7, @OriginalArg(9) int z, @OriginalArg(10) int rotation) {
        if (lowmem && !allLevelsvisible() && (renderFlags[0][arg7][z] & 0x2) == 0) {
            if ((renderFlags[level][arg7][z] & 0x10) != 0) {
                return;
            }
            if (Static4.getRenderLevel(z, arg7, level) != centralPlane) {
                return;
            }
        }
        if (level < firstvisibleLevel) {
            firstvisibleLevel = level;
        }
        @Pc(62) LocType locType = LocTypeList.get(arg5);
        if (GlRenderer.enabled && locType.render) {
            return;
        }
        @Pc(84) int local84;
        @Pc(81) int local81;
        if (rotation == 1 || rotation == 3) {
            local81 = locType.width;
            local84 = locType.length;
        } else {
            local84 = locType.width;
            local81 = locType.length;
        }
        @Pc(103) int local103;
        @Pc(112) int local112;
        if (arg7 + local84 <= 104) {
            local103 = arg7 + (local84 >> 1);
            local112 = arg7 + (local84 + 1 >> 1);
        } else {
            local112 = arg7 + 1;
            local103 = arg7;
        }
        @Pc(129) int local129;
        @Pc(133) int local133;
        if (local81 + z > 104) {
            local129 = z;
            local133 = z + 1;
        } else {
            local129 = (local81 >> 1) + z;
            local133 = z + (local81 + 1 >> 1);
        }
        @Pc(153) int[][] local153 = tileHeights[arg0];
        @Pc(165) int local165 = (local84 << 6) + (arg7 << 7);
        @Pc(173) int local173 = (local81 << 6) + (z << 7);
        @Pc(199) int local199 = local153[local103][local133] + local153[local112][local129] + local153[local103][local129] + local153[local112][local133] >> 2;
        @Pc(201) int local201 = 0;
        @Pc(213) int[][] local213;
        if (GlRenderer.enabled && arg0 != 0) {
            local213 = tileHeights[0];
            local201 = local199 - (local213[local112][local133] + local213[local112][local129] + local213[local103][local129] + local213[local103][local133] >> 2);
        }
        local213 = null;
        @Pc(261) long local261 = (long) (arg7 | 0x40000000 | z << 7 | shape << 14 | rotation << 20);
        if (arg3) {
            local213 = surfaceTileHeights[0];
        } else if (arg0 < 3) {
            local213 = tileHeights[arg0 + 1];
        }
        if (locType.interactable == 0 || arg3) {
            local261 |= Long.MIN_VALUE;
        }
        if (locType.supportItems == 1) {
            local261 |= 0x400000L;
        }
        if (locType.hasAnimation) {
            local261 |= 0x80000000L;
        }
        if (locType.hasBackgroundSound()) {
            AreaSoundManager.add(z, locType, rotation, null, arg7, level, null);
        }
        @Pc(330) boolean local330 = locType.castShadow & !arg3;
        local261 |= (long) arg5 << 32;
        @Pc(387) Entity local387;
        @Pc(403) LocEntity local403;
        if (shape == 22) {
            if (Preferences.showGroundDecorations || locType.interactable != 0 || locType.blockWalk == 1 || locType.forceDecor) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    local403 = locType.method3428(rotation, local165, local153, 22, local199, local213, lowmem, null, local330, local173);
                    if (GlRenderer.enabled && local330) {
                        Static242.method4211(local403.sprite, local165, local201, local173);
                    }
                    local387 = local403.model;
                } else {
                    local387 = new Loc(arg5, 22, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                }
                Scene.addGroundDecoration(level, arg7, z, local199, local387, local261, locType.renderUnderFeet);
                if (locType.blockWalk == 1 && collsion != null) {
                    collsion.method3057(arg7, z);
                }
            }
        } else if (shape == 10 || shape == 11) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                local403 = locType.method3428(shape == 11 ? rotation + 4 : rotation, local165, local153, 10, local199, local213, lowmem, null, local330, local173);
                if (GlRenderer.enabled && local330) {
                    Static242.method4211(local403.sprite, local165, local201, local173);
                }
                local387 = local403.model;
            } else {
                local387 = new Loc(arg5, 10, shape == 11 ? rotation + 4 : rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
            }
            if (local387 != null) {
                @Pc(531) boolean local531 = Static5.method35(level, arg7, z, local199, local84, local81, local387, local261);
                if (locType.active && local531 && lowmem) {
                    @Pc(541) int local541 = 15;
                    if (local387 instanceof Model) {
                        local541 = ((Model) local387).getLengthXZ() / 4;
                        if (local541 > 30) {
                            local541 = 30;
                        }
                    }
                    for (@Pc(560) int local560 = 0; local560 <= local84; local560++) {
                        for (@Pc(565) int local565 = 0; local565 <= local81; local565++) {
                            if (Static118.levelShademap[level][arg7 + local560][local565 + z] < local541) {
                                Static118.levelShademap[level][arg7 + local560][z + local565] = (byte) local541;
                            }
                        }
                    }
                }
            }
            if (locType.blockWalk != 0 && collsion != null) {
                collsion.flagScenery(arg7, locType.blockRange, z, local84, local81);
            }
        } else if (shape >= 12) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                local403 = locType.method3428(rotation, local165, local153, shape, local199, local213, lowmem, null, local330, local173);
                if (GlRenderer.enabled && local330) {
                    Static242.method4211(local403.sprite, local165, local201, local173);
                }
                local387 = local403.model;
            } else {
                local387 = new Loc(arg5, shape, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
            }
            Static5.method35(level, arg7, z, local199, 1, 1, local387, local261);
            if (lowmem && shape >= 12 && shape <= 17 && shape != 13 && level > 0) {
                Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x4;
            }
            if (locType.blockWalk != 0 && collsion != null) {
                collsion.flagScenery(arg7, locType.blockRange, z, local84, local81);
            }
        } else if (shape == 0) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                local403 = locType.method3428(rotation, local165, local153, 0, local199, local213, lowmem, null, local330, local173);
                if (GlRenderer.enabled && local330) {
                    Static242.method4211(local403.sprite, local165, local201, local173);
                }
                local387 = local403.model;
            } else {
                local387 = new Loc(arg5, 0, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
            }
            Static262.addWall(level, arg7, z, local199, local387, null, Static267.ROTATION_WALL_TYPE[rotation], 0, local261);
            if (lowmem) {
                if (rotation == 0) {
                    if (locType.active) {
                        Static118.levelShademap[level][arg7][z] = 50;
                        Static118.levelShademap[level][arg7][z + 1] = 50;
                    }
                    if (locType.occlude) {
                        Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x1;
                    }
                } else if (rotation == 1) {
                    if (locType.active) {
                        Static118.levelShademap[level][arg7][z + 1] = 50;
                        Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
                    }
                    if (locType.occlude) {
                        Static60.anIntArrayArrayArray6[level][arg7][z + 1] |= 0x2;
                    }
                } else if (rotation == 2) {
                    if (locType.active) {
                        Static118.levelShademap[level][arg7 + 1][z] = 50;
                        Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
                    }
                    if (locType.occlude) {
                        Static60.anIntArrayArrayArray6[level][arg7 + 1][z] |= 0x1;
                    }
                } else if (rotation == 3) {
                    if (locType.active) {
                        Static118.levelShademap[level][arg7][z] = 50;
                        Static118.levelShademap[level][arg7 + 1][z] = 50;
                    }
                    if (locType.occlude) {
                        Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x2;
                    }
                }
            }
            if (locType.blockWalk != 0 && collsion != null) {
                collsion.addWall(arg7, z, shape, rotation, locType.blockRange);
            }
            if (locType.wallWidth != 16) {
                Static18.method559(level, arg7, z, locType.wallWidth);
            }
        } else if (shape == 1) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                local403 = locType.method3428(rotation, local165, local153, 1, local199, local213, lowmem, null, local330, local173);
                if (GlRenderer.enabled && local330) {
                    Static242.method4211(local403.sprite, local165, local201, local173);
                }
                local387 = local403.model;
            } else {
                local387 = new Loc(arg5, 1, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
            }
            Static262.addWall(level, arg7, z, local199, local387, null, Wall.ROTATION_WALL_CORNER_TYPE[rotation], 0, local261);
            if (locType.active && lowmem) {
                if (rotation == 0) {
                    Static118.levelShademap[level][arg7][z + 1] = 50;
                } else if (rotation == 1) {
                    Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
                } else if (rotation == 2) {
                    Static118.levelShademap[level][arg7 + 1][z] = 50;
                } else if (rotation == 3) {
                    Static118.levelShademap[level][arg7][z] = 50;
                }
            }
            if (locType.blockWalk != 0 && collsion != null) {
                collsion.addWall(arg7, z, shape, rotation, locType.blockRange);
            }
        } else {
            @Pc(1226) int local1226;
            if (shape == 2) {
                local1226 = rotation + 1 & 0x3;
                @Pc(1269) Entity local1269;
                @Pc(1254) Entity local1254;
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    @Pc(1287) LocEntity local1287 = locType.method3428(rotation + 4, local165, local153, 2, local199, local213, lowmem, null, local330, local173);
                    if (GlRenderer.enabled && local330) {
                        Static242.method4211(local1287.sprite, local165, local201, local173);
                    }
                    local1254 = local1287.model;
                    local1287 = locType.method3428(local1226, local165, local153, 2, local199, local213, lowmem, null, local330, local173);
                    if (GlRenderer.enabled && local330) {
                        Static242.method4211(local1287.sprite, local165, local201, local173);
                    }
                    local1269 = local1287.model;
                } else {
                    local1254 = new Loc(arg5, 2, rotation + 4, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                    local1269 = new Loc(arg5, 2, local1226, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                }
                Static262.addWall(level, arg7, z, local199, local1254, local1269, Static267.ROTATION_WALL_TYPE[rotation], Static267.ROTATION_WALL_TYPE[local1226], local261);
                if (locType.occlude && lowmem) {
                    if (rotation == 0) {
                        Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x1;
                        Static60.anIntArrayArrayArray6[level][arg7][z + 1] |= 0x2;
                    } else if (rotation == 1) {
                        Static60.anIntArrayArrayArray6[level][arg7][z + 1] |= 0x2;
                        Static60.anIntArrayArrayArray6[level][arg7 + 1][z] |= 0x1;
                    } else if (rotation == 2) {
                        Static60.anIntArrayArrayArray6[level][arg7 + 1][z] |= 0x1;
                        Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x2;
                    } else if (rotation == 3) {
                        Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x2;
                        Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x1;
                    }
                }
                if (locType.blockWalk != 0 && collsion != null) {
                    collsion.addWall(arg7, z, shape, rotation, locType.blockRange);
                }
                if (locType.wallWidth != 16) {
                    Static18.method559(level, arg7, z, locType.wallWidth);
                }
            } else if (shape == 3) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    local403 = locType.method3428(rotation, local165, local153, 3, local199, local213, lowmem, null, local330, local173);
                    if (GlRenderer.enabled && local330) {
                        Static242.method4211(local403.sprite, local165, local201, local173);
                    }
                    local387 = local403.model;
                } else {
                    local387 = new Loc(arg5, 3, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                }
                Static262.addWall(level, arg7, z, local199, local387, null, Wall.ROTATION_WALL_CORNER_TYPE[rotation], 0, local261);
                if (locType.active && lowmem) {
                    if (rotation == 0) {
                        Static118.levelShademap[level][arg7][z + 1] = 50;
                    } else if (rotation == 1) {
                        Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
                    } else if (rotation == 2) {
                        Static118.levelShademap[level][arg7 + 1][z] = 50;
                    } else if (rotation == 3) {
                        Static118.levelShademap[level][arg7][z] = 50;
                    }
                }
                if (locType.blockWalk != 0 && collsion != null) {
                    collsion.addWall(arg7, z, shape, rotation, locType.blockRange);
                }
            } else if (shape == 9) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    local403 = locType.method3428(rotation, local165, local153, shape, local199, local213, lowmem, null, local330, local173);
                    if (GlRenderer.enabled && local330) {
                        Static242.method4211(local403.sprite, local165, local201, local173);
                    }
                    local387 = local403.model;
                } else {
                    local387 = new Loc(arg5, shape, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                }
                Static5.method35(level, arg7, z, local199, 1, 1, local387, local261);
                if (locType.blockWalk != 0 && collsion != null) {
                    collsion.flagScenery(arg7, locType.blockRange, z, local84, local81);
                }
                if (locType.wallWidth != 16) {
                    Static18.method559(level, arg7, z, locType.wallWidth);
                }
            } else if (shape == 4) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    local403 = locType.method3428(rotation, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
                    if (GlRenderer.enabled && local330) {
                        Static242.method4211(local403.sprite, local165, local201, local173);
                    }
                    local387 = local403.model;
                } else {
                    local387 = new Loc(arg5, 4, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                }
                Static91.addWallDecoration(level, arg7, z, local199, local387, null, Static267.ROTATION_WALL_TYPE[rotation], 0, 0, 0, local261);
            } else {
                @Pc(1889) long local1889;
                @Pc(1934) Entity local1934;
                @Pc(1950) LocEntity local1950;
                if (shape == 5) {
                    local1226 = 16;
                    local1889 = getWallKey(level, arg7, z);
                    if (local1889 != 0L) {
                        local1226 = LocTypeList.get(Integer.MAX_VALUE & (int) (local1889 >>> 32)).wallWidth;
                    }
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        local1950 = locType.method3428(rotation, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
                        if (GlRenderer.enabled && local330) {
                            Static242.method4211(local1950.sprite, local165 - Static34.WALL_DECORATION_ROTATION_FORWARD_X[rotation] * 8, local201, local173 - Static238.WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * 8);
                        }
                        local1934 = local1950.model;
                    } else {
                        local1934 = new Loc(arg5, 4, rotation, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                    }
                    Static91.addWallDecoration(level, arg7, z, local199, local1934, null, Static267.ROTATION_WALL_TYPE[rotation], 0, local1226 * Static34.WALL_DECORATION_ROTATION_FORWARD_X[rotation], Static238.WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * local1226, local261);
                } else if (shape == 6) {
                    local1226 = 8;
                    local1889 = getWallKey(level, arg7, z);
                    if (local1889 != 0L) {
                        local1226 = LocTypeList.get(Integer.MAX_VALUE & (int) (local1889 >>> 32)).wallWidth / 2;
                    }
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        local1950 = locType.method3428(rotation + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
                        if (GlRenderer.enabled && local330) {
                            Static242.method4211(local1950.sprite, local165 - Static114.anIntArray565[rotation] * 8, local201, local173 - Static64.anIntArray154[rotation] * 8);
                        }
                        local1934 = local1950.model;
                    } else {
                        local1934 = new Loc(arg5, 4, rotation + 4, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                    }
                    Static91.addWallDecoration(level, arg7, z, local199, local1934, null, 256, rotation, local1226 * Static114.anIntArray565[rotation], local1226 * Static64.anIntArray154[rotation], local261);
                } else if (shape == 7) {
                    @Pc(2137) int local2137 = rotation + 2 & 0x3;
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        @Pc(2183) LocEntity local2183 = locType.method3428(local2137 + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
                        if (GlRenderer.enabled && local330) {
                            Static242.method4211(local2183.sprite, local165, local201, local173);
                        }
                        local387 = local2183.model;
                    } else {
                        local387 = new Loc(arg5, 4, local2137 + 4, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                    }
                    Static91.addWallDecoration(level, arg7, z, local199, local387, null, 256, local2137, 0, 0, local261);
                } else if (shape == 8) {
                    local1226 = 8;
                    local1889 = getWallKey(level, arg7, z);
                    if (local1889 != 0L) {
                        local1226 = LocTypeList.get(Integer.MAX_VALUE & (int) (local1889 >>> 32)).wallWidth / 2;
                    }
                    @Pc(2244) int local2244 = rotation + 2 & 0x3;
                    @Pc(2289) Entity local2289;
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        @Pc(2297) int local2297 = Static64.anIntArray154[rotation] * 8;
                        @Pc(2303) int local2303 = Static114.anIntArray565[rotation] * 8;
                        @Pc(2319) LocEntity local2319 = locType.method3428(rotation + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
                        if (GlRenderer.enabled && local330) {
                            Static242.method4211(local2319.sprite, local165 - local2303, local201, local173 - local2297);
                        }
                        local1934 = local2319.model;
                        local2319 = locType.method3428(local2244 + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
                        if (GlRenderer.enabled && local330) {
                            Static242.method4211(local2319.sprite, local165 - local2303, local201, local173 - local2297);
                        }
                        local2289 = local2319.model;
                    } else {
                        local1934 = new Loc(arg5, 4, rotation + 4, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                        local2289 = new Loc(arg5, 4, local2244 + 4, arg0, arg7, z, locType.anim, locType.allowRandomizedAnimation, null);
                    }
                    Static91.addWallDecoration(level, arg7, z, local199, local1934, local2289, 256, rotation, local1226 * Static114.anIntArray565[rotation], Static64.anIntArray154[rotation] * local1226, local261);
                }
            }
        }
    }

    @OriginalMember(owner = "client!bm", name = "a", descriptor = "(IBIIII)V")
    public static void method645(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
        @Pc(3) int local3;
        @Pc(10) int local10;
        for (local3 = arg1; local3 <= arg3 + arg1; local3++) {
            for (local10 = arg2; local10 <= arg4 + arg2; local10++) {
                if (local10 >= 0 && local10 < 104 && local3 >= 0 && local3 < 104) {
                    Static118.levelShademap[arg0][local10][local3] = 127;
                }
            }
        }
        for (local3 = arg1; local3 < arg3 + arg1; local3++) {
            for (local10 = arg2; local10 < arg2 + arg4; local10++) {
                if (local10 >= 0 && local10 < 104 && local3 >= 0 && local3 < 104) {
                    tileHeights[arg0][local10][local3] = arg0 <= 0 ? 0 : tileHeights[arg0 - 1][local10][local3];
                }
            }
        }
        if (arg2 > 0 && arg2 < 104) {
            for (local3 = arg1 + 1; local3 < arg1 + arg3; local3++) {
                if (local3 >= 0 && local3 < 104) {
                    tileHeights[arg0][arg2][local3] = tileHeights[arg0][arg2 - 1][local3];
                }
            }
        }
        if (arg1 > 0 && arg1 < 104) {
            for (local3 = arg2 + 1; local3 < arg2 + arg4; local3++) {
                if (local3 >= 0 && local3 < 104) {
                    tileHeights[arg0][local3][arg1] = tileHeights[arg0][local3][arg1 - 1];
                }
            }
        }
        if (arg2 < 0 || arg1 < 0 || arg2 >= 104 || arg1 >= 104) {
            return;
        }
        if (arg0 == 0) {
            if (arg2 > 0 && tileHeights[arg0][arg2 - 1][arg1] != 0) {
                tileHeights[arg0][arg2][arg1] = tileHeights[arg0][arg2 - 1][arg1];
            } else if (arg1 > 0 && tileHeights[arg0][arg2][arg1 - 1] != 0) {
                tileHeights[arg0][arg2][arg1] = tileHeights[arg0][arg2][arg1 - 1];
            } else if (arg2 > 0 && arg1 > 0 && tileHeights[arg0][arg2 - 1][arg1 - 1] != 0) {
                tileHeights[arg0][arg2][arg1] = tileHeights[arg0][arg2 - 1][arg1 - 1];
            }
        } else if (arg2 > 0 && tileHeights[arg0 - 1][arg2 - 1][arg1] != tileHeights[arg0][arg2 - 1][arg1]) {
            tileHeights[arg0][arg2][arg1] = tileHeights[arg0][arg2 - 1][arg1];
        } else if (arg1 > 0 && tileHeights[arg0][arg2][arg1 - 1] != tileHeights[arg0 - 1][arg2][arg1 - 1]) {
            tileHeights[arg0][arg2][arg1] = tileHeights[arg0][arg2][arg1 - 1];
        } else if (arg2 > 0 && arg1 > 0 && tileHeights[arg0][arg2 - 1][arg1 - 1] != tileHeights[arg0 - 1][arg2 - 1][arg1 - 1]) {
            tileHeights[arg0][arg2][arg1] = tileHeights[arg0][arg2 - 1][arg1 - 1];
        }
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "([Lclient!mj;ZIIIII[B)V")
    public static void method2203(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) byte[] arg6) {
        @Pc(14) int local14;
        @Pc(21) int local21;
        if (!arg1) {
            for (@Pc(9) int local9 = 0; local9 < 4; local9++) {
                for (local14 = 0; local14 < 64; local14++) {
                    for (local21 = 0; local21 < 64; local21++) {
                        if (arg4 + local14 > 0 && local14 + arg4 < 103 && arg3 + local21 > 0 && local21 + arg3 < 103) {
                            arg0[local9].flags[local14 + arg4][arg3 + local21] &= 0xFEFFFFFF;
                        }
                    }
                }
            }
        }
        @Pc(95) Packet local95 = new Packet(arg6);
        @Pc(99) byte local99;
        if (arg1) {
            local99 = 1;
        } else {
            local99 = 4;
        }
        @Pc(117) int local117;
        for (local14 = 0; local14 < local99; local14++) {
            for (local21 = 0; local21 < 64; local21++) {
                for (local117 = 0; local117 < 64; local117++) {
                    Static278.method4651(arg2, arg5, arg1, local95, local117 + arg3, arg4 + local21, 0, local14);
                }
            }
        }
        @Pc(146) boolean local146 = false;
        @Pc(243) int local243;
        @Pc(188) int local188;
        @Pc(190) int local190;
        @Pc(194) int local194;
        while (local95.offset < local95.data.length) {
            local21 = local95.g1();
            if (local21 != 129) {
                local95.offset--;
                break;
            }
            for (local117 = 0; local117 < 4; local117++) {
                @Pc(168) byte local168 = local95.g1s();
                if (local168 == 0) {
                    local243 = arg4;
                    if (arg4 < 0) {
                        local243 = 0;
                    } else if (arg4 >= 104) {
                        local243 = 104;
                    }
                    local190 = arg3;
                    if (arg3 < 0) {
                        local190 = 0;
                    } else if (arg3 >= 104) {
                        local190 = 104;
                    }
                    local188 = arg4 + 64;
                    local194 = arg3 + 64;
                    if (local194 < 0) {
                        local194 = 0;
                    } else if (local194 >= 104) {
                        local194 = 104;
                    }
                    if (local188 < 0) {
                        local188 = 0;
                    } else if (local188 >= 104) {
                        local188 = 104;
                    }
                    while (local243 < local188) {
                        while (local190 < local194) {
                            aByteArrayArrayArray13[local117][local243][local190] = 0;
                            local190++;
                        }
                        local243++;
                    }
                } else if (local168 == 1) {
                    for (local243 = 0; local243 < 64; local243 += 4) {
                        for (local188 = 0; local188 < 64; local188 += 4) {
                            @Pc(305) byte local305 = local95.g1s();
                            for (local194 = local243 + arg4; local194 < arg4 + local243 + 4; local194++) {
                                for (@Pc(320) int local320 = arg3 + local188; local320 < arg3 + local188 + 4; local320++) {
                                    if (local194 >= 0 && local194 < 104 && local320 >= 0 && local320 < 104) {
                                        aByteArrayArrayArray13[local117][local194][local320] = local305;
                                    }
                                }
                            }
                        }
                    }
                } else if (local168 == 2 && local117 > 0) {
                    local188 = arg4 + 64;
                    local190 = arg3;
                    local194 = arg3 + 64;
                    if (local188 < 0) {
                        local188 = 0;
                    } else if (local188 >= 104) {
                        local188 = 104;
                    }
                    if (arg3 < 0) {
                        local190 = 0;
                    } else if (arg3 >= 104) {
                        local190 = 104;
                    }
                    if (local194 < 0) {
                        local194 = 0;
                    } else if (local194 >= 104) {
                        local194 = 104;
                    }
                    local243 = arg4;
                    if (arg4 < 0) {
                        local243 = 0;
                    } else if (arg4 >= 104) {
                        local243 = 104;
                    }
                    while (local188 > local243) {
                        while (local190 < local194) {
                            aByteArrayArrayArray13[local117][local243][local190] = aByteArrayArrayArray13[local117 - 1][local243][local190];
                            local190++;
                        }
                        local243++;
                    }
                }
            }
            local146 = true;
        }
        @Pc(515) int local515;
        if (GlRenderer.enabled && !arg1) {
            @Pc(490) Environment local490 = null;
            label270: while (true) {
                label263: do {
                    while (local95.offset < local95.data.length) {
                        local117 = local95.g1();
                        if (local117 != 0) {
                            if (local117 != 1) {
                                throw new IllegalStateException();
                            }
                            local515 = local95.g1();
                            continue label263;
                        }
                        local490 = new Environment(local95);
                    }
                    if (local490 == null) {
                        local490 = new Environment();
                    }
                    for (local117 = 0; local117 < 8; local117++) {
                        for (local515 = 0; local515 < 8; local515++) {
                            local243 = local117 + (arg4 >> 3);
                            local188 = (arg3 >> 3) + local515;
                            if (local243 >= 0 && local243 < 13 && local188 >= 0 && local188 < 13) {
                                FogManager.chunksAtmosphere[local243][local188] = local490;
                            }
                        }
                    }
                    break label270;
                } while (local515 <= 0);
                for (local243 = 0; local243 < local515; local243++) {
                    @Pc(529) Light local529 = new Light(local95);
                    if (local529.anInt2243 == 31) {
                        @Pc(541) LightType local541 = LightTypeList.get(local95.g2());
                        local529.method1762(local541.anInt2865, local541.anInt2873, local541.anInt2867, local541.anInt2872);
                    }
                    local529.z += arg3 << 7;
                    local529.x += arg4 << 7;
                    local194 = local529.z >> 7;
                    local190 = local529.x >> 7;
                    if (local190 >= 0 && local194 >= 0 && local190 < 104 && local194 < 104) {
                        local529.aBoolean125 = (renderFlags[1][local190][local194] & 0x2) != 0;
                        local529.y = tileHeights[local529.level][local190][local194] - local529.y;
                        LightingManager.method2389(local529);
                    }
                }
            }
        }
        if (local146) {
            return;
        }
        for (local21 = 0; local21 < 4; local21++) {
            for (local117 = 0; local117 < 16; local117++) {
                for (local515 = 0; local515 < 16; local515++) {
                    local243 = (arg4 >> 2) + local117;
                    local188 = local515 + (arg3 >> 2);
                    if (local243 >= 0 && local243 < 26 && local188 >= 0 && local188 < 26) {
                        aByteArrayArrayArray13[local21][local243][local188] = 0;
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!v", name = "a", descriptor = "(IIIJ)Z")
    public static boolean isLocValid(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) long arg3) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        if (local7 == null) {
            return false;
        } else if (local7.wall != null && local7.wall.aLong107 == arg3) {
            return true;
        } else if (local7.wallDecor != null && local7.wallDecor.key == arg3) {
            return true;
        } else if (local7.groundDecor != null && local7.groundDecor.key == arg3) {
            return true;
        } else {
            for (@Pc(46) int local46 = 0; local46 < local7.entityCount; local46++) {
                if (local7.sceneries[local46].hash == arg3) {
                    return true;
                }
            }
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!pb", name = "b", descriptor = "(III)Lclient!jj;")
    public static ObjStackEntity removeObjStack(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) Tile local7 = tiles[arg0][arg1][arg2];
        if (local7 == null) {
            return null;
        } else {
            @Pc(14) ObjStackEntity local14 = local7.aClass79_1;
            local7.aClass79_1 = null;
            return local14;
        }
    }
}
