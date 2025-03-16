package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static150 {

	@OriginalMember(owner = "runetek4.client!lk", name = "J", descriptor = "Lclient!na;")
	public static final JString aClass100_687 = JString.parse(")4p=");

	@OriginalMember(owner = "runetek4.client!lk", name = "V", descriptor = "I")
	public static int anInt3582 = 0;

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIBLclient!ve;)Lclient!qf;")
	public static Sprite method2800(@OriginalArg(1) int arg0, @OriginalArg(3) Js5 arg1) {
		return Static234.method4016(arg1, 0, arg0) ? Static82.method1764() : null;
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIILclient!be;Z)V")
	public static void method2801(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1, @OriginalArg(3) Component arg2, @OriginalArg(4) boolean arg3) {
		@Pc(4) int local4 = arg2.anInt445;
		@Pc(7) int local7 = arg2.anInt459;
		if (arg2.aByte5 == 0) {
			arg2.anInt445 = arg2.baseWidth;
		} else if (arg2.aByte5 == 1) {
			arg2.anInt445 = arg1 - arg2.baseWidth;
		} else if (arg2.aByte5 == 2) {
			arg2.anInt445 = arg2.baseWidth * arg1 >> 14;
		} else if (arg2.aByte5 == 3) {
			if (arg2.INVENTORY == 2) {
				arg2.anInt445 = arg2.baseWidth * 32 + (arg2.baseWidth - 1) * arg2.anInt512;
			} else if (arg2.INVENTORY == 7) {
				arg2.anInt445 = arg2.baseWidth * 115 + arg2.anInt512 * (arg2.baseWidth - 1);
			}
		}
		if (arg2.aByte3 == 0) {
			arg2.anInt459 = arg2.anInt488;
		} else if (arg2.aByte3 == 1) {
			arg2.anInt459 = arg0 - arg2.anInt488;
		} else if (arg2.aByte3 == 2) {
			arg2.anInt459 = arg0 * arg2.anInt488 >> 14;
		} else if (arg2.aByte3 == 3) {
			if (arg2.INVENTORY == 2) {
				arg2.anInt459 = (arg2.anInt488 - 1) * arg2.anInt516 + arg2.anInt488 * 32;
			} else if (arg2.INVENTORY == 7) {
				arg2.anInt459 = arg2.anInt488 * 12 + (arg2.anInt488 - 1) * arg2.anInt516;
			}
		}
		if (arg2.aByte5 == 4) {
			arg2.anInt445 = arg2.anInt473 * arg2.anInt459 / arg2.anInt442;
		}
		if (arg2.aByte3 == 4) {
			arg2.anInt459 = arg2.anInt442 * arg2.anInt445 / arg2.anInt473;
		}
		if (Cheat.qaOpTest && (Static36.method940(arg2).anInt546 != 0 || arg2.INVENTORY == 0)) {
			if (arg2.anInt459 < 5 && arg2.anInt445 < 5) {
				arg2.anInt459 = 5;
				arg2.anInt445 = 5;
			} else {
				if (arg2.anInt445 <= 0) {
					arg2.anInt445 = 5;
				}
				if (arg2.anInt459 <= 0) {
					arg2.anInt459 = 5;
				}
			}
		}
		if (arg2.contentType == 1337) {
			Static280.aClass13_26 = arg2;
		}
		if (arg3 && arg2.anObjectArray17 != null && (local4 != arg2.anInt445 || arg2.anInt459 != local7)) {
			@Pc(305) HookRequest local305 = new HookRequest();
			local305.anObjectArray31 = arg2.anObjectArray17;
			local305.source = arg2;
			Static185.aClass69_101.addTail(local305);
		}
	}

}
