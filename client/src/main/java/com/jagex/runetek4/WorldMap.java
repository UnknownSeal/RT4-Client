package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WorldMap {
    @OriginalMember(owner = "client!fi", name = "j", descriptor = "Lclient!qf;")
    public static Sprite aClass3_Sub2_Sub1_2;
    @OriginalMember(owner = "client!dc", name = "O", descriptor = "I")
    public static int loadPercentage = 0;
    @OriginalMember(owner = "runetek4.client!hc", name = "P", descriptor = "I")
    public static int anInt2428;
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

    @OriginalMember(owner = "client!a", name = "a", descriptor = "(IIIII)V")
    public static void method4(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
        if (GlRenderer.enabled) {
            Static46.method1187(arg0, arg3, arg2 + arg0, arg1 + arg3);
            Static46.method1186(arg0, arg3, arg2, arg1, 0);
        } else {
            Rasterizer.setBounds(arg0, arg3, arg2 + arg0, arg3 + arg1);
            Rasterizer.drawFilledRectangle(arg0, arg3, arg2, arg1, 0);
        }

        if (loadPercentage < 100) {
            return;
        }

        if (aClass3_Sub2_Sub1_2 == null || arg2 != aClass3_Sub2_Sub1_2.width || aClass3_Sub2_Sub1_2.height != arg1) {
            @Pc(63) ImageRGB local63 = new ImageRGB(arg2, arg1);
            Rasterizer.prepare(local63.pixels, arg2, arg1);
            Static214.method4364(arg2, 0, Static48.anInt1449, 0, 0, IdkTypeList.anInt4296, arg1, 0);
            if (GlRenderer.enabled) {
                aClass3_Sub2_Sub1_2 = new GlSprite(local63);
            } else {
                aClass3_Sub2_Sub1_2 = local63;
            }
            if (GlRenderer.enabled) {
                Rasterizer.destinationPixels = null;
            } else {
                SoftwareRaster.frameBuffer.makeTarget();
            }
        }
        aClass3_Sub2_Sub1_2.render(arg0, arg3);
        @Pc(147) int local147 = arg1 * Static109.anInt2884 / IdkTypeList.anInt4296 + arg3;
        @Pc(153) int local153 = Static37.anInt1176 * arg1 / IdkTypeList.anInt4296;
        @Pc(161) int local161 = arg0 + arg2 * Static109.anInt2882 / Static48.anInt1449;
        @Pc(167) int local167 = arg2 * Static89.anInt2387 / Static48.anInt1449;
        @Pc(169) int local169 = 16711680;
        if (client.game == 1) {
            local169 = 16777215;
        }
        if (GlRenderer.enabled) {
            Static46.drawFilledRectangleAlpha(local161, local147, local167, local153, local169, 128);
            Static46.method1179(local161, local147, local167, local153, local169);
        } else {
            Rasterizer.drawFilledRectangleAlpha(local161, local147, local167, local153, local169, 128);
            Rasterizer.drawUnfilledRectangle(local161, local147, local167, local153, local169);
        }
        if (anInt1864 <= 0) {
            return;
        }
        @Pc(225) int local225;
        if (anInt2428 > 10) {
            local225 = (20 - anInt2428) * 25;
        } else {
            local225 = anInt2428 * 25;
        }
        for (@Pc(238) Class3_Sub26 local238 = (Class3_Sub26) Static145.aClass69_84.head(); local238 != null; local238 = (Class3_Sub26) Static145.aClass69_84.next()) {
            if (local238.anInt4308 == CacheArchive.anInt172) {
                @Pc(258) int local258 = arg3 + local238.anInt4314 * arg1 / IdkTypeList.anInt4296;
                @Pc(267) int local267 = arg2 * local238.anInt4307 / Static48.anInt1449 + arg0;
                if (GlRenderer.enabled) {
                    Static46.drawFilledRectangleAlpha(local267 - 2, local258 - 2, 4, 4, 16776960, local225);
                } else {
                    Rasterizer.drawFilledRectangleAlpha(local267 - 2, local258 - 2, 4, 4, 16776960, local225);
                }
            }
        }
    }
}
