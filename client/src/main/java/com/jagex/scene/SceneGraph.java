package com.jagex.scene;

import com.jagex.graphics.lighting.*;
import com.jagex.scene.tile.*;
import com.jagex.sound.spatial.AreaSoundManager;
import com.jagex.client.Client;
import com.jagex.client.Preferences;
import com.jagex.game.runetek4.config.flotype.FloorOverlayType;
import com.jagex.game.runetek4.config.flotype.FloorOverlayTypeList;
import com.jagex.game.runetek4.config.flutype.FloorUnderlayType;
import com.jagex.game.runetek4.config.flutype.FloorUnderlayTypeList;
import com.jagex.game.runetek4.config.lighttype.LightTypeList;
import com.jagex.game.runetek4.config.loctype.LocTypeList;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.LinkedList;
import com.jagex.core.io.Packet;
import com.jagex.game.runetek4.config.loctype.LocType;
import com.jagex.game.runetek4.config.lighttype.LightType;
import com.jagex.js5.Js5TextureProvider;
import com.jagex.entity.Entity;
import com.jagex.entity.player.Player;
import com.jagex.entity.player.PlayerList;
import com.jagex.game.collision.CollisionMap;
import com.jagex.game.PathFinder;
import com.jagex.game.world.WorldLoader;
import com.jagex.graphics.font.Fonts;
import com.jagex.graphics.gl.GlModel;
import com.jagex.graphics.gl.GlRaster;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.graphics.gl.GlTile;
import com.jagex.graphics.environment.Environment;
import com.jagex.graphics.model.Model;
import com.jagex.entity.loc.Loc;
import com.jagex.entity.loc.LocEntity;
import com.jagex.entity.loc.ObjStackEntity;
import com.jagex.graphics.model.RawModel;
import com.jagex.graphics.raster.Rasterizer;
import com.jagex.graphics.raster.SoftwareRenderer;
import com.jagex.graphics.render.MaterialManager;
import com.jagex.graphics.render.UnderwaterMaterialRenderer;
import com.jagex.graphics.render.WaterMaterialRenderer;
import com.jagex.network.Protocol;
import com.jagex.runetek4.scene.tile.*;
import com.jagex.clientscript.ClientScriptRunner;
import com.jagex.ui.component.MiniMap;
import com.jagex.ui.component.MiniMenu;
import com.jagex.core.utils.ArrayUtils;
import com.jagex.math.ColorUtils;
import com.jagex.core.utils.math.MathUtils;
import com.jagex.core.utils.math.PerlinNoise;
import com.jagex.core.utils.string.LocalizedText;
import com.jogamp.opengl.GL2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import static com.jagex.game.camera.CameraMode.MODE_DEFAULT;
import static com.jagex.game.camera.CameraMode.MODE_SPLINE;

public class SceneGraph {
    @OriginalMember(owner = "client!bb", name = "g", descriptor = "[[[B")
    public static final byte[][][] renderFlags = new byte[4][104][104];

    @OriginalMember(owner = "runetek4.client!rj", name = "U", descriptor = "Lclient!ih;")
    public static final LinkedList projectiles = new LinkedList();

    @OriginalMember(owner = "runetek4.client!hk", name = "W", descriptor = "Lclient!ih;")
    public static final LinkedList spotanims = new LinkedList();

    @OriginalMember(owner = "runetek4.client!mi", name = "Y", descriptor = "[[[Lclient!ih;")
    public static final LinkedList[][][] objStacks = new LinkedList[4][104][104];

    @OriginalMember(owner = "runetek4.client!te", name = "B", descriptor = "[I")
    public static final int[] WALL_DECORATION_ROTATION_FORWARD_Z = new int[] { 0, -1, 0, 1 };

    @OriginalMember(owner = "client!fb", name = "q", descriptor = "[I")
    public static final int[] CORNER_OFFSET_X = new int[] { -1, -1, 1, 1 };

    @OriginalMember(owner = "runetek4.client!j", name = "O", descriptor = "[I")
    public static final int[] CORNER_OFFSET_Z = new int[] { 1, -1, -1, 1 };

    @OriginalMember(owner = "runetek4.client!vl", name = "e", descriptor = "[I")
    public static final int[] ROTATION_WALL_TYPE = new int[] { 1, 2, 4, 8 };

    @OriginalMember(owner = "runetek4.client!pg", name = "T", descriptor = "[I")
    public static final int[] BACK_WALL_TYPES = new int[] { 76, 8, 137, 4, 0, 1, 38, 2, 19 };

    @OriginalMember(owner = "client!ec", name = "B", descriptor = "[[I")
    public static final int[][] SHAPE_WALL_COORDINATES = new int[][] { new int[0], { 128, 0, 128, 128, 0, 128 }, { 0, 0, 128, 0, 128, 128, 64, 128 }, { 0, 128, 0, 0, 128, 0, 64, 128 }, { 0, 0, 64, 128, 0, 128 }, { 128, 128, 64, 128, 128, 0 }, { 64, 0, 128, 0, 128, 128, 64, 128 }, { 128, 0, 128, 128, 0, 128, 0, 64, 64, 0 }, { 0, 0, 64, 0, 0, 64 }, { 0, 0, 128, 0, 128, 128, 64, 96, 32, 64 }, { 0, 128, 0, 0, 32, 64, 64, 96, 128, 128 }, { 0, 128, 0, 0, 32, 32, 96, 32, 128, 0, 128, 128 } };

    @OriginalMember(owner = "client!ck", name = "d", descriptor = "[I")
    public static final int[] WALL_DECORATION_ROTATION_FORWARD_X = new int[] { 1, 0, -1, 0 };

    @OriginalMember(owner = "runetek4.client!ka", name = "t", descriptor = "[I")
    public static final int[] WALL_CORNER_TYPE_16_BLOCK_LOC_SPANS = new int[] { 0, 0, 2, 0, 0, 2, 1, 1, 0 };

    @OriginalMember(owner = "runetek4.client!uj", name = "A", descriptor = "[I")
    public static final int[] WALL_CORNER_TYPE_32_BLOCK_LOC_SPANS = new int[] { 2, 0, 0, 2, 0, 0, 0, 4, 4 };

