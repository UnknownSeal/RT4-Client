package com.jagex.runetek4;

import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.scene.tile.WallDecor;
import com.jogamp.opengl.*;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static248 {

	@OriginalMember(owner = "runetek4.client!uc", name = "d", descriptor = "[[[I")
	public static int[][][] anIntArrayArrayArray17;

	@OriginalMember(owner = "runetek4.client!uc", name = "a", descriptor = "(III[[[BIBII)V")
	public static void method3292(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) byte[][][] arg3, @OriginalArg(4) int arg4, @OriginalArg(5) byte arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		Static13.anInt437++;
		Static211.anInt1142 = 0;
		@Pc(9) int local9 = arg6 - 16;
		@Pc(13) int local13 = arg6 + 16;
		@Pc(17) int local17 = arg7 - 16;
		@Pc(21) int local21 = arg7 + 16;
		@Pc(32) int local32;
		@Pc(37) int local37;
		@Pc(183) int local183;
		for (@Pc(23) int local23 = Static235.anInt5276; local23 < Static126.anInt3114; local23++) {
			@Pc(30) Tile[][] local30 = Static130.levelTiles[local23];
			for (local32 = LightingManager.anInt987; local32 < LightingManager.anInt15; local32++) {
				for (local37 = LightingManager.anInt4698; local37 < LightingManager.anInt4866; local37++) {
					@Pc(46) Tile local46 = local30[local32][local37];
					if (local46 != null) {
						if (Static48.aBooleanArrayArray1[local32 + Static277.anInt5855 - Static167.eyeTileX][local37 + Static277.anInt5855 - Static193.anInt4539] && (arg3 == null || local23 < arg4 || arg3[local23][local32][local37] != arg5)) {
							local46.aBoolean45 = true;
							local46.aBoolean46 = true;
							if (local46.entityCount > 0) {
								local46.containsLocs = true;
							} else {
								local46.containsLocs = false;
							}
							Static211.anInt1142++;
						} else {
							local46.aBoolean45 = false;
							local46.aBoolean46 = false;
							local46.checkLocSpans = 0;
							if (local32 >= local9 && local32 <= local13 && local37 >= local17 && local37 <= local21) {
								if (local46.wall != null) {
									@Pc(103) Wall local103 = local46.wall;
									local103.primary.method4545(0, local23, local103.anInt3051, local103.anInt3048, local103.anInt3044);
									if (local103.modelB != null) {
										local103.modelB.method4545(0, local23, local103.anInt3051, local103.anInt3048, local103.anInt3044);
									}
								}
								if (local46.wallDecor != null) {
									@Pc(134) WallDecor local134 = local46.wallDecor;
									local134.primary.method4545(local134.anInt1388, local23, local134.anInt1391, local134.xFine, local134.zFine);
									if (local134.secondary != null) {
										local134.secondary.method4545(local134.anInt1388, local23, local134.anInt1391, local134.xFine, local134.zFine);
									}
								}
								if (local46.groundDecor != null) {
									@Pc(167) GroundDecor local167 = local46.groundDecor;
									local167.entity.method4545(0, local23, local167.anInt733, local167.xFine, local167.zFine);
								}
								if (local46.sceneries != null) {
									for (local183 = 0; local183 < local46.entityCount; local183++) {
										@Pc(192) Scenery local192 = local46.sceneries[local183];
										local192.entity.method4545(local192.anInt1714, local23, local192.anInt1706, local192.anInt1699, local192.anInt1703);
									}
								}
							}
						}
					}
				}
			}
		}
		@Pc(240) boolean local240 = SceneGraph.tileHeights == Static80.anIntArrayArrayArray19;
		if (GlRenderer.enabled) {
			@Pc(244) GL2 local244 = GlRenderer.gl;
			local244.glPushMatrix();
			local244.glTranslatef((float) -arg0, (float) -arg1, (float) -arg2);
			if (local240) {
				Static156.method2959();
				MaterialManager.setMaterial(-1, 3);
				MaterialManager.renderingUnderwater = true;
				Static275.method4609();
				Static152.anInt3604 = -1;
				Static22.anInt730 = -1;
				for (local32 = 0; local32 < Static182.aGlTileArrayArray2[0].length; local32++) {
					@Pc(285) GlTile local285 = Static182.aGlTileArrayArray2[0][local32];
					@Pc(294) float local294 = 251.5F - (local285.blend ? 1.0F : 0.5F);
					if (local285.underwaterColor != Static152.anInt3604) {
						Static152.anInt3604 = local285.underwaterColor;
						Static21.method619(local285.underwaterColor);
						FogManager.setFogColor(Static123.method2422());
					}
					local285.method1944(Static130.levelTiles, local294, false);
				}
				Static275.method4608();
			} else {
				local32 = Static235.anInt5276;
				while (true) {
					if (local32 >= Static126.anInt3114) {
						LightingManager.method2402(Static167.eyeTileX, Static193.anInt4539, Static130.levelTiles);
						break;
					}
					for (local37 = 0; local37 < Static182.aGlTileArrayArray2[local32].length; local37++) {
						@Pc(336) GlTile local336 = Static182.aGlTileArrayArray2[local32][local37];
						@Pc(350) float local350 = 201.5F - (float) local32 * 50.0F - (local336.blend ? 1.0F : 0.5F);
						if (local336.texture != -1 && Rasterizer.textureProvider.getMaterialType(local336.texture) == 4 && Preferences.highWaterDetail) {
							Static21.method619(local336.underwaterColor);
						}
						local336.method1944(Static130.levelTiles, local350, false);
					}
					if (local32 == 0 && Static139.anInt3451 > 0) {
						GlRenderer.method4159(101.5F);
						Static242.method4198(Static167.eyeTileX, Static193.anInt4539, Static277.anInt5855, arg1, Static48.aBooleanArrayArray1, SceneGraph.tileHeights[0]);
					}
					local32++;
				}
			}
			local244.glPopMatrix();
		}
		@Pc(434) int local434;
		@Pc(438) int local438;
		@Pc(450) Tile local450;
		@Pc(399) int local399;
		@Pc(406) Tile[][] local406;
		@Pc(415) int local415;
		@Pc(428) int local428;
		for (local399 = Static235.anInt5276; local399 < Static126.anInt3114; local399++) {
			local406 = Static130.levelTiles[local399];
			for (local37 = -Static277.anInt5855; local37 <= 0; local37++) {
				local415 = Static167.eyeTileX + local37;
				local183 = Static167.eyeTileX - local37;
				if (local415 >= LightingManager.anInt987 || local183 < LightingManager.anInt15) {
					for (local428 = -Static277.anInt5855; local428 <= 0; local428++) {
						local434 = Static193.anInt4539 + local428;
						local438 = Static193.anInt4539 - local428;
						if (local415 >= LightingManager.anInt987) {
							if (local434 >= LightingManager.anInt4698) {
								local450 = local406[local415][local434];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, true);
								}
							}
							if (local438 < LightingManager.anInt4866) {
								local450 = local406[local415][local438];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, true);
								}
							}
						}
						if (local183 < LightingManager.anInt15) {
							if (local434 >= LightingManager.anInt4698) {
								local450 = local406[local183][local434];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, true);
								}
							}
							if (local438 < LightingManager.anInt4866) {
								local450 = local406[local183][local438];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, true);
								}
							}
						}
						if (Static211.anInt1142 == 0) {
							if (!local240) {
								Static158.aBoolean187 = false;
							}
							return;
						}
					}
				}
			}
		}
		for (local399 = Static235.anInt5276; local399 < Static126.anInt3114; local399++) {
			local406 = Static130.levelTiles[local399];
			for (local37 = -Static277.anInt5855; local37 <= 0; local37++) {
				local415 = Static167.eyeTileX + local37;
				local183 = Static167.eyeTileX - local37;
				if (local415 >= LightingManager.anInt987 || local183 < LightingManager.anInt15) {
					for (local428 = -Static277.anInt5855; local428 <= 0; local428++) {
						local434 = Static193.anInt4539 + local428;
						local438 = Static193.anInt4539 - local428;
						if (local415 >= LightingManager.anInt987) {
							if (local434 >= LightingManager.anInt4698) {
								local450 = local406[local415][local434];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, false);
								}
							}
							if (local438 < LightingManager.anInt4866) {
								local450 = local406[local415][local438];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, false);
								}
							}
						}
						if (local183 < LightingManager.anInt15) {
							if (local434 >= LightingManager.anInt4698) {
								local450 = local406[local183][local434];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, false);
								}
							}
							if (local438 < LightingManager.anInt4866) {
								local450 = local406[local183][local438];
								if (local450 != null && local450.aBoolean45) {
									Static247.method4245(local450, false);
								}
							}
						}
						if (Static211.anInt1142 == 0) {
							if (!local240) {
								Static158.aBoolean187 = false;
							}
							return;
						}
					}
				}
			}
		}
		Static158.aBoolean187 = false;
	}
}
