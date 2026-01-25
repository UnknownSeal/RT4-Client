package com.jagex.types.struct;

import com.jagex.core.datastruct.key.IntNode;
import com.jagex.core.datastruct.key.IterableHashTable;
import com.jagex.core.datastruct.key.JstringNode;
import com.jagex.core.utils.string.JString;
import com.jagex.core.io.Packet;
import com.jagex.core.datastruct.Node2;
import com.jagex.core.datastruct.Node;
import com.jagex.math.IntMath;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!lk")
public final class StructType extends Node2 {

	@OriginalMember(owner = "runetek4.client!lk", name = "I", descriptor = "Lclient!sc;")
	private IterableHashTable params;

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(IIB)I")
	public final int method2798(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		if (this.params == null) {
			return arg1;
		} else {
			@Pc(29) IntNode local29 = (IntNode) this.params.get((long) arg0);
			return local29 == null ? arg1 : local29.value;
		}
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(Lclient!wa;IB)V")
	private void decode(@OriginalArg(0) Packet arg0, @OriginalArg(1) int arg1) {
		if (arg1 != 249) {
			return;
		}
		@Pc(17) int local17 = arg0.g1();
		@Pc(25) int local25;
		if (this.params == null) {
			local25 = IntMath.bitceil(local17);
			this.params = new IterableHashTable(local25);
		}
		for (local25 = 0; local25 < local17; local25++) {
			@Pc(45) boolean local45 = arg0.g1() == 1;
			@Pc(49) int local49 = arg0.g3();
			@Pc(58) Node local58;
			if (local45) {
				local58 = new JstringNode(arg0.gjstr());
			} else {
				local58 = new IntNode(arg0.g4());
			}
			this.params.put(local58, (long) local49);
		}
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(Lclient!na;BI)Lclient!na;")
	public final JString getParam(@OriginalArg(0) JString arg0, @OriginalArg(2) int arg1) {
		if (this.params == null) {
			return arg0;
		} else {
			@Pc(16) JstringNode local16 = (JstringNode) this.params.get((long) arg1);
			return local16 == null ? arg0 : local16.value;
		}
	}

	@OriginalMember(owner = "runetek4.client!lk", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(5) int local5 = arg0.g1();
			if (local5 == 0) {
				return;
			}
			this.decode(arg0, local5);
		}
	}
}
