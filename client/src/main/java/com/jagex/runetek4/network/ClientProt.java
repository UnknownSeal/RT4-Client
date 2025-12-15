package com.jagex.runetek4.network;

import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.scene.Camera;
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
import com.jagex.runetek4.ui.component.ComponentList;
import com.jagex.runetek4.ui.component.MiniMenu;
import com.jagex.runetek4.ui.events.ComponentEvent;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;

public class ClientProt {
    public static int DETECT_MODIFIED_CLIENT = 20;
    public static int FRIENDLIST_ADD = 120;
    public static int FRIENDLIST_DEL = 57;
    public static int FRIEND_SETRANK = 188;
    public static int TRANSMITVAR_VERIFYID = 177;
    public static int SOUND_SONGEND = 137;
    public static int MESSAGE_PUBLIC = 237;
    public static int MESSAGE_PRIVATE = 201;

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
        @Pc(8) Component local8 = ComponentList.getCreatedComponent(arg3, arg1);
        if (local8 == null) {
            return;
        }
        if (local8.onOptionClick != null) {
            @Pc(19) ComponentEvent local19 = new ComponentEvent();
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
        if (!local37 || !ComponentList.getServerActiveProperties(local8).isButtonEnabled(arg2 - 1)) {
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

    @OriginalMember(owner = "client!pi", name = "c", descriptor = "(III)V")
    public static void method3502(@OriginalArg(1) int length, @OriginalArg(2) int type) {
        @Pc(13) int bufferSize = length;
        if (length > 25) {
            bufferSize = 25;
        }
        length--;
        @Pc(23) int startX = PathFinder.bfsStepX[length];
        @Pc(27) int startZ = PathFinder.bfsStepZ[length];

        if (type == 0) {
            Protocol.outboundBuffer.pIsaac1(215);
            Protocol.outboundBuffer.p1(bufferSize + bufferSize + 3);
        }
        if (type == 1) {
            Protocol.outboundBuffer.pIsaac1(39);
            Protocol.outboundBuffer.p1(bufferSize + bufferSize + 3 + 14);
        }
        if (type == 2) {
            Protocol.outboundBuffer.pIsaac1(77);
            Protocol.outboundBuffer.p1(bufferSize + bufferSize + 3);
        }
        Protocol.outboundBuffer.p1b_alt1(Keyboard.pressedKeys[Keyboard.KEY_CTRL] ? 1 : 0);
        Protocol.outboundBuffer.p2((startX + Camera.sceneBaseTileX));
        Protocol.outboundBuffer.p2_alt2(startZ + Camera.sceneBaseTileZ);

        LoginManager.flagSceneTileX = PathFinder.bfsStepX[0];
        LoginManager.flagSceneTileZ = PathFinder.bfsStepZ[0];

        for (@Pc(126) int i = 1; i < bufferSize; i++) {
            length--;
            Protocol.outboundBuffer.p1b_alt1(PathFinder.bfsStepX[length] - startX);
            Protocol.outboundBuffer.p1b_alt3(PathFinder.bfsStepZ[length] - startZ);
        }
    }

    @OriginalMember(owner = "client!wh", name = "a", descriptor = "(IILclient!na;)V")
    public static void clickPlayerOption(@OriginalArg(0) int type, @OriginalArg(2) JString arg1) {
        @Pc(7) JString local7 = arg1.method3159().toTitleCase();
        @Pc(13) boolean local13 = false;
        for (@Pc(15) int local15 = 0; local15 < PlayerList.playerCount; local15++) {
            @Pc(28) Player local28 = PlayerList.players[PlayerList.playerIds[local15]];
            if (local28 != null && local28.username != null && local28.username.equalsIgnoreCase(local7)) {
                local13 = true;
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local28.movementQueueX[0], 1, 0, 2, local28.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                if (type == 1) {
                    Protocol.outboundBuffer.pIsaac1(68);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                } else if (type == 4) {
                    Protocol.outboundBuffer.pIsaac1(180);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                } else if (type == 5) {
                    Protocol.outboundBuffer.pIsaac1(4);
                    Protocol.outboundBuffer.p2_alt1(PlayerList.playerIds[local15]);
                } else if (type == 6) {
                    Protocol.outboundBuffer.pIsaac1(133);
                    Protocol.outboundBuffer.p2_alt1(PlayerList.playerIds[local15]);
                } else if (type == 7) {
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
