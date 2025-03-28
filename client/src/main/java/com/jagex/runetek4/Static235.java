package com.jagex.runetek4;

import com.jagex.runetek4.input.Keyboard;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static235 {

	@OriginalMember(owner = "runetek4.client!tb", name = "Q", descriptor = "I")
	public static int anInt5276 = 0;

	@OriginalMember(owner = "runetek4.client!tb", name = "h", descriptor = "(I)I")
	public static int method4044() {
		return Cheat.shiftClick && Keyboard.pressedKeys[81] && MiniMenu.menuActionRow > 2 ? MiniMenu.cursors[MiniMenu.menuActionRow - 2] : MiniMenu.cursors[MiniMenu.menuActionRow - 1];
	}

}
