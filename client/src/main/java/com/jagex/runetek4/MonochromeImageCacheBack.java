package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!a")
public final class MonochromeImageCacheBack {

	static {
		for (@Pc(4) int local4 = 0; local4 < 4096; local4++) {
			MonochromeImageCache.anIntArray1[local4] = MonochromeImageCache.fade(local4);
		}
	}
}
