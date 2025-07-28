package com.jagex.runetek4.config.types.param;

import com.jagex.runetek4.util.string.JString;
import com.jagex.runetek4.core.node.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("runetek4.client!hn")
public final class ParamType extends SecondaryNode {

	@OriginalMember(owner = "runetek4.client!hn", name = "I", descriptor = "I")
	public int defaultInt;

	@OriginalMember(owner = "runetek4.client!hn", name = "L", descriptor = "I")
	private int type;

	@OriginalMember(owner = "runetek4.client!hn", name = "Q", descriptor = "Lclient!na;")
	public JString defaultString;

	@OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(ILclient!wa;I)V")
	private void decode(@OriginalArg(0) int opcode, @OriginalArg(1) Packet arg1) {
		if (opcode == 1) {
			this.type = arg1.g1();
		} else if (opcode == 2) {
			this.defaultInt = arg1.g4();
		} else if (opcode == 5) {
			this.defaultString = arg1.gjstr();
		}
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "a", descriptor = "(ILclient!wa;)V")
	public final void decode(@OriginalArg(1) Packet arg0) {
		while (true) {
			@Pc(13) int opcode = arg0.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(opcode, arg0);
		}
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "f", descriptor = "(I)Z")
	public final boolean isString() {
		return this.type == 115;
	}
}
