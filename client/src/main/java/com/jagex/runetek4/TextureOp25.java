package com.jagex.runetek4;

import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!qg")
public final class TextureOp25 extends TextureOp {

	@OriginalMember(owner = "runetek4.client!qg", name = "<init>", descriptor = "()V")
	public TextureOp25() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!qg", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] method4626(@OriginalArg(0) int arg0) {
		@Pc(9) int[] local9 = this.aClass121_41.get(arg0);
		if (this.aClass121_41.invalid) {
			ArrayUtils.fill(local9, 0, Texture.width, Texture.heightFractions[arg0]);
		}
		return local9;
	}
}
