package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!u")
public abstract class ByteBuffer {

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(I)[B")
	public abstract byte[] method4236();

	@OriginalMember(owner = "runetek4.client!u", name = "a", descriptor = "(I[B)V")
	public abstract void method4238(@OriginalArg(1) byte[] arg0);
}
