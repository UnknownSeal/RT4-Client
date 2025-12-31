package com.jagex.graphics.textureops;

import com.jagex.graphics.texture.Texture;
import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ag")
public final class TextureOpWaveform extends TextureOp {

	@OriginalMember(owner = "runetek4.client!ag", name = "S", descriptor = "I")
	private int waveform = 0;

	@OriginalMember(owner = "runetek4.client!ag", name = "X", descriptor = "I")
	private int shape = 0;

	@OriginalMember(owner = "runetek4.client!ag", name = "W", descriptor = "I")
	private int frequency = 1;

	@OriginalMember(owner = "runetek4.client!ag", name = "<init>", descriptor = "()V")
	public TextureOpWaveform() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!ag", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int y) {
		@Pc(11) int[] dest = this.monochromeImageCache.get(y);
		if (this.monochromeImageCache.invalid) {
			@Pc(20) int local20 = Texture.heightFractions[y];
			@Pc(26) int local26 = local20 - 2048 >> 1;
			for (@Pc(28) int x = 0; x < Texture.width; x++) {
				@Pc(35) int local35 = Texture.widthFractions[x];
				@Pc(41) int local41 = local35 - 2048 >> 1;
				@Pc(68) int local68;
				if (this.shape == 0) {
					local68 = (local35 - local20) * this.frequency;
				} else {
					@Pc(58) int local58 = local41 * local41 + local26 * local26 >> 12;
					local68 = (int) (Math.sqrt((double) ((float) local58 / 4096.0F)) * 4096.0D);
					local68 = (int) ((double) (local68 * this.frequency) * 3.141592653589793D);
				}
				local68 -= local68 & 0xFFFFF000;
				if (this.waveform == 0) {
					local68 = TextureOp.SINE[local68 >> 4 & 0xFF] + 4096 >> 1;
				} else if (this.waveform == 2) {
					local68 -= 2048;
					if (local68 < 0) {
						local68 = -local68;
					}
					local68 = 2048 - local68 << 1;
				}
				dest[x] = local68;
			}
		}
		return dest;
	}

	@OriginalMember(owner = "runetek4.client!ag", name = "e", descriptor = "(I)V")
	@Override
	public final void postDecode() {
		TextureOp.createTrigonometryTables();
	}

	@OriginalMember(owner = "runetek4.client!ag", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.shape = packet.g1();
		} else if (code == 1) {
			this.waveform = packet.g1();
		} else if (code == 3) {
			this.frequency = packet.g1();
		}
	}
}
