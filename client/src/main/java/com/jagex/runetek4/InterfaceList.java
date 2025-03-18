package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.game.config.cursortype.CursorType;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

public class InterfaceList {
    @OriginalMember(owner = "runetek4.client!sg", name = "q", descriptor = "[I")
    public static final int[] keyCodes = new int[128];
    @OriginalMember(owner = "runetek4.client!pa", name = "R", descriptor = "[Z")
    public static final boolean[] aBooleanArray100 = new boolean[100];
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
    public static final LinkedList lowPriorityRequests = new LinkedList();
    @OriginalMember(owner = "client!je", name = "fb", descriptor = "I")
    public static int transmitTimer = 1;
    @OriginalMember(owner = "runetek4.client!md", name = "W", descriptor = "I")
    public static int topLevelInterace = -1;
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
    public static Component aClass13_26 = null;
    @OriginalMember(owner = "runetek4.client!rg", name = "s", descriptor = "I")
    public static int anInt5574 = -1;
    @OriginalMember(owner = "runetek4.client!og", name = "e", descriptor = "Lclient!be;")
    public static Component aClass13_22;
    @OriginalMember(owner = "runetek4.client!gd", name = "j", descriptor = "I")
    public static int lastItemDragTime = 0;
    @OriginalMember(owner = "runetek4.client!qk", name = "f", descriptor = "I")
    public static int clickedInventoryComponentY = 0;

    @OriginalMember(owner = "client!gg", name = "c", descriptor = "(II)V")
    public static void method1750(@OriginalArg(0) int arg0) {
        if (!Static64.aBoolean111) {
            arg0 = -1;
        }

        if (arg0 == client.currentCursor) {
            return;
        }

        if (arg0 != -1) {
            @Pc(24) CursorType cursorType = CursorTypeList.get(arg0);
            @Pc(28) SoftwareSprite local28 = cursorType.getSprite();
            if (local28 == null) {
                arg0 = -1;
            } else {
                GameShell.signLink.setCursor(local28.method301(), local28.innerWidth, GameShell.canvas, new Point(cursorType.hotspotx, cursorType.hotspoty), local28.innerHeight);
                client.currentCursor = arg0;
            }
        }
        if (arg0 == -1 && client.currentCursor != -1) {
            GameShell.signLink.setCursor(null, -1, GameShell.canvas, new Point(), -1);
            client.currentCursor = -1;
        }
    }

