package com.jagex.runetek4;

import com.jagex.runetek4.dash3d.entity.PathingEntity;
import com.jagex.runetek4.media.Rasterizer;
import com.jagex.runetek4.util.ArrayUtils;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static280 {

	@OriginalMember(owner = "runetek4.client!wl", name = "A", descriptor = "I")
	public static int anInt5900;

	@OriginalMember(owner = "runetek4.client!wl", name = "d", descriptor = "Lclient!na;")
	public static final JString COMPASS = JString.parse("compass");

	@OriginalMember(owner = "runetek4.client!wl", name = "u", descriptor = "I")
	public static int anInt5895 = 0;

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(Lclient!fe;B)V")
	public static void method4665(@OriginalArg(0) PathingEntity arg0) {
		if (client.loop == arg0.anInt3386 || arg0.primarySeqId == -1 || arg0.anInt3420 != 0 || arg0.anInt3360 + 1 > SeqTypeList.getAnimationSequence(arg0.primarySeqId).frames[arg0.anInt3425]) {
			@Pc(35) int local35 = arg0.anInt3386 - arg0.anInt3395;
			@Pc(41) int local41 = client.loop - arg0.anInt3395;
			@Pc(52) int local52 = arg0.anInt3380 * 128 + arg0.getSize() * 64;
			@Pc(64) int local64 = arg0.anInt3428 * 128 + arg0.getSize() * 64;
			@Pc(75) int local75 = arg0.anInt3416 * 128 + arg0.getSize() * 64;
			@Pc(86) int local86 = arg0.anInt3392 * 128 + arg0.getSize() * 64;
			arg0.xFine = (local41 * local75 + local52 * (local35 - local41)) / local35;
			arg0.zFine = (local86 * local41 + local64 * (local35 - local41)) / local35;
		}
		arg0.anInt3417 = 0;
		if (arg0.anInt3431 == 0) {
			arg0.dstYaw = 1024;
		}
		if (arg0.anInt3431 == 1) {
			arg0.dstYaw = 1536;
		}
		if (arg0.anInt3431 == 2) {
			arg0.dstYaw = 0;
		}
		if (arg0.anInt3431 == 3) {
			arg0.dstYaw = 512;
		}
		arg0.anInt3381 = arg0.dstYaw;
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "([IIIIIIIIIZB)V")
	public static void method4667(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) boolean arg9) {
		@Pc(7) int local7 = arg2;
		if (Rasterizer.viewportRight <= arg2) {
			return;
		}
		if (arg2 < Rasterizer.viewportLeft) {
			local7 = Rasterizer.viewportLeft;
		}
		@Pc(30) int local30 = arg7 + arg2;
		if (Rasterizer.viewportLeft >= local30) {
			return;
		}
		if (Rasterizer.viewportRight < local30) {
			local30 = Rasterizer.viewportRight;
		}
		@Pc(43) int local43 = arg8;
		if (Rasterizer.viewportBottom <= arg8) {
			return;
		}
		@Pc(56) int local56 = arg8 + arg6;
		if (arg8 < Rasterizer.viewportTop) {
			local43 = Rasterizer.viewportTop;
		}
		if (local56 <= Rasterizer.viewportTop) {
			return;
		}
		@Pc(79) int local79 = local7 + SoftwareRaster.destinationWidth * local43;
		if (arg5 == 9) {
			arg3 = arg3 + 1 & 0x3;
			arg5 = 1;
		}
		@Pc(99) int local99 = local7 + SoftwareRaster.destinationWidth - local30;
		local43 -= arg8;
		@Pc(108) int local108 = arg6 - local43;
		if (Rasterizer.viewportBottom < local56) {
			local56 = Rasterizer.viewportBottom;
		}
		if (arg5 == 10) {
			arg3 = arg3 + 3 & 0x3;
			arg5 = 1;
		}
		local7 -= arg2;
		@Pc(136) int local136 = arg7 - local7;
		if (arg5 == 11) {
			arg3 = arg3 + 3 & 0x3;
			arg5 = 8;
		}
		local30 -= arg2;
		@Pc(157) int local157 = arg7 - local30;
		local56 -= arg8;
		@Pc(165) int local165 = arg6 - local56;
		@Pc(175) int local175;
		@Pc(184) int local184;
		if (arg5 == 1) {
			if (arg3 == 0) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local184 <= local175) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 1) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local175 >= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 2) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local184 >= local175) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 3) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local184 >= local175) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			}
		} else if (arg5 == 2) {
			if (arg3 == 0) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local175 >> 1 >= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 1) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local79 >= 0 && local79 < arg0.length) {
							if (local175 << 1 <= local184) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						} else {
							local79++;
						}
					}
					local79 += local99;
				}
			} else if (arg3 == 2) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local136 - 1; local184 >= local157; local184--) {
						if (local175 >> 1 >= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 3) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local136 - 1; local184 >= local157; local184--) {
						if (local175 << 1 <= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			}
		} else if (arg5 == 3) {
			if (arg3 == 0) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local136 - 1; local184 >= local157; local184--) {
						if (local175 >> 1 >= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 1) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local184 >= local175 << 1) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 2) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local184 <= local175 >> 1) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 3) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local136 - 1; local184 >= local157; local184--) {
						if (local175 << 1 <= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			}
		} else if (arg5 == 4) {
			if (arg3 == 0) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local175 >> 1 <= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 1) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local7; local184 < local30; local184++) {
						if (local175 << 1 >= local184) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 2) {
				for (local175 = local43; local175 < local56; local175++) {
					for (local184 = local136 - 1; local184 >= local157; local184--) {
						if (local184 >= local175 >> 1) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			} else if (arg3 == 3) {
				for (local175 = local108 - 1; local175 >= local165; local175--) {
					for (local184 = local136 - 1; local184 >= local157; local184--) {
						if (local184 <= local175 << 1) {
							arg0[local79] = arg1;
						} else if (arg9) {
							arg0[local79] = arg4;
						}
						local79++;
					}
					local79 += local99;
				}
			}
		} else if (arg5 != 5) {
			if (arg5 == 6) {
				if (arg3 == 0) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local184 <= arg7 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 1) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local175 <= arg6 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 2) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local184 >= arg7 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 3) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local175 >= arg6 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
			}
			if (arg5 == 7) {
				if (arg3 == 0) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local184 <= local175 - arg6 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 1) {
					for (local175 = local108 - 1; local175 >= local165; local175--) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local175 - arg6 / 2 >= local184) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 2) {
					for (local175 = local108 - 1; local175 >= local165; local175--) {
						for (local184 = local136 - 1; local184 >= local157; local184--) {
							if (local184 <= local175 - arg6 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 3) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local136 - 1; local184 >= local157; local184--) {
							if (local175 - arg6 / 2 >= local184) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
			}
			if (arg5 == 8) {
				if (arg3 == 0) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local175 - arg6 / 2 <= local184) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 1) {
					for (local175 = local108 - 1; local175 >= local165; local175--) {
						for (local184 = local7; local184 < local30; local184++) {
							if (local175 - arg6 / 2 <= local184) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 2) {
					for (local175 = local108 - 1; local175 >= local165; local175--) {
						for (local184 = local136 - 1; local184 >= local157; local184--) {
							if (local184 >= local175 - arg6 / 2) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
				if (arg3 == 3) {
					for (local175 = local43; local175 < local56; local175++) {
						for (local184 = local136 - 1; local184 >= local157; local184--) {
							if (local175 - arg6 / 2 <= local184) {
								arg0[local79] = arg1;
							} else if (arg9) {
								arg0[local79] = arg4;
							}
							local79++;
						}
						local79 += local99;
					}
					return;
				}
			}
		} else if (arg3 == 0) {
			for (local175 = local108 - 1; local175 >= local165; local175--) {
				for (local184 = local136 - 1; local184 >= local157; local184--) {
					if (local175 >> 1 <= local184) {
						arg0[local79] = arg1;
					} else if (arg9) {
						arg0[local79] = arg4;
					}
					local79++;
				}
				local79 += local99;
			}
		} else if (arg3 == 1) {
			for (local175 = local108 - 1; local175 >= local165; local175--) {
				for (local184 = local7; local184 < local30; local184++) {
					if (local184 <= local175 << 1) {
						arg0[local79] = arg1;
					} else if (arg9) {
						arg0[local79] = arg4;
					}
					local79++;
				}
				local79 += local99;
			}
		} else if (arg3 == 2) {
			for (local175 = local43; local175 < local56; local175++) {
				for (local184 = local7; local184 < local30; local184++) {
					if (local184 >= local175 >> 1) {
						arg0[local79] = arg1;
					} else if (arg9) {
						arg0[local79] = arg4;
					}
					local79++;
				}
				local79 += local99;
			}
		} else if (arg3 == 3) {
			for (local175 = local43; local175 < local56; local175++) {
				for (local184 = local136 - 1; local184 >= local157; local184--) {
					if (local175 << 1 >= local184) {
						arg0[local79] = arg1;
					} else if (arg9) {
						arg0[local79] = arg4;
					}
					local79++;
				}
				local79 += local99;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!wl", name = "a", descriptor = "(IBIIIII)V")
	public static void method4670(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(18) int local18 = arg2 - arg4;
		Static251.method4278(arg2);
		@Pc(23) int local23 = 0;
		if (local18 < 0) {
			local18 = 0;
		}
		@Pc(32) int local32 = arg2;
		@Pc(35) int local35 = -arg2;
		@Pc(38) int local38 = -local18;
		@Pc(40) int local40 = local18;
		@Pc(42) int local42 = -1;
		@Pc(61) int local61;
		@Pc(69) int local69;
		@Pc(78) int local78;
		@Pc(87) int local87;
		if (arg1 >= Static267.anInt5773 && Static106.anInt2869 >= arg1) {
			@Pc(52) int[] local52 = ObjTypeList.anIntArrayArray10[arg1];
			local61 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 - arg2, Static172.anInt4164);
			local69 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg2 + arg5, Static172.anInt4164);
			local78 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 - local18, Static172.anInt4164);
			local87 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 + local18, Static172.anInt4164);
			ArrayUtils.fillRange(local52, local61, local78, arg3);
			ArrayUtils.fillRange(local52, local78, local87, arg0);
			ArrayUtils.fillRange(local52, local87, local69, arg3);
		}
		@Pc(107) int local107 = -1;
		while (local23 < local32) {
			local42 += 2;
			local107 += 2;
			local38 += local107;
			local35 += local42;
			if (local38 >= 0 && local40 >= 1) {
				local40--;
				Static241.anIntArray522[local40] = local23;
				local38 -= local40 << 1;
			}
			local23++;
			@Pc(264) int local264;
			@Pc(273) int local273;
			@Pc(280) int[] local280;
			@Pc(161) int local161;
			if (local35 >= 0) {
				local32--;
				local35 -= local32 << 1;
				local161 = arg1 - local32;
				local61 = arg1 + local32;
				if (Static267.anInt5773 <= local61 && Static106.anInt2869 >= local161) {
					if (local32 >= local18) {
						local69 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, local23 + arg5, Static172.anInt4164);
						local78 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 - local23, Static172.anInt4164);
						if (Static106.anInt2869 >= local61) {
							ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local61], local78, local69, arg3);
						}
						if (local161 >= Static267.anInt5773) {
							ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local161], local78, local69, arg3);
						}
					} else {
						local69 = Static241.anIntArray522[local32];
						local78 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, local23 + arg5, Static172.anInt4164);
						local87 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 - local23, Static172.anInt4164);
						local264 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 + local69, Static172.anInt4164);
						local273 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 - local69, Static172.anInt4164);
						if (Static106.anInt2869 >= local61) {
							local280 = ObjTypeList.anIntArrayArray10[local61];
							ArrayUtils.fillRange(local280, local87, local273, arg3);
							ArrayUtils.fillRange(local280, local273, local264, arg0);
							ArrayUtils.fillRange(local280, local264, local78, arg3);
						}
						if (local161 >= Static267.anInt5773) {
							local280 = ObjTypeList.anIntArrayArray10[local161];
							ArrayUtils.fillRange(local280, local87, local273, arg3);
							ArrayUtils.fillRange(local280, local273, local264, arg0);
							ArrayUtils.fillRange(local280, local264, local78, arg3);
						}
					}
				}
			}
			local161 = arg1 - local23;
			local61 = arg1 + local23;
			if (Static267.anInt5773 <= local61 && Static106.anInt2869 >= local161) {
				local69 = arg5 + local32;
				local78 = arg5 - local32;
				if (local69 >= Static172.anInt4164 && FloorUnderlayTypeList.anInt5063 >= local78) {
					local69 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, local69, Static172.anInt4164);
					local78 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, local78, Static172.anInt4164);
					if (local23 < local18) {
						local87 = local40 >= local23 ? local40 : Static241.anIntArray522[local23];
						local264 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, local87 + arg5, Static172.anInt4164);
						local273 = IntUtils.clamp(FloorUnderlayTypeList.anInt5063, arg5 - local87, Static172.anInt4164);
						if (Static106.anInt2869 >= local61) {
							local280 = ObjTypeList.anIntArrayArray10[local61];
							ArrayUtils.fillRange(local280, local78, local273, arg3);
							ArrayUtils.fillRange(local280, local273, local264, arg0);
							ArrayUtils.fillRange(local280, local264, local69, arg3);
						}
						if (local161 >= Static267.anInt5773) {
							local280 = ObjTypeList.anIntArrayArray10[local161];
							ArrayUtils.fillRange(local280, local78, local273, arg3);
							ArrayUtils.fillRange(local280, local273, local264, arg0);
							ArrayUtils.fillRange(local280, local264, local69, arg3);
						}
					} else {
						if (Static106.anInt2869 >= local61) {
							ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local61], local78, local69, arg3);
						}
						if (local161 >= Static267.anInt5773) {
							ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local161], local78, local69, arg3);
						}
					}
				}
			}
		}
	}

}
