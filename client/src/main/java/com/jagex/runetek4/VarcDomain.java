package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class VarcDomain {
    @OriginalMember(owner = "runetek4.client!me", name = "P", descriptor = "[I")
    public static final int[] varcs = new int[2000];
    @OriginalMember(owner = "runetek4.client!km", name = "Bc", descriptor = "[I")
    public static final int[] updatedVarcs = new int[32];
    @OriginalMember(owner = "runetek4.client!ac", name = "o", descriptor = "I")
    public static int updatedVarcsWriterIndex = 0;
}
