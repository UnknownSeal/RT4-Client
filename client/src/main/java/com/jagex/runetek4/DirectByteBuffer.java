package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ua")
public final class DirectByteBuffer extends ByteBuffer {

	@OriginalMember(owner = "runetek4.client!ua", name = "m", descriptor = "Ljava/nio/ByteBuffer;")
	private java.nio.ByteBuffer aByteBuffer10;

	@OriginalMember(owner = "runetek4.client!ua", name = "a", descriptor = "(I[B)V")
	@Override
	public final void method4238(@OriginalArg(1) byte[] arg0) {
		this.aByteBuffer10 = java.nio.ByteBuffer.allocateDirect(arg0.length);
		this.aByteBuffer10.position(0);
		this.aByteBuffer10.put(arg0);
	}

	@OriginalMember(owner = "runetek4.client!ua", name = "a", descriptor = "(I)[B")
	@Override
	public final byte[] method4236() {
		@Pc(4) byte[] local4 = new byte[this.aByteBuffer10.capacity()];
		this.aByteBuffer10.position(0);
		this.aByteBuffer10.get(local4);
		return local4;
	}
}
