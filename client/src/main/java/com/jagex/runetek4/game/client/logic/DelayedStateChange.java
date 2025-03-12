package com.jagex.runetek4.game.client.logic;

import com.jagex.runetek4.JString;
import com.jagex.runetek4.MonotonicTime;
import com.jagex.runetek4.Static140;
import com.jagex.runetek4.Static215;
import com.jagex.runetek4.core.datastruct.CachedNode;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!da")
public final class DelayedStateChange extends CachedNode {

	@OriginalMember(owner = "client!da", name = "T", descriptor = "I")
	public int intArg2;

	@OriginalMember(owner = "client!da", name = "U", descriptor = "I")
	public int intArg3;

	@OriginalMember(owner = "client!da", name = "V", descriptor = "I")
	public int intArg1;

	@OriginalMember(owner = "client!da", name = "W", descriptor = "Lclient!na;")
	public JString stringArg;

	@OriginalMember(owner = "client!da", name = "<init>", descriptor = "(II)V")
	public DelayedStateChange(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.nodeId = (long) arg0 << 32 | (long) arg1;
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Z)V")
	public final void method1007() {
		this.secondaryNodeId = MonotonicTime.get() + 500L | Long.MIN_VALUE & this.secondaryNodeId;
		Static140.aClass16_7.pushBack(this);
	}

	@OriginalMember(owner = "client!da", name = "b", descriptor = "(Z)J")
	public final long method1009() {
		return this.secondaryNodeId & Long.MAX_VALUE;
	}

	@OriginalMember(owner = "client!da", name = "e", descriptor = "(I)I")
	public final int method1011() {
		return (int) (this.nodeId >>> 32 & 0xFFL);
	}

	@OriginalMember(owner = "client!da", name = "f", descriptor = "(B)I")
	public final int method1012() {
		return (int) this.nodeId;
	}

	@OriginalMember(owner = "client!da", name = "g", descriptor = "(B)V")
	public final void method1017() {
		this.secondaryNodeId |= Long.MIN_VALUE;
		if (this.method1009() == 0L) {
			Static215.aClass16_9.pushBack(this);
		}
	}
}
