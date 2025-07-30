package com.jagex.runetek4.client;

import com.jagex.runetek4.*;
import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.midi.MidiPlayer;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.audio.streaming.MusicPlayer;
import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.ui.chat.OverHeadChat;
import com.jagex.runetek4.game.logic.PathFinder;
import com.jagex.runetek4.game.locs.AttachLocRequest;
import com.jagex.runetek4.game.locs.ChangeLocRequest;
import com.jagex.runetek4.game.state.VarcDomain;
import com.jagex.runetek4.game.state.VarpDomain;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.network.security.ReflectionCheck;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.component.MiniMap;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.input.MouseCapturer;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.clientscript.DelayedStateChange;
import com.jagex.runetek4.entity.entity.NpcList;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.input.MouseWheel;
import com.jagex.runetek4.ui.component.ComponentList;
import com.jagex.runetek4.ui.component.MiniMenu;
import com.jagex.runetek4.ui.component.Crosshair;
import com.jagex.runetek4.ui.events.ComponentEvent;
import com.jagex.runetek4.util.debug.Cheat;
import com.jagex.runetek4.game.world.WorldMap;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;

public class Game {

    @OriginalMember(owner = "client!gg", name = "a", descriptor = "(Z)V")
    public static void updateGame() {
        // todo: consolidate/rename static classes
        if (Protocol.idleTimeout > 0) {
            Protocol.idleTimeout--;
        }
        if (Player.systemUpdateTimer > 1) {
            Player.systemUpdateTimer--;
            ComponentList.miscTransmitAt = ComponentList.transmitTimer;
        }
        if (LoginManager.aBoolean247) {
            LoginManager.aBoolean247 = false;
            tryReconnect();
            return;
        }
        for (@Pc(34) int i = 0; i < 100 && Protocol.readPacket(); i++) {
            // TODO why is this here?
        }
        if (Client.gameState != 30) {
            return;
        }
        ReflectionCheck.createClientScriptCheckPacket(Protocol.outboundBuffer); // runetek4.ReflectionCheck
        @Pc(60) Object mouseRecorder = MouseCapturer.instance.lock;
        @Pc(86) int offset;
        @Pc(79) int samples;
        @Pc(88) int i;
        @Pc(106) int y;
        @Pc(111) int x;
        @Pc(182) int dx;
        @Pc(189) int dy;
        synchronized (mouseRecorder) {
            if (!MouseCapturer.enabled) {
                MouseCapturer.instance.samples = 0;
            } else if (Mouse.clickButton != 0 || MouseCapturer.instance.samples >= 40) {
                Protocol.outboundBuffer.pIsaac1(123);
                samples = 0;
                Protocol.outboundBuffer.p1(0);
                offset = Protocol.outboundBuffer.offset;
                for (i = 0; MouseCapturer.instance.samples > i && Protocol.outboundBuffer.offset - offset < 240; i++) {
                    samples++;
                    y = MouseCapturer.instance.y[i];
                    x = MouseCapturer.instance.x[i];
                    if (y < 0) {
                        y = 0;
                    } else if (y > 65534) {
                        y = 65534;
                    }
                    if (x < 0) {
                        x = 0;
                    } else if (x > 65534) {
                        x = 65534;
                    }
                    @Pc(142) boolean outsideWindow = false;
                    if (MouseCapturer.instance.y[i] == -1 && MouseCapturer.instance.x[i] == -1) {
                        outsideWindow = true;
                        y = -1;
                        x = -1;
                    }
                    if (MouseCapturer.mouseRecorderPrevX != x || MouseCapturer.mouseRecorderPrevY != y) {
                        dx = x - MouseCapturer.mouseRecorderPrevX;
                        MouseCapturer.mouseRecorderPrevX = x;
                        dy = y - MouseCapturer.mouseRecorderPrevY;
                        MouseCapturer.mouseRecorderPrevY = y;
                        if (Protocol.anInt4762 < 8 && dx >= -32 && dx <= 31 && dy >= -32 && dy <= 31) {
                            dy += 32;
                            dx += 32;
                            Protocol.outboundBuffer.p2(dy + (Protocol.anInt4762 << 12) + (dx << 6));
                            Protocol.anInt4762 = 0;
                        } else if (Protocol.anInt4762 < 32 && dx >= -128 && dx <= 127 && dy >= -128 && dy <= 127) {
                            Protocol.outboundBuffer.p1(Protocol.anInt4762 + 128);
                            dy += 128;
                            dx += 128;
                            Protocol.outboundBuffer.p2((dx << 8) + dy);
                            Protocol.anInt4762 = 0;
                        } else if (Protocol.anInt4762 < 32) {
                            Protocol.outboundBuffer.p1(Protocol.anInt4762 + 192);
                            if (outsideWindow) {
                                Protocol.outboundBuffer.p4(Integer.MIN_VALUE);
                            } else {
                                Protocol.outboundBuffer.p4(x | y << 16);
                            }
                            Protocol.anInt4762 = 0;
                        } else {
                            Protocol.outboundBuffer.p2(Protocol.anInt4762 + 57344);
                            if (outsideWindow) {
                                Protocol.outboundBuffer.p4(Integer.MIN_VALUE);
                            } else {
                                Protocol.outboundBuffer.p4(x | y << 16);
                            }
                            Protocol.anInt4762 = 0;
                        }
                    } else if (Protocol.anInt4762 < 2047) {
                        Protocol.anInt4762++;
                    }
                }
                Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - offset);
                if (MouseCapturer.instance.samples > samples) {
                    MouseCapturer.instance.samples -= samples;
                    for (i = 0; i < MouseCapturer.instance.samples; i++) {
                        MouseCapturer.instance.x[i] = MouseCapturer.instance.x[samples + i];
                        MouseCapturer.instance.y[i] = MouseCapturer.instance.y[samples + i];
                    }
                } else {
                    MouseCapturer.instance.samples = 0;
                }
            }
        }
        if (Mouse.clickButton != 0) {
            @Pc(411) long loops = (Mouse.clickTime - Mouse.prevClickTime) / 50L;
            samples = Mouse.mouseClickY;
            if (samples < 0) {
                samples = 0;
            } else if (samples > 65535) {
                samples = 65535;
            }
            if (loops > 32767L) {
                loops = 32767L;
            }
            i = Mouse.mouseClickX;
            Mouse.prevClickTime = Mouse.clickTime;
            @Pc(437) byte button = 0;
            if (i < 0) {
                i = 0;
            } else if (i > 65535) {
                i = 65535;
            }
            x = (int) loops;
            if (Mouse.clickButton == 2) {
                button = 1;
            }
            Protocol.outboundBuffer.pIsaac1(75);
            Protocol.outboundBuffer.p2_alt3(button << 15 | x);
            Protocol.outboundBuffer.p4_alt3(i | samples << 16);
        }
        if (Protocol.anInt551 > 0) {
            Protocol.anInt551--;
        }
        if (Preferences.aBoolean63) {
            for (i = 0; i < ComponentList.keyQueueSize; i++) {
                offset = ComponentList.keyCodes[i];
                if (offset == 98 || offset == 99 || offset == 96 || offset == 97) {
                    Protocol.aBoolean228 = true;
                    break;
                }
            }
        } else if (Keyboard.pressedKeys[96] || Keyboard.pressedKeys[97] || Keyboard.pressedKeys[98] || Keyboard.pressedKeys[99]) {
            Protocol.aBoolean228 = true;
        }
        if (Protocol.aBoolean228 && Protocol.anInt551 <= 0) {
            Protocol.anInt551 = 20;
            Protocol.aBoolean228 = false;
            Protocol.outboundBuffer.pIsaac1(21);
            Protocol.outboundBuffer.p2_alt2(Camera.orbitCameraPitch);
            Protocol.outboundBuffer.p2_alt1(Camera.orbitCameraYaw);
        }
        if (GameShell.focus && !Protocol.prevFocus) {
            Protocol.prevFocus = true;
            Protocol.outboundBuffer.pIsaac1(22);
            Protocol.outboundBuffer.p1(1);
        }
        if (!GameShell.focus && Protocol.prevFocus) {
            Protocol.prevFocus = false;
            Protocol.outboundBuffer.pIsaac1(22);
            Protocol.outboundBuffer.p1(0);
        }
        if (!Preferences.sentToServer) {
            Protocol.outboundBuffer.pIsaac1(98);
            Protocol.outboundBuffer.p4(Preferences.toInt());
            Preferences.sentToServer = true;
        }
        SceneGraph.updatePlayerPlane();
        if (Client.gameState != 30) {
            return;
        }
        ChangeLocRequest.loop();
        AttachLocRequest.loop();
        SoundPlayer.loop(); // SoundPlayer
        LoginManager.idleNetCycles++;
        if (LoginManager.idleNetCycles > 750) {
            tryReconnect();
            return;
        }
        PlayerList.updatePlayers();
        NpcList.updateNpcs();
        OverHeadChat.tickChatTimers(); // OverheadChat
        if (WorldMap.component != null) {
            WorldMap.method447();
        }
        // VarpDomain
        for (i = VarpDomain.poll(true); i != -1; i = VarpDomain.poll(false)) {
            VarpDomain.refreshMagicVarp(i);
            VarpDomain.updatedVarps[VarpDomain.updatedVarpsWriterIndex++ & 0x1F] = i;
        }
        @Pc(782) int rand;

