package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;

public class PlayerList {
    @OriginalMember(owner = "runetek4.client!mi", name = "S", descriptor = "[Lclient!e;")
    public static final Player[] players = new Player[2048];
    @OriginalMember(owner = "runetek4.client!ja", name = "l", descriptor = "[Lclient!wa;")
    public static final Packet[] PLAYER_APPEARANCE_PACKET = new Packet[2048];
    @OriginalMember(owner = "client!bf", name = "E", descriptor = "I")
    public static int selfId = -1;
    @OriginalMember(owner = "runetek4.client!nk", name = "O", descriptor = "Lclient!e;")
    public static Player self;
    @OriginalMember(owner = "runetek4.client!vl", name = "j", descriptor = "I")
    public static int playerCount = 0;
}
