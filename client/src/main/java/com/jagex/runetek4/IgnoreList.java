package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class IgnoreList {
    @OriginalMember(owner = "runetek4.client!pf", name = "h", descriptor = "[J")
    public static final long[] encodedIgnores = new long[100];
    @OriginalMember(owner = "runetek4.client!cl", name = "Z", descriptor = "I")
    public static int ignoreCount = 0;
}
