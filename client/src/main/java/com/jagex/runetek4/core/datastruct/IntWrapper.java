package com.jagex.runetek4.core.datastruct;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ka")
public final class IntWrapper extends Node {

	@OriginalMember(owner = "client!ka", name = "p", descriptor = "I")
	public int value;

	@OriginalMember(owner = "client!ka", name = "<init>", descriptor = "()V")
	public IntWrapper() {
	}

	@OriginalMember(owner = "client!ka", name = "<init>", descriptor = "(I)V")
	public IntWrapper(@OriginalArg(0) int value) {
		this.value = value;
	}
}
