package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.enumtype.EnumType;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.media.renderable.actor.Player;
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

	@OriginalMember(owner = "runetek4.client!ui", name = "eb", descriptor = "[[[B")
	public static byte[][][] levelTileUnderlayIds;

	@OriginalMember(owner = "runetek4.client!ui", name = "mb", descriptor = "F")
	public static float aFloat37;

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IIZIII)V")
	public static void drawScene(@OriginalArg(1) int arg0, @OriginalArg(2) boolean arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		Static136.anInt3325++;
		Static210.method3711();
		if (!arg1) {
			Game.pushPlayers(true);
			Game.pushNpcs(true);
			Game.pushPlayers(false);
		}
		Game.pushNpcs(false);
		if (!arg1) {
			Game.pushProjectiles();
		}
		Game.pushSpotanims();
		if (GlRenderer.enabled) {
			Static115.method2314(arg3, arg4, arg0, arg2, true);
			arg2 = aClass6.anInt983;
			arg4 = Static24.anInt773;
			arg3 = Static166.anInt4055;
			arg0 = Static245.anInt5377;
		}
		@Pc(59) int pitch;
		@Pc(57) int local57;
		if (Camera.cameraType == 1) {
			local57 = Camera.cameraAnticheatAngle + Camera.orbitCameraYaw & 0x7FF;
			pitch = Camera.orbitCameraPitch;
			if (pitch < Camera.cameraPitchClamp / 256) {
				pitch = Camera.cameraPitchClamp / 256;
			}
			if (Camera.cameraModifierEnabled[4] && Camera.cameraAmplitude[4] + 128 > pitch) {
				pitch = Camera.cameraAmplitude[4] + 128;
			}
			Camera.orbitCamera(Camera.cameraX, arg0, SceneGraph.getTileHeight(Player.plane, PlayerList.self.xFine, PlayerList.self.zFine) - 50, 600 - -(pitch * 3), local57, Camera.cameraZ, pitch);
		}
		local57 = Camera.cameraY;
		pitch = Camera.renderX;
		@Pc(121) int cameraZ = Camera.renderZ;
		@Pc(123) int cameraPitch = Camera.cameraPitch;
		@Pc(125) int cameraYaw = Camera.cameraYaw;
		@Pc(127) int type;
		@Pc(171) int jitter;
		for (type = 0; type < 5; type++) {
			if (Camera.cameraModifierEnabled[type]) {
				jitter = (int) ((double) -Camera.cameraModifierJitter[type] + (double) (Camera.cameraModifierJitter[type] * 2 + 1) * Math.random() + Math.sin((double) Static31.cameraModifierCycle[type] * ((double) Camera.cameraFrequency[type] / 100.0D)) * (double) Camera.cameraAmplitude[type]);
				if (type == 3) {
					Camera.cameraYaw = jitter + Camera.cameraYaw & 0x7FF;
				}
				if (type == 4) {
					Camera.cameraPitch += jitter;
					if (Camera.cameraPitch < 128) {
						Camera.cameraPitch = 128;
					}
					if (Camera.cameraPitch > 383) {
						Camera.cameraPitch = 383;
					}
				}
				if (type == 2) {
					Camera.renderZ += jitter;
				}
				if (type == 1) {
					Camera.cameraY += jitter;
				}
				if (type == 0) {
					Camera.renderX += jitter;
				}
			}
		}
		FloTypeList.method4302();
		if (GlRenderer.enabled) {
			Static46.method1187(arg2, arg4, arg2 + arg3, arg4 - -arg0);
			@Pc(248) float local248 = (float) Camera.cameraPitch * 0.17578125F;
			@Pc(253) float local253 = (float) Camera.cameraYaw * 0.17578125F;
			if (Camera.cameraType == 3) {
				local248 = Camera.aFloat15 * 360.0F / 6.2831855F;
				local253 = Camera.aFloat10 * 360.0F / 6.2831855F;
			}
			GlRenderer.method4171(arg2, arg4, arg3, arg0, arg3 / 2 + arg2, arg4 - -(arg0 / 2), local248, local253, Static223.anInt5029, Static223.anInt5029);
		} else {
			Rasterizer.setBounds(arg2, arg4, arg3 + arg2, arg0 + arg4);
			Pix3D.method1908();
		}
		if (ClientScriptRunner.aBoolean108 || Static155.anInt3751 < arg2 || Static155.anInt3751 >= arg3 + arg2 || arg4 > Static60.anInt1892 || arg0 + arg4 <= Static60.anInt1892) {
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
		client.audioLoop();
		@Pc(387) byte local387 = Static236.method4047() == 2 ? (byte) Static136.anInt3325 : 1;
		if (GlRenderer.enabled) {
			GlRenderer.method4173();
			GlRenderer.setDepthTestEnabled(true);
			GlRenderer.setFogEnabled(true);
			if (client.gameState == 10) {
				jitter = Static103.method2235(Protocol.sceneDelta, Camera.renderZ >> 10, Static113.brightness, Camera.renderX >> 10);
			} else {
				jitter = Static103.method2235(Protocol.sceneDelta, PlayerList.self.movementQueueZ[0] >> 3, Static113.brightness, PlayerList.self.movementQueueX[0] >> 3);
			}
			Static120.method2394(client.loop, !Static11.aBoolean15);
			GlRenderer.clearColorAndDepthBuffers(jitter);
			Static143.method2731(Camera.cameraPitch, Camera.renderZ, Camera.cameraY, Camera.renderX, Camera.cameraYaw);
			GlRenderer.anInt5323 = client.loop;
			Static156.method2954(Camera.renderX, Camera.cameraY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, Player.plane + 1, local387, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
			Static263.aBoolean299 = true;
			Static120.method2390();
			Static143.method2731(0, 0, 0, 0, 0);
			client.audioLoop();
			Static223.method3858();
			Static142.method2726(arg4, arg3, arg2, Static223.anInt5029, arg0, Static223.anInt5029);
			ClientScriptRunner.method4000(arg3, arg2, arg0, Static223.anInt5029, Static223.anInt5029, arg4);
		} else {
			Rasterizer.drawFilledRectangle(arg2, arg4, arg3, arg0, 0);
			Static156.method2954(Camera.renderX, Camera.cameraY, Camera.renderZ, Camera.cameraPitch, Camera.cameraYaw, Static266.aByteArrayArrayArray15, Static79.anIntArray205, Static149.anIntArray338, Static267.anIntArray518, Static50.anIntArray134, Static243.anIntArray476, Player.plane + 1, local387, PlayerList.self.xFine >> 7, PlayerList.self.zFine >> 7);
			client.audioLoop();
			Static223.method3858();
			Static142.method2726(arg4, arg3, arg2, 256, arg0, 256);
			ClientScriptRunner.method4000(arg3, arg2, arg0, 256, 256, arg4);
		}
		((Js5GlTextureProvider) Rasterizer.textureProvider).method3239(Protocol.sceneDelta);
		Static115.method2310(arg3, arg4, arg0, arg2);
		Camera.cameraPitch = cameraPitch;
		Camera.renderZ = cameraZ;
		Camera.cameraY = local57;
		Camera.renderX = pitch;
		Camera.cameraYaw = cameraYaw;
		if (ClientScriptRunner.aBoolean43 && client.js5NetQueue.getUrgentRequestCount() == 0) {
			ClientScriptRunner.aBoolean43 = false;
		}
		if (ClientScriptRunner.aBoolean43) {
			if (GlRenderer.enabled) {
				Static46.method1186(arg2, arg4, arg3, arg0, 0);
			} else {
				Rasterizer.drawFilledRectangle(arg2, arg4, arg3, arg0, 0);
			}
			Fonts.drawTextOnScreen(false, LocalizedText.LOADING);
		}
		if (!arg1 && !ClientScriptRunner.aBoolean43 && !ClientScriptRunner.aBoolean108 && arg2 <= Static155.anInt3751 && arg3 + arg2 > Static155.anInt3751 && arg4 <= Static60.anInt1892 && arg0 + arg4 > Static60.anInt1892) {
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
				local8[local10] = new GlIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], aClass6.aByteArrayArray5[local10], Static259.anIntArray513);
			} else {
				local8[local10] = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], aClass6.aByteArrayArray5[local10], Static259.anIntArray513);
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
