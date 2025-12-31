package com.jagex.core.datastruct;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ih")
public final class LinkedList {

	@OriginalMember(owner = "runetek4.client!ih", name = "p", descriptor = "Lclient!ab;")
	private Node pointer;

	@OriginalMember(owner = "runetek4.client!ih", name = "m", descriptor = "Lclient!ab;")
	public final Node sentinel = new Node();

	@OriginalMember(owner = "runetek4.client!ih", name = "<init>", descriptor = "()V")
	public LinkedList() {
		this.sentinel.next = this.sentinel;
		this.sentinel.prev = this.sentinel;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(I)V")
	public final void clear() {
		while (true) {
			@Pc(5) Node node = this.sentinel.prev;
			if (node == this.sentinel) {
				this.pointer = null;
				return;
			}
			node.unlink();
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "b", descriptor = "(I)Lclient!ab;")
	public final Node last() {
		@Pc(7) Node node = this.sentinel.next;
		if (this.sentinel == node) {
			this.pointer = null;
			return null;
		} else {
			this.pointer = node.next;
			return node;
		}
	}

	@OriginalMember(owner = "client!ih", name = "a", descriptor = "(ZLclient!ab;)V")
	public void push(@OriginalArg(1) Node node) {
		if (node.next != null) {
			node.unlink();
		}
		node.prev = this.sentinel;
		node.next = this.sentinel.next;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(ILclient!ab;)V")
	public final void add(@OriginalArg(1) Node node) {
		if (node.next != null) {
			node.unlink();
		}
		node.prev = this.sentinel.prev;
		node.next = this.sentinel;
		node.next.prev = node;
		node.prev.next = node;
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "d", descriptor = "(I)Lclient!ab;")
	public final Node prev() {
		@Pc(13) Node local13 = this.pointer;
		if (this.sentinel == local13) {
			this.pointer = null;
			return null;
		} else {
			this.pointer = local13.next;
			return local13;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "a", descriptor = "(B)Lclient!ab;")
	public final Node removeHead() {
		@Pc(3) Node node = this.sentinel.prev;
		if (this.sentinel == node) {
			return null;
		} else {
			node.unlink();
			return node;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "e", descriptor = "(I)Lclient!ab;")
	public final Node next() {
		@Pc(12) Node local12 = this.pointer;
		if (local12 == this.sentinel) {
			this.pointer = null;
			return null;
		} else {
			this.pointer = local12.prev;
			return local12;
		}
	}

	@OriginalMember(owner = "runetek4.client!ih", name = "f", descriptor = "(I)Lclient!ab;")
	public final Node head() {
		@Pc(3) Node node = this.sentinel.prev;
		if (this.sentinel == node) {
			this.pointer = null;
			return null;
		} else {
			this.pointer = node.prev;
			return node;
		}
	}
}
