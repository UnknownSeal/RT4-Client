package com.jagex.game.world;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;

import com.jagex.game.runetek4.client.GameShell;
import com.jagex.core.utils.string.JString;
import com.jagex.graphics.raster.SoftwareRenderer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!fd")
public final class WorldMapFont {

	@OriginalMember(owner = "client!fd", name = "d", descriptor = "Ljava/lang/String;")
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!\"£$%^&*()-_=+[{]};:'@#~,<.>/?\\| " + String.valueOf('Ä') + 'Ë' + 'Ï' + 'Ö' + 'Ü' + 'ä' + 'ë' + 'ï' + 'ö' + 'ü' + 'ÿ' + 'ß' + 'Á' + 'À' + 'É' + 'È' + 'Í' + 'Ì' + 'Ó' + 'Ò' + 'Ú' + 'Ù' + 'á' + 'à' + 'é' + 'è' + 'í' + 'ì' + 'ó' + 'ò' + 'ú' + 'ù' + 'Â' + 'Ê' + 'Î' + 'Ô' + 'Û' + 'â' + 'ê' + 'î' + 'ô' + 'û' + 'Æ' + 'æ';

	@OriginalMember(owner = "client!fd", name = "e", descriptor = "I")
	public static final int ALPHABET_SIZE = ALPHABET.length();

	@OriginalMember(owner = "client!fd", name = "f", descriptor = "[I")
	private static final int[] CHAR_INDEXES = new int[256];

	@OriginalMember(owner = "client!fd", name = "c", descriptor = "I")
	private int dataIndex = 0;

