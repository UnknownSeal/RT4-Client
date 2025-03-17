package com.jagex.runetek4;

import com.jagex.runetek4.core.utils.Timer;
import com.jagex.runetek4.util.ArrayUtils;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static200 {

	@OriginalMember(owner = "runetek4.client!qe", name = "r", descriptor = "[S")
	public static short[] aShortArray65;

	@OriginalMember(owner = "runetek4.client!qe", name = "v", descriptor = "Lclient!s;")
	public static Timer aClass93_1;

	@OriginalMember(owner = "runetek4.client!qe", name = "t", descriptor = "[I")
	public static final int[] anIntArray421 = new int[50];

	@OriginalMember(owner = "runetek4.client!qe", name = "b", descriptor = "(II)V")
	public static void method3628(@OriginalArg(1) int arg0) {
		MiniMenu.menuActionRow--;
		if (MiniMenu.menuActionRow == arg0) {
			return;
		}
		ArrayUtils.copy(MiniMenu.ops, arg0 + 1, MiniMenu.ops, arg0, MiniMenu.menuActionRow - arg0);
		ArrayUtils.copy(MiniMenu.opBases, arg0 + 1, MiniMenu.opBases, arg0, MiniMenu.menuActionRow - arg0);
		ArrayUtils.copy(MiniMenu.cursors, arg0 + 1, MiniMenu.cursors, arg0, MiniMenu.menuActionRow - arg0);
		ArrayUtils.copy(MiniMenu.actions, arg0 + 1, MiniMenu.actions, arg0, MiniMenu.menuActionRow - arg0);
		ArrayUtils.copy(Static159.aLongArray5, arg0 + 1, Static159.aLongArray5, arg0, MiniMenu.menuActionRow - arg0);
		ArrayUtils.copy(Static196.anIntArray408, arg0 + 1, Static196.anIntArray408, arg0, MiniMenu.menuActionRow - arg0);
		ArrayUtils.copy(Static56.anIntArray142, arg0 + 1, Static56.anIntArray142, arg0, MiniMenu.menuActionRow - arg0);
	}
}
