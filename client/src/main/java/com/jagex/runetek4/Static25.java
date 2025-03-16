package com.jagex.runetek4;

import com.jagex.runetek4.input.Keyboard;
import com.jagex.runetek4.scene.Scenery;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static25 {

	@OriginalMember(owner = "client!c", name = "bb", descriptor = "[Lclient!ec;")
	public static Scenery[] aClass31Array2;

	@OriginalMember(owner = "client!c", name = "Y", descriptor = "Lclient!na;")
	public static final JString aClass100_154 = Static28.parse("Mem:");

	@OriginalMember(owner = "client!c", name = "eb", descriptor = "I")
	public static int y = 0;

	@OriginalMember(owner = "client!c", name = "hb", descriptor = "Z")
	public static boolean aBoolean57 = false;

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(I)Z")
	public static boolean nextKey() {
		@Pc(6) Keyboard local6 = Static10.aClass149_1;
		synchronized (Static10.aClass149_1) {
			if (Static228.anInt5105 == Static102.anInt2678) {
				return false;
			} else {
				Static102.keyCode = BZip2State.anIntArray375[Static102.anInt2678];
				Static193.keyChar = Static264.anIntArray413[Static102.anInt2678];
				Static102.anInt2678 = Static102.anInt2678 + 1 & 0x7F;
				return true;
			}
		}
	}

	@OriginalMember(owner = "client!c", name = "c", descriptor = "(II)V")
	public static void method715() {
		Static220.aClass99_28.clear(5);
	}

	@OriginalMember(owner = "client!c", name = "d", descriptor = "(II)V")
	public static void method716() {
		Static79.aClass99_11.clear(5);
		aClass6.aClass99_5.clear(5);
	}
}
