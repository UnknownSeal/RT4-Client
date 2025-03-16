package com.jagex.runetek4;

import com.jagex.runetek4.core.utils.MillisTimer;
import com.jagex.runetek4.core.utils.Timer;
import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static70 {

	@OriginalMember(owner = "runetek4.client!fi", name = "j", descriptor = "Lclient!qf;")
	public static Sprite aClass3_Sub2_Sub1_2;

	@OriginalMember(owner = "runetek4.client!fi", name = "m", descriptor = "[[[B")
	public static byte[][][] aByteArrayArrayArray7;

	@OriginalMember(owner = "runetek4.client!fi", name = "k", descriptor = "I")
	public static int crossMode = 0;

	@OriginalMember(owner = "runetek4.client!fi", name = "l", descriptor = "I")
	public static int anInt2014 = 0;

	@OriginalMember(owner = "runetek4.client!fi", name = "n", descriptor = "I")
	public static int updatedVarpsWriterIndex = 0;

	@OriginalMember(owner = "runetek4.client!fi", name = "a", descriptor = "(B)Lclient!s;")
	public static Timer method1547() {
		try {
			return (Timer) Class.forName("com.jagex.runetek4.core.utils.NanoTimer").getDeclaredConstructor().newInstance();
		} catch (@Pc(15) Throwable local15) {
			return new MillisTimer();
		}
	}

	@OriginalMember(owner = "runetek4.client!fi", name = "a", descriptor = "(BI)Lclient!na;")
	public static JString method1548(@OriginalArg(1) int arg0) {
		@Pc(9) JString local9 = JString.parseInt(arg0);
		for (@Pc(21) int local21 = local9.length() - 3; local21 > 0; local21 -= 3) {
			local9 = JString.concatenate(new JString[] { local9.substring(local21, 0), Static159.aClass100_760, local9.substring(local21) });
		}
		if (local9.length() > 9) {
			return JString.concatenate(new JString[] { Static250.aClass100_1043, local9.substring(local9.length() - 8, 0), LocalizedText.MILLION_SHORT, Static123.aClass100_593, local9, Static116.aClass100_583 });
		} else if (local9.length() > 6) {
			return JString.concatenate(new JString[] { Static119.aClass100_589, local9.substring(local9.length() - 4, 0), LocalizedText.THOUSAND_SHORT, Static123.aClass100_593, local9, Static116.aClass100_583 });
		} else {
			return JString.concatenate(new JString[] { Static278.aClass100_1101, local9, Static230.aClass100_978 });
		}
	}

	@OriginalMember(owner = "runetek4.client!fi", name = "a", descriptor = "(III)V")
	public static void method1549(@OriginalArg(1) int arg0, @OriginalArg(2) int arg1) {
		for (@Pc(11) int local11 = 0; local11 < Static98.anInt2510; local11++) {
			@Pc(18) FloorOverlayType local18 = Static256.method4395(local11);
			if (local18 != null) {
				@Pc(24) int local24 = local18.material;
				if (local24 >= 0 && !Pix3D.anInterface1_2.method3236(local24)) {
					local24 = -1;
				}
				@Pc(53) int local53;
				@Pc(66) int local66;
				@Pc(72) int local72;
				@Pc(95) int local95;
				if (local18.averagecolour >= 0) {
					local66 = local18.averagecolour;
					local72 = (local66 & 0x7F) + arg0;
					if (local72 < 0) {
						local72 = 0;
					} else if (local72 > 127) {
						local72 = 127;
					}
					local95 = (local66 & 0x380) + (arg1 + local66 & 0xFC00) + local72;
					local53 = Pix3D.anIntArray220[Static230.method3949(local95, 96)];
				} else if (local24 >= 0) {
					local53 = Pix3D.anIntArray220[Static230.method3949(Pix3D.anInterface1_2.method3234(local24), 96)];
				} else if (local18.rgb == -1) {
					local53 = -1;
				} else {
					local66 = local18.rgb;
					local72 = arg0 + (local66 & 0x7F);
					if (local72 < 0) {
						local72 = 0;
					} else if (local72 > 127) {
						local72 = 127;
					}
					local95 = local72 + (local66 & 0x380) + (local66 + arg1 & 0xFC00);
					local53 = Pix3D.anIntArray220[Static230.method3949(local95, 96)];
				}
				Static145.anIntArray330[local11 + 1] = local53;
			}
		}
	}
}
