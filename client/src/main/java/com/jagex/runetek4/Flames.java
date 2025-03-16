package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class Flames {
    @OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(Lclient!ve;I)Z")
    public static boolean isReady(@OriginalArg(0) Js5 arg0) {
        return arg0.method4506(Static138.anInt3443);
    }
}
