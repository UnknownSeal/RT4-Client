package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!sj")
public final class StringNode extends Node {

	@OriginalMember(owner = "runetek4.client!sj", name = "A", descriptor = "Lclient!na;")
	public JagString aClass100_980;

	@OriginalMember(owner = "runetek4.client!sj", name = "<init>", descriptor = "()V")
	public StringNode() {
	}

	@OriginalMember(owner = "runetek4.client!sj", name = "<init>", descriptor = "(Lclient!na;)V")
	public StringNode(@OriginalArg(0) JagString arg0) {
		this.aClass100_980 = arg0;
	}
}