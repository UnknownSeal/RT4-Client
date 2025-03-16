package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.game.config.bastype.BasTypeList;
import com.jagex.runetek4.input.MouseCapturer;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.dash3d.entity.ProjectileEntity;
import com.jagex.runetek4.dash3d.entity.SpotAnimEntity;
import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class Game {
    @OriginalMember(owner = "runetek4.client!vl", name = "k", descriptor = "I")
    public static int idleTimeout = 0;
    @OriginalMember(owner = "runetek4.client!od", name = "f", descriptor = "Lclient!jd;")
    public static MouseCapturer mouseCapturer;

    @OriginalMember(owner = "runetek4.client!gg", name = "a", descriptor = "(Z)V")
    public static void updateGame() {
        // todo: consolidate/rename static classes
        if (idleTimeout > 0) {
            idleTimeout--;
        }
        if (Static60.systemUpdateTimer > 1) {
            Static60.systemUpdateTimer--;
            Static209.miscTransmitAt = InterfaceList.transmitTimer;
        }
        if (Static224.aBoolean247) {
            Static224.aBoolean247 = false;
            tryReconnect();
            return;
        }
        for (@Pc(34) int i = 0; i < 100 && Static10.readPacket(); i++) {
        }
        if (Static244.gamestate != 30) {
            return;
        }
        ClientScriptRunner.createClientScriptCheckPacket(Static6.outboundBuffer); // runetek4.ReflectionCheck
        @Pc(60) Object mouseRecorder = mouseCapturer.lock;
        @Pc(86) int offset;
        @Pc(79) int samples;
        @Pc(88) int i;
        @Pc(106) int y;
        @Pc(111) int x;
        @Pc(182) int dx;
        @Pc(189) int dy;
        synchronized (mouseRecorder) {
            if (!Static245.enabled) {
                mouseCapturer.coord = 0;
            } else if (Mouse.clickButton != 0 || mouseCapturer.coord >= 40) {
                Static6.outboundBuffer.pIsaac1(123);
                samples = 0;
                Static6.outboundBuffer.p1(0);
                offset = Static6.outboundBuffer.offset;
                for (i = 0; mouseCapturer.coord > i && Static6.outboundBuffer.offset - offset < 240; i++) {
                    samples++;
                    y = mouseCapturer.y[i];
                    x = mouseCapturer.x[i];
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
                    if (mouseCapturer.y[i] == -1 && mouseCapturer.x[i] == -1) {
                        outsideWindow = true;
                        y = -1;
                        x = -1;
                    }
                    if (Static264.mouseRecorderPrevX != x || Static179.mouseRecorderPrevY != y) {
                        dx = x - Static264.mouseRecorderPrevX;
                        Static264.mouseRecorderPrevX = x;
                        dy = y - Static179.mouseRecorderPrevY;
                        Static179.mouseRecorderPrevY = y;
                        if (Static204.anInt4762 < 8 && dx >= -32 && dx <= 31 && dy >= -32 && dy <= 31) {
                            dy += 32;
                            dx += 32;
                            Static6.outboundBuffer.p2(dy + (Static204.anInt4762 << 12) + (dx << 6));
                            Static204.anInt4762 = 0;
                        } else if (Static204.anInt4762 < 32 && dx >= -128 && dx <= 127 && dy >= -128 && dy <= 127) {
                            Static6.outboundBuffer.p1(Static204.anInt4762 + 128);
                            dy += 128;
                            dx += 128;
                            Static6.outboundBuffer.p2((dx << 8) + dy);
                            Static204.anInt4762 = 0;
                        } else if (Static204.anInt4762 < 32) {
                            Static6.outboundBuffer.p1(Static204.anInt4762 + 192);
                            if (outsideWindow) {
                                Static6.outboundBuffer.p4(Integer.MIN_VALUE);
                            } else {
                                Static6.outboundBuffer.p4(x | y << 16);
                            }
                            Static204.anInt4762 = 0;
                        } else {
                            Static6.outboundBuffer.p2(Static204.anInt4762 + 57344);
                            if (outsideWindow) {
                                Static6.outboundBuffer.p4(Integer.MIN_VALUE);
                            } else {
                                Static6.outboundBuffer.p4(x | y << 16);
                            }
                            Static204.anInt4762 = 0;
                        }
                    } else if (Static204.anInt4762 < 2047) {
                        Static204.anInt4762++;
                    }
                }
                Static6.outboundBuffer.p1len(Static6.outboundBuffer.offset - offset);
                if (mouseCapturer.coord > samples) {
                    mouseCapturer.coord -= samples;
                    for (i = 0; i < mouseCapturer.coord; i++) {
                        mouseCapturer.x[i] = mouseCapturer.x[samples + i];
                        mouseCapturer.y[i] = mouseCapturer.y[samples + i];
                    }
                } else {
                    mouseCapturer.coord = 0;
                }
            }
        }
        if (Mouse.clickButton != 0) {
            @Pc(411) long loops = (Static133.clickTime - Static183.prevClickTime) / 50L;
            samples = Static60.mouseClickY;
            if (samples < 0) {
                samples = 0;
            } else if (samples > 65535) {
                samples = 65535;
            }
            if (loops > 32767L) {
                loops = 32767L;
            }
            i = aClass6.mouseClickX;
            Static183.prevClickTime = Static133.clickTime;
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
            Static6.outboundBuffer.pIsaac1(75);
            Static6.outboundBuffer.p2_alt3(button << 15 | x);
            Static6.outboundBuffer.p4_alt3(i | samples << 16);
        }
        if (Static16.anInt551 > 0) {
            Static16.anInt551--;
        }
        if (Preferences.aBoolean63) {
            for (i = 0; i < Static182.keyQueueSize; i++) {
                offset = InterfaceList.keyCodes[i];
                if (offset == 98 || offset == 99 || offset == 96 || offset == 97) {
                    Static197.aBoolean228 = true;
                    break;
                }
            }
        } else if (Static187.pressedKeys[96] || Static187.pressedKeys[97] || Static187.pressedKeys[98] || Static187.pressedKeys[99]) {
            Static197.aBoolean228 = true;
        }
        if (Static197.aBoolean228 && Static16.anInt551 <= 0) {
            Static16.anInt551 = 20;
            Static197.aBoolean228 = false;
            Static6.outboundBuffer.pIsaac1(21);
            Static6.outboundBuffer.p2_alt2(Camera.orbitCameraPitch);
            Static6.outboundBuffer.p2_alt1(Camera.orbitCameraYaw);
        }
        if (Static26.focus && !Static67.prevFocus) {
            Static67.prevFocus = true;
            Static6.outboundBuffer.pIsaac1(22);
            Static6.outboundBuffer.p1(1);
        }
        if (!Static26.focus && Static67.prevFocus) {
            Static67.prevFocus = false;
            Static6.outboundBuffer.pIsaac1(22);
            Static6.outboundBuffer.p1(0);
        }
        if (!Preferences.sentToServer) {
            Static6.outboundBuffer.pIsaac1(98);
            Static6.outboundBuffer.p4(Static145.method2746());
            Preferences.sentToServer = true;
        }
        Static31.method846();
        if (Static244.gamestate != 30) {
            return;
        }
        ChangeLocRequest.loop(); // ChangeLocRequest
        AttachLocRequest.loop(); // AttachLocRequest
        Static54.loop(); // SoundPlayer
        Static201.idleNetCycles++;
        if (Static201.idleNetCycles > 750) {
            tryReconnect();
            return;
        }
        Static71.updatePlayers();
        Static109.updateNpcs();
        Static19.tickChatTimers(); // OverheadChat
        if (Static24.component != null) {
            Static12.method447();
        }
        // VarpDomain
        for (i = Static38.poll(true); i != -1; i = Static38.poll(false)) {
            Static85.handleVarps(i);
            Static83.updatedVarps[Static70.updatedVarpsWriterIndex++ & 0x1F] = i;
        }
        @Pc(782) int rand;
        // runetek4.DelayedStateChange
        for (@Pc(709) DelayedStateChange change = Static127.poll(); change != null; change = Static127.poll()) {
            samples = change.method1011();
            i = change.method1012();
            if (samples == 1) {
                VarPlayerDefinition.varcs[i] = change.intArg1;
                Static138.updatedVarcs[Static4.updatedVarcsWriterIndex++ & 0x1F] = i;
            } else if (samples == 2) {
                Static226.varcstrs[i] = change.stringArg;
                ClientScriptRunner.updatedVarcstrs[Static72.updatedVarcstrsWriterIndex++ & 0x1F] = i;
            } else {
                @Pc(773) Component component;
                if (samples == 3) {
                    component = Component.getComponent(i);
                    if (!change.stringArg.method3108(component.aClass100_84)) {
                        component.aClass100_84 = change.stringArg;
                        Static43.method1143(component);
                    }
                } else if (samples == 4) {
                    component = Component.getComponent(i);
                    x = change.intArg1;
                    dx = change.intArg2;
                    rand = change.intArg3;
                    if (component.modelType != x || component.modelId != rand || dx != component.anInt498) {
                        component.modelId = rand;
                        component.anInt498 = dx;
                        component.modelType = x;
                        Static43.method1143(component);
                    }
                } else if (samples == 5) {
                    component = Component.getComponent(i);
                    if (component.anInt522 != change.intArg1 || change.intArg1 == -1) {
                        component.anInt496 = 1;
                        component.anInt500 = 0;
                        component.anInt522 = change.intArg1;
                        component.anInt510 = 0;
                        Static43.method1143(component);
                    }
                } else if (samples == 6) {
                    y = change.intArg1;
                    x = y >> 10 & 0x1F;
                    dx = y & 0x1F;
                    rand = y >> 5 & 0x1F;
                    @Pc(1189) Component local1189 = Component.getComponent(i);
                    dy = (dx << 3) + (rand << 11) + (x << 19);
                    if (dy != local1189.anInt474) {
                        local1189.anInt474 = dy;
                        Static43.method1143(local1189);
                    }
                } else if (samples == 7) {
                    component = Component.getComponent(i);
                    // todo: this should not be necessary, data/server-related?
                    if (component != null) {
                        @Pc(1145) boolean hidden = change.intArg1 == 1;
                        if (hidden != component.hidden) {
                            component.hidden = hidden;
                            Static43.method1143(component);
                        }
                    }
                } else if (samples == 8) {
                    component = Component.getComponent(i);
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
                        Static43.method1143(component);
                    }
                } else if (samples == 9) {
                    component = Component.getComponent(i);
                    if (change.intArg1 != component.objId || component.objCount != change.intArg3) {
                        component.objId = change.intArg1;
                        component.objCount = change.intArg3;
                        Static43.method1143(component);
                    }
                } else if (samples == 10) {
                    component = Component.getComponent(i);
                    if (component.modelXOffset != change.intArg1 || change.intArg3 != component.modelZOffset || component.modelYOffset != change.intArg2) {
                        component.modelZOffset = change.intArg3;
                        component.modelYOffset = change.intArg2;
                        component.modelXOffset = change.intArg1;
                        Static43.method1143(component);
                    }
                } else if (samples == 11) {
                    component = Component.getComponent(i);
                    component.x = component.baseX = change.intArg1;
                    component.yMode = 0;
                    component.xMode = 0;
                    component.y = component.baseY = change.intArg3;
                    Static43.method1143(component);
                } else if (samples == 12) {
                    component = Component.getComponent(i);
                    x = change.intArg1;
                    if (component != null && component.INVENTORY == 0) {
                        if (x > component.anInt491 - component.anInt459) {
                            x = component.anInt491 - component.anInt459;
                        }
                        if (x < 0) {
                            x = 0;
                        }
                        if (x != component.scrollY) {
                            component.scrollY = x;
                            Static43.method1143(component);
                        }
                    }
                } else if (samples == 13) {
                    component = Component.getComponent(i);
                    component.modelRotationSpeed = change.intArg1;
                }
            }
        }
        // Cross
        if (Static70.crossMode != 0) {
            Static17.crossCycle += 20;
            if (Static17.crossCycle >= 400) {
                Static70.crossMode = 0;
            }
        }
        Static178.sceneDelta++;
        if (Static257.aClass13_7 != null) {
            Static72.anInt2043++;
            if (Static72.anInt2043 >= 15) {
                Static43.method1143(Static257.aClass13_7);
                Static257.aClass13_7 = null;
            }
        }
        @Pc(1361) Component component;
        if (Static118.component != null) {
            Static43.method1143(Static118.component);
            if (Static149.anInt3554 + 5 < Static215.anInt4873 || Static215.anInt4873 < Static149.anInt3554 - 5 || Static206.anInt4773 + 5 < Static223.anInt5032 || Static206.anInt4773 - 5 > Static223.anInt5032) {
                Static123.lastItemDragged = true;
            }
            Static78.lastItemDragTime++;
            if (Static22.activeInterfaceType == 0) {
                if (Static123.lastItemDragged && Static78.lastItemDragTime >= 5) {
                    if (Static118.component == Static169.aClass13_18 && Static4.selectedInventorySlot != Static18.mouseInvInterfaceIndex) {
                        component = Static118.component;
                        @Pc(1363) byte moveItemInsertionMode = 0;
                        if (Static179.bankInsertMode == 1 && component.contentType == 206) {
                            moveItemInsertionMode = 1;
                        }
                        if (component.invSlotObjId[Static18.mouseInvInterfaceIndex] <= 0) {
                            moveItemInsertionMode = 0;
                        }
                        if (Static36.method940(component).method504()) {
                            y = Static4.selectedInventorySlot;
                            x = Static18.mouseInvInterfaceIndex;
                            component.invSlotObjId[x] = component.invSlotObjId[y];
                            component.invSlotObjCount[x] = component.invSlotObjCount[y];
                            component.invSlotObjId[y] = -1;
                            component.invSlotObjCount[y] = 0;
                        } else if (moveItemInsertionMode == 1) {
                            x = Static18.mouseInvInterfaceIndex;
                            y = Static4.selectedInventorySlot;
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
                            component.swapObjs(Static18.mouseInvInterfaceIndex, Static4.selectedInventorySlot);
                        }
                        Static6.outboundBuffer.pIsaac1(231);
                        Static6.outboundBuffer.p2(Static4.selectedInventorySlot);
                        Static6.outboundBuffer.p4_alt1(Static118.component.anInt507);
                        Static6.outboundBuffer.p2_alt2(Static18.mouseInvInterfaceIndex);
                        Static6.outboundBuffer.p1_alt3(moveItemInsertionMode);
                    }
                } else if ((Static116.oneMouseButton == 1 || Static277.menuHasAddFriend(PreciseSleep.menuActionRow - 1)) && PreciseSleep.menuActionRow > 2) {
                    Static226.determineMenuSize();
                } else if (PreciseSleep.menuActionRow > 0) {
                    Static59.processMenuActions();
                }
                Mouse.clickButton = 0;
                Static72.anInt2043 = 10;
                Static118.component = null;
            }
        }
        Static146.aBoolean174 = false;
        Static56.aClass13_12 = null;
        Static44.aBoolean83 = false;
        Static182.keyQueueSize = 0;
        component = Static180.aClass13_22;
        Static180.aClass13_22 = null;
        @Pc(1508) Component local1508 = Static43.aClass13_11;
        Static43.aClass13_11 = null;
        while (Static25.nextKey() && Static182.keyQueueSize < 128) {
            InterfaceList.keyCodes[Static182.keyQueueSize] = Static102.keyCode;
            Static205.keyChars[Static182.keyQueueSize] = Static193.keyChar;
            Static182.keyQueueSize++;
        }
        // WorldMap.component
        Static24.component = null;
        if (Static154.topLevelInterace != -1) {
            Static57.method1320(0, 0, 0, GameShell.canvasWidth, Static154.topLevelInterace, 0, GameShell.canvasHeigth);
        }
        InterfaceList.transmitTimer++;
        while (true) {
            // todo: this is actually split up into low/medium/high
            @Pc(1569) Component highPriorityComponent;
            @Pc(1560) Component highPrioritySource;
            @Pc(1555) HookRequest highPriorityRequest;
            do {
                highPriorityRequest = (HookRequest) Static4.aClass69_2.removeHead();
                if (highPriorityRequest == null) {
                    while (true) {
                        do {
                            highPriorityRequest = (HookRequest) Static115.aClass69_70.removeHead();
                            if (highPriorityRequest == null) {
                                while (true) {
                                    do {
                                        highPriorityRequest = (HookRequest) Static185.aClass69_101.removeHead();
                                        if (highPriorityRequest == null) {
                                            if (Static24.component == null) {
                                                Static137.anInt3337 = 0;
                                            }
                                            if (Static105.aClass13_14 != null) {
                                                Static4.method28();
                                            }
                                            if (Static191.staffModLevel > 0 && Static187.pressedKeys[82] && Static187.pressedKeys[81] && Static58.wheelRotation != 0) {
                                                y = Player.plane - Static58.wheelRotation;
                                                if (y < 0) {
                                                    y = 0;
                                                } else if (y > 3) {
                                                    y = 3;
                                                }
                                                // Cheat
                                                Cheat.teleport(PlayerList.self.pathTileX[0] + Camera.originX, PlayerList.self.pathTileZ[0] + Camera.originZ, y);
                                            }
                                            if (Static191.staffModLevel > 0 && Static187.pressedKeys[82] && Static187.pressedKeys[81]) {
                                                if (Static56.clickTileX != -1) {
                                                    Cheat.teleport(Camera.originX + Static56.clickTileX, Camera.originZ - -Static116.anInt2954, Player.plane);
                                                }
                                                Static187.anInt4422 = 0;
                                                Static125.anInt3096 = 0;
                                            } else if (Static125.anInt3096 == 2) {
                                                if (Static56.clickTileX != -1) {
                                                    Static6.outboundBuffer.pIsaac1(131);
                                                    Static6.outboundBuffer.p4_alt3(Static98.anInt2512);
                                                    Static6.outboundBuffer.p2_alt2(Camera.originX + Static56.clickTileX);
                                                    Static6.outboundBuffer.p2_alt3(Static15.anInt506);
                                                    Static6.outboundBuffer.p2_alt2(Static116.anInt2954 + Camera.originZ);
                                                    Static70.crossMode = 1;
                                                    Static17.crossCycle = 0;
                                                    Static25.y = Static60.mouseClickY;
                                                    Static122.x = aClass6.mouseClickX;
                                                }
                                                Static125.anInt3096 = 0;
                                            } else if (Static187.anInt4422 == 2) {
                                                if (Static56.clickTileX != -1) {
                                                    Static6.outboundBuffer.pIsaac1(179);
                                                    Static6.outboundBuffer.p2(Camera.originZ + Static116.anInt2954);
                                                    Static6.outboundBuffer.p2(Static56.clickTileX + Camera.originX);
                                                    Static17.crossCycle = 0;
                                                    Static70.crossMode = 1;
                                                    Static122.x = aClass6.mouseClickX;
                                                    Static25.y = Static60.mouseClickY;
                                                }
                                                Static187.anInt4422 = 0;
                                            } else if (Static56.clickTileX != -1 && Static125.anInt3096 == 0 && Static187.anInt4422 == 0) {
                                                @Pc(1871) boolean success = Static102.tryMove(PlayerList.self.pathTileZ[0], 0, 0, true, 0, Static56.clickTileX, 0, 0, 0, Static116.anInt2954, PlayerList.self.pathTileX[0]);
                                                if (success) {
                                                    Static25.y = Static60.mouseClickY;
                                                    Static17.crossCycle = 0;
                                                    Static122.x = aClass6.mouseClickX;
                                                    Static70.crossMode = 1;
                                                }
                                            }
                                            Static56.clickTileX = -1;
                                            aClass6.method843();
                                            if (Static180.aClass13_22 != component) {
                                                if (component != null) {
                                                    Static43.method1143(component);
                                                }
                                                if (Static180.aClass13_22 != null) {
                                                    Static43.method1143(Static180.aClass13_22);
                                                }
                                            }
                                            if (local1508 != Static43.aClass13_11 && Static191.anInt4504 == Static133.anInt5235) {
                                                if (local1508 != null) {
                                                    Static43.method1143(local1508);
                                                }
                                                if (Static43.aClass13_11 != null) {
                                                    Static43.method1143(Static43.aClass13_11);
                                                }
                                            }
                                            if (Static43.aClass13_11 == null) {
                                                if (Static133.anInt5235 > 0) {
                                                    Static133.anInt5235--;
                                                }
                                            } else if (Static133.anInt5235 < Static191.anInt4504) {
                                                Static133.anInt5235++;
                                                if (Static191.anInt4504 == Static133.anInt5235) {
                                                    Static43.method1143(Static43.aClass13_11);
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
                                                Static48.setIdleLoops(14500);
                                                Static6.outboundBuffer.pIsaac1(245);
                                            }
                                            if (Static33.openUrlRequest != null && Static33.openUrlRequest.status == 1) {
                                                if (Static33.openUrlRequest.result != null) {
                                                    Static169.openUrl(Static175.url, Static164.newTab);
                                                }
                                                Static175.url = null;
                                                Static33.openUrlRequest = null;
                                                Static164.newTab = false;
                                            }
                                            Static131.anInt3251++;
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
                                                    Static59.minimapAnticheatAngle += Static263.minimapAngleModifier;
                                                }
                                                if ((rand & 0x2) == 2) {
                                                    Static273.minimapZoom += Static179.minimapZoomModifier;
                                                }
                                            }
                                            if (Camera.cameraAnticheatOffsetX < -50) {
                                                Camera.cameraOffsetXModifier = 2;
                                            }
                                            if (Static59.minimapAnticheatAngle < -60) {
                                                Static263.minimapAngleModifier = 2;
                                            }
                                            if (Static273.minimapZoom < -20) {
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
                                            if (Static273.minimapZoom > 10) {
                                                Static179.minimapZoomModifier = -1;
                                            }
                                            if (Static59.minimapAnticheatAngle > 60) {
                                                Static263.minimapAngleModifier = -2;
                                            }
                                            if (Static131.anInt3251 > 50) {
                                                Static6.outboundBuffer.pIsaac1(93);
                                            }
                                            if (Static34.verifyIdChanged) {
                                                Static71.transmitVerifyId();
                                                Static34.verifyIdChanged = false;
                                            }
                                            try {
                                                if (Static124.gameServerSocket != null && Static6.outboundBuffer.offset > 0) {
                                                    Static124.gameServerSocket.write(Static6.outboundBuffer.offset, Static6.outboundBuffer.data);
                                                    Static131.anInt3251 = 0;
                                                    Static6.outboundBuffer.offset = 0;
                                                }
                                            } catch (@Pc(2266) IOException local2266) {
                                                tryReconnect();
                                            }
                                            return;
                                        }
                                        // low priority actually
                                        highPrioritySource = highPriorityRequest.source;
                                        if (highPrioritySource.componentId < 0) {
                                            break;
                                        }
                                        highPriorityComponent = Component.getComponent(highPrioritySource.layer);
                                    } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPrioritySource.componentId >= highPriorityComponent.createdComponents.length || highPrioritySource != highPriorityComponent.createdComponents[highPrioritySource.componentId]);
                                    Static82.method1767(highPriorityRequest);
                                }
                            }
                            highPrioritySource = highPriorityRequest.source;
                            if (highPrioritySource.componentId < 0) {
                                break;
                            }
                            highPriorityComponent = Component.getComponent(highPrioritySource.layer);
                        } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPriorityComponent.createdComponents.length <= highPrioritySource.componentId || highPriorityComponent.createdComponents[highPrioritySource.componentId] != highPrioritySource);
                        Static82.method1767(highPriorityRequest);
                    }
                }
                highPrioritySource = highPriorityRequest.source;
                if (highPrioritySource.componentId < 0) {
                    break;
                }
                highPriorityComponent = Component.getComponent(highPrioritySource.layer);
            } while (highPriorityComponent == null || highPriorityComponent.createdComponents == null || highPrioritySource.componentId >= highPriorityComponent.createdComponents.length || highPriorityComponent.createdComponents[highPrioritySource.componentId] != highPrioritySource);
            Static82.method1767(highPriorityRequest);
        }
    }

    @OriginalMember(owner = "runetek4.client!kd", name = "a", descriptor = "(Ljava/lang/String;B)V")
    public static void printHelp(@OriginalArg(0) String arg0) {
        System.out.println("Bad " + arg0 + ", Usage: worldid, <live/rc/wip>, <english/german>, <game0/game1>");
        System.exit(1);
    }

    @OriginalMember(owner = "runetek4.client!nk", name = "c", descriptor = "(IZ)V")
    public static void pushNpcs(@OriginalArg(1) boolean arg0) {
        @Pc(7) int i;
        @Pc(16) Npc npc;
        @Pc(107) int npcSize;
        @Pc(113) int x;
        @Pc(133) int z;
        @Pc(149) int local149;
        @Pc(158) int local158;
        @Pc(171) int local171;
        for (i = 0; i < Static272.npcCount; i++) {
            npc = NpcList.npcs[Static33.npcIds[i]];
            if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.method2933()) {
                @Pc(42) int npcSize2 = npc.size();
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
                    x = npc.size() + local97;
                    if (local97 < 0) {
                        local97 = 0;
                    }
                    if (x > 104) {
                        x = 104;
                    }
                    z = npcSize + npc.size();
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
        label200: for (i = 0; i < Static272.npcCount; i++) {
            npc = NpcList.npcs[Static33.npcIds[i]];
            @Pc(262) long bitset = (long) Static33.npcIds[i] << 32 | 0x20000000L;
            if (npc != null && npc.isVisible() && npc.type.drawabove == arg0 && npc.type.method2933()) {
                npcSize = npc.size();
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

    @OriginalMember(owner = "runetek4.client!cn", name = "b", descriptor = "(ZI)V")
    public static void pushPlayers(@OriginalArg(0) boolean arg0) {
        @Pc(3) int local3 = Static267.playerCount;
        if (Static115.anInt2939 == PlayerList.self.xFine >> 7 && PlayerList.self.zFine >> 7 == Static84.anInt2255) {
            Static115.anInt2939 = 0;
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
                player = Static159.players[Static105.playerIds[i]];
            }
            if (player != null && player.isVisible()) {
                @Pc(55) int local55 = player.size();
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
                    local182 = player.size() + stx;
                    if (local182 > 104) {
                        local182 = 104;
                    }
                    if (stx < 0) {
                        stx = 0;
                    }
                    local200 = stz + player.size();
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
                player = Static159.players[Static105.playerIds[i]];
                id = (long) Static105.playerIds[i] << 32;
            }
            if (player != null && player.isVisible()) {
                player.lowMemory = false;
                if ((Static15.lowMemory && Static267.playerCount > 200 || Static267.playerCount > 50) && !arg0 && player.movementSeqId == player.getBasType().idleAnimationId) {
                    player.lowMemory = true;
                }
                stz = player.size();
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

    @OriginalMember(owner = "runetek4.client!wj", name = "b", descriptor = "(B)V")
    public static void processLogout() {
        if (Static124.gameServerSocket != null) {
            Static124.gameServerSocket.closeGracefully();
            Static124.gameServerSocket = null;
        }
        Static217.method3768();
        SceneGraph.clear();
        @Pc(19) int local19;
        for (local19 = 0; local19 < 4; local19++) {
            PathFinder.collisionMaps[local19].reset();
        }
        Static116.method2325(false);
        System.gc();
        Static29.method801();
        Static144.aBoolean173 = false;
        BZip2State.anInt4363 = -1;
        AreaSoundManager.clear(true);
        Static230.aBoolean250 = false;
        Camera.originZ = 0;
        Static80.anInt4701 = 0;
        Static52.anInt1695 = 0;
        Camera.originX = 0;
        for (local19 = 0; local19 < Static143.aClass102Array1.length; local19++) {
            Static143.aClass102Array1[local19] = null;
        }
        Static267.playerCount = 0;
        Static272.npcCount = 0;
        for (local19 = 0; local19 < 2048; local19++) {
            Static159.players[local19] = null;
            Static115.PLAYER_APPEARANCE_PACKET[local19] = null;
        }
        for (local19 = 0; local19 < 32768; local19++) {
            NpcList.npcs[local19] = null;
        }
        for (local19 = 0; local19 < 4; local19++) {
            for (@Pc(115) int local115 = 0; local115 < 104; local115++) {
                for (@Pc(122) int local122 = 0; local122 < 104; local122++) {
                    Static159.levelObjStacks[local19][local115][local122] = null;
                }
            }
        }
        Camera.resetCameraEffects();
        Static189.anInt4443 = 0;
        Static8.resetVarBits();
        Static73.method1596(true);
    }

    @OriginalMember(owner = "runetek4.client!nm", name = "a", descriptor = "(Z)V")
    public static void tryReconnect() {
        if (idleTimeout > 0) {
            processLogout();
        } else {
            ClientScriptRunner.aClass95_4 = Static124.gameServerSocket;
            Static124.gameServerSocket = null;
            processGameStatus(40);
        }
    }

    @OriginalMember(owner = "runetek4.client!mh", name = "f", descriptor = "(B)V")
    public static void handleLoginScreenActions() {
        if (Static179.step == 0) {
            return;
        }
        try {
            if (++Static226.loops > 2000) {
                if (Static124.gameServerSocket != null) {
                    Static124.gameServerSocket.closeGracefully();
                    Static124.gameServerSocket = null;
                }
                if (Static57.errors >= 1) {
                    Static223.reply = -5;
                    Static179.step = 0;
                    return;
                }
                Static179.step = 1;
                Static226.loops = 0;
                Static57.errors++;
                if (Static271.defaultPort == Static209.port) {
                    Static209.port = Static55.alternatePort;
                } else {
                    Static209.port = Static271.defaultPort;
                }
            }
            if (Static179.step == 1) {
                Static72.aClass212_3 = GameShell.signLink.openSocket(Static60.hostname, Static209.port);
                Static179.step = 2;
            }
            @Pc(120) int local120;
            if (Static179.step == 2) {
                if (Static72.aClass212_3.status == 2) {
                    throw new IOException();
                }
                if (Static72.aClass212_3.status != 1) {
                    return;
                }
                Static124.gameServerSocket = new BufferedSocket((Socket) Static72.aClass212_3.result, GameShell.signLink);
                Static72.aClass212_3 = null;
                Static124.gameServerSocket.write(Static6.outboundBuffer.offset, Static6.outboundBuffer.data);
                if (Static11.aClass62_1 != null) {
                    Static11.aClass62_1.method3571();
                }
                if (Static147.aClass62_2 != null) {
                    Static147.aClass62_2.method3571();
                }
                local120 = Static124.gameServerSocket.read();
                if (Static11.aClass62_1 != null) {
                    Static11.aClass62_1.method3571();
                }
                if (Static147.aClass62_2 != null) {
                    Static147.aClass62_2.method3571();
                }
                if (local120 != 21) {
                    Static223.reply = local120;
                    Static179.step = 0;
                    Static124.gameServerSocket.closeGracefully();
                    Static124.gameServerSocket = null;
                    return;
                }
                Static179.step = 3;
            }
            if (Static179.step == 3) {
                if (Static124.gameServerSocket.available() < 1) {
                    return;
                }
                Static229.aClass100Array156 = new JString[Static124.gameServerSocket.read()];
                Static179.step = 4;
            }
            if (Static179.step == 4) {
                if (Static124.gameServerSocket.available() < Static229.aClass100Array156.length * 8) {
                    return;
                }
                Static57.in.offset = 0;
                Static124.gameServerSocket.read(0, Static229.aClass100Array156.length * 8, Static57.in.data);
                for (local120 = 0; local120 < Static229.aClass100Array156.length; local120++) {
                    Static229.aClass100Array156[local120] = Base37.decode37(Static57.in.g8());
                }
                Static223.reply = 21;
                Static179.step = 0;
                Static124.gameServerSocket.closeGracefully();
                Static124.gameServerSocket = null;
                return;
            }
        } catch (@Pc(238) IOException ioException) {
            if (Static124.gameServerSocket != null) {
                Static124.gameServerSocket.closeGracefully();
                Static124.gameServerSocket = null;
            }
            if (Static57.errors < 1) {
                Static57.errors++;
                if (Static271.defaultPort == Static209.port) {
                    Static209.port = Static55.alternatePort;
                } else {
                    Static209.port = Static271.defaultPort;
                }
                Static226.loops = 0;
                Static179.step = 1;
            } else {
                Static223.reply = -4;
                Static179.step = 0;
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "(II)V")
    public static void processGameStatus(@OriginalArg(0) int statusCode) {
        if (Static244.gamestate == statusCode) {
            return;
        }
        if (Static244.gamestate == 0) {
            Static163.disposeLoadingText();
        }
        if (statusCode == 40) {
            Static49.method1208();
        }
        @Pc(37) boolean local37 = statusCode == 5 || statusCode == 10 || statusCode == 28;
        if (statusCode != 40 && ClientScriptRunner.aClass95_4 != null) {
            ClientScriptRunner.aClass95_4.closeGracefully();
            ClientScriptRunner.aClass95_4 = null;
        }
        if (statusCode == 25 || statusCode == 28) {
            Static271.anInt5804 = 0;
            Static230.anInt5150 = 1;
            ClientScriptRunner.anInt5223 = 0;
            Static38.anInt1196 = 1;
            Static175.anInt4220 = 0;
            Static116.method2325(true);
        }
        if (statusCode == 25 || statusCode == 10) {
            Static123.method2418();
        }
        if (statusCode == 5) {
            Static181.method3344(Static209.aClass153_86);
        } else {
            Static119.method2381();
        }
        @Pc(106) boolean local106 = Static244.gamestate == 5 || Static244.gamestate == 10 || Static244.gamestate == 28;
        if (local106 != local37) {
            if (local37) {
                BZip2State.anInt4363 = Static250.anInt5441;
                if (Static12.anInt391 == 0) {
                    Static29.method801();
                } else {
                    Static257.method526(Static250.anInt5441, Static130.aClass153_47, 255);
                }
                client.js5NetQueue.writeLoggedIn(false);
            } else {
                Static29.method801();
                client.js5NetQueue.writeLoggedIn(true);
            }
        }
        if (GlRenderer.enabled && (statusCode == 25 || statusCode == 28 || statusCode == 40)) {
            GlRenderer.method4160();
        }
        Static244.gamestate = statusCode;
    }

    @OriginalMember(owner = "runetek4.client!re", name = "a", descriptor = "(I)V")
    public static void clearCaches() {
        Static8.method119();
        Static71.method1443();
        Static238.method4142();
        Static258.method4415();
        Static209.method3706();
        Static190.method3447();
        SeqType.clearAnimationCache();
        Static137.method2666();
        Static269.method2221();
        VarPlayerDefinition.clearVarPlayerDefinitionCache();
        BasTypeList.clean();
        Static276.method4615();
        Static25.method715();
        Static25.method716();
        Static279.method4662();
        Static53.method1289();
        Static158.method3010();
        Static134.method2621();
        Static73.aClass99_10.clear(5);
        Static139.aClass99_22.clear(5);
    }

    @OriginalMember(owner = "runetek4.client!pk", name = "i", descriptor = "(I)V")
    public static void pushProjectiles() {
        for (@Pc(16) ProjectileEntity proj = (ProjectileEntity) Static217.projectiles.head(); proj != null; proj = (ProjectileEntity) Static217.projectiles.next()) {
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
                        player = Static159.players[index];
                    }
                    if (player != null && player.xFine >= 0 && player.xFine < 13312 && player.zFine >= 0 && player.zFine < 13312) {
                        projAnim.updateVelocity(player.zFine, client.loop, SceneGraph.getTileHeight(projAnim.level, player.xFine, player.zFine) - projAnim.anInt4805, player.xFine);
                    }
                }
                projAnim.update(Static178.sceneDelta);
                Static43.addTemporary(Player.plane, (int) projAnim.x, (int) projAnim.y, (int) projAnim.z, 60, projAnim, projAnim.yaw, -1L, false);
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Z)V")
    public static void pushSpotanims() {
        for (@Pc(9) SpotAnimEntity entity = (SpotAnimEntity) Static99.spotanims.head(); entity != null; entity = (SpotAnimEntity) Static99.spotanims.next()) {
            @Pc(15) SpotAnim spotAnim = entity.aClass8_Sub2_1;
            if (spotAnim.level != Player.plane || spotAnim.seqComplete) {
                entity.unlink();
            } else if (spotAnim.startCycle <= client.loop) {
                spotAnim.update(Static178.sceneDelta);
                if (spotAnim.seqComplete) {
                    entity.unlink();
                } else {
                    Static43.addTemporary(spotAnim.level, spotAnim.x, spotAnim.z, spotAnim.anInt599, 60, spotAnim, 0, -1L, false);
                }
            }
        }
    }
}
