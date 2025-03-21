package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.NodeCache;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.Wall;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static262 {

	@OriginalMember(owner = "runetek4.client!vf", name = "c", descriptor = "I")
	public static int anInt5752;

	@OriginalMember(owner = "runetek4.client!vf", name = "n", descriptor = "Lclient!ve;")
	public static Js5 configClientLarge;

	@OriginalMember(owner = "runetek4.client!vf", name = "g", descriptor = "[I")
	public static final int[] anIntArray515 = new int[14];

	@OriginalMember(owner = "runetek4.client!vf", name = "k", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_35 = new NodeCache(5);

	@OriginalMember(owner = "runetek4.client!vf", name = "m", descriptor = "I")
	public static int bgId = -1;

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
		wall.primary = modelA;
		wall.modelB = modelB;
		wall.typeA = arg6;
		wall.typeB = arg7;
		for (@Pc(42) int l = level; l >= 0; l--) {
			if (Static130.levelTiles[l][arg1][z] == null) {
				Static130.levelTiles[l][arg1][z] = new Tile(l, arg1, z);
			}
		}
		Static130.levelTiles[level][arg1][z].wall = wall;
	}

}
