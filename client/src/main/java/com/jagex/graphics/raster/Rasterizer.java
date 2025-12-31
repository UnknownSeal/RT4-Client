package com.jagex.graphics.raster;

import com.jagex.graphics.texture.TextureProvider;
import com.jagex.math.ColorUtils;
import com.jagex.math.IntMath;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Rasterizer {

    @OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "[I")
    public static final int[] palette = new int[65536];

	@OriginalMember(owner = "runetek4.client!hf", name = "e", descriptor = "Lclient!m;")
	public static TextureProvider textureProvider;

	@OriginalMember(owner = "runetek4.client!hf", name = "k", descriptor = "I")
	public static int height;

	@OriginalMember(owner = "runetek4.client!hf", name = "c", descriptor = "[I")
	public static int[] offsets = new int[1024];

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

	@OriginalMember(owner = "runetek4.client!hf", name = "j", descriptor = "Z")
	public static boolean jagged = true;

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "I")
	public static int centerY;

	@OriginalMember(owner = "runetek4.client!hf", name = "m", descriptor = "I")
	public static int centerX;

	@OriginalMember(owner = "runetek4.client!hf", name = "n", descriptor = "I")
	public static int width;

	@OriginalMember(owner = "runetek4.client!hf", name = "i", descriptor = "Z")
	public static boolean opaque = false;

	@OriginalMember(owner = "runetek4.client!hf", name = "l", descriptor = "Z")
	public static boolean lowDetail = false;

	@OriginalMember(owner = "runetek4.client!hf", name = "o", descriptor = "F")
	public static float brightness = 1.0F;

	@OriginalMember(owner = "runetek4.client!hf", name = "p", descriptor = "Z")
	public static boolean testX = false;

	@OriginalMember(owner = "runetek4.client!hf", name = "q", descriptor = "I")
	public static int alpha = 0;

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(Lclient!m;)V")
    public static void unpackTextures(@OriginalArg(0) TextureProvider arg0) {
        textureProvider = arg0;
    }

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "()V")
	public static void prepare() {
		prepareOffsets(SoftwareRenderer.clipLeft, SoftwareRenderer.clipTop, SoftwareRenderer.clipRight, SoftwareRenderer.clipBottom);
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(IIII)V")
	private static void prepareOffsets(@OriginalArg(0) int left, @OriginalArg(1) int top, @OriginalArg(2) int right, @OriginalArg(3) int bottom) {
		width = right - left;
		height = bottom - top;
		prepareOffsets();
		if (offsets.length < height) {
			offsets = new int[IntMath.bitceil(height)];
		}
		@Pc(23) int pixelOffset = top * SoftwareRenderer.width + left;
		for (@Pc(25) int row = 0; row < height; row++) {
			offsets[row] = pixelOffset;
			pixelOffset += SoftwareRenderer.width;
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "(II)V")
	public static void setBounds(@OriginalArg(0) int newCenterX, @OriginalArg(1) int newCenterY) {
		@Pc(3) int firstPixelOffset = offsets[0];
		@Pc(7) int regionTopY = firstPixelOffset / SoftwareRenderer.width;
		@Pc(13) int regionLeftX = firstPixelOffset - regionTopY * SoftwareRenderer.width;
		centerX = newCenterX - regionLeftX;
		centerY = newCenterY - regionTopY;
		screenLowerX = -centerX;
		screenUpperX = width - centerX;
		screenLowerY = -centerY;
		screenUpperY = height - centerY;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "c", descriptor = "()V")
	public static void prepareOffsets() {
		centerX = width / 2;
		centerY = height / 2;
		screenLowerX = -centerX;
		screenUpperX = width - centerX;
		screenLowerY = -centerY;
		screenUpperY = height - centerY;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([BIIIIIII)V")
	public static void fillSpriteTriangle(@OriginalArg(0) byte[] spriteData, @OriginalArg(1) int y1, @OriginalArg(2) int y2, @OriginalArg(3) int y3, @OriginalArg(4) int x1, @OriginalArg(5) int x2, @OriginalArg(6) int x3, @OriginalArg(7) int scanlineStride) {
		@Pc(1) int slope12 = 0;
		if (y2 != y1) {
			slope12 = (x2 - x1 << 16) / (y2 - y1);
		}
		@Pc(16) int slope23 = 0;
		if (y3 != y2) {
			slope23 = (x3 - x2 << 16) / (y3 - y2);
		}
		@Pc(31) int slope31 = 0;
		if (y3 != y1) {
			slope31 = (x1 - x3 << 16) / (y1 - y3);
		}
		if (y1 <= y2 && y1 <= y3) {
			if (y2 < y3) {
				x3 = x1 <<= 0x10;
				if (y1 < 0) {
					x3 -= slope31 * y1;
					x1 -= slope12 * y1;
					y1 = 0;
				}
				x2 <<= 0x10;
				if (y2 < 0) {
					x2 -= slope23 * y2;
					y2 = 0;
				}
				if ((y1 == y2 || slope31 >= slope12) && (y1 != y2 || slope31 <= slope23)) {
					y3 -= y2;
					y2 -= y1;
					y1 *= scanlineStride;
					while (true) {
						y2--;
						if (y2 < 0) {
							while (true) {
								y3--;
								if (y3 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y1, x2 >> 16, x3 >> 16);
								x3 += slope31;
								x2 += slope23;
								y1 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y1, x1 >> 16, x3 >> 16);
						x3 += slope31;
						x1 += slope12;
						y1 += scanlineStride;
					}
				} else {
					y3 -= y2;
					y2 -= y1;
					y1 *= scanlineStride;
					while (true) {
						y2--;
						if (y2 < 0) {
							while (true) {
								y3--;
								if (y3 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y1, x3 >> 16, x2 >> 16);
								x3 += slope31;
								x2 += slope23;
								y1 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y1, x3 >> 16, x1 >> 16);
						x3 += slope31;
						x1 += slope12;
						y1 += scanlineStride;
					}
				}
			} else {
				x2 = x1 <<= 0x10;
				if (y1 < 0) {
					x2 -= slope31 * y1;
					x1 -= slope12 * y1;
					y1 = 0;
				}
				x3 <<= 0x10;
				if (y3 < 0) {
					x3 -= slope23 * y3;
					y3 = 0;
				}
				if ((y1 == y3 || slope31 >= slope12) && (y1 != y3 || slope23 <= slope12)) {
					y2 -= y3;
					y3 -= y1;
					y1 *= scanlineStride;
					while (true) {
						y3--;
						if (y3 < 0) {
							while (true) {
								y2--;
								if (y2 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y1, x1 >> 16, x3 >> 16);
								x3 += slope23;
								x1 += slope12;
								y1 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y1, x1 >> 16, x2 >> 16);
						x2 += slope31;
						x1 += slope12;
						y1 += scanlineStride;
					}
				} else {
					y2 -= y3;
					y3 -= y1;
					y1 *= scanlineStride;
					while (true) {
						y3--;
						if (y3 < 0) {
							while (true) {
								y2--;
								if (y2 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y1, x3 >> 16, x1 >> 16);
								x3 += slope23;
								x1 += slope12;
								y1 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y1, x2 >> 16, x1 >> 16);
						x2 += slope31;
						x1 += slope12;
						y1 += scanlineStride;
					}
				}
			}
		} else if (y2 <= y3) {
			if (y3 < y1) {
				x1 = x2 <<= 0x10;
				if (y2 < 0) {
					x1 -= slope12 * y2;
					x2 -= slope23 * y2;
					y2 = 0;
				}
				x3 <<= 0x10;
				if (y3 < 0) {
					x3 -= slope31 * y3;
					y3 = 0;
				}
				if (y2 != y3 && slope12 < slope23 || y2 == y3 && slope12 > slope31) {
					y1 -= y3;
					y3 -= y2;
					y2 *= scanlineStride;
					while (true) {
						y3--;
						if (y3 < 0) {
							while (true) {
								y1--;
								if (y1 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y2, x1 >> 16, x3 >> 16);
								x1 += slope12;
								x3 += slope31;
								y2 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y2, x1 >> 16, x2 >> 16);
						x1 += slope12;
						x2 += slope23;
						y2 += scanlineStride;
					}
				} else {
					y1 -= y3;
					y3 -= y2;
					y2 *= scanlineStride;
					while (true) {
						y3--;
						if (y3 < 0) {
							while (true) {
								y1--;
								if (y1 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y2, x3 >> 16, x1 >> 16);
								x1 += slope12;
								x3 += slope31;
								y2 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y2, x2 >> 16, x1 >> 16);
						x1 += slope12;
						x2 += slope23;
						y2 += scanlineStride;
					}
				}
			} else {
				x3 = x2 <<= 0x10;
				if (y2 < 0) {
					x3 -= slope12 * y2;
					x2 -= slope23 * y2;
					y2 = 0;
				}
				x1 <<= 0x10;
				if (y1 < 0) {
					x1 -= slope31 * y1;
					y1 = 0;
				}
				if (slope12 < slope23) {
					y3 -= y1;
					y1 -= y2;
					y2 *= scanlineStride;
					while (true) {
						y1--;
						if (y1 < 0) {
							while (true) {
								y3--;
								if (y3 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y2, x1 >> 16, x2 >> 16);
								x1 += slope31;
								x2 += slope23;
								y2 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y2, x3 >> 16, x2 >> 16);
						x3 += slope12;
						x2 += slope23;
						y2 += scanlineStride;
					}
				} else {
					y3 -= y1;
					y1 -= y2;
					y2 *= scanlineStride;
					while (true) {
						y1--;
						if (y1 < 0) {
							while (true) {
								y3--;
								if (y3 < 0) {
									return;
								}
								drawSpriteScanline(spriteData, y2, x2 >> 16, x1 >> 16);
								x1 += slope31;
								x2 += slope23;
								y2 += scanlineStride;
							}
						}
						drawSpriteScanline(spriteData, y2, x2 >> 16, x3 >> 16);
						x3 += slope12;
						x2 += slope23;
						y2 += scanlineStride;
					}
				}
			}
		} else if (y1 < y2) {
			x2 = x3 <<= 0x10;
			if (y3 < 0) {
				x2 -= slope23 * y3;
				x3 -= slope31 * y3;
				y3 = 0;
			}
			x1 <<= 0x10;
			if (y1 < 0) {
				x1 -= slope12 * y1;
				y1 = 0;
			}
			if (slope23 < slope31) {
				y2 -= y1;
				y1 -= y3;
				y3 *= scanlineStride;
				while (true) {
					y1--;
					if (y1 < 0) {
						while (true) {
							y2--;
							if (y2 < 0) {
								return;
							}
							drawSpriteScanline(spriteData, y3, x2 >> 16, x1 >> 16);
							x2 += slope23;
							x1 += slope12;
							y3 += scanlineStride;
						}
					}
					drawSpriteScanline(spriteData, y3, x2 >> 16, x3 >> 16);
					x2 += slope23;
					x3 += slope31;
					y3 += scanlineStride;
				}
			} else {
				y2 -= y1;
				y1 -= y3;
				y3 *= scanlineStride;
				while (true) {
					y1--;
					if (y1 < 0) {
						while (true) {
							y2--;
							if (y2 < 0) {
								return;
							}
							drawSpriteScanline(spriteData, y3, x1 >> 16, x2 >> 16);
							x2 += slope23;
							x1 += slope12;
							y3 += scanlineStride;
						}
					}
					drawSpriteScanline(spriteData, y3, x3 >> 16, x2 >> 16);
					x2 += slope23;
					x3 += slope31;
					y3 += scanlineStride;
				}
			}
		} else {
			x1 = x3 <<= 0x10;
			if (y3 < 0) {
				x1 -= slope23 * y3;
				x3 -= slope31 * y3;
				y3 = 0;
			}
			x2 <<= 0x10;
			if (y2 < 0) {
				x2 -= slope12 * y2;
				y2 = 0;
			}
			if (slope23 < slope31) {
				y1 -= y2;
				y2 -= y3;
				y3 *= scanlineStride;
				while (true) {
					y2--;
					if (y2 < 0) {
						while (true) {
							y1--;
							if (y1 < 0) {
								return;
							}
							drawSpriteScanline(spriteData, y3, x2 >> 16, x3 >> 16);
							x2 += slope12;
							x3 += slope31;
							y3 += scanlineStride;
						}
					}
					drawSpriteScanline(spriteData, y3, x1 >> 16, x3 >> 16);
					x1 += slope23;
					x3 += slope31;
					y3 += scanlineStride;
				}
			} else {
				y1 -= y2;
				y2 -= y3;
				y3 *= scanlineStride;
				while (true) {
					y2--;
					if (y2 < 0) {
						while (true) {
							y1--;
							if (y1 < 0) {
								return;
							}
							drawSpriteScanline(spriteData, y3, x3 >> 16, x2 >> 16);
							x2 += slope12;
							x3 += slope31;
							y3 += scanlineStride;
						}
					}
					drawSpriteScanline(spriteData, y3, x3 >> 16, x1 >> 16);
					x1 += slope23;
					x3 += slope31;
					y3 += scanlineStride;
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([BIIII)V")
	private static void drawSpriteScanline(@OriginalArg(0) byte[] spriteData, @OriginalArg(1) int scanlineOffset, @OriginalArg(3) int leftX, @OriginalArg(4) int rightX) {
		if (leftX >= rightX) {
			return;
		}
		scanlineOffset += leftX;
		@Pc(13) int unrolledCount = rightX - leftX >> 2;
		while (true) {
			unrolledCount--;
			if (unrolledCount < 0) {
				unrolledCount = rightX - leftX & 0x3;
				while (true) {
					unrolledCount--;
					if (unrolledCount < 0) {
						return;
					}
					spriteData[scanlineOffset++] = 1;
				}
			}
			@Pc(19) int offset1 = scanlineOffset + 1;
			spriteData[scanlineOffset] = 1;
			@Pc(24) int offset2 = offset1 + 1;
			spriteData[offset1] = 1;
			@Pc(29) int offset3 = offset2 + 1;
			spriteData[offset2] = 1;
			scanlineOffset = offset3 + 1;
			spriteData[offset3] = 1;
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(IIIIIIIIIIIIIIIIIII)V")
	public static void fillTexturedTriangle(@OriginalArg(0) int yA, @OriginalArg(1) int yB, @OriginalArg(2) int yC, @OriginalArg(3) int xA, @OriginalArg(4) int arg4, @OriginalArg(5) int xC, @OriginalArg(6) int colorA, @OriginalArg(7) int colorB, @OriginalArg(8) int colorC, @OriginalArg(9) int uA, @OriginalArg(10) int vA, @OriginalArg(11) int wA, @OriginalArg(12) int uB, @OriginalArg(13) int vB, @OriginalArg(14) int wB, @OriginalArg(15) int uC, @OriginalArg(16) int vC, @OriginalArg(17) int wC, @OriginalArg(18) int textureId) {
		@Pc(5) int[] texels = textureProvider.method3232(textureId, brightness);
		@Pc(12) int averageColor;
		if (texels == null) {
			averageColor = textureProvider.getAverageColor(textureId);
			fillGouraudTriangle(yA, yB, yC, xA, arg4, xC, ColorUtils.multiplyLightness(averageColor, colorA), ColorUtils.multiplyLightness(averageColor, colorB), ColorUtils.multiplyLightness(averageColor, colorC));
			return;
		}
		lowDetail = textureProvider.isLowDetail(textureId);
		opaque = textureProvider.isOpaque(textureId);
		averageColor = arg4 - xA;
		@Pc(47) int dyAB = yB - yA;
		@Pc(51) int dxAC = xC - xA;
		@Pc(55) int dyAC = yC - yA;
		@Pc(59) int colorStepAB = colorB - colorA;
		@Pc(63) int colorStepAC = colorC - colorA;
		@Pc(65) int xStepAB = 0;
		if (yB != yA) {
			xStepAB = (arg4 - xA << 16) / (yB - yA);
		}
		@Pc(80) int xStepBC = 0;
		if (yC != yB) {
			xStepBC = (xC - arg4 << 16) / (yC - yB);
		}
		@Pc(95) int xStepAC = 0;
		if (yC != yA) {
			xStepAC = (xA - xC << 16) / (yA - yC);
		}
		@Pc(116) int length = averageColor * dyAC - dxAC * dyAB;
		if (length == 0) {
			return;
		}
		@Pc(131) int colorGradientX = (colorStepAB * dyAC - colorStepAC * dyAB << 9) / length;
		@Pc(143) int colorGradientY = (colorStepAC * averageColor - colorStepAB * dxAC << 9) / length;
		@Pc(147) int duAB = uA - vA;
		@Pc(151) int duAC = uB - vB;
		@Pc(155) int duBC = uC - vC;
		@Pc(159) int dvAB = wA - uA;
		@Pc(163) int dvAC = wB - uB;
		@Pc(167) int dvBC = wC - uC;
		@Pc(177) int uInterpolator = dvAB * uB - dvAC * uA << 14;
		@Pc(187) int uStep = dvAC * uC - dvBC * uB << 5;
		@Pc(197) int local197 = dvBC * uA - dvAB * uC << 5;
		@Pc(207) int vInterpolator = duAB * uB - duAC * uA << 14;
		@Pc(217) int vStep = duAC * uC - duBC * uB << 5;
		@Pc(227) int local227 = duBC * uA - duAB * uC << 5;
		@Pc(237) int wInterpolator = duAC * dvAB - duAB * dvAC << 14;
		@Pc(247) int wStep = duBC * dvAC - duAC * dvBC << 5;
		@Pc(257) int local257 = duAB * dvBC - duBC * dvAB << 5;
		@Pc(336) int yOffset;
		if (yA <= yB && yA <= yC) {
			if (yA < height) {
				if (yB > height) {
					yB = height;
				}
				if (yC > height) {
					yC = height;
				}
				colorA = (colorA << 9) + colorGradientX - colorGradientX * xA;
				if (yB < yC) {
					xC = xA <<= 0x10;
					if (yA < 0) {
						xC -= xStepAC * yA;
						xA -= xStepAB * yA;
						colorA -= colorGradientY * yA;
						yA = 0;
					}
					arg4 <<= 0x10;
					if (yB < 0) {
						arg4 -= xStepBC * yB;
						yB = 0;
					}
					yOffset = yA - centerY;
					uInterpolator += local197 * yOffset;
					vInterpolator += local227 * yOffset;
					wInterpolator += local257 * yOffset;
					if (yA != yB && xStepAC < xStepAB || yA == yB && xStepAC > xStepBC) {
						yC -= yB;
						yB -= yA;
						yA = offsets[yA];
						while (true) {
							yB--;
							if (yB < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, xC >> 16, arg4 >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepAC;
									arg4 += xStepBC;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, xC >> 16, xA >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					} else {
						yC -= yB;
						yB -= yA;
						yA = offsets[yA];
						while (true) {
							yB--;
							if (yB < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, arg4 >> 16, xC >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepAC;
									arg4 += xStepBC;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, xA >> 16, xC >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					}
				} else {
					arg4 = xA <<= 0x10;
					if (yA < 0) {
						arg4 -= xStepAC * yA;
						xA -= xStepAB * yA;
						colorA -= colorGradientY * yA;
						yA = 0;
					}
					xC <<= 0x10;
					if (yC < 0) {
						xC -= xStepBC * yC;
						yC = 0;
					}
					yOffset = yA - centerY;
					uInterpolator += local197 * yOffset;
					vInterpolator += local227 * yOffset;
					wInterpolator += local257 * yOffset;
					if ((yA == yC || xStepAC >= xStepAB) && (yA != yC || xStepBC <= xStepAB)) {
						yB -= yC;
						yC -= yA;
						yA = offsets[yA];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yB--;
									if (yB < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, xA >> 16, xC >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepBC;
									xA += xStepAB;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, xA >> 16, arg4 >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							arg4 += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					} else {
						yB -= yC;
						yC -= yA;
						yA = offsets[yA];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yB--;
									if (yB < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, xC >> 16, xA >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepBC;
									xA += xStepAB;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yA, arg4 >> 16, xA >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							arg4 += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					}
				}
			}
		} else if (yB <= yC) {
			if (yB < height) {
				if (yC > height) {
					yC = height;
				}
				if (yA > height) {
					yA = height;
				}
				colorB = (colorB << 9) + colorGradientX - colorGradientX * arg4;
				if (yC < yA) {
					xA = arg4 <<= 0x10;
					if (yB < 0) {
						xA -= xStepAB * yB;
						arg4 -= xStepBC * yB;
						colorB -= colorGradientY * yB;
						yB = 0;
					}
					xC <<= 0x10;
					if (yC < 0) {
						xC -= xStepAC * yC;
						yC = 0;
					}
					yOffset = yB - centerY;
					uInterpolator += local197 * yOffset;
					vInterpolator += local227 * yOffset;
					wInterpolator += local257 * yOffset;
					if (yB != yC && xStepAB < xStepBC || yB == yC && xStepAB > xStepAC) {
						yA -= yC;
						yC -= yB;
						yB = offsets[yB];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yA--;
									if (yA < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, xA >> 16, xC >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAB;
									xC += xStepAC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, xA >> 16, arg4 >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xA += xStepAB;
							arg4 += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					} else {
						yA -= yC;
						yC -= yB;
						yB = offsets[yB];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yA--;
									if (yA < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, xC >> 16, xA >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAB;
									xC += xStepAC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, arg4 >> 16, xA >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xA += xStepAB;
							arg4 += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					}
				} else {
					xC = arg4 <<= 0x10;
					if (yB < 0) {
						xC -= xStepAB * yB;
						arg4 -= xStepBC * yB;
						colorB -= colorGradientY * yB;
						yB = 0;
					}
					xA <<= 0x10;
					if (yA < 0) {
						xA -= xStepAC * yA;
						yA = 0;
					}
					yOffset = yB - centerY;
					uInterpolator += local197 * yOffset;
					vInterpolator += local227 * yOffset;
					wInterpolator += local257 * yOffset;
					if (xStepAB < xStepBC) {
						yC -= yA;
						yA -= yB;
						yB = offsets[yB];
						while (true) {
							yA--;
							if (yA < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, xA >> 16, arg4 >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAC;
									arg4 += xStepBC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, xC >> 16, arg4 >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAB;
							arg4 += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					} else {
						yC -= yA;
						yA -= yB;
						yB = offsets[yB];
						while (true) {
							yA--;
							if (yA < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, arg4 >> 16, xA >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAC;
									arg4 += xStepBC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local197;
									vInterpolator += local227;
									wInterpolator += local257;
								}
							}
							drawTexturedScanline(SoftwareRenderer.pixels, texels, yB, arg4 >> 16, xC >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAB;
							arg4 += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local197;
							vInterpolator += local227;
							wInterpolator += local257;
						}
					}
				}
			}
		} else if (yC < height) {
			if (yA > height) {
				yA = height;
			}
			if (yB > height) {
				yB = height;
			}
			colorC = (colorC << 9) + colorGradientX - colorGradientX * xC;
			if (yA < yB) {
				arg4 = xC <<= 0x10;
				if (yC < 0) {
					arg4 -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorC -= colorGradientY * yC;
					yC = 0;
				}
				xA <<= 0x10;
				if (yA < 0) {
					xA -= xStepAB * yA;
					yA = 0;
				}
				yOffset = yC - centerY;
				uInterpolator += local197 * yOffset;
				vInterpolator += local227 * yOffset;
				wInterpolator += local257 * yOffset;
				if (xStepBC < xStepAC) {
					yB -= yA;
					yA -= yC;
					yC = offsets[yC];
					while (true) {
						yA--;
						if (yA < 0) {
							while (true) {
								yB--;
								if (yB < 0) {
									return;
								}
								drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, arg4 >> 16, xA >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								arg4 += xStepBC;
								xA += xStepAB;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local197;
								vInterpolator += local227;
								wInterpolator += local257;
							}
						}
						drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, arg4 >> 16, xC >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						arg4 += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local197;
						vInterpolator += local227;
						wInterpolator += local257;
					}
				} else {
					yB -= yA;
					yA -= yC;
					yC = offsets[yC];
					while (true) {
						yA--;
						if (yA < 0) {
							while (true) {
								yB--;
								if (yB < 0) {
									return;
								}
								drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, xA >> 16, arg4 >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								arg4 += xStepBC;
								xA += xStepAB;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local197;
								vInterpolator += local227;
								wInterpolator += local257;
							}
						}
						drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, xC >> 16, arg4 >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						arg4 += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local197;
						vInterpolator += local227;
						wInterpolator += local257;
					}
				}
			} else {
				xA = xC <<= 0x10;
				if (yC < 0) {
					xA -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorC -= colorGradientY * yC;
					yC = 0;
				}
				arg4 <<= 0x10;
				if (yB < 0) {
					arg4 -= xStepAB * yB;
					yB = 0;
				}
				yOffset = yC - centerY;
				uInterpolator += local197 * yOffset;
				vInterpolator += local227 * yOffset;
				wInterpolator += local257 * yOffset;
				if (xStepBC < xStepAC) {
					yA -= yB;
					yB -= yC;
					yC = offsets[yC];
					while (true) {
						yB--;
						if (yB < 0) {
							while (true) {
								yA--;
								if (yA < 0) {
									return;
								}
								drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, arg4 >> 16, xC >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								arg4 += xStepAB;
								xC += xStepAC;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local197;
								vInterpolator += local227;
								wInterpolator += local257;
							}
						}
						drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, xA >> 16, xC >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						xA += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local197;
						vInterpolator += local227;
						wInterpolator += local257;
					}
				} else {
					yA -= yB;
					yB -= yC;
					yC = offsets[yC];
					while (true) {
						yB--;
						if (yB < 0) {
							while (true) {
								yA--;
								if (yA < 0) {
									return;
								}
								drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, xC >> 16, arg4 >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								arg4 += xStepAB;
								xC += xStepAC;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local197;
								vInterpolator += local227;
								wInterpolator += local257;
							}
						}
						drawTexturedScanline(SoftwareRenderer.pixels, texels, yC, xC >> 16, xA >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						xA += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local197;
						vInterpolator += local227;
						wInterpolator += local257;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "([I[IIIIIIIIIIIIII)V")
	private static void drawTexturedScanline(@OriginalArg(0) int[] pixelBuffer, @OriginalArg(1) int[] textureData, @OriginalArg(4) int pixelOffset, @OriginalArg(5) int leftX, @OriginalArg(6) int rightX, @OriginalArg(7) int color, @OriginalArg(8) int colorStep, @OriginalArg(9) int uInterpolator, @OriginalArg(10) int vInterpolator, @OriginalArg(11) int wInterpolator, @OriginalArg(12) int uStep, @OriginalArg(13) int vStep, @OriginalArg(14) int wStep) {
		if (testX) {
			if (rightX > width) {
				rightX = width;
			}
			if (leftX < 0) {
				leftX = 0;
			}
		}
		if (leftX >= rightX) {
			return;
		}
		pixelOffset += leftX;
		color += colorStep * leftX;
		@Pc(28) int spanWidth = rightX - leftX;
		@Pc(140) int lightness;
		@Pc(128) int textureStep;
		@Pc(62) int uStart;
		@Pc(66) int vStart;
		@Pc(99) int uEnd;
		@Pc(103) int vEnd;
		@Pc(56) int wDivisor;
		@Pc(34) int xOffset;
		@Pc(154) int texelColor;
		@Pc(114) int textureCoord;
		@Pc(157) int nextPixelOffset;
		@Pc(136) int colorStepBatch;
		@Pc(40) int uPerspective;
		@Pc(46) int vPerspective;
		@Pc(52) int wPerspective;
		if (!lowDetail) {
			xOffset = leftX - centerX;
			uPerspective = uInterpolator + uStep * xOffset;
			vPerspective = vInterpolator + vStep * xOffset;
			wPerspective = wInterpolator + wStep * xOffset;
			wDivisor = wPerspective >> 14;
			if (wDivisor == 0) {
				uStart = 0;
				vStart = 0;
			} else {
				uStart = uPerspective / wDivisor;
				vStart = vPerspective / wDivisor;
			}
			uPerspective += uStep * spanWidth;
			vPerspective += vStep * spanWidth;
			wPerspective += wStep * spanWidth;
			wDivisor = wPerspective >> 14;
			if (wDivisor == 0) {
				uEnd = 0;
				vEnd = 0;
			} else {
				uEnd = uPerspective / wDivisor;
				vEnd = vPerspective / wDivisor;
			}
			textureCoord = (uStart << 18) + vStart;
			textureStep = ((uEnd - uStart) / spanWidth << 18) + (vEnd - vStart) / spanWidth;
			spanWidth >>= 0x3;
			colorStepBatch = colorStep << 3;
			lightness = color >> 8;
			if (opaque) {
				if (spanWidth > 0) {
					do {
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						nextPixelOffset = pixelOffset + 1;
						pixelBuffer[pixelOffset] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelOffset = nextPixelOffset + 1;
						pixelBuffer[nextPixelOffset] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						color += colorStepBatch;
						lightness = color >> 8;
						spanWidth--;
					} while (spanWidth > 0);
				}
				spanWidth = rightX - leftX & 0x7;
				if (spanWidth > 0) {
					do {
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[pixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						spanWidth--;
					} while (spanWidth > 0);
				}
			} else {
				if (spanWidth > 0) {
					do {
						@Pc(1305) int local1305;
						if ((local1305 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[pixelOffset] = ((local1305 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1305 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset = pixelOffset + 1;
						textureCoord += textureStep;
						@Pc(1342) int local1342;
						if ((local1342 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1342 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1342 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1379) int local1379;
						if ((local1379 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1379 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1379 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1416) int local1416;
						if ((local1416 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1416 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1416 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1453) int local1453;
						if ((local1453 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1453 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1453 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1490) int local1490;
						if ((local1490 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1490 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1490 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1527) int local1527;
						if ((local1527 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1527 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1527 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1564) int local1564;
						if ((local1564 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((local1564 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1564 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						pixelOffset = nextPixelOffset + 1;
						textureCoord += textureStep;
						color += colorStepBatch;
						lightness = color >> 8;
						spanWidth--;
					} while (spanWidth > 0);
				}
				spanWidth = rightX - leftX & 0x7;
				if (spanWidth > 0) {
					do {
						@Pc(1620) int local1620;
						if ((local1620 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[pixelOffset] = ((local1620 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1620 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						pixelOffset++;
						textureCoord += textureStep;
						spanWidth--;
					} while (spanWidth > 0);
				}
			}
			return;
		}
		xOffset = leftX - centerX;
		uPerspective = uInterpolator + uStep * xOffset;
		vPerspective = vInterpolator + vStep * xOffset;
		wPerspective = wInterpolator + wStep * xOffset;
		wDivisor = wPerspective >> 12;
		if (wDivisor == 0) {
			uStart = 0;
			vStart = 0;
		} else {
			uStart = uPerspective / wDivisor;
			vStart = vPerspective / wDivisor;
		}
		uPerspective += uStep * spanWidth;
		vPerspective += vStep * spanWidth;
		wPerspective += wStep * spanWidth;
		wDivisor = wPerspective >> 12;
		if (wDivisor == 0) {
			uEnd = 0;
			vEnd = 0;
		} else {
			uEnd = uPerspective / wDivisor;
			vEnd = vPerspective / wDivisor;
		}
		textureCoord = (uStart << 20) + vStart;
		textureStep = ((uEnd - uStart) / spanWidth << 20) + (vEnd - vStart) / spanWidth;
		spanWidth >>= 0x3;
		colorStepBatch = colorStep << 3;
		lightness = color >> 8;
		if (opaque) {
			if (spanWidth > 0) {
				do {
					texelColor = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					nextPixelOffset = pixelOffset + 1;
					pixelBuffer[pixelOffset] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(189) int local189 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(192) int local192 = nextPixelOffset + 1;
					pixelBuffer[nextPixelOffset] = ((local189 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local189 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(224) int local224 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(227) int local227 = local192 + 1;
					pixelBuffer[local192] = ((local224 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local224 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(259) int local259 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(262) int local262 = local227 + 1;
					pixelBuffer[local227] = ((local259 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local259 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(294) int local294 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(297) int local297 = local262 + 1;
					pixelBuffer[local262] = ((local294 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local294 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(329) int local329 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(332) int local332 = local297 + 1;
					pixelBuffer[local297] = ((local329 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local329 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(364) int local364 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(367) int local367 = local332 + 1;
					pixelBuffer[local332] = ((local364 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local364 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(399) int local399 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					pixelOffset = local367 + 1;
					pixelBuffer[local367] = ((local399 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local399 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					color += colorStepBatch;
					lightness = color >> 8;
					spanWidth--;
				} while (spanWidth > 0);
			}
			spanWidth = rightX - leftX & 0x7;
			if (spanWidth > 0) {
				do {
					texelColor = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					pixelBuffer[pixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					spanWidth--;
				} while (spanWidth > 0);
			}
			return;
		}
		if (spanWidth > 0) {
			do {
				@Pc(495) int local495;
				if ((local495 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[pixelOffset] = ((local495 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local495 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset = pixelOffset + 1;
				textureCoord += textureStep;
				@Pc(532) int local532;
				if ((local532 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local532 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local532 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(569) int local569;
				if ((local569 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local569 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local569 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(606) int local606;
				if ((local606 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local606 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local606 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(643) int local643;
				if ((local643 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local643 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local643 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(680) int local680;
				if ((local680 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local680 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local680 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(717) int local717;
				if ((local717 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local717 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local717 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(754) int local754;
				if ((local754 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local754 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local754 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				pixelOffset = nextPixelOffset + 1;
				textureCoord += textureStep;
				color += colorStepBatch;
				lightness = color >> 8;
				spanWidth--;
			} while (spanWidth > 0);
		}
		spanWidth = rightX - leftX & 0x7;
		if (spanWidth <= 0) {
			return;
		}
		do {
			@Pc(810) int local810;
			if ((local810 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
				pixelBuffer[pixelOffset] = ((local810 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local810 & 0xFF00) * lightness & 0xFF0000) >> 8;
			}
			pixelOffset++;
			textureCoord += textureStep;
			spanWidth--;
		} while (spanWidth > 0);
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([IIIIIIII)V")
	private static void drawGouraudScanline(@OriginalArg(0) int[] pixelBuffer, @OriginalArg(1) int pixelOffset, @OriginalArg(4) int leftX, @OriginalArg(5) int rightX, @OriginalArg(6) int color, @OriginalArg(7) int colorStep) {
		if (testX) {
			if (rightX > width) {
				rightX = width;
			}
			if (leftX < 0) {
				leftX = 0;
			}
		}
		if (leftX >= rightX) {
			return;
		}
		pixelOffset += leftX;
		color += colorStep * leftX;
		@Pc(98) int sourceAlpha;
		@Pc(102) int destAlpha;
		@Pc(138) int destPixel;
		@Pc(32) int spanWidth;
		@Pc(46) int sourceColor;
		if (!jagged) {
			spanWidth = rightX - leftX;
			if (alpha == 0) {
				do {
					pixelBuffer[pixelOffset++] = palette[color >> 8];
					color += colorStep;
					spanWidth--;
				} while (spanWidth > 0);
			} else {
				sourceAlpha = alpha;
				destAlpha = 256 - alpha;
				do {
					sourceColor = palette[color >> 8];
					color += colorStep;
					@Pc(379) int blendedSource = ((sourceColor & 0xFF00FF) * destAlpha >> 8 & 0xFF00FF) + ((sourceColor & 0xFF00) * destAlpha >> 8 & 0xFF00);
					destPixel = pixelBuffer[pixelOffset];
					pixelBuffer[pixelOffset++] = blendedSource + ((destPixel & 0xFF00FF) * sourceAlpha >> 8 & 0xFF00FF) + ((destPixel & 0xFF00) * sourceAlpha >> 8 & 0xFF00);
					spanWidth--;
				} while (spanWidth > 0);
			}
			return;
		}
		spanWidth = rightX - leftX >> 2;
		@Pc(36) int colorStepBatch = colorStep << 2;
		@Pc(53) int nextOffset1;
		if (alpha == 0) {
			if (spanWidth > 0) {
				do {
					sourceColor = palette[color >> 8];
					color += colorStepBatch;
					nextOffset1 = pixelOffset + 1;
					pixelBuffer[pixelOffset] = sourceColor;
					@Pc(58) int nextOffset2 = nextOffset1 + 1;
					pixelBuffer[nextOffset1] = sourceColor;
					@Pc(63) int nextOffset3 = nextOffset2 + 1;
					pixelBuffer[nextOffset2] = sourceColor;
					pixelOffset = nextOffset3 + 1;
					pixelBuffer[nextOffset3] = sourceColor;
					spanWidth--;
				} while (spanWidth > 0);
			}
			spanWidth = rightX - leftX & 0x3;
			if (spanWidth > 0) {
				sourceColor = palette[color >> 8];
				do {
					pixelBuffer[pixelOffset++] = sourceColor;
					spanWidth--;
				} while (spanWidth > 0);
			}
			return;
		}
		sourceAlpha = alpha;
		destAlpha = 256 - alpha;
		if (spanWidth > 0) {
			do {
				sourceColor = palette[color >> 8];
				color += colorStepBatch;
				sourceColor = ((sourceColor & 0xFF00FF) * destAlpha >> 8 & 0xFF00FF) + ((sourceColor & 0xFF00) * destAlpha >> 8 & 0xFF00);
				destPixel = pixelBuffer[pixelOffset];
				nextOffset1 = pixelOffset + 1;
				pixelBuffer[pixelOffset] = sourceColor + ((destPixel & 0xFF00FF) * sourceAlpha >> 8 & 0xFF00FF) + ((destPixel & 0xFF00) * sourceAlpha >> 8 & 0xFF00);
				destPixel = pixelBuffer[nextOffset1];
				pixelBuffer[nextOffset1++] = sourceColor + ((destPixel & 0xFF00FF) * sourceAlpha >> 8 & 0xFF00FF) + ((destPixel & 0xFF00) * sourceAlpha >> 8 & 0xFF00);
				destPixel = pixelBuffer[nextOffset1];
				pixelBuffer[nextOffset1++] = sourceColor + ((destPixel & 0xFF00FF) * sourceAlpha >> 8 & 0xFF00FF) + ((destPixel & 0xFF00) * sourceAlpha >> 8 & 0xFF00);
				destPixel = pixelBuffer[nextOffset1];
				pixelOffset = nextOffset1 + 1;
				pixelBuffer[nextOffset1] = sourceColor + ((destPixel & 0xFF00FF) * sourceAlpha >> 8 & 0xFF00FF) + ((destPixel & 0xFF00) * sourceAlpha >> 8 & 0xFF00);
				spanWidth--;
			} while (spanWidth > 0);
		}
		spanWidth = rightX - leftX & 0x3;
		if (spanWidth <= 0) {
			return;
		}
		sourceColor = palette[color >> 8];
		sourceColor = ((sourceColor & 0xFF00FF) * destAlpha >> 8 & 0xFF00FF) + ((sourceColor & 0xFF00) * destAlpha >> 8 & 0xFF00);
		do {
			destPixel = pixelBuffer[pixelOffset];
			pixelBuffer[pixelOffset++] = sourceColor + ((destPixel & 0xFF00FF) * sourceAlpha >> 8 & 0xFF00FF) + ((destPixel & 0xFF00) * sourceAlpha >> 8 & 0xFF00);
			spanWidth--;
		} while (spanWidth > 0);
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(IIIIIIIII)V")
	public static void fillGouraudTriangle(@OriginalArg(0) int yA, @OriginalArg(1) int yB, @OriginalArg(2) int yC, @OriginalArg(3) int xA, @OriginalArg(4) int xB, @OriginalArg(5) int xC, @OriginalArg(6) int colorA, @OriginalArg(7) int colorB, @OriginalArg(8) int colorC) {
		@Pc(3) int dxBA = xB - xA;
		@Pc(7) int dyBA = yB - yA;
		@Pc(11) int dxCA = xC - xA;
		@Pc(15) int dyCA = yC - yA;
		@Pc(19) int colorDiffBA = colorB - colorA;
		@Pc(23) int colorDiffCA = colorC - colorA;
		@Pc(36) int xStepBC;
		if (yC == yB) {
			xStepBC = 0;
		} else {
			xStepBC = (xC - xB << 16) / (yC - yB);
		}
		@Pc(48) int xStepAB;
		if (yB == yA) {
			xStepAB = 0;
		} else {
			xStepAB = (dxBA << 16) / dyBA;
		}
		@Pc(60) int xStepAC;
		if (yC == yA) {
			xStepAC = 0;
		} else {
			xStepAC = (dxCA << 16) / dyCA;
		}
		@Pc(71) int triangleArea = dxBA * dyCA - dxCA * dyBA;
		if (triangleArea == 0) {
			return;
		}
		@Pc(86) int colorGradientX = (colorDiffBA * dyCA - colorDiffCA * dyBA << 8) / triangleArea;
		@Pc(98) int colorGradientY = (colorDiffCA * dxBA - colorDiffBA * dxCA << 8) / triangleArea;
		if (yA <= yB && yA <= yC) {
			if (yA < height) {
				if (yB > height) {
					yB = height;
				}
				if (yC > height) {
					yC = height;
				}
				colorA = (colorA << 8) + colorGradientX - colorGradientX * xA;
				if (yB < yC) {
					xC = xA <<= 0x10;
					if (yA < 0) {
						xC -= xStepAC * yA;
						xA -= xStepAB * yA;
						colorA -= colorGradientY * yA;
						yA = 0;
					}
					xB <<= 0x10;
					if (yB < 0) {
						xB -= xStepBC * yB;
						yB = 0;
					}
					if ((yA == yB || xStepAC >= xStepAB) && (yA != yB || xStepAC <= xStepBC)) {
						yC -= yB;
						yB -= yA;
						yA = offsets[yA];
						while (true) {
							yB--;
							if (yB < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yA, xB >> 16, xC >> 16, colorA, colorGradientX);
									xC += xStepAC;
									xB += xStepBC;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yA, xA >> 16, xC >> 16, colorA, colorGradientX);
							xC += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
						}
					} else {
						yC -= yB;
						yB -= yA;
						yA = offsets[yA];
						while (true) {
							yB--;
							if (yB < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yA, xC >> 16, xB >> 16, colorA, colorGradientX);
									xC += xStepAC;
									xB += xStepBC;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yA, xC >> 16, xA >> 16, colorA, colorGradientX);
							xC += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
						}
					}
				} else {
					xB = xA <<= 0x10;
					if (yA < 0) {
						xB -= xStepAC * yA;
						xA -= xStepAB * yA;
						colorA -= colorGradientY * yA;
						yA = 0;
					}
					xC <<= 0x10;
					if (yC < 0) {
						xC -= xStepBC * yC;
						yC = 0;
					}
					if (yA != yC && xStepAC < xStepAB || yA == yC && xStepBC > xStepAB) {
						yB -= yC;
						yC -= yA;
						yA = offsets[yA];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yB--;
									if (yB < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yA, xC >> 16, xA >> 16, colorA, colorGradientX);
									xC += xStepBC;
									xA += xStepAB;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yA, xB >> 16, xA >> 16, colorA, colorGradientX);
							xB += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
						}
					} else {
						yB -= yC;
						yC -= yA;
						yA = offsets[yA];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yB--;
									if (yB < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yA, xA >> 16, xC >> 16, colorA, colorGradientX);
									xC += xStepBC;
									xA += xStepAB;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yA, xA >> 16, xB >> 16, colorA, colorGradientX);
							xB += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
						}
					}
				}
			}
		} else if (yB <= yC) {
			if (yB < height) {
				if (yC > height) {
					yC = height;
				}
				if (yA > height) {
					yA = height;
				}
				colorB = (colorB << 8) + colorGradientX - colorGradientX * xB;
				if (yC < yA) {
					xA = xB <<= 0x10;
					if (yB < 0) {
						xA -= xStepAB * yB;
						xB -= xStepBC * yB;
						colorB -= colorGradientY * yB;
						yB = 0;
					}
					xC <<= 0x10;
					if (yC < 0) {
						xC -= xStepAC * yC;
						yC = 0;
					}
					if ((yB == yC || xStepAB >= xStepBC) && (yB != yC || xStepAB <= xStepAC)) {
						yA -= yC;
						yC -= yB;
						yB = offsets[yB];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yA--;
									if (yA < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yB, xC >> 16, xA >> 16, colorB, colorGradientX);
									xA += xStepAB;
									xC += xStepAC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yB, xB >> 16, xA >> 16, colorB, colorGradientX);
							xA += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
						}
					} else {
						yA -= yC;
						yC -= yB;
						yB = offsets[yB];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yA--;
									if (yA < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yB, xA >> 16, xC >> 16, colorB, colorGradientX);
									xA += xStepAB;
									xC += xStepAC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yB, xA >> 16, xB >> 16, colorB, colorGradientX);
							xA += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
						}
					}
				} else {
					xC = xB <<= 0x10;
					if (yB < 0) {
						xC -= xStepAB * yB;
						xB -= xStepBC * yB;
						colorB -= colorGradientY * yB;
						yB = 0;
					}
					xA <<= 0x10;
					if (yA < 0) {
						xA -= xStepAC * yA;
						yA = 0;
					}
					if (xStepAB < xStepBC) {
						yC -= yA;
						yA -= yB;
						yB = offsets[yB];
						while (true) {
							yA--;
							if (yA < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yB, xA >> 16, xB >> 16, colorB, colorGradientX);
									xA += xStepAC;
									xB += xStepBC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yB, xC >> 16, xB >> 16, colorB, colorGradientX);
							xC += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
						}
					} else {
						yC -= yA;
						yA -= yB;
						yB = offsets[yB];
						while (true) {
							yA--;
							if (yA < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawGouraudScanline(SoftwareRenderer.pixels, yB, xB >> 16, xA >> 16, colorB, colorGradientX);
									xA += xStepAC;
									xB += xStepBC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
								}
							}
							drawGouraudScanline(SoftwareRenderer.pixels, yB, xB >> 16, xC >> 16, colorB, colorGradientX);
							xC += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
						}
					}
				}
			}
		} else if (yC < height) {
			if (yA > height) {
				yA = height;
			}
			if (yB > height) {
				yB = height;
			}
			colorC = (colorC << 8) + colorGradientX - colorGradientX * xC;
			if (yA < yB) {
				xB = xC <<= 0x10;
				if (yC < 0) {
					xB -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorC -= colorGradientY * yC;
					yC = 0;
				}
				xA <<= 0x10;
				if (yA < 0) {
					xA -= xStepAB * yA;
					yA = 0;
				}
				if (xStepBC < xStepAC) {
					yB -= yA;
					yA -= yC;
					yC = offsets[yC];
					while (true) {
						yA--;
						if (yA < 0) {
							while (true) {
								yB--;
								if (yB < 0) {
									return;
								}
								drawGouraudScanline(SoftwareRenderer.pixels, yC, xB >> 16, xA >> 16, colorC, colorGradientX);
								xB += xStepBC;
								xA += xStepAB;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
							}
						}
						drawGouraudScanline(SoftwareRenderer.pixels, yC, xB >> 16, xC >> 16, colorC, colorGradientX);
						xB += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
					}
				} else {
					yB -= yA;
					yA -= yC;
					yC = offsets[yC];
					while (true) {
						yA--;
						if (yA < 0) {
							while (true) {
								yB--;
								if (yB < 0) {
									return;
								}
								drawGouraudScanline(SoftwareRenderer.pixels, yC, xA >> 16, xB >> 16, colorC, colorGradientX);
								xB += xStepBC;
								xA += xStepAB;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
							}
						}
						drawGouraudScanline(SoftwareRenderer.pixels, yC, xC >> 16, xB >> 16, colorC, colorGradientX);
						xB += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
					}
				}
			} else {
				xA = xC <<= 0x10;
				if (yC < 0) {
					xA -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorC -= colorGradientY * yC;
					yC = 0;
				}
				xB <<= 0x10;
				if (yB < 0) {
					xB -= xStepAB * yB;
					yB = 0;
				}
				if (xStepBC < xStepAC) {
					yA -= yB;
					yB -= yC;
					yC = offsets[yC];
					while (true) {
						yB--;
						if (yB < 0) {
							while (true) {
								yA--;
								if (yA < 0) {
									return;
								}
								drawGouraudScanline(SoftwareRenderer.pixels, yC, xB >> 16, xC >> 16, colorC, colorGradientX);
								xB += xStepAB;
								xC += xStepAC;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
							}
						}
						drawGouraudScanline(SoftwareRenderer.pixels, yC, xA >> 16, xC >> 16, colorC, colorGradientX);
						xA += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
					}
				} else {
					yA -= yB;
					yB -= yC;
					yC = offsets[yC];
					while (true) {
						yB--;
						if (yB < 0) {
							while (true) {
								yA--;
								if (yA < 0) {
									return;
								}
								drawGouraudScanline(SoftwareRenderer.pixels, yC, xC >> 16, xB >> 16, colorC, colorGradientX);
								xB += xStepAB;
								xC += xStepAC;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
							}
						}
						drawGouraudScanline(SoftwareRenderer.pixels, yC, xC >> 16, xA >> 16, colorC, colorGradientX);
						xA += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(F)V")
	public static void setBrightness(@OriginalArg(0) float brightness) {
		randBrightness(brightness);
		calculateBrightness();
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "c", descriptor = "(II)V")
	private static void calculateBrightness() {
		@Pc(3) int paletteIndex = 0;
		for (@Pc(5) int hslIndex = 0; hslIndex < 512; hslIndex++) {
			@Pc(17) double hue = (double) (hslIndex >> 3) / 64.0D + 0.0078125D;
			@Pc(26) double saturation = (double) (hslIndex & 0x7) / 8.0D + 0.0625D;
			for (@Pc(28) int lightness = 0; lightness < 128; lightness++) {
				@Pc(36) double lightnessNormalized = (double) lightness / 128.0D;
				@Pc(38) double red = lightnessNormalized;
				@Pc(40) double green = lightnessNormalized;
				@Pc(42) double blue = lightnessNormalized;
				if (saturation != 0.0D) {
					@Pc(56) double q;
					if (lightnessNormalized < 0.5D) {
						q = lightnessNormalized * (saturation + 1.0D);
					} else {
						q = lightnessNormalized + saturation - lightnessNormalized * saturation;
					}
					@Pc(71) double p = lightnessNormalized * 2.0D - q;
					@Pc(75) double hueRed = hue + 0.3333333333333333D;
					if (hueRed > 1.0D) {
						hueRed--;
					}
					@Pc(89) double hueBlue = hue - 0.3333333333333333D;
					if (hueBlue < 0.0D) {
						hueBlue++;
					}
					if (hueRed * 6.0D < 1.0D) {
						red = p + (q - p) * 6.0D * hueRed;
					} else if (hueRed * 2.0D < 1.0D) {
						red = q;
					} else if (hueRed * 3.0D < 2.0D) {
						red = p + (q - p) * (0.6666666666666666D - hueRed) * 6.0D;
					} else {
						red = p;
					}
					if (hue * 6.0D < 1.0D) {
						green = p + (q - p) * 6.0D * hue;
					} else if (hue * 2.0D < 1.0D) {
						green = q;
					} else if (hue * 3.0D < 2.0D) {
						green = p + (q - p) * (0.6666666666666666D - hue) * 6.0D;
					} else {
						green = p;
					}
					if (hueBlue * 6.0D < 1.0D) {
						blue = p + (q - p) * 6.0D * hueBlue;
					} else if (hueBlue * 2.0D < 1.0D) {
						blue = q;
					} else if (hueBlue * 3.0D < 2.0D) {
						blue = p + (q - p) * (0.6666666666666666D - hueBlue) * 6.0D;
					} else {
						blue = p;
					}
				}
				red = Math.pow(red, (double) brightness);
				green = Math.pow(green, (double) brightness);
				blue = Math.pow(blue, (double) brightness);
				@Pc(258) int redInt = (int) (red * 256.0D);
				@Pc(263) int greenInt = (int) (green * 256.0D);
				@Pc(268) int blueInt = (int) (blue * 256.0D);
				@Pc(278) int rgbColor = (redInt << 16) + (greenInt << 8) + blueInt;
				if (rgbColor == 0) {
					rgbColor = 1;
				}
				palette[paletteIndex++] = rgbColor;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "(F)V")
	private static void randBrightness(@OriginalArg(0) float baseBrightness) {
		brightness = baseBrightness;
		brightness = (float) ((double) brightness + Math.random() * 0.03D - 0.015D);
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "(IIIIIIIIIIIIIIIIIII)V")
	public static void fillTexturedAlphaTriangle(@OriginalArg(0) int yA, @OriginalArg(1) int yB, @OriginalArg(2) int yC, @OriginalArg(3) int xA, @OriginalArg(4) int xB, @OriginalArg(5) int xC, @OriginalArg(6) int colorA, @OriginalArg(7) int colorB, @OriginalArg(8) int colorC, @OriginalArg(9) int uA, @OriginalArg(10) int vA, @OriginalArg(11) int wA, @OriginalArg(12) int uB, @OriginalArg(13) int vB, @OriginalArg(14) int wB, @OriginalArg(15) int uC, @OriginalArg(16) int vC, @OriginalArg(17) int wC, @OriginalArg(18) int textureId) {
		@Pc(5) int[] texels = textureProvider.method3232(textureId, brightness);
		@Pc(15) int dxBA;
		if (texels == null || alpha > 10) {
			dxBA = textureProvider.getAverageColor(textureId);
			textureHasTransparency = true;
			fillGouraudTriangle(yA, yB, yC, xA, xB, xC, ColorUtils.multiplyLightness(dxBA, colorA), ColorUtils.multiplyLightness(dxBA, colorB), ColorUtils.multiplyLightness(dxBA, colorC));
			return;
		}
		lowDetail = textureProvider.isLowDetail(textureId);
		opaque = textureProvider.isOpaque(textureId);
		dxBA = xB - xA;
		@Pc(52) int dyBA = yB - yA;
		@Pc(56) int dxCA = xC - xA;
		@Pc(60) int dyCA = yC - yA;
		@Pc(64) int colorDiffBA = colorB - colorA;
		@Pc(68) int colorDiffCA = colorC - colorA;
		@Pc(70) int xStepAB = 0;
		if (yB != yA) {
			xStepAB = (xB - xA << 16) / (yB - yA);
		}
		@Pc(85) int xStepBC = 0;
		if (yC != yB) {
			xStepBC = (xC - xB << 16) / (yC - yB);
		}
		@Pc(100) int xStepAC = 0;
		if (yC != yA) {
			xStepAC = (xA - xC << 16) / (yA - yC);
		}
		@Pc(121) int triangleArea = dxBA * dyCA - dxCA * dyBA;
		if (triangleArea == 0) {
			return;
		}
		@Pc(136) int colorGradientX = (colorDiffBA * dyCA - colorDiffCA * dyBA << 9) / triangleArea;
		@Pc(148) int colorGradientY = (colorDiffCA * dxBA - colorDiffBA * dxCA << 9) / triangleArea;
		@Pc(152) int duAB = uA - vA;
		@Pc(156) int duAC = uB - vB;
		@Pc(160) int duBC = uC - vC;
		@Pc(164) int dvAB = wA - uA;
		@Pc(168) int dvAC = wB - uB;
		@Pc(172) int dvBC = wC - uC;
		@Pc(182) int uInterpolator = dvAB * uB - dvAC * uA << 14;
		@Pc(192) int uStep = dvAC * uC - dvBC * uB << 8;
		@Pc(202) int local202 = dvBC * uA - dvAB * uC << 5;
		@Pc(212) int vInterpolator = duAB * uB - duAC * uA << 14;
		@Pc(222) int vStep = duAC * uC - duBC * uB << 8;
		@Pc(232) int local232 = duBC * uA - duAB * uC << 5;
		@Pc(242) int wInterpolator = duAC * dvAB - duAB * dvAC << 14;
		@Pc(252) int wStep = duBC * dvAC - duAC * dvBC << 8;
		@Pc(262) int local262 = duAB * dvBC - duBC * dvAB << 5;
		@Pc(341) int yOffset;
		if (yA <= yB && yA <= yC) {
			if (yA < height) {
				if (yB > height) {
					yB = height;
				}
				if (yC > height) {
					yC = height;
				}
				colorA = (colorA << 9) + colorGradientX - colorGradientX * xA;
				if (yB < yC) {
					xC = xA <<= 0x10;
					if (yA < 0) {
						xC -= xStepAC * yA;
						xA -= xStepAB * yA;
						colorA -= colorGradientY * yA;
						yA = 0;
					}
					xB <<= 0x10;
					if (yB < 0) {
						xB -= xStepBC * yB;
						yB = 0;
					}
					yOffset = yA - centerY;
					uInterpolator += local202 * yOffset;
					vInterpolator += local232 * yOffset;
					wInterpolator += local262 * yOffset;
					if (yA != yB && xStepAC < xStepAB || yA == yB && xStepAC > xStepBC) {
						yC -= yB;
						yB -= yA;
						yA = offsets[yA];
						while (true) {
							yB--;
							if (yB < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xC >> 16, xB >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepAC;
									xB += xStepBC;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xC >> 16, xA >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					} else {
						yC -= yB;
						yB -= yA;
						yA = offsets[yA];
						while (true) {
							yB--;
							if (yB < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xB >> 16, xC >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepAC;
									xB += xStepBC;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xA >> 16, xC >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					}
				} else {
					xB = xA <<= 0x10;
					if (yA < 0) {
						xB -= xStepAC * yA;
						xA -= xStepAB * yA;
						colorA -= colorGradientY * yA;
						yA = 0;
					}
					xC <<= 0x10;
					if (yC < 0) {
						xC -= xStepBC * yC;
						yC = 0;
					}
					yOffset = yA - centerY;
					uInterpolator += local202 * yOffset;
					vInterpolator += local232 * yOffset;
					wInterpolator += local262 * yOffset;
					if ((yA == yC || xStepAC >= xStepAB) && (yA != yC || xStepBC <= xStepAB)) {
						yB -= yC;
						yC -= yA;
						yA = offsets[yA];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yB--;
									if (yB < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xA >> 16, xC >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepBC;
									xA += xStepAB;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xA >> 16, xB >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xB += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					} else {
						yB -= yC;
						yC -= yA;
						yA = offsets[yA];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yB--;
									if (yB < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xC >> 16, xA >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xC += xStepBC;
									xA += xStepAB;
									colorA += colorGradientY;
									yA += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yA, xB >> 16, xA >> 16, colorA, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xB += xStepAC;
							xA += xStepAB;
							colorA += colorGradientY;
							yA += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					}
				}
			}
		} else if (yB <= yC) {
			if (yB < height) {
				if (yC > height) {
					yC = height;
				}
				if (yA > height) {
					yA = height;
				}
				colorB = (colorB << 9) + colorGradientX - colorGradientX * xB;
				if (yC < yA) {
					xA = xB <<= 0x10;
					if (yB < 0) {
						xA -= xStepAB * yB;
						xB -= xStepBC * yB;
						colorB -= colorGradientY * yB;
						yB = 0;
					}
					xC <<= 0x10;
					if (yC < 0) {
						xC -= xStepAC * yC;
						yC = 0;
					}
					yOffset = yB - centerY;
					uInterpolator += local202 * yOffset;
					vInterpolator += local232 * yOffset;
					wInterpolator += local262 * yOffset;
					if (yB != yC && xStepAB < xStepBC || yB == yC && xStepAB > xStepAC) {
						yA -= yC;
						yC -= yB;
						yB = offsets[yB];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yA--;
									if (yA < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xA >> 16, xC >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAB;
									xC += xStepAC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xA >> 16, xB >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xA += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					} else {
						yA -= yC;
						yC -= yB;
						yB = offsets[yB];
						while (true) {
							yC--;
							if (yC < 0) {
								while (true) {
									yA--;
									if (yA < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xC >> 16, xA >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAB;
									xC += xStepAC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xB >> 16, xA >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xA += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					}
				} else {
					xC = xB <<= 0x10;
					if (yB < 0) {
						xC -= xStepAB * yB;
						xB -= xStepBC * yB;
						colorB -= colorGradientY * yB;
						yB = 0;
					}
					xA <<= 0x10;
					if (yA < 0) {
						xA -= xStepAC * yA;
						yA = 0;
					}
					yOffset = yB - centerY;
					uInterpolator += local202 * yOffset;
					vInterpolator += local232 * yOffset;
					wInterpolator += local262 * yOffset;
					if (xStepAB < xStepBC) {
						yC -= yA;
						yA -= yB;
						yB = offsets[yB];
						while (true) {
							yA--;
							if (yA < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xA >> 16, xB >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAC;
									xB += xStepBC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xC >> 16, xB >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					} else {
						yC -= yA;
						yA -= yB;
						yB = offsets[yB];
						while (true) {
							yA--;
							if (yA < 0) {
								while (true) {
									yC--;
									if (yC < 0) {
										return;
									}
									drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xB >> 16, xA >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
									xA += xStepAC;
									xB += xStepBC;
									colorB += colorGradientY;
									yB += SoftwareRenderer.width;
									uInterpolator += local202;
									vInterpolator += local232;
									wInterpolator += local262;
								}
							}
							drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yB, xB >> 16, xC >> 16, colorB, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
							xC += xStepAB;
							xB += xStepBC;
							colorB += colorGradientY;
							yB += SoftwareRenderer.width;
							uInterpolator += local202;
							vInterpolator += local232;
							wInterpolator += local262;
						}
					}
				}
			}
		} else if (yC < height) {
			if (yA > height) {
				yA = height;
			}
			if (yB > height) {
				yB = height;
			}
			colorC = (colorC << 9) + colorGradientX - colorGradientX * xC;
			if (yA < yB) {
				xB = xC <<= 0x10;
				if (yC < 0) {
					xB -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorC -= colorGradientY * yC;
					yC = 0;
				}
				xA <<= 0x10;
				if (yA < 0) {
					xA -= xStepAB * yA;
					yA = 0;
				}
				yOffset = yC - centerY;
				uInterpolator += local202 * yOffset;
				vInterpolator += local232 * yOffset;
				wInterpolator += local262 * yOffset;
				if (xStepBC < xStepAC) {
					yB -= yA;
					yA -= yC;
					yC = offsets[yC];
					while (true) {
						yA--;
						if (yA < 0) {
							while (true) {
								yB--;
								if (yB < 0) {
									return;
								}
								drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xB >> 16, xA >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								xB += xStepBC;
								xA += xStepAB;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local202;
								vInterpolator += local232;
								wInterpolator += local262;
							}
						}
						drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xB >> 16, xC >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						xB += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local202;
						vInterpolator += local232;
						wInterpolator += local262;
					}
				} else {
					yB -= yA;
					yA -= yC;
					yC = offsets[yC];
					while (true) {
						yA--;
						if (yA < 0) {
							while (true) {
								yB--;
								if (yB < 0) {
									return;
								}
								drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xA >> 16, xB >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								xB += xStepBC;
								xA += xStepAB;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local202;
								vInterpolator += local232;
								wInterpolator += local262;
							}
						}
						drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xC >> 16, xB >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						xB += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local202;
						vInterpolator += local232;
						wInterpolator += local262;
					}
				}
			} else {
				xA = xC <<= 0x10;
				if (yC < 0) {
					xA -= xStepBC * yC;
					xC -= xStepAC * yC;
					colorC -= colorGradientY * yC;
					yC = 0;
				}
				xB <<= 0x10;
				if (yB < 0) {
					xB -= xStepAB * yB;
					yB = 0;
				}
				yOffset = yC - centerY;
				uInterpolator += local202 * yOffset;
				vInterpolator += local232 * yOffset;
				wInterpolator += local262 * yOffset;
				if (xStepBC < xStepAC) {
					yA -= yB;
					yB -= yC;
					yC = offsets[yC];
					while (true) {
						yB--;
						if (yB < 0) {
							while (true) {
								yA--;
								if (yA < 0) {
									return;
								}
								drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xB >> 16, xC >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								xB += xStepAB;
								xC += xStepAC;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local202;
								vInterpolator += local232;
								wInterpolator += local262;
							}
						}
						drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xA >> 16, xC >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						xA += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local202;
						vInterpolator += local232;
						wInterpolator += local262;
					}
				} else {
					yA -= yB;
					yB -= yC;
					yC = offsets[yC];
					while (true) {
						yB--;
						if (yB < 0) {
							while (true) {
								yA--;
								if (yA < 0) {
									return;
								}
								drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xC >> 16, xB >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
								xB += xStepAB;
								xC += xStepAC;
								colorC += colorGradientY;
								yC += SoftwareRenderer.width;
								uInterpolator += local202;
								vInterpolator += local232;
								wInterpolator += local262;
							}
						}
						drawTexturedAlphaScanline(SoftwareRenderer.pixels, texels, yC, xC >> 16, xA >> 16, colorC, colorGradientX, uInterpolator, vInterpolator, wInterpolator, uStep, vStep, wStep);
						xA += xStepBC;
						xC += xStepAC;
						colorC += colorGradientY;
						yC += SoftwareRenderer.width;
						uInterpolator += local202;
						vInterpolator += local232;
						wInterpolator += local262;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([I[IIIIIIIIIIIIII)V")
	private static void drawTexturedAlphaScanline(@OriginalArg(0) int[] pixelBuffer, @OriginalArg(1) int[] textureData, @OriginalArg(4) int pixelOffset, @OriginalArg(5) int leftX, @OriginalArg(6) int rightX, @OriginalArg(7) int color, @OriginalArg(8) int colorStep, @OriginalArg(9) int uInterpolator, @OriginalArg(10) int vInterpolator, @OriginalArg(11) int wInterpolator, @OriginalArg(12) int uStep, @OriginalArg(13) int vStep, @OriginalArg(14) int wStep) {
		if (testX) {
			if (rightX > width) {
				rightX = width;
			}
			if (leftX < 0) {
				leftX = 0;
			}
		}
		if (leftX >= rightX) {
			return;
		}
		pixelOffset += leftX;
		color += colorStep * leftX;
		@Pc(28) int spanWidth = rightX - leftX;
		@Pc(140) int lightness;
		@Pc(128) int textureStep;
		@Pc(68) int uStart;
		@Pc(72) int vStart;
		@Pc(99) int uEnd;
		@Pc(103) int vEnd;
		@Pc(62) int wDivisor;
		@Pc(34) int xOffset;
		@Pc(154) int texelColor;
		@Pc(114) int textureCoord;
		@Pc(157) int nextPixelOffset;
		@Pc(136) int colorStepBatch;
		@Pc(42) int uPerspective;
		@Pc(50) int vPerspective;
		@Pc(58) int wPerspective;
		if (!lowDetail) {
			xOffset = leftX - centerX;
			uPerspective = uInterpolator + (uStep >> 3) * xOffset;
			vPerspective = vInterpolator + (vStep >> 3) * xOffset;
			wPerspective = wInterpolator + (wStep >> 3) * xOffset;
			wDivisor = wPerspective >> 14;
			if (wDivisor == 0) {
				uStart = 0;
				vStart = 0;
			} else {
				uStart = uPerspective / wDivisor;
				vStart = vPerspective / wDivisor;
			}
			uInterpolator = uPerspective + uStep;
			vInterpolator = vPerspective + vStep;
			wInterpolator = wPerspective + wStep;
			wDivisor = wInterpolator >> 14;
			if (wDivisor == 0) {
				uEnd = 0;
				vEnd = 0;
			} else {
				uEnd = uInterpolator / wDivisor;
				vEnd = vInterpolator / wDivisor;
			}
			textureCoord = (uStart << 18) + vStart;
			textureStep = (uEnd - uStart >> 3 << 18) + (vEnd - vStart >> 3);
			spanWidth >>= 0x3;
			colorStepBatch = colorStep << 3;
			lightness = color >> 8;
			if (opaque) {
				if (spanWidth > 0) {
					do {
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						nextPixelOffset = pixelOffset + 1;
						pixelBuffer[pixelOffset] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[nextPixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelOffset = nextPixelOffset + 1;
						pixelBuffer[nextPixelOffset] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						uStart = uEnd;
						vStart = vEnd;
						uInterpolator += uStep;
						vInterpolator += vStep;
						wInterpolator += wStep;
						wDivisor = wInterpolator >> 14;
						if (wDivisor == 0) {
							uEnd = 0;
							vEnd = 0;
						} else {
							uEnd = uInterpolator / wDivisor;
							vEnd = vInterpolator / wDivisor;
						}
						textureCoord = (uStart << 18) + vStart;
						textureStep = (uEnd - uStart >> 3 << 18) + (vEnd - vStart >> 3);
						color += colorStepBatch;
						lightness = color >> 8;
						spanWidth--;
					} while (spanWidth > 0);
				}
				spanWidth = rightX - leftX & 0x7;
				if (spanWidth > 0) {
					do {
						texelColor = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)];
						pixelBuffer[pixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
						textureCoord += textureStep;
						spanWidth--;
					} while (spanWidth > 0);
				}
			} else {
				if (spanWidth > 0) {
					do {
						@Pc(1470) int texelColor1;
						if ((texelColor1 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[pixelOffset] = ((texelColor1 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor1 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset = pixelOffset + 1;
						textureCoord += textureStep;
						@Pc(1507) int texelColor2;
						if ((texelColor2 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor2 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor2 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1544) int texelColor3;
						if ((texelColor3 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor3 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor3 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1581) int texelColor4;
						if ((texelColor4 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor4 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor4 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1618) int texelColor5;
						if ((texelColor5 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor5 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor5 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1655) int texelColor6;
						if ((texelColor6 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor6 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor6 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1692) int texelColor7;
						if ((texelColor7 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor7 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor7 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						nextPixelOffset++;
						textureCoord += textureStep;
						@Pc(1729) int texelColor8;
						if ((texelColor8 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[nextPixelOffset] = ((texelColor8 & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor8 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						pixelOffset = nextPixelOffset + 1;
						uStart = uEnd;
						vStart = vEnd;
						uInterpolator += uStep;
						vInterpolator += vStep;
						wInterpolator += wStep;
						wDivisor = wInterpolator >> 14;
						if (wDivisor == 0) {
							uEnd = 0;
							vEnd = 0;
						} else {
							uEnd = uInterpolator / wDivisor;
							vEnd = vInterpolator / wDivisor;
						}
						textureCoord = (uStart << 18) + vStart;
						textureStep = (uEnd - uStart >> 3 << 18) + (vEnd - vStart >> 3);
						color += colorStepBatch;
						lightness = color >> 8;
						spanWidth--;
					} while (spanWidth > 0);
				}
				spanWidth = rightX - leftX & 0x7;
				if (spanWidth > 0) {
					do {
						@Pc(1840) int local1840;
						if ((local1840 = textureData[(textureCoord & 0x3F80) + (textureCoord >>> 25)]) != 0) {
							pixelBuffer[pixelOffset] = ((local1840 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local1840 & 0xFF00) * lightness & 0xFF0000) >> 8;
						}
						pixelOffset++;
						textureCoord += textureStep;
						spanWidth--;
					} while (spanWidth > 0);
				}
			}
			return;
		}
		xOffset = leftX - centerX;
		uPerspective = uInterpolator + (uStep >> 3) * xOffset;
		vPerspective = vInterpolator + (vStep >> 3) * xOffset;
		wPerspective = wInterpolator + (wStep >> 3) * xOffset;
		wDivisor = wPerspective >> 12;
		if (wDivisor == 0) {
			uStart = 0;
			vStart = 0;
		} else {
			uStart = uPerspective / wDivisor;
			vStart = vPerspective / wDivisor;
		}
		uInterpolator = uPerspective + uStep;
		vInterpolator = vPerspective + vStep;
		wInterpolator = wPerspective + wStep;
		wDivisor = wInterpolator >> 12;
		if (wDivisor == 0) {
			uEnd = 0;
			vEnd = 0;
		} else {
			uEnd = uInterpolator / wDivisor;
			vEnd = vInterpolator / wDivisor;
		}
		textureCoord = (uStart << 20) + vStart;
		textureStep = (uEnd - uStart >> 3 << 20) + (vEnd - vStart >> 3);
		spanWidth >>= 0x3;
		colorStepBatch = colorStep << 3;
		lightness = color >> 8;
		if (opaque) {
			if (spanWidth > 0) {
				do {
					texelColor = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					nextPixelOffset = pixelOffset + 1;
					pixelBuffer[pixelOffset] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(189) int local189 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(192) int local192 = nextPixelOffset + 1;
					pixelBuffer[nextPixelOffset] = ((local189 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local189 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(224) int local224 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(227) int local227 = local192 + 1;
					pixelBuffer[local192] = ((local224 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local224 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(259) int local259 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(262) int local262 = local227 + 1;
					pixelBuffer[local227] = ((local259 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local259 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(294) int local294 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(297) int local297 = local262 + 1;
					pixelBuffer[local262] = ((local294 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local294 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(329) int local329 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(332) int local332 = local297 + 1;
					pixelBuffer[local297] = ((local329 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local329 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(364) int local364 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					@Pc(367) int local367 = local332 + 1;
					pixelBuffer[local332] = ((local364 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local364 & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					@Pc(399) int local399 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					pixelOffset = local367 + 1;
					pixelBuffer[local367] = ((local399 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local399 & 0xFF00) * lightness & 0xFF0000) >> 8;
					uStart = uEnd;
					vStart = vEnd;
					uInterpolator += uStep;
					vInterpolator += vStep;
					wInterpolator += wStep;
					wDivisor = wInterpolator >> 12;
					if (wDivisor == 0) {
						uEnd = 0;
						vEnd = 0;
					} else {
						uEnd = uInterpolator / wDivisor;
						vEnd = vInterpolator / wDivisor;
					}
					textureCoord = (uStart << 20) + vStart;
					textureStep = (uEnd - uStart >> 3 << 20) + (vEnd - vStart >> 3);
					color += colorStepBatch;
					lightness = color >> 8;
					spanWidth--;
				} while (spanWidth > 0);
			}
			spanWidth = rightX - leftX & 0x7;
			if (spanWidth > 0) {
				do {
					texelColor = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)];
					pixelBuffer[pixelOffset++] = ((texelColor & 0xFF00FF) * lightness & 0xFF00FF00) + ((texelColor & 0xFF00) * lightness & 0xFF0000) >> 8;
					textureCoord += textureStep;
					spanWidth--;
				} while (spanWidth > 0);
			}
			return;
		}
		if (spanWidth > 0) {
			do {
				@Pc(550) int local550;
				if ((local550 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[pixelOffset] = ((local550 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local550 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset = pixelOffset + 1;
				textureCoord += textureStep;
				@Pc(587) int local587;
				if ((local587 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local587 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local587 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(624) int local624;
				if ((local624 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local624 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local624 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(661) int local661;
				if ((local661 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local661 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local661 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(698) int local698;
				if ((local698 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local698 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local698 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(735) int local735;
				if ((local735 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local735 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local735 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(772) int local772;
				if ((local772 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local772 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local772 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				nextPixelOffset++;
				textureCoord += textureStep;
				@Pc(809) int local809;
				if ((local809 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
					pixelBuffer[nextPixelOffset] = ((local809 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local809 & 0xFF00) * lightness & 0xFF0000) >> 8;
				}
				pixelOffset = nextPixelOffset + 1;
				uStart = uEnd;
				vStart = vEnd;
				uInterpolator += uStep;
				vInterpolator += vStep;
				wInterpolator += wStep;
				wDivisor = wInterpolator >> 12;
				if (wDivisor == 0) {
					uEnd = 0;
					vEnd = 0;
				} else {
					uEnd = uInterpolator / wDivisor;
					vEnd = vInterpolator / wDivisor;
				}
				textureCoord = (uStart << 20) + vStart;
				textureStep = (uEnd - uStart >> 3 << 20) + (vEnd - vStart >> 3);
				color += colorStepBatch;
				lightness = color >> 8;
				spanWidth--;
			} while (spanWidth > 0);
		}
		spanWidth = rightX - leftX & 0x7;
		if (spanWidth <= 0) {
			return;
		}
		do {
			@Pc(920) int local920;
			if ((local920 = textureData[(textureCoord & 0xFC0) + (textureCoord >>> 26)]) != 0) {
				pixelBuffer[pixelOffset] = ((local920 & 0xFF00FF) * lightness & 0xFF00FF00) + ((local920 & 0xFF00) * lightness & 0xFF0000) >> 8;
			}
			pixelOffset++;
			textureCoord += textureStep;
			spanWidth--;
		} while (spanWidth > 0);
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "b", descriptor = "()I")
	public static int getOffsetRemainder() {
		return offsets[0] % SoftwareRenderer.width;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(IIIIIII)V")
	public static void fillTriangle(@OriginalArg(0) int y1, @OriginalArg(1) int y2, @OriginalArg(2) int y3, @OriginalArg(3) int x1, @OriginalArg(4) int x2, @OriginalArg(5) int x3, @OriginalArg(6) int color) {
		@Pc(1) int slopeEdge12 = 0;
		if (y2 != y1) {
			slopeEdge12 = (x2 - x1 << 16) / (y2 - y1);
		}
		@Pc(16) int slopeEdge23 = 0;
		if (y3 != y2) {
			slopeEdge23 = (x3 - x2 << 16) / (y3 - y2);
		}
		@Pc(31) int slopeEdge31 = 0;
		if (y3 != y1) {
			slopeEdge31 = (x1 - x3 << 16) / (y1 - y3);
		}
		if (y1 <= y2 && y1 <= y3) {
			if (y1 < height) {
				if (y2 > height) {
					y2 = height;
				}
				if (y3 > height) {
					y3 = height;
				}
				if (y2 < y3) {
					x3 = x1 <<= 0x10;
					if (y1 < 0) {
						x3 -= slopeEdge31 * y1;
						x1 -= slopeEdge12 * y1;
						y1 = 0;
					}
					x2 <<= 0x10;
					if (y2 < 0) {
						x2 -= slopeEdge23 * y2;
						y2 = 0;
					}
					if (y1 != y2 && slopeEdge31 < slopeEdge12 || y1 == y2 && slopeEdge31 > slopeEdge23) {
						y3 -= y2;
						y2 -= y1;
						y1 = offsets[y1];
						while (true) {
							y2--;
							if (y2 < 0) {
								while (true) {
									y3--;
									if (y3 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y1, color, x3 >> 16, x2 >> 16);
									x3 += slopeEdge31;
									x2 += slopeEdge23;
									y1 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y1, color, x3 >> 16, x1 >> 16);
							x3 += slopeEdge31;
							x1 += slopeEdge12;
							y1 += SoftwareRenderer.width;
						}
					} else {
						y3 -= y2;
						y2 -= y1;
						y1 = offsets[y1];
						while (true) {
							y2--;
							if (y2 < 0) {
								while (true) {
									y3--;
									if (y3 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y1, color, x2 >> 16, x3 >> 16);
									x3 += slopeEdge31;
									x2 += slopeEdge23;
									y1 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y1, color, x1 >> 16, x3 >> 16);
							x3 += slopeEdge31;
							x1 += slopeEdge12;
							y1 += SoftwareRenderer.width;
						}
					}
				} else {
					x2 = x1 <<= 0x10;
					if (y1 < 0) {
						x2 -= slopeEdge31 * y1;
						x1 -= slopeEdge12 * y1;
						y1 = 0;
					}
					x3 <<= 0x10;
					if (y3 < 0) {
						x3 -= slopeEdge23 * y3;
						y3 = 0;
					}
					if (y1 != y3 && slopeEdge31 < slopeEdge12 || y1 == y3 && slopeEdge23 > slopeEdge12) {
						y2 -= y3;
						y3 -= y1;
						y1 = offsets[y1];
						while (true) {
							y3--;
							if (y3 < 0) {
								while (true) {
									y2--;
									if (y2 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y1, color, x3 >> 16, x1 >> 16);
									x3 += slopeEdge23;
									x1 += slopeEdge12;
									y1 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y1, color, x2 >> 16, x1 >> 16);
							x2 += slopeEdge31;
							x1 += slopeEdge12;
							y1 += SoftwareRenderer.width;
						}
					} else {
						y2 -= y3;
						y3 -= y1;
						y1 = offsets[y1];
						while (true) {
							y3--;
							if (y3 < 0) {
								while (true) {
									y2--;
									if (y2 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y1, color, x1 >> 16, x3 >> 16);
									x3 += slopeEdge23;
									x1 += slopeEdge12;
									y1 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y1, color, x1 >> 16, x2 >> 16);
							x2 += slopeEdge31;
							x1 += slopeEdge12;
							y1 += SoftwareRenderer.width;
						}
					}
				}
			}
		} else if (y2 <= y3) {
			if (y2 < height) {
				if (y3 > height) {
					y3 = height;
				}
				if (y1 > height) {
					y1 = height;
				}
				if (y3 < y1) {
					x1 = x2 <<= 0x10;
					if (y2 < 0) {
						x1 -= slopeEdge12 * y2;
						x2 -= slopeEdge23 * y2;
						y2 = 0;
					}
					x3 <<= 0x10;
					if (y3 < 0) {
						x3 -= slopeEdge31 * y3;
						y3 = 0;
					}
					if (y2 != y3 && slopeEdge12 < slopeEdge23 || y2 == y3 && slopeEdge12 > slopeEdge31) {
						y1 -= y3;
						y3 -= y2;
						y2 = offsets[y2];
						while (true) {
							y3--;
							if (y3 < 0) {
								while (true) {
									y1--;
									if (y1 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y2, color, x1 >> 16, x3 >> 16);
									x1 += slopeEdge12;
									x3 += slopeEdge31;
									y2 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y2, color, x1 >> 16, x2 >> 16);
							x1 += slopeEdge12;
							x2 += slopeEdge23;
							y2 += SoftwareRenderer.width;
						}
					} else {
						y1 -= y3;
						y3 -= y2;
						y2 = offsets[y2];
						while (true) {
							y3--;
							if (y3 < 0) {
								while (true) {
									y1--;
									if (y1 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y2, color, x3 >> 16, x1 >> 16);
									x1 += slopeEdge12;
									x3 += slopeEdge31;
									y2 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y2, color, x2 >> 16, x1 >> 16);
							x1 += slopeEdge12;
							x2 += slopeEdge23;
							y2 += SoftwareRenderer.width;
						}
					}
				} else {
					x3 = x2 <<= 0x10;
					if (y2 < 0) {
						x3 -= slopeEdge12 * y2;
						x2 -= slopeEdge23 * y2;
						y2 = 0;
					}
					x1 <<= 0x10;
					if (y1 < 0) {
						x1 -= slopeEdge31 * y1;
						y1 = 0;
					}
					if (slopeEdge12 < slopeEdge23) {
						y3 -= y1;
						y1 -= y2;
						y2 = offsets[y2];
						while (true) {
							y1--;
							if (y1 < 0) {
								while (true) {
									y3--;
									if (y3 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y2, color, x1 >> 16, x2 >> 16);
									x1 += slopeEdge31;
									x2 += slopeEdge23;
									y2 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y2, color, x3 >> 16, x2 >> 16);
							x3 += slopeEdge12;
							x2 += slopeEdge23;
							y2 += SoftwareRenderer.width;
						}
					} else {
						y3 -= y1;
						y1 -= y2;
						y2 = offsets[y2];
						while (true) {
							y1--;
							if (y1 < 0) {
								while (true) {
									y3--;
									if (y3 < 0) {
										return;
									}
									drawScanline(SoftwareRenderer.pixels, y2, color, x2 >> 16, x1 >> 16);
									x1 += slopeEdge31;
									x2 += slopeEdge23;
									y2 += SoftwareRenderer.width;
								}
							}
							drawScanline(SoftwareRenderer.pixels, y2, color, x2 >> 16, x3 >> 16);
							x3 += slopeEdge12;
							x2 += slopeEdge23;
							y2 += SoftwareRenderer.width;
						}
					}
				}
			}
		} else if (y3 < height) {
			if (y1 > height) {
				y1 = height;
			}
			if (y2 > height) {
				y2 = height;
			}
			if (y1 < y2) {
				x2 = x3 <<= 0x10;
				if (y3 < 0) {
					x2 -= slopeEdge23 * y3;
					x3 -= slopeEdge31 * y3;
					y3 = 0;
				}
				x1 <<= 0x10;
				if (y1 < 0) {
					x1 -= slopeEdge12 * y1;
					y1 = 0;
				}
				if (slopeEdge23 < slopeEdge31) {
					y2 -= y1;
					y1 -= y3;
					y3 = offsets[y3];
					while (true) {
						y1--;
						if (y1 < 0) {
							while (true) {
								y2--;
								if (y2 < 0) {
									return;
								}
								drawScanline(SoftwareRenderer.pixels, y3, color, x2 >> 16, x1 >> 16);
								x2 += slopeEdge23;
								x1 += slopeEdge12;
								y3 += SoftwareRenderer.width;
							}
						}
						drawScanline(SoftwareRenderer.pixels, y3, color, x2 >> 16, x3 >> 16);
						x2 += slopeEdge23;
						x3 += slopeEdge31;
						y3 += SoftwareRenderer.width;
					}
				} else {
					y2 -= y1;
					y1 -= y3;
					y3 = offsets[y3];
					while (true) {
						y1--;
						if (y1 < 0) {
							while (true) {
								y2--;
								if (y2 < 0) {
									return;
								}
								drawScanline(SoftwareRenderer.pixels, y3, color, x1 >> 16, x2 >> 16);
								x2 += slopeEdge23;
								x1 += slopeEdge12;
								y3 += SoftwareRenderer.width;
							}
						}
						drawScanline(SoftwareRenderer.pixels, y3, color, x3 >> 16, x2 >> 16);
						x2 += slopeEdge23;
						x3 += slopeEdge31;
						y3 += SoftwareRenderer.width;
					}
				}
			} else {
				x1 = x3 <<= 0x10;
				if (y3 < 0) {
					x1 -= slopeEdge23 * y3;
					x3 -= slopeEdge31 * y3;
					y3 = 0;
				}
				x2 <<= 0x10;
				if (y2 < 0) {
					x2 -= slopeEdge12 * y2;
					y2 = 0;
				}
				if (slopeEdge23 < slopeEdge31) {
					y1 -= y2;
					y2 -= y3;
					y3 = offsets[y3];
					while (true) {
						y2--;
						if (y2 < 0) {
							while (true) {
								y1--;
								if (y1 < 0) {
									return;
								}
								drawScanline(SoftwareRenderer.pixels, y3, color, x2 >> 16, x3 >> 16);
								x2 += slopeEdge12;
								x3 += slopeEdge31;
								y3 += SoftwareRenderer.width;
							}
						}
						drawScanline(SoftwareRenderer.pixels, y3, color, x1 >> 16, x3 >> 16);
						x1 += slopeEdge23;
						x3 += slopeEdge31;
						y3 += SoftwareRenderer.width;
					}
				} else {
					y1 -= y2;
					y2 -= y3;
					y3 = offsets[y3];
					while (true) {
						y2--;
						if (y2 < 0) {
							while (true) {
								y1--;
								if (y1 < 0) {
									return;
								}
								drawScanline(SoftwareRenderer.pixels, y3, color, x3 >> 16, x2 >> 16);
								x2 += slopeEdge12;
								x3 += slopeEdge31;
								y3 += SoftwareRenderer.width;
							}
						}
						drawScanline(SoftwareRenderer.pixels, y3, color, x3 >> 16, x1 >> 16);
						x1 += slopeEdge23;
						x3 += slopeEdge31;
						y3 += SoftwareRenderer.width;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "([IIIIII)V")
	private static void drawScanline(@OriginalArg(0) int[] pixels, @OriginalArg(1) int pixelOffset, @OriginalArg(2) int color, @OriginalArg(4) int startX, @OriginalArg(5) int endX) {
		if (testX) {
			if (endX > width) {
				endX = width;
			}
			if (startX < 0) {
				startX = 0;
			}
		}
		if (startX >= endX) {
			return;
		}
		pixelOffset += startX;
		@Pc(24) int unrolledCount = endX - startX >> 2;
		@Pc(32) int nextPixelOffset;
		if (alpha == 0) {
			while (true) {
				unrolledCount--;
				if (unrolledCount < 0) {
					unrolledCount = endX - startX & 0x3;
					while (true) {
						unrolledCount--;
						if (unrolledCount < 0) {
							return;
						}
						pixels[pixelOffset++] = color;
					}
				}
				nextPixelOffset = pixelOffset + 1;
				pixels[pixelOffset] = color;
				@Pc(37) int pixel2Offset = nextPixelOffset + 1;
				pixels[nextPixelOffset] = color;
				@Pc(42) int pixel3Offset = pixel2Offset + 1;
				pixels[pixel2Offset] = color;
				pixelOffset = pixel3Offset + 1;
				pixels[pixel3Offset] = color;
			}
		} else if (alpha == 254) {
			while (true) {
				unrolledCount--;
				if (unrolledCount < 0) {
					unrolledCount = endX - startX & 0x3;
					while (true) {
						unrolledCount--;
						if (unrolledCount < 0) {
							return;
						}
						pixels[pixelOffset++] = pixels[pixelOffset];
					}
				}
				nextPixelOffset = pixelOffset + 1;
				pixels[pixelOffset] = pixels[nextPixelOffset];
				pixels[nextPixelOffset++] = pixels[nextPixelOffset];
				pixels[nextPixelOffset++] = pixels[nextPixelOffset];
				pixelOffset = nextPixelOffset + 1;
				pixels[nextPixelOffset] = pixels[pixelOffset];
			}
		} else {
			@Pc(119) int srcAlpha = alpha;
			@Pc(123) int destAlpha = 256 - alpha;
			@Pc(143) int blendedColor = ((color & 0xFF00FF) * destAlpha >> 8 & 0xFF00FF) + ((color & 0xFF00) * destAlpha >> 8 & 0xFF00);
			while (true) {
				unrolledCount--;
				@Pc(150) int existingPixel;
				if (unrolledCount < 0) {
					unrolledCount = endX - startX & 0x3;
					while (true) {
						unrolledCount--;
						if (unrolledCount < 0) {
							return;
						}
						existingPixel = pixels[pixelOffset];
						pixels[pixelOffset++] = blendedColor + ((existingPixel & 0xFF00FF) * srcAlpha >> 8 & 0xFF00FF) + ((existingPixel & 0xFF00) * srcAlpha >> 8 & 0xFF00);
					}
				}
				existingPixel = pixels[pixelOffset];
				nextPixelOffset = pixelOffset + 1;
				pixels[pixelOffset] = blendedColor + ((existingPixel & 0xFF00FF) * srcAlpha >> 8 & 0xFF00FF) + ((existingPixel & 0xFF00) * srcAlpha >> 8 & 0xFF00);
				@Pc(179) int existingPixel2 = pixels[nextPixelOffset];
				pixels[nextPixelOffset++] = blendedColor + ((existingPixel2 & 0xFF00FF) * srcAlpha >> 8 & 0xFF00FF) + ((existingPixel2 & 0xFF00) * srcAlpha >> 8 & 0xFF00);
				@Pc(208) int existingPixel3 = pixels[nextPixelOffset];
				pixels[nextPixelOffset++] = blendedColor + ((existingPixel3 & 0xFF00FF) * srcAlpha >> 8 & 0xFF00FF) + ((existingPixel3 & 0xFF00) * srcAlpha >> 8 & 0xFF00);
				@Pc(237) int existingPixel4 = pixels[nextPixelOffset];
				pixelOffset = nextPixelOffset + 1;
				pixels[nextPixelOffset] = blendedColor + ((existingPixel4 & 0xFF00FF) * srcAlpha >> 8 & 0xFF00FF) + ((existingPixel4 & 0xFF00) * srcAlpha >> 8 & 0xFF00);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "a", descriptor = "(III)V")
	public static void testPoints(@OriginalArg(0) int x1, @OriginalArg(1) int x2, @OriginalArg(2) int x3) {
		testX = x1 < 0 || x1 > width || x2 < 0 || x2 > width || x3 < 0 || x3 > width;
	}

	@OriginalMember(owner = "runetek4.client!hf", name = "d", descriptor = "()I")
	public static int getOffset() {
		return offsets[0] / SoftwareRenderer.width;
	}
}
