package com.jagex.graphics.textureops;

import com.jagex.graphics.texture.Texture;
import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!pg")
public final class TextureOpMonochromeEdgeDetector extends TextureOp {

	@OriginalMember(owner = "runetek4.client!pg", name = "U", descriptor = "I")
	private int anInt4503 = 4096;

	@OriginalMember(owner = "runetek4.client!pg", name = "<init>", descriptor = "()V")
	public TextureOpMonochromeEdgeDetector() {
		super(1, true);
	}

	@OriginalMember(owner = "runetek4.client!pg", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(19) int[] local19 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			@Pc(34) int[] local34 = this.getChildMonochromeOutput(0, arg0 - 1 & Texture.heightMask);
			@Pc(40) int[] local40 = this.getChildMonochromeOutput(0, arg0);
			@Pc(50) int[] local50 = this.getChildMonochromeOutput(0, Texture.heightMask & arg0 + 1);
			for (@Pc(52) int local52 = 0; local52 < Texture.width; local52++) {
				@Pc(67) int local67 = (local50[local52] - local34[local52]) * this.anInt4503;
				@Pc(87) int local87 = this.anInt4503 * (local40[Texture.widthMask & local52 + 1] - local40[local52 - 1 & Texture.widthMask]);
				@Pc(91) int local91 = local87 >> 12;
				@Pc(95) int local95 = local67 >> 12;
				@Pc(101) int local101 = local91 * local91 >> 12;
				@Pc(107) int local107 = local95 * local95 >> 12;
				@Pc(121) int local121 = (int) (Math.sqrt((double) ((float) (local107 + local101 + 4096) / 4096.0F)) * 4096.0D);
				@Pc(130) int local130 = local121 == 0 ? 0 : 16777216 / local121;
				local19[local52] = 4096 - local130;
			}
		}
		return local19;
	}

	@OriginalMember(owner = "runetek4.client!pg", name = "a", descriptor = "(ILclient!wa;Z)V")
	@Override
	public final void decode(@OriginalArg(1) Packet packet, @OriginalArg(0) int code) {
		if (code == 0) {
			this.anInt4503 = packet.g2();
		}
	}
}
