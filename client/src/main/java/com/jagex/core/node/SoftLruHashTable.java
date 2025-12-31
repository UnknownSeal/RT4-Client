package com.jagex.core.node;

import com.jagex.core.datastruct.Queue;
import com.jagex.core.datastruct.ref.HardReferenceNode;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.ref.ReferenceNode;
import com.jagex.core.datastruct.ref.ReferenceNodeFactory;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!n")
public final class SoftLruHashTable {

	@OriginalMember(owner = "runetek4.client!n", name = "k", descriptor = "Lclient!ce;")
	private final Queue queue = new Queue();

	@OriginalMember(owner = "runetek4.client!n", name = "m", descriptor = "I")
	private final int capacity;

	@OriginalMember(owner = "runetek4.client!n", name = "g", descriptor = "I")
	private int available;

	@OriginalMember(owner = "runetek4.client!n", name = "n", descriptor = "Lclient!sc;")
	private final IterableHashTable iterableHashTable;

	@OriginalMember(owner = "runetek4.client!n", name = "<init>", descriptor = "(I)V")
	public SoftLruHashTable(@OriginalArg(0) int capacity) {
		this.capacity = capacity;
		@Pc(11) int i;
		for (i = 1; i + i < capacity; i += i) {
			// TODO why is this here?
		}
		this.available = capacity;
		this.iterableHashTable = new IterableHashTable(i);
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(BLjava/lang/Object;J)V")
	public final void put(@OriginalArg(1) Object arg0, @OriginalArg(2) long arg1) {
		this.remove(arg1);
		if (this.available == 0) {
			@Pc(26) ReferenceNode node = (ReferenceNode) this.queue.removeFirst();
			node.unlink();
			node.unlink2();
		} else {
			this.available--;
		}
		@Pc(44) HardReferenceNode local44 = new HardReferenceNode(arg0);
		this.iterableHashTable.put(local44, arg1);
		this.queue.add(local44);
		local44.key2 = 0L;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(JB)V")
	public final void remove(@OriginalArg(0) long arg0) {
		@Pc(6) ReferenceNode node = (ReferenceNode) this.iterableHashTable.get(arg0);
		if (node != null) {
			node.unlink();
			node.unlink2();
			this.available++;
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(I)I")
	public final int size() {
		@Pc(10) int local10 = 0;
		for (@Pc(16) ReferenceNode local16 = (ReferenceNode) this.queue.first(); local16 != null; local16 = (ReferenceNode) this.queue.next()) {
			if (!local16.isSoft()) {
				local10++;
			}
		}
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(II)V")
	public final void clean(@OriginalArg(1) int arg0) {
		if (ReferenceNodeFactory.INSTANCE == null) {
			return;
		}
		for (@Pc(9) ReferenceNode cachedNode = (ReferenceNode) this.queue.first(); cachedNode != null; cachedNode = (ReferenceNode) this.queue.next()) {
			if (cachedNode.isSoft()) {
				if (cachedNode.get() == null) {
					cachedNode.unlink();
					cachedNode.unlink2();
					this.available++;
				}
			} else if (++cachedNode.key2 > (long) arg0) {
				@Pc(33) ReferenceNode local33 = ReferenceNodeFactory.INSTANCE.create(cachedNode);
				this.iterableHashTable.put(local33, cachedNode.key);
				Queue.add(cachedNode, local33);
				cachedNode.unlink();
				cachedNode.unlink2();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(B)V")
	public final void removeSoft() {
		for (@Pc(7) ReferenceNode local7 = (ReferenceNode) this.queue.first(); local7 != null; local7 = (ReferenceNode) this.queue.next()) {
			if (local7.isSoft()) {
				local7.unlink();
				local7.unlink2();
				this.available++;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(I)V")
	public final void clean() {
		this.queue.clear();
		this.iterableHashTable.clear();
		this.available = this.capacity;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(JB)Ljava/lang/Object;")
	public final Object get(@OriginalArg(0) long arg0) {
		@Pc(12) ReferenceNode local12 = (ReferenceNode) this.iterableHashTable.get(arg0);
		if (local12 == null) {
			return null;
		}
		@Pc(27) Object local27 = local12.get();
		if (local27 == null) {
			local12.unlink();
			local12.unlink2();
			this.available++;
			return null;
		}
		if (local12.isSoft()) {
			@Pc(53) HardReferenceNode local53 = new HardReferenceNode(local27);
			this.iterableHashTable.put(local53, local12.key);
			this.queue.add(local53);
			local53.key2 = 0L;
			local12.unlink();
			local12.unlink2();
		} else {
			this.queue.add(local12);
			local12.key2 = 0L;
		}
		return local27;
	}
}
