package com.jagex.runetek4;

import java.awt.Container;
import java.awt.Insets;

import com.jagex.runetek4.game.config.meltype.MapElementTypeList;
import com.jagex.runetek4.game.shared.framework.gwc.GWCLocation;
import com.jagex.runetek4.js5.CacheArchive;
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
	public static CacheArchive aClass153_85;

	@OriginalMember(owner = "runetek4.client!qh", name = "c", descriptor = "Lclient!na;")
	public static final JString aClass100_893 = Static28.parse("Memory before cleanup=");

	@OriginalMember(owner = "runetek4.client!qh", name = "i", descriptor = "Lclient!na;")
	public static final JString MEM = Static28.parse("Mem:");

	@OriginalMember(owner = "runetek4.client!qh", name = "a", descriptor = "(Z)V")
	public static void method3662() {
		@Pc(8) Container local8;
		if (GameShell.fullScreenFrame != null) {
			local8 = GameShell.fullScreenFrame;
		} else if (GameShell.frame == null) {
			local8 = GameShell.signLink.applet;
		} else {
			local8 = GameShell.frame;
		}
		GameShell.frameWidth = local8.getSize().width;
		GameShell.frameHeight = local8.getSize().height;
		@Pc(35) Insets local35;
		if (local8 == GameShell.frame) {
			local35 = GameShell.frame.getInsets();
			GameShell.frameHeight -= local35.bottom + local35.top;
			GameShell.frameWidth -= local35.right + local35.left;
		}
		if (Static144.method2736() >= 2) {
			GameShell.canvasWidth = GameShell.frameWidth;
			GameShell.leftMargin = 0;
			GameShell.topMargin = 0;
			GameShell.canvasHeigth = GameShell.frameHeight;
		} else {
			GameShell.topMargin = 0;
			GameShell.leftMargin = (GameShell.frameWidth - 765) / 2;
			GameShell.canvasHeigth = 503;
			GameShell.canvasWidth = 765;
		}
		if (GlRenderer.enabled) {
			GlRenderer.setCanvasSize(GameShell.canvasWidth, GameShell.canvasHeigth);
		}
		GameShell.canvas.setSize(GameShell.canvasWidth, GameShell.canvasHeigth);
		if (local8 == GameShell.frame) {
			local35 = GameShell.frame.getInsets();
			GameShell.canvas.setLocation(local35.left + GameShell.leftMargin, GameShell.topMargin + local35.top);
		} else {
			GameShell.canvas.setLocation(GameShell.leftMargin, GameShell.topMargin);
		}
		if (Static154.topLevelInterace != -1) {
			Static210.method3712(true);
		}
		Static139.method2704();
	}

}
