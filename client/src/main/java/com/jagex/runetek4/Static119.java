package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static119 {

	@OriginalMember(owner = "client!je", name = "W", descriptor = "Lclient!ve;")
	public static Js5 aClass153_44;

	@OriginalMember(owner = "client!je", name = "eb", descriptor = "[I")
	public static int[] anIntArray282;

	@OriginalMember(owner = "client!je", name = "R", descriptor = "Z")
	public static boolean aBoolean153 = false;

	@OriginalMember(owner = "client!je", name = "U", descriptor = "Lclient!na;")
	public static final JString SHOWINGVIDEOAD = JString.parse("showingVideoAd");

	@OriginalMember(owner = "client!je", name = "j", descriptor = "(I)I")
	public static int method2385() {
		if (Static203.aMapElementTypeList_1 == null) {
			return -1;
		}
		while (Static232.anInt5212 < Static203.aMapElementTypeList_1.anInt5074) {
			if (Static203.aMapElementTypeList_1.method3897(Static232.anInt5212)) {
				return Static232.anInt5212++;
			}
			Static232.anInt5212++;
		}
		return -1;
	}

}
