package com.jagex.game;

import com.jagex.core.constants.LocShapes;
import com.jagex.game.collision.CollisionMap;
import com.jagex.ui.component.Crosshair;
import com.jagex.entity.player.PlayerList;
import com.jagex.game.runetek4.config.loctype.LocTypeList;
import com.jagex.game.runetek4.config.loctype.LocType;
import com.jagex.entity.player.Player;
import com.jagex.input.Mouse;
import com.jagex.network.ClientProt;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PathFinder {

    private static final int BFS_QUEUE_SIZE = 4096;

    private static final int GRID_SIZE = 104;

    private static final int COST_INFINITY = 99999999;

    private static final int PARENT_NULL = 0;

    private static final int PARENT_START_MARKER = 99;

    private static final int QUEUE_INDEX_MASK = 0xFFF;

    private static final int DISTANCE_INFINITY = 1000;

    private static final int MAX_NEAREST_COST = 100;

    private static final int APPROXIMATE_RADIUS = 10;

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
    public static final int[] queueX = new int[BFS_QUEUE_SIZE];

    @OriginalMember(owner = "runetek4.client!gk", name = "c", descriptor = "[I")
    public static final int[] queueZ = new int[BFS_QUEUE_SIZE];

    @OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "[[I")
    public static final int[][] parents = new int[GRID_SIZE][GRID_SIZE];

    @OriginalMember(owner = "runetek4.client!nd", name = "q", descriptor = "[[I")
    public static final int[][] costs = new int[GRID_SIZE][GRID_SIZE];

    @OriginalMember(owner = "runetek4.client!s", name = "d", descriptor = "I")
    public static int tryMoveNearest = 0;

    @OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(IIIZIIIIIIII)Z")
    public static boolean findPath(@OriginalArg(0) int startZ, @OriginalArg(1) int angle, @OriginalArg(2) int targetLength, @OriginalArg(3) boolean allowNearest, @OriginalArg(4) int blockSides, @OriginalArg(6) int targetX, @OriginalArg(7) int targetWidth, @OriginalArg(8) int locShape, @OriginalArg(9) int moveType, @OriginalArg(10) int targetZ, @OriginalArg(11) int startX) {
        if (PlayerList.self.getSize() == 2) {
            return findPathMediumEntity(targetWidth, locShape, blockSides, startZ, targetZ, allowNearest, targetLength, angle, targetX, moveType, startX);
        } else if (PlayerList.self.getSize() <= 2) {
            return findPathMediumEntity(targetX, blockSides, startX, targetZ, moveType, targetLength, angle, allowNearest, locShape, startZ, targetWidth);
        } else {
            return findPathLargeEntity(targetZ, targetWidth, moveType, angle, PlayerList.self.getSize(), targetX, locShape, blockSides, startX, targetLength, allowNearest, startZ);
        }
    }

    @OriginalMember(owner = "client!di", name = "a", descriptor = "(IIIIIIIIZIII)Z")
    public static boolean findPathMediumEntity(@OriginalArg(0) int destX, @OriginalArg(1) int blockSides, @OriginalArg(2) int startX, @OriginalArg(4) int targetZ, @OriginalArg(5) int moveType, @OriginalArg(6) int targetLength, @OriginalArg(7) int angle, @OriginalArg(8) boolean approximate, @OriginalArg(9) int locshape, @OriginalArg(10) int startZ, @OriginalArg(11) int targetWidth) {
        @Pc(3) int x;
        @Pc(10) int z;

        // Init search grids
        for (x = 0; x < GRID_SIZE; x++) {
            for (z = 0; z < GRID_SIZE; z++) {
                parents[x][z] = PARENT_NULL;
                costs[x][z] = COST_INFINITY;
            }
        }

        // Set start position
        x = startX;
        parents[startX][startZ] = PARENT_START_MARKER;
        z = startZ;
        costs[startX][startZ] = 0;

        @Pc(51) byte queueHead = 0;
        @Pc(53) boolean foundPath = false;
        @Pc(64) int queueTail = 0;
        queueX[0] = startX;
        @Pc(71) int queueSize = queueHead + 1;
        queueZ[0] = startZ;
        @Pc(78) int[][] collisionFlags = collisionMaps[Player.currentLevel].flags;
        @Pc(198) int adjacentCost;

        // Main loop
        while (queueSize != queueTail) {
            z = queueZ[queueTail];
            x = queueX[queueTail];
            queueTail = queueTail + 1 & QUEUE_INDEX_MASK;

            // Check if reached target
            if (x == destX && z == targetZ) {
                foundPath = true;
                break;
            }

            // Check if at wall target
            if (locshape != LocShapes.WALL_STRAIGHT) {
                if ((locshape < LocShapes.WALLDECOR_STRAIGHT_OFFSET || locshape == LocShapes.CENTREPIECE_STRAIGHT) && collisionMaps[Player.currentLevel].isAtWall(targetZ, x, z, destX, locshape - 1, 1, angle)) {
                    foundPath = true;
                    break;
                }
                if (locshape < LocShapes.CENTREPIECE_STRAIGHT && collisionMaps[Player.currentLevel].isAtDiagonalWallDecor(targetZ, locshape - 1, destX, z, 1, angle, x)) {
                    foundPath = true;
                    break;
                }
            }

            // Check if inside/outside rectangular target
            if (targetWidth != 0 && targetLength != 0 && collisionMaps[Player.currentLevel].isInsideOrOutsideRect(destX, z, x, 1, targetWidth, blockSides, targetZ, targetLength)) {
                foundPath = true;
                break;
            }

            adjacentCost = costs[x][z] + 1;

            // Explore 8 adjacent tiles

            // West
            if (x > 0 && parents[x - 1][z] == 0 && (collisionFlags[x - 1][z] & 0x12C0108) == 0) {
                queueX[queueSize] = x - 1;
                queueZ[queueSize] = z;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x - 1][z] = DIR_WEST;
                costs[x - 1][z] = adjacentCost;
            }

            // East
            if (x < GRID_SIZE -1 && parents[x + 1][z] == 0 && (collisionFlags[x + 1][z] & 0x12C0180) == 0) {
                queueX[queueSize] = x + 1;
                queueZ[queueSize] = z;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x + 1][z] = DIR_EAST;
                costs[x + 1][z] = adjacentCost;
            }

            // South
            if (z > 0 && parents[x][z - 1] == 0 && (collisionFlags[x][z - 1] & 0x12C0102) == 0) {
                queueX[queueSize] = x;
                queueZ[queueSize] = z - 1;
                parents[x][z - 1] = DIR_SOUTH;;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                costs[x][z - 1] = adjacentCost;
            }

            // North
            if (z < GRID_SIZE -1 && parents[x][z + 1] == 0 && (collisionFlags[x][z + 1] & 0x12C0120) == 0) {
                queueX[queueSize] = x;
                queueZ[queueSize] = z + 1;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x][z + 1] = DIR_NORTH;
                costs[x][z + 1] = adjacentCost;
            }

            // Southwest
            if (x > 0 && z > 0 && parents[x - 1][z - 1] == 0 && (collisionFlags[x - 1][z - 1] & 0x12C010E) == 0 && (collisionFlags[x - 1][z] & 0x12C0108) == 0 && (collisionFlags[x][z - 1] & 0x12C0102) == 0) {
                queueX[queueSize] = x - 1;
                queueZ[queueSize] = z - 1;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x - 1][z - 1] = DIR_SOUTHWEST;
                costs[x - 1][z - 1] = adjacentCost;
            }

            // Southeast
            if (x < GRID_SIZE -1 && z > 0 && parents[x + 1][z - 1] == 0 && (collisionFlags[x + 1][z - 1] & 0x12C0183) == 0 && (collisionFlags[x + 1][z] & 0x12C0180) == 0 && (collisionFlags[x][z - 1] & 0x12C0102) == 0) {
                queueX[queueSize] = x + 1;
                queueZ[queueSize] = z - 1;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                parents[x + 1][z - 1] = DIR_SOUTHEAST;
                costs[x + 1][z - 1] = adjacentCost;
            }
            // Northwest
            if (x > 0 && z < GRID_SIZE -1 && parents[x - 1][z + 1] == 0 && (collisionFlags[x - 1][z + 1] & 0x12C0138) == 0 && (collisionFlags[x - 1][z] & 0x12C0108) == 0 && (collisionFlags[x][z + 1] & 0x12C0120) == 0) {
                queueX[queueSize] = x - 1;
                queueZ[queueSize] = z + 1;
                parents[x - 1][z + 1] = DIR_NORTHWEST;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                costs[x - 1][z + 1] = adjacentCost;
            }

            // Northeast
            if (x < GRID_SIZE -1 && z < GRID_SIZE -1 && parents[x + 1][z + 1] == 0 && (collisionFlags[x + 1][z + 1] & 0x12C01E0) == 0 && (collisionFlags[x + 1][z] & 0x12C0180) == 0 && (collisionFlags[x][z + 1] & 0x12C0120) == 0) {
                queueX[queueSize] = x + 1;
                queueZ[queueSize] = z + 1;
                parents[x + 1][z + 1] = DIR_NORTHEAST;
                queueSize = queueSize + 1 & QUEUE_INDEX_MASK;
                costs[x + 1][z + 1] = adjacentCost;
            }
        }

        tryMoveNearest = 0;
        @Pc(839) int minCost;

        // If exact path not found, try to find nearest reachable tile
        if (!foundPath) {
            if (!approximate) {
                return false;
            }

            adjacentCost = DISTANCE_INFINITY;
            minCost = MAX_NEAREST_COST;

            // Search 10 tiles around target for nearest reachable tile
            for (@Pc(846) int searchX = destX - APPROXIMATE_RADIUS; searchX <= destX + APPROXIMATE_RADIUS; searchX++) {
                for (@Pc(856) int searchZ = targetZ - APPROXIMATE_RADIUS; searchZ <= targetZ + APPROXIMATE_RADIUS; searchZ++) {
                    if (searchX >= 0 && searchZ >= 0 && searchX < GRID_SIZE && searchZ < GRID_SIZE && costs[searchX][searchZ] < MAX_NEAREST_COST) {

                        // Calculate distance from tile to target rectangle
                        @Pc(894) int deltaZ = 0;
                        if (searchZ < targetZ) {
                            deltaZ = targetZ - searchZ;
                        } else if (targetLength + targetZ - 1 < searchZ) {
                            deltaZ = searchZ + 1 - targetZ - targetLength;
                        }


                        @Pc(927) int deltaX = 0;
                        if (searchX < destX) {
                            deltaX = destX - searchX;
                        } else if (searchX > targetWidth + destX - 1) {
                            deltaX = searchX + 1 - targetWidth - destX;
                        }

                        @Pc(968) int distanceSquared = deltaZ * deltaZ + deltaX * deltaX;

                        // Select closest tile or cheapest path if tied
                        if (distanceSquared < adjacentCost || distanceSquared == adjacentCost && costs[searchX][searchZ] < minCost) {
                            z = searchZ;
                            adjacentCost = distanceSquared;
                            x = searchX;
                            minCost = costs[searchX][searchZ];
                        }
                    }
                }
            }

            if (adjacentCost == DISTANCE_INFINITY) {
                return false; // No reachable tiles found
            }
            if (startX == x && z == startZ) {
                return false; // Already at nearest tile
            }

            tryMoveNearest = 1;
        }

        // Reconstruct path from target back to start
        @Pc(1032) byte pathLength = 0;
        queueX[0] = x;
        queueTail = pathLength + 1;
        queueZ[0] = z;

        adjacentCost = minCost = parents[x][z];

        // Walk backwards through parent pointers
        while (startX != x || z != startZ) {
            if (minCost != adjacentCost) {
                minCost = adjacentCost;
                queueX[queueTail] = x;
                queueZ[queueTail++] = z;
            }

            // Decode direction and move to parent tile
            if ((adjacentCost & DIR_WEST) != 0) {
                x++;
            } else if ((adjacentCost & DIR_EAST) != 0) {
                x--; // EAST
            }
            if ((adjacentCost & DIR_SOUTH) != 0) {
                z++; // SOUTH
            } else if ((adjacentCost & DIR_NORTH) != 0) {
                z--; // NORTH
            }

            adjacentCost = parents[x][z];
        }

        // Send path to server
        if (queueTail > 0) {
            ClientProt.method3502(queueTail, moveType);
            return true;
        } else if (moveType == 1) {
            return false;
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(IIIIIZIIIIII)Z")
    public static boolean findPathMediumEntity(@OriginalArg(0) int targetWidth, @OriginalArg(1) int locShape, @OriginalArg(2) int blockSides, @OriginalArg(3) int startZ, @OriginalArg(4) int targetZ, @OriginalArg(5) boolean allowNearest, @OriginalArg(6) int targetLength, @OriginalArg(7) int angle, @OriginalArg(8) int targetX, @OriginalArg(9) int moveType, @OriginalArg(11) int startX) {
        @Pc(3) int currentX;
        @Pc(8) int currentZ;

        // Init search grids
        for (currentX = 0; currentX < GRID_SIZE; currentX++) {
            for (currentZ = 0; currentZ < GRID_SIZE; currentZ++) {
                parents[currentX][currentZ] = 0;
                costs[currentX][currentZ] = COST_INFINITY;
            }
        }

        // Set start position
        parents[startX][startZ] = PARENT_START_MARKER;
        costs[startX][startZ] = 0;
        currentZ = startZ;
        currentX = startX;

        @Pc(53) byte local53 = 0;
        queueX[0] = startX;
        @Pc(59) boolean local59 = false;
        @Pc(61) int queueReadPointer = 0;
        @Pc(64) int queueWritePointer = local53 + 1;
        queueZ[0] = startZ;
        @Pc(71) int[][] flags = collisionMaps[Player.currentLevel].flags;
        @Pc(193) int local193;
        while (queueReadPointer != queueWritePointer) {
            currentX = queueX[queueReadPointer];
            currentZ = queueZ[queueReadPointer];
            queueReadPointer = queueReadPointer + 1 & 0xFFF;
            if (targetX == currentX && targetZ == currentZ) {
                local59 = true;
                break;
            }
            if (locShape != 0) {
                if ((locShape < 5 || locShape == 10) && collisionMaps[Player.currentLevel].isAtWall(targetZ, currentX, currentZ, targetX, locShape - 1, 2, angle)) {
                    local59 = true;
                    break;
                }
                if (locShape < 10 && collisionMaps[Player.currentLevel].isAtDiagonalWallDecor(targetZ, locShape - 1, targetX, currentZ, 2, angle, currentX)) {
                    local59 = true;
                    break;
                }
            }
            if (targetWidth != 0 && targetLength != 0 && collisionMaps[Player.currentLevel].isInsideOrOutsideRect(targetX, currentZ, currentX, 2, targetWidth, blockSides, targetZ, targetLength)) {
                local59 = true;
                break;
            }
            local193 = costs[currentX][currentZ] + 1;
            if (currentX > 0 && parents[currentX - 1][currentZ] == 0 && (flags[currentX - 1][currentZ] & 0x12C010E) == 0 && (flags[currentX - 1][currentZ + 1] & 0x12C0138) == 0) {
                queueX[queueWritePointer] = currentX - 1;
                queueZ[queueWritePointer] = currentZ;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
                parents[currentX - 1][currentZ] = 2;
                costs[currentX - 1][currentZ] = local193;
            }
            if (currentX < 102 && parents[currentX + 1][currentZ] == 0 && (flags[currentX + 2][currentZ] & 0x12C0183) == 0 && (flags[currentX + 2][currentZ + 1] & 0x12C01E0) == 0) {
                queueX[queueWritePointer] = currentX + 1;
                queueZ[queueWritePointer] = currentZ;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
                parents[currentX + 1][currentZ] = 8;
                costs[currentX + 1][currentZ] = local193;
            }
            if (currentZ > 0 && parents[currentX][currentZ - 1] == 0 && (flags[currentX][currentZ - 1] & 0x12C010E) == 0 && (flags[currentX + 1][currentZ - 1] & 0x12C0183) == 0) {
                queueX[queueWritePointer] = currentX;
                queueZ[queueWritePointer] = currentZ - 1;
                parents[currentX][currentZ - 1] = 1;
                costs[currentX][currentZ - 1] = local193;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
            }
            if (currentZ < 102 && parents[currentX][currentZ + 1] == 0 && (flags[currentX][currentZ + 2] & 0x12C0138) == 0 && (flags[currentX + 1][currentZ + 2] & 0x12C01E0) == 0) {
                queueX[queueWritePointer] = currentX;
                queueZ[queueWritePointer] = currentZ + 1;
                parents[currentX][currentZ + 1] = 4;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
                costs[currentX][currentZ + 1] = local193;
            }
            if (currentX > 0 && currentZ > 0 && parents[currentX - 1][currentZ - 1] == 0 && (flags[currentX - 1][currentZ] & 0x12C0138) == 0 && (flags[currentX - 1][currentZ - 1] & 0x12C010E) == 0 && (flags[currentX][currentZ - 1] & 0x12C0183) == 0) {
                queueX[queueWritePointer] = currentX - 1;
                queueZ[queueWritePointer] = currentZ - 1;
                parents[currentX - 1][currentZ - 1] = 3;
                costs[currentX - 1][currentZ - 1] = local193;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
            }
            if (currentX < 102 && currentZ > 0 && parents[currentX + 1][currentZ - 1] == 0 && (flags[currentX + 1][currentZ - 1] & 0x12C010E) == 0 && (flags[currentX + 2][currentZ - 1] & 0x12C0183) == 0 && (flags[currentX + 2][currentZ] & 0x12C01E0) == 0) {
                queueX[queueWritePointer] = currentX + 1;
                queueZ[queueWritePointer] = currentZ - 1;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
                parents[currentX + 1][currentZ - 1] = 9;
                costs[currentX + 1][currentZ - 1] = local193;
            }
            if (currentX > 0 && currentZ < 102 && parents[currentX - 1][currentZ + 1] == 0 && (flags[currentX - 1][currentZ + 1] & 0x12C010E) == 0 && (flags[currentX - 1][currentZ + 2] & 0x12C0138) == 0 && (flags[currentX][currentZ + 2] & 0x12C01E0) == 0) {
                queueX[queueWritePointer] = currentX - 1;
                queueZ[queueWritePointer] = currentZ + 1;
                parents[currentX - 1][currentZ + 1] = 6;
                costs[currentX - 1][currentZ + 1] = local193;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
            }
            if (currentX < 102 && currentZ < 102 && parents[currentX + 1][currentZ + 1] == 0 && (flags[currentX + 1][currentZ + 2] & 0x12C0138) == 0 && (flags[currentX + 2][currentZ + 2] & 0x12C01E0) == 0 && (flags[currentX + 2][currentZ + 1] & 0x12C0183) == 0) {
                queueX[queueWritePointer] = currentX + 1;
                queueZ[queueWritePointer] = currentZ + 1;
                queueWritePointer = queueWritePointer + 1 & 0xFFF;
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
            for (@Pc(928) int local928 = targetX - 10; local928 <= targetX + 10; local928++) {
                for (@Pc(942) int local942 = targetZ - 10; local942 <= targetZ + 10; local942++) {
                    if (local928 >= 0 && local942 >= 0 && local928 < 104 && local942 < 104 && costs[local928][local942] < 100) {
                        @Pc(978) int local978 = 0;
                        @Pc(980) int local980 = 0;
                        if (local928 < targetX) {
                            local978 = targetX - local928;
                        } else if (local928 > targetWidth + targetX - 1) {
                            local978 = local928 + 1 - targetWidth - targetX;
                        }
                        if (targetZ > local942) {
                            local980 = targetZ - local942;
                        } else if (local942 > targetZ + targetLength - 1) {
                            local980 = local942 + 1 - targetZ - targetLength;
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
            if (startX == currentX && currentZ == startZ) {
                return false;
            }
            tryMoveNearest = 1;
        }
        @Pc(1121) byte local1121 = 0;
        queueX[0] = currentX;
        queueReadPointer = local1121 + 1;
        queueZ[0] = currentZ;
        local193 = local921 = parents[currentX][currentZ];
        while (startX != currentX || startZ != currentZ) {
            if (local921 != local193) {
                queueX[queueReadPointer] = currentX;
                queueZ[queueReadPointer++] = currentZ;
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
        if (queueReadPointer > 0) {
            ClientProt.method3502(queueReadPointer, moveType);
            return true;
        } else if (moveType == 1) {
            return false;
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "(IBIIIIIIIIIZI)Z")
    public static boolean findPathLargeEntity(@OriginalArg(0) int targetZ, @OriginalArg(2) int targetWidth, @OriginalArg(3) int moveType, @OriginalArg(4) int angle, @OriginalArg(5) int entitySize, @OriginalArg(6) int targetX, @OriginalArg(7) int locShape, @OriginalArg(8) int blockSides, @OriginalArg(9) int startX, @OriginalArg(10) int targetLength, @OriginalArg(11) boolean allowNearest, @OriginalArg(12) int startZ) {
        @Pc(3) int local3;
        @Pc(10) int local10;
        for (local3 = 0; local3 < 104; local3++) {
            for (local10 = 0; local10 < 104; local10++) {
                parents[local3][local10] = 0;
                costs[local3][local10] = 99999999;
            }
        }
        local3 = startX;
        local10 = startZ;
        parents[startX][startZ] = 99;
        costs[startX][startZ] = 0;
        @Pc(53) byte local53 = 0;
        queueX[0] = startX;
        @Pc(65) int local65 = local53 + 1;
        queueZ[0] = startZ;
        @Pc(69) int local69 = 0;
        @Pc(71) boolean local71 = false;
        @Pc(76) int[][] local76 = collisionMaps[Player.currentLevel].flags;
        @Pc(201) int local201;
        @Pc(242) int local242;
        label397: while (local69 != local65) {
            local3 = queueX[local69];
            local10 = queueZ[local69];
            local69 = local69 + 1 & 0xFFF;
            if (targetX == local3 && local10 == targetZ) {
                local71 = true;
                break;
            }
            if (locShape != 0) {
                if ((locShape < 5 || locShape == 10) && collisionMaps[Player.currentLevel].isAtWall(targetZ, local3, local10, targetX, locShape - 1, entitySize, angle)) {
                    local71 = true;
                    break;
                }
                if (locShape < 10 && collisionMaps[Player.currentLevel].isAtDiagonalWallDecor(targetZ, locShape - 1, targetX, local10, entitySize, angle, local3)) {
                    local71 = true;
                    break;
                }
            }
            if (targetWidth != 0 && targetLength != 0 && collisionMaps[Player.currentLevel].isInsideOrOutsideRect(targetX, local10, local3, entitySize, targetWidth, blockSides, targetZ, targetLength)) {
                local71 = true;
                break;
            }
            local201 = costs[local3][local10] + 1;
            if (local3 > 0 && parents[local3 - 1][local10] == 0 && (local76[local3 - 1][local10] & 0x12C010E) == 0 && (local76[local3 - 1][entitySize + local10 - 1] & 0x12C0138) == 0) {
                local242 = 1;
                while (true) {
                    if (entitySize - 1 <= local242) {
                        queueX[local65] = local3 - 1;
                        queueZ[local65] = local10;
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
            if (local3 < 102 && parents[local3 + 1][local10] == 0 && (local76[local3 + entitySize][local10] & 0x12C0183) == 0 && (local76[entitySize + local3][local10 + entitySize - 1] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (local242 >= entitySize - 1) {
                        queueX[local65] = local3 + 1;
                        queueZ[local65] = local10;
                        parents[local3 + 1][local10] = 8;
                        costs[local3 + 1][local10] = local201;
                        local65 = local65 + 1 & 0xFFF;
                        break;
                    }
                    if ((local76[entitySize + local3][local10 + local242] & 0x12C01E3) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local10 > 0 && parents[local3][local10 - 1] == 0 && (local76[local3][local10 - 1] & 0x12C010E) == 0 && (local76[entitySize + local3 - 1][local10 - 1] & 0x12C0183) == 0) {
                local242 = 1;
                while (true) {
                    if (entitySize - 1 <= local242) {
                        queueX[local65] = local3;
                        queueZ[local65] = local10 - 1;
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
            if (local10 < 102 && parents[local3][local10 + 1] == 0 && (local76[local3][local10 + entitySize] & 0x12C0138) == 0 && (local76[local3 + entitySize - 1][entitySize + local10] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (local242 >= entitySize - 1) {
                        queueX[local65] = local3;
                        queueZ[local65] = local10 + 1;
                        parents[local3][local10 + 1] = 4;
                        costs[local3][local10 + 1] = local201;
                        local65 = local65 + 1 & 0xFFF;
                        break;
                    }
                    if ((local76[local3 + local242][entitySize + local10] & 0x12C01F8) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 > 0 && local10 > 0 && parents[local3 - 1][local10 - 1] == 0 && (local76[local3 - 1][entitySize + local10 - 1 - 1] & 0x12C0138) == 0 && (local76[local3 - 1][local10 - 1] & 0x12C010E) == 0 && (local76[entitySize + local3 - 1 - 1][local10 - 1] & 0x12C0183) == 0) {
                local242 = 1;
                while (true) {
                    if (entitySize - 1 <= local242) {
                        queueX[local65] = local3 - 1;
                        queueZ[local65] = local10 - 1;
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
            if (local3 < 102 && local10 > 0 && parents[local3 + 1][local10 - 1] == 0 && (local76[local3 + 1][local10 - 1] & 0x12C010E) == 0 && (local76[entitySize + local3][local10 - 1] & 0x12C0183) == 0 && (local76[local3 + entitySize][local10 + entitySize - 1 - 1] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (local242 >= entitySize - 1) {
                        queueX[local65] = local3 + 1;
                        queueZ[local65] = local10 - 1;
                        local65 = local65 + 1 & 0xFFF;
                        parents[local3 + 1][local10 - 1] = 9;
                        costs[local3 + 1][local10 - 1] = local201;
                        break;
                    }
                    if ((local76[local3 + entitySize][local10 + local242 - 1] & 0x12C01E3) != 0 || (local76[local242 + local3 + 1][local10 - 1] & 0x12C018F) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 > 0 && local10 < 102 && parents[local3 - 1][local10 + 1] == 0 && (local76[local3 - 1][local10 + 1] & 0x12C010E) == 0 && (local76[local3 - 1][local10 + entitySize] & 0x12C0138) == 0 && (local76[local3][local10 + entitySize] & 0x12C01E0) == 0) {
                local242 = 1;
                while (true) {
                    if (entitySize - 1 <= local242) {
                        queueX[local65] = local3 - 1;
                        queueZ[local65] = local10 + 1;
                        local65 = local65 + 1 & 0xFFF;
                        parents[local3 - 1][local10 + 1] = 6;
                        costs[local3 - 1][local10 + 1] = local201;
                        break;
                    }
                    if ((local76[local3 - 1][local10 + local242 + 1] & 0x12C013E) != 0 || (local76[local242 + local3 - 1][entitySize + local10] & 0x12C01F8) != 0) {
                        break;
                    }
                    local242++;
                }
            }
            if (local3 < 102 && local10 < 102 && parents[local3 + 1][local10 + 1] == 0 && (local76[local3 + 1][local10 + entitySize] & 0x12C0138) == 0 && (local76[local3 + entitySize][local10 + entitySize] & 0x12C01E0) == 0 && (local76[entitySize + local3][local10 + 1] & 0x12C0183) == 0) {
                for (local242 = 1; local242 < entitySize - 1; local242++) {
                    if ((local76[local242 + local3 + 1][local10 + entitySize] & 0x12C01F8) != 0 || (local76[entitySize + local3][local242 + local10 + 1] & 0x12C01E3) != 0) {
                        continue label397;
                    }
                }
                queueX[local65] = local3 + 1;
                queueZ[local65] = local10 + 1;
                parents[local3 + 1][local10 + 1] = 12;
                costs[local3 + 1][local10 + 1] = local201;
                local65 = local65 + 1 & 0xFFF;
            }
        }
        tryMoveNearest = 0;
        if (!local71) {
            if (!allowNearest) {
                return false;
            }
            local201 = 1000;
            local242 = 100;
            for (@Pc(1247) int local1247 = targetX - 10; local1247 <= targetX + 10; local1247++) {
                for (@Pc(1257) int local1257 = targetZ - 10; local1257 <= targetZ + 10; local1257++) {
                    if (local1247 >= 0 && local1257 >= 0 && local1247 < 104 && local1257 < 104 && costs[local1247][local1257] < 100) {
                        @Pc(1295) int local1295 = 0;
                        if (targetX > local1247) {
                            local1295 = targetX - local1247;
                        } else if (targetX + targetWidth - 1 < local1247) {
                            local1295 = local1247 + 1 - targetWidth - targetX;
                        }
                        @Pc(1334) int local1334 = 0;
                        if (local1257 < targetZ) {
                            local1334 = targetZ - local1257;
                        } else if (targetZ + targetLength - 1 < local1257) {
                            local1334 = local1257 + 1 - targetZ - targetLength;
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
            if (local3 == startX && startZ == local10) {
                return false;
            }
            tryMoveNearest = 1;
        }
        @Pc(1438) byte local1438 = 0;
        queueX[0] = local3;
        local69 = local1438 + 1;
        queueZ[0] = local10;
        local201 = local242 = parents[local3][local10];
        while (local3 != startX || startZ != local10) {
            if (local242 != local201) {
                queueX[local69] = local3;
                local242 = local201;
                queueZ[local69++] = local10;
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
            ClientProt.method3502(local69, moveType);
            return true;
        } else if (moveType == 1) {
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
