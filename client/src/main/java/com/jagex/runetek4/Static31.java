package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static31 {

	@OriginalMember(owner = "client!ch", name = "w", descriptor = "I")
	public static int anInt987;

	@OriginalMember(owner = "client!ch", name = "x", descriptor = "Lclient!na;")
	public static final JString aClass100_193 = JString.parse(":");

	@OriginalMember(owner = "client!ch", name = "z", descriptor = "[I")
	public static final int[] cameraModifierCycle = new int[5];

	@OriginalMember(owner = "client!ch", name = "C", descriptor = "[[I")
	public static final int[][] anIntArrayArray6 = new int[104][104];

	@OriginalMember(owner = "client!ch", name = "c", descriptor = "(I)V")
	public static void method846() {
		if (!Static138.allLevelsvisible() && Static41.anInt1316 != Player.plane) {
			Static127.method2463(Player.plane, Static52.anInt1695, Static80.anInt4701, PlayerList.self.movementQueueZ[0], false, PlayerList.self.movementQueueX[0]);
		} else if (Player.plane != LightingManager.anInt2875 && Static137.method2665(Player.plane)) {
			LightingManager.anInt2875 = Player.plane;
			Static269.method2218();
		}
	}

}
