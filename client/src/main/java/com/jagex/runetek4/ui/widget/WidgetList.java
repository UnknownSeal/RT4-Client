package com.jagex.runetek4.ui.widget;

import com.jagex.runetek4.*;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.data.cache.CacheArchive;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.entity.entity.PlayerAppearance;
import com.jagex.runetek4.graphics.gl.GlCleaner;
import com.jagex.runetek4.graphics.model.Model;
import com.jagex.runetek4.graphics.model.SoftwareModel;
import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.scene.SceneGraph;
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
import com.jagex.runetek4.ui.sprite.GlSprite;
import com.jagex.runetek4.ui.sprite.Sprite;
import com.jagex.runetek4.ui.sprite.Sprites;
import com.jagex.runetek4.util.IntUtils;
import com.jagex.runetek4.util.math.MathUtils;
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
import com.jagex.runetek4.util.string.StringUtils;
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
    public static boolean hasScrollbar = false;
    @OriginalMember(owner = "client!bn", name = "V", descriptor = "I")
    public static int rectangles = 0;
    @OriginalMember(owner = "runetek4.client!oj", name = "y", descriptor = "I")
    public static int keyQueueSize = 0;
    @OriginalMember(owner = "client!bm", name = "f", descriptor = "Lclient!ve;")
    public static Js5 gameImageJs5;
    @OriginalMember(owner = "runetek4.client!lg", name = "b", descriptor = "Z")
    public static boolean dragActive = false;
    @OriginalMember(owner = "client!sh", name = "f", descriptor = "I")
    public static int minY = -1;
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
    public static Widget targetWidget = null;
    @OriginalMember(owner = "client!bn", name = "O", descriptor = "I")
    public static int menuWidth;
    @OriginalMember(owner = "client!bc", name = "X", descriptor = "I")
    public static int menuHeight;
    @OriginalMember(owner = "runetek4.client!si", name = "ab", descriptor = "I")
    public static int menuY;
    @OriginalMember(owner = "runetek4.client!lj", name = "w", descriptor = "I")
    public static int clickedInventoryComponentX = 0;
    @OriginalMember(owner = "runetek4.client!ok", name = "b", descriptor = "I")
    public static int menuX;
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
    public static int lastMouseY = -1;
    @OriginalMember(owner = "runetek4.client!kl", name = "s", descriptor = "I")
    public static int anInt3337 = 0;
    @OriginalMember(owner = "runetek4.client!jk", name = "p", descriptor = "I")
    public static int lastMouseX = -1;
    @OriginalMember(owner = "client!df", name = "n", descriptor = "I")
    public static int anInt1396 = 0;
    @OriginalMember(owner = "client!di", name = "H", descriptor = "Z")
    public static boolean aBoolean84 = false;
    @OriginalMember(owner = "client!dh", name = "a", descriptor = "Z")
    public static boolean canDrag = false;
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
            redrawScreen(menuX, menuWidth, menuY, menuHeight);
        } else {
            redrawScreen(menuX, menuWidth, menuY, menuHeight);
            local43 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(75) int local75 = 0; local75 < MiniMenu.menuActionRow; local75++) {
                @Pc(88) int local88 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(local75));
                if (local43 < local88) {
                    local43 = local88;
                }
            }
            menuHeight = MiniMenu.menuActionRow * 15 + (hasScrollbar ? 26 : 22);
            menuWidth = local43 + 8;
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
            if (widget != null && widget.overlayer == arg1 && (!widget.if3 || widget.type == 0 || widget.aBoolean25 || getServerActiveProperties(widget).events != 0 || widget == ClientScriptRunner.containerWidget || widget.contentType == 1338) && (!widget.if3 || !method947(widget))) {
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
                if (widget == ClientScriptRunner.dragWidget) {
                    canDrag = true;
                    lastMouseX = local50;
                    lastMouseY = local55;
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
                            if (ClientScriptRunner.dragTime == 0) {
                                ClientScriptRunner.dragWidget = null;
                                ClientScriptRunner.containerWidget = null;
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
                            ClientScriptRunner.startWidgetDrag(Mouse.mouseClickY - local55, Mouse.mouseClickX - local50, widget);
                        }
                        if (ClientScriptRunner.dragWidget != null && ClientScriptRunner.dragWidget != widget && local207 && getServerActiveProperties(widget).isDragTarget()) {
                            targetWidget = widget;
                        }
                        if (widget == ClientScriptRunner.containerWidget) {
                            dragActive = true;
                            ClientScriptRunner.minX = local50;
                            minY = local55;
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
                            if (ClientScriptRunner.dragWidget != null || clickedInventoryWidget != null || ClientScriptRunner.menuVisible || widget.contentType != 1400 && anInt3337 > 0) {
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
                                        ClientScriptRunner.dragStartX = Mouse.lastMouseX;
                                        ClientScriptRunner.dragStartY = Mouse.lastMouseY;
                                        continue;
                                    }
                                    if (local212 && anInt3337 > 0) {
                                        if (anInt3337 == 1 && (ClientScriptRunner.dragStartX != Mouse.lastMouseX || ClientScriptRunner.dragStartY != Mouse.lastMouseY)) {
                                            anInt4620 = WorldMap.anInt435;
                                            anInt1885 = WorldMap.anInt919;
                                            anInt3337 = 2;
                                        }
                                        if (anInt3337 == 2) {
                                            WorldMap.method1964(anInt4620 + (int) ((double) (ClientScriptRunner.dragStartX - Mouse.lastMouseX) * 2.0D / (double) WorldMap.targetZoom));
                                            WorldMap.method4641(anInt1885 + (int) ((double) (ClientScriptRunner.dragStartY - Mouse.lastMouseY) * 2.0D / (double) WorldMap.targetZoom));
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
                    if (!widget.if3 && ClientScriptRunner.dragWidget == null && clickedInventoryWidget == null && !ClientScriptRunner.menuVisible) {
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
    public static Widget canAcceptDrop(@OriginalArg(0) Widget arg0) {
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

    @OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(III[Lclient!be;IIIIBI)V")
    public static void renderWidget(@OriginalArg(0) int clipLeft, @OriginalArg(1) int offsetY, @OriginalArg(2) int offsetX, @OriginalArg(3) Widget[] widgets, @OriginalArg(4) int clipRight, @OriginalArg(5) int layer, @OriginalArg(6) int clipTop, @OriginalArg(7) int clipBottom, @OriginalArg(9) int parentRectangle) {
        if (GlRenderer.enabled) {
            GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
        } else {
            SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
            Rasterizer.prepare();
        }
        for (@Pc(18) int i = 0; i < widgets.length; i++) {
            @Pc(30) Widget widget = widgets[i];
            if (widget != null && (widget.overlayer == layer || layer == -1412584499 && widget == ClientScriptRunner.dragWidget)) {
                @Pc(57) int rectangle;
                if (parentRectangle == -1) {
                    rectangleX[rectangles] = offsetX + widget.x;
                    rectangleY[rectangles] = widget.y + offsetY;
                    rectangleWidth[rectangles] = widget.width;
                    rectangleHeight[rectangles] = widget.height;
                    rectangle = rectangles++;
                } else {
                    rectangle = parentRectangle;
                }
                widget.rectangleLoop = Client.loop;
                widget.rectangle = rectangle;
                if (!widget.if3 || !method947(widget)) {
                    if (widget.contentType > 0) {
                        updateGenderDependentWidgets(widget);
                    }
                    @Pc(114) int renderY = offsetY + widget.y;
                    @Pc(117) int alpha = widget.alpha;
                    @Pc(123) int renderX = widget.x + offsetX;
                    if (Cheat.qaOpTest && (getServerActiveProperties(widget).events != 0 || widget.type == 0) && alpha > 127) {
                        alpha = 127;
                    }
                    @Pc(166) int clipLeft2;
                    @Pc(164) int clipTop2;
                    if (widget == ClientScriptRunner.dragWidget) {
                        if (layer != -1412584499 && !widget.dragRenderBehavior) {
                            ClientScriptRunner.anInt4696 = offsetX;
                            ClientScriptRunner.anInt3126 = offsetY;
                            ClientScriptRunner.aClass13Array13 = widgets;
                            continue;
                        }
                        if (ClientScriptRunner.isDragging && dragActive) {
                            clipTop2 = Mouse.lastMouseY;
                            clipLeft2 = Mouse.lastMouseX;
                            clipTop2 -= ClientScriptRunner.dragStartY;
                            if (clipTop2 < minY) {
                                clipTop2 = minY;
                            }
                            if (clipTop2 + widget.height > ClientScriptRunner.containerWidget.height + minY) {
                                clipTop2 = ClientScriptRunner.containerWidget.height + minY - widget.height;
                            }
                            renderY = clipTop2;
                            clipLeft2 -= ClientScriptRunner.dragStartX;
                            if (ClientScriptRunner.minX > clipLeft2) {
                                clipLeft2 = ClientScriptRunner.minX;
                            }
                            if (ClientScriptRunner.containerWidget.width + ClientScriptRunner.minX < widget.width + clipLeft2) {
                                clipLeft2 = ClientScriptRunner.containerWidget.width + ClientScriptRunner.minX - widget.width;
                            }
                            renderX = clipLeft2;
                        }
                        if (!widget.dragRenderBehavior) {
                            alpha = 128;
                        }
                    }
                    @Pc(302) int clipRight3;
                    @Pc(291) int clipBottom3;
                    @Pc(270) int clipRight2;
                    @Pc(276) int clipBottom2;
                    if (widget.type == 2) {
                        clipBottom3 = clipBottom;
                        clipRight3 = clipRight;
                        clipTop2 = clipTop;
                        clipLeft2 = clipLeft;
                    } else {
                        clipTop2 = renderY > clipTop ? renderY : clipTop;
                        clipLeft2 = clipLeft < renderX ? renderX : clipLeft;
                        clipRight2 = widget.width + renderX;
                        clipBottom2 = renderY + widget.height;
                        if (widget.type == 9) {
                            clipBottom2++;
                            clipRight2++;
                        }
                        clipBottom3 = clipBottom <= clipBottom2 ? clipBottom : clipBottom2;
                        clipRight3 = clipRight2 >= clipRight ? clipRight : clipRight2;
                    }
                    if (!widget.if3 || clipRight3 > clipLeft2 && clipTop2 < clipBottom3) {
                        @Pc(468) int local468;
                        @Pc(503) int memory;
                        @Pc(514) int color;
                        @Pc(518) int cardMemory;
                        @Pc(556) int dragY;
                        @Pc(563) int local563;
                        @Pc(571) int local571;
                        @Pc(545) int objId;
                        if (widget.contentType != 0) {
                            if (widget.contentType == 1337 || widget.contentType == 1403 && GlRenderer.enabled) {
                                aClass13_26 = widget;
                                anInt5574 = renderY;
                                ClientScriptRunner.anInt2503 = renderX;
                                SceneGraph.drawScene(widget.height, widget.contentType == 1403, renderX, widget.width, renderY);
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                continue;
                            }
                            if (widget.contentType == 1338) {
                                if (!widget.method478()) {
                                    continue;
                                }
                                MiniMap.render(rectangle, renderY, renderX, widget);
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                if (MiniMap.state != 0 && MiniMap.state != 3 || ClientScriptRunner.menuVisible || clipLeft2 > ClientScriptRunner.scriptMouseX || ClientScriptRunner.scriptMouseY < clipTop2 || ClientScriptRunner.scriptMouseX >= clipRight3 || clipBottom3 <= ClientScriptRunner.scriptMouseY) {
                                    continue;
                                }
                                clipRight2 = ClientScriptRunner.scriptMouseX - renderX;
                                clipBottom2 = ClientScriptRunner.scriptMouseY - renderY;
                                local468 = widget.compassPixelOffsets[clipBottom2];
                                if (clipRight2 < local468 || clipRight2 > local468 + widget.compassPixelWidths[clipBottom2]) {
                                    continue;
                                }
                                clipBottom2 -= widget.height / 2;
                                memory = Camera.orbitCameraYaw + MiniMap.minimapAnticheatAngle & 0x7FF;
                                clipRight2 -= widget.width / 2;
                                color = MathUtils.sin[memory];
                                cardMemory = MathUtils.cos[memory];
                                color = (MiniMap.minimapZoom + 256) * color >> 8;
                                cardMemory = (MiniMap.minimapZoom + 256) * cardMemory >> 8;
                                objId = cardMemory * clipBottom2 - color * clipRight2 >> 11;
                                dragY = clipBottom2 * color + clipRight2 * cardMemory >> 11;
                                local563 = PlayerList.self.xFine + dragY >> 7;
                                local571 = PlayerList.self.zFine - objId >> 7;
                                if (MiniMenu.aBoolean302 && (MiniMenu.anInt4999 & 0x40) != 0) {
                                    @Pc(583) Widget local583 = getCreatedComponent(MiniMenu.anInt2512, MiniMenu.anInt506);
                                    if (local583 == null) {
                                        MiniMenu.method1294();
                                    } else {
                                        MiniMenu.addActionRow(MiniMenu.anInt5393, 1L, MiniMenu.aClass100_961, local563, (short) 11, MiniMenu.aClass100_545, local571);
                                    }
                                    continue;
                                }
                                if (Client.game == 1) {
                                    MiniMenu.addActionRow(-1, 1L, JString.EMPTY, local563, (short) 36, LocalizedText.FACEHERE, local571);
                                }
                                MiniMenu.addActionRow(-1, 1L, JString.EMPTY, local563, (short) 60, MiniMenu.walkText, local571);
                                continue;
                            }
                            if (widget.contentType == 1339) {
                                if (widget.method478()) {
                                    MiniMap.renderCompass(renderX, renderY, widget, rectangle);
                                    if (GlRenderer.enabled) {
                                        GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                    } else {
                                        SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                    }
                                }
                                continue;
                            }
                            if (widget.contentType == 1400) {
                                WorldMap.method2225(renderX, renderY, widget.height, widget.width);
                                widgetNeedsRedraw[rectangle] = true;
                                rectangleRedraw[rectangle] = true;
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                continue;
                            }
                            if (widget.contentType == 1401) {
                                WorldMap.renderWorldMap(renderX, widget.height, widget.width, renderY);
                                widgetNeedsRedraw[rectangle] = true;
                                rectangleRedraw[rectangle] = true;
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                continue;
                            }
                            if (widget.contentType == 1402) {
                                if (!GlRenderer.enabled) {
                                    Flames.render(renderX, renderY);
                                    widgetNeedsRedraw[rectangle] = true;
                                    rectangleRedraw[rectangle] = true;
                                }
                                continue;
                            }
                            if (widget.contentType == 1405) {
                                if (!Cheat.displayFps) {
                                    continue;
                                }
                                clipRight2 = widget.width + renderX;
                                clipBottom2 = renderY + 15;
                                Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_FPS2, JString.parseInt(GameShell.fps) }), clipRight2, clipBottom2, 16776960, -1);
                                clipBottom2 += 15;
                                @Pc(795) Runtime runtime = Runtime.getRuntime();
                                memory = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
                                color = 16776960;
                                if (memory > 65536) {
                                    color = 16711680;
                                }
                                Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_MEM, JString.parseInt(memory), Cheat.DEBUG_MEM_UNIT}), clipRight2, clipBottom2, color, -1);
                                clipBottom2 += 15;
                                if (GlRenderer.enabled) {
                                    color = 16776960;
                                    cardMemory = (GlCleaner.oncard_texture + GlCleaner.oncard_geometry + GlCleaner.oncard_2d) / 1024;
                                    if (cardMemory > 65536) {
                                        color = 16711680;
                                    }
                                    Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_CARD, JString.parseInt(cardMemory), Cheat.DEBUG_MEM_UNIT}), clipRight2, clipBottom2, color, -1);
                                    clipBottom2 += 15;
                                }
                                cardMemory = 0;
                                objId = 0;
                                dragY = 0;
                                for (local563 = 0; local563 < 28; local563++) {
                                    cardMemory += Client.js5Providers[local563].getIndexSize();
                                    dragY += Client.js5Providers[local563].getVerifiedGroups();
                                    objId += Client.js5Providers[local563].getTotalVerifiedGroups();
                                }
                                local571 = dragY * 10000 / cardMemory;
                                local563 = objId * 100 / cardMemory;
                                @Pc(968) JString local968 = JString.concatenate(new JString[] { Cheat.DEBUG_CACHE, StringUtils.formatNumber(0, true, 2, (long) local571), ClientScriptRunner.aClass100_672, JString.parseInt(local563), ClientScriptRunner.aClass100_80});
                                Fonts.p11Full.renderRight(local968, clipRight2, clipBottom2, 16776960, -1);
                                clipBottom2 += 12;
                                widgetNeedsRedraw[rectangle] = true;
                                rectangleRedraw[rectangle] = true;
                                continue;
                            }
                            if (widget.contentType == 1406) {
                                ClientScriptRunner.anInt3484 = renderY;
                                LoginManager.hoveredWidget = widget;
                                ClientScriptRunner.anInt3260 = renderX;
                                continue;
                            }
                        }
                        if (!ClientScriptRunner.menuVisible) {
                            if (widget.type == 0 && widget.noClickThrough && ClientScriptRunner.scriptMouseX >= clipLeft2 && ClientScriptRunner.scriptMouseY >= clipTop2 && ClientScriptRunner.scriptMouseX < clipRight3 && clipBottom3 > ClientScriptRunner.scriptMouseY && !Cheat.qaOpTest) {
                                MiniMenu.menuActionRow = 1;
                                MiniMenu.cursors[0] = MiniMenu.defaultCursor;
                                MiniMenu.ops[0] = LocalizedText.CANCEL;
                                MiniMenu.opBases[0] = JString.EMPTY;
                                MiniMenu.actions[0] = 1005;
                            }
                            if (clipLeft2 <= ClientScriptRunner.scriptMouseX && clipTop2 <= ClientScriptRunner.scriptMouseY && clipRight3 > ClientScriptRunner.scriptMouseX && clipBottom3 > ClientScriptRunner.scriptMouseY) {
                                MiniMenu.addComponentEntries(ClientScriptRunner.scriptMouseY - renderY, -renderX + ClientScriptRunner.scriptMouseX, widget);
                            }
                        }
                        if (widget.type == 0) {
                            if (!widget.if3 && method947(widget) && aClass13_22 != widget) {
                                continue;
                            }
                            if (!widget.if3) {
                                if (widget.scrollMaxV - widget.height < widget.scrollY) {
                                    widget.scrollY = widget.scrollMaxV - widget.height;
                                }
                                if (widget.scrollY < 0) {
                                    widget.scrollY = 0;
                                }
                            }
                            renderWidget(clipLeft2, renderY - widget.scrollY, -widget.scrollX + renderX, widgets, clipRight3, widget.id, clipTop2, clipBottom3, rectangle);
                            if (widget.createdWidgets != null) {
                                renderWidget(clipLeft2, renderY - widget.scrollY, -widget.scrollX + renderX, widget.createdWidgets, clipRight3, widget.id, clipTop2, clipBottom3, rectangle);
                            }
                            @Pc(1186) SubInterface local1186 = (SubInterface) openInterfaces.get((long) widget.id);
                            if (local1186 != null) {
                                if (local1186.anInt5879 == 0 && !ClientScriptRunner.menuVisible && ClientScriptRunner.scriptMouseX >= clipLeft2 && clipTop2 <= ClientScriptRunner.scriptMouseY && clipRight3 > ClientScriptRunner.scriptMouseX && ClientScriptRunner.scriptMouseY < clipBottom3 && !Cheat.qaOpTest) {
                                    MiniMenu.ops[0] = LocalizedText.CANCEL;
                                    MiniMenu.menuActionRow = 1;
                                    MiniMenu.cursors[0] = MiniMenu.defaultCursor;
                                    MiniMenu.actions[0] = 1005;
                                    MiniMenu.opBases[0] = JString.EMPTY;
                                }
                                ClientScriptRunner.renderOrInvalidateWidget(local1186.interfaceId, clipLeft2, clipRight3, renderX, rectangle, clipBottom3, clipTop2, renderY);
                            }
                            if (GlRenderer.enabled) {
                                GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                            } else {
                                SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                Rasterizer.prepare();
                            }
                        }
                        if (widgetRedrawPrevious[rectangle] || Cheat.rectDebug > 1) {
                            if (widget.type == 0 && !widget.if3 && widget.scrollMaxV > widget.height) {
                                drawScrollbar(widget.scrollY, widget.scrollMaxV, widget.width + renderX, renderY, widget.height);
                            }
                            if (widget.type != 1) {
                                if (widget.type == 2) {
                                    clipRight2 = 0;
                                    for (clipBottom2 = 0; clipBottom2 < widget.baseHeight; clipBottom2++) {
                                        for (local468 = 0; local468 < widget.baseWidth; local468++) {
                                            color = renderY + clipBottom2 * (widget.invMarginY + 32);
                                            memory = (widget.invMarginX + 32) * local468 + renderX;
                                            if (clipRight2 < 20) {
                                                color += widget.invOffsetY[clipRight2];
                                                memory += widget.invOffsetX[clipRight2];
                                            }
                                            if (widget.invSlotObjId[clipRight2] > 0) {
                                                objId = widget.invSlotObjId[clipRight2] - 1;
                                                if (clipLeft < memory + 32 && memory < clipRight && clipTop < color + 32 && color < clipBottom || widget == clickedInventoryWidget && selectedInventorySlot == clipRight2) {
                                                    @Pc(1476) Sprite sprite;
                                                    if (MiniMenu.anInt5014 == 1 && MiniMenu.anInt4370 == clipRight2 && widget.id == MiniMap.anInt5062) {
                                                        sprite = Inv.getObjectSprite(2, objId, widget.objDrawText, widget.invSlotObjCount[clipRight2], 0);
                                                    } else {
                                                        sprite = Inv.getObjectSprite(1, objId, widget.objDrawText, widget.invSlotObjCount[clipRight2], 3153952);
                                                    }
                                                    if (Rasterizer.textureHasTransparency) {
                                                        widgetNeedsRedraw[rectangle] = true;
                                                    }
                                                    if (sprite == null) {
                                                        redraw(widget);
                                                    } else if (clickedInventoryWidget == widget && clipRight2 == selectedInventorySlot) {
                                                        cardMemory = Mouse.lastMouseX - clickedInventoryComponentX;
                                                        dragY = Mouse.lastMouseY - clickedInventoryComponentY;
                                                        if (dragY < 5 && dragY > -5) {
                                                            dragY = 0;
                                                        }
                                                        if (cardMemory < 5 && cardMemory > -5) {
                                                            cardMemory = 0;
                                                        }
                                                        if (lastItemDragTime < 5) {
                                                            cardMemory = 0;
                                                            dragY = 0;
                                                        }
                                                        // draw dragged icon (at half opacity)
                                                        sprite.renderAlpha(memory + cardMemory, color - -dragY, 128);
                                                        if (layer != -1) {
                                                            @Pc(1571) Widget local1571 = widgets[layer & 0xFFFF];
                                                            @Pc(1577) int top;
                                                            @Pc(1575) int bottom;
                                                            if (GlRenderer.enabled) {
                                                                bottom = GlRaster.clipBottom;
                                                                top = GlRaster.clipTop;
                                                            } else {
                                                                top = SoftwareRaster.clipTop;
                                                                bottom = SoftwareRaster.clipBottom;
                                                            }
                                                            @Pc(1611) int local1611;
                                                            if (top > dragY + color && local1571.scrollY > 0) {
                                                                local1611 = Protocol.sceneDelta * (top - dragY - color) / 3;
                                                                if (local1611 > Protocol.sceneDelta * 10) {
                                                                    local1611 = Protocol.sceneDelta * 10;
                                                                }
                                                                if (local1611 > local1571.scrollY) {
                                                                    local1611 = local1571.scrollY;
                                                                }
                                                                local1571.scrollY -= local1611;
                                                                clickedInventoryComponentY += local1611;
                                                                redraw(local1571);
                                                            }
                                                            if (bottom < dragY + color + 32 && local1571.scrollY < local1571.scrollMaxV - local1571.height) {
                                                                local1611 = (color + dragY + 32 - bottom) * Protocol.sceneDelta / 3;
                                                                if (local1611 > Protocol.sceneDelta * 10) {
                                                                    local1611 = Protocol.sceneDelta * 10;
                                                                }
                                                                if (local1571.scrollMaxV - local1571.scrollY - local1571.height < local1611) {
                                                                    local1611 = local1571.scrollMaxV - local1571.height - local1571.scrollY;
                                                                }
                                                                local1571.scrollY += local1611;
                                                                clickedInventoryComponentY -= local1611;
                                                                redraw(local1571);
                                                            }
                                                        }
                                                    } else if (widget == MiniMenu.pressedInventoryWidget && clipRight2 == MiniMenu.anInt5444) {
                                                        sprite.renderAlpha(memory, color, 128);
                                                    } else {
                                                        sprite.render(memory, color);
                                                    }
                                                }
                                            } else if (widget.invSprite != null && clipRight2 < 20) {
                                                @Pc(1381) Sprite local1381 = widget.method482(clipRight2);
                                                if (local1381 != null) {
                                                    local1381.render(memory, color);
                                                } else if (Widget.aBoolean72) {
                                                    redraw(widget);
                                                }
                                            }
                                            clipRight2++;
                                        }
                                    }
                                } else if (widget.type == 3) {
                                    if (ClientScriptRunner.isTrue(widget)) {
                                        clipRight2 = widget.activeColor;
                                        if (aClass13_22 == widget && widget.anInt475 != 0) {
                                            clipRight2 = widget.anInt475;
                                        }
                                    } else {
                                        clipRight2 = widget.color;
                                        if (widget == aClass13_22 && widget.overColor != 0) {
                                            clipRight2 = widget.overColor;
                                        }
                                    }
                                    if (alpha == 0) {
                                        if (widget.filled) {
                                            if (GlRenderer.enabled) {
                                                GlRaster.fillRect(renderX, renderY, widget.width, widget.height, clipRight2);
                                            } else {
                                                SoftwareRaster.fillRect(renderX, renderY, widget.width, widget.height, clipRight2);
                                            }
                                        } else if (GlRenderer.enabled) {
                                            GlRaster.drawRect(renderX, renderY, widget.width, widget.height, clipRight2);
                                        } else {
                                            SoftwareRaster.drawRect(renderX, renderY, widget.width, widget.height, clipRight2);
                                        }
                                    } else if (widget.filled) {
                                        if (GlRenderer.enabled) {
                                            GlRaster.fillRectAlpha(renderX, renderY, widget.width, widget.height, clipRight2, 256 - (alpha & 0xFF));
                                        } else {
                                            SoftwareRaster.fillRectAlpha(renderX, renderY, widget.width, widget.height, clipRight2, 256 - (alpha & 0xFF));
                                        }
                                    } else if (GlRenderer.enabled) {
                                        GlRaster.drawRectAlpha(renderX, renderY, widget.width, widget.height, clipRight2, 256 - (alpha & 0xFF));
                                    } else {
                                        SoftwareRaster.drawRectAlpha(renderX, renderY, widget.width, widget.height, clipRight2, 256 - (alpha & 0xFF));
                                    }
                                } else {
                                    @Pc(1921) com.jagex.runetek4.data.cache.media.Font local1921;
                                    if (widget.type == 4) {
                                        local1921 = widget.getFont(Sprites.nameIcons);
                                        if (local1921 != null) {
                                            @Pc(1934) JString local1934 = widget.text;
                                            if (ClientScriptRunner.isTrue(widget)) {
                                                clipBottom2 = widget.activeColor;
                                                if (aClass13_22 == widget && widget.anInt475 != 0) {
                                                    clipBottom2 = widget.anInt475;
                                                }
                                                if (widget.activeText.length() > 0) {
                                                    local1934 = widget.activeText;
                                                }
                                            } else {
                                                clipBottom2 = widget.color;
                                                if (aClass13_22 == widget && widget.overColor != 0) {
                                                    clipBottom2 = widget.overColor;
                                                }
                                            }
                                            if (widget.if3 && widget.objId != -1) {
                                                @Pc(1989) ObjType local1989 = ObjTypeList.get(widget.objId);
                                                local1934 = local1989.name;
                                                if (local1934 == null) {
                                                    local1934 = MiniMenu.NULL;
                                                }
                                                if ((local1989.stackable == 1 || widget.objCount != 1) && widget.objCount != -1) {
                                                    local1934 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local1934, JString.aClass100_375, StringUtils.formatNumber(widget.objCount) });
                                                }
                                            }
                                            if (ClientScriptRunner.aClass13_10 == widget) {
                                                clipBottom2 = widget.color;
                                                local1934 = LocalizedText.PLEASEWAIT;
                                            }
                                            if (!widget.if3) {
                                                local1934 = JString.processStringTokens(widget, local1934);
                                            }
                                            local1921.drawInterfaceText(local1934, renderX, renderY, widget.width, widget.height, clipBottom2, widget.shadowed ? 0 : -1, widget.halign, widget.valign, widget.vpadding);
                                        } else if (Widget.aBoolean72) {
                                            redraw(widget);
                                        }
                                    } else if (widget.type == 5) {
                                        @Pc(2094) Sprite sprite;
                                        if (widget.if3) {
                                            if (widget.objId == -1) {
                                                sprite = widget.method489(false);
                                            } else {
                                                sprite = Inv.getObjectSprite(widget.outlineThickness, widget.objId, widget.objDrawText, widget.objCount, widget.shadowColor);
                                            }
                                            if (sprite != null) {
                                                clipBottom2 = sprite.innerWidth;
                                                local468 = sprite.innerHeight;
                                                if (widget.spriteTiling) {
                                                    memory = (clipBottom2 + widget.width - 1) / clipBottom2;
                                                    color = (widget.height + local468 - 1) / local468;
                                                    if (GlRenderer.enabled) {
                                                        GlRaster.method1183(renderX, renderY, widget.width + renderX, widget.height + renderY);
                                                        @Pc(2274) boolean local2274 = IntUtils.isPowerOfTwo(sprite.width);
                                                        @Pc(2279) boolean local2279 = IntUtils.isPowerOfTwo(sprite.height);
                                                        @Pc(2282) GlSprite local2282 = (GlSprite) sprite;
                                                        if (local2274 && local2279) {
                                                            if (alpha == 0) {
                                                                local2282.method1429(renderX, renderY, memory, color);
                                                            } else {
                                                                local2282.method1426(renderX, renderY, 256 - (alpha & 0xFF), memory, color);
                                                            }
                                                        } else if (local2274) {
                                                            for (local563 = 0; local563 < color; local563++) {
                                                                if (alpha == 0) {
                                                                    local2282.method1429(renderX, local563 * local468 + renderY, memory, 1);
                                                                } else {
                                                                    local2282.method1426(renderX, renderY + local563 * local468, -(alpha & 0xFF) + 256, memory, 1);
                                                                }
                                                            }
                                                        } else if (local2279) {
                                                            for (local563 = 0; local563 < memory; local563++) {
                                                                if (alpha == 0) {
                                                                    local2282.method1429(clipBottom2 * local563 + renderX, renderY, 1, color);
                                                                } else {
                                                                    local2282.method1426(clipBottom2 * local563 + renderX, renderY, 256 - (alpha & 0xFF), 1, color);
                                                                }
                                                            }
                                                        } else {
                                                            for (local563 = 0; local563 < memory; local563++) {
                                                                for (local571 = 0; local571 < color; local571++) {
                                                                    if (alpha == 0) {
                                                                        sprite.render(renderX + clipBottom2 * local563, local468 * local571 + renderY);
                                                                    } else {
                                                                        sprite.renderAlpha(local563 * clipBottom2 + renderX, local468 * local571 + renderY, 256 - (alpha & 0xFF));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                                    } else {
                                                        SoftwareRaster.method2498(renderX, renderY, renderX + widget.width, renderY - -widget.height);
                                                        for (cardMemory = 0; cardMemory < memory; cardMemory++) {
                                                            for (dragY = 0; dragY < color; dragY++) {
                                                                if (widget.angle2d != 0) {
                                                                    sprite.renderAngled(renderY + local468 * dragY + local468 / 2, widget.angle2d, 4096, cardMemory * clipBottom2 + renderX + clipBottom2 / 2);
                                                                } else if (alpha == 0) {
                                                                    sprite.render(cardMemory * clipBottom2 + renderX, local468 * dragY + renderY);
                                                                } else {
                                                                    sprite.renderAlpha(cardMemory * clipBottom2 + renderX, renderY + dragY * local468, 256 - (alpha & 0xFF));
                                                                }
                                                            }
                                                        }
                                                        SoftwareRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                                    }
                                                } else {
                                                    memory = widget.width * 4096 / clipBottom2;
                                                    if (widget.angle2d != 0) {
                                                        sprite.renderAngled(renderY + widget.height / 2, widget.angle2d, memory, renderX + widget.width / 2);
                                                    } else if (alpha != 0) {
                                                        sprite.renderAlpha(renderX, renderY, widget.width, widget.height, 256 - (alpha & 0xFF));
                                                    } else if (clipBottom2 == widget.width && local468 == widget.height) {
                                                        sprite.render(renderX, renderY);
                                                    } else {
                                                        // render icons in a container i.e bank icons
                                                        sprite.renderResized(renderX, renderY, widget.width, widget.height);
                                                    }
                                                }
                                            } else if (Widget.aBoolean72) {
                                                redraw(widget);
                                            }
                                        } else {
                                            sprite = widget.method489(ClientScriptRunner.isTrue(widget));
                                            if (sprite != null) {
                                                sprite.render(renderX, renderY);
                                            } else if (Widget.aBoolean72) {
                                                redraw(widget);
                                            }
                                        }
                                    } else {
                                        @Pc(2611) ObjType local2611;
                                        if (widget.type == 6) {
                                            @Pc(2587) boolean local2587 = ClientScriptRunner.isTrue(widget);
                                            @Pc(2589) Model local2589 = null;
                                            if (local2587) {
                                                clipBottom2 = widget.activeModelSeqId;
                                            } else {
                                                clipBottom2 = widget.modelSeqId;
                                            }
                                            memory = 0;
                                            if (widget.objId != -1) {
                                                local2611 = ObjTypeList.get(widget.objId);
                                                if (local2611 != null) {
                                                    local2611 = local2611.getMeshAddress(widget.objCount);
                                                    @Pc(2630) SeqType local2630 = clipBottom2 == -1 ? null : SeqTypeList.get(clipBottom2);
                                                    local2589 = local2611.getModel(widget.anInt496, widget.anInt500, local2630, 1, widget.anInt510);
                                                    if (local2589 == null) {
                                                        redraw(widget);
                                                    } else {
                                                        memory = -local2589.getMinY() / 2;
                                                    }
                                                }
                                            } else if (widget.modelType == 5) {
                                                if (widget.modelId == -1) {
                                                    local2589 = PlayerAppearance.DEFAULT.method1954(null, -1, null, null, 0, -1, 0, -1, -1);
                                                } else {
                                                    color = widget.modelId & 0x7FF;
                                                    if (color == PlayerList.selfId) {
                                                        color = 2047;
                                                    }
                                                    @Pc(2751) Player local2751 = PlayerList.players[color];
                                                    @Pc(2760) SeqType local2760 = clipBottom2 == -1 ? null : SeqTypeList.get(clipBottom2);
                                                    if (local2751 != null && (int) local2751.username.encode37() << 11 == (widget.modelId & 0xFFFFF800)) {
                                                        local2589 = local2751.appearance.method1954(null, -1, null, local2760, 0, -1, 0, widget.anInt510, 0);
                                                    }
                                                }
                                            } else if (clipBottom2 == -1) {
                                                local2589 = widget.method488(-1, null, -1, 0, local2587, PlayerList.self.appearance);
                                                if (local2589 == null && Widget.aBoolean72) {
                                                    redraw(widget);
                                                }
                                            } else {
                                                @Pc(2689) SeqType local2689 = SeqTypeList.get(clipBottom2);
                                                local2589 = widget.method488(widget.anInt496, local2689, widget.anInt510, widget.anInt500, local2587, PlayerList.self.appearance);
                                                if (local2589 == null && Widget.aBoolean72) {
                                                    redraw(widget);
                                                }
                                            }
                                            if (local2589 != null) {
                                                if (widget.anInt451 > 0) {
                                                    color = (widget.width << 8) / widget.anInt451;
                                                } else {
                                                    color = 256;
                                                }
                                                if (widget.anInt526 <= 0) {
                                                    cardMemory = 256;
                                                } else {
                                                    cardMemory = (widget.height << 8) / widget.anInt526;
                                                }
                                                dragY = renderX + widget.width / 2 + (color * widget.anInt495 >> 8);
                                                objId = widget.height / 2 + renderY + (cardMemory * widget.anInt481 >> 8);
                                                if (GlRenderer.enabled) {
                                                    if (widget.modelOrtho) {
                                                        GlRenderer.method4182(dragY, objId, widget.modelZoom, widget.aShort11, color, cardMemory);
                                                    } else {
                                                        GlRenderer.method4148(dragY, objId, color, cardMemory);
                                                        GlRenderer.method4152((float) widget.aShort10, (float) widget.aShort11 * 1.5F);
                                                    }
                                                    GlRenderer.restoreLighting();
                                                    GlRenderer.setDepthTestEnabled(true);
                                                    GlRenderer.setFogEnabled(false);
                                                    FogManager.init(Preferences.brightness);
                                                    if (ClientScriptRunner.aBoolean299) {
                                                        GlRaster.method1177();
                                                        GlRenderer.clearDepthBuffer();
                                                        GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                                        ClientScriptRunner.aBoolean299 = false;
                                                    }
                                                    if (widget.aBoolean34) {
                                                        GlRenderer.disableDepthMask();
                                                    }
                                                    local563 = MathUtils.sin[widget.modelXAngle] * widget.modelZoom >> 16;
                                                    local571 = widget.modelZoom * MathUtils.cos[widget.modelXAngle] >> 16;
                                                    if (widget.if3) {
                                                        local2589.setCamera(widget.modelYAngle, widget.modelYOffset, widget.modelXAngle, widget.modelXOffset, widget.modelZOffset + local563 + memory, widget.modelZOffset + local571, -1L);
                                                    } else {
                                                        local2589.setCamera(widget.modelYAngle, 0, widget.modelXAngle, 0, local563, local571, -1L);
                                                    }
                                                    if (widget.aBoolean34) {
                                                        GlRenderer.enableDepthMask();
                                                    }
                                                } else {
                                                    Rasterizer.setBounds(dragY, objId);
                                                    local563 = MathUtils.sin[widget.modelXAngle] * widget.modelZoom >> 16;
                                                    local571 = widget.modelZoom * MathUtils.cos[widget.modelXAngle] >> 16;
                                                    if (!widget.if3) {
                                                        local2589.setCamera(widget.modelYAngle, 0, widget.modelXAngle, 0, local563, local571, -1L);
                                                    } else if (widget.modelOrtho) {
                                                        ((SoftwareModel) local2589).method4591(widget.modelYAngle, widget.modelYOffset, widget.modelXAngle, widget.modelXOffset, widget.modelZOffset + memory + local563, local571 + widget.modelZOffset, widget.modelZoom);
                                                    } else {
                                                        local2589.setCamera(widget.modelYAngle, widget.modelYOffset, widget.modelXAngle, widget.modelXOffset, widget.modelZOffset + local563 + memory, local571 + widget.modelZOffset, -1L);
                                                    }
                                                    Rasterizer.prepareOffsets();
                                                }
                                            }
                                        } else {
                                            if (widget.type == 7) {
                                                local1921 = widget.getFont(Sprites.nameIcons);
                                                if (local1921 == null) {
                                                    if (Widget.aBoolean72) {
                                                        redraw(widget);
                                                    }
                                                    continue;
                                                }
                                                clipBottom2 = 0;
                                                for (local468 = 0; local468 < widget.baseHeight; local468++) {
                                                    for (memory = 0; memory < widget.baseWidth; memory++) {
                                                        if (widget.invSlotObjId[clipBottom2] > 0) {
                                                            local2611 = ObjTypeList.get(widget.invSlotObjId[clipBottom2] - 1);
                                                            @Pc(3159) JString local3159;
                                                            if (local2611.stackable != 1 && widget.invSlotObjCount[clipBottom2] == 1) {
                                                                local3159 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local2611.name, JString.aClass100_978 });
                                                            } else {
                                                                local3159 = JString.concatenate(new JString[] { MiniMenu.aClass100_32, local2611.name, JString.aClass100_375, StringUtils.formatNumber(widget.invSlotObjCount[clipBottom2]) });
                                                            }
                                                            dragY = renderX + memory * (widget.invMarginX + 115);
                                                            objId = (widget.invMarginY + 12) * local468 + renderY;
                                                            if (widget.halign == 0) {
                                                                local1921.renderLeft(local3159, dragY, objId, widget.color, widget.shadowed ? 0 : -1);
                                                            } else if (widget.halign == 1) {
                                                                local1921.renderCenter(local3159, dragY + 57, objId, widget.color, widget.shadowed ? 0 : -1);
                                                            } else {
                                                                local1921.renderRight(local3159, dragY + 115 - 1, objId, widget.color, widget.shadowed ? 0 : -1);
                                                            }
                                                        }
                                                        clipBottom2++;
                                                    }
                                                }
                                            }
                                            if (widget.type == 8 && Protocol.aClass13_11 == widget && Protocol.anInt5235 == ClientScriptRunner.anInt4504) {
                                                clipBottom2 = 0;
                                                clipRight2 = 0;
                                                @Pc(3297) JString local3297 = widget.text;
                                                @Pc(3299) Font local3299 = Fonts.p12Full;
                                                local3297 = JString.processStringTokens(widget, local3297);
                                                @Pc(3325) JString local3325;
                                                while (local3297.length() > 0) {
                                                    cardMemory = local3297.indexOf(JString.aClass100_556);
                                                    if (cardMemory == -1) {
                                                        local3325 = local3297;
                                                        local3297 = JString.EMPTY;
                                                    } else {
                                                        local3325 = local3297.substring(cardMemory, 0);
                                                        local3297 = local3297.substring(cardMemory + 4);
                                                    }
                                                    dragY = local3299.getStringWidth(local3325);
                                                    clipBottom2 += local3299.characterDefaultHeight + 1;
                                                    if (clipRight2 < dragY) {
                                                        clipRight2 = dragY;
                                                    }
                                                }
                                                dragY = renderY + widget.height + 5;
                                                clipRight2 += 6;
                                                clipBottom2 += 7;
                                                if (dragY + clipBottom2 > clipBottom) {
                                                    dragY = clipBottom - clipBottom2;
                                                }
                                                cardMemory = renderX + widget.width - clipRight2 - 5;
                                                if (cardMemory < renderX + 5) {
                                                    cardMemory = renderX + 5;
                                                }
                                                if (clipRight2 + cardMemory > clipRight) {
                                                    cardMemory = clipRight - clipRight2;
                                                }
                                                if (GlRenderer.enabled) {
                                                    GlRaster.fillRect(cardMemory, dragY, clipRight2, clipBottom2, 16777120);
                                                    GlRaster.drawRect(cardMemory, dragY, clipRight2, clipBottom2, 0);
                                                } else {
                                                    SoftwareRaster.fillRect(cardMemory, dragY, clipRight2, clipBottom2, 16777120);
                                                    SoftwareRaster.drawRect(cardMemory, dragY, clipRight2, clipBottom2, 0);
                                                }
                                                local3297 = widget.text;
                                                objId = dragY + local3299.characterDefaultHeight + 2;
                                                local3297 = JString.processStringTokens(widget, local3297);
                                                while (local3297.length() > 0) {
                                                    local563 = local3297.indexOf(JString.aClass100_556);
                                                    if (local563 == -1) {
                                                        local3325 = local3297;
                                                        local3297 = JString.EMPTY;
                                                    } else {
                                                        local3325 = local3297.substring(local563, 0);
                                                        local3297 = local3297.substring(local563 + 4);
                                                    }
                                                    local3299.renderLeft(local3325, cardMemory + 3, objId, 0, -1);
                                                    objId += local3299.characterDefaultHeight + 1;
                                                }
                                            }
                                            if (widget.type == 9) {
                                                if (widget.aBoolean20) {
                                                    local468 = renderX + widget.width;
                                                    clipBottom2 = renderY + widget.height;
                                                    memory = renderY;
                                                } else {
                                                    clipBottom2 = renderY;
                                                    memory = renderY + widget.height;
                                                    local468 = renderX + widget.width;
                                                }
                                                if (widget.lineWidth == 1) {
                                                    if (GlRenderer.enabled) {
                                                        GlRaster.drawDiagonalLine(renderX, clipBottom2, local468, memory, widget.color);
                                                    } else {
                                                        SoftwareRaster.drawDiagonalLine(renderX, clipBottom2, local468, memory, widget.color);
                                                    }
                                                } else if (GlRenderer.enabled) {
                                                    GlRaster.method1181(renderX, clipBottom2, local468, memory, widget.color, widget.lineWidth);
                                                } else {
                                                    SoftwareRaster.method2494(renderX, clipBottom2, local468, memory, widget.color, widget.lineWidth);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(BLclient!be;)V")
    public static void updateGenderDependentWidgets(@OriginalArg(1) Widget widget) {
        @Pc(16) int contentType = widget.contentType;
        if (contentType == 324) {
            if (ClientScriptRunner.defaultSpriteId == -1) {
                ClientScriptRunner.defaultSpriteId = widget.spriteId;
                ClientScriptRunner.alternateSpriteId = widget.activeSpriteId;
            }
            if (PlayerAppearance.DEFAULT.gender) {
                widget.spriteId = ClientScriptRunner.defaultSpriteId;
            } else {
                widget.spriteId = ClientScriptRunner.alternateSpriteId;
            }
        } else if (contentType == 325) {
            if (ClientScriptRunner.defaultSpriteId == -1) {
                ClientScriptRunner.alternateSpriteId = widget.activeSpriteId;
                ClientScriptRunner.defaultSpriteId = widget.spriteId;
            }
            if (PlayerAppearance.DEFAULT.gender) {
                widget.spriteId = ClientScriptRunner.alternateSpriteId;
            } else {
                widget.spriteId = ClientScriptRunner.defaultSpriteId;
            }
        } else if (contentType == 327) {
            widget.modelXAngle = 150;
            widget.modelYAngle = (int) (Math.sin((double) Client.loop / 40.0D) * 256.0D) & 0x7FF;
            widget.modelType = 5;
            widget.modelId = -1;
        } else if (contentType == 328) {
            if (PlayerList.self.username == null) {
                widget.modelId = 0;
            } else {
                widget.modelXAngle = 150;
                widget.modelYAngle = (int) (Math.sin((double) Client.loop / 40.0D) * 256.0D) & 0x7FF;
                widget.modelType = 5;
                widget.modelId = ((int) PlayerList.self.username.encode37() << 11) + 2047;
                widget.anInt496 = PlayerList.self.anInt3388;
                widget.anInt500 = 0;
                widget.modelSeqId = PlayerList.self.movementSeqId;
                widget.anInt510 = PlayerList.self.anInt3407;
            }
        }
    }

    @OriginalMember(owner = "client!fn", name = "a", descriptor = "(BIIIII)V")
    public static void drawScrollbar(@OriginalArg(1) int scrollPosition, @OriginalArg(2) int scrollMax, @OriginalArg(3) int x, @OriginalArg(4) int y, @OriginalArg(5) int height) {
        Sprites.scrollbars[0].renderTransparent(x, y);
        Sprites.scrollbars[1].renderTransparent(x, height + y - 16);
        @Pc(35) int thumbHeight = height * (height - 32) / scrollMax;
        if (thumbHeight < 8) {
            thumbHeight = 8;
        }
        @Pc(54) int thumbY = scrollPosition * (height - thumbHeight - 32) / (scrollMax - height);
        if (!GlRenderer.enabled) {
            SoftwareRaster.fillRect(x, y + 16, 16, height - 32, ClientScriptRunner.anInt4306);
            SoftwareRaster.fillRect(x, thumbY + y + 16, 16, thumbHeight, ClientScriptRunner.anInt1704);
            SoftwareRaster.drawVerticalLine(x, thumbY + y + 16, thumbHeight, ClientScriptRunner.anInt4938);
            SoftwareRaster.drawVerticalLine(x + 1, thumbY + 16 + y, thumbHeight, ClientScriptRunner.anInt4938);
            SoftwareRaster.drawHorizontalLine(x, y + thumbY + 16, 16, ClientScriptRunner.anInt4938);
            SoftwareRaster.drawHorizontalLine(x, y + thumbY + 17, 16, ClientScriptRunner.anInt4938);
            SoftwareRaster.drawVerticalLine(x + 15, thumbY + 16 + y, thumbHeight, ClientScriptRunner.anInt671);
            SoftwareRaster.drawVerticalLine(x + 14, y - -17 - -thumbY, thumbHeight - 1, ClientScriptRunner.anInt671);
            SoftwareRaster.drawHorizontalLine(x, thumbHeight + y + thumbY + 15, 16, ClientScriptRunner.anInt671);
            SoftwareRaster.drawHorizontalLine(x + 1, thumbHeight + y - (-thumbY + -14), 15, ClientScriptRunner.anInt671);
            return;
        }
        GlRaster.fillRect(x, y + 16, 16, height - 32, ClientScriptRunner.anInt4306);
        GlRaster.fillRect(x, y + thumbY + 16, 16, thumbHeight, ClientScriptRunner.anInt1704);
        GlRaster.method1176(x, thumbY + y + 16, thumbHeight, ClientScriptRunner.anInt4938);
        GlRaster.method1176(x + 1, thumbY + 16 + y, thumbHeight, ClientScriptRunner.anInt4938);
        GlRaster.method1174(x, thumbY + y + 16, 16, ClientScriptRunner.anInt4938);
        GlRaster.method1174(x, thumbY + y + 17, 16, ClientScriptRunner.anInt4938);
        GlRaster.method1176(x + 15, y + (16 - -thumbY), thumbHeight, ClientScriptRunner.anInt671);
        GlRaster.method1176(x + 14, y - -thumbY + 17, thumbHeight - 1, ClientScriptRunner.anInt671);
        GlRaster.method1174(x, thumbHeight + y + thumbY + 15, 16, ClientScriptRunner.anInt671);
        GlRaster.method1174(x + 1, y + 14 - -thumbY + thumbHeight, 15, ClientScriptRunner.anInt671);
    }
}
