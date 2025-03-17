package com.jagex.runetek4.frame;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
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
    public static final Class102[] hintMapMarkers = new Class102[4];
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
                ((GlSprite) sprite).method1427(arg2, arg1, arg3.width, arg3.height, anchorX, anchorY, angle, minimapZoom + 256, (GlSprite) arg3.method489(false));
            } else {
                ((SoftwareSprite) sprite).method310(arg2, arg1, arg3.width, arg3.height, anchorX, anchorY, angle, minimapZoom + 256, arg3.anIntArray37, arg3.anIntArray45);
            }
            @Pc(146) int flagX;
            @Pc(181) int flagZ;
            @Pc(150) int local150;
            @Pc(154) int local154;
            @Pc(231) int npcX;
            @Pc(200) int npcZ;
            @Pc(239) int local239;
            @Pc(271) int local271;
            if (Static235.aMapElementTypeList_2 != null) {
                for (@Pc(117) int local117 = 0; local117 < Static235.aMapElementTypeList_2.anInt5074; local117++) {
                    if (Static235.aMapElementTypeList_2.method3892(local117)) {
                        flagX = (Static235.aMapElementTypeList_2.aShortArray73[local117] - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
                        local150 = MathUtils.sin[angle];
                        local154 = MathUtils.cos[angle];
                        @Pc(156) Font local156 = Fonts.p11Full;
                        @Pc(164) int local164 = local150 * 256 / (minimapZoom + 256);
                        flagZ = (Static235.aMapElementTypeList_2.aShortArray72[local117] - Camera.originZ) * 4 + 2 - PlayerList.self.zFine / 32;
                        @Pc(189) int local189 = local154 * 256 / (minimapZoom + 256);
                        npcZ = flagZ * local189 - flagX * local164 >> 16;
                        if (Static235.aMapElementTypeList_2.method3894(local117) == 1) {
                            local156 = Fonts.p12Full;
                        }
                        if (Static235.aMapElementTypeList_2.method3894(local117) == 2) {
                            local156 = Fonts.b12Full;
                        }
                        npcX = local164 * flagZ + local189 * flagX >> 16;
                        local239 = local156.method2856(Static235.aMapElementTypeList_2.aClass100Array153[local117], 100);
                        @Pc(245) int local245 = npcX - local239 / 2;
                        if (local245 >= -arg3.width && local245 <= arg3.width && npcZ >= -arg3.height && npcZ <= arg3.height) {
                            local271 = 16777215;
                            if (Static235.aMapElementTypeList_2.anIntArray444[local117] != -1) {
                                local271 = Static235.aMapElementTypeList_2.anIntArray444[local117];
                            }
                            if (GlRenderer.enabled) {
                                Static46.method1188((GlSprite) arg3.method489(false));
                            } else {
                                Rasterizer.method2486(arg3.anIntArray37, arg3.anIntArray45);
                            }
                            local156.method2869(Static235.aMapElementTypeList_2.aClass100Array153[local117], arg2 + local245 + arg3.width / 2, arg1 + arg3.height / 2 + -npcZ, local239, 50, local271, 0, 1, 0, 0);
                            if (GlRenderer.enabled) {
                                Static46.method1173();
                            } else {
                                Rasterizer.method2482();
                            }
                        }
                    }
                }
            }
            for (flagX = 0; flagX < Static251.anInt5454; flagX++) {
                flagZ = Static145.anIntArray331[flagX] * 4 + 2 - PlayerList.self.xFine / 32;
                local150 = Static93.anIntArray219[flagX] * 4 + 2 - PlayerList.self.zFine / 32;
                @Pc(382) LocType local382 = LocTypeList.get(Static199.anIntArray417[flagX]);
                if (local382.multiloc != null) {
                    local382 = local382.getMultiLoc();
                    if (local382 == null || local382.mapfunction == -1) {
                        continue;
                    }
                }
                drawOnMinimap(arg3, Static67.aClass3_Sub2_Sub1Array4[local382.mapfunction], local150, flagZ, arg1, arg2);
            }
            for (flagX = 0; flagX < 104; flagX++) {
                for (flagZ = 0; flagZ < 104; flagZ++) {
                    @Pc(439) LinkedList local439 = SceneGraph.objStacks[Player.plane][flagX][flagZ];
                    if (local439 != null) {
                        local154 = flagX * 4 + 2 - PlayerList.self.xFine / 32;
                        npcX = flagZ * 4 + 2 - PlayerList.self.zFine / 32;
                        drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[0], npcX, local154, arg1, arg2);
                    }
                }
            }
            for (flagX = 0; flagX < NpcList.npcCount; flagX++) {
                @Pc(498) Npc local498 = NpcList.npcs[Static33.npcIds[flagX]];
                if (local498 != null && local498.isVisible()) {
                    @Pc(507) NpcType local507 = local498.type;
                    if (local507 != null && local507.multiNpcs != null) {
                        local507 = local507.getMultiNPC();
                    }
                    if (local507 != null && local507.minimap && local507.active) {
                        local154 = local498.xFine / 32 - PlayerList.self.xFine / 32;
                        npcX = local498.zFine / 32 - PlayerList.self.zFine / 32;
                        if (local507.anInt3739 == -1) {
                            drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[1], npcX, local154, arg1, arg2);
                        } else {
                            drawOnMinimap(arg3, Static67.aClass3_Sub2_Sub1Array4[local507.anInt3739], npcX, local154, arg1, arg2);
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
                        if (name == Static92.friendName37[local239] && Static104.friendWorld[local239] != 0) {
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
                        drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[3], local154, local150, arg1, arg2);
                    } else if (local660) {
                        drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[5], local154, local150, arg1, arg2);
                    } else if (isTeammate) {
                        drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[4], local154, local150, arg1, arg2);
                    } else {
                        drawOnMinimap(arg3, Static139.aClass3_Sub2_Sub1Array6[2], local154, local150, arg1, arg2);
                    }
                }
            }
            @Pc(756) Class102[] local756 = hintMapMarkers;
            for (flagZ = 0; flagZ < local756.length; flagZ++) {
                @Pc(770) Class102 local770 = local756[flagZ];
                if (local770 != null && local770.headIconDrawType != 0 && client.loop % 20 < 10) {
                    if (local770.headIconDrawType == 1 && local770.hintIconNpcTarget >= 0 && local770.hintIconNpcTarget < NpcList.npcs.length) {
                        @Pc(804) Npc npc = NpcList.npcs[local770.hintIconNpcTarget];
                        if (npc != null) {
                            npcX = npc.xFine / 32 - PlayerList.self.xFine / 32;
                            npcZ = npc.zFine / 32 - PlayerList.self.zFine / 32;
                            Static97.drawMinimapMark(local770.anInt4048, arg1, arg2, npcX, npcZ, arg3);
                        }
                    }
                    if (local770.headIconDrawType == 2) {
                        local154 = (local770.anInt4053 - Camera.originX) * 4 + 2 - PlayerList.self.xFine / 32;
                        npcX = (-Camera.originZ + local770.anInt4046) * 4 + 2 - PlayerList.self.zFine / 32;
                        Static97.drawMinimapMark(local770.anInt4048, arg1, arg2, local154, npcX, arg3);
                    }
                    if (local770.headIconDrawType == 10 && local770.hintIconNpcTarget >= 0 && PlayerList.players.length > local770.hintIconNpcTarget) {
                        @Pc(905) Player player = PlayerList.players[local770.hintIconNpcTarget];
                        if (player != null) {
                            npcZ = player.zFine / 32 - PlayerList.self.zFine / 32;
                            npcX = player.xFine / 32 - PlayerList.self.xFine / 32;
                            Static97.drawMinimapMark(local770.anInt4048, arg1, arg2, npcX, npcZ, arg3);
                        }
                    }
                }
            }
            if (LoginManager.mapFlagX != 0) {
                flagX = LoginManager.mapFlagX * 4 + 2 - PlayerList.self.xFine / 32;
                flagZ = LoginManager.mapFlagZ * 4 + 2 - PlayerList.self.zFine / 32;
                drawOnMinimap(arg3, Static84.aClass3_Sub2_Sub1_4, flagZ, flagX, arg1, arg2);
            }
            if (GlRenderer.enabled) {
                GlRaster.fillRect(arg2 + arg3.width / 2 - 1, arg1 + -1 - -(arg3.height / 2), 3, 3, 16777215);
            } else {
                SoftwareRaster.fillRect(arg3.width / 2 + arg2 - 1, arg3.height / 2 + -1 + arg1, 3, 3, 16777215);
            }
        } else if (GlRenderer.enabled) {
            @Pc(1041) Sprite local1041 = arg3.method489(false);
            if (local1041 != null) {
                local1041.drawSprite(arg2, arg1);
            }
        } else {
            Rasterizer.method2504(arg2, arg1, arg3.anIntArray37, arg3.anIntArray45);
        }
        InterfaceList.rectangleRedraw[arg0] = true;
    }
}
