package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.LocAddEntity;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static226 {

	@OriginalMember(owner = "runetek4.client!sf", name = "b", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_93;

	@OriginalMember(owner = "runetek4.client!sf", name = "c", descriptor = "I")
	public static int anInt5080;

	@OriginalMember(owner = "runetek4.client!sf", name = "j", descriptor = "I")
	public static int anInt5085;

	@OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "I")
	public static int loops = 0;

	@OriginalMember(owner = "runetek4.client!sf", name = "d", descriptor = "I")
	public static int anInt5081 = 1;

	@OriginalMember(owner = "runetek4.client!sf", name = "g", descriptor = "Lclient!na;")
	public static final JString YELLOW2 = Static28.parse("<col=ffff00>");

	@OriginalMember(owner = "runetek4.client!sf", name = "h", descriptor = "[Lclient!na;")
	public static final JString[] varcstrs = new JString[1000];

	@OriginalMember(owner = "runetek4.client!sf", name = "i", descriptor = "I")
	public static int anInt5084 = 0;

	@OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "(ILclient!cd;)V")
	public static void method3898(@OriginalArg(1) LocAddEntity arg0) {
		@Pc(5) long local5 = 0L;
		@Pc(7) int local7 = -1;
		@Pc(14) int local14 = 0;
		if (arg0.anInt927 == 0) {
			local5 = Static265.method4521(arg0.anInt918, arg0.x, arg0.z);
		}
		@Pc(31) int local31 = 0;
		if (arg0.anInt927 == 1) {
			local5 = Static139.method2703(arg0.anInt918, arg0.x, arg0.z);
		}
		if (arg0.anInt927 == 2) {
			local5 = Static35.method899(arg0.anInt918, arg0.x, arg0.z);
		}
		if (arg0.anInt927 == 3) {
			local5 = Static20.method602(arg0.anInt918, arg0.x, arg0.z);
		}
		if (local5 != 0L) {
			local7 = Integer.MAX_VALUE & (int) (local5 >>> 32);
			local31 = (int) local5 >> 20 & 0x3;
			local14 = (int) local5 >> 14 & 0x1F;
		}
		arg0.anInt921 = local7;
		arg0.anInt920 = local14;
		arg0.anInt923 = local31;
	}

	@OriginalMember(owner = "runetek4.client!sf", name = "a", descriptor = "(BLclient!ve;)V")
	public static void method3899(@OriginalArg(1) CacheArchive arg0) {
		Static39.aClass153_23 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!sf", name = "b", descriptor = "(B)V")
	public static void determineMenuSize() {
		@Pc(16) int local16 = Static280.aClass3_Sub2_Sub9_43.method2858(LocalizedText.CHOOSE_OPTION);
		@Pc(18) int local18;
		@Pc(27) int local27;
		for (local18 = 0; local18 < PreciseSleep.menuActionRow; local18++) {
			local27 = Static280.aClass3_Sub2_Sub9_43.method2858(Static269.method2228(local18));
			if (local27 > local16) {
				local16 = local27;
			}
		}
		local18 = PreciseSleep.menuActionRow * 15 + 21;
		@Pc(43) int local43 = Static60.anInt1892;
		local16 += 8;
		local27 = Static155.anInt3751 - local16 / 2;
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
			if (Static155.anInt3751 == Static277.anInt5850 && Static280.anInt5895 == Static60.anInt1892) {
				Static13.anInt436 = PreciseSleep.menuActionRow * 15 + (Static261.aBoolean298 ? 26 : 22);
				Static162.anInt3953 = 0;
				Static229.anInt5138 = local43;
				Static183.anInt4271 = local27;
				Static60.aBoolean108 = true;
				Static24.anInt761 = local16;
			}
		} else if (Static155.anInt3751 == aClass6.mouseClickX && Static60.anInt1892 == Static60.mouseClickY) {
			Static183.anInt4271 = local27;
			Static162.anInt3953 = 0;
			Static24.anInt761 = local16;
			Static229.anInt5138 = local43;
			Static13.anInt436 = (Static261.aBoolean298 ? 26 : 22) + PreciseSleep.menuActionRow * 15;
			Static60.aBoolean108 = true;
		} else {
			Static280.anInt5895 = Static60.mouseClickY;
			Static277.anInt5850 = aClass6.mouseClickX;
			Static162.anInt3953 = 1;
		}
	}
}
