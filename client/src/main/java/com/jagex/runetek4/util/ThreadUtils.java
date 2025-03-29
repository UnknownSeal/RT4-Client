package com.jagex.runetek4.util;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class ThreadUtils {
    @OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(JI)V")
    public static void sleep(@OriginalArg(0) long arg0) {
        if (arg0 <= 0L) {
            return;
        }
        if (arg0 % 10L == 0L) {
            sleepUninterruptibly(arg0 - 1L);
            sleepUninterruptibly(1L);
        } else {
            sleepUninterruptibly(arg0);
        }
    }

    @OriginalMember(owner = "runetek4.client!rm", name = "a", descriptor = "(JB)V")
    public static void sleepUninterruptibly(@OriginalArg(0) long arg0) {
        try {
            Thread.sleep(arg0);
        } catch (@Pc(11) InterruptedException local11) {
        }
    }
}