    @OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ZI)V")
    public static void method1596(@OriginalArg(0) boolean arg0) {
        if (arg0) {
            if (topLevelInterace != -1) {
                resetComponent(topLevelInterace);
            }
            for (@Pc(18) ComponentPointer local18 = (ComponentPointer) openInterfaces.head(); local18 != null; local18 = (ComponentPointer) openInterfaces.prev()) {
                closeInterface(true, local18);
            }
            topLevelInterace = -1;
            openInterfaces = new HashTable(8);
            createComponentMemoryBuffer();
            topLevelInterace = Static156.anInt3783;
            Static210.method3712(false);
            Static87.method1807();
            Static74.method1626(topLevelInterace);
        }
        Static35.anInt1092 = -1;
        method1750(Static270.anInt5794);
        PlayerList.self = new Player();
        PlayerList.self.zFine = 3000;
        PlayerList.self.xFine = 3000;
        if (!GlRenderer.enabled) {
            Static145.method2743(client.js5Archive8);
            client.processGameStatus(10);
            return;
        }
        if (Camera.cameraType == 2) {
            Camera.renderX = Camera.anInt5375 << 7;
            Camera.renderZ = Camera.anInt4232 << 7;
        } else {
            Camera.updateLoginScreenCamera();
        }
        FogManager.setInstantFade();
        LoginManager.setupLoadingScreenRegion();
        client.processGameStatus(28);
    }

    @OriginalMember(owner = "runetek4.client!ig", name = "a", descriptor = "(BI)V")
    public static void resetComponent(@OriginalArg(1) int componentId) {
        if (componentId == -1 || !Static223.loadedComponents[componentId]) {
            return;
        }
        CacheArchive.gameInterfaceJs5.unloadFile(componentId);
        if (Component.cachedComponents[componentId] == null) {
            return;
        }
        @Pc(27) boolean deleteFromCache = true;
        for (@Pc(29) int i = 0; i < Component.cachedComponents[componentId].length; i++) {
            if (Component.cachedComponents[componentId][i] != null) {
                if (Component.cachedComponents[componentId][i].type == 2) {
                    deleteFromCache = false;
                } else {
                    Component.cachedComponents[componentId][i] = null;
                }
            }
        }
        if (deleteFromCache) {
            Component.cachedComponents[componentId] = null;
        }
        Static223.loadedComponents[componentId] = false;
    }

    @OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(ZLclient!wk;Z)V")
    public static void closeInterface(@OriginalArg(0) boolean arg0, @OriginalArg(1) ComponentPointer arg1) {
        @Pc(9) int local9 = (int) arg1.nodeId;
        @Pc(16) int local16 = arg1.interfaceId;
        arg1.unlink();
        if (arg0) {
            resetComponent(local16);
        }
        Static273.method3214(local16);
        @Pc(32) Component local32 = getComponent(local9);
        if (local32 != null) {
            redraw(local32);
        }
        @Pc(41) int local41 = MiniMenu.menuActionRow;
        @Pc(43) int local43;
        for (local43 = 0; local43 < local41; local43++) {
            if (Static2.method5(MiniMenu.actions[local43])) {
                Static200.method3628(local43);
            }
        }
        if (MiniMenu.menuActionRow == 1) {
            ClientScriptRunner.aBoolean108 = false;
            Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
        } else {
            Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
            local43 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
            for (@Pc(75) int local75 = 0; local75 < MiniMenu.menuActionRow; local75++) {
                @Pc(88) int local88 = Fonts.b12Full.getStringWidth(Static269.method2228(local75));
                if (local43 < local88) {
                    local43 = local88;
                }
            }
            Static13.anInt436 = MiniMenu.menuActionRow * 15 + (aBoolean298 ? 26 : 22);
            Static24.anInt761 = local43 + 8;
        }
        if (topLevelInterace != -1) {
            Static54.method1304(1, topLevelInterace);
        }
    }

    @OriginalMember(owner = "runetek4.client!eb", name = "d", descriptor = "(I)V")
    public static void createComponentMemoryBuffer() {
        Component.cachedComponents = new Component[CacheArchive.gameInterfaceJs5.capacity()][];
        Static223.loadedComponents = new boolean[CacheArchive.gameInterfaceJs5.capacity()];
    }

    @OriginalMember(owner = "runetek4.client!jm", name = "a", descriptor = "(Z)V")
    public static void method2460() {
        if (topLevelInterace != -1) {
            Static96.method1949(topLevelInterace);
        }
        for (@Pc(15) int local15 = 0; local15 < rectangles; local15++) {
            if (aBooleanArray100[local15]) {
                rectangleRedraw[local15] = true;
            }
            Static223.aBooleanArray116[local15] = aBooleanArray100[local15];
            aBooleanArray100[local15] = false;
        }
        ClientScriptRunner.anInt2503 = -1;
        Static169.aClass13_18 = null;
        Static182.anInt4311 = client.loop;
        if (GlRenderer.enabled) {
            ClientScriptRunner.aBoolean299 = true;
        }
        anInt5574 = -1;
        if (topLevelInterace != -1) {
            rectangles = 0;
            CacheArchive.method182();
        }
        if (GlRenderer.enabled) {
            GlRaster.method1177();
        } else {
            Rasterizer.resetBounds();
        }
        Protocol.sceneDelta = 0;
    }

    @OriginalMember(owner = "runetek4.client!eg", name = "a", descriptor = "(IIIIIIII)V")
    public static void method1320(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
        if (Component.load(arg4)) {
            Static36.method946(Component.cachedComponents[arg4], -1, arg5, arg1, arg3, arg6, arg0, arg2);
        }
    }

    @OriginalMember(owner = "runetek4.client!af", name = "a", descriptor = "(BI)Lclient!be;")
    public static Component getComponent(@OriginalArg(1) int id) {
        @Pc(7) int interfaceId = id >> 16;
        @Pc(18) int componentId = id & 0xFFFF;
        if (Component.cachedComponents[interfaceId] == null || Component.cachedComponents[interfaceId][componentId] == null) {
            @Pc(33) boolean success = Component.load(interfaceId);
            if (!success) {
                return null;
            }
            // todo: this should not be necessary, data/server-related?
            if (Component.cachedComponents.length <= interfaceId || Component.cachedComponents[interfaceId].length <= componentId) {
                return null;
            }
        }
        return Component.cachedComponents[interfaceId][componentId];
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "b", descriptor = "(Lclient!be;)Lclient!bf;")
    public static ServerActiveProperties getServerActiveProperties(@OriginalArg(0) Component arg0) {
        @Pc(13) ServerActiveProperties local13 = (ServerActiveProperties) Static210.aClass133_21.getNode(((long) arg0.id << 32) + (long) arg0.createdComponentId);
        return local13 == null ? arg0.properties : local13;
    }

    @OriginalMember(owner = "runetek4.client!dg", name = "a", descriptor = "(ILclient!be;)V")
    public static void redraw(@OriginalArg(1) Component arg0) {
        if (Static182.anInt4311 == arg0.rectangleLoop) {
            aBooleanArray100[arg0.rectangle] = true;
        }
    }

    @OriginalMember(owner = "runetek4.client!runetek4.client", name = "c", descriptor = "(Lclient!be;)Z")
    public static boolean method947(@OriginalArg(0) Component arg0) {
        if (Cheat.qaOpTest) {
            if (getServerActiveProperties(arg0).anInt546 != 0) {
                return false;
            }
            if (arg0.type == 0) {
                return false;
            }
        }
        return arg0.hidden;
    }

    @OriginalMember(owner = "runetek4.client!qf", name = "a", descriptor = "(BII)Lclient!be;")
    public static Component method1418(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
        @Pc(7) Component local7 = getComponent(arg0);
        if (arg1 == -1) {
            return local7;
        } else if (local7 == null || local7.createdComponents == null || local7.createdComponents.length <= arg1) {
            return null;
        } else {
            return local7.createdComponents[arg1];
        }
    }
}
