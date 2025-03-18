package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.Npc;
import org.openrs2.deob.annotation.OriginalMember;

public class NpcList {
    @OriginalMember(owner = "runetek4.client!nm", name = "S", descriptor = "[Lclient!km;")
    public static final Npc[] npcs = new Npc[32768];
    @OriginalMember(owner = "runetek4.client!cj", name = "i", descriptor = "[I")
    public static final int[] npcIds = new int[32768];
    @OriginalMember(owner = "runetek4.client!wd", name = "g", descriptor = "I")
    public static int npcCount = 0;
}
