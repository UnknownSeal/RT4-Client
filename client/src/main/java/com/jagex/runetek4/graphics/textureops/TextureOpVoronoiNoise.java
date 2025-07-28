package com.jagex.runetek4.graphics.textureops;

import java.util.Random;

import com.jagex.runetek4.ByteArrayNodeSecondary;
import com.jagex.runetek4.core.datastruct.LruHashTable;
import com.jagex.runetek4.graphics.texture.Texture;
import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.util.RandomUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!hm")
public final class TextureOpVoronoiNoise extends TextureOp {

	@OriginalMember(owner = "client!ec", name = "d", descriptor = "Lclient!gn;")
	public static final LruHashTable aClass54_5 = new LruHashTable(16);

	@OriginalMember(owner = "runetek4.client!cl", name = "X", descriptor = "I")
	public static int anInt1091;

	@OriginalMember(owner = "runetek4.client!jg", name = "g", descriptor = "I")
	public static int anInt3041;

	@OriginalMember(owner = "runetek4.client!uf", name = "p", descriptor = "I")
	public static int anInt5443;

	@OriginalMember(owner = "runetek4.client!ui", name = "Q", descriptor = "I")
	public static int anInt5526;

	@OriginalMember(owner = "runetek4.client!hm", name = "S", descriptor = "I")
	private int anInt2635 = 2;

	@OriginalMember(owner = "runetek4.client!hm", name = "U", descriptor = "I")
	private int anInt2636 = 2048;

	@OriginalMember(owner = "runetek4.client!hm", name = "Z", descriptor = "I")
	private int anInt2639 = 0;

	@OriginalMember(owner = "runetek4.client!hm", name = "W", descriptor = "I")
	private int anInt2638 = 1;

	@OriginalMember(owner = "runetek4.client!hm", name = "jb", descriptor = "I")
	private int anInt2646 = 5;

	@OriginalMember(owner = "runetek4.client!hm", name = "X", descriptor = "[B")
	private byte[] permutation = new byte[512];

	@OriginalMember(owner = "runetek4.client!hm", name = "ib", descriptor = "I")
	private int anInt2645 = 5;

	@OriginalMember(owner = "runetek4.client!hm", name = "eb", descriptor = "[S")
	private short[] aShortArray35 = new short[512];

	@OriginalMember(owner = "runetek4.client!hm", name = "<init>", descriptor = "()V")
	public TextureOpVoronoiNoise() {
		super(0, true);
	}

	@OriginalMember(owner = "client!ha", name = "a", descriptor = "(II)[B")
	public static byte[] method1837(@OriginalArg(1) int arg0) {
		@Pc(10) ByteArrayNodeSecondary local10 = (ByteArrayNodeSecondary) aClass54_5.get((long) arg0);
		if (local10 == null) {
			@Pc(24) Random local24 = new Random((long) arg0);
			@Pc(27) byte[] local27 = new byte[512];
			@Pc(29) int local29;
			for (local29 = 0; local29 < 255; local29++) {
				local27[local29] = (byte) local29;
			}
			for (local29 = 0; local29 < 255; local29++) {
				@Pc(53) int local53 = 255 - local29;
				@Pc(58) int local58 = RandomUtils.nextInt(local53, local24);
				@Pc(62) byte local62 = local27[local58];
				local27[local58] = local27[local53];
				local27[local53] = local27[511 - local29] = local62;
			}
			local10 = new ByteArrayNodeSecondary(local27);
			aClass54_5.put(local10, (long) arg0);
		}
		return local10.balue;
	}

