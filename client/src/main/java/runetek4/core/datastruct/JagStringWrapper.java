package runetek4.core.datastruct;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.JagString;
import runetek4.core.datastruct.Node;

@OriginalClass("client!sj")
public final class JagStringWrapper extends Node {

	@OriginalMember(owner = "client!sj", name = "A", descriptor = "Lclient!na;")
	public JagString value;

	@OriginalMember(owner = "client!sj", name = "<init>", descriptor = "()V")
	public JagStringWrapper() {
	}

	@OriginalMember(owner = "client!sj", name = "<init>", descriptor = "(Lclient!na;)V")
	public JagStringWrapper(@OriginalArg(0) JagString value) {
		this.value = value;
	}
}
