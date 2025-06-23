package com.jagex.runetek4;

import java.nio.ByteBuffer;

import com.jagex.runetek4.node.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!uh")
public final class GlTexture extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!oj", name = "t", descriptor = "[I")
	public static int[] anIntArray372;

	@OriginalMember(owner = "runetek4.client!uh", name = "K", descriptor = "F")
	private float brightness;

	@OriginalMember(owner = "runetek4.client!uh", name = "X", descriptor = "I")
	private int anInt5492;

	@OriginalMember(owner = "runetek4.client!uh", name = "Z", descriptor = "[I")
	private int[] pixels;

	@OriginalMember(owner = "runetek4.client!uh", name = "bb", descriptor = "I")
	private int textureId = -1;

	@OriginalMember(owner = "runetek4.client!uh", name = "eb", descriptor = "Z")
	public boolean aBoolean287 = false;

	@OriginalMember(owner = "runetek4.client!uh", name = "db", descriptor = "I")
	private int textureSize = 0;

	@OriginalMember(owner = "runetek4.client!uh", name = "W", descriptor = "Lclient!lc;")
	private final Texture texture;

	@OriginalMember(owner = "runetek4.client!uh", name = "U", descriptor = "Z")
	private final boolean aBoolean286;

	@OriginalMember(owner = "runetek4.client!uh", name = "jb", descriptor = "Z")
	private final boolean columnmajor;

	@OriginalMember(owner = "runetek4.client!uh", name = "Q", descriptor = "Z")
	private final boolean wrapS;

	@OriginalMember(owner = "runetek4.client!uh", name = "J", descriptor = "Z")
	private final boolean wrapT;

	@OriginalMember(owner = "runetek4.client!uh", name = "L", descriptor = "I")
	private final int anInt5485;

	@OriginalMember(owner = "runetek4.client!uh", name = "hb", descriptor = "I")
	private final int anInt5497;

	@OriginalMember(owner = "runetek4.client!uh", name = "M", descriptor = "I")
	private final int combineRGBMode;

	@OriginalMember(owner = "runetek4.client!uh", name = "S", descriptor = "I")
	private final int mipmapMode;

	@OriginalMember(owner = "runetek4.client!uh", name = "<init>", descriptor = "(Lclient!wa;)V")
	public GlTexture(@OriginalArg(0) Packet packet) {
		this.texture = new Texture(packet);
		this.aBoolean286 = packet.g1() == 1;
		this.columnmajor = packet.g1() == 1;
		this.wrapS = packet.g1() == 1;
		this.wrapT = packet.g1() == 1;
		@Pc(68) int combineRGBMode = packet.g1() & 0x3;
		this.anInt5485 = packet.g1s();
		this.anInt5497 = packet.g1s();
		@Pc(82) int local82 = packet.g1();
		packet.g1();
		this.mipmapMode = local82 >> 4 & 0xF;
		if (combineRGBMode == 1) {
			this.combineRGBMode = 2;
		} else if (combineRGBMode == 2) {
			this.combineRGBMode = 3;
		} else if (combineRGBMode == 3) {
			this.combineRGBMode = 4;
		} else {
			this.combineRGBMode = 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(Lclient!m;IFLclient!ve;Z)[I")
	public int[] method4295(@OriginalArg(0) TextureProvider textureProvider, @OriginalArg(2) float brightness, @OriginalArg(3) Js5 spritesArchive, @OriginalArg(4) boolean lowmem) {
		if (this.pixels == null || this.brightness != brightness) {
			if (!this.texture.isReady(textureProvider, spritesArchive)) {
				return null;
			}
			@Pc(36) int size = lowmem ? 64 : 128;
			this.pixels = this.texture.getPixels(size, this.columnmajor, size, brightness, spritesArchive, textureProvider, true);
			this.brightness = brightness;
			if (this.aBoolean286) {
				@Pc(62) int[] reds = new int[size];
				@Pc(67) int[] pixels = new int[size * size];
				@Pc(70) int[] greens = new int[size];
				@Pc(73) int[] blues = new int[size];
				@Pc(80) int local80;
				@Pc(81) int local81 = local80 = size;
				@Pc(87) int local87 = size * size;
				@Pc(91) int local91 = size - 1;
				@Pc(95) int local95 = size - 1;
				@Pc(101) int local101;
				@Pc(97) int local97;
				for (local97 = 2; local97 >= 0; local97--) {
					for (local101 = local95; local101 >= 0; local101--) {
						local80--;
						@Pc(109) int local109 = this.pixels[local80];
						reds[local101] += local109 >> 16 & 0xFF;
						greens[local101] += local109 >> 8 & 0xFF;
						blues[local101] += local109 & 0xFF;
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
						local162 += greens[local169];
						local164 += blues[local169];
						local165 += reds[local169];
						if (local169 == 0) {
							local169 = size;
						}
					}
					for (local101 = local95; local101 >= 0; local101--) {
						local167--;
						@Pc(215) int local215 = local162 / 9;
						@Pc(219) int local219 = local164 / 9;
						local169--;
						local97 = local165 / 9;
						local152--;
						pixels[local152] = local219 | local97 << 16 | local215 << 8;
						local165 += reds[local169] - reds[local167];
						local164 += blues[local169] - blues[local167];
						local162 += greens[local169] - greens[local167];
						if (local167 == 0) {
							local167 = size;
						}
						if (local169 == 0) {
							local169 = size;
						}
					}
					for (local101 = local95; local101 >= 0; local101--) {
						local81--;
						@Pc(300) int local300 = this.pixels[local81];
						local80--;
						@Pc(306) int local306 = this.pixels[local80];
						reds[local101] += (local306 >> 16 & 0xFF) - (local300 >> 16 & 0xFF);
						greens[local101] += (local306 >> 8 & 0xFF) - (local300 >> 8 & 0xFF);
						blues[local101] += (local306 & 0xFF) - (local300 & 0xFF);
					}
					if (local81 == 0) {
						local81 = local87;
					}
					if (local80 == 0) {
						local80 = local87;
					}
				}
				this.pixels = pixels;
			}
		}
		return this.pixels;
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(Lclient!ve;Lclient!m;IZ)Z")
	public boolean method4296(@OriginalArg(0) Js5 archive, @OriginalArg(1) TextureProvider textureProvider, @OriginalArg(3) boolean lowmem) {
		if (!this.texture.isReady(textureProvider, archive)) {
			return false;
		}
		@Pc(22) GL2 gl = GlRenderer.gl;
		@Pc(28) int size = lowmem ? 64 : 128;
		@Pc(31) int flags = MaterialManager.getFlags();
		if ((flags & 0x1) == 0) {
			if (this.textureId == -1) {
				@Pc(53) int[] temp = new int[1];
				gl.glGenTextures(1, temp, 0);
				this.anInt5492 = GlCleaner.contextId;
				this.textureId = temp[0];
				GlRenderer.setTextureId(this.textureId);
				@Pc(82) ByteBuffer pixels = ByteBuffer.wrap(this.texture.method2728(size, size, this.columnmajor, textureProvider, 0.7D, archive));
				if (this.mipmapMode == 2) {
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
				} else if (this.mipmapMode == 1) {
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
						pixels = ByteBuffer.wrap(this.texture.method2728(size, size, this.columnmajor, textureProvider, 0.7D, archive));
					}
				} else {
					gl.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_RGBA, size, size, 0, GL2.GL_RGBA, GL2.GL_UNSIGNED_BYTE, pixels);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
					gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
					GlCleaner.oncard_texture += pixels.limit() - this.textureSize;
					this.textureSize = pixels.limit();
				}
				gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_S, this.wrapS ? GL2.GL_REPEAT : GL2.GL_CLAMP_TO_EDGE);
				gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_WRAP_T, this.wrapT ? GL2.GL_REPEAT : GL2.GL_CLAMP_TO_EDGE);
			} else {
				GlRenderer.setTextureId(this.textureId);
			}
		}
		if ((flags & 0x2) == 0) {
			GlRenderer.setTextureCombineRgbMode(this.combineRGBMode);
		}
		if ((flags & 0x4) == 0) {
			GlRenderer.setTextureCombineAlphaMode(0);
		}
		if ((flags & 0x8) == 0) {
			if (this.anInt5497 == 0 && this.anInt5485 == 0) {
				GlRenderer.resetTextureMatrix();
			} else {
				@Pc(312) float x = (float) (this.anInt5485 * GlRenderer.anInt5323) / (float) size;
				@Pc(303) float y = (float) (this.anInt5497 * GlRenderer.anInt5323) / (float) size;
				GlRenderer.translateTextureMatrix(x, y, 0.0F);
			}
		}
		return true;
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(ZZLclient!m;Lclient!ve;)[I")
	public int[] getPixels(@OriginalArg(1) boolean lowmem, @OriginalArg(2) TextureProvider textureProvider, @OriginalArg(3) Js5 archive) {
		if (this.texture.isReady(textureProvider, archive)) {
			@Pc(24) int size = lowmem ? 64 : 128;
			return this.texture.getPixels(size, this.columnmajor, size, 1.0D, archive, textureProvider, false);
		} else {
			return null;
		}
	}

	@OriginalMember(owner = "runetek4.client!uh", name = "a", descriptor = "(ILclient!m;Lclient!ve;)Z")
	public boolean isReady(@OriginalArg(1) TextureProvider textureProvider, @OriginalArg(2) Js5 archive) {
		return this.texture.isReady(textureProvider, archive);
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
