package com.jagex.runetek4.game.client.logic;

import com.jagex.runetek4.JString;
import com.jagex.runetek4.MonotonicTime;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.CachedNode;

import com.jagex.runetek4.node.NodeQueue;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!da")
public final class DelayedStateChange extends CachedNode {

	@OriginalMember(owner = "client!runetek4.client", name = "U", descriptor = "Lclient!sc;")
	public static final HashTable changes = new HashTable(16);

	@OriginalMember(owner = "runetek4.client!la", name = "f", descriptor = "Lclient!ce;")
	public static final NodeQueue clientQueue = new NodeQueue();

	@OriginalMember(owner = "runetek4.client!rh", name = "e", descriptor = "Lclient!ce;")
	public static final NodeQueue serverQueue = new NodeQueue();

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

	@OriginalMember(owner = "client!bj", name = "d", descriptor = "(B)V")
	public static void clear() {
		changes.clear();
		clientQueue.clear();
		serverQueue.clear();
	}

	@OriginalMember(owner = "runetek4.client!te", name = "a", descriptor = "(III)Lclient!da;")
	public static DelayedStateChange create(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(13) DelayedStateChange local13 = (DelayedStateChange) changes.getNode((long) arg1 | (long) arg0 << 32);
		if (local13 == null) {
			local13 = new DelayedStateChange(arg0, arg1);
			changes.put(local13, local13.nodeId);
		}
		return local13;
	}

