package com.jagex.runetek4.cache.media;

import com.jagex.runetek4.*;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!mm")
public class SoftwareSprite extends Sprite {

	@OriginalMember(owner = "runetek4.client!mm", name = "L", descriptor = "[I")
	public int[] pixels;

	@OriginalMember(owner = "runetek4.client!mm", name = "<init>", descriptor = "(IIIIII[I)V")
	public SoftwareSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int[] arg6) {
		this.innerWidth = arg0;
		this.innerHeight = arg1;
		this.xOffset = arg2;
		this.yOffset = arg3;
		this.width = arg4;
		this.height = arg5;
		this.pixels = arg6;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "<init>", descriptor = "(II)V")
	public SoftwareSprite(@OriginalArg(0) int width, @OriginalArg(1) int height) {
		this.pixels = new int[width * height];
		this.width = this.innerWidth = width;
		this.height = this.innerHeight = height;
		this.xOffset = this.yOffset = 0;
	}

	@OriginalMember(owner = "runetek4.client!dd", name = "a", descriptor = "([I[BIIIIIII)V")
	public static void blockCopyMask(@OriginalArg(0) int[] arg0, @OriginalArg(1) byte[] arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		@Pc(4) int local4 = -(arg5 >> 2);
		@Pc(9) int local9 = -(arg5 & 0x3);
		for (@Pc(12) int local12 = -arg6; local12 < 0; local12++) {
			@Pc(16) int local16;
			for (local16 = local4; local16 < 0; local16++) {
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
			}
			for (local16 = local9; local16 < 0; local16++) {
				if (arg1[arg3++] == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2;
				}
			}
			arg4 += arg7;
			arg3 += arg8;
		}
	}

	@OriginalMember(owner = "runetek4.client!ek", name = "a", descriptor = "([I[B[IIIIIIII)V")
	public static void blockCopyTrans(@OriginalArg(0) int[] arg0, @OriginalArg(1) byte[] arg1, @OriginalArg(2) int[] arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5, @OriginalArg(7) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8) {
		@Pc(4) int local4 = -(arg5 >> 2);
		@Pc(9) int local9 = -(arg5 & 0x3);
		for (@Pc(12) int local12 = -arg6; local12 < 0; local12++) {
			@Pc(16) int local16;
			@Pc(23) byte local23;
			for (local16 = local4; local16 < 0; local16++) {
				local23 = arg1[arg3++];
				if (local23 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2[local23 & 0xFF];
				}
				local23 = arg1[arg3++];
				if (local23 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2[local23 & 0xFF];
				}
				local23 = arg1[arg3++];
				if (local23 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2[local23 & 0xFF];
				}
				local23 = arg1[arg3++];
				if (local23 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2[local23 & 0xFF];
				}
			}
			for (local16 = local9; local16 < 0; local16++) {
				local23 = arg1[arg3++];
				if (local23 == 0) {
					arg4++;
				} else {
					arg0[arg4++] = arg2[local23 & 0xFF];
				}
			}
			arg4 += arg7;
			arg3 += arg8;
		}
	}

	@OriginalMember(owner = "runetek4.client!gf", name = "a", descriptor = "(Lclient!ve;IIB)Lclient!mm;")
	public static SoftwareSprite loadSoftwareAlphaSprite(@OriginalArg(0) Js5 arg0, @OriginalArg(2) int arg1) {
		return SpriteLoader.decode(arg0, 0, arg1) ? Static102.method2071() : null;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "d", descriptor = "(I)V")
	public final void method298(@OriginalArg(0) int arg0) {
		if (this.width == this.innerWidth && this.height == this.innerHeight) {
			return;
		}
		@Pc(12) int local12 = arg0;
		if (arg0 > this.xOffset) {
			local12 = this.xOffset;
		}
		@Pc(21) int local21 = arg0;
		if (arg0 + this.xOffset + this.width > this.innerWidth) {
			local21 = this.innerWidth - this.xOffset - this.width;
		}
		@Pc(42) int local42 = arg0;
		if (arg0 > this.yOffset) {
			local42 = this.yOffset;
		}
		@Pc(51) int local51 = arg0;
		if (arg0 + this.yOffset + this.height > this.innerHeight) {
			local51 = this.innerHeight - this.yOffset - this.height;
		}
		@Pc(77) int local77 = this.width + local12 + local21;
		@Pc(84) int local84 = this.height + local42 + local51;
		@Pc(89) int[] local89 = new int[local77 * local84];
		for (@Pc(91) int local91 = 0; local91 < this.height; local91++) {
			for (@Pc(97) int local97 = 0; local97 < this.width; local97++) {
				local89[(local91 + local42) * local77 + local97 + local12] = this.pixels[local91 * this.width + local97];
			}
		}
		this.pixels = local89;
		this.width = local77;
		this.height = local84;
		this.xOffset -= local12;
		this.yOffset -= local42;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "()V")
	public final void method299() {
		@Pc(6) int[] local6 = new int[this.width * this.height];
		@Pc(8) int local8 = 0;
		for (@Pc(10) int local10 = 0; local10 < this.height; local10++) {
			for (@Pc(19) int local19 = this.width - 1; local19 >= 0; local19--) {
				local6[local8++] = this.pixels[local19 + local10 * this.width];
			}
		}
		this.pixels = local6;
		this.xOffset = this.innerWidth - this.width - this.xOffset;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "c", descriptor = "(II)V")
	@Override
	public void render(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg0 += this.xOffset;
		arg1 += this.yOffset;
		@Pc(15) int local15 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(17) int local17 = 0;
		@Pc(20) int local20 = this.height;
		@Pc(23) int local23 = this.width;
		@Pc(27) int local27 = SoftwareRaster.destinationWidth - local23;
		@Pc(29) int local29 = 0;
		@Pc(36) int local36;
		if (arg1 < Rasterizer.viewportTop) {
			local36 = Rasterizer.viewportTop - arg1;
			local20 -= local36;
			arg1 = Rasterizer.viewportTop;
			local17 = local36 * local23;
			local15 += local36 * SoftwareRaster.destinationWidth;
		}
		if (arg1 + local20 > Rasterizer.viewportBottom) {
			local20 -= arg1 + local20 - Rasterizer.viewportBottom;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local36 = Rasterizer.viewportLeft - arg0;
			local23 -= local36;
			arg0 = Rasterizer.viewportLeft;
			local17 += local36;
			local15 += local36;
			local29 = local36;
			local27 += local36;
		}
		if (arg0 + local23 > Rasterizer.viewportRight) {
			local36 = arg0 + local23 - Rasterizer.viewportRight;
			local23 -= local36;
			local29 += local36;
			local27 += local36;
		}
		if (local23 > 0 && local20 > 0) {
			Static290.method305(SoftwareRaster.destinationPixels, this.pixels, local17, local15, local23, local20, local27, local29);
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "b", descriptor = "()[I")
	public final int[] method301() {
		@Pc(6) int[] local6 = new int[this.innerWidth * this.innerHeight];
		for (@Pc(8) int local8 = 0; local8 < this.height; local8++) {
			@Pc(17) int local17 = local8 * this.width;
			@Pc(28) int local28 = this.xOffset + (local8 + this.yOffset) * this.innerWidth;
			for (@Pc(30) int local30 = 0; local30 < this.width; local30++) {
				@Pc(40) int local40 = this.pixels[local17++];
				local6[local28++] = local40 == 0 ? 0 : local40 | 0xFF000000;
			}
		}
		return local6;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "(IIIIII)V")
	@Override
	protected void method1416(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		if (arg5 == 0) {
			return;
		}
		@Pc(9) int local9 = arg0 - (this.xOffset << 4);
		@Pc(16) int local16 = arg1 - (this.yOffset << 4);
		@Pc(23) double local23 = (double) (arg4 & 0xFFFF) * 9.587379924285257E-5D;
		@Pc(33) int local33 = (int) Math.floor(Math.sin(local23) * (double) arg5 + 0.5D);
		@Pc(43) int local43 = (int) Math.floor(Math.cos(local23) * (double) arg5 + 0.5D);
		@Pc(53) int local53 = -local9 * local43 + -local16 * local33;
		@Pc(64) int local64 = --local9 * local33 + -local16 * local43;
		@Pc(78) int local78 = ((this.width << 4) - local9) * local43 + -local16 * local33;
		@Pc(93) int local93 = -((this.width << 4) - local9) * local33 + -local16 * local43;
		@Pc(107) int local107 = -local9 * local43 + ((this.height << 4) - local16) * local33;
		@Pc(122) int local122 = --local9 * local33 + ((this.height << 4) - local16) * local43;
		@Pc(140) int local140 = ((this.width << 4) - local9) * local43 + ((this.height << 4) - local16) * local33;
		@Pc(159) int local159 = -((this.width << 4) - local9) * local33 + ((this.height << 4) - local16) * local43;
		@Pc(164) int local164;
		@Pc(166) int local166;
		if (local53 < local78) {
			local164 = local53;
			local166 = local78;
		} else {
			local164 = local78;
			local166 = local53;
		}
		if (local107 < local164) {
			local164 = local107;
		}
		if (local140 < local164) {
			local164 = local140;
		}
		if (local107 > local166) {
			local166 = local107;
		}
		if (local140 > local166) {
			local166 = local140;
		}
		@Pc(196) int local196;
		@Pc(198) int local198;
		if (local64 < local93) {
			local196 = local64;
			local198 = local93;
		} else {
			local196 = local93;
			local198 = local64;
		}
		if (local122 < local196) {
			local196 = local122;
		}
		if (local159 < local196) {
			local196 = local159;
		}
		if (local122 > local198) {
			local198 = local122;
		}
		if (local159 > local198) {
			local198 = local159;
		}
		local164 >>= 0xC;
		local166 = local166 + 4095 >> 12;
		local196 >>= 0xC;
		local198 = local198 + 4095 >> 12;
		local164 += arg2;
		local166 += arg2;
		local196 += arg3;
		local198 += arg3;
		local164 >>= 0x4;
		local166 = local166 + 15 >> 4;
		local196 >>= 0x4;
		local198 = local198 + 15 >> 4;
		if (local164 < Rasterizer.viewportLeft) {
			local164 = Rasterizer.viewportLeft;
		}
		if (local166 > Rasterizer.viewportRight) {
			local166 = Rasterizer.viewportRight;
		}
		if (local196 < Rasterizer.viewportTop) {
			local196 = Rasterizer.viewportTop;
		}
		if (local198 > Rasterizer.viewportBottom) {
			local198 = Rasterizer.viewportBottom;
		}
		local166 = local164 - local166;
		if (local166 >= 0) {
			return;
		}
		local198 = local196 - local198;
		if (local198 >= 0) {
			return;
		}
		@Pc(319) int local319 = local196 * SoftwareRaster.destinationWidth + local164;
		@Pc(324) double local324 = 1.6777216E7D / (double) arg5;
		@Pc(333) int local333 = (int) Math.floor(Math.sin(local23) * local324 + 0.5D);
		@Pc(342) int local342 = (int) Math.floor(Math.cos(local23) * local324 + 0.5D);
		@Pc(350) int local350 = (local164 << 4) + 8 - arg2;
		@Pc(358) int local358 = (local196 << 4) + 8 - arg3;
		@Pc(368) int local368 = (local9 << 8) - (local358 * local333 >> 4);
		@Pc(378) int local378 = (local16 << 8) + (local358 * local342 >> 4);
		@Pc(384) int local384;
		@Pc(388) int local388;
		@Pc(468) int local468;
		@Pc(394) int local394;
		@Pc(432) int local432;
		@Pc(496) int local496;
		if (local342 != 0) {
			@Pc(694) int local694;
			if (local342 < 0) {
				if (local333 == 0) {
					local384 = local198;
					while (local384 < 0) {
						local388 = local319;
						local694 = local368 + (local350 * local342 >> 4);
						local394 = local166;
						if (local378 >= 0 && local378 - (this.height << 12) < 0) {
							@Pc(717) int local717;
							if ((local717 = local694 - (this.width << 12)) >= 0) {
								local496 = (local342 - local717) / local342;
								local394 = local166 + local496;
								local694 += local342 * local496;
								local388 = local319 + local496;
							}
							@Pc(745) int local745;
							if ((local745 = (local694 - local342) / local342) > local394) {
								local394 = local745;
							}
							while (local394 < 0) {
								local432 = this.pixels[(local378 >> 12) * this.width + (local694 >> 12)];
								if (local432 == 0) {
									local388++;
								} else {
									SoftwareRaster.destinationPixels[local388++] = local432;
								}
								local694 += local342;
								local394++;
							}
						}
						local384++;
						local378 += local342;
						local319 += SoftwareRaster.destinationWidth;
					}
				} else if (local333 < 0) {
					local384 = local198;
					while (local384 < 0) {
						local388 = local319;
						local694 = local368 + (local350 * local342 >> 4);
						local468 = local378 + (local350 * local333 >> 4);
						local394 = local166;
						@Pc(825) int local825;
						if ((local825 = local694 - (this.width << 12)) >= 0) {
							local496 = (local342 - local825) / local342;
							local394 = local166 + local496;
							local694 += local342 * local496;
							local468 += local333 * local496;
							local388 = local319 + local496;
						}
						@Pc(859) int local859;
						if ((local859 = (local694 - local342) / local342) > local394) {
							local394 = local859;
						}
						@Pc(871) int local871;
						if ((local871 = local468 - (this.height << 12)) >= 0) {
							local496 = (local333 - local871) / local333;
							local394 += local496;
							local694 += local342 * local496;
							local468 += local333 * local496;
							local388 += local496;
						}
						@Pc(905) int local905;
						if ((local905 = (local468 - local333) / local333) > local394) {
							local394 = local905;
						}
						while (local394 < 0) {
							local432 = this.pixels[(local468 >> 12) * this.width + (local694 >> 12)];
							if (local432 == 0) {
								local388++;
							} else {
								SoftwareRaster.destinationPixels[local388++] = local432;
							}
							local694 += local342;
							local468 += local333;
							local394++;
						}
						local384++;
						local368 -= local333;
						local378 += local342;
						local319 += SoftwareRaster.destinationWidth;
					}
				} else {
					local384 = local198;
					while (local384 < 0) {
						local388 = local319;
						local694 = local368 + (local350 * local342 >> 4);
						local468 = local378 + (local350 * local333 >> 4);
						local394 = local166;
						@Pc(991) int local991;
						if ((local991 = local694 - (this.width << 12)) >= 0) {
							local496 = (local342 - local991) / local342;
							local394 = local166 + local496;
							local694 += local342 * local496;
							local468 += local333 * local496;
							local388 = local319 + local496;
						}
						@Pc(1025) int local1025;
						if ((local1025 = (local694 - local342) / local342) > local394) {
							local394 = local1025;
						}
						if (local468 < 0) {
							local496 = (local333 - local468 - 1) / local333;
							local394 += local496;
							local694 += local342 * local496;
							local468 += local333 * local496;
							local388 += local496;
						}
						@Pc(1073) int local1073;
						if ((local1073 = (local468 + 1 - (this.height << 12) - local333) / local333) > local394) {
							local394 = local1073;
						}
						while (local394 < 0) {
							local432 = this.pixels[(local468 >> 12) * this.width + (local694 >> 12)];
							if (local432 == 0) {
								local388++;
							} else {
								SoftwareRaster.destinationPixels[local388++] = local432;
							}
							local694 += local342;
							local468 += local333;
							local394++;
						}
						local384++;
						local368 -= local333;
						local378 += local342;
						local319 += SoftwareRaster.destinationWidth;
					}
				}
			} else if (local333 == 0) {
				local384 = local198;
				while (local384 < 0) {
					local388 = local319;
					local694 = local368 + (local350 * local342 >> 4);
					local394 = local166;
					if (local378 >= 0 && local378 - (this.height << 12) < 0) {
						if (local694 < 0) {
							local496 = (local342 - local694 - 1) / local342;
							local394 = local166 + local496;
							local694 += local342 * local496;
							local388 = local319 + local496;
						}
						@Pc(1196) int local1196;
						if ((local1196 = (local694 + 1 - (this.width << 12) - local342) / local342) > local394) {
							local394 = local1196;
						}
						while (local394 < 0) {
							local432 = this.pixels[(local378 >> 12) * this.width + (local694 >> 12)];
							if (local432 == 0) {
								local388++;
							} else {
								SoftwareRaster.destinationPixels[local388++] = local432;
							}
							local694 += local342;
							local394++;
						}
					}
					local384++;
					local378 += local342;
					local319 += SoftwareRaster.destinationWidth;
				}
			} else if (local333 < 0) {
				local384 = local198;
				while (local384 < 0) {
					local388 = local319;
					local694 = local368 + (local350 * local342 >> 4);
					local468 = local378 + (local350 * local333 >> 4);
					local394 = local166;
					if (local694 < 0) {
						local496 = (local342 - local694 - 1) / local342;
						local394 = local166 + local496;
						local694 += local342 * local496;
						local468 += local333 * local496;
						local388 = local319 + local496;
					}
					@Pc(1312) int local1312;
					if ((local1312 = (local694 + 1 - (this.width << 12) - local342) / local342) > local394) {
						local394 = local1312;
					}
					@Pc(1324) int local1324;
					if ((local1324 = local468 - (this.height << 12)) >= 0) {
						local496 = (local333 - local1324) / local333;
						local394 += local496;
						local694 += local342 * local496;
						local468 += local333 * local496;
						local388 += local496;
					}
					@Pc(1358) int local1358;
					if ((local1358 = (local468 - local333) / local333) > local394) {
						local394 = local1358;
					}
					while (local394 < 0) {
						local432 = this.pixels[(local468 >> 12) * this.width + (local694 >> 12)];
						if (local432 == 0) {
							local388++;
						} else {
							SoftwareRaster.destinationPixels[local388++] = local432;
						}
						local694 += local342;
						local468 += local333;
						local394++;
					}
					local384++;
					local368 -= local333;
					local378 += local342;
					local319 += SoftwareRaster.destinationWidth;
				}
			} else {
				local384 = local198;
				while (local384 < 0) {
					local388 = local319;
					local694 = local368 + (local350 * local342 >> 4);
					local468 = local378 + (local350 * local333 >> 4);
					local394 = local166;
					if (local694 < 0) {
						local496 = (local342 - local694 - 1) / local342;
						local394 = local166 + local496;
						local694 += local342 * local496;
						local468 += local333 * local496;
						local388 = local319 + local496;
					}
					@Pc(1480) int local1480;
					if ((local1480 = (local694 + 1 - (this.width << 12) - local342) / local342) > local394) {
						local394 = local1480;
					}
					if (local468 < 0) {
						local496 = (local333 - local468 - 1) / local333;
						local394 += local496;
						local694 += local342 * local496;
						local468 += local333 * local496;
						local388 += local496;
					}
					@Pc(1528) int local1528;
					if ((local1528 = (local468 + 1 - (this.height << 12) - local333) / local333) > local394) {
						local394 = local1528;
					}
					while (local394 < 0) {
						local432 = this.pixels[(local468 >> 12) * this.width + (local694 >> 12)];
						if (local432 == 0) {
							local388++;
						} else {
							SoftwareRaster.destinationPixels[local388++] = local432;
						}
						local694 += local342;
						local468 += local333;
						local394++;
					}
					local384++;
					local368 -= local333;
					local378 += local342;
					local319 += SoftwareRaster.destinationWidth;
				}
			}
		} else if (local333 == 0) {
			local384 = local198;
			while (local384 < 0) {
				local388 = local319;
				local394 = local166;
				if (local368 >= 0 && local378 >= 0 && local368 - (this.width << 12) < 0 && local378 - (this.height << 12) < 0) {
					while (local394 < 0) {
						local432 = this.pixels[(local378 >> 12) * this.width + (local368 >> 12)];
						if (local432 == 0) {
							local388++;
						} else {
							SoftwareRaster.destinationPixels[local388++] = local432;
						}
						local394++;
					}
				}
				local384++;
				local319 += SoftwareRaster.destinationWidth;
			}
		} else if (local333 < 0) {
			local384 = local198;
			while (local384 < 0) {
				local388 = local319;
				local468 = local378 + (local350 * local333 >> 4);
				local394 = local166;
				if (local368 >= 0 && local368 - (this.width << 12) < 0) {
					@Pc(489) int local489;
					if ((local489 = local468 - (this.height << 12)) >= 0) {
						local496 = (local333 - local489) / local333;
						local394 = local166 + local496;
						local468 += local333 * local496;
						local388 = local319 + local496;
					}
					@Pc(517) int local517;
					if ((local517 = (local468 - local333) / local333) > local394) {
						local394 = local517;
					}
					while (local394 < 0) {
						local432 = this.pixels[(local468 >> 12) * this.width + (local368 >> 12)];
						if (local432 == 0) {
							local388++;
						} else {
							SoftwareRaster.destinationPixels[local388++] = local432;
						}
						local468 += local333;
						local394++;
					}
				}
				local384++;
				local368 -= local333;
				local319 += SoftwareRaster.destinationWidth;
			}
		} else {
			local384 = local198;
			while (local384 < 0) {
				local388 = local319;
				local468 = local378 + (local350 * local333 >> 4);
				local394 = local166;
				if (local368 >= 0 && local368 - (this.width << 12) < 0) {
					if (local468 < 0) {
						local496 = (local333 - local468 - 1) / local333;
						local394 = local166 + local496;
						local468 += local333 * local496;
						local388 = local319 + local496;
					}
					@Pc(630) int local630;
					if ((local630 = (local468 + 1 - (this.height << 12) - local333) / local333) > local394) {
						local394 = local630;
					}
					while (local394 < 0) {
						local432 = this.pixels[(local468 >> 12) * this.width + (local368 >> 12)];
						if (local432 == 0) {
							local388++;
						} else {
							SoftwareRaster.destinationPixels[local388++] = local432;
						}
						local468 += local333;
						local394++;
					}
				}
				local384++;
				local368 -= local333;
				local319 += SoftwareRaster.destinationWidth;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "e", descriptor = "(I)V")
	public final void drawOutline(@OriginalArg(0) int arg0) {
		@Pc(6) int[] local6 = new int[this.width * this.height];
		@Pc(8) int local8 = 0;
		for (@Pc(10) int local10 = 0; local10 < this.height; local10++) {
			for (@Pc(16) int local16 = 0; local16 < this.width; local16++) {
				@Pc(25) int local25 = this.pixels[local8];
				if (local25 == 0) {
					if (local16 > 0 && this.pixels[local8 - 1] != 0) {
						local25 = arg0;
					} else if (local10 > 0 && this.pixels[local8 - this.width] != 0) {
						local25 = arg0;
					} else if (local16 < this.width - 1 && this.pixels[local8 + 1] != 0) {
						local25 = arg0;
					} else if (local10 < this.height - 1 && this.pixels[local8 + this.width] != 0) {
						local25 = arg0;
					}
				}
				local6[local8++] = local25;
			}
		}
		this.pixels = local6;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "c", descriptor = "()V")
	public final void method304() {
		SoftwareRaster.setSize(this.pixels, this.width, this.height);
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "(IIIIIIDI)V")
	public void method306(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(6) double arg2) {
		try {
			@Pc(15) int local15 = (int) (Math.sin(arg2) * 65536.0D);
			@Pc(21) int local21 = (int) (Math.cos(arg2) * 65536.0D);
			@Pc(27) int local27 = local15 * 256 >> 8;
			@Pc(33) int local33 = local21 * 256 >> 8;
			@Pc(45) int local45 = local27 * -10 + local33 * -10 + 983040;
			@Pc(57) int local57 = local33 * -10 + 983040 - local27 * -10;
			@Pc(63) int local63 = arg0 + arg1 * SoftwareRaster.destinationWidth;
			for (@Pc(65) int local65 = 0; local65 < 20; local65++) {
				@Pc(70) int local70 = local63;
				@Pc(72) int local72 = local45;
				@Pc(74) int local74 = local57;
				for (@Pc(77) int local77 = -20; local77 < 0; local77++) {
					@Pc(93) int local93 = this.pixels[(local72 >> 16) + (local74 >> 16) * this.width];
					if (local93 == 0) {
						local70++;
					} else {
						SoftwareRaster.destinationPixels[local70++] = local93;
					}
					local72 += local33;
					local74 -= local27;
				}
				local45 += local27;
				local57 += local33;
				local63 += SoftwareRaster.destinationWidth;
			}
		} catch (@Pc(128) Exception local128) {
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "d", descriptor = "(II)V")
	@Override
	public void method1421(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg0 += this.innerWidth - this.width - this.xOffset;
		arg1 += this.yOffset;
		@Pc(21) int local21 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(26) int local26 = this.width - 1;
		@Pc(29) int local29 = this.height;
		@Pc(32) int local32 = this.width;
		@Pc(36) int local36 = SoftwareRaster.destinationWidth - local32;
		@Pc(40) int local40 = local32 + local32;
		@Pc(47) int local47;
		if (arg1 < Rasterizer.viewportTop) {
			local47 = Rasterizer.viewportTop - arg1;
			local29 -= local47;
			arg1 = Rasterizer.viewportTop;
			local26 += local47 * local32;
			local21 += local47 * SoftwareRaster.destinationWidth;
		}
		if (arg1 + local29 > Rasterizer.viewportBottom) {
			local29 -= arg1 + local29 - Rasterizer.viewportBottom;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local47 = Rasterizer.viewportLeft - arg0;
			local32 -= local47;
			arg0 = Rasterizer.viewportLeft;
			local26 -= local47;
			local21 += local47;
			local40 -= local47;
			local36 += local47;
		}
		if (arg0 + local32 > Rasterizer.viewportRight) {
			local47 = arg0 + local32 - Rasterizer.viewportRight;
			local32 -= local47;
			local40 -= local47;
			local36 += local47;
		}
		if (local32 > 0 && local29 > 0) {
			Static290.method302(SoftwareRaster.destinationPixels, this.pixels, local26, local21, local32, local29, local36, local40);
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "(III)V")
	@Override
	public void renderAlpha(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		arg0 += this.xOffset;
		arg1 += this.yOffset;
		@Pc(15) int local15 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(17) int local17 = 0;
		@Pc(20) int local20 = this.height;
		@Pc(23) int local23 = this.width;
		@Pc(27) int local27 = SoftwareRaster.destinationWidth - local23;
		@Pc(29) int local29 = 0;
		@Pc(36) int local36;
		if (arg1 < Rasterizer.viewportTop) {
			local36 = Rasterizer.viewportTop - arg1;
			local20 -= local36;
			arg1 = Rasterizer.viewportTop;
			local17 = local36 * local23;
			local15 += local36 * SoftwareRaster.destinationWidth;
		}
		if (arg1 + local20 > Rasterizer.viewportBottom) {
			local20 -= arg1 + local20 - Rasterizer.viewportBottom;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local36 = Rasterizer.viewportLeft - arg0;
			local23 -= local36;
			arg0 = Rasterizer.viewportLeft;
			local17 += local36;
			local15 += local36;
			local29 = local36;
			local27 += local36;
		}
		if (arg0 + local23 > Rasterizer.viewportRight) {
			local36 = arg0 + local23 - Rasterizer.viewportRight;
			local23 -= local36;
			local29 += local36;
			local27 += local36;
		}
		if (local23 > 0 && local20 > 0) {
			Static290.method308(SoftwareRaster.destinationPixels, this.pixels, local17, local15, local23, local20, local27, local29, arg2);
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "e", descriptor = "(II)V")
	@Override
	public void drawSprite(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		arg0 += this.xOffset;
		arg1 += this.yOffset;
		@Pc(15) int local15 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(17) int local17 = 0;
		@Pc(20) int local20 = this.height;
		@Pc(23) int local23 = this.width;
		@Pc(27) int local27 = SoftwareRaster.destinationWidth - local23;
		@Pc(29) int local29 = 0;
		@Pc(36) int local36;
		if (arg1 < Rasterizer.viewportTop) {
			local36 = Rasterizer.viewportTop - arg1;
			local20 -= local36;
			arg1 = Rasterizer.viewportTop;
			local17 = local36 * local23;
			local15 += local36 * SoftwareRaster.destinationWidth;
		}
		if (arg1 + local20 > Rasterizer.viewportBottom) {
			local20 -= arg1 + local20 - Rasterizer.viewportBottom;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local36 = Rasterizer.viewportLeft - arg0;
			local23 -= local36;
			arg0 = Rasterizer.viewportLeft;
			local17 += local36;
			local15 += local36;
			local29 = local36;
			local27 += local36;
		}
		if (arg0 + local23 > Rasterizer.viewportRight) {
			local36 = arg0 + local23 - Rasterizer.viewportRight;
			local23 -= local36;
			local29 += local36;
			local27 += local36;
		}
		if (local23 > 0 && local20 > 0) {
			Static290.method300(SoftwareRaster.destinationPixels, this.pixels, local17, local15, local23, local20, local27, local29);
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "d", descriptor = "()V")
	public final void method309() {
		@Pc(6) int[] local6 = new int[this.width * this.height];
		@Pc(8) int local8 = 0;
		for (@Pc(13) int local13 = this.height - 1; local13 >= 0; local13--) {
			for (@Pc(17) int local17 = 0; local17 < this.width; local17++) {
				local6[local8++] = this.pixels[local17 + local13 * this.width];
			}
		}
		this.pixels = local6;
		this.yOffset = this.innerHeight - this.height - this.yOffset;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "(IIIIIIII[I[I)V")
	public void method310(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int[] arg8, @OriginalArg(9) int[] arg9) {
		try {
			@Pc(4) int local4 = -arg2 / 2;
			@Pc(9) int local9 = -arg3 / 2;
			@Pc(18) int local18 = (int) (Math.sin((double) arg6 / 326.11D) * 65536.0D);
			@Pc(27) int local27 = (int) (Math.cos((double) arg6 / 326.11D) * 65536.0D);
			@Pc(33) int local33 = local18 * arg7 >> 8;
			@Pc(39) int local39 = local27 * arg7 >> 8;
			@Pc(51) int local51 = (arg4 << 16) + local9 * local33 + local4 * local39;
			@Pc(63) int local63 = (arg5 << 16) + (local9 * local39 - local4 * local33);
			@Pc(69) int local69 = arg0 + arg1 * SoftwareRaster.destinationWidth;
			for (@Pc(71) int local71 = 0; local71 < arg3; local71++) {
				@Pc(78) int local78 = arg8[local71];
				@Pc(82) int local82 = local69 + local78;
				@Pc(88) int local88 = local51 + local39 * local78;
				@Pc(94) int local94 = local63 - local33 * local78;
				for (@Pc(99) int local99 = -arg9[local71]; local99 < 0; local99++) {
					SoftwareRaster.destinationPixels[local82++] = this.pixels[(local88 >> 16) + (local94 >> 16) * this.width];
					local88 += local39;
					local94 -= local33;
				}
				local51 += local33;
				local63 += local39;
				local69 += SoftwareRaster.destinationWidth;
			}
		} catch (@Pc(144) Exception local144) {
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "e", descriptor = "()V")
	public final void method311() {
		if (this.width == this.innerWidth && this.height == this.innerHeight) {
			return;
		}
		@Pc(17) int[] local17 = new int[this.innerWidth * this.innerHeight];
		for (@Pc(19) int local19 = 0; local19 < this.height; local19++) {
			for (@Pc(25) int local25 = 0; local25 < this.width; local25++) {
				local17[(local19 + this.yOffset) * this.innerWidth + local25 + this.xOffset] = this.pixels[local19 * this.width + local25];
			}
		}
		this.pixels = local17;
		this.width = this.innerWidth;
		this.height = this.innerHeight;
		this.xOffset = 0;
		this.yOffset = 0;
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "(II[I[I)V")
	public final void drawImage(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int[] arg2, @OriginalArg(3) int[] arg3) {
		if (Rasterizer.viewportBottom - Rasterizer.viewportTop != arg2.length) {
			throw new IllegalStateException();
		}
		arg0 += this.xOffset;
		arg1 += this.yOffset;
		@Pc(21) int local21 = 0;
		@Pc(24) int local24 = this.height;
		@Pc(27) int local27 = this.width;
		@Pc(31) int local31 = SoftwareRaster.destinationWidth - local27;
		@Pc(33) int local33 = 0;
		@Pc(39) int local39 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(46) int local46;
		if (arg1 < Rasterizer.viewportTop) {
			local46 = Rasterizer.viewportTop - arg1;
			local24 -= local46;
			arg1 = Rasterizer.viewportTop;
			local21 = local46 * local27;
			local39 += local46 * SoftwareRaster.destinationWidth;
		}
		if (arg1 + local24 > Rasterizer.viewportBottom) {
			local24 -= arg1 + local24 - Rasterizer.viewportBottom;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local46 = Rasterizer.viewportLeft - arg0;
			local27 -= local46;
			arg0 = Rasterizer.viewportLeft;
			local21 += local46;
			local39 += local46;
			local33 = local46;
			local31 += local46;
		}
		if (arg0 + local27 > Rasterizer.viewportRight) {
			local46 = arg0 + local27 - Rasterizer.viewportRight;
			local27 -= local46;
			local33 += local46;
			local31 += local46;
		}
		if (local27 <= 0 || local24 <= 0) {
			return;
		}
		local46 = arg0 - Rasterizer.viewportLeft;
		@Pc(142) int local142 = arg1 - Rasterizer.viewportTop;
		for (@Pc(144) int local144 = local142; local144 < local142 + local24; local144++) {
			@Pc(153) int local153 = arg2[local144];
			@Pc(157) int local157 = arg3[local144];
			@Pc(159) int local159 = local27;
			@Pc(166) int local166;
			if (local46 > local153) {
				local166 = local46 - local153;
				if (local166 >= local157) {
					local21 += local27 + local33;
					local39 += local27 + local31;
					continue;
				}
				local157 -= local166;
			} else {
				local166 = local153 - local46;
				if (local166 >= local27) {
					local21 += local27 + local33;
					local39 += local27 + local31;
					continue;
				}
				local21 += local166;
				local159 = local27 - local166;
				local39 += local166;
			}
			local166 = 0;
			if (local159 < local157) {
				local157 = local159;
			} else {
				local166 = local159 - local157;
			}
			for (@Pc(234) int local234 = -local157; local234 < 0; local234++) {
				@Pc(242) int local242 = this.pixels[local21++];
				if (local242 == 0) {
					local39++;
				} else {
					SoftwareRaster.destinationPixels[local39++] = local242;
				}
			}
			local21 += local166 + local33;
			local39 += local166 + local31;
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "b", descriptor = "(IIIIIIII[I[I)V")
	public void method313(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(8) int[] arg7, @OriginalArg(9) int[] arg8) {
		try {
			@Pc(4) int local4 = -arg2 / 2;
			@Pc(9) int local9 = -arg3 / 2;
			@Pc(18) int local18 = (int) (Math.sin((double) arg6 / 326.11D) * 65536.0D);
			@Pc(27) int local27 = (int) (Math.cos((double) arg6 / 326.11D) * 65536.0D);
			@Pc(33) int local33 = local18 * 256 >> 8;
			@Pc(39) int local39 = local27 * 256 >> 8;
			@Pc(51) int local51 = (arg4 << 16) + local9 * local33 + local4 * local39;
			@Pc(63) int local63 = (arg5 << 16) + (local9 * local39 - local4 * local33);
			@Pc(69) int local69 = arg0 + arg1 * SoftwareRaster.destinationWidth;
			for (@Pc(71) int local71 = 0; local71 < arg3; local71++) {
				@Pc(78) int local78 = arg7[local71];
				@Pc(82) int local82 = local69 + local78;
				@Pc(88) int local88 = local51 + local39 * local78;
				@Pc(94) int local94 = local63 - local33 * local78;
				for (@Pc(99) int local99 = -arg8[local71]; local99 < 0; local99++) {
					@Pc(115) int local115 = this.pixels[(local88 >> 16) + (local94 >> 16) * this.width];
					if (local115 == 0) {
						local82++;
					} else {
						SoftwareRaster.destinationPixels[local82++] = local115;
					}
					local88 += local39;
					local94 -= local33;
				}
				local51 += local33;
				local63 += local39;
				local69 += SoftwareRaster.destinationWidth;
			}
		} catch (@Pc(150) Exception local150) {
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "f", descriptor = "(I)V")
	public final void drawShadow(@OriginalArg(0) int arg0) {
		for (@Pc(4) int local4 = this.height - 1; local4 > 0; local4--) {
			@Pc(11) int local11 = local4 * this.width;
			for (@Pc(16) int local16 = this.width - 1; local16 > 0; local16--) {
				if (this.pixels[local16 + local11] == 0 && this.pixels[local16 + local11 - this.width - 1] != 0) {
					this.pixels[local16 + local11] = arg0;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "b", descriptor = "(IIIII)V")
	@Override
	public void method1422(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg2 <= 0 || arg3 <= 0) {
			return;
		}
		@Pc(7) int local7 = this.width;
		@Pc(10) int local10 = this.height;
		@Pc(12) int local12 = 0;
		@Pc(14) int local14 = 0;
		@Pc(17) int local17 = this.innerWidth;
		@Pc(20) int local20 = this.innerHeight;
		@Pc(26) int local26 = (local17 << 16) / arg2;
		@Pc(32) int local32 = (local20 << 16) / arg3;
		@Pc(46) int local46;
		if (this.xOffset > 0) {
			local46 = ((this.xOffset << 16) + local26 - 1) / local26;
			arg0 += local46;
			local12 = local46 * local26 - (this.xOffset << 16);
		}
		if (this.yOffset > 0) {
			local46 = ((this.yOffset << 16) + local32 - 1) / local32;
			arg1 += local46;
			local14 = local46 * local32 - (this.yOffset << 16);
		}
		if (local7 < local17) {
			arg2 = ((local7 << 16) + local26 - local12 - 1) / local26;
		}
		if (local10 < local20) {
			arg3 = ((local10 << 16) + local32 - local14 - 1) / local32;
		}
		local46 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(130) int local130 = SoftwareRaster.destinationWidth - arg2;
		if (arg1 + arg3 > Rasterizer.viewportBottom) {
			arg3 -= arg1 + arg3 - Rasterizer.viewportBottom;
		}
		@Pc(150) int local150;
		if (arg1 < Rasterizer.viewportTop) {
			local150 = Rasterizer.viewportTop - arg1;
			arg3 -= local150;
			local46 += local150 * SoftwareRaster.destinationWidth;
			local14 += local32 * local150;
		}
		if (arg0 + arg2 > Rasterizer.viewportRight) {
			local150 = arg0 + arg2 - Rasterizer.viewportRight;
			arg2 -= local150;
			local130 += local150;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local150 = Rasterizer.viewportLeft - arg0;
			arg2 -= local150;
			local46 += local150;
			local12 += local26 * local150;
			local130 += local150;
		}
		Static290.method307(SoftwareRaster.destinationPixels, this.pixels, local12, local14, local46, local130, arg2, arg3, local26, local32, local7, arg4);
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "b", descriptor = "(III)V")
	public final void method315(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		for (@Pc(1) int local1 = 0; local1 < this.pixels.length; local1++) {
			@Pc(11) int local11 = this.pixels[local1];
			if (local11 != 0) {
				@Pc(19) int local19 = local11 >> 16 & 0xFF;
				local19 += arg0;
				if (local19 < 1) {
					local19 = 1;
				} else if (local19 > 255) {
					local19 = 255;
				}
				@Pc(40) int local40 = local11 >> 8 & 0xFF;
				local40 += arg1;
				if (local40 < 1) {
					local40 = 1;
				} else if (local40 > 255) {
					local40 = 255;
				}
				@Pc(59) int local59 = local11 & 0xFF;
				local59 += arg2;
				if (local59 < 1) {
					local59 = 1;
				} else if (local59 > 255) {
					local59 = 255;
				}
				this.pixels[local1] = (local19 << 16) + (local40 << 8) + local59;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!mm", name = "a", descriptor = "(IIII)V")
	@Override
	public void renderResized(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (arg2 <= 0 || arg3 <= 0) {
			return;
		}
		@Pc(7) int local7 = this.width;
		@Pc(10) int local10 = this.height;
		@Pc(12) int local12 = 0;
		@Pc(14) int local14 = 0;
		@Pc(17) int local17 = this.innerWidth;
		@Pc(20) int local20 = this.innerHeight;
		@Pc(26) int local26 = (local17 << 16) / arg2;
		@Pc(32) int local32 = (local20 << 16) / arg3;
		@Pc(46) int local46;
		if (this.xOffset > 0) {
			local46 = ((this.xOffset << 16) + local26 - 1) / local26;
			arg0 += local46;
			local12 = local46 * local26 - (this.xOffset << 16);
		}
		if (this.yOffset > 0) {
			local46 = ((this.yOffset << 16) + local32 - 1) / local32;
			arg1 += local46;
			local14 = local46 * local32 - (this.yOffset << 16);
		}
		if (local7 < local17) {
			arg2 = ((local7 << 16) + local26 - local12 - 1) / local26;
		}
		if (local10 < local20) {
			arg3 = ((local10 << 16) + local32 - local14 - 1) / local32;
		}
		local46 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		@Pc(130) int local130 = SoftwareRaster.destinationWidth - arg2;
		if (arg1 + arg3 > Rasterizer.viewportBottom) {
			arg3 -= arg1 + arg3 - Rasterizer.viewportBottom;
		}
		@Pc(150) int local150;
		if (arg1 < Rasterizer.viewportTop) {
			local150 = Rasterizer.viewportTop - arg1;
			arg3 -= local150;
			local46 += local150 * SoftwareRaster.destinationWidth;
			local14 += local32 * local150;
		}
		if (arg0 + arg2 > Rasterizer.viewportRight) {
			local150 = arg0 + arg2 - Rasterizer.viewportRight;
			arg2 -= local150;
			local130 += local150;
		}
		if (arg0 < Rasterizer.viewportLeft) {
			local150 = Rasterizer.viewportLeft - arg0;
			arg2 -= local150;
			local46 += local150;
			local12 += local26 * local150;
			local130 += local150;
		}
		Static290.method316(SoftwareRaster.destinationPixels, this.pixels, local12, local14, local46, local130, arg2, arg3, local26, local32, local7);
	}
}
