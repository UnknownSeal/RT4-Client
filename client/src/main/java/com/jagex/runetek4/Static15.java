package com.jagex.runetek4;

import com.jagex.runetek4.game.client.Inv;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static15 {

	@OriginalMember(owner = "client!be", name = "ib", descriptor = "Lclient!na;")
	public static final JString aClass100_83 = JString.parse("event_opbase");

	@OriginalMember(owner = "client!be", name = "Kb", descriptor = "Z")
	public static boolean lowMemory = true;

	@OriginalMember(owner = "client!be", name = "kc", descriptor = "J")
	public static long aLong18 = 0L;

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(Z)Lclient!na;")
	public static JString method479() {
		@Pc(8) JString local8 = Static93.aClass100_518;
		@Pc(10) JString local10 = JString.EMPTY;
		if (client.modeWhere != 0) {
			local8 = Static50.aClass100_365;
		}
		if (client.settings != null) {
			local10 = JString.concatenate(new JString[] { Static150.aClass100_687, client.settings});
		}
		return JString.concatenate(new JString[] { Static61.aClass100_424, local8, Static80.aClass100_886, JString.parseInt(client.language), Static257.aClass100_98, JString.parseInt(client.affiliate), local10, Static41.aClass100_268 });
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(III)I")
	public static int method484(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) Inv local10 = (Inv) Inv.recentUse.getNode(arg0);
		if (local10 == null) {
			return -1;
		} else if (arg1 >= 0 && arg1 < local10.invSlotObjId.length) {
			return local10.invSlotObjId[arg1];
		} else {
			return -1;
		}
	}
}