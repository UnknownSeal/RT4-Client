package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.shared.framework.gwc.World;
import com.jagex.runetek4.game.shared.framework.gwc.WorldInfo;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.util.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class WorldList {
    @OriginalMember(owner = "runetek4.client!nd", name = "x", descriptor = "Lclient!na;")
    public static final JString aClass100_783 = JString.parse(")4p=");
    @OriginalMember(owner = "runetek4.client!ja", name = "s", descriptor = "Lclient!na;")
    public static final JString HTTP_PROTOCOL = JString.parse("http:)4)4");
    @OriginalMember(owner = "runetek4.client!wk", name = "x", descriptor = "Lclient!na;")
    public static final JString aClass100_1107 = JString.parse(")4l=");
    @OriginalMember(owner = "runetek4.client!rc", name = "G", descriptor = "Lclient!na;")
    public static final JString aClass100_230 = JString.parse("");
    @OriginalMember(owner = "runetek4.client!l", name = "d", descriptor = "Lclient!na;")
    public static final JString aClass100_659 = JString.parse(")4j");
    @OriginalMember(owner = "client!cg", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_184 = JString.parse("1");
    @OriginalMember(owner = "runetek4.client!vd", name = "F", descriptor = "Lclient!na;")
    public static final JString aClass100_945 = JString.parse("0");
    @OriginalMember(owner = "client!em", name = "u", descriptor = "Lclient!na;")
    public static final JString aClass100_420 = JString.parse(")1o");
    @OriginalMember(owner = "runetek4.client!q", name = "a", descriptor = "Lclient!na;")
    public static final JString aClass100_260 = JString.parse(")1a2)1m");
    @OriginalMember(owner = "client!ch", name = "x", descriptor = "Lclient!na;")
    public static final JString aClass100_193 = JString.parse(":");
    @OriginalMember(owner = "runetek4.client!ii", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_570 = JString.parse(")2");
    @OriginalMember(owner = "runetek4.client!ob", name = "o", descriptor = "Lclient!na;")
    private static final JString aClass100_801 = JString.parse(")4a=");
    @OriginalMember(owner = "runetek4.client!en", name = "A", descriptor = "Z")
    public static boolean loaded = false;
    @OriginalMember(owner = "client!bi", name = "R", descriptor = "I")
    public static int minId;
    @OriginalMember(owner = "runetek4.client!ni", name = "q", descriptor = "I")
    public static int maxId;
    @OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "[Lclient!ba;")
    public static World[] worlds;
    @OriginalMember(owner = "runetek4.client!qh", name = "e", descriptor = "[Lclient!ee;")
    public static WorldInfo[] countries;
    @OriginalMember(owner = "client!gi", name = "c", descriptor = "I")
    public static int step = 0;
    @OriginalMember(owner = "client!be", name = "kc", descriptor = "J")
    public static long openTime = 0L;
    @OriginalMember(owner = "runetek4.client!sk", name = "gb", descriptor = "J")
    public static long closeTime = 0L;
    @OriginalMember(owner = "runetek4.client!jb", name = "y", descriptor = "I")
    public static int bufferLen = 0;
    @OriginalMember(owner = "runetek4.client!si", name = "cb", descriptor = "[B")
    public static byte[] buffer;
    @OriginalMember(owner = "runetek4.client!lb", name = "u", descriptor = "I")
    public static int bufferOff = 0;
    @OriginalMember(owner = "runetek4.client!hm", name = "fb", descriptor = "[Lclient!ba;")
    public static World[] sorted = new World[0];
    @OriginalMember(owner = "runetek4.client!ic", name = "n", descriptor = "I")
    public static int size;
    @OriginalMember(owner = "client!gf", name = "T", descriptor = "I")
    public static int checksum;
    @OriginalMember(owner = "runetek4.client!k", name = "t", descriptor = "I")
    public static int errors = 0;
    @OriginalMember(owner = "client!ea", name = "w", descriptor = "I")
    public static int worldPos = 1;

    @OriginalMember(owner = "runetek4.client!ql", name = "b", descriptor = "(I)I")
    public static int fetch() {
        try {
            if (step == 0) {
                if (MonotonicTime.currentTimeMillis() - 5000L < closeTime) {
                    return 0;
                }
                Protocol.socketRequest = GameShell.signLink.openSocket(client.worldListHostname, client.worldListPort);
                openTime = MonotonicTime.currentTimeMillis();
                step = 1;
            }
            if (openTime + 30000L < MonotonicTime.currentTimeMillis()) {
                return close(1000);
            }
            @Pc(82) int local82;
            @Pc(124) int local124;
            if (step == 1) {
                if (Protocol.socketRequest.status == 2) {
                    return close(1001);
                }
                if (Protocol.socketRequest.status != 1) {
                    return -1;
                }
                Protocol.gameServerSocket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.outboundBuffer.offset = 0;
                Protocol.socketRequest = null;
                local82 = 0;
                if (loaded) {
                    local82 = checksum;
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
                    return close(local124);
                }
                step = 2;
            }
            if (step == 2) {
                if (Protocol.gameServerSocket.available() < 2) {
                    return -1;
                }
                bufferLen = Protocol.gameServerSocket.read();
                bufferLen <<= 0x8;
                bufferLen += Protocol.gameServerSocket.read();
                step = 3;
                bufferOff = 0;
                buffer = new byte[bufferLen];
            }
            if (step != 3) {
                return -1;
            }
            local82 = Protocol.gameServerSocket.available();
            if (local82 < 1) {
                return -1;
            }
            if (local82 > bufferLen - bufferOff) {
                local82 = bufferLen - bufferOff;
            }
            Protocol.gameServerSocket.read(bufferOff, local82, buffer);
            bufferOff += local82;
            if (bufferOff < bufferLen) {
                return -1;
            } else if (decode(buffer)) {
                sorted = new World[size];
                local124 = 0;
                for (@Pc(240) int local240 = minId; local240 <= maxId; local240++) {
                    @Pc(247) World local247 = ClientScriptRunner.getWorld(local240);
                    if (local247 != null) {
                        sorted[local124++] = local247;
                    }
                }
                Protocol.gameServerSocket.closeGracefully();
                Protocol.gameServerSocket = null;
                errors = 0;
                step = 0;
                buffer = null;
                closeTime = MonotonicTime.currentTimeMillis();
                return 0;
            } else {
                return close(1002);
            }
        } catch (@Pc(277) IOException local277) {
            return close(1003);
        }
    }

    @OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IB)Z")
    public static boolean hopWorld(@OriginalArg(0) int arg0) {
        @Pc(3) World local3 = ClientScriptRunner.getWorld(arg0);
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
            @Pc(62) JString local62 = aClass100_230;
            if (client.modeWhere != 0) {
                local62 = JString.concatenate(new JString[] { aClass100_193, JString.parseInt(local3.id + 7000) });
            }
            @Pc(89) JString local89 = aClass100_230;
            if (client.settings != null) {
                local89 = JString.concatenate(new JString[] { aClass100_783, client.settings});
            }
            @Pc(182) JString local182 = JString.concatenate(new JString[] { HTTP_PROTOCOL, local3.hostname, local62, aClass100_1107, JString.parseInt(client.language), aClass100_801, JString.parseInt(client.affiliate), local89, aClass100_659, client.objectTag ? aClass100_184 : aClass100_945, aClass100_420, client.javaScript ? aClass100_184 : aClass100_945, aClass100_260, client.advertSuppressed ? aClass100_184 : aClass100_945 });
            try {
                client.instance.getAppletContext().showDocument(local182.method3107(), "_self");
                return true;
            } catch (@Pc(191) Exception local191) {
                return false;
            }
        }
    }

    @OriginalMember(owner = "client!an", name = "a", descriptor = "(BI)I")
    public static int close(@OriginalArg(1) int arg0) {
        if (Protocol.gameServerSocket != null) {
            Protocol.gameServerSocket.closeGracefully();
            Protocol.gameServerSocket = null;
        }
        errors++;
        if (errors > 4) {
            step = 0;
            errors = 0;
            return arg0;
        }
        step = 0;
        if (client.worldListPort == client.worldListDefaultPort) {
            client.worldListPort = client.worldListAlternatePort;
        } else {
            client.worldListPort = client.worldListDefaultPort;
        }
        return -1;
    }

    @OriginalMember(owner = "runetek4.client!nh", name = "a", descriptor = "(I[B)Z")
    public static boolean decode(@OriginalArg(1) byte[] arg0) {
        @Pc(13) Packet local13 = new Packet(arg0);
        @Pc(17) int local17 = local13.g1();
        if (local17 != 1) {
            return false;
        }
        @Pc(33) boolean local33 = local13.g1() == 1;
        if (local33) {
            decodeWorlds(local13);
        }
        decodePlayers(local13);
        return true;
    }

    @OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(Lclient!wa;I)V")
    public static void decodeWorlds(@OriginalArg(0) Packet packet) {
        @Pc(9) int local9 = packet.gSmart1or2();
        countries = new WorldInfo[local9];
        for (int index = 0; index < local9; index++) {
            countries[index] = new WorldInfo();
            countries[index].flag = packet.gSmart1or2();
            countries[index].name = packet.gjstr2();
        }
        minId = packet.gSmart1or2();
        maxId = packet.gSmart1or2();
        size = packet.gSmart1or2();
        worlds = new World[maxId + 1 - minId];
        for (int index = 0; index < size; index++) {
            @Pc(77) int local77 = packet.gSmart1or2();
            @Pc(85) World local85 = worlds[local77] = new World();
            local85.country = packet.g1();
            local85.flags = packet.g4();
            local85.id = local77 + minId;
            local85.activity = packet.gjstr2();
            local85.hostname = packet.gjstr2();
        }
        checksum = packet.g4();
        loaded = true;
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!wa;I)V")
    public static void decodePlayers(@OriginalArg(0) Packet arg0) {
        for (@Pc(7) int local7 = 0; local7 < size; local7++) {
            @Pc(18) int local18 = arg0.gSmart1or2();
            @Pc(22) int local22 = arg0.g2();
            if (local22 == 65535) {
                local22 = -1;
            }
            if (worlds[local18] != null) {
                worlds[local18].players = local22;
            }
        }
    }

    @OriginalMember(owner = "client!h", name = "a", descriptor = "(I)Lclient!ba;")
    public static World getNextWorld() {
        return sorted.length > worldPos ? sorted[worldPos++] : null;
    }

    @OriginalMember(owner = "client!sh", name = "a", descriptor = "(IZBIZ)V")
    public static void sortWorldList(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3) {
        method1697(arg0, arg2, sorted.length - 1, arg3, 0, arg1);
    }

    @OriginalMember(owner = "client!ge", name = "a", descriptor = "(IIIZIZZ)V")
    public static void method1697(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) boolean arg3, @OriginalArg(4) int arg4, @OriginalArg(5) boolean arg5) {
        if (arg2 <= arg4) {
            return;
        }
        @Pc(13) int local13 = (arg2 + arg4) / 2;
        @Pc(15) int local15 = arg4;
        @Pc(19) World local19 = sorted[local13];
        sorted[local13] = sorted[arg2];
        sorted[arg2] = local19;
        for (@Pc(31) int local31 = arg4; local31 < arg2; local31++) {
            if (method3115(local19, sorted[local31], arg0, arg1, arg3, arg5) <= 0) {
                @Pc(53) World local53 = sorted[local31];
                sorted[local31] = sorted[local15];
                sorted[local15++] = local53;
            }
        }
        sorted[arg2] = sorted[local15];
        sorted[local15] = local19;
        method1697(arg0, arg1, local15 - 1, arg3, arg4, arg5);
        method1697(arg0, arg1, arg2, arg3, local15 + 1, arg5);
    }

    @OriginalMember(owner = "client!bh", name = "a", descriptor = "(B)Lclient!ba;")
    public static World getFirstWorld() {
        worldPos = 0;
        return getNextWorld();
    }

    @OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(Lclient!ba;Lclient!ba;IIIZZ)I")
    public static int method3115(@OriginalArg(0) World arg0, @OriginalArg(1) World arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) boolean arg5) {
        @Pc(8) int local8 = method4595(arg1, arg3, arg0, arg5);
        if (local8 != 0) {
            return arg5 ? -local8 : local8;
        } else if (arg2 == -1) {
            return 0;
        } else {
            @Pc(42) int local42 = method4595(arg1, arg2, arg0, arg4);
            return arg4 ? -local42 : local42;
        }
    }

    @OriginalMember(owner = "runetek4.client!wb", name = "a", descriptor = "(Lclient!ba;IILclient!ba;Z)I")
    public static int method4595(@OriginalArg(0) World arg0, @OriginalArg(1) int arg1, @OriginalArg(3) World arg2, @OriginalArg(4) boolean arg3) {
        if (arg1 == 1) {
            @Pc(11) int local11 = arg0.players;
            @Pc(14) int local14 = arg2.players;
            if (!arg3) {
                if (local14 == -1) {
                    local14 = 2001;
                }
                if (local11 == -1) {
                    local11 = 2001;
                }
            }
            return local11 - local14;
        } else if (arg1 == 2) {
            return arg0.getWorldInfo().name.compare(arg2.getWorldInfo().name);
        } else if (arg1 == 3) {
            if (arg0.activity.strEquals(aClass100_570)) {
                if (arg2.activity.strEquals(aClass100_570)) {
                    return 0;
                } else if (arg3) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (arg2.activity.strEquals(aClass100_570)) {
                return arg3 ? 1 : -1;
            } else {
                return arg0.activity.compare(arg2.activity);
            }
        } else if (arg1 == 4) {
            return arg0.isLootShare() ? (arg2.isLootShare() ? 0 : 1) : arg2.isLootShare() ? -1 : 0;
        } else if (arg1 == 5) {
            return arg0.isQuickChat() ? (arg2.isQuickChat() ? 0 : 1) : (arg2.isQuickChat() ? -1 : 0);
        } else if (arg1 == 6) {
            return arg0.isPvp() ? (arg2.isPvp() ? 0 : 1) : (arg2.isPvp() ? -1 : 0);
        } else if (arg1 == 7) {
            return arg0.isMembers() ? (arg2.isMembers() ? 0 : 1) : (arg2.isMembers() ? -1 : 0);
        } else {
            return arg0.id - arg2.id;
        }
    }
}