	@OriginalMember(owner = "client!fd", name = "b", descriptor = "Z")
	private boolean grayscale = false;

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "[B")
	private byte[] data = new byte[100000];

	static {
		for (@Pc(146) int c = 0; c < 256; c++) {
			@Pc(153) int i = ALPHABET.indexOf(c);
			if (i == -1) {
				i = 74;
			}
			CHAR_INDEXES[c] = i * 9;
		}
	}

	@OriginalMember(owner = "client!fd", name = "<init>", descriptor = "(IZLjava/awt/Component;)V")
	public WorldMapFont(@OriginalArg(0) int size, @OriginalArg(1) boolean arg1, @OriginalArg(2) Component component) {
		this.dataIndex = ALPHABET_SIZE * 9;
		this.grayscale = false;
		@Pc(30) Font bold = new Font("Helvetica", Font.BOLD, size);
		@Pc(34) FontMetrics boldMetrics = component.getFontMetrics(bold);
		@Pc(36) int i;
		for (i = 0; i < ALPHABET_SIZE; i++) {
			this.preRenderGlyph(bold, boldMetrics, ALPHABET.charAt(i), i, false);
		}
		if (this.grayscale) {
			this.dataIndex = ALPHABET_SIZE * 9;
			this.grayscale = false;
			Font plain = new Font("Helvetica", Font.PLAIN, size);
			FontMetrics plainMetrics = component.getFontMetrics(bold);
			for (i = 0; i < ALPHABET_SIZE; i++) {
				this.preRenderGlyph(plain, plainMetrics, ALPHABET.charAt(i), i, false);
			}
			if (!this.grayscale) {
				this.dataIndex = ALPHABET_SIZE * 9;
				this.grayscale = false;
				for (i = 0; i < ALPHABET_SIZE; i++) {
					this.preRenderGlyph(plain, plainMetrics, ALPHABET.charAt(i), i, true);
				}
			}
		}
		@Pc(121) byte[] data = new byte[this.dataIndex];
		for (@Pc(123) int j = 0; j < this.dataIndex; j++) {
			data[j] = this.data[j];
		}
		this.data = data;
	}

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "(Lclient!na;IIIZ)V")
	private void renderString(@OriginalArg(0) JString string, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int color, @OriginalArg(4) boolean shadow) {
		if (this.grayscale || color == 0) {
			shadow = false;
		}
		for (@Pc(8) int i = 0; i < string.length(); i++) {
			@Pc(20) int index = CHAR_INDEXES[string.charAt(i)];
			if (shadow) {
				this.renderGlyph(index, x + 1, y, 1, this.data);
				this.renderGlyph(index, x, y + 1, 1, this.data);
			}
			this.renderGlyph(index, x, y, color, this.data);
			x += this.data[index + 7];
		}
	}

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "()I")
	public int getBaselineOffset() {
		return this.data[8] - 1;
	}

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "([I[BIIIIIII)V")
	private void renderGlyphGrayscale(@OriginalArg(0) int[] dest, @OriginalArg(1) byte[] src, @OriginalArg(2) int color, @OriginalArg(3) int srcIndex, @OriginalArg(4) int destIndex, @OriginalArg(5) int w, @OriginalArg(6) int h, @OriginalArg(7) int destStride, @OriginalArg(8) int srcStride) {
		for (@Pc(2) int y = -h; y < 0; y++) {
			for (@Pc(7) int x = -w; x < 0; x++) {
				@Pc(16) int intensity = src[srcIndex++] & 0xFF;
				if (intensity <= 30) {
					destIndex++;
				} else if (intensity >= 230) {
					dest[destIndex++] = color;
				} else {
					@Pc(32) int backgroundColor = dest[destIndex];
					dest[destIndex++] = ((color & 0xFF00FF) * intensity + (backgroundColor & 0xFF00FF) * (256 - intensity) & 0xFF00FF00) + ((color & 0xFF00) * intensity + (backgroundColor & 0xFF00) * (256 - intensity) & 0xFF0000) >> 8;
				}
			}
			destIndex += destStride;
			srcIndex += srcStride;
		}
	}

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "(IIII[B)V")
	private void renderGlyph(@OriginalArg(0) int index, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int color, @OriginalArg(4) byte[] src) {
		@Pc(7) int glyphX = x + src[index + 5];
		@Pc(15) int glyphY = y - src[index + 6];
		@Pc(21) int width = src[index + 3];
		@Pc(27) int height = src[index + 4];
		@Pc(47) int srcIndex = src[index] * 16384 + src[index + 1] * 128 + src[index + 2];
		@Pc(53) int destIndex = glyphX + glyphY * SoftwareRenderer.width;
		@Pc(57) int destStride = SoftwareRenderer.width - width;
		@Pc(59) int srcStride = 0;
		@Pc(66) int clipAmount;
		if (glyphY < SoftwareRenderer.clipTop) {
			clipAmount = SoftwareRenderer.clipTop - glyphY;
			height -= clipAmount;
			glyphY = SoftwareRenderer.clipTop;
			srcIndex += clipAmount * width;
			destIndex += clipAmount * SoftwareRenderer.width;
		}
		if (glyphY + height >= SoftwareRenderer.clipBottom) {
			height -= glyphY + height + 1 - SoftwareRenderer.clipBottom;
		}
		if (glyphX < SoftwareRenderer.clipLeft) {
			clipAmount = SoftwareRenderer.clipLeft - glyphX;
			width -= clipAmount;
			glyphX = SoftwareRenderer.clipLeft;
			srcIndex += clipAmount;
			destIndex += clipAmount;
			srcStride = clipAmount;
			destStride += clipAmount;
		}
		if (glyphX + width >= SoftwareRenderer.clipRight) {
			clipAmount = glyphX + width + 1 - SoftwareRenderer.clipRight;
			width -= clipAmount;
			srcStride += clipAmount;
			destStride += clipAmount;
		}
		if (width <= 0 || height <= 0) {
			return;
		}
		if (this.grayscale) {
			this.renderGlyphGrayscale(SoftwareRenderer.pixels, src, color, srcIndex, destIndex, width, height, destStride, srcStride);
		} else {
			this.renderGlyphMono(SoftwareRenderer.pixels, src, color, srcIndex, destIndex, width, height, destStride, srcStride);
		}
	}

	@OriginalMember(owner = "client!fd", name = "b", descriptor = "([I[BIIIIIII)V")
	private void renderGlyphMono(@OriginalArg(0) int[] destPixels, @OriginalArg(1) byte[] src, @OriginalArg(2) int color, @OriginalArg(3) int srcIndex, @OriginalArg(4) int destIndex, @OriginalArg(5) int width, @OriginalArg(6) int height, @OriginalArg(7) int destStride, @OriginalArg(8) int srcStride) {
		@Pc(4) int unrolledWidth = -(width >> 2);
		@Pc(9) int remainingWidth = -(width & 0x3);
		for (@Pc(12) int y = -height; y < 0; y++) {
			@Pc(16) int x;
			for (x = unrolledWidth; x < 0; x++) {
				if (src[srcIndex++] == 0) {
					destIndex++;
				} else {
					destPixels[destIndex++] = color;
				}
				if (src[srcIndex++] == 0) {
					destIndex++;
				} else {
					destPixels[destIndex++] = color;
				}
				if (src[srcIndex++] == 0) {
					destIndex++;
				} else {
					destPixels[destIndex++] = color;
				}
				if (src[srcIndex++] == 0) {
					destIndex++;
				} else {
					destPixels[destIndex++] = color;
				}
			}
			for (x = remainingWidth; x < 0; x++) {
				if (src[srcIndex++] == 0) {
					destIndex++;
				} else {
					destPixels[destIndex++] = color;
				}
			}
			destIndex += destStride;
			srcIndex += srcStride;
		}
	}

	@OriginalMember(owner = "client!fd", name = "b", descriptor = "(Lclient!na;IIIZ)V")
	public void renderStringCenter(@OriginalArg(0) JString string, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int color) {
		@Pc(5) int halfWidth = this.getStringWidth(string) / 2;
		@Pc(8) int fontHeight = this.getLineHeight();
		if (x - halfWidth <= SoftwareRenderer.clipRight && (x + halfWidth >= SoftwareRenderer.clipLeft && (y - fontHeight <= SoftwareRenderer.clipBottom && y >= 0))) {
			this.renderString(string, x - halfWidth, y, color, true);
		}
	}

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "(Ljava/awt/Font;Ljava/awt/FontMetrics;CIZ)V")
	private void preRenderGlyph(@OriginalArg(0) Font font, @OriginalArg(1) FontMetrics metrics, @OriginalArg(2) char c, @OriginalArg(3) int id, @OriginalArg(4) boolean shadow) {
		@Pc(3) int imageWidth = metrics.charWidth(c);
		@Pc(5) int width = imageWidth;
		if (shadow) {
			try {
				if (c == '/') {
					shadow = false;
				}
				if (c == 'f' || c == 't' || c == 'w' || c == 'v' || c == 'k' || c == 'x' || c == 'y' || c == 'A' || c == 'V' || c == 'W') {
					imageWidth++;
				}
			} catch (@Pc(45) Exception ignored) {
			}
		}
		@Pc(48) int maxAscent = metrics.getMaxAscent();
		@Pc(54) int imageHeight = metrics.getMaxAscent() + metrics.getMaxDescent();
		@Pc(57) int height = metrics.getHeight();
		@Pc(62) Image image = GameShell.canvas.createImage(imageWidth, imageHeight);
		@Pc(65) Graphics graphics = image.getGraphics();
		graphics.setColor(Color.black);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		graphics.setColor(Color.white);
		graphics.setFont(font);
		graphics.drawString(c + "", 0, maxAscent);
		if (shadow) {
			graphics.drawString(c + "", 1, maxAscent);
		}
		@Pc(111) int[] pixels = new int[imageWidth * imageHeight];
		@Pc(123) PixelGrabber grabber = new PixelGrabber(image, 0, 0, imageWidth, imageHeight, pixels, 0, imageWidth);
		try {
			grabber.grabPixels();
		} catch (@Pc(128) Exception ignored) {
		}
		image.flush();
		@Pc(134) int x0 = 0;
		@Pc(136) int y0 = 0;
		@Pc(138) int x1 = imageWidth;
		@Pc(140) int y1 = imageHeight;
		@Pc(142) int y;
		@Pc(147) int x;
		@Pc(158) int color;
		y0: for (y = 0; y < imageHeight; y++) {
			for (x = 0; x < imageWidth; x++) {
				color = pixels[x + y * imageWidth];
				if ((color & 0xFFFFFF) != 0) {
					y0 = y;
					break y0;
				}
			}
		}
		x0: for (y = 0; y < imageWidth; y++) {
			for (x = 0; x < imageHeight; x++) {
				color = pixels[y + x * imageWidth];
				if ((color & 0xFFFFFF) != 0) {
					x0 = y;
					break x0;
				}
			}
		}
		y1: for (y = imageHeight - 1; y >= 0; y--) {
			for (x = 0; x < imageWidth; x++) {
				color = pixels[x + y * imageWidth];
				if ((color & 0xFFFFFF) != 0) {
					y1 = y + 1;
					break y1;
				}
			}
		}
		x1: for (y = imageWidth - 1; y >= 0; y--) {
			for (x = 0; x < imageHeight; x++) {
				color = pixels[y + x * imageWidth];
				if ((color & 0xFFFFFF) != 0) {
					x1 = y + 1;
					break x1;
				}
			}
		}
		this.data[id * 9] = (byte) (this.dataIndex / 16384);
		this.data[id * 9 + 1] = (byte) (this.dataIndex / 128 & 0x7F);
		this.data[id * 9 + 2] = (byte) (this.dataIndex & 0x7F);
		this.data[id * 9 + 3] = (byte) (x1 - x0);
		this.data[id * 9 + 4] = (byte) (y1 - y0);
		this.data[id * 9 + 5] = (byte) x0;
		this.data[id * 9 + 6] = (byte) (maxAscent - y0);
		this.data[id * 9 + 7] = (byte) width;
		this.data[id * 9 + 8] = (byte) height;
		for (y = y0; y < y1; y++) {
			for (x = x0; x < x1; x++) {
				color = pixels[x + y * imageWidth] & 0xFF;
				if (color > 30 && color < 230) {
					this.grayscale = true;
				}
				this.data[this.dataIndex++] = (byte) color;
			}
		}
	}

	@OriginalMember(owner = "client!fd", name = "a", descriptor = "(Lclient!na;)I")
	private int getStringWidth(@OriginalArg(0) JString string) {
		@Pc(1) int width = 0;
		for (@Pc(3) int i = 0; i < string.length(); i++) {
			if (string.charAt(i) == 64 && i + 4 < string.length() && string.charAt(i + 4) == 64) {
				i += 4;
			} else if (string.charAt(i) == 126 && i + 4 < string.length() && string.charAt(i + 4) == 126) {
				i += 4;
			} else {
				width += this.data[CHAR_INDEXES[string.charAt(i)] + 7];
			}
		}
		return width;
	}

	@OriginalMember(owner = "client!fd", name = "c", descriptor = "()I")
	public int getLineHeight() {
		return this.data[6];
	}
}
