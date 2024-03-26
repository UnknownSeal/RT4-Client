package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!ka")
public final class IntNode extends Node {

	@OriginalMember(owner = "runetek4.client!ka", name = "p", descriptor = "I")
	public int anInt3141;

	@OriginalMember(owner = "runetek4.client!ka", name = "<init>", descriptor = "()V")
	public IntNode() {
	}

	@OriginalMember(owner = "runetek4.client!ka", name = "<init>", descriptor = "(I)V")
	public IntNode(@OriginalArg(0) int arg0) {
		this.anInt3141 = arg0;
	}
}
