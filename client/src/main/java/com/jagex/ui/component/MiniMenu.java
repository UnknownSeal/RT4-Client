package com.jagex.ui.component;

import com.jagex.entity.player.PlayerList;
import com.jagex.scene.Camera;
import com.jagex.ui.chat.ChatHistory;
import com.jagex.client.auth.LoginManager;
import com.jagex.game.runetek4.config.npctype.NpcType;
import com.jagex.game.runetek4.config.objtype.ObjType;
import com.jagex.cache.media.Font;
import com.jagex.cache.media.SoftwareSprite;
import com.jagex.client.Client;
import com.jagex.game.runetek4.config.loctype.LocTypeList;
import com.jagex.game.runetek4.config.objtype.ObjTypeList;
import com.jagex.game.runetek4.config.paramtype.ParamType;
import com.jagex.game.runetek4.config.paramtype.ParamTypeList;
import com.jagex.game.runetek4.config.loctype.LocType;
import com.jagex.core.datastruct.LinkedList;
import com.jagex.entity.npc.Npc;
import com.jagex.entity.npc.NpcList;
import com.jagex.entity.player.Player;
import com.jagex.game.PathFinder;
import com.jagex.game.state.VarpDomain;
import com.jagex.graphics.gl.GlAlphaSprite;
import com.jagex.graphics.gl.GlRaster;
import com.jagex.graphics.gl.GlRenderer;
import com.jagex.ui.sprite.GlSprite;
import com.jagex.graphics.font.Fonts;
import com.jagex.input.Keyboard;
import com.jagex.input.Mouse;
import com.jagex.core.utils.string.JString;
import com.jagex.core.utils.string.LocalizedText;
import com.jagex.graphics.model.Model;
import com.jagex.network.ClientProt;
import com.jagex.network.Protocol;
import com.jagex.entity.loc.ClientObj;
import com.jagex.graphics.raster.Rasterizer;
import com.jagex.graphics.raster.SoftwareRenderer;
import com.jagex.scene.SceneGraph;
import com.jagex.clientscript.ClientScriptRunner;
import com.jagex.ui.sprite.SoftwareAlphaSprite;
import com.jagex.ui.sprite.Sprites;
import com.jagex.ui.events.HookRequest;
import com.jagex.core.utils.ArrayUtils;
import com.jagex.core.utils.debug.Cheat;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class MiniMenu {
    @OriginalMember(owner = "runetek4.client!se", name = "m", descriptor = "Lclient!na;")
    public static final JString aClass100_961 = JString.parse(" )2>");
    @OriginalMember(owner = "runetek4.client!hd", name = "l", descriptor = "Lclient!na;")
    public static final JString NULL = JString.parse("null");
    @OriginalMember(owner = "runetek4.client!nm", name = "bb", descriptor = "Lclient!na;")
    public static final JString COLOR_RED = JString.parse("<col=ff0000>");
    @OriginalMember(owner = "client!fb", name = "p", descriptor = "Lclient!na;")
    public static final JString COLOR_RED_ORANGE = JString.parse("<col=ff3000>");
    @OriginalMember(owner = "runetek4.client!sc", name = "D", descriptor = "Lclient!na;")
    public static final JString COLOR_ORANGE = JString.parse("<col=ff7000>");
    @OriginalMember(owner = "runetek4.client!si", name = "Z", descriptor = "Lclient!na;")
    public static final JString COLOR_AMBER = JString.parse("<col=ffb000>");
    @OriginalMember(owner = "client!ag", name = "bb", descriptor = "Lclient!na;")
    public static final JString COLOR_BRIGHT_LIMEGREEN = JString.parse("<col=40ff00>");
    @OriginalMember(owner = "client!dc", name = "v", descriptor = "Lclient!na;")
    public static final JString COLOR_YELLOW_GREEN = JString.parse("<col=c0ff00>");
    @OriginalMember(owner = "runetek4.client!sf", name = "g", descriptor = "Lclient!na;")
    public static final JString COLOR_YELLOW2 = JString.parse("<col=ffff00>");
    @OriginalMember(owner = "runetek4.client!vg", name = "f", descriptor = "Lclient!na;")
    public static final JString COLOR_SPRING_GREEN = JString.parse("<col=80ff00>");
    @OriginalMember(owner = "runetek4.client!a", name = "j", descriptor = "Lclient!na;")
    public static final JString COLOR_WHITE_BRACKET = JString.parse("<col=ffffff> )4 ");
    @OriginalMember(owner = "runetek4.client!qf", name = "Q", descriptor = "Lclient!na;")
	public static final JString YELLOW_ARROW = JString.parse(" )2> <col=ffff00>");
    @OriginalMember(owner = "client!fl", name = "V", descriptor = "Lclient!na;")
    public static final JString CLOSE_PARENTHESIS = JString.parse("(Y");
    @OriginalMember(owner = "client!gd", name = "c", descriptor = "Lclient!na;")
    public static final JString PLUS = JString.parse(")0");
    @OriginalMember(owner = "runetek4.client!jj", name = "g", descriptor = "Lclient!na;")
    public static final JString OPEN_PARENTHESIS = JString.parse(" (X");
    @OriginalMember(owner = "runetek4.client!qi", name = "B", descriptor = "Lclient!na;")
	public static final JString COLOR_WHITE2 = JString.parse("<col=ffffff>");
    @OriginalMember(owner = "client!cb", name = "ab", descriptor = "Lclient!na;")
    public static final JString CYAN_ARROW = JString.parse(" )2> <col=00ffff>");
    @OriginalMember(owner = "runetek4.client!ud", name = "Q", descriptor = "Lclient!na;")
    public static final JString MULTIPLY_SYMBOL = JString.parse(" x ");
    @OriginalMember(owner = "runetek4.client!ib", name = "k", descriptor = "Lclient!na;")
    public static final JString WHITE_ARROW = JString.parse(" )2> <col=ffffff>");
    @OriginalMember(owner = "runetek4.client!tg", name = "e", descriptor = "Lclient!na;")
    public static final JString COLOR_CYAN = JString.parse("<col=00ffff>");
    @OriginalMember(owner = "runetek4.client!uf", name = "q", descriptor = "Lclient!na;")
    public static final JString NULL_TEXT = JString.parse("Null");
    @OriginalMember(owner = "client!e", name = "pc", descriptor = "[I")
    public static final int[] textBounds = new int[4];
    @OriginalMember(owner = "runetek4.client!af", name = "l", descriptor = "[S")
    public static final short[] PLAYER_ACTION_IDS = new short[] { 30, 6, 31, 29, 10, 44, 37, 57 };
    private static final int MENU_ACTION_BUFFER_SIZE = 500;
    @OriginalMember(owner = "runetek4.client!pk", name = "bb", descriptor = "Lclient!na;")
    public static JString walkText;
    @OriginalMember(owner = "runetek4.client!em", name = "D", descriptor = "I")
    public static int gregorianDateSeed;
    @OriginalMember(owner = "runetek4.client!hj", name = "e", descriptor = "I")
    public static int useWithComponentId;
    @OriginalMember(owner = "client!be", name = "Ec", descriptor = "I")
    public static int useWithSlot = -1;
    @OriginalMember(owner = "runetek4.client!uf", name = "t", descriptor = "I")
    public static int anInt5444 = 0;
    @OriginalMember(owner = "runetek4.client!v", name = "b", descriptor = "Lclient!be;")
    public static Component pressedInventoryComponent;
    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "I")
    public static int pickedEntityCount = 0;
    @OriginalMember(owner = "runetek4.client!id", name = "k", descriptor = "I")
    public static int anInt2878;
    @OriginalMember(owner = "runetek4.client!jl", name = "v", descriptor = "I")
    public static int useItemOnTileMode = 0;
    @OriginalMember(owner = "runetek4.client!wf", name = "d", descriptor = "I")
    public static int anInt4997;
    @OriginalMember(owner = "client!fl", name = "P", descriptor = "I")
    public static int inventoryPressTimer = 0;
    @OriginalMember(owner = "runetek4.client!ml", name = "Q", descriptor = "I")
    public static int menuState = 0;

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
    public static final int LOC_ACTION_6 = 1004;
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
    public static final int RESUME_STRINGDIALOG = 48;
    public static final int RESUME_OBJDIALOG = 12;
    public static final int UNKNOWN_36 = 36;
    public static final int UNKNOWN_6 = 6;
    public static final int UNKNOWN_24 = 24;
    public static final int OBJ_ACTION_3 = 7;
    public static final int LOGOUT_3 = 8;
    public static final int COMPONENT_ACTION = 11;
    public static final int PLAYER_ACTION_4 = 32;
    public static final int OBJ_ACTION_2 = 21;
    public static final int UNKNOWN_9 = 9;
    public static final int UNKNOWN_1003 = 1003;
    public static final int UNKNOWN_41 = 41;
    public static final int PLAYER_ACTION_3 = 10;
    public static final int UNKNOWN_44 = 44;

    public static final int ACTION_PRIORITY_OFFSET = 2000;
    public static final int EXAMINE_ACTION_THRESHOLD = 1000;
    private static final int MAX_MENU_ACTIONS = 400;
    private static final int COLOR_WHITE = 16777215;
    private static final int COLOR_YELLOW = 16776960;
    private static final int TARGET_MASK_OBJSTACK = 0x1;
    private static final int TARGET_MASK_NPC = 0x2;
    private static final int TARGET_MASK_LOC = 0x4;
    private static final int TARGET_MASK_PLAYER = 0x8;
    private static final int TARGET_MASK_TILE = 0x40;

    @OriginalMember(owner = "client!ef", name = "g", descriptor = "I")
    public static int clickTileX = -1;
    @OriginalMember(owner = "runetek4.client!ha", name = "q", descriptor = "I")
    public static int targetX = 0;
    @OriginalMember(owner = "runetek4.client!jb", name = "p", descriptor = "I")
    public static int clickTileZ = -1;
    @OriginalMember(owner = "runetek4.client!kd", name = "zb", descriptor = "I")
    public static int targetZ = 0;
    @OriginalMember(owner = "runetek4.client!mh", name = "Y", descriptor = "Z")
    public static boolean walkTargetActive = false;
    @OriginalMember(owner = "runetek4.client!mj", name = "i", descriptor = "I")
    public static int targetPlane = 0;
    @OriginalMember(owner = "runetek4.client!em", name = "z", descriptor = "Z")
    public static boolean open = false;
    @OriginalMember(owner = "runetek4.client!sk", name = "kb", descriptor = "I")
    public static int menuActionRow = 0;

    @OriginalMember(owner = "runetek4.client!ud", name = "a", descriptor = "(ILclient!be;)Z")
    public static boolean shouldTriggerIdleTimeout(@OriginalArg(1) Component component) {
        if (component.clientcode == 205) {
            Protocol.idleTimeout = 250;
            return true;
        } else {
            return false;
        }
    }

    @OriginalMember(owner = "runetek4.client!qe", name = "b", descriptor = "(II)V")
    public static void removeActionRow(@OriginalArg(1) int index) {
        menuActionRow--;
        if (menuActionRow == index) {
            return;
        }
        ArrayUtils.copy(InterfaceManager.ops, index + 1, InterfaceManager.ops, index, menuActionRow - index);
        ArrayUtils.copy(InterfaceManager.opBases, index + 1, InterfaceManager.opBases, index, menuActionRow - index);
        ArrayUtils.copy(InterfaceManager.cursors, index + 1, InterfaceManager.cursors, index, menuActionRow - index);
        ArrayUtils.copy(InterfaceManager.actions, index + 1, InterfaceManager.actions, index, menuActionRow - index);
        ArrayUtils.copy(InterfaceManager.keys, index + 1, InterfaceManager.keys, index, menuActionRow - index);
        ArrayUtils.copy(InterfaceManager.intArgs1, index + 1, InterfaceManager.intArgs1, index, menuActionRow - index);
        ArrayUtils.copy(InterfaceManager.intArgs2, index + 1, InterfaceManager.intArgs2, index, menuActionRow - index);
    }

    @OriginalMember(owner = "runetek4.client!wl", name = "b", descriptor = "(I)V")
    public static void sortMenuActions() {
        @Pc(3) boolean hasSwapped = false;
        while (!hasSwapped) {
            hasSwapped = true;
            for (@Pc(13) int index = 0; index < menuActionRow - 1; index++) {
                if (InterfaceManager.actions[index] < EXAMINE_ACTION_THRESHOLD && InterfaceManager.actions[index + 1] > EXAMINE_ACTION_THRESHOLD) {
                    @Pc(41) JString tempOpBase = InterfaceManager.opBases[index];
                    hasSwapped = false;
                    InterfaceManager.opBases[index] = InterfaceManager.opBases[index + 1];
                    InterfaceManager.opBases[index + 1] = tempOpBase;
                    @Pc(61) JString tempOp = InterfaceManager.ops[index];
                    InterfaceManager.ops[index] = InterfaceManager.ops[index + 1];
                    InterfaceManager.ops[index + 1] = tempOp;
                    @Pc(79) int tempArg1 = InterfaceManager.intArgs1[index];
                    InterfaceManager.intArgs1[index] = InterfaceManager.intArgs1[index + 1];
                    InterfaceManager.intArgs1[index + 1] = tempArg1;
                    @Pc(97) int tempArg2 = InterfaceManager.intArgs2[index];
                    InterfaceManager.intArgs2[index] = InterfaceManager.intArgs2[index + 1];
                    InterfaceManager.intArgs2[index + 1] = tempArg2;
                    @Pc(115) int tempCursor = InterfaceManager.cursors[index];
                    InterfaceManager.cursors[index] = InterfaceManager.cursors[index + 1];
                    InterfaceManager.cursors[index + 1] = tempCursor;
                    @Pc(133) short tempAction = InterfaceManager.actions[index];
                    InterfaceManager.actions[index] = InterfaceManager.actions[index + 1];
                    InterfaceManager.actions[index + 1] = tempAction;
                    @Pc(151) long tempKey = InterfaceManager.keys[index];
                    InterfaceManager.keys[index] = InterfaceManager.keys[index + 1];
                    InterfaceManager.keys[index + 1] = tempKey;
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!ij", name = "a", descriptor = "(B)V")
    public static void drawContextMenu() {
        @Pc(3) int menuX = InterfaceManager.menuX;
        @Pc(9) int menuY = InterfaceManager.menuY;
        @Pc(11) int menuHeight = InterfaceManager.menuHeight;
        @Pc(13) int menuWidth = InterfaceManager.menuWidth;
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
        InterfaceManager.forceRedrawScreen(InterfaceManager.menuX, InterfaceManager.menuY, InterfaceManager.menuHeight, InterfaceManager.menuWidth);
    }

    @OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "(I)V")
    public static void drawSimpleMenu() {
        @Pc(3) int menuY = InterfaceManager.menuY;
        @Pc(9) int menuWidth = InterfaceManager.menuWidth;
        @Pc(11) int menuX = InterfaceManager.menuX;
        @Pc(15) int menuHeight = InterfaceManager.menuHeight;
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
            @Pc(129) int textColor = COLOR_WHITE; //WHITE
            if (menuX < mouseX && mouseX < menuX + menuWidth && rowY - 13 < mouseY && mouseY < rowY + 3) {
                textColor = COLOR_YELLOW; //YELLOW
            }
            Fonts.b12Full.renderLeft(getOp(rowIndex), menuX + 3, rowY, textColor, 0);
        }
        InterfaceManager.forceRedrawScreen(InterfaceManager.menuX, InterfaceManager.menuY, InterfaceManager.menuHeight, InterfaceManager.menuWidth);
    }

    @OriginalMember(owner = "client!dm", name = "a", descriptor = "(Lclient!be;III)V")
    public static void drawMenuText(@OriginalArg(0) Component component, @OriginalArg(2) int y, @OriginalArg(3) int x) {
        if (menuActionRow < 2 && InterfaceManager.anInt5014 == 0 && !InterfaceManager.targetMode) {
            return;
        }
        @Pc(24) JString actionText = getActionText();
        if (component == null) {
            @Pc(40) int textX = Fonts.b12Full.method2859(actionText, x + 4, y - -15, Client.aRandom1, gregorianDateSeed);
            InterfaceManager.redrawScreen(x + 4, Fonts.b12Full.getStringWidth(actionText) + textX, y, 15);
            return;
        }
        @Pc(59) Font font = component.getFont(Sprites.nameIcons);
        if (font == null) {
            font = Fonts.b12Full;
        }
        font.method2878(actionText, x, y, component.width, component.height, component.color, component.graphicShadow, component.halign, component.valign, Client.aRandom1, gregorianDateSeed, textBounds);
        InterfaceManager.redrawScreen(textBounds[0], textBounds[2], textBounds[1], textBounds[3]);
    }

    @OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
    public static JString getOp(@OriginalArg(0) int index) {
        return InterfaceManager.opBases[index].length() > 0 ? JString.concatenate(new JString[] { InterfaceManager.ops[index], LocalizedText.MINISEPARATOR, InterfaceManager.opBases[index] }) : InterfaceManager.ops[index];
    }

    @OriginalMember(owner = "runetek4.client!i", name = "p", descriptor = "(II)V")
    public static void doAction(@OriginalArg(1) int menuIndex) {
        if (menuIndex < 0) {
            return;
        }
        @Pc(15) int param1 = InterfaceManager.intArgs1[menuIndex]; // re-used variable. Slot, Xcoord, component id
        @Pc(19) int param2 = InterfaceManager.intArgs2[menuIndex]; // re-used variable. Widget ID, Z coord
        @Pc(23) int actionCode = InterfaceManager.actions[menuIndex];
        if (actionCode >= ACTION_PRIORITY_OFFSET) {
            actionCode -= ACTION_PRIORITY_OFFSET;
        }
        @Pc(31) long key = InterfaceManager.keys[menuIndex];
        @Pc(36) int keyInt = (int) InterfaceManager.keys[menuIndex];
        @Pc(43) Player player;
        if (actionCode == PLAYER_FOLLOW_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.x = Mouse.clickX;
                Crosshair.y = Mouse.clickY;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER6);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == LOC_ACTION_4) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC4);
            Protocol.outboundBuffer.p2_alt1(Camera.sceneBaseTileZ + param2);
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2(Integer.MAX_VALUE & (int) (key >>> 32));
        }
        if (actionCode == OBJ_OBJ_ACTION) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ_ON_OBJ);
            Protocol.outboundBuffer.p2(InterfaceManager.anInt4370);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p4_alt1(MiniMap.anInt5062);
            Protocol.outboundBuffer.p2_alt3(anInt4997);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        @Pc(192) Npc npc;
        if (actionCode == NPC_ACTION_4) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.clickY;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPNPC4);
                Protocol.outboundBuffer.p2(keyInt);
            }
        }
        if (actionCode == NPC_ACTION_1) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.clickY;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPNPC1);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        if (actionCode == UNKNOWN_44) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER4);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        if (actionCode == OBJ_ACTION_5) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ5);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p4_alt3(param2);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == LOC_ACTION_1) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC1);
            Protocol.outboundBuffer.p2_alt1(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt2((int) (key >>> 32) & Integer.MAX_VALUE);
            Protocol.outboundBuffer.p2(param2 + Camera.sceneBaseTileZ);
        }

        if (actionCode == COMPONENT_ACTION_CLOSE) {
            InterfaceManager.closeModal();
        }

        if (actionCode == COMPONENT_NPC_ACTION) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.clickY;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPNPCT);
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
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairCycle = 0;
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.clickY;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACK1);
            Protocol.outboundBuffer.p2_alt1(Camera.sceneBaseTileX + param1);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.sceneBaseTileZ);
        }
        if (actionCode == LOC_ACTION_5) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC5);
            Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (key >>> 32));
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.sceneBaseTileZ);
        }
        if (actionCode == OBJ_EXAMINE) {
            Crosshair.CrosshairMode = 2;
            Crosshair.x = Mouse.clickX;
            Crosshair.y = Mouse.clickY;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ_EXAMINE);
            Protocol.outboundBuffer.p2_alt3(keyInt);
        }
        @Pc(693) Component component;
        if (actionCode == OBJ_EXAMINE_IN_COMPONENT) {
            component = InterfaceList.list(param2);
            if (component == null || component.invSlotObjCount[param1] < 100000) {
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ_EXAMINE);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            } else {
                ChatHistory.addMessage(JString.EMPTY, 0, JString.concatenate(new JString[] { JString.parseInt(component.invSlotObjCount[param1]), MULTIPLY_SYMBOL, ObjTypeList.get(keyInt).name}));
            }
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == WALK_HERE) {
            if (keyInt == 0) {
                setWalkTarget(Player.currentLevel, param1, param2);
            } else if (keyInt == 1) {
                if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                    Cheat.teleport(Camera.sceneBaseTileX + param1, Camera.sceneBaseTileZ + param2, Player.currentLevel);
                } else if (PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, true, 0, param1, 0, 0, 1, param2, PlayerList.self.movementQueueX[0])) {
                    Protocol.outboundBuffer.p1(InterfaceManager.anInt5);
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
            Crosshair.y = Mouse.clickY;
            Crosshair.x = Mouse.clickX;
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                @Pc(884) NpcType npcType = npc.type;
                if (npcType.multinpc != null) {
                    npcType = npcType.getMultiNPC();
                }
                if (npcType != null) {
                    Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ1);
                    Protocol.outboundBuffer.p2(npcType.id);
                }
            }
        }
        if (actionCode == OBJ_ACTION_1) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPNPC2);
            Protocol.outboundBuffer.p2_alt3(param1);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p4_alt1(param2);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == COMPONENT_OBJ_ACTION) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPNPC5);
            Protocol.outboundBuffer.p4_alt1(useWithComponentId);
            Protocol.outboundBuffer.p2_alt3(param1);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p2_alt1(useWithSlot);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == PLAYER_ACTION_3) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.clickY;
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER3);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        if (actionCode == UNKNOWN_41 && ClientScriptRunner.modalBackgroundComponent == null) {
            sendComponentContinue(param1, param2);
            ClientScriptRunner.modalBackgroundComponent = InterfaceList.getComponent(param2, param1);
            InterfaceManager.redraw(ClientScriptRunner.modalBackgroundComponent);
        }
        if (actionCode == LOC_ACTION_3) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC3);
            Protocol.outboundBuffer.p2_alt3(Integer.MAX_VALUE & (int) (key >>> 32));
            Protocol.outboundBuffer.p2_alt3(Camera.sceneBaseTileZ + param2);
            Protocol.outboundBuffer.p2_alt1(param1 + Camera.sceneBaseTileX);
        }
        if (actionCode == OBJ_OPERATE_ACTION) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ_ON_GROUND);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p4_alt1(param2);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == OBJ_LOC_ACTION && PathFinder.findPathToLoc(key, param2, param1)) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC2);
            Protocol.outboundBuffer.p2_alt2(Camera.sceneBaseTileX + param1);
            Protocol.outboundBuffer.p2(anInt4997);
            Protocol.outboundBuffer.p2_alt1(param2 + Camera.sceneBaseTileZ);
            Protocol.outboundBuffer.p2(InterfaceManager.anInt4370);
            Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
            Protocol.outboundBuffer.p2_alt2((int) (key >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == PLAYER_REQ_ASSIST_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.clickY;
                Crosshair.x = Mouse.clickX;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER7);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == UNKNOWN_9 || actionCode == UNKNOWN_1003) {
            InterfaceManager.ifButtonXSend(InterfaceManager.opBases[menuIndex], param1, keyInt, param2);
        }
        if (actionCode == OBJ_EQUIP_ACTION) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ_EQUIP);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p4_alt2(param2);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == OBJ_ACTION_2) {
            if (Client.game == 1) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            } else {
                pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                if (!pathFound) {
                    PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
                }
            }
            Crosshair.CrosshairMode = 2;
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairCycle = 0;
            Crosshair.y = Mouse.clickY;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ2);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p2_alt1(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.sceneBaseTileZ);
        }
        if (actionCode == NPC_ACTION_3) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.clickY;
                Crosshair.x = Mouse.clickX;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYERT);
                Protocol.outboundBuffer.p2_alt2(keyInt);
            }
        }
        if (actionCode == PLAYER_ACTION_4) {
            component = InterfaceList.getComponent(param2, param1);
            if (component != null) {
                InterfaceManager.endTargetMode();
                @Pc(1493) ServerActiveProperties local1493 = InterfaceManager.getServerActiveProperties(component);
                startUseWith(param2, param1, local1493.getTargetMask(), local1493.targetParam, component.targetEnterCursor, component.targetEndCursor);
                InterfaceManager.anInt5014 = 0;
                InterfaceManager.aClass100_545 = MiniMap.getTargetVerb(component);
                if (InterfaceManager.aClass100_545 == null) {
                    InterfaceManager.aClass100_545 = NULL_TEXT;
                }
                if (component.hasOpKey) {
                    InterfaceManager.aClass100_466 = JString.concatenate(new JString[] { component.opBase, COLOR_WHITE2});
                } else {
                    InterfaceManager.aClass100_466 = JString.concatenate(new JString[] {InterfaceManager.COLOR_LIMEGREEN, component.optionSuffix, COLOR_WHITE2});
                }
            }
            return;
        }
        if (actionCode == PLAYER_ACTION_TRADE) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.clickX;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER2);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == OBJ_ACTION_4) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ4);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            Protocol.outboundBuffer.p2_alt3(param1);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == COMPONENT_PLAYER_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.clickX;
                Crosshair.y = Mouse.clickY;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACK5);
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
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairMode = 2;
            Crosshair.y = Mouse.clickY;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACK3);
            Protocol.outboundBuffer.p2_alt1(param2 + Camera.sceneBaseTileZ);
            Protocol.outboundBuffer.p2(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt3(keyInt);
        }
        if (actionCode == OBJ_IN_COMPONENT_ACTION_1) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ1);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p4_alt2(param2);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == NPC_ACTION_5) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACKT);
                Protocol.outboundBuffer.p2_alt1(keyInt);
            }
        }
        @Pc(1955) int varpId;
        if (actionCode == LOGOUT_ACTION) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.LOGOUT);
            Protocol.outboundBuffer.p4(param2);
            component = InterfaceList.list(param2);
            if (component.scripts != null && component.scripts[0][0] == 5) {
                varpId = component.scripts[0][1];
                if (VarpDomain.activeVarps[varpId] != component.scriptOperand[0]) {
                    VarpDomain.activeVarps[varpId] = component.scriptOperand[0];
                    VarpDomain.refreshMagicVarp(varpId);
                }
            }
        }
        if (actionCode == OBJ_NPC_ACTION) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.clickY;
                Crosshair.x = Mouse.clickX;
                Protocol.outboundBuffer.pIsaac1(ClientProt.RESUME_PAUSEBUTTON);
                Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
                Protocol.outboundBuffer.p2_alt1(InterfaceManager.anInt4370);
                Protocol.outboundBuffer.p2_alt1(keyInt);
                Protocol.outboundBuffer.p2_alt3(anInt4997);
            }
        }
        if (actionCode == LOGOUT_ACTION_2) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.LOGOUT);
            Protocol.outboundBuffer.p4(param2);
            component = InterfaceList.list(param2);
            if (component.scripts != null && component.scripts[0][0] == 5) {
                varpId = component.scripts[0][1];
                VarpDomain.activeVarps[varpId] = 1 - VarpDomain.activeVarps[varpId];
                VarpDomain.refreshMagicVarp(varpId);
            }
        }
        if (actionCode == OBJ_OBJSTACK_ACTION) {
            pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            if (!pathFound) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            }
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairCycle = 0;
            Crosshair.y = Mouse.clickY;
            Crosshair.CrosshairMode = 2;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACK4);
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt1(InterfaceManager.anInt4370);
            Protocol.outboundBuffer.p2_alt1(anInt4997);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.sceneBaseTileZ);
            Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
        }
        if (actionCode == LOC_ACTION_6) {
            Crosshair.CrosshairCycle = 0;
            Crosshair.CrosshairMode = 2;
            Crosshair.x = Mouse.clickX;
            Crosshair.y = Mouse.clickY;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC6);
            Protocol.outboundBuffer.p2_alt3(keyInt);
        }
        if (actionCode == COMPONENT_ACTION) {
            if (keyInt == 0) {
                useItemOnTileMode = 1;
                setWalkTarget(Player.currentLevel, param1, param2);
            } else if (keyInt == 1) {
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJT);
                Protocol.outboundBuffer.p4_alt3(useWithComponentId);
                Protocol.outboundBuffer.p2_alt2(param1 + Camera.sceneBaseTileX);
                Protocol.outboundBuffer.p2_alt3(useWithSlot);
                Protocol.outboundBuffer.p2_alt2(param2 + Camera.sceneBaseTileZ);
            }
        }
        if (actionCode == LOGOUT_3) {
            component = InterfaceList.list(param2);
            @Pc(2287) boolean shouldProcess = true;
            if (component.clientcode > 0) {
                shouldProcess = shouldTriggerIdleTimeout(component);
            }
            if (shouldProcess) {
                Protocol.outboundBuffer.pIsaac1(ClientProt.LOGOUT);
                Protocol.outboundBuffer.p4(param2);
            }
        }
        if (actionCode == OBJ_PLAYER_ACTION) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.clickX;
                Protocol.outboundBuffer.pIsaac1(ClientProt.TELEPORT);
                Protocol.outboundBuffer.p2_alt3(keyInt);
                Protocol.outboundBuffer.p2(anInt4997);
                Protocol.outboundBuffer.p2(InterfaceManager.anInt4370);
                Protocol.outboundBuffer.p4_alt3(MiniMap.anInt5062);
            }
        }
        if (actionCode == OBJ_ACTION_3) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJ3);
            Protocol.outboundBuffer.p4_alt2(param2);
            Protocol.outboundBuffer.p2(param1);
            Protocol.outboundBuffer.p2_alt2(keyInt);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
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
            Crosshair.y = Mouse.clickY;
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACK2);
            Protocol.outboundBuffer.p2_alt2(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            Protocol.outboundBuffer.p2_alt1(Camera.sceneBaseTileZ + param2);
        }
        if (actionCode == COMPONENT_LOC_ACTION && PathFinder.findPathToLoc(key, param2, param1)) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOCT);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.sceneBaseTileZ);
            Protocol.outboundBuffer.p2_alt2(Camera.sceneBaseTileX + param1);
            Protocol.outboundBuffer.p2_alt3(useWithSlot);
            Protocol.outboundBuffer.p4_alt2(useWithComponentId);
            Protocol.outboundBuffer.p2_alt2((int) (key >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == UNKNOWN_13) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.IF_ON_COMPONENT5);
            Protocol.outboundBuffer.p4(param2);
            Protocol.outboundBuffer.p2_alt2(param1);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == PLAYER_ACTION_5) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairMode = 2;
                Crosshair.y = Mouse.clickY;
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairCycle = 0;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER5);
                Protocol.outboundBuffer.p2_alt2(keyInt);
            }
        }
        if (actionCode == UNKNOWN_22) {
            InterfaceManager.endTargetMode();
            component = InterfaceList.list(param2);
            MiniMap.anInt5062 = param2;
            InterfaceManager.anInt4370 = param1;
            InterfaceManager.anInt5014 = 1;
            anInt4997 = keyInt;
            InterfaceManager.redraw(component);
            InterfaceManager.aClass100_203 = JString.concatenate(new JString[] {InterfaceManager.COLOR_LIGHT_ORANGE, ObjTypeList.get(keyInt).name, COLOR_WHITE2});
            if (InterfaceManager.aClass100_203 == null) {
                InterfaceManager.aClass100_203 = NULL;
            }
            return;
        }
        if (actionCode == LOC_ACTION_2) {
            PathFinder.findPathToLoc(key, param2, param1);
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPLOC2);
            Protocol.outboundBuffer.p2_alt3(param2 + Camera.sceneBaseTileZ);
            Protocol.outboundBuffer.p2_alt1(Camera.sceneBaseTileX + param1);
            Protocol.outboundBuffer.p2((int) (key >>> 32) & Integer.MAX_VALUE);
        }
        if (actionCode == RESUME_STRINGDIALOG) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.RESUME_STRINGDIALOG);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p4_alt2(param2);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == PLAYER_ACTION_1) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.CrosshairCycle = 0;
                Crosshair.x = Mouse.clickX;
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairMode = 2;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER1);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (actionCode == OBJ_IN_COMPONENT_ACTION_4) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.RESUME_P_COUNTDIALOG);
            Protocol.outboundBuffer.p4_alt1(param2);
            Protocol.outboundBuffer.p2_alt1(param1);
            Protocol.outboundBuffer.p2_alt1(keyInt);
            inventoryPressTimer = 0;
            pressedInventoryComponent = InterfaceList.list(param2);
            anInt5444 = param1;
        }
        if (actionCode == COMPONENT_OBJSTACK_ACTION) {
            pathFound = PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 0, false, 0, param1, 0, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            if (!pathFound) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, param1, 1, 0, 2, param2, PlayerList.self.movementQueueX[0]);
            }
            Crosshair.y = Mouse.clickY;
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairMode = 2;
            Crosshair.CrosshairCycle = 0;
            Protocol.outboundBuffer.pIsaac1(ClientProt.RESUME_COUNTDIALOG);
            Protocol.outboundBuffer.p4_alt2(useWithComponentId);
            Protocol.outboundBuffer.p2(Camera.sceneBaseTileZ + param2);
            Protocol.outboundBuffer.p2_alt3(keyInt);
            Protocol.outboundBuffer.p2_alt3(param1 + Camera.sceneBaseTileX);
            Protocol.outboundBuffer.p2_alt1(useWithSlot);
        }
        if (actionCode == RESUME_OBJDIALOG) {
            Protocol.outboundBuffer.pIsaac1(ClientProt.RESUME_OBJDIALOG);
            Protocol.outboundBuffer.p2(useWithSlot);
            Protocol.outboundBuffer.p4_alt2(param2);
            Protocol.outboundBuffer.p4(useWithComponentId);
            Protocol.outboundBuffer.p2_alt3(param1);
        }
        if (actionCode == UNKNOWN_36) {
            if (keyInt == 0) {
                Protocol.walkRequestState = 1;
                setWalkTarget(Player.currentLevel, param1, param2);
            } else if (LoginManager.staffModLevel > 0 && Keyboard.pressedKeys[82] && Keyboard.pressedKeys[81]) {
                Cheat.teleport(param1 + Camera.sceneBaseTileX, Camera.sceneBaseTileZ - -param2, Player.currentLevel);
            } else {
                Protocol.outboundBuffer.pIsaac1(ClientProt.MOVE_CLICK_ALT);
                Protocol.outboundBuffer.p2(param2 + Camera.sceneBaseTileZ);
                Protocol.outboundBuffer.p2(param1 + Camera.sceneBaseTileX);
            }
        }
        if (actionCode == UNKNOWN_6) {
            player = PlayerList.players[keyInt];
            if (player != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, player.movementQueueX[0], 1, 0, 2, player.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairCycle = 0;
                Crosshair.CrosshairMode = 2;
                Crosshair.x = Mouse.clickX;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPPLAYER_ACTION_ALT);
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
            Crosshair.y = Mouse.clickY;
            Crosshair.CrosshairCycle = 0;
            Crosshair.x = Mouse.clickX;
            Crosshair.CrosshairMode = 2;
            Protocol.outboundBuffer.pIsaac1(ClientProt.OPOBJSTACK_ACTION2_ALT);
            Protocol.outboundBuffer.p2(keyInt);
            Protocol.outboundBuffer.p2(Camera.sceneBaseTileX + param1);
            Protocol.outboundBuffer.p2_alt1(Camera.sceneBaseTileZ + param2);
        }
        if (actionCode == NPC_ACTION_2) {
            npc = NpcList.npcs[keyInt];
            if (npc != null) {
                PathFinder.findPath(PlayerList.self.movementQueueZ[0], 0, 1, false, 0, npc.movementQueueX[0], 1, 0, 2, npc.movementQueueZ[0], PlayerList.self.movementQueueX[0]);
                Crosshair.x = Mouse.clickX;
                Crosshair.CrosshairCycle = 0;
                Crosshair.y = Mouse.clickY;
                Crosshair.CrosshairMode = 2;
                Protocol.outboundBuffer.pIsaac1(ClientProt.OPNPC_ACTION2_ALT);
                Protocol.outboundBuffer.p2_alt3(keyInt);
            }
        }
        if (InterfaceManager.anInt5014 != 0) {
            InterfaceManager.anInt5014 = 0;
            InterfaceManager.redraw(InterfaceList.list(MiniMap.anInt5062));
        }
        if (InterfaceManager.targetMode) {
            InterfaceManager.endTargetMode();
        }
        if (pressedInventoryComponent != null && inventoryPressTimer == 0) {
            InterfaceManager.redraw(pressedInventoryComponent);
        }
    }

    @OriginalMember(owner = "runetek4.client!jj", name = "a", descriptor = "(IBI)Lclient!na;")
    public static JString getCombatLevelColorTag(@OriginalArg(0) int viewerLevel, @OriginalArg(2) int otherLevel) {
        @Pc(4) int diff = otherLevel - viewerLevel;
        if (diff < -9) {
            return COLOR_RED;
        } else if (diff < -6) {
            return COLOR_RED_ORANGE;
        } else if (diff < -3) {
            return COLOR_ORANGE;
        } else if (diff < 0) {
            return COLOR_AMBER;
        } else if (diff > 9) {
            return InterfaceManager.COLOR_LIMEGREEN;
        } else if (diff > 6) {
            return COLOR_BRIGHT_LIMEGREEN;
        } else if (diff <= 3) {
            return diff > 0 ? COLOR_YELLOW_GREEN : COLOR_YELLOW2;
        } else {
            return COLOR_SPRING_GREEN;
        }
    }

    @OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IIIIIIB)V")
    public static void populateMenuEntries(@OriginalArg(0) int startY, @OriginalArg(1) int width, @OriginalArg(2) int height, @OriginalArg(3) int startX, @OriginalArg(4) int endY, @OriginalArg(5) int endX) {
        @Pc(15) int screenMaxY;
        @Pc(47) int worldY;
        if (InterfaceManager.anInt5014 == 0) {
            @Pc(13) int screenMinY = Rasterizer.screenUpperY;
            screenMaxY = Rasterizer.screenLowerY;
            @Pc(17) int screenMinX = Rasterizer.screenUpperX;
            @Pc(19) int screenMaxX = Rasterizer.screenLowerX;
            @Pc(33) int worldX = (endX - startX) * (screenMinX - screenMaxX) / width + screenMaxX;
            worldY = screenMaxY + (screenMinY - screenMaxY) * (endY - startY) / height;
            if (InterfaceManager.targetMode && (InterfaceManager.useWithMask & TARGET_MASK_TILE) != 0) {
                @Pc(61) Component component = InterfaceList.getComponent(useWithComponentId, useWithSlot);
                if (component == null) {
                    InterfaceManager.endTargetMode();
                } else {
                    addActionRow(InterfaceManager.useWithCursor, 0L, aClass100_961, worldX, (short) 11, InterfaceManager.aClass100_545, worldY);
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
                if (entityType == 2 && SceneGraph.isLocValid(Player.currentLevel, worldY, localZ, key)) {
                    @Pc(172) LocType locType = LocTypeList.get(entityId);
                    if (locType.multiloc != null) {
                        locType = locType.getMultiLoc();
                    }
                    if (locType == null) {
                        continue;
                    }
                    if (InterfaceManager.anInt5014 == 1) {
                        addActionRow(MiniMap.anInt4075, key, JString.concatenate(new JString[] {InterfaceManager.aClass100_203, CYAN_ARROW, locType.name}), worldY, (short) 14, LocalizedText.USE, localZ);
                    } else if (InterfaceManager.targetMode) {
                        @Pc(363) ParamType paramType = InterfaceManager.useWithParam == -1 ? null : ParamTypeList.get(InterfaceManager.useWithParam);
                        if ((InterfaceManager.useWithMask & TARGET_MASK_LOC) != 0 && (paramType == null || locType.getParam(paramType.defaultInt, InterfaceManager.useWithParam) != paramType.defaultInt)) {
                            addActionRow(InterfaceManager.useWithCursor, key, JString.concatenate(new JString[] {InterfaceManager.aClass100_466, CYAN_ARROW, locType.name}), worldY, (short) 38, InterfaceManager.aClass100_545, localZ);
                        }
                    } else {
                        @Pc(228) JString[] locOps = locType.op;
                        if (InterfaceManager.aBoolean237) {
                            locOps = InterfaceManager.annotateOps(locOps);
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
                                    addActionRow(cursor, key, JString.concatenate(new JString[] {COLOR_CYAN, locType.name}), worldY, actionId, locOps[opIndex], localZ);
                                }
                            }
                        }
                        addActionRow(MiniMap.anInt5073, (long) locType.id, JString.concatenate(new JString[] {COLOR_CYAN, locType.name}), worldY, (short) 1004, LocalizedText.EXAMINE, localZ);
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
                    @Pc(931) LinkedList objStack = SceneGraph.objStacks[Player.currentLevel][worldY][localZ];
                    if (objStack != null) {
                        for (@Pc(940) ClientObj objNode = (ClientObj) objStack.last(); objNode != null; objNode = (ClientObj) objStack.prev()) {
                            opIndex = objNode.value.id;
                            @Pc(951) ObjType objType = ObjTypeList.get(opIndex);
                            if (InterfaceManager.anInt5014 == 1) {
                                addActionRow(MiniMap.anInt4075, (long) opIndex, JString.concatenate(new JString[] {InterfaceManager.aClass100_203, InterfaceManager.COLOR_LIGHT_ORANGE_ARROW, objType.name}), worldY, (short) 33, LocalizedText.USE, localZ);
                            } else if (InterfaceManager.targetMode) {
                                @Pc(1142) ParamType paramType = InterfaceManager.useWithParam == -1 ? null : ParamTypeList.get(InterfaceManager.useWithParam);
                                if ((InterfaceManager.useWithMask & TARGET_MASK_OBJSTACK) != 0 && (paramType == null || objType.getParam(paramType.defaultInt, InterfaceManager.useWithParam) != paramType.defaultInt)) {
                                    addActionRow(InterfaceManager.useWithCursor, (long) opIndex, JString.concatenate(new JString[] {InterfaceManager.aClass100_466, InterfaceManager.COLOR_LIGHT_ORANGE_ARROW, objType.name}), worldY, (short) 39, InterfaceManager.aClass100_545, localZ);
                                }
                            } else {
                                @Pc(997) JString[] objOps = objType.op;
                                if (InterfaceManager.aBoolean237) {
                                    objOps = InterfaceManager.annotateOps(objOps);
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
                                        addActionRow(cursor, (long) opIndex, JString.concatenate(new JString[] {InterfaceManager.COLOR_LIGHT_ORANGE, objType.name}), worldY, actionId, objOps[otherEntityX], localZ);
                                    }
                                }
                                addActionRow(MiniMap.anInt5073, (long) opIndex, JString.concatenate(new JString[] {InterfaceManager.COLOR_LIGHT_ORANGE, objType.name}), worldY, (short) 1002, LocalizedText.EXAMINE, localZ);
                            }
                        }
                    }
                }
            }
        }
    }

    @OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(Lclient!me;IIII)V")
    public static void addNpcEntries(@OriginalArg(0) NpcType npc, @OriginalArg(1) int localX, @OriginalArg(3) int npcId, @OriginalArg(4) int localZ) {
        if (menuActionRow >= MAX_MENU_ACTIONS) {
            return;
        }
        if (npc.multinpc != null) {
            npc = npc.getMultiNPC();
        }
        if (npc == null || !npc.interactive) {
            return;
        }
        @Pc(35) JString tooltip = npc.name;
        if (npc.vislevel != 0) {
            @Pc(47) JString levelText = Client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
            tooltip = JString.concatenate(new JString[] { tooltip, getCombatLevelColorTag(npc.vislevel, PlayerList.self.combatLevel), OPEN_PARENTHESIS, levelText, JString.parseInt(npc.vislevel), CLOSE_PARENTHESIS});
        }
        if (InterfaceManager.anInt5014 == 1) {
            addActionRow(MiniMap.anInt4075, (long) npcId, JString.concatenate(new JString[] {InterfaceManager.aClass100_203, YELLOW_ARROW, tooltip }), localX, (short) 26, LocalizedText.USE, localZ);
        } else if (InterfaceManager.targetMode) {
            @Pc(378) ParamType paramType = InterfaceManager.useWithParam == -1 ? null : ParamTypeList.get(InterfaceManager.useWithParam);
            if ((InterfaceManager.useWithMask & TARGET_MASK_NPC) != 0 && (paramType == null || npc.getParam(InterfaceManager.useWithParam, paramType.defaultInt) != paramType.defaultInt)) {
                addActionRow(InterfaceManager.useWithCursor, (long) npcId, JString.concatenate(new JString[] {InterfaceManager.aClass100_466, YELLOW_ARROW, tooltip }), localX, (short) 45, InterfaceManager.aClass100_545, localZ);
            }
        } else {
            @Pc(129) JString[] npcOps = npc.op;
            if (InterfaceManager.aBoolean237) {
                npcOps = InterfaceManager.annotateOps(npcOps);
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
                        addActionRow(cursor, (long) npcId, JString.concatenate(new JString[] {COLOR_YELLOW2, tooltip }), localX, actionId, npcOps[opIndex], localZ);
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
                        addActionRow(npc.cursorattack, (long) npcId, JString.concatenate(new JString[] {COLOR_YELLOW2, tooltip }), localX, baseActionId, npcOps[opIndex], localZ);
                    }
                }
            }
            addActionRow(MiniMap.anInt5073, (long) npcId, JString.concatenate(new JString[] {COLOR_YELLOW2, tooltip }), localX, (short) 1007, LocalizedText.EXAMINE, localZ);
        }
    }

    @OriginalMember(owner = "runetek4.client!rj", name = "a", descriptor = "(IIILclient!e;I)V")
    public static void addPlayerEntries(@OriginalArg(0) int playerId, @OriginalArg(2) int localZ, @OriginalArg(3) Player other, @OriginalArg(4) int localX) {
        if (PlayerList.self == other || menuActionRow >= 400) {
            return;
        }
        @Pc(158) JString tooltip;
        if (other.skill == 0) {
            @Pc(22) boolean shouldColorByLevel = true;
            if (PlayerList.self.combatRange != -1 && other.combatRange != -1) {
                @Pc(43) int highestCombatLevel = other.combatLevel < PlayerList.self.combatLevel ? PlayerList.self.combatLevel : other.combatLevel;
                @Pc(58) int highestCombatRange = PlayerList.self.combatRange < other.combatRange ? PlayerList.self.combatRange : other.combatRange;
                @Pc(69) int combatRangeThreshold = highestCombatLevel * 10 / 100 + highestCombatRange + 5;
                @Pc(76) int combatDelta = PlayerList.self.combatLevel - other.combatLevel;
                if (combatDelta < 0) {
                    combatDelta = -combatDelta;
                }
                if (combatRangeThreshold < combatDelta) {
                    shouldColorByLevel = false;
                }
            }
            @Pc(95) JString levelText = Client.game == 1 ? LocalizedText.RATING : LocalizedText.LEVEL;
            if (other.combatLevel < other.combatLevelWithSummoning) {
                tooltip = JString.concatenate(new JString[] { other.getUsername(), shouldColorByLevel ? getCombatLevelColorTag(other.combatLevel, PlayerList.self.combatLevel) : COLOR_WHITE2, OPEN_PARENTHESIS, levelText, JString.parseInt(other.combatLevel), PLUS, JString.parseInt(other.combatLevelWithSummoning - other.combatLevel), CLOSE_PARENTHESIS});
            } else {
                tooltip = JString.concatenate(new JString[] { other.getUsername(), shouldColorByLevel ? getCombatLevelColorTag(other.combatLevel, PlayerList.self.combatLevel) : COLOR_WHITE2, OPEN_PARENTHESIS, levelText, JString.parseInt(other.combatLevel), CLOSE_PARENTHESIS});
            }
        } else {
            tooltip = JString.concatenate(new JString[] { other.getUsername(), OPEN_PARENTHESIS, LocalizedText.SKILL, JString.parseInt(other.skill), CLOSE_PARENTHESIS});
        }
        @Pc(275) int playerOptionIndex;
        if (InterfaceManager.anInt5014 == 1) {
            addActionRow(MiniMap.anInt4075, (long) playerId, JString.concatenate(new JString[] {InterfaceManager.aClass100_203, WHITE_ARROW, tooltip }), localX, (short) 1, LocalizedText.USE, localZ);
        } else if (!InterfaceManager.targetMode) {
            for (playerOptionIndex = 7; playerOptionIndex >= 0; playerOptionIndex--) {
                if (Player.options[playerOptionIndex] != null) {
                    @Pc(291) short priorityModifier = 0;
                    if (Client.game == 0 && Player.options[playerOptionIndex].equalsIgnoreCase(LocalizedText.ATTACK)) {
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
                    } else if (Player.secondaryOptions[playerOptionIndex]) {
                        priorityModifier = 2000;
                    }
                    @Pc(353) short baseActionId = PLAYER_ACTION_IDS[playerOptionIndex];
                    @Pc(358) short finalActionId = (short) (baseActionId + priorityModifier);
                    addActionRow(Player.cursors[playerOptionIndex], (long) playerId, JString.concatenate(new JString[] {COLOR_WHITE2, tooltip }), localX, finalActionId, Player.options[playerOptionIndex], localZ);
                }
            }
        } else if ((InterfaceManager.useWithMask & TARGET_MASK_PLAYER) != 0) {
            addActionRow(InterfaceManager.useWithCursor, (long) playerId, JString.concatenate(new JString[] {InterfaceManager.aClass100_466, WHITE_ARROW, tooltip }), localX, (short) 15, InterfaceManager.aClass100_545, localZ);
        }
        for (playerOptionIndex = 0; playerOptionIndex < menuActionRow; playerOptionIndex++) {
            if (InterfaceManager.actions[playerOptionIndex] == 60) {
                InterfaceManager.opBases[playerOptionIndex] = JString.concatenate(new JString[] {COLOR_WHITE2, tooltip });
                break;
            }
        }
    }

    @OriginalMember(owner = "client!bc", name = "f", descriptor = "(B)Lclient!na;")
    public static JString getActionText() {
        @Pc(32) JString actionText;
        if (InterfaceManager.anInt5014 == 1 && menuActionRow < 2) {
            actionText = JString.concatenate(new JString[] { LocalizedText.USE, LocalizedText.MINISEPARATOR, InterfaceManager.aClass100_203, aClass100_961});
        } else if (InterfaceManager.targetMode && menuActionRow < 2) {
            actionText = JString.concatenate(new JString[] {InterfaceManager.aClass100_545, LocalizedText.MINISEPARATOR, InterfaceManager.aClass100_466, aClass100_961});
        } else if (Cheat.shiftClick && Keyboard.pressedKeys[81] && menuActionRow > 2) {
            actionText = getOp(menuActionRow - 2);
        } else {
            actionText = getOp(menuActionRow - 1);
        }
        if (menuActionRow > 2) {
            actionText = JString.concatenate(new JString[] { actionText, COLOR_WHITE_BRACKET, JString.parseInt(menuActionRow - 2), LocalizedText.MOREOPTIONS});
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
        @Pc(12) int actionCode = InterfaceManager.actions[menuIndex];
        if (actionCode >= 2000) {
            actionCode -= 2000;
        }
        return actionCode == 1003;
    }

    @OriginalMember(owner = "runetek4.client!ub", name = "b", descriptor = "(IIIIIII)V")
    public static void startUseWith(@OriginalArg(0) int componentId, @OriginalArg(1) int slot, @OriginalArg(2) int targetMask, @OriginalArg(3) int param, @OriginalArg(4) int cursor, @OriginalArg(6) int defaultCursorValue) {
        @Pc(8) Component component = InterfaceList.getComponent(componentId, slot);
        if (component != null && component.onTargetEnter != null) {
            @Pc(19) HookRequest event = new HookRequest();
            event.source = component;
            event.arguments = component.onTargetEnter;
            ClientScriptRunner.executeScript(event);
        }
        useWithSlot = slot;
        InterfaceManager.useWithParam = param;
        useWithComponentId = componentId;
        InterfaceManager.useWithMask = targetMask;
        InterfaceManager.targetMode = true;
        InterfaceManager.useWithCursor = cursor;
        InterfaceManager.defaultCursor = defaultCursorValue;
        InterfaceManager.redraw(component);
    }

    @OriginalMember(owner = "client!ej", name = "h", descriptor = "(I)V")
    public static void processMenuActions() {
        if (menuState == 2) {
            if (ClientScriptRunner.scriptMouseX == Mouse.anInt5850 && ClientScriptRunner.scriptMouseY == Mouse.anInt5895) {
                menuState = 0;
                if (Cheat.shiftClick && Keyboard.pressedKeys[Keyboard.KEY_SHIFT] && menuActionRow > 2) {
                    doAction(menuActionRow - 2);
                } else {
                    doAction(menuActionRow - 1);
                }
            }
        } else if (ClientScriptRunner.scriptMouseX == Mouse.clickX && ClientScriptRunner.scriptMouseY == Mouse.clickY) {
            menuState = 0;
            if (Cheat.shiftClick && Keyboard.pressedKeys[Keyboard.KEY_SHIFT] && menuActionRow > 2) {
                doAction(menuActionRow - 2);
            } else {
                doAction(menuActionRow - 1);
            }
        } else {
            Mouse.anInt5895 = Mouse.clickY;
            menuState = 2;
            Mouse.anInt5850 = Mouse.clickX;
        }
    }

    @OriginalMember(owner = "runetek4.client!aa", name = "a", descriptor = "(IZI)V")
    public static void sendComponentContinue(@OriginalArg(0) int componentId, @OriginalArg(2) int widgetId) {
        Protocol.outboundBuffer.pIsaac1(132);
        Protocol.outboundBuffer.p4_alt2(widgetId);
        Protocol.outboundBuffer.p2_alt1(componentId);
    }

    @OriginalMember(owner = "runetek4.client!tb", name = "h", descriptor = "(I)I")
    public static int getShiftClickOption() {
        return Cheat.shiftClick && Keyboard.pressedKeys[81] && menuActionRow > 2 ? InterfaceManager.cursors[menuActionRow - 2] : InterfaceManager.cursors[menuActionRow - 1];
    }

    @OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
    public static void addActionRow(@OriginalArg(0) int cursor, @OriginalArg(1) long key, @OriginalArg(3) JString opBase, @OriginalArg(4) int param1, @OriginalArg(5) short action, @OriginalArg(6) JString op, @OriginalArg(7) int param2) {
        if (open || menuActionRow >= MENU_ACTION_BUFFER_SIZE) {
            return;
        }
        InterfaceManager.ops[menuActionRow] = op;
        InterfaceManager.opBases[menuActionRow] = opBase;
        InterfaceManager.cursors[menuActionRow] = cursor == -1 ? InterfaceManager.defaultCursor : cursor;
        InterfaceManager.actions[menuActionRow] = action;
        InterfaceManager.keys[menuActionRow] = key;
        InterfaceManager.intArgs1[menuActionRow] = param1;
        InterfaceManager.intArgs2[menuActionRow] = param2;
        menuActionRow++;
    }
}
