package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static276 {

	@OriginalMember(owner = "runetek4.client!wh", name = "b", descriptor = "(B)Lclient!ok;")
	public static IndexedSprite method4614() {
		@Pc(27) IndexedSprite local27;
		if (GlRenderer.enabled) {
			local27 = new GlIndexedSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[0], SpriteLoader.yOffsets[0], SpriteLoader.innerWidths[0], SpriteLoader.innerHeights[0], SpriteLoader.pixels[0], Static259.anIntArray513);
		} else {
			local27 = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[0], SpriteLoader.yOffsets[0], SpriteLoader.innerWidths[0], SpriteLoader.innerHeights[0], SpriteLoader.pixels[0], Static259.anIntArray513);
		}
		SpriteLoader.clear();
		return local27;
	}

}
