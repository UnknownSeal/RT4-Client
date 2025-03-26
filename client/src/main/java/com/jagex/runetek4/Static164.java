package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static164 {

	@OriginalMember(owner = "runetek4.client!na", name = "cb", descriptor = "Lclient!na;")
	public static final JString ALLYREQ = JString.parse(":allyreq:");

	@OriginalMember(owner = "runetek4.client!na", name = "mb", descriptor = "[I")
	public static final int[] anIntArray362 = new int[50];

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(Lclient!ba;Lclient!ba;IIIZZ)I")
	public static int method3115(@OriginalArg(0) GWCWorld arg0, @OriginalArg(1) GWCWorld arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) boolean arg4, @OriginalArg(6) boolean arg5) {
		@Pc(8) int local8 = Static270.method4595(arg1, arg3, arg0, arg5);
		if (local8 != 0) {
			return arg5 ? -local8 : local8;
		} else if (arg2 == -1) {
			return 0;
		} else {
			@Pc(42) int local42 = Static270.method4595(arg1, arg2, arg0, arg4);
			return arg4 ? -local42 : local42;
		}
	}

	@OriginalMember(owner = "runetek4.client!na", name = "a", descriptor = "(Lclient!ve;IZ)Lclient!mm;")
	public static SoftwareSprite method3117(@OriginalArg(0) Js5 arg0, @OriginalArg(1) int arg1) {
		return Static254.method4346(arg0, arg1) ? Static196.method3537() : null;
	}

}
