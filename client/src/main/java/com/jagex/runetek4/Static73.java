package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.dash3d.entity.PlayerEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static73 {

	@OriginalMember(owner = "runetek4.client!fm", name = "V", descriptor = "I")
	public static int anInt2077;

	@OriginalMember(owner = "runetek4.client!fm", name = "S", descriptor = "Lclient!n;")
	public static final NodeCache aClass99_10 = new NodeCache(4);

	@OriginalMember(owner = "runetek4.client!fm", name = "W", descriptor = "Lclient!na;")
	public static final JString aClass100_453 = Static28.parse(")2");

	@OriginalMember(owner = "runetek4.client!fm", name = "db", descriptor = "Lclient!gn;")
	public static final LruHashTable aClass54_7 = new LruHashTable(64);

	@OriginalMember(owner = "runetek4.client!fm", name = "eb", descriptor = "Lclient!na;")
	public static final JString B12_FULL = Static28.parse("b12_full");

	@OriginalMember(owner = "runetek4.client!fm", name = "fb", descriptor = "[I")
	public static final int[] anIntArray183 = new int[14];

	@OriginalMember(owner = "runetek4.client!fm", name = "gb", descriptor = "Lclient!na;")
	public static final JString OVERLAY2 = Static28.parse("overlay2");

	@OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ZI)V")
	public static void method1596(@OriginalArg(0) boolean arg0) {
		if (arg0) {
			if (Static154.topLevelInterace != -1) {
				Component.resetComponent(Static154.topLevelInterace);
			}
			for (@Pc(18) Class3_Sub31 local18 = (Class3_Sub31) Static119.aClass133_9.peekFront(); local18 != null; local18 = (Class3_Sub31) Static119.aClass133_9.prev()) {
				Static132.method2605(true, local18);
			}
			Static154.topLevelInterace = -1;
			Static119.aClass133_9 = new IterableMap(8);
			Component.createComponentMemoryBuffer();
			Static154.topLevelInterace = Static156.anInt3783;
			Static210.method3712(false);
			Static87.method1807();
			Static74.method1626(Static154.topLevelInterace);
		}
		Static35.anInt1092 = -1;
		client.method1750(Static270.anInt5794);
		Static173.localPlayer = new PlayerEntity();
		Static173.localPlayer.z = 3000;
		Static173.localPlayer.x = 3000;
		if (!GlRenderer.enabled) {
			Static145.method2743(Static209.aClass153_86);
			Game.processGameStatus(10);
			return;
		}
		if (Static227.anInt5096 == 2) {
			Static138.cameraX = Static245.anInt5375 << 7;
			Static134.cameraZ = Static248.anInt4232 << 7;
		} else {
			Static40.method1008();
		}
		Static86.method1799();
		Static26.method740();
		Game.processGameStatus(28);
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ZII)V")
	public static void method1597(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		Static85.anInt2263 = Static192.aClass92ArrayArray1[arg1][arg0].anInt3530;
		Static159.anInt3893 = Static192.aClass92ArrayArray1[arg1][arg0].anInt3528;
		Static148.anInt3534 = Static192.aClass92ArrayArray1[arg1][arg0].anInt3527;
		Static161.method3063((float) Static85.anInt2263, (float) Static159.anInt3893, (float) Static148.anInt3534);
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(ILclient!na;Lclient!na;Lclient!na;I)V")
	public static void method1598(@OriginalArg(1) JString arg0, @OriginalArg(2) JString arg1, @OriginalArg(3) JString arg2) {
		Static154.add(-1, 9, arg0, arg2, arg1);
	}

	@OriginalMember(owner = "runetek4.client!fm", name = "a", descriptor = "(IIIIII)Z")
	public static boolean method1599(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(16) int local16;
		@Pc(20) int local20;
		if (arg1 != arg2 || arg3 != arg4) {
			for (local16 = arg1; local16 <= arg2; local16++) {
				for (local20 = arg3; local20 <= arg4; local20++) {
					if (Static140.anIntArrayArrayArray12[arg0][local16][local20] == -Static13.anInt437) {
						return false;
					}
				}
			}
			local16 = (arg1 << 7) + 1;
			local20 = (arg3 << 7) + 2;
			@Pc(156) int local156 = Static83.levelHeightMap[arg0][arg1][arg3] + arg5;
			if (!Static256.method4394(local16, local156, local20)) {
				return false;
			}
			@Pc(169) int local169 = (arg2 << 7) - 1;
			if (!Static256.method4394(local169, local156, local20)) {
				return false;
			}
			@Pc(182) int local182 = (arg4 << 7) - 1;
			if (!Static256.method4394(local16, local156, local182)) {
				return false;
			} else if (Static256.method4394(local169, local156, local182)) {
				return true;
			} else {
				return false;
			}
		} else if (CacheArchive.method187(arg0, arg1, arg3)) {
			local16 = arg1 << 7;
			local20 = arg3 << 7;
			return Static256.method4394(local16 + 1, Static83.levelHeightMap[arg0][arg1][arg3] + arg5, local20 + 1) && Static256.method4394(local16 + 128 - 1, Static83.levelHeightMap[arg0][arg1 + 1][arg3] + arg5, local20 + 1) && Static256.method4394(local16 + 128 - 1, Static83.levelHeightMap[arg0][arg1 + 1][arg3 + 1] + arg5, local20 + 128 - 1) && Static256.method4394(local16 + 1, Static83.levelHeightMap[arg0][arg1][arg3 + 1] + arg5, local20 + 128 - 1);
		} else {
			return false;
		}
	}
}
