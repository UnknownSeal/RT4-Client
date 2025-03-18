package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.dash3d.CollisionMap;
import com.jagex.runetek4.game.config.lighttype.LightType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static269 {

	@OriginalMember(owner = "runetek4.client!wa", name = "ub", descriptor = "Lclient!bn;")
	public static Map aClass3_Sub2_Sub4_2;

	@OriginalMember(owner = "runetek4.client!wa", name = "X", descriptor = "[Lclient!na;")
	public static JString[] aClass100Array87 = null;

	@OriginalMember(owner = "runetek4.client!wa", name = "e", descriptor = "(B)V")
	public static void method2172() {
		Static118.aClass99_16.clean();
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "([Lclient!mj;ZIIIII[B)V")
	public static void method2203(@OriginalArg(0) CollisionMap[] arg0, @OriginalArg(1) boolean arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) byte[] arg6) {
		@Pc(14) int local14;
		@Pc(21) int local21;
		if (!arg1) {
			for (@Pc(9) int local9 = 0; local9 < 4; local9++) {
				for (local14 = 0; local14 < 64; local14++) {
					for (local21 = 0; local21 < 64; local21++) {
						if (arg4 + local14 > 0 && local14 + arg4 < 103 && arg3 + local21 > 0 && local21 + arg3 < 103) {
							arg0[local9].flags[local14 + arg4][arg3 + local21] &= 0xFEFFFFFF;
						}
					}
				}
			}
		}
		@Pc(95) Packet local95 = new Packet(arg6);
		@Pc(99) byte local99;
		if (arg1) {
			local99 = 1;
		} else {
			local99 = 4;
		}
		@Pc(117) int local117;
		for (local14 = 0; local14 < local99; local14++) {
			for (local21 = 0; local21 < 64; local21++) {
				for (local117 = 0; local117 < 64; local117++) {
					Static278.method4651(arg2, arg5, arg1, local95, local117 + arg3, arg4 + local21, 0, local14);
				}
			}
		}
		@Pc(146) boolean local146 = false;
		@Pc(243) int local243;
		@Pc(188) int local188;
		@Pc(190) int local190;
		@Pc(194) int local194;
		while (local95.offset < local95.data.length) {
			local21 = local95.g1();
			if (local21 != 129) {
				local95.offset--;
				break;
			}
			for (local117 = 0; local117 < 4; local117++) {
				@Pc(168) byte local168 = local95.g1s();
				if (local168 == 0) {
					local243 = arg4;
					if (arg4 < 0) {
						local243 = 0;
					} else if (arg4 >= 104) {
						local243 = 104;
					}
					local190 = arg3;
					if (arg3 < 0) {
						local190 = 0;
					} else if (arg3 >= 104) {
						local190 = 104;
					}
					local188 = arg4 + 64;
					local194 = arg3 + 64;
					if (local194 < 0) {
						local194 = 0;
					} else if (local194 >= 104) {
						local194 = 104;
					}
					if (local188 < 0) {
						local188 = 0;
					} else if (local188 >= 104) {
						local188 = 104;
					}
					while (local243 < local188) {
						while (local190 < local194) {
							SceneGraph.aByteArrayArrayArray13[local117][local243][local190] = 0;
							local190++;
						}
						local243++;
					}
				} else if (local168 == 1) {
					for (local243 = 0; local243 < 64; local243 += 4) {
						for (local188 = 0; local188 < 64; local188 += 4) {
							@Pc(305) byte local305 = local95.g1s();
							for (local194 = local243 + arg4; local194 < arg4 + local243 + 4; local194++) {
								for (@Pc(320) int local320 = arg3 + local188; local320 < arg3 + local188 + 4; local320++) {
									if (local194 >= 0 && local194 < 104 && local320 >= 0 && local320 < 104) {
										SceneGraph.aByteArrayArrayArray13[local117][local194][local320] = local305;
									}
								}
							}
						}
					}
				} else if (local168 == 2 && local117 > 0) {
					local188 = arg4 + 64;
					local190 = arg3;
					local194 = arg3 + 64;
					if (local188 < 0) {
						local188 = 0;
					} else if (local188 >= 104) {
						local188 = 104;
					}
					if (arg3 < 0) {
						local190 = 0;
					} else if (arg3 >= 104) {
						local190 = 104;
					}
					if (local194 < 0) {
						local194 = 0;
					} else if (local194 >= 104) {
						local194 = 104;
					}
					local243 = arg4;
					if (arg4 < 0) {
						local243 = 0;
					} else if (arg4 >= 104) {
						local243 = 104;
					}
					while (local188 > local243) {
						while (local190 < local194) {
							SceneGraph.aByteArrayArrayArray13[local117][local243][local190] = SceneGraph.aByteArrayArrayArray13[local117 - 1][local243][local190];
							local190++;
						}
						local243++;
					}
				}
			}
			local146 = true;
		}
		@Pc(515) int local515;
		if (GlRenderer.enabled && !arg1) {
			@Pc(490) Environment local490 = null;
			label270: while (true) {
				label263: do {
					while (local95.offset < local95.data.length) {
						local117 = local95.g1();
						if (local117 != 0) {
							if (local117 != 1) {
								throw new IllegalStateException();
							}
							local515 = local95.g1();
							continue label263;
						}
						local490 = new Environment(local95);
					}
					if (local490 == null) {
						local490 = new Environment();
					}
					for (local117 = 0; local117 < 8; local117++) {
						for (local515 = 0; local515 < 8; local515++) {
							local243 = local117 + (arg4 >> 3);
							local188 = (arg3 >> 3) + local515;
							if (local243 >= 0 && local243 < 13 && local188 >= 0 && local188 < 13) {
								FogManager.chunksAtmosphere[local243][local188] = local490;
							}
						}
					}
					break label270;
				} while (local515 <= 0);
				for (local243 = 0; local243 < local515; local243++) {
					@Pc(529) Light local529 = new Light(local95);
					if (local529.anInt2243 == 31) {
						@Pc(541) LightType local541 = Static140.method2709(local95.g2());
						local529.method1762(local541.anInt2865, local541.anInt2873, local541.anInt2867, local541.anInt2872);
					}
					local529.anInt2245 += arg3 << 7;
					local529.anInt2240 += arg4 << 7;
					local194 = local529.anInt2245 >> 7;
					local190 = local529.anInt2240 >> 7;
					if (local190 >= 0 && local194 >= 0 && local190 < 104 && local194 < 104) {
						local529.aBoolean125 = (SceneGraph.renderFlags[1][local190][local194] & 0x2) != 0;
						local529.anInt2235 = SceneGraph.tileHeights[local529.anInt2241][local190][local194] - local529.anInt2235;
						Static120.method2389(local529);
					}
				}
			}
		}
		if (local146) {
			return;
		}
		for (local21 = 0; local21 < 4; local21++) {
			for (local117 = 0; local117 < 16; local117++) {
				for (local515 = 0; local515 < 16; local515++) {
					local243 = (arg4 >> 2) + local117;
					local188 = local515 + (arg3 >> 2);
					if (local243 >= 0 && local243 < 26 && local188 >= 0 && local188 < 26) {
						SceneGraph.aByteArrayArrayArray13[local21][local243][local188] = 0;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "o", descriptor = "(I)V")
	public static void method2218() {
		@Pc(8) int local8 = Static236.method4047();
		if (local8 == 0) {
			Static266.aByteArrayArrayArray15 = null;
			Static232.method3993(0);
		} else if (local8 == 1) {
			Static38.method960((byte) 0);
			Static232.method3993(512);
			Static132.method2608();
		} else {
			Static38.method960((byte) (Static136.anInt3325 - 4 & 0xFF));
			Static232.method3993(2);
		}
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "d", descriptor = "(BI)V")
	public static void method2221() {
		Static125.varbitDefinitionCache.clean(5);
	}

	@OriginalMember(owner = "runetek4.client!wa", name = "a", descriptor = "(IZ)Lclient!na;")
	public static JString method2228(@OriginalArg(0) int arg0) {
		return MiniMenu.opBases[arg0].length() > 0 ? JString.concatenate(new JString[] { MiniMenu.ops[arg0], LocalizedText.MINISEPARATOR, MiniMenu.opBases[arg0] }) : MiniMenu.ops[arg0];
	}
}
