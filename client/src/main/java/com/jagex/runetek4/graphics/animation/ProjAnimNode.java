package com.jagex.runetek4.graphics.animation;

import com.jagex.runetek4.core.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!ud")
public final class ProjAnimNode extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!ud", name = "R", descriptor = "Lclient!ra;")
	public final ProjectileAnimation value;

	@OriginalMember(owner = "runetek4.client!ud", name = "<init>", descriptor = "(Lclient!ra;)V")
	public ProjAnimNode(@OriginalArg(0) ProjectileAnimation value) {
		this.value = value;
	}
}
