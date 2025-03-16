package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SeqType;
import com.jagex.runetek4.frame.Minimap;
import com.jagex.runetek4.scene.tile.ComplexTile;
import com.jagex.runetek4.scene.tile.GenericTile;
import com.jagex.runetek4.scene.tile.SceneTile;
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
		if (arg4 - arg3 >= Static172.anInt4164 && FluTypeList.anInt5063 >= arg4 + arg3 && Static267.anInt5773 <= arg1 - arg3 && Static106.anInt2869 >= arg3 + arg1) {
			Static8.method120(arg5, arg0, arg1, arg2, arg3, arg4);
		} else {
			Static280.method4670(arg2, arg1, arg3, arg5, arg0, arg4);
		}
	}

	@OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "([IIIIII)V")
	public static void drawMinimapTile(@OriginalArg(0) int[] destPixels, @OriginalArg(1) int offset, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(7) SceneTile sceneTile = Static130.levelTiles[arg2][arg3][arg4];
		if (sceneTile == null) {
			return;
		}
		@Pc(13) GenericTile genericTile = sceneTile.plainTile;
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
		@Pc(58) ComplexTile local58 = sceneTile.shapedTile;
		if (local58 == null) {
			return;
		}
		local23 = local58.anInt1966;
		@Pc(67) int rotation = local58.anInt1967;
		@Pc(70) int underlayColor = local58.underlayRGB;
		@Pc(73) int overlayColor = local58.overlayRGB;
		@Pc(77) int[] local77 = Minimap.anIntArrayArray24[local23];
		@Pc(81) int[] local81 = Minimap.anIntArrayArray46[rotation];
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

	@OriginalMember(owner = "runetek4.client!ma", name = "a", descriptor = "(ILclient!tk;IIZI)V")
	public static void method2836(@OriginalArg(0) int arg0, @OriginalArg(1) SeqType arg1, @OriginalArg(3) int arg2, @OriginalArg(4) boolean arg3, @OriginalArg(5) int arg4) {
		if (SoundPlayer.size >= 50 || (arg1.sound == null || arg4 >= arg1.sound.length || arg1.sound[arg4] == null)) {
			return;
		}
		@Pc(36) int local36 = arg1.sound[arg4][0];
		@Pc(40) int local40 = local36 >> 8;
		@Pc(57) int local57;
		if (arg1.sound[arg4].length > 1) {
			local57 = (int) ((double) arg1.sound[arg4].length * Math.random());
			if (local57 > 0) {
				local40 = arg1.sound[arg4][local57];
			}
		}
		@Pc(73) int local73 = local36 >> 5 & 0x7;
		@Pc(77) int local77 = local36 & 0x1F;
		if (local77 == 0) {
			if (arg3) {
				Static26.method744(local73, local40, 0);
			}
		} else if (Preferences.ambientSoundsVolume != 0) {
			Static200.anIntArray421[SoundPlayer.size] = local40;
			Static276.anIntArray563[SoundPlayer.size] = local73;
			@Pc(111) int local111 = (arg0 - 64) / 128;
			local57 = (arg2 - 64) / 128;
			Static164.anIntArray362[SoundPlayer.size] = 0;
			Static173.aClass138Array1[SoundPlayer.size] = null;
			Static26.anIntArray68[SoundPlayer.size] = local77 + (local57 << 16) + (local111 << 8);
			SoundPlayer.size++;
		}
	}
}
