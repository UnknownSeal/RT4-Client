package com.jagex.runetek4;

import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static83 {

	@OriginalMember(owner = "client!gj", name = "i", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray3;

	@OriginalMember(owner = "client!gj", name = "r", descriptor = "F")
	public static float aFloat3;

	@OriginalMember(owner = "client!gj", name = "q", descriptor = "[I")
	public static final int[] updatedVarps = new int[32];

	@OriginalMember(owner = "client!gj", name = "a", descriptor = "(III)Lclient!df;")
	public static WallDecor method435(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
		return local7 == null ? null : local7.wallDecor;
	}

	@OriginalMember(owner = "client!gj", name = "b", descriptor = "(I)V")
	public static void method440() {
		Static27.setMaterial(0, 0);
	}
}
