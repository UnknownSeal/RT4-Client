package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!f")
public final class TextureOp8 extends TextureOp {

	@OriginalMember(owner = "runetek4.client!f", name = "R", descriptor = "I")
	private int anInt1935 = 204;

	@OriginalMember(owner = "runetek4.client!f", name = "Y", descriptor = "I")
	private int anInt1940 = 1;

	@OriginalMember(owner = "runetek4.client!f", name = "Q", descriptor = "I")
	private int anInt1934 = 1;

	@OriginalMember(owner = "runetek4.client!f", name = "<init>", descriptor = "()V")
	public TextureOp8() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!f", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt1934 = packet.g1();
		} else if (code == 1) {
			this.anInt1940 = packet.g1();
		} else if (code == 2) {
			this.anInt1935 = packet.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!f", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] method4626(@OriginalArg(0) int arg0) {
		@Pc(19) int[] local19 = this.aClass121_41.get(arg0);
		if (this.aClass121_41.invalid) {
			for (@Pc(25) int local25 = 0; local25 < Texture.width; local25++) {
				@Pc(32) int local32 = Texture.widthFractions[local25];
				@Pc(36) int local36 = Texture.heightFractions[arg0];
				@Pc(43) int local43 = this.anInt1934 * local32 >> 12;
				@Pc(50) int local50 = local36 * this.anInt1940 >> 12;
				@Pc(60) int local60 = this.anInt1934 * (local32 % (4096 / this.anInt1934));
				@Pc(70) int local70 = local36 % (4096 / this.anInt1940) * this.anInt1940;
				if (this.anInt1935 > local70) {
					for (local43 -= local50; local43 < 0; local43 += 4) {
					}
					while (local43 > 3) {
						local43 -= 4;
					}
					if (local43 != 1) {
						local19[local25] = 0;
						continue;
					}
					if (this.anInt1935 > local60) {
						local19[local25] = 0;
						continue;
					}
				}
				if (local60 < this.anInt1935) {
					for (local43 -= local50; local43 < 0; local43 += 4) {
					}
					while (local43 > 3) {
						local43 -= 4;
					}
					if (local43 > 0) {
						local19[local25] = 0;
						continue;
					}
				}
				local19[local25] = 4096;
			}
		}
		return local19;
	}
}
