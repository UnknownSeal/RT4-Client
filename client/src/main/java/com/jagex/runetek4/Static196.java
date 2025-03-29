package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static196 {

	@OriginalMember(owner = "runetek4.client!pl", name = "a", descriptor = "(I)Lclient!mm;")
	public static SoftwareSprite method3537() {
		@Pc(13) int local13 = SpriteLoader.innerWidths[0] * SpriteLoader.innerHeights[0];
		@Pc(17) byte[] local17 = SpriteLoader.pixels[0];
		@Pc(20) int[] local20 = new int[local13];
		for (@Pc(22) int local22 = 0; local22 < local13; local22++) {
			local20[local22] = Static259.anIntArray513[local17[local22] & 0xFF];
		}
		@Pc(57) SoftwareSprite local57 = new SoftwareSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[0], SpriteLoader.yOffsets[0], SpriteLoader.innerWidths[0], SpriteLoader.innerHeights[0], local20);
		SpriteLoader.clear();
		return local57;
	}
}
