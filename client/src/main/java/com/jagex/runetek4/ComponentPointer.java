package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!wk")
public final class ComponentPointer extends Node {

	@OriginalMember(owner = "runetek4.client!wk", name = "r", descriptor = "I")
	public int interfaceId;

	@OriginalMember(owner = "runetek4.client!wk", name = "s", descriptor = "I")
	public int anInt5879;
}
