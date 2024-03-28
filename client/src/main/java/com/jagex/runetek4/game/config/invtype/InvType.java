package com.jagex.runetek4.game.config.invtype;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;
import com.jagex.runetek4.SecondaryNode;
import com.jagex.runetek4.core.io.Packet;

@OriginalClass("client!md")
public final class InvType extends SecondaryNode {

	@OriginalMember(owner = "client!md", name = "K", descriptor = "I")
	public int size = 0;

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(10) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!wa;IZ)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(1) int code) {
		if (code == 2) {
			this.size = packet.g2();
		}
	}
}