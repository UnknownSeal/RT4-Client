package com.jagex.game.compression.huffman;

import com.jagex.core.io.Packet;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class WordPack {

    @OriginalMember(owner = "runetek4.client!vl", name = "f", descriptor = "Lclient!na;")
    public static final JString CABBAGE = JString.parse("Cabbage");

    @OriginalMember(owner = "client!f", name = "W", descriptor = "Lclient!fi;")
    public static Huffman huffman;

    @OriginalMember(owner = "runetek4.client!a", name = "a", descriptor = "(Lclient!fi;I)V")
    public static void init(@OriginalArg(0) Huffman arg0) {
        huffman = arg0;
    }

    @OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(ZLclient!wa;Lclient!na;)I")
    public static int encode(@OriginalArg(1) Packet packet, @OriginalArg(2) JString word) {
        @Pc(6) int posBefore = packet.offset;
        @Pc(14) byte[] encoded = word.encode();
        packet.pSmart1or2(encoded.length);
        packet.offset += huffman.encode(encoded.length, packet.data, encoded, 0, packet.offset);
        return packet.offset - posBefore;
    }

    @OriginalMember(owner = "runetek4.client!uj", name = "a", descriptor = "(Lclient!wa;II)Lclient!na;")
    public static JString decode(@OriginalArg(0) Packet packet) {
        try {
            @Pc(7) int length = packet.gSmart1or2();
            int maxLength = 32767;
            if (length > maxLength) {
                length = maxLength;
            }
            @Pc(15) byte[] bytes = new byte[length];
            packet.offset += huffman.decode(0, length, bytes, packet.data, packet.offset);
            return JString.decodeString(bytes, length, 0);
        } catch (@Pc(47) Exception exception) {
            return CABBAGE;
        }
    }
}
