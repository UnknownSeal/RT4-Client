package com.jagex.runetek4.js5;

import com.jagex.runetek4.cache.bzip.Bzip2Decompressor;
import com.jagex.runetek4.core.io.GZip;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Js5Compression {
    @OriginalMember(owner = "client!kf", name = "j", descriptor = "I")
    public static final int MAX_LENGTH = 0;

    @OriginalMember(owner = "runetek4.client!mf", name = "x", descriptor = "Lclient!ha;")
    public static final GZip GZIP_DECOMPRESSOR = new GZip();

    @OriginalMember(owner = "client!ml", name = "a", descriptor = "(B[B)[B")
    public static byte[] uncompress(@OriginalArg(1) byte[] src) {
        @Pc(17) Packet packet = new Packet(src);
        @Pc(21) int type = packet.g1();
        @Pc(25) int len = packet.g4();
        if (len < 0 || MAX_LENGTH != 0 && len > MAX_LENGTH) {
            throw new RuntimeException("ctype=" + type + " clen=" + len + " maxsize=" + MAX_LENGTH);
        } else if (type == 0) {
            @Pc(53) byte[] bytes = new byte[len];
            packet.gdata(len, bytes);
            return bytes;
        } else {
            @Pc(65) int unpackedLength = packet.g4();
            if (unpackedLength < 0 || MAX_LENGTH != 0 && unpackedLength > MAX_LENGTH) {
                throw new RuntimeException("ctype=" + type + " clen=" + len + " ulen=" + unpackedLength + " maxsize=" + MAX_LENGTH);
            }
            @Pc(85) byte[] bytes = new byte[unpackedLength];
            if (type == 1) {
                Bzip2Decompressor.bunzip2(bytes, unpackedLength, src, len);
            } else {
                GZIP_DECOMPRESSOR.method1842(bytes, packet);
            }
            return bytes;
        }
    }
}
