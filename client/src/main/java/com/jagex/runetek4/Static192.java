package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.Renderable;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.dash3d.entity.Actor;
import com.jagex.runetek4.scene.InteractiveObject;
import com.jagex.runetek4.scene.tile.FloorDecoration;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.scene.tile.WallDecoration;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static192 {

	@OriginalMember(owner = "runetek4.client!ph", name = "b", descriptor = "[[Lclient!li;")
	public static final Environment[][] aClass92ArrayArray1 = new Environment[13][13];

	@OriginalMember(owner = "runetek4.client!ph", name = "a", descriptor = "(B)V")
	public static void loop() {
		while (true) {
			@Pc(17) Class3_Sub8 local17 = (Class3_Sub8) Static128.aClass69_77.removeHead();
			if (local17 == null) {
				return;
			}
			@Pc(40) Actor actor;
			@Pc(29) int pid;
			if (local17.anInt1192 < 0) {
				pid = -local17.anInt1192 - 1;
				if (pid == Static16.localPid) {
					actor = Static173.localPlayer;
				} else {
					actor = Static159.players[pid];
				}
			} else {
				pid = local17.anInt1192 - 1;
				actor = Static175.npcs[pid];
			}
			if (actor != null) {
				@Pc(63) LocType loc = Static271.get(local17.anInt1189);
				if (Static55.currentLevel < 3) {
				}
				@Pc(86) int local86;
				@Pc(83) int local83;
				if (local17.angle == 1 || local17.angle == 3) {
					local83 = loc.width;
					local86 = loc.length;
				} else {
					local86 = loc.width;
					local83 = loc.length;
				}
				@Pc(103) int local103 = (local86 + 1 >> 1) + local17.anInt1190;
				@Pc(110) int local110 = (local86 >> 1) + local17.anInt1190;
				@Pc(117) int local117 = (local83 >> 1) + local17.anInt1204;
				@Pc(126) int local126 = (local83 + 1 >> 1) + local17.anInt1204;
				@Pc(130) int[][] local130 = Static83.levelHeightMap[Static55.currentLevel];
				@Pc(157) int local157 = local130[local103][local126] + local130[local110][local126] + local130[local110][local117] + local130[local103][local117] >> 2;
				@Pc(159) Renderable local159 = null;
				@Pc(164) int local164 = client.locShapeToLayer[local17.anInt1198];
				if (local164 == 0) {
					@Pc(176) Wall local176 = Static262.method4509(Static55.currentLevel, local17.anInt1190, local17.anInt1204);
					if (local176 != null) {
						local159 = local176.modelA;
					}
				} else if (local164 == 1) {
					@Pc(231) WallDecoration local231 = Static83.method435(Static55.currentLevel, local17.anInt1190, local17.anInt1204);
					if (local231 != null) {
						local159 = local231.model;
					}
				} else if (local164 == 2) {
					@Pc(198) InteractiveObject local198 = Static133.method4008(Static55.currentLevel, local17.anInt1190, local17.anInt1204);
					if (local198 != null) {
						local159 = local198.aClass8_4;
					}
				} else if (local164 == 3) {
					@Pc(216) FloorDecoration local216 = Static269.method2210(Static55.currentLevel, local17.anInt1190, local17.anInt1204);
					if (local216 != null) {
						local159 = local216.renderable;
					}
				}
				if (local159 != null) {
					Static29.method800(Static55.currentLevel, local17.anInt1204, 0, local17.anInt1190, local17.anInt1205 + 1, -1, local164, 0, local17.anInt1187 + 1);
					actor.locStopCycle = local17.anInt1205 + Static83.loopCycle;
					actor.locOffsetZ = local83 * 64 + local17.anInt1204 * 128;
					actor.locOffsetX = local86 * 64 + local17.anInt1190 * 128;
					actor.locModel = local159;
					@Pc(292) int local292 = local17.anInt1188;
					actor.locOffsetY = local157;
					actor.locStartCycle = Static83.loopCycle + local17.anInt1187;
					@Pc(304) int local304 = local17.anInt1191;
					@Pc(307) int local307 = local17.anInt1197;
					@Pc(310) int local310 = local17.anInt1200;
					@Pc(316) int local316;
					if (local292 > local304) {
						local316 = local292;
						local292 = local304;
						local304 = local316;
					}
					actor.anInt3411 = local17.anInt1190 + local304;
					if (local307 > local310) {
						local316 = local307;
						local307 = local310;
						local310 = local316;
					}
					actor.anInt3374 = local17.anInt1204 + local307;
					actor.anInt3410 = local310 + local17.anInt1204;
					actor.anInt3384 = local17.anInt1190 + local292;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ph", name = "b", descriptor = "(B)V")
	public static void method3474() {
		Static250.aClass99_33.method3103();
		Static139.aClass99_21.method3103();
	}
}
