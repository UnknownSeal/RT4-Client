package com.jagex.runetek4;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Insets;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static139 {

	@OriginalMember(owner = "runetek4.client!l", name = "j", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array6;

	@OriginalMember(owner = "runetek4.client!l", name = "l", descriptor = "[I")
	public static int[] anIntArray325;

	@OriginalMember(owner = "runetek4.client!l", name = "b", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_21 = new SoftLruHashTable(5);

	@OriginalMember(owner = "runetek4.client!l", name = "d", descriptor = "Lclient!na;")
	public static final JString aClass100_659 = Static28.parse(")4j");

	@OriginalMember(owner = "runetek4.client!l", name = "f", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_22 = new SoftLruHashTable(4);

	@OriginalMember(owner = "runetek4.client!l", name = "k", descriptor = "I")
	public static int anInt3451 = 2;

	@OriginalMember(owner = "runetek4.client!l", name = "a", descriptor = "(III)J")
	public static long method2703(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Ground local7 = Static130.levelTiles[arg0][arg1][arg2];
		return local7 == null || local7.decor == null ? 0L : local7.decor.aLong52;
	}

	@OriginalMember(owner = "runetek4.client!l", name = "b", descriptor = "(I)V")
	public static void method2704() {
		@Pc(7) int local7 = Static178.topMargin;
		@Pc(9) int local9 = Static145.leftMargin;
		@Pc(16) int local16 = Static122.frameHei - Static254.canvasHeigth - local7;
		@Pc(23) int local23 = Static72.frameWid - local9 - Static48.canvasWidth;
		if (local9 <= 0 && local23 <= 0 && local7 <= 0 && local16 <= 0) {
			return;
		}
		try {
			@Pc(46) Container local46;
			if (Static69.aFrame2 != null) {
				local46 = Static69.aFrame2;
			} else if (Static39.frame == null) {
				local46 = GameShell.signLink.anApplet2;
			} else {
				local46 = Static39.frame;
			}
			@Pc(59) int local59 = 0;
			@Pc(61) int local61 = 0;
			if (Static39.frame == local46) {
				@Pc(68) Insets local68 = Static39.frame.getInsets();
				local61 = local68.left;
				local59 = local68.top;
			}
			@Pc(77) Graphics local77 = local46.getGraphics();
			local77.setColor(Color.black);
			if (local9 > 0) {
				local77.fillRect(local61, local59, local9, Static122.frameHei);
			}
			if (local7 > 0) {
				local77.fillRect(local61, local59, Static72.frameWid, local7);
			}
			if (local23 > 0) {
				local77.fillRect(local61 + Static72.frameWid - local23, local59, local23, Static122.frameHei);
			}
			if (local16 > 0) {
				local77.fillRect(local61, local59 + Static122.frameHei - local16, Static72.frameWid, local16);
			}
		} catch (@Pc(132) Exception local132) {
		}
	}
}
