package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static229 {

	@OriginalMember(owner = "runetek4.client!si", name = "R", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray12;

	@OriginalMember(owner = "runetek4.client!si", name = "cb", descriptor = "[B")
	public static byte[] aByteArray70;

	@OriginalMember(owner = "runetek4.client!si", name = "Z", descriptor = "Lclient!na;")
	public static final JString YELLOW = JString.parse("<col=ffb000>");

	@OriginalMember(owner = "runetek4.client!si", name = "a", descriptor = "(ZB)I")
	public static int method3933(@OriginalArg(1) byte arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "runetek4.client!si", name = "a", descriptor = "(BLclient!na;)I")
	public static int method3937(@OriginalArg(1) JString arg0) {
		return arg0.length() + 1;
	}

}
