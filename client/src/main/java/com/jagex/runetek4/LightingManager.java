package com.jagex.runetek4;

import com.jogamp.opengl.GL2;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LightingManager {
    @OriginalMember(owner = "runetek4.client!id", name = "b", descriptor = "I")
    public static int anInt2875 = -1;
    @OriginalMember(owner = "runetek4.client!jf", name = "l", descriptor = "I")
    public static int lightCount = 0;
    @OriginalMember(owner = "runetek4.client!jf", name = "a", descriptor = "[Lclient!gi;")
    public static Light[] lights;
    @OriginalMember(owner = "runetek4.client!jf", name = "f", descriptor = "[Z")
    static boolean[] aBooleanArray65;
    @OriginalMember(owner = "runetek4.client!jf", name = "h", descriptor = "[I")
    static int[] anIntArray284;
    @OriginalMember(owner = "runetek4.client!jf", name = "c", descriptor = "[I")
    static int[] anIntArray283;
    @OriginalMember(owner = "runetek4.client!jf", name = "g", descriptor = "[[[I")
    static int[][][] anIntArrayArrayArray11;
    @OriginalMember(owner = "runetek4.client!jf", name = "m", descriptor = "[Z")
    static boolean[] aBooleanArray66;
    @OriginalMember(owner = "runetek4.client!jf", name = "j", descriptor = "I")
    static int anInt3032;
    @OriginalMember(owner = "runetek4.client!jf", name = "o", descriptor = "I")
    static int anInt3036;
    @OriginalMember(owner = "runetek4.client!jf", name = "p", descriptor = "I")
    static int anInt3037;

    @OriginalMember(owner = "runetek4.client!jf", name = "a", descriptor = "(I)V")
    static void method2396(@OriginalArg(0) int arg0) {
        if (aBooleanArray65[arg0]) {
            aBooleanArray65[arg0] = false;
            @Pc(14) int local14 = arg0 + 16384 + 4;
            @Pc(16) GL2 local16 = GlRenderer.gl;
            local16.glDisable(local14);
        }
    }

    @OriginalMember(owner = "runetek4.client!jf", name = "e", descriptor = "()V")
    public static void method2400() {
        @Pc(1) GL2 local1 = GlRenderer.gl;
        @Pc(3) int local3;
        for (local3 = 0; local3 < 4; local3++) {
            @Pc(10) int local10 = local3 + 16388;
            local1.glLightfv(local10, GL2.GL_AMBIENT, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
            local1.glLightf(local10, GL2.GL_LINEAR_ATTENUATION, 0.0F);
            local1.glLightf(local10, GL2.GL_CONSTANT_ATTENUATION, 0.0F);
        }
        for (local3 = 0; local3 < 4; local3++) {
            anIntArray284[local3] = -1;
            method2396(local3);
        }
    }

    @OriginalMember(owner = "runetek4.client!jf", name = "c", descriptor = "()V")
    public static void method2398() {
        lights = null;
        anIntArray284 = null;
        aBooleanArray65 = null;
        anIntArray283 = null;
        aBooleanArray66 = null;
        anIntArrayArrayArray11 = null;
    }

    @OriginalMember(owner = "runetek4.client!jf", name = "f", descriptor = "()V")
    public static void method2401() {
        lights = new Light[255];
        anIntArray284 = new int[4];
        aBooleanArray65 = new boolean[4];
        anIntArray283 = new int[4];
        aBooleanArray66 = new boolean[4];
        anIntArrayArrayArray11 = new int[anInt3032][anInt3037][anInt3036];
    }
}
