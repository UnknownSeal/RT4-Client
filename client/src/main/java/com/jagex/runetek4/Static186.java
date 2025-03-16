package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static186 {

	@OriginalMember(owner = "runetek4.client!pa", name = "L", descriptor = "[[B")
	public static byte[][] aByteArrayArray14;

	@OriginalMember(owner = "runetek4.client!pa", name = "N", descriptor = "I")
	public static int antialiasingMode = 0;

	@OriginalMember(owner = "runetek4.client!pa", name = "O", descriptor = "Lclient!na;")
	public static final JString aClass100_827 = Static28.parse("");

	@OriginalMember(owner = "runetek4.client!pa", name = "P", descriptor = "Lclient!na;")
	public static JString password = aClass100_827;

	@OriginalMember(owner = "runetek4.client!pa", name = "R", descriptor = "[Z")
	public static final boolean[] aBooleanArray100 = new boolean[100];

	@OriginalMember(owner = "runetek4.client!pa", name = "S", descriptor = "Lclient!na;")
	public static JString username = aClass100_827;

	@OriginalMember(owner = "runetek4.client!pa", name = "a", descriptor = "(IIILclient!e;)V")
	public static void method3415(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Player arg2) {
		if (arg1 == arg2.primarySeqId && arg1 != -1) {
			@Pc(89) SeqType local89 = SeqType.getAnimationSequence(arg1);
			@Pc(92) int local92 = local89.anInt5347;
			if (local92 == 1) {
				arg2.anInt3420 = arg0;
				arg2.anInt3360 = 0;
				arg2.anInt3373 = 1;
				arg2.anInt3425 = 0;
				arg2.anInt3371 = 0;
				Static152.method2836(arg2.zFine, local89, arg2.xFine, PlayerList.self == arg2, arg2.anInt3425);
			}
			if (local92 == 2) {
				arg2.anInt3371 = 0;
			}
		} else if (arg1 == -1 || arg2.primarySeqId == -1 || SeqType.getAnimationSequence(arg1).priority >= SeqType.getAnimationSequence(arg2.primarySeqId).priority) {
			arg2.anInt3373 = 1;
			arg2.anInt3425 = 0;
			arg2.anInt3420 = arg0;
			arg2.anInt3405 = arg2.pathLength;
			arg2.anInt3371 = 0;
			arg2.anInt3360 = 0;
			arg2.primarySeqId = arg1;
			if (arg2.primarySeqId != -1) {
				Static152.method2836(arg2.zFine, SeqType.getAnimationSequence(arg2.primarySeqId), arg2.xFine, arg2 == PlayerList.self, arg2.anInt3425);
			}
		}
	}
}
