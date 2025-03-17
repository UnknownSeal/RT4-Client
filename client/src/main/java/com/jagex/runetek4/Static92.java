package com.jagex.runetek4;

import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static92 {

	@OriginalMember(owner = "runetek4.client!hd", name = "a", descriptor = "I")
	public static int anInt2430 = 0;

	@OriginalMember(owner = "runetek4.client!hd", name = "g", descriptor = "[J")
	public static final long[] friendName37 = new long[200];

	@OriginalMember(owner = "runetek4.client!hd", name = "i", descriptor = "I")
	public static int anInt2433 = 0;

	@OriginalMember(owner = "runetek4.client!hd", name = "a", descriptor = "(IIIIIIII)V")
	public static void method1881(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6) {
		if (arg5 < 0 || arg3 < 0 || arg5 >= 103 || arg3 >= 103) {
			return;
		}
		@Pc(38) int local38;
		if (arg4 == 0) {
			@Pc(28) Wall local28 = SceneGraph.getWall(arg0, arg5, arg3);
			if (local28 != null) {
				local38 = Integer.MAX_VALUE & (int) (local28.aLong107 >>> 32);
				if (arg2 == 2) {
					local28.primary = new Loc(local38, 2, arg1 + 4, arg0, arg5, arg3, arg6, false, local28.primary);
					local28.modelB = new Loc(local38, 2, arg1 + 1 & 0x3, arg0, arg5, arg3, arg6, false, local28.modelB);
				} else {
					local28.primary = new Loc(local38, arg2, arg1, arg0, arg5, arg3, arg6, false, local28.primary);
				}
			}
		}
		if (arg4 == 1) {
			@Pc(106) WallDecor local106 = Static83.method435(arg0, arg5, arg3);
			if (local106 != null) {
				local38 = (int) (local106.key >>> 32) & Integer.MAX_VALUE;
				if (arg2 == 4 || arg2 == 5) {
					local106.primary = new Loc(local38, 4, arg1, arg0, arg5, arg3, arg6, false, local106.primary);
				} else if (arg2 == 6) {
					local106.primary = new Loc(local38, 4, arg1 + 4, arg0, arg5, arg3, arg6, false, local106.primary);
				} else if (arg2 == 7) {
					local106.primary = new Loc(local38, 4, (arg1 + 2 & 0x3) + 4, arg0, arg5, arg3, arg6, false, local106.primary);
				} else if (arg2 == 8) {
					local106.primary = new Loc(local38, 4, arg1 + 4, arg0, arg5, arg3, arg6, false, local106.primary);
					local106.secondary = new Loc(local38, 4, (arg1 + 2 & 0x3) + 4, arg0, arg5, arg3, arg6, false, local106.secondary);
				}
			}
		}
		if (arg4 == 2) {
			if (arg2 == 11) {
				arg2 = 10;
			}
			@Pc(255) Scenery local255 = SceneGraph.getScenery(arg0, arg5, arg3);
			if (local255 != null) {
				local255.entity = new Loc((int) (local255.hash >>> 32) & Integer.MAX_VALUE, arg2, arg1, arg0, arg5, arg3, arg6, false, local255.entity);
			}
		}
		if (arg4 == 3) {
			@Pc(290) GroundDecor local290 = SceneGraph.getGroundDecor(arg0, arg5, arg3);
			if (local290 != null) {
				local290.entity = new Loc(Integer.MAX_VALUE & (int) (local290.key >>> 32), 22, arg1, arg0, arg5, arg3, arg6, false, local290.entity);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hd", name = "a", descriptor = "(I)V")
	public static void removeSoft() {
		Static220.aClass99_28.removeSoft();
	}
}
