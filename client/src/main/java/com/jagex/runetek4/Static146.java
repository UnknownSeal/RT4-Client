package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static146 {

	@OriginalMember(owner = "runetek4.client!lg", name = "c", descriptor = "[Lclient!tk;")
	public static final SeqType[] aClass144Array1 = new SeqType[14];

	@OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(ZLclient!wa;Lclient!na;)I")
	public static int method2748(@OriginalArg(1) Packet arg0, @OriginalArg(2) JString arg1) {
		@Pc(6) int local6 = arg0.offset;
		@Pc(14) byte[] local14 = arg1.method3148();
		arg0.pSmart1or2(local14.length);
		arg0.offset += Static62.aClass44_1.encode(local14.length, arg0.data, local14, 0, arg0.offset);
		return arg0.offset - local6;
	}

	@OriginalMember(owner = "runetek4.client!lg", name = "a", descriptor = "(Lclient!ve;BII)[Lclient!ok;")
	public static IndexedSprite[] method2749(@OriginalArg(0) Js5 arg0, @OriginalArg(3) int arg1) {
		return SpriteLoader.decode(arg0, 0, arg1) ? Static253.method4331() : null;
	}

}
