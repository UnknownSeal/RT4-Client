package com.jagex.runetek4;

import java.util.Random;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.IntUtils;
import com.jagex.runetek4.util.RandomUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static89 {

	@OriginalMember(owner = "runetek4.client!ha", name = "k", descriptor = "[[Z")
	public static boolean[][] aBooleanArrayArray3;

	@OriginalMember(owner = "runetek4.client!ha", name = "o", descriptor = "I")
	public static int anInt2387;

	@OriginalMember(owner = "runetek4.client!ha", name = "q", descriptor = "I")
	public static int anInt2388 = 0;

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(ILclient!be;)Lclient!be;")
	public static Component method1836(@OriginalArg(1) Component arg0) {
		@Pc(12) Component local12 = InterfaceList.method938(arg0);
		if (local12 == null) {
			local12 = arg0.aClass13_5;
		}
		return local12;
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(II)[B")
	public static byte[] method1837(@OriginalArg(1) int arg0) {
		@Pc(10) ByteArrayNodeSecondary local10 = (ByteArrayNodeSecondary) Static53.aClass54_5.get((long) arg0);
		if (local10 == null) {
			@Pc(24) Random local24 = new Random((long) arg0);
			@Pc(27) byte[] local27 = new byte[512];
			@Pc(29) int local29;
			for (local29 = 0; local29 < 255; local29++) {
				local27[local29] = (byte) local29;
			}
			for (local29 = 0; local29 < 255; local29++) {
				@Pc(53) int local53 = 255 - local29;
				@Pc(58) int local58 = RandomUtils.nextInt(local53, local24);
				@Pc(62) byte local62 = local27[local58];
				local27[local58] = local27[local53];
				local27[local53] = local27[511 - local29] = local62;
			}
			local10 = new ByteArrayNodeSecondary(local27);
			Static53.aClass54_5.put(local10, (long) arg0);
		}
		return local10.balue;
	}

	@OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(IIIII)V")
	public static void method1843(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(7) int local7 = 0;
		@Pc(9) int local9 = arg2;
		@Pc(12) int local12 = -arg2;
		@Pc(14) int local14 = -1;
		@Pc(22) int local22 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg2 + arg3, Static172.anInt4164);
		@Pc(30) int local30 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3 - arg2, Static172.anInt4164);
		ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[arg1], local30, local22, arg0);
		while (local7 < local9) {
			local14 += 2;
			local12 += local14;
			@Pc(58) int local58;
			@Pc(68) int local68;
			@Pc(84) int local84;
			@Pc(93) int local93;
			if (local12 > 0) {
				local9--;
				local58 = arg1 - local9;
				local12 -= local9 << 1;
				local68 = arg1 + local9;
				if (local68 >= Static267.anInt5773 && local58 <= Static106.anInt2869) {
					local84 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3 + local7, Static172.anInt4164);
					local93 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3 - local7, Static172.anInt4164);
					if (Static106.anInt2869 >= local68) {
						ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local68], local93, local84, arg0);
					}
					if (Static267.anInt5773 <= local58) {
						ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local58], local93, local84, arg0);
					}
				}
			}
			local7++;
			local58 = arg1 - local7;
			local68 = local7 + arg1;
			if (local68 >= Static267.anInt5773 && Static106.anInt2869 >= local58) {
				local84 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3 + local9, Static172.anInt4164);
				local93 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg3 - local9, Static172.anInt4164);
				if (local68 <= Static106.anInt2869) {
					ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local68], local93, local84, arg0);
				}
				if (local58 >= Static267.anInt5773) {
					ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local58], local93, local84, arg0);
				}
			}
		}
	}
}
