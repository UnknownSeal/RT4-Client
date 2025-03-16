package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.ItemDefinition;
import com.jagex.runetek4.cache.media.Font;
import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static44 {

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "Z")
	public static boolean aBoolean83 = false;

	@OriginalMember(owner = "runetek4.client!dh", name = "d", descriptor = "[I")
	public static final int[] entityUpdateIds = new int[2048];

	@OriginalMember(owner = "runetek4.client!dh", name = "i", descriptor = "Lclient!na;")
	public static final JString aClass100_336 = Static28.parse("<img=1>");

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(Z)V")
	public static void method1146() {
		Static6.outboundBuffer.offset = 0;
		Static5.anInt45 = -1;
		Static60.aBoolean108 = false;
		Static223.packetSize = 0;
		Static115.anInt2939 = 0;
		PreciseSleep.menuActionRow = 0;
		Static230.anInt5152 = -1;
		Static270.anInt5795 = 0;
		Static60.systemUpdateTimer = 0;
		Static49.anInt1462 = -1;
		Static57.in.offset = 0;
		Static201.idleNetCycles = 0;
		Static164.packetType = -1;
		@Pc(35) int local35;
		for (local35 = 0; local35 < Static159.players.length; local35++) {
			if (Static159.players[local35] != null) {
				Static159.players[local35].targetId = -1;
			}
		}
		for (local35 = 0; local35 < NpcList.npcs.length; local35++) {
			if (NpcList.npcs[local35] != null) {
				NpcList.npcs[local35].targetId = -1;
			}
		}
		Static102.method2073();
		Camera.cameraType = 1;
		Game.processGameStatus(30);
		for (local35 = 0; local35 < 100; local35++) {
			Static186.aBooleanArray100[local35] = true;
		}
		Static59.method1373();
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "b", descriptor = "(I)Lclient!q;")
	public static ReferenceNodeFactory method1147() {
		try {
			return (ReferenceNodeFactory) Class.forName("com.jagex.runetek4.SoftReferenceNodeFactory").getDeclaredConstructor().newInstance();
		} catch (@Pc(15) Throwable local15) {
			return null;
		}
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(IIII)Lclient!wk;")
	public static Class3_Sub31 method1148(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2) {
		@Pc(9) Class3_Sub31 local9 = new Class3_Sub31();
		local9.anInt5879 = arg2;
		local9.anInt5878 = arg0;
		Static119.aClass133_9.put(local9, (long) arg1);
		ItemDefinition.method1753(arg0);
		@Pc(28) Component local28 = Component.getComponent(arg1);
		if (local28 != null) {
			Static43.method1143(local28);
		}
		if (Static39.aClass13_10 != null) {
			Static43.method1143(Static39.aClass13_10);
			Static39.aClass13_10 = null;
		}
		@Pc(45) int local45 = PreciseSleep.menuActionRow;
		@Pc(53) int local53;
		for (local53 = 0; local53 < local45; local53++) {
			if (Static2.method5(Static39.aShortArray6[local53])) {
				Static200.method3628(local53);
			}
		}
		if (PreciseSleep.menuActionRow == 1) {
			Static60.aBoolean108 = false;
			Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
		} else {
			Static133.method4012(Static183.anInt4271, Static24.anInt761, Static229.anInt5138, Static13.anInt436);
			local53 = Font.b12Full.method2858(LocalizedText.CHOOSE_OPTION);
			for (@Pc(95) int local95 = 0; local95 < PreciseSleep.menuActionRow; local95++) {
				@Pc(104) int local104 = Font.b12Full.method2858(Static269.method2228(local95));
				if (local104 > local53) {
					local53 = local104;
				}
			}
			Static24.anInt761 = local53 + 8;
			Static13.anInt436 = PreciseSleep.menuActionRow * 15 + (Static261.aBoolean298 ? 26 : 22);
		}
		if (local28 != null) {
			Static17.method531(local28, false);
		}
		Static74.method1626(arg0);
		if (Static154.topLevelInterace != -1) {
			Static54.method1304(1, Static154.topLevelInterace);
		}
		return local9;
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method1149(@OriginalArg(0) JString arg0) {
		@Pc(7) int local7 = Static91.method1879(arg0);
		if (local7 != -1) {
			Static80.method3616(Static203.aMapElementTypeList_1.aShortArray73[local7], Static203.aMapElementTypeList_1.aShortArray72[local7]);
		}
	}

	@OriginalMember(owner = "runetek4.client!dh", name = "a", descriptor = "(B)Lclient!uc;")
	public static MouseWheel create() {
		try {
			return (MouseWheel) Class.forName("com.jagex.runetek4.JavaMouseWheel").getDeclaredConstructor().newInstance();
		} catch (@Pc(15) Throwable local15) {
			return null;
		}
	}
}