	@OriginalMember(owner = "runetek4.client!hm", name = "f", descriptor = "(B)V")
	private void method2052() {
		@Pc(12) Random random = new Random((long) this.anInt2639);
		this.aShortArray35 = new short[512];
		if (this.anInt2636 > 0) {
			for (@Pc(26) int local26 = 0; local26 < 512; local26++) {
				this.aShortArray35[local26] = (short) RandomUtils.nextInt(this.anInt2636, random);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!hm", name = "e", descriptor = "(I)V")
	@Override
	public final void postDecode() {
		this.permutation = method1837(this.anInt2639);
		this.method2052();
	}

	@OriginalMember(owner = "runetek4.client!hm", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int y) {
		@Pc(19) int[] dest = this.monochromeImageCache.get(y);
		if (this.monochromeImageCache.invalid) {
			@Pc(32) int local32 = this.anInt2646 * Texture.heightFractions[y] + 2048;
			@Pc(36) int local36 = local32 >> 12;
			@Pc(40) int local40 = local36 + 1;
			for (@Pc(42) int x = 0; x < Texture.width; x++) {
				anInt5526 = Integer.MAX_VALUE;
				anInt5443 = Integer.MAX_VALUE;
				anInt3041 = Integer.MAX_VALUE;
				anInt1091 = Integer.MAX_VALUE;
				@Pc(62) int local62 = this.anInt2645 * Texture.widthFractions[x] + 2048;
				@Pc(66) int local66 = local62 >> 12;
				@Pc(70) int local70 = local66 + 1;
				@Pc(165) int local165;
				for (@Pc(74) int local74 = local36 - 1; local74 <= local40; local74++) {
					@Pc(104) int local104 = this.permutation[(this.anInt2646 <= local74 ? local74 - this.anInt2646 : local74) & 0xFF] & 0xFF;
					for (@Pc(108) int local108 = local66 - 1; local108 <= local70; local108++) {
						@Pc(138) int local138 = (this.permutation[(this.anInt2645 <= local108 ? local108 - this.anInt2645 : local108) + local104 & 0xFF] & 0xFF) * 2;
						@Pc(142) int local142 = -(local108 << 12);
						@Pc(146) int local146 = local138 + 1;
						@Pc(151) int local151 = local142 + local62 - this.aShortArray35[local138];
						@Pc(162) int local162 = local32 - this.aShortArray35[local146] - (local74 << 12);
						local165 = this.anInt2638;
						@Pc(201) int local201;
						if (local165 == 1) {
							local201 = local162 * local162 + local151 * local151 >> 12;
						} else if (local165 == 3) {
							local151 = local151 < 0 ? -local151 : local151;
							local162 = local162 >= 0 ? local162 : -local162;
							local201 = local162 >= local151 ? local162 : local151;
						} else if (local165 == 4) {
							local151 = (int) (Math.sqrt((double) ((float) (local151 < 0 ? -local151 : local151) / 4096.0F)) * 4096.0D);
							local162 = (int) (Math.sqrt((double) ((float) (local162 >= 0 ? local162 : -local162) / 4096.0F)) * 4096.0D);
							local201 = local162 + local151;
							local201 = local201 * local201 >> 12;
						} else if (local165 == 5) {
							local151 *= local151;
							local162 *= local162;
							local201 = (int) (Math.sqrt(Math.sqrt((double) ((float) (local162 + local151) / 1.6777216E7F))) * 4096.0D);
						} else if (local165 == 2) {
							local201 = (local151 >= 0 ? local151 : -local151) + (local162 < 0 ? -local162 : local162);
						} else {
							local201 = (int) (Math.sqrt((double) ((float) (local162 * local162 + local151 * local151) / 1.6777216E7F)) * 4096.0D);
						}
						if (local201 < anInt1091) {
							anInt5526 = anInt5443;
							anInt5443 = anInt3041;
							anInt3041 = anInt1091;
							anInt1091 = local201;
						} else if (local201 < anInt3041) {
							anInt5526 = anInt5443;
							anInt5443 = anInt3041;
							anInt3041 = local201;
						} else if (anInt5443 > local201) {
							anInt5526 = anInt5443;
							anInt5443 = local201;
						} else if (local201 < anInt5526) {
							anInt5526 = local201;
						}
					}
				}
				local165 = this.anInt2635;
				if (local165 == 0) {
					dest[x] = anInt1091;
				} else if (local165 == 1) {
					dest[x] = anInt3041;
				} else if (local165 == 3) {
					dest[x] = anInt5443;
				} else if (local165 == 4) {
					dest[x] = anInt5526;
				} else if (local165 == 2) {
					dest[x] = anInt3041 - anInt1091;
				}
			}
		}
		return dest;
	}

	@OriginalMember(owner = "runetek4.client!hm", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt2645 = this.anInt2646 = packet.g1();
		} else if (code == 1) {
			this.anInt2639 = packet.g1();
		} else if (code == 2) {
			this.anInt2636 = packet.g2();
		} else if (code == 3) {
			this.anInt2635 = packet.g1();
		} else if (code == 4) {
			this.anInt2638 = packet.g1();
		} else if (code == 5) {
			this.anInt2645 = packet.g1();
		} else if (code == 6) {
			this.anInt2646 = packet.g1();
		}
	}
}
