package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class IdkTypeList {

	@OriginalMember(owner = "runetek4.client!oi", name = "m", descriptor = "I")
	public static int anInt4296;

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(IIIIIIIIII)V")
	public static void method3340(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3, @OriginalArg(4) int arg4, @OriginalArg(5) int arg5, @OriginalArg(6) int arg6, @OriginalArg(7) int arg7, @OriginalArg(8) int arg8) {
		if (arg5 >= Static172.anInt4164 && arg5 <= FloorUnderlayTypeList.anInt5063 && arg0 >= Static172.anInt4164 && arg0 <= FloorUnderlayTypeList.anInt5063 && arg6 >= Static172.anInt4164 && FloorUnderlayTypeList.anInt5063 >= arg6 && Static172.anInt4164 <= arg1 && arg1 <= FloorUnderlayTypeList.anInt5063 && Static267.anInt5773 <= arg4 && arg4 <= Static106.anInt2869 && arg7 >= Static267.anInt5773 && Static106.anInt2869 >= arg7 && arg2 >= Static267.anInt5773 && Static106.anInt2869 >= arg2 && arg3 >= Static267.anInt5773 && arg3 <= Static106.anInt2869) {
			Static38.method962(arg2, arg8, arg7, arg6, arg1, arg3, arg4, arg0, arg5);
		} else {
			Static165.method3162(arg5, arg0, arg7, arg8, arg3, arg2, arg1, arg6, arg4);
		}
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(I)V")
	public static void removeSoft() {
		Static67.aClass99_20.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "b", descriptor = "(I)V")
	public static void clear() {
		Static110.aClass99_15.clean();
	}

	@OriginalMember(owner = "runetek4.client!oi", name = "a", descriptor = "(ILclient!ve;Lclient!ve;Z)V")
	public static void init(@OriginalArg(1) Js5 arg0, @OriginalArg(2) Js5 arg1) {
		Static30.aBoolean61 = true;
		Static121.aClass153_45 = arg1;
		Static146.aClass153_54 = arg0;
	}
}
