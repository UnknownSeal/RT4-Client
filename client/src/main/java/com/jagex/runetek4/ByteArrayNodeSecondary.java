package com.jagex.runetek4;

import com.jagex.runetek4.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!hc")
public final class ByteArrayNodeSecondary extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!hc", name = "Q", descriptor = "[B")
	public final byte[] balue;

	@OriginalMember(owner = "runetek4.client!hc", name = "<init>", descriptor = "([B)V")
	public ByteArrayNodeSecondary(@OriginalArg(0) byte[] value) {
		this.balue = value;
	}
}
