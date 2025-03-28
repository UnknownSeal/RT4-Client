package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static26 {

	@OriginalMember(owner = "client!ca", name = "cb", descriptor = "Lclient!na;")
	public static final JString aClass100_160 = JString.getNbsp();

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(IIII)V")
	public static void method744(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		if (Preferences.soundEffectVolume == 0 || arg0 == 0 || SoundPlayer.size >= 50 || arg1 == -1) {
			return;
		}
		SoundPlayer.ids[SoundPlayer.size] = arg1;
		SoundPlayer.loops[SoundPlayer.size] = arg0;
		SoundPlayer.delays[SoundPlayer.size] = arg2;
		SoundPlayer.sounds[SoundPlayer.size] = null;
		SoundPlayer.positions[SoundPlayer.size] = 0;
		SoundPlayer.size++;
	}
}
