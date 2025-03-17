package com.jagex.runetek4;

import com.jagex.runetek4.cache.cs.ClientScript;
import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static154 {

	@OriginalMember(owner = "runetek4.client!md", name = "S", descriptor = "I")
	public static int anInt3709;

	@OriginalMember(owner = "runetek4.client!md", name = "a", descriptor = "(Lclient!be;I)Z")
	public static boolean method2926(@OriginalArg(0) Component arg0) {
		if (arg0.cs1ComparisonOpcodes == null) {
			return false;
		}
		for (@Pc(14) int local14 = 0; local14 < arg0.cs1ComparisonOpcodes.length; local14++) {
			@Pc(34) int local34 = ClientScript.executeClientscript(local14, arg0);
			@Pc(39) int local39 = arg0.scriptOperand[local14];
			if (arg0.cs1ComparisonOpcodes[local14] == 2) {
				if (local39 <= local34) {
					return false;
				}
			} else if (arg0.cs1ComparisonOpcodes[local14] == 3) {
				if (local34 <= local39) {
					return false;
				}
			} else if (arg0.cs1ComparisonOpcodes[local14] == 4) {
				if (local34 == local39) {
					return false;
				}
			} else if (local39 != local34) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!md", name = "a", descriptor = "(JB)Lclient!na;")
	public static JString method2929(@OriginalArg(0) long arg0) {
		return Static59.method1376(arg0);
	}
}
