package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static65 {

	@OriginalMember(owner = "client!fc", name = "f", descriptor = "Lclient!na;")
	public static final JString aClass100_435 = JString.parse("<img=0>");

	@OriginalMember(owner = "client!fc", name = "a", descriptor = "(Lclient!wa;I)Lclient!na;")
	public static JString method1497(@OriginalArg(0) Packet arg0) {
		return Static254.method4350(arg0);
	}

}
