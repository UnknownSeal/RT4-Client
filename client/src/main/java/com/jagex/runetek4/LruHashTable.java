package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.Node;
import com.jagex.runetek4.node.CachedNode;
import com.jagex.runetek4.node.NodeQueue;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!gn")
public final class LruHashTable {

	@OriginalMember(owner = "runetek4.client!gn", name = "l", descriptor = "Lclient!rg;")
	private CachedNode aClass3_Sub2_37 = new CachedNode();

	@OriginalMember(owner = "runetek4.client!gn", name = "s", descriptor = "Lclient!ce;")
	private final NodeQueue aClass16_1 = new NodeQueue();

	@OriginalMember(owner = "runetek4.client!gn", name = "u", descriptor = "I")
	private int anInt2314;

	@OriginalMember(owner = "runetek4.client!gn", name = "r", descriptor = "I")
	private final int anInt2313;

	@OriginalMember(owner = "runetek4.client!gn", name = "q", descriptor = "Lclient!sc;")
	private final HashTable aClass133_5;

	@OriginalMember(owner = "runetek4.client!gn", name = "<init>", descriptor = "(I)V")
	public LruHashTable(@OriginalArg(0) int arg0) {
		@Pc(13) int local13 = 1;
		this.anInt2314 = arg0;
		while (arg0 > local13 + local13) {
			local13 += local13;
		}
		this.anInt2313 = arg0;
		this.aClass133_5 = new HashTable(local13);
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(JI)Lclient!rg;")
	public final CachedNode get(@OriginalArg(0) long arg0) {
		@Pc(16) CachedNode node = (CachedNode) this.aClass133_5.getNode(arg0);
		if (node != null) {
			this.aClass16_1.pushBack(node);
		}
		return node;
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(I)Lclient!ab;")
	public final Node method1808() {
		return this.aClass133_5.peekFront();
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "a", descriptor = "(Lclient!rg;JB)V")
	public final void put(@OriginalArg(0) CachedNode arg0, @OriginalArg(1) long arg1) {
		if (this.anInt2314 == 0) {
			@Pc(14) CachedNode local14 = this.aClass16_1.pollFront();
			local14.unlink();
			local14.clear();
			if (this.aClass3_Sub2_37 == local14) {
				local14 = this.aClass16_1.pollFront();
				local14.unlink();
				local14.clear();
			}
		} else {
			this.anInt2314--;
		}
		this.aClass133_5.pushNode(arg0, arg1);
		this.aClass16_1.pushBack(arg0);
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "b", descriptor = "(I)Lclient!ab;")
	public final Node method1813() {
		return this.aClass133_5.prev();
	}

	@OriginalMember(owner = "runetek4.client!gn", name = "c", descriptor = "(I)V")
	public final void method1815() {
		this.aClass16_1.method802();
		this.aClass133_5.removeAll();
		this.aClass3_Sub2_37 = new CachedNode();
		this.anInt2314 = this.anInt2313;
	}
}
