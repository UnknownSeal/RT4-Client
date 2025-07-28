package com.jagex.runetek4.graphics.cache;

import com.jagex.runetek4.core.node.Node;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!lb")
public final class MonochromeImageCacheEntry extends Node {

	@OriginalMember(owner = "runetek4.client!lb", name = "t", descriptor = "I")
	public final int row;

	@OriginalMember(owner = "runetek4.client!lb", name = "C", descriptor = "I")
	public final int index;

	@OriginalMember(owner = "runetek4.client!lb", name = "<init>", descriptor = "(II)V")
	public MonochromeImageCacheEntry(@OriginalArg(0) int index, @OriginalArg(1) int row) {
		this.row = row;
		this.index = index;
	}
}
