package com.jagex.runetek4.game.client;

import com.jagex.runetek4.*;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.node.Node;

@OriginalClass("client!qe")
public final class Inv extends Node {

	@OriginalMember(owner = "client!bj", name = "v", descriptor = "Lclient!sc;")
	public static HashTable recentUse = new HashTable(32);
	@OriginalMember(owner = "client!qe", name = "p", descriptor = "[I")
	public int[] invSlotObjId = new int[] { -1 };

	@OriginalMember(owner = "client!qe", name = "u", descriptor = "[I")
	public int[] invSlotObjCount = new int[] { 0 };

	@OriginalMember(owner = "client!wl", name = "a", descriptor = "(IIIIB)V")
	public static void update(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(12) Inv local12 = (Inv) recentUse.getNode((long) arg3);
		if (local12 == null) {
			local12 = new Inv();
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
		@Pc(8) Inv inv = (Inv) recentUse.getNode(arg0);
		if (inv == null) {
			return 0;
		} else if (slot == -1) {
			return 0;
		} else {
			@Pc(25) int total = 0;
			for (@Pc(27) int index = 0; index < inv.invSlotObjCount.length; index++) {
				if (slot == inv.invSlotObjId[index]) {
					total += inv.invSlotObjCount[index];
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
		@Pc(17) Inv inv = (Inv) recentUse.getNode((long) arg0);
		if (inv == null) {
			return Static246.get(arg0).size;
		}
		@Pc(31) int total = 0;
		for (@Pc(33) int local33 = 0; local33 < inv.invSlotObjId.length; local33++) {
			if (inv.invSlotObjId[local33] == -1) {
				total++;
			}
		}
		return total + Static246.get(arg0).size - inv.invSlotObjId.length;
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "f", descriptor = "(B)V")
	public static void clear() {
		recentUse = new HashTable(32);
	}

    @OriginalMember(owner = "runetek4.client!pf", name = "a", descriptor = "(IIZIII)Lclient!qf;")
    public static Sprite getObjectSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
        @Pc(27) int local27 = (arg2 ? 65536 : 0) + arg1 + (arg0 << 17) + (arg4 << 19);
        @Pc(37) long local37 = (long) local27 * 3849834839L + (long) arg3 * 3147483667L;
        @Pc(43) Sprite local43 = (Sprite) Static118.aClass99_16.get(local37);
        if (local43 != null) {
            return local43;
        }
        Rasterizer.textureHasTransparency = false;
        local43 = Static164.method3150(arg4, false, arg1, arg2, arg0, arg3, false);
        if (local43 != null && !Rasterizer.textureHasTransparency) {
            Static118.aClass99_16.put(local43, local37);
        }
        return local43;
    }
}