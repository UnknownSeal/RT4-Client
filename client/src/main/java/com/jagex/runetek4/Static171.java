package com.jagex.runetek4;

import java.util.Random;

import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static171 {

	@OriginalMember(owner = "runetek4.client!ni", name = "h", descriptor = "I")
	public static int anInt4153;

	@OriginalMember(owner = "runetek4.client!ni", name = "q", descriptor = "I")
	public static int anInt4157;

	@OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(ILclient!na;)I")
	public static int method3218(@OriginalArg(1) JString arg0) {
		if (Static203.aMapElementTypeList_1 == null || arg0.length() == 0) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static203.aMapElementTypeList_1.anInt5074; local20++) {
			if (Static203.aMapElementTypeList_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).method3142(arg0)) {
				return local20;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(BILjava/util/Random;)I")
	public static int method3219(@OriginalArg(1) int arg0, @OriginalArg(2) Random arg1) {
		if (arg0 <= 0) {
			throw new IllegalArgumentException();
		} else if (IntUtils.isPowerOfTwo(arg0)) {
			return (int) (((long) arg1.nextInt() & 0xFFFFFFFFL) * (long) arg0 >> 32);
		} else {
			@Pc(38) int local38 = Integer.MIN_VALUE - (int) (4294967296L % (long) arg0);
			@Pc(41) int local41;
			do {
				local41 = arg1.nextInt();
			} while (local38 <= local41);
			return Static39.method990(local41, arg0);
		}
	}

	@OriginalMember(owner = "runetek4.client!ni", name = "a", descriptor = "(ILclient!na;I)V")
	public static void method3221(@OriginalArg(1) JString arg0, @OriginalArg(2) int arg1) {
		Protocol.outboundBuffer.pIsaac1(188);
		Protocol.outboundBuffer.p1b_alt1(arg1);
		Protocol.outboundBuffer.p8(arg0.encode37());
	}
}
