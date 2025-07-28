package com.jagex.runetek4.config.types.inv;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.core.node.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!md")
public final class InvType extends SecondaryNode {

	@OriginalMember(owner = "client!md", name = "K", descriptor = "I")
	public int size = 0;

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(10) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(packet, opcode);
		}
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!wa;IZ)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(1) int opcode) {
		if (opcode == 2) {
			this.size = packet.g2();
		}
	}
}