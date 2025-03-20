package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

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

	@OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "(I)V")
	public static void method2744() {
		@Pc(3) int local3 = InterfaceList.anInt5138;
		@Pc(9) int local9 = InterfaceList.anInt761;
		@Pc(11) int local11 = InterfaceList.anInt4271;
		@Pc(15) int local15 = InterfaceList.anInt436;
		if (GlRenderer.enabled) {
			GlRaster.fillRect(local11, local3, local9, local15, 6116423);
			GlRaster.fillRect(local11 + 1, local3 + 1, local9 - 2, 16, 0);
			GlRaster.drawRect(local11 + 1, local3 + 18, local9 - 2, local15 + -19, 0);
		} else {
			SoftwareRaster.fillRect(local11, local3, local9, local15, 6116423);
			SoftwareRaster.fillRect(local11 + 1, local3 + 1, local9 - 2, 16, 0);
			SoftwareRaster.drawRect(local11 + 1, local3 + 18, local9 - 2, local15 + -19, 0);
		}
		Fonts.b12Full.renderLeft(LocalizedText.CHOOSE_OPTION, local11 + 3, local3 + 14, 6116423, -1);
		@Pc(96) int local96 = Mouse.lastMouseY;
		@Pc(98) int local98 = Mouse.lastMouseX;
		for (@Pc(107) int local107 = 0; local107 < MiniMenu.menuActionRow; local107++) {
			@Pc(127) int local127 = (MiniMenu.menuActionRow - local107 - 1) * 15 + local3 + 31;
			@Pc(129) int local129 = 16777215;
			if (local11 < local98 && local98 < local11 + local9 && local127 - 13 < local96 && local96 < local127 + 3) {
				local129 = 16776960;
			}
			Fonts.b12Full.renderLeft(Static269.method2228(local107), local11 + 3, local127, local129, 0);
		}
		InterfaceList.forceRedrawScreen(InterfaceList.anInt4271, InterfaceList.anInt5138, InterfaceList.anInt436, InterfaceList.anInt761);
	}

	@OriginalMember(owner = "runetek4.client!lf", name = "c", descriptor = "(I)I")
	public static int method2746() {
		return ((Preferences.stereo ? 1 : 0) << 19) + (((Preferences.fogEnabled ? 1 : 0) << 16) + ((Static220.aBoolean244 ? 1 : 0) << 15) + ((Preferences.highDetailLighting ? 1 : 0) << 13) + ((Static209.aBoolean240 ? 1 : 0) << 10) + ((Static159.aBoolean189 ? 1 : 0) << 9) + ((Static15.lowMemory ? 1 : 0) << 7) + ((Preferences.highDetailTextures ? 1 : 0) << 6) + ((Preferences.groundDecoration ? 1 : 0) << 5) + (((Static162.aBoolean190 ? 1 : 0) << 3) + (Preferences.brightness & 0x7) - (-((Preferences.roofsVisible ? 1 : 0) << 4) + -((Static11.aBoolean15 ? 1 : 0) << 8)) - (-((Static139.anInt3451 & 0x3) << 11) + -((Static125.anInt3104 == 0 ? 0 : 1) << 20) - (((Static12.anInt391 == 0 ? 0 : 1) << 21) + ((Preferences.ambientSoundsVolume == 0 ? 0 : 1) << 22)))) + (Preferences.getParticleSetting() << 23));
	}
}
