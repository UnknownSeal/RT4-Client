package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static226 {

	@OriginalMember(owner = "runetek4.client!sf", name = "h", descriptor = "[Lclient!na;")
	public static final JString[] varcstrs = new JString[1000];

	@OriginalMember(owner = "runetek4.client!sf", name = "b", descriptor = "(B)V")
	public static void determineMenuSize() {
		@Pc(16) int local16 = Fonts.b12Full.getStringWidth(LocalizedText.CHOOSE_OPTION);
		@Pc(18) int local18;
		@Pc(27) int local27;
		for (local18 = 0; local18 < MiniMenu.menuActionRow; local18++) {
			local27 = Fonts.b12Full.getStringWidth(MiniMenu.getOp(local18));
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
		if (MiniMenu.anInt3953 == 1) {
			if (ClientScriptRunner.anInt3751 == Mouse.anInt5850 && Mouse.anInt5895 == ClientScriptRunner.anInt1892) {
				InterfaceList.anInt436 = MiniMenu.menuActionRow * 15 + (InterfaceList.aBoolean298 ? 26 : 22);
				MiniMenu.anInt3953 = 0;
				InterfaceList.anInt5138 = local43;
				InterfaceList.anInt4271 = local27;
				ClientScriptRunner.aBoolean108 = true;
				InterfaceList.anInt761 = local16;
			}
		} else if (ClientScriptRunner.anInt3751 == Mouse.mouseClickX && ClientScriptRunner.anInt1892 == Mouse.mouseClickY) {
			InterfaceList.anInt4271 = local27;
			MiniMenu.anInt3953 = 0;
			InterfaceList.anInt761 = local16;
			InterfaceList.anInt5138 = local43;
			InterfaceList.anInt436 = (InterfaceList.aBoolean298 ? 26 : 22) + MiniMenu.menuActionRow * 15;
			ClientScriptRunner.aBoolean108 = true;
		} else {
			Mouse.anInt5895 = Mouse.mouseClickY;
			Mouse.anInt5850 = Mouse.mouseClickX;
			MiniMenu.anInt3953 = 1;
		}
	}
}
