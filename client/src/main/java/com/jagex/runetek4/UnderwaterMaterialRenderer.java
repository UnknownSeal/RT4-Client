package com.jagex.runetek4;

import java.nio.ByteBuffer;

import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.ColorUtils;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!wg")
public final class UnderwaterMaterialRenderer implements MaterialRenderer {

	@OriginalMember(owner = "runetek4.client!nh", name = "Z", descriptor = "I")
	public static int anInt3241 = 128;
	@OriginalMember(owner = "runetek4.client!wg", name = "b", descriptor = "Z")
	public static boolean aBoolean308 = false;
	@OriginalMember(owner = "runetek4.client!wg", name = "c", descriptor = "I")
	private int anInt5805 = -1;

	@OriginalMember(owner = "runetek4.client!wg", name = "a", descriptor = "[F")
	private final float[] aFloatArray29 = new float[4];

	@OriginalMember(owner = "runetek4.client!wg", name = "d", descriptor = "I")
	private int anInt5806 = -1;

	@OriginalMember(owner = "runetek4.client!wg", name = "<init>", descriptor = "()V")
	public UnderwaterMaterialRenderer() {
		if (GlRenderer.maxTextureUnits >= 2) {
			@Pc(17) int[] local17 = new int[1];
			@Pc(20) byte[] local20 = new byte[8];
			@Pc(22) int local22 = 0;
			while (local22 < 8) {
				local20[local22++] = (byte) (local22 * 159 / 8 + 96);
			}
			@Pc(40) GL2 gl = GlRenderer.gl;
			gl.glGenTextures(1, local17, 0);
			gl.glBindTexture(GL2.GL_TEXTURE_1D, local17[0]);
			gl.glTexImage1D(GL2.GL_TEXTURE_1D, 0, GL2.GL_ALPHA, 8, 0, GL2.GL_ALPHA, GL2.GL_UNSIGNED_BYTE, ByteBuffer.wrap(local20));
			gl.glTexParameteri(GL2.GL_TEXTURE_1D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_LINEAR);
			gl.glTexParameteri(GL2.GL_TEXTURE_1D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_LINEAR);
			gl.glTexParameteri(GL2.GL_TEXTURE_1D, GL2.GL_TEXTURE_WRAP_S, GL2.GL_CLAMP_TO_EDGE);
			this.anInt5805 = local17[0];
			aBoolean308 = GlRenderer.maxTextureUnits > 2 && GlRenderer.extTexture3dSupported;
			this.method4606();
		}
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "e", descriptor = "()I")
	public static int method4607() {
		return aBoolean308 ? 33986 : 33985;
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "f", descriptor = "()V")
	public static void method4608() {
		@Pc(1) GL2 gl = GlRenderer.gl;
		gl.glClientActiveTexture(method4607());
		gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glClientActiveTexture(GL2.GL_TEXTURE0);
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "g", descriptor = "()V")
	public static void method4609() {
		@Pc(1) GL2 gl = GlRenderer.gl;
		gl.glClientActiveTexture(method4607());
		gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glClientActiveTexture(GL2.GL_TEXTURE0);
	}

