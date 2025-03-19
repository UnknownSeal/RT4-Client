package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.scene.tile.WallDecor;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static91 {

	@OriginalMember(owner = "runetek4.client!hc", name = "O", descriptor = "[Lclient!pe;")
	public static Class120[] aClass120Array1;

    @OriginalMember(owner = "runetek4.client!hc", name = "d", descriptor = "(I)I")
	public static int getZoom() {
		if ((double) Static138.aFloat14 == 3.0D) {
			return 37;
		} else if ((double) Static138.aFloat14 == 4.0D) {
			return 50;
		} else if ((double) Static138.aFloat14 == 6.0D) {
			return 75;
		} else if ((double) Static138.aFloat14 == 8.0D) {
			return 100;
		} else {
			return 200;
		}
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!na;Z)I")
	public static int method1879(@OriginalArg(0) JString arg0) {
		if (Static203.aMapElementTypeList_1 == null || arg0.length() == 0) {
			return -1;
		}
		for (@Pc(20) int local20 = 0; local20 < Static203.aMapElementTypeList_1.anInt5074; local20++) {
			if (Static203.aMapElementTypeList_1.aClass100Array153[local20].method3140(Static101.aClass100_538, Static197.aClass100_872).strEquals(arg0)) {
				return local20;
			}
		}
		return -1;
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(IIIILclient!th;Lclient!th;IIIIJ)V")
	public static void addWallDecoration(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) Entity arg4, @OriginalArg(5) Entity arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) long arg10) {
		if (arg4 == null) {
			return;
		}
		@Pc(6) WallDecor wallDecor = new WallDecor();
		wallDecor.key = arg10;
		wallDecor.xFine = arg1 * 128 + 64;
		wallDecor.zFine = arg2 * 128 + 64;
		wallDecor.anInt1391 = arg3;
		wallDecor.primary = arg4;
		wallDecor.secondary = arg5;
		wallDecor.type = arg6;
		wallDecor.anInt1388 = arg7;
		wallDecor.xOffset = arg8;
		wallDecor.zOffset = arg9;
		for (@Pc(46) int local46 = arg0; local46 >= 0; local46--) {
			if (Static130.levelTiles[local46][arg1][arg2] == null) {
				Static130.levelTiles[local46][arg1][arg2] = new Tile(local46, arg1, arg2);
			}
		}
		Static130.levelTiles[arg0][arg1][arg2].wallDecor = wallDecor;
	}
}
