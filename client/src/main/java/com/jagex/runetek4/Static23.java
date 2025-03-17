package com.jagex.runetek4;

import com.jagex.runetek4.game.client.Inv;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static23 {

	@OriginalMember(owner = "client!bm", name = "a", descriptor = "F")
	public static float aFloat5;

	@OriginalMember(owner = "client!bm", name = "e", descriptor = "Lclient!ve;")
	public static Js5 aClass153_11;

	@OriginalMember(owner = "client!bm", name = "p", descriptor = "Lclient!na;")
	public static final JString aClass100_133 = JString.parse("(U1");

	@OriginalMember(owner = "client!bm", name = "a", descriptor = "(IBIIII)V")
	public static void method645(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) int arg2, @OriginalArg(4) int arg3, @OriginalArg(5) int arg4) {
		@Pc(3) int local3;
		@Pc(10) int local10;
		for (local3 = arg1; local3 <= arg3 + arg1; local3++) {
			for (local10 = arg2; local10 <= arg4 + arg2; local10++) {
				if (local10 >= 0 && local10 < 104 && local3 >= 0 && local3 < 104) {
					Static118.levelShademap[arg0][local10][local3] = 127;
				}
			}
		}
		for (local3 = arg1; local3 < arg3 + arg1; local3++) {
			for (local10 = arg2; local10 < arg2 + arg4; local10++) {
				if (local10 >= 0 && local10 < 104 && local3 >= 0 && local3 < 104) {
					SceneGraph.tileHeights[arg0][local10][local3] = arg0 <= 0 ? 0 : SceneGraph.tileHeights[arg0 - 1][local10][local3];
				}
			}
		}
		if (arg2 > 0 && arg2 < 104) {
			for (local3 = arg1 + 1; local3 < arg1 + arg3; local3++) {
				if (local3 >= 0 && local3 < 104) {
					SceneGraph.tileHeights[arg0][arg2][local3] = SceneGraph.tileHeights[arg0][arg2 - 1][local3];
				}
			}
		}
		if (arg1 > 0 && arg1 < 104) {
			for (local3 = arg2 + 1; local3 < arg2 + arg4; local3++) {
				if (local3 >= 0 && local3 < 104) {
					SceneGraph.tileHeights[arg0][local3][arg1] = SceneGraph.tileHeights[arg0][local3][arg1 - 1];
				}
			}
		}
		if (arg2 < 0 || arg1 < 0 || arg2 >= 104 || arg1 >= 104) {
			return;
		}
		if (arg0 == 0) {
			if (arg2 > 0 && SceneGraph.tileHeights[arg0][arg2 - 1][arg1] != 0) {
				SceneGraph.tileHeights[arg0][arg2][arg1] = SceneGraph.tileHeights[arg0][arg2 - 1][arg1];
			} else if (arg1 > 0 && SceneGraph.tileHeights[arg0][arg2][arg1 - 1] != 0) {
				SceneGraph.tileHeights[arg0][arg2][arg1] = SceneGraph.tileHeights[arg0][arg2][arg1 - 1];
			} else if (arg2 > 0 && arg1 > 0 && SceneGraph.tileHeights[arg0][arg2 - 1][arg1 - 1] != 0) {
				SceneGraph.tileHeights[arg0][arg2][arg1] = SceneGraph.tileHeights[arg0][arg2 - 1][arg1 - 1];
			}
		} else if (arg2 > 0 && SceneGraph.tileHeights[arg0 - 1][arg2 - 1][arg1] != SceneGraph.tileHeights[arg0][arg2 - 1][arg1]) {
			SceneGraph.tileHeights[arg0][arg2][arg1] = SceneGraph.tileHeights[arg0][arg2 - 1][arg1];
		} else if (arg1 > 0 && SceneGraph.tileHeights[arg0][arg2][arg1 - 1] != SceneGraph.tileHeights[arg0 - 1][arg2][arg1 - 1]) {
			SceneGraph.tileHeights[arg0][arg2][arg1] = SceneGraph.tileHeights[arg0][arg2][arg1 - 1];
		} else if (arg2 > 0 && arg1 > 0 && SceneGraph.tileHeights[arg0][arg2 - 1][arg1 - 1] != SceneGraph.tileHeights[arg0 - 1][arg2 - 1][arg1 - 1]) {
			SceneGraph.tileHeights[arg0][arg2][arg1] = SceneGraph.tileHeights[arg0][arg2 - 1][arg1 - 1];
		}
	}

	@OriginalMember(owner = "client!bm", name = "a", descriptor = "(III)I")
	public static int method647(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) Inv local10 = (Inv) Inv.recentUse.getNode((long) arg0);
		if (local10 == null) {
			return 0;
		} else if (arg1 >= 0 && arg1 < local10.invSlotObjCount.length) {
			return local10.invSlotObjCount[arg1];
		} else {
			return 0;
		}
	}

}
