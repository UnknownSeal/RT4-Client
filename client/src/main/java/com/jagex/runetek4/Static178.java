package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IntWrapper;
import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.cache.def.ObjType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static178 {

	@OriginalMember(owner = "runetek4.client!od", name = "c", descriptor = "Z")
	public static boolean highDetailLighting = true;

	@OriginalMember(owner = "runetek4.client!od", name = "g", descriptor = "S")
	public static short aShort25 = 256;

	@OriginalMember(owner = "runetek4.client!od", name = "a", descriptor = "(IZII)I")
	public static int method3319(@OriginalArg(1) boolean arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(19) Inv local19 = (Inv) Inv.recentUse.getNode((long) arg1);
		if (local19 == null) {
			return 0;
		}
		@Pc(27) int local27 = 0;
		for (@Pc(29) int local29 = 0; local29 < local19.invSlotObjId.length; local29++) {
			if (local19.invSlotObjId[local29] >= 0 && ObjTypeList.capacity > local19.invSlotObjId[local29]) {
				@Pc(56) ObjType local56 = ObjTypeList.get(local19.invSlotObjId[local29]);
				if (local56.params != null) {
					@Pc(68) IntWrapper local68 = (IntWrapper) local56.params.getNode((long) arg2);
					if (local68 != null) {
						if (arg0) {
							local27 += local19.invSlotObjCount[local29] * local68.value;
						} else {
							local27 += local68.value;
						}
					}
				}
			}
		}
		return local27;
	}
}
