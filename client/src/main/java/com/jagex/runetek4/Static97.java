package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.game.shared.framework.gwc.GWCLocation;
import com.jagex.runetek4.game.shared.framework.gwc.GWCWorld;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static97 {

	@OriginalMember(owner = "runetek4.client!hi", name = "f", descriptor = "J")
	public static long aLong89 = 0L;

	@OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(IIIIILclient!be;Z)V")
	public static void drawMinimapMark(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int mapX, @OriginalArg(4) int mapY, @OriginalArg(5) Component arg5) {
		@Pc(13) int len = mapX * mapX + mapY * mapY;
		if (len > 360000) {
			return;
		}
		@Pc(30) int local30 = Math.min(arg5.width / 2, arg5.height / 2);
		if (local30 * local30 >= len) {
			MiniMap.drawOnMinimap(arg5, Static149.aClass3_Sub2_Sub1Array7[arg0], mapY, mapX, arg1, arg2);
			return;
		}
		local30 -= 10;
		@Pc(58) int local58 = MiniMap.minimapAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
		@Pc(62) int local62 = MathUtils.cos[local58];
		@Pc(66) int local66 = MathUtils.sin[local58];
		@Pc(74) int local74 = local66 * 256 / (MiniMap.minimapZoom + 256);
		@Pc(82) int local82 = local62 * 256 / (MiniMap.minimapZoom + 256);
		@Pc(93) int local93 = mapY * local74 + local82 * mapX >> 16;
		@Pc(104) int local104 = mapY * local82 - local74 * mapX >> 16;
		@Pc(110) double angle = Math.atan2((double) local93, (double) local104);
		@Pc(117) int sine = (int) (Math.sin(angle) * (double) local30);
		@Pc(124) int cosine = (int) (Math.cos(angle) * (double) local30);
		if (GlRenderer.enabled) {
			((GlSprite) Static277.aClass3_Sub2_Sub1Array12[arg0]).method1428((arg5.width / 2 + arg2 + sine) * 16, (arg5.height / 2 + arg1 - cosine) * 16, (int) (angle * 10430.378D));
		} else {
			((SoftwareSprite) Static277.aClass3_Sub2_Sub1Array12[arg0]).method306(sine + arg5.width / 2 + arg2 - 10, arg5.height / 2 + -10 + arg1 + -cosine, angle);
		}
	}

    @OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(Lclient!wa;I)V")
	public static void method1962(@OriginalArg(0) Packet packet) {
		@Pc(9) int local9 = packet.gSmart1or2();
		Static203.aGWCLocationArray1 = new GWCLocation[local9];
		for (int index = 0; index < local9; index++) {
			Static203.aGWCLocationArray1[index] = new GWCLocation();
			Static203.aGWCLocationArray1[index].flag = packet.gSmart1or2();
			Static203.aGWCLocationArray1[index].name = packet.gjstr2();
		}
		Static19.anInt636 = packet.gSmart1or2();
		Static171.anInt4157 = packet.gSmart1or2();
		Static106.anInt2871 = packet.gSmart1or2();
		Static196.aClass10_Sub1Array2 = new GWCWorld[Static171.anInt4157 + 1 - Static19.anInt636];
		for (int index = 0; index < Static106.anInt2871; index++) {
			@Pc(77) int local77 = packet.gSmart1or2();
			@Pc(85) GWCWorld local85 = Static196.aClass10_Sub1Array2[local77] = new GWCWorld();
			local85.country = packet.g1();
			local85.flags = packet.g4();
			local85.id = local77 + Static19.anInt636;
			local85.activity = packet.gjstr2();
			local85.hostname = packet.gjstr2();
		}
		Static80.anInt4702 = packet.g4();
		WorldList.loaded = true;
	}

	@OriginalMember(owner = "runetek4.client!hi", name = "a", descriptor = "(Lclient!be;B)Lclient!na;")
	public static JString method1963(@OriginalArg(0) Component arg0) {
		if (InterfaceList.getServerActiveProperties(arg0).method512() == 0) {
			return null;
		} else if (arg0.optionCircumfix == null || arg0.optionCircumfix.trim().length() == 0) {
			return Cheat.qaOpTest ? Static143.HIDDEN_USE : null;
		} else {
			return arg0.optionCircumfix;
		}
	}
}
