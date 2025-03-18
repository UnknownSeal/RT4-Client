package com.jagex.runetek4;

import com.jagex.runetek4.cache.CacheArchive;
import com.jagex.runetek4.scene.tile.SceneTile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static276 {

	@OriginalMember(owner = "runetek4.client!wh", name = "c", descriptor = "[[[Lclient!bj;")
	public static SceneTile[][][] aClass3_Sub5ArrayArrayArray3;

	@OriginalMember(owner = "runetek4.client!wh", name = "l", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array11;

	@OriginalMember(owner = "runetek4.client!wh", name = "g", descriptor = "[I")
	public static final int[] anIntArray563 = new int[50];

	@OriginalMember(owner = "runetek4.client!wh", name = "j", descriptor = "[Lclient!cl;")
	public static final AnimFrameset[] aClass3_Sub2_Sub7Array8 = new AnimFrameset[14];

	@OriginalMember(owner = "runetek4.client!wh", name = "n", descriptor = "Lclient!na;")
	public static final JString aClass100_1095 = JString.parse("; version=1; path=)4; domain=");

	@OriginalMember(owner = "runetek4.client!wh", name = "s", descriptor = "I")
	public static int anInt5816 = 0;

	@OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(IIII)Z")
	public static boolean visible(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		if (CacheArchive.method187(arg0, arg1, arg2)) {
			@Pc(10) int local10 = arg1 << 7;
			@Pc(14) int local14 = arg2 << 7;
			return Static256.method4394(local10 + 1, SceneGraph.tileHeights[arg0][arg1][arg2] + arg3, local14 + 1) && Static256.method4394(local10 + 128 - 1, SceneGraph.tileHeights[arg0][arg1 + 1][arg2] + arg3, local14 + 1) && Static256.method4394(local10 + 128 - 1, SceneGraph.tileHeights[arg0][arg1 + 1][arg2 + 1] + arg3, local14 + 128 - 1) && Static256.method4394(local10 + 1, SceneGraph.tileHeights[arg0][arg1][arg2 + 1] + arg3, local14 + 128 - 1);
		} else {
			return false;
		}
	}

	@OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(I)V")
	public static void clear() {
		ClientScriptRunner.aClass99_31.clean();
	}

	@OriginalMember(owner = "runetek4.client!wh", name = "b", descriptor = "(B)Lclient!ok;")
	public static IndexedSprite method4614() {
		@Pc(27) IndexedSprite local27;
		if (GlRenderer.enabled) {
			local27 = new GlIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], aClass6.aByteArrayArray5[0], Static259.anIntArray513);
		} else {
			local27 = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], aClass6.aByteArrayArray5[0], Static259.anIntArray513);
		}
		Static75.method1631();
		return local27;
	}

	@OriginalMember(owner = "runetek4.client!wh", name = "a", descriptor = "(II)V")
	public static void method4615() {
		PreciseSleep.aClass99_29.clean(5);
		Static219.aClass99_27.clean(5);
	}
}
