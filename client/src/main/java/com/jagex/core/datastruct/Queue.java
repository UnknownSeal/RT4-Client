package com.jagex.core.datastruct;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!ce")
public final class Queue {

	@OriginalMember(owner = "client!ce", name = "n", descriptor = "Lclient!rg;")
	private Node2 pointer;

	@OriginalMember(owner = "client!ce", name = "l", descriptor = "Lclient!rg;")
	private final Node2 sentinel = new Node2();

	@OriginalMember(owner = "client!ce", name = "<init>", descriptor = "()V")
	public Queue() {
		this.sentinel.next2 = this.sentinel;
		this.sentinel.prev2 = this.sentinel;
	}

	@OriginalMember(owner = "client!gk", name = "a", descriptor = "(Lclient!rg;Lclient!rg;B)V")
	public static void add(@OriginalArg(0) Node2 node2, @OriginalArg(1) Node2 node) {
		if (node.prev2 != null) {
			node.unlink2();
		}
		node.prev2 = node2;
		node.next2 = node2.next2;
		node.prev2.next2 = node;
		node.next2.prev2 = node;
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(I)I")
	public int size() {
		@Pc(3) int size = 0;
		@Pc(7) Node2 node2 = this.sentinel.next2;
		while (node2 != this.sentinel) {
			node2 = node2.next2;
			size++;
		}
		return size;
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(B)Lclient!rg;")
	public Node2 first() {
		@Pc(3) Node2 node = this.sentinel.next2;
		if (this.sentinel == node) {
			this.pointer = null;
			return null;
		} else {
			this.pointer = node.next2;
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "b", descriptor = "(I)Lclient!rg;")
	public Node2 removeFirst() {
		@Pc(7) Node2 node = this.sentinel.next2;
		if (node == this.sentinel) {
			return null;
		} else {
			node.unlink2();
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "c", descriptor = "(I)Lclient!rg;")
	public Node2 next() {
		@Pc(2) Node2 node = this.pointer;
		if (node == this.sentinel) {
			this.pointer = null;
			return null;
		} else {
			this.pointer = node.next2;
			return node;
		}
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(Lclient!rg;B)V")
	public void add(@OriginalArg(0) Node2 arg0) {
		if (arg0.prev2 != null) {
			arg0.unlink2();
		}
		arg0.prev2 = this.sentinel.prev2;
		arg0.next2 = this.sentinel;
		arg0.prev2.next2 = arg0;
		arg0.next2.prev2 = arg0;
	}

	@OriginalMember(owner = "client!ce", name = "d", descriptor = "(I)V")
	public void clear() {
		while (true) {
			@Pc(15) Node2 node2 = this.sentinel.next2;
			if (this.sentinel == node2) {
				this.pointer = null;
				return;
			}
			node2.unlink2();
		}
	}
}
