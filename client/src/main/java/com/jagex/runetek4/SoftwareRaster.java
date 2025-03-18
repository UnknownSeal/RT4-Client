package com.jagex.runetek4;

import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SoftwareRaster {
    @OriginalMember(owner = "runetek4.client!vd", name = "w", descriptor = "Lclient!vk;")
    public static FrameBuffer frameBuffer;
    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "I")
    public static int destinationWidth;
    @OriginalMember(owner = "runetek4.client!kb", name = "i", descriptor = "[I")
    public static int[] destinationPixels;

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIII)V")
    public static void setClip(@OriginalArg(0) int minX, @OriginalArg(1) int minY, @OriginalArg(2) int maxX, @OriginalArg(3) int maxY) {
        if (minX < 0) {
            minX = 0;
        }
        if (minY < 0) {
            minY = 0;
        }
        if (maxX > destinationWidth) {
            maxX = destinationWidth;
        }
        if (maxY > Rasterizer.destinationHeight) {
            maxY = Rasterizer.destinationHeight;
        }
        Rasterizer.viewportLeft = minX;
        Rasterizer.viewportTop = minY;
        Rasterizer.viewportRight = maxX;
        Rasterizer.viewportBottom = maxY;
        Rasterizer.method2482();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIIII)V")
    public static void fillRect(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color) {
        if (x < Rasterizer.viewportLeft) {
            width -= Rasterizer.viewportLeft - x;
            x = Rasterizer.viewportLeft;
        }
        if (y < Rasterizer.viewportTop) {
            height -= Rasterizer.viewportTop - y;
            y = Rasterizer.viewportTop;
        }
        if (x + width > Rasterizer.viewportRight) {
            width = Rasterizer.viewportRight - x;
        }
        if (y + height > Rasterizer.viewportBottom) {
            height = Rasterizer.viewportBottom - y;
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

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIIII)V")
    public static void drawRect(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int color) {
        drawHorizontalLine(x, y, width, color);
        drawHorizontalLine(x, y + height - 1, width, color);
        Rasterizer.drawVerticalLine(x, y, height, color);
        Rasterizer.drawVerticalLine(x + width - 1, y, height, color);
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIIIII)V")
    public static void fillRectAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int width, @OriginalArg(3) int height, @OriginalArg(4) int arg4, @OriginalArg(5) int alpha) {
        if (x < Rasterizer.viewportLeft) {
            width -= Rasterizer.viewportLeft - x;
            x = Rasterizer.viewportLeft;
        }
        if (y < Rasterizer.viewportTop) {
            height -= Rasterizer.viewportTop - y;
            y = Rasterizer.viewportTop;
        }
        if (x + width > Rasterizer.viewportRight) {
            width = Rasterizer.viewportRight - x;
        }
        if (y + height > Rasterizer.viewportBottom) {
            height = Rasterizer.viewportBottom - y;
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
        if (y < Rasterizer.viewportTop || y >= Rasterizer.viewportBottom) {
            return;
        }
        if (x < Rasterizer.viewportLeft) {
            length -= Rasterizer.viewportLeft - x;
            x = Rasterizer.viewportLeft;
        }
        if (x + length > Rasterizer.viewportRight) {
            length = Rasterizer.viewportRight - x;
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

    @OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "(IIIII)V")
    private static void drawVerticalLineAlpha(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int color, @OriginalArg(4) int alpha) {
        if (x < Rasterizer.viewportLeft || x >= Rasterizer.viewportRight) {
            return;
        }
        if (y < Rasterizer.viewportTop) {
            length -= Rasterizer.viewportTop - y;
            y = Rasterizer.viewportTop;
        }
        if (y + length > Rasterizer.viewportBottom) {
            length = Rasterizer.viewportBottom - y;
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

    @OriginalMember(owner = "runetek4.client!kb", name = "d", descriptor = "(IIII)V")
    public static void method2498(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        if (Rasterizer.viewportLeft < arg0) {
            Rasterizer.viewportLeft = arg0;
        }
        if (Rasterizer.viewportTop < arg1) {
            Rasterizer.viewportTop = arg1;
        }
        if (Rasterizer.viewportRight > arg2) {
            Rasterizer.viewportRight = arg2;
        }
        if (Rasterizer.viewportBottom > arg3) {
            Rasterizer.viewportBottom = arg3;
        }
        Rasterizer.method2482();
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

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIII)V")
    public static void drawHorizontalLine(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
        if (arg1 < Rasterizer.viewportTop || arg1 >= Rasterizer.viewportBottom) {
            return;
        }
        if (arg0 < Rasterizer.viewportLeft) {
            arg2 -= Rasterizer.viewportLeft - arg0;
            arg0 = Rasterizer.viewportLeft;
        }
        if (arg0 + arg2 > Rasterizer.viewportRight) {
            arg2 = Rasterizer.viewportRight - arg0;
        }
        @Pc(32) int local32 = arg0 + arg1 * destinationWidth;
        for (@Pc(34) int local34 = 0; local34 < arg2; local34++) {
            destinationPixels[local32 + local34] = arg3;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([III)V")
    public static void setSize(@OriginalArg(0) int[] arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
        destinationPixels = arg0;
        destinationWidth = arg1;
        Rasterizer.destinationHeight = arg2;
        setClip(0, 0, arg1, arg2);
    }
}
