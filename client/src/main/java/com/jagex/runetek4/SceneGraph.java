package com.jagex.runetek4;

import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.Wall;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SceneGraph {
    @OriginalMember(owner = "runetek4.client!gj", name = "m", descriptor = "[[[I")
    public static int[][][] tileHeights;

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
}
