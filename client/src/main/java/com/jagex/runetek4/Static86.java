package com.jagex.runetek4;

import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalMember;

import java.math.BigInteger;

public final class Static86 {

    // Jagex's RSA key:
    public static final BigInteger RSA_MODULUS = new BigInteger("7162900525229798032761816791230527296329313291232324290237849263501208207972894053929065636522363163621000728841182238772712427862772219676577293600221789");

	public static final BigInteger RSA_EXPONENT = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

    @OriginalMember(owner = "client!gm", name = "ib", descriptor = "Lclient!ve;")
	public static Js5 aClass153_37;

	@OriginalMember(owner = "client!gm", name = "R", descriptor = "I")
	public static int anInt2293 = (int) (Math.random() * 17.0D) - 8;

	@OriginalMember(owner = "client!gm", name = "W", descriptor = "Lclient!na;")
	public static final JString aClass100_488 = JString.parse("_");

	@OriginalMember(owner = "client!gm", name = "bb", descriptor = "Z")
	public static boolean aBoolean129 = false;

	@OriginalMember(owner = "client!gm", name = "gb", descriptor = "[I")
	public static final int[] WALL_CORNER_TYPE_64_BLOCK_LOC_SPANS = new int[] { 0, 4, 4, 8, 0, 0, 8, 0, 0 };

}
