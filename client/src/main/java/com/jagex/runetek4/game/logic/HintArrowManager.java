package com.jagex.runetek4.game.logic;

import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.entity.player.Player;
import com.jagex.runetek4.graphics.gl.GlModel;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.model.Model;
import com.jagex.runetek4.graphics.model.RawModel;
import com.jagex.runetek4.graphics.model.SoftwareModel;
import com.jagex.runetek4.core.node.SoftLruHashTable;
import com.jagex.runetek4.scene.SceneGraph;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class HintArrowManager {

    // Cache of hint arrow models
    // Maximum 4 cached
    @OriginalMember(owner = "runetek4.client!ih", name = "l", descriptor = "Lclient!n;")
    public static final SoftLruHashTable models = new SoftLruHashTable(4);

    // Cleans aged entries from cache to free memory
    @OriginalMember(owner = "runetek4.client!mh", name = "c", descriptor = "(II)V")
    public static void clean() {
        models.clean(5);
    }

    // Removes soft reference entries from cache
    @OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(I)V")
    public static void removeSoft() {
        models.removeSoft();
    }

    // Clear all models from cache
    @OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(I)V")
    public static void clear() {
        models.clean();
    }

    // Retrieve hint arrow model
    @OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(ZIIIILclient!ak;I)Lclient!ak;")
    public static Model getModel(@OriginalArg(1) int yRotation, @OriginalArg(2) int worldX, @OriginalArg(3) int modelId, @OriginalArg(4) int worldZ, @OriginalArg(5) Model baseModel, @OriginalArg(6) int baseHeight) {
        @Pc(4) long cacheKey = (long) modelId;
        @Pc(10) Model model = (Model) models.get(cacheKey);

        // Load model from cache if not already loaded
        if (model == null) {
            @Pc(22) RawModel rawModel = RawModel.create(Client.js5Archive7, modelId);
            if (rawModel == null) {
                return null;
            }

            // Create lit model with hint arrow lightning parameters
            int HINT_ARROW_AMBIENT_LIGHT = 64;
            int HINT_ARROW_CONTRAST = 768;
            int HINT_ARROW_LIGHT_X = -50;
            int HINT_ARROW_LIGHT_Y = -10;
            int HINT_ARROW_LIGHT_Z = -50;
            model = rawModel.createModel(HINT_ARROW_AMBIENT_LIGHT, HINT_ARROW_CONTRAST, HINT_ARROW_LIGHT_X, HINT_ARROW_LIGHT_Y, HINT_ARROW_LIGHT_Z);
            models.put(model, cacheKey);
        }

        // Get target entitys bounding box for positioning
        @Pc(42) int minX = baseModel.getMinX();
        @Pc(45) int maxX = baseModel.getMaxX();
        @Pc(48) int minZ = baseModel.getMinZ();
        @Pc(51) int maxZ = baseModel.getMaxZ();

        // Create independent copy for positioning
        model = model.copy(true, true, true);

        // Apply rotation if needed
        if (yRotation != 0) {
            model.rotateY(yRotation);
        }

        @Pc(94) int vertexIndex;

        // Make the arrow model fit the terrain by adjusting vertex heights
        if (GlRenderer.enabled) {
            //OpenGL rendering

            @Pc(68) GlModel glModel = (GlModel) model;

            // Check if terrain varies across the tergets bounds
            if (baseHeight != SceneGraph.getTileHeight(Player.currentLevel, worldZ + minX, worldX + minZ) || baseHeight != SceneGraph.getTileHeight(Player.currentLevel, worldZ + maxX, maxZ + worldX)) {

                // Adjust each vertex to match terrain elevation
                for (vertexIndex = 0; vertexIndex < glModel.vertexCount; vertexIndex++) {
                    glModel.vertexY[vertexIndex] += SceneGraph.getTileHeight(Player.currentLevel, glModel.vertexX[vertexIndex] + worldZ, glModel.vertexZ[vertexIndex] + worldX) - baseHeight;
                }

                // Invalidate buffers to trigger GPU update
                glModel.vertexBuffer.valid = false;
                glModel.bounds.valid = false;
            }
        } else {
            // Software rendering

            @Pc(142) SoftwareModel softwareModel = (SoftwareModel) model;

            // Check if terrain varies across the tergets bounds
            if (baseHeight != SceneGraph.getTileHeight(Player.currentLevel, minX + worldZ, minZ + worldX) || baseHeight != SceneGraph.getTileHeight(Player.currentLevel, worldZ + maxX, maxZ + worldX)) {

                // Adjust each vertex to match terrain elevation
                for (vertexIndex = 0; vertexIndex < softwareModel.vertexCount; vertexIndex++) {
                    softwareModel.vertexY[vertexIndex] += SceneGraph.getTileHeight(Player.currentLevel, worldZ + softwareModel.vertexX[vertexIndex], softwareModel.vertexZ[vertexIndex] + worldX) - baseHeight;
                }

                // Invalidate bounds to trigger recalcultion
                softwareModel.boundsValid = false;
            }
        }
        return model;
    }
}
