package com.jagex.runetek4.cache.media;

import java.awt.*;
import java.util.Random;

import com.jagex.runetek4.*;
import com.jagex.runetek4.node.CachedNode;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!rk")
public abstract class Font extends CachedNode {

	@OriginalMember(owner = "runetek4.client!rk", name = "K", descriptor = "Lclient!na;")
	public static final JString greaterThan = Static28.parse("gt");
	@OriginalMember(owner = "runetek4.client!rk", name = "L", descriptor = "Lclient!na;")
	public static final JString lessThan = Static28.parse("lt");
	@OriginalMember(owner = "runetek4.client!rk", name = "R", descriptor = "Lclient!na;")
	public static final JString euroSymbol = Static28.parse("euro");
	@OriginalMember(owner = "runetek4.client!rk", name = "cb", descriptor = "Lclient!na;")
	public static final JString multiplicationSymbol = Static28.parse("times");
	@OriginalMember(owner = "runetek4.client!rk", name = "fb", descriptor = "Lclient!na;")
	public static final JString nonBreakingSpace = Static28.parse("nbsp");
	@OriginalMember(owner = "runetek4.client!rk", name = "mb", descriptor = "Lclient!na;")
	public static final JString image = Static28.parse("img=");
	@OriginalMember(owner = "runetek4.client!rk", name = "ob", descriptor = "Lclient!na;")
	public static final JString copyright = Static28.parse("copy");
	@OriginalMember(owner = "runetek4.client!rk", name = "qb", descriptor = "Lclient!na;")
	public static final JString registeredTrademark = Static28.parse("reg");
	@OriginalMember(owner = "runetek4.client!rk", name = "N", descriptor = "Lclient!na;")
	public static final JString softHyphen = Static28.parse("shy");
	@OriginalMember(owner = "runetek4.client!rk", name = "xb", descriptor = "I")
	public static int opacity = 256;
	@OriginalMember(owner = "runetek4.client!rk", name = "Cb", descriptor = "I")
	public static int textColor = 0;
    @OriginalMember(owner = "runetek4.client!wl", name = "q", descriptor = "Lclient!rk;")
    public static Font b12Full;
    @OriginalMember(owner = "client!rk", name = "W", descriptor = "[I")
	private int[] moderatorIcon;

	@OriginalMember(owner = "client!rk", name = "gb", descriptor = "[B")
	private byte[] aByteArray50;

	@OriginalMember(owner = "client!rk", name = "jb", descriptor = "[I")
	private int[] anIntArray348;

	@OriginalMember(owner = "client!rk", name = "nb", descriptor = "[Lclient!ok;")
	private IndexedSprite[] aClass36Array11;

	@OriginalMember(owner = "client!rk", name = "ab", descriptor = "I")
	public int characterDefaultHeight = 0;

	@OriginalMember(owner = "client!rk", name = "S", descriptor = "[I")
	private int[] anIntArray344;

	@OriginalMember(owner = "client!rk", name = "db", descriptor = "[I")
	private int[] anIntArray347;

	@OriginalMember(owner = "client!rk", name = "I", descriptor = "[I")
	protected int[] anIntArray343;

	@OriginalMember(owner = "client!rk", name = "U", descriptor = "[I")
	protected int[] anIntArray345;

	@OriginalMember(owner = "client!rk", name = "V", descriptor = "I")
	private int anInt3625;

	@OriginalMember(owner = "client!rk", name = "J", descriptor = "I")
	private int anInt3624;

