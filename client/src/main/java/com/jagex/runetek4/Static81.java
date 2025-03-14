package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.game.config.idktype.IDKType;
import com.jagex.runetek4.cache.media.component.Component;
import com.jagex.runetek4.js5.Js5;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static81 {

	@OriginalMember(owner = "runetek4.client!gg", name = "Z", descriptor = "I")
	public static int anInt2222;

	@OriginalMember(owner = "runetek4.client!gg", name = "bb", descriptor = "I")
	public static int anInt2223;

	@OriginalMember(owner = "runetek4.client!gg", name = "U", descriptor = "I")
	public static int modeWhat = 0;

	@OriginalMember(owner = "runetek4.client!gg", name = "W", descriptor = "Lclient!na;")
	public static final JString NULL = Static28.parse("null");

	@OriginalMember(owner = "runetek4.client!gg", name = "Y", descriptor = "Lclient!na;")
	public static final JString GC = Static28.parse("::gc");

	@OriginalMember(owner = "runetek4.client!gg", name = "db", descriptor = "I")
	public static int anInt2225 = -1;

	@OriginalMember(owner = "runetek4.client!gg", name = "a", descriptor = "([[IZ)V")
	public static void method1751(@OriginalArg(0) int[][] arg0) {
		Static71.anIntArrayArray10 = arg0;
	}

	@OriginalMember(owner = "runetek4.client!gg", name = "d", descriptor = "(II)Lclient!dm;")
	public static IDKType get(@OriginalArg(0) int arg0) {
		@Pc(10) IDKType idkType = (IDKType) Static67.aClass99_20.get((long) arg0);
		if (idkType != null) {
			return idkType;
		}
		@Pc(21) byte[] bytes = Static216.aClass153_31.getfile(3, arg0);
		idkType = new IDKType();
		if (bytes != null) {
			idkType.decode(new Packet(bytes));
		}
		Static67.aClass99_20.put(idkType, (long) arg0);
		return idkType;
	}

	@OriginalMember(owner = "runetek4.client!gg", name = "e", descriptor = "(II)V")
	public static void method1753(@OriginalArg(0) int arg0) {
		if (!Component.load(arg0)) {
			return;
		}
		@Pc(15) Component[] local15 = Component.cachedComponents[arg0];
		for (@Pc(17) int local17 = 0; local17 < local15.length; local17++) {
			@Pc(29) Component local29 = local15[local17];
			if (local29 != null) {
				local29.anInt496 = 1;
				local29.anInt510 = 0;
				local29.anInt500 = 0;
			}
		}
	}

	@OriginalMember(owner = "runetek4.client!gg", name = "a", descriptor = "(ILclient!ve;)V")
	public static void method1754(@OriginalArg(1) Js5 arg0) {
		Static138.anInt3443 = arg0.method4482(Static12.aClass100_73);
	}

}
