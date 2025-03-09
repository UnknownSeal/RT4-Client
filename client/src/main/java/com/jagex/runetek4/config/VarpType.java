package com.jagex.runetek4.config;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

// VarpType
@OriginalClass("client!eh")
public final class VarpType {

	@OriginalMember(owner = "client!eh", name = "e", descriptor = "I")
	public int clientCode = 0;

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(ILclient!wa;)V")
	public void decode(@OriginalArg(1) Packet packet) {
		while (true) {
			@Pc(5) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!eh", name = "a", descriptor = "(Lclient!wa;BI)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(2) int code) {
		if (code == 5) {
			this.clientCode = packet.g2();
		}
	}
}
