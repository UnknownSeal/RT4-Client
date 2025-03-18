package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.CollisionMap;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PathFinder {
    @OriginalMember(owner = "runetek4.client!li", name = "h", descriptor = "[Lclient!mj;")
    public static final CollisionMap[] collisionMaps = new CollisionMap[4];
    @OriginalMember(owner = "runetek4.client!vc", name = "eb", descriptor = "[I")
    public static final int[] queueX = new int[4096];
    @OriginalMember(owner = "runetek4.client!gk", name = "c", descriptor = "[I")
    public static final int[] queueZ = new int[4096];

    @OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(IIIZIIIIIIII)Z")
    public static boolean tryMove(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
        if (PlayerList.self.getSize() == 2) {
            return Static2.method8(arg6, arg7, arg4, arg0, arg9, arg3, arg2, arg1, arg5, arg8, arg10);
        } else if (PlayerList.self.getSize() <= 2) {
            return findPath2(arg5, arg4, arg10, arg9, arg8, arg2, arg1, arg3, arg7, arg0, arg6);
        } else {
            return Static96.method1955(arg9, arg6, arg8, arg1, PlayerList.self.getSize(), arg5, arg7, arg4, arg10, arg2, arg3, arg0);
        }
    }

    @OriginalMember(owner = "client!di", name = "a", descriptor = "(IIIIIIIIZIII)Z")
    public static boolean findPath2(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) boolean arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
        @Pc(3) int local3;
        @Pc(10) int local10;
        for (local3 = 0; local3 < 104; local3++) {
            for (local10 = 0; local10 < 104; local10++) {
                Static145.anIntArrayArray25[local3][local10] = 0;
                Static167.anIntArrayArray31[local3][local10] = 99999999;
            }
        }
        local3 = arg2;
        Static145.anIntArrayArray25[arg2][arg9] = 99;
        local10 = arg9;
        Static167.anIntArrayArray31[arg2][arg9] = 0;
        @Pc(51) byte local51 = 0;
        @Pc(53) boolean local53 = false;
        @Pc(64) int local64 = 0;
        queueX[0] = arg2;
        @Pc(71) int local71 = local51 + 1;
        queueZ[0] = arg9;
        @Pc(78) int[][] local78 = collisionMaps[Player.plane].flags;
        @Pc(198) int local198;
        while (local71 != local64) {
            local10 = queueZ[local64];
            local3 = queueX[local64];
            local64 = local64 + 1 & 0xFFF;
            if (local3 == arg0 && local10 == arg3) {
                local53 = true;
                break;
            }
            if (arg8 != 0) {
                if ((arg8 < 5 || arg8 == 10) && collisionMaps[Player.plane].isAtWall(arg3, local3, local10, arg0, arg8 - 1, 1, arg6)) {
                    local53 = true;
                    break;
                }
                if (arg8 < 10 && collisionMaps[Player.plane].isAtWallDecor(arg3, arg8 - 1, arg0, local10, 1, arg6, local3)) {
                    local53 = true;
                    break;
                }
            }
            if (arg10 != 0 && arg5 != 0 && collisionMaps[Player.plane].isInsideOrOutsideRect(arg0, local10, local3, 1, arg10, arg1, arg3, arg5)) {
                local53 = true;
                break;
            }
            local198 = Static167.anIntArrayArray31[local3][local10] + 1;
            if (local3 > 0 && Static145.anIntArrayArray25[local3 - 1][local10] == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0) {
                queueX[local71] = local3 - 1;
                queueZ[local71] = local10;
                local71 = local71 + 1 & 0xFFF;
                Static145.anIntArrayArray25[local3 - 1][local10] = 2;
                Static167.anIntArrayArray31[local3 - 1][local10] = local198;
            }
            if (local3 < 103 && Static145.anIntArrayArray25[local3 + 1][local10] == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0) {
                queueX[local71] = local3 + 1;
                queueZ[local71] = local10;
                local71 = local71 + 1 & 0xFFF;
                Static145.anIntArrayArray25[local3 + 1][local10] = 8;
                Static167.anIntArrayArray31[local3 + 1][local10] = local198;
            }
            if (local10 > 0 && Static145.anIntArrayArray25[local3][local10 - 1] == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
                queueX[local71] = local3;
                queueZ[local71] = local10 - 1;
                Static145.anIntArrayArray25[local3][local10 - 1] = 1;
                local71 = local71 + 1 & 0xFFF;
                Static167.anIntArrayArray31[local3][local10 - 1] = local198;
            }
            if (local10 < 103 && Static145.anIntArrayArray25[local3][local10 + 1] == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
                queueX[local71] = local3;
                queueZ[local71] = local10 + 1;
                local71 = local71 + 1 & 0xFFF;
                Static145.anIntArrayArray25[local3][local10 + 1] = 4;
                Static167.anIntArrayArray31[local3][local10 + 1] = local198;
            }
            if (local3 > 0 && local10 > 0 && Static145.anIntArrayArray25[local3 - 1][local10 - 1] == 0 && (local78[local3 - 1][local10 - 1] & 0x12C010E) == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
                queueX[local71] = local3 - 1;
                queueZ[local71] = local10 - 1;
                local71 = local71 + 1 & 0xFFF;
                Static145.anIntArrayArray25[local3 - 1][local10 - 1] = 3;
                Static167.anIntArrayArray31[local3 - 1][local10 - 1] = local198;
            }
            if (local3 < 103 && local10 > 0 && Static145.anIntArrayArray25[local3 + 1][local10 - 1] == 0 && (local78[local3 + 1][local10 - 1] & 0x12C0183) == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
                queueX[local71] = local3 + 1;
                queueZ[local71] = local10 - 1;
                local71 = local71 + 1 & 0xFFF;
                Static145.anIntArrayArray25[local3 + 1][local10 - 1] = 9;
                Static167.anIntArrayArray31[local3 + 1][local10 - 1] = local198;
            }
            if (local3 > 0 && local10 < 103 && Static145.anIntArrayArray25[local3 - 1][local10 + 1] == 0 && (local78[local3 - 1][local10 + 1] & 0x12C0138) == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
                queueX[local71] = local3 - 1;
                queueZ[local71] = local10 + 1;
                Static145.anIntArrayArray25[local3 - 1][local10 + 1] = 6;
                local71 = local71 + 1 & 0xFFF;
                Static167.anIntArrayArray31[local3 - 1][local10 + 1] = local198;
            }
            if (local3 < 103 && local10 < 103 && Static145.anIntArrayArray25[local3 + 1][local10 + 1] == 0 && (local78[local3 + 1][local10 + 1] & 0x12C01E0) == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
                queueX[local71] = local3 + 1;
                queueZ[local71] = local10 + 1;
                Static145.anIntArrayArray25[local3 + 1][local10 + 1] = 12;
                local71 = local71 + 1 & 0xFFF;
                Static167.anIntArrayArray31[local3 + 1][local10 + 1] = local198;
            }
        }
        BZip2State.tryMoveNearest = 0;
        @Pc(839) int local839;
        if (!local53) {
            if (!arg7) {
                return false;
            }
            local198 = 1000;
            local839 = 100;
            for (@Pc(846) int local846 = arg0 - 10; local846 <= arg0 + 10; local846++) {
                for (@Pc(856) int local856 = arg3 - 10; local856 <= arg3 + 10; local856++) {
                    if (local846 >= 0 && local856 >= 0 && local846 < 104 && local856 < 104 && Static167.anIntArrayArray31[local846][local856] < 100) {
                        @Pc(894) int local894 = 0;
                        if (local856 < arg3) {
                            local894 = arg3 - local856;
                        } else if (arg5 + arg3 - 1 < local856) {
                            local894 = local856 + 1 - arg3 - arg5;
                        }
                        @Pc(927) int local927 = 0;
                        if (local846 < arg0) {
                            local927 = arg0 - local846;
                        } else if (local846 > arg10 + arg0 - 1) {
                            local927 = local846 + 1 - arg10 - arg0;
                        }
                        @Pc(968) int local968 = local894 * local894 + local927 * local927;
                        if (local968 < local198 || local968 == local198 && Static167.anIntArrayArray31[local846][local856] < local839) {
                            local10 = local856;
                            local198 = local968;
                            local3 = local846;
                            local839 = Static167.anIntArrayArray31[local846][local856];
                        }
                    }
                }
            }
            if (local198 == 1000) {
                return false;
            }
            if (arg2 == local3 && local10 == arg9) {
                return false;
            }
            BZip2State.tryMoveNearest = 1;
        }
        @Pc(1032) byte local1032 = 0;
        queueX[0] = local3;
        local64 = local1032 + 1;
        queueZ[0] = local10;
        local198 = local839 = Static145.anIntArrayArray25[local3][local10];
        while (arg2 != local3 || local10 != arg9) {
            if (local839 != local198) {
                local839 = local198;
                queueX[local64] = local3;
                queueZ[local64++] = local10;
            }
            if ((local198 & 0x2) != 0) {
                local3++;
            } else if ((local198 & 0x8) != 0) {
                local3--;
            }
            if ((local198 & 0x1) != 0) {
                local10++;
            } else if ((local198 & 0x4) != 0) {
                local10--;
            }
            local198 = Static145.anIntArrayArray25[local3][local10];
        }
        if (local64 > 0) {
            ClientProt.method3502(local64, arg4);
            return true;
        } else if (arg4 == 1) {
            return false;
        } else {
            return true;
        }
    }
}
