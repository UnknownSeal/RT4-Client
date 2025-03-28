package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.ColorUtils;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static156 {

	@OriginalMember(owner = "runetek4.client!mf", name = "a", descriptor = "()V")
	public static void method2959() {
		@Pc(1) GL2 local1 = GlRenderer.gl;
		local1.glDisableClientState(GL2.GL_COLOR_ARRAY);
		GlRenderer.setLightingEnabled(false);
		local1.glDisable(GL2.GL_DEPTH_TEST);
		local1.glPushAttrib(GL2.GL_FOG_BIT);
		local1.glFogf(GL2.GL_FOG_START, 3072.0F);
		GlRenderer.disableDepthMask();
		for (@Pc(19) int local19 = 0; local19 < SceneGraph.surfaceHdTiles[0].length; local19++) {
			@Pc(31) GlTile local31 = SceneGraph.surfaceHdTiles[0][local19];
			if (local31.texture >= 0 && Rasterizer.textureProvider.getMaterialType(local31.texture) == 4) {
				local1.glColor4fv(ColorUtils.getRgbFloat(local31.underwaterColor), 0);
				@Pc(57) float local57 = 201.5F - (local31.blend ? 1.0F : 0.5F);
				local31.method1944(SceneGraph.tiles, local57, true);
			}
		}
		local1.glEnableClientState(GL2.GL_COLOR_ARRAY);
		GlRenderer.restoreLighting();
		local1.glEnable(GL2.GL_DEPTH_TEST);
		local1.glPopAttrib();
		GlRenderer.enableDepthMask();
	}

	@OriginalMember(owner = "runetek4.client!mf", name = "a", descriptor = "(BLclient!wa;)Lclient!ta;")
	public static TextureOp29SubOp4 method2960(@OriginalArg(1) Packet arg0) {
		return new TextureOp29SubOp4(arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g2s(), arg0.g3(), arg0.g3(), arg0.g1());
	}
}
