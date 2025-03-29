package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!ca")
public final class TextureOpBinary extends TextureOp {

	@OriginalMember(owner = "runetek4.client!ca", name = "T", descriptor = "I")
	private int maxValue = 4096;

	@OriginalMember(owner = "runetek4.client!ca", name = "P", descriptor = "I")
	private int minValue = 0;

	@OriginalMember(owner = "runetek4.client!ca", name = "<init>", descriptor = "()V")
	public TextureOpBinary() {
		super(1, true);
	}

	@OriginalMember(owner = "runetek4.client!ca", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(19) int[] local19 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			@Pc(29) int[] local29 = this.getChildMonochromeOutput(0, arg0);
			for (@Pc(31) int local31 = 0; local31 < Texture.width; local31++) {
				@Pc(38) int local38 = local29[local31];
				local19[local31] = this.minValue <= local38 && local38 <= this.maxValue ? 4096 : 0;
			}
		}
		return local19;
	}

	@OriginalMember(owner = "runetek4.client!ca", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.minValue = packet.g2();
		} else if (code == 1) {
			this.maxValue = packet.g2();
		}
	}
}
