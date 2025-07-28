package com.jagex.runetek4.util.data;

import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Base37 {
    @OriginalMember(owner = "client!ch", name = "v", descriptor = "[B")
    public static final byte[] aByteArray12 = new byte[]{95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57};

    @OriginalMember(owner = "runetek4.client!ge", name = "a", descriptor = "(IJ)Lclient!na;")
    public static JString decode37(@OriginalArg(1) long value) {
        if (value <= 0L || value >= 6582952005840035281L) {
            return null;
        } else if (value % 37L == 0L) {
            return null;
        } else {
            @Pc(32) int length = 0;
            @Pc(34) long v = value;
            while (v != 0L) {
                v /= 37L;
                length++;
            }
            @Pc(48) byte[] local48 = new byte[length];
            while (value != 0L) {
                @Pc(65) long t = value;
                value /= 37L;
                length--;
                local48[length] = aByteArray12[(int) (t - value * 37L)];
            }
            @Pc(88) JString local88 = new JString();
            local88.chars = local48;
            local88.length = local48.length;
            return local88;
        }
    }
}
