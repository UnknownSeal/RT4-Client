package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public class PlayerSkillXpTable {
    @OriginalMember(owner = "runetek4.client!hk", name = "fb", descriptor = "[I")
    public static final int[] skillLevel = new int[25];
    @OriginalMember(owner = "runetek4.client!lb", name = "p", descriptor = "[I")
	public static final int[] skillBaseLevel = new int[25];
    @OriginalMember(owner = "runetek4.client!sg", name = "b", descriptor = "[I")
    public static final int[] skillExperience = new int[25];
}
