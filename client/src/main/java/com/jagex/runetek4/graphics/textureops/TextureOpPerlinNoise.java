package com.jagex.runetek4.graphics.textureops;

import com.jagex.runetek4.graphics.cache.MonochromeImageCache;
import com.jagex.runetek4.graphics.texture.Texture;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!bi")
public final class TextureOpPerlinNoise extends TextureOp {

	@OriginalMember(owner = "runetek4.client!bi", name = "ib", descriptor = "[S")
	private short[] aShortArray3;

	@OriginalMember(owner = "runetek4.client!bi", name = "lb", descriptor = "[S")
	private short[] aShortArray4;

	@OriginalMember(owner = "runetek4.client!bi", name = "X", descriptor = "I")
	public int anInt641 = 4;

	@OriginalMember(owner = "runetek4.client!bi", name = "eb", descriptor = "I")
	public int anInt646 = 4;

	@OriginalMember(owner = "runetek4.client!bi", name = "kb", descriptor = "Z")
	public boolean aBoolean44 = true;

	@OriginalMember(owner = "runetek4.client!bi", name = "gb", descriptor = "I")
	public int anInt648 = 1638;

	@OriginalMember(owner = "runetek4.client!bi", name = "ab", descriptor = "[B")
	private byte[] aByteArray10 = new byte[512];

	@OriginalMember(owner = "runetek4.client!bi", name = "Z", descriptor = "I")
	public int anInt642 = 4;

	@OriginalMember(owner = "runetek4.client!bi", name = "mb", descriptor = "I")
	public int anInt650 = 0;

