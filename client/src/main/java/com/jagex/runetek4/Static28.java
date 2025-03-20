package com.jagex.runetek4;

import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.Tile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static28 {

	@OriginalMember(owner = "client!cd", name = "s", descriptor = "I")
	public static int anInt917;

	@OriginalMember(owner = "client!cd", name = "a", descriptor = "(IIIIZ)V")
	public static void method792(@OriginalArg(3) int arg0, @OriginalArg(4) boolean arg1) {
		Static152.anInt3594 = 104;
		Static99.anInt2550 = 104;
		Static277.anInt5855 = arg0;
		Static197.aClass3_Sub5ArrayArrayArray2 = new Tile[4][Static152.anInt3594][Static99.anInt2550];
		Static107.anIntArrayArrayArray10 = new int[4][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
		if (GlRenderer.enabled) {
			Static36.aGlTileArrayArray1 = new GlTile[4][];
		}
		if (arg1) {
			Static276.aClass3_Sub5ArrayArrayArray3 = new Tile[1][Static152.anInt3594][Static99.anInt2550];
			Static62.anIntArrayArray11 = new int[Static152.anInt3594][Static99.anInt2550];
			Static80.anIntArrayArrayArray19 = new int[1][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
			if (GlRenderer.enabled) {
				Static195.aClass3_Sub14ArrayArray3 = new GlTile[1][];
			}
		} else {
			Static276.aClass3_Sub5ArrayArrayArray3 = null;
			Static62.anIntArrayArray11 = null;
			Static80.anIntArrayArrayArray19 = null;
			Static195.aClass3_Sub14ArrayArray3 = null;
		}
		Static278.method4648(false);
		Static91.aClass120Array1 = new Class120[500];
		anInt917 = 0;
		Static247.aClass120Array2 = new Class120[500];
		Static215.anInt4870 = 0;
		Static140.anIntArrayArrayArray12 = new int[4][Static152.anInt3594 + 1][Static99.anInt2550 + 1];
		Static243.aClass31Array3 = new Scenery[5000];
		Static22.anInt726 = 0;
		Static25.aClass31Array2 = new Scenery[100];
		Static48.aBooleanArrayArray1 = new boolean[Static277.anInt5855 + Static277.anInt5855 + 1][Static277.anInt5855 + Static277.anInt5855 + 1];
		Static89.aBooleanArrayArray3 = new boolean[Static277.anInt5855 + Static277.anInt5855 + 2][Static277.anInt5855 + Static277.anInt5855 + 2];
		SceneGraph.aByteArrayArrayArray13 = new byte[4][Static152.anInt3594][Static99.anInt2550];
	}
}