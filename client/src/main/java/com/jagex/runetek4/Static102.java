package com.jagex.runetek4;

import java.util.Calendar;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static102 {

	@OriginalMember(owner = "runetek4.client!hn", name = "K", descriptor = "Ljava/util/Calendar;")
	public static final Calendar aCalendar2 = Calendar.getInstance();

	@OriginalMember(owner = "runetek4.client!hn", name = "V", descriptor = "I")
	public static int anInt2678 = 0;

	@OriginalMember(owner = "runetek4.client!hn", name = "Y", descriptor = "I")
	public static int anInt2680 = 0;

	@OriginalMember(owner = "runetek4.client!hn", name = "d", descriptor = "(I)Lclient!mm;")
	public static SoftwareSprite method2071() {
		@Pc(13) int local13 = Static26.anIntArray66[0] * Static254.anIntArray488[0];
		@Pc(17) byte[] local17 = aClass6.aByteArrayArray5[0];
		@Pc(78) SoftwareSprite local78;
		if (Static159.aBooleanArray87[0]) {
			@Pc(30) byte[] local30 = Static64.aByteArrayArray9[0];
			@Pc(33) int[] local33 = new int[local13];
			for (@Pc(35) int local35 = 0; local35 < local13; local35++) {
				local33[local35] = (local30[local35] & 0xFF) << 24 | Static259.anIntArray513[local17[local35] & 0xFF];
			}
			local78 = new SoftwareAlphaSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], local33);
		} else {
			@Pc(83) int[] local83 = new int[local13];
			for (@Pc(85) int local85 = 0; local85 < local13; local85++) {
				local83[local85] = Static259.anIntArray513[local17[local85] & 0xFF];
			}
			local78 = new SoftwareSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[0], Static269.anIntArray252[0], Static254.anIntArray488[0], Static26.anIntArray66[0], local83);
		}
		Static75.method1631();
		return local78;
	}

}
