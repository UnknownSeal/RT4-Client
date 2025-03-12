package com.jagex.runetek4;

import java.io.IOException;

import com.jagex.runetek4.config.Component;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class aClass6 {

	@OriginalMember(owner = "client!ah", name = "l", descriptor = "[[B")
	public static byte[][] aByteArrayArray5;

	@OriginalMember(owner = "client!ah", name = "t", descriptor = "I")
	public static int anInt986;

	@OriginalMember(owner = "client!ah", name = "i", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_5 = new NodeCache(2);

	@OriginalMember(owner = "client!ah", name = "k", descriptor = "[J")
	public static final long[] aLongArray2 = new long[32];

	@OriginalMember(owner = "client!ah", name = "n", descriptor = "I")
	public static int anInt983 = 0;

	@OriginalMember(owner = "client!ah", name = "p", descriptor = "Lclient!ih;")
	public static final LinkList drawTileQueue = new LinkList();

	@OriginalMember(owner = "client!ah", name = "s", descriptor = "I")
	public static int mouseClickX = 0;

	@OriginalMember(owner = "client!ah", name = "a", descriptor = "(BZ)V")
	public static void method842(@OriginalArg(1) boolean arg0) {
		Static107.method2261();
		if (Static244.gamestate != 30 && Static244.gamestate != 25) {
			return;
		}
		Static131.anInt3251++;
		if (Static131.anInt3251 < 50 && !arg0) {
			return;
		}
		Static131.anInt3251 = 0;
		if (!Static224.aBoolean247 && Static124.socket != null) {
			Static6.outboundBuffer.pIsaac1(93);
			try {
				Static124.socket.write(Static6.outboundBuffer.position, Static6.outboundBuffer.data);
				Static6.outboundBuffer.position = 0;
			} catch (@Pc(53) IOException local53) {
				Static224.aBoolean247 = true;
			}
		}
		Static107.method2261();
	}

	@OriginalMember(owner = "client!ah", name = "b", descriptor = "(I)V")
	public static void method843() {
		if (Static118.component != null || Static105.aClass13_14 != null) {
			return;
		}
		@Pc(20) int local20 = Mouse.clickButton;
		@Pc(93) int local93;
		@Pc(99) int local99;
		if (!Static60.aBoolean108) {
			if (local20 == 1 && PreciseSleep.menuActionRow > 0) {
				@Pc(37) short local37 = Static39.aShortArray6[PreciseSleep.menuActionRow - 1];
				if (local37 == 25 || local37 == 23 || local37 == 48 || local37 == 7 || local37 == 13 || local37 == 47 || local37 == 5 || local37 == 43 || local37 == 35 || local37 == 58 || local37 == 22 || local37 == 1006) {
					local93 = Static196.anIntArray408[PreciseSleep.menuActionRow - 1];
					local99 = Static56.anIntArray142[PreciseSleep.menuActionRow - 1];
					@Pc(103) Component local103 = Static5.getComponent(local99);
					@Pc(106) ServerActiveProperties local106 = Static36.method940(local103);
					if (local106.method511() || local106.method504()) {
						Static78.lastItemDragTime = 0;
						Static123.lastItemDragged = false;
						if (Static118.component != null) {
							Static43.method1143(Static118.component);
						}
						Static118.component = Static5.getComponent(local99);
						Static149.anInt3554 = mouseClickX;
						Static206.anInt4773 = Static60.mouseClickY;
						Static4.selectedInventorySlot = local93;
						Static43.method1143(Static118.component);
						return;
					}
				}
			}
			if (local20 == 1 && (Static116.oneMouseButton == 1 && PreciseSleep.menuActionRow > 2 || Static277.menuHasAddFriend(PreciseSleep.menuActionRow - 1))) {
				local20 = 2;
			}
			if (local20 == 2 && PreciseSleep.menuActionRow > 0 || Static162.anInt3953 == 1) {
				Static226.determineMenuSize();
			}
			if (local20 == 1 && PreciseSleep.menuActionRow > 0 || Static162.anInt3953 == 2) {
				Static59.processMenuActions();
			}
			return;
		}
		@Pc(204) int local204;
		if (local20 != 1) {
			local93 = Static223.anInt5032;
			local204 = Static215.anInt4873;
			if (local204 < Static183.anInt4271 - 10 || local204 > Static24.anInt761 + Static183.anInt4271 + 10 || Static229.anInt5138 - 10 > local93 || local93 > Static13.anInt436 + Static229.anInt5138 + 10) {
				Static60.aBoolean108 = false;
				Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
			}
		}
		if (local20 != 1) {
			return;
		}
		local204 = Static183.anInt4271;
		local93 = Static229.anInt5138;
		local99 = Static24.anInt761;
		@Pc(265) int local265 = mouseClickX;
		@Pc(267) int local267 = Static60.mouseClickY;
		@Pc(269) int local269 = -1;
		for (@Pc(271) int local271 = 0; local271 < PreciseSleep.menuActionRow; local271++) {
			@Pc(289) int local289;
			if (Static261.aBoolean298) {
				local289 = (PreciseSleep.menuActionRow - local271 - 1) * 15 + local93 + 35;
			} else {
				local289 = (PreciseSleep.menuActionRow - local271 - 1) * 15 + local93 + 31;
			}
			if (local265 > local204 && local204 + local99 > local265 && local289 - 13 < local267 && local289 + 3 > local267) {
				local269 = local271;
			}
		}
		if (local269 != -1) {
			Static103.method2232(local269);
		}
		Static60.aBoolean108 = false;
		Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
	}

}
