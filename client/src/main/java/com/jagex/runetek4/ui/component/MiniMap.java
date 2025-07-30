package com.jagex.runetek4.ui.component;

import com.jagex.runetek4.*;
import com.jagex.runetek4.game.world.WorldLoader;
import com.jagex.runetek4.ui.chat.ClanChat;
import com.jagex.runetek4.client.LoginManager;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.data.cache.media.SoftwareSprite;
import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.msi.MSITypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.config.types.msi.MSIType;
import com.jagex.runetek4.core.datastruct.LinkedList;
import com.jagex.runetek4.entity.entity.Npc;
import com.jagex.runetek4.entity.entity.NpcList;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.game.logic.PathFinder;
import com.jagex.runetek4.graphics.gl.GlFont;
import com.jagex.runetek4.graphics.gl.GlRaster;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.ui.sprite.GlSprite;
import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.game.map.MapMarker;
import com.jagex.runetek4.graphics.raster.SoftwareRaster;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.scene.tile.PlainTile;
import com.jagex.runetek4.scene.tile.ShapedTile;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.social.FriendList;
import com.jagex.runetek4.ui.sprite.SoftwareIndexedSprite;
import com.jagex.runetek4.ui.sprite.Sprite;
import com.jagex.runetek4.ui.sprite.Sprites;
import com.jagex.runetek4.util.math.MathUtils;
import com.jagex.runetek4.util.debug.Cheat;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MiniMap {

    @OriginalMember(owner = "runetek4.client!wc", name = "h", descriptor = "[[I")
    public static final int[][] TILE_ROTATION_PATTERNS = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };

    @OriginalMember(owner = "runetek4.client!ke", name = "T", descriptor = "[[I")
    public static final int[][] TILE_SHAPE_PATTERNS = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };

    @OriginalMember(owner = "runetek4.client!ld", name = "b", descriptor = "[Lclient!nc;")
    public static final MapMarker[] hintMapMarkers = new MapMarker[4];

    @OriginalMember(owner = "runetek4.client!qc", name = "ab", descriptor = "[I")
    public static final int[] locId = new int[1000];

    @OriginalMember(owner = "runetek4.client!lf", name = "d", descriptor = "[I")
    public static final int[] locX = new int[1000];

    @OriginalMember(owner = "runetek4.client!he", name = "eb", descriptor = "[I")
    public static final int[] locZ = new int[1000];

    @OriginalMember(owner = "runetek4.client!ld", name = "d", descriptor = "Lclient!na;")
    public static final JString HIDDEN_USE = JString.parse("Hidden)2use");

    @OriginalMember(owner = "runetek4.client!ha", name = "i", descriptor = "Lclient!qf;")
    public static Sprite sprite;

    @OriginalMember(owner = "runetek4.client!wb", name = "d", descriptor = "I")
    public static int state = 0;

    @OriginalMember(owner = "runetek4.client!we", name = "w", descriptor = "I")
    public static int minimapZoom = 0;

    @OriginalMember(owner = "runetek4.client!ej", name = "W", descriptor = "I")
    public static int minimapAnticheatAngle = 0;

    @OriginalMember(owner = "runetek4.client!sd", name = "R", descriptor = "I")
    public static int anInt5062;

    @OriginalMember(owner = "client!ef", name = "j", descriptor = "Lclient!mm;")
    public static SoftwareSprite softwareSprite;

    @OriginalMember(owner = "runetek4.client!ug", name = "m", descriptor = "I")
    public static int locs = 0;

    @OriginalMember(owner = "runetek4.client!vg", name = "d", descriptor = "I")
    public static int minimapAngleModifier = 2;

    @OriginalMember(owner = "runetek4.client!oe", name = "n", descriptor = "I")
    public static int minimapZoomModifier = 1;

    @OriginalMember(owner = "client!gi", name = "H", descriptor = "I")
    public static int minimapOffsetCycle = 0;

    @OriginalMember(owner = "runetek4.client!nf", name = "i", descriptor = "I")
    public static int anInt4075 = -1;

    @OriginalMember(owner = "runetek4.client!se", name = "h", descriptor = "I")
    public static int anInt5073 = -1;

    @OriginalMember(owner = "runetek4.client!em", name = "a", descriptor = "(Lclient!be;Lclient!qf;IIIBI)V")
    public static void drawOnMinimap(@OriginalArg(0) Component component, @OriginalArg(1) Sprite sprite, @OriginalArg(2) int worldX, @OriginalArg(3) int worldZ, @OriginalArg(4) int offsetY, @OriginalArg(6) int offsetX) {
        if (sprite == null) {
            return;
        }
        @Pc(21) int distanceSquared = worldZ * worldZ + worldX * worldX;
        @Pc(27) int rotatedAngle = minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
        @Pc(39) int maxRadius = Math.max(component.width / 2, component.height / 2) + 10;
        if (maxRadius * maxRadius < distanceSquared) {
            return;
        }
        @Pc(50) int sinValue = MathUtils.sin[rotatedAngle];
        @Pc(58) int scaledSin = sinValue * 256 / (minimapZoom + 256);
        @Pc(62) int cosValue = MathUtils.cos[rotatedAngle];
        @Pc(70) int scaledCos = cosValue * 256 / (minimapZoom + 256);
        @Pc(81) int rotatedX = scaledSin * worldX + worldZ * scaledCos >> 16;
        @Pc(92) int rotatedZ = scaledCos * worldX - worldZ * scaledSin >> 16;
        if (GlRenderer.enabled) {
            ((GlSprite) sprite).method1425(component.width / 2 + offsetX + rotatedX - sprite.innerWidth / 2, component.height / 2 + offsetY - (rotatedZ + sprite.innerHeight / 2), (GlSprite) component.method489(false));
        } else {
            ((SoftwareSprite) sprite).drawImage(component.width / 2 + offsetX + rotatedX - sprite.innerWidth / 2, -(sprite.innerHeight / 2) + component.height / 2 + offsetY + -rotatedZ, component.compassPixelOffsets, component.compassPixelWidths);
        }
    }

    @OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(IBIILclient!be;)V")
    public static void render(@OriginalArg(0) int rectangle, @OriginalArg(2) int y, @OriginalArg(3) int x, @OriginalArg(4) Component component) {
        Client.audioLoop();
        if (GlRenderer.enabled) {
            GlRaster.setClip(x, y, x + component.width, y + component.height);
        } else {
            SoftwareRaster.setClip(x, y, x + component.width, y + component.height);
        }
        if (state != 2 && state != 5 && sprite != null) {
            @Pc(48) int angle = minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
            @Pc(57) int anchorX = PlayerList.self.xFine / 32 + 48;
            @Pc(67) int anchorY = 464 - PlayerList.self.zFine / 32;
            if (GlRenderer.enabled) {
                ((GlSprite) sprite).renderRotatedTransparent(x, y, component.width, component.height, anchorX, anchorY, angle, minimapZoom + 256, (GlSprite) component.method489(false));
            } else {
                ((SoftwareSprite) sprite).renderRotated(x, y, component.width, component.height, anchorX, anchorY, angle, minimapZoom + 256, component.compassPixelOffsets, component.compassPixelWidths);
            }
            @Pc(146) int flagX;
            @Pc(181) int flagZ;
            @Pc(150) int relativeX;
            @Pc(154) int relativeZ;
            @Pc(231) int npcX;
            @Pc(200) int npcZ;
            @Pc(239) int local239;
            @Pc(271) int local271;
            if (WorldLoader.mapElementList != null) {
                for (@Pc(117) int local117 = 0; local117 < WorldLoader.mapElementList.anInt5074; local117++) {
                    if (WorldLoader.mapElementList.hasFlag4Set(local117)) {
                        flagX = (WorldLoader.mapElementList.aShortArray73[local117] - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
                        relativeX = MathUtils.sin[angle];
                        relativeZ = MathUtils.cos[angle];
                        @Pc(156) Font font = Fonts.p11Full;
                        @Pc(164) int scaledSin = relativeX * 256 / (minimapZoom + 256);
                        flagZ = (WorldLoader.mapElementList.aShortArray72[local117] - Camera.originZ) * 4 + 2 - PlayerList.self.zFine / 32;
                        @Pc(189) int scaledCos = relativeZ * 256 / (minimapZoom + 256);
                        npcZ = flagZ * scaledCos - flagX * scaledSin >> 16;
                        if (WorldLoader.mapElementList.getLowerTwoBits(local117) == 1) {
                            font = Fonts.p12Full;
                        }
                        if (WorldLoader.mapElementList.getLowerTwoBits(local117) == 2) {
                            font = Fonts.b12Full;
                        }
                        npcX = scaledSin * flagZ + scaledCos * flagX >> 16;
                        local239 = font.getMaxLineWidth(WorldLoader.mapElementList.text[local117], 100);
                        @Pc(245) int textX = npcX - local239 / 2;
                        if (textX >= -component.width && textX <= component.width && npcZ >= -component.height && npcZ <= component.height) {
                            local271 = 16777215;
                            if (WorldLoader.mapElementList.anIntArray444[local117] != -1) {
                                local271 = WorldLoader.mapElementList.anIntArray444[local117];
                            }
                            if (GlRenderer.enabled) {
                                GlFont.method1188((GlSprite) component.method489(false));
                            } else {
                                SoftwareRaster.method2486(component.compassPixelOffsets, component.compassPixelWidths);
                            }
                            font.renderParagraphAlpha(WorldLoader.mapElementList.text[local117], x + textX + component.width / 2, y + component.height / 2 + -npcZ, local239, 50, local271, 0, 1, 0, 0);
                            if (GlRenderer.enabled) {
                                GlFont.method1173();
                            } else {
                                SoftwareRaster.method2482();
                            }
                        }
                    }
                }
            }
            for (flagX = 0; flagX < locs; flagX++) {
                flagZ = locX[flagX] * 4 + 2 - PlayerList.self.xFine / 32;
                relativeX = locZ[flagX] * 4 + 2 - PlayerList.self.zFine / 32;
                @Pc(382) LocType locType = LocTypeList.get(locId[flagX]);
                if (locType.multiloc != null) {
                    locType = locType.getMultiLoc();
                    if (locType == null || locType.mapfunction == -1) {
                        continue;
                    }
                }
                drawOnMinimap(component, Sprites.mapfuncs[locType.mapfunction], relativeX, flagZ, y, x);
            }
            for (flagX = 0; flagX < 104; flagX++) {
                for (flagZ = 0; flagZ < 104; flagZ++) {
                    @Pc(439) LinkedList objStack = SceneGraph.objStacks[Player.plane][flagX][flagZ];
                    if (objStack != null) {
                        relativeZ = flagX * 4 + 2 - PlayerList.self.xFine / 32;
                        npcX = flagZ * 4 + 2 - PlayerList.self.zFine / 32;
                        drawOnMinimap(component, Sprites.mapdots[0], npcX, relativeZ, y, x);
                    }
                }
            }
            for (flagX = 0; flagX < NpcList.npcCount; flagX++) {
                @Pc(498) Npc npc = NpcList.npcs[NpcList.npcIds[flagX]];
                if (npc != null && npc.isVisible()) {
                    @Pc(507) NpcType npcType = npc.type;
                    if (npcType != null && npcType.multinpc != null) {
                        npcType = npcType.getMultiNPC();
                    }
                    if (npcType != null && npcType.minimap && npcType.active) {
                        relativeZ = npc.xFine / 32 - PlayerList.self.xFine / 32;
                        npcX = npc.zFine / 32 - PlayerList.self.zFine / 32;
                        if (npcType.miniMapMarkerObjectEntry == -1) {
                            drawOnMinimap(component, Sprites.mapdots[1], npcX, relativeZ, y, x);
                        } else {
                            drawOnMinimap(component, Sprites.mapfuncs[npcType.miniMapMarkerObjectEntry], npcX, relativeZ, y, x);
                        }
                    }
                }
            }
            for (flagX = 0; flagX < PlayerList.playerCount; flagX++) {
                @Pc(591) Player player = PlayerList.players[PlayerList.playerIds[flagX]];
                if (player != null && player.isVisible()) {
                    relativeZ = player.zFine / 32 - PlayerList.self.zFine / 32;
                    relativeX = player.xFine / 32 - PlayerList.self.xFine / 32;
                    @Pc(624) long name = player.username.encode37();
                    @Pc(626) boolean isFriend = false;
                    for (local239 = 0; local239 < FriendList.friendCount; local239++) {
                        if (name == FriendList.encodedUsernames[local239] && FriendList.friendWorlds[local239] != 0) {
                            isFriend = true;
                            break;
                        }
                    }
                    @Pc(660) boolean isClanMember = false;
                    for (local271 = 0; local271 < ClanChat.size; local271++) {
                        if (name == ClanChat.members[local271].nodeId) {
                            isClanMember = true;
                            break;
                        }
                    }
                    @Pc(682) boolean isTeammate = false;
                    if (PlayerList.self.teamId != 0 && player.teamId != 0 && player.teamId == PlayerList.self.teamId) {
                        isTeammate = true;
                    }
                    if (isFriend) {
                        drawOnMinimap(component, Sprites.mapdots[3], relativeZ, relativeX, y, x);
                    } else if (isClanMember) {
                        drawOnMinimap(component, Sprites.mapdots[5], relativeZ, relativeX, y, x);
                    } else if (isTeammate) {
                        drawOnMinimap(component, Sprites.mapdots[4], relativeZ, relativeX, y, x);
                    } else {
                        drawOnMinimap(component, Sprites.mapdots[2], relativeZ, relativeX, y, x);
                    }
                }
            }
            @Pc(756) MapMarker[] markers = hintMapMarkers;
            for (flagZ = 0; flagZ < markers.length; flagZ++) {
                @Pc(770) MapMarker marker = markers[flagZ];
                if (marker != null && marker.type != 0 && Client.loop % 20 < 10) {
                    if (marker.type == 1 && marker.actorTargetId >= 0 && marker.actorTargetId < NpcList.npcs.length) {
                        @Pc(804) Npc npc = NpcList.npcs[marker.actorTargetId];
                        if (npc != null) {
                            npcX = npc.xFine / 32 - PlayerList.self.xFine / 32;
                            npcZ = npc.zFine / 32 - PlayerList.self.zFine / 32;
                            drawMinimapMark(marker.anInt4048, y, x, npcX, npcZ, component);
                        }
                    }
                    if (marker.type == 2) {
                        relativeZ = (marker.targetX - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
                        npcX = (-Camera.originZ + marker.anInt4046) * 4 + 2 - PlayerList.self.zFine / 32;
                        drawMinimapMark(marker.anInt4048, y, x, relativeZ, npcX, component);
                    }
                    if (marker.type == 10 && marker.actorTargetId >= 0 && PlayerList.players.length > marker.actorTargetId) {
                        @Pc(905) Player player = PlayerList.players[marker.actorTargetId];
                        if (player != null) {
                            npcZ = player.zFine / 32 - PlayerList.self.zFine / 32;
                            npcX = player.xFine / 32 - PlayerList.self.xFine / 32;
                            drawMinimapMark(marker.anInt4048, y, x, npcX, npcZ, component);
                        }
                    }
                }
            }
            if (LoginManager.mapFlagX != 0) {
                flagX = LoginManager.mapFlagX * 4 + 2 - PlayerList.self.xFine / 32;
                flagZ = LoginManager.mapFlagZ * 4 + 2 - PlayerList.self.zFine / 32;
                drawOnMinimap(component, Sprites.mapflags, flagZ, flagX, y, x);
            }
            if (GlRenderer.enabled) {
                GlRaster.fillRect(x + component.width / 2 - 1, y + -1 - -(component.height / 2), 3, 3, 16777215);
            } else {
                SoftwareRaster.fillRect(component.width / 2 + x - 1, component.height / 2 + -1 + y, 3, 3, 16777215);
            }
        } else if (GlRenderer.enabled) {
            @Pc(1041) Sprite fallbackSprite = component.method489(false);
            if (fallbackSprite != null) {
                fallbackSprite.render(x, y);
            }
        } else {
            SoftwareRaster.method2504(x, y, component.compassPixelOffsets, component.compassPixelWidths);
        }
        ComponentList.rectangleRedraw[rectangle] = true;
    }

    @OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "([IIIIII)V")
    public static void drawMiniMapTile(@OriginalArg(0) int[] destPixels, @OriginalArg(1) int offset, @OriginalArg(3) int plane, @OriginalArg(4) int x, @OriginalArg(5) int z) {
        @Pc(7) Tile tile = SceneGraph.tiles[plane][x][z];
        if (tile == null) {
            return;
        }
        @Pc(13) PlainTile plainTile = tile.plainTile;
        @Pc(23) int row;
        if (plainTile != null) {
            @Pc(18) int color = plainTile.rgbColor;
            if (color != 0) {
                for (row = 0; row < 4; row++) {
                    destPixels[offset] = color;
                    destPixels[offset + 1] = color;
                    destPixels[offset + 2] = color;
                    destPixels[offset + 3] = color;
                    offset += 512;
                }
            }
            return;
        }
        @Pc(58) ShapedTile shapedTile = tile.shapedTile;
        if (shapedTile == null) {
            return;
        }
        row = shapedTile.anInt1966;
        @Pc(67) int rotation = shapedTile.anInt1967;
        @Pc(70) int underlayColor = shapedTile.underlayRGB;
        @Pc(73) int overlayColor = shapedTile.overlayRGB;
        @Pc(77) int[] shapePattern = TILE_SHAPE_PATTERNS[row];
        @Pc(81) int[] rotationPattern = TILE_ROTATION_PATTERNS[rotation];
        @Pc(83) int pointer = 0;
        @Pc(87) int i;
        if (underlayColor != 0) {
            for (i = 0; i < 4; i++) {
                destPixels[offset] = shapePattern[rotationPattern[pointer++]] == 0 ? underlayColor : overlayColor;
                destPixels[offset + 1] = shapePattern[rotationPattern[pointer++]] == 0 ? underlayColor : overlayColor;
                destPixels[offset + 2] = shapePattern[rotationPattern[pointer++]] == 0 ? underlayColor : overlayColor;
                destPixels[offset + 3] = shapePattern[rotationPattern[pointer++]] == 0 ? underlayColor : overlayColor;
                offset += 512;
            }
            return;
        }
        for (i = 0; i < 4; i++) {
            if (shapePattern[rotationPattern[pointer++]] != 0) {
                destPixels[offset] = overlayColor;
            }
            if (shapePattern[rotationPattern[pointer++]] != 0) {
                destPixels[offset + 1] = overlayColor;
            }
            if (shapePattern[rotationPattern[pointer++]] != 0) {
                destPixels[offset + 2] = overlayColor;
            }
            if (shapePattern[rotationPattern[pointer++]] != 0) {
                destPixels[offset + 3] = overlayColor;
            }
            offset += 512;
        }
    }

    @OriginalMember(owner = "runetek4.client!kl", name = "b", descriptor = "(II)Z")
    public static boolean drawMap(@OriginalArg(1) int plane) {
        @Pc(35) int pixelCount;
        @Pc(37) int local37;
        @Pc(76) int local76;
        @Pc(80) int local80;
        if (softwareSprite == null) {
            if (GlRenderer.enabled || sprite == null) {
                softwareSprite = new SoftwareSprite(512, 512);
            } else {
                softwareSprite = (SoftwareSprite) sprite;
            }
            @Pc(32) int[] local32 = softwareSprite.pixels;
            pixelCount = local32.length;
            for (local37 = 0; local37 < pixelCount; local37++) {
                local32[local37] = 1;
            }
            for (local37 = 1; local37 < 103; local37++) {
                local76 = 4 * 512 * (103 - local37) + 24628;
                for (local80 = 1; local80 < 103; local80++) {
                    if ((SceneGraph.renderFlags[plane][local80][local37] & 0x18) == 0) {
                        drawMiniMapTile(local32, local76, plane, local80, local37);
                    }
                    if (plane < 3 && (SceneGraph.renderFlags[plane + 1][local80][local37] & 0x8) != 0) {
                        drawMiniMapTile(local32, local76, plane + 1, local80, local37);
                    }
                    local76 += 4;
                }
            }
            locs = 0;
            for (local37 = 0; local37 < 104; local37++) {
                for (local76 = 0; local76 < 104; local76++) {
                    @Pc(169) long locationKey = SceneGraph.getGroundDecorKey(Player.plane, local37 + 0, local76);
                    if (locationKey != 0L) {
                        @Pc(184) LocType locType = LocTypeList.get((int) (locationKey >>> 32) & Integer.MAX_VALUE);
                        @Pc(187) int mapFunction = locType.mapfunction;
                        @Pc(194) int adjustedX;
                        if (locType.multiloc != null) {
                            for (adjustedX = 0; adjustedX < locType.multiloc.length; adjustedX++) {
                                if (locType.multiloc[adjustedX] != -1) {
                                    @Pc(216) LocType local216 = LocTypeList.get(locType.multiloc[adjustedX]);
                                    if (local216.mapfunction >= 0) {
                                        mapFunction = local216.mapfunction;
                                        break;
                                    }
                                }
                            }
                        }
                        if (mapFunction >= 0) {
                            @Pc(237) int adjustedZ = local76;
                            adjustedX = local37;
                            if (mapFunction != 22 && mapFunction != 29 && mapFunction != 34 && mapFunction != 36 && mapFunction != 46 && mapFunction != 47 && mapFunction != 48) {
                                @Pc(269) int[][] collisionFlags = PathFinder.collisionMaps[Player.plane].flags;
                                for (@Pc(271) int attempt = 0; attempt < 10; attempt++) {
                                    @Pc(281) int direction = (int) (Math.random() * 4.0D);
                                    if (direction == 0 && adjustedX > 0 && local37 - 3 < adjustedX && (collisionFlags[adjustedX - 1][adjustedZ] & 0x12C0108) == 0) {
                                        adjustedX--;
                                    }
                                    if (direction == 1 && adjustedX < 103 && local37 + 3 > adjustedX && (collisionFlags[adjustedX + 1][adjustedZ] & 0x12C0180) == 0) {
                                        adjustedX++;
                                    }
                                    if (direction == 2 && adjustedZ > 0 && local76 - 3 < adjustedZ && (collisionFlags[adjustedX][adjustedZ - 1] & 0x12C0102) == 0) {
                                        adjustedZ--;
                                    }
                                    if (direction == 3 && adjustedZ < 103 && adjustedZ < local76 + 3 && (collisionFlags[adjustedX][adjustedZ + 1] & 0x12C0120) == 0) {
                                        adjustedZ++;
                                    }
                                }
                            }
                            locId[locs] = locType.id;
                            locX[locs] = adjustedX;
                            locZ[locs] = adjustedZ;
                            locs++;
                        }
                    }
                }
            }
        }
        softwareSprite.makeTarget();
        @Pc(455) int wallColor = ((int) (Math.random() * 20.0D) + 238 - 10 << 8) + ((int) (Math.random() * 20.0D) + 238 - 10 << 16) + (int) (Math.random() * 20.0D) + 228;
        pixelCount = (int) (Math.random() * 20.0D) + 238 - 10 << 16;
        for (local37 = 1; local37 < 103; local37++) {
            for (local76 = 1; local76 < 103; local76++) {
                if ((SceneGraph.renderFlags[plane][local76][local37] & 0x18) == 0 && !drawMinimapWalls(local76, wallColor, local37, pixelCount, plane)) {
                    if (GlRenderer.enabled) {
                        SoftwareRaster.pixels = null;
                    } else {
                        SoftwareRaster.frameBuffer.makeTarget();
                    }
                    return false;
                }
                if (plane < 3 && (SceneGraph.renderFlags[plane + 1][local76][local37] & 0x8) != 0 && !drawMinimapWalls(local76, wallColor, local37, pixelCount, plane + 1)) {
                    if (GlRenderer.enabled) {
                        SoftwareRaster.pixels = null;
                    } else {
                        SoftwareRaster.frameBuffer.makeTarget();
                    }
                    return false;
                }
            }
        }
        if (GlRenderer.enabled) {
            @Pc(576) int[] glPixels = softwareSprite.pixels;
            local76 = glPixels.length;
            for (local80 = 0; local80 < local76; local80++) {
                if (glPixels[local80] == 0) {
                    glPixels[local80] = 1;
                }
            }
            sprite = new GlSprite(softwareSprite);
        } else {
            sprite = softwareSprite;
        }
        if (GlRenderer.enabled) {
            SoftwareRaster.pixels = null;
        } else {
            SoftwareRaster.frameBuffer.makeTarget();
        }
        softwareSprite = null;
        return true;
    }

    @OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(IIIIILclient!be;Z)V")
    public static void drawMinimapMark(@OriginalArg(0) int markType, @OriginalArg(1) int y, @OriginalArg(2) int z, @OriginalArg(3) int mapX, @OriginalArg(4) int mapY, @OriginalArg(5) Component component) {
        @Pc(13) int distanceSquared = mapX * mapX + mapY * mapY;
        if (distanceSquared > 360000) {
            return;
        }
        @Pc(30) int radius = Math.min(component.width / 2, component.height / 2);
        if (radius * radius >= distanceSquared) {
            drawOnMinimap(component, Sprites.mapmarkhints[markType], mapY, mapX, y, z);
            return;
        }
        radius -= 10;
        @Pc(58) int totalAngle = minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
        @Pc(62) int cosTotal = MathUtils.cos[totalAngle];
        @Pc(66) int sinTotal = MathUtils.sin[totalAngle];
        @Pc(74) int sinScaled = sinTotal * 256 / (minimapZoom + 256);
        @Pc(82) int cosScaled = cosTotal * 256 / (minimapZoom + 256);
        @Pc(93) int rotatedX = mapY * sinScaled + cosScaled * mapX >> 16;
        @Pc(104) int rotatedY = mapY * cosScaled - sinScaled * mapX >> 16;
        @Pc(110) double edgeAngle = Math.atan2((double) rotatedX, (double) rotatedY);
        @Pc(117) int edgeOffsetX = (int) (Math.sin(edgeAngle) * (double) radius);
        @Pc(124) int edgeOffsetY = (int) (Math.cos(edgeAngle) * (double) radius);
        if (GlRenderer.enabled) {
            ((GlSprite) Sprites.hintMapEdge[markType]).method1428((component.width / 2 + z + edgeOffsetX) * 16, (component.height / 2 + y - edgeOffsetY) * 16, (int) (edgeAngle * 10430.378D));
        } else {
            ((SoftwareSprite) Sprites.hintMapEdge[markType]).method306(edgeOffsetX + component.width / 2 + z - 10, component.height / 2 + -10 + y + -edgeOffsetY, edgeAngle);
        }
    }

    @OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(Lclient!be;B)Lclient!na;")
    public static JString getTargetVerb(@OriginalArg(0) Component component) {
        if (ComponentList.getServerActiveProperties(component).getTargetMask() == 0) {
            return null;
        } else if (component.optionCircumfix == null || component.optionCircumfix.trim().length() == 0) {
            return Cheat.qaOpTest ? HIDDEN_USE : null;
        } else {
            return component.optionCircumfix;
        }
    }

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(ILclient!pb;ZIIII)Z")
	public static boolean drawMapSceneIcon(@OriginalArg(0) int x, @OriginalArg(1) LocType loc, @OriginalArg(5) int z, @OriginalArg(6) int rotation) {
		@Pc(10) MSIType msiType = MSITypeList.get(loc.mapsceneicon);
		if (msiType.spriteId == -1) {
			return true;
		}
		if (loc.mapsceneiconrorate) {
			@Pc(24) int adjustedRotation = rotation + loc.mapsceneiconrotationoffset;
			rotation = adjustedRotation & 0x3;
		} else {
			rotation = 0;
		}
		@Pc(42) SoftwareIndexedSprite iconSprite = msiType.getSprite(rotation);
		if (iconSprite == null) {
			return false;
		}
		@Pc(49) int width = loc.width;
		@Pc(52) int length = loc.length;
		if ((rotation & 0x1) == 1) {
			width = loc.length;
			length = loc.width;
		}
		@Pc(66) int spriteWidth = iconSprite.innerWidth;
		@Pc(69) int spriteHeight = iconSprite.innerHeight;
		if (msiType.aBoolean2) {
			spriteHeight = length * 4;
			spriteWidth = width * 4;
		}
		if (msiType.anInt11 == 0) {
			iconSprite.method1398(x * 4 + 48, (-length + -z + 104) * 4 + 48, spriteWidth, spriteHeight);
		} else {
			iconSprite.method1390(x * 4 + 48, (-length + -z + 104) * 4 + 48, spriteWidth, spriteHeight, msiType.anInt11);
		}
		return true;
	}

    @OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(IIIZIII)V")
    public static void renderOverheadHints(@OriginalArg(0) int cameraX, @OriginalArg(1) int screenX, @OriginalArg(2) int cameraY, @OriginalArg(4) int cameraZ, @OriginalArg(5) int cameraPlane, @OriginalArg(6) int screenY) {
        @Pc(3) int index = 0;
        @Pc(5) MapMarker[] markers = hintMapMarkers;
        while (markers.length > index) {
            @Pc(17) MapMarker marker = markers[index];
            if (marker != null && marker.type == 2) {
                ClientScriptRunner.calculateScreenCoordinates(cameraX >> 1, cameraPlane, (marker.anInt4046 - Camera.originZ << 7) + marker.anInt4047, marker.anInt4050 * 2, cameraY >> 1, marker.anInt4045 + (marker.targetX - Camera.originX << 7), cameraZ);
                if (ClientScriptRunner.overheadScreenX > -1 && Client.loop % 20 < 10) {
                    Sprites.headhints[marker.anInt4048].render(screenX + ClientScriptRunner.overheadScreenX - 12, screenY + -28 - -ClientScriptRunner.overheadScreenY);
                }
            }
            index++;
        }
    }

    @OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IIIIIIIZ)Z")
    public static boolean drawMinimapWalls(@OriginalArg(1) int x, @OriginalArg(2) int wallColor1, @OriginalArg(3) int z, @OriginalArg(4) int wallColor2, @OriginalArg(6) int plane) {
        @Pc(14) long sceneKey = SceneGraph.getWallKey(plane, x + 0, z);
        @Pc(28) int rotation;
        @Pc(35) int shape;
        @Pc(42) int locId;
        @Pc(46) LocType loc;
        @Pc(65) int wallColor;
        @Pc(75) int[] pixels;
        @Pc(90) int pixelOffset;
        if (sceneKey != 0L) {
            rotation = (int) sceneKey >> 20 & 0x3;
            shape = (int) sceneKey >> 14 & 0x1F;
            locId = Integer.MAX_VALUE & (int) (sceneKey >>> 32);
            loc = LocTypeList.get(locId);
            if (loc.mapsceneicon == -1) {
                wallColor = wallColor1;
                if (sceneKey > 0L) {
                    wallColor = wallColor2;
                }
                pixels = SoftwareRaster.pixels;
                pixelOffset = (52736 - z * 512) * 4 + x * 4 + 24624;
                if (shape == LocType.WALL_STRAIGHT || shape == LocType.WALL_L) {
                    if (rotation == 0) {
                        pixels[pixelOffset] = wallColor;
                        pixels[pixelOffset + 512] = wallColor;
                        pixels[pixelOffset + 1024] = wallColor;
                        pixels[pixelOffset + 1536] = wallColor;
                    } else if (rotation == 1) {
                        pixels[pixelOffset] = wallColor;
                        pixels[pixelOffset + 1] = wallColor;
                        pixels[pixelOffset + 2] = wallColor;
                        pixels[pixelOffset + 3] = wallColor;
                    } else if (rotation == 2) {
                        pixels[pixelOffset + 3] = wallColor;
                        pixels[pixelOffset + 3 + 512] = wallColor;
                        pixels[pixelOffset + 3 + 1024] = wallColor;
                        pixels[pixelOffset + 3 + 1536] = wallColor;
                    } else if (rotation == 3) {
                        pixels[pixelOffset + 1536] = wallColor;
                        pixels[pixelOffset + 1536 + 1] = wallColor;
                        pixels[pixelOffset + 1538] = wallColor;
                        pixels[pixelOffset + 3 + 1536] = wallColor;
                    }
                }
                if (shape == LocType.WALL_SQUARE_CORNER) {
                    if (rotation == 0) {
                        pixels[pixelOffset] = wallColor;
                    } else if (rotation == 1) {
                        pixels[pixelOffset + 3] = wallColor;
                    } else if (rotation == 2) {
                        pixels[pixelOffset + 3 + 1536] = wallColor;
                    } else if (rotation == 3) {
                        pixels[pixelOffset + 1536] = wallColor;
                    }
                }
                if (shape == LocType.WALL_L) {
                    if (rotation == 3) {
                        pixels[pixelOffset] = wallColor;
                        pixels[pixelOffset + 512] = wallColor;
                        pixels[pixelOffset + 1024] = wallColor;
                        pixels[pixelOffset + 1536] = wallColor;
                    } else if (rotation == 0) {
                        pixels[pixelOffset] = wallColor;
                        pixels[pixelOffset + 1] = wallColor;
                        pixels[pixelOffset + 2] = wallColor;
                        pixels[pixelOffset + 3] = wallColor;
                    } else if (rotation == 1) {
                        pixels[pixelOffset + 3] = wallColor;
                        pixels[pixelOffset + 512 + 3] = wallColor;
                        pixels[pixelOffset + 1024 + 3] = wallColor;
                        pixels[pixelOffset + 1536 + 3] = wallColor;
                    } else if (rotation == 2) {
                        pixels[pixelOffset + 1536] = wallColor;
                        pixels[pixelOffset + 1536 + 1] = wallColor;
                        pixels[pixelOffset + 1536 + 2] = wallColor;
                        pixels[pixelOffset + 1539] = wallColor;
                    }
                }
            } else if (!drawMapSceneIcon(x, loc, z, rotation)) {
                return false;
            }
        }
        sceneKey = SceneGraph.getSceneryKey(plane, x + 0, z);
        if (sceneKey != 0L) {
            rotation = (int) sceneKey >> 20 & 0x3;
            shape = (int) sceneKey >> 14 & 0x1F;
            locId = (int) (sceneKey >>> 32) & Integer.MAX_VALUE;
            loc = LocTypeList.get(locId);
            if (loc.mapsceneicon == -1) {
                if (shape == LocType.WALL_DIAGONAL) {
                    wallColor = 15658734;
                    if (sceneKey > 0L) {
                        wallColor = 15597568;
                    }
                    pixelOffset = x * 4 + (103 - z) * 2048 + 24624;
                    pixels = SoftwareRaster.pixels;
                    if (rotation == 0 || rotation == 2) {
                        pixels[pixelOffset + 1536] = wallColor;
                        pixels[pixelOffset + 1025] = wallColor;
                        pixels[pixelOffset + 512 + 2] = wallColor;
                        pixels[pixelOffset + 3] = wallColor;
                    } else {
                        pixels[pixelOffset] = wallColor;
                        pixels[pixelOffset + 512 + 1] = wallColor;
                        pixels[pixelOffset + 1024 + 2] = wallColor;
                        pixels[pixelOffset + 1536 + 3] = wallColor;
                    }
                }
            } else if (!drawMapSceneIcon(x, loc, z, rotation)) {
                return false;
            }
        }
        sceneKey = SceneGraph.getGroundDecorKey(plane, x + 0, z);
        if (sceneKey != 0L) {
            rotation = (int) sceneKey >> 20 & 0x3;
            shape = (int) (sceneKey >>> 32) & Integer.MAX_VALUE;
            @Pc(586) LocType groundDecorLoc = LocTypeList.get(shape);
            if (groundDecorLoc.mapsceneicon != -1 && !drawMapSceneIcon(x, groundDecorLoc, z, rotation)) {
                return false;
            }
        }
        return true;
    }

    @OriginalMember(owner = "client!mj", name = "a", descriptor = "(IILclient!be;IB)V")
    public static void renderCompass(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) Component component, @OriginalArg(3) int rectangle) {
        if (GlRenderer.enabled) {
            GlRaster.setClip(x, y, component.width + x, component.height + y);
        }
        if (state >= 3) {
            if (GlRenderer.enabled) {
                @Pc(44) Sprite sprite = component.method489(false);
                if (sprite != null) {
                    sprite.render(x, y);
                }
            } else {
                SoftwareRaster.method2504(x, y, component.compassPixelOffsets, component.compassPixelWidths);
            }
        } else if (GlRenderer.enabled) {
            ((GlSprite) Sprites.compass).renderRotatedTransparent(x, y, component.width, component.height, Sprites.compass.width / 2, Sprites.compass.height / 2, Camera.orbitCameraYaw, 256, (GlSprite) component.method489(false));
        } else {
            ((SoftwareSprite) Sprites.compass).renderRotated(x, y, component.width, component.height, Sprites.compass.width / 2, Sprites.compass.height / 2, Camera.orbitCameraYaw, component.compassPixelOffsets, component.compassPixelWidths);
        }
        ComponentList.rectangleRedraw[rectangle] = true;
    }
}
