package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Cheat {
    @OriginalMember(owner = "runetek4.client!p", name = "f", descriptor = "Lclient!na;")
    public static final JString SERVERJS5DROP = Static28.parse("::serverjs5drop");
    @OriginalMember(owner = "runetek4.client!v", name = "d", descriptor = "Lclient!na;")
    public static final JString CLIENTDROP = Static28.parse("::clientdrop");
    @OriginalMember(owner = "runetek4.client!dg", name = "b", descriptor = "Lclient!na;")
    public static final JString SHIFTCLICK_ENABLED = Static28.parse("Shift)2click ENABLED(Q");
    @OriginalMember(owner = "runetek4.client!nh", name = "hb", descriptor = "Lclient!na;")
    public static final JString COMMAND_FPS = Static28.parse("::fps ");
    @OriginalMember(owner = "runetek4.client!nb", name = "e", descriptor = "Lclient!na;")
    public static final JString BREAKCON = Static28.parse("::breakcon");
    @OriginalMember(owner = "runetek4.client!wf", name = "s", descriptor = "Lclient!na;")
    public static final JString FORCED_TWEENING_DISABLED = Static28.parse("Forced tweening disabled)3");
    @OriginalMember(owner = "runetek4.client!wd", name = "f", descriptor = "Lclient!na;")
    public static final JString ERRORTEST = Static28.parse("::errortest");
    @OriginalMember(owner = "runetek4.client!wb", name = "j", descriptor = "Lclient!na;")
    public static final JString aClass100_1093 = Static28.parse("Memory after cleanup=");
    @OriginalMember(owner = "runetek4.client!th", name = "h", descriptor = "Lclient!na;")
    public static final JString SETPARTICLES = Static28.parse("::setparticles");
    @OriginalMember(owner = "runetek4.client!rc", name = "K", descriptor = "Lclient!na;")
    public static final JString RECT_DEBUG = Static28.parse("::rect_debug");
    @OriginalMember(owner = "runetek4.client!hh", name = "b", descriptor = "Lclient!na;")
    public static final JString aClass100_521 = Static28.parse("::tele ");
    @OriginalMember(owner = "runetek4.client!hh", name = "k", descriptor = "Lclient!na;")
    public static final JString WM0 = Static28.parse("::wm0");
    @OriginalMember(owner = "runetek4.client!tg", name = "i", descriptor = "Lclient!na;")
    public static final JString PCACHESIZE = Static28.parse("::pcachesize");
    @OriginalMember(owner = "runetek4.client!fh", name = "ab", descriptor = "Lclient!na;")
    public static final JString NOCLIP = Static28.parse("::noclip");
    @OriginalMember(owner = "runetek4.client!en", name = "e", descriptor = "Lclient!na;")
    public static final JString FPSOFF = Static28.parse("::fpsoff");
    @OriginalMember(owner = "runetek4.client!ja", name = "k", descriptor = "Lclient!na;")
    public static final JString DEBUG_FPS = Static28.parse("Fps:");
    @OriginalMember(owner = "runetek4.client!qh", name = "i", descriptor = "Lclient!na;")
    public static final JString MEM = Static28.parse("Mem:");
    @OriginalMember(owner = "client!bi", name = "W", descriptor = "Lclient!na;")
    public static final JString DEBUG_MEMORY_UNIT = Static28.parse("k");
    @OriginalMember(owner = "runetek4.client!dg", name = "d", descriptor = "Lclient!na;")
    public static final JString DEBUG_CACHE = Static28.parse("Cache:");
    @OriginalMember(owner = "runetek4.client!hm", name = "Y", descriptor = "Lclient!na;")
    public static final JString DEBUG_FPS2 = Static28.parse("Fps:");
    @OriginalMember(owner = "client!c", name = "Y", descriptor = "Lclient!na;")
    public static final JString DEBUG_MEM = Static28.parse("Mem:");
    @OriginalMember(owner = "client!bg", name = "z", descriptor = "Lclient!na;")
    public static final JString DEBUG_MEM_UNIT = Static28.parse("k");
    @OriginalMember(owner = "runetek4.client!q", name = "h", descriptor = "Lclient!na;")
    public static final JString DEBUG_CARD = Static28.parse("Card:");
    @OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "Lclient!na;")
    public static final JString QA_OP_TEST = Static28.parse("::qa_op_test");
    @OriginalMember(owner = "runetek4.client!jk", name = "F", descriptor = "Lclient!na;")
    public static final JString TWEEN = Static28.parse("::tween");
    @OriginalMember(owner = "runetek4.client!j", name = "z", descriptor = "Lclient!na;")
    public static final JString SHIFTCLICK = Static28.parse("::shiftclick");
    @OriginalMember(owner = "runetek4.client!qf", name = "N", descriptor = "Lclient!na;")
    public static final JString CARDMEM = Static28.parse("::cardmem");
    @OriginalMember(owner = "runetek4.client!e", name = "Ac", descriptor = "Lclient!na;")
    public static final JString FORCED_TWEENING_ENABLED = Static28.parse("Forced tweening ENABLED(Q");
    @OriginalMember(owner = "runetek4.client!md", name = "T", descriptor = "Lclient!na;")
    public static final JString SHIFTCLICK_DISABLED = Static28.parse("Shift)2click disabled)3");
    @OriginalMember(owner = "runetek4.client!wh", name = "o", descriptor = "Lclient!na;")
    public static final JString RECT_DEBUG_EQUALS = Static28.parse("rect_debug=");
    @OriginalMember(owner = "runetek4.client!dh", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_335 = Static28.parse("Number of player models in cache:");
    @OriginalMember(owner = "runetek4.client!qh", name = "c", descriptor = "Lclient!na;")
    public static final JString aClass100_893 = Static28.parse("Memory before cleanup=");
    @OriginalMember(owner = "runetek4.client!qg", name = "Z", descriptor = "Lclient!na;")
    public static final JString aClass100_892 = Static28.parse("mem=");
    @OriginalMember(owner = "runetek4.client!gg", name = "Y", descriptor = "Lclient!na;")
    public static final JString GC = Static28.parse("::gc");
    @OriginalMember(owner = "runetek4.client!wk", name = "u", descriptor = "Lclient!na;")
    public static final JString CLIENTJS5DROP = Static28.parse("::clientjs5drop");
    @OriginalMember(owner = "runetek4.client!id", name = "c", descriptor = "Lclient!na;")
    public static final JString FPSON = Static28.parse("::fpson");
    @OriginalMember(owner = "runetek4.client!md", name = "Q", descriptor = "Lclient!na;")
    public static final JString MM = Static28.parse("::mm");
    @OriginalMember(owner = "runetek4.client!j", name = "M", descriptor = "Lclient!na;")
    public static final JString REPLACECANVAS = Static28.parse("::replacecanvas");
    @OriginalMember(owner = "runetek4.client!li", name = "p", descriptor = "Lclient!na;")
    public static final JString REBUILD = Static28.parse("::rebuild");
    @OriginalMember(owner = "runetek4.client!oi", name = "j", descriptor = "Lclient!na;")
    public static final JString WM1 = Static28.parse("::wm1");
    @OriginalMember(owner = "runetek4.client!ql", name = "i", descriptor = "Lclient!na;")
    public static final JString WM2 = Static28.parse("::wm2");
    @OriginalMember(owner = "runetek4.client!hk", name = "db", descriptor = "Lclient!na;")
    public static final JString WM3 = Static28.parse("::wm3");
    @OriginalMember(owner = "runetek4.client!dg", name = "f", descriptor = "Z")
    public static boolean displayFps = false;
    @OriginalMember(owner = "runetek4.client!nj", name = "a", descriptor = "Z")
    public static boolean shiftClick = false;
    @OriginalMember(owner = "runetek4.client!qc", name = "U", descriptor = "I")
    public static int rectDebug = 0;
    @OriginalMember(owner = "runetek4.client!jg", name = "e", descriptor = "Z")
    public static boolean qaOpTest = false;

    @OriginalMember(owner = "runetek4.client!en", name = "a", descriptor = "(IIIB)V")
    public static void teleport(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(66) JString local66 = JString.concatenate(new JString[] {aClass100_521, JString.parseInt(arg2), Static159.aClass100_760, JString.parseInt(arg0 >> 6), Static159.aClass100_760, JString.parseInt(arg1 >> 6), Static159.aClass100_760, JString.parseInt(arg0 & 0x3F), Static159.aClass100_760, JString.parseInt(arg1 & 0x3F) });
        local66.printToConsole();
        execute(local66);
    }

    @OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(Lclient!na;Z)V")
    public static void execute(@OriginalArg(0) JString chatTyped) {
        if (Static191.staffModLevel >= 2) {
            @Pc(18) int local18;
            @Pc(38) int local38;
            @Pc(29) Runtime local29;
            if (chatTyped.equalsIgnoreCase(GC)) {
                client.unloadSoft();
                for (local18 = 0; local18 < 10; local18++) {
                    System.gc();
                }
                local29 = Runtime.getRuntime();
                local38 = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
                Chat.addMessage(null, 0, JString.concatenate(new JString[] {aClass100_892, JString.parseInt(local38), DEBUG_MEM_UNIT}));
            }
            @Pc(117) int local117;
            if (chatTyped.equalsIgnoreCase(MM)) {
                client.unloadSoft();
                for (local18 = 0; local18 < 10; local18++) {
                    System.gc();
                }
                local29 = Runtime.getRuntime();
                local38 = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
                Chat.addMessage(null, 0, JString.concatenate(new JString[] {aClass100_893, JString.parseInt(local38), DEBUG_MEM_UNIT}));
                Player.method501();
                client.unloadSoft();
                for (local117 = 0; local117 < 10; local117++) {
                    System.gc();
                }
                local38 = (int) ((local29.totalMemory() - local29.freeMemory()) / 1024L);
                Chat.addMessage(null, 0, JString.concatenate(new JString[] {aClass100_1093, JString.parseInt(local38), DEBUG_MEM_UNIT}));
            }
            if (chatTyped.equalsIgnoreCase(PCACHESIZE)) {
                Chat.addMessage(null, 0, JString.concatenate(new JString[] {aClass100_335, JString.parseInt(Static198.method1029()) }));
            }
            if (GlRenderer.enabled && chatTyped.equalsIgnoreCase(CARDMEM)) {
                System.out.println("oncard_geometry:" + Static63.oncard_geometry);
                System.out.println("oncard_2d:" + Static63.oncard_2d);
                System.out.println("oncard_texture:" + Static63.oncard_texture);
            }
            if (chatTyped.equalsIgnoreCase(CLIENTDROP)) {
                Game.tryReconnect();
            }
            if (chatTyped.equalsIgnoreCase(CLIENTJS5DROP)) {
                client.js5NetQueue.clientDrop();
            }
            if (chatTyped.equalsIgnoreCase(SERVERJS5DROP)) {
                client.js5NetQueue.serverDrop();
            }
            if (chatTyped.equalsIgnoreCase(BREAKCON)) {
                GameShell.signLink.breakConnection();
                Static124.gameServerSocket.breakConnection();
                client.js5NetQueue.method2323();
            }
            if (chatTyped.equalsIgnoreCase(REPLACECANVAS)) {
                Static35.canvasReplaceRecommended = true;
            }
            if (chatTyped.equalsIgnoreCase(REBUILD)) {
                Game.processGameStatus(25);
            }
            if (chatTyped.equalsIgnoreCase(FPSON)) {
                displayFps = true;
            }
            if (chatTyped.equalsIgnoreCase(FPSOFF)) {
                displayFps = false;
            }
            if (chatTyped.equalsIgnoreCase(WM0)) {
                DisplayMode.setWindowMode(false, 0, -1, -1);
            }
            if (chatTyped.equalsIgnoreCase(WM1)) {
                DisplayMode.setWindowMode(false, 1, -1, -1);
            }
            if (chatTyped.equalsIgnoreCase(WM2)) {
                DisplayMode.setWindowMode(false, 2, -1, -1);
            }
            if (chatTyped.equalsIgnoreCase(WM3)) {
                DisplayMode.setWindowMode(false, 3, 1024, 768);
            }
            if (chatTyped.equalsIgnoreCase(NOCLIP)) {
                for (local18 = 0; local18 < 4; local18++) {
                    for (local38 = 1; local38 < 103; local38++) {
                        for (local117 = 1; local117 < 103; local117++) {
                            PathFinder.collisionMaps[local18].flags[local38][local117] = 0;
                        }
                    }
                }
            }
            if (chatTyped.startsWith(SETPARTICLES)) {
                Preferences.setParticles(chatTyped.substring(15).parseInt());
                Preferences.write(GameShell.signLink);
                Preferences.sentToServer = false;
            }
            if (chatTyped.startsWith(COMMAND_FPS) && client.modeWhere != 0) {
                GameShell.setFpsTarget(chatTyped.substring(6).parseInt());
            }
            if (chatTyped.equalsIgnoreCase(ERRORTEST)) {
                throw new RuntimeException();
            }
            if (chatTyped.startsWith(RECT_DEBUG)) {
                rectDebug = chatTyped.substring(12).trim().parseInt();
                Chat.addMessage(null, 0, JString.concatenate(new JString[] {RECT_DEBUG_EQUALS, JString.parseInt(rectDebug) }));
            }
            if (chatTyped.equalsIgnoreCase(QA_OP_TEST)) {
                qaOpTest = true;
            }
            if (chatTyped.equalsIgnoreCase(TWEEN)) {
                if (SeqType.tween) {
                    SeqType.tween = false;
                    Chat.addMessage(null, 0, FORCED_TWEENING_DISABLED);
                } else {
                    SeqType.tween = true;
                    Chat.addMessage(null, 0, FORCED_TWEENING_ENABLED);
                }
            }
            if (chatTyped.equalsIgnoreCase(SHIFTCLICK)) {
                if (shiftClick) {
                    SHIFTCLICK_DISABLED.printToConsole();
                    shiftClick = false;
                } else {
                    SHIFTCLICK_ENABLED.printToConsole();
                    shiftClick = true;
                }
            }
        }
        Static6.outboundBuffer.pIsaac1(44);
        Static6.outboundBuffer.p1(chatTyped.length() - 1);
        Static6.outboundBuffer.pjstr(chatTyped.substring(2));
    }
}
