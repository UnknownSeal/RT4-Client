package com.jagex.runetek4.core.datastruct;

import com.jagex.runetek4.core.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!gf")
public abstract class ReferenceNode extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!gf", name = "<init>", descriptor = "()V")
	protected ReferenceNode() {
	}

	@OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(Z)Ljava/lang/Object;")
	public abstract Object get();

	@OriginalMember(owner = "runetek4.client!gf", name = "e", descriptor = "(I)Z")
	public abstract boolean isSoft();
}