	@OriginalMember(owner = "client!rk", name = "<init>", descriptor = "([B[I[I[I[I)V")
	protected Font(@OriginalArg(0) byte[] arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) int[] arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4) {
		this.anIntArray344 = arg1;
		this.anIntArray347 = arg2;
		this.anIntArray343 = arg3;
		this.anIntArray345 = arg4;
		this.method2876(arg0);
		@Pc(21) int local21 = Integer.MAX_VALUE;
		@Pc(23) int local23 = Integer.MIN_VALUE;
		for (@Pc(25) int local25 = 0; local25 < 256; local25++) {
			if (this.anIntArray347[local25] < local21 && this.anIntArray345[local25] != 0) {
				local21 = this.anIntArray347[local25];
			}
			if (this.anIntArray347[local25] + this.anIntArray345[local25] > local23) {
				local23 = this.anIntArray347[local25] + this.anIntArray345[local25];
			}
		}
		this.anInt3625 = this.characterDefaultHeight - local21;
		this.anInt3624 = local23 - this.characterDefaultHeight;
	}

	@OriginalMember(owner = "client!rk", name = "<init>", descriptor = "([B)V")
	public Font(@OriginalArg(0) byte[] arg0) {
		this.method2876(arg0);
	}

    @OriginalMember(owner = "runetek4.client!j", name = "a", descriptor = "(BZLclient!na;)V")
    public static void drawTextOnScreen(@OriginalArg(1) boolean arg0, @OriginalArg(2) JString arg1) {
        @Pc(24) int local24 = Static215.aClass3_Sub2_Sub9_32.method2856(arg1, 250);
        @Pc(31) int local31 = Static215.aClass3_Sub2_Sub9_32.method2860(arg1, 250) * 13;
        if (GlRenderer.enabled) {
            Static46.method1186(6, 6, local24 + 4 + 4, local31 + 8, 0);
            Static46.method1179(6, 6, local24 + 4 + 4, local31 + 4 + 4, 16777215);
        } else {
            Rasterizer.drawFilledRectangle(6, 6, local24 + 4 + 4, local31 + 8, 0);
            Rasterizer.drawUnfilledRectangle(6, 6, local24 + 8, 4 + 4 + local31, 16777215);
        }
        Static215.aClass3_Sub2_Sub9_32.method2852(arg1, 10, 10, local24, local31, 16777215, -1, 1, 1, 0);
        Static133.method4012(6, local24 + 8, 6, local31 + 4 + 4);
        if (!arg0) {
            Static121.method2407(10, 10, local31, local24);
        } else if (GlRenderer.enabled) {
            GlRenderer.method4153();
        } else {
            try {
                @Pc(159) Graphics local159 = GameShell.canvas.getGraphics();
                Static260.frameBuffer.draw(local159);
            } catch (@Pc(167) Exception local167) {
                GameShell.canvas.repaint();
            }
        }
    }

    @OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIIIIII)I")
	public final int method2852(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9) {
		return this.method2869(arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9);
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
					if (effectString.method3108(lessThan)) {
						c = 60;
					} else if (effectString.method3108(greaterThan)) {
						c = 62;
					} else if (effectString.method3108(nonBreakingSpace)) {
						c = 160;
					} else if (effectString.method3108(softHyphen)) {
						c = 173;
					} else if (effectString.method3108(multiplicationSymbol)) {
						c = 215;
					} else if (effectString.method3108(euroSymbol)) {
						c = 128;
					} else if (effectString.method3108(copyright)) {
						c = 169;
					} else {
						if (!effectString.method3108(registeredTrademark)) {
							if (effectString.startsWith(image)) {
								try {
									icon = effectString.substring(4).method3132();
									@Pc(125) IndexedSprite nameIcon = this.aClass36Array11[icon];
									@Pc(136) int imageHeight = this.moderatorIcon == null ? nameIcon.anInt4276 : this.moderatorIcon[icon];
									if (opacity == 256) {
										nameIcon.drawImage(x, y + this.characterDefaultHeight - imageHeight);
									} else {
										nameIcon.drawImageAlpha(x, y + this.characterDefaultHeight - imageHeight, opacity);
									}
									x += nameIcon.maxWidth;
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
					if (this.aByteArray50 != null && local8 != 0) {
						x += this.aByteArray50[(local8 << 8) + c];
					}
					@Pc(197) int local197 = this.anIntArray343[c];
					icon = this.anIntArray345[c];
					if (c == 32) {
						if (Static218.anInt3636 > 0) {
							Static218.anInt3629 += Static218.anInt3636;
							x += Static218.anInt3629 >> 8;
							Static218.anInt3629 &= 0xFF;
						}
					} else if (opacity == 256) {
						if (Static218.shadowColor != -1) {
							this.method2854(c, x + this.anIntArray344[c] + 1, y + this.anIntArray347[c] + 1, local197, icon, Static218.shadowColor);
						}
						this.method2854(c, x + this.anIntArray344[c], y + this.anIntArray347[c], local197, icon, textColor);
					} else {
						if (Static218.shadowColor != -1) {
							this.method2855(c, x + this.anIntArray344[c] + 1, y + this.anIntArray347[c] + 1, local197, icon, Static218.shadowColor, opacity);
						}
						this.method2855(c, x + this.anIntArray344[c], y + this.anIntArray347[c], local197, icon, textColor, opacity);
					}
					@Pc(323) int local323 = this.anIntArray348[c];
					if (Static218.strikethroughColor != -1) {
						if (GlRenderer.enabled) {
							Static46.method1174(x, y + (int) ((double) this.characterDefaultHeight * 0.7D), local323, Static218.strikethroughColor);
						} else {
							Rasterizer.drawHorizontalLine(x, y + (int) ((double) this.characterDefaultHeight * 0.7D), local323, Static218.strikethroughColor);
						}
					}
					if (Static218.underlineColor != -1) {
						if (GlRenderer.enabled) {
							Static46.method1174(x, y + this.characterDefaultHeight + 1, local323, Static218.underlineColor);
						} else {
							Rasterizer.drawHorizontalLine(x, y + this.characterDefaultHeight + 1, local323, Static218.underlineColor);
						}
					}
					x += local323;
					local8 = c;
				}
			}
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(IIIIIIZ)V")
	protected abstract void method2854(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5);

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(IIIIIIIZ)V")
	protected abstract void method2855(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6);

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;I)I")
	public final int method2856(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1) {
		@Pc(10) int local10 = this.method2867(arg0, new int[] { arg1 }, Static218.aClass100Array110);
		@Pc(12) int local12 = 0;
		for (@Pc(14) int local14 = 0; local14 < local10; local14++) {
			@Pc(23) int local23 = this.method2858(Static218.aClass100Array110[local14]);
			if (local23 > local12) {
				local12 = local23;
			}
		}
		return local12;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIII)V")
	public final void drawString(@OriginalArg(0) JString string, @OriginalArg(1) int x, @OriginalArg(2) int y, @OriginalArg(3) int color, @OriginalArg(4) int shadow) {
		if (string != null) {
			this.setEffects(color, shadow);
			this.drawBasicString(string, x, y);
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;)I")
	public final int method2858(@OriginalArg(0) JString arg0) {
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
					if (local43.method3108(lessThan)) {
						local23 = 60;
					} else if (local43.method3108(greaterThan)) {
						local23 = 62;
					} else if (local43.method3108(nonBreakingSpace)) {
						local23 = 160;
					} else if (local43.method3108(softHyphen)) {
						local23 = 173;
					} else if (local43.method3108(multiplicationSymbol)) {
						local23 = 215;
					} else if (local43.method3108(euroSymbol)) {
						local23 = 128;
					} else if (local43.method3108(copyright)) {
						local23 = 169;
					} else {
						if (!local43.method3108(registeredTrademark)) {
							if (local43.startsWith(image)) {
								try {
									@Pc(121) int local121 = local43.substring(4).method3132();
									local9 += this.aClass36Array11[local121].maxWidth;
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
					local9 += this.anIntArray348[local23];
					if (this.aByteArray50 != null && local7 != 0) {
						local9 += this.aByteArray50[(local7 << 8) + local23];
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
		this.method2879(arg0, arg1, arg2, local24, null);
		return local26;
	}

	@OriginalMember(owner = "client!rk", name = "c", descriptor = "(Lclient!na;I)I")
	public final int method2860(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1) {
		return this.method2867(arg0, new int[] { arg1 }, Static218.aClass100Array110);
	}

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;)V")
	private void parseStringForEffects(@OriginalArg(0) JString string) {
		try {
			if (string.startsWith(Static218.startColor)) {
				textColor = string.substring(4).method3110(16);
			} else if (string.method3108(Static218.endColor)) {
				textColor = Static218.defaultTextColor;
			} else if (string.startsWith(Static218.startTrans)) {
				opacity = string.substring(6).method3132();
			} else if (string.method3108(Static218.endTrans)) {
				opacity = Static218.defaultOpacity;
			} else if (string.startsWith(Static218.startStrikethrough)) {
				Static218.strikethroughColor = string.substring(4).method3110(16);
			} else if (string.method3108(Static218.startDefaultStrikeThrough)) {
				Static218.strikethroughColor = 8388608;
			} else if (string.method3108(Static218.endStrikeThrough)) {
				Static218.strikethroughColor = -1;
			} else if (string.startsWith(Static218.startUnderline)) {
				Static218.underlineColor = string.substring(2).method3110(16);
			} else if (string.method3108(Static218.startDefaultUnderline)) {
				Static218.underlineColor = 0;
			} else if (string.method3108(Static218.endUnderline)) {
				Static218.underlineColor = -1;
			} else if (string.startsWith(Static218.startShadow)) {
				Static218.shadowColor = string.substring(5).method3110(16);
			} else if (string.method3108(Static218.startDefaultShadow)) {
				Static218.shadowColor = 0;
			} else if (string.method3108(Static218.endShadow)) {
				Static218.shadowColor = Static218.defaultShadowColor;
			} else if (string.method3108(Static218.lineBreak)) {
				this.setEffectsAlpha(Static218.defaultTextColor, Static218.defaultShadowColor, Static218.defaultOpacity);
			}
		} catch (@Pc(144) Exception exception) {
		}
	}

	@OriginalMember(owner = "client!rk", name = "d", descriptor = "(I)I")
	private int method2863(@OriginalArg(0) int arg0) {
		return this.anIntArray348[arg0 & 0xFF];
	}

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;IIII)V")
	public final void method2864(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg0 != null) {
			this.setEffects(arg3, arg4);
			this.drawBasicString(arg0, arg1 - this.method2858(arg0), arg2);
		}
	}

	@OriginalMember(owner = "client!rk", name = "d", descriptor = "(Lclient!na;I)V")
	private void method2866(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1) {
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
			Static218.anInt3636 = (arg1 - this.method2858(arg0) << 8) / local1;
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;[I[Lclient!na;)I")
	public final int method2867(@OriginalArg(0) JString arg0, @OriginalArg(1) int[] arg1, @OriginalArg(2) JString[] arg2) {
		if (arg0 == null) {
			return 0;
		}
		Static218.aClass100_720.method3133(0);
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
					Static218.aClass100_720.method3152(60);
					Static218.aClass100_720.method3113(local57);
					Static218.aClass100_720.method3152(62);
					if (local57.method3108(Static218.lineBreak)) {
						if (arg2[local23] == null) {
							arg2[local23] = Static218.aClass100_720.method3143().substring(Static218.aClass100_720.length(), local11);
						} else {
							arg2[local23].method3133(0);
							arg2[local23].method3122(Static218.aClass100_720, local11, Static218.aClass100_720.length());
						}
						local23++;
						local11 = Static218.aClass100_720.length();
						local9 = 0;
						local13 = -1;
						local21 = 0;
					} else if (local57.method3108(lessThan)) {
						local9 += this.method2863(60);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 60];
						}
						local21 = 60;
					} else if (local57.method3108(greaterThan)) {
						local9 += this.method2863(62);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 62];
						}
						local21 = 62;
					} else if (local57.method3108(nonBreakingSpace)) {
						local9 += this.method2863(160);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 160];
						}
						local21 = 160;
					} else if (local57.method3108(softHyphen)) {
						local9 += this.method2863(173);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 173];
						}
						local21 = 173;
					} else if (local57.method3108(multiplicationSymbol)) {
						local9 += this.method2863(215);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 215];
						}
						local21 = 215;
					} else if (local57.method3108(euroSymbol)) {
						local9 += this.method2863(128);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 128];
						}
						local21 = 128;
					} else if (local57.method3108(copyright)) {
						local9 += this.method2863(169);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 169];
						}
						local21 = 169;
					} else if (local57.method3108(registeredTrademark)) {
						local9 += this.method2863(174);
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + 174];
						}
						local21 = 174;
					} else if (local57.startsWith(image)) {
						try {
							@Pc(377) int local377 = local57.substring(4).method3132();
							local9 += this.aClass36Array11[local377].maxWidth;
							local21 = 0;
						} catch (@Pc(389) Exception local389) {
						}
					}
					local37 = 0;
				}
				if (local19 == -1) {
					if (local37 != 0) {
						Static218.aClass100_720.method3152(local37);
						local9 += this.anIntArray348[local37];
						if (this.aByteArray50 != null && local21 != 0) {
							local9 += this.aByteArray50[(local21 << 8) + local37];
						}
						local21 = local37;
					}
					if (local37 == 32) {
						local13 = Static218.aClass100_720.length();
						local15 = local9;
						local17 = 1;
					}
					if (arg1 != null && local9 > arg1[local23 < arg1.length ? local23 : arg1.length - 1] && local13 >= 0) {
						if (arg2[local23] == null) {
							arg2[local23] = Static218.aClass100_720.method3143().substring(local13 - local17, local11);
						} else {
							arg2[local23].method3133(0);
							arg2[local23] = arg2[local23].method3122(Static218.aClass100_720, local11, local13 - local17);
						}
						local23++;
						local11 = local13;
						local13 = -1;
						local9 -= local15;
						local21 = 0;
					}
					if (local37 == 45) {
						local13 = Static218.aClass100_720.length();
						local15 = local9;
						local17 = 0;
					}
				}
			}
		}
		if (Static218.aClass100_720.length() > local11) {
			if (arg2[local23] == null) {
				arg2[local23] = Static218.aClass100_720.method3143().substring(Static218.aClass100_720.length(), local11);
			} else {
				arg2[local23].method3133(0);
				arg2[local23] = arg2[local23].method3122(Static218.aClass100_720, local11, Static218.aClass100_720.length());
			}
			local23++;
		}
		return local23;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIII)V")
	public final void method2868(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
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
		this.method2879(arg0, arg1 - this.method2858(arg0) / 2, arg2, null, local26);
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIIIIIIII)I")
	public final int method2869(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(8) int arg7, @OriginalArg(9) int arg8, @OriginalArg(10) int arg9) {
		if (arg0 == null) {
			return 0;
		}
		this.setEffectsAlpha(arg5, arg6, 256);
		if (arg9 == 0) {
			arg9 = this.characterDefaultHeight;
		}
		@Pc(20) int[] local20 = new int[] { arg3 };
		if (arg4 < this.anInt3625 + this.anInt3624 + arg9 && arg4 < arg9 + arg9) {
			local20 = null;
		}
		@Pc(42) int local42 = this.method2867(arg0, local20, Static218.aClass100Array110);
		if (arg8 == 3 && local42 == 1) {
			arg8 = 1;
		}
		@Pc(57) int local57;
		@Pc(118) int local118;
		if (arg8 == 0) {
			local57 = arg2 + this.anInt3625;
		} else if (arg8 == 1) {
			local57 = arg2 + this.anInt3625 + (arg4 - this.anInt3625 - this.anInt3624 - (local42 - 1) * arg9) / 2;
		} else if (arg8 == 2) {
			local57 = arg2 + arg4 - this.anInt3624 - (local42 - 1) * arg9;
		} else {
			local118 = (arg4 - this.anInt3625 - this.anInt3624 - (local42 - 1) * arg9) / (local42 + 1);
			if (local118 < 0) {
				local118 = 0;
			}
			local57 = arg2 + this.anInt3625 + local118;
			arg9 += local118;
		}
		for (local118 = 0; local118 < local42; local118++) {
			if (arg7 == 0) {
				this.drawBasicString(Static218.aClass100Array110[local118], arg1, local57);
			} else if (arg7 == 1) {
				this.drawBasicString(Static218.aClass100Array110[local118], arg1 + (arg3 - this.method2858(Static218.aClass100Array110[local118])) / 2, local57);
			} else if (arg7 == 2) {
				this.drawBasicString(Static218.aClass100Array110[local118], arg1 + arg3 - this.method2858(Static218.aClass100Array110[local118]), local57);
			} else if (local118 == local42 - 1) {
				this.drawBasicString(Static218.aClass100Array110[local118], arg1, local57);
			} else {
				this.method2866(Static218.aClass100Array110[local118], arg3);
				this.drawBasicString(Static218.aClass100Array110[local118], arg1, local57);
				Static218.anInt3636 = 0;
			}
			local57 += arg9;
		}
		return local42;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;IIIII)V")
	public final void method2871(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
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
		this.method2879(arg0, arg1 - this.method2858(arg0) / 2, arg2, local13, local16);
	}

	@OriginalMember(owner = "client!rk", name = "b", descriptor = "(Lclient!na;IIIII)V")
	public final void method2872(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4) {
		if (arg0 == null) {
			return;
		}
		this.setEffects(arg3, 0);
		@Pc(10) int local10 = arg0.length();
		@Pc(13) int[] local13 = new int[local10];
		for (@Pc(15) int local15 = 0; local15 < local10; local15++) {
			local13[local15] = (int) (Math.sin((double) local15 / 2.0D + (double) arg4 / 5.0D) * 5.0D);
		}
		this.method2879(arg0, arg1 - this.method2858(arg0) / 2, arg2, null, local13);
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "([Lclient!ok;[I)V")
	public final void method2873(@OriginalArg(0) IndexedSprite[] arg0, @OriginalArg(1) int[] arg1) {
		if (arg1 != null && arg1.length != arg0.length) {
			throw new IllegalArgumentException();
		}
		this.aClass36Array11 = arg0;
		this.moderatorIcon = arg1;
	}

	@OriginalMember(owner = "client!rk", name = "c", descriptor = "(II)V")
	private void setEffects(@OriginalArg(0) int color, @OriginalArg(1) int shadow) {
		Static218.strikethroughColor = -1;
		Static218.underlineColor = -1;
		Static218.defaultShadowColor = shadow;
		Static218.shadowColor = shadow;
		Static218.defaultTextColor = color;
		textColor = color;
		Static218.defaultOpacity = 256;
		opacity = 256;
		Static218.anInt3636 = 0;
		Static218.anInt3629 = 0;
	}

	@OriginalMember(owner = "client!rk", name = "c", descriptor = "(Lclient!na;IIII)V")
	public final void method2875(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4) {
		if (arg0 != null) {
			this.setEffects(arg3, arg4);
			this.drawBasicString(arg0, arg1 - this.method2858(arg0) / 2, arg2);
		}
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "([B)V")
	private void method2876(@OriginalArg(0) byte[] arg0) {
		this.anIntArray348 = new int[256];
		@Pc(9) int local9;
		if (arg0.length == 257) {
			for (local9 = 0; local9 < this.anIntArray348.length; local9++) {
				this.anIntArray348[local9] = arg0[local9] & 0xFF;
			}
			this.characterDefaultHeight = arg0[256] & 0xFF;
			return;
		}
		local9 = 0;
		for (@Pc(37) int local37 = 0; local37 < 256; local37++) {
			this.anIntArray348[local37] = arg0[local9++] & 0xFF;
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
		this.aByteArray50 = new byte[65536];
		for (local138 = 0; local138 < 256; local138++) {
			if (local138 != 32 && local138 != 160) {
				for (local109 = 0; local109 < 256; local109++) {
					if (local109 != 32 && local109 != 160) {
						this.aByteArray50[(local138 << 8) + local109] = (byte) Static218.method2870(local93, local136, local58, this.anIntArray348, local55, local138, local109);
					}
				}
			}
		}
		this.characterDefaultHeight = local58[32] + local55[32];
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(III)V")
	private void setEffectsAlpha(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		Static218.strikethroughColor = -1;
		Static218.underlineColor = -1;
		Static218.defaultShadowColor = arg1;
		Static218.shadowColor = arg1;
		Static218.defaultTextColor = arg0;
		textColor = arg0;
		Static218.defaultOpacity = arg2;
		opacity = arg2;
		Static218.anInt3636 = 0;
		Static218.anInt3629 = 0;
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
		@Pc(50) int local50 = arg2 + this.anInt3625;
		@Pc(52) int local52 = -1;
		if (arg8 == 1) {
			local50 += (arg4 - this.anInt3625 - this.anInt3624) / 2;
		} else if (arg8 == 2) {
			local50 = arg2 + arg4 - this.anInt3624;
		}
		if (arg7 == 1) {
			local52 = this.method2858(arg0) + local26;
			local28 = arg1 + (arg3 - local52) / 2;
		} else if (arg7 == 2) {
			local52 = this.method2858(arg0) + local26;
			local28 = arg1 + arg3 - local52;
		}
		this.method2879(arg0, local28, local50, local24, null);
		if (arg11 != null) {
			if (local52 == -1) {
				local52 = this.method2858(arg0) + local26;
			}
			arg11[0] = local28;
			arg11[1] = local50 - this.anInt3625;
			arg11[2] = local52;
			arg11[3] = this.anInt3625 + this.anInt3624;
		}
		return local26;
	}

	@OriginalMember(owner = "client!rk", name = "a", descriptor = "(Lclient!na;II[I[I)V")
	private void method2879(@OriginalArg(0) JString arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int[] arg3, @OriginalArg(4) int[] arg4) {
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
					if (local44.method3108(lessThan)) {
						local24 = 60;
					} else if (local44.method3108(greaterThan)) {
						local24 = 62;
					} else if (local44.method3108(nonBreakingSpace)) {
						local24 = 160;
					} else if (local44.method3108(softHyphen)) {
						local24 = 173;
					} else if (local44.method3108(multiplicationSymbol)) {
						local24 = 215;
					} else if (local44.method3108(euroSymbol)) {
						local24 = 128;
					} else if (local44.method3108(copyright)) {
						local24 = 169;
					} else {
						if (!local44.method3108(registeredTrademark)) {
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
									local141 = local44.substring(4).method3132();
									@Pc(146) IndexedSprite local146 = this.aClass36Array11[local141];
									@Pc(157) int local157 = this.moderatorIcon == null ? local146.anInt4276 : this.moderatorIcon[local141];
									if (opacity == 256) {
										local146.drawImage(arg1 + local121, local4 + this.characterDefaultHeight - local157 + local130);
									} else {
										local146.drawImageAlpha(arg1 + local121, local4 + this.characterDefaultHeight - local157 + local130, opacity);
									}
									arg1 += local146.maxWidth;
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
					if (this.aByteArray50 != null && local8 != 0) {
						arg1 += this.aByteArray50[(local8 << 8) + local24];
					}
					@Pc(226) int local226 = this.anIntArray343[local24];
					local121 = this.anIntArray345[local24];
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
						if (Static218.anInt3636 > 0) {
							Static218.anInt3629 += Static218.anInt3636;
							arg1 += Static218.anInt3629 >> 8;
							Static218.anInt3629 &= 0xFF;
						}
					} else if (opacity == 256) {
						if (Static218.shadowColor != -1) {
							this.method2854(local24, arg1 + this.anIntArray344[local24] + local130 + 1, local4 + this.anIntArray347[local24] + 1 + local141, local226, local121, Static218.shadowColor);
						}
						this.method2854(local24, arg1 + this.anIntArray344[local24] + local130, local4 + this.anIntArray347[local24] + local141, local226, local121, textColor);
					} else {
						if (Static218.shadowColor != -1) {
							this.method2855(local24, arg1 + this.anIntArray344[local24] + local130 + 1, local4 + this.anIntArray347[local24] + 1 + local141, local226, local121, Static218.shadowColor, opacity);
						}
						this.method2855(local24, arg1 + this.anIntArray344[local24] + local130, local4 + this.anIntArray347[local24] + local141, local226, local121, textColor, opacity);
					}
					@Pc(387) int local387 = this.anIntArray348[local24];
					if (Static218.strikethroughColor != -1) {
						if (GlRenderer.enabled) {
							Static46.method1174(arg1, local4 + (int) ((double) this.characterDefaultHeight * 0.7D), local387, Static218.strikethroughColor);
						} else {
							Rasterizer.drawHorizontalLine(arg1, local4 + (int) ((double) this.characterDefaultHeight * 0.7D), local387, Static218.strikethroughColor);
						}
					}
					if (Static218.underlineColor != -1) {
						if (GlRenderer.enabled) {
							Static46.method1174(arg1, local4 + this.characterDefaultHeight, local387, Static218.underlineColor);
						} else {
							Rasterizer.drawHorizontalLine(arg1, local4 + this.characterDefaultHeight, local387, Static218.underlineColor);
						}
					}
					arg1 += local387;
					local8 = local24;
				}
			}
		}
	}
}
