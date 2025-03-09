package com.jagex.runetek4;

import com.jagex.runetek4.entity.Entity;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static262 {

	@OriginalMember(owner = "runetek4.client!vf", name = "c", descriptor = "I")
	public static int anInt5752;

	@OriginalMember(owner = "runetek4.client!vf", name = "n", descriptor = "Lclient!ve;")
	public static Js5 configClientLarge;

	@OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_34 = new SoftLruHashTable(64);

	@OriginalMember(owner = "runetek4.client!vf", name = "g", descriptor = "[I")
	public static final int[] anIntArray515 = new int[14];

	@OriginalMember(owner = "runetek4.client!vf", name = "k", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_35 = new SoftLruHashTable(5);

	@OriginalMember(owner = "runetek4.client!vf", name = "l", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_36 = new SoftLruHashTable(50);

	@OriginalMember(owner = "runetek4.client!vf", name = "m", descriptor = "I")
	public static int anInt5754 = -1;

	@OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIJ)V")
	public static void addWall(@OriginalArg(0) int level, @OriginalArg(1) int arg1, @OriginalArg(2) int z, @OriginalArg(3) int arg3, @OriginalArg(4) Entity modelA, @OriginalArg(5) Entity modelB, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) long arg8) {
		if (modelA == null && modelB == null) {
			return;
		}
		@Pc(8) Wall wall = new Wall();
		wall.aLong107 = arg8;
		wall.anInt3048 = arg1 * 128 + 64;
		wall.anInt3044 = z * 128 + 64;
		wall.anInt3051 = arg3;
		wall.modelA = modelA;
		wall.modelB = modelB;
		wall.typeA = arg6;
		wall.typeB = arg7;
		for (@Pc(42) int l = level; l >= 0; l--) {
			if (Static130.levelTiles[l][arg1][z] == null) {
				Static130.levelTiles[l][arg1][z] = new Ground(l, arg1, z);
			}
		}
		Static130.levelTiles[level][arg1][z].wall = wall;
	}

	@OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(III)Lclient!jh;")
	public static Wall method4509(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Ground local7 = Static130.levelTiles[arg0][arg1][arg2];
		return local7 == null ? null : local7.wall;
	}

	@OriginalMember(owner = "runetek4.client!vf", name = "a", descriptor = "(IB)Lclient!na;")
	public static JagString method4510(@OriginalArg(0) int arg0) {
		return arg0 >= 999999999 ? Static220.aClass100_930 : Static123.method2423(arg0);
	}
}
