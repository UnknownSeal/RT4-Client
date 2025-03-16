package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ShadowManager {
    @OriginalMember(owner = "runetek4.client!tj", name = "c", descriptor = "I")
    static int anInt5345;
    @OriginalMember(owner = "runetek4.client!tj", name = "e", descriptor = "I")
    static int anInt5346;

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "()V")
    public static void method4203() {
        Static242.aClass36_Sub1_4 = null;
        Static242.aClass36_Sub1Array2 = null;
        Static242.aOpenGLRendererArrayArray1 = null;
    }

    @OriginalMember(owner = "runetek4.client!tj", name = "a", descriptor = "(II)V")
    public static void method4201() {
        anInt5346 = 13;
        anInt5345 = 13;
        Static242.aClass36_Sub1_4 = new SoftwareIndexedSprite(anInt5346 * 128 + 2, anInt5345 * 128 + 2, 0);
        Static242.aOpenGLRendererArrayArray1 = new OpenGLRenderer[anInt5346][anInt5345];
        for (@Pc(32) int local32 = 0; local32 < anInt5346; local32++) {
            for (@Pc(37) int local37 = 0; local37 < anInt5345; local37++) {
                Static242.aOpenGLRendererArrayArray1[local32][local37] = new OpenGLRenderer();
            }
        }
    }
}
