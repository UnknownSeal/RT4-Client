package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.config.FluType;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.game.config.msitype.MSIType;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static33 {

    @OriginalMember(owner = "client!cj", name = "a", descriptor = "(ILclient!pb;ZIIII)Z")
	public static boolean method867(@OriginalArg(0) int arg0, @OriginalArg(1) LocType arg1, @OriginalArg(5) int arg2, @OriginalArg(6) int arg3) {
		@Pc(10) MSIType local10 = Static40.get(arg1.mapsceneicon);
		if (local10.spriteId == -1) {
			return true;
		}
		if (arg1.mapsceneiconrotate) {
			@Pc(24) int local24 = arg3 + arg1.mapSceneAngleOffset;
			arg3 = local24 & 0x3;
		} else {
			arg3 = 0;
		}
		@Pc(42) SoftwareIndexedSprite local42 = local10.method9(arg3);
		if (local42 == null) {
			return false;
		}
		@Pc(49) int local49 = arg1.width;
		@Pc(52) int local52 = arg1.length;
		if ((arg3 & 0x1) == 1) {
			local49 = arg1.length;
			local52 = arg1.width;
		}
		@Pc(66) int local66 = local42.innerWidth;
		@Pc(69) int local69 = local42.innerHeight;
		if (local10.aBoolean2) {
			local69 = local52 * 4;
			local66 = local49 * 4;
		}
		if (local10.anInt11 == 0) {
			local42.method1398(arg0 * 4 + 48, (-local52 + -arg2 + 104) * 4 + 48, local66, local69);
		} else {
			local42.method1390(arg0 * 4 + 48, (-local52 + -arg2 + 104) * 4 + 48, local66, local69, local10.anInt11);
		}
		return true;
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(BLclient!wa;)V")
	public static void method868(@OriginalArg(1) Packet arg0) {
		@Pc(13) int local13 = Static266.anInt5338 >> 1;
		@Pc(19) int local19 = Static131.anInt3254 >> 2 << 10;
		@Pc(23) byte[][] local23 = new byte[Static48.anInt1449][WorldMap.length];
		@Pc(33) int local33;
		@Pc(102) int local102;
		@Pc(114) int local114;
		while (arg0.offset < arg0.data.length) {
			@Pc(31) int local31 = 0;
			local33 = 0;
			@Pc(35) boolean local35 = false;
			if (arg0.g1() == 1) {
				local33 = arg0.g1();
				local31 = arg0.g1();
				local35 = true;
			}
			@Pc(57) int local57 = arg0.g1();
			@Pc(61) int local61 = arg0.g1();
			@Pc(68) int local68 = local57 * 64 - WorldMap.originX;
			@Pc(78) int local78 = WorldMap.length + WorldMap.originZ - local61 * 64 - 1;
			if (local68 >= 0 && local78 - 63 >= 0 && Static48.anInt1449 > local68 + 63 && WorldMap.length > local78) {
				for (local102 = 0; local102 < 64; local102++) {
					@Pc(112) byte[] local112 = local23[local68 + local102];
					for (local114 = 0; local114 < 64; local114++) {
						if (!local35 || local102 >= local33 * 8 && local33 * 8 + 8 > local102 && local114 >= local31 * 8 && local114 < local31 * 8 + 8) {
							local112[local78 - local114] = arg0.g1s();
						}
					}
				}
			} else if (local35) {
				arg0.offset += 64;
			} else {
				arg0.offset += 4096;
			}
		}
		@Pc(175) int local175 = Static48.anInt1449;
		local33 = WorldMap.length;
		@Pc(180) int[] local180 = new int[local33];
		@Pc(183) int[] local183 = new int[local33];
		@Pc(186) int[] local186 = new int[local33];
		@Pc(189) int[] local189 = new int[local33];
		@Pc(192) int[] local192 = new int[local33];
		for (local102 = -5; local102 < local175; local102++) {
			@Pc(225) int local225;
			@Pc(293) int local293;
			for (@Pc(203) int local203 = 0; local203 < local33; local203++) {
				local114 = local102 + 5;
				@Pc(272) int local272;
				if (local175 > local114) {
					local225 = local23[local114][local203] & 0xFF;
					if (local225 > 0) {
						@Pc(236) FluType local236 = FloorUnderlayTypeList.get(local225 - 1);
						local183[local203] += local236.weightedHue;
						local180[local203] += local236.saturation;
						local186[local203] += local236.lightness;
						local189[local203] += local236.chroma;
						local272 = local192[local203]++;
					}
				}
				local225 = local102 - 5;
				if (local225 >= 0) {
					local293 = local23[local225][local203] & 0xFF;
					if (local293 > 0) {
						@Pc(302) FluType local302 = FloorUnderlayTypeList.get(local293 - 1);
						local183[local203] -= local302.weightedHue;
						local180[local203] -= local302.saturation;
						local186[local203] -= local302.lightness;
						local189[local203] -= local302.chroma;
						local272 = local192[local203]--;
					}
				}
			}
			if (local102 >= 0) {
				@Pc(355) int[][] local355 = Static248.anIntArrayArrayArray17[local102 >> 6];
				local114 = 0;
				local225 = 0;
				@Pc(361) int local361 = 0;
				@Pc(363) int local363 = 0;
				local293 = 0;
				for (@Pc(367) int local367 = -5; local367 < local33; local367++) {
					@Pc(378) int local378 = local367 + 5;
					if (local33 > local378) {
						local363 += local192[local378];
						local225 += local180[local378];
						local293 += local186[local378];
						local114 += local183[local378];
						local361 += local189[local378];
					}
					@Pc(415) int local415 = local367 - 5;
					if (local415 >= 0) {
						local293 -= local186[local415];
						local361 -= local189[local415];
						local114 -= local183[local415];
						local363 -= local192[local415];
						local225 -= local180[local415];
					}
					if (local367 >= 0 && local363 > 0) {
						@Pc(462) int[] local462 = local355[local367 >> 6];
						@Pc(480) int local480 = local361 == 0 ? 0 : ColorUtils.method1309(local293 / local363, local225 / local363, local114 * 256 / local361);
						if (local23[local102][local367] != 0) {
							if (local462 == null) {
								local462 = local355[local367 >> 6] = new int[4096];
							}
							@Pc(519) int local519 = local13 + (local480 & 0x7F);
							if (local519 < 0) {
								local519 = 0;
							} else if (local519 > 127) {
								local519 = 127;
							}
							@Pc(541) int local541 = local519 + (local480 & 0x380) + (local480 + local19 & 0xFC00);
							local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = Rasterizer.palette[ColorUtils.multiplyLightnessSafe(96, local541)];
						} else if (local462 != null) {
							local462[((local367 & 0x3F) << 6) + (local102 & 0x3F)] = 0;
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!cj", name = "a", descriptor = "(IB)I")
	public static int method872(@OriginalArg(0) int arg0) {
		return arg0 & 0xFF;
	}

}
