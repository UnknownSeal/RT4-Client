package com.jagex.runetek4.dash3d.entity;

import com.jagex.runetek4.SpotAnim;
import com.jagex.runetek4.node.CachedNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!bk")
public final class SpotAnimEntity extends CachedNode {

	@OriginalMember(owner = "runetek4.client!bk", name = "M", descriptor = "Lclient!bh;")
	public final SpotAnim aClass8_Sub2_1;

	@OriginalMember(owner = "runetek4.client!bk", name = "<init>", descriptor = "(Lclient!bh;)V")
	public SpotAnimEntity(@OriginalArg(0) SpotAnim arg0) {
		this.aClass8_Sub2_1 = arg0;
	}
}
