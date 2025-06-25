package com.jagex.runetek4;

import com.jagex.runetek4.node.Node;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!oj")
public final class MapFunction extends Node {

	@OriginalMember(owner = "runetek4.client!oj", name = "q", descriptor = "I")
	public int x;

	@OriginalMember(owner = "runetek4.client!oj", name = "r", descriptor = "I")
	public int id;

	@OriginalMember(owner = "runetek4.client!oj", name = "A", descriptor = "I")
	public int z;
}
