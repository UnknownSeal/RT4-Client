package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.config.FluType;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.game.config.flotype.FloType;
import com.jagex.runetek4.game.config.flotype.FloTypeList;
import com.jagex.runetek4.game.config.meltype.MapElementList;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WorldMap {
    @OriginalMember(owner = "runetek4.client!nc", name = "e", descriptor = "Lclient!na;")
    public static final JString UNDERLAY = JString.parse("underlay");
    @OriginalMember(owner = "runetek4.client!vj", name = "m", descriptor = "Lclient!na;")
    public static final JString LABELS = JString.parse("_labels");
    @OriginalMember(owner = "runetek4.client!ac", name = "m", descriptor = "Lclient!na;")
    public static final JString OVERLAY = JString.parse("overlay");
    @OriginalMember(owner = "client!fm", name = "gb", descriptor = "Lclient!na;")
    public static final JString OVERLAY2 = JString.parse("overlay2");
    @OriginalMember(owner = "client!df", name = "c", descriptor = "Lclient!na;")
    public static final JString LOC = JString.parse("loc");
    @OriginalMember(owner = "runetek4.client!vk", name = "h", descriptor = "I")
    public static final int anInt5338 = (int) (Math.random() * 33.0D) - 16;
    @OriginalMember(owner = "runetek4.client!kd", name = "rb", descriptor = "I")
    public static final int anInt3254 = (int) (Math.random() * 17.0D) - 8;
    @OriginalMember(owner = "runetek4.client!lf", name = "c", descriptor = "Lclient!ih;")
    public static final LinkedList mapElements = new LinkedList();
    @OriginalMember(owner = "runetek4.client!he", name = "db", descriptor = "Lclient!na;")
    public static final JString aClass100_517 = JString.parse("");
    @OriginalMember(owner = "runetek4.client!hm", name = "T", descriptor = "Lclient!na;")
    public static final JString aClass100_538 = JString.parse(" ");
    @OriginalMember(owner = "runetek4.client!pm", name = "Y", descriptor = "Lclient!na;")
    public static final JString aClass100_872 = JString.parse("<br>");
    @OriginalMember(owner = "runetek4.client!nj", name = "h", descriptor = "Lclient!ih;")
    public static final LinkedList aClass69_97 = new LinkedList();
    @OriginalMember(owner = "client!di", name = "q", descriptor = "[Lclient!na;")
    public static final JString[] aClass100Array53 = new JString[5];
    @OriginalMember(owner = "client!fi", name = "j", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_2;
    @OriginalMember(owner = "client!dc", name = "O", descriptor = "I")
    public static int loadPercentage = 0;
    @OriginalMember(owner = "runetek4.client!qf", name = "S", descriptor = "I")
    public static int anInt1864;
    @OriginalMember(owner = "runetek4.client!oi", name = "m", descriptor = "I")
    public static int length;
    @OriginalMember(owner = "client!bn", name = "N", descriptor = "Lclient!be;")
    public static Component component;
    @OriginalMember(owner = "client!bc", name = "W", descriptor = "I")
    public static int anInt435;
    @OriginalMember(owner = "client!cd", name = "u", descriptor = "I")
    public static int anInt919;
    @OriginalMember(owner = "runetek4.client!mh", name = "S", descriptor = "I")
    public static int originX;
    @OriginalMember(owner = "runetek4.client!aa", name = "j", descriptor = "I")
    public static int originZ;
    @OriginalMember(owner = "runetek4.client!wa", name = "ub", descriptor = "Lclient!bn;")
    public static Map currentMap;
    @OriginalMember(owner = "client!gj", name = "r", descriptor = "F")
    public static float zoom;
    @OriginalMember(owner = "runetek4.client!km", name = "uc", descriptor = "F")
    public static float targetZoom;
    @OriginalMember(owner = "client!dl", name = "e", descriptor = "I")
    public static int width;
    @OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "[I")
    public static int[] overlayColors;
    @OriginalMember(owner = "runetek4.client!wb", name = "l", descriptor = "Lclient!fd;")
    public static WorldMapFont font26;
    @OriginalMember(owner = "runetek4.client!mj", name = "n", descriptor = "Lclient!fd;")
    public static WorldMapFont font30;
    @OriginalMember(owner = "runetek4.client!kc", name = "C", descriptor = "Lclient!fd;")
    public static WorldMapFont font22;
    @OriginalMember(owner = "runetek4.client!qh", name = "d", descriptor = "Lclient!fd;")
    public static WorldMapFont font19;
    @OriginalMember(owner = "runetek4.client!kc", name = "n", descriptor = "Lclient!fd;")
    public static WorldMapFont font17;
    @OriginalMember(owner = "runetek4.client!nf", name = "d", descriptor = "Lclient!fd;")
    public static WorldMapFont font14;
    @OriginalMember(owner = "runetek4.client!ma", name = "q", descriptor = "Lclient!fd;")
    public static WorldMapFont font12;
    @OriginalMember(owner = "runetek4.client!we", name = "v", descriptor = "Lclient!fd;")
    public static WorldMapFont font11;
    @OriginalMember(owner = "runetek4.client!rj", name = "P", descriptor = "I")
    public static int anInt4901 = -1;
    @OriginalMember(owner = "runetek4.client!lc", name = "l", descriptor = "I")
    public static int anInt3482 = -1;
    @OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "Lclient!se;")
    public static MapElementList labels;
    @OriginalMember(owner = "runetek4.client!ck", name = "J", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray3;
    @OriginalMember(owner = "client!fi", name = "m", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray7;
    @OriginalMember(owner = "runetek4.client!hb", name = "v", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray8;
    @OriginalMember(owner = "runetek4.client!si", name = "R", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray12;
    @OriginalMember(owner = "runetek4.client!jl", name = "I", descriptor = "[[[B")
    public static byte[][][] aByteArrayArrayArray10;
    @OriginalMember(owner = "client!gj", name = "i", descriptor = "[[[I")
    public static int[][][] underlayColors;
    @OriginalMember(owner = "runetek4.client!uc", name = "d", descriptor = "[[[I")
    public static int[][][] anIntArrayArrayArray17;
    @OriginalMember(owner = "runetek4.client!mc", name = "Q", descriptor = "Lclient!na;")
    public static JString aClass100_724;
    @OriginalMember(owner = "runetek4.client!mc", name = "S", descriptor = "Lclient!mm;")
    public static SoftwareSprite aClass3_Sub2_Sub1_Sub1_2;
    @OriginalMember(owner = "runetek4.client!ha", name = "o", descriptor = "I")
    public static int anInt2387;
    @OriginalMember(owner = "client!cm", name = "c", descriptor = "I")
    public static int anInt1176;
    @OriginalMember(owner = "runetek4.client!sm", name = "m", descriptor = "I")
    public static int anInt5212;
    @OriginalMember(owner = "client!al", name = "e", descriptor = "I")
    public static int anInt172;
    @OriginalMember(owner = "client!eh", name = "g", descriptor = "[[[I")
    public static int[][][] scenery;

    @OriginalMember(owner = "client!jb", name = "a", descriptor = "(IZ)V")
    public static void clear(@OriginalArg(1) boolean arg0) {
        aByteArrayArrayArray8 = null;
        underlayColors = null;
        component = null;
        aByteArrayArrayArray3 = null;
        overlayColors = null;
        aByteArrayArrayArray10 = null;
        if (arg0 && currentMap != null) {
            aClass100_724 = currentMap.group;
        } else {
            aClass100_724 = null;
        }
        aByteArrayArrayArray7 = null;
        aByteArrayArrayArray12 = null;
        scenery = null;
        anIntArrayArrayArray17 = null;
        loadPercentage = 0;
        currentMap = null;
        mapElements.clear();
        labels = null;
        anInt4901 = -1;
        font22 = null;
        font30 = null;
        font12 = null;
        font26 = null;
        font11 = null;
        font14 = null;
        font17 = null;
        font19 = null;
        aClass3_Sub2_Sub1_2 = null;
        anInt3482 = -1;
        aClass3_Sub2_Sub1_Sub1_2 = null;
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IIIII)V")
    public static void method2225(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        if (loadPercentage < 100) {
            load();
        }
        if (GlRenderer.enabled) {
            GlRaster.setClip(arg0, arg1, arg0 + arg3, arg2 + arg1);
        } else {
            SoftwareRaster.setClip(arg0, arg1, arg0 + arg3, arg2 + arg1);
        }
        @Pc(50) int local50;
        @Pc(61) int local61;
        if (loadPercentage < 100) {
            local50 = arg0 + arg3 / 2;
            local61 = arg2 / 2 + arg1 - 18 - 20;
            if (GlRenderer.enabled) {
                GlRaster.fillRect(arg0, arg1, arg3, arg2, 0);
                GlRaster.drawRect(local50 - 152, local61, 304, 34, 9179409);
                GlRaster.drawRect(local50 - 151, local61 + 1, 302, 32, 0);
                GlRaster.fillRect(local50 - 150, local61 + 2, loadPercentage * 3, 30, 9179409);
                GlRaster.fillRect(local50 + loadPercentage * 3 - 150, local61 - -2, 300 - loadPercentage * 3, 30, 0);
            } else {
                SoftwareRaster.fillRect(arg0, arg1, arg3, arg2, 0);
                SoftwareRaster.drawRect(local50 - 152, local61, 304, 34, 9179409);
                SoftwareRaster.drawRect(local50 - 151, local61 + 1, 302, 32, 0);
                SoftwareRaster.fillRect(local50 - 150, local61 + 2, loadPercentage * 3, 30, 9179409);
                SoftwareRaster.fillRect(loadPercentage * 3 + local50 - 150, local61 - -2, 300 - loadPercentage * 3, 30, 0);
            }
            Fonts.b12Full.renderCenter(LocalizedText.LOADINGDOTDOTDOT, local50, local61 + 20, 16777215, -1);
            return;
        }
        anInt1176 = (int) ((float) (arg2 * 2) / zoom);
        ClientScriptRunner.anInt2882 = anInt435 - (int) ((float) arg3 / zoom);
        @Pc(211) int local211 = anInt435 - (int) ((float) arg3 / zoom);
        local50 = anInt919 - (int) ((float) arg2 / zoom);
        ClientScriptRunner.anInt2884 = anInt919 - (int) ((float) arg2 / zoom);
        @Pc(236) int local236 = anInt919 + (int) ((float) arg2 / zoom);
        local61 = (int) ((float) arg3 / zoom) + anInt435;
        anInt2387 = (int) ((float) (arg3 * 2) / zoom);
        if (GlRenderer.enabled) {
            if (aClass3_Sub2_Sub1_Sub1_2 == null || aClass3_Sub2_Sub1_Sub1_2.width != arg3 || aClass3_Sub2_Sub1_Sub1_2.height != arg2) {
                aClass3_Sub2_Sub1_Sub1_2 = null;
                aClass3_Sub2_Sub1_Sub1_2 = new SoftwareSprite(arg3, arg2);
            }
            SoftwareRaster.setSize(aClass3_Sub2_Sub1_Sub1_2.pixels, arg3, arg2);
            Static214.method4364(arg3, 0, local61, local50, 0, local236, arg2, local211);
            Static48.method1195(arg3, 0, local61, local236, arg2, 0, local211, local50);
            method959(0, 0, local211, arg3, local236, local50, local61, arg2);
            GlRaster.render(aClass3_Sub2_Sub1_Sub1_2.pixels, arg0, arg1, arg3, arg2);
            SoftwareRaster.pixels = null;
        } else {
            Static214.method4364(arg3 + arg0, arg1, local61, local50, arg0, local236, arg1 + arg2, local211);
            Static48.method1195(arg0 + arg3, arg0, local61, local236, arg2 + arg1, arg1, local211, local50);
            method959(arg0, arg1, local211, arg0 + arg3, local236, local50, local61, arg2 + arg1);
        }
        if (anInt1864 > 0) {
            ClientScriptRunner.anInt2428--;
            if (ClientScriptRunner.anInt2428 == 0) {
                ClientScriptRunner.anInt2428 = 20;
                anInt1864--;
            }
        }
        if (!Cheat.displayFps) {
            return;
        }
        @Pc(405) int local405 = arg1 + arg2 - 8;
        @Pc(412) int local412 = arg0 + arg3 - 5;
        Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_FPS, JString.parseInt(GameShell.fps) }), local412, local405, 16776960, -1);
        @Pc(434) Runtime local434 = Runtime.getRuntime();
        @Pc(443) int local443 = (int) ((local434.totalMemory() - local434.freeMemory()) / 1024L);
        @Pc(445) int local445 = 16776960;
        @Pc(446) int local446 = local405 - 15;
        if (local443 > 65536) {
            local445 = 16711680;
        }
        Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.MEM, JString.parseInt(local443), Cheat.DEBUG_MEMORY_UNIT}), local412, local446, local445, -1);
        local405 = local446 - 15;
    }

    @OriginalMember(owner = "client!dk", name = "a", descriptor = "(Lclient!wa;Z)V")
    public static void method3998(@OriginalArg(0) Packet arg0) {
        label87: while (true) {
            if (arg0.offset < arg0.data.length) {
                @Pc(22) int local22 = 0;
                @Pc(24) boolean local24 = false;
                @Pc(26) int local26 = 0;
                if (arg0.g1() == 1) {
                    local24 = true;
                    local22 = arg0.g1();
                    local26 = arg0.g1();
                }
                @Pc(46) int local46 = arg0.g1();
                @Pc(50) int local50 = arg0.g1();
                @Pc(62) int local62 = originZ + length - local50 * 64 - 1;
                @Pc(69) int local69 = local46 * 64 - originX;
                @Pc(147) byte local147;
                @Pc(91) int local91;
                if (local69 >= 0 && local62 - 63 >= 0 && local69 + 63 < width && local62 < length) {
                    local91 = local69 >> 6;
                    @Pc(95) int local95 = local62 >> 6;
                    @Pc(97) int local97 = 0;
                    while (true) {
                        if (local97 >= 64) {
                            continue label87;
                        }
                        for (@Pc(104) int local104 = 0; local104 < 64; local104++) {
                            if (!local24 || local97 >= local22 * 8 && local97 < local22 * 8 + 8 && local104 >= local26 * 8 && local104 < local26 * 8 + 8) {
                                local147 = arg0.g1s();
                                if (local147 != 0) {
                                    if (aByteArrayArrayArray3[local91][local95] == null) {
                                        aByteArrayArrayArray3[local91][local95] = new byte[4096];
                                    }
                                    aByteArrayArrayArray3[local91][local95][local97 + (63 - local104 << 6)] = local147;
                                    @Pc(186) byte local186 = arg0.g1s();
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
                    if ((local24 ? 64 : 4096) <= local91) {
                        continue label87;
                    }
                    local147 = arg0.g1s();
                    if (local147 != 0) {
                        arg0.offset++;
                    }
                    local91++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(II)V")
    public static void method1964(@OriginalArg(0) int arg0) {
        anInt4901 = -1;
        anInt3482 = -1;
        anInt435 = arg0;
        method965();
    }

    @OriginalMember(owner = "runetek4.client!wi", name = "d", descriptor = "(II)V")
    public static void method4641(@OriginalArg(1) int arg0) {
        anInt4901 = -1;
        anInt4901 = -1;
        anInt919 = arg0;
        method965();
    }

    @OriginalMember(owner = "client!je", name = "a", descriptor = "(IIIII)V")
    public static void method2387(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        anInt435 = width * arg2 / arg0;
        anInt919 = length * arg1 / arg3;
        anInt3482 = -1;
        anInt4901 = -1;
        method965();
    }

    @OriginalMember(owner = "runetek4.client!lb", name = "d", descriptor = "(B)V")
    public static void method2720() {
        if (aClass100_724 != null) {
            Static90.method1853(aClass100_724);
            aClass100_724 = null;
        }
    }

    @OriginalMember(owner = "runetek4.client!pa", name = "d", descriptor = "(I)V")
    public static void load() {
        if (currentMap == null) {
            return;
        }
        if (loadPercentage < 10) {
            if (!MapList.archive.isGroupReady(currentMap.group)) {
                loadPercentage = client.js5Archive23.method4478(currentMap.group) / 10;
                return;
            }
            client.method84();
            loadPercentage = 10;
        }
        if (loadPercentage == 10) {
            originX = currentMap.displayMinX >> 6 << 6;
            originZ = currentMap.displayMaxX >> 6 << 6;
            length = (currentMap.displayMinZ >> 6 << 6) + 64 - originZ;
            width = (currentMap.displayMaxZ >> 6 << 6) + 64 - originX;
            if (currentMap.defaultZoom == 37) {
                zoom = 3.0F;
                targetZoom = 3.0F;
            } else if (currentMap.defaultZoom == 50) {
                zoom = 4.0F;
                targetZoom = 4.0F;
            } else if (currentMap.defaultZoom == 75) {
                zoom = 6.0F;
                targetZoom = 6.0F;
            } else if (currentMap.defaultZoom == 100) {
                zoom = 8.0F;
                targetZoom = 8.0F;
            } else if (currentMap.defaultZoom == 200) {
                zoom = 16.0F;
                targetZoom = 16.0F;
            } else {
                zoom = 8.0F;
                targetZoom = 8.0F;
            }
            @Pc(144) int local144 = (PlayerList.self.xFine >> 7) + Camera.originX - originX;
            @Pc(153) int local153 = local144 + (int) (Math.random() * 10.0D) - 5;
            @Pc(168) int local168 = originZ + length - Camera.originZ - (PlayerList.self.zFine >> 7) - 1;
            @Pc(177) int local177 = local168 + (int) (Math.random() * 10.0D) - 5;
            if (local153 >= 0 && width > local153 && local177 >= 0 && local177 < length) {
                anInt435 = local153;
                anInt919 = local177;
            } else {
                anInt919 = originZ + length - currentMap.originZ * 64 - 1;
                anInt435 = currentMap.originX * 64 - originX;
            }
            method965();
            overlayColors = new int[FloTypeList.capacity + 1];
            @Pc(235) int local235 = length >> 6;
            @Pc(239) int local239 = width >> 6;
            aByteArrayArrayArray8 = new byte[local239][local235][];
            @Pc(249) int local249 = SceneGraph.anInt2293 >> 2 << 10;
            aByteArrayArrayArray7 = new byte[local239][local235][];
            underlayColors = new int[local239][local235][];
            aByteArrayArrayArray3 = new byte[local239][local235][];
            anIntArrayArrayArray17 = new int[local239][local235][];
            aByteArrayArrayArray12 = new byte[local239][local235][];
            @Pc(273) int local273 = SceneGraph.anInt4272 >> 1;
            aByteArrayArrayArray10 = new byte[local239][local235][];
            scenery = new int[local239][local235][];
            loadOverlayColors(local273, local249);
            loadPercentage = 20;
        } else if (loadPercentage == 20) {
            readUnderlay(new Packet(MapList.archive.fetchFile(UNDERLAY, currentMap.group)));
            loadPercentage = 30;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 30) {
            method3998(new Packet(MapList.archive.fetchFile(OVERLAY, currentMap.group)));
            loadPercentage = 40;
            GameShell.resetTimer();
        } else if (loadPercentage == 40) {
            PreciseSleep.method3980(new Packet(MapList.archive.fetchFile(OVERLAY2, currentMap.group)));
            loadPercentage = 50;
            GameShell.resetTimer();
        } else if (loadPercentage == 50) {
            readLocs(new Packet(MapList.archive.fetchFile(LOC, currentMap.group)));
            loadPercentage = 60;
            ClientProt.ping(true);
            GameShell.resetTimer();
        } else if (loadPercentage == 60) {
            if (MapList.archive.isGroupNameValid(JString.concatenate(new JString[]{currentMap.group, LABELS}))) {
                if (!MapList.archive.isGroupReady(JString.concatenate(new JString[]{currentMap.group, LABELS}))) {
                    return;
                }
                labels = MapElementList.create(JString.concatenate(new JString[]{currentMap.group, LABELS}), MapList.archive);
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
    public static void readUnderlay(@OriginalArg(1) Packet arg0) {
        @Pc(13) int local13 = anInt5338 >> 1;
        @Pc(19) int local19 = anInt3254 >> 2 << 10;
        @Pc(23) byte[][] local23 = new byte[width][length];
        @Pc(33) int local33;
        @Pc(102) int local102;
        @Pc(114) int local114;
        while (arg0.offset < arg0.data.length) {
            @Pc(31) int local31 = 0;
            local33 = 0;
            @Pc(35) boolean local35 = false;
            if (arg0.g1() == 1) {
                local33 = arg0.g1();
                local31 = arg0.g1();
                local35 = true;
            }
            @Pc(57) int local57 = arg0.g1();
            @Pc(61) int local61 = arg0.g1();
            @Pc(68) int local68 = local57 * 64 - originX;
            @Pc(78) int local78 = length + originZ - local61 * 64 - 1;
            if (local68 >= 0 && local78 - 63 >= 0 && width > local68 + 63 && length > local78) {
                for (local102 = 0; local102 < 64; local102++) {
                    @Pc(112) byte[] local112 = local23[local68 + local102];
                    for (local114 = 0; local114 < 64; local114++) {
                        if (!local35 || local102 >= local33 * 8 && local33 * 8 + 8 > local102 && local114 >= local31 * 8 && local114 < local31 * 8 + 8) {
                            local112[local78 - local114] = arg0.g1s();
                        }
                    }
                }
            } else if (local35) {
                arg0.offset += 64;
            } else {
                arg0.offset += 4096;
            }
        }
        @Pc(175) int local175 = width;
        local33 = length;
        @Pc(180) int[] local180 = new int[local33];
        @Pc(183) int[] local183 = new int[local33];
        @Pc(186) int[] local186 = new int[local33];
        @Pc(189) int[] local189 = new int[local33];
        @Pc(192) int[] local192 = new int[local33];
        for (local102 = -5; local102 < local175; local102++) {
            @Pc(225) int local225;
            @Pc(293) int local293;
            for (@Pc(203) int local203 = 0; local203 < local33; local203++) {
                local114 = local102 + 5;
                @Pc(272) int local272;
                if (local175 > local114) {
                    local225 = local23[local114][local203] & 0xFF;
                    if (local225 > 0) {
                        @Pc(236) FluType local236 = FloorUnderlayTypeList.get(local225 - 1);
                        local183[local203] += local236.weightedHue;
                        local180[local203] += local236.saturation;
                        local186[local203] += local236.lightness;
                        local189[local203] += local236.chroma;
                        local272 = local192[local203]++;
                    }
                }
                local225 = local102 - 5;
                if (local225 >= 0) {
                    local293 = local23[local225][local203] & 0xFF;
                    if (local293 > 0) {
                        @Pc(302) FluType local302 = FloorUnderlayTypeList.get(local293 - 1);
                        local183[local203] -= local302.weightedHue;
                        local180[local203] -= local302.saturation;
                        local186[local203] -= local302.lightness;
                        local189[local203] -= local302.chroma;
                        local272 = local192[local203]--;
                    }
                }
            }
            if (local102 >= 0) {
                @Pc(355) int[][] local355 = anIntArrayArrayArray17[local102 >> 6];
                local114 = 0;
                local225 = 0;
                @Pc(361) int local361 = 0;
                @Pc(363) int local363 = 0;
                local293 = 0;
                for (@Pc(367) int local367 = -5; local367 < local33; local367++) {
                    @Pc(378) int local378 = local367 + 5;
                    if (local33 > local378) {
                        local363 += local192[local378];
                        local225 += local180[local378];
                        local293 += local186[local378];
                        local114 += local183[local378];
                        local361 += local189[local378];
                    }
                    @Pc(415) int local415 = local367 - 5;
                    if (local415 >= 0) {
                        local293 -= local186[local415];
                        local361 -= local189[local415];
                        local114 -= local183[local415];
                        local363 -= local192[local415];
                        local225 -= local180[local415];
                    }
                    if (local367 >= 0 && local363 > 0) {
                        @Pc(462) int[] local462 = local355[local367 >> 6];
                        @Pc(480) int local480 = local361 == 0 ? 0 : ColorUtils.method1309(local293 / local363, local225 / local363, local114 * 256 / local361);
                        if (local23[local102][local367] != 0) {
                            if (local462 == null) {
                                local462 = local355[local367 >> 6] = new int[4096];
                            }
                            @Pc(519) int local519 = local13 + (local480 & 0x7F);
                            if (local519 < 0) {
                                local519 = 0;
                            } else if (local519 > 127) {
                                local519 = 127;
                            }
                            @Pc(541) int local541 = local519 + (local480 & 0x380) + (local480 + local19 & 0xFC00);
                            local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = Rasterizer.palette[ColorUtils.multiplyLightnessSafe(96, local541)];
                        } else if (local462 != null) {
                            local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = 0;
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!nc", name = "a", descriptor = "(BLclient!wa;)V")
    public static void readLocs(@OriginalArg(1) Packet arg0) {
        label123: while (true) {
            if (arg0.data.length > arg0.offset) {
                @Pc(17) boolean local17 = false;
                @Pc(19) int local19 = 0;
                @Pc(21) int local21 = 0;
                if (arg0.g1() == 1) {
                    local19 = arg0.g1();
                    local17 = true;
                    local21 = arg0.g1();
                }
                @Pc(42) int local42 = arg0.g1();
                @Pc(46) int local46 = arg0.g1();
                @Pc(53) int local53 = local42 * 64 - originX;
                @Pc(65) int local65 = originZ + length - local46 * 64 - 1;
                @Pc(84) int local84;
                @Pc(95) int local95;
                if (local53 >= 0 && local65 - 63 >= 0 && width > local53 + 63 && local65 < length) {
                    local84 = local53 >> 6;
                    local95 = local65 >> 6;
                    @Pc(150) int local150 = 0;
                    while (true) {
                        if (local150 >= 64) {
                            continue label123;
                        }
                        for (@Pc(155) int local155 = 0; local155 < 64; local155++) {
                            if (!local17 || local19 * 8 <= local150 && local150 < local19 * 8 + 8 && local155 >= local21 * 8 && local155 < local21 * 8 + 8) {
                                @Pc(202) int local202 = arg0.g1();
                                if (local202 != 0) {
                                    @Pc(214) int local214;
                                    if ((local202 & 0x1) == 1) {
                                        local214 = arg0.g1();
                                        if (aByteArrayArrayArray7[local84][local95] == null) {
                                            aByteArrayArrayArray7[local84][local95] = new byte[4096];
                                        }
                                        aByteArrayArrayArray7[local84][local95][local150 + (63 - local155 << 6)] = (byte) local214;
                                    }
                                    if ((local202 & 0x2) == 2) {
                                        local214 = arg0.g3();
                                        if (scenery[local84][local95] == null) {
                                            scenery[local84][local95] = new int[4096];
                                        }
                                        scenery[local84][local95][(63 - local155 << 6) + local150] = local214;
                                    }
                                    if ((local202 & 0x4) == 4) {
                                        local214 = arg0.g3();
                                        if (underlayColors[local84][local95] == null) {
                                            underlayColors[local84][local95] = new int[4096];
                                        }
                                        local214--;
                                        @Pc(312) LocType local312 = LocTypeList.get(local214);
                                        if (local312.multiloc != null) {
                                            local312 = local312.getMultiLoc();
                                            if (local312 == null || local312.mapfunction == -1) {
                                                continue;
                                            }
                                        }
                                        underlayColors[local84][local95][(63 - local155 << 6) + local150] = local312.id + 1;
                                        @Pc(353) Class3_Sub26 local353 = new Class3_Sub26();
                                        local353.id = local312.mapfunction;
                                        local353.anInt4307 = local53;
                                        local353.anInt4314 = local65;
                                        mapElements.addTail(local353);
                                    }
                                }
                            }
                        }
                        local150++;
                    }
                }
                local84 = 0;
                while (true) {
                    if (local84 >= (local17 ? 64 : 4096)) {
                        continue label123;
                    }
                    local95 = arg0.g1();
                    if (local95 != 0) {
                        if ((local95 & 0x1) == 1) {
                            arg0.offset++;
                        }
                        if ((local95 & 0x2) == 2) {
                            arg0.offset += 2;
                        }
                        if ((local95 & 0x4) == 4) {
                            arg0.offset += 3;
                        }
                    }
                    local84++;
                }
            }
            return;
        }
    }

    @OriginalMember(owner = "client!cn", name = "e", descriptor = "(B)V")
    public static void method965() {
        if (anInt435 < 0) {
            anInt4901 = -1;
            anInt435 = 0;
            anInt3482 = -1;
        }
        if (anInt435 > width) {
            anInt4901 = -1;
            anInt435 = width;
            anInt3482 = -1;
        }
        if (anInt919 < 0) {
            anInt3482 = -1;
            anInt4901 = -1;
            anInt919 = 0;
        }
        if (length < anInt919) {
            anInt919 = length;
            anInt4901 = -1;
            anInt3482 = -1;
        }
    }

    @OriginalMember(owner = "client!fi", name = "a", descriptor = "(III)V")
    public static void loadOverlayColors(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        for (@Pc(11) int local11 = 0; local11 < FloTypeList.capacity; local11++) {
            @Pc(18) FloType local18 = FloTypeList.method4395(local11);
            if (local18 != null) {
                @Pc(24) int local24 = local18.material;
                if (local24 >= 0 && !Rasterizer.textureProvider.method3236(local24)) {
                    local24 = -1;
                }
                @Pc(53) int local53;
                @Pc(66) int local66;
                @Pc(72) int local72;
                @Pc(95) int local95;
                if (local18.secondaryColor >= 0) {
                    local66 = local18.secondaryColor;
                    local72 = (local66 & 0x7F) + arg0;
                    if (local72 < 0) {
                        local72 = 0;
                    } else if (local72 > 127) {
                        local72 = 127;
                    }
                    local95 = (local66 & 0x380) + (arg1 + local66 & 0xFC00) + local72;
                    local53 = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(local95, 96)];
                } else if (local24 >= 0) {
                    local53 = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(Rasterizer.textureProvider.getAverageColor(local24), 96)];
                } else if (local18.baseColor == -1) {
                    local53 = -1;
                } else {
                    local66 = local18.baseColor;
                    local72 = arg0 + (local66 & 0x7F);
                    if (local72 < 0) {
                        local72 = 0;
                    } else if (local72 > 127) {
                        local72 = 127;
                    }
                    local95 = local72 + (local66 & 0x380) + (local66 + arg1 & 0xFC00);
                    local53 = Rasterizer.palette[ColorUtils.multiplyLightnessGrayscale(local95, 96)];
                }
                overlayColors[local11 + 1] = local53;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(ILclient!na;)I")
    public static int method3218(@OriginalArg(1) JString arg0) {
        if (labels == null || arg0.length() == 0) {
            return -1;
        }
        for (@Pc(20) int local20 = 0; local20 < labels.anInt5074; local20++) {
            if (labels.aClass100Array153[local20].method3140(aClass100_538, aClass100_872).method3142(arg0)) {
                return local20;
            }
        }
        return -1;
    }

    @OriginalMember(owner = "client!cn", name = "a", descriptor = "(BIIIIIIII)V")
    public static void method959(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7) {
        for (@Pc(11) int local11 = 0; local11 < labels.anInt5074; local11++) {
            if (labels.method3890(local11)) {
                @Pc(32) int local32 = labels.aShortArray73[local11] - originX;
                @Pc(43) int local43 = originZ + length - labels.aShortArray72[local11] - 1;
                @Pc(59) int local59 = arg0 + (arg3 - arg0) * (local32 - arg2) / (arg6 - arg2);
                @Pc(64) int local64 = labels.method3894(local11);
                @Pc(80) int local80 = (arg7 - arg1) * (local43 - arg5) / (arg4 - arg5) + arg1;
                @Pc(82) int local82 = 16777215;
                @Pc(84) WorldMapFont local84 = null;
                if (local64 == 0) {
                    if ((double) zoom == 3.0D) {
                        local84 = font11;
                    }
                    if ((double) zoom == 4.0D) {
                        local84 = font12;
                    }
                    if ((double) zoom == 6.0D) {
                        local84 = font14;
                    }
                    if ((double) zoom >= 8.0D) {
                        local84 = font17;
                    }
                }
                if (local64 == 1) {
                    if ((double) zoom == 3.0D) {
                        local84 = font14;
                    }
                    if ((double) zoom == 4.0D) {
                        local84 = font17;
                    }
                    if ((double) zoom == 6.0D) {
                        local84 = font19;
                    }
                    if ((double) zoom >= 8.0D) {
                        local84 = font22;
                    }
                }
                if (local64 == 2) {
                    if ((double) zoom == 3.0D) {
                        local84 = font19;
                    }
                    local82 = 16755200;
                    if ((double) zoom == 4.0D) {
                        local84 = font22;
                    }
                    if ((double) zoom == 6.0D) {
                        local84 = font26;
                    }
                    if ((double) zoom >= 8.0D) {
                        local84 = font30;
                    }
                }
                if (labels.anIntArray444[local11] != -1) {
                    local82 = labels.anIntArray444[local11];
                }
                if (local84 != null) {
                    @Pc(211) int local211 = Fonts.p11Full.splitParagraph(labels.aClass100Array153[local11], null, aClass100Array53);
                    local80 -= local84.method1503() * (local211 - 1) / 2;
                    local80 += local84.method1511() / 2;
                    for (@Pc(231) int local231 = 0; local231 < local211; local231++) {
                        @Pc(242) JString local242 = aClass100Array53[local231];
                        if (local211 - 1 > local231) {
                            local242.method3133(local242.length() - 4);
                        }
                        local84.renderStringCenter(local242, local59, local80, local82);
                        local80 += local84.method1503();
                    }
                }
            }
        }
    }
}
