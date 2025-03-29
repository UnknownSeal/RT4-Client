package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static227 {

	@OriginalMember(owner = "runetek4.client!sg", name = "h", descriptor = "I")
	public static int anInt5091;

	@OriginalMember(owner = "runetek4.client!sg", name = "a", descriptor = "(B)V")
	public static void removeSoft() {
		Static142.animationSequenceCache.removeSoft();
		Static267.skeletonCache.removeSoft();
	}
}
