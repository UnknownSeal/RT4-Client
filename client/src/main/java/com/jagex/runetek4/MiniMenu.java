package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
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

    @OriginalMember(owner = "runetek4.client!ud", name = "a", descriptor = "(ILclient!be;)Z")
    public static boolean method4265(@OriginalArg(1) Component arg0) {
        if (arg0.contentType == 205) {
            Game.idleTimeout = 250;
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
        Static35.anInt1092 = -1;
        InterfaceList.redraw(local19);
    }

    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
    public static void addActionRow(@OriginalArg(0) int arg0, @OriginalArg(1) long arg1, @OriginalArg(3) JString arg2, @OriginalArg(4) int arg3, @OriginalArg(5) short arg4, @OriginalArg(6) JString arg5, @OriginalArg(7) int arg6) {
        if (ClientScriptRunner.aBoolean108 || menuActionRow >= 500) {
            return;
        }
        ops[menuActionRow] = arg5;
        opBases[menuActionRow] = arg2;
        cursors[menuActionRow] = arg0 == -1 ? Static35.anInt1092 : arg0;
        actions[menuActionRow] = arg4;
        Static159.aLongArray5[menuActionRow] = arg1;
        Static196.anIntArray408[menuActionRow] = arg3;
        Static56.anIntArray142[menuActionRow] = arg6;
        menuActionRow++;
    }

    @OriginalMember(owner = "runetek4.client!va", name = "a", descriptor = "(IZILclient!be;)V")
    public static void addComponentEntries(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component arg2) {
        if (arg2.buttonType == 1) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 8, arg2.option, arg2.id);
        }
        @Pc(47) JString local47;
        if (arg2.buttonType == 2 && !aBoolean302) {
            local47 = Static97.method1963(arg2);
            if (local47 != null) {
                addActionRow(-1, 0L, JString.concatenate(new JString[] { Static42.GREEN, arg2.optionSuffix}), -1, (short) 32, local47, arg2.id);
            }
        }
        if (arg2.buttonType == 3) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 28, LocalizedText.CLOSE, arg2.id);
        }
        if (arg2.buttonType == 4) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 59, arg2.option, arg2.id);
        }
        if (arg2.buttonType == 5) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 51, arg2.option, arg2.id);
        }
        if (arg2.buttonType == 6 && ClientScriptRunner.aClass13_10 == null) {
            addActionRow(-1, 0L, JString.EMPTY, -1, (short) 41, arg2.option, arg2.id);
        }
        @Pc(173) int local173;
        @Pc(171) int local171;
        if (arg2.type == 2) {
            local171 = 0;
            for (local173 = 0; local173 < arg2.baseHeight; local173++) {
                for (@Pc(183) int local183 = 0; local183 < arg2.baseWidth; local183++) {
                    @Pc(195) int local195 = (arg2.invMarginX + 32) * local183;
                    @Pc(202) int local202 = (arg2.invMarginY + 32) * local173;
                    if (local171 < 20) {
                        local202 += arg2.invOffsetY[local171];
                        local195 += arg2.invOffsetX[local171];
                    }
                    if (arg1 >= local195 && local202 <= arg0 && local195 + 32 > arg1 && local202 + 32 > arg0) {
                        Static169.aClass13_18 = arg2;
                        Static18.mouseInvInterfaceIndex = local171;
                        if (arg2.invSlotObjId[local171] > 0) {
                            @Pc(267) ServerActiveProperties local267 = InterfaceList.getServerActiveProperties(arg2);
                            @Pc(276) ObjType local276 = ObjTypeList.get(arg2.invSlotObjId[local171] - 1);
                            if (anInt5014 == 1 && local267.method510()) {
                                if (MiniMap.anInt5062 != arg2.id || anInt4370 != local171) {
                                    addActionRow(-1, (long) local276.anInt2354, JString.concatenate(new JString[] { Static34.aClass100_203, Static223.aClass100_947, local276.name}), local171, (short) 40, LocalizedText.USE, arg2.id);
                                }
                            } else if (aBoolean302 && local267.method510()) {
                                @Pc(596) Class3_Sub2_Sub12 local596 = Static121.anInt3039 == -1 ? null : Static110.method2277(Static121.anInt3039);
                                if ((Static274.anInt4999 & 0x10) != 0 && (local596 == null || local276.getParam(local596.anInt2667, Static121.anInt3039) != local596.anInt2667)) {
                                    addActionRow(anInt5393, (long) local276.anInt2354, JString.concatenate(new JString[] { Static78.aClass100_466, Static223.aClass100_947, local276.name}), local171, (short) 3, aClass100_545, arg2.id);
                                }
                            } else {
                                @Pc(296) JString[] local296 = local276.interfaceOptions;
                                if (Static208.aBoolean237) {
                                    local296 = Static279.method4664(local296);
                                }
                                @Pc(309) int local309;
                                @Pc(334) byte local334;
                                if (local267.method510()) {
                                    for (local309 = 4; local309 >= 3; local309--) {
                                        if (local296 != null && local296[local309] != null) {
                                            if (local309 == 3) {
                                                local334 = 35;
                                            } else {
                                                local334 = 58;
                                            }
                                            addActionRow(-1, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, local334, local296[local309], arg2.id);
                                        }
                                    }
                                }
                                if (local267.method507()) {
                                    addActionRow(Static169.anInt4075, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, (short) 22, LocalizedText.USE, arg2.id);
                                }
                                if (local267.method510() && local296 != null) {
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
                                            addActionRow(-1, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, local334, local296[local309], arg2.id);
                                        }
                                    }
                                }
                                local296 = arg2.invOptions;
                                if (Static208.aBoolean237) {
                                    local296 = Static279.method4664(local296);
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
                                            addActionRow(-1, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, local334, local296[local309], arg2.id);
                                        }
                                    }
                                }
                                addActionRow(Static225.anInt5073, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, (short) 1006, LocalizedText.EXAMINE, arg2.id);
                            }
                        }
                    }
                    local171++;
                }
            }
        }
        if (!arg2.if3) {
            return;
        }
        if (!aBoolean302) {
            for (local171 = 9; local171 >= 5; local171--) {
                @Pc(765) JString local765 = Static205.method3677(arg2, local171);
                if (local765 != null) {
                    addActionRow(Static8.method118(local171, arg2), (long) (local171 + 1), arg2.optionBase, arg2.createdComponentId, (short) 1003, local765, arg2.id);
                }
            }
            local47 = Static97.method1963(arg2);
            if (local47 != null) {
                addActionRow(-1, 0L, arg2.optionBase, arg2.createdComponentId, (short) 32, local47, arg2.id);
            }
            for (local173 = 4; local173 >= 0; local173--) {
                @Pc(828) JString local828 = Static205.method3677(arg2, local173);
                if (local828 != null) {
                    addActionRow(Static8.method118(local173, arg2), (long) (local173 + 1), arg2.optionBase, arg2.createdComponentId, (short) 9, local828, arg2.id);
                }
            }
            if (InterfaceList.getServerActiveProperties(arg2).method506()) {
                addActionRow(-1, 0L, JString.EMPTY, arg2.createdComponentId, (short) 41, LocalizedText.CONTINUE, arg2.id);
            }
        } else if (InterfaceList.getServerActiveProperties(arg2).method508() && (Static274.anInt4999 & 0x20) != 0) {
            addActionRow(anInt5393, 0L, JString.concatenate(new JString[] { Static78.aClass100_466, Static201.aClass100_408, arg2.optionBase}), arg2.createdComponentId, (short) 12, aClass100_545, arg2.id);
        }
    }
}
