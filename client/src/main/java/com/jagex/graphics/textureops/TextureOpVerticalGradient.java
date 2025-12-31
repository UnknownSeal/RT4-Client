package com.jagex.graphics.textureops;

import com.jagex.graphics.texture.Texture;
import com.jagex.core.utils.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!qg")
public final class TextureOpVerticalGradient extends TextureOp {

	@OriginalMember(owner = "runetek4.client!qg", name = "<init>", descriptor = "()V")
	public TextureOpVerticalGradient() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		@Pc(9) int[] local9 = this.monochromeImageCache.get(arg0);
		if (this.monochromeImageCache.invalid) {
			ArrayUtils.fill(local9, 0, Texture.width, Texture.heightFractions[arg0]);
		}
		return local9;
	}
}
