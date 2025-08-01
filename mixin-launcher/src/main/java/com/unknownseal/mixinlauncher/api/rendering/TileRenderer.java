package com.unknownseal.mixinlauncher.api.rendering;

import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.raster.SoftwareRaster;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.scene.tile.ShapedTile;
import com.jagex.runetek4.scene.tile.Tile;
import com.unknownseal.mixinlauncher.api.rendering.TileProjection.ProjectionResult;

/**
 * Handles the actual drawing and rendering of tile borders and highlights.
 */
public class TileRenderer {

    public static void highlightTile(int plane, int tileX, int tileZ, int color) {
        if (GlRenderer.enabled) {
            return;
        }

        drawTileBorder(plane, tileX, tileZ, color);
    }

    public static void drawTileBorder(int plane, int tileX, int tileZ, int color) {
        if (SceneGraph.tiles == null || SceneGraph.tiles[plane] == null) {
            return;
        }

        Tile tile = SceneGraph.tiles[plane][tileX][tileZ];
        if (tile == null) {
            return;
        }

        if (tile.shapedTile != null) {
            drawShapedTileBorder(tile.shapedTile, color);
        } else {
            drawSimpleTileBorder(tileX, tileZ, plane, color);
        }
    }

    public static void drawShapedTileBorder(ShapedTile shapedTile, int color) {
        int[] vertexX = shapedTile.anIntArray168;
        int[] vertexY = shapedTile.anIntArray160;
        int[] vertexZ = shapedTile.anIntArray163;

        if (vertexX == null || vertexY == null || vertexZ == null) {
            return;
        }

        ProjectionResult result = TileProjection.projectMultiplePoints(vertexX, vertexY, vertexZ);

        if (result.validVertices >= 3) {
            for (int i = 0; i < result.validVertices; i++) {
                int nextIndex = (i + 1) % result.validVertices;
                SoftwareRaster.drawDiagonalLine(
                    result.screenX[i], result.screenY[i], 
                    result.screenX[nextIndex], result.screenY[nextIndex], 
                    color
                );
            }
        }
    }

    public static void drawSimpleTileBorder(int tileX, int tileZ, int plane, int color) {
        int tileSize = 128;

        int worldBaseX = tileX * tileSize;
        int worldBaseZ = tileZ * tileSize;

        int swHeight = SceneGraph.getTileHeight(plane, worldBaseX, worldBaseZ);
        int seHeight = SceneGraph.getTileHeight(plane, worldBaseX + tileSize, worldBaseZ);
        int nwHeight = SceneGraph.getTileHeight(plane, worldBaseX, worldBaseZ + tileSize);
        int neHeight = SceneGraph.getTileHeight(plane, worldBaseX + tileSize, worldBaseZ + tileSize);

        int[] cornerX = {worldBaseX, worldBaseX + tileSize, worldBaseX, worldBaseX + tileSize};
        int[] cornerZ = {worldBaseZ, worldBaseZ, worldBaseZ + tileSize, worldBaseZ + tileSize};
        int[] cornerY = {swHeight, seHeight, nwHeight, neHeight};

        ProjectionResult result = TileProjection.projectMultiplePoints(cornerX, cornerY, cornerZ);

        if (result.validVertices == 4) {
            SoftwareRaster.drawDiagonalLine(result.screenX[0], result.screenY[0], result.screenX[1], result.screenY[1], color);
            SoftwareRaster.drawDiagonalLine(result.screenX[1], result.screenY[1], result.screenX[3], result.screenY[3], color);
            SoftwareRaster.drawDiagonalLine(result.screenX[3], result.screenY[3], result.screenX[2], result.screenY[2], color);
            SoftwareRaster.drawDiagonalLine(result.screenX[2], result.screenY[2], result.screenX[0], result.screenY[0], color);
        }
    }
}