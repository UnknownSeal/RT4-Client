package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class CreateManager {
    @OriginalMember(owner = "runetek4.client!oe", name = "l", descriptor = "I")
    public static int step = 0;
    @OriginalMember(owner = "runetek4.client!sc", name = "y", descriptor = "I")
    public static int reply = -2;
    @OriginalMember(owner = "runetek4.client!si", name = "S", descriptor = "[Lclient!na;")
    public static JString[] suggestedNames;

    @OriginalMember(owner = "runetek4.client!da", name = "a", descriptor = "(IIIILclient!na;JI)V")
    public static void createAccount(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) JString password, @OriginalArg(5) long name, @OriginalArg(6) int arg5) {
        @Pc(8) Packet local8 = new Packet(128);
        local8.p1(10);
        local8.p2((int) (Math.random() * 99999.0D));
        local8.p2(530);
        local8.p8(name);
        local8.p4((int) (Math.random() * 9.9999999E7D));
        local8.pjstr(password);
        local8.p4((int) (Math.random() * 9.9999999E7D));
        local8.p2(client.affiliate);
        local8.p1(arg0);
        local8.p1(arg2);
        local8.p4((int) (Math.random() * 9.9999999E7D));
        local8.p2(arg5);
        local8.p2(arg1);
        local8.p4((int) (Math.random() * 9.9999999E7D));
        local8.rsaenc(Static86.RSA_EXPONENT, Static86.RSA_MODULUS);
        Protocol.outboundBuffer.offset = 0;
        Protocol.outboundBuffer.p1(36);
        Protocol.outboundBuffer.p1(local8.offset);
        Protocol.outboundBuffer.pdata(local8.data, local8.offset);
        reply = -3;
        step = 1;
        Static226.loops = 0;
        Static57.errors = 0;
    }

    @OriginalMember(owner = "runetek4.client!gd", name = "a", descriptor = "(JI)V")
    public static void checkName(@OriginalArg(0) long name) {
        Protocol.outboundBuffer.offset = 0;
        Protocol.outboundBuffer.p1(186);
        Protocol.outboundBuffer.p8(name);
        step = 1;
        Static226.loops = 0;
        Static57.errors = 0;
        reply = -3;
    }

    @OriginalMember(owner = "runetek4.client!jl", name = "a", descriptor = "(IIIII)V")
    public static void checkInfo(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        Protocol.outboundBuffer.offset = 0;
        Protocol.outboundBuffer.p1(147);
        Protocol.outboundBuffer.p1(arg2);
        Protocol.outboundBuffer.p1(arg3);
        Protocol.outboundBuffer.p2(arg0);
        Protocol.outboundBuffer.p2(arg1);
        Static226.loops = 0;
        Static57.errors = 0;
        step = 1;
        reply = -3;
    }
}
