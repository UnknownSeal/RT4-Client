package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static38 {

	@OriginalMember(owner = "client!cn", name = "L", descriptor = "I")
	public static int anInt1203 = 0;

	@OriginalMember(owner = "client!cn", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void method962(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		if (arg8 == arg7 && arg2 == arg6 && arg4 == arg3 && arg0 == arg5) {
			Static241.method4547(arg1, arg5, arg6, arg4, arg8);
			return;
		}
		@Pc(37) int local37 = arg6;
		@Pc(39) int local39 = arg8;
		@Pc(43) int local43 = arg8 * 3;
		@Pc(47) int local47 = arg6 * 3;
		@Pc(51) int local51 = arg7 * 3;
		@Pc(55) int local55 = arg2 * 3;
		@Pc(59) int local59 = arg3 * 3;
		@Pc(63) int local63 = arg0 * 3;
		@Pc(73) int local73 = arg4 + local51 - local59 - arg8;
		@Pc(83) int local83 = arg5 + local55 - arg6 - local63;
		@Pc(93) int local93 = local43 + local59 - local51 - local51;
		@Pc(103) int local103 = local47 + local63 - local55 - local55;
		@Pc(108) int local108 = local51 - local43;
		@Pc(113) int local113 = local55 - local47;
		for (@Pc(115) int local115 = 128; local115 <= 4096; local115 += 128) {
			@Pc(126) int local126 = local115 * local115 >> 12;
			@Pc(132) int local132 = local115 * local126 >> 12;
			@Pc(136) int local136 = local83 * local132;
			@Pc(140) int local140 = local126 * local93;
			@Pc(144) int local144 = local73 * local132;
			@Pc(148) int local148 = local126 * local103;
			@Pc(152) int local152 = local108 * local115;
			@Pc(156) int local156 = local113 * local115;
			@Pc(167) int local167 = (local152 + local144 + local140 >> 12) + arg8;
			@Pc(177) int local177 = arg6 + (local156 + local136 + local148 >> 12);
			Static241.method4547(arg1, local177, local37, local167, local39);
			local39 = local167;
			local37 = local177;
		}
	}

	@OriginalMember(owner = "client!cn", name = "a", descriptor = "(ZI)I")
	public static int poll(@OriginalArg(0) boolean arg0) {
		@Pc(4) long local4 = MonotonicTime.currentTimeMillis();
		for (@Pc(28) LongNode local28 = arg0 ? (LongNode) VarpDomain.aClass133_20.head() : (LongNode) VarpDomain.aClass133_20.next(); local28 != null; local28 = (LongNode) VarpDomain.aClass133_20.next()) {
			if ((local28.value & 0x3FFFFFFFFFFFFFFFL) < local4) {
				if ((local28.value & 0x4000000000000000L) != 0L) {
					@Pc(58) int local58 = (int) local28.nodeId;
					VarpDomain.activeVarps[local58] = VarpDomain.varp[local58];
					local28.unlink();
					return local58;
				}
				local28.unlink();
			}
		}
		return -1;
	}

}