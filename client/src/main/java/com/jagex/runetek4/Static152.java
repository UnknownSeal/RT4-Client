package com.jagex.runetek4;

import com.jagex.runetek4.frame.MiniMap;
import com.jagex.runetek4.scene.tile.ComplexTile;
import com.jagex.runetek4.scene.tile.GenericTile;
import com.jagex.runetek4.scene.tile.Tile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static152 {

	@OriginalMember(owner = "runetek4.client!ma", name = "i", descriptor = "I")
	public static int anInt3594;

	@OriginalMember(owner = "runetek4.client!ma", name = "q", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_3;

	@OriginalMember(owner = "runetek4.client!ma", name = "z", descriptor = "I")
	public static int anInt3604 = -1;

	@OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "(IIBIIII)V")
	public static void method2826(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4, @OriginalArg(6) int arg5) {
		if (arg4 - arg3 >= Static172.anInt4164 && FloorUnderlayTypeList.anInt5063 >= arg4 + arg3 && Static267.anInt5773 <= arg1 - arg3 && Static106.anInt2869 >= arg3 + arg1) {
			Static8.method120(arg5, arg0, arg1, arg2, arg3, arg4);
		} else {
			Static280.method4670(arg2, arg1, arg3, arg5, arg0, arg4);
		}
	}

	@OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "([IIIIII)V")
	public static void drawMinimapTile(@OriginalArg(0) int[] destPixels, @OriginalArg(1) int offset, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(7) Tile tile = Static130.levelTiles[arg2][arg3][arg4];
		if (tile == null) {
			return;
		}
		@Pc(13) GenericTile genericTile = tile.plainTile;
		@Pc(23) int local23;
		if (genericTile != null) {
			@Pc(18) int color = genericTile.rgbColor;
			if (color != 0) {
				for (local23 = 0; local23 < 4; local23++) {
					destPixels[offset] = color;
					destPixels[offset + 1] = color;
					destPixels[offset + 2] = color;
					destPixels[offset + 3] = color;
					offset += 512;
				}
			}
			return;
		}
		@Pc(58) ComplexTile local58 = tile.shapedTile;
		if (local58 == null) {
			return;
		}
		local23 = local58.anInt1966;
		@Pc(67) int rotation = local58.anInt1967;
		@Pc(70) int underlayColor = local58.underlayRGB;
		@Pc(73) int overlayColor = local58.overlayRGB;
		@Pc(77) int[] local77 = MiniMap.anIntArrayArray24[local23];
		@Pc(81) int[] local81 = MiniMap.anIntArrayArray46[rotation];
		@Pc(83) int pointer = 0;
		@Pc(87) int i;
		if (underlayColor != 0) {
			for (i = 0; i < 4; i++) {
				destPixels[offset] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
				destPixels[offset + 1] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
				destPixels[offset + 2] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
				destPixels[offset + 3] = local77[local81[pointer++]] == 0 ? underlayColor : overlayColor;
				offset += 512;
			}
			return;
		}
		for (i = 0; i < 4; i++) {
			if (local77[local81[pointer++]] != 0) {
				destPixels[offset] = overlayColor;
			}
			if (local77[local81[pointer++]] != 0) {
				destPixels[offset + 1] = overlayColor;
			}
			if (local77[local81[pointer++]] != 0) {
				destPixels[offset + 2] = overlayColor;
			}
			if (local77[local81[pointer++]] != 0) {
				destPixels[offset + 3] = overlayColor;
			}
			offset += 512;
		}
	}

}
