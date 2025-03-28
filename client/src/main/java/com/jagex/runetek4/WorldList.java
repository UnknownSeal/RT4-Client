package com.jagex.runetek4;

import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class WorldList {
    @OriginalMember(owner = "runetek4.client!ob", name = "o", descriptor = "Lclient!na;")
    private static final JString aClass100_801 = JString.parse(")4a=");
    @OriginalMember(owner = "runetek4.client!en", name = "A", descriptor = "Z")
    public static boolean loaded = false;

    @OriginalMember(owner = "runetek4.client!ql", name = "b", descriptor = "(I)I")
    public static int fetch() {
        try {
            if (Static82.anInt2231 == 0) {
                if (MonotonicTime.currentTimeMillis() - 5000L < PreciseSleep.aLong174) {
                    return 0;
                }
                Protocol.socketRequest = GameShell.signLink.openSocket(client.worldListHostname, client.worldListPort);
                Static15.aLong18 = MonotonicTime.currentTimeMillis();
                Static82.anInt2231 = 1;
            }
            if (Static15.aLong18 + 30000L < MonotonicTime.currentTimeMillis()) {
                return Static10.method347(1000);
            }
            @Pc(82) int local82;
            @Pc(124) int local124;
            if (Static82.anInt2231 == 1) {
                if (Protocol.socketRequest.status == 2) {
                    return Static10.method347(1001);
                }
                if (Protocol.socketRequest.status != 1) {
                    return -1;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.outboundBuffer.offset = 0;
                Protocol.socketRequest = null;
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

    @OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IB)Z")
    public static boolean hopWorld(@OriginalArg(0) int arg0) {
        @Pc(3) GWCWorld local3 = Static54.method1310(arg0);
        if (local3 == null) {
            return false;
        } else if (SignLink.anInt5928 == 1 || SignLink.anInt5928 == 2 || client.modeWhere == 2) {
            @Pc(31) byte[] local31 = local3.hostname.method3148();
            client.hostname = new String(local31, 0, local31.length);
            Player.worldId = local3.id;
            if (client.modeWhere != 0) {
                client.defaultPort = Player.worldId + 40000;
                client.port = client.defaultPort;
                client.alternatePort = Player.worldId + 50000;
            }
            return true;
        } else {
            @Pc(62) JString local62 = Static211.aClass100_230;
            if (client.modeWhere != 0) {
                local62 = JString.concatenate(new JString[] { Static31.aClass100_193, JString.parseInt(local3.id + 7000) });
            }
            @Pc(89) JString local89 = Static211.aClass100_230;
            if (client.settings != null) {
                local89 = JString.concatenate(new JString[] { Static167.aClass100_783, client.settings});
            }
            @Pc(182) JString local182 = JString.concatenate(new JString[] { Static115.aClass100_582, local3.hostname, local62, Static279.aClass100_1107, JString.parseInt(client.language), aClass100_801, JString.parseInt(client.affiliate), local89, Static139.aClass100_659, client.objectTag ? Static30.aClass100_184 : Static260.aClass100_945, Static60.aClass100_420, client.javaScript ? Static30.aClass100_184 : Static260.aClass100_945, Static198.aClass100_260, client.advertSuppressed ? Static30.aClass100_184 : Static260.aClass100_945 });
            try {
                client.instance.getAppletContext().showDocument(local182.method3107(), "_self");
                return true;
            } catch (@Pc(191) Exception local191) {
                return false;
            }
        }
    }
}
