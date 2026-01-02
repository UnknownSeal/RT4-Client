package com.jagex.game.world;

import com.jagex.game.runetek4.config.flotype.FloorOverlayType;
import com.jagex.game.runetek4.config.flotype.FloorOverlayTypeList;
import com.jagex.cache.media.SoftwareSprite;
import com.jagex.ui.component.Component;
import com.jagex.game.runetek4.client.GameShell;
import com.jagex.client.Client;
import com.jagex.game.runetek4.config.flutype.FloorUnderlayType;
import com.jagex.game.runetek4.config.flutype.FloorUnderlayTypeList;
import com.jagex.game.runetek4.config.loctype.LocTypeList;
import com.jagex.game.runetek4.config.msitype.MSITypeList;
import com.jagex.core.datastruct.LinkedList;
import com.jagex.core.io.Packet;
import com.jagex.game.runetek4.config.loctype.LocType;
import com.jagex.game.runetek4.config.msitype.MSIType;
import com.jagex.entity.player.PlayerList;
import com.jagex.graphics.gl.GlRaster;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.graphics.font.Fonts;
import com.jagex.scene.Camera;
import com.jagex.ui.sprite.GlSprite;
import com.jagex.core.utils.string.JString;
import com.jagex.core.utils.string.LocalizedText;
import com.jagex.game.map.Map;
import com.jagex.game.map.MapElementList;
import com.jagex.game.map.MapFunction;
import com.jagex.game.map.MapList;
import com.jagex.network.ClientProt;
import com.jagex.graphics.raster.Rasterizer;
import com.jagex.graphics.raster.SoftwareRenderer;
import com.jagex.scene.SceneGraph;
import com.jagex.clientscript.ClientScriptRunner;
import com.jagex.ui.sprite.SoftwareIndexedSprite;
import com.jagex.ui.sprite.Sprite;
import com.jagex.math.ColorUtils;
import com.jagex.core.utils.system.PreciseSleep;
import com.jagex.core.utils.debug.Cheat;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WorldMap {
    @OriginalMember(owner = "client!nc", name = "e", descriptor = "Lclient!na;")
    public static final JString UNDERLAY = JString.parse("underlay");

    @OriginalMember(owner = "client!vj", name = "m", descriptor = "Lclient!na;")
    public static final JString LABELS = JString.parse("_labels");

    @OriginalMember(owner = "client!ac", name = "m", descriptor = "Lclient!na;")
    public static final JString OVERLAY = JString.parse("overlay");

    @OriginalMember(owner = "client!fm", name = "gb", descriptor = "Lclient!na;")
    public static final JString OVERLAY2 = JString.parse("overlay2");

    @OriginalMember(owner = "client!df", name = "c", descriptor = "Lclient!na;")
    public static final JString LOC = JString.parse("loc");

    @OriginalMember(owner = "client!vk", name = "h", descriptor = "I")
    public static final int randomOffsetX = (int) (Math.random() * 33.0D) - 16;

    @OriginalMember(owner = "client!kd", name = "rb", descriptor = "I")
    public static final int randomOffsetZ = (int) (Math.random() * 17.0D) - 8;

    @OriginalMember(owner = "client!lf", name = "c", descriptor = "Lclient!ih;")
    public static final LinkedList elements = new LinkedList();

    @OriginalMember(owner = "client!he", name = "db", descriptor = "Lclient!na;")
    public static final JString EMPTY_STRING = JString.parse("");

    @OriginalMember(owner = "client!hm", name = "T", descriptor = "Lclient!na;")
    public static final JString SPACE_STRING = JString.parse(" ");

    @OriginalMember(owner = "client!pm", name = "Y", descriptor = "Lclient!na;")
    public static final JString LINE_BREAK = JString.parse("<br>");

    @OriginalMember(owner = "client!nj", name = "h", descriptor = "Lclient!ih;")
    public static final LinkedList mapElementCache = new LinkedList();

    @OriginalMember(owner = "client!di", name = "q", descriptor = "[Lclient!na;")
    public static final JString[] lines = new JString[5];

    @OriginalMember(owner = "client!fi", name = "j", descriptor = "Lclient!qf;")
    public static Sprite cachedMapSprite;

    @OriginalMember(owner = "client!dc", name = "O", descriptor = "I")
    public static int loadPercentage = 0;

    @OriginalMember(owner = "client!qf", name = "S", descriptor = "I")
    public static int mapFunctionCount;

    @OriginalMember(owner = "client!oi", name = "m", descriptor = "I")
    public static int areaHeight;

    @OriginalMember(owner = "client!bn", name = "N", descriptor = "Lclient!be;")
    public static Component component;

    @OriginalMember(owner = "client!bc", name = "W", descriptor = "I")
    public static int displayX;

    @OriginalMember(owner = "client!cd", name = "u", descriptor = "I")
    public static int displayZ;

    @OriginalMember(owner = "client!mh", name = "S", descriptor = "I")
    public static int originX;

    @OriginalMember(owner = "client!aa", name = "j", descriptor = "I")
    public static int originZ;

    @OriginalMember(owner = "client!wa", name = "ub", descriptor = "Lclient!bn;")
    public static Map area;

    @OriginalMember(owner = "client!gj", name = "r", descriptor = "F")
    public static float zoom;

    @OriginalMember(owner = "client!km", name = "uc", descriptor = "F")
    public static float targetZoom;

    @OriginalMember(owner = "client!dl", name = "e", descriptor = "I")
    public static int areaWidth;

    @OriginalMember(owner = "client!lf", name = "b", descriptor = "[I")
    public static int[] overlayColors;

    @OriginalMember(owner = "client!wb", name = "l", descriptor = "Lclient!fd;")
    public static WorldMapFont font26;

    @OriginalMember(owner = "client!mj", name = "n", descriptor = "Lclient!fd;")
    public static WorldMapFont font30;

    @OriginalMember(owner = "client!kc", name = "C", descriptor = "Lclient!fd;")
    public static WorldMapFont font22;

    @OriginalMember(owner = "client!qh", name = "d", descriptor = "Lclient!fd;")
    public static WorldMapFont font19;

    @OriginalMember(owner = "client!kc", name = "n", descriptor = "Lclient!fd;")
    public static WorldMapFont font17;

    @OriginalMember(owner = "client!nf", name = "d", descriptor = "Lclient!fd;")
    public static WorldMapFont font14;

    @OriginalMember(owner = "client!ma", name = "q", descriptor = "Lclient!fd;")
    public static WorldMapFont font12;

    @OriginalMember(owner = "client!we", name = "v", descriptor = "Lclient!fd;")
    public static WorldMapFont font11;

    @OriginalMember(owner = "client!rj", name = "P", descriptor = "I")
    public static int targetCenterZ = -1;

    @OriginalMember(owner = "client!lc", name = "l", descriptor = "I")
    public static int targetCenterX = -1;

    @OriginalMember(owner = "client!qh", name = "a", descriptor = "Lclient!se;")
    public static MapElementList labels;

    @OriginalMember(owner = "client!ck", name = "J", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray3;

    @OriginalMember(owner = "client!fi", name = "m", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray7;

    @OriginalMember(owner = "client!hb", name = "v", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray8;

    @OriginalMember(owner = "client!si", name = "R", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray12;

    @OriginalMember(owner = "client!jl", name = "I", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray10;

    @OriginalMember(owner = "client!gj", name = "i", descriptor = "[[[I")
    public static int[][][] underlayColors;

    @OriginalMember(owner = "client!uc", name = "d", descriptor = "[[[I")
    public static int[][][] underlayColorData;

    @OriginalMember(owner = "client!mc", name = "Q", descriptor = "Lclient!na;")
    public static JString lastAreaId;

    @OriginalMember(owner = "client!mc", name = "S", descriptor = "Lclient!mm;")
    public static SoftwareSprite aClass3_Sub2_Sub1_Sub1_2;

    @OriginalMember(owner = "client!ha", name = "o", descriptor = "I")
    public static int viewportDoubleWidth;

    @OriginalMember(owner = "client!cm", name = "c", descriptor = "I")
    public static int viewportDoubleHeight;

    @OriginalMember(owner = "client!sm", name = "m", descriptor = "I")
    public static int currentLabelIndex;

    @OriginalMember(owner = "client!al", name = "e", descriptor = "I")
    public static int selectedMapFunctionId;

    @OriginalMember(owner = "client!eh", name = "g", descriptor = "[[[I")
    public static int[][][] scenery;

    @OriginalMember(owner = "client!jb", name = "a", descriptor = "(IZ)V")
    public static void reset(@OriginalArg(1) boolean saveArea) {
        aByteArrayArrayArray8 = null;
        underlayColors = null;
        component = null;
        aByteArrayArrayArray3 = null;
        overlayColors = null;
        aByteArrayArrayArray10 = null;
        if (saveArea && area != null) {
            lastAreaId = area.id;
        } else {
            lastAreaId = null;
        }
        aByteArrayArrayArray7 = null;
        aByteArrayArrayArray12 = null;
        scenery = null;
        underlayColorData = null;
        loadPercentage = 0;
        area = null;
        elements.clear();
        labels = null;
        targetCenterZ = -1;
        font22 = null;
        font30 = null;
        font12 = null;
        font26 = null;
        font11 = null;
        font14 = null;
        font17 = null;
        font19 = null;
        cachedMapSprite = null;
        targetCenterX = -1;
        aClass3_Sub2_Sub1_Sub1_2 = null;
    }

    @OriginalMember(owner = "client!wa", name = "a", descriptor = "(IIIII)V")
    public static void draw(@OriginalArg(0) int x, @OriginalArg(2) int y, @OriginalArg(3) int height, @OriginalArg(4) int width) {
        if (loadPercentage < 100) {
            load();
        }
        if (GlRenderer.enabled) {
            GlRaster.setClip(x, y, x + width, height + y);
        } else {
            SoftwareRenderer.setClip(x, y, x + width, height + y);
        }
        @Pc(50) int centerX;
        @Pc(61) int centerY;
        if (loadPercentage < 100) {
            centerX = x + width / 2;
            centerY = height / 2 + y - 18 - 20;
            if (GlRenderer.enabled) {
                GlRaster.fillRect(x, y, width, height, 0);
                GlRaster.drawRect(centerX - 152, centerY, 304, 34, 9179409);
                GlRaster.drawRect(centerX - 151, centerY + 1, 302, 32, 0);
                GlRaster.fillRect(centerX - 150, centerY + 2, loadPercentage * 3, 30, 9179409);
                GlRaster.fillRect(centerX + loadPercentage * 3 - 150, centerY - -2, 300 - loadPercentage * 3, 30, 0);
            } else {
                SoftwareRenderer.fillRect(x, y, width, height, 0);
                SoftwareRenderer.drawRect(centerX - 152, centerY, 304, 34, 9179409);
                SoftwareRenderer.drawRect(centerX - 151, centerY + 1, 302, 32, 0);
                SoftwareRenderer.fillRect(centerX - 150, centerY + 2, loadPercentage * 3, 30, 9179409);
                SoftwareRenderer.fillRect(loadPercentage * 3 + centerX - 150, centerY - -2, 300 - loadPercentage * 3, 30, 0);
            }
            Fonts.b12Full.renderCenter(LocalizedText.LOADINGDOTDOTDOT, centerX, centerY + 20, 16777215, -1);
            return;
        }
        viewportDoubleHeight = (int) ((float) (height * 2) / zoom);
        ClientScriptRunner.worldMapViewportX = displayX - (int) ((float) width / zoom);
        @Pc(211) int leftBound = displayX - (int) ((float) width / zoom);
        centerX = displayZ - (int) ((float) height / zoom);
        ClientScriptRunner.worldMapViewportY = displayZ - (int) ((float) height / zoom);
        @Pc(236) int bottomBound = displayZ + (int) ((float) height / zoom);
        centerY = (int) ((float) width / zoom) + displayX;
        viewportDoubleWidth = (int) ((float) (width * 2) / zoom);
        if (GlRenderer.enabled) {
            if (aClass3_Sub2_Sub1_Sub1_2 == null || aClass3_Sub2_Sub1_Sub1_2.width != width || aClass3_Sub2_Sub1_Sub1_2.height != height) {
                aClass3_Sub2_Sub1_Sub1_2 = null;
                aClass3_Sub2_Sub1_Sub1_2 = new SoftwareSprite(width, height);
            }
            SoftwareRenderer.setSize(aClass3_Sub2_Sub1_Sub1_2.pixels, width, height);
            renderMapToBuffer(width, 0, centerY, centerX, 0, bottomBound, height, leftBound);
            renderLabelsWithGradient(width, 0, centerY, bottomBound, height, 0, leftBound, centerX);
            renderLabelsToBuffer(0, 0, leftBound, width, bottomBound, centerX, centerY, height);
            GlRaster.render(aClass3_Sub2_Sub1_Sub1_2.pixels, x, y, width, height);
            SoftwareRenderer.pixels = null;
        } else {
            renderMapToBuffer(width + x, y, centerY, centerX, x, bottomBound, y + height, leftBound);
            renderLabelsWithGradient(x + width, x, centerY, bottomBound, height + y, y, leftBound, centerX);
            renderLabelsToBuffer(x, y, leftBound, x + width, bottomBound, centerX, centerY, height + y);
        }
        if (mapFunctionCount > 0) {
            ClientScriptRunner.mapFunctionFlashTimer--;
            if (ClientScriptRunner.mapFunctionFlashTimer == 0) {
                ClientScriptRunner.mapFunctionFlashTimer = 20;
                mapFunctionCount--;
            }
        }
        if (!Cheat.displayFps) {
            return;
        }
        // Render Debug menu
        @Pc(405) int debugY = y + height - 8;
        @Pc(412) int debugX = x + width - 5;
        Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_FPS, JString.parseInt(GameShell.fps) }), debugX, debugY, 16776960, -1);
        @Pc(434) Runtime runtime = Runtime.getRuntime();
        @Pc(443) int memoryUsed = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
        @Pc(445) int memoryColor = 16776960;
        @Pc(446) int memoryY = debugY - 15;
        if (memoryUsed > 65536) {
            memoryColor = 16711680;
        }
        Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.MEM, JString.parseInt(memoryUsed), Cheat.DEBUG_MEMORY_UNIT}), debugX, memoryY, memoryColor, -1);
        debugY = memoryY - 15;
    }

    //TODO Move?
    @OriginalMember(owner = "client!dk", name = "a", descriptor = "(Lclient!wa;Z)V")
    public static void readOverlayData(@OriginalArg(0) Packet packet) {
        label87: while (true) {
            if (packet.offset < packet.data.length) {
                @Pc(22) int subTileX = 0;
                @Pc(24) boolean isSubTile = false;
                @Pc(26) int subTileZ = 0;
                if (packet.g1() == 1) {
                    isSubTile = true;
                    subTileX = packet.g1();
                    subTileZ = packet.g1();
                }
                @Pc(46) int mapSquareX = packet.g1();
                @Pc(50) int mapSquareZ = packet.g1();
                @Pc(62) int mapZ = originZ + areaHeight - mapSquareZ * 64 - 1;
                @Pc(69) int mapX = mapSquareX * 64 - originX;
                @Pc(147) byte local147;
                @Pc(91) int local91;
                if (mapX >= 0 && mapZ - 63 >= 0 && mapX + 63 < areaWidth && mapZ < areaHeight) {
                    local91 = mapX >> 6;
                    @Pc(95) int local95 = mapZ >> 6;
                    @Pc(97) int local97 = 0;
                    while (true) {
                        if (local97 >= 64) {
                            continue label87;
                        }
                        for (@Pc(104) int local104 = 0; local104 < 64; local104++) {
                            if (!isSubTile || local97 >= subTileX * 8 && local97 < subTileX * 8 + 8 && local104 >= subTileZ * 8 && local104 < subTileZ * 8 + 8) {
                                local147 = packet.g1s();
                                if (local147 != 0) {
                                    if (aByteArrayArrayArray3[local91][local95] == null) {
                                        aByteArrayArrayArray3[local91][local95] = new byte[4096];
                                    }
                                    aByteArrayArrayArray3[local91][local95][local97 + (63 - local104 << 6)] = local147;
                                    @Pc(186) byte local186 = packet.g1s();
                                    if (aByteArrayArrayArray8[local91][local95] == null) {
                                        aByteArrayArrayArray8[local91][local95] = new byte[4096];
                                    }
                                    aByteArrayArrayArray8[local91][local95][local97 + (63 - local104 << 6)] = local186;
                                }
                            }
                        }
                        local97++;
                    }
                }
                local91 = 0;
                while (true) {
                    if ((isSubTile ? 64 : 4096) <= local91) {
                        continue label87;
                    }
                    local147 = packet.g1s();
                    if (local147 != 0) {
                        packet.offset++;
                    }
                    local91++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "client!hj", name = "a", descriptor = "(II)V")
    public static void setDisplayX(@OriginalArg(0) int x) {
        targetCenterZ = -1;
        targetCenterX = -1;
        displayX = x;
        checkJump();
    }

    @OriginalMember(owner = "client!wi", name = "d", descriptor = "(II)V")
    public static void setDisplayZ(@OriginalArg(1) int z) {
        targetCenterZ = -1;
        targetCenterZ = -1;
        displayZ = z;
        checkJump();
    }

    @OriginalMember(owner = "client!je", name = "a", descriptor = "(IIIII)V")
    public static void clickedOverview(@OriginalArg(1) int overviewWidth, @OriginalArg(2) int overviewHeight, @OriginalArg(3) int clickX, @OriginalArg(4) int clickZ) {
        displayX = areaWidth * clickX / overviewWidth;
        displayZ = areaHeight * overviewHeight / clickZ;
        targetCenterX = -1;
        targetCenterZ = -1;
        checkJump();
    }

    @OriginalMember(owner = "client!lb", name = "d", descriptor = "(B)V")
    public static void loadLastArea() {
        if (lastAreaId != null) {
            loadAreaByName(lastAreaId);
            lastAreaId = null;
        }
    }

    @OriginalMember(owner = "client!pa", name = "d", descriptor = "(I)V")
    public static void load() {
        if (area == null) {
            return;
        }
        if (loadPercentage < 10) {
            if (!MapList.archive.isGroupReady(area.id)) {
                loadPercentage = Client.js5Archive23.method4478(area.id) / 10;
                return;
            }
            Client.method84();
            loadPercentage = 10;
        }
        if (loadPercentage == 10) {
            originX = area.displayMinZ >> 6 << 6;
            originZ = area.displayMinX >> 6 << 6;
            areaHeight = (area.displayMaxX >> 6 << 6) + 64 - originZ;
            areaWidth = (area.displayMaxZ >> 6 << 6) + 64 - originX;
            if (area.defaultZoom == 37) {
                zoom = 3.0F;
                targetZoom = 3.0F;
            } else if (area.defaultZoom == 50) {
                zoom = 4.0F;
                targetZoom = 4.0F;
            } else if (area.defaultZoom == 75) {
                zoom = 6.0F;
                targetZoom = 6.0F;
            } else if (area.defaultZoom == 100) {
                zoom = 8.0F;
                targetZoom = 8.0F;
            } else if (area.defaultZoom == 200) {
                zoom = 16.0F;
                targetZoom = 16.0F;
            } else {
                zoom = 8.0F;
                targetZoom = 8.0F;
            }
            @Pc(144) int playerMapX = (PlayerList.self.xFine >> 7) + Camera.sceneBaseTileX - originX;
            @Pc(153) int randomizedPlayerX = playerMapX + (int) (Math.random() * 10.0D) - 5;
            @Pc(168) int playerMapZ = originZ + areaHeight - Camera.sceneBaseTileZ - (PlayerList.self.zFine >> 7) - 1;
            @Pc(177) int randomizedPlayerZ = playerMapZ + (int) (Math.random() * 10.0D) - 5;
            if (randomizedPlayerX >= 0 && areaWidth > randomizedPlayerX && randomizedPlayerZ >= 0 && randomizedPlayerZ < areaHeight) {
                displayX = randomizedPlayerX;
                displayZ = randomizedPlayerZ;
            } else {
                displayZ = originZ + areaHeight - area.originZ * 64 - 1;
                displayX = area.originX * 64 - originX;
            }
            checkJump();
            overlayColors = new int[FloorOverlayTypeList.capacity + 1];
            @Pc(235) int mapSquaresZ = areaHeight >> 6;
            @Pc(239) int mapSquaresX = areaWidth >> 6;
            aByteArrayArrayArray8 = new byte[mapSquaresX][mapSquaresZ][];
            @Pc(249) int lightOffsetHue = SceneGraph.randomLightOffsetX >> 2 << 10;
            aByteArrayArrayArray7 = new byte[mapSquaresX][mapSquaresZ][];
            underlayColors = new int[mapSquaresX][mapSquaresZ][];
            aByteArrayArrayArray3 = new byte[mapSquaresX][mapSquaresZ][];
            underlayColorData = new int[mapSquaresX][mapSquaresZ][];
            aByteArrayArrayArray12 = new byte[mapSquaresX][mapSquaresZ][];
            @Pc(273) int local273 = SceneGraph.randomLightOffsetZ >> 1;
            aByteArrayArrayArray10 = new byte[mapSquaresX][mapSquaresZ][];
            scenery = new int[mapSquaresX][mapSquaresZ][];
            loadOverlayColors(local273, lightOffsetHue);
            loadPercentage = 20;
        } else if (loadPercentage == 20) {
            readUnderlay(new Packet(MapList.archive.fetchFile(UNDERLAY, area.id)));
            loadPercentage = 30;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 30) {
            readOverlayData(new Packet(MapList.archive.fetchFile(OVERLAY, area.id)));
            loadPercentage = 40;
            GameShell.resetTimer();
        } else if (loadPercentage == 40) {
            PreciseSleep.method3980(new Packet(MapList.archive.fetchFile(OVERLAY2, area.id)));
            loadPercentage = 50;
            GameShell.resetTimer();
        } else if (loadPercentage == 50) {
            readLocs(new Packet(MapList.archive.fetchFile(LOC, area.id)));
            loadPercentage = 60;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 60) {
            if (MapList.archive.isGroupNameValid(JString.concatenate(new JString[]{area.id, LABELS}))) {
                if (!MapList.archive.isGroupReady(JString.concatenate(new JString[]{area.id, LABELS}))) {
                    return;
                }
                labels = MapElementList.create(JString.concatenate(new JString[]{area.id, LABELS}), MapList.archive);
            } else {
                labels = new MapElementList(0);
            }
            loadPercentage = 70;
            GameShell.resetTimer();
        } else if (loadPercentage == 70) {
            font11 = new WorldMapFont(11, true, GameShell.canvas);
            loadPercentage = 73;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 73) {
            font12 = new WorldMapFont(12, true, GameShell.canvas);
            loadPercentage = 76;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 76) {
            font14 = new WorldMapFont(14, true, GameShell.canvas);
            loadPercentage = 79;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 79) {
            font17 = new WorldMapFont(17, true, GameShell.canvas);
            loadPercentage = 82;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 82) {
            font19 = new WorldMapFont(19, true, GameShell.canvas);
            loadPercentage = 85;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 85) {
            font22 = new WorldMapFont(22, true, GameShell.canvas);
            loadPercentage = 88;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 88) {
            font26 = new WorldMapFont(26, true, GameShell.canvas);
            loadPercentage = 91;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else {
            font30 = new WorldMapFont(30, true, GameShell.canvas);
            loadPercentage = 100;
            ClientProt.ping(true);
            GameShell.resetTimer();
            System.gc();
        }
    }

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(BLclient!wa;)V")
    public static void readUnderlay(@OriginalArg(1) Packet packet) {
        @Pc(13) int lightnessOffset = randomOffsetX >> 1;
        @Pc(19) int hueOffset = randomOffsetZ >> 2 << 10;
        @Pc(23) byte[][] local23 = new byte[areaWidth][areaHeight];
        @Pc(33) int subTileX;
        @Pc(102) int local102;
        @Pc(114) int z;
        while (packet.offset < packet.data.length) {
            @Pc(31) int subTileZ = 0;
            subTileX = 0;
            @Pc(35) boolean isSubTile = false;
            if (packet.g1() == 1) {
                subTileX = packet.g1();
                subTileZ = packet.g1();
                isSubTile = true;
            }
            @Pc(57) int mapSquareX = packet.g1();
            @Pc(61) int mapSquareZ = packet.g1();
            @Pc(68) int worldX = mapSquareX * 64 - originX;
            @Pc(78) int worldZ = areaHeight + originZ - mapSquareZ * 64 - 1;
            if (worldX >= 0 && worldZ - 63 >= 0 && areaWidth > worldX + 63 && areaHeight > worldZ) {
                for (local102 = 0; local102 < 64; local102++) {
                    @Pc(112) byte[] local112 = local23[worldX + local102];
                    for (z = 0; z < 64; z++) {
                        if (!isSubTile || local102 >= subTileX * 8 && subTileX * 8 + 8 > local102 && z >= subTileZ * 8 && z < subTileZ * 8 + 8) {
                            local112[worldZ - z] = packet.g1s();
                        }
                    }
                }
            } else if (isSubTile) {
                packet.offset += 64;
            } else {
                packet.offset += 4096;
            }
        }
        @Pc(175) int local175 = areaWidth;
        subTileX = areaHeight;
        @Pc(180) int[] saturationSum = new int[subTileX];
        @Pc(183) int[] hueSum = new int[subTileX];
        @Pc(186) int[] lightnessSum = new int[subTileX];
        @Pc(189) int[] chromaSum = new int[subTileX];
        @Pc(192) int[] underlayCount = new int[subTileX];
        for (local102 = -5; local102 < local175; local102++) {
            @Pc(225) int local225;
            @Pc(293) int local293;
            for (@Pc(203) int local203 = 0; local203 < subTileX; local203++) {
                z = local102 + 5;
                @Pc(272) int local272;
                if (local175 > z) {
                    local225 = local23[z][local203] & 0xFF;
                    if (local225 > 0) {
                        @Pc(236) FloorUnderlayType local236 = FloorUnderlayTypeList.get(local225 - 1);
                        hueSum[local203] += local236.weightedHue;
                        saturationSum[local203] += local236.saturation;
                        lightnessSum[local203] += local236.lightness;
                        chromaSum[local203] += local236.chroma;
                        local272 = underlayCount[local203]++;
                    }
                }
                local225 = local102 - 5;
                if (local225 >= 0) {
                    local293 = local23[local225][local203] & 0xFF;
                    if (local293 > 0) {
                        @Pc(302) FloorUnderlayType local302 = FloorUnderlayTypeList.get(local293 - 1);
                        hueSum[local203] -= local302.weightedHue;
                        saturationSum[local203] -= local302.saturation;
                        lightnessSum[local203] -= local302.lightness;
                        chromaSum[local203] -= local302.chroma;
                        local272 = underlayCount[local203]--;
                    }
                }
            }
            if (local102 >= 0) {
                @Pc(355) int[][] local355 = underlayColorData[local102 >> 6];
                z = 0;
                local225 = 0;
                @Pc(361) int local361 = 0;
                @Pc(363) int local363 = 0;
                local293 = 0;
                for (@Pc(367) int local367 = -5; local367 < subTileX; local367++) {
                    @Pc(378) int local378 = local367 + 5;
                    if (subTileX > local378) {
                        local363 += underlayCount[local378];
                        local225 += saturationSum[local378];
                        local293 += lightnessSum[local378];
                        z += hueSum[local378];
                        local361 += chromaSum[local378];
                    }
                    @Pc(415) int local415 = local367 - 5;
                    if (local415 >= 0) {
                        local293 -= lightnessSum[local415];
                        local361 -= chromaSum[local415];
                        z -= hueSum[local415];
                        local363 -= underlayCount[local415];
                        local225 -= saturationSum[local415];
                    }
                    if (local367 >= 0 && local363 > 0) {
                        @Pc(462) int[] local462 = local355[local367 >> 6];
                        @Pc(480) int local480 = local361 == 0 ? 0 : ColorUtils.method1309(local293 / local363, local225 / local363, z * 256 / local361);
                        if (local23[local102][local367] != 0) {
                            if (local462 == null) {
                                local462 = local355[local367 >> 6] = new int[4096];
                            }
                            @Pc(519) int local519 = lightnessOffset + (local480 & 0x7F);
                            if (local519 < 0) {
                                local519 = 0;
                            } else if (local519 > 127) {
                                local519 = 127;
                            }
                            @Pc(541) int local541 = local519 + (local480 & 0x380) + (local480 + hueOffset & 0xFC00);
                            local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = Rasterizer.palette[ColorUtils.multiplyLightnessSafe(96, local541)];
                        } else if (local462 != null) {
                            local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = 0;
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!nc", name = "a", descriptor = "(BLclient!wa;)V")
    public static void readLocs(@OriginalArg(1) Packet packet) {
        label123: while (true) {
            if (packet.data.length > packet.offset) {
                @Pc(17) boolean isSubTile = false;
                @Pc(19) int subTileX = 0;
                @Pc(21) int subTileZ = 0;
                if (packet.g1() == 1) {
                    subTileX = packet.g1();
                    isSubTile = true;
                    subTileZ = packet.g1();
                }
                @Pc(42) int mapSquareX = packet.g1();
                @Pc(46) int mapSquareZ = packet.g1();
                @Pc(53) int mapx = mapSquareX * 64 - originX;
                @Pc(65) int mapz = originZ + areaHeight - mapSquareZ * 64 - 1;
                @Pc(84) int x;
                @Pc(95) int z;
                if (mapx >= 0 && mapz - 63 >= 0 && areaWidth > mapx + 63 && mapz < areaHeight) {
                    x = mapx >> 6;
                    z = mapz >> 6;
                    @Pc(150) int localX = 0;
                    while (true) {
                        if (localX >= 64) {
                            continue label123;
                        }
                        for (@Pc(155) int localZ = 0; localZ < 64; localZ++) {
                            if (!isSubTile || subTileX * 8 <= localX && localX < subTileX * 8 + 8 && localZ >= subTileZ * 8 && localZ < subTileZ * 8 + 8) {

                                @Pc(202) int flags = packet.g1();
                                if (flags != 0) {
                                    @Pc(214) int data;
                                    if ((flags & 0x1) == 1) {
                                        data = packet.g1();
                                        if (aByteArrayArrayArray7[x][z] == null) {
                                            aByteArrayArrayArray7[x][z] = new byte[4096];
                                        }
                                        aByteArrayArrayArray7[x][z][localX + (63 - localZ << 6)] = (byte) data;
                                    }
                                    if ((flags & 0x2) == 2) {
                                        data = packet.g3();
                                        if (scenery[x][z] == null) {
                                            scenery[x][z] = new int[4096];
                                        }
                                        scenery[x][z][(63 - localZ << 6) + localX] = data;
                                    }
                                    if ((flags & 0x4) == 4) {
                                        int locId = packet.g3();
                                        if (underlayColors[x][z] == null) {
                                            underlayColors[x][z] = new int[4096];
                                        }
                                        locId--;
                                        @Pc(312) LocType type = LocTypeList.get(locId);
                                        if (type.multiloc != null) {
                                            type = type.getMultiLoc();
                                            if (type == null || type.mapelement == -1) {
                                                continue;
                                            }
                                        }
                                        underlayColors[x][z][(63 - localZ << 6) + localX] = type.id + 1;
                                        @Pc(353) MapFunction mapFunction = new MapFunction();
                                        mapFunction.id = type.mapelement;
                                        mapFunction.x = mapx;
                                        mapFunction.z = mapz;
                                        elements.push(mapFunction);
                                    }
                                }
                            }
                        }
                        localX++;
                    }
                }
                x = 0;
                while (true) {
                    if (x >= (isSubTile ? 64 : 4096)) {
                        continue label123;
                    }
                    z = packet.g1();
                    if (z != 0) {
                        if ((z & 0x1) == 1) {
                            packet.offset++;
                        }
                        if ((z & 0x2) == 2) {
                            packet.offset += 2;
                        }
                        if ((z & 0x4) == 4) {
                            packet.offset += 3;
                        }
                    }
                    x++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "client!cn", name = "e", descriptor = "(B)V")
    public static void checkJump() {
        if (displayX < 0) {
            targetCenterZ = -1;
            displayX = 0;
            targetCenterX = -1;
        }
        if (displayX > areaWidth) {
            targetCenterZ = -1;
            displayX = areaWidth;
            targetCenterX = -1;
        }
        if (displayZ < 0) {
            targetCenterX = -1;
            targetCenterZ = -1;
            displayZ = 0;
        }
        if (areaHeight < displayZ) {
            displayZ = areaHeight;
            targetCenterZ = -1;
            targetCenterX = -1;
        }
    }

    @OriginalMember(owner = "client!fi", name = "a", descriptor = "(III)V")
    public static void loadOverlayColors(@OriginalArg(1) int randomLightZ, @OriginalArg(2) int randomLightX) {
        for (@Pc(11) int i = 0; i < FloorOverlayTypeList.capacity; i++) {
            @Pc(18) FloorOverlayType overlayType = FloorOverlayTypeList.method4395(i);
            if (overlayType != null) {
                @Pc(24) int textureId = overlayType.texture;
                if (textureId >= 0 && !Rasterizer.textureProvider.method3236(textureId)) {
                    textureId = -1;
                }
                @Pc(53) int finalColor;
                @Pc(66) int baseColor;
                @Pc(72) int adjustedLightness;
                @Pc(95) int adjustedColor;
                if (overlayType.blendColor >= 0) {
                    baseColor = overlayType.blendColor;
                    adjustedLightness = (baseColor & 0x7F) + randomLightZ;
                    if (adjustedLightness < 0) {
                        adjustedLightness = 0;
                    } else if (adjustedLightness > 127) {
                        adjustedLightness = 127;
                    }
                    adjustedColor = (baseColor & 0x380) + (randomLightX + baseColor & 0xFC00) + adjustedLightness;
                    finalColor = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(adjustedColor, 96)];
                } else if (textureId >= 0) {
                    finalColor = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(Rasterizer.textureProvider.getAverageColor(textureId), 96)];
                } else if (overlayType.baseColor == -1) {
                    finalColor = -1;
                } else {
                    baseColor = overlayType.baseColor;
                    adjustedLightness = randomLightZ + (baseColor & 0x7F);
                    if (adjustedLightness < 0) {
                        adjustedLightness = 0;
                    } else if (adjustedLightness > 127) {
                        adjustedLightness = 127;
                    }
                    adjustedColor = adjustedLightness + (baseColor & 0x380) + (baseColor + randomLightX & 0xFC00);
                    finalColor = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(adjustedColor, 96)];
                }
                overlayColors[i + 1] = finalColor;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(ILclient!na;)I")
    public static int findLabelIndexFuzzy(@OriginalArg(1) JString text) {
        if (labels == null || text.length() == 0) {
            return -1;
        }
        for (@Pc(20) int i = 0; i < labels.anInt5074; i++) {
            if (labels.text[i].method3140(SPACE_STRING, LINE_BREAK).method3142(text)) {
                return i;
            }
        }
        return -1;
    }

    @OriginalMember(owner = "client!cn", name = "a", descriptor = "(BIIIIIIII)V")
    public static void renderLabelsToBuffer(@OriginalArg(1) int screenX1, @OriginalArg(2) int screenY1, @OriginalArg(3) int mapLeft, @OriginalArg(4) int screenX2, @OriginalArg(5) int mapBottom, @OriginalArg(6) int mapTop, @OriginalArg(7) int mapRight, @OriginalArg(8) int screenY2) {
        for (@Pc(11) int j = 0; j < labels.anInt5074; j++) {
            if (labels.hasFlag8Set(j)) {
                @Pc(32) int labelMapX = labels.aShortArray73[j] - originX;
                @Pc(43) int labelMapZ = originZ + areaHeight - labels.aShortArray72[j] - 1;
                @Pc(59) int screenX = screenX1 + (screenX2 - screenX1) * (labelMapX - mapLeft) / (mapRight - mapLeft);
                @Pc(64) int textsize = labels.getLowerTwoBits(j);
                @Pc(80) int screenY = (screenY2 - screenY1) * (labelMapZ - mapTop) / (mapBottom - mapTop) + screenY1;
                @Pc(82) int textColor = 16777215;
                @Pc(84) WorldMapFont font = null;
                if (textsize == 0) {
                    if ((double) zoom == 3.0D) {
                        font = font11;
                    }
                    if ((double) zoom == 4.0D) {
                        font = font12;
                    }
                    if ((double) zoom == 6.0D) {
                        font = font14;
                    }
                    if ((double) zoom >= 8.0D) {
                        font = font17;
                    }
                }
                if (textsize == 1) {
                    if ((double) zoom == 3.0D) {
                        font = font14;
                    }
                    if ((double) zoom == 4.0D) {
                        font = font17;
                    }
                    if ((double) zoom == 6.0D) {
                        font = font19;
                    }
                    if ((double) zoom >= 8.0D) {
                        font = font22;
                    }
                }
                if (textsize == 2) {
                    if ((double) zoom == 3.0D) {
                        font = font19;
                    }
                    textColor = 16755200;
                    if ((double) zoom == 4.0D) {
                        font = font22;
                    }
                    if ((double) zoom == 6.0D) {
                        font = font26;
                    }
                    if ((double) zoom >= 8.0D) {
                        font = font30;
                    }
                }
                if (labels.anIntArray444[j] != -1) {
                    textColor = labels.anIntArray444[j];
                }
                if (font != null) {
                    @Pc(211) int lineCount = Fonts.p11Full.splitParagraph(labels.text[j], null, lines);
                    screenY -= font.getBaselineOffset() * (lineCount - 1) / 2;
                    screenY += font.getLineHeight() / 2;
                    for (@Pc(231) int i = 0; i < lineCount; i++) {
                        @Pc(242) JString line = lines[i];
                        if (lineCount - 1 > i) {
                            line.method3133(line.length() - 4);
                        }
                        font.renderStringCenter(line, screenX, screenY, textColor);
                        screenY += font.getBaselineOffset();
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(Lclient!na;I)V")
    public static void centerOnLabelFuzzy(@OriginalArg(0) JString arg0) {
        @Pc(9) int labelText = findLabelIndexFuzzy(arg0);
        if (labelText != -1) {
            setMapCenterPosition(labels.aShortArray73[labelText], labels.aShortArray72[labelText]);
        }
    }

    @OriginalMember(owner = "runetek4.client!af", name = "b", descriptor = "(B)V")
    public static void reset() {
        reset(false);
        System.gc();
        Client.processGameStatus(25);
    }

    @OriginalMember(owner = "client!bb", name = "a", descriptor = "(I)V")
    public static void update() {
        if (zoom < targetZoom) {
            zoom = (float) ((double) zoom + (double) zoom / 30.0D);
            if (targetZoom < zoom) {
                zoom = targetZoom;
            }
            checkJump();
        } else if (targetZoom < zoom) {
            zoom = (float) ((double) zoom - (double) zoom / 30.0D);
            if (targetZoom > zoom) {
                zoom = targetZoom;
            }
            checkJump();
        }
        if (targetCenterX == -1 || targetCenterZ == -1) {
            return;
        }
        @Pc(60) int deltaX = targetCenterX - displayX;
        if (deltaX < 2 || deltaX > 2) {
            deltaX >>= 0x4;
        }
        @Pc(78) int deltaZ = targetCenterZ - displayZ;
        if (deltaZ < 2 || deltaZ > 2) {
            deltaZ >>= 0x4;
        }
        displayZ -= -deltaZ;
        displayX += deltaX;
        if (deltaX == 0 && deltaZ == 0) {
            targetCenterX = -1;
            targetCenterZ = -1;
        }
        checkJump();
    }

    @OriginalMember(owner = "client!dh", name = "a", descriptor = "(Lclient!na;I)V")
    public static void centerOnLabelExact(@OriginalArg(0) JString labelText) {
        @Pc(7) int labelIndex = findLabelIndexExact(labelText);
        if (labelIndex != -1) {
            setMapCenterPosition(labels.aShortArray73[labelIndex], labels.aShortArray72[labelIndex]);
        }
    }

    @OriginalMember(owner = "client!dl", name = "a", descriptor = "(IIIIIIIII)V")
    public static void renderLabelsWithGradient(@OriginalArg(0) int screenX2, @OriginalArg(1) int screenX1, @OriginalArg(2) int mapRight, @OriginalArg(3) int mapBottom, @OriginalArg(4) int screenY2, @OriginalArg(5) int screenY1, @OriginalArg(7) int mapLeft, @OriginalArg(8) int mapTop) {
        @Pc(13) int mapWidth = mapRight - mapLeft;
        @Pc(17) int mapHeight = mapBottom - mapTop;
        @Pc(26) int scaleX = (screenX2 - screenX1 << 16) / mapWidth;
        @Pc(35) int scaleY = (screenY2 - screenY1 << 16) / mapHeight;
        renderMapIconsToBuffer(screenX1, mapBottom, mapRight, scaleY, mapLeft, scaleX, mapTop, screenY1);
    }

    @OriginalMember(owner = "client!gf", name = "a", descriptor = "(BII)V")
    public static void setMapCenterPosition(@OriginalArg(1) int worldX, @OriginalArg(2) int worldZ) {
        targetCenterX = worldX - originX;
        @Pc(24) int minX = targetCenterX - (int) ((float) component.width / zoom);
        @Pc(33) int maxX = targetCenterX + (int) ((float) component.width / zoom);
        if (minX < 0) {
            targetCenterX = (int) ((float) component.width / zoom);
        }
        targetCenterZ = areaHeight + originZ - worldZ - 1;
        @Pc(61) int maxZ = (int) ((float) component.height / zoom) + targetCenterZ;
        @Pc(70) int minZ = targetCenterZ - (int) ((float) component.height / zoom);
        if (maxX > areaWidth) {
            targetCenterX = areaWidth - (int) ((float) component.width / zoom);
        }
        if (minZ < 0) {
            targetCenterZ = (int) ((float) component.height / zoom);
        }
        if (areaHeight < maxZ) {
            targetCenterZ = areaHeight - (int) ((float) component.height / zoom);
        }
    }

    @OriginalMember(owner = "runetek4.client!hb", name = "b", descriptor = "(Lclient!na;I)V")
    public static void loadAreaByName(@OriginalArg(0) JString areaName) {
        reset(false);
        setAreaByName(areaName);
    }

    @OriginalMember(owner = "runetek4.client!hc", name = "d", descriptor = "(I)I")
    public static int getTargetZoom() {
        if ((double) targetZoom == 3.0D) {
            return 37;
        } else if ((double) targetZoom == 4.0D) {
            return 50;
        } else if ((double) targetZoom == 6.0D) {
            return 75;
        } else if ((double) targetZoom == 8.0D) {
            return 100;
        } else {
            return 200;
        }
    }

    @OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
    public static int findLabelIndexExact(@OriginalArg(0) JString text) {
        if (labels == null || text.length() == 0) {
            return -1;
        }
        for (@Pc(20) int i = 0; i < labels.anInt5074; i++) {
            if (labels.text[i].method3140(SPACE_STRING, LINE_BREAK).strEquals(text)) {
                return i;
            }
        }
        return -1;
    }

    @OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(B)I")
    public static int resetLabelIterator() {
        currentLabelIndex = 0;
        return getNextVisibleLabel();
    }

    @OriginalMember(owner = "client!je", name = "j", descriptor = "(I)I")
    public static int getNextVisibleLabel() {
        if (labels == null) {
            return -1;
        }
        while (currentLabelIndex < labels.anInt5074) {
            if (labels.isFlag16Clear(currentLabelIndex)) {
                return currentLabelIndex++;
            }
            currentLabelIndex++;
        }
        return -1;
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "a", descriptor = "(Lclient!na;I)V")
    public static void setAreaByName(@OriginalArg(0) JString areaName) {
        for (@Pc(15) Map map = (Map) MapList.areas.head(); map != null; map = (Map) MapList.areas.next()) {
            if (map.id.strEquals(areaName)) {
                area = map;
                return;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!le", name = "a", descriptor = "(IIIIIIIIIII)V")
    public static void renderMapTilesToBuffer(@OriginalArg(0) int screenY1, @OriginalArg(2) int screenY2, @OriginalArg(3) int mapTop, @OriginalArg(4) int mapRight, @OriginalArg(5) int screenX1, @OriginalArg(6) int mapLeft, @OriginalArg(7) int scaleY, @OriginalArg(8) int scaleX) {
        @Pc(9) int mapWidth = mapRight - mapLeft;
        @Pc(14) int mapHeight = screenY2 - mapTop;
        if (areaWidth > mapRight) {
            mapWidth++;
        }
        if (areaHeight > screenY2) {
            mapHeight++;
        }
        @Pc(32) int x;
        @Pc(47) int pixelX1;
        @Pc(57) int pixelX2;
        @Pc(62) int pixelWidth;
        @Pc(71) int zoneX;
        @Pc(104) int color;
        @Pc(145) int pixelY1;
        @Pc(157) int pixelY2;
        @Pc(162) int pixelHeight;
        @Pc(211) int z;
        @Pc(222) int y;
        @Pc(233) int local233;
        @Pc(254) int zoneZ;
        @Pc(270) int localX;
        @Pc(276) int tileIndex;
        @Pc(312) int local312;
        @Pc(372) int overlayColor;
        @Pc(185) int[][] colorData;
        for (x = 0; x < mapWidth; x++) {
            pixelX1 = x * scaleX >> 16;
            pixelX2 = (x + 1) * scaleX >> 16;
            pixelWidth = pixelX2 - pixelX1;
            if (pixelWidth > 0) {
                zoneX = x + mapLeft >> 6;
                if (zoneX >= 0 && underlayColorData.length - 1 >= zoneX) {
                    pixelX1 += screenX1;
                    colorData = underlayColorData[zoneX];
                    @Pc(189) byte[][] overlayIdRow = aByteArrayArrayArray3[zoneX];
                    @Pc(193) byte[][] overlayShapeRow = aByteArrayArrayArray8[zoneX];
                    @Pc(197) byte[][] borderRow = aByteArrayArrayArray7[zoneX];
                    @Pc(201) byte[][] overlay2Row = aByteArrayArrayArray10[zoneX];
                    pixelX2 += screenX1;
                    @Pc(209) byte[][] overlay2IdRow = aByteArrayArrayArray12[zoneX];
                    for (z = 0; z < mapHeight; z++) {
                        y = scaleY * z >> 16;
                        local233 = (z + 1) * scaleY >> 16;
                        @Pc(238) int local238 = local233 - y;
                        if (local238 > 0) {
                            local233 += screenY1;
                            zoneZ = mapTop + z >> 6;
                            @Pc(260) int localZ = mapTop + z & 0x3F;
                            y += screenY1;
                            localX = x + mapLeft & 0x3F;
                            tileIndex = (localZ << 6) + localX;
                            if (zoneZ < 0 || colorData.length - 1 < zoneZ || colorData[zoneZ] == null) {
                                if (area.backgroundColor != -1) {
                                    local312 = area.backgroundColor;
                                } else if ((z + mapTop & 0x4) == (mapLeft + x & 0x4)) {
                                    local312 = overlayColors[FloorOverlayType.anInt865 + 1];
                                } else {
                                    local312 = 4936552;
                                }
                                if (zoneZ < 0 || zoneZ > colorData.length - 1) {
                                    if (local312 == 0) {
                                        local312 = 1;
                                    }
                                    SoftwareRenderer.fillRect(pixelX1, y, pixelWidth, local238, local312);
                                    continue;
                                }
                            } else {
                                local312 = colorData[zoneZ][tileIndex];
                            }
                            overlayColor = overlayIdRow[zoneZ] == null ? 0 : overlayColors[overlayIdRow[zoneZ][tileIndex] & 0xFF];
                            if (local312 == 0) {
                                local312 = 1;
                            }
                            @Pc(395) int overlay2Color = overlay2IdRow[zoneZ] == null ? 0 : overlayColors[overlay2IdRow[zoneZ][tileIndex] & 0xFF];
                            @Pc(437) int local437;
                            if (overlayColor == 0 && overlay2Color == 0) {
                                SoftwareRenderer.fillRect(pixelX1, y, pixelWidth, local238, local312);
                            } else {
                                @Pc(433) byte shape;
                                if (overlayColor != 0) {
                                    if (overlayColor == -1) {
                                        overlayColor = 1;
                                    }
                                    shape = overlayShapeRow[zoneZ] == null ? 0 : overlayShapeRow[zoneZ][tileIndex];
                                    local437 = shape & 0xFC;
                                    if (local437 == 0 || pixelWidth <= 1 || local238 <= 1) {
                                        SoftwareRenderer.fillRect(pixelX1, y, pixelWidth, local238, overlayColor);
                                    } else {
                                        renderFloorOverlayPattern(SoftwareRenderer.pixels, overlayColor, pixelX1, shape & 0x3, local312, local437 >> 2, local238, pixelWidth, y, true);
                                    }
                                }
                                if (overlay2Color != 0) {
                                    if (overlay2Color == -1) {
                                        overlay2Color = local312;
                                    }
                                    shape = overlay2Row[zoneZ][tileIndex];
                                    local437 = shape & 0xFC;
                                    if (local437 == 0 || pixelWidth <= 1 || local238 <= 1) {
                                        SoftwareRenderer.fillRect(pixelX1, y, pixelWidth, local238, overlay2Color);
                                    }
                                    renderFloorOverlayPattern(SoftwareRenderer.pixels, overlay2Color, pixelX1, shape & 0x3, 0, local437 >> 2, local238, pixelWidth, y, overlayColor == 0);
                                }
                            }
                            if (borderRow[zoneZ] != null) {
                                @Pc(546) int borderType = borderRow[zoneZ][tileIndex] & 0xFF;
                                if (borderType != 0) {
                                    if (pixelWidth == 1) {
                                        local437 = pixelX1;
                                    } else {
                                        local437 = pixelX2 - 1;
                                    }
                                    @Pc(569) int local569;
                                    if (local238 == 1) {
                                        local569 = y;
                                    } else {
                                        local569 = local233 - 1;
                                    }
                                    @Pc(575) int edgeColor = 13421772;
                                    if (borderType >= 5 && borderType <= 8 || borderType >= 13 && borderType <= 16 || borderType >= 21 && borderType <= 24 || borderType == 27 || borderType == 28) {
                                        edgeColor = 13369344;
                                        borderType -= 4;
                                    }
                                    if (borderType == 1) {
                                        SoftwareRenderer.drawVerticalLine(pixelX1, y, local238, edgeColor);
                                    } else if (borderType == 2) {
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, y, pixelWidth, edgeColor);
                                    } else if (borderType == 3) {
                                        SoftwareRenderer.drawVerticalLine(local437, y, local238, edgeColor);
                                    } else if (borderType == 4) {
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, local569, pixelWidth, edgeColor);
                                    } else if (borderType == 9) {
                                        SoftwareRenderer.drawVerticalLine(pixelX1, y, local238, 16777215);
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, y, pixelWidth, edgeColor);
                                    } else if (borderType == 10) {
                                        SoftwareRenderer.drawVerticalLine(local437, y, local238, 16777215);
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, y, pixelWidth, edgeColor);
                                    } else if (borderType == 11) {
                                        SoftwareRenderer.drawVerticalLine(local437, y, local238, 16777215);
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, local569, pixelWidth, edgeColor);
                                    } else if (borderType == 12) {
                                        SoftwareRenderer.drawVerticalLine(pixelX1, y, local238, 16777215);
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, local569, pixelWidth, edgeColor);
                                    } else if (borderType == 17) {
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, y, 1, edgeColor);
                                    } else if (borderType == 18) {
                                        SoftwareRenderer.drawHorizontalLine(local437, y, 1, edgeColor);
                                    } else if (borderType == 19) {
                                        SoftwareRenderer.drawHorizontalLine(local437, local569, 1, edgeColor);
                                    } else if (borderType == 20) {
                                        SoftwareRenderer.drawHorizontalLine(pixelX1, local569, 1, edgeColor);
                                    } else {
                                        @Pc(705) int local705;
                                        if (borderType == 25) {
                                            for (local705 = 0; local705 < local238; local705++) {
                                                SoftwareRenderer.drawHorizontalLine(local705 + pixelX1, -local705 + local569, 1, edgeColor);
                                            }
                                        } else if (borderType == 26) {
                                            for (local705 = 0; local705 < local238; local705++) {
                                                SoftwareRenderer.drawHorizontalLine(local705 + pixelX1, y + local705, 1, edgeColor);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    pixelX1 += screenX1;
                    for (@Pc(90) int local90 = 0; local90 < mapHeight; local90++) {
                        if (area.backgroundColor != -1) {
                            color = area.backgroundColor;
                        } else if ((x + mapLeft & 0x4) == (local90 + mapTop & 0x4)) {
                            color = overlayColors[FloorOverlayType.anInt865 + 1];
                        } else {
                            color = 4936552;
                        }
                        if (color == 0) {
                            color = 1;
                        }
                        pixelY1 = (scaleY * local90 >> 16) + screenY1;
                        pixelY2 = screenY1 + ((local90 + 1) * scaleY >> 16);
                        pixelHeight = pixelY2 - pixelY1;
                        SoftwareRenderer.fillRect(pixelX1, pixelY1, pixelWidth, pixelHeight, color);
                    }
                }
            }
        }
        for (x = -2; x < mapWidth + 2; x++) {
            pixelX1 = x * scaleX >> 16;
            pixelX2 = scaleX * (x + 1) >> 16;
            pixelWidth = pixelX2 - pixelX1;
            if (pixelWidth > 0) {
                pixelX1 += screenX1;
                zoneX = mapLeft + x >> 6;
                if (zoneX >= 0 && scenery.length - 1 >= zoneX) {
                    colorData = scenery[zoneX];
                    for (color = -2; color < mapHeight + 2; color++) {
                        pixelY1 = color * scaleY >> 16;
                        pixelY2 = (color + 1) * scaleY >> 16;
                        pixelHeight = pixelY2 - pixelY1;
                        if (pixelHeight > 0) {
                            pixelY1 += screenY1;
                            @Pc(931) int local931 = color + mapTop >> 6;
                            if (local931 >= 0 && local931 <= colorData.length - 1) {
                                z = ((mapTop + color & 0x3F) << 6) + (x + mapLeft & 0x3F);
                                if (colorData[local931] != null) {
                                    y = colorData[local931][z];
                                    local233 = y & 0x3FFF;
                                    if (local233 != 0) {
                                        zoneZ = y >> 14 & 0x3;
                                        @Pc(998) MSIType local998 = MSITypeList.get(local233 - 1);
                                        @Pc(1003) SoftwareIndexedSprite local1003 = local998.getSprite(zoneZ);
                                        if (local1003 != null) {
                                            tileIndex = pixelHeight * local1003.height / 4;
                                            localX = pixelWidth * local1003.width / 4;
                                            if (local998.aBoolean2) {
                                                local312 = y >> 16 & 0xF;
                                                overlayColor = y >> 20 & 0xF;
                                                if ((zoneZ & 0x1) == 1) {
                                                    zoneZ = local312;
                                                    local312 = overlayColor;
                                                    overlayColor = zoneZ;
                                                }
                                                localX = pixelWidth * local312;
                                                tileIndex = pixelHeight * overlayColor;
                                            }
                                            if (localX != 0 && tileIndex != 0) {
                                                if (local998.anInt11 == 0) {
                                                    local1003.method1398(pixelX1, pixelY1 + pixelHeight - tileIndex, localX, tileIndex);
                                                } else {
                                                    local1003.method1390(pixelX1, pixelY1 + pixelHeight - tileIndex, localX, tileIndex, local998.anInt11);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!me", name = "a", descriptor = "(IB)V")
    public static void setTargetZoom(@OriginalArg(0) int zoomLevel) {
        targetCenterZ = -1;
        if (zoomLevel == 37) {
            targetZoom = 3.0F;
        } else if (zoomLevel == 50) {
            targetZoom = 4.0F;
        } else if (zoomLevel == 75) {
            targetZoom = 6.0F;
        } else if (zoomLevel == 100) {
            targetZoom = 8.0F;
        } else if (zoomLevel == 200) {
            targetZoom = 16.0F;
        }
        targetCenterZ = -1;
    }

    @OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Lclient!na;Z)Lclient!na;")
    public static JString method923(@OriginalArg(0) JString text) {
        @Pc(12) int labelIndex = findLabelIndexFuzzy(text);
        return labelIndex == -1 ? EMPTY_STRING : labels.text[labelIndex].method3140(SPACE_STRING, LINE_BREAK);
    }

    @OriginalMember(owner = "runetek4.client!rg", name = "d", descriptor = "(B)Lclient!bn;")
    public static Map getCurrentMap() {
        return area;
    }

    @OriginalMember(owner = "runetek4.client!rg", name = "a", descriptor = "(IIIIIIIII)V")
    public static void renderMapToBuffer(@OriginalArg(0) int screenX2, @OriginalArg(1) int screenY1, @OriginalArg(3) int mapRight, @OriginalArg(4) int mapTop, @OriginalArg(5) int screenX1, @OriginalArg(6) int screenY2, @OriginalArg(7) int mapBottom, @OriginalArg(8) int mapLeft) {
        @Pc(7) int mapWidth = mapRight - mapLeft;
        @Pc(16) int scaleX = (screenX2 - screenX1 << 16) / mapWidth;
        @Pc(21) int mapHeight = screenY2 - mapTop;
        @Pc(30) int scaleY = (mapBottom - screenY1 << 16) / mapHeight;
        renderMapTilesToBuffer(screenY1, screenY2, mapTop, mapRight, screenX1, mapLeft, scaleY, scaleX);
    }

    @OriginalMember(owner = "runetek4.client!sm", name = "a", descriptor = "(IIIIIIIIIII)V")
    public static void renderMapIconsToBuffer(@OriginalArg(0) int screenX1, @OriginalArg(1) int screenY2, @OriginalArg(2) int mapRight, @OriginalArg(3) int scaleY, @OriginalArg(4) int mapLeft, @OriginalArg(8) int scaleX, @OriginalArg(9) int mapTop, @OriginalArg(10) int screenY1) {
        @Pc(9) int mapWidth = mapRight - mapLeft;
        @Pc(11) int flashAlpha = -1;
        if (mapFunctionCount > 0) {
            if (ClientScriptRunner.mapFunctionFlashTimer <= 10) {
                flashAlpha = ClientScriptRunner.mapFunctionFlashTimer * 5;
            } else {
                flashAlpha = 50 - (ClientScriptRunner.mapFunctionFlashTimer - 10) * 5;
            }
        }
        @Pc(39) int mapHeight = screenY2 - mapTop;
        @Pc(43) int xPadding = 983040 / scaleX;
        @Pc(47) int zPadding = 983040 / scaleY;
        for (@Pc(50) int x = -xPadding; x < mapWidth + xPadding; x++) {
            @Pc(65) int pixelX1 = x * scaleX >> 16;
            @Pc(75) int pixelX2 = scaleX * (x + 1) >> 16;
            @Pc(80) int pixelWidth = pixelX2 - pixelX1;
            if (pixelWidth > 0) {
                @Pc(91) int zoneX = mapLeft + x >> 6;
                pixelX1 += screenX1;
                if (zoneX >= 0 && zoneX <= underlayColors.length - 1) {
                    @Pc(116) int[][] underlayColorRow = underlayColors[zoneX];
                    for (@Pc(119) int z = -zPadding; z < mapHeight + zPadding; z++) {
                        @Pc(136) int pixelY2 = scaleY * (z + 1) >> 16;
                        @Pc(144) int pixelY1 = z * scaleY >> 16;
                        @Pc(149) int i = pixelY2 - pixelY1;
                        if (i > 0) {
                            pixelY1 += screenY1;
                            @Pc(163) int zoneZ = mapTop + z >> 6;
                            if (zoneZ >= 0 && zoneZ <= underlayColorRow.length - 1 && underlayColorRow[zoneZ] != null) {
                                @Pc(203) int tileIndex = (x + mapLeft & 0x3F) + ((mapTop + z & 0x3F) << 6);
                                @Pc(209) int locId = underlayColorRow[zoneZ][tileIndex];
                                if (locId != 0) {
                                    @Pc(222) LocType locType = LocTypeList.get(locId - 1);
                                    if (!MapList.aBooleanArray130[locType.mapelement]) {
                                        if (flashAlpha != -1 && locType.mapelement == selectedMapFunctionId) {
                                            @Pc(243) MapFunction icon = new MapFunction();
                                            icon.x = pixelX1;
                                            icon.z = pixelY1;
                                            icon.id = locType.mapelement;
                                            mapElementCache.push(icon);
                                        } else {
                                            MapList.sprites[locType.mapelement].render(pixelX1 - 7, pixelY1 + -7);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (@Pc(285) MapFunction icon = (MapFunction) mapElementCache.head(); icon != null; icon = (MapFunction) mapElementCache.next()) {
            SoftwareRenderer.drawCircleAlpha(icon.x, icon.z, 15, flashAlpha);
            SoftwareRenderer.drawCircleAlpha(icon.x, icon.z, 13, flashAlpha);
            SoftwareRenderer.drawCircleAlpha(icon.x, icon.z, 11, flashAlpha);
            SoftwareRenderer.drawCircleAlpha(icon.x, icon.z, 9, flashAlpha);
            MapList.sprites[icon.id].render(icon.x - 7, icon.z + -7);
        }
        mapElementCache.clear();
    }

    @OriginalMember(owner = "runetek4.client!va", name = "c", descriptor = "(BI)V")
    public static void selectAndFlashMapFunction(@OriginalArg(1) int iconId) {
        selectedMapFunctionId = iconId;
        ClientScriptRunner.mapFunctionFlashTimer = 20;
        mapFunctionCount = 3;
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "([IIIIIIIIIZB)V")
    public static void renderFloorOverlayPattern(@OriginalArg(0) int[] pixels, @OriginalArg(1) int color, @OriginalArg(2) int x, @OriginalArg(3) int rotation, @OriginalArg(4) int underlayColor, @OriginalArg(5) int shapeId, @OriginalArg(6) int height, @OriginalArg(7) int width, @OriginalArg(8) int y, @OriginalArg(9) boolean drawUnderlay) {
        @Pc(7) int clippedX = x;
        if (SoftwareRenderer.clipRight <= x) {
            return;
        }
        if (x < SoftwareRenderer.clipLeft) {
            clippedX = SoftwareRenderer.clipLeft;
        }
        @Pc(30) int maxX = width + x;
        if (SoftwareRenderer.clipLeft >= maxX) {
            return;
        }
        if (SoftwareRenderer.clipRight < maxX) {
            maxX = SoftwareRenderer.clipRight;
        }
        @Pc(43) int clippedY = y;
        if (SoftwareRenderer.clipBottom <= y) {
            return;
        }
        @Pc(56) int maxY = y + height;
        if (y < SoftwareRenderer.clipTop) {
            clippedY = SoftwareRenderer.clipTop;
        }
        if (maxY <= SoftwareRenderer.clipTop) {
            return;
        }
        @Pc(79) int pixelIndex = clippedX + SoftwareRenderer.width * clippedY;
        if (shapeId == 9) {
            rotation = rotation + 1 & 0x3;
            shapeId = 1;
        }
        @Pc(99) int rowStride = clippedX + SoftwareRenderer.width - maxX;
        clippedY -= y;
        @Pc(108) int clippedHeight = height - clippedY;
        if (SoftwareRenderer.clipBottom < maxY) {
            maxY = SoftwareRenderer.clipBottom;
        }
        if (shapeId == 10) {
            rotation = rotation + 3 & 0x3;
            shapeId = 1;
        }
        clippedX -= x;
        @Pc(136) int clippedWidth = width - clippedX;
        if (shapeId == 11) {
            rotation = rotation + 3 & 0x3;
            shapeId = 8;
        }
        maxX -= x;
        @Pc(157) int rightWidth = width - maxX;
        maxY -= y;
        @Pc(165) int bottomHeight = height - maxY;
        @Pc(175) int local175;
        @Pc(184) int local184;
        if (shapeId == 1) {
            if (rotation == 0) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local184 <= local175) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 1) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local175 >= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 2) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local184 >= local175) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 3) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local184 >= local175) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            }
        } else if (shapeId == 2) {
            if (rotation == 0) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local175 >> 1 >= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 1) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (pixelIndex >= 0 && pixelIndex < pixels.length) {
                            if (local175 << 1 <= local184) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        } else {
                            pixelIndex++;
                        }
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 2) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                        if (local175 >> 1 >= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 3) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                        if (local175 << 1 <= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            }
        } else if (shapeId == 3) {
            if (rotation == 0) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                        if (local175 >> 1 >= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 1) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local184 >= local175 << 1) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 2) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local184 <= local175 >> 1) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 3) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                        if (local175 << 1 <= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            }
        } else if (shapeId == 4) {
            if (rotation == 0) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local175 >> 1 <= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 1) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedX; local184 < maxX; local184++) {
                        if (local175 << 1 >= local184) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 2) {
                for (local175 = clippedY; local175 < maxY; local175++) {
                    for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                        if (local184 >= local175 >> 1) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            } else if (rotation == 3) {
                for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                    for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                        if (local184 <= local175 << 1) {
                            pixels[pixelIndex] = color;
                        } else if (drawUnderlay) {
                            pixels[pixelIndex] = underlayColor;
                        }
                        pixelIndex++;
                    }
                    pixelIndex += rowStride;
                }
            }
        } else if (shapeId != 5) {
            if (shapeId == 6) {
                if (rotation == 0) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local184 <= width / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 1) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local175 <= height / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 2) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local184 >= width / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 3) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local175 >= height / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
            }
            if (shapeId == 7) {
                if (rotation == 0) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local184 <= local175 - height / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 1) {
                    for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local175 - height / 2 >= local184) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 2) {
                    for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                        for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                            if (local184 <= local175 - height / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 3) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                            if (local175 - height / 2 >= local184) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
            }
            if (shapeId == 8) {
                if (rotation == 0) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local175 - height / 2 <= local184) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 1) {
                    for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                        for (local184 = clippedX; local184 < maxX; local184++) {
                            if (local175 - height / 2 <= local184) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 2) {
                    for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                        for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                            if (local184 >= local175 - height / 2) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
                if (rotation == 3) {
                    for (local175 = clippedY; local175 < maxY; local175++) {
                        for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                            if (local175 - height / 2 <= local184) {
                                pixels[pixelIndex] = color;
                            } else if (drawUnderlay) {
                                pixels[pixelIndex] = underlayColor;
                            }
                            pixelIndex++;
                        }
                        pixelIndex += rowStride;
                    }
                    return;
                }
            }
        } else if (rotation == 0) {
            for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                    if (local175 >> 1 <= local184) {
                        pixels[pixelIndex] = color;
                    } else if (drawUnderlay) {
                        pixels[pixelIndex] = underlayColor;
                    }
                    pixelIndex++;
                }
                pixelIndex += rowStride;
            }
        } else if (rotation == 1) {
            for (local175 = clippedHeight - 1; local175 >= bottomHeight; local175--) {
                for (local184 = clippedX; local184 < maxX; local184++) {
                    if (local184 <= local175 << 1) {
                        pixels[pixelIndex] = color;
                    } else if (drawUnderlay) {
                        pixels[pixelIndex] = underlayColor;
                    }
                    pixelIndex++;
                }
                pixelIndex += rowStride;
            }
        } else if (rotation == 2) {
            for (local175 = clippedY; local175 < maxY; local175++) {
                for (local184 = clippedX; local184 < maxX; local184++) {
                    if (local184 >= local175 >> 1) {
                        pixels[pixelIndex] = color;
                    } else if (drawUnderlay) {
                        pixels[pixelIndex] = underlayColor;
                    }
                    pixelIndex++;
                }
                pixelIndex += rowStride;
            }
        } else if (rotation == 3) {
            for (local175 = clippedY; local175 < maxY; local175++) {
                for (local184 = clippedWidth - 1; local184 >= rightWidth; local184--) {
                    if (local175 << 1 >= local184) {
                        pixels[pixelIndex] = color;
                    } else if (drawUnderlay) {
                        pixels[pixelIndex] = underlayColor;
                    }
                    pixelIndex++;
                }
                pixelIndex += rowStride;
            }
        }
    }

    @OriginalMember(owner = "client!a", name = "a", descriptor = "(IIIII)V")
    public static void renderWorldMap(@OriginalArg(0) int x, @OriginalArg(1) int height, @OriginalArg(2) int width, @OriginalArg(4) int y) {
        if (GlRenderer.enabled) {
            GlRaster.setClip(x, y, width + x, height + y);
            GlRaster.fillRect(x, y, width, height, 0);
        } else {
            SoftwareRenderer.setClip(x, y, width + x, y + height);
            SoftwareRenderer.fillRect(x, y, width, height, 0);
        }

        if (loadPercentage < 100) {
            return;
        }

        if (cachedMapSprite == null || width != cachedMapSprite.width || cachedMapSprite.height != height) {
            @Pc(63) SoftwareSprite mapSprite = new SoftwareSprite(width, height);
            SoftwareRenderer.setSize(mapSprite.pixels, width, height);
            renderMapToBuffer(width, 0, WorldMap.areaWidth, 0, 0, areaHeight, height, 0);
            if (GlRenderer.enabled) {
                cachedMapSprite = new GlSprite(mapSprite);
            } else {
                cachedMapSprite = mapSprite;
            }
            if (GlRenderer.enabled) {
                SoftwareRenderer.pixels = null;
            } else {
                SoftwareRenderer.frameBuffer.makeTarget();
            }
        }
        cachedMapSprite.drawPixels(x, y);
        @Pc(147) int viewportY = height * ClientScriptRunner.worldMapViewportY / areaHeight + y;
        @Pc(153) int viewportHeight = viewportDoubleHeight * height / areaHeight;
        @Pc(161) int viewportX = x + width * ClientScriptRunner.worldMapViewportX / WorldMap.areaWidth;
        @Pc(167) int viewportWidth = width * viewportDoubleWidth / WorldMap.areaWidth;
        @Pc(169) int viewportColor = 16711680;
        if (Client.game == 1) {
            viewportColor = 16777215;
        }
        if (GlRenderer.enabled) {
            GlRaster.fillRectAlpha(viewportX, viewportY, viewportWidth, viewportHeight, viewportColor, 128);
            GlRaster.drawRect(viewportX, viewportY, viewportWidth, viewportHeight, viewportColor);
        } else {
            SoftwareRenderer.fillRectAlpha(viewportX, viewportY, viewportWidth, viewportHeight, viewportColor, 128);
            SoftwareRenderer.drawRect(viewportX, viewportY, viewportWidth, viewportHeight, viewportColor);
        }
        if (mapFunctionCount <= 0) {
            return;
        }
        @Pc(225) int alpha;
        if (ClientScriptRunner.mapFunctionFlashTimer > 10) {
            alpha = (20 - ClientScriptRunner.mapFunctionFlashTimer) * 25;
        } else {
            alpha = ClientScriptRunner.mapFunctionFlashTimer * 25;
        }
        for (@Pc(238) MapFunction mapFunction = (MapFunction) elements.head(); mapFunction != null; mapFunction = (MapFunction) elements.next()) {
            if (mapFunction.id == selectedMapFunctionId) {
                @Pc(267) int local267 = width * mapFunction.x / WorldMap.areaWidth + x;
                @Pc(258) int local258 = y + mapFunction.z * height / areaHeight;
                if (GlRenderer.enabled) {
                    GlRaster.fillRectAlpha(local267 - 2, local258 - 2, 4, 4, 16776960, alpha);
                } else {
                    SoftwareRenderer.fillRectAlpha(local267 - 2, local258 - 2, 4, 4, 16776960, alpha);
                }
            }
        }
    }
}
