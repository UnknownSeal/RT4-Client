package com.jagex.runetek4;

import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static31 {

	@OriginalMember(owner = "client!ch", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_193 = JString.parse(":");

	@OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
	public static final int[] cameraModifierCycle = new int[5];

	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] anIntArrayArray6 = new int[104][104];

	@OriginalMember(owner = "client!ch", name = "c", descriptor = "(I)V")
	public static void method846() {
		if (!SceneGraph.allLevelsvisible() && SceneGraph.centralPlane != Player.plane) {
			LoginManager.method2463(Player.plane, SceneGraph.centralZoneZ, SceneGraph.centralZoneX, PlayerList.self.movementQueueZ[0], false, PlayerList.self.movementQueueX[0]);
		} else if (Player.plane != LightingManager.anInt2875 && MiniMap.drawMap(Player.plane)) {
			LightingManager.anInt2875 = Player.plane;
			ClientScriptRunner.method2218();
		}
	}

}
