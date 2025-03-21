package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static85 {

	@OriginalMember(owner = "client!gl", name = "d", descriptor = "I")
	public static int anInt2261;

	@OriginalMember(owner = "client!gl", name = "e", descriptor = "I")
	public static int anInt2262;

	@OriginalMember(owner = "client!gl", name = "a", descriptor = "(II)V")
	public static void handleVarps(@OriginalArg(1) int arg0) {
		InterfaceList.redrawActiveInterfaces();
		AreaSoundManager.setObjectSounds();
		@Pc(17) int varpType = VarPlayerDefinition.getDefinition(arg0).type;
		if (varpType == 0) {
			return;
		}
		@Pc(25) int varpValue = VarPlayerDefinition.varPlayers[arg0];
		if (varpType == 6) {
			Static79.chatEffectsDisabled = varpValue;
		}
		if (varpType == 5) {
			Static116.oneMouseButton = varpValue;
		}
		if (varpType == 9) {
			Static179.bankInsertMode = varpValue;
		}
	}
}
