package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.textureops.TextureOpPerlinNoise;
import com.jagex.runetek4.util.MathUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!gi")
public final class Light {

	@OriginalMember(owner = "client!f", name = "P", descriptor = "[I")
	public static int[] NOISE;
	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "Z")
	public final boolean aBoolean124;

	@OriginalMember(owner = "runetek4.client!gi", name = "d", descriptor = "I")
	private int alphaMax;

	@OriginalMember(owner = "runetek4.client!gi", name = "e", descriptor = "I")
	private int anInt2233;

	@OriginalMember(owner = "runetek4.client!gi", name = "i", descriptor = "I")
	public int y;

	@OriginalMember(owner = "runetek4.client!gi", name = "j", descriptor = "I")
	public final int radius;

	@OriginalMember(owner = "runetek4.client!gi", name = "p", descriptor = "I")
	private int alphaMin;

	@OriginalMember(owner = "runetek4.client!gi", name = "q", descriptor = "I")
	public int x;

	@OriginalMember(owner = "runetek4.client!gi", name = "r", descriptor = "I")
	public int level;

	@OriginalMember(owner = "runetek4.client!gi", name = "t", descriptor = "I")
	public final int anInt2243;

	@OriginalMember(owner = "runetek4.client!gi", name = "u", descriptor = "[S")
	public final short[] aShortArray30;

	@OriginalMember(owner = "runetek4.client!gi", name = "w", descriptor = "F")
	public float alpha;

	@OriginalMember(owner = "runetek4.client!gi", name = "x", descriptor = "I")
	public int z;

	@OriginalMember(owner = "runetek4.client!gi", name = "y", descriptor = "I")
	private int anInt2246;

	@OriginalMember(owner = "runetek4.client!gi", name = "z", descriptor = "F")
	public float aFloat9;

	@OriginalMember(owner = "runetek4.client!gi", name = "A", descriptor = "Z")
	public final boolean aBoolean126;

	@OriginalMember(owner = "runetek4.client!gi", name = "B", descriptor = "Lclient!fj;")
	public Class45 aClass45_1;

	@OriginalMember(owner = "runetek4.client!gi", name = "C", descriptor = "I")
	public final int color;

	@OriginalMember(owner = "runetek4.client!gi", name = "E", descriptor = "I")
	private final int anInt2249;

	@OriginalMember(owner = "runetek4.client!gi", name = "g", descriptor = "Z")
	public boolean aBoolean125 = false;

	@OriginalMember(owner = "runetek4.client!gi", name = "I", descriptor = "[F")
	public final float[] diffuse = new float[4];

	@OriginalMember(owner = "runetek4.client!gi", name = "<init>", descriptor = "(Lclient!wa;)V")
	public Light(@OriginalArg(0) Packet packet) {
		if (NOISE == null) {
			init();
		}
		this.level = packet.g1();
		this.aBoolean124 = (this.level & 0x10) != 0;
		this.aBoolean126 = (this.level & 0x8) != 0;
		this.level &= 0x7;
		this.x = packet.g2();
		this.z = packet.g2();
		this.y = packet.g2();
		this.radius = packet.g1();
		this.method1763();
		this.aShortArray30 = new short[this.radius * 2 + 1];
		@Pc(87) int local87;
		for (local87 = 0; local87 < this.aShortArray30.length; local87++) {
			this.aShortArray30[local87] = (short) packet.g2();
		}
		this.color = Rasterizer.palette[packet.g2()];
		local87 = packet.g1();
		this.anInt2249 = (local87 & 0xE0) << 3;
		this.anInt2243 = local87 & 0x1F;
		if (this.anInt2243 != 31) {
			this.method1766();
		}
	}

	@OriginalMember(owner = "runetek4.client!qk", name = "a", descriptor = "(ZIIIIFII)[I")
	public static int[] createNoise(@OriginalArg(5) float arg0) {
		@Pc(11) int[] local11 = new int[2048];
		@Pc(15) TextureOpPerlinNoise local15 = new TextureOpPerlinNoise();
		local15.anInt646 = 8;
		local15.anInt642 = 4;
		local15.anInt650 = 35;
		local15.anInt641 = 8;
		local15.anInt648 = (int) (arg0 * 4096.0F);
		local15.aBoolean44 = true;
		local15.postDecode();
		Texture.setSize(1, 2048);
		local15.method584(0, local11);
		return local11;
	}

	@OriginalMember(owner = "client!gk", name = "b", descriptor = "(B)V")
	public static void init() {
		NOISE = createNoise(0.4F);
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "(BIIII)V")
	public final void method1762(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3) {
		this.anInt2233 = arg0;
		this.alphaMin = arg2;
		this.alphaMax = arg3;
		this.anInt2246 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "(I)V")
	private void method1763() {
		@Pc(10) int local10 = (this.radius << 7) + 64;
		this.aFloat9 = 1.0F / (float) (local10 * local10);
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "a", descriptor = "(ZII)V")
	public final void method1765(@OriginalArg(0) boolean arg0, @OriginalArg(1) int arg1) {
		@Pc(26) int local26 = this.anInt2249 + arg1 * this.anInt2246 / 50 & 0x7FF;
		@Pc(29) int local29 = this.anInt2233;
		@Pc(62) int local62;
		if (local29 == 1) {
			local62 = (MathUtils.sin[local26] >> 6) + 1024;
		} else if (local29 == 3) {
			local62 = NOISE[local26] >> 1;
		} else if (local29 == 4) {
			local62 = local26 >> 10 << 11;
		} else if (local29 == 2) {
			local62 = local26;
		} else if (local29 == 5) {
			local62 = (local26 < 1024 ? local26 : 2048 - local26) << 1;
		} else {
			local62 = 2048;
		}
		if (arg0) {
			local62 = 2048;
		}
		this.alpha = (float) (this.alphaMax + (local62 * this.alphaMin >> 11)) / 2048.0F;
		@Pc(123) float local123 = this.alpha / 255.0F;
		this.diffuse[0] = (float) (this.color >> 16 & 0xFF) * local123;
		this.diffuse[2] = local123 * (float) (this.color & 0xFF);
		this.diffuse[1] = (float) (this.color >> 8 & 0xFF) * local123;
	}

	@OriginalMember(owner = "runetek4.client!gi", name = "c", descriptor = "(I)V")
	private void method1766() {
		@Pc(4) int local4 = this.anInt2243;
		if (local4 == 2) {
			this.alphaMin = 2048;
			this.alphaMax = 0;
			this.anInt2233 = 1;
			this.anInt2246 = 2048;
		} else if (local4 == 3) {
			this.alphaMax = 0;
			this.anInt2246 = 4096;
			this.anInt2233 = 1;
			this.alphaMin = 2048;
		} else if (local4 == 4) {
			this.alphaMax = 0;
			this.alphaMin = 2048;
			this.anInt2233 = 4;
			this.anInt2246 = 2048;
		} else if (local4 == 5) {
			this.anInt2233 = 4;
			this.alphaMin = 2048;
			this.anInt2246 = 8192;
			this.alphaMax = 0;
		} else if (local4 == 12) {
			this.alphaMin = 2048;
			this.anInt2233 = 2;
			this.anInt2246 = 2048;
			this.alphaMax = 0;
		} else if (local4 == 13) {
			this.anInt2246 = 8192;
			this.alphaMin = 2048;
			this.anInt2233 = 2;
			this.alphaMax = 0;
		} else if (local4 == 10) {
			this.alphaMin = 512;
			this.anInt2233 = 3;
			this.alphaMax = 1536;
			this.anInt2246 = 2048;
		} else if (local4 == 11) {
			this.anInt2233 = 3;
			this.anInt2246 = 4096;
			this.alphaMin = 512;
			this.alphaMax = 1536;
		} else if (local4 == 6) {
			this.alphaMin = 768;
			this.alphaMax = 1280;
			this.anInt2233 = 3;
			this.anInt2246 = 2048;
		} else if (local4 == 7) {
			this.alphaMin = 768;
			this.alphaMax = 1280;
			this.anInt2246 = 4096;
			this.anInt2233 = 3;
		} else if (local4 == 8) {
			this.anInt2246 = 2048;
			this.anInt2233 = 3;
			this.alphaMin = 1024;
			this.alphaMax = 1024;
		} else if (local4 == 9) {
			this.anInt2246 = 4096;
			this.alphaMax = 1024;
			this.alphaMin = 1024;
			this.anInt2233 = 3;
		} else if (local4 == 14) {
			this.anInt2246 = 2048;
			this.alphaMax = 1280;
			this.anInt2233 = 1;
			this.alphaMin = 768;
		} else if (local4 == 15) {
			this.alphaMin = 512;
			this.anInt2246 = 4096;
			this.alphaMax = 1536;
			this.anInt2233 = 1;
		} else if (local4 == 16) {
			this.anInt2246 = 8192;
			this.alphaMax = 1792;
			this.anInt2233 = 1;
			this.alphaMin = 256;
		} else {
			this.anInt2246 = 2048;
			this.alphaMax = 0;
			this.alphaMin = 2048;
			this.anInt2233 = 0;
		}
	}
}
