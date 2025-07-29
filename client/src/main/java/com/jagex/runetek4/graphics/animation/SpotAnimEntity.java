package com.jagex.runetek4.graphics.animation;

import com.jagex.runetek4.core.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!bk")
public final class SpotAnimEntity extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!bk", name = "M", descriptor = "Lclient!bh;")
	public final SpotAnim animation;

	@OriginalMember(owner = "runetek4.client!bk", name = "<init>", descriptor = "(Lclient!bh;)V")
	public SpotAnimEntity(@OriginalArg(0) SpotAnim arg0) {
		this.animation = arg0;
	}
}
