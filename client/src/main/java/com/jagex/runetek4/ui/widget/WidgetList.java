package com.jagex.runetek4.ui.widget;

import com.jagex.runetek4.*;
import com.jagex.runetek4.data.cache.CacheArchive;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.ui.chat.ClanChat;
import com.jagex.runetek4.client.LoginManager;
import com.jagex.runetek4.config.types.seq.SeqType;
import com.jagex.runetek4.data.cache.media.SoftwareSprite;
import com.jagex.runetek4.data.cache.media.component.Widget;
import com.jagex.runetek4.client.GameShell;
import com.jagex.runetek4.client.Preferences;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.cursor.CursorTypeList;
import com.jagex.runetek4.config.types.seq.SeqTypeList;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.HashTableIterator;
import com.jagex.runetek4.core.datastruct.LinkedList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.types.cursor.CursorType;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.game.inventory.Inv;
import com.jagex.runetek4.game.state.VarcDomain;
import com.jagex.runetek4.game.state.VarpDomain;
import com.jagex.runetek4.graphics.gl.GlRaster;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.input.MouseWheel;
import com.jagex.runetek4.data.js5.Js5;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.graphics.lighting.FogManager;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.core.node.Node;
import com.jagex.runetek4.graphics.raster.SoftwareRaster;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.social.FriendList;
import com.jagex.runetek4.game.stockmarket.StockMarketManager;
import com.jagex.runetek4.ui.events.WidgetEvent;
import com.jagex.runetek4.util.debug.Cheat;
import com.jagex.runetek4.game.world.WorldMap;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

public class WidgetList {
    @OriginalMember(owner = "runetek4.client!sg", name = "q", descriptor = "[I")
    public static final int[] keyCodes = new int[128];
    @OriginalMember(owner = "runetek4.client!pa", name = "R", descriptor = "[Z")
    public static final boolean[] widgetNeedsRedraw = new boolean[100];
    @OriginalMember(owner = "client!ch", name = "y", descriptor = "[Z")
    public static final boolean[] rectangleRedraw = new boolean[100];
    @OriginalMember(owner = "runetek4.client!sd", name = "V", descriptor = "[I")
    public static final int[] rectangleWidth = new int[100];
    @OriginalMember(owner = "runetek4.client!vh", name = "b", descriptor = "[I")
    public static final int[] rectangleX = new int[100];
    @OriginalMember(owner = "runetek4.client!fe", name = "lb", descriptor = "[I")
    public static final int[] rectangleHeight = new int[100];
    @OriginalMember(owner = "runetek4.client!e", name = "sc", descriptor = "[I")
    public static final int[] rectangleY = new int[100];
    @OriginalMember(owner = "runetek4.client!qj", name = "i", descriptor = "[I")
    public static final int[] keyChars = new int[128];
    @OriginalMember(owner = "runetek4.client!ac", name = "i", descriptor = "Lclient!ih;")
    public static final LinkedList highPriorityRequests = new LinkedList();
    @OriginalMember(owner = "runetek4.client!ja", name = "f", descriptor = "Lclient!ih;")
    public static final LinkedList mediumPriorityRequests = new LinkedList();
    @OriginalMember(owner = "runetek4.client!p", name = "c", descriptor = "Lclient!ih;")
    public static final LinkedList lowPriorityRequests = new LinkedList();
    @OriginalMember(owner = "runetek4.client!rb", name = "b", descriptor = "Lclient!sc;")
    public static final HashTable properties = new HashTable(512);
    @OriginalMember(owner = "runetek4.client!ql", name = "h", descriptor = "Lclient!na;")
    public static final JString aClass100_903 = JString.parse("Hidden)2");
    @OriginalMember(owner = "runetek4.client!sc", name = "z", descriptor = "[Z")
    public static final boolean[] widgetRedrawPrevious = new boolean[100];
    @OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
    public static int transmitTimer = 1;
    @OriginalMember(owner = "runetek4.client!md", name = "W", descriptor = "I")
    public static int topLevelInterface = -1;
    @OriginalMember(owner = "client!je", name = "T", descriptor = "Lclient!sc;")
    public static HashTable openInterfaces = new HashTable(8);
    @OriginalMember(owner = "runetek4.client!ve", name = "w", descriptor = "Z")
    public static boolean aBoolean298 = false;
    @OriginalMember(owner = "client!bn", name = "V", descriptor = "I")
    public static int rectangles = 0;
    @OriginalMember(owner = "runetek4.client!oj", name = "y", descriptor = "I")
    public static int keyQueueSize = 0;
    @OriginalMember(owner = "client!bm", name = "f", descriptor = "Lclient!ve;")
    public static Js5 gameImageJs5;
    @OriginalMember(owner = "runetek4.client!lg", name = "b", descriptor = "Z")
    public static boolean aBoolean174 = false;
    @OriginalMember(owner = "client!sh", name = "f", descriptor = "I")
    public static int anInt5103 = -1;
    @OriginalMember(owner = "runetek4.client!wl", name = "h", descriptor = "Lclient!be;")
    public static Widget aClass13_26 = null;
    @OriginalMember(owner = "runetek4.client!rg", name = "s", descriptor = "I")
    public static int anInt5574 = -1;
    @OriginalMember(owner = "runetek4.client!og", name = "e", descriptor = "Lclient!be;")
    public static Widget aClass13_22;
    @OriginalMember(owner = "runetek4.client!gd", name = "j", descriptor = "I")
    public static int lastItemDragTime = 0;
    @OriginalMember(owner = "runetek4.client!qk", name = "f", descriptor = "I")
    public static int clickedInventoryComponentY = 0;
    @OriginalMember(owner = "runetek4.client!nd", name = "v", descriptor = "Lclient!ve;")
    public static Js5 aClass153_64;
    @OriginalMember(owner = "runetek4.client!qg", name = "ab", descriptor = "Lclient!ve;")
    public static Js5 gameInterfaceJs5;
    @OriginalMember(owner = "runetek4.client!qh", name = "g", descriptor = "Lclient!ve;")
    public static Js5 aClass153_85;
    @OriginalMember(owner = "runetek4.client!th", name = "j", descriptor = "[[Lclient!be;")
    public static Widget[][] cachedWidgets;
    @OriginalMember(owner = "runetek4.client!sc", name = "m", descriptor = "[Z")
    public static boolean[] loadedComponents;
    @OriginalMember(owner = "runetek4.client!ra", name = "J", descriptor = "I")
    public static int miscTransmitAt = 0;
    @OriginalMember(owner = "runetek4.client!jd", name = "i", descriptor = "Lclient!be;")
    public static Widget clickedInventoryWidget;
    @OriginalMember(owner = "runetek4.client!nf", name = "h", descriptor = "Lclient!be;")
    public static Widget mouseOverInventoryInterface;
    @OriginalMember(owner = "client!ef", name = "r", descriptor = "Lclient!be;")
    public static Widget aClass13_12 = null;
    @OriginalMember(owner = "client!bn", name = "O", descriptor = "I")
    public static int anInt761;
    @OriginalMember(owner = "client!bc", name = "X", descriptor = "I")
    public static int anInt436;
    @OriginalMember(owner = "runetek4.client!si", name = "ab", descriptor = "I")
    public static int anInt5138;
    @OriginalMember(owner = "runetek4.client!lj", name = "w", descriptor = "I")
    public static int clickedInventoryComponentX = 0;
    @OriginalMember(owner = "runetek4.client!ok", name = "b", descriptor = "I")
    public static int anInt4271;
    @OriginalMember(owner = "runetek4.client!ac", name = "n", descriptor = "I")
    public static int selectedInventorySlot = 0;
    @OriginalMember(owner = "runetek4.client!jj", name = "j", descriptor = "Z")
    public static boolean draggingClickedInventoryObject = false;
    @OriginalMember(owner = "runetek4.client!oj", name = "v", descriptor = "I")
    public static int anInt4311 = -2;
    @OriginalMember(owner = "runetek4.client!pm", name = "hb", descriptor = "I")
    public static int anInt4620;
    @OriginalMember(owner = "client!fk", name = "e", descriptor = "I")
    public static int anInt1885;
    @OriginalMember(owner = "client!bj", name = "s", descriptor = "I")
    public static int anInt660 = -1;
    @OriginalMember(owner = "runetek4.client!kl", name = "s", descriptor = "I")
    public static int anInt3337 = 0;
    @OriginalMember(owner = "runetek4.client!jk", name = "p", descriptor = "I")
    public static int anInt3075 = -1;
    @OriginalMember(owner = "client!df", name = "n", descriptor = "I")
    public static int anInt1396 = 0;
    @OriginalMember(owner = "client!di", name = "H", descriptor = "Z")
    public static boolean aBoolean84 = false;
    @OriginalMember(owner = "client!dh", name = "a", descriptor = "Z")
    public static boolean aBoolean83 = false;
    @OriginalMember(owner = "client!ja", name = "r", descriptor = "I")
    public static int currentCursor = -1;
    @OriginalMember(owner = "runetek4.client!a", name = "h", descriptor = "I")
    public static int anInt5;

