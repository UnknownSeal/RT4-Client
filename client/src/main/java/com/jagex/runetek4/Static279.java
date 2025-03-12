package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static279 {

	@OriginalMember(owner = "runetek4.client!wk", name = "v", descriptor = "[I")
	public static int[] anIntArray568;

	@OriginalMember(owner = "runetek4.client!wk", name = "w", descriptor = "I")
	public static int anInt5880;

	@OriginalMember(owner = "runetek4.client!wk", name = "t", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_38 = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!wk", name = "u", descriptor = "Lclient!na;")
	public static final JString CLIENTJS5DROP = Static28.parse("::clientjs5drop");

	@OriginalMember(owner = "runetek4.client!wk", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_1107 = Static28.parse(")4l=");

	@OriginalMember(owner = "runetek4.client!wk", name = "b", descriptor = "(II)V")
	public static void method4662() {
		Static250.aClass99_33.method3102(5);
		Static139.aClass99_21.method3102(5);
	}

	@OriginalMember(owner = "runetek4.client!wk", name = "a", descriptor = "(I[Lclient!na;)[Lclient!na;")
	public static JString[] method4664(@OriginalArg(1) JString[] arg0) {
		@Pc(8) JString[] local8 = new JString[5];
		for (@Pc(15) int local15 = 0; local15 < 5; local15++) {
			local8[local15] = Static34.method882(new JString[] { Static123.method2423(local15), Static27.aClass100_168 });
			if (arg0 != null && arg0[local15] != null) {
				local8[local15] = Static34.method882(new JString[] { local8[local15], arg0[local15] });
			}
		}
		return local8;
	}
}
