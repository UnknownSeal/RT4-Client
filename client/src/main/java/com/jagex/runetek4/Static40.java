package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static40 {

	@OriginalMember(owner = "runetek4.client!da", name = "ab", descriptor = "I")
	public static int anInt1275;

	@OriginalMember(owner = "client!da", name = "O", descriptor = "Lclient!na;")
	public static final JString aClass100_253 = JString.parse("(U0a )2 via: ");

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(IIILclient!be;)V")
	public static void method1015(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) Component arg2) {
		if (ClientScriptRunner.aClass13_14 != null || ClientScriptRunner.aBoolean108 || (arg2 == null || Static89.method1836(arg2) == null)) {
			return;
		}
		ClientScriptRunner.aClass13_14 = arg2;
		ClientScriptRunner.aClass13_1 = Static89.method1836(arg2);
		ClientScriptRunner.anInt5388 = arg1;
		ClientScriptRunner.aBoolean172 = false;
		ClientScriptRunner.anInt4851 = 0;
		ClientScriptRunner.anInt4035 = arg0;
	}

}
