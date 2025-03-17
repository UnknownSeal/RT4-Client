package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.SoftwareSprite;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static213 {

	@OriginalMember(owner = "runetek4.client!re", name = "w", descriptor = "[Lclient!qf;")
	public static Sprite[] aClass3_Sub2_Sub1Array8;

	@OriginalMember(owner = "runetek4.client!re", name = "b", descriptor = "(I)[Lclient!qf;")
	public static Sprite[] method3730() {
		@Pc(14) Sprite[] local14 = new Sprite[Static165.anInt4038];
		for (@Pc(16) int local16 = 0; local16 < Static165.anInt4038; local16++) {
			@Pc(23) byte[] local23 = aClass6.aByteArrayArray5[local16];
			@Pc(31) int local31 = Static26.anIntArray66[local16] * Static254.anIntArray488[local16];
			if (Static159.aBooleanArray87[local16]) {
				@Pc(38) int[] local38 = new int[local31];
				@Pc(42) byte[] local42 = Static64.aByteArrayArray9[local16];
				for (@Pc(44) int local44 = 0; local44 < local31; local44++) {
					local38[local44] = Static259.anIntArray513[local23[local44] & 0xFF] | (local42[local44] & 0xFF) << 24;
				}
				if (GlRenderer.enabled) {
					local14[local16] = new GlAlphaSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local16], Static269.anIntArray252[local16], Static254.anIntArray488[local16], Static26.anIntArray66[local16], local38);
				} else {
					local14[local16] = new SoftwareAlphaSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local16], Static269.anIntArray252[local16], Static254.anIntArray488[local16], Static26.anIntArray66[local16], local38);
				}
			} else {
				@Pc(119) int[] local119 = new int[local31];
				for (@Pc(121) int local121 = 0; local121 < local31; local121++) {
					local119[local121] = Static259.anIntArray513[local23[local121] & 0xFF];
				}
				if (GlRenderer.enabled) {
					local14[local16] = new GlSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local16], Static269.anIntArray252[local16], Static254.anIntArray488[local16], Static26.anIntArray66[local16], local119);
				} else {
					local14[local16] = new SoftwareSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local16], Static269.anIntArray252[local16], Static254.anIntArray488[local16], Static26.anIntArray66[local16], local119);
				}
			}
		}
		Static75.method1631();
		return local14;
	}
}
