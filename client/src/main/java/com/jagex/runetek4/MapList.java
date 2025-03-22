package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MapList {
    @OriginalMember(owner = "client!ce", name = "a", descriptor = "(IBI)Lclient!bn;")
    public static Map getContainingSource(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        for (@Pc(10) Map local10 = (Map) Static228.aClass69_120.head(); local10 != null; local10 = (Map) Static228.aClass69_120.next()) {
            if (local10.aBoolean50 && local10.method664(arg1, arg0)) {
                return local10;
            }
        }
        return null;
    }
}
