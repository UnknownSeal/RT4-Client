package com.jagex.runetek4;

import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static59 {

	@OriginalMember(owner = "client!ej", name = "h", descriptor = "(I)V")
	public static void processMenuActions() {
		if (Static162.anInt3953 == 2) {
			if (ClientScriptRunner.anInt3751 == Static277.anInt5850 && ClientScriptRunner.anInt1892 == Static280.anInt5895) {
				Static162.anInt3953 = 0;
				if (Cheat.shiftClick && Keyboard.pressedKeys[81] && MiniMenu.menuActionRow > 2) {
					Static103.method2232(MiniMenu.menuActionRow - 2);
				} else {
					Static103.method2232(MiniMenu.menuActionRow - 1);
				}
			}
		} else if (ClientScriptRunner.anInt3751 == Mouse.mouseClickX && ClientScriptRunner.anInt1892 == Mouse.mouseClickY) {
			Static162.anInt3953 = 0;
			if (Cheat.shiftClick && Keyboard.pressedKeys[81] && MiniMenu.menuActionRow > 2) {
				Static103.method2232(MiniMenu.menuActionRow - 2);
			} else {
				Static103.method2232(MiniMenu.menuActionRow - 1);
			}
		} else {
			Static280.anInt5895 = Mouse.mouseClickY;
			Static162.anInt3953 = 2;
			Static277.anInt5850 = Mouse.mouseClickX;
		}
	}

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
