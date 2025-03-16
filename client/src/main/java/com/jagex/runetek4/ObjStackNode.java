package com.jagex.runetek4;

import com.jagex.runetek4.node.CachedNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!pa")
public final class ObjStackNode extends CachedNode {

	@OriginalMember(owner = "runetek4.client!pa", name = "T", descriptor = "Lclient!uj;")
	public final ObjStack aClass8_Sub7_1;

	@OriginalMember(owner = "runetek4.client!pa", name = "<init>", descriptor = "(Lclient!uj;)V")
	public ObjStackNode(@OriginalArg(0) ObjStack arg0) {
		this.aClass8_Sub7_1 = arg0;
	}
}
