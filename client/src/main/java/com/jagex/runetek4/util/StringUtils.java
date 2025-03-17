package com.jagex.runetek4.util;

import com.jagex.runetek4.JString;
import com.jagex.runetek4.Static220;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public class StringUtils {
    @OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(IB)Lclient!na;")
    public static JString toString(@OriginalArg(0) int arg0) {
        return arg0 >= 999999999 ? Static220.aClass100_930 : JString.parseInt(arg0);
    }
}
