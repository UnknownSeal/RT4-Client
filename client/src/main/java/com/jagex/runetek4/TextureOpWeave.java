package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!sa")
public final class TextureOpWeave extends TextureOp {

	@OriginalMember(owner = "runetek4.client!sa", name = "T", descriptor = "I")
	private int thickness = 585;

	@OriginalMember(owner = "runetek4.client!sa", name = "<init>", descriptor = "()V")
	public TextureOpWeave() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!sa", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int y) {
		@Pc(19) int[] dest = this.monochromeImageCache.get(y);
		if (this.monochromeImageCache.invalid) {
			@Pc(28) int local28 = Texture.heightFractions[y];
			for (@Pc(30) int x = 0; x < Texture.width; x++) {
				@Pc(41) int local41 = Texture.widthFractions[x];
				@Pc(76) int local76;
				if (local41 > this.thickness && 4096 - this.thickness > local41 && 2048 - this.thickness < local28 && local28 < this.thickness + 2048) {
					local76 = 2048 - local41;
					local76 = local76 < 0 ? -local76 : local76;
					local76 <<= 0xC;
					local76 /= 2048 - this.thickness;
					dest[x] = 4096 - local76;
				} else if (local41 > 2048 - this.thickness && local41 < this.thickness + 2048) {
					local76 = local28 - 2048;
					local76 = local76 >= 0 ? local76 : -local76;
					local76 -= this.thickness;
					local76 <<= 0xC;
					dest[x] = local76 / (2048 - this.thickness);
				} else if (local28 < this.thickness || 4096 - this.thickness < local28) {
					local76 = local41 - 2048;
					@Pc(188) int local188 = local76 < 0 ? -local76 : local76;
					@Pc(193) int local193 = local188 - this.thickness;
					@Pc(197) int local197 = local193 << 12;
					dest[x] = local197 / (2048 - this.thickness);
				} else if (this.thickness <= local41 && local41 <= 4096 - this.thickness) {
					dest[x] = 0;
				} else {
					local76 = 2048 - local28;
					local76 = local76 < 0 ? -local76 : local76;
					local76 <<= 0xC;
					local76 /= 2048 - this.thickness;
					dest[x] = 4096 - local76;
				}
			}
		}
		return dest;
	}

	@OriginalMember(owner = "runetek4.client!sa", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.thickness = packet.g2();
		}
	}
}
