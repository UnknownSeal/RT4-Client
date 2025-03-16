package com.jagex.runetek4.game.client;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.Static246;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.Node;

@OriginalClass("client!qe")
public final class ClientInvCache extends Node {

	@OriginalMember(owner = "client!bj", name = "v", descriptor = "Lclient!sc;")
	public static HashTable recentUse = new HashTable(32);
	@OriginalMember(owner = "client!qe", name = "p", descriptor = "[I")
	public int[] invSlotObjId = new int[] { -1 };

	@OriginalMember(owner = "client!qe", name = "u", descriptor = "[I")
	public int[] invSlotObjCount = new int[] { 0 };

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IIIIB)V")
	public static void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(12) ClientInvCache local12 = (ClientInvCache) recentUse.getNode((long) arg3);
		if (local12 == null) {
			local12 = new ClientInvCache();
			recentUse.put(local12, (long) arg3);
		}
		if (local12.invSlotObjId.length <= arg1) {
			@Pc(39) int[] local39 = new int[arg1 + 1];
			@Pc(44) int[] local44 = new int[arg1 + 1];
			for (int index = 0; index < local12.invSlotObjId.length; index++) {
				local39[index] = local12.invSlotObjId[index];
				local44[index] = local12.invSlotObjCount[index];
			}
			for (int index = local12.invSlotObjId.length; index < arg1; index++) {
				local39[index] = -1;
				local44[index] = 0;
			}
			local12.invSlotObjId = local39;
			local12.invSlotObjCount = local44;
		}
		local12.invSlotObjId[arg1] = arg0;
		local12.invSlotObjCount[arg1] = arg2;
	}

	@OriginalMember(owner = "client!wj", name = "a", descriptor = "(BII)I")
	public static int total(@OriginalArg(1) int arg0, @OriginalArg(2) int slot) {
		@Pc(8) ClientInvCache clientInvCache = (ClientInvCache) recentUse.getNode(arg0);
		if (clientInvCache == null) {
			return 0;
		} else if (slot == -1) {
			return 0;
		} else {
			@Pc(25) int total = 0;
			for (@Pc(27) int index = 0; index < clientInvCache.invSlotObjCount.length; index++) {
				if (slot == clientInvCache.invSlotObjId[index]) {
					total += clientInvCache.invSlotObjCount[index];
				}
			}
			return total;
		}
	}

	@OriginalMember(owner = "client!ba", name = "a", descriptor = "(IB)I")
	public static int method446(@OriginalArg(0) int arg0) {
		if (arg0 < 0) {
			return 0;
		}
		@Pc(17) ClientInvCache clientInvCache = (ClientInvCache) recentUse.getNode((long) arg0);
		if (clientInvCache == null) {
			return Static246.get(arg0).size;
		}
		@Pc(31) int total = 0;
		for (@Pc(33) int local33 = 0; local33 < clientInvCache.invSlotObjId.length; local33++) {
			if (clientInvCache.invSlotObjId[local33] == -1) {
				total++;
			}
		}
		return total + Static246.get(arg0).size - clientInvCache.invSlotObjId.length;
	}
}