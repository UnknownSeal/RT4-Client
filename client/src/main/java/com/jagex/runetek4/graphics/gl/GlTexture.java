package com.jagex.runetek4.graphics.gl;

import java.nio.ByteBuffer;

import com.jagex.runetek4.graphics.render.MaterialManager;
import com.jagex.runetek4.graphics.texture.Texture;
import com.jagex.runetek4.graphics.texture.TextureProvider;
import com.jagex.runetek4.core.node.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.data.js5.Js5;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!uh")
public final class GlTexture extends SecondaryNode {

	@OriginalMember(owner = "client!oj", name = "t", descriptor = "[I")
	public static int[] anIntArray372;

	@OriginalMember(owner = "client!uh", name = "K", descriptor = "F")
	private float brightness;

	@OriginalMember(owner = "client!uh", name = "X", descriptor = "I")
	private int anInt5492;

	@OriginalMember(owner = "client!uh", name = "Z", descriptor = "[I")
	private int[] pixels;

	@OriginalMember(owner = "client!uh", name = "bb", descriptor = "I")
	private int textureId = -1;

	@OriginalMember(owner = "client!uh", name = "eb", descriptor = "Z")
	public boolean aBoolean287 = false;

	@OriginalMember(owner = "client!uh", name = "db", descriptor = "I")
	private int textureSize = 0;

	@OriginalMember(owner = "client!uh", name = "W", descriptor = "Lclient!lc;")
	private final Texture texture;

	@OriginalMember(owner = "client!uh", name = "U", descriptor = "Z")
	private final boolean aBoolean286;

	@OriginalMember(owner = "client!uh", name = "jb", descriptor = "Z")
	private final boolean aBoolean288;

	@OriginalMember(owner = "client!uh", name = "Q", descriptor = "Z")
	private final boolean aBoolean285;

	@OriginalMember(owner = "client!uh", name = "J", descriptor = "Z")
	private final boolean aBoolean284;

	@OriginalMember(owner = "client!uh", name = "L", descriptor = "I")
	private final int anInt5485;

	@OriginalMember(owner = "client!uh", name = "hb", descriptor = "I")
	private final int anInt5497;

	@OriginalMember(owner = "client!uh", name = "M", descriptor = "I")
	private final int combineRgbMode;

	@OriginalMember(owner = "client!uh", name = "S", descriptor = "I")
	private final int mipMapMode;

	@OriginalMember(owner = "client!uh", name = "<init>", descriptor = "(Lclient!wa;)V")
	public GlTexture(@OriginalArg(0) Packet packet) {
		this.texture = new Texture(packet);
		this.aBoolean286 = packet.g1() == 1;
		this.aBoolean288 = packet.g1() == 1;
		this.aBoolean285 = packet.g1() == 1;
		this.aBoolean284 = packet.g1() == 1;
		@Pc(68) int combineRgbMode = packet.g1() & 0x3;
		this.anInt5485 = packet.g1s();
		this.anInt5497 = packet.g1s();
		@Pc(82) int local82 = packet.g1();
		packet.g1();
		if (combineRgbMode == 1) {
			this.combineRgbMode = 2;
		} else if (combineRgbMode == 2) {
			this.combineRgbMode = 3;
		} else if (combineRgbMode == 3) {
			this.combineRgbMode = 4;
		} else {
			this.combineRgbMode = 0;
		}
		this.mipMapMode = local82 >> 4 & 0xF;
	}

