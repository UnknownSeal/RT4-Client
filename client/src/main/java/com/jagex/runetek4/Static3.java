package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static3 {

	@OriginalMember(owner = "runetek4.client!ab", name = "b", descriptor = "I")
	public static int anInt5868 = 0;

	@OriginalMember(owner = "runetek4.client!ab", name = "c", descriptor = "[Z")
	public static final boolean[] aBooleanArray135 = new boolean[200];

	@OriginalMember(owner = "runetek4.client!ab", name = "j", descriptor = "[Lclient!na;")
	public static final JString[] scriptStringValues = new JString[1000];

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(Lclient!na;I)V")
	public static void method4656(@OriginalArg(0) JString arg0) {
		@Pc(9) int local9 = Static171.method3218(arg0);
		if (local9 != -1) {
			Static80.method3616(Static203.aMapElementTypeList_1.aShortArray73[local9], Static203.aMapElementTypeList_1.aShortArray72[local9]);
		}
	}

	@OriginalMember(owner = "runetek4.client!ab", name = "b", descriptor = "(B)V")
	public static void method4657() {
		VarPlayerDefinition.varPlayerDefinitionCache.method3103();
	}

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(II)V")
	public static void method4659(@OriginalArg(1) int arg0) {
		if (arg0 == 0) {
			return;
		}
		if (arg0 == 1) {
			LocalizedText.switchToGerman();
		} else if (arg0 == 2) {
			LocalizedText.switchToFrench();
		} else {
			throw new RuntimeException();
		}
	}

	@OriginalMember(owner = "runetek4.client!ab", name = "a", descriptor = "(ZLclient!ve;Lclient!ve;Lclient!ve;Lclient!ve;)V")
	public static void method4661(@OriginalArg(1) CacheArchive arg0, @OriginalArg(2) CacheArchive arg1, @OriginalArg(3) CacheArchive arg2, @OriginalArg(4) CacheArchive arg3) {
		Static23.gameImageCacheArchive = arg1;
		com.jagex.runetek4.cache.CacheArchive.aClass153_64 = arg0;
		com.jagex.runetek4.cache.CacheArchive.gameInterfaceCacheArchive = arg2;
		Static203.aClass153_85 = arg3;
		Component.cachedComponents = new Component[com.jagex.runetek4.cache.CacheArchive.gameInterfaceCacheArchive.capacity()][];
		Static223.loadedComponents = new boolean[com.jagex.runetek4.cache.CacheArchive.gameInterfaceCacheArchive.capacity()];
	}
}
