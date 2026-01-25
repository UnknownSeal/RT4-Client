package com.jagex.core.datastruct.key;

import com.jagex.core.datastruct.Node;
import com.jagex.core.datastruct.Node2;
import com.jagex.core.datastruct.Queue;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!gn")
public final class LruCache {

	@OriginalMember(owner = "client!gn", name = "l", descriptor = "Lclient!rg;")
	private Node2 pointer = new Node2();

	@OriginalMember(owner = "client!gn", name = "s", descriptor = "Lclient!ce;")
	private final Queue history = new Queue();

	@OriginalMember(owner = "client!gn", name = "u", descriptor = "I")
	private int remaining;

	@OriginalMember(owner = "client!gn", name = "r", descriptor = "I")
	private final int capacity;

	@OriginalMember(owner = "client!gn", name = "q", descriptor = "Lclient!sc;")
	private final IterableHashTable table;

	@OriginalMember(owner = "client!gn", name = "<init>", descriptor = "(I)V")
	public LruCache(@OriginalArg(0) int capacity) {
		@Pc(13) int bucketCount = 1;
		this.remaining = capacity;
		while (capacity > bucketCount + bucketCount) {
			bucketCount += bucketCount;
		}
		this.capacity = capacity;
		this.table = new IterableHashTable(bucketCount);
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(JI)Lclient!rg;")
	public final Node2 get(@OriginalArg(0) long key) {
		@Pc(16) Node2 node = (Node2) this.table.get(key);
		if (node != null) {
			this.history.add(node);
		}
		return node;
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(I)Lclient!ab;")
	public final Node method1808() {
		return this.table.head();
	}

	@OriginalMember(owner = "client!gn", name = "a", descriptor = "(Lclient!rg;JB)V")
	public final void put(@OriginalArg(0) Node2 node, @OriginalArg(1) long key) {
		if (this.remaining == 0) {
			@Pc(14) Node2 first = this.history.removeFirst();
			first.unlink();
			first.unlink2();
			if (this.pointer == first) {
				first = this.history.removeFirst();
				first.unlink();
				first.unlink2();
			}
		} else {
			this.remaining--;
		}
		this.table.put(node, key);
		this.history.add(node);
	}

	@OriginalMember(owner = "client!gn", name = "b", descriptor = "(I)Lclient!ab;")
	public final Node method1813() {
		return this.table.next();
	}

	@OriginalMember(owner = "client!gn", name = "c", descriptor = "(I)V")
	public final void clear() {
		this.history.clear();
		this.table.clear();
		this.pointer = new Node2();
		this.remaining = this.capacity;
	}
}
