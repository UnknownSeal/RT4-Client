package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static209 {

	@OriginalMember(owner = "runetek4.client!ra", name = "R", descriptor = "Z")
	public static boolean aBoolean240 = true;

	@OriginalMember(owner = "runetek4.client!ra", name = "jb", descriptor = "J")
	public static volatile long aLong161 = 0L;

	@OriginalMember(owner = "runetek4.client!ra", name = "c", descriptor = "(BI)V")
	public static void method3706() {
		Static93.aClass99_13.clean(5);
		Static125.aClass99_18.clean(5);
		Static262.aClass99_35.clean(5);
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(IBILclient!ve;)[Lclient!qf;")
	public static Sprite[] method3708(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return SpriteLoader.decode(arg1, 0, arg0) ? Static213.method3730() : null;
	}

}
