package com.jagex.runetek4;

import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class WorldList {
    @OriginalMember(owner = "runetek4.client!en", name = "A", descriptor = "Z")
    public static boolean loaded = false;

    @OriginalMember(owner = "runetek4.client!ql", name = "b", descriptor = "(I)I")
    public static int fetch() {
        try {
            if (Static82.anInt2231 == 0) {
                if (MonotonicTime.currentTimeMillis() - 5000L < PreciseSleep.aLong174) {
                    return 0;
                }
                Static72.aClass212_3 = GameShell.signLink.openSocket(client.worldListHostname, client.worldListPort);
                Static15.aLong18 = MonotonicTime.currentTimeMillis();
                Static82.anInt2231 = 1;
            }
            if (Static15.aLong18 + 30000L < MonotonicTime.currentTimeMillis()) {
                return Static10.method347(1000);
            }
            @Pc(82) int local82;
            @Pc(124) int local124;
            if (Static82.anInt2231 == 1) {
                if (Static72.aClass212_3.status == 2) {
                    return Static10.method347(1001);
                }
                if (Static72.aClass212_3.status != 1) {
                    return -1;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
                Protocol.outboundBuffer.offset = 0;
                Static72.aClass212_3 = null;
                local82 = 0;
                if (loaded) {
                    local82 = Static80.anInt4702;
                }
                Protocol.outboundBuffer.p1(255);
                Protocol.outboundBuffer.p4(local82);
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                local124 = Protocol.gameServerSocket.read();
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                if (local124 != 0) {
                    return Static10.method347(local124);
                }
                Static82.anInt2231 = 2;
            }
            if (Static82.anInt2231 == 2) {
                if (Protocol.gameServerSocket.available() < 2) {
                    return -1;
                }
                Static116.anInt2961 = Protocol.gameServerSocket.read();
                Static116.anInt2961 <<= 0x8;
                Static116.anInt2961 += Protocol.gameServerSocket.read();
                Static82.anInt2231 = 3;
                Static141.anInt3469 = 0;
                Static229.aByteArray70 = new byte[Static116.anInt2961];
            }
            if (Static82.anInt2231 != 3) {
                return -1;
            }
            local82 = Protocol.gameServerSocket.available();
            if (local82 < 1) {
                return -1;
            }
            if (local82 > Static116.anInt2961 - Static141.anInt3469) {
                local82 = Static116.anInt2961 - Static141.anInt3469;
            }
            Protocol.gameServerSocket.read(Static141.anInt3469, local82, Static229.aByteArray70);
            Static141.anInt3469 += local82;
            if (Static141.anInt3469 < Static116.anInt2961) {
                return -1;
            } else if (ObjTypeList.method2572(Static229.aByteArray70)) {
                Static101.aClass10_Sub1Array1 = new GWCWorld[Static106.anInt2871];
                local124 = 0;
                for (@Pc(240) int local240 = Static19.anInt636; local240 <= Static171.anInt4157; local240++) {
                    @Pc(247) GWCWorld local247 = Static54.method1310(local240);
                    if (local247 != null) {
                        Static101.aClass10_Sub1Array1[local124++] = local247;
                    }
                }
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
                Static127.anInt3132 = 0;
                Static82.anInt2231 = 0;
                Static229.aByteArray70 = null;
                PreciseSleep.aLong174 = MonotonicTime.currentTimeMillis();
                return 0;
            } else {
                return Static10.method347(1002);
            }
        } catch (@Pc(277) IOException local277) {
            return Static10.method347(1003);
        }
    }
}
