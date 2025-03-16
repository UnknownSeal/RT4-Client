package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.scene.tile.SceneTile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static265 {

	@OriginalMember(owner = "runetek4.client!vj", name = "j", descriptor = "Lclient!dd;")
	public static SoftwareFont aClass3_Sub2_Sub9_Sub1_2;

	@OriginalMember(owner = "runetek4.client!vj", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_1086 = Static28.parse("_labels");

	@OriginalMember(owner = "runetek4.client!vj", name = "a", descriptor = "(III)J")
	public static long method4521(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) SceneTile local7 = Static130.levelTiles[arg0][arg1][arg2];
		return local7 == null || local7.wall == null ? 0L : local7.wall.aLong107;
	}

	@OriginalMember(owner = "runetek4.client!vj", name = "a", descriptor = "(IIILclient!ve;)[Lclient!mm;")
	public static ImageRGB[] method4523(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return Static234.method4016(arg1, 0, arg0) ? Static14.method474() : null;
	}
}
