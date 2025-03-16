package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.scene.Scenery;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static243 {

	@OriginalMember(owner = "runetek4.client!tk", name = "j", descriptor = "Lclient!ve;")
	public static Js5 aClass153_97;

	@OriginalMember(owner = "runetek4.client!tk", name = "o", descriptor = "Lclient!ok;")
	public static IndexedSprite aClass36_1;

	@OriginalMember(owner = "runetek4.client!tk", name = "s", descriptor = "Lclient!ve;")
	public static Js5 aClass153_98;

	@OriginalMember(owner = "runetek4.client!tk", name = "D", descriptor = "[Lclient!ec;")
	public static Scenery[] aClass31Array3;

	@OriginalMember(owner = "runetek4.client!tk", name = "c", descriptor = "J")
	public static volatile long lastCanvasReplace = 0L;

	@OriginalMember(owner = "runetek4.client!tk", name = "v", descriptor = "I")
	public static int fps = 0;

	@OriginalMember(owner = "runetek4.client!tk", name = "K", descriptor = "[I")
	public static int[] anIntArray476 = new int[2];

	@OriginalMember(owner = "runetek4.client!tk", name = "a", descriptor = "(Lclient!sc;ZLclient!wl;)Lclient!hg;")
	public static Class3_Sub14 method4212(@OriginalArg(0) HashTable arg0, @OriginalArg(2) FloorOverlayType arg1) {
		@Pc(23) long local23 = (long) ((arg1.material + 1 << 16) + arg1.materialscale) + ((long) arg1.priority << 56) + ((long) arg1.waterfogcolour << 32);
		@Pc(38) Class3_Sub14 local38 = (Class3_Sub14) arg0.getNode(local23);
		if (local38 == null) {
			local38 = new Class3_Sub14(arg1.material, (float) arg1.materialscale, true, false, arg1.waterfogcolour);
			arg0.put(local38, local23);
		}
		return local38;
	}

	@OriginalMember(owner = "runetek4.client!tk", name = "a", descriptor = "(Z)V")
	public static void method4221() {
		Static204.anInt4762 = 0;
		Static67.prevFocus = true;
		Static183.prevClickTime = 0L;
		Game.mouseCapturer.coord = 0;
		Static26.focus = true;
		Static114.method4625();
		Static49.anInt1462 = -1;
		Static5.anInt45 = -1;
		Static164.packetType = -1;
		Game.idleTimeout = 0;
		Static60.systemUpdateTimer = 0;
		Static6.outboundBuffer.position = 0;
		Static230.anInt5152 = -1;
		Static201.idleNetCycles = 0;
		Static57.in.position = 0;
		@Pc(3506) int i;
		for (i = 0; i < Static143.aClass102Array1.length; i++) {
			Static143.aClass102Array1[i] = null;
		}
		PreciseSleep.menuActionRow = 0;
		Static60.aBoolean108 = false;
		Static48.setIdleLoops(0);
		for (i = 0; i < 100; i++) {
			Static230.messageText[i] = null;
		}
		Static260.anInt5014 = 0;
		Static132.cameraAnticheatOffsetX = (int) (Math.random() * 100.0D) - 50;
		Static84.anInt2255 = 0;
		Static57.orbitCameraYaw = (int) (Math.random() * 20.0D) - 10 & 0x7FF;
		Static107.anInt2875 = -1;
		Static267.playerCount = 0;
		Static270.anInt5795 = 0;
		Static206.cameraAnticheatOffsetZ = (int) (Math.random() * 110.0D) - 55;
		Static241.aBoolean302 = false;
		Static273.minimapZoom = (int) (Math.random() * 30.0D) - 20;
		Static189.anInt4451 = 0;
		Static115.anInt2939 = 0;
		Static59.minimapAnticheatAngle = (int) (Math.random() * 120.0D) - 60;
		Static62.anInt1941 = 0;
		Static230.cameraAnticheatAngle = (int) (Math.random() * 80.0D) - 40;
		Static272.npcCount = 0;
		for (i = 0; i < 2048; i++) {
			Static159.players[i] = null;
			Static115.playerAppearanceBuffer[i] = null;
		}
		for (i = 0; i < 32768; i++) {
			NpcList.npcs[i] = null;
		}
		PlayerList.self = Static159.players[2047] = new Player();
		Static217.projectiles.clear();
		Static99.spotanims.clear();
		if (Static159.levelObjStacks != null) {
			for (i = 0; i < 4; i++) {
				for (@Pc(3663) int local3663 = 0; local3663 < 104; local3663++) {
					for (@Pc(3670) int local3670 = 0; local3670 < 104; local3670++) {
						Static159.levelObjStacks[i][local3663][local3670] = null;
					}
				}
			}
		}
		Static26.spawnedLocations = new LinkedList();
		Static166.anInt4054 = 0;
		CacheArchive.friendCount = 0;
		Static8.resetVarBits();
		Static20.method601();
		Static133.anInt5230 = 0;
		ClientScriptRunner.anInt5217 = 0;
		Static265.anInt5765 = 0;
		PreciseSleep.anInt5203 = 0;
		Static248.anInt4232 = 0;
		Static245.anInt5375 = 0;
		Static57.anInt1744 = 0;
		Static251.anInt5449 = 0;
		Static113.anInt4612 = 0;
		ClientScriptRunner.anInt5225 = 0;
		for (i = 0; i < VarPlayerDefinition.varcs.length; i++) {
			VarPlayerDefinition.varcs[i] = -1;
		}
		if (Static154.topLevelInterace != -1) {
			Component.resetComponent(Static154.topLevelInterace);
		}
		for (@Pc(3755) Class3_Sub31 local3755 = (Class3_Sub31) Static119.aClass133_9.head(); local3755 != null; local3755 = (Class3_Sub31) Static119.aClass133_9.prev()) {
			Static132.method2605(true, local3755);
		}
		Static154.topLevelInterace = -1;
		Static119.aClass133_9 = new HashTable(8);
		Component.createComponentMemoryBuffer();
		Static39.aClass13_10 = null;
		Static60.aBoolean108 = false;
		PreciseSleep.menuActionRow = 0;
		Static134.A_PLAYER_MODEL___2.method1950(new int[] { 0, 0, 0, 0, 0 }, -1, false, null, -1);
		for (i = 0; i < 8; i++) {
			Static160.aClass100Array121[i] = null;
			Static1.aBooleanArray1[i] = false;
			Static191.anIntArray388[i] = -1;
		}
		Static102.method2073();
		Static19.aBoolean43 = true;
		for (i = 0; i < 100; i++) {
			Static186.aBooleanArray100[i] = true;
		}
		Static214.anInt5577 = 0;
		Static199.aClass3_Sub22Array1 = null;
		Static15.aClass100_87 = null;
		for (i = 0; i < 6; i++) {
			Static229.aClass136Array1[i] = new StockMarketOffer();
		}
		for (i = 0; i < 25; i++) {
			Static99.skillLevel[i] = 0;
			Static141.skillBaseLevel[i] = 0;
			Static227.skillExperience[i] = 0;
		}
		if (GlRenderer.enabled) {
			Static86.method1799();
		}
		Static197.aBoolean228 = true;
		Static189.anInt4443 = 0;
		Static195.aClass100_859 = LocalizedText.WALKHERE;
		Static127.aBoolean160 = false;
		Static259.aShortArray88 = Static62.aShortArray19 = Static232.aShortArray74 = Static259.aShortArray87 = new short[256];
		Static114.method4637();
		Static261.aBoolean298 = false;
		Static59.method1373();
	}
}
