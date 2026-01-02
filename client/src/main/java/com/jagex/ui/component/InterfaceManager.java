package com.jagex.ui.component;

import com.jagex.core.constants.ComponentClientCode;
import com.jagex.game.runetek4.config.objtype.ObjType;
import com.jagex.game.runetek4.config.objtype.ObjTypeList;
import com.jagex.cache.CacheArchive;
import com.jagex.cache.media.Font;
import com.jagex.entity.player.PlayerAppearance;
import com.jagex.entity.player.PlayerList;
import com.jagex.entity.player.PlayerSkillXpTable;
import com.jagex.game.runetek4.config.paramtype.ParamType;
import com.jagex.game.runetek4.config.paramtype.ParamTypeList;
import com.jagex.graphics.effects.Flames;
import com.jagex.graphics.gl.GlCleaner;
import com.jagex.graphics.model.Model;
import com.jagex.graphics.model.SoftwareModel;
import com.jagex.graphics.raster.Rasterizer;
import com.jagex.scene.Camera;
import com.jagex.scene.SceneGraph;
import com.jagex.ui.chat.ChatHistory;
import com.jagex.ui.chat.ClanChat;
import com.jagex.client.auth.LoginManager;
import com.jagex.game.runetek4.config.seqtype.SeqType;
import com.jagex.cache.media.SoftwareSprite;
import com.jagex.game.runetek4.client.GameShell;
import com.jagex.client.Preferences;
import com.jagex.client.Client;
import com.jagex.game.runetek4.config.bastype.cursor.CursorTypeList;
import com.jagex.game.runetek4.config.seqtype.SeqTypeList;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.key.HashTableIterator;
import com.jagex.core.datastruct.LinkedList;
import com.jagex.game.runetek4.config.bastype.cursor.CursorType;
import com.jagex.entity.player.Player;
import com.jagex.game.inventory.ClientInventory;
import com.jagex.game.state.VarcDomain;
import com.jagex.game.state.VarpDomain;
import com.jagex.graphics.gl.GlRaster;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.graphics.font.Fonts;
import com.jagex.input.Keyboard;
import com.jagex.input.Mouse;
import com.jagex.input.MouseWheel;
import com.jagex.js5.Js5;
import com.jagex.ui.sprite.GlSprite;
import com.jagex.ui.sprite.Sprite;
import com.jagex.ui.sprite.Sprites;
import com.jagex.math.IntMath;
import com.jagex.core.utils.math.MathUtils;
import com.jagex.core.utils.string.JString;
import com.jagex.core.utils.string.LocalizedText;
import com.jagex.graphics.lighting.FogManager;
import com.jagex.network.ClientProt;
import com.jagex.network.Protocol;
import com.jagex.core.datastruct.Node;
import com.jagex.graphics.raster.SoftwareRenderer;
import com.jagex.clientscript.ClientScriptRunner;
import com.jagex.ui.social.FriendList;
import com.jagex.game.stockmarket.StockMarketManager;
import com.jagex.ui.events.HookRequest;
import com.jagex.core.utils.debug.Cheat;
import com.jagex.game.world.WorldMap;
import com.jagex.core.utils.string.StringUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

import static com.jagex.game.camera.CameraMode.MODE_ORBITAL;
import static com.jagex.network.ClientProt.CLOSE_MODAL;

public class InterfaceManager {

    @OriginalMember(owner = "runetek4.client!pf", name = "r", descriptor = "[I")
    public static final int[] cursors = new int[500];
    @OriginalMember(owner = "runetek4.client!uj", name = "C", descriptor = "[Lclient!na;")
    public static final JString[] ops = new JString[500];
    @OriginalMember(owner = "runetek4.client!t", name = "v", descriptor = "[Lclient!na;")
    public static final JString[] opBases = new JString[500];
    @OriginalMember(owner = "runetek4.client!d", name = "eb", descriptor = "[S")
    public static final short[] actions = new short[500];
    @OriginalMember(owner = "client!aj", name = "R", descriptor = "Lclient!na;")
    public static final JString COLOR_LIGHT_ORANGE = JString.parse("<col=ff9040>");
    @OriginalMember(owner = "runetek4.client!pl", name = "e", descriptor = "[I")
    public static final int[] intArgs1 = new int[500];
    @OriginalMember(owner = "client!df", name = "l", descriptor = "Lclient!na;")
    public static final JString COLOR_LIMEGREEN = JString.parse("<col=00ff00>");
    @OriginalMember(owner = "runetek4.client!sc", name = "g", descriptor = "Lclient!na;")
    public static final JString COLOR_LIGHT_ORANGE_ARROW = JString.parse(" )2> <col=ff9040>");
    @OriginalMember(owner = "runetek4.client!mi", name = "U", descriptor = "[J")
    public static final long[] keys = new long[500];
    @OriginalMember(owner = "client!ef", name = "c", descriptor = "[I")
    public static final int[] intArgs2 = new int[500];
    @OriginalMember(owner = "runetek4.client!r", name = "d", descriptor = "Z")
    public static final boolean aBoolean237 = false;
    @OriginalMember(owner = "client!cb", name = "fb", descriptor = "Lclient!na;")
    public static final JString COLON_SEPARATOR = JString.parse(": ");
    @OriginalMember(owner = "runetek4.client!qf", name = "R", descriptor = "Lclient!na;")
    public static final JString ARROW_SEPARATOR = JString.parse(" )2> ");
    private static final int ROOT = 0xABCDABCD;

    @OriginalMember(owner = "runetek4.client!sg", name = "q", descriptor = "[I")
    public static final int[] keyCodes = new int[128];
    @OriginalMember(owner = "runetek4.client!pa", name = "R", descriptor = "[Z")
    public static final boolean[] componentNeedsRedraw = new boolean[100];
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
    public static final IterableHashTable properties = new IterableHashTable(512);
    @OriginalMember(owner = "runetek4.client!ql", name = "h", descriptor = "Lclient!na;")
    public static final JString aClass100_903 = JString.parse("Hidden)2");
    @OriginalMember(owner = "runetek4.client!sc", name = "z", descriptor = "[Z")
    public static final boolean[] componentRedrawPrevious = new boolean[100];
    private static final int TARGET_MASK_OBJ = 0x10;
    private static final int TARGET_MASK_COMPONENT = 0x20;
    @OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
    public static int transmitTimer = 1;
    @OriginalMember(owner = "runetek4.client!md", name = "W", descriptor = "I")
    public static int topLevelInterface = -1;
    @OriginalMember(owner = "client!je", name = "T", descriptor = "Lclient!sc;")
    public static IterableHashTable subInterfaces = new IterableHashTable(8);
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
    public static Component specialComponent = null;
    @OriginalMember(owner = "runetek4.client!rg", name = "s", descriptor = "I")
    public static int anInt5574 = -1;
    @OriginalMember(owner = "runetek4.client!og", name = "e", descriptor = "Lclient!be;")
    public static Component previousMouseOverComponent;
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
    @OriginalMember(owner = "runetek4.client!sc", name = "m", descriptor = "[Z")
    public static boolean[] loadedComponents;
    @OriginalMember(owner = "runetek4.client!ra", name = "J", descriptor = "I")
    public static int miscTransmitAt = 0;
    @OriginalMember(owner = "runetek4.client!jd", name = "i", descriptor = "Lclient!be;")
    public static Component clickedInventoryComponent;
    @OriginalMember(owner = "runetek4.client!nf", name = "h", descriptor = "Lclient!be;")
    public static Component mouseOverInventoryComponent;
    @OriginalMember(owner = "client!ef", name = "r", descriptor = "Lclient!be;")
    public static Component dragTarget = null;
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
    public static int currentFrameLoop = -2;
    @OriginalMember(owner = "runetek4.client!pm", name = "hb", descriptor = "I")
    public static int anInt4620;
    @OriginalMember(owner = "client!fk", name = "e", descriptor = "I")
    public static int anInt1885;
    @OriginalMember(owner = "client!bj", name = "s", descriptor = "I")
    public static int lastMouseY = -1;
    @OriginalMember(owner = "runetek4.client!kl", name = "s", descriptor = "I")
    public static int worldMapState = 0;
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
    @OriginalMember(owner = "runetek4.client!k", name = "j", descriptor = "I")
    public static int dragOffsetY;
    @OriginalMember(owner = "runetek4.client!gf", name = "K", descriptor = "I")
    public static int dragOffsetX;
    @OriginalMember(owner = "runetek4.client!th", name = "m", descriptor = "[Lclient!be;")
    public static Component[] dragChildren;
    @OriginalMember(owner = "runetek4.client!ib", name = "e", descriptor = "Lclient!be;")
    public static Component dragSource = null;
    @OriginalMember(owner = "runetek4.client!km", name = "pc", descriptor = "Z")
    public static boolean isDragging = false;
    @OriginalMember(owner = "runetek4.client!ac", name = "p", descriptor = "Lclient!be;")
    public static Component dragLayer = null;
    @OriginalMember(owner = "runetek4.client!nb", name = "d", descriptor = "I")
    public static int dragStartY = 0;
    @OriginalMember(owner = "runetek4.client!re", name = "y", descriptor = "I")
    public static int dragTicks;
    @OriginalMember(owner = "runetek4.client!vd", name = "C", descriptor = "I")
    public static int anInt5014 = 0;
    @OriginalMember(owner = "runetek4.client!th", name = "n", descriptor = "Z")
    public static boolean targetMode = false;
    @OriginalMember(owner = "runetek4.client!u", name = "i", descriptor = "I")
    public static int useWithCursor;
    @OriginalMember(owner = "runetek4.client!hn", name = "W", descriptor = "Lclient!na;")
    public static JString aClass100_545 = null;
    @OriginalMember(owner = "runetek4.client!p", name = "e", descriptor = "I")
    public static int anInt4370;
    @OriginalMember(owner = "runetek4.client!cl", name = "Y", descriptor = "I")
    public static int defaultCursor = -1;
    @OriginalMember(owner = "client!ck", name = "D", descriptor = "Lclient!na;")
    public static JString aClass100_203 = null;
    @OriginalMember(owner = "client!gd", name = "i", descriptor = "Lclient!na;")
    public static JString aClass100_466 = null;
    @OriginalMember(owner = "runetek4.client!wf", name = "f", descriptor = "I")
    public static int useWithMask;
    @OriginalMember(owner = "client!bh", name = "t", descriptor = "I")
    public static int mouseInvInterfaceIndex = 0;
    @OriginalMember(owner = "runetek4.client!jg", name = "b", descriptor = "I")
    public static int useWithParam;

    @OriginalMember(owner = "client!gg", name = "c", descriptor = "(II)V")
    public static void setCursor(@OriginalArg(0) int cursorId) {
        if (!Preferences.cursorsEnabled) {
            cursorId = -1;
        }

        if (cursorId == currentCursor) {
            return;
        }

        if (cursorId != -1) {
            @Pc(24) CursorType cursorType = CursorTypeList.get(cursorId);
            @Pc(28) SoftwareSprite cursorSprite = cursorType.getSprite();
            if (cursorSprite == null) {
                cursorId = -1;
            } else {
                GameShell.signLink.setCursor(cursorSprite.method301(), cursorSprite.innerWidth, GameShell.canvas, new Point(cursorType.hotspotx, cursorType.hotspoty), cursorSprite.innerHeight);
                currentCursor = cursorId;
            }
        }
        if (cursorId == -1 && currentCursor != -1) {
            GameShell.signLink.setCursor(null, -1, GameShell.canvas, new Point(), -1);
            currentCursor = -1;
        }
    }

