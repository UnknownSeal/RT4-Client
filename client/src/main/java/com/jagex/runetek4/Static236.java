package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static236 {

	@OriginalMember(owner = "runetek4.client!tc", name = "a", descriptor = "(B)I")
	public static int method4047() {
		if (ClientScriptRunner.neverRemoveRoofs) {
			return 0;
		} else if (Static138.allLevelsvisible()) {
			return Preferences.roofsVisible ? 2 : 1;
		} else {
			return 1;
		}
	}

}
