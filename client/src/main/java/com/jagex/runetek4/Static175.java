package com.jagex.runetek4;

import com.jagex.runetek4.game.scene.entities.NPCEntity;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static175 {

	@OriginalMember(owner = "runetek4.client!nm", name = "P", descriptor = "[I")
	public static int[] anIntArray371;

	@OriginalMember(owner = "runetek4.client!nm", name = "W", descriptor = "Lclient!na;")
	public static JagString url;

	@OriginalMember(owner = "runetek4.client!nm", name = "S", descriptor = "[Lclient!km;")
	public static final NPCEntity[] npcs = new NPCEntity[32768];

	@OriginalMember(owner = "runetek4.client!nm", name = "U", descriptor = "I")
	public static int anInt4220 = 0;

	@OriginalMember(owner = "runetek4.client!nm", name = "Y", descriptor = "J")
	public static long aLong138 = 0L;

	@OriginalMember(owner = "runetek4.client!nm", name = "bb", descriptor = "Lclient!na;")
	public static final JagString aClass100_798 = Static28.parse("<col=ff0000>");

	@OriginalMember(owner = "runetek4.client!nm", name = "a", descriptor = "(Z)V")
	public static void tryReconnect() {
		if (Static267.idleTimeout > 0) {
			Static278.processLogout();
		} else {
			Static233.aClass95_4 = Static124.socket;
			Static124.socket = null;
			Static196.method3534(40);
		}
	}
}
