package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.math.BigInteger;

public final class Static86 {

    // Jagex's RSA key:
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");
    public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");
    @OriginalMember(owner = "runetek4.client!gm", name = "T", descriptor = "Lclient!k;")
	public static Js5CacheQueue js5CacheQueue;

	@OriginalMember(owner = "runetek4.client!gm", name = "ib", descriptor = "Lclient!ve;")
	public static Js5 aClass153_37;

	@OriginalMember(owner = "runetek4.client!gm", name = "R", descriptor = "I")
	public static int anInt2293 = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "runetek4.client!gm", name = "W", descriptor = "Lclient!na;")
	public static final JagString aClass100_488 = Static28.parse("_");

	@OriginalMember(owner = "runetek4.client!gm", name = "bb", descriptor = "Z")
	public static boolean aBoolean129 = false;

	@OriginalMember(owner = "runetek4.client!gm", name = "db", descriptor = "Lclient!na;")
	public static final JagString aClass100_490 = Static28.parse("cross");

	@OriginalMember(owner = "runetek4.client!gm", name = "gb", descriptor = "[I")
	public static final int[] anIntArray211 = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };

	@OriginalMember(owner = "runetek4.client!gm", name = "f", descriptor = "(B)V")
	public static void method1799() {
		Static222.aBoolean246 = true;
	}

	@OriginalMember(owner = "runetek4.client!gm", name = "h", descriptor = "(I)V")
	public static void method1800() {
		Static116.anInt2951 = 0;
		Static240.anInt5335 = 0;
		Static49.method1202();
		Static278.method4645();
		Static234.method4014();
		@Pc(19) int local19;
		for (local19 = 0; local19 < Static240.anInt5335; local19++) {
			@Pc(30) int local30 = Static52.anIntArray136[local19];
			if (Static175.aClass8_Sub4_Sub2Array1[local30].anInt3430 != Static83.anInt372) {
				if (Static175.aClass8_Sub4_Sub2Array1[local30].npcType.hasBackgroundSound()) {
					Static91.method1877(Static175.aClass8_Sub4_Sub2Array1[local30]);
				}
				Static175.aClass8_Sub4_Sub2Array1[local30].method2698(null);
				Static175.aClass8_Sub4_Sub2Array1[local30] = null;
			}
		}
		if (Static223.anInt5028 != Static57.aClass3_Sub15_Sub1_3.pos) {
			throw new RuntimeException("gnp1 pos:" + Static57.aClass3_Sub15_Sub1_3.pos + " psize:" + Static223.anInt5028);
		}
		for (local19 = 0; local19 < Static272.anInt5214; local19++) {
			if (Static175.aClass8_Sub4_Sub2Array1[Static33.anIntArray79[local19]] == null) {
				throw new RuntimeException("gnp2 pos:" + local19 + " size:" + Static272.anInt5214);
			}
		}
	}
}
