package com.jagex.runetek4.media;

import com.jagex.runetek4.*;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Rasterizer {

    @OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "[I")
    public static final int[] palette = new int[65536];
    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "I")
	public static int destinationHeight;

	@OriginalMember(owner = "runetek4.client!kb", name = "f", descriptor = "[I")
	public static int[] anIntArray295;

	@OriginalMember(owner = "runetek4.client!kb", name = "g", descriptor = "[I")
	public static int[] anIntArray296;

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "I")
	public static int viewportLeft = 0;

	@OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "I")
	public static int viewportTop = 0;

	@OriginalMember(owner = "runetek4.client!kb", name = "e", descriptor = "I")
	public static int viewportRight = 0;

	@OriginalMember(owner = "runetek4.client!kb", name = "h", descriptor = "I")
	public static int viewportBottom = 0;
	@OriginalMember(owner = "runetek4.client!hf", name = "e", descriptor = "Lclient!m;")
	public static GlTextureProvider textureProvider;
	@OriginalMember(owner = "runetek4.client!hf", name = "k", descriptor = "I")
	public static int anInt2470;
	@OriginalMember(owner = "runetek4.client!hf", name = "c", descriptor = "[I")
	public static int[] anIntArray221 = new int[1024];
	@OriginalMember(owner = "runetek4.client!hf", name = "d", descriptor = "Z")
	public static boolean textureHasTransparency = false;
	@OriginalMember(owner = "runetek4.client!tg", name = "c", descriptor = "I")
	public static int screenLowerX;
	@OriginalMember(owner = "runetek4.client!ub", name = "m", descriptor = "I")
	public static int screenUpperX;
	@OriginalMember(owner = "runetek4.client!a", name = "g", descriptor = "I")
	public static int screenLowerY;
	@OriginalMember(owner = "runetek4.client!li", name = "x", descriptor = "I")
	public static int screenUpperY;

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "()V")
	public static void method2482() {
		anIntArray295 = null;
		anIntArray296 = null;
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III)V")
	private static void drawPixel(@OriginalArg(0) int x, @OriginalArg(1) int y) {
		if (x >= viewportLeft && y >= viewportTop && x < viewportRight && y < viewportBottom) {
			SoftwareRaster.destinationPixels[x + y * SoftwareRaster.destinationWidth] = 16776960;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([I[I)V")
	public static void method2486(@OriginalArg(0) int[] arg0, @OriginalArg(1) int[] arg1) {
		if (arg0.length != viewportBottom - viewportTop || arg1.length != viewportBottom - viewportTop) {
			throw new IllegalArgumentException();
		}
		anIntArray295 = arg0;
		anIntArray296 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([I)V")
	public static void setViewportDimensions(@OriginalArg(0) int[] arg0) {
		viewportLeft = arg0[0];
		viewportTop = arg0[1];
		viewportRight = arg0[2];
		viewportBottom = arg0[3];
		method2482();
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "(IIII)V")
	public static void drawVerticalLine(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int color) {
		if (x < viewportLeft || x >= viewportRight) {
			return;
		}
		if (y < viewportTop) {
			length -= viewportTop - y;
			y = viewportTop;
		}
		if (y + length > viewportBottom) {
			length = viewportBottom - y;
		}
		@Pc(32) int pixelOffset = x + y * SoftwareRaster.destinationWidth;
		for (@Pc(34) int pixel = 0; pixel < length; pixel++) {
			SoftwareRaster.destinationPixels[pixelOffset + pixel * SoftwareRaster.destinationWidth] = color;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "()V")
	public static void clear() {
		@Pc(1) int local1 = 0;
		@Pc(7) int local7 = SoftwareRaster.destinationWidth * destinationHeight - 7;
		while (local1 < local7) {
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
			SoftwareRaster.destinationPixels[local1++] = 0;
		}
		local7 += 7;
		while (local1 < local7) {
			SoftwareRaster.destinationPixels[local1++] = 0;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "([I)V")
	public static void getViewportDimensions(@OriginalArg(0) int[] arg0) {
		arg0[0] = viewportLeft;
		arg0[1] = viewportTop;
		arg0[2] = viewportRight;
		arg0[3] = viewportBottom;
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "e", descriptor = "(IIIII)V")
	public static void drawDiagonalLine(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int destX, @OriginalArg(3) int destY, @OriginalArg(4) int color) {
		destX -= x;
		destY -= y;
		if (destY == 0) {
			if (destX >= 0) {
				SoftwareRaster.drawHorizontalLine(x, y, destX + 1, color);
			} else {
				SoftwareRaster.drawHorizontalLine(x + destX, y, 1 - destX, color);
			}
		} else if (destX != 0) {
			if (destX + destY < 0) {
				x += destX;
				destX = -destX;
				y += destY;
				destY = -destY;
			}
			@Pc(96) int i;
			@Pc(127) int local127;
			if (destX > destY) {
				y <<= 0x10;
				y += 32768;
				@Pc(86) int destY2 = destY << 16;
				i = (int) Math.floor((double) destY2 / (double) destX + 0.5D);
				destX += x;
				if (x < viewportLeft) {
					y += i * (viewportLeft - x);
					x = viewportLeft;
				}
				if (destX >= viewportRight) {
					destX = viewportRight - 1;
				}
				while (x <= destX) {
					local127 = y >> 16;
					if (local127 >= viewportTop && local127 < viewportBottom) {
						SoftwareRaster.destinationPixels[x + local127 * SoftwareRaster.destinationWidth] = color;
					}
					y += i;
					x++;
				}
			} else {
				x <<= 0x10;
				x += 32768;
				@Pc(160) int j = destX << 16;
				i = (int) Math.floor((double) j / (double) destY + 0.5D);
				destY += y;
				if (y < viewportTop) {
					x += i * (viewportTop - y);
					y = viewportTop;
				}
				if (destY >= viewportBottom) {
					destY = viewportBottom - 1;
				}
				while (y <= destY) {
					local127 = x >> 16;
					if (local127 >= viewportLeft && local127 < viewportRight) {
						SoftwareRaster.destinationPixels[local127 + y * SoftwareRaster.destinationWidth] = color;
					}
					x += i;
					y++;
				}
			}
		} else if (destY >= 0) {
			drawVerticalLine(x, y, destY + 1, color);
		} else {
			drawVerticalLine(x, y + destY, -destY + 1, color);
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "e", descriptor = "(IIII)V")
	private static void drawCircle(@OriginalArg(0) int arg0, @OriginalArg(1) int y, @OriginalArg(2) int radius) {
		if (radius == 0) {
			drawPixel(arg0, y);
			return;
		}
		if (radius < 0) {
			radius = -radius;
		}
		@Pc(15) int local15 = y - radius;
		if (local15 < viewportTop) {
			local15 = viewportTop;
		}
		@Pc(26) int local26 = y + radius + 1;
		if (local26 > viewportBottom) {
			local26 = viewportBottom;
		}
		@Pc(33) int local33 = local15;
		@Pc(37) int local37 = radius * radius;
		@Pc(39) int local39 = 0;
		@Pc(43) int local43 = y - local15;
		@Pc(47) int local47 = local43 * local43;
		@Pc(51) int local51 = local47 - local43;
		if (y > local26) {
			y = local26;
		}
		@Pc(85) int local85;
		@Pc(94) int local94;
		@Pc(105) int local105;
		@Pc(107) int local107;
		while (local33 < y) {
			while (local51 <= local37 || local47 <= local37) {
				local47 += local39 + local39;
				local51 += local39++ + local39;
			}
			local85 = arg0 + 1 - local39;
			if (local85 < viewportLeft) {
				local85 = viewportLeft;
			}
			local94 = arg0 + local39;
			if (local94 > viewportRight) {
				local94 = viewportRight;
			}
			local105 = local85 + local33 * SoftwareRaster.destinationWidth;
			for (local107 = local85; local107 < local94; local107++) {
				SoftwareRaster.destinationPixels[local105++] = 16776960;
			}
			local33++;
			local47 -= local43-- + local43;
			local51 -= local43 + local43;
		}
		local39 = radius;
		local43 = local33 - y;
		local51 = local43 * local43 + local37;
		local47 = local51 - radius;
		local51 -= local43;
		while (local33 < local26) {
			while (local51 > local37 && local47 > local37) {
				local51 -= local39-- + local39;
				local47 -= local39 + local39;
			}
			local85 = arg0 - local39;
			if (local85 < viewportLeft) {
				local85 = viewportLeft;
			}
			local94 = arg0 + local39;
			if (local94 > viewportRight - 1) {
				local94 = viewportRight - 1;
			}
			local105 = local85 + local33 * SoftwareRaster.destinationWidth;
			for (local107 = local85; local107 <= local94; local107++) {
				SoftwareRaster.destinationPixels[local105++] = 16776960;
			}
			local33++;
			local51 += local43 + local43;
			local47 += local43++ + local43;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "f", descriptor = "(IIIII)V")
	public static void drawCircleAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int arg2, @OriginalArg(4) int alpha) {
		if (alpha == 0) {
			return;
		}
		if (alpha == 256) {
			drawCircle(x, y, arg2);
			return;
		}
		@Pc(20) int a = 256 - alpha;
		@Pc(28) int local28 = alpha * 255;
		@Pc(36) int local36 = alpha * 255;
		@Pc(42) int local42 = alpha * 0;
		@Pc(46) int topY = y - arg2;
		if (topY < viewportTop) {
			topY = viewportTop;
		}
		@Pc(57) int bottomY = y + arg2 + 1;
		if (bottomY > viewportBottom) {
			bottomY = viewportBottom;
		}
		@Pc(64) int local64 = topY;
		@Pc(68) int local68 = arg2 * arg2;
		@Pc(70) int local70 = 0;
		@Pc(74) int local74 = y - topY;
		@Pc(78) int local78 = local74 * local74;
		@Pc(82) int local82 = local78 - local74;
		if (y > bottomY) {
			y = bottomY;
		}
		@Pc(151) int local151;
		@Pc(161) int local161;
		@Pc(169) int local169;
		@Pc(116) int local116;
		@Pc(125) int local125;
		@Pc(136) int local136;
		@Pc(138) int local138;
		@Pc(191) int local191;
		while (local64 < y) {
			while (local82 <= local68 || local78 <= local68) {
				local78 += local70 + local70;
				local82 += local70++ + local70;
			}
			local116 = x + 1 - local70;
			if (local116 < viewportLeft) {
				local116 = viewportLeft;
			}
			local125 = x + local70;
			if (local125 > viewportRight) {
				local125 = viewportRight;
			}
			local136 = local116 + local64 * SoftwareRaster.destinationWidth;
			for (local138 = local116; local138 < local125; local138++) {
				local151 = (SoftwareRaster.destinationPixels[local136] >> 16 & 0xFF) * a;
				local161 = (SoftwareRaster.destinationPixels[local136] >> 8 & 0xFF) * a;
				local169 = (SoftwareRaster.destinationPixels[local136] & 0xFF) * a;
				local191 = (local28 + local151 >> 8 << 16) + (local36 + local161 >> 8 << 8) + (local42 + local169 >> 8);
				SoftwareRaster.destinationPixels[local136++] = local191;
			}
			local64++;
			local78 -= local74-- + local74;
			local82 -= local74 + local74;
		}
		local70 = arg2;
		local74 = -local74;
		local82 = local74 * local74 + local68;
		local78 = local82 - arg2;
		local82 -= local74;
		while (local64 < bottomY) {
			while (local82 > local68 && local78 > local68) {
				local82 -= local70-- + local70;
				local78 -= local70 + local70;
			}
			local116 = x - local70;
			if (local116 < viewportLeft) {
				local116 = viewportLeft;
			}
			local125 = x + local70;
			if (local125 > viewportRight - 1) {
				local125 = viewportRight - 1;
			}
			local136 = local116 + local64 * SoftwareRaster.destinationWidth;
			for (local138 = local116; local138 <= local125; local138++) {
				local151 = (SoftwareRaster.destinationPixels[local136] >> 16 & 0xFF) * a;
				local161 = (SoftwareRaster.destinationPixels[local136] >> 8 & 0xFF) * a;
				local169 = (SoftwareRaster.destinationPixels[local136] & 0xFF) * a;
				local191 = (local28 + local151 >> 8 << 16) + (local36 + local161 >> 8 << 8) + (local42 + local169 >> 8);
				SoftwareRaster.destinationPixels[local136++] = local191;
			}
			local64++;
			local82 += local74 + local74;
			local78 += local74++ + local74;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III[I[I)V")
	public static void method2504(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int[] arg2, @OriginalArg(4) int[] arg3) {
		@Pc(5) int local5 = arg0 + arg1 * SoftwareRaster.destinationWidth;
		for (@Pc(7) int local7 = 0; local7 < arg2.length; local7++) {
			@Pc(17) int local17 = local5 + arg2[local7];
			for (@Pc(22) int local22 = -arg3[local7]; local22 < 0; local22++) {
				SoftwareRaster.destinationPixels[local17++] = 0;
			}
			local5 += SoftwareRaster.destinationWidth;
		}
	}

    @OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(Lclient!m;)V")
    public static void unpackTextures(@OriginalArg(0) GlTextureProvider arg0) {
        textureProvider = arg0;
    }

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "()V")
	public static void setSize() {
		method1925(viewportLeft, viewportTop, viewportRight, viewportBottom);
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(IIII)V")
	private static void method1925(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		Pix3D.anInt2472 = arg2 - arg0;
		anInt2470 = arg3 - arg1;
		prepareOffsets();
		if (anIntArray221.length < anInt2470) {
			anIntArray221 = new int[IntUtils.bitceil(anInt2470)];
		}
		@Pc(23) int local23 = arg1 * SoftwareRaster.destinationWidth + arg0;
		for (@Pc(25) int local25 = 0; local25 < anInt2470; local25++) {
			anIntArray221[local25] = local23;
			local23 += SoftwareRaster.destinationWidth;
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "(II)V")
	public static void setBounds(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) int local3 = anIntArray221[0];
		@Pc(7) int local7 = local3 / SoftwareRaster.destinationWidth;
		@Pc(13) int local13 = local3 - local7 * SoftwareRaster.destinationWidth;
		Pix3D.anInt2471 = arg0 - local13;
		Pix3D.anInt2469 = arg1 - local7;
		screenLowerX = -Pix3D.anInt2471;
		screenUpperX = Pix3D.anInt2472 - Pix3D.anInt2471;
		screenLowerY = -Pix3D.anInt2469;
		screenUpperY = anInt2470 - Pix3D.anInt2469;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "c", descriptor = "()V")
	public static void prepareOffsets() {
		Pix3D.anInt2471 = Pix3D.anInt2472 / 2;
		Pix3D.anInt2469 = anInt2470 / 2;
		screenLowerX = -Pix3D.anInt2471;
		screenUpperX = Pix3D.anInt2472 - Pix3D.anInt2471;
		screenLowerY = -Pix3D.anInt2469;
		screenUpperY = anInt2470 - Pix3D.anInt2469;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([BIIIIIII)V")
	public static void fillSpriteTriangle(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7) {
		@Pc(1) int local1 = 0;
		if (arg2 != arg1) {
			local1 = (arg5 - arg4 << 16) / (arg2 - arg1);
		}
		@Pc(16) int local16 = 0;
		if (arg3 != arg2) {
			local16 = (arg6 - arg5 << 16) / (arg3 - arg2);
		}
		@Pc(31) int local31 = 0;
		if (arg3 != arg1) {
			local31 = (arg4 - arg6 << 16) / (arg1 - arg3);
		}
		if (arg1 <= arg2 && arg1 <= arg3) {
			if (arg2 < arg3) {
				arg6 = arg4 <<= 0x10;
				if (arg1 < 0) {
					arg6 -= local31 * arg1;
					arg4 -= local1 * arg1;
					arg1 = 0;
				}
				arg5 <<= 0x10;
				if (arg2 < 0) {
					arg5 -= local16 * arg2;
					arg2 = 0;
				}
				if ((arg1 == arg2 || local31 >= local1) && (arg1 != arg2 || local31 <= local16)) {
					arg3 -= arg2;
					arg2 -= arg1;
					arg1 *= arg7;
					while (true) {
						arg2--;
						if (arg2 < 0) {
							while (true) {
								arg3--;
								if (arg3 < 0) {
									return;
								}
								method1930(arg0, arg1, arg5 >> 16, arg6 >> 16);
								arg6 += local31;
								arg5 += local16;
								arg1 += arg7;
							}
						}
						method1930(arg0, arg1, arg4 >> 16, arg6 >> 16);
						arg6 += local31;
						arg4 += local1;
						arg1 += arg7;
					}
				} else {
					arg3 -= arg2;
					arg2 -= arg1;
					arg1 *= arg7;
					while (true) {
						arg2--;
						if (arg2 < 0) {
							while (true) {
								arg3--;
								if (arg3 < 0) {
									return;
								}
								method1930(arg0, arg1, arg6 >> 16, arg5 >> 16);
								arg6 += local31;
								arg5 += local16;
								arg1 += arg7;
							}
						}
						method1930(arg0, arg1, arg6 >> 16, arg4 >> 16);
						arg6 += local31;
						arg4 += local1;
						arg1 += arg7;
					}
				}
			} else {
				arg5 = arg4 <<= 0x10;
				if (arg1 < 0) {
					arg5 -= local31 * arg1;
					arg4 -= local1 * arg1;
					arg1 = 0;
				}
				arg6 <<= 0x10;
				if (arg3 < 0) {
					arg6 -= local16 * arg3;
					arg3 = 0;
				}
				if ((arg1 == arg3 || local31 >= local1) && (arg1 != arg3 || local16 <= local1)) {
					arg2 -= arg3;
					arg3 -= arg1;
					arg1 *= arg7;
					while (true) {
						arg3--;
						if (arg3 < 0) {
							while (true) {
								arg2--;
								if (arg2 < 0) {
									return;
								}
								method1930(arg0, arg1, arg4 >> 16, arg6 >> 16);
								arg6 += local16;
								arg4 += local1;
								arg1 += arg7;
							}
						}
						method1930(arg0, arg1, arg4 >> 16, arg5 >> 16);
						arg5 += local31;
						arg4 += local1;
						arg1 += arg7;
					}
				} else {
					arg2 -= arg3;
					arg3 -= arg1;
					arg1 *= arg7;
					while (true) {
						arg3--;
						if (arg3 < 0) {
							while (true) {
								arg2--;
								if (arg2 < 0) {
									return;
								}
								method1930(arg0, arg1, arg6 >> 16, arg4 >> 16);
								arg6 += local16;
								arg4 += local1;
								arg1 += arg7;
							}
						}
						method1930(arg0, arg1, arg5 >> 16, arg4 >> 16);
						arg5 += local31;
						arg4 += local1;
						arg1 += arg7;
					}
				}
			}
		} else if (arg2 <= arg3) {
			if (arg3 < arg1) {
				arg4 = arg5 <<= 0x10;
				if (arg2 < 0) {
					arg4 -= local1 * arg2;
					arg5 -= local16 * arg2;
					arg2 = 0;
				}
				arg6 <<= 0x10;
				if (arg3 < 0) {
					arg6 -= local31 * arg3;
					arg3 = 0;
				}
				if (arg2 != arg3 && local1 < local16 || arg2 == arg3 && local1 > local31) {
					arg1 -= arg3;
					arg3 -= arg2;
					arg2 *= arg7;
					while (true) {
						arg3--;
						if (arg3 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								method1930(arg0, arg2, arg4 >> 16, arg6 >> 16);
								arg4 += local1;
								arg6 += local31;
								arg2 += arg7;
							}
						}
						method1930(arg0, arg2, arg4 >> 16, arg5 >> 16);
						arg4 += local1;
						arg5 += local16;
						arg2 += arg7;
					}
				} else {
					arg1 -= arg3;
					arg3 -= arg2;
					arg2 *= arg7;
					while (true) {
						arg3--;
						if (arg3 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								method1930(arg0, arg2, arg6 >> 16, arg4 >> 16);
								arg4 += local1;
								arg6 += local31;
								arg2 += arg7;
							}
						}
						method1930(arg0, arg2, arg5 >> 16, arg4 >> 16);
						arg4 += local1;
						arg5 += local16;
						arg2 += arg7;
					}
				}
			} else {
				arg6 = arg5 <<= 0x10;
				if (arg2 < 0) {
					arg6 -= local1 * arg2;
					arg5 -= local16 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				if (arg1 < 0) {
					arg4 -= local31 * arg1;
					arg1 = 0;
				}
				if (local1 < local16) {
					arg3 -= arg1;
					arg1 -= arg2;
					arg2 *= arg7;
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg3--;
								if (arg3 < 0) {
									return;
								}
								method1930(arg0, arg2, arg4 >> 16, arg5 >> 16);
								arg4 += local31;
								arg5 += local16;
								arg2 += arg7;
							}
						}
						method1930(arg0, arg2, arg6 >> 16, arg5 >> 16);
						arg6 += local1;
						arg5 += local16;
						arg2 += arg7;
					}
				} else {
					arg3 -= arg1;
					arg1 -= arg2;
					arg2 *= arg7;
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg3--;
								if (arg3 < 0) {
									return;
								}
								method1930(arg0, arg2, arg5 >> 16, arg4 >> 16);
								arg4 += local31;
								arg5 += local16;
								arg2 += arg7;
							}
						}
						method1930(arg0, arg2, arg5 >> 16, arg6 >> 16);
						arg6 += local1;
						arg5 += local16;
						arg2 += arg7;
					}
				}
			}
		} else if (arg1 < arg2) {
			arg5 = arg6 <<= 0x10;
			if (arg3 < 0) {
				arg5 -= local16 * arg3;
				arg6 -= local31 * arg3;
				arg3 = 0;
			}
			arg4 <<= 0x10;
			if (arg1 < 0) {
				arg4 -= local1 * arg1;
				arg1 = 0;
			}
			if (local16 < local31) {
				arg2 -= arg1;
				arg1 -= arg3;
				arg3 *= arg7;
				while (true) {
					arg1--;
					if (arg1 < 0) {
						while (true) {
							arg2--;
							if (arg2 < 0) {
								return;
							}
							method1930(arg0, arg3, arg5 >> 16, arg4 >> 16);
							arg5 += local16;
							arg4 += local1;
							arg3 += arg7;
						}
					}
					method1930(arg0, arg3, arg5 >> 16, arg6 >> 16);
					arg5 += local16;
					arg6 += local31;
					arg3 += arg7;
				}
			} else {
				arg2 -= arg1;
				arg1 -= arg3;
				arg3 *= arg7;
				while (true) {
					arg1--;
					if (arg1 < 0) {
						while (true) {
							arg2--;
							if (arg2 < 0) {
								return;
							}
							method1930(arg0, arg3, arg4 >> 16, arg5 >> 16);
							arg5 += local16;
							arg4 += local1;
							arg3 += arg7;
						}
					}
					method1930(arg0, arg3, arg6 >> 16, arg5 >> 16);
					arg5 += local16;
					arg6 += local31;
					arg3 += arg7;
				}
			}
		} else {
			arg4 = arg6 <<= 0x10;
			if (arg3 < 0) {
				arg4 -= local16 * arg3;
				arg6 -= local31 * arg3;
				arg3 = 0;
			}
			arg5 <<= 0x10;
			if (arg2 < 0) {
				arg5 -= local1 * arg2;
				arg2 = 0;
			}
			if (local16 < local31) {
				arg1 -= arg2;
				arg2 -= arg3;
				arg3 *= arg7;
				while (true) {
					arg2--;
					if (arg2 < 0) {
						while (true) {
							arg1--;
							if (arg1 < 0) {
								return;
							}
							method1930(arg0, arg3, arg5 >> 16, arg6 >> 16);
							arg5 += local1;
							arg6 += local31;
							arg3 += arg7;
						}
					}
					method1930(arg0, arg3, arg4 >> 16, arg6 >> 16);
					arg4 += local16;
					arg6 += local31;
					arg3 += arg7;
				}
			} else {
				arg1 -= arg2;
				arg2 -= arg3;
				arg3 *= arg7;
				while (true) {
					arg2--;
					if (arg2 < 0) {
						while (true) {
							arg1--;
							if (arg1 < 0) {
								return;
							}
							method1930(arg0, arg3, arg6 >> 16, arg5 >> 16);
							arg5 += local1;
							arg6 += local31;
							arg3 += arg7;
						}
					}
					method1930(arg0, arg3, arg6 >> 16, arg4 >> 16);
					arg4 += local16;
					arg6 += local31;
					arg3 += arg7;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([BIIII)V")
	private static void method1930(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		if (arg2 >= arg3) {
			return;
		}
		arg1 += arg2;
		@Pc(13) int local13 = arg3 - arg2 >> 2;
		while (true) {
			local13--;
			if (local13 < 0) {
				local13 = arg3 - arg2 & 0x3;
				while (true) {
					local13--;
					if (local13 < 0) {
						return;
					}
					arg0[arg1++] = 1;
				}
			}
			@Pc(19) int local19 = arg1 + 1;
			arg0[arg1] = 1;
			@Pc(24) int local24 = local19 + 1;
			arg0[local19] = 1;
			@Pc(29) int local29 = local24 + 1;
			arg0[local24] = 1;
			arg1 = local29 + 1;
			arg0[local29] = 1;
		}
	}
}
