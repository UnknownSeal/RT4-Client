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

    // System/Security
    public static int DETECT_MODIFIED_CLIENT = 20;
    public static int NO_TIMEOUT = 93;
    public static int CLIENT_CHEAT = 44;
    public static int TRANSMITVAR_VERIFYID = 177;

    // Interface buttons
    public static int IF_BUTTON1 = 155;
    public static int IF_BUTTON2 = 196;
    public static int IF_BUTTON3 = 124;
    public static int IF_BUTTON4 = 199;
    public static int IF_BUTTON5 = 234;
    public static int IF_BUTTON6 = 168;
    public static int IF_BUTTON7 = 166;
    public static int IF_BUTTON8 = 64;
    public static int IF_BUTTON9 = 53;
    public static int IF_BUTTON10 = 9;
    public static int CLOSE_MODAL = 184;

    // Movement
    public static int MOVE_GAMECLICK = 215;
    public static int MOVE_MINIMAPCLICK = 39;
    public static int MOVE_SCRIPTED = 77;

    // Player actions
    public static int OPPLAYER1 = 68;
    public static int OPPLAYER2 = 180;
    public static int OPPLAYER3 = 4;
    public static int OPPLAYER4 = 133;
    public static int OPPLAYER5 = 175;
    public static int OPPLAYER6 = 71;
    public static int OPPLAYER7 = 114;
    public static int OPPLAYERT = 148;

    // NPC actions
    public static int OPNPC1 = 78;
    public static int OPNPC2 = 156;
    public static int OPNPC3 = 84;
    public static int OPNPC4 = 30;
    public static int OPNPC5 = 253;
    public static int OPNPCT = 239;

    // Location actions
    public static int OPLOC1 = 254;
    public static int OPLOC2 = 134;
    public static int OPLOC3 = 84;
    public static int OPLOC4 = 247;
    public static int OPLOC5 = 170;
    public static int OPLOC6 = 94;
    public static int OPLOCT = 233;

    // Ground item actions
    public static int OPOBJSTACK1 = 66;
    public static int OPOBJSTACK2 = 48;
    public static int OPOBJSTACK3 = 109;
    public static int OPOBJSTACK4 = 101;
    public static int OPOBJSTACK5 = 195;
    public static int OPOBJSTACKT = 218;

    // Inventory item actions
    public static int OPOBJ1 = 72;
    public static int OPOBJ2 = 228;
    public static int OPOBJ3 = 85;
    public static int OPOBJ4 = 161;
    public static int OPOBJ5 = 135;
    public static int OPOBJ_EQUIP = 55;
    public static int OPOBJ_EXAMINE = 92;
    public static int OPOBJ_ON_OBJ = 27;
    public static int OPOBJ_ON_GROUND = 206;
    public static int OPOBJT = 131;

    // Component/Item in interface actions
    public static int OPHELDT = 79;
    public static int IF_ON_COMPONENT = 23;
    public static int IF_ON_COMPONENT2 = 244;
    public static int IF_ON_COMPONENT3 = 65;
    public static int IF_ON_COMPONENT4 = 111;
    public static int IF_ON_COMPONENT5 = 6;

    // Social
    public static int MESSAGE_PUBLIC = 237;
    public static int MESSAGE_PRIVATE = 201;
    public static int FRIENDLIST_ADD = 120;
    public static int FRIENDLIST_DEL = 57;
    public static int FRIEND_SETRANK = 188;
    public static int IGNORELIST_ADD = 34;
    public static int IGNORELIST_DEL = 213;
    public static int REPORT_ABUSE = 99;
    public static int CLANCHAT_JOINCHANNEL = 104;
    public static int CLANCHAT_LEAVECHANNEL = 162;

    // Audio
    public static int SOUND_SONGEND = 137;

    // Client events
    public static int EVENT_MOUSE_MOVE = 123;
    public static int EVENT_MOUSE_CLICK = 75;
    public static int EVENT_CAMERA_POSITION = 21;
    public static int EVENT_APPLET_FOCUS = 22;
    public static int WINDOW_STATUS = 243;

    // Game actions
    public static int LOGOUT = 10;
    public static int RESUME_PAUSEBUTTON = 115;
    public static int RESUME_OBJDIALOG = 82;
    public static int RESUME_COUNTDIALOG = 73;
    public static int RESUME_NAMEDIALOG = 194;
    public static int RESUME_STRINGDIALOG = 154;
    public static int RESUME_P_COUNTDIALOG = 153;

    // World/Map
    public static int TELEPORT = 248;
    public static int MAP_BUILD_COMPLETE = 110;

    // ClientScript triggered
    public static int CLIENTSCRIPT_TRIGGERED = 157;
    public static int CLIENTSCRIPT_167 = 167;
    public static int CLIENTSCRIPT_178 = 178;
    public static int CLIENTSCRIPT_117 = 117;

    // Login/Session
    public static int LOGIN_TIMINGS = 17;
    public static int IDLE_LOGOUT = 98;
    public static int REFLECTION_CHECK_REPLY = 231;


    @OriginalMember(owner = "client!ej", name = "i", descriptor = "(I)V")
    public static void sendWindowStatus() {
        Protocol.outboundBuffer.pIsaac1(WINDOW_STATUS);
        Protocol.outboundBuffer.p1(DisplayMode.getWindowMode());
        Protocol.outboundBuffer.p2(GameShell.canvasWidth);
        Protocol.outboundBuffer.p2(GameShell.canvasHeigth);
        Protocol.outboundBuffer.p1(Preferences.antiAliasingMode);
    }

    @OriginalMember(owner = "client!vg", name = "a", descriptor = "(Lclient!na;IIBI)V")
    public static void method4512(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int type, @OriginalArg(4) int arg3) {
        @Pc(8) Component local8 = ComponentList.getCreatedComponent(arg3, arg1);
        if (local8 == null) {
            return;
        }
        if (local8.onOptionClick != null) {
            @Pc(19) ComponentEvent local19 = new ComponentEvent();
            local19.arguments = local8.onOptionClick;
            local19.source = local8;
            local19.opBase = arg0;
            local19.op = type;
            ClientScriptRunner.run(local19);
        }
        @Pc(37) boolean local37 = true;
        if (local8.contentType > 0) {
            local37 = MiniMenu.shouldTriggerIdleTimeout(local8);
        }
        if (!local37 || !ComponentList.getServerActiveProperties(local8).isButtonEnabled(type - 1)) {
            return;
        }
        if (type == 1) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON1);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 2) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON2);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 3) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON3);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 4) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON4);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 5) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON5);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 6) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON6);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 7) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON7);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 8) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON8);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 9) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON9);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (type == 10) {
            Protocol.outboundBuffer.pIsaac1(IF_BUTTON10);
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
            Protocol.outboundBuffer.pIsaac1(MOVE_GAMECLICK);
            Protocol.outboundBuffer.p1(bufferSize + bufferSize + 3);
        }
        if (type == 1) {
            Protocol.outboundBuffer.pIsaac1(MOVE_MINIMAPCLICK);
            Protocol.outboundBuffer.p1(bufferSize + bufferSize + 3 + 14);
        }
        if (type == 2) {
            Protocol.outboundBuffer.pIsaac1(MOVE_SCRIPTED);
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
                    Protocol.outboundBuffer.pIsaac1(OPPLAYER1);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                } else if (type == 4) {
                    Protocol.outboundBuffer.pIsaac1(OPPLAYER4);
                    Protocol.outboundBuffer.p2_alt3(PlayerList.playerIds[local15]);
                } else if (type == 5) {
                    Protocol.outboundBuffer.pIsaac1(OPPLAYER5);
                    Protocol.outboundBuffer.p2_alt1(PlayerList.playerIds[local15]);
                } else if (type == 6) {
                    Protocol.outboundBuffer.pIsaac1(OPPLAYER6);
                    Protocol.outboundBuffer.p2_alt1(PlayerList.playerIds[local15]);
                } else if (type == 7) {
                    Protocol.outboundBuffer.pIsaac1(OPPLAYER7);
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
        Protocol.noTimeoutCycle++;
        if (Protocol.noTimeoutCycle < 50 && !arg0) {
            return;
        }
        Protocol.noTimeoutCycle = 0;
        if (!LoginManager.aBoolean247 && Protocol.gameServerSocket != null) {
            Protocol.outboundBuffer.pIsaac1(NO_TIMEOUT);
            try {
                Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                Protocol.outboundBuffer.offset = 0;
            } catch (@Pc(53) IOException ioException) {
                LoginManager.aBoolean247 = true;
            }
        }
        Client.audioLoop();
    }
}
