package com.jagex.runetek4;

import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.client.client;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.msi.MSITypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.config.types.msi.MSIType;
import com.jagex.runetek4.scene.tile.PlainTile;
import com.jagex.runetek4.scene.tile.ShapedTile;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MiniMap {

    @OriginalMember(owner = "runetek4.client!wc", name = "h", descriptor = "[[I")
    public static final int[][] anIntArrayArray46 = new int[][] { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 }, { 12, 8, 4, 0, 13, 9, 5, 1, 14, 10, 6, 2, 15, 11, 7, 3 }, { 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 }, { 3, 7, 11, 15, 2, 6, 10, 14, 1, 5, 9, 13, 0, 4, 8, 12 } };

    @OriginalMember(owner = "runetek4.client!ke", name = "T", descriptor = "[[I")
    public static final int[][] anIntArrayArray24 = new int[][] { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0 }, { 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1 }, { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 1 } };

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
    public static void drawOnMinimap(@OriginalArg(0) Component arg0, @OriginalArg(1) Sprite sprite, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
        if (sprite == null) {
            return;
        }
        @Pc(21) int local21 = arg3 * arg3 + arg2 * arg2;
        @Pc(27) int local27 = minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
        @Pc(39) int local39 = Math.max(arg0.width / 2, arg0.height / 2) + 10;
        if (local39 * local39 < local21) {
            return;
        }
        @Pc(50) int local50 = MathUtils.sin[local27];
        @Pc(58) int local58 = local50 * 256 / (minimapZoom + 256);
        @Pc(62) int local62 = MathUtils.cos[local27];
        @Pc(70) int local70 = local62 * 256 / (minimapZoom + 256);
        @Pc(81) int local81 = local58 * arg2 + arg3 * local70 >> 16;
        @Pc(92) int local92 = local70 * arg2 - arg3 * local58 >> 16;
        if (GlRenderer.enabled) {
            ((GlSprite) sprite).method1425(arg0.width / 2 + arg5 + local81 - sprite.innerWidth / 2, arg0.height / 2 + arg4 - (local92 + sprite.innerHeight / 2), (GlSprite) arg0.method489(false));
        } else {
            ((SoftwareSprite) sprite).drawImage(arg0.width / 2 + arg5 + local81 - sprite.innerWidth / 2, -(sprite.innerHeight / 2) + arg0.height / 2 + arg4 + -local92, arg0.anIntArray37, arg0.anIntArray45);
        }
    }

    @OriginalMember(owner = "runetek4.client!ed", name = "a", descriptor = "(IBIILclient!be;)V")
    public static void render(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) Component arg3) {
        client.audioLoop();
        if (GlRenderer.enabled) {
            GlRaster.setClip(arg2, arg1, arg2 + arg3.width, arg1 + arg3.height);
        } else {
            SoftwareRaster.setClip(arg2, arg1, arg2 + arg3.width, arg1 + arg3.height);
        }
        if (state != 2 && state != 5 && sprite != null) {
            @Pc(48) int angle = minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
            @Pc(57) int anchorX = PlayerList.self.xFine / 32 + 48;
            @Pc(67) int anchorY = 464 - PlayerList.self.zFine / 32;
            if (GlRenderer.enabled) {
                ((GlSprite) sprite).renderRotatedTransparent(arg2, arg1, arg3.width, arg3.height, anchorX, anchorY, angle, minimapZoom + 256, (GlSprite) arg3.method489(false));
            } else {
                ((SoftwareSprite) sprite).renderRotated(arg2, arg1, arg3.width, arg3.height, anchorX, anchorY, angle, minimapZoom + 256, arg3.anIntArray37, arg3.anIntArray45);
            }
            @Pc(146) int flagX;
            @Pc(181) int flagZ;
            @Pc(150) int local150;
            @Pc(154) int local154;
            @Pc(231) int npcX;
            @Pc(200) int npcZ;
            @Pc(239) int local239;
            @Pc(271) int local271;
            if (LoginManager.mapElementList != null) {
                for (@Pc(117) int local117 = 0; local117 < LoginManager.mapElementList.anInt5074; local117++) {
                    if (LoginManager.mapElementList.method3892(local117)) {
                        flagX = (LoginManager.mapElementList.aShortArray73[local117] - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
                        local150 = MathUtils.sin[angle];
                        local154 = MathUtils.cos[angle];
                        @Pc(156) Font local156 = Fonts.p11Full;
                        @Pc(164) int local164 = local150 * 256 / (minimapZoom + 256);
                        flagZ = (LoginManager.mapElementList.aShortArray72[local117] - Camera.originZ) * 4 + 2 - PlayerList.self.zFine / 32;
                        @Pc(189) int local189 = local154 * 256 / (minimapZoom + 256);
                        npcZ = flagZ * local189 - flagX * local164 >> 16;
                        if (LoginManager.mapElementList.method3894(local117) == 1) {
                            local156 = Fonts.p12Full;
                        }
                        if (LoginManager.mapElementList.method3894(local117) == 2) {
                            local156 = Fonts.b12Full;
                        }
                        npcX = local164 * flagZ + local189 * flagX >> 16;
                        local239 = local156.getMaxLineWidth(LoginManager.mapElementList.aClass100Array153[local117], 100);
                        @Pc(245) int local245 = npcX - local239 / 2;
                        if (local245 >= -arg3.width && local245 <= arg3.width && npcZ >= -arg3.height && npcZ <= arg3.height) {
                            local271 = 16777215;
                            if (LoginManager.mapElementList.anIntArray444[local117] != -1) {
                                local271 = LoginManager.mapElementList.anIntArray444[local117];
                            }
                            if (GlRenderer.enabled) {
                                GlFont.method1188((GlSprite) arg3.method489(false));
                            } else {
                                SoftwareRaster.method2486(arg3.anIntArray37, arg3.anIntArray45);
                            }
                            local156.renderParagraphAlpha(LoginManager.mapElementList.aClass100Array153[local117], arg2 + local245 + arg3.width / 2, arg1 + arg3.height / 2 + -npcZ, local239, 50, local271, 0, 1, 0, 0);
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
                local150 = locZ[flagX] * 4 + 2 - PlayerList.self.zFine / 32;
                @Pc(382) LocType local382 = LocTypeList.get(locId[flagX]);
                if (local382.multiloc != null) {
                    local382 = local382.getMultiLoc();
                    if (local382 == null || local382.mapfunction == -1) {
                        continue;
                    }
                }
                drawOnMinimap(arg3, Sprites.mapfuncs[local382.mapfunction], local150, flagZ, arg1, arg2);
            }
            for (flagX = 0; flagX < 104; flagX++) {
                for (flagZ = 0; flagZ < 104; flagZ++) {
                    @Pc(439) LinkedList local439 = SceneGraph.objStacks[Player.plane][flagX][flagZ];
                    if (local439 != null) {
                        local154 = flagX * 4 + 2 - PlayerList.self.xFine / 32;
                        npcX = flagZ * 4 + 2 - PlayerList.self.zFine / 32;
                        drawOnMinimap(arg3, Sprites.mapdots[0], npcX, local154, arg1, arg2);
                    }
                }
            }
            for (flagX = 0; flagX < NpcList.npcCount; flagX++) {
                @Pc(498) Npc local498 = NpcList.npcs[NpcList.npcIds[flagX]];
                if (local498 != null && local498.isVisible()) {
                    @Pc(507) NpcType local507 = local498.type;
                    if (local507 != null && local507.multiNpcs != null) {
                        local507 = local507.getMultiNPC();
                    }
                    if (local507 != null && local507.miniMapDisplay && local507.interactive) {
                        local154 = local498.xFine / 32 - PlayerList.self.xFine / 32;
                        npcX = local498.zFine / 32 - PlayerList.self.zFine / 32;
                        if (local507.miniMapMarkerObjectEntry == -1) {
                            drawOnMinimap(arg3, Sprites.mapdots[1], npcX, local154, arg1, arg2);
                        } else {
                            drawOnMinimap(arg3, Sprites.mapfuncs[local507.miniMapMarkerObjectEntry], npcX, local154, arg1, arg2);
                        }
                    }
                }
            }
            for (flagX = 0; flagX < PlayerList.playerCount; flagX++) {
                @Pc(591) Player local591 = PlayerList.players[PlayerList.playerIds[flagX]];
                if (local591 != null && local591.isVisible()) {
                    local154 = local591.zFine / 32 - PlayerList.self.zFine / 32;
                    local150 = local591.xFine / 32 - PlayerList.self.xFine / 32;
                    @Pc(624) long name = local591.username.encode37();
                    @Pc(626) boolean isFriend = false;
                    for (local239 = 0; local239 < FriendList.friendCount; local239++) {
                        if (name == FriendList.encodedUsernames[local239] && FriendList.friendWorlds[local239] != 0) {
                            isFriend = true;
                            break;
                        }
                    }
                    @Pc(660) boolean local660 = false;
                    for (local271 = 0; local271 < ClanChat.size; local271++) {
                        if (name == ClanChat.members[local271].nodeId) {
                            local660 = true;
                            break;
                        }
                    }
                    @Pc(682) boolean isTeammate = false;
                    if (PlayerList.self.teamId != 0 && local591.teamId != 0 && local591.teamId == PlayerList.self.teamId) {
                        isTeammate = true;
                    }
                    if (isFriend) {
                        drawOnMinimap(arg3, Sprites.mapdots[3], local154, local150, arg1, arg2);
                    } else if (local660) {
                        drawOnMinimap(arg3, Sprites.mapdots[5], local154, local150, arg1, arg2);
                    } else if (isTeammate) {
                        drawOnMinimap(arg3, Sprites.mapdots[4], local154, local150, arg1, arg2);
                    } else {
                        drawOnMinimap(arg3, Sprites.mapdots[2], local154, local150, arg1, arg2);
                    }
                }
            }
            @Pc(756) MapMarker[] local756 = hintMapMarkers;
            for (flagZ = 0; flagZ < local756.length; flagZ++) {
                @Pc(770) MapMarker local770 = local756[flagZ];
                if (local770 != null && local770.type != 0 && client.loop % 20 < 10) {
                    if (local770.type == 1 && local770.actorTargetId >= 0 && local770.actorTargetId < NpcList.npcs.length) {
                        @Pc(804) Npc npc = NpcList.npcs[local770.actorTargetId];
                        if (npc != null) {
                            npcX = npc.xFine / 32 - PlayerList.self.xFine / 32;
                            npcZ = npc.zFine / 32 - PlayerList.self.zFine / 32;
                            drawMinimapMark(local770.anInt4048, arg1, arg2, npcX, npcZ, arg3);
                        }
                    }
                    if (local770.type == 2) {
                        local154 = (local770.targetX - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
                        npcX = (-Camera.originZ + local770.anInt4046) * 4 + 2 - PlayerList.self.zFine / 32;
                        drawMinimapMark(local770.anInt4048, arg1, arg2, local154, npcX, arg3);
                    }
                    if (local770.type == 10 && local770.actorTargetId >= 0 && PlayerList.players.length > local770.actorTargetId) {
                        @Pc(905) Player player = PlayerList.players[local770.actorTargetId];
                        if (player != null) {
                            npcZ = player.zFine / 32 - PlayerList.self.zFine / 32;
                            npcX = player.xFine / 32 - PlayerList.self.xFine / 32;
                            drawMinimapMark(local770.anInt4048, arg1, arg2, npcX, npcZ, arg3);
                        }
                    }
                }
            }
            if (LoginManager.mapFlagX != 0) {
                flagX = LoginManager.mapFlagX * 4 + 2 - PlayerList.self.xFine / 32;
                flagZ = LoginManager.mapFlagZ * 4 + 2 - PlayerList.self.zFine / 32;
                drawOnMinimap(arg3, Sprites.mapflags, flagZ, flagX, arg1, arg2);
            }
            if (GlRenderer.enabled) {
                GlRaster.fillRect(arg2 + arg3.width / 2 - 1, arg1 + -1 - -(arg3.height / 2), 3, 3, 16777215);
            } else {
                SoftwareRaster.fillRect(arg3.width / 2 + arg2 - 1, arg3.height / 2 + -1 + arg1, 3, 3, 16777215);
            }
        } else if (GlRenderer.enabled) {
            @Pc(1041) Sprite local1041 = arg3.method489(false);
            if (local1041 != null) {
                local1041.render(arg2, arg1);
            }
        } else {
            SoftwareRaster.method2504(arg2, arg1, arg3.anIntArray37, arg3.anIntArray45);
        }
        InterfaceList.rectangleRedraw[arg0] = true;
    }

    @OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "([IIIIII)V")
    public static void drawMiniMapTile(@OriginalArg(0) int[] destPixels, @OriginalArg(1) int offset, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
        @Pc(7) Tile tile = SceneGraph.tiles[arg2][arg3][arg4];
        if (tile == null) {
            return;
        }
        @Pc(13) PlainTile plainTile = tile.plainTile;
        @Pc(23) int local23;
        if (plainTile != null) {
            @Pc(18) int color = plainTile.rgbColor;
            if (color != 0) {
                for (local23 = 0; local23 < 4; local23++) {
                    destPixels[offset] = color;
                    destPixels[offset + 1] = color;
                    destPixels[offset + 2] = color;
                    destPixels[offset + 3] = color;
                    offset += 512;
                }
            }
            return;
        }
        @Pc(58) ShapedTile local58 = tile.shapedTile;
        if (local58 == null) {
            return;
        }
        local23 = local58.anInt1966;
        @Pc(67) int rotation = local58.anInt1967;
        @Pc(70) int underlayColor = local58.underlayRGB;
        @Pc(73) int overlayColor = local58.overlayRGB;
        @Pc(77) int[] local77 = anIntArrayArray24[local23];
        @Pc(81) int[] local81 = anIntArrayArray46[rotation];
        @Pc(83) int pointer = 0;
        @Pc(87) int i;
        if (underlayColor != 0) {
            for (i = 0; i < 4; i++) {
                destPixels[offset] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
                destPixels[offset + 1] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
                destPixels[offset + 2] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
                destPixels[offset + 3] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
                offset += 512;
            }
            return;
        }
        for (i = 0; i < 4; i++) {
            if (local77[local81[pointer++]] != 0) {
                destPixels[offset] = overlayColor;
            }
            if (local77[local81[pointer++]] != 0) {
                destPixels[offset + 1] = overlayColor;
            }
            if (local77[local81[pointer++]] != 0) {
                destPixels[offset + 2] = overlayColor;
            }
            if (local77[local81[pointer++]] != 0) {
                destPixels[offset + 3] = overlayColor;
            }
            offset += 512;
        }
    }

    @OriginalMember(owner = "runetek4.client!kl", name = "b", descriptor = "(II)Z")
    public static boolean drawMap(@OriginalArg(1) int arg0) {
        @Pc(35) int local35;
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
            local35 = local32.length;
            for (local37 = 0; local37 < local35; local37++) {
                local32[local37] = 1;
            }
            for (local37 = 1; local37 < 103; local37++) {
                local76 = 4 * 512 * (103 - local37) + 24628;
                for (local80 = 1; local80 < 103; local80++) {
                    if ((SceneGraph.renderFlags[arg0][local80][local37] & 0x18) == 0) {
                        drawMiniMapTile(local32, local76, arg0, local80, local37);
                    }
                    if (arg0 < 3 && (SceneGraph.renderFlags[arg0 + 1][local80][local37] & 0x8) != 0) {
                        drawMiniMapTile(local32, local76, arg0 + 1, local80, local37);
                    }
                    local76 += 4;
                }
            }
            locs = 0;
            for (local37 = 0; local37 < 104; local37++) {
                for (local76 = 0; local76 < 104; local76++) {
                    @Pc(169) long local169 = SceneGraph.getGroundDecorKey(Player.plane, local37 + 0, local76);
                    if (local169 != 0L) {
                        @Pc(184) LocType local184 = LocTypeList.get((int) (local169 >>> 32) & Integer.MAX_VALUE);
                        @Pc(187) int local187 = local184.mapfunction;
                        @Pc(194) int local194;
                        if (local184.multiloc != null) {
                            for (local194 = 0; local194 < local184.multiloc.length; local194++) {
                                if (local184.multiloc[local194] != -1) {
                                    @Pc(216) LocType local216 = LocTypeList.get(local184.multiloc[local194]);
                                    if (local216.mapfunction >= 0) {
                                        local187 = local216.mapfunction;
                                        break;
                                    }
                                }
                            }
                        }
                        if (local187 >= 0) {
                            @Pc(237) int local237 = local76;
                            local194 = local37;
                            if (local187 != 22 && local187 != 29 && local187 != 34 && local187 != 36 && local187 != 46 && local187 != 47 && local187 != 48) {
                                @Pc(269) int[][] local269 = PathFinder.collisionMaps[Player.plane].flags;
                                for (@Pc(271) int local271 = 0; local271 < 10; local271++) {
                                    @Pc(281) int local281 = (int) (Math.random() * 4.0D);
                                    if (local281 == 0 && local194 > 0 && local37 - 3 < local194 && (local269[local194 - 1][local237] & 0x12C0108) == 0) {
                                        local194--;
                                    }
                                    if (local281 == 1 && local194 < 103 && local37 + 3 > local194 && (local269[local194 + 1][local237] & 0x12C0180) == 0) {
                                        local194++;
                                    }
                                    if (local281 == 2 && local237 > 0 && local76 - 3 < local237 && (local269[local194][local237 - 1] & 0x12C0102) == 0) {
                                        local237--;
                                    }
                                    if (local281 == 3 && local237 < 103 && local237 < local76 + 3 && (local269[local194][local237 + 1] & 0x12C0120) == 0) {
                                        local237++;
                                    }
                                }
                            }
                            locId[locs] = local184.id;
                            locX[locs] = local194;
                            locZ[locs] = local237;
                            locs++;
                        }
                    }
                }
            }
        }
        softwareSprite.makeTarget();
        @Pc(455) int local455 = ((int) (Math.random() * 20.0D) + 238 - 10 << 8) + ((int) (Math.random() * 20.0D) + 238 - 10 << 16) + (int) (Math.random() * 20.0D) + 228;
        local35 = (int) (Math.random() * 20.0D) + 238 - 10 << 16;
        for (local37 = 1; local37 < 103; local37++) {
            for (local76 = 1; local76 < 103; local76++) {
                if ((SceneGraph.renderFlags[arg0][local76][local37] & 0x18) == 0 && !method3109(local76, local455, local37, local35, arg0)) {
                    if (GlRenderer.enabled) {
                        SoftwareRaster.pixels = null;
                    } else {
                        SoftwareRaster.frameBuffer.makeTarget();
                    }
                    return false;
                }
                if (arg0 < 3 && (SceneGraph.renderFlags[arg0 + 1][local76][local37] & 0x8) != 0 && !method3109(local76, local455, local37, local35, arg0 + 1)) {
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
            @Pc(576) int[] local576 = softwareSprite.pixels;
            local76 = local576.length;
            for (local80 = 0; local80 < local76; local80++) {
                if (local576[local80] == 0) {
                    local576[local80] = 1;
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
    public static void drawMinimapMark(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int mapX, @OriginalArg(4) int mapY, @OriginalArg(5) Component arg5) {
        @Pc(13) int len = mapX * mapX + mapY * mapY;
        if (len > 360000) {
            return;
        }
        @Pc(30) int local30 = Math.min(arg5.width / 2, arg5.height / 2);
        if (local30 * local30 >= len) {
            drawOnMinimap(arg5, Sprites.mapmarkhints[arg0], mapY, mapX, arg1, arg2);
            return;
        }
        local30 -= 10;
        @Pc(58) int local58 = minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
        @Pc(62) int local62 = MathUtils.cos[local58];
        @Pc(66) int local66 = MathUtils.sin[local58];
        @Pc(74) int local74 = local66 * 256 / (minimapZoom + 256);
        @Pc(82) int local82 = local62 * 256 / (minimapZoom + 256);
        @Pc(93) int local93 = mapY * local74 + local82 * mapX >> 16;
        @Pc(104) int local104 = mapY * local82 - local74 * mapX >> 16;
        @Pc(110) double angle = Math.atan2((double) local93, (double) local104);
        @Pc(117) int sine = (int) (Math.sin(angle) * (double) local30);
        @Pc(124) int cosine = (int) (Math.cos(angle) * (double) local30);
        if (GlRenderer.enabled) {
            ((GlSprite) Sprites.hintMapEdge[arg0]).method1428((arg5.width / 2 + arg2 + sine) * 16, (arg5.height / 2 + arg1 - cosine) * 16, (int) (angle * 10430.378D));
        } else {
            ((SoftwareSprite) Sprites.hintMapEdge[arg0]).method306(sine + arg5.width / 2 + arg2 - 10, arg5.height / 2 + -10 + arg1 + -cosine, angle);
        }
    }

    @OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(Lclient!be;B)Lclient!na;")
    public static JString getTargetVerb(@OriginalArg(0) Component arg0) {
        if (InterfaceList.getServerActiveProperties(arg0).getTargetMask() == 0) {
            return null;
        } else if (arg0.optionCircumfix == null || arg0.optionCircumfix.trim().length() == 0) {
            return Cheat.qaOpTest ? HIDDEN_USE : null;
        } else {
            return arg0.optionCircumfix;
        }
    }

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(ILclient!pb;ZIIII)Z")
	public static boolean renderScenery(@OriginalArg(0) int arg0, @OriginalArg(1) LocType arg1, @OriginalArg(5) int arg2, @OriginalArg(6) int arg3) {
		@Pc(10) MSIType msiType = MSITypeList.get(arg1.mapSceneIcon);
		if (msiType.spriteId == -1) {
			return true;
		}
		if (arg1.mapSceneRotated) {
			@Pc(24) int local24 = arg3 + arg1.mapSceneAngleOffset;
			arg3 = local24 & 0x3;
		} else {
			arg3 = 0;
		}
		@Pc(42) SoftwareIndexedSprite local42 = msiType.getSprite(arg3);
		if (local42 == null) {
			return false;
		}
		@Pc(49) int local49 = arg1.width;
		@Pc(52) int local52 = arg1.length;
		if ((arg3 & 0x1) == 1) {
			local49 = arg1.length;
			local52 = arg1.width;
		}
		@Pc(66) int local66 = local42.innerWidth;
		@Pc(69) int local69 = local42.innerHeight;
		if (msiType.aBoolean2) {
			local69 = local52 * 4;
			local66 = local49 * 4;
		}
		if (msiType.anInt11 == 0) {
			local42.method1398(arg0 * 4 + 48, (-local52 + -arg2 + 104) * 4 + 48, local66, local69);
		} else {
			local42.method1390(arg0 * 4 + 48, (-local52 + -arg2 + 104) * 4 + 48, local66, local69, msiType.anInt11);
		}
		return true;
	}

    @OriginalMember(owner = "runetek4.client!t", name = "a", descriptor = "(IIIZIII)V")
    public static void method4000(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
        @Pc(3) int local3 = 0;
        @Pc(5) MapMarker[] local5 = hintMapMarkers;
        while (local5.length > local3) {
            @Pc(17) MapMarker local17 = local5[local3];
            if (local17 != null && local17.type == 2) {
                ClientScriptRunner.method1026(arg0 >> 1, arg4, (local17.anInt4046 - Camera.originZ << 7) + local17.anInt4047, local17.anInt4050 * 2, arg2 >> 1, local17.anInt4045 + (local17.targetX - Camera.originX << 7), arg3);
                if (ClientScriptRunner.anInt1951 > -1 && client.loop % 20 < 10) {
                    Sprites.headhints[local17.anInt4048].render(arg1 + ClientScriptRunner.anInt1951 - 12, arg5 + -28 - -ClientScriptRunner.anInt548);
                }
            }
            local3++;
        }
    }

    @OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(IIIIIIIZ)Z")
    public static boolean method3109(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(6) int arg4) {
        @Pc(14) long local14 = SceneGraph.getWallKey(arg4, arg0 + 0, arg2);
        @Pc(28) int local28;
        @Pc(35) int local35;
        @Pc(42) int local42;
        @Pc(46) LocType local46;
        @Pc(65) int local65;
        @Pc(75) int[] local75;
        @Pc(90) int local90;
        if (local14 != 0L) {
            local28 = (int) local14 >> 20 & 0x3;
            local35 = (int) local14 >> 14 & 0x1F;
            local42 = Integer.MAX_VALUE & (int) (local14 >>> 32);
            local46 = LocTypeList.get(local42);
            if (local46.mapSceneIcon == -1) {
                local65 = arg1;
                if (local14 > 0L) {
                    local65 = arg3;
                }
                local75 = SoftwareRaster.pixels;
                local90 = (52736 - arg2 * 512) * 4 + arg0 * 4 + 24624;
                if (local35 == 0 || local35 == 2) {
                    if (local28 == 0) {
                        local75[local90] = local65;
                        local75[local90 + 512] = local65;
                        local75[local90 + 1024] = local65;
                        local75[local90 + 1536] = local65;
                    } else if (local28 == 1) {
                        local75[local90] = local65;
                        local75[local90 + 1] = local65;
                        local75[local90 + 2] = local65;
                        local75[local90 + 3] = local65;
                    } else if (local28 == 2) {
                        local75[local90 + 3] = local65;
                        local75[local90 + 3 + 512] = local65;
                        local75[local90 + 3 + 1024] = local65;
                        local75[local90 + 3 + 1536] = local65;
                    } else if (local28 == 3) {
                        local75[local90 + 1536] = local65;
                        local75[local90 + 1536 + 1] = local65;
                        local75[local90 + 1538] = local65;
                        local75[local90 + 3 + 1536] = local65;
                    }
                }
                if (local35 == 3) {
                    if (local28 == 0) {
                        local75[local90] = local65;
                    } else if (local28 == 1) {
                        local75[local90 + 3] = local65;
                    } else if (local28 == 2) {
                        local75[local90 + 3 + 1536] = local65;
                    } else if (local28 == 3) {
                        local75[local90 + 1536] = local65;
                    }
                }
                if (local35 == 2) {
                    if (local28 == 3) {
                        local75[local90] = local65;
                        local75[local90 + 512] = local65;
                        local75[local90 + 1024] = local65;
                        local75[local90 + 1536] = local65;
                    } else if (local28 == 0) {
                        local75[local90] = local65;
                        local75[local90 + 1] = local65;
                        local75[local90 + 2] = local65;
                        local75[local90 + 3] = local65;
                    } else if (local28 == 1) {
                        local75[local90 + 3] = local65;
                        local75[local90 + 512 + 3] = local65;
                        local75[local90 + 1024 + 3] = local65;
                        local75[local90 + 1536 + 3] = local65;
                    } else if (local28 == 2) {
                        local75[local90 + 1536] = local65;
                        local75[local90 + 1536 + 1] = local65;
                        local75[local90 + 1536 + 2] = local65;
                        local75[local90 + 1539] = local65;
                    }
                }
            } else if (!renderScenery(arg0, local46, arg2, local28)) {
                return false;
            }
        }
        local14 = SceneGraph.getSceneryKey(arg4, arg0 + 0, arg2);
        if (local14 != 0L) {
            local28 = (int) local14 >> 20 & 0x3;
            local35 = (int) local14 >> 14 & 0x1F;
            local42 = (int) (local14 >>> 32) & Integer.MAX_VALUE;
            local46 = LocTypeList.get(local42);
            if (local46.mapSceneIcon == -1) {
                if (local35 == 9) {
                    local65 = 15658734;
                    if (local14 > 0L) {
                        local65 = 15597568;
                    }
                    local90 = arg0 * 4 + (103 - arg2) * 2048 + 24624;
                    local75 = SoftwareRaster.pixels;
                    if (local28 == 0 || local28 == 2) {
                        local75[local90 + 1536] = local65;
                        local75[local90 + 1025] = local65;
                        local75[local90 + 512 + 2] = local65;
                        local75[local90 + 3] = local65;
                    } else {
                        local75[local90] = local65;
                        local75[local90 + 512 + 1] = local65;
                        local75[local90 + 1024 + 2] = local65;
                        local75[local90 + 1536 + 3] = local65;
                    }
                }
            } else if (!renderScenery(arg0, local46, arg2, local28)) {
                return false;
            }
        }
        local14 = SceneGraph.getGroundDecorKey(arg4, arg0 + 0, arg2);
        if (local14 != 0L) {
            local28 = (int) local14 >> 20 & 0x3;
            local35 = (int) (local14 >>> 32) & Integer.MAX_VALUE;
            @Pc(586) LocType local586 = LocTypeList.get(local35);
            if (local586.mapSceneIcon != -1 && !renderScenery(arg0, local586, arg2, local28)) {
                return false;
            }
        }
        return true;
    }
}
