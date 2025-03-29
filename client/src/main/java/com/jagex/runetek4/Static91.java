package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static91 {

	@OriginalMember(owner = "runetek4.client!hc", name = "d", descriptor = "(I)I")
	public static int getZoom() {
		if ((double) WorldMap.targetZoom == 3.0D) {
			return 37;
		} else if ((double) WorldMap.targetZoom == 4.0D) {
			return 50;
		} else if ((double) WorldMap.targetZoom == 6.0D) {
			return 75;
		} else if ((double) WorldMap.targetZoom == 8.0D) {
			return 100;
		} else {
			return 200;
		}
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
	public static int method1879(@OriginalArg(0) JString arg0) {
		if (WorldMap.labels == null || arg0.length() == 0) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < WorldMap.labels.anInt5074; local20++) {
			if (WorldMap.labels.aClass100Array153[local20].method3140(WorldMap.aClass100_538, WorldMap.aClass100_872).strEquals(arg0)) {
				return local20;
			}
		}
		return -1;
	}

}
