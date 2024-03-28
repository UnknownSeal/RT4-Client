package com.jagex.runetek4.game.client.logic;

import com.jagex.runetek4.*;
import com.jagex.runetek4.core.datastruct.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!da")
public final class DelayedStateChange extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!da", name = "T", descriptor = "I")
	public int intArg2;

	@OriginalMember(owner = "runetek4.client!da", name = "U", descriptor = "I")
	public int intArg3;

	@OriginalMember(owner = "runetek4.client!da", name = "V", descriptor = "I")
	public int intArg1;

	@OriginalMember(owner = "runetek4.client!da", name = "W", descriptor = "Lclient!na;")
	public JagString stringArg;

	@OriginalMember(owner = "runetek4.client!da", name = "<init>", descriptor = "(II)V")
	public DelayedStateChange(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		this.nodeId = (long) arg0 << 32 | (long) arg1;
	}

	@OriginalMember(owner = "runetek4.client!te", name = "a", descriptor = "(III)Lclient!da;")
	public static DelayedStateChange method4143(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(13) DelayedStateChange local13 = (DelayedStateChange) Static36.aClass133_3.getNode((long) arg1 | (long) arg0 << 32);
		if (local13 == null) {
			local13 = new DelayedStateChange(arg0, arg1);
			Static36.aClass133_3.pushNode(local13, local13.nodeId);
		}
		return local13;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(B)Lclient!da;")
	public static DelayedStateChange poll() {
		@Pc(10) DelayedStateChange local10 = (DelayedStateChange) Static215.aClass16_9.peekFront();
		if (local10 != null) {
			local10.remove();
			local10.secondaryRemove();
			return local10;
		}
		do {
			local10 = (DelayedStateChange) Static140.aClass16_7.peekFront();
			if (local10 == null) {
				return null;
			}
			if (local10.method1009() > MonotonicTime.get()) {
				return null;
			}
			local10.remove();
			local10.secondaryRemove();
		} while ((Long.MIN_VALUE & local10.secondaryNodeId) == 0L);
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "a", descriptor = "(Z)V")
	public final void method1007() {
		this.secondaryNodeId = MonotonicTime.get() + 500L | Long.MIN_VALUE & this.secondaryNodeId;
		Static140.aClass16_7.method798(this);
	}

	@OriginalMember(owner = "runetek4.client!da", name = "b", descriptor = "(Z)J")
	public final long method1009() {
		return this.secondaryNodeId & Long.MAX_VALUE;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "e", descriptor = "(I)I")
	public final int method1011() {
		return (int) (this.nodeId >>> 32 & 0xFFL);
	}

	@OriginalMember(owner = "runetek4.client!da", name = "f", descriptor = "(B)I")
	public final int method1012() {
		return (int) this.nodeId;
	}

	@OriginalMember(owner = "runetek4.client!da", name = "g", descriptor = "(B)V")
	public final void method1017() {
		this.secondaryNodeId |= Long.MIN_VALUE;
		if (this.method1009() == 0L) {
			Static215.aClass16_9.method798(this);
		}
	}
}
