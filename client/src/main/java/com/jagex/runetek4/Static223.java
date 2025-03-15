package com.jagex.runetek4;

import java.awt.Component;

import com.jagex.runetek4.cache.media.AnimationSequence;
import com.jagex.runetek4.dash3d.entity.NPCEntity;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static223 {

	@OriginalMember(owner = "runetek4.client!sc", name = "m", descriptor = "[Z")
	public static boolean[] loadedComponents;

	@OriginalMember(owner = "runetek4.client!sc", name = "f", descriptor = "Lclient!na;")
	public static final JString aClass100_946 = Static28.parse("(R");

	@OriginalMember(owner = "runetek4.client!sc", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_947 = Static28.parse(" )2> <col=ff9040>");

	@OriginalMember(owner = "runetek4.client!sc", name = "o", descriptor = "I")
	public static int packetSize = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "p", descriptor = "I")
	public static int anInt5029 = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "v", descriptor = "I")
	public static int anInt5032 = 0;

	@OriginalMember(owner = "runetek4.client!sc", name = "y", descriptor = "I")
	public static int reply = -2;

	@OriginalMember(owner = "runetek4.client!sc", name = "z", descriptor = "[Z")
	public static final boolean[] aBooleanArray116 = new boolean[100];

	@OriginalMember(owner = "runetek4.client!sc", name = "D", descriptor = "Lclient!na;")
	public static final JString ORANGE = Static28.parse("<col=ff7000>");

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(IIILclient!km;)V")
	public static void method3855(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) NPCEntity arg2) {
		if (arg2.primarySeqId == arg1 && arg1 != -1) {
			@Pc(10) AnimationSequence local10 = AnimationSequence.getAnimationSequence(arg1);
			@Pc(13) int local13 = local10.anInt5347;
			if (local13 == 1) {
				arg2.anInt3373 = 1;
				arg2.anInt3425 = 0;
				arg2.anInt3360 = 0;
				arg2.anInt3371 = 0;
				arg2.anInt3420 = arg0;
				Static152.method2836(arg2.z, local10, arg2.x, false, arg2.anInt3425);
			}
			if (local13 == 2) {
				arg2.anInt3371 = 0;
			}
		} else if (arg1 == -1 || arg2.primarySeqId == -1 || AnimationSequence.getAnimationSequence(arg1).priority >= AnimationSequence.getAnimationSequence(arg2.primarySeqId).priority) {
			arg2.anInt3360 = 0;
			arg2.primarySeqId = arg1;
			arg2.anInt3373 = 1;
			arg2.anInt3371 = 0;
			arg2.anInt3420 = arg0;
			arg2.anInt3405 = arg2.pathLength;
			arg2.anInt3425 = 0;
			if (arg2.primarySeqId != -1) {
				Static152.method2836(arg2.z, AnimationSequence.getAnimationSequence(arg2.primarySeqId), arg2.x, false, arg2.anInt3425);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "()V")
	public static void method3858() {
		for (@Pc(1) int local1 = 0; local1 < Static22.anInt726; local1++) {
			@Pc(8) Scenery local8 = Static243.aClass31Array3[local1];
			Static266.method4193(local8);
			Static243.aClass31Array3[local1] = null;
		}
		Static22.anInt726 = 0;
	}

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(ILjava/awt/runetek4.Component;)V")
	public static void method3866(@OriginalArg(1) Component arg0) {
		arg0.removeMouseListener(Static93.aClass150_1);
		arg0.removeMouseMotionListener(Static93.aClass150_1);
		arg0.removeFocusListener(Static93.aClass150_1);
		Static57.anInt1759 = 0;
	}
}
