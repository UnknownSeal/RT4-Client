package com.jagex.runetek4;

import com.jagex.runetek4.game.client.Inv;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static14 {

	@OriginalMember(owner = "client!bd", name = "a", descriptor = "(BI)V")
	public static void method475(@OriginalArg(1) int arg0) {
		@Pc(8) Inv local8 = (Inv) Inv.objectContainerCache.get(arg0);
		if (local8 != null) {
			for (@Pc(24) int local24 = 0; local24 < local8.invSlotObjId.length; local24++) {
				local8.invSlotObjId[local24] = -1;
				local8.invSlotObjCount[local24] = 0;
			}
		}
	}
}