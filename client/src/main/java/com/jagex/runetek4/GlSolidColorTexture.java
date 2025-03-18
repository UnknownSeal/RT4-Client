package com.jagex.runetek4;

import java.nio.ByteBuffer;

import com.jagex.runetek4.node.CachedNode;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!sd")
public final class GlSolidColorTexture extends CachedNode {

	@OriginalMember(owner = "runetek4.client!sd", name = "U", descriptor = "I")
	private int anInt5065 = 0;

	@OriginalMember(owner = "runetek4.client!sd", name = "K", descriptor = "I")
	private int anInt5058 = -1;

	@OriginalMember(owner = "runetek4.client!sd", name = "L", descriptor = "I")
	private final int anInt5059;

	@OriginalMember(owner = "runetek4.client!sd", name = "<init>", descriptor = "(I)V")
	public GlSolidColorTexture(@OriginalArg(0) int arg0) {
		@Pc(9) GL2 local9 = GlRenderer.gl;
		@Pc(12) int[] local12 = new int[1];
		local9.glGenTextures(1, local12, 0);
		this.anInt5058 = local12[0];
		this.anInt5059 = GlCleaner.contextId;
		GlRenderer.setTextureId(this.anInt5058);
		@Pc(32) int local32 = Pix3D.anIntArray220[arg0];
		@Pc(58) byte[] local58 = new byte[] { (byte) (local32 >> 16), (byte) (local32 >> 8), (byte) local32, -1 };
		@Pc(61) ByteBuffer local61 = ByteBuffer.wrap(local58);
		local9.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, 1, 1, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, local61);
		local9.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
		local9.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		Static63.oncard_texture += local61.limit() - this.anInt5065;
		this.anInt5065 = local61.limit();
	}

	@OriginalMember(owner = "runetek4.client!sd", name = "finalize", descriptor = "()V")
	@Override
	public final void finalize() throws Throwable {
		if (this.anInt5058 != -1) {
			GlCleaner.deleteTexture(this.anInt5058, this.anInt5065, this.anInt5059);
			this.anInt5058 = -1;
			this.anInt5065 = 0;
		}
		super.finalize();
	}

	@OriginalMember(owner = "runetek4.client!sd", name = "g", descriptor = "(B)V")
	public final void method3887() {
		@Pc(8) int local8 = Static56.method1314();
		if ((local8 & 0x1) == 0) {
			GlRenderer.setTextureId(this.anInt5058);
		}
		if ((local8 & 0x2) == 0) {
			GlRenderer.setTextureCombineRgbMode(0);
		}
		if ((local8 & 0x4) == 0) {
			GlRenderer.setTextureCombineAlphaMode(0);
		}
	}
}
