package com.jagex.runetek4.graphics.raster;

import com.jagex.runetek4.graphics.core.FrameBuffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SoftwareRaster {

    @OriginalMember(owner = "runetek4.client!vd", name = "w", descriptor = "Lclient!vk;")
    public static FrameBuffer frameBuffer;

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "I")
    public static int width;

    @OriginalMember(owner = "runetek4.client!kb", name = "i", descriptor = "[I")
    public static int[] pixels;

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "I")
	public static int height;

    @OriginalMember(owner = "runetek4.client!kb", name = "f", descriptor = "[I")
    public static int[] anIntArray295;

    @OriginalMember(owner = "runetek4.client!kb", name = "g", descriptor = "[I")
    public static int[] anIntArray296;

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "I")
    public static int clipLeft = 0;

    @OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "I")
    public static int clipTop = 0;

    @OriginalMember(owner = "runetek4.client!kb", name = "e", descriptor = "I")
    public static int clipRight = 0;

    @OriginalMember(owner = "runetek4.client!kb", name = "h", descriptor = "I")
    public static int clipBottom = 0;

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIII)V")
    public static void setClip(@OriginalArg(0) int minX, @OriginalArg(1) int minY, @OriginalArg(2) int maxX, @OriginalArg(3) int maxY) {
        if (minX < 0) {
            minX = 0;
        }
        if (minY < 0) {
            minY = 0;
        }
        if (maxX > width) {
            maxX = width;
        }
        if (maxY > height) {
            maxY = height;
        }
        clipLeft = minX;
        clipTop = minY;
        clipRight = maxX;
        clipBottom = maxY;
        method2482();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIIII)V")
    public static void fillRect(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color) {
        if (x < clipLeft) {
            width -= clipLeft - x;
            x = clipLeft;
        }
        if (y < clipTop) {
            height -= clipTop - y;
            y = clipTop;
        }
        if (x + width > clipRight) {
            width = clipRight - x;
        }
        if (y + height > clipBottom) {
            height = clipBottom - y;
        }
        @Pc(43) int pixelOffset = SoftwareRaster.width - width;
        @Pc(49) int pixel = x + y * SoftwareRaster.width;
        for (@Pc(52) int heightCounter = -height; heightCounter < 0; heightCounter++) {
            for (@Pc(57) int widthCounter = -width; widthCounter < 0; widthCounter++) {
                pixels[pixel++] = color;
            }
            pixel += pixelOffset;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIIII)V")
    public static void drawRect(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color) {
        drawHorizontalLine(x, y, width, color);
        drawHorizontalLine(x, y + height - 1, width, color);
        drawVerticalLine(x, y, height, color);
        drawVerticalLine(x + width - 1, y, height, color);
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIIIII)V")
    public static void fillRectAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int arg4, @OriginalArg(5) int alpha) {
        if (x < clipLeft) {
            width -= clipLeft - x;
            x = clipLeft;
        }
        if (y < clipTop) {
            height -= clipTop - y;
            y = clipTop;
        }
        if (x + width > clipRight) {
            width = clipRight - x;
        }
        if (y + height > clipBottom) {
            height = clipBottom - y;
        }
        @Pc(59) int rgba = ((arg4 & 0xFF00FF) * alpha >> 8 & 0xFF00FF) + ((arg4 & 0xFF00) * alpha >> 8 & 0xFF00);
        @Pc(63) int a = 256 - alpha;
        @Pc(67) int widthOffset = SoftwareRaster.width - width;
        @Pc(73) int pixel = x + y * SoftwareRaster.width;
        for (@Pc(75) int heightCounter = 0; heightCounter < height; heightCounter++) {
            for (@Pc(81) int widthCounter = -width; widthCounter < 0; widthCounter++) {
                @Pc(87) int local87 = pixels[pixel];
                @Pc(107) int local107 = ((local87 & 0xFF00FF) * a >> 8 & 0xFF00FF) + ((local87 & 0xFF00) * a >> 8 & 0xFF00);
                pixels[pixel++] = rgba + local107;
            }
            pixel += widthOffset;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "(IIIIII)V")
    public static void drawRectAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color, @OriginalArg(5) int alpha) {
        drawHorizontalLineAlpha(x, y, width, color, alpha);
        drawHorizontalLineAlpha(x, y + height - 1, width, color, alpha);
        if (height >= 3) {
            drawVerticalLineAlpha(x, y + 1, height - 2, color, alpha);
            drawVerticalLineAlpha(x + width - 1, y + 1, height - 2, color, alpha);
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "(IIIII)V")
    private static void drawHorizontalLineAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
        if (y < clipTop || y >= clipBottom) {
            return;
        }
        if (x < clipLeft) {
            length -= clipLeft - x;
            x = clipLeft;
        }
        if (x + length > clipRight) {
            length = clipRight - x;
        }
        @Pc(30) int a = 256 - arg4;
        @Pc(38) int r = (arg3 >> 16 & 0xFF) * arg4;
        @Pc(46) int g = (arg3 >> 8 & 0xFF) * arg4;
        @Pc(52) int b = (arg3 & 0xFF) * arg4;
        @Pc(58) int pixelOffset = x + y * width;
        for (@Pc(60) int lengthCounter = 0; lengthCounter < length; lengthCounter++) {
            @Pc(73) int red = (pixels[pixelOffset] >> 16 & 0xFF) * a;
            @Pc(83) int green = (pixels[pixelOffset] >> 8 & 0xFF) * a;
            @Pc(91) int blue = (pixels[pixelOffset] & 0xFF) * a;
            @Pc(113) int rgba = (r + red >> 8 << 16) + (g + green >> 8 << 8) + (b + blue >> 8);
            pixels[pixelOffset++] = rgba;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "(IIIII)V")
    private static void drawVerticalLineAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int color, @OriginalArg(4) int alpha) {
        if (x < clipLeft || x >= clipRight) {
            return;
        }
        if (y < clipTop) {
            length -= clipTop - y;
            y = clipTop;
        }
        if (y + length > clipBottom) {
            length = clipBottom - y;
        }
        @Pc(30) int a = 256 - alpha;
        @Pc(38) int r = (color >> 16 & 0xFF) * alpha;
        @Pc(46) int g = (color >> 8 & 0xFF) * alpha;
        @Pc(52) int b = (color & 0xFF) * alpha;
        @Pc(58) int pixelOffset = x + y * width;
        for (@Pc(60) int lengthCounter = 0; lengthCounter < length; lengthCounter++) {
            @Pc(73) int red = (pixels[pixelOffset] >> 16 & 0xFF) * a;
            @Pc(83) int green = (pixels[pixelOffset] >> 8 & 0xFF) * a;
            @Pc(91) int blue = (pixels[pixelOffset] & 0xFF) * a;
            @Pc(113) int rgba = (r + red >> 8 << 16) + (g + green >> 8 << 8) + (b + blue >> 8);
            pixels[pixelOffset] = rgba;
            pixelOffset += width;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "(IIII)V")
    public static void method2498(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        if (clipLeft < arg0) {
            clipLeft = arg0;
        }
        if (clipTop < arg1) {
            clipTop = arg1;
        }
        if (clipRight > arg2) {
            clipRight = arg2;
        }
        if (clipBottom > arg3) {
            clipBottom = arg3;
        }
        method2482();
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
        @Pc(85) int local85 = arg0 - Rasterizer.getOffsetRemainder();
        @Pc(89) int local89 = arg1 - Rasterizer.getOffset();
        @Pc(93) int local93 = local85 + local59;
        @Pc(97) int local97 = local85 - local67;
        @Pc(103) int local103 = local85 + local3 - local67;
        @Pc(109) int local109 = local85 + local3 + local59;
        @Pc(113) int local113 = local89 + local73;
        @Pc(117) int local117 = local89 - local81;
        @Pc(123) int local123 = local89 + local7 - local81;
        @Pc(129) int local129 = local89 + local7 + local73;
        Rasterizer.testPoints(local93, local97, local103);
        Rasterizer.fillTriangle(local113, local117, local123, local93, local97, local103, arg4);
        Rasterizer.testPoints(local93, local103, local109);
        Rasterizer.fillTriangle(local113, local123, local129, local93, local103, local109, arg4);
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIII)V")
    public static void drawHorizontalLine(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        if (arg1 < clipTop || arg1 >= clipBottom) {
            return;
        }
        if (arg0 < clipLeft) {
            arg2 -= clipLeft - arg0;
            arg0 = clipLeft;
        }
        if (arg0 + arg2 > clipRight) {
            arg2 = clipRight - arg0;
        }
        @Pc(32) int local32 = arg0 + arg1 * width;
        for (@Pc(34) int local34 = 0; local34 < arg2; local34++) {
            pixels[local32 + local34] = arg3;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([III)V")
    public static void setSize(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        pixels = arg0;
        width = arg1;
        height = arg2;
        setClip(0, 0, arg1, arg2);
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "()V")
    public static void resetBounds() {
        clipLeft = 0;
        clipTop = 0;
        clipRight = width;
        clipBottom = height;
        method2482();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "([I)V")
    public static void saveClip(@OriginalArg(0) int[] arg0) {
        arg0[0] = clipLeft;
        arg0[1] = clipTop;
        arg0[2] = clipRight;
        arg0[3] = clipBottom;
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([I)V")
    public static void restoreClip(@OriginalArg(0) int[] arg0) {
        clipLeft = arg0[0];
        clipTop = arg0[1];
        clipRight = arg0[2];
        clipBottom = arg0[3];
        method2482();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "()V")
    public static void method2482() {
        anIntArray295 = null;
        anIntArray296 = null;
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "(IIII)V")
    public static void drawVerticalLine(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int color) {
        if (x < clipLeft || x >= clipRight) {
            return;
        }
        if (y < clipTop) {
            length -= clipTop - y;
            y = clipTop;
        }
        if (y + length > clipBottom) {
            length = clipBottom - y;
        }
        @Pc(32) int pixelOffset = x + y * width;
        for (@Pc(34) int pixel = 0; pixel < length; pixel++) {
            pixels[pixelOffset + pixel * width] = color;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III)V")
    public static void drawPixel(@OriginalArg(0) int x, @OriginalArg(1) int y) {
        if (x >= clipLeft && y >= clipTop && x < clipRight && y < clipBottom) {
            pixels[x + y * width] = 16776960;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([I[I)V")
    public static void method2486(@OriginalArg(0) int[] arg0, @OriginalArg(1) int[] arg1) {
        if (arg0.length != clipBottom - clipTop || arg1.length != clipBottom - clipTop) {
            throw new IllegalArgumentException();
        }
        anIntArray295 = arg0;
        anIntArray296 = arg1;
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "()V")
    public static void clear() {
        @Pc(1) int local1 = 0;
        @Pc(7) int local7 = width * height - 7;
        while (local1 < local7) {
            pixels[local1++] = 0;
            pixels[local1++] = 0;
            pixels[local1++] = 0;
            pixels[local1++] = 0;
            pixels[local1++] = 0;
            pixels[local1++] = 0;
            pixels[local1++] = 0;
            pixels[local1++] = 0;
        }
        local7 += 7;
        while (local1 < local7) {
            pixels[local1++] = 0;
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
                if (x < clipLeft) {
                    y += i * (clipLeft - x);
                    x = clipLeft;
                }
                if (destX >= clipRight) {
                    destX = clipRight - 1;
                }
                while (x <= destX) {
                    local127 = y >> 16;
                    if (local127 >= clipTop && local127 < clipBottom) {
                        pixels[x + local127 * width] = color;
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
                if (y < clipTop) {
                    x += i * (clipTop - y);
                    y = clipTop;
                }
                if (destY >= clipBottom) {
                    destY = clipBottom - 1;
                }
                while (y <= destY) {
                    local127 = x >> 16;
                    if (local127 >= clipLeft && local127 < clipRight) {
                        pixels[local127 + y * width] = color;
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
    public static void drawCircle(@OriginalArg(0) int arg0, @OriginalArg(1) int y, @OriginalArg(2) int radius) {
        if (radius == 0) {
            drawPixel(arg0, y);
            return;
        }
        if (radius < 0) {
            radius = -radius;
        }
        @Pc(15) int local15 = y - radius;
        if (local15 < clipTop) {
            local15 = clipTop;
        }
        @Pc(26) int local26 = y + radius + 1;
        if (local26 > clipBottom) {
            local26 = clipBottom;
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
            if (local85 < clipLeft) {
                local85 = clipLeft;
            }
            local94 = arg0 + local39;
            if (local94 > clipRight) {
                local94 = clipRight;
            }
            local105 = local85 + local33 * width;
            for (local107 = local85; local107 < local94; local107++) {
                pixels[local105++] = 16776960;
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
            if (local85 < clipLeft) {
                local85 = clipLeft;
            }
            local94 = arg0 + local39;
            if (local94 > clipRight - 1) {
                local94 = clipRight - 1;
            }
            local105 = local85 + local33 * width;
            for (local107 = local85; local107 <= local94; local107++) {
                pixels[local105++] = 16776960;
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
        if (topY < clipTop) {
            topY = clipTop;
        }
        @Pc(57) int bottomY = y + arg2 + 1;
        if (bottomY > clipBottom) {
            bottomY = clipBottom;
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
            if (local116 < clipLeft) {
                local116 = clipLeft;
            }
            local125 = x + local70;
            if (local125 > clipRight) {
                local125 = clipRight;
            }
            local136 = local116 + local64 * width;
            for (local138 = local116; local138 < local125; local138++) {
                local151 = (pixels[local136] >> 16 & 0xFF) * a;
                local161 = (pixels[local136] >> 8 & 0xFF) * a;
                local169 = (pixels[local136] & 0xFF) * a;
                local191 = (local28 + local151 >> 8 << 16) + (local36 + local161 >> 8 << 8) + (local42 + local169 >> 8);
                pixels[local136++] = local191;
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
            if (local116 < clipLeft) {
                local116 = clipLeft;
            }
            local125 = x + local70;
            if (local125 > clipRight - 1) {
                local125 = clipRight - 1;
            }
            local136 = local116 + local64 * width;
            for (local138 = local116; local138 <= local125; local138++) {
                local151 = (pixels[local136] >> 16 & 0xFF) * a;
                local161 = (pixels[local136] >> 8 & 0xFF) * a;
                local169 = (pixels[local136] & 0xFF) * a;
                local191 = (local28 + local151 >> 8 << 16) + (local36 + local161 >> 8 << 8) + (local42 + local169 >> 8);
                pixels[local136++] = local191;
            }
            local64++;
            local82 += local74 + local74;
            local78 += local74++ + local74;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III[I[I)V")
    public static void method2504(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int[] arg2, @OriginalArg(4) int[] arg3) {
        @Pc(5) int local5 = arg0 + arg1 * width;
        for (@Pc(7) int local7 = 0; local7 < arg2.length; local7++) {
            @Pc(17) int local17 = local5 + arg2[local7];
            for (@Pc(22) int local22 = -arg3[local7]; local22 < 0; local22++) {
                pixels[local17++] = 0;
            }
            local5 += width;
        }
    }
}
