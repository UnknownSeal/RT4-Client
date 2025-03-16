package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.actor.Player;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static41 {

	@OriginalMember(owner = "runetek4.client!dc", name = "z", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_25;

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

	@OriginalMember(owner = "runetek4.client!dc", name = "db", descriptor = "[[B")
	public static final byte[][] aByteArrayArray6 = new byte[50][];

	@OriginalMember(owner = "runetek4.client!dc", name = "a", descriptor = "(IIIZ)V")
	public static void method1045(@OriginalArg(3) boolean arg0) {
		Static258.anInt5637 = 2;
		Static164.aBoolean192 = arg0;
		Static44.anInt1404 = 22050;
	}

	@OriginalMember(owner = "runetek4.client!dc", name = "b", descriptor = "(Z)V")
	public static void readPlayerInfo() {
		@Pc(6) int local6 = Static57.in.gBit(8);

		if (Static267.playerCount > local6) {
			for (int inxed = local6; inxed < Static267.playerCount; inxed++) {
				Static52.entityRemovalIds[Static240.entityRemovalCount++] = Static105.playerIds[inxed];
			}
		}
		if (local6 > Static267.playerCount) {
			throw new RuntimeException("gppov1");
		}

		Static267.playerCount = 0;

		for (int index = 0; index < local6; index++) {
			@Pc(75) int local75 = Static105.playerIds[index];
			@Pc(79) Player local79 = Static159.players[local75];
			@Pc(84) int local84 = Static57.in.gBit(1);
			if (local84 == 0) {
				Static105.playerIds[Static267.playerCount++] = local75;
				local79.cycle = Static83.loopCycle;
			} else {
				@Pc(107) int local107 = Static57.in.gBit(2);
				if (local107 == 0) {
					Static105.playerIds[Static267.playerCount++] = local75;
					local79.cycle = Static83.loopCycle;
					Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
				} else {
					@Pc(153) int local153;
					@Pc(163) int local163;
					if (local107 == 1) {
						Static105.playerIds[Static267.playerCount++] = local75;
						local79.cycle = Static83.loopCycle;
						local153 = Static57.in.gBit(3);
						local79.method2684(1, local153);
						local163 = Static57.in.gBit(1);
						if (local163 == 1) {
							Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
						}
					} else if (local107 == 2) {
						Static105.playerIds[Static267.playerCount++] = local75;
						local79.cycle = Static83.loopCycle;
						if (Static57.in.gBit(1) == 1) {
							local153 = Static57.in.gBit(3);
							local79.method2684(2, local153);
							local163 = Static57.in.gBit(3);
							local79.method2684(2, local163);
						} else {
							local153 = Static57.in.gBit(3);
							local79.method2684(0, local153);
						}
						local153 = Static57.in.gBit(1);
						if (local153 == 1) {
							Static44.entityUpdateIds[Static116.entityUpdateCount++] = local75;
						}
					} else if (local107 == 3) {
						Static52.entityRemovalIds[Static240.entityRemovalCount++] = local75;
					}
				}
			}
		}
	}
}
