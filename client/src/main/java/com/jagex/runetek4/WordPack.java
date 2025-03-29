package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WordPack {
    @OriginalMember(owner = "runetek4.client!vl", name = "f", descriptor = "Lclient!na;")
    public static final JString CABBAGE = JString.parse("Cabbage");
    @OriginalMember(owner = "client!f", name = "W", descriptor = "Lclient!fi;")
    public static HuffmanCodec codec;

    @OriginalMember(owner = "runetek4.client!a", name = "a", descriptor = "(Lclient!fi;I)V")
    public static void init(@OriginalArg(0) HuffmanCodec arg0) {
        codec = arg0;
    }

    @OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(ZLclient!wa;Lclient!na;)I")
    public static int encode(@OriginalArg(1) Packet arg0, @OriginalArg(2) JString arg1) {
        @Pc(6) int local6 = arg0.offset;
        @Pc(14) byte[] local14 = arg1.method3148();
        arg0.pSmart1or2(local14.length);
        arg0.offset += codec.encode(local14.length, arg0.data, local14, 0, arg0.offset);
        return arg0.offset - local6;
    }
}
