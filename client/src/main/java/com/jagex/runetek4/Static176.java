package com.jagex.runetek4;

import com.jagex.runetek4.scene.tile.ShapedTile;
import com.jagex.runetek4.scene.tile.GenericTile;
import com.jagex.runetek4.scene.tile.Tile;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static176 {

	@OriginalMember(owner = "runetek4.client!ob", name = "p", descriptor = "Lclient!na;")
	public static final JString aClass100_802 = JString.parse("(U0a )2 non)2existant gosub script)2num: ");

	@OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IIIIIIIIIIIIIIIIIIII)V")
	public static void method3305(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8, @OriginalArg(9) int arg9, @OriginalArg(10) int arg10, @OriginalArg(11) int arg11, @OriginalArg(12) int arg12, @OriginalArg(13) int arg13, @OriginalArg(14) int arg14, @OriginalArg(15) int arg15, @OriginalArg(16) int arg16, @OriginalArg(17) int arg17, @OriginalArg(18) int arg18, @OriginalArg(19) int arg19) {
		@Pc(12) GenericTile local12;
		@Pc(14) int local14;
		if (arg3 == 0) {
			local12 = new GenericTile(arg10, arg11, arg12, arg13, -1, arg18, false);
			for (local14 = arg0; local14 >= 0; local14--) {
				if (SceneGraph.tiles[local14][arg1][arg2] == null) {
					SceneGraph.tiles[local14][arg1][arg2] = new Tile(local14, arg1, arg2);
				}
			}
			SceneGraph.tiles[arg0][arg1][arg2].plainTile = local12;
		} else if (arg3 == 1) {
			local12 = new GenericTile(arg14, arg15, arg16, arg17, arg5, arg19, arg6 == arg7 && arg6 == arg8 && arg6 == arg9);
			for (local14 = arg0; local14 >= 0; local14--) {
				if (SceneGraph.tiles[local14][arg1][arg2] == null) {
					SceneGraph.tiles[local14][arg1][arg2] = new Tile(local14, arg1, arg2);
				}
			}
			SceneGraph.tiles[arg0][arg1][arg2].plainTile = local12;
		} else {
			@Pc(134) ShapedTile local134 = new ShapedTile(arg3, arg4, arg5, arg1, arg2, arg6, arg7, arg8, arg9, arg10, arg11, arg12, arg13, arg14, arg15, arg16, arg17, arg18, arg19);
			for (local14 = arg0; local14 >= 0; local14--) {
				if (SceneGraph.tiles[local14][arg1][arg2] == null) {
					SceneGraph.tiles[local14][arg1][arg2] = new Tile(local14, arg1, arg2);
				}
			}
			SceneGraph.tiles[arg0][arg1][arg2].shapedTile = local134;
		}
	}

	@OriginalMember(owner = "runetek4.client!ob", name = "a", descriptor = "(IIIIII)V")
	public static void method3308(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		for (@Pc(8) int local8 = arg2; local8 <= arg0; local8++) {
			ArrayUtils.fillRange(ObjTypeList.anIntArrayArray10[local8], arg3, arg1, arg4);
		}
	}
}
