package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.ObjStackEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static187 {

	@OriginalMember(owner = "runetek4.client!pb", name = "q", descriptor = "[Z")
	public static final boolean[] pressedKeys = new boolean[112];

	@OriginalMember(owner = "runetek4.client!pb", name = "x", descriptor = "[[[I")
	public static final int[][][] anIntArrayArrayArray18 = new int[4][13][13];

    @OriginalMember(owner = "runetek4.client!pb", name = "ab", descriptor = "I")
	public static int anInt4422 = 0;

	@OriginalMember(owner = "runetek4.client!pb", name = "rb", descriptor = "S")
	public static short aShort27 = 320;

	@OriginalMember(owner = "runetek4.client!pb", name = "b", descriptor = "(III)Lclient!jj;")
	public static ObjStackEntity method3420(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Ground local7 = Static130.levelTiles[arg0][arg1][arg2];
		if (local7 == null) {
			return null;
		} else {
			@Pc(14) ObjStackEntity local14 = local7.aClass79_1;
			local7.aClass79_1 = null;
			return local14;
		}
	}
}
