package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static73 {

	@OriginalMember(owner = "client!fm", name = "V", descriptor = "I")
	public static int b12FullId;

	@OriginalMember(owner = "client!fm", name = "W", descriptor = "Lclient!na;")
	public static final JString aClass100_453 = JString.parse(")2");

	@OriginalMember(owner = "client!fm", name = "gb", descriptor = "Lclient!na;")
	public static final JString OVERLAY2 = JString.parse("overlay2");

	@OriginalMember(owner = "client!fm", name = "a", descriptor = "(IIIIII)Z")
	public static boolean method1599(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(16) int local16;
		@Pc(20) int local20;
		if (arg1 != arg2 || arg3 != arg4) {
			for (local16 = arg1; local16 <= arg2; local16++) {
				for (local20 = arg3; local20 <= arg4; local20++) {
					if (Static140.anIntArrayArrayArray12[arg0][local16][local20] == -Static13.anInt437) {
						return false;
					}
				}
			}
			local16 = (arg1 << 7) + 1;
			local20 = (arg3 << 7) + 2;
			@Pc(156) int local156 = SceneGraph.tileHeights[arg0][arg1][arg3] + arg5;
			if (!Static256.method4394(local16, local156, local20)) {
				return false;
			}
			@Pc(169) int local169 = (arg2 << 7) - 1;
			if (!Static256.method4394(local169, local156, local20)) {
				return false;
			}
			@Pc(182) int local182 = (arg4 << 7) - 1;
			if (!Static256.method4394(local16, local156, local182)) {
				return false;
			} else if (Static256.method4394(local169, local156, local182)) {
				return true;
			} else {
				return false;
			}
		} else if (CacheArchive.method187(arg0, arg1, arg3)) {
			local16 = arg1 << 7;
			local20 = arg3 << 7;
			return Static256.method4394(local16 + 1, SceneGraph.tileHeights[arg0][arg1][arg3] + arg5, local20 + 1) && Static256.method4394(local16 + 128 - 1, SceneGraph.tileHeights[arg0][arg1 + 1][arg3] + arg5, local20 + 1) && Static256.method4394(local16 + 128 - 1, SceneGraph.tileHeights[arg0][arg1 + 1][arg3 + 1] + arg5, local20 + 128 - 1) && Static256.method4394(local16 + 1, SceneGraph.tileHeights[arg0][arg1][arg3 + 1] + arg5, local20 + 128 - 1);
		} else {
			return false;
		}
	}
}
