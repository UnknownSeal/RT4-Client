package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static269 {

	@OriginalMember(owner = "runetek4.client!wa", name = "ub", descriptor = "Lclient!bn;")
	public static Map aClass3_Sub2_Sub4_2;

	@OriginalMember(owner = "runetek4.client!wa", name = "X", descriptor = "[Lclient!na;")
	public static JString[] aClass100Array87 = null;

	@OriginalMember(owner = "runetek4.client!wa", name = "e", descriptor = "(B)V")
	public static void method2172() {
		ObjTypeList.objectSpriteCache.clean();
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "d", descriptor = "(BI)V")
	public static void method2221() {
		Static125.varbitDefinitionCache.clean(5);
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
	public static JString method2228(@OriginalArg(0) int arg0) {
		return MiniMenu.opBases[arg0].length() > 0 ? JString.concatenate(new JString[] { MiniMenu.ops[arg0], LocalizedText.MINISEPARATOR, MiniMenu.opBases[arg0] }) : MiniMenu.ops[arg0];
	}
}
