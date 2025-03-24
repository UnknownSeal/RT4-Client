package com.jagex.runetek4.node;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ab")
public class Node {

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "J")
	public long nodeId;

	@OriginalMember(owner = "client!ab", name = "d", descriptor = "Lclient!ab;")
	public Node prev;

	@OriginalMember(owner = "client!ab", name = "l", descriptor = "Lclient!ab;")
	public Node next;

	@OriginalMember(owner = "client!ab", name = "b", descriptor = "(I)V")
	public final void unlink() {
		if (this.next != null) {
			this.next.prev = this.prev;
			this.prev.next = this.next;
			this.next = null;
			this.prev = null;
		}
	}

	@OriginalMember(owner = "client!ab", name = "a", descriptor = "(I)Z")
	public final boolean isLinked() {
		return this.next != null;
	}
}