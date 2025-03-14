package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.LocEntity;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static141 {

    @OriginalMember(owner = "runetek4.client!lb", name = "p", descriptor = "[I")
	public static final int[] skillBaseLevel = new int[25];

	@OriginalMember(owner = "runetek4.client!lb", name = "s", descriptor = "Lclient!na;")
	public static final JString CLAN = Static28.parse(":clan:");

	@OriginalMember(owner = "runetek4.client!lb", name = "u", descriptor = "I")
	public static int anInt3469 = 0;

	@OriginalMember(owner = "runetek4.client!lb", name = "v", descriptor = "I")
	public static int anInt3470 = 0;

	@OriginalMember(owner = "runetek4.client!lb", name = "A", descriptor = "I")
	public static int anInt3474 = 0;

	@OriginalMember(owner = "runetek4.client!lb", name = "d", descriptor = "(B)V")
	public static void method2720() {
		if (Static153.aClass100_724 != null) {
			Static90.method1853(Static153.aClass100_724);
			Static153.aClass100_724 = null;
		}
	}

	@OriginalMember(owner = "runetek4.client!lb", name = "a", descriptor = "(Z)V")
	public static void method2721() {
		Static65.method1500();
		Static89.aClass3_Sub2_Sub1_5 = null;
		Static107.anInt2875 = -1;
		Static217.method3768();
		Static255.aClass54_16.method1815();
		Static171.aLocEntity_1 = new LocEntity();
		((Js5GlTextureProvider) Pix3D.anInterface1_2).method3247();
		Static120.anInt3034 = 0;
		Static120.aClass51Array1 = new Light[255];
		Static237.method4120();
		Static242.method4203();
		Static115.method2315();
		Static116.method2325(false);
		Static119.method2381();
		for (@Pc(39) int local39 = 0; local39 < 2048; local39++) {
			@Pc(46) PlayerEntity local46 = Static159.players[local39];
			if (local46 != null) {
				local46.locModel = null;
			}
		}
		if (GlRenderer.enabled) {
			Static242.method4201();
			Static76.method1642();
		}
		Static102.method2074(Static261.aClass153_107, Static209.aClass153_86);
		Static30.method839(Static209.aClass153_86);
		Static204.aClass3_Sub2_Sub1_10 = null;
		Static39.aClass3_Sub2_Sub1_1 = null;
		Static92.aClass3_Sub2_Sub1_6 = null;
		Static165.aClass3_Sub2_Sub1_8 = null;
		Static181.aClass3_Sub2_Sub1_9 = null;
		if (Static244.gamestate == 5) {
			Static181.method3344(Static209.aClass153_86);
		}
		if (Static244.gamestate == 10) {
			Static73.method1596(false);
		}
		if (Static244.gamestate == 30) {
			Game.processGameStatus(25);
		}
	}

	@OriginalMember(owner = "runetek4.client!lb", name = "a", descriptor = "(ZIIIBII)V")
	public static void method2722(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		Static113.anInt4612 = arg3;
		PreciseSleep.anInt5203 = arg2;
		Static245.anInt5375 = arg5;
		ClientScriptRunner.anInt5225 = arg1;
		Static248.anInt4232 = arg4;
		if (arg0 && Static113.anInt4612 >= 100) {
			Static138.cameraX = Static245.anInt5375 * 128 + 64;
			Static134.cameraZ = Static248.anInt4232 * 128 + 64;
			Static5.cameraY = Static207.getHeightmapY(Static55.currentLevel, Static138.cameraX, Static134.cameraZ) - PreciseSleep.anInt5203;
		}
		Static227.anInt5096 = 2;
	}

	@OriginalMember(owner = "runetek4.client!lb", name = "a", descriptor = "(Lclient!ve;Lclient!ve;ILclient!ve;)V")
	public static void method2724(@OriginalArg(0) CacheArchive arg0, @OriginalArg(1) CacheArchive arg1, @OriginalArg(3) CacheArchive arg2) {
		Static243.aClass153_98 = arg1;
		Static5.aClass153_1 = arg0;
		Static225.aClass153_92 = arg2;
	}
}
