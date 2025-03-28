package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static121 {

	@OriginalMember(owner = "runetek4.client!jg", name = "b", descriptor = "I")
	public static int anInt3039;

    @OriginalMember(owner = "runetek4.client!jg", name = "g", descriptor = "I")
	public static int anInt3041;

	@OriginalMember(owner = "runetek4.client!jg", name = "a", descriptor = "(I)[Lclient!ek;")
	public static SoftwareIndexedSprite[] method2406() {
		@Pc(2) SoftwareIndexedSprite[] local2 = new SoftwareIndexedSprite[Static165.anInt4038];
		for (@Pc(8) int local8 = 0; local8 < Static165.anInt4038; local8++) {
			local2[local8] = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[local8], SpriteLoader.yOffsets[local8], SpriteLoader.innerWidths[local8], SpriteLoader.innerHeights[local8], SpriteLoader.pixels[local8], Static259.anIntArray513);
		}
		SpriteLoader.clear();
		return local2;
	}

}
