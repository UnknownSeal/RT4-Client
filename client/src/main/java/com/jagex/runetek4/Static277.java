package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static277 {

	@OriginalMember(owner = "runetek4.client!wi", name = "R", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array12;

	@OriginalMember(owner = "runetek4.client!wi", name = "ab", descriptor = "I")
	public static int anInt5853;

	@OriginalMember(owner = "runetek4.client!wi", name = "db", descriptor = "I")
	public static int anInt5855;

	@OriginalMember(owner = "runetek4.client!wi", name = "W", descriptor = "I")
	public static int anInt5850 = 0;

	@OriginalMember(owner = "runetek4.client!wi", name = "bb", descriptor = "I")
	public static int anInt5854 = 0;

	@OriginalMember(owner = "runetek4.client!wi", name = "hb", descriptor = "[[Z")
	public static final boolean[][] aBooleanArrayArray4 = new boolean[][] { { true, true, true }, { false, false }, { false, true }, { true, false }, { false, true, true }, { true, false, true }, { false, true, false }, { true, false, false } };

	@OriginalMember(owner = "runetek4.client!wi", name = "c", descriptor = "(II)Z")
	public static boolean menuHasAddFriend(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return false;
		}
		@Pc(12) int local12 = MiniMenu.actions[arg0];
		if (local12 >= 2000) {
			local12 -= 2000;
		}
		return local12 == 1003;
	}

}
