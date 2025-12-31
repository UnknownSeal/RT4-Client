package com.jagex.ui.component;

import com.jagex.core.constants.ComponentClientCode;
import com.jagex.game.runetek4.config.objtype.ObjType;
import com.jagex.game.runetek4.config.objtype.ObjTypeList;
import com.jagex.cache.CacheArchive;
import com.jagex.cache.media.Font;
import com.jagex.entity.player.PlayerAppearance;
import com.jagex.entity.player.PlayerList;
import com.jagex.entity.player.PlayerSkillXpTable;
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
import com.jagex.core.io.Packet;
import com.jagex.game.runetek4.config.bastype.cursor.CursorType;
import com.jagex.entity.player.Player;
import com.jagex.game.inventory.Inv;
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
import com.jagex.ui.events.ComponentEvent;
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
    @OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
    public static int transmitTimer = 1;
    @OriginalMember(owner = "runetek4.client!md", name = "W", descriptor = "I")
    public static int topLevelInterface = -1;
    @OriginalMember(owner = "client!je", name = "T", descriptor = "Lclient!sc;")
    public static IterableHashTable openInterfaces = new IterableHashTable(8);
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
    @OriginalMember(owner = "runetek4.client!th", name = "j", descriptor = "[[Lclient!be;")
    public static Component[][] cachedComponents;
    @OriginalMember(owner = "runetek4.client!sc", name = "m", descriptor = "[Z")
    public static boolean[] loadedComponents;
    @OriginalMember(owner = "runetek4.client!ra", name = "J", descriptor = "I")
    public static int miscTransmitAt = 0;
    @OriginalMember(owner = "runetek4.client!jd", name = "i", descriptor = "Lclient!be;")
    public static Component clickedInventoryComponent;
    @OriginalMember(owner = "runetek4.client!nf", name = "h", descriptor = "Lclient!be;")
    public static Component mouseOverInventoryComponent;
    @OriginalMember(owner = "client!ef", name = "r", descriptor = "Lclient!be;")
    public static Component targetComponent = null;
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
            for (@Pc(18) SubInterface SubInterface = (SubInterface) openInterfaces.head(); SubInterface != null; SubInterface = (SubInterface) openInterfaces.next()) {
                closeInterface(true, SubInterface);
            }
            topLevelInterface = -1;
            openInterfaces = new IterableHashTable(8);
            createComponentMemoryBuffer();
            topLevelInterface = LoginManager.loginScreenId;
            updateInterfaceLayout(false);
            ClientScriptRunner.method1807();
            runInterfaceInitScripts(topLevelInterface);
        }
        MiniMenu.defaultCursor = -1;
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
        if (cachedComponents[componentId] == null) {
            return;
        }
        @Pc(27) boolean canDeleteFromCache = true;
        for (@Pc(29) int childComponentIndex = 0; childComponentIndex < cachedComponents[componentId].length; childComponentIndex++) {
            if (cachedComponents[componentId][childComponentIndex] != null) {
                if (cachedComponents[componentId][childComponentIndex].type == 2) {
                    canDeleteFromCache = false;
                } else {
                    cachedComponents[componentId][childComponentIndex] = null;
                }
            }
        }
        if (canDeleteFromCache) {
            cachedComponents[componentId] = null;
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
        @Pc(32) Component parentComponent = getComponent(parentComponentId);
        if (parentComponent != null) {
            redraw(parentComponent);
        }
        @Pc(41) int totalMenuActions = MiniMenu.menuActionRow;
        @Pc(43) int maxWidth;
        for (maxWidth = 0; maxWidth < totalMenuActions; maxWidth++) {
            if (shouldRemoveMenuAction(MiniMenu.actions[maxWidth])) {
                MiniMenu.removeActionRow(maxWidth);
            }
        }
        if (MiniMenu.menuActionRow == 1) {
            ClientScriptRunner.menuVisible = false;
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
        cachedComponents = new Component[gameInterfaceJs5.capacity()][];
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
        if (load(interfaceId)) {
            processComponents(cachedComponents[interfaceId], -1, clipX, y, width, height, x, clipY);
        }
    }

    @OriginalMember(owner = "runetek4.client!af", name = "a", descriptor = "(BI)Lclient!be;")
    public static Component getComponent(@OriginalArg(1) int packedId) {
        @Pc(7) int interfaceId = packedId >> 16;
        @Pc(18) int componentId = packedId & 0xFFFF;
        if (cachedComponents[interfaceId] == null || cachedComponents[interfaceId][componentId] == null) {
            @Pc(33) boolean success = load(interfaceId);
            if (!success) {
                return null;
            }
            // todo: this should not be necessary, data/server-related?
            if (cachedComponents.length <= interfaceId || cachedComponents[interfaceId].length <= componentId) {
                return null;
            }
        }
        return cachedComponents[interfaceId][componentId];
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "b", descriptor = "(Lclient!be;)Lclient!bf;")
    public static ServerActiveProperties getServerActiveProperties(@OriginalArg(0) Component component) {
        @Pc(13) ServerActiveProperties serverProperties = (ServerActiveProperties) properties.get(((long) component.id << 32) + (long) component.createdComponentId);
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

    @OriginalMember(owner = "runetek4.client!qf", name = "a", descriptor = "(BII)Lclient!be;")
    public static Component getCreatedComponent(@OriginalArg(1) int parentId, @OriginalArg(2) int childIndex) {
        @Pc(7) Component parentComponent = getComponent(parentId);
        if (childIndex == -1) {
            return parentComponent;
        } else if (parentComponent == null || parentComponent.createdComponents == null || parentComponent.createdComponents.length <= childIndex) {
            return null;
        } else {
            return parentComponent.createdComponents[childIndex];
        }
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
        cachedComponents = new Component[gameInterfaceJs5.capacity()][];
        loadedComponents = new boolean[gameInterfaceJs5.capacity()];
    }

    @OriginalMember(owner = "runetek4.client!tm", name = "b", descriptor = "(II)Z")
    public static boolean load(@OriginalArg(0) int interfaceId) {
        if (loadedComponents[interfaceId]) {
            return true;
        } else if (gameInterfaceJs5.isGroupReady(interfaceId)) {
            @Pc(25) int componentCount = gameInterfaceJs5.getGroupCapacity(interfaceId);
            if (componentCount == 0) {
                loadedComponents[interfaceId] = true;
                return true;
            }
            if (cachedComponents[interfaceId] == null) {
                cachedComponents[interfaceId] = new Component[componentCount];
            }
            for (@Pc(46) int componentIndex = 0; componentIndex < componentCount; componentIndex++) {
                if (cachedComponents[interfaceId][componentIndex] == null) {
                    @Pc(62) byte[] componentData = gameInterfaceJs5.getfile(interfaceId, componentIndex);
                    if (componentData != null) {
                        @Pc(74) Component component = cachedComponents[interfaceId][componentIndex] = new Component();
                        component.id = componentIndex + (interfaceId << 16);
                        if (componentData[0] == -1) {
                            component.decodeIf3(new Packet(componentData));
                        } else {
                            component.decodeIf1(new Packet(componentData));
                        }
                    }
                }
            }
            loadedComponents[interfaceId] = true;
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!i", name = "i", descriptor = "(Z)V")
    public static void redrawActiveInterfaces() {
        for (@Pc(6) SubInterface subInterface = (SubInterface) openInterfaces.head(); subInterface != null; subInterface = (SubInterface) openInterfaces.next()) {
            @Pc(14) int interfaceId = subInterface.interfaceId;
            if (load(interfaceId)) {
                @Pc(21) boolean isIf3Format = true;
                @Pc(25) Component[] components = cachedComponents[interfaceId];
                @Pc(27) int local27;
                for (local27 = 0; local27 < components.length; local27++) {
                    if (components[local27] != null) {
                        isIf3Format = components[local27].if3;
                        break;
                    }
                }
                if (!isIf3Format) {
                    local27 = (int) subInterface.key;
                    @Pc(60) Component parentComponent = getComponent(local27);
                    if (parentComponent != null) {
                        redraw(parentComponent);
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!qj", name = "a", descriptor = "(Lclient!be;BI)Lclient!na;")
    public static JString getOp(@OriginalArg(0) Component component, @OriginalArg(2) int opIndex) {
        if (!getServerActiveProperties(component).isButtonEnabled(opIndex) && component.onOptionClick == null) {
            return null;
        } else if (component.ops == null || component.ops.length <= opIndex || component.ops[opIndex] == null || component.ops[opIndex].trim().length() == 0) {
            return Cheat.testOpacity ? JString.concatenate(new JString[] {aClass100_903, JString.parseInt(opIndex) }) : null;
        } else {
            return component.ops[opIndex];
        }
    }

    @OriginalMember(owner = "runetek4.client!gg", name = "e", descriptor = "(II)V")
    public static void resetComponentAnimations(@OriginalArg(0) int interfaceId) {
        if (!load(interfaceId)) {
            return;
        }
        @Pc(15) Component[] components = cachedComponents[interfaceId];
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
        if (load(interfaceId)) {
            updateComponentLayout(-1, fullUpdate, canvasWidth, canvasHeight, cachedComponents[interfaceId]);
        }
    }

    @OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(IZIII[Lclient!be;)V")
    public static void updateComponentLayout(@OriginalArg(0) int parentId, @OriginalArg(1) boolean fullUpdate, @OriginalArg(2) int canvasWidth, @OriginalArg(4) int canvasHeight, @OriginalArg(5) Component[] components) {
        for (@Pc(3) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(19) Component component = components[componentIndex];
            if (component != null && component.layer == parentId) {
                calculateComponentSize(canvasHeight, canvasWidth, component, fullUpdate);
                calculateComponentPosition(component, canvasHeight, canvasWidth);
                if (component.scrollMaxH - component.width < component.scrollX) {
                    component.scrollX = component.scrollMaxH - component.width;
                }
                if (component.scrollY > component.scrollMaxV - component.height) {
                    component.scrollY = component.scrollMaxV - component.height;
                }
                if (component.scrollY < 0) {
                    component.scrollY = 0;
                }
                if (component.scrollX < 0) {
                    component.scrollX = 0;
                }
                if (component.type == 0) {
                    updateContainerLayout(component, fullUpdate);
                }
            }
        }
    }

    @OriginalMember(owner = "client!bg", name = "a", descriptor = "(Lclient!be;ZI)V")
    public static void updateContainerLayout(@OriginalArg(0) Component container, @OriginalArg(1) boolean fullUpdate) {
        @Pc(20) int contentWidth = container.scrollMaxH == 0 ? container.width : container.scrollMaxH;
        @Pc(32) int contentHeight = container.scrollMaxV == 0 ? container.height : container.scrollMaxV;
        updateComponentLayout(container.id, fullUpdate, contentWidth, contentHeight, cachedComponents[container.id >> 16]);
        if (container.createdComponents != null) {
            updateComponentLayout(container.id, fullUpdate, contentWidth, contentHeight, container.createdComponents);
        }
        @Pc(66) SubInterface subInterface = (SubInterface) openInterfaces.get((long) container.id);
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
            @Pc(305) ComponentEvent resizeEvent = new ComponentEvent();
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

    @OriginalMember(owner = "client!fn", name = "c", descriptor = "(II)V")
    public static void runInterfaceInitScripts(@OriginalArg(0) int interfaceId) {
        if (interfaceId == -1 || !load(interfaceId)) {
            return;
        }
        @Pc(31) Component[] components = cachedComponents[interfaceId];
        for (@Pc(33) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(41) Component component = components[componentIndex];
            if (component.onInterfaceOpen != null) {
                @Pc(50) ComponentEvent initEvent = new ComponentEvent();
                initEvent.arguments = component.onInterfaceOpen;
                initEvent.source = component;
                ClientScriptRunner.run(2000000, initEvent);
            }
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
        if (load(interfaceId)) {
            runComponentScripts(cachedComponents[interfaceId], triggerId);
        }
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(Lclient!be;I)Lclient!be;")
    public static Component getParentComponent(@OriginalArg(0) Component component) {
        if (component.layer != -1) {
            return getComponent(component.layer);
        }
        @Pc(28) int interfaceId = component.id >>> 16;
        @Pc(33) HashTableIterator interfaceIterator = new HashTableIterator(openInterfaces);
        for (@Pc(38) SubInterface subInterface = (SubInterface) interfaceIterator.method2701(); subInterface != null; subInterface = (SubInterface) interfaceIterator.method2700()) {
            if (interfaceId == subInterface.interfaceId) {
                return getComponent((int) subInterface.key);
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
                    if (component.createdComponents != null) {
                        runComponentScripts(component.createdComponents, triggerId);
                    }
                    @Pc(49) SubInterface subInterface = (SubInterface) openInterfaces.get((long) component.id);
                    if (subInterface != null) {
                        runScripts(triggerId, subInterface.interfaceId);
                    }
                }
                @Pc(72) ComponentEvent scriptEvent;
                if (triggerId == 0 && component.onDialogAbort != null) {
                    scriptEvent = new ComponentEvent();
                    scriptEvent.arguments = component.onDialogAbort;
                    scriptEvent.source = component;
                    ClientScriptRunner.run(scriptEvent);
                }
                if (triggerId == 1 && component.onComponentsOpenClose != null) {
                    if (component.createdComponentId >= 0) {
                        @Pc(103) Component parentComponent = getComponent(component.id);
                        if (parentComponent == null || parentComponent.createdComponents == null || component.createdComponentId >= parentComponent.createdComponents.length || parentComponent.createdComponents[component.createdComponentId] != component) {
                            continue;
                        }
                    }
                    scriptEvent = new ComponentEvent();
                    scriptEvent.arguments = component.onComponentsOpenClose;
                    scriptEvent.source = component;
                    ClientScriptRunner.run(scriptEvent);
                }
            }
        }
    }

    @OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "([Lclient!be;IIIIIII)V")
    public static void processComponents(@OriginalArg(0) Component[] components, @OriginalArg(1) int parentId, @OriginalArg(2) int clipLeft, @OriginalArg(3) int clipTop, @OriginalArg(4) int clipRight, @OriginalArg(5) int clipBottom, @OriginalArg(6) int offsetX, @OriginalArg(7) int offsetY) {
        for (@Pc(1) int componentIndex = 0; componentIndex < components.length; componentIndex++) {
            @Pc(9) Component component = components[componentIndex];
            if (component != null && component.layer == parentId && (!component.if3 || component.type == 0 || component.interactive || getServerActiveProperties(component).events != 0 || component == ClientScriptRunner.containerComponent || component.clientcode == 1338) && (!component.if3 || !isHidden(component))) {
                @Pc(50) int componentX = component.x + offsetX;
                @Pc(55) int componentY = component.y + offsetY;
                @Pc(61) int effectiveClipLeft;
                @Pc(63) int effectiveClipTop;
                @Pc(65) int effectiveClipRight;
                @Pc(67) int effectiveClipBottom;
                if (component.type == 2) {
                    effectiveClipLeft = clipLeft;
                    effectiveClipTop = clipTop;
                    effectiveClipRight = clipRight;
                    effectiveClipBottom = clipBottom;
                } else {
                    @Pc(73) int componentRight = componentX + component.width;
                    @Pc(78) int componentBottom = componentY + component.height;
                    if (component.type == 9) {
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
                if (!component.if3 || effectiveClipLeft < effectiveClipRight && effectiveClipTop < effectiveClipBottom) {
                    if (component.type == 0) {
                        if (!component.if3 && isHidden(component) && previousMouseOverComponent != component) {
                            continue;
                        }
                        if (component.noClickThrough && Mouse.lastMouseX >= effectiveClipLeft && Mouse.lastMouseY >= effectiveClipTop && Mouse.lastMouseX < effectiveClipRight && Mouse.lastMouseY < effectiveClipBottom) {
                            for (@Pc(164) ComponentEvent local164 = (ComponentEvent) lowPriorityRequests.head(); local164 != null; local164 = (ComponentEvent) lowPriorityRequests.next()) {
                                if (local164.shouldExecute) {
                                    local164.unlink();
                                    local164.source.mouseHover = false;
                                }
                            }
                            if (ClientScriptRunner.dragTime == 0) {
                                dragSource = null;
                                ClientScriptRunner.containerComponent = null;
                            }
                            worldMapState = 0;
                        }
                    }
                    if (component.if3) {
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
                                            ClientProt.method4512(JString.EMPTY, -1, hotkeyIndex + 1, component.id);
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
                            ClientScriptRunner.startComponentDrag(Mouse.mouseClickY - componentY, Mouse.mouseClickX - componentX, component);
                        }
                        if (dragSource != null && dragSource != component && mouseOver && getServerActiveProperties(component).isDragTarget()) {
                            targetComponent = component;
                        }
                        if (component == ClientScriptRunner.containerComponent) {
                            dragActive = true;
                            ClientScriptRunner.minX = componentX;
                            minY = componentY;
                        }
                        if (component.interactive || component.clientcode != 0) {
                            @Pc(399) ComponentEvent request;
                            if (mouseOver && MouseWheel.wheelRotation != 0 && component.onScroll != null) {
                                request = new ComponentEvent();
                                request.shouldExecute = true;
                                request.source = component;
                                request.mouseY = MouseWheel.wheelRotation;
                                request.arguments = component.onScroll;
                                lowPriorityRequests.push(request);
                            }
                            if (dragSource != null || clickedInventoryComponent != null || ClientScriptRunner.menuVisible || component.clientcode != 1400 && worldMapState > 0) {
                                mouseClicked = false;
                                mousePressed = false;
                                mouseOver = false;
                            }
                            @Pc(508) int skill;
                            if (component.clientcode != 0) {
                                if (component.clientcode == 1337) {
                                    specialComponent = component;
                                    redraw(component);
                                    continue;
                                }
                                if (component.clientcode == 1338) {
                                    if (mouseClicked) {
                                        anInt5 = Mouse.mouseClickX - componentX;
                                        MiniMenu.anInt2878 = Mouse.mouseClickY - componentY;
                                    }
                                    continue;
                                }
                                if (component.clientcode == 1400) {
                                    WorldMap.component = component;
                                    if (mouseClicked) {
                                        if (Keyboard.pressedKeys[82] && LoginManager.staffModLevel > 0) {
                                            hotkeyIndex = (int) ((double) (Mouse.mouseClickX - componentX - component.width / 2) * 2.0D / (double) WorldMap.zoom);
                                            skill = (int) ((double) (Mouse.mouseClickY - componentY - component.height / 2) * 2.0D / (double) WorldMap.zoom);
                                            cooldownDelay = WorldMap.anInt435 + hotkeyIndex;
                                            @Pc(516) int local516 = WorldMap.anInt919 + skill;
                                            @Pc(520) int local520 = cooldownDelay + WorldMap.originX;
                                            @Pc(528) int local528 = WorldMap.length + WorldMap.originZ - local516 - 1;
                                            Cheat.teleport(local520, local528, 0);
                                            closeModal();
                                            continue;
                                        }
                                        worldMapState = 1;
                                        ClientScriptRunner.dragStartX = Mouse.lastMouseX;
                                        ClientScriptRunner.dragStartY = Mouse.lastMouseY;
                                        continue;
                                    }
                                    if (mousePressed && worldMapState > 0) {
                                        if (worldMapState == 1 && (ClientScriptRunner.dragStartX != Mouse.lastMouseX || ClientScriptRunner.dragStartY != Mouse.lastMouseY)) {
                                            anInt4620 = WorldMap.anInt435;
                                            anInt1885 = WorldMap.anInt919;
                                            worldMapState = 2;
                                        }
                                        if (worldMapState == 2) {
                                            WorldMap.method1964(anInt4620 + (int) ((double) (ClientScriptRunner.dragStartX - Mouse.lastMouseX) * 2.0D / (double) WorldMap.targetZoom));
                                            WorldMap.method4641(anInt1885 + (int) ((double) (ClientScriptRunner.dragStartY - Mouse.lastMouseY) * 2.0D / (double) WorldMap.targetZoom));
                                        }
                                        continue;
                                    }
                                    worldMapState = 0;
                                    continue;
                                }
                                if (component.clientcode == 1401) {
                                    if (mousePressed) {
                                        WorldMap.method2387(component.width, Mouse.lastMouseY - componentY, Mouse.lastMouseX - componentX, component.height);
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
                                if (component.onClickRepeat != null) {
                                    request = new ComponentEvent();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.mouseClickX - componentX;
                                    request.mouseY = Mouse.mouseClickY - componentY;
                                    request.arguments = component.onClickRepeat;
                                    lowPriorityRequests.push(request);
                                }
                            }
                            if (component.mousePressed && mousePressed && component.onDrag != null) {
                                request = new ComponentEvent();
                                request.shouldExecute = true;
                                request.source = component;
                                request.mouseX = Mouse.lastMouseX - componentX;
                                request.mouseY = Mouse.lastMouseY - componentY;
                                request.arguments = component.onDrag;
                                lowPriorityRequests.push(request);
                            }
                            if (component.mousePressed && !mousePressed) {
                                component.mousePressed = false;
                                if (component.onRelease != null) {
                                    request = new ComponentEvent();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.lastMouseX - componentX;
                                    request.mouseY = Mouse.lastMouseY - componentY;
                                    request.arguments = component.onRelease;
                                    mediumPriorityRequests.push(request);
                                }
                            }
                            if (mousePressed && component.onHold != null) {
                                request = new ComponentEvent();
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
                                    request = new ComponentEvent();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.lastMouseX - componentX;
                                    request.mouseY = Mouse.lastMouseY - componentY;
                                    request.arguments = component.onMouseOver;
                                    lowPriorityRequests.push(request);
                                }
                            }
                            if (component.mouseHover && mouseOver && component.onMouseRepeat != null) {
                                request = new ComponentEvent();
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
                                    request = new ComponentEvent();
                                    request.shouldExecute = true;
                                    request.source = component;
                                    request.mouseX = Mouse.lastMouseX - componentX;
                                    request.mouseY = Mouse.lastMouseY - componentY;
                                    request.arguments = component.onMouseLeave;
                                    mediumPriorityRequests.push(request);
                                }
                            }
                            if (component.onTimer != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onTimer;
                                highPriorityRequests.push(request);
                            }
                            @Pc(966) ComponentEvent request2;
                            if (component.onVarcTransmit != null && VarcDomain.updatedVarcsWriterIndex > component.updatedVarcsReaderIndex) {
                                if (component.varcTriggers == null || VarcDomain.updatedVarcsWriterIndex - component.updatedVarcsReaderIndex > 32) {
                                    request = new ComponentEvent();
                                    request.source = component;
                                    request.arguments = component.onVarcTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label563: for (hotkeyIndex = component.updatedVarcsReaderIndex; hotkeyIndex < VarcDomain.updatedVarcsWriterIndex; hotkeyIndex++) {
                                        skill = VarcDomain.updatedVarcs[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.varcTriggers.length; cooldownDelay++) {
                                            if (component.varcTriggers[cooldownDelay] == skill) {
                                                request2 = new ComponentEvent();
                                                request2.source = component;
                                                request2.arguments = component.onVarcTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label563;
                                            }
                                        }
                                    }
                                }
                                component.updatedVarcsReaderIndex = VarcDomain.updatedVarcsWriterIndex;
                            }
                            if (component.onVarcstrTransmit != null && VarcDomain.updatedVarcstrsWriterIndex > component.updatedVarcstrsReaderIndex) {
                                if (component.varcstrTriggers == null || VarcDomain.updatedVarcstrsWriterIndex - component.updatedVarcstrsReaderIndex > 32) {
                                    request = new ComponentEvent();
                                    request.source = component;
                                    request.arguments = component.onVarcstrTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label539: for (hotkeyIndex = component.updatedVarcstrsReaderIndex; hotkeyIndex < VarcDomain.updatedVarcstrsWriterIndex; hotkeyIndex++) {
                                        skill = VarcDomain.updatedVarcstrs[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.varcstrTriggers.length; cooldownDelay++) {
                                            if (component.varcstrTriggers[cooldownDelay] == skill) {
                                                request2 = new ComponentEvent();
                                                request2.source = component;
                                                request2.arguments = component.onVarcstrTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label539;
                                            }
                                        }
                                    }
                                }
                                component.updatedVarcstrsReaderIndex = VarcDomain.updatedVarcstrsWriterIndex;
                            }
                            if (component.onVarpTransmit != null && VarpDomain.updatedVarpsWriterIndex > component.updatedVarpsReaderIndex) {
                                if (component.varpTriggers == null || VarpDomain.updatedVarpsWriterIndex - component.updatedVarpsReaderIndex > 32) {
                                    request = new ComponentEvent();
                                    request.source = component;
                                    request.arguments = component.onVarpTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label515: for (hotkeyIndex = component.updatedVarpsReaderIndex; hotkeyIndex < VarpDomain.updatedVarpsWriterIndex; hotkeyIndex++) {
                                        skill = VarpDomain.updatedVarps[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.varpTriggers.length; cooldownDelay++) {
                                            if (component.varpTriggers[cooldownDelay] == skill) {
                                                request2 = new ComponentEvent();
                                                request2.source = component;
                                                request2.arguments = component.onVarpTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label515;
                                            }
                                        }
                                    }
                                }
                                component.updatedVarpsReaderIndex = VarpDomain.updatedVarpsWriterIndex;
                            }
                            if (component.onInvTransmit != null && Inv.updatedInventoriesWriterIndex > component.updatedInventoriesReaderIndex) {
                                if (component.inventoryTriggers == null || Inv.updatedInventoriesWriterIndex - component.updatedInventoriesReaderIndex > 32) {
                                    request = new ComponentEvent();
                                    request.source = component;
                                    request.arguments = component.onInvTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label491: for (hotkeyIndex = component.updatedInventoriesReaderIndex; hotkeyIndex < Inv.updatedInventoriesWriterIndex; hotkeyIndex++) {
                                        skill = Inv.updatedInventories[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.inventoryTriggers.length; cooldownDelay++) {
                                            if (component.inventoryTriggers[cooldownDelay] == skill) {
                                                request2 = new ComponentEvent();
                                                request2.source = component;
                                                request2.arguments = component.onInvTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label491;
                                            }
                                        }
                                    }
                                }
                                component.updatedInventoriesReaderIndex = Inv.updatedInventoriesWriterIndex;
                            }
                            if (component.onStatTransmit != null && PlayerSkillXpTable.updatedStatsWriterIndex > component.updatedStatsReaderIndex) {
                                if (component.statTriggers == null || PlayerSkillXpTable.updatedStatsWriterIndex - component.updatedStatsReaderIndex > 32) {
                                    request = new ComponentEvent();
                                    request.source = component;
                                    request.arguments = component.onStatTransmit;
                                    lowPriorityRequests.push(request);
                                } else {
                                    label467: for (hotkeyIndex = component.updatedStatsReaderIndex; hotkeyIndex < PlayerSkillXpTable.updatedStatsWriterIndex; hotkeyIndex++) {
                                        skill = PlayerSkillXpTable.updatedStats[hotkeyIndex & 0x1F];
                                        for (cooldownDelay = 0; cooldownDelay < component.statTriggers.length; cooldownDelay++) {
                                            if (component.statTriggers[cooldownDelay] == skill) {
                                                request2 = new ComponentEvent();
                                                request2.source = component;
                                                request2.arguments = component.onStatTransmit;
                                                lowPriorityRequests.push(request2);
                                                break label467;
                                            }
                                        }
                                    }
                                }
                                component.updatedStatsReaderIndex = PlayerSkillXpTable.updatedStatsWriterIndex;
                            }
                            if (ChatHistory.transmitAt > component.lastTransmitTimer && component.onMsg != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onMsg;
                                lowPriorityRequests.push(request);
                            }
                            if (FriendList.transmitAt > component.lastTransmitTimer && component.onFriendTransmit != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onFriendTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (ClanChat.transmitAt > component.lastTransmitTimer && component.onClanTransmit != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onClanTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (StockMarketManager.transmitAt > component.lastTransmitTimer && component.onStockTransmit != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onStockTransmit;
                                lowPriorityRequests.push(request);
                            }
                            if (miscTransmitAt > component.lastTransmitTimer && component.onMiscTransmit != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onMiscTransmit;
                                lowPriorityRequests.push(request);
                            }
                            component.lastTransmitTimer = transmitTimer;
                            if (component.onKey != null) {
                                for (hotkeyIndex = 0; hotkeyIndex < keyQueueSize; hotkeyIndex++) {
                                    @Pc(1430) ComponentEvent local1430 = new ComponentEvent();
                                    local1430.source = component;
                                    local1430.keyCode = keyCodes[hotkeyIndex];
                                    local1430.keyChar = keyChars[hotkeyIndex];
                                    local1430.arguments = component.onKey;
                                    lowPriorityRequests.push(local1430);
                                }
                            }
                            if (Camera.shouldReverse && component.onMinimapUnlock != null) {
                                request = new ComponentEvent();
                                request.source = component;
                                request.arguments = component.onMinimapUnlock;
                                lowPriorityRequests.push(request);
                            }
                        }
                    }
                    if (!component.if3 && dragSource == null && clickedInventoryComponent == null && !ClientScriptRunner.menuVisible) {
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
                        if (component.scrollMaxV > component.height) {
                            handleScrollbar(Mouse.lastMouseY, component.height, component, Mouse.lastMouseX, componentX + component.width, componentY, component.scrollMaxV);
                        }
                    }
                    if (component.type == 0) {
                        processComponents(components, component.id, effectiveClipLeft, effectiveClipTop, effectiveClipRight, effectiveClipBottom, componentX - component.scrollX, componentY - component.scrollY);
                        if (component.createdComponents != null) {
                            processComponents(component.createdComponents, component.id, effectiveClipLeft, effectiveClipTop, effectiveClipRight, effectiveClipBottom, componentX - component.scrollX, componentY - component.scrollY);
                        }
                        @Pc(1595) SubInterface subInterface = (SubInterface) openInterfaces.get((long) component.id);
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
        if (load(interfaceId)) {
            updateComponentState(-1, cachedComponents[interfaceId]);
        }
    }

    @OriginalMember(owner = "client!runetek4.client", name = "a", descriptor = "(Lclient!be;)Lclient!be;")
    public static Component canAcceptDrop(@OriginalArg(0) Component component) {
        @Pc(4) int dragDepth = getServerActiveProperties(component).getDragDepth();
        if (dragDepth == 0) {
            return null;
        }
        for (@Pc(10) int level = 0; level < dragDepth; level++) {
            component = getComponent(component.layer);
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
            if (component != null && component.layer == parentId && (!component.if3 || !isHidden(component))) {
                if (component.type == 0) {
                    if (!component.if3 && isHidden(component) && component != previousMouseOverComponent) {
                        continue;
                    }
                    updateComponentState(component.id, components);
                    if (component.createdComponents != null) {
                        updateComponentState(component.id, component.createdComponents);
                    }
                    @Pc(73) SubInterface subInterface = (SubInterface) openInterfaces.get((long) component.id);
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
                    if (component.modelRotationSpeed != 0 && !component.if3) {
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
        for (@Pc(18) SubInterface subInterface = (SubInterface) openInterfaces.head(); subInterface != null; subInterface = (SubInterface) openInterfaces.next()) {
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
                if (!child.if3 || !isHidden(child)) {
                    if (child.clientcode > 0) {
                        updateGenderDependentComponents(child);
                    }
                    @Pc(114) int renderY = offsetY + child.y;
                    @Pc(117) int transparency = child.alpha;
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
                            clipTop2 -= ClientScriptRunner.dragStartY;
                            if (clipTop2 < minY) {
                                clipTop2 = minY;
                            }
                            if (clipTop2 + child.height > ClientScriptRunner.containerComponent.height + minY) {
                                clipTop2 = ClientScriptRunner.containerComponent.height + minY - child.height;
                            }
                            renderY = clipTop2;
                            clipLeft2 -= ClientScriptRunner.dragStartX;
                            if (ClientScriptRunner.minX > clipLeft2) {
                                clipLeft2 = ClientScriptRunner.minX;
                            }
                            if (ClientScriptRunner.containerComponent.width + ClientScriptRunner.minX < child.width + clipLeft2) {
                                clipLeft2 = ClientScriptRunner.containerComponent.width + ClientScriptRunner.minX - child.width;
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
                    if (!child.if3 || clipRight3 > clipLeft2 && clipTop2 < clipBottom3) {
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
                                if (MiniMap.state != 0 && MiniMap.state != 3 || ClientScriptRunner.menuVisible || clipLeft2 > ClientScriptRunner.scriptMouseX || ClientScriptRunner.scriptMouseY < clipTop2 || ClientScriptRunner.scriptMouseX >= clipRight3 || clipBottom3 <= ClientScriptRunner.scriptMouseY) {
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
                                if (MiniMenu.useWithActive && (MiniMenu.useWithMask & 0x40) != 0) {
                                    @Pc(583) Component local583 = getCreatedComponent(MiniMenu.useWithComponentId, MiniMenu.useWithSlot);
                                    if (local583 == null) {
                                        MiniMenu.handleUseWith();
                                    } else {
                                        MiniMenu.addActionRow(MiniMenu.useWithCursor, 1L, MiniMenu.aClass100_961, tileX, (short) 11, MiniMenu.aClass100_545, tileZ);
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
                        if (!ClientScriptRunner.menuVisible) {
                            if (child.type == Component.TYPE_LAYER && child.noClickThrough && ClientScriptRunner.scriptMouseX >= clipLeft2 && ClientScriptRunner.scriptMouseY >= clipTop2 && ClientScriptRunner.scriptMouseX < clipRight3 && clipBottom3 > ClientScriptRunner.scriptMouseY && !Cheat.testOpacity) {
                                MiniMenu.menuActionRow = 1;
                                MiniMenu.cursors[0] = MiniMenu.defaultCursor;
                                MiniMenu.ops[0] = LocalizedText.CANCEL;
                                MiniMenu.opBases[0] = JString.EMPTY;
                                MiniMenu.actions[0] = 1005;
                            }
                            if (clipLeft2 <= ClientScriptRunner.scriptMouseX && clipTop2 <= ClientScriptRunner.scriptMouseY && clipRight3 > ClientScriptRunner.scriptMouseX && clipBottom3 > ClientScriptRunner.scriptMouseY) {
                                MiniMenu.addMiniMenuOptions(ClientScriptRunner.scriptMouseY - renderY, -renderX + ClientScriptRunner.scriptMouseX, child);
                            }
                        }
                        if (child.type == Component.TYPE_LAYER) {
                            if (!child.if3 && isHidden(child) && previousMouseOverComponent != child) {
                                continue;
                            }
                            if (!child.if3) {
                                if (child.scrollMaxV - child.height < child.scrollY) {
                                    child.scrollY = child.scrollMaxV - child.height;
                                }
                                if (child.scrollY < 0) {
                                    child.scrollY = 0;
                                }
                            }
                            draw(clipLeft2, renderY - child.scrollY, -child.scrollX + renderX, children, clipRight3, child.id, clipTop2, clipBottom3, rectangle);
                            if (child.createdComponents != null) {
                                draw(clipLeft2, renderY - child.scrollY, -child.scrollX + renderX, child.createdComponents, clipRight3, child.id, clipTop2, clipBottom3, rectangle);
                            }
                            @Pc(1186) SubInterface local1186 = (SubInterface) openInterfaces.get((long) child.id);
                            if (local1186 != null) {
                                if (local1186.modalType == 0 && !ClientScriptRunner.menuVisible && ClientScriptRunner.scriptMouseX >= clipLeft2 && clipTop2 <= ClientScriptRunner.scriptMouseY && clipRight3 > ClientScriptRunner.scriptMouseX && ClientScriptRunner.scriptMouseY < clipBottom3 && !Cheat.testOpacity) {
                                    MiniMenu.ops[0] = LocalizedText.CANCEL;
                                    MiniMenu.menuActionRow = 1;
                                    MiniMenu.cursors[0] = MiniMenu.defaultCursor;
                                    MiniMenu.actions[0] = 1005;
                                    MiniMenu.opBases[0] = JString.EMPTY;
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
                            if (child.type == 0 && !child.if3 && child.scrollMaxV > child.height) {
                                drawScrollbar(child.scrollY, child.scrollMaxV, child.width + renderX, renderY, child.height);
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
                                                    if (MiniMenu.anInt5014 == 1 && MiniMenu.anInt4370 == clipRight2 && child.id == MiniMap.anInt5062) {
                                                        sprite = Inv.getObjectSprite(2, objId, child.objDrawText, child.invSlotObjCount[clipRight2], 0);
                                                    } else {
                                                        sprite = Inv.getObjectSprite(1, objId, child.objDrawText, child.invSlotObjCount[clipRight2], 3153952);
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
                                                            if (bottom < dragOffsetY + colorValue + 32 && local1571.scrollY < local1571.scrollMaxV - local1571.height) {
                                                                local1611 = (colorValue + dragOffsetY + 32 - bottom) * Protocol.sceneDelta / 3;
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
                                            if (child.if3 && child.objId != -1) {
                                                @Pc(1989) ObjType local1989 = ObjTypeList.get(child.objId);
                                                local1934 = local1989.name;
                                                if (local1934 == null) {
                                                    local1934 = MiniMenu.NULL;
                                                }
                                                if ((local1989.stackable == 1 || child.objCount != 1) && child.objCount != -1) {
                                                    local1934 = JString.concatenate(new JString[] { MiniMenu.COLOR_LIGHT_ORANGE, local1934, JString.aClass100_375, StringUtils.formatNumber(child.objCount) });
                                                }
                                            }
                                            if (ClientScriptRunner.modalBackgroundComponent == child) {
                                                clipBottom2 = child.color;
                                                local1934 = LocalizedText.PLEASEWAIT;
                                            }
                                            if (!child.if3) {
                                                local1934 = JString.processStringTokens(child, local1934);
                                            }
                                            local1921.drawInterfaceText(local1934, renderX, renderY, child.width, child.height, clipBottom2, child.shadowed ? 0 : -1, child.halign, child.valign, child.vpadding);
                                        } else if (Component.aBoolean72) {
                                            redraw(child);
                                        }
                                    } else if (child.type == Component.TYPE_GRAPHIC) {
                                        @Pc(2094) Sprite sprite;
                                        if (child.if3) {
                                            if (child.objId == -1) {
                                                sprite = child.method489(false);
                                            } else {
                                                sprite = Inv.getObjectSprite(child.outlineThickness, child.objId, child.objDrawText, child.objCount, child.shadowColor);
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
                                            if (child.objId != -1) {
                                                local2611 = ObjTypeList.get(child.objId);
                                                if (local2611 != null) {
                                                    local2611 = local2611.getMeshAddress(child.objCount);
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
                                                        local2589 = local2751.appearance.createAnimatedBodyModel(null, -1, null, local2760, 0, -1, 0, child.animationId, 0);
                                                    }
                                                }
                                            } else if (clipBottom2 == -1) {
                                                local2589 = child.method488(-1, null, -1, 0, local2587, PlayerList.self.appearance);
                                                if (local2589 == null && Component.aBoolean72) {
                                                    redraw(child);
                                                }
                                            } else {
                                                @Pc(2689) SeqType local2689 = SeqTypeList.get(clipBottom2);
                                                local2589 = child.method488(child.animationFrame, local2689, child.animationId, child.animationDelay, local2587, PlayerList.self.appearance);
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
                                                    if (child.if3) {
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
                                                    if (!child.if3) {
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
                                                                local3159 = JString.concatenate(new JString[] { MiniMenu.COLOR_LIGHT_ORANGE, local2611.name, JString.aClass100_978 });
                                                            } else {
                                                                local3159 = JString.concatenate(new JString[] { MiniMenu.COLOR_LIGHT_ORANGE, local2611.name, JString.aClass100_375, StringUtils.formatNumber(child.invSlotObjCount[clipBottom2]) });
                                                            }
                                                            dragOffsetY = renderX + tempValue * (child.invMarginX + 115);
                                                            objId = (child.invMarginY + 12) * temp + renderY;
                                                            if (child.halign == 0) {
                                                                local1921.renderLeft(local3159, dragOffsetY, objId, child.color, child.shadowed ? 0 : -1);
                                                            } else if (child.halign == 1) {
                                                                local1921.renderCenter(local3159, dragOffsetY + 57, objId, child.color, child.shadowed ? 0 : -1);
                                                            } else {
                                                                local1921.renderRight(local3159, dragOffsetY + 115 - 1, objId, child.color, child.shadowed ? 0 : -1);
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
                ClientScriptRunner.defaultSpriteId = component.spriteId;
                ClientScriptRunner.alternateSpriteId = component.activeSpriteId;
            }
            if (PlayerAppearance.DEFAULT.gender) {
                component.spriteId = ClientScriptRunner.defaultSpriteId;
            } else {
                component.spriteId = ClientScriptRunner.alternateSpriteId;
            }
        } else if (contentType == 325) {
            if (ClientScriptRunner.defaultSpriteId == -1) {
                ClientScriptRunner.alternateSpriteId = component.activeSpriteId;
                ClientScriptRunner.defaultSpriteId = component.spriteId;
            }
            if (PlayerAppearance.DEFAULT.gender) {
                component.spriteId = ClientScriptRunner.alternateSpriteId;
            } else {
                component.spriteId = ClientScriptRunner.defaultSpriteId;
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
}
