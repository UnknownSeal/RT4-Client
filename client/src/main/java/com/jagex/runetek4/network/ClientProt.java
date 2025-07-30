package com.jagex.runetek4.network;

import com.jagex.runetek4.*;
import com.jagex.runetek4.data.cache.media.component.Widget;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.LoginManager;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.game.logic.PathFinder;
import com.jagex.runetek4.graphics.core.DisplayMode;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.widget.WidgetList;
import com.jagex.runetek4.ui.widget.MiniMenu;
import com.jagex.runetek4.ui.events.WidgetEvent;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;

public class ClientProt {
    @OriginalMember(owner = "runetek4.client!ej", name = "i", descriptor = "(I)V")
    public static void sendWindowDetails() {
        Protocol.outboundBuffer.pIsaac1(243);
        Protocol.outboundBuffer.p1(DisplayMode.getWindowMode());
        Protocol.outboundBuffer.p2(GameShell.canvasWidth);
        Protocol.outboundBuffer.p2(GameShell.canvasHeigth);
        Protocol.outboundBuffer.p1(Preferences.antiAliasingMode);
    }

    @OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "(Lclient!na;IIBI)V")
    public static void method4512(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
        @Pc(8) Widget local8 = WidgetList.getCreatedComponent(arg3, arg1);
        if (local8 == null) {
            return;
        }
        if (local8.onOptionClick != null) {
            @Pc(19) WidgetEvent local19 = new WidgetEvent();
            local19.arguments = local8.onOptionClick;
            local19.source = local8;
            local19.opBase = arg0;
            local19.op = arg2;
            ClientScriptRunner.run(local19);
        }
        @Pc(37) boolean local37 = true;
        if (local8.contentType > 0) {
            local37 = MiniMenu.shouldTriggerIdleTimeout(local8);
        }
        if (!local37 || !WidgetList.getServerActiveProperties(local8).isButtonEnabled(arg2 - 1)) {
            return;
        }
        if (arg2 == 1) {
            Protocol.outboundBuffer.pIsaac1(155);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 2) {
            Protocol.outboundBuffer.pIsaac1(196);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 3) {
            Protocol.outboundBuffer.pIsaac1(124);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 4) {
            Protocol.outboundBuffer.pIsaac1(199);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 5) {
            Protocol.outboundBuffer.pIsaac1(234);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 6) {
            Protocol.outboundBuffer.pIsaac1(168);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 7) {
            Protocol.outboundBuffer.pIsaac1(166);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 8) {
            Protocol.outboundBuffer.pIsaac1(64);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 9) {
            Protocol.outboundBuffer.pIsaac1(53);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (arg2 == 10) {
            Protocol.outboundBuffer.pIsaac1(9);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
    }

    @OriginalMember(owner = "runetek4.client!pi", name = "c", descriptor = "(III)V")
    public static void method3502(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        @Pc(13) int local13 = arg0;
        if (arg0 > 25) {
            local13 = 25;
        }
        arg0--;
        @Pc(23) int local23 = PathFinder.queueX[arg0];
        @Pc(27) int local27 = PathFinder.queueZ[arg0];
        if (arg1 == 0) {
            Protocol.outboundBuffer.pIsaac1(215);
            Protocol.outboundBuffer.p1(local13 + local13 + 3);
            System.out.println("Steps: " + (local13 + local13 + 3));
        }
        if (arg1 == 1) {
            Protocol.outboundBuffer.pIsaac1(39);
            Protocol.outboundBuffer.p1(local13 + local13 + 3 + 14);
        }
        if (arg1 == 2) {
            Protocol.outboundBuffer.pIsaac1(77);
            Protocol.outboundBuffer.p1(local13 + local13 + 3);
        }
        Protocol.outboundBuffer.p1b_alt1(Keyboard.pressedKeys[Keyboard.KEY_CTRL] ? 1 : 0);
        System.out.println("InvertRun: " + (Keyboard.pressedKeys[Keyboard.KEY_CTRL] ? 1 : 0) );
        Protocol.outboundBuffer.p2((Camera.originX + local23));
        System.out.println("StartX: " + (Camera.originX + local23));
        Protocol.outboundBuffer.p2_alt2(Camera.originZ + local27);
        System.out.println("StartZ: " + (Camera.originZ + local27));
        LoginManager.mapFlagZ = PathFinder.queueZ[0];
        LoginManager.mapFlagX = PathFinder.queueX[0];
        for (@Pc(126) int local126 = 1; local126 < local13; local126++) {
            arg0--;
            Protocol.outboundBuffer.p1b_alt1(PathFinder.queueX[arg0] - local23);
            System.out.println(PathFinder.queueX[arg0] - local23);
            Protocol.outboundBuffer.p1_alt3(PathFinder.queueZ[arg0] - local27);
            System.out.println(PathFinder.queueZ[arg0] - local27);
        }
    }

    @OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(IILclient!na;)V")
    public static void clickPlayerOption(@OriginalArg(0) int arg0, @OriginalArg(2) JString arg1) {
        @Pc(7) JString local7 = arg1.method3159().toTitleCase();
        @Pc(13) boolean local13 = false;
        for (@Pc(15) int local15 = 0; local15 < PlayerList.playerCount; local15++) {
            @Pc(28) Player local28 = PlayerList.players[PlayerList.playerIds[local15]];
            if (local28 != null && local28.username != null && local28.username.equalsIgnoreCase(local7)) {
                local13 = true;
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local28.movementQueueX[0], 1, 0, 2, local28.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                if (arg0 == 1) {
                    Protocol.outboundBuffer.pIsaac1(68);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                } else if (arg0 == 4) {
                    Protocol.outboundBuffer.pIsaac1(180);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                } else if (arg0 == 5) {
                    Protocol.outboundBuffer.pIsaac1(4);
                    Protocol.outboundBuffer.p2_alt1(PlayerList.playerIds[local15]);
                } else if (arg0 == 6) {
                    Protocol.outboundBuffer.pIsaac1(133);
                    Protocol.outboundBuffer.p2_alt1(PlayerList.playerIds[local15]);
                } else if (arg0 == 7) {
                    Protocol.outboundBuffer.pIsaac1(114);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                }
                break;
            }
        }
        if (!local13) {
            Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { LocalizedText.UNABLETOFIND, local7 }));
        }
    }

    @OriginalMember(owner = "client!ah", name = "a", descriptor = "(BZ)V")
    public static void ping(@OriginalArg(1) boolean arg0) {
        Client.audioLoop();
        if (Client.gameState != 30 && Client.gameState != 25) {
            return;
        }
        Protocol.anInt3251++;
        if (Protocol.anInt3251 < 50 && !arg0) {
            return;
        }
        Protocol.anInt3251 = 0;
        if (!LoginManager.aBoolean247 && Protocol.gameServerSocket != null) {
            Protocol.outboundBuffer.pIsaac1(93);
            try {
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                Protocol.outboundBuffer.offset = 0;
            } catch (@Pc(53) IOException local53) {
                LoginManager.aBoolean247 = true;
            }
        }
        Client.audioLoop();
    }
}
