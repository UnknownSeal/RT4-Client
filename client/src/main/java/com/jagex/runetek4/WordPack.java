package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WordPack {

    @OriginalMember(owner = "runetek4.client!vl", name = "f", descriptor = "Lclient!na;")
    public static final JString CABBAGE = JString.parse("Cabbage");

    @OriginalMember(owner = "client!f", name = "W", descriptor = "Lclient!fi;")
    public static HuffmanCodec huffmanCodec;

    @OriginalMember(owner = "runetek4.client!a", name = "a", descriptor = "(Lclient!fi;I)V")
    public static void init(@OriginalArg(0) HuffmanCodec arg0) {
        huffmanCodec = arg0;
    }

    @OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(ZLclient!wa;Lclient!na;)I")
    public static int writeString(@OriginalArg(1) Packet arg0, @OriginalArg(2) JString arg1) {
        @Pc(6) int local6 = arg0.offset;
        @Pc(14) byte[] local14 = arg1.method3148();
        arg0.pSmart1or2(local14.length);
        arg0.offset += huffmanCodec.encode(local14.length, arg0.data, local14, 0, arg0.offset);
        return arg0.offset - local6;
    }

    @OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(Lclient!wa;II)Lclient!na;")
    public static JString readStringInternal(@OriginalArg(0) Packet packet) {
        try {
            @Pc(7) int len = packet.gSmart1or2();
            if (len > 32767) {
                len = 32767;
            }
            @Pc(15) byte[] bytes = new byte[len];
            packet.offset += huffmanCodec.decode(0, len, bytes, packet.data, packet.offset);
            return JString.decodeString(bytes, len, 0);
        } catch (@Pc(47) Exception local47) {
            return CABBAGE;
        }
    }
}
