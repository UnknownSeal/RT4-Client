package com.jagex.runetek4;

import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.input.Keyboard;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static13 {

	@OriginalMember(owner = "client!bc", name = "W", descriptor = "I")
	public static int anInt435;

	@OriginalMember(owner = "client!bc", name = "X", descriptor = "I")
	public static int anInt436;

	@OriginalMember(owner = "client!bc", name = "Z", descriptor = "I")
	public static int anInt437;

	@OriginalMember(owner = "client!bc", name = "N", descriptor = "Lclient!lb;")
	public static final Class3_Sub23 aClass3_Sub23_1 = new Class3_Sub23(0, 0);

	@OriginalMember(owner = "client!bc", name = "f", descriptor = "(B)Lclient!na;")
	public static JString method471() {
		@Pc(32) JString local32;
		if (MiniMenu.anInt5014 == 1 && MiniMenu.menuActionRow < 2) {
			local32 = JString.concatenate(new JString[] { LocalizedText.USE, LocalizedText.MINISEPARATOR, Static34.aClass100_203, Static225.aClass100_961 });
		} else if (MiniMenu.aBoolean302 && MiniMenu.menuActionRow < 2) {
			local32 = JString.concatenate(new JString[] { Static102.aClass100_545, LocalizedText.MINISEPARATOR, Static78.aClass100_466, Static225.aClass100_961 });
		} else if (Cheat.shiftClick && Keyboard.pressedKeys[81] && MiniMenu.menuActionRow > 2) {
			local32 = Static269.method2228(MiniMenu.menuActionRow - 2);
		} else {
			local32 = Static269.method2228(MiniMenu.menuActionRow - 1);
		}
		if (MiniMenu.menuActionRow > 2) {
			local32 = JString.concatenate(new JString[] { local32, Static1.aClass100_2, JString.parseInt(MiniMenu.menuActionRow - 2), LocalizedText.MOREOPTIONS});
		}
		return local32;
	}

	@OriginalMember(owner = "client!bc", name = "d", descriptor = "(II)V")
	public static void method472(@OriginalArg(0) int arg0) {
		@Pc(14) Inv local14 = (Inv) Inv.recentUse.getNode((long) arg0);
		if (local14 != null) {
			local14.unlink();
		}
	}

}
