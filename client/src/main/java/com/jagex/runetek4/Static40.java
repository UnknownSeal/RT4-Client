package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.game.config.msitype.MSIType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static40 {

	@OriginalMember(owner = "runetek4.client!da", name = "M", descriptor = "Z")
	public static boolean aBoolean78;

	@OriginalMember(owner = "runetek4.client!da", name = "ab", descriptor = "I")
	public static int anInt1275;

	@OriginalMember(owner = "runetek4.client!da", name = "O", descriptor = "Lclient!na;")
	public static final JString aClass100_253 = JString.parse("(U0a )2 via: ");

	@OriginalMember(owner = "runetek4.client!da", name = "a", descriptor = "(ILclient!ve;Z)Lclient!ok;")
	public static IndexedSprite method1010(@OriginalArg(0) int arg0, @OriginalArg(1) Js5 arg1) {
		return Static254.method4346(arg1, arg0) ? Static276.method4614() : null;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "c", descriptor = "(II)Lclient!aa;")
	public static MSIType get(@OriginalArg(0) int arg0) {
		@Pc(10) MSIType local10 = (MSIType) PreciseSleep.aClass99_29.get((long) arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(20) byte[] bytes = Static98.aClass153_42.getfile(34, arg0);
		local10 = new MSIType();
		if (bytes != null) {
			local10.decode(new Packet(bytes));
		}
		PreciseSleep.aClass99_29.put(local10, (long) arg0);
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "a", descriptor = "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)Ljava/lang/String;")
	public static String method1014(@OriginalArg(0) String arg0, @OriginalArg(1) String arg1, @OriginalArg(3) String arg2) {
		for (@Pc(5) int local5 = arg2.indexOf(arg0); local5 != -1; local5 = arg2.indexOf(arg0, local5 + arg1.length())) {
			arg2 = arg2.substring(0, local5) + arg1 + arg2.substring(arg0.length() + local5);
		}
		return arg2;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "a", descriptor = "(IIILclient!be;)V")
	public static void method1015(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) Component arg2) {
		if (ClientScriptRunner.aClass13_14 != null || ClientScriptRunner.aBoolean108 || (arg2 == null || Static89.method1836(arg2) == null)) {
			return;
		}
		ClientScriptRunner.aClass13_14 = arg2;
		Static4.aClass13_1 = Static89.method1836(arg2);
		Static246.anInt5388 = arg1;
		Static138.aBoolean172 = false;
		Static213.anInt4851 = 0;
		Static165.anInt4035 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "a", descriptor = "(IIIILclient!na;JI)V")
	public static void method1016(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) JString password, @OriginalArg(5) long name, @OriginalArg(6) int arg5) {
		@Pc(8) Packet local8 = new Packet(128);
		local8.p1(10);
		local8.p2((int) (Math.random() * 99999.0D));
		local8.p2(530);
		local8.p8(name);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.pjstr(password);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.p2(client.affiliate);
		local8.p1(arg0);
		local8.p1(arg2);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.p2(arg5);
		local8.p2(arg1);
		local8.p4((int) (Math.random() * 9.9999999E7D));
		local8.rsaenc(Static86.RSA_EXPONENT, Static86.RSA_MODULUS);
		Protocol.outboundBuffer.offset = 0;
		Protocol.outboundBuffer.p1(36);
		Protocol.outboundBuffer.p1(local8.offset);
		Protocol.outboundBuffer.pdata(local8.data, local8.offset);
		Static223.reply = -3;
		CreateManager.step = 1;
		Static226.loops = 0;
		Static57.errors = 0;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "h", descriptor = "(B)V")
	public static void removeSoft() {
		Component.interfaceItemImageCache.removeSoft();
		Static124.aClass99_17.removeSoft();
		Component.interfaceTypefaceCache.removeSoft();
	}
}
