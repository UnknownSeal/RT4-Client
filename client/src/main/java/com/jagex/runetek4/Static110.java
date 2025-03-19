package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.Wall;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static110 {

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(III)Lclient!jh;")
	public static Wall method2276(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Tile local7 = Static130.levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) Wall local14 = local7.wall;
			local7.wall = null;
			return local14;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(II)Lclient!hn;")
	public static Class3_Sub2_Sub12 method2277(@OriginalArg(1) int arg0) {
		@Pc(6) Class3_Sub2_Sub12 local6 = (Class3_Sub2_Sub12) Static272.aClass54_14.get((long) arg0);
		if (local6 != null) {
			return local6;
		}
		@Pc(30) byte[] local30 = Static39.aClass153_23.getfile(11, arg0);
		local6 = new Class3_Sub2_Sub12();
		if (local30 != null) {
			local6.method2076(new Packet(local30));
		}
		Static272.aClass54_14.put(local6, (long) arg0);
		return local6;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(I[Lclient!hg;)V")
	public static void method2280(@OriginalArg(0) int arg0, @OriginalArg(1) GlTile[] arg1) {
		Static182.aGlTileArrayArray2[arg0] = arg1;
	}

}
