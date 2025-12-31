package com.jagex.game.runetek4.config.invtype;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.core.datastruct.Node2;
import com.jagex.core.io.Packet;

@OriginalClass("client!md")
public final class InvType extends Node2 {

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