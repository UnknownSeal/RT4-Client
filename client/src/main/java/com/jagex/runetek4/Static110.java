package com.jagex.runetek4;

import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.Wall;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static110 {

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(III)Lclient!jh;")
	public static Wall method2276(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Tile local7 = SceneGraph.tiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) Wall local14 = local7.wall;
			local7.wall = null;
			return local14;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(I[Lclient!hg;)V")
	public static void method2280(@OriginalArg(0) int arg0, @OriginalArg(1) GlTile[] arg1) {
		Static182.aGlTileArrayArray2[arg0] = arg1;
	}

}
