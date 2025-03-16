package com.jagex.runetek4.game.world.entity;

import com.jagex.runetek4.*;
import com.jagex.runetek4.cache.def.NpcType;
import com.jagex.runetek4.cache.def.ItemDefinition;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.bastype.BasType;
import com.jagex.runetek4.game.config.bastype.BasTypeList;
import com.jagex.runetek4.game.config.idktype.IDKType;
import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.graphics.ModelUnlit;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hh")
public final class PlayerModel {

	@OriginalMember(owner = "client!hh", name = "e", descriptor = "I")
	public int transformationNpcId;

	@OriginalMember(owner = "client!hh", name = "i", descriptor = "J")
	private long aLong87;

	@OriginalMember(owner = "client!hh", name = "l", descriptor = "[I")
	private int[] appearance;

	@OriginalMember(owner = "client!hh", name = "m", descriptor = "I")
	private int anInt2497;

	@OriginalMember(owner = "client!hh", name = "q", descriptor = "J")
	private long aLong88;

	@OriginalMember(owner = "client!hh", name = "t", descriptor = "[I")
	private int[] anIntArray236;

	@OriginalMember(owner = "client!hh", name = "w", descriptor = "[[I")
	private int[][] anIntArrayArray19;

	@OriginalMember(owner = "client!hh", name = "x", descriptor = "Z")
	public boolean aBoolean141;

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(IIIILclient!tk;III)Lclient!ak;")
	public final Model method1946(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) SeqType arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(24) long local24 = (long) arg2 | (long) (arg6 << 16) | (long) arg1 << 32;
		@Pc(30) Model local30 = (Model) Static139.aClass99_21.get(local24);
		if (local30 == null) {
			@Pc(36) ModelUnlit[] local36 = new ModelUnlit[3];
			@Pc(38) int local38 = 0;
			if (!com.jagex.runetek4.cache.def.ItemDefinition.get(arg2).hasReadyHeads() || !com.jagex.runetek4.cache.def.ItemDefinition.get(arg6).hasReadyHeads() || !com.jagex.runetek4.cache.def.ItemDefinition.get(arg1).hasReadyHeads()) {
				return null;
			}
			@Pc(66) ModelUnlit local66 = com.jagex.runetek4.cache.def.ItemDefinition.get(arg2).getHeadModel();
			if (local66 != null) {
				local38++;
				local36[0] = local66;
			}
			local66 = com.jagex.runetek4.cache.def.ItemDefinition.get(arg6).getHeadModel();
			if (local66 != null) {
				local36[local38++] = local66;
			}
			local66 = com.jagex.runetek4.cache.def.ItemDefinition.get(arg1).getHeadModel();
			if (local66 != null) {
				local36[local38++] = local66;
			}
			local66 = new ModelUnlit(local36, local38);
			for (@Pc(110) int local110 = 0; local110 < 5; local110++) {
				if (this.anIntArray236[local110] < Static33.aShortArrayArray2[local110].length) {
					local66.recolor(Static200.aShortArray65[local110], Static33.aShortArrayArray2[local110][this.anIntArray236[local110]]);
				}
				if (Static172.aShortArrayArray7[local110].length > this.anIntArray236[local110]) {
					local66.recolor(Static160.aShortArray41[local110], Static172.aShortArrayArray7[local110][this.anIntArray236[local110]]);
				}
			}
			local30 = local66.applyLightning(64, 768, -50, -10, -50);
			Static139.aClass99_21.put(local30, local24);
		}
		if (arg4 != null) {
			local30 = arg4.method4215(local30, arg0, arg3, arg5);
		}
		return local30;
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(I)V")
	private void method1947() {
		@Pc(8) long local8 = this.aLong88;
		this.aLong88 = -1L;
		@Pc(13) long[] local13 = Packet.CRC64_TABLE;
		this.aLong88 = local13[(int) (((long) (this.anInt2497 >> 8) ^ this.aLong88) & 0xFFL)] ^ this.aLong88 >>> 8;
		this.aLong88 = local13[(int) ((this.aLong88 ^ (long) this.anInt2497) & 0xFFL)] ^ this.aLong88 >>> 8;
		@Pc(53) int local53;
		for (local53 = 0; local53 < 12; local53++) {
			this.aLong88 = this.aLong88 >>> 8 ^ local13[(int) ((this.aLong88 ^ (long) (this.appearance[local53] >> 24)) & 0xFFL)];
			this.aLong88 = this.aLong88 >>> 8 ^ local13[(int) ((this.aLong88 ^ (long) (this.appearance[local53] >> 16)) & 0xFFL)];
			this.aLong88 = local13[(int) (((long) (this.appearance[local53] >> 8) ^ this.aLong88) & 0xFFL)] ^ this.aLong88 >>> 8;
			this.aLong88 = this.aLong88 >>> 8 ^ local13[(int) ((this.aLong88 ^ (long) this.appearance[local53]) & 0xFFL)];
		}
		for (local53 = 0; local53 < 5; local53++) {
			this.aLong88 = local13[(int) (((long) this.anIntArray236[local53] ^ this.aLong88) & 0xFFL)] ^ this.aLong88 >>> 8;
		}
		this.aLong88 = local13[(int) (((long) (this.aBoolean141 ? 1 : 0) ^ this.aLong88) & 0xFFL)] ^ this.aLong88 >>> 8;
		if (local8 != 0L && this.aLong88 != local8) {
			Static250.aClass99_33.remove(local8);
		}
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(ZZ)V")
	public final void method1948(@OriginalArg(0) boolean arg0) {
		this.aBoolean141 = arg0;
		this.method1947();
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "([IIZI[II)V")
	public final void method1950(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) boolean arg2, @OriginalArg(4) int[] arg3, @OriginalArg(5) int arg4) {
		if (arg4 != this.anInt2497) {
			this.anInt2497 = arg4;
			this.anIntArrayArray19 = null;
		}
		if (arg3 == null) {
			arg3 = new int[12];
			for (@Pc(24) int local24 = 0; local24 < 8; local24++) {
				for (@Pc(31) int local31 = 0; local31 < Static53.anInt1716; local31++) {
					@Pc(38) IDKType local38 = com.jagex.runetek4.cache.def.ItemDefinition.get(local31);
					if (local38 != null && !local38.disable && local38.type == (arg2 ? Static153.anIntArray351[local24] : Static204.anIntArray425[local24])) {
						arg3[Static272.anIntArray451[local24]] = Integer.MIN_VALUE | local31;
						break;
					}
				}
			}
		}
		this.transformationNpcId = arg1;
		this.aBoolean141 = arg2;
		this.anIntArray236 = arg0;
		this.appearance = arg3;
		this.method1947();
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(IZI)V")
	public final void method1951(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		this.anIntArray236[arg0] = arg1;
		this.method1947();
	}

	@OriginalMember(owner = "client!hh", name = "b", descriptor = "(I)I")
	public final int getHeadModelId() {
		return this.transformationNpcId == -1 ? (this.appearance[8] << 10) + ((this.anIntArray236[0] << 25) + (this.anIntArray236[4] << 20)) + (this.appearance[0] << 15) + (this.appearance[11] << 5) + this.appearance[1] : NpcType.getDefinition(this.transformationNpcId).id + 305419896;
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(III)V")
	public final void method1953(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(7) int local7 = Static272.anIntArray451[arg0];
		if (0 != this.appearance[local7] && com.jagex.runetek4.cache.def.ItemDefinition.get(arg1) != null) {
			this.appearance[local7] = arg1 | Integer.MIN_VALUE;
			this.method1947();
		}
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "([Lclient!ub;ILclient!tk;Lclient!tk;IIIIZII)Lclient!ak;")
	public final Model method1954(@OriginalArg(0) Class147[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) SeqType arg2, @OriginalArg(3) SeqType arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(7) int arg6, @OriginalArg(9) int arg7, @OriginalArg(10) int arg8) {
		if (this.transformationNpcId != -1) {
			return NpcType.getDefinition(this.transformationNpcId).method2937(arg0, arg5, arg8, arg1, arg6, arg7, arg2, arg4, arg3);
		}
		@Pc(38) int[] local38 = this.appearance;
		@Pc(41) long local41 = this.aLong88;
		if (arg3 != null && (arg3.mainhand >= 0 || arg3.offhand >= 0)) {
			local38 = new int[12];
			for (@Pc(61) int local61 = 0; local61 < 12; local61++) {
				local38[local61] = this.appearance[local61];
			}
			if (arg3.mainhand >= 0) {
				if (arg3.mainhand == 65535) {
					local41 ^= 0xFFFFFFFF00000000L;
					local38[5] = 0;
				} else {
					local38[5] = arg3.mainhand | 0x40000000;
					local41 ^= (long) local38[5] << 32;
				}
			}
			if (arg3.offhand >= 0) {
				if (arg3.offhand == 65535) {
					local38[3] = 0;
					local41 ^= 0xFFFFFFFFL;
				} else {
					local38[3] = arg3.offhand | 0x40000000;
					local41 ^= local38[3];
				}
			}
		}
		@Pc(154) Model local154 = (Model) Static250.aClass99_33.get(local41);
		@Pc(158) boolean local158;
		@Pc(353) int local353;
		@Pc(360) int local360;
		@Pc(374) int local374;
		@Pc(367) int local367;
		@Pc(381) int local381;
		@Pc(451) int local451;
		@Pc(457) int local457;
		@Pc(475) int local475;
		@Pc(481) int local481;
		@Pc(598) int local598;
		@Pc(346) int local346;
		if (local154 == null) {
			local158 = false;
			@Pc(169) int local169;
			for (@Pc(160) int local160 = 0; local160 < 12; local160++) {
				local169 = local38[local160];
				if ((local169 & 0x40000000) == 0) {
					if ((local169 & Integer.MIN_VALUE) != 0 && !com.jagex.runetek4.cache.def.ItemDefinition.get(local169 & 0x3FFFFFFF).hasReadyModels()) {
						local158 = true;
					}
				} else if (!Static71.get(local169 & 0x3FFFFFFF).method1822(this.aBoolean141)) {
					local158 = true;
				}
			}
			if (local158) {
				if (this.aLong87 != -1L) {
					local154 = (Model) Static250.aClass99_33.get(this.aLong87);
				}
				if (local154 == null) {
					return null;
				}
			}
			if (local154 == null) {
				@Pc(239) ModelUnlit[] local239 = new ModelUnlit[12];
				@Pc(250) int local250;
				for (local169 = 0; local169 < 12; local169++) {
					local250 = local38[local169];
					@Pc(272) ModelUnlit local272;
					if ((local250 & 0x40000000) != 0) {
						local272 = Static71.get(local250 & 0x3FFFFFFF).method1831(this.aBoolean141);
						if (local272 != null) {
							local239[local169] = local272;
						}
					} else if ((Integer.MIN_VALUE & local250) != 0) {
						local272 = com.jagex.runetek4.cache.def.ItemDefinition.get(local250 & 0x3FFFFFFF).getModel();
						if (local272 != null) {
							local239[local169] = local272;
						}
					}
				}
				@Pc(303) BasType local303 = null;
				if (this.anInt2497 != -1) {
					local303 = BasTypeList.get(this.anInt2497);
				}
				if (local303 != null && local303.modelRotateTranslate != null) {
					for (local250 = 0; local250 < local303.modelRotateTranslate.length; local250++) {
						if (local303.modelRotateTranslate[local250] != null && local239[local250] != null) {
							local346 = local303.modelRotateTranslate[local250][0];
							local353 = local303.modelRotateTranslate[local250][1];
							local360 = local303.modelRotateTranslate[local250][2];
							local367 = local303.modelRotateTranslate[local250][4];
							local374 = local303.modelRotateTranslate[local250][3];
							local381 = local303.modelRotateTranslate[local250][5];
							if (this.anIntArrayArray19 == null) {
								this.anIntArrayArray19 = new int[local303.modelRotateTranslate.length][];
							}
							if (this.anIntArrayArray19[local250] == null) {
								@Pc(404) int[] local404 = this.anIntArrayArray19[local250] = new int[15];
								if (local374 == 0 && local367 == 0 && local381 == 0) {
									local404[12] = -local346;
									local404[13] = -local353;
									local404[0] = local404[4] = local404[8] = 32768;
									local404[14] = -local360;
								} else {
									local451 = MathUtils.cos[local374] >> 1;
									local457 = MathUtils.sin[local374] >> 1;
									@Pc(463) int local463 = MathUtils.cos[local367] >> 1;
									@Pc(469) int local469 = MathUtils.sin[local367] >> 1;
									local475 = MathUtils.cos[local381] >> 1;
									local481 = MathUtils.sin[local381] >> 1;
									local404[4] = local451 * local475 + 16384 >> 15;
									local404[5] = -local457;
									local404[3] = local481 * local451 + 16384 >> 15;
									local404[2] = local451 * local469 + 16384 >> 15;
									local404[8] = local463 * local451 + 16384 >> 15;
									@Pc(534) int local534 = local481 * local457 + 16384 >> 15;
									local404[0] = local469 * local534 + local475 * local463 + 16384 >> 15;
									local404[14] = local404[8] * -local360 + -local353 * local404[5] + local404[2] * -local346 + 16384 >> 15;
									local404[6] = local463 * local534 + local475 * -local469 + 16384 >> 15;
									local598 = local475 * local457 + 16384 >> 15;
									local404[7] = -local481 * -local469 + local598 * local463 + 16384 >> 15;
									local404[1] = local469 * local598 + local463 * -local481 + 16384 >> 15;
									local404[12] = -local353 * local404[3] + local404[0] * -local346 + -local360 * local404[6] + 16384 >> 15;
									local404[13] = -local353 * local404[4] + local404[1] * -local346 + -local360 * local404[7] + 16384 >> 15;
								}
								local404[9] = local346;
								local404[11] = local360;
								local404[10] = local353;
							}
							if (local374 != 0 || local367 != 0 || local381 != 0) {
								local239[local250].method1684(local374, local367, local381);
							}
							if (local346 != 0 || local353 != 0 || local360 != 0) {
								local239[local250].translate(local346, local353, local360);
							}
						}
					}
				}
				@Pc(740) ModelUnlit model = new ModelUnlit(local239, local239.length);
				for (local346 = 0; local346 < 5; local346++) {
					if (Static33.aShortArrayArray2[local346].length > this.anIntArray236[local346]) {
						model.recolor(Static200.aShortArray65[local346], Static33.aShortArrayArray2[local346][this.anIntArray236[local346]]);
					}
					if (Static172.aShortArrayArray7[local346].length > this.anIntArray236[local346]) {
						model.recolor(Static160.aShortArray41[local346], Static172.aShortArrayArray7[local346][this.anIntArray236[local346]]);
					}
				}
				local154 = model.applyLightning(64, 850, -30, -50, -30);
				if (GlRenderer.enabled) {
					((GlModel) local154).method4111(false, false, true, false, false, true);
				}
				Static250.aClass99_33.put(local154, local41);
				this.aLong87 = local41;
			}
		}
		local158 = false;
		@Pc(827) boolean local827 = false;
		local346 = arg0 == null ? 0 : arg0.length;
		@Pc(836) boolean local836 = false;
		@Pc(838) boolean local838 = false;
		@Pc(979) int local979;
		for (local353 = 0; local353 < local346; local353++) {
			if (arg0[local353] != null) {
				@Pc(858) SeqType local858 = SeqType.getAnimationSequence(arg0[local353].anInt5396);
				if (local858.anIntArray473 != null) {
					local158 = true;
					Static208.aClass144Array2[local353] = local858;
					local374 = arg0[local353].anInt5399;
					local367 = arg0[local353].anInt5398;
					local381 = local858.anIntArray473[local374];
					Static276.aClass3_Sub2_Sub7Array8[local353] = Static72.method1566(local381 >>> 16);
					local381 &= 0xFFFF;
					Static241.anIntArray520[local353] = local381;
					if (Static276.aClass3_Sub2_Sub7Array8[local353] != null) {
						local836 |= Static276.aClass3_Sub2_Sub7Array8[local353].isColorTransformed(local381);
						local827 |= Static276.aClass3_Sub2_Sub7Array8[local353].isAlphaTransformed(local381);
						local838 |= local858.aBoolean278;
					}
					if ((local858.aBoolean277 || SeqType.tween) && local367 != -1 && local367 < local858.anIntArray473.length) {
						Static262.anIntArray515[local353] = local858.frames[local374];
						Static73.anIntArray183[local353] = arg0[local353].anInt5404;
						local979 = local858.anIntArray473[local367];
						Static133.aClass3_Sub2_Sub7Array7[local353] = Static72.method1566(local979 >>> 16);
						@Pc(991) int local991 = local979 & 0xFFFF;
						Static216.anIntArray187[local353] = local991;
						if (Static133.aClass3_Sub2_Sub7Array7[local353] != null) {
							local836 |= Static133.aClass3_Sub2_Sub7Array7[local353].isColorTransformed(local991);
							local827 |= Static133.aClass3_Sub2_Sub7Array7[local353].isAlphaTransformed(local991);
						}
					} else {
						Static262.anIntArray515[local353] = 0;
						Static73.anIntArray183[local353] = 0;
						Static133.aClass3_Sub2_Sub7Array7[local353] = null;
						Static216.anIntArray187[local353] = -1;
					}
				}
			}
		}
		if (!local158 && arg3 == null && arg2 == null) {
			return local154;
		}
		local353 = -1;
		local360 = -1;
		local374 = 0;
		@Pc(1040) AnimFrameset local1040 = null;
		@Pc(1042) AnimFrameset local1042 = null;
		if (arg3 != null) {
			local353 = arg3.anIntArray473[arg7];
			local979 = local353 >>> 16;
			local1042 = Static72.method1566(local979);
			local353 &= 0xFFFF;
			if (local1042 != null) {
				local836 |= local1042.isColorTransformed(local353);
				local827 |= local1042.isAlphaTransformed(local353);
				local838 |= arg3.aBoolean278;
			}
			if ((arg3.aBoolean277 || SeqType.tween) && arg1 != -1 && arg3.anIntArray473.length > arg1) {
				local360 = arg3.anIntArray473[arg1];
				local451 = local360 >>> 16;
				local360 &= 0xFFFF;
				local374 = arg3.frames[arg7];
				if (local979 == local451) {
					local1040 = local1042;
				} else {
					local1040 = Static72.method1566(local360 >>> 16);
				}
				if (local1040 != null) {
					local836 |= local1040.isColorTransformed(local360);
					local827 |= local1040.isAlphaTransformed(local360);
				}
			}
		}
		local979 = -1;
		local451 = -1;
		@Pc(1154) AnimFrameset local1154 = null;
		@Pc(1156) AnimFrameset local1156 = null;
		local457 = 0;
		if (arg2 != null) {
			local979 = arg2.anIntArray473[arg8];
			local475 = local979 >>> 16;
			local979 &= 0xFFFF;
			local1154 = Static72.method1566(local475);
			if (local1154 != null) {
				local836 |= local1154.isColorTransformed(local979);
				local827 |= local1154.isAlphaTransformed(local979);
				local838 |= arg2.aBoolean278;
			}
			if ((arg2.aBoolean277 || SeqType.tween) && arg5 != -1 && arg2.anIntArray473.length > arg5) {
				local457 = arg2.frames[arg8];
				local451 = arg2.anIntArray473[arg5];
				local481 = local451 >>> 16;
				local451 &= 0xFFFF;
				if (local475 == local481) {
					local1156 = local1154;
				} else {
					local1156 = Static72.method1566(local451 >>> 16);
				}
				if (local1156 != null) {
					local836 |= local1156.isColorTransformed(local451);
					local827 |= local1156.isAlphaTransformed(local451);
				}
			}
		}
		@Pc(1284) Model local1284 = local154.method4572(!local827, !local836, !local838);
		local481 = 0;
		local598 = 1;
		while (local481 < local346) {
			if (Static276.aClass3_Sub2_Sub7Array8[local481] != null) {
				local1284.method4565(Static276.aClass3_Sub2_Sub7Array8[local481], Static241.anIntArray520[local481], Static133.aClass3_Sub2_Sub7Array7[local481], Static216.anIntArray187[local481], Static73.anIntArray183[local481] - 1, Static262.anIntArray515[local481], local598, Static208.aClass144Array2[local481].aBoolean278, this.anIntArrayArray19[local481]);
			}
			local481++;
			local598 <<= 0x1;
		}
		if (local1042 != null && local1154 != null) {
			local1284.method4570(local1042, local353, local1040, local360, arg6 - 1, local374, local1154, local979, local1156, local451, arg4 - 1, local457, arg3.aBooleanArray123, arg3.aBoolean278 | arg2.aBoolean278);
		} else if (local1042 != null) {
			local1284.method4558(local1042, local353, local1040, local360, arg6 - 1, local374, arg3.aBoolean278);
		} else if (local1154 != null) {
			local1284.method4558(local1154, local979, local1156, local451, arg4 - 1, local457, arg2.aBoolean278);
		}
		for (local481 = 0; local481 < local346; local481++) {
			Static276.aClass3_Sub2_Sub7Array8[local481] = null;
			Static133.aClass3_Sub2_Sub7Array7[local481] = null;
			Static208.aClass144Array2[local481] = null;
		}
		return local1284;
	}

	@OriginalMember(owner = "client!hh", name = "a", descriptor = "(IBLclient!tk;II)Lclient!ak;")
	public final Model getStaticModel(@OriginalArg(0) int arg0, @OriginalArg(2) SeqType arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (this.transformationNpcId != -1) {
			return NpcType.getDefinition(this.transformationNpcId).getHeadModel(arg1, arg0, arg3, arg2);
		}
		@Pc(30) Model cachedModel = (Model) Static139.aClass99_21.get(this.aLong88);
		if (cachedModel == null) {
			@Pc(42) boolean bool = false;
			@Pc(52) int i;
			for (@Pc(44) int local44 = 0; local44 < 12; local44++) {
				i = this.appearance[local44];
				if ((i & 0x40000000) == 0) {
					if ((i & Integer.MIN_VALUE) != 0 && !ItemDefinition.get(i & 0x3FFFFFFF).hasReadyHeads()) {
						bool = true;
					}
				} else if (!Static71.get(i & 0x3FFFFFFF).headPieceReady(this.aBoolean141)) {
					bool = true;
				}
			}
			if (bool) {
				return null;
			}
			@Pc(100) ModelUnlit[] models = new ModelUnlit[12];
			i = 0;
			@Pc(114) int j;
			for (@Pc(104) int equipmentSlot = 0; equipmentSlot < 12; equipmentSlot++) {
				j = this.appearance[equipmentSlot];
				@Pc(134) ModelUnlit local134;
				if ((j & 0x40000000) != 0) {
					local134 = Static71.get(j & 0x3FFFFFFF).method1830(this.aBoolean141);
					if (local134 != null) {
						models[i++] = local134;
					}
				} else if ((Integer.MIN_VALUE & j) != 0) {
					local134 = ItemDefinition.get(j & 0x3FFFFFFF).getHeadModel();
					if (local134 != null) {
						models[i++] = local134;
					}
				}
			}
			@Pc(171) ModelUnlit local171 = new ModelUnlit(models, i);
			for (j = 0; j < 5; j++) {
				if (Static33.aShortArrayArray2[j].length > this.anIntArray236[j]) {
					local171.recolor(Static200.aShortArray65[j], Static33.aShortArrayArray2[j][this.anIntArray236[j]]);
				}
				if (Static172.aShortArrayArray7[j].length > this.anIntArray236[j]) {
					local171.recolor(Static160.aShortArray41[j], Static172.aShortArrayArray7[j][this.anIntArray236[j]]);
				}
			}
			cachedModel = local171.applyLightning(64, 768, -50, -10, -50);
			Static139.aClass99_21.put(cachedModel, this.aLong88);
		}
		if (arg1 != null) {
			cachedModel = arg1.method4215(cachedModel, arg3, arg0, arg2);
		}
		return cachedModel;
	}
}