        for (@Pc(709) DelayedStateChange change = DelayedStateChange.poll(); change != null; change = DelayedStateChange.poll()) {
            samples = change.getType();
            i = change.getId();
            if (samples == 1) {
                VarcDomain.varcs[i] = change.intArg1;
                VarcDomain.updatedVarcs[VarcDomain.updatedVarcsWriterIndex++ & 0x1F] = i;
            } else if (samples == 2) {
                VarcDomain.varcstrs[i] = change.stringArg;
                VarcDomain.updatedVarcstrs[VarcDomain.updatedVarcstrsWriterIndex++ & 0x1F] = i;
            } else {
                @Pc(773) Component component;
                if (samples == 3) {
                    component = ComponentList.getComponent(i);
                    if (!change.stringArg.strEquals(component.text)) {
                        component.text = change.stringArg;
                        ComponentList.redraw(component);
                    }
                } else if (samples == 4) {
                    component = ComponentList.getComponent(i);
                    x = change.intArg1;
                    dx = change.intArg2;
                    rand = change.intArg3;
                    if (component.modelType != x || component.modelId != rand || dx != component.anInt498) {
                        component.modelId = rand;
                        component.anInt498 = dx;
                        component.modelType = x;
                        ComponentList.redraw(component);
                    }
                } else if (samples == 5) {
                    component = ComponentList.getComponent(i);
                    if (component.modelSeqId != change.intArg1 || change.intArg1 == -1) {
                        component.animationFrame = 1;
                        component.animationDelay = 0;
                        component.modelSeqId = change.intArg1;
                        component.animationId = 0;
                        ComponentList.redraw(component);
                    }
                } else if (samples == 6) {
                    y = change.intArg1;
                    x = y >> 10 & 0x1F;
                    dx = y & 0x1F;
                    rand = y >> 5 & 0x1F;
                    @Pc(1189) Component local1189 = ComponentList.getComponent(i);
                    dy = (dx << 3) + (rand << 11) + (x << 19);
                    if (dy != local1189.color) {
                        local1189.color = dy;
                        ComponentList.redraw(local1189);
                    }
                } else if (samples == 7) {
                    component = ComponentList.getComponent(i);
                    // todo: this should not be necessary, data/server-related?
                    if (component != null) {
                        @Pc(1145) boolean hidden = change.intArg1 == 1;
                        if (hidden != component.hidden) {
                            component.hidden = hidden;
                            ComponentList.redraw(component);
                        }
                    }
                } else if (samples == 8) {
                    component = ComponentList.getComponent(i);
                    if (change.intArg1 != component.modelXAngle || component.modelYAngle != change.intArg3 || change.intArg2 != component.modelZoom) {
                        component.modelXAngle = change.intArg1;
                        component.modelZoom = change.intArg2;
                        component.modelYAngle = change.intArg3;
                        if (component.objId != -1) {
                            if (component.anInt451 > 0) {
                                component.modelZoom = component.modelZoom * 32 / component.anInt451;
                            } else if (component.baseWidth > 0) {
                                component.modelZoom = component.modelZoom * 32 / component.baseWidth;
                            }
                        }
                        ComponentList.redraw(component);
                    }
                } else if (samples == 9) {
                    component = ComponentList.getComponent(i);
                    if (change.intArg1 != component.objId || component.objCount != change.intArg3) {
                        component.objId = change.intArg1;
                        component.objCount = change.intArg3;
                        ComponentList.redraw(component);
                    }
                } else if (samples == 10) {
                    component = ComponentList.getComponent(i);
                    if (component.modelXOffset != change.intArg1 || change.intArg3 != component.modelZOffset || component.modelYOffset != change.intArg2) {
                        component.modelZOffset = change.intArg3;
                        component.modelYOffset = change.intArg2;
                        component.modelXOffset = change.intArg1;
                        ComponentList.redraw(component);
                    }
                } else if (samples == 11) {
                    component = ComponentList.getComponent(i);
                    component.x = component.baseX = change.intArg1;
                    component.yMode = 0;
                    component.xMode = 0;
                    component.y = component.baseY = change.intArg3;
                    ComponentList.redraw(component);
                } else if (samples == 12) {
                    component = ComponentList.getComponent(i);
                    x = change.intArg1;
                    if (component != null && component.type == 0) {
                        if (x > component.scrollMaxV - component.height) {
                            x = component.scrollMaxV - component.height;
                        }
                        if (x < 0) {
                            x = 0;
                        }
                        if (x != component.scrollY) {
                            component.scrollY = x;
                            ComponentList.redraw(component);
                        }
                    }
                } else if (samples == 13) {
                    component = ComponentList.getComponent(i);
                    component.modelRotationSpeed = change.intArg1;
                }
            }
        }
        // Cross
        if (Crosshair.CrosshairMode != 0) {
            Crosshair.CrosshairCycle += 20;
            if (Crosshair.CrosshairCycle >= 400) {
                Crosshair.CrosshairMode = 0;
            }
        }
        Protocol.sceneDelta++;
        if (MiniMenu.pressedInventoryComponent != null) {
            MiniMenu.anInt2043++;
            if (MiniMenu.anInt2043 >= 15) {
                ComponentList.redraw(MiniMenu.pressedInventoryComponent);
                MiniMenu.pressedInventoryComponent = null;
            }
        }
        @Pc(1361) Component component;
        if (ComponentList.clickedInventoryComponent != null) {
            ComponentList.redraw(ComponentList.clickedInventoryComponent);
            if (ComponentList.clickedInventoryComponentX + 5 < Mouse.lastMouseX || Mouse.lastMouseX < ComponentList.clickedInventoryComponentX - 5 || ComponentList.clickedInventoryComponentY + 5 < Mouse.lastMouseY || ComponentList.clickedInventoryComponentY - 5 > Mouse.lastMouseY) {
                ComponentList.draggingClickedInventoryObject = true;
            }
            ComponentList.lastItemDragTime++;
            if (Mouse.pressedButton == 0) {
                if (ComponentList.draggingClickedInventoryObject && ComponentList.lastItemDragTime >= 5) {
                    if (ComponentList.clickedInventoryComponent == ComponentList.mouseOverInventoryComponent && ComponentList.selectedInventorySlot != MiniMenu.mouseInvInterfaceIndex) {
                        component = ComponentList.clickedInventoryComponent;
                        @Pc(1363) byte moveItemInsertionMode = 0;
                        if (VarpDomain.bankInsertMode == 1 && component.contentType == 206) {
                            moveItemInsertionMode = 1;
                        }
                        if (component.invSlotObjId[MiniMenu.mouseInvInterfaceIndex] <= 0) {
                            moveItemInsertionMode = 0;
                        }
                        if (ComponentList.getServerActiveProperties(component).isObjReplaceEnabled()) {
                            y = ComponentList.selectedInventorySlot;
                            x = MiniMenu.mouseInvInterfaceIndex;
                            component.invSlotObjId[x] = component.invSlotObjId[y];
                            component.invSlotObjCount[x] = component.invSlotObjCount[y];
                            component.invSlotObjId[y] = -1;
                            component.invSlotObjCount[y] = 0;
                        } else if (moveItemInsertionMode == 1) {
                            x = MiniMenu.mouseInvInterfaceIndex;
                            y = ComponentList.selectedInventorySlot;
                            while (x != y) {
                                if (y > x) {
                                    component.swapObjs(y - 1, y);
                                    y--;
                                } else if (x > y) {
                                    component.swapObjs(y + 1, y);
                                    y++;
                                }
                            }
                        } else {
                            component.swapObjs(MiniMenu.mouseInvInterfaceIndex, ComponentList.selectedInventorySlot);
                        }
                        Protocol.outboundBuffer.pIsaac1(231);
                        Protocol.outboundBuffer.p2(ComponentList.selectedInventorySlot);
                        Protocol.outboundBuffer.p4_alt1(ComponentList.clickedInventoryComponent.id);
                        Protocol.outboundBuffer.p2_alt2(MiniMenu.mouseInvInterfaceIndex);
                        Protocol.outboundBuffer.p1_alt3(moveItemInsertionMode);
                    }
                } else if ((VarpDomain.oneMouseButton == 1 || MiniMenu.isComponentAction(MiniMenu.menuActionRow - 1)) && MiniMenu.menuActionRow > 2) {
                    ClientScriptRunner.determineMenuSize();
                } else if (MiniMenu.menuActionRow > 0) {
                    MiniMenu.processMenuActions();
                }
                Mouse.clickButton = 0;
                MiniMenu.anInt2043 = 10;
                ComponentList.clickedInventoryComponent = null;
            }
        }
        ComponentList.dragActive = false;
        ComponentList.targetComponent = null;
        ComponentList.canDrag = false;
        ComponentList.keyQueueSize = 0;
        component = ComponentList.aClass13_22;
        ComponentList.aClass13_22 = null;
        @Pc(1508) Component local1508 = Protocol.aClass13_11;
        Protocol.aClass13_11 = null;
        while (Keyboard.nextKey() && ComponentList.keyQueueSize < 128) {
            ComponentList.keyCodes[ComponentList.keyQueueSize] = Keyboard.keyCode;
            ComponentList.keyChars[ComponentList.keyQueueSize] = Keyboard.keyChar;
            ComponentList.keyQueueSize++;
        }
        // WorldMap.component
        WorldMap.component = null;
        if (ComponentList.topLevelInterface != -1) {
            ComponentList.renderInterface(0, 0, 0, GameShell.canvasWidth, ComponentList.topLevelInterface, 0, GameShell.canvasHeigth);
        }
        ComponentList.transmitTimer++;
        while (true) {
            // todo: this is actually split up into low/medium/high
            @Pc(1569) Component highPriorityComponent;
            @Pc(1560) Component highPrioritySource;
            @Pc(1555) ComponentEvent highPriorityRequest;
            do {
                highPriorityRequest = (ComponentEvent) ComponentList.highPriorityRequests.removeHead();
                if (highPriorityRequest == null) {
                    while (true) {
                        do {
                            highPriorityRequest = (ComponentEvent) ComponentList.mediumPriorityRequests.removeHead();
                            if (highPriorityRequest == null) {
                                while (true) {
                                    do {
                                        highPriorityRequest = (ComponentEvent) ComponentList.lowPriorityRequests.removeHead();
                                        if (highPriorityRequest == null) {
                                            if (WorldMap.component == null) {
                                                ComponentList.anInt3337 = 0;
                                            }
                                            if (ClientScriptRunner.dragComponent != null) {
                                                ClientScriptRunner.handleComponentDrag();
                                            }
                                            if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81] && MouseWheel.wheelRotation != 0) {
                                                y = Player.plane - MouseWheel.wheelRotation;
                                                if (y < 0) {
                                                    y = 0;
                                                } else if (y > 3) {
                                                    y = 3;
                                                }
                                                // Cheat
                                                Cheat.teleport(PlayerList.self.movementQueueX[0] + Camera.originX, PlayerList.self.movementQueueZ[0] + Camera.originZ, y);
                                            }
                                            if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                                                if (MiniMenu.clickTileX != -1) {
                                                    Cheat.teleport(Camera.originX + MiniMenu.clickTileX, Camera.originZ + MiniMenu.clickTileZ, Player.plane);
                                                }
                                                Protocol.anInt4422 = 0;
                                                MiniMenu.anInt3096 = 0;
                                            } else if (MiniMenu.anInt3096 == 2) {
                                                if (MiniMenu.clickTileX != -1) {
                                                    Protocol.outboundBuffer.pIsaac1(131);
                                                    Protocol.outboundBuffer.p4_alt3(MiniMenu.useWithComponentId);
                                                    Protocol.outboundBuffer.p2_alt2(Camera.originX + MiniMenu.clickTileX);
                                                    Protocol.outboundBuffer.p2_alt3(MiniMenu.useWithSlot);
                                                    Protocol.outboundBuffer.p2_alt2(MiniMenu.clickTileZ + Camera.originZ);
                                                    Crosshair.CrosshairMode = 1;
                                                    Crosshair.CrosshairCycle = 0;
                                                    Crosshair.y = Mouse.mouseClickY;
                                                    Crosshair.x = Mouse.mouseClickX;
                                                }
                                                MiniMenu.anInt3096 = 0;
                                            } else if (Protocol.anInt4422 == 2) {
                                                if (MiniMenu.clickTileX != -1) {
                                                    Protocol.outboundBuffer.pIsaac1(179);
                                                    Protocol.outboundBuffer.p2(Camera.originZ + MiniMenu.clickTileZ);
                                                    Protocol.outboundBuffer.p2(MiniMenu.clickTileX + Camera.originX);
                                                    Crosshair.CrosshairCycle = 0;
                                                    Crosshair.CrosshairMode = 1;
                                                    Crosshair.x = Mouse.mouseClickX;
                                                    Crosshair.y = Mouse.mouseClickY;
                                                }
                                                Protocol.anInt4422 = 0;
                                            } else if (MiniMenu.clickTileX != -1 && MiniMenu.anInt3096 == 0 && Protocol.anInt4422 == 0) {
                                                @Pc(1871) boolean success = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, MiniMenu.clickTileX, 0, 0, 0, MiniMenu.clickTileZ, PlayerList.self.movementQueueX[0]);
                                                if (success) {
                                                    Crosshair.y = Mouse.mouseClickY;
                                                    Crosshair.CrosshairCycle = 0;
                                                    Crosshair.x = Mouse.mouseClickX;
                                                    Crosshair.CrosshairMode = 1;
                                                }
                                            }
                                            MiniMenu.clickTileX = -1;
                                            Protocol.method843();
                                            if (ComponentList.aClass13_22 != component) {
                                                if (component != null) {
                                                    ComponentList.redraw(component);
                                                }
                                                if (ComponentList.aClass13_22 != null) {
                                                    ComponentList.redraw(ComponentList.aClass13_22);
                                                }
                                            }
                                            if (local1508 != Protocol.aClass13_11 && ClientScriptRunner.anInt4504 == Protocol.anInt5235) {
                                                if (local1508 != null) {
                                                    ComponentList.redraw(local1508);
                                                }
                                                if (Protocol.aClass13_11 != null) {
                                                    ComponentList.redraw(Protocol.aClass13_11);
                                                }
                                            }
                                            if (Protocol.aClass13_11 == null) {
                                                if (Protocol.anInt5235 > 0) {
                                                    Protocol.anInt5235--;
                                                }
                                            } else if (Protocol.anInt5235 < ClientScriptRunner.anInt4504) {
                                                Protocol.anInt5235++;
                                                if (ClientScriptRunner.anInt4504 == Protocol.anInt5235) {
                                                    ComponentList.redraw(Protocol.aClass13_11);
                                                }
                                            }
                                            if (Camera.cameraType == 1) {
                                                Camera.updatePlayerCamera();
                                            } else if (Camera.cameraType == 2) {
                                                Camera.updateLockedCamera();
                                            } else {
                                                Camera.updateLoginScreenCamera();
                                            }
                                            for (y = 0; y < 5; y++) {
                                                @Pc(2001) int local2001 = Protocol.cameraModifierCycle[y]++;
                                            }
                                            y = Mouse.getIdleLoops(); // runetek4.Mouse
                                            x = Keyboard.getIdleLoops(); // runetek4.Keyboard
                                            if (y > 15000 && x > 15000) {
                                                Protocol.idleTimeout = 250;
                                                Mouse.setIdleLoops(14500);
                                                Protocol.outboundBuffer.pIsaac1(245);
                                            }
                                            if (Protocol.openUrlRequest != null && Protocol.openUrlRequest.status == 1) {
                                                if (Protocol.openUrlRequest.result != null) {
                                                    ClientScriptRunner.openUrl(ClientScriptRunner.url, Protocol.newTab);
                                                }
                                                ClientScriptRunner.url = null;
                                                Protocol.openUrlRequest = null;
                                                Protocol.newTab = false;
                                            }
                                            Protocol.anInt3251++;
                                            MiniMap.minimapOffsetCycle++;
                                            Protocol.cameraOffsetCycle++;
                                            if (Protocol.cameraOffsetCycle > 500) {
                                                Protocol.cameraOffsetCycle = 0;
                                                rand = (int) (Math.random() * 8.0D);
                                                if ((rand & 0x4) == 4) {
                                                    Camera.cameraAnticheatAngle += Protocol.cameraOffsetYawModifier;
                                                }
                                                if ((rand & 0x2) == 2) {
                                                    Camera.cameraAnticheatOffsetZ += Protocol.cameraOffsetZModifier;
                                                }
                                                if ((rand & 0x1) == 1) {
                                                    Camera.cameraAnticheatOffsetX += Camera.cameraOffsetXModifier;
                                                }
                                            }
                                            if (MiniMap.minimapOffsetCycle > 500) {
                                                MiniMap.minimapOffsetCycle = 0;
                                                rand = (int) (Math.random() * 8.0D);
                                                if ((rand & 0x1) == 1) {
                                                    MiniMap.minimapAnticheatAngle += MiniMap.minimapAngleModifier;
                                                }
                                                if ((rand & 0x2) == 2) {
                                                    MiniMap.minimapZoom += MiniMap.minimapZoomModifier;
                                                }
                                            }
                                            if (Camera.cameraAnticheatOffsetX < -50) {
                                                Camera.cameraOffsetXModifier = 2;
                                            }
                                            if (MiniMap.minimapAnticheatAngle < -60) {
                                                MiniMap.minimapAngleModifier = 2;
                                            }
                                            if (MiniMap.minimapZoom < -20) {
                                                MiniMap.minimapZoomModifier = 1;
                                            }
                                            if (Camera.cameraAnticheatOffsetZ < -55) {
                                                Protocol.cameraOffsetZModifier = 2;
                                            }
                                            if (Camera.cameraAnticheatOffsetZ > 55) {
                                                Protocol.cameraOffsetZModifier = -2;
                                            }
                                            if (Camera.cameraAnticheatAngle < -40) {
                                                Protocol.cameraOffsetYawModifier = 1;
                                            }
                                            if (Camera.cameraAnticheatOffsetX > 50) {
                                                Camera.cameraOffsetXModifier = -2;
                                            }
                                            if (Camera.cameraAnticheatAngle > 40) {
                                                Protocol.cameraOffsetYawModifier = -1;
                                            }
                                            if (MiniMap.minimapZoom > 10) {
                                                MiniMap.minimapZoomModifier = -1;
                                            }
                                            if (MiniMap.minimapAnticheatAngle > 60) {
                                                MiniMap.minimapAngleModifier = -2;
                                            }
                                            if (Protocol.anInt3251 > 50) {
                                                Protocol.outboundBuffer.pIsaac1(93);
                                            }
                                            if (Protocol.verifyIdChanged) {
                                                Protocol.transmitVerifyId();
                                                Protocol.verifyIdChanged = false;
                                            }
                                            try {
                                                if (Protocol.gameServerSocket != null && Protocol.outboundBuffer.offset > 0) {
                                                    Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                                                    Protocol.anInt3251 = 0;
                                                    Protocol.outboundBuffer.offset = 0;
                                                }
                                            } catch (@Pc(2266) IOException local2266) {
                                                tryReconnect();
                                            }
                                            return;
                                        }
                                        // low priority actually
                                        highPrioritySource = highPriorityRequest.source;
                                        if (highPrioritySource.createdComponentId < 0) {
                                            break;
                                        }
                                        highPriorityComponent = ComponentList.getComponent(highPrioritySource.overlayer);
                                    } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPrioritySource.createdComponentId >= highPriorityComponent.createdComponents.length || highPrioritySource != highPriorityComponent.createdComponents[highPrioritySource.createdComponentId]);
                                    ClientScriptRunner.run(highPriorityRequest);
                                }
                            }
                            highPrioritySource = highPriorityRequest.source;
                            if (highPrioritySource.createdComponentId < 0) {
                                break;
                            }
                            highPriorityComponent = ComponentList.getComponent(highPrioritySource.overlayer);
                        } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPriorityComponent.createdComponents.length <= highPrioritySource.createdComponentId || highPriorityComponent.createdComponents[highPrioritySource.createdComponentId] != highPrioritySource);
                        ClientScriptRunner.run(highPriorityRequest);
                    }
                }
                highPrioritySource = highPriorityRequest.source;
                if (highPrioritySource.createdComponentId < 0) {
                    break;
                }
                highPriorityComponent = ComponentList.getComponent(highPrioritySource.overlayer);
            } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPrioritySource.createdComponentId >= highPriorityComponent.createdComponents.length || highPriorityComponent.createdComponents[highPrioritySource.createdComponentId] != highPrioritySource);
            ClientScriptRunner.run(highPriorityRequest);
        }
    }

    @OriginalMember(owner = "client!kd", name = "a", descriptor = "(Ljava/lang/String;B)V")
    public static void printHelp(@OriginalArg(0) String arg0) {
        System.out.println("Bad " + arg0 + ", Usage: worldid, <live/rc/wip>, <english/german>, <game0/game1>");
        System.exit(1);
    }

    @OriginalMember(owner = "client!wj", name = "b", descriptor = "(B)V")
    public static void processLogout() {
        if (Protocol.gameServerSocket != null) {
            Protocol.gameServerSocket.closeGracefully();
            Protocol.gameServerSocket = null;
        }
        Client.unload();
        SceneGraph.clear();
        @Pc(19) int i;
        for (i = 0; i < 4; i++) {
            PathFinder.collisionMaps[i].reset();
        }
        WorldMap.clear(false);
        System.gc();
        MidiPlayer.playFadeOut();
        MidiPlayer.jingle = false;
        MusicPlayer.groupId = -1;
        AreaSoundManager.clear(true);
        SceneGraph.dynamicMapRegion = false;
        Camera.originZ = 0;
        SceneGraph.centralZoneX = 0;
        SceneGraph.centralZoneZ = 0;
        Camera.originX = 0;
        for (i = 0; i < MiniMap.hintMapMarkers.length; i++) {
            MiniMap.hintMapMarkers[i] = null;
        }
        PlayerList.playerCount = 0;
        NpcList.npcCount = 0;
        for (i = 0; i < 2048; i++) {
            PlayerList.players[i] = null;
            PlayerList.appearanceCache[i] = null;
        }
        for (i = 0; i < 32768; i++) {
            NpcList.npcs[i] = null;
        }
        for (i = 0; i < 4; i++) {
            for (@Pc(115) int local115 = 0; local115 < 104; local115++) {
                for (@Pc(122) int local122 = 0; local122 < 104; local122++) {
                    SceneGraph.objStacks[i][local115][local122] = null;
                }
            }
        }
        Camera.resetCameraEffects();
        Protocol.verifyId = 0;
        VarpDomain.resetVarBits();
        ComponentList.initializeLoginScreen(true);
    }

    @OriginalMember(owner = "client!nm", name = "a", descriptor = "(Z)V")
    public static void tryReconnect() {
        if (Protocol.idleTimeout > 0) {
            processLogout();
        } else {
            Protocol.aClass95_4 = Protocol.gameServerSocket;
            Protocol.gameServerSocket = null;
            Client.processGameStatus(40);
        }
    }

}
