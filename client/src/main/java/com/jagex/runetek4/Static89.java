package com.jagex.runetek4;

import java.util.Random;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static89 {

	@OriginalMember(owner = "runetek4.client!ha", name = "k", descriptor = "[[Z")
	public static boolean[][] aBooleanArrayArray3;

	@OriginalMember(owner = "runetek4.client!ha", name = "o", descriptor = "I")
	public static int anInt2387;

	@OriginalMember(owner = "runetek4.client!ha", name = "m", descriptor = "I")
	public static int anInt2385 = 0;

	@OriginalMember(owner = "runetek4.client!ha", name = "p", descriptor = "Lclient!gn;")
	public static final LruHashTable aClass54_8 = new LruHashTable(64);

	@OriginalMember(owner = "runetek4.client!ha", name = "q", descriptor = "I")
	public static int anInt2388 = 0;

	@OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(ZB)V")
	public static void method1835(@OriginalArg(0) boolean arg0) {
		@Pc(11) byte local11;
		@Pc(13) byte[][] local13;
		if (GlRenderer.enabled && arg0) {
			local11 = 1;
			local13 = Static186.aByteArrayArray14;
		} else {
			local13 = Static273.aByteArrayArray13;
			local11 = 4;
		}
		for (@Pc(21) int local21 = 0; local21 < local11; local21++) {
			client.audioLoop();
			for (@Pc(32) int local32 = 0; local32 < 13; local32++) {
				for (@Pc(39) int local39 = 0; local39 < 13; local39++) {
					@Pc(52) int local52 = Static187.anIntArrayArrayArray18[local21][local32][local39];
					@Pc(54) boolean local54 = false;
					if (local52 != -1) {
						@Pc(65) int local65 = local52 >> 24 & 0x3;
						if (!arg0 || local65 == 0) {
							@Pc(76) int local76 = local52 >> 3 & 0x7FF;
							@Pc(82) int local82 = local52 >> 1 & 0x3;
							@Pc(88) int local88 = local52 >> 14 & 0x3FF;
							@Pc(98) int local98 = (local88 / 8 << 8) + local76 / 8;
							for (@Pc(100) int local100 = 0; local100 < Static238.anIntArray470.length; local100++) {
								if (Static238.anIntArray470[local100] == local98 && local13[local100] != null) {
									Static245.method4228(local82, local32 * 8, local21, PathFinder.collisionMaps, local39 * 8, local13[local100], local65, (local76 & 0x7) * 8, (local88 & 0x7) * 8, arg0);
									local54 = true;
									break;
								}
							}
						}
					}
					if (!local54) {
						Static23.method645(local21, local39 * 8, local32 * 8, 8, 8);
					}
				}
			}
		}
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(ILclient!be;)Lclient!be;")
	public static Component method1836(@OriginalArg(1) Component arg0) {
		@Pc(12) Component local12 = Static36.method938(arg0);
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
				@Pc(58) int local58 = Static171.method3219(local53, local24);
				@Pc(62) byte local62 = local27[local58];
				local27[local58] = local27[local53];
				local27[local53] = local27[511 - local29] = local62;
			}
			local10 = new ByteArrayNodeSecondary(local27);
			Static53.aClass54_5.put(local10, (long) arg0);
		}
		return local10.balue;
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "([IJIZ)Lclient!na;")
	public static JString method1838(@OriginalArg(0) int[] arg0, @OriginalArg(1) long arg1, @OriginalArg(2) int arg2) {
		if (Static107.anInterface3_1 != null) {
			@Pc(17) JString local17 = Static107.anInterface3_1.method30(arg2, arg0, arg1);
			if (local17 != null) {
				return local17;
			}
		}
		return Static154.method2929(arg1);
	}

	@OriginalMember(owner = "runetek4.client!ha", name = "a", descriptor = "(IIIII)V")
	public static void method1843(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(7) int local7 = 0;
		@Pc(9) int local9 = arg2;
		@Pc(12) int local12 = -arg2;
		@Pc(14) int local14 = -1;
		@Pc(22) int local22 = Static78.method1690(FluTypeList.anInt5063, arg2 + arg3, Static172.anInt4164);
		@Pc(30) int local30 = Static78.method1690(FluTypeList.anInt5063, arg3 - arg2, Static172.anInt4164);
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
					local84 = Static78.method1690(FluTypeList.anInt5063, arg3 + local7, Static172.anInt4164);
					local93 = Static78.method1690(FluTypeList.anInt5063, arg3 - local7, Static172.anInt4164);
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
				local84 = Static78.method1690(FluTypeList.anInt5063, arg3 + local9, Static172.anInt4164);
				local93 = Static78.method1690(FluTypeList.anInt5063, arg3 - local9, Static172.anInt4164);
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
