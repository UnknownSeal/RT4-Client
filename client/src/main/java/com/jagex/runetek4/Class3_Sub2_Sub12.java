package com.jagex.runetek4;

import com.jagex.runetek4.core.datastruct.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!hn")
public final class Class3_Sub2_Sub12 extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!hn", name = "I", descriptor = "I")
	public int anInt2667;

	@OriginalMember(owner = "runetek4.client!hn", name = "L", descriptor = "I")
	private int anInt2669;

	@OriginalMember(owner = "runetek4.client!hn", name = "Q", descriptor = "Lclient!na;")
	public JString aClass100_544;

	@OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(ILclient!wa;I)V")
	private void method2072(@OriginalArg(0) int arg0, @OriginalArg(1) Packet arg1) {
		if (arg0 == 1) {
			this.anInt2669 = arg1.g1();
		} else if (arg0 == 2) {
			this.anInt2667 = arg1.g4();
		} else if (arg0 == 5) {
			this.aClass100_544 = arg1.gjstr();
		}
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(ILclient!wa;)V")
	public final void method2076(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(13) int local13 = arg0.g1();
			if (local13 == 0) {
				return;
			}
			this.method2072(local13, arg0);
		}
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "f", descriptor = "(I)Z")
	public final boolean method2078() {
		return this.anInt2669 == 115;
	}
}
