package com.jagex.core.datastruct;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!rg")
public class Node2 extends Node {

	@OriginalMember(owner = "client!rg", name = "v", descriptor = "J")
	public long key2;

	@OriginalMember(owner = "client!rg", name = "w", descriptor = "Lclient!rg;")
	public Node2 prev2;

	@OriginalMember(owner = "client!rg", name = "G", descriptor = "Lclient!rg;")
	public Node2 next2;

	@OriginalMember(owner = "client!rg", name = "e", descriptor = "(B)V")
	public final void unlink2() {
		if (this.prev2 != null) {
			this.prev2.next2 = this.next2;
			this.next2.prev2 = this.prev2;
			this.next2 = null;
			this.prev2 = null;
		}
	}
}
