package com.jagex.runetek4;

import com.jagex.runetek4.node.CachedNode;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!gf")
public abstract class ReferenceNode extends CachedNode {

	@OriginalMember(owner = "runetek4.client!gf", name = "<init>", descriptor = "()V")
	protected ReferenceNode() {
	}

	@OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(Z)Ljava/lang/Object;")
	public abstract Object get();

	@OriginalMember(owner = "runetek4.client!gf", name = "e", descriptor = "(I)Z")
	public abstract boolean isSoft();
}
