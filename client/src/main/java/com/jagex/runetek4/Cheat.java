package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.game.world.entity.PlayerAppearance;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class Cheat {
    @OriginalMember(owner = "runetek4.client!p", name = "f", descriptor = "Lclient!na;")
    public static final JString SERVERJS5DROP = JString.parse("::serverjs5drop");
    @OriginalMember(owner = "runetek4.client!v", name = "d", descriptor = "Lclient!na;")
    public static final JString CLIENTDROP = JString.parse("::clientdrop");
    @OriginalMember(owner = "runetek4.client!dg", name = "b", descriptor = "Lclient!na;")
    public static final JString SHIFTCLICK_ENABLED = JString.parse("Shift)2click ENABLED(Q");
    @OriginalMember(owner = "runetek4.client!nh", name = "hb", descriptor = "Lclient!na;")
    public static final JString COMMAND_FPS = JString.parse("::fps ");
    @OriginalMember(owner = "runetek4.client!nb", name = "e", descriptor = "Lclient!na;")
    public static final JString BREAKCON = JString.parse("::breakcon");
    @OriginalMember(owner = "runetek4.client!wf", name = "s", descriptor = "Lclient!na;")
    public static final JString FORCED_TWEENING_DISABLED = JString.parse("Forced tweening disabled)3");
    @OriginalMember(owner = "runetek4.client!wd", name = "f", descriptor = "Lclient!na;")
    public static final JString ERRORTEST = JString.parse("::errortest");
    @OriginalMember(owner = "runetek4.client!wb", name = "j", descriptor = "Lclient!na;")
    public static final JString aClass100_1093 = JString.parse("Memory after cleanup=");
    @OriginalMember(owner = "runetek4.client!th", name = "h", descriptor = "Lclient!na;")
    public static final JString SETPARTICLES = JString.parse("::setparticles");
    @OriginalMember(owner = "runetek4.client!rc", name = "K", descriptor = "Lclient!na;")
    public static final JString RECT_DEBUG = JString.parse("::rect_debug");
    @OriginalMember(owner = "client!hh", name = "b", descriptor = "Lclient!na;")
    public static final JString TELE = JString.parse("::tele ");
    @OriginalMember(owner = "client!hh", name = "k", descriptor = "Lclient!na;")
    public static final JString WM0 = JString.parse("::wm0");
    @OriginalMember(owner = "client!tg", name = "i", descriptor = "Lclient!na;")
    public static final JString PCACHESIZE = JString.parse("::pcachesize");
    @OriginalMember(owner = "client!fh", name = "ab", descriptor = "Lclient!na;")
    public static final JString NOCLIP = JString.parse("::noclip");
    @OriginalMember(owner = "client!en", name = "e", descriptor = "Lclient!na;")
    public static final JString FPSOFF = JString.parse("::fpsoff");
    @OriginalMember(owner = "client!ja", name = "k", descriptor = "Lclient!na;")
    public static final JString DEBUG_FPS = JString.parse("Fps:");
    @OriginalMember(owner = "client!qh", name = "i", descriptor = "Lclient!na;")
    public static final JString MEM = JString.parse("Mem:");
    @OriginalMember(owner = "client!bi", name = "W", descriptor = "Lclient!na;")
    public static final JString DEBUG_MEMORY_UNIT = JString.parse("k");
    @OriginalMember(owner = "client!dg", name = "d", descriptor = "Lclient!na;")
    public static final JString DEBUG_CACHE = JString.parse("Cache:");
    @OriginalMember(owner = "client!hm", name = "Y", descriptor = "Lclient!na;")
    public static final JString DEBUG_FPS2 = JString.parse("Fps:");
    @OriginalMember(owner = "client!c", name = "Y", descriptor = "Lclient!na;")
    public static final JString DEBUG_MEM = JString.parse("Mem:");
    @OriginalMember(owner = "client!bg", name = "z", descriptor = "Lclient!na;")
    public static final JString DEBUG_MEM_UNIT = JString.parse("k");
    @OriginalMember(owner = "client!q", name = "h", descriptor = "Lclient!na;")
    public static final JString DEBUG_CARD = JString.parse("Card:");
    @OriginalMember(owner = "client!oi", name = "b", descriptor = "Lclient!na;")
    public static final JString QA_OP_TEST = JString.parse("::qa_op_test");
    @OriginalMember(owner = "client!jk", name = "F", descriptor = "Lclient!na;")
    public static final JString TWEEN = JString.parse("::tween");
    @OriginalMember(owner = "client!j", name = "z", descriptor = "Lclient!na;")
    public static final JString SHIFTCLICK = JString.parse("::shiftclick");
    @OriginalMember(owner = "client!qf", name = "N", descriptor = "Lclient!na;")
    public static final JString CARDMEM = JString.parse("::cardmem");
    @OriginalMember(owner = "client!e", name = "Ac", descriptor = "Lclient!na;")
    public static final JString FORCED_TWEENING_ENABLED = JString.parse("Forced tweening ENABLED(Q");
    @OriginalMember(owner = "client!md", name = "T", descriptor = "Lclient!na;")
    public static final JString SHIFTCLICK_DISABLED = JString.parse("Shift)2click disabled)3");
    @OriginalMember(owner = "client!wh", name = "o", descriptor = "Lclient!na;")
    public static final JString RECT_DEBUG_EQUALS = JString.parse("rect_debug=");
    @OriginalMember(owner = "client!dh", name = "e", descriptor = "Lclient!na;")
    public static final JString aClass100_335 = JString.parse("Number of player models in cache:");
    @OriginalMember(owner = "client!qh", name = "c", descriptor = "Lclient!na;")
    public static final JString aClass100_893 = JString.parse("Memory before cleanup=");
    @OriginalMember(owner = "client!qg", name = "Z", descriptor = "Lclient!na;")
    public static final JString aClass100_892 = JString.parse("mem=");
    @OriginalMember(owner = "client!gg", name = "Y", descriptor = "Lclient!na;")
    public static final JString GC = JString.parse("::gc");
    @OriginalMember(owner = "client!wk", name = "u", descriptor = "Lclient!na;")
    public static final JString CLIENTJS5DROP = JString.parse("::clientjs5drop");
    @OriginalMember(owner = "client!id", name = "c", descriptor = "Lclient!na;")
    public static final JString FPSON = JString.parse("::fpson");
    @OriginalMember(owner = "client!md", name = "Q", descriptor = "Lclient!na;")
    public static final JString MM = JString.parse("::mm");
    @OriginalMember(owner = "client!j", name = "M", descriptor = "Lclient!na;")
    public static final JString REPLACECANVAS = JString.parse("::replacecanvas");
    @OriginalMember(owner = "client!li", name = "p", descriptor = "Lclient!na;")
    public static final JString REBUILD = JString.parse("::rebuild");
    @OriginalMember(owner = "client!oi", name = "j", descriptor = "Lclient!na;")
    public static final JString WM1 = JString.parse("::wm1");
    @OriginalMember(owner = "client!ql", name = "i", descriptor = "Lclient!na;")
    public static final JString WM2 = JString.parse("::wm2");
    @OriginalMember(owner = "client!hk", name = "db", descriptor = "Lclient!na;")
    public static final JString WM3 = JString.parse("::wm3");
    @OriginalMember(owner = "client!dg", name = "f", descriptor = "Z")
    public static boolean displayFps = false;
    @OriginalMember(owner = "client!nj", name = "a", descriptor = "Z")
    public static boolean shiftClick = false;
    @OriginalMember(owner = "client!qc", name = "U", descriptor = "I")
    public static int rectDebug = 0;
    @OriginalMember(owner = "client!jg", name = "e", descriptor = "Z")
    public static boolean qaOpTest = false;

    @OriginalMember(owner = "client!en", name = "a", descriptor = "(IIIB)V")
    public static void teleport(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        @Pc(66) JString command = JString.concatenate(new JString[] {TELE, JString.parseInt(arg2), JString.aClass100_760, JString.parseInt(arg0 >> 6), JString.aClass100_760, JString.parseInt(arg1 >> 6), JString.aClass100_760, JString.parseInt(arg0 & 0x3F), JString.aClass100_760, JString.parseInt(arg1 & 0x3F) });
        command.printToConsole();
        execute(command);
    }

    @OriginalMember(owner = "client!k", name = "a", descriptor = "(Lclient!na;Z)V")
    public static void execute(@OriginalArg(0) JString chatTyped) {
        if (LoginManager.staffModLevel >= 2) {
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
                Chat.addMessage(null, 0, JString.concatenate(new JString[] {aClass100_335, JString.parseInt(PlayerAppearance.getModelCacheSize()) }));
            }
            if (GlRenderer.enabled && chatTyped.equalsIgnoreCase(CARDMEM)) {
                System.out.println("oncard_geometry:" + GlCleaner.oncard_geometry);
                System.out.println("oncard_2d:" + GlCleaner.oncard_2d);
                System.out.println("oncard_texture:" + GlCleaner.oncard_texture);
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
                Protocol.gameServerSocket.breakConnection();
                client.js5NetQueue.breakConnection();
            }
            if (chatTyped.equalsIgnoreCase(REPLACECANVAS)) {
                GameShell.canvasReplaceRecommended = true;
            }
            if (chatTyped.equalsIgnoreCase(REBUILD)) {
                client.processGameStatus(25);
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
        Protocol.outboundBuffer.pIsaac1(44);
        Protocol.outboundBuffer.p1(chatTyped.length() - 1);
        Protocol.outboundBuffer.pjstr(chatTyped.substring(2));
    }
}
