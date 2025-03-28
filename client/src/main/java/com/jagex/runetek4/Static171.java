package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static171 {

	@OriginalMember(owner = "runetek4.client!ni", name = "q", descriptor = "I")
	public static int anInt4157;

	@OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(ILclient!na;)I")
	public static int method3218(@OriginalArg(1) JString arg0) {
		if (Static203.aMapElementList_1 == null || arg0.length() == 0) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static203.aMapElementList_1.anInt5074; local20++) {
			if (Static203.aMapElementList_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).method3142(arg0)) {
				return local20;
			}
		}
		return -1;
	}

}
