package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static26 {

	@OriginalMember(owner = "client!ca", name = "Y", descriptor = "[I")
	public static int[] anIntArray66;

	@OriginalMember(owner = "client!ca", name = "cb", descriptor = "Lclient!na;")
	public static final JString aClass100_160 = Static165.method3165();

	@OriginalMember(owner = "client!ca", name = "db", descriptor = "I")
	public static int anInt865 = 0;

	@OriginalMember(owner = "client!ca", name = "fb", descriptor = "[I")
	public static final int[] anIntArray68 = new int[50];

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(Z)V")
	public static void removeSoft() {
		Static79.aClass99_11.removeSoft();
		aClass6.aClass99_5.removeSoft();
	}

	@OriginalMember(owner = "client!ca", name = "a", descriptor = "(ZI)V")
	public static void method743(@OriginalArg(0) boolean arg0) {
		@Pc(13) int local13 = Static273.aByteArrayArray13.length;
		@Pc(19) byte[][] local19;
		if (GlRenderer.enabled && arg0) {
			local19 = Static19.aByteArrayArray4;
		} else {
			local19 = Static156.aByteArrayArray11;
		}
		for (@Pc(25) int local25 = 0; local25 < local13; local25++) {
			@Pc(32) byte[] local32 = local19[local25];
			if (local32 != null) {
				@Pc(45) int local45 = (Static238.anIntArray470[local25] >> 8) * 64 - Camera.originX;
				@Pc(56) int local56 = (Static238.anIntArray470[local25] & 0xFF) * 64 - Camera.originZ;
				client.audioLoop();
				Static124.method2437(local45, arg0, local32, local56, PathFinder.collisionMaps);
			}
		}
	}

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
