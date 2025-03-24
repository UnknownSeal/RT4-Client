package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ObjType;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
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
    public static void addComponentEntries(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component arg2) {
        if (arg2.buttonType == 1) {
            addActionRow(-1, 0L, JString.EMPTY, 0, (short) 8, arg2.option, arg2.id);
        }
        @Pc(47) JString local47;
        if (arg2.buttonType == 2 && !aBoolean302) {
            local47 = MiniMap.getTargetVerb(arg2);
            if (local47 != null) {
                addActionRow(-1, 0L, JString.concatenate(new JString[] { GREEN, arg2.optionSuffix}), -1, (short) 32, local47, arg2.id);
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
                        InterfaceList.mouseOverInventoryInterface = arg2;
                        Static18.mouseInvInterfaceIndex = local171;
                        if (arg2.invSlotObjId[local171] > 0) {
                            @Pc(267) ServerActiveProperties local267 = InterfaceList.getServerActiveProperties(arg2);
                            @Pc(276) ObjType local276 = ObjTypeList.get(arg2.invSlotObjId[local171] - 1);
                            if (anInt5014 == 1 && local267.method510()) {
                                if (MiniMap.anInt5062 != arg2.id || anInt4370 != local171) {
                                    addActionRow(-1, (long) local276.anInt2354, JString.concatenate(new JString[] { Static34.aClass100_203, aClass100_947, local276.name}), local171, (short) 40, LocalizedText.USE, arg2.id);
                                }
                            } else if (aBoolean302 && local267.method510()) {
                                @Pc(596) Class3_Sub2_Sub12 local596 = Static121.anInt3039 == -1 ? null : Static110.method2277(Static121.anInt3039);
                                if ((Static274.anInt4999 & 0x10) != 0 && (local596 == null || local276.getParam(local596.anInt2667, Static121.anInt3039) != local596.anInt2667)) {
                                    addActionRow(anInt5393, (long) local276.anInt2354, JString.concatenate(new JString[] { Static78.aClass100_466, aClass100_947, local276.name}), local171, (short) 3, aClass100_545, arg2.id);
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
                                    addActionRow(MiniMap.anInt4075, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, (short) 22, LocalizedText.USE, arg2.id);
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
                                addActionRow(MiniMap.anInt5073, (long) local276.anInt2354, JString.concatenate(new JString[] { aClass100_32, local276.name}), local171, (short) 1006, LocalizedText.EXAMINE, arg2.id);
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
                @Pc(765) JString local765 = InterfaceList.getOp(arg2, local171);
                if (local765 != null) {
                    addActionRow(Static8.method118(local171, arg2), (long) (local171 + 1), arg2.optionBase, arg2.createdComponentId, (short) 1003, local765, arg2.id);
                }
            }
            local47 = MiniMap.getTargetVerb(arg2);
            if (local47 != null) {
                addActionRow(-1, 0L, arg2.optionBase, arg2.createdComponentId, (short) 32, local47, arg2.id);
            }
            for (local173 = 4; local173 >= 0; local173--) {
                @Pc(828) JString local828 = InterfaceList.getOp(arg2, local173);
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

    @OriginalMember(owner = "runetek4.client!qe", name = "b", descriptor = "(II)V")
    public static void remove(@OriginalArg(1) int arg0) {
        menuActionRow--;
        if (menuActionRow == arg0) {
            return;
        }
        ArrayUtils.copy(ops, arg0 + 1, ops, arg0, menuActionRow - arg0);
        ArrayUtils.copy(opBases, arg0 + 1, opBases, arg0, menuActionRow - arg0);
        ArrayUtils.copy(cursors, arg0 + 1, cursors, arg0, menuActionRow - arg0);
        ArrayUtils.copy(actions, arg0 + 1, actions, arg0, menuActionRow - arg0);
        ArrayUtils.copy(keys, arg0 + 1, keys, arg0, menuActionRow - arg0);
        ArrayUtils.copy(intArgs1, arg0 + 1, intArgs1, arg0, menuActionRow - arg0);
        ArrayUtils.copy(intArgs2, arg0 + 1, intArgs2, arg0, menuActionRow - arg0);
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
            if (client.js5Archive8.isFileReady(Static55.anInt1736) && client.js5Archive8.isFileReady(Static169.anInt4073)) {
                LoginManager.aClass3_Sub2_Sub1_1 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, Static55.anInt1736);
                LoginManager.aClass3_Sub2_Sub1_9 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, Static169.anInt4073);
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
                GlRaster.fillRectAlpha(local3, local9, local13, 20, Static40.anInt1275, 256 - Static111.anInt2910);
            } else {
                SoftwareRaster.fillRectAlpha(local3, local9, local13, 20, Static40.anInt1275, 256 - Static111.anInt2910);
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
        Fonts.b12Full.renderLeft(LocalizedText.CHOOSE_OPTION, local3 + 3, local9 + 14, Static195.anInt4581, -1);
        if (GlRenderer.enabled) {
            GlRaster.fillRectAlpha(local3, local9 + 20, local13, local11 - 20, Static40.anInt1275, 256 - Static111.anInt2910);
        } else {
            SoftwareRaster.fillRectAlpha(local3, local9 + 20, local13, local11 - 20, Static40.anInt1275, 256 - Static111.anInt2910);
        }
        local114 = Mouse.lastMouseY;
        local112 = Mouse.lastMouseX;
        @Pc(203) int local203;
        @Pc(219) int local219;
        for (local203 = 0; local203 < menuActionRow; local203++) {
            local219 = (menuActionRow - local203 - 1) * 15 + local9 + 35;
            if (local3 < local112 && local112 < local3 + local13 && local114 > local219 - 13 && local114 < local219 + 3) {
                if (GlRenderer.enabled) {
                    GlRaster.fillRectAlpha(local3, local219 - 13, local13, 16, Static251.anInt5457, 256 - Static232.anInt5208);
                } else {
                    SoftwareRaster.fillRectAlpha(local3, local219 - 13, local13, 16, Static251.anInt5457, 256 - Static232.anInt5208);
                }
            }
        }
        if ((LoginManager.aClass3_Sub2_Sub1_8 == null || LoginManager.aClass3_Sub2_Sub1_6 == null || LoginManager.aClass3_Sub2_Sub1_10 == null) && client.js5Archive8.isFileReady(Static85.anInt2261) && client.js5Archive8.isFileReady(Static136.anInt3324) && client.js5Archive8.isFileReady(Static254.anInt5556)) {
            LoginManager.aClass3_Sub2_Sub1_8 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, Static85.anInt2261);
            LoginManager.aClass3_Sub2_Sub1_6 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, Static136.anInt3324);
            LoginManager.aClass3_Sub2_Sub1_10 = com.jagex.runetek4.cache.media.SoftwareSprite.loadSoftwareAlphaSprite(client.js5Archive8, Static254.anInt5556);
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
            local418 = Static195.anInt4581;
            if (local3 < local112 && local13 + local3 > local112 && local219 - 13 < local114 && local114 < local219 + 3) {
                local418 = Static262.anInt5752;
            }
            Fonts.b12Full.renderLeft(Static269.method2228(local203), local3 + 3, local219, local418, 0);
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
            @Pc(129) int local129 = 16777215;
            if (local11 < local98 && local98 < local11 + local9 && local127 - 13 < local96 && local96 < local127 + 3) {
                local129 = 16776960;
            }
            Fonts.b12Full.renderLeft(Static269.method2228(local107), local11 + 3, local127, local129, 0);
        }
        InterfaceList.forceRedrawScreen(InterfaceList.anInt4271, InterfaceList.anInt5138, InterfaceList.anInt436, InterfaceList.anInt761);
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(Lclient!be;III)V")
    public static void method1207(@OriginalArg(0) Component arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
        if (menuActionRow < 2 && anInt5014 == 0 && !aBoolean302) {
            return;
        }
        @Pc(24) JString local24 = Static13.method471();
        if (arg0 == null) {
            @Pc(40) int local40 = Fonts.b12Full.method2859(local24, arg2 + 4, arg1 - -15, client.aRandom1, gregorianDateSeed);
            InterfaceList.redrawScreen(arg2 + 4, Fonts.b12Full.getStringWidth(local24) + local40, arg1, 15);
            return;
        }
        @Pc(59) Font local59 = arg0.getFont(Sprites.nameIcons);
        if (local59 == null) {
            local59 = Fonts.b12Full;
        }
        local59.method2878(local24, arg2, arg1, arg0.width, arg0.height, arg0.color, arg0.shadowColor, arg0.halign, arg0.valign, client.aRandom1, gregorianDateSeed, Static50.anIntArray132);
        InterfaceList.redrawScreen(Static50.anIntArray132[0], Static50.anIntArray132[2], Static50.anIntArray132[1], Static50.anIntArray132[3]);
    }
}