    @OriginalMember(owner = "client!gg", name = "c", descriptor = "(II)V")
    public static void method1750(@OriginalArg(0) int arg0) {
        if (!Preferences.cursorsEnabled) {
            arg0 = -1;
        }

        if (arg0 == currentCursor) {
            return;
        }

        if (arg0 != -1) {
            @Pc(24) CursorType cursorType = CursorTypeList.get(arg0);
            @Pc(28) SoftwareSprite local28 = cursorType.getSprite();
            if (local28 == null) {
                arg0 = -1;
            } else {
                GameShell.signLink.setCursor(local28.method301(), local28.innerWidth, GameShell.canvas, new Point(cursorType.hotspotx, cursorType.hotspoty), local28.innerHeight);
                currentCursor = arg0;
            }
        }
        if (arg0 == -1 && currentCursor != -1) {
            GameShell.signLink.setCursor(null, -1, GameShell.canvas, new Point(), -1);
            currentCursor = -1;
        }
    }

    @OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ZI)V")
    public static void method1596(@OriginalArg(0) boolean arg0) {
        if (arg0) {
            if (topLevelInterface != -1) {
                resetComponent(topLevelInterface);
            }
            for (@Pc(18) SubInterface local18 = (SubInterface) openInterfaces.head(); local18 != null; local18 = (SubInterface) openInterfaces.next()) {
                closeInterface(true, local18);
            }
            topLevelInterface = -1;
            openInterfaces = new HashTable(8);
            createComponentMemoryBuffer();
            topLevelInterface = LoginManager.loginScreenId;
            method3712(false);
            ClientScriptRunner.method1807();
            method1626(topLevelInterface);
        }
        MiniMenu.defaultCursor = -1;
        method1750(ClientScriptRunner.anInt5794);
        PlayerList.self = new Player();
        PlayerList.self.zFine = 3000;
        PlayerList.self.xFine = 3000;
        if (!GlRenderer.enabled) {
            Flames.load(Client.js5Archive8);
            Client.processGameStatus(10);
            return;
        }
        if (Camera.cameraType == 2) {
            Camera.renderX = Camera.targetTileZ << 7;
            Camera.renderZ = Camera.targetTileX << 7;
        } else {
            Camera.updateLoginScreenCamera();
        }
        FogManager.setInstantFade();
        LoginManager.setupLoadingScreenRegion();
        Client.processGameStatus(28);
    }

    @OriginalMember(owner = "runetek4.client!ig", name = "a", descriptor = "(BI)V")
    public static void resetComponent(@OriginalArg(1) int componentId) {
        if (componentId == -1 || !loadedComponents[componentId]) {
            return;
        }
        gameInterfaceJs5.discardUnpacked(componentId);
        if (cachedWidgets[componentId] == null) {
            return;
        }
        @Pc(27) boolean deleteFromCache = true;
        for (@Pc(29) int i = 0; i < cachedWidgets[componentId].length; i++) {
            if (cachedWidgets[componentId][i] != null) {
                if (cachedWidgets[componentId][i].type == 2) {
                    deleteFromCache = false;
                } else {
                    cachedWidgets[componentId][i] = null;
                }
            }
        }
        if (deleteFromCache) {
            cachedWidgets[componentId] = null;
        }
        loadedComponents[componentId] = false;
    }

    @OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(ZLclient!wk;Z)V")
    public static void closeInterface(@OriginalArg(0) boolean arg0, @OriginalArg(1) SubInterface arg1) {
        @Pc(9) int local9 = (int) arg1.nodeId;
        @Pc(16) int local16 = arg1.interfaceId;
        arg1.unlink();
        if (arg0) {
            resetComponent(local16);
        }
        method3214(local16);
        @Pc(32) Widget local32 = getComponent(local9);
        if (local32 != null) {
            redraw(local32);
        }
        @Pc(41) int local41 = MiniMenu.menuActionRow;
        @Pc(43) int local43;
        for (local43 = 0; local43 < local41; local43++) {
            if (method5(MiniMenu.actions[local43])) {
                MiniMenu.remove(local43);
            }
        }
        if (MiniMenu.menuActionRow == 1) {
            ClientScriptRunner.menuVisible = false;
            redrawScreen(anInt4271, anInt761, anInt5138, anInt436);
        } else {
            redrawScreen(anInt4271, anInt761, anInt5138, anInt436);
            local43 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(75) int local75 = 0; local75 < MiniMenu.menuActionRow; local75++) {
                @Pc(88) int local88 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(local75));
                if (local43 < local88) {
                    local43 = local88;
                }
            }
            anInt436 = MiniMenu.menuActionRow * 15 + (aBoolean298 ? 26 : 22);
            anInt761 = local43 + 8;
        }
        if (topLevelInterface != -1) {
            runScripts(1, topLevelInterface);
        }
    }

    @OriginalMember(owner = "runetek4.client!eb", name = "d", descriptor = "(I)V")
    public static void createComponentMemoryBuffer() {
        cachedWidgets = new Widget[gameInterfaceJs5.capacity()][];
        loadedComponents = new boolean[gameInterfaceJs5.capacity()];
    }

    @OriginalMember(owner = "runetek4.client!jm", name = "a", descriptor = "(Z)V")
    public static void method2460() {
        if (topLevelInterface != -1) {
            method1949(topLevelInterface);
        }
        for (@Pc(15) int local15 = 0; local15 < rectangles; local15++) {
            if (widgetNeedsRedraw[local15]) {
                rectangleRedraw[local15] = true;
            }
            widgetRedrawPrevious[local15] = widgetNeedsRedraw[local15];
            widgetNeedsRedraw[local15] = false;
        }
        ClientScriptRunner.anInt2503 = -1;
        mouseOverInventoryInterface = null;
        anInt4311 = Client.loop;
        if (GlRenderer.enabled) {
            ClientScriptRunner.aBoolean299 = true;
        }
        anInt5574 = -1;
        if (topLevelInterface != -1) {
            rectangles = 0;
            CacheArchive.method182();
        }
        if (GlRenderer.enabled) {
            GlRaster.method1177();
        } else {
            SoftwareRaster.resetBounds();
        }
        Protocol.sceneDelta = 0;
    }

    @OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "(IIIIIIII)V")
    public static void method1320(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
        if (load(arg4)) {
            method946(cachedWidgets[arg4], -1, arg5, arg1, arg3, arg6, arg0, arg2);
        }
    }

    @OriginalMember(owner = "runetek4.client!af", name = "a", descriptor = "(BI)Lclient!be;")
    public static Widget getComponent(@OriginalArg(1) int id) {
        @Pc(7) int interfaceId = id >> 16;
        @Pc(18) int componentId = id & 0xFFFF;
        if (cachedWidgets[interfaceId] == null || cachedWidgets[interfaceId][componentId] == null) {
            @Pc(33) boolean success = load(interfaceId);
            if (!success) {
                return null;
            }
            // todo: this should not be necessary, data/server-related?
            if (cachedWidgets.length <= interfaceId || cachedWidgets[interfaceId].length <= componentId) {
                return null;
            }
        }
        return cachedWidgets[interfaceId][componentId];
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "b", descriptor = "(Lclient!be;)Lclient!bf;")
    public static ServerActiveProperties getServerActiveProperties(@OriginalArg(0) Widget arg0) {
        @Pc(13) ServerActiveProperties local13 = (ServerActiveProperties) properties.get(((long) arg0.id << 32) + (long) arg0.createdComponentId);
        return local13 == null ? arg0.properties : local13;
    }

    @OriginalMember(owner = "runetek4.client!dg", name = "a", descriptor = "(ILclient!be;)V")
    public static void redraw(@OriginalArg(1) Widget arg0) {
        if (anInt4311 == arg0.rectangleLoop) {
            widgetNeedsRedraw[arg0.rectangle] = true;
        }
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "c", descriptor = "(Lclient!be;)Z")
    public static boolean method947(@OriginalArg(0) Widget arg0) {
        if (Cheat.qaOpTest) {
            if (getServerActiveProperties(arg0).events != 0) {
                return false;
            }
            if (arg0.type == 0) {
                return false;
            }
        }
        return arg0.hidden;
    }

    @OriginalMember(owner = "runetek4.client!qf", name = "a", descriptor = "(BII)Lclient!be;")
    public static Widget getCreatedComponent(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        @Pc(7) Widget local7 = getComponent(arg0);
        if (arg1 == -1) {
            return local7;
        } else if (local7 == null || local7.createdWidgets == null || local7.createdWidgets.length <= arg1) {
            return null;
        } else {
            return local7.createdWidgets[arg1];
        }
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "a", descriptor = "(IIBII)V")
    public static void redrawScreen(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        for (@Pc(12) int local12 = 0; local12 < rectangles; local12++) {
            if (rectangleWidth[local12] + rectangleX[local12] > arg0 && arg1 + arg0 > rectangleX[local12] && arg2 < rectangleHeight[local12] + rectangleY[local12] && rectangleY[local12] < arg2 + arg3) {
                widgetNeedsRedraw[local12] = true;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(ZB)V")
    public static void method3712(@OriginalArg(0) boolean arg0) {
        method4017(GameShell.canvasHeigth, arg0, topLevelInterface, GameShell.canvasWidth);
    }

    @OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(ZLclient!ve;Lclient!ve;Lclient!ve;Lclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1, @OriginalArg(3) Js5 arg2, @OriginalArg(4) Js5 arg3) {
        gameImageJs5 = arg1;
        aClass153_64 = arg0;
        gameInterfaceJs5 = arg2;
        aClass153_85 = arg3;
        cachedWidgets = new Widget[gameInterfaceJs5.capacity()][];
        loadedComponents = new boolean[gameInterfaceJs5.capacity()];
    }

    @OriginalMember(owner = "runetek4.client!tm", name = "b", descriptor = "(II)Z")
    public static boolean load(@OriginalArg(0) int componentId) {
        if (loadedComponents[componentId]) {
            return true;
        } else if (gameInterfaceJs5.isGroupReady(componentId)) {
            @Pc(25) int gameInterfaceCount = gameInterfaceJs5.getGroupCapacity(componentId);
            if (gameInterfaceCount == 0) {
                loadedComponents[componentId] = true;
                return true;
            }
            if (cachedWidgets[componentId] == null) {
                cachedWidgets[componentId] = new Widget[gameInterfaceCount];
            }
            for (@Pc(46) int i = 0; i < gameInterfaceCount; i++) {
                if (cachedWidgets[componentId][i] == null) {
                    @Pc(62) byte[] interfaceFileData = gameInterfaceJs5.getfile(componentId, i);
                    if (interfaceFileData != null) {
                        @Pc(74) Widget local74 = cachedWidgets[componentId][i] = new Widget();
                        local74.id = i + (componentId << 16);
                        if (interfaceFileData[0] == -1) {
                            local74.decodeIf3(new Packet(interfaceFileData));
                        } else {
                            local74.decodeIf1(new Packet(interfaceFileData));
                        }
                    }
                }
            }
            loadedComponents[componentId] = true;
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!i", name = "i", descriptor = "(Z)V")
    public static void redrawActiveInterfaces() {
        for (@Pc(6) SubInterface local6 = (SubInterface) openInterfaces.head(); local6 != null; local6 = (SubInterface) openInterfaces.next()) {
            @Pc(14) int local14 = local6.interfaceId;
            if (load(local14)) {
                @Pc(21) boolean local21 = true;
                @Pc(25) Widget[] local25 = cachedWidgets[local14];
                @Pc(27) int local27;
                for (local27 = 0; local27 < local25.length; local27++) {
                    if (local25[local27] != null) {
                        local21 = local25[local27].if3;
                        break;
                    }
                }
                if (!local21) {
                    local27 = (int) local6.nodeId;
                    @Pc(60) Widget local60 = getComponent(local27);
                    if (local60 != null) {
                        redraw(local60);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!qj", name = "a", descriptor = "(Lclient!be;BI)Lclient!na;")
    public static JString getOp(@OriginalArg(0) Widget arg0, @OriginalArg(2) int arg1) {
        if (!getServerActiveProperties(arg0).isButtonEnabled(arg1) && arg0.onOptionClick == null) {
            return null;
        } else if (arg0.ops == null || arg0.ops.length <= arg1 || arg0.ops[arg1] == null || arg0.ops[arg1].trim().length() == 0) {
            return Cheat.qaOpTest ? JString.concatenate(new JString[] {aClass100_903, JString.parseInt(arg1) }) : null;
        } else {
            return arg0.ops[arg1];
        }
    }

    @OriginalMember(owner = "runetek4.client!gg", name = "e", descriptor = "(II)V")
    public static void method1753(@OriginalArg(0) int arg0) {
        if (!load(arg0)) {
            return;
        }
        @Pc(15) Widget[] local15 = cachedWidgets[arg0];
        for (@Pc(17) int local17 = 0; local17 < local15.length; local17++) {
            @Pc(29) Widget local29 = local15[local17];
            if (local29 != null) {
                local29.anInt496 = 1;
                local29.anInt510 = 0;
                local29.anInt500 = 0;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(IZIII)V")
    public static void method4017(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        if (load(arg2)) {
            method4190(-1, arg1, arg3, arg0, cachedWidgets[arg2]);
        }
    }

    @OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(IZIII[Lclient!be;)V")
    public static void method4190(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) Widget[] arg4) {
        for (@Pc(3) int local3 = 0; local3 < arg4.length; local3++) {
            @Pc(19) Widget local19 = arg4[local3];
            if (local19 != null && local19.overlayer == arg0) {
                method2801(arg3, arg2, local19, arg1);
                method2291(local19, arg3, arg2);
                if (local19.scrollMaxH - local19.width < local19.scrollX) {
                    local19.scrollX = local19.scrollMaxH - local19.width;
                }
                if (local19.scrollY > local19.scrollMaxV - local19.height) {
                    local19.scrollY = local19.scrollMaxV - local19.height;
                }
                if (local19.scrollY < 0) {
                    local19.scrollY = 0;
                }
                if (local19.scrollX < 0) {
                    local19.scrollX = 0;
                }
                if (local19.type == 0) {
                    method531(local19, arg1);
                }
            }
        }
    }

    @OriginalMember(owner = "client!bg", name = "a", descriptor = "(Lclient!be;ZI)V")
    public static void method531(@OriginalArg(0) Widget arg0, @OriginalArg(1) boolean arg1) {
        @Pc(20) int local20 = arg0.scrollMaxH == 0 ? arg0.width : arg0.scrollMaxH;
        @Pc(32) int local32 = arg0.scrollMaxV == 0 ? arg0.height : arg0.scrollMaxV;
        method4190(arg0.id, arg1, local20, local32, cachedWidgets[arg0.id >> 16]);
        if (arg0.createdWidgets != null) {
            method4190(arg0.id, arg1, local20, local32, arg0.createdWidgets);
        }
        @Pc(66) SubInterface local66 = (SubInterface) openInterfaces.get((long) arg0.id);
        if (local66 != null) {
            method4017(local32, arg1, local66.interfaceId, local20);
        }
    }

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIILclient!be;Z)V")
    public static void method2801(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Widget arg2, @OriginalArg(4) boolean arg3) {
        @Pc(4) int local4 = arg2.width;
        @Pc(7) int local7 = arg2.height;
        if (arg2.dynamicWidthValue == 0) {
            arg2.width = arg2.baseWidth;
        } else if (arg2.dynamicWidthValue == 1) {
            arg2.width = arg1 - arg2.baseWidth;
        } else if (arg2.dynamicWidthValue == 2) {
            arg2.width = arg2.baseWidth * arg1 >> 14;
        } else if (arg2.dynamicWidthValue == 3) {
            if (arg2.type == 2) {
                arg2.width = arg2.baseWidth * 32 + (arg2.baseWidth - 1) * arg2.invMarginX;
            } else if (arg2.type == 7) {
                arg2.width = arg2.baseWidth * 115 + arg2.invMarginX * (arg2.baseWidth - 1);
            }
        }
        if (arg2.dynamicHeightValue == 0) {
            arg2.height = arg2.baseHeight;
        } else if (arg2.dynamicHeightValue == 1) {
            arg2.height = arg0 - arg2.baseHeight;
        } else if (arg2.dynamicHeightValue == 2) {
            arg2.height = arg0 * arg2.baseHeight >> 14;
        } else if (arg2.dynamicHeightValue == 3) {
            if (arg2.type == 2) {
                arg2.height = (arg2.baseHeight - 1) * arg2.invMarginY + arg2.baseHeight * 32;
            } else if (arg2.type == 7) {
                arg2.height = arg2.baseHeight * 12 + (arg2.baseHeight - 1) * arg2.invMarginY;
            }
        }
        if (arg2.dynamicWidthValue == 4) {
            arg2.width = arg2.aspectWidth * arg2.height / arg2.aspectHeight;
        }
        if (arg2.dynamicHeightValue == 4) {
            arg2.height = arg2.aspectHeight * arg2.width / arg2.aspectWidth;
        }
        if (Cheat.qaOpTest && (getServerActiveProperties(arg2).events != 0 || arg2.type == 0)) {
            if (arg2.height < 5 && arg2.width < 5) {
                arg2.height = 5;
                arg2.width = 5;
            } else {
                if (arg2.width <= 0) {
                    arg2.width = 5;
                }
                if (arg2.height <= 0) {
                    arg2.height = 5;
                }
            }
        }
        if (arg2.contentType == 1337) {
            aClass13_26 = arg2;
        }
        if (arg3 && arg2.onResize != null && (local4 != arg2.width || arg2.height != local7)) {
            @Pc(305) WidgetEvent local305 = new WidgetEvent();
            local305.arguments = arg2.onResize;
            local305.source = arg2;
            lowPriorityRequests.addTail(local305);
        }
    }

    @OriginalMember(owner = "runetek4.client!ii", name = "a", descriptor = "(Lclient!be;III)V")
    public static void method2291(@OriginalArg(0) Widget arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
        if (arg0.xMode == 0) {
            arg0.y = arg0.baseY;
        } else if (arg0.xMode == 1) {
            arg0.y = (arg1 - arg0.height) / 2 + arg0.baseY;
        } else if (arg0.xMode == 2) {
            arg0.y = arg1 - arg0.height - arg0.baseY;
        } else if (arg0.xMode == 3) {
            arg0.y = arg0.baseY * arg1 >> 14;
        } else if (arg0.xMode == 4) {
            arg0.y = (arg1 * arg0.baseY >> 14) + (arg1 - arg0.height) / 2;
        } else {
            arg0.y = arg1 - (arg1 * arg0.baseY >> 14) - arg0.height;
        }
        if (arg0.yMode == 0) {
            arg0.x = arg0.baseX;
        } else if (arg0.yMode == 1) {
            arg0.x = arg0.baseX + (arg2 - arg0.width) / 2;
        } else if (arg0.yMode == 2) {
            arg0.x = arg2 - arg0.baseX - arg0.width;
        } else if (arg0.yMode == 3) {
            arg0.x = arg0.baseX * arg2 >> 14;
        } else if (arg0.yMode == 4) {
            arg0.x = (arg0.baseX * arg2 >> 14) + (arg2 - arg0.width) / 2;
        } else {
            arg0.x = arg2 - (arg2 * arg0.baseX >> 14) - arg0.width;
        }
        if (!Cheat.qaOpTest || getServerActiveProperties(arg0).events == 0 && arg0.type != 0) {
            return;
        }
        if (arg0.y < 0) {
            arg0.y = 0;
        } else if (arg0.height + arg0.y > arg1) {
            arg0.y = arg1 - arg0.height;
        }
        if (arg0.x < 0) {
            arg0.x = 0;
        } else if (arg2 < arg0.x + arg0.width) {
            arg0.x = arg2 - arg0.width;
        }
    }

    @OriginalMember(owner = "client!fn", name = "c", descriptor = "(II)V")
    public static void method1626(@OriginalArg(0) int arg0) {
        if (arg0 == -1 || !load(arg0)) {
            return;
        }
        @Pc(31) Widget[] local31 = cachedWidgets[arg0];
        for (@Pc(33) int local33 = 0; local33 < local31.length; local33++) {
            @Pc(41) Widget local41 = local31[local33];
            if (local41.anObjectArray3 != null) {
                @Pc(50) WidgetEvent local50 = new WidgetEvent();
                local50.arguments = local41.anObjectArray3;
                local50.source = local41;
                ClientScriptRunner.run(2000000, local50);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(SI)Z")
    public static boolean method5(@OriginalArg(0) short arg0) {
        if (arg0 == 47 || arg0 == 5 || arg0 == 43 || arg0 == 35 || arg0 == 58 || arg0 == 22 || arg0 == 40 || arg0 == 3) {
            return true;
        } else if (arg0 == 9 || arg0 == 12 || arg0 == 1006 || arg0 == 1003) {
            return true;
        } else if (arg0 == 25 || arg0 == 23 || arg0 == 48 || arg0 == 7 || arg0 == 13) {
            return true;
        } else {
            return arg0 == 8 || arg0 == 32 || arg0 == 28 || arg0 == 59 || arg0 == 51 || arg0 == 41;
        }
    }

    @OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(BI)V")
    public static void method3214(@OriginalArg(1) int arg0) {
        for (@Pc(11) Node local11 = properties.head(); local11 != null; local11 = properties.next()) {
            if ((local11.nodeId >> 48 & 0xFFFFL) == (long) arg0) {
                local11.unlink();
            }
        }
    }

    @OriginalMember(owner = "client!ed", name = "a", descriptor = "(III)V")
    public static void runScripts(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        if (load(arg1)) {
            method7(cachedWidgets[arg1], arg0);
        }
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(Lclient!be;I)Lclient!be;")
    public static Widget method4668(@OriginalArg(0) Widget arg0) {
        if (arg0.overlayer != -1) {
            return getComponent(arg0.overlayer);
        }
        @Pc(28) int local28 = arg0.id >>> 16;
        @Pc(33) HashTableIterator local33 = new HashTableIterator(openInterfaces);
        for (@Pc(38) SubInterface local38 = (SubInterface) local33.method2701(); local38 != null; local38 = (SubInterface) local33.method2700()) {
            if (local28 == local38.interfaceId) {
                return getComponent((int) local38.nodeId);
            }
        }
        return null;
    }

    @OriginalMember(owner = "client!fn", name = "a", descriptor = "(ILclient!be;)V")
    public static void update(@OriginalArg(1) Widget arg0) {
        @Pc(7) Widget local7 = method4668(arg0);
        @Pc(19) int local19;
        @Pc(17) int local17;
        if (local7 == null) {
            local17 = GameShell.canvasHeigth;
            local19 = GameShell.canvasWidth;
        } else {
            local17 = local7.height;
            local19 = local7.width;
        }
        method2801(local17, local19, arg0, false);
        method2291(arg0, local17, local19);
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "([Lclient!be;ZI)V")
    public static void method7(@OriginalArg(0) Widget[] arg0, @OriginalArg(2) int arg1) {
        for (@Pc(11) int local11 = 0; local11 < arg0.length; local11++) {
            @Pc(23) Widget local23 = arg0[local11];
            if (local23 != null) {
                if (local23.type == 0) {
                    if (local23.createdWidgets != null) {
                        method7(local23.createdWidgets, arg1);
                    }
                    @Pc(49) SubInterface local49 = (SubInterface) openInterfaces.get((long) local23.id);
                    if (local49 != null) {
                        runScripts(arg1, local49.interfaceId);
                    }
                }
                @Pc(72) WidgetEvent local72;
                if (arg1 == 0 && local23.onDialogAbort != null) {
                    local72 = new WidgetEvent();
                    local72.arguments = local23.onDialogAbort;
                    local72.source = local23;
                    ClientScriptRunner.run(local72);
                }
                if (arg1 == 1 && local23.onWidgetsOpenClose != null) {
                    if (local23.createdComponentId >= 0) {
                        @Pc(103) Widget local103 = getComponent(local23.id);
                        if (local103 == null || local103.createdWidgets == null || local23.createdComponentId >= local103.createdWidgets.length || local103.createdWidgets[local23.createdComponentId] != local23) {
                            continue;
                        }
                    }
                    local72 = new WidgetEvent();
                    local72.arguments = local23.onWidgetsOpenClose;
                    local72.source = local23;
                    ClientScriptRunner.run(local72);
                }
            }
        }
    }

    @OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "([Lclient!be;IIIIIII)V")
    public static void method946(@OriginalArg(0) Widget[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
        for (@Pc(1) int local1 = 0; local1 < arg0.length; local1++) {
            @Pc(9) Widget widget = arg0[local1];
            if (widget != null && widget.overlayer == arg1 && (!widget.if3 || widget.type == 0 || widget.aBoolean25 || getServerActiveProperties(widget).events != 0 || widget == ClientScriptRunner.aClass13_1 || widget.contentType == 1338) && (!widget.if3 || !method947(widget))) {
                @Pc(50) int local50 = widget.x + arg6;
                @Pc(55) int local55 = widget.y + arg7;
                @Pc(61) int local61;
                @Pc(63) int local63;
                @Pc(65) int local65;
                @Pc(67) int local67;
                if (widget.type == 2) {
                    local61 = arg2;
                    local63 = arg3;
                    local65 = arg4;
                    local67 = arg5;
                } else {
                    @Pc(73) int local73 = local50 + widget.width;
                    @Pc(78) int local78 = local55 + widget.height;
                    if (widget.type == 9) {
                        local73++;
                        local78++;
                    }
                    local61 = local50 > arg2 ? local50 : arg2;
                    local63 = local55 > arg3 ? local55 : arg3;
                    local65 = local73 < arg4 ? local73 : arg4;
                    local67 = local78 < arg5 ? local78 : arg5;
                }
                if (widget == ClientScriptRunner.aClass13_14) {
                    aBoolean83 = true;
                    anInt3075 = local50;
                    anInt660 = local55;
                }
                if (!widget.if3 || local61 < local65 && local63 < local67) {
                    if (widget.type == 0) {
                        if (!widget.if3 && method947(widget) && aClass13_22 != widget) {
                            continue;
                        }
                        if (widget.noClickThrough && Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
                            for (@Pc(164) WidgetEvent local164 = (WidgetEvent) lowPriorityRequests.head(); local164 != null; local164 = (WidgetEvent) lowPriorityRequests.next()) {
                                if (local164.aBoolean158) {
                                    local164.unlink();
                                    local164.source.aBoolean19 = false;
                                }
                            }
                            if (ClientScriptRunner.anInt4851 == 0) {
                                ClientScriptRunner.aClass13_14 = null;
                                ClientScriptRunner.aClass13_1 = null;
                            }
                            anInt3337 = 0;
                        }
                    }
                    if (widget.if3) {
                        @Pc(207) boolean local207;
                        if (Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
                            local207 = true;
                        } else {
                            local207 = false;
                        }
                        @Pc(212) boolean local212 = false;
                        if (Mouse.pressedButton == 1 && local207) {
                            local212 = true;
                        }
                        @Pc(221) boolean local221 = false;
                        if (Mouse.clickButton == 1 && Mouse.mouseClickX >= local61 && Mouse.mouseClickY >= local63 && Mouse.mouseClickX < local65 && Mouse.mouseClickY < local67) {
                            local221 = true;
                        }
                        @Pc(243) int i;
                        @Pc(322) int k;
                        if (widget.aByteArray8 != null) {
                            for (i = 0; i < widget.aByteArray8.length; i++) {
                                if (Keyboard.pressedKeys[widget.aByteArray8[i]]) {
                                    if (widget.anIntArray49 == null || Client.loop >= widget.anIntArray49[i]) {
                                        @Pc(279) byte local279 = widget.aByteArray7[i];
                                        if (local279 == 0 || ((local279 & 0x2) == 0 || Keyboard.pressedKeys[86]) && ((local279 & 0x1) == 0 || Keyboard.pressedKeys[82]) && ((local279 & 0x4) == 0 || Keyboard.pressedKeys[81])) {
                                            ClientProt.method4512(JString.EMPTY, -1, i + 1, widget.id);
                                            k = widget.anIntArray46[i];
                                            if (widget.anIntArray49 == null) {
                                                widget.anIntArray49 = new int[widget.aByteArray8.length];
                                            }
                                            if (k == 0) {
                                                widget.anIntArray49[i] = Integer.MAX_VALUE;
                                            } else {
                                                widget.anIntArray49[i] = Client.loop + k;
                                            }
                                        }
                                    }
                                } else if (widget.anIntArray49 != null) {
                                    widget.anIntArray49[i] = 0;
                                }
                            }
                        }
                        if (local221) {
                            ClientScriptRunner.method1015(Mouse.mouseClickY - local55, Mouse.mouseClickX - local50, widget);
                        }
                        if (ClientScriptRunner.aClass13_14 != null && ClientScriptRunner.aClass13_14 != widget && local207 && getServerActiveProperties(widget).isDragTarget()) {
                            aClass13_12 = widget;
                        }
                        if (widget == ClientScriptRunner.aClass13_1) {
                            aBoolean174 = true;
                            ClientScriptRunner.anInt2225 = local50;
                            anInt5103 = local55;
                        }
                        if (widget.aBoolean25 || widget.contentType != 0) {
                            @Pc(399) WidgetEvent request;
                            if (local207 && MouseWheel.wheelRotation != 0 && widget.onScroll != null) {
                                request = new WidgetEvent();
                                request.aBoolean158 = true;
                                request.source = widget;
                                request.mouseY = MouseWheel.wheelRotation;
                                request.arguments = widget.onScroll;
                                lowPriorityRequests.addTail(request);
                            }
                            if (ClientScriptRunner.aClass13_14 != null || clickedInventoryWidget != null || ClientScriptRunner.menuVisible || widget.contentType != 1400 && anInt3337 > 0) {
                                local221 = false;
                                local212 = false;
                                local207 = false;
                            }
                            @Pc(508) int skill;
                            if (widget.contentType != 0) {
                                if (widget.contentType == 1337) {
                                    aClass13_26 = widget;
                                    redraw(widget);
                                    continue;
                                }
                                if (widget.contentType == 1338) {
                                    if (local221) {
                                        anInt5 = Mouse.mouseClickX - local50;
                                        MiniMenu.anInt2878 = Mouse.mouseClickY - local55;
                                    }
                                    continue;
                                }
                                if (widget.contentType == 1400) {
                                    WorldMap.widget = widget;
                                    if (local221) {
                                        if (Keyboard.pressedKeys[82] && LoginManager.staffModLevel > 0) {
                                            i = (int) ((double) (Mouse.mouseClickX - local50 - widget.width / 2) * 2.0D / (double) WorldMap.zoom);
                                            skill = (int) ((double) (Mouse.mouseClickY - local55 - widget.height / 2) * 2.0D / (double) WorldMap.zoom);
                                            k = WorldMap.anInt435 + i;
                                            @Pc(516) int local516 = WorldMap.anInt919 + skill;
                                            @Pc(520) int local520 = k + WorldMap.originX;
                                            @Pc(528) int local528 = WorldMap.length + WorldMap.originZ - local516 - 1;
                                            Cheat.teleport(local520, local528, 0);
                                            closeModal();
                                            continue;
                                        }
                                        anInt3337 = 1;
                                        ClientScriptRunner.anInt5388 = Mouse.lastMouseX;
                                        ClientScriptRunner.anInt4035 = Mouse.lastMouseY;
                                        continue;
                                    }
                                    if (local212 && anInt3337 > 0) {
                                        if (anInt3337 == 1 && (ClientScriptRunner.anInt5388 != Mouse.lastMouseX || ClientScriptRunner.anInt4035 != Mouse.lastMouseY)) {
                                            anInt4620 = WorldMap.anInt435;
                                            anInt1885 = WorldMap.anInt919;
                                            anInt3337 = 2;
                                        }
                                        if (anInt3337 == 2) {
                                            WorldMap.method1964(anInt4620 + (int) ((double) (ClientScriptRunner.anInt5388 - Mouse.lastMouseX) * 2.0D / (double) WorldMap.targetZoom));
                                            WorldMap.method4641(anInt1885 + (int) ((double) (ClientScriptRunner.anInt4035 - Mouse.lastMouseY) * 2.0D / (double) WorldMap.targetZoom));
                                        }
                                        continue;
                                    }
                                    anInt3337 = 0;
                                    continue;
                                }
                                if (widget.contentType == 1401) {
                                    if (local212) {
                                        WorldMap.method2387(widget.width, Mouse.lastMouseY - local55, Mouse.lastMouseX - local50, widget.height);
                                    }
                                    continue;
                                }
                                if (widget.contentType == 1402) {
                                    if (!GlRenderer.enabled) {
                                        redraw(widget);
                                    }
                                    continue;
                                }
                            }
                            if (!widget.aBoolean24 && local221) {
                                widget.aBoolean24 = true;
                                if (widget.onClickRepeat != null) {
                                    request = new WidgetEvent();
                                    request.aBoolean158 = true;
                                    request.source = widget;
                                    request.mouseX = Mouse.mouseClickX - local50;
                                    request.mouseY = Mouse.mouseClickY - local55;
                                    request.arguments = widget.onClickRepeat;
                                    lowPriorityRequests.addTail(request);
                                }
                            }
                            if (widget.aBoolean24 && local212 && widget.onDrag != null) {
                                request = new WidgetEvent();
                                request.aBoolean158 = true;
                                request.source = widget;
                                request.mouseX = Mouse.lastMouseX - local50;
                                request.mouseY = Mouse.lastMouseY - local55;
                                request.arguments = widget.onDrag;
                                lowPriorityRequests.addTail(request);
                            }
                            if (widget.aBoolean24 && !local212) {
                                widget.aBoolean24 = false;
                                if (widget.onRelease != null) {
                                    request = new WidgetEvent();
                                    request.aBoolean158 = true;
                                    request.source = widget;
                                    request.mouseX = Mouse.lastMouseX - local50;
                                    request.mouseY = Mouse.lastMouseY - local55;
                                    request.arguments = widget.onRelease;
                                    mediumPriorityRequests.addTail(request);
                                }
                            }
                            if (local212 && widget.onHold != null) {
                                request = new WidgetEvent();
                                request.aBoolean158 = true;
                                request.source = widget;
                                request.mouseX = Mouse.lastMouseX - local50;
                                request.mouseY = Mouse.lastMouseY - local55;
                                request.arguments = widget.onHold;
                                lowPriorityRequests.addTail(request);
                            }
                            if (!widget.aBoolean19 && local207) {
                                widget.aBoolean19 = true;
                                if (widget.onMouseOver != null) {
                                    request = new WidgetEvent();
                                    request.aBoolean158 = true;
                                    request.source = widget;
                                    request.mouseX = Mouse.lastMouseX - local50;
                                    request.mouseY = Mouse.lastMouseY - local55;
                                    request.arguments = widget.onMouseOver;
                                    lowPriorityRequests.addTail(request);
                                }
                            }
                            if (widget.aBoolean19 && local207 && widget.onMouseRepeat != null) {
                                request = new WidgetEvent();
                                request.aBoolean158 = true;
                                request.source = widget;
                                request.mouseX = Mouse.lastMouseX - local50;
                                request.mouseY = Mouse.lastMouseY - local55;
                                request.arguments = widget.onMouseRepeat;
                                lowPriorityRequests.addTail(request);
                            }
                            if (widget.aBoolean19 && !local207) {
                                widget.aBoolean19 = false;
                                if (widget.onMouseLeave != null) {
                                    request = new WidgetEvent();
                                    request.aBoolean158 = true;
                                    request.source = widget;
                                    request.mouseX = Mouse.lastMouseX - local50;
                                    request.mouseY = Mouse.lastMouseY - local55;
                                    request.arguments = widget.onMouseLeave;
                                    mediumPriorityRequests.addTail(request);
                                }
                            }
                            if (widget.onTimer != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onTimer;
                                highPriorityRequests.addTail(request);
                            }
                            @Pc(966) WidgetEvent request2;
                            if (widget.onVarcTransmit != null && VarcDomain.updatedVarcsWriterIndex > widget.updatedVarcsReaderIndex) {
                                if (widget.varcTriggers == null || VarcDomain.updatedVarcsWriterIndex - widget.updatedVarcsReaderIndex > 32) {
                                    request = new WidgetEvent();
                                    request.source = widget;
                                    request.arguments = widget.onVarcTransmit;
                                    lowPriorityRequests.addTail(request);
                                } else {
                                    label563: for (i = widget.updatedVarcsReaderIndex; i < VarcDomain.updatedVarcsWriterIndex; i++) {
                                        skill = VarcDomain.updatedVarcs[i & 0x1F];
                                        for (k = 0; k < widget.varcTriggers.length; k++) {
                                            if (widget.varcTriggers[k] == skill) {
                                                request2 = new WidgetEvent();
                                                request2.source = widget;
                                                request2.arguments = widget.onVarcTransmit;
                                                lowPriorityRequests.addTail(request2);
                                                break label563;
                                            }
                                        }
                                    }
                                }
                                widget.updatedVarcsReaderIndex = VarcDomain.updatedVarcsWriterIndex;
                            }
                            if (widget.onVarcstrTransmit != null && VarcDomain.updatedVarcstrsWriterIndex > widget.updatedVarcstrsReaderIndex) {
                                if (widget.varcstrTriggers == null || VarcDomain.updatedVarcstrsWriterIndex - widget.updatedVarcstrsReaderIndex > 32) {
                                    request = new WidgetEvent();
                                    request.source = widget;
                                    request.arguments = widget.onVarcstrTransmit;
                                    lowPriorityRequests.addTail(request);
                                } else {
                                    label539: for (i = widget.updatedVarcstrsReaderIndex; i < VarcDomain.updatedVarcstrsWriterIndex; i++) {
                                        skill = VarcDomain.updatedVarcstrs[i & 0x1F];
                                        for (k = 0; k < widget.varcstrTriggers.length; k++) {
                                            if (widget.varcstrTriggers[k] == skill) {
                                                request2 = new WidgetEvent();
                                                request2.source = widget;
                                                request2.arguments = widget.onVarcstrTransmit;
                                                lowPriorityRequests.addTail(request2);
                                                break label539;
                                            }
                                        }
                                    }
                                }
                                widget.updatedVarcstrsReaderIndex = VarcDomain.updatedVarcstrsWriterIndex;
                            }
                            if (widget.onVarpTransmit != null && VarpDomain.updatedVarpsWriterIndex > widget.updatedVarpsReaderIndex) {
                                if (widget.varpTriggers == null || VarpDomain.updatedVarpsWriterIndex - widget.updatedVarpsReaderIndex > 32) {
                                    request = new WidgetEvent();
                                    request.source = widget;
                                    request.arguments = widget.onVarpTransmit;
                                    lowPriorityRequests.addTail(request);
                                } else {
                                    label515: for (i = widget.updatedVarpsReaderIndex; i < VarpDomain.updatedVarpsWriterIndex; i++) {
                                        skill = VarpDomain.updatedVarps[i & 0x1F];
                                        for (k = 0; k < widget.varpTriggers.length; k++) {
                                            if (widget.varpTriggers[k] == skill) {
                                                request2 = new WidgetEvent();
                                                request2.source = widget;
                                                request2.arguments = widget.onVarpTransmit;
                                                lowPriorityRequests.addTail(request2);
                                                break label515;
                                            }
                                        }
                                    }
                                }
                                widget.updatedVarpsReaderIndex = VarpDomain.updatedVarpsWriterIndex;
                            }
                            if (widget.onInvTransmit != null && Inv.updatedInventoriesWriterIndex > widget.updatedInventoriesReaderIndex) {
                                if (widget.inventoryTriggers == null || Inv.updatedInventoriesWriterIndex - widget.updatedInventoriesReaderIndex > 32) {
                                    request = new WidgetEvent();
                                    request.source = widget;
                                    request.arguments = widget.onInvTransmit;
                                    lowPriorityRequests.addTail(request);
                                } else {
                                    label491: for (i = widget.updatedInventoriesReaderIndex; i < Inv.updatedInventoriesWriterIndex; i++) {
                                        skill = Inv.updatedInventories[i & 0x1F];
                                        for (k = 0; k < widget.inventoryTriggers.length; k++) {
                                            if (widget.inventoryTriggers[k] == skill) {
                                                request2 = new WidgetEvent();
                                                request2.source = widget;
                                                request2.arguments = widget.onInvTransmit;
                                                lowPriorityRequests.addTail(request2);
                                                break label491;
                                            }
                                        }
                                    }
                                }
                                widget.updatedInventoriesReaderIndex = Inv.updatedInventoriesWriterIndex;
                            }
                            if (widget.onStatTransmit != null && PlayerSkillXpTable.updatedStatsWriterIndex > widget.updatedStatsReaderIndex) {
                                if (widget.statTriggers == null || PlayerSkillXpTable.updatedStatsWriterIndex - widget.updatedStatsReaderIndex > 32) {
                                    request = new WidgetEvent();
                                    request.source = widget;
                                    request.arguments = widget.onStatTransmit;
                                    lowPriorityRequests.addTail(request);
                                } else {
                                    label467: for (i = widget.updatedStatsReaderIndex; i < PlayerSkillXpTable.updatedStatsWriterIndex; i++) {
                                        skill = PlayerSkillXpTable.updatedStats[i & 0x1F];
                                        for (k = 0; k < widget.statTriggers.length; k++) {
                                            if (widget.statTriggers[k] == skill) {
                                                request2 = new WidgetEvent();
                                                request2.source = widget;
                                                request2.arguments = widget.onStatTransmit;
                                                lowPriorityRequests.addTail(request2);
                                                break label467;
                                            }
                                        }
                                    }
                                }
                                widget.updatedStatsReaderIndex = PlayerSkillXpTable.updatedStatsWriterIndex;
                            }
                            if (Chat.transmitAt > widget.lastTransmitTimer && widget.onMsg != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onMsg;
                                lowPriorityRequests.addTail(request);
                            }
                            if (FriendList.transmitAt > widget.lastTransmitTimer && widget.onFriendTransmit != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onFriendTransmit;
                                lowPriorityRequests.addTail(request);
                            }
                            if (ClanChat.transmitAt > widget.lastTransmitTimer && widget.onClanTransmit != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onClanTransmit;
                                lowPriorityRequests.addTail(request);
                            }
                            if (StockMarketManager.transmitAt > widget.lastTransmitTimer && widget.onStockTransmit != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onStockTransmit;
                                lowPriorityRequests.addTail(request);
                            }
                            if (miscTransmitAt > widget.lastTransmitTimer && widget.onMiscTransmit != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onMiscTransmit;
                                lowPriorityRequests.addTail(request);
                            }
                            widget.lastTransmitTimer = transmitTimer;
                            if (widget.onKey != null) {
                                for (i = 0; i < keyQueueSize; i++) {
                                    @Pc(1430) WidgetEvent local1430 = new WidgetEvent();
                                    local1430.source = widget;
                                    local1430.keyCode = keyCodes[i];
                                    local1430.keyChar = keyChars[i];
                                    local1430.arguments = widget.onKey;
                                    lowPriorityRequests.addTail(local1430);
                                }
                            }
                            if (Camera.shouldReverse && widget.onMinimapUnlock != null) {
                                request = new WidgetEvent();
                                request.source = widget;
                                request.arguments = widget.onMinimapUnlock;
                                lowPriorityRequests.addTail(request);
                            }
                        }
                    }
                    if (!widget.if3 && ClientScriptRunner.aClass13_14 == null && clickedInventoryWidget == null && !ClientScriptRunner.menuVisible) {
                        if ((widget.anInt470 >= 0 || widget.overColor != 0) && Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
                            if (widget.anInt470 >= 0) {
                                aClass13_22 = arg0[widget.anInt470];
                            } else {
                                aClass13_22 = widget;
                            }
                        }
                        if (widget.type == 8 && Mouse.lastMouseX >= local61 && Mouse.lastMouseY >= local63 && Mouse.lastMouseX < local65 && Mouse.lastMouseY < local67) {
                            Protocol.aClass13_11 = widget;
                        }
                        if (widget.scrollMaxV > widget.height) {
                            method4049(Mouse.lastMouseY, widget.height, widget, Mouse.lastMouseX, local50 + widget.width, local55, widget.scrollMaxV);
                        }
                    }
                    if (widget.type == 0) {
                        method946(arg0, widget.id, local61, local63, local65, local67, local50 - widget.scrollX, local55 - widget.scrollY);
                        if (widget.createdWidgets != null) {
                            method946(widget.createdWidgets, widget.id, local61, local63, local65, local67, local50 - widget.scrollX, local55 - widget.scrollY);
                        }
                        @Pc(1595) SubInterface local1595 = (SubInterface) openInterfaces.get((long) widget.id);
                        if (local1595 != null) {
                            method1320(local50, local63, local55, local65, local1595.interfaceId, local61, local67);
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!tc", name = "a", descriptor = "(IILclient!be;BIIII)V")
    public static void method4049(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) Widget arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
        if (aBoolean84) {
            anInt1396 = 32;
        } else {
            anInt1396 = 0;
        }
        aBoolean84 = false;
        @Pc(139) int local139;
        if (Mouse.pressedButton != 0) {
            if (arg4 <= arg3 && arg4 + 16 > arg3 && arg0 >= arg5 && arg5 + 16 > arg0) {
                arg2.scrollY -= 4;
                redraw(arg2);
            } else if (arg3 >= arg4 && arg3 < arg4 + 16 && arg0 >= arg1 + arg5 - 16 && arg1 + arg5 > arg0) {
                arg2.scrollY += 4;
                redraw(arg2);
            } else if (arg3 >= arg4 - anInt1396 && arg3 < arg4 + anInt1396 + 16 && arg0 >= arg5 + 16 && arg1 + arg5 - 16 > arg0) {
                local139 = arg1 * (arg1 - 32) / arg6;
                if (local139 < 8) {
                    local139 = 8;
                }
                @Pc(150) int local150 = arg1 - local139 - 32;
                @Pc(162) int local162 = arg0 - local139 / 2 - arg5 - 16;
                arg2.scrollY = (arg6 - arg1) * local162 / local150;
                redraw(arg2);
                aBoolean84 = true;
            }
        }
        if (MouseWheel.wheelRotation == 0) {
            return;
        }
        local139 = arg2.width;
        if (arg4 - local139 <= arg3 && arg5 <= arg0 && arg3 < arg4 + 16 && arg1 + arg5 >= arg0) {
            arg2.scrollY += MouseWheel.wheelRotation * 45;
            redraw(arg2);
        }
    }

    @OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "(IBIII)V")
    public static void forceRedrawScreen(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        for (@Pc(3) int local3 = 0; local3 < rectangles; local3++) {
            if (arg0 < rectangleX[local3] + rectangleWidth[local3] && arg0 + arg3 > rectangleX[local3] && rectangleY[local3] + rectangleHeight[local3] > arg1 && rectangleY[local3] < arg2 + arg1) {
                rectangleRedraw[local3] = true;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "(II)V")
    public static void method1949(@OriginalArg(1) int arg0) {
        if (load(arg0)) {
            method2354(-1, cachedWidgets[arg0]);
        }
    }

    @OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "(Lclient!be;)Lclient!be;")
    public static Widget method938(@OriginalArg(0) Widget arg0) {
        @Pc(4) int local4 = getServerActiveProperties(arg0).getDragDepth();
        if (local4 == 0) {
            return null;
        }
        for (@Pc(10) int local10 = 0; local10 < local4; local10++) {
            arg0 = getComponent(arg0.overlayer);
            if (arg0 == null) {
                return null;
            }
        }
        return arg0;
    }

    @OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II[Lclient!be;)V")
    public static void method2354(@OriginalArg(1) int arg0, @OriginalArg(2) Widget[] arg1) {
        for (@Pc(7) int local7 = 0; local7 < arg1.length; local7++) {
            @Pc(15) Widget local15 = arg1[local7];
            if (local15 != null && local15.overlayer == arg0 && (!local15.if3 || !method947(local15))) {
                if (local15.type == 0) {
                    if (!local15.if3 && method947(local15) && local15 != aClass13_22) {
                        continue;
                    }
                    method2354(local15.id, arg1);
                    if (local15.createdWidgets != null) {
                        method2354(local15.id, local15.createdWidgets);
                    }
                    @Pc(73) SubInterface local73 = (SubInterface) openInterfaces.get((long) local15.id);
                    if (local73 != null) {
                        method1949(local73.interfaceId);
                    }
                }
                if (local15.type == 6) {
                    @Pc(105) int local105;
                    if (local15.modelSeqId != -1 || local15.activeModelSeqId != -1) {
                        @Pc(100) boolean local100 = ClientScriptRunner.isTrue(local15);
                        if (local100) {
                            local105 = local15.activeModelSeqId;
                        } else {
                            local105 = local15.modelSeqId;
                        }
                        if (local105 != -1) {
                            @Pc(118) SeqType local118 = SeqTypeList.get(local105);
                            if (local118 != null) {
                                local15.anInt500 += Protocol.sceneDelta;
                                while (local15.anInt500 > local118.frameDelay[local15.anInt510]) {
                                    local15.anInt500 -= local118.frameDelay[local15.anInt510];
                                    local15.anInt510++;
                                    if (local118.frames.length <= local15.anInt510) {
                                        local15.anInt510 -= local118.replayOff;
                                        if (local15.anInt510 < 0 || local118.frames.length <= local15.anInt510) {
                                            local15.anInt510 = 0;
                                        }
                                    }
                                    local15.anInt496 = local15.anInt510 + 1;
                                    if (local118.frames.length <= local15.anInt496) {
                                        local15.anInt496 -= local118.replayOff;
                                        if (local15.anInt496 < 0 || local118.frames.length <= local15.anInt496) {
                                            local15.anInt496 = -1;
                                        }
                                    }
                                    redraw(local15);
                                }
                            }
                        }
                    }
                    if (local15.modelRotationSpeed != 0 && !local15.if3) {
                        @Pc(239) int local239 = local15.modelRotationSpeed >> 16;
                        @Pc(243) int local243 = local239 * Protocol.sceneDelta;
                        local105 = local15.modelRotationSpeed << 16 >> 16;
                        local15.modelXAngle = local243 + local15.modelXAngle & 0x7FF;
                        local105 *= Protocol.sceneDelta;
                        local15.modelYAngle = local15.modelYAngle + local105 & 0x7FF;
                        redraw(local15);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!mc", name = "f", descriptor = "(B)V")
    public static void closeModal() {
        Protocol.outboundBuffer.pIsaac1(184);
        for (@Pc(18) SubInterface local18 = (SubInterface) openInterfaces.head(); local18 != null; local18 = (SubInterface) openInterfaces.next()) {
            if (local18.anInt5879 == 0) {
                closeInterface(true, local18);
            }
        }
        if (ClientScriptRunner.aClass13_10 != null) {
            redraw(ClientScriptRunner.aClass13_10);
            ClientScriptRunner.aClass13_10 = null;
        }
    }
}
