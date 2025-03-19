package com.jagex.runetek4;

import com.jagex.runetek4.media.renderable.Entity;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.Tile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static105 {

	@OriginalMember(owner = "runetek4.client!ib", name = "g", descriptor = "Lclient!na;")
	public static final JString aClass100_559 = JString.parse("<col=ffff00>");

	@OriginalMember(owner = "runetek4.client!ib", name = "k", descriptor = "Lclient!na;")
	public static final JString aClass100_561 = JString.parse(" )2> <col=ffffff>");

	@OriginalMember(owner = "runetek4.client!ib", name = "b", descriptor = "(I)V")
	public static void method2255() {
		Static251.blendChroma = null;
		Static60.anIntArrayArrayArray6 = null;
		Static128.blendMagnitude = null;
		Static163.aByteArrayArrayArray11 = null;
		Static4.aByteArrayArrayArray1 = null;
		Static118.levelShademap = null;
		Static240.aByteArrayArrayArray14 = null;
		Static253.levelTileUnderlayIds = null;
		Static139.anIntArray325 = null;
		BZip2State.anIntArray376 = null;
		Static279.anIntArray568 = null;
	}

	@OriginalMember(owner = "runetek4.client!ib", name = "a", descriptor = "(IIIIIIIILclient!th;IZJ)Z")
	public static boolean addLoc(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) Entity arg8, @OriginalArg(9) int arg9, @OriginalArg(10) boolean arg10, @OriginalArg(11) long arg11) {
		@Pc(6) boolean local6 = SceneGraph.tileHeights == Static80.anIntArrayArrayArray19;
		@Pc(8) int local8 = 0;
		@Pc(17) int local17;
		for (@Pc(10) int local10 = arg1; local10 < arg1 + arg3; local10++) {
			for (local17 = arg2; local17 < arg2 + arg4; local17++) {
				if (local10 < 0 || local17 < 0 || local10 >= Static152.anInt3594 || local17 >= Static99.anInt2550) {
					return false;
				}
				@Pc(42) Tile local42 = Static130.levelTiles[arg0][local10][local17];
				if (local42 != null && local42.entityCount >= 5) {
					return false;
				}
			}
		}
		@Pc(58) Scenery local58 = new Scenery();
		local58.hash = arg11;
		local58.anInt1709 = arg0;
		local58.anInt1699 = arg5;
		local58.anInt1703 = arg6;
		local58.anInt1706 = arg7;
		local58.entity = arg8;
		local58.anInt1714 = arg9;
		local58.anInt1701 = arg1;
		local58.anInt1696 = arg2;
		local58.anInt1713 = arg1 + arg3 - 1;
		local58.anInt1698 = arg2 + arg4 - 1;
		@Pc(108) int local108;
		for (local17 = arg1; local17 < arg1 + arg3; local17++) {
			for (local108 = arg2; local108 < arg2 + arg4; local108++) {
				@Pc(115) int local115 = 0;
				if (local17 > arg1) {
					local115++;
				}
				if (local17 < arg1 + arg3 - 1) {
					local115 += 4;
				}
				if (local108 > arg2) {
					local115 += 8;
				}
				if (local108 < arg2 + arg4 - 1) {
					local115 += 2;
				}
				for (@Pc(141) int local141 = arg0; local141 >= 0; local141--) {
					if (Static130.levelTiles[local141][local17][local108] == null) {
						Static130.levelTiles[local141][local17][local108] = new Tile(local141, local17, local108);
					}
				}
				@Pc(174) Tile local174 = Static130.levelTiles[arg0][local17][local108];
				local174.sceneries[local174.entityCount] = local58;
				local174.anIntArray59[local174.entityCount] = local115;
				local174.locSpans |= local115;
				local174.entityCount++;
				if (local6 && Static62.anIntArrayArray11[local17][local108] != 0) {
					local8 = Static62.anIntArrayArray11[local17][local108];
				}
			}
		}
		if (local6 && local8 != 0) {
			for (local17 = arg1; local17 < arg1 + arg3; local17++) {
				for (local108 = arg2; local108 < arg2 + arg4; local108++) {
					if (Static62.anIntArrayArray11[local17][local108] == 0) {
						Static62.anIntArrayArray11[local17][local108] = local8;
					}
				}
			}
		}
		if (arg10) {
			Static243.aClass31Array3[Static22.anInt726++] = local58;
		}
		return true;
	}
}
