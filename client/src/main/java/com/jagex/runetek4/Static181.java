package com.jagex.runetek4;

import com.jagex.runetek4.game.client.logic.DelayedStateChange;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.media.Rasterizer;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static181 {

	@OriginalMember(owner = "runetek4.client!oi", name = "h", descriptor = "Lclient!qf;")
	public static Sprite aClass3_Sub2_Sub1_9;

	@OriginalMember(owner = "runetek4.client!oi", name = "m", descriptor = "I")
	public static int anInt4296;

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void method3340(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		if (arg5 >= Static172.anInt4164 && arg5 <= Static224.anInt5063 && arg0 >= Static172.anInt4164 && arg0 <= Static224.anInt5063 && arg6 >= Static172.anInt4164 && Static224.anInt5063 >= arg6 && Static172.anInt4164 <= arg1 && arg1 <= Static224.anInt5063 && Static267.anInt5773 <= arg4 && arg4 <= Static106.anInt2869 && arg7 >= Static267.anInt5773 && Static106.anInt2869 >= arg7 && arg2 >= Static267.anInt5773 && Static106.anInt2869 >= arg2 && arg3 >= Static267.anInt5773 && arg3 <= Static106.anInt2869) {
			Static38.method962(arg2, arg8, arg7, arg6, arg1, arg3, arg4, arg0, arg5);
		} else {
			Static165.method3162(arg5, arg0, arg7, arg8, arg3, arg2, arg1, arg6, arg4);
		}
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(II)Lclient!na;")
	public static JString method3341(@OriginalArg(0) int arg0) {
		return JString.concatenate(new JString[] { JString.parseInt(arg0 >> 24 & 0xFF), ClientScriptRunner.aClass100_994, JString.parseInt(arg0 >> 16 & 0xFF), ClientScriptRunner.aClass100_994, JString.parseInt(arg0 >> 8 & 0xFF), ClientScriptRunner.aClass100_994, JString.parseInt(arg0 & 0xFF) });
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(I)V")
	public static void method3342() {
		Static67.aClass99_20.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(Lclient!ve;B)V")
	public static void method3344(@OriginalArg(0) Js5 arg0) {
		if (Static18.aBoolean40) {
			return;
		}
		if (GlRenderer.enabled) {
			Static46.method1184();
		} else {
			Rasterizer.clear();
		}
		Static78.aClass3_Sub2_Sub1_3 = Static130.method2514(arg0, Static262.anInt5754);
		@Pc(20) int local20 = GameShell.canvasHeigth;
		@Pc(26) int local26 = local20 * 956 / 503;
		Static78.aClass3_Sub2_Sub1_3.method1419((GameShell.canvasWidth - local26) / 2, 0, local26, local20);
		Static243.aClass36_1 = Static40.method1010(Static136.anInt3322, arg0);
		Static243.aClass36_1.drawImage(GameShell.canvasWidth / 2 - Static243.aClass36_1.anInt4270 / 2, 18);
		Static18.aBoolean40 = true;
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(II)V")
	public static void method3345(@OriginalArg(0) int arg0) {
		@Pc(8) DelayedStateChange local8 = Static238.method4143(5, arg0);
		local8.method1007();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(I)V")
	public static void method3347() {
		Static110.aClass99_15.method3104();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(ILclient!ve;Lclient!ve;Z)V")
	public static void method3349(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		Static30.aBoolean61 = true;
		Static121.aClass153_45 = arg1;
		Static146.aClass153_54 = arg0;
	}
}
