package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.IterableMap;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.CacheArchive;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static210 {

	@OriginalMember(owner = "runetek4.client!rb", name = "f", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_87;

	@OriginalMember(owner = "runetek4.client!rb", name = "b", descriptor = "Lclient!sc;")
	public static final IterableMap aClass133_21 = new IterableMap(512);

	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(I)V")
	public static void method3711() {
		for (@Pc(7) int local7 = 0; local7 < 104; local7++) {
			for (@Pc(14) int local14 = 0; local14 < 104; local14++) {
				Static31.anIntArrayArray6[local7][local14] = 0;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(ZB)V")
	public static void method3712(@OriginalArg(0) boolean arg0) {
		Static234.method4017(Static254.canvasHeigth, arg0, Static154.topLevelInterace, Static48.canvasWidth);
	}

	@OriginalMember(owner = "runetek4.client!rb", name = "a", descriptor = "(Lclient!wa;Z)Lclient!bn;")
	public static Map method3713(@OriginalArg(0) Packet arg0) {
		@Pc(35) Map local35 = new Map(arg0.gjstr(), arg0.gjstr(), arg0.g2(), arg0.g2(), arg0.g4(), arg0.g1() == 1, arg0.g1());
		@Pc(39) int local39 = arg0.g1();
		for (@Pc(41) int local41 = 0; local41 < local39; local41++) {
			local35.aClass69_23.addTail(new Class3_Sub24(arg0.g2(), arg0.g2(), arg0.g2(), arg0.g2()));
		}
		local35.method665();
		return local35;
	}
}
