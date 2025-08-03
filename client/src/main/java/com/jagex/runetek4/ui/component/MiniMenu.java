package com.jagex.runetek4.ui.component;

import com.jagex.runetek4.entity.entity.PlayerList;
import com.jagex.runetek4.scene.Camera;
import com.jagex.runetek4.ui.chat.Chat;
import com.jagex.runetek4.client.LoginManager;
import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.data.cache.media.Font;
import com.jagex.runetek4.data.cache.media.SoftwareSprite;
import com.jagex.runetek4.data.cache.media.component.Component;
import com.jagex.runetek4.client.Client;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.param.ParamType;
import com.jagex.runetek4.config.types.param.ParamTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.core.datastruct.LinkedList;
import com.jagex.runetek4.entity.entity.Npc;
import com.jagex.runetek4.entity.entity.NpcList;
import com.jagex.runetek4.entity.entity.Player;
import com.jagex.runetek4.game.logic.PathFinder;
import com.jagex.runetek4.game.state.VarpDomain;
import com.jagex.runetek4.graphics.gl.GlAlphaSprite;
import com.jagex.runetek4.graphics.gl.GlRaster;
import com.jagex.runetek4.graphics.gl.GlRenderer;
import com.jagex.runetek4.ui.sprite.GlSprite;
import com.jagex.runetek4.graphics.font.Fonts;
import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.input.Mouse;
import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.util.string.LocalizedText;
import com.jagex.runetek4.graphics.model.Model;
import com.jagex.runetek4.network.ClientProt;
import com.jagex.runetek4.network.Protocol;
import com.jagex.runetek4.entity.loc.ObjStackNode;
import com.jagex.runetek4.graphics.raster.Rasterizer;
import com.jagex.runetek4.graphics.raster.SoftwareRenderer;
import com.jagex.runetek4.scene.SceneGraph;
import com.jagex.runetek4.clientscript.ClientScriptRunner;
import com.jagex.runetek4.ui.sprite.SoftwareAlphaSprite;
import com.jagex.runetek4.ui.sprite.Sprites;
import com.jagex.runetek4.ui.events.ComponentEvent;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.debug.Cheat;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MiniMenu {
    @OriginalMember(owner = "runetek4.client!se", name = "m", descriptor = "Lclient!na;")
    public static final JString aClass100_961 = JString.parse(" )2>");
    @OriginalMember(owner = "runetek4.client!pf", name = "r", descriptor = "[I")
    public static final int[] cursors = new int[500];
    @OriginalMember(owner = "runetek4.client!uj", name = "C", descriptor = "[Lclient!na;")
    public static final JString[] ops = new JString[500];
    @OriginalMember(owner = "runetek4.client!t", name = "v", descriptor = "[Lclient!na;")
    public static final JString[] opBases = new JString[500];
    @OriginalMember(owner = "runetek4.client!d", name = "eb", descriptor = "[S")
    public static final short[] actions = new short[500];
    @OriginalMember(owner = "runetek4.client!hd", name = "l", descriptor = "Lclient!na;")
    public static final JString NULL = JString.parse("null");
    @OriginalMember(owner = "client!aj", name = "R", descriptor = "Lclient!na;")
    public static final JString aClass100_32 = JString.parse("<col=ff9040>");
    @OriginalMember(owner = "runetek4.client!pl", name = "e", descriptor = "[I")
    public static final int[] intArgs1 = new int[500];
    @OriginalMember(owner = "client!df", name = "l", descriptor = "Lclient!na;")
    public static final JString GREEN = JString.parse("<col=00ff00>");
    @OriginalMember(owner = "runetek4.client!sc", name = "g", descriptor = "Lclient!na;")
    public static final JString aClass100_947 = JString.parse(" )2> <col=ff9040>");
    @OriginalMember(owner = "runetek4.client!mi", name = "U", descriptor = "[J")
    public static final long[] keys = new long[500];
    @OriginalMember(owner = "client!ef", name = "c", descriptor = "[I")
    public static final int[] intArgs2 = new int[500];
    @OriginalMember(owner = "runetek4.client!nm", name = "bb", descriptor = "Lclient!na;")
    public static final JString RED = JString.parse("<col=ff0000>");
    @OriginalMember(owner = "client!fb", name = "p", descriptor = "Lclient!na;")
    public static final JString RED2 = JString.parse("<col=ff3000>");
    @OriginalMember(owner = "runetek4.client!sc", name = "D", descriptor = "Lclient!na;")
    public static final JString ORANGE = JString.parse("<col=ff7000>");
    @OriginalMember(owner = "runetek4.client!si", name = "Z", descriptor = "Lclient!na;")
    public static final JString YELLOW = JString.parse("<col=ffb000>");
    @OriginalMember(owner = "client!ag", name = "bb", descriptor = "Lclient!na;")
    public static final JString GREEN2 = JString.parse("<col=40ff00>");
    @OriginalMember(owner = "client!dc", name = "v", descriptor = "Lclient!na;")
    public static final JString GREEN3 = JString.parse("<col=c0ff00>");
    @OriginalMember(owner = "runetek4.client!sf", name = "g", descriptor = "Lclient!na;")
    public static final JString YELLOW2 = JString.parse("<col=ffff00>");
    @OriginalMember(owner = "runetek4.client!vg", name = "f", descriptor = "Lclient!na;")
    public static final JString GREEN4 = JString.parse("<col=80ff00>");
    @OriginalMember(owner = "runetek4.client!r", name = "d", descriptor = "Z")
    public static final boolean aBoolean237 = false;
    @OriginalMember(owner = "runetek4.client!a", name = "j", descriptor = "Lclient!na;")
    public static final JString aClass100_2 = JString.parse("<col=ffffff> )4 ");
    @OriginalMember(owner = "client!cb", name = "fb", descriptor = "Lclient!na;")
    public static final JString COLON_SEPARATOR = JString.parse(": ");
    @OriginalMember(owner = "runetek4.client!qf", name = "Q", descriptor = "Lclient!na;")
	public static final JString aClass100_407 = JString.parse(" )2> <col=ffff00>");
    @OriginalMember(owner = "runetek4.client!qf", name = "R", descriptor = "Lclient!na;")
    public static final JString aClass100_408 = JString.parse(" )2> ");
    @OriginalMember(owner = "client!fl", name = "V", descriptor = "Lclient!na;")
    public static final JString CLOSE_PARENTHESIS = JString.parse("(Y");
    @OriginalMember(owner = "client!gd", name = "c", descriptor = "Lclient!na;")
    public static final JString PLUS = JString.parse(")0");
    @OriginalMember(owner = "runetek4.client!jj", name = "g", descriptor = "Lclient!na;")
    public static final JString OPEN_PARENTHESIS = JString.parse(" (X");
    @OriginalMember(owner = "runetek4.client!qi", name = "B", descriptor = "Lclient!na;")
	public static final JString WHITE = JString.parse("<col=ffffff>");
    @OriginalMember(owner = "client!cb", name = "ab", descriptor = "Lclient!na;")
    public static final JString aClass100_164 = JString.parse(" )2> <col=00ffff>");
    @OriginalMember(owner = "runetek4.client!ud", name = "Q", descriptor = "Lclient!na;")
    public static final JString aClass100_1039 = JString.parse(" x ");
    @OriginalMember(owner = "runetek4.client!ib", name = "k", descriptor = "Lclient!na;")
    public static final JString aClass100_561 = JString.parse(" )2> <col=ffffff>");
    @OriginalMember(owner = "runetek4.client!tg", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_1008 = JString.parse("<col=00ffff>");
    @OriginalMember(owner = "runetek4.client!uf", name = "q", descriptor = "Lclient!na;")
    public static final JString aClass100_1042 = JString.parse("Null");
    @OriginalMember(owner = "client!e", name = "pc", descriptor = "[I")
    public static final int[] textBounds = new int[4];
    @OriginalMember(owner = "runetek4.client!af", name = "l", descriptor = "[S")
    public static final short[] PLAYER_ACTION_IDS = new short[] { 30, 6, 31, 29, 10, 44, 37, 57 };
    @OriginalMember(owner = "runetek4.client!sk", name = "kb", descriptor = "I")
    public static int menuActionRow = 0;
    @OriginalMember(owner = "runetek4.client!vd", name = "C", descriptor = "I")
    public static int anInt5014 = 0;
    @OriginalMember(owner = "runetek4.client!th", name = "n", descriptor = "Z")
    public static boolean useWithActive = false;
    @OriginalMember(owner = "runetek4.client!pk", name = "bb", descriptor = "Lclient!na;")
    public static JString walkText;
    @OriginalMember(owner = "runetek4.client!em", name = "D", descriptor = "I")
    public static int gregorianDateSeed;
    @OriginalMember(owner = "runetek4.client!hj", name = "e", descriptor = "I")
    public static int useWithComponentId;
    @OriginalMember(owner = "runetek4.client!u", name = "i", descriptor = "I")
    public static int useWithCursor;
    @OriginalMember(owner = "client!be", name = "Ec", descriptor = "I")
    public static int useWithSlot = -1;
    @OriginalMember(owner = "runetek4.client!hn", name = "W", descriptor = "Lclient!na;")
    public static JString aClass100_545 = null;
    @OriginalMember(owner = "runetek4.client!p", name = "e", descriptor = "I")
    public static int anInt4370;
    @OriginalMember(owner = "runetek4.client!uf", name = "t", descriptor = "I")
    public static int anInt5444 = 0;
    @OriginalMember(owner = "runetek4.client!v", name = "b", descriptor = "Lclient!be;")
    public static Component pressedInventoryComponent;
    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "I")
    public static int pickedEntityCount = 0;
    @OriginalMember(owner = "runetek4.client!id", name = "k", descriptor = "I")
    public static int anInt2878;
    @OriginalMember(owner = "runetek4.client!cl", name = "Y", descriptor = "I")
    public static int defaultCursor = -1;
    @OriginalMember(owner = "runetek4.client!jl", name = "v", descriptor = "I")
    public static int anInt3096 = 0;
    @OriginalMember(owner = "client!ck", name = "D", descriptor = "Lclient!na;")
    public static JString aClass100_203 = null;
    @OriginalMember(owner = "client!gd", name = "i", descriptor = "Lclient!na;")
    public static JString aClass100_466 = null;
    @OriginalMember(owner = "runetek4.client!wf", name = "f", descriptor = "I")
    public static int useWithMask;
    @OriginalMember(owner = "runetek4.client!wf", name = "d", descriptor = "I")
    public static int anInt4997;
    @OriginalMember(owner = "client!fl", name = "P", descriptor = "I")
    public static int anInt2043 = 0;
    @OriginalMember(owner = "runetek4.client!ml", name = "Q", descriptor = "I")
    public static int menuState = 0;
    @OriginalMember(owner = "client!bh", name = "t", descriptor = "I")
    public static int mouseInvInterfaceIndex = 0;

    /***********************
     *   Action Constants  *
     ***********************/
    /* Tiles */
    public static final int WALK_HERE = 60;
    /* NPCs */
    public static final int NPC_ACTION_1 = 17;
    public static final int NPC_ACTION_2 = 16;
    public static final int NPC_ACTION_3 = 4;
    public static final int NPC_ACTION_4 = 19;
    public static final int NPC_ACTION_5 = 2;
    public static final int NPC_EXAMINE = 1007;
    /* Players */
    public static final int PLAYER_ACTION_1 = 30;
    public static final int PLAYER_ACTION_BLOCK = 34;
    public static final int PLAYER_ACTION_TRADE = 29;
    public static final int PLAYER_REQ_ASSIST_ACTION = 37;
    public static final int PLAYER_FOLLOW_ACTION = 31;
    public static final int PLAYER_ACTION_5 = 57;
    /* Objects */
    public static final int OBJ_ACTION_1 = 47;
    public static final int OBJ_EQUIP_ACTION = 5;
    public static final int OBJ_ACTION_4 = 35;
    public static final int OBJ_OPERATE_ACTION = 23;
    public static final int OBJ_ACTION_5 = 58;
    public static final int OBJ_EXAMINE = 1002;
    public static final int OBJ_PLAYER_ACTION = 1;
    public static final int OBJ_OBJSTACK_ACTION = 33;
    public static final int OBJ_NPC_ACTION = 26;
    public static final int OBJ_LOC_ACTION = 14;
    public static final int OBJ_OBJ_ACTION = 40;
    /* Object Stacks */
    public static final int OBJSTACK_ACTION_1 = 18;
    public static final int OBJSTACK_ACTION_2 = 20;
    /* Locations */
    public static final int LOC_ACTION_1 = 42;
    public static final int LOC_ACTION_2 = 50;
    public static final int LOC_ACTION_3 = 49;
    public static final int LOC_ACTION_4 = 46;
    public static final int LOC_ACTION_5 = 1001;
    public static final int LOC_ACTION_EXAMINE = 1004;
    /* Components */
    public static final int COMPONENT_ACTION_CLOSE = 28;
    public static final int COMPONENT_OBJSTACK_ACTION = 39;
    public static final int OBJ_IN_COMPONENT_ACTION_4 = 43;
    public static final int COMPONENT_LOC_ACTION = 38;
    public static final int LOGOUT_ACTION_2 = 59;
    public static final int LOGOUT_ACTION = 51;
    public static final int OBJ_IN_COMPONENT_ACTION_1 = 25;
    public static final int COMPONENT_PLAYER_ACTION = 15;
    public static final int COMPONENT_OBJ_ACTION = 3;
    public static final int COMPONENT_NPC_ACTION = 45;
    public static final int OBJ_EXAMINE_IN_COMPONENT = 1006;
    /* Unknown/Unidentified */
    public static final int UNKNOWN_13 = 13;
    public static final int UNKNOWN_22 = 22;
    public static final int UNKNOWN_48 = 48;
    public static final int UNKNOWN_12 = 12;
    public static final int UNKNOWN_36 = 36;
    public static final int UNKNOWN_6 = 6;
    public static final int UNKNOWN_24 = 24;
    public static final int UNKNOWN_7 = 7;
    public static final int UNKNOWN_8 = 8;
    public static final int UNKNOWN_11 = 11;
    public static final int UNKNOWN_32 = 32;
    public static final int UNKNOWN_21 = 21;
    public static final int UNKNOWN_9 = 9;
    public static final int UNKNOWN_1003 = 1003;
    public static final int UNKNOWN_41 = 41;
    public static final int UNKNOWN_10 = 10;
    public static final int UNKNOWN_44 = 44;
    @OriginalMember(owner = "client!ef", name = "g", descriptor = "I")
    public static int clickTileX = -1;
    @OriginalMember(owner = "runetek4.client!ha", name = "q", descriptor = "I")
    public static int targetX = 0;
    @OriginalMember(owner = "runetek4.client!jb", name = "p", descriptor = "I")
    public static int clickTileZ = -1;
    @OriginalMember(owner = "runetek4.client!jg", name = "b", descriptor = "I")
    public static int useWithParam;
    @OriginalMember(owner = "runetek4.client!kd", name = "zb", descriptor = "I")
    public static int targetZ = 0;
    @OriginalMember(owner = "runetek4.client!mh", name = "Y", descriptor = "Z")
    public static boolean walkTargetActive = false;
    @OriginalMember(owner = "runetek4.client!mj", name = "i", descriptor = "I")
    public static int targetPlane = 0;

    @OriginalMember(owner = "runetek4.client!ud", name = "a", descriptor = "(ILclient!be;)Z")
    public static boolean shouldTriggerIdleTimeout(@OriginalArg(1) Component component) {
        if (component.contentType == 205) {
            Protocol.idleTimeout = 250;
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!ec", name = "a", descriptor = "(B)V")
    public static void handleUseWith() {
        if (!useWithActive) {
            return;
        }
        @Pc(19) Component component = ComponentList.getCreatedComponent(useWithComponentId, useWithSlot);
        if (component != null && component.onUseWith != null) {
            @Pc(29) ComponentEvent local29 = new ComponentEvent();
            local29.arguments = component.onUseWith;
            local29.source = component;
            ClientScriptRunner.run(local29);
        }
        useWithActive = false;
        defaultCursor = -1;
        ComponentList.redraw(component);
    }

    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
    public static void addActionRow(@OriginalArg(0) int cursor, @OriginalArg(1) long key, @OriginalArg(3) JString opBase, @OriginalArg(4) int param1, @OriginalArg(5) short action, @OriginalArg(6) JString op, @OriginalArg(7) int param2) {
        if (ClientScriptRunner.menuVisible || menuActionRow >= 500) {
            return;
        }
        ops[menuActionRow] = op;
        opBases[menuActionRow] = opBase;
        cursors[menuActionRow] = cursor == -1 ? defaultCursor : cursor;
        actions[menuActionRow] = action;
        keys[menuActionRow] = key;
        intArgs1[menuActionRow] = param1;
        intArgs2[menuActionRow] = param2;
        menuActionRow++;
    }

    @OriginalMember(owner = "runetek4.client!va", name = "a", descriptor = "(IZILclient!be;)V")
    public static void addComponentEntries(@OriginalArg(0) int mouseY, @OriginalArg(2) int mouseX, @OriginalArg(3) Component component) {
        if (component.buttonType == 1) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 8, component.option, component.id);
        }
        @Pc(47) JString ops;
        if (component.buttonType == 2 && !useWithActive) {
            ops = MiniMap.getTargetVerb(component);
            if (ops != null) {
                addActionRow(-1, 0L, JString.concatenate(new JString[] { GREEN, component.optionSuffix}), -1, (short) 32, ops, component.id);
            }
        }
        if (component.buttonType == 3) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 28, LocalizedText.CLOSE, component.id);
        }
        if (component.buttonType == 4) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 59, component.option, component.id);
        }
        if (component.buttonType == 5) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 51, component.option, component.id);
        }
        if (component.buttonType == 6 && ClientScriptRunner.modalBackgroundComponent == null) {
            addActionRow(-1, 0L, JString.EMPTY, -1, (short) 41, component.option, component.id);
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
                        ComponentList.mouseOverInventoryComponent = component;
                        mouseInvInterfaceIndex = slotIndex;
                        if (component.invSlotObjId[slotIndex] > 0) {
                            @Pc(267) ServerActiveProperties serverProps = ComponentList.getServerActiveProperties(component);
                            @Pc(276) ObjType objType = ObjTypeList.get(component.invSlotObjId[slotIndex] - 1);
                            if (anInt5014 == 1 && serverProps.isObjOpsEnabled()) {
                                if (MiniMap.anInt5062 != component.id || anInt4370 != slotIndex) {
                                    addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] { aClass100_203, aClass100_947, objType.name}), slotIndex, (short) 40, LocalizedText.USE, component.id);
                                }
                            } else if (useWithActive && serverProps.isObjOpsEnabled()) {
                                @Pc(596) ParamType local596 = useWithParam == -1 ? null : ParamTypeList.get(useWithParam);
                                if ((useWithMask & 0x10) != 0 && (local596 == null || objType.getParam(local596.defaultInt, useWithParam) != local596.defaultInt)) {
                                    addActionRow(useWithCursor, (long) objType.id, JString.concatenate(new JString[] { aClass100_466, aClass100_947, objType.name}), slotIndex, (short) 3, aClass100_545, component.id);
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
                                            addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] { aClass100_32, objType.name}), slotIndex, actionId, objOps[opIndex], component.id);
                                        }
                                    }
                                }
                                if (serverProps.isObjUseEnabled()) {
                                    addActionRow(MiniMap.anInt4075, (long) objType.id, JString.concatenate(new JString[] { aClass100_32, objType.name}), slotIndex, (short) 22, LocalizedText.USE, component.id);
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
                                            addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] { aClass100_32, objType.name}), slotIndex, actionId, objOps[opIndex], component.id);
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
                                            addActionRow(-1, (long) objType.id, JString.concatenate(new JString[] { aClass100_32, objType.name}), slotIndex, actionId, objOps[opIndex], component.id);
                                        }
                                    }
                                }
                                addActionRow(MiniMap.anInt5073, (long) objType.id, JString.concatenate(new JString[] { aClass100_32, objType.name}), slotIndex, (short) 1006, LocalizedText.EXAMINE, component.id);
                            }
                        }
                    }
                    slotIndex++;
                }
            }
        }
        if (!component.if3) {
            return;
        }
        if (!useWithActive) {
            for (slotIndex = 9; slotIndex >= 5; slotIndex--) {
                @Pc(765) JString local765 = ComponentList.getOp(component, slotIndex);
                if (local765 != null) {
                    addActionRow(getOpCursor(slotIndex, component), (long) (slotIndex + 1), component.optionBase, component.createdComponentId, (short) 1003, local765, component.id);
                }
            }
            ops = MiniMap.getTargetVerb(component);
            if (ops != null) {
                addActionRow(-1, 0L, component.optionBase, component.createdComponentId, (short) 32, ops, component.id);
            }
            for (row = 4; row >= 0; row--) {
                @Pc(828) JString local828 = ComponentList.getOp(component, row);
                if (local828 != null) {
                    addActionRow(getOpCursor(row, component), (long) (row + 1), component.optionBase, component.createdComponentId, (short) 9, local828, component.id);
                }
            }
            if (ComponentList.getServerActiveProperties(component).isResumePauseButtonEnabled()) {
                addActionRow(-1, 0L, JString.EMPTY, component.createdComponentId, (short) 41, LocalizedText.CONTINUE, component.id);
            }
        } else if (ComponentList.getServerActiveProperties(component).isUseTarget() && (useWithMask & 0x20) != 0) {
            addActionRow(useWithCursor, 0L, JString.concatenate(new JString[] { aClass100_466, aClass100_408, component.optionBase}), component.createdComponentId, (short) 12, aClass100_545, component.id);
        }
    }

    @OriginalMember(owner = "runetek4.client!qe", name = "b", descriptor = "(II)V")
    public static void removeActionRow(@OriginalArg(1) int index) {
        menuActionRow--;
        if (menuActionRow == index) {
            return;
        }
        ArrayUtils.copy(ops, index + 1, ops, index, menuActionRow - index);
        ArrayUtils.copy(opBases, index + 1, opBases, index, menuActionRow - index);
        ArrayUtils.copy(cursors, index + 1, cursors, index, menuActionRow - index);
        ArrayUtils.copy(actions, index + 1, actions, index, menuActionRow - index);
        ArrayUtils.copy(keys, index + 1, keys, index, menuActionRow - index);
        ArrayUtils.copy(intArgs1, index + 1, intArgs1, index, menuActionRow - index);
        ArrayUtils.copy(intArgs2, index + 1, intArgs2, index, menuActionRow - index);
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "b", descriptor = "(I)V")
    public static void sortMenuActions() {
        @Pc(3) boolean hasSwapped = false;
        while (!hasSwapped) {
            hasSwapped = true;
            for (@Pc(13) int index = 0; index < menuActionRow - 1; index++) {
                if (actions[index] < 1000 && actions[index + 1] > 1000) {
                    @Pc(41) JString tempOpBase = opBases[index];
                    hasSwapped = false;
                    opBases[index] = opBases[index + 1];
                    opBases[index + 1] = tempOpBase;
                    @Pc(61) JString tempOp = ops[index];
                    ops[index] = ops[index + 1];
                    ops[index + 1] = tempOp;
                    @Pc(79) int tempArg1 = intArgs1[index];
                    intArgs1[index] = intArgs1[index + 1];
                    intArgs1[index + 1] = tempArg1;
                    @Pc(97) int tempArg2 = intArgs2[index];
                    intArgs2[index] = intArgs2[index + 1];
                    intArgs2[index + 1] = tempArg2;
                    @Pc(115) int tempCursor = cursors[index];
                    cursors[index] = cursors[index + 1];
                    cursors[index + 1] = tempCursor;
                    @Pc(133) short tempAction = actions[index];
                    actions[index] = actions[index + 1];
                    actions[index + 1] = tempAction;
                    @Pc(151) long tempKey = keys[index];
                    keys[index] = keys[index + 1];
                    keys[index + 1] = tempKey;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ij", name = "a", descriptor = "(B)V")
    public static void drawContextMenu() {
        @Pc(3) int menuX = ComponentList.menuX;
        @Pc(9) int menuY = ComponentList.menuY;
        @Pc(11) int menuHeight = ComponentList.menuHeight;
        @Pc(13) int menuWidth = ComponentList.menuWidth;
        if (LoginManager.loginSprite2 == null || LoginManager.loginSprite5 == null) {
            if (Client.js5Archive8.isFileReady(LoginManager.anInt1736) && Client.js5Archive8.isFileReady(LoginManager.anInt4073)) {
                LoginManager.loginSprite2 = SoftwareSprite.loadSoftwareAlphaSprite(Client.js5Archive8, LoginManager.anInt1736);
                LoginManager.loginSprite5 = SoftwareSprite.loadSoftwareAlphaSprite(Client.js5Archive8, LoginManager.anInt4073);
                if (GlRenderer.enabled) {
                    if (LoginManager.loginSprite2 instanceof SoftwareAlphaSprite) {
                        LoginManager.loginSprite2 = new GlAlphaSprite((SoftwareSprite) LoginManager.loginSprite2);
                    } else {
                        LoginManager.loginSprite2 = new GlSprite((SoftwareSprite) LoginManager.loginSprite2);
                    }
                    if (LoginManager.loginSprite5 instanceof SoftwareAlphaSprite) {
                        LoginManager.loginSprite5 = new GlAlphaSprite((SoftwareSprite) LoginManager.loginSprite5);
                    } else {
                        LoginManager.loginSprite5 = new GlSprite((SoftwareSprite) LoginManager.loginSprite5);
                    }
                }
            } else if (GlRenderer.enabled) {
                GlRaster.fillRectAlpha(menuX, menuY, menuWidth, 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
            } else {
                SoftwareRenderer.fillRectAlpha(menuX, menuY, menuWidth, 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
            }
        }
        @Pc(112) int mouseX;
        @Pc(114) int mouseY;
        if (LoginManager.loginSprite2 != null && LoginManager.loginSprite5 != null) {
            mouseX = menuWidth / LoginManager.loginSprite2.width;
            for (mouseY = 0; mouseY < mouseX; mouseY++) {
                LoginManager.loginSprite2.render(mouseY * LoginManager.loginSprite2.width + menuX, menuY);
            }
            LoginManager.loginSprite5.render(menuX, menuY);
            LoginManager.loginSprite5.renderHorizontalFlip(menuX + menuWidth - LoginManager.loginSprite5.width, menuY);
        }
        Fonts.b12Full.renderLeft(LocalizedText.CHOOSE_OPTION, menuX + 3, menuY + 14, LoginManager.anInt4581, -1);
        if (GlRenderer.enabled) {
            GlRaster.fillRectAlpha(menuX, menuY + 20, menuWidth, menuHeight - 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
        } else {
            SoftwareRenderer.fillRectAlpha(menuX, menuY + 20, menuWidth, menuHeight - 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
        }
        mouseY = Mouse.lastMouseY;
        mouseX = Mouse.lastMouseX;
        @Pc(203) int rowIndex;
        @Pc(219) int rowY;
        for (rowIndex = 0; rowIndex < menuActionRow; rowIndex++) {
            rowY = (menuActionRow - rowIndex - 1) * 15 + menuY + 35;
            if (menuX < mouseX && mouseX < menuX + menuWidth && mouseY > rowY - 13 && mouseY < rowY + 3) {
                if (GlRenderer.enabled) {
                    GlRaster.fillRectAlpha(menuX, rowY - 13, menuWidth, 16, LoginManager.anInt5457, 256 - LoginManager.anInt5208);
                } else {
                    SoftwareRenderer.fillRectAlpha(menuX, rowY - 13, menuWidth, 16, LoginManager.anInt5457, 256 - LoginManager.anInt5208);
                }
            }
        }
        if ((LoginManager.loginSprite4 == null || LoginManager.loginSprite3 == null || LoginManager.loginSprite1 == null) && Client.js5Archive8.isFileReady(LoginManager.anInt2261) && Client.js5Archive8.isFileReady(LoginManager.anInt3324) && Client.js5Archive8.isFileReady(LoginManager.anInt5556)) {
            LoginManager.loginSprite4 = SoftwareSprite.loadSoftwareAlphaSprite(Client.js5Archive8, LoginManager.anInt2261);
            LoginManager.loginSprite3 = SoftwareSprite.loadSoftwareAlphaSprite(Client.js5Archive8, LoginManager.anInt3324);
            LoginManager.loginSprite1 = SoftwareSprite.loadSoftwareAlphaSprite(Client.js5Archive8, LoginManager.anInt5556);
            if (GlRenderer.enabled) {
                if (LoginManager.loginSprite4 instanceof SoftwareAlphaSprite) {
                    LoginManager.loginSprite4 = new GlAlphaSprite((SoftwareSprite) LoginManager.loginSprite4);
                } else {
                    LoginManager.loginSprite4 = new GlSprite((SoftwareSprite) LoginManager.loginSprite4);
                }
                if (LoginManager.loginSprite3 instanceof SoftwareAlphaSprite) {
                    LoginManager.loginSprite3 = new GlAlphaSprite((SoftwareSprite) LoginManager.loginSprite3);
                } else {
                    LoginManager.loginSprite3 = new GlSprite((SoftwareSprite) LoginManager.loginSprite3);
                }
                if (LoginManager.loginSprite1 instanceof SoftwareAlphaSprite) {
                    LoginManager.loginSprite1 = new GlAlphaSprite((SoftwareSprite) LoginManager.loginSprite1);
                } else {
                    LoginManager.loginSprite1 = new GlSprite((SoftwareSprite) LoginManager.loginSprite1);
                }
            }
        }
        @Pc(418) int textColor;
        if (LoginManager.loginSprite4 != null && LoginManager.loginSprite3 != null && LoginManager.loginSprite1 != null) {
            rowIndex = menuWidth / LoginManager.loginSprite4.width;
            for (rowY = 0; rowY < rowIndex; rowY++) {
                LoginManager.loginSprite4.render(menuX + LoginManager.loginSprite4.width * rowY, menuHeight + menuY + -LoginManager.loginSprite4.height);
            }
            rowY = (menuHeight - 20) / LoginManager.loginSprite3.height;
            for (textColor = 0; textColor < rowY; textColor++) {
                LoginManager.loginSprite3.render(menuX, menuY + textColor * LoginManager.loginSprite3.height + 20);
                LoginManager.loginSprite3.renderHorizontalFlip(menuX + menuWidth - LoginManager.loginSprite3.width, menuY + 20 + textColor * LoginManager.loginSprite3.height);
            }
            LoginManager.loginSprite1.render(menuX, menuHeight + menuY - LoginManager.loginSprite1.height);
            LoginManager.loginSprite1.renderHorizontalFlip(menuX + menuWidth - LoginManager.loginSprite1.width, menuY - -menuHeight + -LoginManager.loginSprite1.height);
        }
        for (rowIndex = 0; rowIndex < menuActionRow; rowIndex++) {
            rowY = (menuActionRow - rowIndex - 1) * 15 + menuY + 35;
            textColor = LoginManager.anInt4581;
            if (menuX < mouseX && menuWidth + menuX > mouseX && rowY - 13 < mouseY && mouseY < rowY + 3) {
                textColor = LoginManager.anInt5752;
            }
            Fonts.b12Full.renderLeft(getOp(rowIndex), menuX + 3, rowY, textColor, 0);
        }
        ComponentList.forceRedrawScreen(ComponentList.menuX, ComponentList.menuY, ComponentList.menuHeight, ComponentList.menuWidth);
    }

    @OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "(I)V")
    public static void drawSimpleMenu() {
        @Pc(3) int menuY = ComponentList.menuY;
        @Pc(9) int menuWidth = ComponentList.menuWidth;
        @Pc(11) int menuX = ComponentList.menuX;
        @Pc(15) int menuHeight = ComponentList.menuHeight;
        if (GlRenderer.enabled) {
            GlRaster.fillRect(menuX, menuY, menuWidth, menuHeight, 6116423);
            GlRaster.fillRect(menuX + 1, menuY + 1, menuWidth - 2, 16, 0);
            GlRaster.drawRect(menuX + 1, menuY + 18, menuWidth - 2, menuHeight + -19, 0);
        } else {
            SoftwareRenderer.fillRect(menuX, menuY, menuWidth, menuHeight, 6116423);
            SoftwareRenderer.fillRect(menuX + 1, menuY + 1, menuWidth - 2, 16, 0);
            SoftwareRenderer.drawRect(menuX + 1, menuY + 18, menuWidth - 2, menuHeight + -19, 0);
        }
        Fonts.b12Full.renderLeft(LocalizedText.CHOOSE_OPTION, menuX + 3, menuY + 14, 6116423, -1);
        @Pc(96) int mouseY = Mouse.lastMouseY;
        @Pc(98) int mouseX = Mouse.lastMouseX;
        for (@Pc(107) int rowIndex = 0; rowIndex < menuActionRow; rowIndex++) {
            @Pc(127) int rowY = (menuActionRow - rowIndex - 1) * 15 + menuY + 31;
            @Pc(129) int textColor = 16777215; //WHITE
            if (menuX < mouseX && mouseX < menuX + menuWidth && rowY - 13 < mouseY && mouseY < rowY + 3) {
                textColor = 16776960; //YELLOW
            }
            Fonts.b12Full.renderLeft(getOp(rowIndex), menuX + 3, rowY, textColor, 0);
        }
        ComponentList.forceRedrawScreen(ComponentList.menuX, ComponentList.menuY, ComponentList.menuHeight, ComponentList.menuWidth);
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(Lclient!be;III)V")
    public static void drawMenuText(@OriginalArg(0) Component component, @OriginalArg(2) int y, @OriginalArg(3) int x) {
        if (menuActionRow < 2 && anInt5014 == 0 && !useWithActive) {
            return;
        }
        @Pc(24) JString actionText = getActionText();
        if (component == null) {
            @Pc(40) int textX = Fonts.b12Full.method2859(actionText, x + 4, y - -15, Client.aRandom1, gregorianDateSeed);
            ComponentList.redrawScreen(x + 4, Fonts.b12Full.getStringWidth(actionText) + textX, y, 15);
            return;
        }
        @Pc(59) Font font = component.getFont(Sprites.nameIcons);
        if (font == null) {
            font = Fonts.b12Full;
        }
        font.method2878(actionText, x, y, component.width, component.height, component.color, component.shadowColor, component.halign, component.valign, Client.aRandom1, gregorianDateSeed, textBounds);
        ComponentList.redrawScreen(textBounds[0], textBounds[2], textBounds[1], textBounds[3]);
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
        if (!ComponentList.getServerActiveProperties(component).isButtonEnabled(opIndex) && component.onOptionClick == null) {
            return -1;
        } else if (component.opCursors == null || opIndex >= component.opCursors.length) {
            return -1;
        } else {
            return component.opCursors[opIndex];
        }
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
    public static JString getOp(@OriginalArg(0) int index) {
        return opBases[index].length() > 0 ? JString.concatenate(new JString[] { ops[index], LocalizedText.MINISEPARATOR, opBases[index] }) : ops[index];
    }

    @OriginalMember(owner = "runetek4.client!i", name = "p", descriptor = "(II)V")
    public static void doAction(@OriginalArg(1) int menuIndex) {
        if (menuIndex < 0) {
            return;
        }
        @Pc(15) int param1 = intArgs1[menuIndex];
        @Pc(19) int param2 = intArgs2[menuIndex];
        @Pc(23) int actionCode = actions[menuIndex];
        if (actionCode >= 2000) {
            actionCode -= 2000;
        }
        @Pc(31) long key = keys[menuIndex];
        @Pc(36) int keyInt = (int) keys[menuIndex];
        @Pc(43) Player player;
        if (actionCode == PLAYER_FOLLOW_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(71);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == LOC_ACTION_4) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(247);
            Protocol.outboundBuffer.p2_alt1(Camera.originZ + param2);
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.originX);
            Protocol.outboundBuffer.p2(Integer.MAX_VALUE & (int) (key >>> 32));
        }
        if (actionCode == OBJ_OBJ_ACTION) {
            Protocol.outboundBuffer.pIsaac1(27);
            Protocol.outboundBuffer.p2(anInt4370);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p4_alt1(MiniMap.anInt5062);
            Protocol.outboundBuffer.p2_alt3(anInt4997);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        @Pc(192) Npc npc;
        if (actionCode == NPC_ACTION_4) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(30);
                Protocol.outboundBuffer.p2(keyInt);
            }
        }
        if (actionCode == NPC_ACTION_1) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(78);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        if (actionCode == UNKNOWN_44) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(133);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        if (actionCode == OBJ_ACTION_5) {
            Protocol.outboundBuffer.pIsaac1(135);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p4_alt3(param2);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == LOC_ACTION_1) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(254);
            Protocol.outboundBuffer.p2_alt1(param1 + Camera.originX);
            Protocol.outboundBuffer.p2_alt2((int) (key >>> 32) & Integer.MAX_VALUE);
            Protocol.outboundBuffer.p2(param2 + Camera.originZ);
        }
        if (actionCode == COMPONENT_ACTION_CLOSE) {
            ComponentList.closeModal();
        }
        if (actionCode == COMPONENT_NPC_ACTION) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(239);
                Protocol.outboundBuffer.p4_alt1(useWithComponentId);
                Protocol.outboundBuffer.p2_alt2(useWithSlot);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        @Pc(560) boolean pathFound;
        if (actionCode == OBJSTACK_ACTION_1) {
            if (Client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            } else {
                pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                if (!pathFound) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                }
            }
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairCycle = 0;
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.mouseClickY;
            Protocol.outboundBuffer.pIsaac1(66);
            Protocol.outboundBuffer.p2_alt1(Camera.originX + param1);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.originZ);
        }
        if (actionCode == LOC_ACTION_5) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(170);
            Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (key >>> 32));
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.originX);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.originZ);
        }
        if (actionCode == OBJ_EXAMINE) {
            Crosshair.CrosshairMode = 2;
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(92);
            Protocol.outboundBuffer.p2_alt3(keyInt);
        }
        @Pc(693) Component component;
        if (actionCode == OBJ_EXAMINE_IN_COMPONENT) {
            component = ComponentList.getComponent(param2);
            if (component == null || component.invSlotObjCount[param1] < 100000) {
                Protocol.outboundBuffer.pIsaac1(92);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            } else {
                Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { JString.parseInt(component.invSlotObjCount[param1]), aClass100_1039, ObjTypeList.get(keyInt).name}));
            }
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == WALK_HERE) {
            if (keyInt == 0) {
                setWalkTarget(Player.plane, param1, param2);
            } else if (keyInt == 1) {
                if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                    Cheat.teleport(Camera.originX + param1, Camera.originZ + param2, Player.plane);
                } else if (PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, param1, 0, 0, 1, param2, PlayerList.self.movementQueueX[0])) {
                    Protocol.outboundBuffer.p1(ComponentList.anInt5);
                    Protocol.outboundBuffer.p1(anInt2878);
                    Protocol.outboundBuffer.p2(Camera.orbitCameraYaw);
                    Protocol.outboundBuffer.p1(57);
                    Protocol.outboundBuffer.p1(MiniMap.minimapAnticheatAngle);
                    Protocol.outboundBuffer.p1(MiniMap.minimapZoom);
                    Protocol.outboundBuffer.p1(89);
                    Protocol.outboundBuffer.p2(PlayerList.self.xFine);
                    Protocol.outboundBuffer.p2(PlayerList.self.zFine);
                    Protocol.outboundBuffer.p1(PathFinder.tryMoveNearest);
                    Protocol.outboundBuffer.p1(63);
                }
            }
        }
        if (actionCode == NPC_EXAMINE) {
            Crosshair.CrosshairCycle = 0;
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.x = Mouse.mouseClickX;
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                @Pc(884) NpcType local884 = npc.type;
                if (local884.multinpc != null) {
                    local884 = local884.getMultiNPC();
                }
                if (local884 != null) {
                    Protocol.outboundBuffer.pIsaac1(72);
                    Protocol.outboundBuffer.p2(local884.id);
                }
            }
        }
        if (actionCode == OBJ_ACTION_1) {
            Protocol.outboundBuffer.pIsaac1(156);
            Protocol.outboundBuffer.p2_alt3(param1);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p4_alt1(param2);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == COMPONENT_OBJ_ACTION) {
            Protocol.outboundBuffer.pIsaac1(253);
            Protocol.outboundBuffer.p4_alt1(useWithComponentId);
            Protocol.outboundBuffer.p2_alt3(param1);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p2_alt1(useWithSlot);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == UNKNOWN_10) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(4);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        if (actionCode == UNKNOWN_41 && ClientScriptRunner.modalBackgroundComponent == null) {
            sendComponentContinue(param1, param2);
            ClientScriptRunner.modalBackgroundComponent = ComponentList.getCreatedComponent(param2, param1);
            ComponentList.redraw(ClientScriptRunner.modalBackgroundComponent);
        }
        if (actionCode == LOC_ACTION_3) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(84);
            Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (key >>> 32));
            Protocol.outboundBuffer.p2_alt3(Camera.originZ + param2);
            Protocol.outboundBuffer.p2_alt1(param1 + Camera.originX);
        }
        if (actionCode == OBJ_OPERATE_ACTION) {
            Protocol.outboundBuffer.pIsaac1(206);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p4_alt1(param2);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == OBJ_LOC_ACTION && PathFinder.findPathToLoc(key, param2, param1)) {
            Protocol.outboundBuffer.pIsaac1(134);
            Protocol.outboundBuffer.p2_alt2(Camera.originX + param1);
            Protocol.outboundBuffer.p2(anInt4997);
            Protocol.outboundBuffer.p2_alt1(param2 + Camera.originZ);
            Protocol.outboundBuffer.p2(anInt4370);
            Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
            Protocol.outboundBuffer.p2_alt2((int) (key >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == PLAYER_REQ_ASSIST_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(114);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == UNKNOWN_9 || actionCode == UNKNOWN_1003) {
            ClientProt.method4512(opBases[menuIndex], param1, keyInt, param2);
        }
        if (actionCode == OBJ_EQUIP_ACTION) {
            Protocol.outboundBuffer.pIsaac1(55);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p4rme(param2);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == UNKNOWN_21) {
            if (Client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            } else {
                pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                if (!pathFound) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                }
            }
            Crosshair.CrosshairMode = 2;
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairCycle = 0;
            Crosshair.y = Mouse.mouseClickY;
            Protocol.outboundBuffer.pIsaac1(228);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p2_alt1(Camera.originX + param1);
            Protocol.outboundBuffer.p2_alt3(Camera.originZ + param2);
        }
        if (actionCode == NPC_ACTION_3) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(148);
                Protocol.outboundBuffer.p2_alt2(keyInt);
            }
        }
        if (actionCode == UNKNOWN_32) {
            component = ComponentList.getCreatedComponent(param2, param1);
            if (component != null) {
                handleUseWith();
                @Pc(1493) ServerActiveProperties local1493 = ComponentList.getServerActiveProperties(component);
                startUseWith(param2, param1, local1493.getTargetMask(), local1493.targetParam, component.anInt499, component.anInt484);
                anInt5014 = 0;
                aClass100_545 = MiniMap.getTargetVerb(component);
                if (aClass100_545 == null) {
                    aClass100_545 = aClass100_1042;
                }
                if (component.if3) {
                    aClass100_466 = JString.concatenate(new JString[] { component.optionBase, WHITE});
                } else {
                    aClass100_466 = JString.concatenate(new JString[] {GREEN, component.optionSuffix, WHITE});
                }
            }
            return;
        }
        if (actionCode == PLAYER_ACTION_TRADE) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(180);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == OBJ_ACTION_4) {
            Protocol.outboundBuffer.pIsaac1(161);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            Protocol.outboundBuffer.p2_alt3(param1);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == COMPONENT_PLAYER_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(195);
                Protocol.outboundBuffer.p2_alt2(useWithSlot);
                Protocol.outboundBuffer.p4_alt1(useWithComponentId);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == PLAYER_ACTION_BLOCK) {
            if (Client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            } else {
                pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                if (!pathFound) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                }
            }
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(109);
            Protocol.outboundBuffer.p2_alt1(param2 + Camera.originZ);
            Protocol.outboundBuffer.p2(param1 + Camera.originX);
            Protocol.outboundBuffer.p2_alt3(keyInt);
        }
        if (actionCode == OBJ_IN_COMPONENT_ACTION_1) {
            Protocol.outboundBuffer.pIsaac1(81);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p4rme(param2);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == NPC_ACTION_5) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(218);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        @Pc(1955) int varp;
        if (actionCode == LOGOUT_ACTION) {
            Protocol.outboundBuffer.pIsaac1(10);
            Protocol.outboundBuffer.p4(param2);
            component = ComponentList.getComponent(param2);
            if (component.scripts != null && component.scripts[0][0] == 5) {
                varp = component.scripts[0][1];
                if (VarpDomain.activeVarps[varp] != component.scriptOperand[0]) {
                    VarpDomain.activeVarps[varp] = component.scriptOperand[0];
                    VarpDomain.refreshMagicVarp(varp);
                }
            }
        }
        if (actionCode == OBJ_NPC_ACTION) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(115);
                Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
                Protocol.outboundBuffer.p2_alt1(anInt4370);
                Protocol.outboundBuffer.p2_alt1(keyInt);
                Protocol.outboundBuffer.p2_alt3(anInt4997);
            }
        }
        if (actionCode == LOGOUT_ACTION_2) {
            Protocol.outboundBuffer.pIsaac1(10);
            Protocol.outboundBuffer.p4(param2);
            component = ComponentList.getComponent(param2);
            if (component.scripts != null && component.scripts[0][0] == 5) {
                varp = component.scripts[0][1];
                VarpDomain.activeVarps[varp] = 1 - VarpDomain.activeVarps[varp];
                VarpDomain.refreshMagicVarp(varp);
            }
        }
        if (actionCode == OBJ_OBJSTACK_ACTION) {
            pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            if (!pathFound) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            }
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairCycle = 0;
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.CrosshairMode = 2;
            Protocol.outboundBuffer.pIsaac1(101);
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.originX);
            Protocol.outboundBuffer.p2_alt1(anInt4370);
            Protocol.outboundBuffer.p2_alt1(anInt4997);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            Protocol.outboundBuffer.p2_alt3(Camera.originZ + param2);
            Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
        }
        if (actionCode == LOC_ACTION_EXAMINE) {
            Crosshair.CrosshairCycle = 0;
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.mouseClickY;
            Protocol.outboundBuffer.pIsaac1(94);
            Protocol.outboundBuffer.p2_alt3(keyInt);
        }
        if (actionCode == UNKNOWN_11) {
            if (keyInt == 0) {
                anInt3096 = 1;
                setWalkTarget(Player.plane, param1, param2);
            } else if (keyInt == 1) {
                Protocol.outboundBuffer.pIsaac1(131);
                Protocol.outboundBuffer.p4_alt3(useWithComponentId);
                Protocol.outboundBuffer.p2_alt2(Camera.originX + param1);
                Protocol.outboundBuffer.p2_alt3(useWithSlot);
                Protocol.outboundBuffer.p2_alt2(param2 + Camera.originZ);
            }
        }
        if (actionCode == UNKNOWN_8) {
            component = ComponentList.getComponent(param2);
            @Pc(2287) boolean shouldProcess = true;
            if (component.contentType > 0) {
                shouldProcess = shouldTriggerIdleTimeout(component);
            }
            if (shouldProcess) {
                Protocol.outboundBuffer.pIsaac1(10);
                Protocol.outboundBuffer.p4(param2);
            }
        }
        if (actionCode == OBJ_PLAYER_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(248);
                Protocol.outboundBuffer.p2_alt3(keyInt);
                Protocol.outboundBuffer.p2(anInt4997);
                Protocol.outboundBuffer.p2(anInt4370);
                Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
            }
        }
        if (actionCode == UNKNOWN_7) {
            Protocol.outboundBuffer.pIsaac1(85);
            Protocol.outboundBuffer.p4rme(param2);
            Protocol.outboundBuffer.p2(param1);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == UNKNOWN_24) {
            if (Client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            } else {
                pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                if (!pathFound) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                }
            }
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(48);
            Protocol.outboundBuffer.p2_alt2(param1 + Camera.originX);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            Protocol.outboundBuffer.p2_alt1(Camera.originZ + param2);
        }
        if (actionCode == COMPONENT_LOC_ACTION && PathFinder.findPathToLoc(key, param2, param1)) {
            Protocol.outboundBuffer.pIsaac1(233);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.originZ);
            Protocol.outboundBuffer.p2_alt2(Camera.originX + param1);
            Protocol.outboundBuffer.p2_alt3(useWithSlot);
            Protocol.outboundBuffer.p4rme(useWithComponentId);
            Protocol.outboundBuffer.p2_alt2((int) (key >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == UNKNOWN_13) {
            Protocol.outboundBuffer.pIsaac1(6);
            Protocol.outboundBuffer.p4(param2);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == PLAYER_ACTION_5) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(175);
                Protocol.outboundBuffer.p2_alt2(keyInt);
            }
        }
        if (actionCode == UNKNOWN_22) {
            handleUseWith();
            component = ComponentList.getComponent(param2);
            MiniMap.anInt5062 = param2;
            anInt4370 = param1;
            anInt5014 = 1;
            anInt4997 = keyInt;
            ComponentList.redraw(component);
            aClass100_203 = JString.concatenate(new JString[] {aClass100_32, ObjTypeList.get(keyInt).name, WHITE});
            if (aClass100_203 == null) {
                aClass100_203 = NULL;
            }
            return;
        }
        if (actionCode == LOC_ACTION_2) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(194);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.originZ);
            Protocol.outboundBuffer.p2_alt1(Camera.originX + param1);
            Protocol.outboundBuffer.p2((int) (key >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == UNKNOWN_48) {
            Protocol.outboundBuffer.pIsaac1(154);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p4rme(param2);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == PLAYER_ACTION_1) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairMode = 2;
                Protocol.outboundBuffer.pIsaac1(68);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == OBJ_IN_COMPONENT_ACTION_4) {
            Protocol.outboundBuffer.pIsaac1(153);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            anInt2043 = 0;
            pressedInventoryComponent = ComponentList.getComponent(param2);
            anInt5444 = param1;
        }
        if (actionCode == COMPONENT_OBJSTACK_ACTION) {
            pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            if (!pathFound) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            }
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairMode = 2;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(73);
            Protocol.outboundBuffer.p4rme(useWithComponentId);
            Protocol.outboundBuffer.p2(Camera.originZ + param2);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.originX);
            Protocol.outboundBuffer.p2_alt1(useWithSlot);
        }
        if (actionCode == UNKNOWN_12) {
            Protocol.outboundBuffer.pIsaac1(82);
            Protocol.outboundBuffer.p2(useWithSlot);
            Protocol.outboundBuffer.p4rme(param2);
            Protocol.outboundBuffer.p4(useWithComponentId);
            Protocol.outboundBuffer.p2_alt3(param1);
        }
        if (actionCode == UNKNOWN_36) {
            if (keyInt == 0) {
                Protocol.anInt4422 = 1;
                setWalkTarget(Player.plane, param1, param2);
            } else if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                Cheat.teleport(param1 + Camera.originX, Camera.originZ - -param2, Player.plane);
            } else {
                Protocol.outboundBuffer.pIsaac1(179);
                Protocol.outboundBuffer.p2(param2 + Camera.originZ);
                Protocol.outboundBuffer.p2(param1 + Camera.originX);
            }
        }
        if (actionCode == UNKNOWN_6) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(106);
                Protocol.outboundBuffer.p2(keyInt);
            }
        }
        if (actionCode == OBJSTACK_ACTION_2) {
            if (Client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            } else {
                pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                if (!pathFound) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                }
            }
            Crosshair.y = Mouse.mouseClickY;
            Crosshair.CrosshairCycle = 0;
            Crosshair.x = Mouse.mouseClickX;
            Crosshair.CrosshairMode = 2;
            Protocol.outboundBuffer.pIsaac1(33);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p2(Camera.originX + param1);
            Protocol.outboundBuffer.p2_alt1(Camera.originZ + param2);
        }
        if (actionCode == NPC_ACTION_2) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.mouseClickX;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.mouseClickY;
                Crosshair.CrosshairMode = 2;
                Protocol.outboundBuffer.pIsaac1(3);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (anInt5014 != 0) {
            anInt5014 = 0;
            ComponentList.redraw(ComponentList.getComponent(MiniMap.anInt5062));
        }
        if (useWithActive) {
            handleUseWith();
        }
        if (pressedInventoryComponent != null && anInt2043 == 0) {
            ComponentList.redraw(pressedInventoryComponent);
        }
    }

    @OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "(IBI)Lclient!na;")
    public static JString getCombatLevelColorTag(@OriginalArg(0) int viewerLevel, @OriginalArg(2) int otherLevel) {
        @Pc(4) int diff = otherLevel - viewerLevel;
        if (diff < -9) {
            return RED;
        } else if (diff < -6) {
            return RED2;
        } else if (diff < -3) {
            return ORANGE;
        } else if (diff < 0) {
            return YELLOW;
        } else if (diff > 9) {
            return GREEN;
        } else if (diff > 6) {
            return GREEN2;
        } else if (diff <= 3) {
            return diff > 0 ? GREEN3 : YELLOW2;
        } else {
            return GREEN4;
        }
    }

    @OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IIIIIIB)V")
    public static void populateMenuEntries(@OriginalArg(0) int startY, @OriginalArg(1) int width, @OriginalArg(2) int height, @OriginalArg(3) int startX, @OriginalArg(4) int endY, @OriginalArg(5) int endX) {
        @Pc(15) int screenMaxY;
        @Pc(47) int worldY;
        if (anInt5014 == 0) {
            @Pc(13) int screenMinY = Rasterizer.screenUpperY;
            screenMaxY = Rasterizer.screenLowerY;
            @Pc(17) int screenMinX = Rasterizer.screenUpperX;
            @Pc(19) int screenMaxX = Rasterizer.screenLowerX;
            @Pc(33) int worldX = (endX - startX) * (screenMinX - screenMaxX) / width + screenMaxX;
            worldY = screenMaxY + (screenMinY - screenMaxY) * (endY - startY) / height;
            if (useWithActive && (useWithMask & 0x40) != 0) {
                @Pc(61) Component component = ComponentList.getCreatedComponent(useWithComponentId, useWithSlot);
                if (component == null) {
                    handleUseWith();
                } else {
                    addActionRow(useWithCursor, 0L, aClass100_961, worldX, (short) 11, aClass100_545, worldY);
                }
            } else {
                if (Client.game == 1) {
                    addActionRow(-1, 0L, JString.EMPTY, worldX, (short) 36, LocalizedText.FACEHERE, worldY);
                }
                addActionRow(-1, 0L, JString.EMPTY, worldX, (short) 60, walkText, worldY);
            }
        }
        @Pc(112) long lastKey = -1L;
        for (screenMaxY = 0; screenMaxY < pickedEntityCount; screenMaxY++) {
            @Pc(121) long key = Model.aLongArray11[screenMaxY];
            worldY = (int) key & 0x7F;
            @Pc(133) int entityType = (int) key >> 29 & 0x3;
            @Pc(140) int entityId = (int) (key >>> 32) & Integer.MAX_VALUE;
            @Pc(147) int localZ = (int) key >> 7 & 0x7F;
            if (key != lastKey) {
                lastKey = key;
                @Pc(240) int opIndex;
                if (entityType == 2 && SceneGraph.isLocValid(Player.plane, worldY, localZ, key)) {
                    @Pc(172) LocType locType = LocTypeList.get(entityId);
                    if (locType.multiloc != null) {
                        locType = locType.getMultiLoc();
                    }
                    if (locType == null) {
                        continue;
                    }
                    if (anInt5014 == 1) {
                        addActionRow(MiniMap.anInt4075, key, JString.concatenate(new JString[] {aClass100_203, aClass100_164, locType.name}), worldY, (short) 14, LocalizedText.USE, localZ);
                    } else if (useWithActive) {
                        @Pc(363) ParamType paramType = useWithParam == -1 ? null : ParamTypeList.get(useWithParam);
                        if ((useWithMask & 0x4) != 0 && (paramType == null || locType.getParam(paramType.defaultInt, useWithParam) != paramType.defaultInt)) {
                            addActionRow(useWithCursor, key, JString.concatenate(new JString[] {aClass100_466, aClass100_164, locType.name}), worldY, (short) 38, aClass100_545, localZ);
                        }
                    } else {
                        @Pc(228) JString[] locOps = locType.op;
                        if (aBoolean237) {
                            locOps = annotateOps(locOps);
                        }
                        if (locOps != null) {
                            for (opIndex = 4; opIndex >= 0; opIndex--) {
                                if (locOps[opIndex] != null) {
                                    @Pc(254) short actionId = 0;
                                    if (opIndex == 0) {
                                        actionId = 42;
                                    }
                                    if (opIndex == 1) {
                                        actionId = 50;
                                    }
                                    @Pc(268) int cursor = -1;
                                    if (opIndex == 2) {
                                        actionId = 49;
                                    }
                                    if (locType.cursor1op == opIndex) {
                                        cursor = locType.cursor1;
                                    }
                                    if (opIndex == 3) {
                                        actionId = 46;
                                    }
                                    if (opIndex == locType.cursor2op) {
                                        cursor = locType.cursor2;
                                    }
                                    if (opIndex == 4) {
                                        actionId = 1001;
                                    }
                                    addActionRow(cursor, key, JString.concatenate(new JString[] {aClass100_1008, locType.name}), worldY, actionId, locOps[opIndex], localZ);
                                }
                            }
                        }
                        addActionRow(MiniMap.anInt5073, (long) locType.id, JString.concatenate(new JString[] {aClass100_1008, locType.name}), worldY, (short) 1004, LocalizedText.EXAMINE, localZ);
                    }
                }
                @Pc(514) int otherEntityX;
                @Pc(526) int otherEntityZ;
                @Pc(479) int entityBoundsX;
                @Pc(493) int i;
                @Pc(502) Npc otherNpc;
                @Pc(597) Player otherPlayer;
                if (entityType == 1) {
                    @Pc(421) Npc npc = NpcList.npcs[entityId];
                    if ((npc.type.size & 0x1) == 0 && (npc.xFine & 0x7F) == 0 && (npc.zFine & 0x7F) == 0 || (npc.type.size & 0x1) == 1 && (npc.xFine & 0x7F) == 64 && (npc.zFine & 0x7F) == 64) {
                        entityBoundsX = npc.xFine + 64 - npc.type.size * 64;
                        opIndex = npc.zFine - (npc.type.size - 1) * 64;
                        for (i = 0; i < NpcList.npcCount; i++) {
                            otherNpc = NpcList.npcs[NpcList.npcIds[i]];
                            otherEntityX = otherNpc.xFine + 64 - otherNpc.type.size * 64;
                            otherEntityZ = otherNpc.zFine + 64 - otherNpc.type.size * 64;
                            if (otherNpc != null && npc != otherNpc && otherEntityX >= entityBoundsX && npc.type.size - (otherEntityX - entityBoundsX >> 7) >= otherNpc.type.size && opIndex <= otherEntityZ && otherNpc.type.size <= npc.type.size - (otherEntityZ - opIndex >> 7)) {
                                addNpcEntries(otherNpc.type, worldY, NpcList.npcIds[i], localZ);
                            }
                        }
                        for (i = 0; i < PlayerList.playerCount; i++) {
                            otherPlayer = PlayerList.players[PlayerList.playerIds[i]];
                            otherEntityX = otherPlayer.xFine + 64 - otherPlayer.getSize() * 64;
                            otherEntityZ = otherPlayer.zFine + 64 - otherPlayer.getSize() * 64;
                            if (otherPlayer != null && otherEntityX >= entityBoundsX && otherPlayer.getSize() <= npc.type.size - (otherEntityX - entityBoundsX >> 7) && otherEntityZ >= opIndex && otherPlayer.getSize() <= npc.type.size - (otherEntityZ - opIndex >> 7)) {
                                addPlayerEntries(PlayerList.playerIds[i], localZ, otherPlayer, worldY);
                            }
                        }
                    }
                    addNpcEntries(npc.type, worldY, entityId, localZ);
                }
                if (entityType == 0) {
                    @Pc(688) Player player = PlayerList.players[entityId];
                    if ((player.xFine & 0x7F) == 64 && (player.zFine & 0x7F) == 64) {
                        entityBoundsX = player.xFine - (player.getSize() - 1) * 64;
                        opIndex = player.zFine + 64 - player.getSize() * 64;
                        for (i = 0; i < NpcList.npcCount; i++) {
                            otherNpc = NpcList.npcs[NpcList.npcIds[i]];
                            otherEntityX = otherNpc.xFine + 64 - otherNpc.type.size * 64;
                            otherEntityZ = otherNpc.zFine + 64 - otherNpc.type.size * 64;
                            if (otherNpc != null && otherEntityX >= entityBoundsX && otherNpc.type.size <= player.getSize() - (otherEntityX - entityBoundsX >> 7) && otherEntityZ >= opIndex && otherNpc.type.size <= player.getSize() - (otherEntityZ - opIndex >> 7)) {
                                addNpcEntries(otherNpc.type, worldY, NpcList.npcIds[i], localZ);
                            }
                        }
                        for (i = 0; i < PlayerList.playerCount; i++) {
                            otherPlayer = PlayerList.players[PlayerList.playerIds[i]];
                            otherEntityX = otherPlayer.xFine - (otherPlayer.getSize() - 1) * 64;
                            otherEntityZ = otherPlayer.zFine + 64 - otherPlayer.getSize() * 64;
                            if (otherPlayer != null && otherPlayer != player && entityBoundsX <= otherEntityX && otherPlayer.getSize() <= player.getSize() - (otherEntityX - entityBoundsX >> 7) && otherEntityZ >= opIndex && otherPlayer.getSize() <= player.getSize() - (otherEntityZ - opIndex >> 7)) {
                                addPlayerEntries(PlayerList.playerIds[i], localZ, otherPlayer, worldY);
                            }
                        }
                    }
                    addPlayerEntries(entityId, localZ, player, worldY);
                }
                if (entityType == 3) {
                    @Pc(931) LinkedList objStack = SceneGraph.objStacks[Player.plane][worldY][localZ];
                    if (objStack != null) {
                        for (@Pc(940) ObjStackNode objNode = (ObjStackNode) objStack.tail(); objNode != null; objNode = (ObjStackNode) objStack.prev()) {
                            opIndex = objNode.value.type;
                            @Pc(951) ObjType objType = ObjTypeList.get(opIndex);
                            if (anInt5014 == 1) {
                                addActionRow(MiniMap.anInt4075, (long) opIndex, JString.concatenate(new JString[] {aClass100_203, aClass100_947, objType.name}), worldY, (short) 33, LocalizedText.USE, localZ);
                            } else if (useWithActive) {
                                @Pc(1142) ParamType paramType = useWithParam == -1 ? null : ParamTypeList.get(useWithParam);
                                if ((useWithMask & 0x1) != 0 && (paramType == null || objType.getParam(paramType.defaultInt, useWithParam) != paramType.defaultInt)) {
                                    addActionRow(useWithCursor, (long) opIndex, JString.concatenate(new JString[] {aClass100_466, aClass100_947, objType.name}), worldY, (short) 39, aClass100_545, localZ);
                                }
                            } else {
                                @Pc(997) JString[] objOps = objType.op;
                                if (aBoolean237) {
                                    objOps = annotateOps(objOps);
                                }
                                for (otherEntityX = 4; otherEntityX >= 0; otherEntityX--) {
                                    if (objOps != null && objOps[otherEntityX] != null) {
                                        @Pc(1025) byte actionId = 0;
                                        if (otherEntityX == 0) {
                                            actionId = 21;
                                        }
                                        if (otherEntityX == 1) {
                                            actionId = 34;
                                        }
                                        @Pc(1041) int cursor = -1;
                                        if (otherEntityX == objType.cursor1Op) {
                                            cursor = objType.cursor1;
                                        }
                                        if (otherEntityX == 2) {
                                            actionId = 18;
                                        }
                                        if (objType.cursor2Op == otherEntityX) {
                                            cursor = objType.cursor2;
                                        }
                                        if (otherEntityX == 3) {
                                            actionId = 20;
                                        }
                                        if (otherEntityX == 4) {
                                            actionId = 24;
                                        }
                                        addActionRow(cursor, (long) opIndex, JString.concatenate(new JString[] {aClass100_32, objType.name}), worldY, actionId, objOps[otherEntityX], localZ);
                                    }
                                }
                                addActionRow(MiniMap.anInt5073, (long) opIndex, JString.concatenate(new JString[] {aClass100_32, objType.name}), worldY, (short) 1002, LocalizedText.EXAMINE, localZ);
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Lclient!me;IIII)V")
    public static void addNpcEntries(@OriginalArg(0) NpcType npc, @OriginalArg(1) int localX, @OriginalArg(3) int npcId, @OriginalArg(4) int localZ) {
        if (menuActionRow >= 400) {
            return;
        }
        if (npc.multinpc != null) {
            npc = npc.getMultiNPC();
        }
        if (npc == null || !npc.active) {
            return;
        }
        @Pc(35) JString tooltip = npc.name;
        if (npc.vislevel != 0) {
            @Pc(47) JString levelText = Client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
            tooltip = JString.concatenate(new JString[] { tooltip, getCombatLevelColorTag(npc.vislevel, PlayerList.self.combatLevel), OPEN_PARENTHESIS, levelText, JString.parseInt(npc.vislevel), CLOSE_PARENTHESIS});
        }
        if (anInt5014 == 1) {
            addActionRow(MiniMap.anInt4075, (long) npcId, JString.concatenate(new JString[] {aClass100_203, aClass100_407, tooltip }), localX, (short) 26, LocalizedText.USE, localZ);
        } else if (useWithActive) {
            @Pc(378) ParamType paramType = useWithParam == -1 ? null : ParamTypeList.get(useWithParam);
            if ((useWithMask & 0x2) != 0 && (paramType == null || npc.getParam(useWithParam, paramType.defaultInt) != paramType.defaultInt)) {
                addActionRow(useWithCursor, (long) npcId, JString.concatenate(new JString[] {aClass100_466, aClass100_407, tooltip }), localX, (short) 45, aClass100_545, localZ);
            }
        } else {
            @Pc(129) JString[] npcOps = npc.op;
            if (aBoolean237) {
                npcOps = annotateOps(npcOps);
            }
            @Pc(140) int opIndex;
            if (npcOps != null) {
                for (opIndex = 4; opIndex >= 0; opIndex--) {
                    if (npcOps[opIndex] != null && (Client.game != 0 || !npcOps[opIndex].equalsIgnoreCase(LocalizedText.ATTACK))) {
                        @Pc(161) byte actionId = 0;
                        if (opIndex == 0) {
                            actionId = 17;
                        }
                        if (opIndex == 1) {
                            actionId = 16;
                        }
                        @Pc(176) int cursor = -1;
                        if (opIndex == 2) {
                            actionId = 4;
                        }
                        if (opIndex == 3) {
                            actionId = 19;
                        }
                        if (npc.cursor1Op == opIndex) {
                            cursor = npc.cursor1;
                        }
                        if (opIndex == npc.cursor2Op) {
                            cursor = npc.cursor2;
                        }
                        if (opIndex == 4) {
                            actionId = 2;
                        }
                        addActionRow(cursor, (long) npcId, JString.concatenate(new JString[] {YELLOW2, tooltip }), localX, actionId, npcOps[opIndex], localZ);
                    }
                }
            }
            if (Client.game == 0 && npcOps != null) {
                for (opIndex = 4; opIndex >= 0; opIndex--) {
                    if (npcOps[opIndex] != null && npcOps[opIndex].equalsIgnoreCase(LocalizedText.ATTACK)) {
                        @Pc(271) short priorityModifier = 0;
                        if (npc.vislevel > PlayerList.self.combatLevel) {
                            priorityModifier = 2000; //THIS iS FOR LEFT CLICK ATTACK
                        }
                        @Pc(281) short baseActionId = 0;
                        if (opIndex == 0) {
                            baseActionId = 17;
                        }
                        if (opIndex == 1) {
                            baseActionId = 16;
                        }
                        if (opIndex == 2) {
                            baseActionId = 4;
                        }
                        if (opIndex == 3) {
                            baseActionId = 19;
                        }
                        if (opIndex == 4) {
                            baseActionId = 2;
                        }
                        if (baseActionId != 0) {
                            baseActionId += priorityModifier;
                        }
                        addActionRow(npc.cursorattack, (long) npcId, JString.concatenate(new JString[] {YELLOW2, tooltip }), localX, baseActionId, npcOps[opIndex], localZ);
                    }
                }
            }
            addActionRow(MiniMap.anInt5073, (long) npcId, JString.concatenate(new JString[] {YELLOW2, tooltip }), localX, (short) 1007, LocalizedText.EXAMINE, localZ);
        }
    }

    @OriginalMember(owner = "runetek4.client!rj", name = "a", descriptor = "(IIILclient!e;I)V")
    public static void addPlayerEntries(@OriginalArg(0) int playerId, @OriginalArg(2) int localZ, @OriginalArg(3) Player other, @OriginalArg(4) int localX) {
        if (PlayerList.self == other || menuActionRow >= 400) {
            return;
        }
        @Pc(158) JString tooltip;
        if (other.skill == 0) {
            @Pc(22) boolean markCombatDifference = true;
            if (PlayerList.self.combatRange != -1 && other.combatRange != -1) {
                @Pc(43) int highestCombatLevel = other.combatLevel < PlayerList.self.combatLevel ? PlayerList.self.combatLevel : other.combatLevel;
                @Pc(58) int highestCombatRange = PlayerList.self.combatRange < other.combatRange ? PlayerList.self.combatRange : other.combatRange;
                @Pc(69) int calc = highestCombatLevel * 10 / 100 + highestCombatRange + 5;
                @Pc(76) int combatDelta = PlayerList.self.combatLevel - other.combatLevel;
                if (combatDelta < 0) {
                    combatDelta = -combatDelta;
                }
                if (calc < combatDelta) {
                    markCombatDifference = false;
                }
            }
            @Pc(95) JString levelText = Client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
            if (other.combatLevel < other.combatLevelWithSummoning) {
                tooltip = JString.concatenate(new JString[] { other.getUsername(), markCombatDifference ? getCombatLevelColorTag(other.combatLevel, PlayerList.self.combatLevel) : WHITE, OPEN_PARENTHESIS, levelText, JString.parseInt(other.combatLevel), PLUS, JString.parseInt(other.combatLevelWithSummoning - other.combatLevel), CLOSE_PARENTHESIS});
            } else {
                tooltip = JString.concatenate(new JString[] { other.getUsername(), markCombatDifference ? getCombatLevelColorTag(other.combatLevel, PlayerList.self.combatLevel) : WHITE, OPEN_PARENTHESIS, levelText, JString.parseInt(other.combatLevel), CLOSE_PARENTHESIS});
            }
        } else {
            tooltip = JString.concatenate(new JString[] { other.getUsername(), OPEN_PARENTHESIS, LocalizedText.SKILL, JString.parseInt(other.skill), CLOSE_PARENTHESIS});
        }
        @Pc(275) int local275;
        if (anInt5014 == 1) {
            addActionRow(MiniMap.anInt4075, (long) playerId, JString.concatenate(new JString[] {aClass100_203, aClass100_561, tooltip }), localX, (short) 1, LocalizedText.USE, localZ);
        } else if (!useWithActive) {
            for (local275 = 7; local275 >= 0; local275--) {
                if (Player.options[local275] != null) {
                    @Pc(291) short priorityModifier = 0;
                    if (Client.game == 0 && Player.options[local275].equalsIgnoreCase(LocalizedText.ATTACK)) {
                        if (other.combatLevel > PlayerList.self.combatLevel) {
                            priorityModifier = 2000;
                        }
                        if (PlayerList.self.teamId != 0 && other.teamId != 0) {
                            if (PlayerList.self.teamId == other.teamId) {
                                priorityModifier = 2000;
                            } else {
                                priorityModifier = 0;
                            }
                        }
                    } else if (Player.secondaryOptions[local275]) {
                        priorityModifier = 2000;
                    }
                    @Pc(353) short baseActionId = PLAYER_ACTION_IDS[local275];
                    @Pc(358) short finalActionId = (short) (baseActionId + priorityModifier);
                    addActionRow(Player.cursors[local275], (long) playerId, JString.concatenate(new JString[] {WHITE, tooltip }), localX, finalActionId, Player.options[local275], localZ);
                }
            }
        } else if ((useWithMask & 0x8) != 0) {
            addActionRow(useWithCursor, (long) playerId, JString.concatenate(new JString[] {aClass100_466, aClass100_561, tooltip }), localX, (short) 15, aClass100_545, localZ);
        }
        for (local275 = 0; local275 < menuActionRow; local275++) {
            if (actions[local275] == 60) {
                opBases[local275] = JString.concatenate(new JString[] {WHITE, tooltip });
                break;
            }
        }
    }

    @OriginalMember(owner = "client!bc", name = "f", descriptor = "(B)Lclient!na;")
    public static JString getActionText() {
        @Pc(32) JString actionText;
        if (anInt5014 == 1 && menuActionRow < 2) {
            actionText = JString.concatenate(new JString[] { LocalizedText.USE, LocalizedText.MINISEPARATOR, aClass100_203, aClass100_961});
        } else if (useWithActive && menuActionRow < 2) {
            actionText = JString.concatenate(new JString[] {aClass100_545, LocalizedText.MINISEPARATOR, aClass100_466, aClass100_961});
        } else if (Cheat.shiftClick && Keyboard.pressedKeys[81] && menuActionRow > 2) {
            actionText = getOp(menuActionRow - 2);
        } else {
            actionText = getOp(menuActionRow - 1);
        }
        if (menuActionRow > 2) {
            actionText = JString.concatenate(new JString[] { actionText, aClass100_2, JString.parseInt(menuActionRow - 2), LocalizedText.MOREOPTIONS});
        }
        return actionText;
    }

    @OriginalMember(owner = "runetek4.client!il", name = "a", descriptor = "(III)V")
    public static void setWalkTarget(@OriginalArg(0) int plane, @OriginalArg(1) int x, @OriginalArg(2) int z) {
        walkTargetActive = true;
        targetPlane = plane;
        targetX = x;
        targetZ = z;
        clickTileX = -1;
        clickTileZ = -1;
    }

    @OriginalMember(owner = "runetek4.client!wi", name = "c", descriptor = "(II)Z")
    public static boolean isComponentAction(@OriginalArg(0) int menuIndex) {
        if (menuIndex < 0) {
            return false;
        }
        @Pc(12) int actionCode = actions[menuIndex];
        if (actionCode >= 2000) {
            actionCode -= 2000;
        }
        return actionCode == 1003;
    }

    @OriginalMember(owner = "runetek4.client!ub", name = "b", descriptor = "(IIIIIII)V")
    public static void startUseWith(@OriginalArg(0) int componentId, @OriginalArg(1) int slot, @OriginalArg(2) int targetMask, @OriginalArg(3) int param, @OriginalArg(4) int cursor, @OriginalArg(6) int defaultCursordefaultCursor) {
        @Pc(8) Component component = ComponentList.getCreatedComponent(componentId, slot);
        if (component != null && component.onUse != null) {
            @Pc(19) ComponentEvent event = new ComponentEvent();
            event.source = component;
            event.arguments = component.onUse;
            ClientScriptRunner.run(event);
        }
        useWithSlot = slot;
        useWithParam = param;
        useWithComponentId = componentId;
        useWithMask = targetMask;
        useWithActive = true;
        useWithCursor = cursor;
        defaultCursor = defaultCursordefaultCursor;
        ComponentList.redraw(component);
    }

    @OriginalMember(owner = "client!ej", name = "h", descriptor = "(I)V")
    public static void processMenuActions() {
        if (menuState == 2) {
            if (ClientScriptRunner.scriptMouseX == Mouse.lastClickX && ClientScriptRunner.scriptMouseY == Mouse.lastClickY) {
                menuState = 0;
                if (Cheat.shiftClick && Keyboard.pressedKeys[Keyboard.KEY_SHIFT] && menuActionRow > 2) {
                    doAction(menuActionRow - 2);
                } else {
                    doAction(menuActionRow - 1);
                }
            }
        } else if (ClientScriptRunner.scriptMouseX == Mouse.mouseClickX && ClientScriptRunner.scriptMouseY == Mouse.mouseClickY) {
            menuState = 0;
            if (Cheat.shiftClick && Keyboard.pressedKeys[Keyboard.KEY_SHIFT] && menuActionRow > 2) {
                doAction(menuActionRow - 2);
            } else {
                doAction(menuActionRow - 1);
            }
        } else {
            Mouse.lastClickY = Mouse.mouseClickY;
            menuState = 2;
            Mouse.lastClickX = Mouse.mouseClickX;
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(IZI)V")
    public static void sendComponentContinue(@OriginalArg(0) int componentId, @OriginalArg(2) int widgetId) {
        Protocol.outboundBuffer.pIsaac1(132);
        Protocol.outboundBuffer.p4rme(widgetId);
        Protocol.outboundBuffer.p2_alt1(componentId);
    }

    @OriginalMember(owner = "runetek4.client!tb", name = "h", descriptor = "(I)I")
    public static int getShiftClickOption() {
        return Cheat.shiftClick && Keyboard.pressedKeys[81] && menuActionRow > 2 ? cursors[menuActionRow - 2] : cursors[menuActionRow - 1];
    }
}
