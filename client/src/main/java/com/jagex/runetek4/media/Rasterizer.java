package com.jagex.runetek4.media;

import com.jagex.runetek4.*;
import com.jagex.runetek4.util.IntUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Rasterizer {

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "I")
	public static int destinationWidth;

	@OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "I")
	public static int destinationHeight;

	@OriginalMember(owner = "runetek4.client!kb", name = "f", descriptor = "[I")
	public static int[] anIntArray295;

	@OriginalMember(owner = "runetek4.client!kb", name = "g", descriptor = "[I")
	public static int[] anIntArray296;

	@OriginalMember(owner = "runetek4.client!kb", name = "i", descriptor = "[I")
	public static int[] destinationPixels;

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

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "()V")
	public static void method2482() {
		anIntArray295 = null;
		anIntArray296 = null;
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III)V")
	private static void drawPixel(@OriginalArg(0) int x, @OriginalArg(1) int y) {
		if (x >= viewportLeft && y >= viewportTop && x < viewportRight && y < viewportBottom) {
			destinationPixels[x + y * destinationWidth] = 16776960;
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

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIII)V")
	public static void drawHorizontalLine(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (arg1 < viewportTop || arg1 >= viewportBottom) {
			return;
		}
		if (arg0 < viewportLeft) {
			arg2 -= viewportLeft - arg0;
			arg0 = viewportLeft;
		}
		if (arg0 + arg2 > viewportRight) {
			arg2 = viewportRight - arg0;
		}
		@Pc(32) int local32 = arg0 + arg1 * destinationWidth;
		for (@Pc(34) int local34 = 0; local34 < arg2; local34++) {
			destinationPixels[local32 + local34] = arg3;
		}
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
		@Pc(32) int pixelOffset = x + y * destinationWidth;
		for (@Pc(34) int pixel = 0; pixel < length; pixel++) {
			destinationPixels[pixelOffset + pixel * destinationWidth] = color;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([III)V")
	public static void prepare(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		destinationPixels = arg0;
		destinationWidth = arg1;
		destinationHeight = arg2;
		SoftwareRaster.setClip(0, 0, arg1, arg2);
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "()V")
	public static void clear() {
		@Pc(1) int local1 = 0;
		@Pc(7) int local7 = destinationWidth * destinationHeight - 7;
		while (local1 < local7) {
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
			destinationPixels[local1++] = 0;
		}
		local7 += 7;
		while (local1 < local7) {
			destinationPixels[local1++] = 0;
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
				drawHorizontalLine(x, y, destX + 1, color);
			} else {
				drawHorizontalLine(x + destX, y, 1 - destX, color);
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
						destinationPixels[x + local127 * destinationWidth] = color;
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
						destinationPixels[local127 + y * destinationWidth] = color;
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
			local105 = local85 + local33 * destinationWidth;
			for (local107 = local85; local107 < local94; local107++) {
				destinationPixels[local105++] = 16776960;
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
			local105 = local85 + local33 * destinationWidth;
			for (local107 = local85; local107 <= local94; local107++) {
				destinationPixels[local105++] = 16776960;
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
			local136 = local116 + local64 * destinationWidth;
			for (local138 = local116; local138 < local125; local138++) {
				local151 = (destinationPixels[local136] >> 16 & 0xFF) * a;
				local161 = (destinationPixels[local136] >> 8 & 0xFF) * a;
				local169 = (destinationPixels[local136] & 0xFF) * a;
				local191 = (local28 + local151 >> 8 << 16) + (local36 + local161 >> 8 << 8) + (local42 + local169 >> 8);
				destinationPixels[local136++] = local191;
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
			local136 = local116 + local64 * destinationWidth;
			for (local138 = local116; local138 <= local125; local138++) {
				local151 = (destinationPixels[local136] >> 16 & 0xFF) * a;
				local161 = (destinationPixels[local136] >> 8 & 0xFF) * a;
				local169 = (destinationPixels[local136] & 0xFF) * a;
				local191 = (local28 + local151 >> 8 << 16) + (local36 + local161 >> 8 << 8) + (local42 + local169 >> 8);
				destinationPixels[local136++] = local191;
			}
			local64++;
			local82 += local74 + local74;
			local78 += local74++ + local74;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "()V")
	public static void resetBounds() {
		viewportLeft = 0;
		viewportTop = 0;
		viewportRight = destinationWidth;
		viewportBottom = destinationHeight;
		method2482();
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III[I[I)V")
	public static void method2504(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int[] arg2, @OriginalArg(4) int[] arg3) {
		@Pc(5) int local5 = arg0 + arg1 * destinationWidth;
		for (@Pc(7) int local7 = 0; local7 < arg2.length; local7++) {
			@Pc(17) int local17 = local5 + arg2[local7];
			for (@Pc(22) int local22 = -arg3[local7]; local22 < 0; local22++) {
				destinationPixels[local17++] = 0;
			}
			local5 += destinationWidth;
		}
	}

    @OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(Lclient!m;)V")
    public static void unpackTextures(@OriginalArg(0) GlTextureProvider arg0) {
        textureProvider = arg0;
    }

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "()V")
	public static void prepare() {
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
		@Pc(23) int local23 = arg1 * destinationWidth + arg0;
		for (@Pc(25) int local25 = 0; local25 < anInt2470; local25++) {
			anIntArray221[local25] = local23;
			local23 += destinationWidth;
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "(II)V")
	public static void setBounds(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(3) int local3 = anIntArray221[0];
		@Pc(7) int local7 = local3 / destinationWidth;
		@Pc(13) int local13 = local3 - local7 * destinationWidth;
		Pix3D.anInt2471 = arg0 - local13;
		Pix3D.anInt2469 = arg1 - local7;
		Static240.anInt5334 = -Pix3D.anInt2471;
		Static247.anInt5405 = Pix3D.anInt2472 - Pix3D.anInt2471;
		Static1.anInt4 = -Pix3D.anInt2469;
		Static148.anInt3535 = anInt2470 - Pix3D.anInt2469;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "c", descriptor = "()V")
	public static void prepareOffsets() {
		Pix3D.anInt2471 = Pix3D.anInt2472 / 2;
		Pix3D.anInt2469 = anInt2470 / 2;
		Static240.anInt5334 = -Pix3D.anInt2471;
		Static247.anInt5405 = Pix3D.anInt2472 - Pix3D.anInt2471;
		Static1.anInt4 = -Pix3D.anInt2469;
		Static148.anInt3535 = anInt2470 - Pix3D.anInt2469;
	}
}
