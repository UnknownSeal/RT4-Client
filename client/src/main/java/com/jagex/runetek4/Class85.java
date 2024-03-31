package com.jagex.runetek4;

import com.jagex.runetek4.core.io.Packet;
import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalClass;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

// Varbit
@OriginalClass("client!kk")
public final class Class85 {

	@OriginalMember(owner = "client!kk", name = "c", descriptor = "I")
	public int anInt3318;

	@OriginalMember(owner = "client!kk", name = "h", descriptor = "I")
	public int anInt3323;

	@OriginalMember(owner = "client!kk", name = "l", descriptor = "I")
	public int anInt3327;

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;I)V")
	public void decode(@OriginalArg(0) Packet packet) {
		while (true) {
			@Pc(9) int code = packet.g1();
			if (code == 0) {
				return;
			}
			this.decode(packet, code);
		}
	}

	@OriginalMember(owner = "client!kk", name = "a", descriptor = "(Lclient!wa;II)V")
	private void decode(@OriginalArg(0) Packet packet, @OriginalArg(2) int code) {
		if (code == 1) {
			this.anInt3327 = packet.g2();
			this.anInt3318 = packet.g1();
			this.anInt3323 = packet.g1();
		}
	}
}
