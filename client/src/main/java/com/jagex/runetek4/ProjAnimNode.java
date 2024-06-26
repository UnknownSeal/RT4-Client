package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ud")
public final class ProjAnimNode extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!ud", name = "R", descriptor = "Lclient!ra;")
	public final ProjectileAnimation aClass8_Sub6_1;

	@OriginalMember(owner = "runetek4.client!ud", name = "<init>", descriptor = "(Lclient!ra;)V")
	public ProjAnimNode(@OriginalArg(0) ProjectileAnimation arg0) {
		this.aClass8_Sub6_1 = arg0;
	}
}
