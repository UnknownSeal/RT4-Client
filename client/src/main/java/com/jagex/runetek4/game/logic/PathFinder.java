package com.jagex.runetek4.game.logic;

import com.jagex.runetek4.ui.component.Crosshair;
import com.jagex.runetek4.entity.player.PlayerList;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.entity.player.Player;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.network.ClientProt;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PathFinder {

    private static final int BFS_QUEUE_SIZE = 4096;

    private static final int BUILD_AREA_SIZE = 104;

    private static final int COST_INFINITY = 99999999;

    private static final int PARENT_START_MARKER = 99;

    private static final int QUEUE_INDEX_MASK = 0xFFF;

    private static final int DISTANCE_INFINITY = 1000;

    private static final int MAX_NEAREST_COST = 100;

    private static final int NEAREST_SEARCH_RADIUS = 10;

    private static final int DIR_SOUTH = 1;
    private static final int DIR_WEST = 2;
    private static final int DIR_SOUTHWEST = 3;
    private static final int DIR_NORTH = 4;
    private static final int DIR_NORTHWEST = 6;
    private static final int DIR_EAST = 8;
    private static final int DIR_SOUTHEAST = 9;
    private static final int DIR_NORTHEAST = 12;

    @OriginalMember(owner = "runetek4.client!li", name = "h", descriptor = "[Lclient!mj;")
    public static final CollisionMap[] collisionMaps = new CollisionMap[4];

    @OriginalMember(owner = "runetek4.client!vc", name = "eb", descriptor = "[I")
    public static final int[] bfsStepX = new int[BFS_QUEUE_SIZE];

    @OriginalMember(owner = "runetek4.client!gk", name = "c", descriptor = "[I")
    public static final int[] bfsStepZ = new int[BFS_QUEUE_SIZE];

    @OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "[[I")
    public static final int[][] parents = new int[BUILD_AREA_SIZE][BUILD_AREA_SIZE];

    @OriginalMember(owner = "runetek4.client!nd", name = "q", descriptor = "[[I")
    public static final int[][] costs = new int[BUILD_AREA_SIZE][BUILD_AREA_SIZE];

    @OriginalMember(owner = "runetek4.client!s", name = "d", descriptor = "I")
    public static int tryMoveNearest = 0;

    @OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(IIIZIIIIIIII)Z")
    public static boolean findPath(@OriginalArg(0) int startX, @OriginalArg(1) int startZ, @OriginalArg(2) int targetX, @OriginalArg(3) boolean allowNearest, @OriginalArg(4) int arg4, @OriginalArg(6) int targetZ, @OriginalArg(7) int targetWidth, @OriginalArg(8) int targetHeight, @OriginalArg(9) int moveType, @OriginalArg(10) int locationAngle, @OriginalArg(11) int entitySize) {
        if (PlayerList.self.getSize() == 2) {
            return findPathMediumEntity(targetWidth, targetHeight, arg4, startX, locationAngle, allowNearest, targetX, startZ, targetZ, moveType, entitySize);
        } else if (PlayerList.self.getSize() <= 2) {
            return findPathMediumEntity(targetZ, arg4, entitySize, locationAngle, moveType, targetX, startZ, allowNearest, targetHeight, startX, targetWidth);
        } else {
            return findPathLargeEntity(locationAngle, targetWidth, moveType, startZ, PlayerList.self.getSize(), targetZ, targetHeight, arg4, entitySize, targetX, allowNearest, startX);
        }
    }

    @OriginalMember(owner = "client!di", name = "a", descriptor = "(IIIIIIIIZIII)Z")
    public static boolean findPathMediumEntity(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) boolean arg7, @OriginalArg(9) int targetHeight, @OriginalArg(10) int locationAngle, @OriginalArg(11) int targetDepth) {
        @Pc(3) int x;
        @Pc(10) int z;

        // Init search grids
        for (x = 0; x < BUILD_AREA_SIZE; x++) {
            for (z = 0; z < BUILD_AREA_SIZE; z++) {
                parents[x][z] = 0;
                costs[x][z] = COST_INFINITY;
            }
        }

        // Set start position
        x = arg2;
        parents[arg2][locationAngle] = PARENT_START_MARKER;
        z = locationAngle;
        costs[arg2][locationAngle] = 0;

        @Pc(51) byte queueHead = 0;
        @Pc(53) boolean foundPath = false;
        @Pc(64) int queueTail = 0;
        bfsStepX[0] = arg2;
        @Pc(71) int queueSize = queueHead + 1;
        bfsStepZ[0] = locationAngle;
        @Pc(78) int[][] collisionFlags = collisionMaps[Player.currentLevel].flags;
        @Pc(198) int newCost;

        // Main loop
        while (queueSize != queueTail) {
            z = bfsStepZ[queueTail];
            x = bfsStepX[queueTail];
            queueTail = queueTail + 1 & QUEUE_INDEX_MASK;

            // Check if reached target
            if (x == arg0 && z == arg3) {
                foundPath = true;
                break;
            }

            // Check if at wall target
            if (targetHeight != 0) {
                if ((targetHeight < 5 || targetHeight == 10) && collisionMaps[Player.currentLevel].isAtWall(arg3, x, z, arg0, targetHeight - 1, 1, arg6)) {
                    foundPath = true;
                    break;
                }
                if (targetHeight < 10 && collisionMaps[Player.currentLevel].isAtWallDecor(arg3, targetHeight - 1, arg0, z, 1, arg6, x)) {
                    foundPath = true;
                    break;
                }
            }

            // Check if inside/outside rectangular target
            if (targetDepth != 0 && arg5 != 0 && collisionMaps[Player.currentLevel].isInsideOrOutsideRect(arg0, z, x, 1, targetDepth, arg1, arg3, arg5)) {
                foundPath = true;
                break;
            }

            newCost = costs[x][z] + 1;

            // Explore 8 adjacent tiles

            // West
            if (x > 0 && parents[x - 1][z] == 0 && (collisionFlags[x - 1][z] & 0x12C0108) == 0) {
                bfsStepX[queueSize] = x - 1;
                bfsStepZ[queueSize] = z;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x - 1][z] = DIR_WEST;
                costs[x - 1][z] = newCost;
            }

            // East
            if (x < 103 && parents[x + 1][z] == 0 && (collisionFlags[x + 1][z] & 0x12C0180) == 0) {
                bfsStepX[queueSize] = x + 1;
                bfsStepZ[queueSize] = z;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x + 1][z] = DIR_EAST;
                costs[x + 1][z] = newCost;
            }

            // South
            if (z > 0 && parents[x][z - 1] == 0 && (collisionFlags[x][z - 1] & 0x12C0102) == 0) {
                bfsStepX[queueSize] = x;
                bfsStepZ[queueSize] = z - 1;
                parents[x][z - 1] = DIR_SOUTH;;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                costs[x][z - 1] = newCost;
            }

            // North
            if (z < 103 && parents[x][z + 1] == 0 && (collisionFlags[x][z + 1] & 0x12C0120) == 0) {
                bfsStepX[queueSize] = x;
                bfsStepZ[queueSize] = z + 1;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x][z + 1] = DIR_NORTH;
                costs[x][z + 1] = newCost;
            }

            // Southwest
            if (x > 0 && z > 0 && parents[x - 1][z - 1] == 0 && (collisionFlags[x - 1][z - 1] & 0x12C010E) == 0 && (collisionFlags[x - 1][z] & 0x12C0108) == 0 && (collisionFlags[x][z - 1] & 0x12C0102) == 0) {
                bfsStepX[queueSize] = x - 1;
                bfsStepZ[queueSize] = z - 1;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x - 1][z - 1] = DIR_SOUTHWEST;
                costs[x - 1][z - 1] = newCost;
            }

            // Southeast
            if (x < 103 && z > 0 && parents[x + 1][z - 1] == 0 && (collisionFlags[x + 1][z - 1] & 0x12C0183) == 0 && (collisionFlags[x + 1][z] & 0x12C0180) == 0 && (collisionFlags[x][z - 1] & 0x12C0102) == 0) {
                bfsStepX[queueSize] = x + 1;
                bfsStepZ[queueSize] = z - 1;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x + 1][z - 1] = DIR_SOUTHEAST;
                costs[x + 1][z - 1] = newCost;
            }
            // Northwest
            if (x > 0 && z < 103 && parents[x - 1][z + 1] == 0 && (collisionFlags[x - 1][z + 1] & 0x12C0138) == 0 && (collisionFlags[x - 1][z] & 0x12C0108) == 0 && (collisionFlags[x][z + 1] & 0x12C0120) == 0) {
                bfsStepX[queueSize] = x - 1;
                bfsStepZ[queueSize] = z + 1;
                parents[x - 1][z + 1] = DIR_NORTHWEST;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                costs[x - 1][z + 1] = newCost;
            }

            // Northeast
            if (x < 103 && z < 103 && parents[x + 1][z + 1] == 0 && (collisionFlags[x + 1][z + 1] & 0x12C01E0) == 0 && (collisionFlags[x + 1][z] & 0x12C0180) == 0 && (collisionFlags[x][z + 1] & 0x12C0120) == 0) {
                bfsStepX[queueSize] = x + 1;
                bfsStepZ[queueSize] = z + 1;
                parents[x + 1][z + 1] = DIR_NORTHEAST;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                costs[x + 1][z + 1] = newCost;
            }
        }

        tryMoveNearest = 0;
        @Pc(839) int minCost;

        // If exact path not found, try to find nearest reachable tile
        if (!foundPath) {
            if (!arg7) {
                return false;
            }

            newCost = DISTANCE_INFINITY;
            minCost = MAX_NEAREST_COST;

            // Search 10 tiles around target for nearest reachable tile
            for (@Pc(846) int searchX = arg0 - NEAREST_SEARCH_RADIUS; searchX <= arg0 + NEAREST_SEARCH_RADIUS; searchX++) {
                for (@Pc(856) int searchZ = arg3 - NEAREST_SEARCH_RADIUS; searchZ <= arg3 + NEAREST_SEARCH_RADIUS; searchZ++) {
                    if (searchX >= 0 && searchZ >= 0 && searchX < BUILD_AREA_SIZE && searchZ < BUILD_AREA_SIZE && costs[searchX][searchZ] < MAX_NEAREST_COST) {

                        // Calculate distance from tile to target rectangle
                        @Pc(894) int deltaZ = 0;
                        if (searchZ < arg3) {
                            deltaZ = arg3 - searchZ;
                        } else if (arg5 + arg3 - 1 < searchZ) {
                            deltaZ = searchZ + 1 - arg3 - arg5;
                        }


                        @Pc(927) int deltaX = 0;
                        if (searchX < arg0) {
                            deltaX = arg0 - searchX;
                        } else if (searchX > targetDepth + arg0 - 1) {
                            deltaX = searchX + 1 - targetDepth - arg0;
                        }

                        @Pc(968) int distanceSquared = deltaZ * deltaZ + deltaX * deltaX;

                        // Select closest tile or cheapest path if tied
                        if (distanceSquared < newCost || distanceSquared == newCost && costs[searchX][searchZ] < minCost) {
                            z = searchZ;
                            newCost = distanceSquared;
                            x = searchX;
                            minCost = costs[searchX][searchZ];
                        }
                    }
                }
            }

            if (newCost == DISTANCE_INFINITY) {
                return false; // No reachable tiles found
            }
            if (arg2 == x && z == locationAngle) {
                return false; // Already at nearest tile
            }

            tryMoveNearest = 1;
        }

        // Reconstruct path from target back to start
        @Pc(1032) byte pathLength = 0;
        bfsStepX[0] = x;
        queueTail = pathLength + 1;
        bfsStepZ[0] = z;

        newCost = minCost = parents[x][z];

        // Walk backwards through parent pointers
        while (arg2 != x || z != locationAngle) {
            if (minCost != newCost) {
                minCost = newCost;
                bfsStepX[queueTail] = x;
                bfsStepZ[queueTail++] = z;
            }

            // Decode direction and move to parent tile
            if ((newCost & DIR_WEST) != 0) {
                x++;
            } else if ((newCost & 0x8) != 0) {
                x--; // EAST
            }
            if ((newCost & 0x1) != 0) {
                z++; // SOUTH
            } else if ((newCost & 0x4) != 0) {
                z--; // NORTH
            }

            newCost = parents[x][z];
        }

        // Send path to server
        if (queueTail > 0) {
            ClientProt.method3502(queueTail, arg4);
            return true;
        } else if (arg4 == 1) {
            return false;
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(IIIIIZIIIIII)Z")
    public static boolean findPathMediumEntity(@OriginalArg(0) int targetWidth, @OriginalArg(1) int targetHeight, @OriginalArg(2) int arg2, @OriginalArg(3) int startX, @OriginalArg(4) int locationShape, @OriginalArg(5) boolean allowNearest, @OriginalArg(6) int targetX, @OriginalArg(7) int startZ, @OriginalArg(8) int targetZ, @OriginalArg(9) int moveType, @OriginalArg(11) int entitySize) {
        @Pc(3) int currentX;
        @Pc(8) int currentZ;

        // Init search grids
        for (currentX = 0; currentX < BUILD_AREA_SIZE; currentX++) {
            for (currentZ = 0; currentZ < BUILD_AREA_SIZE; currentZ++) {
                parents[currentX][currentZ] = 0;
                costs[currentX][currentZ] = COST_INFINITY;
            }
        }

        // Set start position
        parents[entitySize][startX] = PARENT_START_MARKER;
        costs[entitySize][startX] = 0;
        currentZ = startX;
        currentX = entitySize;

        @Pc(53) byte local53 = 0;
        bfsStepX[0] = entitySize;
        @Pc(59) boolean local59 = false;
        @Pc(61) int local61 = 0;
        @Pc(64) int local64 = local53 + 1;
        bfsStepZ[0] = startX;
        @Pc(71) int[][] local71 = collisionMaps[Player.currentLevel].flags;
        @Pc(193) int local193;
        while (local61 != local64) {
            currentX = bfsStepX[local61];
            currentZ = bfsStepZ[local61];
            local61 = local61 + 1 & 0xFFF;
            if (targetZ == currentX && locationShape == currentZ) {
                local59 = true;
                break;
            }
            if (targetHeight != 0) {
                if ((targetHeight < 5 || targetHeight == 10) && collisionMaps[Player.currentLevel].isAtWall(locationShape, currentX, currentZ, targetZ, targetHeight - 1, 2, startZ)) {
                    local59 = true;
                    break;
                }
                if (targetHeight < 10 && collisionMaps[Player.currentLevel].isAtWallDecor(locationShape, targetHeight - 1, targetZ, currentZ, 2, startZ, currentX)) {
                    local59 = true;
                    break;
                }
            }
            if (targetWidth != 0 && targetX != 0 && collisionMaps[Player.currentLevel].isInsideOrOutsideRect(targetZ, currentZ, currentX, 2, targetWidth, arg2, locationShape, targetX)) {
                local59 = true;
                break;
            }
            local193 = costs[currentX][currentZ] + 1;
            if (currentX > 0 && parents[currentX - 1][currentZ] == 0 && (local71[currentX - 1][currentZ] & 0x12C010E) == 0 && (local71[currentX - 1][currentZ + 1] & 0x12C0138) == 0) {
                bfsStepX[local64] = currentX - 1;
                bfsStepZ[local64] = currentZ;
                local64 = local64 + 1 & 0xFFF;
                parents[currentX - 1][currentZ] = 2;
                costs[currentX - 1][currentZ] = local193;
            }
            if (currentX < 102 && parents[currentX + 1][currentZ] == 0 && (local71[currentX + 2][currentZ] & 0x12C0183) == 0 && (local71[currentX + 2][currentZ + 1] & 0x12C01E0) == 0) {
                bfsStepX[local64] = currentX + 1;
                bfsStepZ[local64] = currentZ;
                local64 = local64 + 1 & 0xFFF;
                parents[currentX + 1][currentZ] = 8;
                costs[currentX + 1][currentZ] = local193;
            }
            if (currentZ > 0 && parents[currentX][currentZ - 1] == 0 && (local71[currentX][currentZ - 1] & 0x12C010E) == 0 && (local71[currentX + 1][currentZ - 1] & 0x12C0183) == 0) {
                bfsStepX[local64] = currentX;
                bfsStepZ[local64] = currentZ - 1;
                parents[currentX][currentZ - 1] = 1;
                costs[currentX][currentZ - 1] = local193;
                local64 = local64 + 1 & 0xFFF;
            }
            if (currentZ < 102 && parents[currentX][currentZ + 1] == 0 && (local71[currentX][currentZ + 2] & 0x12C0138) == 0 && (local71[currentX + 1][currentZ + 2] & 0x12C01E0) == 0) {
                bfsStepX[local64] = currentX;
                bfsStepZ[local64] = currentZ + 1;
                parents[currentX][currentZ + 1] = 4;
                local64 = local64 + 1 & 0xFFF;
                costs[currentX][currentZ + 1] = local193;
            }
            if (currentX > 0 && currentZ > 0 && parents[currentX - 1][currentZ - 1] == 0 && (local71[currentX - 1][currentZ] & 0x12C0138) == 0 && (local71[currentX - 1][currentZ - 1] & 0x12C010E) == 0 && (local71[currentX][currentZ - 1] & 0x12C0183) == 0) {
                bfsStepX[local64] = currentX - 1;
                bfsStepZ[local64] = currentZ - 1;
                parents[currentX - 1][currentZ - 1] = 3;
                costs[currentX - 1][currentZ - 1] = local193;
                local64 = local64 + 1 & 0xFFF;
            }
            if (currentX < 102 && currentZ > 0 && parents[currentX + 1][currentZ - 1] == 0 && (local71[currentX + 1][currentZ - 1] & 0x12C010E) == 0 && (local71[currentX + 2][currentZ - 1] & 0x12C0183) == 0 && (local71[currentX + 2][currentZ] & 0x12C01E0) == 0) {
                bfsStepX[local64] = currentX + 1;
                bfsStepZ[local64] = currentZ - 1;
                local64 = local64 + 1 & 0xFFF;
                parents[currentX + 1][currentZ - 1] = 9;
                costs[currentX + 1][currentZ - 1] = local193;
            }
            if (currentX > 0 && currentZ < 102 && parents[currentX - 1][currentZ + 1] == 0 && (local71[currentX - 1][currentZ + 1] & 0x12C010E) == 0 && (local71[currentX - 1][currentZ + 2] & 0x12C0138) == 0 && (local71[currentX][currentZ + 2] & 0x12C01E0) == 0) {
                bfsStepX[local64] = currentX - 1;
                bfsStepZ[local64] = currentZ + 1;
                parents[currentX - 1][currentZ + 1] = 6;
                costs[currentX - 1][currentZ + 1] = local193;
                local64 = local64 + 1 & 0xFFF;
            }
            if (currentX < 102 && currentZ < 102 && parents[currentX + 1][currentZ + 1] == 0 && (local71[currentX + 1][currentZ + 2] & 0x12C0138) == 0 && (local71[currentX + 2][currentZ + 2] & 0x12C01E0) == 0 && (local71[currentX + 2][currentZ + 1] & 0x12C0183) == 0) {
                bfsStepX[local64] = currentX + 1;
                bfsStepZ[local64] = currentZ + 1;
                local64 = local64 + 1 & 0xFFF;
                parents[currentX + 1][currentZ + 1] = 12;
                costs[currentX + 1][currentZ + 1] = local193;
            }
        }
        tryMoveNearest = 0;
        @Pc(921) int local921;
        if (!local59) {
            if (!allowNearest) {
                return false;
            }
            local193 = 1000;
            local921 = 100;
            for (@Pc(928) int local928 = targetZ - 10; local928 <= targetZ + 10; local928++) {
                for (@Pc(942) int local942 = locationShape - 10; local942 <= locationShape + 10; local942++) {
                    if (local928 >= 0 && local942 >= 0 && local928 < 104 && local942 < 104 && costs[local928][local942] < 100) {
                        @Pc(978) int local978 = 0;
                        @Pc(980) int local980 = 0;
                        if (local928 < targetZ) {
                            local978 = targetZ - local928;
                        } else if (local928 > targetWidth + targetZ - 1) {
                            local978 = local928 + 1 - targetWidth - targetZ;
                        }
                        if (locationShape > local942) {
                            local980 = locationShape - local942;
                        } else if (local942 > locationShape + targetX - 1) {
                            local980 = local942 + 1 - locationShape - targetX;
                        }
                        @Pc(1057) int local1057 = local978 * local978 + local980 * local980;
                        if (local1057 < local193 || local193 == local1057 && costs[local928][local942] < local921) {
                            currentZ = local942;
                            local921 = costs[local928][local942];
                            local193 = local1057;
                            currentX = local928;
                        }
                    }
                }
            }
            if (local193 == 1000) {
                return false;
            }
            if (entitySize == currentX && currentZ == startX) {
                return false;
            }
            tryMoveNearest = 1;
        }
        @Pc(1121) byte local1121 = 0;
        bfsStepX[0] = currentX;
        local61 = local1121 + 1;
        bfsStepZ[0] = currentZ;
        local193 = local921 = parents[currentX][currentZ];
        while (entitySize != currentX || startX != currentZ) {
            if (local921 != local193) {
                bfsStepX[local61] = currentX;
                bfsStepZ[local61++] = currentZ;
                local921 = local193;
            }
            if ((local193 & 0x2) != 0) {
                currentX++;
            } else if ((local193 & 0x8) != 0) {
                currentX--;
            }
            if ((local193 & 0x1) != 0) {
                currentZ++;
            } else if ((local193 & 0x4) != 0) {
                currentZ--;
            }
            local193 = parents[currentX][currentZ];
        }
        if (local61 > 0) {
            ClientProt.method3502(local61, moveType);
            return true;
        } else if (moveType == 1) {
            return false;
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "(IBIIIIIIIIIZI)Z")
    public static boolean findPathLargeEntity(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) boolean arg10, @OriginalArg(12) int arg11) {
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
        @Pc(76) int[][] local76 = collisionMaps[Player.currentLevel].flags;
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
                if ((arg6 < 5 || arg6 == 10) && collisionMaps[Player.currentLevel].isAtWall(arg0, local3, local10, arg5, arg6 - 1, arg4, arg3)) {
                    local71 = true;
                    break;
                }
                if (arg6 < 10 && collisionMaps[Player.currentLevel].isAtWallDecor(arg0, arg6 - 1, arg5, local10, arg4, arg3, local3)) {
                    local71 = true;
                    break;
                }
            }
            if (arg1 != 0 && arg9 != 0 && collisionMaps[Player.currentLevel].isInsideOrOutsideRect(arg5, local10, local3, arg4, arg1, arg7, arg0, arg9)) {
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
    public static boolean findPathToLoc(@OriginalArg(1) long locKey, @OriginalArg(2) int targetX, @OriginalArg(3) int targetZ) {
        // Decode loc properties from packed key
        @Pc(12) int shape = (int) locKey >> 14 & 0x1F;
        @Pc(24) int rotation = (int) locKey >> 20 & 0x3;
        @Pc(31) int locId = (int) (locKey >>> 32) & Integer.MAX_VALUE;

        if (shape == 10 || shape == 11 || shape == 22) {
            @Pc(46) LocType locType = LocTypeList.get(locId);
            @Pc(62) int width;
            @Pc(59) int length;

            // Swap dimensions if rotated 90 or 270 degrees
            if (rotation == 0 || rotation == 2) {
                length = locType.length;
                width = locType.width;
            } else {
                length = locType.width;
                width = locType.length;
            }

            // Get blocking sides and rotate based on angle
            @Pc(73) int blockSides = locType.blocksides;
            if (rotation != 0) {
                blockSides = (blockSides << rotation & 0xF) + (blockSides >> 4 - rotation);
            }
            findPath(PlayerList.self.movementQueueZ[0], 0, length, true, blockSides, targetZ, width, 0, 2, targetX, PlayerList.self.movementQueueX[0]);
        } else {
            // For wall/decor shapes, use shape directly
            findPath(PlayerList.self.movementQueueZ[0], rotation, 0, true, 0, targetZ, 0, shape + 1, 2, targetX, PlayerList.self.movementQueueX[0]);
        }

        // Set crosshair at click position
        Crosshair.y = Mouse.mouseClickY;
        Crosshair.CrosshairCycle = 0;
        Crosshair.CrosshairMode = 2;
        Crosshair.x = Mouse.mouseClickX;
        return true;
    }
}
