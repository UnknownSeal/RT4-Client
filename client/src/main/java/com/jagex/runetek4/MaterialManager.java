package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.nio.ByteBuffer;

public class MaterialManager {
    @OriginalMember(owner = "runetek4.client!lm", name = "c", descriptor = "Ljava/nio/ByteBuffer;")
    static ByteBuffer aByteBuffer6;
    @OriginalMember(owner = "runetek4.client!lm", name = "d", descriptor = "Ljava/nio/ByteBuffer;")
    static ByteBuffer aByteBuffer7;

    @OriginalMember(owner = "runetek4.client!lm", name = "a", descriptor = "()V")
    public static void method2807() {
        @Pc(11) byte[] local11;
        if (aByteBuffer7 == null) {
            @Pc(5) NoiseGenerator3D_Sub1_Sub1 local5 = new NoiseGenerator3D_Sub1_Sub1();
            local11 = local5.method3215();
            aByteBuffer7 = ByteBuffer.allocateDirect(local11.length);
            aByteBuffer7.position(0);
            aByteBuffer7.put(local11);
            aByteBuffer7.flip();
        }
        if (aByteBuffer6 != null) {
            return;
        }
        @Pc(32) NoiseGenerator3D_Sub2_Sub1 local32 = new NoiseGenerator3D_Sub2_Sub1();
        local11 = local32.method3854();
        aByteBuffer6 = ByteBuffer.allocateDirect(local11.length);
        aByteBuffer6.position(0);
        aByteBuffer6.put(local11);
        aByteBuffer6.flip();
    }
}
