package com.jagex.runetek4;

import java.nio.ByteBuffer;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import com.jagex.runetek4.util.IntUtils;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass(".client!el")
public final class GlAlphaSprite extends GlSprite {

	@OriginalMember(owner = "client!el", name = "<init>", descriptor = "(IIIIII[I)V")
	public GlAlphaSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int[] arg6) {
		super(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
	}

	@OriginalMember(owner = "client!el", name = "<init>", descriptor = "(Lclient!mm;)V")
	public GlAlphaSprite(@OriginalArg(0) SoftwareSprite sprite) {
		super(sprite);
	}

	@OriginalMember(owner = "client!el", name = "a", descriptor = "([I)V")
	@Override
	protected void setPixels(@OriginalArg(0) int[] source) {
		this.powerOfTwoWidth = IntUtils.bitceil(this.width);
		this.powerOfTwoHeight = IntUtils.bitceil(this.height);

		@Pc(20) byte[] destination = new byte[this.powerOfTwoWidth * this.powerOfTwoHeight * 4];
		@Pc(22) int destinationOffset = 0;
		@Pc(24) int sourceOffset = 0;
		@Pc(32) int destinationStride = (this.powerOfTwoWidth - this.width) * 4;

		for (@Pc(34) int y = 0; y < this.height; y++) {
			for (@Pc(40) int x = 0; x < this.width; x++) {
				@Pc(49) int colour = source[sourceOffset++];
				if (colour == 0) {
					destinationOffset += 4;
				} else {
					destination[destinationOffset++] = (byte) (colour >> 16);
					destination[destinationOffset++] = (byte) (colour >> 8);
					destination[destinationOffset++] = (byte) colour;
					destination[destinationOffset++] = (byte) (colour >> 24);
				}
			}
			destinationOffset += destinationStride;
		}

		@Pc(94) ByteBuffer buffer = ByteBuffer.wrap(destination);
		@Pc(96) GL2 gl2 = GlRenderer.gl;
		if (this.textureId == -1) {
			@Pc(103) int[] temporary = new int[1];
			gl2.glGenTextures(1, temporary, 0);
			this.textureId = temporary[0];
		}

		GlRenderer.setTextureId(this.textureId);
		gl2.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, this.powerOfTwoWidth, this.powerOfTwoHeight, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, buffer);
		GlCleaner.oncard_2d += buffer.limit() - this.size;
		this.size = buffer.limit();
	}
}
