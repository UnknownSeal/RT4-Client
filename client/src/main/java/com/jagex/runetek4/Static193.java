package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.config.FluType;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static193 {

	@OriginalMember(owner = "runetek4.client!pi", name = "U", descriptor = "I")
	public static int anInt4539;

	@OriginalMember(owner = "runetek4.client!pi", name = "a", descriptor = "([[[B[[B[[B[[I[[F[[I[[B[[B[[FI[[F[[I[[I[[II)[Lclient!hg;")
	public static GlTile[] method3501(@OriginalArg(0) byte[][][] arg0, @OriginalArg(1) byte[][] arg1, @OriginalArg(2) byte[][] arg2, @OriginalArg(3) int[][] arg3, @OriginalArg(4) float[][] arg4, @OriginalArg(5) int[][] arg5, @OriginalArg(6) byte[][] arg6, @OriginalArg(7) byte[][] arg7, @OriginalArg(8) float[][] arg8, @OriginalArg(9) int arg9, @OriginalArg(10) float[][] arg10, @OriginalArg(11) int[][] arg11, @OriginalArg(12) int[][] arg12, @OriginalArg(13) int[][] arg13) {
		@Pc(9) int[][] local9 = new int[105][105];
		@Pc(16) int local16;
		for (@Pc(11) int local11 = 1; local11 <= 103; local11++) {
			for (local16 = 1; local16 <= 103; local16++) {
				@Pc(25) byte local25 = arg2[local11][local16];
				if (local25 == 0) {
					local25 = arg2[local11 - 1][local16];
				}
				if (local25 == 0) {
					local25 = arg2[local11][local16 - 1];
				}
				if (local25 == 0) {
					local25 = arg2[local11 - 1][local16 - 1];
				}
				if (local25 != 0) {
					@Pc(77) FluType local77 = FloorUnderlayTypeList.get((local25 & 0xFF) - 1);
					local9[local11][local16] = (local77.material + 1 << 16) + local77.hardShadow;
				}
			}
		}
		@Pc(103) HashTable local103 = new HashTable(128);
		@Pc(155) int local155;
		@Pc(161) int local161;
		@Pc(169) int local169;
		@Pc(112) int local112;
		for (local16 = 1; local16 <= 102; local16++) {
			for (local112 = 1; local112 <= 102; local112++) {
				if (arg2[local16][local112] != 0) {
					@Pc(135) int[] local135;
					if (arg6[local16][local112] == 0) {
						local135 = Static228.anIntArrayArray35[0];
					} else {
						local135 = Static53.anIntArrayArray8[arg1[local16][local112]];
						if (local135.length == 0) {
							continue;
						}
					}
					local155 = 0;
					local161 = local9[local16][local112];
					local169 = local9[local16 + 1][local112];
					if (arg5 != null) {
						local155 = arg5[local16][local112] & 0xFFFFFF;
					}
					@Pc(188) long local188 = (long) local155 | (long) local169 << 32;
					@Pc(196) int local196 = local9[local16][local112 + 1];
					@Pc(206) int local206 = local9[local16 + 1][local112 + 1];
					@Pc(214) long local214 = (long) local196 << 32 | (long) local155;
					@Pc(219) int local219 = local135.length / 2;
					@Pc(227) long local227 = (long) local155 | (long) local161 << 32;
					@Pc(233) GlTile local233 = (GlTile) local103.getNode(local227);
					if (local233 == null) {
						local233 = new GlTile((local161 >> 16) - 1, (float) (local161 & 0xFFFF), false, arg13 != null, local155);
						local103.put(local233, local227);
					}
					local233.anInt2484++;
					local233.anInt2482 += local219;
					if (local188 != local227) {
						local233 = (GlTile) local103.getNode(local188);
						if (local233 == null) {
							local233 = new GlTile((local169 >> 16) - 1, (float) (local169 & 0xFFFF), false, arg13 != null, local155);
							local103.put(local233, local188);
						}
						local233.anInt2484++;
						local233.anInt2482 += local219;
					}
					@Pc(340) long local340 = (long) local206 << 32 | (long) local155;
					if (local340 != local227 && local340 != local188) {
						local233 = (GlTile) local103.getNode(local340);
						if (local233 == null) {
							local233 = new GlTile((local206 >> 16) - 1, (float) (local206 & 0xFFFF), false, arg13 != null, local155);
							local103.put(local233, local340);
						}
						local233.anInt2482 += local219;
						local233.anInt2484++;
					}
					if (local214 != local227 && local188 != local214 && local214 != local340) {
						local233 = (GlTile) local103.getNode(local214);
						if (local233 == null) {
							local233 = new GlTile((local196 >> 16) - 1, (float) (local196 & 0xFFFF), false, arg13 != null, local155);
							local103.put(local233, local214);
						}
						local233.anInt2484++;
						local233.anInt2482 += local219;
					}
				}
			}
		}
		@Pc(493) GlTile local493;
		for (local493 = (GlTile) local103.head(); local493 != null; local493 = (GlTile) local103.next()) {
			local493.method1940();
		}
		for (local16 = 1; local16 <= 102; local16++) {
			for (local112 = 1; local112 <= 102; local112++) {
				@Pc(524) byte local524 = arg2[local16][local112];
				if (local524 != 0) {
					if ((arg0[arg9][local16][local112] & 0x8) != 0) {
						local155 = 0;
					} else if ((arg0[1][local16][local112] & 0x2) == 2 && arg9 > 0) {
						local155 = arg9 - 1;
					} else {
						local155 = arg9;
					}
					local161 = 0;
					@Pc(574) boolean[] local574 = null;
					local169 = 128;
					if (arg5 != null) {
						local169 = arg5[local16][local112] >>> 24 << 3;
						local161 = arg5[local16][local112] & 0xFFFFFF;
					}
					@Pc(655) int local655;
					@Pc(712) int local712;
					@Pc(614) int[] local614;
					@Pc(628) byte local628;
					@Pc(678) int local678;
					@Pc(754) int local754;
					if (arg6[local16][local112] == 0) {
						local655 = local524 == arg2[local16 - 1][local112 - 1] ? 1 : -1;
						local614 = Static228.anIntArrayArray35[0];
						local678 = local524 == arg2[local16 + 1][local112 - 1] ? 1 : -1;
						if (arg2[local16][local112 - 1] == local524) {
							local678++;
							local655++;
						} else {
							local655--;
							local678--;
						}
						local712 = local524 == arg2[local16 + 1][local112 + 1] ? 1 : -1;
						if (local524 == arg2[local16 + 1][local112]) {
							local712++;
							local678++;
						} else {
							local678--;
							local712--;
						}
						local754 = local524 == arg2[local16 - 1][local112 + 1] ? 1 : -1;
						if (arg2[local16][local112 + 1] == local524) {
							local754++;
							local712++;
						} else {
							local712--;
							local754--;
						}
						if (arg2[local16 - 1][local112] == local524) {
							local754++;
							local655++;
						} else {
							local754--;
							local655--;
						}
						@Pc(789) int local789 = local655 - local712;
						@Pc(794) int local794 = local678 - local754;
						if (local794 < 0) {
							local794 = -local794;
						}
						if (local789 < 0) {
							local789 = -local789;
						}
						local628 = (byte) (local794 <= local789 ? 0 : 1);
						arg7[local16][local112] = local628;
					} else {
						local614 = Static53.anIntArrayArray8[arg1[local16][local112]];
						local574 = Static69.aBooleanArrayArray2[arg1[local16][local112]];
						local628 = arg7[local16][local112];
						if (local614.length == 0) {
							continue;
						}
					}
					local655 = local9[local16][local112];
					local678 = local9[local16 + 1][local112];
					local712 = local9[local16 + 1][local112 + 1];
					@Pc(861) long local861 = (long) local655 << 32 | (long) local161;
					@Pc(869) long local869 = (long) local678 << 32 | (long) local161;
					@Pc(877) long local877 = (long) local712 << 32 | (long) local161;
					@Pc(883) int local883 = arg11[local16][local112];
					local754 = local9[local16][local112 + 1];
					@Pc(901) int local901 = arg11[local16 + 1][local112 + 1];
					@Pc(909) int local909 = arg11[local16 + 1][local112];
					@Pc(917) long local917 = (long) local161 | (long) local754 << 32;
					@Pc(925) int local925 = arg11[local16][local112 + 1];
					@Pc(931) int local931 = arg3[local16][local112];
					@Pc(939) int local939 = arg3[local16 + 1][local112];
					@Pc(949) int local949 = arg3[local16 + 1][local112 + 1];
					@Pc(957) int local957 = arg3[local16][local112 + 1];
					@Pc(963) int local963 = (local678 >> 16) - 1;
					@Pc(969) int local969 = (local655 >> 16) - 1;
					@Pc(975) int local975 = (local712 >> 16) - 1;
					@Pc(981) GlTile local981 = (GlTile) local103.getNode(local861);
					Static53.method1291(arg13, local655 <= local655, Static19.method588(local969, local883, local931), local981, local614, local112, local155, local16, local655 <= local712, arg8, local754 >= local655, arg4, local169, Static19.method588(local969, local925, local957), Static19.method588(local969, local901, local949), local655 <= local678, arg12, arg10, local628, Static19.method588(local969, local909, local939), local574);
					@Pc(1050) int local1050 = (local754 >> 16) - 1;
					if (local869 != local861) {
						local981 = (GlTile) local103.getNode(local869);
						Static53.method1291(arg13, local678 <= local655, Static19.method588(local963, local883, local931), local981, local614, local112, local155, local16, local712 >= local678, arg8, local678 <= local754, arg4, local169, Static19.method588(local963, local925, local957), Static19.method588(local963, local901, local949), local678 <= local678, arg12, arg10, local628, Static19.method588(local963, local909, local939), local574);
					}
					if (local877 != local861 && local877 != local869) {
						local981 = (GlTile) local103.getNode(local877);
						Static53.method1291(arg13, local655 >= local712, Static19.method588(local975, local883, local931), local981, local614, local112, local155, local16, local712 <= local712, arg8, local712 <= local754, arg4, local169, Static19.method588(local975, local925, local957), Static19.method588(local975, local901, local949), local678 >= local712, arg12, arg10, local628, Static19.method588(local975, local909, local939), local574);
					}
					if (local917 != local861 && local917 != local869 && local917 != local877) {
						local981 = (GlTile) local103.getNode(local917);
						Static53.method1291(arg13, local754 <= local655, Static19.method588(local1050, local883, local931), local981, local614, local112, local155, local16, local754 <= local712, arg8, local754 >= local754, arg4, local169, Static19.method588(local1050, local925, local957), Static19.method588(local1050, local901, local949), local678 >= local754, arg12, arg10, local628, Static19.method588(local1050, local909, local939), local574);
					}
				}
			}
		}
		for (local493 = (GlTile) local103.head(); local493 != null; local493 = (GlTile) local103.next()) {
			if (local493.anInt2483 == 0) {
				local493.unlink();
			} else {
				local493.method1943();
			}
		}
		local16 = local103.length();
		@Pc(1348) GlTile[] local1348 = new GlTile[local16];
		local103.addNodes(local1348);
		@Pc(1358) long[] local1358 = new long[local16];
		for (local155 = 0; local155 < local16; local155++) {
			local1358[local155] = local1348[local155].nodeId;
		}
		ArrayUtils.sort(local1358, local1348);
		return local1348;
	}

}
