package com.jagex.runetek4.util.system;

import com.jagex.runetek4.config.types.flu.FluTypeList;
import com.jagex.runetek4.config.types.obj.ObjTypeList;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.graphics.textureops.TextureOp29SubOp4;
import com.jagex.runetek4.graphics.textureops.TextureOpRasterizer;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.IntUtils;
import com.jagex.runetek4.game.world.WorldMap;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class PreciseSleep {


	@OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(ILclient!wa;)V")
	public static void method3980(@OriginalArg(1) Packet arg0) {
		label83: while (true) {
			if (arg0.offset < arg0.data.length) {
				@Pc(23) int local23 = 0;
				@Pc(25) boolean local25 = false;
				@Pc(27) int local27 = 0;
				if (arg0.g1() == 1) {
					local25 = true;
					local23 = arg0.g1();
					local27 = arg0.g1();
				}
				@Pc(46) int local46 = arg0.g1();
				@Pc(50) int local50 = arg0.g1();
				@Pc(57) int local57 = local46 * 64 - WorldMap.originX;
				@Pc(68) int local68 = WorldMap.length + WorldMap.originZ - local50 * 64 - 1;
				@Pc(146) byte local146;
				@Pc(96) int local96;
				if (local57 >= 0 && local68 - 63 >= 0 && WorldMap.width > local57 + 63 && local68 < WorldMap.length) {
					local96 = local57 >> 6;
					@Pc(100) int local100 = local68 >> 6;
					@Pc(102) int local102 = 0;
					while (true) {
						if (local102 >= 64) {
							continue label83;
						}
						for (@Pc(107) int local107 = 0; local107 < 64; local107++) {
							if (!local25 || local23 * 8 <= local102 && local23 * 8 + 8 > local102 && local107 >= local27 * 8 && local27 * 8 + 8 > local107) {
								local146 = arg0.g1s();
								if (local146 != 0) {
									if (WorldMap.aByteArrayArrayArray12[local96][local100] == null) {
										WorldMap.aByteArrayArrayArray12[local96][local100] = new byte[4096];
									}
									WorldMap.aByteArrayArrayArray12[local96][local100][(63 - local107 << 6) + local102] = local146;
									@Pc(182) byte local182 = arg0.g1s();
									if (WorldMap.aByteArrayArrayArray10[local96][local100] == null) {
										WorldMap.aByteArrayArrayArray10[local96][local100] = new byte[4096];
									}
									WorldMap.aByteArrayArrayArray10[local96][local100][local102 + (63 - local107 << 6)] = local182;
								}
							}
						}
						local102++;
					}
				}
				local96 = 0;
				while (true) {
					if (local96 >= (local25 ? 64 : 4096)) {
						continue label83;
					}
					local146 = arg0.g1s();
					if (local146 != 0) {
						arg0.offset++;
					}
					local96++;
				}
			}
			return;
		}
	}

	@OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(IIIIBIII)V")
	public static void method3981(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		@Pc(7) int local7 = 0;
		@Pc(12) int local12 = arg0 - arg3;
		@Pc(14) int local14 = 0;
		@Pc(16) int local16 = arg6;
		@Pc(20) int local20 = arg6 * arg6;
		@Pc(25) int local25 = arg6 - arg3;
		@Pc(29) int local29 = arg0 * arg0;
		@Pc(33) int local33 = local20 << 1;
		@Pc(37) int local37 = local25 * local25;
		@Pc(41) int local41 = local12 * local12;
		@Pc(45) int local45 = local29 << 1;
		@Pc(49) int local49 = local37 << 1;
		@Pc(53) int local53 = local41 << 1;
		@Pc(57) int local57 = local25 << 1;
		@Pc(61) int local61 = arg6 << 1;
		@Pc(70) int local70 = local33 + local29 * (1 - local61);
		@Pc(79) int local79 = local20 - (local61 - 1) * local45;
		@Pc(88) int local88 = local49 + local41 * (1 - local57);
		@Pc(96) int local96 = local37 - local53 * (local57 - 1);
		@Pc(100) int local100 = local20 << 2;
		@Pc(104) int local104 = local29 << 2;
		@Pc(108) int local108 = local37 << 2;
		@Pc(112) int local112 = local33 * 3;
		@Pc(118) int local118 = (local61 - 3) * local45;
		@Pc(130) int local130 = local41 << 2;
		@Pc(134) int local134 = local49 * 3;
		@Pc(140) int local140 = (local57 - 3) * local53;
		@Pc(146) int local146 = (arg6 - 1) * local104;
		@Pc(148) int local148 = local108;
		@Pc(150) int local150 = local100;
		@Pc(156) int local156 = (local25 - 1) * local130;
		@Pc(174) int local174;
		@Pc(183) int local183;
		@Pc(192) int local192;
		@Pc(201) int local201;
		if (arg4 >= TextureOpRasterizer.anInt5773 && TextureOpRasterizer.anInt2869 >= arg4) {
			@Pc(166) int[] local166 = ObjTypeList.anIntArrayArray10[arg4];
			local174 = IntUtils.clamp(FluTypeList.anInt5063, arg5 - arg0, TextureOpRasterizer.anInt4164);
			local183 = IntUtils.clamp(FluTypeList.anInt5063, arg5 + arg0, TextureOpRasterizer.anInt4164);
			local192 = IntUtils.clamp(FluTypeList.anInt5063, arg5 - local12, TextureOpRasterizer.anInt4164);
			local201 = IntUtils.clamp(FluTypeList.anInt5063, arg5 + local12, TextureOpRasterizer.anInt4164);
			ArrayUtils.fillRange(local166, local174, local192, arg2);
			ArrayUtils.fillRange(local166, local192, local201, arg1);
			ArrayUtils.fillRange(local166, local201, local183, arg2);
		}
		while (local16 > 0) {
			if (local70 < 0) {
				while (local70 < 0) {
					local70 += local112;
					local112 += local100;
					local7++;
					local79 += local150;
					local150 += local100;
				}
			}
			@Pc(255) boolean local255 = local16 <= local25;
			if (local79 < 0) {
				local79 += local150;
				local70 += local112;
				local7++;
				local150 += local100;
				local112 += local100;
			}
			if (local255) {
				if (local88 < 0) {
					while (local88 < 0) {
						local14++;
						local96 += local148;
						local148 += local108;
						local88 += local134;
						local134 += local108;
					}
				}
				if (local96 < 0) {
					local14++;
					local96 += local148;
					local88 += local134;
					local148 += local108;
					local134 += local108;
				}
				local88 += -local156;
				local156 -= local130;
				local96 += -local140;
				local140 -= local130;
			}
			local79 += -local118;
			local70 += -local146;
			local146 -= local104;
			local118 -= local104;
			local16--;
			local183 = arg4 + local16;
			local174 = arg4 - local16;
			if (TextureOpRasterizer.anInt5773 <= local183 && TextureOpRasterizer.anInt2869 >= local174) {
				local192 = IntUtils.clamp(FluTypeList.anInt5063, arg5 + local7, TextureOpRasterizer.anInt4164);
				local201 = IntUtils.clamp(FluTypeList.anInt5063, arg5 - local7, TextureOpRasterizer.anInt4164);
				if (local255) {
					@Pc(404) int local404 = IntUtils.clamp(FluTypeList.anInt5063, arg5 + local14, TextureOpRasterizer.anInt4164);
					@Pc(412) int local412 = IntUtils.clamp(FluTypeList.anInt5063, arg5 - local14, TextureOpRasterizer.anInt4164);
					@Pc(420) int[] local420;
					if (TextureOpRasterizer.anInt5773 <= local174) {
						local420 = ObjTypeList.anIntArrayArray10[local174];
						ArrayUtils.fillRange(local420, local201, local412, arg2);
						ArrayUtils.fillRange(local420, local412, local404, arg1);
						ArrayUtils.fillRange(local420, local404, local192, arg2);
					}
					if (local183 <= TextureOpRasterizer.anInt2869) {
						local420 = ObjTypeList.anIntArrayArray10[local183];
						ArrayUtils.fillRange(local420, local201, local412, arg2);
						ArrayUtils.fillRange(local420, local412, local404, arg1);
						ArrayUtils.fillRange(local420, local404, local192, arg2);
					}
				} else {
					if (TextureOpRasterizer.anInt5773 <= local174) {
						ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local174], local201, local192, arg2);
					}
					if (TextureOpRasterizer.anInt2869 >= local183) {
						ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local183], local201, local192, arg2);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!sk", name = "a", descriptor = "(IIIIII)V")
	public static void method3982(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(15) int local15 = arg4 - arg2;
		@Pc(19) int local19 = arg1 - arg3;
		if (local15 == 0) {
			if (local19 != 0) {
				TextureOpRasterizer.method1306(arg1, arg3, arg0, arg2);
			}
		} else if (local19 == 0) {
			TextureOpRasterizer.method4019(arg2, arg0, arg4, arg3);
		} else {
			@Pc(50) int local50 = (local19 << 12) / local15;
			@Pc(59) int local59 = arg3 - (local50 * arg2 >> 12);
			@Pc(68) int local68;
			@Pc(76) int local76;
			if (TextureOpRasterizer.anInt4164 > arg2) {
				local68 = TextureOpRasterizer.anInt4164;
				local76 = (TextureOpRasterizer.anInt4164 * local50 >> 12) + local59;
			} else if (arg2 > FluTypeList.anInt5063) {
				local76 = (FluTypeList.anInt5063 * local50 >> 12) + local59;
				local68 = FluTypeList.anInt5063;
			} else {
				local68 = arg2;
				local76 = arg3;
			}
			@Pc(109) int local109;
			@Pc(118) int local118;
			if (arg4 < TextureOpRasterizer.anInt4164) {
				local109 = TextureOpRasterizer.anInt4164;
				local118 = local59 + (local50 * TextureOpRasterizer.anInt4164 >> 12);
			} else if (FluTypeList.anInt5063 < arg4) {
				local109 = FluTypeList.anInt5063;
				local118 = local59 + (local50 * FluTypeList.anInt5063 >> 12);
			} else {
				local118 = arg1;
				local109 = arg4;
			}
			if (TextureOpRasterizer.anInt5773 > local118) {
				local109 = (TextureOpRasterizer.anInt5773 - local59 << 12) / local50;
				local118 = TextureOpRasterizer.anInt5773;
			} else if (local118 > TextureOpRasterizer.anInt2869) {
				local118 = TextureOpRasterizer.anInt2869;
				local109 = (TextureOpRasterizer.anInt2869 - local59 << 12) / local50;
			}
			if (local76 < TextureOpRasterizer.anInt5773) {
				local76 = TextureOpRasterizer.anInt5773;
				local68 = (TextureOpRasterizer.anInt5773 - local59 << 12) / local50;
			} else if (TextureOpRasterizer.anInt2869 < local76) {
				local76 = TextureOpRasterizer.anInt2869;
				local68 = (TextureOpRasterizer.anInt2869 - local59 << 12) / local50;
			}
			TextureOp29SubOp4.method4547(arg0, local118, local76, local109, local68);
		}
	}

}
