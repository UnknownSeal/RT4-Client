package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!eb")
public final class LongNode extends Node {

	@OriginalMember(owner = "runetek4.client!eb", name = "v", descriptor = "J")
	public long aLong55;

	@OriginalMember(owner = "runetek4.client!eb", name = "<init>", descriptor = "(J)V")
	public LongNode(@OriginalArg(0) long arg0) {
		this.aLong55 = arg0;
	}
}
