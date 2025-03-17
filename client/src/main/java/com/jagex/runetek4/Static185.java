package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.CollisionMap;
import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.dash3d.entity.LocEntity;
import com.jagex.runetek4.dash3d.entity.LocType;
import com.jagex.runetek4.scene.Scene;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static185 {

	@OriginalMember(owner = "runetek4.client!p", name = "e", descriptor = "I")
	public static int anInt4370;

	@OriginalMember(owner = "runetek4.client!p", name = "g", descriptor = "F")
	public static float aFloat23;

	@OriginalMember(owner = "runetek4.client!p", name = "c", descriptor = "Lclient!ih;")
	public static final LinkedList aClass69_101 = new LinkedList();

	@OriginalMember(owner = "runetek4.client!p", name = "d", descriptor = "I")
	public static int anInt4369 = 0;

    @OriginalMember(owner = "runetek4.client!p", name = "a", descriptor = "(IZIZLclient!mj;IIIBII)V")
	public static void method3397(@OriginalArg(0) int arg0, @OriginalArg(1) boolean lowmem, @OriginalArg(2) int level, @OriginalArg(3) boolean arg3, @OriginalArg(4) CollisionMap collsion, @OriginalArg(5) int arg5, @OriginalArg(6) int shape, @OriginalArg(7) int arg7, @OriginalArg(9) int z, @OriginalArg(10) int rotation) {
		if (lowmem && !Static138.allLevelsvisible() && (SceneGraph.renderFlags[0][arg7][z] & 0x2) == 0) {
			if ((SceneGraph.renderFlags[level][arg7][z] & 0x10) != 0) {
				return;
			}
			if (Static4.getRenderLevel(z, arg7, level) != Static41.anInt1316) {
				return;
			}
		}
		if (level < Static146.firstvisibleLevel) {
			Static146.firstvisibleLevel = level;
		}
		@Pc(62) LocType locType = LocTypeList.get(arg5);
		if (GlRenderer.enabled && locType.istexture) {
			return;
		}
		@Pc(84) int local84;
		@Pc(81) int local81;
		if (rotation == 1 || rotation == 3) {
			local81 = locType.width;
			local84 = locType.length;
		} else {
			local84 = locType.width;
			local81 = locType.length;
		}
		@Pc(103) int local103;
		@Pc(112) int local112;
		if (arg7 + local84 <= 104) {
			local103 = arg7 + (local84 >> 1);
			local112 = arg7 + (local84 + 1 >> 1);
		} else {
			local112 = arg7 + 1;
			local103 = arg7;
		}
		@Pc(129) int local129;
		@Pc(133) int local133;
		if (local81 + z > 104) {
			local129 = z;
			local133 = z + 1;
		} else {
			local129 = (local81 >> 1) + z;
			local133 = z + (local81 + 1 >> 1);
		}
		@Pc(153) int[][] local153 = SceneGraph.tileHeights[arg0];
		@Pc(165) int local165 = (local84 << 6) + (arg7 << 7);
		@Pc(173) int local173 = (local81 << 6) + (z << 7);
		@Pc(199) int local199 = local153[local103][local133] + local153[local112][local129] + local153[local103][local129] + local153[local112][local133] >> 2;
		@Pc(201) int local201 = 0;
		@Pc(213) int[][] local213;
		if (GlRenderer.enabled && arg0 != 0) {
			local213 = SceneGraph.tileHeights[0];
			local201 = local199 - (local213[local112][local133] + local213[local112][local129] + local213[local103][local129] + local213[local103][local133] >> 2);
		}
		local213 = null;
		@Pc(261) long local261 = (long) (arg7 | 0x40000000 | z << 7 | shape << 14 | rotation << 20);
		if (arg3) {
			local213 = Static107.anIntArrayArrayArray10[0];
		} else if (arg0 < 3) {
			local213 = SceneGraph.tileHeights[arg0 + 1];
		}
		if (locType.active == 0 || arg3) {
			local261 |= Long.MIN_VALUE;
		}
		if (locType.raiseobject == 1) {
			local261 |= 0x400000L;
		}
		if (locType.overrideSDFLO) {
			local261 |= 0x80000000L;
		}
		if (locType.hasBackgroundSound()) {
			AreaSoundManager.add(z, locType, rotation, null, arg7, level, null);
		}
		@Pc(330) boolean local330 = locType.hardshadow & !arg3;
		local261 |= (long) arg5 << 32;
		@Pc(387) Entity local387;
		@Pc(403) LocEntity local403;
		if (shape == 22) {
			if (Preferences.groundDecoration || locType.active != 0 || locType.blockwalk == 1 || locType.forcedecor) {
				if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
					local403 = locType.method3428(rotation, local165, local153, 22, local199, local213, lowmem, null, local330, local173);
					if (GlRenderer.enabled && local330) {
						Static242.method4211(local403.sprite, local165, local201, local173);
					}
					local387 = local403.model;
				} else {
					local387 = new Loc(arg5, 22, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
				}
				Scene.addGroundDecoration(level, arg7, z, local199, local387, local261, locType.renderUnderFeet);
				if (locType.blockwalk == 1 && collsion != null) {
					collsion.method3057(arg7, z);
				}
			}
		} else if (shape == 10 || shape == 11) {
			if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
				local403 = locType.method3428(shape == 11 ? rotation + 4 : rotation, local165, local153, 10, local199, local213, lowmem, null, local330, local173);
				if (GlRenderer.enabled && local330) {
					Static242.method4211(local403.sprite, local165, local201, local173);
				}
				local387 = local403.model;
			} else {
				local387 = new Loc(arg5, 10, shape == 11 ? rotation + 4 : rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
			}
			if (local387 != null) {
				@Pc(531) boolean local531 = Static5.method35(level, arg7, z, local199, local84, local81, local387, local261);
				if (locType.shadow && local531 && lowmem) {
					@Pc(541) int local541 = 15;
					if (local387 instanceof Model) {
						local541 = ((Model) local387).method4566() / 4;
						if (local541 > 30) {
							local541 = 30;
						}
					}
					for (@Pc(560) int local560 = 0; local560 <= local84; local560++) {
						for (@Pc(565) int local565 = 0; local565 <= local81; local565++) {
							if (Static118.levelShademap[level][arg7 + local560][local565 + z] < local541) {
								Static118.levelShademap[level][arg7 + local560][z + local565] = (byte) local541;
							}
						}
					}
				}
			}
			if (locType.blockwalk != 0 && collsion != null) {
				collsion.method3043(arg7, locType.blockrange, z, local84, local81);
			}
		} else if (shape >= 12) {
			if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
				local403 = locType.method3428(rotation, local165, local153, shape, local199, local213, lowmem, null, local330, local173);
				if (GlRenderer.enabled && local330) {
					Static242.method4211(local403.sprite, local165, local201, local173);
				}
				local387 = local403.model;
			} else {
				local387 = new Loc(arg5, shape, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
			}
			Static5.method35(level, arg7, z, local199, 1, 1, local387, local261);
			if (lowmem && shape >= 12 && shape <= 17 && shape != 13 && level > 0) {
				Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x4;
			}
			if (locType.blockwalk != 0 && collsion != null) {
				collsion.method3043(arg7, locType.blockrange, z, local84, local81);
			}
		} else if (shape == 0) {
			if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
				local403 = locType.method3428(rotation, local165, local153, 0, local199, local213, lowmem, null, local330, local173);
				if (GlRenderer.enabled && local330) {
					Static242.method4211(local403.sprite, local165, local201, local173);
				}
				local387 = local403.model;
			} else {
				local387 = new Loc(arg5, 0, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
			}
			Static262.addWall(level, arg7, z, local199, local387, null, Static267.ROTATION_WALL_TYPE[rotation], 0, local261);
			if (lowmem) {
				if (rotation == 0) {
					if (locType.shadow) {
						Static118.levelShademap[level][arg7][z] = 50;
						Static118.levelShademap[level][arg7][z + 1] = 50;
					}
					if (locType.occlude) {
						Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x1;
					}
				} else if (rotation == 1) {
					if (locType.shadow) {
						Static118.levelShademap[level][arg7][z + 1] = 50;
						Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
					}
					if (locType.occlude) {
						Static60.anIntArrayArrayArray6[level][arg7][z + 1] |= 0x2;
					}
				} else if (rotation == 2) {
					if (locType.shadow) {
						Static118.levelShademap[level][arg7 + 1][z] = 50;
						Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
					}
					if (locType.occlude) {
						Static60.anIntArrayArrayArray6[level][arg7 + 1][z] |= 0x1;
					}
				} else if (rotation == 3) {
					if (locType.shadow) {
						Static118.levelShademap[level][arg7][z] = 50;
						Static118.levelShademap[level][arg7 + 1][z] = 50;
					}
					if (locType.occlude) {
						Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x2;
					}
				}
			}
			if (locType.blockwalk != 0 && collsion != null) {
				collsion.addWall(arg7, z, shape, rotation, locType.blockrange);
			}
			if (locType.wallwidth != 16) {
				Static18.method559(level, arg7, z, locType.wallwidth);
			}
		} else if (shape == 1) {
			if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
				local403 = locType.method3428(rotation, local165, local153, 1, local199, local213, lowmem, null, local330, local173);
				if (GlRenderer.enabled && local330) {
					Static242.method4211(local403.sprite, local165, local201, local173);
				}
				local387 = local403.model;
			} else {
				local387 = new Loc(arg5, 1, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
			}
			Static262.addWall(level, arg7, z, local199, local387, null, Static78.ROTATION_WALL_CORNER_TYPE[rotation], 0, local261);
			if (locType.shadow && lowmem) {
				if (rotation == 0) {
					Static118.levelShademap[level][arg7][z + 1] = 50;
				} else if (rotation == 1) {
					Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
				} else if (rotation == 2) {
					Static118.levelShademap[level][arg7 + 1][z] = 50;
				} else if (rotation == 3) {
					Static118.levelShademap[level][arg7][z] = 50;
				}
			}
			if (locType.blockwalk != 0 && collsion != null) {
				collsion.addWall(arg7, z, shape, rotation, locType.blockrange);
			}
		} else {
			@Pc(1226) int local1226;
			if (shape == 2) {
				local1226 = rotation + 1 & 0x3;
				@Pc(1269) Entity local1269;
				@Pc(1254) Entity local1254;
				if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
					@Pc(1287) LocEntity local1287 = locType.method3428(rotation + 4, local165, local153, 2, local199, local213, lowmem, null, local330, local173);
					if (GlRenderer.enabled && local330) {
						Static242.method4211(local1287.sprite, local165, local201, local173);
					}
					local1254 = local1287.model;
					local1287 = locType.method3428(local1226, local165, local153, 2, local199, local213, lowmem, null, local330, local173);
					if (GlRenderer.enabled && local330) {
						Static242.method4211(local1287.sprite, local165, local201, local173);
					}
					local1269 = local1287.model;
				} else {
					local1254 = new Loc(arg5, 2, rotation + 4, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
					local1269 = new Loc(arg5, 2, local1226, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
				}
				Static262.addWall(level, arg7, z, local199, local1254, local1269, Static267.ROTATION_WALL_TYPE[rotation], Static267.ROTATION_WALL_TYPE[local1226], local261);
				if (locType.occlude && lowmem) {
					if (rotation == 0) {
						Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x1;
						Static60.anIntArrayArrayArray6[level][arg7][z + 1] |= 0x2;
					} else if (rotation == 1) {
						Static60.anIntArrayArrayArray6[level][arg7][z + 1] |= 0x2;
						Static60.anIntArrayArrayArray6[level][arg7 + 1][z] |= 0x1;
					} else if (rotation == 2) {
						Static60.anIntArrayArrayArray6[level][arg7 + 1][z] |= 0x1;
						Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x2;
					} else if (rotation == 3) {
						Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x2;
						Static60.anIntArrayArrayArray6[level][arg7][z] |= 0x1;
					}
				}
				if (locType.blockwalk != 0 && collsion != null) {
					collsion.addWall(arg7, z, shape, rotation, locType.blockrange);
				}
				if (locType.wallwidth != 16) {
					Static18.method559(level, arg7, z, locType.wallwidth);
				}
			} else if (shape == 3) {
				if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
					local403 = locType.method3428(rotation, local165, local153, 3, local199, local213, lowmem, null, local330, local173);
					if (GlRenderer.enabled && local330) {
						Static242.method4211(local403.sprite, local165, local201, local173);
					}
					local387 = local403.model;
				} else {
					local387 = new Loc(arg5, 3, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
				}
				Static262.addWall(level, arg7, z, local199, local387, null, Static78.ROTATION_WALL_CORNER_TYPE[rotation], 0, local261);
				if (locType.shadow && lowmem) {
					if (rotation == 0) {
						Static118.levelShademap[level][arg7][z + 1] = 50;
					} else if (rotation == 1) {
						Static118.levelShademap[level][arg7 + 1][z + 1] = 50;
					} else if (rotation == 2) {
						Static118.levelShademap[level][arg7 + 1][z] = 50;
					} else if (rotation == 3) {
						Static118.levelShademap[level][arg7][z] = 50;
					}
				}
				if (locType.blockwalk != 0 && collsion != null) {
					collsion.addWall(arg7, z, shape, rotation, locType.blockrange);
				}
			} else if (shape == 9) {
				if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
					local403 = locType.method3428(rotation, local165, local153, shape, local199, local213, lowmem, null, local330, local173);
					if (GlRenderer.enabled && local330) {
						Static242.method4211(local403.sprite, local165, local201, local173);
					}
					local387 = local403.model;
				} else {
					local387 = new Loc(arg5, shape, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
				}
				Static5.method35(level, arg7, z, local199, 1, 1, local387, local261);
				if (locType.blockwalk != 0 && collsion != null) {
					collsion.method3043(arg7, locType.blockrange, z, local84, local81);
				}
				if (locType.wallwidth != 16) {
					Static18.method559(level, arg7, z, locType.wallwidth);
				}
			} else if (shape == 4) {
				if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
					local403 = locType.method3428(rotation, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
					if (GlRenderer.enabled && local330) {
						Static242.method4211(local403.sprite, local165, local201, local173);
					}
					local387 = local403.model;
				} else {
					local387 = new Loc(arg5, 4, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
				}
				Static91.addWallDecoration(level, arg7, z, local199, local387, null, Static267.ROTATION_WALL_TYPE[rotation], 0, 0, 0, local261);
			} else {
				@Pc(1889) long local1889;
				@Pc(1934) Entity local1934;
				@Pc(1950) LocEntity local1950;
				if (shape == 5) {
					local1226 = 16;
					local1889 = SceneGraph.getWallKey(level, arg7, z);
					if (local1889 != 0L) {
						local1226 = LocTypeList.get(Integer.MAX_VALUE & (int) (local1889 >>> 32)).wallwidth;
					}
					if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
						local1950 = locType.method3428(rotation, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
						if (GlRenderer.enabled && local330) {
							Static242.method4211(local1950.sprite, local165 - Static34.WALL_DECORATION_ROTATION_FORWARD_X[rotation] * 8, local201, local173 - Static238.WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * 8);
						}
						local1934 = local1950.model;
					} else {
						local1934 = new Loc(arg5, 4, rotation, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
					}
					Static91.addWallDecoration(level, arg7, z, local199, local1934, null, Static267.ROTATION_WALL_TYPE[rotation], 0, local1226 * Static34.WALL_DECORATION_ROTATION_FORWARD_X[rotation], Static238.WALL_DECORATION_ROTATION_FORWARD_Z[rotation] * local1226, local261);
				} else if (shape == 6) {
					local1226 = 8;
					local1889 = SceneGraph.getWallKey(level, arg7, z);
					if (local1889 != 0L) {
						local1226 = LocTypeList.get(Integer.MAX_VALUE & (int) (local1889 >>> 32)).wallwidth / 2;
					}
					if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
						local1950 = locType.method3428(rotation + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
						if (GlRenderer.enabled && local330) {
							Static242.method4211(local1950.sprite, local165 - Static114.anIntArray565[rotation] * 8, local201, local173 - Static64.anIntArray154[rotation] * 8);
						}
						local1934 = local1950.model;
					} else {
						local1934 = new Loc(arg5, 4, rotation + 4, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
					}
					Static91.addWallDecoration(level, arg7, z, local199, local1934, null, 256, rotation, local1226 * Static114.anIntArray565[rotation], local1226 * Static64.anIntArray154[rotation], local261);
				} else if (shape == 7) {
					@Pc(2137) int local2137 = rotation + 2 & 0x3;
					if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
						@Pc(2183) LocEntity local2183 = locType.method3428(local2137 + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
						if (GlRenderer.enabled && local330) {
							Static242.method4211(local2183.sprite, local165, local201, local173);
						}
						local387 = local2183.model;
					} else {
						local387 = new Loc(arg5, 4, local2137 + 4, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
					}
					Static91.addWallDecoration(level, arg7, z, local199, local387, null, 256, local2137, 0, 0, local261);
				} else if (shape == 8) {
					local1226 = 8;
					local1889 = SceneGraph.getWallKey(level, arg7, z);
					if (local1889 != 0L) {
						local1226 = LocTypeList.get(Integer.MAX_VALUE & (int) (local1889 >>> 32)).wallwidth / 2;
					}
					@Pc(2244) int local2244 = rotation + 2 & 0x3;
					@Pc(2289) Entity local2289;
					if (locType.anim == -1 && locType.multiloc == null && !locType.aBoolean214) {
						@Pc(2297) int local2297 = Static64.anIntArray154[rotation] * 8;
						@Pc(2303) int local2303 = Static114.anIntArray565[rotation] * 8;
						@Pc(2319) LocEntity local2319 = locType.method3428(rotation + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
						if (GlRenderer.enabled && local330) {
							Static242.method4211(local2319.sprite, local165 - local2303, local201, local173 - local2297);
						}
						local1934 = local2319.model;
						local2319 = locType.method3428(local2244 + 4, local165, local153, 4, local199, local213, lowmem, null, local330, local173);
						if (GlRenderer.enabled && local330) {
							Static242.method4211(local2319.sprite, local165 - local2303, local201, local173 - local2297);
						}
						local2289 = local2319.model;
					} else {
						local1934 = new Loc(arg5, 4, rotation + 4, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
						local2289 = new Loc(arg5, 4, local2244 + 4, arg0, arg7, z, locType.anim, locType.randomanimframe, null);
					}
					Static91.addWallDecoration(level, arg7, z, local199, local1934, local2289, 256, rotation, local1226 * Static114.anIntArray565[rotation], Static64.anIntArray154[rotation] * local1226, local261);
				}
			}
		}
	}
}