	@OriginalMember(owner = "runetek4.client!bi", name = "<init>", descriptor = "()V")
	public TextureOpPerlinNoise() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!bi", name = "a", descriptor = "(ZI[I)V")
	public final void method584(@OriginalArg(1) int arg0, @OriginalArg(2) int[] arg1) {
		@Pc(12) int local12 = this.anInt641 * Texture.heightFractions[arg0];
		@Pc(115) int local115;
		@Pc(129) int local129;
		@Pc(40) int local40;
		@Pc(27) short local27;
		@Pc(105) int local105;
		@Pc(60) int local60;
		@Pc(54) int local54;
		@Pc(47) int local47;
		@Pc(85) int local85;
		@Pc(64) int local64;
		@Pc(68) int local68;
		@Pc(77) int local77;
		@Pc(103) int local103;
		if (this.anInt642 == 1) {
			local27 = this.aShortArray4[0];
			local40 = this.aShortArray3[0] << 12;
			local60 = local12 * local40 >> 12;
			local54 = this.anInt646 * local40 >> 12;
			local47 = local40 * this.anInt641 >> 12;
			local64 = local60 >> 12;
			local77 = this.aByteArray10[local64 & 0xFF] & 0xFF;
			local60 &= 0xFFF;
			local85 = MonochromeImageCache.anIntArray1[local60];
			local68 = local64 + 1;
			if (local47 <= local68) {
				local68 = 0;
			}
			local103 = this.aByteArray10[local68 & 0xFF] & 0xFF;
			if (this.aBoolean44) {
				for (local105 = 0; local105 < Texture.width; local105++) {
					local115 = this.anInt646 * Texture.widthFractions[local105];
					local129 = this.method590(local40 * local115 >> 12, local103, local77, local54, local60, local85);
					local129 = local27 * local129 >> 12;
					arg1[local105] = (local129 >> 1) + 2048;
				}
			} else {
				for (local105 = 0; local105 < Texture.width; local105++) {
					local115 = this.anInt646 * Texture.widthFractions[local105];
					local129 = this.method590(local40 * local115 >> 12, local103, local77, local54, local60, local85);
					arg1[local105] = local27 * local129 >> 12;
				}
			}
			return;
		}
		local27 = this.aShortArray4[0];
		if (local27 > 8 || local27 < -8) {
			local40 = this.aShortArray3[0] << 12;
			local47 = local40 * this.anInt641 >> 12;
			local54 = this.anInt646 * local40 >> 12;
			local60 = local12 * local40 >> 12;
			local64 = local60 >> 12;
			local68 = local64 + 1;
			local77 = this.aByteArray10[local64 & 0xFF] & 0xFF;
			@Pc(81) int local81 = local60 & 0xFFF;
			local85 = MonochromeImageCache.anIntArray1[local81];
			if (local68 >= local47) {
				local68 = 0;
			}
			local103 = this.aByteArray10[local68 & 0xFF] & 0xFF;
			for (local105 = 0; local105 < Texture.width; local105++) {
				local115 = Texture.widthFractions[local105] * this.anInt646;
				local129 = this.method590(local115 * local40 >> 12, local103, local77, local54, local81, local85);
				arg1[local105] = local129 * local27 >> 12;
			}
		}
		for (@Pc(142) int local142 = 1; local142 < this.anInt642; local142++) {
			local27 = this.aShortArray4[local142];
			if (local27 > 8 || local27 < -8) {
				local40 = this.aShortArray3[local142] << 12;
				local60 = local40 * local12 >> 12;
				local64 = local60 >> 12;
				local77 = this.aByteArray10[local64 & 0xFF] & 0xFF;
				local54 = this.anInt646 * local40 >> 12;
				local68 = local64 + 1;
				local60 &= 0xFFF;
				local85 = MonochromeImageCache.anIntArray1[local60];
				local47 = this.anInt641 * local40 >> 12;
				if (local68 >= local47) {
					local68 = 0;
				}
				local103 = this.aByteArray10[local68 & 0xFF] & 0xFF;
				if (this.aBoolean44 && local142 == this.anInt642 - 1) {
					for (local105 = 0; local105 < Texture.width; local105++) {
						local115 = Texture.widthFractions[local105] * this.anInt646;
						local129 = this.method590(local40 * local115 >> 12, local103, local77, local54, local60, local85);
						local129 = (local27 * local129 >> 12) + arg1[local105];
						arg1[local105] = (local129 >> 1) + 2048;
					}
				} else {
					for (local105 = 0; local105 < Texture.width; local105++) {
						local115 = Texture.widthFractions[local105] * this.anInt646;
						local129 = this.method590(local115 * local40 >> 12, local103, local77, local54, local60, local85);
						arg1[local105] += local129 * local27 >> 12;
					}
				}
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!bi", name = "e", descriptor = "(I)V")
	@Override
	public final void postDecode() {
		this.aByteArray10 = TextureOpVoronoiNoise.method1837(this.anInt650);
		this.method589();
		for (@Pc(15) int local15 = this.anInt642 - 1; local15 >= 1; local15--) {
			@Pc(23) short local23 = this.aShortArray4[local15];
			if (local23 > 8 || local23 < -8) {
				break;
			}
			this.anInt642--;
		}
	}

	@OriginalMember(owner = "runetek4.client!bi", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.aBoolean44 = packet.g1() == 1;
		} else if (code == 1) {
			this.anInt642 = packet.g1();
		} else if (code == 2) {
			this.anInt648 = packet.g2s();
			if (this.anInt648 < 0) {
				this.aShortArray4 = new short[this.anInt642];
				for (@Pc(93) int local93 = 0; local93 < this.anInt642; local93++) {
					this.aShortArray4[local93] = (short) packet.g2s();
				}
			}
		} else if (code == 3) {
			this.anInt646 = this.anInt641 = packet.g1();
		} else if (code == 4) {
			this.anInt650 = packet.g1();
		} else if (code == 5) {
			this.anInt646 = packet.g1();
		} else if (code == 6) {
			this.anInt641 = packet.g1();
		}
	}

	@OriginalMember(owner = "runetek4.client!bi", name = "b", descriptor = "(Z)V")
	private void method589() {
		@Pc(21) int local21;
		if (this.anInt648 > 0) {
			this.aShortArray4 = new short[this.anInt642];
			this.aShortArray3 = new short[this.anInt642];
			for (local21 = 0; local21 < this.anInt642; local21++) {
				this.aShortArray4[local21] = (short) (Math.pow((double) ((float) this.anInt648 / 4096.0F), (double) local21) * 4096.0D);
				this.aShortArray3[local21] = (short) Math.pow(2.0D, (double) local21);
			}
		} else if (this.aShortArray4 != null && this.aShortArray4.length == this.anInt642) {
			this.aShortArray3 = new short[this.anInt642];
			for (local21 = 0; local21 < this.anInt642; local21++) {
				this.aShortArray3[local21] = (short) Math.pow(2.0D, (double) local21);
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!bi", name = "a", descriptor = "(IIIIIII)I")
	private int method590(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		@Pc(15) int local15 = arg4 - 4096;
		@Pc(19) int local19 = arg0 >> 12;
		@Pc(23) int local23 = local19 + 1;
		@Pc(27) int local27 = local19 & 0xFF;
		if (local23 >= arg3) {
			local23 = 0;
		}
		@Pc(40) int local40 = arg0 & 0xFFF;
		@Pc(50) int local50 = this.aByteArray10[local27 + arg2] & 0x3;
		@Pc(54) int local54 = MonochromeImageCache.anIntArray1[local40];
		@Pc(70) int local70;
		if (local50 > 1) {
			local70 = local50 == 2 ? local40 - arg4 : -local40 + -arg4;
		} else {
			local70 = local50 == 0 ? arg4 + local40 : -local40 + arg4;
		}
		local23 &= 0xFF;
		@Pc(92) int local92 = local40 - 4096;
		local50 = this.aByteArray10[arg2 + local23] & 0x3;
		@Pc(118) int local118;
		if (local50 <= 1) {
			local118 = local50 == 0 ? arg4 + local92 : -local92 + arg4;
		} else {
			local118 = local50 == 2 ? local92 - arg4 : -local92 + -arg4;
		}
		local50 = this.aByteArray10[local27 + arg1] & 0x3;
		@Pc(155) int local155 = local70 + ((local118 - local70) * local54 >> 12);
		if (local50 > 1) {
			local70 = local50 == 2 ? local40 - local15 : -local40 - local15;
		} else {
			local70 = local50 == 0 ? local40 + local15 : local15 + -local40;
		}
		local50 = this.aByteArray10[arg1 + local23] & 0x3;
		if (local50 > 1) {
			local118 = local50 == 2 ? local92 - local15 : -local15 + -local92;
		} else {
			local118 = local50 == 0 ? local92 + local15 : local15 + -local92;
		}
		@Pc(237) int local237 = local70 + ((local118 - local70) * local54 >> 12);
		return local155 + (arg5 * (local237 - local155) >> 12);
	}

	@OriginalMember(owner = "runetek4.client!bi", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(17) int[] local17 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			this.method584(arg0, local17);
		}
		return local17;
	}
}
