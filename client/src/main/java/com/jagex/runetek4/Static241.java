package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static241 {

	@OriginalMember(owner = "runetek4.client!th", name = "f", descriptor = "[Lclient!ok;")
	public static IndexedSprite[] aClass36Array16;

	@OriginalMember(owner = "runetek4.client!th", name = "o", descriptor = "[I")
	public static int[] anIntArray522;

	@OriginalMember(owner = "runetek4.client!th", name = "i", descriptor = "[I")
	public static final int[] anIntArray520 = new int[14];

	@OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(ZBLclient!ve;Lclient!dd;Lclient!ve;)V")
	public static void init(@OriginalArg(2) Js5 arg0, @OriginalArg(3) SoftwareFont arg1, @OriginalArg(4) Js5 arg2) {
		Static240.aBoolean276 = true;
		Static230.modelArchive = arg2;
		com.jagex.runetek4.cache.CacheArchive.aClass153_61 = arg0;
		@Pc(23) int local23 = com.jagex.runetek4.cache.CacheArchive.aClass153_61.capacity() - 1;
		ObjTypeList.capacity = com.jagex.runetek4.cache.CacheArchive.aClass153_61.getGroupCapacity(local23) + local23 * 256;
		Static143.aClass100Array104 = new JString[] { null, null, null, null, LocalizedText.DROP};
		Static269.aClass100Array87 = new JString[] { null, null, LocalizedText.TAKE, null, null };
		ObjTypeList.font = arg1;
	}

	@OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(BIIIII)V")
	public static void method4547(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(9) int local9 = arg1 - arg2;
		@Pc(14) int local14 = arg3 - arg4;
		if (local14 == 0) {
			if (local9 != 0) {
				Static101.method2054(arg2, arg4, arg1, arg0);
			}
		} else if (local9 == 0) {
			Static222.method3826(arg0, arg2, arg3, arg4);
		} else {
			if (local9 < 0) {
				local9 = -local9;
			}
			if (local14 < 0) {
				local14 = -local14;
			}
			@Pc(70) boolean local70 = local14 < local9;
			@Pc(74) int local74;
			@Pc(78) int local78;
			if (local70) {
				local74 = arg4;
				arg4 = arg2;
				local78 = arg3;
				arg2 = local74;
				arg3 = arg1;
				arg1 = local78;
			}
			if (arg3 < arg4) {
				local74 = arg4;
				arg4 = arg3;
				arg3 = local74;
				local78 = arg2;
				arg2 = arg1;
				arg1 = local78;
			}
			local74 = arg2;
			local78 = arg3 - arg4;
			@Pc(111) int local111 = arg1 - arg2;
			@Pc(116) int local116 = -(local78 >> 1);
			@Pc(123) int local123 = arg1 <= arg2 ? -1 : 1;
			if (local111 < 0) {
				local111 = -local111;
			}
			@Pc(136) int local136;
			if (local70) {
				for (local136 = arg4; local136 <= arg3; local136++) {
					ObjTypeList.anIntArrayArray10[local136][local74] = arg0;
					local116 += local111;
					if (local116 > 0) {
						local74 += local123;
						local116 -= local78;
					}
				}
			} else {
				for (local136 = arg4; local136 <= arg3; local136++) {
					local116 += local111;
					ObjTypeList.anIntArrayArray10[local74][local136] = arg0;
					if (local116 > 0) {
						local74 += local123;
						local116 -= local78;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!th", name = "a", descriptor = "(Z)V")
	public static void method4548() {
		Static172.aClass3_Sub3_Sub4_2.method4446();
		Static14.anInt441 = 1;
		Static172.aClass153_70 = null;
	}
}
