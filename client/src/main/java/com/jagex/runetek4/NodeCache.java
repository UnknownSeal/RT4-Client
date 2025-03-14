package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IterableMap;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!n")
public final class NodeCache {

	@OriginalMember(owner = "runetek4.client!n", name = "k", descriptor = "Lclient!ce;")
	private final SecondaryLinkedList aClass16_8 = new SecondaryLinkedList();

	@OriginalMember(owner = "runetek4.client!n", name = "m", descriptor = "I")
	private final int anInt3970;

	@OriginalMember(owner = "runetek4.client!n", name = "g", descriptor = "I")
	private int anInt3966;

	@OriginalMember(owner = "runetek4.client!n", name = "n", descriptor = "Lclient!sc;")
	private final IterableMap aClass133_18;

	@OriginalMember(owner = "runetek4.client!n", name = "<init>", descriptor = "(I)V")
	public NodeCache(@OriginalArg(0) int arg0) {
		this.anInt3970 = arg0;
		@Pc(11) int local11;
		for (local11 = 1; local11 + local11 < arg0; local11 += local11) {
		}
		this.anInt3966 = arg0;
		this.aClass133_18 = new IterableMap(local11);
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(BLjava/lang/Object;J)V")
	public final void put(@OriginalArg(1) Object arg0, @OriginalArg(2) long arg1) {
		this.method3098(arg1);
		if (this.anInt3966 == 0) {
			@Pc(26) ReferenceNode local26 = (ReferenceNode) this.aClass16_8.pollFront();
			local26.unlink();
			local26.clear();
		} else {
			this.anInt3966--;
		}
		@Pc(44) HardReferenceNode local44 = new HardReferenceNode(arg0);
		this.aClass133_18.pushNode(local44, arg1);
		this.aClass16_8.pushBack(local44);
		local44.secondaryNodeId = 0L;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(JB)V")
	public final void method3098(@OriginalArg(0) long arg0) {
		@Pc(6) ReferenceNode local6 = (ReferenceNode) this.aClass133_18.getNode(arg0);
		if (local6 != null) {
			local6.unlink();
			local6.clear();
			this.anInt3966++;
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(I)I")
	public final int method3100() {
		@Pc(10) int local10 = 0;
		for (@Pc(16) ReferenceNode local16 = (ReferenceNode) this.aClass16_8.head(); local16 != null; local16 = (ReferenceNode) this.aClass16_8.prev()) {
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
		for (@Pc(9) ReferenceNode cachedNode = (ReferenceNode) this.aClass16_8.head(); cachedNode != null; cachedNode = (ReferenceNode) this.aClass16_8.prev()) {
			if (cachedNode.method3619()) {
				if (cachedNode.method3618() == null) {
					cachedNode.unlink();
					cachedNode.clear();
					this.anInt3966++;
				}
			} else if (++cachedNode.secondaryNodeId > (long) arg0) {
				@Pc(33) ReferenceNode local33 = Static252.aClass22_1.method1027(cachedNode);
				this.aClass133_18.pushNode(local33, cachedNode.nodeId);
				Static84.method1772(cachedNode, local33);
				cachedNode.unlink();
				cachedNode.clear();
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(B)V")
	public final void method3103() {
		for (@Pc(7) ReferenceNode local7 = (ReferenceNode) this.aClass16_8.head(); local7 != null; local7 = (ReferenceNode) this.aClass16_8.prev()) {
			if (local7.method3619()) {
				local7.unlink();
				local7.clear();
				this.anInt3966++;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!n", name = "c", descriptor = "(I)V")
	public final void method3104() {
		this.aClass16_8.method802();
		this.aClass133_18.removeAll();
		this.anInt3966 = this.anInt3970;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "b", descriptor = "(JB)Ljava/lang/Object;")
	public final Object get(@OriginalArg(0) long arg0) {
		@Pc(12) ReferenceNode local12 = (ReferenceNode) this.aClass133_18.getNode(arg0);
		if (local12 == null) {
			return null;
		}
		@Pc(27) Object local27 = local12.method3618();
		if (local27 == null) {
			local12.unlink();
			local12.clear();
			this.anInt3966++;
			return null;
		}
		if (local12.method3619()) {
			@Pc(53) HardReferenceNode local53 = new HardReferenceNode(local27);
			this.aClass133_18.pushNode(local53, local12.nodeId);
			this.aClass16_8.pushBack(local53);
			local53.secondaryNodeId = 0L;
			local12.unlink();
			local12.clear();
		} else {
			this.aClass16_8.pushBack(local12);
			local12.secondaryNodeId = 0L;
		}
		return local27;
	}
}
