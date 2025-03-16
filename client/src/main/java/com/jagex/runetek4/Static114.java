package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.Font;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static114 {

	@OriginalMember(owner = "runetek4.client!j", name = "x", descriptor = "Lclient!rk;")
	public static Font aClass3_Sub2_Sub9_42;

	@OriginalMember(owner = "runetek4.client!j", name = "z", descriptor = "Lclient!na;")
	public static final JString SHIFTCLICK = Static28.parse("::shiftclick");

	@OriginalMember(owner = "runetek4.client!j", name = "L", descriptor = "I")
	public static int anInt5844 = 0;

	@OriginalMember(owner = "runetek4.client!j", name = "M", descriptor = "Lclient!na;")
	public static final JString REPLACECANVAS = Static28.parse("::replacecanvas");

	@OriginalMember(owner = "runetek4.client!j", name = "O", descriptor = "[I")
	public static final int[] anIntArray565 = new int[] { 1, -1, -1, 1 };

	@OriginalMember(owner = "runetek4.client!j", name = "c", descriptor = "(I)V")
	public static void method4625() {
		Static204.aClass69_113 = new LinkedList();
	}

	@OriginalMember(owner = "runetek4.client!j", name = "a", descriptor = "([BI)Lclient!dd;")
	public static SoftwareFont method4635(@OriginalArg(0) byte[] arg0) {
		if (arg0 == null) {
			return null;
		} else {
			@Pc(22) SoftwareFont local22 = new SoftwareFont(arg0, Static274.anIntArray440, Static269.anIntArray252, Static254.anIntArray488, Static26.anIntArray66, aClass6.aByteArrayArray5);
			Static75.method1631();
			return local22;
		}
	}

	@OriginalMember(owner = "runetek4.client!j", name = "g", descriptor = "(I)V")
	public static void method4637() {
		Static165.aClass3_Sub2_Sub1_8 = null;
		Static39.aClass3_Sub2_Sub1_1 = null;
		Static92.aClass3_Sub2_Sub1_6 = null;
		Static181.aClass3_Sub2_Sub1_9 = null;
		Static204.aClass3_Sub2_Sub1_10 = null;
	}
}
