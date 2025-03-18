package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WorldMap {
    @OriginalMember(owner = "client!fi", name = "j", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_2;
    @OriginalMember(owner = "client!dc", name = "O", descriptor = "I")
    public static int loadPercentage = 0;
    @OriginalMember(owner = "runetek4.client!qf", name = "S", descriptor = "I")
    public static int anInt1864;

    @OriginalMember(owner = "client!jb", name = "a", descriptor = "(IZ)V")
    public static void clear(@OriginalArg(1) boolean arg0) {
        Static90.aByteArrayArrayArray8 = null;
        Static83.anIntArrayArrayArray3 = null;
        Static24.component = null;
        Static34.aByteArrayArrayArray3 = null;
        Static145.anIntArray330 = null;
        Static125.aByteArrayArrayArray10 = null;
        if (arg0 && Static269.aClass3_Sub2_Sub4_2 != null) {
            Static153.aClass100_724 = Static269.aClass3_Sub2_Sub4_2.aClass100_138;
        } else {
            Static153.aClass100_724 = null;
        }
        Static70.aByteArrayArrayArray7 = null;
        Static229.aByteArrayArrayArray12 = null;
        Static58.anIntArrayArrayArray5 = null;
        Static248.anIntArrayArrayArray17 = null;
        loadPercentage = 0;
        Static269.aClass3_Sub2_Sub4_2 = null;
        Static145.aClass69_84.clear();
        Static203.aMapElementTypeList_1 = null;
        Static217.anInt4901 = -1;
        Static130.aClass41_2 = null;
        Static160.aClass41_4 = null;
        Static152.aClass41_3 = null;
        Static270.aClass41_9 = null;
        Static273.aClass41_7 = null;
        Static169.aClass41_5 = null;
        Static130.aClass41_1 = null;
        Static203.aClass41_8 = null;
        aClass3_Sub2_Sub1_2 = null;
        Static142.anInt3482 = -1;
        Static153.aClass3_Sub2_Sub1_Sub1_2 = null;
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IIIII)V")
    public static void method2225(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        if (loadPercentage < 100) {
            Preferences.method3413();
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
        Static37.anInt1176 = (int) ((float) (arg2 * 2) / Static83.aFloat3);
        ClientScriptRunner.anInt2882 = Static13.anInt435 - (int) ((float) arg3 / Static83.aFloat3);
        @Pc(211) int local211 = Static13.anInt435 - (int) ((float) arg3 / Static83.aFloat3);
        local50 = Static28.anInt919 - (int) ((float) arg2 / Static83.aFloat3);
        ClientScriptRunner.anInt2884 = Static28.anInt919 - (int) ((float) arg2 / Static83.aFloat3);
        @Pc(236) int local236 = Static28.anInt919 + (int) ((float) arg2 / Static83.aFloat3);
        local61 = (int) ((float) arg3 / Static83.aFloat3) + Static13.anInt435;
        Static89.anInt2387 = (int) ((float) (arg3 * 2) / Static83.aFloat3);
        if (GlRenderer.enabled) {
            if (Static153.aClass3_Sub2_Sub1_Sub1_2 == null || Static153.aClass3_Sub2_Sub1_Sub1_2.width != arg3 || Static153.aClass3_Sub2_Sub1_Sub1_2.height != arg2) {
                Static153.aClass3_Sub2_Sub1_Sub1_2 = null;
                Static153.aClass3_Sub2_Sub1_Sub1_2 = new SoftwareSprite(arg3, arg2);
            }
            SoftwareRaster.setSize(Static153.aClass3_Sub2_Sub1_Sub1_2.pixels, arg3, arg2);
            Static214.method4364(arg3, 0, local61, local50, 0, local236, arg2, local211);
            Static48.method1195(arg3, 0, local61, local236, arg2, 0, local211, local50);
            Static38.method959(0, 0, local211, arg3, local236, local50, local61, arg2);
            GlRaster.render(Static153.aClass3_Sub2_Sub1_Sub1_2.pixels, arg0, arg1, arg3, arg2);
            SoftwareRaster.destinationPixels = null;
        } else {
            Static214.method4364(arg3 + arg0, arg1, local61, local50, arg0, local236, arg1 + arg2, local211);
            Static48.method1195(arg0 + arg3, arg0, local61, local236, arg2 + arg1, arg1, local211, local50);
            Static38.method959(arg0, arg1, local211, arg0 + arg3, local236, local50, local61, arg2 + arg1);
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
                @Pc(62) int local62 = Static2.anInt13 + IdkTypeList.anInt4296 - local50 * 64 - 1;
                @Pc(69) int local69 = local46 * 64 - Static158.anInt3846;
                @Pc(147) byte local147;
                @Pc(91) int local91;
                if (local69 >= 0 && local62 - 63 >= 0 && local69 + 63 < Static48.anInt1449 && local62 < IdkTypeList.anInt4296) {
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
                                    if (Static34.aByteArrayArrayArray3[local91][local95] == null) {
                                        Static34.aByteArrayArrayArray3[local91][local95] = new byte[4096];
                                    }
                                    Static34.aByteArrayArrayArray3[local91][local95][local97 + (63 - local104 << 6)] = local147;
                                    @Pc(186) byte local186 = arg0.g1s();
                                    if (Static90.aByteArrayArrayArray8[local91][local95] == null) {
                                        Static90.aByteArrayArrayArray8[local91][local95] = new byte[4096];
                                    }
                                    Static90.aByteArrayArrayArray8[local91][local95][local97 + (63 - local104 << 6)] = local186;
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
}
