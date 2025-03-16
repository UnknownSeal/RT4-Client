package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static41 {

	@OriginalMember(owner = "runetek4.client!dc", name = "z", descriptor = "Lclient!ve;")
	public static Js5 aClass153_25;

	@OriginalMember(owner = "runetek4.client!dc", name = "v", descriptor = "Lclient!na;")
	public static final JString GREEN3 = Static28.parse("<col=c0ff00>");

	@OriginalMember(owner = "runetek4.client!dc", name = "E", descriptor = "Lclient!na;")
	private static final JString aClass100_267 = Static28.parse("wave:");

	@OriginalMember(owner = "runetek4.client!dc", name = "M", descriptor = "Lclient!na;")
	public static final JString aClass100_268 = Static28.parse(")4");

	@OriginalMember(owner = "runetek4.client!dc", name = "O", descriptor = "I")
	public static int anInt1309 = 0;

	@OriginalMember(owner = "runetek4.client!dc", name = "R", descriptor = "Lclient!na;")
	private static final JString aClass100_269 = Static28.parse("Drop");

	@OriginalMember(owner = "runetek4.client!dc", name = "W", descriptor = "I")
	public static volatile int anInt1313 = 0;

	@OriginalMember(owner = "runetek4.client!dc", name = "ab", descriptor = "I")
	public static int anInt1316 = 0;

	@OriginalMember(owner = "runetek4.client!dc", name = "a", descriptor = "(IIIZ)V")
	public static void init(@OriginalArg(3) boolean stereo) {
		AudioChannel.threadPriority = 2;
		AudioChannel.stereo = stereo;
		AudioChannel.sampleRate = 22050;
	}

}
