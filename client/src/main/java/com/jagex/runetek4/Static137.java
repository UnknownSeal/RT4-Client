package com.jagex.runetek4;

import com.jagex.runetek4.cache.def.SpotAnimType;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static137 {

	@OriginalMember(owner = "runetek4.client!kl", name = "a", descriptor = "(Lclient!wa;B)Lclient!kc;")
	public static TextureOp29SubOp2 method2664(@OriginalArg(0) Packet arg0) {
		return new TextureOp29SubOp2(arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g3(), arg0.g3(), arg0.g1());
	}

	@OriginalMember(owner = "runetek4.client!kl", name = "c", descriptor = "(II)V")
	public static void method2666() {
		Static279.aClass99_38.clear(5);
		SpotAnimType.modelCache.clear(5);
	}
}
