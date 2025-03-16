package com.jagex.runetek4;

import com.jagex.runetek4.node.NodeCache;
import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static83 {

	@OriginalMember(owner = "runetek4.client!gj", name = "i", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray3;

	@OriginalMember(owner = "runetek4.client!gj", name = "r", descriptor = "F")
	public static float aFloat3;

	@OriginalMember(owner = "runetek4.client!gj", name = "p", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_3 = new NodeCache(64);

	@OriginalMember(owner = "runetek4.client!gj", name = "q", descriptor = "[I")
	public static final int[] updatedVarps = new int[32];

	@OriginalMember(owner = "runetek4.client!gj", name = "a", descriptor = "(II)Z")
	public static boolean method433(@OriginalArg(0) int arg0) {
		if (arg0 >= 97 && arg0 <= 122) {
			return true;
		} else if (arg0 >= 65 && arg0 <= 90) {
			return true;
		} else {
			return arg0 >= 48 && arg0 <= 57;
		}
	}

	@OriginalMember(owner = "runetek4.client!gj", name = "a", descriptor = "(III)Lclient!df;")
	public static WallDecor method435(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
		return local7 == null ? null : local7.wallDecor;
	}

	@OriginalMember(owner = "runetek4.client!gj", name = "b", descriptor = "(I)V")
	public static void method440() {
		Static27.setMaterial(0, 0);
	}
}
