package com.jagex.runetek4.graphics.gl;

import java.nio.ByteBuffer;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("client!ql")
public final class GlBuffer {

	@OriginalMember(owner = "client!ql", name = "e", descriptor = "Lclient!vi;")
	public GlVertexBufferObject vbo;

	@OriginalMember(owner = "client!ql", name = "f", descriptor = "Ljava/nio/ByteBuffer;")
	public ByteBuffer buffer;

	@OriginalMember(owner = "client!ql", name = "b", descriptor = "Z")
	public boolean valid = false;

	@OriginalMember(owner = "client!ql", name = "a", descriptor = "I")
	public int pointer = 0;

	@OriginalMember(owner = "client!ql", name = "m", descriptor = "I")
	public int stride = 0;
}
