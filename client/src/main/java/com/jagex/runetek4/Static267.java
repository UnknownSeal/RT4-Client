package com.jagex.runetek4;

import com.jagex.runetek4.node.NodeCache;
import com.jagex.runetek4.scene.tile.GroundDecor;
import com.jagex.runetek4.scene.tile.Tile;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static267 {

	@OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "Lclient!n;")
	public static final NodeCache skeletonCache = new NodeCache(100);

	@OriginalMember(owner = "runetek4.client!vl", name = "e", descriptor = "[I")
	public static final int[] ROTATION_WALL_TYPE = new int[] { 1, 2, 4, 8 };

	@OriginalMember(owner = "runetek4.client!vl", name = "f", descriptor = "Lclient!na;")
	public static final JString CABBAGE = JString.parse("Cabbage");

	@OriginalMember(owner = "runetek4.client!vl", name = "h", descriptor = "I")
	public static int anInt5773 = 0;

	@OriginalMember(owner = "runetek4.client!vl", name = "i", descriptor = "[I")
	public static int[] anIntArray518 = new int[2];

	@OriginalMember(owner = "runetek4.client!vl", name = "l", descriptor = "I")
	public static int anInt5776 = 0;

	@OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "(III)Lclient!bm;")
	public static GroundDecor method4526(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2) {
		@Pc(7) Tile floorDecoration = SceneGraph.tiles[arg0][arg1][arg2];
		if (floorDecoration == null) {
			return null;
		} else {
			@Pc(14) GroundDecor local14 = floorDecoration.groundDecor;
			floorDecoration.groundDecor = null;
			return local14;
		}
	}

	@OriginalMember(owner = "runetek4.client!vl", name = "a", descriptor = "(I)Z")
	public static boolean method4527() {
		if (client.objectTag) {
			try {
				return !((Boolean) Static119.SHOWINGVIDEOAD.browserControlCall(GameShell.signLink.applet));
			} catch (@Pc(21) Throwable local21) {
			}
		}
		return true;
	}

    @OriginalMember(owner = "runetek4.client!vl", name = "b", descriptor = "(I)V")
	public static void removeSoft() {
		PreciseSleep.aClass99_29.removeSoft();
		Static219.aClass99_27.removeSoft();
	}
}
