package com.jagex.runetek4;

import java.awt.event.KeyEvent;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static136 {

	@OriginalMember(owner = "runetek4.client!kk", name = "i", descriptor = "I")
	public static int anInt3324;

	@OriginalMember(owner = "runetek4.client!kk", name = "g", descriptor = "I")
	public static int logoId = -1;

	@OriginalMember(owner = "runetek4.client!kk", name = "j", descriptor = "I")
	public static int anInt3325 = 0;

	@OriginalMember(owner = "runetek4.client!kk", name = "m", descriptor = "Lclient!na;")
	public static final JString aClass100_633 = JString.parse("Clientscript error )2 check log for details");

	@OriginalMember(owner = "runetek4.client!kk", name = "a", descriptor = "(ZLjava/awt/event/KeyEvent;)I")
	public static int method2650(@OriginalArg(1) KeyEvent arg0) {
		@Pc(6) int local6 = arg0.getKeyChar();
		if (local6 == 8364) {
			return 128;
		} else {
			if (local6 <= 0 || local6 >= 256) {
				local6 = -1;
			}
			return local6;
		}
	}

	@OriginalMember(owner = "runetek4.client!kk", name = "b", descriptor = "(Lclient!wa;I)V")
	public static void method2654(@OriginalArg(0) Packet arg0) {
		if (arg0.data.length - arg0.offset < 1) {
			return;
		}
		@Pc(21) int local21 = arg0.g1();
		if (local21 < 0 || local21 > 11) {
			return;
		}
		@Pc(34) byte local34;
		if (local21 == 11) {
			local34 = 33;
		} else if (local21 == 10) {
			local34 = 32;
		} else if (local21 == 9) {
			local34 = 31;
		} else if (local21 == 8) {
			local34 = 30;
		} else if (local21 == 7) {
			local34 = 29;
		} else if (local21 == 6) {
			local34 = 28;
		} else if (local21 == 5) {
			local34 = 28;
		} else if (local21 == 4) {
			local34 = 24;
		} else if (local21 == 3) {
			local34 = 23;
		} else if (local21 == 2) {
			local34 = 22;
		} else if (local21 == 1) {
			local34 = 23;
		} else {
			local34 = 19;
		}
		if (arg0.data.length - arg0.offset < local34) {
			return;
		}
		Preferences.brightness = arg0.g1();
		if (Preferences.brightness < 1) {
			Preferences.brightness = 1;
		} else if (Preferences.brightness > 4) {
			Preferences.brightness = 4;
		}
		Preferences.setAllLevelsVisible(arg0.g1() == 1);
		Preferences.roofsVisible = arg0.g1() == 1;
		Preferences.groundDecoration = arg0.g1() == 1;
		Preferences.highDetailTextures = arg0.g1() == 1;
		Static15.lowMemory = arg0.g1() == 1;
		Static11.aBoolean15 = arg0.g1() == 1;
		Static159.aBoolean189 = arg0.g1() == 1;
		Static209.aBoolean240 = arg0.g1() == 1;
		Static139.anInt3451 = arg0.g1();
		if (Static139.anInt3451 > 2) {
			Static139.anInt3451 = 2;
		}
		if (local21 < 2) {
			Preferences.highDetailLighting = arg0.g1() == 1;
			arg0.g1();
		} else {
			Preferences.highDetailLighting = arg0.g1() == 1;
		}
		Static220.aBoolean244 = arg0.g1() == 1;
		Preferences.fogEnabled = arg0.g1() == 1;
		Preferences.windowMode = arg0.g1();
		if (Preferences.windowMode > 2) {
			Preferences.windowMode = 2;
		}
		Preferences.antiAliasingMode = Preferences.windowMode;
		Preferences.stereo = arg0.g1() == 1;
		Static125.anInt3104 = arg0.g1();
		if (Static125.anInt3104 > 127) {
			Static125.anInt3104 = 127;
		}
		Static12.anInt391 = arg0.g1();
		Preferences.ambientSoundsVolume = arg0.g1();
		if (Preferences.ambientSoundsVolume > 127) {
			Preferences.ambientSoundsVolume = 127;
		}
		if (local21 >= 1) {
			Preferences.fullScreenWidth = arg0.g2();
			Preferences.fullScreenHeight = arg0.g2();
		}
		if (local21 >= 3 && local21 < 6) {
			arg0.g1();
		}
		if (local21 >= 4) {
			@Pc(386) int local386 = arg0.g1();
			if (GameShell.maxMemory < 96) {
				local386 = 0;
			}
			Preferences.setParticles(local386);
		}
		if (local21 >= 5) {
			Static164.anInt3988 = arg0.g4();
		}
		if (local21 >= 6) {
			Preferences.favoriteWorlds = arg0.g1();
		}
		if (local21 >= 7) {
			Preferences.safeMode = arg0.g1() == 1;
		}
		if (local21 >= 8) {
			Preferences.aBoolean63 = arg0.g1() == 1;
		}
		if (local21 >= 9) {
			Preferences.buildArea = arg0.g1();
		}
		if (local21 >= 10) {
			Static127.aBoolean159 = arg0.g1() != 0;
		}
		if (local21 >= 11) {
			Static64.aBoolean111 = arg0.g1() != 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!kk", name = "a", descriptor = "(I)Z")
	public static boolean method2655() {
		return Static14.anInt441 == 0 ? Static172.aClass3_Sub3_Sub4_2.method4414() : true;
	}
}
