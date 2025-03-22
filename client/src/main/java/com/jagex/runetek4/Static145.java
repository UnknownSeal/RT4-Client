package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static145 {

	@OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "[I")
	public static int[] anIntArray330;

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "[[I")
	public static final int[][] anIntArrayArray25 = new int[104][104];

	@OriginalMember(owner = "runetek4.client!lf", name = "c", descriptor = "Lclient!ih;")
	public static final LinkedList aClass69_84 = new LinkedList();

	@OriginalMember(owner = "runetek4.client!lf", name = "d", descriptor = "[I")
	public static final int[] anIntArray331 = new int[1000];

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(I)V")
	public static void method2742() {
		if (client.gameState == 10 && GlRenderer.enabled) {
			client.processGameStatus(28);
		}
		if (client.gameState == 30) {
			client.processGameStatus(25);
		}
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "c", descriptor = "(I)I")
	public static int method2746() {
		return ((Preferences.stereo ? 1 : 0) << 19) + (((Preferences.fogEnabled ? 1 : 0) << 16) + ((Preferences.highWaterDetail ? 1 : 0) << 15) + ((Preferences.highDetailLighting ? 1 : 0) << 13) + ((Static209.aBoolean240 ? 1 : 0) << 10) + ((Static159.aBoolean189 ? 1 : 0) << 9) + ((Static15.lowMemory ? 1 : 0) << 7) + ((Preferences.highDetailTextures ? 1 : 0) << 6) + ((Preferences.showGroundDecorations ? 1 : 0) << 5) + (((Static162.aBoolean190 ? 1 : 0) << 3) + (Preferences.brightness & 0x7) - (-((Preferences.roofsVisible ? 1 : 0) << 4) + -((Static11.aBoolean15 ? 1 : 0) << 8)) - (-((Static139.anInt3451 & 0x3) << 11) + -((Static125.anInt3104 == 0 ? 0 : 1) << 20) - (((Preferences.musicVolume == 0 ? 0 : 1) << 21) + ((Preferences.ambientSoundsVolume == 0 ? 0 : 1) << 22)))) + (Preferences.getParticleSetting() << 23));
	}
}
