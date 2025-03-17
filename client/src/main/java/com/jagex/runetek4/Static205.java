package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static205 {

	@OriginalMember(owner = "runetek4.client!qj", name = "a", descriptor = "(Lclient!be;BI)Lclient!na;")
	public static JString method3677(@OriginalArg(0) Component arg0, @OriginalArg(2) int arg1) {
		if (!InterfaceList.getServerActiveProperties(arg0).isButtonEnabled(arg1) && arg0.onOptionClick == null) {
			return null;
		} else if (arg0.ops == null || arg0.ops.length <= arg1 || arg0.ops[arg1] == null || arg0.ops[arg1].trim().length() == 0) {
			return Cheat.qaOpTest ? JString.concatenate(new JString[] { Static207.aClass100_903, JString.parseInt(arg1) }) : null;
		} else {
			return arg0.ops[arg1];
		}
	}
}
