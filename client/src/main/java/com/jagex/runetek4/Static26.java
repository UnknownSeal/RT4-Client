package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static26 {

	@OriginalMember(owner = "client!ca", name = "cb", descriptor = "Lclient!na;")
	public static final JString aClass100_160 = JString.getNbsp();

	@OriginalMember(owner = "client!ca", name = "fb", descriptor = "[I")
	public static final int[] anIntArray68 = new int[50];

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(IIII)V")
	public static void method744(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (Static125.anInt3104 == 0 || arg0 == 0 || SoundPlayer.size >= 50 || arg1 == -1) {
			return;
		}
		Static200.anIntArray421[SoundPlayer.size] = arg1;
		Static276.anIntArray563[SoundPlayer.size] = arg0;
		Static164.anIntArray362[SoundPlayer.size] = arg2;
		Static173.aClass138Array1[SoundPlayer.size] = null;
		anIntArray68[SoundPlayer.size] = 0;
		SoundPlayer.size++;
	}
}
