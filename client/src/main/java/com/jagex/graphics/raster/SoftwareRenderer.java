package com.jagex.graphics.raster;

import com.jagex.graphics.core.FrameBuffer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public class SoftwareRenderer {

    @OriginalMember(owner = "runetek4.client!vd", name = "w", descriptor = "Lclient!vk;")
    public static FrameBuffer frameBuffer;

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "I")
    public static int width;

    @OriginalMember(owner = "runetek4.client!kb", name = "i", descriptor = "[I")
    public static int[] pixels;

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "I")
	public static int height;

    @OriginalMember(owner = "runetek4.client!kb", name = "f", descriptor = "[I")
    public static int[] clipLeftOffsets;

    @OriginalMember(owner = "runetek4.client!kb", name = "g", descriptor = "[I")
    public static int[] clipLineWidths;

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
        invalidateClippingCache();
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
        @Pc(43) int pixelOffset = SoftwareRenderer.width - width;
        @Pc(49) int pixel = x + y * SoftwareRenderer.width;
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
        @Pc(67) int widthOffset = SoftwareRenderer.width - width;
        @Pc(73) int pixel = x + y * SoftwareRenderer.width;
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
    public static void intersectClipBounds(@OriginalArg(0) int left, @OriginalArg(1) int top, @OriginalArg(2) int right, @OriginalArg(3) int bottom) {
        if (clipLeft < left) {
            clipLeft = left;
        }
        if (clipTop < top) {
            clipTop = top;
        }
        if (clipRight > right) {
            clipRight = right;
        }
        if (clipBottom > bottom) {
            clipBottom = bottom;
        }
        invalidateClippingCache();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "(IIIIII)V")
    public static void drawThickLine(@OriginalArg(0) int x1, @OriginalArg(1) int y1, @OriginalArg(2) int x2, @OriginalArg(3) int y2, @OriginalArg(4) int color, @OriginalArg(5) int thickness) {
        @Pc(3) int deltaX = x2 - x1;
        @Pc(7) int deltaY = y2 - y1;
        @Pc(14) int absX = deltaX >= 0 ? deltaX : -deltaX;
        @Pc(21) int absY = deltaY >= 0 ? deltaY : -deltaY;
        @Pc(23) int maxDistance = absX;
        if (absX < absY) {
            maxDistance = absY;
        }
        if (maxDistance == 0) {
            return;
        }
        @Pc(37) int normalX = (deltaX << 16) / maxDistance;
        @Pc(43) int normalY = (deltaY << 16) / maxDistance;
        if (normalY <= normalX) {
            normalX = -normalX;
        } else {
            normalY = -normalY;
        }
        @Pc(59) int offsetY1 = thickness * normalY >> 17;
        @Pc(67) int offsetY2 = thickness * normalY + 1 >> 17;
        @Pc(73) int offsetX1 = thickness * normalX >> 17;
        @Pc(81) int offsetX2 = thickness * normalX + 1 >> 17;
        @Pc(85) int adjustedX1 = x1 - Rasterizer.getOffsetRemainder();
        @Pc(89) int adjustedY1 = y1 - Rasterizer.getOffset();
        @Pc(93) int quadX1 = adjustedX1 + offsetY1;
        @Pc(97) int quadX2 = adjustedX1 - offsetY2;
        @Pc(103) int quadX3 = adjustedX1 + deltaX - offsetY2;
        @Pc(109) int quadX4 = adjustedX1 + deltaX + offsetY1;
        @Pc(113) int quadY1 = adjustedY1 + offsetX1;
        @Pc(117) int quadY2 = adjustedY1 - offsetX2;
        @Pc(123) int quadY3 = adjustedY1 + deltaY - offsetX2;
        @Pc(129) int quadY4 = adjustedY1 + deltaY + offsetX1;
        Rasterizer.testPoints(quadX1, quadX2, quadX3);
        Rasterizer.fillTriangle(quadY1, quadY2, quadY3, quadX1, quadX2, quadX3, color);
        Rasterizer.testPoints(quadX1, quadX3, quadX4);
        Rasterizer.fillTriangle(quadY1, quadY3, quadY4, quadX1, quadX3, quadX4, color);
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(IIII)V")
    public static void drawHorizontalLine(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int length, @OriginalArg(3) int color) {
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
        @Pc(32) int pixelOffset = x + y * width;
        for (@Pc(34) int i = 0; i < length; i++) {
            pixels[pixelOffset + i] = color;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([III)V")
    public static void setSize(@OriginalArg(0) int[] pixelBuffer, @OriginalArg(1) int bufferWidth, @OriginalArg(2) int bufferHeight) {
        pixels = pixelBuffer;
        width = bufferWidth;
        height = bufferHeight;
        setClip(0, 0, bufferWidth, bufferHeight);
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "c", descriptor = "()V")
    public static void resetBounds() {
        clipLeft = 0;
        clipTop = 0;
        clipRight = width;
        clipBottom = height;
        invalidateClippingCache();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "([I)V")
    public static void saveClip(@OriginalArg(0) int[] clipBounds) {
        clipBounds[0] = clipLeft;
        clipBounds[1] = clipTop;
        clipBounds[2] = clipRight;
        clipBounds[3] = clipBottom;
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "([I)V")
    public static void restoreClip(@OriginalArg(0) int[] clipBounds) {
        clipLeft = clipBounds[0];
        clipTop = clipBounds[1];
        clipRight = clipBounds[2];
        clipBottom = clipBounds[3];
        invalidateClippingCache();
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "()V")
    public static void invalidateClippingCache() {
        clipLeftOffsets = null;
        clipLineWidths = null;
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
    public static void setClippingMask(@OriginalArg(0) int[] leftOffsets, @OriginalArg(1) int[] lineWidths) {
        if (leftOffsets.length != clipBottom - clipTop || lineWidths.length != clipBottom - clipTop) {
            throw new IllegalArgumentException();
        }
        clipLeftOffsets = leftOffsets;
        clipLineWidths = lineWidths;
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "b", descriptor = "()V")
    public static void clearPixelBuffer() {
        @Pc(1) int pixelIndex = 0;
        @Pc(7) int unrolledLoopEnd = width * height - 7;
        while (pixelIndex < unrolledLoopEnd) {
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
            pixels[pixelIndex++] = 0;
        }
        unrolledLoopEnd += 7;
        while (pixelIndex < unrolledLoopEnd) {
            pixels[pixelIndex++] = 0;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "e", descriptor = "(IIIII)V")
    public static void drawDiagonalLine(@OriginalArg(0) int x, @OriginalArg(1) int y, @OriginalArg(2) int deltaX, @OriginalArg(3) int deltaY, @OriginalArg(4) int color) {
        deltaX -= x;
        deltaY -= y;
        if (deltaY == 0) {
            if (deltaX >= 0) {
                drawHorizontalLine(x, y, deltaX + 1, color);
            } else {
                drawHorizontalLine(x + deltaX, y, 1 - deltaX, color);
            }
        } else if (deltaX != 0) {
            if (deltaX + deltaY < 0) {
                x += deltaX;
                deltaX = -deltaX;
                y += deltaY;
                deltaY = -deltaY;
            }
            @Pc(96) int stepIncrement;
            @Pc(127) int interpolatedCoord;
            if (deltaX > deltaY) {
                y <<= 0x10;
                y += 32768;
                @Pc(86) int deltaYFixed = deltaY << 16;
                stepIncrement = (int) Math.floor((double) deltaYFixed / (double) deltaX + 0.5D);
                deltaX += x;
                if (x < clipLeft) {
                    y += stepIncrement * (clipLeft - x);
                    x = clipLeft;
                }
                if (deltaX >= clipRight) {
                    deltaX = clipRight - 1;
                }
                while (x <= deltaX) {
                    interpolatedCoord = y >> 16;
                    if (interpolatedCoord >= clipTop && interpolatedCoord < clipBottom) {
                        pixels[x + interpolatedCoord * width] = color;
                    }
                    y += stepIncrement;
                    x++;
                }
            } else {
                x <<= 0x10;
                x += 32768;
                @Pc(160) int deltaXFixed = deltaX << 16;
                stepIncrement = (int) Math.floor((double) deltaXFixed / (double) deltaY + 0.5D);
                deltaY += y;
                if (y < clipTop) {
                    x += stepIncrement * (clipTop - y);
                    y = clipTop;
                }
                if (deltaY >= clipBottom) {
                    deltaY = clipBottom - 1;
                }
                while (y <= deltaY) {
                    interpolatedCoord = x >> 16;
                    if (interpolatedCoord >= clipLeft && interpolatedCoord < clipRight) {
                        pixels[interpolatedCoord + y * width] = color;
                    }
                    x += stepIncrement;
                    y++;
                }
            }
        } else if (deltaY >= 0) {
            drawVerticalLine(x, y, deltaY + 1, color);
        } else {
            drawVerticalLine(x, y + deltaY, -deltaY + 1, color);
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "e", descriptor = "(IIII)V")
    public static void drawCircle(@OriginalArg(0) int centerX, @OriginalArg(1) int centerY, @OriginalArg(2) int radius) {
        if (radius == 0) {
            drawPixel(centerX, centerY);
            return;
        }
        if (radius < 0) {
            radius = -radius;
        }
        @Pc(15) int topBound = centerY - radius;
        if (topBound < clipTop) {
            topBound = clipTop;
        }
        @Pc(26) int bottomBound = centerY + radius + 1;
        if (bottomBound > clipBottom) {
            bottomBound = clipBottom;
        }
        @Pc(33) int currentY = topBound;
        @Pc(37) int radiusSquared = radius * radius;
        @Pc(39) int halfWidth = 0;
        @Pc(43) int yDistance = centerY - topBound;
        @Pc(47) int yDistanceSquared = yDistance * yDistance;
        @Pc(51) int discriminant = yDistanceSquared - yDistance;
        if (centerY > bottomBound) {
            centerY = bottomBound;
        }
        @Pc(85) int leftX;
        @Pc(94) int rightX;
        @Pc(105) int pixelOffset;
        @Pc(107) int x;
        while (currentY < centerY) {
            while (discriminant <= radiusSquared || yDistanceSquared <= radiusSquared) {
                yDistanceSquared += halfWidth + halfWidth;
                discriminant += halfWidth++ + halfWidth;
            }
            leftX = centerX + 1 - halfWidth;
            if (leftX < clipLeft) {
                leftX = clipLeft;
            }
            rightX = centerX + halfWidth;
            if (rightX > clipRight) {
                rightX = clipRight;
            }
            pixelOffset = leftX + currentY * width;
            for (x = leftX; x < rightX; x++) {
                pixels[pixelOffset++] = 16776960;
            }
            currentY++;
            yDistanceSquared -= yDistance-- + yDistance;
            discriminant -= yDistance + yDistance;
        }
        halfWidth = radius;
        yDistance = currentY - centerY;
        discriminant = yDistance * yDistance + radiusSquared;
        yDistanceSquared = discriminant - radius;
        discriminant -= yDistance;
        while (currentY < bottomBound) {
            while (discriminant > radiusSquared && yDistanceSquared > radiusSquared) {
                discriminant -= halfWidth-- + halfWidth;
                yDistanceSquared -= halfWidth + halfWidth;
            }
            leftX = centerX - halfWidth;
            if (leftX < clipLeft) {
                leftX = clipLeft;
            }
            rightX = centerX + halfWidth;
            if (rightX > clipRight - 1) {
                rightX = clipRight - 1;
            }
            pixelOffset = leftX + currentY * width;
            for (x = leftX; x <= rightX; x++) {
                pixels[pixelOffset++] = 16776960;
            }
            currentY++;
            discriminant += yDistance + yDistance;
            yDistanceSquared += yDistance++ + yDistance;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "f", descriptor = "(IIIII)V")
    public static void drawCircleAlpha(@OriginalArg(0) int centerX, @OriginalArg(1) int centerY, @OriginalArg(2) int radius, @OriginalArg(4) int alpha) {
        if (alpha == 0) {
            return;
        }
        if (alpha == 256) {
            drawCircle(centerX, centerY, radius);
            return;
        }
        @Pc(20) int inverseAlpha = 256 - alpha;
        @Pc(28) int redComponent = alpha * 255;
        @Pc(36) int greenComponent = alpha * 255;
        @Pc(42) int blueComponent = alpha * 0;
        @Pc(46) int topY = centerY - radius;
        if (topY < clipTop) {
            topY = clipTop;
        }
        @Pc(57) int bottomY = centerY + radius + 1;
        if (bottomY > clipBottom) {
            bottomY = clipBottom;
        }
        @Pc(64) int currentY = topY;
        @Pc(68) int radiusSquared = radius * radius;
        @Pc(70) int halfWidth = 0;
        @Pc(74) int yDistance = centerY - topY;
        @Pc(78) int yDistanceSquared = yDistance * yDistance;
        @Pc(82) int discriminant = yDistanceSquared - yDistance;
        if (centerY > bottomY) {
            centerY = bottomY;
        }
        @Pc(151) int bgRed;
        @Pc(161) int bgGreen;
        @Pc(169) int bgBlue;
        @Pc(116) int leftX;
        @Pc(125) int rightX;
        @Pc(136) int pixelOffset;
        @Pc(138) int x;
        @Pc(191) int blendedColor;
        while (currentY < centerY) {
            while (discriminant <= radiusSquared || yDistanceSquared <= radiusSquared) {
                yDistanceSquared += halfWidth + halfWidth;
                discriminant += halfWidth++ + halfWidth;
            }
            leftX = centerX + 1 - halfWidth;
            if (leftX < clipLeft) {
                leftX = clipLeft;
            }
            rightX = centerX + halfWidth;
            if (rightX > clipRight) {
                rightX = clipRight;
            }
            pixelOffset = leftX + currentY * width;
            for (x = leftX; x < rightX; x++) {
                bgRed = (pixels[pixelOffset] >> 16 & 0xFF) * inverseAlpha;
                bgGreen = (pixels[pixelOffset] >> 8 & 0xFF) * inverseAlpha;
                bgBlue = (pixels[pixelOffset] & 0xFF) * inverseAlpha;
                blendedColor = (redComponent + bgRed >> 8 << 16) + (greenComponent + bgGreen >> 8 << 8) + (blueComponent + bgBlue >> 8);
                pixels[pixelOffset++] = blendedColor;
            }
            currentY++;
            yDistanceSquared -= yDistance-- + yDistance;
            discriminant -= yDistance + yDistance;
        }
        halfWidth = radius;
        yDistance = -yDistance;
        discriminant = yDistance * yDistance + radiusSquared;
        yDistanceSquared = discriminant - radius;
        discriminant -= yDistance;
        while (currentY < bottomY) {
            while (discriminant > radiusSquared && yDistanceSquared > radiusSquared) {
                discriminant -= halfWidth-- + halfWidth;
                yDistanceSquared -= halfWidth + halfWidth;
            }
            leftX = centerX - halfWidth;
            if (leftX < clipLeft) {
                leftX = clipLeft;
            }
            rightX = centerX + halfWidth;
            if (rightX > clipRight - 1) {
                rightX = clipRight - 1;
            }
            pixelOffset = leftX + currentY * width;
            for (x = leftX; x <= rightX; x++) {
                bgRed = (pixels[pixelOffset] >> 16 & 0xFF) * inverseAlpha;
                bgGreen = (pixels[pixelOffset] >> 8 & 0xFF) * inverseAlpha;
                bgBlue = (pixels[pixelOffset] & 0xFF) * inverseAlpha;
                blendedColor = (redComponent + bgRed >> 8 << 16) + (greenComponent + bgGreen >> 8 << 8) + (blueComponent + bgBlue >> 8);
                pixels[pixelOffset++] = blendedColor;
            }
            currentY++;
            discriminant += yDistance + yDistance;
            yDistanceSquared += yDistance++ + yDistance;
        }
    }

    @OriginalMember(owner = "runetek4.client!kb", name = "a", descriptor = "(III[I[I)V")
    public static void clearMaskedRegion(@OriginalArg(0) int startX, @OriginalArg(1) int startY, @OriginalArg(3) int[] lineOffsets, @OriginalArg(4) int[] lineWidths) {
        @Pc(5) int rowPixelOffset = startX + startY * width;
        for (@Pc(7) int lineIndex = 0; lineIndex < lineOffsets.length; lineIndex++) {
            @Pc(17) int pixelOffset = rowPixelOffset + lineOffsets[lineIndex];
            for (@Pc(22) int widthCounter = -lineWidths[lineIndex]; widthCounter < 0; widthCounter++) {
                pixels[pixelOffset++] = 0;
            }
            rowPixelOffset += width;
        }
    }
}
