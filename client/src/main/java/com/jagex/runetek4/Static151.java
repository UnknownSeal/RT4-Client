package com.jagex.runetek4;

import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static151 {

	@OriginalMember(owner = "runetek4.client!lm", name = "e", descriptor = "Z")
	public static boolean aBoolean176;

	@OriginalMember(owner = "runetek4.client!lm", name = "a", descriptor = "[I")
	public static int[] anIntArray340 = null;

	@OriginalMember(owner = "runetek4.client!lm", name = "b", descriptor = "[I")
	public static int[] anIntArray341 = null;

	@OriginalMember(owner = "runetek4.client!lm", name = "f", descriptor = "I")
	public static int anInt3587 = -1;

	@OriginalMember(owner = "runetek4.client!lm", name = "g", descriptor = "I")
	public static int anInt3588 = -1;

	@OriginalMember(owner = "runetek4.client!lm", name = "b", descriptor = "()V")
	public static void method2808() {
		@Pc(4) GL2 local4;
		@Pc(11) int[] local11;
		if (anInt3587 != -1) {
			local4 = GlRenderer.gl;
			local11 = new int[] { anInt3587 };
			local4.glDeleteTextures(1, local11, 0);
			anInt3587 = -1;
			GlCleaner.oncard_texture -= MaterialManager.aByteBuffer7.limit() * 2;
		}
		if (anIntArray341 != null) {
			local4 = GlRenderer.gl;
			local4.glDeleteTextures(64, anIntArray341, 0);
			anIntArray341 = null;
			GlCleaner.oncard_texture -= MaterialManager.aByteBuffer7.limit() * 2;
		}
		if (anInt3588 != -1) {
			local4 = GlRenderer.gl;
			local11 = new int[] { anInt3588 };
			local4.glDeleteTextures(1, local11, 0);
			anInt3588 = -1;
			GlCleaner.oncard_texture -= MaterialManager.aByteBuffer6.limit() * 2;
		}
		if (anIntArray340 != null) {
			local4 = GlRenderer.gl;
			local4.glDeleteTextures(64, anIntArray340, 0);
			anIntArray340 = null;
			GlCleaner.oncard_texture -= MaterialManager.aByteBuffer6.limit() * 2;
		}
	}

	@OriginalMember(owner = "runetek4.client!lm", name = "c", descriptor = "()V")
	public static void method2809() {
		aBoolean176 = GlRenderer.extTexture3dSupported;
		MaterialManager.method2807();
		method2811();
		method2812();
	}

	@OriginalMember(owner = "runetek4.client!lm", name = "e", descriptor = "()V")
	private static void method2811() {
		@Pc(1) GL2 local1 = GlRenderer.gl;
		if (aBoolean176) {
			@Pc(6) int[] local6 = new int[1];
			local1.glGenTextures(1, local6, 0);
			local1.glBindTexture(GL2.GL_TEXTURE_3D, local6[0]);
			MaterialManager.aByteBuffer7.position(0);
			local1.glTexImage3D(GL2.GL_TEXTURE_3D, 0, GL2.GL_LUMINANCE_ALPHA, 64, 64, 64, 0, GL2.GL_LUMINANCE_ALPHA, GL2.GL_UNSIGNED_BYTE, MaterialManager.aByteBuffer7);
			local1.glTexParameteri(GL2.GL_TEXTURE_3D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
			local1.glTexParameteri(GL2.GL_TEXTURE_3D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
			anInt3587 = local6[0];
			GlCleaner.oncard_texture += MaterialManager.aByteBuffer7.limit() * 2;
			return;
		}
		anIntArray341 = new int[64];
		local1.glGenTextures(64, anIntArray341, 0);
		for (@Pc(65) int local65 = 0; local65 < 64; local65++) {
			GlRenderer.setTextureId(anIntArray341[local65]);
			MaterialManager.aByteBuffer7.position(local65 * 64 * 64 * 2);
			local1.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_LUMINANCE_ALPHA, 64, 64, 0, GL2.GL_LUMINANCE_ALPHA, GL2.GL_UNSIGNED_BYTE, MaterialManager.aByteBuffer7);
			local1.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
			local1.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		}
		GlCleaner.oncard_texture += MaterialManager.aByteBuffer7.limit() * 2;
	}

	@OriginalMember(owner = "runetek4.client!lm", name = "f", descriptor = "()V")
	private static void method2812() {
		@Pc(1) GL2 local1 = GlRenderer.gl;
		if (aBoolean176) {
			@Pc(6) int[] local6 = new int[1];
			local1.glGenTextures(1, local6, 0);
			local1.glBindTexture(GL2.GL_TEXTURE_3D, local6[0]);
			MaterialManager.aByteBuffer6.position(0);
			local1.glTexImage3D(GL2.GL_TEXTURE_3D, 0, GL2.GL_LUMINANCE_ALPHA, 64, 64, 64, 0, GL2.GL_LUMINANCE_ALPHA, GL2.GL_UNSIGNED_BYTE, MaterialManager.aByteBuffer6);
			local1.glTexParameteri(GL2.GL_TEXTURE_3D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
			local1.glTexParameteri(GL2.GL_TEXTURE_3D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
			anInt3588 = local6[0];
			GlCleaner.oncard_texture += MaterialManager.aByteBuffer6.limit() * 2;
			return;
		}
		anIntArray340 = new int[64];
		local1.glGenTextures(64, anIntArray340, 0);
		for (@Pc(65) int local65 = 0; local65 < 64; local65++) {
			GlRenderer.setTextureId(anIntArray340[local65]);
			MaterialManager.aByteBuffer6.position(local65 * 64 * 64 * 2);
			local1.glTexImage2D(GL2.GL_TEXTURE_2D, 0, GL2.GL_LUMINANCE_ALPHA, 64, 64, 0, GL2.GL_LUMINANCE_ALPHA, GL2.GL_UNSIGNED_BYTE, MaterialManager.aByteBuffer6);
			local1.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
			local1.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
		}
		GlCleaner.oncard_texture += MaterialManager.aByteBuffer6.limit() * 2;
	}
}
