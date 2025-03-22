package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ShadowManager {
    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "Lclient!ek;")
    public static SoftwareIndexedSprite shadowMapImage;
    @OriginalMember(owner = "runetek4.client!tj", name = "b", descriptor = "[[Lclient!wm;")
    public static OpenGLRenderer[][] aOpenGLRendererArrayArray1;
    @OriginalMember(owner = "runetek4.client!tj", name = "c", descriptor = "I")
    static int anInt5345;
    @OriginalMember(owner = "runetek4.client!tj", name = "e", descriptor = "I")
    static int anInt5346;

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "()V")
    public static void method4203() {
        shadowMapImage = null;
        Static242.aClass36_Sub1Array2 = null;
        aOpenGLRendererArrayArray1 = null;
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "(II)V")
    public static void method4201() {
        anInt5346 = 13;
        anInt5345 = 13;
        shadowMapImage = new SoftwareIndexedSprite(anInt5346 * 128 + 2, anInt5345 * 128 + 2, 0);
        aOpenGLRendererArrayArray1 = new OpenGLRenderer[anInt5346][anInt5345];
        for (@Pc(32) int local32 = 0; local32 < anInt5346; local32++) {
            for (@Pc(37) int local37 = 0; local37 < anInt5345; local37++) {
                aOpenGLRendererArrayArray1[local32][local37] = new OpenGLRenderer();
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "([B[BIIIIII)V")
    private static void method4195(@OriginalArg(0) byte[] arg0, @OriginalArg(1) byte[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
        @Pc(4) int local4 = -(arg4 >> 2);
        @Pc(9) int local9 = -(arg4 & 0x3);
        for (@Pc(12) int local12 = -arg5; local12 < 0; local12++) {
            @Pc(16) int local16;
            @Pc(20) int local20;
            for (local16 = local4; local16 < 0; local16++) {
                local20 = arg3++;
                arg0[local20] -= arg1[arg2++];
                @Pc(32) int local32 = arg3++;
                arg0[local32] -= arg1[arg2++];
                @Pc(44) int local44 = arg3++;
                arg0[local44] -= arg1[arg2++];
                @Pc(56) int local56 = arg3++;
                arg0[local56] -= arg1[arg2++];
            }
            for (local16 = local9; local16 < 0; local16++) {
                local20 = arg3++;
                arg0[local20] -= arg1[arg2++];
            }
            arg3 += arg6;
            arg2 += arg7;
        }
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "(IIII)V")
    static void method4196(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        @Pc(5) int local5 = arg0 - 1 >> 7;
        @Pc(15) int local15 = arg0 + arg2 - 1 - 1 >> 7;
        @Pc(21) int local21 = arg1 - 1 >> 7;
        @Pc(31) int local31 = arg1 + arg3 - 1 - 1 >> 7;
        for (@Pc(33) int local33 = local5; local33 <= local15; local33++) {
            for (@Pc(38) int local38 = local21; local38 <= local31; local38++) {
                aOpenGLRendererArrayArray1[local33][local38].outputToSprite = true;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "c", descriptor = "(Lclient!ek;Lclient!ek;II)V")
    private static void method4202(@OriginalArg(0) SoftwareIndexedSprite arg0, @OriginalArg(1) SoftwareIndexedSprite arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        arg2 += arg0.xOffset;
        arg3 += arg0.yOffset;
        @Pc(16) int local16 = arg2 + arg3 * arg1.width;
        @Pc(18) int local18 = 0;
        @Pc(21) int local21 = arg0.height;
        @Pc(24) int local24 = arg0.width;
        @Pc(29) int local29 = arg1.width - local24;
        @Pc(31) int local31 = 0;
        @Pc(37) int local37;
        if (arg3 <= 0) {
            local37 = 1 - arg3;
            local21 -= local37;
            local18 = local37 * local24;
            local16 += local37 * arg1.width;
            arg3 = 1;
        }
        if (arg3 + local21 >= arg1.height) {
            local37 = arg3 + local21 + 1 - arg1.height;
            local21 -= local37;
        }
        if (arg2 <= 0) {
            local37 = 1 - arg2;
            local24 -= local37;
            local18 += local37;
            local16 += local37;
            local31 = local37;
            local29 += local37;
            arg2 = 1;
        }
        if (arg2 + local24 >= arg1.width) {
            local37 = arg2 + local24 + 1 - arg1.width;
            local24 -= local37;
            local31 += local37;
            local29 += local37;
        }
        if (local24 > 0 && local21 > 0) {
            method4195(arg1.aByteArray18, arg0.aByteArray18, local18, local16, local24, local21, local29, local31);
            method4196(arg2, arg3, local24, local21);
        }
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "(Lclient!ek;III)V")
    public static void method4207(@OriginalArg(0) SoftwareIndexedSprite arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        if (arg0 != null) {
            @Pc(12) int local12 = arg1 - (arg2 * FogManager.lightX >> 8) >> 3;
            @Pc(22) int local22 = arg3 - (arg2 * FogManager.lightZ >> 8) >> 3;
            method4202(arg0, shadowMapImage, local12 + 1, local22 + 1);
        }
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "([BIIIII)Z")
    private static boolean method4199(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
        @Pc(3) int local3 = arg2 % 8;
        @Pc(9) int local9;
        if (local3 == 0) {
            local9 = 0;
        } else {
            local9 = 8 - local3;
        }
        @Pc(21) int local21 = -((arg3 + 8 - 1) / 8);
        @Pc(30) int local30 = -((arg2 + 8 - 1) / 8);
        for (@Pc(32) int local32 = local21; local32 < 0; local32++) {
            for (@Pc(36) int local36 = local30; local36 < 0; local36++) {
                if (arg0[arg1] == 0) {
                    return true;
                }
                arg1 += 8;
            }
            arg1 -= local9;
            if (arg0[arg1 - 1] == 0) {
                return true;
            }
            arg1 += arg4;
        }
        return false;
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "b", descriptor = "(Lclient!ek;Lclient!ek;II)Z")
    private static boolean method4200(@OriginalArg(0) SoftwareIndexedSprite arg0, @OriginalArg(1) SoftwareIndexedSprite arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        arg2 += arg0.xOffset;
        arg3 += arg0.yOffset;
        @Pc(16) int local16 = arg2 + arg3 * arg1.width;
        @Pc(19) int local19 = arg0.height;
        @Pc(22) int local22 = arg0.width;
        @Pc(27) int local27 = arg1.width - local22;
        @Pc(33) int local33;
        if (arg3 <= 0) {
            local33 = 1 - arg3;
            local19 -= local33;
            local16 += local33 * arg1.width;
            arg3 = 1;
        }
        if (arg3 + local19 >= arg1.height) {
            local33 = arg3 + local19 + 1 - arg1.height;
            local19 -= local33;
        }
        if (arg2 <= 0) {
            local33 = 1 - arg2;
            local22 -= local33;
            local16 += local33;
            local27 += local33;
            arg2 = 1;
        }
        if (arg2 + local22 >= arg1.width) {
            local33 = arg2 + local22 + 1 - arg1.width;
            local22 -= local33;
            local27 += local33;
        }
        if (local22 > 0 && local19 > 0) {
            local27 += arg1.width * 7;
            method4196(arg2, arg3, local22, local19);
            return method4199(arg1.aByteArray18, local16, local22, local19, local27);
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "b", descriptor = "(Lclient!ek;III)Z")
    public static boolean method4209(@OriginalArg(0) SoftwareIndexedSprite arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        if (arg0 == null) {
            return false;
        } else {
            @Pc(13) int local13 = arg1 - (arg2 * FogManager.lightX >> 8) >> 3;
            @Pc(23) int local23 = arg3 - (arg2 * FogManager.lightZ >> 8) >> 3;
            return method4200(arg0, shadowMapImage, local13 + 1, local23 + 1);
        }
    }
}
