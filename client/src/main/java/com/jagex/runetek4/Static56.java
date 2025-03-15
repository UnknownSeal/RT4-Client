package com.jagex.runetek4;

import com.jagex.runetek4.cache.media.ImageRGB;
import com.jagex.runetek4.cache.media.component.Component;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static56 {

	@OriginalMember(owner = "runetek4.client!ef", name = "j", descriptor = "Lclient!mm;")
	public static ImageRGB aClass3_Sub2_Sub1_Sub1_1;

	@OriginalMember(owner = "runetek4.client!ef", name = "p", descriptor = "Lclient!vj;")
	public static Resampler aClass156_1;

	@OriginalMember(owner = "runetek4.client!ef", name = "a", descriptor = "[I")
	public static final int[] anIntArray141 = new int[] { 768, 1024, 1280, 512, 1536, 256, 0, 1792 };

	@OriginalMember(owner = "runetek4.client!ef", name = "c", descriptor = "[I")
	public static final int[] anIntArray142 = new int[500];

	@OriginalMember(owner = "runetek4.client!ef", name = "f", descriptor = "Lclient!na;")
	public static final JString DUELSTAKE = Static28.parse(":duelstake:");

	@OriginalMember(owner = "runetek4.client!ef", name = "g", descriptor = "I")
	public static int clickTileX = -1;

	@OriginalMember(owner = "runetek4.client!ef", name = "h", descriptor = "Lclient!na;")
	public static final JString aClass100_380 = Static28.parse("unzap");

	@OriginalMember(owner = "runetek4.client!ef", name = "i", descriptor = "I")
	public static int anInt1743 = 0;

	@OriginalMember(owner = "runetek4.client!ef", name = "r", descriptor = "Lclient!be;")
	public static Component aClass13_12 = null;

	@OriginalMember(owner = "runetek4.client!ef", name = "a", descriptor = "(I)I")
	public static int method1314() {
		return Static48.anInt1447 == 0 ? 0 : Static2.anInterface4Array1[Static48.anInt1447].method4605();
	}
}
