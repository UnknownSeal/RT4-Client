package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static266 {

	@OriginalMember(owner = "runetek4.client!vk", name = "h", descriptor = "I")
	public static final int anInt5338 = (int) (Math.random() * 33.0D) - 16;

	@OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(Lclient!ve;Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
		Static93.modelArchive = arg0;
		Static132.aClass153_48 = arg1;
	}

}
