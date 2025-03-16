package com.jagex.runetek4.util;

import com.jagex.runetek4.Static220;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class ThreadUtils {
    @OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(JI)V")
    public static void sleep(@OriginalArg(0) long arg0) {
        if (arg0 <= 0L) {
            return;
        }
        if (arg0 % 10L == 0L) {
            Static220.sleep0(arg0 - 1L);
            Static220.sleep0(1L);
        } else {
            Static220.sleep0(arg0);
        }
    }
}
