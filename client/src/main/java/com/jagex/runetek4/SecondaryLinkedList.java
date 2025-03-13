package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.CachedNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ce")
public final class SecondaryLinkedList {

	@OriginalMember(owner = "client!ce", name = "n", descriptor = "Lclient!rg;")
	private CachedNode peeked;

	@OriginalMember(owner = "client!ce", name = "l", descriptor = "Lclient!rg;")
	private final CachedNode head = new CachedNode();

	@OriginalMember(owner = "client!ce", name = "<init>", descriptor = "()V")
	public SecondaryLinkedList() {
		this.head.secondaryPrev = this.head;
		this.head.secondaryNext = this.head;
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(I)I")
	public int length() {
		@Pc(3) int length = 0;
		@Pc(7) CachedNode cachedNode = this.head.secondaryPrev;
		while (cachedNode != this.head) {
			cachedNode = cachedNode.secondaryPrev;
			length++;
		}
		return length;
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(B)Lclient!rg;")
	public CachedNode head() {
		@Pc(3) CachedNode node = this.head.secondaryPrev;
		if (this.head == node) {
			this.peeked = null;
			return null;
		} else {
			this.peeked = node.secondaryPrev;
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(I)Lclient!rg;")
	public CachedNode pollFront() {
		@Pc(7) CachedNode node = this.head.secondaryPrev;
		if (node == this.head) {
			return null;
		} else {
			node.clear();
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
			this.peeked = node.secondaryPrev;
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(Lclient!rg;B)V")
	public void pushBack(@OriginalArg(0) CachedNode arg0) {
		if (arg0.secondaryNext != null) {
			arg0.clear();
		}
		arg0.secondaryNext = this.head.secondaryNext;
		arg0.secondaryPrev = this.head;
		arg0.secondaryNext.secondaryPrev = arg0;
		arg0.secondaryPrev.secondaryNext = arg0;
	}

	@OriginalMember(owner = "client!ce", name = "d", descriptor = "(I)V")
	public void method802() {
		while (true) {
			@Pc(15) CachedNode local15 = this.head.secondaryPrev;
			if (this.head == local15) {
				this.peeked = null;
				return;
			}
			local15.clear();
		}
	}
}
