package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.enumtype.EnumType;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static253 {

	@OriginalMember(owner = "runetek4.client!ui", name = "Q", descriptor = "I")
	public static int anInt5526;

	@OriginalMember(owner = "runetek4.client!ui", name = "R", descriptor = "I")
	public static int anInt5527;

	@OriginalMember(owner = "runetek4.client!ui", name = "T", descriptor = "F")
	public static float aFloat36;

	@OriginalMember(owner = "runetek4.client!ui", name = "eb", descriptor = "[[[B")
	public static byte[][][] levelTileUnderlayIds;

	@OriginalMember(owner = "runetek4.client!ui", name = "mb", descriptor = "F")
	public static float aFloat37;

	@OriginalMember(owner = "runetek4.client!ui", name = "c", descriptor = "(II)I")
	public static int method4328(@OriginalArg(0) int arg0) {
		return arg0 >>> 8;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "a", descriptor = "(IZ)Lclient!ml;")
	public static EnumType get(@OriginalArg(0) int id) {
		@Pc(10) EnumType enumType = (EnumType) Static149.aClass54_10.get((long) id);
		if (enumType != null) {
			return enumType;
		}
		@Pc(24) byte[] bytes = Static84.aClass153_35.getfile(Static97.method1959(id), Static103.method2236(id));
		enumType = new EnumType();
		if (bytes != null) {
			enumType.decode(new Packet(bytes));
		}
		Static149.aClass54_10.put(enumType, (long) id);
		return enumType;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "h", descriptor = "(I)[Lclient!ok;")
	public static IndexedSprite[] method4331() {
		@Pc(8) IndexedSprite[] local8 = new IndexedSprite[Static165.anInt4038];
		for (@Pc(10) int local10 = 0; local10 < Static165.anInt4038; local10++) {
			if (GlRenderer.enabled) {
				local8[local10] = new GlIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], aClass6.aByteArrayArray5[local10], Static259.anIntArray513);
			} else {
				local8[local10] = new SoftwareIndexedSprite(Static124.anInt3080, Static227.anInt5091, Static274.anIntArray440[local10], Static269.anIntArray252[local10], Static254.anIntArray488[local10], Static26.anIntArray66[local10], aClass6.aByteArrayArray5[local10], Static259.anIntArray513);
			}
		}
		Static75.method1631();
		return local8;
	}

	@OriginalMember(owner = "runetek4.client!ui", name = "d", descriptor = "(II)V")
	public static void method4332(@OriginalArg(0) int arg0) {
		if (arg0 >= 0 && Static258.aBooleanArray130.length > arg0) {
			Static258.aBooleanArray130[arg0] = !Static258.aBooleanArray130[arg0];
		}
	}
}
