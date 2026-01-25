package com.jagex.game.runetek4.config.paramtype;

import com.jagex.core.utils.string.JString;
import com.jagex.core.datastruct.Node2;
import com.jagex.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

@OriginalClass("client!hn")
public final class ParamType extends Node2 {

	@OriginalMember(owner = "client!hn", name = "I", descriptor = "I")
	public int defaultInt;

	@OriginalMember(owner = "client!hn", name = "L", descriptor = "I")
	private int type;

	@OriginalMember(owner = "client!hn", name = "Q", descriptor = "Lclient!na;")
	public JString defaultString;

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(ILclient!wa;I)V")
	private void decode(@OriginalArg(0) int opcode, @OriginalArg(1) Packet packet) {
		if (opcode == 1) {
			this.type = packet.g1();
		} else if (opcode == 2) {
			this.defaultInt = packet.g4();
		} else if (opcode == 5) {
			this.defaultString = packet.gjstr();
		}
	}

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(13) int opcode = packet.g1();
			if (opcode == 0) {
				return;
			}
			this.decode(opcode, packet);
		}
	}

	@OriginalMember(owner = "runetek4.client!hn", name = "f", descriptor = "(I)Z")
	public boolean isString() {
		return this.type == 115;
	}
}
