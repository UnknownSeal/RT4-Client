package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static98 {

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "I")
	public static int anInt2510;

	@OriginalMember(owner = "runetek4.client!hj", name = "e", descriptor = "I")
	public static int anInt2512;

	@OriginalMember(owner = "runetek4.client!hj", name = "m", descriptor = "Lclient!ve;")
	public static Js5 aClass153_42;

	@OriginalMember(owner = "runetek4.client!hj", name = "d", descriptor = "Lclient!na;")
	public static final JagString HINT_HEADICONS = Static28.parse("hint_headicons");

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(II)V")
	public static void method1964(@OriginalArg(0) int arg0) {
		Static217.anInt4901 = -1;
		Static142.anInt3482 = -1;
		Static13.anInt435 = arg0;
		Static38.method965();
	}

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(Lclient!na;B)Z")
	public static boolean method1965(@OriginalArg(0) JagString username) {
		if (username == null) {
			return false;
		}
		for (@Pc(12) int i = 0; i < Static9.friendCount; i++) {
			if (username.equalsIgnoreCase(Static122.friendName[i])) {
				return true;
			}
		}
		if (username.equalsIgnoreCase(Static173.localPlayer.username)) {
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
	public static void method1966(@OriginalArg(0) int arg0, @OriginalArg(1) long arg1, @OriginalArg(3) JagString arg2, @OriginalArg(4) int arg3, @OriginalArg(5) short arg4, @OriginalArg(6) JagString arg5, @OriginalArg(7) int arg6) {
		if (Static60.aBoolean108 || PreciseSleep.anInt5204 >= 500) {
			return;
		}
		Static254.aClass100Array168[PreciseSleep.anInt5204] = arg5;
		Static233.aClass100Array160[PreciseSleep.anInt5204] = arg2;
		Static190.anIntArray382[PreciseSleep.anInt5204] = arg0 == -1 ? Static35.anInt1092 : arg0;
		Static39.aShortArray6[PreciseSleep.anInt5204] = arg4;
		Static159.aLongArray5[PreciseSleep.anInt5204] = arg1;
		Static196.anIntArray408[PreciseSleep.anInt5204] = arg3;
		Static56.anIntArray142[PreciseSleep.anInt5204] = arg6;
		PreciseSleep.anInt5204++;
	}
}
