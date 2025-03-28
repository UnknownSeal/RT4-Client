package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static91 {

	@OriginalMember(owner = "runetek4.client!hc", name = "d", descriptor = "(I)I")
	public static int getZoom() {
		if ((double) Static138.aFloat14 == 3.0D) {
			return 37;
		} else if ((double) Static138.aFloat14 == 4.0D) {
			return 50;
		} else if ((double) Static138.aFloat14 == 6.0D) {
			return 75;
		} else if ((double) Static138.aFloat14 == 8.0D) {
			return 100;
		} else {
			return 200;
		}
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
	public static int method1879(@OriginalArg(0) JString arg0) {
		if (Static203.aMapElementList_1 == null || arg0.length() == 0) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static203.aMapElementList_1.anInt5074; local20++) {
			if (Static203.aMapElementList_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).strEquals(arg0)) {
				return local20;
			}
		}
		return -1;
	}

}
