package com.jagex.runetek4.node;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!rg")
public class CachedNode extends Node {

	@OriginalMember(owner = "client!rg", name = "v", descriptor = "J")
	public long secondaryNodeId;

	@OriginalMember(owner = "client!rg", name = "w", descriptor = "Lclient!rg;")
	public CachedNode nextCachedNode;

	@OriginalMember(owner = "client!rg", name = "G", descriptor = "Lclient!rg;")
	public CachedNode previousCachedNode;

	@OriginalMember(owner = "client!rg", name = "e", descriptor = "(B)V")
	public final void clear() {
		if (this.nextCachedNode != null) {
			this.nextCachedNode.previousCachedNode = this.previousCachedNode;
			this.previousCachedNode.nextCachedNode = this.nextCachedNode;
			this.previousCachedNode = null;
			this.nextCachedNode = null;
		}
	}
}
