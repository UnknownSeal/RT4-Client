package com.jagex.ui.chat;

import com.jagex.entity.player.PlayerList;
import com.jagex.entity.npc.Npc;
import com.jagex.entity.npc.NpcList;
import com.jagex.entity.player.Player;
import com.jagex.core.utils.string.JString;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class OverHeadChat {

    @OriginalMember(owner = "client!bf", name = "I", descriptor = "[I")
    public static final int[] CHAT_COLORS = new int[]{16776960, 16711680, 65280, 65535, 16711935, 16777215};

    @OriginalMember(owner = "client!pg", name = "fb", descriptor = "I")
    public static final int capacity = 50;

    @OriginalMember(owner = "client!pg", name = "P", descriptor = "[Lclient!na;")
    public static final JString[] messages = new JString[capacity];

    @OriginalMember(owner = "client!pg", name = "R", descriptor = "[I")
    public static final int[] anIntArray385 = new int[capacity];

    @OriginalMember(owner = "client!pg", name = "lb", descriptor = "[I")
    public static final int[] anIntArray392 = new int[capacity];

    @OriginalMember(owner = "runetek4.client!pg", name = "eb", descriptor = "[I")
    public static final int[] colors = new int[capacity];

    @OriginalMember(owner = "client!pg", name = "Q", descriptor = "[I")
    public static final int[] loops = new int[capacity];

    @OriginalMember(owner = "client!pg", name = "kb", descriptor = "[I")
    public static final int[] effects = new int[capacity];

    @OriginalMember(owner = "client!pg", name = "Z", descriptor = "[I")
    public static final int[] anIntArray387 = new int[capacity];

    @OriginalMember(owner = "client!pg", name = "cb", descriptor = "[I")
    public static final int[] anIntArray389 = new int[capacity];

    @OriginalMember(owner = "client!wi", name = "bb", descriptor = "I")
    public static int sizes = 0;

    @OriginalMember(owner = "client!bi", name = "f", descriptor = "(B)V")
    public static void tickChatTimers() {
        @Pc(11) int i;
        for (i = -1; i < PlayerList.playerCount; i++) {
            @Pc(22) int pid;
            if (i == -1) {
                pid = 2047;
            } else {
                pid = PlayerList.playerIds[i];
            }
            @Pc(30) Player player = PlayerList.players[pid];
            if (player != null && player.chatLoops > 0) {
                player.chatLoops--;
                if (player.chatLoops == 0) {
                    player.chatMessage = null;
                }
            }
        }
        for (i = 0; i < NpcList.npcCount; i++) {
            @Pc(68) int nid = NpcList.npcIds[i];
            @Pc(72) Npc npc = NpcList.npcs[nid];
            if (npc != null && npc.chatLoops > 0) {
                npc.chatLoops--;
                if (npc.chatLoops == 0) {
                    npc.chatMessage = null;
                }
            }
        }
    }
}
