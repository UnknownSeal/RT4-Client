package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;

public final class Static119 {

	@OriginalMember(owner = "client!je", name = "j", descriptor = "(I)I")
	public static int method2385() {
		if (WorldMap.labels == null) {
			return -1;
		}
		while (WorldMap.anInt5212 < WorldMap.labels.anInt5074) {
			if (WorldMap.labels.method3897(WorldMap.anInt5212)) {
				return WorldMap.anInt5212++;
			}
			WorldMap.anInt5212++;
		}
		return -1;
	}

}
