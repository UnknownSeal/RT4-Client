package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static44 {

	@OriginalMember(owner = "client!dh", name = "d", descriptor = "[I")
	public static final int[] entityUpdateIds = new int[2048];

	@OriginalMember(owner = "client!dh", name = "i", descriptor = "Lclient!na;")
	public static final JString aClass100_336 = JString.parse("<img=1>");

	@OriginalMember(owner = "client!dh", name = "b", descriptor = "(I)Lclient!q;")
	public static ReferenceNodeFactory method1147() {
		try {
			return (ReferenceNodeFactory) Class.forName("com.jagex.runetek4.SoftReferenceNodeFactory").getDeclaredConstructor().newInstance();
		} catch (@Pc(15) Throwable local15) {
			return null;
		}
	}

	@OriginalMember(owner = "client!dh", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method1149(@OriginalArg(0) JString arg0) {
		@Pc(7) int local7 = Static91.method1879(arg0);
		if (local7 != -1) {
			Static80.method3616(Static203.aMapElementList_1.aShortArray73[local7], Static203.aMapElementList_1.aShortArray72[local7]);
		}
	}

}
