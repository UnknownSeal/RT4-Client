package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static226 {

	@OriginalMember(owner = "runetek4.client!sf", name = "j", descriptor = "I")
	public static int anInt5085;

	@OriginalMember(owner = "runetek4.client!sf", name = "g", descriptor = "Lclient!na;")
	public static final JString YELLOW2 = JString.parse("<col=ffff00>");

	@OriginalMember(owner = "runetek4.client!sf", name = "h", descriptor = "[Lclient!na;")
	public static final JString[] varcstrs = new JString[1000];

	@OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "(BLclient!ve;)V")
	public static void init(@OriginalArg(1) Js5 arg0) {
		Static39.aClass153_23 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!sf", name = "b", descriptor = "(B)V")
	public static void determineMenuSize() {
		@Pc(16) int local16 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
		@Pc(18) int local18;
		@Pc(27) int local27;
		for (local18 = 0; local18 < MiniMenu.menuActionRow; local18++) {
			local27 = Fonts.b12Full.getStringWidth(Static269.method2228(local18));
			if (local27 > local16) {
				local16 = local27;
			}
		}
		local18 = MiniMenu.menuActionRow * 15 + 21;
		@Pc(43) int local43 = ClientScriptRunner.anInt1892;
		local16 += 8;
		local27 = ClientScriptRunner.anInt3751 - local16 / 2;
		if (local43 + local18 > GameShell.canvasHeigth) {
			local43 = GameShell.canvasHeigth - local18;
		}
		if (GameShell.canvasWidth < local27 + local16) {
			local27 = GameShell.canvasWidth - local16;
		}
		if (local27 < 0) {
			local27 = 0;
		}
		if (local43 < 0) {
			local43 = 0;
		}
		if (Static162.anInt3953 == 1) {
			if (ClientScriptRunner.anInt3751 == Static277.anInt5850 && Static280.anInt5895 == ClientScriptRunner.anInt1892) {
				Static13.anInt436 = MiniMenu.menuActionRow * 15 + (InterfaceList.aBoolean298 ? 26 : 22);
				Static162.anInt3953 = 0;
				Static229.anInt5138 = local43;
				Static183.anInt4271 = local27;
				ClientScriptRunner.aBoolean108 = true;
				Static24.anInt761 = local16;
			}
		} else if (ClientScriptRunner.anInt3751 == aClass6.mouseClickX && ClientScriptRunner.anInt1892 == Static60.mouseClickY) {
			Static183.anInt4271 = local27;
			Static162.anInt3953 = 0;
			Static24.anInt761 = local16;
			Static229.anInt5138 = local43;
			Static13.anInt436 = (InterfaceList.aBoolean298 ? 26 : 22) + MiniMenu.menuActionRow * 15;
			ClientScriptRunner.aBoolean108 = true;
		} else {
			Static280.anInt5895 = Static60.mouseClickY;
			Static277.anInt5850 = aClass6.mouseClickX;
			Static162.anInt3953 = 1;
		}
	}
}
