package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.meltype.MapElementTypeList;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static235 {

	@OriginalMember(owner = "runetek4.client!tb", name = "X", descriptor = "Lclient!se;")
	public static MapElementTypeList aMapElementTypeList_2;

	@OriginalMember(owner = "runetek4.client!tb", name = "P", descriptor = "Lclient!na;")
	public static final JagString aClass100_1002 = Static28.parse("(U5");

	@OriginalMember(owner = "runetek4.client!tb", name = "Q", descriptor = "I")
	public static int anInt5276 = 0;

	@OriginalMember(owner = "runetek4.client!tb", name = "h", descriptor = "(I)I")
	public static int method4044() {
		return Static172.shiftClick && Static187.pressedKeys[81] && PreciseSleep.anInt5204 > 2 ? Static190.anIntArray382[PreciseSleep.anInt5204 - 2] : Static190.anIntArray382[PreciseSleep.anInt5204 - 1];
	}

	@OriginalMember(owner = "runetek4.client!tb", name = "b", descriptor = "(IB)Lclient!bc;")
	public static Class3_Sub2_Sub2 method4045(@OriginalArg(0) int arg0) {
		@Pc(10) Class3_Sub2_Sub2 local10 = (Class3_Sub2_Sub2) Static73.aClass54_7.get((long) arg0);
		if (local10 != null) {
			return local10;
		}
		@Pc(24) byte[] local24;
		if (arg0 < 32768) {
			local24 = Static87.aClass153_38.getfile(0, arg0);
		} else {
			local24 = Static29.aClass153_19.getfile(0, arg0 & 0x7FFF);
		}
		local10 = new Class3_Sub2_Sub2();
		if (local24 != null) {
			local10.method470(new Packet(local24));
		}
		if (arg0 >= 32768) {
			local10.method465();
		}
		Static73.aClass54_7.put(local10, (long) arg0);
		return local10;
	}
}
