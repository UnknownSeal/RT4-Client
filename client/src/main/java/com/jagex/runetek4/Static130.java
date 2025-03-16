package com.jagex.runetek4;

import com.jagex.runetek4.js5.CacheArchive;
import com.jagex.runetek4.scene.tile.SceneTile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static130 {

	@OriginalMember(owner = "runetek4.client!kc", name = "n", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_1;

	@OriginalMember(owner = "runetek4.client!kc", name = "o", descriptor = "[[[Lclient!bj;")
	public static SceneTile[][][] levelTiles;

	@OriginalMember(owner = "runetek4.client!kc", name = "p", descriptor = "[I")
	public static int[] anIntArray299;

	@OriginalMember(owner = "runetek4.client!kc", name = "t", descriptor = "I")
	public static int anInt3161;

	@OriginalMember(owner = "runetek4.client!kc", name = "w", descriptor = "Lclient!ve;")
	public static CacheArchive aClass153_47;

	@OriginalMember(owner = "runetek4.client!kc", name = "C", descriptor = "Lclient!fd;")
	public static WorldMapFont aClass41_2;

	@OriginalMember(owner = "runetek4.client!kc", name = "s", descriptor = "[I")
	public static final int[] anIntArray300 = new int[] { 1, 1, 1, 1, 4, 1, 1, 5, 6, 1, 5, 0, 7, 0, 4, 1, 7, 2, 1, 1, 6, 1, 1, 3, 6, 1, 7, 0, 0, 6, 7, 0, 1, 7, 6, 1, 1, 1, 5, 4, 3, 2, 1, 1, 0, 4, 1, 5 };

	@OriginalMember(owner = "runetek4.client!kc", name = "a", descriptor = "(ILclient!ve;I)Lclient!qf;")
	public static Sprite method2514(@OriginalArg(1) CacheArchive arg0, @OriginalArg(2) int arg1) {
		return Static254.method4346(arg0, arg1) ? Static82.method1764() : null;
	}
}
