package com.jagex.runetek4.core.node;

import com.jagex.runetek4.core.datastruct.HardReferenceNode;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.core.datastruct.ReferenceNode;
import com.jagex.runetek4.core.factory.ReferenceNodeFactory;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!n")
public final class SoftLruHashTable {

	@OriginalMember(owner = "runetek4.client!n", name = "k", descriptor = "Lclient!ce;")
	private final SecondaryLinkedList queue = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!n", name = "m", descriptor = "I")
	private final int capacity;

	@OriginalMember(owner = "runetek4.client!n", name = "g", descriptor = "I")
	private int available;

	@OriginalMember(owner = "runetek4.client!n", name = "n", descriptor = "Lclient!sc;")
	private final HashTable hashTable;

	@OriginalMember(owner = "runetek4.client!n", name = "<init>", descriptor = "(I)V")
	public SoftLruHashTable(@OriginalArg(0) int capacity) {
		this.capacity = capacity;
		@Pc(11) int i;
		for (i = 1; i + i < capacity; i += i) {
		}
		this.available = capacity;
		this.hashTable = new HashTable(i);
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(BLjava/lang/Object;J)V")
	public final void put(@OriginalArg(1) Object arg0, @OriginalArg(2) long arg1) {
		this.remove(arg1);
		if (this.available == 0) {
			@Pc(26) ReferenceNode node = (ReferenceNode) this.queue.removeHead();
			node.unlink();
			node.unlinkCachedNode();
		} else {
			this.available--;
		}
		@Pc(44) HardReferenceNode local44 = new HardReferenceNode(arg0);
		this.hashTable.put(local44, arg1);
		this.queue.addTail(local44);
		local44.secondaryKey = 0L;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(JB)V")
	public final void remove(@OriginalArg(0) long arg0) {
		@Pc(6) ReferenceNode node = (ReferenceNode) this.hashTable.get(arg0);
		if (node != null) {
			node.unlink();
			node.unlinkCachedNode();
			this.available++;
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(I)I")
	public final int size() {
		@Pc(10) int local10 = 0;
		for (@Pc(16) ReferenceNode local16 = (ReferenceNode) this.queue.head(); local16 != null; local16 = (ReferenceNode) this.queue.next()) {
			if (!local16.isSoft()) {
				local10++;
			}
		}
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(II)V")
	public final void clean(@OriginalArg(1) int arg0) {
		if (ReferenceNodeFactory.SOFT_REFERENCE_NODE_FACTORY == null) {
			return;
		}
		for (@Pc(9) ReferenceNode cachedNode = (ReferenceNode) this.queue.head(); cachedNode != null; cachedNode = (ReferenceNode) this.queue.next()) {
			if (cachedNode.isSoft()) {
				if (cachedNode.get() == null) {
					cachedNode.unlink();
					cachedNode.unlinkCachedNode();
					this.available++;
				}
			} else if (++cachedNode.secondaryKey > (long) arg0) {
				@Pc(33) ReferenceNode local33 = ReferenceNodeFactory.SOFT_REFERENCE_NODE_FACTORY.create(cachedNode);
				this.hashTable.put(local33, cachedNode.nodeId);
				SecondaryLinkedList.insertAfter(cachedNode, local33);
				cachedNode.unlink();
				cachedNode.unlinkCachedNode();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(B)V")
	public final void removeSoft() {
		for (@Pc(7) ReferenceNode local7 = (ReferenceNode) this.queue.head(); local7 != null; local7 = (ReferenceNode) this.queue.next()) {
			if (local7.isSoft()) {
				local7.unlink();
				local7.unlinkCachedNode();
				this.available++;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(I)V")
	public final void clean() {
		this.queue.clear();
		this.hashTable.clear();
		this.available = this.capacity;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(JB)Ljava/lang/Object;")
	public final Object get(@OriginalArg(0) long arg0) {
		@Pc(12) ReferenceNode local12 = (ReferenceNode) this.hashTable.get(arg0);
		if (local12 == null) {
			return null;
		}
		@Pc(27) Object local27 = local12.get();
		if (local27 == null) {
			local12.unlink();
			local12.unlinkCachedNode();
			this.available++;
			return null;
		}
		if (local12.isSoft()) {
			@Pc(53) HardReferenceNode local53 = new HardReferenceNode(local27);
			this.hashTable.put(local53, local12.nodeId);
			this.queue.addTail(local53);
			local53.secondaryKey = 0L;
			local12.unlink();
			local12.unlinkCachedNode();
		} else {
			this.queue.addTail(local12);
			local12.secondaryKey = 0L;
		}
		return local27;
	}
}
