package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static93 {

	@OriginalMember(owner = "runetek4.client!he", name = "cb", descriptor = "Lclient!ve;")
	public static CacheArchive modelArchive;

	@OriginalMember(owner = "runetek4.client!he", name = "V", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_13 = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!he", name = "Y", descriptor = "I")
	public static volatile int anInt2467 = 0;

    @OriginalMember(owner = "runetek4.client!he", name = "bb", descriptor = "Lclient!ug;")
	public static Mouse aClass150_1 = new Mouse();

	@OriginalMember(owner = "runetek4.client!he", name = "db", descriptor = "Lclient!na;")
	public static final JString aClass100_517 = Static28.parse("");

	@OriginalMember(owner = "runetek4.client!he", name = "eb", descriptor = "[I")
	public static final int[] anIntArray219 = new int[1000];

	@OriginalMember(owner = "runetek4.client!he", name = "fb", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_14 = new NodeCache(30);

	@OriginalMember(owner = "runetek4.client!he", name = "gb", descriptor = "Lclient!na;")
	public static final JString aClass100_518 = Static28.parse("www");

	@OriginalMember(owner = "runetek4.client!he", name = "c", descriptor = "(II)V")
	public static void method1906(@OriginalArg(1) int arg0) {
		@Pc(12) DelayedStateChange local12 = Static238.method4143(7, arg0);
		local12.method1007();
	}
}
