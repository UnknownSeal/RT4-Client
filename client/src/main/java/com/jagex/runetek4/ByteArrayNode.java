package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ea")
public final class ByteArrayNode extends Node {

	@OriginalMember(owner = "runetek4.client!ea", name = "t", descriptor = "[B")
	public final byte[] value;

	static {
		@Pc(10) int local10 = 2;
		for (@Pc(12) int local12 = 0; local12 < 32; local12++) {
			VarpDomain.varbitMasks[local12] = local10 - 1;
			local10 += local10;
		}
	}

	@OriginalMember(owner = "runetek4.client!ea", name = "<init>", descriptor = "([B)V")
	public ByteArrayNode(@OriginalArg(0) byte[] arg0) {
		this.value = arg0;
	}
}
