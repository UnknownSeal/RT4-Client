package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimType;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SpotAnimTypeList {
    @OriginalMember(owner = "client!ck", name = "a", descriptor = "(BI)Lclient!eg;")
    public static SpotAnimType get(@OriginalArg(1) int arg0) {
        @Pc(10) SpotAnimType local10 = (SpotAnimType) Static279.aClass99_38.get((long) arg0);
        if (local10 != null) {
            return local10;
        }
        @Pc(26) byte[] local26 = Static132.aClass153_48.getfile(Static206.method3681(arg0), Static133.method4010(arg0));
        local10 = new SpotAnimType();
        local10.anInt1751 = arg0;
        if (local26 != null) {
            local10.readValues(new Packet(local26));
        }
        Static279.aClass99_38.put(local10, (long) arg0);
        return local10;
    }
}
