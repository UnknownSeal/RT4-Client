package com.jagex.runetek4;

import java.awt.Frame;
import java.util.Random;

import com.jagex.runetek4.config.Component;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static39 {

	@OriginalMember(owner = "runetek4.client!d", name = "Y", descriptor = "Ljava/awt/Frame;")
	public static Frame frame;

	@OriginalMember(owner = "runetek4.client!d", name = "hb", descriptor = "Lclient!ve;")
	public static Js5 aClass153_23;

	@OriginalMember(owner = "runetek4.client!d", name = "ib", descriptor = "Lclient!qf;")
	public static Sprite aClass3_Sub2_Sub1_1;

	@OriginalMember(owner = "runetek4.client!d", name = "R", descriptor = "Lclient!be;")
	public static Component aClass13_10 = null;

	@OriginalMember(owner = "runetek4.client!d", name = "S", descriptor = "Ljava/util/Random;")
	public static final Random aRandom1 = new Random();

	@OriginalMember(owner = "runetek4.client!d", name = "db", descriptor = "Z")
	public static boolean aBoolean77 = false;

	@OriginalMember(owner = "runetek4.client!d", name = "eb", descriptor = "[S")
	public static final short[] aShortArray6 = new short[500];

	@OriginalMember(owner = "runetek4.client!d", name = "c", descriptor = "(III)I")
	public static int method990(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(11) int local11 = arg0 >> 31 & arg1 - 1;
		return ((arg0 >>> 31) + arg0) % arg1 + local11;
	}
}
