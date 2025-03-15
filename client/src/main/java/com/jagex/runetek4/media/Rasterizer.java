package com.jagex.runetek4.media;

import com.jagex.runetek4.Pix3D;
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

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "()V")
	public static void method2482() {
		anIntArray295 = null;
		anIntArray296 = null;
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIIII)V")
	public static void drawUnfilledRectangle(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color) {
		drawHorizontalLine(x, y, width, color);
		drawHorizontalLine(x, y + height - 1, width, color);
		drawVerticalLine(x, y, height, color);
		drawVerticalLine(x + width - 1, y, height, color);
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIIIII)V")
	public static void drawFilledRectangleAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int arg4, @OriginalArg(5) int alpha) {
		if (x < viewportLeft) {
			width -= viewportLeft - x;
			x = viewportLeft;
		}
		if (y < viewportTop) {
			height -= viewportTop - y;
			y = viewportTop;
		}
		if (x + width > viewportRight) {
			width = viewportRight - x;
		}
		if (y + height > viewportBottom) {
			height = viewportBottom - y;
		}
		@Pc(59) int rgba = ((arg4 & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((arg4 & 0xFF00) * alpha >> 8 & 0xFF00);
		@Pc(63) int a = 256 - alpha;
		@Pc(67) int widthOffset = destinationWidth - width;
		@Pc(73) int pixel = x + y * destinationWidth;
		for (@Pc(75) int heightCounter = 0; heightCounter < height; heightCounter++) {
			for (@Pc(81) int widthCounter = -width; widthCounter < 0; widthCounter++) {
				@Pc(87) int local87 = destinationPixels[pixel];
				@Pc(107) int local107 = ((local87 & 0xFF00FF) * a >> 8 & 0xFF00FF) + ((local87 & 0xFF00) * a >> 8 & 0xFF00);
				destinationPixels[pixel++] = rgba + local107;
			}
			pixel += widthOffset;
		}
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

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "(IIIIII)V")
	public static void drawUnfilledRectangleAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color, @OriginalArg(5) int alpha) {
		drawHorizontalLineAlpha(x, y, width, color, alpha);
		drawHorizontalLineAlpha(x, y + height - 1, width, color, alpha);
		if (height >= 3) {
			drawVerticalLineAlpha(x, y + 1, height - 2, color, alpha);
			drawVerticalLineAlpha(x + width - 1, y + 1, height - 2, color, alpha);
		}
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
		setBounds(0, 0, arg1, arg2);
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "()V")
	public static void method2492() {
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

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "(IIIII)V")
	private static void drawHorizontalLineAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (y < viewportTop || y >= viewportBottom) {
			return;
		}
		if (x < viewportLeft) {
			length -= viewportLeft - x;
			x = viewportLeft;
		}
		if (x + length > viewportRight) {
			length = viewportRight - x;
		}
		@Pc(30) int a = 256 - arg4;
		@Pc(38) int r = (arg3 >> 16 & 0xFF) * arg4;
		@Pc(46) int g = (arg3 >> 8 & 0xFF) * arg4;
		@Pc(52) int b = (arg3 & 0xFF) * arg4;
		@Pc(58) int pixelOffset = x + y * destinationWidth;
		for (@Pc(60) int lengthCounter = 0; lengthCounter < length; lengthCounter++) {
			@Pc(73) int red = (destinationPixels[pixelOffset] >> 16 & 0xFF) * a;
			@Pc(83) int green = (destinationPixels[pixelOffset] >> 8 & 0xFF) * a;
			@Pc(91) int blue = (destinationPixels[pixelOffset] & 0xFF) * a;
			@Pc(113) int rgba = (r + red >> 8 << 16) + (g + green >> 8 << 8) + (b + blue >> 8);
			destinationPixels[pixelOffset++] = rgba;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIIIII)V")
	public static void method2494(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5) {
		@Pc(3) int local3 = arg2 - arg0;
		@Pc(7) int local7 = arg3 - arg1;
		@Pc(14) int local14 = local3 >= 0 ? local3 : -local3;
		@Pc(21) int local21 = local7 >= 0 ? local7 : -local7;
		@Pc(23) int local23 = local14;
		if (local14 < local21) {
			local23 = local21;
		}
		if (local23 == 0) {
			return;
		}
		@Pc(37) int local37 = (local3 << 16) / local23;
		@Pc(43) int local43 = (local7 << 16) / local23;
		if (local43 <= local37) {
			local37 = -local37;
		} else {
			local43 = -local43;
		}
		@Pc(59) int local59 = arg5 * local43 >> 17;
		@Pc(67) int local67 = arg5 * local43 + 1 >> 17;
		@Pc(73) int local73 = arg5 * local37 >> 17;
		@Pc(81) int local81 = arg5 * local37 + 1 >> 17;
		@Pc(85) int local85 = arg0 - Pix3D.method1913();
		@Pc(89) int local89 = arg1 - Pix3D.method1927();
		@Pc(93) int local93 = local85 + local59;
		@Pc(97) int local97 = local85 - local67;
		@Pc(103) int local103 = local85 + local3 - local67;
		@Pc(109) int local109 = local85 + local3 + local59;
		@Pc(113) int local113 = local89 + local73;
		@Pc(117) int local117 = local89 - local81;
		@Pc(123) int local123 = local89 + local7 - local81;
		@Pc(129) int local129 = local89 + local7 + local73;
		Pix3D.method1922(local93, local97, local103);
		Pix3D.method1918(local113, local117, local123, local93, local97, local103, arg4);
		Pix3D.method1922(local93, local103, local109);
		Pix3D.method1918(local113, local123, local129, local93, local103, local109, arg4);
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIIII)V")
	public static void drawFilledRectangle(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color) {
		if (x < viewportLeft) {
			width -= viewportLeft - x;
			x = viewportLeft;
		}
		if (y < viewportTop) {
			height -= viewportTop - y;
			y = viewportTop;
		}
		if (x + width > viewportRight) {
			width = viewportRight - x;
		}
		if (y + height > viewportBottom) {
			height = viewportBottom - y;
		}
		@Pc(43) int pixelOffset = destinationWidth - width;
		@Pc(49) int pixel = x + y * destinationWidth;
		for (@Pc(52) int heightCounter = -height; heightCounter < 0; heightCounter++) {
			for (@Pc(57) int widthCounter = -width; widthCounter < 0; widthCounter++) {
				destinationPixels[pixel++] = color;
			}
			pixel += pixelOffset;
		}
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIII)V")
	public static void setBounds(@OriginalArg(0) int minX, @OriginalArg(1) int minY, @OriginalArg(2) int maxX, @OriginalArg(3) int maxY) {
		if (minX < 0) {
			minX = 0;
		}
		if (minY < 0) {
			minY = 0;
		}
		if (maxX > destinationWidth) {
			maxX = destinationWidth;
		}
		if (maxY > destinationHeight) {
			maxY = destinationHeight;
		}
		viewportLeft = minX;
		viewportTop = minY;
		viewportRight = maxX;
		viewportBottom = maxY;
		method2482();
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "([I)V")
	public static void getViewportDimensions(@OriginalArg(0) int[] arg0) {
		arg0[0] = viewportLeft;
		arg0[1] = viewportTop;
		arg0[2] = viewportRight;
		arg0[3] = viewportBottom;
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "(IIII)V")
	public static void method2498(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (viewportLeft < arg0) {
			viewportLeft = arg0;
		}
		if (viewportTop < arg1) {
			viewportTop = arg1;
		}
		if (viewportRight > arg2) {
			viewportRight = arg2;
		}
		if (viewportBottom > arg3) {
			viewportBottom = arg3;
		}
		method2482();
	}

	@OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "(IIIII)V")
	private static void drawVerticalLineAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int color, @OriginalArg(4) int alpha) {
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
		@Pc(30) int a = 256 - alpha;
		@Pc(38) int r = (color >> 16 & 0xFF) * alpha;
		@Pc(46) int g = (color >> 8 & 0xFF) * alpha;
		@Pc(52) int b = (color & 0xFF) * alpha;
		@Pc(58) int pixelOffset = x + y * destinationWidth;
		for (@Pc(60) int lengthCounter = 0; lengthCounter < length; lengthCounter++) {
			@Pc(73) int red = (destinationPixels[pixelOffset] >> 16 & 0xFF) * a;
			@Pc(83) int green = (destinationPixels[pixelOffset] >> 8 & 0xFF) * a;
			@Pc(91) int blue = (destinationPixels[pixelOffset] & 0xFF) * a;
			@Pc(113) int rgba = (r + red >> 8 << 16) + (g + green >> 8 << 8) + (b + blue >> 8);
			destinationPixels[pixelOffset] = rgba;
			pixelOffset += destinationWidth;
		}
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
}
