package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class FogManager {
    @OriginalMember(owner = "client!mk", name = "e", descriptor = "I")
    public static int defaulFogColorRgb = 13156520;
    @OriginalMember(owner = "client!mk", name = "i", descriptor = "F")
    static float aFloat21;
    @OriginalMember(owner = "client!mk", name = "f", descriptor = "F")
    static float aFloat19 = -1.0F;
    @OriginalMember(owner = "client!mk", name = "b", descriptor = "I")
    static int anInt3919 = -1;

    @OriginalMember(owner = "client!gm", name = "f", descriptor = "(B)V")
    public static void setInstantFade() {
        Static222.aBoolean246 = true;
    }

    @OriginalMember(owner = "client!mk", name = "g", descriptor = "()F")
    public static float getLightingModelAmbient() {
        return aFloat21;
    }

    @OriginalMember(owner = "client!mk", name = "b", descriptor = "()F")
    public static float getLight0Diffuse() {
        return aFloat19;
    }

    @OriginalMember(owner = "client!mk", name = "d", descriptor = "()I")
    public static int getLightColor() {
        return anInt3919;
    }
}
