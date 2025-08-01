package com.unknownseal.mixinlauncher.api.rendering;

import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.scene.SceneGraph;

/**
 * Handles coordinate transformations and camera projections for tiles.
 */
public class TileProjection {

    public static int[] projectTileToScreen(int tileX, int tileZ, int plane) {
        int worldX = tileX * 128 + 64;
        int worldZ = tileZ * 128 + 64;
        int worldY = SceneGraph.getTileHeight(plane, worldX, worldZ);
        
        return projectWorldToScreen(worldX, worldY, worldZ);
    }

    public static int[] projectWorldToScreen(int worldX, int worldY, int worldZ) {
        int relativeX = worldX - SceneGraph.eyeX;
        int relativeY = worldY - SceneGraph.eyeY;
        int relativeZ = worldZ - SceneGraph.eyeZ;

        int sinYaw = SceneGraph.anInt2886;
        int cosYaw = SceneGraph.anInt3038;
        int sinPitch = SceneGraph.anInt5205;
        int cosPitch = SceneGraph.anInt2222;

        int rotatedX = relativeZ * sinPitch + relativeX * cosPitch >> 16;
        int rotatedZ = relativeZ * cosPitch - relativeX * sinPitch >> 16;
        int transformedY = relativeY * cosYaw - rotatedZ * sinYaw >> 16;
        int depth = relativeY * sinYaw + rotatedZ * cosYaw >> 16;
        
        if (depth < 50) {
            return null;
        }

        int screenX = Rasterizer.centerX + (rotatedX << 9) / depth;
        int screenY = Rasterizer.centerY + (transformedY << 9) / depth;
        
        return new int[]{screenX, screenY};
    }

    public static ProjectionResult projectMultiplePoints(int[] worldX, int[] worldY, int[] worldZ) {
        if (worldX == null || worldY == null || worldZ == null || 
            worldX.length != worldY.length || worldY.length != worldZ.length) {
            return new ProjectionResult(new int[0], new int[0], 0);
        }

        int vertexCount = worldX.length;
        int[] screenX = new int[vertexCount];
        int[] screenY = new int[vertexCount];
        int validVertices = 0;

        int sinYaw = SceneGraph.anInt2886;
        int cosYaw = SceneGraph.anInt3038;
        int sinPitch = SceneGraph.anInt5205;
        int cosPitch = SceneGraph.anInt2222;

        for (int i = 0; i < vertexCount; i++) {
            int vertexA = worldX[i] - SceneGraph.eyeX;
            int relativeY = worldY[i] - SceneGraph.eyeY;
            int local29 = worldZ[i] - SceneGraph.eyeZ;

            int local39 = local29 * sinPitch + vertexA * cosPitch >> 16;
            int rotatedZ = local29 * cosPitch - vertexA * sinPitch >> 16;
            int transformedY = relativeY * cosYaw - rotatedZ * sinYaw >> 16;
            int depth = relativeY * sinYaw + rotatedZ * cosYaw >> 16;

            if (depth >= 50) {
                screenX[validVertices] = Rasterizer.centerX + (local39 << 9) / depth;
                screenY[validVertices] = Rasterizer.centerY + (transformedY << 9) / depth;
                validVertices++;
            }
        }

        return new ProjectionResult(screenX, screenY, validVertices);
    }

    public static class ProjectionResult {
        public final int[] screenX;
        public final int[] screenY;
        public final int validVertices;

        public ProjectionResult(int[] screenX, int[] screenY, int validVertices) {
            this.screenX = screenX;
            this.screenY = screenY;
            this.validVertices = validVertices;
        }
    }
}