package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static130 {

	@OriginalMember(owner = "runetek4.client!kc", name = "n", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_1;

	@OriginalMember(owner = "runetek4.client!kc", name = "t", descriptor = "I")
	public static int p12FullId;

	@OriginalMember(owner = "runetek4.client!kc", name = "C", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_2;

	@OriginalMember(owner = "runetek4.client!kc", name = "a", descriptor = "(ILclient!ve;I)Lclient!qf;")
	public static Sprite method2514(@OriginalArg(1) Js5 arg0, @OriginalArg(2) int arg1) {
		return Static254.method4346(arg0, arg1) ? Static82.method1764() : null;
	}
}
