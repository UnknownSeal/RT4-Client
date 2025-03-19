package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.invtype.InvType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class InvTypeList {
    @OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(II)Lclient!md;")
    public static InvType get(@OriginalArg(0) int arg0) {
        @Pc(16) InvType invType = (InvType) Static89.aClass54_8.get((long) arg0);
        if (invType != null) {
            return invType;
        }
        @Pc(27) byte[] bytes = CacheArchive.aClass153_2.getfile(5, arg0);
        invType = new InvType();
        if (bytes != null) {
            invType.decode(new Packet(bytes));
        }
        Static89.aClass54_8.put(invType, (long) arg0);
        return invType;
    }
}
