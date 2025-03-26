package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static145 {

	@OriginalMember(owner = "runetek4.client!lf", name = "b", descriptor = "[I")
	public static int[] anIntArray330;

	@OriginalMember(owner = "runetek4.client!lf", name = "c", descriptor = "Lclient!ih;")
	public static final LinkedList aClass69_84 = new LinkedList();

	@OriginalMember(owner = "runetek4.client!lf", name = "a", descriptor = "(I)V")
	public static void method2742() {
		if (client.gameState == 10 && GlRenderer.enabled) {
			client.processGameStatus(28);
		}
		if (client.gameState == 30) {
			client.processGameStatus(25);
		}
	}

}
