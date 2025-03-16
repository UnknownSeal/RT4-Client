package com.jagex.runetek4.util;

import com.jagex.runetek4.JString;
import com.jagex.runetek4.Static153;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class LangUtils {
    @OriginalMember(owner = "runetek4.client!hm", name = "a", descriptor = "(Lclient!na;B)I")
    public static int method2053(@OriginalArg(0) JString arg0) {
        for (@Pc(12) int local12 = 0; local12 < Static153.aClass100Array113.length; local12++) {
            if (Static153.aClass100Array113[local12].equalsIgnoreCase(arg0)) {
                return local12;
            }
        }
        return -1;
    }
}
