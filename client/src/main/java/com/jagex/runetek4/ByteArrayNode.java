package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!hc")
public final class ByteArrayNode extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!hc", name = "Q", descriptor = "[B")
	public final byte[] aByteArray37;

	@OriginalMember(owner = "runetek4.client!hc", name = "<init>", descriptor = "([B)V")
	public ByteArrayNode(@OriginalArg(0) byte[] arg0) {
		this.aByteArray37 = arg0;
	}
}
