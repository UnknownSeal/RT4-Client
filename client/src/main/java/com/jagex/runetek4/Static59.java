package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static59 {

	@OriginalMember(owner = "client!ej", name = "a", descriptor = "(Lclient!ve;ILclient!ve;Lclient!of;)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(2) Js5 arg1, @OriginalArg(3) QuickChatCommandDecoder arg2) {
		Static262.configClientLarge = arg0;
		Static107.anInterface3_1 = arg2;
		Static238.configClientSmall = arg1;
		if (Static238.configClientSmall != null) {
			QuickChatPhraseTypeList.anInt3490 = Static238.configClientSmall.getGroupCapacity(1);
		}
		if (Static262.configClientLarge != null) {
			QuickChatPhraseTypeList.anInt1047 = Static262.configClientLarge.getGroupCapacity(1);
		}
	}

}
