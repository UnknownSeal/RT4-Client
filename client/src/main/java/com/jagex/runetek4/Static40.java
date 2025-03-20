package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.game.config.msitype.MSIType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static40 {

	@OriginalMember(owner = "runetek4.client!da", name = "ab", descriptor = "I")
	public static int anInt1275;

	@OriginalMember(owner = "client!da", name = "O", descriptor = "Lclient!na;")
	public static final JString aClass100_253 = JString.parse("(U0a )2 via: ");

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(ILclient!ve;Z)Lclient!ok;")
	public static IndexedSprite method1010(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1) {
		return Static254.method4346(arg1, arg0) ? Static276.method4614() : null;
	}

	@OriginalMember(owner = "client!da", name = "c", descriptor = "(II)Lclient!aa;")
	public static MSIType get(@OriginalArg(0) int arg0) {
		@Pc(10) MSIType local10 = (MSIType) PreciseSleep.aClass99_29.get(arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(20) byte[] bytes = Static98.aClass153_42.getfile(34, arg0);
		local10 = new MSIType();
		if (bytes != null) {
			local10.decode(new Packet(bytes));
		}
		PreciseSleep.aClass99_29.put(local10, arg0);
		return local10;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;")
	public static String method1014(@OriginalArg(0) String arg0, @OriginalArg(1) String arg1, @OriginalArg(3) String arg2) {
		for (@Pc(5) int local5 = arg2.indexOf(arg0); local5 != -1; local5 = arg2.indexOf(arg0, local5 + arg1.length())) {
			arg2 = arg2.substring(0, local5) + arg1 + arg2.substring(arg0.length() + local5);
		}
		return arg2;
	}

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
