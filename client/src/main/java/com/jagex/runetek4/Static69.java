package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static69 {

	@OriginalMember(owner = "client!fh", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method1546(@OriginalArg(0) Packet arg0) {
		for (@Pc(7) int local7 = 0; local7 < Static106.anInt2871; local7++) {
			@Pc(18) int local18 = arg0.gSmart1or2();
			@Pc(22) int local22 = arg0.g2();
			if (local22 == 65535) {
				local22 = -1;
			}
			if (Static196.aClass10_Sub1Array2[local18] != null) {
				Static196.aClass10_Sub1Array2[local18].players = local22;
			}
		}
	}
}
