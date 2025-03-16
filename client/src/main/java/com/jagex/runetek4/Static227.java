package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static227 {

	@OriginalMember(owner = "runetek4.client!sg", name = "h", descriptor = "I")
	public static int anInt5091;

	@OriginalMember(owner = "runetek4.client!sg", name = "i", descriptor = "Lclient!be;")
	public static Component aClass13_25;

	@OriginalMember(owner = "runetek4.client!sg", name = "k", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_94;

	@OriginalMember(owner = "runetek4.client!sg", name = "o", descriptor = "I")
	public static int anInt5096;

	@OriginalMember(owner = "runetek4.client!sg", name = "p", descriptor = "I")
	public static int anInt5097;

	@OriginalMember(owner = "runetek4.client!sg", name = "b", descriptor = "[I")
	public static final int[] skillExperience = new int[25];

	@OriginalMember(owner = "runetek4.client!sg", name = "c", descriptor = "I")
	public static int anInt5087 = 0;

	@OriginalMember(owner = "runetek4.client!sg", name = "e", descriptor = "Lclient!na;")
	public static final JString aClass100_966 = Static28.parse("settings");

	@OriginalMember(owner = "runetek4.client!sg", name = "q", descriptor = "[I")
	public static final int[] keyCodes = new int[128];

	@OriginalMember(owner = "runetek4.client!sg", name = "a", descriptor = "(B)V")
	public static void method3903() {
		Static142.animationSequenceCache.method3103();
		Static267.skeletonCache.method3103();
	}
}
