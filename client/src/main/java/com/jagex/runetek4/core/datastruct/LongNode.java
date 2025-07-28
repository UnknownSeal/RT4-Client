package com.jagex.runetek4.core.datastruct;

import com.jagex.runetek4.core.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!eb")
public final class LongNode extends Node {

	@OriginalMember(owner = "runetek4.client!eb", name = "v", descriptor = "J")
	public long value;

	@OriginalMember(owner = "runetek4.client!eb", name = "<init>", descriptor = "(J)V")
	public LongNode(@OriginalArg(0) long arg0) {
		this.value = arg0;
	}
}
