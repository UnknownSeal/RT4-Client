package com.jagex.js5;

import com.jagex.core.compress.Bzip2Decompressor;
import com.jagex.core.compress.GZipDecompressor;
import com.jagex.core.constants.CompressionType;
import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Js5Compression {
    @OriginalMember(owner = "client!kf", name = "j", descriptor = "I")
    public static final int MAX_LENGTH = 0;

    @OriginalMember(owner = "runetek4.client!mf", name = "x", descriptor = "Lclient!ha;")
    public static final GZipDecompressor GZIP_DECOMPRESSOR = new GZipDecompressor();

    @OriginalMember(owner = "client!ml", name = "a", descriptor = "(B[B)[B")
    public static byte[] uncompress(@OriginalArg(1) byte[] src) {
        @Pc(17) Packet packet = new Packet(src);
        @Pc(21) int type = packet.g1();
        @Pc(25) int len = packet.g4();
        if (len < 0 || MAX_LENGTH != 0 && len > MAX_LENGTH) {
            throw new RuntimeException("ctype=" + type + " clen=" + len + " maxsize=" + MAX_LENGTH);
        } else if (type == CompressionType.NONE) {
            @Pc(53) byte[] bytes = new byte[len];
            packet.gdata(len, bytes);
            return bytes;
        } else {
            @Pc(65) int unpackedLength = packet.g4();
            if (unpackedLength < 0 || MAX_LENGTH != 0 && unpackedLength > MAX_LENGTH) {
                // return new byte[4]; instead should prevent crash from missing XTEA
                throw new RuntimeException("ctype=" + type + " clen=" + len + " ulen=" + unpackedLength + " maxsize=" + MAX_LENGTH);
            }
            @Pc(85) byte[] bytes = new byte[unpackedLength];
            if (type == CompressionType.BZIP2) {
                Bzip2Decompressor.bunzip2(bytes, unpackedLength, src, len);
            } else {
                GZIP_DECOMPRESSOR.gunzip(bytes, packet);
            }
            return bytes;
        }
    }
}
