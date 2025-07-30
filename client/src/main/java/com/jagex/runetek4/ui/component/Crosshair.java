package com.jagex.runetek4.ui.component;

import org.openrs2.deob.annotation.OriginalMember;

public class Crosshair {
    @OriginalMember(owner = "client!fi", name = "k", descriptor = "I")
    public static int CrosshairMode = 0;
    @OriginalMember(owner = "client!bg", name = "M", descriptor = "I")
    public static int CrosshairCycle = 0;
    @OriginalMember(owner = "client!c", name = "eb", descriptor = "I")
    public static int y = 0;
    @OriginalMember(owner = "client!jh", name = "j", descriptor = "I")
    public static int x = 0;
}
