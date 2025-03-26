package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class PlayerSkillXpTable {

    @OriginalMember(owner = "runetek4.client!hk", name = "fb", descriptor = "[I")
    public static final int[] boostedLevels = new int[25];

    @OriginalMember(owner = "runetek4.client!lb", name = "p", descriptor = "[I")
	public static final int[] baseLevels = new int[25];

    @OriginalMember(owner = "runetek4.client!sg", name = "b", descriptor = "[I")
    public static final int[] experience = new int[25];

    @OriginalMember(owner = "client!h", name = "S", descriptor = "[I")
    public static final int[] xpLevelLookup = new int[99];

    @OriginalMember(owner = "runetek4.client!oj", name = "z", descriptor = "[Z")
    public static final boolean[] ENABLED_SKILLS = new boolean[] { true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, false };

    @OriginalMember(owner = "runetek4.client!ud", name = "T", descriptor = "[I")
    public static final int[] updatedStats = new int[32];

    @OriginalMember(owner = "runetek4.client!ha", name = "m", descriptor = "I")
    public static int updatedStatsWriterIndex = 0;

    static {
        @Pc(4) int experience = 0;
        for (@Pc(6) int i = 0; i < 99; i++) {
            @Pc(13) int level = i + 1;
            @Pc(26) int delta = (int) (Math.pow(2.0D, (double) level / 7.0D) * 300.0D + (double) level);
            experience += delta;
            PlayerSkillXpTable.xpLevelLookup[i] = experience / 4;
        }
    }
}
