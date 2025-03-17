package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static251 {

	@OriginalMember(owner = "runetek4.client!ug", name = "d", descriptor = "[I")
	public static int[] blendChroma;

	@OriginalMember(owner = "runetek4.client!ug", name = "p", descriptor = "I")
	public static int anInt5457;

	@OriginalMember(owner = "runetek4.client!ug", name = "m", descriptor = "I")
	public static int anInt5454 = 0;

	@OriginalMember(owner = "runetek4.client!ug", name = "b", descriptor = "(B)V")
	public static void removeSoft() {
		Static45.aClass99_6.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(II)V")
	public static void method4278(@OriginalArg(0) int arg0) {
		if (Static241.anIntArray522 == null || Static241.anIntArray522.length < arg0) {
			Static241.anIntArray522 = new int[arg0];
		}
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(IB)V")
	public static void method4279(@OriginalArg(0) int arg0) {
		@Pc(14) DelayedStateChange local14 = Static238.method4143(9, arg0);
		local14.method1007();
	}
}
