package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static265 {

	@OriginalMember(owner = "runetek4.client!vj", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_1086 = JString.parse("_labels");

	@OriginalMember(owner = "runetek4.client!vj", name = "a", descriptor = "(IIILclient!ve;)[Lclient!mm;")
	public static SoftwareSprite[] method4523(@OriginalArg(2) int arg0, @OriginalArg(3) Js5 arg1) {
		return Static234.method4016(arg1, 0, arg0) ? Static14.method474() : null;
	}
}
