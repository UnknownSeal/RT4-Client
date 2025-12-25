package com.jagex.runetek4.entity.player;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.entity.npc.NpcList;
import com.jagex.runetek4.util.string.JString;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PlayerList {
    @OriginalMember(owner = "client!mi", name = "S", descriptor = "[Lclient!e;")
    public static final Player[] players = new Player[2048];

    @OriginalMember(owner = "client!ja", name = "l", descriptor = "[Lclient!wa;")
    public static final Packet[] appearanceCache = new Packet[2048];

    @OriginalMember(owner = "client!ib", name = "f", descriptor = "[I")
    public static final int[] playerIds = new int[2048];

    @OriginalMember(owner = "runetek4.client!ke", name = "Y", descriptor = "[I")
    public static final int[] anIntArray309 = new int[] { 1, 4 };

    @OriginalMember(owner = "client!bf", name = "E", descriptor = "I")
    public static int localPid = -1;

    @OriginalMember(owner = "client!nk", name = "O", descriptor = "Lclient!e;")
    public static Player self;

    @OriginalMember(owner = "client!vl", name = "j", descriptor = "I")
    public static int playerCount = 0;

    @OriginalMember(owner = "runetek4.client!i", name = "Ub", descriptor = "[Lclient!na;")
    public static JString[] playerNames;

    @OriginalMember(owner = "runetek4.client!vg", name = "a", descriptor = "[Lclient!na;")
    public static JString[] playerNames2;

    @OriginalMember(owner = "client!fk", name = "b", descriptor = "(I)V")
    public static void updatePlayers() {
        for (@Pc(7) int currentPlayerIndex = -1; currentPlayerIndex < playerCount; currentPlayerIndex++) {
            @Pc(21) int actualIndex;
            if (currentPlayerIndex == -1) {
                actualIndex = 2047;
            } else {
                actualIndex = playerIds[currentPlayerIndex];
            }
            @Pc(31) Player player = players[actualIndex];
            if (player != null) {
                NpcList.updateEntity(player.getSize(), player);
            }
        }
    }
}
