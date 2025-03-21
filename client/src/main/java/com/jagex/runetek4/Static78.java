package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static78 {

	@OriginalMember(owner = "client!gd", name = "l", descriptor = "I")
	public static int anInt2147;

	@OriginalMember(owner = "client!gd", name = "m", descriptor = "Lclient!ve;")
	public static Js5 aClass153_32;

	@OriginalMember(owner = "client!gd", name = "n", descriptor = "Lclient!qf;")
	public static Sprite aClass3_Sub2_Sub1_3;

	@OriginalMember(owner = "client!gd", name = "b", descriptor = "Lclient!na;")
	public static final JString NULL = JString.parse("null");

	@OriginalMember(owner = "client!gd", name = "c", descriptor = "Lclient!na;")
	public static final JString aClass100_465 = JString.parse(")0");

	@OriginalMember(owner = "client!gd", name = "h", descriptor = "[I")
	public static final int[] ROTATION_WALL_CORNER_TYPE = new int[] { 16, 32, 64, 128 };

	@OriginalMember(owner = "client!gd", name = "i", descriptor = "Lclient!na;")
	public static JString aClass100_466 = null;

	@OriginalMember(owner = "client!gd", name = "a", descriptor = "(I)V")
	public static void removeSoft() {
		Static125.varbitDefinitionCache.removeSoft();
	}

}
