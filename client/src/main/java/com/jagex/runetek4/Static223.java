package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.dash3d.entity.Npc;
import com.jagex.runetek4.scene.Scenery;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static223 {

	@OriginalMember(owner = "runetek4.client!sc", name = "m", descriptor = "[Z")
	public static boolean[] loadedComponents;

	@OriginalMember(owner = "runetek4.client!sc", name = "f", descriptor = "Lclient!na;")
	public static final JString aClass100_946 = JString.parse("(R");

	@OriginalMember(owner = "runetek4.client!sc", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_947 = JString.parse(" )2> <col=ff9040>");

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
	public static final JString ORANGE = JString.parse("<col=ff7000>");

	@OriginalMember(owner = "runetek4.client!sc", name = "a", descriptor = "(IIILclient!km;)V")
	public static void method3855(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) Npc arg2) {
		if (arg2.primarySeqId == arg1 && arg1 != -1) {
			@Pc(10) SeqType local10 = SeqType.getAnimationSequence(arg1);
			@Pc(13) int local13 = local10.anInt5347;
			if (local13 == 1) {
				arg2.anInt3373 = 1;
				arg2.anInt3425 = 0;
				arg2.anInt3360 = 0;
				arg2.anInt3371 = 0;
				arg2.anInt3420 = arg0;
				Static152.method2836(arg2.zFine, local10, arg2.xFine, false, arg2.anInt3425);
			}
			if (local13 == 2) {
				arg2.anInt3371 = 0;
			}
		} else if (arg1 == -1 || arg2.primarySeqId == -1 || SeqType.getAnimationSequence(arg1).priority >= SeqType.getAnimationSequence(arg2.primarySeqId).priority) {
			arg2.anInt3360 = 0;
			arg2.primarySeqId = arg1;
			arg2.anInt3373 = 1;
			arg2.anInt3371 = 0;
			arg2.anInt3420 = arg0;
			arg2.anInt3405 = arg2.movementQueueSize;
			arg2.anInt3425 = 0;
			if (arg2.primarySeqId != -1) {
				Static152.method2836(arg2.zFine, SeqType.getAnimationSequence(arg2.primarySeqId), arg2.xFine, false, arg2.anInt3425);
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

}
