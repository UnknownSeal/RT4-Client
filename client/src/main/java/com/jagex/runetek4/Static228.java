package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static228 {

	@OriginalMember(owner = "client!sh", name = "a", descriptor = "Lclient!ih;")
	public static final LinkedList aClass69_120 = new LinkedList();

	@OriginalMember(owner = "client!sh", name = "e", descriptor = "Lclient!na;")
	public static final JString aClass100_967 = JString.parse("");

	@OriginalMember(owner = "client!sh", name = "h", descriptor = "I")
	public static int anInt5105 = 0;

	@OriginalMember(owner = "client!sh", name = "i", descriptor = "[[I")
	public static final int[][] anIntArrayArray35 = new int[][] { { 0, 128, 0, 0, 128, 0, 128, 128 }, { 0, 128, 0, 0, 128, 0 }, { 0, 0, 64, 128, 0, 128 }, { 128, 128, 64, 128, 128, 0 }, { 0, 0, 128, 0, 128, 128, 64, 128 }, { 0, 128, 0, 0, 128, 0, 64, 128 }, { 64, 128, 0, 128, 0, 0, 64, 0 }, { 0, 0, 64, 0, 0, 64 }, { 128, 0, 128, 128, 0, 128, 0, 64, 64, 0 }, { 0, 128, 0, 0, 32, 64, 64, 96, 128, 128 }, { 0, 0, 128, 0, 128, 128, 64, 96, 32, 64 }, { 0, 0, 128, 0, 96, 32, 32, 32 } };

	@OriginalMember(owner = "client!sh", name = "j", descriptor = "Lclient!na;")
	public static final JString HEADICONS_PRAYER = JString.parse("headicons_prayer");

	@OriginalMember(owner = "client!sh", name = "l", descriptor = "[J")
	public static final long[] aLongArray8 = new long[32];

	@OriginalMember(owner = "client!sh", name = "a", descriptor = "(II)[B")
	public static synchronized byte[] alloc(@OriginalArg(1) int arg0) {
		@Pc(22) byte[] local22;
		if (arg0 == 100 && Packet.allocatedMinCount > 0) {
			local22 = Packet.allocatedMin[--Packet.allocatedMinCount];
			Packet.allocatedMin[Packet.allocatedMinCount] = null;
			return local22;
		} else if (arg0 == 5000 && Packet.allocatedMidCount > 0) {
			local22 = Packet.allocatedMid[--Packet.allocatedMidCount];
			Packet.allocatedMid[Packet.allocatedMidCount] = null;
			return local22;
		} else if (arg0 == 30000 && Packet.allocatedMaxCount > 0) {
			local22 = Packet.allocatedMax[--Packet.allocatedMaxCount];
			Packet.allocatedMax[Packet.allocatedMaxCount] = null;
			return local22;
		} else {
			return new byte[arg0];
		}
	}

	@OriginalMember(owner = "client!sh", name = "a", descriptor = "(IZBIZ)V")
	public static void method3908(@OriginalArg(0) int arg0, @OriginalArg(1) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3) {
		Static79.method1697(arg0, arg2, Static101.aClass10_Sub1Array1.length - 1, arg3, 0, arg1);
	}

}
