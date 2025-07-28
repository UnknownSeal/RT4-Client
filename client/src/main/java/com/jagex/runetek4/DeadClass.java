package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.LruHashTable;
import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalMember;

// Has no usages.

public class DeadClass {
    @OriginalMember(owner = "runetek4.client!ul", name = "I", descriptor = "Lclient!gn;")
    public static final LruHashTable cache = new LruHashTable(4);
    @OriginalMember(owner = "runetek4.client!ck", name = "b", descriptor = "Lclient!na;")
    private static final JString aClass100_195 = JString.parse("Discard");
    @OriginalMember(owner = "client!ck", name = "o", descriptor = "Lclient!na;")
    private static final JString LOADED_WORLD_LIST_DATA = JString.parse("Loaded world list data");
    @OriginalMember(owner = "client!dc", name = "E", descriptor = "Lclient!na;")
    private static final JString ENG_CHATEFFECT1 = JString.parse("wave:");
    @OriginalMember(owner = "client!dc", name = "R", descriptor = "Lclient!na;")
    private static final JString aClass100_269 = JString.parse("Drop");
    @OriginalMember(owner = "runetek4.client!rc", name = "R", descriptor = "Z")
    private static boolean aBoolean74;
}
