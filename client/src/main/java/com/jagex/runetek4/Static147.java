package com.jagex.runetek4;

import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.scene.tile.ShapedTile;
import com.jagex.runetek4.util.ColorUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static147 {

	@OriginalMember(owner = "runetek4.client!lh", name = "b", descriptor = "(II)V")
	public static void method2761(@OriginalArg(0) int arg0) {
		UnderwaterMaterialRenderer.anInt3241 = arg0;
		MaterialManager.resetArgument(3);
		MaterialManager.resetArgument(4);
	}

	@OriginalMember(owner = "runetek4.client!lh", name = "a", descriptor = "(Lclient!fg;IIIIIIZ)V")
	public static void drawTileOverlay(@OriginalArg(0) ShapedTile overlay, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) boolean arg7) {
		@Pc(3) int vertexCount = overlay.anIntArray168.length;
		@Pc(5) int i;
		@Pc(15) int a;
		@Pc(22) int local22;
		@Pc(29) int local29;
		@Pc(39) int local39;
		for (i = 0; i < vertexCount; i++) {
			a = overlay.anIntArray168[i] - Static149.eyeX;
			local22 = overlay.anIntArray160[i] - Static162.eyeY;
			local29 = overlay.anIntArray163[i] - Static217.eyeZ;
			local39 = local29 * arg3 + a * arg4 >> 16;
			@Pc(49) int local49 = local29 * arg4 - a * arg3 >> 16;
			@Pc(61) int local61 = local22 * arg2 - local49 * arg1 >> 16;
			@Pc(71) int z = local22 * arg1 + local49 * arg2 >> 16;
			if (z < 50) {
				return;
			}
			if (overlay.triangleTextureIds != null) {
				Static68.anIntArray159[i] = local39;
				Static68.anIntArray170[i] = local61;
				Static68.tmpViewspaceZ[i] = z;
			}
			Static68.anIntArray165[i] = Rasterizer.centerX + (local39 << 9) / z;
			Static68.anIntArray164[i] = Rasterizer.centerY + (local61 << 9) / z;
		}
		Rasterizer.alpha = 0;
		vertexCount = overlay.triangleA.length;
		for (i = 0; i < vertexCount; i++) {
			a = overlay.triangleA[i];
			local22 = overlay.triangleB[i];
			local29 = overlay.triangleC[i];
			local39 = Static68.anIntArray165[a];
			@Pc(148) int local148 = Static68.anIntArray165[local22];
			@Pc(152) int local152 = Static68.anIntArray165[local29];
			@Pc(156) int local156 = Static68.anIntArray164[a];
			@Pc(160) int local160 = Static68.anIntArray164[local22];
			@Pc(164) int local164 = Static68.anIntArray164[local29];
			if ((local39 - local148) * (local164 - local160) - (local156 - local160) * (local152 - local148) > 0) {
				if (Static158.aBoolean187 && Static19.method583(Static89.anInt2388 + Rasterizer.centerX, Static131.anInt3259 + Rasterizer.centerY, local156, local160, local164, local39, local148, local152)) {
					Static56.clickTileX = arg5;
					Static116.anInt2954 = arg6;
				}
				if (!GlRenderer.enabled && !arg7) {
					Rasterizer.testX = false;
					if (local39 < 0 || local148 < 0 || local152 < 0 || local39 > Rasterizer.width || local148 > Rasterizer.width || local152 > Rasterizer.width) {
						Rasterizer.testX = true;
					}
					if (overlay.triangleTextureIds == null || overlay.triangleTextureIds[i] == -1) {
						if (overlay.triangleHSLA[i] != 12345678) {
							Rasterizer.fillGouraudTriangle(local156, local160, local164, local39, local148, local152, overlay.triangleHSLA[i], overlay.triangleHSLB[i], overlay.triangleHSLC[i]);
						}
					} else if (!Preferences.manyGroundTextures) {
						@Pc(373) int local373 = Rasterizer.textureProvider.getAverageColor(overlay.triangleTextureIds[i]);
						Rasterizer.fillGouraudTriangle(local156, local160, local164, local39, local148, local152, ColorUtils.multiplyLightness3(local373, overlay.triangleHSLA[i]), ColorUtils.multiplyLightness3(local373, overlay.triangleHSLB[i]), ColorUtils.multiplyLightness3(local373, overlay.triangleHSLC[i]));
					} else if (overlay.aBoolean113) {
						Rasterizer.fillTexturedTriangle(local156, local160, local164, local39, local148, local152, overlay.triangleHSLA[i], overlay.triangleHSLB[i], overlay.triangleHSLC[i], Static68.anIntArray159[0], Static68.anIntArray159[1], Static68.anIntArray159[3], Static68.anIntArray170[0], Static68.anIntArray170[1], Static68.anIntArray170[3], Static68.tmpViewspaceZ[0], Static68.tmpViewspaceZ[1], Static68.tmpViewspaceZ[3], overlay.triangleTextureIds[i]);
					} else {
						Rasterizer.fillTexturedTriangle(local156, local160, local164, local39, local148, local152, overlay.triangleHSLA[i], overlay.triangleHSLB[i], overlay.triangleHSLC[i], Static68.anIntArray159[a], Static68.anIntArray159[local22], Static68.anIntArray159[local29], Static68.anIntArray170[a], Static68.anIntArray170[local22], Static68.anIntArray170[local29], Static68.tmpViewspaceZ[a], Static68.tmpViewspaceZ[local22], Static68.tmpViewspaceZ[local29], overlay.triangleTextureIds[i]);
					}
				}
			}
		}
	}

}
