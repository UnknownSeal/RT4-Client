package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static114 {

	@OriginalMember(owner = "runetek4.client!j", name = "a", descriptor = "([BI)Lclient!dd;")
	public static SoftwareFont method4635(@OriginalArg(0) byte[] arg0) {
		if (arg0 == null) {
			return null;
		} else {
			@Pc(22) SoftwareFont local22 = new SoftwareFont(arg0, SpriteLoader.xOffsets, SpriteLoader.yOffsets, SpriteLoader.innerWidths, SpriteLoader.innerHeights, SpriteLoader.pixels);
			SpriteLoader.clear();
			return local22;
		}
	}

}
