package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static273 {

	@OriginalMember(owner = "runetek4.client!we", name = "v", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_7;

	@OriginalMember(owner = "runetek4.client!we", name = "H", descriptor = "[[B")
	public static byte[][] aByteArrayArray13;

	@OriginalMember(owner = "runetek4.client!we", name = "w", descriptor = "I")
	public static int minimapZoom = 0;

	@OriginalMember(owner = "runetek4.client!we", name = "a", descriptor = "(BI)I")
	public static int fade(@OriginalArg(1) int t) {
		@Pc(13) int depth = t * (t * t >> 12) >> 12;
		@Pc(26) int x = t * 6 - 61440;
		@Pc(34) int y = (t * x >> 12) + 40960;
		return depth * y >> 12;
	}

	@OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(II)Z")
	public static boolean method3213(@OriginalArg(1) int arg0) {
		if (arg0 >= 32 && arg0 <= 126) {
			return true;
		} else if (arg0 >= 160 && arg0 <= 255) {
			return true;
		} else {
			return arg0 == 128 || arg0 == 140 || arg0 == 151 || arg0 == 156 || arg0 == 159;
		}
	}

	@OriginalMember(owner = "runetek4.client!we", name = "b", descriptor = "(BI)V")
	public static void method3214(@OriginalArg(1) int arg0) {
		for (@Pc(11) Node local11 = Static210.aClass133_21.peekFront(); local11 != null; local11 = Static210.aClass133_21.prev()) {
			if ((local11.nodeId >> 48 & 0xFFFFL) == (long) arg0) {
				local11.unlink();
			}
		}
	}
}
