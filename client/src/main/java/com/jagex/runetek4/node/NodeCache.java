package com.jagex.runetek4.node;

import com.jagex.runetek4.HardReferenceNode;
import com.jagex.runetek4.ReferenceNode;
import com.jagex.runetek4.Static252;
import com.jagex.runetek4.Static84;
import com.jagex.runetek4.core.datastruct.HashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!n")
public final class NodeCache {

	@OriginalMember(owner = "runetek4.client!n", name = "k", descriptor = "Lclient!ce;")
	private final NodeQueue nodeQueue = new NodeQueue();

	@OriginalMember(owner = "runetek4.client!n", name = "m", descriptor = "I")
	private final int size;

	@OriginalMember(owner = "runetek4.client!n", name = "g", descriptor = "I")
	private int remaining;

	@OriginalMember(owner = "runetek4.client!n", name = "n", descriptor = "Lclient!sc;")
	private final HashTable hashTable;

	@OriginalMember(owner = "runetek4.client!n", name = "<init>", descriptor = "(I)V")
	public NodeCache(@OriginalArg(0) int size) {
		this.size = size;
		@Pc(11) int i;
		for (i = 1; i + i < size; i += i) {
		}
		this.remaining = size;
		this.hashTable = new HashTable(i);
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(BLjava/lang/Object;J)V")
	public final void put(@OriginalArg(1) Object arg0, @OriginalArg(2) long arg1) {
		this.remove(arg1);
		if (this.remaining == 0) {
			@Pc(26) ReferenceNode node = (ReferenceNode) this.nodeQueue.pollFront();
			node.unlink();
			node.clear();
		} else {
			this.remaining--;
		}
		@Pc(44) HardReferenceNode local44 = new HardReferenceNode(arg0);
		this.hashTable.put(local44, arg1);
		this.nodeQueue.pushBack(local44);
		local44.secondaryNodeId = 0L;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(JB)V")
	public final void remove(@OriginalArg(0) long arg0) {
		@Pc(6) ReferenceNode node = (ReferenceNode) this.hashTable.getNode(arg0);
		if (node != null) {
			node.unlink();
			node.clear();
			this.remaining++;
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(I)I")
	public final int method3100() {
		@Pc(10) int local10 = 0;
		for (@Pc(16) ReferenceNode local16 = (ReferenceNode) this.nodeQueue.head(); local16 != null; local16 = (ReferenceNode) this.nodeQueue.prev()) {
			if (!local16.method3619()) {
				local10++;
			}
		}
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(II)V")
	public final void clear(@OriginalArg(1) int arg0) {
		if (Static252.aClass22_1 == null) {
			return;
		}
		for (@Pc(9) ReferenceNode cachedNode = (ReferenceNode) this.nodeQueue.head(); cachedNode != null; cachedNode = (ReferenceNode) this.nodeQueue.prev()) {
			if (cachedNode.method3619()) {
				if (cachedNode.method3618() == null) {
					cachedNode.unlink();
					cachedNode.clear();
					this.remaining++;
				}
			} else if (++cachedNode.secondaryNodeId > (long) arg0) {
				@Pc(33) ReferenceNode local33 = Static252.aClass22_1.method1027(cachedNode);
				this.hashTable.put(local33, cachedNode.nodeId);
				Static84.method1772(cachedNode, local33);
				cachedNode.unlink();
				cachedNode.clear();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(B)V")
	public final void method3103() {
		for (@Pc(7) ReferenceNode local7 = (ReferenceNode) this.nodeQueue.head(); local7 != null; local7 = (ReferenceNode) this.nodeQueue.prev()) {
			if (local7.method3619()) {
				local7.unlink();
				local7.clear();
				this.remaining++;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(I)V")
	public final void method3104() {
		this.nodeQueue.method802();
		this.hashTable.removeAll();
		this.remaining = this.size;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(JB)Ljava/lang/Object;")
	public final Object get(@OriginalArg(0) long arg0) {
		@Pc(12) ReferenceNode local12 = (ReferenceNode) this.hashTable.getNode(arg0);
		if (local12 == null) {
			return null;
		}
		@Pc(27) Object local27 = local12.method3618();
		if (local27 == null) {
			local12.unlink();
			local12.clear();
			this.remaining++;
			return null;
		}
		if (local12.method3619()) {
			@Pc(53) HardReferenceNode local53 = new HardReferenceNode(local27);
			this.hashTable.put(local53, local12.nodeId);
			this.nodeQueue.pushBack(local53);
			local53.secondaryNodeId = 0L;
			local12.unlink();
			local12.clear();
		} else {
			this.nodeQueue.pushBack(local12);
			local12.secondaryNodeId = 0L;
		}
		return local27;
	}
}
