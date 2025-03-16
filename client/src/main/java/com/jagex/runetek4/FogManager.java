package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class FogManager {
    @OriginalMember(owner = "runetek4.client!mk", name = "e", descriptor = "I")
    public static int defaulFogColorRgb = 13156520;

    @OriginalMember(owner = "runetek4.client!gm", name = "f", descriptor = "(B)V")
    public static void setInstantFade() {
        Static222.aBoolean246 = true;
    }
}
