package com.jagex.runetek4.config.types.flu;

import com.jagex.runetek4.core.io.Packet;
import com.jagex.runetek4.js5.Js5;
import com.jagex.runetek4.node.SoftLruHashTable;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class FluTypeList {

	@OriginalMember(owner = "client!gj", name = "p", descriptor = "Lclient!n;")
	public static final SoftLruHashTable types = new SoftLruHashTable(64);

	@OriginalMember(owner = "client!sd", name = "S", descriptor = "I")
	public static int anInt5063 = 100;
	@OriginalMember(owner = "runetek4.client!oj", name = "x", descriptor = "Lclient!ve;")
	public static Js5 archive;

	@OriginalMember(owner = "client!sd", name = "f", descriptor = "(B)V")
	public static void removeSoft() {
		types.removeSoft();
	}

	@OriginalMember(owner = "runetek4.client!qc", name = "a", descriptor = "(ZI)Lclient!ni;")
	public static FluType get(@OriginalArg(1) int arg0) {
		@Pc(10) FluType floorUnderlay = (FluType) types.get((long) arg0);
		if (floorUnderlay != null) {
			return floorUnderlay;
		}
		@Pc(27) byte[] local27 = archive.getfile(1, arg0);
		floorUnderlay = new FluType();
		if (local27 != null) {
			floorUnderlay.decode(new Packet(local27));
		}
		types.put(floorUnderlay, (long) arg0);
		return floorUnderlay;
	}

	@OriginalMember(owner = "runetek4.client!hc", name = "a", descriptor = "(Lclient!ve;I)V")
	public static void init(@OriginalArg(0) Js5 arg0) {
		archive = arg0;
	}

	@OriginalMember(owner = "client!fk", name = "b", descriptor = "(IB)V")
	public static void clean() {
		types.clean(5);
	}

	@OriginalMember(owner = "client!ed", name = "c", descriptor = "(I)V")
	public static void clear() {
		types.clean();
	}
}
