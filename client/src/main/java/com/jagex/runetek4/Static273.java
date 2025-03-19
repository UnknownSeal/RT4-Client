package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static273 {

	@OriginalMember(owner = "runetek4.client!we", name = "v", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_7;

	@OriginalMember(owner = "runetek4.client!we", name = "H", descriptor = "[[B")
	public static byte[][] aByteArrayArray13;

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BI)I")
	public static int fade(@OriginalArg(1) int t) {
		@Pc(13) int depth = t * (t * t >> 12) >> 12;
		@Pc(26) int x = t * 6 - 61440;
		@Pc(34) int y = (t * x >> 12) + 40960;
		return depth * y >> 12;
	}

}
