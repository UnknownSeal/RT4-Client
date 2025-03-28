package com.jagex.runetek4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static278 {

	@OriginalMember(owner = "runetek4.client!wj", name = "a", descriptor = "(IIIIIII)V")
	public static void method4647(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6) {
		@Pc(3) Class120 local3 = new Class120();
		local3.anInt4452 = arg1 / 128;
		local3.anInt4446 = arg2 / 128;
		local3.anInt4461 = arg3 / 128;
		local3.anInt4464 = arg4 / 128;
		local3.anInt4453 = arg0;
		local3.anInt4460 = arg1;
		local3.anInt4445 = arg2;
		local3.anInt4458 = arg3;
		local3.anInt4449 = arg4;
		local3.anInt4444 = arg5;
		local3.anInt4447 = arg6;
		SceneGraph.aClass120Array1[SceneGraph.anInt917++] = local3;
	}

}
