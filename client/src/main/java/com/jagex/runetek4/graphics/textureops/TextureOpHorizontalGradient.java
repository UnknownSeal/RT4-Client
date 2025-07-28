package com.jagex.runetek4.graphics.textureops;

import com.jagex.runetek4.graphics.texture.Texture;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;

@OriginalClass("runetek4.client!rl")
public final class TextureOpHorizontalGradient extends TextureOp {

	@OriginalMember(owner = "runetek4.client!rl", name = "<init>", descriptor = "()V")
	public TextureOpHorizontalGradient() {
		super(0, true);
	}

	@OriginalMember(owner = "runetek4.client!rl", name = "a", descriptor = "(IB)[I")
	@Override
	public final int[] getMonochromeOutput(@OriginalArg(0) int arg0) {
		return Texture.widthFractions;
	}
}