    @OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ZI)V")
    public static void initializeLoginScreen(@OriginalArg(0) boolean resetInterfaces) {
        if (resetInterfaces) {
            if (topLevelInterface != -1) {
                resetComponent(topLevelInterface);
            }
            for (@Pc(18) SubInterface SubInterface = (SubInterface) subInterfaces.head(); SubInterface != null; SubInterface = (SubInterface) subInterfaces.next()) {
                closeInterface(true, SubInterface);
            }
            topLevelInterface = -1;
            subInterfaces = new IterableHashTable(8);
            createComponentMemoryBuffer();
            topLevelInterface = LoginManager.loginScreenId;
            updateInterfaceLayout(false);
            ClientScriptRunner.method1807();
            ClientScriptRunner.executeOnLoad(topLevelInterface);
        }
        defaultCursor = -1;
        setCursor(ClientScriptRunner.anInt5794);
        PlayerList.self = new Player();
        PlayerList.self.zFine = 3000;
        PlayerList.self.xFine = 3000;
        if (!GlRenderer.enabled) {
            Flames.load(Client.js5Archive8);
            Client.processGameStatus(10);
            return;
        }
        if (Camera.mode == MODE_ORBITAL) {
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
        if (InterfaceList.interfaces[componentId] == null) {
            return;
        }
        @Pc(27) boolean canDeleteFromCache = true;
        for (@Pc(29) int childComponentIndex = 0; childComponentIndex < InterfaceList.interfaces[componentId].length; childComponentIndex++) {
            if (InterfaceList.interfaces[componentId][childComponentIndex] != null) {
                if (InterfaceList.interfaces[componentId][childComponentIndex].type == 2) {
                    canDeleteFromCache = false;
                } else {
                    InterfaceList.interfaces[componentId][childComponentIndex] = null;
                }
            }
        }
        if (canDeleteFromCache) {
            InterfaceList.interfaces[componentId] = null;
        }
        loadedComponents[componentId] = false;
    }

    @OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(ZLclient!wk;Z)V")
    public static void closeInterface(@OriginalArg(0) boolean resetComponent, @OriginalArg(1) SubInterface subInterface) {
        @Pc(9) int parentComponentId = (int) subInterface.key;
        @Pc(16) int componentId = subInterface.interfaceId;
        subInterface.unlink();
        if (resetComponent) {
            resetComponent(componentId);
        }
        clearInterfaceProperties(componentId);
        @Pc(32) Component parentComponent = InterfaceList.list(parentComponentId);
        if (parentComponent != null) {
            redraw(parentComponent);
        }
        @Pc(41) int totalMenuActions = MiniMenu.menuActionRow;
        @Pc(43) int maxWidth;
        for (maxWidth = 0; maxWidth < totalMenuActions; maxWidth++) {
            if (shouldRemoveMenuAction(actions[maxWidth])) {
                MiniMenu.removeActionRow(maxWidth);
            }
        }
        if (MiniMenu.menuActionRow == 1) {
            MiniMenu.open = false;
            redrawScreen(menuX, menuWidth, menuY, menuHeight);
        } else {
            redrawScreen(menuX, menuWidth, menuY, menuHeight);
            maxWidth = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(75) int actionIndex = 0; actionIndex < MiniMenu.menuActionRow; actionIndex++) {
                @Pc(88) int actionWidth = Fonts.b12Full.getStringWidth(MiniMenu.getOp(actionIndex));
                if (maxWidth < actionWidth) {
                    maxWidth = actionWidth;
                }
            }
            menuHeight = MiniMenu.menuActionRow * 15 + (hasScrollbar ? 26 : 22);
            menuWidth = maxWidth + 8;
        }
        if (topLevelInterface != -1) {
            runScripts(1, topLevelInterface);
        }
    }

    @OriginalMember(owner = "runetek4.client!eb", name = "d", descriptor = "(I)V")
    public static void createComponentMemoryBuffer() {
        InterfaceList.interfaces = new Component[gameInterfaceJs5.capacity()][];
        loadedComponents = new boolean[gameInterfaceJs5.capacity()];
    }

    @OriginalMember(owner = "runetek4.client!jm", name = "a", descriptor = "(Z)V")
    public static void prepareFrame() {
        if (topLevelInterface != -1) {
            updateInterfaceState(topLevelInterface);
        }
        for (@Pc(15) int rectangleIndex = 0; rectangleIndex < rectangles; rectangleIndex++) {
            if (componentNeedsRedraw[rectangleIndex]) {
                rectangleRedraw[rectangleIndex] = true;
            }
            componentRedrawPrevious[rectangleIndex] = componentNeedsRedraw[rectangleIndex];
            componentNeedsRedraw[rectangleIndex] = false;
        }
        ClientScriptRunner.anInt2503 = -1;
        mouseOverInventoryComponent = null;
        currentFrameLoop = Client.loop;
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
            SoftwareRenderer.resetBounds();
        }
        Protocol.sceneDelta = 0;
    }

    @OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "(IIIIIIII)V")
    public static void renderInterface(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(3) int clipY, @OriginalArg(4) int width, @OriginalArg(5) int interfaceId, @OriginalArg(6) int clipX, @OriginalArg(7) int height) {
        if (InterfaceList.load(interfaceId)) {
            logicComponentList(InterfaceList.interfaces[interfaceId], -1, clipX, y, width, height, x, clipY);
        }
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "b", descriptor = "(Lclient!be;)Lclient!bf;")
    public static ServerActiveProperties getServerActiveProperties(@OriginalArg(0) Component component) {
        @Pc(13) ServerActiveProperties serverProperties = (ServerActiveProperties) properties.get(((long) component.slot << 32) + (long) component.id);
        return serverProperties == null ? component.properties : serverProperties;
    }

    @OriginalMember(owner = "runetek4.client!dg", name = "a", descriptor = "(ILclient!be;)V")
    public static void redraw(@OriginalArg(1) Component component) {
        if (currentFrameLoop == component.lastUpdate) {
            componentNeedsRedraw[component.rectangle] = true;
        }
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "c", descriptor = "(Lclient!be;)Z")
    public static boolean isHidden(@OriginalArg(0) Component component) {
        if (Cheat.testOpacity) {
            if (getServerActiveProperties(component).events != 0) {
                return false;
            }
            if (component.type == 0) {
                return false;
            }
        }
        return component.hidden;
    }

    @OriginalMember(owner = "runetek4.client!kf", name = "a", descriptor = "(IIBII)V")
    public static void redrawScreen(@OriginalArg(0) int x, @OriginalArg(1) int width, @OriginalArg(3) int y, @OriginalArg(4) int height) {
        for (@Pc(12) int rectangleIndex = 0; rectangleIndex < rectangles; rectangleIndex++) {
            if (rectangleWidth[rectangleIndex] + rectangleX[rectangleIndex] > x && width + x > rectangleX[rectangleIndex] && y < rectangleHeight[rectangleIndex] + rectangleY[rectangleIndex] && rectangleY[rectangleIndex] < y + height) {
                componentNeedsRedraw[rectangleIndex] = true;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(ZB)V")
    public static void updateInterfaceLayout(@OriginalArg(0) boolean fullUpdate) {
        updateInterfaceLayoutImpl(GameShell.canvasHeight, fullUpdate, topLevelInterface, GameShell.canvasWidth);
    }

    @OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(ZLclient!ve;Lclient!ve;Lclient!ve;Lclient!ve;)V")
    public static void init(@OriginalArg(1) Js5 fontsJs5, @OriginalArg(2) Js5 imagesJs5, @OriginalArg(3) Js5 interfacesJs5, @OriginalArg(4) Js5 spritesJs5) {
        gameImageJs5 = imagesJs5;
        aClass153_64 = fontsJs5;
        gameInterfaceJs5 = interfacesJs5;
        aClass153_85 = spritesJs5;
        InterfaceList.interfaces = new Component[gameInterfaceJs5.capacity()][];
        loadedComponents = new boolean[gameInterfaceJs5.capacity()];
    }

    @OriginalMember(owner = "runetek4.client!i", name = "i", descriptor = "(Z)V")
    public static void redrawActiveInterfaces() {
        for (@Pc(6) SubInterface subInterface = (SubInterface) subInterfaces.head(); subInterface != null; subInterface = (SubInterface) subInterfaces.next()) {
            @Pc(14) int interfaceId = subInterface.interfaceId;
            if (InterfaceList.load(interfaceId)) {
                @Pc(21) boolean isIf3Format = true;
                @Pc(25) Component[] components = InterfaceList.interfaces[interfaceId];
                @Pc(27) int local27;
                for (local27 = 0; local27 < components.length; local27++) {
                    if (components[local27] != null) {
                        isIf3Format = components[local27].hasOpKey;
                        break;
                    }
                }
                if (!isIf3Format) {
                    local27 = (int) subInterface.key;
                    @Pc(60) Component parentComponent = InterfaceList.list(local27);
                    if (parentComponent != null) {
                        redraw(parentComponent);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!qj", name = "a", descriptor = "(Lclient!be;BI)Lclient!na;")
    public static JString getOp(@OriginalArg(0) Component component, @OriginalArg(2) int opIndex) {
        if (!getServerActiveProperties(component).isButtonEnabled(opIndex) && component.onOp == null) {
            return null;
        } else if (component.ops == null || component.ops.length <= opIndex || component.ops[opIndex] == null || component.ops[opIndex].trim().length() == 0) {
            return Cheat.testOpacity ? JString.concatenate(new JString[] {aClass100_903, JString.parseInt(opIndex) }) : null;
        } else {
            return component.ops[opIndex];
        }
    }

    @OriginalMember(owner = "runetek4.client!gg", name = "e", descriptor = "(II)V")
    public static void resetComponentAnimations(@OriginalArg(0) int interfaceId) {
        if (!InterfaceList.load(interfaceId)) {
            return;
        }
        @Pc(15) Component[] components = InterfaceList.interfaces[interfaceId];
        for (@Pc(17) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(29) Component component = components[componentIndex];
            if (component != null) {
                component.animationFrame = 1;
                component.animationId = 0;
                component.animationDelay = 0;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ta", name = "a", descriptor = "(IZIII)V")
    public static void updateInterfaceLayoutImpl(@OriginalArg(0) int canvasHeight, @OriginalArg(1) boolean fullUpdate, @OriginalArg(3) int interfaceId, @OriginalArg(4) int canvasWidth) {
        if (InterfaceList.load(interfaceId)) {
            updateComponentLayout(-1, fullUpdate, canvasWidth, canvasHeight, InterfaceList.interfaces[interfaceId]);
        }
    }

    @OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(IZIII[Lclient!be;)V")
    public static void updateComponentLayout(@OriginalArg(0) int parentId, @OriginalArg(1) boolean fullUpdate, @OriginalArg(2) int canvasWidth, @OriginalArg(4) int canvasHeight, @OriginalArg(5) Component[] components) {
        for (@Pc(3) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(19) Component component = components[componentIndex];
            if (component != null && component.layer == parentId) {
                calculateComponentSize(canvasHeight, canvasWidth, component, fullUpdate);
                calculateComponentPosition(component, canvasHeight, canvasWidth);
                if (component.scrollWidth - component.width < component.scrollX) {
                    component.scrollX = component.scrollWidth - component.width;
                }
                if (component.scrollY > component.scrollHeight - component.height) {
                    component.scrollY = component.scrollHeight - component.height;
                }
                if (component.scrollY < 0) {
                    component.scrollY = 0;
                }
                if (component.scrollX < 0) {
                    component.scrollX = 0;
                }
                if (component.type == 0) {
                    calculateLayerDimensions(component, fullUpdate);
                }
            }
        }
    }

    @OriginalMember(owner = "client!bg", name = "a", descriptor = "(Lclient!be;ZI)V")
    public static void calculateLayerDimensions(@OriginalArg(0) Component container, @OriginalArg(1) boolean fullUpdate) {
        @Pc(20) int contentWidth = container.scrollWidth == 0 ? container.width : container.scrollWidth;
        @Pc(32) int contentHeight = container.scrollHeight == 0 ? container.height : container.scrollHeight;
        updateComponentLayout(container.slot, fullUpdate, contentWidth, contentHeight, InterfaceList.interfaces[container.slot >> 16]);
        if (container.staticComponents != null) {
            updateComponentLayout(container.slot, fullUpdate, contentWidth, contentHeight, container.staticComponents);
        }
        @Pc(66) SubInterface subInterface = (SubInterface) subInterfaces.get((long) container.slot);
        if (subInterface != null) {
            updateInterfaceLayoutImpl(contentHeight, fullUpdate, subInterface.interfaceId, contentWidth);
        }
    }

    @OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIILclient!be;Z)V")
    public static void calculateComponentSize(@OriginalArg(0) int availableHeight, @OriginalArg(2) int availableWidth, @OriginalArg(3) Component component, @OriginalArg(4) boolean triggerEvents) {
        @Pc(4) int oldWidth = component.width;
        @Pc(7) int oldHeight = component.height;
        if (component.dynamicWidthValue == 0) {
            component.width = component.baseWidth;
        } else if (component.dynamicWidthValue == 1) {
            component.width = availableWidth - component.baseWidth;
        } else if (component.dynamicWidthValue == 2) {
            component.width = component.baseWidth * availableWidth >> 14;
        } else if (component.dynamicWidthValue == 3) {
            if (component.type == 2) {
                component.width = component.baseWidth * 32 + (component.baseWidth - 1) * component.invMarginX;
            } else if (component.type == 7) {
                component.width = component.baseWidth * 115 + component.invMarginX * (component.baseWidth - 1);
            }
        }
        if (component.dynamicHeightValue == 0) {
            component.height = component.baseHeight;
        } else if (component.dynamicHeightValue == 1) {
            component.height = availableHeight - component.baseHeight;
        } else if (component.dynamicHeightValue == 2) {
            component.height = availableHeight * component.baseHeight >> 14;
        } else if (component.dynamicHeightValue == 3) {
            if (component.type == 2) {
                component.height = (component.baseHeight - 1) * component.invMarginY + component.baseHeight * 32;
            } else if (component.type == 7) {
                component.height = component.baseHeight * 12 + (component.baseHeight - 1) * component.invMarginY;
            }
        }
        if (component.dynamicWidthValue == 4) {
            component.width = component.aspectWidth * component.height / component.aspectHeight;
        }
        if (component.dynamicHeightValue == 4) {
            component.height = component.aspectHeight * component.width / component.aspectWidth;
        }
        if (Cheat.testOpacity && (getServerActiveProperties(component).events != 0 || component.type == 0)) {
            if (component.height < 5 && component.width < 5) {
                component.height = 5;
                component.width = 5;
            } else {
                if (component.width <= 0) {
                    component.width = 5;
                }
                if (component.height <= 0) {
                    component.height = 5;
                }
            }
        }
        if (component.clientcode == 1337) {
            specialComponent = component;
        }
        if (triggerEvents && component.onResize != null && (oldWidth != component.width || component.height != oldHeight)) {
            @Pc(305) HookRequest resizeEvent = new HookRequest();
            resizeEvent.arguments = component.onResize;
            resizeEvent.source = component;
            lowPriorityRequests.push(resizeEvent);
        }
    }

    @OriginalMember(owner = "runetek4.client!ii", name = "a", descriptor = "(Lclient!be;III)V")
    public static void calculateComponentPosition(@OriginalArg(0) Component component, @OriginalArg(2) int availableHeight, @OriginalArg(3) int availableWidth) {
        if (component.xMode == 0) {
            component.y = component.baseY;
        } else if (component.xMode == 1) {
            component.y = (availableHeight - component.height) / 2 + component.baseY;
        } else if (component.xMode == 2) {
            component.y = availableHeight - component.height - component.baseY;
        } else if (component.xMode == 3) {
            component.y = component.baseY * availableHeight >> 14;
        } else if (component.xMode == 4) {
            component.y = (availableHeight * component.baseY >> 14) + (availableHeight - component.height) / 2;
        } else {
            component.y = availableHeight - (availableHeight * component.baseY >> 14) - component.height;
        }
        if (component.yMode == 0) {
            component.x = component.baseX;
        } else if (component.yMode == 1) {
            component.x = component.baseX + (availableWidth - component.width) / 2;
        } else if (component.yMode == 2) {
            component.x = availableWidth - component.baseX - component.width;
        } else if (component.yMode == 3) {
            component.x = component.baseX * availableWidth >> 14;
        } else if (component.yMode == 4) {
            component.x = (component.baseX * availableWidth >> 14) + (availableWidth - component.width) / 2;
        } else {
            component.x = availableWidth - (availableWidth * component.baseX >> 14) - component.width;
        }
        if (!Cheat.testOpacity || getServerActiveProperties(component).events == 0 && component.type != 0) {
            return;
        }
        if (component.y < 0) {
            component.y = 0;
        } else if (component.height + component.y > availableHeight) {
            component.y = availableHeight - component.height;
        }
        if (component.x < 0) {
            component.x = 0;
        } else if (availableWidth < component.x + component.width) {
            component.x = availableWidth - component.width;
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(SI)Z")
    public static boolean shouldRemoveMenuAction(@OriginalArg(0) short actionId) {
        if (actionId == 47 || actionId == 5 || actionId == 43 || actionId == 35 || actionId == 58 || actionId == 22 || actionId == 40 || actionId == 3) {
            return true;
        } else if (actionId == 9 || actionId == 12 || actionId == 1006 || actionId == 1003) {
            return true;
        } else if (actionId == 25 || actionId == 23 || actionId == 48 || actionId == 7 || actionId == 13) {
            return true;
        } else {
            return actionId == 8 || actionId == 32 || actionId == 28 || actionId == 59 || actionId == 51 || actionId == 41;
        }
    }

    @OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(BI)V")
    public static void clearInterfaceProperties(@OriginalArg(1) int interfaceId) {
        for (@Pc(11) Node propertyNode = properties.head(); propertyNode != null; propertyNode = properties.next()) {
            if ((propertyNode.key >> 48 & 0xFFFFL) == (long) interfaceId) {
                propertyNode.unlink();
            }
        }
    }

    @OriginalMember(owner = "client!ed", name = "a", descriptor = "(III)V")
    public static void runScripts(@OriginalArg(1) int triggerId, @OriginalArg(2) int interfaceId) {
        if (InterfaceList.load(interfaceId)) {
            runComponentScripts(InterfaceList.interfaces[interfaceId], triggerId);
        }
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(Lclient!be;I)Lclient!be;")
    public static Component getParentComponent(@OriginalArg(0) Component component) {
        if (component.layer != -1) {
            return InterfaceList.list(component.layer);
        }
        @Pc(28) int interfaceId = component.slot >>> 16;
        @Pc(33) HashTableIterator interfaceIterator = new HashTableIterator(subInterfaces);
        for (@Pc(38) SubInterface subInterface = (SubInterface) interfaceIterator.method2701(); subInterface != null; subInterface = (SubInterface) interfaceIterator.method2700()) {
            if (interfaceId == subInterface.interfaceId) {
                return InterfaceList.list((int) subInterface.key);
            }
        }
        return null;
    }

    @OriginalMember(owner = "client!fn", name = "a", descriptor = "(ILclient!be;)V")
    public static void update(@OriginalArg(1) Component component) {
        @Pc(7) Component parentComponent = getParentComponent(component);
        @Pc(19) int availableWidth;
        @Pc(17) int availableHeight;
        if (parentComponent == null) {
            availableHeight = GameShell.canvasHeight;
            availableWidth = GameShell.canvasWidth;
        } else {
            availableHeight = parentComponent.height;
            availableWidth = parentComponent.width;
        }
        calculateComponentSize(availableHeight, availableWidth, component, false);
        calculateComponentPosition(component, availableHeight, availableWidth);
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "([Lclient!be;ZI)V")
    public static void runComponentScripts(@OriginalArg(0) Component[] components, @OriginalArg(2) int triggerId) {
        for (@Pc(11) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(23) Component component = components[componentIndex];
            if (component != null) {
                if (component.type == 0) {
                    if (component.staticComponents != null) {
                        runComponentScripts(component.staticComponents, triggerId);
                    }
                    @Pc(49) SubInterface subInterface = (SubInterface) subInterfaces.get((long) component.slot);
                    if (subInterface != null) {
                        runScripts(triggerId, subInterface.interfaceId);
                    }
                }
                @Pc(72) HookRequest scriptEvent;
                if (triggerId == 0 && component.onDialogAbort != null) {
                    scriptEvent = new HookRequest();
                    scriptEvent.arguments = component.onDialogAbort;
                    scriptEvent.source = component;
                    ClientScriptRunner.executeScript(scriptEvent);
                }
                if (triggerId == 1 && component.onSubChange != null) {
                    if (component.id >= 0) {
                        @Pc(103) Component parentComponent = InterfaceList.list(component.slot);
                        if (parentComponent == null || parentComponent.staticComponents == null || component.id >= parentComponent.staticComponents.length || parentComponent.staticComponents[component.id] != component) {
                            continue;
                        }
                    }
                    scriptEvent = new HookRequest();
                    scriptEvent.arguments = component.onSubChange;
                    scriptEvent.source = component;
                    ClientScriptRunner.executeScript(scriptEvent);
                }
            }
        }
    }

    @OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "([Lclient!be;IIIIIII)V")
    public static void logicComponentList(@OriginalArg(0) Component[] components, @OriginalArg(1) int parentId, @OriginalArg(2) int clipLeft, @OriginalArg(3) int clipTop, @OriginalArg(4) int clipRight, @OriginalArg(5) int clipBottom, @OriginalArg(6) int offsetX, @OriginalArg(7) int offsetY) {
        for (@Pc(1) int i = 0; i < components.length; i++) {
            @Pc(9) Component component = components[i];
            if (component != null && component.layer == parentId && (!component.hasOpKey || component.type == 0 || component.interactive || getServerActiveProperties(component).events != 0 || component == dragLayer || component.clientcode == 1338) && (!component.hasOpKey || !isHidden(component))) {
                @Pc(50) int componentX = component.x + offsetX;
                @Pc(55) int componentY = component.y + offsetY;
                @Pc(61) int effectiveClipLeft;
                @Pc(63) int effectiveClipTop;
                @Pc(65) int effectiveClipRight;
                @Pc(67) int effectiveClipBottom;
                if (component.type == Component.TYPE_INVENTORY) {
                    effectiveClipLeft = clipLeft;
                    effectiveClipTop = clipTop;
                    effectiveClipRight = clipRight;
                    effectiveClipBottom = clipBottom;
                } else {
                    @Pc(73) int componentRight = componentX + component.width;
                    @Pc(78) int componentBottom = componentY + component.height;
                    if (component.type == Component.TYPE_LINE) {
                        componentRight++;
                        componentBottom++;
                    }
                    effectiveClipLeft = componentX > clipLeft ? componentX : clipLeft;
                    effectiveClipTop = componentY > clipTop ? componentY : clipTop;
                    effectiveClipRight = componentRight < clipRight ? componentRight : clipRight;
                    effectiveClipBottom = componentBottom < clipBottom ? componentBottom : clipBottom;
                }
                if (component == dragSource) {
                    canDrag = true;
                    lastMouseX = componentX;
                    lastMouseY = componentY;
                }
                if (!component.hasOpKey || effectiveClipLeft < effectiveClipRight && effectiveClipTop < effectiveClipBottom) {
                    if (component.type == Component.TYPE_LAYER) {
                        if (!component.hasOpKey && isHidden(component) && previousMouseOverComponent != component) {
                            continue;
                        }
                        if (component.noClickThrough && Mouse.lastMouseX >= effectiveClipLeft && Mouse.lastMouseY >= effectiveClipTop && Mouse.lastMouseX < effectiveClipRight && Mouse.lastMouseY < effectiveClipBottom) {
                            for (@Pc(164) HookRequest hook = (HookRequest) lowPriorityRequests.head(); hook != null; hook = (HookRequest) lowPriorityRequests.next()) {
                                if (hook.shouldExecute) {
                                    hook.unlink();
                                    hook.source.mouseHover = false;
                                }
                            }
                            if (dragTicks == 0) {
                                dragSource = null;
                                dragLayer = null;
                            }
                            worldMapState = 0;
                        }
                    }
                    if (component.hasOpKey) {
                        @Pc(207) boolean mouseOver;
                        if (Mouse.lastMouseX >= effectiveClipLeft && Mouse.lastMouseY >= effectiveClipTop && Mouse.lastMouseX < effectiveClipRight && Mouse.lastMouseY < effectiveClipBottom) {
                            mouseOver = true;
                        } else {
                            mouseOver = false;
                        }
                        @Pc(212) boolean mousePressed = false;
                        if (Mouse.pressedButton == 1 && mouseOver) {
                            mousePressed = true;
                        }
                        @Pc(221) boolean mouseClicked = false;
                        if (Mouse.clickButton == 1 && Mouse.mouseClickX >= effectiveClipLeft && Mouse.mouseClickY >= effectiveClipTop && Mouse.mouseClickX < effectiveClipRight && Mouse.mouseClickY < effectiveClipBottom) {
                            mouseClicked = true;
                        }
                        @Pc(243) int hotkeyIndex;
                        @Pc(322) int cooldownDelay;
                        if (component.hotkeys != null) {
                            for (hotkeyIndex = 0; hotkeyIndex < component.hotkeys.length; hotkeyIndex++) {
                                if (Keyboard.pressedKeys[component.hotkeys[hotkeyIndex]]) {
                                    if (component.hotkeyCooldowns == null || Client.loop >= component.hotkeyCooldowns[hotkeyIndex]) {
                                        @Pc(279) byte modifierMask = component.hotkeyModifiers[hotkeyIndex];
                                        if (modifierMask == 0 || ((modifierMask & 0x2) == 0 || Keyboard.pressedKeys[86]) && ((modifierMask & 0x1) == 0 || Keyboard.pressedKeys[82]) && ((modifierMask & 0x4) == 0 || Keyboard.pressedKeys[81])) {
                                            ifButtonXSend(JString.EMPTY, -1, hotkeyIndex + 1, component.slot);
                                            cooldownDelay = component.hotkeyDelays[hotkeyIndex];
                                            if (component.hotkeyCooldowns == null) {
                                                component.hotkeyCooldowns = new int[component.hotkeys.length];
                                            }
                                            if (cooldownDelay == 0) {
                                                component.hotkeyCooldowns[hotkeyIndex] = Integer.MAX_VALUE;
                                            } else {
                                                component.hotkeyCooldowns[hotkeyIndex] = Client.loop + cooldownDelay;
                                            }
                                        }
                                    }
                                } else if (component.hotkeyCooldowns != null) {
                                    component.hotkeyCooldowns[hotkeyIndex] = 0;
                                }
                            }
                        }
                        if (mouseClicked) {
                            dragTryPickup(Mouse.mouseClickY - componentY, Mouse.mouseClickX - componentX, component);
                        }
                        if (dragSource != null && dragSource != component && mouseOver && getServerActiveProperties(component).isDragTarget()) {
                            dragTarget = component;
                        }
                        if (component == dragLayer) {
                            dragActive = true;
                            ClientScriptRunner.minX = componentX;
                            minY = componentY;
                        }
                        if (component.interactive || component.clientcode != 0) {
                            @Pc(399) HookRequest request;
                            if (mouseOver && MouseWheel.wheelRotation != 0 && component.onScrollWheel != null) {
                                request = new HookRequest();
                                request.shouldExecute = true;
                                request.source = component;
                                request.mouseY = MouseWheel.wheelRotation;
                                request.arguments = component.onScrollWheel;
                                lowPriorityRequests.push(request);
                            }
                            if (dragSource != null || clickedInventoryComponent != null || MiniMenu.open || component.clientcode != 1400 && worldMapState > 0) {
                                mouseClicked = false;
                                mousePressed = false;
                                mouseOver = false;
                            }
                            @Pc(508) int skill;
                            if (component.clientcode != 0) {
                                if (component.clientcode == ComponentClientCode.SCENE) {
                                    specialComponent = component;
                                    redraw(component);
                                    continue;
                                }
                                if (component.clientcode == ComponentClientCode.MINIMAP) {
                                    if (mouseClicked) {
                                        anInt5 = Mouse.mouseClickX - componentX;
                                        MiniMenu.anInt2878 = Mouse.mouseClickY - componentY;
                                    }
                                    continue;
                                }
                                if (component.clientcode == ComponentClientCode.WORLD_MAP) {
                                    WorldMap.component = component;
                                    if (mouseClicked) {
                                        if (Keyboard.pressedKeys[82] && LoginManager.staffModLevel > 0) {
                                            hotkeyIndex = (int) ((double) (Mouse.mouseClickX - componentX - component.width / 2) * 2.0D / (double) WorldMap.zoom);
                                            skill = (int) ((double) (Mouse.mouseClickY - componentY - component.height / 2) * 2.0D / (double) WorldMap.zoom);
                                            cooldownDelay = WorldMap.displayX + hotkeyIndex;
                                            @Pc(516) int local516 = WorldMap.displayZ + skill;
                                            @Pc(520) int local520 = cooldownDelay + WorldMap.originX;
                                            @Pc(528) int local528 = WorldMap.areaHeight + WorldMap.originZ - local516 - 1;
                                            Cheat.teleport(local520, local528, 0);
                                            closeModal();
                                            continue;
                                        }
                                        worldMapState = 1;
                                        ClientScriptRunner.dragStartX = Mouse.lastMouseX;
                                        dragStartY = Mouse.lastMouseY;
                                        continue;
                                    }
                                    if (mousePressed && worldMapState > 0) {
                                        if (worldMapState == 1 && (ClientScriptRunner.dragStartX != Mouse.lastMouseX || dragStartY != Mouse.lastMouseY)) {
                                            anInt4620 = WorldMap.displayX;
                                            anInt1885 = WorldMap.displayZ;
                                            worldMapState = 2;
                                        }
                                        if (worldMapState == 2) {
                                            WorldMap.setDisplayX(anInt4620 + (int) ((double) (ClientScriptRunner.dragStartX - Mouse.lastMouseX) * 2.0D / (double) WorldMap.targetZoom));
                                            WorldMap.setDisplayZ(anInt1885 + (int) ((double) (dragStartY - Mouse.lastMouseY) * 2.0D / (double) WorldMap.targetZoom));
                                        }
                                        continue;
                                    }
                                    worldMapState = 0;
                                    continue;
                                }
                                if (component.clientcode == ComponentClientCode.WORLD_MAP_OVERVIEW) {
                                    if (mousePressed) {
                                        WorldMap.clickedOverview(component.width, Mouse.lastMouseY - componentY, Mouse.lastMouseX - componentX, component.height);
                                    }
                                    continue;
                                }
                                if (component.clientcode == 1402) {
                                    if (!GlRenderer.enabled) {
                                        redraw(component);
                                    }
                                    continue;
                                }
                            }
                            if (!component.mousePressed && mouseClicked) {
                                component.mousePressed = true;
                                if (component.onClick != null) {
                                    request = new HookRequest();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.mouseClickX - componentX;
                                    request.mouseY = Mouse.mouseClickY - componentY;
                                    request.arguments = component.onClick;
                                    lowPriorityRequests.push(request);
                                }
                            }
                            if (component.mousePressed && mousePressed && component.onClickRepeat != null) {
                                request = new HookRequest();
                                request.shouldExecute = true;
                                request.source = component;
                                request.mouseX = Mouse.lastMouseX - componentX;
                                request.mouseY = Mouse.lastMouseY - componentY;
                                request.arguments = component.onClickRepeat;
                                lowPriorityRequests.push(request);
                            }
                            if (component.mousePressed && !mousePressed) {
                                component.mousePressed = false;
                                if (component.onRelease != null) {
                                    request = new HookRequest();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.lastMouseX - componentX;
                                    request.mouseY = Mouse.lastMouseY - componentY;
                                    request.arguments = component.onRelease;
                                    mediumPriorityRequests.push(request);
                                }
                            }
                            if (mousePressed && component.onHold != null) {
                                request = new HookRequest();
                                request.shouldExecute = true;
                                request.source = component;
                                request.mouseX = Mouse.lastMouseX - componentX;
                                request.mouseY = Mouse.lastMouseY - componentY;
                                request.arguments = component.onHold;
                                lowPriorityRequests.push(request);
                            }
                            if (!component.mouseHover && mouseOver) {
                                component.mouseHover = true;
                                if (component.onMouseOver != null) {
                                    request = new HookRequest();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.lastMouseX - componentX;
                                    request.mouseY = Mouse.lastMouseY - componentY;
                                    request.arguments = component.onMouseOver;
                                    lowPriorityRequests.push(request);
                                }
                            }
                            if (component.mouseHover && mouseOver && component.onMouseRepeat != null) {
                                request = new HookRequest();
                                request.shouldExecute = true;
                                request.source = component;
                                request.mouseX = Mouse.lastMouseX - componentX;
                                request.mouseY = Mouse.lastMouseY - componentY;
                                request.arguments = component.onMouseRepeat;
                                lowPriorityRequests.push(request);
                            }
                            if (component.mouseHover && !mouseOver) {
                                component.mouseHover = false;
                                if (component.onMouseLeave != null) {
                                    request = new HookRequest();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.lastMouseX - componentX;
                                    request.mouseY = Mouse.lastMouseY - componentY;
                                    request.arguments = component.onMouseLeave;
                                    mediumPriorityRequests.push(request);
                                }
                            }
                            if (component.onTimer != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onTimer;
                                highPriorityRequests.push(request);
                            }
                            @Pc(966) HookRequest request2;
                            if (component.onVarcTransmit != null && VarcDomain.varcUpdateCount > component.lastVarcUpdate) {
                                if (component.varcTriggers == null || VarcDomain.varcUpdateCount - component.lastVarcUpdate > 32) {
                                    request = new HookRequest();
                                    request.source = component;
                                    request.arguments = component.onVarcTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label563: for (hotkeyIndex = component.lastVarcUpdate; hotkeyIndex < VarcDomain.varcUpdateCount; hotkeyIndex++) {
                                        skill = VarcDomain.updatedVarcs[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.varcTriggers.length; cooldownDelay++) {
                                            if (component.varcTriggers[cooldownDelay] == skill) {
                                                request2 = new HookRequest();
                                                request2.source = component;
                                                request2.arguments = component.onVarcTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label563;
                                            }
                                        }
                                    }
                                }
                                component.lastVarcUpdate = VarcDomain.varcUpdateCount;
                            }
                            if (component.onVarcstrTransmit != null && VarcDomain.varcstrUpdateCount > component.lastVarcstrUpdate) {
                                if (component.varcstrTriggers == null || VarcDomain.varcstrUpdateCount - component.lastVarcstrUpdate > 32) {
                                    request = new HookRequest();
                                    request.source = component;
                                    request.arguments = component.onVarcstrTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label539: for (hotkeyIndex = component.lastVarcstrUpdate; hotkeyIndex < VarcDomain.varcstrUpdateCount; hotkeyIndex++) {
                                        skill = VarcDomain.updatedVarcstrs[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.varcstrTriggers.length; cooldownDelay++) {
                                            if (component.varcstrTriggers[cooldownDelay] == skill) {
                                                request2 = new HookRequest();
                                                request2.source = component;
                                                request2.arguments = component.onVarcstrTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label539;
                                            }
                                        }
                                    }
                                }
                                component.lastVarcstrUpdate = VarcDomain.varcstrUpdateCount;
                            }
                            if (component.onVarpTransmit != null && VarpDomain.varpUpdateCount > component.lastVarpUpdate) {
                                if (component.varpTriggers == null || VarpDomain.varpUpdateCount - component.lastVarpUpdate > 32) {
                                    request = new HookRequest();
                                    request.source = component;
                                    request.arguments = component.onVarpTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label515: for (hotkeyIndex = component.lastVarpUpdate; hotkeyIndex < VarpDomain.varpUpdateCount; hotkeyIndex++) {
                                        skill = VarpDomain.updatedVarps[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.varpTriggers.length; cooldownDelay++) {
                                            if (component.varpTriggers[cooldownDelay] == skill) {
                                                request2 = new HookRequest();
                                                request2.source = component;
                                                request2.arguments = component.onVarpTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label515;
                                            }
                                        }
                                    }
                                }
                                component.lastVarpUpdate = VarpDomain.varpUpdateCount;
                            }
                            if (component.onInvTransmit != null && ClientInventory.updateCount > component.lastInvUpdate) {
                                if (component.inventoryTriggers == null || ClientInventory.updateCount - component.lastInvUpdate > 32) {
                                    request = new HookRequest();
                                    request.source = component;
                                    request.arguments = component.onInvTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label491: for (hotkeyIndex = component.lastInvUpdate; hotkeyIndex < ClientInventory.updateCount; hotkeyIndex++) {
                                        skill = ClientInventory.updates[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.inventoryTriggers.length; cooldownDelay++) {
                                            if (component.inventoryTriggers[cooldownDelay] == skill) {
                                                request2 = new HookRequest();
                                                request2.source = component;
                                                request2.arguments = component.onInvTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label491;
                                            }
                                        }
                                    }
                                }
                                component.lastInvUpdate = ClientInventory.updateCount;
                            }
                            if (component.onStatTransmit != null && Component.statUpdateCount > component.lastStatUpdate) {
                                if (component.statTriggers == null || Component.statUpdateCount - component.lastStatUpdate > 32) {
                                    request = new HookRequest();
                                    request.source = component;
                                    request.arguments = component.onStatTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label467: for (hotkeyIndex = component.lastStatUpdate; hotkeyIndex < Component.statUpdateCount; hotkeyIndex++) {
                                        skill = PlayerSkillXpTable.updatedStats[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.statTriggers.length; cooldownDelay++) {
                                            if (component.statTriggers[cooldownDelay] == skill) {
                                                request2 = new HookRequest();
                                                request2.source = component;
                                                request2.arguments = component.onStatTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label467;
                                            }
                                        }
                                    }
                                }
                                component.lastStatUpdate = Component.statUpdateCount;
                            }
                            if (ChatHistory.transmitAt > component.lastTransmitTimer && component.onChatTransmit != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onChatTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (FriendList.transmitAt > component.lastTransmitTimer && component.onFriendTransmit != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onFriendTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (ClanChat.transmitAt > component.lastTransmitTimer && component.onClanTransmit != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onClanTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (StockMarketManager.transmitAt > component.lastTransmitTimer && component.onStockTransmit != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onStockTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (miscTransmitAt > component.lastTransmitTimer && component.onMiscTransmit != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onMiscTransmit;
                                lowPriorityRequests.push(request);
                            }
                            component.lastTransmitTimer = transmitTimer;
                            if (component.onKey != null) {
                                for (hotkeyIndex = 0; hotkeyIndex < keyQueueSize; hotkeyIndex++) {
                                    @Pc(1430) HookRequest local1430 = new HookRequest();
                                    local1430.source = component;
                                    local1430.keyCode = keyCodes[hotkeyIndex];
                                    local1430.keyChar = keyChars[hotkeyIndex];
                                    local1430.arguments = component.onKey;
                                    lowPriorityRequests.push(local1430);
                                }
                            }
                            if (Camera.splineFinished && component.onCamFinished != null) {
                                request = new HookRequest();
                                request.source = component;
                                request.arguments = component.onCamFinished;
                                lowPriorityRequests.push(request);
                            }
                        }
                    }
                    if (!component.hasOpKey && dragSource == null && clickedInventoryComponent == null && !MiniMenu.open) {
                        if ((component.anInt470 >= 0 || component.overColor != 0) && Mouse.lastMouseX >= effectiveClipLeft && Mouse.lastMouseY >= effectiveClipTop && Mouse.lastMouseX < effectiveClipRight && Mouse.lastMouseY < effectiveClipBottom) {
                            if (component.anInt470 >= 0) {
                                previousMouseOverComponent = components[component.anInt470];
                            } else {
                                previousMouseOverComponent = component;
                            }
                        }
                        if (component.type == 8 && Mouse.lastMouseX >= effectiveClipLeft && Mouse.lastMouseY >= effectiveClipTop && Mouse.lastMouseX < effectiveClipRight && Mouse.lastMouseY < effectiveClipBottom) {
                            Protocol.dragHoverComponent = component;
                        }
                        if (component.scrollHeight > component.height) {
                            handleScrollbar(Mouse.lastMouseY, component.height, component, Mouse.lastMouseX, componentX + component.width, componentY, component.scrollHeight);
                        }
                    }
                    if (component.type == 0) {
                        logicComponentList(components, component.slot, effectiveClipLeft, effectiveClipTop, effectiveClipRight, effectiveClipBottom, componentX - component.scrollX, componentY - component.scrollY);
                        if (component.staticComponents != null) {
                            logicComponentList(component.staticComponents, component.slot, effectiveClipLeft, effectiveClipTop, effectiveClipRight, effectiveClipBottom, componentX - component.scrollX, componentY - component.scrollY);
                        }
                        @Pc(1595) SubInterface subInterface = (SubInterface) subInterfaces.get((long) component.slot);
                        if (subInterface != null) {
                            renderInterface(componentX, effectiveClipTop, componentY, effectiveClipRight, subInterface.interfaceId, effectiveClipLeft, effectiveClipBottom);
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!tc", name = "a", descriptor = "(IILclient!be;BIIII)V")
    public static void handleScrollbar(@OriginalArg(0) int mouseY, @OriginalArg(1) int scrollbarHeight, @OriginalArg(2) Component component, @OriginalArg(4) int mouseX, @OriginalArg(5) int scrollbarX, @OriginalArg(6) int scrollbarY, @OriginalArg(7) int contentHeight) {
        if (aBoolean84) {
            anInt1396 = 32;
        } else {
            anInt1396 = 0;
        }
        aBoolean84 = false;
        @Pc(139) int thumbHeight;
        if (Mouse.pressedButton != 0) {
            if (scrollbarX <= mouseX && scrollbarX + 16 > mouseX && mouseY >= scrollbarY && scrollbarY + 16 > mouseY) {
                component.scrollY -= 4;
                redraw(component);
            } else if (mouseX >= scrollbarX && mouseX < scrollbarX + 16 && mouseY >= scrollbarHeight + scrollbarY - 16 && scrollbarHeight + scrollbarY > mouseY) {
                component.scrollY += 4;
                redraw(component);
            } else if (mouseX >= scrollbarX - anInt1396 && mouseX < scrollbarX + anInt1396 + 16 && mouseY >= scrollbarY + 16 && scrollbarHeight + scrollbarY - 16 > mouseY) {
                thumbHeight = scrollbarHeight * (scrollbarHeight - 32) / contentHeight;
                if (thumbHeight < 8) {
                    thumbHeight = 8;
                }
                @Pc(150) int trackHeight = scrollbarHeight - thumbHeight - 32;
                @Pc(162) int thumbOffset = mouseY - thumbHeight / 2 - scrollbarY - 16;
                component.scrollY = (contentHeight - scrollbarHeight) * thumbOffset / trackHeight;
                redraw(component);
                aBoolean84 = true;
            }
        }
        if (MouseWheel.wheelRotation == 0) {
            return;
        }
        thumbHeight = component.width;
        if (scrollbarX - thumbHeight <= mouseX && scrollbarY <= mouseY && mouseX < scrollbarX + 16 && scrollbarHeight + scrollbarY >= mouseY) {
            component.scrollY += MouseWheel.wheelRotation * 45;
            redraw(component);
        }
    }

    @OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "(IBIII)V")
    public static void forceRedrawScreen(@OriginalArg(0) int x, @OriginalArg(2) int y, @OriginalArg(3) int height, @OriginalArg(4) int width) {
        for (@Pc(3) int rectangleIndex = 0; rectangleIndex < rectangles; rectangleIndex++) {
            if (x < rectangleX[rectangleIndex] + rectangleWidth[rectangleIndex] && x + width > rectangleX[rectangleIndex] && rectangleY[rectangleIndex] + rectangleHeight[rectangleIndex] > y && rectangleY[rectangleIndex] < height + y) {
                rectangleRedraw[rectangleIndex] = true;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!hh", name = "a", descriptor = "(II)V")
    public static void updateInterfaceState(@OriginalArg(1) int interfaceId) {
        if (InterfaceList.load(interfaceId)) {
            updateComponentState(-1, InterfaceList.interfaces[interfaceId]);
        }
    }

    @OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "(Lclient!be;)Lclient!be;")
    public static Component canAcceptDrop(@OriginalArg(0) Component component) {
        @Pc(4) int dragDepth = getServerActiveProperties(component).getDragDepth();
        if (dragDepth == 0) {
            return null;
        }
        for (@Pc(10) int level = 0; level < dragDepth; level++) {
            component = InterfaceList.list(component.layer);
            if (component == null) {
                return null;
            }
        }
        return component;
    }

    @OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(II[Lclient!be;)V")
    public static void updateComponentState(@OriginalArg(1) int parentId, @OriginalArg(2) Component[] components) {
        for (@Pc(7) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(15) Component component = components[componentIndex];
            if (component != null && component.layer == parentId && (!component.hasOpKey || !isHidden(component))) {
                if (component.type == 0) {
                    if (!component.hasOpKey && isHidden(component) && component != previousMouseOverComponent) {
                        continue;
                    }
                    updateComponentState(component.slot, components);
                    if (component.staticComponents != null) {
                        updateComponentState(component.slot, component.staticComponents);
                    }
                    @Pc(73) SubInterface subInterface = (SubInterface) subInterfaces.get((long) component.slot);
                    if (subInterface != null) {
                        updateInterfaceState(subInterface.interfaceId);
                    }
                }
                if (component.type == 6) {
                    @Pc(105) int sequenceId;
                    if (component.modelSeqId != -1 || component.activeModelSeqId != -1) {
                        @Pc(100) boolean isActive = ClientScriptRunner.isTrue(component);
                        if (isActive) {
                            sequenceId = component.activeModelSeqId;
                        } else {
                            sequenceId = component.modelSeqId;
                        }
                        if (sequenceId != -1) {
                            @Pc(118) SeqType sequence = SeqTypeList.get(sequenceId);
                            if (sequence != null) {
                                component.animationDelay += Protocol.sceneDelta;
                                while (component.animationDelay > sequence.frameDelay[component.animationId]) {
                                    component.animationDelay -= sequence.frameDelay[component.animationId];
                                    component.animationId++;
                                    if (sequence.frames.length <= component.animationId) {
                                        component.animationId -= sequence.replayOff;
                                        if (component.animationId < 0 || sequence.frames.length <= component.animationId) {
                                            component.animationId = 0;
                                        }
                                    }
                                    component.animationFrame = component.animationId + 1;
                                    if (sequence.frames.length <= component.animationFrame) {
                                        component.animationFrame -= sequence.replayOff;
                                        if (component.animationFrame < 0 || sequence.frames.length <= component.animationFrame) {
                                            component.animationFrame = -1;
                                        }
                                    }
                                    redraw(component);
                                }
                            }
                        }
                    }
                    if (component.modelRotationSpeed != 0 && !component.hasOpKey) {
                        @Pc(239) int xRotationRate = component.modelRotationSpeed >> 16;
                        @Pc(243) int xRotationDelta = xRotationRate * Protocol.sceneDelta;
                        sequenceId = component.modelRotationSpeed << 16 >> 16;
                        component.modelXAngle = xRotationDelta + component.modelXAngle & 0x7FF;
                        sequenceId *= Protocol.sceneDelta;
                        component.modelYAngle = component.modelYAngle + sequenceId & 0x7FF;
                        redraw(component);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "client!mc", name = "f", descriptor = "(B)V")
    public static void closeModal() {
        Protocol.outboundBuffer.pIsaac1(CLOSE_MODAL);
        for (@Pc(18) SubInterface subInterface = (SubInterface) subInterfaces.head(); subInterface != null; subInterface = (SubInterface) subInterfaces.next()) {
            if (subInterface.modalType == 0) {
                closeInterface(true, subInterface);
            }
        }
        if (ClientScriptRunner.modalBackgroundComponent != null) {
            redraw(ClientScriptRunner.modalBackgroundComponent);
            ClientScriptRunner.modalBackgroundComponent = null;
        }
    }

    @OriginalMember(owner = "client!gn", name = "a", descriptor = "(III[Lclient!be;IIIIBI)V")
    public static void draw(@OriginalArg(0) int clipLeft, @OriginalArg(1) int offsetY, @OriginalArg(2) int offsetX, @OriginalArg(3) Component[] children, @OriginalArg(4) int clipRight, @OriginalArg(5) int layer, @OriginalArg(6) int clipTop, @OriginalArg(7) int clipBottom, @OriginalArg(9) int boundRectangle) {
        if (GlRenderer.enabled) {
            GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
        } else {
            SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
            Rasterizer.prepare();
        }
        for (@Pc(18) int i = 0; i < children.length; i++) {
            @Pc(30) Component child = children[i];
            if (child != null && (child.layer == layer || layer == ROOT && child == dragSource)) {
                @Pc(57) int rectangle;
                if (boundRectangle == -1) {
                    rectangleX[rectangles] = offsetX + child.x;
                    rectangleY[rectangles] = child.y + offsetY;
                    rectangleWidth[rectangles] = child.width;
                    rectangleHeight[rectangles] = child.height;
                    rectangle = rectangles++;
                } else {
                    rectangle = boundRectangle;
                }
                child.lastUpdate = Client.loop;
                child.rectangle = rectangle;
                if (!child.hasOpKey || !isHidden(child)) {
                    if (child.clientcode > 0) {
                        updateGenderDependentComponents(child);
                    }
                    @Pc(114) int renderY = offsetY + child.y;
                    @Pc(117) int transparency = child.transparency;
                    @Pc(123) int renderX = child.x + offsetX;
                    if (Cheat.testOpacity && (getServerActiveProperties(child).events != 0 || child.type == Component.TYPE_LAYER) && transparency > 127) {
                        transparency = 127;
                    }
                    @Pc(166) int clipLeft2;
                    @Pc(164) int clipTop2;
                    if (child == dragSource) {
                        if (layer != ROOT && !child.dragRenderBehavior) {
                            dragOffsetX = offsetX;
                            dragOffsetY = offsetY;
                            dragChildren = children;
                            continue;
                        }
                        if (isDragging && dragActive) {
                            clipTop2 = Mouse.lastMouseY;
                            clipLeft2 = Mouse.lastMouseX;
                            clipTop2 -= dragStartY;
                            if (clipTop2 < minY) {
                                clipTop2 = minY;
                            }
                            if (clipTop2 + child.height > dragLayer.height + minY) {
                                clipTop2 = dragLayer.height + minY - child.height;
                            }
                            renderY = clipTop2;
                            clipLeft2 -= ClientScriptRunner.dragStartX;
                            if (ClientScriptRunner.minX > clipLeft2) {
                                clipLeft2 = ClientScriptRunner.minX;
                            }
                            if (dragLayer.width + ClientScriptRunner.minX < child.width + clipLeft2) {
                                clipLeft2 = dragLayer.width + ClientScriptRunner.minX - child.width;
                            }
                            renderX = clipLeft2;
                        }
                        if (!child.dragRenderBehavior) {
                            transparency = 128;
                        }
                    }
                    @Pc(302) int clipRight3;
                    @Pc(291) int clipBottom3;
                    @Pc(270) int clipRight2;
                    @Pc(276) int clipBottom2;
                    if (child.type == Component.TYPE_INVENTORY) {
                        clipBottom3 = clipBottom;
                        clipRight3 = clipRight;
                        clipTop2 = clipTop;
                        clipLeft2 = clipLeft;
                    } else {
                        clipTop2 = renderY > clipTop ? renderY : clipTop;
                        clipLeft2 = clipLeft < renderX ? renderX : clipLeft;
                        clipRight2 = child.width + renderX;
                        clipBottom2 = renderY + child.height;
                        if (child.type == Component.TYPE_LINE) {
                            clipBottom2++;
                            clipRight2++;
                        }
                        clipBottom3 = clipBottom <= clipBottom2 ? clipBottom : clipBottom2;
                        clipRight3 = clipRight2 >= clipRight ? clipRight : clipRight2;
                    }
                    if (!child.hasOpKey || clipRight3 > clipLeft2 && clipTop2 < clipBottom3) {
                        @Pc(468) int temp;
                        @Pc(503) int tempValue;
                        @Pc(514) int colorValue;
                        @Pc(518) int gpuMemory;
                        @Pc(556) int dragOffsetY;
                        @Pc(563) int tileX;
                        @Pc(571) int tileZ;
                        @Pc(545) int objId;
                        if (child.clientcode != 0) {
                            if (child.clientcode == ComponentClientCode.SCENE || child.clientcode == ComponentClientCode.LOGIN_SCENE && GlRenderer.enabled) {
                                specialComponent = child;
                                anInt5574 = renderY;
                                ClientScriptRunner.anInt2503 = renderX;
                                SceneGraph.drawScene(child.height, child.clientcode == ComponentClientCode.LOGIN_SCENE, renderX, child.width, renderY);
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                continue;
                            }
                            if (child.clientcode == ComponentClientCode.MINIMAP) {
                                if (!child.graphic()) {
                                    continue;
                                }
                                MiniMap.draw(rectangle, renderY, renderX, child);
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                if (MiniMap.toggle != 0 && MiniMap.toggle != 3 || MiniMenu.open || clipLeft2 > ClientScriptRunner.scriptMouseX || ClientScriptRunner.scriptMouseY < clipTop2 || ClientScriptRunner.scriptMouseX >= clipRight3 || clipBottom3 <= ClientScriptRunner.scriptMouseY) {
                                    continue;
                                }
                                clipRight2 = ClientScriptRunner.scriptMouseX - renderX;
                                clipBottom2 = ClientScriptRunner.scriptMouseY - renderY;
                                temp = child.compassPixelOffsets[clipBottom2];
                                if (clipRight2 < temp || clipRight2 > temp + child.compassPixelWidths[clipBottom2]) {
                                    continue;
                                }
                                clipBottom2 -= child.height / 2;
                                tempValue = Camera.orbitCameraYaw + MiniMap.minimapAnticheatAngle & 0x7FF;
                                clipRight2 -= child.width / 2;
                                colorValue = MathUtils.sin[tempValue];
                                gpuMemory = MathUtils.cos[tempValue];
                                colorValue = (MiniMap.minimapZoom + 256) * colorValue >> 8;
                                gpuMemory = (MiniMap.minimapZoom + 256) * gpuMemory >> 8;
                                objId = gpuMemory * clipBottom2 - colorValue * clipRight2 >> 11;
                                dragOffsetY = clipBottom2 * colorValue + clipRight2 * gpuMemory >> 11;
                                tileX = PlayerList.self.xFine + dragOffsetY >> 7;
                                tileZ = PlayerList.self.zFine - objId >> 7;
                                if (targetMode && (useWithMask & 0x40) != 0) {
                                    @Pc(583) Component local583 = InterfaceList.getComponent(MiniMenu.useWithComponentId, MiniMenu.useWithSlot);
                                    if (local583 == null) {
                                        endTargetMode();
                                    } else {
                                        MiniMenu.addActionRow(useWithCursor, 1L, MiniMenu.aClass100_961, tileX, (short) 11, aClass100_545, tileZ);
                                    }
                                    continue;
                                }
                                if (Client.game == 1) {
                                    MiniMenu.addActionRow(-1, 1L, JString.EMPTY, tileX, (short) 36, LocalizedText.FACEHERE, tileZ);
                                }
                                MiniMenu.addActionRow(-1, 1L, JString.EMPTY, tileX, (short) 60, MiniMenu.walkText, tileZ);
                                continue;
                            }
                            if (child.clientcode == ComponentClientCode.COMPASS) {
                                if (child.graphic()) {
                                    MiniMap.drawCompass(renderX, renderY, child, rectangle);
                                    if (GlRenderer.enabled) {
                                        GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                    } else {
                                        SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                    }
                                }
                                continue;
                            }
                            if (child.clientcode == ComponentClientCode.WORLD_MAP) {
                                WorldMap.draw(renderX, renderY, child.height, child.width);
                                componentNeedsRedraw[rectangle] = true;
                                rectangleRedraw[rectangle] = true;
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                continue;
                            }
                            if (child.clientcode == ComponentClientCode.WORLD_MAP_OVERVIEW) {
                                WorldMap.renderWorldMap(renderX, child.height, child.width, renderY);
                                componentNeedsRedraw[rectangle] = true;
                                rectangleRedraw[rectangle] = true;
                                if (GlRenderer.enabled) {
                                    GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                } else {
                                    SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                }
                                continue;
                            }
                            if (child.clientcode == ComponentClientCode.FLAMES_OVERLAY) {
                                if (!GlRenderer.enabled) {
                                    Flames.render(renderX, renderY);
                                    componentNeedsRedraw[rectangle] = true;
                                    rectangleRedraw[rectangle] = true;
                                }
                                continue;
                            }
                            if (child.clientcode == ComponentClientCode.DEBUG_OVERLAY) {
                                if (!Cheat.displayFps) {
                                    continue;
                                }
                                clipRight2 = child.width + renderX;
                                clipBottom2 = renderY + 15;
                                Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_FPS2, JString.parseInt(GameShell.fps) }), clipRight2, clipBottom2, 16776960, -1);
                                clipBottom2 += 15;
                                @Pc(795) Runtime runtime = Runtime.getRuntime();
                                tempValue = (int) ((runtime.totalMemory() - runtime.freeMemory()) / 1024L);
                                colorValue = 16776960;
                                if (tempValue > 65536) {
                                    colorValue = 16711680;
                                }
                                Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_MEM, JString.parseInt(tempValue), Cheat.DEBUG_MEM_UNIT}), clipRight2, clipBottom2, colorValue, -1);
                                clipBottom2 += 15;
                                if (GlRenderer.enabled) {
                                    colorValue = 16776960;
                                    gpuMemory = (GlCleaner.oncard_texture + GlCleaner.oncard_geometry + GlCleaner.oncard_2d) / 1024;
                                    if (gpuMemory > 65536) {
                                        colorValue = 16711680;
                                    }
                                    Fonts.p12Full.renderRight(JString.concatenate(new JString[] { Cheat.DEBUG_CARD, JString.parseInt(gpuMemory), Cheat.DEBUG_MEM_UNIT}), clipRight2, clipBottom2, colorValue, -1);
                                    clipBottom2 += 15;
                                }
                                gpuMemory = 0;
                                objId = 0;
                                dragOffsetY = 0;
                                for (tileX = 0; tileX < 28; tileX++) {
                                    gpuMemory += Client.js5Providers[tileX].getIndexSize();
                                    dragOffsetY += Client.js5Providers[tileX].getVerifiedGroups();
                                    objId += Client.js5Providers[tileX].getTotalVerifiedGroups();
                                }
                                tileZ = dragOffsetY * 10000 / gpuMemory;
                                tileX = objId * 100 / gpuMemory;
                                @Pc(968) JString local968 = JString.concatenate(new JString[] { Cheat.DEBUG_CACHE, StringUtils.formatNumber(0, true, 2, (long) tileZ), ClientScriptRunner.TYPE_SIG_UNION_X, JString.parseInt(tileX), ClientScriptRunner.TYPE_SIG_UNION_Y});
                                Fonts.p11Full.renderRight(local968, clipRight2, clipBottom2, 16776960, -1);
                                clipBottom2 += 12;
                                componentNeedsRedraw[rectangle] = true;
                                rectangleRedraw[rectangle] = true;
                                continue;
                            }
                            if (child.clientcode == 1406) {
                                ClientScriptRunner.anInt3484 = renderY;
                                LoginManager.hoveredComponent = child;
                                ClientScriptRunner.anInt3260 = renderX;
                                continue;
                            }
                        }
                        if (!MiniMenu.open) {
                            if (child.type == Component.TYPE_LAYER && child.noClickThrough && ClientScriptRunner.scriptMouseX >= clipLeft2 && ClientScriptRunner.scriptMouseY >= clipTop2 && ClientScriptRunner.scriptMouseX < clipRight3 && clipBottom3 > ClientScriptRunner.scriptMouseY && !Cheat.testOpacity) {
                                MiniMenu.menuActionRow = 1;
                                cursors[0] = defaultCursor;
                                ops[0] = LocalizedText.CANCEL;
                                opBases[0] = JString.EMPTY;
                                actions[0] = 1005;
                            }
                            if (clipLeft2 <= ClientScriptRunner.scriptMouseX && clipTop2 <= ClientScriptRunner.scriptMouseY && clipRight3 > ClientScriptRunner.scriptMouseX && clipBottom3 > ClientScriptRunner.scriptMouseY) {
                                addMiniMenuOptions(ClientScriptRunner.scriptMouseY - renderY, -renderX + ClientScriptRunner.scriptMouseX, child);
                            }
                        }
                        if (child.type == Component.TYPE_LAYER) {
                            if (!child.hasOpKey && isHidden(child) && previousMouseOverComponent != child) {
                                continue;
                            }
                            if (!child.hasOpKey) {
                                if (child.scrollHeight - child.height < child.scrollY) {
                                    child.scrollY = child.scrollHeight - child.height;
                                }
                                if (child.scrollY < 0) {
                                    child.scrollY = 0;
                                }
                            }
                            draw(clipLeft2, renderY - child.scrollY, -child.scrollX + renderX, children, clipRight3, child.slot, clipTop2, clipBottom3, rectangle);
                            if (child.staticComponents != null) {
                                draw(clipLeft2, renderY - child.scrollY, -child.scrollX + renderX, child.staticComponents, clipRight3, child.slot, clipTop2, clipBottom3, rectangle);
                            }
                            @Pc(1186) SubInterface local1186 = (SubInterface) subInterfaces.get((long) child.slot);
                            if (local1186 != null) {
                                if (local1186.modalType == 0 && !MiniMenu.open && ClientScriptRunner.scriptMouseX >= clipLeft2 && clipTop2 <= ClientScriptRunner.scriptMouseY && clipRight3 > ClientScriptRunner.scriptMouseX && ClientScriptRunner.scriptMouseY < clipBottom3 && !Cheat.testOpacity) {
                                    ops[0] = LocalizedText.CANCEL;
                                    MiniMenu.menuActionRow = 1;
                                    cursors[0] = defaultCursor;
                                    actions[0] = 1005;
                                    opBases[0] = JString.EMPTY;
                                }
                                ClientScriptRunner.renderOrInvalidateComponent(local1186.interfaceId, clipLeft2, clipRight3, renderX, rectangle, clipBottom3, clipTop2, renderY);
                            }
                            if (GlRenderer.enabled) {
                                GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                            } else {
                                SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                Rasterizer.prepare();
                            }
                        }
                        if (componentRedrawPrevious[rectangle] || Cheat.rectDebug > 1) {
                            if (child.type == 0 && !child.hasOpKey && child.scrollHeight > child.height) {
                                drawScrollbar(child.scrollY, child.scrollHeight, child.width + renderX, renderY, child.height);
                            }
                            if (child.type != 1) {
                                if (child.type == Component.TYPE_INVENTORY) {
                                    clipRight2 = 0;
                                    for (clipBottom2 = 0; clipBottom2 < child.baseHeight; clipBottom2++) {
                                        for (temp = 0; temp < child.baseWidth; temp++) {
                                            colorValue = renderY + clipBottom2 * (child.invMarginY + 32);
                                            tempValue = (child.invMarginX + 32) * temp + renderX;
                                            if (clipRight2 < 20) {
                                                colorValue += child.invOffsetY[clipRight2];
                                                tempValue += child.invOffsetX[clipRight2];
                                            }
                                            if (child.invSlotObjId[clipRight2] > 0) {
                                                objId = child.invSlotObjId[clipRight2] - 1;
                                                if (clipLeft < tempValue + 32 && tempValue < clipRight && clipTop < colorValue + 32 && colorValue < clipBottom || child == clickedInventoryComponent && selectedInventorySlot == clipRight2) {
                                                    @Pc(1476) Sprite sprite;
                                                    if (anInt5014 == 1 && anInt4370 == clipRight2 && child.slot == MiniMap.anInt5062) {
                                                        sprite = ClientInventory.getObjectSprite(2, objId, child.objDrawText, child.invSlotObjCount[clipRight2], 0);
                                                    } else {
                                                        sprite = ClientInventory.getObjectSprite(1, objId, child.objDrawText, child.invSlotObjCount[clipRight2], 3153952);
                                                    }
                                                    if (Rasterizer.textureHasTransparency) {
                                                        componentNeedsRedraw[rectangle] = true;
                                                    }
                                                    if (sprite == null) {
                                                        redraw(child);
                                                    } else if (clickedInventoryComponent == child && clipRight2 == selectedInventorySlot) {
                                                        gpuMemory = Mouse.lastMouseX - clickedInventoryComponentX;
                                                        dragOffsetY = Mouse.lastMouseY - clickedInventoryComponentY;
                                                        if (dragOffsetY < 5 && dragOffsetY > -5) {
                                                            dragOffsetY = 0;
                                                        }
                                                        if (gpuMemory < 5 && gpuMemory > -5) {
                                                            gpuMemory = 0;
                                                        }
                                                        if (lastItemDragTime < 5) {
                                                            gpuMemory = 0;
                                                            dragOffsetY = 0;
                                                        }
                                                        // draw dragged icon (at half opacity)
                                                        sprite.renderAlpha(tempValue + gpuMemory, colorValue - -dragOffsetY, 128);
                                                        if (layer != -1) {
                                                            @Pc(1571) Component local1571 = children[layer & 0xFFFF];
                                                            @Pc(1577) int top;
                                                            @Pc(1575) int bottom;
                                                            if (GlRenderer.enabled) {
                                                                bottom = GlRaster.clipBottom;
                                                                top = GlRaster.clipTop;
                                                            } else {
                                                                top = SoftwareRenderer.clipTop;
                                                                bottom = SoftwareRenderer.clipBottom;
                                                            }
                                                            @Pc(1611) int local1611;
                                                            if (top > dragOffsetY + colorValue && local1571.scrollY > 0) {
                                                                local1611 = Protocol.sceneDelta * (top - dragOffsetY - colorValue) / 3;
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
                                                            if (bottom < dragOffsetY + colorValue + 32 && local1571.scrollY < local1571.scrollHeight - local1571.height) {
                                                                local1611 = (colorValue + dragOffsetY + 32 - bottom) * Protocol.sceneDelta / 3;
                                                                if (local1611 > Protocol.sceneDelta * 10) {
                                                                    local1611 = Protocol.sceneDelta * 10;
                                                                }
                                                                if (local1571.scrollHeight - local1571.scrollY - local1571.height < local1611) {
                                                                    local1611 = local1571.scrollHeight - local1571.height - local1571.scrollY;
                                                                }
                                                                local1571.scrollY += local1611;
                                                                clickedInventoryComponentY -= local1611;
                                                                redraw(local1571);
                                                            }
                                                        }
                                                    } else if (child == MiniMenu.pressedInventoryComponent && clipRight2 == MiniMenu.anInt5444) {
                                                        sprite.renderAlpha(tempValue, colorValue, 128);
                                                    } else {
                                                        sprite.render(tempValue, colorValue);
                                                    }
                                                }
                                            } else if (child.invSprite != null && clipRight2 < 20) {
                                                @Pc(1381) Sprite local1381 = child.method482(clipRight2);
                                                if (local1381 != null) {
                                                    local1381.render(tempValue, colorValue);
                                                } else if (Component.aBoolean72) {
                                                    redraw(child);
                                                }
                                            }
                                            clipRight2++;
                                        }
                                    }
                                } else if (child.type == Component.TYPE_RECTANGLE) {
                                    if (ClientScriptRunner.isTrue(child)) {
                                        clipRight2 = child.activeColor;
                                        if (previousMouseOverComponent == child && child.anInt475 != 0) {
                                            clipRight2 = child.anInt475;
                                        }
                                    } else {
                                        clipRight2 = child.color;
                                        if (child == previousMouseOverComponent && child.overColor != 0) {
                                            clipRight2 = child.overColor;
                                        }
                                    }
                                    if (transparency == 0) {
                                        if (child.filled) {
                                            if (GlRenderer.enabled) {
                                                GlRaster.fillRect(renderX, renderY, child.width, child.height, clipRight2);
                                            } else {
                                                SoftwareRenderer.fillRect(renderX, renderY, child.width, child.height, clipRight2);
                                            }
                                        } else if (GlRenderer.enabled) {
                                            GlRaster.drawRect(renderX, renderY, child.width, child.height, clipRight2);
                                        } else {
                                            SoftwareRenderer.drawRect(renderX, renderY, child.width, child.height, clipRight2);
                                        }
                                    } else if (child.filled) {
                                        if (GlRenderer.enabled) {
                                            GlRaster.fillRectAlpha(renderX, renderY, child.width, child.height, clipRight2, 256 - (transparency & 0xFF));
                                        } else {
                                            SoftwareRenderer.fillRectAlpha(renderX, renderY, child.width, child.height, clipRight2, 256 - (transparency & 0xFF));
                                        }
                                    } else if (GlRenderer.enabled) {
                                        GlRaster.drawRectAlpha(renderX, renderY, child.width, child.height, clipRight2, 256 - (transparency & 0xFF));
                                    } else {
                                        SoftwareRenderer.drawRectAlpha(renderX, renderY, child.width, child.height, clipRight2, 256 - (transparency & 0xFF));
                                    }
                                } else {
                                    @Pc(1921) Font local1921;
                                    if (child.type == Component.TYPE_TEXT) {
                                        local1921 = child.getFont(Sprites.nameIcons);
                                        if (local1921 != null) {
                                            @Pc(1934) JString local1934 = child.text;
                                            if (ClientScriptRunner.isTrue(child)) {
                                                clipBottom2 = child.activeColor;
                                                if (previousMouseOverComponent == child && child.anInt475 != 0) {
                                                    clipBottom2 = child.anInt475;
                                                }
                                                if (child.activeText.length() > 0) {
                                                    local1934 = child.activeText;
                                                }
                                            } else {
                                                clipBottom2 = child.color;
                                                if (previousMouseOverComponent == child && child.overColor != 0) {
                                                    clipBottom2 = child.overColor;
                                                }
                                            }
                                            if (child.hasOpKey && child.invObject != -1) {
                                                @Pc(1989) ObjType local1989 = ObjTypeList.get(child.invObject);
                                                local1934 = local1989.name;
                                                if (local1934 == null) {
                                                    local1934 = MiniMenu.NULL;
                                                }
                                                if ((local1989.stackable == 1 || child.invCount != 1) && child.invCount != -1) {
                                                    local1934 = JString.concatenate(new JString[] { COLOR_LIGHT_ORANGE, local1934, JString.aClass100_375, StringUtils.formatNumber(child.invCount) });
                                                }
                                            }
                                            if (ClientScriptRunner.modalBackgroundComponent == child) {
                                                clipBottom2 = child.color;
                                                local1934 = LocalizedText.PLEASEWAIT;
                                            }
                                            if (!child.hasOpKey) {
                                                local1934 = JString.processStringTokens(child, local1934);
                                            }
                                            local1921.drawInterfaceText(local1934, renderX, renderY, child.width, child.height, clipBottom2, child.textShadow ? 0 : -1, child.halign, child.valign, child.vpadding);
                                        } else if (Component.aBoolean72) {
                                            redraw(child);
                                        }
                                    } else if (child.type == Component.TYPE_GRAPHIC) {
                                        @Pc(2094) Sprite sprite;
                                        if (child.hasOpKey) {
                                            if (child.invObject == -1) {
                                                sprite = child.method489(false);
                                            } else {
                                                sprite = ClientInventory.getObjectSprite(child.outlineThickness, child.invObject, child.objDrawText, child.invCount, child.graphicShadow);
                                            }
                                            if (sprite != null) {
                                                clipBottom2 = sprite.innerWidth;
                                                temp = sprite.innerHeight;
                                                if (child.spriteTiling) {
                                                    tempValue = (clipBottom2 + child.width - 1) / clipBottom2;
                                                    colorValue = (child.height + temp - 1) / temp;
                                                    if (GlRenderer.enabled) {
                                                        GlRaster.method1183(renderX, renderY, child.width + renderX, child.height + renderY);
                                                        @Pc(2274) boolean local2274 = IntMath.isPowerOfTwo(sprite.width);
                                                        @Pc(2279) boolean local2279 = IntMath.isPowerOfTwo(sprite.height);
                                                        @Pc(2282) GlSprite local2282 = (GlSprite) sprite;
                                                        if (local2274 && local2279) {
                                                            if (transparency == 0) {
                                                                local2282.method1429(renderX, renderY, tempValue, colorValue);
                                                            } else {
                                                                local2282.method1426(renderX, renderY, 256 - (transparency & 0xFF), tempValue, colorValue);
                                                            }
                                                        } else if (local2274) {
                                                            for (tileX = 0; tileX < colorValue; tileX++) {
                                                                if (transparency == 0) {
                                                                    local2282.method1429(renderX, tileX * temp + renderY, tempValue, 1);
                                                                } else {
                                                                    local2282.method1426(renderX, renderY + tileX * temp, -(transparency & 0xFF) + 256, tempValue, 1);
                                                                }
                                                            }
                                                        } else if (local2279) {
                                                            for (tileX = 0; tileX < tempValue; tileX++) {
                                                                if (transparency == 0) {
                                                                    local2282.method1429(clipBottom2 * tileX + renderX, renderY, 1, colorValue);
                                                                } else {
                                                                    local2282.method1426(clipBottom2 * tileX + renderX, renderY, 256 - (transparency & 0xFF), 1, colorValue);
                                                                }
                                                            }
                                                        } else {
                                                            for (tileX = 0; tileX < tempValue; tileX++) {
                                                                for (tileZ = 0; tileZ < colorValue; tileZ++) {
                                                                    if (transparency == 0) {
                                                                        sprite.render(renderX + clipBottom2 * tileX, temp * tileZ + renderY);
                                                                    } else {
                                                                        sprite.renderAlpha(tileX * clipBottom2 + renderX, temp * tileZ + renderY, 256 - (transparency & 0xFF));
                                                                    }
                                                                }
                                                            }
                                                        }
                                                        GlRaster.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                                    } else {
                                                        SoftwareRenderer.intersectClipBounds(renderX, renderY, renderX + child.width, renderY - -child.height);
                                                        for (gpuMemory = 0; gpuMemory < tempValue; gpuMemory++) {
                                                            for (dragOffsetY = 0; dragOffsetY < colorValue; dragOffsetY++) {
                                                                if (child.angle2d != 0) {
                                                                    sprite.renderAngled(renderY + temp * dragOffsetY + temp / 2, child.angle2d, 4096, gpuMemory * clipBottom2 + renderX + clipBottom2 / 2);
                                                                } else if (transparency == 0) {
                                                                    sprite.render(gpuMemory * clipBottom2 + renderX, temp * dragOffsetY + renderY);
                                                                } else {
                                                                    sprite.renderAlpha(gpuMemory * clipBottom2 + renderX, renderY + dragOffsetY * temp, 256 - (transparency & 0xFF));
                                                                }
                                                            }
                                                        }
                                                        SoftwareRenderer.setClip(clipLeft, clipTop, clipRight, clipBottom);
                                                    }
                                                } else {
                                                    tempValue = child.width * 4096 / clipBottom2;
                                                    if (child.angle2d != 0) {
                                                        sprite.renderAngled(renderY + child.height / 2, child.angle2d, tempValue, renderX + child.width / 2);
                                                    } else if (transparency != 0) {
                                                        sprite.renderAlpha(renderX, renderY, child.width, child.height, 256 - (transparency & 0xFF));
                                                    } else if (clipBottom2 == child.width && temp == child.height) {
                                                        sprite.render(renderX, renderY);
                                                    } else {
                                                        // render icons in a container i.e bank icons
                                                        sprite.renderResized(renderX, renderY, child.width, child.height);
                                                    }
                                                }
                                            } else if (Component.aBoolean72) {
                                                redraw(child);
                                            }
                                        } else {
                                            sprite = child.method489(ClientScriptRunner.isTrue(child));
                                            if (sprite != null) {
                                                sprite.render(renderX, renderY);
                                            } else if (Component.aBoolean72) {
                                                redraw(child);
                                            }
                                        }
                                    } else {
                                        @Pc(2611) ObjType local2611;
                                        if (child.type == Component.TYPE_MODEL) {
                                            @Pc(2587) boolean local2587 = ClientScriptRunner.isTrue(child);
                                            @Pc(2589) Model local2589 = null;
                                            if (local2587) {
                                                clipBottom2 = child.activeModelSeqId;
                                            } else {
                                                clipBottom2 = child.modelSeqId;
                                            }
                                            tempValue = 0;
                                            if (child.invObject != -1) {
                                                local2611 = ObjTypeList.get(child.invObject);
                                                if (local2611 != null) {
                                                    local2611 = local2611.getMeshAddress(child.invCount);
                                                    @Pc(2630) SeqType local2630 = clipBottom2 == -1 ? null : SeqTypeList.get(clipBottom2);
                                                    local2589 = local2611.getModel(child.animationFrame, child.animationDelay, local2630, 1, child.animationId);
                                                    if (local2589 == null) {
                                                        redraw(child);
                                                    } else {
                                                        tempValue = -local2589.getMinY() / 2;
                                                    }
                                                }
                                            } else if (child.modelType == 5) {
                                                if (child.modelId == -1) {
                                                    local2589 = PlayerAppearance.DEFAULT.createAnimatedBodyModel(null, -1, null, null, 0, -1, 0, -1, -1);
                                                } else {
                                                    colorValue = child.modelId & 0x7FF;
                                                    if (colorValue == PlayerList.localPid) {
                                                        colorValue = 2047;
                                                    }
                                                    @Pc(2751) Player local2751 = PlayerList.players[colorValue];
                                                    @Pc(2760) SeqType local2760 = clipBottom2 == -1 ? null : SeqTypeList.get(clipBottom2);
                                                    if (local2751 != null && (int) local2751.username.encode37() << 11 == (child.modelId & 0xFFFFF800)) {
                                                        local2589 = local2751.playerModel.createAnimatedBodyModel(null, -1, null, local2760, 0, -1, 0, child.animationId, 0);
                                                    }
                                                }
                                            } else if (clipBottom2 == -1) {
                                                local2589 = child.method488(-1, null, -1, 0, local2587, PlayerList.self.playerModel);
                                                if (local2589 == null && Component.aBoolean72) {
                                                    redraw(child);
                                                }
                                            } else {
                                                @Pc(2689) SeqType local2689 = SeqTypeList.get(clipBottom2);
                                                local2589 = child.method488(child.animationFrame, local2689, child.animationId, child.animationDelay, local2587, PlayerList.self.playerModel);
                                                if (local2589 == null && Component.aBoolean72) {
                                                    redraw(child);
                                                }
                                            }
                                            if (local2589 != null) {
                                                if (child.maxModelSize > 0) {
                                                    colorValue = (child.width << 8) / child.maxModelSize;
                                                } else {
                                                    colorValue = 256;
                                                }
                                                if (child.anInt526 <= 0) {
                                                    gpuMemory = 256;
                                                } else {
                                                    gpuMemory = (child.height << 8) / child.anInt526;
                                                }
                                                dragOffsetY = renderX + child.width / 2 + (colorValue * child.anInt495 >> 8);
                                                objId = child.height / 2 + renderY + (gpuMemory * child.anInt481 >> 8);
                                                if (GlRenderer.enabled) {
                                                    if (child.modelOrtho) {
                                                        GlRenderer.method4182(dragOffsetY, objId, child.modelZoom, child.aShort11, colorValue, gpuMemory);
                                                    } else {
                                                        GlRenderer.method4148(dragOffsetY, objId, colorValue, gpuMemory);
                                                        GlRenderer.method4152((float) child.aShort10, (float) child.aShort11 * 1.5F);
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
                                                    if (child.aBoolean34) {
                                                        GlRenderer.disableDepthMask();
                                                    }
                                                    tileX = MathUtils.sin[child.modelXAngle] * child.modelZoom >> 16;
                                                    tileZ = child.modelZoom * MathUtils.cos[child.modelXAngle] >> 16;
                                                    if (child.hasOpKey) {
                                                        local2589.setCamera(child.modelYAngle, child.modelYOffset, child.modelXAngle, child.modelXOffset, child.modelZOffset + tileX + tempValue, child.modelZOffset + tileZ, -1L);
                                                    } else {
                                                        local2589.setCamera(child.modelYAngle, 0, child.modelXAngle, 0, tileX, tileZ, -1L);
                                                    }
                                                    if (child.aBoolean34) {
                                                        GlRenderer.enableDepthMask();
                                                    }
                                                } else {
                                                    Rasterizer.setBounds(dragOffsetY, objId);
                                                    tileX = MathUtils.sin[child.modelXAngle] * child.modelZoom >> 16;
                                                    tileZ = child.modelZoom * MathUtils.cos[child.modelXAngle] >> 16;
                                                    if (!child.hasOpKey) {
                                                        local2589.setCamera(child.modelYAngle, 0, child.modelXAngle, 0, tileX, tileZ, -1L);
                                                    } else if (child.modelOrtho) {
                                                        ((SoftwareModel) local2589).method4591(child.modelYAngle, child.modelYOffset, child.modelXAngle, child.modelXOffset, child.modelZOffset + tempValue + tileX, tileZ + child.modelZOffset, child.modelZoom);
                                                    } else {
                                                        local2589.setCamera(child.modelYAngle, child.modelYOffset, child.modelXAngle, child.modelXOffset, child.modelZOffset + tileX + tempValue, tileZ + child.modelZOffset, -1L);
                                                    }
                                                    Rasterizer.prepareOffsets();
                                                }
                                            }
                                        } else {
                                            if (child.type == 7) {
                                                local1921 = child.getFont(Sprites.nameIcons);
                                                if (local1921 == null) {
                                                    if (Component.aBoolean72) {
                                                        redraw(child);
                                                    }
                                                    continue;
                                                }
                                                clipBottom2 = 0;
                                                for (temp = 0; temp < child.baseHeight; temp++) {
                                                    for (tempValue = 0; tempValue < child.baseWidth; tempValue++) {
                                                        if (child.invSlotObjId[clipBottom2] > 0) {
                                                            local2611 = ObjTypeList.get(child.invSlotObjId[clipBottom2] - 1);
                                                            @Pc(3159) JString local3159;
                                                            if (local2611.stackable != 1 && child.invSlotObjCount[clipBottom2] == 1) {
                                                                local3159 = JString.concatenate(new JString[] { COLOR_LIGHT_ORANGE, local2611.name, JString.aClass100_978 });
                                                            } else {
                                                                local3159 = JString.concatenate(new JString[] { COLOR_LIGHT_ORANGE, local2611.name, JString.aClass100_375, StringUtils.formatNumber(child.invSlotObjCount[clipBottom2]) });
                                                            }
                                                            dragOffsetY = renderX + tempValue * (child.invMarginX + 115);
                                                            objId = (child.invMarginY + 12) * temp + renderY;
                                                            if (child.halign == 0) {
                                                                local1921.renderLeft(local3159, dragOffsetY, objId, child.color, child.textShadow ? 0 : -1);
                                                            } else if (child.halign == 1) {
                                                                local1921.renderCenter(local3159, dragOffsetY + 57, objId, child.color, child.textShadow ? 0 : -1);
                                                            } else {
                                                                local1921.renderRight(local3159, dragOffsetY + 115 - 1, objId, child.color, child.textShadow ? 0 : -1);
                                                            }
                                                        }
                                                        clipBottom2++;
                                                    }
                                                }
                                            }
                                            if (child.type == 8 && Protocol.dragHoverComponent == child && Protocol.dragHoverAnimationProgress == ClientScriptRunner.MAX_CALL_STACK_DEPTH) {
                                                clipBottom2 = 0;
                                                clipRight2 = 0;
                                                @Pc(3297) JString local3297 = child.text;
                                                @Pc(3299) Font local3299 = Fonts.p12Full;
                                                local3297 = JString.processStringTokens(child, local3297);
                                                @Pc(3325) JString local3325;
                                                while (local3297.length() > 0) {
                                                    gpuMemory = local3297.indexOf(JString.aClass100_556);
                                                    if (gpuMemory == -1) {
                                                        local3325 = local3297;
                                                        local3297 = JString.EMPTY;
                                                    } else {
                                                        local3325 = local3297.substring(gpuMemory, 0);
                                                        local3297 = local3297.substring(gpuMemory + 4);
                                                    }
                                                    dragOffsetY = local3299.getStringWidth(local3325);
                                                    clipBottom2 += local3299.characterDefaultHeight + 1;
                                                    if (clipRight2 < dragOffsetY) {
                                                        clipRight2 = dragOffsetY;
                                                    }
                                                }
                                                dragOffsetY = renderY + child.height + 5;
                                                clipRight2 += 6;
                                                clipBottom2 += 7;
                                                if (dragOffsetY + clipBottom2 > clipBottom) {
                                                    dragOffsetY = clipBottom - clipBottom2;
                                                }
                                                gpuMemory = renderX + child.width - clipRight2 - 5;
                                                if (gpuMemory < renderX + 5) {
                                                    gpuMemory = renderX + 5;
                                                }
                                                if (clipRight2 + gpuMemory > clipRight) {
                                                    gpuMemory = clipRight - clipRight2;
                                                }
                                                if (GlRenderer.enabled) {
                                                    GlRaster.fillRect(gpuMemory, dragOffsetY, clipRight2, clipBottom2, 16777120);
                                                    GlRaster.drawRect(gpuMemory, dragOffsetY, clipRight2, clipBottom2, 0);
                                                } else {
                                                    SoftwareRenderer.fillRect(gpuMemory, dragOffsetY, clipRight2, clipBottom2, 16777120);
                                                    SoftwareRenderer.drawRect(gpuMemory, dragOffsetY, clipRight2, clipBottom2, 0);
                                                }
                                                local3297 = child.text;
                                                objId = dragOffsetY + local3299.characterDefaultHeight + 2;
                                                local3297 = JString.processStringTokens(child, local3297);
                                                while (local3297.length() > 0) {
                                                    tileX = local3297.indexOf(JString.aClass100_556);
                                                    if (tileX == -1) {
                                                        local3325 = local3297;
                                                        local3297 = JString.EMPTY;
                                                    } else {
                                                        local3325 = local3297.substring(tileX, 0);
                                                        local3297 = local3297.substring(tileX + 4);
                                                    }
                                                    local3299.renderLeft(local3325, gpuMemory + 3, objId, 0, -1);
                                                    objId += local3299.characterDefaultHeight + 1;
                                                }
                                            }
                                            if (child.type == Component.TYPE_LINE) {
                                                if (child.aBoolean20) {
                                                    temp = renderX + child.width;
                                                    clipBottom2 = renderY + child.height;
                                                    tempValue = renderY;
                                                } else {
                                                    clipBottom2 = renderY;
                                                    tempValue = renderY + child.height;
                                                    temp = renderX + child.width;
                                                }
                                                if (child.lineWidth == 1) {
                                                    if (GlRenderer.enabled) {
                                                        GlRaster.drawDiagonalLine(renderX, clipBottom2, temp, tempValue, child.color);
                                                    } else {
                                                        SoftwareRenderer.drawDiagonalLine(renderX, clipBottom2, temp, tempValue, child.color);
                                                    }
                                                } else if (GlRenderer.enabled) {
                                                    GlRaster.method1181(renderX, clipBottom2, temp, tempValue, child.color, child.lineWidth);
                                                } else {
                                                    SoftwareRenderer.drawThickLine(renderX, clipBottom2, temp, tempValue, child.color, child.lineWidth);
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
    public static void updateGenderDependentComponents(@OriginalArg(1) Component component) {
        @Pc(16) int contentType = component.clientcode;
        if (contentType == 324) {
            if (ClientScriptRunner.defaultSpriteId == -1) {
                ClientScriptRunner.defaultSpriteId = component.graphic;
                ClientScriptRunner.alternateSpriteId = component.activeSpriteId;
            }
            if (PlayerAppearance.DEFAULT.gender) {
                component.graphic = ClientScriptRunner.defaultSpriteId;
            } else {
                component.graphic = ClientScriptRunner.alternateSpriteId;
            }
        } else if (contentType == 325) {
            if (ClientScriptRunner.defaultSpriteId == -1) {
                ClientScriptRunner.alternateSpriteId = component.activeSpriteId;
                ClientScriptRunner.defaultSpriteId = component.graphic;
            }
            if (PlayerAppearance.DEFAULT.gender) {
                component.graphic = ClientScriptRunner.alternateSpriteId;
            } else {
                component.graphic = ClientScriptRunner.defaultSpriteId;
            }
        } else if (contentType == 327) {
            component.modelXAngle = 150;
            component.modelYAngle = (int) (Math.sin((double) Client.loop / 40.0D) * 256.0D) & 0x7FF;
            component.modelType = 5;
            component.modelId = -1;
        } else if (contentType == ComponentClientCode.SPINNING_PLAYER) {
            if (PlayerList.self.username == null) {
                component.modelId = 0;
            } else {
                component.modelXAngle = 150;
                component.modelYAngle = (int) (Math.sin((double) Client.loop / 40.0D) * 256.0D) & 0x7FF;
                component.modelType = Component.OBJ_TYPE_PLAYERMODEL;
                component.modelId = ((int) PlayerList.self.username.encode37() << 11) + 2047;
                component.animationFrame = PlayerList.self.anInt3388;
                component.animationDelay = 0;
                component.modelSeqId = PlayerList.self.movementSeqId;
                component.animationId = PlayerList.self.anInt3407;
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
            SoftwareRenderer.fillRect(x, y + 16, 16, height - 32, ClientScriptRunner.COLOR_LIGHT_BROWN);
            SoftwareRenderer.fillRect(x, thumbY + y + 16, 16, thumbHeight, ClientScriptRunner.COLOR_ORANGE_BROWN);
            SoftwareRenderer.drawVerticalLine(x, thumbY + y + 16, thumbHeight, ClientScriptRunner.COLOR_LIGHT_GRAY);
            SoftwareRenderer.drawVerticalLine(x + 1, thumbY + 16 + y, thumbHeight, ClientScriptRunner.COLOR_LIGHT_GRAY);
            SoftwareRenderer.drawHorizontalLine(x, y + thumbY + 16, 16, ClientScriptRunner.COLOR_LIGHT_GRAY);
            SoftwareRenderer.drawHorizontalLine(x, y + thumbY + 17, 16, ClientScriptRunner.COLOR_LIGHT_GRAY);
            SoftwareRenderer.drawVerticalLine(x + 15, thumbY + 16 + y, thumbHeight, ClientScriptRunner.COLOR_DARK_RED);
            SoftwareRenderer.drawVerticalLine(x + 14, y - -17 - -thumbY, thumbHeight - 1, ClientScriptRunner.COLOR_DARK_RED);
            SoftwareRenderer.drawHorizontalLine(x, thumbHeight + y + thumbY + 15, 16, ClientScriptRunner.COLOR_DARK_RED);
            SoftwareRenderer.drawHorizontalLine(x + 1, thumbHeight + y - (-thumbY + -14), 15, ClientScriptRunner.COLOR_DARK_RED);
            return;
        }
        GlRaster.fillRect(x, y + 16, 16, height - 32, ClientScriptRunner.COLOR_LIGHT_BROWN);
        GlRaster.fillRect(x, y + thumbY + 16, 16, thumbHeight, ClientScriptRunner.COLOR_ORANGE_BROWN);
        GlRaster.method1176(x, thumbY + y + 16, thumbHeight, ClientScriptRunner.COLOR_LIGHT_GRAY);
        GlRaster.method1176(x + 1, thumbY + 16 + y, thumbHeight, ClientScriptRunner.COLOR_LIGHT_GRAY);
        GlRaster.method1174(x, thumbY + y + 16, 16, ClientScriptRunner.COLOR_LIGHT_GRAY);
        GlRaster.method1174(x, thumbY + y + 17, 16, ClientScriptRunner.COLOR_LIGHT_GRAY);
        GlRaster.method1176(x + 15, y + (16 - -thumbY), thumbHeight, ClientScriptRunner.COLOR_DARK_RED);
        GlRaster.method1176(x + 14, y - -thumbY + 17, thumbHeight - 1, ClientScriptRunner.COLOR_DARK_RED);
        GlRaster.method1174(x, thumbHeight + y + thumbY + 15, 16, ClientScriptRunner.COLOR_DARK_RED);
        GlRaster.method1174(x + 1, y + 14 - -thumbY + thumbHeight, 15, ClientScriptRunner.COLOR_DARK_RED);
    }

    //TODO move somewhere else
    @OriginalMember(owner = "client!da", name = "a", descriptor = "(IIILclient!be;)V")
    public static void dragTryPickup(@OriginalArg(0) int startY, @OriginalArg(1) int startX, @OriginalArg(3) Component component) {
        if (dragSource != null || MiniMenu.open || (component == null || getWidgetContainer(component) == null)) {
            return;
        }
        dragSource = component;
        dragLayer = getWidgetContainer(component);
        ClientScriptRunner.dragStartX = startX;
        isDragging = false;
        dragTicks = 0;
        dragStartY = startY;
    }

    //TODO move somewhere else
    @OriginalMember(owner = "client!ha", name = "a", descriptor = "(ILclient!be;)Lclient!be;")
    public static Component getWidgetContainer(@OriginalArg(1) Component component) {
        @Pc(12) Component container = canAcceptDrop(component);
        if (container == null) {
            container = component.parent;
        }
        return container;
    }

    @OriginalMember(owner = "client!vg", name = "a", descriptor = "(Lclient!na;IIBI)V")
    public static void ifButtonXSend(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int opcode, @OriginalArg(4) int arg3) {
        @Pc(8) Component button = InterfaceList.getComponent(arg3, arg1);
        if (button == null) {
            return;
        }
        if (button.onOp != null) {
            @Pc(19) HookRequest hook = new HookRequest();
            hook.arguments = button.onOp;
            hook.source = button;
            hook.opBase = arg0;
            hook.op = opcode;
            ClientScriptRunner.executeScript(hook);
        }
        @Pc(37) boolean local37 = true;
        if (button.clientcode > 0) {
            local37 = MiniMenu.shouldTriggerIdleTimeout(button);
        }
        if (!local37 || !getServerActiveProperties(button).isButtonEnabled(opcode - 1)) {
            return;
        }
        if (opcode == 1) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON1);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 2) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON2);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 3) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON3);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 4) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON4);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 5) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON5);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 6) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON6);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 7) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON7);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 8) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON8);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 9) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON9);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
        if (opcode == 10) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_BUTTON10);
            Protocol.outboundBuffer.p4(arg3);
            Protocol.outboundBuffer.p2(arg1);
        }
    }

    @OriginalMember(owner = "runetek4.client!ec", name = "a", descriptor = "(B)V")
    public static void endTargetMode() {
        if (!targetMode) {
            return;
        }
        @Pc(19) Component component = InterfaceList.getComponent(MiniMenu.useWithComponentId, MiniMenu.useWithSlot);
        if (component != null && component.onTargetLeave != null) {
            @Pc(29) HookRequest local29 = new HookRequest();
            local29.arguments = component.onTargetLeave;
            local29.source = component;
            ClientScriptRunner.executeScript(local29);
        }
        targetMode = false;
        defaultCursor = -1;
        redraw(component);
    }

    @OriginalMember(owner = "runetek4.client!va", name = "a", descriptor = "(IZILclient!be;)V")
    public static void addMiniMenuOptions(@OriginalArg(0) int mouseY, @OriginalArg(2) int mouseX, @OriginalArg(3) Component component) {
        if (component.buttonType == 1) {
            MiniMenu.addActionRow(-1, 0L, JString.EMPTY, 0, (short) 8, component.option, component.slot);
        }
        @Pc(47) JString targetVerb;
        if (component.buttonType == 2 && !targetMode) {
            targetVerb = MiniMap.getTargetVerb(component);
            if (targetVerb != null) {
                MiniMenu.addActionRow(-1, 0L, JString.concatenate(new JString[] {COLOR_LIMEGREEN, component.optionSuffix}), -1, (short) 32, targetVerb, component.slot);
            }
        }
        if (component.buttonType == 3) {
            MiniMenu.addActionRow(-1, 0L, JString.EMPTY, 0, (short) 28, LocalizedText.CLOSE, component.slot);
        }
        if (component.buttonType == 4) {
            MiniMenu.addActionRow(-1, 0L, JString.EMPTY, 0, (short) 59, component.option, component.slot);
        }
        if (component.buttonType == 5) {
            MiniMenu.addActionRow(-1, 0L, JString.EMPTY, 0, (short) 51, component.option, component.slot);
        }
        if (component.buttonType == 6 && ClientScriptRunner.modalBackgroundComponent == null) {
            MiniMenu.addActionRow(-1, 0L, JString.EMPTY, -1, (short) 41, component.option, component.slot);
        }
        @Pc(173) int row;
        @Pc(171) int slotIndex;
        if (component.type == 2) {
            slotIndex = 0;
            for (row = 0; row < component.baseHeight; row++) {
                for (@Pc(183) int col = 0; col < component.baseWidth; col++) {
                    @Pc(195) int slotX = (component.invMarginX + 32) * col;
                    @Pc(202) int slotY = (component.invMarginY + 32) * row;
                    if (slotIndex < 20) {
                        slotY += component.invOffsetY[slotIndex];
                        slotX += component.invOffsetX[slotIndex];
                    }
                    if (mouseX >= slotX && slotY <= mouseY && slotX + 32 > mouseX && slotY + 32 > mouseY) {
                        mouseOverInventoryComponent = component;
                        mouseInvInterfaceIndex = slotIndex;
                        if (component.invSlotObjId[slotIndex] > 0) {
                            @Pc(267) ServerActiveProperties serverProps = getServerActiveProperties(component);
                            @Pc(276) ObjType objType = ObjTypeList.get(component.invSlotObjId[slotIndex] - 1);
                            if (anInt5014 == 1 && serverProps.isObjOpsEnabled()) {
                                if (MiniMap.anInt5062 != component.slot || anInt4370 != slotIndex) {
                                    MiniMenu.addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] { aClass100_203, COLOR_LIGHT_ORANGE_ARROW, objType.name}), slotIndex, (short) 40, LocalizedText.USE, component.slot);
                                }
                            } else if (targetMode && serverProps.isObjOpsEnabled()) {
                                @Pc(596) ParamType paramType = useWithParam == -1 ? null : ParamTypeList.get(useWithParam);
                                if ((useWithMask & TARGET_MASK_OBJ) != 0 && (paramType == null || objType.getParam(paramType.defaultInt, useWithParam) != paramType.defaultInt)) {
                                    MiniMenu.addActionRow(useWithCursor, (long) objType.id, JString.concatenate(new JString[] { aClass100_466, COLOR_LIGHT_ORANGE_ARROW, objType.name}), slotIndex, (short) 3, aClass100_545, component.slot);
                                }
                            } else {
                                @Pc(296) JString[] objOps = objType.iop;
                                if (aBoolean237) {
                                    objOps = annotateOps(objOps);
                                }
                                @Pc(309) int opIndex;
                                @Pc(334) byte actionId;
                                if (serverProps.isObjOpsEnabled()) {
                                    for (opIndex = 4; opIndex >= 3; opIndex--) {
                                        if (objOps != null && objOps[opIndex] != null) {
                                            if (opIndex == 3) {
                                                actionId = 35;
                                            } else {
                                                actionId = 58;
                                            }
                                            MiniMenu.addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] {COLOR_LIGHT_ORANGE, objType.name}), slotIndex, actionId, objOps[opIndex], component.slot);
                                        }
                                    }
                                }
                                if (serverProps.isObjUseEnabled()) {
                                    MiniMenu.addActionRow(MiniMap.anInt4075, (long) objType.id, JString.concatenate(new JString[] {COLOR_LIGHT_ORANGE, objType.name}), slotIndex, (short) 22, LocalizedText.USE, component.slot);
                                }
                                if (serverProps.isObjOpsEnabled() && objOps != null) {
                                    for (opIndex = 2; opIndex >= 0; opIndex--) {
                                        if (objOps[opIndex] != null) {
                                            actionId = 0;
                                            if (opIndex == 0) {
                                                actionId = 47;
                                            }
                                            if (opIndex == 1) {
                                                actionId = 5;
                                            }
                                            if (opIndex == 2) {
                                                actionId = 43;
                                            }
                                            MiniMenu.addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] {COLOR_LIGHT_ORANGE, objType.name}), slotIndex, actionId, objOps[opIndex], component.slot);
                                        }
                                    }
                                }
                                objOps = component.invOptions;
                                if (aBoolean237) {
                                    objOps = annotateOps(objOps);
                                }
                                if (objOps != null) {
                                    for (opIndex = 4; opIndex >= 0; opIndex--) {
                                        if (objOps[opIndex] != null) {
                                            actionId = 0;
                                            if (opIndex == 0) {
                                                actionId = 25;
                                            }
                                            if (opIndex == 1) {
                                                actionId = 23;
                                            }
                                            if (opIndex == 2) {
                                                actionId = 48;
                                            }
                                            if (opIndex == 3) {
                                                actionId = 7;
                                            }
                                            if (opIndex == 4) {
                                                actionId = 13;
                                            }
                                            MiniMenu.addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] {COLOR_LIGHT_ORANGE, objType.name}), slotIndex, actionId, objOps[opIndex], component.slot);
                                        }
                                    }
                                }
                                MiniMenu.addActionRow(MiniMap.anInt5073, (long) objType.id, JString.concatenate(new JString[] {COLOR_LIGHT_ORANGE, objType.name}), slotIndex, (short) 1006, LocalizedText.EXAMINE, component.slot);
                            }
                        }
                    }
                    slotIndex++;
                }
            }
        }
        if (!component.hasOpKey) {
            return;
        }
        if (!targetMode) {
            for (slotIndex = 9; slotIndex >= 5; slotIndex--) {
                @Pc(765) JString componentOp = getOp(component, slotIndex);
                if (componentOp != null) {
                    MiniMenu.addActionRow(getOpCursor(slotIndex, component), (long) (slotIndex + 1), component.opBase, component.id, (short) 1003, componentOp, component.slot);
                }
            }
            targetVerb = MiniMap.getTargetVerb(component);
            if (targetVerb != null) {
                MiniMenu.addActionRow(-1, 0L, component.opBase, component.id, (short) 32, targetVerb, component.slot);
            }
            for (row = 4; row >= 0; row--) {
                @Pc(828) JString componentOp = getOp(component, row);
                if (componentOp != null) {
                    MiniMenu.addActionRow(getOpCursor(row, component), (long) (row + 1), component.opBase, component.id, (short) 9, componentOp, component.slot);
                }
            }
            if (getServerActiveProperties(component).isResumePauseButtonEnabled()) {
                MiniMenu.addActionRow(-1, 0L, JString.EMPTY, component.id, (short) 41, LocalizedText.CONTINUE, component.slot);
            }
        } else if (getServerActiveProperties(component).isUseTarget() && (useWithMask & TARGET_MASK_COMPONENT) != 0) {
            MiniMenu.addActionRow(useWithCursor, 0L, JString.concatenate(new JString[] { aClass100_466, ARROW_SEPARATOR, component.opBase}), component.id, (short) 12, aClass100_545, component.slot);
        }
    }

    @OriginalMember(owner = "runetek4.client!wk", name = "a", descriptor = "(I[Lclient!na;)[Lclient!na;")
    public static JString[] annotateOps(@OriginalArg(1) JString[] ops) {
        @Pc(8) JString[] annotatedOps = new JString[5];
        for (@Pc(15) int i = 0; i < 5; i++) {
            annotatedOps[i] = JString.concatenate(new JString[] { JString.parseInt(i), COLON_SEPARATOR});
            if (ops != null && ops[i] != null) {
                annotatedOps[i] = JString.concatenate(new JString[] { annotatedOps[i], ops[i] });
            }
        }
        return annotatedOps;
    }

    @OriginalMember(owner = "client!aj", name = "a", descriptor = "(BILclient!be;)I")
    public static int getOpCursor(@OriginalArg(1) int opIndex, @OriginalArg(2) Component component) {
        if (!getServerActiveProperties(component).isButtonEnabled(opIndex) && component.onOp == null) {
            return -1;
        } else if (component.opCursors == null || opIndex >= component.opCursors.length) {
            return -1;
        } else {
            return component.opCursors[opIndex];
        }
    }
}
