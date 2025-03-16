package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class WordPack {
    @OriginalMember(owner = "runetek4.client!a", name = "a", descriptor = "(Lclient!fi;I)V")
    public static void init(@OriginalArg(0) HuffmanCodec arg0) {
        Static62.aClass44_1 = arg0;
    }
}
