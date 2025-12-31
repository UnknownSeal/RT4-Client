package com.jagex.core.utils;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("signlink!ad")
public final class SystemTimer {

	@OriginalMember(owner = "signlink!ad", name = "a", descriptor = "J")
	private static long timeOffset;

	@OriginalMember(owner = "signlink!ad", name = "b", descriptor = "J")
	private static long lastTime;

	@OriginalMember(owner = "signlink!ad", name = "a", descriptor = "(B)J")
	public static synchronized long safetime() {
		@Pc(1) long now = System.currentTimeMillis();
		if (lastTime > now) {
			timeOffset += lastTime - now;
		}
		lastTime = now;
		return timeOffset + now;
	}
}
