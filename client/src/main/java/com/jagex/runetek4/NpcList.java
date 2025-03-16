package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.Npc;
import org.openrs2.deob.annotation.OriginalMember;

public class NpcList {
    @OriginalMember(owner = "runetek4.client!nm", name = "S", descriptor = "[Lclient!km;")
    public static final Npc[] npcs = new Npc[32768];
}
