package com.jagex.runetek4;

import com.jagex.runetek4.game.config.bastype.BasTypeList;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static79 {

	@OriginalMember(owner = "client!ge", name = "a", descriptor = "(BLclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0) {
		BasTypeList.archive = arg0;
	}
}