	@OriginalMember(owner = "client!uh", name = "a", descriptor = "(Lclient!m;IFLclient!ve;Z)[I")
	public final int[] method4295(@OriginalArg(0) TextureProvider arg0, @OriginalArg(2) float brightness, @OriginalArg(3) Js5 arg2, @OriginalArg(4) boolean lowDetail) {
		if (this.pixels == null || this.brightness != brightness) {
			if (!this.texture.isReady(arg0, arg2)) {
				return null;
			}
			@Pc(36) int local36 = lowDetail ? 64 : 128;
			this.pixels = this.texture.getPixels(local36, this.aBoolean288, local36, (double) brightness, arg2, arg0, true);
			this.brightness = brightness;
			if (this.aBoolean286) {
				@Pc(62) int[] local62 = new int[local36];
				@Pc(67) int[] local67 = new int[local36 * local36];
				@Pc(70) int[] local70 = new int[local36];
				@Pc(73) int[] local73 = new int[local36];
				@Pc(80) int local80;
				@Pc(81) int local81 = local80 = local36;
				@Pc(87) int local87 = local36 * local36;
				@Pc(91) int local91 = local36 - 1;
				@Pc(95) int local95 = local36 - 1;
				@Pc(101) int local101;
				@Pc(97) int local97;
				for (local97 = 2; local97 >= 0; local97--) {
					for (local101 = local95; local101 >= 0; local101--) {
						local80--;
						@Pc(109) int local109 = this.pixels[local80];
						local62[local101] += local109 >> 16 & 0xFF;
						local70[local101] += local109 >> 8 & 0xFF;
						local73[local101] += local109 & 0xFF;
					}
					if (local80 == 0) {
						local80 = local87;
					}
				}
				@Pc(152) int local152 = local87;
				for (@Pc(154) int local154 = local91; local154 >= 0; local154--) {
					@Pc(162) int local162 = 0;
					@Pc(164) int local164 = 0;
					@Pc(165) int local165 = 0;
					@Pc(167) int local167 = 1;
					@Pc(169) int local169 = 1;
					for (local101 = 2; local101 >= 0; local101--) {
						local169--;
						local162 += local70[local169];
						local164 += local73[local169];
						local165 += local62[local169];
						if (local169 == 0) {
							local169 = local36;
						}
					}
					for (local101 = local95; local101 >= 0; local101--) {
						local167--;
						@Pc(215) int local215 = local162 / 9;
						@Pc(219) int local219 = local164 / 9;
						local169--;
						local97 = local165 / 9;
						local152--;
						local67[local152] = local219 | local97 << 16 | local215 << 8;
						local165 += local62[local169] - local62[local167];
						local164 += local73[local169] - local73[local167];
						local162 += local70[local169] - local70[local167];
						if (local167 == 0) {
							local167 = local36;
						}
						if (local169 == 0) {
							local169 = local36;
						}
					}
					for (local101 = local95; local101 >= 0; local101--) {
						local81--;
						@Pc(300) int local300 = this.pixels[local81];
						local80--;
						@Pc(306) int local306 = this.pixels[local80];
						local62[local101] += (local306 >> 16 & 0xFF) - (local300 >> 16 & 0xFF);
						local70[local101] += (local306 >> 8 & 0xFF) - (local300 >> 8 & 0xFF);
						local73[local101] += (local306 & 0xFF) - (local300 & 0xFF);
					}
					if (local81 == 0) {
						local81 = local87;
					}
					if (local80 == 0) {
						local80 = local87;
					}
				}
				this.pixels = local67;
			}
		}
		return this.pixels;
	}

