package runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import runetek4.core.datastruct.Node;

@OriginalClass("runetek4.client!hb")
public final class Class3_Sub13 extends Node {

	@OriginalMember(owner = "runetek4.client!hb", name = "y", descriptor = "Lclient!na;")
	public final JagString aClass100_503;

	@OriginalMember(owner = "runetek4.client!hb", name = "<init>", descriptor = "(Lclient!na;I)V")
	public Class3_Sub13(@OriginalArg(0) JagString arg0, @OriginalArg(1) int arg1) {
		this.aClass100_503 = arg0;
	}
}
