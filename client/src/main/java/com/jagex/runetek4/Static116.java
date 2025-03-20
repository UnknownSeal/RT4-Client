package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static116 {

	@OriginalMember(owner = "runetek4.client!jb", name = "k", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array3;

	@OriginalMember(owner = "runetek4.client!jb", name = "m", descriptor = "I")
	public static int entityUpdateCount = 0;

	@OriginalMember(owner = "runetek4.client!jb", name = "n", descriptor = "I")
	public static int oneMouseButton = 0;

	@OriginalMember(owner = "runetek4.client!jb", name = "p", descriptor = "I")
	public static int anInt2954 = -1;

	@OriginalMember(owner = "runetek4.client!jb", name = "y", descriptor = "I")
	public static int anInt2961 = 0;

	@OriginalMember(owner = "runetek4.client!jb", name = "a", descriptor = "(ILclient!ve;I)Lclient!jk;")
	public static MidiInstrument method2320(@OriginalArg(1) Js5 arg0, @OriginalArg(2) int arg1) {
		@Pc(9) byte[] local9 = arg0.method4500(arg1);
		return local9 == null ? null : new MidiInstrument(local9);
	}

}