	@OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(III)V")
	public static void setComponentModelRotationSpeedServer(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(14) DelayedStateChange local14 = create(13, arg1);
		local14.pushServer();
		local14.intArg1 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!pi", name = "a", descriptor = "(Lclient!na;BI)V")
	public static void method3498(@OriginalArg(0) JString arg0, @OriginalArg(2) int arg1) {
		@Pc(10) DelayedStateChange local10 = create(2, arg1);
		local10.pushServer();
		local10.stringArg = arg0;
	}

	@OriginalMember(owner = "runetek4.client!mc", name = "c", descriptor = "(III)V")
	public static void method2905(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(14) DelayedStateChange local14 = create(7, arg0);
		local14.pushServer();
		local14.intArg1 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "c", descriptor = "(III)V")
	public static void updateVarC(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(8) DelayedStateChange local8 = create(1, arg0);
		local8.pushServer();
		local8.intArg1 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!ke", name = "a", descriptor = "(IIIBI)V")
	public static void updateComponentModel(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) DelayedStateChange local8 = create(4, arg2);
		local8.pushServer();
		local8.intArg3 = arg3;
		local8.intArg2 = arg0;
		local8.intArg1 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!se", name = "a", descriptor = "(III)V")
	public static void method3893(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(14) DelayedStateChange local14 = create(5, arg0);
		local14.pushServer();
		local14.intArg1 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!ve", name = "a", descriptor = "(BIIII)V")
	public static void updateView(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		@Pc(8) DelayedStateChange local8 = create(8, arg1);
		local8.pushServer();
		local8.intArg2 = arg0;
		local8.intArg1 = arg3;
		local8.intArg3 = arg2;
	}

	@OriginalMember(owner = "runetek4.client!kk", name = "a", descriptor = "(IIB)V")
	public static void setColor(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(4) DelayedStateChange local4 = create(6, arg1);
		local4.pushServer();
		local4.intArg1 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(IIII)V")
	public static void method4666(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2) {
		@Pc(18) DelayedStateChange local18 = create(11, arg1);
		local18.pushServer();
		local18.intArg3 = arg2;
		local18.intArg1 = arg0;
	}

	@OriginalMember(owner = "client!gf", name = "a", descriptor = "(Lclient!na;II)V")
	public static void method3617(@OriginalArg(0) JString arg0, @OriginalArg(2) int arg1) {
		@Pc(6) DelayedStateChange local6 = create(3, arg1);
		local6.pushServer();
		local6.stringArg = arg0;
	}

	@OriginalMember(owner = "runetek4.client!ug", name = "a", descriptor = "(IB)V")
	public static void setComponentObjClient(@OriginalArg(0) int arg0) {
		@Pc(14) DelayedStateChange local14 = create(9, arg0);
		local14.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!mc", name = "a", descriptor = "(BI)V")
	public static void setComponentModelAngleClient(@OriginalArg(1) int arg0) {
		@Pc(4) DelayedStateChange local4 = create(8, arg0);
		local4.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!og", name = "a", descriptor = "(II)V")
	public static void setComponentModelOffsetClient(@OriginalArg(0) int arg0) {
		@Pc(12) DelayedStateChange local12 = create(10, arg0);
		local12.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!wc", name = "a", descriptor = "(ZI)V")
	public static void method4600(@OriginalArg(1) int arg0) {
		@Pc(8) DelayedStateChange local8 = create(4, arg0);
		local8.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!jd", name = "a", descriptor = "(IB)V")
	public static void method2353(@OriginalArg(0) int arg0) {
		@Pc(12) DelayedStateChange local12 = create(12, arg0);
		local12.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(B)Lclient!da;")
	public static DelayedStateChange poll() {
		@Pc(10) DelayedStateChange local10 = (DelayedStateChange) serverQueue.head();
		if (local10 != null) {
			local10.unlink();
			local10.unlinkCachedNode();
			return local10;
		}
		do {
			local10 = (DelayedStateChange) clientQueue.head();
			if (local10 == null) {
				return null;
			}
			if (local10.getTime() > MonotonicTime.currentTimeMillis()) {
				return null;
			}
			local10.unlink();
			local10.unlinkCachedNode();
		} while ((Long.MIN_VALUE & local10.secondaryNodeId) == 0L);
		return local10;
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(IIIIB)V")
	public static void method2745(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(8) DelayedStateChange local8 = create(10, arg0);
		local8.pushServer();
		local8.intArg3 = arg2;
		local8.intArg1 = arg3;
		local8.intArg2 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!n", name = "a", descriptor = "(II)V")
	public static void method3096(@OriginalArg(0) int arg0) {
		@Pc(8) DelayedStateChange local8 = create(3, arg0);
		local8.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(II)V")
	public static void method3345(@OriginalArg(0) int arg0) {
		@Pc(8) DelayedStateChange local8 = create(5, arg0);
		local8.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!ra", name = "a", descriptor = "(BIII)V")
	public static void method3707(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(12) DelayedStateChange local12 = create(9, arg0);
		local12.pushServer();
		local12.intArg1 = arg2;
		local12.intArg3 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!si", name = "b", descriptor = "(IIB)V")
	public static void method3938(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(16) DelayedStateChange local16 = create(12, arg1);
		local16.pushServer();
		local16.intArg1 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!tm", name = "a", descriptor = "(II)V")
	public static void method4224(@OriginalArg(0) int arg0) {
		@Pc(16) DelayedStateChange local16 = create(6, arg0);
		local16.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(II)V")
	public static void method4675(@OriginalArg(1) int arg0) {
		@Pc(17) DelayedStateChange local17 = create(11, arg0);
		local17.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!ac", name = "a", descriptor = "(BI)V")
	public static void method24(@OriginalArg(1) int arg0) {
		@Pc(16) DelayedStateChange local16 = create(1, arg0);
		local16.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!ha", name = "b", descriptor = "(II)V")
	public static void method1840(@OriginalArg(1) int arg0) {
		@Pc(8) DelayedStateChange local8 = create(2, arg0);
		local8.pushClient();
	}

	@OriginalMember(owner = "runetek4.client!he", name = "c", descriptor = "(II)V")
	public static void method1906(@OriginalArg(1) int arg0) {
		@Pc(12) DelayedStateChange local12 = create(7, arg0);
		local12.pushClient();
	}

	@OriginalMember(owner = "client!da", name = "a", descriptor = "(Z)V")
	public final void pushClient() {
		this.secondaryNodeId = MonotonicTime.currentTimeMillis() + 500L | Long.MIN_VALUE & this.secondaryNodeId;
		clientQueue.addTail(this);
	}

	@OriginalMember(owner = "client!da", name = "b", descriptor = "(Z)J")
	public final long getTime() {
		return this.secondaryNodeId & Long.MAX_VALUE;
	}

	@OriginalMember(owner = "client!da", name = "e", descriptor = "(I)I")
	public final int getType() {
		return (int) (this.nodeId >>> 32 & 0xFFL);
	}

	@OriginalMember(owner = "client!da", name = "f", descriptor = "(B)I")
	public final int getId() {
		return (int) this.nodeId;
	}

	@OriginalMember(owner = "client!da", name = "g", descriptor = "(B)V")
	public final void pushServer() {
		this.secondaryNodeId |= Long.MIN_VALUE;
		if (this.getTime() == 0L) {
			serverQueue.addTail(this);
		}
	}
}
