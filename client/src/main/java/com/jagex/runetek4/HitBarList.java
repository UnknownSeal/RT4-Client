package com.jagex.runetek4;

import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalMember;

public class HitBarList {
    @OriginalMember(owner = "client!fm", name = "S", descriptor = "Lclient!n;")
    public static final SoftLruHashTable hitBars = new SoftLruHashTable(4);
}
