package com.jagex.runetek4;

import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import com.jagex.runetek4.config.FloType;
import com.jagex.runetek4.game.config.iftype.componentproperties.ServerActiveProperties;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static45 {

	@OriginalMember(owner = "runetek4.client!di", name = "q", descriptor = "[Lclient!na;")
	public static final JagString[] aClass100Array53 = new JagString[5];

	@OriginalMember(owner = "runetek4.client!di", name = "F", descriptor = "Lclient!bf;")
	public static final ServerActiveProperties aClass3_Sub4_2 = new ServerActiveProperties(0, -1);

	@OriginalMember(owner = "runetek4.client!di", name = "H", descriptor = "Z")
	public static boolean aBoolean84 = false;

	@OriginalMember(owner = "runetek4.client!di", name = "I", descriptor = "Lclient!n;")
	public static final SoftLruHashTable aClass99_6 = new SoftLruHashTable(32);

	@OriginalMember(owner = "runetek4.client!di", name = "a", descriptor = "([Lclient!mj;ZI)V")
	public static void method1169(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) boolean arg1) {
		@Pc(10) int local10;
		@Pc(15) int local15;
		if (!arg1) {
			for (local10 = 0; local10 < 4; local10++) {
				for (local15 = 0; local15 < 104; local15++) {
					for (@Pc(22) int local22 = 0; local22 < 104; local22++) {
						if ((Static12.aByteArrayArrayArray2[local10][local15][local22] & 0x1) == 1) {
							@Pc(43) int local43 = local10;
							if ((Static12.aByteArrayArrayArray2[1][local15][local22] & 0x2) == 2) {
								local43 = local10 - 1;
							}
							if (local43 >= 0) {
								arg0[local43].method3051(local22, local15);
							}
						}
					}
				}
			}
			Static183.anInt4272 += (int) (Math.random() * 5.0D) - 2;
			if (Static183.anInt4272 < -16) {
				Static183.anInt4272 = -16;
			}
			if (Static183.anInt4272 > 16) {
				Static183.anInt4272 = 16;
			}
			Static86.anInt2293 += (int) (Math.random() * 5.0D) - 2;
			if (Static86.anInt2293 < -8) {
				Static86.anInt2293 = -8;
			}
			if (Static86.anInt2293 > 8) {
				Static86.anInt2293 = 8;
			}
		}
		@Pc(128) byte local128;
		if (arg1) {
			local128 = 1;
		} else {
			local128 = 4;
		}
		local10 = Static86.anInt2293 >> 2 << 10;
		@Pc(142) int[][] local142 = new int[104][104];
		@Pc(146) int[][] levelLightMap = new int[104][104];
		local15 = Static183.anInt4272 >> 1;
		@Pc(152) int level;
		@Pc(168) int z0;
		@Pc(173) int x0;
		@Pc(178) int z;
		@Pc(194) int underlayId;
		@Pc(200) int local200;
		@Pc(202) int dx;
		@Pc(209) int dz;
		@Pc(349) int len;
		@Pc(234) int normalX;
		@Pc(254) int normalY;
		@Pc(267) int normalZ;
		for (level = 0; level < local128; level++) {
			@Pc(159) byte[][] local159 = Static118.levelShademap[level];
			@Pc(273) int local273;
			@Pc(326) int local326;
			@Pc(332) int local332;
			@Pc(322) int local322;
			if (!GlRenderer.enabled) {
				z0 = (int) Math.sqrt(5100.0D);
				x0 = z0 * 768 >> 8;
				for (z = 1; z < 103; z++) {
					for (underlayId = 1; underlayId < 103; underlayId++) {
						dz = Static83.levelHeightMap[level][underlayId][z + 1] - Static83.levelHeightMap[level][underlayId][z - 1];
						dx = Static83.levelHeightMap[level][underlayId + 1][z] - Static83.levelHeightMap[level][underlayId - 1][z];
						len = (int) Math.sqrt((double) (dx * dx + dz * dz + 65536));
						normalZ = (dz << 8) / len;
						normalY = -65536 / len;
						normalX = (dx << 8) / len;
						local273 = (local159[underlayId][z] >> 1) + (local159[underlayId][z - 1] >> 2) + (local159[underlayId - -1][z] >> 3) + (local159[underlayId - 1][z] >> 2) + (local159[underlayId][z + 1] >> 3);
						local200 = (normalZ * -50 + normalX * -50 + normalY * -10) / x0 + 74;
						levelLightMap[underlayId][z] = local200 - local273;
					}
				}
			} else if (Static178.highDetailLighting) {
				for (z0 = 1; z0 < 103; z0++) {
					for (x0 = 1; x0 < 103; x0++) {
						underlayId = (local159[x0 + 1][z0] >> 3) + (local159[x0 - 1][z0] >> 2) + (local159[x0][z0 + -1] >> 2) + (local159[x0][z0 + 1] >> 3) + (local159[x0][z0] >> 1);
						levelLightMap[x0][z0] = 74 - underlayId;
					}
				}
			} else {
				z0 = (int) Static161.aFloatArray18[0];
				x0 = (int) Static161.aFloatArray18[1];
				z = (int) Static161.aFloatArray18[2];
				underlayId = (int) Math.sqrt((double) (x0 * x0 + z0 * z0 + z * z));
				local200 = underlayId * 1024 >> 8;
				for (dx = 1; dx < 103; dx++) {
					for (dz = 1; dz < 103; dz++) {
						normalX = Static83.levelHeightMap[level][dz + 1][dx] - Static83.levelHeightMap[level][dz - 1][dx];
						normalY = Static83.levelHeightMap[level][dz][dx + 1] - Static83.levelHeightMap[level][dz][dx - 1];
						normalZ = (int) Math.sqrt((double) (normalX * normalX + normalY * normalY + 65536));
						local273 = (normalX << 8) / normalZ;
						local322 = (local159[dz][dx + 1] >> 3) + (local159[dz][dx - 1] >> 2) + (local159[dz - 1][dx] >> 2) + (local159[dz + 1][dx] >> 3) + (local159[dz][dx] >> 1);
						local326 = -65536 / normalZ;
						local332 = (normalY << 8) / normalZ;
						len = (z * local332 + z0 * local273 + local326 * x0) / local200 + 96;
						levelLightMap[dz][dx] = len - (int) ((float) local322 * 1.7F);
					}
				}
			}
			for (z0 = 0; z0 < 104; z0++) {
				BZip2State.anIntArray376[z0] = 0;
				Static139.anIntArray325[z0] = 0;
				Static279.anIntArray568[z0] = 0;
				Static251.blendChroma[z0] = 0;
				Static128.blendMagnitude[z0] = 0;
			}
			for (z0 = -5; z0 < 104; z0++) {
				for (x0 = 0; x0 < 104; x0++) {
					z = z0 + 5;
					@Pc(729) int debugMag;
					if (z < 104) {
						underlayId = Static253.levelTileUnderlayIds[level][z][x0] & 0xFF;
						if (underlayId > 0) {
							@Pc(693) FloType flu = Static199.method3593(underlayId - 1);
							BZip2State.anIntArray376[x0] += flu.anInt4154;
							Static139.anIntArray325[x0] += flu.anInt4149;
							Static279.anIntArray568[x0] += flu.anInt4158;
							Static251.blendChroma[x0] += flu.chroma;
							debugMag = Static128.blendMagnitude[x0]++;
						}
					}
					underlayId = z0 - 5;
					if (underlayId >= 0) {
						local200 = Static253.levelTileUnderlayIds[level][underlayId][x0] & 0xFF;
						if (local200 > 0) {
							@Pc(758) FloType local758 = Static199.method3593(local200 - 1);
							BZip2State.anIntArray376[x0] -= local758.anInt4154;
							Static139.anIntArray325[x0] -= local758.anInt4149;
							Static279.anIntArray568[x0] -= local758.anInt4158;
							Static251.blendChroma[x0] -= local758.chroma;
							debugMag = Static128.blendMagnitude[x0]--;
						}
					}
				}
				if (z0 >= 0) {
					x0 = 0;
					underlayId = 0;
					z = 0;
					local200 = 0;
					dx = 0;
					for (dz = -5; dz < 104; dz++) {
						len = dz + 5;
						if (len < 104) {
							z += Static139.anIntArray325[len];
							dx += Static128.blendMagnitude[len];
							x0 += BZip2State.anIntArray376[len];
							local200 += Static251.blendChroma[len];
							underlayId += Static279.anIntArray568[len];
						}
						normalX = dz - 5;
						if (normalX >= 0) {
							z -= Static139.anIntArray325[normalX];
							local200 -= Static251.blendChroma[normalX];
							x0 -= BZip2State.anIntArray376[normalX];
							dx -= Static128.blendMagnitude[normalX];
							underlayId -= Static279.anIntArray568[normalX];
						}
						if (dz >= 0 && dx > 0) {
							local142[z0][dz] = Static54.method1309(underlayId / dx, z / dx, x0 * 256 / local200);
						}
					}
				}
			}
			for (z0 = 1; z0 < 103; z0++) {
				label771: for (x0 = 1; x0 < 103; x0++) {
					if (arg1 || Static138.allLevelsvisible() || (Static12.aByteArrayArrayArray2[0][z0][x0] & 0x2) != 0 || (Static12.aByteArrayArrayArray2[level][z0][x0] & 0x10) == 0 && Static4.getRenderLevel(x0, z0, level) == Static41.anInt1316) {
						if (Static146.firstvisibleLevel > level) {
							Static146.firstvisibleLevel = level;
						}
						z = Static253.levelTileUnderlayIds[level][z0][x0] & 0xFF;
						underlayId = Static240.aByteArrayArrayArray14[level][z0][x0] & 0xFF;
						if (z > 0 || underlayId > 0) {
							dx = Static83.levelHeightMap[level][z0 + 1][x0];
							local200 = Static83.levelHeightMap[level][z0][x0];
							len = Static83.levelHeightMap[level][z0][x0 + 1];
							dz = Static83.levelHeightMap[level][z0 + 1][x0 + 1];
							if (level > 0) {
								@Pc(1067) boolean local1067 = true;
								if (z == 0 && Static163.aByteArrayArrayArray11[level][z0][x0] != 0) {
									local1067 = false;
								}
								if (underlayId > 0 && !Static256.method4395(underlayId - 1).occlude) {
									local1067 = false;
								}
								if (local1067 && local200 == dx && local200 == dz && len == local200) {
									Static60.anIntArrayArrayArray6[level][z0][x0] |= 0x4;
								}
							}
							if (z <= 0) {
								normalX = -1;
								normalY = 0;
							} else {
								normalX = local142[z0][x0];
								normalZ = (normalX & 0x7F) + local15;
								if (normalZ < 0) {
									normalZ = 0;
								} else if (normalZ > 127) {
									normalZ = 127;
								}
								local273 = (normalX & 0x380) + (normalX + local10 & 0xFC00) + normalZ;
								normalY = Pix3D.anIntArray220[Static87.method1814(96, local273)];
							}
							normalZ = levelLightMap[z0][x0];
							local332 = levelLightMap[z0][x0 + 1];
							local273 = levelLightMap[z0 + 1][x0];
							local326 = levelLightMap[z0 + 1][x0 + 1];
							if (underlayId == 0) {
								Static176.method3305(level, z0, x0, 0, 0, -1, local200, dx, dz, len, Static87.method1814(normalZ, normalX), Static87.method1814(local273, normalX), Static87.method1814(local326, normalX), Static87.method1814(local332, normalX), 0, 0, 0, 0, normalY, 0);
								if (GlRenderer.enabled && level > 0 && normalX != -1 && Static199.method3593(z - 1).occlude) {
									Static242.method4197(0, 0, true, false, z0, x0, local200 - Static83.levelHeightMap[0][z0][x0], -Static83.levelHeightMap[0][z0 + 1][x0] + dx, dz - Static83.levelHeightMap[0][z0 + 1][x0 + 1], len - Static83.levelHeightMap[0][z0][x0 + 1]);
								}
								if (GlRenderer.enabled && !arg1 && Static62.anIntArrayArray11 != null && level == 0) {
									for (local322 = z0 - 1; local322 <= z0 + 1; local322++) {
										for (@Pc(1794) int local1794 = x0 - 1; local1794 <= x0 + 1; local1794++) {
											if ((local322 != z0 || x0 != local1794) && local322 >= 0 && local322 < 104 && local1794 >= 0 && local1794 < 104) {
												@Pc(1834) int local1834 = Static240.aByteArrayArrayArray14[level][local322][local1794] & 0xFF;
												if (local1834 != 0) {
													@Pc(1842) FloorOverlayType local1842 = Static256.method4395(local1834 - 1);
													if (local1842.material != -1 && Pix3D.anInterface1_2.method3237(local1842.material) == 4) {
														Static62.anIntArrayArray11[z0][x0] = local1842.waterfogcolour + (local1842.waterfogscale << 24);
														continue label771;
													}
												}
											}
										}
									}
								}
							} else {
								local322 = Static163.aByteArrayArrayArray11[level][z0][x0] + 1;
								@Pc(1242) byte local1242 = Static4.aByteArrayArrayArray1[level][z0][x0];
								@Pc(1248) FloorOverlayType local1248 = Static256.method4395(underlayId - 1);
								@Pc(1301) int local1301;
								@Pc(1353) int local1353;
								@Pc(1288) int local1288;
								if (GlRenderer.enabled && !arg1 && Static62.anIntArrayArray11 != null && level == 0) {
									if (local1248.material != -1 && Pix3D.anInterface1_2.method3237(local1248.material) == 4) {
										Static62.anIntArrayArray11[z0][x0] = (local1248.waterfogscale << 24) + local1248.waterfogcolour;
									} else {
										label737: for (local1288 = z0 - 1; local1288 <= z0 + 1; local1288++) {
											for (local1301 = x0 - 1; local1301 <= x0 + 1; local1301++) {
												if ((z0 != local1288 || local1301 != x0) && local1288 >= 0 && local1288 < 104 && local1301 >= 0 && local1301 < 104) {
													local1353 = Static240.aByteArrayArrayArray14[level][local1288][local1301] & 0xFF;
													if (local1353 != 0) {
														@Pc(1366) FloorOverlayType local1366 = Static256.method4395(local1353 - 1);
														if (local1366.material != -1 && Pix3D.anInterface1_2.method3237(local1366.material) == 4) {
															Static62.anIntArrayArray11[z0][x0] = local1366.waterfogcolour + (local1366.waterfogscale << 24);
															break label737;
														}
													}
												}
											}
										}
									}
								}
								local1288 = local1248.material;
								if (local1288 >= 0 && !Pix3D.anInterface1_2.method3236(local1288)) {
									local1288 = -1;
								}
								@Pc(1458) int local1458;
								@Pc(1429) int local1429;
								if (local1288 >= 0) {
									local1301 = -1;
									local1353 = Pix3D.anIntArray220[Static230.method3949(Pix3D.anInterface1_2.method3234(local1288), 96)];
								} else if (local1248.rgb == -1) {
									local1301 = -2;
									local1353 = 0;
								} else {
									local1301 = local1248.rgb;
									local1429 = local15 + (local1301 & 0x7F);
									if (local1429 < 0) {
										local1429 = 0;
									} else if (local1429 > 127) {
										local1429 = 127;
									}
									local1458 = (local1301 & 0x380) + ((local1301 + local10 & 0xFC00) + local1429);
									local1353 = Pix3D.anIntArray220[Static230.method3949(local1458, 96)];
								}
								if (local1248.averagecolour >= 0) {
									local1429 = local1248.averagecolour;
									local1458 = local15 + (local1429 & 0x7F);
									if (local1458 < 0) {
										local1458 = 0;
									} else if (local1458 > 127) {
										local1458 = 127;
									}
									@Pc(1529) int local1529 = (local1429 & 0x380) + ((local1429 + local10 & 0xFC00) + local1458);
									local1353 = Pix3D.anIntArray220[Static230.method3949(local1529, 96)];
								}
								Static176.method3305(level, z0, x0, local322, local1242, local1288, local200, dx, dz, len, Static87.method1814(normalZ, normalX), Static87.method1814(local273, normalX), Static87.method1814(local326, normalX), Static87.method1814(local332, normalX), Static230.method3949(local1301, normalZ), Static230.method3949(local1301, local273), Static230.method3949(local1301, local326), Static230.method3949(local1301, local332), normalY, local1353);
								if (GlRenderer.enabled && level > 0) {
									Static242.method4197(local322, local1242, local1301 == -2 || !local1248.hardshadow, normalX == -1 || !Static199.method3593(z - 1).occlude, z0, x0, local200 - Static83.levelHeightMap[0][z0][x0], dx - Static83.levelHeightMap[0][z0 + 1][x0], dz - Static83.levelHeightMap[0][z0 + 1][x0 + 1], -Static83.levelHeightMap[0][z0][x0 + 1] + len);
								}
							}
						}
					}
				}
			}
			if (GlRenderer.enabled) {
				@Pc(1888) float[][] local1888 = new float[105][105];
				@Pc(1892) int[][] local1892 = Static83.levelHeightMap[level];
				@Pc(1896) float[][] local1896 = new float[105][105];
				@Pc(1900) float[][] local1900 = new float[105][105];
				local200 = 1;
				while (true) {
					if (local200 > 103) {
						@Pc(2025) Class3_Sub14[] local2025;
						if (arg1) {
							local2025 = Static193.method3501(Static12.aByteArrayArrayArray2, Static163.aByteArrayArrayArray11[level], Static253.levelTileUnderlayIds[level], levelLightMap, local1896, Static62.anIntArrayArray11, Static240.aByteArrayArrayArray14[level], Static4.aByteArrayArrayArray1[level], local1888, level, local1900, local142, Static83.levelHeightMap[level], Static107.anIntArrayArrayArray10[0]);
							Static110.method2280(level, local2025);
							break;
						}
						local2025 = Static193.method3501(Static12.aByteArrayArrayArray2, Static163.aByteArrayArrayArray11[level], Static253.levelTileUnderlayIds[level], levelLightMap, local1896, null, Static240.aByteArrayArrayArray14[level], Static4.aByteArrayArrayArray1[level], local1888, level, local1900, local142, Static83.levelHeightMap[level], null);
						@Pc(2049) Class3_Sub14[] local2049 = Static1.method2(local1896, local1888, Static83.levelHeightMap[level], level, local1900, Static4.aByteArrayArrayArray1[level], levelLightMap, Static163.aByteArrayArrayArray11[level], Static253.levelTileUnderlayIds[level], Static240.aByteArrayArrayArray14[level], Static12.aByteArrayArrayArray2);
						@Pc(2057) Class3_Sub14[] local2057 = new Class3_Sub14[local2025.length + local2049.length];
						for (len = 0; len < local2025.length; len++) {
							local2057[len] = local2025[len];
						}
						for (len = 0; len < local2049.length; len++) {
							local2057[local2025.length + len] = local2049[len];
						}
						Static110.method2280(level, local2057);
						BZip2State.method3393(local1900, Static253.levelTileUnderlayIds[level], Static4.aByteArrayArrayArray1[level], Static120.aClass51Array1, level, Static120.anInt3034, local1896, Static163.aByteArrayArrayArray11[level], Static240.aByteArrayArrayArray14[level], Static83.levelHeightMap[level], local1888);
						break;
					}
					for (dx = 1; dx <= 103; dx++) {
						len = local1892[dx][local200 + 1] - local1892[dx][local200 - 1];
						dz = local1892[dx + 1][local200] - local1892[dx - 1][local200];
						@Pc(1962) float local1962 = (float) Math.sqrt((double) (dz * dz + len * len + 65536));
						local1888[dx][local200] = (float) dz / local1962;
						local1896[dx][local200] = -256.0F / local1962;
						local1900[dx][local200] = (float) len / local1962;
					}
					local200++;
				}
			}
			Static253.levelTileUnderlayIds[level] = null;
			Static240.aByteArrayArrayArray14[level] = null;
			Static163.aByteArrayArrayArray11[level] = null;
			Static4.aByteArrayArrayArray1[level] = null;
			Static118.levelShademap[level] = null;
		}
		Static220.method3801();
		if (arg1) {
			return;
		}
		@Pc(2204) int local2204;
		for (level = 0; level < 104; level++) {
			for (local2204 = 0; local2204 < 104; local2204++) {
				if ((Static12.aByteArrayArrayArray2[1][level][local2204] & 0x2) == 2) {
					Static224.method3884(level, local2204);
				}
			}
		}
		for (level = 0; level < 4; level++) {
			for (local2204 = 0; local2204 <= 104; local2204++) {
				for (z0 = 0; z0 <= 104; z0++) {
					if ((Static60.anIntArrayArrayArray6[level][z0][local2204] & 0x1) != 0) {
						local200 = level;
						for (x0 = local2204; x0 > 0 && (Static60.anIntArrayArrayArray6[level][z0][x0 - 1] & 0x1) != 0; x0--) {
						}
						underlayId = level;
						for (z = local2204; z < 104 && (Static60.anIntArrayArrayArray6[level][z0][z + 1] & 0x1) != 0; z++) {
						}
						label454: while (underlayId > 0) {
							for (dx = x0; dx <= z; dx++) {
								if ((Static60.anIntArrayArrayArray6[underlayId - 1][z0][dx] & 0x1) == 0) {
									break label454;
								}
							}
							underlayId--;
						}
						label443: while (local200 < 3) {
							for (dx = x0; dx <= z; dx++) {
								if ((Static60.anIntArrayArrayArray6[local200 + 1][z0][dx] & 0x1) == 0) {
									break label443;
								}
							}
							local200++;
						}
						dx = (local200 + 1 - underlayId) * (-x0 + (z - -1));
						if (dx >= 8) {
							len = Static83.levelHeightMap[local200][z0][x0] - 240;
							normalX = Static83.levelHeightMap[underlayId][z0][x0];
							Static278.method4647(1, z0 * 128, z0 * 128, x0 * 128, z * 128 + 128, len, normalX);
							for (normalY = underlayId; normalY <= local200; normalY++) {
								for (normalZ = x0; normalZ <= z; normalZ++) {
									Static60.anIntArrayArrayArray6[normalY][z0][normalZ] &= 0xFFFFFFFE;
								}
							}
						}
					}
					if ((Static60.anIntArrayArrayArray6[level][z0][local2204] & 0x2) != 0) {
						for (x0 = z0; x0 > 0 && (Static60.anIntArrayArrayArray6[level][x0 - 1][local2204] & 0x2) != 0; x0--) {
						}
						local200 = level;
						underlayId = level;
						for (z = z0; z < 104 && (Static60.anIntArrayArrayArray6[level][z + 1][local2204] & 0x2) != 0; z++) {
						}
						label508: while (underlayId > 0) {
							for (dx = x0; dx <= z; dx++) {
								if ((Static60.anIntArrayArrayArray6[underlayId - 1][dx][local2204] & 0x2) == 0) {
									break label508;
								}
							}
							underlayId--;
						}
						label497: while (local200 < 3) {
							for (dx = x0; dx <= z; dx++) {
								if ((Static60.anIntArrayArrayArray6[local200 + 1][dx][local2204] & 0x2) == 0) {
									break label497;
								}
							}
							local200++;
						}
						dx = (z + 1 - x0) * (-underlayId + local200 - -1);
						if (dx >= 8) {
							len = Static83.levelHeightMap[local200][x0][local2204] - 240;
							normalX = Static83.levelHeightMap[underlayId][x0][local2204];
							Static278.method4647(2, x0 * 128, z * 128 + 128, local2204 * 128, local2204 * 128, len, normalX);
							for (normalY = underlayId; normalY <= local200; normalY++) {
								for (normalZ = x0; normalZ <= z; normalZ++) {
									Static60.anIntArrayArrayArray6[normalY][normalZ][local2204] &= 0xFFFFFFFD;
								}
							}
						}
					}
					if ((Static60.anIntArrayArrayArray6[level][z0][local2204] & 0x4) != 0) {
						x0 = z0;
						z = z0;
						for (underlayId = local2204; underlayId > 0 && (Static60.anIntArrayArrayArray6[level][z0][underlayId - 1] & 0x4) != 0; underlayId--) {
						}
						for (local200 = local2204; local200 < 104 && (Static60.anIntArrayArrayArray6[level][z0][local200 + 1] & 0x4) != 0; local200++) {
						}
						label562: while (x0 > 0) {
							for (dx = underlayId; dx <= local200; dx++) {
								if ((Static60.anIntArrayArrayArray6[level][x0 - 1][dx] & 0x4) == 0) {
									break label562;
								}
							}
							x0--;
						}
						label551: while (z < 104) {
							for (dx = underlayId; dx <= local200; dx++) {
								if ((Static60.anIntArrayArrayArray6[level][z + 1][dx] & 0x4) == 0) {
									break label551;
								}
							}
							z++;
						}
						if ((z + 1 - x0) * (local200 - (underlayId - 1)) >= 4) {
							dx = Static83.levelHeightMap[level][x0][underlayId];
							Static278.method4647(4, x0 * 128, z * 128 + 128, underlayId * 128, local200 * 128 + 128, dx, dx);
							for (dz = x0; dz <= z; dz++) {
								for (len = underlayId; len <= local200; len++) {
									Static60.anIntArrayArrayArray6[level][dz][len] &= 0xFFFFFFFB;
								}
							}
						}
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!di", name = "a", descriptor = "(IIIIIIIIZIII)Z")
	public static boolean method1171(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) boolean arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9, @OriginalArg(11) int arg10) {
		@Pc(3) int local3;
		@Pc(10) int local10;
		for (local3 = 0; local3 < 104; local3++) {
			for (local10 = 0; local10 < 104; local10++) {
				Static145.anIntArrayArray25[local3][local10] = 0;
				Static167.anIntArrayArray31[local3][local10] = 99999999;
			}
		}
		local3 = arg2;
		Static145.anIntArrayArray25[arg2][arg9] = 99;
		local10 = arg9;
		Static167.anIntArrayArray31[arg2][arg9] = 0;
		@Pc(51) byte local51 = 0;
		@Pc(53) boolean local53 = false;
		@Pc(64) int local64 = 0;
		Static259.anIntArray514[0] = arg2;
		@Pc(71) int local71 = local51 + 1;
		Static84.anIntArray209[0] = arg9;
		@Pc(78) int[][] local78 = Static148.levelCollisionMap[Static55.currentLevel].flags;
		@Pc(198) int local198;
		while (local71 != local64) {
			local10 = Static84.anIntArray209[local64];
			local3 = Static259.anIntArray514[local64];
			local64 = local64 + 1 & 0xFFF;
			if (local3 == arg0 && local10 == arg3) {
				local53 = true;
				break;
			}
			if (arg8 != 0) {
				if ((arg8 < 5 || arg8 == 10) && Static148.levelCollisionMap[Static55.currentLevel].method3042(arg3, local3, local10, arg0, arg8 - 1, 1, arg6)) {
					local53 = true;
					break;
				}
				if (arg8 < 10 && Static148.levelCollisionMap[Static55.currentLevel].method3046(arg3, arg8 - 1, arg0, local10, 1, arg6, local3)) {
					local53 = true;
					break;
				}
			}
			if (arg10 != 0 && arg5 != 0 && Static148.levelCollisionMap[Static55.currentLevel].method3052(arg0, local10, local3, 1, arg10, arg1, arg3, arg5)) {
				local53 = true;
				break;
			}
			local198 = Static167.anIntArrayArray31[local3][local10] + 1;
			if (local3 > 0 && Static145.anIntArrayArray25[local3 - 1][local10] == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0) {
				Static259.anIntArray514[local71] = local3 - 1;
				Static84.anIntArray209[local71] = local10;
				local71 = local71 + 1 & 0xFFF;
				Static145.anIntArrayArray25[local3 - 1][local10] = 2;
				Static167.anIntArrayArray31[local3 - 1][local10] = local198;
			}
			if (local3 < 103 && Static145.anIntArrayArray25[local3 + 1][local10] == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0) {
				Static259.anIntArray514[local71] = local3 + 1;
				Static84.anIntArray209[local71] = local10;
				local71 = local71 + 1 & 0xFFF;
				Static145.anIntArrayArray25[local3 + 1][local10] = 8;
				Static167.anIntArrayArray31[local3 + 1][local10] = local198;
			}
			if (local10 > 0 && Static145.anIntArrayArray25[local3][local10 - 1] == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
				Static259.anIntArray514[local71] = local3;
				Static84.anIntArray209[local71] = local10 - 1;
				Static145.anIntArrayArray25[local3][local10 - 1] = 1;
				local71 = local71 + 1 & 0xFFF;
				Static167.anIntArrayArray31[local3][local10 - 1] = local198;
			}
			if (local10 < 103 && Static145.anIntArrayArray25[local3][local10 + 1] == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
				Static259.anIntArray514[local71] = local3;
				Static84.anIntArray209[local71] = local10 + 1;
				local71 = local71 + 1 & 0xFFF;
				Static145.anIntArrayArray25[local3][local10 + 1] = 4;
				Static167.anIntArrayArray31[local3][local10 + 1] = local198;
			}
			if (local3 > 0 && local10 > 0 && Static145.anIntArrayArray25[local3 - 1][local10 - 1] == 0 && (local78[local3 - 1][local10 - 1] & 0x12C010E) == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
				Static259.anIntArray514[local71] = local3 - 1;
				Static84.anIntArray209[local71] = local10 - 1;
				local71 = local71 + 1 & 0xFFF;
				Static145.anIntArrayArray25[local3 - 1][local10 - 1] = 3;
				Static167.anIntArrayArray31[local3 - 1][local10 - 1] = local198;
			}
			if (local3 < 103 && local10 > 0 && Static145.anIntArrayArray25[local3 + 1][local10 - 1] == 0 && (local78[local3 + 1][local10 - 1] & 0x12C0183) == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0 && (local78[local3][local10 - 1] & 0x12C0102) == 0) {
				Static259.anIntArray514[local71] = local3 + 1;
				Static84.anIntArray209[local71] = local10 - 1;
				local71 = local71 + 1 & 0xFFF;
				Static145.anIntArrayArray25[local3 + 1][local10 - 1] = 9;
				Static167.anIntArrayArray31[local3 + 1][local10 - 1] = local198;
			}
			if (local3 > 0 && local10 < 103 && Static145.anIntArrayArray25[local3 - 1][local10 + 1] == 0 && (local78[local3 - 1][local10 + 1] & 0x12C0138) == 0 && (local78[local3 - 1][local10] & 0x12C0108) == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
				Static259.anIntArray514[local71] = local3 - 1;
				Static84.anIntArray209[local71] = local10 + 1;
				Static145.anIntArrayArray25[local3 - 1][local10 + 1] = 6;
				local71 = local71 + 1 & 0xFFF;
				Static167.anIntArrayArray31[local3 - 1][local10 + 1] = local198;
			}
			if (local3 < 103 && local10 < 103 && Static145.anIntArrayArray25[local3 + 1][local10 + 1] == 0 && (local78[local3 + 1][local10 + 1] & 0x12C01E0) == 0 && (local78[local3 + 1][local10] & 0x12C0180) == 0 && (local78[local3][local10 + 1] & 0x12C0120) == 0) {
				Static259.anIntArray514[local71] = local3 + 1;
				Static84.anIntArray209[local71] = local10 + 1;
				Static145.anIntArrayArray25[local3 + 1][local10 + 1] = 12;
				local71 = local71 + 1 & 0xFFF;
				Static167.anIntArrayArray31[local3 + 1][local10 + 1] = local198;
			}
		}
		BZip2State.tryMoveNearest = 0;
		@Pc(839) int local839;
		if (!local53) {
			if (!arg7) {
				return false;
			}
			local198 = 1000;
			local839 = 100;
			for (@Pc(846) int local846 = arg0 - 10; local846 <= arg0 + 10; local846++) {
				for (@Pc(856) int local856 = arg3 - 10; local856 <= arg3 + 10; local856++) {
					if (local846 >= 0 && local856 >= 0 && local846 < 104 && local856 < 104 && Static167.anIntArrayArray31[local846][local856] < 100) {
						@Pc(894) int local894 = 0;
						if (local856 < arg3) {
							local894 = arg3 - local856;
						} else if (arg5 + arg3 - 1 < local856) {
							local894 = local856 + 1 - arg3 - arg5;
						}
						@Pc(927) int local927 = 0;
						if (local846 < arg0) {
							local927 = arg0 - local846;
						} else if (local846 > arg10 + arg0 - 1) {
							local927 = local846 + 1 - arg10 - arg0;
						}
						@Pc(968) int local968 = local894 * local894 + local927 * local927;
						if (local968 < local198 || local968 == local198 && Static167.anIntArrayArray31[local846][local856] < local839) {
							local10 = local856;
							local198 = local968;
							local3 = local846;
							local839 = Static167.anIntArrayArray31[local846][local856];
						}
					}
				}
			}
			if (local198 == 1000) {
				return false;
			}
			if (arg2 == local3 && local10 == arg9) {
				return false;
			}
			BZip2State.tryMoveNearest = 1;
		}
		@Pc(1032) byte local1032 = 0;
		Static259.anIntArray514[0] = local3;
		local64 = local1032 + 1;
		Static84.anIntArray209[0] = local10;
		local198 = local839 = Static145.anIntArrayArray25[local3][local10];
		while (arg2 != local3 || local10 != arg9) {
			if (local839 != local198) {
				local839 = local198;
				Static259.anIntArray514[local64] = local3;
				Static84.anIntArray209[local64++] = local10;
			}
			if ((local198 & 0x2) != 0) {
				local3++;
			} else if ((local198 & 0x8) != 0) {
				local3--;
			}
			if ((local198 & 0x1) != 0) {
				local10++;
			} else if ((local198 & 0x4) != 0) {
				local10--;
			}
			local198 = Static145.anIntArrayArray25[local3][local10];
		}
		if (local64 > 0) {
			Static193.method3502(local64, arg4);
			return true;
		} else if (arg4 == 1) {
			return false;
		} else {
			return true;
		}
	}

	@OriginalMember(owner = "runetek4.client!di", name = "d", descriptor = "(I)V")
	public static void method1172() {
		Static262.aClass99_34.method3103();
	}
}
