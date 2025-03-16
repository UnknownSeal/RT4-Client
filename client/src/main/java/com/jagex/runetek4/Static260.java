package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static260 {

	@OriginalMember(owner = "runetek4.client!vd", name = "w", descriptor = "Lclient!vk;")
	public static FrameBuffer frameBuffer;

	@OriginalMember(owner = "runetek4.client!vd", name = "v", descriptor = "Lclient!na;")
	public static final JString HITBAR_DEFAULT = Static28.parse("hitbar_default");

	@OriginalMember(owner = "runetek4.client!vd", name = "B", descriptor = "[S")
	public static final short[] aShortArray71 = new short[] { 6798, 8741, 25238, 4626, 4550 };

	@OriginalMember(owner = "runetek4.client!vd", name = "C", descriptor = "I")
	public static int anInt5014 = 0;

	@OriginalMember(owner = "runetek4.client!vd", name = "F", descriptor = "Lclient!na;")
	public static final JString aClass100_945 = Static28.parse("0");

	@OriginalMember(owner = "runetek4.client!vd", name = "b", descriptor = "(IIII)Z")
	public static boolean wallVisible(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (!CacheArchive.method187(arg0, arg1, arg2)) {
			return false;
		}
		@Pc(10) int local10 = arg1 << 7;
		@Pc(14) int local14 = arg2 << 7;
		@Pc(24) int local24 = SceneGraph.tileHeights[arg0][arg1][arg2] - 1;
		@Pc(28) int local28 = local24 - 120;
		@Pc(32) int local32 = local24 - 230;
		@Pc(36) int local36 = local24 - 238;
		if (arg3 < 16) {
			if (arg3 == 1) {
				if (local10 > Static149.eyeX) {
					if (!Static256.method4394(local10, local24, local14)) {
						return false;
					}
					if (!Static256.method4394(local10, local24, local14 + 128)) {
						return false;
					}
				}
				if (arg0 > 0) {
					if (!Static256.method4394(local10, local28, local14)) {
						return false;
					}
					if (!Static256.method4394(local10, local28, local14 + 128)) {
						return false;
					}
				}
				if (!Static256.method4394(local10, local32, local14)) {
					return false;
				}
				if (!Static256.method4394(local10, local32, local14 + 128)) {
					return false;
				}
				return true;
			}
			if (arg3 == 2) {
				if (local14 < Static217.eyeZ) {
					if (!Static256.method4394(local10, local24, local14 + 128)) {
						return false;
					}
					if (!Static256.method4394(local10 + 128, local24, local14 + 128)) {
						return false;
					}
				}
				if (arg0 > 0) {
					if (!Static256.method4394(local10, local28, local14 + 128)) {
						return false;
					}
					if (!Static256.method4394(local10 + 128, local28, local14 + 128)) {
						return false;
					}
				}
				if (!Static256.method4394(local10, local32, local14 + 128)) {
					return false;
				}
				if (!Static256.method4394(local10 + 128, local32, local14 + 128)) {
					return false;
				}
				return true;
			}
			if (arg3 == 4) {
				if (local10 < Static149.eyeX) {
					if (!Static256.method4394(local10 + 128, local24, local14)) {
						return false;
					}
					if (!Static256.method4394(local10 + 128, local24, local14 + 128)) {
						return false;
					}
				}
				if (arg0 > 0) {
					if (!Static256.method4394(local10 + 128, local28, local14)) {
						return false;
					}
					if (!Static256.method4394(local10 + 128, local28, local14 + 128)) {
						return false;
					}
				}
				if (!Static256.method4394(local10 + 128, local32, local14)) {
					return false;
				}
				if (!Static256.method4394(local10 + 128, local32, local14 + 128)) {
					return false;
				}
				return true;
			}
			if (arg3 == 8) {
				if (local14 > Static217.eyeZ) {
					if (!Static256.method4394(local10, local24, local14)) {
						return false;
					}
					if (!Static256.method4394(local10 + 128, local24, local14)) {
						return false;
					}
				}
				if (arg0 > 0) {
					if (!Static256.method4394(local10, local28, local14)) {
						return false;
					}
					if (!Static256.method4394(local10 + 128, local28, local14)) {
						return false;
					}
				}
				if (!Static256.method4394(local10, local32, local14)) {
					return false;
				}
				if (!Static256.method4394(local10 + 128, local32, local14)) {
					return false;
				}
				return true;
			}
		}
		if (!Static256.method4394(local10 + 64, local36, local14 + 64)) {
			return false;
		} else if (arg3 == 16) {
			return Static256.method4394(local10, local32, local14 + 128);
		} else if (arg3 == 32) {
			return Static256.method4394(local10 + 128, local32, local14 + 128);
		} else if (arg3 == 64) {
			return Static256.method4394(local10 + 128, local32, local14);
		} else if (arg3 == 128) {
			return Static256.method4394(local10, local32, local14);
		} else {
			return true;
		}
	}

}
