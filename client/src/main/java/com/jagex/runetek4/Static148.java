package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.VarPlayerDefinition;
import com.jagex.runetek4.core.datastruct.HashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static148 {

    @OriginalMember(owner = "runetek4.client!li", name = "t", descriptor = "I")
	public static int anInt3534;

	@OriginalMember(owner = "runetek4.client!li", name = "w", descriptor = "Lclient!sc;")
	public static HashTable aClass133_13;

	@OriginalMember(owner = "runetek4.client!li", name = "x", descriptor = "I")
	public static int anInt3535;

	@OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(ZI)V")
	public static void method2765(@OriginalArg(1) int arg0) {
		if (arg0 == -1 && !Static144.jingle) {
			Static241.method4548();
		} else if (arg0 != -1 && (BZip2State.anInt4363 != arg0 || !Static136.method2655()) && Static12.anInt391 != 0 && !Static144.jingle) {
			Static257.method526(arg0, client.js5Archive6, Static12.anInt391);
		}
		BZip2State.anInt4363 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(III)V")
	public static void method2766(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		VarPlayerDefinition.varPlayers[arg0] = arg1;
		@Pc(21) LongNode local21 = (LongNode) Static199.aClass133_20.getNode((long) arg0);
		if (local21 == null) {
			local21 = new LongNode(MonotonicTime.currentTimeMillis() + 500L);
			Static199.aClass133_20.put(local21, (long) arg0);
		} else {
			local21.aLong55 = MonotonicTime.currentTimeMillis() + 500L;
		}
	}

	@OriginalMember(owner = "runetek4.client!li", name = "a", descriptor = "(II)Lclient!dd;")
	public static SoftwareFont method2768(@OriginalArg(1) int arg0) {
		@Pc(16) SoftwareFont local16 = (SoftwareFont) Static139.fontMetrics.get((long) arg0);
		if (local16 != null) {
			return local16;
		}
		@Pc(26) byte[] local26 = client.js5Archive13.getfile(arg0, 0);
		local16 = new SoftwareFont(local26);
		local16.setNameIcons(Sprites.nameIcons, null);
		Static139.fontMetrics.put(local16, (long) arg0);
		return local16;
	}
}
