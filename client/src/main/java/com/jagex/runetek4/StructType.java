package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.*;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!lk")
public final class StructType extends CachedNode {

	@OriginalMember(owner = "runetek4.client!lk", name = "I", descriptor = "Lclient!sc;")
	private IterableMap aClass133_14;

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIB)I")
	public final int method2798(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (this.aClass133_14 == null) {
			return arg1;
		} else {
			@Pc(29) IntWrapper local29 = (IntWrapper) this.aClass133_14.getNode((long) arg0);
			return local29 == null ? arg1 : local29.value;
		}
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(Lclient!wa;IB)V")
	private void method2799(@OriginalArg(0) Packet arg0, @OriginalArg(1) int arg1) {
		if (arg1 != 249) {
			return;
		}
		@Pc(17) int local17 = arg0.g1();
		@Pc(25) int local25;
		if (this.aClass133_14 == null) {
			local25 = Static165.bitceil(local17);
			this.aClass133_14 = new IterableMap(local25);
		}
		for (local25 = 0; local25 < local17; local25++) {
			@Pc(45) boolean local45 = arg0.g1() == 1;
			@Pc(49) int local49 = arg0.g3();
			@Pc(58) Node local58;
			if (local45) {
				local58 = new JagStringWrapper(arg0.gjstr());
			} else {
				local58 = new IntWrapper(arg0.g4());
			}
			this.aClass133_14.pushNode(local58, (long) local49);
		}
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(Lclient!na;BI)Lclient!na;")
	public final JString method2802(@OriginalArg(0) JString arg0, @OriginalArg(2) int arg1) {
		if (this.aClass133_14 == null) {
			return arg0;
		} else {
			@Pc(16) JagStringWrapper local16 = (JagStringWrapper) this.aClass133_14.getNode((long) arg1);
			return local16 == null ? arg0 : local16.value;
		}
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(ILclient!wa;)V")
	public final void method2806(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(5) int local5 = arg0.g1();
			if (local5 == 0) {
				return;
			}
			this.method2799(arg0, local5);
		}
	}
}
