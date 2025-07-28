package com.jagex.runetek4.game.world;

import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.MonotonicTime;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.network.BufferedSocket;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.util.system.SignLink;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class WorldList {

    @OriginalMember(owner = "client!ii", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_570 = JString.parse(")2");

    @OriginalMember(owner = "client!en", name = "A", descriptor = "Z")
    public static boolean loaded = false;

    @OriginalMember(owner = "client!bi", name = "R", descriptor = "I")
    public static int minId;

    @OriginalMember(owner = "client!ni", name = "q", descriptor = "I")
    public static int maxId;

    @OriginalMember(owner = "client!pl", name = "a", descriptor = "[Lclient!ba;")
    public static World[] worlds;

    @OriginalMember(owner = "client!qh", name = "e", descriptor = "[Lclient!ee;")
    public static WorldInfo[] countries;

    @OriginalMember(owner = "client!gi", name = "c", descriptor = "I")
    public static int step = 0;

    @OriginalMember(owner = "client!be", name = "kc", descriptor = "J")
    public static long openTime = 0L;

    @OriginalMember(owner = "client!sk", name = "gb", descriptor = "J")
    public static long closeTime = 0L;

    @OriginalMember(owner = "client!jb", name = "y", descriptor = "I")
    public static int bufferLen = 0;

    @OriginalMember(owner = "client!si", name = "cb", descriptor = "[B")
    public static byte[] buffer;

    @OriginalMember(owner = "client!lb", name = "u", descriptor = "I")
    public static int bufferOff = 0;

    @OriginalMember(owner = "client!hm", name = "fb", descriptor = "[Lclient!ba;")
    public static World[] sorted = new World[0];

    @OriginalMember(owner = "client!ic", name = "n", descriptor = "I")
    public static int size;

    @OriginalMember(owner = "client!gf", name = "T", descriptor = "I")
    public static int checksum;

    @OriginalMember(owner = "client!k", name = "t", descriptor = "I")
    public static int errors = 0;

    @OriginalMember(owner = "client!ea", name = "w", descriptor = "I")
    public static int worldPos = 1;

    @OriginalMember(owner = "client!ql", name = "b", descriptor = "(I)I")
    public static int fetch() {
        try {
            if (step == 0) {
                if (MonotonicTime.currentTimeMillis() - 5000L < closeTime) {
                    return 0;
                }
                Protocol.socketRequest = GameShell.signLink.openSocket(Client.worldListHostname, Client.worldListPort);
                openTime = MonotonicTime.currentTimeMillis();
                step = 1;
            }
            if (openTime + 30000L < MonotonicTime.currentTimeMillis()) {
                return close(1000);
            }
            @Pc(82) int checksum;
            @Pc(124) int i;
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
                checksum = 0;
                if (loaded) {
                    checksum = WorldList.checksum;
                }
                Protocol.outboundBuffer.p1(255);
                Protocol.outboundBuffer.p4(checksum);
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                if (Client.musicChannel != null) {
                    Client.musicChannel.method3571();
                }
                if (Client.soundChannel != null) {
                    Client.soundChannel.method3571();
                }
                i = Protocol.gameServerSocket.read();
                if (Client.musicChannel != null) {
                    Client.musicChannel.method3571();
                }
                if (Client.soundChannel != null) {
                    Client.soundChannel.method3571();
                }
                if (i != 0) {
                    return close(i);
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
            checksum = Protocol.gameServerSocket.available();
            if (checksum < 1) {
                return -1;
            }
            if (checksum > bufferLen - bufferOff) {
                checksum = bufferLen - bufferOff;
            }
            Protocol.gameServerSocket.read(bufferOff, checksum, buffer);
            bufferOff += checksum;
            if (bufferOff < bufferLen) {
                return -1;
            } else if (decode(buffer)) {
                sorted = new World[size];
                i = 0;
                for (@Pc(240) int id = minId; id <= maxId; id++) {
                    @Pc(247) World world = ClientScriptRunner.getWorld(id);
                    if (world != null) {
                        sorted[i++] = world;
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

    @OriginalMember(owner = "client!ob", name = "a", descriptor = "(IB)Z")
    public static boolean hopWorld(@OriginalArg(0) int id) {
        @Pc(3) World world = ClientScriptRunner.getWorld(id);
        if (world == null) {
            return false;
        } else if (SignLink.anInt5928 == 1 || SignLink.anInt5928 == 2 || Client.modeWhere == 2) {
            @Pc(31) byte[] local31 = world.hostname.method3148();
            Client.hostname = new String(local31, 0, local31.length);
            Player.worldId = world.id;
            if (Client.modeWhere != 0) {
                Client.defaultPort = Player.worldId + 40000;
                Client.port = Client.defaultPort;
                Client.alternatePort = Player.worldId + 50000;
            }
            return true;
        } else {
//            @Pc(62) JString port = aClass100_230;
//            if (client.modeWhere != 0) {
//                port = JString.concatenate(new JString[] { aClass100_193, JString.parseInt(world.id + 7000) });
//            }
//            @Pc(89) JString settings = aClass100_230;
//            if (client.settings != null) {
//                settings = JString.concatenate(new JString[] { aClass100_783, client.settings});
//            }
//            @Pc(182) JString url = JString.concatenate(new JString[] { HTTP_PROTOCOL, world.hostname, port, aClass100_1107, JString.parseInt(client.language), aClass100_801, JString.parseInt(client.affiliate), settings, aClass100_659, client.objectTag ? aClass100_184 : aClass100_945, aClass100_420, client.javaScript ? aClass100_184 : aClass100_945, aClass100_260, client.advertSuppressed ? aClass100_184 : aClass100_945 });
//            try {
//                client.instance.getAppletContext().showDocument(url.method3107(), "_self");
//                return true;
//            } catch (@Pc(191) Exception local191) {
//                return false;
//            }
            return false;
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
        if (Client.worldListPort == Client.worldListDefaultPort) {
            Client.worldListPort = Client.worldListAlternatePort;
        } else {
            Client.worldListPort = Client.worldListDefaultPort;
        }
        return -1;
    }

    @OriginalMember(owner = "client!nh", name = "a", descriptor = "(I[B)Z")
    public static boolean decode(@OriginalArg(1) byte[] bytes) {
        @Pc(13) Packet packet = new Packet(bytes);
        @Pc(17) int version = packet.g1();
        if (version != 1) {
            return false;
        }
        @Pc(33) boolean worldsUpdated = packet.g1() == 1;
        if (worldsUpdated) {
            decodeWorlds(packet);
        }
        decodePlayers(packet);
        return true;
    }

    @OriginalMember(owner = "client!hi", name = "a", descriptor = "(Lclient!wa;I)V")
    public static void decodeWorlds(@OriginalArg(0) Packet packet) {
        @Pc(9) int countryCount = packet.gSmart1or2();
        countries = new WorldInfo[countryCount];
        for (int index = 0; index < countryCount; index++) {
            countries[index] = new WorldInfo();
            countries[index].flag = packet.gSmart1or2();
            countries[index].name = packet.gjstr2();
        }
        minId = packet.gSmart1or2();
        maxId = packet.gSmart1or2();
        size = packet.gSmart1or2();
        worlds = new World[maxId + 1 - minId];
        for (int index = 0; index < size; index++) {
            @Pc(77) int offset = packet.gSmart1or2();
            @Pc(85) World world = worlds[offset] = new World();
            world.country = packet.g1();
            world.flags = packet.g4();
            world.id = offset + minId;
            world.activity = packet.gjstr2();
            world.hostname = packet.gjstr2();
        }
        checksum = packet.g4();
        loaded = true;
    }

    @OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!wa;I)V")
    public static void decodePlayers(@OriginalArg(0) Packet packet) {
        for (@Pc(7) int i = 0; i < size; i++) {
            @Pc(18) int offset = packet.gSmart1or2();
            @Pc(22) int players = packet.g2();
            if (players == 65535) {
                players = -1;
            }
            if (worlds[offset] != null) {
                worlds[offset].players = players;
            }
        }
    }

    @OriginalMember(owner = "client!h", name = "a", descriptor = "(I)Lclient!ba;")
    public static World getNextWorld() {
        return sorted.length > worldPos ? sorted[worldPos++] : null;
    }

    @OriginalMember(owner = "client!sh", name = "a", descriptor = "(IZBIZ)V")
    public static void sortWorldList(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3) {
        sortWorldList(arg0, arg2, sorted.length - 1, arg3, 0, arg1);
    }

    @OriginalMember(owner = "client!ge", name = "a", descriptor = "(IIIZIZZ)V")
    public static void sortWorldList(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int hi, @OriginalArg(3) boolean arg3, @OriginalArg(4) int lo, @OriginalArg(5) boolean arg5) {
        if (hi <= lo) {
            return;
        }
        @Pc(13) int mid = (hi + lo) / 2;
        @Pc(15) int i = lo;
        @Pc(19) World pivot = sorted[mid];
        sorted[mid] = sorted[hi];
        sorted[hi] = pivot;
        for (@Pc(31) int j = lo; j < hi; j++) {
            if (compare(pivot, sorted[j], arg0, arg1, arg3, arg5) <= 0) {
                @Pc(53) World world = sorted[j];
                sorted[j] = sorted[i];
                sorted[i++] = world;
            }
        }
        sorted[hi] = sorted[i];
        sorted[i] = pivot;
        sortWorldList(arg0, arg1, i - 1, arg3, lo, arg5);
        sortWorldList(arg0, arg1, hi, arg3, i + 1, arg5);
    }

    @OriginalMember(owner = "client!bh", name = "a", descriptor = "(B)Lclient!ba;")
    public static World getFirstWorld() {
        worldPos = 0;
        return getNextWorld();
    }

    @OriginalMember(owner = "client!na", name = "a", descriptor = "(Lclient!ba;Lclient!ba;IIIZZ)I")
    public static int compare(@OriginalArg(0) World y, @OriginalArg(1) World x, @OriginalArg(3) int secondaryField, @OriginalArg(4) int arg3, @OriginalArg(5) boolean secondaryDesc, @OriginalArg(6) boolean primaryDesc) {
        @Pc(8) int primary = compare(x, arg3, y, primaryDesc);
        if (primary != 0) {
            return primaryDesc ? -primary : primary;
        } else if (secondaryField == -1) {
            return 0;
        } else {
            @Pc(42) int secondary = compare(x, secondaryField, y, secondaryDesc);
            return secondaryDesc ? -secondary : secondary;
        }
    }

    @OriginalMember(owner = "client!wb", name = "a", descriptor = "(Lclient!ba;IILclient!ba;Z)I")
    public static int compare(@OriginalArg(0) World x, @OriginalArg(1) int field, @OriginalArg(3) World y, @OriginalArg(4) boolean desc) {
        if (field == 1) {
            @Pc(11) int xPlayers = x.players;
            @Pc(14) int yPlayers = y.players;
            if (!desc) {
                if (yPlayers == -1) {
                    yPlayers = 2001;
                }
                if (xPlayers == -1) {
                    xPlayers = 2001;
                }
            }
            return xPlayers - yPlayers;
        } else if (field == 2) {
            return x.getWorldInfo().name.compare(y.getWorldInfo().name);
        } else if (field == 3) {
            if (x.activity.strEquals(aClass100_570)) {
                if (y.activity.strEquals(aClass100_570)) {
                    return 0;
                } else if (desc) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (y.activity.strEquals(aClass100_570)) {
                return desc ? 1 : -1;
            } else {
                return x.activity.compare(y.activity);
            }
        } else if (field == 4) {
            return x.isLootShare() ? (y.isLootShare() ? 0 : 1) : y.isLootShare() ? -1 : 0;
        } else if (field == 5) {
            return x.isQuickChat() ? (y.isQuickChat() ? 0 : 1) : (y.isQuickChat() ? -1 : 0);
        } else if (field == 6) {
            return x.isPvp() ? (y.isPvp() ? 0 : 1) : (y.isPvp() ? -1 : 0);
        } else if (field == 7) {
            return x.isMembers() ? (y.isMembers() ? 0 : 1) : (y.isMembers() ? -1 : 0);
        } else {
            return x.id - y.id;
        }
    }
}
