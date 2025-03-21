package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
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

    @OriginalMember(owner = "client!cb", name = "b", descriptor = "(III)V")
    public static void setMaterial(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        if (arg1 == 4 && !Static220.aBoolean244) {
            arg1 = 2;
            arg0 = 2;
        }
        if (Static48.anInt1447 != arg1) {
            if (Static119.aBoolean153) {
                return;
            }
            if (Static48.anInt1447 != 0) {
                Static2.anInterface4Array1[Static48.anInt1447].unbind();
            }
            if (arg1 != 0) {
                @Pc(61) MaterialRenderer local61 = Static2.anInterface4Array1[arg1];
                local61.bind();
                local61.setArgument(arg0);
            }
            Static48.anInt1447 = arg1;
            Static158.anInt3857 = arg0;
        } else if (arg1 != 0 && arg0 != Static158.anInt3857) {
            Static2.anInterface4Array1[arg1].setArgument(arg0);
            Static158.anInt3857 = arg0;
        }
    }

    @OriginalMember(owner = "runetek4.client!nj", name = "a", descriptor = "(I)V")
    public static void quit() {
        Static2.anInterface4Array1 = null;
        Static151.method2808();
    }

    @OriginalMember(owner = "runetek4.client!te", name = "e", descriptor = "(I)V")
    public static void init() {
        Static151.method2809();
        Static2.anInterface4Array1 = new MaterialRenderer[7];
        Static2.anInterface4Array1[1] = new SpecularMaterialRenderer();
        Static2.anInterface4Array1[2] = new LiquidMaterialRenderer();
        Static2.anInterface4Array1[3] = new UnderwaterMaterialRenderer();
        Static2.anInterface4Array1[4] = new WaterMaterialRenderer();
        Static2.anInterface4Array1[5] = new WaterfallMaterialRenderer();
        Static2.anInterface4Array1[6] = new UnlitMaterialRenderer();
    }
}
