package com.jagex.runetek4.entity.loc;

import com.jagex.runetek4.core.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!pa")
public final class ObjStackNode extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!pa", name = "T", descriptor = "Lclient!uj;")
	public final ObjStack value;

	@OriginalMember(owner = "runetek4.client!pa", name = "<init>", descriptor = "(Lclient!uj;)V")
	public ObjStackNode(@OriginalArg(0) ObjStack arg0) {
		this.value = arg0;
	}
}
