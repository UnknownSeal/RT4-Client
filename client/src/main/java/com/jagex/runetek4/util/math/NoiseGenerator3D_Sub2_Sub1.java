package com.jagex.runetek4.util.math;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!sb")
public final class NoiseGenerator3D_Sub2_Sub1 extends NoiseGenerator3D_Sub2 {

	@OriginalMember(owner = "runetek4.client!sb", name = "I", descriptor = "[B")
	private byte[] aByteArray68;

	@OriginalMember(owner = "runetek4.client!sb", name = "<init>", descriptor = "()V")
	public NoiseGenerator3D_Sub2_Sub1() {
		super(12, 5, 16, 2, 2, 0.45F);
	}

	@OriginalMember(owner = "runetek4.client!sb", name = "a", descriptor = "(IB)V")
	@Override
	protected final void method3853(@OriginalArg(0) int arg0, @OriginalArg(1) byte arg1) {
		@Pc(3) int local3 = arg0 * 2;
		@Pc(12) byte local12 = (byte) ((arg1 >> 1 & 0x7F) + 127);
		@Pc(15) int local15 = local3;
		@Pc(16) int local16 = local3 + 1;
		this.aByteArray68[local15] = local12;
		this.aByteArray68[local16] = local12;
	}

	@OriginalMember(owner = "runetek4.client!sb", name = "b", descriptor = "(III)[B")
	public final byte[] method3854() {
		this.aByteArray68 = new byte[524288];
		this.method3841();
		return this.aByteArray68;
	}
}
