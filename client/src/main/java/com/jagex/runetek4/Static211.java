package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static211 {

	@OriginalMember(owner = "runetek4.client!rc", name = "R", descriptor = "Z")
	private static boolean aBoolean74;

	@OriginalMember(owner = "runetek4.client!rc", name = "p", descriptor = "I")
	public static int anInt1142 = 0;

	@OriginalMember(owner = "runetek4.client!rc", name = "C", descriptor = "Z")
	public static boolean aBoolean72 = false;

	@OriginalMember(owner = "runetek4.client!rc", name = "G", descriptor = "Lclient!na;")
	public static final JString aClass100_230 = Static28.parse("");

	@OriginalMember(owner = "runetek4.client!rc", name = "I", descriptor = "Lclient!na;")
	public static final JString aClass100_231 = Static28.parse(")3)3)3");

	@OriginalMember(owner = "runetek4.client!rc", name = "M", descriptor = "Z")
	public static boolean aBoolean73 = false;

	@OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Lclient!na;Z)Lclient!na;")
	public static JString method923(@OriginalArg(0) JString arg0) {
		@Pc(12) int local12 = Static171.method3218(arg0);
		return local12 == -1 ? Static93.aClass100_517 : Static203.aMapElementTypeList_1.aClass100Array153[local12].method3140(Static101.aClass100_538, Static197.aClass100_872);
	}

	@OriginalMember(owner = "runetek4.client!rc", name = "a", descriptor = "(Z)V")
	public static void method924() {
		Static244.aClass99_32.method3104();
	}

	@OriginalMember(owner = "runetek4.client!rc", name = "d", descriptor = "(I)V")
	public static void method930() {
		if (Static11.aClass62_1 != null) {
			Static11.aClass62_1.quit();
		}
		if (Static147.aClass62_2 != null) {
			Static147.aClass62_2.quit();
		}
		Static41.init(Static99.aBoolean143);
		Static11.aClass62_1 = Static107.method2262(22050, GameShell.signLink, GameShell.canvas, 0);
		Static11.aClass62_1.method3566(Static148.aClass3_Sub3_Sub4_1);
		Static147.aClass62_2 = Static107.method2262(2048, GameShell.signLink, GameShell.canvas, 1);
		Static147.aClass62_2.method3566(client.soundStream);
	}
}
