package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static65 {

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "I")
	public static int anInt1951 = -1;

	@OriginalMember(owner = "client!fc", name = "f", descriptor = "Lclient!na;")
	public static final JString aClass100_435 = JString.parse("<img=0>");

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!wa;I)Lclient!na;")
	public static JString method1497(@OriginalArg(0) Packet arg0) {
		return Static254.method4350(arg0);
	}

	// MapLoader should be owning class.
	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(III)I")
	public static int method1498(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(36) int local36 = Static115.method2309(4, arg1 + 45365, arg0 + 91923) + (Static115.method2309(2, arg1 + 10294, arg0 + 37821) - 128 >> 1) + (Static115.method2309(1, arg1, arg0) + -128 >> 2) - 128;
		local36 = (int) ((double) local36 * 0.3D) + 35;
		if (local36 < 10) {
			local36 = 10;
		} else if (local36 > 60) {
			local36 = 60;
		}
		return local36;
	}

}
