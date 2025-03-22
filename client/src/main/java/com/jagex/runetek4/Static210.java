package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static210 {

	@OriginalMember(owner = "runetek4.client!rb", name = "f", descriptor = "Lclient!ve;")
	public static Js5 aClass153_87;

	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(I)V")
	public static void method3711() {
		for (@Pc(7) int local7 = 0; local7 < 104; local7++) {
			for (@Pc(14) int local14 = 0; local14 < 104; local14++) {
				Static31.anIntArrayArray6[local7][local14] = 0;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(Lclient!wa;Z)Lclient!bn;")
	public static Map create(@OriginalArg(0) Packet arg0) {
		@Pc(35) Map map = new Map(arg0.gjstr(), arg0.gjstr(), arg0.g2(), arg0.g2(), arg0.g4(), arg0.g1() == 1, arg0.g1());
		@Pc(39) int len = arg0.g1();
		for (@Pc(41) int i = 0; i < len; i++) {
			map.chunks.addTail(new MapChunk(arg0.g2(), arg0.g2(), arg0.g2(), arg0.g2()));
		}
		map.computeBounds();
		return map;
	}
}
