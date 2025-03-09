package com.jagex.runetek4;

import java.awt.Container;
import java.awt.Insets;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.meltype.MapElementTypeList;
import com.jagex.runetek4.game.shared.framework.gwc.GWCLocation;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static203 {

	@OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "Lclient!se;")
	public static MapElementTypeList aMapElementTypeList_1;

	@OriginalMember(owner = "runetek4.client!qh", name = "d", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_8;

	@OriginalMember(owner = "runetek4.client!qh", name = "e", descriptor = "[Lclient!ee;")
	public static GWCLocation[] aGWCLocationArray1;

	@OriginalMember(owner = "runetek4.client!qh", name = "g", descriptor = "Lclient!ve;")
	public static Js5 aClass153_85;

	@OriginalMember(owner = "runetek4.client!qh", name = "c", descriptor = "Lclient!na;")
	public static final JString aClass100_893 = Static28.parse("Memory before cleanup=");

	@OriginalMember(owner = "runetek4.client!qh", name = "i", descriptor = "Lclient!na;")
	public static final JString MEM = Static28.parse("Mem:");

	@OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "(Z)V")
	public static void method3662() {
		@Pc(8) Container local8;
		if (Static69.aFrame2 != null) {
			local8 = Static69.aFrame2;
		} else if (Static39.frame == null) {
			local8 = GameShell.signLink.anApplet2;
		} else {
			local8 = Static39.frame;
		}
		Static72.frameWid = local8.getSize().width;
		Static122.frameHei = local8.getSize().height;
		@Pc(35) Insets local35;
		if (local8 == Static39.frame) {
			local35 = Static39.frame.getInsets();
			Static122.frameHei -= local35.bottom + local35.top;
			Static72.frameWid -= local35.right + local35.left;
		}
		if (Static144.method2736() >= 2) {
			Static48.canvasWidth = Static72.frameWid;
			Static145.leftMargin = 0;
			Static178.topMargin = 0;
			Static254.canvasHeigth = Static122.frameHei;
		} else {
			Static178.topMargin = 0;
			Static145.leftMargin = (Static72.frameWid - 765) / 2;
			Static254.canvasHeigth = 503;
			Static48.canvasWidth = 765;
		}
		if (GlRenderer.enabled) {
			GlRenderer.setCanvasSize(Static48.canvasWidth, Static254.canvasHeigth);
		}
		Static154.canvas.setSize(Static48.canvasWidth, Static254.canvasHeigth);
		if (local8 == Static39.frame) {
			local35 = Static39.frame.getInsets();
			Static154.canvas.setLocation(local35.left + Static145.leftMargin, Static178.topMargin + local35.top);
		} else {
			Static154.canvas.setLocation(Static145.leftMargin, Static178.topMargin);
		}
		if (Static154.topLevelInterace != -1) {
			Static210.method3712(true);
		}
		Static139.method2704();
	}

	@OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "(Lsignlink!ll;B)V")
	public static void method3663(@OriginalArg(0) SignLink arg0) {
		@Pc(11) FileOnDisk local11 = null;
		try {
			@Pc(16) PrivilegedRequest local16 = arg0.method5112("runescape");
			while (local16.status == 0) {
				PreciseSleep.sleep(1L);
			}
			if (local16.status == 1) {
				local11 = (FileOnDisk) local16.result;
				@Pc(39) Packet local39 = Static48.method1196();
				local11.method5134(local39.data, local39.pos, 0);
			}
		} catch (@Pc(49) Exception local49) {
		}
		try {
			if (local11 != null) {
				local11.method5136();
			}
		} catch (@Pc(56) Exception local56) {
		}
	}
}