	@OriginalMember(owner = "runetek4.client!mf", name = "a", descriptor = "()V")
	public static void method2959() {
		@Pc(1) GL2 gl = GlRenderer.gl;
		gl.glDisableClientState(GL2.GL_COLOR_ARRAY);
		GlRenderer.setLightingEnabled(false);
		gl.glDisable(GL2.GL_DEPTH_TEST);
		gl.glPushAttrib(GL2.GL_FOG_BIT);
		gl.glFogf(GL2.GL_FOG_START, 3072.0F);
		GlRenderer.disableDepthMask();
		for (@Pc(19) int local19 = 0; local19 < SceneGraph.surfaceHdTiles[0].length; local19++) {
			@Pc(31) GlTile local31 = SceneGraph.surfaceHdTiles[0][local19];
			if (local31.texture >= 0 && Rasterizer.textureProvider.getMaterialType(local31.texture) == 4) {
				gl.glColor4fv(ColorUtils.getRgbFloat(local31.underwaterColor), 0);
				@Pc(57) float local57 = 201.5F - (local31.blend ? 1.0F : 0.5F);
				local31.method1944(SceneGraph.tiles, local57, true);
			}
		}
		gl.glEnableClientState(GL2.GL_COLOR_ARRAY);
		GlRenderer.restoreLighting();
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glPopAttrib();
		GlRenderer.enableDepthMask();
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "d", descriptor = "()V")
	private void method4606() {
		@Pc(1) GL2 gl = GlRenderer.gl;
		this.anInt5806 = gl.glGenLists(2);
		gl.glNewList(this.anInt5806, GL2.GL_COMPILE);
		gl.glActiveTexture(GL2.GL_TEXTURE1);
		if (aBoolean308) {
			gl.glBindTexture(GL2.GL_TEXTURE_3D, MaterialManager.texture3D);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_ADD);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_OPERAND0_RGB, GL2.GL_SRC_COLOR);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_ALPHA, GL2.GL_REPLACE);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_ALPHA, GL2.GL_PREVIOUS);
			gl.glTexGeni(GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_EYE_LINEAR);
			gl.glTexGeni(GL2.GL_R, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_EYE_LINEAR);
			gl.glTexGeni(GL2.GL_T, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_EYE_LINEAR);
			gl.glTexGeni(GL2.GL_Q, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_OBJECT_LINEAR);
			gl.glTexGenfv(GL2.GL_Q, GL2.GL_OBJECT_PLANE, new float[] { 0.0F, 0.0F, 0.0F, 1.0F }, 0);
			gl.glEnable(GL2.GL_TEXTURE_GEN_S);
			gl.glEnable(GL2.GL_TEXTURE_GEN_T);
			gl.glEnable(GL2.GL_TEXTURE_GEN_R);
			gl.glEnable(GL2.GL_TEXTURE_GEN_Q);
			gl.glEnable(GL2.GL_TEXTURE_3D);
			gl.glActiveTexture(GL2.GL_TEXTURE2);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_COMBINE);
		}
		gl.glBindTexture(GL2.GL_TEXTURE_1D, this.anInt5805);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_INTERPOLATE);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_RGB, GL2.GL_CONSTANT);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC2_RGB, GL2.GL_TEXTURE);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_ALPHA, GL2.GL_REPLACE);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_ALPHA, GL2.GL_PREVIOUS);
		gl.glTexGeni(GL2.GL_S, GL2.GL_TEXTURE_GEN_MODE, GL2.GL_EYE_LINEAR);
		gl.glEnable(GL2.GL_TEXTURE_1D);
		gl.glEnable(GL2.GL_TEXTURE_GEN_S);
		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glEndList();
		gl.glNewList(this.anInt5806 + 1, GL2.GL_COMPILE);
		gl.glActiveTexture(GL2.GL_TEXTURE1);
		if (aBoolean308) {
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_MODULATE);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_OPERAND0_RGB, GL2.GL_SRC_COLOR);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_ALPHA, GL2.GL_MODULATE);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_ALPHA, GL2.GL_TEXTURE);
			gl.glDisable(GL2.GL_TEXTURE_GEN_S);
			gl.glDisable(GL2.GL_TEXTURE_GEN_T);
			gl.glDisable(GL2.GL_TEXTURE_GEN_R);
			gl.glDisable(GL2.GL_TEXTURE_GEN_Q);
			gl.glDisable(GL2.GL_TEXTURE_3D);
			gl.glActiveTexture(GL2.GL_TEXTURE2);
			gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_MODE, GL2.GL_MODULATE);
		}
		gl.glTexEnvfv(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_COLOR, new float[] { 0.0F, 1.0F, 0.0F, 1.0F }, 0);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_RGB, GL2.GL_MODULATE);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_RGB, GL2.GL_TEXTURE);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC2_RGB, GL2.GL_CONSTANT);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_COMBINE_ALPHA, GL2.GL_MODULATE);
		gl.glTexEnvi(GL2.GL_TEXTURE_ENV, GL2.GL_SRC0_ALPHA, GL2.GL_TEXTURE);
		gl.glDisable(GL2.GL_TEXTURE_1D);
		gl.glDisable(GL2.GL_TEXTURE_GEN_S);
		gl.glActiveTexture(GL2.GL_TEXTURE0);
		gl.glEndList();
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "b", descriptor = "()V")
	@Override
	public final void bind() {
		@Pc(1) GL2 gl = GlRenderer.gl;
		gl.glCallList(this.anInt5806);
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "c", descriptor = "()I")
	@Override
	public final int getFlags() {
		return 0;
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "a", descriptor = "()V")
	@Override
	public final void unbind() {
		@Pc(1) GL2 gl = GlRenderer.gl;
		gl.glCallList(this.anInt5806 + 1);
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "a", descriptor = "(I)V")
	@Override
	public final void setArgument(@OriginalArg(0) int arg0) {
		@Pc(1) GL2 gl = GlRenderer.gl;
		gl.glActiveTexture(GL2.GL_TEXTURE1);
		if (aBoolean308 || arg0 >= 0) {
			gl.glPushMatrix();
			gl.glLoadIdentity();
			gl.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
			gl.glRotatef((float) MaterialManager.anInt5559 * 360.0F / 2048.0F, 1.0F, 0.0F, 0.0F);
			gl.glRotatef((float) MaterialManager.anInt1815 * 360.0F / 2048.0F, 0.0F, 1.0F, 0.0F);
			gl.glTranslatef((float) -MaterialManager.anInt406, (float) -MaterialManager.anInt4675, (float) -MaterialManager.anInt5158);
			if (aBoolean308) {
				this.aFloatArray29[0] = 0.001F;
				this.aFloatArray29[1] = 9.0E-4F;
				this.aFloatArray29[2] = 0.0F;
				this.aFloatArray29[3] = 0.0F;
				gl.glTexGenfv(GL2.GL_S, GL2.GL_EYE_PLANE, this.aFloatArray29, 0);
				this.aFloatArray29[0] = 0.0F;
				this.aFloatArray29[1] = 9.0E-4F;
				this.aFloatArray29[2] = 0.001F;
				this.aFloatArray29[3] = 0.0F;
				gl.glTexGenfv(GL2.GL_T, GL2.GL_EYE_PLANE, this.aFloatArray29, 0);
				this.aFloatArray29[0] = 0.0F;
				this.aFloatArray29[1] = 0.0F;
				this.aFloatArray29[2] = 0.0F;
				this.aFloatArray29[3] = (float) GlRenderer.anInt5323 * 0.005F;
				gl.glTexGenfv(GL2.GL_R, GL2.GL_EYE_PLANE, this.aFloatArray29, 0);
				gl.glActiveTexture(GL2.GL_TEXTURE2);
			}
			gl.glTexEnvfv(GL2.GL_TEXTURE_ENV, GL2.GL_TEXTURE_ENV_COLOR, WaterMaterialRenderer.method2422(), 0);
			if (arg0 >= 0) {
				this.aFloatArray29[0] = 0.0F;
				this.aFloatArray29[1] = 1.0F / (float) anInt3241;
				this.aFloatArray29[2] = 0.0F;
				this.aFloatArray29[3] = (float) arg0 * 1.0F / (float) anInt3241;
				gl.glTexGenfv(GL2.GL_S, GL2.GL_EYE_PLANE, this.aFloatArray29, 0);
				gl.glEnable(GL2.GL_TEXTURE_GEN_S);
			} else {
				gl.glDisable(GL2.GL_TEXTURE_GEN_S);
			}
			gl.glPopMatrix();
		} else {
			gl.glDisable(GL2.GL_TEXTURE_GEN_S);
		}
		gl.glActiveTexture(GL2.GL_TEXTURE0);
	}
}
