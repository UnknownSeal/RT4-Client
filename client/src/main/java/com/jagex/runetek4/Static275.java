package com.jagex.runetek4;

import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static275 {

	@OriginalMember(owner = "runetek4.client!wg", name = "b", descriptor = "Z")
	public static boolean aBoolean308 = false;

	@OriginalMember(owner = "runetek4.client!wg", name = "e", descriptor = "()I")
	public static int method4607() {
		return aBoolean308 ? 33986 : 33985;
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "f", descriptor = "()V")
	public static void method4608() {
		@Pc(1) GL2 local1 = GlRenderer.gl;
		local1.glClientActiveTexture(method4607());
		local1.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		local1.glClientActiveTexture(GL2.GL_TEXTURE0);
	}

	@OriginalMember(owner = "runetek4.client!wg", name = "g", descriptor = "()V")
	public static void method4609() {
		@Pc(1) GL2 local1 = GlRenderer.gl;
		local1.glClientActiveTexture(method4607());
		local1.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		local1.glClientActiveTexture(GL2.GL_TEXTURE0);
	}
}
