package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.enumtype.EnumType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static253 {

	@OriginalMember(owner = "runetek4.client!ui", name = "Q", descriptor = "I")
	public static int anInt5526;

	@OriginalMember(owner = "runetek4.client!ui", name = "R", descriptor = "I")
	public static int anInt5527;

	@OriginalMember(owner = "runetek4.client!ui", name = "T", descriptor = "F")
	public static float aFloat36;

	@OriginalMember(owner = "runetek4.client!ui", name = "cb", descriptor = "Lclient!ve;")
	public static Js5 aClass153_104;

	@OriginalMember(owner = "runetek4.client!ui", name = "eb", descriptor = "[[[B")
	public static byte[][][] levelTileUnderlayIds;

	@OriginalMember(owner = "runetek4.client!ui", name = "mb", descriptor = "F")
	public static float aFloat37;

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IIZIII)V")
	public static void drawScene(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		Static136.anInt3325++;
		Static210.method3711();
		if (!arg1) {
			Static38.pushPlayers(true);
			Static173.pushNpcs(true);
			Static38.pushPlayers(false);
		}
		Static173.pushNpcs(false);
		if (!arg1) {
			Static195.pushProjectiles();
		}
		Static246.pushSpotanims();
		if (GlRenderer.enabled) {
			Static115.method2314(arg3, arg4, arg0, arg2, true);
			arg2 = VarpDefinition.anInt983;
			arg4 = Static24.anInt773;
			arg3 = Static166.anInt4055;
			arg0 = Static245.anInt5377;
		}
		@Pc(59) int pitch;
		@Pc(57) int local57;
		if (Static227.anInt5096 == 1) {
			local57 = Static230.cameraAnticheatAngle + Static57.orbitCameraYaw & 0x7FF;
			pitch = Static72.orbitCameraPitch;
			if (pitch < Static234.cameraPitchClamp / 256) {
				pitch = Static234.cameraPitchClamp / 256;
			}
			if (Static176.cameraModifierEnabled[4] && Static276.cameraModifierWobbleScale[4] + 128 > pitch) {
				pitch = Static276.cameraModifierWobbleScale[4] + 128;
			}
			Static18.orbitCamera(Static81.anInt2223, arg0, Static207.getHeightmapY(Static55.currentLevel, Static173.localPlayer.x, Static173.localPlayer.z) - 50, 600 - -(pitch * 3), local57, Static111.anInt2900, pitch);
		}
		local57 = Static5.cameraY;
		pitch = Static138.cameraX;
		@Pc(121) int cameraZ = Static134.cameraZ;
		@Pc(123) int cameraPitch = Static240.cameraPitch;
		@Pc(125) int cameraYaw = Static184.cameraYaw;
		@Pc(127) int type;
		@Pc(171) int jitter;
		for (type = 0; type < 5; type++) {
			if (Static176.cameraModifierEnabled[type]) {
				jitter = (int) ((double) -Static222.cameraModifierJitter[type] + (double) (Static222.cameraModifierJitter[type] * 2 + 1) * Math.random() + Math.sin((double) Static31.cameraModifierCycle[type] * ((double) Static202.cameraModifierWobbleSpeed[type] / 100.0D)) * (double) Static276.cameraModifierWobbleScale[type]);
				if (type == 3) {
					Static184.cameraYaw = jitter + Static184.cameraYaw & 0x7FF;
				}
				if (type == 4) {
					Static240.cameraPitch += jitter;
					if (Static240.cameraPitch < 128) {
						Static240.cameraPitch = 128;
					}
					if (Static240.cameraPitch > 383) {
						Static240.cameraPitch = 383;
					}
				}
				if (type == 2) {
					Static134.cameraZ += jitter;
				}
				if (type == 1) {
					Static5.cameraY += jitter;
				}
				if (type == 0) {
					Static138.cameraX += jitter;
				}
			}
		}
		Static252.method4302();
		if (GlRenderer.enabled) {
			Static46.method1187(arg2, arg4, arg2 + arg3, arg4 - -arg0);
			@Pc(248) float local248 = (float) Static240.cameraPitch * 0.17578125F;
			@Pc(253) float local253 = (float) Static184.cameraYaw * 0.17578125F;
			if (Static227.anInt5096 == 3) {
				local248 = Static146.aFloat15 * 360.0F / 6.2831855F;
				local253 = Static84.aFloat10 * 360.0F / 6.2831855F;
			}
			GlRenderer.method4171(arg2, arg4, arg3, arg0, arg3 / 2 + arg2, arg4 - -(arg0 / 2), local248, local253, Static223.anInt5029, Static223.anInt5029);
		} else {
			Static129.method2496(arg2, arg4, arg3 + arg2, arg0 + arg4);
			Pix3D.method1908();
		}
		if (Static60.aBoolean108 || Static155.anInt3751 < arg2 || Static155.anInt3751 >= arg3 + arg2 || arg4 > Static60.anInt1892 || arg0 + arg4 <= Static60.anInt1892) {
			Static39.aBoolean77 = false;
			Static2.anInt7 = 0;
		} else {
			Static39.aBoolean77 = true;
			Static2.anInt7 = 0;
			jitter = Static247.anInt5405;
			@Pc(344) int local344 = Static1.anInt4;
			type = Static240.anInt5334;
			Static150.anInt3582 = type + (jitter - type) * (-arg2 + Static155.anInt3751) / arg3;
			@Pc(361) int local361 = Static148.anInt3535;
			Static34.anInt1053 = (local361 - local344) * (Static60.anInt1892 - arg4) / arg0 + local344;
		}
		Static107.method2261();
		@Pc(387) byte local387 = Static236.method4047() == 2 ? (byte) Static136.anInt3325 : 1;
		if (GlRenderer.enabled) {
			GlRenderer.method4173();
			GlRenderer.setDepthTestEnabled(true);
			GlRenderer.setFogEnabled(true);
			if (Static244.gamestate == 10) {
				jitter = Static103.method2235(Static178.sceneDelta, Static134.cameraZ >> 10, Static113.anInt4609, Static138.cameraX >> 10);
			} else {
				jitter = Static103.method2235(Static178.sceneDelta, Static173.localPlayer.pathTileZ[0] >> 3, Static113.anInt4609, Static173.localPlayer.pathTileX[0] >> 3);
			}
			Static120.method2394(Static83.loopCycle, !Static11.aBoolean15);
			GlRenderer.clearColorAndDepthBuffers(jitter);
			Static143.method2731(Static240.cameraPitch, Static134.cameraZ, Static5.cameraY, Static138.cameraX, Static184.cameraYaw);
			GlRenderer.anInt5323 = Static83.loopCycle;
			Static156.method2954(Static138.cameraX, Static5.cameraY, Static134.cameraZ, Static240.cameraPitch, Static184.cameraYaw, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, Static55.currentLevel + 1, local387, Static173.localPlayer.x >> 7, Static173.localPlayer.z >> 7);
			Static263.aBoolean299 = true;
			Static120.method2390();
			Static143.method2731(0, 0, 0, 0, 0);
			Static107.method2261();
			Static223.method3858();
			Static142.method2726(arg4, arg3, arg2, Static223.anInt5029, arg0, Static223.anInt5029);
			Static233.method4000(arg3, arg2, arg0, Static223.anInt5029, Static223.anInt5029, arg4);
		} else {
			Static129.method2495(arg2, arg4, arg3, arg0, 0);
			Static156.method2954(Static138.cameraX, Static5.cameraY, Static134.cameraZ, Static240.cameraPitch, Static184.cameraYaw, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, Static55.currentLevel + 1, local387, Static173.localPlayer.x >> 7, Static173.localPlayer.z >> 7);
			Static107.method2261();
			Static223.method3858();
			Static142.method2726(arg4, arg3, arg2, 256, arg0, 256);
			Static233.method4000(arg3, arg2, arg0, 256, 256, arg4);
		}
		((Js5GlTextureProvider) Pix3D.anInterface1_2).method3239(Static178.sceneDelta);
		Static115.method2310(arg3, arg4, arg0, arg2);
		Static240.cameraPitch = cameraPitch;
		Static134.cameraZ = cameraZ;
		Static5.cameraY = local57;
		Static138.cameraX = pitch;
		Static184.cameraYaw = cameraYaw;
		if (Static19.aBoolean43 && client.js5NetQueue.getUrgentRequestCount() == 0) {
			Static19.aBoolean43 = false;
		}
		if (Static19.aBoolean43) {
			if (GlRenderer.enabled) {
				Static46.method1186(arg2, arg4, arg3, arg0, 0);
			} else {
				Static129.method2495(arg2, arg4, arg3, arg0, 0);
			}
			Static114.method4636(false, LocalizedText.LOADING);
		}
		if (!arg1 && !Static19.aBoolean43 && !Static60.aBoolean108 && arg2 <= Static155.anInt3751 && arg3 + arg2 > Static155.anInt3751 && arg4 <= Static60.anInt1892 && arg0 + arg4 > Static60.anInt1892) {
			Static176.method3304(arg4, arg3, arg0, arg2, Static60.anInt1892, Static155.anInt3751);
		}
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "c", descriptor = "(II)I")
	public static int method4328(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IZ)Lclient!ml;")
	public static EnumType get(@OriginalArg(0) int id) {
		@Pc(10) EnumType enumType = (EnumType) Static149.aClass54_10.get((long) id);
		if (enumType != null) {
			return enumType;
		}
		@Pc(24) byte[] bytes = Static84.aClass153_35.getfile(Static97.method1959(id), Static103.method2236(id));
		enumType = new EnumType();
		if (bytes != null) {
			enumType.decode(new Packet(bytes));
		}
		Static149.aClass54_10.put(enumType, (long) id);
		return enumType;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "h", descriptor = "(I)[Lclient!ok;")
	public static IndexedSprite[] method4331() {
		@Pc(8) IndexedSprite[] local8 = new IndexedSprite[Static165.anInt4038];
		for (@Pc(10) int local10 = 0; local10 < Static165.anInt4038; local10++) {
			if (GlRenderer.enabled) {
				local8[local10] = new GlIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], VarpDefinition.aByteArrayArray5[local10], Static259.anIntArray513);
			} else {
				local8[local10] = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], VarpDefinition.aByteArrayArray5[local10], Static259.anIntArray513);
			}
		}
		Static75.method1631();
		return local8;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "d", descriptor = "(II)V")
	public static void method4332(@OriginalArg(0) int arg0) {
		if (arg0 >= 0 && Static258.aBooleanArray130.length > arg0) {
			Static258.aBooleanArray130[arg0] = !Static258.aBooleanArray130[arg0];
		}
	}
}
