package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.media.renderable.actor.Player;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static32 {

	@OriginalMember(owner = "runetek4.client!ci", name = "t", descriptor = "I")
	public static int anInt1016;

	@OriginalMember(owner = "client!ci", name = "a", descriptor = "([I[ILclient!e;B[I)V")
	public static void method865(@OriginalArg(0) int[] arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) Player arg2, @OriginalArg(4) int[] arg3) {
		for (@Pc(7) int local7 = 0; local7 < arg1.length; local7++) {
			@Pc(20) int local20 = arg1[local7];
			@Pc(24) int local24 = arg3[local7];
			@Pc(28) int local28 = arg0[local7];
			@Pc(30) int local30 = 0;
			while (local24 != 0 && local30 < arg2.aPathingEntityClass147Array3.length) {
				if ((local24 & 0x1) != 0) {
					if (local20 == -1) {
						arg2.aPathingEntityClass147Array3[local30] = null;
					} else {
						@Pc(68) SeqType local68 = SeqTypeList.getAnimationSequence(local20);
						@Pc(71) int local71 = local68.anInt5347;
						@Pc(76) PathingEntity_Class147 local76 = arg2.aPathingEntityClass147Array3[local30];
						if (local76 != null) {
							if (local20 == local76.anInt5396) {
								if (local71 == 0) {
									local76 = arg2.aPathingEntityClass147Array3[local30] = null;
								} else if (local71 == 1) {
									local76.anInt5400 = 0;
									local76.anInt5398 = 1;
									local76.anInt5399 = 0;
									local76.anInt5408 = local28;
									local76.anInt5404 = 0;
									SoundPlayer.playSeqSound(arg2.zFine, local68, arg2.xFine, arg2 == PlayerList.self, 0);
								} else if (local71 == 2) {
									local76.anInt5400 = 0;
								}
							} else if (local68.priority >= SeqTypeList.getAnimationSequence(local76.anInt5396).priority) {
								local76 = arg2.aPathingEntityClass147Array3[local30] = null;
							}
						}
						if (local76 == null) {
							local76 = arg2.aPathingEntityClass147Array3[local30] = new PathingEntity_Class147();
							local76.anInt5396 = local20;
							local76.anInt5398 = 1;
							local76.anInt5404 = 0;
							local76.anInt5408 = local28;
							local76.anInt5399 = 0;
							local76.anInt5400 = 0;
							SoundPlayer.playSeqSound(arg2.zFine, local68, arg2.xFine, arg2 == PlayerList.self, 0);
						}
					}
				}
				local30++;
				local24 >>>= 0x1;
			}
		}
	}
}
