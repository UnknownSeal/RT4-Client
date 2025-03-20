package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static230 {

	@OriginalMember(owner = "runetek4.client!sj", name = "r", descriptor = "Lclient!ve;")
	public static Js5 modelArchive;

	@OriginalMember(owner = "runetek4.client!sj", name = "D", descriptor = "I")
	public static int anInt5158;

	@OriginalMember(owner = "runetek4.client!sj", name = "u", descriptor = "Z")
	public static boolean aBoolean250 = false;

	@OriginalMember(owner = "runetek4.client!sj", name = "c", descriptor = "(I)V")
	public static void clear() {
		Static250.aClass99_33.clean();
		Static139.aClass99_21.clean();
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(IIBIII)V")
	public static void method3950(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 >= Static172.anInt4164 && arg3 <= FloorUnderlayTypeList.anInt5063 && Static267.anInt5773 <= arg4 && Static106.anInt2869 >= arg2) {
			Static176.method3308(arg2, arg3, arg4, arg0, arg1);
		} else {
			Static163.method3105(arg1, arg3, arg4, arg0, arg2);
		}
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "a", descriptor = "(ILclient!na;)V")
	public static void method3954(@OriginalArg(1) JString arg0) {
		client.settings = arg0;
		if (GameShell.signLink.applet == null) {
			return;
		}
		try {
			@Pc(17) JString local17 = Static272.aClass100_989.fromParameters(GameShell.signLink.applet);
			@Pc(23) JString local23 = Static246.aClass100_1029.fromParameters(GameShell.signLink.applet);
			@Pc(48) JString local48 = JString.concatenate(new JString[] { local17, Static142.aClass100_667, arg0, Static276.aClass100_1095, local23 });
			if (arg0.length() == 0) {
				local48 = JString.concatenate(new JString[] { local48, Static245.aClass100_1018 });
			} else {
				local48 = JString.concatenate(new JString[] { local48, Static263.aClass100_1082, DateUtil.getDateString(MonotonicTime.currentTimeMillis() + 94608000000L), Protocol.MAX_AGE, JString.method2929(94608000L) });
			}
			JString.concatenate(new JString[] { BZip2State.aClass100_821, local48, Static223.aClass100_946 }).method3134(GameShell.signLink.applet);
		} catch (@Pc(124) Throwable local124) {
		}
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "c", descriptor = "(II)V")
	public static void method3956(@OriginalArg(0) int arg0) {
		if (Static14.anInt441 == 0) {
			Static172.aClass3_Sub3_Sub4_2.method4447(arg0);
		} else {
			Static253.anInt5527 = arg0;
		}
	}
}
