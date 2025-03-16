package com.jagex.runetek4;

import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.Wall;
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
        Static36.aClass3_Sub14ArrayArray1 = null;
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
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
        return local7 == null ? null : local7.wall;
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "b", descriptor = "(III)Lclient!ec;")
    public static Scenery getScenery(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
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
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
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
        if (!Static138.allLevelsvisible() && (renderFlags[0][arg1][arg4] & 0x2) == 0) {
            local39 = arg2;
            if ((renderFlags[arg2][arg1][arg4] & 0x8) != 0) {
                local39 = 0;
            }
            if (local39 != Static41.anInt1316) {
                return;
            }
        }
        local39 = arg2;
        if (arg2 < 3 && (renderFlags[1][arg1][arg4] & 0x2) == 2) {
            local39 = arg2 + 1;
        }
        Static43.method1144(arg4, arg1, arg2, arg6, local39, PathFinder.collisionMaps[arg2]);
        if (arg0 >= 0) {
            @Pc(92) boolean local92 = Static250.aBoolean283;
            Static250.aBoolean283 = true;
            Static185.method3397(local39, false, arg2, false, PathFinder.collisionMaps[arg2], arg0, arg5, arg1, arg4, arg3);
            Static250.aBoolean283 = local92;
        }
    }

    @OriginalMember(owner = "runetek4.client!vj", name = "a", descriptor = "(III)J")
    public static long getWallKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
        return local7 == null || local7.wall == null ? 0L : local7.wall.aLong107;
    }

    @OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "(III)J")
    public static long getWallDecorKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
        return local7 == null || local7.wallDecor == null ? 0L : local7.wallDecor.key;
    }

    @OriginalMember(owner = "runetek4.client!cl", name = "a", descriptor = "(III)J")
    public static long getSceneryKey(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
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
        @Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
        return local7 == null || local7.groundDecor == null ? 0L : local7.groundDecor.key;
    }
}
