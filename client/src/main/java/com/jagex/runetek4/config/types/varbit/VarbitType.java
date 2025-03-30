package com.jagex.runetek4.config.types.varbit;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

// VarbitDefinition
@OriginalClass("client!kk")
public final class VarbitType {

	@OriginalMember(owner = "client!kk", name = "c", descriptor = "I")
	public int startBit;

	@OriginalMember(owner = "client!kk", name = "h", descriptor = "I")
	public int endBit;

	@OriginalMember(owner = "client!kk", name = "l", descriptor = "I")
	public int baseVar;

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(2) int opcode) {
		if (opcode == 1) {
			this.baseVar = packet.g2();
			this.startBit = packet.g1();
			this.endBit = packet.g1();
		}
	}
}
