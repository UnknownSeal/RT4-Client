package com.jagex.runetek4.client;

import com.jagex.runetek4.audio.core.SoundPlayer;
import com.jagex.runetek4.audio.midi.MidiPlayer;
import com.jagex.runetek4.audio.spatial.AreaSoundManager;
import com.jagex.runetek4.audio.streaming.MusicPlayer;
import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.scene.Camera;
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
import com.jagex.runetek4.clientscript.ClientServerStateSync;
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

import static com.jagex.runetek4.network.ClientProt.*;

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
        if (LoginManager.shouldReconnect) {
            LoginManager.shouldReconnect = false;
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
        @Pc(60) Object mouseRecorderLock = MouseCapturer.instance.lock;
        @Pc(86) int packetStartOffset;
        @Pc(79) int mouseSampleCount;
        @Pc(88) int i;
        @Pc(106) int y;
        @Pc(111) int x;
        @Pc(182) int dx;
        @Pc(189) int dy;
        synchronized (mouseRecorderLock) {
            if (!MouseCapturer.enabled) {
                MouseCapturer.instance.samples = 0;
            } else if (Mouse.clickButton != 0 || MouseCapturer.instance.samples >= 40) {
                // Encode and send mouse movement data to server
                Protocol.outboundBuffer.pIsaac1(EVENT_MOUSE_MOVE);
                mouseSampleCount = 0;
                Protocol.outboundBuffer.p1(0);
                packetStartOffset = Protocol.outboundBuffer.offset;
                for (i = 0; MouseCapturer.instance.samples > i && Protocol.outboundBuffer.offset - packetStartOffset < 240; i++) {
                    mouseSampleCount++;
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

                    // Check if mouse left the game window
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
                        if (Protocol.mouseIdleFrameCount < 8 && dx >= -32 && dx <= 31 && dy >= -32 && dy <= 31) {
                            dy += 32;
                            dx += 32;
                            Protocol.outboundBuffer.p2(dy + (Protocol.mouseIdleFrameCount << 12) + (dx << 6));
                            Protocol.mouseIdleFrameCount = 0;
                        } else if (Protocol.mouseIdleFrameCount < 32 && dx >= -128 && dx <= 127 && dy >= -128 && dy <= 127) {
                            Protocol.outboundBuffer.p1(Protocol.mouseIdleFrameCount + 128);
                            dy += 128;
                            dx += 128;
                            Protocol.outboundBuffer.p2((dx << 8) + dy);
                            Protocol.mouseIdleFrameCount = 0;
                        } else if (Protocol.mouseIdleFrameCount < 32) {
                            Protocol.outboundBuffer.p1(Protocol.mouseIdleFrameCount + 192);
                            if (outsideWindow) {
                                Protocol.outboundBuffer.p4(Integer.MIN_VALUE);
                            } else {
                                Protocol.outboundBuffer.p4(x | y << 16);
                            }
                            Protocol.mouseIdleFrameCount = 0;
                        } else {
                            Protocol.outboundBuffer.p2(Protocol.mouseIdleFrameCount + 57344);
                            if (outsideWindow) {
                                Protocol.outboundBuffer.p4(Integer.MIN_VALUE);
                            } else {
                                Protocol.outboundBuffer.p4(x | y << 16);
                            }
                            Protocol.mouseIdleFrameCount = 0;
                        }
                    } else if (Protocol.mouseIdleFrameCount < 2047) {
                        // Position unchanged, increment idle frame counter
                        Protocol.mouseIdleFrameCount++;
                    }
                }
                Protocol.outboundBuffer.p1len(Protocol.outboundBuffer.offset - packetStartOffset);
                if (MouseCapturer.instance.samples > mouseSampleCount) {
                    MouseCapturer.instance.samples -= mouseSampleCount;
                    for (i = 0; i < MouseCapturer.instance.samples; i++) {
                        MouseCapturer.instance.x[i] = MouseCapturer.instance.x[mouseSampleCount + i];
                        MouseCapturer.instance.y[i] = MouseCapturer.instance.y[mouseSampleCount + i];
                    }
                } else {
                    MouseCapturer.instance.samples = 0;
                }
            }
        }
        if (Mouse.clickButton != 0) {
            @Pc(411) long timeSinceLastClick = (Mouse.clickTime - Mouse.prevClickTime) / 50L;
            mouseSampleCount = Mouse.mouseClickY;
            if (mouseSampleCount < 0) {
                mouseSampleCount = 0;
            } else if (mouseSampleCount > 65535) {
                mouseSampleCount = 65535;
            }
            if (timeSinceLastClick > 32767L) {
                timeSinceLastClick = 32767L;
            }
            i = Mouse.mouseClickX;
            Mouse.prevClickTime = Mouse.clickTime;
            @Pc(437) byte button = 0;
            if (i < 0) {
                i = 0;
            } else if (i > 65535) {
                i = 65535;
            }
            x = (int) timeSinceLastClick;
            if (Mouse.clickButton == 2) {
                button = 1;
            }
            Protocol.outboundBuffer.pIsaac1(EVENT_MOUSE_CLICK);
            Protocol.outboundBuffer.p2_alt3(button << 15 | x);
            Protocol.outboundBuffer.p4_alt3(i | mouseSampleCount << 16);
        }
        if (Protocol.cameraPositionUpdateCooldown > 0) {
            Protocol.cameraPositionUpdateCooldown--;
        }
        if (Preferences.keyboardCameraEnabled) {
            for (i = 0; i < ComponentList.keyQueueSize; i++) {
                packetStartOffset = ComponentList.keyCodes[i];
                if (packetStartOffset == 98 || packetStartOffset == 99 || packetStartOffset == 96 || packetStartOffset == 97) {
                    Protocol.shouldSendCameraPosition = true;
                    break;
                }
            }
        } else if (Keyboard.pressedKeys[96] || Keyboard.pressedKeys[97] || Keyboard.pressedKeys[98] || Keyboard.pressedKeys[99]) {
            Protocol.shouldSendCameraPosition = true;
        }
        if (Protocol.shouldSendCameraPosition && Protocol.cameraPositionUpdateCooldown <= 0) {
            Protocol.cameraPositionUpdateCooldown = 20;
            Protocol.shouldSendCameraPosition = false;
            Protocol.outboundBuffer.pIsaac1(EVENT_CAMERA_POSITION);
            Protocol.outboundBuffer.p2_alt2(Camera.orbitCameraPitch);
            Protocol.outboundBuffer.p2_alt1(Camera.orbitCameraYaw);
        }
        if (GameShell.focus && !Protocol.prevFocus) {
            Protocol.prevFocus = true;
            Protocol.outboundBuffer.pIsaac1(EVENT_APPLET_FOCUS);
            Protocol.outboundBuffer.p1(1);
        }
        if (!GameShell.focus && Protocol.prevFocus) {
            Protocol.prevFocus = false;
            Protocol.outboundBuffer.pIsaac1(EVENT_APPLET_FOCUS);
            Protocol.outboundBuffer.p1(0);
        }
        if (!Preferences.sentToServer) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IDLE_LOGOUT);
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
            WorldMap.update();
        }
        // VarpDomain

        // Process VarP updates from server
        for (i = VarpDomain.poll(true); i != -1; i = VarpDomain.poll(false)) {
            VarpDomain.refreshMagicVarp(i);
            VarpDomain.updatedVarps[VarpDomain.updatedVarpsWriterIndex++ & 0x1F] = i;
        }
        @Pc(782) int anticheatRandom;

        // Process client server sync queue
        for (@Pc(709) ClientServerStateSync change = ClientServerStateSync.poll(); change != null; change = ClientServerStateSync.poll()) {
            mouseSampleCount = change.getType();
            i = change.getId();

            // Handle different change types
            if (mouseSampleCount == 1) {
                // VarC update
                VarcDomain.varcs[i] = change.intArg1;
                VarcDomain.updatedVarcs[VarcDomain.updatedVarcsWriterIndex++ & 0x1F] = i;
            } else if (mouseSampleCount == 2) {
                // VarC string update
                VarcDomain.varcstrs[i] = change.stringArg;
                VarcDomain.updatedVarcstrs[VarcDomain.updatedVarcstrsWriterIndex++ & 0x1F] = i;
            } else {
                @Pc(773) Component component;
                if (mouseSampleCount == 3) {
                    // Component text update
                    component = ComponentList.getComponent(i);
                    if (!change.stringArg.strEquals(component.text)) {
                        component.text = change.stringArg;
                        ComponentList.redraw(component);
                    }
                } else if (mouseSampleCount == 4) {
                    component = ComponentList.getComponent(i);
                    x = change.intArg1;
                    dx = change.intArg2;
                    anticheatRandom = change.intArg3;
                    if (component.modelType != x || component.modelId != anticheatRandom || dx != component.zoom) {
                        component.modelId = anticheatRandom;
                        component.zoom = dx;
                        component.modelType = x;
                        ComponentList.redraw(component);
                    }
                } else if (mouseSampleCount == 5) {
                    component = ComponentList.getComponent(i);
                    if (component.modelSeqId != change.intArg1 || change.intArg1 == -1) {
                        component.animationFrame = 1;
                        component.animationDelay = 0;
                        component.modelSeqId = change.intArg1;
                        component.animationId = 0;
                        ComponentList.redraw(component);
                    }
                } else if (mouseSampleCount == 6) {
                    y = change.intArg1;
                    x = y >> 10 & 0x1F;
                    dx = y & 0x1F;
                    anticheatRandom = y >> 5 & 0x1F;
                    @Pc(1189) Component local1189 = ComponentList.getComponent(i);
                    dy = (dx << 3) + (anticheatRandom << 11) + (x << 19);
                    if (dy != local1189.color) {
                        local1189.color = dy;
                        ComponentList.redraw(local1189);
                    }
                } else if (mouseSampleCount == 7) {
                    component = ComponentList.getComponent(i);
                    // todo: this should not be necessary, data/server-related?
                    if (component != null) {
                        @Pc(1145) boolean hidden = change.intArg1 == 1;
                        if (hidden != component.hidden) {
                            component.hidden = hidden;
                            ComponentList.redraw(component);
                        }
                    }
                } else if (mouseSampleCount == 8) {
                    component = ComponentList.getComponent(i);
                    if (change.intArg1 != component.modelXAngle || component.modelYAngle != change.intArg3 || change.intArg2 != component.modelZoom) {
                        component.modelXAngle = change.intArg1;
                        component.modelZoom = change.intArg2;
                        component.modelYAngle = change.intArg3;
                        if (component.objId != -1) {
                            if (component.maxModelSize > 0) {
                                component.modelZoom = component.modelZoom * 32 / component.maxModelSize;
                            } else if (component.baseWidth > 0) {
                                component.modelZoom = component.modelZoom * 32 / component.baseWidth;
                            }
                        }
                        ComponentList.redraw(component);
                    }
                } else if (mouseSampleCount == 9) {
                    component = ComponentList.getComponent(i);
                    if (change.intArg1 != component.objId || component.objCount != change.intArg3) {
                        component.objId = change.intArg1;
                        component.objCount = change.intArg3;
                        ComponentList.redraw(component);
                    }
                } else if (mouseSampleCount == 10) {
                    component = ComponentList.getComponent(i);
                    if (component.modelXOffset != change.intArg1 || change.intArg3 != component.modelZOffset || component.modelYOffset != change.intArg2) {
                        component.modelZOffset = change.intArg3;
                        component.modelYOffset = change.intArg2;
                        component.modelXOffset = change.intArg1;
                        ComponentList.redraw(component);
                    }
                } else if (mouseSampleCount == 11) {
                    component = ComponentList.getComponent(i);
                    component.x = component.baseX = change.intArg1;
                    component.yMode = 0;
                    component.xMode = 0;
                    component.y = component.baseY = change.intArg3;
                    ComponentList.redraw(component);
                } else if (mouseSampleCount == 12) {
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
                } else if (mouseSampleCount == 13) {
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
            MiniMenu.inventoryPressTimer++;
            if (MiniMenu.inventoryPressTimer >= 15) {
                ComponentList.redraw(MiniMenu.pressedInventoryComponent);
                MiniMenu.pressedInventoryComponent = null;
            }
        }
        @Pc(1361) Component component;
        if (ComponentList.clickedInventoryComponent != null) {
            ComponentList.redraw(ComponentList.clickedInventoryComponent);

            // Check if mouse moved far enough from when clicked to trigger dragging
            if (ComponentList.clickedInventoryComponentX + 5 < Mouse.lastMouseX || Mouse.lastMouseX < ComponentList.clickedInventoryComponentX - 5 || ComponentList.clickedInventoryComponentY + 5 < Mouse.lastMouseY || ComponentList.clickedInventoryComponentY - 5 > Mouse.lastMouseY) {
                ComponentList.draggingClickedInventoryObject = true;
            }
            ComponentList.lastItemDragTime++;
            if (Mouse.pressedButton == 0) { // Mouse released
                if (ComponentList.draggingClickedInventoryObject && ComponentList.lastItemDragTime >= 5) {
                    // Dragged item to different slot
                    if (ComponentList.clickedInventoryComponent == ComponentList.mouseOverInventoryComponent && ComponentList.selectedInventorySlot != MiniMenu.mouseInvInterfaceIndex) {
                        component = ComponentList.clickedInventoryComponent;
                        @Pc(1363) byte insertMode = 0;

                        // Check if bank insert mode is enabled
                        if (VarpDomain.bankInsertMode == 1 && component.contentType == 206) {
                            insertMode = 1;
                        }

                        // Cant insert into empty slot
                        if (component.invSlotObjId[MiniMenu.mouseInvInterfaceIndex] <= 0) {
                            insertMode = 0;
                        }

                        if (ComponentList.getServerActiveProperties(component).isObjReplaceEnabled()) {
                            // Mode 0: swap items
                            y = ComponentList.selectedInventorySlot;
                            x = MiniMenu.mouseInvInterfaceIndex;
                            component.invSlotObjId[x] = component.invSlotObjId[y];
                            component.invSlotObjCount[x] = component.invSlotObjCount[y];
                            component.invSlotObjId[y] = -1;
                            component.invSlotObjCount[y] = 0;
                        } else if (insertMode == 1) {
                            // Mode 1: Insert (Shift items between source and destination)
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
                            // Simple swap
                            component.swapObjs(MiniMenu.mouseInvInterfaceIndex, ComponentList.selectedInventorySlot);
                        }

                        // Send Move item packet to server
                        Protocol.outboundBuffer.pIsaac1(ClientProt.MOVE_ITEM);
                        Protocol.outboundBuffer.p2(ComponentList.selectedInventorySlot);
                        Protocol.outboundBuffer.p4_alt1(ComponentList.clickedInventoryComponent.id);
                        Protocol.outboundBuffer.p2_alt2(MiniMenu.mouseInvInterfaceIndex);
                        Protocol.outboundBuffer.p1b_alt3(insertMode);
                    }
                } else if ((VarpDomain.oneMouseButton == 1 || MiniMenu.isComponentAction(MiniMenu.menuActionRow - 1)) && MiniMenu.menuActionRow > 2) {
                    // Show right click menu
                    ClientScriptRunner.determineMenuSize();
                } else if (MiniMenu.menuActionRow > 0) {
                    // Process single action
                    MiniMenu.processMenuActions();
                }
                Mouse.clickButton = 0;
                MiniMenu.inventoryPressTimer = 10;
                ComponentList.clickedInventoryComponent = null;
            }
        }
        ComponentList.dragActive = false;
        ComponentList.targetComponent = null;
        ComponentList.canDrag = false;
        ComponentList.keyQueueSize = 0;
        component = ComponentList.previousMouseOverComponent;
        ComponentList.previousMouseOverComponent = null;
        @Pc(1508) Component local1508 = Protocol.dragHoverComponent;
        Protocol.dragHoverComponent = null;
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
                                                ComponentList.worldMapState = 0;
                                            }
                                            if (ClientScriptRunner.dragComponent != null) {
                                                ClientScriptRunner.handleComponentDrag();
                                            }
                                            if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81] && MouseWheel.wheelRotation != 0) {
                                                y = Player.currentLevel - MouseWheel.wheelRotation;
                                                if (y < 0) {
                                                    y = 0;
                                                } else if (y > 3) {
                                                    y = 3;
                                                }
                                                // Cheat
                                                Cheat.teleport(PlayerList.self.movementQueueX[0] + Camera.sceneBaseTileX, PlayerList.self.movementQueueZ[0] + Camera.sceneBaseTileZ, y);
                                            }
                                            if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                                                if (MiniMenu.clickTileX != -1) {
                                                    Cheat.teleport(Camera.sceneBaseTileX + MiniMenu.clickTileX, Camera.sceneBaseTileZ + MiniMenu.clickTileZ, Player.currentLevel);
                                                }
                                                Protocol.walkRequestState = 0;
                                                MiniMenu.useItemOnTileMode = 0;
                                            } else if (MiniMenu.useItemOnTileMode == 2) {
                                                if (MiniMenu.clickTileX != -1) {
                                                    Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJT);
                                                    Protocol.outboundBuffer.p4_alt3(MiniMenu.useWithComponentId);
                                                    Protocol.outboundBuffer.p2_alt2(Camera.sceneBaseTileX + MiniMenu.clickTileX);
                                                    Protocol.outboundBuffer.p2_alt3(MiniMenu.useWithSlot);
                                                    Protocol.outboundBuffer.p2_alt2(MiniMenu.clickTileZ + Camera.sceneBaseTileZ);
                                                    Crosshair.CrosshairMode = 1;
                                                    Crosshair.CrosshairCycle = 0;
                                                    Crosshair.y = Mouse.mouseClickY;
                                                    Crosshair.x = Mouse.mouseClickX;
                                                }
                                                MiniMenu.useItemOnTileMode = 0;
                                            } else if (Protocol.walkRequestState == 2) {
                                                if (MiniMenu.clickTileX != -1) {
                                                    Protocol.outboundBuffer.pIsaac1(ClientProt.MOVE_CLICK_ALT);
                                                    Protocol.outboundBuffer.p2(Camera.sceneBaseTileZ + MiniMenu.clickTileZ);
                                                    Protocol.outboundBuffer.p2(MiniMenu.clickTileX + Camera.sceneBaseTileX);
                                                    Crosshair.CrosshairCycle = 0;
                                                    Crosshair.CrosshairMode = 1;
                                                    Crosshair.x = Mouse.mouseClickX;
                                                    Crosshair.y = Mouse.mouseClickY;
                                                }
                                                Protocol.walkRequestState = 0;
                                            } else if (MiniMenu.clickTileX != -1 && MiniMenu.useItemOnTileMode == 0 && Protocol.walkRequestState == 0) {
                                                @Pc(1871) boolean success = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, MiniMenu.clickTileX, 0, 0, 0, MiniMenu.clickTileZ, PlayerList.self.movementQueueX[0]);
                                                if (success) {
                                                    Crosshair.y = Mouse.mouseClickY;
                                                    Crosshair.CrosshairCycle = 0;
                                                    Crosshair.x = Mouse.mouseClickX;
                                                    Crosshair.CrosshairMode = 1;
                                                }
                                            }
                                            MiniMenu.clickTileX = -1;
                                            Protocol.handleMenuClick();
                                            if (ComponentList.previousMouseOverComponent != component) {
                                                if (component != null) {
                                                    ComponentList.redraw(component);
                                                }
                                                if (ComponentList.previousMouseOverComponent != null) {
                                                    ComponentList.redraw(ComponentList.previousMouseOverComponent);
                                                }
                                            }
                                            if (local1508 != Protocol.dragHoverComponent && ClientScriptRunner.MAX_CALL_STACK_DEPTH == Protocol.dragHoverAnimationProgress) {
                                                if (local1508 != null) {
                                                    ComponentList.redraw(local1508);
                                                }
                                                if (Protocol.dragHoverComponent != null) {
                                                    ComponentList.redraw(Protocol.dragHoverComponent);
                                                }
                                            }
                                            if (Protocol.dragHoverComponent == null) {
                                                if (Protocol.dragHoverAnimationProgress > 0) {
                                                    Protocol.dragHoverAnimationProgress--;
                                                }
                                            } else if (Protocol.dragHoverAnimationProgress < ClientScriptRunner.MAX_CALL_STACK_DEPTH) {
                                                Protocol.dragHoverAnimationProgress++;
                                                if (ClientScriptRunner.MAX_CALL_STACK_DEPTH == Protocol.dragHoverAnimationProgress) {
                                                    ComponentList.redraw(Protocol.dragHoverComponent);
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
                                                Protocol.outboundBuffer.pIsaac1(ClientProt.EVENT_IDLE);
                                            }
                                            if (Protocol.openUrlRequest != null && Protocol.openUrlRequest.status == 1) {
                                                if (Protocol.openUrlRequest.result != null) {
                                                    ClientScriptRunner.openUrl(ClientScriptRunner.url, Protocol.newTab);
                                                }
                                                ClientScriptRunner.url = null;
                                                Protocol.openUrlRequest = null;
                                                Protocol.newTab = false;
                                            }
                                            Protocol.noTimeoutCycle++;
                                            MiniMap.minimapOffsetCycle++;
                                            Protocol.cameraOffsetCycle++;

                                            // Anticheat
                                            // Every 500 ticks, apply random jitter to camera position
                                            if (Protocol.cameraOffsetCycle > 500) {
                                                Protocol.cameraOffsetCycle = 0;
                                                anticheatRandom = (int) (Math.random() * 8.0D);
                                                if ((anticheatRandom & 0x4) == 4) {
                                                    Camera.cameraAnticheatAngle += Protocol.cameraOffsetYawModifier;
                                                }
                                                if ((anticheatRandom & 0x2) == 2) {
                                                    Camera.cameraAnticheatOffsetZ += Protocol.cameraOffsetZModifier;
                                                }
                                                if ((anticheatRandom & 0x1) == 1) {
                                                    Camera.cameraAnticheatOffsetX += Camera.cameraOffsetXModifier;
                                                }
                                            }

                                            // Anticheat
                                            // Every 500 ticks, apply random jitter to minimap
                                            if (MiniMap.minimapOffsetCycle > 500) {
                                                MiniMap.minimapOffsetCycle = 0;
                                                anticheatRandom = (int) (Math.random() * 8.0D);
                                                if ((anticheatRandom & 0x1) == 1) {
                                                    MiniMap.minimapAnticheatAngle += MiniMap.minimapAngleModifier;
                                                }
                                                if ((anticheatRandom & 0x2) == 2) {
                                                    MiniMap.minimapZoom += MiniMap.minimapZoomModifier;
                                                }
                                            }

                                            // Revser direction when bounds are reached
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

                                            if (Protocol.noTimeoutCycle > 50) {
                                                Protocol.outboundBuffer.pIsaac1(NO_TIMEOUT);
                                            }
                                            if (Protocol.verifyIdChanged) {
                                                Protocol.transmitVerifyId();
                                                Protocol.verifyIdChanged = false;
                                            }

                                            try {
                                                if (Protocol.gameServerSocket != null && Protocol.outboundBuffer.offset > 0) {
                                                    Protocol.gameServerSocket.write(Protocol.outboundBuffer.offset, Protocol.outboundBuffer.data);
                                                    Protocol.noTimeoutCycle = 0;
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
        Camera.sceneBaseTileZ = 0;
        SceneGraph.centralZoneX = 0;
        SceneGraph.centralZoneZ = 0;
        Camera.sceneBaseTileX = 0;
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
            Protocol.previousSocket = Protocol.gameServerSocket;
            Protocol.gameServerSocket = null;
            Client.processGameStatus(40);
        }
    }

}
