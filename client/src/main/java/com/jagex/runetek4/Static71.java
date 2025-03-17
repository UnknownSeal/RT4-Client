package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static71 {

	@OriginalMember(owner = "runetek4.client!fk", name = "e", descriptor = "I")
	public static int anInt1885;

	@OriginalMember(owner = "runetek4.client!fk", name = "g", descriptor = "Z")
	public static boolean aBoolean107 = true;

	@OriginalMember(owner = "runetek4.client!fk", name = "k", descriptor = "[I")
	public static final int[] anIntArray147 = new int[14];

	@OriginalMember(owner = "runetek4.client!fk", name = "a", descriptor = "(B)V")
	public static void removeSoft() {
		Static279.aClass99_38.removeSoft();
		SpotAnimDefinition.modelCache.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "b", descriptor = "(IB)V")
	public static void method1443() {
		Static83.aClass99_3.clear(5);
	}

	@OriginalMember(owner = "runetek4.client!fk", name = "c", descriptor = "(I)V")
	public static void transmitVerifyId() {
		Protocol.outboundBuffer.pIsaac1(177);
		Protocol.outboundBuffer.p2(Protocol.verifyId);
	}
}
