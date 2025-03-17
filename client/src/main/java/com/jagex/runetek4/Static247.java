package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.cache.def.ItemDefinition;
import com.jagex.runetek4.cache.def.SpotAnimDefinition;
import com.jagex.runetek4.dash3d.entity.ObjStackEntity;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.SceneTile;
import com.jagex.runetek4.scene.tile.Wall;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static247 {

	@OriginalMember(owner = "runetek4.client!ub", name = "h", descriptor = "[Lclient!pe;")
	public static Class120[] aClass120Array2;

	@OriginalMember(owner = "runetek4.client!ub", name = "m", descriptor = "I")
	public static int anInt5405;

	@OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(IIIIIII)V")
	public static void method4244(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		if (Static172.anInt4164 <= arg1 && FluTypeList.anInt5063 >= arg3 && Static267.anInt5773 <= arg2 && arg4 <= Static106.anInt2869) {
			if (arg5 == 1) {
				Static134.method2622(arg0, arg3, arg2, arg4, arg1);
			} else {
				Static183.method3334(arg3, arg2, arg0, arg4, arg5, arg1);
			}
		} else if (arg5 == 1) {
			Static173.method3246(arg0, arg1, arg4, arg3, arg2);
		} else {
			Static10.method352(arg4, arg5, arg3, arg1, arg0, arg2);
		}
	}

	@OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(Lclient!bj;Z)V")
	public static void method4245(@OriginalArg(0) SceneTile arg0, @OriginalArg(1) boolean arg1) {
		aClass6.drawTileQueue.addTail(arg0);
		while (true) {
			@Pc(8) SceneTile tile;
			@Pc(18) int tileX;
			@Pc(21) int tileZ;
			@Pc(24) int local24;
			@Pc(27) int occludeLevel;
			@Pc(31) SceneTile[][] tiles;
			@Pc(65) int frontWallTypes;
			@Pc(115) int farthestIndex;
			@Pc(894) int x;
			@Pc(899) int y;
			@Pc(904) int z;
			@Pc(153) SceneTile local153;
			@Pc(1332) int local1332;
			do {
				do {
					do {
						do {
							do {
								do {
									while (true) {
										@Pc(44) int var9;
										@Pc(48) int direction;
										@Pc(907) int rotation;
										@Pc(916) int nearestX;
										@Pc(363) Wall var22;
										@Pc(469) boolean tileDrawn;
										@Pc(425) Scenery var25;
										@Pc(1179) SceneTile adjacent;
										while (true) {
											do {
												tile = (SceneTile) aClass6.drawTileQueue.removeHead();
												if (tile == null) {
													return;
												}
											} while (!tile.aBoolean46);
											tileX = tile.anInt669;
											tileZ = tile.anInt666;
											local24 = tile.anInt672;
											occludeLevel = tile.anInt668;
											tiles = Static130.levelTiles[local24];
											@Pc(33) float local33 = 0.0F;
											if (GlRenderer.enabled) {
												if (Static80.anIntArrayArrayArray19 == SceneGraph.tileHeights) {
													var9 = Static62.anIntArrayArray11[tileX][tileZ];
													direction = var9 & 0xFFFFFF;
													if (direction != Static152.anInt3604) {
														Static152.anInt3604 = direction;
														Static21.method619(direction);
														Static161.method3066(Static123.method2422());
													}
													frontWallTypes = var9 >>> 24 << 3;
													if (frontWallTypes != Static22.anInt730) {
														Static22.anInt730 = frontWallTypes;
														Static147.method2761(frontWallTypes);
													}
													farthestIndex = Static107.anIntArrayArrayArray10[0][tileX][tileZ] + Static107.anIntArrayArrayArray10[0][tileX + 1][tileZ] + Static107.anIntArrayArrayArray10[0][tileX][tileZ + 1] + Static107.anIntArrayArrayArray10[0][tileX + 1][tileZ + 1] >> 2;
													Static27.setMaterial(-farthestIndex, 3);
													local33 = 201.5F;
													GlRenderer.method4159(local33);
												} else {
													local33 = 201.5F - (float) (occludeLevel + 1) * 50.0F;
													GlRenderer.method4159(local33);
												}
											}
											if (!tile.aBoolean45) {
												break;
											}
											if (arg1) {
												if (local24 > 0) {
													local153 = Static130.levelTiles[local24 - 1][tileX][tileZ];
													if (local153 != null && local153.aBoolean46) {
														continue;
													}
												}
												if (tileX <= Static167.eyeTileX && tileX > Static31.anInt987) {
													local153 = tiles[tileX - 1][tileZ];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (tile.locSpans & 0x1) == 0)) {
														continue;
													}
												}
												if (tileX >= Static167.eyeTileX && tileX < Static2.anInt15 - 1) {
													local153 = tiles[tileX + 1][tileZ];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (tile.locSpans & 0x4) == 0)) {
														continue;
													}
												}
												if (tileZ <= Static193.anInt4539 && tileZ > Static80.anInt4698) {
													local153 = tiles[tileX][tileZ - 1];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (tile.locSpans & 0x8) == 0)) {
														continue;
													}
												}
												if (tileZ >= Static193.anInt4539 && tileZ < Static215.anInt4866 - 1) {
													local153 = tiles[tileX][tileZ + 1];
													if (local153 != null && local153.aBoolean46 && (local153.aBoolean45 || (tile.locSpans & 0x2) == 0)) {
														continue;
													}
												}
											} else {
												arg1 = true;
											}
											tile.aBoolean45 = false;
											if (tile.aClass3_Sub5_1 != null) {
												local153 = tile.aClass3_Sub5_1;
												if (GlRenderer.enabled) {
													GlRenderer.method4159(201.5F - (float) (local153.anInt668 + 1) * 50.0F);
												}
												if (local153.plainTile == null) {
													if (local153.shapedTile != null) {
														if (CacheArchive.method187(0, tileX, tileZ)) {
															Static147.drawTileOverlay(local153.shapedTile, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, true);
														} else {
															Static147.drawTileOverlay(local153.shapedTile, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, false);
														}
													}
												} else if (CacheArchive.method187(0, tileX, tileZ)) {
													Static132.method2610(local153.plainTile, 0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, true);
												} else {
													Static132.method2610(local153.plainTile, 0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, false);
												}
												var22 = local153.wall;
												if (var22 != null) {
													if (GlRenderer.enabled) {
														if ((var22.typeA & tile.backWallTypes) == 0) {
															Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
														} else {
															Static120.method2388(var22.typeA, Static149.eyeX, Static162.eyeY, Static217.eyeZ, occludeLevel, tileX, tileZ);
														}
													}
													var22.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, var22.anInt3048 - Static149.eyeX, var22.anInt3051 - Static162.eyeY, var22.anInt3044 - Static217.eyeZ, var22.aLong107, local24, null);
												}
												for (frontWallTypes = 0; frontWallTypes < local153.entityCount; frontWallTypes++) {
													var25 = local153.sceneries[frontWallTypes];
													if (var25 != null) {
														if (GlRenderer.enabled) {
															Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
														}
														var25.entity.render(var25.anInt1714, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, var25.anInt1699 - Static149.eyeX, var25.anInt1706 - Static162.eyeY, var25.anInt1703 - Static217.eyeZ, var25.hash, local24, null);
													}
												}
												if (GlRenderer.enabled) {
													GlRenderer.method4159(local33);
												}
											}
											tileDrawn = false;
											if (tile.plainTile == null) {
												if (tile.shapedTile != null) {
													if (CacheArchive.method187(occludeLevel, tileX, tileZ)) {
														Static147.drawTileOverlay(tile.shapedTile, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, true);
													} else {
														tileDrawn = true;
														Static147.drawTileOverlay(tile.shapedTile, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, false);
													}
												}
											} else if (CacheArchive.method187(occludeLevel, tileX, tileZ)) {
												Static132.method2610(tile.plainTile, occludeLevel, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, true);
											} else {
												tileDrawn = true;
												if (tile.plainTile.anInt4865 != 12345678 || Static158.aBoolean187 && local24 <= Static160.anInt3902) {
													Static132.method2610(tile.plainTile, occludeLevel, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, tileX, tileZ, false);
												}
											}
											if (tileDrawn) {
												@Pc(549) GroundDecor local549 = tile.groundDecor;
												if (local549 != null && (local549.key & 0x80000000L) != 0L) {
													if (GlRenderer.enabled && local549.aBoolean49) {
														GlRenderer.method4159(local33 + 50.0F - 1.5F);
													}
													if (GlRenderer.enabled) {
														Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
													}
													local549.entity.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local549.xFine - Static149.eyeX, local549.anInt733 - Static162.eyeY, local549.zFine - Static217.eyeZ, local549.key, local24, null);
													if (GlRenderer.enabled && local549.aBoolean49) {
														GlRenderer.method4159(local33);
													}
												}
											}
											direction = 0;
											frontWallTypes = 0;
											@Pc(616) Wall wall = tile.wall;
											@Pc(619) WallDecor wallDecor = tile.wallDecor;
											if (wall != null || wallDecor != null) {
												if (Static167.eyeTileX == tileX) {
													direction++;
												} else if (Static167.eyeTileX < tileX) {
													direction += 2;
												}
												if (Static193.anInt4539 == tileZ) {
													direction += 3;
												} else if (Static193.anInt4539 > tileZ) {
													direction += 6;
												}
												frontWallTypes = Static138.FRONT_WALL_TYPES[direction];
												tile.backWallTypes = Static191.BACK_WALL_TYPES[direction];
											}
											if (wall != null) {
												if ((wall.typeA & Static90.DIRECTION_ALLOW_WALL_CORNER_TYPE[direction]) == 0) {
													tile.checkLocSpans = 0;
												} else if (wall.typeA == 16) {
													tile.checkLocSpans = 3;
													tile.blockLocSpans = Static128.WALL_CORNER_TYPE_16_BLOCK_LOC_SPANS[direction];
													tile.inverseBlockLocSpans = 3 - tile.blockLocSpans;
												} else if (wall.typeA == 32) {
													tile.checkLocSpans = 6;
													tile.blockLocSpans = Static254.WALL_CORNER_TYPE_32_BLOCK_LOC_SPANS[direction];
													tile.inverseBlockLocSpans = 6 - tile.blockLocSpans;
												} else if (wall.typeA == 64) {
													tile.checkLocSpans = 12;
													tile.blockLocSpans = Static86.WALL_CORNER_TYPE_64_BLOCK_LOC_SPANS[direction];
													tile.inverseBlockLocSpans = 12 - tile.blockLocSpans;
												} else {
													tile.checkLocSpans = 9;
													tile.blockLocSpans = Static131.WALL_CORNER_TYPE_128_BLOCK_LOC_SPANS[direction];
													tile.inverseBlockLocSpans = 9 - tile.blockLocSpans;
												}
												if ((wall.typeA & frontWallTypes) != 0 && !Static260.wallVisible(occludeLevel, tileX, tileZ, wall.typeA)) {
													if (GlRenderer.enabled) {
														Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
													}
													wall.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, wall.anInt3048 - Static149.eyeX, wall.anInt3051 - Static162.eyeY, wall.anInt3044 - Static217.eyeZ, wall.aLong107, local24, null);
												}
												if ((wall.typeB & frontWallTypes) != 0 && !Static260.wallVisible(occludeLevel, tileX, tileZ, wall.typeB)) {
													if (GlRenderer.enabled) {
														Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
													}
													wall.modelB.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, wall.anInt3048 - Static149.eyeX, wall.anInt3051 - Static162.eyeY, wall.anInt3044 - Static217.eyeZ, wall.aLong107, local24, null);
												}
											}
											if (wallDecor != null && !Static276.visible(occludeLevel, tileX, tileZ, wallDecor.primary.getMinY())) {
												if (GlRenderer.enabled) {
													GlRenderer.method4159(local33 - 0.5F);
												}
												if ((wallDecor.type & frontWallTypes) != 0) {
													if (GlRenderer.enabled) {
														Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
													}
													wallDecor.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, wallDecor.xFine + wallDecor.xOffset - Static149.eyeX, wallDecor.anInt1391 - Static162.eyeY, wallDecor.zFine + wallDecor.zOffset - Static217.eyeZ, wallDecor.key, local24, null);
												} else if (wallDecor.type == 256) {
													x = wallDecor.xFine - Static149.eyeX;
													y = wallDecor.anInt1391 - Static162.eyeY;
													z = wallDecor.zFine - Static217.eyeZ;
													rotation = wallDecor.anInt1388;
													if (rotation == 1 || rotation == 2) {
														nearestX = -x;
													} else {
														nearestX = x;
													}
													@Pc(928) int nearestZ;
													if (rotation == 2 || rotation == 3) {
														nearestZ = -z;
													} else {
														nearestZ = z;
													}
													if (nearestZ < nearestX) {
														if (GlRenderer.enabled) {
															Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
														}
														wallDecor.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, x + wallDecor.xOffset, y, z + wallDecor.zOffset, wallDecor.key, local24, null);
													} else if (wallDecor.secondary != null) {
														if (GlRenderer.enabled) {
															Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
														}
														wallDecor.secondary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, x, y, z, wallDecor.key, local24, null);
													}
												}
												if (GlRenderer.enabled) {
													GlRenderer.method4159(local33);
												}
											}
											if (tileDrawn) {
												@Pc(1001) GroundDecor groundDecor = tile.groundDecor;
												if (groundDecor != null && (groundDecor.key & 0x80000000L) == 0L) {
													if (GlRenderer.enabled && groundDecor.aBoolean49) {
														GlRenderer.method4159(local33 + 50.0F - 1.5F);
													}
													if (GlRenderer.enabled) {
														Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
													}
													groundDecor.entity.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, groundDecor.xFine - Static149.eyeX, groundDecor.anInt733 - Static162.eyeY, groundDecor.zFine - Static217.eyeZ, groundDecor.key, local24, null);
													if (GlRenderer.enabled && groundDecor.aBoolean49) {
														GlRenderer.method4159(local33);
													}
												}
												@Pc(1064) ObjStackEntity objs = tile.aClass79_1;
												if (objs != null && objs.offset == 0) {
													if (GlRenderer.enabled) {
														Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
													}
													if (objs.aClass8_7 != null) {
														objs.aClass8_7.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, objs.anInt3064 - Static149.eyeX, objs.anInt3057 - Static162.eyeY, objs.anInt3061 - Static217.eyeZ, objs.aLong108, local24, null);
													}
													if (objs.aClass8_8 != null) {
														objs.aClass8_8.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, objs.anInt3064 - Static149.eyeX, objs.anInt3057 - Static162.eyeY, objs.anInt3061 - Static217.eyeZ, objs.aLong108, local24, null);
													}
													if (objs.aClass8_9 != null) {
														objs.aClass8_9.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, objs.anInt3064 - Static149.eyeX, objs.anInt3057 - Static162.eyeY, objs.anInt3061 - Static217.eyeZ, objs.aLong108, local24, null);
													}
												}
											}
											x = tile.locSpans;
											if (x != 0) {
												if (tileX < Static167.eyeTileX && (x & 0x4) != 0) {
													adjacent = tiles[tileX + 1][tileZ];
													if (adjacent != null && adjacent.aBoolean46) {
														aClass6.drawTileQueue.addTail(adjacent);
													}
												}
												if (tileZ < Static193.anInt4539 && (x & 0x2) != 0) {
													adjacent = tiles[tileX][tileZ + 1];
													if (adjacent != null && adjacent.aBoolean46) {
														aClass6.drawTileQueue.addTail(adjacent);
													}
												}
												if (tileX > Static167.eyeTileX && (x & 0x1) != 0) {
													adjacent = tiles[tileX - 1][tileZ];
													if (adjacent != null && adjacent.aBoolean46) {
														aClass6.drawTileQueue.addTail(adjacent);
													}
												}
												if (tileZ > Static193.anInt4539 && (x & 0x8) != 0) {
													adjacent = tiles[tileX][tileZ - 1];
													if (adjacent != null && adjacent.aBoolean46) {
														aClass6.drawTileQueue.addTail(adjacent);
													}
												}
											}
											break;
										}
										if (tile.checkLocSpans != 0) {
											tileDrawn = true;
											for (direction = 0; direction < tile.entityCount; direction++) {
												if (tile.sceneries[direction].anInt1707 != Static13.anInt437 && (tile.anIntArray59[direction] & tile.checkLocSpans) == tile.blockLocSpans) {
													tileDrawn = false;
													break;
												}
											}
											if (tileDrawn) {
												var22 = tile.wall;
												if (!Static260.wallVisible(occludeLevel, tileX, tileZ, var22.typeA)) {
													if (GlRenderer.enabled) {
														label882: {
															if ((var22.aLong107 & 0xFC000L) == 16384L) {
																frontWallTypes = var22.anInt3048 - Static149.eyeX;
																farthestIndex = var22.anInt3044 - Static217.eyeZ;
																local1332 = (int) (var22.aLong107 >> 20 & 0x3L);
																if (local1332 == 0) {
																	frontWallTypes -= 64;
																	farthestIndex += 64;
																	if (farthestIndex < frontWallTypes && tileX > 0 && tileZ < Static99.anInt2550 - 1) {
																		Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX - 1, tileZ + 1);
																		break label882;
																	}
																} else if (local1332 == 1) {
																	frontWallTypes += 64;
																	farthestIndex += 64;
																	if (farthestIndex < -frontWallTypes && tileX < Static152.anInt3594 - 1 && tileZ < Static99.anInt2550 - 1) {
																		Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX + 1, tileZ + 1);
																		break label882;
																	}
																} else if (local1332 == 2) {
																	frontWallTypes += 64;
																	farthestIndex -= 64;
																	if (farthestIndex > frontWallTypes && tileX < Static152.anInt3594 - 1 && tileZ > 0) {
																		Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX + 1, tileZ - 1);
																		break label882;
																	}
																} else if (local1332 == 3) {
																	frontWallTypes -= 64;
																	farthestIndex -= 64;
																	if (farthestIndex > -frontWallTypes && tileX > 0 && tileZ > 0) {
																		Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX - 1, tileZ - 1);
																		break label882;
																	}
																}
															}
															Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
														}
													}
													var22.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, var22.anInt3048 - Static149.eyeX, var22.anInt3051 - Static162.eyeY, var22.anInt3044 - Static217.eyeZ, var22.aLong107, local24, null);
												}
												tile.checkLocSpans = 0;
											}
										}
										if (!tile.containsLocs) {
											break;
										}
										try {
											var9 = tile.entityCount;
											tile.containsLocs = false;
											direction = 0;
											iterate_locs: for (frontWallTypes = 0; frontWallTypes < var9; frontWallTypes++) {
												var25 = tile.sceneries[frontWallTypes];
												if (var25.anInt1707 != Static13.anInt437) {
													for (local1332 = var25.anInt1701; local1332 <= var25.anInt1713; local1332++) {
														for (x = var25.anInt1696; x <= var25.anInt1698; x++) {
															adjacent = tiles[local1332][x];
															if (adjacent.aBoolean45) {
																tile.containsLocs = true;
																continue iterate_locs;
															}
															if (adjacent.checkLocSpans != 0) {
																z = 0;
																if (local1332 > var25.anInt1701) {
																	z++;
																}
																if (local1332 < var25.anInt1713) {
																	z += 4;
																}
																if (x > var25.anInt1696) {
																	z += 8;
																}
																if (x < var25.anInt1698) {
																	z += 2;
																}
																if ((z & adjacent.checkLocSpans) == tile.inverseBlockLocSpans) {
																	tile.containsLocs = true;
																	continue iterate_locs;
																}
															}
														}
													}
													Static25.aClass31Array2[direction++] = var25;
													local1332 = Static167.eyeTileX - var25.anInt1701;
													x = var25.anInt1713 - Static167.eyeTileX;
													if (x > local1332) {
														local1332 = x;
													}
													y = Static193.anInt4539 - var25.anInt1696;
													z = var25.anInt1698 - Static193.anInt4539;
													if (z > y) {
														var25.anInt1705 = local1332 + z;
													} else {
														var25.anInt1705 = local1332 + y;
													}
												}
											}
											while (direction > 0) {
												frontWallTypes = -50;
												farthestIndex = -1;
												for (local1332 = 0; local1332 < direction; local1332++) {
													@Pc(1628) Scenery local1628 = Static25.aClass31Array2[local1332];
													if (local1628.anInt1707 != Static13.anInt437) {
														if (local1628.anInt1705 > frontWallTypes) {
															frontWallTypes = local1628.anInt1705;
															farthestIndex = local1332;
														} else if (local1628.anInt1705 == frontWallTypes) {
															y = local1628.anInt1699 - Static149.eyeX;
															z = local1628.anInt1703 - Static217.eyeZ;
															rotation = Static25.aClass31Array2[farthestIndex].anInt1699 - Static149.eyeX;
															nearestX = Static25.aClass31Array2[farthestIndex].anInt1703 - Static217.eyeZ;
															if (y * y + z * z > rotation * rotation + nearestX * nearestX) {
																farthestIndex = local1332;
															}
														}
													}
												}
												if (farthestIndex == -1) {
													break;
												}
												@Pc(1697) Scenery local1697 = Static25.aClass31Array2[farthestIndex];
												local1697.anInt1707 = Static13.anInt437;
												if (!Static73.method1599(occludeLevel, local1697.anInt1701, local1697.anInt1713, local1697.anInt1696, local1697.anInt1698, local1697.entity.getMinY())) {
													if (GlRenderer.enabled) {
														if ((local1697.hash & 0xFC000L) == 147456L) {
															Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
															x = local1697.anInt1699 - Static149.eyeX;
															y = local1697.anInt1703 - Static217.eyeZ;
															z = (int) (local1697.hash >> 20 & 0x3L);
															if (z == 1 || z == 3) {
																if (y > -x) {
																	Static120.method2397(local24, tileX, tileZ - 1, tileX - 1, tileZ);
																} else {
																	Static120.method2397(local24, tileX, tileZ + 1, tileX + 1, tileZ);
																}
															} else if (y > x) {
																Static120.method2397(local24, tileX, tileZ - 1, tileX + 1, tileZ);
															} else {
																Static120.method2397(local24, tileX, tileZ + 1, tileX - 1, tileZ);
															}
														} else {
															Static120.method2391(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, local1697.anInt1701, local1697.anInt1696, local1697.anInt1713, local1697.anInt1698);
														}
													}
													local1697.entity.render(local1697.anInt1714, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local1697.anInt1699 - Static149.eyeX, local1697.anInt1706 - Static162.eyeY, local1697.anInt1703 - Static217.eyeZ, local1697.hash, local24, null);
												}
												for (x = local1697.anInt1701; x <= local1697.anInt1713; x++) {
													for (y = local1697.anInt1696; y <= local1697.anInt1698; y++) {
														@Pc(1863) SceneTile local1863 = tiles[x][y];
														if (local1863.checkLocSpans != 0) {
															aClass6.drawTileQueue.addTail(local1863);
														} else if ((x != tileX || y != tileZ) && local1863.aBoolean46) {
															aClass6.drawTileQueue.addTail(local1863);
														}
													}
												}
											}
											if (!tile.containsLocs) {
												break;
											}
										} catch (@Pc(1895) Exception local1895) {
											tile.containsLocs = false;
											break;
										}
									}
								} while (!tile.aBoolean46);
							} while (tile.checkLocSpans != 0);
							if (tileX > Static167.eyeTileX || tileX <= Static31.anInt987) {
								break;
							}
							local153 = tiles[tileX - 1][tileZ];
						} while (local153 != null && local153.aBoolean46);
						if (tileX < Static167.eyeTileX || tileX >= Static2.anInt15 - 1) {
							break;
						}
						local153 = tiles[tileX + 1][tileZ];
					} while (local153 != null && local153.aBoolean46);
					if (tileZ > Static193.anInt4539 || tileZ <= Static80.anInt4698) {
						break;
					}
					local153 = tiles[tileX][tileZ - 1];
				} while (local153 != null && local153.aBoolean46);
				if (tileZ < Static193.anInt4539 || tileZ >= Static215.anInt4866 - 1) {
					break;
				}
				local153 = tiles[tileX][tileZ + 1];
			} while (local153 != null && local153.aBoolean46);
			tile.aBoolean46 = false;
			Static211.anInt1142--;
			@Pc(1999) ObjStackEntity local1999 = tile.aClass79_1;
			if (local1999 != null && local1999.offset != 0) {
				if (GlRenderer.enabled) {
					Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
				}
				if (local1999.aClass8_7 != null) {
					local1999.aClass8_7.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local1999.anInt3064 - Static149.eyeX, local1999.anInt3057 - Static162.eyeY - local1999.offset, local1999.anInt3061 - Static217.eyeZ, local1999.aLong108, local24, null);
				}
				if (local1999.aClass8_8 != null) {
					local1999.aClass8_8.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local1999.anInt3064 - Static149.eyeX, local1999.anInt3057 - Static162.eyeY - local1999.offset, local1999.anInt3061 - Static217.eyeZ, local1999.aLong108, local24, null);
				}
				if (local1999.aClass8_9 != null) {
					local1999.aClass8_9.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local1999.anInt3064 - Static149.eyeX, local1999.anInt3057 - Static162.eyeY - local1999.offset, local1999.anInt3061 - Static217.eyeZ, local1999.aLong108, local24, null);
				}
			}
			if (tile.backWallTypes != 0) {
				@Pc(2109) WallDecor local2109 = tile.wallDecor;
				if (local2109 != null && !Static276.visible(occludeLevel, tileX, tileZ, local2109.primary.getMinY())) {
					if ((local2109.type & tile.backWallTypes) != 0) {
						if (GlRenderer.enabled) {
							Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
						}
						local2109.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local2109.xFine + local2109.xOffset - Static149.eyeX, local2109.anInt1391 - Static162.eyeY, local2109.zFine + local2109.zOffset - Static217.eyeZ, local2109.key, local24, null);
					} else if (local2109.type == 256) {
						frontWallTypes = local2109.xFine - Static149.eyeX;
						farthestIndex = local2109.anInt1391 - Static162.eyeY;
						local1332 = local2109.zFine - Static217.eyeZ;
						x = local2109.anInt1388;
						if (x == 1 || x == 2) {
							y = -frontWallTypes;
						} else {
							y = frontWallTypes;
						}
						if (x == 2 || x == 3) {
							z = -local1332;
						} else {
							z = local1332;
						}
						if (z >= y) {
							if (GlRenderer.enabled) {
								Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
							}
							local2109.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, frontWallTypes + local2109.xOffset, farthestIndex, local1332 + local2109.zOffset, local2109.key, local24, null);
						} else if (local2109.secondary != null) {
							if (GlRenderer.enabled) {
								Static120.method2393(Static149.eyeX, Static162.eyeY, Static217.eyeZ, local24, tileX, tileZ);
							}
							local2109.secondary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, frontWallTypes, farthestIndex, local1332, local2109.key, local24, null);
						}
					}
				}
				@Pc(2275) Wall local2275 = tile.wall;
				if (local2275 != null) {
					if ((local2275.typeB & tile.backWallTypes) != 0 && !Static260.wallVisible(occludeLevel, tileX, tileZ, local2275.typeB)) {
						if (GlRenderer.enabled) {
							Static120.method2388(local2275.typeB, Static149.eyeX, Static162.eyeY, Static217.eyeZ, occludeLevel, tileX, tileZ);
						}
						local2275.modelB.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, com.jagex.runetek4.cache.def.ItemDefinition.anInt2222, local2275.anInt3048 - Static149.eyeX, local2275.anInt3051 - Static162.eyeY, local2275.anInt3044 - Static217.eyeZ, local2275.aLong107, local24, null);
					}
					if ((local2275.typeA & tile.backWallTypes) != 0 && !Static260.wallVisible(occludeLevel, tileX, tileZ, local2275.typeA)) {
						if (GlRenderer.enabled) {
							Static120.method2388(local2275.typeA, Static149.eyeX, Static162.eyeY, Static217.eyeZ, occludeLevel, tileX, tileZ);
						}
						local2275.primary.render(0, Static109.anInt2886, Static121.anInt3038, PreciseSleep.anInt5205, ItemDefinition.anInt2222, local2275.anInt3048 - Static149.eyeX, local2275.anInt3051 - Static162.eyeY, local2275.anInt3044 - Static217.eyeZ, local2275.aLong107, local24, null);
					}
				}
			}
			@Pc(2388) SceneTile local2388;
			if (local24 < Static126.anInt3114 - 1) {
				local2388 = Static130.levelTiles[local24 + 1][tileX][tileZ];
				if (local2388 != null && local2388.aBoolean46) {
					aClass6.drawTileQueue.addTail(local2388);
				}
			}
			if (tileX < Static167.eyeTileX) {
				local2388 = tiles[tileX + 1][tileZ];
				if (local2388 != null && local2388.aBoolean46) {
					aClass6.drawTileQueue.addTail(local2388);
				}
			}
			if (tileZ < Static193.anInt4539) {
				local2388 = tiles[tileX][tileZ + 1];
				if (local2388 != null && local2388.aBoolean46) {
					aClass6.drawTileQueue.addTail(local2388);
				}
			}
			if (tileX > Static167.eyeTileX) {
				local2388 = tiles[tileX - 1][tileZ];
				if (local2388 != null && local2388.aBoolean46) {
					aClass6.drawTileQueue.addTail(local2388);
				}
			}
			if (tileZ > Static193.anInt4539) {
				local2388 = tiles[tileX][tileZ - 1];
				if (local2388 != null && local2388.aBoolean46) {
					aClass6.drawTileQueue.addTail(local2388);
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!ub", name = "b", descriptor = "(IIIIIII)V")
	public static void method4246(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(6) int arg5) {
		@Pc(8) Component local8 = Static201.method1418(arg0, arg1);
		if (local8 != null && local8.onUse != null) {
			@Pc(19) HookRequest local19 = new HookRequest();
			local19.source = local8;
			local19.arguments = local8.onUse;
			ClientScriptRunner.run(local19);
		}
		Static15.anInt506 = arg1;
		Static121.anInt3039 = arg3;
		Static98.anInt2512 = arg0;
		Static274.anInt4999 = arg2;
		MiniMenu.aBoolean302 = true;
		Static246.anInt5393 = arg4;
		Static35.anInt1092 = arg5;
		InterfaceList.redraw(local8);
	}

	@OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(IB)I")
	public static int method4247(@OriginalArg(0) int arg0) {
		return arg0 & 0xFF;
	}

	@OriginalMember(owner = "runetek4.client!ub", name = "a", descriptor = "(Z)V")
	public static void clear() {
		Static279.aClass99_38.clear();
		SpotAnimDefinition.modelCache.clear();
	}
}
