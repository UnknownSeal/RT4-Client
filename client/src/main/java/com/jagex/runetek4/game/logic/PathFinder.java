package com.jagex.runetek4.game.logic;

import com.jagex.runetek4.ui.component.Crosshair;
import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.network.ClientProt;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PathFinder {

    @OriginalMember(owner = "runetek4.client!li", name = "h", descriptor = "[Lclient!mj;")
    public static final CollisionMap[] collisionMaps = new CollisionMap[4];

    @OriginalMember(owner = "runetek4.client!vc", name = "eb", descriptor = "[I")
    public static final int[] bfsStepX = new int[4096];

    @OriginalMember(owner = "runetek4.client!gk", name = "c", descriptor = "[I")
    public static final int[] bfsStepZ = new int[4096];

    @OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "[[I")
    public static final int[][] parents = new int[104][104];

    @OriginalMember(owner = "runetek4.client!nd", name = "q", descriptor = "[[I")
    public static final int[][] costs = new int[104][104];

    @OriginalMember(owner = "runetek4.client!s", name = "d", descriptor = "I")
    public static int tryMoveNearest = 0;

    @OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(IIIZIIIIIIII)Z")
    public static boolean findPath(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
        if (PlayerList.self.getSize() == 2) {
            return findPathN(arg6, arg7, arg4, arg0, arg9, arg3, arg2, arg1, arg5, arg8, arg10);
        } else if (PlayerList.self.getSize() <= 2) {
            return findPathN(arg5, arg4, arg10, arg9, arg8, arg2, arg1, arg3, arg7, arg0, arg6);
        } else {
            return findPath1(arg9, arg6, arg8, arg1, PlayerList.self.getSize(), arg5, arg7, arg4, arg10, arg2, arg3, arg0);
        }
    }

    @OriginalMember(owner = "client!di", name = "a", descriptor = "(IIIIIIIIZIII)Z")
    public static boolean findPathN(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) boolean arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
        @Pc(3) int x;
        @Pc(10) int z;
        for (x = 0; x < 104; x++) {
            for (z = 0; z < 104; z++) {
                parents[x][z] = 0;
                costs[x][z] = 99999999;
            }
        }
        x = arg2;
        parents[arg2][arg9] = 99;
        z = arg9;
        costs[arg2][arg9] = 0;
        @Pc(51) byte local51 = 0;
        @Pc(53) boolean local53 = false;
        @Pc(64) int local64 = 0;
        bfsStepX[0] = arg2;
        @Pc(71) int local71 = local51 + 1;
        bfsStepZ[0] = arg9;
        @Pc(78) int[][] local78 = collisionMaps[Player.plane].flags;
        @Pc(198) int local198;
        while (local71 != local64) {
            z = bfsStepZ[local64];
            x = bfsStepX[local64];
            local64 = local64 + 1 & 0xFFF;
            if (x == arg0 && z == arg3) {
                local53 = true;
                break;
            }
            if (arg8 != 0) {
                if ((arg8 < 5 || arg8 == 10) && collisionMaps[Player.plane].isAtWall(arg3, x, z, arg0, arg8 - 1, 1, arg6)) {
                    local53 = true;
                    break;
                }
                if (arg8 < 10 && collisionMaps[Player.plane].isAtWallDecor(arg3, arg8 - 1, arg0, z, 1, arg6, x)) {
                    local53 = true;
                    break;
                }
            }
            if (arg10 != 0 && arg5 != 0 && collisionMaps[Player.plane].isInsideOrOutsideRect(arg0, z, x, 1, arg10, arg1, arg3, arg5)) {
                local53 = true;
                break;
            }
            local198 = costs[x][z] + 1;
            if (x > 0 && parents[x - 1][z] == 0 && (local78[x - 1][z] & 0x12C0108) == 0) {
                bfsStepX[local71] = x - 1;
                bfsStepZ[local71] = z;
                local71 = local71 + 1 & 0xFFF;
                parents[x - 1][z] = 2;
                costs[x - 1][z] = local198;
            }
            if (x < 103 && parents[x + 1][z] == 0 && (local78[x + 1][z] & 0x12C0180) == 0) {
                bfsStepX[local71] = x + 1;
                bfsStepZ[local71] = z;
                local71 = local71 + 1 & 0xFFF;
                parents[x + 1][z] = 8;
                costs[x + 1][z] = local198;
            }
            if (z > 0 && parents[x][z - 1] == 0 && (local78[x][z - 1] & 0x12C0102) == 0) {
                bfsStepX[local71] = x;
                bfsStepZ[local71] = z - 1;
                parents[x][z - 1] = 1;
                local71 = local71 + 1 & 0xFFF;
                costs[x][z - 1] = local198;
            }
            if (z < 103 && parents[x][z + 1] == 0 && (local78[x][z + 1] & 0x12C0120) == 0) {
                bfsStepX[local71] = x;
                bfsStepZ[local71] = z + 1;
                local71 = local71 + 1 & 0xFFF;
                parents[x][z + 1] = 4;
                costs[x][z + 1] = local198;
            }
            if (x > 0 && z > 0 && parents[x - 1][z - 1] == 0 && (local78[x - 1][z - 1] & 0x12C010E) == 0 && (local78[x - 1][z] & 0x12C0108) == 0 && (local78[x][z - 1] & 0x12C0102) == 0) {
                bfsStepX[local71] = x - 1;
                bfsStepZ[local71] = z - 1;
                local71 = local71 + 1 & 0xFFF;
                parents[x - 1][z - 1] = 3;
                costs[x - 1][z - 1] = local198;
            }
            if (x < 103 && z > 0 && parents[x + 1][z - 1] == 0 && (local78[x + 1][z - 1] & 0x12C0183) == 0 && (local78[x + 1][z] & 0x12C0180) == 0 && (local78[x][z - 1] & 0x12C0102) == 0) {
                bfsStepX[local71] = x + 1;
                bfsStepZ[local71] = z - 1;
                local71 = local71 + 1 & 0xFFF;
                parents[x + 1][z - 1] = 9;
                costs[x + 1][z - 1] = local198;
            }
            if (x > 0 && z < 103 && parents[x - 1][z + 1] == 0 && (local78[x - 1][z + 1] & 0x12C0138) == 0 && (local78[x - 1][z] & 0x12C0108) == 0 && (local78[x][z + 1] & 0x12C0120) == 0) {
                bfsStepX[local71] = x - 1;
                bfsStepZ[local71] = z + 1;
                parents[x - 1][z + 1] = 6;
                local71 = local71 + 1 & 0xFFF;
                costs[x - 1][z + 1] = local198;
            }
            if (x < 103 && z < 103 && parents[x + 1][z + 1] == 0 && (local78[x + 1][z + 1] & 0x12C01E0) == 0 && (local78[x + 1][z] & 0x12C0180) == 0 && (local78[x][z + 1] & 0x12C0120) == 0) {
                bfsStepX[local71] = x + 1;
                bfsStepZ[local71] = z + 1;
                parents[x + 1][z + 1] = 12;
                local71 = local71 + 1 & 0xFFF;
                costs[x + 1][z + 1] = local198;
            }
        }
        tryMoveNearest = 0;
        @Pc(839) int local839;
        if (!local53) {
            if (!arg7) {
                return false;
            }
            local198 = 1000;
            local839 = 100;
            for (@Pc(846) int local846 = arg0 - 10; local846 <= arg0 + 10; local846++) {
                for (@Pc(856) int local856 = arg3 - 10; local856 <= arg3 + 10; local856++) {
                    if (local846 >= 0 && local856 >= 0 && local846 < 104 && local856 < 104 && costs[local846][local856] < 100) {
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
                        if (local968 < local198 || local968 == local198 && costs[local846][local856] < local839) {
                            z = local856;
                            local198 = local968;
                            x = local846;
                            local839 = costs[local846][local856];
                        }
                    }
                }
            }
            if (local198 == 1000) {
                return false;
            }
            if (arg2 == x && z == arg9) {
                return false;
            }
            tryMoveNearest = 1;
        }
        @Pc(1032) byte local1032 = 0;
        bfsStepX[0] = x;
        local64 = local1032 + 1;
        bfsStepZ[0] = z;
        local198 = local839 = parents[x][z];
        while (arg2 != x || z != arg9) {
            if (local839 != local198) {
                local839 = local198;
                bfsStepX[local64] = x;
                bfsStepZ[local64++] = z;
            }
            if ((local198 & 0x2) != 0) {
                x++;
            } else if ((local198 & 0x8) != 0) {
                x--;
            }
            if ((local198 & 0x1) != 0) {
                z++;
            } else if ((local198 & 0x4) != 0) {
                z--;
            }
            local198 = parents[x][z];
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

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(IIIIIZIIIIII)Z")
    public static boolean findPathN(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(11) int arg10) {
        @Pc(3) int local3;
        @Pc(8) int local8;
        for (local3 = 0; local3 < 104; local3++) {
            for (local8 = 0; local8 < 104; local8++) {
                parents[local3][local8] = 0;
                costs[local3][local8] = 99999999;
            }
        }
        parents[arg10][arg3] = 99;
        costs[arg10][arg3] = 0;
        local8 = arg3;
        local3 = arg10;
        @Pc(53) byte local53 = 0;
        bfsStepX[0] = arg10;
        @Pc(59) boolean local59 = false;
        @Pc(61) int local61 = 0;
        @Pc(64) int local64 = local53 + 1;
        bfsStepZ[0] = arg3;
        @Pc(71) int[][] local71 = collisionMaps[Player.plane].flags;
        @Pc(193) int local193;
        while (local61 != local64) {
            local3 = bfsStepX[local61];
            local8 = bfsStepZ[local61];
            local61 = local61 + 1 & 0xFFF;
            if (arg8 == local3 && arg4 == local8) {
                local59 = true;
                break;
            }
            if (arg1 != 0) {
                if ((arg1 < 5 || arg1 == 10) && collisionMaps[Player.plane].isAtWall(arg4, local3, local8, arg8, arg1 - 1, 2, arg7)) {
                    local59 = true;
                    break;
                }
                if (arg1 < 10 && collisionMaps[Player.plane].isAtWallDecor(arg4, arg1 - 1, arg8, local8, 2, arg7, local3)) {
                    local59 = true;
                    break;
                }
            }
            if (arg0 != 0 && arg6 != 0 && collisionMaps[Player.plane].isInsideOrOutsideRect(arg8, local8, local3, 2, arg0, arg2, arg4, arg6)) {
                local59 = true;
                break;
            }
            local193 = costs[local3][local8] + 1;
            if (local3 > 0 && parents[local3 - 1][local8] == 0 && (local71[local3 - 1][local8] & 0x12C010E) == 0 && (local71[local3 - 1][local8 + 1] & 0x12C0138) == 0) {
                bfsStepX[local64] = local3 - 1;
                bfsStepZ[local64] = local8;
                local64 = local64 + 1 & 0xFFF;
                parents[local3 - 1][local8] = 2;
                costs[local3 - 1][local8] = local193;
            }
            if (local3 < 102 && parents[local3 + 1][local8] == 0 && (local71[local3 + 2][local8] & 0x12C0183) == 0 && (local71[local3 + 2][local8 + 1] & 0x12C01E0) == 0) {
                bfsStepX[local64] = local3 + 1;
                bfsStepZ[local64] = local8;
                local64 = local64 + 1 & 0xFFF;
                parents[local3 + 1][local8] = 8;
                costs[local3 + 1][local8] = local193;
            }
            if (local8 > 0 && parents[local3][local8 - 1] == 0 && (local71[local3][local8 - 1] & 0x12C010E) == 0 && (local71[local3 + 1][local8 - 1] & 0x12C0183) == 0) {
                bfsStepX[local64] = local3;
                bfsStepZ[local64] = local8 - 1;
                parents[local3][local8 - 1] = 1;
                costs[local3][local8 - 1] = local193;
                local64 = local64 + 1 & 0xFFF;
            }
            if (local8 < 102 && parents[local3][local8 + 1] == 0 && (local71[local3][local8 + 2] & 0x12C0138) == 0 && (local71[local3 + 1][local8 + 2] & 0x12C01E0) == 0) {
                bfsStepX[local64] = local3;
                bfsStepZ[local64] = local8 + 1;
                parents[local3][local8 + 1] = 4;
                local64 = local64 + 1 & 0xFFF;
                costs[local3][local8 + 1] = local193;
            }
            if (local3 > 0 && local8 > 0 && parents[local3 - 1][local8 - 1] == 0 && (local71[local3 - 1][local8] & 0x12C0138) == 0 && (local71[local3 - 1][local8 - 1] & 0x12C010E) == 0 && (local71[local3][local8 - 1] & 0x12C0183) == 0) {
                bfsStepX[local64] = local3 - 1;
                bfsStepZ[local64] = local8 - 1;
                parents[local3 - 1][local8 - 1] = 3;
                costs[local3 - 1][local8 - 1] = local193;
                local64 = local64 + 1 & 0xFFF;
            }
            if (local3 < 102 && local8 > 0 && parents[local3 + 1][local8 - 1] == 0 && (local71[local3 + 1][local8 - 1] & 0x12C010E) == 0 && (local71[local3 + 2][local8 - 1] & 0x12C0183) == 0 && (local71[local3 + 2][local8] & 0x12C01E0) == 0) {
                bfsStepX[local64] = local3 + 1;
                bfsStepZ[local64] = local8 - 1;
                local64 = local64 + 1 & 0xFFF;
                parents[local3 + 1][local8 - 1] = 9;
                costs[local3 + 1][local8 - 1] = local193;
            }
            if (local3 > 0 && local8 < 102 && parents[local3 - 1][local8 + 1] == 0 && (local71[local3 - 1][local8 + 1] & 0x12C010E) == 0 && (local71[local3 - 1][local8 + 2] & 0x12C0138) == 0 && (local71[local3][local8 + 2] & 0x12C01E0) == 0) {
                bfsStepX[local64] = local3 - 1;
                bfsStepZ[local64] = local8 + 1;
                parents[local3 - 1][local8 + 1] = 6;
                costs[local3 - 1][local8 + 1] = local193;
                local64 = local64 + 1 & 0xFFF;
            }
            if (local3 < 102 && local8 < 102 && parents[local3 + 1][local8 + 1] == 0 && (local71[local3 + 1][local8 + 2] & 0x12C0138) == 0 && (local71[local3 + 2][local8 + 2] & 0x12C01E0) == 0 && (local71[local3 + 2][local8 + 1] & 0x12C0183) == 0) {
                bfsStepX[local64] = local3 + 1;
                bfsStepZ[local64] = local8 + 1;
                local64 = local64 + 1 & 0xFFF;
                parents[local3 + 1][local8 + 1] = 12;
                costs[local3 + 1][local8 + 1] = local193;
            }
        }
        tryMoveNearest = 0;
        @Pc(921) int local921;
        if (!local59) {
            if (!arg5) {
                return false;
            }
            local193 = 1000;
            local921 = 100;
            for (@Pc(928) int local928 = arg8 - 10; local928 <= arg8 + 10; local928++) {
                for (@Pc(942) int local942 = arg4 - 10; local942 <= arg4 + 10; local942++) {
                    if (local928 >= 0 && local942 >= 0 && local928 < 104 && local942 < 104 && costs[local928][local942] < 100) {
                        @Pc(978) int local978 = 0;
                        @Pc(980) int local980 = 0;
                        if (local928 < arg8) {
                            local978 = arg8 - local928;
                        } else if (local928 > arg0 + arg8 - 1) {
                            local978 = local928 + 1 - arg0 - arg8;
                        }
                        if (arg4 > local942) {
                            local980 = arg4 - local942;
                        } else if (local942 > arg4 + arg6 - 1) {
                            local980 = local942 + 1 - arg4 - arg6;
                        }
                        @Pc(1057) int local1057 = local978 * local978 + local980 * local980;
                        if (local1057 < local193 || local193 == local1057 && costs[local928][local942] < local921) {
                            local8 = local942;
                            local921 = costs[local928][local942];
                            local193 = local1057;
                            local3 = local928;
                        }
                    }
                }
            }
            if (local193 == 1000) {
                return false;
            }
            if (arg10 == local3 && local8 == arg3) {
                return false;
            }
            tryMoveNearest = 1;
        }
        @Pc(1121) byte local1121 = 0;
        bfsStepX[0] = local3;
        local61 = local1121 + 1;
        bfsStepZ[0] = local8;
        local193 = local921 = parents[local3][local8];
        while (arg10 != local3 || arg3 != local8) {
            if (local921 != local193) {
                bfsStepX[local61] = local3;
                bfsStepZ[local61++] = local8;
                local921 = local193;
            }
            if ((local193 & 0x2) != 0) {
                local3++;
            } else if ((local193 & 0x8) != 0) {
                local3--;
            }
            if ((local193 & 0x1) != 0) {
                local8++;
            } else if ((local193 & 0x4) != 0) {
                local8--;
            }
            local193 = parents[local3][local8];
        }
        if (local61 > 0) {
            ClientProt.method3502(local61, arg9);
            return true;
        } else if (arg9 == 1) {
            return false;
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "(IBIIIIIIIIIZI)Z")
    public static boolean findPath1(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) boolean arg10, @OriginalArg(12) int arg11) {
        @Pc(3) int local3;
        @Pc(10) int local10;
        for (local3 = 0; local3 < 104; local3++) {
            for (local10 = 0; local10 < 104; local10++) {
                parents[local3][local10] = 0;
                costs[local3][local10] = 99999999;
            }
        }
        local3 = arg8;
        local10 = arg11;
        parents[arg8][arg11] = 99;
        costs[arg8][arg11] = 0;
        @Pc(53) byte local53 = 0;
        bfsStepX[0] = arg8;
        @Pc(65) int local65 = local53 + 1;
        bfsStepZ[0] = arg11;
        @Pc(69) int local69 = 0;
        @Pc(71) boolean local71 = false;
        @Pc(76) int[][] local76 = collisionMaps[Player.plane].flags;
        @Pc(201) int local201;
        @Pc(242) int local242;
        label397: while (local69 != local65) {
            local3 = bfsStepX[local69];
            local10 = bfsStepZ[local69];
            local69 = local69 + 1 & 0xFFF;
            if (arg5 == local3 && local10 == arg0) {
                local71 = true;
                break;
            }
            if (arg6 != 0) {
                if ((arg6 < 5 || arg6 == 10) && collisionMaps[Player.plane].isAtWall(arg0, local3, local10, arg5, arg6 - 1, arg4, arg3)) {
                    local71 = true;
                    break;
                }
                if (arg6 < 10 && collisionMaps[Player.plane].isAtWallDecor(arg0, arg6 - 1, arg5, local10, arg4, arg3, local3)) {
                    local71 = true;
                    break;
                }
            }
            if (arg1 != 0 && arg9 != 0 && collisionMaps[Player.plane].isInsideOrOutsideRect(arg5, local10, local3, arg4, arg1, arg7, arg0, arg9)) {
                local71 = true;
                break;
            }
            local201 = costs[local3][local10] + 1;
            if (local3 > 0 && parents[local3 - 1][local10] == 0 && (local76[local3 - 1][local10] & 0x12C010E) == 0 && (local76[local3 - 1][arg4 + local10 - 1] & 0x12C0138) == 0) {
                local242 = 1;
                while (true) {
                    if (arg4 - 1 <= local242) {
                        bfsStepX[local65] = local3 - 1;
                        bfsStepZ[local65] = local10;
                        parents[local3 - 1][local10] = 2;
                        local65 = local65 + 1 & 0xFFF;
                        costs[local3 - 1][local10] = local201;
                        break;
                    }
                    if ((local76[local3 - 1][local10 + local242] & 0x12C013E) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 < 102 && parents[local3 + 1][local10] == 0 && (local76[local3 + arg4][local10] & 0x12C0183) == 0 && (local76[arg4 + local3][local10 + arg4 - 1] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (local242 >= arg4 - 1) {
                        bfsStepX[local65] = local3 + 1;
                        bfsStepZ[local65] = local10;
                        parents[local3 + 1][local10] = 8;
                        costs[local3 + 1][local10] = local201;
                        local65 = local65 + 1 & 0xFFF;
                        break;
                    }
                    if ((local76[arg4 + local3][local10 + local242] & 0x12C01E3) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local10 > 0 && parents[local3][local10 - 1] == 0 && (local76[local3][local10 - 1] & 0x12C010E) == 0 && (local76[arg4 + local3 - 1][local10 - 1] & 0x12C0183) == 0) {
                local242 = 1;
                while (true) {
                    if (arg4 - 1 <= local242) {
                        bfsStepX[local65] = local3;
                        bfsStepZ[local65] = local10 - 1;
                        parents[local3][local10 - 1] = 1;
                        local65 = local65 + 1 & 0xFFF;
                        costs[local3][local10 - 1] = local201;
                        break;
                    }
                    if ((local76[local3 + local242][local10 - 1] & 0x12C018F) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local10 < 102 && parents[local3][local10 + 1] == 0 && (local76[local3][local10 + arg4] & 0x12C0138) == 0 && (local76[local3 + arg4 - 1][arg4 + local10] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (local242 >= arg4 - 1) {
                        bfsStepX[local65] = local3;
                        bfsStepZ[local65] = local10 + 1;
                        parents[local3][local10 + 1] = 4;
                        costs[local3][local10 + 1] = local201;
                        local65 = local65 + 1 & 0xFFF;
                        break;
                    }
                    if ((local76[local3 + local242][arg4 + local10] & 0x12C01F8) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 > 0 && local10 > 0 && parents[local3 - 1][local10 - 1] == 0 && (local76[local3 - 1][arg4 + local10 - 1 - 1] & 0x12C0138) == 0 && (local76[local3 - 1][local10 - 1] & 0x12C010E) == 0 && (local76[arg4 + local3 - 1 - 1][local10 - 1] & 0x12C0183) == 0) {
                local242 = 1;
                while (true) {
                    if (arg4 - 1 <= local242) {
                        bfsStepX[local65] = local3 - 1;
                        bfsStepZ[local65] = local10 - 1;
                        local65 = local65 + 1 & 0xFFF;
                        parents[local3 - 1][local10 - 1] = 3;
                        costs[local3 - 1][local10 - 1] = local201;
                        break;
                    }
                    if ((local76[local3 - 1][local10 + local242 - 1] & 0x12C013E) != 0 || (local76[local242 + local3 - 1][local10 - 1] & 0x12C018F) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 < 102 && local10 > 0 && parents[local3 + 1][local10 - 1] == 0 && (local76[local3 + 1][local10 - 1] & 0x12C010E) == 0 && (local76[arg4 + local3][local10 - 1] & 0x12C0183) == 0 && (local76[local3 + arg4][local10 + arg4 - 1 - 1] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (local242 >= arg4 - 1) {
                        bfsStepX[local65] = local3 + 1;
                        bfsStepZ[local65] = local10 - 1;
                        local65 = local65 + 1 & 0xFFF;
                        parents[local3 + 1][local10 - 1] = 9;
                        costs[local3 + 1][local10 - 1] = local201;
                        break;
                    }
                    if ((local76[local3 + arg4][local10 + local242 - 1] & 0x12C01E3) != 0 || (local76[local242 + local3 + 1][local10 - 1] & 0x12C018F) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 > 0 && local10 < 102 && parents[local3 - 1][local10 + 1] == 0 && (local76[local3 - 1][local10 + 1] & 0x12C010E) == 0 && (local76[local3 - 1][local10 + arg4] & 0x12C0138) == 0 && (local76[local3][local10 + arg4] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (arg4 - 1 <= local242) {
                        bfsStepX[local65] = local3 - 1;
                        bfsStepZ[local65] = local10 + 1;
                        local65 = local65 + 1 & 0xFFF;
                        parents[local3 - 1][local10 + 1] = 6;
                        costs[local3 - 1][local10 + 1] = local201;
                        break;
                    }
                    if ((local76[local3 - 1][local10 + local242 + 1] & 0x12C013E) != 0 || (local76[local242 + local3 - 1][arg4 + local10] & 0x12C01F8) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 < 102 && local10 < 102 && parents[local3 + 1][local10 + 1] == 0 && (local76[local3 + 1][local10 + arg4] & 0x12C0138) == 0 && (local76[local3 + arg4][local10 + arg4] & 0x12C01E0) == 0 && (local76[arg4 + local3][local10 + 1] & 0x12C0183) == 0) {
                for (local242 = 1; local242 < arg4 - 1; local242++) {
                    if ((local76[local242 + local3 + 1][local10 + arg4] & 0x12C01F8) != 0 || (local76[arg4 + local3][local242 + local10 + 1] & 0x12C01E3) != 0) {
                        continue label397;
                    }
                }
                bfsStepX[local65] = local3 + 1;
                bfsStepZ[local65] = local10 + 1;
                parents[local3 + 1][local10 + 1] = 12;
                costs[local3 + 1][local10 + 1] = local201;
                local65 = local65 + 1 & 0xFFF;
            }
        }
        tryMoveNearest = 0;
        if (!local71) {
            if (!arg10) {
                return false;
            }
            local201 = 1000;
            local242 = 100;
            for (@Pc(1247) int local1247 = arg5 - 10; local1247 <= arg5 + 10; local1247++) {
                for (@Pc(1257) int local1257 = arg0 - 10; local1257 <= arg0 + 10; local1257++) {
                    if (local1247 >= 0 && local1257 >= 0 && local1247 < 104 && local1257 < 104 && costs[local1247][local1257] < 100) {
                        @Pc(1295) int local1295 = 0;
                        if (arg5 > local1247) {
                            local1295 = arg5 - local1247;
                        } else if (arg5 + arg1 - 1 < local1247) {
                            local1295 = local1247 + 1 - arg1 - arg5;
                        }
                        @Pc(1334) int local1334 = 0;
                        if (local1257 < arg0) {
                            local1334 = arg0 - local1257;
                        } else if (arg0 + arg9 - 1 < local1257) {
                            local1334 = local1257 + 1 - arg0 - arg9;
                        }
                        @Pc(1377) int local1377 = local1295 * local1295 + local1334 * local1334;
                        if (local1377 < local201 || local1377 == local201 && local242 > costs[local1247][local1257]) {
                            local242 = costs[local1247][local1257];
                            local3 = local1247;
                            local201 = local1377;
                            local10 = local1257;
                        }
                    }
                }
            }
            if (local201 == 1000) {
                return false;
            }
            if (local3 == arg8 && arg11 == local10) {
                return false;
            }
            tryMoveNearest = 1;
        }
        @Pc(1438) byte local1438 = 0;
        bfsStepX[0] = local3;
        local69 = local1438 + 1;
        bfsStepZ[0] = local10;
        local201 = local242 = parents[local3][local10];
        while (local3 != arg8 || arg11 != local10) {
            if (local242 != local201) {
                bfsStepX[local69] = local3;
                local242 = local201;
                bfsStepZ[local69++] = local10;
            }
            if ((local201 & 0x2) != 0) {
                local3++;
            } else if ((local201 & 0x8) != 0) {
                local3--;
            }
            if ((local201 & 0x1) != 0) {
                local10++;
            } else if ((local201 & 0x4) != 0) {
                local10--;
            }
            local201 = parents[local3][local10];
        }
        if (local69 > 0) {
            ClientProt.method3502(local69, arg2);
            return true;
        } else if (arg2 == 1) {
            return false;
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(BJII)Z")
    public static boolean findPathToLoc(@OriginalArg(1) long arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
        @Pc(12) int local12 = (int) arg0 >> 14 & 0x1F;
        @Pc(24) int local24 = (int) arg0 >> 20 & 0x3;
        @Pc(31) int local31 = (int) (arg0 >>> 32) & Integer.MAX_VALUE;
        if (local12 == 10 || local12 == 11 || local12 == 22) {
            @Pc(46) LocType local46 = LocTypeList.get(local31);
            @Pc(62) int local62;
            @Pc(59) int local59;
            if (local24 == 0 || local24 == 2) {
                local59 = local46.length;
                local62 = local46.width;
            } else {
                local59 = local46.width;
                local62 = local46.length;
            }
            @Pc(73) int local73 = local46.blocksides;
            if (local24 != 0) {
                local73 = (local73 << local24 & 0xF) + (local73 >> 4 - local24);
            }
            findPath(PlayerList.self.movementQueueZ[0], 0, local59, true, local73, arg2, local62, 0, 2, arg1, PlayerList.self.movementQueueX[0]);
        } else {
            findPath(PlayerList.self.movementQueueZ[0], local24, 0, true, 0, arg2, 0, local12 + 1, 2, arg1, PlayerList.self.movementQueueX[0]);
        }
        Crosshair.y = Mouse.mouseClickY;
        Crosshair.CrosshairCycle = 0;
        Crosshair.CrosshairMode = 2;
        Crosshair.x = Mouse.mouseClickX;
        return true;
    }
}