	@OriginalMember(owner = "client!uh", name = "a", descriptor = "(Lclient!ve;Lclient!m;IZ)Z")
	public final boolean method4296(@OriginalArg(0) Js5 arg0, @OriginalArg(1) TextureProvider arg1, @OriginalArg(3) boolean arg2) {
		if (!this.texture.isReady(arg1, arg0)) {
			return false;
		}
		@Pc(22) GL2 gl = GlRenderer.gl;
		@Pc(28) int size = arg2 ? 64 : 128;
		@Pc(31) int local31 = MaterialManager.getFlags();
		if ((local31 & 0x1) == 0) {
			if (this.textureId == -1) {
				@Pc(53) int[] temp = new int[1];
				gl.glGenTextures(1, temp, 0);
				this.anInt5492 = GlCleaner.contextId;
				this.textureId = temp[0];
				GlRenderer.setTextureId(this.textureId);
				@Pc(82) ByteBuffer pixels = ByteBuffer.wrap(this.texture.method2728(size, size, this.aBoolean288, arg1, 0.7D, arg0));
				if (this.mipMapMode == 2) {
					// Old GLU Code
					//@Pc(201) GLUgl2es1 local201 = new GLUgl2es1();
					//local28.gluBuild2DMipmaps(3553, 6408, local28, local28, 6408, 5121, pixels);
					//gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
					//gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);

					// New Code OpenGL 4+ w/ texStorage
					int num_mipmaps = 4;
					gl.glTexStorage2D(GL2.GL_TEXTURE_2D, num_mipmaps, GL2.GL_RGBA8, size, size);
					gl.glTexSubImage2D(GL2.GL_TEXTURE_2D, 0, 0, 0, size, size, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, pixels);
					gl.glGenerateMipmap(GL2.GL_TEXTURE_2D);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_REPEAT);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, GL2.GL_REPEAT);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
					GlCleaner.oncard_texture += pixels.limit() * 4 / 3 - this.textureSize;
					this.textureSize = pixels.limit() * 4 / 3;
				} else if (this.mipMapMode == 1) {
					@Pc(129) int local129 = 0;
					while (true) {
						gl.glTexImage2D(GL2.GL_TEXTURE_2D, local129++, GL2.GL_RGBA, size, size, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, pixels);
						size >>= 0x1;
						if (size == 0) {
							gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR_MIPMAP_LINEAR);
							gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
							GlCleaner.oncard_texture += pixels.limit() * 4 / 3 - this.textureSize;
							this.textureSize = pixels.limit() * 4 / 3;
							break;
						}
						pixels = ByteBuffer.wrap(this.texture.method2728(size, size, this.aBoolean288, arg1, 0.7D, arg0));
					}
				} else {
					gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, size, size, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, pixels);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
					GlCleaner.oncard_texture += pixels.limit() - this.textureSize;
					this.textureSize = pixels.limit();
				}
				gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, this.aBoolean285 ? GL2.GL_REPEAT : GL2.GL_CLAMP_TO_EDGE);
				gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, this.aBoolean284 ? GL2.GL_REPEAT : GL2.GL_CLAMP_TO_EDGE);
			} else {
				GlRenderer.setTextureId(this.textureId);
			}
		}
		if ((local31 & 0x2) == 0) {
			GlRenderer.setTextureCombineRgbMode(this.combineRgbMode);
		}
		if ((local31 & 0x4) == 0) {
			GlRenderer.setTextureCombineAlphaMode(0);
		}
		if ((local31 & 0x8) == 0) {
			if (this.anInt5497 == 0 && this.anInt5485 == 0) {
				GlRenderer.resetTextureMatrix();
			} else {
				@Pc(303) float local303 = (float) (this.anInt5497 * GlRenderer.anInt5323) / (float) size;
				@Pc(312) float local312 = (float) (this.anInt5485 * GlRenderer.anInt5323) / (float) size;
				GlRenderer.translateTextureMatrix(local312, local303, 0.0F);
			}
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(ZZLclient!m;Lclient!ve;)[I")
	public final int[] method4297(@OriginalArg(1) boolean arg0, @OriginalArg(2) TextureProvider arg1, @OriginalArg(3) Js5 arg2) {
		if (this.texture.isReady(arg1, arg2)) {
			@Pc(24) int local24 = arg0 ? 64 : 128;
			return this.texture.getPixels(local24, this.aBoolean288, local24, 1.0D, arg2, arg1, false);
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(ILclient!m;Lclient!ve;)Z")
	public final boolean method4299(@OriginalArg(1) TextureProvider arg0, @OriginalArg(2) Js5 arg1) {
		return this.texture.isReady(arg0, arg1);
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(IB)V")
	public final void method4300(@OriginalArg(0) int arg0) {
		if (this.pixels == null || this.anInt5497 == 0 && this.anInt5485 == 0) {
			return;
		}
		if (anIntArray372 == null || anIntArray372.length < this.pixels.length) {
			anIntArray372 = new int[this.pixels.length];
		}
		@Pc(47) int local47 = arg0 * this.anInt5485;
		@Pc(58) int local58 = this.pixels.length == 4096 ? 64 : 128;
		@Pc(62) int local62 = this.pixels.length;
		@Pc(66) int local66 = local58 - 1;
		@Pc(73) int local73 = this.anInt5497 * arg0 * local58;
		@Pc(77) int local77 = local62 - 1;
		for (@Pc(79) int local79 = 0; local79 < local62; local79 += local58) {
			@Pc(88) int local88 = local73 + local79 & local77;
			for (@Pc(90) int local90 = 0; local90 < local58; local90++) {
				@Pc(102) int local102 = (local66 & local90 + local47) + local88;
				@Pc(106) int local106 = local90 + local79;
				anIntArray372[local106] = this.pixels[local102];
			}
		}
		@Pc(125) int[] local125 = this.pixels;
		this.pixels = anIntArray372;
		anIntArray372 = local125;
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "finalize", descriptor = "()V")
	@Override
	public final void finalize() throws Throwable {
		if (this.textureId != -1) {
			GlCleaner.deleteTexture(this.textureId, this.textureSize, this.anInt5492);
			this.textureSize = 0;
			this.textureId = -1;
		}
		super.finalize();
	}
}
