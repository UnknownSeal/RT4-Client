package com.jagex.runetek4.graphics.textureops;

import com.jagex.runetek4.util.system.PreciseSleep;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ci")
public final class TextureOp29SubOp1 extends TextureOp29SubOp {

	@OriginalMember(owner = "runetek4.client!ci", name = "s", descriptor = "I")
	private final int anInt1015;

	@OriginalMember(owner = "runetek4.client!ci", name = "o", descriptor = "I")
	private final int anInt1012;

	@OriginalMember(owner = "runetek4.client!ci", name = "m", descriptor = "I")
	private final int anInt1010;

	@OriginalMember(owner = "runetek4.client!ci", name = "v", descriptor = "I")
	private final int anInt1017;

	@OriginalMember(owner = "runetek4.client!ci", name = "<init>", descriptor = "(IIIIII)V")
	public TextureOp29SubOp1(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		super(-1, arg4, arg5);
		this.anInt1015 = arg3;
		this.anInt1012 = arg1;
		this.anInt1010 = arg0;
		this.anInt1017 = arg2;
	}

	@OriginalMember(owner = "runetek4.client!ci", name = "a", descriptor = "(III)V")
	@Override
	public final void method4007(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
	}

	@OriginalMember(owner = "runetek4.client!ci", name = "c", descriptor = "(III)V")
	@Override
	public final void method4013(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
	}

	@OriginalMember(owner = "runetek4.client!ci", name = "a", descriptor = "(IZI)V")
	@Override
	public final void method4009(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) int local10 = arg1 * this.anInt1010 >> 12;
		@Pc(25) int local25 = this.anInt1017 * arg1 >> 12;
		@Pc(32) int local32 = arg0 * this.anInt1012 >> 12;
		@Pc(39) int local39 = arg0 * this.anInt1015 >> 12;
		PreciseSleep.method3982(this.anInt5229, local39, local10, local32, local25);
	}
}
