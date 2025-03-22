package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static3 {

	@OriginalMember(owner = "runetek4.client!ab", name = "j", descriptor = "[Lclient!na;")
	public static final JString[] scriptStringValues = new JString[1000];

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method4656(@OriginalArg(0) JString arg0) {
		@Pc(9) int local9 = Static171.method3218(arg0);
		if (local9 != -1) {
			Static80.method3616(Static203.aMapElementList_1.aShortArray73[local9], Static203.aMapElementList_1.aShortArray72[local9]);
		}
	}

	@OriginalMember(owner = "runetek4.client!ab", name = "b", descriptor = "(B)V")
	public static void removeSoft() {
		VarPlayerDefinition.varPlayerDefinitionCache.removeSoft();
	}

}
