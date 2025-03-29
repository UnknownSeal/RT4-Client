package com.jagex.runetek4;

import com.jagex.runetek4.config.types.npc.NpcType;
import com.jagex.runetek4.config.types.obj.ObjType;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.client.client;
import com.jagex.runetek4.config.types.loc.LocTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.config.types.param.ParamType;
import com.jagex.runetek4.config.types.param.ParamTypeList;
import com.jagex.runetek4.config.types.loc.LocType;
import com.jagex.runetek4.util.ArrayUtils;
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
    public static final JString aClass100_168 = JString.parse(": ");
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
    public static final int[] anIntArray132 = new int[4];
    @OriginalMember(owner = "runetek4.client!af", name = "l", descriptor = "[S")
    public static final short[] aShortArray2 = new short[] { 30, 6, 31, 29, 10, 44, 37, 57 };
    @OriginalMember(owner = "runetek4.client!sk", name = "kb", descriptor = "I")
    public static int menuActionRow = 0;
    @OriginalMember(owner = "runetek4.client!vd", name = "C", descriptor = "I")
    public static int anInt5014 = 0;
    @OriginalMember(owner = "runetek4.client!th", name = "n", descriptor = "Z")
    public static boolean aBoolean302 = false;
    @OriginalMember(owner = "runetek4.client!pk", name = "bb", descriptor = "Lclient!na;")
    public static JString walkText;
    @OriginalMember(owner = "runetek4.client!em", name = "D", descriptor = "I")
    public static int gregorianDateSeed;
    @OriginalMember(owner = "runetek4.client!hj", name = "e", descriptor = "I")
    public static int anInt2512;
    @OriginalMember(owner = "runetek4.client!u", name = "i", descriptor = "I")
    public static int anInt5393;
    @OriginalMember(owner = "client!be", name = "Ec", descriptor = "I")
    public static int anInt506 = -1;
    @OriginalMember(owner = "runetek4.client!hn", name = "W", descriptor = "Lclient!na;")
    public static JString aClass100_545 = null;
    @OriginalMember(owner = "runetek4.client!p", name = "e", descriptor = "I")
    public static int anInt4370;
    @OriginalMember(owner = "runetek4.client!uf", name = "t", descriptor = "I")
    public static int anInt5444 = 0;
    @OriginalMember(owner = "runetek4.client!v", name = "b", descriptor = "Lclient!be;")
    public static Component pressedInventoryComponent;
    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "I")
    public static int anInt7 = 0;
    @OriginalMember(owner = "runetek4.client!id", name = "k", descriptor = "I")
    public static int anInt2878;
    @OriginalMember(owner = "runetek4.client!cl", name = "Y", descriptor = "I")
    public static int anInt1092 = -1;
    @OriginalMember(owner = "runetek4.client!jl", name = "v", descriptor = "I")
    public static int anInt3096 = 0;
    @OriginalMember(owner = "client!ck", name = "D", descriptor = "Lclient!na;")
    public static JString aClass100_203 = null;
    @OriginalMember(owner = "client!gd", name = "i", descriptor = "Lclient!na;")
    public static JString aClass100_466 = null;
    @OriginalMember(owner = "runetek4.client!wf", name = "f", descriptor = "I")
    public static int anInt4999;
    @OriginalMember(owner = "runetek4.client!wf", name = "d", descriptor = "I")
    public static int anInt4997;
    @OriginalMember(owner = "client!fl", name = "P", descriptor = "I")
    public static int anInt2043 = 0;
    @OriginalMember(owner = "runetek4.client!ml", name = "Q", descriptor = "I")
    public static int anInt3953 = 0;
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
    public static int anInt2388 = 0;
    @OriginalMember(owner = "runetek4.client!jb", name = "p", descriptor = "I")
    public static int anInt2954 = -1;
    @OriginalMember(owner = "runetek4.client!jg", name = "b", descriptor = "I")
    public static int anInt3039;
    @OriginalMember(owner = "runetek4.client!kd", name = "zb", descriptor = "I")
    public static int anInt3259 = 0;
    @OriginalMember(owner = "runetek4.client!mh", name = "Y", descriptor = "Z")
    public static boolean aBoolean187 = false;
    @OriginalMember(owner = "runetek4.client!mj", name = "i", descriptor = "I")
    public static int anInt3902 = 0;

    @OriginalMember(owner = "runetek4.client!ud", name = "a", descriptor = "(ILclient!be;)Z")
    public static boolean method4265(@OriginalArg(1) Component arg0) {
        if (arg0.contentType == 205) {
            Protocol.idleTimeout = 250;
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!ec", name = "a", descriptor = "(B)V")
    public static void method1294() {
        if (!aBoolean302) {
            return;
        }
        @Pc(19) Component local19 = InterfaceList.method1418(anInt2512, anInt506);
        if (local19 != null && local19.onUseWith != null) {
            @Pc(29) HookRequest local29 = new HookRequest();
            local29.arguments = local19.onUseWith;
            local29.source = local19;
            ClientScriptRunner.run(local29);
        }
        aBoolean302 = false;
        anInt1092 = -1;
        InterfaceList.redraw(local19);
    }

    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
    public static void addActionRow(@OriginalArg(0) int arg0, @OriginalArg(1) long arg1, @OriginalArg(3) JString arg2, @OriginalArg(4) int arg3, @OriginalArg(5) short arg4, @OriginalArg(6) JString arg5, @OriginalArg(7) int arg6) {
        if (ClientScriptRunner.aBoolean108 || menuActionRow >= 500) {
            return;
        }
        ops[menuActionRow] = arg5;
        opBases[menuActionRow] = arg2;
        cursors[menuActionRow] = arg0 == -1 ? anInt1092 : arg0;
        actions[menuActionRow] = arg4;
        keys[menuActionRow] = arg1;
        intArgs1[menuActionRow] = arg3;
        intArgs2[menuActionRow] = arg6;
        menuActionRow++;
    }

    @OriginalMember(owner = "runetek4.client!va", name = "a", descriptor = "(IZILclient!be;)V")
    public static void addComponentEntries(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component component) {
        if (component.buttonType == 1) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 8, component.option, component.id);
        }
        @Pc(47) JString local47;
        if (component.buttonType == 2 && !aBoolean302) {
            local47 = MiniMap.getTargetVerb(component);
            if (local47 != null) {
                addActionRow(-1, 0L, JString.concatenate(new JString[] { GREEN, component.optionSuffix}), -1, (short) 32, local47, component.id);
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
        if (component.buttonType == 6 && ClientScriptRunner.aClass13_10 == null) {
            addActionRow(-1, 0L, JString.EMPTY, -1, (short) 41, component.option, component.id);
        }
        @Pc(173) int local173;
        @Pc(171) int local171;
        if (component.type == 2) {
            local171 = 0;
            for (local173 = 0; local173 < component.baseHeight; local173++) {
                for (@Pc(183) int local183 = 0; local183 < component.baseWidth; local183++) {
                    @Pc(195) int local195 = (component.invMarginX + 32) * local183;
                    @Pc(202) int local202 = (component.invMarginY + 32) * local173;
                    if (local171 < 20) {
                        local202 += component.invOffsetY[local171];
                        local195 += component.invOffsetX[local171];
                    }
                    if (arg1 >= local195 && local202 <= arg0 && local195 + 32 > arg1 && local202 + 32 > arg0) {
                        InterfaceList.mouseOverInventoryInterface = component;
                        mouseInvInterfaceIndex = local171;
                        if (component.invSlotObjId[local171] > 0) {
                            @Pc(267) ServerActiveProperties local267 = InterfaceList.getServerActiveProperties(component);
                            @Pc(276) ObjType local276 = ObjTypeList.get(component.invSlotObjId[local171] - 1);
                            if (anInt5014 == 1 && local267.isObjOpsEnabled()) {
                                if (MiniMap.anInt5062 != component.id || anInt4370 != local171) {
                                    addActionRow(-1, (long) local276.id, JString.concatenate(new JString[] { aClass100_203, aClass100_947, local276.name}), local171, (short) 40, LocalizedText.USE, component.id);
                                }
                            } else if (aBoolean302 && local267.isObjOpsEnabled()) {
                                @Pc(596) ParamType local596 = anInt3039 == -1 ? null : ParamTypeList.get(anInt3039);
                                if ((anInt4999 & 0x10) != 0 && (local596 == null || local276.getParam(local596.defaultInt, anInt3039) != local596.defaultInt)) {
                                    addActionRow(anInt5393, (long) local276.id, JString.concatenate(new JString[] { aClass100_466, aClass100_947, local276.name}), local171, (short) 3, aClass100_545, component.id);
                                }
                            } else {
                                @Pc(296) JString[] local296 = local276.interfaceOptions;
                                if (aBoolean237) {
                                    local296 = annotateOps(local296);
                                }
                                @Pc(309) int local309;
                                @Pc(334) byte local334;
                                if (local267.isObjOpsEnabled()) {
                                    for (local309 = 4; local309 >= 3; local309--) {
                                        if (local296 != null && local296[local309] != null) {
                                            if (local309 == 3) {
                                                local334 = 35;
                                            } else {
                                                local334 = 58;
                                            }
                                            addActionRow(-1, (long) local276.id, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, local334, local296[local309], component.id);
                                        }
                                    }
                                }
                                if (local267.isObjUseEnabled()) {
                                    addActionRow(MiniMap.anInt4075, (long) local276.id, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, (short) 22, LocalizedText.USE, component.id);
                                }
                                if (local267.isObjOpsEnabled() && local296 != null) {
                                    for (local309 = 2; local309 >= 0; local309--) {
                                        if (local296[local309] != null) {
                                            local334 = 0;
                                            if (local309 == 0) {
                                                local334 = 47;
                                            }
                                            if (local309 == 1) {
                                                local334 = 5;
                                            }
                                            if (local309 == 2) {
                                                local334 = 43;
                                            }
                                            addActionRow(-1, (long) local276.id, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, local334, local296[local309], component.id);
                                        }
                                    }
                                }
                                local296 = component.invOptions;
                                if (aBoolean237) {
                                    local296 = annotateOps(local296);
                                }
                                if (local296 != null) {
                                    for (local309 = 4; local309 >= 0; local309--) {
                                        if (local296[local309] != null) {
                                            local334 = 0;
                                            if (local309 == 0) {
                                                local334 = 25;
                                            }
                                            if (local309 == 1) {
                                                local334 = 23;
                                            }
                                            if (local309 == 2) {
                                                local334 = 48;
                                            }
                                            if (local309 == 3) {
                                                local334 = 7;
                                            }
                                            if (local309 == 4) {
                                                local334 = 13;
                                            }
                                            addActionRow(-1, (long) local276.id, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, local334, local296[local309], component.id);
                                        }
                                    }
                                }
                                addActionRow(MiniMap.anInt5073, (long) local276.id, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, (short) 1006, LocalizedText.EXAMINE, component.id);
                            }
                        }
                    }
                    local171++;
                }
            }
        }
        if (!component.if3) {
            return;
        }
        if (!aBoolean302) {
            for (local171 = 9; local171 >= 5; local171--) {
                @Pc(765) JString local765 = InterfaceList.getOp(component, local171);
                if (local765 != null) {
                    addActionRow(getOpCursor(local171, component), (long) (local171 + 1), component.optionBase, component.createdComponentId, (short) 1003, local765, component.id);
                }
            }
            local47 = MiniMap.getTargetVerb(component);
            if (local47 != null) {
                addActionRow(-1, 0L, component.optionBase, component.createdComponentId, (short) 32, local47, component.id);
            }
            for (local173 = 4; local173 >= 0; local173--) {
                @Pc(828) JString local828 = InterfaceList.getOp(component, local173);
                if (local828 != null) {
                    addActionRow(getOpCursor(local173, component), (long) (local173 + 1), component.optionBase, component.createdComponentId, (short) 9, local828, component.id);
                }
            }
            if (InterfaceList.getServerActiveProperties(component).isResumePauseButtonEnabled()) {
                addActionRow(-1, 0L, JString.EMPTY, component.createdComponentId, (short) 41, LocalizedText.CONTINUE, component.id);
            }
        } else if (InterfaceList.getServerActiveProperties(component).isUseTarget() && (anInt4999 & 0x20) != 0) {
            addActionRow(anInt5393, 0L, JString.concatenate(new JString[] { aClass100_466, aClass100_408, component.optionBase}), component.createdComponentId, (short) 12, aClass100_545, component.id);
        }
    }

    @OriginalMember(owner = "runetek4.client!qe", name = "b", descriptor = "(II)V")
    public static void remove(@OriginalArg(1) int i) {
        menuActionRow--;
        if (menuActionRow == i) {
            return;
        }
        ArrayUtils.copy(ops, i + 1, ops, i, menuActionRow - i);
        ArrayUtils.copy(opBases, i + 1, opBases, i, menuActionRow - i);
        ArrayUtils.copy(cursors, i + 1, cursors, i, menuActionRow - i);
        ArrayUtils.copy(actions, i + 1, actions, i, menuActionRow - i);
        ArrayUtils.copy(keys, i + 1, keys, i, menuActionRow - i);
        ArrayUtils.copy(intArgs1, i + 1, intArgs1, i, menuActionRow - i);
        ArrayUtils.copy(intArgs2, i + 1, intArgs2, i, menuActionRow - i);
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "b", descriptor = "(I)V")
    public static void sort() {
        @Pc(3) boolean local3 = false;
        while (!local3) {
            local3 = true;
            for (@Pc(13) int local13 = 0; local13 < menuActionRow - 1; local13++) {
                if (actions[local13] < 1000 && actions[local13 + 1] > 1000) {
                    @Pc(41) JString local41 = opBases[local13];
                    local3 = false;
                    opBases[local13] = opBases[local13 + 1];
                    opBases[local13 + 1] = local41;
                    @Pc(61) JString local61 = ops[local13];
                    ops[local13] = ops[local13 + 1];
                    ops[local13 + 1] = local61;
                    @Pc(79) int local79 = intArgs1[local13];
                    intArgs1[local13] = intArgs1[local13 + 1];
                    intArgs1[local13 + 1] = local79;
                    @Pc(97) int local97 = intArgs2[local13];
                    intArgs2[local13] = intArgs2[local13 + 1];
                    intArgs2[local13 + 1] = local97;
                    @Pc(115) int local115 = cursors[local13];
                    cursors[local13] = cursors[local13 + 1];
                    cursors[local13 + 1] = local115;
                    @Pc(133) short local133 = actions[local13];
                    actions[local13] = actions[local13 + 1];
                    actions[local13 + 1] = local133;
                    @Pc(151) long local151 = keys[local13];
                    keys[local13] = keys[local13 + 1];
                    keys[local13 + 1] = local151;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ij", name = "a", descriptor = "(B)V")
    public static void drawB() {
        @Pc(3) int local3 = InterfaceList.anInt4271;
        @Pc(9) int local9 = InterfaceList.anInt5138;
        @Pc(11) int local11 = InterfaceList.anInt436;
        @Pc(13) int local13 = InterfaceList.anInt761;
        if (LoginManager.aClass3_Sub2_Sub1_1 == null || LoginManager.aClass3_Sub2_Sub1_9 == null) {
            if (client.js5Archive8.isFileReady(LoginManager.anInt1736) && client.js5Archive8.isFileReady(LoginManager.anInt4073)) {
                LoginManager.aClass3_Sub2_Sub1_1 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, LoginManager.anInt1736);
                LoginManager.aClass3_Sub2_Sub1_9 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, LoginManager.anInt4073);
                if (GlRenderer.enabled) {
                    if (LoginManager.aClass3_Sub2_Sub1_1 instanceof SoftwareAlphaSprite) {
                        LoginManager.aClass3_Sub2_Sub1_1 = new GlAlphaSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_1);
                    } else {
                        LoginManager.aClass3_Sub2_Sub1_1 = new GlSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_1);
                    }
                    if (LoginManager.aClass3_Sub2_Sub1_9 instanceof SoftwareAlphaSprite) {
                        LoginManager.aClass3_Sub2_Sub1_9 = new GlAlphaSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_9);
                    } else {
                        LoginManager.aClass3_Sub2_Sub1_9 = new GlSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_9);
                    }
                }
            } else if (GlRenderer.enabled) {
                GlRaster.fillRectAlpha(local3, local9, local13, 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
            } else {
                SoftwareRaster.fillRectAlpha(local3, local9, local13, 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
            }
        }
        @Pc(112) int local112;
        @Pc(114) int local114;
        if (LoginManager.aClass3_Sub2_Sub1_1 != null && LoginManager.aClass3_Sub2_Sub1_9 != null) {
            local112 = local13 / LoginManager.aClass3_Sub2_Sub1_1.width;
            for (local114 = 0; local114 < local112; local114++) {
                LoginManager.aClass3_Sub2_Sub1_1.render(local114 * LoginManager.aClass3_Sub2_Sub1_1.width + local3, local9);
            }
            LoginManager.aClass3_Sub2_Sub1_9.render(local3, local9);
            LoginManager.aClass3_Sub2_Sub1_9.renderHorizontalFlip(local3 + local13 - LoginManager.aClass3_Sub2_Sub1_9.width, local9);
        }
        Fonts.b12Full.renderLeft(LocalizedText.CHOOSE_OPTION, local3 + 3, local9 + 14, LoginManager.anInt4581, -1);
        if (GlRenderer.enabled) {
            GlRaster.fillRectAlpha(local3, local9 + 20, local13, local11 - 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
        } else {
            SoftwareRaster.fillRectAlpha(local3, local9 + 20, local13, local11 - 20, LoginManager.anInt1275, 256 - LoginManager.anInt2910);
        }
        local114 = Mouse.lastMouseY;
        local112 = Mouse.lastMouseX;
        @Pc(203) int local203;
        @Pc(219) int local219;
        for (local203 = 0; local203 < menuActionRow; local203++) {
            local219 = (menuActionRow - local203 - 1) * 15 + local9 + 35;
            if (local3 < local112 && local112 < local3 + local13 && local114 > local219 - 13 && local114 < local219 + 3) {
                if (GlRenderer.enabled) {
                    GlRaster.fillRectAlpha(local3, local219 - 13, local13, 16, LoginManager.anInt5457, 256 - LoginManager.anInt5208);
                } else {
                    SoftwareRaster.fillRectAlpha(local3, local219 - 13, local13, 16, LoginManager.anInt5457, 256 - LoginManager.anInt5208);
                }
            }
        }
        if ((LoginManager.aClass3_Sub2_Sub1_8 == null || LoginManager.aClass3_Sub2_Sub1_6 == null || LoginManager.aClass3_Sub2_Sub1_10 == null) && client.js5Archive8.isFileReady(LoginManager.anInt2261) && client.js5Archive8.isFileReady(LoginManager.anInt3324) && client.js5Archive8.isFileReady(LoginManager.anInt5556)) {
            LoginManager.aClass3_Sub2_Sub1_8 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, LoginManager.anInt2261);
            LoginManager.aClass3_Sub2_Sub1_6 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, LoginManager.anInt3324);
            LoginManager.aClass3_Sub2_Sub1_10 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, LoginManager.anInt5556);
            if (GlRenderer.enabled) {
                if (LoginManager.aClass3_Sub2_Sub1_8 instanceof SoftwareAlphaSprite) {
                    LoginManager.aClass3_Sub2_Sub1_8 = new GlAlphaSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_8);
                } else {
                    LoginManager.aClass3_Sub2_Sub1_8 = new GlSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_8);
                }
                if (LoginManager.aClass3_Sub2_Sub1_6 instanceof SoftwareAlphaSprite) {
                    LoginManager.aClass3_Sub2_Sub1_6 = new GlAlphaSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_6);
                } else {
                    LoginManager.aClass3_Sub2_Sub1_6 = new GlSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_6);
                }
                if (LoginManager.aClass3_Sub2_Sub1_10 instanceof SoftwareAlphaSprite) {
                    LoginManager.aClass3_Sub2_Sub1_10 = new GlAlphaSprite((com.jagex.runetek4.cache.media.SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_10);
                } else {
                    LoginManager.aClass3_Sub2_Sub1_10 = new GlSprite((SoftwareSprite) LoginManager.aClass3_Sub2_Sub1_10);
                }
            }
        }
        @Pc(418) int local418;
        if (LoginManager.aClass3_Sub2_Sub1_8 != null && LoginManager.aClass3_Sub2_Sub1_6 != null && LoginManager.aClass3_Sub2_Sub1_10 != null) {
            local203 = local13 / LoginManager.aClass3_Sub2_Sub1_8.width;
            for (local219 = 0; local219 < local203; local219++) {
                LoginManager.aClass3_Sub2_Sub1_8.render(local3 + LoginManager.aClass3_Sub2_Sub1_8.width * local219, local11 + local9 + -LoginManager.aClass3_Sub2_Sub1_8.height);
            }
            local219 = (local11 - 20) / LoginManager.aClass3_Sub2_Sub1_6.height;
            for (local418 = 0; local418 < local219; local418++) {
                LoginManager.aClass3_Sub2_Sub1_6.render(local3, local9 + local418 * LoginManager.aClass3_Sub2_Sub1_6.height + 20);
                LoginManager.aClass3_Sub2_Sub1_6.renderHorizontalFlip(local3 + local13 - LoginManager.aClass3_Sub2_Sub1_6.width, local9 + 20 + local418 * LoginManager.aClass3_Sub2_Sub1_6.height);
            }
            LoginManager.aClass3_Sub2_Sub1_10.render(local3, local11 + local9 - LoginManager.aClass3_Sub2_Sub1_10.height);
            LoginManager.aClass3_Sub2_Sub1_10.renderHorizontalFlip(local3 + local13 - LoginManager.aClass3_Sub2_Sub1_10.width, local9 - -local11 + -LoginManager.aClass3_Sub2_Sub1_10.height);
        }
        for (local203 = 0; local203 < menuActionRow; local203++) {
            local219 = (menuActionRow - local203 - 1) * 15 + local9 + 35;
            local418 = LoginManager.anInt4581;
            if (local3 < local112 && local13 + local3 > local112 && local219 - 13 < local114 && local114 < local219 + 3) {
                local418 = LoginManager.anInt5752;
            }
            Fonts.b12Full.renderLeft(getOp(local203), local3 + 3, local219, local418, 0);
        }
        InterfaceList.forceRedrawScreen(InterfaceList.anInt4271, InterfaceList.anInt5138, InterfaceList.anInt436, InterfaceList.anInt761);
    }

    @OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "(I)V")
    public static void drawA() {
        @Pc(3) int local3 = InterfaceList.anInt5138;
        @Pc(9) int local9 = InterfaceList.anInt761;
        @Pc(11) int local11 = InterfaceList.anInt4271;
        @Pc(15) int local15 = InterfaceList.anInt436;
        if (GlRenderer.enabled) {
            GlRaster.fillRect(local11, local3, local9, local15, 6116423);
            GlRaster.fillRect(local11 + 1, local3 + 1, local9 - 2, 16, 0);
            GlRaster.drawRect(local11 + 1, local3 + 18, local9 - 2, local15 + -19, 0);
        } else {
            SoftwareRaster.fillRect(local11, local3, local9, local15, 6116423);
            SoftwareRaster.fillRect(local11 + 1, local3 + 1, local9 - 2, 16, 0);
            SoftwareRaster.drawRect(local11 + 1, local3 + 18, local9 - 2, local15 + -19, 0);
        }
        Fonts.b12Full.renderLeft(LocalizedText.CHOOSE_OPTION, local11 + 3, local3 + 14, 6116423, -1);
        @Pc(96) int local96 = Mouse.lastMouseY;
        @Pc(98) int local98 = Mouse.lastMouseX;
        for (@Pc(107) int local107 = 0; local107 < menuActionRow; local107++) {
            @Pc(127) int local127 = (menuActionRow - local107 - 1) * 15 + local3 + 31;
            @Pc(129) int local129 = 16777215; //WHITE
            if (local11 < local98 && local98 < local11 + local9 && local127 - 13 < local96 && local96 < local127 + 3) {
                local129 = 16776960; //YELLOW
            }
            Fonts.b12Full.renderLeft(getOp(local107), local11 + 3, local127, local129, 0);
        }
        InterfaceList.forceRedrawScreen(InterfaceList.anInt4271, InterfaceList.anInt5138, InterfaceList.anInt436, InterfaceList.anInt761);
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(Lclient!be;III)V")
    public static void method1207(@OriginalArg(0) Component arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
        if (menuActionRow < 2 && anInt5014 == 0 && !aBoolean302) {
            return;
        }
        @Pc(24) JString local24 = method471();
        if (arg0 == null) {
            @Pc(40) int local40 = Fonts.b12Full.method2859(local24, arg2 + 4, arg1 - -15, client.aRandom1, gregorianDateSeed);
            InterfaceList.redrawScreen(arg2 + 4, Fonts.b12Full.getStringWidth(local24) + local40, arg1, 15);
            return;
        }
        @Pc(59) Font local59 = arg0.getFont(Sprites.nameIcons);
        if (local59 == null) {
            local59 = Fonts.b12Full;
        }
        local59.method2878(local24, arg2, arg1, arg0.width, arg0.height, arg0.color, arg0.shadowColor, arg0.halign, arg0.valign, client.aRandom1, gregorianDateSeed, anIntArray132);
        InterfaceList.redrawScreen(anIntArray132[0], anIntArray132[2], anIntArray132[1], anIntArray132[3]);
    }

    @OriginalMember(owner = "runetek4.client!wk", name = "a", descriptor = "(I[Lclient!na;)[Lclient!na;")
    public static JString[] annotateOps(@OriginalArg(1) JString[] ops) {
        @Pc(8) JString[] annotatedOps = new JString[5];
        for (@Pc(15) int i = 0; i < 5; i++) {
            annotatedOps[i] = JString.concatenate(new JString[] { JString.parseInt(i), aClass100_168});
            if (ops != null && ops[i] != null) {
                annotatedOps[i] = JString.concatenate(new JString[] { annotatedOps[i], ops[i] });
            }
        }
        return annotatedOps;
    }

    @OriginalMember(owner = "client!aj", name = "a", descriptor = "(BILclient!be;)I")
    public static int getOpCursor(@OriginalArg(1) int arg0, @OriginalArg(2) Component arg1) {
        if (!InterfaceList.getServerActiveProperties(arg1).isButtonEnabled(arg0) && arg1.onOptionClick == null) {
            return -1;
        } else if (arg1.anIntArray39 == null || arg0 >= arg1.anIntArray39.length) {
            return -1;
        } else {
            return arg1.anIntArray39[arg0];
        }
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
    public static JString getOp(@OriginalArg(0) int arg0) {
        return opBases[arg0].length() > 0 ? JString.concatenate(new JString[] { ops[arg0], LocalizedText.MINISEPARATOR, opBases[arg0] }) : ops[arg0];
    }

    @OriginalMember(owner = "runetek4.client!i", name = "p", descriptor = "(II)V")
    public static void doAction(@OriginalArg(1) int arg0) {
        if (arg0 < 0) {
            return;
        }
        @Pc(15) int local15 = intArgs1[arg0];
        @Pc(19) int local19 = intArgs2[arg0];
        @Pc(23) int actionCode = actions[arg0];
        if (actionCode >= 2000) {
            actionCode -= 2000;
        }
        @Pc(31) long local31 = keys[arg0];
        @Pc(36) int a = (int) keys[arg0];
        @Pc(43) Player player;
        if (actionCode == PLAYER_FOLLOW_ACTION) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossMode = 2;
                Cross.crossCycle = 0;
                Cross.x = Mouse.mouseClickX;
                Cross.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(71);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        if (actionCode == LOC_ACTION_4) {
            PathFinder.findPathToLoc(local31, local19, local15);
            Protocol.outboundBuffer.pIsaac1(247);
            Protocol.outboundBuffer.p2_alt1(Camera.originZ + local19);
            Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
            Protocol.outboundBuffer.p2(Integer.MAX_VALUE & (int) (local31 >>> 32));
        }
        if (actionCode == OBJ_OBJ_ACTION) {
            Protocol.outboundBuffer.pIsaac1(27);
            Protocol.outboundBuffer.p2(anInt4370);
            Protocol.outboundBuffer.p4_alt1(local19);
            Protocol.outboundBuffer.p2_alt1(local15);
            Protocol.outboundBuffer.p4_alt1(MiniMap.anInt5062);
            Protocol.outboundBuffer.p2_alt3(anInt4997);
            Protocol.outboundBuffer.p2_alt3(a);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        @Pc(192) Npc npc;
        if (actionCode == NPC_ACTION_4) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.x = Mouse.mouseClickX;
                Cross.crossMode = 2;
                Cross.crossCycle = 0;
                Cross.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(30);
                Protocol.outboundBuffer.p2(a);
            }
        }
        if (actionCode == NPC_ACTION_1) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.x = Mouse.mouseClickX;
                Cross.crossCycle = 0;
                Cross.crossMode = 2;
                Cross.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(78);
                Protocol.outboundBuffer.p2_alt1(a);
            }
        }
        if (actionCode == UNKNOWN_44) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.x = Mouse.mouseClickX;
                Cross.crossMode = 2;
                Cross.y = Mouse.mouseClickY;
                Cross.crossCycle = 0;
                Protocol.outboundBuffer.pIsaac1(133);
                Protocol.outboundBuffer.p2_alt1(a);
            }
        }
        if (actionCode == OBJ_ACTION_5) {
            Protocol.outboundBuffer.pIsaac1(135);
            Protocol.outboundBuffer.p2_alt2(a);
            Protocol.outboundBuffer.p2_alt2(local15);
            Protocol.outboundBuffer.p4_alt3(local19);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == LOC_ACTION_1) {
            PathFinder.findPathToLoc(local31, local19, local15);
            Protocol.outboundBuffer.pIsaac1(254);
            Protocol.outboundBuffer.p2_alt1(local15 + Camera.originX);
            Protocol.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
            Protocol.outboundBuffer.p2(local19 + Camera.originZ);
        }
        if (actionCode == COMPONENT_ACTION_CLOSE) {
            ClientProt.closeWidget();
        }
        if (actionCode == COMPONENT_NPC_ACTION) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.x = Mouse.mouseClickX;
                Cross.crossMode = 2;
                Cross.crossCycle = 0;
                Cross.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(239);
                Protocol.outboundBuffer.p4_alt1(anInt2512);
                Protocol.outboundBuffer.p2_alt2(anInt506);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        @Pc(560) boolean local560;
        if (actionCode == OBJSTACK_ACTION_1) {
            if (client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            } else {
                local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                if (!local560) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                }
            }
            Cross.x = Mouse.mouseClickX;
            Cross.crossCycle = 0;
            Cross.crossMode = 2;
            Cross.y = Mouse.mouseClickY;
            Protocol.outboundBuffer.pIsaac1(66);
            Protocol.outboundBuffer.p2_alt1(Camera.originX + local15);
            Protocol.outboundBuffer.p2(a);
            Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
        }
        if (actionCode == LOC_ACTION_5) {
            PathFinder.findPathToLoc(local31, local19, local15);
            Protocol.outboundBuffer.pIsaac1(170);
            Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (local31 >>> 32));
            Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
            Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
        }
        if (actionCode == OBJ_EXAMINE) {
            Cross.crossMode = 2;
            Cross.x = Mouse.mouseClickX;
            Cross.y = Mouse.mouseClickY;
            Cross.crossCycle = 0;
            Protocol.outboundBuffer.pIsaac1(92);
            Protocol.outboundBuffer.p2_alt3(a);
        }
        @Pc(693) Component com;
        if (actionCode == OBJ_EXAMINE_IN_COMPONENT) {
            com = InterfaceList.getComponent(local19);
            if (com == null || com.invSlotObjCount[local15] < 100000) {
                Protocol.outboundBuffer.pIsaac1(92);
                Protocol.outboundBuffer.p2_alt3(a);
            } else {
                Chat.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { JString.parseInt(com.invSlotObjCount[local15]), aClass100_1039, ObjTypeList.get(a).name}));
            }
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == WALK_HERE) {
            if (a == 0) {
                method3556(Player.plane, local15, local19);
            } else if (a == 1) {
                if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                    Cheat.teleport(Camera.originX + local15, Camera.originZ + local19, Player.plane);
                } else if (PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, local15, 0, 0, 1, local19, PlayerList.self.movementQueueX[0])) {
                    Protocol.outboundBuffer.p1(InterfaceList.anInt5);
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
            Cross.crossCycle = 0;
            Cross.crossMode = 2;
            Cross.y = Mouse.mouseClickY;
            Cross.x = Mouse.mouseClickX;
            npc = NpcList.npcs[a];
            if (npc != null) {
                @Pc(884) NpcType local884 = npc.type;
                if (local884.multiNpcs != null) {
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
            Protocol.outboundBuffer.p2_alt3(local15);
            Protocol.outboundBuffer.p2_alt2(a);
            Protocol.outboundBuffer.p4_alt1(local19);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == COMPONENT_OBJ_ACTION) {
            Protocol.outboundBuffer.pIsaac1(253);
            Protocol.outboundBuffer.p4_alt1(anInt2512);
            Protocol.outboundBuffer.p2_alt3(local15);
            Protocol.outboundBuffer.p4_alt1(local19);
            Protocol.outboundBuffer.p2_alt2(a);
            Protocol.outboundBuffer.p2_alt1(anInt506);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == UNKNOWN_10) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossMode = 2;
                Cross.y = Mouse.mouseClickY;
                Cross.x = Mouse.mouseClickX;
                Cross.crossCycle = 0;
                Protocol.outboundBuffer.pIsaac1(4);
                Protocol.outboundBuffer.p2_alt1(a);
            }
        }
        if (actionCode == UNKNOWN_41 && ClientScriptRunner.aClass13_10 == null) {
            method10(local15, local19);
            ClientScriptRunner.aClass13_10 = InterfaceList.method1418(local19, local15);
            InterfaceList.redraw(ClientScriptRunner.aClass13_10);
        }
        if (actionCode == LOC_ACTION_3) {
            PathFinder.findPathToLoc(local31, local19, local15);
            Protocol.outboundBuffer.pIsaac1(84);
            Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (local31 >>> 32));
            Protocol.outboundBuffer.p2_alt3(Camera.originZ + local19);
            Protocol.outboundBuffer.p2_alt1(local15 + Camera.originX);
        }
        if (actionCode == OBJ_OPERATE_ACTION) {
            Protocol.outboundBuffer.pIsaac1(206);
            Protocol.outboundBuffer.p2_alt2(a);
            Protocol.outboundBuffer.p2_alt1(local15);
            Protocol.outboundBuffer.p4_alt1(local19);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == OBJ_LOC_ACTION && PathFinder.findPathToLoc(local31, local19, local15)) {
            Protocol.outboundBuffer.pIsaac1(134);
            Protocol.outboundBuffer.p2_alt2(Camera.originX + local15);
            Protocol.outboundBuffer.p2(anInt4997);
            Protocol.outboundBuffer.p2_alt1(local19 + Camera.originZ);
            Protocol.outboundBuffer.p2(anInt4370);
            Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
            Protocol.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == PLAYER_REQ_ASSIST_ACTION) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossCycle = 0;
                Cross.crossMode = 2;
                Cross.y = Mouse.mouseClickY;
                Cross.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(114);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        if (actionCode == UNKNOWN_9 || actionCode == UNKNOWN_1003) {
            ClientProt.method4512(opBases[arg0], local15, a, local19);
        }
        if (actionCode == OBJ_EQUIP_ACTION) {
            Protocol.outboundBuffer.pIsaac1(55);
            Protocol.outboundBuffer.p2_alt1(a);
            Protocol.outboundBuffer.p2_alt2(local15);
            Protocol.outboundBuffer.p4rme(local19);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == UNKNOWN_21) {
            if (client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            } else {
                local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                if (!local560) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                }
            }
            Cross.crossMode = 2;
            Cross.x = Mouse.mouseClickX;
            Cross.crossCycle = 0;
            Cross.y = Mouse.mouseClickY;
            Protocol.outboundBuffer.pIsaac1(228);
            Protocol.outboundBuffer.p2(a);
            Protocol.outboundBuffer.p2_alt1(Camera.originX + local15);
            Protocol.outboundBuffer.p2_alt3(Camera.originZ + local19);
        }
        if (actionCode == NPC_ACTION_3) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossMode = 2;
                Cross.crossCycle = 0;
                Cross.y = Mouse.mouseClickY;
                Cross.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(148);
                Protocol.outboundBuffer.p2_alt2(a);
            }
        }
        if (actionCode == UNKNOWN_32) {
            com = InterfaceList.method1418(local19, local15);
            if (com != null) {
                method1294();
                @Pc(1493) ServerActiveProperties local1493 = InterfaceList.getServerActiveProperties(com);
                method4246(local19, local15, local1493.getTargetMask(), local1493.targetParam, com.anInt499, com.anInt484);
                anInt5014 = 0;
                aClass100_545 = MiniMap.getTargetVerb(com);
                if (aClass100_545 == null) {
                    aClass100_545 = aClass100_1042;
                }
                if (com.if3) {
                    aClass100_466 = JString.concatenate(new JString[] { com.optionBase, WHITE});
                } else {
                    aClass100_466 = JString.concatenate(new JString[] {GREEN, com.optionSuffix, WHITE});
                }
            }
            return;
        }
        if (actionCode == PLAYER_ACTION_TRADE) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.y = Mouse.mouseClickY;
                Cross.crossCycle = 0;
                Cross.crossMode = 2;
                Cross.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(180);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        if (actionCode == OBJ_ACTION_4) {
            Protocol.outboundBuffer.pIsaac1(161);
            Protocol.outboundBuffer.p4_alt1(local19);
            Protocol.outboundBuffer.p2_alt3(a);
            Protocol.outboundBuffer.p2_alt3(local15);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == COMPONENT_PLAYER_ACTION) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossCycle = 0;
                Cross.crossMode = 2;
                Cross.x = Mouse.mouseClickX;
                Cross.y = Mouse.mouseClickY;
                Protocol.outboundBuffer.pIsaac1(195);
                Protocol.outboundBuffer.p2_alt2(anInt506);
                Protocol.outboundBuffer.p4_alt1(anInt2512);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        if (actionCode == PLAYER_ACTION_BLOCK) {
            if (client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            } else {
                local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                if (!local560) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                }
            }
            Cross.x = Mouse.mouseClickX;
            Cross.crossMode = 2;
            Cross.y = Mouse.mouseClickY;
            Cross.crossCycle = 0;
            Protocol.outboundBuffer.pIsaac1(109);
            Protocol.outboundBuffer.p2_alt1(local19 + Camera.originZ);
            Protocol.outboundBuffer.p2(local15 + Camera.originX);
            Protocol.outboundBuffer.p2_alt3(a);
        }
        if (actionCode == OBJ_IN_COMPONENT_ACTION_1) {
            Protocol.outboundBuffer.pIsaac1(81);
            Protocol.outboundBuffer.p2_alt2(local15);
            Protocol.outboundBuffer.p2(a);
            Protocol.outboundBuffer.p4rme(local19);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == NPC_ACTION_5) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.y = Mouse.mouseClickY;
                Cross.crossMode = 2;
                Cross.x = Mouse.mouseClickX;
                Cross.crossCycle = 0;
                Protocol.outboundBuffer.pIsaac1(218);
                Protocol.outboundBuffer.p2_alt1(a);
            }
        }
        @Pc(1955) int varp;
        if (actionCode == LOGOUT_ACTION) {
            Protocol.outboundBuffer.pIsaac1(10);
            Protocol.outboundBuffer.p4(local19);
            com = InterfaceList.getComponent(local19);
            if (com.scripts != null && com.scripts[0][0] == 5) {
                varp = com.scripts[0][1];
                if (VarpDomain.activeVarps[varp] != com.scriptOperand[0]) {
                    VarpDomain.activeVarps[varp] = com.scriptOperand[0];
                    VarpDomain.refreshMagicVarp(varp);
                }
            }
        }
        if (actionCode == OBJ_NPC_ACTION) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossMode = 2;
                Cross.crossCycle = 0;
                Cross.y = Mouse.mouseClickY;
                Cross.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(115);
                Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
                Protocol.outboundBuffer.p2_alt1(anInt4370);
                Protocol.outboundBuffer.p2_alt1(a);
                Protocol.outboundBuffer.p2_alt3(anInt4997);
            }
        }
        if (actionCode == LOGOUT_ACTION_2) {
            Protocol.outboundBuffer.pIsaac1(10);
            Protocol.outboundBuffer.p4(local19);
            com = InterfaceList.getComponent(local19);
            if (com.scripts != null && com.scripts[0][0] == 5) {
                varp = com.scripts[0][1];
                VarpDomain.activeVarps[varp] = 1 - VarpDomain.activeVarps[varp];
                VarpDomain.refreshMagicVarp(varp);
            }
        }
        if (actionCode == OBJ_OBJSTACK_ACTION) {
            local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            if (!local560) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            }
            Cross.x = Mouse.mouseClickX;
            Cross.crossCycle = 0;
            Cross.y = Mouse.mouseClickY;
            Cross.crossMode = 2;
            Protocol.outboundBuffer.pIsaac1(101);
            Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
            Protocol.outboundBuffer.p2_alt1(anInt4370);
            Protocol.outboundBuffer.p2_alt1(anInt4997);
            Protocol.outboundBuffer.p2_alt1(a);
            Protocol.outboundBuffer.p2_alt3(Camera.originZ + local19);
            Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
        }
        if (actionCode == LOC_ACTION_EXAMINE) {
            Cross.crossCycle = 0;
            Cross.x = Mouse.mouseClickX;
            Cross.crossMode = 2;
            Cross.y = Mouse.mouseClickY;
            Protocol.outboundBuffer.pIsaac1(94);
            Protocol.outboundBuffer.p2_alt3(a);
        }
        if (actionCode == UNKNOWN_11) {
            if (a == 0) {
                anInt3096 = 1;
                method3556(Player.plane, local15, local19);
            } else if (a == 1) {
                Protocol.outboundBuffer.pIsaac1(131);
                Protocol.outboundBuffer.p4_alt3(anInt2512);
                Protocol.outboundBuffer.p2_alt2(Camera.originX + local15);
                Protocol.outboundBuffer.p2_alt3(anInt506);
                Protocol.outboundBuffer.p2_alt2(local19 + Camera.originZ);
            }
        }
        if (actionCode == UNKNOWN_8) {
            com = InterfaceList.getComponent(local19);
            @Pc(2287) boolean local2287 = true;
            if (com.contentType > 0) {
                local2287 = method4265(com);
            }
            if (local2287) {
                Protocol.outboundBuffer.pIsaac1(10);
                Protocol.outboundBuffer.p4(local19);
            }
        }
        if (actionCode == OBJ_PLAYER_ACTION) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossCycle = 0;
                Cross.y = Mouse.mouseClickY;
                Cross.crossMode = 2;
                Cross.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(248);
                Protocol.outboundBuffer.p2_alt3(a);
                Protocol.outboundBuffer.p2(anInt4997);
                Protocol.outboundBuffer.p2(anInt4370);
                Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
            }
        }
        if (actionCode == UNKNOWN_7) {
            Protocol.outboundBuffer.pIsaac1(85);
            Protocol.outboundBuffer.p4rme(local19);
            Protocol.outboundBuffer.p2(local15);
            Protocol.outboundBuffer.p2_alt2(a);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == UNKNOWN_24) {
            if (client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            } else {
                local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                if (!local560) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                }
            }
            Cross.crossMode = 2;
            Cross.y = Mouse.mouseClickY;
            Cross.x = Mouse.mouseClickX;
            Cross.crossCycle = 0;
            Protocol.outboundBuffer.pIsaac1(48);
            Protocol.outboundBuffer.p2_alt2(local15 + Camera.originX);
            Protocol.outboundBuffer.p2_alt3(a);
            Protocol.outboundBuffer.p2_alt1(Camera.originZ + local19);
        }
        if (actionCode == COMPONENT_LOC_ACTION && PathFinder.findPathToLoc(local31, local19, local15)) {
            Protocol.outboundBuffer.pIsaac1(233);
            Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
            Protocol.outboundBuffer.p2_alt2(Camera.originX + local15);
            Protocol.outboundBuffer.p2_alt3(anInt506);
            Protocol.outboundBuffer.p4rme(anInt2512);
            Protocol.outboundBuffer.p2_alt2((int) (local31 >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == UNKNOWN_13) {
            Protocol.outboundBuffer.pIsaac1(6);
            Protocol.outboundBuffer.p4(local19);
            Protocol.outboundBuffer.p2_alt2(local15);
            Protocol.outboundBuffer.p2_alt1(a);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == PLAYER_ACTION_5) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossMode = 2;
                Cross.y = Mouse.mouseClickY;
                Cross.x = Mouse.mouseClickX;
                Cross.crossCycle = 0;
                Protocol.outboundBuffer.pIsaac1(175);
                Protocol.outboundBuffer.p2_alt2(a);
            }
        }
        if (actionCode == UNKNOWN_22) {
            method1294();
            com = InterfaceList.getComponent(local19);
            MiniMap.anInt5062 = local19;
            anInt4370 = local15;
            anInt5014 = 1;
            anInt4997 = a;
            InterfaceList.redraw(com);
            aClass100_203 = JString.concatenate(new JString[] {aClass100_32, ObjTypeList.get(a).name, WHITE});
            if (aClass100_203 == null) {
                aClass100_203 = NULL;
            }
            return;
        }
        if (actionCode == LOC_ACTION_2) {
            PathFinder.findPathToLoc(local31, local19, local15);
            Protocol.outboundBuffer.pIsaac1(194);
            Protocol.outboundBuffer.p2_alt3(local19 + Camera.originZ);
            Protocol.outboundBuffer.p2_alt1(Camera.originX + local15);
            Protocol.outboundBuffer.p2((int) (local31 >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == UNKNOWN_48) {
            Protocol.outboundBuffer.pIsaac1(154);
            Protocol.outboundBuffer.p2_alt1(local15);
            Protocol.outboundBuffer.p4rme(local19);
            Protocol.outboundBuffer.p2_alt3(a);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == PLAYER_ACTION_1) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.crossCycle = 0;
                Cross.x = Mouse.mouseClickX;
                Cross.y = Mouse.mouseClickY;
                Cross.crossMode = 2;
                Protocol.outboundBuffer.pIsaac1(68);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        if (actionCode == OBJ_IN_COMPONENT_ACTION_4) {
            Protocol.outboundBuffer.pIsaac1(153);
            Protocol.outboundBuffer.p4_alt1(local19);
            Protocol.outboundBuffer.p2_alt1(local15);
            Protocol.outboundBuffer.p2_alt1(a);
            anInt2043 = 0;
            pressedInventoryComponent = InterfaceList.getComponent(local19);
            anInt5444 = local15;
        }
        if (actionCode == COMPONENT_OBJSTACK_ACTION) {
            local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            if (!local560) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            }
            Cross.y = Mouse.mouseClickY;
            Cross.x = Mouse.mouseClickX;
            Cross.crossMode = 2;
            Cross.crossCycle = 0;
            Protocol.outboundBuffer.pIsaac1(73);
            Protocol.outboundBuffer.p4rme(anInt2512);
            Protocol.outboundBuffer.p2(Camera.originZ + local19);
            Protocol.outboundBuffer.p2_alt3(a);
            Protocol.outboundBuffer.p2_alt3(local15 + Camera.originX);
            Protocol.outboundBuffer.p2_alt1(anInt506);
        }
        if (actionCode == UNKNOWN_12) {
            Protocol.outboundBuffer.pIsaac1(82);
            Protocol.outboundBuffer.p2(anInt506);
            Protocol.outboundBuffer.p4rme(local19);
            Protocol.outboundBuffer.p4(anInt2512);
            Protocol.outboundBuffer.p2_alt3(local15);
        }
        if (actionCode == UNKNOWN_36) {
            if (a == 0) {
                Protocol.anInt4422 = 1;
                method3556(Player.plane, local15, local19);
            } else if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                Cheat.teleport(local15 + Camera.originX, Camera.originZ - -local19, Player.plane);
            } else {
                Protocol.outboundBuffer.pIsaac1(179);
                Protocol.outboundBuffer.p2(local19 + Camera.originZ);
                Protocol.outboundBuffer.p2(local15 + Camera.originX);
            }
        }
        if (actionCode == UNKNOWN_6) {
            player = PlayerList.players[a];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.y = Mouse.mouseClickY;
                Cross.crossCycle = 0;
                Cross.crossMode = 2;
                Cross.x = Mouse.mouseClickX;
                Protocol.outboundBuffer.pIsaac1(106);
                Protocol.outboundBuffer.p2(a);
            }
        }
        if (actionCode == OBJSTACK_ACTION_2) {
            if (client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
            } else {
                local560 = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, local15, 0, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                if (!local560) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, local15, 1, 0, 2, local19, PlayerList.self.movementQueueX[0]);
                }
            }
            Cross.y = Mouse.mouseClickY;
            Cross.crossCycle = 0;
            Cross.x = Mouse.mouseClickX;
            Cross.crossMode = 2;
            Protocol.outboundBuffer.pIsaac1(33);
            Protocol.outboundBuffer.p2(a);
            Protocol.outboundBuffer.p2(Camera.originX + local15);
            Protocol.outboundBuffer.p2_alt1(Camera.originZ + local19);
        }
        if (actionCode == NPC_ACTION_2) {
            npc = NpcList.npcs[a];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Cross.x = Mouse.mouseClickX;
                Cross.crossCycle = 0;
                Cross.y = Mouse.mouseClickY;
                Cross.crossMode = 2;
                Protocol.outboundBuffer.pIsaac1(3);
                Protocol.outboundBuffer.p2_alt3(a);
            }
        }
        if (anInt5014 != 0) {
            anInt5014 = 0;
            InterfaceList.redraw(InterfaceList.getComponent(MiniMap.anInt5062));
        }
        if (aBoolean302) {
            method1294();
        }
        if (pressedInventoryComponent != null && anInt2043 == 0) {
            InterfaceList.redraw(pressedInventoryComponent);
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
    public static void addEntries(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
        @Pc(15) int local15;
        @Pc(47) int local47;
        if (anInt5014 == 0) {
            @Pc(13) int local13 = Rasterizer.screenUpperY;
            local15 = Rasterizer.screenLowerY;
            @Pc(17) int local17 = Rasterizer.screenUpperX;
            @Pc(19) int local19 = Rasterizer.screenLowerX;
            @Pc(33) int local33 = (arg5 - arg3) * (local17 - local19) / arg1 + local19;
            local47 = local15 + (local13 - local15) * (arg4 - arg0) / arg2;
            if (aBoolean302 && (anInt4999 & 0x40) != 0) {
                @Pc(61) Component local61 = InterfaceList.method1418(anInt2512, anInt506);
                if (local61 == null) {
                    method1294();
                } else {
                    addActionRow(anInt5393, 0L, aClass100_961, local33, (short) 11, aClass100_545, local47);
                }
            } else {
                if (client.game == 1) {
                    addActionRow(-1, 0L, JString.EMPTY, local33, (short) 36, LocalizedText.FACEHERE, local47);
                }
                addActionRow(-1, 0L, JString.EMPTY, local33, (short) 60, walkText, local47);
            }
        }
        @Pc(112) long local112 = -1L;
        for (local15 = 0; local15 < anInt7; local15++) {
            @Pc(121) long local121 = Model.aLongArray11[local15];
            local47 = (int) local121 & 0x7F;
            @Pc(133) int local133 = (int) local121 >> 29 & 0x3;
            @Pc(140) int local140 = (int) (local121 >>> 32) & Integer.MAX_VALUE;
            @Pc(147) int local147 = (int) local121 >> 7 & 0x7F;
            if (local121 != local112) {
                local112 = local121;
                @Pc(240) int local240;
                if (local133 == 2 && SceneGraph.isLocValid(Player.plane, local47, local147, local121)) {
                    @Pc(172) LocType local172 = LocTypeList.get(local140);
                    if (local172.multiloc != null) {
                        local172 = local172.getMultiLoc();
                    }
                    if (local172 == null) {
                        continue;
                    }
                    if (anInt5014 == 1) {
                        addActionRow(MiniMap.anInt4075, local121, JString.concatenate(new JString[] {aClass100_203, aClass100_164, local172.name}), local47, (short) 14, LocalizedText.USE, local147);
                    } else if (aBoolean302) {
                        @Pc(363) ParamType local363 = anInt3039 == -1 ? null : ParamTypeList.get(anInt3039);
                        if ((anInt4999 & 0x4) != 0 && (local363 == null || local172.getParam(local363.defaultInt, anInt3039) != local363.defaultInt)) {
                            addActionRow(anInt5393, local121, JString.concatenate(new JString[] {aClass100_466, aClass100_164, local172.name}), local47, (short) 38, aClass100_545, local147);
                        }
                    } else {
                        @Pc(228) JString[] local228 = local172.op;
                        if (aBoolean237) {
                            local228 = annotateOps(local228);
                        }
                        if (local228 != null) {
                            for (local240 = 4; local240 >= 0; local240--) {
                                if (local228[local240] != null) {
                                    @Pc(254) short local254 = 0;
                                    if (local240 == 0) {
                                        local254 = 42;
                                    }
                                    if (local240 == 1) {
                                        local254 = 50;
                                    }
                                    @Pc(268) int local268 = -1;
                                    if (local240 == 2) {
                                        local254 = 49;
                                    }
                                    if (local172.cursor1op == local240) {
                                        local268 = local172.cursor1;
                                    }
                                    if (local240 == 3) {
                                        local254 = 46;
                                    }
                                    if (local240 == local172.cursor2op) {
                                        local268 = local172.cursor2;
                                    }
                                    if (local240 == 4) {
                                        local254 = 1001;
                                    }
                                    addActionRow(local268, local121, JString.concatenate(new JString[] {aClass100_1008, local172.name}), local47, local254, local228[local240], local147);
                                }
                            }
                        }
                        addActionRow(MiniMap.anInt5073, (long) local172.id, JString.concatenate(new JString[] {aClass100_1008, local172.name}), local47, (short) 1004, LocalizedText.EXAMINE, local147);
                    }
                }
                @Pc(514) int local514;
                @Pc(526) int local526;
                @Pc(479) int local479;
                @Pc(493) int local493;
                @Pc(502) Npc local502;
                @Pc(597) Player local597;
                if (local133 == 1) {
                    @Pc(421) Npc local421 = NpcList.npcs[local140];
                    if ((local421.type.size & 0x1) == 0 && (local421.xFine & 0x7F) == 0 && (local421.zFine & 0x7F) == 0 || (local421.type.size & 0x1) == 1 && (local421.xFine & 0x7F) == 64 && (local421.zFine & 0x7F) == 64) {
                        local479 = local421.xFine + 64 - local421.type.size * 64;
                        local240 = local421.zFine - (local421.type.size - 1) * 64;
                        for (local493 = 0; local493 < NpcList.npcCount; local493++) {
                            local502 = NpcList.npcs[NpcList.npcIds[local493]];
                            local514 = local502.xFine + 64 - local502.type.size * 64;
                            local526 = local502.zFine + 64 - local502.type.size * 64;
                            if (local502 != null && local421 != local502 && local514 >= local479 && local421.type.size - (local514 - local479 >> 7) >= local502.type.size && local240 <= local526 && local502.type.size <= local421.type.size - (local526 - local240 >> 7)) {
                                addNpcEntries(local502.type, local47, NpcList.npcIds[local493], local147);
                            }
                        }
                        for (local493 = 0; local493 < PlayerList.playerCount; local493++) {
                            local597 = PlayerList.players[PlayerList.playerIds[local493]];
                            local514 = local597.xFine + 64 - local597.getSize() * 64;
                            local526 = local597.zFine + 64 - local597.getSize() * 64;
                            if (local597 != null && local514 >= local479 && local597.getSize() <= local421.type.size - (local514 - local479 >> 7) && local526 >= local240 && local597.getSize() <= local421.type.size - (local526 - local240 >> 7)) {
                                addPlayerEntries(PlayerList.playerIds[local493], local147, local597, local47);
                            }
                        }
                    }
                    addNpcEntries(local421.type, local47, local140, local147);
                }
                if (local133 == 0) {
                    @Pc(688) Player local688 = PlayerList.players[local140];
                    if ((local688.xFine & 0x7F) == 64 && (local688.zFine & 0x7F) == 64) {
                        local479 = local688.xFine - (local688.getSize() - 1) * 64;
                        local240 = local688.zFine + 64 - local688.getSize() * 64;
                        for (local493 = 0; local493 < NpcList.npcCount; local493++) {
                            local502 = NpcList.npcs[NpcList.npcIds[local493]];
                            local514 = local502.xFine + 64 - local502.type.size * 64;
                            local526 = local502.zFine + 64 - local502.type.size * 64;
                            if (local502 != null && local514 >= local479 && local502.type.size <= local688.getSize() - (local514 - local479 >> 7) && local526 >= local240 && local502.type.size <= local688.getSize() - (local526 - local240 >> 7)) {
                                addNpcEntries(local502.type, local47, NpcList.npcIds[local493], local147);
                            }
                        }
                        for (local493 = 0; local493 < PlayerList.playerCount; local493++) {
                            local597 = PlayerList.players[PlayerList.playerIds[local493]];
                            local514 = local597.xFine - (local597.getSize() - 1) * 64;
                            local526 = local597.zFine + 64 - local597.getSize() * 64;
                            if (local597 != null && local597 != local688 && local479 <= local514 && local597.getSize() <= local688.getSize() - (local514 - local479 >> 7) && local526 >= local240 && local597.getSize() <= local688.getSize() - (local526 - local240 >> 7)) {
                                addPlayerEntries(PlayerList.playerIds[local493], local147, local597, local47);
                            }
                        }
                    }
                    addPlayerEntries(local140, local147, local688, local47);
                }
                if (local133 == 3) {
                    @Pc(931) LinkedList local931 = SceneGraph.objStacks[Player.plane][local47][local147];
                    if (local931 != null) {
                        for (@Pc(940) ObjStackNode local940 = (ObjStackNode) local931.tail(); local940 != null; local940 = (ObjStackNode) local931.prev()) {
                            local240 = local940.value.type;
                            @Pc(951) ObjType local951 = ObjTypeList.get(local240);
                            if (anInt5014 == 1) {
                                addActionRow(MiniMap.anInt4075, (long) local240, JString.concatenate(new JString[] {aClass100_203, aClass100_947, local951.name}), local47, (short) 33, LocalizedText.USE, local147);
                            } else if (aBoolean302) {
                                @Pc(1142) ParamType local1142 = anInt3039 == -1 ? null : ParamTypeList.get(anInt3039);
                                if ((anInt4999 & 0x1) != 0 && (local1142 == null || local951.getParam(local1142.defaultInt, anInt3039) != local1142.defaultInt)) {
                                    addActionRow(anInt5393, (long) local240, JString.concatenate(new JString[] {aClass100_466, aClass100_947, local951.name}), local47, (short) 39, aClass100_545, local147);
                                }
                            } else {
                                @Pc(997) JString[] local997 = local951.groundOptions;
                                if (aBoolean237) {
                                    local997 = annotateOps(local997);
                                }
                                for (local514 = 4; local514 >= 0; local514--) {
                                    if (local997 != null && local997[local514] != null) {
                                        @Pc(1025) byte local1025 = 0;
                                        if (local514 == 0) {
                                            local1025 = 21;
                                        }
                                        if (local514 == 1) {
                                            local1025 = 34;
                                        }
                                        @Pc(1041) int local1041 = -1;
                                        if (local514 == local951.cursor1Op) {
                                            local1041 = local951.cursor1;
                                        }
                                        if (local514 == 2) {
                                            local1025 = 18;
                                        }
                                        if (local951.cursor2Op == local514) {
                                            local1041 = local951.cursor2;
                                        }
                                        if (local514 == 3) {
                                            local1025 = 20;
                                        }
                                        if (local514 == 4) {
                                            local1025 = 24;
                                        }
                                        addActionRow(local1041, (long) local240, JString.concatenate(new JString[] {aClass100_32, local951.name}), local47, local1025, local997[local514], local147);
                                    }
                                }
                                addActionRow(MiniMap.anInt5073, (long) local240, JString.concatenate(new JString[] {aClass100_32, local951.name}), local47, (short) 1002, LocalizedText.EXAMINE, local147);
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Lclient!me;IIII)V")
    public static void addNpcEntries(@OriginalArg(0) NpcType npc, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
        if (menuActionRow >= 400) {
            return;
        }
        if (npc.multiNpcs != null) {
            npc = npc.getMultiNPC();
        }
        if (npc == null || !npc.interactive) {
            return;
        }
        @Pc(35) JString tooltip = npc.name;
        if (npc.combatLevel != 0) {
            @Pc(47) JString string = client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
            tooltip = JString.concatenate(new JString[] { tooltip, getCombatLevelColorTag(npc.combatLevel, PlayerList.self.combatLevel), OPEN_PARENTHESIS, string, JString.parseInt(npc.combatLevel), CLOSE_PARENTHESIS});
        }
        if (anInt5014 == 1) {
            addActionRow(MiniMap.anInt4075, (long) arg2, JString.concatenate(new JString[] {aClass100_203, aClass100_407, tooltip }), arg1, (short) 26, LocalizedText.USE, arg3);
        } else if (aBoolean302) {
            @Pc(378) ParamType local378 = anInt3039 == -1 ? null : ParamTypeList.get(anInt3039);
            if ((anInt4999 & 0x2) != 0 && (local378 == null || npc.getParam(anInt3039, local378.defaultInt) != local378.defaultInt)) {
                addActionRow(anInt5393, (long) arg2, JString.concatenate(new JString[] {aClass100_466, aClass100_407, tooltip }), arg1, (short) 45, aClass100_545, arg3);
            }
        } else {
            @Pc(129) JString[] spellSelected = npc.ops;
            if (aBoolean237) {
                spellSelected = annotateOps(spellSelected);
            }
            @Pc(140) int op;
            if (spellSelected != null) {
                for (op = 4; op >= 0; op--) {
                    if (spellSelected[op] != null && (client.game != 0 || !spellSelected[op].equalsIgnoreCase(LocalizedText.ATTACK))) {
                        @Pc(161) byte local161 = 0;
                        if (op == 0) {
                            local161 = 17;
                        }
                        if (op == 1) {
                            local161 = 16;
                        }
                        @Pc(176) int local176 = -1;
                        if (op == 2) {
                            local161 = 4;
                        }
                        if (op == 3) {
                            local161 = 19;
                        }
                        if (npc.cursor1Op == op) {
                            local176 = npc.cursor1;
                        }
                        if (op == npc.cursor2Op) {
                            local176 = npc.cursor2;
                        }
                        if (op == 4) {
                            local161 = 2;
                        }
                        addActionRow(local176, (long) arg2, JString.concatenate(new JString[] {YELLOW2, tooltip }), arg1, local161, spellSelected[op], arg3);
                    }
                }
            }
            if (client.game == 0 && spellSelected != null) {
                for (op = 4; op >= 0; op--) {
                    if (spellSelected[op] != null && spellSelected[op].equalsIgnoreCase(LocalizedText.ATTACK)) {
                        @Pc(271) short action = 0;
                        if (npc.combatLevel > PlayerList.self.combatLevel) {
                            action = 2000; //THIS iS FOR LEFT CLICK ATTACK
                        }
                        @Pc(281) short menuOption = 0;
                        if (op == 0) {
                            menuOption = 17;
                        }
                        if (op == 1) {
                            menuOption = 16;
                        }
                        if (op == 2) {
                            menuOption = 4;
                        }
                        if (op == 3) {
                            menuOption = 19;
                        }
                        if (op == 4) {
                            menuOption = 2;
                        }
                        if (menuOption != 0) {
                            menuOption += action;
                        }
                        addActionRow(npc.attackCursor, (long) arg2, JString.concatenate(new JString[] {YELLOW2, tooltip }), arg1, menuOption, spellSelected[op], arg3);
                    }
                }
            }
            addActionRow(MiniMap.anInt5073, (long) arg2, JString.concatenate(new JString[] {YELLOW2, tooltip }), arg1, (short) 1007, LocalizedText.EXAMINE, arg3);
        }
    }

    @OriginalMember(owner = "runetek4.client!rj", name = "a", descriptor = "(IIILclient!e;I)V")
    public static void addPlayerEntries(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Player other, @OriginalArg(4) int arg3) {
        if (PlayerList.self == other || menuActionRow >= 400) {
            return;
        }
        @Pc(158) JString string;
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
            @Pc(95) JString local95 = client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
            if (other.combatLevel < other.combatLevelWithSummoning) {
                string = JString.concatenate(new JString[] { other.getUsername(), markCombatDifference ? getCombatLevelColorTag(other.combatLevel, PlayerList.self.combatLevel) : WHITE, OPEN_PARENTHESIS, local95, JString.parseInt(other.combatLevel), PLUS, JString.parseInt(other.combatLevelWithSummoning - other.combatLevel), CLOSE_PARENTHESIS});
            } else {
                string = JString.concatenate(new JString[] { other.getUsername(), markCombatDifference ? getCombatLevelColorTag(other.combatLevel, PlayerList.self.combatLevel) : WHITE, OPEN_PARENTHESIS, local95, JString.parseInt(other.combatLevel), CLOSE_PARENTHESIS});
            }
        } else {
            string = JString.concatenate(new JString[] { other.getUsername(), OPEN_PARENTHESIS, LocalizedText.SKILL, JString.parseInt(other.skill), CLOSE_PARENTHESIS});
        }
        @Pc(275) int local275;
        if (anInt5014 == 1) {
            addActionRow(MiniMap.anInt4075, (long) arg0, JString.concatenate(new JString[] {aClass100_203, aClass100_561, string }), arg3, (short) 1, LocalizedText.USE, arg1);
        } else if (!aBoolean302) {
            for (local275 = 7; local275 >= 0; local275--) {
                if (Player.options[local275] != null) {
                    @Pc(291) short local291 = 0;
                    if (client.game == 0 && Player.options[local275].equalsIgnoreCase(LocalizedText.ATTACK)) {
                        if (other.combatLevel > PlayerList.self.combatLevel) {
                            local291 = 2000;
                        }
                        if (PlayerList.self.teamId != 0 && other.teamId != 0) {
                            if (PlayerList.self.teamId == other.teamId) {
                                local291 = 2000;
                            } else {
                                local291 = 0;
                            }
                        }
                    } else if (Player.secondaryOptions[local275]) {
                        local291 = 2000;
                    }
                    @Pc(353) short local353 = aShortArray2[local275];
                    @Pc(358) short local358 = (short) (local353 + local291);
                    addActionRow(Player.cursors[local275], (long) arg0, JString.concatenate(new JString[] {WHITE, string }), arg3, local358, Player.options[local275], arg1);
                }
            }
        } else if ((anInt4999 & 0x8) != 0) {
            addActionRow(anInt5393, (long) arg0, JString.concatenate(new JString[] {aClass100_466, aClass100_561, string }), arg3, (short) 15, aClass100_545, arg1);
        }
        for (local275 = 0; local275 < menuActionRow; local275++) {
            if (actions[local275] == 60) {
                opBases[local275] = JString.concatenate(new JString[] {WHITE, string });
                break;
            }
        }
    }

    @OriginalMember(owner = "client!bc", name = "f", descriptor = "(B)Lclient!na;")
    public static JString method471() {
        @Pc(32) JString local32;
        if (anInt5014 == 1 && menuActionRow < 2) {
            local32 = JString.concatenate(new JString[] { LocalizedText.USE, LocalizedText.MINISEPARATOR, aClass100_203, aClass100_961});
        } else if (aBoolean302 && menuActionRow < 2) {
            local32 = JString.concatenate(new JString[] {aClass100_545, LocalizedText.MINISEPARATOR, aClass100_466, aClass100_961});
        } else if (Cheat.shiftClick && Keyboard.pressedKeys[81] && menuActionRow > 2) {
            local32 = getOp(menuActionRow - 2);
        } else {
            local32 = getOp(menuActionRow - 1);
        }
        if (menuActionRow > 2) {
            local32 = JString.concatenate(new JString[] { local32, aClass100_2, JString.parseInt(menuActionRow - 2), LocalizedText.MOREOPTIONS});
        }
        return local32;
    }

    @OriginalMember(owner = "runetek4.client!il", name = "a", descriptor = "(III)V")
    public static void method3556(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        aBoolean187 = true;
        anInt3902 = arg0;
        anInt2388 = arg1;
        anInt3259 = arg2;
        clickTileX = -1;
        anInt2954 = -1;
    }

    @OriginalMember(owner = "runetek4.client!wi", name = "c", descriptor = "(II)Z")
    public static boolean menuHasAddFriend(@OriginalArg(0) int arg0) {
        if (arg0 < 0) {
            return false;
        }
        @Pc(12) int local12 = actions[arg0];
        if (local12 >= 2000) {
            local12 -= 2000;
        }
        return local12 == 1003;
    }

    @OriginalMember(owner = "runetek4.client!ub", name = "b", descriptor = "(IIIIIII)V")
    public static void method4246(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
        @Pc(8) Component local8 = InterfaceList.method1418(arg0, arg1);
        if (local8 != null && local8.onUse != null) {
            @Pc(19) HookRequest local19 = new HookRequest();
            local19.source = local8;
            local19.arguments = local8.onUse;
            ClientScriptRunner.run(local19);
        }
        anInt506 = arg1;
        anInt3039 = arg3;
        anInt2512 = arg0;
        anInt4999 = arg2;
        aBoolean302 = true;
        anInt5393 = arg4;
        anInt1092 = arg5;
        InterfaceList.redraw(local8);
    }

    @OriginalMember(owner = "client!ej", name = "h", descriptor = "(I)V")
    public static void processMenuActions() {
        if (anInt3953 == 2) {
            if (ClientScriptRunner.anInt3751 == Mouse.anInt5850 && ClientScriptRunner.anInt1892 == Mouse.anInt5895) {
                anInt3953 = 0;
                if (Cheat.shiftClick && Keyboard.pressedKeys[Keyboard.KEY_SHIFT] && menuActionRow > 2) {
                    doAction(menuActionRow - 2);
                } else {
                    doAction(menuActionRow - 1);
                }
            }
        } else if (ClientScriptRunner.anInt3751 == Mouse.mouseClickX && ClientScriptRunner.anInt1892 == Mouse.mouseClickY) {
            anInt3953 = 0;
            if (Cheat.shiftClick && Keyboard.pressedKeys[Keyboard.KEY_SHIFT] && menuActionRow > 2) {
                doAction(menuActionRow - 2);
            } else {
                doAction(menuActionRow - 1);
            }
        } else {
            Mouse.anInt5895 = Mouse.mouseClickY;
            anInt3953 = 2;
            Mouse.anInt5850 = Mouse.mouseClickX;
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(IZI)V")
    public static void method10(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
        Protocol.outboundBuffer.pIsaac1(132);
        Protocol.outboundBuffer.p4rme(arg1);
        Protocol.outboundBuffer.p2_alt1(arg0);
    }
}
