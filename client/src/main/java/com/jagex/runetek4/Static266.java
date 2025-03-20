package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.scene.Scenery;
import com.jagex.runetek4.scene.tile.Tile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static266 {

	@OriginalMember(owner = "runetek4.client!vk", name = "f", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray15;

	@OriginalMember(owner = "runetek4.client!vk", name = "o", descriptor = "I")
	public static int anInt5344;

	@OriginalMember(owner = "runetek4.client!vk", name = "d", descriptor = "Lclient!na;")
	public static final JString aClass100_1010 = JString.parse("null");

	@OriginalMember(owner = "runetek4.client!vk", name = "h", descriptor = "I")
	public static final int anInt5338 = (int) (Math.random() * 33.0D) - 16;

	@OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(Lclient!ve;Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0, @OriginalArg(1) Js5 arg1) {
		Static93.modelArchive = arg0;
		Static132.aClass153_48 = arg1;
	}

	@OriginalMember(owner = "runetek4.client!vk", name = "a", descriptor = "(Lclient!ec;)V")
	public static void method4193(@OriginalArg(0) Scenery arg0) {
		for (@Pc(2) int local2 = arg0.anInt1701; local2 <= arg0.anInt1713; local2++) {
			for (@Pc(9) int local9 = arg0.anInt1696; local9 <= arg0.anInt1698; local9++) {
				@Pc(22) Tile local22 = Static130.levelTiles[arg0.anInt1709][local2][local9];
				if (local22 != null) {
					@Pc(26) int local26;
					for (local26 = 0; local26 < local22.entityCount; local26++) {
						if (local22.sceneries[local26] == arg0) {
							local22.entityCount--;
							for (@Pc(44) int local44 = local26; local44 < local22.entityCount; local44++) {
								local22.sceneries[local44] = local22.sceneries[local44 + 1];
								local22.anIntArray59[local44] = local22.anIntArray59[local44 + 1];
							}
							local22.sceneries[local22.entityCount] = null;
							break;
						}
					}
					local22.locSpans = 0;
					for (local26 = 0; local26 < local22.entityCount; local26++) {
						local22.locSpans |= local22.anIntArray59[local26];
					}
				}
			}
		}
	}
}
