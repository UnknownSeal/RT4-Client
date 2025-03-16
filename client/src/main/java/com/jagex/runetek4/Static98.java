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
	public static final JString HINT_HEADICONS = JString.parse("hint_headicons");

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(II)V")
	public static void method1964(@OriginalArg(0) int arg0) {
		Static217.anInt4901 = -1;
		Static142.anInt3482 = -1;
		Static13.anInt435 = arg0;
		Static38.method965();
	}

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(Lclient!na;B)Z")
	public static boolean method1965(@OriginalArg(0) JString username) {
		if (username == null) {
			return false;
		}
		for (@Pc(12) int i = 0; i < FriendList.friendCount; i++) {
			if (username.equalsIgnoreCase(Static122.friendName[i])) {
				return true;
			}
		}
		if (username.equalsIgnoreCase(PlayerList.self.username)) {
			return true;
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "runetek4.client!hj", name = "a", descriptor = "(IJBLclient!na;ISLclient!na;I)V")
	public static void addActionRow(@OriginalArg(0) int arg0, @OriginalArg(1) long arg1, @OriginalArg(3) JString arg2, @OriginalArg(4) int arg3, @OriginalArg(5) short arg4, @OriginalArg(6) JString arg5, @OriginalArg(7) int arg6) {
		if (ClientScriptRunner.aBoolean108 || MiniMenu.menuActionRow >= 500) {
			return;
		}
		Static254.aClass100Array168[MiniMenu.menuActionRow] = arg5;
		ClientScriptRunner.aClass100Array160[MiniMenu.menuActionRow] = arg2;
		Static190.anIntArray382[MiniMenu.menuActionRow] = arg0 == -1 ? Static35.anInt1092 : arg0;
		Static39.aShortArray6[MiniMenu.menuActionRow] = arg4;
		Static159.aLongArray5[MiniMenu.menuActionRow] = arg1;
		Static196.anIntArray408[MiniMenu.menuActionRow] = arg3;
		Static56.anIntArray142[MiniMenu.menuActionRow] = arg6;
		MiniMenu.menuActionRow++;
	}
}
