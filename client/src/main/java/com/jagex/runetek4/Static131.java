package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static131 {

	@OriginalMember(owner = "runetek4.client!kd", name = "Cb", descriptor = "I")
	public static int anInt3261;

	@OriginalMember(owner = "runetek4.client!kd", name = "rb", descriptor = "I")
	public static final int anInt3254 = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "runetek4.client!kd", name = "zb", descriptor = "I")
	public static int anInt3259 = 0;

	@OriginalMember(owner = "runetek4.client!kd", name = "a", descriptor = "(IIILclient!ve;)[Lclient!qf;")
	public static Sprite[] method2580(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return SpriteLoader.decode(arg1, 0, arg0) ? Class6.method870() : null;
	}
}
