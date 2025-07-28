package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.LruHashTable;
import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalMember;

// Has no usages.

public class DeadClass {
    @OriginalMember(owner = "runetek4.client!ul", name = "I", descriptor = "Lclient!gn;")
    public static final LruHashTable cache = new LruHashTable(4);
}
