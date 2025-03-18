package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static253 {

	@OriginalMember(owner = "runetek4.client!ui", name = "Q", descriptor = "I")
	public static int anInt5526;

	@OriginalMember(owner = "runetek4.client!ui", name = "R", descriptor = "I")
	public static int anInt5527;

	@OriginalMember(owner = "runetek4.client!ui", name = "eb", descriptor = "[[[B")
	public static byte[][][] levelTileUnderlayIds;

	@OriginalMember(owner = "runetek4.client!ui", name = "c", descriptor = "(II)I")
	public static int method4328(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "h", descriptor = "(I)[Lclient!ok;")
	public static IndexedSprite[] method4331() {
		@Pc(8) IndexedSprite[] local8 = new IndexedSprite[Static165.anInt4038];
		for (@Pc(10) int local10 = 0; local10 < Static165.anInt4038; local10++) {
			if (GlRenderer.enabled) {
				local8[local10] = new GlIndexedSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[local10], SpriteLoader.yOffsets[local10], SpriteLoader.innerWidths[local10], SpriteLoader.innerHeights[local10], SpriteLoader.pixels[local10], Static259.anIntArray513);
			} else {
				local8[local10] = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, SpriteLoader.xOffsets[local10], SpriteLoader.yOffsets[local10], SpriteLoader.innerWidths[local10], SpriteLoader.innerHeights[local10], SpriteLoader.pixels[local10], Static259.anIntArray513);
			}
		}
		SpriteLoader.clear();
		return local8;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "d", descriptor = "(II)V")
	public static void method4332(@OriginalArg(0) int arg0) {
		if (arg0 >= 0 && Static258.aBooleanArray130.length > arg0) {
			Static258.aBooleanArray130[arg0] = !Static258.aBooleanArray130[arg0];
		}
	}
}
