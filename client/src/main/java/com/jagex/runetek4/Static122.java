package com.jagex.runetek4;

import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static122 {

	@OriginalMember(owner = "runetek4.client!jh", name = "n", descriptor = "Lclient!bd;")
	public static QuickChatPhrase aQuickChatPhrase_1;

	@OriginalMember(owner = "runetek4.client!jh", name = "p", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_46;

	@OriginalMember(owner = "runetek4.client!jh", name = "b", descriptor = "[Lclient!na;")
	public static final JString[] friendName = new JString[200];

    @OriginalMember(owner = "runetek4.client!jh", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_591 = Static28.parse("(Udns");

	@OriginalMember(owner = "runetek4.client!jh", name = "j", descriptor = "I")
	public static int x = 0;

	@OriginalMember(owner = "runetek4.client!jh", name = "a", descriptor = "(Lclient!ve;ZIIZI)V")
	public static void method2410(@OriginalArg(0) CacheArchive arg0, @OriginalArg(2) int arg1, @OriginalArg(5) int arg2) {
		Static172.aClass153_70 = arg0;
		Static14.anInt441 = 1;
		Static253.anInt5527 = arg2;
		Static226.anInt5085 = 0;
		Static277.anInt5853 = arg1;
		Static72.aBoolean116 = false;
		Static57.anInt1757 = 10000;
	}

	@OriginalMember(owner = "runetek4.client!jh", name = "a", descriptor = "(IILclient!ve;Lclient!ve;I)Lclient!dd;")
	public static SoftwareFont method2412(@OriginalArg(0) int arg0, @OriginalArg(2) CacheArchive arg1, @OriginalArg(3) CacheArchive arg2) {
		return Static234.method4016(arg2, 0, arg0) ? Static114.method4635(arg1.getfile(arg0, 0)) : null;
	}
}
