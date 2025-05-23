package com.jagex.runetek4.cache.media;

import java.util.Random;

import com.jagex.runetek4.*;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SecondaryNode;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rk")
public abstract class Font extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!rk", name = "K", descriptor = "Lclient!na;")
	public static final JString greaterThan = JString.parse("gt");

	@OriginalMember(owner = "runetek4.client!rk", name = "L", descriptor = "Lclient!na;")
	public static final JString lessThan = JString.parse("lt");

	@OriginalMember(owner = "runetek4.client!rk", name = "R", descriptor = "Lclient!na;")
	public static final JString euroSymbol = JString.parse("euro");

	@OriginalMember(owner = "runetek4.client!rk", name = "cb", descriptor = "Lclient!na;")
	public static final JString multiplicationSymbol = JString.parse("times");

	@OriginalMember(owner = "runetek4.client!rk", name = "fb", descriptor = "Lclient!na;")
	public static final JString nonBreakingSpace = JString.parse("nbsp");

	@OriginalMember(owner = "runetek4.client!rk", name = "mb", descriptor = "Lclient!na;")
	public static final JString image = JString.parse("img=");

	@OriginalMember(owner = "runetek4.client!rk", name = "ob", descriptor = "Lclient!na;")
	public static final JString copyright = JString.parse("copy");

	@OriginalMember(owner = "runetek4.client!rk", name = "qb", descriptor = "Lclient!na;")
	public static final JString registeredTrademark = JString.parse("reg");

	@OriginalMember(owner = "runetek4.client!rk", name = "N", descriptor = "Lclient!na;")
	public static final JString softHyphen = JString.parse("shy");
	@OriginalMember(owner = "runetek4.client!rk", name = "T", descriptor = "Lclient!na;")
	public static final JString endColor = JString.parse(")4col");
	@OriginalMember(owner = "runetek4.client!rk", name = "Q", descriptor = "Lclient!na;")
	public static final JString startStrikethrough = JString.parse("str=");
	@OriginalMember(owner = "runetek4.client!rk", name = "X", descriptor = "Lclient!na;")
	public static final JString endShadow = JString.parse(")4shad");
	@OriginalMember(owner = "runetek4.client!rk", name = "Y", descriptor = "Lclient!na;")
	public static final JString startColor = JString.parse("col=");
	@OriginalMember(owner = "runetek4.client!rk", name = "bb", descriptor = "Lclient!na;")
	public static final JString startDefaultUnderline = JString.parse("u");
	@OriginalMember(owner = "runetek4.client!rk", name = "eb", descriptor = "Lclient!na;")
	public static final JString endTrans = JString.parse(")4trans");
	@OriginalMember(owner = "runetek4.client!rk", name = "ib", descriptor = "Lclient!na;")
	public static final JString endUnderline = JString.parse(")4u");
	@OriginalMember(owner = "runetek4.client!rk", name = "kb", descriptor = "Lclient!na;")
	public static final JString lineBreak = JString.parse("br");
	@OriginalMember(owner = "runetek4.client!rk", name = "lb", descriptor = "Lclient!na;")
	public static final JString startDefaultShadow = JString.parse("shad");
	@OriginalMember(owner = "runetek4.client!rk", name = "pb", descriptor = "Lclient!na;")
	public static final JString endStrikeThrough = JString.parse(")4str");
	@OriginalMember(owner = "runetek4.client!rk", name = "rb", descriptor = "Lclient!na;")
	public static final JString startDefaultStrikeThrough = JString.parse("str");
	@OriginalMember(owner = "runetek4.client!rk", name = "sb", descriptor = "Lclient!na;")
	public static final JString buffer = JString.allocate(100);
	@OriginalMember(owner = "runetek4.client!rk", name = "Ab", descriptor = "[Lclient!na;")
	public static final JString[] lines = new JString[100];
	@OriginalMember(owner = "runetek4.client!rk", name = "Z", descriptor = "Lclient!na;")
	public static final JString aClass100_706 = JString.parse("<gt>");
	@OriginalMember(owner = "runetek4.client!rk", name = "hb", descriptor = "Lclient!na;")
	public static final JString aClass100_711 = JString.parse("<lt>");

	@OriginalMember(owner = "runetek4.client!rk", name = "xb", descriptor = "I")
	public static int opacity = 256;

	@OriginalMember(owner = "runetek4.client!rk", name = "Cb", descriptor = "I")
	public static int textColor = 0;
	@OriginalMember(owner = "runetek4.client!rk", name = "tb", descriptor = "I")
	public static int strikethroughColor = -1;
	@OriginalMember(owner = "runetek4.client!rk", name = "ub", descriptor = "I")
	public static int underlineColor = -1;
	@OriginalMember(owner = "runetek4.client!rk", name = "vb", descriptor = "I")
	public static int extraSpaceWidth = 0;
	@OriginalMember(owner = "runetek4.client!rk", name = "wb", descriptor = "I")
	public static int defaultTextColor = 0;
	@OriginalMember(owner = "runetek4.client!rk", name = "yb", descriptor = "I")
	public static int defaultOpacity = 256;
	@OriginalMember(owner = "runetek4.client!rk", name = "zb", descriptor = "I")
	public static int defaultShadowColor = -1;
	@OriginalMember(owner = "runetek4.client!rk", name = "Bb", descriptor = "I")
	public static int shadowColor = -1;
	@OriginalMember(owner = "runetek4.client!rk", name = "Db", descriptor = "I")
	public static int spaceWidth = 0;

	@OriginalMember(owner = "client!rk", name = "W", descriptor = "[I")
	private int[] nameIconHeights;

	@OriginalMember(owner = "client!rk", name = "gb", descriptor = "[B")
	private byte[] kerning;

	@OriginalMember(owner = "client!rk", name = "jb", descriptor = "[I")
	private int[] glyphWidths;

	@OriginalMember(owner = "client!rk", name = "nb", descriptor = "[Lclient!ok;")
	private IndexedSprite[] nameIcons;

	@OriginalMember(owner = "client!rk", name = "ab", descriptor = "I")
	public int characterDefaultHeight = 0;

	@OriginalMember(owner = "client!rk", name = "S", descriptor = "[I")
	private int[] spriteXOffsets;

	@OriginalMember(owner = "client!rk", name = "db", descriptor = "[I")
	private int[] spriteYOffsets;

	@OriginalMember(owner = "client!rk", name = "I", descriptor = "[I")
	protected int[] spriteInnerWidths;

	@OriginalMember(owner = "client!rk", name = "U", descriptor = "[I")
	protected int[] spriteInnerHeights;

	@OriginalMember(owner = "client!rk", name = "V", descriptor = "I")
	private int paragraphTopPadding;

	@OriginalMember(owner = "client!rk", name = "J", descriptor = "I")
	private int paragraphBottomPadding;

	@OriginalMember(owner = "client!rk", name = "<init>", descriptor = "([B[I[I[I[I)V")
	protected Font(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int[] arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4) {
		this.spriteXOffsets = arg1;
		this.spriteYOffsets = arg2;
		this.spriteInnerWidths = arg3;
		this.spriteInnerHeights = arg4;
		this.decode(arg0);
		@Pc(21) int local21 = Integer.MAX_VALUE;
		@Pc(23) int local23 = Integer.MIN_VALUE;
		for (@Pc(25) int local25 = 0; local25 < 256; local25++) {
			if (this.spriteYOffsets[local25] < local21 && this.spriteInnerHeights[local25] != 0) {
				local21 = this.spriteYOffsets[local25];
			}
			if (this.spriteYOffsets[local25] + this.spriteInnerHeights[local25] > local23) {
				local23 = this.spriteYOffsets[local25] + this.spriteInnerHeights[local25];
			}
		}
		this.paragraphTopPadding = this.characterDefaultHeight - local21;
		this.paragraphBottomPadding = local23 - this.characterDefaultHeight;
	}

	@OriginalMember(owner = "client!rk", name = "<init>", descriptor = "([B)V")
	public Font(@OriginalArg(0) byte[] arg0) {
		this.decode(arg0);
	}

	@OriginalMember(owner = "runetek4.client!rk", name = "c", descriptor = "(Lclient!na;)Lclient!na;")
	public static JString escape(@OriginalArg(0) JString arg0) {
		@Pc(3) int local3 = arg0.length();
		@Pc(5) int local5 = 0;
		@Pc(15) int local15;
		for (@Pc(7) int local7 = 0; local7 < local3; local7++) {
			local15 = arg0.charAt(local7);
			if (local15 == 60 || local15 == 62) {
				local5 += 3;
			}
		}
		@Pc(30) JString local30 = JString.allocate(local3 + local5);
		for (local15 = 0; local15 < local3; local15++) {
			@Pc(40) int local40 = arg0.charAt(local15);
			if (local40 == 60) {
				local30.method3113(aClass100_711);
			} else if (local40 == 62) {
				local30.method3113(aClass100_706);
			} else {
				local30.append(local40);
			}
		}
		return local30;
	}

	@OriginalMember(owner = "runetek4.client!rk", name = "a", descriptor = "([[B[[B[I[I[III)I")
	public static int method2870(@OriginalArg(0) byte[][] arg0, @OriginalArg(1) byte[][] arg1, @OriginalArg(2) int[] arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) int local3 = arg2[arg5];
		@Pc(9) int local9 = local3 + arg4[arg5];
		@Pc(13) int local13 = arg2[arg6];
		@Pc(19) int local19 = local13 + arg4[arg6];
		@Pc(21) int local21 = local3;
		if (local13 > local3) {
			local21 = local13;
		}
		@Pc(28) int local28 = local9;
		if (local19 < local9) {
			local28 = local19;
		}
		@Pc(37) int local37 = arg3[arg5];
		if (arg3[arg6] < local37) {
			local37 = arg3[arg6];
		}
		@Pc(50) byte[] local50 = arg1[arg5];
		@Pc(54) byte[] local54 = arg0[arg6];
		@Pc(58) int local58 = local21 - local3;
		@Pc(62) int local62 = local21 - local13;
		for (@Pc(64) int local64 = local21; local64 < local28; local64++) {
			@Pc(77) int local77 = local50[local58++] + local54[local62++];
			if (local77 < local37) {
				local37 = local77;
			}
		}
		return -local37;
	}

	@OriginalMember(owner = "client!ce", name = "a", descriptor = "(I[B)Lclient!rk;")
	public static Font method799(@OriginalArg(1) byte[] arg0) {
		if (arg0 == null) {
			return null;
		}
		@Pc(27) Font local27;
		if (GlRenderer.enabled) {
			local27 = new GlFont(arg0, SpriteLoader.xOffsets, SpriteLoader.yOffsets, SpriteLoader.innerWidths, SpriteLoader.innerHeights, SpriteLoader.pixels);
		} else {
			local27 = new SoftwareFont(arg0, SpriteLoader.xOffsets, SpriteLoader.yOffsets, SpriteLoader.innerWidths, SpriteLoader.innerHeights, SpriteLoader.pixels);
		}
		SpriteLoader.clear();
		return local27;
	}

	@OriginalMember(owner = "runetek4.client!k", name = "a", descriptor = "(IIBLclient!ve;Lclient!ve;)Lclient!rk;")
	public static Font getFont(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1, @OriginalArg(4) Js5 arg2) {
		return SpriteLoader.decode(arg1, 0, arg0) ? method799(arg2.getfile(arg0, 0)) : null;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIIIIII)I")
	public final int drawInterfaceText(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9) {
		return this.renderParagraphAlpha(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;II)V")
	private void drawBasicString(@OriginalArg(0) JString string, @OriginalArg(1) int x, @OriginalArg(2) int arg2) {
		@Pc(4) int y = arg2 - this.characterDefaultHeight;
		@Pc(6) int effectIndex = -1;
		@Pc(8) int local8 = 0;
		@Pc(12) int local12 = string.length();
		for (@Pc(14) int character = 0; character < local12; character++) {
			@Pc(22) int c = string.charAt(character);
			if (c == 60) {
				effectIndex = character;
			} else {
				@Pc(120) int icon;
				if (c == 62 && effectIndex != -1) {
					@Pc(42) JString effectString = string.substring(character, effectIndex + 1);
					effectIndex = -1;
					if (effectString.strEquals(lessThan)) {
						c = 60;
					} else if (effectString.strEquals(greaterThan)) {
						c = 62;
					} else if (effectString.strEquals(nonBreakingSpace)) {
						c = 160;
					} else if (effectString.strEquals(softHyphen)) {
						c = 173;
					} else if (effectString.strEquals(multiplicationSymbol)) {
						c = 215;
					} else if (effectString.strEquals(euroSymbol)) {
						c = 128;
					} else if (effectString.strEquals(copyright)) {
						c = 169;
					} else {
						if (!effectString.strEquals(registeredTrademark)) {
							if (effectString.startsWith(image)) {
								try {
									icon = effectString.substring(4).parseInt();
									@Pc(125) IndexedSprite nameIcon = this.nameIcons[icon];
									@Pc(136) int imageHeight = this.nameIconHeights == null ? nameIcon.innerHeight : this.nameIconHeights[icon];
									if (opacity == 256) {
										nameIcon.renderTransparent(x, y + this.characterDefaultHeight - imageHeight);
									} else {
										nameIcon.drawImageAlpha(x, y + this.characterDefaultHeight - imageHeight, opacity);
									}
									x += nameIcon.innerWidth;
									local8 = 0;
								} catch (@Pc(168) Exception local168) {
								}
							} else {
								this.parseStringForEffects(effectString);
							}
							continue;
						}
						c = 174;
					}
				}
				if (effectIndex == -1) {
					if (this.kerning != null && local8 != 0) {
						x += this.kerning[(local8 << 8) + c];
					}
					@Pc(197) int local197 = this.spriteInnerWidths[c];
					icon = this.spriteInnerHeights[c];
					if (c == 32) {
						if (spaceWidth > 0) {
							extraSpaceWidth += spaceWidth;
							x += extraSpaceWidth >> 8;
							extraSpaceWidth &= 0xFF;
						}
					} else if (opacity == 256) {
						if (shadowColor != -1) {
							this.renderGlyph(c, x + this.spriteXOffsets[c] + 1, y + this.spriteYOffsets[c] + 1, local197, icon, shadowColor);
						}
						this.renderGlyph(c, x + this.spriteXOffsets[c], y + this.spriteYOffsets[c], local197, icon, textColor);
					} else {
						if (shadowColor != -1) {
							this.renderGlyphTransparent(c, x + this.spriteXOffsets[c] + 1, y + this.spriteYOffsets[c] + 1, local197, icon, shadowColor, opacity);
						}
						this.renderGlyphTransparent(c, x + this.spriteXOffsets[c], y + this.spriteYOffsets[c], local197, icon, textColor, opacity);
					}
					@Pc(323) int local323 = this.glyphWidths[c];
					if (strikethroughColor != -1) {
						if (GlRenderer.enabled) {
							GlRaster.method1174(x, y + (int) ((double) this.characterDefaultHeight * 0.7D), local323, strikethroughColor);
						} else {
							SoftwareRaster.drawHorizontalLine(x, y + (int) ((double) this.characterDefaultHeight * 0.7D), local323, strikethroughColor);
						}
					}
					if (underlineColor != -1) {
						if (GlRenderer.enabled) {
							GlRaster.method1174(x, y + this.characterDefaultHeight + 1, local323, underlineColor);
						} else {
							SoftwareRaster.drawHorizontalLine(x, y + this.characterDefaultHeight + 1, local323, underlineColor);
						}
					}
					x += local323;
					local8 = c;
				}
			}
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(IIIIIIZ)V")
	protected abstract void renderGlyph(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5);

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(IIIIIIIZ)V")
	protected abstract void renderGlyphTransparent(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6);

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;I)I")
	public final int getMaxLineWidth(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1) {
		@Pc(10) int local10 = this.splitParagraph(arg0, new int[] { arg1 }, lines);
		@Pc(12) int local12 = 0;
		for (@Pc(14) int local14 = 0; local14 < local10; local14++) {
			@Pc(23) int local23 = this.getStringWidth(lines[local14]);
			if (local23 > local12) {
				local12 = local23;
			}
		}
		return local12;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIII)V")
	public final void renderLeft(@OriginalArg(0) JString string, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int color, @OriginalArg(4) int shadow) {
		if (string != null) {
			this.setEffects(color, shadow);
			this.drawBasicString(string, x, y);
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;)I")
	public final int getStringWidth(@OriginalArg(0) JString arg0) {
		if (arg0 == null) {
			return 0;
		}
		@Pc(5) int local5 = -1;
		@Pc(7) int local7 = 0;
		@Pc(9) int local9 = 0;
		@Pc(13) int local13 = arg0.length();
		for (@Pc(15) int local15 = 0; local15 < local13; local15++) {
			@Pc(23) int local23 = arg0.charAt(local15);
			if (local23 == 60) {
				local5 = local15;
			} else {
				if (local23 == 62 && local5 != -1) {
					@Pc(43) JString local43 = arg0.substring(local15, local5 + 1);
					local5 = -1;
					if (local43.strEquals(lessThan)) {
						local23 = 60;
					} else if (local43.strEquals(greaterThan)) {
						local23 = 62;
					} else if (local43.strEquals(nonBreakingSpace)) {
						local23 = 160;
					} else if (local43.strEquals(softHyphen)) {
						local23 = 173;
					} else if (local43.strEquals(multiplicationSymbol)) {
						local23 = 215;
					} else if (local43.strEquals(euroSymbol)) {
						local23 = 128;
					} else if (local43.strEquals(copyright)) {
						local23 = 169;
					} else {
						if (!local43.strEquals(registeredTrademark)) {
							if (local43.startsWith(image)) {
								try {
									@Pc(121) int local121 = local43.substring(4).parseInt();
									local9 += this.nameIcons[local121].innerWidth;
									local7 = 0;
								} catch (@Pc(133) Exception local133) {
								}
							}
							continue;
						}
						local23 = 174;
					}
				}
				if (local5 == -1) {
					local9 += this.glyphWidths[local23];
					if (this.kerning != null && local7 != 0) {
						local9 += this.kerning[(local7 << 8) + local23];
					}
					local7 = local23;
				}
			}
		}
		return local9;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIILjava/util/Random;I)I")
	public final int method2859(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(5) Random arg3, @OriginalArg(6) int arg4) {
		if (arg0 == null) {
			return 0;
		}
		arg3.setSeed((long) arg4);
		this.setEffectsAlpha(16777215, 0, (arg3.nextInt() & 0x1F) + 192);
		@Pc(21) int local21 = arg0.length();
		@Pc(24) int[] local24 = new int[local21];
		@Pc(26) int local26 = 0;
		for (@Pc(28) int local28 = 0; local28 < local21; local28++) {
			local24[local28] = local26;
			if ((arg3.nextInt() & 0x3) == 0) {
				local26++;
			}
		}
		this.renderOffset(arg0, arg1, arg2, local24, null);
		return local26;
	}

	@OriginalMember(owner = "client!rk", name = "c", descriptor = "(Lclient!na;I)I")
	public final int getParagraphLineCount(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1) {
		return this.splitParagraph(arg0, new int[] { arg1 }, lines);
	}

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;)V")
	private void parseStringForEffects(@OriginalArg(0) JString string) {
		try {
			if (string.startsWith(startColor)) {
				textColor = string.substring(4).parseHexString(16);
			} else if (string.strEquals(endColor)) {
				textColor = defaultTextColor;
			} else if (string.startsWith(FogManager.startTrans)) {
				opacity = string.substring(6).parseInt();
			} else if (string.strEquals(endTrans)) {
				opacity = defaultOpacity;
			} else if (string.startsWith(startStrikethrough)) {
				strikethroughColor = string.substring(4).parseHexString(16);
			} else if (string.strEquals(startDefaultStrikeThrough)) {
				strikethroughColor = 8388608;
			} else if (string.strEquals(endStrikeThrough)) {
				strikethroughColor = -1;
			} else if (string.startsWith(FogManager.startUnderline)) {
				underlineColor = string.substring(2).parseHexString(16);
			} else if (string.strEquals(startDefaultUnderline)) {
				underlineColor = 0;
			} else if (string.strEquals(endUnderline)) {
				underlineColor = -1;
			} else if (string.startsWith(FogManager.startShadow)) {
				shadowColor = string.substring(5).parseHexString(16);
			} else if (string.strEquals(startDefaultShadow)) {
				shadowColor = 0;
			} else if (string.strEquals(endShadow)) {
				shadowColor = defaultShadowColor;
			} else if (string.strEquals(lineBreak)) {
				this.setEffectsAlpha(defaultTextColor, defaultShadowColor, defaultOpacity);
			}
		} catch (@Pc(144) Exception exception) {
		}
	}

	@OriginalMember(owner = "client!rk", name = "d", descriptor = "(I)I")
	private int getGlyphWidth(@OriginalArg(0) int arg0) {
		return this.glyphWidths[arg0 & 0xFF];
	}

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;IIII)V")
	public final void renderRight(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg0 != null) {
			this.setEffects(arg3, arg4);
			this.drawBasicString(arg0, arg1 - this.getStringWidth(arg0), arg2);
		}
	}

	@OriginalMember(owner = "client!rk", name = "d", descriptor = "(Lclient!na;I)V")
	private void justify(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1) {
		@Pc(1) int local1 = 0;
		@Pc(3) boolean local3 = false;
		@Pc(7) int local7 = arg0.length();
		for (@Pc(9) int local9 = 0; local9 < local7; local9++) {
			@Pc(17) int local17 = arg0.charAt(local9);
			if (local17 == 60) {
				local3 = true;
			} else if (local17 == 62) {
				local3 = false;
			} else if (!local3 && local17 == 32) {
				local1++;
			}
		}
		if (local1 > 0) {
			spaceWidth = (arg1 - this.getStringWidth(arg0) << 8) / local1;
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;[I[Lclient!na;)I")
	public final int splitParagraph(@OriginalArg(0) JString arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) JString[] arg2) {
		if (arg0 == null) {
			return 0;
		}
		buffer.method3133(0);
		@Pc(9) int local9 = 0;
		@Pc(11) int local11 = 0;
		@Pc(13) int local13 = -1;
		@Pc(15) int local15 = 0;
		@Pc(17) byte local17 = 0;
		@Pc(19) int local19 = -1;
		@Pc(21) int local21 = 0;
		@Pc(23) int local23 = 0;
		@Pc(27) int local27 = arg0.length();
		for (@Pc(29) int local29 = 0; local29 < local27; local29++) {
			@Pc(37) int local37 = arg0.charAt(local29);
			if (local37 == 60) {
				local19 = local29;
			} else {
				if (local37 == 62 && local19 != -1) {
					@Pc(57) JString local57 = arg0.substring(local29, local19 + 1);
					local19 = -1;
					buffer.append(60);
					buffer.method3113(local57);
					buffer.append(62);
					if (local57.strEquals(lineBreak)) {
						if (arg2[local23] == null) {
							arg2[local23] = buffer.asString().substring(buffer.length(), local11);
						} else {
							arg2[local23].method3133(0);
							arg2[local23].method3122(buffer, local11, buffer.length());
						}
						local23++;
						local11 = buffer.length();
						local9 = 0;
						local13 = -1;
						local21 = 0;
					} else if (local57.strEquals(lessThan)) {
						local9 += this.getGlyphWidth(60);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 60];
						}
						local21 = 60;
					} else if (local57.strEquals(greaterThan)) {
						local9 += this.getGlyphWidth(62);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 62];
						}
						local21 = 62;
					} else if (local57.strEquals(nonBreakingSpace)) {
						local9 += this.getGlyphWidth(160);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 160];
						}
						local21 = 160;
					} else if (local57.strEquals(softHyphen)) {
						local9 += this.getGlyphWidth(173);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 173];
						}
						local21 = 173;
					} else if (local57.strEquals(multiplicationSymbol)) {
						local9 += this.getGlyphWidth(215);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 215];
						}
						local21 = 215;
					} else if (local57.strEquals(euroSymbol)) {
						local9 += this.getGlyphWidth(128);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 128];
						}
						local21 = 128;
					} else if (local57.strEquals(copyright)) {
						local9 += this.getGlyphWidth(169);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 169];
						}
						local21 = 169;
					} else if (local57.strEquals(registeredTrademark)) {
						local9 += this.getGlyphWidth(174);
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + 174];
						}
						local21 = 174;
					} else if (local57.startsWith(image)) {
						try {
							@Pc(377) int local377 = local57.substring(4).parseInt();
							local9 += this.nameIcons[local377].innerWidth;
							local21 = 0;
						} catch (@Pc(389) Exception local389) {
						}
					}
					local37 = 0;
				}
				if (local19 == -1) {
					if (local37 != 0) {
						buffer.append(local37);
						local9 += this.glyphWidths[local37];
						if (this.kerning != null && local21 != 0) {
							local9 += this.kerning[(local21 << 8) + local37];
						}
						local21 = local37;
					}
					if (local37 == 32) {
						local13 = buffer.length();
						local15 = local9;
						local17 = 1;
					}
					if (arg1 != null && local9 > arg1[local23 < arg1.length ? local23 : arg1.length - 1] && local13 >= 0) {
						if (arg2[local23] == null) {
							arg2[local23] = buffer.asString().substring(local13 - local17, local11);
						} else {
							arg2[local23].method3133(0);
							arg2[local23] = arg2[local23].method3122(buffer, local11, local13 - local17);
						}
						local23++;
						local11 = local13;
						local13 = -1;
						local9 -= local15;
						local21 = 0;
					}
					if (local37 == 45) {
						local13 = buffer.length();
						local15 = local9;
						local17 = 0;
					}
				}
			}
		}
		if (buffer.length() > local11) {
			if (arg2[local23] == null) {
				arg2[local23] = buffer.asString().substring(buffer.length(), local11);
			} else {
				arg2[local23].method3133(0);
				arg2[local23] = arg2[local23].method3122(buffer, local11, buffer.length());
			}
			local23++;
		}
		return local23;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIII)V")
	public final void renderShake(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		if (arg0 == null) {
			return;
		}
		this.setEffects(arg3, 0);
		@Pc(13) double local13 = 7.0D - (double) arg5 / 8.0D;
		if (local13 < 0.0D) {
			local13 = 0.0D;
		}
		@Pc(23) int local23 = arg0.length();
		@Pc(26) int[] local26 = new int[local23];
		for (@Pc(28) int local28 = 0; local28 < local23; local28++) {
			local26[local28] = (int) (Math.sin((double) local28 / 1.5D + (double) arg4 / 1.0D) * local13);
		}
		this.renderOffset(arg0, arg1 - this.getStringWidth(arg0) / 2, arg2, null, local26);
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIIIIIII)I")
	public final int renderParagraphAlpha(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9) {
		if (arg0 == null) {
			return 0;
		}
		this.setEffectsAlpha(arg5, arg6, 256);
		if (arg9 == 0) {
			arg9 = this.characterDefaultHeight;
		}
		@Pc(20) int[] local20 = new int[] { arg3 };
		if (arg4 < this.paragraphTopPadding + this.paragraphBottomPadding + arg9 && arg4 < arg9 + arg9) {
			local20 = null;
		}
		@Pc(42) int local42 = this.splitParagraph(arg0, local20, lines);
		if (arg8 == 3 && local42 == 1) {
			arg8 = 1;
		}
		@Pc(57) int local57;
		@Pc(118) int local118;
		if (arg8 == 0) {
			local57 = arg2 + this.paragraphTopPadding;
		} else if (arg8 == 1) {
			local57 = arg2 + this.paragraphTopPadding + (arg4 - this.paragraphTopPadding - this.paragraphBottomPadding - (local42 - 1) * arg9) / 2;
		} else if (arg8 == 2) {
			local57 = arg2 + arg4 - this.paragraphBottomPadding - (local42 - 1) * arg9;
		} else {
			local118 = (arg4 - this.paragraphTopPadding - this.paragraphBottomPadding - (local42 - 1) * arg9) / (local42 + 1);
			if (local118 < 0) {
				local118 = 0;
			}
			local57 = arg2 + this.paragraphTopPadding + local118;
			arg9 += local118;
		}
		for (local118 = 0; local118 < local42; local118++) {
			if (arg7 == 0) {
				this.drawBasicString(lines[local118], arg1, local57);
			} else if (arg7 == 1) {
				this.drawBasicString(lines[local118], arg1 + (arg3 - this.getStringWidth(lines[local118])) / 2, local57);
			} else if (arg7 == 2) {
				this.drawBasicString(lines[local118], arg1 + arg3 - this.getStringWidth(lines[local118]), local57);
			} else if (local118 == local42 - 1) {
				this.drawBasicString(lines[local118], arg1, local57);
			} else {
				this.justify(lines[local118], arg3);
				this.drawBasicString(lines[local118], arg1, local57);
				spaceWidth = 0;
			}
			local57 += arg9;
		}
		return local42;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIII)V")
	public final void renderWave2(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 == null) {
			return;
		}
		this.setEffects(arg3, 0);
		@Pc(10) int local10 = arg0.length();
		@Pc(13) int[] local13 = new int[local10];
		@Pc(16) int[] local16 = new int[local10];
		for (@Pc(18) int local18 = 0; local18 < local10; local18++) {
			local13[local18] = (int) (Math.sin((double) local18 / 5.0D + (double) arg4 / 5.0D) * 5.0D);
			local16[local18] = (int) (Math.sin((double) local18 / 3.0D + (double) arg4 / 5.0D) * 5.0D);
		}
		this.renderOffset(arg0, arg1 - this.getStringWidth(arg0) / 2, arg2, local13, local16);
	}

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;IIIII)V")
	public final void renderWave(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 == null) {
			return;
		}
		this.setEffects(arg3, 0);
		@Pc(10) int local10 = arg0.length();
		@Pc(13) int[] local13 = new int[local10];
		for (@Pc(15) int local15 = 0; local15 < local10; local15++) {
			local13[local15] = (int) (Math.sin((double) local15 / 2.0D + (double) arg4 / 5.0D) * 5.0D);
		}
		this.renderOffset(arg0, arg1 - this.getStringWidth(arg0) / 2, arg2, null, local13);
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "([Lclient!ok;[I)V")
	public final void setNameIcons(@OriginalArg(0) IndexedSprite[] arg0, @OriginalArg(1) int[] arg1) {
		if (arg1 != null && arg1.length != arg0.length) {
			throw new IllegalArgumentException();
		}
		this.nameIcons = arg0;
		this.nameIconHeights = arg1;
	}

	@OriginalMember(owner = "client!rk", name = "c", descriptor = "(II)V")
	private void setEffects(@OriginalArg(0) int color, @OriginalArg(1) int shadow) {
		strikethroughColor = -1;
		underlineColor = -1;
		defaultShadowColor = shadow;
		shadowColor = shadow;
		defaultTextColor = color;
		textColor = color;
		defaultOpacity = 256;
		opacity = 256;
		spaceWidth = 0;
		extraSpaceWidth = 0;
	}

	@OriginalMember(owner = "client!rk", name = "c", descriptor = "(Lclient!na;IIII)V")
	public final void renderCenter(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg0 != null) {
			this.setEffects(arg3, arg4);
			this.drawBasicString(arg0, arg1 - this.getStringWidth(arg0) / 2, arg2);
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "([B)V")
	private void decode(@OriginalArg(0) byte[] arg0) {
		this.glyphWidths = new int[256];
		@Pc(9) int local9;
		if (arg0.length == 257) {
			for (local9 = 0; local9 < this.glyphWidths.length; local9++) {
				this.glyphWidths[local9] = arg0[local9] & 0xFF;
			}
			this.characterDefaultHeight = arg0[256] & 0xFF;
			return;
		}
		local9 = 0;
		for (@Pc(37) int local37 = 0; local37 < 256; local37++) {
			this.glyphWidths[local37] = arg0[local9++] & 0xFF;
		}
		@Pc(55) int[] local55 = new int[256];
		@Pc(58) int[] local58 = new int[256];
		@Pc(60) int local60;
		for (local60 = 0; local60 < 256; local60++) {
			local55[local60] = arg0[local9++] & 0xFF;
		}
		for (local60 = 0; local60 < 256; local60++) {
			local58[local60] = arg0[local9++] & 0xFF;
		}
		@Pc(93) byte[][] local93 = new byte[256][];
		@Pc(109) int local109;
		for (@Pc(95) int local95 = 0; local95 < 256; local95++) {
			local93[local95] = new byte[local55[local95]];
			@Pc(107) byte local107 = 0;
			for (local109 = 0; local109 < local93[local95].length; local109++) {
				local107 += arg0[local9++];
				local93[local95][local109] = local107;
			}
		}
		@Pc(136) byte[][] local136 = new byte[256][];
		@Pc(138) int local138;
		for (local138 = 0; local138 < 256; local138++) {
			local136[local138] = new byte[local55[local138]];
			@Pc(150) byte local150 = 0;
			for (@Pc(152) int local152 = 0; local152 < local136[local138].length; local152++) {
				local150 += arg0[local9++];
				local136[local138][local152] = local150;
			}
		}
		this.kerning = new byte[65536];
		for (local138 = 0; local138 < 256; local138++) {
			if (local138 != 32 && local138 != 160) {
				for (local109 = 0; local109 < 256; local109++) {
					if (local109 != 32 && local109 != 160) {
						this.kerning[(local138 << 8) + local109] = (byte) method2870(local93, local136, local58, this.glyphWidths, local55, local138, local109);
					}
				}
			}
		}
		this.characterDefaultHeight = local58[32] + local55[32];
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(III)V")
	private void setEffectsAlpha(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		strikethroughColor = -1;
		underlineColor = -1;
		defaultShadowColor = arg1;
		shadowColor = arg1;
		defaultTextColor = arg0;
		textColor = arg0;
		defaultOpacity = arg2;
		opacity = arg2;
		spaceWidth = 0;
		extraSpaceWidth = 0;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIIIIILjava/util/Random;I[I)I")
	public final int method2878(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) Random arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int[] arg11) {
		if (arg0 == null) {
			return 0;
		}
		arg9.setSeed((long) arg10);
		this.setEffectsAlpha(arg5, arg6, (arg9.nextInt() & 0x1F) + 192);
		@Pc(21) int local21 = arg0.length();
		@Pc(24) int[] local24 = new int[local21];
		@Pc(26) int local26 = 0;
		@Pc(28) int local28;
		for (local28 = 0; local28 < local21; local28++) {
			local24[local28] = local26;
			if ((arg9.nextInt() & 0x3) == 0) {
				local26++;
			}
		}
		local28 = arg1;
		@Pc(50) int local50 = arg2 + this.paragraphTopPadding;
		@Pc(52) int local52 = -1;
		if (arg8 == 1) {
			local50 += (arg4 - this.paragraphTopPadding - this.paragraphBottomPadding) / 2;
		} else if (arg8 == 2) {
			local50 = arg2 + arg4 - this.paragraphBottomPadding;
		}
		if (arg7 == 1) {
			local52 = this.getStringWidth(arg0) + local26;
			local28 = arg1 + (arg3 - local52) / 2;
		} else if (arg7 == 2) {
			local52 = this.getStringWidth(arg0) + local26;
			local28 = arg1 + arg3 - local52;
		}
		this.renderOffset(arg0, local28, local50, local24, null);
		if (arg11 != null) {
			if (local52 == -1) {
				local52 = this.getStringWidth(arg0) + local26;
			}
			arg11[0] = local28;
			arg11[1] = local50 - this.paragraphTopPadding;
			arg11[2] = local52;
			arg11[3] = this.paragraphTopPadding + this.paragraphBottomPadding;
		}
		return local26;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;II[I[I)V")
	private void renderOffset(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4) {
		@Pc(4) int local4 = arg2 - this.characterDefaultHeight;
		@Pc(6) int local6 = -1;
		@Pc(8) int local8 = 0;
		@Pc(10) int local10 = 0;
		@Pc(14) int local14 = arg0.length();
		for (@Pc(16) int local16 = 0; local16 < local14; local16++) {
			@Pc(24) int local24 = arg0.charAt(local16);
			if (local24 == 60) {
				local6 = local16;
			} else {
				@Pc(121) int local121;
				@Pc(130) int local130;
				@Pc(141) int local141;
				if (local24 == 62 && local6 != -1) {
					@Pc(44) JString local44 = arg0.substring(local16, local6 + 1);
					local6 = -1;
					if (local44.strEquals(lessThan)) {
						local24 = 60;
					} else if (local44.strEquals(greaterThan)) {
						local24 = 62;
					} else if (local44.strEquals(nonBreakingSpace)) {
						local24 = 160;
					} else if (local44.strEquals(softHyphen)) {
						local24 = 173;
					} else if (local44.strEquals(multiplicationSymbol)) {
						local24 = 215;
					} else if (local44.strEquals(euroSymbol)) {
						local24 = 128;
					} else if (local44.strEquals(copyright)) {
						local24 = 169;
					} else {
						if (!local44.strEquals(registeredTrademark)) {
							if (local44.startsWith(image)) {
								try {
									if (arg3 == null) {
										local121 = 0;
									} else {
										local121 = arg3[local10];
									}
									if (arg4 == null) {
										local130 = 0;
									} else {
										local130 = arg4[local10];
									}
									local10++;
									local141 = local44.substring(4).parseInt();
									@Pc(146) IndexedSprite local146 = this.nameIcons[local141];
									@Pc(157) int local157 = this.nameIconHeights == null ? local146.innerHeight : this.nameIconHeights[local141];
									if (opacity == 256) {
										local146.renderTransparent(arg1 + local121, local4 + this.characterDefaultHeight - local157 + local130);
									} else {
										local146.drawImageAlpha(arg1 + local121, local4 + this.characterDefaultHeight - local157 + local130, opacity);
									}
									arg1 += local146.innerWidth;
									local8 = 0;
								} catch (@Pc(197) Exception local197) {
								}
							} else {
								this.parseStringForEffects(local44);
							}
							continue;
						}
						local24 = 174;
					}
				}
				if (local6 == -1) {
					if (this.kerning != null && local8 != 0) {
						arg1 += this.kerning[(local8 << 8) + local24];
					}
					@Pc(226) int local226 = this.spriteInnerWidths[local24];
					local121 = this.spriteInnerHeights[local24];
					if (arg3 == null) {
						local130 = 0;
					} else {
						local130 = arg3[local10];
					}
					if (arg4 == null) {
						local141 = 0;
					} else {
						local141 = arg4[local10];
					}
					local10++;
					if (local24 == 32) {
						if (spaceWidth > 0) {
							extraSpaceWidth += spaceWidth;
							arg1 += extraSpaceWidth >> 8;
							extraSpaceWidth &= 0xFF;
						}
					} else if (opacity == 256) {
						if (shadowColor != -1) {
							this.renderGlyph(local24, arg1 + this.spriteXOffsets[local24] + local130 + 1, local4 + this.spriteYOffsets[local24] + 1 + local141, local226, local121, shadowColor);
						}
						this.renderGlyph(local24, arg1 + this.spriteXOffsets[local24] + local130, local4 + this.spriteYOffsets[local24] + local141, local226, local121, textColor);
					} else {
						if (shadowColor != -1) {
							this.renderGlyphTransparent(local24, arg1 + this.spriteXOffsets[local24] + local130 + 1, local4 + this.spriteYOffsets[local24] + 1 + local141, local226, local121, shadowColor, opacity);
						}
						this.renderGlyphTransparent(local24, arg1 + this.spriteXOffsets[local24] + local130, local4 + this.spriteYOffsets[local24] + local141, local226, local121, textColor, opacity);
					}
					@Pc(387) int local387 = this.glyphWidths[local24];
					if (strikethroughColor != -1) {
						if (GlRenderer.enabled) {
							GlRaster.method1174(arg1, local4 + (int) ((double) this.characterDefaultHeight * 0.7D), local387, strikethroughColor);
						} else {
							SoftwareRaster.drawHorizontalLine(arg1, local4 + (int) ((double) this.characterDefaultHeight * 0.7D), local387, strikethroughColor);
						}
					}
					if (underlineColor != -1) {
						if (GlRenderer.enabled) {
							GlRaster.method1174(arg1, local4 + this.characterDefaultHeight, local387, underlineColor);
						} else {
							SoftwareRaster.drawHorizontalLine(arg1, local4 + this.characterDefaultHeight, local387, underlineColor);
						}
					}
					arg1 += local387;
					local8 = local24;
				}
			}
		}
	}
}
