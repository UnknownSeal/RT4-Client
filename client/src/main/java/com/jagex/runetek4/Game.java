package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.input.MouseCapturer;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.dash3d.entity.ProjectileEntity;
import com.jagex.runetek4.dash3d.entity.SpotAnimEntity;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;

public class Game {
    @OriginalMember(owner = "client!vl", name = "k", descriptor = "I")
    public static int idleTimeout = 0;

    @OriginalMember(owner = "client!gg", name = "a", descriptor = "(Z)V")
    public static void updateGame() {
        // todo: consolidate/rename static classes
        if (idleTimeout > 0) {
            idleTimeout--;
        }
        if (Static60.systemUpdateTimer > 1) {
            Static60.systemUpdateTimer--;
            InterfaceList.miscTransmitAt = InterfaceList.transmitTimer;
        }
        if (LoginManager.aBoolean247) {
            LoginManager.aBoolean247 = false;
            tryReconnect();
            return;
        }
        for (@Pc(34) int i = 0; i < 100 && Protocol.readPacket(); i++) {
        }
        if (client.gameState != 30) {
            return;
        }
        ClientScriptRunner.createClientScriptCheckPacket(Protocol.outboundBuffer); // runetek4.ReflectionCheck
        @Pc(60) Object mouseRecorder = MouseCapturer.instance.lock;
        @Pc(86) int offset;
        @Pc(79) int samples;
        @Pc(88) int i;
        @Pc(106) int y;
        @Pc(111) int x;
        @Pc(182) int dx;
        @Pc(189) int dy;
        synchronized (mouseRecorder) {
            if (!Static245.enabled) {
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
                    if (Static264.mouseRecorderPrevX != x || Static179.mouseRecorderPrevY != y) {
                        dx = x - Static264.mouseRecorderPrevX;
                        Static264.mouseRecorderPrevX = x;
                        dy = y - Static179.mouseRecorderPrevY;
                        Static179.mouseRecorderPrevY = y;
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
            @Pc(411) long loops = (Static133.clickTime - Mouse.prevClickTime) / 50L;
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
            Mouse.prevClickTime = Static133.clickTime;
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
        if (Static16.anInt551 > 0) {
            Static16.anInt551--;
        }
        if (Preferences.aBoolean63) {
            for (i = 0; i < InterfaceList.keyQueueSize; i++) {
                offset = InterfaceList.keyCodes[i];
                if (offset == 98 || offset == 99 || offset == 96 || offset == 97) {
                    Protocol.aBoolean228 = true;
                    break;
                }
            }
        } else if (Keyboard.pressedKeys[96] || Keyboard.pressedKeys[97] || Keyboard.pressedKeys[98] || Keyboard.pressedKeys[99]) {
            Protocol.aBoolean228 = true;
        }
        if (Protocol.aBoolean228 && Static16.anInt551 <= 0) {
            Static16.anInt551 = 20;
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
            Protocol.outboundBuffer.p4(Static145.method2746());
            Preferences.sentToServer = true;
        }
        Static31.method846();
        if (client.gameState != 30) {
            return;
        }
        ChangeLocRequest.loop();
        AttachLocRequest.loop();
        Static54.loop(); // SoundPlayer
        LoginManager.idleNetCycles++;
        if (LoginManager.idleNetCycles > 750) {
            tryReconnect();
            return;
        }
        PlayerList.updatePlayers();
        Static109.updateNpcs();
        Static19.tickChatTimers(); // OverheadChat
        if (WorldMap.component != null) {
            Static12.method447();
        }
        // VarpDomain
        for (i = Static38.poll(true); i != -1; i = Static38.poll(false)) {
            Static85.handleVarps(i);
            Static83.updatedVarps[Static70.updatedVarpsWriterIndex++ & 0x1F] = i;
        }
        @Pc(782) int rand;

        for (@Pc(709) DelayedStateChange change = DelayedStateChange.poll(); change != null; change = DelayedStateChange.poll()) {
            samples = change.getType();
            i = change.getId();
            if (samples == 1) {
                VarcDomain.varcs[i] = change.intArg1;
                VarcDomain.updatedVarcs[VarcDomain.updatedVarcsWriterIndex++ & 0x1F] = i;
            } else if (samples == 2) {
                Static226.varcstrs[i] = change.stringArg;
                ClientScriptRunner.updatedVarcstrs[Static72.updatedVarcstrsWriterIndex++ & 0x1F] = i;
            } else {
                @Pc(773) Component component;
                if (samples == 3) {
                    component = InterfaceList.getComponent(i);
                    if (!change.stringArg.strEquals(component.text)) {
                        component.text = change.stringArg;
                        InterfaceList.redraw(component);
                    }
                } else if (samples == 4) {
                    component = InterfaceList.getComponent(i);
                    x = change.intArg1;
                    dx = change.intArg2;
                    rand = change.intArg3;
                    if (component.modelType != x || component.modelId != rand || dx != component.anInt498) {
                        component.modelId = rand;
                        component.anInt498 = dx;
                        component.modelType = x;
                        InterfaceList.redraw(component);
                    }
                } else if (samples == 5) {
                    component = InterfaceList.getComponent(i);
                    if (component.modelSeqId != change.intArg1 || change.intArg1 == -1) {
                        component.anInt496 = 1;
                        component.anInt500 = 0;
                        component.modelSeqId = change.intArg1;
                        component.anInt510 = 0;
                        InterfaceList.redraw(component);
                    }
                } else if (samples == 6) {
                    y = change.intArg1;
                    x = y >> 10 & 0x1F;
                    dx = y & 0x1F;
                    rand = y >> 5 & 0x1F;
                    @Pc(1189) Component local1189 = InterfaceList.getComponent(i);
                    dy = (dx << 3) + (rand << 11) + (x << 19);
                    if (dy != local1189.color) {
                        local1189.color = dy;
                        InterfaceList.redraw(local1189);
                    }
                } else if (samples == 7) {
                    component = InterfaceList.getComponent(i);
                    // todo: this should not be necessary, data/server-related?
                    if (component != null) {
                        @Pc(1145) boolean hidden = change.intArg1 == 1;
                        if (hidden != component.hidden) {
                            component.hidden = hidden;
                            InterfaceList.redraw(component);
                        }
                    }
                } else if (samples == 8) {
                    component = InterfaceList.getComponent(i);
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
                        InterfaceList.redraw(component);
                    }
                } else if (samples == 9) {
                    component = InterfaceList.getComponent(i);
                    if (change.intArg1 != component.objId || component.objCount != change.intArg3) {
                        component.objId = change.intArg1;
                        component.objCount = change.intArg3;
                        InterfaceList.redraw(component);
                    }
                } else if (samples == 10) {
                    component = InterfaceList.getComponent(i);
                    if (component.modelXOffset != change.intArg1 || change.intArg3 != component.modelZOffset || component.modelYOffset != change.intArg2) {
                        component.modelZOffset = change.intArg3;
                        component.modelYOffset = change.intArg2;
                        component.modelXOffset = change.intArg1;
                        InterfaceList.redraw(component);
                    }
                } else if (samples == 11) {
                    component = InterfaceList.getComponent(i);
                    component.x = component.baseX = change.intArg1;
                    component.yMode = 0;
                    component.xMode = 0;
                    component.y = component.baseY = change.intArg3;
                    InterfaceList.redraw(component);
                } else if (samples == 12) {
                    component = InterfaceList.getComponent(i);
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
                            InterfaceList.redraw(component);
                        }
                    }
                } else if (samples == 13) {
                    component = InterfaceList.getComponent(i);
                    component.modelRotationSpeed = change.intArg1;
                }
            }
        }
        // Cross
        if (Cross.crossMode != 0) {
            Cross.crossCycle += 20;
            if (Cross.crossCycle >= 400) {
                Cross.crossMode = 0;
            }
        }
        Protocol.sceneDelta++;
        if (MiniMenu.pressedInventoryComponent != null) {
            Static72.anInt2043++;
            if (Static72.anInt2043 >= 15) {
                InterfaceList.redraw(MiniMenu.pressedInventoryComponent);
                MiniMenu.pressedInventoryComponent = null;
            }
        }
        @Pc(1361) Component component;
        if (InterfaceList.clickedInventoryComponent != null) {
            InterfaceList.redraw(InterfaceList.clickedInventoryComponent);
            if (InterfaceList.clickedInventoryComponentX + 5 < Mouse.lastMouseX || Mouse.lastMouseX < InterfaceList.clickedInventoryComponentX - 5 || InterfaceList.clickedInventoryComponentY + 5 < Mouse.lastMouseY || InterfaceList.clickedInventoryComponentY - 5 > Mouse.lastMouseY) {
                InterfaceList.draggingClickedInventoryObject = true;
            }
            InterfaceList.lastItemDragTime++;
            if (Mouse.pressedButton == 0) {
                if (InterfaceList.draggingClickedInventoryObject && InterfaceList.lastItemDragTime >= 5) {
                    if (InterfaceList.clickedInventoryComponent == InterfaceList.mouseOverInventoryInterface && InterfaceList.selectedInventorySlot != Static18.mouseInvInterfaceIndex) {
                        component = InterfaceList.clickedInventoryComponent;
                        @Pc(1363) byte moveItemInsertionMode = 0;
                        if (Static179.bankInsertMode == 1 && component.contentType == 206) {
                            moveItemInsertionMode = 1;
                        }
                        if (component.invSlotObjId[Static18.mouseInvInterfaceIndex] <= 0) {
                            moveItemInsertionMode = 0;
                        }
                        if (InterfaceList.getServerActiveProperties(component).method504()) {
                            y = InterfaceList.selectedInventorySlot;
                            x = Static18.mouseInvInterfaceIndex;
                            component.invSlotObjId[x] = component.invSlotObjId[y];
                            component.invSlotObjCount[x] = component.invSlotObjCount[y];
                            component.invSlotObjId[y] = -1;
                            component.invSlotObjCount[y] = 0;
                        } else if (moveItemInsertionMode == 1) {
                            x = Static18.mouseInvInterfaceIndex;
                            y = InterfaceList.selectedInventorySlot;
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
                            component.swapObjs(Static18.mouseInvInterfaceIndex, InterfaceList.selectedInventorySlot);
                        }
                        Protocol.outboundBuffer.pIsaac1(231);
                        Protocol.outboundBuffer.p2(InterfaceList.selectedInventorySlot);
                        Protocol.outboundBuffer.p4_alt1(InterfaceList.clickedInventoryComponent.id);
                        Protocol.outboundBuffer.p2_alt2(Static18.mouseInvInterfaceIndex);
                        Protocol.outboundBuffer.p1_alt3(moveItemInsertionMode);
                    }
                } else if ((Static116.oneMouseButton == 1 || Static277.menuHasAddFriend(MiniMenu.menuActionRow - 1)) && MiniMenu.menuActionRow > 2) {
                    Static226.determineMenuSize();
                } else if (MiniMenu.menuActionRow > 0) {
                    Static59.processMenuActions();
                }
                Mouse.clickButton = 0;
                Static72.anInt2043 = 10;
                InterfaceList.clickedInventoryComponent = null;
            }
        }
        InterfaceList.aBoolean174 = false;
        InterfaceList.aClass13_12 = null;
        InterfaceList.aBoolean83 = false;
        InterfaceList.keyQueueSize = 0;
        component = InterfaceList.aClass13_22;
        InterfaceList.aClass13_22 = null;
        @Pc(1508) Component local1508 = Protocol.aClass13_11;
        Protocol.aClass13_11 = null;
        while (Keyboard.nextKey() && InterfaceList.keyQueueSize < 128) {
            InterfaceList.keyCodes[InterfaceList.keyQueueSize] = Keyboard.keyCode;
            InterfaceList.keyChars[InterfaceList.keyQueueSize] = Static193.keyChar;
            InterfaceList.keyQueueSize++;
        }
        // WorldMap.component
        WorldMap.component = null;
        if (InterfaceList.topLevelInterface != -1) {
            InterfaceList.method1320(0, 0, 0, GameShell.canvasWidth, InterfaceList.topLevelInterface, 0, GameShell.canvasHeigth);
        }
        InterfaceList.transmitTimer++;
        while (true) {
            // todo: this is actually split up into low/medium/high
            @Pc(1569) Component highPriorityComponent;
            @Pc(1560) Component highPrioritySource;
            @Pc(1555) HookRequest highPriorityRequest;
            do {
                highPriorityRequest = (HookRequest) InterfaceList.highPriorityRequests.removeHead();
                if (highPriorityRequest == null) {
                    while (true) {
                        do {
                            highPriorityRequest = (HookRequest) InterfaceList.mediumPriorityRequests.removeHead();
                            if (highPriorityRequest == null) {
                                while (true) {
                                    do {
                                        highPriorityRequest = (HookRequest) InterfaceList.lowPriorityRequests.removeHead();
                                        if (highPriorityRequest == null) {
                                            if (WorldMap.component == null) {
                                                InterfaceList.anInt3337 = 0;
                                            }
                                            if (ClientScriptRunner.aClass13_14 != null) {
                                                ClientScriptRunner.method28();
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
                                                if (Static56.clickTileX != -1) {
                                                    Cheat.teleport(Camera.originX + Static56.clickTileX, Camera.originZ + Static116.anInt2954, Player.plane);
                                                }
                                                Static187.anInt4422 = 0;
                                                Static125.anInt3096 = 0;
                                            } else if (Static125.anInt3096 == 2) {
                                                if (Static56.clickTileX != -1) {
                                                    Protocol.outboundBuffer.pIsaac1(131);
                                                    Protocol.outboundBuffer.p4_alt3(MiniMenu.anInt2512);
                                                    Protocol.outboundBuffer.p2_alt2(Camera.originX + Static56.clickTileX);
                                                    Protocol.outboundBuffer.p2_alt3(MiniMenu.anInt506);
                                                    Protocol.outboundBuffer.p2_alt2(Static116.anInt2954 + Camera.originZ);
                                                    Cross.crossMode = 1;
                                                    Cross.crossCycle = 0;
                                                    Cross.y = Mouse.mouseClickY;
                                                    Cross.x = Mouse.mouseClickX;
                                                }
                                                Static125.anInt3096 = 0;
                                            } else if (Static187.anInt4422 == 2) {
                                                if (Static56.clickTileX != -1) {
                                                    Protocol.outboundBuffer.pIsaac1(179);
                                                    Protocol.outboundBuffer.p2(Camera.originZ + Static116.anInt2954);
                                                    Protocol.outboundBuffer.p2(Static56.clickTileX + Camera.originX);
                                                    Cross.crossCycle = 0;
                                                    Cross.crossMode = 1;
                                                    Cross.x = Mouse.mouseClickX;
                                                    Cross.y = Mouse.mouseClickY;
                                                }
                                                Static187.anInt4422 = 0;
                                            } else if (Static56.clickTileX != -1 && Static125.anInt3096 == 0 && Static187.anInt4422 == 0) {
                                                @Pc(1871) boolean success = PathFinder.tryMove(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, Static56.clickTileX, 0, 0, 0, Static116.anInt2954, PlayerList.self.movementQueueX[0]);
                                                if (success) {
                                                    Cross.y = Mouse.mouseClickY;
                                                    Cross.crossCycle = 0;
                                                    Cross.x = Mouse.mouseClickX;
                                                    Cross.crossMode = 1;
                                                }
                                            }
                                            Static56.clickTileX = -1;
                                            aClass6.method843();
                                            if (InterfaceList.aClass13_22 != component) {
                                                if (component != null) {
                                                    InterfaceList.redraw(component);
                                                }
                                                if (InterfaceList.aClass13_22 != null) {
                                                    InterfaceList.redraw(InterfaceList.aClass13_22);
                                                }
                                            }
                                            if (local1508 != Protocol.aClass13_11 && ClientScriptRunner.anInt4504 == Protocol.anInt5235) {
                                                if (local1508 != null) {
                                                    InterfaceList.redraw(local1508);
                                                }
                                                if (Protocol.aClass13_11 != null) {
                                                    InterfaceList.redraw(Protocol.aClass13_11);
                                                }
                                            }
                                            if (Protocol.aClass13_11 == null) {
                                                if (Protocol.anInt5235 > 0) {
                                                    Protocol.anInt5235--;
                                                }
                                            } else if (Protocol.anInt5235 < ClientScriptRunner.anInt4504) {
                                                Protocol.anInt5235++;
                                                if (ClientScriptRunner.anInt4504 == Protocol.anInt5235) {
                                                    InterfaceList.redraw(Protocol.aClass13_11);
                                                }
                                            }
                                            if (Camera.cameraType == 1) {
                                                Camera.method4273();
                                            } else if (Camera.cameraType == 2) {
                                                Camera.updateLockedCamera();
                                            } else {
                                                Camera.updateLoginScreenCamera();
                                            }
                                            for (y = 0; y < 5; y++) {
                                                @Pc(2001) int local2001 = Static31.cameraModifierCycle[y]++;
                                            }
                                            y = Static142.getIdleLoops(); // runetek4.Mouse
                                            x = Static195.getIdleLoops(); // runetek4.Keyboard
                                            if (y > 15000 && x > 15000) {
                                                idleTimeout = 250;
                                                Mouse.setIdleLoops(14500);
                                                Protocol.outboundBuffer.pIsaac1(245);
                                            }
                                            if (Protocol.openUrlRequest != null && Protocol.openUrlRequest.status == 1) {
                                                if (Protocol.openUrlRequest.result != null) {
                                                    Static169.openUrl(ClientScriptRunner.url, Protocol.newTab);
                                                }
                                                ClientScriptRunner.url = null;
                                                Protocol.openUrlRequest = null;
                                                Protocol.newTab = false;
                                            }
                                            Protocol.anInt3251++;
                                            Static82.minimapOffsetCycle++;
                                            Static143.cameraOffsetCycle++;
                                            if (Static143.cameraOffsetCycle > 500) {
                                                Static143.cameraOffsetCycle = 0;
                                                rand = (int) (Math.random() * 8.0D);
                                                if ((rand & 0x4) == 4) {
                                                    Camera.cameraAnticheatAngle += Static220.cameraOffsetYawModifier;
                                                }
                                                if ((rand & 0x2) == 2) {
                                                    Camera.cameraAnticheatOffsetZ += Static20.cameraOffsetZModifier;
                                                }
                                                if ((rand & 0x1) == 1) {
                                                    Camera.cameraAnticheatOffsetX += Camera.cameraOffsetXModifier;
                                                }
                                            }
                                            if (Static82.minimapOffsetCycle > 500) {
                                                Static82.minimapOffsetCycle = 0;
                                                rand = (int) (Math.random() * 8.0D);
                                                if ((rand & 0x1) == 1) {
                                                    MiniMap.minimapAnticheatAngle += Static263.minimapAngleModifier;
                                                }
                                                if ((rand & 0x2) == 2) {
                                                    MiniMap.minimapZoom += Static179.minimapZoomModifier;
                                                }
                                            }
                                            if (Camera.cameraAnticheatOffsetX < -50) {
                                                Camera.cameraOffsetXModifier = 2;
                                            }
                                            if (MiniMap.minimapAnticheatAngle < -60) {
                                                Static263.minimapAngleModifier = 2;
                                            }
                                            if (MiniMap.minimapZoom < -20) {
                                                Static179.minimapZoomModifier = 1;
                                            }
                                            if (Camera.cameraAnticheatOffsetZ < -55) {
                                                Static20.cameraOffsetZModifier = 2;
                                            }
                                            if (Camera.cameraAnticheatOffsetZ > 55) {
                                                Static20.cameraOffsetZModifier = -2;
                                            }
                                            if (Camera.cameraAnticheatAngle < -40) {
                                                Static220.cameraOffsetYawModifier = 1;
                                            }
                                            if (Camera.cameraAnticheatOffsetX > 50) {
                                                Camera.cameraOffsetXModifier = -2;
                                            }
                                            if (Camera.cameraAnticheatAngle > 40) {
                                                Static220.cameraOffsetYawModifier = -1;
                                            }
                                            if (MiniMap.minimapZoom > 10) {
                                                Static179.minimapZoomModifier = -1;
                                            }
                                            if (MiniMap.minimapAnticheatAngle > 60) {
                                                Static263.minimapAngleModifier = -2;
                                            }
                                            if (Protocol.anInt3251 > 50) {
                                                Protocol.outboundBuffer.pIsaac1(93);
                                            }
                                            if (Protocol.verifyIdChanged) {
                                                Static71.transmitVerifyId();
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
                                        highPriorityComponent = InterfaceList.getComponent(highPrioritySource.overlayer);
                                    } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPrioritySource.createdComponentId >= highPriorityComponent.createdComponents.length || highPrioritySource != highPriorityComponent.createdComponents[highPrioritySource.createdComponentId]);
                                    ClientScriptRunner.run(highPriorityRequest);
                                }
                            }
                            highPrioritySource = highPriorityRequest.source;
                            if (highPrioritySource.createdComponentId < 0) {
                                break;
                            }
                            highPriorityComponent = InterfaceList.getComponent(highPrioritySource.overlayer);
                        } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPriorityComponent.createdComponents.length <= highPrioritySource.createdComponentId || highPriorityComponent.createdComponents[highPrioritySource.createdComponentId] != highPrioritySource);
                        ClientScriptRunner.run(highPriorityRequest);
                    }
                }
                highPrioritySource = highPriorityRequest.source;
                if (highPrioritySource.createdComponentId < 0) {
                    break;
                }
                highPriorityComponent = InterfaceList.getComponent(highPrioritySource.overlayer);
            } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPrioritySource.createdComponentId >= highPriorityComponent.createdComponents.length || highPriorityComponent.createdComponents[highPrioritySource.createdComponentId] != highPrioritySource);
            ClientScriptRunner.run(highPriorityRequest);
        }
    }

    @OriginalMember(owner = "client!kd", name = "a", descriptor = "(Ljava/lang/String;B)V")
    public static void printHelp(@OriginalArg(0) String arg0) {
        System.out.println("Bad " + arg0 + ", Usage: worldid, <live/rc/wip>, <english/german>, <game0/game1>");
        System.exit(1);
    }

    @OriginalMember(owner = "client!nk", name = "c", descriptor = "(IZ)V")
    public static void pushNpcs(@OriginalArg(1) boolean arg0) {
        @Pc(7) int i;
        @Pc(16) Npc npc;
        @Pc(107) int npcSize;
        @Pc(113) int x;
        @Pc(133) int z;
        @Pc(149) int local149;
        @Pc(158) int local158;
        @Pc(171) int local171;
        for (i = 0; i < NpcList.npcCount; i++) {
            npc = NpcList.npcs[NpcList.npcIds[i]];
            if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.method2933()) {
                @Pc(42) int npcSize2 = npc.getSize();
                @Pc(97) int local97;
                if (npcSize2 == 1) {
                    if ((npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
                        local97 = npc.xFine >> 7;
                        npcSize = npc.zFine >> 7;
                        if (local97 >= 0 && local97 < 104 && npcSize >= 0 && npcSize < 104) {
                            local171 = Static31.anIntArrayArray6[local97][npcSize]++;
                        }
                    }
                } else if (((npcSize2 & 0x1) != 0 || (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0) && ((npcSize2 & 0x1) != 1 || (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64)) {
                    local97 = npc.xFine - npcSize2 * 64 >> 7;
                    npcSize = npc.zFine - npcSize2 * 64 >> 7;
                    x = npc.getSize() + local97;
                    if (local97 < 0) {
                        local97 = 0;
                    }
                    if (x > 104) {
                        x = 104;
                    }
                    z = npcSize + npc.getSize();
                    if (npcSize < 0) {
                        npcSize = 0;
                    }
                    if (z > 104) {
                        z = 104;
                    }
                    for (local149 = local97; local149 < x; local149++) {
                        for (local158 = npcSize; local158 < z; local158++) {
                            local171 = Static31.anIntArrayArray6[local149][local158]++;
                        }
                    }
                }
            }
        }
        label200: for (i = 0; i < NpcList.npcCount; i++) {
            npc = NpcList.npcs[NpcList.npcIds[i]];
            @Pc(262) long bitset = (long) NpcList.npcIds[i] << 32 | 0x20000000L;
            if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.method2933()) {
                npcSize = npc.getSize();
                if (npcSize == 1) {
                    if ((npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
                        x = npc.xFine >> 7;
                        z = npc.zFine >> 7;
                        if (x < 0 || x >= 104 || z < 0 || z >= 104) {
                            continue;
                        }
                        if (Static31.anIntArrayArray6[x][z] > 1) {
                            local171 = Static31.anIntArrayArray6[x][z]--;
                            continue;
                        }
                    }
                } else if ((npcSize & 0x1) == 0 && (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0 || (npcSize & 0x1) == 1 && (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
                    x = npc.xFine - npcSize * 64 >> 7;
                    z = npc.zFine - npcSize * 64 >> 7;
                    local158 = z + npcSize;
                    if (z < 0) {
                        z = 0;
                    }
                    @Pc(368) boolean local368 = true;
                    local149 = x + npcSize;
                    if (local158 > 104) {
                        local158 = 104;
                    }
                    if (x < 0) {
                        x = 0;
                    }
                    if (local149 > 104) {
                        local149 = 104;
                    }
                    @Pc(396) int local396;
                    @Pc(401) int local401;
                    for (local396 = x; local396 < local149; local396++) {
                        for (local401 = z; local401 < local158; local401++) {
                            if (Static31.anIntArrayArray6[local396][local401] <= 1) {
                                local368 = false;
                                break;
                            }
                        }
                    }
                    if (local368) {
                        local396 = x;
                        while (true) {
                            if (local396 >= local149) {
                                continue label200;
                            }
                            for (local401 = z; local401 < local158; local401++) {
                                local171 = Static31.anIntArrayArray6[local396][local401]--;
                            }
                            local396++;
                        }
                    }
                }
                if (!npc.type.active) {
                    bitset |= Long.MIN_VALUE;
                }
                npc.y = SceneGraph.getTileHeight(Player.plane, npc.xFine, npc.zFine);
                Static43.addTemporary(Player.plane, npc.xFine, npc.zFine, npc.y, npcSize * 64 + 60 - 64, npc, npc.anInt3381, bitset, npc.seqStretches);
            }
        }
    }

    @OriginalMember(owner = "client!cn", name = "b", descriptor = "(ZI)V")
    public static void pushPlayers(@OriginalArg(0) boolean arg0) {
        @Pc(3) int local3 = PlayerList.playerCount;
        if (LoginManager.mapFlagX == PlayerList.self.xFine >> 7 && PlayerList.self.zFine >> 7 == LoginManager.mapFlagZ) {
            LoginManager.mapFlagX = 0;
        }
        if (arg0) {
            local3 = 1;
        }
        @Pc(28) int i;
        @Pc(39) Player player;
        @Pc(82) int stz;
        @Pc(182) int local182;
        @Pc(200) int local200;
        @Pc(214) int local214;
        @Pc(223) int local223;
        @Pc(106) int local106;
        for (i = 0; i < local3; i++) {
            if (arg0) {
                player = PlayerList.self;
            } else {
                player = PlayerList.players[PlayerList.playerIds[i]];
            }
            if (player != null && player.isVisible()) {
                @Pc(55) int local55 = player.getSize();
                @Pc(77) int stx;
                if (local55 == 1) {
                    if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
                        stx = player.xFine >> 7;
                        stz = player.zFine >> 7;
                        if (stx >= 0 && stx < 104 && stz >= 0 && stz < 104) {
                            local106 = Static31.anIntArrayArray6[stx][stz]++;
                        }
                    }
                } else if (((local55 & 0x1) != 0 || (player.xFine & 0x7F) == 0 && (player.zFine & 0x7F) == 0) && ((local55 & 0x1) != 1 || (player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64)) {
                    stx = player.xFine - local55 * 64 >> 7;
                    stz = player.zFine - local55 * 64 >> 7;
                    local182 = player.getSize() + stx;
                    if (local182 > 104) {
                        local182 = 104;
                    }
                    if (stx < 0) {
                        stx = 0;
                    }
                    local200 = stz + player.getSize();
                    if (stz < 0) {
                        stz = 0;
                    }
                    if (local200 > 104) {
                        local200 = 104;
                    }
                    for (local214 = stx; local214 < local182; local214++) {
                        for (local223 = stz; local223 < local200; local223++) {
                            local106 = Static31.anIntArrayArray6[local214][local223]++;
                        }
                    }
                }
            }
        }
        label220: for (i = 0; i < local3; i++) {
            @Pc(272) long id;
            if (arg0) {
                player = PlayerList.self;
                id = 8791798054912L;
            } else {
                player = PlayerList.players[PlayerList.playerIds[i]];
                id = (long) PlayerList.playerIds[i] << 32;
            }
            if (player != null && player.isVisible()) {
                player.lowMemory = false;
                if ((Static15.lowMemory && PlayerList.playerCount > 200 || PlayerList.playerCount > 50) && !arg0 && player.movementSeqId == player.getBasType().idleAnimationId) {
                    player.lowMemory = true;
                }
                stz = player.getSize();
                if (stz == 1) {
                    if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
                        local182 = player.xFine >> 7;
                        local200 = player.zFine >> 7;
                        if (local182 < 0 || local182 >= 104 || local200 < 0 || local200 >= 104) {
                            continue;
                        }
                        if (Static31.anIntArrayArray6[local182][local200] > 1) {
                            local106 = Static31.anIntArrayArray6[local182][local200]--;
                            continue;
                        }
                    }
                } else if ((stz & 0x1) == 0 && (player.xFine & 0x7F) == 0 && (player.zFine & 0x7F) == 0 || (stz & 0x1) == 1 && (player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 0) {
                    local182 = player.xFine - stz * 64 >> 7;
                    local214 = stz + local182;
                    local200 = player.zFine - stz * 64 >> 7;
                    if (local214 > 104) {
                        local214 = 104;
                    }
                    if (local182 < 0) {
                        local182 = 0;
                    }
                    local223 = stz + local200;
                    if (local200 < 0) {
                        local200 = 0;
                    }
                    @Pc(468) boolean local468 = true;
                    if (local223 > 104) {
                        local223 = 104;
                    }
                    @Pc(476) int local476;
                    @Pc(485) int local485;
                    for (local476 = local182; local476 < local214; local476++) {
                        for (local485 = local200; local485 < local223; local485++) {
                            if (Static31.anIntArrayArray6[local476][local485] <= 1) {
                                local468 = false;
                                break;
                            }
                        }
                    }
                    if (local468) {
                        local476 = local182;
                        while (true) {
                            if (local476 >= local214) {
                                continue label220;
                            }
                            for (local485 = local200; local485 < local223; local485++) {
                                local106 = Static31.anIntArrayArray6[local476][local485]--;
                            }
                            local476++;
                        }
                    }
                }
                if (player.attachment == null || client.loop < player.attachmentSetAt || player.attachmentResetAt <= client.loop) {
                    player.y = SceneGraph.getTileHeight(Player.plane, player.xFine, player.zFine);
                    Static43.addTemporary(Player.plane, player.xFine, player.zFine, player.y, (stz - 1) * 64 + 60, player, player.anInt3381, id, player.seqStretches);
                } else {
                    player.lowMemory = false;
                    player.y = SceneGraph.getTileHeight(Player.plane, player.xFine, player.zFine);
                    Static184.addTemporary(Player.plane, player.xFine, player.zFine, player.y, player, player.anInt3381, id, player.atachmentX0, player.attachmentZ0, player.attachmentX1, player.attachmentZ1);
                }
            }
        }
    }

    @OriginalMember(owner = "client!wj", name = "b", descriptor = "(B)V")
    public static void processLogout() {
        if (Protocol.gameServerSocket != null) {
            Protocol.gameServerSocket.closeGracefully();
            Protocol.gameServerSocket = null;
        }
        client.unload();
        SceneGraph.clear();
        @Pc(19) int local19;
        for (local19 = 0; local19 < 4; local19++) {
            PathFinder.collisionMaps[local19].reset();
        }
        WorldMap.clear(false);
        System.gc();
        Static29.method801();
        Static144.jingle = false;
        MusicPlayer.groupId = -1;
        AreaSoundManager.clear(true);
        Static230.aBoolean250 = false;
        Camera.originZ = 0;
        Static80.anInt4701 = 0;
        Static52.anInt1695 = 0;
        Camera.originX = 0;
        for (local19 = 0; local19 < MiniMap.hintMapMarkers.length; local19++) {
            MiniMap.hintMapMarkers[local19] = null;
        }
        PlayerList.playerCount = 0;
        NpcList.npcCount = 0;
        for (local19 = 0; local19 < 2048; local19++) {
            PlayerList.players[local19] = null;
            PlayerList.PLAYER_APPEARANCE_PACKET[local19] = null;
        }
        for (local19 = 0; local19 < 32768; local19++) {
            NpcList.npcs[local19] = null;
        }
        for (local19 = 0; local19 < 4; local19++) {
            for (@Pc(115) int local115 = 0; local115 < 104; local115++) {
                for (@Pc(122) int local122 = 0; local122 < 104; local122++) {
                    SceneGraph.objStacks[local19][local115][local122] = null;
                }
            }
        }
        Camera.resetCameraEffects();
        Protocol.verifyId = 0;
        VarpDomain.resetVarBits();
        InterfaceList.method1596(true);
    }

    @OriginalMember(owner = "client!nm", name = "a", descriptor = "(Z)V")
    public static void tryReconnect() {
        if (idleTimeout > 0) {
            processLogout();
        } else {
            Protocol.aClass95_4 = Protocol.gameServerSocket;
            Protocol.gameServerSocket = null;
            client.processGameStatus(40);
        }
    }

    @OriginalMember(owner = "client!pk", name = "i", descriptor = "(I)V")
    public static void pushProjectiles() {
        for (@Pc(16) ProjectileEntity proj = (ProjectileEntity) SceneGraph.projectiles.head(); proj != null; proj = (ProjectileEntity) SceneGraph.projectiles.next()) {
            @Pc(21) ProjectileAnimation projAnim = proj.aClass8_Sub6_1;
            if (Player.plane != projAnim.level || projAnim.lastCycle < client.loop) {
                proj.unlink();
            } else if (client.loop >= projAnim.startCycle) {
                if (projAnim.target > 0) {
                    @Pc(54) Npc npc = NpcList.npcs[projAnim.target - 1];
                    if (npc != null && npc.xFine >= 0 && npc.xFine < 13312 && npc.zFine >= 0 && npc.zFine < 13312) {
                        projAnim.updateVelocity(npc.zFine, client.loop, SceneGraph.getTileHeight(projAnim.level, npc.xFine, npc.zFine) - projAnim.anInt4805, npc.xFine);
                    }
                }
                if (projAnim.target < 0) {
                    @Pc(102) int index = -projAnim.target - 1;
                    @Pc(107) Player player;
                    if (PlayerList.selfId == index) {
                        player = PlayerList.self;
                    } else {
                        player = PlayerList.players[index];
                    }
                    if (player != null && player.xFine >= 0 && player.xFine < 13312 && player.zFine >= 0 && player.zFine < 13312) {
                        projAnim.updateVelocity(player.zFine, client.loop, SceneGraph.getTileHeight(projAnim.level, player.xFine, player.zFine) - projAnim.anInt4805, player.xFine);
                    }
                }
                projAnim.update(Protocol.sceneDelta);
                Static43.addTemporary(Player.plane, (int) projAnim.x, (int) projAnim.y, (int) projAnim.z, 60, projAnim, projAnim.yaw, -1L, false);
            }
        }
    }

    @OriginalMember(owner = "client!u", name = "a", descriptor = "(Z)V")
    public static void pushSpotanims() {
        for (@Pc(9) SpotAnimEntity entity = (SpotAnimEntity) SceneGraph.spotanims.head(); entity != null; entity = (SpotAnimEntity) SceneGraph.spotanims.next()) {
            @Pc(15) SpotAnim spotAnim = entity.aClass8_Sub2_1;
            if (spotAnim.level != Player.plane || spotAnim.seqComplete) {
                entity.unlink();
            } else if (spotAnim.startCycle <= client.loop) {
                spotAnim.update(Protocol.sceneDelta);
                if (spotAnim.seqComplete) {
                    entity.unlink();
                } else {
                    Static43.addTemporary(spotAnim.level, spotAnim.x, spotAnim.z, spotAnim.anInt599, 60, spotAnim, 0, -1L, false);
                }
            }
        }
    }
}
