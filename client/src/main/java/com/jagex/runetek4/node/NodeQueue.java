package com.jagex.runetek4.node;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ce")
public final class NodeQueue {

	@OriginalMember(owner = "client!ce", name = "n", descriptor = "Lclient!rg;")
	private CachedNode peeked;

	@OriginalMember(owner = "client!ce", name = "l", descriptor = "Lclient!rg;")
	private final CachedNode head = new CachedNode();

	@OriginalMember(owner = "client!ce", name = "<init>", descriptor = "()V")
	public NodeQueue() {
		this.head.previousCachedNode = this.head;
		this.head.nextCachedNode = this.head;
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(I)I")
	public int length() {
		@Pc(3) int length = 0;
		@Pc(7) CachedNode cachedNode = this.head.previousCachedNode;
		while (cachedNode != this.head) {
			cachedNode = cachedNode.previousCachedNode;
			length++;
		}
		return length;
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(B)Lclient!rg;")
	public CachedNode head() {
		@Pc(3) CachedNode node = this.head.previousCachedNode;
		if (this.head == node) {
			this.peeked = null;
			return null;
		} else {
			this.peeked = node.previousCachedNode;
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(I)Lclient!rg;")
	public CachedNode removeHead() {
		@Pc(7) CachedNode node = this.head.previousCachedNode;
		if (node == this.head) {
			return null;
		} else {
			node.unlinkCachedNode();
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "c", descriptor = "(I)Lclient!rg;")
	public CachedNode prev() {
		@Pc(2) CachedNode node = this.peeked;
		if (node == this.head) {
			this.peeked = null;
			return null;
		} else {
			this.peeked = node.previousCachedNode;
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(Lclient!rg;B)V")
	public void addTail(@OriginalArg(0) CachedNode arg0) {
		if (arg0.nextCachedNode != null) {
			arg0.unlinkCachedNode();
		}
		arg0.nextCachedNode = this.head.nextCachedNode;
		arg0.previousCachedNode = this.head;
		arg0.nextCachedNode.previousCachedNode = arg0;
		arg0.previousCachedNode.nextCachedNode = arg0;
	}

	@OriginalMember(owner = "client!ce", name = "d", descriptor = "(I)V")
	public void clear() {
		while (true) {
			@Pc(15) CachedNode local15 = this.head.previousCachedNode;
			if (this.head == local15) {
				this.peeked = null;
				return;
			}
			local15.unlinkCachedNode();
		}
	}
}
