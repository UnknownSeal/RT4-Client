package com.jagex.runetek4.core.datastruct;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!rg")
public class CachedNode extends Node {

	@OriginalMember(owner = "client!rg", name = "v", descriptor = "J")
	public long secondaryNodeId;

	@OriginalMember(owner = "client!rg", name = "w", descriptor = "Lclient!rg;")
	public CachedNode secondaryNext;

	@OriginalMember(owner = "client!rg", name = "G", descriptor = "Lclient!rg;")
	public CachedNode secondaryPrev;

	@OriginalMember(owner = "client!rg", name = "e", descriptor = "(B)V")
	public final void clear() {
		if (this.secondaryNext != null) {
			this.secondaryNext.secondaryPrev = this.secondaryPrev;
			this.secondaryPrev.secondaryNext = this.secondaryNext;
			this.secondaryPrev = null;
			this.secondaryNext = null;
		}
	}
}
