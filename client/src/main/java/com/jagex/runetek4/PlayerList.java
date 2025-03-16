package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;

public class PlayerList {
    @OriginalMember(owner = "client!bf", name = "E", descriptor = "I")
    public static int selfId = -1;
    @OriginalMember(owner = "runetek4.client!nk", name = "O", descriptor = "Lclient!e;")
    public static Player self;
}
