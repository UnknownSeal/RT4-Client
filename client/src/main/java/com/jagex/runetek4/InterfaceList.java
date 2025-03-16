package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class InterfaceList {
    @OriginalMember(owner = "runetek4.client!sg", name = "q", descriptor = "[I")
    public static final int[] keyCodes = new int[128];
    @OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
    public static int transmitTimer = 1;
}
