package com.jagex.runetek4.graphics.textureops;

import com.jagex.runetek4.graphics.texture.Texture;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!gm")
public final class TextureOpMandelbrot extends TextureOp {

	@OriginalMember(owner = "runetek4.client!gm", name = "Z", descriptor = "I")
	private int anInt2298 = 1365;

	@OriginalMember(owner = "runetek4.client!gm", name = "ab", descriptor = "I")
	private int anInt2299 = 0;

	@OriginalMember(owner = "runetek4.client!gm", name = "V", descriptor = "I")
	private int anInt2296 = 0;

	@OriginalMember(owner = "runetek4.client!gm", name = "Y", descriptor = "I")
	private int anInt2297 = 20;

	@OriginalMember(owner = "runetek4.client!gm", name = "<init>", descriptor = "()V")
	public TextureOpMandelbrot() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!gm", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt2298 = packet.g2();
		} else if (code == 1) {
			this.anInt2297 = packet.g2();
		} else if (code == 2) {
			this.anInt2296 = packet.g2();
		} else if (code == 3) {
			this.anInt2299 = packet.g2();
		}
	}

	@OriginalMember(owner = "runetek4.client!gm", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(19) int[] local19 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			for (@Pc(26) int local26 = 0; local26 < Texture.width; local26++) {
				@Pc(45) int local45 = this.anInt2299 + (Texture.heightFractions[arg0] << 12) / this.anInt2298;
				@Pc(57) int local57 = this.anInt2296 + (Texture.widthFractions[local26] << 12) / this.anInt2298;
				@Pc(61) int local61 = local57;
				@Pc(65) int local65 = local45;
				@Pc(67) int local67 = 0;
				@Pc(73) int local73 = local57 * local57 >> 12;
				@Pc(79) int local79 = local45 * local45 >> 12;
				while (local73 + local79 < 16384 && this.anInt2297 > local67) {
					local65 = (local61 * local65 >> 12) * 2 + local45;
					local67++;
					local61 = local73 + local57 - local79;
					local79 = local65 * local65 >> 12;
					local73 = local61 * local61 >> 12;
				}
				local19[local26] = local67 >= this.anInt2297 - 1 ? 0 : (local67 << 12) / this.anInt2297;
			}
		}
		return local19;
	}
}
