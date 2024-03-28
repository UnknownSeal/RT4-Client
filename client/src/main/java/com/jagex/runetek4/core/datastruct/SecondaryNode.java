package com.jagex.runetek4.core.datastruct;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!rg")
public class SecondaryNode extends Node {

	@OriginalMember(owner = "client!rg", name = "v", descriptor = "J")
	public long secondaryNodeId;

	@OriginalMember(owner = "client!rg", name = "w", descriptor = "Lclient!rg;")
	public SecondaryNode secondaryNext;

	@OriginalMember(owner = "client!rg", name = "G", descriptor = "Lclient!rg;")
	public SecondaryNode secondaryPrev;

	@OriginalMember(owner = "client!rg", name = "e", descriptor = "(B)V")
	public final void secondaryRemove() {
		if (this.secondaryNext != null) {
			this.secondaryNext.secondaryPrev = this.secondaryPrev;
			this.secondaryPrev.secondaryNext = this.secondaryNext;
			this.secondaryPrev = null;
			this.secondaryNext = null;
		}
	}
}
