package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class LightingManager {
    @OriginalMember(owner = "runetek4.client!id", name = "b", descriptor = "I")
    public static int anInt2875 = -1;
    @OriginalMember(owner = "runetek4.client!jf", name = "l", descriptor = "I")
    public static int lightCount = 0;
    @OriginalMember(owner = "runetek4.client!jf", name = "a", descriptor = "[Lclient!gi;")
    public static Light[] lights;
}
