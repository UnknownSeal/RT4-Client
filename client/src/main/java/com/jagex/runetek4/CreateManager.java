package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class CreateManager {
    @OriginalMember(owner = "client!oe", name = "l", descriptor = "I")
    public static int step = 0;

    @OriginalMember(owner = "client!sc", name = "y", descriptor = "I")
    public static int reply = -2;

    @OriginalMember(owner = "client!si", name = "S", descriptor = "[Lclient!na;")
    public static JString[] suggestedNames;

    @OriginalMember(owner = "client!sf", name = "a", descriptor = "I")
    public static int loops = 0;

    @OriginalMember(owner = "client!eg", name = "v", descriptor = "I")
    public static int errors = 0;

    @OriginalMember(owner = "client!da", name = "a", descriptor = "(IIIILclient!na;JI)V")
    public static void createAccount(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) JString password, @OriginalArg(5) long name, @OriginalArg(6) int arg5) {
        @Pc(8) Packet packet = new Packet(128);
        packet.p1(10);
        packet.p2((int) (Math.random() * 99999.0D));
        packet.p2(530);
        packet.p8(name);
        packet.p4((int) (Math.random() * 9.9999999E7D));
        packet.pjstr(password);
        packet.p4((int) (Math.random() * 9.9999999E7D));
        packet.p2(client.affiliate);
        packet.p1(arg0);
        packet.p1(arg2);
        packet.p4((int) (Math.random() * 9.9999999E7D));
        packet.p2(arg5);
        packet.p2(arg1);
        packet.p4((int) (Math.random() * 9.9999999E7D));
        packet.rsaenc(LoginManager.RSA_EXPONENT, LoginManager.RSA_MODULUS);
        Protocol.outboundBuffer.offset = 0;
        Protocol.outboundBuffer.p1(36);
        Protocol.outboundBuffer.p1(packet.offset);
        Protocol.outboundBuffer.pdata(packet.data, packet.offset);
        reply = -3;
        step = 1;
        loops = 0;
        errors = 0;
    }

    @OriginalMember(owner = "client!gd", name = "a", descriptor = "(JI)V")
    public static void checkName(@OriginalArg(0) long name) {
        Protocol.outboundBuffer.offset = 0;
        Protocol.outboundBuffer.p1(186);
        Protocol.outboundBuffer.p8(name);
        step = 1;
        loops = 0;
        errors = 0;
        reply = -3;
    }

    @OriginalMember(owner = "client!jl", name = "a", descriptor = "(IIIII)V")
    public static void checkInfo(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        Protocol.outboundBuffer.offset = 0;
        Protocol.outboundBuffer.p1(147);
        Protocol.outboundBuffer.p1(arg2);
        Protocol.outboundBuffer.p1(arg3);
        Protocol.outboundBuffer.p2(arg0);
        Protocol.outboundBuffer.p2(arg1);
        loops = 0;
        errors = 0;
        step = 1;
        reply = -3;
    }

    @OriginalMember(owner = "client!mh", name = "f", descriptor = "(B)V")
    public static void handleLoginScreenActions() {
        if (step == 0) {
            return;
        }
        try {
            if (++loops > 2000) {
                if (Protocol.gameServerSocket != null) {
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                }
                if (errors >= 1) {
                    reply = -5;
                    step = 0;
                    return;
                }
                step = 1;
                loops = 0;
                errors++;
                if (client.defaultPort == client.port) {
                    client.port = client.alternatePort;
                } else {
                    client.port = client.defaultPort;
                }
            }
            if (step == 1) {
                Protocol.socketRequest = GameShell.signLink.openSocket(client.hostname, client.port);
                step = 2;
            }
            @Pc(120) int local120;
            if (step == 2) {
                if (Protocol.socketRequest.status == 2) {
                    throw new IOException();
                }
                if (Protocol.socketRequest.status != 1) {
                    return;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.socketRequest = null;
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                local120 = Protocol.gameServerSocket.read();
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                if (local120 != 21) {
                    reply = local120;
                    step = 0;
                    Protocol.gameServerSocket.closeGracefully();
                    Protocol.gameServerSocket = null;
                    return;
                }
                step = 3;
            }
            if (step == 3) {
                if (Protocol.gameServerSocket.available() < 1) {
                    return;
                }
                suggestedNames = new JString[Protocol.gameServerSocket.read()];
                step = 4;
            }
            if (step == 4) {
                if (Protocol.gameServerSocket.available() < suggestedNames.length * 8) {
                    return;
                }
                Protocol.inboundBuffer.offset = 0;
                Protocol.gameServerSocket.read(0, suggestedNames.length * 8, Protocol.inboundBuffer.data);
                for (local120 = 0; local120 < suggestedNames.length; local120++) {
                    suggestedNames[local120] = Base37.decode37(Protocol.inboundBuffer.g8());
                }
                reply = 21;
                step = 0;
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
            }
        } catch (@Pc(238) IOException ioException) {
            if (Protocol.gameServerSocket != null) {
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
            }
            if (errors < 1) {
                errors++;
                if (client.defaultPort == client.port) {
                    client.port = client.alternatePort;
                } else {
                    client.port = client.defaultPort;
                }
                loops = 0;
                step = 1;
            } else {
                reply = -4;
                step = 0;
            }
        }
    }
}
