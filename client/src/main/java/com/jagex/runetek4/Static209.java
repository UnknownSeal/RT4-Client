package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static209 {

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(IBILclient!ve;)[Lclient!qf;")
	public static Sprite[] method3708(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return SpriteLoader.decode(arg1, 0, arg0) ? Static213.method3730() : null;
	}

}
