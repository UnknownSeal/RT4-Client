package com.jagex.runetek4;

import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static88 {

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(Lclient!ve;Lclient!ve;Z)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
		Static86.aClass153_37 = arg0;
		Static58.aClass153_28 = arg1;
	}

	@OriginalMember(owner = "client!h", name = "a", descriptor = "(I)Lclient!ba;")
	public static GWCWorld method1821() {
		return Static101.aClass10_Sub1Array1.length > Static51.anInt1682 ? Static101.aClass10_Sub1Array1[Static51.anInt1682++] : null;
	}

}