    @OriginalMember(owner = "client!gm", name = "gb", descriptor = "[I")
    public static final int[] WALL_CORNER_TYPE_64_BLOCK_LOC_SPANS = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };

    @OriginalMember(owner = "runetek4.client!kd", name = "sb", descriptor = "[I")
    public static final int[] WALL_CORNER_TYPE_128_BLOCK_LOC_SPANS = new int[] { 1, 1, 0, 0, 0, 8, 0, 0, 8 };

    @OriginalMember(owner = "runetek4.client!hb", name = "t", descriptor = "[I")
    public static final int[] DIRECTION_ALLOW_WALL_CORNER_TYPE = new int[] { 160, 192, 80, 96, 0, 144, 80, 48, 160 };

    @OriginalMember(owner = "runetek4.client!km", name = "Rc", descriptor = "[I")
    public static final int[] FRONT_WALL_TYPES = new int[] { 19, 55, 38, 155, 255, 110, 137, 205, 76 };

    @OriginalMember(owner = "client!fh", name = "U", descriptor = "[[Z")
    public static final boolean[][] SHAPE_TEXTURE_OVERLAY_FLAGS = new boolean[][] { new boolean[0], { true, false, true }, { true, false, false, true }, { false, false, true, true }, { true, true, false }, { false, true, true }, { true, false, false, true }, { false, false, false, true, true }, { false, true, true }, { true, false, true, true, true }, { false, true, true, true, true }, { false, true, true, true, true, false } };

    @OriginalMember(owner = "client!fg", name = "d", descriptor = "[I")
    public static final int[] tmpViewspaceX = new int[6];

    @OriginalMember(owner = "client!fg", name = "l", descriptor = "[I")
    public static final int[] tmpViewspaceY = new int[6];

    @OriginalMember(owner = "client!fg", name = "m", descriptor = "[I")
    public static final int[] tmpLocalX = new int[6];

    @OriginalMember(owner = "client!fg", name = "r", descriptor = "[I")
    public static final int[] tmpViewspaceZ = new int[6];

    @OriginalMember(owner = "client!fg", name = "t", descriptor = "[I")
    public static final int[] tmpLocalY = new int[6];

    @OriginalMember(owner = "client!ah", name = "p", descriptor = "Lclient!ih;")
    public static final LinkedList drawTileQueue = new LinkedList();

    @OriginalMember(owner = "client!sh", name = "i", descriptor = "[[I")
    public static final int[][] SHAPE_OVERLAY_COORDINATES = new int[][] { { 0, 128, 0, 0, 128, 0, 128, 128 }, { 0, 128, 0, 0, 128, 0 }, { 0, 0, 64, 128, 0, 128 }, { 128, 128, 64, 128, 128, 0 }, { 0, 0, 128, 0, 128, 128, 64, 128 }, { 0, 128, 0, 0, 128, 0, 64, 128 }, { 64, 128, 0, 128, 0, 0, 64, 0 }, { 0, 0, 64, 0, 0, 64 }, { 128, 0, 128, 128, 0, 128, 0, 64, 64, 0 }, { 0, 128, 0, 0, 32, 64, 64, 96, 128, 128 }, { 0, 0, 128, 0, 128, 128, 64, 96, 32, 64 }, { 0, 0, 128, 0, 96, 32, 32, 32 } };

    @OriginalMember(owner = "client!gf", name = "S", descriptor = "[I")
    public static final int[] SHAPE_WALL_CORNER_COUNT = new int[] { 0, 2, 2, 2, 1, 1, 2, 2, 1, 3, 1, 1 };

    @OriginalMember(owner = "runetek4.client!kc", name = "s", descriptor = "[I")
    public static final int[] SHAPE_WALL_VERTEX_DATA = new int[] { 1, 1, 1, 1, 4, 1, 1, 5, 6, 1, 5, 0, 7, 0, 4, 1, 7, 2, 1, 1, 6, 1, 1, 3, 6, 1, 7, 0, 0, 6, 7, 0, 1, 7, 6, 1, 1, 1, 5, 4, 3, 2, 1, 1, 0, 4, 1, 5 };

    @OriginalMember(owner = "runetek4.client!wi", name = "hb", descriptor = "[[Z")
    public static final boolean[][] SHAPE_VISIBLE_FLAGS = new boolean[][] { { true, true, true }, { false, false }, { false, true }, { true, false }, { false, true, true }, { true, false, true }, { false, true, false }, { true, false, false } };

    @OriginalMember(owner = "runetek4.client!gj", name = "m", descriptor = "[[[I")
    public static int[][][] tileHeights;

    @OriginalMember(owner = "runetek4.client!sm", name = "e", descriptor = "[[[B")
    public static byte[][][] tileSettings;

    @OriginalMember(owner = "runetek4.client!id", name = "i", descriptor = "[[[I")
    public static int[][][] surfaceTileHeights;

    @OriginalMember(owner = "client!gn", name = "d", descriptor = "Z")
    public static boolean hdLighting = false;

    @OriginalMember(owner = "runetek4.client!sj", name = "u", descriptor = "Z")
    public static boolean dynamicMapRegion = false;

    @OriginalMember(owner = "client!gf", name = "R", descriptor = "I")
    public static int centralZoneX;

    @OriginalMember(owner = "client!eb", name = "u", descriptor = "I")
    public static int centralZoneZ;

    @OriginalMember(owner = "client!dc", name = "ab", descriptor = "I")
    public static int centralPlane = 0;

    @OriginalMember(owner = "runetek4.client!lg", name = "k", descriptor = "I")
    public static int firstVisibleLevel = 99;

    @OriginalMember(owner = "runetek4.client!kc", name = "o", descriptor = "[[[Lclient!bj;")
    public static Tile[][][] tiles;

    @OriginalMember(owner = "client!runetek4.client", name = "kb", descriptor = "[[Lclient!hg;")
    public static GlTile[][] surfaceHdTiles;

    @OriginalMember(owner = "runetek4.client!ja", name = "q", descriptor = "I")
    public static int currentChunkX;

    @OriginalMember(owner = "runetek4.client!og", name = "b", descriptor = "I")
    public static int currentChunkZ;

    @OriginalMember(owner = "runetek4.client!tg", name = "g", descriptor = "[[[B")
    public static byte[][][] tileOverlays;

    @OriginalMember(owner = "runetek4.client!n", name = "h", descriptor = "[[[B")
    public static byte[][][] tileShapes;

    @OriginalMember(owner = "runetek4.client!ac", name = "e", descriptor = "[[[B")
    public static byte[][][] tileAngles;

    @OriginalMember(owner = "runetek4.client!ui", name = "eb", descriptor = "[[[B")
    public static byte[][][] tileUnderlays;

    @OriginalMember(owner = "client!em", name = "t", descriptor = "[[[I")
    public static int[][][] occludeFlags;

    @OriginalMember(owner = "runetek4.client!ka", name = "r", descriptor = "[I")
    public static int[] rowCount;

    @OriginalMember(owner = "runetek4.client!ug", name = "d", descriptor = "[I")
    public static int[] rowChroma;

    @OriginalMember(owner = "runetek4.client!l", name = "l", descriptor = "[I")
    public static int[] rowSaturation;

    @OriginalMember(owner = "runetek4.client!s", name = "i", descriptor = "[I")
    public static int[] rowWeightedHue;

    @OriginalMember(owner = "runetek4.client!jd", name = "d", descriptor = "[[[B")
    public static byte[][][] shadowmap;

    @OriginalMember(owner = "runetek4.client!wk", name = "v", descriptor = "[I")
    public static int[] rowLightness;

    @OriginalMember(owner = "runetek4.client!ub", name = "h", descriptor = "[Lclient!pe;")
    public static SceneOccluder[] aSceneGraphClass120Array2;

    @OriginalMember(owner = "runetek4.client!rh", name = "k", descriptor = "I")
    public static int lightSourceCount = 0;

    @OriginalMember(owner = "client!bl", name = "T", descriptor = "I")
    public static int sceneryLen = 0;

    @OriginalMember(owner = "runetek4.client!pm", name = "cb", descriptor = "[[[Lclient!bj;")
    public static Tile[][][] surfaceGroundTiles;

    @OriginalMember(owner = "runetek4.client!wh", name = "c", descriptor = "[[[Lclient!bj;")
    public static Tile[][][] underWaterGroundTiles;

    @OriginalMember(owner = "runetek4.client!pk", name = "R", descriptor = "[[Lclient!hg;")
    public static GlTile[][] underWaterHdTiles;

    @OriginalMember(owner = "runetek4.client!hc", name = "O", descriptor = "[Lclient!pe;")
    public static SceneOccluder[] renderables;

    @OriginalMember(owner = "runetek4.client!ma", name = "i", descriptor = "I")
    public static int width;

    @OriginalMember(owner = "runetek4.client!hk", name = "Y", descriptor = "I")
    public static int length;

    @OriginalMember(owner = "client!cd", name = "s", descriptor = "I")
    public static int renderableCount;

    @OriginalMember(owner = "runetek4.client!tk", name = "D", descriptor = "[Lclient!ec;")
    public static Scenery[] scenery;

    @OriginalMember(owner = "client!c", name = "bb", descriptor = "[Lclient!ec;")
    public static Scenery[] pickableEntities;

    @OriginalMember(owner = "client!gf", name = "O", descriptor = "[[[I")
    public static int[][][] underwaterTileHeights;

    @OriginalMember(owner = "runetek4.client!oj", name = "E", descriptor = "[[Lclient!hg;")
    public static GlTile[][] underwaterHdTiles;

    @OriginalMember(owner = "runetek4.client!jm", name = "r", descriptor = "I")
    public static int planes;

    @OriginalMember(owner = "runetek4.client!wi", name = "db", descriptor = "I")
    public static int visibility;

    @OriginalMember(owner = "client!f", name = "ab", descriptor = "[[I")
    public static int[][] tileColorBuffers;

    @OriginalMember(owner = "runetek4.client!la", name = "i", descriptor = "[[[I")
    public static int[][][] lightmapBuffers;

    @OriginalMember(owner = "client!dl", name = "h", descriptor = "[[Z")
    public static boolean[][] tileVisibility;

    @OriginalMember(owner = "runetek4.client!ha", name = "k", descriptor = "[[Z")
    public static boolean[][] tileCulling;

    @OriginalMember(owner = "client!gm", name = "R", descriptor = "I")
    public static int randomLightOffsetX = (int) (Math.random() * 17.0D) - 8;

    @OriginalMember(owner = "runetek4.client!ok", name = "c", descriptor = "I")
    public static int randomLightOffsetZ = (int) (Math.random() * 33.0D) - 16;

    @OriginalMember(owner = "runetek4.client!rj", name = "R", descriptor = "I")
    public static int eyeZ;

    @OriginalMember(owner = "runetek4.client!pi", name = "U", descriptor = "I")
    public static int eyeTileZ;

    @OriginalMember(owner = "runetek4.client!sk", name = "mb", descriptor = "I")
    public static int sinPitch;

    @OriginalMember(owner = "client!bl", name = "X", descriptor = "I")
    public static int currentAnimationFrame = -1;

    @OriginalMember(owner = "client!aj", name = "Z", descriptor = "[I")
    public static int[] viewDistances;

    @OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "I")
    public static int cosYaw;

    @OriginalMember(owner = "runetek4.client!ma", name = "z", descriptor = "I")
    public static int lastAnimationFrame = -1;

    @OriginalMember(owner = "runetek4.client!ig", name = "i", descriptor = "I")
    public static int sinYaw;

    @OriginalMember(owner = "runetek4.client!k", name = "l", descriptor = "[I")
    public static int[] projectionX;

    @OriginalMember(owner = "runetek4.client!ta", name = "o", descriptor = "[I")
    public static int[] projectionY;

    @OriginalMember(owner = "runetek4.client!qk", name = "c", descriptor = "[I")
    public static int[] projectionZ;

    @OriginalMember(owner = "runetek4.client!hh", name = "p", descriptor = "[I")
    public static int[] screenDepth;

    @OriginalMember(owner = "runetek4.client!ml", name = "K", descriptor = "I")
    public static int eyeY;

    @OriginalMember(owner = "runetek4.client!nd", name = "s", descriptor = "I")
    public static int eyeTileX;

    @OriginalMember(owner = "runetek4.client!lj", name = "B", descriptor = "I")
    public static int eyeX;

    @OriginalMember(owner = "runetek4.client!tb", name = "Q", descriptor = "I")
    public static int frameCounter = 0;

    @OriginalMember(owner = "client!bc", name = "Z", descriptor = "I")
    public static int renderDistance;

    @OriginalMember(owner = "runetek4.client!rc", name = "p", descriptor = "I")
    public static int fogDistance = 0;

    @OriginalMember(owner = "runetek4.client!gg", name = "Z", descriptor = "I")
    public static int cosPitch;

    @OriginalMember(owner = "client!fc", name = "a", descriptor = "()V")
    public static void clear() {
        @Pc(3) int plane;
        @Pc(9) int x;
        @Pc(14) int z;
        if (surfaceGroundTiles != null) {
            for (plane = 0; plane < surfaceGroundTiles.length; plane++) {
                for (x = 0; x < width; x++) {
                    for (z = 0; z < length; z++) {
                        surfaceGroundTiles[plane][x][z] = null;
                    }
                }
            }
        }
        surfaceHdTiles = null;
        if (underWaterGroundTiles != null) {
            for (plane = 0; plane < underWaterGroundTiles.length; plane++) {
                for (x = 0; x < width; x++) {
                    for (z = 0; z < length; z++) {
                        underWaterGroundTiles[plane][x][z] = null;
                    }
                }
            }
        }
        underWaterHdTiles = null;
        renderableCount = 0;
        if (renderables != null) {
            for (plane = 0; plane < renderableCount; plane++) {
                renderables[plane] = null;
            }
        }
        if (scenery != null) {
            for (plane = 0; plane < sceneryLen; plane++) {
                scenery[plane] = null;
            }
            sceneryLen = 0;
        }
        if (pickableEntities != null) {
            for (plane = 0; plane < pickableEntities.length; plane++) {
                pickableEntities[plane] = null;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(III)Lclient!jh;")
    public static Wall getWall(@OriginalArg(0) int level, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[level][x][z];
        return tile == null ? null : tile.wall;
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "b", descriptor = "(III)Lclient!ec;")
    public static Scenery getScenery(@OriginalArg(0) int level, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[level][x][z];
        if (tile == null) {
            return null;
        }
        for (@Pc(13) int i = 0; i < tile.sceneryLen; i++) {
            @Pc(22) Scenery scenery = tile.scenery[i];
            if ((scenery.key >> 29 & 0x3L) == 2L && scenery.xMin == x && scenery.zMin == z) {
                return scenery;
            }
        }
        return null;
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(III)Lclient!bm;")
    public static GroundDecor getGroundDecor(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        return tile == null || tile.groundDecor == null ? null : tile.groundDecor;
    }

    @OriginalMember(owner = "runetek4.client!ql", name = "a", descriptor = "(IIII)I")
    public static int getTileHeight(@OriginalArg(0) int level, @OriginalArg(2) int xFine, @OriginalArg(3) int zFine) {
        if (tileHeights == null) {
            return 0;
        }
        @Pc(12) int tileX = xFine >> 7;
        @Pc(16) int tileZ = zFine >> 7;
        if (tileX < 0 || tileZ < 0 || tileX > 103 || tileZ > 103) {
            return 0;
        }
        @Pc(36) int xOffset = xFine & 0x7F;
        @Pc(40) int zOffset = zFine & 0x7F;
        @Pc(42) int virtualLevel = level;
        if (level < 3 && (renderFlags[1][tileX][tileZ] & 0x2) == 2) {
            virtualLevel = level + 1;
        }
        @Pc(91) int interpolatedHeightNorth = xOffset * tileHeights[virtualLevel][tileX + 1][tileZ + 1] + tileHeights[virtualLevel][tileX][tileZ + 1] * (128 - xOffset) >> 7;
        @Pc(118) int interpolatedHeightSouth = xOffset * tileHeights[virtualLevel][tileX + 1][tileZ] + (128 - xOffset) * tileHeights[virtualLevel][tileX][tileZ] >> 7;
        return zOffset * interpolatedHeightNorth + (128 - zOffset) * interpolatedHeightSouth >> 7;
    }

    @OriginalMember(owner = "runetek4.client!ge", name = "a", descriptor = "(IIIIIIII)V")
    public static void setLocationObject(@OriginalArg(0) int locId, @OriginalArg(1) int x, @OriginalArg(2) int plane, @OriginalArg(3) int rotation, @OriginalArg(4) int z, @OriginalArg(6) int shape, @OriginalArg(7) int layer) {
        if (x < 1 || z < 1 || x > 102 || z > 102) {
            return;
        }
        @Pc(39) int renderPlane;
        if (!allLevelsAreVisible() && (renderFlags[0][x][z] & 0x2) == 0) {
            renderPlane = plane;
            if ((renderFlags[plane][x][z] & 0x8) != 0) {
                renderPlane = 0;
            }
            if (renderPlane != centralPlane) {
                return;
            }
        }
        renderPlane = plane;
        if (plane < 3 && (renderFlags[1][x][z] & 0x2) == 2) {
            renderPlane = plane + 1;
        }
        removeLocWithCollision(z, x, plane, layer, renderPlane, PathFinder.collisionMaps[plane]);
        if (locId >= 0) {
            @Pc(92) boolean originalShowGroundDecorations = Preferences.showGroundDecorations;
            Preferences.showGroundDecorations = true;
            addLoc(renderPlane, false, plane, false, PathFinder.collisionMaps[plane], locId, shape, x, z, rotation);
            Preferences.showGroundDecorations = originalShowGroundDecorations;
        }
    }

    @OriginalMember(owner = "runetek4.client!vj", name = "a", descriptor = "(III)J")
    public static long getWallKey(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        return tile == null || tile.wall == null ? 0L : tile.wall.key;
    }

    @OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "(III)J")
    public static long getWallDecorKey(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        return tile == null || tile.wallDecor == null ? 0L : tile.wallDecor.key;
    }

    @OriginalMember(owner = "runetek4.client!cl", name = "a", descriptor = "(III)J")
    public static long getSceneryKey(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return 0L;
        }
        for (@Pc(13) int i = 0; i < tile.sceneryLen; i++) {
            @Pc(22) Scenery scenery = tile.scenery[i];
            if ((scenery.key >> 29 & 0x3L) == 2L && scenery.xMin == x && scenery.zMin == z) {
                return scenery.key;
            }
        }
        return 0L;
    }

    @OriginalMember(owner = "client!bj", name = "a", descriptor = "(III)J")
    public static long getGroundDecorKey(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        return tile == null || tile.groundDecor == null ? 0L : tile.groundDecor.key;
    }

    @OriginalMember(owner = "client!sd", name = "c", descriptor = "(II)V")
    public static void method3884(@OriginalArg(0) int x, @OriginalArg(1) int z) {
        @Pc(7) Tile originalGroundTile = tiles[0][x][z];
        for (@Pc(9) int plane = 0; plane < 3; plane++) {
            @Pc(30) Tile tile = tiles[plane][x][z] = tiles[plane + 1][x][z];
            if (tile != null) {
                tile.sceneX--;
                for (@Pc(40) int sceneryIndex = 0; sceneryIndex < tile.sceneryLen; sceneryIndex++) {
                    @Pc(49) Scenery scenery = tile.scenery[sceneryIndex];
                    if ((scenery.key >> 29 & 0x3L) == 2L && scenery.xMin == x && scenery.zMin == z) {
                        scenery.level--;
                    }
                }
            }
        }
        if (tiles[0][x][z] == null) {
            tiles[0][x][z] = new Tile(0, x, z);
        }
        tiles[0][x][z].bridgeTile = originalGroundTile;
        tiles[3][x][z] = null;
    }

    @OriginalMember(owner = "runetek4.client!jk", name = "a", descriptor = "(IZ[BII[Lclient!mj;)V")
    public static void readLocs(@OriginalArg(0) int baseX, @OriginalArg(1) boolean highmem, @OriginalArg(2) byte[] data, @OriginalArg(3) int baseZ, @OriginalArg(5) CollisionMap[] collisionMaps) {
        @Pc(10) Packet packet = new Packet(data);
        @Pc(12) int locId = -1;
        while (true) {
            @Pc(16) int locIdDelta = packet.gVarSmart();
            if (locIdDelta == 0) {
                return;
            }
            locId += locIdDelta;
            @Pc(27) int position = 0;
            while (true) {
                @Pc(31) int positionDelta = packet.gSmart1or2();
                if (positionDelta == 0) {
                    break;
                }
                position += positionDelta - 1;
                @Pc(46) int localZ = position & 0x3F;
                @Pc(50) int level = position >> 12;
                @Pc(56) int localX = position >> 6 & 0x3F;
                @Pc(60) int shapeAndRotation = packet.g1();
                @Pc(64) int shape = shapeAndRotation >> 2;
                @Pc(68) int rotation = shapeAndRotation & 0x3;
                @Pc(72) int x = baseX + localX;
                @Pc(76) int z = localZ + baseZ;
                if (x > 0 && z > 0 && x < 103 && z < 103) {
                    @Pc(90) CollisionMap collisionMap = null;
                    if (!highmem) {
                        @Pc(95) int collisionLevel = level;
                        if ((renderFlags[1][x][z] & 0x2) == 2) {
                            collisionLevel = level - 1;
                        }
                        if (collisionLevel >= 0) {
                            collisionMap = collisionMaps[collisionLevel];
                        }
                    }
                    addLoc(level, !highmem, level, highmem, collisionMap, locId, shape, x, z, rotation);
                }
            }
        }
    }

    @OriginalMember(owner = "client!cd", name = "a", descriptor = "(IIIIZ)V")
    public static void init(@OriginalArg(3) int tileVisibility, @OriginalArg(4) boolean hasUnderWaterMap) {
        width = 104;
        length = 104;
        visibility = tileVisibility;
        surfaceGroundTiles = new Tile[4][width][length];
        surfaceTileHeights = new int[4][width + 1][length + 1];
        if (GlRenderer.enabled) {
            surfaceHdTiles = new GlTile[4][];
        }
        if (hasUnderWaterMap) {
            underWaterGroundTiles = new Tile[1][width][length];
            tileColorBuffers = new int[width][length];
            underwaterTileHeights = new int[1][width + 1][length + 1];
            if (GlRenderer.enabled) {
                underWaterHdTiles = new GlTile[1][];
            }
        } else {
            underWaterGroundTiles = null;
            tileColorBuffers = null;
            underwaterTileHeights = null;
            underWaterHdTiles = null;
        }
        setUnderwater(false);
        renderables = new SceneOccluder[500];
        renderableCount = 0;
        aSceneGraphClass120Array2 = new SceneOccluder[500];
        lightSourceCount = 0;
        lightmapBuffers = new int[4][width + 1][length + 1];
        scenery = new Scenery[5000];
        sceneryLen = 0;
        pickableEntities = new Scenery[100];
        SceneGraph.tileVisibility = new boolean[visibility + visibility + 1][visibility + visibility + 1];
        tileCulling = new boolean[visibility + visibility + 2][visibility + visibility + 2];
        tileSettings = new byte[4][width][length];
    }

    @OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "(ZI)V")
	public static void load(@OriginalArg(0) boolean underwater) {
		rowWeightedHue = new int[104];
		rowSaturation = new int[104];
		firstVisibleLevel = 99;
		rowChroma = new int[104];
		@Pc(14) byte plane;
		if (underwater) {
			plane = 1;
		} else {
			plane = 4;
		}
		tileShapes = new byte[plane][104][104];
		rowCount = new int[104];
		occludeFlags = new int[plane][105][105];
		shadowmap = new byte[plane][105][105];
		tileOverlays = new byte[plane][104][104];
		rowLightness = new int[104];
		tileAngles = new byte[plane][104][104];
		tileUnderlays = new byte[plane][104][104];
	}

    @OriginalMember(owner = "client!di", name = "a", descriptor = "([Lclient!mj;ZI)V")
    public static void buildTiles(@OriginalArg(0) CollisionMap[] collisionMaps, @OriginalArg(1) boolean underwater) {
        @Pc(10) int lightLevel;
        @Pc(15) int tileX;
        if (!underwater) {
            for (lightLevel = 0; lightLevel < 4; lightLevel++) {
                for (tileX = 0; tileX < 104; tileX++) {
                    for (@Pc(22) int tileZ = 0; tileZ < 104; tileZ++) {
                        if ((renderFlags[lightLevel][tileX][tileZ] & 0x1) == 1) {
                            @Pc(43) int collisionLevel = lightLevel;
                            if ((renderFlags[1][tileX][tileZ] & 0x2) == 2) {
                                collisionLevel = lightLevel - 1;
                            }
                            if (collisionLevel >= 0) {
                                collisionMaps[collisionLevel].flagBlocked(tileZ, tileX);
                            }
                        }
                    }
                }
            }
            randomLightOffsetZ += (int) (Math.random() * 5.0D) - 2;
            if (randomLightOffsetZ < -16) {
                randomLightOffsetZ = -16;
            }
            if (randomLightOffsetZ > 16) {
                randomLightOffsetZ = 16;
            }
            randomLightOffsetX += (int) (Math.random() * 5.0D) - 2;
            if (randomLightOffsetX < -8) {
                randomLightOffsetX = -8;
            }
            if (randomLightOffsetX > 8) {
                randomLightOffsetX = 8;
            }
        }
        @Pc(128) byte levelCount;
        if (underwater) {
            levelCount = 1;
        } else {
            levelCount = 4;
        }
        lightLevel = randomLightOffsetX >> 2 << 10;
        @Pc(142) int[][] underlayColorMap = new int[104][104];
        @Pc(146) int[][] levelLightMap = new int[104][104];
        tileX = randomLightOffsetZ >> 1;
        @Pc(152) int level;
        @Pc(168) int z;
        @Pc(173) int x;
        @Pc(178) int lightZ;
        @Pc(194) int underwaterDepth;
        @Pc(200) int lightValue;
        @Pc(202) int heightDx;
        @Pc(209) int heightDz;
        @Pc(349) int len;
        @Pc(234) int normalX;
        @Pc(254) int normalY;
        @Pc(267) int normalZ;
        for (level = 0; level < levelCount; level++) {
            @Pc(159) byte[][] shadowData = shadowmap[level];
            @Pc(273) int normalizedX;
            @Pc(326) int normalizedY;
            @Pc(332) int normalizedZ;
            @Pc(322) int neighborZ;
            if (!GlRenderer.enabled) {
                z = (int) Math.sqrt(5100.0D);
                x = z * 768 >> 8;
                for (lightZ = 1; lightZ < 103; lightZ++) {
                    for (underwaterDepth = 1; underwaterDepth < 103; underwaterDepth++) {
                        heightDz = tileHeights[level][underwaterDepth][lightZ + 1] - tileHeights[level][underwaterDepth][lightZ - 1];
                        heightDx = tileHeights[level][underwaterDepth + 1][lightZ] - tileHeights[level][underwaterDepth - 1][lightZ];
                        len = (int) Math.sqrt((double) (heightDx * heightDx + heightDz * heightDz + 65536));
                        normalZ = (heightDz << 8) / len;
                        normalY = -65536 / len;
                        normalX = (heightDx << 8) / len;
                        normalizedX = (shadowData[underwaterDepth][lightZ] >> 1) + (shadowData[underwaterDepth][lightZ - 1] >> 2) + (shadowData[underwaterDepth - -1][lightZ] >> 3) + (shadowData[underwaterDepth - 1][lightZ] >> 2) + (shadowData[underwaterDepth][lightZ + 1] >> 3);
                        lightValue = (normalZ * -50 + normalX * -50 + normalY * -10) / x + 74;
                        levelLightMap[underwaterDepth][lightZ] = lightValue - normalizedX;
                    }
                }
            } else if (Preferences.highDetailLighting) {
                for (z = 1; z < 103; z++) {
                    for (x = 1; x < 103; x++) {
                        underwaterDepth = (shadowData[x + 1][z] >> 3) + (shadowData[x - 1][z] >> 2) + (shadowData[x][z + -1] >> 2) + (shadowData[x][z + 1] >> 3) + (shadowData[x][z] >> 1);
                        levelLightMap[x][z] = 74 - underwaterDepth;
                    }
                }
            } else {
                z = (int) FogManager.light0Position[0];
                x = (int) FogManager.light0Position[1];
                lightZ = (int) FogManager.light0Position[2];
                underwaterDepth = (int) Math.sqrt((double) (x * x + z * z + lightZ * lightZ));
                lightValue = underwaterDepth * 1024 >> 8;
                for (heightDx = 1; heightDx < 103; heightDx++) {
                    for (heightDz = 1; heightDz < 103; heightDz++) {
                        normalX = tileHeights[level][heightDz + 1][heightDx] - tileHeights[level][heightDz - 1][heightDx];
                        normalY = tileHeights[level][heightDz][heightDx + 1] - tileHeights[level][heightDz][heightDx - 1];
                        normalZ = (int) Math.sqrt((double) (normalX * normalX + normalY * normalY + 65536));
                        normalizedX = (normalX << 8) / normalZ;
                        neighborZ = (shadowData[heightDz][heightDx + 1] >> 3) + (shadowData[heightDz][heightDx - 1] >> 2) + (shadowData[heightDz - 1][heightDx] >> 2) + (shadowData[heightDz + 1][heightDx] >> 3) + (shadowData[heightDz][heightDx] >> 1);
                        normalizedY = -65536 / normalZ;
                        normalizedZ = (normalY << 8) / normalZ;
                        len = (lightZ * normalizedZ + z * normalizedX + normalizedY * x) / lightValue + 96;
                        levelLightMap[heightDz][heightDx] = len - (int) ((float) neighborZ * 1.7F);
                    }
                }
            }
            for (z = 0; z < 104; z++) {
                rowWeightedHue[z] = 0;
                rowSaturation[z] = 0;
                rowLightness[z] = 0;
                rowChroma[z] = 0;
                rowCount[z] = 0;
            }
            for (z = -5; z < 104; z++) {
                for (x = 0; x < 104; x++) {
                    lightZ = z + 5;
                    @Pc(729) int debugMag;
                    if (lightZ < 104) {
                        underwaterDepth = tileUnderlays[level][lightZ][x] & 0xFF;
                        if (underwaterDepth > 0) {
                            @Pc(693) FloorUnderlayType flu = FloorUnderlayTypeList.get(underwaterDepth - 1);
                            rowWeightedHue[x] += flu.weightedHue;
                            rowSaturation[x] += flu.saturation;
                            rowLightness[x] += flu.lightness;
                            rowChroma[x] += flu.chroma;
                            debugMag = rowCount[x]++;
                        }
                    }
                    underwaterDepth = z - 5;
                    if (underwaterDepth >= 0) {
                        lightValue = tileUnderlays[level][underwaterDepth][x] & 0xFF;
                        if (lightValue > 0) {
                            @Pc(758) FloorUnderlayType local758 = FloorUnderlayTypeList.get(lightValue - 1);
                            rowWeightedHue[x] -= local758.weightedHue;
                            rowSaturation[x] -= local758.saturation;
                            rowLightness[x] -= local758.lightness;
                            rowChroma[x] -= local758.chroma;
                            debugMag = rowCount[x]--;
                        }
                    }
                }
                if (z >= 0) {
                    x = 0;
                    underwaterDepth = 0;
                    lightZ = 0;
                    lightValue = 0;
                    heightDx = 0;
                    for (heightDz = -5; heightDz < 104; heightDz++) {
                        len = heightDz + 5;
                        if (len < 104) {
                            lightZ += rowSaturation[len];
                            heightDx += rowCount[len];
                            x += rowWeightedHue[len];
                            lightValue += rowChroma[len];
                            underwaterDepth += rowLightness[len];
                        }
                        normalX = heightDz - 5;
                        if (normalX >= 0) {
                            lightZ -= rowSaturation[normalX];
                            lightValue -= rowChroma[normalX];
                            x -= rowWeightedHue[normalX];
                            heightDx -= rowCount[normalX];
                            underwaterDepth -= rowLightness[normalX];
                        }
                        if (heightDz >= 0 && heightDx > 0) {
                            underlayColorMap[z][heightDz] = ColorUtils.method1309(underwaterDepth / heightDx, lightZ / heightDx, x * 256 / lightValue);
                        }
                    }
                }
            }
            for (z = 1; z < 103; z++) {
                waterCheckLoop: for (x = 1; x < 103; x++) {
                    if (underwater || allLevelsAreVisible() || (renderFlags[0][z][x] & 0x2) != 0 || (renderFlags[level][z][x] & 0x10) == 0 && getRenderLevel(x, z, level) == centralPlane) {
                        if (firstVisibleLevel > level) {
                            firstVisibleLevel = level;
                        }
                        lightZ = tileUnderlays[level][z][x] & 0xFF;
                        underwaterDepth = tileOverlays[level][z][x] & 0xFF;
                        if (lightZ > 0 || underwaterDepth > 0) {
                            heightDx = tileHeights[level][z + 1][x];
                            lightValue = tileHeights[level][z][x];
                            len = tileHeights[level][z][x + 1];
                            heightDz = tileHeights[level][z + 1][x + 1];
                            if (level > 0) {
                                @Pc(1067) boolean canOcclude = true;
                                if (lightZ == 0 && tileShapes[level][z][x] != 0) {
                                    canOcclude = false;
                                }
                                if (underwaterDepth > 0 && !FloorOverlayTypeList.method4395(underwaterDepth - 1).occlude) {
                                    canOcclude = false;
                                }
                                if (canOcclude && lightValue == heightDx && lightValue == heightDz && len == lightValue) {
                                    occludeFlags[level][z][x] |= 0x4;
                                }
                            }
                            if (lightZ <= 0) {
                                normalX = -1;
                                normalY = 0;
                            } else {
                                normalX = underlayColorMap[z][x];
                                normalZ = (normalX & 0x7F) + tileX;
                                if (normalZ < 0) {
                                    normalZ = 0;
                                } else if (normalZ > 127) {
                                    normalZ = 127;
                                }
                                normalizedX = (normalX & 0x380) + (normalX + lightLevel & 0xFC00) + normalZ;
                                normalY = Rasterizer.palette[ColorUtils.multiplyLightnessSafe(96, normalizedX)];
                            }
                            normalZ = levelLightMap[z][x];
                            normalizedZ = levelLightMap[z][x + 1];
                            normalizedX = levelLightMap[z + 1][x];
                            normalizedY = levelLightMap[z + 1][x + 1];
                            if (underwaterDepth == 0) {
                                setTile(level, z, x, 0, 0, -1, lightValue, heightDx, heightDz, len, ColorUtils.multiplyLightnessSafe(normalZ, normalX), ColorUtils.multiplyLightnessSafe(normalizedX, normalX), ColorUtils.multiplyLightnessSafe(normalizedY, normalX), ColorUtils.multiplyLightnessSafe(normalizedZ, normalX), 0, 0, 0, 0, normalY, 0);
                                if (GlRenderer.enabled && level > 0 && normalX != -1 && FloorUnderlayTypeList.get(lightZ - 1).blockShadow) {
                                    ShadowManager.method4197(0, 0, true, false, z, x, lightValue - tileHeights[0][z][x], -tileHeights[0][z + 1][x] + heightDx, heightDz - tileHeights[0][z + 1][x + 1], len - tileHeights[0][z][x + 1]);
                                }
                                if (GlRenderer.enabled && !underwater && tileColorBuffers != null && level == 0) {
                                    for (neighborZ = z - 1; neighborZ <= z + 1; neighborZ++) {
                                        for (@Pc(1794) int neighborX = x - 1; neighborX <= x + 1; neighborX++) {
                                            if ((neighborZ != z || x != neighborX) && neighborZ >= 0 && neighborZ < 104 && neighborX >= 0 && neighborX < 104) {
                                                @Pc(1834) int neighborOverlayId = tileOverlays[level][neighborZ][neighborX] & 0xFF;
                                                if (neighborOverlayId != 0) {
                                                    @Pc(1842) FloorOverlayType neighborFloorOverlayType = FloorOverlayTypeList.method4395(neighborOverlayId - 1);
                                                    if (neighborFloorOverlayType.texture != -1 && Rasterizer.textureProvider.getMaterialType(neighborFloorOverlayType.texture) == 4) {
                                                        tileColorBuffers[z][x] = neighborFloorOverlayType.waterColor + (neighborFloorOverlayType.waterOpaity << 24);
                                                        continue waterCheckLoop;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            } else {
                                neighborZ = tileShapes[level][z][x] + 1;
                                @Pc(1242) byte tileAngle = tileAngles[level][z][x];
                                @Pc(1248) FloorOverlayType floorOverlayType = FloorOverlayTypeList.method4395(underwaterDepth - 1);
                                @Pc(1301) int baseColor;
                                @Pc(1353) int overlayColor;
                                @Pc(1288) int materialId;
                                if (GlRenderer.enabled && !underwater && tileColorBuffers != null && level == 0) {
                                    if (floorOverlayType.texture != -1 && Rasterizer.textureProvider.getMaterialType(floorOverlayType.texture) == 4) {
                                        tileColorBuffers[z][x] = (floorOverlayType.waterOpaity << 24) + floorOverlayType.waterColor;
                                    } else {
                                        neighborWaterCheck: for (materialId = z - 1; materialId <= z + 1; materialId++) {
                                            for (baseColor = x - 1; baseColor <= x + 1; baseColor++) {
                                                if ((z != materialId || baseColor != x) && materialId >= 0 && materialId < 104 && baseColor >= 0 && baseColor < 104) {
                                                    overlayColor = tileOverlays[level][materialId][baseColor] & 0xFF;
                                                    if (overlayColor != 0) {
                                                        @Pc(1366) FloorOverlayType neighborFloorOverlayType = FloorOverlayTypeList.method4395(overlayColor - 1);
                                                        if (neighborFloorOverlayType.texture != -1 && Rasterizer.textureProvider.getMaterialType(neighborFloorOverlayType.texture) == 4) {
                                                            tileColorBuffers[z][x] = neighborFloorOverlayType.waterColor + (neighborFloorOverlayType.waterOpaity << 24);
                                                            break neighborWaterCheck;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                materialId = floorOverlayType.texture;
                                if (materialId >= 0 && !Rasterizer.textureProvider.method3236(materialId)) {
                                    materialId = -1;
                                }
                                @Pc(1458) int adjustedSaturation;
                                @Pc(1429) int adjustedHue;
                                if (materialId >= 0) {
                                    baseColor = -1;
                                    overlayColor = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(Rasterizer.textureProvider.getAverageColor(materialId), 96)];
                                } else if (floorOverlayType.baseColor == -1) {
                                    baseColor = -2;
                                    overlayColor = 0;
                                } else {
                                    baseColor = floorOverlayType.baseColor;
                                    adjustedHue = tileX + (baseColor & 0x7F);
                                    if (adjustedHue < 0) {
                                        adjustedHue = 0;
                                    } else if (adjustedHue > 127) {
                                        adjustedHue = 127;
                                    }
                                    adjustedSaturation = (baseColor & 0x380) + ((baseColor + lightLevel & 0xFC00) + adjustedHue);
                                    overlayColor = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(adjustedSaturation, 96)];
                                }
                                if (floorOverlayType.blendColor >= 0) {
                                    adjustedHue = floorOverlayType.blendColor;
                                    adjustedSaturation = tileX + (adjustedHue & 0x7F);
                                    if (adjustedSaturation < 0) {
                                        adjustedSaturation = 0;
                                    } else if (adjustedSaturation > 127) {
                                        adjustedSaturation = 127;
                                    }
                                    @Pc(1529) int secondaryColorValue = (adjustedHue & 0x380) + ((adjustedHue + lightLevel & 0xFC00) + adjustedSaturation);
                                    overlayColor = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(secondaryColorValue, 96)];
                                }
                                setTile(level, z, x, neighborZ, tileAngle, materialId, lightValue, heightDx, heightDz, len, ColorUtils.multiplyLightnessSafe(normalZ, normalX), ColorUtils.multiplyLightnessSafe(normalizedX, normalX), ColorUtils.multiplyLightnessSafe(normalizedY, normalX), ColorUtils.multiplyLightnessSafe(normalizedZ, normalX), ColorUtils.multiplyLightnessGrayscale(baseColor, normalZ), ColorUtils.multiplyLightnessGrayscale(baseColor, normalizedX), ColorUtils.multiplyLightnessGrayscale(baseColor, normalizedY), ColorUtils.multiplyLightnessGrayscale(baseColor, normalizedZ), normalY, overlayColor);
                                if (GlRenderer.enabled && level > 0) {
                                    ShadowManager.method4197(neighborZ, tileAngle, baseColor == -2 || !floorOverlayType.hardShadow, normalX == -1 || !FloorUnderlayTypeList.get(lightZ - 1).blockShadow, z, x, lightValue - tileHeights[0][z][x], heightDx - tileHeights[0][z + 1][x], heightDz - tileHeights[0][z + 1][x + 1], -tileHeights[0][z][x + 1] + len);
                                }
                            }
                        }
                    }
                }
            }
            if (GlRenderer.enabled) {
                @Pc(1888) float[][] normalXMap = new float[105][105];
                @Pc(1892) int[][] heightMap = tileHeights[level];
                @Pc(1896) float[][] normalYMap = new float[105][105];
                @Pc(1900) float[][] normalZMap = new float[105][105];
                lightValue = 1;
                while (true) {
                    if (lightValue > 103) {
                        @Pc(2025) GlTile[] baseTiles;
                        if (underwater) {
                            baseTiles = buildGlTiles(renderFlags, tileShapes[level], tileUnderlays[level], levelLightMap, normalYMap, tileColorBuffers, tileOverlays[level], tileAngles[level], normalXMap, level, normalZMap, underlayColorMap, tileHeights[level], surfaceTileHeights[0]);
                            setUnderwaterHdTiles(level, baseTiles);
                            break;
                        }
                        baseTiles = buildGlTiles(renderFlags, tileShapes[level], tileUnderlays[level], levelLightMap, normalYMap, null, tileOverlays[level], tileAngles[level], normalXMap, level, normalZMap, underlayColorMap, tileHeights[level], null);
                        @Pc(2049) GlTile[] additionalTiles = buildUnderwaterGlTiles(normalYMap, normalXMap, tileHeights[level], level, normalZMap, tileAngles[level], levelLightMap, tileShapes[level], tileUnderlays[level], tileOverlays[level], renderFlags);
                        @Pc(2057) GlTile[] combinedTiles = new GlTile[baseTiles.length + additionalTiles.length];
                        for (len = 0; len < baseTiles.length; len++) {
                            combinedTiles[len] = baseTiles[len];
                        }
                        for (len = 0; len < additionalTiles.length; len++) {
                            combinedTiles[baseTiles.length + len] = additionalTiles[len];
                        }
                        setUnderwaterHdTiles(level, combinedTiles);
                        generateLightMaps(normalZMap, tileUnderlays[level], tileAngles[level], LightingManager.lights, level, LightingManager.lightCount, normalYMap, tileShapes[level], tileOverlays[level], tileHeights[level], normalXMap);
                        break;
                    }
                    for (heightDx = 1; heightDx <= 103; heightDx++) {
                        len = heightMap[heightDx][lightValue + 1] - heightMap[heightDx][lightValue - 1];
                        heightDz = heightMap[heightDx + 1][lightValue] - heightMap[heightDx - 1][lightValue];
                        @Pc(1962) float normalLength = (float) Math.sqrt((double) (heightDz * heightDz + len * len + 65536));
                        normalXMap[heightDx][lightValue] = (float) heightDz / normalLength;
                        normalYMap[heightDx][lightValue] = -256.0F / normalLength;
                        normalZMap[heightDx][lightValue] = (float) len / normalLength;
                    }
                    lightValue++;
                }
            }
            tileUnderlays[level] = null;
            tileOverlays[level] = null;
            tileShapes[level] = null;
            tileAngles[level] = null;
            shadowmap[level] = null;
        }
        buildSceneModels();
        if (underwater) {
            return;
        }
        @Pc(2204) int coord;
        for (level = 0; level < 104; level++) {
            for (coord = 0; coord < 104; coord++) {
                if ((renderFlags[1][level][coord] & 0x2) == 2) {
                    method3884(level, coord);
                }
            }
        }
        for (level = 0; level < 4; level++) {
            for (coord = 0; coord <= 104; coord++) {
                for (z = 0; z <= 104; z++) {
                    if ((occludeFlags[level][z][coord] & 0x1) != 0) {
                        lightValue = level;
                        for (x = coord; x > 0 && (occludeFlags[level][z][x - 1] & 0x1) != 0; x--) {
                            // TODO why is this here?
                        }
                        underwaterDepth = level;
                        for (lightZ = coord; lightZ < 104 && (occludeFlags[level][z][lightZ + 1] & 0x1) != 0; lightZ++) {
                            // TODO why is this here?
                        }
                        findLowerLevel: while (underwaterDepth > 0) {
                            for (heightDx = x; heightDx <= lightZ; heightDx++) {
                                if ((occludeFlags[underwaterDepth - 1][z][heightDx] & 0x1) == 0) {
                                    break findLowerLevel;
                                }
                            }
                            underwaterDepth--;
                        }
                        findUpperLevel: while (lightValue < 3) {
                            for (heightDx = x; heightDx <= lightZ; heightDx++) {
                                if ((occludeFlags[lightValue + 1][z][heightDx] & 0x1) == 0) {
                                    break findUpperLevel;
                                }
                            }
                            lightValue++;
                        }
                        heightDx = (lightValue + 1 - underwaterDepth) * (-x + (lightZ - -1));
                        if (heightDx >= 8) {
                            len = tileHeights[lightValue][z][x] - 240;
                            normalX = tileHeights[underwaterDepth][z][x];
                            SceneOccluder.addOccluder(1, z * 128, z * 128, x * 128, lightZ * 128 + 128, len, normalX);
                            for (normalY = underwaterDepth; normalY <= lightValue; normalY++) {
                                for (normalZ = x; normalZ <= lightZ; normalZ++) {
                                    occludeFlags[normalY][z][normalZ] &= 0xFFFFFFFE;
                                }
                            }
                        }
                    }
                    if ((occludeFlags[level][z][coord] & 0x2) != 0) {
                        for (x = z; x > 0 && (occludeFlags[level][x - 1][coord] & 0x2) != 0; x--) {
                            // TODO why is this here?
                        }
                        lightValue = level;
                        underwaterDepth = level;
                        for (lightZ = z; lightZ < 104 && (occludeFlags[level][lightZ + 1][coord] & 0x2) != 0; lightZ++) {
                            // TODO why is this here?
                        }
                        findLowerLevelY: while (underwaterDepth > 0) {
                            for (heightDx = x; heightDx <= lightZ; heightDx++) {
                                if ((occludeFlags[underwaterDepth - 1][heightDx][coord] & 0x2) == 0) {
                                    break findLowerLevelY;
                                }
                            }
                            underwaterDepth--;
                        }
                        findUpperLevelY: while (lightValue < 3) {
                            for (heightDx = x; heightDx <= lightZ; heightDx++) {
                                if ((occludeFlags[lightValue + 1][heightDx][coord] & 0x2) == 0) {
                                    break findUpperLevelY;
                                }
                            }
                            lightValue++;
                        }
                        heightDx = (lightZ + 1 - x) * (-underwaterDepth + lightValue - -1);
                        if (heightDx >= 8) {
                            len = tileHeights[lightValue][x][coord] - 240;
                            normalX = tileHeights[underwaterDepth][x][coord];
                            SceneOccluder.addOccluder(2, x * 128, lightZ * 128 + 128, coord * 128, coord * 128, len, normalX);
                            for (normalY = underwaterDepth; normalY <= lightValue; normalY++) {
                                for (normalZ = x; normalZ <= lightZ; normalZ++) {
                                    occludeFlags[normalY][normalZ][coord] &= 0xFFFFFFFD;
                                }
                            }
                        }
                    }
                    if ((occludeFlags[level][z][coord] & 0x4) != 0) {
                        x = z;
                        lightZ = z;
                        for (underwaterDepth = coord; underwaterDepth > 0 && (occludeFlags[level][z][underwaterDepth - 1] & 0x4) != 0; underwaterDepth--) {
                            // TODO why is this here?
                        }
                        for (lightValue = coord; lightValue < 104 && (occludeFlags[level][z][lightValue + 1] & 0x4) != 0; lightValue++) {
                            // TODO why is this here?
                        }
                        expandNegativeX: while (x > 0) {
                            for (heightDx = underwaterDepth; heightDx <= lightValue; heightDx++) {
                                if ((occludeFlags[level][x - 1][heightDx] & 0x4) == 0) {
                                    break expandNegativeX;
                                }
                            }
                            x--;
                        }
                        expandPositiveX: while (lightZ < 104) {
                            for (heightDx = underwaterDepth; heightDx <= lightValue; heightDx++) {
                                if ((occludeFlags[level][lightZ + 1][heightDx] & 0x4) == 0) {
                                    break expandPositiveX;
                                }
                            }
                            lightZ++;
                        }
                        if ((lightZ + 1 - x) * (lightValue - (underwaterDepth - 1)) >= 4) {
                            heightDx = tileHeights[level][x][underwaterDepth];
                            SceneOccluder.addOccluder(4, x * 128, lightZ * 128 + 128, underwaterDepth * 128, lightValue * 128 + 128, heightDx, heightDx);
                            for (heightDz = x; heightDz <= lightZ; heightDz++) {
                                for (len = underwaterDepth; len <= lightValue; len++) {
                                    occludeFlags[level][heightDz][len] &= 0xFFFFFFFB;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!km", name = "f", descriptor = "(I)Z")
    public static boolean allLevelsAreVisible() {
        return GlRenderer.enabled ? true : Preferences.lowmem;
    }

    @OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(I)V")
    public static void ensureTilesExist(@OriginalArg(0) int plane) {
        frameCounter = plane;
        for (@Pc(3) int x = 0; x < width; x++) {
            for (@Pc(8) int z = 0; z < length; z++) {
                if (tiles[plane][x][z] == null) {
                    tiles[plane][x][z] = new Tile(plane, x, z);
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ib", name = "b", descriptor = "(I)V")
    public static void unload() {
        rowChroma = null;
        occludeFlags = null;
        rowCount = null;
        tileShapes = null;
        tileAngles = null;
        shadowmap = null;
        tileOverlays = null;
        tileUnderlays = null;
        rowSaturation = null;
        rowWeightedHue = null;
        rowLightness = null;
    }

    @OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(Z)V")
    public static void setUnderwater(@OriginalArg(0) boolean underwater) {
        if (underwater) {
            tiles = underWaterGroundTiles;
            tileHeights = underwaterTileHeights;
            underwaterHdTiles = underWaterHdTiles;
        } else {
            tiles = surfaceGroundTiles;
            tileHeights = surfaceTileHeights;
            underwaterHdTiles = surfaceHdTiles;
        }
        planes = tiles.length;
    }

    @OriginalMember(owner = "runetek4.client!p", name = "a", descriptor = "(IZIZLclient!mj;IIIBII)V")
    public static void addLoc(@OriginalArg(0) int currentlevel, @OriginalArg(1) boolean lowmem, @OriginalArg(2) int level, @OriginalArg(3) boolean highmem, @OriginalArg(4) CollisionMap collision, @OriginalArg(5) int locId, @OriginalArg(6) int shape, @OriginalArg(7) int x, @OriginalArg(9) int z, @OriginalArg(10) int rotation) {
        if (lowmem && !allLevelsAreVisible() && (renderFlags[0][x][z] & 0x2) == 0) {
            if ((renderFlags[level][x][z] & 0x10) != 0) {
                return;
            }
            if (getRenderLevel(z, x, level) != centralPlane) {
                return;
            }
        }
        if (level < firstVisibleLevel) {
            firstVisibleLevel = level;
        }
        @Pc(62) LocType locType = LocTypeList.get(locId);
        if (GlRenderer.enabled && locType.istexture) {
            return;
        }
        @Pc(84) int width;
        @Pc(81) int length;
        if (rotation == 1 || rotation == 3) {
            length = locType.width;
            width = locType.length;
        } else {
            width = locType.width;
            length = locType.length;
        }
        @Pc(103) int west;
        @Pc(112) int east;
        if (x + width <= 104) {
            west = x + (width >> 1);
            east = x + (width + 1 >> 1);
        } else {
            east = x + 1;
            west = x;
        }
        @Pc(129) int south;
        @Pc(133) int north;
        if (length + z > 104) {
            south = z;
            north = z + 1;
        } else {
            south = (length >> 1) + z;
            north = z + (length + 1 >> 1);
        }
        @Pc(153) int[][] currentHeightmap = tileHeights[currentlevel];
        @Pc(165) int fineX = (width << 6) + (x << 7);
        @Pc(173) int fineZ = (length << 6) + (z << 7);
        @Pc(199) int averageY = currentHeightmap[west][north] + currentHeightmap[east][south] + currentHeightmap[west][south] + currentHeightmap[east][north] >> 2;
        @Pc(201) int diffAverageY = 0;
        @Pc(213) int[][] heightmap;
        if (GlRenderer.enabled && currentlevel != 0) {
            heightmap = tileHeights[0];
            diffAverageY = averageY - (heightmap[east][north] + heightmap[east][south] + heightmap[west][south] + heightmap[west][north] >> 2);
        }
        heightmap = null;
        @Pc(261) long bitset = x | 0x40000000 | z << 7 | shape << 14 | rotation << 20;
        if (highmem) {
            heightmap = surfaceTileHeights[0];
        } else if (currentlevel < 3) {
            heightmap = tileHeights[currentlevel + 1];
        }
        if (locType.active == 0 || highmem) {
            bitset |= Long.MIN_VALUE;
        }
        if (locType.raiseobject == 1) {
            bitset |= 0x400000L;
        }
        if (locType.hasAnimation) {
            bitset |= 0x80000000L;
        }
        if (locType.hasBackgroundSound()) {
            AreaSoundManager.add(z, locType, rotation, null, x, level, null);
        }
        @Pc(330) boolean hasShadow = locType.hardshadow & !highmem;
        bitset |= (long) locId << 32;
        @Pc(387) Entity entity;
        @Pc(403) LocEntity locEntity;
        if (shape == LocType.GROUND_DECOR) {
            if (Preferences.showGroundDecorations || locType.active != 0 || locType.blockwalk == 1 || locType.forcedecor) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    locEntity = locType.method3428(rotation, fineX, currentHeightmap, 22, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                    if (GlRenderer.enabled && hasShadow) {
                        ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                    }
                    entity = locEntity.model;
                } else {
                    entity = new Loc(locId, 22, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                }
                setGroundDecor(level, x, z, averageY, entity, bitset, locType.renderUnderFeet);
                if (locType.blockwalk == 1 && collision != null) {
                    collision.flagGroundDecor(x, z);
                }
            }
        } else if (shape == LocType.CENTREPIECE_STRAIGHT || shape == LocType.CENTREPIECE_DIAGONAL) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                locEntity = locType.method3428(shape == LocType.CENTREPIECE_DIAGONAL ? rotation + 4 : rotation, fineX, currentHeightmap, 10, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                if (GlRenderer.enabled && hasShadow) {
                    ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                }
                entity = locEntity.model;
            } else {
                entity = new Loc(locId, 10, shape == 11 ? rotation + 4 : rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
            }
            if (entity != null) {
                @Pc(531) boolean wasAdded = addStaticLoc(level, x, z, averageY, width, length, entity, bitset);
                if (locType.hashardshadow && wasAdded && lowmem) {
                    @Pc(541) int shadowRadius = 15;
                    if (entity instanceof Model) {
                        shadowRadius = ((Model) entity).getLengthXZ() / 4;
                        if (shadowRadius > 30) {
                            shadowRadius = 30;
                        }
                    }
                    for (@Pc(560) int offsetX = 0; offsetX <= width; offsetX++) {
                        for (@Pc(565) int offsetZ = 0; offsetZ <= length; offsetZ++) {
                            if (shadowmap[level][x + offsetX][offsetZ + z] < shadowRadius) {
                                shadowmap[level][x + offsetX][z + offsetZ] = (byte) shadowRadius;
                            }
                        }
                    }
                }
            }
            if (locType.blockwalk != 0 && collision != null) {
                collision.flagScenery(x, locType.blockrange, z, width, length);
            }
        } else if (shape >= LocType.ROOF_STRAIGHT) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                locEntity = locType.method3428(rotation, fineX, currentHeightmap, shape, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                if (GlRenderer.enabled && hasShadow) {
                    ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                }
                entity = locEntity.model;
            } else {
                entity = new Loc(locId, shape, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
            }
            addStaticLoc(level, x, z, averageY, 1, 1, entity, bitset);
            if (lowmem && shape >= 12 && shape <= 17 && shape != 13 && level > 0) {
                occludeFlags[level][x][z] |= 0x4;
            }
            if (locType.blockwalk != 0 && collision != null) {
                collision.flagScenery(x, locType.blockrange, z, width, length);
            }
        } else if (shape == LocType.WALL_STRAIGHT) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                locEntity = locType.method3428(rotation, fineX, currentHeightmap, 0, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                if (GlRenderer.enabled && hasShadow) {
                    ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                }
                entity = locEntity.model;
            } else {
                entity = new Loc(locId, 0, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
            }
            addWall(level, x, z, averageY, entity, null, ROTATION_WALL_TYPE[rotation], 0, bitset);
            if (lowmem) {
                if (rotation == 0) {
                    if (locType.hashardshadow) {
                        shadowmap[level][x][z] = 50;
                        shadowmap[level][x][z + 1] = 50;
                    }
                    if (locType.occlude) {
                        occludeFlags[level][x][z] |= 0x1;
                    }
                } else if (rotation == 1) {
                    if (locType.hashardshadow) {
                        shadowmap[level][x][z + 1] = 50;
                        shadowmap[level][x + 1][z + 1] = 50;
                    }
                    if (locType.occlude) {
                        occludeFlags[level][x][z + 1] |= 0x2;
                    }
                } else if (rotation == 2) {
                    if (locType.hashardshadow) {
                        shadowmap[level][x + 1][z] = 50;
                        shadowmap[level][x + 1][z + 1] = 50;
                    }
                    if (locType.occlude) {
                        occludeFlags[level][x + 1][z] |= 0x1;
                    }
                } else if (rotation == 3) {
                    if (locType.hashardshadow) {
                        shadowmap[level][x][z] = 50;
                        shadowmap[level][x + 1][z] = 50;
                    }
                    if (locType.occlude) {
                        occludeFlags[level][x][z] |= 0x2;
                    }
                }
            }
            if (locType.blockwalk != 0 && collision != null) {
                collision.flagWall(x, z, shape, rotation, locType.blockrange);
            }
            if (locType.walloff != 16) {
                setWallDecoration(level, x, z, locType.walloff);
            }
        } else if (shape == LocType.WALL_DIAGONAL_CORNER) {
            if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                locEntity = locType.method3428(rotation, fineX, currentHeightmap, 1, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                if (GlRenderer.enabled && hasShadow) {
                    ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                }
                entity = locEntity.model;
            } else {
                entity = new Loc(locId, 1, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
            }
            addWall(level, x, z, averageY, entity, null, Wall.CORNER_FLAGS[rotation], 0, bitset);
            if (locType.hashardshadow && lowmem) {
                if (rotation == 0) {
                    shadowmap[level][x][z + 1] = 50;
                } else if (rotation == 1) {
                    shadowmap[level][x + 1][z + 1] = 50;
                } else if (rotation == 2) {
                    shadowmap[level][x + 1][z] = 50;
                } else if (rotation == 3) {
                    shadowmap[level][x][z] = 50;
                }
            }
            if (locType.blockwalk != 0 && collision != null) {
                collision.flagWall(x, z, shape, rotation, locType.blockrange);
            }
        } else {
            @Pc(1226) int wallOffset;
            if (shape == LocType.WALL_L) {
                wallOffset = rotation + 1 & 0x3;
                @Pc(1269) Entity secondaryWall;
                @Pc(1254) Entity primaryWall;
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    @Pc(1287) LocEntity wallEntity = locType.method3428(rotation + 4, fineX, currentHeightmap, 2, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                    if (GlRenderer.enabled && hasShadow) {
                        ShadowManager.method4211(wallEntity.sprite, fineX, diffAverageY, fineZ);
                    }
                    primaryWall = wallEntity.model;
                    wallEntity = locType.method3428(wallOffset, fineX, currentHeightmap, 2, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                    if (GlRenderer.enabled && hasShadow) {
                        ShadowManager.method4211(wallEntity.sprite, fineX, diffAverageY, fineZ);
                    }
                    secondaryWall = wallEntity.model;
                } else {
                    primaryWall = new Loc(locId, 2, rotation + 4, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                    secondaryWall = new Loc(locId, 2, wallOffset, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                }
                addWall(level, x, z, averageY, primaryWall, secondaryWall, ROTATION_WALL_TYPE[rotation], ROTATION_WALL_TYPE[wallOffset], bitset);
                if (locType.occlude && lowmem) {
                    if (rotation == 0) {
                        occludeFlags[level][x][z] |= 0x1;
                        occludeFlags[level][x][z + 1] |= 0x2;
                    } else if (rotation == 1) {
                        occludeFlags[level][x][z + 1] |= 0x2;
                        occludeFlags[level][x + 1][z] |= 0x1;
                    } else if (rotation == 2) {
                        occludeFlags[level][x + 1][z] |= 0x1;
                        occludeFlags[level][x][z] |= 0x2;
                    } else if (rotation == 3) {
                        occludeFlags[level][x][z] |= 0x2;
                        occludeFlags[level][x][z] |= 0x1;
                    }
                }
                if (locType.blockwalk != 0 && collision != null) {
                    collision.flagWall(x, z, shape, rotation, locType.blockrange);
                }
                if (locType.walloff != 16) {
                    setWallDecoration(level, x, z, locType.walloff);
                }
            } else if (shape == LocType.WALL_SQUARE_CORNER) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    locEntity = locType.method3428(rotation, fineX, currentHeightmap, 3, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                    if (GlRenderer.enabled && hasShadow) {
                        ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                    }
                    entity = locEntity.model;
                } else {
                    entity = new Loc(locId, 3, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                }
                addWall(level, x, z, averageY, entity, null, Wall.CORNER_FLAGS[rotation], 0, bitset);
                if (locType.hashardshadow && lowmem) {
                    if (rotation == 0) {
                        shadowmap[level][x][z + 1] = 50;
                    } else if (rotation == 1) {
                        shadowmap[level][x + 1][z + 1] = 50;
                    } else if (rotation == 2) {
                        shadowmap[level][x + 1][z] = 50;
                    } else if (rotation == 3) {
                        shadowmap[level][x][z] = 50;
                    }
                }
                if (locType.blockwalk != 0 && collision != null) {
                    collision.flagWall(x, z, shape, rotation, locType.blockrange);
                }
            } else if (shape == LocType.WALL_DIAGONAL) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    locEntity = locType.method3428(rotation, fineX, currentHeightmap, shape, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                    if (GlRenderer.enabled && hasShadow) {
                        ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                    }
                    entity = locEntity.model;
                } else {
                    entity = new Loc(locId, shape, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                }
                addStaticLoc(level, x, z, averageY, 1, 1, entity, bitset);
                if (locType.blockwalk != 0 && collision != null) {
                    collision.flagScenery(x, locType.blockrange, z, width, length);
                }
                if (locType.walloff != 16) {
                    setWallDecoration(level, x, z, locType.walloff);
                }
            } else if (shape == LocType.WALLDECOR_STRAIGHT_NOOFFSET) {
                if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                    locEntity = locType.method3428(rotation, fineX, currentHeightmap, 4, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                    if (GlRenderer.enabled && hasShadow) {
                        ShadowManager.method4211(locEntity.sprite, fineX, diffAverageY, fineZ);
                    }
                    entity = locEntity.model;
                } else {
                    entity = new Loc(locId, 4, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                }
                addWallDecoration(level, x, z, averageY, entity, null, ROTATION_WALL_TYPE[rotation], 0, 0, 0, bitset);
            } else {
                @Pc(1889) long existingWallKey;
                @Pc(1934) Entity wallDecor;
                @Pc(1950) LocEntity wallDecorEntity;
                if (shape == LocType.WALLDECOR_STRAIGHT_OFFSET) {
                    wallOffset = 16;
                    existingWallKey = getWallKey(level, x, z);
                    if (existingWallKey != 0L) {
                        wallOffset = LocTypeList.get(Integer.MAX_VALUE & (int) (existingWallKey >>> 32)).walloff;
                    }
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        wallDecorEntity = locType.method3428(rotation, fineX, currentHeightmap, 4, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                        if (GlRenderer.enabled && hasShadow) {
                            ShadowManager.method4211(wallDecorEntity.sprite, fineX - WALL_DECORATION_ROTATION_FORWARD_X[rotation] * 8, diffAverageY, fineZ - WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * 8);
                        }
                        wallDecor = wallDecorEntity.model;
                    } else {
                        wallDecor = new Loc(locId, 4, rotation, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                    }
                    addWallDecoration(level, x, z, averageY, wallDecor, null, ROTATION_WALL_TYPE[rotation], 0, wallOffset * WALL_DECORATION_ROTATION_FORWARD_X[rotation], WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * wallOffset, bitset);
                } else if (shape == LocType.WALLDECOR_DIAGONAL_OFFSET) {
                    wallOffset = 8;
                    existingWallKey = getWallKey(level, x, z);
                    if (existingWallKey != 0L) {
                        wallOffset = LocTypeList.get(Integer.MAX_VALUE & (int) (existingWallKey >>> 32)).walloff / 2;
                    }
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        wallDecorEntity = locType.method3428(rotation + 4, fineX, currentHeightmap, 4, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                        if (GlRenderer.enabled && hasShadow) {
                            ShadowManager.method4211(wallDecorEntity.sprite, fineX - CORNER_OFFSET_Z[rotation] * 8, diffAverageY, fineZ - CORNER_OFFSET_X[rotation] * 8);
                        }
                        wallDecor = wallDecorEntity.model;
                    } else {
                        wallDecor = new Loc(locId, 4, rotation + 4, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                    }
                    addWallDecoration(level, x, z, averageY, wallDecor, null, 256, rotation, wallOffset * CORNER_OFFSET_Z[rotation], wallOffset * CORNER_OFFSET_X[rotation], bitset);
                } else if (shape == LocType.WALLDECOR_DIAGONAL_NOOFFSET) {
                    @Pc(2137) int oppositeRotation = rotation + 2 & 0x3;
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        @Pc(2183) LocEntity decorEntity = locType.method3428(oppositeRotation + 4, fineX, currentHeightmap, 4, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                        if (GlRenderer.enabled && hasShadow) {
                            ShadowManager.method4211(decorEntity.sprite, fineX, diffAverageY, fineZ);
                        }
                        entity = decorEntity.model;
                    } else {
                        entity = new Loc(locId, 4, oppositeRotation + 4, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                    }
                    addWallDecoration(level, x, z, averageY, entity, null, 256, oppositeRotation, 0, 0, bitset);
                } else if (shape == LocType.WALLDECOR_DIAGONAL_BOTH) {
                    wallOffset = 8;
                    existingWallKey = getWallKey(level, x, z);
                    if (existingWallKey != 0L) {
                        wallOffset = LocTypeList.get(Integer.MAX_VALUE & (int) (existingWallKey >>> 32)).walloff / 2;
                    }
                    @Pc(2244) int oppositeRotation2 = rotation + 2 & 0x3;
                    @Pc(2289) Entity secondaryDecor;
                    if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
                        @Pc(2297) int offsetZ8 = CORNER_OFFSET_X[rotation] * 8;
                        @Pc(2303) int offsetX8 = CORNER_OFFSET_Z[rotation] * 8;
                        @Pc(2319) LocEntity decorEntity2 = locType.method3428(rotation + 4, fineX, currentHeightmap, 4, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                        if (GlRenderer.enabled && hasShadow) {
                            ShadowManager.method4211(decorEntity2.sprite, fineX - offsetX8, diffAverageY, fineZ - offsetZ8);
                        }
                        wallDecor = decorEntity2.model;
                        decorEntity2 = locType.method3428(oppositeRotation2 + 4, fineX, currentHeightmap, 4, averageY, heightmap, lowmem, null, hasShadow, fineZ);
                        if (GlRenderer.enabled && hasShadow) {
                            ShadowManager.method4211(decorEntity2.sprite, fineX - offsetX8, diffAverageY, fineZ - offsetZ8);
                        }
                        secondaryDecor = decorEntity2.model;
                    } else {
                        wallDecor = new Loc(locId, 4, rotation + 4, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                        secondaryDecor = new Loc(locId, 4, oppositeRotation2 + 4, currentlevel, x, z, locType.anim, locType.randomanimframe, null);
                    }
                    addWallDecoration(level, x, z, averageY, wallDecor, secondaryDecor, 256, rotation, wallOffset * CORNER_OFFSET_Z[rotation], CORNER_OFFSET_X[rotation] * wallOffset, bitset);
                }
            }
        }
    }

    @OriginalMember(owner = "client!bm", name = "a", descriptor = "(IBIIII)V")
    public static void initializeEmptyTerrain(@OriginalArg(0) int plane, @OriginalArg(2) int startZ, @OriginalArg(3) int startX, @OriginalArg(4) int width, @OriginalArg(5) int height) {
        @Pc(3) int z;
        @Pc(10) int x;
        for (z = startZ; z <= width + startZ; z++) {
            for (x = startX; x <= height + startX; x++) {
                if (x >= 0 && x < 104 && z >= 0 && z < 104) {
                    shadowmap[plane][x][z] = 127;
                }
            }
        }
        for (z = startZ; z < width + startZ; z++) {
            for (x = startX; x < startX + height; x++) {
                if (x >= 0 && x < 104 && z >= 0 && z < 104) {
                    tileHeights[plane][x][z] = plane <= 0 ? 0 : tileHeights[plane - 1][x][z];
                }
            }
        }
        if (startX > 0 && startX < 104) {
            for (z = startZ + 1; z < startZ + width; z++) {
                if (z >= 0 && z < 104) {
                    tileHeights[plane][startX][z] = tileHeights[plane][startX - 1][z];
                }
            }
        }
        if (startZ > 0 && startZ < 104) {
            for (z = startX + 1; z < startX + height; z++) {
                if (z >= 0 && z < 104) {
                    tileHeights[plane][z][startZ] = tileHeights[plane][z][startZ - 1];
                }
            }
        }
        if (startX < 0 || startZ < 0 || startX >= 104 || startZ >= 104) {
            return;
        }
        if (plane == 0) {
            if (startX > 0 && tileHeights[plane][startX - 1][startZ] != 0) {
                tileHeights[plane][startX][startZ] = tileHeights[plane][startX - 1][startZ];
            } else if (startZ > 0 && tileHeights[plane][startX][startZ - 1] != 0) {
                tileHeights[plane][startX][startZ] = tileHeights[plane][startX][startZ - 1];
            } else if (startX > 0 && startZ > 0 && tileHeights[plane][startX - 1][startZ - 1] != 0) {
                tileHeights[plane][startX][startZ] = tileHeights[plane][startX - 1][startZ - 1];
            }
        } else if (startX > 0 && tileHeights[plane - 1][startX - 1][startZ] != tileHeights[plane][startX - 1][startZ]) {
            tileHeights[plane][startX][startZ] = tileHeights[plane][startX - 1][startZ];
        } else if (startZ > 0 && tileHeights[plane][startX][startZ - 1] != tileHeights[plane - 1][startX][startZ - 1]) {
            tileHeights[plane][startX][startZ] = tileHeights[plane][startX][startZ - 1];
        } else if (startX > 0 && startZ > 0 && tileHeights[plane][startX - 1][startZ - 1] != tileHeights[plane - 1][startX - 1][startZ - 1]) {
            tileHeights[plane][startX][startZ] = tileHeights[plane][startX - 1][startZ - 1];
        }
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "([Lclient!mj;ZIIIII[B)V")
    public static void loadMapRegion(@OriginalArg(0) CollisionMap[] collisionMaps, @OriginalArg(1) boolean underwater, @OriginalArg(2) int offsetX, @OriginalArg(3) int baseZ, @OriginalArg(5) int baseX, @OriginalArg(6) int offsetZ, @OriginalArg(7) byte[] mapData) {
        @Pc(14) int x;
        @Pc(21) int z;
        if (!underwater) {
            for (@Pc(9) int plane = 0; plane < 4; plane++) {
                for (x = 0; x < 64; x++) {
                    for (z = 0; z < 64; z++) {
                        if (baseX + x > 0 && x + baseX < 103 && baseZ + z > 0 && z + baseZ < 103) {
                            collisionMaps[plane].flags[x + baseX][baseZ + z] &= 0xFEFFFFFF;
                        }
                    }
                }
            }
        }
        @Pc(95) Packet packet = new Packet(mapData);
        @Pc(99) byte planeCount;
        if (underwater) {
            planeCount = 1;
        } else {
            planeCount = 4;
        }
        @Pc(117) int tileZ;
        for (x = 0; x < planeCount; x++) {
            for (z = 0; z < 64; z++) {
                for (tileZ = 0; tileZ < 64; tileZ++) {
                    readTile(offsetX, offsetZ, underwater, packet, tileZ + baseZ, baseX + z, 0, x);
                }
            }
        }
        @Pc(146) boolean hasHeightData = false;
        @Pc(243) int startX;
        @Pc(188) int local188;
        @Pc(190) int startZ;
        @Pc(194) int endZ;
        while (packet.offset < packet.data.length) {
            z = packet.g1();
            if (z != 129) {
                packet.offset--;
                break;
            }
            for (tileZ = 0; tileZ < 4; tileZ++) {
                @Pc(168) byte heightDataType = packet.g1s();
                if (heightDataType == 0) {
                    startX = baseX;
                    if (baseX < 0) {
                        startX = 0;
                    } else if (baseX >= 104) {
                        startX = 104;
                    }
                    startZ = baseZ;
                    if (baseZ < 0) {
                        startZ = 0;
                    } else if (baseZ >= 104) {
                        startZ = 104;
                    }
                    local188 = baseX + 64;
                    endZ = baseZ + 64;
                    if (endZ < 0) {
                        endZ = 0;
                    } else if (endZ >= 104) {
                        endZ = 104;
                    }
                    if (local188 < 0) {
                        local188 = 0;
                    } else if (local188 >= 104) {
                        local188 = 104;
                    }
                    while (startX < local188) {
                        while (startZ < endZ) {
                            tileSettings[tileZ][startX][startZ] = 0;
                            startZ++;
                        }
                        startX++;
                    }
                } else if (heightDataType == 1) {
                    for (startX = 0; startX < 64; startX += 4) {
                        for (local188 = 0; local188 < 64; local188 += 4) {
                            @Pc(305) byte heightValue = packet.g1s();
                            for (endZ = startX + baseX; endZ < baseX + startX + 4; endZ++) {
                                for (@Pc(320) int tileZ2 = baseZ + local188; tileZ2 < baseZ + local188 + 4; tileZ2++) {
                                    if (endZ >= 0 && endZ < 104 && tileZ2 >= 0 && tileZ2 < 104) {
                                        tileSettings[tileZ][endZ][tileZ2] = heightValue;
                                    }
                                }
                            }
                        }
                    }
                } else if (heightDataType == 2 && tileZ > 0) {
                    local188 = baseX + 64;
                    startZ = baseZ;
                    endZ = baseZ + 64;
                    if (local188 < 0) {
                        local188 = 0;
                    } else if (local188 >= 104) {
                        local188 = 104;
                    }
                    if (baseZ < 0) {
                        startZ = 0;
                    } else if (baseZ >= 104) {
                        startZ = 104;
                    }
                    if (endZ < 0) {
                        endZ = 0;
                    } else if (endZ >= 104) {
                        endZ = 104;
                    }
                    startX = baseX;
                    if (baseX < 0) {
                        startX = 0;
                    } else if (baseX >= 104) {
                        startX = 104;
                    }
                    while (local188 > startX) {
                        while (startZ < endZ) {
                            tileSettings[tileZ][startX][startZ] = tileSettings[tileZ - 1][startX][startZ];
                            startZ++;
                        }
                        startX++;
                    }
                }
            }
            hasHeightData = true;
        }
        @Pc(515) int lightCount;
        if (GlRenderer.enabled && !underwater) {
            @Pc(490) Environment environment = null;
            environmentProcessingComplete: while (true) {
                lightProcessingLoop: do {
                    while (packet.offset < packet.data.length) {
                        tileZ = packet.g1();
                        if (tileZ != 0) {
                            if (tileZ != 1) {
                                throw new IllegalStateException();
                            }
                            lightCount = packet.g1();
                            continue lightProcessingLoop;
                        }
                        environment = new Environment(packet);
                    }
                    if (environment == null) {
                        environment = new Environment();
                    }
                    for (tileZ = 0; tileZ < 8; tileZ++) {
                        for (lightCount = 0; lightCount < 8; lightCount++) {
                            startX = tileZ + (baseX >> 3);
                            local188 = (baseZ >> 3) + lightCount;
                            if (startX >= 0 && startX < 13 && local188 >= 0 && local188 < 13) {
                                FogManager.chunksAtmosphere[startX][local188] = environment;
                            }
                        }
                    }
                    break environmentProcessingComplete;
                } while (lightCount <= 0);
                for (startX = 0; startX < lightCount; startX++) {
                    @Pc(529) Light light = new Light(packet);
                    if (light.anInt2243 == 31) {
                        @Pc(541) LightType lightType = LightTypeList.get(packet.g2());
                        light.method1762(lightType.anInt2865, lightType.anInt2873, lightType.anInt2867, lightType.anInt2872);
                    }
                    light.z += baseZ << 7;
                    light.x += baseX << 7;
                    endZ = light.z >> 7;
                    startZ = light.x >> 7;
                    if (startZ >= 0 && endZ >= 0 && startZ < 104 && endZ < 104) {
                        light.aBoolean125 = (renderFlags[1][startZ][endZ] & 0x2) != 0;
                        light.y = tileHeights[light.level][startZ][endZ] - light.y;
                        LightingManager.method2389(light);
                    }
                }
            }
        }
        if (hasHeightData) {
            return;
        }
        for (z = 0; z < 4; z++) {
            for (tileZ = 0; tileZ < 16; tileZ++) {
                for (lightCount = 0; lightCount < 16; lightCount++) {
                    startX = (baseX >> 2) + tileZ;
                    local188 = lightCount + (baseZ >> 2);
                    if (startX >= 0 && startX < 26 && local188 >= 0 && local188 < 26) {
                        tileSettings[z][startX][local188] = 0;
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!v", name = "a", descriptor = "(IIIJ)Z")
    public static boolean isLocValid(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) long key) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return false;
        } else if (tile.wall != null && tile.wall.key == key) {
            return true;
        } else if (tile.wallDecor != null && tile.wallDecor.key == key) {
            return true;
        } else if (tile.groundDecor != null && tile.groundDecor.key == key) {
            return true;
        } else {
            for (@Pc(46) int local46 = 0; local46 < tile.sceneryLen; local46++) {
                if (tile.scenery[local46].key == key) {
                    return true;
                }
            }
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!pb", name = "b", descriptor = "(III)Lclient!jj;")
    public static ObjStackEntity removeGroundObjects(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return null;
        } else {
            @Pc(14) ObjStackEntity entity = tile.objStack;
            tile.objStack = null;
            return entity;
        }
    }

    @OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(III)Lclient!jh;")
    public static Wall removeWall(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return null;
        } else {
            @Pc(14) Wall wallAtTile = tile.wall;
            tile.wall = null;
            return wallAtTile;
        }
    }

    @OriginalMember(owner = "client!gj", name = "a", descriptor = "(III)Lclient!df;")
    public static WallDecor getWallDecor(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        return tile == null ? null : tile.wallDecor;
    }

    @OriginalMember(owner = "runetek4.client!um", name = "c", descriptor = "(III)Z")
    public static boolean isInOccluderBounds(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int z) {
        for (@Pc(1) int occluderIndex = 0; occluderIndex < lightSourceCount; occluderIndex++) {
            @Pc(8) SceneOccluder occluder = aSceneGraphClass120Array2[occluderIndex];
            @Pc(17) int delta;
            @Pc(29) int minBound1;
            @Pc(39) int maxBound1;
            @Pc(49) int minBound2;
            @Pc(59) int maxBound2;
            if (occluder.anInt4462 == 1) {
                delta = occluder.worldX1 - x;
                if (delta > 0) {
                    minBound1 = occluder.worldX2 + (occluder.anInt4454 * delta >> 8);
                    maxBound1 = occluder.worldZ2 + (occluder.anInt4450 * delta >> 8);
                    minBound2 = occluder.minY + (occluder.anInt4459 * delta >> 8);
                    maxBound2 = occluder.maxY + (occluder.anInt4463 * delta >> 8);
                    if (z >= minBound1 && z <= maxBound1 && y >= minBound2 && y <= maxBound2) {
                        return true;
                    }
                }
            } else if (occluder.anInt4462 == 2) {
                delta = x - occluder.worldX1;
                if (delta > 0) {
                    minBound1 = occluder.worldX2 + (occluder.anInt4454 * delta >> 8);
                    maxBound1 = occluder.worldZ2 + (occluder.anInt4450 * delta >> 8);
                    minBound2 = occluder.minY + (occluder.anInt4459 * delta >> 8);
                    maxBound2 = occluder.maxY + (occluder.anInt4463 * delta >> 8);
                    if (z >= minBound1 && z <= maxBound1 && y >= minBound2 && y <= maxBound2) {
                        return true;
                    }
                }
            } else if (occluder.anInt4462 == 3) {
                delta = occluder.worldX2 - z;
                if (delta > 0) {
                    minBound1 = occluder.worldX1 + (occluder.anInt4448 * delta >> 8);
                    maxBound1 = occluder.worldZ1 + (occluder.anInt4456 * delta >> 8);
                    minBound2 = occluder.minY + (occluder.anInt4459 * delta >> 8);
                    maxBound2 = occluder.maxY + (occluder.anInt4463 * delta >> 8);
                    if (x >= minBound1 && x <= maxBound1 && y >= minBound2 && y <= maxBound2) {
                        return true;
                    }
                }
            } else if (occluder.anInt4462 == 4) {
                delta = z - occluder.worldX2;
                if (delta > 0) {
                    minBound1 = occluder.worldX1 + (occluder.anInt4448 * delta >> 8);
                    maxBound1 = occluder.worldZ1 + (occluder.anInt4456 * delta >> 8);
                    minBound2 = occluder.minY + (occluder.anInt4459 * delta >> 8);
                    maxBound2 = occluder.maxY + (occluder.anInt4463 * delta >> 8);
                    if (x >= minBound1 && x <= maxBound1 && y >= minBound2 && y <= maxBound2) {
                        return true;
                    }
                }
            } else if (occluder.anInt4462 == 5) {
                delta = y - occluder.minY;
                if (delta > 0) {
                    minBound1 = occluder.worldX1 + (occluder.anInt4448 * delta >> 8);
                    maxBound1 = occluder.worldZ1 + (occluder.anInt4456 * delta >> 8);
                    minBound2 = occluder.worldX2 + (occluder.anInt4454 * delta >> 8);
                    maxBound2 = occluder.worldZ2 + (occluder.anInt4450 * delta >> 8);
                    if (x >= minBound1 && x <= maxBound1 && z >= minBound2 && z <= maxBound2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIZLclient!wa;IIBII)V")
    public static void readTile(@OriginalArg(0) int baseX, @OriginalArg(1) int baseZ, @OriginalArg(2) boolean isDynamic, @OriginalArg(3) Packet packet, @OriginalArg(4) int z, @OriginalArg(5) int x, @OriginalArg(7) int rotation, @OriginalArg(8) int level) {
        @Pc(32) int opcode;
        if (x < 0 || x >= 104 || z < 0 || z >= 104) {
            while (true) {
                opcode = packet.g1();
                if (opcode == 0) {
                    break;
                }
                if (opcode == 1) {
                    packet.g1();
                    break;
                }
                if (opcode <= 49) {
                    packet.g1();
                }
            }
            return;
        }
        if (!isDynamic) {
            renderFlags[level][x][z] = 0;
        }
        while (true) {
            opcode = packet.g1();
            if (opcode == 0) {
                if (isDynamic) {
                    tileHeights[0][x][z] = surfaceTileHeights[0][x][z];
                } else if (level == 0) {
                    tileHeights[0][x][z] = -PerlinNoise.getTileHeight(z + baseZ + 556238, baseX + x + 932731) * 8;
                } else {
                    tileHeights[level][x][z] = tileHeights[level - 1][x][z] - 240;
                }
                break;
            }
            if (opcode == 1) {
                @Pc(111) int heightOffset = packet.g1();
                if (isDynamic) {
                    tileHeights[0][x][z] = surfaceTileHeights[0][x][z] + heightOffset * 8;
                } else {
                    if (heightOffset == 1) {
                        heightOffset = 0;
                    }
                    if (level == 0) {
                        tileHeights[0][x][z] = -heightOffset * 8;
                    } else {
                        tileHeights[level][x][z] = tileHeights[level - 1][x][z] - heightOffset * 8;
                    }
                }
                break;
            }
            if (opcode <= 49) {
                tileOverlays[level][x][z] = packet.g1s();
                tileShapes[level][x][z] = (byte) ((opcode - 2) / 4);
                tileAngles[level][x][z] = (byte) (opcode + rotation - 2 & 0x3);
            } else if (opcode > 81) {
                tileUnderlays[level][x][z] = (byte) (opcode - 81);
            } else if (!isDynamic) {
                renderFlags[level][x][z] = (byte) (opcode - 49);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!hd", name = "a", descriptor = "(IIIIIIII)V")
    public static void attachLocToTile(@OriginalArg(0) int plane, @OriginalArg(2) int rotation, @OriginalArg(3) int shape, @OriginalArg(4) int z, @OriginalArg(5) int layer, @OriginalArg(6) int x, @OriginalArg(7) int height) {
        if (x < 0 || z < 0 || x >= 103 || z >= 103) {
            return;
        }
        @Pc(38) int locId;
        if (layer == 0) {
            @Pc(28) Wall wall = getWall(plane, x, z);
            if (wall != null) {
                locId = Integer.MAX_VALUE & (int) (wall.key >>> 32);
                if (shape == 2) {
                    wall.primary = new Loc(locId, 2, rotation + 4, plane, x, z, height, false, wall.primary);
                    wall.secondary = new Loc(locId, 2, rotation + 1 & 0x3, plane, x, z, height, false, wall.secondary);
                } else {
                    wall.primary = new Loc(locId, shape, rotation, plane, x, z, height, false, wall.primary);
                }
            }
        }
        if (layer == 1) {
            @Pc(106) WallDecor wallDecor = getWallDecor(plane, x, z);
            if (wallDecor != null) {
                locId = (int) (wallDecor.key >>> 32) & Integer.MAX_VALUE;
                if (shape == 4 || shape == 5) {
                    wallDecor.primary = new Loc(locId, 4, rotation, plane, x, z, height, false, wallDecor.primary);
                } else if (shape == 6) {
                    wallDecor.primary = new Loc(locId, 4, rotation + 4, plane, x, z, height, false, wallDecor.primary);
                } else if (shape == 7) {
                    wallDecor.primary = new Loc(locId, 4, (rotation + 2 & 0x3) + 4, plane, x, z, height, false, wallDecor.primary);
                } else if (shape == 8) {
                    wallDecor.primary = new Loc(locId, 4, rotation + 4, plane, x, z, height, false, wallDecor.primary);
                    wallDecor.secondary = new Loc(locId, 4, (rotation + 2 & 0x3) + 4, plane, x, z, height, false, wallDecor.secondary);
                }
            }
        }
        if (layer == 2) {
            if (shape == 11) {
                shape = 10;
            }
            @Pc(255) Scenery scenery = getScenery(plane, x, z);
            if (scenery != null) {
                scenery.entity = new Loc((int) (scenery.key >>> 32) & Integer.MAX_VALUE, shape, rotation, plane, x, z, height, false, scenery.entity);
            }
        }
        if (layer == 3) {
            @Pc(290) GroundDecor groundDecor = getGroundDecor(plane, x, z);
            if (groundDecor != null) {
                groundDecor.entity = new Loc(Integer.MAX_VALUE & (int) (groundDecor.key >>> 32), 22, rotation, plane, x, z, height, false, groundDecor.entity);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(IIIILclient!th;JZ)V")
    public static void setGroundDecor(@OriginalArg(0) int level, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int height, @OriginalArg(4) Entity entity, @OriginalArg(5) long key, @OriginalArg(6) boolean clickable) {
        if (entity == null) {
            return;
        }
        @Pc(6) GroundDecor groundDecor = new GroundDecor();
        groundDecor.entity = entity;
        groundDecor.xFine = x * 128 + 64;
        groundDecor.zFine = z * 128 + 64;
        groundDecor.yFine = height;
        groundDecor.key = key;
        groundDecor.interactive = clickable;
        if (tiles[level][x][z] == null) {
            tiles[level][x][z] = new Tile(level, x, z);
        }
        tiles[level][x][z].groundDecor = groundDecor;
    }

    @OriginalMember(owner = "runetek4.client!ia", name = "a", descriptor = "(IB)I")
    public static int getCollisionFlag(@OriginalArg(0) int shapeAndRotation) {
        @Pc(11) int shape = shapeAndRotation & 0x3F;
        @Pc(17) int rotation = shapeAndRotation >> 6 & 0x3;
        if (shape == 18) {
            if (rotation == 0) {
                return 1;
            }
            if (rotation == 1) {
                return 2;
            }
            if (rotation == 2) {
                return 4;
            }
            if (rotation == 3) {
                return 8;
            }
        } else if (shape == 19 || shape == 21) {
            if (rotation == 0) {
                return 16;
            }
            if (rotation == 1) {
                return 32;
            }
            if (rotation == 2) {
                return 64;
            }
            if (rotation == 3) {
                return 128;
            }
        }
        return 0;
    }

    @OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIJ)V")
    public static void addWall(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int height, @OriginalArg(4) Entity primary, @OriginalArg(5) Entity secondary, @OriginalArg(6) int typeA, @OriginalArg(7) int typeB, @OriginalArg(8) long key) {
        if (primary == null && secondary == null) {
            return;
        }
        @Pc(8) Wall wall = new Wall();
        wall.key = key;
        wall.xFine = x * 128 + 64;
        wall.zFine = z * 128 + 64;
        wall.yFine = height;
        wall.primary = primary;
        wall.secondary = secondary;
        wall.typeA = typeA;
        wall.typeB = typeB;
        for (@Pc(42) int currentPlane = plane; currentPlane >= 0; currentPlane--) {
            if (tiles[currentPlane][x][z] == null) {
                tiles[currentPlane][x][z] = new Tile(currentPlane, x, z);
            }
        }
        tiles[plane][x][z].wall = wall;
    }

    @OriginalMember(owner = "client!g", name = "a", descriptor = "(III)Lclient!df;")
    public static WallDecor removeWallDecor(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return null;
        } else {
            @Pc(14) WallDecor local14 = tile.wallDecor;
            tile.wallDecor = null;
            return local14;
        }
    }

    @OriginalMember(owner = "runetek4.client!dk", name = "a", descriptor = "(III)Lclient!ec;")
    public static Scenery removeScenery(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return null;
        }
        for (@Pc(13) int i = 0; i < tile.sceneryLen; i++) {
            @Pc(22) Scenery scenery = tile.scenery[i];
            if ((scenery.key >> 29 & 0x3L) == 2L && scenery.xMin == x && scenery.zMin == z) {
                removeScenery(scenery);
                return scenery;
            }
        }
        return null;
    }

    @OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "(III)Lclient!bm;")
    public static GroundDecor removeGroundDecor(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return null;
        } else {
            @Pc(14) GroundDecor groundDecor = tile.groundDecor;
            tile.groundDecor = null;
            return groundDecor;
        }
    }

    @OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(Lclient!ec;)V")
    public static void removeScenery(@OriginalArg(0) Scenery scenery) {
        for (@Pc(2) int x = scenery.xMin; x <= scenery.xMax; x++) {
            for (@Pc(9) int z = scenery.zMin; z <= scenery.zMax; z++) {
                @Pc(22) Tile tile = tiles[scenery.level][x][z];
                if (tile != null) {
                    @Pc(26) int i;
                    for (i = 0; i < tile.sceneryLen; i++) {
                        if (tile.scenery[i] == scenery) {
                            tile.sceneryLen--;
                            for (@Pc(44) int j = i; j < tile.sceneryLen; j++) {
                                tile.scenery[j] = tile.scenery[j + 1];
                                tile.layerCollisionFlags[j] = tile.layerCollisionFlags[j + 1];
                            }
                            tile.scenery[tile.sceneryLen] = null;
                            break;
                        }
                    }
                    tile.combinedFlags = 0;
                    for (i = 0; i < tile.sceneryLen; i++) {
                        tile.combinedFlags |= tile.layerCollisionFlags[i];
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(I[Lclient!hg;)V")
    public static void setUnderwaterHdTiles(@OriginalArg(0) int plane, @OriginalArg(1) GlTile[] tiles) {
        underwaterHdTiles[plane] = tiles;
    }

    @OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IIIIIIIIIIIIIIIIIIII)V")
    public static void setTile(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int shape, @OriginalArg(4) int rotation, @OriginalArg(5) int underlay, @OriginalArg(6) int swHeight, @OriginalArg(7) int seHeight, @OriginalArg(8) int neHeight, @OriginalArg(9) int nwHeight, @OriginalArg(10) int swColor, @OriginalArg(11) int seColor, @OriginalArg(12) int neColor, @OriginalArg(13) int nwColor, @OriginalArg(14) int swColorOverlay, @OriginalArg(15) int seColorOverlay, @OriginalArg(16) int neColorOverlay, @OriginalArg(17) int nwColorOverlay, @OriginalArg(18) int overlay, @OriginalArg(19) int overlayPath) {
        @Pc(12) PlainTile tile;
        @Pc(14) int currentLevel;
        if (shape == 0) {
            tile = new PlainTile(swColor, seColor, neColor, nwColor, -1, overlay, false);
            for (currentLevel = plane; currentLevel >= 0; currentLevel--) {
                if (tiles[currentLevel][x][z] == null) {
                    tiles[currentLevel][x][z] = new Tile(currentLevel, x, z);
                }
            }
            tiles[plane][x][z].plainTile = tile;
        } else if (shape == 1) {
            tile = new PlainTile(swColorOverlay, seColorOverlay, neColorOverlay, nwColorOverlay, underlay, overlayPath, swHeight == seHeight && swHeight == neHeight && swHeight == nwHeight);
            for (currentLevel = plane; currentLevel >= 0; currentLevel--) {
                if (tiles[currentLevel][x][z] == null) {
                    tiles[currentLevel][x][z] = new Tile(currentLevel, x, z);
                }
            }
            tiles[plane][x][z].plainTile = tile;
        } else {
            @Pc(134) ShapedTile shapedTile = new ShapedTile(shape, rotation, underlay, x, z, swHeight, seHeight, neHeight, nwHeight, swColor, seColor, neColor, nwColor, swColorOverlay, seColorOverlay, neColorOverlay, nwColorOverlay, overlay, overlayPath);
            for (currentLevel = plane; currentLevel >= 0; currentLevel--) {
                if (tiles[currentLevel][x][z] == null) {
                    tiles[currentLevel][x][z] = new Tile(currentLevel, x, z);
                }
            }
            tiles[plane][x][z].shapedTile = shapedTile;
        }
    }

    @OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(III)V")
    public static void buildSceneModels() {
        for (@Pc(1) int planes = 0; planes < SceneGraph.planes; planes++) {
            for (@Pc(6) int x = 0; x < width; x++) {
                for (@Pc(11) int z = 0; z < length; z++) {
                    @Pc(22) Tile tile = tiles[planes][x][z];
                    if (tile != null) {
                        @Pc(27) Wall wall = tile.wall;
                        if (wall != null && wall.primary.method4543()) {
                            connectAdjacentEntities(wall.primary, planes, x, z, 1, 1);
                            if (wall.secondary != null && wall.secondary.method4543()) {
                                connectAdjacentEntities(wall.secondary, planes, x, z, 1, 1);
                                wall.primary.method4544(wall.secondary, 0, 0, 0, false);
                                wall.secondary = wall.secondary.createModel();
                            }
                            wall.primary = wall.primary.createModel();
                        }
                        for (@Pc(83) int sceneryIndex = 0; sceneryIndex < tile.sceneryLen; sceneryIndex++) {
                            @Pc(92) Scenery scenery = tile.scenery[sceneryIndex];
                            if (scenery != null && scenery.entity.method4543()) {
                                connectAdjacentEntities(scenery.entity, planes, x, z, scenery.xMax + 1 - scenery.xMin, scenery.zMax - scenery.zMin + 1);
                                scenery.entity = scenery.entity.createModel();
                            }
                        }
                        @Pc(131) GroundDecor groundDecor = tile.groundDecor;
                        if (groundDecor != null && groundDecor.entity.method4543()) {
                            connectGroundDecorModels(groundDecor.entity, planes, x, z);
                            groundDecor.entity = groundDecor.entity.createModel();
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ib", name = "a", descriptor = "(IIIIIIIILclient!th;IZJ)Z")
    public static boolean addLoc(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int width, @OriginalArg(4) int length, @OriginalArg(5) int height, @OriginalArg(6) int shape, @OriginalArg(7) int rotation, @OriginalArg(8) Entity entity, @OriginalArg(9) int animationId, @OriginalArg(10) boolean addToGlobalList, @OriginalArg(11) long key) {
        @Pc(6) boolean isUnderwater = tileHeights == underwaterTileHeights;
        @Pc(8) int underwaterValue = 0;
        @Pc(17) int currentX;
        for (@Pc(10) int tileX = x; tileX < x + width; tileX++) {
            for (currentX = z; currentX < z + length; currentX++) {
                if (tileX < 0 || currentX < 0 || tileX >= SceneGraph.width || currentX >= SceneGraph.length) {
                    return false;
                }
                @Pc(42) Tile tile = tiles[plane][tileX][currentX];
                if (tile != null && tile.sceneryLen >= 5) {
                    return false;
                }
            }
        }
        @Pc(58) Scenery scenery = new Scenery();
        scenery.key = key;
        scenery.level = plane;
        scenery.yMin = height;
        scenery.yMax = shape;
        scenery.type = rotation;
        scenery.entity = entity;
        scenery.animationId = animationId;
        scenery.xMin = x;
        scenery.zMin = z;
        scenery.xMax = x + width - 1;
        scenery.zMax = z + length - 1;
        @Pc(108) int currentZ;
        for (currentX = x; currentX < x + width; currentX++) {
            for (currentZ = z; currentZ < z + length; currentZ++) {
                @Pc(115) int interiorFlag = 0;
                if (currentX > x) {
                    interiorFlag++;
                }
                if (currentX < x + width - 1) {
                    interiorFlag += 4;
                }
                if (currentZ > z) {
                    interiorFlag += 8;
                }
                if (currentZ < z + length - 1) {
                    interiorFlag += 2;
                }
                for (@Pc(141) int currentLevel = plane; currentLevel >= 0; currentLevel--) {
                    if (tiles[currentLevel][currentX][currentZ] == null) {
                        tiles[currentLevel][currentX][currentZ] = new Tile(currentLevel, currentX, currentZ);
                    }
                }
                @Pc(174) Tile currentTile = tiles[plane][currentX][currentZ];
                currentTile.scenery[currentTile.sceneryLen] = scenery;
                currentTile.layerCollisionFlags[currentTile.sceneryLen] = interiorFlag;
                currentTile.combinedFlags |= interiorFlag;
                currentTile.sceneryLen++;
                if (isUnderwater && tileColorBuffers[currentX][currentZ] != 0) {
                    underwaterValue = tileColorBuffers[currentX][currentZ];
                }
            }
        }
        if (isUnderwater && underwaterValue != 0) {
            for (currentX = x; currentX < x + width; currentX++) {
                for (currentZ = z; currentZ < z + length; currentZ++) {
                    if (tileColorBuffers[currentX][currentZ] == 0) {
                        tileColorBuffers[currentX][currentZ] = underwaterValue;
                    }
                }
            }
        }
        if (addToGlobalList) {
            SceneGraph.scenery[sceneryLen++] = scenery;
        }
        return true;
    }

    @OriginalMember(owner = "client!dg", name = "a", descriptor = "(IIIIILclient!th;IJZ)Z")
    public static boolean addTemporary(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int shape, @OriginalArg(4) int padding, @OriginalArg(5) Entity entity, @OriginalArg(6) int yaw, @OriginalArg(7) long key, @OriginalArg(8) boolean adjustForDirection) {
        if (entity == null) {
            return true;
        }
        @Pc(7) int minX = x - padding;
        @Pc(11) int minZ = z - padding;
        @Pc(15) int maxX = x + padding;
        @Pc(19) int maxZ = z + padding;
        if (adjustForDirection) {
            if (yaw > 640 && yaw < 1408) {
                maxZ += 128;
            }
            if (yaw > 1152 && yaw < 1920) {
                maxX += 128;
            }
            if (yaw > 1664 || yaw < 384) {
                minZ -= 128;
            }
            if (yaw > 128 && yaw < 896) {
                minX -= 128;
            }
        }
        minX /= 128;
        minZ /= 128;
        maxX /= 128;
        maxZ /= 128;
        return addLoc(plane, minX, minZ, maxX + 1 - minX, maxZ - minZ + 1, x, z, shape, entity, yaw, true, key);
    }

    @OriginalMember(owner = "client!dg", name = "a", descriptor = "(IIIIIILclient!mj;)V")
    public static void removeLocWithCollision(@OriginalArg(1) int z, @OriginalArg(2) int x, @OriginalArg(3) int plane, @OriginalArg(4) int layer, @OriginalArg(5) int height, @OriginalArg(6) CollisionMap collisionMap) {
        @Pc(9) long key = 0L;
        if (layer == 0) {
            key = getWallKey(plane, x, z);
        } else if (layer == 1) {
            key = getWallDecorKey(plane, x, z);
        } else if (layer == 2) {
            key = getSceneryKey(plane, x, z);
        } else if (layer == 3) {
            key = getGroundDecorKey(plane, x, z);
        }
        @Pc(57) int shape = (int) key >> 14 & 0x1F;
        @Pc(70) int locId = (int) (key >>> 32) & Integer.MAX_VALUE;
        @Pc(74) LocType locType = LocTypeList.get(locId);
        if (locType.hasBackgroundSound()) {
            AreaSoundManager.remove(x, locType, z, plane);
        }
        @Pc(92) int rotation = (int) key >> 20 & 0x3;
        if (key == 0L) {
            return;
        }
        @Pc(100) Entity primaryEntity = null;
        @Pc(102) Entity secondaryEntity = null;
        if (layer == 0) {
            @Pc(110) Wall wall = removeWall(plane, x, z);
            if (wall != null) {
                primaryEntity = wall.primary;
                secondaryEntity = wall.secondary;
            }
            if (locType.blockwalk != 0) {
                collisionMap.unflagWall(rotation, locType.blockrange, z, shape, x);
            }
        } else if (layer == 1) {
            @Pc(233) WallDecor wallDecor = removeWallDecor(plane, x, z);
            if (wallDecor != null) {
                primaryEntity = wallDecor.primary;
                secondaryEntity = wallDecor.secondary;
            }
        } else if (layer == 2) {
            @Pc(148) Scenery scenery = removeScenery(plane, x, z);
            if (scenery != null) {
                primaryEntity = scenery.entity;
            }
            if (locType.blockwalk != 0 && locType.width + x < 104 && locType.width + z < 104 && x + locType.length < 104 && z + locType.length < 104) {
                collisionMap.unflagLoc(x, locType.width, locType.blockrange, rotation, locType.length, z);
            }
        } else if (layer == 3) {
            @Pc(211) GroundDecor groundDecor = removeGroundDecor(plane, x, z);
            if (groundDecor != null) {
                primaryEntity = groundDecor.entity;
            }
            if (locType.blockwalk == 1) {
                collisionMap.unflagGroundDecor(z, x);
            }
        }
        if (!GlRenderer.enabled || !locType.hardshadow) {
            return;
        }
        if (shape == 2) {
            if (primaryEntity instanceof Loc) {
                ((Loc) primaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, 0, rotation + 4, 0, shape, x, z, height);
            }
            if (secondaryEntity instanceof Loc) {
                ((Loc) secondaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, 0, rotation + 1 & 0x3, 0, shape, x, z, height);
            }
        } else if (shape == 5) {
            if (primaryEntity instanceof Loc) {
                ((Loc) primaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * 8, rotation, WALL_DECORATION_ROTATION_FORWARD_X[rotation] * 8, 4, x, z, height);
            }
        } else if (shape == 6) {
            if (primaryEntity instanceof Loc) {
                ((Loc) primaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, CORNER_OFFSET_X[rotation] * 8, rotation + 4, CORNER_OFFSET_Z[rotation] * 8, 4, x, z, height);
            }
        } else if (shape == 7) {
            if (primaryEntity instanceof Loc) {
                ((Loc) primaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, 0, (rotation + 2 & 0x3) + 4, 0, 4, x, z, height);
            }
        } else if (shape == 8) {
            if (primaryEntity instanceof Loc) {
                ((Loc) primaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, CORNER_OFFSET_X[rotation] * 8, rotation + 4, CORNER_OFFSET_Z[rotation] * 8, 4, x, z, height);
            }
            if (secondaryEntity instanceof Loc) {
                ((Loc) secondaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, CORNER_OFFSET_X[rotation] * 8, (rotation + 2 & 0x3) + 4, CORNER_OFFSET_Z[rotation] * 8, 4, x, z, height);
            }
        } else if (shape == 11) {
            if (primaryEntity instanceof Loc) {
                ((Loc) primaryEntity).clearShadow();
            } else {
                Loc.registerLocShadow(locType, 0, rotation + 4, 0, 10, x, z, height);
            }
        } else if (primaryEntity instanceof Loc) {
            ((Loc) primaryEntity).clearShadow();
        } else {
            Loc.registerLocShadow(locType, 0, rotation, 0, shape, x, z, height);
        }
    }

    @OriginalMember(owner = "client!fm", name = "a", descriptor = "(IIIIII)Z")
    public static boolean isAreaVisible(@OriginalArg(0) int plane, @OriginalArg(1) int minX, @OriginalArg(2) int maxX, @OriginalArg(3) int minZ, @OriginalArg(4) int maxZ, @OriginalArg(5) int height) {
        @Pc(16) int tileX;
        @Pc(20) int tileZ;
        if (minX != maxX || minZ != maxZ) {
            for (tileX = minX; tileX <= maxX; tileX++) {
                for (tileZ = minZ; tileZ <= maxZ; tileZ++) {
                    if (lightmapBuffers[plane][tileX][tileZ] == -renderDistance) {
                        return false;
                    }
                }
            }
            tileX = (minX << 7) + 1;
            tileZ = (minZ << 7) + 2;
            @Pc(156) int adjustedHeight = tileHeights[plane][minX][minZ] + height;
            if (!isInOccluderBounds(tileX, adjustedHeight, tileZ)) {
                return false;
            }
            @Pc(169) int maxXFine = (maxX << 7) - 1;
            if (!isInOccluderBounds(maxXFine, adjustedHeight, tileZ)) {
                return false;
            }
            @Pc(182) int maxZFine = (maxZ << 7) - 1;
            if (!isInOccluderBounds(tileX, adjustedHeight, maxZFine)) {
                return false;
            } else if (isInOccluderBounds(maxXFine, adjustedHeight, maxZFine)) {
                return true;
            } else {
                return false;
            }
        } else if (isTileVisible(plane, minX, minZ)) {
            tileX = minX << 7;
            tileZ = minZ << 7;
            return isInOccluderBounds(tileX + 1, tileHeights[plane][minX][minZ] + height, tileZ + 1) && isInOccluderBounds(tileX + 128 - 1, tileHeights[plane][minX + 1][minZ] + height, tileZ + 1) && isInOccluderBounds(tileX + 128 - 1, tileHeights[plane][minX + 1][minZ + 1] + height, tileZ + 128 - 1) && isInOccluderBounds(tileX + 1, tileHeights[plane][minX][minZ + 1] + height, tileZ + 128 - 1);
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIIIJ)V")
    public static void addWallDecoration(@OriginalArg(0) int level, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int height, @OriginalArg(4) Entity primary, @OriginalArg(5) Entity secondary, @OriginalArg(6) int type, @OriginalArg(7) int rotation, @OriginalArg(8) int xOffset, @OriginalArg(9) int zOffset, @OriginalArg(10) long key) {
        if (primary == null) {
            return;
        }
        @Pc(6) WallDecor wallDecor = new WallDecor();
        wallDecor.key = key;
        wallDecor.xFine = x * 128 + 64;
        wallDecor.zFine = z * 128 + 64;
        wallDecor.yOffset = height;
        wallDecor.primary = primary;
        wallDecor.secondary = secondary;
        wallDecor.type = type;
        wallDecor.yFine = rotation;
        wallDecor.xOffset = xOffset;
        wallDecor.zOffset = zOffset;
        for (@Pc(46) int currentPlane = level; currentPlane >= 0; currentPlane--) {
            if (tiles[currentPlane][x][z] == null) {
                tiles[currentPlane][x][z] = new Tile(currentPlane, x, z);
            }
        }
        tiles[level][x][z].wallDecor = wallDecor;
    }

    @OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(Lclient!bj;Z)V")
    public static void renderScene(@OriginalArg(0) Tile startTile, @OriginalArg(1) boolean checkOcclusion) {
        drawTileQueue.push(startTile);
        while (true) {
            @Pc(8) Tile tile;
            @Pc(18) int tileX;
            @Pc(21) int tileZ;
            @Pc(24) int plane;
            @Pc(27) int occlusionLevel;
            @Pc(31) Tile[][] tiles;
            @Pc(65) int frontWallTypes;
            @Pc(115) int farthestIndex;
            @Pc(894) int x;
            @Pc(899) int y;
            @Pc(904) int z;
            @Pc(153) Tile checkTile;
            @Pc(1332) int tempValue;
            do {
                do {
                    do {
                        do {
                            do {
                                do {
                                    while (true) {
                                        @Pc(44) int colorData;
                                        @Pc(48) int viewDirection;
                                        @Pc(907) int wallRotation;
                                        @Pc(916) int adjustedX;
                                        @Pc(363) Wall var22;
                                        @Pc(469) boolean shouldDrawTile;
                                        @Pc(425) Scenery scenery;
                                        @Pc(1179) Tile adjacentTile;
                                        while (true) {
                                            do {
                                                tile = (Tile) drawTileQueue.removeHead();
                                                if (tile == null) {
                                                    return;
                                                }
                                            } while (!tile.bridgeAbove);
                                            tileX = tile.localZ;
                                            tileZ = tile.plane;
                                            plane = tile.sceneX;
                                            occlusionLevel = tile.localX;
                                            tiles = SceneGraph.tiles[plane];
                                            @Pc(33) float local33 = 0.0F;
                                            if (GlRenderer.enabled) {
                                                if (underwaterTileHeights == tileHeights) {
                                                    colorData = tileColorBuffers[tileX][tileZ];
                                                    viewDirection = colorData & 0xFFFFFF;
                                                    if (viewDirection != lastAnimationFrame) {
                                                        lastAnimationFrame = viewDirection;
                                                        WaterMaterialRenderer.method619(viewDirection);
                                                        FogManager.setFogColor(WaterMaterialRenderer.method2422());
                                                    }
                                                    frontWallTypes = colorData >>> 24 << 3;
                                                    if (frontWallTypes != currentAnimationFrame) {
                                                        currentAnimationFrame = frontWallTypes;
                                                        MaterialManager.method2761(frontWallTypes);
                                                    }
                                                    farthestIndex = surfaceTileHeights[0][tileX][tileZ] + surfaceTileHeights[0][tileX + 1][tileZ] + surfaceTileHeights[0][tileX][tileZ + 1] + surfaceTileHeights[0][tileX + 1][tileZ + 1] >> 2;
                                                    MaterialManager.setMaterial(-farthestIndex, 3);
                                                    local33 = 201.5F;
                                                    GlRenderer.method4159(local33);
                                                } else {
                                                    local33 = 201.5F - (float) (occlusionLevel + 1) * 50.0F;
                                                    GlRenderer.method4159(local33);
                                                }
                                            }
                                            if (!tile.hasObjects) {
                                                break;
                                            }
                                            if (checkOcclusion) {
                                                if (plane > 0) {
                                                    checkTile = SceneGraph.tiles[plane - 1][tileX][tileZ];
                                                    if (checkTile != null && checkTile.bridgeAbove) {
                                                        continue;
                                                    }
                                                }
                                                if (tileX <= eyeTileX && tileX > LightingManager.anInt987) {
                                                    checkTile = tiles[tileX - 1][tileZ];
                                                    if (checkTile != null && checkTile.bridgeAbove && (checkTile.hasObjects || (tile.combinedFlags & 0x1) == 0)) {
                                                        continue;
                                                    }
                                                }
                                                if (tileX >= eyeTileX && tileX < LightingManager.anInt15 - 1) {
                                                    checkTile = tiles[tileX + 1][tileZ];
                                                    if (checkTile != null && checkTile.bridgeAbove && (checkTile.hasObjects || (tile.combinedFlags & 0x4) == 0)) {
                                                        continue;
                                                    }
                                                }
                                                if (tileZ <= eyeTileZ && tileZ > LightingManager.anInt4698) {
                                                    checkTile = tiles[tileX][tileZ - 1];
                                                    if (checkTile != null && checkTile.bridgeAbove && (checkTile.hasObjects || (tile.combinedFlags & 0x8) == 0)) {
                                                        continue;
                                                    }
                                                }
                                                if (tileZ >= eyeTileZ && tileZ < LightingManager.anInt4866 - 1) {
                                                    checkTile = tiles[tileX][tileZ + 1];
                                                    if (checkTile != null && checkTile.bridgeAbove && (checkTile.hasObjects || (tile.combinedFlags & 0x2) == 0)) {
                                                        continue;
                                                    }
                                                }
                                            } else {
                                                checkOcclusion = true;
                                            }
                                            tile.hasObjects = false;
                                            if (tile.bridgeTile != null) {
                                                checkTile = tile.bridgeTile;
                                                if (GlRenderer.enabled) {
                                                    GlRenderer.method4159(201.5F - (float) (checkTile.localX + 1) * 50.0F);
                                                }
                                                if (checkTile.plainTile == null) {
                                                    if (checkTile.shapedTile != null) {
                                                        if (isTileVisible(0, tileX, tileZ)) {
                                                            drawTileOverlay(checkTile.shapedTile, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, true);
                                                        } else {
                                                            drawTileOverlay(checkTile.shapedTile, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, false);
                                                        }
                                                    }
                                                } else if (isTileVisible(0, tileX, tileZ)) {
                                                    renderPlainTile(checkTile.plainTile, 0, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, true);
                                                } else {
                                                    renderPlainTile(checkTile.plainTile, 0, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, false);
                                                }
                                                var22 = checkTile.wall;
                                                if (var22 != null) {
                                                    if (GlRenderer.enabled) {
                                                        if ((var22.typeA & tile.backWallFlags) == 0) {
                                                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                        } else {
                                                            LightingManager.method2388(var22.typeA, eyeX, eyeY, eyeZ, occlusionLevel, tileX, tileZ);
                                                        }
                                                    }
                                                    var22.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, var22.xFine - eyeX, var22.yFine - eyeY, var22.zFine - eyeZ, var22.key, plane, null);
                                                }
                                                for (frontWallTypes = 0; frontWallTypes < checkTile.sceneryLen; frontWallTypes++) {
                                                    scenery = checkTile.scenery[frontWallTypes];
                                                    if (scenery != null) {
                                                        if (GlRenderer.enabled) {
                                                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                        }
                                                        scenery.entity.render(scenery.animationId, sinYaw, cosYaw, sinPitch, cosPitch, scenery.yMin - eyeX, scenery.type - eyeY, scenery.yMax - eyeZ, scenery.key, plane, null);
                                                    }
                                                }
                                                if (GlRenderer.enabled) {
                                                    GlRenderer.method4159(local33);
                                                }
                                            }
                                            shouldDrawTile = false;
                                            if (tile.plainTile == null) {
                                                if (tile.shapedTile != null) {
                                                    if (isTileVisible(occlusionLevel, tileX, tileZ)) {
                                                        drawTileOverlay(tile.shapedTile, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, true);
                                                    } else {
                                                        shouldDrawTile = true;
                                                        drawTileOverlay(tile.shapedTile, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, false);
                                                    }
                                                }
                                            } else if (isTileVisible(occlusionLevel, tileX, tileZ)) {
                                                renderPlainTile(tile.plainTile, occlusionLevel, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, true);
                                            } else {
                                                shouldDrawTile = true;
                                                if (tile.plainTile.seHeight != 12345678 || MiniMenu.walkTargetActive && plane <= MiniMenu.targetPlane) {
                                                    renderPlainTile(tile.plainTile, occlusionLevel, sinYaw, cosYaw, sinPitch, cosPitch, tileX, tileZ, false);
                                                }
                                            }
                                            if (shouldDrawTile) {
                                                @Pc(549) GroundDecor groundDecor = tile.groundDecor;
                                                if (groundDecor != null && (groundDecor.key & 0x80000000L) != 0L) {
                                                    if (GlRenderer.enabled && groundDecor.interactive) {
                                                        GlRenderer.method4159(local33 + 50.0F - 1.5F);
                                                    }
                                                    if (GlRenderer.enabled) {
                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                    }
                                                    groundDecor.entity.render(0, sinYaw, cosYaw, sinPitch, cosPitch, groundDecor.xFine - eyeX, groundDecor.yFine - eyeY, groundDecor.zFine - eyeZ, groundDecor.key, plane, null);
                                                    if (GlRenderer.enabled && groundDecor.interactive) {
                                                        GlRenderer.method4159(local33);
                                                    }
                                                }
                                            }
                                            viewDirection = 0;
                                            frontWallTypes = 0;
                                            @Pc(616) Wall wall = tile.wall;
                                            @Pc(619) WallDecor wallDecor = tile.wallDecor;
                                            if (wall != null || wallDecor != null) {
                                                if (eyeTileX == tileX) {
                                                    viewDirection++;
                                                } else if (eyeTileX < tileX) {
                                                    viewDirection += 2;
                                                }
                                                if (eyeTileZ == tileZ) {
                                                    viewDirection += 3;
                                                } else if (eyeTileZ > tileZ) {
                                                    viewDirection += 6;
                                                }
                                                frontWallTypes = FRONT_WALL_TYPES[viewDirection];
                                                tile.backWallFlags = BACK_WALL_TYPES[viewDirection];
                                            }
                                            if (wall != null) {
                                                if ((wall.typeA & DIRECTION_ALLOW_WALL_CORNER_TYPE[viewDirection]) == 0) {
                                                    tile.locCheckFlags = 0;
                                                } else if (wall.typeA == 16) {
                                                    tile.locCheckFlags = 3;
                                                    tile.locBlockFlags = WALL_CORNER_TYPE_16_BLOCK_LOC_SPANS[viewDirection];
                                                    tile.inverseLocBlockFlags = 3 - tile.locBlockFlags;
                                                } else if (wall.typeA == 32) {
                                                    tile.locCheckFlags = 6;
                                                    tile.locBlockFlags = WALL_CORNER_TYPE_32_BLOCK_LOC_SPANS[viewDirection];
                                                    tile.inverseLocBlockFlags = 6 - tile.locBlockFlags;
                                                } else if (wall.typeA == 64) {
                                                    tile.locCheckFlags = 12;
                                                    tile.locBlockFlags = WALL_CORNER_TYPE_64_BLOCK_LOC_SPANS[viewDirection];
                                                    tile.inverseLocBlockFlags = 12 - tile.locBlockFlags;
                                                } else {
                                                    tile.locCheckFlags = 9;
                                                    tile.locBlockFlags = WALL_CORNER_TYPE_128_BLOCK_LOC_SPANS[viewDirection];
                                                    tile.inverseLocBlockFlags = 9 - tile.locBlockFlags;
                                                }
                                                if ((wall.typeA & frontWallTypes) != 0 && !wallVisible(occlusionLevel, tileX, tileZ, wall.typeA)) {
                                                    if (GlRenderer.enabled) {
                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                    }
                                                    wall.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, wall.xFine - eyeX, wall.yFine - eyeY, wall.zFine - eyeZ, wall.key, plane, null);
                                                }
                                                if ((wall.typeB & frontWallTypes) != 0 && !wallVisible(occlusionLevel, tileX, tileZ, wall.typeB)) {
                                                    if (GlRenderer.enabled) {
                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                    }
                                                    wall.secondary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, wall.xFine - eyeX, wall.yFine - eyeY, wall.zFine - eyeZ, wall.key, plane, null);
                                                }
                                            }
                                            if (wallDecor != null && !visible(occlusionLevel, tileX, tileZ, wallDecor.primary.getMinY())) {
                                                if (GlRenderer.enabled) {
                                                    GlRenderer.method4159(local33 - 0.5F);
                                                }
                                                if ((wallDecor.type & frontWallTypes) != 0) {
                                                    if (GlRenderer.enabled) {
                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                    }
                                                    wallDecor.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, wallDecor.xFine + wallDecor.xOffset - eyeX, wallDecor.yOffset - eyeY, wallDecor.zFine + wallDecor.zOffset - eyeZ, wallDecor.key, plane, null);
                                                } else if (wallDecor.type == 256) {
                                                    x = wallDecor.xFine - eyeX;
                                                    y = wallDecor.yOffset - eyeY;
                                                    z = wallDecor.zFine - eyeZ;
                                                    wallRotation = wallDecor.yFine;
                                                    if (wallRotation == 1 || wallRotation == 2) {
                                                        adjustedX = -x;
                                                    } else {
                                                        adjustedX = x;
                                                    }
                                                    @Pc(928) int adjustedZ;
                                                    if (wallRotation == 2 || wallRotation == 3) {
                                                        adjustedZ = -z;
                                                    } else {
                                                        adjustedZ = z;
                                                    }
                                                    if (adjustedZ < adjustedX) {
                                                        if (GlRenderer.enabled) {
                                                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                        }
                                                        wallDecor.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, x + wallDecor.xOffset, y, z + wallDecor.zOffset, wallDecor.key, plane, null);
                                                    } else if (wallDecor.secondary != null) {
                                                        if (GlRenderer.enabled) {
                                                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                        }
                                                        wallDecor.secondary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, x, y, z, wallDecor.key, plane, null);
                                                    }
                                                }
                                                if (GlRenderer.enabled) {
                                                    GlRenderer.method4159(local33);
                                                }
                                            }
                                            if (shouldDrawTile) {
                                                @Pc(1001) GroundDecor groundDecor = tile.groundDecor;
                                                if (groundDecor != null && (groundDecor.key & 0x80000000L) == 0L) {
                                                    if (GlRenderer.enabled && groundDecor.interactive) {
                                                        GlRenderer.method4159(local33 + 50.0F - 1.5F);
                                                    }
                                                    if (GlRenderer.enabled) {
                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                    }
                                                    groundDecor.entity.render(0, sinYaw, cosYaw, sinPitch, cosPitch, groundDecor.xFine - eyeX, groundDecor.yFine - eyeY, groundDecor.zFine - eyeZ, groundDecor.key, plane, null);
                                                    if (GlRenderer.enabled && groundDecor.interactive) {
                                                        GlRenderer.method4159(local33);
                                                    }
                                                }
                                                @Pc(1064) ObjStackEntity objs = tile.objStack;
                                                if (objs != null && objs.offset == 0) {
                                                    if (GlRenderer.enabled) {
                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                    }
                                                    if (objs.secondary != null) {
                                                        objs.secondary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, objs.xFine - eyeX, objs.anInt3057 - eyeY, objs.zFine - eyeZ, objs.key, plane, null);
                                                    }
                                                    if (objs.tertiary != null) {
                                                        objs.tertiary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, objs.xFine - eyeX, objs.anInt3057 - eyeY, objs.zFine - eyeZ, objs.key, plane, null);
                                                    }
                                                    if (objs.primary != null) {
                                                        objs.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, objs.xFine - eyeX, objs.anInt3057 - eyeY, objs.zFine - eyeZ, objs.key, plane, null);
                                                    }
                                                }
                                            }
                                            x = tile.combinedFlags;
                                            if (x != 0) {
                                                if (tileX < eyeTileX && (x & 0x4) != 0) {
                                                    adjacentTile = tiles[tileX + 1][tileZ];
                                                    if (adjacentTile != null && adjacentTile.bridgeAbove) {
                                                        drawTileQueue.push(adjacentTile);
                                                    }
                                                }
                                                if (tileZ < eyeTileZ && (x & 0x2) != 0) {
                                                    adjacentTile = tiles[tileX][tileZ + 1];
                                                    if (adjacentTile != null && adjacentTile.bridgeAbove) {
                                                        drawTileQueue.push(adjacentTile);
                                                    }
                                                }
                                                if (tileX > eyeTileX && (x & 0x1) != 0) {
                                                    adjacentTile = tiles[tileX - 1][tileZ];
                                                    if (adjacentTile != null && adjacentTile.bridgeAbove) {
                                                        drawTileQueue.push(adjacentTile);
                                                    }
                                                }
                                                if (tileZ > eyeTileZ && (x & 0x8) != 0) {
                                                    adjacentTile = tiles[tileX][tileZ - 1];
                                                    if (adjacentTile != null && adjacentTile.bridgeAbove) {
                                                        drawTileQueue.push(adjacentTile);
                                                    }
                                                }
                                            }
                                            break;
                                        }
                                        if (tile.locCheckFlags != 0) {
                                            shouldDrawTile = true;
                                            for (viewDirection = 0; viewDirection < tile.sceneryLen; viewDirection++) {
                                                if (tile.scenery[viewDirection].config != renderDistance && (tile.layerCollisionFlags[viewDirection] & tile.locCheckFlags) == tile.locBlockFlags) {
                                                    shouldDrawTile = false;
                                                    break;
                                                }
                                            }
                                            if (shouldDrawTile) {
                                                var22 = tile.wall;
                                                if (!wallVisible(occlusionLevel, tileX, tileZ, var22.typeA)) {
                                                    if (GlRenderer.enabled) {
                                                        label882: {
                                                            if ((var22.key & 0xFC000L) == 16384L) {
                                                                frontWallTypes = var22.xFine - eyeX;
                                                                farthestIndex = var22.zFine - eyeZ;
                                                                tempValue = (int) (var22.key >> 20 & 0x3L);
                                                                if (tempValue == 0) {
                                                                    frontWallTypes -= 64;
                                                                    farthestIndex += 64;
                                                                    if (farthestIndex < frontWallTypes && tileX > 0 && tileZ < length - 1) {
                                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX - 1, tileZ + 1);
                                                                        break label882;
                                                                    }
                                                                } else if (tempValue == 1) {
                                                                    frontWallTypes += 64;
                                                                    farthestIndex += 64;
                                                                    if (farthestIndex < -frontWallTypes && tileX < width - 1 && tileZ < length - 1) {
                                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX + 1, tileZ + 1);
                                                                        break label882;
                                                                    }
                                                                } else if (tempValue == 2) {
                                                                    frontWallTypes += 64;
                                                                    farthestIndex -= 64;
                                                                    if (farthestIndex > frontWallTypes && tileX < width - 1 && tileZ > 0) {
                                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX + 1, tileZ - 1);
                                                                        break label882;
                                                                    }
                                                                } else if (tempValue == 3) {
                                                                    frontWallTypes -= 64;
                                                                    farthestIndex -= 64;
                                                                    if (farthestIndex > -frontWallTypes && tileX > 0 && tileZ > 0) {
                                                                        LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX - 1, tileZ - 1);
                                                                        break label882;
                                                                    }
                                                                }
                                                            }
                                                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                        }
                                                    }
                                                    var22.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, var22.xFine - eyeX, var22.yFine - eyeY, var22.zFine - eyeZ, var22.key, plane, null);
                                                }
                                                tile.locCheckFlags = 0;
                                            }
                                        }
                                        if (!tile.containsLocs) {
                                            break;
                                        }
                                        try {
                                            colorData = tile.sceneryLen;
                                            tile.containsLocs = false;
                                            viewDirection = 0;
                                            iterate_locs: for (frontWallTypes = 0; frontWallTypes < colorData; frontWallTypes++) {
                                                scenery = tile.scenery[frontWallTypes];
                                                if (scenery.config != renderDistance) {
                                                    for (tempValue = scenery.xMin; tempValue <= scenery.xMax; tempValue++) {
                                                        for (x = scenery.zMin; x <= scenery.zMax; x++) {
                                                            adjacentTile = tiles[tempValue][x];
                                                            if (adjacentTile.hasObjects) {
                                                                tile.containsLocs = true;
                                                                continue iterate_locs;
                                                            }
                                                            if (adjacentTile.locCheckFlags != 0) {
                                                                z = 0;
                                                                if (tempValue > scenery.xMin) {
                                                                    z++;
                                                                }
                                                                if (tempValue < scenery.xMax) {
                                                                    z += 4;
                                                                }
                                                                if (x > scenery.zMin) {
                                                                    z += 8;
                                                                }
                                                                if (x < scenery.zMax) {
                                                                    z += 2;
                                                                }
                                                                if ((z & adjacentTile.locCheckFlags) == tile.inverseLocBlockFlags) {
                                                                    tile.containsLocs = true;
                                                                    continue iterate_locs;
                                                                }
                                                            }
                                                        }
                                                    }
                                                    pickableEntities[viewDirection++] = scenery;
                                                    tempValue = eyeTileX - scenery.xMin;
                                                    x = scenery.xMax - eyeTileX;
                                                    if (x > tempValue) {
                                                        tempValue = x;
                                                    }
                                                    y = eyeTileZ - scenery.zMin;
                                                    z = scenery.zMax - eyeTileZ;
                                                    if (z > y) {
                                                        scenery.rotation = tempValue + z;
                                                    } else {
                                                        scenery.rotation = tempValue + y;
                                                    }
                                                }
                                            }
                                            while (viewDirection > 0) {
                                                frontWallTypes = -50;
                                                farthestIndex = -1;
                                                for (tempValue = 0; tempValue < viewDirection; tempValue++) {
                                                    @Pc(1628) Scenery currentScenery = pickableEntities[tempValue];
                                                    if (currentScenery.config != renderDistance) {
                                                        if (currentScenery.rotation > frontWallTypes) {
                                                            frontWallTypes = currentScenery.rotation;
                                                            farthestIndex = tempValue;
                                                        } else if (currentScenery.rotation == frontWallTypes) {
                                                            y = currentScenery.yMin - eyeX;
                                                            z = currentScenery.yMax - eyeZ;
                                                            wallRotation = pickableEntities[farthestIndex].yMin - eyeX;
                                                            adjustedX = pickableEntities[farthestIndex].yMax - eyeZ;
                                                            if (y * y + z * z > wallRotation * wallRotation + adjustedX * adjustedX) {
                                                                farthestIndex = tempValue;
                                                            }
                                                        }
                                                    }
                                                }
                                                if (farthestIndex == -1) {
                                                    break;
                                                }
                                                @Pc(1697) Scenery selectedScenery = pickableEntities[farthestIndex];
                                                selectedScenery.config = renderDistance;
                                                if (!isAreaVisible(occlusionLevel, selectedScenery.xMin, selectedScenery.xMax, selectedScenery.zMin, selectedScenery.zMax, selectedScenery.entity.getMinY())) {
                                                    if (GlRenderer.enabled) {
                                                        if ((selectedScenery.key & 0xFC000L) == 147456L) {
                                                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                                                            x = selectedScenery.yMin - eyeX;
                                                            y = selectedScenery.yMax - eyeZ;
                                                            z = (int) (selectedScenery.key >> 20 & 0x3L);
                                                            if (z == 1 || z == 3) {
                                                                if (y > -x) {
                                                                    LightingManager.method2397(plane, tileX, tileZ - 1, tileX - 1, tileZ);
                                                                } else {
                                                                    LightingManager.method2397(plane, tileX, tileZ + 1, tileX + 1, tileZ);
                                                                }
                                                            } else if (y > x) {
                                                                LightingManager.method2397(plane, tileX, tileZ - 1, tileX + 1, tileZ);
                                                            } else {
                                                                LightingManager.method2397(plane, tileX, tileZ + 1, tileX - 1, tileZ);
                                                            }
                                                        } else {
                                                            LightingManager.method2391(eyeX, eyeY, eyeZ, plane, selectedScenery.xMin, selectedScenery.zMin, selectedScenery.xMax, selectedScenery.zMax);
                                                        }
                                                    }
                                                    selectedScenery.entity.render(selectedScenery.animationId, sinYaw, cosYaw, sinPitch, cosPitch, selectedScenery.yMin - eyeX, selectedScenery.type - eyeY, selectedScenery.yMax - eyeZ, selectedScenery.key, plane, null);
                                                }
                                                for (x = selectedScenery.xMin; x <= selectedScenery.xMax; x++) {
                                                    for (y = selectedScenery.zMin; y <= selectedScenery.zMax; y++) {
                                                        @Pc(1863) Tile spanTile = tiles[x][y];
                                                        if (spanTile.locCheckFlags != 0) {
                                                            drawTileQueue.push(spanTile);
                                                        } else if ((x != tileX || y != tileZ) && spanTile.bridgeAbove) {
                                                            drawTileQueue.push(spanTile);
                                                        }
                                                    }
                                                }
                                            }
                                            if (!tile.containsLocs) {
                                                break;
                                            }
                                        } catch (@Pc(1895) Exception local1895) {
                                            tile.containsLocs = false;
                                            break;
                                        }
                                    }
                                } while (!tile.bridgeAbove);
                            } while (tile.locCheckFlags != 0);
                            if (tileX > eyeTileX || tileX <= LightingManager.anInt987) {
                                break;
                            }
                            checkTile = tiles[tileX - 1][tileZ];
                        } while (checkTile != null && checkTile.bridgeAbove);
                        if (tileX < eyeTileX || tileX >= LightingManager.anInt15 - 1) {
                            break;
                        }
                        checkTile = tiles[tileX + 1][tileZ];
                    } while (checkTile != null && checkTile.bridgeAbove);
                    if (tileZ > eyeTileZ || tileZ <= LightingManager.anInt4698) {
                        break;
                    }
                    checkTile = tiles[tileX][tileZ - 1];
                } while (checkTile != null && checkTile.bridgeAbove);
                if (tileZ < eyeTileZ || tileZ >= LightingManager.anInt4866 - 1) {
                    break;
                }
                checkTile = tiles[tileX][tileZ + 1];
            } while (checkTile != null && checkTile.bridgeAbove);
            tile.bridgeAbove = false;
            fogDistance--;
            @Pc(1999) ObjStackEntity objStack = tile.objStack;
            if (objStack != null && objStack.offset != 0) {
                if (GlRenderer.enabled) {
                    LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                }
                if (objStack.secondary != null) {
                    objStack.secondary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, objStack.xFine - eyeX, objStack.anInt3057 - eyeY - objStack.offset, objStack.zFine - eyeZ, objStack.key, plane, null);
                }
                if (objStack.tertiary != null) {
                    objStack.tertiary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, objStack.xFine - eyeX, objStack.anInt3057 - eyeY - objStack.offset, objStack.zFine - eyeZ, objStack.key, plane, null);
                }
                if (objStack.primary != null) {
                    objStack.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, objStack.xFine - eyeX, objStack.anInt3057 - eyeY - objStack.offset, objStack.zFine - eyeZ, objStack.key, plane, null);
                }
            }
            if (tile.backWallFlags != 0) {
                @Pc(2109) WallDecor wallDecor = tile.wallDecor;
                if (wallDecor != null && !visible(occlusionLevel, tileX, tileZ, wallDecor.primary.getMinY())) {
                    if ((wallDecor.type & tile.backWallFlags) != 0) {
                        if (GlRenderer.enabled) {
                            LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                        }
                        wallDecor.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, wallDecor.xFine + wallDecor.xOffset - eyeX, wallDecor.yOffset - eyeY, wallDecor.zFine + wallDecor.zOffset - eyeZ, wallDecor.key, plane, null);
                    } else if (wallDecor.type == 256) {
                        frontWallTypes = wallDecor.xFine - eyeX;
                        farthestIndex = wallDecor.yOffset - eyeY;
                        tempValue = wallDecor.zFine - eyeZ;
                        x = wallDecor.yFine;
                        if (x == 1 || x == 2) {
                            y = -frontWallTypes;
                        } else {
                            y = frontWallTypes;
                        }
                        if (x == 2 || x == 3) {
                            z = -tempValue;
                        } else {
                            z = tempValue;
                        }
                        if (z >= y) {
                            if (GlRenderer.enabled) {
                                LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                            }
                            wallDecor.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, frontWallTypes + wallDecor.xOffset, farthestIndex, tempValue + wallDecor.zOffset, wallDecor.key, plane, null);
                        } else if (wallDecor.secondary != null) {
                            if (GlRenderer.enabled) {
                                LightingManager.method2393(eyeX, eyeY, eyeZ, plane, tileX, tileZ);
                            }
                            wallDecor.secondary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, frontWallTypes, farthestIndex, tempValue, wallDecor.key, plane, null);
                        }
                    }
                }
                @Pc(2275) Wall wall = tile.wall;
                if (wall != null) {
                    if ((wall.typeB & tile.backWallFlags) != 0 && !wallVisible(occlusionLevel, tileX, tileZ, wall.typeB)) {
                        if (GlRenderer.enabled) {
                            LightingManager.method2388(wall.typeB, eyeX, eyeY, eyeZ, occlusionLevel, tileX, tileZ);
                        }
                        wall.secondary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, wall.xFine - eyeX, wall.yFine - eyeY, wall.zFine - eyeZ, wall.key, plane, null);
                    }
                    if ((wall.typeA & tile.backWallFlags) != 0 && !wallVisible(occlusionLevel, tileX, tileZ, wall.typeA)) {
                        if (GlRenderer.enabled) {
                            LightingManager.method2388(wall.typeA, eyeX, eyeY, eyeZ, occlusionLevel, tileX, tileZ);
                        }
                        wall.primary.render(0, sinYaw, cosYaw, sinPitch, cosPitch, wall.xFine - eyeX, wall.yFine - eyeY, wall.zFine - eyeZ, wall.key, plane, null);
                    }
                }
            }
            @Pc(2388) Tile neighborTile;
            if (plane < planes - 1) {
                neighborTile = SceneGraph.tiles[plane + 1][tileX][tileZ];
                if (neighborTile != null && neighborTile.bridgeAbove) {
                    drawTileQueue.push(neighborTile);
                }
            }
            if (tileX < eyeTileX) {
                neighborTile = tiles[tileX + 1][tileZ];
                if (neighborTile != null && neighborTile.bridgeAbove) {
                    drawTileQueue.push(neighborTile);
                }
            }
            if (tileZ < eyeTileZ) {
                neighborTile = tiles[tileX][tileZ + 1];
                if (neighborTile != null && neighborTile.bridgeAbove) {
                    drawTileQueue.push(neighborTile);
                }
            }
            if (tileX > eyeTileX) {
                neighborTile = tiles[tileX - 1][tileZ];
                if (neighborTile != null && neighborTile.bridgeAbove) {
                    drawTileQueue.push(neighborTile);
                }
            }
            if (tileZ > eyeTileZ) {
                neighborTile = tiles[tileX][tileZ - 1];
                if (neighborTile != null && neighborTile.bridgeAbove) {
                    drawTileQueue.push(neighborTile);
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(IIII)Z")
    public static boolean visible(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int height) {
        if (isTileVisible(plane, x, z)) {
            @Pc(10) int xFine = x << 7;
            @Pc(14) int zFine = z << 7;
            return isInOccluderBounds(xFine + 1, tileHeights[plane][x][z] + height, zFine + 1) && isInOccluderBounds(xFine + 128 - 1, tileHeights[plane][x + 1][z] + height, zFine + 1) && isInOccluderBounds(xFine + 128 - 1, tileHeights[plane][x + 1][z + 1] + height, zFine + 128 - 1) && isInOccluderBounds(xFine + 1, tileHeights[plane][x][z + 1] + height, zFine + 128 - 1);
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!vd", name = "b", descriptor = "(IIII)Z")
    public static boolean wallVisible(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int wallType) {
        if (!isTileVisible(plane, x, z)) {
            return false;
        }
        @Pc(10) int xFine = x << 7;
        @Pc(14) int zFine = z << 7;
        @Pc(24) int baseHeight = tileHeights[plane][x][z] - 1;
        @Pc(28) int upperHeight = baseHeight - 120;
        @Pc(32) int lowerHeight = baseHeight - 230;
        @Pc(36) int cornerHeight = baseHeight - 238;
        if (wallType < 16) {
            if (wallType == 1) {
                if (xFine > eyeX) {
                    if (!isInOccluderBounds(xFine, baseHeight, zFine)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine, baseHeight, zFine + 128)) {
                        return false;
                    }
                }
                if (plane > 0) {
                    if (!isInOccluderBounds(xFine, upperHeight, zFine)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine, upperHeight, zFine + 128)) {
                        return false;
                    }
                }
                if (!isInOccluderBounds(xFine, lowerHeight, zFine)) {
                    return false;
                }
                if (!isInOccluderBounds(xFine, lowerHeight, zFine + 128)) {
                    return false;
                }
                return true;
            }
            if (wallType == 2) {
                if (zFine < eyeZ) {
                    if (!isInOccluderBounds(xFine, baseHeight, zFine + 128)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine + 128, baseHeight, zFine + 128)) {
                        return false;
                    }
                }
                if (plane > 0) {
                    if (!isInOccluderBounds(xFine, upperHeight, zFine + 128)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine + 128, upperHeight, zFine + 128)) {
                        return false;
                    }
                }
                if (!isInOccluderBounds(xFine, lowerHeight, zFine + 128)) {
                    return false;
                }
                if (!isInOccluderBounds(xFine + 128, lowerHeight, zFine + 128)) {
                    return false;
                }
                return true;
            }
            if (wallType == 4) {
                if (xFine < eyeX) {
                    if (!isInOccluderBounds(xFine + 128, baseHeight, zFine)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine + 128, baseHeight, zFine + 128)) {
                        return false;
                    }
                }
                if (plane > 0) {
                    if (!isInOccluderBounds(xFine + 128, upperHeight, zFine)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine + 128, upperHeight, zFine + 128)) {
                        return false;
                    }
                }
                if (!isInOccluderBounds(xFine + 128, lowerHeight, zFine)) {
                    return false;
                }
                if (!isInOccluderBounds(xFine + 128, lowerHeight, zFine + 128)) {
                    return false;
                }
                return true;
            }
            if (wallType == 8) {
                if (zFine > eyeZ) {
                    if (!isInOccluderBounds(xFine, baseHeight, zFine)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine + 128, baseHeight, zFine)) {
                        return false;
                    }
                }
                if (plane > 0) {
                    if (!isInOccluderBounds(xFine, upperHeight, zFine)) {
                        return false;
                    }
                    if (!isInOccluderBounds(xFine + 128, upperHeight, zFine)) {
                        return false;
                    }
                }
                if (!isInOccluderBounds(xFine, lowerHeight, zFine)) {
                    return false;
                }
                if (!isInOccluderBounds(xFine + 128, lowerHeight, zFine)) {
                    return false;
                }
                return true;
            }
        }
        if (!isInOccluderBounds(xFine + 64, cornerHeight, zFine + 64)) {
            return false;
        } else if (wallType == 16) {
            return isInOccluderBounds(xFine, lowerHeight, zFine + 128);
        } else if (wallType == 32) {
            return isInOccluderBounds(xFine + 128, lowerHeight, zFine + 128);
        } else if (wallType == 64) {
            return isInOccluderBounds(xFine + 128, lowerHeight, zFine);
        } else if (wallType == 128) {
            return isInOccluderBounds(xFine, lowerHeight, zFine);
        } else {
            return true;
        }
    }

    @OriginalMember(owner = "client!ch", name = "c", descriptor = "(I)V")
    public static void updatePlayerPlane() {
        if (!allLevelsAreVisible() && centralPlane != Player.currentLevel) {
            WorldLoader.initializeMapRegion(Player.currentLevel, centralZoneZ, centralZoneX, PlayerList.self.movementQueueZ[0], false, PlayerList.self.movementQueueX[0]);
        } else if (Player.currentLevel != LightingManager.anInt2875 && MiniMap.drawMap(Player.currentLevel)) {
            LightingManager.anInt2875 = Player.currentLevel;
            ClientScriptRunner.method2218();
        }
    }

    @OriginalMember(owner = "runetek4.client!vh", name = "a", descriptor = "(Lclient!th;III)V")
    public static void connectGroundDecorModels(@OriginalArg(0) Entity entity, @OriginalArg(1) int plane, @OriginalArg(2) int x, @OriginalArg(3) int z) {
        @Pc(12) Tile adjacentTile;
        if (x < width) {
            adjacentTile = tiles[plane][x + 1][z];
            if (adjacentTile != null && adjacentTile.groundDecor != null && adjacentTile.groundDecor.entity.method4543()) {
                entity.method4544(adjacentTile.groundDecor.entity, 128, 0, 0, true);
            }
        }
        if (z < width) {
            adjacentTile = tiles[plane][x][z + 1];
            if (adjacentTile != null && adjacentTile.groundDecor != null && adjacentTile.groundDecor.entity.method4543()) {
                entity.method4544(adjacentTile.groundDecor.entity, 0, 0, 128, true);
            }
        }
        if (x < width && z < length) {
            adjacentTile = tiles[plane][x + 1][z + 1];
            if (adjacentTile != null && adjacentTile.groundDecor != null && adjacentTile.groundDecor.entity.method4543()) {
                entity.method4544(adjacentTile.groundDecor.entity, 128, 0, 128, true);
            }
        }
        if (x < width && z > 0) {
            adjacentTile = tiles[plane][x + 1][z - 1];
            if (adjacentTile != null && adjacentTile.groundDecor != null && adjacentTile.groundDecor.entity.method4543()) {
                entity.method4544(adjacentTile.groundDecor.entity, 128, 0, -128, true);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!mf", name = "a", descriptor = "(IIIII[[[B[I[I[I[I[IIBII)V")
    public static void setupSceneRender(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int z, @OriginalArg(3) int yaw, @OriginalArg(4) int pitch, @OriginalArg(5) byte[][][] flags, @OriginalArg(6) int[] viewDistanceArray, @OriginalArg(7) int[] projectionXArray, @OriginalArg(8) int[] screenDepthArray, @OriginalArg(9) int[] projectionYArray, @OriginalArg(10) int[] projectionZArray, @OriginalArg(11) int renderLevel, @OriginalArg(12) byte renderMode, @OriginalArg(13) int centerX, @OriginalArg(14) int centerZ) {
        if (x < 0) {
            x = 0;
        } else if (x >= width * 128) {
            x = width * 128 - 1;
        }
        if (z < 0) {
            z = 0;
        } else if (z >= length * 128) {
            z = length * 128 - 1;
        }
        sinYaw = MathUtils.sin[yaw];
        cosYaw = MathUtils.cos[yaw];
        sinPitch = MathUtils.sin[pitch];
        cosPitch = MathUtils.cos[pitch];
        eyeX = x;
        eyeY = y;
        eyeZ = z;
        eyeTileX = x / 128;
        eyeTileZ = z / 128;
        LightingManager.anInt987 = eyeTileX - visibility;
        if (LightingManager.anInt987 < 0) {
            LightingManager.anInt987 = 0;
        }
        LightingManager.anInt4698 = eyeTileZ - visibility;
        if (LightingManager.anInt4698 < 0) {
            LightingManager.anInt4698 = 0;
        }
        LightingManager.anInt15 = eyeTileX + visibility;
        if (LightingManager.anInt15 > width) {
            LightingManager.anInt15 = width;
        }
        LightingManager.anInt4866 = eyeTileZ + visibility;
        if (LightingManager.anInt4866 > length) {
            LightingManager.anInt4866 = length;
        }
        @Pc(99) short viewDistance;
        if (GlRenderer.enabled) {
            viewDistance = 3584;
        } else {
            viewDistance = 3500;
        }
        @Pc(104) int tileX;
        @Pc(113) int tileZ;
        for (tileX = 0; tileX < visibility + visibility + 2; tileX++) {
            for (tileZ = 0; tileZ < visibility + visibility + 2; tileZ++) {
                @Pc(130) int relativeX = (tileX - visibility << 7) - (eyeX & 0x7F);
                @Pc(140) int relativeZ = (tileZ - visibility << 7) - (eyeZ & 0x7F);
                @Pc(146) int worldX = eyeTileX + tileX - visibility;
                @Pc(152) int worldZ = eyeTileZ + tileZ - visibility;
                if (worldX >= 0 && worldZ >= 0 && worldX < width && worldZ < length) {
                    @Pc(176) int surfaceHeight;
                    if (underwaterTileHeights == null) {
                        surfaceHeight = surfaceTileHeights[0][worldX][worldZ] + 128 - eyeY;
                    } else {
                        surfaceHeight = underwaterTileHeights[0][worldX][worldZ] + 128 - eyeY;
                    }
                    @Pc(201) int ceilingHeight = surfaceTileHeights[3][worldX][worldZ] - eyeY - 1000;
                    tileCulling[tileX][tileZ] = isLineVisible(relativeX, ceilingHeight, surfaceHeight, relativeZ, viewDistance);
                } else {
                    tileCulling[tileX][tileZ] = false;
                }
            }
        }
        for (tileX = 0; tileX < visibility + visibility + 1; tileX++) {
            for (tileZ = 0; tileZ < visibility + visibility + 1; tileZ++) {
                tileVisibility[tileX][tileZ] = tileCulling[tileX][tileZ] || tileCulling[tileX + 1][tileZ] || tileCulling[tileX][tileZ + 1] || tileCulling[tileX + 1][tileZ + 1];
            }
        }
        viewDistances = viewDistanceArray;
        projectionX = projectionXArray;
        screenDepth = screenDepthArray;
        projectionY = projectionYArray;
        projectionZ = projectionZArray;
        processOccluders();
        if (underWaterGroundTiles != null) {
            setUnderwater(true);
            renderVisibleTiles(x, y, z, null, 0, (byte) 0, centerX, centerZ);
            if (GlRenderer.enabled) {
                MaterialManager.renderingUnderwater = false;
                MaterialManager.setMaterial(0, 0);
                FogManager.setFogColor(null);
                LightingManager.method2390();
            }
            setUnderwater(false);
        }
        renderVisibleTiles(x, y, z, flags, renderLevel, renderMode, centerX, centerZ);
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(IIIILclient!th;JLclient!th;Lclient!th;)V")
    public static void setObjStack(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int height, @OriginalArg(4) Entity primary, @OriginalArg(5) long key, @OriginalArg(6) Entity secondary, @OriginalArg(7) Entity tertiary) {
        @Pc(3) ObjStackEntity objStack = new ObjStackEntity();
        objStack.primary = primary;
        objStack.xFine = x * 128 + 64;
        objStack.zFine = z * 128 + 64;
        objStack.anInt3057 = height;
        objStack.key = key;
        objStack.secondary = secondary;
        objStack.tertiary = tertiary;
        @Pc(34) int minHeight = 0;
        @Pc(42) Tile tile = tiles[plane][x][z];
        if (tile != null) {
            for (@Pc(46) int sceneryIndex = 0; sceneryIndex < tile.sceneryLen; sceneryIndex++) {
                @Pc(55) Scenery scenery = tile.scenery[sceneryIndex];
                if ((scenery.key & 0x400000L) == 4194304L) {
                    @Pc(66) int sceneryMinY = scenery.entity.getMinY();
                    if (sceneryMinY != -32768 && sceneryMinY < minHeight) {
                        minHeight = sceneryMinY;
                    }
                }
            }
        }
        objStack.offset = -minHeight;
        if (tiles[plane][x][z] == null) {
            tiles[plane][x][z] = new Tile(plane, x, z);
        }
        tiles[plane][x][z].objStack = objStack;
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!th;IIIII)V")
    public static void connectAdjacentEntities(@OriginalArg(0) Entity entity, @OriginalArg(1) int plane, @OriginalArg(2) int x, @OriginalArg(3) int z, @OriginalArg(4) int width, @OriginalArg(5) int length) {
        @Pc(1) boolean isFirstLevel = true;
        @Pc(3) int startX = x;
        @Pc(7) int endX = x + width;
        @Pc(11) int startZ = z - 1;
        @Pc(15) int endZ = z + length;
        for (@Pc(17) int currentLevel = plane; currentLevel <= plane + 1; currentLevel++) {
            if (currentLevel != planes) {
                for (@Pc(28) int currentX = startX; currentX <= endX; currentX++) {
                    if (currentX >= 0 && currentX < SceneGraph.width) {
                        for (@Pc(39) int currentZ = startZ; currentZ <= endZ; currentZ++) {
                            if (currentZ >= 0 && currentZ < SceneGraph.length && (!isFirstLevel || currentX >= endX || currentZ >= endZ || currentZ < z && currentX != x)) {
                                @Pc(71) Tile tile = tiles[currentLevel][currentX][currentZ];
                                if (tile != null) {
                                    @Pc(158) int heightDifference = (tileHeights[currentLevel][currentX][currentZ] + tileHeights[currentLevel][currentX + 1][currentZ] + tileHeights[currentLevel][currentX][currentZ + 1] + tileHeights[currentLevel][currentX + 1][currentZ + 1]) / 4 - (tileHeights[plane][x][z] + tileHeights[plane][x + 1][z] + tileHeights[plane][x][z + 1] + tileHeights[plane][x + 1][z + 1]) / 4;
                                    @Pc(161) Wall wall = tile.wall;
                                    if (wall != null) {
                                        if (wall.primary.method4543()) {
                                            entity.method4544(wall.primary, (currentX - x) * 128 + (1 - width) * 64, heightDifference, (currentZ - z) * 128 + (1 - length) * 64, isFirstLevel);
                                        }
                                        if (wall.secondary != null && wall.secondary.method4543()) {
                                            entity.method4544(wall.secondary, (currentX - x) * 128 + (1 - width) * 64, heightDifference, (currentZ - z) * 128 + (1 - length) * 64, isFirstLevel);
                                        }
                                    }
                                    for (@Pc(232) int sceneryIndex = 0; sceneryIndex < tile.sceneryLen; sceneryIndex++) {
                                        @Pc(241) Scenery scenery = tile.scenery[sceneryIndex];
                                        if (scenery != null && scenery.entity.method4543() && (currentX == scenery.xMin || currentX == startX) && (currentZ == scenery.zMin || currentZ == startZ)) {
                                            @Pc(270) int sceneryWidth = scenery.xMax + 1 - scenery.xMin;
                                            @Pc(278) int sceneryLength = scenery.zMax + 1 - scenery.zMin;
                                            entity.method4544(scenery.entity, (scenery.xMin - x) * 128 + (sceneryWidth - width) * 64, heightDifference, (scenery.zMin - z) * 128 + (sceneryLength - length) * 64, isFirstLevel);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                startX--;
                isFirstLevel = false;
            }
        }
    }

    @OriginalMember(owner = "client!bh", name = "a", descriptor = "(IIII)V")
    public static void setWallDecoration(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int scale) {
        @Pc(7) Tile tile = tiles[plane][x][z];
        if (tile == null) {
            return;
        }
        @Pc(13) WallDecor wallDecor = tile.wallDecor;
        if (wallDecor != null) {
            wallDecor.xOffset = wallDecor.xOffset * scale / 16;
            wallDecor.zOffset = wallDecor.zOffset * scale / 16;
        }
    }

    @OriginalMember(owner = "runetek4.client!s", name = "a", descriptor = "([[F[[B[[B[Lclient!gi;II[[F[[B[[B[[II[[F)V")
    public static void generateLightMaps(@OriginalArg(0) float[][] lightIntensity, @OriginalArg(1) byte[][] underlayIds, @OriginalArg(2) byte[][] tileRotations, @OriginalArg(3) Light[] lights, @OriginalArg(4) int plane, @OriginalArg(5) int lightCount, @OriginalArg(6) float[][] shadowMap, @OriginalArg(7) byte[][] overlayShapes, @OriginalArg(8) byte[][] overlayIds, @OriginalArg(9) int[][] heightMap, @OriginalArg(11) float[][] ambientLight) {
        for (@Pc(7) int lightIndex = 0; lightIndex < lightCount; lightIndex++) {
            @Pc(18) Light light = lights[lightIndex];
            if (light.level == plane) {
                @Pc(24) int radiusOffset = 0;
                @Pc(28) LightBuffer lightBuffer = new LightBuffer();
                @Pc(37) int minTileX = (light.x >> 7) - light.radius;
                @Pc(46) int minTileZ = (light.z >> 7) - light.radius;
                if (minTileZ < 0) {
                    radiusOffset = -minTileZ;
                    minTileZ = 0;
                }
                @Pc(65) int maxTileZ = light.radius + (light.z >> 7);
                if (maxTileZ > 103) {
                    maxTileZ = 103;
                }
                @Pc(72) int tileZ;
                @Pc(84) short radiusData;
                @Pc(90) int startX;
                @Pc(99) int endX;
                @Pc(114) int tileX;
                @Pc(133) int overlayId;
                @Pc(328) boolean adjacentRight;
                @Pc(355) int adjacentEndX;
                for (tileZ = minTileZ; tileZ <= maxTileZ; tileZ++) {
                    radiusData = light.aShortArray30[radiusOffset];
                    startX = minTileX + (radiusData >> 8);
                    endX = startX + (radiusData & 0xFF) - 1;
                    if (endX > 103) {
                        endX = 103;
                    }
                    if (startX < 0) {
                        startX = 0;
                    }
                    for (tileX = startX; tileX <= endX; tileX++) {
                        @Pc(125) int underlayId = underlayIds[tileX][tileZ] & 0xFF;
                        overlayId = overlayIds[tileX][tileZ] & 0xFF;
                        @Pc(135) boolean hasOverlay = false;
                        @Pc(151) FloorOverlayType floorOverlayType;
                        @Pc(176) int[] overlayShape;
                        @Pc(234) int[] underlayShape;
                        if (underlayId == 0) {
                            if (overlayId == 0) {
                                continue;
                            }
                            floorOverlayType = FloorOverlayTypeList.method4395(overlayId - 1);
                            if (floorOverlayType.baseColor == -1) {
                                continue;
                            }
                            if (overlayShapes[tileX][tileZ] != 0) {
                                overlayShape = SHAPE_OVERLAY_COORDINATES[overlayShapes[tileX][tileZ]];
                                lightBuffer.anInt2016 += ((overlayShape.length >> 1) - 2) * 3;
                                lightBuffer.anInt2019 += overlayShape.length >> 1;
                                continue;
                            }
                        } else if (overlayId != 0) {
                            floorOverlayType = FloorOverlayTypeList.method4395(overlayId - 1);
                            @Pc(224) byte local224;
                            if (floorOverlayType.baseColor == -1) {
                                local224 = overlayShapes[tileX][tileZ];
                                if (local224 != 0) {
                                    underlayShape = SHAPE_WALL_COORDINATES[local224];
                                    lightBuffer.anInt2016 += ((underlayShape.length >> 1) - 2) * 3;
                                    lightBuffer.anInt2019 += underlayShape.length >> 1;
                                }
                                continue;
                            }
                            local224 = overlayShapes[tileX][tileZ];
                            if (local224 != 0) {
                                hasOverlay = true;
                            }
                        }
                        @Pc(275) Scenery scenery = getScenery(plane, tileX, tileZ);
                        if (scenery != null) {
                            @Pc(287) int sceneryShape = (int) (scenery.key >> 14) & 0x3F;
                            if (sceneryShape == 9) {
                                underlayShape = null;
                                @Pc(302) int sceneryRotation = (int) (scenery.key >> 20) & 0x3;
                                @Pc(315) boolean adjacentLeft;
                                @Pc(343) short adjacentRadiusData;
                                @Pc(349) int adjacentStartX;
                                if ((sceneryRotation & 0x1) == 0) {
                                    adjacentRight = endX >= tileX + 1;
                                    adjacentLeft = tileX - 1 >= startX;
                                    if (!adjacentLeft && tileZ + 1 <= maxTileZ) {
                                        adjacentRadiusData = light.aShortArray30[radiusOffset + 1];
                                        adjacentStartX = minTileX + (adjacentRadiusData >> 8);
                                        adjacentEndX = adjacentStartX + (adjacentRadiusData & 0xFF);
                                        adjacentLeft = adjacentStartX < tileX && tileX < adjacentEndX;
                                    }
                                    if (!adjacentRight && tileZ - 1 >= minTileZ) {
                                        adjacentRadiusData = light.aShortArray30[radiusOffset - 1];
                                        adjacentStartX = minTileX + (adjacentRadiusData >> 8);
                                        adjacentEndX = adjacentStartX + (adjacentRadiusData & 0xFF);
                                        adjacentRight = tileX > adjacentStartX && tileX < adjacentEndX;
                                    }
                                    if (adjacentLeft && adjacentRight) {
                                        underlayShape = SHAPE_OVERLAY_COORDINATES[0];
                                    } else if (adjacentLeft) {
                                        underlayShape = SHAPE_OVERLAY_COORDINATES[1];
                                    } else if (adjacentRight) {
                                        underlayShape = SHAPE_OVERLAY_COORDINATES[1];
                                    }
                                } else {
                                    adjacentLeft = startX <= tileX - 1;
                                    adjacentRight = tileX + 1 <= endX;
                                    if (!adjacentLeft && tileZ - 1 >= minTileZ) {
                                        adjacentRadiusData = light.aShortArray30[radiusOffset - 1];
                                        adjacentStartX = (adjacentRadiusData >> 8) + minTileX;
                                        adjacentEndX = adjacentStartX + (adjacentRadiusData & 0xFF);
                                        adjacentLeft = adjacentStartX < tileX && tileX < adjacentEndX;
                                    }
                                    if (!adjacentRight && tileZ + 1 <= maxTileZ) {
                                        adjacentRadiusData = light.aShortArray30[radiusOffset + 1];
                                        adjacentStartX = (adjacentRadiusData >> 8) + minTileX;
                                        adjacentEndX = adjacentStartX + (adjacentRadiusData & 0xFF);
                                        adjacentRight = adjacentStartX < tileX && adjacentEndX > tileX;
                                    }
                                    if (adjacentLeft && adjacentRight) {
                                        underlayShape = SHAPE_OVERLAY_COORDINATES[0];
                                    } else if (adjacentLeft) {
                                        underlayShape = SHAPE_OVERLAY_COORDINATES[1];
                                    } else if (adjacentRight) {
                                        underlayShape = SHAPE_OVERLAY_COORDINATES[1];
                                    }
                                }
                                if (underlayShape != null) {
                                    lightBuffer.anInt2016 += (underlayShape.length >> 1) * 3 - 6;
                                    lightBuffer.anInt2019 += underlayShape.length >> 1;
                                }
                                continue;
                            }
                        }
                        if (hasOverlay) {
                            underlayShape = SHAPE_WALL_COORDINATES[overlayShapes[tileX][tileZ]];
                            overlayShape = SHAPE_OVERLAY_COORDINATES[overlayShapes[tileX][tileZ]];
                            lightBuffer.anInt2016 += ((overlayShape.length >> 1) - 2) * 3;
                            lightBuffer.anInt2016 += ((underlayShape.length >> 1) - 2) * 3;
                            lightBuffer.anInt2019 += overlayShape.length >> 1;
                            lightBuffer.anInt2019 += underlayShape.length >> 1;
                        } else {
                            overlayShape = SHAPE_OVERLAY_COORDINATES[0];
                            lightBuffer.anInt2016 += ((overlayShape.length >> 1) - 2) * 3;
                            lightBuffer.anInt2019 += overlayShape.length >> 1;
                        }
                    }
                    radiusOffset++;
                }
                radiusOffset = 0;
                lightBuffer.method1555();
                if ((light.z >> 7) - light.radius < 0) {
                    radiusOffset = light.radius - (light.z >> 7);
                }
                for (tileZ = minTileZ; tileZ <= maxTileZ; tileZ++) {
                    radiusData = light.aShortArray30[radiusOffset];
                    startX = (radiusData >> 8) + minTileX;
                    endX = (radiusData & 0xFF) + startX - 1;
                    if (endX > 103) {
                        endX = 103;
                    }
                    if (startX < 0) {
                        startX = 0;
                    }
                    for (tileX = startX; tileX <= endX; tileX++) {
                        @Pc(775) int local775 = overlayIds[tileX][tileZ] & 0xFF;
                        overlayId = underlayIds[tileX][tileZ] & 0xFF;
                        @Pc(789) byte local789 = tileRotations[tileX][tileZ];
                        @Pc(791) boolean local791 = false;
                        @Pc(805) FloorOverlayType local805;
                        if (overlayId == 0) {
                            if (local775 == 0) {
                                continue;
                            }
                            local805 = FloorOverlayTypeList.method4395(local775 - 1);
                            if (local805.baseColor == -1) {
                                continue;
                            }
                            if (overlayShapes[tileX][tileZ] != 0) {
                                addLightVertices(lightIntensity, heightMap, tileX, shadowMap, tileZ, SHAPE_OVERLAY_COORDINATES[overlayShapes[tileX][tileZ]], lightBuffer, light, ambientLight, tileRotations[tileX][tileZ]);
                                continue;
                            }
                        } else if (local775 != 0) {
                            local805 = FloorOverlayTypeList.method4395(local775 - 1);
                            if (local805.baseColor == -1) {
                                addLightVertices(lightIntensity, heightMap, tileX, shadowMap, tileZ, SHAPE_WALL_COORDINATES[overlayShapes[tileX][tileZ]], lightBuffer, light, ambientLight, tileRotations[tileX][tileZ]);
                                continue;
                            }
                            @Pc(815) byte local815 = overlayShapes[tileX][tileZ];
                            if (local815 != 0) {
                                local791 = true;
                            }
                        }
                        @Pc(899) Scenery local899 = getScenery(plane, tileX, tileZ);
                        if (local899 != null) {
                            @Pc(911) int local911 = (int) (local899.key >> 14) & 0x3F;
                            if (local911 == 9) {
                                @Pc(917) int[] local917 = null;
                                @Pc(925) int local925 = (int) (local899.key >> 20) & 0x3;
                                @Pc(973) int local973;
                                @Pc(947) boolean local947;
                                @Pc(961) short local961;
                                if ((local925 & 0x1) == 0) {
                                    adjacentRight = tileX - 1 >= startX;
                                    local947 = endX >= tileX + 1;
                                    if (!adjacentRight && maxTileZ >= tileZ + 1) {
                                        local961 = light.aShortArray30[radiusOffset + 1];
                                        adjacentEndX = (local961 >> 8) + minTileX;
                                        local973 = (local961 & 0xFF) + adjacentEndX;
                                        adjacentRight = tileX > adjacentEndX && local973 > tileX;
                                    }
                                    if (!local947 && tileZ - 1 >= minTileZ) {
                                        local961 = light.aShortArray30[radiusOffset - 1];
                                        adjacentEndX = minTileX + (local961 >> 8);
                                        local973 = (local961 & 0xFF) + adjacentEndX;
                                        local947 = adjacentEndX < tileX && local973 > tileX;
                                    }
                                    if (adjacentRight && local947) {
                                        local917 = SHAPE_OVERLAY_COORDINATES[0];
                                    } else if (adjacentRight) {
                                        local917 = SHAPE_OVERLAY_COORDINATES[1];
                                        local789 = 1;
                                    } else if (local947) {
                                        local917 = SHAPE_OVERLAY_COORDINATES[1];
                                        local789 = 3;
                                    }
                                } else {
                                    adjacentRight = tileX - 1 >= startX;
                                    local947 = endX >= tileX + 1;
                                    if (!adjacentRight && minTileZ <= tileZ - 1) {
                                        local961 = light.aShortArray30[radiusOffset - 1];
                                        adjacentEndX = minTileX + (local961 >> 8);
                                        local973 = (local961 & 0xFF) + adjacentEndX;
                                        adjacentRight = tileX > adjacentEndX && local973 > tileX;
                                    }
                                    if (!local947 && maxTileZ >= tileZ + 1) {
                                        local961 = light.aShortArray30[radiusOffset + 1];
                                        adjacentEndX = minTileX + (local961 >> 8);
                                        local973 = (local961 & 0xFF) + adjacentEndX;
                                        local947 = tileX > adjacentEndX && local973 > tileX;
                                    }
                                    if (adjacentRight && local947) {
                                        local917 = SHAPE_OVERLAY_COORDINATES[0];
                                    } else if (adjacentRight) {
                                        local789 = 0;
                                        local917 = SHAPE_OVERLAY_COORDINATES[1];
                                    } else if (local947) {
                                        local917 = SHAPE_OVERLAY_COORDINATES[1];
                                        local789 = 2;
                                    }
                                }
                                if (local917 != null) {
                                    addLightVertices(lightIntensity, heightMap, tileX, shadowMap, tileZ, local917, lightBuffer, light, ambientLight, local789);
                                }
                                continue;
                            }
                        }
                        if (local791) {
                            addLightVertices(lightIntensity, heightMap, tileX, shadowMap, tileZ, SHAPE_WALL_COORDINATES[overlayShapes[tileX][tileZ]], lightBuffer, light, ambientLight, tileRotations[tileX][tileZ]);
                            addLightVertices(lightIntensity, heightMap, tileX, shadowMap, tileZ, SHAPE_OVERLAY_COORDINATES[overlayShapes[tileX][tileZ]], lightBuffer, light, ambientLight, tileRotations[tileX][tileZ]);
                        } else {
                            addLightVertices(lightIntensity, heightMap, tileX, shadowMap, tileZ, SHAPE_OVERLAY_COORDINATES[0], lightBuffer, light, ambientLight, local789);
                        }
                    }
                    radiusOffset++;
                }
                if (lightBuffer.anInt2017 > 0 && lightBuffer.anInt2018 > 0) {
                    lightBuffer.method1554();
                    light.aLightBuffer_1 = lightBuffer;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!pi", name = "a", descriptor = "([[[B[[B[[B[[I[[F[[I[[B[[B[[FI[[F[[I[[I[[II)[Lclient!hg;")
    public static GlTile[] buildGlTiles(@OriginalArg(0) byte[][][] renderFlags, @OriginalArg(1) byte[][] underlayShapes, @OriginalArg(2) byte[][] underlayIds, @OriginalArg(3) int[][] tileHeights, @OriginalArg(4) float[][] lightValues, @OriginalArg(5) int[][] colorData, @OriginalArg(6) byte[][] overlayShapes, @OriginalArg(7) byte[][] overlayRotations, @OriginalArg(8) float[][] shadowValues, @OriginalArg(9) int plane, @OriginalArg(10) float[][] ambientLight, @OriginalArg(11) int[][] heightMap, @OriginalArg(12) int[][] normalMap, @OriginalArg(13) int[][] waterHeights) {
        @Pc(9) int[][] materialMap = new int[105][105];
        @Pc(16) int z;
        for (@Pc(11) int x = 1; x <= 103; x++) {
            for (z = 1; z <= 103; z++) {
                @Pc(25) byte underlayId = underlayIds[x][z];
                if (underlayId == 0) {
                    underlayId = underlayIds[x - 1][z];
                }
                if (underlayId == 0) {
                    underlayId = underlayIds[x][z - 1];
                }
                if (underlayId == 0) {
                    underlayId = underlayIds[x - 1][z - 1];
                }
                if (underlayId != 0) {
                    @Pc(77) FloorUnderlayType floorUnderlayType = FloorUnderlayTypeList.get((underlayId & 0xFF) - 1);
                    materialMap[x][z] = (floorUnderlayType.material + 1 << 16) + floorUnderlayType.hardShadow;
                }
            }
        }
        @Pc(103) IterableHashTable glTileMap = new IterableHashTable(128);
        @Pc(155) int colorValue;
        @Pc(161) int materialId1;
        @Pc(169) int materialId2;
        @Pc(112) int tileZ;
        for (z = 1; z <= 102; z++) {
            for (tileZ = 1; tileZ <= 102; tileZ++) {
                if (underlayIds[z][tileZ] != 0) {
                    @Pc(135) int[] shapeVertices;
                    if (overlayShapes[z][tileZ] == 0) {
                        shapeVertices = SHAPE_OVERLAY_COORDINATES[0];
                    } else {
                        shapeVertices = SHAPE_WALL_COORDINATES[underlayShapes[z][tileZ]];
                        if (shapeVertices.length == 0) {
                            continue;
                        }
                    }
                    colorValue = 0;
                    materialId1 = materialMap[z][tileZ];
                    materialId2 = materialMap[z + 1][tileZ];
                    if (colorData != null) {
                        colorValue = colorData[z][tileZ] & 0xFFFFFF;
                    }
                    @Pc(188) long tileKey1 = (long) colorValue | (long) materialId2 << 32;
                    @Pc(196) int materialId3 = materialMap[z][tileZ + 1];
                    @Pc(206) int materialId4 = materialMap[z + 1][tileZ + 1];
                    @Pc(214) long tileKey2 = (long) materialId3 << 32 | (long) colorValue;
                    @Pc(219) int vertexCount = shapeVertices.length / 2;
                    @Pc(227) long baseTileKey = (long) colorValue | (long) materialId1 << 32;
                    @Pc(233) GlTile glTile = (GlTile) glTileMap.get(baseTileKey);
                    if (glTile == null) {
                        glTile = new GlTile((materialId1 >> 16) - 1, (float) (materialId1 & 0xFFFF), false, waterHeights != null, colorValue);
                        glTileMap.put(glTile, baseTileKey);
                    }
                    glTile.anInt2484++;
                    glTile.anInt2482 += vertexCount;
                    if (tileKey1 != baseTileKey) {
                        glTile = (GlTile) glTileMap.get(tileKey1);
                        if (glTile == null) {
                            glTile = new GlTile((materialId2 >> 16) - 1, (float) (materialId2 & 0xFFFF), false, waterHeights != null, colorValue);
                            glTileMap.put(glTile, tileKey1);
                        }
                        glTile.anInt2484++;
                        glTile.anInt2482 += vertexCount;
                    }
                    @Pc(340) long tileKey3 = (long) materialId4 << 32 | (long) colorValue;
                    if (tileKey3 != baseTileKey && tileKey3 != tileKey1) {
                        glTile = (GlTile) glTileMap.get(tileKey3);
                        if (glTile == null) {
                            glTile = new GlTile((materialId4 >> 16) - 1, (float) (materialId4 & 0xFFFF), false, waterHeights != null, colorValue);
                            glTileMap.put(glTile, tileKey3);
                        }
                        glTile.anInt2482 += vertexCount;
                        glTile.anInt2484++;
                    }
                    if (tileKey2 != baseTileKey && tileKey1 != tileKey2 && tileKey2 != tileKey3) {
                        glTile = (GlTile) glTileMap.get(tileKey2);
                        if (glTile == null) {
                            glTile = new GlTile((materialId3 >> 16) - 1, (float) (materialId3 & 0xFFFF), false, waterHeights != null, colorValue);
                            glTileMap.put(glTile, tileKey2);
                        }
                        glTile.anInt2484++;
                        glTile.anInt2482 += vertexCount;
                    }
                }
            }
        }
        @Pc(493) GlTile currentTile;
        for (currentTile = (GlTile) glTileMap.head(); currentTile != null; currentTile = (GlTile) glTileMap.next()) {
            currentTile.method1940();
        }
        for (z = 1; z <= 102; z++) {
            for (tileZ = 1; tileZ <= 102; tileZ++) {
                @Pc(524) byte currentUnderlayId = underlayIds[z][tileZ];
                if (currentUnderlayId != 0) {
                    if ((renderFlags[plane][z][tileZ] & 0x8) != 0) {
                        colorValue = 0;
                    } else if ((renderFlags[1][z][tileZ] & 0x2) == 2 && plane > 0) {
                        colorValue = plane - 1;
                    } else {
                        colorValue = plane;
                    }
                    materialId1 = 0;
                    @Pc(574) boolean[] shapeVisibility = null;
                    materialId2 = 128;
                    if (colorData != null) {
                        materialId2 = colorData[z][tileZ] >>> 24 << 3;
                        materialId1 = colorData[z][tileZ] & 0xFFFFFF;
                    }
                    @Pc(655) int neighborComparison1;
                    @Pc(712) int neighborComparison3;
                    @Pc(614) int[] currentShapeVertices;
                    @Pc(628) byte rotation;
                    @Pc(678) int neighborComparison2;
                    @Pc(754) int neighborComparison4;
                    if (overlayShapes[z][tileZ] == 0) {
                        neighborComparison1 = currentUnderlayId == underlayIds[z - 1][tileZ - 1] ? 1 : -1;
                        currentShapeVertices = SHAPE_OVERLAY_COORDINATES[0];
                        neighborComparison2 = currentUnderlayId == underlayIds[z + 1][tileZ - 1] ? 1 : -1;
                        if (underlayIds[z][tileZ - 1] == currentUnderlayId) {
                            neighborComparison2++;
                            neighborComparison1++;
                        } else {
                            neighborComparison1--;
                            neighborComparison2--;
                        }
                        neighborComparison3 = currentUnderlayId == underlayIds[z + 1][tileZ + 1] ? 1 : -1;
                        if (currentUnderlayId == underlayIds[z + 1][tileZ]) {
                            neighborComparison3++;
                            neighborComparison2++;
                        } else {
                            neighborComparison2--;
                            neighborComparison3--;
                        }
                        neighborComparison4 = currentUnderlayId == underlayIds[z - 1][tileZ + 1] ? 1 : -1;
                        if (underlayIds[z][tileZ + 1] == currentUnderlayId) {
                            neighborComparison4++;
                            neighborComparison3++;
                        } else {
                            neighborComparison3--;
                            neighborComparison4--;
                        }
                        if (underlayIds[z - 1][tileZ] == currentUnderlayId) {
                            neighborComparison4++;
                            neighborComparison1++;
                        } else {
                            neighborComparison4--;
                            neighborComparison1--;
                        }
                        @Pc(789) int diagonalDiff = neighborComparison1 - neighborComparison3;
                        @Pc(794) int cardinalDiff = neighborComparison2 - neighborComparison4;
                        if (cardinalDiff < 0) {
                            cardinalDiff = -cardinalDiff;
                        }
                        if (diagonalDiff < 0) {
                            diagonalDiff = -diagonalDiff;
                        }
                        rotation = (byte) (cardinalDiff <= diagonalDiff ? 0 : 1);
                        overlayRotations[z][tileZ] = rotation;
                    } else {
                        currentShapeVertices = SHAPE_WALL_COORDINATES[underlayShapes[z][tileZ]];
                        shapeVisibility = SHAPE_TEXTURE_OVERLAY_FLAGS[underlayShapes[z][tileZ]];
                        rotation = overlayRotations[z][tileZ];
                        if (currentShapeVertices.length == 0) {
                            continue;
                        }
                    }
                    neighborComparison1 = materialMap[z][tileZ];
                    neighborComparison2 = materialMap[z + 1][tileZ];
                    neighborComparison3 = materialMap[z + 1][tileZ + 1];
                    @Pc(861) long cornerKey1 = (long) neighborComparison1 << 32 | (long) materialId1;
                    @Pc(869) long cornerKey2 = (long) neighborComparison2 << 32 | (long) materialId1;
                    @Pc(877) long cornerKey3 = (long) neighborComparison3 << 32 | (long) materialId1;
                    @Pc(883) int local883 = heightMap[z][tileZ];
                    neighborComparison4 = materialMap[z][tileZ + 1];
                    @Pc(901) int local901 = heightMap[z + 1][tileZ + 1];
                    @Pc(909) int local909 = heightMap[z + 1][tileZ];
                    @Pc(917) long cornerKey4 = (long) materialId1 | (long) neighborComparison4 << 32;
                    @Pc(925) int local925 = heightMap[z][tileZ + 1];
                    @Pc(931) int local931 = tileHeights[z][tileZ];
                    @Pc(939) int local939 = tileHeights[z + 1][tileZ];
                    @Pc(949) int local949 = tileHeights[z + 1][tileZ + 1];
                    @Pc(957) int local957 = tileHeights[z][tileZ + 1];
                    @Pc(963) int local963 = (neighborComparison2 >> 16) - 1;
                    @Pc(969) int local969 = (neighborComparison1 >> 16) - 1;
                    @Pc(975) int local975 = (neighborComparison3 >> 16) - 1;
                    @Pc(981) GlTile local981 = (GlTile) glTileMap.get(cornerKey1);
                    buildTileGeometry(waterHeights, neighborComparison1 <= neighborComparison1, calculateTexturedColor(local969, local883, local931), local981, currentShapeVertices, tileZ, colorValue, z, neighborComparison1 <= neighborComparison3, shadowValues, neighborComparison4 >= neighborComparison1, lightValues, materialId2, calculateTexturedColor(local969, local925, local957), calculateTexturedColor(local969, local901, local949), neighborComparison1 <= neighborComparison2, normalMap, ambientLight, rotation, calculateTexturedColor(local969, local909, local939), shapeVisibility);
                    @Pc(1050) int local1050 = (neighborComparison4 >> 16) - 1;
                    if (cornerKey2 != cornerKey1) {
                        local981 = (GlTile) glTileMap.get(cornerKey2);
                        buildTileGeometry(waterHeights, neighborComparison2 <= neighborComparison1, calculateTexturedColor(local963, local883, local931), local981, currentShapeVertices, tileZ, colorValue, z, neighborComparison3 >= neighborComparison2, shadowValues, neighborComparison2 <= neighborComparison4, lightValues, materialId2, calculateTexturedColor(local963, local925, local957), calculateTexturedColor(local963, local901, local949), neighborComparison2 <= neighborComparison2, normalMap, ambientLight, rotation, calculateTexturedColor(local963, local909, local939), shapeVisibility);
                    }
                    if (cornerKey3 != cornerKey1 && cornerKey3 != cornerKey2) {
                        local981 = (GlTile) glTileMap.get(cornerKey3);
                        buildTileGeometry(waterHeights, neighborComparison1 >= neighborComparison3, calculateTexturedColor(local975, local883, local931), local981, currentShapeVertices, tileZ, colorValue, z, neighborComparison3 <= neighborComparison3, shadowValues, neighborComparison3 <= neighborComparison4, lightValues, materialId2, calculateTexturedColor(local975, local925, local957), calculateTexturedColor(local975, local901, local949), neighborComparison2 >= neighborComparison3, normalMap, ambientLight, rotation, calculateTexturedColor(local975, local909, local939), shapeVisibility);
                    }
                    if (cornerKey4 != cornerKey1 && cornerKey4 != cornerKey2 && cornerKey4 != cornerKey3) {
                        local981 = (GlTile) glTileMap.get(cornerKey4);
                        buildTileGeometry(waterHeights, neighborComparison4 <= neighborComparison1, calculateTexturedColor(local1050, local883, local931), local981, currentShapeVertices, tileZ, colorValue, z, neighborComparison4 <= neighborComparison3, shadowValues, neighborComparison4 >= neighborComparison4, lightValues, materialId2, calculateTexturedColor(local1050, local925, local957), calculateTexturedColor(local1050, local901, local949), neighborComparison2 >= neighborComparison4, normalMap, ambientLight, rotation, calculateTexturedColor(local1050, local909, local939), shapeVisibility);
                    }
                }
            }
        }
        for (currentTile = (GlTile) glTileMap.head(); currentTile != null; currentTile = (GlTile) glTileMap.next()) {
            if (currentTile.anInt2483 == 0) {
                currentTile.unlink();
            } else {
                currentTile.method1943();
            }
        }
        z = glTileMap.length();
        @Pc(1348) GlTile[] resultTiles = new GlTile[z];
        glTileMap.addNodes(resultTiles);
        @Pc(1358) long[] sortKeys = new long[z];
        for (colorValue = 0; colorValue < z; colorValue++) {
            sortKeys[colorValue] = resultTiles[colorValue].key;
        }
        ArrayUtils.sort(sortKeys, resultTiles);
        return resultTiles;
    }

    @OriginalMember(owner = "runetek4.client!ec", name = "a", descriptor = "([[IZILclient!hg;[IIIIZ[[FZI[[FIIIZ[[I[[FBI[Z)V")
    public static void buildTileGeometry(@OriginalArg(0) int[][] waterHeights, @OriginalArg(1) boolean corner1Visible, @OriginalArg(2) int color1, @OriginalArg(3) GlTile glTile, @OriginalArg(4) int[] shapeVertices, @OriginalArg(5) int tileZ, @OriginalArg(6) int plane, @OriginalArg(7) int tileX, @OriginalArg(8) boolean corner2Visible, @OriginalArg(9) float[][] shadowValues, @OriginalArg(10) boolean corner3Visible, @OriginalArg(12) float[][] arg11, @OriginalArg(13) int intensity, @OriginalArg(14) int color2, @OriginalArg(15) int color3, @OriginalArg(16) boolean corner4Visible, @OriginalArg(17) int[][] normalHeights, @OriginalArg(18) float[][] ambientLight, @OriginalArg(19) byte rotation, @OriginalArg(20) int color4, @OriginalArg(21) boolean[] vertexVisibility) {
        @Pc(11) int packedColor1 = (color1 << 8) + (corner1Visible ? 255 : 0);
        @Pc(25) int packedColor2 = (corner2Visible ? 255 : 0) + (color3 << 8);
        @Pc(31) int[] vertices = new int[shapeVertices.length / 2];
        @Pc(41) int packedColor3 = (corner3Visible ? 255 : 0) + (color2 << 8);
        @Pc(51) int packedColor4 = (color4 << 8) + (corner4Visible ? 255 : 0);
        for (@Pc(53) int vertexIndex = 0; vertexIndex < vertices.length; vertexIndex++) {
            @Pc(67) int vertexX = shapeVertices[vertexIndex + vertexIndex];
            @Pc(80) int[][] heightMap = waterHeights == null || vertexVisibility == null || !vertexVisibility[vertexIndex] ? normalHeights : waterHeights;
            @Pc(88) int vertexZ = shapeVertices[vertexIndex + vertexIndex + 1];
            vertices[vertexIndex] = createVertex(packedColor3, (float) intensity, packedColor1, packedColor4, waterHeights, heightMap, tileX, ambientLight, packedColor2, rotation, false, glTile, shadowValues, tileZ, vertexX, arg11, vertexZ);
        }
        glTile.method1945(plane, tileX, tileZ, vertices, null, false);
    }

    @OriginalMember(owner = "runetek4.client!af", name = "a", descriptor = "(IIIIIILclient!th;IJ)Z")
    public static boolean addStaticLoc(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z, @OriginalArg(3) int shape, @OriginalArg(4) int width, @OriginalArg(5) int length, @OriginalArg(6) Entity entity, @OriginalArg(8) long key) {
        if (entity == null) {
            return true;
        } else {
            @Pc(11) int centerX = x * 128 + width * 64;
            @Pc(19) int centerZ = z * 128 + length * 64;
            return addLoc(plane, x, z, width, length, centerX, centerZ, shape, entity, 0, false, key);
        }
    }

    @OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(IIII)I")
    public static int getRenderLevel(@OriginalArg(0) int x, @OriginalArg(1) int z, @OriginalArg(3) int plane) {
        if ((renderFlags[plane][z][x] & 0x8) == 0) {
            return plane <= 0 || (renderFlags[1][z][x] & 0x2) == 0 ? plane : plane - 1;
        } else {
            return 0;
        }
    }

    @OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(Lclient!rh;IIIIIIIZ)V")
    public static void renderPlainTile(@OriginalArg(0) PlainTile plainTile, @OriginalArg(1) int plane, @OriginalArg(2) int sinYaw, @OriginalArg(3) int cosYaw, @OriginalArg(4) int sinPitch, @OriginalArg(5) int cosPitch, @OriginalArg(6) int tileX, @OriginalArg(7) int tileZ, @OriginalArg(8) boolean skipRendering) {
        @Pc(6) int baseX;
        @Pc(7) int relativeX1 = baseX = (tileX << 7) - eyeX;
        @Pc(14) int baseZ;
        @Pc(15) int relativeZ1 = baseZ = (tileZ << 7) - eyeZ;
        @Pc(20) int farX;
        @Pc(21) int relativeX2 = farX = relativeX1 + 128;
        @Pc(26) int farZ;
        @Pc(27) int relativeZ2 = farZ = relativeZ1 + 128;
        @Pc(37) int height1 = tileHeights[plane][tileX][tileZ] - eyeY;
        @Pc(49) int height2 = tileHeights[plane][tileX + 1][tileZ] - eyeY;
        @Pc(63) int height3 = tileHeights[plane][tileX + 1][tileZ + 1] - eyeY;
        @Pc(75) int height4 = tileHeights[plane][tileX][tileZ + 1] - eyeY;
        @Pc(85) int rotatedX = relativeZ1 * sinPitch + relativeX1 * cosPitch >> 16;
        @Pc(95) int rotatedZ1 = relativeZ1 * cosPitch - relativeX1 * sinPitch >> 16;
        @Pc(97) int projectedX1 = rotatedX;
        @Pc(107) int transformedY1 = height1 * cosYaw - rotatedZ1 * sinYaw >> 16;
        @Pc(117) int depth1 = height1 * sinYaw + rotatedZ1 * cosYaw >> 16;
        @Pc(119) int projectedY1 = transformedY1;
        if (depth1 < 50) {
            return;
        }
        rotatedX = baseZ * sinPitch + relativeX2 * cosPitch >> 16;
        @Pc(143) int rotatedZ2 = baseZ * cosPitch - relativeX2 * sinPitch >> 16;
        relativeX2 = rotatedX;
        rotatedX = height2 * cosYaw - rotatedZ2 * sinYaw >> 16;
        @Pc(165) int depth2 = height2 * sinYaw + rotatedZ2 * cosYaw >> 16;
        height2 = rotatedX;
        if (depth2 < 50) {
            return;
        }
        rotatedX = relativeZ2 * sinPitch + farX * cosPitch >> 16;
        relativeZ2 = relativeZ2 * cosPitch - farX * sinPitch >> 16;
        @Pc(193) int projectedX3 = rotatedX;
        rotatedX = height3 * cosYaw - relativeZ2 * sinYaw >> 16;
        relativeZ2 = height3 * sinYaw + relativeZ2 * cosYaw >> 16;
        height3 = rotatedX;
        if (relativeZ2 < 50) {
            return;
        }
        rotatedX = farZ * sinPitch + baseX * cosPitch >> 16;
        @Pc(239) int rotatedZ4 = farZ * cosPitch - baseX * sinPitch >> 16;
        @Pc(241) int projectedX4 = rotatedX;
        rotatedX = height4 * cosYaw - rotatedZ4 * sinYaw >> 16;
        @Pc(261) int depth4 = height4 * sinYaw + rotatedZ4 * cosYaw >> 16;
        if (depth4 < 50) {
            return;
        }
        @Pc(275) int screenX1 = Rasterizer.centerX + (projectedX1 << 9) / depth1;
        @Pc(283) int screenY1 = Rasterizer.centerY + (projectedY1 << 9) / depth1;
        @Pc(291) int screenX2 = Rasterizer.centerX + (relativeX2 << 9) / depth2;
        @Pc(299) int screenY2 = Rasterizer.centerY + (height2 << 9) / depth2;
        @Pc(307) int screenX3 = Rasterizer.centerX + (projectedX3 << 9) / relativeZ2;
        @Pc(315) int screenY3 = Rasterizer.centerY + (height3 << 9) / relativeZ2;
        @Pc(323) int screenX4 = Rasterizer.centerX + (projectedX4 << 9) / depth4;
        @Pc(331) int screenY4 = Rasterizer.centerY + (rotatedX << 9) / depth4;
        Rasterizer.alpha = 0;
        @Pc(475) int textureColor;
        if ((screenX3 - screenX4) * (screenY2 - screenY4) - (screenY3 - screenY4) * (screenX2 - screenX4) > 0) {
            if (MiniMenu.walkTargetActive && pointInTriangle(MiniMenu.targetX + Rasterizer.centerX, MiniMenu.targetZ + Rasterizer.centerY, screenY3, screenY4, screenY2, screenX3, screenX4, screenX2)) {
                MiniMenu.clickTileX = tileX;
                MiniMenu.clickTileZ = tileZ;
            }
            if (!GlRenderer.enabled && !skipRendering) {
                Rasterizer.testX = false;
                if (screenX3 < 0 || screenX4 < 0 || screenX2 < 0 || screenX3 > Rasterizer.width || screenX4 > Rasterizer.width || screenX2 > Rasterizer.width) {
                    Rasterizer.testX = true;
                }
                if (plainTile.texture == -1) {
                    if (plainTile.seHeight != 12345678) {
                        Rasterizer.fillGouraudTriangle(screenY3, screenY4, screenY2, screenX3, screenX4, screenX2, plainTile.seHeight, plainTile.nwHeight, plainTile.swHeight);
                    }
                } else if (!Preferences.manyGroundTextures) {
                    textureColor = Rasterizer.textureProvider.getAverageColor(plainTile.texture);
                    Rasterizer.fillGouraudTriangle(screenY3, screenY4, screenY2, screenX3, screenX4, screenX2, ColorUtils.multiplyLightness3(textureColor, plainTile.seHeight), ColorUtils.multiplyLightness3(textureColor, plainTile.nwHeight), ColorUtils.multiplyLightness3(textureColor, plainTile.swHeight));
                } else if (plainTile.isFlat) {
                    Rasterizer.fillTexturedTriangle(screenY3, screenY4, screenY2, screenX3, screenX4, screenX2, plainTile.seHeight, plainTile.nwHeight, plainTile.swHeight, projectedX1, relativeX2, projectedX4, projectedY1, height2, rotatedX, depth1, depth2, depth4, plainTile.texture);
                } else {
                    Rasterizer.fillTexturedTriangle(screenY3, screenY4, screenY2, screenX3, screenX4, screenX2, plainTile.seHeight, plainTile.nwHeight, plainTile.swHeight, projectedX3, projectedX4, relativeX2, height3, rotatedX, height2, relativeZ2, depth4, depth2, plainTile.texture);
                }
            }
        }
        if ((screenX1 - screenX2) * (screenY4 - screenY2) - (screenY1 - screenY2) * (screenX4 - screenX2) <= 0) {
            return;
        }
        if (MiniMenu.walkTargetActive && pointInTriangle(MiniMenu.targetX + Rasterizer.centerX, MiniMenu.targetZ + Rasterizer.centerY, screenY1, screenY2, screenY4, screenX1, screenX2, screenX4)) {
            MiniMenu.clickTileX = tileX;
            MiniMenu.clickTileZ = tileZ;
        }
        if (GlRenderer.enabled || skipRendering) {
            return;
        }
        Rasterizer.testX = false;
        if (screenX1 < 0 || screenX2 < 0 || screenX4 < 0 || screenX1 > Rasterizer.width || screenX2 > Rasterizer.width || screenX4 > Rasterizer.width) {
            Rasterizer.testX = true;
        }
        if (plainTile.texture == -1) {
            if (plainTile.neHeight != 12345678) {
                Rasterizer.fillGouraudTriangle(screenY1, screenY2, screenY4, screenX1, screenX2, screenX4, plainTile.neHeight, plainTile.swHeight, plainTile.nwHeight);
            }
        } else if (Preferences.manyGroundTextures) {
            Rasterizer.fillTexturedTriangle(screenY1, screenY2, screenY4, screenX1, screenX2, screenX4, plainTile.neHeight, plainTile.swHeight, plainTile.nwHeight, projectedX1, relativeX2, projectedX4, projectedY1, height2, rotatedX, depth1, depth2, depth4, plainTile.texture);
        } else {
            textureColor = Rasterizer.textureProvider.getAverageColor(plainTile.texture);
            Rasterizer.fillGouraudTriangle(screenY1, screenY2, screenY4, screenX1, screenX2, screenX4, ColorUtils.multiplyLightness3(textureColor, plainTile.neHeight), ColorUtils.multiplyLightness3(textureColor, plainTile.swHeight), ColorUtils.multiplyLightness3(textureColor, plainTile.nwHeight));
        }
    }

    @OriginalMember(owner = "client!al", name = "a", descriptor = "(III)Z")
    public static boolean isTileVisible(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        @Pc(7) int visibilityState = lightmapBuffers[plane][x][z];
        if (visibilityState == -renderDistance) {
            return false;
        } else if (visibilityState == renderDistance) {
            return true;
        } else {
            @Pc(22) int xFine = x << 7;
            @Pc(26) int zFine = z << 7;
            if (isInOccluderBounds(xFine + 1, tileHeights[plane][x][z], zFine + 1) && isInOccluderBounds(xFine + 128 - 1, tileHeights[plane][x + 1][z], zFine + 1) && isInOccluderBounds(xFine + 128 - 1, tileHeights[plane][x + 1][z + 1], zFine + 128 - 1) && isInOccluderBounds(xFine + 1, tileHeights[plane][x][z + 1], zFine + 128 - 1)) {
                lightmapBuffers[plane][x][z] = renderDistance;
                return true;
            } else {
                lightmapBuffers[plane][x][z] = -renderDistance;
                return false;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!mj", name = "a", descriptor = "(IIIII)Z")
    public static boolean isLineVisible(@OriginalArg(0) int x, @OriginalArg(1) int y1, @OriginalArg(2) int y2, @OriginalArg(3) int z, @OriginalArg(4) int maxDistance) {
        @Pc(9) int rotatedX = z * sinPitch + x * cosPitch >> 16;
        @Pc(19) int rotatedZ = z * cosPitch - x * sinPitch >> 16;
        @Pc(29) int depth1 = y1 * sinYaw + rotatedZ * cosYaw >> 16;
        @Pc(39) int transformedY1 = y1 * cosYaw - rotatedZ * sinYaw >> 16;
        if (depth1 < 1) {
            depth1 = 1;
        }
        @Pc(50) int screenX1 = (rotatedX << 9) / depth1;
        @Pc(56) int screenY1 = (transformedY1 << 9) / depth1;
        @Pc(66) int depth2 = y2 * sinYaw + rotatedZ * cosYaw >> 16;
        @Pc(76) int transformedY2 = y2 * cosYaw - rotatedZ * sinYaw >> 16;
        if (depth2 < 1) {
            depth2 = 1;
        }
        @Pc(87) int screenX2 = (rotatedX << 9) / depth2;
        @Pc(93) int screenY2 = (transformedY2 << 9) / depth2;
        if (depth1 < 50 && depth2 < 50) {
            return false;
        } else if (depth1 > maxDistance && depth2 > maxDistance) {
            return false;
        } else if (screenX1 < Rasterizer.screenLowerX && screenX2 < Rasterizer.screenLowerX) {
            return false;
        } else if (screenX1 > Rasterizer.screenUpperX && screenX2 > Rasterizer.screenUpperX) {
            return false;
        } else if (screenY1 < Rasterizer.screenLowerY && screenY2 < Rasterizer.screenLowerY) {
            return false;
        } else {
            return screenY1 <= Rasterizer.screenUpperY || screenY2 <= Rasterizer.screenUpperY;
        }
    }

    @OriginalMember(owner = "runetek4.client!kd", name = "a", descriptor = "([[F[[II[[FI[ILclient!fj;BLclient!gi;[[FI)V")
    public static void addLightVertices(@OriginalArg(0) float[][] lightIntensity, @OriginalArg(1) int[][] heightMap, @OriginalArg(2) int tileX, @OriginalArg(3) float[][] shadowValues, @OriginalArg(4) int tileZ, @OriginalArg(5) int[] shapeVertices, @OriginalArg(6) LightBuffer lightBuffer, @OriginalArg(8) Light light, @OriginalArg(9) float[][] ambientLight, @OriginalArg(10) int rotation) {
        @Pc(7) int[] vertices = new int[shapeVertices.length / 2];
        for (@Pc(13) int vertexIndex = 0; vertexIndex < vertices.length; vertexIndex++) {
            @Pc(27) int vertexX = shapeVertices[vertexIndex + vertexIndex];
            @Pc(35) int vertexZ = shapeVertices[vertexIndex + vertexIndex + 1];
            @Pc(53) int tempCoord;
            if (rotation == 1) {
                tempCoord = vertexX;
                vertexX = vertexZ;
                vertexZ = 128 - tempCoord;
            } else if (rotation == 2) {
                vertexZ = 128 - vertexZ;
                vertexX = 128 - vertexX;
            } else if (rotation == 3) {
                tempCoord = vertexX;
                vertexX = 128 - vertexZ;
                vertexZ = tempCoord;
            }
            @Pc(123) float shadowValue;
            @Pc(107) float lightValue;
            @Pc(115) float ambientValue;
            if (vertexX == 0 && vertexZ == 0) {
                shadowValue = shadowValues[tileX][tileZ];
                ambientValue = ambientLight[tileX][tileZ];
                lightValue = lightIntensity[tileX][tileZ];
            } else if (vertexX == 128 && vertexZ == 0) {
                lightValue = lightIntensity[tileX + 1][tileZ];
                ambientValue = ambientLight[tileX + 1][tileZ];
                shadowValue = shadowValues[tileX + 1][tileZ];
            } else if (vertexX == 128 && vertexZ == 128) {
                shadowValue = shadowValues[tileX + 1][tileZ + 1];
                ambientValue = ambientLight[tileX + 1][tileZ + 1];
                lightValue = lightIntensity[tileX + 1][tileZ + 1];
            } else if (vertexX == 0 && vertexZ == 128) {
                lightValue = lightIntensity[tileX][tileZ + 1];
                shadowValue = shadowValues[tileX][tileZ + 1];
                ambientValue = ambientLight[tileX][tileZ + 1];
            } else {
                ambientValue = ambientLight[tileX][tileZ];
                lightValue = lightIntensity[tileX][tileZ];
                @Pc(187) float xInterpolation = (float) vertexX / 128.0F;
                shadowValue = shadowValues[tileX][tileZ];
                @Pc(208) float interpolatedShadow1 = shadowValue + (shadowValues[tileX + 1][tileZ] - shadowValue) * xInterpolation;
                @Pc(222) float interpolatedLight1 = lightValue + xInterpolation * (lightIntensity[tileX + 1][tileZ] - lightValue);
                @Pc(237) float interpolatedAmbient1 = ambientValue + (ambientLight[tileX + 1][tileZ] - ambientValue) * xInterpolation;
                @Pc(245) float cornerAmbient = ambientLight[tileX][tileZ + 1];
                @Pc(261) float interpolatedAmbient2 = cornerAmbient + (ambientLight[tileX + 1][tileZ + 1] - cornerAmbient) * xInterpolation;
                @Pc(269) float cornerShadow = shadowValues[tileX][tileZ + 1];
                @Pc(274) float zInterpolation = (float) vertexZ / 128.0F;
                ambientValue = interpolatedAmbient1 + (interpolatedAmbient2 - interpolatedAmbient1) * zInterpolation;
                @Pc(291) float cornerLight = lightIntensity[tileX][tileZ + 1];
                @Pc(307) float interpolatedLight2 = cornerLight + (lightIntensity[tileX + 1][tileZ + 1] - cornerLight) * xInterpolation;
                @Pc(324) float interpolatedShadow2 = cornerShadow + (shadowValues[tileX + 1][tileZ + 1] - cornerShadow) * xInterpolation;
                shadowValue = interpolatedShadow1 + (interpolatedShadow2 - interpolatedShadow1) * zInterpolation;
                lightValue = interpolatedLight1 + (interpolatedLight2 - interpolatedLight1) * zInterpolation;
            }
            @Pc(393) int worldX = (tileX << 7) + vertexX;
            @Pc(400) int worldZ = (tileZ << 7) + vertexZ;
            @Pc(408) int worldY = interpolateHeight(vertexX, tileZ, heightMap, tileX, vertexZ);
            vertices[vertexIndex] = lightBuffer.method1553(light, worldX, worldY, worldZ, ambientValue, shadowValue, lightValue);
        }
        lightBuffer.method1557(vertices);
    }

    @OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "()V")
    public static void processOccluders() {
        lightSourceCount = 0;
        label194: for (@Pc(3) int occluderIndex = 0; occluderIndex < renderableCount; occluderIndex++) {
            @Pc(10) SceneOccluder occluder = renderables[occluderIndex];
            @Pc(14) int relativeCoord;
            if (viewDistances != null) {
                for (relativeCoord = 0; relativeCoord < viewDistances.length; relativeCoord++) {
                    if (viewDistances[relativeCoord] != -1000000 && (occluder.minY <= viewDistances[relativeCoord] || occluder.maxY <= viewDistances[relativeCoord]) && (occluder.worldX1 <= screenDepth[relativeCoord] || occluder.worldZ1 <= screenDepth[relativeCoord]) && (occluder.worldX1 >= projectionX[relativeCoord] || occluder.worldZ1 >= projectionX[relativeCoord]) && (occluder.worldX2 <= projectionY[relativeCoord] || occluder.worldZ2 <= projectionY[relativeCoord]) && (occluder.worldX2 >= projectionZ[relativeCoord] || occluder.worldZ2 >= projectionZ[relativeCoord])) {
                        continue label194;
                    }
                }
            }
            @Pc(115) int startRange;
            @Pc(126) int endRange;
            @Pc(158) int distance;
            @Pc(137) boolean isVisible;
            if (occluder.plane == 1) {
                relativeCoord = occluder.tileX1 + visibility - eyeTileX;
                if (relativeCoord >= 0 && relativeCoord <= visibility + visibility) {
                    startRange = occluder.tileX2 + visibility - eyeTileZ;
                    if (startRange < 0) {
                        startRange = 0;
                    }
                    endRange = occluder.tileZ2 + visibility - eyeTileZ;
                    if (endRange > visibility + visibility) {
                        endRange = visibility + visibility;
                    }
                    isVisible = false;
                    while (startRange <= endRange) {
                        if (tileVisibility[relativeCoord][startRange++]) {
                            isVisible = true;
                            break;
                        }
                    }
                    if (isVisible) {
                        distance = eyeX - occluder.worldX1;
                        if (distance > 32) {
                            occluder.anInt4462 = 1;
                        } else {
                            if (distance >= -32) {
                                continue;
                            }
                            occluder.anInt4462 = 2;
                            distance = -distance;
                        }
                        occluder.anInt4454 = (occluder.worldX2 - eyeZ << 8) / distance;
                        occluder.anInt4450 = (occluder.worldZ2 - eyeZ << 8) / distance;
                        occluder.anInt4459 = (occluder.minY - eyeY << 8) / distance;
                        occluder.anInt4463 = (occluder.maxY - eyeY << 8) / distance;
                        aSceneGraphClass120Array2[lightSourceCount++] = occluder;
                    }
                }
            } else if (occluder.plane == 2) {
                relativeCoord = occluder.tileX2 + visibility - eyeTileZ;
                if (relativeCoord >= 0 && relativeCoord <= visibility + visibility) {
                    startRange = occluder.tileX1 + visibility - eyeTileX;
                    if (startRange < 0) {
                        startRange = 0;
                    }
                    endRange = occluder.tileZ1 + visibility - eyeTileX;
                    if (endRange > visibility + visibility) {
                        endRange = visibility + visibility;
                    }
                    isVisible = false;
                    while (startRange <= endRange) {
                        if (tileVisibility[startRange++][relativeCoord]) {
                            isVisible = true;
                            break;
                        }
                    }
                    if (isVisible) {
                        distance = eyeZ - occluder.worldX2;
                        if (distance > 32) {
                            occluder.anInt4462 = 3;
                        } else {
                            if (distance >= -32) {
                                continue;
                            }
                            occluder.anInt4462 = 4;
                            distance = -distance;
                        }
                        occluder.anInt4448 = (occluder.worldX1 - eyeX << 8) / distance;
                        occluder.anInt4456 = (occluder.worldZ1 - eyeX << 8) / distance;
                        occluder.anInt4459 = (occluder.minY - eyeY << 8) / distance;
                        occluder.anInt4463 = (occluder.maxY - eyeY << 8) / distance;
                        aSceneGraphClass120Array2[lightSourceCount++] = occluder;
                    }
                }
            } else if (occluder.plane == 4) {
                relativeCoord = occluder.minY - eyeY;
                if (relativeCoord > 128) {
                    startRange = occluder.tileX2 + visibility - eyeTileZ;
                    if (startRange < 0) {
                        startRange = 0;
                    }
                    endRange = occluder.tileZ2 + visibility - eyeTileZ;
                    if (endRange > visibility + visibility) {
                        endRange = visibility + visibility;
                    }
                    if (startRange <= endRange) {
                        @Pc(408) int local408 = occluder.tileX1 + visibility - eyeTileX;
                        if (local408 < 0) {
                            local408 = 0;
                        }
                        distance = occluder.tileZ1 + visibility - eyeTileX;
                        if (distance > visibility + visibility) {
                            distance = visibility + visibility;
                        }
                        @Pc(430) boolean hasVisibleTile = false;
                        label166: for (@Pc(432) int checkX = local408; checkX <= distance; checkX++) {
                            for (@Pc(437) int checkZ = startRange; checkZ <= endRange; checkZ++) {
                                if (tileVisibility[checkX][checkZ]) {
                                    hasVisibleTile = true;
                                    break label166;
                                }
                            }
                        }
                        if (hasVisibleTile) {
                            occluder.anInt4462 = 5;
                            occluder.anInt4448 = (occluder.worldX1 - eyeX << 8) / relativeCoord;
                            occluder.anInt4456 = (occluder.worldZ1 - eyeX << 8) / relativeCoord;
                            occluder.anInt4454 = (occluder.worldX2 - eyeZ << 8) / relativeCoord;
                            occluder.anInt4450 = (occluder.worldZ2 - eyeZ << 8) / relativeCoord;
                            aSceneGraphClass120Array2[lightSourceCount++] = occluder;
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!bi", name = "a", descriptor = "(IIIIIIII)Z")
    public static boolean pointInTriangle(@OriginalArg(0) int pointX, @OriginalArg(1) int pointY, @OriginalArg(2) int vertex1Y, @OriginalArg(3) int vertex2Y, @OriginalArg(4) int vertex3Y, @OriginalArg(5) int vertex1X, @OriginalArg(6) int vertex2X, @OriginalArg(7) int vertex3X) {
        if (pointY < vertex1Y && pointY < vertex2Y && pointY < vertex3Y) {
            return false;
        } else if (pointY > vertex1Y && pointY > vertex2Y && pointY > vertex3Y) {
            return false;
        } else if (pointX < vertex1X && pointX < vertex2X && pointX < vertex3X) {
            return false;
        } else if (pointX > vertex1X && pointX > vertex2X && pointX > vertex3X) {
            return false;
        } else {
            @Pc(59) int crossProduct1 = (pointY - vertex1Y) * (vertex2X - vertex1X) - (pointX - vertex1X) * (vertex2Y - vertex1Y);
            @Pc(75) int crossProduct2 = (pointY - vertex3Y) * (vertex1X - vertex3X) - (pointX - vertex3X) * (vertex1Y - vertex3Y);
            @Pc(91) int crossProduct3 = (pointY - vertex2Y) * (vertex3X - vertex2X) - (pointX - vertex2X) * (vertex3Y - vertex2Y);
            return crossProduct1 * crossProduct3 > 0 && crossProduct3 * crossProduct2 > 0;
        }
    }

    @OriginalMember(owner = "runetek4.client!lh", name = "a", descriptor = "(Lclient!fg;IIIIIIZ)V")
    public static void drawTileOverlay(@OriginalArg(0) ShapedTile overlay, @OriginalArg(1) int sinYaw, @OriginalArg(2) int cosYaw, @OriginalArg(3) int sinPitch, @OriginalArg(4) int cosPitch, @OriginalArg(5) int tileX, @OriginalArg(6) int tileZ, @OriginalArg(7) boolean skipRendering) {
        @Pc(3) int numVertices = overlay.vertexX.length;
        @Pc(5) int index;
        @Pc(15) int vertexA;
        @Pc(22) int relativeY;
        @Pc(29) int local29;
        @Pc(39) int local39;
        for (index = 0; index < numVertices; index++) {
            vertexA = overlay.vertexX[index] - eyeX;
            relativeY = overlay.vertexY[index] - eyeY;
            local29 = overlay.vertexZ[index] - eyeZ;
            local39 = local29 * sinPitch + vertexA * cosPitch >> 16;
            @Pc(49) int rotatedZ = local29 * cosPitch - vertexA * sinPitch >> 16;
            @Pc(61) int transformedY = relativeY * cosYaw - rotatedZ * sinYaw >> 16;
            @Pc(71) int depth = relativeY * sinYaw + rotatedZ * cosYaw >> 16;
            if (depth < 50) {
                return;
            }
            if (overlay.triangleTextureIds != null) {
                tmpViewspaceX[index] = local39;
                tmpLocalY[index] = transformedY;
                tmpViewspaceZ[index] = depth;
            }
            tmpLocalX[index] = Rasterizer.centerX + (local39 << 9) / depth;
            tmpViewspaceY[index] = Rasterizer.centerY + (transformedY << 9) / depth;
        }
        Rasterizer.alpha = 0;
        numVertices = overlay.triangleA.length;
        for (index = 0; index < numVertices; index++) {
            vertexA = overlay.triangleA[index];
            relativeY = overlay.triangleB[index];
            local29 = overlay.triangleC[index];
            local39 = tmpLocalX[vertexA];
            @Pc(148) int screenX2 = tmpLocalX[relativeY];
            @Pc(152) int screenX3 = tmpLocalX[local29];
            @Pc(156) int screenY1 = tmpViewspaceY[vertexA];
            @Pc(160) int screenY2 = tmpViewspaceY[relativeY];
            @Pc(164) int screenY3 = tmpViewspaceY[local29];
            if ((local39 - screenX2) * (screenY3 - screenY2) - (screenY1 - screenY2) * (screenX3 - screenX2) > 0) {
                if (MiniMenu.walkTargetActive && pointInTriangle(MiniMenu.targetX + Rasterizer.centerX, MiniMenu.targetZ + Rasterizer.centerY, screenY1, screenY2, screenY3, local39, screenX2, screenX3)) {
                    MiniMenu.clickTileX = tileX;
                    MiniMenu.clickTileZ = tileZ;
                }
                if (!GlRenderer.enabled && !skipRendering) {
                    Rasterizer.testX = false;
                    if (local39 < 0 || screenX2 < 0 || screenX3 < 0 || local39 > Rasterizer.width || screenX2 > Rasterizer.width || screenX3 > Rasterizer.width) {
                        Rasterizer.testX = true;
                    }
                    if (overlay.triangleTextureIds == null || overlay.triangleTextureIds[index] == -1) {
                        if (overlay.triangleHSLA[index] != 12345678) {
                            Rasterizer.fillGouraudTriangle(screenY1, screenY2, screenY3, local39, screenX2, screenX3, overlay.triangleHSLA[index], overlay.triangleHSLB[index], overlay.triangleHSLC[index]);
                        }
                    } else if (!Preferences.manyGroundTextures) {
                        @Pc(373) int textureColor = Rasterizer.textureProvider.getAverageColor(overlay.triangleTextureIds[index]);
                        Rasterizer.fillGouraudTriangle(screenY1, screenY2, screenY3, local39, screenX2, screenX3, ColorUtils.multiplyLightness3(textureColor, overlay.triangleHSLA[index]), ColorUtils.multiplyLightness3(textureColor, overlay.triangleHSLB[index]), ColorUtils.multiplyLightness3(textureColor, overlay.triangleHSLC[index]));
                    } else if (overlay.isFlat) {
                        Rasterizer.fillTexturedTriangle(screenY1, screenY2, screenY3, local39, screenX2, screenX3, overlay.triangleHSLA[index], overlay.triangleHSLB[index], overlay.triangleHSLC[index], tmpViewspaceX[0], tmpViewspaceX[1], tmpViewspaceX[3], tmpLocalY[0], tmpLocalY[1], tmpLocalY[3], tmpViewspaceZ[0], tmpViewspaceZ[1], tmpViewspaceZ[3], overlay.triangleTextureIds[index]);
                    } else {
                        Rasterizer.fillTexturedTriangle(screenY1, screenY2, screenY3, local39, screenX2, screenX3, overlay.triangleHSLA[index], overlay.triangleHSLB[index], overlay.triangleHSLC[index], tmpViewspaceX[vertexA], tmpViewspaceX[relativeY], tmpViewspaceX[local29], tmpLocalY[vertexA], tmpLocalY[relativeY], tmpLocalY[local29], tmpViewspaceZ[vertexA], tmpViewspaceZ[relativeY], tmpViewspaceZ[local29], overlay.triangleTextureIds[index]);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!a", name = "a", descriptor = "([[F[[F[[II[[F[[B[[II[[B[[B[[B[[[B)[Lclient!hg;")
    public static GlTile[] buildUnderwaterGlTiles(@OriginalArg(0) float[][] lightIntensity, @OriginalArg(1) float[][] shadowValues, @OriginalArg(2) int[][] heightMap, @OriginalArg(3) int plane, @OriginalArg(4) float[][] ambientLight, @OriginalArg(5) byte[][] underlayRotations, @OriginalArg(6) int[][] colorMap, @OriginalArg(8) byte[][] overlayShapes, @OriginalArg(9) byte[][] underlayIds, @OriginalArg(10) byte[][] overlayIds, @OriginalArg(11) byte[][][] renderFlags) {
        @Pc(10) IterableHashTable tileMap = new IterableHashTable(128);
        @Pc(12) int x;
        @Pc(17) int z;
        @Pc(30) int local30;
        @Pc(38) int overlayId;
        @Pc(131) int southNeighborShape;
        @Pc(168) int eastNeighborShape;
        @Pc(143) int northNeighborShape;
        @Pc(163) int northOverlayId;
        @Pc(153) int westOverlayId;
        @Pc(190) int eastOverlayId;
        @Pc(180) int northeastOverlayId;
        @Pc(214) int northwestOverlayId;
        @Pc(202) int southwestOverlayId;
        @Pc(226) int southeastOverlayId;
        @Pc(274) byte neighborShape;
        @Pc(299) int shapeIndex2;
        @Pc(317) int shapeIndex3;
        @Pc(127) int neighborCount;
        @Pc(133) int westNeighborShape;
        @Pc(777) int blendIndex;
        @Pc(1035) int blendNeighborIndex;
        @Pc(1055) boolean[] westEdgeShape;
        @Pc(1068) boolean[] southEdgeShape;
        @Pc(1086) boolean[] eastEdgeShape;
        for (x = 1; x <= 102; x++) {
            for (z = 1; z <= 102; z++) {
                local30 = underlayIds[x][z] & 0xFF;
                overlayId = overlayIds[x][z] & 0xFF;
                if (overlayId != 0) {
                    @Pc(50) FloorOverlayType floorOverlayType = FloorOverlayTypeList.method4395(overlayId - 1);
                    if (floorOverlayType.baseColor == -1) {
                        continue;
                    }
                    @Pc(61) GlTile glTile = getOrCreateGlTile(tileMap, floorOverlayType);
                    @Pc(67) byte overlayShape = overlayShapes[x][z];
                    @Pc(71) int[] shapeVertices = SHAPE_OVERLAY_COORDINATES[overlayShape];
                    glTile.anInt2482 += shapeVertices.length / 2;
                    glTile.anInt2484++;
                    if (floorOverlayType.blend && local30 != 0) {
                        glTile.anInt2482 += SHAPE_WALL_CORNER_COUNT[overlayShape];
                    }
                }
                if ((underlayIds[x][z] & 0xFF) != 0 || overlayId != 0 && overlayShapes[x][z] == 0) {
                    neighborCount = 0;
                    @Pc(129) int local129 = 0;
                    southNeighborShape = 0;
                    westNeighborShape = 0;
                    northNeighborShape = overlayIds[x][z + 1] & 0xFF;
                    westOverlayId = overlayIds[x][z - 1] & 0xFF;
                    northOverlayId = overlayIds[x - 1][z] & 0xFF;
                    @Pc(166) int[] local166 = new int[8];
                    eastNeighborShape = 0;
                    northeastOverlayId = overlayIds[x - 1][z + 1] & 0xFF;
                    eastOverlayId = overlayIds[x + 1][z] & 0xFF;
                    southwestOverlayId = overlayIds[x + 1][z - 1] & 0xFF;
                    northwestOverlayId = overlayIds[x - 1][z - 1] & 0xFF;
                    southeastOverlayId = overlayIds[x + 1][z + 1] & 0xFF;
                    @Pc(242) FloorOverlayType neighborFloorOverlayType;
                    @Pc(264) byte neighborRotation;
                    @Pc(287) int shapeIndex1;
                    if (northeastOverlayId != 0 && overlayId != northeastOverlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(northeastOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            neighborRotation = underlayRotations[x - 1][z + 1];
                            neighborShape = overlayShapes[x - 1][z + 1];
                            shapeIndex1 = SHAPE_WALL_VERTEX_DATA[neighborShape * 4 + (neighborRotation + 2 & 0x3)];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[(neighborRotation + 3 & 0x3) + neighborShape * 4];
                            if (!SHAPE_VISIBLE_FLAGS[shapeIndex2][1] || !SHAPE_VISIBLE_FLAGS[shapeIndex1][0]) {
                                for (shapeIndex3 = 0; shapeIndex3 < 8; shapeIndex3++) {
                                    if (shapeIndex3 == 0) {
                                        neighborCount++;
                                        local166[0] = northeastOverlayId;
                                        break;
                                    }
                                    if (northeastOverlayId == local166[shapeIndex3]) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (northwestOverlayId != 0 && northwestOverlayId != overlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(northwestOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            neighborRotation = underlayRotations[x - 1][z - 1];
                            neighborShape = overlayShapes[x - 1][z - 1];
                            shapeIndex1 = SHAPE_WALL_VERTEX_DATA[neighborShape * 4 + (neighborRotation & 0x3)];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[(neighborRotation + 3 & 0x3) + neighborShape * 4];
                            if (!SHAPE_VISIBLE_FLAGS[shapeIndex1][1] || !SHAPE_VISIBLE_FLAGS[shapeIndex2][0]) {
                                for (shapeIndex3 = 0; shapeIndex3 < 8; shapeIndex3++) {
                                    if (neighborCount == shapeIndex3) {
                                        local166[neighborCount++] = northwestOverlayId;
                                        break;
                                    }
                                    if (local166[shapeIndex3] == northwestOverlayId) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (southwestOverlayId != 0 && overlayId != southwestOverlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(southwestOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            neighborRotation = underlayRotations[x + 1][z - 1];
                            neighborShape = overlayShapes[x + 1][z - 1];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[neighborShape * 4 + (neighborRotation + 1 & 0x3)];
                            shapeIndex1 = SHAPE_WALL_VERTEX_DATA[neighborShape * 4 + (neighborRotation & 0x3)];
                            if (!SHAPE_VISIBLE_FLAGS[shapeIndex2][1] || !SHAPE_VISIBLE_FLAGS[shapeIndex1][0]) {
                                for (shapeIndex3 = 0; shapeIndex3 < 8; shapeIndex3++) {
                                    if (neighborCount == shapeIndex3) {
                                        local166[neighborCount++] = southwestOverlayId;
                                        break;
                                    }
                                    if (southwestOverlayId == local166[shapeIndex3]) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (southeastOverlayId != 0 && southeastOverlayId != overlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(southeastOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            neighborRotation = underlayRotations[x + 1][z + 1];
                            neighborShape = overlayShapes[x + 1][z + 1];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[neighborShape * 4 + (neighborRotation + 1 & 0x3)];
                            shapeIndex1 = SHAPE_WALL_VERTEX_DATA[neighborShape * 4 + (neighborRotation + 2 & 0x3)];
                            if (!SHAPE_VISIBLE_FLAGS[shapeIndex1][1] || !SHAPE_VISIBLE_FLAGS[shapeIndex2][0]) {
                                for (shapeIndex3 = 0; shapeIndex3 < 8; shapeIndex3++) {
                                    if (neighborCount == shapeIndex3) {
                                        local166[neighborCount++] = southeastOverlayId;
                                        break;
                                    }
                                    if (southeastOverlayId == local166[shapeIndex3]) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if (northNeighborShape != 0 && overlayId != northNeighborShape) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(northNeighborShape - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            local129 = SHAPE_WALL_VERTEX_DATA[overlayShapes[x][z + 1] * 4 + (underlayRotations[x][z + 1] + 2 & 0x3)];
                            for (blendIndex = 0; blendIndex < 8; blendIndex++) {
                                if (neighborCount == blendIndex) {
                                    local166[neighborCount++] = northNeighborShape;
                                    break;
                                }
                                if (local166[blendIndex] == northNeighborShape) {
                                    break;
                                }
                            }
                        }
                    }
                    if (northOverlayId != 0 && overlayId != northOverlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(northOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            westNeighborShape = SHAPE_WALL_VERTEX_DATA[(underlayRotations[x - 1][z] + 3 & 0x3) + overlayShapes[x - 1][z] * 4];
                            for (blendIndex = 0; blendIndex < 8; blendIndex++) {
                                if (neighborCount == blendIndex) {
                                    local166[neighborCount++] = northOverlayId;
                                    break;
                                }
                                if (northOverlayId == local166[blendIndex]) {
                                    break;
                                }
                            }
                        }
                    }
                    if (westOverlayId != 0 && overlayId != westOverlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(westOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            southNeighborShape = SHAPE_WALL_VERTEX_DATA[(underlayRotations[x][z - 1] & 0x3) + overlayShapes[x][z - 1] * 4];
                            for (blendIndex = 0; blendIndex < 8; blendIndex++) {
                                if (blendIndex == neighborCount) {
                                    local166[neighborCount++] = westOverlayId;
                                    break;
                                }
                                if (westOverlayId == local166[blendIndex]) {
                                    break;
                                }
                            }
                        }
                    }
                    if (eastOverlayId != 0 && overlayId != eastOverlayId) {
                        neighborFloorOverlayType = FloorOverlayTypeList.method4395(eastOverlayId - 1);
                        if (neighborFloorOverlayType.blend && neighborFloorOverlayType.baseColor != -1) {
                            eastNeighborShape = SHAPE_WALL_VERTEX_DATA[(underlayRotations[x + 1][z] + 1 & 0x3) + overlayShapes[x + 1][z] * 4];
                            for (blendIndex = 0; blendIndex < 8; blendIndex++) {
                                if (neighborCount == blendIndex) {
                                    local166[neighborCount++] = eastOverlayId;
                                    break;
                                }
                                if (local166[blendIndex] == eastOverlayId) {
                                    break;
                                }
                            }
                        }
                    }
                    for (blendNeighborIndex = 0; blendNeighborIndex < neighborCount; blendNeighborIndex++) {
                        blendIndex = local166[blendNeighborIndex];
                        westEdgeShape = SHAPE_VISIBLE_FLAGS[northOverlayId == blendIndex ? westNeighborShape : 0];
                        southEdgeShape = SHAPE_VISIBLE_FLAGS[blendIndex == westOverlayId ? southNeighborShape : 0];
                        @Pc(1077) boolean[] local1077 = SHAPE_VISIBLE_FLAGS[northNeighborShape == blendIndex ? local129 : 0];
                        eastEdgeShape = SHAPE_VISIBLE_FLAGS[eastOverlayId == blendIndex ? eastNeighborShape : 0];
                        @Pc(1092) FloorOverlayType local1092 = FloorOverlayTypeList.method4395(blendIndex - 1);
                        @Pc(1097) GlTile local1097 = getOrCreateGlTile(tileMap, local1092);
                        local1097.anInt2482 += 5;
                        local1097.anInt2482 += local1077.length - 2;
                        local1097.anInt2482 += westEdgeShape.length - 2;
                        local1097.anInt2482 += southEdgeShape.length - 2;
                        local1097.anInt2482 += eastEdgeShape.length - 2;
                        local1097.anInt2484++;
                    }
                }
            }
        }
        @Pc(1161) GlTile currentTile;
        for (currentTile = (GlTile) tileMap.head(); currentTile != null; currentTile = (GlTile) tileMap.next()) {
            currentTile.method1940();
        }
        for (x = 1; x <= 102; x++) {
            for (z = 1; z <= 102; z++) {
                overlayId = underlayIds[x][z] & 0xFF;
                neighborCount = overlayIds[x][z] & 0xFF;
                if ((renderFlags[plane][x][z] & 0x8) != 0) {
                    local30 = 0;
                } else if ((renderFlags[1][x][z] & 0x2) == 2 && plane > 0) {
                    local30 = plane - 1;
                } else {
                    local30 = plane;
                }
                if (neighborCount != 0) {
                    @Pc(1250) FloorOverlayType local1250 = FloorOverlayTypeList.method4395(neighborCount - 1);
                    if (local1250.baseColor == -1) {
                        continue;
                    }
                    @Pc(1261) GlTile local1261 = getOrCreateGlTile(tileMap, local1250);
                    @Pc(1267) byte local1267 = overlayShapes[x][z];
                    @Pc(1273) byte local1273 = underlayRotations[x][z];
                    eastNeighborShape = calculateTexturedColor(local1250.texture, local1250.baseColor, colorMap[x][z]);
                    northNeighborShape = calculateTexturedColor(local1250.texture, local1250.baseColor, colorMap[x + 1][z]);
                    northOverlayId = calculateTexturedColor(local1250.texture, local1250.baseColor, colorMap[x + 1][z + 1]);
                    westOverlayId = calculateTexturedColor(local1250.texture, local1250.baseColor, colorMap[x][z + 1]);
                    addOverlayVertices(eastNeighborShape, heightMap, shadowValues, x, lightIntensity, northNeighborShape, local1273, local30, northOverlayId, overlayId != 0 && local1250.blend, local1267, z, ambientLight, westOverlayId, local1261);
                }
                if ((underlayIds[x][z] & 0xFF) != 0 || neighborCount != 0 && overlayShapes[x][z] == 0) {
                    @Pc(1382) int[] blendNeighbors = new int[8];
                    westNeighborShape = 0;
                    @Pc(1386) int blendNeighborCount = 0;
                    southNeighborShape = 0;
                    eastNeighborShape = 0;
                    northOverlayId = overlayIds[x][z + 1] & 0xFF;
                    northNeighborShape = 0;
                    westOverlayId = overlayIds[x - 1][z] & 0xFF;
                    northeastOverlayId = overlayIds[x + 1][z] & 0xFF;
                    eastOverlayId = overlayIds[x][z - 1] & 0xFF;
                    northwestOverlayId = overlayIds[x - 1][z + 1] & 0xFF;
                    southwestOverlayId = overlayIds[x - 1][z - 1] & 0xFF;
                    southeastOverlayId = overlayIds[x + 1][z - 1] & 0xFF;
                    blendNeighborIndex = overlayIds[x + 1][z + 1] & 0xFF;
                    @Pc(1527) byte local1527;
                    @Pc(1496) FloorOverlayType local1496;
                    @Pc(1571) int local1571;
                    if (northwestOverlayId == 0 || northwestOverlayId == neighborCount) {
                        northwestOverlayId = 0;
                    } else {
                        local1496 = FloorOverlayTypeList.method4395(northwestOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            neighborShape = underlayRotations[x - 1][z + 1];
                            local1527 = overlayShapes[x - 1][z + 1];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[local1527 * 4 + (neighborShape + 2 & 0x3)];
                            shapeIndex3 = SHAPE_WALL_VERTEX_DATA[local1527 * 4 + (neighborShape + 3 & 0x3)];
                            if (SHAPE_VISIBLE_FLAGS[shapeIndex3][1] && SHAPE_VISIBLE_FLAGS[shapeIndex2][0]) {
                                northwestOverlayId = 0;
                            } else {
                                for (local1571 = 0; local1571 < 8; local1571++) {
                                    if (local1571 == 0) {
                                        blendNeighborCount++;
                                        blendNeighbors[0] = northwestOverlayId;
                                        break;
                                    }
                                    if (blendNeighbors[local1571] == northwestOverlayId) {
                                        break;
                                    }
                                }
                            }
                        } else {
                            northwestOverlayId = 0;
                        }
                    }
                    if (southwestOverlayId == 0 || neighborCount == southwestOverlayId) {
                        southwestOverlayId = 0;
                    } else {
                        local1496 = FloorOverlayTypeList.method4395(southwestOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            neighborShape = underlayRotations[x - 1][z - 1];
                            local1527 = overlayShapes[x - 1][z - 1];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[(neighborShape & 0x3) + local1527 * 4];
                            shapeIndex3 = SHAPE_WALL_VERTEX_DATA[(neighborShape + 3 & 0x3) + local1527 * 4];
                            if (SHAPE_VISIBLE_FLAGS[shapeIndex2][1] && SHAPE_VISIBLE_FLAGS[shapeIndex3][0]) {
                                southwestOverlayId = 0;
                            } else {
                                for (local1571 = 0; local1571 < 8; local1571++) {
                                    if (local1571 == blendNeighborCount) {
                                        blendNeighbors[blendNeighborCount++] = southwestOverlayId;
                                        break;
                                    }
                                    if (southwestOverlayId == blendNeighbors[local1571]) {
                                        break;
                                    }
                                }
                            }
                        } else {
                            southwestOverlayId = 0;
                        }
                    }
                    if (southeastOverlayId == 0 || southeastOverlayId == neighborCount) {
                        southeastOverlayId = 0;
                    } else {
                        local1496 = FloorOverlayTypeList.method4395(southeastOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            neighborShape = underlayRotations[x + 1][z - 1];
                            local1527 = overlayShapes[x + 1][z - 1];
                            shapeIndex3 = SHAPE_WALL_VERTEX_DATA[(neighborShape + 1 & 0x3) + local1527 * 4];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[local1527 * 4 + (neighborShape & 0x3)];
                            if (SHAPE_VISIBLE_FLAGS[shapeIndex3][1] && SHAPE_VISIBLE_FLAGS[shapeIndex2][0]) {
                                southeastOverlayId = 0;
                            } else {
                                for (local1571 = 0; local1571 < 8; local1571++) {
                                    if (local1571 == blendNeighborCount) {
                                        blendNeighbors[blendNeighborCount++] = southeastOverlayId;
                                        break;
                                    }
                                    if (blendNeighbors[local1571] == southeastOverlayId) {
                                        break;
                                    }
                                }
                            }
                        } else {
                            southeastOverlayId = 0;
                        }
                    }
                    if (blendNeighborIndex == 0 || neighborCount == blendNeighborIndex) {
                        blendNeighborIndex = 0;
                    } else {
                        local1496 = FloorOverlayTypeList.method4395(blendNeighborIndex - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            local1527 = overlayShapes[x + 1][z + 1];
                            neighborShape = underlayRotations[x + 1][z + 1];
                            shapeIndex2 = SHAPE_WALL_VERTEX_DATA[(neighborShape + 2 & 0x3) + local1527 * 4];
                            shapeIndex3 = SHAPE_WALL_VERTEX_DATA[(neighborShape + 1 & 0x3) + local1527 * 4];
                            if (SHAPE_VISIBLE_FLAGS[shapeIndex2][1] && SHAPE_VISIBLE_FLAGS[shapeIndex3][0]) {
                                blendNeighborIndex = 0;
                            } else {
                                for (local1571 = 0; local1571 < 8; local1571++) {
                                    if (local1571 == blendNeighborCount) {
                                        blendNeighbors[blendNeighborCount++] = blendNeighborIndex;
                                        break;
                                    }
                                    if (blendNeighbors[local1571] == blendNeighborIndex) {
                                        break;
                                    }
                                }
                            }
                        } else {
                            blendNeighborIndex = 0;
                        }
                    }
                    @Pc(2003) int local2003;
                    if (northOverlayId != 0 && northOverlayId != neighborCount) {
                        local1496 = FloorOverlayTypeList.method4395(northOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            westNeighborShape = SHAPE_WALL_VERTEX_DATA[overlayShapes[x][z + 1] * 4 + (underlayRotations[x][z + 1] + 2 & 0x3)];
                            for (local2003 = 0; local2003 < 8; local2003++) {
                                if (local2003 == blendNeighborCount) {
                                    blendNeighbors[blendNeighborCount++] = northOverlayId;
                                    break;
                                }
                                if (blendNeighbors[local2003] == northOverlayId) {
                                    break;
                                }
                            }
                        }
                    }
                    if (westOverlayId != 0 && neighborCount != westOverlayId) {
                        local1496 = FloorOverlayTypeList.method4395(westOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            southNeighborShape = SHAPE_WALL_VERTEX_DATA[(underlayRotations[x - 1][z] + 3 & 0x3) + overlayShapes[x - 1][z] * 4];
                            for (local2003 = 0; local2003 < 8; local2003++) {
                                if (blendNeighborCount == local2003) {
                                    blendNeighbors[blendNeighborCount++] = westOverlayId;
                                    break;
                                }
                                if (westOverlayId == blendNeighbors[local2003]) {
                                    break;
                                }
                            }
                        }
                    }
                    if (eastOverlayId != 0 && eastOverlayId != neighborCount) {
                        local1496 = FloorOverlayTypeList.method4395(eastOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            eastNeighborShape = SHAPE_WALL_VERTEX_DATA[(underlayRotations[x][z - 1] & 0x3) + overlayShapes[x][z - 1] * 4];
                            for (local2003 = 0; local2003 < 8; local2003++) {
                                if (blendNeighborCount == local2003) {
                                    blendNeighbors[blendNeighborCount++] = eastOverlayId;
                                    break;
                                }
                                if (eastOverlayId == blendNeighbors[local2003]) {
                                    break;
                                }
                            }
                        }
                    }
                    if (northeastOverlayId != 0 && northeastOverlayId != neighborCount) {
                        local1496 = FloorOverlayTypeList.method4395(northeastOverlayId - 1);
                        if (local1496.blend && local1496.baseColor != -1) {
                            northNeighborShape = SHAPE_WALL_VERTEX_DATA[overlayShapes[x + 1][z] * 4 + (underlayRotations[x + 1][z] + 1 & 0x3)];
                            for (local2003 = 0; local2003 < 8; local2003++) {
                                if (local2003 == blendNeighborCount) {
                                    blendNeighbors[blendNeighborCount++] = northeastOverlayId;
                                    break;
                                }
                                if (blendNeighbors[local2003] == northeastOverlayId) {
                                    break;
                                }
                            }
                        }
                    }
                    for (blendIndex = 0; blendIndex < blendNeighborCount; blendIndex++) {
                        local2003 = blendNeighbors[blendIndex];
                        westEdgeShape = SHAPE_VISIBLE_FLAGS[local2003 == northOverlayId ? westNeighborShape : 0];
                        southEdgeShape = SHAPE_VISIBLE_FLAGS[westOverlayId == local2003 ? southNeighborShape : 0];
                        eastEdgeShape = SHAPE_VISIBLE_FLAGS[local2003 == eastOverlayId ? eastNeighborShape : 0];
                        @Pc(2318) boolean[] local2318 = SHAPE_VISIBLE_FLAGS[local2003 == northeastOverlayId ? northNeighborShape : 0];
                        @Pc(2324) FloorOverlayType local2324 = FloorOverlayTypeList.method4395(local2003 - 1);
                        @Pc(2329) GlTile local2329 = getOrCreateGlTile(tileMap, local2324);
                        @Pc(2345) int local2345 = calculateTexturedColor(local2324.texture, local2324.baseColor, colorMap[x][z]) << 8 | 0xFF;
                        @Pc(2365) int local2365 = calculateTexturedColor(local2324.texture, local2324.baseColor, colorMap[x + 1][z]) << 8 | 0xFF;
                        @Pc(2385) int local2385 = calculateTexturedColor(local2324.texture, local2324.baseColor, colorMap[x + 1][z + 1]) << 8 | 0xFF;
                        @Pc(2403) int local2403 = calculateTexturedColor(local2324.texture, local2324.baseColor, colorMap[x][z + 1]) << 8 | 0xFF;
                        @Pc(2422) boolean local2422 = local2003 != southwestOverlayId && eastEdgeShape[0] && southEdgeShape[1];
                        @Pc(2441) boolean local2441 = local2003 != blendNeighborIndex && westEdgeShape[0] && local2318[1];
                        @Pc(2456) boolean local2456 = northwestOverlayId != local2003 && southEdgeShape[0] && westEdgeShape[1];
                        @Pc(2463) int local2463 = westEdgeShape.length + 6 - 2;
                        @Pc(2482) boolean local2482 = local2003 != southeastOverlayId && local2318[0] && eastEdgeShape[1];
                        @Pc(2489) int local2489 = local2463 + southEdgeShape.length - 2;
                        @Pc(2496) int local2496 = local2489 + eastEdgeShape.length - 2;
                        @Pc(2503) int local2503 = local2496 + local2318.length - 2;
                        @Pc(2524) int local2524 = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, true, local2329, shadowValues, z, 64, lightIntensity, 64);
                        @Pc(2527) int[] local2527 = new int[local2503];
                        @Pc(2529) byte local2529 = 0;
                        @Pc(2550) int local2550 = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, local2456, local2329, shadowValues, z, 0, lightIntensity, 128);
                        @Pc(2571) int local2571 = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, local2441, local2329, shadowValues, z, 128, lightIntensity, 128);
                        @Pc(2592) int local2592 = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, local2422, local2329, shadowValues, z, 0, lightIntensity, 0);
                        @Pc(2613) int local2613 = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, local2482, local2329, shadowValues, z, 128, lightIntensity, 0);
                        @Pc(2616) int local2616 = local2529 + 1;
                        local2527[0] = local2524;
                        @Pc(2621) int local2621 = local2616 + 1;
                        local2527[1] = local2571;
                        if (westEdgeShape.length > 2) {
                            local2621++;
                            local2527[2] = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, westEdgeShape[2], local2329, shadowValues, z, 64, lightIntensity, 128);
                        }
                        local2527[local2621++] = local2550;
                        if (southEdgeShape.length > 2) {
                            local2527[local2621++] = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, southEdgeShape[2], local2329, shadowValues, z, 0, lightIntensity, 64);
                        }
                        local2527[local2621++] = local2592;
                        if (eastEdgeShape.length > 2) {
                            local2527[local2621++] = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, eastEdgeShape[2], local2329, shadowValues, z, 64, lightIntensity, 0);
                        }
                        local2527[local2621++] = local2613;
                        if (local2318.length > 2) {
                            local2527[local2621++] = createVertex(local2403, 0.0F, local2345, local2365, null, heightMap, x, ambientLight, local2385, 0, local2318[2], local2329, shadowValues, z, 128, lightIntensity, 64);
                        }
                        local2527[local2621++] = local2571;
                        local2329.method1945(local30, x, z, local2527, null, true);
                    }
                }
            }
        }
        for (currentTile = (GlTile) tileMap.head(); currentTile != null; currentTile = (GlTile) tileMap.next()) {
            if (currentTile.anInt2483 == 0) {
                currentTile.unlink();
            } else {
                currentTile.method1943();
            }
        }
        x = tileMap.length();
        @Pc(2823) GlTile[] resultTiles = new GlTile[x];
        @Pc(2826) long[] sortKeys = new long[x];
        tileMap.addNodes(resultTiles);
        for (overlayId = 0; overlayId < x; overlayId++) {
            sortKeys[overlayId] = resultTiles[overlayId].key;
        }
        ArrayUtils.sort(sortKeys, resultTiles);
        return resultTiles;
    }

    @OriginalMember(owner = "runetek4.client!tk", name = "a", descriptor = "(Lclient!sc;ZLclient!wl;)Lclient!hg;")
    public static GlTile getOrCreateGlTile(@OriginalArg(0) IterableHashTable tileMap, @OriginalArg(2) FloorOverlayType floorOverlayType) {
        @Pc(23) long tileKey = (long) ((floorOverlayType.texture + 1 << 16) + floorOverlayType.materialScale) + ((long) floorOverlayType.textureBrightness << 56) + ((long) floorOverlayType.waterColor << 32);
        @Pc(38) GlTile glTile = (GlTile) tileMap.get(tileKey);
        if (glTile == null) {
            glTile = new GlTile(floorOverlayType.texture, (float) floorOverlayType.materialScale, true, false, floorOverlayType.waterColor);
            tileMap.put(glTile, tileKey);
        }
        return glTile;
    }

    @OriginalMember(owner = "client!bi", name = "a", descriptor = "(IIBI)I")
    public static int calculateTexturedColor(@OriginalArg(0) int materialId, @OriginalArg(1) int baseColor, @OriginalArg(3) int lightness) {
        @Pc(19) int finalColor = Rasterizer.palette[ColorUtils.multiplyLightness2(baseColor, lightness)];
        if (materialId > 0) {
            @Pc(31) int blendFactor = Rasterizer.textureProvider.method3238(materialId & 0xFFFF);
            @Pc(49) int local49;
            @Pc(73) int local73;
            if (blendFactor != 0) {
                if (lightness < 0) {
                    local49 = 0;
                } else if (lightness > 127) {
                    local49 = 16777215;
                } else {
                    local49 = lightness * 131586;
                }
                if (blendFactor == 256) {
                    finalColor = local49;
                } else {
                    local73 = 256 - blendFactor;
                    finalColor = ((local49 & 0xFF00) * blendFactor + local73 * (finalColor & 0xFF00) & 0xFF0000) + (blendFactor * (local49 & 0xFF00FF) + ((finalColor & 0xFF00FF) * local73) & 0xFF00FF00) >> 8;
                }
            }
            local49 = Rasterizer.textureProvider.method3229(materialId & 0xFFFF);
            if (local49 != 0) {
                local49 += 256;
                @Pc(125) int local125 = (finalColor >> 16 & 0xFF) * local49;
                if (local125 > 65535) {
                    local125 = 65535;
                }
                local73 = (finalColor >> 8 & 0xFF) * local49;
                if (local73 > 65535) {
                    local73 = 65535;
                }
                @Pc(150) int local150 = local49 * (finalColor & 0xFF);
                if (local150 > 65535) {
                    local150 = 65535;
                }
                finalColor = (local150 >> 8) + (local73 & 0xFF00) + (local125 << 8 & 0xFF001F);
            }
        }
        return finalColor;
    }

    @OriginalMember(owner = "client!eh", name = "a", descriptor = "(I[[I[[FI[[FIBIIBZBI[[FILclient!hg;)V")
    public static void addOverlayVertices(@OriginalArg(0) int color1, @OriginalArg(1) int[][] heightMap, @OriginalArg(2) float[][] lightValues, @OriginalArg(3) int tileX, @OriginalArg(4) float[][] ambientLight, @OriginalArg(5) int color2, @OriginalArg(6) byte rotation, @OriginalArg(7) int level, @OriginalArg(8) int color3, @OriginalArg(10) boolean hasUnderlay, @OriginalArg(11) byte overlayShape, @OriginalArg(12) int tileZ, @OriginalArg(13) float[][] shadowValues, @OriginalArg(14) int color4, @OriginalArg(15) GlTile glTile) {
        @Pc(11) int packedColor1 = (color1 << 8) + 255;
        @Pc(17) int packedColor2 = (color2 << 8) + 255;
        @Pc(23) int packedColor3 = (color3 << 8) + 255;
        @Pc(29) int packedColor4 = (color4 << 8) + 255;
        @Pc(33) int[] shapeVertices = SHAPE_OVERLAY_COORDINATES[overlayShape];
        @Pc(39) int[] baseVertices = new int[shapeVertices.length >> 1];
        @Pc(41) int vertex1;
        for (vertex1 = 0; vertex1 < baseVertices.length; vertex1++) {
            baseVertices[vertex1] = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, false, glTile, lightValues, tileZ, shapeVertices[vertex1 + vertex1], ambientLight, shapeVertices[vertex1 + vertex1 + 1]);
        }
        @Pc(87) int[] blendVertices = null;
        if (hasUnderlay) {
            @Pc(191) int vertex2;
            if (overlayShape == 1) {
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 64, ambientLight, 128);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 64);
                blendVertices = new int[] { vertex2, vertex1, baseVertices[2], vertex1, baseVertices[0], baseVertices[2] };
            } else if (overlayShape == 2) {
                blendVertices = new int[6];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 128);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 64, ambientLight, 0);
                blendVertices[2] = vertex1;
                blendVertices[0] = baseVertices[0];
                blendVertices[5] = baseVertices[0];
                blendVertices[3] = vertex1;
                blendVertices[1] = vertex2;
                blendVertices[4] = baseVertices[1];
            } else if (overlayShape == 3) {
                blendVertices = new int[6];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 0, ambientLight, 128);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 64, ambientLight, 0);
                blendVertices[4] = vertex2;
                blendVertices[1] = baseVertices[1];
                blendVertices[0] = baseVertices[2];
                blendVertices[3] = vertex1;
                blendVertices[2] = vertex1;
                blendVertices[5] = baseVertices[2];
            } else if (overlayShape == 4) {
                blendVertices = new int[3];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 0, ambientLight, 128);
                blendVertices[0] = baseVertices[3];
                blendVertices[2] = baseVertices[0];
                blendVertices[1] = vertex1;
            } else if (overlayShape == 5) {
                blendVertices = new int[3];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 128);
                blendVertices[1] = vertex1;
                blendVertices[0] = baseVertices[2];
                blendVertices[2] = baseVertices[3];
            } else if (overlayShape == 6) {
                blendVertices = new int[6];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 0);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 128);
                blendVertices[1] = vertex1;
                blendVertices[0] = baseVertices[3];
                blendVertices[2] = vertex2;
                blendVertices[4] = baseVertices[0];
                blendVertices[3] = vertex2;
                blendVertices[5] = baseVertices[3];
            } else if (overlayShape == 7) {
                blendVertices = new int[6];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 0, ambientLight, 128);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 0);
                blendVertices[3] = vertex1;
                blendVertices[2] = vertex1;
                blendVertices[0] = baseVertices[1];
                blendVertices[4] = baseVertices[2];
                blendVertices[1] = vertex2;
                blendVertices[5] = baseVertices[1];
            } else if (overlayShape == 8) {
                blendVertices = new int[3];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 0, ambientLight, 0);
                blendVertices[2] = baseVertices[4];
                blendVertices[0] = baseVertices[3];
                blendVertices[1] = vertex1;
            } else if (overlayShape == 9) {
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 64);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 96, ambientLight, 32);
                @Pc(715) int vertex3 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 64, ambientLight, 0);
                blendVertices = new int[] { vertex2, vertex1, baseVertices[4], vertex2, baseVertices[4], baseVertices[3], vertex2, baseVertices[3], baseVertices[2], vertex2, baseVertices[2], baseVertices[1], vertex2, baseVertices[1], vertex3 };
            } else if (overlayShape == 10) {
                blendVertices = new int[9];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 0, ambientLight, 128);
                blendVertices[0] = baseVertices[2];
                blendVertices[8] = baseVertices[0];
                blendVertices[1] = vertex1;
                blendVertices[4] = vertex1;
                blendVertices[2] = baseVertices[3];
                blendVertices[7] = vertex1;
                blendVertices[3] = baseVertices[3];
                blendVertices[5] = baseVertices[4];
                blendVertices[6] = baseVertices[4];
            } else if (overlayShape == 11) {
                blendVertices = new int[12];
                vertex1 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 0, ambientLight, 64);
                vertex2 = createVertex(packedColor4, 0.0F, packedColor1, packedColor2, null, heightMap, tileX, shadowValues, packedColor3, rotation, true, glTile, lightValues, tileZ, 128, ambientLight, 64);
                blendVertices[5] = vertex1;
                blendVertices[1] = vertex1;
                blendVertices[8] = vertex1;
                blendVertices[0] = baseVertices[3];
                blendVertices[2] = baseVertices[0];
                blendVertices[11] = vertex2;
                blendVertices[6] = baseVertices[2];
                blendVertices[7] = vertex2;
                blendVertices[10] = baseVertices[1];
                blendVertices[3] = baseVertices[3];
                blendVertices[4] = baseVertices[2];
                blendVertices[9] = baseVertices[2];
            }
        }
        glTile.method1945(level, tileX, tileZ, baseVertices, blendVertices, false);
    }

    @OriginalMember(owner = "runetek4.client!ql", name = "a", descriptor = "(IFII[[I[[II[[FIBIZLclient!hg;[[FII[[FI)I")
    public static int createVertex(@OriginalArg(0) int color1, @OriginalArg(1) float heightScale, @OriginalArg(2) int color2, @OriginalArg(3) int color3, @OriginalArg(4) int[][] waterHeights, @OriginalArg(5) int[][] heightMap, @OriginalArg(6) int tileX, @OriginalArg(7) float[][] shadowValues, @OriginalArg(8) int color4, @OriginalArg(10) int rotation, @OriginalArg(11) boolean isBlended, @OriginalArg(12) GlTile glTile, @OriginalArg(13) float[][] lightValues, @OriginalArg(14) int tileZ, @OriginalArg(15) int vertexX, @OriginalArg(16) float[][] ambientLight, @OriginalArg(17) int vertexZ) {
        @Pc(20) int tempCoord;
        if (rotation == 1) {
            tempCoord = vertexX;
            vertexX = vertexZ;
            vertexZ = 128 - tempCoord;
        } else if (rotation == 2) {
            vertexZ = 128 - vertexZ;
            vertexX = 128 - vertexX;
        } else if (rotation == 3) {
            tempCoord = vertexX;
            vertexX = 128 - vertexZ;
            vertexZ = tempCoord;
        }
        @Pc(66) float ambientValue;
        @Pc(72) float shadowValue;
        @Pc(80) int finalColor;
        @Pc(78) float lightValue;
        if (vertexX == 0 && vertexZ == 0) {
            ambientValue = ambientLight[tileX][tileZ];
            shadowValue = shadowValues[tileX][tileZ];
            lightValue = lightValues[tileX][tileZ];
            finalColor = color2;
        } else if (vertexX == 128 && vertexZ == 0) {
            finalColor = color3;
            ambientValue = ambientLight[tileX + 1][tileZ];
            shadowValue = shadowValues[tileX + 1][tileZ];
            lightValue = lightValues[tileX + 1][tileZ];
        } else if (vertexX == 128 && vertexZ == 128) {
            shadowValue = shadowValues[tileX + 1][tileZ + 1];
            lightValue = lightValues[tileX + 1][tileZ + 1];
            ambientValue = ambientLight[tileX + 1][tileZ + 1];
            finalColor = color4;
        } else if (vertexX == 0 && vertexZ == 128) {
            shadowValue = shadowValues[tileX][tileZ + 1];
            ambientValue = ambientLight[tileX][tileZ + 1];
            lightValue = lightValues[tileX][tileZ + 1];
            finalColor = color1;
        } else {
            lightValue = lightValues[tileX][tileZ];
            ambientValue = ambientLight[tileX][tileZ];
            @Pc(219) float zInterpolation = (float) vertexZ / 128.0F;
            @Pc(224) float xInterpolation = (float) vertexX / 128.0F;
            @Pc(238) float interpolatedLight1 = lightValue + (lightValues[tileX + 1][tileZ] - lightValue) * xInterpolation;
            @Pc(253) float interpolatedAmbient1 = ambientValue + (ambientLight[tileX + 1][tileZ] - ambientValue) * xInterpolation;
            @Pc(261) float cornerLight = lightValues[tileX][tileZ + 1];
            @Pc(269) float cornerAmbient = ambientLight[tileX][tileZ + 1];
            @Pc(286) float interpolatedAmbient2 = cornerAmbient + (ambientLight[tileX + 1][tileZ + 1] - cornerAmbient) * xInterpolation;
            shadowValue = shadowValues[tileX][tileZ];
            ambientValue = interpolatedAmbient1 + zInterpolation * (interpolatedAmbient2 - interpolatedAmbient1);
            @Pc(309) float cornerShadow = shadowValues[tileX][tileZ + 1];
            @Pc(326) float interpolatedLight2 = cornerLight + (lightValues[tileX + 1][tileZ + 1] - cornerLight) * xInterpolation;
            lightValue = interpolatedLight1 + zInterpolation * (interpolatedLight2 - interpolatedLight1);
            @Pc(352) float interpolatedShadow1 = cornerShadow + (shadowValues[tileX + 1][tileZ + 1] - cornerShadow) * xInterpolation;
            @Pc(367) float interpolatedShadow2 = shadowValue + (shadowValues[tileX + 1][tileZ] - shadowValue) * xInterpolation;
            shadowValue = interpolatedShadow2 + (interpolatedShadow1 - interpolatedShadow2) * zInterpolation;
            @Pc(382) int interpolatedColor1 = interpolateColor(vertexX, color2, color3);
            @Pc(388) int interpolatedColor2 = interpolateColor(vertexX, color1, color4);
            finalColor = interpolateColor(vertexZ, interpolatedColor1, interpolatedColor2);
        }
        @Pc(405) int worldZ = vertexZ + (tileZ << 7);
        @Pc(413) int worldY = interpolateHeight(vertexX, tileZ, heightMap, tileX, vertexZ);
        @Pc(420) int worldX = (tileX << 7) + vertexX;
        return glTile.method1941(worldX, worldY, worldZ, lightValue, ambientValue, shadowValue, isBlended ? finalColor & 0xFFFFFF00 : finalColor, waterHeights == null ? 0.0F : (float) (worldY - interpolateHeight(vertexX, tileZ, waterHeights, tileX, vertexZ)) / heightScale);
    }

    @OriginalMember(owner = "runetek4.client!oj", name = "a", descriptor = "(IBI[[III)I")
    public static int interpolateHeight(@OriginalArg(0) int localX, @OriginalArg(2) int tileZ, @OriginalArg(3) int[][] heightMap, @OriginalArg(4) int tileX, @OriginalArg(5) int localZ) {
        @Pc(25) int interpolatedTop = localX * heightMap[tileX + 1][tileZ] + (128 - localX) * heightMap[tileX][tileZ] >> 7;
        @Pc(52) int interpolatedBottom = heightMap[tileX][tileZ + 1] * (128 - localX) + heightMap[tileX + 1][tileZ + 1] * localX >> 7;
        return interpolatedTop * (128 - localZ) + localZ * interpolatedBottom >> 7;
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(IIII)I")
    public static int interpolateColor(@OriginalArg(1) int blendFactor, @OriginalArg(2) int color1, @OriginalArg(3) int color2) {
        if (color1 == color2) {
            return color1;
        } else {
            @Pc(17) int inverseFactor = 128 - blendFactor;
            @Pc(50) int highChannels = blendFactor * (color2 >>> 7 & 0x1FE01FE) + inverseFactor * (color1 >>> 7 & 0x1FE01FE) & 0xFF00FF00;
            @Pc(65) int lowChannels = inverseFactor * (color1 & 0xFF00FF) + (color2 & 0xFF00FF) * blendFactor & 0xFF00FF00;
            return highChannels + (lowChannels >> 7);
        }
    }

    @OriginalMember(owner = "runetek4.client!tm", name = "a", descriptor = "(III[Lclient!mj;IB[BIIIZ)V")
    public static void parseTerrainData(@OriginalArg(0) int rotation, @OriginalArg(1) int baseX, @OriginalArg(2) int plane, @OriginalArg(3) CollisionMap[] collisionMaps, @OriginalArg(4) int baseZ, @OriginalArg(6) byte[] terrainData, @OriginalArg(7) int targetPlane, @OriginalArg(8) int regionOffsetZ, @OriginalArg(9) int regionOffsetX, @OriginalArg(10) boolean underWater) {
        @Pc(17) int z;
        if (!underWater) {
            for (@Pc(10) int x = 0; x < 8; x++) {
                for (z = 0; z < 8; z++) {
                    if (baseX + x > 0 && x + baseX < 103 && z + baseZ > 0 && baseZ + z < 103) {
                        collisionMaps[plane].flags[x + baseX][z + baseZ] &= 0xFEFFFFFF;
                    }
                }
            }
        }
        @Pc(87) byte planeCount;
        if (underWater) {
            planeCount = 1;
        } else {
            planeCount = 4;
        }
        @Pc(96) Packet packet = new Packet(terrainData);
        @Pc(103) int tileX;
        @Pc(108) int tileZ;
        for (z = 0; z < planeCount; z++) {
            for (tileX = 0; tileX < 64; tileX++) {
                for (tileZ = 0; tileZ < 64; tileZ++) {
                    if (targetPlane == z && regionOffsetX <= tileX && regionOffsetX + 8 > tileX && regionOffsetZ <= tileZ && tileZ < regionOffsetZ + 8) {
                        readTile(0, 0, underWater, packet, transformZ(rotation, tileX & 0x7, tileZ & 0x7) + baseZ, transformX(rotation, tileZ & 0x7, tileX & 0x7) + baseX, rotation, plane);
                    } else {
                        readTile(0, 0, underWater, packet, -1, -1, 0, 0);
                    }
                }
            }
        }
        @Pc(232) int local232;
        @Pc(417) int local417;
        @Pc(255) int local255;
        @Pc(266) int rotatedX;
        @Pc(316) int rotatedZ;
        while (packet.data.length > packet.offset) {
            tileX = packet.g1();
            if (tileX != 129) {
                packet.offset--;
                break;
            }
            for (tileZ = 0; tileZ < 4; tileZ++) {
                @Pc(223) byte overlayType = packet.g1s();
                @Pc(237) int local237;
                if (overlayType == 0) {
                    if (tileZ <= targetPlane) {
                        local237 = baseX + 7;
                        local232 = baseX;
                        local255 = baseZ + 7;
                        if (local255 < 0) {
                            local255 = 0;
                        } else if (local255 >= 104) {
                            local255 = 104;
                        }
                        if (local237 < 0) {
                            local237 = 0;
                        } else if (local237 >= 104) {
                            local237 = 104;
                        }
                        local417 = baseZ;
                        if (baseZ < 0) {
                            local417 = 0;
                        } else if (baseZ >= 104) {
                            local417 = 104;
                        }
                        if (baseX < 0) {
                            local232 = 0;
                        } else if (baseX >= 104) {
                            local232 = 104;
                        }
                        while (local237 > local232) {
                            while (local417 < local255) {
                                tileSettings[plane][local232][local417] = 0;
                                local417++;
                            }
                            local232++;
                        }
                    }
                } else if (overlayType == 1) {
                    for (local232 = 0; local232 < 64; local232 += 4) {
                        for (local237 = 0; local237 < 64; local237 += 4) {
                            @Pc(246) byte overlayId = packet.g1s();
                            if (tileZ <= targetPlane) {
                                for (local255 = local232; local255 < local232 + 4; local255++) {
                                    for (rotatedX = local237; rotatedX < local237 + 4; rotatedX++) {
                                        if (local255 >= regionOffsetX && local255 < regionOffsetX + 8 && rotatedX >= regionOffsetZ && regionOffsetZ + 8 > regionOffsetZ) {
                                            rotatedZ = baseX + transformX(rotation, rotatedX & 0x7, local255 & 0x7);
                                            @Pc(328) int local328 = transformZ(rotation, local255 & 0x7, rotatedX & 0x7) + baseZ;
                                            if (rotatedZ >= 0 && rotatedZ < 104 && local328 >= 0 && local328 < 104) {
                                                tileSettings[plane][rotatedZ][local328] = overlayId;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (overlayType == 2) {
                    // TODO why is this here?
                }
            }
        }
        @Pc(497) int local497;
        if (GlRenderer.enabled && !underWater) {
            @Pc(472) Environment environment = null;
            lightProcessingComplete: while (true) {
                processLights: do {
                    while (packet.data.length > packet.offset) {
                        tileZ = packet.g1();
                        if (tileZ != 0) {
                            if (tileZ != 1) {
                                throw new IllegalStateException();
                            }
                            local497 = packet.g1();
                            continue processLights;
                        }
                        environment = new Environment(packet);
                    }
                    if (environment == null) {
                        environment = new Environment();
                    }
                    FogManager.chunksAtmosphere[baseX >> 3][baseZ >> 3] = environment;
                    break lightProcessingComplete;
                } while (local497 <= 0);
                for (local232 = 0; local232 < local497; local232++) {
                    @Pc(517) Light light = new Light(packet);
                    if (light.anInt2243 == 31) {
                        @Pc(529) LightType lightType = LightTypeList.get(packet.g2());
                        light.method1762(lightType.anInt2865, lightType.anInt2873, lightType.anInt2867, lightType.anInt2872);
                    }
                    local417 = light.x >> 7;
                    local255 = light.z >> 7;
                    if (targetPlane == light.level && local417 >= regionOffsetX && regionOffsetX + 8 > local417 && regionOffsetZ <= local255 && regionOffsetZ + 8 > local255) {
                        rotatedX = transformXFine(rotation, light.x & 0x3FF, light.z & 0x3FF) + (baseX << 7);
                        rotatedZ = transformZFine(light.x & 0x3FF, rotation, light.z & 0x3FF) + (baseZ << 7);
                        light.x = rotatedX;
                        light.z = rotatedZ;
                        local417 = light.x >> 7;
                        local255 = light.z >> 7;
                        if (local417 >= 0 && local255 >= 0 && local417 < 104 && local255 < 104) {
                            light.aBoolean125 = (renderFlags[1][local417][local255] & 0x2) != 0;
                            light.y = tileHeights[light.level][local417][local255] - light.y;
                            LightingManager.method2389(light);
                        }
                    }
                }
            }
        }
        tileX = baseX + 7;
        tileZ = baseZ + 7;
        for (local497 = baseX; local497 < tileX; local497++) {
            for (local232 = baseZ; local232 < tileZ; local232++) {
                tileSettings[plane][local497][local232] = 0;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!rg", name = "a", descriptor = "(IZII)I")
    public static int transformX(@OriginalArg(0) int rotation, @OriginalArg(2) int y, @OriginalArg(3) int x) {
        @Pc(3) int rotationQuadrant = rotation & 0x3;
        if (rotationQuadrant == 0) {
            return x;
        } else if (rotationQuadrant == 1) {
            return y;
        } else if (rotationQuadrant == 2) {
            return 7 - x;
        } else {
            return 7 - y;
        }
    }

    @OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IBII)I")
    public static int transformZ(@OriginalArg(0) int rotation, @OriginalArg(2) int x, @OriginalArg(3) int z) {
        @Pc(3) int rotationQuadrant = rotation & 0x3;
        if (rotationQuadrant == 0) {
            return z;
        } else if (rotationQuadrant == 1) {
            return 7 - x;
        } else if (rotationQuadrant == 2) {
            return 7 - z;
        } else {
            return x;
        }
    }

    @OriginalMember(owner = "runetek4.client!qi", name = "a", descriptor = "(IIBI)I")
    public static int transformXFine(@OriginalArg(0) int rotation, @OriginalArg(1) int x, @OriginalArg(3) int z) {
        @Pc(3) int rotationQuadrant = rotation & 0x3;
        if (rotationQuadrant == 0) {
            return x;
        } else if (rotationQuadrant == 1) {
            return z;
        } else if (rotationQuadrant == 2) {
            return 1023 - x;
        } else {
            return 1023 - z;
        }
    }

    @OriginalMember(owner = "runetek4.client!ol", name = "a", descriptor = "(IIZI)I")
    public static int transformZFine(@OriginalArg(0) int x, @OriginalArg(1) int rotation, @OriginalArg(3) int z) {
        @Pc(3) int rotationQuadrant = rotation & 0x3;
        if (rotationQuadrant == 0) {
            return z;
        } else if (rotationQuadrant == 1) {
            return 1023 - x;
        } else if (rotationQuadrant == 2) {
            return 1023 - z;
        } else {
            return x;
        }
    }

    @OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "(III[[[BIBII)V")
    public static void renderVisibleTiles(@OriginalArg(0) int offsetX, @OriginalArg(1) int offsetY, @OriginalArg(2) int offsetZ, @OriginalArg(3) byte[][][] visibilityMask, @OriginalArg(4) int maxVisiblePlane, @OriginalArg(5) byte visibilityFlag, @OriginalArg(6) int centerX, @OriginalArg(7) int centerZ) {
        renderDistance++;
        fogDistance = 0;
        @Pc(9) int minUpdateX = centerX - 16;
        @Pc(13) int maxUpdateX = centerX + 16;
        @Pc(17) int minUpdateZ = centerZ - 16;
        @Pc(21) int maxUpdateZ = centerZ + 16;
        @Pc(32) int x;
        @Pc(37) int z;
        @Pc(183) int sceneryIndex;
        for (@Pc(23) int plane = frameCounter; plane < planes; plane++) {
            @Pc(30) Tile[][] planeTiles = tiles[plane];
            for (x = LightingManager.anInt987; x < LightingManager.anInt15; x++) {
                for (z = LightingManager.anInt4698; z < LightingManager.anInt4866; z++) {
                    @Pc(46) Tile tile = planeTiles[x][z];
                    if (tile != null) {
                        if (tileVisibility[x + visibility - eyeTileX][z + visibility - eyeTileZ] && (visibilityMask == null || plane < maxVisiblePlane || visibilityMask[plane][x][z] != visibilityFlag)) {
                            tile.hasObjects = true;
                            tile.bridgeAbove = true;
                            if (tile.sceneryLen > 0) {
                                tile.containsLocs = true;
                            } else {
                                tile.containsLocs = false;
                            }
                            fogDistance++;
                        } else {
                            tile.hasObjects = false;
                            tile.bridgeAbove = false;
                            tile.locCheckFlags = 0;
                            if (x >= minUpdateX && x <= maxUpdateX && z >= minUpdateZ && z <= maxUpdateZ) {
                                if (tile.wall != null) {
                                    @Pc(103) Wall local103 = tile.wall;
                                    local103.primary.update(0, plane, local103.yFine, local103.xFine, local103.zFine);
                                    if (local103.secondary != null) {
                                        local103.secondary.update(0, plane, local103.yFine, local103.xFine, local103.zFine);
                                    }
                                }
                                if (tile.wallDecor != null) {
                                    @Pc(134) WallDecor local134 = tile.wallDecor;
                                    local134.primary.update(local134.yFine, plane, local134.yOffset, local134.xFine, local134.zFine);
                                    if (local134.secondary != null) {
                                        local134.secondary.update(local134.yFine, plane, local134.yOffset, local134.xFine, local134.zFine);
                                    }
                                }
                                if (tile.groundDecor != null) {
                                    @Pc(167) GroundDecor local167 = tile.groundDecor;
                                    local167.entity.update(0, plane, local167.yFine, local167.xFine, local167.zFine);
                                }
                                if (tile.scenery != null) {
                                    for (sceneryIndex = 0; sceneryIndex < tile.sceneryLen; sceneryIndex++) {
                                        @Pc(192) Scenery local192 = tile.scenery[sceneryIndex];
                                        local192.entity.update(local192.animationId, plane, local192.type, local192.yMin, local192.yMax);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        @Pc(240) boolean isUnderwater = tileHeights == underwaterTileHeights;
        if (GlRenderer.enabled) {
            @Pc(244) GL2 local244 = GlRenderer.gl;
            local244.glPushMatrix();
            local244.glTranslatef((float) -offsetX, (float) -offsetY, (float) -offsetZ);
            if (isUnderwater) {
                UnderwaterMaterialRenderer.method2959();
                MaterialManager.setMaterial(-1, 3);
                MaterialManager.renderingUnderwater = true;
                UnderwaterMaterialRenderer.method4609();
                lastAnimationFrame = -1;
                currentAnimationFrame = -1;
                for (x = 0; x < underwaterHdTiles[0].length; x++) {
                    @Pc(285) GlTile local285 = underwaterHdTiles[0][x];
                    @Pc(294) float local294 = 251.5F - (local285.blend ? 1.0F : 0.5F);
                    if (local285.underwaterColor != lastAnimationFrame) {
                        lastAnimationFrame = local285.underwaterColor;
                        WaterMaterialRenderer.method619(local285.underwaterColor);
                        FogManager.setFogColor(WaterMaterialRenderer.method2422());
                    }
                    local285.method1944(tiles, local294, false);
                }
                UnderwaterMaterialRenderer.method4608();
            } else {
                x = frameCounter;
                while (true) {
                    if (x >= planes) {
                        LightingManager.method2402(eyeTileX, eyeTileZ, tiles);
                        break;
                    }
                    for (z = 0; z < underwaterHdTiles[x].length; z++) {
                        @Pc(336) GlTile local336 = underwaterHdTiles[x][z];
                        @Pc(350) float local350 = 201.5F - (float) x * 50.0F - (local336.blend ? 1.0F : 0.5F);
                        if (local336.texture != -1 && Rasterizer.textureProvider.getMaterialType(local336.texture) == 4 && Preferences.highWaterDetail) {
                            WaterMaterialRenderer.method619(local336.underwaterColor);
                        }
                        local336.method1944(tiles, local350, false);
                    }
                    if (x == 0 && Preferences.sceneryShadowsType > 0) {
                        GlRenderer.method4159(101.5F);
                        ShadowManager.method4198(eyeTileX, eyeTileZ, visibility, offsetY, tileVisibility, tileHeights[0]);
                    }
                    x++;
                }
            }
            local244.glPopMatrix();
        }
        @Pc(434) int topZ;
        @Pc(438) int bottomZ;
        @Pc(450) Tile renderTile;
        @Pc(399) int renderPlane;
        @Pc(406) Tile[][] renderPlaneTiles;
        @Pc(415) int leftX;
        @Pc(428) int zOffset;
        for (renderPlane = frameCounter; renderPlane < planes; renderPlane++) {
            renderPlaneTiles = tiles[renderPlane];
            for (z = -visibility; z <= 0; z++) {
                leftX = eyeTileX + z;
                sceneryIndex = eyeTileX - z;
                if (leftX >= LightingManager.anInt987 || sceneryIndex < LightingManager.anInt15) {
                    for (zOffset = -visibility; zOffset <= 0; zOffset++) {
                        topZ = eyeTileZ + zOffset;
                        bottomZ = eyeTileZ - zOffset;
                        if (leftX >= LightingManager.anInt987) {
                            if (topZ >= LightingManager.anInt4698) {
                                renderTile = renderPlaneTiles[leftX][topZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, true);
                                }
                            }
                            if (bottomZ < LightingManager.anInt4866) {
                                renderTile = renderPlaneTiles[leftX][bottomZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, true);
                                }
                            }
                        }
                        if (sceneryIndex < LightingManager.anInt15) {
                            if (topZ >= LightingManager.anInt4698) {
                                renderTile = renderPlaneTiles[sceneryIndex][topZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, true);
                                }
                            }
                            if (bottomZ < LightingManager.anInt4866) {
                                renderTile = renderPlaneTiles[sceneryIndex][bottomZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, true);
                                }
                            }
                        }
                        if (fogDistance == 0) {
                            if (!isUnderwater) {
                                MiniMenu.walkTargetActive = false;
                            }
                            return;
                        }
                    }
                }
            }
        }
        for (renderPlane = frameCounter; renderPlane < planes; renderPlane++) {
            renderPlaneTiles = tiles[renderPlane];
            for (z = -visibility; z <= 0; z++) {
                leftX = eyeTileX + z;
                sceneryIndex = eyeTileX - z;
                if (leftX >= LightingManager.anInt987 || sceneryIndex < LightingManager.anInt15) {
                    for (zOffset = -visibility; zOffset <= 0; zOffset++) {
                        topZ = eyeTileZ + zOffset;
                        bottomZ = eyeTileZ - zOffset;
                        if (leftX >= LightingManager.anInt987) {
                            if (topZ >= LightingManager.anInt4698) {
                                renderTile = renderPlaneTiles[leftX][topZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, false);
                                }
                            }
                            if (bottomZ < LightingManager.anInt4866) {
                                renderTile = renderPlaneTiles[leftX][bottomZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, false);
                                }
                            }
                        }
                        if (sceneryIndex < LightingManager.anInt15) {
                            if (topZ >= LightingManager.anInt4698) {
                                renderTile = renderPlaneTiles[sceneryIndex][topZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, false);
                                }
                            }
                            if (bottomZ < LightingManager.anInt4866) {
                                renderTile = renderPlaneTiles[sceneryIndex][bottomZ];
                                if (renderTile != null && renderTile.hasObjects) {
                                    renderScene(renderTile, false);
                                }
                            }
                        }
                        if (fogDistance == 0) {
                            if (!isUnderwater) {
                                MiniMenu.walkTargetActive = false;
                            }
                            return;
                        }
                    }
                }
            }
        }
        MiniMenu.walkTargetActive = false;
    }

    @OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IIZIII)V")
    public static void drawScene(@OriginalArg(1) int height, @OriginalArg(2) boolean skipEntityUpdates, @OriginalArg(3) int x, @OriginalArg(4) int width, @OriginalArg(5) int y) {
        ClientScriptRunner.anInt3325++;
        ClientScriptRunner.resetTileOccupancy();
        if (!skipEntityUpdates) {
            ClientScriptRunner.pushPlayers(true);
            ClientScriptRunner.pushNpcs(true);
            ClientScriptRunner.pushPlayers(false);
        }
        ClientScriptRunner.pushNpcs(false);
        if (!skipEntityUpdates) {
            ClientScriptRunner.updateSceneProjectiles();
        }
        ClientScriptRunner.updateSpotAnims();
        if (GlRenderer.enabled) {
            ClientScriptRunner.method2314(width, y, height, x, true);
            x = ClientScriptRunner.anInt983;
            y = ClientScriptRunner.anInt773;
            width = ClientScriptRunner.calculatedViewportWidth;
            height = ClientScriptRunner.calculatedViewportHeight;
        }
        @Pc(59) int pitch;
        @Pc(57) int cameraY;
        if (Camera.mode == MODE_DEFAULT) {
            cameraY = Camera.cameraAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
            pitch = Camera.orbitCameraPitch;
            if (pitch < Camera.cameraPitchClamp / 256) {
                pitch = Camera.cameraPitchClamp / 256;
            }
            if (Camera.cameraModifierEnabled[4] && Camera.cameraAmplitude[4] + 128 > pitch) {
                pitch = Camera.cameraAmplitude[4] + 128;
            }
            Camera.orbitCamera(Camera.cameraX, height, getTileHeight(Player.currentLevel, PlayerList.self.xFine, PlayerList.self.zFine) - 50, 600 + (pitch * 3), cameraY, Camera.cameraZ, pitch);
        }
        cameraY = Camera.cameraY;
        pitch = Camera.renderX;
        @Pc(121) int originalRenderZ = Camera.renderZ;
        @Pc(123) int originalCameraPitch = Camera.cameraPitch;
        @Pc(125) int originalCameraYaw = Camera.cameraYaw;
        @Pc(127) int modifierType;
        @Pc(171) int jitter;
        for (modifierType = 0; modifierType < 5; modifierType++) {
            if (Camera.cameraModifierEnabled[modifierType]) {
                jitter = (int) ((double) -Camera.cameraModifierJitter[modifierType] + (double) (Camera.cameraModifierJitter[modifierType] * 2 + 1) * Math.random() + Math.sin((double) Protocol.cameraModifierCycle[modifierType] * ((double) Camera.cameraFrequency[modifierType] / 100.0D)) * (double) Camera.cameraAmplitude[modifierType]);
                if (modifierType == 3) {
                    Camera.cameraYaw = jitter + Camera.cameraYaw & 0x7FF;
                }
                if (modifierType == 4) {
                    Camera.cameraPitch += jitter;
                    if (Camera.cameraPitch < 128) {
                        Camera.cameraPitch = 128;
                    }
                    if (Camera.cameraPitch > 383) {
                        Camera.cameraPitch = 383;
                    }
                }
                if (modifierType == 2) {
                    Camera.renderZ += jitter;
                }
                if (modifierType == 1) {
                    Camera.cameraY += jitter;
                }
                if (modifierType == 0) {
                    Camera.renderX += jitter;
                }
            }
        }
        ClientScriptRunner.performVisibilityCulling();
        if (GlRenderer.enabled) {
            GlRaster.setClip(x, y, x + width, y - -height);
            @Pc(248) float pitchDegrees = (float) Camera.cameraPitch * 0.17578125F;
            @Pc(253) float yawDegrees = (float) Camera.cameraYaw * 0.17578125F;
            if (Camera.mode == MODE_SPLINE) {
                pitchDegrees = Camera.pitchRadians * 360.0F / 6.2831855F;
                yawDegrees = Camera.yawRadians * 360.0F / 6.2831855F;
            }
            GlRenderer.method4171(x, y, width, height, width / 2 + x, y - -(height / 2), pitchDegrees, yawDegrees, ClientScriptRunner.anInt5029, ClientScriptRunner.anInt5029);
        } else {
            SoftwareRenderer.setClip(x, y, width + x, height + y);
            Rasterizer.prepare();
        }
        if (ClientScriptRunner.menuVisible || ClientScriptRunner.scriptMouseX < x || ClientScriptRunner.scriptMouseX >= width + x || y > ClientScriptRunner.scriptMouseY || height + y <= ClientScriptRunner.scriptMouseY) {
            RawModel.allowInput = false;
            MiniMenu.pickedEntityCount = 0;
        } else {
            RawModel.allowInput = true;
            MiniMenu.pickedEntityCount = 0;
            jitter = Rasterizer.screenUpperX;
            @Pc(344) int screenBottom = Rasterizer.screenLowerY;
            modifierType = Rasterizer.screenLowerX;
            GlModel.anInt3582 = modifierType + (jitter - modifierType) * (-x + ClientScriptRunner.scriptMouseX) / width;
            @Pc(361) int screenTop = Rasterizer.screenUpperY;
            RawModel.anInt1053 = (screenTop - screenBottom) * (ClientScriptRunner.scriptMouseY - y) / height + screenBottom;
        }
        Client.audioLoop();
        @Pc(387) byte roofVisibilityFlag = ClientScriptRunner.getRoofVisibilityMode() == 2 ? (byte) ClientScriptRunner.anInt3325 : 1;
        if (GlRenderer.enabled) {
            GlRenderer.restoreLighting();
            GlRenderer.setDepthTestEnabled(true);
            GlRenderer.setFogEnabled(true);
            if (Client.gameState == 10) {
                jitter = FogManager.method2235(Protocol.sceneDelta, Camera.renderZ >> 10, Preferences.brightness, Camera.renderX >> 10);
            } else {
                jitter = FogManager.method2235(Protocol.sceneDelta, PlayerList.self.movementQueueZ[0] >> 3, Preferences.brightness, PlayerList.self.movementQueueX[0] >> 3);
            }
            LightingManager.method2394(Client.loop, !Preferences.flickeringEffectsOn);
            GlRenderer.clearColorAndDepthBuffers(jitter);
            MaterialManager.method2731(Camera.cameraPitch, Camera.renderZ, Camera.cameraY, Camera.renderX, Camera.cameraYaw);
            GlRenderer.anInt5323 = Client.loop;
            setupSceneRender(Camera.renderX, Camera.cameraY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, ClientScriptRunner.tileMarkings, ClientScriptRunner.maxHeights, ClientScriptRunner.anIntArray338, ClientScriptRunner.anIntArray518, ClientScriptRunner.anIntArray134, ClientScriptRunner.anIntArray476, Player.currentLevel + 1, roofVisibilityFlag, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
            ClientScriptRunner.aBoolean299 = true;
            LightingManager.method2390();
            MaterialManager.method2731(0, 0, 0, 0, 0);
            Client.audioLoop();
            ClientScriptRunner.clearAllScenery();
            ClientScriptRunner.drawOverheads(y, width, x, ClientScriptRunner.anInt5029, height, ClientScriptRunner.anInt5029);
            MiniMap.renderOverheadHints(width, x, height, ClientScriptRunner.anInt5029, ClientScriptRunner.anInt5029, y);
        } else {
            SoftwareRenderer.fillRect(x, y, width, height, 0);
            setupSceneRender(Camera.renderX, Camera.cameraY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, ClientScriptRunner.tileMarkings, ClientScriptRunner.maxHeights, ClientScriptRunner.anIntArray338, ClientScriptRunner.anIntArray518, ClientScriptRunner.anIntArray134, ClientScriptRunner.anIntArray476, Player.currentLevel + 1, roofVisibilityFlag, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
            Client.audioLoop();
            ClientScriptRunner.clearAllScenery();
            ClientScriptRunner.drawOverheads(y, width, x, 256, height, 256);
            MiniMap.renderOverheadHints(width, x, height, 256, 256, y);
        }
        ((Js5TextureProvider) Rasterizer.textureProvider).method3239(Protocol.sceneDelta);
        Player.method2310(width, y, height, x);
        Camera.cameraPitch = originalCameraPitch;
        Camera.renderZ = originalRenderZ;
        Camera.cameraY = cameraY;
        Camera.renderX = pitch;
        Camera.cameraYaw = originalCameraYaw;
        if (ClientScriptRunner.aBoolean43 && Client.js5NetQueue.getUrgentRequestCount() == 0) {
            ClientScriptRunner.aBoolean43 = false;
        }
        if (ClientScriptRunner.aBoolean43) {
            if (GlRenderer.enabled) {
                GlRaster.fillRect(x, y, width, height, 0);
            } else {
                SoftwareRenderer.fillRect(x, y, width, height, 0);
            }
            Fonts.drawTextOnScreen(false, LocalizedText.LOADING);
        }
        if (!skipEntityUpdates && !ClientScriptRunner.aBoolean43 && !ClientScriptRunner.menuVisible && x <= ClientScriptRunner.scriptMouseX && width + x > ClientScriptRunner.scriptMouseX && y <= ClientScriptRunner.scriptMouseY && height + y > ClientScriptRunner.scriptMouseY) {
            MiniMenu.populateMenuEntries(y, width, height, x, ClientScriptRunner.scriptMouseY, ClientScriptRunner.scriptMouseX);
        }
    }
}
