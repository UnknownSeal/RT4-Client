package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.HashTable;
import com.jagex.runetek4.game.config.flotype.FloorOverlayType;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.scene.Scenery;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static243 {

	@OriginalMember(owner = "runetek4.client!tk", name = "o", descriptor = "Lclient!ok;")
	public static IndexedSprite aClass36_1;

	@OriginalMember(owner = "runetek4.client!tk", name = "s", descriptor = "Lclient!ve;")
	public static Js5 aClass153_98;

	@OriginalMember(owner = "runetek4.client!tk", name = "D", descriptor = "[Lclient!ec;")
	public static Scenery[] aClass31Array3;

	@OriginalMember(owner = "runetek4.client!tk", name = "K", descriptor = "[I")
	public static int[] anIntArray476 = new int[2];

	@OriginalMember(owner = "runetek4.client!tk", name = "a", descriptor = "(Lclient!sc;ZLclient!wl;)Lclient!hg;")
	public static Class3_Sub14 method4212(@OriginalArg(0) HashTable arg0, @OriginalArg(2) FloorOverlayType arg1) {
		@Pc(23) long local23 = (long) ((arg1.material + 1 << 16) + arg1.materialScale) + ((long) arg1.textureBrightness << 56) + ((long) arg1.waterColor << 32);
		@Pc(38) Class3_Sub14 local38 = (Class3_Sub14) arg0.getNode(local23);
		if (local38 == null) {
			local38 = new Class3_Sub14(arg1.material, (float) arg1.materialScale, true, false, arg1.waterColor);
			arg0.put(local38, local23);
		}
		return local38;
	}

}
